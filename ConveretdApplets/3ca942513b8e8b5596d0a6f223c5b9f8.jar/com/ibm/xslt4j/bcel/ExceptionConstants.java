// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel;

public interface ExceptionConstants
{
    public static final Class THROWABLE = forName;
    public static final Class RUNTIME_EXCEPTION = forName2;
    public static final Class LINKING_EXCEPTION = forName3;
    public static final Class CLASS_CIRCULARITY_ERROR = forName4;
    public static final Class CLASS_FORMAT_ERROR = forName5;
    public static final Class EXCEPTION_IN_INITIALIZER_ERROR = forName6;
    public static final Class INCOMPATIBLE_CLASS_CHANGE_ERROR = forName7;
    public static final Class ABSTRACT_METHOD_ERROR = forName8;
    public static final Class ILLEGAL_ACCESS_ERROR = forName9;
    public static final Class INSTANTIATION_ERROR = forName10;
    public static final Class NO_SUCH_FIELD_ERROR = forName11;
    public static final Class NO_SUCH_METHOD_ERROR = forName12;
    public static final Class NO_CLASS_DEF_FOUND_ERROR = forName13;
    public static final Class UNSATISFIED_LINK_ERROR = forName14;
    public static final Class VERIFY_ERROR = forName15;
    public static final Class NULL_POINTER_EXCEPTION = forName16;
    public static final Class ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION = forName17;
    public static final Class ARITHMETIC_EXCEPTION = forName18;
    public static final Class NEGATIVE_ARRAY_SIZE_EXCEPTION = forName19;
    public static final Class CLASS_CAST_EXCEPTION = forName20;
    public static final Class ILLEGAL_MONITOR_STATE = forName21;
    public static final Class[] EXCS_CLASS_AND_INTERFACE_RESOLUTION = { ExceptionConstants.NO_CLASS_DEF_FOUND_ERROR, ExceptionConstants.CLASS_FORMAT_ERROR, ExceptionConstants.VERIFY_ERROR, ExceptionConstants.ABSTRACT_METHOD_ERROR, ExceptionConstants.EXCEPTION_IN_INITIALIZER_ERROR, ExceptionConstants.ILLEGAL_ACCESS_ERROR };
    public static final Class[] EXCS_FIELD_AND_METHOD_RESOLUTION = { ExceptionConstants.NO_SUCH_FIELD_ERROR, ExceptionConstants.ILLEGAL_ACCESS_ERROR, ExceptionConstants.NO_SUCH_METHOD_ERROR };
    public static final Class[] EXCS_INTERFACE_METHOD_RESOLUTION = new Class[0];
    public static final Class[] EXCS_STRING_RESOLUTION = new Class[0];
    public static final Class[] EXCS_ARRAY_EXCEPTION = { ExceptionConstants.NULL_POINTER_EXCEPTION, ExceptionConstants.ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION };
    
    default static {
        Class<?> forName;
        try {
            forName = Class.forName("java.lang.Throwable");
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
        Class<?> forName2;
        try {
            forName2 = Class.forName("java.lang.RuntimeException");
        }
        catch (ClassNotFoundException ex2) {
            throw new NoClassDefFoundError(ex2.getMessage());
        }
        Class<?> forName3;
        try {
            forName3 = Class.forName("java.lang.LinkageError");
        }
        catch (ClassNotFoundException ex3) {
            throw new NoClassDefFoundError(ex3.getMessage());
        }
        Class<?> forName4;
        try {
            forName4 = Class.forName("java.lang.ClassCircularityError");
        }
        catch (ClassNotFoundException ex4) {
            throw new NoClassDefFoundError(ex4.getMessage());
        }
        Class<?> forName5;
        try {
            forName5 = Class.forName("java.lang.ClassFormatError");
        }
        catch (ClassNotFoundException ex5) {
            throw new NoClassDefFoundError(ex5.getMessage());
        }
        Class<?> forName6;
        try {
            forName6 = Class.forName("java.lang.ExceptionInInitializerError");
        }
        catch (ClassNotFoundException ex6) {
            throw new NoClassDefFoundError(ex6.getMessage());
        }
        Class<?> forName7;
        try {
            forName7 = Class.forName("java.lang.IncompatibleClassChangeError");
        }
        catch (ClassNotFoundException ex7) {
            throw new NoClassDefFoundError(ex7.getMessage());
        }
        Class<?> forName8;
        try {
            forName8 = Class.forName("java.lang.AbstractMethodError");
        }
        catch (ClassNotFoundException ex8) {
            throw new NoClassDefFoundError(ex8.getMessage());
        }
        Class<?> forName9;
        try {
            forName9 = Class.forName("java.lang.IllegalAccessError");
        }
        catch (ClassNotFoundException ex9) {
            throw new NoClassDefFoundError(ex9.getMessage());
        }
        Class<?> forName10;
        try {
            forName10 = Class.forName("java.lang.InstantiationError");
        }
        catch (ClassNotFoundException ex10) {
            throw new NoClassDefFoundError(ex10.getMessage());
        }
        Class<?> forName11;
        try {
            forName11 = Class.forName("java.lang.NoSuchFieldError");
        }
        catch (ClassNotFoundException ex11) {
            throw new NoClassDefFoundError(ex11.getMessage());
        }
        Class<?> forName12;
        try {
            forName12 = Class.forName("java.lang.NoSuchMethodError");
        }
        catch (ClassNotFoundException ex12) {
            throw new NoClassDefFoundError(ex12.getMessage());
        }
        Class<?> forName13;
        try {
            forName13 = Class.forName("java.lang.NoClassDefFoundError");
        }
        catch (ClassNotFoundException ex13) {
            throw new NoClassDefFoundError(ex13.getMessage());
        }
        Class<?> forName14;
        try {
            forName14 = Class.forName("java.lang.UnsatisfiedLinkError");
        }
        catch (ClassNotFoundException ex14) {
            throw new NoClassDefFoundError(ex14.getMessage());
        }
        Class<?> forName15;
        try {
            forName15 = Class.forName("java.lang.VerifyError");
        }
        catch (ClassNotFoundException ex15) {
            throw new NoClassDefFoundError(ex15.getMessage());
        }
        Class<?> forName16;
        try {
            forName16 = Class.forName("java.lang.NullPointerException");
        }
        catch (ClassNotFoundException ex16) {
            throw new NoClassDefFoundError(ex16.getMessage());
        }
        Class<?> forName17;
        try {
            forName17 = Class.forName("java.lang.ArrayIndexOutOfBoundsException");
        }
        catch (ClassNotFoundException ex17) {
            throw new NoClassDefFoundError(ex17.getMessage());
        }
        Class<?> forName18;
        try {
            forName18 = Class.forName("java.lang.ArithmeticException");
        }
        catch (ClassNotFoundException ex18) {
            throw new NoClassDefFoundError(ex18.getMessage());
        }
        Class<?> forName19;
        try {
            forName19 = Class.forName("java.lang.NegativeArraySizeException");
        }
        catch (ClassNotFoundException ex19) {
            throw new NoClassDefFoundError(ex19.getMessage());
        }
        Class<?> forName20;
        try {
            forName20 = Class.forName("java.lang.ClassCastException");
        }
        catch (ClassNotFoundException ex20) {
            throw new NoClassDefFoundError(ex20.getMessage());
        }
        Class<?> forName21;
        try {
            forName21 = Class.forName("java.lang.IllegalMonitorStateException");
        }
        catch (ClassNotFoundException ex21) {
            throw new NoClassDefFoundError(ex21.getMessage());
        }
    }
}
