// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import org.jfree.util.ObjectUtilities;
import org.jfree.ui.TextAnchor;
import java.util.Date;

public class DateTick extends ValueTick
{
    private Date date;
    
    public DateTick(final Date date, final String label, final TextAnchor textAnchor, final TextAnchor rotationAnchor, final double angle) {
        super(date.getTime(), label, textAnchor, rotationAnchor, angle);
        this.date = date;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DateTick && super.equals(obj)) {
            final DateTick dt = (DateTick)obj;
            return ObjectUtilities.equal(this.date, dt.date);
        }
        return false;
    }
    
    public int hashCode() {
        return this.date.hashCode();
    }
}
