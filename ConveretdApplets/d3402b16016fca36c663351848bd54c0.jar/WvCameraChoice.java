import java.awt.Event;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.awt.Color;
import java.util.Hashtable;
import java.awt.Choice;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvCameraChoice extends Choice implements WvEventListener, WvPresetListener, WvFloorListener
{
    private WvDispatcher wvDispatcher;
    private Hashtable presetHash;
    private String langAndCharset;
    private boolean bEnableRemoveAll;
    
    public void kickOff() {
        this.disable();
    }
    
    public void enabledCameraControl(final int n) {
        if (this.countItems() > 1) {
            this.setBackground(Color.white);
            this.enable();
        }
    }
    
    public void disabledCameraControl() {
        this.disable();
    }
    
    public void cameraConnected(final boolean b) {
        if (b) {
            if (this.bEnableRemoveAll && this.countItems() > 0) {
                try {
                    this.getClass().getMethod("removeAll", (Class<?>[])null);
                    this.removeAll();
                    this.presetHash.clear();
                }
                catch (Exception ex) {
                    WvDebug.println(this + " " + ex);
                    this.bEnableRemoveAll = false;
                }
            }
            this.getCameraList();
            this.makeCameraSelected(this.wvDispatcher.getCurrentCameraId());
            return;
        }
        this.disable();
    }
    
    public void cameraSelected(final String s) {
    }
    
    private void makeCameraSelected(final int n) {
        final String string = "" + (n + 128);
        final Enumeration<String> keys = this.presetHash.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final Object value = this.presetHash.get(s);
            if (value != null && value instanceof String && string.equals(value)) {
                this.select(s);
            }
        }
    }
    
    public void disconnect(final int n) {
        this.disable();
    }
    
    public WvCameraChoice(final WvDispatcher wvDispatcher) {
        this.presetHash = new Hashtable();
        this.wvDispatcher = wvDispatcher;
        if (wvDispatcher.isEnglish()) {
            this.langAndCharset = "?language=English&character_set=ascii";
        }
        else {
            this.langAndCharset = "?language=Japanese&character_set=unicode";
        }
        this.disable();
        this.bEnableRemoveAll = true;
    }
    
    private void getCameraList() {
        final String syncSendCommand = this.wvDispatcher.syncSendCommand("GetCameraList".trim() + this.langAndCharset);
        if (syncSendCommand == null) {
            return;
        }
        int n = 1;
        final StringTokenizer stringTokenizer = new StringTokenizer(syncSendCommand, "\r\n");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.startsWith("camera_")) {
                final int index = nextToken.indexOf("=");
                if (index <= 0) {
                    continue;
                }
                final String substring = nextToken.substring(7, index);
                final String substring2 = nextToken.substring(index + 1);
                if (this.presetHash.get(substring2) != null) {
                    continue;
                }
                this.presetHash.put(substring2, substring);
                this.addItem(substring2);
                n = Integer.parseInt(substring) - 128;
                this.wvDispatcher.putObject("camera_name_" + n, substring2);
            }
        }
        this.resize(this.size().width - 1, this.size().height);
        this.wvDispatcher.putObject("camera_count", new Integer(this.countItems()));
        this.wvDispatcher.putObject("last_camera_id", new Integer(n));
    }
    
    public void videoConnected(final boolean b) {
    }
    
    public void presetCameraOperationFinished(final String s) {
        this.makeCameraSelected(this.wvDispatcher.getWvCameraInfo().getCurrentCameraId());
    }
    
    public void disable() {
        this.setBackground(Color.lightGray);
        super.disable();
    }
    
    public void waitingCameraControl(final int n) {
        this.disable();
    }
    
    public void failedToGetCameraControl() {
        this.disable();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Choice) {
            final String s = this.presetHash.get(this.getSelectedItem());
            if (!("" + (this.wvDispatcher.getCurrentCameraId() + 128)).equals(s)) {
                this.wvDispatcher.asyncStackCommand("OperateCamera".trim() + ("?camera_id=" + s));
            }
        }
        return false;
    }
    
    public void cameraChanged(final int n) {
        this.makeCameraSelected(n);
    }
    
    public void cameraWillChanged() {
    }
    
    public void connect(final String s) {
        this.disable();
    }
}
