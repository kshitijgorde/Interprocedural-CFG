import java.awt.Dimension;
import java.awt.Container;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljemd extends Dialog
{
    public boolean a;
    private Component b;
    public Component c;
    public boolean d;
    private abljema e;
    
    public abljemd(final abljema abljema, final Frame frame, final String s, final boolean b) {
        super(frame, s, b);
        this.a = false;
        this.d = true;
        this.a(abljema, frame, null);
    }
    
    public abljemd(final abljema abljema, final Frame frame, final String s, final Dialog dialog) {
        super(frame, s, false);
        this.a = false;
        this.d = true;
        this.a(abljema, frame, dialog);
    }
    
    private void a(final abljema e, final Frame b, final Dialog b2) {
        this.e = e;
        if (b2 != null) {
            this.b = b2;
        }
        else if (this.e.b3) {
            this.b = b;
        }
        this.c = this.b;
        if (this.e.cd) {
            this.a = true;
        }
    }
    
    public boolean action(final Event event, final Object o) {
        boolean b = super.action(event, o);
        if (this.b != null && !b) {
            b = this.b.action(event, o);
        }
        return b;
    }
    
    public boolean handleEvent(final Event event) {
        if (abljema.b(event)) {
            return super.handleEvent(event);
        }
        boolean b = super.handleEvent(event);
        if ((event.id == 401 || event.id == 403 || event.id == 402 || event.id == 404) && this.c != null && !b) {
            b = this.c.handleEvent(event);
        }
        if (event.id == 201 && event.target == this && this.d) {
            this.hide();
            if (this.b != null) {
                this.b.handleEvent(event);
            }
            return true;
        }
        if (this.b != null && !b) {
            b = this.b.handleEvent(event);
        }
        return b;
    }
    
    public boolean keyDown(final Event event, final int n) {
        boolean b = super.keyDown(event, n);
        if (this.c != null && !b) {
            b = this.c.keyDown(event, n);
        }
        return b;
    }
    
    public boolean keyUp(final Event event, final int n) {
        boolean b = super.keyUp(event, n);
        if (this.c != null && !b) {
            b = this.c.keyUp(event, n);
        }
        return b;
    }
    
    public void show() {
        this.enable();
        if (this.a) {
            this.pack();
        }
        super.show();
    }
    
    public void show(final boolean b) {
        this.enable();
        if (this.a) {
            this.pack();
        }
        super.show(b);
    }
    
    public void hide() {
        final Container parent = this.getParent();
        if (parent instanceof abljemf) {
            ((abljemf)parent).d8 = true;
        }
        this.getParent().requestFocus();
        super.hide();
        this.disable();
    }
    
    public void resize(final Dimension dimension) {
        if (this.a) {
            this.pack();
        }
        super.resize(dimension);
    }
    
    public void resize(final int n, final int n2) {
        if (this.a) {
            this.pack();
        }
        super.resize(n, n2);
    }
    
    public void pack() {
        this.a = false;
        super.pack();
    }
    
    public void requestFocus() {
        super.requestFocus();
    }
    
    public boolean lostFocus(final Event event, final Object o) {
        return super.lostFocus(event, o);
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        return super.gotFocus(event, o);
    }
    
    public void a() {
        this.hide();
        this.removeAll();
        this.dispose();
    }
}
