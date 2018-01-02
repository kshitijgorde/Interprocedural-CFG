// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet;

import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.util.Vector;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public class MapTool extends Canvas implements MouseListener, MouseMotionListener
{
    private Vector layersVector;
    private Vector layersTransparencies;
    public Image bgImage;
    public Image mapImage;
    public Image offScreenImage;
    Object parent;
    private boolean waiter;
    Font font;
    FontMetrics fm;
    
    public MapTool(final Object par) {
        this.waiter = false;
        this.parent = par;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.font = new Font(((Main)this.parent).getFont().getFamily(), 1, 18);
        this.layersVector = new Vector();
        this.layersTransparencies = new Vector();
    }
    
    public void startWaiter() {
        this.waiter = true;
        this.repaint();
    }
    
    public void stopWaiter() {
        this.waiter = false;
        this.repaint();
    }
    
    public void mouseMoved(final MouseEvent e) {
        ((Main)this.parent).drawCoords(e.getX(), e.getY());
    }
    
    public void mouseDragged(final MouseEvent e) {
        ((Main)this.parent).drawCoords(e.getX(), e.getY());
    }
    
    public void mouseExited(final MouseEvent e) {
        ((Main)this.parent).statusBar.setText("");
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void changeMap(final Image mapImg) {
        this.bgImage = mapImg;
        this.setSize(this.bgImage.getWidth(null), this.bgImage.getHeight(null));
        this.combineMapImages();
        ((Main)this.parent).invalidate();
    }
    
    public void paint(final Graphics g) {
        if (this.mapImage != null) {
            g.drawImage(this.mapImage, 0, 0, this);
        }
        ((Main)this.parent).repaintMap(g);
        if (this.waiter) {
            this.fm = g.getFontMetrics(this.font);
            g.setFont(this.font);
            final String str = "Please wait...";
            final int wid = this.fm.stringWidth(str);
            g.setColor(Color.white);
            g.clearRect((this.getSize().width - wid) / 2 - 10, this.getSize().height / 2 - this.fm.getHeight() / 2 - 10, wid + 20, this.fm.getHeight() + 30);
            g.setColor(Color.black);
            g.drawString(str, (this.getSize().width - wid) / 2, this.getSize().height / 2 + this.fm.getHeight() / 2);
        }
    }
    
    public void update(final Graphics g) {
        final Dimension screenSize = this.getSize();
        if (this.offScreenImage == null || this.offScreenImage.getWidth(null) != screenSize.width || this.offScreenImage.getHeight(null) != screenSize.height) {
            this.offScreenImage = this.createImage(screenSize.width, screenSize.height);
        }
        final Graphics offScreenGraphics = this.offScreenImage.getGraphics();
        offScreenGraphics.setColor(this.getBackground());
        offScreenGraphics.fillRect(0, 0, screenSize.width, screenSize.height);
        this.paint(offScreenGraphics);
        g.drawImage(this.offScreenImage, 0, 0, this);
    }
    
    public synchronized void combineMapImages() {
        boolean editWaiter = false;
        if (!this.waiter) {
            this.startWaiter();
            editWaiter = true;
        }
        int[] bgRaster = null;
        final MyPixelGrabber bgPixGrab = new MyPixelGrabber(this.bgImage);
        try {
            bgPixGrab.grabPixels();
            bgRaster = (int[])bgPixGrab.getPixels();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        final int[] resultImage = bgRaster.clone();
        for (int i = 0; i < this.getLayersVector().size(); ++i) {
            final Image bufImg = this.getLayersVector().elementAt(i);
            if (bufImg != null && this.layersTransparencies.elementAt(i) != null) {
                final float transp = this.layersTransparencies.elementAt(i);
                int[] newImgRaster = new int[bufImg.getWidth(null) * bufImg.getHeight(null)];
                final MyPixelGrabber pixGrab = new MyPixelGrabber(bufImg);
                try {
                    pixGrab.grabPixels();
                    newImgRaster = (int[])pixGrab.getPixels();
                    for (int j = 0; j < newImgRaster.length; ++j) {
                        if (pixGrab.getAlpha(newImgRaster[j]) > 0) {
                            final int pix0 = (int)(pixGrab.getRed(newImgRaster[j]) * transp + bgPixGrab.getRed(resultImage[j]) * (1.0f - transp));
                            final int pix2 = (int)(pixGrab.getGreen(newImgRaster[j]) * transp + bgPixGrab.getGreen(resultImage[j]) * (1.0f - transp));
                            final int pix3 = (int)(pixGrab.getBlue(newImgRaster[j]) * transp + bgPixGrab.getBlue(resultImage[j]) * (1.0f - transp));
                            resultImage[j] = pix3 + (pix2 << 8) + (pix0 << 16);
                        }
                        else {
                            resultImage[j] = bgPixGrab.getBlue(resultImage[j]) + (bgPixGrab.getGreen(resultImage[j]) << 8) + (bgPixGrab.getRed(resultImage[j]) << 16);
                        }
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        this.mapImage = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(this.bgImage.getWidth(null), this.bgImage.getHeight(null), Toolkit.getDefaultToolkit().getColorModel(), resultImage, 0, this.bgImage.getWidth(null)));
        if (editWaiter) {
            this.stopWaiter();
        }
        this.repaint();
    }
    
    public Vector getLayersVector() {
        return this.layersVector;
    }
    
    public void setLayersVector(final Vector layersVector, final Vector layersTransparencies) {
        this.layersVector = (Vector)layersVector.clone();
        this.layersTransparencies = (Vector)layersTransparencies.clone();
    }
}
