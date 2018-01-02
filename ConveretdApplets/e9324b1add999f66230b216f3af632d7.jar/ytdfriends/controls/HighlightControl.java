// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.controls;

import ytdfriends.FriendsLib;
import edu.berkeley.guir.prefuse.focus.FocusSet;
import edu.berkeley.guir.prefuse.FocusManager;
import edu.berkeley.guir.prefuse.ItemRegistry;
import java.awt.event.MouseEvent;
import edu.berkeley.guir.prefuse.VisualItem;
import java.util.TimerTask;
import edu.berkeley.guir.prefuse.activity.Activity;
import edu.berkeley.guir.prefuse.event.ControlAdapter;

public class HighlightControl extends ControlAdapter
{
    private Object focusKey;
    private Activity activity;
    private TimerTask task;
    private long delay;
    
    public HighlightControl(final Activity act, final Object key) {
        this.delay = 500L;
        this.activity = act;
        this.focusKey = key;
    }
    
    public void itemEntered(final VisualItem vi, final MouseEvent e) {
        if (!this.shouldProcess(vi)) {
            return;
        }
        if (this.task != null) {
            this.task.cancel();
        }
        final ItemRegistry registry = vi.getItemRegistry();
        synchronized (registry) {
            final FocusManager fman = registry.getFocusManager();
            final FocusSet fset = fman.getFocusSet(this.focusKey);
            fset.set(vi.getEntity());
            registry.touch(vi.getItemClass());
        }
        // monitorexit(registry)
        this.runActivity();
    }
    
    public void itemExited(final VisualItem vi, final MouseEvent e) {
        if (!this.shouldProcess(vi)) {
            return;
        }
        final ItemRegistry registry = vi.getItemRegistry();
        if (registry != null) {
            final FocusManager fman = registry.getFocusManager();
            final FocusSet fset = fman.getFocusSet(this.focusKey);
            fset.remove(vi.getEntity());
            registry.touch(vi.getItemClass());
        }
        this.task = new TimerTask() {
            public void run() {
                HighlightControl.this.runActivity();
            }
        };
        try {
            FriendsLib.getTimer().schedule(this.task, this.delay);
        }
        catch (Exception ex) {}
    }
    
    private boolean shouldProcess(final VisualItem item) {
        return item.getItemClass().equals("node");
    }
    
    private void runActivity() {
        if (this.activity != null) {
            this.activity.runNow();
        }
    }
}
