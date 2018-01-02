import java.awt.Color;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.util.StringTokenizer;
import java.awt.Point;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class b2 extends Component
{
    String _flddo;
    String[] a;
    Rectangle _fldif;
    
    public b2(final Container container, final String flddo, final Point point) {
        container.add(this, 0);
        this._flddo = flddo;
        final StringTokenizer stringTokenizer = new StringTokenizer(flddo, "\n");
        final int countTokens = stringTokenizer.countTokens();
        this.a = new String[countTokens];
        for (int i = 0; i < countTokens; ++i) {
            this.a[i] = stringTokenizer.nextToken();
        }
        final FontMetrics fontMetrics = this.getGraphics().getFontMetrics();
        final int height = fontMetrics.getHeight();
        int n = 0;
        for (int j = 0; j < this.a.length; ++j) {
            final int stringWidth = fontMetrics.stringWidth(this.a[j]);
            if (stringWidth > n) {
                n = stringWidth;
            }
        }
        this._fldif = new Rectangle(point.x + 15, point.y + 15, n + 8 + 2, this.a.length * height + 8 + 3);
        final Dimension size = this.getParent().getSize();
        final Dimension dimension = new Dimension(size.width - 60, size.height - 15);
        if (this._fldif.x + this._fldif.width > dimension.width) {
            this._fldif.x = dimension.width - this._fldif.width;
        }
        if (this._fldif.y + this._fldif.height > dimension.height) {
            this._fldif.y = dimension.height - this._fldif.height - 1;
        }
        if (this._fldif.contains(point)) {
            this._fldif.x = point.x - this._fldif.width - 15;
        }
        this.paint(this.getParent().getGraphics());
    }
    
    public void a() {
        this.getParent().remove(this);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRoundRect(this._fldif.x + 2, this._fldif.y + 3, this._fldif.width - 2, this._fldif.height - 3, 10, 10);
        graphics.fillRoundRect(this._fldif.x, this._fldif.y, this._fldif.width - 2, this._fldif.height - 3, 10, 10);
        graphics.setColor(new Color(255, 255, 224));
        graphics.fillRoundRect(this._fldif.x + 1, this._fldif.y + 1, this._fldif.width - 2 - 2, this._fldif.height - 2 - 3, 10, 10);
        graphics.setColor(Color.black);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final int ascent = fontMetrics.getAscent();
        final int n = this._fldif.x + 4;
        for (int n2 = this._fldif.y + ascent + (this._fldif.height - this.a.length * height) / 2, i = 0; i < this.a.length; ++i, n2 += height) {
            graphics.drawString(this.a[i], n, n2);
        }
    }
}
