// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.tree;

import jmaster.util.swing.GUIHelper;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class FileTreeMenu extends JPopupMenu
{
    private static final long C = 3443829470478133578L;
    private static final String A = "fileTreeMenu";
    protected JMenuItem B;
    
    public FileTreeMenu() {
        this.B = new JMenuItem();
        final GUIHelper instance = GUIHelper.getInstance();
        instance.injectProperties(this, "fileTreeMenu");
        instance.injectProperties(this.B, "fileTreeMenu", "itemRefresh");
        this.add(this.B);
    }
    
    public JMenuItem getItemRefresh() {
        return this.B;
    }
}
