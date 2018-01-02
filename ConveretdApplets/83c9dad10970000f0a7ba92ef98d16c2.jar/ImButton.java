import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Button;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ImButton extends Button
{
    int type;
    Polygon[] shapes;
    
    public ImButton() {
    }
    
    public ImButton(final int type) {
        this.type = type;
    }
    
    public void setType(final int type) {
        this.type = type;
    }
    
    public void clear() {
        this.shapes = null;
    }
    
    void initShapes() {
        this.shapes = new Polygon[40];
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final int n = width / 2;
        final int n2 = height / 2;
        final int n3 = (width < height) ? (n - 3) : (n2 - 3);
        final int[] array = { n + n3, n + n3, n - n3 };
        final int[] array2 = { n2 - n3, n2 + n3, n2 };
        this.shapes[0] = new Polygon(array, array2, 3);
        (this.shapes[1] = new Polygon(array, array2, 3)).translate(1, 1);
        final int[] array3 = { n - n3, n - n3, n + n3 };
        final int[] array4 = { n2 - n3, n2 + n3, n2 };
        this.shapes[2] = new Polygon(array3, array4, 3);
        (this.shapes[3] = new Polygon(array3, array4, 3)).translate(1, 1);
        final int[] array5 = { n + n3, n + n3, n, n, n - n3, n, n };
        final int[] array6 = { n2 + n3, n2 - n3, n2, n2 - n3, n2, n2 + n3, n2 };
        this.shapes[4] = new Polygon(array5, array6, 7);
        (this.shapes[5] = new Polygon(array5, array6, 7)).translate(1, 1);
        final int[] array7 = { n - n3, n - n3, n, n, n + n3, n, n };
        final int[] array8 = { n2 + n3, n2 - n3, n2, n2 - n3, n2, n2 + n3, n2 };
        this.shapes[6] = new Polygon(array7, array8, 7);
        (this.shapes[7] = new Polygon(array7, array8, 7)).translate(1, 1);
        final int[] array9 = { n - n3, n - n3 * 3 / 4, n - n3 * 3 / 4, n - n3 };
        final int[] array10 = { n2 - n3, n2 - n3, n2 + n3, n2 + n3 };
        this.shapes[8] = new Polygon(array9, array10, 4);
        (this.shapes[9] = new Polygon(array9, array10, 4)).translate(1, 1);
        final int[] array11 = { n + n3, n + n3 * 3 / 4, n + n3 * 3 / 4, n + n3 };
        final int[] array12 = { n2 - n3, n2 - n3, n2 + n3, n2 + n3 };
        this.shapes[10] = new Polygon(array11, array12, 4);
        (this.shapes[11] = new Polygon(array11, array12, 4)).translate(1, 1);
        final int[] array13 = { n - n3, n - n3, n - n3 / 3, n - n3 / 3 };
        final int[] array14 = { n2 - n3, n2 + n3, n2 + n3, n2 - n3 };
        this.shapes[12] = new Polygon(array13, array14, 4);
        (this.shapes[13] = new Polygon(array13, array14, 4)).translate(1, 1);
        final int[] array15 = { n + n3, n + n3, n + n3 / 3, n + n3 / 3 };
        final int[] array16 = { n2 - n3, n2 + n3, n2 + n3, n2 - n3 };
        this.shapes[14] = new Polygon(array15, array16, 4);
        (this.shapes[15] = new Polygon(array15, array16, 4)).translate(1, 1);
        final int[] array17 = { n - n3 * 2 / 3, n - n3 * 2 / 3, n + n3 * 2 / 3, n + n3 * 2 / 3 };
        final int[] array18 = { n2 - n3 * 2 / 3, n2 + n3 * 2 / 3, n2 + n3 * 2 / 3, n2 - n3 * 2 / 3 };
        this.shapes[16] = new Polygon(array17, array18, 4);
        (this.shapes[17] = new Polygon(array17, array18, 4)).translate(1, 1);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.shapes == null) {
            this.initShapes();
        }
        if (!this.isEnabled()) {
            graphics.setColor(new Color(255, 255, 255));
            this.drawShape(graphics, 1);
            graphics.setColor(new Color(128, 128, 128));
        }
        else {
            graphics.setColor(this.getForeground());
        }
        this.drawShape(graphics, 0);
    }
    
    public void drawShape(final Graphics graphics, final int n) {
        switch (this.type) {
            case 0: {
                graphics.fillPolygon(this.shapes[n]);
            }
            case 1: {
                graphics.fillPolygon(this.shapes[2 + n]);
            }
            case 2: {
                graphics.fillPolygon(this.shapes[4 + n]);
            }
            case 3: {
                graphics.fillPolygon(this.shapes[6 + n]);
            }
            case 4: {
                graphics.fillPolygon(this.shapes[n]);
                graphics.fillPolygon(this.shapes[8 + n]);
            }
            case 5: {
                graphics.fillPolygon(this.shapes[2 + n]);
                graphics.fillPolygon(this.shapes[10 + n]);
            }
            case 6: {
                graphics.fillPolygon(this.shapes[4 + n]);
                graphics.fillPolygon(this.shapes[8 + n]);
            }
            case 7: {
                graphics.fillPolygon(this.shapes[6 + n]);
                graphics.fillPolygon(this.shapes[10 + n]);
            }
            case 8: {
                graphics.fillPolygon(this.shapes[12 + n]);
                graphics.fillPolygon(this.shapes[14 + n]);
            }
            case 9: {
                graphics.fillPolygon(this.shapes[16 + n]);
            }
            default: {}
        }
    }
}
