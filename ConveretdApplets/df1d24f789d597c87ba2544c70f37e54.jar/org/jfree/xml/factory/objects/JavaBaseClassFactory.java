// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

public class JavaBaseClassFactory extends ClassFactoryImpl
{
    static /* synthetic */ Class class$java$awt$Dimension;
    static /* synthetic */ Class class$java$awt$geom$Dimension2D;
    static /* synthetic */ Class class$org$jfree$ui$FloatDimension;
    static /* synthetic */ Class class$java$util$Date;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$awt$geom$Line2D;
    static /* synthetic */ Class class$java$awt$geom$Point2D;
    static /* synthetic */ Class class$java$awt$geom$Rectangle2D;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$awt$Color;
    static /* synthetic */ Class class$java$awt$BasicStroke;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$text$Format;
    static /* synthetic */ Class class$java$text$NumberFormat;
    static /* synthetic */ Class class$java$text$DecimalFormat;
    static /* synthetic */ Class class$java$text$DecimalFormatSymbols;
    static /* synthetic */ Class class$java$text$DateFormat;
    static /* synthetic */ Class class$java$text$SimpleDateFormat;
    static /* synthetic */ Class class$java$text$DateFormatSymbols;
    static /* synthetic */ Class class$java$util$ArrayList;
    static /* synthetic */ Class class$java$util$Vector;
    static /* synthetic */ Class class$java$util$HashSet;
    static /* synthetic */ Class class$java$util$TreeSet;
    static /* synthetic */ Class class$java$util$Set;
    static /* synthetic */ Class class$java$util$List;
    static /* synthetic */ Class class$java$util$Collection;
    
    public JavaBaseClassFactory() {
        this.registerClass((JavaBaseClassFactory.class$java$awt$Dimension == null) ? (JavaBaseClassFactory.class$java$awt$Dimension = class$("java.awt.Dimension")) : JavaBaseClassFactory.class$java$awt$Dimension, new DimensionObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$awt$geom$Dimension2D == null) ? (JavaBaseClassFactory.class$java$awt$geom$Dimension2D = class$("java.awt.geom.Dimension2D")) : JavaBaseClassFactory.class$java$awt$geom$Dimension2D, new Dimension2DObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$org$jfree$ui$FloatDimension == null) ? (JavaBaseClassFactory.class$org$jfree$ui$FloatDimension = class$("org.jfree.ui.FloatDimension")) : JavaBaseClassFactory.class$org$jfree$ui$FloatDimension, new BeanObjectDescription((JavaBaseClassFactory.class$org$jfree$ui$FloatDimension == null) ? (JavaBaseClassFactory.class$org$jfree$ui$FloatDimension = class$("org.jfree.ui.FloatDimension")) : JavaBaseClassFactory.class$org$jfree$ui$FloatDimension));
        this.registerClass((JavaBaseClassFactory.class$java$util$Date == null) ? (JavaBaseClassFactory.class$java$util$Date = class$("java.util.Date")) : JavaBaseClassFactory.class$java$util$Date, new DateObjectDescription());
        this.registerClass(Boolean.TYPE, new BooleanObjectDescription());
        this.registerClass(Byte.TYPE, new ByteObjectDescription());
        this.registerClass(Double.TYPE, new DoubleObjectDescription());
        this.registerClass(Float.TYPE, new FloatObjectDescription());
        this.registerClass(Integer.TYPE, new IntegerObjectDescription());
        this.registerClass(Long.TYPE, new LongObjectDescription());
        this.registerClass(Short.TYPE, new ShortObjectDescription());
        this.registerClass(Character.TYPE, new CharacterObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$lang$Character == null) ? (JavaBaseClassFactory.class$java$lang$Character = class$("java.lang.Character")) : JavaBaseClassFactory.class$java$lang$Character, new CharacterObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$lang$Boolean == null) ? (JavaBaseClassFactory.class$java$lang$Boolean = class$("java.lang.Boolean")) : JavaBaseClassFactory.class$java$lang$Boolean, new BooleanObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$lang$Byte == null) ? (JavaBaseClassFactory.class$java$lang$Byte = class$("java.lang.Byte")) : JavaBaseClassFactory.class$java$lang$Byte, new ByteObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$lang$Double == null) ? (JavaBaseClassFactory.class$java$lang$Double = class$("java.lang.Double")) : JavaBaseClassFactory.class$java$lang$Double, new DoubleObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$lang$Float == null) ? (JavaBaseClassFactory.class$java$lang$Float = class$("java.lang.Float")) : JavaBaseClassFactory.class$java$lang$Float, new FloatObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$lang$Integer == null) ? (JavaBaseClassFactory.class$java$lang$Integer = class$("java.lang.Integer")) : JavaBaseClassFactory.class$java$lang$Integer, new IntegerObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$lang$Long == null) ? (JavaBaseClassFactory.class$java$lang$Long = class$("java.lang.Long")) : JavaBaseClassFactory.class$java$lang$Long, new LongObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$lang$Short == null) ? (JavaBaseClassFactory.class$java$lang$Short = class$("java.lang.Short")) : JavaBaseClassFactory.class$java$lang$Short, new ShortObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$awt$geom$Line2D == null) ? (JavaBaseClassFactory.class$java$awt$geom$Line2D = class$("java.awt.geom.Line2D")) : JavaBaseClassFactory.class$java$awt$geom$Line2D, new Line2DObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$awt$geom$Point2D == null) ? (JavaBaseClassFactory.class$java$awt$geom$Point2D = class$("java.awt.geom.Point2D")) : JavaBaseClassFactory.class$java$awt$geom$Point2D, new Point2DObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$awt$geom$Rectangle2D == null) ? (JavaBaseClassFactory.class$java$awt$geom$Rectangle2D = class$("java.awt.geom.Rectangle2D")) : JavaBaseClassFactory.class$java$awt$geom$Rectangle2D, new Rectangle2DObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$lang$String == null) ? (JavaBaseClassFactory.class$java$lang$String = class$("java.lang.String")) : JavaBaseClassFactory.class$java$lang$String, new StringObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$awt$Color == null) ? (JavaBaseClassFactory.class$java$awt$Color = class$("java.awt.Color")) : JavaBaseClassFactory.class$java$awt$Color, new ColorObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$awt$BasicStroke == null) ? (JavaBaseClassFactory.class$java$awt$BasicStroke = class$("java.awt.BasicStroke")) : JavaBaseClassFactory.class$java$awt$BasicStroke, new BasicStrokeObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$lang$Object == null) ? (JavaBaseClassFactory.class$java$lang$Object = class$("java.lang.Object")) : JavaBaseClassFactory.class$java$lang$Object, new ClassLoaderObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$text$Format == null) ? (JavaBaseClassFactory.class$java$text$Format = class$("java.text.Format")) : JavaBaseClassFactory.class$java$text$Format, new ClassLoaderObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$text$NumberFormat == null) ? (JavaBaseClassFactory.class$java$text$NumberFormat = class$("java.text.NumberFormat")) : JavaBaseClassFactory.class$java$text$NumberFormat, this.createNumberFormatDescription());
        this.registerClass((JavaBaseClassFactory.class$java$text$DecimalFormat == null) ? (JavaBaseClassFactory.class$java$text$DecimalFormat = class$("java.text.DecimalFormat")) : JavaBaseClassFactory.class$java$text$DecimalFormat, new DecimalFormatObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$text$DecimalFormatSymbols == null) ? (JavaBaseClassFactory.class$java$text$DecimalFormatSymbols = class$("java.text.DecimalFormatSymbols")) : JavaBaseClassFactory.class$java$text$DecimalFormatSymbols, this.createDecimalFormatSymbols());
        this.registerClass((JavaBaseClassFactory.class$java$text$DateFormat == null) ? (JavaBaseClassFactory.class$java$text$DateFormat = class$("java.text.DateFormat")) : JavaBaseClassFactory.class$java$text$DateFormat, new ClassLoaderObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$text$SimpleDateFormat == null) ? (JavaBaseClassFactory.class$java$text$SimpleDateFormat = class$("java.text.SimpleDateFormat")) : JavaBaseClassFactory.class$java$text$SimpleDateFormat, new SimpleDateFormatObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$text$DateFormatSymbols == null) ? (JavaBaseClassFactory.class$java$text$DateFormatSymbols = class$("java.text.DateFormatSymbols")) : JavaBaseClassFactory.class$java$text$DateFormatSymbols, new ClassLoaderObjectDescription());
        this.registerClass((JavaBaseClassFactory.class$java$util$ArrayList == null) ? (JavaBaseClassFactory.class$java$util$ArrayList = class$("java.util.ArrayList")) : JavaBaseClassFactory.class$java$util$ArrayList, new CollectionObjectDescription((JavaBaseClassFactory.class$java$util$ArrayList == null) ? (JavaBaseClassFactory.class$java$util$ArrayList = class$("java.util.ArrayList")) : JavaBaseClassFactory.class$java$util$ArrayList));
        this.registerClass((JavaBaseClassFactory.class$java$util$Vector == null) ? (JavaBaseClassFactory.class$java$util$Vector = class$("java.util.Vector")) : JavaBaseClassFactory.class$java$util$Vector, new CollectionObjectDescription((JavaBaseClassFactory.class$java$util$Vector == null) ? (JavaBaseClassFactory.class$java$util$Vector = class$("java.util.Vector")) : JavaBaseClassFactory.class$java$util$Vector));
        this.registerClass((JavaBaseClassFactory.class$java$util$HashSet == null) ? (JavaBaseClassFactory.class$java$util$HashSet = class$("java.util.HashSet")) : JavaBaseClassFactory.class$java$util$HashSet, new CollectionObjectDescription((JavaBaseClassFactory.class$java$util$HashSet == null) ? (JavaBaseClassFactory.class$java$util$HashSet = class$("java.util.HashSet")) : JavaBaseClassFactory.class$java$util$HashSet));
        this.registerClass((JavaBaseClassFactory.class$java$util$TreeSet == null) ? (JavaBaseClassFactory.class$java$util$TreeSet = class$("java.util.TreeSet")) : JavaBaseClassFactory.class$java$util$TreeSet, new CollectionObjectDescription((JavaBaseClassFactory.class$java$util$TreeSet == null) ? (JavaBaseClassFactory.class$java$util$TreeSet = class$("java.util.TreeSet")) : JavaBaseClassFactory.class$java$util$TreeSet));
        this.registerClass((JavaBaseClassFactory.class$java$util$Set == null) ? (JavaBaseClassFactory.class$java$util$Set = class$("java.util.Set")) : JavaBaseClassFactory.class$java$util$Set, new CollectionObjectDescription((JavaBaseClassFactory.class$java$util$HashSet == null) ? (JavaBaseClassFactory.class$java$util$HashSet = class$("java.util.HashSet")) : JavaBaseClassFactory.class$java$util$HashSet));
        this.registerClass((JavaBaseClassFactory.class$java$util$List == null) ? (JavaBaseClassFactory.class$java$util$List = class$("java.util.List")) : JavaBaseClassFactory.class$java$util$List, new CollectionObjectDescription((JavaBaseClassFactory.class$java$util$ArrayList == null) ? (JavaBaseClassFactory.class$java$util$ArrayList = class$("java.util.ArrayList")) : JavaBaseClassFactory.class$java$util$ArrayList));
        this.registerClass((JavaBaseClassFactory.class$java$util$Collection == null) ? (JavaBaseClassFactory.class$java$util$Collection = class$("java.util.Collection")) : JavaBaseClassFactory.class$java$util$Collection, new CollectionObjectDescription((JavaBaseClassFactory.class$java$util$ArrayList == null) ? (JavaBaseClassFactory.class$java$util$ArrayList = class$("java.util.ArrayList")) : JavaBaseClassFactory.class$java$util$ArrayList));
    }
    
    private ObjectDescription createNumberFormatDescription() {
        final BeanObjectDescription beanObjectDescription = new BeanObjectDescription((JavaBaseClassFactory.class$java$text$NumberFormat == null) ? (JavaBaseClassFactory.class$java$text$NumberFormat = class$("java.text.NumberFormat")) : JavaBaseClassFactory.class$java$text$NumberFormat, false);
        beanObjectDescription.setParameterDefinition("groupingUsed", Boolean.TYPE);
        beanObjectDescription.setParameterDefinition("maximumFractionDigits", Integer.TYPE);
        beanObjectDescription.setParameterDefinition("minimumFractionDigits", Integer.TYPE);
        beanObjectDescription.setParameterDefinition("maximumIntegerDigits", Integer.TYPE);
        beanObjectDescription.setParameterDefinition("minimumIntegerDigits", Integer.TYPE);
        beanObjectDescription.setParameterDefinition("parseIntegerOnly", Boolean.TYPE);
        return beanObjectDescription;
    }
    
    private ObjectDescription createDecimalFormatSymbols() {
        final BeanObjectDescription beanObjectDescription = new BeanObjectDescription((JavaBaseClassFactory.class$java$text$DecimalFormatSymbols == null) ? (JavaBaseClassFactory.class$java$text$DecimalFormatSymbols = class$("java.text.DecimalFormatSymbols")) : JavaBaseClassFactory.class$java$text$DecimalFormatSymbols, false);
        beanObjectDescription.setParameterDefinition("currencySymbol", (JavaBaseClassFactory.class$java$lang$String == null) ? (JavaBaseClassFactory.class$java$lang$String = class$("java.lang.String")) : JavaBaseClassFactory.class$java$lang$String);
        beanObjectDescription.setParameterDefinition("decimalSeparator", Character.TYPE);
        beanObjectDescription.setParameterDefinition("digit", Character.TYPE);
        beanObjectDescription.setParameterDefinition("groupingSeparator", Character.TYPE);
        beanObjectDescription.setParameterDefinition("infinity", (JavaBaseClassFactory.class$java$lang$String == null) ? (JavaBaseClassFactory.class$java$lang$String = class$("java.lang.String")) : JavaBaseClassFactory.class$java$lang$String);
        beanObjectDescription.setParameterDefinition("internationalCurrencySymbol", (JavaBaseClassFactory.class$java$lang$String == null) ? (JavaBaseClassFactory.class$java$lang$String = class$("java.lang.String")) : JavaBaseClassFactory.class$java$lang$String);
        beanObjectDescription.setParameterDefinition("minusSign", Character.TYPE);
        beanObjectDescription.setParameterDefinition("monetaryDecimalSeparator", Character.TYPE);
        beanObjectDescription.setParameterDefinition("naN", (JavaBaseClassFactory.class$java$lang$String == null) ? (JavaBaseClassFactory.class$java$lang$String = class$("java.lang.String")) : JavaBaseClassFactory.class$java$lang$String);
        beanObjectDescription.setParameterDefinition("patternSeparator", Character.TYPE);
        beanObjectDescription.setParameterDefinition("perMill", Character.TYPE);
        beanObjectDescription.setParameterDefinition("percent", Character.TYPE);
        beanObjectDescription.setParameterDefinition("zeroDigit", Character.TYPE);
        return beanObjectDescription;
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
