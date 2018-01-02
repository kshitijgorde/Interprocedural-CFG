import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class basictoc2Rec
{
    public String id;
    public String name;
    public String helpstr;
    public String urlstr;
    public String head;
    public String altstr;
    public String target;
    public Image defimg;
    public Image altimg;
    public Image img;
    public int elemx;
    public int elemy;
    public int level;
    public int itemh;
    public boolean isopen;
    public boolean ishead;
    public boolean isvisible;
    
    public basictoc2Rec() {
        this.id = "";
        this.name = "";
        this.helpstr = "";
        this.urlstr = "";
        this.head = "";
        this.altstr = "";
        this.target = "";
        this.level = 0;
        this.itemh = 0;
    }
    
    public basictoc2Rec(final String helpstr, final String urlstr, final String altstr) {
        this.helpstr = helpstr;
        this.urlstr = urlstr;
        this.altstr = altstr;
    }
    
    void setxny(final int elemx, final int elemy) {
        this.elemx = elemx;
        this.elemy = elemy;
    }
}
