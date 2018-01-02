// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1base;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import ji.util.d;
import java.awt.Container;
import java.awt.event.WindowListener;
import ji.v1event.ai;
import java.awt.Frame;

public class gr extends Frame
{
    private ai a;
    private boolean b;
    private boolean c;
    private x8 d;
    
    public gr() {
        this.a = null;
        this.b = false;
        this.c = false;
        this.addWindowListener(this.d = new x8());
    }
    
    public Container a() {
        return this;
    }
    
    public gr(final String s) {
        super(s);
        this.a = null;
        this.b = false;
        this.c = false;
        this.addWindowListener(this.d = new x8());
    }
    
    public final void a(final ai a) {
        this.a = a;
    }
    
    public void dispose() {
        try {
            this.setVisible(false);
        }
        catch (Exception ex) {}
        this.c();
    }
    
    public void finalize() {
        try {
            if (ji.util.d.cz()) {
                System.out.println("finalize frame ".concat(String.valueOf(String.valueOf(this))));
            }
        }
        catch (Exception ex) {}
    }
    
    public void b() {
        try {
            if (this.a != null) {
                this.a.kk();
            }
            this.a = null;
            this.setVisible(false);
            this.dispose();
            if (this.b) {
                if (ji.util.d.c) {
                    System.out.println("System Exit 1");
                }
                else {
                    System.exit(0);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void c() {
        this.a(true);
    }
    
    public void a(final boolean b) {
        try {
            if (!this.c) {
                this.c = true;
                try {
                    if (this.a != null) {
                        this.a.kk();
                    }
                    this.a = null;
                }
                catch (Exception ex) {}
                try {
                    if (this.d != null) {
                        this.removeWindowListener(this.d);
                        this.d = null;
                    }
                }
                catch (Exception ex2) {}
                try {
                    if (b) {
                        try {
                            super.removeNotify();
                            super.dispose();
                        }
                        catch (Exception ex3) {}
                    }
                }
                catch (Exception ex4) {}
                this.c = false;
            }
        }
        catch (Exception ex5) {}
    }
    
    class x8 extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            gr.this.b();
        }
    }
}
