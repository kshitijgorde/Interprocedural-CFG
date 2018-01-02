import java.awt.Container;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvControlButton extends Canvas implements Runnable, WvEventListener, WvFloorListener
{
    private static final int WIDTH = 77;
    private static final int HEIGHT = 43;
    private Thread thread;
    private Image backBuffer;
    private Graphics backGC;
    private Image imageMap;
    private boolean isEnglish;
    private boolean raised;
    private boolean nowMousePressed;
    private int imageIndex;
    private WvDispatcher wvDispatcher;
    private Component wvTimeIndicator;
    
    public WvControlButton(final WvDispatcher wvDispatcher, final Image imageMap) {
        this.wvDispatcher = wvDispatcher;
        this.imageMap = imageMap;
        this.isEnglish = wvDispatcher.isEnglish();
        this.raised = true;
        this.imageIndex = (this.isEnglish ? 5 : 2);
        this.resize(77, 43);
        this.disable();
    }
    
    public void kickOff() {
        this.disable();
    }
    
    public void enabledCameraControl(final int n) {
        this.disable();
    }
    
    public void disabledCameraControl() {
        this.enable();
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.isEnabled()) {
            return false;
        }
        if (this.nowMousePressed && event.target instanceof WvControlButton) {
            this.raised = true;
            this.nowMousePressed = false;
            this.repaint();
            this.wvDispatcher.syncSendCommand("GetCameraControl");
        }
        return false;
    }
    
    public void cameraConnected(final boolean b) {
        if (b) {
            this.enable();
            return;
        }
        this.disable();
    }
    
    public void paint(final Graphics graphics) {
        if (this.backBuffer == null) {
            this.backBuffer = this.createImage(77, 43);
            if (this.backBuffer == null) {
                return;
            }
            this.backGC = this.backBuffer.getGraphics();
        }
        this.backGC.setColor(this.getBackground());
        this.backGC.fillRect(0, 0, 77, 43);
        this.backGC.drawImage(this.imageMap, -this.imageIndex * 75 + 1, 1, this);
        this.backGC.setColor(Color.lightGray);
        this.backGC.draw3DRect(0, 0, 76, 42, this.raised);
        this.backGC.setColor(Color.black);
        if (this.raised) {
            this.backGC.drawLine(1, 42, 77, 42);
            this.backGC.drawLine(76, 1, 76, 43);
        }
        graphics.drawImage(this.backBuffer, 0, 0, 77, 43, this);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.isEnabled()) {
            return false;
        }
        if (event.target instanceof WvControlButton) {
            this.raised = true;
            this.nowMousePressed = false;
            this.repaint();
            this.wvDispatcher.syncSendCommand("GetCameraControl");
        }
        return false;
    }
    
    public void disconnect(final int n) {
        this.disable();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void videoConnected(final boolean b) {
    }
    
    public void enable() {
        super.enable();
        if (this.thread != null && this.thread.isAlive()) {
            return;
        }
        (this.thread = new Thread(this)).start();
    }
    
    public void disable() {
        super.disable();
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
        this.imageIndex = (this.isEnglish ? 5 : 2);
        this.raised = true;
        this.repaint();
    }
    
    public void waitingCameraControl(final int n) {
        this.disable();
    }
    
    public void failedToGetCameraControl() {
        this.enable();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.isEnabled()) {
            return false;
        }
        if (event.target instanceof WvControlButton) {
            this.raised = false;
            this.nowMousePressed = true;
            this.wvDispatcher.getWvCameraInfo().setCameraState(1);
            this.repaintTimeIndicator();
            this.repaint();
        }
        return false;
    }
    
    public void run() {
        while (true) {
            if (this.isEnglish) {
                this.imageIndex = ((this.imageIndex == 3) ? 4 : 3);
            }
            else {
                this.imageIndex = ((this.imageIndex == 0) ? 1 : 0);
            }
            this.repaint();
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex) {}
        }
    }
    
    private void repaintTimeIndicator() {
        if (this.wvTimeIndicator == null) {
            final Container parent = this.getParent();
            if (parent != null) {
                final Component[] components = parent.getComponents();
                for (int i = 0; i < components.length; ++i) {
                    if (components[i] instanceof WvTimeIndicator) {
                        this.wvTimeIndicator = components[i];
                        break;
                    }
                }
            }
        }
        if (this.wvTimeIndicator != null) {
            this.wvTimeIndicator.repaint();
        }
    }
    
    public void connect(final String s) {
    }
}
