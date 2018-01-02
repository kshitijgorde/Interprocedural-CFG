import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.util.Map;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.net.URL;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.awt.Color;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BMTron extends Applet implements Runnable, MouseListener, MouseMotionListener
{
    private static final boolean DEBUG = false;
    private final int width = 520;
    private final int height = 415;
    final int pwidth = 500;
    final int pheight = 350;
    final int px = 10;
    final int py = 40;
    final Rectangle prect;
    private final int statx = 200;
    private final int staty = 5;
    private final int statwidth = 310;
    private final int statheight = 26;
    private boolean statusCenter;
    private String status1;
    private String status2;
    private Object statusLock;
    private boolean atitle;
    static final String title = "BMTron";
    static final String version = "1.0.4";
    static final String homepage = "http://www.sampo.ru/~kryshen/bmtron.html";
    private final Font hpFont;
    private final int hpHeight = 20;
    private int hpWidth;
    private final int hpX = 10;
    private final int hpY = 395;
    private Image offScrImage;
    private Graphics offScrGC;
    private boolean application;
    private Thread resourceLoader;
    private static boolean aa;
    BMTSkin skin;
    
    public BMTron() {
        this.prect = new Rectangle(10, 40, 500, 350);
        this.statusCenter = true;
        this.status1 = "Loading BMTron...";
        this.status2 = "please wait";
        this.statusLock = new Object();
        this.atitle = false;
        this.hpFont = new Font("Helvetica", 0, 12);
        this.hpWidth = 500;
        this.application = false;
        this.resourceLoader = null;
        this.skin = new BMTSkin(this);
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("BMTron 1.0.4");
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        final BMTron bmTron = new BMTron();
        bmTron.application = true;
        frame.add(bmTron, "Center");
        bmTron.init(frame);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }
    
    public void init() {
        this.init(null);
    }
    
    private void init(final Frame frame) {
        System.err.println("BMTron 1.0.4");
        this.setBackground(Color.white);
        this.setSize(520, 415);
        if (frame != null) {
            frame.pack();
            frame.show();
        }
        this.offScrImage = this.createImage(520, 415);
        this.offScrGC = this.offScrImage.getGraphics();
        (this.resourceLoader = new Thread(this)).start();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void run() {
        try {
            this.setStatus("loading...");
            InputStream openStream;
            if (this.application) {
                final File file = new File(System.getProperty("java.class.path"));
                String s;
                if (!file.isDirectory()) {
                    s = file.getParent();
                }
                else {
                    s = file.getPath();
                }
                if (s == null) {
                    s = ".";
                }
                openStream = new BufferedInputStream(new FileInputStream(new File(new File(s, "skins"), "default.bin")));
            }
            else {
                openStream = new URL(this.getCodeBase(), "skins/default.bin").openStream();
            }
            this.skin.load(openStream);
            openStream.close();
            this.setStatus("wait, please");
            this.setLayout(null);
            final BMTOptions bmtOptions = new BMTOptions(this);
            bmtOptions.setSize(500, 350);
            bmtOptions.setLocation(10, 40);
            this.add(bmtOptions);
            this.setStatus("BMTron", "by Mikhael Kryshen");
            Thread.currentThread();
            Thread.sleep(1500L);
            if (this.statusCenter) {
                this.setStatus("version", "1.0.4", false);
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
            this.setStatus("ERROR");
        }
    }
    
    static void setAA(final Graphics aa, final boolean b) {
        if (BMTron.aa) {
            try {
                new Object() {
                    void setAA(final Graphics graphics) throws Throwable {
                        if (b) {
                            ((Graphics2D)graphics).addRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
                        }
                        else {
                            ((Graphics2D)graphics).addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
                        }
                    }
                }.setAA(aa);
            }
            catch (Throwable t) {
                System.err.println("BM Tron: " + t);
                System.err.println("         Antialiasing is not supported by your Java VM.");
                BMTron.aa = false;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.offScrGC.setClip(graphics.getClip());
        this.paint(this.offScrGC);
        graphics.drawImage(this.offScrImage, 0, 0, null);
    }
    
    public void paint(final Graphics graphics) {
        Rectangle clipBounds = graphics.getClipBounds();
        if (clipBounds == null) {
            clipBounds = new Rectangle(0, 0, 520, 415);
        }
        if (!this.prect.intersection(clipBounds).equals(clipBounds)) {
            if (this.skin.background != null) {
                graphics.drawImage(this.skin.background, 0, 0, this);
            }
            else {
                graphics.clearRect(0, 0, 519, 414);
            }
            setAA(graphics, true);
            graphics.setColor(Color.blue);
            graphics.setFont(this.hpFont);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            this.hpWidth = fontMetrics.stringWidth("http://www.sampo.ru/~kryshen/bmtron.html");
            final int n = 395 + (20 - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
            graphics.drawString("http://www.sampo.ru/~kryshen/bmtron.html", 10, n);
            if (this.atitle && this.skin.alink != null) {
                graphics.drawImage(this.skin.alink, 0, 0, this);
                graphics.drawLine(10, n + 1, fontMetrics.stringWidth("http://www.sampo.ru/~kryshen/bmtron.html") + 10 - 1, n + 1);
            }
        }
        super.paint(graphics);
        if (!this.prect.intersection(clipBounds).equals(clipBounds) || this.statusCenter) {
            setAA(graphics, false);
            synchronized (this.statusLock) {
                if (this.statusCenter) {
                    final Font font = new Font("Helvetica", 1, 32);
                    final Font font2 = new Font("Helvetica", 1, 16);
                    final FontMetrics fontMetrics2 = graphics.getFontMetrics(font);
                    final FontMetrics fontMetrics3 = graphics.getFontMetrics(font2);
                    final int n2 = (415 - fontMetrics2.getHeight() - fontMetrics3.getHeight() - 5) / 2;
                    final int n3 = n2 + 5 + fontMetrics2.getHeight();
                    final int n4 = (520 - fontMetrics2.stringWidth(this.status1)) / 2;
                    final int n5 = (520 - fontMetrics3.stringWidth(this.status2)) / 2;
                    final int n6 = (n4 < n5) ? n4 : n5;
                    final int n7 = (n2 < n3) ? n2 : n3;
                    try {
                        graphics.setColor(new Color(255, 255, 255, 210));
                    }
                    catch (Throwable t) {
                        graphics.setColor(Color.white);
                    }
                    graphics.fillRoundRect(n6 - 15, n7 - 15, 550 - n6 * 2, 445 - n7 * 2, 40, 40);
                    graphics.setColor(Color.black);
                    graphics.drawRoundRect(n6 - 14, n7 - 14, 550 - n6 * 2, 445 - n7 * 2, 40, 40);
                    graphics.setColor(Color.red);
                    graphics.drawRoundRect(n6 - 15, n7 - 15, 550 - n6 * 2, 445 - n7 * 2, 40, 40);
                    graphics.setFont(font);
                    graphics.setColor(Color.black);
                    graphics.drawString(this.status1, n4 + 1, n2 + fontMetrics2.getAscent() + 1);
                    graphics.setColor(Color.blue);
                    graphics.drawString(this.status1, n4, n2 + fontMetrics2.getAscent());
                    graphics.setFont(font2);
                    graphics.setColor(Color.black);
                    graphics.drawString(this.status2, n5 + 1, n3 + fontMetrics3.getAscent() + 1);
                    graphics.setColor(Color.red);
                    graphics.drawString(this.status2, n5, n3 + fontMetrics3.getAscent());
                }
                else {
                    graphics.setFont(new Font("Helvetica", 1, 12));
                    final FontMetrics fontMetrics4 = graphics.getFontMetrics();
                    fontMetrics4.getHeight();
                    final int stringWidth = fontMetrics4.stringWidth(this.status1);
                    final int stringWidth2 = fontMetrics4.stringWidth(this.status2);
                    final int n8 = (310 - stringWidth) / 2 + 200;
                    final int n9 = (310 - stringWidth2) / 2 + 200;
                    final int n10 = (13 - fontMetrics4.getHeight()) / 2 + 5;
                    final int n11 = n10 + 13;
                    graphics.setColor(Color.blue);
                    graphics.drawString(this.status1, n8, n10 + fontMetrics4.getAscent());
                    graphics.drawString(this.status2, n9, n11 + fontMetrics4.getAscent());
                }
            }
        }
    }
    
    void setStatus(final String status1, final String status2, final boolean statusCenter) {
        final boolean statusCenter2 = this.statusCenter;
        synchronized (this.statusLock) {
            this.statusCenter = statusCenter;
            if (status1 != null) {
                this.status1 = status1;
            }
            if (status2 != null) {
                this.status2 = status2;
            }
        }
        if (statusCenter2 || statusCenter) {
            this.repaint();
        }
        else {
            this.repaint(200, 5, 310, 26);
        }
    }
    
    void setStatus(final String s, final String s2) {
        this.setStatus(s, s2, this.statusCenter);
    }
    
    void setStatus(final String s) {
        this.setStatus(null, s);
    }
    
    private void setATitle(final boolean atitle) {
        this.atitle = atitle;
        if (atitle) {
            this.setCursor(new Cursor(12));
        }
        else {
            this.setCursor(new Cursor(0));
        }
        this.repaint(0, 0, 159, 35);
        this.repaint(10, 395, this.hpWidth, 20);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if ((x >= 3 && y >= 2 && x <= 158 && y <= 34) || (x >= 10 && y >= 395 && x <= this.hpWidth + 10 && y <= 415)) {
            if (this.atitle) {
                return;
            }
        }
        else if (!this.atitle) {
            return;
        }
        this.setATitle(!this.atitle);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.atitle) {
            return;
        }
        this.setATitle(false);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!this.atitle) {
            return;
        }
        try {
            this.getAppletContext().showDocument(new URL("http://www.sampo.ru/~kryshen/bmtron.html"), "_self");
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    static void trace(final Object o) {
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(520, 415);
    }
    
    public boolean isDoubleBuffered() {
        return true;
    }
    
    static {
        BMTron.aa = true;
    }
}
