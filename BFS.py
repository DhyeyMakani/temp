from collections import deque

def bfs(graph, start):
    visited = set()       # To keep track of visited nodes
    queue = deque([start]) # Initialize a queue with the start node
    result = []           # To store the traversal order

    while queue:
        node = queue.popleft()  # Dequeue a node
        if node not in visited:
            visited.add(node)  # Mark the node as visited
            result.append(node)  # Add it to the result list

            # Add unvisited neighbors to the queue
            for neighbor in graph[node]:
                if neighbor not in visited:
                    queue.append(neighbor)

    return result

# Example usage:
graph = {
    'A': ['B', 'C'],
    'B': ['D', 'E'],
    'C': ['F'],
    'D': [],
    'E': ['F'],
    'F': []
}

start_node = 'A'
print("BFS Traversal:", bfs(graph, start_node))