// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.filetree;

import javax.swing.filechooser.FileSystemView;
import jmaster.util.C.B;
import javax.swing.tree.MutableTreeNode;
import javax.swing.Icon;
import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileTreeNode extends DefaultMutableTreeNode
{
    private static final long G = -363443472285782805L;
    protected File B;
    protected Icon D;
    protected String F;
    protected boolean A;
    protected boolean E;
    private boolean C;
    
    public FileTreeNode(final File file) {
        this.setFile(file);
        if (file != null && file.isDirectory()) {
            this.add(new FileTreePendingNode());
        }
        if (file != null) {
            if (jmaster.util.C.B.A()) {
                throw new RuntimeException("Initializing files in swing thread!");
            }
            final FileSystemView fileSystemView = FileSystemView.getFileSystemView();
            this.D = fileSystemView.getSystemIcon(file);
            this.F = fileSystemView.getSystemDisplayName(file);
            this.C = fileSystemView.isDrive(file);
        }
    }
    
    public boolean isExpanded() {
        return this.A;
    }
    
    public void setExpanded(final boolean a) {
        this.A = a;
    }
    
    public File getFile() {
        return this.B;
    }
    
    public void setFile(final File b) {
        this.B = b;
    }
    
    public boolean isProcessing() {
        return this.E;
    }
    
    public void setProcessing(final boolean e) {
        this.E = e;
    }
    
    public Icon getIcon() {
        return this.D;
    }
    
    public void setIcon(final Icon d) {
        this.D = d;
    }
    
    public String getText() {
        return this.F;
    }
    
    public void setText(final String f) {
        this.F = f;
    }
    
    public boolean isDrive() {
        return this.C;
    }
    
    public void setDrive(final boolean c) {
        this.C = c;
    }
    
    public String toString() {
        return (this.B == null) ? "null" : this.B.getAbsolutePath();
    }
}
