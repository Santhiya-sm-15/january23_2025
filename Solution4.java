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