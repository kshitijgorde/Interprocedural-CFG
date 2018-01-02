// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.tree;

import java.io.File;
import javax.swing.event.TreeSelectionEvent;
import jmaster.jumploader.view.api.file.tree.IFileTreeViewListener;
import jmaster.util.swing.GUIHelper;
import java.awt.Component;
import javax.swing.JScrollPane;
import jmaster.jumploader.model.api.B;
import javax.swing.event.TreeSelectionListener;
import jmaster.jumploader.view.api.file.tree.IFileTreeView;
import jmaster.jumploader.view.impl.GenericView;

public class FileTreeView extends GenericView implements IFileTreeView, TreeSelectionListener
{
    private static final long \u00f2 = 8268631162146549122L;
    public static final String PREFIX = "localFilesView";
    private FileTree \u00f1;
    
    public FileTreeView(final B b) {
        super(b);
        this.\u00f1 = new FileTree(b);
        this.A(this, "localFilesView", null);
        this.\u00f1.getSelectionModel().addTreeSelectionListener(this);
        this.add(new JScrollPane(this.\u00f1), this.A(this.I.newGBC(), 0, 0, 1, 1, 1, 1, 1, 18, GUIHelper.INSETS0));
    }
    
    public FileTree getTree() {
        return this.\u00f1;
    }
    
    public void setTree(final FileTree \u00f1) {
        this.\u00f1 = \u00f1;
    }
    
    private void G() {
        for (int i = 0; i < this.H.C(); ++i) {
            ((IFileTreeViewListener)this.H.A(i)).fileTreeViewPathChanged(this);
        }
    }
    
    public void valueChanged(final TreeSelectionEvent treeSelectionEvent) {
        this.G();
    }
    
    public void destroy() {
    }
    
    public File getPath() {
        return this.\u00f1.getSelectedFile();
    }
    
    public void setPath(final File file) {
        this.\u00f1.navigateTo(file);
    }
    
    public void updateView() {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.A(this);
            return;
        }
        this.\u00f1.getFileTreeCellRenderer().setRenderFileLength(this.F.H().isFileTreeViewShowFileLength());
        this.\u00f1.getFileTreeModel().reset();
        this.\u00f1.navigateTo(this.F.G().getPath());
    }
    
    public String getPathString() {
        final File path = this.getPath();
        return (path == null) ? null : path.getAbsolutePath();
    }
    
    public void setPathString(final String s) {
        this.setPath((s == null) ? null : new File(s));
    }
}
