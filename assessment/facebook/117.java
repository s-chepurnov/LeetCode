class Solution {
    public Node connect(Node root) {
        if(root == null) return root;
        Queue<Node> bfs_queue = new LinkedList<>();
        bfs_queue.add(root);
        while(!bfs_queue.isEmpty()){
            int size = bfs_queue.size();
            for(int i = 0; i < size; i++){
                Node top = bfs_queue.poll();
                if(i < size - 1){
                    top.next = bfs_queue.peek();
                }
                if(top.left != null) bfs_queue.add(top.left);
                if(top.right != null) bfs_queue.add(top.right);
            }
        }
        return root;
    }
