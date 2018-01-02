import java.awt.Graphics;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import gamelib.AVEntry;
import gamelib.ActionBuffer;
import java.awt.Image;
import java.awt.event.MouseListener;
import gamelib.OffComponent;

// 
// Decompiled by Procyon v0.5.30
// 

class ArsenalControl extends OffComponent implements MouseListener
{
    private static Image img;
    
    ArsenalControl(final ActionBuffer actionBuffer) {
        super(actionBuffer);
        super.buffer.addMouseListener(this);
        if (ArsenalControl.img == null) {
            ArsenalControl.img = new AVEntry("Arsenal.gif", 0).getImage();
        }
        if (ArsenalControl.img == null) {
            return;
        }
        this.setSize(ArsenalControl.img.getWidth(super.buffer), ArsenalControl.img.getHeight(super.buffer));
        this.setPosition(0, super.buffer.getBounds().height - super.bounds.height);
        this.setVisible(true);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (super.bounds.contains(mouseEvent.getX(), mouseEvent.getY())) {
            try {
                ((Field)super.buffer).applet.getAppletContext().showDocument(new URL("http://www.gamemakers.de/TankGameFA"));
            }
            catch (Exception ex) {}
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    protected void paint(final Graphics graphics) {
        graphics.drawImage(ArsenalControl.img, super.bounds.x, super.bounds.y, super.buffer);
    }
    
    public void removeSelf() {
        super.buffer.removeMouseListener(this);
        super.removeSelf();
    }
}
