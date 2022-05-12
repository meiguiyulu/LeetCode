package leetcode;

/**
 * @Author LiuYunjie
 * @Date 2022/5/11 18:13
 **/

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 449. 序列化和反序列化二叉搜索树
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        postOrder(root, buffer);
        return buffer.toString().substring(0, buffer.length());
    }

    /**
     * 先序遍历
     *
     * @param root
     * @param buffer
     */
    private void postOrder(TreeNode root, StringBuffer buffer) {
        if (root != null) {
            buffer.append(root.val);
            buffer.append(",");
            postOrder(root.left, buffer);
            postOrder(root.right, buffer);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] split = data.split(",");
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int length = split.length;
        for (int i = 0; i < length; i++) {
            stack.push(Integer.parseInt(split[i]));
        }
        return construct(stack, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode construct(Deque<Integer> deque, int minValue, int maxValue) {
        if (deque.isEmpty() || deque.peek() < minValue || deque.peek() > maxValue) {
            return null;
        }
        Integer pop = deque.pop();
        TreeNode treeNode = new TreeNode(pop);
        treeNode.left = construct(deque, minValue, pop);
        treeNode.right = construct(deque, pop, maxValue);
        return treeNode;
    }
}

