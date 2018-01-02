// 
// Decompiled by Procyon v0.5.30
// 

package ji.applet.support;

import java.util.Vector;
import ji.util.d;
import ji.util.e;
import java.awt.AWTEvent;
import java.awt.event.WindowEvent;
import ji.io.h;
import ji.util.i;
import java.awt.Window;
import ji.ext.cb;
import java.awt.event.WindowListener;

public class cc implements WindowListener, cd
{
    private ts a;
    private Object b;
    private boolean c;
    private boolean d;
    private cb e;
    private boolean f;
    private Window g;
    private String h;
    private int i;
    private int j;
    
    public cc(final String h, final Window g, final int i, final int j) {
        this.b = new Object();
        this.c = false;
        this.d = false;
        this.e = null;
        this.f = false;
        this.g = null;
        this.i = 0;
        this.j = 0;
        this.i = i;
        this.j = j;
        this.h = h;
        this.a = new ts((ad6)null);
        this.g = g;
        if (g instanceof WindowListener) {
            g.removeWindowListener((WindowListener)g);
        }
        g.addWindowListener(this);
    }
    
    public static boolean a(final int n, final int n2, final String s) {
        if (n2 == 0) {
            if (i.c(124)) {
                h.d(s, "Unknown plugin type");
            }
            return false;
        }
        boolean b = false;
        switch (n) {
            case 3: {
                b = (n2 != 2);
                break;
            }
            case 1:
            case 2:
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 11: {
                b = true;
                break;
            }
            default: {
                b = false;
                break;
            }
        }
        return b;
    }
    
    public void a(final cb e) {
        this.e = e;
    }
    
    private synchronized boolean c() {
        return this.c;
    }
    
    private synchronized boolean d() {
        return this.d;
    }
    
    private synchronized void e() {
        if (ji.util.i.c(131)) {
            ji.io.h.d(this.h, "set caughtClosing");
        }
        this.c = true;
    }
    
    private synchronized void f() {
        if (ji.util.i.c(131)) {
            ji.io.h.d(this.h, "set caughtClosed");
        }
        this.d = true;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (ji.util.i.c(131)) {
            ji.io.h.d(this.h, "windowClosing received");
        }
        if (!this.c()) {
            try {
                if (ji.util.i.c(131)) {
                    ji.io.h.d(this.h, "about to queue windowClosing event");
                }
                if (this.a == null) {
                    return;
                }
                if (ji.util.i.c(131)) {
                    ji.io.h.d(this.h, "windowClosing: q1");
                }
                synchronized (this.a) {
                    if (ji.util.i.c(131)) {
                        ji.io.h.d(this.h, "windowClosing: q2");
                    }
                    if (windowEvent.getComponent() == this.g) {
                        if (ji.util.i.c(131)) {
                            ji.io.h.d(this.h, "parentContainer matches");
                        }
                        this.e();
                        this.a.addElement(windowEvent);
                        if (ji.util.i.c(131)) {
                            ji.io.h.d(this.h, "queued windowClosing event");
                        }
                        if (this.e != null) {
                            this.e.a(windowEvent.getWindow());
                        }
                        if (this.i == 4 || this.i == 2) {
                            if (this.g instanceof WindowListener) {
                                this.g.addWindowListener((WindowListener)this.g);
                            }
                            final AWTEvent awtEvent = this.a.elementAt(0);
                            this.a.removeElementAt(0);
                            if (ji.util.i.c(131)) {
                                ji.io.h.d(this.h, "dispatching event for 1.3");
                            }
                            if (ji.util.e.a7()) {
                                if (ji.util.i.c(131)) {
                                    ji.io.h.d(this.h, "dispatching event as we are in dispatch thread");
                                }
                                this.g.dispatchEvent(awtEvent);
                            }
                            else if (ji.util.i.c(131)) {
                                ji.io.h.d(this.h, "NOT dispatching event as we are NOT in dispatch thread");
                            }
                        }
                        if (ji.util.i.c(131)) {
                            ji.io.h.d(this.h, "Notifying queue windowClosing");
                        }
                        this.a.a(true);
                        this.a.notify();
                    }
                    else if (ji.util.i.c(131)) {
                        ji.io.h.d(this.h, "parentContainer mis-match");
                    }
                }
                // monitorexit(this.a)
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
                return;
            }
        }
        if (this.g != null && this.i != 4 && this.i != 2) {
            if (ji.util.i.c(131)) {
                ji.io.h.d(this.h, "removing this as WindowListener");
            }
            this.g.removeWindowListener(this);
        }
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
        if (ji.util.i.c(131)) {
            ji.io.h.d(this.h, "windowClosed received");
        }
        if (!this.d()) {
            this.f();
            if (this.i == 4 || this.i == 2 || this.i == 6) {
                boolean b = false;
                try {
                    if (ji.util.i.c(131)) {
                        ji.io.h.d(this.h, "queued windowClosed event for 1.3");
                    }
                    if (this.g != null && this.g instanceof WindowListener) {
                        this.g.removeWindowListener((WindowListener)this.g);
                    }
                    if (this.a != null) {
                        if (ji.util.i.c(131)) {
                            ji.io.h.d(this.h, "windowClosed: q1");
                        }
                        synchronized (this.a) {
                            if (ji.util.i.c(131)) {
                                ji.io.h.d(this.h, "windowClosed: q2");
                            }
                            this.a.addElement(windowEvent);
                            if (ji.util.i.c(131)) {
                                ji.io.h.d(this.h, "Notifying queue windowClosed");
                            }
                            this.a.a(true);
                            this.a.notify();
                            b = true;
                        }
                        // monitorexit(this.a)
                    }
                    return;
                }
                catch (Exception ex) {
                    ji.util.d.a(ex);
                    return;
                }
                finally {
                    if (!b) {
                        if (ji.util.i.c(131)) {
                            ji.io.h.d(this.h, "Queue not notified, trying again");
                        }
                        if (this.a != null) {
                            if (ji.util.i.c(131)) {
                                ji.io.h.d(this.h, "windowClosed#2: q1");
                            }
                            synchronized (this.a) {
                                if (ji.util.i.c(131)) {
                                    ji.io.h.d(this.h, "windowClosed#2: q2");
                                }
                                if (ji.util.i.c(131)) {
                                    ji.io.h.d(this.h, "Notifying queue windowClosed #2");
                                }
                                this.a.a(true);
                                this.a.notify();
                            }
                            // monitorexit(this.a)
                        }
                    }
                }
            }
            if (this.a == null) {
                return;
            }
            if (ji.util.i.c(131)) {
                ji.io.h.d(this.h, "windowClosed#3: q1");
            }
            synchronized (this.a) {
                if (ji.util.i.c(131)) {
                    ji.io.h.d(this.h, "windowClosed#3: q1");
                }
                if (!this.c()) {
                    if (ji.util.i.c(131)) {
                        ji.io.h.d(this.h, "windowClosing not yet received queuesize=".concat(String.valueOf(String.valueOf(this.a.size()))));
                    }
                    if (b9.a(this.i, this.j, this.h)) {
                        if (ji.util.i.c(131)) {
                            ji.io.h.d(this.h, "Notifying queue windowClosed #3");
                        }
                        this.a.a(true);
                        this.a.notify();
                        if (ji.util.i.c(131)) {
                            ji.io.h.d(this.h, "Finished notify");
                        }
                    }
                }
                else if (ji.util.i.c(131)) {
                    ji.io.h.d(this.h, "Already caught closing");
                }
                // monitorexit(this.a)
                return;
            }
        }
        if (this.g != null && (this.i == 4 || this.i == 2)) {
            if (ji.util.i.c(131)) {
                ji.io.h.d(this.h, "removing this as WindowListener 1.3");
            }
            this.g.removeWindowListener(this);
        }
        if (this.e != null) {
            this.e.b(this.g);
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void a() {
        if (!this.f) {
            if (ji.util.i.c(131)) {
                ji.io.h.d(this.h, "stopFinished");
            }
            this.f = true;
            this.g();
        }
    }
    
    public void b() {
        try {
            if (ji.util.i.c(131)) {
                ji.io.h.d(this.h, "destroyFinished");
            }
            if (this.e != null) {
                if (ji.util.i.c(131)) {
                    ji.io.h.d(this.h, "destroyFinished: notifying shutdownListener");
                }
                this.e.b();
            }
            else if (ji.util.i.c(131)) {
                ji.io.h.d(this.h, "destroyFinished: not notifying shutdownListener (null)");
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        this.h();
    }
    
    private void g() {
        try {
            if (ji.util.i.c(131)) {
                ji.io.h.d(this.h, "appletFinished");
            }
            if (this.g != null && this.i == 5 && this.g instanceof WindowListener) {
                if (ji.util.i.c(131)) {
                    ji.io.h.d(this.h, "re-adding parent");
                }
                this.g.addWindowListener((WindowListener)this.g);
            }
            if (this.i != 5) {
                AWTEvent awtEvent = null;
                boolean b = false;
                synchronized (this.b) {
                    if (this.a != null) {
                        if (ji.util.i.c(131)) {
                            ji.io.h.d(this.h, "appletFinished: q1");
                        }
                        final ts a = this.a;
                        // monitorenter(a)
                        try {
                            if (ji.util.i.c(131)) {
                                ji.io.h.d(this.h, "appletFinished: q2");
                            }
                            int n = 1;
                            if (ji.util.i.c(131)) {
                                ji.io.h.d(this.h, "querying wait");
                            }
                            if (b9.a(this.i, this.j, this.h)) {
                                n = 5000;
                                if (ji.util.i.c(131)) {
                                    ji.io.h.d(this.h, "this browser/runtime requires wait for event ".concat(String.valueOf(String.valueOf(n))));
                                }
                            }
                            else if (ji.util.i.c(131)) {
                                ji.io.h.d(this.h, "no wait required");
                            }
                            int n2 = this.a.size();
                            if (n2 == 0) {
                                final long currentTimeMillis = System.currentTimeMillis();
                                if (!this.a.a()) {
                                    if (ji.util.i.c(131)) {
                                        ji.io.h.d(this.h, "waiting for queue notification ".concat(String.valueOf(String.valueOf(n))));
                                    }
                                    this.a.wait(n);
                                    final long currentTimeMillis2 = System.currentTimeMillis();
                                    if (ji.util.i.c(131)) {
                                        if (currentTimeMillis2 - currentTimeMillis >= n) {
                                            ji.io.h.d(this.h, "TIMEOUT");
                                        }
                                        if (!this.a.a()) {
                                            ji.io.h.d(this.h, "TIMEOUT (flag)");
                                        }
                                    }
                                    n2 = this.a.size();
                                    if (ji.util.i.c(131)) {
                                        ji.io.h.d(this.h, String.valueOf(String.valueOf(new StringBuffer("got queue notification after ").append(currentTimeMillis2 - currentTimeMillis).append(" with queue size ").append(n2))));
                                    }
                                }
                                else if (ji.util.i.c(131)) {
                                    ji.io.h.d(this.h, "NOT waiting for queue notification, already notified");
                                }
                            }
                            if (this.g instanceof WindowListener) {
                                if (ji.util.i.c(131)) {
                                    ji.io.h.d(this.h, "re-adding parent");
                                }
                                this.g.addWindowListener((WindowListener)this.g);
                            }
                            if (n2 > 0) {
                                for (int i = 0; i < n2; ++i) {
                                    awtEvent = this.a.elementAt(0);
                                    this.a.removeElementAt(0);
                                    if (ji.util.i.c(131)) {
                                        ji.io.h.d(this.h, "dispatching event");
                                    }
                                }
                            }
                            else {
                                this.e();
                                awtEvent = new WindowEvent(this.g, 201);
                                if (ji.util.i.c(131)) {
                                    ji.io.h.d(this.h, "dispatching custom closing event");
                                }
                                b = true;
                            }
                        }
                        // monitorexit(a)
                        finally {}
                    }
                }
                // monitorexit(this.b)
                if (awtEvent != null && this.g != null) {
                    this.g.dispatchEvent(awtEvent);
                }
                if (b && this.g != null) {
                    final WindowEvent windowEvent = new WindowEvent(this.g, 202);
                    if (ji.util.i.c(131)) {
                        ji.io.h.d(this.h, "dispatching custom closed event");
                    }
                    this.g.dispatchEvent(windowEvent);
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    private void h() {
        if (ji.util.i.c(131)) {
            ji.io.h.d(this.h, "EP4 releaseResources");
        }
        synchronized (this.b) {
            if (this.g != null) {}
            if (this.a != null) {
                if (ji.util.i.c(131)) {
                    ji.io.h.d(this.h, "releaseResources: q1");
                }
                final ts a = this.a;
                // monitorenter(a)
                try {
                    if (ji.util.i.c(131)) {
                        ji.io.h.d(this.h, "releaseResources: q2");
                    }
                    this.a.removeAllElements();
                    this.a = null;
                }
                // monitorexit(a)
                finally {}
            }
            this.e = null;
            this.g = null;
        }
        // monitorexit(this.b)
    }
    
    private class ts extends Vector
    {
        private boolean a;
        
        private ts(final cc cc) {
            this.a = false;
        }
        
        public boolean a() {
            return this.a;
        }
        
        public void a(final boolean a) {
            this.a = a;
        }
    }
    
    interface ad6
    {
    }
}
