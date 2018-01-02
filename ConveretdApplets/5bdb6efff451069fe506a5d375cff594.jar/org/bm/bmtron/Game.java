// 
// Decompiled by Procyon v0.5.30
// 

package org.bm.bmtron;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.util.Map;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Color;
import java.net.URL;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.net.MalformedURLException;
import java.io.File;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

public class Game extends Applet implements Runnable, MouseListener, MouseMotionListener
{
    public static final String TITLE = "BMTron";
    public static final String VERSION = "1.1";
    public static final String AUTHOR = "Mikhail A. Kryshen";
    public static final String HOME_PAGE = "http://bm.org.ru/java/bmtron.html";
    public static final boolean DISPLAY_URL = true;
    private static final boolean DEBUG = true;
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
    private final int hpHeight = 20;
    private int hpWidth;
    private final int hpX = 10;
    private final int hpY = 395;
    private Image offScreenImage;
    private Graphics offScreenGraphics;
    private boolean application;
    private Thread loader;
    Field field;
    private static boolean aa;
    Skin skin;
    static final Random random;
    
    public Game() {
        this.prect = new Rectangle(10, 40, 500, 350);
        this.statusCenter = true;
        this.status1 = "Loading BMTron...";
        this.status2 = "wait, please";
        this.statusLock = new Object();
        this.atitle = false;
        this.hpWidth = 500;
        this.application = false;
        this.loader = null;
        this.field = null;
        this.skin = null;
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("BMTron 1.1");
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        final Game game = new Game();
        game.application = true;
        frame.add(game, "Center");
        URL url = null;
        try {
            url = ((array.length > 0) ? new File(array[nextRandom(0, array.length - 1)]) : new File("skins/default.bin")).toURL();
        }
        catch (MalformedURLException ex) {
            System.err.println(ex);
        }
        game.init(frame, new Skin(game, url));
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }
    
    public void init() {
        URL url = null;
        try {
            String s = this.getParameter("skin");
            if (s == null) {
                int n;
                for (n = 1; this.getParameter("skin" + n) != null; ++n) {}
                if (--n > 0) {
                    s = this.getParameter("skin" + nextRandom(1, n));
                }
                else {
                    s = "skins/default.bin";
                }
            }
            url = new URL(this.getCodeBase(), s);
        }
        catch (MalformedURLException ex) {
            System.err.println(ex);
        }
        this.init(null, (this.skin == null) ? new Skin(this, url) : this.skin);
    }
    
    public void init(final Frame frame, final Skin skin) {
        System.err.println("BMTron 1.1");
        System.err.println("    by Mikhail A. Kryshen");
        System.err.println("Distributed under the terms of the GNU General Public License.");
        System.err.println();
        System.err.println("Using skin " + skin);
        this.skin = skin;
        this.setLayout(null);
        this.setBackground(Color.white);
        if (frame != null) {
            frame.pack();
            frame.show();
        }
        this.offScreenImage = this.createImage(520, 415);
        this.offScreenGraphics = this.offScreenImage.getGraphics();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        (this.loader = new Thread(this)).start();
    }
    
    public void destroy() {
        if (this.field != null) {
            this.field.stop();
        }
        this.removeMouseListener(this);
        this.removeMouseMotionListener(this);
        this.removeAll();
    }
    
    public void run() {
        try {
            this.setStatus("loading...");
            if (this.getSize().width < 520 || this.getSize().height < 415) {
                throw new Exception("Bad applet size");
            }
            if (!this.skin.isLoaded()) {
                this.skin.load();
            }
            this.setStatus("wait, please");
            final SettingsScreen settingsScreen = new SettingsScreen(this);
            settingsScreen.setSize(500, 350);
            settingsScreen.setLocation(10, 40);
            (this.field = new Field(settingsScreen)).setVisible(false);
            this.field.setLocation(10, 40);
            this.field.setSize(500, 350);
            this.add(this.field);
            this.add(settingsScreen);
            this.setStatus("BMTron", "by Mikhail A. Kryshen");
            Thread.currentThread();
            Thread.sleep(1500L);
            if (this.statusCenter) {
                this.setStatus("version 1.1", "by Mikhail A. Kryshen", false);
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
            this.setStatus("ERROR", t.getLocalizedMessage());
        }
    }
    
    static void setAA(final Graphics aa, final boolean b) {
        if (Game.aa) {
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
                Game.aa = false;
            }
        }
    }
    
    public synchronized void update(final Graphics graphics) {
        this.offScreenGraphics.setClip(graphics.getClip());
        this.paint(this.offScreenGraphics);
        graphics.drawImage(this.offScreenImage, 0, 0, null);
    }
    
    public void paint(final Graphics graphics) {
        Rectangle clipBounds = graphics.getClipBounds();
        if (clipBounds == null) {
            clipBounds = new Rectangle(0, 0, 520, 415);
        }
        synchronized (this.skin.getSkinLock()) {
            if (!this.prect.intersection(clipBounds).equals(clipBounds)) {
                graphics.setColor(this.skin.backColor);
                graphics.fillRect(0, 0, 519, 414);
                if (this.skin.background != null) {
                    graphics.drawImage(this.skin.background, 0, 0, this);
                }
                setAA(graphics, true);
                graphics.setColor(this.skin.linkColor);
                graphics.setFont(this.skin.linkFont);
                final FontMetrics fontMetrics = graphics.getFontMetrics();
                this.hpWidth = fontMetrics.stringWidth("http://bm.org.ru/java/bmtron.html");
                final int n = 395 + (20 - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
                graphics.drawString("http://bm.org.ru/java/bmtron.html", 10, n);
                if (this.atitle && this.skin.activeTitle != null) {
                    graphics.drawImage(this.skin.activeTitle, 0, 0, this);
                    graphics.drawLine(10, n + 1, this.hpWidth + 10 - 1, n + 1);
                }
                else if (this.skin.title != null) {
                    graphics.drawImage(this.skin.title, 0, 0, this);
                }
            }
            super.paint(graphics);
            if (!this.prect.intersection(clipBounds).equals(clipBounds) || this.statusCenter) {
                setAA(graphics, false);
                synchronized (this.statusLock) {
                    if (this.statusCenter) {
                        final Font messageFont1 = this.skin.messageFont1;
                        final Font messageFont2 = this.skin.messageFont2;
                        final FontMetrics fontMetrics2 = graphics.getFontMetrics(messageFont1);
                        final FontMetrics fontMetrics3 = graphics.getFontMetrics(messageFont2);
                        String status2 = this.status2;
                        int stringWidth;
                        for (int length = status2.length(); (stringWidth = fontMetrics3.stringWidth(status2)) > 480; status2 = this.status2.substring(0, length) + "...") {
                            --length;
                        }
                        this.status2 = status2;
                        final int n2 = (415 - fontMetrics2.getHeight() - fontMetrics3.getHeight() - 5) / 2;
                        final int n3 = n2 + 5 + fontMetrics2.getHeight();
                        final int n4 = (520 - fontMetrics2.stringWidth(this.status1)) / 2;
                        final int n5 = (520 - stringWidth) / 2;
                        final int n6 = (n4 < n5) ? n4 : n5;
                        final int n7 = (n2 < n3) ? n2 : n3;
                        graphics.setColor(this.skin.messageBackColor);
                        graphics.fillRoundRect(n6 - 15, n7 - 15, 550 - n6 * 2, 445 - n7 * 2, 40, 40);
                        graphics.setColor(this.skin.messageShadowColor);
                        graphics.drawRoundRect(n6 - 14, n7 - 14, 550 - n6 * 2, 445 - n7 * 2, 40, 40);
                        graphics.setColor(this.skin.messageBorderColor);
                        graphics.drawRoundRect(n6 - 15, n7 - 15, 550 - n6 * 2, 445 - n7 * 2, 40, 40);
                        graphics.setFont(messageFont1);
                        graphics.setColor(this.skin.messageShadowColor);
                        graphics.drawString(this.status1, n4 + 1, n2 + fontMetrics2.getAscent() + 1);
                        graphics.setColor(this.skin.messageTextColor1);
                        graphics.drawString(this.status1, n4, n2 + fontMetrics2.getAscent());
                        graphics.setFont(messageFont2);
                        graphics.setColor(this.skin.messageShadowColor);
                        graphics.drawString(this.status2, n5 + 1, n3 + fontMetrics3.getAscent() + 1);
                        graphics.setColor(this.skin.messageTextColor2);
                        graphics.drawString(this.status2, n5, n3 + fontMetrics3.getAscent());
                    }
                    else {
                        graphics.setFont(this.skin.statusFont);
                        final FontMetrics fontMetrics4 = graphics.getFontMetrics();
                        fontMetrics4.getHeight();
                        final int stringWidth2 = fontMetrics4.stringWidth(this.status1);
                        final int stringWidth3 = fontMetrics4.stringWidth(this.status2);
                        final int n8 = (310 - stringWidth2) / 2 + 200;
                        final int n9 = (310 - stringWidth3) / 2 + 200;
                        final int n10 = (13 - fontMetrics4.getHeight()) / 2 + 5;
                        final int n11 = n10 + 13;
                        graphics.setColor(this.skin.statusTextColor);
                        graphics.drawString(this.status1, n8, n10 + fontMetrics4.getAscent());
                        graphics.drawString(this.status2, n9, n11 + fontMetrics4.getAscent());
                    }
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
        this.repaint(0, 0, this.skin.title.getWidth(null), this.skin.title.getHeight(null));
        this.repaint(10, 395, this.hpWidth, 20);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if ((this.skin.title != null && x <= this.skin.title.getWidth(null) && y <= this.skin.title.getHeight(null)) || (x >= 10 && y >= 395 && x <= this.hpWidth + 10 && y <= 415)) {
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
            this.getAppletContext().showDocument(new URL("http://bm.org.ru/java/bmtron.html"), "_self");
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
    
    static int nextRandom(final int n, final int n2) {
        return (int)((n2 - n + 1) * Game.random.nextFloat() + n);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(520, 415);
    }
    
    public boolean isDoubleBuffered() {
        return true;
    }
    
    static {
        Game.aa = true;
        random = new Random();
    }
}
