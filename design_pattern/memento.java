import java.util.Stack;

// memento: stores the text state
class TextMemento {
    private final String text;

    public TextMemento(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

// originator: creates and restores mementos
class TextEditor {
    private String text = "";

    public void type(String newText) {
        text += newText;
    }

    public TextMemento save() {
        return new TextMemento(text);
    }

    public void restore(TextMemento memento) {
        text = memento.getText();
    }

    public void print() {
        System.out.println("Current Text: " + text);
    }
}

// Caretaker: Manages mementos
class History {
    private Stack<TextMemento> history = new Stack<>();

    public void save(TextEditor editor) {
        history.push(editor.save());
    }

    public void undo(TextEditor editor) {
        if (!history.isEmpty()) {
            editor.restore(history.pop());
        }
    }
}

public class memento {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        History history = new History();

        editor.type("Hello ");
        history.save(editor); // save state

        editor.type("World!");
        editor.print(); // o.p.: "Hello World!"

        history.undo(editor); // undo
        editor.print(); // o.p.: "Hello "
    }
}