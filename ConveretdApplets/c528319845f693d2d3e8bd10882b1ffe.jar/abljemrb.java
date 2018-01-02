import java.awt.Event;
import java.awt.Component;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Checkbox;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljemrb extends Checkbox
{
    public int a;
    public int b;
    public String c;
    private abljema d;
    private abljemtf e;
    private Color f;
    private Color g;
    private Color h;
    private Color i;
    private boolean j;
    private abljemfs k;
    
    abljemrb(final abljema abljema, final int a, final int b, final abljemtf e, final String c, final String s, final CheckboxGroup checkboxGroup) {
        super(s, checkboxGroup, false);
        this.j = false;
        this.d = abljema;
        this.e = e;
        this.a = a;
        this.b = b;
        this.d = abljema;
        this.c = c;
        this.k = new abljemfs(abljema, this, true);
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
            this.f = this.getForeground();
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
        return ((event.id == 401 || event.id == 403) && this.keyDown(event, event.key)) || super.handleEvent(event);
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
        this.d.e5 = this.a;
        this.d.e6 = this.b;
        this.d.fb.i();
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
        if (event.modifiers == 0 && n >= 1004 && n <= 1007) {
            this.e.a(this, n);
            return true;
        }
        if ((n == 43 && event.modifiers == 0 && this.d.j.a5) || n == 9 || n == 10 || n > 255) {
            return this.e.keyDown(event, n);
        }
        if (n == 32 && event.modifiers == 0) {
            this.setState(!this.getState());
            return true;
        }
        return super.keyDown(event, n);
    }
    
    public void setState(final boolean state) {
        if (this.e.f != null) {
            this.e.a(this, state);
        }
        super.setState(state);
    }
}
