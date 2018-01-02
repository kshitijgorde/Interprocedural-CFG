import java.util.BitSet;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.Color;
import java.util.Vector;
import java.awt.Cursor;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ax extends ae
{
    public boolean a;
    public boolean b;
    public int c;
    
    public ax(final d d, final q q, final int n, final int n2, final boolean a) {
        super(d, q, n, n2);
        this.setCursor(Cursor.getPredefinedCursor(0));
        this.setBackground(super.a.i().c);
        super.a.d();
        this.setForeground(f.a(this.getBackground()));
        this.a = a;
        this.b = !this.a;
        super.f = 1;
        this.c = super.f;
    }
    
    public Vector a(final s s) {
        final Vector<am> vector = new Vector<am>(2);
        if (s.f.d() != null && !s.c().ac) {
            vector.addElement(new am(super.a, false, this.c(false), s.c().a()));
        }
        else {
            vector.addElement(new am(super.a, false, this.b(false), s.c().a()));
        }
        return vector;
    }
    
    public Color b(final s s) {
        return s.c().c;
    }
    
    public Vector a(final Vector vector, final s s) {
        return s.a(vector);
    }
    
    public final Vector a(final s s, final int n) {
        Vector<am> vector = null;
        String c = null;
        if (s.f.d() != null && !s.c().ac) {
            c = this.c(true);
        }
        final StringBuffer sb = new StringBuffer((c != null) ? c.trim() : "");
        final String s2 = (n > 1 || sb.length() == 0) ? this.b(false) : null;
        if (s2 != null && s2.trim().length() > 0) {
            sb.append((sb.length() > 0) ? " | " : "");
            sb.append(s2.trim());
        }
        if (sb.length() > 0) {
            vector = new Vector<am>(1);
            final Object object = new Object(super.a, 1) {
                public d a = a;
                public Vector b = new Vector(n);
                public Vector c = new Vector(n);
                
                public final void a(final Color color, final int y) {
                    if (this.b.size() == 0) {
                        if (y > 0) {
                            this.b.addElement(new Point(0, y));
                            this.c.addElement(color);
                        }
                    }
                    else {
                        final Point point = this.b.elementAt(this.b.size() - 1);
                        final Color color2 = this.c.elementAt(this.c.size() - 1);
                        if (y > point.y) {
                            if ((color2 == null && color == null) || (color2 != null && color2.equals(color))) {
                                point.y = y;
                            }
                            else {
                                this.b.addElement(new Point(point.y, y));
                                this.c.addElement(color);
                            }
                        }
                    }
                }
                
                public final int a() {
                    return this.b.size();
                }
                
                public final Point a(final int n) {
                    return this.b.elementAt(n);
                }
                
                public final Color b(final int n) {
                    return this.c.elementAt(n);
                }
            };
            object.a(null, sb.length());
            vector.addElement(new am(super.a, false, sb.toString(), object));
        }
        return vector;
    }
    
    public String c(final s s) {
        return null;
    }
    
    public String d(final s s) {
        return null;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Cursor cursor;
        if ((cursor = this.getCursor()) != null && cursor.equals(ag.a)) {
            this.e();
        }
        super.mousePressed(mouseEvent);
    }
    
    public w f() {
        return super.q = super.c.d();
    }
    
    public boolean g() {
        return this.b() == null;
    }
    
    public void a(final boolean b) {
        if (!this.a) {
            this.b = b;
        }
        else if (b) {
            this.c = Math.max(this.c, super.f);
            super.f = 0;
        }
        else {
            super.f = this.c;
        }
    }
    
    public String b(final boolean b) {
        return this.a().b(b);
    }
    
    public String c(final boolean b) {
        final s a = this.a();
        String f = null;
        final y b2;
        if ((b2 = a.f.b()) != null && a.x()) {
            f = b2.f;
        }
        final y d;
        String s;
        if ((d = a.f.d()) != null) {
            final Object a2 = super.a.d().a(a, d.f, this.b(), new Object[1], f);
            if (a2 != null) {
                s = a2.toString();
            }
            else {
                s = a.c().a;
            }
            if (!b && s.equals(a.c().a)) {
                s = "";
            }
        }
        else if (b) {
            s = a.c().a;
        }
        else {
            s = this.b(b);
        }
        return s;
    }
    
    public void a(final BitSet set) {
    }
    
    public final void b(final BitSet set) {
        final BitSet set2 = new BitSet(1);
        set2.set(0);
        super.a(set2);
        super.b(set2);
    }
}
