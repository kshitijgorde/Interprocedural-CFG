import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.WindowListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class Geo extends Frame
{
    Line empty;
    int b1;
    int d;
    int MouseX;
    int MouseY;
    boolean part0;
    private Image dbImage;
    private Graphics dbg;
    boolean straightx;
    boolean straighty;
    boolean selectlock;
    boolean grid;
    boolean mouse;
    Line[] a;
    Connector[] b;
    int arraylength;
    boolean init;
    MyMouseMotionAdapter me;
    boolean begin;
    
    public Geo() {
        super("Angle Finder");
        this.empty = new Line(0, 0, 0, 0);
        this.part0 = false;
        this.straightx = false;
        this.straighty = false;
        this.selectlock = false;
        this.grid = true;
        this.mouse = true;
        this.a = new Line[100];
        this.b = new Connector[100];
        this.init = false;
        this.me = new MyMouseMotionAdapter(this);
        this.begin = true;
        this.setBackground(Color.white);
        this.a[0] = new Line(0, 0, 0, 0);
        this.addMouseListener(new MyMouseAdapter(this));
        this.addMouseMotionListener(new MyMouseMotionAdapter(this));
        this.addKeyListener(new MyKeyAdapter(this));
        this.addWindowListener(new MyWindowAdapter(this));
        final MenuBar menuBar = new MenuBar();
        this.setMenuBar(menuBar);
        final Menu menu = new Menu("File");
        final MenuItem menuItem;
        menu.add(menuItem = new MenuItem("Reset..."));
        final MenuItem menuItem2;
        menu.add(menuItem2 = new MenuItem("Controls..."));
        final MenuItem menuItem3;
        menu.add(menuItem3 = new MenuItem("-"));
        final MenuItem menuItem4;
        menu.add(menuItem4 = new MenuItem("Quit..."));
        menuBar.add(menu);
        final Menu menu2 = new Menu("About");
        final MenuItem menuItem5;
        menu2.add(menuItem5 = new MenuItem("Version Info"));
        final MenuItem menuItem6;
        menu2.add(menuItem6 = new MenuItem("-"));
        final MenuItem menuItem7;
        menu2.add(menuItem7 = new MenuItem("About..."));
        menuBar.add(menu2);
        final MyMenuHandler myMenuHandler = new MyMenuHandler(this);
        menuItem.addActionListener(myMenuHandler);
        menuItem2.addActionListener(myMenuHandler);
        menuItem3.addActionListener(myMenuHandler);
        menuItem4.addActionListener(myMenuHandler);
        menuItem5.addActionListener(myMenuHandler);
        menuItem6.addActionListener(myMenuHandler);
        menuItem7.addActionListener(myMenuHandler);
        this.setFont(new Font("Dialog", 1, 12));
        this.setSize(new Dimension(600, 450));
        this.setVisible(true);
        this.repaint();
        this.run();
    }
    
    public void start() {
        new WControlDialog(this, "Controls").setVisible(true);
    }
    
    public void close() {
        System.exit(0);
        this.setVisible(false);
    }
    
    public void update(final Graphics graphics) {
        if (this.dbImage == null) {
            this.dbImage = this.createImage(this.getSize().width, this.getSize().height);
            this.dbg = this.dbImage.getGraphics();
        }
        this.dbg.setColor(this.getBackground());
        this.dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.dbg.setColor(this.getForeground());
        this.grid(this.dbg);
        this.paint(this.dbg);
        graphics.drawImage(this.dbImage, 0, 0, this);
    }
    
    public void mouseMoved(final int mouseX, final int mouseY) {
        this.MouseX = mouseX;
        this.MouseY = mouseY;
        this.repaint();
    }
    
    public void run() {
        try {
            while (true) {
                if (this.begin) {
                    this.start();
                }
                if (this.begin) {
                    this.begin = false;
                }
                Thread.sleep(100L);
                this.repaint();
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void KeyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 37: {
                this.straightx = true;
                break;
            }
            case 38: {
                this.straighty = true;
                break;
            }
            case 39: {
                this.straightx = true;
                break;
            }
            case 40: {
                this.straighty = true;
                break;
            }
            case 83: {
                if (!this.selectlock) {
                    this.selectlock = true;
                    break;
                }
                this.selectlock = false;
                break;
            }
        }
        this.repaint();
    }
    
    public void KeyReleased() {
        this.straightx = false;
        this.straighty = false;
        this.repaint();
    }
    
    public void movetest() {
        int i = 0;
        final int n = 4;
        final Color color = new Color(0, 255, 0);
        new Color(0, 0, 255);
        new Color(255, 0, 0);
        new Color(255, 255, 255);
        final Color color2 = new Color(0, 0, 255);
        int n2 = 0;
        try {
            while (i < this.arraylength) {
                if (this.a[i].getX1() >= this.a[this.arraylength].getX1() - n && this.a[i].getX1() <= this.a[this.arraylength].getX1() + n && this.a[i].getY1() >= this.a[this.arraylength].getY1() - n && this.a[i].getY1() <= this.a[this.arraylength].getY1() + n) {
                    this.b[this.b1] = new Connector(this.a[i].getX2(), this.a[i].getY2(), this.a[i], this.a[this.arraylength]);
                    this.a[this.arraylength].setX1(this.a[i].getX1());
                    this.a[this.arraylength].setY1(this.a[i].getY1());
                }
                if (this.a[i].getX2() >= this.a[this.arraylength].getX1() - n && this.a[i].getX2() <= this.a[this.arraylength].getX1() + n && this.a[i].getY2() >= this.a[this.arraylength].getY1() - n && this.a[i].getY2() <= this.a[this.arraylength].getY1() + n) {
                    this.b[this.b1] = new Connector(this.a[i].getX2(), this.a[i].getY2(), this.a[i], this.a[this.arraylength]);
                    this.a[this.arraylength].setX1(this.a[i].getX2());
                    this.a[this.arraylength].setY1(this.a[i].getY2());
                }
                if (this.a[i].getX1() >= this.a[this.arraylength].getX2() - n && this.a[i].getX1() <= this.a[this.arraylength].getX2() + n && this.a[i].getY1() >= this.a[this.arraylength].getY2() - n && this.a[i].getY1() <= this.a[this.arraylength].getY2() + n) {
                    ++n2;
                    this.a[this.arraylength].setColor(color);
                }
                if (this.a[i].getX2() >= this.a[this.arraylength].getX2() - n && this.a[i].getX2() <= this.a[this.arraylength].getX2() + n && this.a[i].getY2() >= this.a[this.arraylength].getY2() - n && this.a[i].getY2() <= this.a[this.arraylength].getY2() + n) {
                    ++n2;
                    this.a[this.arraylength].setColor(color);
                }
                if (n2 < 1) {
                    this.a[this.arraylength].setColor(color2);
                }
                if (n2 >= 1) {
                    this.a[this.arraylength].setColor(color);
                }
                ++i;
            }
        }
        catch (NullPointerException ex) {}
    }
    
    public void contest() {
        int i = 0;
        final int n = 4;
        try {
            while (i < this.arraylength) {
                if (this.a[i].getX1() >= this.a[this.arraylength].getX1() - n && this.a[i].getX1() <= this.a[this.arraylength].getX1() + n && this.a[i].getY1() >= this.a[this.arraylength].getY1() - n && this.a[i].getY1() <= this.a[this.arraylength].getY1() + n) {
                    this.a[this.arraylength].setX1(this.a[i].getX1());
                    this.a[this.arraylength].setY1(this.a[i].getY1());
                    this.b[this.b1] = new Connector(this.a[i].getX1(), this.a[i].getY1(), this.a[i], this.a[this.arraylength]);
                    this.b[this.b1 + 1] = new Connector(0, 0, this.empty, this.empty);
                    ++this.b1;
                }
                if (this.a[i].getX2() >= this.a[this.arraylength].getX1() - n && this.a[i].getX2() <= this.a[this.arraylength].getX1() + n && this.a[i].getY2() >= this.a[this.arraylength].getY1() - n && this.a[i].getY2() <= this.a[this.arraylength].getY1() + n) {
                    this.a[this.arraylength].setX1(this.a[i].getX2());
                    this.a[this.arraylength].setY1(this.a[i].getY2());
                    this.b[this.b1] = new Connector(this.a[i].getX2(), this.a[i].getY2(), this.a[i], this.a[this.arraylength]);
                    this.b[this.b1 + 1] = new Connector(0, 0, this.empty, this.empty);
                    ++this.b1;
                }
                if (this.a[i].getX1() >= this.a[this.arraylength].getX2() - n && this.a[i].getX1() <= this.a[this.arraylength].getX2() + n && this.a[i].getY1() >= this.a[this.arraylength].getY2() - n && this.a[i].getY1() <= this.a[this.arraylength].getY2() + n) {
                    this.a[this.arraylength].setX2(this.a[i].getX1());
                    this.a[this.arraylength].setY2(this.a[i].getY1());
                    this.b[this.b1] = new Connector(this.a[i].getX1(), this.a[i].getY1(), this.a[i], this.a[this.arraylength]);
                    this.b[this.b1 + 1] = new Connector(0, 0, this.empty, this.empty);
                    ++this.b1;
                }
                if (this.a[i].getX2() >= this.a[this.arraylength].getX2() - n && this.a[i].getX2() <= this.a[this.arraylength].getX2() + n && this.a[i].getY2() >= this.a[this.arraylength].getY2() - n && this.a[i].getY2() <= this.a[this.arraylength].getY2() + n) {
                    this.a[this.arraylength].setX2(this.a[i].getX2());
                    this.a[this.arraylength].setY2(this.a[i].getY2());
                    this.b[this.b1] = new Connector(this.a[i].getX2(), this.a[i].getY2(), this.a[i], this.a[this.arraylength]);
                    this.b[this.b1 + 1] = new Connector(0, 0, this.empty, this.empty);
                    ++this.b1;
                }
                ++i;
            }
            this.repaint();
        }
        catch (NullPointerException ex) {}
    }
    
    public void mouseClicked(final int n, final int n2) {
        if (this.selectlock) {
            int i = 0;
            final Line line = new Line(n, n2, this.a[i].getX1(), this.a[i].getY1());
            while (i <= this.arraylength) {
                final Line line2 = new Line(n, n2, this.a[i].getX1(), this.a[i].getY1());
                if (this.a[i].getSlope() / line2.getSlope() <= 1.1 && this.a[i].getSlope() / line2.getSlope() >= 0.8) {
                    new SelectedLine(this, "Line Properties", this.a[i], i).setVisible(true);
                    this.KeyReleased();
                    this.KeyReleased();
                    this.repaint();
                }
                System.out.println("Hello: " + (this.a[i].getSlope() > 0.0 && this.a[i].getSlope() < 0.0));
                if (this.a[i].getSlope() > 0.0 && this.a[i].getSlope() < 0.0 && this.a[i].getSlope() == 0.0) {
                    if (Math.abs(n - this.a[i].getX1()) < 5) {
                        new SelectedLine(this, "Line Properties", this.a[i], i);
                    }
                    else if (Math.abs(n2 - this.a[i].getY1()) < 5) {
                        new SelectedLine(this, "Line Properties", this.a[i], i);
                    }
                }
                ++i;
            }
        }
    }
    
    public void grid(final Graphics graphics) {
        graphics.setColor(new Color(192, 192, 192));
        graphics.drawLine(50, 0, 50, 400);
        graphics.drawLine(100, 0, 100, 400);
        graphics.drawLine(150, 0, 150, 400);
        graphics.drawLine(200, 0, 200, 400);
        graphics.drawLine(250, 0, 250, 400);
        graphics.drawLine(300, 0, 300, 400);
        graphics.drawLine(350, 0, 350, 400);
        graphics.drawLine(400, 0, 400, 400);
        graphics.drawLine(450, 0, 450, 400);
        graphics.drawLine(500, 0, 500, 400);
        graphics.drawLine(550, 0, 550, 400);
        graphics.drawLine(600, 0, 600, 400);
        graphics.drawLine(0, 50, 600, 50);
        graphics.drawLine(0, 100, 600, 100);
        graphics.drawLine(0, 150, 600, 150);
        graphics.drawLine(0, 200, 600, 200);
        graphics.drawLine(0, 250, 600, 250);
        graphics.drawLine(0, 300, 600, 300);
        graphics.drawLine(0, 350, 600, 350);
        graphics.drawLine(0, 400, 600, 400);
    }
    
    public void paint(final Graphics graphics) {
        try {
            int i = 0;
            while (i <= this.arraylength) {
                graphics.setColor(this.a[i].getColor());
                graphics.drawLine(this.a[i].getX1(), this.a[i].getY1(), this.a[i].getX2(), this.a[i].getY2());
                ++i;
                graphics.setFont(new Font("Dialog", 1, 10));
                graphics.drawString("(" + this.MouseX + ", " + -1 * (this.MouseY - 400) + ")", this.MouseX + 5, this.MouseY - 5);
                graphics.setColor(new Color(238, 239, 221));
                graphics.fillRect(0, 401, 600, 24);
                graphics.setColor(Color.black);
                graphics.setFont(new Font("Dialog", 1, 12));
                graphics.drawString("Length: ", 20, 413);
                graphics.drawString("Slope: ", 210, 413);
                graphics.setColor(Color.red);
                if (this.straightx && !this.straighty) {
                    graphics.drawString("X-Lock", 550, 413);
                }
                if (this.straighty && !this.straightx) {
                    graphics.drawString("Y-Lock", 550, 413);
                }
                graphics.setColor(Color.blue);
                if (this.selectlock) {
                    graphics.drawString("Select Lock", 470, 413);
                }
            }
            int j = 0;
            graphics.setColor(Color.black);
            graphics.setFont(new Font("Dialog", 0, 12));
            graphics.setColor(Color.blue);
            if (this.a[this.arraylength].getY1() != 0) {
                graphics.drawString(String.valueOf(this.a[this.arraylength].getSlope() * -1.0), 260, 413);
                graphics.drawString(String.valueOf(this.a[this.arraylength].getLength() / 10.0), 70, 413);
            }
            else if (this.arraylength >= 1) {
                graphics.drawString(String.valueOf(this.a[this.arraylength - 1].getSlope() * -1.0), 260, 413);
                graphics.drawString(String.valueOf(this.a[this.arraylength - 1].getLength() / 10.0), 70, 413);
            }
            ++this.d;
            while (j <= this.b1) {
                graphics.setColor(new Color(0, 255, 0));
                graphics.fillOval(this.b[j].getX() - 4, this.b[j].getY() - 4, 8, 8);
                graphics.setColor(Color.black);
                graphics.setFont(new Font("Dialog", 0, 10));
                if ((int)this.b[j].getAngle() / 10.0 == 0.0) {
                    graphics.drawString("NaN", this.b[j].getAX(), this.b[j].getAY());
                }
                else {
                    graphics.drawString(String.valueOf((int)this.b[j].getAngle() / 10.0), this.b[j].getAX(), this.b[j].getAY());
                }
                ++j;
            }
        }
        catch (NullPointerException ex) {}
    }
    
    public void mouseDragged(final int n, final int n2) {
        if (!this.selectlock) {
            if (!this.straightx && !this.straighty) {
                this.a[this.arraylength].setX2(n);
                this.a[this.arraylength].setY2(n2);
            }
            if (this.straighty) {
                this.a[this.arraylength].setY2(n2);
            }
            if (this.straightx) {
                this.a[this.arraylength].setX2(n);
            }
            this.movetest();
            this.repaint();
        }
    }
    
    public void mousePressed(final int n, final int n2) {
        if (!this.selectlock) {
            this.a[this.arraylength] = new Line(n, n2, n, n2);
            this.a[this.arraylength + 1] = new Line(0, 0, 0, 0);
        }
    }
    
    public void mouseReleased() {
        if (!this.selectlock) {
            this.contest();
            ++this.arraylength;
        }
    }
    
    public static void main(final String[] array) throws Exception {
        new Geo();
    }
}
