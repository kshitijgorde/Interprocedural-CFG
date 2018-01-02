import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.AWTEventMulticaster;
import java.beans.PropertyChangeListener;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImgBtn extends Canvas implements MouseListener
{
    private static final int IMAGE_UP = 0;
    private static final int IMAGE_DOWN = 1;
    protected int curImage;
    protected boolean hand;
    protected Image[] images;
    protected int cxImage;
    protected int cyImage;
    protected boolean loadStatus;
    protected PropertyChangeSupport changes;
    protected String actionCommand;
    protected ActionListener actionListener;
    
    public ImgBtn(final boolean hand, final Image image) {
        this.curImage = 0;
        (this.images = new Image[1])[0] = image;
        this.hand = hand;
        this.init();
    }
    
    public ImgBtn(final boolean hand, final Image image, final Image image2) {
        this.curImage = 0;
        (this.images = new Image[2])[0] = image;
        this.images[1] = image2;
        this.hand = hand;
        this.init();
    }
    
    protected void init() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.images[0], 0);
        if (this.images.length > 1) {
            mediaTracker.addImage(this.images[1], 0);
        }
        try {
            mediaTracker.waitForAll();
            this.cxImage = this.images[0].getWidth(null);
            this.cyImage = this.images[0].getHeight(null);
            for (int i = 0; i < this.images.length; ++i) {
                if (this.cxImage != this.images[i].getWidth(null) || this.cyImage != this.images[i].getHeight(null)) {
                    throw new InterruptedException("Images aren't same size");
                }
            }
            if (mediaTracker.isErrorAny()) {
                throw new InterruptedException("Error loading image files");
            }
        }
        catch (InterruptedException ex) {
            System.err.println(ex);
            return;
        }
        this.changes = new PropertyChangeSupport(this);
        this.loadStatus = true;
        this.setSize(20, 40);
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.changes.addPropertyChangeListener(propertyChangeListener);
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.changes.removePropertyChangeListener(propertyChangeListener);
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.add(this.actionListener, actionListener);
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.remove(this.actionListener, actionListener);
    }
    
    protected void sourceActionEvent() {
        if (this.actionListener != null) {
            this.actionListener.actionPerformed(new ActionEvent(this, 1001, this.actionCommand));
        }
    }
    
    public boolean getLoadStatus() {
        return this.loadStatus;
    }
    
    public void setActionCommand(final String actionCommand) {
        final String actionCommand2 = this.actionCommand;
        this.actionCommand = actionCommand;
        this.changes.firePropertyChange("ActionCommand", actionCommand2, actionCommand);
    }
    
    public String getActionCommand() {
        return this.actionCommand;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.images.length > 1) {
            this.curImage = 1;
            this.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.images.length > 1) {
            this.curImage = 0;
            this.repaint();
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x >= 0 && x <= this.cxImage && y >= 0 && y <= this.cyImage) {
            this.sourceActionEvent();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.hand) {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.hand) {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.cxImage, this.cyImage);
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public synchronized void removeNotify() {
        this.removeMouseListener(this);
        super.removeNotify();
    }
    
    public synchronized void addNotify() {
        super.addNotify();
        this.addMouseListener(this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.images[this.curImage], 0, 0, null);
    }
}
