package com.company.Trees;

/**
 * Created by hmaduri on 7/2/16.
 */
public class BuildTreeFromInorderPreOrder {

    public static void main(String[] args) {
        Character inorder[] = {'D', 'B', 'E', 'A', 'F', 'C'};
        Character preorder[] = {'A', 'B', 'D','E','C','F'};

        Tree tree = buildTree(preorder, inorder, preorder.length, 0, 0);
        printTree(tree,"");
    }

    public static void printTree(Tree tree, String indent) {
        if (tree==null)
            return;
        System.out.println(indent+tree.data);
        printTree(tree.getlChild(), indent+"****");
        printTree(tree.getrChild(), indent+"****");
    }

    public static Tree buildTree(Character preorder[], Character inorder[], int length,
                   int startpreorder, int startinorder) {
        if (length==0)
            return null;
        Tree root = new Tree();
        int l = search(preorder, inorder, startpreorder, startinorder, startinorder+length);
        root.setKey(preorder[startpreorder]);
        root.left = buildTree(preorder, inorder, l, startpreorder+1, startinorder);
        root.right = buildTree(preorder, inorder, length-(l+1), startpreorder+l+1, startinorder+l+1);
        return root;
    }


    private static int search(Character preorder[], Character inorder[], int startpreorder, int startinorder, int length) {
        Character ch = preorder[startpreorder];
        int lindex=0;
        for (int index=startinorder;index<length;index++) {
            if (inorder[index] == ch) break;
            lindex++;
        }
        return lindex;
    }


    private static class Tree {
        private Character data;
        private Tree left;
        private Tree right;

        Tree() {
        }

        public void setKey(Character data) {
            this.data = data;
        }

        public void setlChild(Tree tree) {
            this.left = tree;
        }

        public void setrChild(Tree tree){
            this.right = tree;
        }

        public Tree getrChild(){
            return this.right;
        }

        public Tree getlChild() {
            return this.left;
        }
    }
}
