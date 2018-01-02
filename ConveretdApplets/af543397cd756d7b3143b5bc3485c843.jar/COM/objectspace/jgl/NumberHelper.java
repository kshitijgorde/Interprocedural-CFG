// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

import java.math.BigInteger;
import java.math.BigDecimal;

final class NumberHelper
{
    private static Class classInteger;
    private static Class classLong;
    private static Class classFloat;
    private static Class classDouble;
    private static Class classByte;
    private static Class classShort;
    private static Class classBigInteger;
    private static Class classBigDecimal;
    static Class classNumber;
    
    static BigDecimal asBigDecimal(final Number n) {
        return (BigDecimal)((n instanceof BigDecimal) ? n : new BigDecimal(n.toString()));
    }
    
    static BigInteger asBigInteger(final Number n) {
        return (BigInteger)((n instanceof BigInteger) ? n : asBigDecimal(n).toBigInteger());
    }
    
    static Number plus(final Number n, final Number n2, final Class clazz) {
        if (clazz.equals(NumberHelper.classInteger)) {
            return new Integer(n.intValue() + n2.intValue());
        }
        if (clazz.equals(NumberHelper.classLong)) {
            return new Long(n.longValue() + n2.longValue());
        }
        if (clazz.equals(NumberHelper.classFloat)) {
            return new Float(n.floatValue() + n2.floatValue());
        }
        if (clazz.equals(NumberHelper.classDouble)) {
            return new Double(n.doubleValue() + n2.doubleValue());
        }
        if (clazz.equals(NumberHelper.classByte)) {
            return new Byte((byte)(n.byteValue() + n2.byteValue()));
        }
        if (clazz.equals(NumberHelper.classShort)) {
            return new Short((short)(n.shortValue() + n2.shortValue()));
        }
        if (clazz.equals(NumberHelper.classBigInteger)) {
            return asBigInteger(n).add(asBigInteger(n2));
        }
        if (clazz.equals(NumberHelper.classBigDecimal)) {
            return asBigDecimal(n).add(asBigDecimal(n2));
        }
        throw new IllegalArgumentException(String.valueOf("unknown subclass of java.lang.Number: ").concat(String.valueOf(clazz.getClass())));
    }
    
    static Number minus(final Number n, final Number n2, final Class clazz) {
        if (clazz.equals(NumberHelper.classInteger)) {
            return new Integer(n.intValue() - n2.intValue());
        }
        if (clazz.equals(NumberHelper.classLong)) {
            return new Long(n.longValue() - n2.longValue());
        }
        if (clazz.equals(NumberHelper.classFloat)) {
            return new Float(n.floatValue() - n2.floatValue());
        }
        if (clazz.equals(NumberHelper.classDouble)) {
            return new Double(n.doubleValue() - n2.doubleValue());
        }
        if (clazz.equals(NumberHelper.classByte)) {
            return new Byte((byte)(n.byteValue() - n2.byteValue()));
        }
        if (clazz.equals(NumberHelper.classShort)) {
            return new Short((short)(n.shortValue() - n2.shortValue()));
        }
        if (clazz.equals(NumberHelper.classBigInteger)) {
            return asBigInteger(n).subtract(asBigInteger(n2));
        }
        if (clazz.equals(NumberHelper.classBigDecimal)) {
            return asBigDecimal(n).subtract(asBigDecimal(n2));
        }
        throw new IllegalArgumentException(String.valueOf("unknown subclass of java.lang.Number: ").concat(String.valueOf(clazz.getClass())));
    }
    
    static Number multiply(final Number n, final Number n2, final Class clazz) {
        if (clazz.equals(NumberHelper.classInteger)) {
            return new Integer(n.intValue() * n2.intValue());
        }
        if (clazz.equals(NumberHelper.classLong)) {
            return new Long(n.longValue() * n2.longValue());
        }
        if (clazz.equals(NumberHelper.classFloat)) {
            return new Float(n.floatValue() * n2.floatValue());
        }
        if (clazz.equals(NumberHelper.classDouble)) {
            return new Double(n.doubleValue() * n2.doubleValue());
        }
        if (clazz.equals(NumberHelper.classByte)) {
            return new Byte((byte)(n.byteValue() * n2.byteValue()));
        }
        if (clazz.equals(NumberHelper.classShort)) {
            return new Short((short)(n.shortValue() * n2.shortValue()));
        }
        if (clazz.equals(NumberHelper.classBigInteger)) {
            return asBigInteger(n).multiply(asBigInteger(n2));
        }
        if (clazz.equals(NumberHelper.classBigDecimal)) {
            return asBigDecimal(n).multiply(asBigDecimal(n2));
        }
        throw new IllegalArgumentException(String.valueOf("unknown subclass of java.lang.Number: ").concat(String.valueOf(clazz.getClass())));
    }
    
    static Number divides(final Number n, final Number n2, final Class clazz, final int n3) {
        if (clazz.equals(NumberHelper.classInteger)) {
            return new Integer(n.intValue() / n2.intValue());
        }
        if (clazz.equals(NumberHelper.classLong)) {
            return new Long(n.longValue() / n2.longValue());
        }
        if (clazz.equals(NumberHelper.classFloat)) {
            return new Float(n.floatValue() / n2.floatValue());
        }
        if (clazz.equals(NumberHelper.classDouble)) {
            return new Double(n.doubleValue() / n2.doubleValue());
        }
        if (clazz.equals(NumberHelper.classByte)) {
            return new Byte((byte)(n.byteValue() / n2.byteValue()));
        }
        if (clazz.equals(NumberHelper.classShort)) {
            return new Short((short)(n.shortValue() / n2.shortValue()));
        }
        if (clazz.equals(NumberHelper.classBigInteger)) {
            return asBigInteger(n).divide(asBigInteger(n2));
        }
        if (clazz.equals(NumberHelper.classBigDecimal)) {
            return asBigDecimal(n).divide(asBigDecimal(n2), n3);
        }
        throw new IllegalArgumentException(String.valueOf("unknown subclass of java.lang.Number: ").concat(String.valueOf(clazz.getClass())));
    }
    
    static Number modulus(final Number n, final Number n2, final Class clazz, final int n3) {
        if (clazz.equals(NumberHelper.classInteger)) {
            return new Integer(n.intValue() % n2.intValue());
        }
        if (clazz.equals(NumberHelper.classLong)) {
            return new Long(n.longValue() % n2.longValue());
        }
        if (clazz.equals(NumberHelper.classFloat)) {
            return new Float(n.floatValue() % n2.floatValue());
        }
        if (clazz.equals(NumberHelper.classDouble)) {
            return new Double(n.doubleValue() % n2.doubleValue());
        }
        if (clazz.equals(NumberHelper.classByte)) {
            return new Byte((byte)(n.byteValue() % n2.byteValue()));
        }
        if (clazz.equals(NumberHelper.classShort)) {
            return new Short((short)(n.shortValue() % n2.shortValue()));
        }
        if (clazz.equals(NumberHelper.classBigInteger)) {
            return asBigInteger(n).mod(asBigInteger(n2));
        }
        if (clazz.equals(NumberHelper.classBigDecimal)) {
            final BigDecimal bigDecimal = asBigDecimal(n);
            final BigDecimal bigDecimal2 = asBigDecimal(n2);
            return bigDecimal.subtract(bigDecimal.divide(bigDecimal2, n3).multiply(bigDecimal2));
        }
        throw new IllegalArgumentException(String.valueOf("unknown subclass of java.lang.Number: ").concat(String.valueOf(clazz.getClass())));
    }
    
    static int compare(final Number n, final Number n2, final Class clazz) {
        if (clazz.equals(NumberHelper.classInteger)) {
            return (n.intValue() < n2.intValue()) ? -1 : ((n.intValue() > n2.intValue()) ? 1 : 0);
        }
        if (clazz.equals(NumberHelper.classLong)) {
            return (n.longValue() < n2.longValue()) ? -1 : ((n.longValue() > n2.longValue()) ? 1 : 0);
        }
        if (clazz.equals(NumberHelper.classFloat)) {
            return (n.floatValue() < n2.floatValue()) ? -1 : ((n.floatValue() > n2.floatValue()) ? 1 : 0);
        }
        if (clazz.equals(NumberHelper.classDouble)) {
            return (n.doubleValue() < n2.doubleValue()) ? -1 : ((n.doubleValue() > n2.doubleValue()) ? 1 : 0);
        }
        if (clazz.equals(NumberHelper.classByte)) {
            return (n.byteValue() < n2.byteValue()) ? -1 : ((n.byteValue() > n2.byteValue()) ? 1 : 0);
        }
        if (clazz.equals(NumberHelper.classShort)) {
            return (n.shortValue() < n2.shortValue()) ? -1 : ((n.shortValue() > n2.shortValue()) ? 1 : 0);
        }
        if (clazz.equals(NumberHelper.classBigInteger)) {
            return asBigInteger(n).compareTo(asBigInteger(n2));
        }
        if (clazz.equals(NumberHelper.classBigDecimal)) {
            return asBigDecimal(n).compareTo(asBigDecimal(n2));
        }
        throw new IllegalArgumentException(String.valueOf("unknown subclass of java.lang.Number: ").concat(String.valueOf(clazz.getClass())));
    }
    
    static {
        NumberHelper.classInteger = new Integer(0).getClass();
        NumberHelper.classLong = new Long(0L).getClass();
        NumberHelper.classFloat = new Float(0.0f).getClass();
        NumberHelper.classDouble = new Double(0.0).getClass();
        NumberHelper.classByte = new Byte((byte)0).getClass();
        NumberHelper.classShort = new Short((short)0).getClass();
        NumberHelper.classBigInteger = new BigInteger("0").getClass();
        NumberHelper.classBigDecimal = new BigDecimal("0").getClass();
        NumberHelper.classNumber = new Integer(0).getClass().getSuperclass();
    }
}
