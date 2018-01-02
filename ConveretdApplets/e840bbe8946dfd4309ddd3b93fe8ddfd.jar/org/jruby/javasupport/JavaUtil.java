// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.RubyBigDecimal;
import java.util.HashMap;
import org.jruby.RubyBoolean;
import java.io.UnsupportedEncodingException;
import org.jruby.RubyTime;
import org.jruby.RubyFloat;
import org.jruby.RubyFixnum;
import org.jruby.RubyNil;
import java.math.BigDecimal;
import org.jruby.RubyNumeric;
import org.jruby.RubyBignum;
import java.math.BigInteger;
import org.jruby.util.ByteList;
import org.jruby.RubyEncoding;
import org.jruby.util.TypeConverter;
import org.jruby.RubyString;
import org.jruby.javasupport.proxy.InternalJavaProxy;
import org.jruby.java.proxies.RubyObjectHolderProxy;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import org.jruby.java.proxies.JavaProxy;
import org.jruby.RubyClass;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.runtime.Visibility;
import org.jruby.RubyProc;
import org.jruby.RubyModule;
import org.jruby.RubyBasicObject;
import org.jruby.RubyObject;
import org.jruby.java.proxies.ArrayJavaProxy;
import java.lang.reflect.Array;
import org.jruby.RubyArray;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import java.util.Map;
import java.util.regex.Pattern;

public class JavaUtil
{
    private static final Pattern RUBY_CASE_SPLITTER;
    private static final JavaConverter JAVA_DEFAULT_CONVERTER;
    private static final JavaConverter JAVA_BOOLEAN_CONVERTER;
    private static final JavaConverter JAVA_FLOAT_CONVERTER;
    private static final JavaConverter JAVA_DOUBLE_CONVERTER;
    private static final JavaConverter JAVA_CHAR_CONVERTER;
    private static final JavaConverter JAVA_BYTE_CONVERTER;
    private static final JavaConverter JAVA_SHORT_CONVERTER;
    private static final JavaConverter JAVA_INT_CONVERTER;
    private static final JavaConverter JAVA_LONG_CONVERTER;
    private static final JavaConverter JAVA_BOOLEANPRIM_CONVERTER;
    private static final JavaConverter JAVA_FLOATPRIM_CONVERTER;
    private static final JavaConverter JAVA_DOUBLEPRIM_CONVERTER;
    private static final JavaConverter JAVA_CHARPRIM_CONVERTER;
    private static final JavaConverter JAVA_BYTEPRIM_CONVERTER;
    private static final JavaConverter JAVA_SHORTPRIM_CONVERTER;
    private static final JavaConverter JAVA_INTPRIM_CONVERTER;
    private static final JavaConverter JAVA_LONGPRIM_CONVERTER;
    private static final JavaConverter JAVA_STRING_CONVERTER;
    private static final JavaConverter BYTELIST_CONVERTER;
    private static final JavaConverter JAVA_BIGINTEGER_CONVERTER;
    private static final Map<Class, JavaConverter> JAVA_CONVERTERS;
    private static final NumericConverter NUMERIC_TO_BYTE;
    private static final NumericConverter NUMERIC_TO_SHORT;
    private static final NumericConverter NUMERIC_TO_CHARACTER;
    private static final NumericConverter NUMERIC_TO_INTEGER;
    private static final NumericConverter NUMERIC_TO_LONG;
    private static final NumericConverter NUMERIC_TO_FLOAT;
    private static final NumericConverter NUMERIC_TO_DOUBLE;
    private static final NumericConverter NUMERIC_TO_BIGINTEGER;
    private static final NumericConverter NUMERIC_TO_OBJECT;
    private static final NumericConverter NUMERIC_TO_OTHER;
    private static final NumericConverter NUMERIC_TO_VOID;
    private static final Map<Class, NumericConverter> NUMERIC_CONVERTERS;
    @Deprecated
    public static final RubyConverter RUBY_BOOLEAN_CONVERTER;
    @Deprecated
    public static final RubyConverter RUBY_BYTE_CONVERTER;
    @Deprecated
    public static final RubyConverter RUBY_SHORT_CONVERTER;
    @Deprecated
    public static final RubyConverter RUBY_CHAR_CONVERTER;
    @Deprecated
    public static final RubyConverter RUBY_INTEGER_CONVERTER;
    @Deprecated
    public static final RubyConverter RUBY_LONG_CONVERTER;
    @Deprecated
    public static final RubyConverter RUBY_FLOAT_CONVERTER;
    @Deprecated
    public static final RubyConverter RUBY_DOUBLE_CONVERTER;
    @Deprecated
    public static final Map<Class, RubyConverter> RUBY_CONVERTERS;
    @Deprecated
    public static final RubyConverter ARRAY_BOOLEAN_CONVERTER;
    @Deprecated
    public static final RubyConverter ARRAY_BYTE_CONVERTER;
    @Deprecated
    public static final RubyConverter ARRAY_SHORT_CONVERTER;
    @Deprecated
    public static final RubyConverter ARRAY_CHAR_CONVERTER;
    @Deprecated
    public static final RubyConverter ARRAY_INT_CONVERTER;
    @Deprecated
    public static final RubyConverter ARRAY_LONG_CONVERTER;
    @Deprecated
    public static final RubyConverter ARRAY_FLOAT_CONVERTER;
    @Deprecated
    public static final RubyConverter ARRAY_DOUBLE_CONVERTER;
    @Deprecated
    public static final RubyConverter ARRAY_OBJECT_CONVERTER;
    @Deprecated
    public static final RubyConverter ARRAY_CLASS_CONVERTER;
    @Deprecated
    public static final RubyConverter ARRAY_STRING_CONVERTER;
    @Deprecated
    public static final RubyConverter ARRAY_BIGINTEGER_CONVERTER;
    @Deprecated
    public static final RubyConverter ARRAY_BIGDECIMAL_CONVERTER;
    @Deprecated
    public static final Map<Class, RubyConverter> ARRAY_CONVERTERS;
    
    public static IRubyObject[] convertJavaArrayToRuby(final Ruby runtime, final Object[] objects) {
        if (objects == null) {
            return IRubyObject.NULL_ARRAY;
        }
        final IRubyObject[] rubyObjects = new IRubyObject[objects.length];
        for (int i = 0; i < objects.length; ++i) {
            rubyObjects[i] = convertJavaToUsableRubyObject(runtime, objects[i]);
        }
        return rubyObjects;
    }
    
    public static RubyArray convertJavaArrayToRubyWithNesting(final ThreadContext context, final Object array) {
        final int length = Array.getLength(array);
        final RubyArray outer = context.runtime.newArray(length);
        for (int i = 0; i < length; ++i) {
            final Object element = Array.get(array, i);
            if (element instanceof ArrayJavaProxy) {
                outer.append(convertJavaArrayToRubyWithNesting(context, ((ArrayJavaProxy)element).getObject()));
            }
            else {
                outer.append(convertJavaToUsableRubyObject(context.runtime, element));
            }
        }
        return outer;
    }
    
    public static JavaConverter getJavaConverter(final Class clazz) {
        JavaConverter converter = JavaUtil.JAVA_CONVERTERS.get(clazz);
        if (converter == null) {
            converter = JavaUtil.JAVA_DEFAULT_CONVERTER;
        }
        return converter;
    }
    
    public static IRubyObject convertJavaToRuby(final Ruby runtime, final Object object) {
        return convertJavaToUsableRubyObject(runtime, object);
    }
    
    public static IRubyObject convertJavaToRuby(final Ruby runtime, final Object object, final Class javaClass) {
        return convertJavaToUsableRubyObjectWithConverter(runtime, object, getJavaConverter(javaClass));
    }
    
    public static IRubyObject convertJavaToRuby(final Ruby runtime, final int i) {
        return runtime.newFixnum(i);
    }
    
    public static IRubyObject convertJavaToRuby(final Ruby runtime, final long l) {
        return runtime.newFixnum(l);
    }
    
    public static IRubyObject convertJavaToRuby(final Ruby runtime, final float f) {
        return runtime.newFloat(f);
    }
    
    public static IRubyObject convertJavaToRuby(final Ruby runtime, final double d) {
        return runtime.newFloat(d);
    }
    
    public static IRubyObject convertJavaToRuby(final Ruby runtime, final boolean b) {
        return runtime.newBoolean(b);
    }
    
    public static IRubyObject convertJavaToUsableRubyObject(final Ruby runtime, final Object object) {
        final IRubyObject result = trySimpleConversions(runtime, object);
        if (result != null) {
            return result;
        }
        final JavaConverter converter = getJavaConverter(object.getClass());
        if (converter == null || converter == JavaUtil.JAVA_DEFAULT_CONVERTER) {
            return Java.getInstance(runtime, object);
        }
        return converter.convert(runtime, object);
    }
    
    public static IRubyObject convertJavaToUsableRubyObjectWithConverter(final Ruby runtime, final Object object, final JavaConverter converter) {
        final IRubyObject result = trySimpleConversions(runtime, object);
        if (result != null) {
            return result;
        }
        if (converter == null || converter == JavaUtil.JAVA_DEFAULT_CONVERTER) {
            return Java.getInstance(runtime, object);
        }
        return converter.convert(runtime, object);
    }
    
    public static IRubyObject convertJavaArrayElementToRuby(final Ruby runtime, final JavaConverter converter, final Object array, final int i) {
        if (converter == null || converter == JavaUtil.JAVA_DEFAULT_CONVERTER) {
            final IRubyObject x = convertJavaToUsableRubyObject(runtime, ((Object[])array)[i]);
            return x;
        }
        return converter.get(runtime, array, i);
    }
    
    public static Class<?> primitiveToWrapper(final Class<?> type) {
        if (type.isPrimitive()) {
            if (type == Boolean.TYPE) {
                return Boolean.class;
            }
            if (type == Byte.TYPE) {
                return Byte.class;
            }
            if (type == Short.TYPE) {
                return Short.class;
            }
            if (type == Character.TYPE) {
                return Character.class;
            }
            if (type == Integer.TYPE) {
                return Integer.class;
            }
            if (type == Long.TYPE) {
                return Long.class;
            }
            if (type == Float.TYPE) {
                return Float.class;
            }
            if (type == Double.TYPE) {
                return Double.class;
            }
            if (type == Void.TYPE) {
                return Void.class;
            }
        }
        return type;
    }
    
    public static boolean isDuckTypeConvertable(final Class providedArgumentType, final Class parameterType) {
        return parameterType.isInterface() && !parameterType.isAssignableFrom(providedArgumentType) && RubyObject.class.isAssignableFrom(providedArgumentType);
    }
    
    public static Object convertProcToInterface(final ThreadContext context, final RubyObject rubyObject, final Class target) {
        return convertProcToInterface(context, (RubyBasicObject)rubyObject, target);
    }
    
    public static Object convertProcToInterface(final ThreadContext context, final RubyBasicObject rubyObject, final Class target) {
        final Ruby runtime = context.getRuntime();
        final RubyModule javaInterfaceModule = (RubyModule)Java.get_interface_module(runtime, JavaClass.get(runtime, target));
        if (!javaInterfaceModule.isInstance(rubyObject)) {
            javaInterfaceModule.callMethod(context, "extend_object", rubyObject);
            javaInterfaceModule.callMethod(context, "extended", rubyObject);
        }
        if (rubyObject instanceof RubyProc) {
            final RubyClass singletonClass = rubyObject.getSingletonClass();
            singletonClass.addMethod("method_missing", new DynamicMethod(singletonClass, Visibility.PUBLIC, CallConfiguration.FrameNoneScopeNone) {
                public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
                    if (!(self instanceof RubyProc)) {
                        throw context.getRuntime().newTypeError("interface impl method_missing for block used with non-Proc object");
                    }
                    final RubyProc proc = (RubyProc)self;
                    IRubyObject[] newArgs;
                    if (args.length == 1) {
                        newArgs = IRubyObject.NULL_ARRAY;
                    }
                    else {
                        newArgs = new IRubyObject[args.length - 1];
                        System.arraycopy(args, 1, newArgs, 0, args.length - 1);
                    }
                    return proc.call(context, newArgs);
                }
                
                public DynamicMethod dup() {
                    return this;
                }
            });
        }
        final JavaObject jo = (JavaObject)RuntimeHelpers.invoke(context, rubyObject, "__jcreate_meta!");
        return jo.getValue();
    }
    
    public static NumericConverter getNumericConverter(final Class target) {
        final NumericConverter converter = JavaUtil.NUMERIC_CONVERTERS.get(target);
        if (converter == null) {
            return JavaUtil.NUMERIC_TO_OTHER;
        }
        return converter;
    }
    
    public static boolean isJavaObject(final IRubyObject candidate) {
        return candidate instanceof JavaProxy || candidate.dataGetStruct() instanceof JavaObject;
    }
    
    public static Object unwrapJavaObject(final IRubyObject object) {
        if (object instanceof JavaProxy) {
            return ((JavaProxy)object).getObject();
        }
        return ((JavaObject)object.dataGetStruct()).getValue();
    }
    
    public static Object unwrapJavaValue(final Ruby runtime, final IRubyObject obj, final String errorMessage) {
        if (obj instanceof JavaProxy) {
            return ((JavaProxy)obj).getObject();
        }
        if (obj instanceof JavaObject) {
            return ((JavaObject)obj).getValue();
        }
        if (obj.dataGetStruct() != null && obj.dataGetStruct() instanceof IRubyObject) {
            return unwrapJavaValue(runtime, (IRubyObject)obj.dataGetStruct(), errorMessage);
        }
        throw runtime.newTypeError(errorMessage);
    }
    
    public static String getJavaPropertyName(final String beanMethodName) {
        final int length = beanMethodName.length();
        if ((beanMethodName.startsWith("get") || beanMethodName.startsWith("set")) && length > 3) {
            final char ch;
            if (isUpperDigit(ch = beanMethodName.charAt(3))) {
                if (length == 4) {
                    return Character.toString(Character.toLowerCase(ch));
                }
                return "" + Character.toLowerCase(ch) + beanMethodName.substring(4);
            }
        }
        else {
            final char ch;
            if (beanMethodName.startsWith("is") && length > 2 && isUpperDigit(ch = beanMethodName.charAt(2))) {
                if (length == 3) {
                    return Character.toString(Character.toLowerCase(ch));
                }
                return "" + Character.toLowerCase(ch) + beanMethodName.substring(3);
            }
        }
        return null;
    }
    
    public static String getRubyCasedName(final String javaCasedName) {
        final StringBuilder b = new StringBuilder();
        final char[] chars = javaCasedName.toCharArray();
        int behind = 0;
        for (int i = 0; i < chars.length; ++i) {
            if (behind < 2) {
                ++behind;
            }
            else {
                behind = consume(b, chars, i);
            }
        }
        if (behind == 2) {
            b.append(Character.toLowerCase(chars[chars.length - 2]));
            if (Character.isUpperCase(chars[chars.length - 1]) && !Character.isUpperCase(chars[chars.length - 2])) {
                b.append('_');
            }
            b.append(Character.toLowerCase(chars[chars.length - 1]));
        }
        else if (behind > 0) {
            if (behind > 1) {
                b.append(Character.toLowerCase(chars[chars.length - 2]));
            }
            b.append(Character.toLowerCase(chars[chars.length - 1]));
        }
        return b.toString();
    }
    
    private static int consume(final StringBuilder b, final char[] chars, final int i) {
        final char prev2;
        char prev3;
        if (isLowerDigit(prev2 = chars[i - 2]) && Character.isUpperCase(prev3 = chars[i - 1])) {
            b.append(prev2).append('_').append(Character.toLowerCase(prev3));
            return 1;
        }
        final char cur;
        if (isLetterDigit(prev2) && Character.isUpperCase(prev3 = chars[i - 1]) && Character.isLowerCase(cur = chars[i])) {
            b.append(Character.toLowerCase(prev2)).append('_').append(Character.toLowerCase(prev3)).append(cur);
            return 0;
        }
        b.append(Character.toLowerCase(prev2));
        return 2;
    }
    
    private static boolean isUpperDigit(final char c) {
        return Character.isUpperCase(c) || Character.isDigit(c);
    }
    
    private static boolean isLowerDigit(final char c) {
        return Character.isLowerCase(c) || Character.isDigit(c);
    }
    
    private static boolean isLetterDigit(final char c) {
        return Character.isLetter(c) || Character.isDigit(c);
    }
    
    public static String getJavaCasedName(final String javaCasedName) {
        final Matcher m = JavaUtil.RUBY_CASE_SPLITTER.matcher(javaCasedName);
        final StringBuffer newName = new StringBuffer();
        if (!m.find()) {
            return null;
        }
        m.reset();
        while (m.find()) {
            m.appendReplacement(newName, m.group(1) + Character.toUpperCase(m.group(2).charAt(0)));
        }
        m.appendTail(newName);
        return newName.toString();
    }
    
    public static Set<String> getRubyNamesForJavaName(final String javaName, final List<Method> methods) {
        final String javaPropertyName = getJavaPropertyName(javaName);
        final String rubyName = getRubyCasedName(javaName);
        final Set<String> nameSet = new LinkedHashSet<String>();
        nameSet.add(javaName);
        nameSet.add(rubyName);
        String rubyPropertyName = null;
        for (final Method method : methods) {
            final Class<?>[] argTypes = method.getParameterTypes();
            final Class<?> resultType = method.getReturnType();
            final int argCount = argTypes.length;
            if (javaPropertyName != null) {
                if (rubyName.startsWith("get_")) {
                    rubyPropertyName = rubyName.substring(4);
                    if (argCount != 0 && (argCount != 1 || argTypes[0] != Integer.TYPE)) {
                        continue;
                    }
                    nameSet.add(javaPropertyName);
                    nameSet.add(rubyPropertyName);
                    if (resultType != Boolean.TYPE) {
                        continue;
                    }
                    nameSet.add(javaPropertyName + '?');
                    nameSet.add(rubyPropertyName + '?');
                }
                else if (rubyName.startsWith("set_")) {
                    rubyPropertyName = rubyName.substring(4);
                    if (argCount != 1 || resultType != Void.TYPE) {
                        continue;
                    }
                    nameSet.add(javaPropertyName + '=');
                    nameSet.add(rubyPropertyName + '=');
                }
                else {
                    if (!rubyName.startsWith("is_")) {
                        continue;
                    }
                    rubyPropertyName = rubyName.substring(3);
                    if (resultType != Boolean.TYPE) {
                        continue;
                    }
                    nameSet.add(javaPropertyName);
                    nameSet.add(rubyPropertyName);
                    nameSet.add(javaPropertyName + '?');
                    nameSet.add(rubyPropertyName + '?');
                }
            }
            else {
                if (resultType != Boolean.TYPE) {
                    continue;
                }
                nameSet.add(javaName + '?');
                nameSet.add(rubyName + '?');
            }
        }
        return nameSet;
    }
    
    private static IRubyObject trySimpleConversions(final Ruby runtime, final Object object) {
        if (object == null) {
            return runtime.getNil();
        }
        if (object instanceof IRubyObject) {
            return (IRubyObject)object;
        }
        if (object instanceof RubyObjectHolderProxy) {
            return ((RubyObjectHolderProxy)object).__ruby_object();
        }
        if (object instanceof InternalJavaProxy) {
            final InternalJavaProxy internalJavaProxy = (InternalJavaProxy)object;
            final IRubyObject orig = internalJavaProxy.___getInvocationHandler().getOrig();
            if (orig != null) {
                return orig;
            }
        }
        return null;
    }
    
    private static boolean isDoubleFloatable(final double value) {
        return true;
    }
    
    private static boolean isLongByteable(final long value) {
        return value >= -128L && value <= 127L;
    }
    
    private static boolean isLongShortable(final long value) {
        return value >= -32768L && value <= 32767L;
    }
    
    private static boolean isLongCharable(final long value) {
        return value >= 0L && value <= 65535L;
    }
    
    private static boolean isLongIntable(final long value) {
        return value >= -2147483648L && value <= 2147483647L;
    }
    
    @Deprecated
    public static Object convertRubyToJava(final IRubyObject rubyObject) {
        return convertRubyToJava(rubyObject, Object.class);
    }
    
    @Deprecated
    public static Object convertRubyToJava(IRubyObject rubyObject, Class javaClass) {
        if (javaClass == Void.TYPE || rubyObject == null || rubyObject.isNil()) {
            return null;
        }
        final ThreadContext context = rubyObject.getRuntime().getCurrentContext();
        final IRubyObject origObject = rubyObject;
        if (rubyObject.dataGetStruct() instanceof JavaObject) {
            rubyObject = (JavaObject)rubyObject.dataGetStruct();
            if (rubyObject == null) {
                throw new RuntimeException("dataGetStruct returned null for " + origObject.getType().getName());
            }
        }
        else if (rubyObject.respondsTo("java_object")) {
            rubyObject = rubyObject.callMethod(context, "java_object");
            if (rubyObject == null) {
                throw new RuntimeException("java_object returned null for " + origObject.getType().getName());
            }
        }
        if (rubyObject instanceof JavaObject) {
            final Object value = ((JavaObject)rubyObject).getValue();
            return convertArgument(rubyObject.getRuntime(), value, value.getClass());
        }
        if (javaClass == Object.class || javaClass == null) {
            javaClass = rubyObject.getJavaClass();
        }
        if (javaClass.isInstance(rubyObject)) {
            return rubyObject;
        }
        final RubyConverter converter = JavaUtil.RUBY_CONVERTERS.get(javaClass);
        if (converter != null) {
            return converter.convert(context, rubyObject);
        }
        if (javaClass.isPrimitive()) {
            final String s = ((RubyString)TypeConverter.convertToType(rubyObject, rubyObject.getRuntime().getString(), "to_s", true)).getUnicodeValue();
            if (s.length() > 0) {
                return s.charAt(0);
            }
            return '\0';
        }
        else {
            if (javaClass == String.class) {
                final RubyString rubyString = (RubyString)rubyObject.callMethod(context, "to_s");
                final ByteList bytes = rubyString.getByteList();
                return RubyEncoding.decodeUTF8(bytes.getUnsafeBytes(), bytes.begin(), bytes.length());
            }
            if (javaClass == ByteList.class) {
                return rubyObject.convertToString().getByteList();
            }
            if (javaClass == BigInteger.class) {
                if (rubyObject instanceof RubyBignum) {
                    return ((RubyBignum)rubyObject).getValue();
                }
                if (rubyObject instanceof RubyNumeric) {
                    return BigInteger.valueOf(((RubyNumeric)rubyObject).getLongValue());
                }
                if (rubyObject.respondsTo("to_i")) {
                    final RubyNumeric rubyNumeric = (RubyNumeric)rubyObject.callMethod(context, "to_f");
                    return BigInteger.valueOf(rubyNumeric.getLongValue());
                }
            }
            else if (javaClass == BigDecimal.class && !(rubyObject instanceof JavaObject) && rubyObject.respondsTo("to_f")) {
                final double double_value = ((RubyNumeric)rubyObject.callMethod(context, "to_f")).getDoubleValue();
                return new BigDecimal(double_value);
            }
            try {
                if (isDuckTypeConvertable(rubyObject.getClass(), javaClass)) {
                    return convertProcToInterface(context, (RubyObject)rubyObject, javaClass);
                }
                return ((JavaObject)rubyObject).getValue();
            }
            catch (ClassCastException ex) {
                if (rubyObject.getRuntime().getDebug().isTrue()) {
                    ex.printStackTrace();
                }
                return null;
            }
        }
    }
    
    @Deprecated
    public static byte convertRubyToJavaByte(final IRubyObject rubyObject) {
        return (byte)convertRubyToJava(rubyObject, Byte.TYPE);
    }
    
    @Deprecated
    public static short convertRubyToJavaShort(final IRubyObject rubyObject) {
        return (short)convertRubyToJava(rubyObject, Short.TYPE);
    }
    
    @Deprecated
    public static char convertRubyToJavaChar(final IRubyObject rubyObject) {
        return (char)convertRubyToJava(rubyObject, Character.TYPE);
    }
    
    @Deprecated
    public static int convertRubyToJavaInt(final IRubyObject rubyObject) {
        return (int)convertRubyToJava(rubyObject, Integer.TYPE);
    }
    
    @Deprecated
    public static long convertRubyToJavaLong(final IRubyObject rubyObject) {
        return (long)convertRubyToJava(rubyObject, Long.TYPE);
    }
    
    @Deprecated
    public static float convertRubyToJavaFloat(final IRubyObject rubyObject) {
        return (float)convertRubyToJava(rubyObject, Float.TYPE);
    }
    
    @Deprecated
    public static double convertRubyToJavaDouble(final IRubyObject rubyObject) {
        return (double)convertRubyToJava(rubyObject, Double.TYPE);
    }
    
    @Deprecated
    public static boolean convertRubyToJavaBoolean(final IRubyObject rubyObject) {
        return (boolean)convertRubyToJava(rubyObject, Boolean.TYPE);
    }
    
    @Deprecated
    public static Object convertArgumentToType(final ThreadContext context, final IRubyObject arg, final Class target) {
        return arg.toJava(target);
    }
    
    @Deprecated
    public static Object coerceNilToType(final RubyNil nil, final Class target) {
        return nil.toJava(target);
    }
    
    @Deprecated
    public static IRubyObject convertJavaToRuby(final Ruby runtime, final JavaConverter converter, final Object object) {
        if (converter == null || converter == JavaUtil.JAVA_DEFAULT_CONVERTER) {
            return Java.getInstance(runtime, object);
        }
        return converter.convert(runtime, object);
    }
    
    @Deprecated
    public static RubyConverter getArrayConverter(final Class type) {
        final RubyConverter converter = JavaUtil.ARRAY_CONVERTERS.get(type);
        if (converter == null) {
            return JavaUtil.ARRAY_OBJECT_CONVERTER;
        }
        return converter;
    }
    
    @Deprecated
    public static IRubyObject ruby_to_java(final IRubyObject recv, final IRubyObject object, final Block unusedBlock) {
        if (object.respondsTo("to_java_object")) {
            IRubyObject result = (JavaObject)object.dataGetStruct();
            if (result == null) {
                result = object.callMethod(recv.getRuntime().getCurrentContext(), "to_java_object");
            }
            if (result instanceof JavaObject) {
                recv.getRuntime().getJavaSupport().getObjectProxyCache().put(((JavaObject)result).getValue(), object);
            }
            return result;
        }
        return primitive_to_java(recv, object, unusedBlock);
    }
    
    @Deprecated
    public static IRubyObject java_to_primitive(final IRubyObject recv, final IRubyObject object, final Block unusedBlock) {
        if (object instanceof JavaObject) {
            return convertJavaToRuby(recv.getRuntime(), ((JavaObject)object).getValue());
        }
        return object;
    }
    
    @Deprecated
    public static IRubyObject primitive_to_java(final IRubyObject recv, final IRubyObject object, final Block unusedBlock) {
        if (object instanceof JavaObject) {
            return object;
        }
        final Ruby runtime = recv.getRuntime();
        Object javaObject = null;
        switch (object.getMetaClass().index) {
            case 5: {
                javaObject = null;
                break;
            }
            case 1: {
                javaObject = ((RubyFixnum)object).getLongValue();
                break;
            }
            case 2: {
                javaObject = ((RubyBignum)object).getValue();
                break;
            }
            case 11: {
                javaObject = new Double(((RubyFloat)object).getValue());
                break;
            }
            case 4: {
                final ByteList bytes = ((RubyString)object).getByteList();
                javaObject = RubyEncoding.decodeUTF8(bytes.getUnsafeBytes(), bytes.begin(), bytes.length());
                break;
            }
            case 6: {
                javaObject = Boolean.TRUE;
                break;
            }
            case 7: {
                javaObject = Boolean.FALSE;
                break;
            }
            case 19: {
                javaObject = ((RubyTime)object).getJavaDate();
                break;
            }
            default: {
                return object;
            }
        }
        return JavaObject.wrap(runtime, javaObject);
    }
    
    @Deprecated
    public static Object convertArgument(final Ruby runtime, Object argument, final Class<?> parameterType) {
        if (argument == null) {
            if (parameterType.isPrimitive()) {
                throw runtime.newTypeError("primitives do not accept null");
            }
            return null;
        }
        else {
            if (argument instanceof JavaObject) {
                argument = ((JavaObject)argument).getValue();
                if (argument == null) {
                    return null;
                }
            }
            final Class<?> type = primitiveToWrapper(parameterType);
            if (type == Void.class) {
                return null;
            }
            if (argument instanceof Number) {
                final Number number = (Number)argument;
                if (type == Long.class) {
                    return number.longValue();
                }
                if (type == Integer.class) {
                    return number.intValue();
                }
                if (type == Byte.class) {
                    return number.byteValue();
                }
                if (type == Character.class) {
                    return (char)number.intValue();
                }
                if (type == Double.class) {
                    return new Double(number.doubleValue());
                }
                if (type == Float.class) {
                    return new Float(number.floatValue());
                }
                if (type == Short.class) {
                    return number.shortValue();
                }
            }
            if (isDuckTypeConvertable(argument.getClass(), parameterType)) {
                final RubyObject rubyObject = (RubyObject)argument;
                if (!rubyObject.respondsTo("java_object")) {
                    return convertProcToInterface(runtime.getCurrentContext(), rubyObject, parameterType);
                }
            }
            return argument;
        }
    }
    
    @Deprecated
    public static IRubyObject java_to_ruby(final Ruby runtime, final IRubyObject object) {
        if (object instanceof JavaObject) {
            return convertJavaToUsableRubyObject(runtime, ((JavaObject)object).getValue());
        }
        return object;
    }
    
    @Deprecated
    public static Object coerceStringToType(final RubyString string, final Class target) {
        try {
            final ByteList bytes = string.getByteList();
            if (string.getRuntime().is1_9()) {
                return new String(bytes.getUnsafeBytes(), bytes.begin(), bytes.length(), string.getEncoding().toString());
            }
            return RubyEncoding.decodeUTF8(bytes.getUnsafeBytes(), bytes.begin(), bytes.length());
        }
        catch (UnsupportedEncodingException uee) {
            return string.toString();
        }
    }
    
    @Deprecated
    public static Object coerceOtherToType(final ThreadContext context, final IRubyObject arg, final Class target) {
        if (isDuckTypeConvertable(arg.getClass(), target)) {
            final RubyObject rubyObject = (RubyObject)arg;
            if (!rubyObject.respondsTo("java_object")) {
                return convertProcToInterface(context, rubyObject, target);
            }
        }
        return arg;
    }
    
    @Deprecated
    public static Object coerceJavaObjectToType(final ThreadContext context, final Object javaObject, final Class target) {
        if (javaObject == null || !isDuckTypeConvertable(javaObject.getClass(), target)) {
            return javaObject;
        }
        final RubyObject rubyObject = (RubyObject)javaObject;
        if (!rubyObject.respondsTo("java_object")) {
            return convertProcToInterface(context, rubyObject, target);
        }
        return javaObject;
    }
    
    @Deprecated
    public static JavaObject unwrapJavaObject(final Ruby runtime, final IRubyObject convertee, final String errorMessage) {
        IRubyObject obj = convertee;
        if (!(obj instanceof JavaObject)) {
            if (obj.dataGetStruct() == null || !(obj.dataGetStruct() instanceof JavaObject)) {
                throw runtime.newTypeError(errorMessage);
            }
            obj = (JavaObject)obj.dataGetStruct();
        }
        return (JavaObject)obj;
    }
    
    static {
        RUBY_CASE_SPLITTER = Pattern.compile("([a-z][0-9]*)_([a-z])");
        JAVA_DEFAULT_CONVERTER = new JavaConverter(Object.class) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                final IRubyObject result = trySimpleConversions(runtime, object);
                if (result != null) {
                    return result;
                }
                return JavaObject.wrap(runtime, object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return this.convert(runtime, ((Object[])array)[i]);
            }
        };
        JAVA_BOOLEAN_CONVERTER = new JavaConverter(Boolean.class) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyBoolean.newBoolean(runtime, (boolean)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return this.convert(runtime, ((Boolean[])array)[i]);
            }
        };
        JAVA_FLOAT_CONVERTER = new JavaConverter(Float.class) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyFloat.newFloat(runtime, (double)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return this.convert(runtime, ((Float[])array)[i]);
            }
        };
        JAVA_DOUBLE_CONVERTER = new JavaConverter(Double.class) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyFloat.newFloat(runtime, (double)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return this.convert(runtime, ((Double[])array)[i]);
            }
        };
        JAVA_CHAR_CONVERTER = new JavaConverter(Character.class) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyFixnum.newFixnum(runtime, (char)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return this.convert(runtime, ((Character[])array)[i]);
            }
        };
        JAVA_BYTE_CONVERTER = new JavaConverter(Byte.class) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyFixnum.newFixnum(runtime, (byte)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return this.convert(runtime, ((Byte[])array)[i]);
            }
        };
        JAVA_SHORT_CONVERTER = new JavaConverter(Short.class) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyFixnum.newFixnum(runtime, (short)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return this.convert(runtime, ((Short[])array)[i]);
            }
        };
        JAVA_INT_CONVERTER = new JavaConverter(Integer.class) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyFixnum.newFixnum(runtime, (int)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return this.convert(runtime, ((Integer[])array)[i]);
            }
        };
        JAVA_LONG_CONVERTER = new JavaConverter(Long.class) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyFixnum.newFixnum(runtime, (long)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return this.convert(runtime, ((Long[])array)[i]);
            }
        };
        JAVA_BOOLEANPRIM_CONVERTER = new JavaConverter(Boolean.TYPE) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyBoolean.newBoolean(runtime, (boolean)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return RubyBoolean.newBoolean(runtime, ((boolean[])array)[i]);
            }
        };
        JAVA_FLOATPRIM_CONVERTER = new JavaConverter(Float.TYPE) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyFloat.newFloat(runtime, (double)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return RubyFloat.newFloat(runtime, ((float[])array)[i]);
            }
        };
        JAVA_DOUBLEPRIM_CONVERTER = new JavaConverter(Double.TYPE) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyFloat.newFloat(runtime, (double)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return RubyFloat.newFloat(runtime, ((double[])array)[i]);
            }
        };
        JAVA_CHARPRIM_CONVERTER = new JavaConverter(Character.TYPE) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyFixnum.newFixnum(runtime, (char)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return RubyFixnum.newFixnum(runtime, ((char[])array)[i]);
            }
        };
        JAVA_BYTEPRIM_CONVERTER = new JavaConverter(Byte.TYPE) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyFixnum.newFixnum(runtime, (byte)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return RubyFixnum.newFixnum(runtime, ((byte[])array)[i]);
            }
        };
        JAVA_SHORTPRIM_CONVERTER = new JavaConverter(Short.TYPE) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyFixnum.newFixnum(runtime, (short)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return RubyFixnum.newFixnum(runtime, ((short[])array)[i]);
            }
        };
        JAVA_INTPRIM_CONVERTER = new JavaConverter(Integer.TYPE) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyFixnum.newFixnum(runtime, (int)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return RubyFixnum.newFixnum(runtime, ((int[])array)[i]);
            }
        };
        JAVA_LONGPRIM_CONVERTER = new JavaConverter(Long.TYPE) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyFixnum.newFixnum(runtime, (long)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return RubyFixnum.newFixnum(runtime, ((long[])array)[i]);
            }
        };
        JAVA_STRING_CONVERTER = new JavaConverter(String.class) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyString.newUnicodeString(runtime, (String)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return this.convert(runtime, ((String[])array)[i]);
            }
        };
        BYTELIST_CONVERTER = new JavaConverter(ByteList.class) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyString.newString(runtime, (ByteList)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return this.convert(runtime, ((ByteList[])array)[i]);
            }
        };
        JAVA_BIGINTEGER_CONVERTER = new JavaConverter(BigInteger.class) {
            public IRubyObject convert(final Ruby runtime, final Object object) {
                if (object == null) {
                    return runtime.getNil();
                }
                return RubyBignum.newBignum(runtime, (BigInteger)object);
            }
            
            public IRubyObject get(final Ruby runtime, final Object array, final int i) {
                return this.convert(runtime, ((BigInteger[])array)[i]);
            }
        };
        (JAVA_CONVERTERS = new HashMap<Class, JavaConverter>()).put(Byte.class, JavaUtil.JAVA_BYTE_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(Byte.TYPE, JavaUtil.JAVA_BYTEPRIM_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(Short.class, JavaUtil.JAVA_SHORT_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(Short.TYPE, JavaUtil.JAVA_SHORTPRIM_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(Character.class, JavaUtil.JAVA_CHAR_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(Character.TYPE, JavaUtil.JAVA_CHARPRIM_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(Integer.class, JavaUtil.JAVA_INT_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(Integer.TYPE, JavaUtil.JAVA_INTPRIM_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(Long.class, JavaUtil.JAVA_LONG_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(Long.TYPE, JavaUtil.JAVA_LONGPRIM_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(Float.class, JavaUtil.JAVA_FLOAT_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(Float.TYPE, JavaUtil.JAVA_FLOATPRIM_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(Double.class, JavaUtil.JAVA_DOUBLE_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(Double.TYPE, JavaUtil.JAVA_DOUBLEPRIM_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(Boolean.class, JavaUtil.JAVA_BOOLEAN_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(Boolean.TYPE, JavaUtil.JAVA_BOOLEANPRIM_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(String.class, JavaUtil.JAVA_STRING_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(ByteList.class, JavaUtil.BYTELIST_CONVERTER);
        JavaUtil.JAVA_CONVERTERS.put(BigInteger.class, JavaUtil.JAVA_BIGINTEGER_CONVERTER);
        NUMERIC_TO_BYTE = new NumericConverter() {
            public Object coerce(final RubyNumeric numeric, final Class target) {
                final long value = numeric.getLongValue();
                if (isLongByteable(value)) {
                    return (byte)value;
                }
                throw numeric.getRuntime().newRangeError("too big for byte: " + numeric);
            }
        };
        NUMERIC_TO_SHORT = new NumericConverter() {
            public Object coerce(final RubyNumeric numeric, final Class target) {
                final long value = numeric.getLongValue();
                if (isLongShortable(value)) {
                    return (short)value;
                }
                throw numeric.getRuntime().newRangeError("too big for short: " + numeric);
            }
        };
        NUMERIC_TO_CHARACTER = new NumericConverter() {
            public Object coerce(final RubyNumeric numeric, final Class target) {
                final long value = numeric.getLongValue();
                if (isLongCharable(value)) {
                    return (char)value;
                }
                throw numeric.getRuntime().newRangeError("too big for char: " + numeric);
            }
        };
        NUMERIC_TO_INTEGER = new NumericConverter() {
            public Object coerce(final RubyNumeric numeric, final Class target) {
                final long value = numeric.getLongValue();
                if (isLongIntable(value)) {
                    return (int)value;
                }
                throw numeric.getRuntime().newRangeError("too big for int: " + numeric);
            }
        };
        NUMERIC_TO_LONG = new NumericConverter() {
            public Object coerce(final RubyNumeric numeric, final Class target) {
                return numeric.getLongValue();
            }
        };
        NUMERIC_TO_FLOAT = new NumericConverter() {
            public Object coerce(final RubyNumeric numeric, final Class target) {
                final double value = numeric.getDoubleValue();
                if (isDoubleFloatable(value)) {
                    return (float)value;
                }
                throw numeric.getRuntime().newTypeError("too big for float: " + numeric);
            }
        };
        NUMERIC_TO_DOUBLE = new NumericConverter() {
            public Object coerce(final RubyNumeric numeric, final Class target) {
                return numeric.getDoubleValue();
            }
        };
        NUMERIC_TO_BIGINTEGER = new NumericConverter() {
            public Object coerce(final RubyNumeric numeric, final Class target) {
                return numeric.getBigIntegerValue();
            }
        };
        NUMERIC_TO_OBJECT = new NumericConverter() {
            public Object coerce(final RubyNumeric numeric, final Class target) {
                if (numeric instanceof RubyFixnum) {
                    final long value = numeric.getLongValue();
                    return value;
                }
                if (numeric instanceof RubyFloat) {
                    final double value2 = numeric.getDoubleValue();
                    return value2;
                }
                if (numeric instanceof RubyBignum) {
                    return ((RubyBignum)numeric).getValue();
                }
                if (numeric instanceof RubyBigDecimal) {
                    return ((RubyBigDecimal)numeric).getValue();
                }
                return JavaUtil.NUMERIC_TO_OTHER.coerce(numeric, target);
            }
        };
        NUMERIC_TO_OTHER = new NumericConverter() {
            public Object coerce(final RubyNumeric numeric, final Class target) {
                if (target.isAssignableFrom(numeric.getClass())) {
                    return numeric;
                }
                throw numeric.getRuntime().newTypeError("could not coerce " + numeric.getMetaClass() + " to " + target);
            }
        };
        NUMERIC_TO_VOID = new NumericConverter() {
            public Object coerce(final RubyNumeric numeric, final Class target) {
                return null;
            }
        };
        (NUMERIC_CONVERTERS = new HashMap<Class, NumericConverter>()).put(Byte.TYPE, JavaUtil.NUMERIC_TO_BYTE);
        JavaUtil.NUMERIC_CONVERTERS.put(Byte.class, JavaUtil.NUMERIC_TO_BYTE);
        JavaUtil.NUMERIC_CONVERTERS.put(Short.TYPE, JavaUtil.NUMERIC_TO_SHORT);
        JavaUtil.NUMERIC_CONVERTERS.put(Short.class, JavaUtil.NUMERIC_TO_SHORT);
        JavaUtil.NUMERIC_CONVERTERS.put(Character.TYPE, JavaUtil.NUMERIC_TO_CHARACTER);
        JavaUtil.NUMERIC_CONVERTERS.put(Character.class, JavaUtil.NUMERIC_TO_CHARACTER);
        JavaUtil.NUMERIC_CONVERTERS.put(Integer.TYPE, JavaUtil.NUMERIC_TO_INTEGER);
        JavaUtil.NUMERIC_CONVERTERS.put(Integer.class, JavaUtil.NUMERIC_TO_INTEGER);
        JavaUtil.NUMERIC_CONVERTERS.put(Long.TYPE, JavaUtil.NUMERIC_TO_LONG);
        JavaUtil.NUMERIC_CONVERTERS.put(Long.class, JavaUtil.NUMERIC_TO_LONG);
        JavaUtil.NUMERIC_CONVERTERS.put(Float.TYPE, JavaUtil.NUMERIC_TO_FLOAT);
        JavaUtil.NUMERIC_CONVERTERS.put(Float.class, JavaUtil.NUMERIC_TO_FLOAT);
        JavaUtil.NUMERIC_CONVERTERS.put(Double.TYPE, JavaUtil.NUMERIC_TO_DOUBLE);
        JavaUtil.NUMERIC_CONVERTERS.put(Double.class, JavaUtil.NUMERIC_TO_DOUBLE);
        JavaUtil.NUMERIC_CONVERTERS.put(BigInteger.class, JavaUtil.NUMERIC_TO_BIGINTEGER);
        JavaUtil.NUMERIC_CONVERTERS.put(Object.class, JavaUtil.NUMERIC_TO_OBJECT);
        JavaUtil.NUMERIC_CONVERTERS.put(Void.TYPE, JavaUtil.NUMERIC_TO_VOID);
        RUBY_BOOLEAN_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                return rubyObject.isTrue();
            }
        };
        RUBY_BYTE_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                if (rubyObject.respondsTo("to_i")) {
                    return (byte)((RubyNumeric)rubyObject.callMethod(context, "to_i")).getLongValue();
                }
                return 0;
            }
        };
        RUBY_SHORT_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                if (rubyObject.respondsTo("to_i")) {
                    return (short)((RubyNumeric)rubyObject.callMethod(context, "to_i")).getLongValue();
                }
                return 0;
            }
        };
        RUBY_CHAR_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                if (rubyObject.respondsTo("to_i")) {
                    return (char)((RubyNumeric)rubyObject.callMethod(context, "to_i")).getLongValue();
                }
                return '\0';
            }
        };
        RUBY_INTEGER_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                if (rubyObject.respondsTo("to_i")) {
                    return (int)((RubyNumeric)rubyObject.callMethod(context, "to_i")).getLongValue();
                }
                return 0;
            }
        };
        RUBY_LONG_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                if (rubyObject.respondsTo("to_i")) {
                    return ((RubyNumeric)rubyObject.callMethod(context, "to_i")).getLongValue();
                }
                return 0L;
            }
        };
        RUBY_FLOAT_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                if (rubyObject.respondsTo("to_f")) {
                    return new Float((float)((RubyNumeric)rubyObject.callMethod(context, "to_f")).getDoubleValue());
                }
                return new Float(0.0);
            }
        };
        RUBY_DOUBLE_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                if (rubyObject.respondsTo("to_f")) {
                    return new Double(((RubyNumeric)rubyObject.callMethod(context, "to_f")).getDoubleValue());
                }
                return new Double(0.0);
            }
        };
        (RUBY_CONVERTERS = new HashMap<Class, RubyConverter>()).put(Boolean.class, JavaUtil.RUBY_BOOLEAN_CONVERTER);
        JavaUtil.RUBY_CONVERTERS.put(Boolean.TYPE, JavaUtil.RUBY_BOOLEAN_CONVERTER);
        JavaUtil.RUBY_CONVERTERS.put(Byte.class, JavaUtil.RUBY_BYTE_CONVERTER);
        JavaUtil.RUBY_CONVERTERS.put(Byte.TYPE, JavaUtil.RUBY_BYTE_CONVERTER);
        JavaUtil.RUBY_CONVERTERS.put(Short.class, JavaUtil.RUBY_SHORT_CONVERTER);
        JavaUtil.RUBY_CONVERTERS.put(Short.TYPE, JavaUtil.RUBY_SHORT_CONVERTER);
        JavaUtil.RUBY_CONVERTERS.put(Integer.class, JavaUtil.RUBY_INTEGER_CONVERTER);
        JavaUtil.RUBY_CONVERTERS.put(Integer.TYPE, JavaUtil.RUBY_INTEGER_CONVERTER);
        JavaUtil.RUBY_CONVERTERS.put(Long.class, JavaUtil.RUBY_LONG_CONVERTER);
        JavaUtil.RUBY_CONVERTERS.put(Long.TYPE, JavaUtil.RUBY_LONG_CONVERTER);
        JavaUtil.RUBY_CONVERTERS.put(Float.class, JavaUtil.RUBY_FLOAT_CONVERTER);
        JavaUtil.RUBY_CONVERTERS.put(Float.TYPE, JavaUtil.RUBY_FLOAT_CONVERTER);
        JavaUtil.RUBY_CONVERTERS.put(Double.class, JavaUtil.RUBY_DOUBLE_CONVERTER);
        JavaUtil.RUBY_CONVERTERS.put(Double.TYPE, JavaUtil.RUBY_DOUBLE_CONVERTER);
        ARRAY_BOOLEAN_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                return rubyObject.toJava(Boolean.class);
            }
        };
        ARRAY_BYTE_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                return rubyObject.toJava(Byte.class);
            }
        };
        ARRAY_SHORT_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                return rubyObject.toJava(Short.class);
            }
        };
        ARRAY_CHAR_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                return rubyObject.toJava(Character.class);
            }
        };
        ARRAY_INT_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                return rubyObject.toJava(Integer.class);
            }
        };
        ARRAY_LONG_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                return rubyObject.toJava(Long.class);
            }
        };
        ARRAY_FLOAT_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                return rubyObject.toJava(Float.class);
            }
        };
        ARRAY_DOUBLE_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                return rubyObject.toJava(Double.class);
            }
        };
        ARRAY_OBJECT_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                return rubyObject.toJava(Object.class);
            }
        };
        ARRAY_CLASS_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                return rubyObject.toJava(Class.class);
            }
        };
        ARRAY_STRING_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                return rubyObject.toJava(String.class);
            }
        };
        ARRAY_BIGINTEGER_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                return rubyObject.toJava(BigInteger.class);
            }
        };
        ARRAY_BIGDECIMAL_CONVERTER = new RubyConverter() {
            public Object convert(final ThreadContext context, final IRubyObject rubyObject) {
                return rubyObject.toJava(BigDecimal.class);
            }
        };
        (ARRAY_CONVERTERS = new HashMap<Class, RubyConverter>()).put(Boolean.class, JavaUtil.ARRAY_BOOLEAN_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(Boolean.TYPE, JavaUtil.ARRAY_BOOLEAN_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(Byte.class, JavaUtil.ARRAY_BYTE_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(Byte.TYPE, JavaUtil.ARRAY_BYTE_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(Short.class, JavaUtil.ARRAY_SHORT_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(Short.TYPE, JavaUtil.ARRAY_SHORT_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(Character.class, JavaUtil.ARRAY_CHAR_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(Character.TYPE, JavaUtil.ARRAY_CHAR_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(Integer.class, JavaUtil.ARRAY_INT_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(Integer.TYPE, JavaUtil.ARRAY_INT_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(Long.class, JavaUtil.ARRAY_LONG_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(Long.TYPE, JavaUtil.ARRAY_LONG_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(Float.class, JavaUtil.ARRAY_FLOAT_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(Float.TYPE, JavaUtil.ARRAY_FLOAT_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(Double.class, JavaUtil.ARRAY_DOUBLE_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(Double.TYPE, JavaUtil.ARRAY_DOUBLE_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(String.class, JavaUtil.ARRAY_STRING_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(Class.class, JavaUtil.ARRAY_CLASS_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(BigInteger.class, JavaUtil.ARRAY_BIGINTEGER_CONVERTER);
        JavaUtil.ARRAY_CONVERTERS.put(BigDecimal.class, JavaUtil.ARRAY_BIGDECIMAL_CONVERTER);
    }
    
    public abstract static class JavaConverter
    {
        private final Class type;
        
        public JavaConverter(final Class type) {
            this.type = type;
        }
        
        public abstract IRubyObject convert(final Ruby p0, final Object p1);
        
        public abstract IRubyObject get(final Ruby p0, final Object p1, final int p2);
        
        public String toString() {
            return this.type.getName() + " converter";
        }
    }
    
    @Deprecated
    public interface RubyConverter
    {
        Object convert(final ThreadContext p0, final IRubyObject p1);
    }
    
    public interface NumericConverter
    {
        Object coerce(final RubyNumeric p0, final Class p1);
    }
}
