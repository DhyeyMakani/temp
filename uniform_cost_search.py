import heapq

def uniform_cost_search(graph, start, goal):
    # priority queue to store (cost, node, path)
    priority_queue = [(0, start, [start])]
    visited = set()

    while priority_queue:
        # pop the node with the lowest cost
        cost, current_node, path = heapq.heappop(priority_queue)

        if current_node in visited:
            continue

        # mark the current node as visited
        visited.add(current_node)

        # goal check
        if current_node == goal:
            return cost, path

        # add neighbors to the priority queue
        for neighbor, weight in graph.get(current_node, []):
            if neighbor not in visited:
                heapq.heappush(priority_queue, (cost + weight, neighbor, path + [neighbor]))

    return float("inf"), []  # return infinity if the goal is not reachable

graph = {
    'A': [('B', 1), ('C', 4)],
    'B': [('D', 2), ('E', 5)],
    'C': [('F', 1)],
    'D': [('G', 3)],
    'E': [('G', 1)],
    'F': [('G', 2)],
    'G': []
}

start_node = 'A'
goal_node = 'G'

cost, path = uniform_cost_search(graph, start_node, goal_node)
print("Minimum Cost:", cost)
print("Path:", path)