import java.awt.Event;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetSpeekButton extends Panel implements ImageObserver
{
    private IRCQNet theApp;
    private Image up;
    private Image down;
    private Image stage1;
    private Image stage2;
    private int drawStage;
    private int lastStage;
    private int height;
    private int width;
    private boolean wasDown;
    private boolean disabled;
    
    public IRCQNetSpeekButton(final IRCQNet theApp) {
        this.drawStage = 1;
        this.lastStage = 1;
        this.wasDown = false;
        this.theApp = theApp;
        theApp.getToolkit().prepareImage(this.stage1 = theApp.getImage(theApp.getCodeBase(), "icons/speek/stage1.gif"), 58, 19, this);
        theApp.getToolkit().prepareImage(this.stage2 = theApp.getImage(theApp.getCodeBase(), "icons/speek/stage2.gif"), 58, 19, this);
        theApp.getToolkit().prepareImage(this.up = theApp.getImage(theApp.getCodeBase(), "icons/speek/up.gif"), 58, 19, this);
        theApp.getToolkit().prepareImage(this.down = theApp.getImage(theApp.getCodeBase(), "icons/speek/down.gif"), 58, 19, this);
        this.setBackground(IRCQNetColors.controlColor);
        this.disabled = this.theApp.MPanel.getParams().disableLineInput;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isVisible()) {
            return;
        }
        graphics.setColor(IRCQNetColors.controlColor);
        graphics.fillRect(0, 0, this.width, this.height);
        final int n = this.height / 2 - this.stage1.getHeight(null) / 2;
        final int n2 = this.width / 2 - this.stage1.getWidth(null) / 2;
        switch (this.drawStage) {
            case 1: {
                graphics.drawImage(this.stage1, n2, n, this);
            }
            case 2: {
                graphics.drawImage(this.stage2, n2, n, this);
            }
            case 3: {
                graphics.drawImage(this.down, n2, n, this);
            }
            case 4: {
                graphics.drawImage(this.up, n2, n, this);
            }
            default: {
                graphics.drawImage(this.stage1, n2, n, this);
            }
        }
    }
    
    public void changeStage() {
        switch (this.drawStage) {
            case 1: {
                this.drawStage = 2;
                break;
            }
            case 2: {
                this.drawStage = 1;
                break;
            }
            default: {
                this.drawStage = 2;
                break;
            }
        }
        this.lastStage = this.drawStage;
        this.repaint();
    }
    
    public Dimension preferredSize() {
        return new Dimension(60, 20);
    }
    
    public Dimension minimumSize() {
        return new Dimension(60, 20);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.disabled) {
            return super.mouseDown(event, n, n2);
        }
        this.wasDown = true;
        this.drawStage = 3;
        this.repaint();
        return super.mouseDown(event, n, n2);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.disabled) {
            return super.mouseExit(event, n, n2);
        }
        this.wasDown = false;
        this.drawStage = this.lastStage;
        this.repaint();
        return super.mouseExit(event, n, n2);
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.disabled) {
            return super.mouseMove(event, n, n2);
        }
        this.drawStage = 4;
        this.repaint();
        return super.mouseMove(event, n, n2);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.disabled) {
            return super.mouseUp(event, n, n2);
        }
        this.drawStage = 4;
        this.repaint();
        this.postEvent(new Event(this, 10022, null));
        return super.mouseUp(event, n, n2);
    }
    
    public void reshape(final int n, final int n2, final int width, final int height) {
        super.reshape(n, n2, width, height);
        this.width = width;
        this.height = height;
    }
}
