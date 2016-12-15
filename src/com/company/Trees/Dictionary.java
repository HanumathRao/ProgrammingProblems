package com.company.Trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hmaduri on 7/2/16.
 */
public class Dictionary {
    private TRIENode root;

    private static class TRIENode {
        private TRIENode a[] = new TRIENode[26];
        private String value;

        public void set(Character ch, TRIENode child, String Value) {
            this.a[ch-'a'] = child;
            this.value = Value;
        }

        public TRIENode() {
            this.a = new  TRIENode[26];
        }

        public TRIENode getChild(Character ch) {
            return this.a[ch-'a'];
        }

        public TRIENode setValue(String val) {
            this.value = val;
            return this;
        }

        public String getValue() {
            return this.value;
        }
    }

    public Dictionary() {
        this.root = new TRIENode();
    }

    public void prepareDictionary(BufferedReader in) throws IOException {
        String line;
        while((line = in.readLine()) != null) {
            this.insert(line);
        }
    }

    public void insert(String line) {
        line = line.toLowerCase();
        String[] strings = line.split(",");
        TRIENode node = root;
        for (Character ch : strings[0].toCharArray()) {
            TRIENode parent =node;
            node = node.getChild(ch);
            if (node == null){
                node = new TRIENode();
                parent.set(ch, node, null);
            }
        }
        node.setValue(strings[1]);
    }

    public String get(String key) {
        TRIENode node = root;
        for (Character ch: key.toCharArray())
            if (node != null)
                node = node.getChild(ch);
            else
                break;

        if (node == null || node.getValue() == null)
            return "not exists";
        else
            return node.getValue();
    }
}
