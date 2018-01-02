// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import java.text.DecimalFormat;

public class DecimalFormatObjectDescription extends BeanObjectDescription
{
    static /* synthetic */ Class class$java$text$DecimalFormat;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$text$DecimalFormatSymbols;
    
    public DecimalFormatObjectDescription() {
        this((DecimalFormatObjectDescription.class$java$text$DecimalFormat == null) ? (DecimalFormatObjectDescription.class$java$text$DecimalFormat = class$("java.text.DecimalFormat")) : DecimalFormatObjectDescription.class$java$text$DecimalFormat);
    }
    
    public DecimalFormatObjectDescription(final Class clazz) {
        super(clazz, false);
        this.setParameterDefinition("localizedPattern", (DecimalFormatObjectDescription.class$java$lang$String == null) ? (DecimalFormatObjectDescription.class$java$lang$String = class$("java.lang.String")) : DecimalFormatObjectDescription.class$java$lang$String);
        this.setParameterDefinition("pattern", (DecimalFormatObjectDescription.class$java$lang$String == null) ? (DecimalFormatObjectDescription.class$java$lang$String = class$("java.lang.String")) : DecimalFormatObjectDescription.class$java$lang$String);
        this.setParameterDefinition("decimalFormatSymbols", (DecimalFormatObjectDescription.class$java$text$DecimalFormatSymbols == null) ? (DecimalFormatObjectDescription.class$java$text$DecimalFormatSymbols = class$("java.text.DecimalFormatSymbols")) : DecimalFormatObjectDescription.class$java$text$DecimalFormatSymbols);
        this.setParameterDefinition("decimalSeparatorAlwaysShown", Boolean.TYPE);
        this.setParameterDefinition("groupingSize", Integer.TYPE);
        this.setParameterDefinition("groupingUsed", Boolean.TYPE);
        this.setParameterDefinition("maximumFractionDigits", Integer.TYPE);
        this.setParameterDefinition("maximumIntegerDigits", Integer.TYPE);
        this.setParameterDefinition("minimumFractionDigits", Integer.TYPE);
        this.setParameterDefinition("minimumIntegerDigits", Integer.TYPE);
        this.setParameterDefinition("multiplier", Integer.TYPE);
        this.setParameterDefinition("negativePrefix", (DecimalFormatObjectDescription.class$java$lang$String == null) ? (DecimalFormatObjectDescription.class$java$lang$String = class$("java.lang.String")) : DecimalFormatObjectDescription.class$java$lang$String);
        this.setParameterDefinition("negativeSuffix", (DecimalFormatObjectDescription.class$java$lang$String == null) ? (DecimalFormatObjectDescription.class$java$lang$String = class$("java.lang.String")) : DecimalFormatObjectDescription.class$java$lang$String);
        this.setParameterDefinition("parseIntegerOnly", Boolean.TYPE);
        this.setParameterDefinition("positivePrefix", (DecimalFormatObjectDescription.class$java$lang$String == null) ? (DecimalFormatObjectDescription.class$java$lang$String = class$("java.lang.String")) : DecimalFormatObjectDescription.class$java$lang$String);
        this.setParameterDefinition("positiveSuffix", (DecimalFormatObjectDescription.class$java$lang$String == null) ? (DecimalFormatObjectDescription.class$java$lang$String = class$("java.lang.String")) : DecimalFormatObjectDescription.class$java$lang$String);
        this.ignoreParameter("localizedPattern");
        this.ignoreParameter("pattern");
    }
    
    public DecimalFormatObjectDescription(final Class clazz, final boolean b) {
        this(clazz);
    }
    
    public void setParameterFromObject(final Object parameterFromObject) throws ObjectFactoryException {
        super.setParameterFromObject(parameterFromObject);
        final DecimalFormat decimalFormat = (DecimalFormat)parameterFromObject;
        this.setParameter("localizedPattern", decimalFormat.toLocalizedPattern());
        this.setParameter("pattern", decimalFormat.toPattern());
    }
    
    public Object createObject() {
        final DecimalFormat decimalFormat = (DecimalFormat)super.createObject();
        if (this.getParameter("pattern") != null) {
            decimalFormat.applyPattern((String)this.getParameter("pattern"));
        }
        if (this.getParameter("localizedPattern") != null) {
            decimalFormat.applyLocalizedPattern((String)this.getParameter("localizedPattern"));
        }
        return decimalFormat;
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
