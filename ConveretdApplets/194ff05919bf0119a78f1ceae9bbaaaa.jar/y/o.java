// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.util.Vector;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;

public final class o implements KeyListener, MouseListener
{
    private Vector[] a;
    
    public o() {
        this.a = new Vector[2];
        for (int i = 0; i < 2; ++i) {
            this.a[i] = new Vector();
        }
    }
    
    public final void a(final cz cz, final bz[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.a[array[i].a()].addElement(cz);
        }
    }
    
    private void a(final bz bz) {
        final Vector vector = this.a[bz.a()];
        for (int i = 0; i < vector.size(); ++i) {
            vector.elementAt(i).a();
        }
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        this.a(bz.b);
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        this.a(bz.a);
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
}
