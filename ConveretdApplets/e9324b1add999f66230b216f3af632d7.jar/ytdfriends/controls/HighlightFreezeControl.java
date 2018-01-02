// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.controls;

import ytdfriends.FriendsLib;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import edu.berkeley.guir.prefuse.FocusManager;
import edu.berkeley.guir.prefuse.ItemRegistry;
import edu.berkeley.guir.prefuse.VisualItem;
import java.util.TimerTask;
import edu.berkeley.guir.prefuse.activity.Activity;
import edu.berkeley.guir.prefuse.graph.Entity;
import edu.berkeley.guir.prefuse.focus.FocusSet;
import edu.berkeley.guir.prefuse.event.ControlAdapter;

public class HighlightFreezeControl extends ControlAdapter
{
    private FocusSet focusSet;
    private Entity curFocus;
    private Object focusSetKey;
    private Activity activity;
    private TimerTask task;
    private long delay;
    
    public HighlightFreezeControl(final Activity act, final Object focusSetKey) {
        this.delay = 200L;
        this.activity = act;
        this.focusSetKey = focusSetKey;
    }
    
    public void setFocus(final VisualItem item) {
        final Entity focus = item.getEntity();
        if (focus != this.curFocus) {
            final ItemRegistry registry = item.getItemRegistry();
            final FocusManager fm = registry.getFocusManager();
            this.focusSet = fm.getFocusSet(this.focusSetKey);
            this.curFocus = focus;
            this.focusSet.set(focus);
            registry.touch(item.getItemClass());
        }
    }
    
    public void clearFocus() {
        this.focusSet.clear();
        this.focusSet = null;
        this.curFocus = null;
    }
    
    public void itemClicked(final VisualItem item, final MouseEvent e) {
        if (this.isAllowedType(item) && SwingUtilities.isLeftMouseButton(e)) {
            if (e.getClickCount() == 1) {
                this.task = new TimerTask() {
                    public void run() {
                        HighlightFreezeControl.this.setFocus(item);
                        HighlightFreezeControl.this.runActivity();
                    }
                };
                FriendsLib.getTimer().schedule(this.task, this.delay);
            }
            else if (e.getClickCount() > 1 && this.task != null) {
                this.task.cancel();
            }
        }
    }
    
    public void mouseClicked(final MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e) && this.focusSet != null) {
            if (this.task != null) {
                this.task.cancel();
            }
            this.clearFocus();
            this.runActivity();
        }
    }
    
    private boolean isAllowedType(final VisualItem item) {
        return item.getItemClass().equals("node");
    }
    
    private void runActivity() {
        if (this.activity != null) {
            this.activity.runNow();
        }
    }
}
