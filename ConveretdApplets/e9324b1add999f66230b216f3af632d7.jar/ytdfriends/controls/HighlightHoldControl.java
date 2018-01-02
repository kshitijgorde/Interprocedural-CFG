// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.controls;

import edu.berkeley.guir.prefuse.FocusManager;
import edu.berkeley.guir.prefuse.ItemRegistry;
import ytdfriends.FriendsLib;
import java.awt.event.MouseEvent;
import edu.berkeley.guir.prefuse.VisualItem;
import edu.berkeley.guir.prefuse.activity.Activity;
import edu.berkeley.guir.prefuse.graph.Entity;
import edu.berkeley.guir.prefuse.focus.FocusSet;
import java.util.TimerTask;
import ytdfriends.FriendsPanel;
import edu.berkeley.guir.prefuse.event.ControlAdapter;

public class HighlightHoldControl extends ControlAdapter
{
    public static final int MOUSE_OVER_MODE = 0;
    public static final int CLICK_MODE = 1;
    public static final int CLICK_AND_HOLD_MODE = 2;
    private FriendsPanel fPanel;
    private long delay;
    private int mouseButton;
    private int mouseMask;
    private int mode;
    private TimerTask task;
    private FocusSet focusSet;
    private Entity curFocus;
    private Object focusSetKey;
    private Activity activity;
    
    public HighlightHoldControl(final Activity act, final Object focusSetKey, final FriendsPanel fPanel, final int mode) {
        this.delay = 1000L;
        this.mouseButton = 1;
        this.mouseMask = 1024;
        if (mode < 0 || mode > 2) {
            throw new IllegalArgumentException("Unrecognized mode.");
        }
        this.fPanel = fPanel;
        this.mode = mode;
        this.activity = act;
        this.focusSetKey = focusSetKey;
    }
    
    public void itemEntered(final VisualItem vi, final MouseEvent e) {
        if (this.mode != 0) {
            return;
        }
        this.task = new TimerTask() {
            public void run() {
                HighlightHoldControl.this.setFocus(vi);
                HighlightHoldControl.this.setMagnify(true);
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
        this.clearFocus();
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
                HighlightHoldControl.this.setFocus(vi);
                HighlightHoldControl.this.setMagnify(true);
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
        this.setFocus(vi);
    }
    
    public void mouseReleased(final MouseEvent e) {
        if (e.getButton() != this.mouseButton) {
            return;
        }
        if (this.mode == 0) {
            return;
        }
        this.setMagnify(false);
        this.clearFocus();
    }
    
    private void setMagnify(final boolean state) {
        this.fPanel.setMagnify(state);
    }
    
    private void setFocus(final VisualItem item) {
        final Entity focus = item.getEntity();
        if (focus != this.curFocus) {
            final ItemRegistry registry = item.getItemRegistry();
            final FocusManager fm = registry.getFocusManager();
            this.focusSet = fm.getFocusSet(this.focusSetKey);
            this.curFocus = focus;
            this.focusSet.set(focus);
            registry.touch(item.getItemClass());
        }
        this.runActivity();
    }
    
    private void clearFocus() {
        if (this.focusSet != null) {
            this.focusSet.clear();
            this.focusSet = null;
            this.curFocus = null;
            this.runActivity();
        }
    }
    
    private void runActivity() {
        if (this.activity != null) {
            this.activity.runNow();
        }
    }
}
