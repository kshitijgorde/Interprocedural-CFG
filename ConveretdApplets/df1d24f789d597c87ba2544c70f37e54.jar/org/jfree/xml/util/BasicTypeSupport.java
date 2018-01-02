// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.util;

public class BasicTypeSupport
{
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$org$jfree$xml$attributehandlers$IntegerAttributeHandler;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$org$jfree$xml$attributehandlers$ShortAttributeHandler;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$org$jfree$xml$attributehandlers$LongAttributeHandler;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$org$jfree$xml$attributehandlers$BooleanAttributeHandler;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$org$jfree$xml$attributehandlers$FloatAttributeHandler;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$org$jfree$xml$attributehandlers$DoubleAttributeHandler;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$org$jfree$xml$attributehandlers$ByteAttributeHandler;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$jfree$xml$attributehandlers$StringAttributeHandler;
    
    public static String getHandlerClass(final Class clazz) {
        if (clazz.equals((BasicTypeSupport.class$java$lang$Integer == null) ? (BasicTypeSupport.class$java$lang$Integer = class$("java.lang.Integer")) : BasicTypeSupport.class$java$lang$Integer) || clazz.equals(Integer.TYPE)) {
            return ((BasicTypeSupport.class$org$jfree$xml$attributehandlers$IntegerAttributeHandler == null) ? (BasicTypeSupport.class$org$jfree$xml$attributehandlers$IntegerAttributeHandler = class$("org.jfree.xml.attributehandlers.IntegerAttributeHandler")) : BasicTypeSupport.class$org$jfree$xml$attributehandlers$IntegerAttributeHandler).getName();
        }
        if (clazz.equals((BasicTypeSupport.class$java$lang$Short == null) ? (BasicTypeSupport.class$java$lang$Short = class$("java.lang.Short")) : BasicTypeSupport.class$java$lang$Short) || clazz.equals(Short.TYPE)) {
            return ((BasicTypeSupport.class$org$jfree$xml$attributehandlers$ShortAttributeHandler == null) ? (BasicTypeSupport.class$org$jfree$xml$attributehandlers$ShortAttributeHandler = class$("org.jfree.xml.attributehandlers.ShortAttributeHandler")) : BasicTypeSupport.class$org$jfree$xml$attributehandlers$ShortAttributeHandler).getName();
        }
        if (clazz.equals((BasicTypeSupport.class$java$lang$Long == null) ? (BasicTypeSupport.class$java$lang$Long = class$("java.lang.Long")) : BasicTypeSupport.class$java$lang$Long) || clazz.equals(Long.TYPE)) {
            return ((BasicTypeSupport.class$org$jfree$xml$attributehandlers$LongAttributeHandler == null) ? (BasicTypeSupport.class$org$jfree$xml$attributehandlers$LongAttributeHandler = class$("org.jfree.xml.attributehandlers.LongAttributeHandler")) : BasicTypeSupport.class$org$jfree$xml$attributehandlers$LongAttributeHandler).getName();
        }
        if (clazz.equals((BasicTypeSupport.class$java$lang$Boolean == null) ? (BasicTypeSupport.class$java$lang$Boolean = class$("java.lang.Boolean")) : BasicTypeSupport.class$java$lang$Boolean) || clazz.equals(Boolean.TYPE)) {
            return ((BasicTypeSupport.class$org$jfree$xml$attributehandlers$BooleanAttributeHandler == null) ? (BasicTypeSupport.class$org$jfree$xml$attributehandlers$BooleanAttributeHandler = class$("org.jfree.xml.attributehandlers.BooleanAttributeHandler")) : BasicTypeSupport.class$org$jfree$xml$attributehandlers$BooleanAttributeHandler).getName();
        }
        if (clazz.equals((BasicTypeSupport.class$java$lang$Float == null) ? (BasicTypeSupport.class$java$lang$Float = class$("java.lang.Float")) : BasicTypeSupport.class$java$lang$Float) || clazz.equals(Float.TYPE)) {
            return ((BasicTypeSupport.class$org$jfree$xml$attributehandlers$FloatAttributeHandler == null) ? (BasicTypeSupport.class$org$jfree$xml$attributehandlers$FloatAttributeHandler = class$("org.jfree.xml.attributehandlers.FloatAttributeHandler")) : BasicTypeSupport.class$org$jfree$xml$attributehandlers$FloatAttributeHandler).getName();
        }
        if (clazz.equals((BasicTypeSupport.class$java$lang$Double == null) ? (BasicTypeSupport.class$java$lang$Double = class$("java.lang.Double")) : BasicTypeSupport.class$java$lang$Double) || clazz.equals(Double.TYPE)) {
            return ((BasicTypeSupport.class$org$jfree$xml$attributehandlers$DoubleAttributeHandler == null) ? (BasicTypeSupport.class$org$jfree$xml$attributehandlers$DoubleAttributeHandler = class$("org.jfree.xml.attributehandlers.DoubleAttributeHandler")) : BasicTypeSupport.class$org$jfree$xml$attributehandlers$DoubleAttributeHandler).getName();
        }
        if (clazz.equals((BasicTypeSupport.class$java$lang$Byte == null) ? (BasicTypeSupport.class$java$lang$Byte = class$("java.lang.Byte")) : BasicTypeSupport.class$java$lang$Byte) || clazz.equals(Byte.TYPE)) {
            return ((BasicTypeSupport.class$org$jfree$xml$attributehandlers$ByteAttributeHandler == null) ? (BasicTypeSupport.class$org$jfree$xml$attributehandlers$ByteAttributeHandler = class$("org.jfree.xml.attributehandlers.ByteAttributeHandler")) : BasicTypeSupport.class$org$jfree$xml$attributehandlers$ByteAttributeHandler).getName();
        }
        if (clazz.equals((BasicTypeSupport.class$java$lang$String == null) ? (BasicTypeSupport.class$java$lang$String = class$("java.lang.String")) : BasicTypeSupport.class$java$lang$String)) {
            return ((BasicTypeSupport.class$org$jfree$xml$attributehandlers$StringAttributeHandler == null) ? (BasicTypeSupport.class$org$jfree$xml$attributehandlers$StringAttributeHandler = class$("org.jfree.xml.attributehandlers.StringAttributeHandler")) : BasicTypeSupport.class$org$jfree$xml$attributehandlers$StringAttributeHandler).getName();
        }
        throw new IllegalArgumentException("BasicTypeSupport.getHandlerClass(Class): this is no attribute type.");
    }
    
    public static boolean isBasicDataType(final Class clazz) {
        return clazz.equals((BasicTypeSupport.class$java$lang$Integer == null) ? (BasicTypeSupport.class$java$lang$Integer = class$("java.lang.Integer")) : BasicTypeSupport.class$java$lang$Integer) || clazz.equals(Integer.TYPE) || (clazz.equals((BasicTypeSupport.class$java$lang$Short == null) ? (BasicTypeSupport.class$java$lang$Short = class$("java.lang.Short")) : BasicTypeSupport.class$java$lang$Short) || clazz.equals(Short.TYPE)) || (clazz.equals((BasicTypeSupport.class$java$lang$Long == null) ? (BasicTypeSupport.class$java$lang$Long = class$("java.lang.Long")) : BasicTypeSupport.class$java$lang$Long) || clazz.equals(Long.TYPE)) || (clazz.equals((BasicTypeSupport.class$java$lang$Boolean == null) ? (BasicTypeSupport.class$java$lang$Boolean = class$("java.lang.Boolean")) : BasicTypeSupport.class$java$lang$Boolean) || clazz.equals(Boolean.TYPE)) || (clazz.equals((BasicTypeSupport.class$java$lang$Float == null) ? (BasicTypeSupport.class$java$lang$Float = class$("java.lang.Float")) : BasicTypeSupport.class$java$lang$Float) || clazz.equals(Float.TYPE)) || (clazz.equals((BasicTypeSupport.class$java$lang$Double == null) ? (BasicTypeSupport.class$java$lang$Double = class$("java.lang.Double")) : BasicTypeSupport.class$java$lang$Double) || clazz.equals(Double.TYPE)) || (clazz.equals((BasicTypeSupport.class$java$lang$Byte == null) ? (BasicTypeSupport.class$java$lang$Byte = class$("java.lang.Byte")) : BasicTypeSupport.class$java$lang$Byte) || clazz.equals(Byte.TYPE)) || clazz.equals((BasicTypeSupport.class$java$lang$String == null) ? (BasicTypeSupport.class$java$lang$String = class$("java.lang.String")) : BasicTypeSupport.class$java$lang$String);
    }
    
    public static Class getClassRepresentation(final String s) {
        if (s.equals("::double")) {
            return Double.TYPE;
        }
        if (s.equals("::boolean")) {
            return Boolean.TYPE;
        }
        if (s.equals("::int")) {
            return Integer.TYPE;
        }
        if (s.equals("::short")) {
            return Short.TYPE;
        }
        if (s.equals("::long")) {
            return Long.TYPE;
        }
        if (s.equals("::byte")) {
            return Byte.TYPE;
        }
        throw new IllegalArgumentException("This is none of my primitives.");
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
