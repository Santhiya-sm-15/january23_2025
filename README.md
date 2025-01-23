# january23_2025
The problems that I solved today

1.You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column. Return the number of servers that communicate with any other server.

Code:
class Solution {
    public int countServers(int[][] grid) {
        int i,j,n=grid.length,m=grid[0].length;
        int cnt=0;
        int[] row=new int[n];
        int[] col=new int[m];
        for(i=0;i<n;i++)
        {
            for(j=0;j<m;j++)
            {
                if(grid[i][j]==1)
                {
                    row[i]+=1;
                    col[j]+=1;
                }
            }
        }
        for(i=0;i<n;i++)
        {
            for(j=0;j<m;j++)
            {
                if(grid[i][j]==1 && (row[i]>1 || col[j]>1))
                    cnt++;
            }
        }
        return cnt;
    }
}

2.Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree. According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Code:
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root==p || root==q)
            return root;
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);
        if(left==null)
            return right;
        if(right==null)
            return left;
        return root;
    }
}

3.Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST. According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Code:
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)
            return null;
        if(root.val>p.val && root.val>q.val)
            return lowestCommonAncestor(root.left,p,q);
        if(root.val<p.val && root.val<q.val)
            return lowestCommonAncestor(root.right,p,q);
        return root;
    }
}

4.Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.

Code:
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        int n=nums.length;
        int x=n/2;
        TreeNode root=null;
        return f(0,n-1,root,nums);
    }
    public TreeNode f(int i,int j,TreeNode root,int[] nums)
    {
        if(i>j)
            return null;
        if(i==j)
            return new TreeNode(nums[i]);
        int mid=(i+j)/2;
        root=new TreeNode(nums[mid]);
        root.left=f(i,mid-1,root,nums);
        root.right=f(mid+1,j,root,nums);
        return root;
    }
}

5.There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai. For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1. Return true if you can finish all courses. Otherwise, return false.

Code:
class Graph
{
    LinkedList<Integer>[] adj;    
    int v;
    public Graph(int n)
    {
        adj=new LinkedList[n];
        this.v=n;
        for(int i=0;i<n;i++)
            adj[i]=new LinkedList<>();
    }
    public void addEdge(int src,int dest,int[] indegree)
    {
        adj[src].add(dest);
        indegree[dest]++;
    }
    public int f(int[] indegree,int n)
    {
        Queue<Integer> q=new LinkedList<>();
        int i;
        for(i=0;i<n;i++)
        {
            if(indegree[i]==0)
                q.add(i);
        }
        int cnt=0;
        while(!q.isEmpty())
        {
            int x=q.poll();
            cnt++;
            for(int a:adj[x])
            {
                indegree[a]--;
                if(indegree[a]==0)
                    q.add(a);
            }
        }
        return cnt;
    }
}
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree=new int[numCourses];
        Graph g=new Graph(numCourses);
        for(int[] x:prerequisites)
            g.addEdge(x[1],x[0],indegree);
        int cnt=g.f(indegree,numCourses);
        return cnt==numCourses;
    }
}

6.There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai. For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1. Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

Code:
class Graph
{
    LinkedList<Integer>[] adj;
    int v;
    public Graph(int n)
    {
        this.v=n;
        adj=new LinkedList[n];
        for(int i=0;i<n;i++)
            adj[i]=new LinkedList<>();
    }
    public void addEdge(int src,int dest,int[] indegree)
    {
        adj[src].add(dest);
        indegree[dest]++;
    }
    public boolean f(int[] indegree,int n,List<Integer> l)
    {
        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<n;i++)
        {
            if(indegree[i]==0)
                q.add(i);
        }
        while(!q.isEmpty())
        {
            int x=q.poll();
            l.add(x);
            for(int a:adj[x])
            {
                indegree[a]--;
                if(indegree[a]==0)
                    q.add(a);
            }
        }
        for(int i=0;i<n;i++)
        {
            if(indegree[i]!=0)  
                return false;
        }
        return true;
    }
}
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Graph g=new Graph(numCourses);
        int[] indegree=new int[numCourses];
        for(int[] x:prerequisites)
            g.addEdge(x[1],x[0],indegree);
        List<Integer> l=new ArrayList<>();
        if(!g.f(indegree,numCourses,l))
            return new int[0];
        int[] res=new int[l.size()];
        for(int i=0;i<l.size();i++) 
            res[i]=l.get(i);
        return res;
    }
}
