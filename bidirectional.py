from collections import deque

# fun to construct the path
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

# bidirectional search function
def bidir_search(src, dest, graph):
    src_q, dest_q = deque(), deque()
    src_vis, dest_vis = set(), set()
    src_par, dest_par = {}, {}

    src_q.append(src)
    dest_q.append(dest)
    src_vis.add(src)
    dest_vis.add(dest)
    src_par[src] = -1
    dest_par[dest] = -1

    while src_q and dest_q:
        # bfs from source
        cur_src = src_q.popleft()
        for child in graph[cur_src]:
            if child not in src_vis:
                src_q.append(child)
                src_vis.add(child)
                src_par[child] = cur_src

        # bfs from destination
        cur_dest = dest_q.popleft()
        for child in graph[cur_dest]:
            if child not in dest_vis:
                dest_q.append(child)
                dest_vis.add(child)
                dest_par[child] = cur_dest

        # check for intersection
        for node in src_vis:
            if node in dest_vis:
                return cons_path(node, src_par, dest_par)

    return []

def solve():
    graph = {
        1: [2, 3],
        2: [1, 4],
        3: [1, 5],
        4: [2, 6],
        5: [3, 6],
        6: [4, 5]
    }

    src, dest = 1, 6
    ans = bidir_search(src, dest, graph)

    if ans:
        print("Path:", " ".join(map(str, ans)))
    else:
        print("No path")

if __name__ == "__main__":
    solve()