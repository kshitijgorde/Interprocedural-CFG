import java.awt.Component;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.util.Stack;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public final class aE extends Frame
{
    private Stack a;
    public au a;
    public boolean a;
    public boolean b;
    public int a;
    public aP a;
    public boolean c;
    public Canvas a;
    public an a;
    public aI a;
    public boolean d;
    public boolean e;
    private T a;
    
    public final boolean a() {
        final Object[] array = this.a.toArray();
        for (int i = 0; i < array.length; ++i) {
            if (((an)array[i]).b == 11) {
                return true;
            }
        }
        return false;
    }
    
    public final void a(final an a) {
        if (this.a != null) {
            this.a.add(this.a);
            this.a.a();
        }
        this.a = a;
    }
    
    public final void a() {
        switch (this.a()) {
            case 8:
            case 11: {}
            default: {
                while (this.b()) {
                    this.b();
                }
            }
        }
    }
    
    public final void b() {
        if (this.a.empty()) {
            this.a = null;
            return;
        }
        this.a = this.a.pop();
    }
    
    public final boolean b() {
        return this.a != null;
    }
    
    public final boolean c() {
        return !this.b();
    }
    
    public final int a() {
        if (this.a == null) {
            return -1;
        }
        return this.a.b;
    }
    
    public final boolean hasFocus() {
        return this.a.a.hasFocus;
    }
    
    public aE() {
        this.a = new Stack();
        this.a = false;
        this.b = false;
        this.a = -1;
        this.c = false;
        this.a = new Canvas();
        this.a = null;
        this.a = new aI();
        this.d = false;
        this.e = false;
    }
    
    public final void c() {
        this.d = true;
    }
    
    public final void a(final String s) {
        if (s == null) {
            return;
        }
        this.a(true);
        System.gc();
        this.b(true);
        try {
            this.a(true);
            if (this.a.b(s)) {
                throw new Exception("Cart Failed to Load");
            }
            this.a(false);
        }
        catch (Exception ex) {
            this.b(false);
            this.a(false);
        }
        this.a = new aI();
        this.b(false);
        System.gc();
        ((Component)(this.a = -1)).requestFocus();
    }
    
    public final void a(final au a) {
        this.a = a;
        a.a.setLayout(new BorderLayout());
        final ak ak = new ak(a, this);
        final Z b = new Z(a, this);
        a.a.addKeyListener(ak);
        a.a.addMouseListener(b);
        a.a.addMouseMotionListener(b);
        a.a.requestFocus();
        this.a = new aP(a, this);
    }
    
    public final void d() {
        this.a("Welcome to NESCafe 1.02, press ESC for menu...", 10, 0);
        this.e();
    }
    
    public final void e() {
        this.e = true;
    }
    
    public final void a(final boolean b) {
        if (this.a != null && this.a.a != null) {
            this.a.a.a = !b;
        }
        if (this.a != null && this.a.a != null) {
            this.a.a.a(b);
        }
    }
    
    public final void f() {
        if (this.a != null) {
            this.a.b();
        }
    }
    
    public final void g() {
        if (this.a != null) {
            this.a.d();
        }
    }
    
    public final void b(final boolean c) {
        this.c = c;
        if (c) {
            this.a.a(true);
        }
    }
    
    public final void b(final String s) {
        if (this.a != null) {
            this.a.a();
            try {
                this.a.join();
            }
            catch (Exception ex) {}
        }
        (this.a = new T(this, s)).start();
    }
    
    public final void a(final String s, final boolean b) {
        if (this.a != null) {
            this.a.a();
            try {
                this.a.join();
            }
            catch (Exception ex) {}
        }
        (this.a = new T(this, s, b)).start();
    }
    
    public final void a(final String s, final int n, final int a) {
        if (this.a != null) {
            this.a.a();
            try {
                this.a.join();
            }
            catch (Exception ex) {}
        }
        this.a = new T(this, s, n);
        this.a.a = a;
        this.a.start();
    }
    
    public final void c(final String s) {
        if (this.a != null) {
            this.a.a(s);
        }
    }
}
