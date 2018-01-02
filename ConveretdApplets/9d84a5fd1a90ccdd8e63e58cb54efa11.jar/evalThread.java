import netscape.javascript.JSObject;

// 
// Decompiled by Procyon v0.5.30
// 

class evalThread extends Thread
{
    JSObject twindow;
    double tx1;
    double tx2;
    double ty1;
    double ty2;
    boolean tdraw;
    String tname;
    
    public evalThread(final JSObject window, final String name, final double x1, final double y1, final double x2, final double y2, final boolean draw) {
        this.twindow = window;
        this.tname = name;
        this.tx1 = x1;
        this.ty1 = y1;
        this.tx2 = x2;
        this.ty2 = y2;
        this.tdraw = draw;
    }
    
    public void run() {
        this.twindow.eval("mapplet_apply('" + this.tname + "'," + Math.min(this.tx1, this.tx2) + "," + Math.min(this.ty1, this.ty2) + "," + Math.max(this.tx1, this.tx2) + "," + Math.max(this.ty1, this.ty2) + ", " + this.tdraw + ");");
    }
}
