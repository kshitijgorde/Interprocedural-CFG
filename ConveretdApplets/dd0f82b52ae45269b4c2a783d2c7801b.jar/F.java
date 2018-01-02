import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class F implements KeyListener, n
{
    private boolean[] c;
    int[] a;
    private int d;
    V b;
    
    public F(final V b, final int d) {
        this.b = b;
        this.d = d;
        this.c = new boolean[255];
        this.a = new int[8];
    }
    
    public final short a(final int n) {
        return (short)(this.c[this.a[n]] ? 65 : 64);
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        final int keyCode;
        if ((keyCode = keyEvent.getKeyCode()) >= this.c.length) {
            return;
        }
        this.c[keyCode] = true;
        if (keyCode == this.a[6]) {
            this.c[this.a[7]] = false;
            return;
        }
        if (keyCode == this.a[7]) {
            this.c[this.a[6]] = false;
            return;
        }
        if (keyCode == this.a[4]) {
            this.c[this.a[5]] = false;
            return;
        }
        if (keyCode == this.a[5]) {
            this.c[this.a[4]] = false;
        }
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
        final int keyCode;
        if ((keyCode = keyEvent.getKeyCode()) >= this.c.length) {
            return;
        }
        this.c[keyCode] = false;
        if (this.d == 0) {
            switch (keyCode) {
                case 116: {
                    if (this.b.m) {
                        this.b.b();
                        this.b.c();
                        final V b;
                        if ((b = this.b).l != null) {
                            b.a(b.l);
                        }
                        this.b.a();
                        return;
                    }
                    break;
                }
                case 121: {
                    if (this.b.j != null) {
                        this.b.j.g();
                        return;
                    }
                    break;
                }
                case 123: {
                    JOptionPane.showInputDialog("Save Code for Resuming Game.", "Test");
                    break;
                }
            }
        }
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
    }
    
    public final void a() {
        this.c = new boolean[255];
    }
}
