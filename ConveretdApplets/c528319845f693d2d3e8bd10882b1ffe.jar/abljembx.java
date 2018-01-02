import java.awt.Event;
import java.awt.Component;
import ABLjemsty.EmuPanel;
import java.awt.Color;
import java.awt.Checkbox;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljembx extends Checkbox
{
    public int a;
    public int b;
    public int c;
    private abljema d;
    private abljemtf e;
    private Color f;
    private Color g;
    private Color h;
    private Color i;
    private boolean j;
    private abljemfs k;
    private int l;
    private EmuPanel m;
    
    abljembx(final abljema d, final abljemtf e, final int b, final int c) {
        this.j = false;
        this.l = 1;
        this.d = d;
        this.e = e;
        this.b = b;
        this.c = c;
        if (e != null) {
            this.a = e.az;
        }
        this.k = new abljemfs(d, this, true);
    }
    
    public void a() {
        this.l = 2;
    }
    
    public void a(final EmuPanel m) {
        this.l = 3;
        this.m = m;
    }
    
    public void a(final Color f, final Color g) {
        if (g != null) {
            this.g = g;
        }
        else {
            this.g = this.getBackground();
        }
        if (f != null) {
            this.f = f;
        }
        else {
            this.f = new Color(255 - this.g.getRed(), 255 - this.g.getGreen(), 255 - this.g.getBlue());
        }
        this.setForeground(this.f);
        this.setBackground(this.g);
        if (this.h == null) {
            this.h = this.f;
        }
        if (this.i == null) {
            this.i = this.g;
        }
    }
    
    public boolean handleEvent(final Event event) {
        return super.handleEvent(event);
    }
    
    public void enable() {
        super.enable();
        this.k.b();
    }
    
    public void disable() {
        if (!this.k.c(this.j)) {
            return;
        }
        super.disable();
    }
    
    public void hide() {
        if (!this.k.a(this.j)) {
            return;
        }
        super.hide();
    }
    
    public void show() {
        super.show();
        this.k.a();
    }
    
    public void requestFocus() {
        super.requestFocus();
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        this.j = true;
        if (this.l == 1) {
            this.d.fb.i();
        }
        if (this.h != this.f) {
            this.setForeground(this.h);
        }
        if (this.i != this.g) {
            this.setBackground(this.i);
        }
        return super.gotFocus(event, o);
    }
    
    public boolean lostFocus(final Event event, final Object o) {
        this.j = false;
        if (this.h != this.f) {
            this.setForeground(this.f);
        }
        if (this.i != this.g) {
            this.setBackground(this.g);
        }
        return super.lostFocus(event, o);
    }
    
    public boolean getFocusTraversalKeysEnabled() {
        return false;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        return super.mouseDown(event, n, n2);
    }
    
    public boolean keyDown(final Event event, final int n) {
        if ((n == 43 && event.modifiers == 0 && this.d.j.a5) || n == 9) {
            this.d.a(this.a, event.modifiers, true);
            return true;
        }
        if (n == 10) {
            switch (event.modifiers) {
                case 0: {
                    if (!this.d.bu && this.d.fb.e0.h) {
                        this.d.fb.e0.b(event);
                    }
                    if (this.d.j.a4) {
                        this.d.a(this.a, 0, true);
                    }
                    else {
                        this.d.e();
                    }
                    return true;
                }
                case 2: {
                    if (this.d.ca) {
                        this.d.cb = !this.d.cb;
                        if (this.d.cb) {
                            return true;
                        }
                    }
                    if (!this.d.bu && this.d.fb.e0.h) {
                        this.d.fb.e0.b(event);
                    }
                    if (this.d.j.a4) {
                        this.d.e();
                    }
                    else {
                        this.d.a(this.a, 0, true);
                    }
                    return true;
                }
                case 9:
                case 10: {
                    this.d.fb.t();
                    return true;
                }
            }
        }
        switch (this.l) {
            case 1: {
                final String s = new String(String.valueOf((char)n));
                if (n == 32) {
                    this.setState(!this.getState());
                    return true;
                }
                if (s.equalsIgnoreCase(this.e.n)) {
                    this.setState(true);
                    return true;
                }
                if (s.equalsIgnoreCase(this.e.o)) {
                    this.setState(false);
                    return true;
                }
                break;
            }
            case 2:
            case 3: {
                if (n == 32) {
                    this.setState(!this.getState());
                    return true;
                }
                break;
            }
        }
        return super.keyDown(event, n);
    }
    
    public void setState(final boolean state) {
        if (this.l == 2) {
            this.d.fb.a(this.e, state);
        }
        if (this.l == 3) {
            this.d.fb.a(this.m, state);
        }
        if (this.l == 1 && this.e.q != null) {
            this.e.q.a(this.e.r, state);
        }
        super.setState(state);
    }
}
