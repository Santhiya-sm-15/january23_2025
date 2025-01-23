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