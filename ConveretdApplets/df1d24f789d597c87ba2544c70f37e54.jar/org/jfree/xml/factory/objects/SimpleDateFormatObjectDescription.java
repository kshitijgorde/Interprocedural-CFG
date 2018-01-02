// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import java.text.SimpleDateFormat;

public class SimpleDateFormatObjectDescription extends BeanObjectDescription
{
    static /* synthetic */ Class class$java$text$SimpleDateFormat;
    static /* synthetic */ Class class$java$util$Date;
    static /* synthetic */ Class class$java$util$Calendar;
    static /* synthetic */ Class class$java$text$DateFormatSymbols;
    static /* synthetic */ Class class$java$text$NumberFormat;
    static /* synthetic */ Class class$java$lang$String;
    
    public SimpleDateFormatObjectDescription() {
        this((SimpleDateFormatObjectDescription.class$java$text$SimpleDateFormat == null) ? (SimpleDateFormatObjectDescription.class$java$text$SimpleDateFormat = class$("java.text.SimpleDateFormat")) : SimpleDateFormatObjectDescription.class$java$text$SimpleDateFormat);
    }
    
    public SimpleDateFormatObjectDescription(final Class clazz) {
        this(clazz, true);
    }
    
    public SimpleDateFormatObjectDescription(final Class clazz, final boolean b) {
        super(clazz, false);
        this.setParameterDefinition("2DigitYearStart", (SimpleDateFormatObjectDescription.class$java$util$Date == null) ? (SimpleDateFormatObjectDescription.class$java$util$Date = class$("java.util.Date")) : SimpleDateFormatObjectDescription.class$java$util$Date);
        this.setParameterDefinition("calendar", (SimpleDateFormatObjectDescription.class$java$util$Calendar == null) ? (SimpleDateFormatObjectDescription.class$java$util$Calendar = class$("java.util.Calendar")) : SimpleDateFormatObjectDescription.class$java$util$Calendar);
        this.setParameterDefinition("dateFormatSymbols", (SimpleDateFormatObjectDescription.class$java$text$DateFormatSymbols == null) ? (SimpleDateFormatObjectDescription.class$java$text$DateFormatSymbols = class$("java.text.DateFormatSymbols")) : SimpleDateFormatObjectDescription.class$java$text$DateFormatSymbols);
        this.setParameterDefinition("lenient", Boolean.TYPE);
        this.setParameterDefinition("numberFormat", (SimpleDateFormatObjectDescription.class$java$text$NumberFormat == null) ? (SimpleDateFormatObjectDescription.class$java$text$NumberFormat = class$("java.text.NumberFormat")) : SimpleDateFormatObjectDescription.class$java$text$NumberFormat);
        this.setParameterDefinition("localizedPattern", (SimpleDateFormatObjectDescription.class$java$lang$String == null) ? (SimpleDateFormatObjectDescription.class$java$lang$String = class$("java.lang.String")) : SimpleDateFormatObjectDescription.class$java$lang$String);
        this.setParameterDefinition("pattern", (SimpleDateFormatObjectDescription.class$java$lang$String == null) ? (SimpleDateFormatObjectDescription.class$java$lang$String = class$("java.lang.String")) : SimpleDateFormatObjectDescription.class$java$lang$String);
        this.ignoreParameter("localizedPattern");
        this.ignoreParameter("pattern");
    }
    
    public void setParameterFromObject(final Object parameterFromObject) throws ObjectFactoryException {
        super.setParameterFromObject(parameterFromObject);
        final SimpleDateFormat simpleDateFormat = (SimpleDateFormat)parameterFromObject;
        this.setParameter("localizedPattern", simpleDateFormat.toLocalizedPattern());
        this.setParameter("pattern", simpleDateFormat.toPattern());
    }
    
    public Object createObject() {
        final SimpleDateFormat simpleDateFormat = (SimpleDateFormat)super.createObject();
        if (this.getParameter("pattern") != null) {
            simpleDateFormat.applyPattern((String)this.getParameter("pattern"));
        }
        if (this.getParameter("localizedPattern") != null) {
            simpleDateFormat.applyLocalizedPattern((String)this.getParameter("localizedPattern"));
        }
        return simpleDateFormat;
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
