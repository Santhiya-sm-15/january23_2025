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