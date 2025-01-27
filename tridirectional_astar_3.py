import heapq
import math

def heuristic(node1, node2):
    """heuristic function: Euclidean distance."""
    x1, y1 = node1
    x2, y2 = node2
    return math.sqrt((x1 - x2) ** 2 + (y1 - y2) ** 2)

def bidirectional_a_star(graph, start, goal):
    """Bidirectional A* search between two nodes."""
    forward_pq = []
    backward_pq = []
    heapq.heappush(forward_pq, (0 + heuristic(start, goal), 0, start, [start]))
    heapq.heappush(backward_pq, (0 + heuristic(goal, start), 0, goal, [goal]))

    forward_visited = {}
    backward_visited = {}

    while forward_pq and backward_pq:
        # expand forward search
        if forward_pq:
            f, g, current, path = heapq.heappop(forward_pq)
            if current in forward_visited:
                continue
            forward_visited[current] = (g, path)

            # check for meeting point
            if current in backward_visited:
                backward_g, backward_path = backward_visited[current]
                return g + backward_g, path + backward_path[::-1][1:]

            for neighbor, weight in graph[current].items():
                if neighbor not in forward_visited:
                    heapq.heappush(forward_pq, (g + weight + heuristic(neighbor, goal), g + weight, neighbor, path + [neighbor]))

        # expand backward search
        if backward_pq:
            f, g, current, path = heapq.heappop(backward_pq)
            if current in backward_visited:
                continue
            backward_visited[current] = (g, path)

            # check for meeting point
            if current in forward_visited:
                forward_g, forward_path = forward_visited[current]
                return g + forward_g, forward_path + path[::-1][1:]

            for neighbor, weight in graph[current].items():
                if neighbor not in backward_visited:
                    heapq.heappush(backward_pq, (g + weight + heuristic(neighbor, start), g + weight, neighbor, path + [neighbor]))

    return float('inf'), []  # return infinite cost and empty path if no path is found

def is_graph_connected(graph):
    """check if the graph is connected using BFS."""
    if not graph:
        return True  # an empty graph is trivially connected.

    start_node = next(iter(graph))  # get any node from the graph
    visited = set()
    queue = [start_node]

    while queue:
        current = queue.pop(0)
        if current not in visited:
            visited.add(current)
            for neighbor in graph[current]:
                if neighbor not in visited:
                    queue.append(neighbor)

    return len(visited) == len(graph)

def tridirectional_astar(graph, node1, node2, node3):
    """find the optimal path and cost among three nodes using bidirectional A* search."""
    # check if the graph is connected
    if not is_graph_connected(graph):
        return "the graph is not fully connected. cannot compute a valid path.", float('inf')

    # compute pairwise shortest paths
    cost12, path12 = bidirectional_a_star(graph, node1, node2)
    cost23, path23 = bidirectional_a_star(graph, node2, node3)
    cost13, path13 = bidirectional_a_star(graph, node1, node3)

    # handle cases where paths are not found (infinite cost)
    if cost12 == float('inf') or cost23 == float('inf') or cost13 == float('inf'):
        return "Some paths cannot be found in the graph.", float('inf')

    # list all possible paths with their total costs
    possible_paths = [
        (path12[:-1] + path23, cost12 + cost23),  # A -> B -> C
        (path13[:-1] + path23[::-1], cost13 + cost23),  # A -> C -> B
        (path23[:-1] + path12[::-1], cost23 + cost12),  # B -> C -> A
    ]

    # find the optimal path
    optimal_path, optimal_cost = min(possible_paths, key=lambda x: x[1])

    return optimal_path, optimal_cost

# example graph (adjacency list)
example_graph = {
    (0, 0): {(0, 1): 1, (1, 0): 1.5},
    (0, 1): {(1, 0): 1, (1, 1): 1.2},
    (1, 0): {(0, 0): 1.5, (1, 1): 0.8, (2,2): 4.5},
    (1, 1): {(0, 1): 1.2, (1, 0): 0.8, (2,2): 2},
    (2, 2): {(1, 0): 4.5,(1,1): 2}
}

# nodes
node1 = (0, 0)
node2 = (0, 1)
node3 = (2, 2)

path, cost = tridirectional_astar(example_graph, node1, node2, node3)
print(f"optimal path: {path}")
print(f"total cost: {cost}")