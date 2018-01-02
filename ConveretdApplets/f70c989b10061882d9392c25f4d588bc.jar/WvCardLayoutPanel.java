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
    
    static {
        layoutKey = new String[] { "blank", "balloon", "message", "preset" };
    }
    
    private void showScrollMessage(final int i) {
        this.changeLayoutTemporary(2);
        this.wvScrollMessage.showScrollMessage(i);
    }
    
    private void showBlinkMessage() {
        this.changeLayoutTemporary(2);
        this.wvScrollMessage.showBlinkMessage(2);
    }
    
    public void kickOff() {
    }
    
    public void enabledCameraControl(final int i) {
        this.changeLayoutPersistent((this.wvPresetChoice.countItems() <= 0) ? 0 : 3);
    }
    
    public void disabledCameraControl() {
        this.changeLayoutPersistent(0);
    }
    
    private void showBalloonMessage() {
        this.changeLayoutTemporary(1);
        this.wvBalloonMessage.start();
    }
    
    public void cameraConnected(final boolean flag) {
        if (!flag) {
            this.showScrollMessage(1);
            return;
        }
        final WvCameraInfo wvcamerainfo = this.wvDispatcher.getWvCameraInfo();
        if (wvcamerainfo.cameraEnabled()) {
            this.showBalloonMessage();
            return;
        }
        this.showScrollMessage(2);
    }
    
    private void changeLayoutPersistent(final int i) {
        this.changeLayout(this.prevLayoutIndex = i);
    }
    
    public void userTriedToControl(final Event event) {
        if (event.target instanceof Choice) {
            return;
        }
        if (!this.isEnabled()) {
            return;
        }
        final WvCameraInfo wvcamerainfo = this.wvDispatcher.getWvCameraInfo();
        switch (wvcamerainfo.getCameraState()) {
            case 0:
            case 2:
            case 7: {
                break;
            }
            case 6: {
                this.showScrollMessage(1);
            }
            default: {
                if (wvcamerainfo.hasFloor()) {
                    if (!wvcamerainfo.cameraEnabled()) {
                        this.showBlinkMessage();
                        return;
                    }
                    break;
                }
                else {
                    if (wvcamerainfo.cameraEnabled()) {
                        this.showBalloonMessage();
                        return;
                    }
                    this.showScrollMessage(2);
                    return;
                }
                break;
            }
        }
    }
    
    public void disconnect(final int i) {
        this.changeLayoutPersistent(0);
        this.disable();
    }
    
    public void restoreLayout() {
        this.changeLayout(this.prevLayoutIndex);
    }
    
    public void videoConnected(final boolean flag) {
    }
    
    public void imageSizeChanged(final String s) {
    }
    
    public WvCardLayoutPanel(final WvDispatcher wvdispatcher) {
        this.setLayout(this.cards = new CardLayout());
        this.wvDispatcher = wvdispatcher;
        this.wvPresetChoice = new WvPresetChoice(wvdispatcher);
        this.wvScrollMessage = new WvScrollMessage(wvdispatcher);
        this.wvBalloonMessage = new WvBalloonMessage(wvdispatcher);
        wvdispatcher.addWvEventListener(this.wvPresetChoice);
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
    
    public void cameraPower(final boolean flag) {
        final WvCameraInfo wvcamerainfo = this.wvDispatcher.getWvCameraInfo();
        if (flag) {
            if (wvcamerainfo.hasFloor() && this.wvPresetChoice.countItems() > 0) {
                this.changeLayoutPersistent(3);
                return;
            }
            this.changeLayoutPersistent(0);
        }
        else {
            if (wvcamerainfo.hasFloor()) {
                this.showBlinkMessage();
                return;
            }
            this.showScrollMessage(2);
        }
    }
    
    public void waitingCameraControl(final int i) {
    }
    
    public void failedToGetCameraControl() {
        this.showScrollMessage(0);
    }
    
    private void changeLayout(final int i) {
        if (i != 2) {
            this.wvScrollMessage.stop();
        }
        if (i != 1) {
            this.wvBalloonMessage.stop();
        }
        this.cards.show(this, WvCardLayoutPanel.layoutKey[i]);
    }
    
    private void changeLayoutTemporary(final int i) {
        this.changeLayout(i);
    }
    
    public void connect(final String s) {
        this.enable();
    }
}
