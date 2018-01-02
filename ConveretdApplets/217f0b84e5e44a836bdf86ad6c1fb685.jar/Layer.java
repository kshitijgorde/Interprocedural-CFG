import java.awt.Graphics;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class Layer implements Visibility
{
    Vector Images;
    int visibility;
    double x;
    double y;
    double factorX;
    double factorY;
    double clipx;
    double clipy;
    double clipw;
    double cliph;
    int SearchResult;
    
    Layer() {
        this.Images = new Vector(1, 10);
        this.visibility = 3;
        this.x = 0.0;
        this.y = 0.0;
        this.factorX = 1.0;
        this.factorY = 1.0;
        this.clipx = 0.0;
        this.clipy = 0.0;
        this.clipw = 1.0;
        this.cliph = 1.0;
    }
    
    final LayerElement find(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        if (this.visibility == 0 || this.factorX < 0.001 || this.factorY < 0.001) {
            return null;
        }
        final double n7 = n + this.x * n3;
        final double n8 = n2 + this.y * n4;
        final double n9 = n3 * this.factorX;
        final double n10 = n4 * this.factorY;
        for (int i = this.Images.size() - 1; i >= 0; --i) {
            final LayerElement layerElement = this.Images.elementAt(i);
            if (layerElement.find(n7, n8, n9, n10, n5, n6)) {
                return layerElement;
            }
        }
        return null;
    }
    
    final LayerElement find(final String s, final int searchResult) {
        this.SearchResult = searchResult;
        LayerElement layerElement = null;
        if (this.visibility == 0) {
            return null;
        }
        for (int i = this.Images.size() - 1; i >= 0; --i) {
            final LayerElement layerElement2 = this.Images.elementAt(i);
            this.SearchResult = layerElement2.find(s, this.SearchResult);
            if (this.SearchResult >= 0) {
                layerElement = layerElement2;
            }
        }
        return layerElement;
    }
    
    final void render(final Graphics graphics, final double n, final double n2, final double n3, final double n4, final double n5) {
        if (this.visibility <= 1 || this.factorX < 0.001 || this.factorY < 0.001) {
            return;
        }
        try {
            graphics.clipRect((int)(this.clipx * n5), (int)(this.clipy * n5), (int)(this.clipw * n5), (int)(this.cliph * n5));
        }
        catch (Exception ex) {}
        final double n6 = n + this.x * n3;
        final double n7 = n2 + this.y * n4;
        final double n8 = n3 * this.factorX;
        final double n9 = n4 * this.factorY;
        for (int i = 0; i < this.Images.size(); ++i) {
            ((LayerElement)this.Images.elementAt(i)).render(graphics, n6, n7, n8, n9, this.visibility == 3);
        }
    }
}
