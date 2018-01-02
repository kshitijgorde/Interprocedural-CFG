// 
// Decompiled by Procyon v0.5.30
// 

public class OLink
{
    public double x;
    public double y;
    public String string;
    
    public OLink() {
        this.string = null;
    }
    
    public OLink(final double x, final double y, final String s) {
        this.string = null;
        this.x = x;
        this.y = y;
        this.string = new String(s);
    }
}
