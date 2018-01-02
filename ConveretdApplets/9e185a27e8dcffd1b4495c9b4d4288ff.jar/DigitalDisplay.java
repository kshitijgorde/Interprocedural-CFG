import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Color;
import java.util.Vector;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

public class DigitalDisplay implements Runnable, ImageObserver, MouseListener
{
    private Canvas parent;
    private long delayMillis;
    private long loopDelayMilli;
    private String message;
    private String[] messageArray;
    private int currentMessage;
    private int left;
    private int top;
    private int width;
    private int height;
    private int numCells;
    private DigitalCourierGlyph glyphCell;
    private Graphics onScreenGfx;
    private Image onScreenImage;
    private Graphics offScreenGfx;
    private Image offScreenImage;
    private Vector glyphImages;
    private Image magiLabel;
    private boolean threading;
    private boolean animate;
    private boolean registered;
    private int activationX;
    
    public DigitalDisplay(final Image magiLabel) {
        this.currentMessage = 0;
        this.onScreenGfx = null;
        this.onScreenImage = null;
        this.offScreenGfx = null;
        this.offScreenImage = null;
        this.glyphImages = null;
        this.magiLabel = null;
        this.threading = false;
        this.animate = true;
        this.registered = false;
        this.activationX = 0;
        this.init(Color.green, 0, 0, 10, magiLabel);
    }
    
    public DigitalDisplay(final Color displayColour, final Image magiLabel) {
        this.currentMessage = 0;
        this.onScreenGfx = null;
        this.onScreenImage = null;
        this.offScreenGfx = null;
        this.offScreenImage = null;
        this.glyphImages = null;
        this.magiLabel = null;
        this.threading = false;
        this.animate = true;
        this.registered = false;
        this.init(displayColour, this.activationX = 0, 0, 10, magiLabel);
    }
    
    public DigitalDisplay(final Color displayColour, final int left, final int top, final int numCells, final Image magiLabel) {
        this.currentMessage = 0;
        this.onScreenGfx = null;
        this.onScreenImage = null;
        this.offScreenGfx = null;
        this.offScreenImage = null;
        this.glyphImages = null;
        this.magiLabel = null;
        this.threading = false;
        this.animate = true;
        this.registered = false;
        this.activationX = 0;
        this.init(displayColour, left, top, numCells, magiLabel);
    }
    
    private void init(final Color displayColour, final int left, final int top, final int numCells, final Image magiLabel) {
        final int xOffset = 0;
        final int yOffset = 0;
        this.left = left;
        this.top = top;
        this.numCells = numCells;
        if (magiLabel.getWidth(this) > 0 && magiLabel.getHeight(this) > 0) {
            this.magiLabel = magiLabel;
        }
        this.animate = true;
        (this.glyphCell = new DigitalCourierGlyph()).setColour(displayColour);
        this.glyphCell.setOrigin(xOffset, yOffset);
        this.glyphCell.setBorder(true);
        this.width = this.glyphCell.getWidth() * numCells;
        this.height = this.glyphCell.getHeight();
        this.glyphImages = new Vector(numCells);
    }
    
    private void initDoubleBuffer(final Component parent) {
        if (this.onScreenImage == null || this.offScreenImage == null) {
            this.onScreenImage = parent.createImage(this.getWidth(), this.getHeight());
            this.onScreenGfx = this.onScreenImage.getGraphics();
            this.offScreenImage = parent.createImage(this.getWidth(), this.getHeight());
            this.offScreenGfx = this.offScreenImage.getGraphics();
            this.glyphCell.setDigitalCharacter(' ');
            this.glyphImages.removeAllElements();
            for (int i = 0; i < this.numCells; ++i) {
                final Image glyph = parent.createImage(this.glyphCell.getWidth(), this.glyphCell.getHeight());
                final Graphics glyphGfx = glyph.getGraphics();
                this.glyphCell.draw(glyphGfx, false);
                this.glyphImages.addElement(glyph);
            }
        }
    }
    
    public void flip() {
        final Image tempImg = this.onScreenImage;
        final Graphics tempGfx = this.onScreenGfx;
        this.onScreenImage = this.offScreenImage;
        this.onScreenGfx = this.offScreenGfx;
        this.offScreenImage = tempImg;
        this.offScreenGfx = tempGfx;
    }
    
    public void setDelay(final long milli) {
        this.delayMillis = milli;
    }
    
    public long getDelay() {
        return this.delayMillis;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void showMessage(final Canvas parent, final String message) {
        this.parent = parent;
        this.message = message;
        this.messageArray = null;
    }
    
    public void showMessage(final Canvas parent, final String[] messageArray) {
        this.parent = parent;
        this.messageArray = messageArray;
        this.message = null;
    }
    
    private String getNextMessage() {
        String msg;
        if (this.messageArray != null) {
            msg = this.messageArray[this.currentMessage++];
            this.currentMessage %= this.messageArray.length;
        }
        else {
            msg = this.message;
        }
        return msg;
    }
    
    public void loop(final long loopDelayMilli) {
        this.loopDelayMilli = loopDelayMilli;
    }
    
    public void run() {
        final int firstCell = this.numCells - 1;
        int charIndex = 0;
        int blankCount = 0;
        do {
            for (String msg = this.getNextMessage(); (charIndex < msg.length() || blankCount <= this.numCells) && this.animate; ++charIndex) {
                Image img = this.glyphImages.elementAt(0);
                final Graphics gfx = img.getGraphics();
                this.glyphImages.removeElementAt(0);
                if (msg.length() > charIndex) {
                    this.glyphCell.setDigitalCharacter(msg.charAt(charIndex));
                }
                else {
                    ++blankCount;
                    this.glyphCell.allLightsOff();
                }
                this.glyphCell.draw(gfx, false);
                this.glyphImages.insertElementAt(img, this.glyphImages.size());
                for (int i = 0; i < this.numCells; ++i) {
                    img = this.glyphImages.elementAt(i);
                    this.offScreenGfx.drawImage(img, this.glyphCell.getWidth() * i, 0, this);
                }
                this.flip();
                try {
                    Thread.currentThread();
                    Thread.sleep(this.delayMillis);
                }
                catch (InterruptedException iex) {
                    System.out.println("showMessage thread has been interrupted.");
                    return;
                }
            }
            blankCount = 0;
            charIndex = 0;
            if (this.loopDelayMilli > 0 && this.animate) {
                try {
                    Thread.currentThread();
                    Thread.sleep(this.loopDelayMilli);
                }
                catch (InterruptedException iex) {
                    System.out.println("showMessage thread has been interrupted.");
                    return;
                }
            }
        } while (this.loopDelayMilli > 0 && this.animate);
        this.cleanUpAfterThread();
    }
    
    public void cleanUpAfterThread() {
        this.animate = false;
        this.threading = false;
        this.currentMessage = 0;
    }
    
    public void animate(final boolean animateState) {
        this.animate = animateState;
    }
    
    public void draw(final Graphics gfx, final boolean updateOnly) {
        this.initDoubleBuffer(this.parent);
        if (this.registered || this.magiLabel != null) {
            gfx.drawImage(this.onScreenImage, this.left, this.top, this);
        }
        if (this.magiLabel != null) {
            final Dimension dim = gfx.getClipBounds().getSize();
            this.activationX = dim.width - this.magiLabel.getWidth(this) - 1;
            gfx.drawImage(this.magiLabel, this.activationX, (dim.height - this.magiLabel.getHeight(this) - 1) / 2, this);
        }
        else if (!this.registered) {
            this.activationX = 0;
            gfx.setColor(Color.white);
            gfx.drawString("Click for www.magi-au.com (Magi Systems)", 10, 14);
            gfx.drawString("( magi.gif image was not found )", 10, 30);
        }
        if (!this.threading && this.animate) {
            this.threading = true;
            new Thread(this).start();
        }
    }
    
    public boolean imageUpdate(final Image img, final int infoflags, final int x, final int y, final int width, final int height) {
        return false;
    }
    
    public void mouseClicked(final MouseEvent e) {
        if (!this.registered && e.getX() >= this.activationX) {
            final DigitalScroller applet = (DigitalScroller)this.parent.getParent();
            try {
                applet.getAppletContext().showDocument(new URL("http://www.magi-au.com/"), "magi");
            }
            catch (MalformedURLException ex) {}
        }
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
}
