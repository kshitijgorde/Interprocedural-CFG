import com.daysofwonder.req.x;
import com.daysofwonder.req.H;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import com.daysofwonder.applet.aG;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import com.daysofwonder.applet.J;

// 
// Decompiled by Procyon v0.5.30
// 

public class F extends J implements ActionListener, KeyListener, MouseListener, MouseMotionListener
{
    private aG a;
    private y b;
    private boolean c;
    
    public F(final aG a, final y b) {
        this.a = a;
        (this.b = b).addMouseListener(this);
        b.addMouseMotionListener(this);
        b.addKeyListener(this);
        this.a.a(this);
    }
    
    public void c() {
        this.b.removeMouseListener(this);
        this.b.removeMouseMotionListener(this);
        this.b.removeKeyListener(this);
        this.b = null;
        this.a = null;
    }
    
    public void a(final String s, final int n, final int n2) {
        if (this.b != null) {
            this.b.i();
            this.b.a(s, n, n2);
            this.b.j();
        }
    }
    
    public void a(final String s, final String s2, final int n) {
        if (this.b != null) {
            this.b.i();
            this.b.a(s, s2, n);
            this.b.j();
        }
    }
    
    public void a(final int n, final Object[] array) {
    }
    
    public void d() {
        if (!this.c && this.b != null) {
            this.b.repaint();
        }
    }
    
    public void b(final int n, final Object[] array) {
        if (this.b != null) {
            this.b.b(n, array);
        }
    }
    
    public void a() {
        if (this.b != null) {
            System.out.println("fireStateChange");
            this.b.i();
            this.d();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.b != null) {
            this.b.mouseClicked(mouseEvent);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.b != null) {
            this.b.mousePressed(mouseEvent);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.b != null) {
            this.b.mouseReleased(mouseEvent);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.b != null) {
            this.b.mouseDragged(mouseEvent);
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.b != null) {
            this.b.mouseMoved(mouseEvent);
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (this.b != null) {
            this.b.b(keyEvent);
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        if (this.b != null) {
            this.b.a(keyEvent);
        }
    }
    
    public void b() {
    }
    
    public void a(final H h) {
        if (this.b != null) {
            this.b.a(h);
        }
    }
    
    public void a(final x x) {
        if (this.b != null) {
            this.b.a(x);
        }
    }
    
    public void a(final int n) {
        if (this.b != null) {
            this.b.a(n);
        }
    }
    
    public void a(final String s) {
        if (this.b != null) {
            this.b.b(s);
        }
    }
}
