// 
// Decompiled by Procyon v0.5.30
// 

class cuppatoc_tryRec
{
    public String id;
    public String name;
    public String tocstr;
    public String urlstr;
    public String head;
    public String targetframe;
    public int elemx;
    public int elemy;
    public int tailcount;
    public int headidx;
    public int level;
    public boolean visible;
    public boolean tailopen;
    public int[] tailidx;
    
    public cuppatoc_tryRec() {
        this.tailidx = new int[49];
        this.id = "";
        this.tocstr = "";
        this.tailcount = 0;
        this.urlstr = "";
        this.head = "head";
        this.headidx = -1;
        this.visible = false;
        this.level = 0;
    }
    
    void setxny(final int elemx, final int elemy) {
        this.elemx = elemx;
        this.elemy = elemy;
    }
}
