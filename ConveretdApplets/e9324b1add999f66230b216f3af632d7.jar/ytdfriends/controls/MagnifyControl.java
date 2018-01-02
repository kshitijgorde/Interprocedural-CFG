// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.controls;

import ytdfriends.FriendsLib;
import java.awt.event.MouseEvent;
import edu.berkeley.guir.prefuse.VisualItem;
import java.util.TimerTask;
import ytdfriends.FriendsPanel;
import edu.berkeley.guir.prefuse.event.ControlAdapter;

public class MagnifyControl extends ControlAdapter
{
    public static final int MOUSE_OVER_MODE = 0;
    public static final int CLICK_MODE = 1;
    public static final int CLICK_AND_HOLD_MODE = 2;
    private FriendsPanel vizster;
    private long delay;
    private int mouseButton;
    private int mouseMask;
    private int mode;
    private TimerTask task;
    
    public MagnifyControl(final FriendsPanel vizster, final int mode) {
        this.delay = 1000L;
        this.mouseButton = 1;
        this.mouseMask = 1024;
        if (mode < 0 || mode > 2) {
            throw new IllegalArgumentException("Unrecognized mode.");
        }
        this.vizster = vizster;
        this.mode = mode;
    }
    
    public void itemEntered(final VisualItem vi, final MouseEvent e) {
        if (this.mode != 0) {
            return;
        }
        this.task = new TimerTask() {
            public void run() {
                MagnifyControl.this.setMagnify(true);
            }
        };
        FriendsLib.getTimer().schedule(this.task, this.delay);
    }
    
    public void itemExited(final VisualItem vi, final MouseEvent e) {
        if (this.mode != 0) {
            return;
        }
        this.task.cancel();
        this.setMagnify(false);
    }
    
    public void itemDragged(final VisualItem vi, final MouseEvent e) {
        if ((e.getModifiersEx() & this.mouseMask) == 0x0) {
            return;
        }
        if (this.mode == 1) {
            return;
        }
        this.task.cancel();
    }
    
    public void itemPressed(final VisualItem vi, final MouseEvent e) {
        if (e.getButton() != this.mouseButton) {
            return;
        }
        if (this.mode != 2) {
            return;
        }
        this.task = new TimerTask() {
            public void run() {
                MagnifyControl.this.setMagnify(true);
            }
        };
        FriendsLib.getTimer().schedule(this.task, this.delay);
    }
    
    public void itemReleased(final VisualItem vi, final MouseEvent e) {
        if (e.getButton() != this.mouseButton) {
            return;
        }
        if (this.mode != 2) {
            return;
        }
        this.task.cancel();
    }
    
    public void itemClicked(final VisualItem vi, final MouseEvent e) {
        if (e.getButton() != this.mouseButton) {
            return;
        }
        if (this.mode != 1) {
            return;
        }
        this.setMagnify(true);
    }
    
    public void mouseReleased(final MouseEvent e) {
        if (e.getButton() != this.mouseButton) {
            return;
        }
        if (this.mode == 0) {
            return;
        }
        this.setMagnify(false);
    }
    
    private void setMagnify(final boolean state) {
        this.vizster.setMagnify(state);
    }
}
