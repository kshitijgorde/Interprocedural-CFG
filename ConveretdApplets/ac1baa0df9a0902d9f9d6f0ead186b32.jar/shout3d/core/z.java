// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;

public class z implements DeviceObserver
{
    Shout3DViewer a;
    int b;
    x c;
    boolean d;
    boolean e;
    
    protected void a(final boolean b, final boolean b2) {
        boolean b3 = false;
        if (b && !this.e) {
            this.e = true;
            b3 = true;
            if (this.a.b() instanceof Applet) {
                ((Applet)this.a.b()).showStatus(this.c.e.t[4]);
            }
        }
        else if (!b && this.e) {
            this.e = false;
            b3 = true;
            if (this.a.b() instanceof Applet) {
                ((Applet)this.a.b()).showStatus("");
            }
        }
        if (b3 && b2 && System.getProperty("java.version").indexOf("1.0") <= -1) {
            new a().a(this.a, this.e);
        }
    }
    
    public boolean onDeviceInput(final DeviceInput deviceInput, final Object o) {
        if (this.c == null) {
            this.c = ((g)this.a.getRenderer()).h;
            this.b = this.c.e.q[1];
        }
        if (this.c != null && this.c.e.t[4] != null) {
            switch (((MouseInput)deviceInput).which) {
                case 0: {
                    return this.a(((MouseInput)deviceInput).x, ((MouseInput)deviceInput).y);
                }
                case 2: {
                    return this.b(((MouseInput)deviceInput).x, ((MouseInput)deviceInput).y);
                }
            }
        }
        return false;
    }
    
    public z() {
        this.d = false;
        this.e = false;
    }
    
    public void a(final Shout3DViewer a) {
        if (this.a != null) {
            this.a.getDeviceListener().removeDeviceObserver(this, "MouseInput");
        }
        this.a = a;
        if (this.a != null) {
            this.a.getDeviceListener().addDeviceObserver(this, "MouseInput", null);
        }
    }
    
    protected void a() {
        if (this.a.b() instanceof Applet) {
            final Applet applet = (Applet)this.a.b();
            try {
                applet.getAppletContext().showDocument(new URL(this.c.e.t[4]), "_new");
            }
            catch (MalformedURLException ex) {
                System.err.println(ex);
            }
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.a != null) {
            this.a.getDeviceListener().removeDeviceObserver(this, "MouseInput");
        }
    }
    
    protected boolean a(final int n, final int n2) {
        if (n2 > this.b - 45 && n2 < this.b - 15) {
            this.a();
            return true;
        }
        return false;
    }
    
    protected boolean b(final int n, final int n2) {
        if (n2 > this.b - 45 && n2 < this.b - 15) {
            this.a(true, true);
            this.d = true;
        }
        else if (this.d) {
            this.a(false, true);
            this.d = false;
        }
        return this.d;
    }
}
