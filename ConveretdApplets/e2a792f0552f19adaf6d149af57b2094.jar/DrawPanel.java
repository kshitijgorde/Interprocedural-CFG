import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Point;
import java.util.Vector;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, Runnable
{
    private static final int HEIGHT = 425;
    private static final int WIDTH = 550;
    private static final int SCRIBBLE = 1;
    private static final int SPRAY = 2;
    private static final int DELETE = 3;
    private static final int NODES = 4;
    private static final int S1 = 4;
    private static final int S2 = 5;
    private static final int S3 = 6;
    private static final int S4 = 9;
    private static final int S5 = 10;
    private static final int S6 = 11;
    private static final int DELETE_SIZE = 32;
    private static final int SMALL_OFFSET = 7;
    private static final int BIG_OFFSET = 15;
    private static final int FILL = 7;
    private static final int STROKE = 8;
    private BufferedImage currentImage;
    private BufferedImage oldImage;
    private BufferedImage tempImage;
    private BufferedImage storedImage;
    private Color drawColor;
    private int i;
    private int j;
    private int tempx;
    private int tempy;
    private int lastY;
    private int lastX;
    private int bgColor;
    private int mode;
    private PanelMediator mediator;
    private static Image mImage;
    private Cursor scribblePointer;
    private Cursor sprayPointer;
    private Cursor rubberPointer;
    private Cursor strokePointer;
    private Cursor fillPointer;
    private Cursor stampPointer;
    private boolean oldIsOld;
    private Thread runner;
    private int tX;
    private int tY;
    private boolean stop;
    private boolean checkUp;
    private boolean checkDown;
    private int fillX;
    private int fillY;
    private int picNo;
    private Vector fillPoints;
    private Point addPoint;
    public URL URL_STRING;
    private URL stamp1;
    private URL stamp2;
    private URL stamp3;
    private URL stamp4;
    private URL stamp5;
    private URL stamp6;
    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;
    private Image image5;
    private Image image6;
    private Image bg0;
    private Image bg1;
    private Image bg2;
    private Image bg3;
    private Image bgImage;
    
    public DrawPanel(final PanelMediator mediator) {
        this.drawColor = Color.red;
        this.mode = 1;
        this.oldIsOld = true;
        this.stop = true;
        this.checkUp = false;
        this.checkDown = false;
        this.picNo = 0;
        this.mediator = mediator;
        this.URL_STRING = mediator.URL_STRING;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setMinimumSize(new Dimension(550, 425));
        this.setMaximumSize(new Dimension(550, 425));
        this.currentImage = new BufferedImage(550, 425, 2);
        this.currentImage.createGraphics().fillRect(0, 0, 550, 425);
        this.oldImage = new BufferedImage(550, 425, 2);
        this.storedImage = new BufferedImage(550, 425, 2);
        this.setBackground(Color.white);
        this.fillPoints = new Vector();
        try {
            this.stamp1 = new URL(this.URL_STRING + "images/tree.gif");
            this.stamp2 = new URL(this.URL_STRING + "images/car.gif");
            this.stamp3 = new URL(this.URL_STRING + "images/house.gif");
            this.stamp4 = new URL(this.URL_STRING + "images/dog.gif");
            this.stamp5 = new URL(this.URL_STRING + "images/sun.gif");
            this.stamp6 = new URL(this.URL_STRING + "images/splat.gif");
            this.bg0 = new ImageIcon(new URL(this.URL_STRING + "images/background.gif")).getImage();
            this.bg1 = new ImageIcon(new URL(this.URL_STRING + "images/background1.gif")).getImage();
            this.bg2 = new ImageIcon(new URL(this.URL_STRING + "images/background2.gif")).getImage();
            this.bg3 = new ImageIcon(new URL(this.URL_STRING + "images/background3.gif")).getImage();
            this.image1 = new ImageIcon(this.stamp1).getImage();
            this.image2 = new ImageIcon(this.stamp2).getImage();
            this.image3 = new ImageIcon(this.stamp3).getImage();
            this.image4 = new ImageIcon(this.stamp4).getImage();
            this.image5 = new ImageIcon(this.stamp5).getImage();
            this.image6 = new ImageIcon(this.stamp6).getImage();
        }
        catch (MalformedURLException ex) {}
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        try {
            this.scribblePointer = defaultToolkit.createCustomCursor(defaultToolkit.getImage(new URL(this.URL_STRING + "images/scribblemouse.gif")), new Point(0, 31), "Scribble");
            this.sprayPointer = defaultToolkit.createCustomCursor(defaultToolkit.getImage(new URL(this.URL_STRING + "images/spraypaint.gif")), new Point(0, 6), "Spray");
            this.strokePointer = defaultToolkit.createCustomCursor(defaultToolkit.getImage(new URL(this.URL_STRING + "images/stroke.gif")), new Point(0, 31), "Stroke");
            this.fillPointer = defaultToolkit.createCustomCursor(defaultToolkit.getImage(new URL(this.URL_STRING + "images/fill2.gif")), new Point(6, 28), "Fill");
            this.stampPointer = defaultToolkit.createCustomCursor(defaultToolkit.getImage(new URL(this.URL_STRING + "images/stampMouse.gif")), new Point(15, 15), "Stamp");
            this.rubberPointer = defaultToolkit.createCustomCursor(defaultToolkit.getImage(new URL(this.URL_STRING + "images/rubbermouse.gif")), new Point(15, 15), "Rubber");
        }
        catch (Exception ex2) {}
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
            this.stop = false;
        }
    }
    
    public void run() {
        while (!this.stop) {
            this.spray(this.tX, this.tY);
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void stop() {
        this.runner = null;
    }
    
    public void paintComponent(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        super.paintComponent(graphics2D);
        graphics2D.drawImage(this.currentImage, 0, 0, this);
    }
    
    public void setColor(final Color drawColor) {
        this.drawColor = drawColor;
    }
    
    public void setMode(final int mode) {
        this.mode = mode;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.tempImage = this.storedImage;
        this.storedImage = this.oldImage;
        this.oldImage = this.tempImage;
        this.lastX = mouseEvent.getX();
        this.lastY = mouseEvent.getY();
        if (this.mode == 1) {
            this.scribble(mouseEvent.getX(), mouseEvent.getY());
        }
        if (this.mode == 2) {
            this.tX = mouseEvent.getX();
            this.tY = mouseEvent.getY();
            this.start();
        }
        if (this.mode == 3) {
            this.delete(mouseEvent.getX(), mouseEvent.getY());
        }
        if (this.mode == 7) {
            this.fillPoints.add(new Point(mouseEvent.getX(), mouseEvent.getY()));
            try {
                this.floodFill(mouseEvent.getX(), mouseEvent.getY());
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (this.mode == 4) {
            this.drawImage(mouseEvent.getX(), mouseEvent.getY(), this.image1);
        }
        if (this.mode == 5) {
            this.drawImage(mouseEvent.getX(), mouseEvent.getY(), this.image2);
        }
        if (this.mode == 6) {
            this.drawImage(mouseEvent.getX(), mouseEvent.getY(), this.image3);
        }
        if (this.mode == 9) {
            this.drawImage(mouseEvent.getX(), mouseEvent.getY(), this.image4);
        }
        if (this.mode == 10) {
            this.drawImage(mouseEvent.getX(), mouseEvent.getY(), this.image5);
        }
        if (this.mode == 11) {
            this.drawImage(mouseEvent.getX(), mouseEvent.getY(), this.image6);
        }
        if (this.mode == 8) {
            this.drawStroke(mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.stop();
        this.stop = true;
        this.storedImage.setData(this.currentImage.getData());
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.mode == 1) {
            this.setCursor(this.scribblePointer);
        }
        if (this.mode == 2) {
            this.setCursor(this.sprayPointer);
        }
        if (this.mode == 3) {
            this.setCursor(this.rubberPointer);
        }
        if (this.mode == 8) {
            this.setCursor(this.strokePointer);
        }
        if (this.mode == 7) {
            this.setCursor(this.fillPointer);
        }
        if (this.mode == 4) {
            this.setCursor(this.stampPointer);
        }
        if (this.mode == 5) {
            this.setCursor(this.stampPointer);
        }
        if (this.mode == 6) {
            this.setCursor(this.stampPointer);
        }
        if (this.mode == 9) {
            this.setCursor(this.stampPointer);
        }
        if (this.mode == 10) {
            this.setCursor(this.stampPointer);
        }
        if (this.mode == 11) {
            this.setCursor(this.stampPointer);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.mode == 1) {
            this.scribble(mouseEvent.getX(), mouseEvent.getY());
        }
        if (this.mode == 2) {
            this.tX = mouseEvent.getX();
            this.tY = mouseEvent.getY();
            this.spray(mouseEvent.getX(), mouseEvent.getY());
        }
        if (this.mode == 3) {
            this.delete(mouseEvent.getX(), mouseEvent.getY());
        }
        if (this.mode == 8) {
            this.drawStroke(mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void backGroundImage() {
        try {
            if (this.picNo == 0) {
                this.bgImage = this.bg0;
            }
            if (this.picNo == 1) {
                this.bgImage = this.bg1;
            }
            if (this.picNo == 2) {
                this.bgImage = this.bg2;
            }
            if (this.picNo == 3) {
                this.bgImage = this.bg3;
            }
        }
        catch (Exception ex) {}
        this.oldImage.setData(this.currentImage.getData());
        this.currentImage.createGraphics().drawImage(this.bgImage, 0, 0, this);
        this.picNo = ++this.picNo % 4;
        this.repaint();
        this.storedImage.setData(this.currentImage.getData());
    }
    
    public void spray(final int n, final int n2) {
        final Graphics2D graphics = this.currentImage.createGraphics();
        graphics.setPaint(this.drawColor);
        this.i = 0;
        while (this.i < 35) {
            this.tempx = n + (int)Math.round(14.0 * (Math.random() - 0.5));
            this.tempy = n2 + (int)((Math.random() - 0.5) * 2.0 * Math.sqrt(49 - (n - this.tempx) * (n - this.tempx)));
            graphics.drawLine(this.tempx, this.tempy, this.tempx, this.tempy);
            ++this.i;
        }
        this.i = 0;
        while (this.i < 12) {
            this.tempx = n + (int)Math.round(30.0 * (Math.random() - 0.5));
            this.tempy = n2 + (int)((Math.random() - 0.5) * 2.0 * Math.sqrt(225 - (n - this.tempx) * (n - this.tempx)));
            graphics.drawLine(this.tempx, this.tempy, this.tempx, this.tempy);
            ++this.i;
        }
        this.repaint();
    }
    
    public void scribble(final int lastX, final int lastY) {
        final Graphics2D graphics = this.currentImage.createGraphics();
        graphics.setPaint(this.drawColor);
        graphics.drawLine(this.lastX, this.lastY, lastX, lastY);
        this.lastX = lastX;
        this.lastY = lastY;
        this.repaint();
    }
    
    public void undo() {
        this.tempImage = this.oldImage;
        this.oldImage = this.currentImage;
        this.currentImage = this.tempImage;
        this.repaint();
        this.storedImage.setData(this.currentImage.getData());
    }
    
    public void drawStroke(final int lastX, final int lastY) {
        final Graphics2D graphics = this.currentImage.createGraphics();
        graphics.setPaint(this.drawColor);
        graphics.setStroke(new BasicStroke(5.0f, 1, 1));
        graphics.drawLine(this.lastX, this.lastY, lastX, lastY);
        this.lastX = lastX;
        this.lastY = lastY;
        this.repaint();
    }
    
    public void floodFill(final int n, final int n2) throws Exception {
        this.bgColor = this.currentImage.getRGB(n, n2);
        if (this.bgColor != this.drawColor.getRGB()) {
            this.setCursor(Cursor.getPredefinedCursor(3));
            while (!this.fillPoints.isEmpty()) {
                final Point point = this.fillPoints.elementAt(0);
                this.fillPoints.remove(0);
                this.fillX = (int)point.getX();
                this.fillY = (int)point.getY();
                if (this.fillY > 0) {
                    if (this.currentImage.getRGB(this.fillX, this.fillY - 1) != this.bgColor) {
                        this.checkUp = true;
                    }
                    else {
                        this.checkUp = false;
                        this.addPoint = new Point(this.fillX, this.fillY - 1);
                        if (!this.fillPoints.contains(this.addPoint)) {
                            this.fillPoints.add(new Point(this.fillX, this.fillY - 1));
                        }
                    }
                }
                if (this.fillY < 424) {
                    if (this.currentImage.getRGB(this.fillX, this.fillY + 1) != this.bgColor) {
                        this.checkDown = true;
                    }
                    else {
                        this.checkDown = false;
                        this.addPoint = new Point(this.fillX, this.fillY + 1);
                        if (!this.fillPoints.contains(this.addPoint)) {
                            this.fillPoints.add(new Point(this.fillX, this.fillY + 1));
                        }
                    }
                }
                this.currentImage.setRGB(this.fillX, this.fillY, this.drawColor.getRGB());
                if (this.fillX > 0) {
                    this.fillLeft(this.checkUp, this.checkDown, this.fillX - 1, this.fillY);
                }
                if (this.fillX < 549) {
                    this.fillRight(this.checkUp, this.checkDown, this.fillX + 1, this.fillY);
                }
            }
            this.fillPoints.clear();
            this.repaint();
            this.setCursor(this.fillPointer);
        }
    }
    
    public void fillLeft(boolean b, boolean b2, final int n, final int n2) {
        if (this.currentImage.getRGB(n, n2) == this.bgColor) {
            this.currentImage.setRGB(n, n2, this.drawColor.getRGB());
            if (n2 > 0 && n2 < 424) {
                if (this.currentImage.getRGB(n, n2 - 1) != this.bgColor) {
                    b = true;
                }
                else if (b) {
                    this.addPoint = new Point(n, n2 - 1);
                    if (!this.fillPoints.contains(this.addPoint)) {
                        this.fillPoints.add(new Point(n, n2 - 1));
                    }
                    b = false;
                }
                if (this.currentImage.getRGB(n, n2 + 1) != this.bgColor) {
                    b2 = true;
                }
                else if (b2) {
                    this.addPoint = new Point(n, n2 + 1);
                    if (!this.fillPoints.contains(this.addPoint)) {
                        this.fillPoints.add(new Point(n, n2 + 1));
                    }
                    b2 = false;
                }
            }
            if (n > 0) {
                this.fillLeft(b, b2, n - 1, n2);
            }
        }
    }
    
    public void fillRight(boolean b, boolean b2, final int n, final int n2) {
        if (this.currentImage.getRGB(n, n2) == this.bgColor) {
            this.currentImage.setRGB(n, n2, this.drawColor.getRGB());
            if (n2 > 0 && n2 < 424) {
                if (this.currentImage.getRGB(n, n2 - 1) != this.bgColor) {
                    b = true;
                }
                else if (b) {
                    this.addPoint = new Point(n, n2 - 1);
                    if (!this.fillPoints.contains(this.addPoint)) {
                        this.fillPoints.add(new Point(n, n2 - 1));
                    }
                    b = false;
                }
                if (this.currentImage.getRGB(n, n2 + 1) != this.bgColor) {
                    b2 = true;
                }
                else if (b2) {
                    this.addPoint = new Point(n, n2 + 1);
                    if (!this.fillPoints.contains(this.addPoint)) {
                        this.fillPoints.add(new Point(n, n2 + 1));
                    }
                    b2 = false;
                }
            }
            if (n < 549) {
                this.fillRight(b, b2, n + 1, n2);
            }
        }
    }
    
    public void delete(final int lastX, final int lastY) {
        final Graphics2D graphics = this.currentImage.createGraphics();
        graphics.setPaint(Color.white);
        graphics.setStroke(new BasicStroke(30.0f, 1, 1));
        graphics.draw(new Line2D.Double(this.lastX, this.lastY, lastX, lastY));
        this.repaint();
        this.lastX = lastX;
        this.lastY = lastY;
    }
    
    public void clear() {
        if (JOptionPane.showConfirmDialog(this, "Do you really want to start again?", "Start Over?", 0) == 0) {
            this.oldImage.setData(this.currentImage.getData());
            this.currentImage.createGraphics().fillRect(0, 0, 550, 425);
            this.repaint();
            this.storedImage.setData(this.currentImage.getData());
        }
    }
    
    public void drawImage(final int n, final int n2, final Image image) {
        final Graphics2D graphics = this.currentImage.createGraphics();
        final Image image2 = new ImageIcon(image).getImage();
        graphics.drawImage(image2, n - image2.getWidth(this) / 2, n2 - image2.getHeight(this) / 2, this);
        this.repaint();
    }
}
