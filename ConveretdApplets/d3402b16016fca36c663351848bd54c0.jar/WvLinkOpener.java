import java.net.URLConnection;
import java.io.DataInputStream;
import java.util.StringTokenizer;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class WvLinkOpener implements Runnable
{
    private WvDispatcher wvDispatcher;
    private String idStr;
    private Thread thread;
    
    public void stop() {
        if (this.thread != null && this.thread.isAlive()) {
            this.thread.stop();
        }
        this.thread = null;
    }
    
    private int doOpenServer() {
        final String urlBaseStr = this.wvDispatcher.getUrlBaseStr();
        final String vcHostStr = this.wvDispatcher.getVcHostStr();
        final String ccHostStr = this.wvDispatcher.getCcHostStr();
        final int vcPort = this.wvDispatcher.getVcPort();
        final int ccPort = this.wvDispatcher.getCcPort();
        String s = urlBaseStr + "OpenCameraServer".trim();
        final Object object = this.wvDispatcher.getObject("version");
        if (object != null && object instanceof String) {
            s = s + "?client_version=" + (String)object;
        }
        final Object object2 = this.wvDispatcher.getObject("captureSize");
        if (object2 != null && object2 instanceof String) {
            s = s + "&image_size=" + (String)object2;
        }
        if (vcHostStr != null) {
            s = s + "&VC_HOST=" + vcHostStr;
        }
        if (ccHostStr != null) {
            s = s + "&CC_HOST=" + ccHostStr;
        }
        if (vcPort > 0) {
            s = s + "&VC_PORT=" + vcPort;
        }
        if (ccPort > 0) {
            s = s + "&CC_PORT=" + ccPort;
        }
        if (this.wvDispatcher.openParams != null) {
            for (int i = 0; i < this.wvDispatcher.openParams.length; ++i) {
                s = s + "&" + this.wvDispatcher.openParams[i];
            }
        }
        int int1;
        try {
            final URL url = new URL(s);
            WvDebug.println(url.toString());
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            openConnection.setDefaultUseCaches(false);
            final String headerField = openConnection.getHeaderField("Livescope-Status");
            WvDebug.println("OpenCameraServer:Livescope-Status:" + headerField);
            try {
                int1 = Integer.parseInt(new StringTokenizer(headerField).nextToken());
            }
            catch (Exception ex2) {
                return 10012;
            }
            switch (int1) {
                default: {
                    return int1;
                }
                case 0:
                case 106: {
                    final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
                    String line;
                    while ((line = dataInputStream.readLine()) != null) {
                        if (line.startsWith("connection_id=")) {
                            WvDebug.println(this.idStr = line);
                            break;
                        }
                    }
                    dataInputStream.close();
                    break;
                }
            }
        }
        catch (Exception ex) {
            WvDebug.println("doOpenServer():" + ex);
            return 10012;
        }
        return int1;
    }
    
    public void start() {
        (this.thread = new Thread(this)).start();
    }
    
    public WvLinkOpener(final WvDispatcher wvDispatcher) {
        this.wvDispatcher = wvDispatcher;
    }
    
    public void run() {
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {}
        final int doOpenServer = this.doOpenServer();
        WvEvent wvEvent = null;
        switch (doOpenServer) {
            case 106: {
                System.out.println("possible client version mismatch.");
            }
            case 0: {
                wvEvent = new WvEvent(10002, this.idStr);
                break;
            }
            default: {
                wvEvent = new WvEvent(10011, doOpenServer);
                break;
            }
        }
        this.wvDispatcher.postEvent(wvEvent);
    }
}
