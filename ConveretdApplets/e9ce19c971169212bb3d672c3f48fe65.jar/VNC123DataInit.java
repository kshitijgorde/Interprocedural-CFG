import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import com.vncproxy.web.tools.VNC123Session;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class VNC123DataInit implements Serializable
{
    private static final long serialVersionUID = 1234567L;
    public String sessionId;
    public String action;
    public String error;
    public String userHashCode;
    public byte[] key;
    public byte[] validationCode;
    public VNC123Session session;
    public String localHostname;
    public String localIPLan;
    public String localOSArch;
    public String localOSName;
    public String localOSVersion;
    public String localJavaVersion;
    public String localJavaVendor;
    public String localUserName;
    public String localBrowser;
    
    public VNC123DataInit(final String sessionId, final String action, final String userHashCode, final byte[] key) {
        this.sessionId = sessionId;
        this.action = action;
        this.error = null;
        this.userHashCode = userHashCode;
        this.session = null;
        this.key = null;
        if (key != null) {
            try {
                this.validationCode = this.cryptWithAES(("Validation OK" + System.currentTimeMillis()).getBytes("UTF-8"), key);
            }
            catch (Exception e) {
                e.printStackTrace();
                this.validationCode = null;
            }
        }
    }
    
    public VNC123DataInit(final String sessionId, final byte[] key) {
        this.sessionId = sessionId;
        this.action = null;
        this.error = null;
        this.userHashCode = null;
        this.session = null;
        this.key = key;
        this.validationCode = null;
    }
    
    public VNC123DataInit(final String error) {
        this.sessionId = null;
        this.action = null;
        this.error = error;
        this.userHashCode = null;
        this.session = null;
        this.key = null;
        this.validationCode = null;
    }
    
    public VNC123DataInit(final VNC123Session session) {
        this.sessionId = null;
        this.action = null;
        this.error = null;
        this.userHashCode = null;
        this.session = session;
        this.key = null;
        this.validationCode = null;
    }
    
    private byte[] cryptWithAES(final byte[] plaintext, final byte[] bytesKey) throws Exception {
        byte[] encrypted = null;
        final Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, new SecretKeySpec(bytesKey, "AES"));
        encrypted = cipher.doFinal(plaintext);
        return encrypted;
    }
}
