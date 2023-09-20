package leetcode.debug.binnarytree;

public class IsSameTree100 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode left1 = new TreeNode(1);
        TreeNode right1 = new TreeNode(2);
        root1.left=left1;
        root1.right=right1;
        
        TreeNode root2 = new TreeNode(1);
        TreeNode left2 = new TreeNode(2);
        TreeNode right2 = new TreeNode(1);
        root2.left=left2;
        root1.right=right2;


        Solution solution = new Solution();
        System.out.println(solution.isSameTree(root1,root2));
    }
}

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null){
            return true;
        }else if(p==null && q!=null || p!=null && q==null){
            return false;
        }else{
            //都不为空
            if(p.val!=q.val){
                return false;
            }else{
                return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
            }
        }
    }
}

