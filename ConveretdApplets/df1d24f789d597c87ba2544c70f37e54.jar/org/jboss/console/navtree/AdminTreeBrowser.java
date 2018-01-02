// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.navtree;

import org.jboss.console.manager.interfaces.TreeAction;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import javax.swing.tree.TreePath;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import org.jboss.console.manager.interfaces.SimpleTreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.impl.SeparatorTreeNodeMenuEntry;
import javax.swing.JPopupMenu;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.awt.event.MouseAdapter;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeExpansionEvent;
import java.util.TreeSet;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.TreeModel;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;
import java.awt.event.MouseListener;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;

public class AdminTreeBrowser
{
    TreeContext ctx;
    ConsoleTreeModel tm;
    TreeCellRenderer cellRenderer;
    TreeSelectionListener selectionListener;
    OpenNodeAccounter openNodeAccounter;
    String webHost;
    public static final String RIGHT_FRAME_NAME = "right";
    private JTree tree;
    
    public AdminTreeBrowser(final TreeContext ctx) throws Exception {
        this.ctx = null;
        this.tm = null;
        this.cellRenderer = null;
        this.selectionListener = null;
        this.openNodeAccounter = null;
        this.webHost = null;
        this.ctx = ctx;
        this.tm = new ConsoleTreeModel(ctx);
        this.cellRenderer = new TreeCellRenderer(ctx);
        this.initComponents();
        this.openNodeAccounter = new OpenNodeAccounter(this.getTree());
        this.getTree().addMouseListener(new PopupMenuMgr());
        this.getTree().getSelectionModel().setSelectionMode(1);
        ToolTipManager.sharedInstance().registerComponent(this.getTree());
    }
    
    public void refreshTree(final boolean force) {
        try {
            this.tm.refreshTree(force);
        }
        catch (Exception displayed) {
            displayed.printStackTrace();
        }
    }
    
    private void initComponents() {
        (this.tree = new JTree()).setCellRenderer(this.getCellRenderer());
        this.tree.setModel(this.getTreeModel());
        this.tree.setAutoscrolls(true);
    }
    
    public TreeCellRenderer getCellRenderer() {
        return this.cellRenderer;
    }
    
    public TreeModel getTreeModel() {
        return this.tm;
    }
    
    public TreeSelectionListener getSelectionListener() {
        return this.selectionListener;
    }
    
    public JTree getTree() {
        return this.tree;
    }
    
    public class OpenNodeAccounter implements TreeExpansionListener, TreeModelListener
    {
        protected TreeSet openNodes;
        
        public OpenNodeAccounter(final JTree tree) {
            this.openNodes = new TreeSet();
            tree.getModel().addTreeModelListener(this);
        }
        
        public void treeExpanded(final TreeExpansionEvent event) {
            this.openNodes.add(((NodeWrapper)event.getSource()).getPath());
        }
        
        public void treeCollapsed(final TreeExpansionEvent event) {
            this.openNodes.remove(((NodeWrapper)event.getSource()).getPath());
        }
        
        public void treeNodesChanged(final TreeModelEvent e) {
        }
        
        public void recursivelyOpen(final NodeWrapper son) {
        }
        
        public void treeNodesInserted(final TreeModelEvent e) {
        }
        
        public void treeNodesRemoved(final TreeModelEvent e) {
        }
        
        public void treeStructureChanged(final TreeModelEvent e) {
        }
    }
    
    public class PopupMenuMgr extends MouseAdapter
    {
        HashMap menus;
        
        public PopupMenuMgr() {
            this.menus = new HashMap();
        }
        
        public void mousePressed(final MouseEvent e) {
            this.mouseReleased(e);
        }
        
        public void mouseReleased(final MouseEvent e) {
            final TreePath loc = AdminTreeBrowser.this.getTree().getPathForLocation(e.getX(), e.getY());
            if (loc == null) {
                return;
            }
            AdminTreeBrowser.this.getTree().setSelectionPath(loc);
            if (e.isPopupTrigger()) {
                final Object node = AdminTreeBrowser.this.getTree().getLastSelectedPathComponent();
                if (node == null) {
                    return;
                }
                JPopupMenu popup = null;
                if (this.menus.containsKey(node)) {
                    popup = this.menus.get(node);
                }
                else if (node instanceof NodeWrapper) {
                    final NodeWrapper who = (NodeWrapper)node;
                    final TreeNodeMenuEntry[] entries = who.getMenuEntries();
                    if (entries != null && entries.length > 0) {
                        popup = new JPopupMenu();
                        popup.setOpaque(true);
                        popup.setLightWeightPopupEnabled(true);
                        this.menus.put(node, popup);
                        for (int i = 0; i < entries.length; ++i) {
                            if (entries[i] instanceof SeparatorTreeNodeMenuEntry) {
                                popup.addSeparator();
                            }
                            else if (entries[i] instanceof SimpleTreeNodeMenuEntry) {
                                final SimpleTreeNodeMenuEntry txt = (SimpleTreeNodeMenuEntry)entries[i];
                                final JMenuItem mi = new JMenuItem(txt.getText());
                                mi.addActionListener(new ActionListener() {
                                    public void actionPerformed(final ActionEvent e) {
                                        AdminTreeBrowser.this.ctx.doPopupMenuAction(txt);
                                    }
                                });
                                popup.add(mi);
                            }
                        }
                    }
                }
                if (popup != null) {
                    popup.show((Component)e.getSource(), e.getX(), e.getY());
                }
            }
            else {
                final Object node = AdminTreeBrowser.this.getTree().getLastSelectedPathComponent();
                if (node != null && node instanceof NodeWrapper) {
                    final NodeWrapper who2 = (NodeWrapper)node;
                    final TreeAction act = who2.getAssociatedAction();
                    AdminTreeBrowser.this.ctx.doAdminTreeAction(act);
                }
            }
        }
    }
}
