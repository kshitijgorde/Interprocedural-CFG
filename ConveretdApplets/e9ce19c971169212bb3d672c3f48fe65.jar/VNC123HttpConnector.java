import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.net.HttpURLConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class VNC123HttpConnector
{
    private byte READ;
    private byte SEND;
    private byte[] aesKey;
    private URL vncProxyHub;
    private byte[] bSessionId;
    private String appletType;
    
    VNC123HttpConnector(final URL codeBase, final String sessionId, final byte[] aesKey, final String appletType) throws Exception {
        this.READ = 0;
        this.SEND = 1;
        this.aesKey = new byte[16];
        this.vncProxyHub = null;
        this.bSessionId = new byte[4];
        this.appletType = "Server";
        this.bSessionId = this.intToByteArray(Integer.parseInt(sessionId));
        this.vncProxyHub = new URL(codeBase, "VNC123HubListener");
        this.aesKey = aesKey;
        this.appletType = appletType;
        if (this.appletType.equals("Viewer")) {
            this.READ = 1;
            this.SEND = 0;
        }
    }
    
    public byte[] readFromVNCHub() throws Exception {
        byte[] result = null;
        final HttpURLConnection con = this.getVNC123HubConnection();
        final DataOutputStream dos = new DataOutputStream(con.getOutputStream());
        dos.write(this.bSessionId);
        dos.write(this.READ);
        dos.flush();
        con.connect();
        final int responseCode = con.getResponseCode();
        final int contentLength = con.getContentLength();
        if (responseCode == 200 && contentLength > 1) {
            final DataInputStream dis = new DataInputStream(con.getInputStream());
            result = new byte[contentLength];
            dis.readFully(result);
            return this.decryptInBytesWithAES(result, this.aesKey);
        }
        return null;
    }
    
    public void sendToVNCHub(final byte[] bData) throws Exception {
        byte[] result = null;
        final HttpURLConnection con = this.getVNC123HubConnection();
        final DataOutputStream dos = new DataOutputStream(con.getOutputStream());
        dos.write(this.bSessionId);
        dos.write(this.SEND);
        dos.write(this.cryptWithAES(bData, this.aesKey));
        dos.flush();
        dos.close();
        con.connect();
        final int responseCode = con.getResponseCode();
        if (responseCode == 200) {
            final int contentLength = con.getContentLength();
            final DataInputStream dis = new DataInputStream(con.getInputStream());
            result = new byte[contentLength];
            dis.readFully(result);
            dis.close();
        }
    }
    
    private HttpURLConnection getVNC123HubConnection() throws Exception {
        final HttpURLConnection con = (HttpURLConnection)this.vncProxyHub.openConnection();
        con.setDoOutput(true);
        con.setUseCaches(false);
        return con;
    }
    
    private byte[] cryptWithAES(final byte[] plaintext, final byte[] bytesKey) throws Exception {
        byte[] encrypted = null;
        final Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, new SecretKeySpec(bytesKey, "AES"));
        encrypted = cipher.doFinal(plaintext);
        return encrypted;
    }
    
    private byte[] decryptInBytesWithAES(final byte[] ciphertext, final byte[] bytesKey) throws Exception {
        byte[] decrypted = null;
        final Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, new SecretKeySpec(bytesKey, "AES"));
        decrypted = cipher.doFinal(ciphertext);
        return decrypted;
    }
    
    public int byteArrayToInt(final byte[] b, final int offset) {
        return (b[0] << 24) + ((b[1] & 0xFF) << 16) + ((b[2] & 0xFF) << 8) + (b[3] & 0xFF);
    }
    
    public byte[] intToByteArray(final int value) {
        return new byte[] { (byte)(value >>> 24), (byte)(value >> 16 & 0xFF), (byte)(value >> 8 & 0xFF), (byte)(value & 0xFF) };
    }
}
