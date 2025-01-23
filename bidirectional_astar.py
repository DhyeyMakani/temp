from collections import deque
import heapq

# function to construct the path from both sides
def cons_path(meet_nd, src_par, dest_par):
    path = []
    cur = meet_nd

    # add path from source to meeting node
    while cur != -1:
        path.append(cur)
        cur = src_par[cur]

    path.reverse()

    # add path from meeting node to destination
    cur = dest_par[meet_nd]
    while cur != -1:
        path.append(cur)
        cur = dest_par[cur]

    return path

# A* heuristic function (this is an example, you should adjust it to your problem)
def heuristic(node, dest):
    # example heuristic (Manhattan distance or any domain-specific heuristic)
    return abs(node - dest)

# bidirectional A* Search function
def bidir_astar_search(src, dest, graph):
    # priority queues for source and destination
    src_q, dest_q = [], []
    # set to track visited nodes for both sides
    src_vis, dest_vis = set(), set()
    # parent dictionaries to store the path
    src_par, dest_par = {}, {}
    # g-values (cost from start) and f-values (g + h)
    src_g, dest_g = {}, {}
    
    # initialize for source
    heapq.heappush(src_q, (0 + heuristic(src, dest), 0, src))  # (f, g, node)
    src_g[src] = 0
    src_par[src] = -1
    src_vis.add(src)
    
    # initialize for destination
    heapq.heappush(dest_q, (0 + heuristic(dest, src), 0, dest))  # (f, g, node)
    dest_g[dest] = 0
    dest_par[dest] = -1
    dest_vis.add(dest)

    while src_q and dest_q:
        # A* search from the source (here _ repre. Fst val. Which is f val)
        _, g_src, cur_src = heapq.heappop(src_q)
        for child, _ in graph[cur_src]:  # child is the node, _ is the cost (not needed here)
            tentative_g = g_src + 1  # assuming uniform cost, so the cost to move to child is 1
            if child not in src_g or tentative_g < src_g[child]:
                src_g[child] = tentative_g
                f_val = tentative_g + heuristic(child, dest)
                heapq.heappush(src_q, (f_val, tentative_g, child))
                src_par[child] = cur_src
                src_vis.add(child)

        # A* search from the destination
        _, g_dest, cur_dest = heapq.heappop(dest_q)
        for child, _ in graph[cur_dest]:  # child is the node, _ is the cost (not needed here)
            tentative_g = g_dest + 1  # assuming uniform cost
            if child not in dest_g or tentative_g < dest_g[child]:
                dest_g[child] = tentative_g
                f_val = tentative_g + heuristic(child, src)
                heapq.heappush(dest_q, (f_val, tentative_g, child))
                dest_par[child] = cur_dest
                dest_vis.add(child)

        # check for intersection of the search frontiers
        for node in src_vis:
            if node in dest_vis:
                return cons_path(node, src_par, dest_par)

    return []

def solve():
    # graph with node connections and costs as tuples (neighbor, cost)
    graph = {
        1: [(2, 1), (3, 2)],
        2: [(1, 1), (4, 3)],
        3: [(1, 2), (5, 4)],
        4: [(2, 3), (6, 2)],
        5: [(3, 4), (6, 1)],
        6: [(4, 2), (5, 1)]
    }

    src, dest = 1, 6
    ans = bidir_astar_search(src, dest, graph)

    if ans:
        print("Path:", " ".join(map(str, ans)))
        # calculate the total cost by summing the costs between consecutive nodes
        min_cost = 0
        for i in range(len(ans) - 1):
            # find the cost for this pair of nodes
            for neighbor, cost in graph[ans[i]]:
                if neighbor == ans[i+1]:
                    min_cost += cost
                    break
        print("Minimum Cost:", min_cost)
    else:
        print("No path")

if __name__ == "__main__":
    solve()