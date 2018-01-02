import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.util.Vector;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class cTools extends Panel implements MouseMotionListener, MouseListener
{
    static String sVer;
    Vector listeners;
    Font f0;
    Font f1;
    private boolean _$11802;
    private boolean _$11801;
    int w;
    int h;
    int xcoord;
    int ycoord;
    int iColor;
    int fgColor;
    int bgColor;
    int iAction;
    String Text;
    int[] iBX;
    int[] iBY;
    int[] iBsizeX;
    int[] iBsizeY;
    int[] iBT;
    int[] iBI;
    int[] iBA;
    int[] iTP;
    private Image _$11892;
    private Graphics _$11893;
    private int _$11894;
    private Image _$11895;
    private Image[] _$11480;
    private Choice _$13044;
    private Choice _$13045;
    private Choice _$13046;
    private TextField _$11501;
    public Button b1;
    public Button b2;
    
    public cTools() {
        this._$11802 = false;
        this._$11801 = false;
        this.w = 0;
        this.h = 0;
        this.iColor = -2;
        this.fgColor = 0;
        this.bgColor = -1;
        this.iAction = 0;
        this.Text = "";
        this.iBX = new int[] { 2, 2, 55, 84, 113, 142, 171, 200, 229, 258, 258, 258, 258, 287, 287, 287, 316, 345, 60, 89, 118, 147, 176, 205, 234, 263, 292, 321 };
        this.iBY = new int[] { 3, 3, 4, 4, 4, 4, 4, 4, 4, 2, 10, 19, 28, 3, 14, 25, 4, 4, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65 };
        this.iBsizeX = new int[] { 45, 45, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 25, 25, 25, 24, 24, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22 };
        this.iBsizeY = new int[] { 35, 35, 24, 24, 24, 24, 24, 24, 24, 7, 8, 8, 8, 10, 10, 10, 24, 24, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22 };
        this.iBT = new int[] { 0, 0, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 4, 4, 4, 2, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        this.iBI = new int[] { -1, -1, 1, 5, 0, 4, 6, 7, 3, -1, -1, -1, -1, -1, -1, -1, -1, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
        this.iBA = new int[] { 800, 801, 24, 21, 25, 20, 1, 4, 11, 910, 911, 912, 913, 900, 901, 902, 999, 26, 950, 951, 952, 953, 954, 955, 956, 957, 958, 959 };
        this.iTP = new int[] { -1, 21, -1, 910, 900, -1 };
        this._$11894 = 13684944;
        this._$11895 = null;
        this._$13044 = null;
        this._$13045 = new Choice();
        this._$13046 = new Choice();
        this._$11501 = new TextField("", 15);
        this.b1 = null;
        this.b2 = null;
        this.listeners = new Vector();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }
    
    private void _$13047() {
        this.f0 = null;
        this._$13044 = new Choice();
        final String[] fontList = this.getToolkit().getFontList();
        for (int i = 0; i < fontList.length; ++i) {
            this._$13044.addItem(fontList[i]);
        }
        this._$13045.addItem("7");
        this._$13045.addItem("8");
        this._$13045.addItem("9");
        this._$13045.addItem("10");
        this._$13045.addItem("11");
        this._$13045.addItem("12");
        this._$13045.addItem("14");
        this._$13045.addItem("16");
        this._$13045.addItem("18");
        this._$13045.addItem("20");
        this._$13045.addItem("24");
        this._$13045.addItem("28");
        this._$13045.addItem("32");
        this._$13045.addItem("36");
        this._$13045.addItem("40");
        this._$13045.select(8);
        this._$13046.addItem("Plain");
        this._$13046.addItem("Bold");
        this._$13046.addItem("Italic");
        this._$13046.addItem("Bold+Italic");
        this.setLayout(null);
        final int n = 38;
        final Label label = new Label("Font");
        this.add(label);
        label.setBounds(5, n, 50, 20);
        this.add(this._$13044);
        this._$13044.setBounds(60, n, 100, 20);
        this.add(this._$13045);
        this._$13045.setBounds(168, n, 50, 20);
        this.add(this._$13046);
        this._$13046.setBounds(226, n, 60, 20);
        this.add(this._$11501);
        this._$11501.setBounds(290, n, 250, 22);
        final int n2 = 67;
        int width = this.getSize().width;
        final Label label2 = new Label("Symbol");
        this.add(label2);
        label2.setBounds(5, n2, 50, 20);
        if (this.b1 != null) {
            this.add(this.b1);
            this.b1.setBounds(width - 50, n2 - 4, 50, 25);
            width -= 55;
        }
        if (this.b2 != null) {
            this.add(this.b2);
            this.b2.setBounds(width - 65, n2 - 4, 65, 25);
            width -= 65;
        }
        final Label label3 = new Label(cTools.sVer);
        this.add(label3);
        label3.setBounds(width - 85, n2, 83, 25);
    }
    
    public void setBkColor(final int $11894) {
        this._$11894 = $11894;
    }
    
    public void setBkImage(final Image $11895) {
        this._$11895 = $11895;
    }
    
    public void setImage(final Image image) {
        this._$11480 = new Image[20];
        if (image == null) {
            return;
        }
        final int n = image.getWidth(this) / 10;
        final int height = image.getHeight(this);
        for (int i = 0; i < 10; ++i) {
            this._$11480[i] = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(i * n, 0, n, height)));
        }
    }
    
    public void setSymbol(final Image image, final int n) {
        if (image == null) {
            return;
        }
        this._$11480[10 + n] = image;
    }
    
    public void paint(final Graphics graphics) {
        if (this._$13044 == null) {
            this._$13047();
        }
        if (this._$11893 == null) {
            this.w = this.getSize().width;
            this.h = this.getSize().height;
            try {
                this._$11892 = this.createImage(this.w, this.h);
                this._$11893 = this._$11892.getGraphics();
            }
            catch (Exception ex) {
                this._$11893 = null;
            }
        }
        this.update(graphics);
    }
    
    public synchronized void update(final Graphics graphics) {
        if (this._$11893 != null) {
            this._$11828(this._$11893);
            graphics.drawImage(this._$11892, 0, 0, this);
        }
        else {
            this._$11828(graphics);
        }
    }
    
    private void _$11828(final Graphics graphics) {
        graphics.setColor(new Color(this._$11894));
        graphics.fillRect(0, 0, this.w, this.h);
        if (this._$11895 != null) {
            graphics.drawImage(this._$11895, 0, 0, this);
        }
        graphics.setColor(Color.lightGray);
        for (int i = 2; i < this.iBX.length; ++i) {
            graphics.fill3DRect(this.iBX[i], this.iBY[i], this.iBsizeX[i], this.iBsizeY[i], true);
            if (this.iBA[i] == this.iTP[this.iBT[i]]) {
                graphics.fill3DRect(this.iBX[i], this.iBY[i], this.iBsizeX[i], this.iBsizeY[i], false);
            }
            else {
                graphics.fill3DRect(this.iBX[i], this.iBY[i], this.iBsizeX[i], this.iBsizeY[i], true);
            }
            if (this.iBI[i] > -1 && this._$11480[this.iBI[i]] != null) {
                if (i > 9) {
                    graphics.drawImage(this._$11480[this.iBI[i]], this.iBX[i] + 1, this.iBY[i] + 1, this);
                }
                else {
                    graphics.drawImage(this._$11480[this.iBI[i]], this.iBX[i] + 2, this.iBY[i] + 2, this);
                }
            }
        }
        graphics.setColor(new Color(this.bgColor));
        graphics.fillRect(this.iBX[0] + 15, this.iBY[0] + 12, 28, 20);
        graphics.setColor(new Color(this.fgColor));
        graphics.fillRect(this.iBX[0], this.iBY[0], 28, 20);
        final int n = this.iBX.length - 10;
        graphics.setColor(Color.black);
        graphics.drawLine(this.iBX[n - 5] + 2, this.iBY[n - 5] + 4, this.iBX[n - 5] + 20, this.iBY[n - 5] + 4);
        graphics.drawLine(this.iBX[n - 4] + 2, this.iBY[n - 4] + 4, this.iBX[n - 4] + 20, this.iBY[n - 4] + 4);
        graphics.drawLine(this.iBX[n - 4] + 2, this.iBY[n - 4] + 3, this.iBX[n - 4] + 20, this.iBY[n - 4] + 3);
        graphics.drawLine(this.iBX[n - 3] + 2, this.iBY[n - 3] + 4, this.iBX[n - 3] + 20, this.iBY[n - 3] + 4);
        graphics.drawLine(this.iBX[n - 3] + 2, this.iBY[n - 3] + 3, this.iBX[n - 3] + 20, this.iBY[n - 3] + 3);
        graphics.drawLine(this.iBX[n - 3] + 2, this.iBY[n - 3] + 5, this.iBX[n - 3] + 20, this.iBY[n - 3] + 5);
        cDraw.drawLine(graphics, 0, this.iBX[n - 8] + 2, this.iBY[n - 8] + 4, this.iBX[n - 8] + 20, this.iBY[n - 8] + 4, 1);
        cDraw.drawLine(graphics, 0, this.iBX[n - 7] + 2, this.iBY[n - 7] + 4, this.iBX[n - 7] + 20, this.iBY[n - 7] + 4, 2);
        cDraw.drawLine(graphics, 0, this.iBX[n - 6] + 2, this.iBY[n - 6] + 4, this.iBX[n - 6] + 20, this.iBY[n - 6] + 4, 3);
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Dialog", 1, 16));
        graphics.drawString("X", this.iBX[n - 2] + 5, 23);
        graphics.drawString("X", this.iBX[n - 2] + 6, 24);
        if (this.iColor != -2) {
            graphics.setColor(new Color(this.iColor));
            graphics.fillRect(this.w - 175, 4, 20, 30);
        }
        int n2 = 0;
        int n3 = 0;
        for (int j = 0; j < 6; ++j) {
            for (int k = 0; k < 6; ++k) {
                for (int l = 0; l < 6; ++l) {
                    graphics.setColor(new Color(j * 51, k * 51, l * 51));
                    graphics.fillRect(this.w - 10 - 140 + n3 * 4, 4 + n2 * 5, 4, 5);
                    if (n3 > 34) {
                        ++n2;
                        n3 = 0;
                    }
                    else {
                        ++n3;
                    }
                }
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        for (int i = 1; i < this.iBX.length; ++i) {
            if (x > this.iBX[i] && x < this.iBX[i] + this.iBsizeX[i] && y > this.iBY[i] && y < this.iBY[i] + this.iBsizeY[i]) {
                this.iAction = i;
            }
        }
        this.iColor = -2;
        if (x > this.w - 150 && y > 4) {
            int n = 0;
            int n2 = 0;
            for (int j = 0; j < 6; ++j) {
                for (int k = 0; k < 6; ++k) {
                    for (int l = 0; l < 6; ++l) {
                        if (x > this.w - 150 + n2 * 4 && x <= this.w - 146 + n2 * 4 && y > 4 + n * 5 && y <= 9 + n * 5) {
                            final Color color = new Color(j * 51, k * 51, l * 51);
                            this.iAction = 0;
                            this.iColor = color.getRGB();
                        }
                        if (n2 > 34) {
                            ++n;
                            n2 = 0;
                        }
                        else {
                            ++n2;
                        }
                    }
                }
            }
        }
        this.xcoord = x;
        this.ycoord = y;
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this._$11802 = false;
        this.iColor = -2;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this._$11802 = false;
        mouseEvent.getX();
        mouseEvent.getY();
        if (this.iAction == 0) {
            if (mouseEvent.getModifiers() == 4 || mouseEvent.getModifiers() == 1) {
                ++this.iAction;
            }
            if (this.iColor == -2) {
                this.iAction = -1;
            }
        }
        if (this.iAction > -1 && this.iAction < this.iBX.length) {
            this._$11905(this.iAction);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this._$11802 = true;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(40, 65);
    }
    
    public String getText() {
        this.f1 = new Font(this._$13044.getSelectedItem(), this._$13046.getSelectedIndex(), Integer.parseInt(this._$13045.getSelectedItem()));
        if (!this.f1.equals(this.f0)) {
            this._$11905(1000);
            this.f0 = this.f1;
        }
        if (this._$11501 != null) {
            return this._$11501.getText();
        }
        return "";
    }
    
    public void addcToolsListener(final cToolsListener cToolsListener) {
        if (this.listeners.indexOf(cToolsListener) == -1) {
            this.listeners.addElement(cToolsListener);
        }
    }
    
    public void removecToolsListener(final cToolsListener cToolsListener) {
        this.listeners.removeElement(cToolsListener);
    }
    
    private void _$11905(final int n) {
        final Enumeration<cToolsListener> elements = this.listeners.elements();
        int n2;
        int n3;
        if (n != 1000) {
            n2 = this.iBT[n];
            n3 = this.iBA[n];
            if (n3 != 999 && n3 != 26) {
                this.iTP[n2] = n3;
            }
        }
        else {
            n3 = 1000;
            n2 = -1;
        }
        if (n3 == 800) {
            this.fgColor = this.iColor;
        }
        else if (n3 == 801) {
            this.bgColor = this.iColor;
        }
        while (elements.hasMoreElements()) {
            final cToolsListener cToolsListener = elements.nextElement();
            if (n3 == 999) {
                cToolsListener.onErase();
            }
            if (n3 == 26) {
                cToolsListener.onUndo();
            }
            if (n3 == 800 || n3 == 801) {
                cToolsListener.onColor(this.fgColor, this.bgColor);
            }
            if (n3 == 900 || n3 == 901 || n3 == 902) {
                cToolsListener.onSize(n3 - 899);
            }
            if (n3 == 910 || n3 == 911 || n3 == 912 || n3 == 913) {
                cToolsListener.onArrow(n3 - 910);
            }
            if (n3 == 1000) {
                cToolsListener.onFont(this.f1);
            }
            if (n2 == 1) {
                cToolsListener.onAction(n3);
            }
        }
        this.repaint();
    }
    
    static {
        cTools.sVer = "aDraw v2.00";
    }
}
