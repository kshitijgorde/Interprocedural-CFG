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
    
    static {
        comStr = new String[] { "?pan=", "?tilt=", "?zoom=" };
        WvScrollbar.reso = 10000;
        final Scrollbar scrollbar = new Scrollbar(0, 0, 50, 0, 10);
        scrollbar.setValue(10);
        WvScrollbar.addVisible = (scrollbar.getValue() != 10);
    }
    
    public WvScrollbar(final WvDispatcher wvdispatcher, final int i, final int j) {
        super(i, WvScrollbar.reso / 2, 10, 0, WvScrollbar.reso + (WvScrollbar.addVisible ? 10 : 0));
        this.wvDispatcher = wvdispatcher;
        this.ptz = j;
        this.max = WvScrollbar.reso;
        this.min = 0;
        this.val = WvScrollbar.reso / 2;
        this.flip = (j == 1);
        this.disable();
    }
    
    public void kickOff() {
        this.disable();
    }
    
    public void enabledCameraControl(final int i) {
    }
    
    public void disabledCameraControl() {
        this.updateScrollRange();
    }
    
    public int getSValue() {
        this.getValue();
        final double d = this.getValue() * (this.max - this.min) / WvScrollbar.reso + this.min;
        final double d2 = (WvScrollbar.reso - this.getValue()) * (this.max - this.min) / WvScrollbar.reso + this.min;
        if (!this.flip) {
            if (d > 0.0) {
                return (int)Math.ceil(d);
            }
            return (int)Math.floor(d);
        }
        else {
            if (d2 > 0.0) {
                return (int)Math.ceil(d2);
            }
            return (int)Math.floor(d2);
        }
    }
    
    public void cameraConnected(final boolean flag) {
        if (flag) {
            this.enable();
            this.updateScrollRange();
        }
    }
    
    public void cameraSelected(final String s) {
    }
    
    public void ptzbChanged(final WvPTZB wvptzb) {
        if (wvptzb.zInitialized) {
            this.changeBlockIncrementByZoom(wvptzb.zoom);
        }
        this.ptzbChangedByAnotherClient(wvptzb);
    }
    
    private void setSValue(int i) {
        i = Math.min(this.max, Math.max(this.min, i));
        if (!this.flip) {
            this.val = WvScrollbar.reso * (i - this.min) / (this.max - this.min);
        }
        else {
            this.val = WvScrollbar.reso * (this.max - i) / (this.max - this.min);
        }
        this.setValue(this.val);
    }
    
    private void changeBlockIncrementByZoom(final int i) {
        int j = 0;
        if (this.max - this.min > 0) {
            j = i * WvScrollbar.reso / (this.max - this.min);
        }
        if (this.ptz == 1) {
            j = this.wvDispatcher.getWvCameraInfo().getZoomHeight(j) * 3 / 4;
        }
        else if (this.ptz != 0) {
            return;
        }
        if (j < 5) {
            j = 5;
        }
        this.setWvUnitIncrement(j / 5);
        this.setWvBlockIncrement(j / 2);
    }
    
    private void setValues(final int i, final int j, final int k) {
        this.min = Math.min(j, k);
        this.max = Math.max(j, k);
        if (this.min == this.max) {
            ++this.max;
        }
        this.setSValue(i);
    }
    
    public void disconnect(final int i) {
        this.disable();
    }
    
    private void updateScrollRange() {
        final WvCameraInfo wvcamerainfo = this.wvDispatcher.getWvCameraInfo();
        int i = wvcamerainfo.getMinValue(this.ptz, 1);
        int j = wvcamerainfo.getMaxValue(this.ptz, 1);
        int k = wvcamerainfo.getPtzValue(this.ptz);
        if (this.ptz == 2 && i == j) {
            i = wvcamerainfo.getMinValue(2, 0);
            j = wvcamerainfo.getMaxValue(2, 0);
        }
        if (i == j) {
            i -= 50;
            j += 50;
            k = (i + j) / 2;
        }
        this.setValues(k, i, j);
        if (this.ptz == 2) {
            this.setWvUnitIncrement(WvScrollbar.reso / 10);
            this.setWvBlockIncrement(WvScrollbar.reso / 5);
        }
        this.changeBlockIncrementByZoom(wvcamerainfo.getPtzValue(2));
    }
    
    public void setWvUnitIncrement(final int i) {
        final String s = this.wvDispatcher.getStringObject("scrollbar_notavail_jdk11methods");
        if (s != null) {
            super.setLineIncrement(i);
            return;
        }
        try {
            final Class[] aclass = { Integer.TYPE };
            this.getClass().getMethod("setUnitIncrement", (Class<?>[])aclass);
            this.setUnitIncrement(i);
        }
        catch (Exception exception) {
            WvDebug.println(this + " " + exception);
            this.setLineIncrement(i);
            this.wvDispatcher.putObject("scrollbar_notavail_jdk11methods", "true");
        }
    }
    
    public void videoConnected(final boolean flag) {
    }
    
    public void presetCameraOperationFinished(final String s) {
        this.updateScrollRange();
    }
    
    public void ptzbChangedInternal(final Object obj, final int i, final int j) {
        if (!this.wvDispatcher.hasFloor()) {
            return;
        }
        if (i == this.ptz && obj != this) {
            this.setSValue(j);
        }
    }
    
    public void setWvBlockIncrement(final int i) {
        final String s = this.wvDispatcher.getStringObject("scrollbar_notavail_jdk11methods");
        if (s != null) {
            this.setPageIncrement(i);
            return;
        }
        try {
            final Class[] aclass = { Integer.TYPE };
            this.getClass().getMethod("setBlockIncrement", (Class<?>[])aclass);
            this.setBlockIncrement(i);
        }
        catch (Exception exception) {
            WvDebug.println(this + " " + exception);
            this.setPageIncrement(i);
            this.wvDispatcher.putObject("scrollbar_notavail_jdk11methods", "true");
        }
    }
    
    public void waitingCameraControl(final int i) {
    }
    
    public void failedToGetCameraControl() {
    }
    
    public void ptzbChangedByAnotherClient(final WvPTZB wvptzb) {
        int i = 0;
        switch (this.ptz) {
            case 0: {
                if (wvptzb.zInitialized) {
                    this.changeBlockIncrementByZoom(wvptzb.zoom);
                }
                if (wvptzb.pInitialized) {
                    i = wvptzb.pan;
                    break;
                }
                return;
            }
            case 1: {
                if (wvptzb.zInitialized) {
                    this.changeBlockIncrementByZoom(wvptzb.zoom);
                }
                if (wvptzb.tInitialized) {
                    i = wvptzb.tilt;
                    break;
                }
                return;
            }
            case 2: {
                if (wvptzb.zInitialized) {
                    i = wvptzb.zoom;
                    break;
                }
                return;
            }
        }
        this.setSValue(i);
    }
    
    public void cameraChanged(final int i) {
        this.updateScrollRange();
    }
    
    public void cameraWillChanged() {
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target instanceof WvScrollbar && event.id != 605 && event.id != 602 && event.id != 601 && event.id != 604 && event.id != 603) {
            return true;
        }
        final WvCameraInfo wvcamerainfo = this.wvDispatcher.getWvCameraInfo();
        if (wvcamerainfo.hasFloor() && wvcamerainfo.cameraEnabled()) {
            this.wvDispatcher.asyncOverwriteCommand(String.valueOf("OperateCamera".trim()) + WvScrollbar.comStr[this.ptz] + this.getSValue());
        }
        else {
            this.setValue(this.val);
        }
        return super.handleEvent(event);
    }
    
    public void connect(final String s) {
    }
}
