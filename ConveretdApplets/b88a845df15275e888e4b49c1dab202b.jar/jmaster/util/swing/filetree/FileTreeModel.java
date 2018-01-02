// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.filetree;

import java.util.Arrays;
import java.util.ArrayList;
import javax.swing.filechooser.FileSystemView;
import jmaster.util.C.B;
import javax.swing.SwingUtilities;
import jmaster.util.swing.SwingUtil;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.Comparator;
import java.io.FileFilter;
import javax.swing.tree.TreeModel;
import javax.swing.tree.DefaultTreeModel;

public class FileTreeModel extends DefaultTreeModel implements TreeModel
{
    private static final long E = -5923991880231569068L;
    protected FileFilter C;
    protected Comparator D;
    protected File[] B;
    protected FileTreeNode[] A;
    static /* synthetic */ Class class$jmaster$util$swing$filetree$FileTreeNode;
    
    public FileTreeModel() {
        super(new DefaultMutableTreeNode());
        this.D = new DefaultFileComparator();
    }
    
    public FileFilter getFileFilter() {
        return this.C;
    }
    
    public void setFileFilter(final FileFilter c) {
        this.C = c;
    }
    
    public Comparator getFileComparator() {
        return this.D;
    }
    
    public void setFileComparator(final Comparator d) {
        this.D = d;
    }
    
    public void init(final File[] b) {
        this.B = b;
        final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)this.getRoot();
        if (b != null) {
            this.A = new FileTreeNode[b.length];
            for (int i = 0; i < b.length; ++i) {
                defaultMutableTreeNode.add(this.A[i] = new FileTreeNode(b[i]));
            }
        }
        SwingUtil.reloadTreeNode(this, defaultMutableTreeNode);
    }
    
    public void expandNode(final FileTreeNode fileTreeNode) {
        try {
            if (!fileTreeNode.isExpanded()) {
                if (SwingUtilities.isEventDispatchThread()) {
                    jmaster.util.C.B.B(this, "expandNode", new Class[] { (FileTreeModel.class$jmaster$util$swing$filetree$FileTreeNode == null) ? (FileTreeModel.class$jmaster$util$swing$filetree$FileTreeNode = class$("jmaster.util.swing.filetree.FileTreeNode")) : FileTreeModel.class$jmaster$util$swing$filetree$FileTreeNode }, new Object[] { fileTreeNode });
                    return;
                }
                if (fileTreeNode.isExpanded()) {
                    return;
                }
                fileTreeNode.setProcessing(true);
                File[] files = FileSystemView.getFileSystemView().getFiles(fileTreeNode.getFile(), false);
                if (files != null && this.C != null) {
                    final ArrayList list = new ArrayList<Object>(files.length);
                    for (int n = 0; files != null && n < files.length; ++n) {
                        if (this.C.accept(files[n])) {
                            list.add(files[n]);
                        }
                    }
                    files = new File[list.size()];
                    for (int i = 0; i < list.size(); ++i) {
                        files[i] = (File)list.get(i);
                    }
                }
                if (this.D != null) {
                    Arrays.sort(files, this.D);
                }
                for (int n2 = 0; files != null && n2 < files.length; ++n2) {
                    fileTreeNode.add(new FileTreeNode(files[n2]));
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            fileTreeNode.setProcessing(false);
            fileTreeNode.setExpanded(true);
            if (fileTreeNode.getChildCount() > 0 && fileTreeNode.getChildAt(0) instanceof FileTreePendingNode) {
                fileTreeNode.remove(0);
            }
            SwingUtil.reloadTreeNode(this, fileTreeNode);
        }
    }
    
    public FileTreeNode[] getRootNodes() {
        return this.A;
    }
    
    public void reset() {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.C(this, "reset");
            return;
        }
        final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)this.getRoot();
        defaultMutableTreeNode.removeAllChildren();
        if (this.B != null) {
            this.A = new FileTreeNode[this.B.length];
            for (int i = 0; i < this.B.length; ++i) {
                defaultMutableTreeNode.add(this.A[i] = new FileTreeNode(this.B[i]));
            }
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
