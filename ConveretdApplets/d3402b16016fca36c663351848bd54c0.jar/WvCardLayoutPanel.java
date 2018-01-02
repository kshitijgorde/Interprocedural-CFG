import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Choice;
import java.awt.Event;
import java.awt.CardLayout;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvCardLayoutPanel extends Panel implements WvEventListener, WvFloorListener, WvUtilListener
{
    private WvDispatcher wvDispatcher;
    private WvPresetChoice wvPresetChoice;
    private WvScrollMessage wvScrollMessage;
    private WvBalloonMessage wvBalloonMessage;
    private static final String[] layoutKey;
    private static final int BLANK = 0;
    private static final int BALLOON = 1;
    private static final int MESSAGE = 2;
    private static final int PRESET = 3;
    private CardLayout cards;
    private int prevLayoutIndex;
    
    private void showScrollMessage(final int n) {
        this.changeLayoutTemporary(2);
        this.wvScrollMessage.showScrollMessage(n);
    }
    
    private void showBlinkMessage() {
        this.changeLayoutTemporary(2);
        this.wvScrollMessage.showBlinkMessage(2);
    }
    
    public void kickOff() {
    }
    
    public void enabledCameraControl(final int n) {
        this.changeLayoutPersistent((this.wvPresetChoice.countItems() > 0) ? 3 : 0);
    }
    
    public void disabledCameraControl() {
        this.changeLayoutPersistent(0);
    }
    
    private void showBalloonMessage() {
        this.changeLayoutTemporary(1);
        this.wvBalloonMessage.start();
    }
    
    public void cameraConnected(final boolean b) {
        if (!b) {
            this.showScrollMessage(1);
            return;
        }
        if (this.wvDispatcher.getWvCameraInfo().cameraEnabled()) {
            this.showBalloonMessage();
            return;
        }
        this.showScrollMessage(2);
    }
    
    private void changeLayoutPersistent(final int prevLayoutIndex) {
        this.changeLayout(this.prevLayoutIndex = prevLayoutIndex);
    }
    
    public void userTriedToControl(final Event event) {
        if (event.target instanceof Choice) {
            return;
        }
        if (!this.isEnabled()) {
            return;
        }
        final WvCameraInfo wvCameraInfo = this.wvDispatcher.getWvCameraInfo();
        switch (wvCameraInfo.getCameraState()) {
            case 6: {
                this.showScrollMessage(1);
            }
            default: {
                if (wvCameraInfo.hasFloor()) {
                    if (!wvCameraInfo.cameraEnabled()) {
                        this.showBlinkMessage();
                    }
                    return;
                }
                else {
                    if (wvCameraInfo.cameraEnabled()) {
                        this.showBalloonMessage();
                        return;
                    }
                    this.showScrollMessage(2);
                    return;
                }
                break;
            }
            case 0:
            case 2:
            case 7: {}
        }
    }
    
    public void disconnect(final int n) {
        this.changeLayoutPersistent(0);
        this.disable();
    }
    
    public void restoreLayout() {
        this.changeLayout(this.prevLayoutIndex);
    }
    
    public void videoConnected(final boolean b) {
    }
    
    public void imageSizeChanged(final String s) {
    }
    
    public WvCardLayoutPanel(final WvDispatcher wvDispatcher) {
        this.setLayout(this.cards = new CardLayout());
        this.wvDispatcher = wvDispatcher;
        this.wvPresetChoice = new WvPresetChoice(wvDispatcher);
        this.wvScrollMessage = new WvScrollMessage(wvDispatcher);
        this.wvBalloonMessage = new WvBalloonMessage(wvDispatcher);
        wvDispatcher.addWvEventListener(this.wvPresetChoice);
        this.add(WvCardLayoutPanel.layoutKey[0], new Label());
        this.add(WvCardLayoutPanel.layoutKey[1], this.wvBalloonMessage);
        this.add(WvCardLayoutPanel.layoutKey[2], this.wvScrollMessage);
        final Panel panel = new Panel();
        final Label label = new Label(" ");
        panel.setLayout(new BorderLayout());
        panel.add("Center", this.wvPresetChoice);
        panel.add("East", label);
        this.add(WvCardLayoutPanel.layoutKey[3], panel);
        this.changeLayout(0);
    }
    
    static {
        layoutKey = new String[] { "blank", "balloon", "message", "preset" };
    }
    
    public void cameraPower(final boolean b) {
        final WvCameraInfo wvCameraInfo = this.wvDispatcher.getWvCameraInfo();
        if (b) {
            if (wvCameraInfo.hasFloor() && this.wvPresetChoice.countItems() > 0) {
                this.changeLayoutPersistent(3);
                return;
            }
            this.changeLayoutPersistent(0);
        }
        else {
            if (wvCameraInfo.hasFloor()) {
                this.showBlinkMessage();
                return;
            }
            this.showScrollMessage(2);
        }
    }
    
    public void waitingCameraControl(final int n) {
    }
    
    public void failedToGetCameraControl() {
        this.showScrollMessage(0);
    }
    
    private void changeLayout(final int n) {
        if (n != 2) {
            this.wvScrollMessage.stop();
        }
        if (n != 1) {
            this.wvBalloonMessage.stop();
        }
        this.cards.show(this, WvCardLayoutPanel.layoutKey[n]);
    }
    
    private void changeLayoutTemporary(final int n) {
        this.changeLayout(n);
    }
    
    public void connect(final String s) {
        this.enable();
    }
}
