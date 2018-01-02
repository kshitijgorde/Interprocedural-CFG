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
    
    public void setWaitingTime(final int waitingTime) {
        this.waitingTime = waitingTime;
    }
    
    public void enabledCameraControl(final int controlTime) {
        this.cameraState = 3;
        this.controlTime = controlTime;
    }
    
    public void disabledCameraControl() {
        this.cameraState = 4;
        this.parseCameraParameters(this.wvDispatcher.syncSendCommand("GetCameraInfo".trim()));
        this.wvDispatcher.postEvent(new WvEvent(42, this.ptzb));
    }
    
    public int getCameraState() {
        return this.cameraState;
    }
    
    public void cameraConnected(final boolean b) {
        this.cameraState = (b ? 4 : 6);
    }
    
    public void cameraSelected(final String s) {
    }
    
    public boolean connected() {
        return this.cameraState != 0 && this.cameraState != 6 && this.cameraState != 7;
    }
    
    public void setCameraState(final int cameraState) {
        this.cameraState = cameraState;
    }
    
    public boolean hasFloor() {
        return this.cameraState == 3;
    }
    
    public void disconnect(final int n) {
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
    
    public void videoConnected(final boolean b) {
    }
    
    public void ptzbChangedInternal(final Object o, final int n, final int n2) {
    }
    
    public int getZoomValue() {
        return this.ptzb.zoom;
    }
    
    public int getMaxValue(final int n, final int n2) {
        WvScope wvScope = null;
        switch (n2) {
            case 0: {
                wvScope = this.hardScope;
                break;
            }
            case 1: {
                wvScope = this.softScope;
                break;
            }
            case 2: {
                wvScope = this.viewScope;
                break;
            }
            default: {
                Thread.currentThread();
                Thread.dumpStack();
                throw new IllegalArgumentException();
            }
        }
        int n3 = 0;
        switch (n) {
            case 0: {
                n3 = wvScope.getPanMax();
                break;
            }
            case 1: {
                n3 = wvScope.getTiltMax();
                break;
            }
            case 2: {
                n3 = wvScope.getZoomMax();
                break;
            }
            default: {
                Thread.currentThread();
                Thread.dumpStack();
                throw new IllegalArgumentException();
            }
        }
        return n3;
    }
    
    public void waitingCameraControl(final int waitingTime) {
        this.cameraState = 2;
        this.waitingTime = waitingTime;
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
    
    public void cameraChanged(final int cameraId) {
        this.ptzb.cameraId = cameraId;
        this.parseCameraParameters(this.wvDispatcher.syncSendCommand("GetCameraInfo".trim()));
        final String syncSendCommand = this.wvDispatcher.syncSendCommand("GetVideoInfo".trim());
        if (syncSendCommand != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(syncSendCommand, "\r\n=");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.equals("image_height")) {
                    this.wvDispatcher.putObject("image_height", new Integer(stringTokenizer.nextToken()));
                }
                else {
                    if (!nextToken.equals("image_width")) {
                        continue;
                    }
                    this.wvDispatcher.putObject("image_width", new Integer(stringTokenizer.nextToken()));
                }
            }
        }
    }
    
    public void cameraWillChanged() {
    }
    
    public int getZoomHeight(final int n) {
        if (this.cameraType.compareTo("Celery") == 0 || this.cameraType.compareTo("Canon VB-C10") == 0 || this.cameraType.compareTo("Canon VB-C10R") == 0) {
            return n * 11 / 10;
        }
        return n;
    }
    
    public void kickOff() {
        this.cameraState = 0;
        this.cameraEnable = false;
    }
    
    public int getMinValue(final int n, final int n2) {
        WvScope wvScope = null;
        switch (n2) {
            case 0: {
                wvScope = this.hardScope;
                break;
            }
            case 1: {
                wvScope = this.softScope;
                break;
            }
            case 2: {
                wvScope = this.viewScope;
                break;
            }
            default: {
                Thread.currentThread();
                Thread.dumpStack();
                throw new IllegalArgumentException();
            }
        }
        int n3 = 0;
        switch (n) {
            case 0: {
                n3 = wvScope.getPanMin();
                break;
            }
            case 1: {
                n3 = wvScope.getTiltMin();
                break;
            }
            case 2: {
                n3 = wvScope.getZoomMin();
                break;
            }
            default: {
                Thread.currentThread();
                Thread.dumpStack();
                throw new IllegalArgumentException();
            }
        }
        return n3;
    }
    
    public void ptzbChanged(final WvPTZB wvPTZB) {
        this.ptzb.pan = wvPTZB.pan;
        this.ptzb.tilt = wvPTZB.tilt;
        this.ptzb.zoom = wvPTZB.zoom;
        this.ptzb.backLight = wvPTZB.backLight;
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
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\r\n");
        while (stringTokenizer.hasMoreElements()) {
            final String nextToken = stringTokenizer.nextToken();
            final int index = nextToken.indexOf("=");
            if (index >= 0) {
                final String substring = nextToken.substring(0, index);
                final String substring2 = nextToken.substring(index + 1);
                final Object value = this.propertyTarget.get(substring);
                if (value == null || !(value instanceof WvPropertyParser)) {
                    continue;
                }
                ((WvPropertyParser)value).parseProperty(substring, substring2);
            }
        }
        if (s.indexOf("view") == -1) {
            this.viewScope.setViewScope(this.hardScope, this.softScope);
        }
    }
    
    protected WvCameraInfo(final WvDispatcher wvDispatcher) {
        this.cameraEnable = true;
        this.ptzb = new WvPTZB();
        this.hardScope = new WvScope(this, 0);
        this.softScope = new WvScope(this, 1);
        this.viewScope = new WvScope(this, 2);
        this.propertyTarget = new Hashtable();
        this.waitingTime = 10000;
        this.controlTime = 10000;
        this.cameraType = "Canon VC-C4";
        this.wvDispatcher = wvDispatcher;
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
    
    public void cameraPower(final boolean cameraEnable) {
        this.cameraEnable = cameraEnable;
    }
    
    public void parseProperty(final String s, final String cameraType) {
        if (s.equals("camera_status")) {
            this.cameraEnable = cameraType.equals("enabled");
        }
        if (s.equals("camera_type")) {
            this.cameraType = cameraType;
        }
    }
    
    public void ptzbChangedByAnotherClient(final WvPTZB wvPTZB) {
        this.ptzbChanged(wvPTZB);
    }
    
    public boolean cameraEnabled() {
        return this.cameraEnable;
    }
    
    public int getPtzValue(final int n) {
        int n2 = 0;
        switch (n) {
            case 0: {
                n2 = this.ptzb.pan;
                break;
            }
            case 1: {
                n2 = this.ptzb.tilt;
                break;
            }
            case 2: {
                n2 = this.ptzb.zoom;
                break;
            }
            default: {
                Thread.currentThread();
                Thread.dumpStack();
                throw new IllegalArgumentException();
            }
        }
        return n2;
    }
    
    public WvScope getSoftScope() {
        return this.softScope;
    }
}
