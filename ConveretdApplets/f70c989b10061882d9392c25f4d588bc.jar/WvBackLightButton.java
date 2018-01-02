import java.awt.Rectangle;
import java.awt.Event;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvBackLightButton extends WvImageButton implements WvFloorListener, WvPTZBListener, WvPresetListener, WvUtilListener
{
    public void connect(final String s) {
        this.disable();
    }
    
    public void enabledCameraControl(final int i) {
        if (super.wvDispatcher.getWvCameraInfo().cameraEnabled()) {
            this.enable();
        }
    }
    
    public void disabledCameraControl() {
        this.disable();
        super.isRaised = !super.wvDispatcher.getWvCameraInfo().getBackLight();
        this.repaint();
    }
    
    public void kickOff() {
        this.disable();
    }
    
    public void ptzbChanged(final WvPTZB wvptzb) {
        if (wvptzb.bInitialized) {
            super.isRaised = !wvptzb.backLight;
            this.repaint();
        }
    }
    
    public void cameraSelected(final String s) {
    }
    
    public void cameraConnected(final boolean flag) {
        super.isRaised = !super.wvDispatcher.getWvCameraInfo().getBackLight();
        this.repaint();
    }
    
    public boolean mouseUp(final Event event, final int i, final int j) {
        if (!this.isEnabled()) {
            return false;
        }
        final WvCameraInfo wvcamerainfo = super.wvDispatcher.getWvCameraInfo();
        if (wvcamerainfo.hasFloor()) {
            super.wvDispatcher.asyncStackCommand(String.valueOf("OperateCamera".trim()) + (super.isRaised ? "?B=OFF" : "?B=ON"));
        }
        return true;
    }
    
    public void disconnect(final int i) {
        this.disable();
    }
    
    public void presetCameraOperationFinished(final String s) {
    }
    
    public void ptzbChangedInternal(final Object obj, final int i, final int j) {
    }
    
    public void imageSizeChanged(final String s) {
    }
    
    public void videoConnected(final boolean flag) {
    }
    
    public void cameraPower(final boolean flag) {
        if (flag && super.wvDispatcher.hasFloor()) {
            this.enable();
            return;
        }
        this.disable();
    }
    
    public void waitingCameraControl(final int i) {
    }
    
    public void failedToGetCameraControl() {
        this.disable();
    }
    
    public boolean mouseDown(final Event event, final int i, final int j) {
        if (!this.isEnabled()) {
            return false;
        }
        super.isRaised = !super.isRaised;
        this.repaint();
        return true;
    }
    
    public void ptzbChangedByAnotherClient(final WvPTZB wvptzb) {
        if (wvptzb.bInitialized) {
            super.isRaised = !wvptzb.backLight;
            this.repaint();
        }
    }
    
    public void cameraChanged(final int i) {
        super.isRaised = !super.wvDispatcher.getWvCameraInfo().getBackLight();
        this.repaint();
    }
    
    public void cameraWillChanged() {
    }
    
    public WvBackLightButton(final WvDispatcher wvdispatcher, final Rectangle[] arectangle) {
        super(wvdispatcher, arectangle, true);
        this.disable();
    }
}
