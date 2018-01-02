// 
// Decompiled by Procyon v0.5.30
// 

class PGC_Utility
{
    public static Double DoubleValue(final String s) {
        Double value = null;
        if (s != null) {
            try {
                value = Double.valueOf(s);
            }
            catch (NumberFormatException ex) {}
        }
        return value;
    }
    
    public static Integer IntegerValue(final String s) {
        Integer value = null;
        if (s != null) {
            try {
                value = Integer.valueOf(s);
            }
            catch (NumberFormatException ex) {}
        }
        return value;
    }
    
    public static Integer IntegerValue(final String s, final int n) {
        Integer value = null;
        if (s != null) {
            try {
                value = Integer.valueOf(s, n);
            }
            catch (NumberFormatException ex) {}
        }
        return value;
    }
    
    public static double DValue(final String s) {
        final Double doubleValue = DoubleValue(s);
        double doubleValue2;
        if (doubleValue == null) {
            doubleValue2 = 0.0;
        }
        else {
            doubleValue2 = doubleValue;
        }
        return doubleValue2;
    }
    
    public static int IValue(final String s) {
        final Integer integerValue = IntegerValue(s);
        int intValue;
        if (integerValue == null) {
            intValue = 0;
        }
        else {
            intValue = integerValue;
        }
        return intValue;
    }
    
    public static int IValue(final String s, final int n) {
        final Integer integerValue = IntegerValue(s, n);
        int intValue;
        if (integerValue == null) {
            intValue = 0;
        }
        else {
            intValue = integerValue;
        }
        return intValue;
    }
}
