import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;
import java.io.DataInputStream;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class WvEventSender extends WvHttpTalker
{
    public static final String CloseCameraServer = "CloseCameraServer";
    public static final String OperateCamera = "OperateCamera";
    public static final String OperateCameraOnScreen = "OperateCameraOnScreen";
    public static final String OpenCameraServer = "OpenCameraServer";
    public static final String GetCameraControl = "GetCameraControl";
    public static final String GetLiveImage = "GetLiveImage";
    public static final String GetNotice = "GetNotice";
    public static final String GetCameraInfo = "GetCameraInfo";
    public static final String GetSystemInfo = "GetSystemInfo";
    public static final String GetVideoInfo = "GetVideoInfo";
    public static final String GetOneShot = "GetOneShot";
    public static final String GetStillImage = "GetStillImage";
    public static final String GetCameraList = "GetCameraList";
    public static final String GetCameraPositionList = "GetCameraPositionList";
    public static final String GetPresetList = "GetPresetList";
    public static final String GetCameraPositionInfo = "GetCameraPositionInfo";
    public static final String GetPanoramaImage = "GetPanoramaImage";
    public static final String GetPanoramaInfo = "GetPanoramaInfo";
    public static final String GetCameraServerInfo = "GetCameraServerInfo";
    public static final String ChangeImageSize = "ChangeImageSize";
    public static final String GetPanoramaList = "GetPanoramaList";
    private String overWriteCommandStr;
    private boolean asyncCommandChanged;
    private Vector asyncCommandVector;
    
    public final void asyncStackCommand(final String s) {
        synchronized (this.asyncCommandVector) {
            this.asyncCommandVector.addElement(s);
            this.asyncCommandChanged = true;
        }
        // monitorexit(this.asyncCommandVector)
    }
    
    protected String getThreadName() {
        return "LiveApplet-WvEventSender";
    }
    
    public WvEventSender(final WvDispatcher wvdispatcher) {
        super(wvdispatcher);
        this.asyncCommandVector = new Vector();
    }
    
    private String readUnicode(final DataInputStream datainputstream) {
        char c;
        char[] ac;
        int i;
        int j;
        for (c = '\u1400', ac = new char[c], i = 0; i < c; ac[i++] = (char)j) {
            try {
                j = datainputstream.readUnsignedShort();
            }
            catch (Exception _ex) {
                break;
            }
            if (j == 0) {
                break;
            }
        }
        return new String(ac, 0, i);
    }
    
    private URLConnection getURLConnection(final URL url) throws Exception {
        int i = 10;
        try {
            final URLConnection urlconnection = url.openConnection();
            return urlconnection;
        }
        catch (NullPointerException ex) {
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex2) {}
            if (--i <= 0) {
                return null;
            }
            return url.openConnection();
        }
        catch (Exception ex3) {}
    }
    
    public void run() {
        int i = 100;
        while (true) {
            while (!super.cameraConnected) {
                this.sleepWhile(200L);
                if (i == 0) {
                    return;
                }
                if (--i < 0) {
                Label_0134_Outer:
                    while (super.wvDispatcher.getRunnable()) {
                        this.sleepWhile(200L);
                        Thread.yield();
                        String s = null;
                        synchronized (this) {
                            if (this.overWriteCommandStr != null) {
                                s = this.overWriteCommandStr;
                                this.overWriteCommandStr = null;
                            }
                        }
                        if (s != null) {
                            this.syncSendCommand(s);
                        }
                        if (this.asyncCommandChanged) {
                            this.asyncCommandChanged = false;
                            while (true) {
                                final Vector vector;
                                Label_0156: {
                                    synchronized (this.asyncCommandVector) {
                                        vector = (Vector)this.asyncCommandVector.clone();
                                        this.asyncCommandVector.removeAllElements();
                                        // monitorexit(this.asyncCommandVector)
                                        break Label_0156;
                                    }
                                    final String s2 = vector.elementAt(0);
                                    vector.removeElementAt(0);
                                    this.syncSendCommand(s2);
                                }
                                if (vector.isEmpty()) {
                                    continue Label_0134_Outer;
                                }
                                continue;
                            }
                        }
                    }
                    return;
                }
            }
            continue;
        }
    }
    
    public final synchronized void asyncOverWriteCommand(final String s) {
        this.overWriteCommandStr = s;
    }
    
    public final String syncSendCommand(final String s) {
        if (super.idStr == null) {
            return null;
        }
        String s2 = String.valueOf(super.wvDispatcher.getUrlBaseStr()) + s.trim();
        if (s.indexOf("?") != -1) {
            s2 = String.valueOf(s2) + "&" + super.idStr;
        }
        else {
            s2 = String.valueOf(s2) + "?" + super.idStr;
        }
        final Object obj = null;
        String s3 = null;
        try {
            WvDebug.println("WvEventSender:" + s2);
            final URL url = new URL(s2);
            final URLConnection urlconnection = this.getURLConnection(url);
            if (urlconnection == null) {
                WvDebug.println("WvEventSender: failed to connect to server");
                super.wvDispatcher.stopAll();
                return null;
            }
            urlconnection.setUseCaches(false);
            urlconnection.setDefaultUseCaches(false);
            final String s4 = urlconnection.getHeaderField("Livescope-Status");
            if (s4 == null) {
                WvDebug.println("WvEventSender: failed to get to status header");
                return null;
            }
            if (!s4.startsWith("0")) {
                WvDebug.println("WvEventSender:Livescope-Status :" + s4);
                return null;
            }
            final InputStream inputstream = urlconnection.getInputStream();
            final DataInputStream datainputstream = new DataInputStream(inputstream);
            if (s.indexOf("character_set=unicode") != -1) {
                s3 = this.readUnicode(datainputstream);
            }
            else {
                final StringBuffer stringbuffer = new StringBuffer();
                String s5 = null;
                while ((s5 = datainputstream.readLine()) != null) {
                    stringbuffer.append(s5).append("\n");
                }
                s3 = new String(stringbuffer);
            }
            datainputstream.close();
        }
        catch (Exception exception) {
            WvDebug.println("WvEventSender:syncSendCommand:" + exception);
            super.wvDispatcher.stopAll();
            return null;
        }
        return s3;
    }
}
