package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CodeC {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return rserialize(root,"");
    }

    private String rserialize(TreeNode root, String str) {
        if (root == null){
            str += "None,";
        }else {
            str += String.valueOf(root.val) + ",";
            str = rserialize(root.left,str);
            str = rserialize(root.right,str);
        }
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> dataList = new LinkedList<>(Arrays.asList(dataArray));
        return rdeserialize(dataList);
    }

    private TreeNode rdeserialize(List<String> dataList) {
        if (dataList.get(0).equals("None")){
            dataList.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
        dataList.remove(0);
        root.left = rdeserialize(dataList);
        root.right = rdeserialize(dataList);

        return root;
    }
}
