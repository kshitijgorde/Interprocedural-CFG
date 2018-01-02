// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.ui;

import javax.swing.event.TreeModelEvent;
import prefuse.data.Graph;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;
import prefuse.util.collections.CopyOnWriteArrayList;
import java.awt.Component;
import javax.swing.JScrollPane;
import prefuse.visual.VisualTree;
import javax.swing.JFrame;
import prefuse.util.StringLib;
import prefuse.data.Node;
import prefuse.data.event.GraphListener;
import javax.swing.tree.TreeModel;
import prefuse.data.Tree;
import javax.swing.JTree;

public class JPrefuseTree extends JTree
{
    private Tree m_tree;
    private String m_field;
    
    public JPrefuseTree(final Tree tree, final String field) {
        this.m_tree = tree;
        this.m_field = field;
        final PrefuseTreeModel model = new PrefuseTreeModel();
        super.setModel(model);
        this.m_tree.addGraphModelListener(model);
    }
    
    public Tree getTree() {
        return this.m_tree;
    }
    
    public String convertValueToText(final Object o, final boolean b, final boolean b2, final boolean b3, final int n, final boolean b4) {
        if (o == null) {
            return "";
        }
        if (!(o instanceof Node)) {
            return o.toString();
        }
        final Object value = ((Node)o).get(this.m_field);
        if (value.getClass().isArray()) {
            return StringLib.getArrayString(value);
        }
        return value.toString();
    }
    
    public static JFrame showTreeWindow(final Tree tree, final String s) {
        final JPrefuseTree prefuseTree = new JPrefuseTree(tree, s);
        String s2 = tree.toString();
        if (tree instanceof VisualTree) {
            s2 = ((VisualTree)tree).getGroup() + " " + s2;
        }
        final JFrame frame = new JFrame(s2);
        frame.getContentPane().add(new JScrollPane(prefuseTree));
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
    
    public class PrefuseTreeModel implements TreeModel, GraphListener
    {
        private CopyOnWriteArrayList m_listeners;
        
        public PrefuseTreeModel() {
            this.m_listeners = new CopyOnWriteArrayList();
        }
        
        public Object getRoot() {
            return JPrefuseTree.this.m_tree.getRoot();
        }
        
        public Object getChild(final Object o, final int n) {
            final Node child = ((Node)o).getChild(n);
            if (child == null) {
                throw new IllegalArgumentException("Index out of range: " + n);
            }
            return child;
        }
        
        public int getChildCount(final Object o) {
            return ((Node)o).getChildCount();
        }
        
        public boolean isLeaf(final Object o) {
            return ((Node)o).getChildCount() == 0;
        }
        
        public void valueForPathChanged(final TreePath treePath, final Object o) {
        }
        
        public int getIndexOfChild(final Object o, final Object o2) {
            return ((Node)o).getChildIndex((Node)o2);
        }
        
        public void addTreeModelListener(final TreeModelListener treeModelListener) {
            if (!this.m_listeners.contains(treeModelListener)) {
                this.m_listeners.add(treeModelListener);
            }
        }
        
        public void removeTreeModelListener(final TreeModelListener treeModelListener) {
            this.m_listeners.remove(treeModelListener);
        }
        
        public void graphChanged(final Graph graph, final String s, final int n, final int n2, final int n3, final int n4) {
            if (this.m_listeners == null || this.m_listeners.size() == 0) {
                return;
            }
            final boolean equals = s.equals(Graph.NODES);
            if (n4 != 0 && equals) {
                return;
            }
            if (n4 == 0 && !equals) {
                return;
            }
            for (int i = n; i <= n2; ++i) {
                Node node;
                if (equals) {
                    node = graph.getNode(i);
                }
                else {
                    node = graph.getEdge(i).getTargetNode();
                }
                final Object[] array = new Object[node.getDepth() + 1];
                int length = array.length;
                while (--length >= 0) {
                    array[length] = node;
                    node = node.getParent();
                }
                final TreeModelEvent treeModelEvent = new TreeModelEvent(this, array);
                final Object[] array2 = this.m_listeners.getArray();
                for (int j = 0; j < array2.length; ++j) {
                    final TreeModelListener treeModelListener = (TreeModelListener)array2[j];
                    switch (n4) {
                        case 1: {
                            treeModelListener.treeNodesInserted(treeModelEvent);
                            break;
                        }
                        case -1: {
                            treeModelListener.treeNodesRemoved(treeModelEvent);
                            break;
                        }
                        case 0: {
                            treeModelListener.treeNodesChanged(treeModelEvent);
                            break;
                        }
                    }
                }
            }
        }
    }
}
