// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import javax.swing.Icon;

public class CellData
{
    public String username;
    public boolean dirty;
    public Icon icon;
    
    public CellData() {
        this.username = null;
        this.dirty = true;
        this.icon = null;
    }
    
    public int compare(final CellData cellData) {
        return this.username.toLowerCase().compareTo(cellData.username.toLowerCase());
    }
    
    public String toString() {
        return "uname=" + this.username + ",dirty=" + this.dirty + ",icon=" + this.icon;
    }
}
