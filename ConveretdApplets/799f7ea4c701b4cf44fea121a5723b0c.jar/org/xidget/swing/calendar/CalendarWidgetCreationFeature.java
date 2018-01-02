// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.calendar;

import javax.swing.JComponent;
import org.xidget.IXidget;
import org.xidget.swing.feature.SwingWidgetCreationFeature;

public class CalendarWidgetCreationFeature extends SwingWidgetCreationFeature
{
    private CalendarPanel widget;
    
    public CalendarWidgetCreationFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    protected JComponent createSwingWidget() {
        return this.widget = new CalendarPanel(this.xidget);
    }
    
    @Override
    public Object[] getLastWidgets() {
        return new Object[] { this.widget };
    }
    
    public CalendarPanel getCalendarPanel() {
        return this.widget;
    }
}
