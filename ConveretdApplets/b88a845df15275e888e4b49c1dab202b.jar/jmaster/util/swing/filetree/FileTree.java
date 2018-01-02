// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.filetree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeExpansionEvent;
import java.util.ArrayList;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.filechooser.FileSystemView;
import java.util.Vector;
import jmaster.util.C.B;
import javax.swing.tree.TreeCellRenderer;
import java.io.File;
import javax.swing.tree.TreeModel;
import javax.swing.event.TreeExpansionListener;
import javax.swing.JTree;

public class FileTree extends JTree implements TreeExpansionListener
{
    private static final long U = -2947248786804745544L;
    protected FileTreeModel S;
    protected boolean T;
    static /* synthetic */ Class class$java$io$File;
    
    public FileTree() {
        this.setModel(this.S = new FileTreeModel());
    }
    
    protected void A(final File[] array) {
        try {
            this.S.init(array);
            this.setCellRenderer(new FileTreeCellRenderer());
            this.setShowsRootHandles(true);
            this.setRootVisible(false);
            this.addTreeExpansionListener(this);
        }
        finally {
            this.T = true;
        }
    }
    
    public FileTreeModel getFileTreeModel() {
        return this.S;
    }
    
    public FileTreeCellRenderer getFileTreeCellRenderer() {
        return (FileTreeCellRenderer)this.getCellRenderer();
    }
    
    public void navigateTo(File file) {
        if (B.A()) {
            B.B(this, "navigateTo", new Class[] { (FileTree.class$java$io$File == null) ? (FileTree.class$java$io$File = class$("java.io.File")) : FileTree.class$java$io$File }, new Object[] { file });
            return;
        }
        while (!this.T) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
        final FileTreeNode[] rootNodes = this.S.getRootNodes();
        FileTreeNode fileTreeNode = null;
        final Vector vector = new Vector<File>();
        File parentFile = file;
        do {
            vector.insertElementAt(parentFile, 0);
            for (int i = 0; i < rootNodes.length; ++i) {
                if (parentFile.equals(rootNodes[i].getFile())) {
                    fileTreeNode = rootNodes[i];
                }
            }
        } while (fileTreeNode == null && (parentFile = parentFile.getParentFile()) != null);
        if (fileTreeNode == null) {
            vector.removeAllElements();
            final FileSystemView fileSystemView = FileSystemView.getFileSystemView();
            File file2 = fileSystemView.createFileObject(file.getPath());
            while (file2 != null && fileTreeNode == null) {
                vector.insertElementAt(file2, 0);
                for (int j = 0; j < rootNodes.length; ++j) {
                    if (file2.equals(rootNodes[j].getFile())) {
                        fileTreeNode = rootNodes[j];
                    }
                    file2 = fileSystemView.getParentDirectory(file2);
                }
            }
            if (fileTreeNode == null) {
                return;
            }
        }
        final Object[] array = new Object[vector.size() + 1];
        Object o = fileTreeNode;
        array[0] = this.S.getRoot();
        array[1] = o;
        for (int k = 1; k < array.length - 1; ++k) {
            file = vector.elementAt(k);
            if (!((FileTreeNode)o).isExpanded()) {
                this.S.expandNode((FileTreeNode)o);
            }
            FileTreeNode fileTreeNode2 = null;
            for (int l = 0; l < this.S.getChildCount(o); ++l) {
                final FileTreeNode fileTreeNode3 = (FileTreeNode)this.S.getChild(o, l);
                if (file.equals(fileTreeNode3.getFile())) {
                    fileTreeNode2 = fileTreeNode3;
                    break;
                }
            }
            if (fileTreeNode2 == null) {
                ((DefaultMutableTreeNode)o).insert(fileTreeNode2 = new FileTreeNode(file), 0);
                this.S.reload((TreeNode)o);
                this.S.expandNode(fileTreeNode2);
            }
            o = (array[k + 1] = fileTreeNode2);
        }
        final TreePath selectionPath = new TreePath(array);
        this.expandPath(selectionPath);
        this.setSelectionPath(selectionPath);
        this.makeVisible(new TreePath(new Object[] { array[array.length - 1] }));
    }
    
    public synchronized void refresh() {
        final TreePath selectionPath = this.getSelectionPath();
        if (selectionPath != null && selectionPath.getPathCount() > 1) {
            final FileTreeNode fileTreeNode = (FileTreeNode)selectionPath.getPath()[1];
            while (fileTreeNode.getChildCount() > 0) {
                this.S.removeNodeFromParent((MutableTreeNode)fileTreeNode.getLastChild());
            }
            fileTreeNode.setExpanded(false);
            fileTreeNode.add(new FileTreePendingNode());
            this.navigateTo(((FileTreeNode)selectionPath.getLastPathComponent()).getFile());
        }
    }
    
    public File getSelectedFile() {
        File file = null;
        final TreePath selectionPath = this.getSelectionPath();
        if (selectionPath != null) {
            final Object lastPathComponent = selectionPath.getLastPathComponent();
            if (lastPathComponent instanceof FileTreeNode) {
                file = ((FileTreeNode)lastPathComponent).getFile();
            }
        }
        return file;
    }
    
    public File[] getSelectedFiles() {
        File[] array = null;
        final TreePath[] selectionPaths = this.getSelectionPaths();
        if (selectionPaths != null) {
            final ArrayList<File> list = new ArrayList<File>();
            for (int i = 0; i < selectionPaths.length; ++i) {
                final Object lastPathComponent = selectionPaths[i].getLastPathComponent();
                if (lastPathComponent instanceof FileTreeNode) {
                    list.add(((FileTreeNode)lastPathComponent).getFile());
                }
            }
            array = new File[list.size()];
            for (int j = 0; j < list.size(); ++j) {
                array[j] = (File)list.get(j);
            }
        }
        return array;
    }
    
    protected void A(final FileTreeNode fileTreeNode) {
        if (!fileTreeNode.isExpanded() && !fileTreeNode.isProcessing()) {
            final Thread thread = new Thread(new Runnable() {
                public void run() {
                    FileTree.this.S.expandNode(fileTreeNode);
                }
            });
            thread.setName("FileTreeExpander_" + fileTreeNode.getFile());
            thread.start();
        }
    }
    
    public void treeCollapsed(final TreeExpansionEvent treeExpansionEvent) {
    }
    
    public void treeExpanded(final TreeExpansionEvent treeExpansionEvent) {
        final Object lastPathComponent = treeExpansionEvent.getPath().getLastPathComponent();
        if (lastPathComponent != null && lastPathComponent instanceof FileTreeNode) {
            this.A((FileTreeNode)lastPathComponent);
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
