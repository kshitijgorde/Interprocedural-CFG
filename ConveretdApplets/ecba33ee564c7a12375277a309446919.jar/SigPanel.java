import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class SigPanel extends Panel implements MouseListener, MouseMotionListener
{
    Signal signal;
    int prevX;
    int prevY;
    boolean setup;
    FourierControls fFourierControls;
    MagPanel fMagPanel;
    Image fImage;
    Graphics fGraphics;
    
    public SigPanel() {
        this.prevX = 0;
        this.prevY = 0;
        this.setup = false;
        this.setBackground(Color.white);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void clear() {
        this.signal.zero();
        this.repaint();
    }
    
    public void doDecay() {
        this.signal.doDecay();
        this.repaint();
    }
    
    public void doDoublePulse() {
        this.signal.doDoublePulse();
        this.repaint();
    }
    
    public void doNoise() {
        this.signal.doNoise();
        this.repaint();
    }
    
    public void doPulse() {
        this.signal.doPulse();
        this.repaint();
    }
    
    public void doSawtooth() {
        this.signal.doSawtooth();
        this.repaint();
    }
    
    public void doTriangle() {
        this.signal.doTriangle();
        this.repaint();
    }
    
    public void getCoefs(final TextArea textArea) {
        this.signal.getCoefs(textArea);
    }
    
    public void go(final int n) {
        this.signal.go(n);
        this.repaint();
    }
    
    public Dimension minimumSize() {
        return new Dimension(300, 100);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.signal.Modify(mouseEvent.getX(), mouseEvent.getY());
        this.prevX = mouseEvent.getX();
        this.prevY = mouseEvent.getY();
        this.repaint();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.signal.Drag(this.prevX, this.prevY, mouseEvent.getX(), mouseEvent.getY());
        this.prevX = mouseEvent.getX();
        this.prevY = mouseEvent.getY();
        this.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        if (!this.setup) {
            this.signal = new Signal(this.getSize().width, this.getSize().height, this.fFourierControls, this.fMagPanel);
            this.fImage = this.createImage(this.getSize().width, this.getSize().height);
            this.fGraphics = this.fImage.getGraphics();
            this.setup = true;
        }
        this.fGraphics.clearRect(0, 0, this.getSize().width, this.getSize().height);
        this.signal.Draw(this.fGraphics);
        graphics.drawImage(this.fImage, 0, 0, this);
    }
    
    public void setControls(final FourierControls fFourierControls, final MagPanel fMagPanel) {
        this.fFourierControls = fFourierControls;
        this.fMagPanel = fMagPanel;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
