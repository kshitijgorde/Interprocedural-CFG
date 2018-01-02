// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.util;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyTree<T>
{
    private static final Pattern regex;
    private TreeNode<T> root;
    private TreeNode<T> node;
    
    static {
        regex = Pattern.compile(String.format("((%s)([+](%s))*\\s*[,])?\\s*(([^+, ]+)(\\s*[,]\\s*[^+, ]+)*)", "shift|control|option|command|alt|meta", "shift|control|option|command|alt|meta"));
    }
    
    public KeyTree() {
        this.root = new TreeNode<T>();
        this.node = this.root;
    }
    
    public boolean validKeySequence(final String s) {
        return KeyTree.regex.matcher(s).matches();
    }
    
    public void bind(final String s, final T t) {
        final Matcher matcher = KeyTree.regex.matcher(s);
        if (matcher.matches()) {
            final String group = matcher.group(1);
            final String[] array = null;
            String[] split;
            if (group != null) {
                split = group.substring(0, group.length() - 1).split("\\s*[+]\\s*");
            }
            else {
                split = new String[0];
            }
            this.bind(split, matcher.group(5).split("\\s*,\\s*"), t);
            return;
        }
        throw new RuntimeException("Unrecognized key sequence: " + s);
    }
    
    public void unbind(final String s) {
        final Matcher matcher = KeyTree.regex.matcher(s);
        if (matcher.matches()) {
            final String group = matcher.group(1);
            final String[] array = null;
            String[] split;
            if (group != null) {
                split = group.substring(0, group.length() - 1).split("\\s*[+]\\s*");
            }
            else {
                split = new String[0];
            }
            this.unbind(split, matcher.group(5).split("\\s*,\\s*"));
        }
    }
    
    private void bind(final String[] array, final String[] array2, final T t) {
        if (array.length < 2) {
            final ArrayList<Object> list = new ArrayList<Object>();
            list.addAll(Arrays.asList(array));
            list.addAll(Arrays.asList(array2));
            this.bind((List<String>)list, t);
        }
        else if (array.length == 2) {
            final ArrayList<Object> list2 = new ArrayList<Object>();
            list2.addAll(Arrays.asList(array));
            list2.addAll(Arrays.asList(array2));
            this.bind((List<String>)list2, t);
            Collections.swap(list2, 0, 1);
            this.bind((List<String>)list2, t);
        }
        else if (array.length == 3) {
            final ArrayList<Object> list3 = new ArrayList<Object>();
            list3.addAll(Arrays.asList(array));
            list3.addAll(Arrays.asList(array2));
            for (int i = 0; i < 3; ++i) {
                this.bind((List<String>)list3, t);
                Collections.swap(list3, 1, 2);
                this.bind((List<String>)list3, t);
                Collections.swap(list3, 0, 2);
            }
        }
    }
    
    private void unbind(final String[] array, final String[] array2) {
        if (array.length < 2) {
            final ArrayList<Object> list = new ArrayList<Object>();
            list.addAll(Arrays.asList(array));
            list.addAll(Arrays.asList(array2));
            this.unbind((List<String>)list);
        }
        else if (array.length == 2) {
            final ArrayList<Object> list2 = new ArrayList<Object>();
            list2.addAll(Arrays.asList(array));
            list2.addAll(Arrays.asList(array2));
            this.unbind((List<String>)list2);
            Collections.swap(list2, 0, 1);
            this.unbind((List<String>)list2);
        }
        else if (array.length == 3) {
            final ArrayList<Object> list3 = new ArrayList<Object>();
            list3.addAll(Arrays.asList(array));
            list3.addAll(Arrays.asList(array2));
            for (int i = 0; i < 3; ++i) {
                this.unbind((List<String>)list3);
                Collections.swap(list3, 1, 2);
                this.unbind((List<String>)list3);
                Collections.swap(list3, 0, 2);
            }
        }
    }
    
    private void bind(final List<String> list, final T binding) {
        Object root = this.root;
        for (final String s : list) {
            if (((TreeNode)root).children == null) {
                ((TreeNode)root).children = new HashMap<String, TreeNode<U>>();
            }
            TreeNode<Object> treeNode = ((TreeNode)root).children.get(s);
            if (treeNode == null) {
                treeNode = new TreeNode<Object>();
                TreeNode.access$0(treeNode, (TreeNode<Object>)root);
                TreeNode.access$1(treeNode, s);
                ((TreeNode)root).children.put(s, treeNode);
            }
            root = treeNode;
        }
        ((TreeNode)root).binding = (U)binding;
    }
    
    private void unbind(final List<String> list) {
        Object o = this.root;
        for (final String s : list) {
            if (((TreeNode)o).children == null) {
                return;
            }
            final TreeNode<Object> treeNode = ((TreeNode)o).children.get(s);
            if (treeNode == null) {
                return;
            }
            o = treeNode;
        }
        ((TreeNode)o).binding = null;
        while (o != null && (((TreeNode)o).children == null || ((TreeNode)o).children.size() == 0)) {
            if (((TreeNode<Object>)o).parent != null) {
                ((TreeNode<Object>)o).parent.children.remove(((TreeNode<Object>)o).key);
            }
            o = ((TreeNode<Object>)o).parent;
        }
    }
    
    public void reset() {
        this.node = this.root;
    }
    
    public T keyDown(final String s) {
        TreeNode<T> node = null;
        if (this.node.children != null) {
            node = this.node.children.get(s);
            if (node != null) {
                this.node = node;
            }
        }
        if (node != null) {
            return node.binding;
        }
        final TreeNode<T> node2 = new TreeNode<T>();
        TreeNode.access$1((TreeNode<Object>)node2, s);
        TreeNode.access$0((TreeNode<Object>)node2, (TreeNode<Object>)this.node);
        this.node = node2;
        return null;
    }
    
    public void keyUp(final String s) {
        Object o;
        for (o = this.node; o != this.root && !((TreeNode<Object>)o).key.equals(s); o = ((TreeNode<Object>)o).parent) {}
        if (o != this.root) {
            this.node = (TreeNode<T>)((TreeNode<Object>)o).parent;
        }
        else {
            this.node = this.root;
        }
    }
    
    class TreeNode<U>
    {
        private String key;
        private TreeNode<U> parent;
        public Map<String, TreeNode<U>> children;
        public U binding;
        
        static /* synthetic */ void access$0(final TreeNode treeNode, final TreeNode parent) {
            treeNode.parent = parent;
        }
        
        static /* synthetic */ void access$1(final TreeNode treeNode, final String key) {
            treeNode.key = key;
        }
    }
}
