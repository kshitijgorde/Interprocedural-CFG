import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.net.URL;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Image;
import java.util.Vector;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class SlideShow extends Panel implements Runnable, MouseListener
{
    private boolean borderVisible_;
    private int imageIndex;
    private int imageStyle;
    private int switchSpeed;
    private transient Vector images;
    private transient Image image;
    private transient Thread displayThread;
    Vector uRLList;
    private int borderWidth;
    
    public SlideShow() {
        this.setBackground(Color.green.darker());
        this.imageIndex = 0;
        this.switchSpeed = 750;
        this.uRLList = new Vector();
        this.images = new Vector();
        this.borderVisible_ = true;
        this.borderWidth = 10;
        this.setLayout(null);
        this.addMouseListener(this);
    }
    
    void setURLList(final Vector vector) {
        this.uRLList.removeAllElements();
        this.images.removeAllElements();
        if (vector.size() == 0) {
            try {
                if (this.isBorderVisible()) {
                    this.getGraphics().clearRect(this.borderWidth, this.borderWidth, this.getSize().width - 2 * this.borderWidth, this.getSize().height - 2 * this.borderWidth);
                }
                else {
                    this.getGraphics().clearRect(0, 0, this.getSize().width, this.getSize().height);
                }
            }
            catch (NullPointerException ex) {}
            this.image = null;
            return;
        }
        for (int i = 0; i < vector.size(); ++i) {
            final Image loadImageFromURL = this.loadImageFromURL(vector.elementAt(i));
            this.imageLoadWait(loadImageFromURL);
            this.uRLList.addElement(vector.elementAt(i));
            this.images.addElement(loadImageFromURL);
        }
        this.setImage(0);
    }
    
    public Vector getURLList() {
        return this.uRLList;
    }
    
    public void setSwitchSpeed(final int switchSpeed) {
        if (switchSpeed > 0) {
            this.switchSpeed = switchSpeed;
        }
    }
    
    public int getSwitchSpeed() {
        return this.switchSpeed;
    }
    
    public int getNumberOfImages() {
        return this.images.size();
    }
    
    public Image getSelectedImage() {
        return this.images.elementAt(this.imageIndex);
    }
    
    public URL getURL(final int n) {
        try {
            return this.uRLList.elementAt(n);
        }
        catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    public boolean isAtFirstImage() {
        return this.imageIndex == 0;
    }
    
    public boolean isAtLastImage() {
        return this.imageIndex == this.images.size() - 1;
    }
    
    public Image getImage(final int n) {
        try {
            return this.images.elementAt(n);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    public void setImage(final int imageIndex) {
        try {
            this.image = this.images.elementAt(imageIndex);
            this.imageIndex = imageIndex;
            this.repaint(this.borderWidth, this.borderWidth, this.getSize().width - 2 * this.borderWidth, this.getSize().height - 2 * this.borderWidth);
        }
        catch (IndexOutOfBoundsException ex) {}
        catch (Exception ex2) {}
    }
    
    public void setBorderVisible(final boolean borderVisible_) {
        this.borderVisible_ = borderVisible_;
        if (this.borderVisible_) {
            this.borderWidth = 10;
        }
        else {
            this.borderWidth = 0;
            try {
                this.getGraphics().clearRect(0, 0, this.getSize().width, this.getSize().height);
            }
            catch (NullPointerException ex) {}
        }
        this.repaint();
    }
    
    public boolean isBorderVisible() {
        return this.borderVisible_;
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.switchSpeed);
            }
            catch (InterruptedException ex) {}
            synchronized (this) {
                this.setImage(this.imageIndex);
                ++this.imageIndex;
                if (this.imageIndex <= this.images.size() - 1) {
                    continue;
                }
                this.imageIndex = 0;
            }
        }
    }
    
    public void first() {
        this.setImage(0);
    }
    
    public void last() {
        this.setImage(this.images.size() - 1);
    }
    
    public void play() {
        try {
            this.displayThread.start();
        }
        catch (NullPointerException ex) {
            (this.displayThread = new Thread(this)).start();
        }
        catch (IllegalThreadStateException ex2) {
            (this.displayThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        try {
            this.displayThread.stop();
            this.displayThread = null;
        }
        catch (NullPointerException ex) {}
        catch (IllegalThreadStateException ex2) {}
        this.setImage(0);
    }
    
    public void pause(final boolean b) {
        if (b) {
            try {
                this.displayThread.suspend();
                return;
            }
            catch (NullPointerException ex) {
                return;
            }
            catch (IllegalThreadStateException ex2) {
                return;
            }
        }
        try {
            this.displayThread.resume();
        }
        catch (NullPointerException ex3) {}
        catch (IllegalThreadStateException ex4) {}
    }
    
    public void next() {
        int image;
        if (!this.isAtLastImage()) {
            image = this.imageIndex + 1;
        }
        else {
            image = 0;
        }
        this.setImage(image);
    }
    
    public void previous() {
        int n;
        if (!this.isAtFirstImage()) {
            n = this.imageIndex - 1;
        }
        else {
            n = this.images.size() - 1;
        }
        this.setImage(n - 1);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        this.drawImage(graphics);
        if (this.isBorderVisible()) {
            this.drawBorder(graphics);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    private void imageLoadWait(final Image image) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
    }
    
    private Image loadImageFromURL(final URL url) {
        Image image = null;
        try {
            image = this.getToolkit().getImage(url);
        }
        catch (Exception ex) {}
        return image;
    }
    
    private void drawRectOfBorder(final Graphics graphics, final Color color, final Color color2, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(color);
        graphics.drawLine(n, n2, n3 - n, n2);
        graphics.drawLine(n, n2, n, n4 - n2);
        graphics.setColor(color2);
        graphics.drawLine(n, n4 - n2, n3 - n, n4 - n2);
        graphics.drawLine(n3 - n, n2, n3 - n, n4 - n2);
    }
    
    private void drawBorder(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final Color black = Color.black;
        final Color darker = Color.yellow.darker();
        this.drawRectOfBorder(graphics, black, darker.brighter(), 0, 0, width, height);
        this.drawRectOfBorder(graphics, black, darker.brighter(), 1, 1, width, height);
        this.drawRectOfBorder(graphics, black, darker.brighter(), 2, 2, width, height);
        this.drawRectOfBorder(graphics, darker.darker(), darker, 3, 3, width, height);
        this.drawRectOfBorder(graphics, darker.darker(), darker.darker(), 4, 4, width, height);
        this.drawRectOfBorder(graphics, darker.darker(), darker.darker(), 5, 5, width, height);
        this.drawRectOfBorder(graphics, darker.darker(), darker.darker(), 6, 6, width, height);
        this.drawRectOfBorder(graphics, darker.darker(), darker.darker(), 7, 7, width, height);
        this.drawRectOfBorder(graphics, darker.darker(), darker.darker(), 8, 8, width, height);
        this.drawRectOfBorder(graphics, black, darker.brighter(), 9, 9, width, height);
        this.drawRectOfBorder(graphics, black, darker.brighter(), 10, 10, width, height);
        graphics.setColor(darker.darker().darker());
        graphics.drawLine(0, 0, this.borderWidth, this.borderWidth);
        graphics.drawLine(0, this.getSize().height, this.borderWidth, this.getSize().height - this.borderWidth);
        graphics.drawLine(this.getSize().width, 0, this.getSize().width - this.borderWidth, this.borderWidth);
        graphics.setColor(darker.darker().darker());
        graphics.drawLine(this.getSize().width - 2, this.getSize().height - 2, this.getSize().width - this.borderWidth + 1, this.getSize().height - this.borderWidth + 1);
    }
    
    private void drawImage(final Graphics graphics) {
        try {
            final Image image = this.createImage(this.getSize().width, this.getSize().height);
            final Graphics graphics2 = image.getGraphics();
            graphics2.setColor(this.getBackground());
            graphics2.fillRect(0, 0, this.getSize().width, this.getSize().height);
            graphics2.drawImage(this.getImage(this.imageIndex), 0, 0, this.getSize().width, this.getSize().height, this);
            graphics.drawImage(image, this.borderWidth, this.borderWidth, this.getSize().width - 2 * this.borderWidth, this.getSize().height - 2 * this.borderWidth, this);
        }
        catch (NullPointerException ex) {
            graphics.clearRect(0, 0, this.getSize().width, this.getSize().height);
        }
    }
}
