// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.image;

import jmaster.jumploader.model.api.config.ImageConfig;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Iterator;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import java.util.Vector;
import jmaster.jumploader.model.api.B;
import jmaster.util.swing.GUIHelper;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

public class ImageControl extends JComponent implements MouseListener, MouseMotionListener
{
    private static final long B = -6171717419492647594L;
    public static final String PREFIX = "imageControl";
    protected GUIHelper N;
    protected B F;
    protected Vector M;
    protected RectangleRubberBand P;
    protected Double I;
    protected Integer D;
    protected Integer J;
    protected JLabel O;
    protected BufferedImage C;
    protected int L;
    protected int G;
    protected double A;
    protected Point H;
    protected boolean E;
    protected Image K;
    
    public ImageControl(final B f) {
        this.N = GUIHelper.getInstance();
        this.M = new Vector();
        this.I = null;
        this.D = null;
        this.J = null;
        this.O = new JLabel();
        this.A = 1.0;
        this.H = null;
        this.E = false;
        this.F = f;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.N.injectProperties(this, "imageControl");
        this.N.injectProperties(this.O, "imageControl", "emptyControlLabel");
        this.O.setHorizontalAlignment(0);
    }
    
    public void init() {
        this.init(true);
    }
    
    public void init(final boolean b) {
        this.setPreferredSize(new Dimension((int)(this.L * this.A), (int)(this.G * this.A)));
        if (b) {
            this.B(this.P = null);
        }
        this.revalidate();
        this.repaint();
    }
    
    public void addImageControlListener(final ImageControlListener imageControlListener) {
        this.M.add(imageControlListener);
    }
    
    public void removeImageControlListener(final ImageControlListener imageControlListener) {
        this.M.remove(imageControlListener);
    }
    
    private void B(final RectangleRubberBand rectangleRubberBand) {
        final Iterator<ImageControlListener> iterator = this.M.iterator();
        while (iterator.hasNext()) {
            iterator.next().rectangleRubberBandChanged(this, rectangleRubberBand);
        }
    }
    
    public BufferedImage getImage() {
        return this.C;
    }
    
    public void setImage(final BufferedImage c) {
        this.C = c;
        if (c != null) {
            this.L = c.getWidth();
            this.G = c.getHeight();
        }
        else {
            this.L = 0;
            this.G = 0;
        }
        this.init();
    }
    
    public void discardImage() {
        this.C = null;
        this.L = 0;
        this.G = 0;
        this.init();
    }
    
    public double getZoomFactor() {
        return this.A;
    }
    
    public void setZoomFactor(final double a) {
        this.A = a;
        this.init(false);
    }
    
    public int getImageHeight() {
        return this.G;
    }
    
    public int getImageWidth() {
        return this.L;
    }
    
    public RectangleRubberBand getBand() {
        return this.P;
    }
    
    public void setBandCoords(final int n, final int n2, final int n3, final int n4) {
        if (this.P == null) {
            this.P = this.A();
        }
        else {
            this.A(this.P);
        }
        this.P.setCoords(n, n2, n3, n4);
        this.A(this.P);
    }
    
    public boolean isWaitMode() {
        return this.E;
    }
    
    public void setWaitMode(final boolean e) {
        this.E = e;
    }
    
    public Image getWaitImage() {
        return this.K;
    }
    
    public void setWaitImage(final Image k) {
        this.K = k;
    }
    
    public Integer getBandPerimeterMax() {
        return this.J;
    }
    
    public void setBandPerimeterMax(final Integer j) {
        this.J = j;
    }
    
    public Integer getBandPerimeterMin() {
        return this.D;
    }
    
    public void setBandPerimeterMin(final Integer d) {
        this.D = d;
    }
    
    public Double getBandProportions() {
        return this.I;
    }
    
    public void setBandProportions(final Double i) {
        this.I = i;
    }
    
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        if (this.C != null) {
            graphics.drawImage(this.C, 0, 0, (int)(this.L * this.A), (int)(this.G * this.A), 0, 0, this.L, this.G, null);
            if (this.P != null) {
                this.A((Graphics2D)graphics, this.P);
            }
        }
        else {
            final Graphics2D graphics2D = (Graphics2D)graphics;
            graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
            this.O.setBounds(this.getBounds());
            this.O.paint(graphics);
        }
    }
    
    protected void A(final RectangleRubberBand rectangleRubberBand) {
        this.A(null, rectangleRubberBand);
    }
    
    protected synchronized void A(Graphics2D graphics2D, final RectangleRubberBand rectangleRubberBand) {
        if (graphics2D == null) {
            graphics2D = (Graphics2D)this.getGraphics();
        }
        graphics2D.setColor(Color.BLACK);
        graphics2D.setXORMode(Color.WHITE);
        graphics2D.setStroke(RectangleRubberBand.O);
        if (rectangleRubberBand.getWidth() == 0 || rectangleRubberBand.getHeight() == 0) {
            System.out.println("!");
        }
        final Point imageToScreen = this.imageToScreen(rectangleRubberBand.getLeftTop());
        final Point imageToScreen2 = this.imageToScreen(rectangleRubberBand.getRightBottom());
        imageToScreen2.setLocation(imageToScreen2.getX() + this.A - 1.0, imageToScreen2.getY() + this.A - 1.0);
        final double n = imageToScreen2.getX() - imageToScreen.getX();
        final double n2 = imageToScreen2.getY() - imageToScreen.getY();
        graphics2D.drawRect((int)imageToScreen.getX(), (int)imageToScreen.getY(), (int)n, (int)n2);
        graphics2D.setStroke(RectangleRubberBand.I);
        for (int i = 0; i < 8; ++i) {
            graphics2D.drawRect((int)(RectangleRubberBand.ANCHOR_X_FACTOR[i] * n + imageToScreen.getX() - 3.0), (int)(RectangleRubberBand.ANCHOR_Y_FACTOR[i] * n2 + imageToScreen.getY() - 3.0), 6, 6);
        }
    }
    
    public Point screenToImage(final Point point) {
        int n = (int)(point.getX() / this.A);
        if (n < 0) {
            n = 0;
        }
        if (n >= this.L) {
            n = this.L - 1;
        }
        int n2 = (int)(point.getY() / this.A);
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 >= this.G) {
            n2 = this.G - 1;
        }
        return new Point(n, n2);
    }
    
    public Point imageToScreen(final Point point) {
        final Point point2 = new Point();
        point2.setLocation(point.getX() * this.A, point.getY() * this.A);
        return point2;
    }
    
    public boolean isImagePoint(final Point point) {
        return this.C != null && point.getX() >= 0.0 && point.getY() >= 0.0 && point.getX() < this.L && point.getY() < this.G;
    }
    
    public Rectangle getBandScreenRectangle(final RectangleRubberBand rectangleRubberBand) {
        final Point imageToScreen = this.imageToScreen(rectangleRubberBand.getLeftTop());
        final Point imageToScreen2 = this.imageToScreen(rectangleRubberBand.getRightBottom());
        imageToScreen2.setLocation(imageToScreen2.getX() + this.A - 1.0, imageToScreen2.getY() + this.A - 1.0);
        final Rectangle rectangle = new Rectangle(imageToScreen);
        rectangle.add(imageToScreen2);
        return rectangle;
    }
    
    private boolean A(final RectangleRubberBand rectangleRubberBand, final Point point) {
        return this.getBandScreenRectangle(rectangleRubberBand).contains(point);
    }
    
    private int B(final RectangleRubberBand rectangleRubberBand, final Point point) {
        int n = -1;
        for (int i = 0; i < 8; ++i) {
            if (this.A(rectangleRubberBand, i).contains(point)) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    private Rectangle A(final RectangleRubberBand rectangleRubberBand, final int n) {
        final Point imageToScreen = this.imageToScreen(rectangleRubberBand.getLeftTop());
        final Point imageToScreen2 = this.imageToScreen(rectangleRubberBand.getRightBottom());
        imageToScreen2.setLocation(imageToScreen2.getX() + this.A - 1.0, imageToScreen2.getY() + this.A - 1.0);
        return new Rectangle((int)(RectangleRubberBand.ANCHOR_X_FACTOR[n] * (imageToScreen2.getX() - imageToScreen.getX()) + imageToScreen.getX() - 3.0), (int)(RectangleRubberBand.ANCHOR_Y_FACTOR[n] * (imageToScreen2.getY() - imageToScreen.getY()) + imageToScreen.getY() - 3.0), 6, 6);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.P != null) {
            final int b = this.B(this.P, mouseEvent.getPoint());
            if (b != -1) {
                this.P.setHotAnchorId(b);
                this.P.setStatus(1);
            }
            else if (this.A(this.P, mouseEvent.getPoint())) {
                this.P.setStatus(2);
                this.setCursor(RectangleRubberBand.D);
                final Point imageToScreen = this.imageToScreen(this.P.getLeftTop());
                this.H = new Point((int)(mouseEvent.getX() - imageToScreen.getX()), (int)(mouseEvent.getY() - imageToScreen.getY()));
            }
        }
        if (this.P == null || this.P.getStatus() == 0) {
            if (this.P != null) {
                this.A(this.P);
            }
            final Point screenToImage = this.screenToImage(mouseEvent.getPoint());
            if (this.isImagePoint(screenToImage)) {
                (this.P = this.A()).setPoint0(screenToImage);
                this.P.setPoint1(this.P.getPoint0());
                this.P.setStatus(1);
                this.P.setHotAnchorId(4);
                this.A(this.P);
            }
        }
    }
    
    private RectangleRubberBand A() {
        final RectangleRubberBand rectangleRubberBand = new RectangleRubberBand();
        final ImageConfig j = this.F.J();
        if (j.getCropPerimeterMin() != null) {
            rectangleRubberBand.setPerimeterMin(new Integer(j.getCropPerimeterMin()));
        }
        if (j.getCropRatio() != null) {
            rectangleRubberBand.setProportions(new Double(j.getCropRatio()));
        }
        return rectangleRubberBand;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.P != null) {
            this.P.setStatus(0);
            if (this.P.getWidth() == 0 && this.P.getHeight() == 0) {
                this.A(this.P);
                this.P = null;
            }
            this.B(this.P);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.P != null && this.P.getStatus() != 0) {
            final int x0 = this.P.getX0();
            final int y0 = this.P.getY0();
            final int x2 = this.P.getX1();
            final int y2 = this.P.getY1();
            Label_0353: {
                switch (this.P.getStatus()) {
                    case 2: {
                        final Point screenToImage = this.screenToImage(new Point((int)(mouseEvent.getX() - this.H.getX()), (int)(mouseEvent.getY() - this.H.getY())));
                        int i = (int)(screenToImage.getX() + this.P.getWidth());
                        int j = (int)(screenToImage.getY() + this.P.getHeight());
                        while (i >= this.L) {
                            --i;
                        }
                        while (j >= this.G) {
                            --j;
                        }
                        screenToImage.setLocation(i - this.P.getWidth(), j - this.P.getHeight());
                        this.P.setPoint0(screenToImage);
                        this.P.setPoint1(new Point(i, j));
                        break;
                    }
                    case 1: {
                        final Point screenToImage2 = this.screenToImage(mouseEvent.getPoint());
                        switch (this.P.getHotAnchorId()) {
                            case 0:
                            case 2:
                            case 4:
                            case 6: {
                                this.P.setPoint1(screenToImage2);
                                break Label_0353;
                            }
                            case 3:
                            case 7: {
                                this.P.setX1((int)screenToImage2.getX());
                                break Label_0353;
                            }
                            case 1:
                            case 5: {
                                this.P.setY1((int)screenToImage2.getY());
                                break Label_0353;
                            }
                        }
                        break;
                    }
                }
            }
            if (x0 != this.P.getX0() || y0 != this.P.getY0() || x2 != this.P.getX1() || y2 != this.P.getY1()) {
                final int x3 = this.P.getX0();
                final int y3 = this.P.getY0();
                final int x4 = this.P.getX1();
                final int y4 = this.P.getY1();
                this.P.setCoords(x0, y0, x2, y2);
                this.A(this.P);
                this.P.setCoords(x3, y3, x4, y4);
                this.A(this.P);
            }
            this.B(this.P);
        }
        this.scrollRectToVisible(new Rectangle(mouseEvent.getX(), mouseEvent.getY(), 1, 1));
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.P != null) {
            final int b = this.B(this.P, mouseEvent.getPoint());
            if (b != -1) {
                this.setCursor(RectangleRubberBand.F[b]);
            }
            else if (this.A(this.P, mouseEvent.getPoint())) {
                this.setCursor(RectangleRubberBand.G);
            }
            else {
                this.setCursor(RectangleRubberBand.H);
            }
        }
    }
}
