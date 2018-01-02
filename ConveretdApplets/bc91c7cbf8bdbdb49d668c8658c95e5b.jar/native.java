// 
// Decompiled by Procyon v0.5.30
// 

public class native
{
    public static final String ha;
    private static String Ea = "\u8745\u8769\u8776\u877f\u8774\u876f\u8761\u876e\u8772\u873c\u8726\u875c\u8769\u8774\u8767\u8768\u8726\u8755\u8767\u8770\u8769\u8770\u876f\u8765\u8726\u8734\u8736\u8736\u8736\u872b\u8734\u8736\u8736\u8737\u8726\u873a\u8760\u876a\u8769\u8771\u8728\u8768\u8763\u8772\u8760\u876f\u8774\u876b\u8775\u8728\u8765\u8769\u876b\u8738\u8728\u8726\u8747\u876a\u876a\u8726\u8774\u876f\u8761\u876e\u8772\u8775\u8726\u8774\u8763\u8775\u8763\u8774\u8770\u8763\u8762";
    
    static {
        native.Ea = _(native.Ea);
        ha = new String(native.Ea);
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u8706');
        }
        return new String(array);
    }
}
