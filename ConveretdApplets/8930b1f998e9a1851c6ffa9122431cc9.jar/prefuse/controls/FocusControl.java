// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.controls;

import prefuse.data.tuple.TupleSet;
import java.awt.event.InputEvent;
import prefuse.util.ui.UILib;
import java.awt.Cursor;
import prefuse.Display;
import java.awt.event.MouseEvent;
import prefuse.util.StringLib;
import java.util.logging.Logger;
import prefuse.data.Tuple;
import prefuse.Visualization;
import prefuse.data.expression.Predicate;
import prefuse.visual.VisualItem;

public class FocusControl extends ControlAdapter
{
    private String group;
    protected String activity;
    protected VisualItem curFocus;
    protected int ccount;
    protected int button;
    protected Predicate filter;
    
    public FocusControl() {
        this(1);
    }
    
    public FocusControl(final String group) {
        this(1);
        this.group = group;
    }
    
    public FocusControl(final int ccount) {
        this.group = Visualization.FOCUS_ITEMS;
        this.button = 16;
        this.filter = null;
        this.ccount = ccount;
    }
    
    public FocusControl(final String group, final int ccount) {
        this.group = Visualization.FOCUS_ITEMS;
        this.button = 16;
        this.filter = null;
        this.ccount = ccount;
        this.group = group;
    }
    
    public FocusControl(final int ccount, final String activity) {
        this.group = Visualization.FOCUS_ITEMS;
        this.button = 16;
        this.filter = null;
        this.ccount = ccount;
        this.activity = activity;
    }
    
    public FocusControl(final String group, final int ccount, final String activity) {
        this.group = Visualization.FOCUS_ITEMS;
        this.button = 16;
        this.filter = null;
        this.ccount = ccount;
        this.activity = activity;
        this.group = group;
    }
    
    public void setFilter(final Predicate filter) {
        this.filter = filter;
    }
    
    public Predicate getFilter() {
        return this.filter;
    }
    
    protected boolean filterCheck(final VisualItem visualItem) {
        if (this.filter == null) {
            return true;
        }
        try {
            return this.filter.getBoolean(visualItem);
        }
        catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage() + "\n" + StringLib.getStackTrace(ex));
            return false;
        }
    }
    
    public void itemEntered(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (!this.filterCheck(visualItem)) {
            return;
        }
        ((Display)mouseEvent.getSource()).setCursor(Cursor.getPredefinedCursor(12));
        if (this.ccount == 0) {
            final Visualization visualization = visualItem.getVisualization();
            visualization.getFocusGroup(this.group).setTuple(visualItem);
            this.curFocus = visualItem;
            this.runActivity(visualization);
        }
    }
    
    public void itemExited(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (!this.filterCheck(visualItem)) {
            return;
        }
        ((Display)mouseEvent.getSource()).setCursor(Cursor.getDefaultCursor());
        if (this.ccount == 0) {
            this.curFocus = null;
            final Visualization visualization = visualItem.getVisualization();
            visualization.getFocusGroup(this.group).removeTuple(visualItem);
            this.runActivity(visualization);
        }
    }
    
    public void itemClicked(final VisualItem curFocus, final MouseEvent mouseEvent) {
        if (!this.filterCheck(curFocus)) {
            return;
        }
        if (UILib.isButtonPressed(mouseEvent, this.button) && mouseEvent.getClickCount() == this.ccount) {
            if (curFocus != this.curFocus) {
                final Visualization visualization = curFocus.getVisualization();
                final TupleSet focusGroup = visualization.getFocusGroup(this.group);
                if (!mouseEvent.isControlDown()) {
                    focusGroup.setTuple(this.curFocus = curFocus);
                }
                else if (focusGroup.containsTuple(curFocus)) {
                    focusGroup.removeTuple(curFocus);
                }
                else {
                    focusGroup.addTuple(curFocus);
                }
                this.runActivity(visualization);
            }
            else if (mouseEvent.isControlDown()) {
                final Visualization visualization2 = curFocus.getVisualization();
                visualization2.getFocusGroup(this.group).removeTuple(curFocus);
                this.curFocus = null;
                this.runActivity(visualization2);
            }
        }
    }
    
    private void runActivity(final Visualization visualization) {
        if (this.activity != null) {
            visualization.run(this.activity);
        }
    }
}
