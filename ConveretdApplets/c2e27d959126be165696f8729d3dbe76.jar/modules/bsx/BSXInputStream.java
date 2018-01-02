// 
// Decompiled by Procyon v0.5.30
// 

package modules.bsx;

import java.awt.Polygon;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;

public class BSXInputStream extends DataInputStream
{
    public BSXInputStream(final InputStream in) {
        super(in);
    }
    
    public int readASCIIHex() {
        int h;
        int l;
        try {
            h = this.readByte();
            l = this.readByte();
        }
        catch (Exception ex) {
            return 0;
        }
        h = h - 48 - ((h > 57) ? 7 : 0);
        l = l - 48 - ((l > 57) ? 7 : 0);
        return 16 * h + l;
    }
    
    public final BSXGraphic readBSXGraphic() throws IOException {
        int ap = this.readASCIIHex();
        final BSXGraphic picture = new BSXGraphic(ap);
        while (ap > 0) {
            picture.addPolygon(this.readBSXPolygon());
            --ap;
        }
        return picture;
    }
    
    public final BSXPolygon readBSXPolygon() throws IOException {
        final BSXPolygon p = new BSXPolygon();
        int points = this.readASCIIHex();
        final int color = this.readASCIIHex();
        p.setColor(color);
        while (points > 0) {
            final int x = this.readASCIIHex();
            final int y = this.readASCIIHex();
            ((Polygon)p).addPoint(x, y);
            --points;
        }
        return p;
    }
}
