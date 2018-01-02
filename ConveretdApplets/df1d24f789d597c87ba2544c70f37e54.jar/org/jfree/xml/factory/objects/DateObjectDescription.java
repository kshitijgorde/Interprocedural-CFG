// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import java.util.Date;
import java.util.GregorianCalendar;

public class DateObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$util$Date;
    static /* synthetic */ Class class$java$lang$Integer;
    
    public DateObjectDescription() {
        super((DateObjectDescription.class$java$util$Date == null) ? (DateObjectDescription.class$java$util$Date = class$("java.util.Date")) : DateObjectDescription.class$java$util$Date);
        this.setParameterDefinition("year", (DateObjectDescription.class$java$lang$Integer == null) ? (DateObjectDescription.class$java$lang$Integer = class$("java.lang.Integer")) : DateObjectDescription.class$java$lang$Integer);
        this.setParameterDefinition("month", (DateObjectDescription.class$java$lang$Integer == null) ? (DateObjectDescription.class$java$lang$Integer = class$("java.lang.Integer")) : DateObjectDescription.class$java$lang$Integer);
        this.setParameterDefinition("day", (DateObjectDescription.class$java$lang$Integer == null) ? (DateObjectDescription.class$java$lang$Integer = class$("java.lang.Integer")) : DateObjectDescription.class$java$lang$Integer);
    }
    
    public Object createObject() {
        return new GregorianCalendar(this.getIntParameter("year"), this.getIntParameter("month"), this.getIntParameter("day")).getTime();
    }
    
    private int getIntParameter(final String s) {
        final Integer n = (Integer)this.getParameter(s);
        if (n == null) {
            return 0;
        }
        return n;
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (o instanceof Date) {
            final GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime((Date)o);
            final int value = gregorianCalendar.get(1);
            final int value2 = gregorianCalendar.get(2);
            final int value3 = gregorianCalendar.get(5);
            this.setParameter("year", new Integer(value));
            this.setParameter("month", new Integer(value2));
            this.setParameter("day", new Integer(value3));
            return;
        }
        throw new ObjectFactoryException("Is no instance of java.util.Date");
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
