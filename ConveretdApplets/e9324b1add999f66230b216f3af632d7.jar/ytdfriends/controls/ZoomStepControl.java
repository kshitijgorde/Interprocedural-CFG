// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.controls;

import ytdfriends.FriendsLib;
import edu.berkeley.guir.prefuse.Display;
import java.awt.geom.Point2D;
import java.awt.event.MouseEvent;
import java.util.TimerTask;
import ytdfriends.FriendsPanel;
import edu.berkeley.guir.prefuse.event.ControlAdapter;

public class ZoomStepControl extends ControlAdapter
{
    private FriendsPanel vizster;
    private TimerTask task;
    private long delay;
    private long down;
    private int mouseButton;
    private int mouseMask;
    private boolean zoomIn;
    
    public ZoomStepControl(final FriendsPanel vizster) {
        this.delay = 250L;
        this.mouseButton = 3;
        this.mouseMask = 4096;
        this.zoomIn = false;
        this.vizster = vizster;
    }
    
    public void mouseClicked(final MouseEvent e) {
        if (e.getButton() != this.mouseButton) {
            return;
        }
        if (this.zoomIn) {
            if (this.task != null) {
                this.task.cancel();
                this.task = null;
            }
            final long now = System.currentTimeMillis();
            if (now - this.down < 200L) {
                this.vizster.resetDisplay();
            }
        }
        else {
            this.vizster.resetDisplay();
        }
    }
    
    public void mousePressed(final MouseEvent e) {
        if (!this.zoomIn) {
            return;
        }
        if (e.getButton() != this.mouseButton) {
            return;
        }
        this.down = System.currentTimeMillis();
        this.task = new TimerTask() {
            public void run() {
                final Display d = ZoomStepControl.this.vizster.getDisplay();
                final double s = 0.99 / d.getScale();
                d.animateZoom((Point2D)e.getPoint(), s, 2000L);
                ZoomStepControl.access$1(ZoomStepControl.this, null);
            }
        };
        FriendsLib.getTimer().schedule(this.task, this.delay);
    }
    
    public void mouseReleased(final MouseEvent e) {
        if (!this.zoomIn) {
            return;
        }
        if (e.getButton() != this.mouseButton) {
            return;
        }
        if (this.task != null) {
            this.task.cancel();
            this.task = null;
        }
    }
    
    public void mouseDragged(final MouseEvent e) {
        if (!this.zoomIn) {
            return;
        }
        if ((e.getModifiersEx() & this.mouseMask) == 0x0) {
            return;
        }
        if (this.task != null) {
            this.task.cancel();
            this.task = null;
        }
    }
    
    static /* synthetic */ void access$1(final ZoomStepControl zoomStepControl, final TimerTask task) {
        zoomStepControl.task = task;
    }
}
