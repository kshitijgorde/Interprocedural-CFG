// 
// Decompiled by Procyon v0.5.30
// 

public class WvVideoState implements WvEventListener, WvPresetListener
{
    protected static final int OPENING = 0;
    protected static final int CLOSED = 1;
    protected static final int LIVE = 2;
    protected static final int ERROR = 3;
    protected static final int SWITCH = 4;
    private int state;
    private int prevState;
    private String message;
    private String hostname;
    private boolean onceConnected;
    private WvDispatcher wvDispatcher;
    
    public void kickOff() {
        this.onceConnected = false;
        final String stringObject = this.wvDispatcher.getStringObject("connect_msg");
        this.message = stringObject;
        if (stringObject == null || (this.wvDispatcher.isEnglish() && !this.wvDispatcher.IsAscii(this.message))) {
            if (this.hostname != null) {
                this.message = "connecting to\n" + this.hostname;
            }
            else {
                this.message = "now connecting";
            }
        }
        this.setState(0);
    }
    
    protected int getState() {
        return this.state;
    }
    
    public void cameraConnected(final boolean flag) {
    }
    
    public void cameraSelected(final String s) {
        final String stringObject = this.wvDispatcher.getStringObject("cam_switch_msg");
        this.message = stringObject;
        if (stringObject == null || (this.wvDispatcher.isEnglish() && !this.wvDispatcher.IsAscii(this.message))) {
            this.message = "camera switching\nwait a minute";
        }
        this.setState(4);
    }
    
    protected int getPrevState() {
        return this.prevState;
    }
    
    private void setState(final int i) {
        if (i != this.state) {
            this.prevState = this.state;
            switch (this.state = i) {
                case 3: {
                    this.wvDispatcher.setStatus(4);
                }
            }
        }
    }
    
    protected WvVideoState(final WvDispatcher wvdispatcher) {
        this.state = 1;
        this.prevState = 1;
        final Object obj = wvdispatcher.getObject("hostname");
        if (obj != null && obj instanceof String) {
            this.hostname = (String)obj;
            if (this.hostname.length() <= 0) {
                this.hostname = null;
            }
        }
        else {
            this.hostname = null;
        }
        this.wvDispatcher = wvdispatcher;
        this.init();
    }
    
    public void disconnect(final int i) {
        switch (i) {
            case 503: {
                final String stringObject = this.wvDispatcher.getStringObject("too_many_msg");
                this.message = stringObject;
                if (stringObject == null || (this.wvDispatcher.isEnglish() && !this.wvDispatcher.IsAscii(this.message))) {
                    this.message = "too many clients";
                }
                this.setState(3);
            }
            case 105: {
                final String stringObject2 = this.wvDispatcher.getStringObject("error_msg");
                this.message = stringObject2;
                if (stringObject2 == null || (this.wvDispatcher.isEnglish() && !this.wvDispatcher.IsAscii(this.message))) {
                    this.message = "can't connect\nversion mismatched";
                }
                this.setState(3);
            }
            default: {
                if (this.onceConnected) {
                    final String stringObject3 = this.wvDispatcher.getStringObject("disconnect_msg");
                    this.message = stringObject3;
                    if (stringObject3 == null || (this.wvDispatcher.isEnglish() && !this.wvDispatcher.IsAscii(this.message))) {
                        this.message = "disconnected";
                        if (this.hostname != null) {
                            this.message = String.valueOf(this.message) + " from\n" + this.hostname;
                        }
                    }
                    this.setState(1);
                    return;
                }
                final String stringObject4 = this.wvDispatcher.getStringObject("error_msg");
                this.message = stringObject4;
                if (stringObject4 == null || (this.wvDispatcher.isEnglish() && !this.wvDispatcher.IsAscii(this.message))) {
                    this.message = "can't connect";
                    if (this.hostname != null) {
                        this.message = String.valueOf(this.message) + " to\n" + this.hostname;
                    }
                }
                this.setState(3);
            }
        }
    }
    
    protected String getMessage() {
        return this.message;
    }
    
    public void videoConnected(final boolean flag) {
        if (flag) {
            this.onceConnected = true;
            this.message = null;
            this.setState(2);
        }
    }
    
    public void presetCameraOperationFinished(final String s) {
        this.message = null;
        this.setState(2);
    }
    
    public void init() {
        this.message = this.wvDispatcher.getStringObject("no_connect_msg");
        if (this.wvDispatcher.isEnglish() && !this.wvDispatcher.IsAscii(this.message)) {
            this.message = "no connection";
        }
        this.state = 1;
        this.prevState = 1;
    }
    
    public void cameraChanged(final int i) {
        this.message = null;
        this.setState(2);
    }
    
    public void cameraWillChanged() {
        final String stringObject = this.wvDispatcher.getStringObject("cam_switch_msg");
        this.message = stringObject;
        if (stringObject == null || (this.wvDispatcher.isEnglish() && !this.wvDispatcher.IsAscii(this.message))) {
            this.message = "camera switching\nwait a minute";
        }
        this.setState(4);
    }
    
    public void connect(final String s) {
    }
}
