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
        int i = 10012;
        final String s = this.wvDispatcher.getUrlBaseStr();
        final String s2 = this.wvDispatcher.getVcHostStr();
        final String s3 = this.wvDispatcher.getCcHostStr();
        final int j = this.wvDispatcher.getVcPort();
        final int k = this.wvDispatcher.getCcPort();
        String s4 = String.valueOf(s) + "OpenCameraServer".trim();
        Object obj = this.wvDispatcher.getObject("version");
        if (obj != null && obj instanceof String) {
            s4 = String.valueOf(s4) + "?client_version=" + (String)obj;
        }
        obj = this.wvDispatcher.getObject("captureSize");
        if (obj != null && obj instanceof String) {
            s4 = String.valueOf(s4) + "&image_size=" + (String)obj;
        }
        if (s2 != null) {
            s4 = String.valueOf(s4) + "&VC_HOST=" + s2;
        }
        if (s3 != null) {
            s4 = String.valueOf(s4) + "&CC_HOST=" + s3;
        }
        if (j > 0) {
            s4 = String.valueOf(s4) + "&VC_PORT=" + j;
        }
        if (k > 0) {
            s4 = String.valueOf(s4) + "&CC_PORT=" + k;
        }
        if (this.wvDispatcher.openParams != null) {
            for (int l = 0; l < this.wvDispatcher.openParams.length; ++l) {
                s4 = String.valueOf(s4) + "&" + this.wvDispatcher.openParams[l];
            }
        }
        try {
            final URL url = new URL(s4);
            WvDebug.println(url.toString());
            final URLConnection urlconnection = url.openConnection();
            urlconnection.setUseCaches(false);
            urlconnection.setDefaultUseCaches(false);
            final String s5 = urlconnection.getHeaderField("Livescope-Status");
            WvDebug.println("OpenCameraServer:Livescope-Status:" + s5);
            try {
                final StringTokenizer stringtokenizer = new StringTokenizer(s5);
                i = Integer.parseInt(stringtokenizer.nextToken());
            }
            catch (Exception _ex) {
                return 10012;
            }
            switch (i) {
                default: {
                    return i;
                }
                case 0:
                case 106: {
                    final DataInputStream datainputstream = new DataInputStream(urlconnection.getInputStream());
                    String s6 = null;
                    while ((s6 = datainputstream.readLine()) != null) {
                        if (s6.startsWith("connection_id=")) {
                            WvDebug.println(this.idStr = s6);
                            break;
                        }
                    }
                    datainputstream.close();
                    break;
                }
            }
        }
        catch (Exception exception) {
            WvDebug.println("doOpenServer():" + exception);
            return 10012;
        }
        return i;
    }
    
    public void start() {
        (this.thread = new Thread(this)).start();
    }
    
    public WvLinkOpener(final WvDispatcher wvdispatcher) {
        this.wvDispatcher = wvdispatcher;
    }
    
    public void run() {
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {}
        final int i = this.doOpenServer();
        WvEvent wvevent = null;
        switch (i) {
            case 106: {
                System.out.println("possible client version mismatch.");
            }
            case 0: {
                wvevent = new WvEvent(10002, this.idStr);
                break;
            }
            default: {
                wvevent = new WvEvent(10011, i);
                break;
            }
        }
        this.wvDispatcher.postEvent(wvevent);
    }
}
