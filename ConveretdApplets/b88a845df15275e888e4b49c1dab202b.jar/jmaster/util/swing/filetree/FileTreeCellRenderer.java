// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.filetree;

import java.awt.Graphics;
import java.io.File;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.ImageIcon;
import jmaster.util.swing.GUIHelper;
import javax.swing.Icon;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.DefaultTreeCellRenderer;

public class FileTreeCellRenderer extends DefaultTreeCellRenderer implements TreeCellRenderer, Icon
{
    private static final long C = 1819576905522102192L;
    public static final String PREFIX = "fileTreeCellRenderer";
    protected GUIHelper H;
    protected ImageIcon B;
    protected String F;
    protected boolean D;
    protected FileTreeNode G;
    protected Icon A;
    protected FileLengthIcon J;
    protected String E;
    protected boolean I;
    
    public FileTreeCellRenderer() {
        this.H = GUIHelper.getInstance();
        this.F = "Please wait...";
        this.J = new FileLengthIcon();
        this.I = true;
        this.H.injectProperties(this, "fileTreeCellRenderer");
        this.H.injectProperties(this.J, "fileTreeCellRenderer", "fileLengthIcon");
    }
    
    public ImageIcon getIconWait() {
        return this.B;
    }
    
    public void setIconWait(final ImageIcon b) {
        this.B = b;
    }
    
    public String getTextWait() {
        return this.F;
    }
    
    public void setTextWait(final String f) {
        this.F = f;
    }
    
    public boolean isRenderFileLength() {
        return this.I;
    }
    
    public void setRenderFileLength(final boolean i) {
        this.I = i;
    }
    
    public synchronized Component getTreeCellRendererComponent(final JTree tree, final Object o, final boolean b, final boolean b2, final boolean b3, final int n, final boolean b4) {
        super.getTreeCellRendererComponent(tree, o, b, b2, b3, n, b4);
        this.G = null;
        this.A = null;
        this.E = null;
        if (o instanceof FileTreeNode) {
            this.G = (FileTreeNode)o;
            final File file = this.G.getFile();
            try {
                this.setIcon(this.G.getIcon());
                this.setText(this.E = this.G.getText());
                if (!this.G.isDrive() && this.isRenderFileLength() && file.isFile()) {
                    this.setIcon(this);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.D = false;
        }
        else if (o instanceof FileTreePendingNode) {
            this.setIcon(this.B);
            this.setText(this.F);
            this.D = true;
        }
        return this;
    }
    
    public int getIconHeight() {
        return this.J.getIconHeight();
    }
    
    public int getIconWidth() {
        return (this.A == null) ? 0 : (this.A.getIconWidth() + this.J.getIconWidth());
    }
    
    public void paintIcon(final Component component, final Graphics graphics, int n, final int n2) {
        if (this.A != null) {
            this.A.paintIcon(component, graphics, n, n2);
            n += this.A.getIconWidth();
        }
        this.J.setFileLength((this.G == null) ? -1L : this.G.getFile().length());
        this.J.paintIcon(component, graphics, n, n2);
    }
}
