// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.awt.Point;
import java.awt.image.WritableRaster;
import java.awt.image.Raster;
import java.util.Iterator;
import java.util.Enumeration;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.image.ColorModel;
import java.awt.image.SampleModel;
import java.awt.image.RenderedImage;

public abstract class a implements RenderedImage
{
    protected int D;
    protected int C;
    protected int B;
    protected int L;
    protected int J;
    protected int F;
    protected int E;
    protected int K;
    protected SampleModel I;
    protected ColorModel H;
    protected Vector A;
    protected Hashtable G;
    
    public a() {
        this.E = 0;
        this.K = 0;
        this.I = null;
        this.H = null;
        this.A = new Vector();
        this.G = new Hashtable();
    }
    
    public int getMinX() {
        return this.D;
    }
    
    public final int B() {
        return this.getMinX() + this.getWidth();
    }
    
    public int getMinY() {
        return this.C;
    }
    
    public final int D() {
        return this.getMinY() + this.getHeight();
    }
    
    public int getWidth() {
        return this.B;
    }
    
    public int getHeight() {
        return this.L;
    }
    
    public Rectangle A() {
        return new Rectangle(this.getMinX(), this.getMinY(), this.getWidth(), this.getHeight());
    }
    
    public int getTileWidth() {
        return this.J;
    }
    
    public int getTileHeight() {
        return this.F;
    }
    
    public int getTileGridXOffset() {
        return this.E;
    }
    
    public int getTileGridYOffset() {
        return this.K;
    }
    
    public int getMinTileX() {
        return this.A(this.getMinX());
    }
    
    public int C() {
        return this.A(this.B() - 1);
    }
    
    public int getNumXTiles() {
        return this.C() - this.getMinTileX() + 1;
    }
    
    public int getMinTileY() {
        return this.D(this.getMinY());
    }
    
    public int E() {
        return this.D(this.D() - 1);
    }
    
    public int getNumYTiles() {
        return this.E() - this.getMinTileY() + 1;
    }
    
    public SampleModel getSampleModel() {
        return this.I;
    }
    
    public ColorModel getColorModel() {
        return this.H;
    }
    
    public Object getProperty(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final Object value = this.G.get(lowerCase);
        return (value != null) ? value : Image.UndefinedProperty;
    }
    
    public String[] getPropertyNames() {
        String[] array = null;
        if (this.G.size() > 0) {
            array = new String[this.G.size()];
            int n = 0;
            final Enumeration<String> keys = this.G.keys();
            while (keys.hasMoreElements()) {
                array[n++] = keys.nextElement();
            }
        }
        return array;
    }
    
    public String[] A(String lowerCase) {
        final String[] propertyNames = this.getPropertyNames();
        if (propertyNames == null) {
            return null;
        }
        lowerCase = lowerCase.toLowerCase();
        final Vector vector = new Vector<String>();
        for (int i = 0; i < propertyNames.length; ++i) {
            if (propertyNames[i].startsWith(lowerCase)) {
                vector.addElement(propertyNames[i]);
            }
        }
        if (vector.size() == 0) {
            return null;
        }
        final String[] array = new String[vector.size()];
        int n = 0;
        final Iterator<String> iterator = vector.iterator();
        while (iterator.hasNext()) {
            array[n++] = iterator.next();
        }
        return array;
    }
    
    public static int D(int n, final int n2, final int n3) {
        n -= n2;
        if (n < 0) {
            n += 1 - n3;
        }
        return n / n3;
    }
    
    public static int B(int n, final int n2, final int n3) {
        n -= n2;
        if (n < 0) {
            n += 1 - n3;
        }
        return n / n3;
    }
    
    public int A(final int n) {
        return D(n, this.getTileGridXOffset(), this.getTileWidth());
    }
    
    public int D(final int n) {
        return B(n, this.getTileGridYOffset(), this.getTileHeight());
    }
    
    public static int A(final int n, final int n2, final int n3) {
        return n * n3 + n2;
    }
    
    public static int C(final int n, final int n2, final int n3) {
        return n * n3 + n2;
    }
    
    public int B(final int n) {
        return n * this.J + this.E;
    }
    
    public int C(final int n) {
        return n * this.F + this.K;
    }
    
    public Vector getSources() {
        return null;
    }
    
    public Raster getData() {
        return this.getData(new Rectangle(this.getMinX(), this.getMinY(), this.getWidth(), this.getHeight()));
    }
    
    public Raster getData(Rectangle rectangle) {
        final Rectangle a = this.A();
        if (rectangle == null) {
            rectangle = a;
        }
        else if (!rectangle.intersects(a)) {
            throw new IllegalArgumentException(m.A("SimpleRenderedImage0"));
        }
        int n = this.A(rectangle.x);
        int n2 = this.D(rectangle.y);
        int n3 = this.A(rectangle.x + rectangle.width - 1);
        int n4 = this.D(rectangle.y + rectangle.height - 1);
        if (n == n3 && n2 == n4) {
            return this.getTile(n, n2).createChild(rectangle.x, rectangle.y, rectangle.width, rectangle.height, rectangle.x, rectangle.y, null);
        }
        if (!a.contains(rectangle)) {
            final Rectangle intersection = rectangle.intersection(a);
            n = this.A(intersection.x);
            n2 = this.D(intersection.y);
            n3 = this.A(intersection.x + intersection.width - 1);
            n4 = this.D(intersection.y + intersection.height - 1);
        }
        final WritableRaster a2 = U.A(this.I.createCompatibleSampleModel(rectangle.width, rectangle.height), rectangle.getLocation());
        for (int i = n2; i <= n4; ++i) {
            for (int j = n; j <= n3; ++j) {
                final Raster tile = this.getTile(j, i);
                tile.getBounds();
                final Rectangle intersection2 = rectangle.intersection(tile.getBounds());
                a2.setRect(tile.createChild(intersection2.x, intersection2.y, intersection2.width, intersection2.height, intersection2.x, intersection2.y, null));
            }
        }
        return a2;
    }
    
    public WritableRaster copyData(WritableRaster a) {
        final Rectangle a2 = this.A();
        Rectangle bounds;
        if (a == null) {
            bounds = a2;
            a = U.A(this.I.createCompatibleSampleModel(this.B, this.L), new Point(this.D, this.C));
        }
        else {
            bounds = a.getBounds();
        }
        final Rectangle rectangle = a2.contains(bounds) ? bounds : bounds.intersection(a2);
        final int a3 = this.A(rectangle.x);
        final int d = this.D(rectangle.y);
        final int a4 = this.A(rectangle.x + rectangle.width - 1);
        for (int d2 = this.D(rectangle.y + rectangle.height - 1), i = d; i <= d2; ++i) {
            for (int j = a3; j <= a4; ++j) {
                final Raster tile = this.getTile(j, i);
                tile.getBounds();
                final Rectangle intersection = bounds.intersection(tile.getBounds());
                a.setRect(tile.createChild(intersection.x, intersection.y, intersection.width, intersection.height, intersection.x, intersection.y, null));
            }
        }
        return a;
    }
}
