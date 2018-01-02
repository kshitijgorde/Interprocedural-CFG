import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.tree.MutableTreeNode;
import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

class FileNode implements Serializable
{
    protected File m_file;
    
    public FileNode(final File file) {
        this.m_file = file;
    }
    
    public File getFile() {
        return this.m_file;
    }
    
    public String toString() {
        return (this.m_file.getName().length() > 0) ? this.m_file.getName() : this.m_file.getPath();
    }
    
    public boolean expand(final DefaultMutableTreeNode defaultMutableTreeNode) {
        final DefaultMutableTreeNode defaultMutableTreeNode2 = (DefaultMutableTreeNode)defaultMutableTreeNode.getFirstChild();
        if (defaultMutableTreeNode2 == null) {
            return false;
        }
        if (!(defaultMutableTreeNode2.getUserObject() instanceof Boolean)) {
            return false;
        }
        defaultMutableTreeNode.removeAllChildren();
        final File[] listFiles = this.listFiles();
        if (listFiles == null) {
            return true;
        }
        final Vector vector = new Vector<FileNode>();
        for (int i = 0; i < listFiles.length; ++i) {
            final File file = listFiles[i];
            final FileNode fileNode = new FileNode(file);
            boolean b = false;
            if (file.isDirectory() || file.isFile()) {
                for (int j = 0; j < vector.size(); ++j) {
                    if (fileNode.compareTo(vector.elementAt(j)) < 0) {
                        vector.insertElementAt(fileNode, j);
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    vector.addElement(fileNode);
                }
            }
        }
        for (int k = 0; k < vector.size(); ++k) {
            final FileNode fileNode2 = vector.elementAt(k);
            final DefaultMutableTreeNode defaultMutableTreeNode3 = new DefaultMutableTreeNode(fileNode2);
            defaultMutableTreeNode.add(defaultMutableTreeNode3);
            if (fileNode2.hasSubDirs()) {
                defaultMutableTreeNode3.add(new DefaultMutableTreeNode(new Boolean(true)));
            }
        }
        return true;
    }
    
    public boolean hasSubDirs() {
        return this.listFiles() != null;
    }
    
    public int compareTo(final FileNode fileNode) {
        return this.m_file.getName().compareToIgnoreCase(fileNode.m_file.getName());
    }
    
    protected File[] listFiles() {
        if (!this.m_file.isDirectory()) {
            return null;
        }
        try {
            return this.m_file.listFiles();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error reading directory " + this.m_file.getAbsolutePath(), "Warning", 2);
            return null;
        }
    }
}
