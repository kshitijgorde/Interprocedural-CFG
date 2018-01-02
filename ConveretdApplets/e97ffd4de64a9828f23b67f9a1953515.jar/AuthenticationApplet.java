import java.awt.Graphics;
import java.security.MessageDigest;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class AuthenticationApplet extends Applet
{
    static final long serialVersionUID = -1L;
    private String errorMsg;
    private String uniqueClientId;
    
    public AuthenticationApplet() {
        this.uniqueClientId = null;
        this.errorMsg = null;
    }
    
    public void start() {
        String string;
        try {
            string = System.getProperty("java.home") + System.getProperty("os.arch") + System.getProperty("user.dir") + System.getProperty("user.home") + System.getProperty("user.name") + System.getProperty("os.name");
        }
        catch (SecurityException ex) {
            this.errorMsg = "No se puede procesar el certificado de autenticidad";
            throw new RuntimeException(this.errorMsg, ex);
        }
        catch (Exception ex2) {
            this.errorMsg = "Problemas al elaborar el certificado de autenticidad";
            throw new RuntimeException(this.errorMsg, ex2);
        }
        if (string != null && string.length() > 0) {
            this.uniqueClientId = md5(string);
        }
    }
    
    public String getUniqueClientId() {
        return this.uniqueClientId;
    }
    
    public String getErrorMsg() {
        return this.errorMsg;
    }
    
    private static String getString(final byte[] array) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(0xFF & array[i]);
            if (i + 1 < array.length) {
                sb.append("-");
            }
        }
        return sb.toString();
    }
    
    private static String md5(final String s) {
        try {
            return getString(MessageDigest.getInstance("MD5").digest(s.getBytes()));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawString("Identificaci\u00f3n de computadora: habilitado", 10, 10);
    }
}
