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
    
    public WvEventSender(final WvDispatcher wvDispatcher) {
        super(wvDispatcher);
        this.asyncCommandVector = new Vector();
    }
    
    private String readUnicode(final DataInputStream dataInputStream) {
        int n;
        char[] array;
        int i;
        int unsignedShort;
        for (n = 5120, array = new char[n], i = 0; i < n; array[i++] = (char)unsignedShort) {
            try {
                unsignedShort = dataInputStream.readUnsignedShort();
            }
            catch (Exception ex) {
                break;
            }
            if (unsignedShort == 0) {
                break;
            }
        }
        return new String(array, 0, i);
    }
    
    private URLConnection getURLConnection(final URL url) throws Exception {
        int n = 10;
        try {
            return url.openConnection();
        }
        catch (NullPointerException ex2) {
            Label_0029: {
                try {
                    Thread.sleep(100L);
                    break Label_0029;
                }
                catch (Exception ex3) {
                    break Label_0029;
                }
                throw;
            }
            if (--n <= 0) {
                return null;
            }
            return url.openConnection();
        }
        catch (Exception ex) {}
    }
    
    public void run() {
        int n = 100;
        while (!super.cameraConnected) {
            this.sleepWhile(200L);
            if (n == 0) {
                return;
            }
            if (--n < 0) {
                break;
            }
        }
    Label_0159_Outer:
        while (super.wvDispatcher.getRunnable()) {
            this.sleepWhile(200L);
            Thread.yield();
            String overWriteCommandStr = null;
            synchronized (this) {
                if (this.overWriteCommandStr != null) {
                    overWriteCommandStr = this.overWriteCommandStr;
                    this.overWriteCommandStr = null;
                }
            }
            if (overWriteCommandStr != null) {
                this.syncSendCommand(overWriteCommandStr);
            }
            if (this.asyncCommandChanged) {
                this.asyncCommandChanged = false;
                while (true) {
                    final Vector vector;
                    Label_0179: {
                        synchronized (this.asyncCommandVector) {
                            vector = (Vector)this.asyncCommandVector.clone();
                            this.asyncCommandVector.removeAllElements();
                            // monitorexit(this.asyncCommandVector)
                            break Label_0179;
                        }
                        final String s = vector.elementAt(0);
                        vector.removeElementAt(0);
                        this.syncSendCommand(s);
                    }
                    if (vector.isEmpty()) {
                        continue Label_0159_Outer;
                    }
                    continue;
                }
            }
        }
    }
    
    public final synchronized void asyncOverWriteCommand(final String overWriteCommandStr) {
        this.overWriteCommandStr = overWriteCommandStr;
    }
    
    public final String syncSendCommand(final String s) {
        if (super.idStr == null) {
            return null;
        }
        final String string = super.wvDispatcher.getUrlBaseStr() + s.trim();
        String s2;
        if (s.indexOf("?") != -1) {
            s2 = string + "&" + super.idStr;
        }
        else {
            s2 = string + "?" + super.idStr;
        }
        String unicode;
        try {
            WvDebug.println("WvEventSender:" + s2);
            final URLConnection urlConnection = this.getURLConnection(new URL(s2));
            if (urlConnection == null) {
                WvDebug.println("WvEventSender: failed to connect to server");
                super.wvDispatcher.stopAll();
                return null;
            }
            urlConnection.setUseCaches(false);
            urlConnection.setDefaultUseCaches(false);
            final String headerField = urlConnection.getHeaderField("Livescope-Status");
            if (headerField == null) {
                WvDebug.println("WvEventSender: failed to get to status header");
                return null;
            }
            if (!headerField.startsWith("0")) {
                WvDebug.println("WvEventSender:Livescope-Status :" + headerField);
                return null;
            }
            final DataInputStream dataInputStream = new DataInputStream(urlConnection.getInputStream());
            if (s.indexOf("character_set=unicode") != -1) {
                unicode = this.readUnicode(dataInputStream);
            }
            else {
                final StringBuffer sb = new StringBuffer();
                String line;
                while ((line = dataInputStream.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                unicode = new String(sb);
            }
            dataInputStream.close();
        }
        catch (Exception ex) {
            WvDebug.println("WvEventSender:syncSendCommand:" + ex);
            super.wvDispatcher.stopAll();
            return null;
        }
        return unicode;
    }
}
