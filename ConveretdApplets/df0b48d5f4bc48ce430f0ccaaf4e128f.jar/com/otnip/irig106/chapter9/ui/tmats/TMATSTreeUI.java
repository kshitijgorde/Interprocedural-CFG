// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.ui.tmats;

import java.util.Iterator;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.DefaultTreeModel;
import com.otnip.irig106.chapter9.r.R;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Component;
import javax.swing.tree.TreeCellRenderer;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import com.otnip.irig106.chapter9.TMATS;
import javax.swing.JPanel;

public class TMATSTreeUI extends JPanel
{
    private static final long serialVersionUID = 0L;
    private TMATS tmats;
    private JTree tmatsTree;
    private JScrollPane tmatsTreeScrollPane;
    
    public TMATSTreeUI(final TMATS tmats) {
        this.tmats = tmats;
        this.initComponents();
        this.updateDisplay();
    }
    
    private void initComponents() {
        this.tmatsTreeScrollPane = new JScrollPane();
        this.tmatsTree = new JTree();
        this.setLayout(new BorderLayout());
        this.tmatsTree.setCellRenderer(new TMATSTreeCellRenderer());
        this.tmatsTree.setRowHeight(24);
        this.tmatsTreeScrollPane.setViewportView(this.tmatsTree);
        this.add(this.tmatsTreeScrollPane, "Center");
    }
    
    private void updateDisplay() {
        final DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new TMATSTreeField("TMATS", null, this.tmats, "/IconExperience/icons/24/plain/atom.png"));
        final DefaultMutableTreeNode gNode = TreeNodeGenerator.generateGNode(this.tmats.g, this.tmats);
        rootNode.add(gNode);
        for (final R r : this.tmats.rs) {
            rootNode.add(TreeNodeGenerator.generateRNode(r));
        }
        this.tmatsTree.setModel(new DefaultTreeModel(rootNode));
    }
}
