// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.calendar;

import org.xidget.IXidget;
import org.xidget.ifeature.model.ISingleValueWidgetFeature;

public class CalendarSingleValueWidgetFeature implements ISingleValueWidgetFeature
{
    private IXidget xidget;
    
    public CalendarSingleValueWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void setValue(final Object o) {
        final CalendarPanel calendarPanel = this.xidget.getFeature(CalendarPanel.class);
        if (o instanceof Number) {
            final long longValue = ((Number)o).longValue();
            if (calendarPanel.getTime() != longValue) {
                calendarPanel.setTime(longValue);
            }
        }
        else {
            try {
                final long time = (long)Double.parseDouble((o != null) ? o.toString() : "");
                if (calendarPanel.getTime() != time) {
                    calendarPanel.setTime(time);
                }
            }
            catch (NumberFormatException ex) {}
        }
    }
    
    @Override
    public Object getValue() {
        return this.xidget.getFeature(CalendarPanel.class).getTime();
    }
}
