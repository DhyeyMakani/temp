from flask import Flask, request, jsonify
from pymongo import MongoClient
from bson import ObjectId  # Convert ObjectId for JSON response
import requests

app = Flask(__name__)

# Connect to MongoDB
client = MongoClient("mongodb://localhost:27017/")
db = client["my_database"]  # Match with Spring Boot
collection = db["users"]  # Ensure same collection as Spring Boot

@app.route("/")
def home():
    return """
    <h1>Welcome to the Flask-MongoDB App</h1>
    <p>Go to <a href='/form'>/form</a> to fill out the form.</p>
    """

@app.route("/form", methods=["GET"])
def form():
    return """
    <h1>Submit Data</h1>
    <form id="dataForm">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br>
        <label for="age">Age:</label>
        <input type="number" id="age" name="age" required><br>
        <label for="city">City:</label>
        <input type="text" id="city" name="city" required><br><br>
        <button type="submit">Submit</button>
    </form>

    <script>
        document.getElementById("dataForm").addEventListener("submit", function(event) {
            event.preventDefault();
            
            let formData = {
                name: document.getElementById("name").value,
                age: parseInt(document.getElementById("age").value),
                city: document.getElementById("city").value
            };

            fetch("/submit", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            })
            .then(response => response.json())
            .then(data => alert(JSON.stringify(data)))
            .catch(error => console.error("Error:", error));
        });
    </script>
    """

@app.route("/submit", methods=["POST"])
def submit():
    try:
        # Accept JSON data
        if request.is_json:
            data = request.get_json()
        else:
            # Accept form-data input
            data = {
                "name": request.form.get("name"),
                "age": int(request.form.get("age")),
                "city": request.form.get("city")
            }

        # Validate input
        if not all(key in data for key in ("name", "age", "city")):
            return jsonify({"error": "Missing required fields"}), 400

        # Save data to MongoDB
        result = collection.insert_one(data)
        data["_id"] = str(result.inserted_id)

        return jsonify({"message": "Data saved successfully!", "data": data}), 201

    except Exception as e:
        return jsonify({"error": str(e)}), 500

@app.route("/users", methods=["GET"])
def get_users():
    """Retrieve all users from MongoDB."""
    users = list(collection.find({}, {"_id": 1, "name": 1, "age": 1, "city": 1}))
    for user in users:
        user["_id"] = str(user["_id"])  # Convert ObjectId to string
    return jsonify(users)

@app.route("/user/<id>", methods=["GET"])
def get_user(id):
    """Retrieve a single user by ID."""
    try:
        user = collection.find_one({"_id": ObjectId(id)}, {"_id": 1, "name": 1, "age": 1, "city": 1})
        if user:
            user["_id"] = str(user["_id"])
            return jsonify(user)
        return jsonify({"error": "User not found"}), 404
    except Exception:
        return jsonify({"error": "Invalid ID format"}), 400

@app.route('/spring-hello', methods=['GET'])
def call_spring():
    """Call the Spring Boot API to fetch hello message"""
    try:
        response = requests.get("http://localhost:8080/api/hello", timeout=10)
        print("Spring Boot response status:", response.status_code)
        print("Spring Boot raw response:", response.text)  # Print raw response for debugging

        # Ensure Spring Boot is returning valid JSON
        return response.json()
    except requests.exceptions.RequestException as e:
        return jsonify({"error": "Failed to connect to Spring Boot", "details": str(e)}), 500

if __name__ == "__main__":
    app.run(debug=True)