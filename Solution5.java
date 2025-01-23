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