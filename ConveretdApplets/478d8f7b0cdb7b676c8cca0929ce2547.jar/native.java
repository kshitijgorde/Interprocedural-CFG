import javax.swing.JOptionPane;
import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class native implements MouseListener, MouseMotionListener
{
    private static String F = "\uf5e2\uf5ce\uf5c5\uf5c4\uf581\uf587\uf581\uf5ce\uf5d3\uf5c8\uf5c6\uf5c8\uf5cf\uf5c0\uf5cd\uf581\uf5c8\uf5c5\uf5c4\uf5c0\uf59b\uf5ab\uf5ef\uf5c8\uf5ca\uf5ce\uf581\uf5ea\uf5ce\uf5d3\uf5d5\uf5c4\uf5cd\uf5c0\uf5c8\uf5cf\uf5c4\uf5cf\uf581\uf589\uf5d2\uf5ce\uf5c7\uf5d5\uf5d6\uf5c0\uf5d3\uf5c4\uf5fe\uf5c3\uf5d8\uf5fe\uf5cf\uf5c8\uf5ca\uf5ce\uf5ca\uf5e1\uf5c9\uf5ce\uf5d5\uf5cc\uf5c0\uf5c8\uf5cd\uf58f\uf5c2\uf5ce\uf5cc\uf588\uf5ab\uf5ab\uf5ee\uf5d3\uf5c8\uf5c6\uf5c8\uf5cf\uf5c0\uf5cd\uf581\uf5c6\uf5d3\uf5c0\uf5d1\uf5c9\uf5c8\uf5c2\uf5d2\uf581\uf587\uf581\uf5c8\uf5c2\uf5ce\uf5cf\uf5d2\uf59b\uf5ab\uf5eb\uf5ce\uf5d4\uf5cf\uf5c8\uf581\uf5f4\uf5d5\uf5d3\uf5c8\uf5c0\uf5c8\uf5cf\uf5c4\uf5cf\uf581\uf589\uf5cb\uf5d5\uf5d4\uf5e1\uf5d2\uf5c2\uf5c8\uf58f\uf5c7\uf5c8\uf588\uf5ab\uf5ab\uf5e7\uf5ce\uf5d3\uf581\uf5cc\uf5ce\uf5d3\uf5c4\uf581\uf5c8\uf5cf\uf5c7\uf5ce\uf5d3\uf5cc\uf5c0\uf5d5\uf5c8\uf5ce\uf5cf\uf581\uf5c0\uf5c3\uf5ce\uf5d4\uf5d5\uf581\uf5ef\uf5c4\uf5d6\uf581\uf5e4\uf5c0\uf5d3\uf5d5\uf5c9\uf581\uf5f5\uf5c8\uf5cc\uf5c4\uf59b\uf5ab\uf5c9\uf5d5\uf5d5\uf5d1\uf59b\uf58e\uf58e\uf5d6\uf5d6\uf5d6\uf58f\uf5cf\uf5c4\uf5d6\uf5c4\uf5c0\uf5d3\uf5d5\uf5c9\uf5d5\uf5c8\uf5cc\uf5c4\uf58f\uf5cf\uf5c4\uf5d5\uf5ab\uf5ab\uf5e2\uf5d4\uf5d3\uf5d3\uf5c4\uf5cf\uf5d5\uf581\uf5d2\uf5ca\uf5c8\uf5cf\uf581\uf5c2\uf5d3\uf5c4\uf5c0\uf5d5\uf5c4\uf5c5\uf581\uf5c3\uf5d8\uf59b\uf5ab";
    private static String G = "\uf5ef\uf5e4\uf5f5\uf581\uf5e2\uf5cd\uf5ce\uf5c2\uf5ca";
    private static String H = "\uf5d7\uf590\uf58f\uf591";
    private Cursor I;
    private Cursor J;
    private Rectangle K;
    private boolean L;
    private boolean ya;
    private String M;
    private Component N;
    private static String T = "\uf5ec\uf5ce\uf5d4\uf5d2\uf5c4\uf581\uf5c2\uf5cd\uf5c8\uf5c2\uf5ca\uf5c4\uf5c5\uf581\uf5c0\uf5d5\uf581\uf5d9\uf59b\uf581";
    private static String U = "\uf58d\uf581\uf5d8\uf59b\uf581";
    private static String V = "\uf5e2\uf5ce\uf5c5\uf5c4\uf581\uf587\uf581\uf5ce\uf5d3\uf5c8\uf5c6\uf5c8\uf5cf\uf5c0\uf5cd\uf581\uf5c8\uf5c5\uf5c4\uf5c0\uf59b\uf5ab\uf5ef\uf5c8\uf5ca\uf5ce\uf581\uf5ea\uf5ce\uf5d3\uf5d5\uf5c4\uf5cd\uf5c0\uf5c8\uf5cf\uf5c4\uf5cf\uf581\uf589\uf5d2\uf5ce\uf5c7\uf5d5\uf5d6\uf5c0\uf5d3\uf5c4\uf5fe\uf5c3\uf5d8\uf5fe\uf5cf\uf5c8\uf5ca\uf5ce\uf5ca\uf5e1\uf5c9\uf5ce\uf5d5\uf5cc\uf5c0\uf5c8\uf5cd\uf58f\uf5c2\uf5ce\uf5cc\uf588\uf5ab\uf5ab\uf5ee\uf5d3\uf5c8\uf5c6\uf5c8\uf5cf\uf5c0\uf5cd\uf581\uf5c6\uf5d3\uf5c0\uf5d1\uf5c9\uf5c8\uf5c2\uf5d2\uf581\uf587\uf581\uf5c8\uf5c2\uf5ce\uf5cf\uf5d2\uf59b\uf5ab\uf5eb\uf5ce\uf5d4\uf5cf\uf5c8\uf581\uf5f4\uf5d5\uf5d3\uf5c8\uf5c0\uf5c8\uf5cf\uf5c4\uf5cf\uf581\uf589\uf5cb\uf5d5\uf5d4\uf5e1\uf5d2\uf5c2\uf5c8\uf58f\uf5c7\uf5c8\uf588\uf5ab\uf5ab\uf5e7\uf5ce\uf5d3\uf581\uf5cc\uf5ce\uf5d3\uf5c4\uf581\uf5c8\uf5cf\uf5c7\uf5ce\uf5d3\uf5cc\uf5c0\uf5d5\uf5c8\uf5ce\uf5cf\uf581\uf5c0\uf5c3\uf5ce\uf5d4\uf5d5\uf581\uf5ef\uf5c4\uf5d6\uf581\uf5e4\uf5c0\uf5d3\uf5d5\uf5c9\uf581\uf5f5\uf5c8\uf5cc\uf5c4\uf59b\uf5ab\uf5c9\uf5d5\uf5d5\uf5d1\uf59b\uf58e\uf58e\uf5d6\uf5d6\uf5d6\uf58f\uf5cf\uf5c4\uf5d6\uf5c4\uf5c0\uf5d3\uf5d5\uf5c9\uf5d5\uf5c8\uf5cc\uf5c4\uf58f\uf5cf\uf5c4\uf5d5\uf5ab\uf5ab\uf5e2\uf5d4\uf5d3\uf5d3\uf5c4\uf5cf\uf5d5\uf581\uf5d2\uf5ca\uf5c8\uf5cf\uf581\uf5c2\uf5d3\uf5c4\uf5c0\uf5d5\uf5c4\uf5c5\uf581\uf5c3\uf5d8\uf59b\uf5ab";
    private static String W = "\uf5ef\uf5e4\uf5f5\uf581\uf5e2\uf5cd\uf5ce\uf5c2\uf5ca\uf581\uf5d7\uf590\uf58f\uf591";
    private static String ba = "\uf5e2\uf5d4\uf5d3\uf5d2\uf5ce\uf5d3\uf581\uf5c4\uf5cf\uf5d5\uf5c4\uf5d3\uf5c4\uf5c5\uf581\uf5d5\uf5ce\uf581\uf5c0\uf5c2\uf5d5\uf5c8\uf5d7\uf5c4\uf581\uf5c0\uf5d3\uf5c4\uf5c0\uf580";
    private static String ca = "\uf5e2\uf5d4\uf5d3\uf5d2\uf5ce\uf5d3\uf581\uf5c4\uf5d9\uf5c8\uf5d5\uf5c4\uf5c5\uf581\uf5c0\uf5c2\uf5d5\uf5c8\uf5d7\uf5c4\uf581\uf5c0\uf5d3\uf5c4\uf5c0\uf580";
    private static String da = "\uf581\uf58c\uf581";
    private static String ea = "\uf5ef\uf5e4\uf5f5\uf581\uf5e2\uf5cd\uf5ce\uf5c2\uf5ca";
    private static String ta = "\uf5d7\uf590\uf58f\uf591";
    
    public native(final boolean ya, final int n, final int n2, final int n3, final int n4, final String m, final Component n5) {
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = false;
        this.K = new Rectangle(n, n2, n3, n4);
        this.I = new Cursor(12);
        this.J = new Cursor(0);
        this.ya = ya;
        this.N = n5;
        this.M = m;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.L && this.K.contains(x, y)) {
            return;
        }
        if (!this.L && !this.K.contains(x, y)) {
            return;
        }
        if (this.L && !this.K.contains(x, y)) {
            this.b((Canvas)mouseEvent.getSource());
        }
        else if (!this.L && this.K.contains(x, y)) {
            this._((Canvas)mouseEvent.getSource());
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.b(String.valueOf(String.valueOf(new StringBuffer(native.T).append(mouseEvent.getX()).append(native.U).append(mouseEvent.getY()))));
        if (this.K.contains(mouseEvent.getX(), mouseEvent.getY())) {
            JOptionPane.showMessageDialog(this.N, native.V.concat(String.valueOf(String.valueOf(this.M))), native.W, 1);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    private void _(final Canvas canvas) {
        this.b(native.ba);
        canvas.setCursor(this.I);
        this.L = true;
    }
    
    private void b(final Canvas canvas) {
        this.b(native.ca);
        canvas.setCursor(this.J);
        this.L = false;
    }
    
    private void b(final String s) {
        if (this.ya) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i()))).append(native.da).append(s))));
        }
    }
    
    private String i() {
        return this.getClass().getName();
    }
    
    static {
        native.F = n(native.F);
        native.G = n(native.G);
        native.H = n(native.H);
        native.T = n(native.T);
        native.U = n(native.U);
        native.V = n(native.V);
        native.W = n(native.W);
        native.ba = n(native.ba);
        native.ca = n(native.ca);
        native.da = n(native.da);
        native.ea = n(native.ea);
        native.ta = n(native.ta);
        native.F = native.V;
        native.G = native.ea;
        native.H = native.ta;
    }
    
    private static String n(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFEF5A1);
        }
        return new String(array);
    }
}
