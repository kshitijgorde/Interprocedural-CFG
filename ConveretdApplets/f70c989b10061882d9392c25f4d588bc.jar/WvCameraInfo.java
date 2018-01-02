import java.util.StringTokenizer;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvCameraInfo implements WvPropertyParser, WvEventListener, WvPresetListener, WvPTZBListener, WvFloorListener, WvUtilListener
{
    public static final int CONNECTING = 0;
    public static final int QUERYING = 1;
    public static final int WAITING = 2;
    public static final int CONTROLOK = 3;
    public static final int CONTROLNG = 4;
    public static final int CAMERANG = 5;
    public static final int DISCONNECT = 6;
    public static final int NOCONNECT = 7;
    protected int cameraState;
    protected boolean cameraEnable;
    private WvPTZB ptzb;
    private WvScope hardScope;
    private WvScope softScope;
    private WvScope viewScope;
    private Hashtable propertyTarget;
    private int waitingTime;
    private int controlTime;
    private WvDispatcher wvDispatcher;
    private String cameraType;
    
    public void connect(final String s) {
    }
    
    public int getWaitingTime() {
        return this.waitingTime;
    }
    
    public void setWaitingTime(final int i) {
        this.waitingTime = i;
    }
    
    public void enabledCameraControl(final int i) {
        this.cameraState = 3;
        this.controlTime = i;
    }
    
    public void disabledCameraControl() {
        this.cameraState = 4;
        final String s = this.wvDispatcher.syncSendCommand("GetCameraInfo".trim());
        this.parseCameraParameters(s);
        this.wvDispatcher.postEvent(new WvEvent(42, this.ptzb));
    }
    
    public int getCameraState() {
        return this.cameraState;
    }
    
    public void cameraConnected(final boolean flag) {
        this.cameraState = (flag ? 4 : 6);
    }
    
    public void cameraSelected(final String s) {
    }
    
    public boolean connected() {
        return this.cameraState != 0 && this.cameraState != 6 && this.cameraState != 7;
    }
    
    public void setCameraState(final int i) {
        this.cameraState = i;
    }
    
    public boolean hasFloor() {
        return this.cameraState == 3;
    }
    
    public void disconnect(final int i) {
        this.cameraState = 6;
        this.cameraEnable = false;
    }
    
    public WvScope getViewScope() {
        return this.viewScope;
    }
    
    public int getTiltValue() {
        return this.ptzb.tilt;
    }
    
    public int getPanValue() {
        return this.ptzb.pan;
    }
    
    public void videoConnected(final boolean flag) {
    }
    
    public void ptzbChangedInternal(final Object obj, final int i, final int j) {
    }
    
    public int getZoomValue() {
        return this.ptzb.zoom;
    }
    
    public int getMaxValue(final int i, final int j) {
        WvScope wvscope = null;
        switch (j) {
            case 0: {
                wvscope = this.hardScope;
                break;
            }
            case 1: {
                wvscope = this.softScope;
                break;
            }
            case 2: {
                wvscope = this.viewScope;
                break;
            }
            default: {
                Thread.currentThread();
                Thread.dumpStack();
                throw new IllegalArgumentException();
            }
        }
        int k = 0;
        switch (i) {
            case 0: {
                k = wvscope.getPanMax();
                break;
            }
            case 1: {
                k = wvscope.getTiltMax();
                break;
            }
            case 2: {
                k = wvscope.getZoomMax();
                break;
            }
            default: {
                Thread.currentThread();
                Thread.dumpStack();
                throw new IllegalArgumentException();
            }
        }
        return k;
    }
    
    public void waitingCameraControl(final int i) {
        this.cameraState = 2;
        this.waitingTime = i;
    }
    
    public void failedToGetCameraControl() {
        this.cameraState = 4;
    }
    
    public WvScope getHardScope() {
        return this.hardScope;
    }
    
    public int getCurrentCameraId() {
        return this.ptzb.cameraId;
    }
    
    public boolean getBackLight() {
        return this.ptzb.backLight;
    }
    
    public void cameraChanged(final int i) {
        this.ptzb.cameraId = i;
        final String s = this.wvDispatcher.syncSendCommand("GetCameraInfo".trim());
        this.parseCameraParameters(s);
        final String s2 = this.wvDispatcher.syncSendCommand("GetVideoInfo".trim());
        if (s2 != null) {
            final StringTokenizer stringtokenizer = new StringTokenizer(s2, "\r\n=");
            while (stringtokenizer.hasMoreTokens()) {
                final String s3 = stringtokenizer.nextToken();
                if (s3.equals("image_height")) {
                    this.wvDispatcher.putObject("image_height", new Integer(stringtokenizer.nextToken()));
                }
                else {
                    if (!s3.equals("image_width")) {
                        continue;
                    }
                    this.wvDispatcher.putObject("image_width", new Integer(stringtokenizer.nextToken()));
                }
            }
        }
    }
    
    public void cameraWillChanged() {
    }
    
    public int getZoomHeight(final int i) {
        if (this.cameraType.compareTo("Celery") == 0 || this.cameraType.compareTo("Canon VB-C10") == 0 || this.cameraType.compareTo("Canon VB-C10R") == 0) {
            return i * 11 / 10;
        }
        return i;
    }
    
    public void kickOff() {
        this.cameraState = 0;
        this.cameraEnable = false;
    }
    
    public int getMinValue(final int i, final int j) {
        WvScope wvscope = null;
        switch (j) {
            case 0: {
                wvscope = this.hardScope;
                break;
            }
            case 1: {
                wvscope = this.softScope;
                break;
            }
            case 2: {
                wvscope = this.viewScope;
                break;
            }
            default: {
                Thread.currentThread();
                Thread.dumpStack();
                throw new IllegalArgumentException();
            }
        }
        int k = 0;
        switch (i) {
            case 0: {
                k = wvscope.getPanMin();
                break;
            }
            case 1: {
                k = wvscope.getTiltMin();
                break;
            }
            case 2: {
                k = wvscope.getZoomMin();
                break;
            }
            default: {
                Thread.currentThread();
                Thread.dumpStack();
                throw new IllegalArgumentException();
            }
        }
        return k;
    }
    
    public void ptzbChanged(final WvPTZB wvptzb) {
        this.ptzb.pan = wvptzb.pan;
        this.ptzb.tilt = wvptzb.tilt;
        this.ptzb.zoom = wvptzb.zoom;
        this.ptzb.backLight = wvptzb.backLight;
    }
    
    public int getControlTime() {
        return this.controlTime;
    }
    
    public void presetCameraOperationFinished(final String s) {
        this.parseCameraParameters(s);
    }
    
    public void imageSizeChanged(final String s) {
    }
    
    protected void parseCameraParameters(final String s) {
        if (s == null) {
            return;
        }
        final StringTokenizer stringtokenizer = new StringTokenizer(s, "\r\n");
        while (stringtokenizer.hasMoreElements()) {
            final String s2 = stringtokenizer.nextToken();
            final int i = s2.indexOf("=");
            if (i >= 0) {
                final String s3 = s2.substring(0, i);
                final String s4 = s2.substring(i + 1);
                final Object obj = this.propertyTarget.get(s3);
                if (obj == null || !(obj instanceof WvPropertyParser)) {
                    continue;
                }
                ((WvPropertyParser)obj).parseProperty(s3, s4);
            }
        }
        if (s.indexOf("view") == -1) {
            this.viewScope.setViewScope(this.hardScope, this.softScope);
        }
    }
    
    protected WvCameraInfo(final WvDispatcher wvdispatcher) {
        this.cameraEnable = true;
        this.ptzb = new WvPTZB();
        this.hardScope = new WvScope(this, 0);
        this.softScope = new WvScope(this, 1);
        this.viewScope = new WvScope(this, 2);
        this.propertyTarget = new Hashtable();
        this.waitingTime = 10000;
        this.controlTime = 10000;
        this.cameraType = "Canon VC-C4";
        this.wvDispatcher = wvdispatcher;
        for (int i = 0; i < WvScope.hardKey.length; ++i) {
            this.propertyTarget.put(WvScope.hardKey[i], this.hardScope);
        }
        for (int j = 0; j < WvScope.softKey.length; ++j) {
            this.propertyTarget.put(WvScope.softKey[j], this.softScope);
        }
        for (int k = 0; k < WvScope.viewKey.length; ++k) {
            this.propertyTarget.put(WvScope.viewKey[k], this.viewScope);
        }
        for (int l = 0; l < WvPTZB.key.length; ++l) {
            this.propertyTarget.put(WvPTZB.key[l], this.ptzb);
        }
        this.propertyTarget.put("camera_status", this);
        this.propertyTarget.put("camera_type", this);
    }
    
    public void cameraPower(final boolean flag) {
        this.cameraEnable = flag;
    }
    
    public void parseProperty(final String s, final String s1) {
        if (s.equals("camera_status")) {
            this.cameraEnable = s1.equals("enabled");
        }
        if (s.equals("camera_type")) {
            this.cameraType = s1;
        }
    }
    
    public void ptzbChangedByAnotherClient(final WvPTZB wvptzb) {
        this.ptzbChanged(wvptzb);
    }
    
    public boolean cameraEnabled() {
        return this.cameraEnable;
    }
    
    public int getPtzValue(final int i) {
        int j = 0;
        switch (i) {
            case 0: {
                j = this.ptzb.pan;
                break;
            }
            case 1: {
                j = this.ptzb.tilt;
                break;
            }
            case 2: {
                j = this.ptzb.zoom;
                break;
            }
            default: {
                Thread.currentThread();
                Thread.dumpStack();
                throw new IllegalArgumentException();
            }
        }
        return j;
    }
    
    public WvScope getSoftScope() {
        return this.softScope;
    }
}
