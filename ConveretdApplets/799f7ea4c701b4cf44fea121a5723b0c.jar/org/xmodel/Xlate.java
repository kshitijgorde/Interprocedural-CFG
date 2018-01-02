// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import org.xmodel.xpath.XPath;
import org.xmodel.xpath.expression.IExpression;

public class Xlate
{
    public static boolean get(final IModelObject modelObject, final boolean b) {
        if (modelObject == null) {
            return b;
        }
        final Object value = modelObject.getValue();
        if (value == null) {
            return b;
        }
        if (value instanceof Boolean) {
            return (boolean)value;
        }
        return Boolean.toString(true).equals(value.toString());
    }
    
    public static short get(final IModelObject modelObject, final short n) {
        if (modelObject == null) {
            return n;
        }
        final Object value = modelObject.getValue();
        if (value == null) {
            return n;
        }
        if (value instanceof Number) {
            return ((Number)value).shortValue();
        }
        try {
            return Short.parseShort(value.toString());
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static int get(final IModelObject modelObject, final int n) {
        if (modelObject == null) {
            return n;
        }
        final Object value = modelObject.getValue();
        if (value == null) {
            return n;
        }
        if (value instanceof Number) {
            return ((Number)value).intValue();
        }
        try {
            return Integer.parseInt(value.toString());
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static long get(final IModelObject modelObject, final long n) {
        if (modelObject == null) {
            return n;
        }
        final Object value = modelObject.getValue();
        if (value == null) {
            return n;
        }
        if (value instanceof Number) {
            return ((Number)value).longValue();
        }
        try {
            return Long.parseLong(value.toString());
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static float get(final IModelObject modelObject, final float n) {
        if (modelObject == null) {
            return n;
        }
        final Object value = modelObject.getValue();
        if (value == null) {
            return n;
        }
        if (value instanceof Number) {
            return ((Number)value).floatValue();
        }
        try {
            return Float.parseFloat(value.toString());
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static double get(final IModelObject modelObject, final double n) {
        if (modelObject == null) {
            return n;
        }
        final Object value = modelObject.getValue();
        if (value == null) {
            return n;
        }
        if (value instanceof Number) {
            return ((Number)value).doubleValue();
        }
        try {
            return Double.parseDouble(value.toString());
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static byte get(final IModelObject modelObject, final byte b) {
        if (modelObject == null) {
            return b;
        }
        final Object value = modelObject.getValue();
        if (value == null) {
            return b;
        }
        if (value instanceof Number) {
            return ((Number)value).byteValue();
        }
        try {
            return Byte.parseByte(value.toString());
        }
        catch (NumberFormatException ex) {
            return b;
        }
    }
    
    public static byte[] get(final IModelObject modelObject, final byte[] array) {
        if (modelObject == null) {
            return array;
        }
        final Object value = modelObject.getValue();
        if (value == null) {
            return array;
        }
        final char[] charArray = value.toString().toCharArray();
        final byte[] array2 = new byte[charArray.length / 2];
        for (int i = 0; i < charArray.length; i += 2) {
            array2[i] = (byte)(Character.digit(charArray[i], 16) << 4 & Character.digit(charArray[i + 1], 16));
        }
        return array2;
    }
    
    public static char get(final IModelObject modelObject, final char c) {
        if (modelObject == null) {
            return c;
        }
        final Object value = modelObject.getValue();
        if (value == null) {
            return c;
        }
        if (value instanceof Character) {
            return (char)value;
        }
        return value.toString().charAt(0);
    }
    
    public static char[] get(final IModelObject modelObject, final char[] array) {
        if (modelObject == null) {
            return array;
        }
        final Object value = modelObject.getValue();
        if (value == null) {
            return array;
        }
        return value.toString().toCharArray();
    }
    
    public static String get(final IModelObject modelObject, final String s) {
        if (modelObject == null) {
            return s;
        }
        final Object value = modelObject.getValue();
        if (value == null) {
            return s;
        }
        return value.toString();
    }
    
    public static IExpression get(final IModelObject modelObject, final IExpression expression) {
        if (modelObject == null) {
            return expression;
        }
        final Object value = modelObject.getValue();
        if (value == null) {
            return expression;
        }
        final IExpression expression2 = XPath.createExpression(value.toString());
        return (expression2 != null) ? expression2 : expression;
    }
    
    public static <T> T get(final IModelObject modelObject, final T t) {
        if (modelObject == null) {
            return t;
        }
        final Object value = modelObject.getValue();
        if (value == null) {
            return t;
        }
        return (T)value;
    }
    
    public static boolean get(final IModelObject modelObject, final String s, final boolean b) {
        if (modelObject == null) {
            return b;
        }
        final Object attribute = modelObject.getAttribute(s);
        if (attribute == null) {
            return b;
        }
        if (attribute instanceof Boolean) {
            return (boolean)attribute;
        }
        return Boolean.toString(true).equals(attribute.toString());
    }
    
    public static short get(final IModelObject modelObject, final String s, final short n) {
        if (modelObject == null) {
            return n;
        }
        final Object attribute = modelObject.getAttribute(s);
        if (attribute == null) {
            return n;
        }
        if (attribute instanceof Number) {
            return ((Number)attribute).shortValue();
        }
        try {
            return Short.parseShort(attribute.toString());
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static int get(final IModelObject modelObject, final String s, final int n) {
        if (modelObject == null) {
            return n;
        }
        final Object attribute = modelObject.getAttribute(s);
        if (attribute == null) {
            return n;
        }
        if (attribute instanceof Number) {
            return ((Number)attribute).intValue();
        }
        try {
            return Integer.parseInt(attribute.toString());
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static long get(final IModelObject modelObject, final String s, final long n) {
        if (modelObject == null) {
            return n;
        }
        final Object attribute = modelObject.getAttribute(s);
        if (attribute == null) {
            return n;
        }
        if (attribute instanceof Number) {
            return ((Number)attribute).longValue();
        }
        try {
            return Long.parseLong(attribute.toString());
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static float get(final IModelObject modelObject, final String s, final float n) {
        if (modelObject == null) {
            return n;
        }
        final Object attribute = modelObject.getAttribute(s);
        if (attribute == null) {
            return n;
        }
        if (attribute instanceof Number) {
            return ((Number)attribute).floatValue();
        }
        try {
            return Float.parseFloat(attribute.toString());
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static double get(final IModelObject modelObject, final String s, final double n) {
        if (modelObject == null) {
            return n;
        }
        final Object attribute = modelObject.getAttribute(s);
        if (attribute == null) {
            return n;
        }
        if (attribute instanceof Number) {
            return ((Number)attribute).doubleValue();
        }
        try {
            return Double.parseDouble(attribute.toString());
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static byte get(final IModelObject modelObject, final String s, final byte b) {
        if (modelObject == null) {
            return b;
        }
        final Object attribute = modelObject.getAttribute(s);
        if (attribute == null) {
            return b;
        }
        if (attribute instanceof Number) {
            return ((Number)attribute).byteValue();
        }
        try {
            return Byte.parseByte(attribute.toString());
        }
        catch (NumberFormatException ex) {
            return b;
        }
    }
    
    public static byte[] get(final IModelObject modelObject, final String s, final byte[] array) {
        if (modelObject == null) {
            return array;
        }
        final Object attribute = modelObject.getAttribute(s);
        if (attribute == null) {
            return array;
        }
        final char[] charArray = attribute.toString().toCharArray();
        final byte[] array2 = new byte[charArray.length / 2];
        for (int i = 0; i < charArray.length; i += 2) {
            array2[i] = (byte)(Character.digit(charArray[i], 16) << 4 & Character.digit(charArray[i + 1], 16));
        }
        return array2;
    }
    
    public static char get(final IModelObject modelObject, final String s, final char c) {
        if (modelObject == null) {
            return c;
        }
        final Object attribute = modelObject.getAttribute(s);
        if (attribute == null) {
            return c;
        }
        if (attribute instanceof Character) {
            return (char)attribute;
        }
        return attribute.toString().charAt(0);
    }
    
    public static char[] get(final IModelObject modelObject, final String s, final char[] array) {
        if (modelObject == null) {
            return array;
        }
        final Object attribute = modelObject.getAttribute(s);
        if (attribute == null) {
            return array;
        }
        return attribute.toString().toCharArray();
    }
    
    public static String get(final IModelObject modelObject, final String s, final String s2) {
        if (modelObject == null) {
            return s2;
        }
        final Object attribute = modelObject.getAttribute(s);
        if (attribute == null) {
            return s2;
        }
        return attribute.toString();
    }
    
    public static <T> T get(final IModelObject modelObject, final String s, final T t) {
        if (modelObject == null) {
            return t;
        }
        final Object attribute = modelObject.getAttribute(s);
        if (attribute == null) {
            return t;
        }
        return (T)attribute;
    }
    
    public static IExpression get(final IModelObject modelObject, final String s, final IExpression expression) {
        if (modelObject == null) {
            return expression;
        }
        final Object attribute = modelObject.getAttribute(s);
        if (attribute == null) {
            return expression;
        }
        final IExpression expression2 = XPath.createExpression(attribute.toString());
        return (expression2 != null) ? expression2 : expression;
    }
    
    public static boolean set(final IModelObject modelObject, final boolean b) {
        final boolean value = get(modelObject, false);
        modelObject.setValue(b);
        return value;
    }
    
    public static short set(final IModelObject modelObject, final short n) {
        final short value = get(modelObject, (short)0);
        modelObject.setValue(n);
        return value;
    }
    
    public static int set(final IModelObject modelObject, final int n) {
        final int value = get(modelObject, 0);
        modelObject.setValue(n);
        return value;
    }
    
    public static long set(final IModelObject modelObject, final long n) {
        final long value = get(modelObject, 0L);
        modelObject.setValue(n);
        return value;
    }
    
    public static float set(final IModelObject modelObject, final float n) {
        final float value = get(modelObject, 0.0f);
        modelObject.setValue(n);
        return value;
    }
    
    public static double set(final IModelObject modelObject, final double n) {
        final double value = get(modelObject, 0.0);
        modelObject.setValue(n);
        return value;
    }
    
    public static byte set(final IModelObject modelObject, final byte b) {
        final byte value = get(modelObject, b);
        modelObject.setValue(b);
        return value;
    }
    
    public static byte[] set(final IModelObject modelObject, final byte[] array) {
        final byte[] value = get(modelObject, (byte[])null);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Character.forDigit(array[i] >> 4 & 0xF, 16));
            sb.append(Character.forDigit(array[i] & 0xF, 16));
        }
        modelObject.setValue(sb.toString());
        return value;
    }
    
    public static char set(final IModelObject modelObject, final char c) {
        final char value = get(modelObject, '\0');
        modelObject.setValue(Character.toString(c));
        return value;
    }
    
    public static char[] set(final IModelObject modelObject, final char[] array) {
        final char[] value = get(modelObject, (char[])null);
        modelObject.setValue(new String(array));
        return value;
    }
    
    public static String set(final IModelObject modelObject, final String value) {
        final String value2 = get(modelObject, (String)null);
        modelObject.setValue(value);
        return value2;
    }
    
    public static <T> T set(final IModelObject modelObject, final T value) {
        final Object value2 = get(modelObject, (Object)null);
        modelObject.setValue(value);
        return (T)value2;
    }
    
    public static boolean set(final IModelObject modelObject, final String s, final boolean b) {
        final boolean value = get(modelObject, s, false);
        modelObject.setAttribute(s, b);
        return value;
    }
    
    public static short set(final IModelObject modelObject, final String s, final short n) {
        final short value = get(modelObject, s, (short)0);
        modelObject.setAttribute(s, n);
        return value;
    }
    
    public static int set(final IModelObject modelObject, final String s, final int n) {
        final int value = get(modelObject, s, 0);
        modelObject.setAttribute(s, n);
        return value;
    }
    
    public static long set(final IModelObject modelObject, final String s, final long n) {
        final long value = get(modelObject, s, 0L);
        modelObject.setAttribute(s, n);
        return value;
    }
    
    public static float set(final IModelObject modelObject, final String s, final float n) {
        final float value = get(modelObject, s, 0.0f);
        modelObject.setAttribute(s, n);
        return value;
    }
    
    public static double set(final IModelObject modelObject, final String s, final double n) {
        final double value = get(modelObject, s, 0.0);
        modelObject.setAttribute(s, n);
        return value;
    }
    
    public static byte set(final IModelObject modelObject, final String s, final byte b) {
        final byte value = get(modelObject, s, (byte)0);
        modelObject.setAttribute(s, b);
        return value;
    }
    
    public static byte[] set(final IModelObject modelObject, final String s, final byte[] array) {
        final byte[] value = get(modelObject, s, (byte[])null);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Character.forDigit(array[i] >> 4 & 0xF, 16));
            sb.append(Character.forDigit(array[i] & 0xF, 16));
        }
        modelObject.setAttribute(s, sb.toString());
        return value;
    }
    
    public static char set(final IModelObject modelObject, final String s, final char c) {
        final char value = get(modelObject, s, '\0');
        modelObject.setAttribute(s, Character.toString(c));
        return value;
    }
    
    public static char[] set(final IModelObject modelObject, final String s, final char[] array) {
        final char[] value = get(modelObject, s, (char[])null);
        modelObject.setAttribute(s, new String(array));
        return value;
    }
    
    public static String set(final IModelObject modelObject, final String s, final String s2) {
        final String value = get(modelObject, s, (String)null);
        modelObject.setAttribute(s, s2);
        return value;
    }
    
    public static <T> T set(final IModelObject modelObject, final String s, final T t) {
        final Object value = get(modelObject, s, (Object)null);
        modelObject.setAttribute(s, t);
        return (T)value;
    }
    
    public static boolean childGet(final IModelObject modelObject, final String s, final boolean b) {
        if (modelObject == null) {
            return b;
        }
        return get(modelObject.getFirstChild(s), b);
    }
    
    public static short childGet(final IModelObject modelObject, final String s, final short n) {
        if (modelObject == null) {
            return n;
        }
        return get(modelObject.getFirstChild(s), n);
    }
    
    public static int childGet(final IModelObject modelObject, final String s, final int n) {
        if (modelObject == null) {
            return n;
        }
        return get(modelObject.getFirstChild(s), n);
    }
    
    public static long childGet(final IModelObject modelObject, final String s, final long n) {
        if (modelObject == null) {
            return n;
        }
        return get(modelObject.getFirstChild(s), n);
    }
    
    public static float childGet(final IModelObject modelObject, final String s, final float n) {
        if (modelObject == null) {
            return n;
        }
        return get(modelObject.getFirstChild(s), n);
    }
    
    public static double childGet(final IModelObject modelObject, final String s, final double n) {
        if (modelObject == null) {
            return n;
        }
        return get(modelObject.getFirstChild(s), n);
    }
    
    public static byte childGet(final IModelObject modelObject, final String s, final byte b) {
        if (modelObject == null) {
            return b;
        }
        return get(modelObject.getFirstChild(s), b);
    }
    
    public static byte[] childGet(final IModelObject modelObject, final String s, final byte[] array) {
        if (modelObject == null) {
            return array;
        }
        return get(modelObject.getFirstChild(s), array);
    }
    
    public static char childGet(final IModelObject modelObject, final String s, final char c) {
        if (modelObject == null) {
            return c;
        }
        return get(modelObject.getFirstChild(s), c);
    }
    
    public static char[] childGet(final IModelObject modelObject, final String s, final char[] array) {
        if (modelObject == null) {
            return array;
        }
        return get(modelObject.getFirstChild(s), array);
    }
    
    public static String childGet(final IModelObject modelObject, final String s, final String s2) {
        if (modelObject == null) {
            return s2;
        }
        return get(modelObject.getFirstChild(s), s2);
    }
    
    public static IExpression childGet(final IModelObject modelObject, final String s, final IExpression expression) {
        if (modelObject == null) {
            return expression;
        }
        return get(modelObject.getFirstChild(s), expression);
    }
    
    public static <T> T childGet(final IModelObject modelObject, final String s, final T t) {
        if (modelObject == null) {
            return t;
        }
        return get(modelObject.getFirstChild(s), t);
    }
    
    public static boolean childSet(final IModelObject modelObject, final String s, final boolean b) {
        return set(modelObject.getCreateChild(s), b);
    }
    
    public static short childSet(final IModelObject modelObject, final String s, final short n) {
        return set(modelObject.getCreateChild(s), n);
    }
    
    public static int childSet(final IModelObject modelObject, final String s, final int n) {
        return set(modelObject.getCreateChild(s), n);
    }
    
    public static long childSet(final IModelObject modelObject, final String s, final long n) {
        return set(modelObject.getCreateChild(s), n);
    }
    
    public static float childSet(final IModelObject modelObject, final String s, final float n) {
        return set(modelObject.getCreateChild(s), n);
    }
    
    public static double childSet(final IModelObject modelObject, final String s, final double n) {
        return set(modelObject.getCreateChild(s), n);
    }
    
    public static byte childSet(final IModelObject modelObject, final String s, final byte b) {
        return set(modelObject.getCreateChild(s), b);
    }
    
    public static byte[] childSet(final IModelObject modelObject, final String s, final byte[] array) {
        return set(modelObject.getCreateChild(s), array);
    }
    
    public static char childSet(final IModelObject modelObject, final String s, final char c) {
        return set(modelObject.getCreateChild(s), c);
    }
    
    public static char[] childSet(final IModelObject modelObject, final String s, final char[] array) {
        return set(modelObject.getCreateChild(s), array);
    }
    
    public static String childSet(final IModelObject modelObject, final String s, final String s2) {
        return set(modelObject.getCreateChild(s), s2);
    }
    
    public static IExpression childSet(final IModelObject modelObject, final String s, final IExpression expression) {
        return set(modelObject.getCreateChild(s), expression);
    }
    
    public static <T> T childSet(final IModelObject modelObject, final String s, final T t) {
        return set(modelObject.getCreateChild(s), t);
    }
}
