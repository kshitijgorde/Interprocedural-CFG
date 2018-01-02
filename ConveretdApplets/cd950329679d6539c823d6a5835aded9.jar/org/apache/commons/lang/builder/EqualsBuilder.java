// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.lang.builder;

import sun.misc.Unsafe;
import java.math.MutableBigInteger;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.io.ObjectInputStream;
import java.math.RoundingMode;
import java.math.MathContext;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.AccessibleObject;
import java.util.Collections;
import java.util.Arrays;
import java.util.Collection;

public class EqualsBuilder
{
    private boolean isEquals;
    
    public EqualsBuilder() {
        this.isEquals = true;
    }
    
    public static boolean reflectionEquals(final Object lhs, final Object rhs) {
        return reflectionEquals(lhs, rhs, false, null, null);
    }
    
    public static boolean reflectionEquals(final Object lhs, final Object rhs, final Collection excludeFields) {
        return reflectionEquals(lhs, rhs, ReflectionToStringBuilder.toNoNullStringArray(excludeFields));
    }
    
    public static boolean reflectionEquals(final Object lhs, final Object rhs, final String[] excludeFields) {
        return reflectionEquals(lhs, rhs, false, null, excludeFields);
    }
    
    public static boolean reflectionEquals(final Object lhs, final Object rhs, final boolean testTransients) {
        return reflectionEquals(lhs, rhs, testTransients, null, null);
    }
    
    public static boolean reflectionEquals(final Object lhs, final Object rhs, final boolean testTransients, final Class reflectUpToClass) {
        return reflectionEquals(lhs, rhs, testTransients, reflectUpToClass, null);
    }
    
    public static boolean reflectionEquals(final Object lhs, final Object rhs, final boolean testTransients, final Class reflectUpToClass, final String[] excludeFields) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* lhs */
        //     1: aload_1         /* rhs */
        //     2: if_acmpne       7
        //     5: iconst_1       
        //     6: ireturn        
        //     7: aload_0         /* lhs */
        //     8: ifnull          15
        //    11: aload_1         /* rhs */
        //    12: ifnonnull       17
        //    15: iconst_0       
        //    16: ireturn        
        //    17: aload_0         /* lhs */
        //    18: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    21: astore          lhsClass
        //    23: aload_1         /* rhs */
        //    24: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    27: astore          rhsClass
        //    29: aload           lhsClass
        //    31: aload_1         /* rhs */
        //    32: invokevirtual   java/lang/Class.isInstance:(Ljava/lang/Object;)Z
        //    35: ifeq            58
        //    38: aload           lhsClass
        //    40: astore          testClass
        //    42: aload           rhsClass
        //    44: aload_0         /* lhs */
        //    45: invokevirtual   java/lang/Class.isInstance:(Ljava/lang/Object;)Z
        //    48: ifne            89
        //    51: aload           rhsClass
        //    53: astore          testClass
        //    55: goto            89
        //    58: aload           rhsClass
        //    60: aload_0         /* lhs */
        //    61: invokevirtual   java/lang/Class.isInstance:(Ljava/lang/Object;)Z
        //    64: ifeq            87
        //    67: aload           rhsClass
        //    69: astore          testClass
        //    71: aload           lhsClass
        //    73: aload_1         /* rhs */
        //    74: invokevirtual   java/lang/Class.isInstance:(Ljava/lang/Object;)Z
        //    77: ifne            89
        //    80: aload           lhsClass
        //    82: astore          testClass
        //    84: goto            89
        //    87: iconst_0       
        //    88: ireturn        
        //    89: new             Lorg/apache/commons/lang/builder/EqualsBuilder;
        //    92: dup            
        //    93: invokespecial   org/apache/commons/lang/builder/EqualsBuilder.<init>:()V
        //    96: astore          equalsBuilder
        //    98: aload_0         /* lhs */
        //    99: aload_1         /* rhs */
        //   100: aload           7
        //   102: aload           equalsBuilder
        //   104: iload_2         /* testTransients */
        //   105: aload           excludeFields
        //   107: invokestatic    org/apache/commons/lang/builder/EqualsBuilder.reflectionAppend:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Class;Lorg/apache/commons/lang/builder/EqualsBuilder;Z[Ljava/lang/String;)V
        //   110: aload           7
        //   112: invokevirtual   java/lang/Class.getSuperclass:()Ljava/lang/Class;
        //   115: ifnull          146
        //   118: aload           7
        //   120: aload_3         /* reflectUpToClass */
        //   121: if_acmpeq       146
        //   124: aload           7
        //   126: invokevirtual   java/lang/Class.getSuperclass:()Ljava/lang/Class;
        //   129: astore          testClass
        //   131: aload_0         /* lhs */
        //   132: aload_1         /* rhs */
        //   133: aload           testClass
        //   135: aload           equalsBuilder
        //   137: iload_2         /* testTransients */
        //   138: aload           excludeFields
        //   140: invokestatic    org/apache/commons/lang/builder/EqualsBuilder.reflectionAppend:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Class;Lorg/apache/commons/lang/builder/EqualsBuilder;Z[Ljava/lang/String;)V
        //   143: goto            110
        //   146: goto            153
        //   149: astore          e
        //   151: iconst_0       
        //   152: ireturn        
        //   153: aload           equalsBuilder
        //   155: invokevirtual   org/apache/commons/lang/builder/EqualsBuilder.isEquals:()Z
        //   158: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name              Signature
        //  -----  ------  ----  ----------------  -----------------------------------------------
        //  42     16      7     testClass         Ljava/lang/Class;
        //  71     16      7     testClass         Ljava/lang/Class;
        //  131    15      7     testClass         Ljava/lang/Class;
        //  151    2       9     e                 Ljava/lang/IllegalArgumentException;
        //  0      159     0     lhs               Ljava/lang/Object;
        //  0      159     1     rhs               Ljava/lang/Object;
        //  0      159     2     testTransients    Z
        //  0      159     3     reflectUpToClass  Ljava/lang/Class;
        //  0      159     4     excludeFields     [Ljava/lang/String;
        //  23     136     5     lhsClass          Ljava/lang/Class;
        //  29     130     6     rhsClass          Ljava/lang/Class;
        //  98     61      8     equalsBuilder     Lorg/apache/commons/lang/builder/EqualsBuilder;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  98     146    149    153    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static void reflectionAppend(final Object lhs, final Object rhs, final Class clazz, final EqualsBuilder builder, final boolean useTransients, final String[] excludeFields) {
        final Field[] fields = clazz.getDeclaredFields();
        final List excludedFieldList = (excludeFields != null) ? Arrays.asList(excludeFields) : Collections.EMPTY_LIST;
        AccessibleObject.setAccessible(fields, true);
        for (int i = 0; i < fields.length && builder.isEquals; ++i) {
            final Field f = fields[i];
            if (!excludedFieldList.contains(f.getName()) && f.getName().indexOf(36) == -1 && (useTransients || !Modifier.isTransient(f.getModifiers())) && !Modifier.isStatic(f.getModifiers())) {
                try {
                    builder.append(f.get(lhs), f.get(rhs));
                }
                catch (IllegalAccessException e) {
                    throw new InternalError("Unexpected IllegalAccessException");
                }
            }
        }
    }
    
    public EqualsBuilder appendSuper(final boolean superEquals) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = superEquals;
        return this;
    }
    
    public EqualsBuilder append(final Object lhs, final Object rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        final Class lhsClass = lhs.getClass();
        if (!lhsClass.isArray()) {
            if (lhs instanceof BigDecimal) {
                this.isEquals = (((BigDecimal)lhs).compareTo(rhs) == 0);
            }
            else {
                this.isEquals = lhs.equals(rhs);
            }
        }
        else if (lhs.getClass() != rhs.getClass()) {
            this.setEquals(false);
        }
        else if (lhs instanceof long[]) {
            this.append((long[])lhs, (long[])rhs);
        }
        else if (lhs instanceof int[]) {
            this.append((int[])lhs, (int[])rhs);
        }
        else if (lhs instanceof short[]) {
            this.append((short[])lhs, (short[])rhs);
        }
        else if (lhs instanceof char[]) {
            this.append((char[])lhs, (char[])rhs);
        }
        else if (lhs instanceof byte[]) {
            this.append((byte[])lhs, (byte[])rhs);
        }
        else if (lhs instanceof double[]) {
            this.append((double[])lhs, (double[])rhs);
        }
        else if (lhs instanceof float[]) {
            this.append((float[])lhs, (float[])rhs);
        }
        else if (lhs instanceof boolean[]) {
            this.append((boolean[])lhs, (boolean[])rhs);
        }
        else {
            this.append((Object[])lhs, (Object[])rhs);
        }
        return this;
    }
    
    public EqualsBuilder append(final long lhs, final long rhs) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = (lhs == rhs);
        return this;
    }
    
    public EqualsBuilder append(final int lhs, final int rhs) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = (lhs == rhs);
        return this;
    }
    
    public EqualsBuilder append(final short lhs, final short rhs) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = (lhs == rhs);
        return this;
    }
    
    public EqualsBuilder append(final char lhs, final char rhs) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = (lhs == rhs);
        return this;
    }
    
    public EqualsBuilder append(final byte lhs, final byte rhs) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = (lhs == rhs);
        return this;
    }
    
    public EqualsBuilder append(final double lhs, final double rhs) {
        if (!this.isEquals) {
            return this;
        }
        return this.append(Double.doubleToLongBits(lhs), Double.doubleToLongBits(rhs));
    }
    
    public EqualsBuilder append(final float lhs, final float rhs) {
        if (!this.isEquals) {
            return this;
        }
        return this.append(Float.floatToIntBits(lhs), Float.floatToIntBits(rhs));
    }
    
    public EqualsBuilder append(final boolean lhs, final boolean rhs) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = (lhs == rhs);
        return this;
    }
    
    public EqualsBuilder append(final Object[] lhs, final Object[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }
    
    public EqualsBuilder append(final long[] lhs, final long[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }
    
    public EqualsBuilder append(final int[] lhs, final int[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }
    
    public EqualsBuilder append(final short[] lhs, final short[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }
    
    public EqualsBuilder append(final char[] lhs, final char[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }
    
    public EqualsBuilder append(final byte[] lhs, final byte[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }
    
    public EqualsBuilder append(final double[] lhs, final double[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }
    
    public EqualsBuilder append(final float[] lhs, final float[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }
    
    public EqualsBuilder append(final boolean[] lhs, final boolean[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }
    
    public boolean isEquals() {
        return this.isEquals;
    }
    
    protected void setEquals(final boolean isEquals) {
        this.isEquals = isEquals;
    }
}
