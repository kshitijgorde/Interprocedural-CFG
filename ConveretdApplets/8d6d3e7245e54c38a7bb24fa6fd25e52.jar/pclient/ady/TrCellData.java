// 
// Decompiled by Procyon v0.5.30
// 

package pclient.ady;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.Icon;
import java.awt.Image;

public class TrCellData
{
    public static int T_ROOT;
    public static int T_ROOM;
    public static int T_USER;
    public int type;
    public String name;
    public Image image;
    public Icon icon;
    public boolean dirty;
    
    public TrCellData() {
        this.type = 0;
        this.name = null;
        this.image = null;
        this.icon = null;
        this.dirty = true;
    }
    
    public String toString() {
        return this.name;
    }
    
    public int compareUserName(final String s) {
        return this.name.toLowerCase().compareTo(s.toLowerCase());
    }
    
    protected static DefaultMutableTreeNode createRoot(final String s) {
        final DefaultMutableTreeNode node = createNode(s);
        ((TrCellData)node.getUserObject()).type = TrCellData.T_ROOT;
        return node;
    }
    
    protected static DefaultMutableTreeNode createRoom(final String s) {
        final DefaultMutableTreeNode node = createNode(s);
        ((TrCellData)node.getUserObject()).type = TrCellData.T_ROOM;
        return node;
    }
    
    protected static DefaultMutableTreeNode createUser(final String s) {
        final DefaultMutableTreeNode node = createNode(s);
        ((TrCellData)node.getUserObject()).type = TrCellData.T_USER;
        return node;
    }
    
    private static DefaultMutableTreeNode createNode(final String name) {
        final TrCellData trCellData = new TrCellData();
        trCellData.name = name;
        return new DefaultMutableTreeNode(trCellData);
    }
    
    static {
        TrCellData.T_ROOT = 2;
        TrCellData.T_ROOM = 4;
        TrCellData.T_USER = 6;
    }
}
