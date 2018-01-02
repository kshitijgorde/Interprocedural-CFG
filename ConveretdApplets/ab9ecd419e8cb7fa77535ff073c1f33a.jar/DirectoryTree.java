import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.tree.TreePath;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.event.MouseListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.MutableTreeNode;
import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JTree;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class DirectoryTree extends JPanel
{
    protected JTree m_tree;
    protected DefaultTreeModel m_model;
    protected JTextField m_display;
    protected Vector images;
    private String dateiString;
    
    public synchronized String getFileString() {
        return this.dateiString;
    }
    
    public synchronized void setFileString(final String dateiString) {
        this.dateiString = dateiString;
    }
    
    public DirectoryTree() {
        this.dateiString = null;
        this.images = new Vector();
        final DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode("My Computer");
        final File[] listRoots = File.listRoots();
        for (int i = 0; i < listRoots.length; ++i) {
            final DefaultMutableTreeNode defaultMutableTreeNode2 = new DefaultMutableTreeNode(new FileNode(listRoots[i]));
            defaultMutableTreeNode.add(defaultMutableTreeNode2);
            defaultMutableTreeNode2.add(new DefaultMutableTreeNode(new Boolean(true)));
        }
        this.m_model = new DefaultTreeModel(defaultMutableTreeNode);
        (this.m_tree = new JTree(this.m_model)).setScrollsOnExpand(true);
        this.m_tree.getSelectionModel().setSelectionMode(1);
        this.m_tree.putClientProperty("JTree.lineStyle", "Angled");
        this.m_tree.setCellRenderer(new IconCellRenderer());
        this.m_tree.addTreeExpansionListener(new DirExpansionListener());
        this.m_tree.addTreeSelectionListener(new SelectListener(this));
        this.m_tree.addMouseListener(new ClickListener(this));
        this.m_tree.setShowsRootHandles(true);
        this.m_tree.setEditable(false);
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(365, 250));
        scrollPane.getViewport().add(this.m_tree);
        this.add(scrollPane, "Center");
        (this.m_display = new JTextField()).setEditable(false);
        this.setVisible(true);
    }
    
    DefaultMutableTreeNode getTreeNode(final TreePath treePath) {
        return (DefaultMutableTreeNode)treePath.getLastPathComponent();
    }
    
    FileNode getFileNode(final DefaultMutableTreeNode defaultMutableTreeNode) {
        if (defaultMutableTreeNode == null) {
            return null;
        }
        Object o = defaultMutableTreeNode.getUserObject();
        if (o instanceof IconData) {
            o = ((IconData)o).getObject();
        }
        if (o instanceof FileNode) {
            return (FileNode)o;
        }
        return null;
    }
    
    class DirExpansionListener implements TreeExpansionListener
    {
        private final /* synthetic */ DirectoryTree this$0;
        
        public void treeExpanded(final TreeExpansionEvent treeExpansionEvent) {
            final DefaultMutableTreeNode treeNode = DirectoryTree.this.getTreeNode(treeExpansionEvent.getPath());
            new Thread() {
                private final /* synthetic */ FileNode val$fnode = DirectoryTree.this.getFileNode(treeNode);
                private final /* synthetic */ DirExpansionListener this$1 = this$1;
                
                public void run() {
                    if (this.val$fnode != null && this.val$fnode.expand(treeNode)) {
                        SwingUtilities.invokeLater(new Runnable() {
                            private final /* synthetic */ DirectoryTree$1 this$2 = this$2;
                            
                            public void run() {
                                this.this$2.this$1.this$0.m_model.reload(treeNode);
                            }
                        });
                    }
                }
            }.start();
        }
        
        public void treeCollapsed(final TreeExpansionEvent treeExpansionEvent) {
        }
    }
}
