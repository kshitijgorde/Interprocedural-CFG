import java.awt.Event;
import java.awt.Scrollbar;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvScrollbar extends Scrollbar implements WvEventListener, WvPresetListener, WvPTZBListener, WvFloorListener
{
    private static final String[] comStr;
    private WvDispatcher wvDispatcher;
    private int ptz;
    private int max;
    private int min;
    private int val;
    private boolean flip;
    private static int reso;
    private static boolean addVisible;
    
    public WvScrollbar(final WvDispatcher wvDispatcher, final int n, final int ptz) {
        super(n, WvScrollbar.reso / 2, 10, 0, WvScrollbar.reso + (WvScrollbar.addVisible ? 10 : 0));
        this.wvDispatcher = wvDispatcher;
        this.ptz = ptz;
        this.max = WvScrollbar.reso;
        this.min = 0;
        this.val = WvScrollbar.reso / 2;
        this.flip = (ptz == 1);
        this.disable();
    }
    
    public void kickOff() {
        this.disable();
    }
    
    public void enabledCameraControl(final int n) {
    }
    
    public void disabledCameraControl() {
        this.updateScrollRange();
    }
    
    public int getSValue() {
        this.getValue();
        final double n = this.getValue() * (this.max - this.min) / WvScrollbar.reso + this.min;
        final double n2 = (WvScrollbar.reso - this.getValue()) * (this.max - this.min) / WvScrollbar.reso + this.min;
        if (!this.flip) {
            if (n > 0.0) {
                return (int)Math.ceil(n);
            }
            return (int)Math.floor(n);
        }
        else {
            if (n2 > 0.0) {
                return (int)Math.ceil(n2);
            }
            return (int)Math.floor(n2);
        }
    }
    
    public void cameraConnected(final boolean b) {
        if (b) {
            this.enable();
            this.updateScrollRange();
        }
    }
    
    public void cameraSelected(final String s) {
    }
    
    public void ptzbChanged(final WvPTZB wvPTZB) {
        if (wvPTZB.zInitialized) {
            this.changeBlockIncrementByZoom(wvPTZB.zoom);
        }
        this.ptzbChangedByAnotherClient(wvPTZB);
    }
    
    private void setSValue(int min) {
        min = Math.min(this.max, Math.max(this.min, min));
        if (!this.flip) {
            this.val = WvScrollbar.reso * (min - this.min) / (this.max - this.min);
        }
        else {
            this.val = WvScrollbar.reso * (this.max - min) / (this.max - this.min);
        }
        this.setValue(this.val);
    }
    
    private void changeBlockIncrementByZoom(final int n) {
        int n2 = 0;
        if (this.max - this.min > 0) {
            n2 = n * WvScrollbar.reso / (this.max - this.min);
        }
        if (this.ptz == 1) {
            n2 = this.wvDispatcher.getWvCameraInfo().getZoomHeight(n2) * 3 / 4;
        }
        else if (this.ptz != 0) {
            return;
        }
        if (n2 < 5) {
            n2 = 5;
        }
        this.setWvUnitIncrement(n2 / 5);
        this.setWvBlockIncrement(n2 / 2);
    }
    
    private void setValues(final int sValue, final int n, final int n2) {
        this.min = Math.min(n, n2);
        this.max = Math.max(n, n2);
        if (this.min == this.max) {
            ++this.max;
        }
        this.setSValue(sValue);
    }
    
    public void disconnect(final int n) {
        this.disable();
    }
    
    private void updateScrollRange() {
        final WvCameraInfo wvCameraInfo = this.wvDispatcher.getWvCameraInfo();
        int n = wvCameraInfo.getMinValue(this.ptz, 1);
        int n2 = wvCameraInfo.getMaxValue(this.ptz, 1);
        int ptzValue = wvCameraInfo.getPtzValue(this.ptz);
        if (this.ptz == 2 && n == n2) {
            n = wvCameraInfo.getMinValue(2, 0);
            n2 = wvCameraInfo.getMaxValue(2, 0);
        }
        if (n == n2) {
            n -= 50;
            n2 += 50;
            ptzValue = (n + n2) / 2;
        }
        this.setValues(ptzValue, n, n2);
        if (this.ptz == 2) {
            this.setWvUnitIncrement(WvScrollbar.reso / 10);
            this.setWvBlockIncrement(WvScrollbar.reso / 5);
        }
        this.changeBlockIncrementByZoom(wvCameraInfo.getPtzValue(2));
    }
    
    public void setWvUnitIncrement(final int lineIncrement) {
        if (this.wvDispatcher.getStringObject("scrollbar_notavail_jdk11methods") != null) {
            super.setLineIncrement(lineIncrement);
            return;
        }
        try {
            this.getClass().getMethod("setUnitIncrement", Integer.TYPE);
            this.setUnitIncrement(lineIncrement);
        }
        catch (Exception ex) {
            WvDebug.println(this + " " + ex);
            this.setLineIncrement(lineIncrement);
            this.wvDispatcher.putObject("scrollbar_notavail_jdk11methods", "true");
        }
    }
    
    public void videoConnected(final boolean b) {
    }
    
    public void presetCameraOperationFinished(final String s) {
        this.updateScrollRange();
    }
    
    public void ptzbChangedInternal(final Object o, final int n, final int sValue) {
        if (!this.wvDispatcher.hasFloor()) {
            return;
        }
        if (n == this.ptz && o != this) {
            this.setSValue(sValue);
        }
    }
    
    public void setWvBlockIncrement(final int pageIncrement) {
        if (this.wvDispatcher.getStringObject("scrollbar_notavail_jdk11methods") != null) {
            this.setPageIncrement(pageIncrement);
            return;
        }
        try {
            this.getClass().getMethod("setBlockIncrement", Integer.TYPE);
            this.setBlockIncrement(pageIncrement);
        }
        catch (Exception ex) {
            WvDebug.println(this + " " + ex);
            this.setPageIncrement(pageIncrement);
            this.wvDispatcher.putObject("scrollbar_notavail_jdk11methods", "true");
        }
    }
    
    static {
        comStr = new String[] { "?pan=", "?tilt=", "?zoom=" };
        WvScrollbar.reso = 10000;
        final Scrollbar scrollbar = new Scrollbar(0, 0, 50, 0, 10);
        scrollbar.setValue(10);
        WvScrollbar.addVisible = (scrollbar.getValue() != 10);
    }
    
    public void waitingCameraControl(final int n) {
    }
    
    public void failedToGetCameraControl() {
    }
    
    public void ptzbChangedByAnotherClient(final WvPTZB wvPTZB) {
        int sValue = 0;
        switch (this.ptz) {
            case 0: {
                if (wvPTZB.zInitialized) {
                    this.changeBlockIncrementByZoom(wvPTZB.zoom);
                }
                if (wvPTZB.pInitialized) {
                    sValue = wvPTZB.pan;
                    break;
                }
                return;
            }
            case 1: {
                if (wvPTZB.zInitialized) {
                    this.changeBlockIncrementByZoom(wvPTZB.zoom);
                }
                if (wvPTZB.tInitialized) {
                    sValue = wvPTZB.tilt;
                    break;
                }
                return;
            }
            case 2: {
                if (wvPTZB.zInitialized) {
                    sValue = wvPTZB.zoom;
                    break;
                }
                return;
            }
        }
        this.setSValue(sValue);
    }
    
    public void cameraChanged(final int n) {
        this.updateScrollRange();
    }
    
    public void cameraWillChanged() {
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target instanceof WvScrollbar && event.id != 605 && event.id != 602 && event.id != 601 && event.id != 604 && event.id != 603) {
            return true;
        }
        final WvCameraInfo wvCameraInfo = this.wvDispatcher.getWvCameraInfo();
        if (wvCameraInfo.hasFloor() && wvCameraInfo.cameraEnabled()) {
            this.wvDispatcher.asyncOverwriteCommand("OperateCamera".trim() + WvScrollbar.comStr[this.ptz] + this.getSValue());
        }
        else {
            this.setValue(this.val);
        }
        return super.handleEvent(event);
    }
    
    public void connect(final String s) {
    }
}
