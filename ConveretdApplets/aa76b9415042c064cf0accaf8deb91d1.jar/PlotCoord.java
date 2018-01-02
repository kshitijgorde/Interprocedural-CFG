// 
// Decompiled by Procyon v0.5.30
// 

class PlotCoord
{
    float xstretch;
    float ystretch;
    float xshift;
    float yshift;
    float ywholeScreen;
    int inset;
    
    PlotCoord(final float n, final float n2, final float n3, final float n4, final int n5, final int n6, final int inset) {
        this.xstretch = 1.0f;
        this.ystretch = 1.0f;
        this.inset = inset;
        this.xstretch = (n5 - 2 * this.inset) / (n - n3);
        this.ystretch = (n6 - 2 * this.inset) / (n2 - n4);
        this.xshift = -n3;
        this.yshift = -n4;
        this.ywholeScreen = n2 - n4;
    }
    
    int xcoord(final float n) {
        return Math.round((n + this.xshift) * this.xstretch + this.inset);
    }
    
    int ycoord(final float n) {
        return Math.round((this.ywholeScreen - (n + this.yshift)) * this.ystretch + this.inset);
    }
}
