import java.awt.Point;
import java.util.Stack;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import java.awt.image.PixelGrabber;
import java.util.Enumeration;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class cDraw extends Panel implements MouseMotionListener, MouseListener, cToolsListener
{
    cTools zz;
    Dimension size;
    int[] pixels;
    int x1;
    int x2;
    int y1;
    int y2;
    int X;
    int iW;
    int iH;
    public Vector v;
    String[] ImgName;
    int old_x;
    int old_y;
    int new_x;
    int new_y;
    private boolean _$11796;
    private boolean _$11797;
    private boolean _$11798;
    private boolean _$11800;
    private boolean _$11801;
    private boolean _$11802;
    String Text;
    protected Image[] img;
    private Image _$11804;
    private Graphics _$11805;
    private Graphics _$11806;
    boolean bButtonRight;
    int iColor;
    int fgColor;
    int bgColor;
    int iAction;
    int iSize;
    private int _$11814;
    int iNB;
    String sNB;
    
    public cDraw(final cTools zz) {
        this.zz = null;
        this.size = new Dimension(0, 0);
        this.v = new Vector(15, 10);
        this.ImgName = new String[12];
        this._$11796 = true;
        this._$11797 = false;
        this._$11798 = true;
        this._$11800 = false;
        this._$11801 = false;
        this._$11802 = false;
        this.Text = "";
        this.img = new Image[11];
        this.bButtonRight = false;
        this.iColor = 0;
        this.fgColor = 0;
        this.bgColor = -1;
        this.iAction = 21;
        this.iSize = 1;
        this._$11814 = 0;
        this.iNB = 0;
        this.sNB = "";
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        for (int i = 0; i < 11; ++i) {
            this.img[i] = null;
        }
        this.zz = zz;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void init() {
        this.size = this.getSize();
        this.iW = this.size.width;
        this.iH = this.size.height;
        this._$11804 = this.createImage(this.size.width * 2, this.size.height);
        this._$11805 = this._$11804.getGraphics();
        this.pixels = new int[this.size.width * this.size.height];
        this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("32 ; ").append(this.bgColor).append(" ;"))));
        this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("6 ; 0 ; 0 ; ").append(this.size.width).append(" ; ").append(this.size.height).append(" ;"))));
        this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("32 ; ").append(this.fgColor).append(" ;"))));
    }
    
    public void triangle(final int n, final int n2, final int n3, final int n4) {
        this._$11805.fillPolygon(new int[] { n, n + n3 / 2, n + n3 }, new int[] { n2 + n4, n2, n2 + n4 }, 3);
    }
    
    public void setBkImage(final Image image, final String s, final int n) {
        if (n < 11) {
            this.img[n] = image;
            this.ImgName[n] = s;
        }
    }
    
    public void paintCanvas() {
        if (this._$11800) {
            this._$11805.copyArea(this.size.width, 0, this.size.width, this.size.height, -this.size.width, 0);
            this._$11805.fillRect(0, 0, this.size.width * 2, this.size.height);
            this.doRedraw(this._$11805);
            this._$11800 = false;
            this._$11805.copyArea(0, 0, this.size.width, this.size.height, this.size.width, 0);
            this._$11796 = true;
            return;
        }
        if (!this._$11797 && this._$11796) {
            this._$11805.copyArea(this.size.width, 0, this.size.width, this.size.height, -this.size.width, 0);
        }
        if (this._$11798) {
            this._$11805.setColor(new Color(this.bgColor));
            this._$11805.fillRect(0, 0, this.size.width * 2, this.size.height);
            if (this.img[0] != null) {
                this._$11805.drawImage(this.img[0], 0, 0, this);
                this._$11805.drawImage(this.img[0], this.size.width, 0, this);
                this.v.addElement(" 22 ; 0 ; 0 ; 0 ;");
            }
            this._$11798 = false;
        }
        int iColor;
        if (this.bButtonRight || this.iAction == 25) {
            iColor = this.bgColor;
        }
        else {
            iColor = this.fgColor;
        }
        this._$11805.setColor(new Color(iColor));
        if (this.iColor != iColor) {
            this.iColor = iColor;
            this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("32 ; ").append(this.iColor).append(" ;"))));
        }
        if (this._$11802) {
            switch (this.iAction) {
                case 1: {
                    this._$11797 = false;
                    drawRect(this._$11805, this.iSize, this.x1, this.y1, this.x2, this.y2, true);
                    break;
                }
                case 6: {
                    this._$11797 = false;
                    this._$11805.fillRect(this.x1, this.y1, this.x2 - this.x1, this.y2 - this.y1);
                    this._$11805.fillRect(this.x2, this.y2, this.x1 - this.x2, this.y1 - this.y2);
                    this._$11805.fillRect(this.x2, this.y1, this.x1 - this.x2, this.y2 - this.y1);
                    this._$11805.fillRect(this.x1, this.y2, this.x2 - this.x1, this.y1 - this.y2);
                    break;
                }
                case 4: {
                    this._$11797 = false;
                    drawOval(this._$11805, this.iSize, this.x1, this.y1, this.x2, this.y2, true);
                    break;
                }
                case 9: {
                    this._$11797 = false;
                    this._$11805.fillOval(this.x1, this.y1, this.x2 - this.x1, this.y2 - this.y1);
                    this._$11805.fillOval(this.x2, this.y2, this.x1 - this.x2, this.y1 - this.y2);
                    this._$11805.fillOval(this.x2, this.y1, this.x1 - this.x2, this.y2 - this.y1);
                    this._$11805.fillOval(this.x1, this.y2, this.x2 - this.x1, this.y1 - this.y2);
                    break;
                }
                case 21: {
                    this._$11797 = false;
                    this._$8350(this.iSize, this.x1, this.y1, this.x2, this.y2, this._$11814);
                    break;
                }
                case 24:
                case 25: {
                    this._$11797 = true;
                    if (this.iAction == 25) {
                        this._$8350(3, this.old_x, this.old_y, this.new_x, this.new_y);
                    }
                    else {
                        this._$8350(this.iSize, this.old_x, this.old_y, this.new_x, this.new_y);
                    }
                    ++this.iNB;
                    this.sNB = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.sNB))).append(this.new_x).append(";").append(this.new_y).append(";")));
                    if (this.iNB > 30) {
                        if (this.iAction == 25) {
                            this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("24 ; 3 ; ").append(this.sNB))));
                        }
                        else {
                            this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("24 ; ").append(this.iSize).append(" ; ").append(this.sNB))));
                        }
                        this.iNB = 1;
                        this.sNB = String.valueOf(String.valueOf(new StringBuffer("").append(this.new_x).append(";").append(this.new_y).append(";")));
                    }
                    this.old_x = this.new_x;
                    this.old_y = this.new_y;
                    break;
                }
                case 11: {
                    this._$11797 = false;
                    this.fillZone(this.x2, this.y2, this.fgColor);
                    break;
                }
                case 20: {
                    this._$11805.setClip(0, 0, this.size.width, this.size.height);
                    this._$11797 = false;
                    this.Text = this._$11832();
                    this._$11805.drawString(this.Text, this.x2, this.y2);
                    this._$11805.setClip(0, 0, this.size.width * 2, this.size.height);
                    break;
                }
                case 950:
                case 951:
                case 952:
                case 953:
                case 954:
                case 955:
                case 956:
                case 957:
                case 958:
                case 959: {
                    final int n = this.iAction - 950 + 1;
                    this._$11797 = false;
                    if (this.img[n] != null) {
                        this._$11805.setClip(0, 0, this.size.width, this.size.height);
                        this._$11805.drawImage(this.img[n], this.x2, this.y2, this);
                        this._$11805.setClip(0, 0, this.size.width * 2, this.size.height);
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    private void _$8350(final int n, final int n2, final int n3, final int n4, final int n5) {
        this._$8350(n, n2, n3, n4, n5, 0);
    }
    
    private void _$8350(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        drawLine(this._$11805, n, n2, n3, n4, n5, n6);
    }
    
    static void drawLine(final Graphics graphics, final int n, int n2, int n3, int n4, int n5, final int n6) {
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        if (n6 > 0) {
            final int[] array = { n2, 0, 0 };
            final int[] array2 = { n3, 0, 0 };
            final int n11 = n2 - n4;
            final int n12 = n3 - n5;
            n7 = n4 - n2 - n12 / 2;
            n9 = n5 - n3 + n11 / 2;
            final int n13 = 8 + n * n;
            double sqrt = Math.sqrt((n7 * n7 + n9 * n9) / (n13 * n13));
            if (sqrt == 0) {
                sqrt = 1.0;
            }
            if (n6 == 1 || n6 == 3) {
                array[1] = n2 + (int)((n4 - n2 - n12 / 2) / sqrt);
                array2[1] = n3 + (int)((n5 - n3 + n11 / 2) / sqrt);
                array[2] = n2 + (int)((n4 - n2 + n12 / 2) / sqrt);
                array2[2] = n3 + (int)((n5 - n3 - n11 / 2) / sqrt);
                graphics.fillPolygon(array, array2, 3);
                n7 = array[2] - (array[2] - array[1]) / 2;
                n9 = array2[2] - (array2[2] - array2[1]) / 2;
                if (n6 == 1) {
                    n8 = n4;
                    n10 = n5;
                }
            }
            if (n6 == 2 || n6 == 3) {
                array[0] = n4;
                array2[0] = n5;
                array[1] = n4 + (int)((n2 - n4 - n12 / 2) / sqrt);
                array2[1] = n5 + (int)((n3 - n5 + n11 / 2) / sqrt);
                array[2] = n4 + (int)((n2 - n4 + n12 / 2) / sqrt);
                array2[2] = n5 + (int)((n3 - n5 - n11 / 2) / sqrt);
                graphics.fillPolygon(array, array2, 3);
                if (n6 == 2) {
                    n7 = n2;
                    n9 = n3;
                }
                n8 = array[2] - (array[2] - array[1]) / 2;
                n10 = array2[2] - (array2[2] - array2[1]) / 2;
            }
        }
        if (n6 != 0) {
            n2 = n7;
            n4 = n8;
            n3 = n9;
            n5 = n10;
        }
        graphics.drawLine(n2, n3, n4, n5);
        for (int i = 1; i < n; ++i) {
            graphics.drawLine(n2 - i, n3, n4 - i, n5);
            graphics.drawLine(n2 + i, n3, n4 + i, n5);
            graphics.drawLine(n2, n3 - i, n4, n5 - i);
            graphics.drawLine(n2, n3 + i, n4, n5 + i);
        }
    }
    
    static void drawRect(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        int n6 = n2;
        int n7 = n4;
        int n8 = n3;
        int n9 = n5;
        if (b) {
            n7 = n4 - n2;
            n9 = n5 - n3;
            if (n4 <= n2) {
                n7 = n2 - n4;
                n6 = n4;
            }
            if (n5 <= n3) {
                n9 = n3 - n5;
                n8 = n5;
            }
        }
        for (int i = 0; i < n; ++i) {
            graphics.drawRect(n6 + i, n8 + i, n7 - i * 2, n9 - i * 2);
        }
    }
    
    static void drawOval(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        int n6 = n2;
        int n7 = n4;
        int n8 = n3;
        int n9 = n5;
        if (b) {
            n7 = n4 - n2;
            n9 = n5 - n3;
            if (n4 <= n2) {
                n7 = n2 - n4;
                n6 = n4;
            }
            if (n5 <= n3) {
                n9 = n3 - n5;
                n8 = n5;
            }
        }
        for (int i = 0; i < n; ++i) {
            graphics.drawOval(n6 + i, n8 + i, n7 - i * 2, n9 - i * 2);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.x1 = mouseEvent.getX();
        this.y1 = mouseEvent.getY();
        this.bButtonRight = (mouseEvent.getModifiers() == 4 || mouseEvent.getModifiers() == 1);
        this.x2 = this.x1 + 1;
        this.y2 = this.y1 + 1;
        this.iNB = 1;
        this.sNB = String.valueOf(String.valueOf(new StringBuffer("").append(this.x1).append(";").append(this.y1).append(";")));
        final int x1 = this.x1;
        this.new_x = x1;
        this.old_x = x1;
        final int y1 = this.y1;
        this.new_y = y1;
        this.old_y = y1;
        this._$11802 = true;
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        mouseEvent.getX();
        mouseEvent.getY();
        if (this._$11801) {
            if (this.iAction != 24) {
                if (this.iAction >= 950 && this.iAction < 960) {
                    this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("22 ; ").append(this.iAction + 1 - 950).append(" ; ").append(this.x2).append(" ; ").append(this.y2).append(" ;"))));
                }
                else if (this.iAction == 20) {
                    this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("").append(this.iAction).append(" ; ").append(this._$11832()).append(" ; ").append(this.x2).append(" ; ").append(this.y2).append(" ;"))));
                }
                else if (this.iAction == 20) {
                    this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("").append(this.iAction).append(" ; ").append(this.x1).append(" ; ").append(this.y1).append(" ; ").append(this.x2).append(" ; ").append(this.y2).append(" ;"))));
                }
                else if (this.iAction == 21) {
                    this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("").append(this.iAction).append(" ; ").append(this.iSize).append(" ; ").append(this.x1).append(" ; ").append(this.y1).append(" ; ").append(this.x2).append(" ; ").append(this.y2).append(" ;").append(this._$11814).append(" ;"))));
                }
                else if (this.iAction == 25) {
                    this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("24 ; 3 ; ").append(this.sNB))));
                }
                else if (this.iAction == 4 || this.iAction == 9 || this.iAction == 1 || this.iAction == 6) {
                    int n = this.x1;
                    int n2 = this.x2 - this.x1;
                    int n3 = this.y1;
                    int n4 = this.y2 - this.y1;
                    if (this.x2 <= this.x1) {
                        n2 = this.x1 - this.x2;
                        n = this.x2;
                    }
                    if (this.y2 <= this.y1) {
                        n4 = this.y1 - this.y2;
                        n3 = this.y2;
                    }
                    if (this.iAction == 4 || this.iAction == 1) {
                        this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("").append(this.iAction).append(" ; ").append(this.iSize).append(" ; ").append(n).append(" ; ").append(n3).append(" ; ").append(n2).append(" ; ").append(n4).append(" ;"))));
                    }
                    else {
                        this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("").append(this.iAction).append(" ; ").append(n).append(" ; ").append(n3).append(" ; ").append(n2).append(" ; ").append(n4).append(" ;"))));
                    }
                }
                else {
                    this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("").append(this.iAction).append(" ; ").append(this.x1).append(" ; ").append(this.y1).append(" ; ").append(this.x2).append(" ; ").append(this.y2).append(" ;"))));
                }
            }
            else {
                this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("24 ; ").append(this.iSize).append(" ; ").append(this.sNB))));
            }
            this._$11805.copyArea(0, 0, this.size.width, this.size.height, this.size.width, 0);
            this._$11796 = true;
        }
        this._$11798 = false;
        this._$11802 = false;
        this.repaint();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this._$11801 && this._$11802) {
            if (x > 0 && x < this.iW) {
                this.x2 = x;
            }
            if (y > 0 && y < this.iH) {
                this.y2 = y;
            }
            this.new_x = x;
            this.new_y = y;
            this.repaint();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this._$11801 = true;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this._$11801 = false;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (this._$11804 == null) {
            this.init();
        }
        this.paintCanvas();
        graphics.drawImage(this._$11804, 0, 0, this);
    }
    
    private String _$11832() {
        if (this.zz != null) {
            return this.zz.getText();
        }
        return "";
    }
    
    public void onErase() {
        this.getData();
        this.v.removeAllElements();
        this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("32 ; ").append(this.bgColor).append(" ;"))));
        this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("6 ; 0 ; 0 ; ").append(this.size.width).append(" ; ").append(this.size.height).append(" ;"))));
        this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("32 ; ").append(this.fgColor).append(" ;"))));
        this._$11798 = true;
        this.repaint();
    }
    
    public void onUndo() {
        this._$11800 = true;
        this.repaint();
    }
    
    public void onColor(final int fgColor, final int bgColor) {
        this.fgColor = fgColor;
        this.bgColor = bgColor;
    }
    
    public void onAction(final int iAction) {
        this.iAction = iAction;
    }
    
    public void onSize(final int iSize) {
        this.iSize = iSize;
    }
    
    public void onArrow(final int $11814) {
        this._$11814 = $11814;
    }
    
    public void onFont(final Font font) {
        if (font != null) {
            this._$11805.setFont(font);
            this.v.addElement(String.valueOf(String.valueOf(new StringBuffer("31 ; ").append(font.getName()).append(" ; ").append(font.getStyle()).append(" ; ").append(font.getSize()).append(" ;"))));
        }
    }
    
    public String getData() {
        String value = "";
        final Enumeration<Object> elements = this.v.elements();
        while (elements.hasMoreElements()) {
            value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value))).append(elements.nextElement().toString()).append("\n")));
        }
        return value;
    }
    
    public void doRedraw(final Graphics graphics) {
        final Enumeration<Object> elements = this.v.elements();
        this.v.removeElementAt(this.v.size() - 1);
        while (elements.hasMoreElements()) {
            new z_data(elements.nextElement().toString()).paint(this, graphics);
        }
    }
    
    protected void fillZone(final int n, final int n2) {
        this.fillZone(n, n2, this.fgColor);
    }
    
    protected void fillZone(final int n, final int n2, int n3) {
        final PixelGrabber pixelGrabber = new PixelGrabber(this._$11804, 0, 0, this.size.width, this.size.height, this.pixels, 0, this.size.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.out.println("--> erreur fillZone 1 : ".concat(String.valueOf(String.valueOf(ex))));
        }
        if (n3 > 0) {
            n3 -= 16777216;
        }
        final int n4 = this.pixels[n2 * this.size.width + n];
        if (n3 != n4) {
            this._$11863(n, n2, n3, n4);
            try {
                this._$11805.drawImage(this.createImage(new MemoryImageSource(this.size.width, this.size.height, new DirectColorModel(24, 16711680, 65280, 255), this.pixels, 0, this.size.width)), 0, 0, this);
            }
            catch (Exception ex2) {
                System.out.println("--> erreur fillZone 2 : ".concat(String.valueOf(String.valueOf(ex2))));
            }
        }
    }
    
    private void _$11866(final int n, final int n2, final int n3, final int n4) {
        this.pixels[n2 * this.iW + n] = n3;
        if (n > 0 && n4 == this.pixels[n2 * this.iW + (n - 1)]) {
            this._$11866(n - 1, n2, n3, n4);
        }
        if (n < this.iW - 1 && n4 == this.pixels[n2 * this.iW + (n + 1)]) {
            this._$11866(n + 1, n2, n3, n4);
        }
        if (n2 < this.iH - 1 && n4 == this.pixels[(n2 + 1) * this.iW + n]) {
            this._$11866(n, n2 + 1, n3, n4);
        }
        if (n2 > 0 && n4 == this.pixels[(n2 - 1) * this.iW + n]) {
            this._$11866(n, n2 - 1, n3, n4);
        }
    }
    
    private void _$11863(final int n, final int n2, final int n3, final int n4) {
        final Stack stack = new Stack<Point>();
        stack.push(new Point(n, n2));
        while (!stack.isEmpty()) {
            Point point;
            for (point = stack.pop(); this._$11875(point.x, point.y) == n3 && !stack.isEmpty(); point = stack.pop()) {}
            int x = point.x;
            int x2 = point.x;
            int x3 = point.x;
            final int y = point.y;
            this._$11876(x3, y, n3);
            ++x3;
            for (int n5 = this._$11875(x3, y); (n5 == n4 || n5 == n3) && x3 < this.iW; ++x3, n5 = this._$11875(x3, y)) {
                if (n5 != n3) {
                    this._$11876(x3, y, n3);
                }
                x2 = x3;
            }
            for (int n6 = point.x - 1, n7 = this._$11875(n6, y); (n7 == n4 || n7 == n3) && n6 > -1; n7 = this._$11875(n6, y)) {
                if (n7 != n3) {
                    this._$11876(n6, y, n3);
                }
                x = n6;
                if (--n6 < 0) {
                    break;
                }
            }
            int i = x2;
            if (y < this.iH) {
                while (i >= x) {
                    int n8;
                    for (n8 = this._$11875(i, y + 1); n8 != n4 && i >= x; --i, n8 = this._$11875(i, y + 1)) {}
                    if (i >= x && n8 == n4) {
                        stack.push(new Point(i, y + 1));
                    }
                    for (int n9 = this._$11875(i, y + 1); (n9 == n4 || n9 == n3) && i >= x; --i, n9 = this._$11875(i, y + 1)) {}
                }
            }
            int j = x2;
            if (y > 0) {
                while (j >= x) {
                    int n10;
                    for (n10 = this._$11875(j, y - 1); n10 != n4 && j >= x; --j, n10 = this._$11875(j, y - 1)) {}
                    if (j >= x && n10 == n4) {
                        stack.push(new Point(j, y - 1));
                    }
                    for (int n11 = this._$11875(j, y - 1); (n11 == n4 || n11 == n3) && j >= x; --j, n11 = this._$11875(j, y - 1)) {}
                }
            }
        }
    }
    
    private int _$11875(final int n, final int n2) {
        if (n < this.iW && n2 < this.iH && n > -1 && n2 > -1) {
            return this.pixels[n2 * this.iW + n];
        }
        return -1;
    }
    
    private void _$11876(final int n, final int n2, final int n3) {
        if (n < this.iW && n2 < this.iH && n > -1 && n2 > -1) {
            this.pixels[n2 * this.iW + n] = n3;
        }
    }
}
