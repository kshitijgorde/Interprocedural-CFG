// 
// Decompiled by Procyon v0.5.30
// 

class item
{
    boolean dedans;
    String lib;
    int y;
    String url;
    
    item() {
        this.dedans = false;
        this.y = 0;
        this.lib = "__rien";
        this.url = "";
    }
    
    item(final int y, final String lib, final String url) {
        this.dedans = false;
        this.y = y;
        this.lib = lib;
        this.url = url;
    }
}
