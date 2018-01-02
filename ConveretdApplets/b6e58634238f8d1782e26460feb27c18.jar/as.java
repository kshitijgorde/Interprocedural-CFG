import java.awt.Point;
import java.text.FieldPosition;
import java.util.StringTokenizer;
import java.text.DecimalFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public final class as extends DecimalFormat
{
    public d a;
    public int b;
    public double c;
    public DecimalFormat d;
    public int e;
    public double f;
    public DecimalFormat g;
    
    public as(final d a, final String s) {
        Math.log(10.0);
        this.a = a;
        final boolean b = true;
        this.e = (b ? 1 : 0);
        this.b = (b ? 1 : 0);
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        if (stringTokenizer.hasMoreTokens()) {
            try {
                final Integer c = this.a.d().c(stringTokenizer.nextToken().substring(1));
                if (c > 0) {
                    this.b = c;
                }
            }
            catch (Exception ex) {}
        }
        if (stringTokenizer.hasMoreTokens()) {
            try {
                final Integer c2 = this.a.d().c(stringTokenizer.nextToken().substring(1));
                if (c2 > 0) {
                    this.e = c2;
                }
            }
            catch (Exception ex2) {}
        }
        else {
            this.e = this.b;
        }
        double n;
        int i;
        for (n = 1.0 / this.b % 1.0, i = 0; n > 0.0; n = n * 10.0 % 1.0, ++i) {}
        this.c = Math.pow(10.0, i);
        final StringBuffer sb = new StringBuffer(i + 2);
        sb.append("0");
        while (i > 0) {
            if (sb.length() <= 1) {
                sb.append('.');
            }
            sb.append('#');
            --i;
        }
        this.d = new DecimalFormat(sb.toString());
        double n2;
        int j;
        for (n2 = 1.0 / this.e % 1.0, j = 0; n2 > 0.0; n2 = n2 * 10.0 % 1.0, ++j) {}
        this.f = Math.pow(10.0, j);
        final StringBuffer sb2 = new StringBuffer(j + 2);
        sb2.append("0");
        while (j > 0) {
            if (sb2.length() <= 1) {
                sb2.append('.');
            }
            sb2.append('#');
            --j;
        }
        this.g = new DecimalFormat(sb2.toString());
    }
    
    public StringBuffer format(final long n, final StringBuffer sb, final FieldPosition fieldPosition) {
        return this.format((double)n, sb, fieldPosition);
    }
    
    public StringBuffer format(double n, final StringBuffer sb, final FieldPosition fieldPosition) {
        final boolean b;
        if (b = (n < 0.0)) {
            n = -n;
        }
        final double n2 = b ? this.f : this.c;
        n = (Math.floor(n2 * n) + ((n2 * n % 1.0 >= 0.5) ? 1.0 : 0.0)) / n2;
        final int n3 = b ? this.e : this.b;
        final Point point = new Point((int)Math.floor(n * n3), n3);
        if (b) {
            sb.append(this.getDecimalFormatSymbols().getMinusSign());
        }
        if (n * n3 - point.x <= 0.0) {
            final int n4 = point.x / point.y;
            point.x -= n4 * point.y;
            this.a(point);
            if (n4 != 0 || point.x == 0) {
                sb.append(n4);
                if (point.x != 0) {
                    sb.append(' ');
                }
            }
            if (point.x != 0) {
                sb.append(point.x);
                sb.append('/');
                sb.append(point.y);
            }
        }
        else {
            sb.append((b ? this.g : this.d).format(n));
        }
        return sb;
    }
    
    private void a(final Point point) {
        final Point point2 = new Point(point.x, point.y);
        while (point2.x != 0) {
            final int x = point2.x;
            point2.x = point2.y % point2.x;
            point2.y = x;
        }
        point.x /= point2.y;
        point.y /= point2.y;
    }
}
