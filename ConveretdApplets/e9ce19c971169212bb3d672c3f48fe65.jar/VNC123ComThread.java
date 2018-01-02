import java.net.URL;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class VNC123ComThread extends Thread
{
    private VNC123HttpConnector httpConnector;
    private VNC123LocalConnector localConnector;
    private String comType;
    private boolean stop;
    
    public VNC123ComThread(final Socket localSocket, final URL codeBase, final String sessionId, final byte[] aesKey, final String type, final String appletType) throws Exception {
        this.httpConnector = null;
        this.localConnector = null;
        this.comType = "";
        this.stop = false;
        this.httpConnector = new VNC123HttpConnector(codeBase, sessionId, aesKey, appletType);
        this.localConnector = new VNC123LocalConnector(localSocket);
        this.comType = type;
    }
    
    public void run() {
        try {
            if (this.comType.equalsIgnoreCase("LocalToHub")) {
                System.out.println(" --- Running LocalToHubThread ---");
                while (!this.stop) {
                    this.httpConnector.sendToVNCHub(this.localConnector.readFromVNCLocal());
                }
            }
            else if (this.comType.equalsIgnoreCase("HubToLocal")) {
                System.out.println(" --- Running HubToLocalThread --- ");
                while (!this.stop) {
                    this.localConnector.sendToVNCLocal(this.httpConnector.readFromVNCHub());
                }
            }
            else {
                System.err.println("VNC123ComThread error : Bad communicationType : [" + this.comType + "]");
            }
        }
        catch (Exception e) {
            System.err.println("VNC123ComThread error : " + e);
            e.printStackTrace();
        }
    }
    
    public void stopMe() {
        this.stop = true;
    }
}
