// 
// Decompiled by Procyon v0.5.30
// 

package gjt;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Canvas;

public class ImageButton extends Canvas
{
    private boolean isDisabled;
    private Dimension preferredSize;
    protected Image image;
    protected Image disabledImage;
    private ImageButtonController controller;
    private boolean noBorder;
    private boolean raised;
    int thickness;
    public static int _defaultThickness;
    
    private Point findUpperLeft() {
        final Dimension size = this.size();
        return new Point(size.width / 2 - this.preferredSize.width / 2, size.height / 2 - this.preferredSize.height / 2);
    }
    
    public void paintInset() {
        final Point upperLeft = this.findUpperLeft();
        final Graphics g = this.getGraphics();
        final Image image = this.isDisabled() ? this.disabledImage : this.image;
        final Dimension size = this.size();
        if (g != null) {
            g.drawImage(image, upperLeft.x + this.thickness, upperLeft.y + this.thickness, this);
            this.raised = false;
        }
    }
    
    public ImageButton(final Image image) {
        this(image, ImageButton._defaultThickness, null);
    }
    
    public ImageButton(final Image image, final ImageButtonController controller) {
        this(image, ImageButton._defaultThickness, controller);
    }
    
    public ImageButton(final Image image, final int thickness, final ImageButtonController controller) {
        this.isDisabled = false;
        this.preferredSize = new Dimension(0, 0);
        this.noBorder = true;
        this.raised = true;
        if (controller == null) {
            this.controller = new SpringyImageButtonController(this);
        }
        else {
            this.controller = controller;
        }
        this.thickness = thickness;
        this.setImage(image);
    }
    
    public boolean isDisabled() {
        return this.isDisabled;
    }
    
    public void paint(final Graphics g) {
        if (this.isRaised()) {
            this.paintRaised();
        }
        else {
            this.paintInset();
        }
    }
    
    public boolean mouseUp(final Event event, final int x, final int y) {
        return !this.isDisabled() && this.controller.mouseUp(event, x, y);
    }
    
    public void setController(final ImageButtonController controller) {
        this.controller = controller;
    }
    
    public ImageButtonController getController() {
        return this.controller;
    }
    
    public Dimension minimumSize() {
        return this.preferredSize;
    }
    
    public void resize(final int w, final int h) {
        this.reshape(this.location().x, this.location().y, w, h);
    }
    
    private void createDisabledImage() {
    }
    
    static {
        ImageButton._defaultThickness = 2;
    }
    
    public void enable() {
        this.isDisabled = false;
        this.repaint();
    }
    
    public void disable() {
        this.isDisabled = true;
        this.repaint();
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        return !this.isDisabled() && this.controller.mouseDown(event, x, y);
    }
    
    public void setImage(final Image image) {
        this.image = image;
        this.preferredSize.width = image.getWidth(this) + 2 * this.thickness;
        this.preferredSize.height = image.getHeight(this) + 2 * this.thickness;
    }
    
    public void setNoBorder(final boolean nb) {
        this.noBorder = nb;
    }
    
    public Dimension preferredSize() {
        return this.preferredSize;
    }
    
    public boolean isRaised() {
        return this.raised;
    }
    
    public void paintRaised() {
        final Point upperLeft = this.findUpperLeft();
        final Graphics g = this.getGraphics();
        final Image image = this.isDisabled() ? this.disabledImage : this.image;
        if (g != null) {
            g.drawImage(image, upperLeft.x + this.thickness, upperLeft.y + this.thickness, this);
            this.raised = true;
        }
    }
    
    public boolean mouseDrag(final Event event, final int x, final int y) {
        return !this.isDisabled() && this.controller.mouseDrag(event, x, y);
    }
    
    public boolean isInside(final int x, final int y) {
        final Dimension size = this.size();
        return x >= 0 && x < size.width && y >= 0 && y < size.height;
    }
    
    public void reshape(final int x, final int y, final int w, final int h) {
        super.reshape(x, y, w, h);
    }
}
