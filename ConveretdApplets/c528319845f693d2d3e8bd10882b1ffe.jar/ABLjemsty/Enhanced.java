// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

import ABLwidgets.utils;
import ABLwidgets.abljem;
import java.util.Enumeration;
import java.util.Vector;

public class Enhanced
{
    private int a;
    private Vector b;
    private Vector c;
    private Vector d;
    private Vector e;
    private Vector f;
    
    public Enhanced() {
        this.a(0);
    }
    
    public Enumeration a() {
        return this.c.elements();
    }
    
    public Enumeration b() {
        return this.d.elements();
    }
    
    public Enumeration c() {
        return this.f.elements();
    }
    
    public int d() {
        return this.b.size();
    }
    
    public DdsWindow e() {
        if (this.b.size() == 0) {
            return null;
        }
        return this.b.elementAt(this.b.size() - 1);
    }
    
    public Rollbar f() {
        if (this.d.size() == 0) {
            return null;
        }
        return this.d.elementAt(0);
    }
    
    public void a(final int a) {
        this.a = a;
        this.b = new Vector();
        this.c = new Vector();
        this.d = new Vector();
        this.e = new Vector();
        this.f = new Vector();
    }
    
    public int a(final byte[] array, int n, int n2) {
        int n3 = 63;
        ++n;
        if (--n2 < 1) {
            return 0;
        }
        try {
            n3 = array[n];
            switch (n3) {
                case 87: {
                    this.b(array, n, n2);
                    break;
                }
                case 83: {
                    this.c(array, n, n2);
                    break;
                }
                case 82: {
                    this.d(array, n, n2);
                    break;
                }
                case 68: {
                    this.e(array, n, n2);
                    break;
                }
                case 84: {
                    this.f(array, n, n2);
                    break;
                }
                default: {
                    return 0;
                }
            }
            return n2 + 1;
        }
        catch (Throwable t) {
            abljem.b("Store Enhanced " + n3 + " failed");
            t.printStackTrace();
            return 0;
        }
    }
    
    private void b(final byte[] array, final int n, final int n2) {
        final DdsWindow ddsWindow = new DdsWindow();
        final int n3 = n + n2;
        final StringBuffer sb = new StringBuffer();
        int n4 = n + 1;
        ddsWindow.b = utils.a(array, n4, 4);
        n4 += 4;
        ddsWindow.c = utils.a(array, n4, 2);
        n4 += 2;
        ddsWindow.d = utils.a(array, n4, 3);
        n4 += 3;
        final boolean[] a = this.a(array[n4++]);
        ddsWindow.e = a[0];
        ddsWindow.f = a[1];
        ddsWindow.g = (char)array[n4++];
        ddsWindow.h = (char)array[n4++];
        final int n5 = n4 + utils.a(array, n4, n3, sb);
        if (sb.length() > 0) {
            ddsWindow.i = sb.toString();
        }
        final int n6 = n5 + utils.a(array, n5, n3, sb);
        if (sb.length() > 0) {
            ddsWindow.j = sb.toString();
        }
        if (ddsWindow.f && !ddsWindow.e) {
            return;
        }
        this.b.addElement(ddsWindow);
    }
    
    private void c(final byte[] array, final int n, final int n2) {
        final Selfield selfield = new Selfield();
        final int n3 = n + n2;
        int i = n + 1;
        selfield.b = utils.a(array, i, 4);
        i += 4;
        selfield.c = utils.a(array, i, 4);
        i += 4;
        selfield.s = new ChoiceDetail[1];
        while (i < n3) {
            final byte b = (byte)(array[i++] - 48);
            if (b < 0 || i + b > n3) {
                abljem.b("jes error 1");
                break;
            }
            final int a = utils.a(array, i, b);
            final byte b2 = (byte)(i + b);
            if (a < 1 || b2 + a > n3) {
                abljem.b("jes error 2");
                break;
            }
            switch (array[b2]) {
                case 66: {
                    this.a(array, b2, a, selfield);
                    i = b2 + a;
                    continue;
                }
                case 82: {
                    this.b(array, b2, a, selfield);
                    i = b2 + a;
                    continue;
                }
                case 67: {
                    this.c(array, b2, a, selfield);
                    i = b2 + a;
                    continue;
                }
                default: {
                    abljem.b("jes error 3 type=" + array[b2]);
                    i = b2 + a;
                    continue;
                }
            }
        }
        this.c.addElement(selfield);
    }
    
    private void a(final byte[] array, final int n, final int n2, final Selfield selfield) {
        int n3 = n + 1;
        selfield.d = (char)array[n3++];
        final boolean[] a = this.a(array[n3++]);
        selfield.e = a[0];
        selfield.f = a[2];
        selfield.g = (char)array[n3++];
        selfield.h = (char)array[n3++];
        selfield.i = (char)array[n3++];
        selfield.j = utils.a(array, n3, 3);
        n3 += 3;
        selfield.k = utils.a(array, n3, 2);
        n3 += 2;
        selfield.l = utils.a(array, n3, 2);
        n3 += 2;
        selfield.m = utils.a(array, n3, 2);
        n3 += 2;
        selfield.n = new String(array, 0, n3, 3);
        n3 += 3;
        selfield.o = utils.a(array, n3, 3);
        n3 += 3;
        selfield.p = (array[n3++] == 49);
        selfield.s = new ChoiceDetail[selfield.o];
    }
    
    private void b(final byte[] array, final int n, final int n2, final Selfield selfield) {
        int n3 = n + 1;
        n3 += 10;
        selfield.q = utils.a(array, n3, 10);
        n3 += 10;
        selfield.r = utils.a(array, n3, 10);
        n3 += 10;
    }
    
    private void c(final byte[] array, final int n, final int n2, final Selfield selfield) {
        final ChoiceDetail choiceDetail = new ChoiceDetail();
        final int n3 = n + n2;
        final StringBuffer sb = new StringBuffer();
        int n4 = n + 1;
        choiceDetail.b = (array[n4++] == 83);
        choiceDetail.c = (array[n4++] == 65);
        final boolean[] a = this.a(array[n4++]);
        choiceDetail.d = a[0];
        choiceDetail.h = a[1];
        choiceDetail.i = a[2];
        final boolean[] a2 = this.a(array[n4++]);
        choiceDetail.j = a2[0];
        choiceDetail.k = a2[1];
        choiceDetail.l = a2[2];
        choiceDetail.e = ((array[n4] == 32) ? -1 : utils.a(array, n4, 2));
        n4 += 2;
        choiceDetail.f = new String(array, 0, n4, 3);
        n4 += 3;
        choiceDetail.m = (char)array[n4++];
        choiceDetail.n = (char)array[n4++];
        final int n5 = n4 + utils.a(array, n4, n3, sb);
        choiceDetail.g = sb.toString();
        int n6;
        for (n6 = 0; n6 < selfield.s.length - 1 && selfield.s[n6] != null; ++n6) {}
        choiceDetail.o = 0;
        choiceDetail.p = 0;
        if (n6 > 0) {
            final ChoiceDetail choiceDetail2 = selfield.s[n6 - 1];
            choiceDetail.o = choiceDetail2.o;
            choiceDetail.p = choiceDetail2.p + choiceDetail2.q + 2;
            if ((selfield.h == 'B' && choiceDetail.d) || (selfield.h != 'B' && selfield.l > 0 && n6 % selfield.l == 0)) {
                final ChoiceDetail choiceDetail3 = choiceDetail;
                ++choiceDetail3.o;
                choiceDetail.p = 0;
            }
        }
        if (selfield.h == 'B') {
            choiceDetail.q = choiceDetail.g.length() + selfield.m;
        }
        else {
            choiceDetail.q = selfield.j + selfield.m;
        }
        choiceDetail.r = selfield.c + choiceDetail.o * this.a + choiceDetail.p;
        selfield.s[n6] = choiceDetail;
    }
    
    private void d(final byte[] array, final int n, final int n2) {
        final Rollbar rollbar = new Rollbar();
        int n3 = n + 1;
        rollbar.b = utils.a(array, n3, 4);
        n3 += 4;
        rollbar.c = utils.a(array, n3, 4);
        n3 += 4;
        rollbar.d = (array[n3++] == 86);
        rollbar.e = this.a(array[n3++])[0];
        rollbar.f = utils.a(array, n3, 10);
        n3 += 10;
        rollbar.g = utils.a(array, n3, 10);
        n3 += 10;
        rollbar.h = utils.a(array, n3, 3);
        n3 += 3;
        this.d.addElement(rollbar);
    }
    
    private void e(final byte[] array, final int n, final int n2) {
        final Distribution distribution = new Distribution();
        int n3 = n + 1;
        distribution.b = utils.a(array, n3, 4);
        n3 += 4;
        distribution.c = utils.a(array, n3, 4);
        n3 += 4;
        distribution.d = this.a(array[n3++])[0];
        final int a = utils.a(array, n3, 4);
        n3 += 4;
        distribution.e = new String(array, 0, n3, a);
        this.e.addElement(distribution);
    }
    
    private void f(final byte[] array, final int n, final int n2) {
        final FieldControl fieldControl = new FieldControl();
        int n3 = n + 1;
        fieldControl.b = utils.a(array, n3, 4);
        n3 += 4;
        fieldControl.c = utils.a(array, n3, 4);
        n3 += 4;
        fieldControl.d = (char)array[n3++];
        fieldControl.e = (char)array[n3++];
        fieldControl.f = (char)array[n3++];
        fieldControl.g = (char)array[n3++];
        fieldControl.h = ((array[n3] == 32) ? -1 : utils.a(array, n3, 3));
        n3 += 3;
        fieldControl.i = (byte)((array[n3] == 32) ? 0 : utils.a(array[n3], array[n3 + 1]));
        n3 += 2;
        fieldControl.j = ((array[n3] == 32) ? null : new String(array, 0, n3, 3));
        n3 += 3;
        fieldControl.k = (char)array[n3++];
        this.f.addElement(fieldControl);
    }
    
    private boolean[] a(final byte b) {
        final byte b2 = (byte)(b - 48);
        return new boolean[] { (b2 & 0x4) != 0x0, (b2 & 0x2) != 0x0, (b2 & 0x1) != 0x0 };
    }
    
    public class DdsWindow
    {
        public int b;
        public int c;
        public int d;
        public boolean e;
        public boolean f;
        public char g;
        public char h;
        public String i;
        public String j;
    }
    
    public class Selfield
    {
        public int b;
        public int c;
        public char d;
        public boolean e;
        public boolean f;
        public char g;
        public char h;
        public char i;
        public int j;
        public int k;
        public int l;
        public int m;
        public String n;
        public int o;
        public boolean p;
        public int q;
        public int r;
        public ChoiceDetail[] s;
    }
    
    public class ChoiceDetail
    {
        public boolean b;
        public boolean c;
        public boolean d;
        public int e;
        public String f;
        public String g;
        public boolean h;
        public boolean i;
        public boolean j;
        public boolean k;
        public boolean l;
        public char m;
        public char n;
        public int o;
        public int p;
        public int q;
        public int r;
    }
    
    public class Rollbar
    {
        public int b;
        public int c;
        public boolean d;
        public boolean e;
        public int f;
        public int g;
        public int h;
    }
    
    public class Distribution
    {
        public int b;
        public int c;
        public boolean d;
        public String e;
    }
    
    public class FieldControl
    {
        public int b;
        public int c;
        public char d;
        public char e;
        public char f;
        public char g;
        public int h;
        public byte i;
        public String j;
        public char k;
    }
}
