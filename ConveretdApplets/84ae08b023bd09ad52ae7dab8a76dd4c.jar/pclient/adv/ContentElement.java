// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

public class ContentElement
{
    public static final int T_TEXT = 2;
    public static final int T_ICON = 4;
    public static final int T_LINK = 6;
    public int type;
    public String text;
    public String imageID;
    public String link;
    
    public ContentElement() {
        this.text = null;
        this.imageID = null;
        this.link = null;
        this.type = 2;
    }
    
    public String toString() {
        return this.type + " " + this.imageID + " " + this.text;
    }
}
