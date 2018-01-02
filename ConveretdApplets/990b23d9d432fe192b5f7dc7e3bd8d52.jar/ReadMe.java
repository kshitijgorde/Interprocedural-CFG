import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class ReadMe extends Canvas implements MouseListener, MouseMotionListener, Runnable
{
    private Vector text;
    private Font font1;
    private Font font2;
    private Color color1;
    private Color color2;
    private int width;
    private int height;
    private Color bgcolor;
    private Color fgcolor1;
    private Color fgcolor2;
    private Polygon p1;
    private Polygon p2;
    private boolean active;
    private boolean changed;
    private boolean hot1;
    private boolean hot2;
    private boolean loaded;
    private int screen;
    private Image buff;
    private Graphics g;
    private Thread animThread;
    private boolean alive;
    private boolean hot1pressed;
    private boolean hot2pressed;
    private String file;
    
    public ReadMe(final Component component, final String file, final int width, final int height) {
        this.screen = 0;
        this.alive = true;
        this.width = width;
        this.height = height;
        this.file = file;
        this.font1 = new Font("Arial", 0, 12);
        this.font2 = new Font("Arial", 1, 14);
        this.color1 = new Color(0, 122, 122);
        this.color2 = new Color(122, 122, 0);
        this.fgcolor1 = new Color(0, 122, 122);
        this.fgcolor2 = new Color(0, 122, 0);
        this.setBackground(this.bgcolor = new Color(0, 0, 0));
        this.p1 = new Polygon(new int[] { width - 10, width - 20, width - 15 }, new int[] { height - 20, height - 20, height - 10 }, 3);
        this.p2 = new Polygon(new int[] { width - 10, width - 20, width - 15 }, new int[] { 20, 20, 10 }, 3);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.buff = component.createImage(width, height);
        this.g = this.buff.getGraphics();
    }
    
    public void delete() {
        this.buff = null;
        this.g = null;
        this.text = null;
        this.alive = false;
    }
    
    private Vector getLines(final String s) {
        final Vector<String> vector = new Vector<String>();
        String s2 = "";
        String string = "";
        final FontMetrics fontMetrics = this.getFontMetrics(this.font1);
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        while (stringTokenizer.hasMoreTokens()) {
            boolean b = false;
            while (!b && stringTokenizer.hasMoreTokens()) {
                s2 = " " + stringTokenizer.nextToken();
                if (fontMetrics.stringWidth(String.valueOf(string) + s2) < this.width - 40 && s2.toUpperCase().indexOf("<BR>") == -1) {
                    string = String.valueOf(string) + "" + s2;
                }
                else {
                    b = true;
                    s2 = this.removeTags(s2, "<BR>");
                }
            }
            vector.addElement(string.trim());
            string = s2;
        }
        return vector;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.hot1pressed = false;
        this.hot2pressed = false;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.changed = false;
        if (x > this.width - 30 && y > this.height - 30) {
            this.active = true;
            if (!this.hot1) {
                this.changed = true;
            }
            this.hot1 = true;
            this.hot2 = false;
        }
        else if (x > this.width - 30 && y < 30) {
            this.active = true;
            if (!this.hot2) {
                this.changed = true;
            }
            this.hot2 = true;
            this.hot1 = false;
        }
        else {
            if (this.active) {
                this.changed = true;
            }
            this.active = false;
            this.hot1 = false;
            this.hot2 = false;
        }
        if (this.changed) {
            this.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x > this.width - 30 && y > this.height - 30) {
            this.hot1pressed = true;
        }
        else if (x > this.width - 30 && y < 30) {
            this.hot2pressed = true;
        }
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.hot1pressed = false;
        this.hot2pressed = false;
    }
    
    public void paint(final Graphics graphics) {
        this.g.setColor(this.bgcolor);
        this.g.fillRect(0, 0, this.width, this.height);
        int n = 10;
        this.g.setColor(this.fgcolor1);
        if (this.hot1) {
            this.g.setColor(this.fgcolor2);
        }
        this.g.fillPolygon(this.p1);
        this.g.setColor(this.fgcolor1);
        if (this.hot2) {
            this.g.setColor(this.fgcolor2);
        }
        this.g.fillPolygon(this.p2);
        this.g.setColor(this.color1);
        if (!this.loaded) {
            this.g.drawString("Loading file...", 20, 20);
            graphics.drawImage(this.buff, 0, 0, this);
            if (this.animThread == null) {
                (this.animThread = new Thread(this)).start();
            }
        }
        else {
            final Enumeration<String> elements = (Enumeration<String>)this.text.elements();
            while (elements.hasMoreElements()) {
                this.g.setColor(this.color1);
                final String s = elements.nextElement();
                if (s.toUpperCase().indexOf("<H1>") == -1) {
                    this.g.setFont(this.font1);
                    this.g.setColor(this.color1);
                    n += 15;
                }
                else {
                    this.g.setFont(this.font2);
                    this.g.setColor(this.color2);
                    n += 20;
                }
                final String removeTags = this.removeTags(s, "<H1>");
                if (this.screen * 5 + n > -5 && this.screen * 5 + n < this.height + 10) {
                    this.g.drawString(removeTags, 10, this.screen * 5 + n);
                }
            }
        }
        this.g.setColor(this.fgcolor1);
        for (int i = 0; i < 4; ++i) {
            this.g.setColor(this.g.getColor().brighter());
            this.g.drawRect(i, i, this.width - i * 2, this.height - i * 2);
        }
        graphics.drawImage(this.buff, 0, 0, this);
    }
    
    private String readData(final String s) {
        int i = 0;
        String string = "";
        try {
            final InputStream openStream = new URL(s).openStream();
            while (i != -1) {
                i = openStream.read();
                if (i != -1) {
                    string = String.valueOf(string) + (char)i;
                }
            }
            openStream.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Corrupted data file or file is missing");
        }
        return string;
    }
    
    private String removeTags(String string, final String s) {
        final int index = string.toUpperCase().indexOf(s);
        if (index > -1) {
            string = String.valueOf(string.substring(0, index)) + string.substring(index + 4, string.length());
        }
        return string;
    }
    
    public void run() {
        this.text = this.getLines(this.readData(this.file));
        this.loaded = true;
        this.repaint();
        while (this.alive) {
            if (this.hot1pressed) {
                --this.screen;
                this.repaint();
            }
            else if (this.hot2pressed) {
                if (this.screen < 0) {
                    ++this.screen;
                }
                this.repaint();
            }
            try {
                Thread.sleep(60L);
            }
            catch (Exception ex) {}
        }
    }
    
    public void setFont(final Font font, final int n) {
        if (n == 1) {
            this.font1 = font;
        }
        else if (n == 2) {
            this.font2 = font;
        }
    }
    
    public void setFontColor(final Color color, final int n) {
        if (n == 1) {
            this.color1 = color;
        }
        else if (n == 2) {
            this.color2 = color;
        }
    }
    
    public void setScreenColor(final Color fgcolor2, final int n) {
        if (n == 1) {
            this.setBackground(this.bgcolor = fgcolor2);
        }
        else if (n == 2) {
            this.fgcolor1 = fgcolor2;
        }
        else if (n == 3) {
            this.fgcolor2 = fgcolor2;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
