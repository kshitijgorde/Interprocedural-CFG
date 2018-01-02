// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.sound;

import java.net.MalformedURLException;
import shout3d.core.g;
import shout3d.core.Field;
import shout3d.core.Shout3DViewer;
import java.net.URL;
import shout3d.core.Clock;
import java.applet.Applet;
import shout3d.core.StringField;
import shout3d.core.BooleanField;
import shout3d.core.DoubleField;
import shout3d.core.FieldObserver;
import shout3d.core.Node;

public class JavaSound extends Node implements FieldObserver
{
    public final DoubleField a;
    public final DoubleField b;
    public final BooleanField d;
    public final StringField c;
    private Applet e;
    private static final int f = 0;
    private static final int g = 1;
    private static final int h = 2;
    private static final int i = 3;
    private static final int j = 4;
    private Clock k;
    private a l;
    private short m;
    private static final short n = 1;
    private static final short o = 2;
    private static final short p = 4;
    private static final short q = 8;
    private static final short r = 16;
    private boolean s;
    private URL t;
    
    private boolean a() {
        if (this.k != null && this.b.getValue() > 0.0 && this.b.getValue() < this.k.getAbsoluteTime()) {
            this.b();
            return true;
        }
        return false;
    }
    
    private void b() {
        if (this.l != null && !this.l.b) {
            this.l.f().stop();
            this.s = false;
        }
    }
    
    public JavaSound() {
        this.a = new DoubleField(this, "startTime", 0, 0.0);
        this.b = new DoubleField(this, "stopTime", 0, 0.0);
        this.d = new BooleanField(this, "loop", 0, false);
        this.c = new StringField(this, "url", 0, "");
        this.l = null;
        this.m = -1;
        this.s = false;
    }
    
    public JavaSound(final Shout3DViewer viewer) {
        this.a = new DoubleField(this, "startTime", 0, 0.0);
        this.b = new DoubleField(this, "stopTime", 0, 0.0);
        this.d = new BooleanField(this, "loop", 0, false);
        this.c = new StringField(this, "url", 0, "");
        this.l = null;
        this.m = -1;
        this.s = false;
        this.setViewer(viewer);
    }
    
    public void setViewer(final Shout3DViewer viewer) {
        super.setViewer(viewer);
        if (super.c != null) {
            this.k = super.c.getClock();
            if (super.c.b() instanceof Applet) {
                this.e = (Applet)super.c.b();
            }
        }
        this.a.addFieldObserver(this, new Integer(0));
        this.b.addFieldObserver(this, new Integer(1));
        this.d.addFieldObserver(this, new Integer(2));
        this.c.addFieldObserver(this, new Integer(3));
    }
    
    public void onFieldChange(final Field field, final Object o) {
        switch ((int)o) {
            case 0: {
                this.m |= 0x1;
            }
            case 1: {
                this.m |= 0x2;
            }
            case 2: {
                this.m |= 0x4;
            }
            case 3: {
                this.m |= 0x8;
            }
            case 4: {
                this.m |= 0x10;
            }
            default: {}
        }
    }
    
    private void c() {
        if (this.m == 0) {
            return;
        }
        if ((this.m & 0x8) == 0x0) {
            return;
        }
        if (this.l != null && this.s) {
            this.b();
        }
        if (this.g() == null) {
            this.l = null;
            return;
        }
        final Object b = super.c.a().b(this.g());
        if (b == null) {
            (this.l = new a(super.c, this.g())).c();
        }
        else {
            this.l = (a)b;
        }
        this.m &= 0xFFFFFFF7;
    }
    
    private void d() {
        if (this.l == null) {
            return;
        }
        if (this.l.f() == null && !this.l.b) {
            this.l.c();
        }
        if (this.l.f() != null && !this.l.b) {
            this.l.f().play();
            this.s = true;
        }
    }
    
    private boolean e() {
        if (this.d.getValue()) {
            this.f();
            return true;
        }
        if (this.k != null && this.a.getValue() > 0.0 && this.a.getValue() <= this.k.getAbsoluteTime()) {
            this.d();
            return true;
        }
        return false;
    }
    
    public void b(final g g) {
        super.b(g);
        this.c();
        if (this.l == null || this.l.f() == null) {
            return;
        }
        if (this.m == 0 || this.l.b) {
            return;
        }
        if (((this.m & 0x1) != 0x0 || (this.m & 0x4) != 0x0) && this.e() && !this.l.b) {
            this.m &= 0xFFFFFFFE;
            this.m &= 0xFFFFFFFB;
        }
        if ((this.m & 0x2) != 0x0 && this.a() && !this.l.b) {
            this.m &= 0xFFFFFFFD;
        }
    }
    
    private void f() {
        if (this.l == null) {
            return;
        }
        if (this.l.f() == null && !this.l.b) {
            this.l.c();
        }
        if (this.l.f() != null && !this.l.b) {
            this.l.f().loop();
            this.s = true;
        }
    }
    
    private void a(final URL url) {
        try {
            String value = null;
            if (this.c != null && this.c.getValue() != null && !this.c.getValue().equals("")) {
                value = this.c.getValue();
            }
            if (value == null) {
                return;
            }
            try {
                if (value.startsWith("http") || value.startsWith("HTTP")) {
                    this.t = new URL(value);
                    return;
                }
                this.t = new URL(url, value);
            }
            catch (MalformedURLException ex) {
                System.err.println(ex);
            }
        }
        catch (Exception ex2) {}
    }
    
    protected void finalize() throws Throwable {
        this.a.removeFieldObserver(this);
        this.b.removeFieldObserver(this);
        this.d.removeFieldObserver(this);
        this.c.removeFieldObserver(this);
        if (this.l != null && this.l.f() != null) {
            this.l.f().stop();
        }
        super.finalize();
    }
    
    protected URL g() {
        if ((this.t == null || (this.m & 0x8) != 0x0) && super.c != null) {
            if (super.c.a().b() != null) {
                this.a(super.c.a().b());
            }
            else if (super.c.b() instanceof Applet) {
                this.a(((Applet)super.c.b()).getCodeBase());
            }
        }
        return this.t;
    }
}
