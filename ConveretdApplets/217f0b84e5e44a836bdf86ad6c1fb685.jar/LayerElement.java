import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class LayerElement implements Visibility
{
    CgmContext cgm;
    int visibility;
    double x;
    double y;
    double factorX;
    double factorY;
    String id;
    String clonename;
    int SearchResult;
    
    LayerElement(final CgmContext cgm, final String id) {
        this.visibility = 3;
        this.x = 0.0;
        this.y = 0.0;
        this.factorX = 1.0;
        this.factorY = 1.0;
        this.id = "";
        this.clonename = "";
        this.cgm = cgm;
        this.id = id;
    }
    
    public final boolean equals(final Object o) {
        return this.cgm == ((LayerElement)o).cgm && this.id.equals(((LayerElement)o).id);
    }
    
    final boolean find(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        if (this.visibility == 0 || this.factorX < 0.001 || this.factorY < 0.001) {
            return false;
        }
        this.cgm.finishReading();
        if (this.cgm.find((n5 - (n + this.x * n3)) / (this.factorX * n3), (n6 - (n2 + this.y * n4)) / (this.factorY * n4)) != null) {
            this.clonename = String.valueOf(this.cgm.filename) + this.id;
            this.SearchResult = this.cgm.SearchResult;
            return true;
        }
        return false;
    }
    
    final int find(final String s, final int n) {
        if (this.visibility == 0) {
            return n;
        }
        this.cgm.finishReading();
        if (this.cgm.find(s, n) != null) {
            this.clonename = String.valueOf(this.cgm.filename) + this.id;
            return this.SearchResult = this.cgm.SearchResult;
        }
        return n;
    }
    
    final void render(final Graphics graphics, final double n, final double n2, final double n3, final double n4, final boolean b) {
        if (this.visibility <= 1 || this.factorX < 0.001 || this.factorY < 0.001) {
            return;
        }
        this.cgm.finishReading();
        final int n5 = (int)(n + this.x * n3);
        final int n6 = (int)(n2 + this.y * n4);
        graphics.translate(n5, n6);
        this.cgm.render(graphics, this.factorX * n3, this.factorY * n4, this.visibility == 3 & b);
        graphics.translate(-n5, -n6);
    }
    
    final void replaceText(final int n, final String s) {
        this.cgm.finishReading();
        this.cgm.replaceText(n, s);
    }
}
