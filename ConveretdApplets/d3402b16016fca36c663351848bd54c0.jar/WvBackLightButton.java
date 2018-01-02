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
    
    public void enabledCameraControl(final int n) {
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
    
    public void ptzbChanged(final WvPTZB wvPTZB) {
        if (wvPTZB.bInitialized) {
            super.isRaised = !wvPTZB.backLight;
            this.repaint();
        }
    }
    
    public void cameraSelected(final String s) {
    }
    
    public void cameraConnected(final boolean b) {
        super.isRaised = !super.wvDispatcher.getWvCameraInfo().getBackLight();
        this.repaint();
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.isEnabled()) {
            return false;
        }
        if (super.wvDispatcher.getWvCameraInfo().hasFloor()) {
            super.wvDispatcher.asyncStackCommand("OperateCamera".trim() + (super.isRaised ? "?B=OFF" : "?B=ON"));
        }
        return true;
    }
    
    public void disconnect(final int n) {
        this.disable();
    }
    
    public void presetCameraOperationFinished(final String s) {
    }
    
    public void ptzbChangedInternal(final Object o, final int n, final int n2) {
    }
    
    public void imageSizeChanged(final String s) {
    }
    
    public void videoConnected(final boolean b) {
    }
    
    public void cameraPower(final boolean b) {
        if (b && super.wvDispatcher.hasFloor()) {
            this.enable();
            return;
        }
        this.disable();
    }
    
    public void waitingCameraControl(final int n) {
    }
    
    public void failedToGetCameraControl() {
        this.disable();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.isEnabled()) {
            return false;
        }
        super.isRaised = !super.isRaised;
        this.repaint();
        return true;
    }
    
    public void ptzbChangedByAnotherClient(final WvPTZB wvPTZB) {
        if (wvPTZB.bInitialized) {
            super.isRaised = !wvPTZB.backLight;
            this.repaint();
        }
    }
    
    public void cameraChanged(final int n) {
        super.isRaised = !super.wvDispatcher.getWvCameraInfo().getBackLight();
        this.repaint();
    }
    
    public void cameraWillChanged() {
    }
    
    public WvBackLightButton(final WvDispatcher wvDispatcher, final Rectangle[] array) {
        super(wvDispatcher, array, true);
        this.disable();
    }
}
