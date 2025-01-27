import heapq
import math

def euclidean_distance(coord1, coord2):
    """calc. the euclidean distance between two coordinates."""
    return math.sqrt((coord1[0] - coord2[0])**2 + (coord1[1] - coord2[1])**2)

def a_star_search(graph, start, goal, coordinates):
    # store (f, g, node, path)
    priority_queue = [(0, 0, start, [start])]
    visited = set()

    while priority_queue:
        # pop the node with lowest f-score
        f, g, current_node, path = heapq.heappop(priority_queue)

        if current_node in visited:
            continue

        # mark the current node as visited
        visited.add(current_node)

        # goal check
        if current_node == goal:
            return g, path

        # add neighbors to the priority queue
        for neighbor, weight in graph.get(current_node, []):
            if neighbor not in visited:
                new_g = g + weight
                h = euclidean_distance(coordinates[neighbor], coordinates[goal])
                new_f = new_g + h
                heapq.heappush(priority_queue, (new_f, new_g, neighbor, path + [neighbor]))

    return float("inf"), []  # return infinity if the goal is not reachable

# graph represented as an adjacency list with weights
graph = {
    'A': [('B', 1), ('C', 4)],
    'B': [('D', 2), ('E', 5)],
    'C': [('F', 1)],
    'D': [('G', 3)],
    'E': [('G', 1)],
    'F': [('G', 2)],
    'G': []
}

# coordinates for each node
coordinates = {
    'A': (0, 0),
    'B': (1, 1),
    'C': (1, 3),
    'D': (2, 2),
    'E': (3, 1),
    'F': (2, 4),
    'G': (4, 2)
}

start_node = 'A'
goal_node = 'G'

cost, path = a_star_search(graph, start_node, goal_node, coordinates)
print("min Cost:", cost)
print("path:", path)