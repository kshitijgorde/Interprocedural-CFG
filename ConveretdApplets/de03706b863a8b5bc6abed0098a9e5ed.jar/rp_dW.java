import java.awt.Dimension;
import java.awt.Point;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_dW
{
    public int a;
    public int b;
    public int c;
    public int[] a;
    
    public rp_dW() {
        this.a = 0;
        this.b = -1;
    }
    
    public final void a(final rp_eg rp_eg) {
        final Vector<String> vector;
        (vector = new Vector<String>()).addElement(rp_eg.a("type", "predef"));
        rp_eg.a("roomshape", vector);
        vector.removeAllElements();
        vector.addElement(rp_eg.a("shape", Integer.toString(this.b)));
        vector.addElement(rp_eg.a("width", Integer.toString(this.c)));
        vector.addElement(rp_eg.a("orientation", Integer.toString(this.a)));
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.a.length; ++i) {
            if (i > 0) {
                sb.append(';');
            }
            sb.append(this.a[i]);
        }
        rp_eg.a("predef", vector, sb.toString());
        rp_eg.a();
    }
    
    public final rp_dt a(final rp_fx rp_fx, final boolean b) {
        final int c = this.c;
        final int[] a = this.a;
        final int c2 = this.c;
        Point[] array = null;
        switch (this.b) {
            case 0: {
                (array = new Point[4])[0] = new Point(0, 0);
                array[1] = new Point(a[0] + c2, 0);
                array[2] = new Point(a[0] + c2, a[1] + c2);
                array[3] = new Point(0, a[1] + c2);
                break;
            }
            case 1: {
                (array = new Point[6])[0] = new Point(0, 0);
                array[1] = new Point(a[0] + c2, 0);
                array[2] = new Point(a[0] + c2, a[1] - a[3]);
                array[3] = new Point(a[2] + c2, a[1] - a[3]);
                array[4] = new Point(a[2] + c2, a[1] + c2);
                array[5] = new Point(0, a[1] + c2);
                break;
            }
            case 2: {
                (array = new Point[8])[0] = new Point(0, 0);
                array[1] = new Point(a[0] + c2, 0);
                array[2] = new Point(a[0] + c2, a[5]);
                array[3] = new Point(a[2] - a[4], a[5]);
                array[4] = new Point(a[2] - a[4], a[1] - a[3]);
                array[5] = new Point(a[2] + c2, a[1] - a[3]);
                array[6] = new Point(a[2] + c2, a[1] + c2);
                array[7] = new Point(0, a[1] + c2);
                break;
            }
            case 3: {
                (array = new Point[8])[0] = new Point(0, 0);
                array[1] = new Point(a[0] + c2, 0);
                array[2] = new Point(a[0] + c2, a[5] + c2);
                array[3] = new Point(a[2] + a[3] + c2, a[5] + c2);
                array[4] = new Point(a[2] + a[3] + c2, a[4] + a[5] + c2);
                array[5] = new Point(a[2], a[4] + a[5] + c2);
                array[6] = new Point(a[2], a[1] + c2);
                array[7] = new Point(0, a[1] + c2);
                break;
            }
        }
        final Point[] array2 = array;
        final Dimension dimension = new Dimension(0, 0);
        for (int i = 0; i < array2.length; ++i) {
            dimension.width = Math.max(array2[i].x, dimension.width);
            dimension.height = Math.max(array2[i].y, dimension.height);
        }
        final Dimension dimension2 = new Dimension(0, 0);
        boolean b2 = false;
        if ((this.a & 0x1) != 0x0) {
            for (int j = 0; j < array2.length; ++j) {
                array2[j].x = -array2[j].x;
            }
            final Dimension dimension3 = dimension2;
            dimension3.width += dimension.width;
            b2 = true;
        }
        if ((this.a & 0x2) != 0x0) {
            for (int k = 0; k < array2.length; ++k) {
                array2[k].y = -array2[k].y;
            }
            final Dimension dimension4 = dimension2;
            dimension4.height += dimension.height;
            b2 = !b2;
        }
        if (b2) {
            new Point();
            for (int l = 0; l < array2.length / 2; ++l) {
                final Point point = array2[l];
                array2[l] = array2[array2.length - l - 1];
                array2[array2.length - l - 1] = point;
            }
        }
        final Dimension dimension5 = dimension2;
        dimension5.width += Math.max(0, (rp_fx.a().width - dimension.width) / 2);
        final Dimension dimension6 = dimension2;
        dimension6.height += Math.max(0, (rp_fx.a().height - dimension.height) / 2);
        for (int n = 0; n < array2.length; ++n) {
            array2[n].translate(dimension2.width, dimension2.height);
        }
        int n2 = 0;
        final rp_dC[] array3 = new rp_dC[array2.length];
        for (int n3 = 0; n3 < array2.length; ++n3) {
            final int n4 = (n3 == array2.length - 1) ? 0 : (n3 + 1);
            array3[n2++] = new rp_c(rp_fx.a.a(), rp_fx.a, array2[n3].x, array2[n3].y, array2[n4].x, array2[n4].y, c);
        }
        return new rp_fM(array3);
    }
}
