import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.awt.Cursor;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SharkTicker extends Applet implements Runnable, MouseListener, MouseMotionListener
{
    protected Thread scroller;
    protected SharkReader reader;
    protected TickerInfo info;
    protected int delay;
    protected int scroll;
    protected long refresh;
    protected boolean mouse;
    private int xoffset;
    private int yoffset;
    private boolean forward;
    private boolean suspended;
    private int x_MouseDown;
    private int y_MouseDown;
    private int x_Mouse;
    private int y_Mouse;
    protected int space;
    private boolean horizontal;
    protected int waitingPos;
    protected int waitingTime;
    private boolean interrupt;
    
    public SharkTicker() {
        this.scroller = null;
        this.reader = null;
        this.info = null;
        this.delay = 50;
        this.scroll = 1;
        this.refresh = 0L;
        this.mouse = false;
        this.xoffset = 0;
        this.yoffset = 0;
        this.forward = true;
        this.suspended = false;
        this.x_Mouse = -1;
        this.y_Mouse = -1;
        this.space = 30;
        this.horizontal = true;
        this.waitingPos = 0;
        this.waitingTime = 0;
        this.interrupt = false;
    }
    
    @Override
    public String getAppletInfo() {
        return "Shark Ticker for JDK 1.1 - $Revision: 1.10 $ - (C) 2002 Rolotec AG, <http://www.rolotec.ch>";
    }
    
    @Override
    public String[][] getParameterInfo() {
        return new String[][] { { "Data", "URL", "URL to retrieve ticker info" }, { "Text", "String", "fix Text to display" }, { "Refresh", "Number", "number of seconds between data refreshes" }, { "Delay", "Number", "scrolling delay" }, { "Scroll", "Number", "number of scrolled pixels" }, { "Mouse", "Boolean", "wether mouse events are processed" }, { "Font", "String", "default font name" }, { "Size", "Number", "default font size" }, { "Smallsize", "Number", "default small font size" }, { "Tinysize", "Number", "default tiny font size" }, { "Color", "RRGGBB", "default text color" }, { "Color1", "RRGGBB", "default text1 color" }, { "Space", "Number", "default space between the entries" }, { "Margin", "Number", "default margin between the entries and top/left edge" }, { "BGColor", "RRGGBB", "background color" }, { "BGImage", "URL", "background image" }, { "Direction", "String", "horizontal or vertical" }, { "waitingPos", "Number", "position to interrupt scrolling" }, { "waitingTime", "Number", "time to interrup scrolling" } };
    }
    
    @Override
    public void init() {
        System.out.println(this.getAppletInfo());
        System.out.println("DocumentBase=" + this.getDocumentBase());
        this.info = new TickerInfo(this);
        try {
            this.delay = Integer.parseInt(this.getParameter("Delay"));
        }
        catch (NumberFormatException ex) {}
        System.out.println("Delay=" + this.delay);
        try {
            this.scroll = Integer.parseInt(this.getParameter("Scroll"));
        }
        catch (NumberFormatException ex2) {}
        System.out.println("Scroll=" + this.scroll);
        try {
            this.refresh = Long.parseLong(this.getParameter("Refresh"));
            this.refresh *= 1000L;
        }
        catch (NumberFormatException ex3) {}
        System.out.println("Refresh=" + this.refresh);
        try {
            this.space = Integer.parseInt(this.getParameter("Space"));
        }
        catch (NumberFormatException ex4) {}
        System.out.println("Space=" + this.space);
        try {
            this.horizontal = !this.getParameter("Direction").equals("vertical");
        }
        catch (Exception ex5) {}
        System.out.println("Horizontal=" + this.horizontal);
        this.info.horizontal = this.horizontal;
        try {
            this.waitingPos = Integer.parseInt(this.getParameter("waitingPos"));
        }
        catch (NumberFormatException ex6) {}
        System.out.println("waitingPos=" + this.waitingPos);
        try {
            this.waitingTime = Integer.parseInt(this.getParameter("waitingTime"));
        }
        catch (NumberFormatException ex7) {}
        System.out.println("waitingTime=" + this.waitingTime);
        try {
            this.mouse = Boolean.valueOf(this.getParameter("Mouse"));
        }
        catch (Exception ex8) {}
        System.out.println("Mouse=" + (this.mouse ? "enabled" : "disabled"));
        if (this.mouse) {
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }
    }
    
    @Override
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    @Override
    public void paint(final Graphics graphics) {
        boolean b = this.info.data == null;
        if (this.suspended) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
        else if (this.forward) {
            if (this.horizontal) {
                this.xoffset -= this.scroll;
                if (this.xoffset < -this.info.appWidth) {
                    this.xoffset = this.getSize().width;
                    b = true;
                    this.resetInterrupted();
                }
            }
            else {
                this.yoffset -= this.scroll;
                if (this.yoffset < -this.info.appHeight) {
                    this.yoffset = this.getSize().height;
                    b = true;
                    this.resetInterrupted();
                }
            }
        }
        else if (this.horizontal) {
            this.xoffset += this.scroll;
            if (this.xoffset > this.getSize().width) {
                this.xoffset = -this.info.appWidth;
                b = true;
                this.resetInterrupted();
            }
        }
        else {
            this.yoffset += this.scroll;
            if (this.yoffset > this.getSize().height) {
                this.yoffset = -this.info.appHeight;
                b = true;
                this.resetInterrupted();
            }
        }
        if (b && this.info.newdata != null) {
            this.info.data = this.info.newdata;
            this.info.newdata = null;
            this.info.resize();
        }
        if (this.info.data != null) {
            this.info.redraw(this.horizontal, this.xoffset, this.getSize().width, this.yoffset, this.getSize().height);
            graphics.drawImage(this.info.appImage, 0, 0, this);
        }
    }
    
    @Override
    public void start() {
        if (this.reader == null) {
            (this.reader = new SharkReader(this.info, this.refresh)).start();
        }
        if (this.scroller == null) {
            (this.scroller = new Thread(this)).start();
        }
    }
    
    @Override
    public void stop() {
        if (this.reader != null) {
            this.reader.stop();
        }
        this.reader = null;
        if (this.scroller != null) {
            this.scroller.stop();
        }
        this.scroller = null;
    }
    
    @Override
    public void run() {
        System.out.println("SharkTicker.run()");
        this.xoffset = 0;
        this.suspended = false;
        while (this.scroller != null) {
            this.checkPosition();
            this.repaint();
            try {
                System.out.flush();
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
            if (this.interrupt) {
                try {
                    Thread.sleep(this.waitingTime);
                }
                catch (InterruptedException ex2) {}
                this.interrupt = false;
            }
        }
    }
    
    public void freeze() {
        if (!this.suspended) {
            this.suspended = true;
            System.out.println("suspended...");
        }
    }
    
    public void thaw() {
        if (this.suspended) {
            this.suspended = false;
            System.out.println("...resumed.");
        }
    }
    
    private void checkPosition() {
        if (this.info.data == null) {
            return;
        }
        try {
            boolean b = false;
            final Enumeration<TickerData> elements = this.info.data.elements();
            while (elements.hasMoreElements()) {
                final TickerData tickerData = elements.nextElement();
                if (tickerData.width <= 0) {
                    continue;
                }
                final int currentPositionX = tickerData.currentPositionX;
                final int n = currentPositionX + tickerData.width - this.space;
                final int currentPositionY = tickerData.currentPositionY;
                final int n2 = currentPositionY + tickerData.height - this.space;
                if (currentPositionX < 0 || currentPositionY < 0) {
                    continue;
                }
                if (this.horizontal) {
                    if (currentPositionX < this.waitingPos && n > this.waitingPos && !tickerData.interrupted && currentPositionX > 0) {
                        this.interrupt = true;
                        tickerData.interrupted = true;
                    }
                    if (n < this.waitingPos && tickerData.interrupted) {
                        tickerData.interrupted = false;
                    }
                }
                else {
                    if (currentPositionY < this.waitingPos && n2 > this.waitingPos && !tickerData.interrupted) {
                        this.interrupt = true;
                        tickerData.interrupted = true;
                    }
                    if (n2 < this.waitingPos && tickerData.interrupted) {
                        tickerData.interrupted = false;
                    }
                }
                if (currentPositionY > this.y_Mouse || n2 < this.y_Mouse || currentPositionX > this.x_Mouse || n < this.x_Mouse || tickerData.link.equals("no link?")) {
                    continue;
                }
                b = true;
                this.info.status(new URL(this.getDocumentBase(), tickerData.link).toString());
            }
            if (b) {
                this.setCursor(new Cursor(12));
            }
            else {
                this.setCursor(new Cursor(0));
                this.info.status("");
            }
        }
        catch (Exception ex) {
            System.out.println("SharkTicker.checkPosition(), Error: " + ex);
        }
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.x_Mouse = mouseEvent.getX();
        this.y_Mouse = mouseEvent.getY();
        final Enumeration<TickerData> elements = this.info.data.elements();
        while (elements.hasMoreElements()) {
            final TickerData tickerData = elements.nextElement();
            final int currentPositionX = tickerData.currentPositionX;
            final int n = currentPositionX + tickerData.width - this.space;
            final int currentPositionY = tickerData.currentPositionY;
            final int n2 = currentPositionY + tickerData.height - this.space;
            if (currentPositionX <= this.x_Mouse && n >= this.x_Mouse && currentPositionY <= this.y_Mouse && n2 >= this.y_Mouse && !tickerData.link.equals("no link?")) {
                try {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), tickerData.link), tickerData.target);
                }
                catch (Exception ex) {
                    System.out.println("SharkTicker.mouseClicked: " + ex);
                }
                break;
            }
        }
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.x_Mouse = mouseEvent.getX();
        this.y_Mouse = mouseEvent.getY();
        this.checkPosition();
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        this.x_Mouse = -1;
        this.y_Mouse = -1;
        this.checkPosition();
    }
    
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.x_Mouse = mouseEvent.getX();
        this.y_Mouse = mouseEvent.getY();
        this.checkPosition();
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        this.x_MouseDown = mouseEvent.getX();
        this.y_MouseDown = mouseEvent.getY();
        this.freeze();
    }
    
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int abs = Math.abs(this.x_MouseDown - x);
        final int abs2 = Math.abs(this.y_MouseDown - y);
        if (this.horizontal) {
            if (x > this.x_MouseDown) {
                this.xoffset += abs;
                this.setWaitingPos(false);
            }
            else if (x < this.x_MouseDown) {
                this.xoffset -= abs;
                this.setWaitingPos(true);
            }
        }
        else if (y > this.y_MouseDown) {
            this.yoffset += abs2;
            this.setWaitingPos(false);
        }
        else if (y < this.y_MouseDown) {
            this.yoffset -= abs2;
            this.setWaitingPos(true);
        }
        this.x_MouseDown = x;
        this.y_MouseDown = y;
        this.repaint();
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.thaw();
    }
    
    private void setWaitingPos(final boolean b) {
        if (b && !this.forward) {
            if (!this.horizontal) {
                try {
                    this.waitingPos = Integer.parseInt(this.getParameter("waitingPos"));
                }
                catch (NumberFormatException ex) {
                    System.out.println("SharkTicker.setWaitingPos(), ignore exception " + ex);
                }
            }
            this.forward = true;
            this.resetInterrupted();
        }
        else if (!b && this.forward) {
            if (!this.horizontal) {
                this.waitingPos += this.info.dataHeight - this.space;
            }
            this.forward = false;
            this.resetInterrupted();
        }
    }
    
    private void resetInterrupted() {
        try {
            final Enumeration<TickerData> elements = this.info.data.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().interrupted = false;
            }
        }
        catch (Exception ex) {}
    }
}
