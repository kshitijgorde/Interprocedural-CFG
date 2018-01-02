// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.awt.Cursor;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Date;
import java.awt.Panel;

public final class Calender extends Panel
{
    private int a;
    private int b;
    private Date c;
    private Date d;
    private Rectangle e;
    private Rectangle f;
    private Rectangle g;
    private int h;
    private int i;
    private g j;
    String[] k;
    String[] l;
    private static String[] z;
    
    protected Calender() {
        this.c = new Date();
        this.d = null;
        this.e = new Rectangle(0, 0, 0, 0);
        this.f = new Rectangle(0, 0, 0, 0);
        this.g = new Rectangle(0, 0, 0, 0);
        this.h = 1;
        this.i = 1;
        this.j = null;
        this.k = new String[] { Calender.z[19], Calender.z[5], Calender.z[2], Calender.z[10], Calender.z[12], Calender.z[8], Calender.z[9] };
        this.l = new String[] { Calender.z[18], Calender.z[0], Calender.z[3], Calender.z[6], Calender.z[15], Calender.z[13], Calender.z[14], Calender.z[4], Calender.z[1], Calender.z[17], Calender.z[7], Calender.z[11] };
        this.setBackground(Color.white);
        this.enableEvents(48L);
        this.setFont(new Font(Calender.z[16], 0, 11));
        this.a((Date)null);
    }
    
    protected void a(final g j) {
        this.j = j;
    }
    
    protected void a(final String s) {
        final int a = RotationImageFilter.a;
        if (s.length() < 1) {
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",", false);
        int n = 0;
        while (stringTokenizer.hasMoreTokens() && n < 7) {
            this.k[n++] = stringTokenizer.nextToken();
            if (a != 0) {
                break;
            }
        }
    }
    
    protected void b(final String s) {
        final int a = RotationImageFilter.a;
        if (s.length() < 1) {
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",", false);
        int n = 0;
        while (stringTokenizer.hasMoreTokens() && n < 12) {
            this.l[n++] = stringTokenizer.nextToken();
            if (a != 0) {
                break;
            }
        }
    }
    
    protected Date a() {
        return this.d;
    }
    
    protected void a(final Date d) {
        Label_0027: {
            if (d == null) {
                this.d = new Date();
                if (RotationImageFilter.a == 0) {
                    break Label_0027;
                }
            }
            this.d = d;
        }
        this.a = this.d.getMonth();
        this.b = this.d.getYear() + 1900;
    }
    
    public void doLayout() {
        super.doLayout();
        final Rectangle bounds = this.getParent().getBounds();
        final Rectangle bounds2 = this.getBounds();
        final int ascent = this.getGraphics().getFontMetrics().getAscent();
        final int n = ascent + 4;
        this.e = new Rectangle(0, 0, bounds2.width - 1, n);
        this.f = new Rectangle(this.e.x, this.e.y + this.e.height, this.e.width, ascent + 4);
        final int a = this.a(this.a + 1, this.b);
        this.g = new Rectangle(this.f.x, this.f.y + this.f.height, this.f.width, n * a);
        this.h = this.g.width / 7;
        this.i = this.g.height / a;
        bounds2.height = this.e.height + this.f.height + this.g.height + 4;
        if (RotationImageFilter.a == 0) {
            if (bounds2.y + bounds2.height > bounds.y + bounds.height) {
                bounds2.y = bounds.height - bounds2.height + 1;
            }
            this.setBounds(bounds2);
        }
    }
    
    public void paint(final Graphics graphics) {
        final int a = RotationImageFilter.a;
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        fontMetrics.getAscent();
        final String concat = String.valueOf(String.valueOf(this.l[this.a]).concat(String.valueOf(" "))).concat(String.valueOf(String.valueOf(this.b)));
        graphics.setColor(Color.black);
        graphics.drawRect(this.e.x, this.e.y, this.e.width, this.e.height);
        graphics.drawRect(this.f.x, this.f.y, this.f.width, this.f.height);
        graphics.drawRect(this.g.x, this.g.y, this.g.width, this.g.height);
        graphics.setColor(Color.blue);
        graphics.fillRect(this.e.x + 1, this.e.y + 1, this.e.width - 1, this.e.height - 1);
        graphics.setColor(new Color(180, 230, 250));
        graphics.fillRect(this.f.x + 1, this.f.y + 1, this.f.width - 1, this.f.height - 1);
        graphics.setColor(new Color(223, 250, 255));
        graphics.fillRect(this.g.x + 1, this.g.y + 1, this.g.width - 1, this.g.height - 1);
        graphics.setColor(Color.black);
        graphics.setColor(Color.white);
        final int[] array = { 3, 10, 10 };
        final int[] array2 = { 8, 14, 2 };
        final int[] array3 = { 0, 0, 7 };
        final int[] array4 = { 2, 14, 8 };
        graphics.fillPolygon(array, array2, 3);
        final int[] array5 = array3;
        final int n = 0;
        array5[n] += this.e.width - 10;
        final int[] array6 = array3;
        final int n2 = 1;
        array6[n2] += this.e.width - 10;
        final int[] array7 = array3;
        final int n3 = 2;
        array7[n3] += this.e.width - 10;
        graphics.fillPolygon(array3, array4, 3);
        graphics.drawString(concat, this.e.x + (this.e.width - fontMetrics.stringWidth(concat)) / 2, this.e.y + this.e.height - 2);
        graphics.setColor(Color.black);
        this.a(this.a + 1, this.b);
        int n4 = this.f.x;
        int i = 0;
        while (true) {
            while (i < 7) {
                graphics.drawString(this.k[i], n4 + (this.h - fontMetrics.stringWidth(this.k[i])) / 2, this.f.y + this.f.height - 2);
                n4 += this.h;
                if (a == 0) {
                    final int n5 = i;
                    final int n6 = 6;
                    if (a != 0) {
                        Label_0798: {
                            while (true) {
                                if (n5 < n6) {
                                    n4 += this.i;
                                    graphics.drawLine(this.g.x, n4, this.g.x + this.g.width, n4);
                                    ++i;
                                    if (a != 0) {
                                        break Label_0798;
                                    }
                                    if (a == 0) {
                                        continue;
                                    }
                                }
                                break;
                            }
                            n4 = this.b(this.a + 1, this.b);
                            i = t.a(this.a + 1, this.b);
                        }
                        final int x = this.g.x;
                        int y = this.g.y;
                        int x2 = x + n4 * this.h;
                        int n7 = n4;
                        int j = 1;
                        while (j <= i) {
                            final String value = String.valueOf(j);
                            int n9;
                            int b;
                            final int n8 = b = (n9 = this.a);
                            int month;
                            int n11;
                            final int n10 = n11 = (month = this.d.getMonth());
                            Label_1068: {
                                final int n12;
                                final int n13;
                                final int n14;
                                final int date;
                                Label_0975: {
                                    if (a == 0) {
                                        if (n8 == n10) {
                                            n12 = (n9 = this.b);
                                            n13 = (month = this.d.getYear() + 1900);
                                            if (a != 0) {
                                                break Label_0975;
                                            }
                                            if (n12 == n13) {
                                                n14 = j;
                                                date = this.d.getDate();
                                                if (a != 0) {
                                                    break Label_0975;
                                                }
                                                if (n14 == date) {
                                                    graphics.setColor(new Color(150, 220, 250));
                                                    graphics.fillRect(x2 + 1, y + 1, this.h - 1, this.i - 1);
                                                    graphics.setColor(Color.black);
                                                    if (a == 0) {
                                                        break Label_1068;
                                                    }
                                                }
                                            }
                                        }
                                        final int a2 = this.a;
                                        this.c.getMonth();
                                    }
                                }
                                Label_1061: {
                                    if (a == 0) {
                                        if (n8 != n10) {
                                            break Label_1061;
                                        }
                                        b = this.b;
                                        n11 = this.c.getYear() + 1900;
                                    }
                                    if (a == 0) {
                                        if (n12 != n13) {
                                            break Label_1061;
                                        }
                                        this.c.getDate();
                                    }
                                    if (n14 == date) {
                                        graphics.setColor(Color.yellow);
                                        graphics.fillRect(x2 + 1, y + 1, this.h - 1, this.i - 1);
                                        graphics.setColor(Color.black);
                                        if (a == 0) {
                                            break Label_1068;
                                        }
                                    }
                                }
                                graphics.setColor(Color.black);
                            }
                            graphics.drawString(value, x2 + (this.h - fontMetrics.stringWidth(value)) / 2, y + this.i - 2);
                            final int n15 = ++n7;
                            final int n16 = 6;
                            Label_1149: {
                                if (a == 0) {
                                    if (n15 > n16) {
                                        n7 = 0;
                                        x2 = this.f.x;
                                        y += this.i;
                                        if (a == 0) {
                                            break Label_1149;
                                        }
                                    }
                                    final int h = this.h;
                                }
                                x2 = n15 + n16;
                            }
                            ++j;
                            if (a != 0) {
                                break;
                            }
                        }
                        return;
                    }
                    if (n5 < n6) {
                        graphics.drawLine(n4, this.f.y, n4, this.g.y + this.g.height);
                    }
                    ++i;
                }
                if (a != 0) {
                    int p = ItemApplet.P;
                    ItemApplet.P = ++p;
                    break;
                }
            }
            n4 = this.g.y;
            i = 0;
            continue;
        }
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        final int a = RotationImageFilter.a;
        super.processMouseEvent(mouseEvent);
        final int id = mouseEvent.getID();
        Label_0057: {
            if (a == 0) {
                switch (id) {
                    case 501: {
                        mouseEvent.getClickCount();
                        break;
                    }
                    case 502: {
                        break Label_0057;
                    }
                }
            }
            if (id > 0) {}
            if (a == 0) {
                return;
            }
        }
        int n4;
        int n3;
        int n2;
        final int n = n2 = (n3 = (n4 = mouseEvent.getClickCount()));
        int n8;
        int n7;
        int n6;
        final int n5 = n6 = (n7 = (n8 = 0));
        if (a == 0) {
            if (n <= n5) {
                return;
            }
            final int n9;
            n2 = (n9 = (n3 = (n4 = (this.e.contains(mouseEvent.getPoint()) ? 1 : 0))));
            final int n10;
            n6 = (n10 = (n7 = (n8 = 1)));
        }
        final int n24;
        final int n25;
        final int n26;
        final int a3;
        Label_0485: {
            Label_0480: {
                if (a == 0) {
                    if (n == n5) {
                        final int n11 = mouseEvent.getX() - this.e.x;
                        final int n12 = mouseEvent.getY() - this.e.y;
                        int a2;
                        final int n13 = a2 = n11;
                        int n15;
                        final int n14 = n15 = 10;
                        Label_0269: {
                            if (a == 0) {
                                if (n13 < n14) {
                                    Calender calender = this;
                                    Calender calender2 = this;
                                    Label_0186: {
                                        Label_0177: {
                                            if (a == 0) {
                                                if (this.a == 0) {
                                                    calender = this;
                                                    calender2 = this;
                                                    if (a != 0) {
                                                        break Label_0177;
                                                    }
                                                    if (this.b > 1900) {
                                                        this.a = 11;
                                                        --this.b;
                                                        if (a == 0) {
                                                            break Label_0186;
                                                        }
                                                    }
                                                }
                                                calender = this;
                                                calender2 = this;
                                            }
                                        }
                                        calender.a = calender2.a - 1;
                                    }
                                    if (a == 0) {
                                        break Label_0269;
                                    }
                                }
                                final int n16;
                                a2 = (n16 = n11);
                                final int n17;
                                n15 = (n17 = this.e.width - 10);
                            }
                            Calender calender3 = null;
                            Calender calender4 = null;
                            Label_0260: {
                                if (a == 0) {
                                    if (n13 <= n14) {
                                        break Label_0269;
                                    }
                                    calender3 = this;
                                    calender4 = this;
                                    if (a != 0) {
                                        break Label_0260;
                                    }
                                    a2 = this.a;
                                    n15 = 11;
                                }
                                if (a2 == n15) {
                                    calender3 = this;
                                    calender4 = this;
                                    if (a != 0) {
                                        break Label_0260;
                                    }
                                    if (this.b < 2600) {
                                        this.a = 0;
                                        ++this.b;
                                        if (a == 0) {
                                            break Label_0269;
                                        }
                                    }
                                }
                                calender3 = this;
                                calender4 = this;
                            }
                            calender3.a = calender4.a + 1;
                        }
                        this.repaint(0L);
                        if (a == 0) {
                            break Label_0480;
                        }
                    }
                    n2 = (n4 = (this.g.contains(mouseEvent.getPoint()) ? 1 : 0));
                    n6 = (n8 = 1);
                }
                if (a != 0) {
                    break Label_0485;
                }
                if (n2 == n6) {
                    final int n18 = mouseEvent.getX() - this.g.x;
                    final int n19 = mouseEvent.getY() - this.g.y;
                    final int n20 = n18 / this.h;
                    final int n21 = n19 / this.i;
                    final int b = this.b(this.a + 1, this.b);
                    final int n22 = n21;
                    final int n23 = 0;
                    if (a == 0 && n22 <= n23) {
                        n24 = (n4 = n20);
                        n25 = (n8 = b);
                        if (a != 0) {
                            break Label_0485;
                        }
                        if (n24 >= n25) {
                            goto Label_0376;
                        }
                    }
                    else {
                        final int date = n26 = n22 + n23;
                        a3 = t.a(1 + this.a, this.b);
                        if (a != 0) {
                            break Label_0485;
                        }
                        if (n26 <= a3) {
                            this.d.setDate(date);
                            this.d.setMonth(this.a);
                            this.d.setYear(this.b - 1900);
                            Container container;
                            final g g = (g)(container = this.j);
                            if (a == 0) {
                                if (g != null) {
                                    this.j.d(true);
                                }
                                this.setVisible(false);
                                container = this.getParent();
                            }
                            container.requestFocus();
                        }
                    }
                }
            }
            mouseEvent.getX();
        }
        if (a == 0) {
            if (n24 <= n25) {
                return;
            }
            mouseEvent.getX();
        }
        if (n26 <= a3) {}
    }
    
    protected void processMouseMotionEvent(final MouseEvent mouseEvent) {
        final int a = RotationImageFilter.a;
        boolean b;
        final int n = (b = (mouseEvent.getID() != 0)) ? 1 : 0;
        int n3;
        final int n2 = n3 = 503;
        Label_0106: {
            if (a == 0) {
                if (n != n2) {
                    break Label_0106;
                }
                final boolean contains;
                b = (contains = this.e.contains(mouseEvent.getPoint()));
                final boolean b2;
                n3 = ((b2 = true) ? 1 : 0);
            }
            Label_0102: {
                Calender calender = null;
                Label_0091: {
                    if (a == 0) {
                        if (n == n2) {
                            this.setCursor(new Cursor(12));
                            if (a == 0) {
                                break Label_0102;
                            }
                        }
                        calender = this;
                        if (a != 0) {
                            break Label_0091;
                        }
                        b = this.g.contains(mouseEvent.getPoint());
                        n3 = 1;
                    }
                    if ((b ? 1 : 0) == n3) {
                        this.setCursor(new Cursor(12));
                        if (a == 0) {
                            break Label_0102;
                        }
                    }
                    calender = this;
                }
                calender.setCursor(new Cursor(0));
            }
            if (a == 0) {
                return;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    protected int a(final int n, final int n2) {
        final int a = RotationImageFilter.a;
        int n3 = n2;
        if (a == 0) {
            Label_0038: {
                if (n2 >= 1582) {
                    n3 = n;
                    if (a != 0) {
                        return n3;
                    }
                    if (n >= 1) {
                        int b = n;
                        if (a == 0) {
                            if (n > 12) {
                                break Label_0038;
                            }
                            b = this.b(n2, n);
                        }
                        final int n4 = b;
                        final int n5 = 2;
                        int b2 = 0;
                        Label_0089: {
                            final int n6;
                            Label_0088: {
                                if (a == 0) {
                                    if (n == n5) {
                                        n6 = n4;
                                        final int n7 = 6;
                                        if (a != 0) {
                                            break Label_0088;
                                        }
                                        if (n6 == n7) {
                                            final int n8 = b2 = (t.b(n2) ? 1 : 0);
                                            if (a != 0) {
                                                break Label_0089;
                                            }
                                            if (n8 == 0) {
                                                return 4;
                                            }
                                        }
                                    }
                                    t.a(n, n2);
                                }
                            }
                            b2 = n6 + n5;
                        }
                        final int n9 = b2;
                        if (a == 0 && n9 > 35) {}
                        return n9;
                    }
                }
            }
            n3 = -1;
        }
        return n3;
    }
    
    protected int b(final int n, final int n2) {
        final int a = RotationImageFilter.a;
        int n3 = n2;
        if (a == 0) {
            Label_0038: {
                if (n2 >= 1582) {
                    n3 = n;
                    if (a != 0) {
                        return n3;
                    }
                    if (n >= 1) {
                        int a2 = n;
                        if (a == 0) {
                            if (n > 12) {
                                break Label_0038;
                            }
                            a2 = t.a(n2);
                        }
                        int n4 = a2;
                        int i = 1;
                        int n5 = 0;
                        while (i < n) {
                            n5 = n4 + t.a(i, n2);
                            if (a != 0) {
                                return n5;
                            }
                            n4 = n5;
                            ++i;
                            if (a != 0) {
                                break;
                            }
                        }
                        return n5;
                    }
                }
            }
            n3 = -1;
        }
        return n3;
    }
    
    static {
        final String[] z = new String[20];
        final int n = 0;
        final char[] charArray = "\fP\u0013\t\u001f+G\b".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'J';
                            break;
                        }
                        case 1: {
                            c2 = '5';
                            break;
                        }
                        case 2: {
                            c2 = 'q';
                            break;
                        }
                        case 3: {
                            c2 = '{';
                            break;
                        }
                        default: {
                            c2 = 'j';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "\u0019P\u0001\u000f\u000f'W\u0014\t".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'J';
                            break;
                        }
                        case 1: {
                            c4 = '5';
                            break;
                        }
                        case 2: {
                            c4 = 'q';
                            break;
                        }
                        case 3: {
                            c4 = '{';
                            break;
                        }
                        default: {
                            c4 = 'j';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u001dP\u0015".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 'J';
                            break;
                        }
                        case 1: {
                            c6 = '5';
                            break;
                        }
                        case 2: {
                            c6 = 'q';
                            break;
                        }
                        case 3: {
                            c6 = '{';
                            break;
                        }
                        default: {
                            c6 = 'j';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "\u0007T\u0003\u0018\u0002".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = 'J';
                            break;
                        }
                        case 1: {
                            c8 = '5';
                            break;
                        }
                        case 2: {
                            c8 = 'q';
                            break;
                        }
                        case 3: {
                            c8 = '{';
                            break;
                        }
                        default: {
                            c8 = 'j';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "\u000b@\u0016\u000e\u0019>".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = 'J';
                            break;
                        }
                        case 1: {
                            c10 = '5';
                            break;
                        }
                        case 2: {
                            c10 = 'q';
                            break;
                        }
                        case 3: {
                            c10 = '{';
                            break;
                        }
                        default: {
                            c10 = 'j';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        z[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "\u001e@\u0014".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = 'J';
                            break;
                        }
                        case 1: {
                            c12 = '5';
                            break;
                        }
                        case 2: {
                            c12 = 'q';
                            break;
                        }
                        case 3: {
                            c12 = '{';
                            break;
                        }
                        default: {
                            c12 = 'j';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        z[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "\u000bE\u0003\u0012\u0006".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0798: {
                if (n26 > 1) {
                    break Label_0798;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = 'J';
                            break;
                        }
                        case 1: {
                            c14 = '5';
                            break;
                        }
                        case 2: {
                            c14 = 'q';
                            break;
                        }
                        case 3: {
                            c14 = '{';
                            break;
                        }
                        default: {
                            c14 = 'j';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        z[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "\u0004Z\u0007\u001e\u0007(P\u0003".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0918: {
                if (n30 > 1) {
                    break Label_0918;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = 'J';
                            break;
                        }
                        case 1: {
                            c16 = '5';
                            break;
                        }
                        case 2: {
                            c16 = 'q';
                            break;
                        }
                        case 3: {
                            c16 = '{';
                            break;
                        }
                        default: {
                            c16 = 'j';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        z[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "\u0019T\u0005".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1038: {
                if (n34 > 1) {
                    break Label_1038;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = 'J';
                            break;
                        }
                        case 1: {
                            c18 = '5';
                            break;
                        }
                        case 2: {
                            c18 = 'q';
                            break;
                        }
                        case 3: {
                            c18 = '{';
                            break;
                        }
                        default: {
                            c18 = 'j';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        z[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "\u0019@\u001f".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1158: {
                if (n38 > 1) {
                    break Label_1158;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = 'J';
                            break;
                        }
                        case 1: {
                            c20 = '5';
                            break;
                        }
                        case 2: {
                            c20 = 'q';
                            break;
                        }
                        case 3: {
                            c20 = '{';
                            break;
                        }
                        default: {
                            c20 = 'j';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 > n40) {
                continue;
            }
            break;
        }
        z[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "\u001e]\u0004".toCharArray();
        int length11;
        int n43;
        final int n42 = n43 = (length11 = charArray11.length);
        int n44 = 0;
        while (true) {
            Label_1278: {
                if (n42 > 1) {
                    break Label_1278;
                }
                length11 = (n43 = n44);
                do {
                    final char c21 = charArray11[n43];
                    char c22 = '\0';
                    switch (n44 % 5) {
                        case 0: {
                            c22 = 'J';
                            break;
                        }
                        case 1: {
                            c22 = '5';
                            break;
                        }
                        case 2: {
                            c22 = 'q';
                            break;
                        }
                        case 3: {
                            c22 = '{';
                            break;
                        }
                        default: {
                            c22 = 'j';
                            break;
                        }
                    }
                    charArray11[length11] = (char)(c21 ^ c22);
                    ++n44;
                } while (n42 == 0);
            }
            if (n42 > n44) {
                continue;
            }
            break;
        }
        z[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "\u000eP\u0012\u001e\u0007(P\u0003".toCharArray();
        int length12;
        int n47;
        final int n46 = n47 = (length12 = charArray12.length);
        int n48 = 0;
        while (true) {
            Label_1398: {
                if (n46 > 1) {
                    break Label_1398;
                }
                length12 = (n47 = n48);
                do {
                    final char c23 = charArray12[n47];
                    char c24 = '\0';
                    switch (n48 % 5) {
                        case 0: {
                            c24 = 'J';
                            break;
                        }
                        case 1: {
                            c24 = '5';
                            break;
                        }
                        case 2: {
                            c24 = 'q';
                            break;
                        }
                        case 3: {
                            c24 = '{';
                            break;
                        }
                        default: {
                            c24 = 'j';
                            break;
                        }
                    }
                    charArray12[length12] = (char)(c23 ^ c24);
                    ++n48;
                } while (n46 == 0);
            }
            if (n46 > n48) {
                continue;
            }
            break;
        }
        z[n45] = new String(charArray12).intern();
        final int n49 = 12;
        final char[] charArray13 = "\fG\u0018".toCharArray();
        int length13;
        int n51;
        final int n50 = n51 = (length13 = charArray13.length);
        int n52 = 0;
        while (true) {
            Label_1518: {
                if (n50 > 1) {
                    break Label_1518;
                }
                length13 = (n51 = n52);
                do {
                    final char c25 = charArray13[n51];
                    char c26 = '\0';
                    switch (n52 % 5) {
                        case 0: {
                            c26 = 'J';
                            break;
                        }
                        case 1: {
                            c26 = '5';
                            break;
                        }
                        case 2: {
                            c26 = 'q';
                            break;
                        }
                        case 3: {
                            c26 = '{';
                            break;
                        }
                        default: {
                            c26 = 'j';
                            break;
                        }
                    }
                    charArray13[length13] = (char)(c25 ^ c26);
                    ++n52;
                } while (n50 == 0);
            }
            if (n50 > n52) {
                continue;
            }
            break;
        }
        z[n49] = new String(charArray13).intern();
        final int n53 = 13;
        final char[] charArray14 = "\u0000@\u001f\u001e".toCharArray();
        int length14;
        int n55;
        final int n54 = n55 = (length14 = charArray14.length);
        int n56 = 0;
        while (true) {
            Label_1638: {
                if (n54 > 1) {
                    break Label_1638;
                }
                length14 = (n55 = n56);
                do {
                    final char c27 = charArray14[n55];
                    char c28 = '\0';
                    switch (n56 % 5) {
                        case 0: {
                            c28 = 'J';
                            break;
                        }
                        case 1: {
                            c28 = '5';
                            break;
                        }
                        case 2: {
                            c28 = 'q';
                            break;
                        }
                        case 3: {
                            c28 = '{';
                            break;
                        }
                        default: {
                            c28 = 'j';
                            break;
                        }
                    }
                    charArray14[length14] = (char)(c27 ^ c28);
                    ++n56;
                } while (n54 == 0);
            }
            if (n54 > n56) {
                continue;
            }
            break;
        }
        z[n53] = new String(charArray14).intern();
        final int n57 = 14;
        final char[] charArray15 = "\u0000@\u001d\u0002".toCharArray();
        int length15;
        int n59;
        final int n58 = n59 = (length15 = charArray15.length);
        int n60 = 0;
        while (true) {
            Label_1758: {
                if (n58 > 1) {
                    break Label_1758;
                }
                length15 = (n59 = n60);
                do {
                    final char c29 = charArray15[n59];
                    char c30 = '\0';
                    switch (n60 % 5) {
                        case 0: {
                            c30 = 'J';
                            break;
                        }
                        case 1: {
                            c30 = '5';
                            break;
                        }
                        case 2: {
                            c30 = 'q';
                            break;
                        }
                        case 3: {
                            c30 = '{';
                            break;
                        }
                        default: {
                            c30 = 'j';
                            break;
                        }
                    }
                    charArray15[length15] = (char)(c29 ^ c30);
                    ++n60;
                } while (n58 == 0);
            }
            if (n58 > n60) {
                continue;
            }
            break;
        }
        z[n57] = new String(charArray15).intern();
        final int n61 = 15;
        final char[] charArray16 = "\u0007T\b".toCharArray();
        int length16;
        int n63;
        final int n62 = n63 = (length16 = charArray16.length);
        int n64 = 0;
        while (true) {
            Label_1878: {
                if (n62 > 1) {
                    break Label_1878;
                }
                length16 = (n63 = n64);
                do {
                    final char c31 = charArray16[n63];
                    char c32 = '\0';
                    switch (n64 % 5) {
                        case 0: {
                            c32 = 'J';
                            break;
                        }
                        case 1: {
                            c32 = '5';
                            break;
                        }
                        case 2: {
                            c32 = 'q';
                            break;
                        }
                        case 3: {
                            c32 = '{';
                            break;
                        }
                        default: {
                            c32 = 'j';
                            break;
                        }
                    }
                    charArray16[length16] = (char)(c31 ^ c32);
                    ++n64;
                } while (n62 == 0);
            }
            if (n62 > n64) {
                continue;
            }
            break;
        }
        z[n61] = new String(charArray16).intern();
        final int n65 = 16;
        final char[] charArray17 = "\u000bG\u0018\u001a\u0006".toCharArray();
        int length17;
        int n67;
        final int n66 = n67 = (length17 = charArray17.length);
        int n68 = 0;
        while (true) {
            Label_1998: {
                if (n66 > 1) {
                    break Label_1998;
                }
                length17 = (n67 = n68);
                do {
                    final char c33 = charArray17[n67];
                    char c34 = '\0';
                    switch (n68 % 5) {
                        case 0: {
                            c34 = 'J';
                            break;
                        }
                        case 1: {
                            c34 = '5';
                            break;
                        }
                        case 2: {
                            c34 = 'q';
                            break;
                        }
                        case 3: {
                            c34 = '{';
                            break;
                        }
                        default: {
                            c34 = 'j';
                            break;
                        }
                    }
                    charArray17[length17] = (char)(c33 ^ c34);
                    ++n68;
                } while (n66 == 0);
            }
            if (n66 > n68) {
                continue;
            }
            break;
        }
        z[n65] = new String(charArray17).intern();
        final int n69 = 17;
        final char[] charArray18 = "\u0005V\u0005\u0014\b/G".toCharArray();
        int length18;
        int n71;
        final int n70 = n71 = (length18 = charArray18.length);
        int n72 = 0;
        while (true) {
            Label_2118: {
                if (n70 > 1) {
                    break Label_2118;
                }
                length18 = (n71 = n72);
                do {
                    final char c35 = charArray18[n71];
                    char c36 = '\0';
                    switch (n72 % 5) {
                        case 0: {
                            c36 = 'J';
                            break;
                        }
                        case 1: {
                            c36 = '5';
                            break;
                        }
                        case 2: {
                            c36 = 'q';
                            break;
                        }
                        case 3: {
                            c36 = '{';
                            break;
                        }
                        default: {
                            c36 = 'j';
                            break;
                        }
                    }
                    charArray18[length18] = (char)(c35 ^ c36);
                    ++n72;
                } while (n70 == 0);
            }
            if (n70 > n72) {
                continue;
            }
            break;
        }
        z[n69] = new String(charArray18).intern();
        final int n73 = 18;
        final char[] charArray19 = "\u0000T\u001f\u000e\u000b8L".toCharArray();
        int length19;
        int n75;
        final int n74 = n75 = (length19 = charArray19.length);
        int n76 = 0;
        while (true) {
            Label_2238: {
                if (n74 > 1) {
                    break Label_2238;
                }
                length19 = (n75 = n76);
                do {
                    final char c37 = charArray19[n75];
                    char c38 = '\0';
                    switch (n76 % 5) {
                        case 0: {
                            c38 = 'J';
                            break;
                        }
                        case 1: {
                            c38 = '5';
                            break;
                        }
                        case 2: {
                            c38 = 'q';
                            break;
                        }
                        case 3: {
                            c38 = '{';
                            break;
                        }
                        default: {
                            c38 = 'j';
                            break;
                        }
                    }
                    charArray19[length19] = (char)(c37 ^ c38);
                    ++n76;
                } while (n74 == 0);
            }
            if (n74 > n76) {
                continue;
            }
            break;
        }
        z[n73] = new String(charArray19).intern();
        final int n77 = 19;
        final char[] charArray20 = "\u0007Z\u001f".toCharArray();
        int length20;
        int n79;
        final int n78 = n79 = (length20 = charArray20.length);
        int n80 = 0;
        while (true) {
            Label_2358: {
                if (n78 > 1) {
                    break Label_2358;
                }
                length20 = (n79 = n80);
                do {
                    final char c39 = charArray20[n79];
                    char c40 = '\0';
                    switch (n80 % 5) {
                        case 0: {
                            c40 = 'J';
                            break;
                        }
                        case 1: {
                            c40 = '5';
                            break;
                        }
                        case 2: {
                            c40 = 'q';
                            break;
                        }
                        case 3: {
                            c40 = '{';
                            break;
                        }
                        default: {
                            c40 = 'j';
                            break;
                        }
                    }
                    charArray20[length20] = (char)(c39 ^ c40);
                    ++n80;
                } while (n78 == 0);
            }
            if (n78 <= n80) {
                z[n77] = new String(charArray20).intern();
                Calender.z = z;
                return;
            }
            continue;
        }
    }
}
