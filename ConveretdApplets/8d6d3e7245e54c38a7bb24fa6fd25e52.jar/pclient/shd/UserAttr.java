// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import java.awt.Color;

public class UserAttr
{
    public String avatar;
    public String status;
    public boolean busy;
    public boolean showVid;
    public boolean isMember;
    public Color foreground;
    public Color background;
    
    public UserAttr() {
        this.avatar = null;
        this.status = null;
        this.busy = false;
        this.showVid = false;
        this.isMember = false;
        this.foreground = null;
        this.background = null;
    }
    
    public String encodeStatus() {
        return " (" + this.status + ")";
    }
}
