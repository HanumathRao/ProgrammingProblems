package com.company;

/**
 * Created by hmaduri on 7/25/16.
 */

import com.sun.tools.javac.util.Pair;

import java.util.List;
import java.util.*;

public class Flatten {
    public static class TreeNode {
        private List<TreeNode> children = new ArrayList<>();
        private TreeNode sibling;

        public TreeNode(TreeNode brother, TreeNode... children) {
            this.sibling = brother;
            for(TreeNode child : children)
                this.children.add(child);
        }

        public List<TreeNode> getChildren() {
            return this.children;
        }
    }

    public static void main(String [] args) {
        //create the tree

        //TreeNode node =
    }

    public Pair<TreeNode,TreeNode> flatten(TreeNode root) {
        Pair<TreeNode,TreeNode> result = null;
        TreeNode head = null;
        TreeNode last = null;
        for (TreeNode child : root.getChildren()) {
            result = flatten(child);
            if (head == null)
                head = result.fst;
            else {
                TreeNode lastchld = result.snd;
                lastchld.sibling = head;
                head = result.fst;
            }

            if (last == null) {
                last = result.snd;
            }
        }

        result = flatten(root.sibling);
        if (head == null)
            head = result.fst;
        else {
            result.snd.sibling = head;
            head = result.fst;
        }

        if (last == null) {
            last = result.snd;
        }

        root.sibling = head;
        head = root;
        if (last == null)
            last = head;
        return Pair.of(head,last);
    }
}
