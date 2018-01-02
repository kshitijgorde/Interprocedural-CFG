import java.io.InputStream;
import java.io.DataOutputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class VNC123LocalConnector
{
    private Socket localSocket;
    
    public VNC123LocalConnector(final Socket localSocket) {
        this.localSocket = null;
        this.localSocket = localSocket;
    }
    
    public void sendToVNCLocal(final byte[] out) throws Exception {
        DataOutputStream dos = null;
        dos = new DataOutputStream(this.localSocket.getOutputStream());
        if (out != null) {
            dos.write(out);
        }
    }
    
    public byte[] readFromVNCLocal() throws Exception {
        byte[] result = null;
        final InputStream in = this.localSocket.getInputStream();
        while (in.available() < 1) {
            Thread.sleep(2L);
        }
        result = new byte[in.available()];
        in.read(result, 0, result.length);
        return result;
    }
    
    public boolean isBound() throws Exception {
        return this.localSocket.isBound();
    }
}
