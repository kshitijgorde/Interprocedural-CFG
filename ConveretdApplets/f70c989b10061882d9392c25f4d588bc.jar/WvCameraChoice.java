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
    
    public void enabledCameraControl(final int i) {
        if (this.countItems() > 1) {
            this.setBackground(Color.white);
            this.enable();
        }
    }
    
    public void disabledCameraControl() {
        this.disable();
    }
    
    public void cameraConnected(final boolean flag) {
        if (flag) {
            if (this.bEnableRemoveAll && this.countItems() > 0) {
                try {
                    this.getClass().getMethod("removeAll", (Class<?>[])null);
                    this.removeAll();
                    this.presetHash.clear();
                }
                catch (Exception exception) {
                    WvDebug.println(this + " " + exception);
                    this.bEnableRemoveAll = false;
                }
            }
            this.getCameraList();
            final int i = this.wvDispatcher.getCurrentCameraId();
            this.makeCameraSelected(i);
            return;
        }
        this.disable();
    }
    
    public void cameraSelected(final String s) {
    }
    
    private void makeCameraSelected(final int i) {
        final String s = new StringBuffer().append(i + 128).toString();
        final Enumeration enumeration = this.presetHash.keys();
        while (enumeration.hasMoreElements()) {
            final String s2 = enumeration.nextElement();
            final Object obj = this.presetHash.get(s2);
            if (obj != null && obj instanceof String && s.equals(obj)) {
                this.select(s2);
            }
        }
    }
    
    public void disconnect(final int i) {
        this.disable();
    }
    
    public WvCameraChoice(final WvDispatcher wvdispatcher) {
        this.presetHash = new Hashtable();
        this.wvDispatcher = wvdispatcher;
        if (wvdispatcher.isEnglish()) {
            this.langAndCharset = "?language=English&character_set=ascii";
        }
        else {
            this.langAndCharset = "?language=Japanese&character_set=unicode";
        }
        this.disable();
        this.bEnableRemoveAll = true;
    }
    
    private void getCameraList() {
        final String s = String.valueOf("GetCameraList".trim()) + this.langAndCharset;
        final String s2 = this.wvDispatcher.syncSendCommand(s);
        if (s2 == null) {
            return;
        }
        int i = 1;
        final StringTokenizer stringtokenizer = new StringTokenizer(s2, "\r\n");
        while (stringtokenizer.hasMoreTokens()) {
            final String s3 = stringtokenizer.nextToken();
            if (s3.startsWith("camera_")) {
                final int j = s3.indexOf("=");
                if (j <= 0) {
                    continue;
                }
                final String s4 = s3.substring(7, j);
                final String s5 = s3.substring(j + 1);
                if (this.presetHash.get(s5) != null) {
                    continue;
                }
                this.presetHash.put(s5, s4);
                this.addItem(s5);
                i = Integer.parseInt(s4) - 128;
                this.wvDispatcher.putObject("camera_name_" + i, s5);
            }
        }
        this.resize(this.size().width - 1, this.size().height);
        this.wvDispatcher.putObject("camera_count", new Integer(this.countItems()));
        this.wvDispatcher.putObject("last_camera_id", new Integer(i));
    }
    
    public void videoConnected(final boolean flag) {
    }
    
    public void presetCameraOperationFinished(final String s) {
        final int i = this.wvDispatcher.getWvCameraInfo().getCurrentCameraId();
        this.makeCameraSelected(i);
    }
    
    public void disable() {
        this.setBackground(Color.lightGray);
        super.disable();
    }
    
    public void waitingCameraControl(final int i) {
        this.disable();
    }
    
    public void failedToGetCameraControl() {
        this.disable();
    }
    
    public boolean action(final Event event, final Object obj) {
        if (event.target instanceof Choice) {
            final String s = this.getSelectedItem();
            final String s2 = this.presetHash.get(s);
            final String s3 = new StringBuffer().append(this.wvDispatcher.getCurrentCameraId() + 128).toString();
            if (!s3.equals(s2)) {
                final String s4 = "?camera_id=" + s2;
                this.wvDispatcher.asyncStackCommand(String.valueOf("OperateCamera".trim()) + s4);
            }
        }
        return false;
    }
    
    public void cameraChanged(final int i) {
        this.makeCameraSelected(i);
    }
    
    public void cameraWillChanged() {
    }
    
    public void connect(final String s) {
        this.disable();
    }
}
