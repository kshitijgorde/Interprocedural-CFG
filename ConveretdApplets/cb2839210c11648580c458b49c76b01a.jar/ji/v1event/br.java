// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import ji.util.d;
import ji.v1base.jiPanel;
import ji.v1base.bz;
import ji.util.e;
import java.awt.event.MouseEvent;
import java.awt.Point;
import ji.awt.bb;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class br implements MouseListener, MouseMotionListener
{
    static boolean a;
    static long b;
    static long c;
    static long d;
    Object e;
    boolean f;
    boolean g;
    bb h;
    Point i;
    private String j;
    
    public br(final String j, final Object e) {
        this.e = null;
        this.f = false;
        this.g = false;
        this.h = null;
        this.i = new Point(0, 0);
        this.j = null;
        this.e = e;
        this.j = j;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.f = true;
        br.b = System.currentTimeMillis();
        this.a(mouseEvent);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.f = false;
        this.g = false;
        br.c = System.currentTimeMillis();
        this.a(mouseEvent, true);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.a(mouseEvent, true);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.a(mouseEvent, this.g = true);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.g = true;
        this.a(mouseEvent, false);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.a(mouseEvent, false);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    private final void a(final MouseEvent mouseEvent, final boolean b) {
        try {
            this.i = mouseEvent.getPoint();
            if (!b && System.currentTimeMillis() - br.d < ji.util.e.bf) {
                return;
            }
            if (br.a) {
                br.a = false;
                br.d = 0L;
                final Point point = mouseEvent.getPoint();
                this.a(new d8(mouseEvent.getSource(), 2, point.x, point.y));
            }
        }
        catch (Exception ex) {}
    }
    
    private final void a(final MouseEvent mouseEvent) {
        try {
            this.i = mouseEvent.getPoint();
            if (!br.a && !br.a && this.h == null && this.f && !this.g) {
                (this.h = new bb(this.j, new zw(mouseEvent))).start();
            }
        }
        catch (Exception ex) {}
    }
    
    private void a(final d8 d8) {
        try {
            if (this.e instanceof bz) {
                ((bz)this.e).a(d8);
            }
            else if (this.e instanceof jiPanel) {
                ((jiPanel)this.e).fireTipEvent(d8);
            }
        }
        catch (Exception ex) {}
    }
    
    public void a() {
        this.e = null;
    }
    
    static {
        br.a = false;
        br.b = 0L;
        br.c = 0L;
        br.d = 0L;
    }
    
    class zw implements Runnable
    {
        Object a;
        
        public zw(final MouseEvent mouseEvent) {
            this.a = mouseEvent.getSource();
        }
        
        public final void run() {
            try {
                ji.util.d.b(ji.util.e.bc, 104, br.this.j);
                if (br.this.f && !br.this.g) {
                    br.a = true;
                    br.d = System.currentTimeMillis();
                    br.this.a(new d8(this.a, 1, br.this.i.x, br.this.i.y));
                }
            }
            catch (Exception ex) {}
            finally {
                br.this.h = null;
            }
        }
    }
}
