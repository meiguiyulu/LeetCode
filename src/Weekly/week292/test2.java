package Weekly.week292;

/**
 * @Author LiuYunJie
 * @Date 2022/5/23 9:55
 **/

/**
 * 给你一棵二叉树的根节点 root ，找出并返回满足要求的节点数，要求节点的值等于其 子树 中值的 平均值 。
 *
 * 注意：
 *
 * n 个元素的平均值可以由 n 个元素 求和 然后再除以 n ，并 向下舍入 到最近的整数。
 * root 的 子树 由 root 和它的所有后代组成。
 */
public class test2 {

    private int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        GetSumAnsNum(root);
        return ans;
    }

    public int[] GetSumAnsNum(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = GetSumAnsNum(root.left);
        int[] right = GetSumAnsNum(root.right);
        int sum = left[0] + right[0] + root.val;
        int num = left[1] + right[1] + 1;
        if (sum / num == root.val) {
            ++ans;
        }
        return new int[]{sum, num};
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
