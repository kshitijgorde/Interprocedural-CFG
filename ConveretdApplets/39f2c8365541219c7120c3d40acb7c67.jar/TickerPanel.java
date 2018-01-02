import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class TickerPanel extends Panel implements Runnable, MouseListener
{
    Ticker applet;
    Dimension mySize;
    boolean isRunning;
    static int panelCount;
    int id;
    int scrollDirection;
    int scrollDelay;
    int scrollDelay2;
    Thread myThread;
    Vector images;
    
    public void mouseClicked(final MouseEvent evt) {
        final int scrollOld = this.scrollDirection;
        this.scrollDirection = 0;
        final int x = evt.getX();
        for (int i = 0; i < this.images.size(); ++i) {
            final ImageWrapper wrapper = this.images.elementAt(i);
            if (x > wrapper.x && x < wrapper.x + wrapper.width) {
                if (wrapper.link != null) {
                    this.applet.openPage(wrapper.link, true);
                }
                else if (wrapper.symbol != null) {
                    this.applet.openPage(wrapper.symbol);
                }
            }
        }
        this.scrollDirection = scrollOld;
    }
    
    public void mousePressed(final MouseEvent evt) {
    }
    
    public TickerPanel(final Ticker a, final Dimension d) {
        this.mySize = new Dimension(0, 0);
        this.isRunning = false;
        this.id = 0;
        this.scrollDirection = -1;
        this.scrollDelay = 1;
        this.scrollDelay2 = 1;
        this.images = new Vector();
        this.applet = a;
        this.mySize = d;
        this.addMouseListener(this);
        this.id = ++TickerPanel.panelCount;
    }
    
    public void mouseReleased(final MouseEvent evt) {
    }
    
    public void paint(final Graphics g) {
        for (int i = 0; i < this.images.size(); ++i) {
            final ImageWrapper wrapper = this.images.elementAt(i);
            if (this.scrollDirection < 0 && wrapper.x + wrapper.width < 0) {
                final ImageWrapper wrapper2 = this.images.lastElement();
                wrapper.x = wrapper2.x + wrapper2.width;
                if (wrapper.x < this.mySize.width) {
                    wrapper.x = this.mySize.width;
                }
                this.images.removeElementAt(i);
                this.images.addElement(wrapper);
            }
            else if (this.scrollDirection > 0 && wrapper.x > this.mySize.width) {
                final ImageWrapper wrapper2 = this.images.firstElement();
                wrapper.x = wrapper2.x - wrapper.width;
                if (wrapper.x > 0) {
                    wrapper.x = 0;
                }
                this.images.removeElementAt(i);
                this.images.insertElementAt(wrapper, 0);
            }
            else {
                final ImageWrapper imageWrapper = wrapper;
                imageWrapper.x += this.scrollDirection;
                g.drawImage(wrapper.image, wrapper.x, 0, this);
            }
        }
    }
    
    public void stopThread() {
        this.isRunning = false;
        try {
            this.myThread.interrupt();
        }
        catch (Exception ex) {}
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void mouseEntered(final MouseEvent evt) {
        this.scrollDelay = this.scrollDelay2 * 2;
    }
    
    public void mouseExited(final MouseEvent evt) {
        this.scrollDelay = this.scrollDelay2;
    }
    
    static {
        TickerPanel.panelCount = 0;
    }
    
    public void setScroll(final int direction, final int delay) {
        this.scrollDirection = direction;
        this.scrollDelay = delay;
        this.scrollDelay2 = this.scrollDelay;
    }
    
    public Dimension getMinimumSize() {
        return this.mySize;
    }
    
    public Dimension getPreferredSize() {
        return this.mySize;
    }
    
    public void run() {
        System.out.println("ScrollThread#" + this.id + " started.");
        while (this.isRunning) {
            this.repaint();
            try {
                Thread.sleep(this.scrollDelay);
            }
            catch (InterruptedException ie) {
                this.isRunning = false;
            }
        }
        System.out.println("ScrollThread#" + this.id + " stopped.");
    }
    
    public synchronized void addPromoItem(final String s, final Color c) {
        final StringTokenizer st = new StringTokenizer(s, "~");
        String text = null;
        String link = null;
        if (st.hasMoreTokens()) {
            text = st.nextToken();
        }
        if (st.hasMoreTokens()) {
            link = st.nextToken();
        }
        if (text == null) {
            return;
        }
        final Font font = this.getFont();
        final FontMetrics fm = this.getFontMetrics(font);
        final int width = fm.stringWidth(text) + 10;
        final Image img = this.createImage(width, this.mySize.height);
        final Graphics g = img.getGraphics();
        g.setFont(font);
        g.setColor(c);
        g.drawString(text, 0, fm.getHeight());
        final ImageWrapper wrapper = new ImageWrapper();
        wrapper.image = img;
        wrapper.width = width;
        wrapper.link = link;
        if (this.images.size() == 0) {
            wrapper.x = this.mySize.width;
        }
        else {
            final ImageWrapper wrapper2 = this.images.lastElement();
            wrapper.x = wrapper2.x + wrapper2.width;
        }
        wrapper.label = text;
        this.images.addElement(wrapper);
    }
    
    public synchronized void addDataItem(final String name, final String data, final Color[] c, final int clrindx, final boolean split) {
        final StringTokenizer st = new StringTokenizer(name, ":");
        final String symbol = st.nextToken().trim();
        String label = "";
        if (st.hasMoreTokens()) {
            label = st.nextToken().trim();
        }
        else {
            label = symbol;
        }
        final Font font = this.getFont();
        final FontMetrics fm = this.getFontMetrics(font);
        Font font2 = null;
        final int w1 = fm.stringWidth(label);
        int w2 = 0;
        if (split) {
            font2 = new Font(font.getName(), font.getStyle(), font.getSize() - 2);
            final FontMetrics fm2 = this.getFontMetrics(font2);
            w2 = fm2.stringWidth(data);
        }
        else {
            font2 = new Font(font.getName(), font.getStyle(), font.getSize() - 1);
            final FontMetrics fm2 = this.getFontMetrics(font2);
            w2 = fm.stringWidth(data + label);
        }
        int width = 0;
        if (w1 > w2) {
            width = w1;
        }
        else {
            width = w2;
        }
        try {
            final Image img = this.createImage(width + 10, this.mySize.height);
            final Graphics g = img.getGraphics();
            g.setFont(font);
            g.setColor(c[1]);
            final int y = fm.getHeight();
            g.drawString(label, 0, y);
            g.setColor(c[clrindx]);
            g.setFont(font2);
            if (split) {
                g.drawString(data, 0, this.getFontMetrics(font2).getHeight() + y);
            }
            else {
                g.drawString(data, w1 + 3, y);
            }
            final ImageWrapper wrapper = new ImageWrapper();
            wrapper.image = img;
            wrapper.width = width + 10;
            if (this.images.size() == 0) {
                wrapper.x = this.mySize.width;
            }
            else {
                final ImageWrapper wrapper2 = this.images.lastElement();
                wrapper.x = wrapper2.x + wrapper2.width;
            }
            wrapper.label = label;
            if (data.length() > 0) {
                wrapper.symbol = symbol;
            }
            this.images.addElement(wrapper);
            if (!this.isRunning) {
                this.isRunning = true;
                (this.myThread = new Thread(this, "ScrollThread#" + this.id)).start();
            }
        }
        catch (Exception e) {
            System.err.println("Ticker: caught " + e);
        }
    }
    
    public void clearAllItems() {
        final int scrollDelayX = this.scrollDelay;
        final int scrollDirectionX = this.scrollDirection;
        this.scrollDirection = 0;
        this.scrollDelay = 1000;
        synchronized (this.images) {
            for (int i = 0; i < this.images.size(); ++i) {
                final ImageWrapper wrapper = this.images.elementAt(i);
                if (wrapper.image != null) {
                    wrapper.image.flush();
                }
            }
            this.images.removeAllElements();
        }
        // monitorexit(this.images)
        final Graphics g = this.getGraphics();
        if (g != null) {
            g.setColor(this.getBackground());
            g.fillRect(0, 0, this.mySize.width, this.mySize.height);
        }
        this.scrollDirection = scrollDirectionX;
        this.scrollDelay = scrollDelayX;
    }
}
