import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.CheckboxGroup;
import java.awt.Component;
import java.awt.Button;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Checkbox;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class eq3456 extends Applet implements ActionListener, ItemListener, MouseListener, MouseMotionListener
{
    final int div = 25;
    final int nest = 2;
    final int zoomRatio = 2;
    final int borderSize = 35;
    int deg;
    int size;
    double scale;
    double offx;
    double offy;
    double xmax;
    double ymax;
    point[] pt;
    double[][] mat;
    int near;
    int keep;
    int wx;
    int wy;
    Checkbox cb3;
    Checkbox cb4;
    Checkbox cb5;
    Checkbox cb6;
    Image image1;
    Graphics offg;
    
    public eq3456() {
        this.scale = 50.0;
        this.pt = new point[27];
        this.mat = new double[27][28];
    }
    
    public void init() {
        this.wx = this.getSize().width;
        this.wy = this.getSize().height;
        this.offx = this.wx / 2.0;
        this.offy = (this.wy - 35) / 2.0;
        this.xmax = this.wx / 2.0 / this.scale;
        this.ymax = (this.wy - 35) / 2.0 / this.scale;
        this.image1 = this.createImage(this.wx, this.wy);
        this.offg = this.image1.getGraphics();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        final Button button = new Button("F I T");
        final Button button2 = new Button("zoom IN");
        final Button button3 = new Button("zoom OUT");
        final Button button4 = new Button("Random");
        panel.add(button);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        button.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        this.add("South", panel);
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.cb3 = new Checkbox("3", checkboxGroup, true);
        this.cb4 = new Checkbox("4", checkboxGroup, false);
        this.cb5 = new Checkbox("5", checkboxGroup, false);
        this.cb6 = new Checkbox("6 degree", checkboxGroup, false);
        panel.add(this.cb3);
        panel.add(this.cb4);
        panel.add(this.cb5);
        panel.add(this.cb6);
        this.cb3.addItemListener(this);
        this.cb4.addItemListener(this);
        this.cb5.addItemListener(this);
        this.cb6.addItemListener(this);
        this.deg = 3;
        this.size = this.ptnum(this.deg);
        this.randpoint();
    }
    
    public int ptnum(final int n) {
        int n2 = 0;
        switch (n) {
            case 3: {
                n2 = 9;
                break;
            }
            case 4: {
                n2 = 14;
                break;
            }
            case 5: {
                n2 = 20;
                break;
            }
            case 6: {
                n2 = 27;
                break;
            }
        }
        return n2;
    }
    
    public void randpoint() {
        int n = 0;
        for (int i = 0; i < this.size; ++i) {
            this.pt[n++] = new point(this.xmax / 1.5 * (2.0 * Math.random() - 1.0), this.ymax / 1.5 * (2.0 * Math.random() - 1.0));
        }
        this.near = -1;
        this.keep = -1;
    }
    
    public void paint(final Graphics graphics) {
        this.offg.setColor(new Color(100, 100, 100));
        this.offg.fillRect(0, 0, this.wx, this.wy);
        this.xmax = this.wx / 2.0 / this.scale;
        this.ymax = (this.wy - 35) / 2.0 / this.scale;
        this.setMat();
        this.offg.setColor(new Color(255, 255, 255));
        this.draw_Curv(this.offg, -this.xmax, -this.ymax, this.xmax, this.ymax, 2, 0);
        this.offg.setColor(new Color(255, 0, 0));
        for (int i = 0; i < this.size; ++i) {
            this.offg.drawOval((int)(this.scale * this.pt[i].x + this.offx) - 1, (int)(-this.scale * this.pt[i].y + this.offy) - 1, 3, 3);
        }
        if (this.near >= 0) {
            this.offg.drawRect((int)(this.scale * this.pt[this.near].x + this.offx) - 7, (int)(-this.scale * this.pt[this.near].y + this.offy) - 7, 15, 15);
        }
        graphics.drawImage(this.image1, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void setMat() {
        for (int i = 0; i < this.size; ++i) {
            this.mat[i][0] = this.pt[i].x;
            this.mat[i][1] = this.pt[i].y;
            this.mat[i][2] = this.pt[i].x * this.pt[i].x;
            this.mat[i][3] = this.pt[i].x * this.pt[i].y;
            this.mat[i][4] = this.pt[i].y * this.pt[i].y;
            this.mat[i][5] = this.pt[i].x * this.pt[i].x * this.pt[i].x;
            this.mat[i][6] = this.pt[i].x * this.pt[i].x * this.pt[i].y;
            this.mat[i][7] = this.pt[i].x * this.pt[i].y * this.pt[i].y;
            this.mat[i][8] = this.pt[i].y * this.pt[i].y * this.pt[i].y;
            if (this.deg != 3) {
                this.mat[i][9] = this.pt[i].x * this.pt[i].x * this.pt[i].x * this.pt[i].x;
                this.mat[i][10] = this.pt[i].x * this.pt[i].x * this.pt[i].x * this.pt[i].y;
                this.mat[i][11] = this.pt[i].x * this.pt[i].x * this.pt[i].y * this.pt[i].y;
                this.mat[i][12] = this.pt[i].x * this.pt[i].y * this.pt[i].y * this.pt[i].y;
                this.mat[i][13] = this.pt[i].y * this.pt[i].y * this.pt[i].y * this.pt[i].y;
                if (this.deg != 4) {
                    this.mat[i][14] = this.mat[i][9] * this.pt[i].x;
                    this.mat[i][15] = this.mat[i][9] * this.pt[i].y;
                    this.mat[i][16] = this.pt[i].x * this.mat[i][11];
                    this.mat[i][17] = this.pt[i].x * this.mat[i][12];
                    this.mat[i][18] = this.pt[i].x * this.mat[i][13];
                    this.mat[i][19] = this.mat[i][13] * this.pt[i].y;
                    if (this.deg != 5) {
                        this.mat[i][20] = this.mat[i][14] * this.pt[i].x;
                        this.mat[i][21] = this.mat[i][14] * this.pt[i].y;
                        this.mat[i][22] = this.mat[i][15] * this.pt[i].y;
                        this.mat[i][23] = this.mat[i][16] * this.pt[i].y;
                        this.mat[i][24] = this.pt[i].x * this.mat[i][18];
                        this.mat[i][25] = this.mat[i][18] * this.pt[i].y;
                        this.mat[i][26] = this.mat[i][19] * this.pt[i].y;
                    }
                }
            }
        }
        for (int j = 0; j < this.size; ++j) {
            this.mat[j][this.size] = -1.0;
        }
        this.haki(this.mat);
    }
    
    private double func(final double n, final double n2) {
        double n3 = 1.0;
        do {
            n3 += this.mat[0][this.size] * n + this.mat[1][this.size] * n2 + this.mat[2][this.size] * n * n + this.mat[3][this.size] * n * n2 + this.mat[4][this.size] * n2 * n2 + this.mat[5][this.size] * n * n * n + this.mat[6][this.size] * n * n * n2 + this.mat[7][this.size] * n * n2 * n2 + this.mat[8][this.size] * n2 * n2 * n2;
            if (this.deg == 3) {
                break;
            }
            n3 += this.mat[9][this.size] * n * n * n * n + this.mat[10][this.size] * n * n * n * n2 + this.mat[11][this.size] * n * n * n2 * n2 + this.mat[12][this.size] * n * n2 * n2 * n2 + this.mat[13][this.size] * n2 * n2 * n2 * n2;
            if (this.deg == 4) {
                break;
            }
            n3 += this.mat[14][this.size] * n * n * n * n * n + this.mat[15][this.size] * n * n * n * n * n2 + this.mat[16][this.size] * n * n * n * n2 * n2 + this.mat[17][this.size] * n * n * n2 * n2 * n2 + this.mat[18][this.size] * n * n2 * n2 * n2 * n2 + this.mat[19][this.size] * n2 * n2 * n2 * n2 * n2;
            if (this.deg == 5) {
                break;
            }
            n3 += this.mat[20][this.size] * n * n * n * n * n * n + this.mat[21][this.size] * n * n * n * n * n * n2 + this.mat[22][this.size] * n * n * n * n * n2 * n2 + this.mat[23][this.size] * n * n * n * n2 * n2 * n2 + this.mat[24][this.size] * n * n * n2 * n2 * n2 * n2 + this.mat[25][this.size] * n * n2 * n2 * n2 * n2 * n2 + this.mat[26][this.size] * n2 * n2 * n2 * n2 * n2 * n2;
        } while (this.deg != 6);
        return n3;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.cb3) {
            this.deg = 3;
            this.size = this.ptnum(this.deg);
        }
        else if (itemEvent.getItemSelectable() == this.cb4) {
            this.deg = 4;
            this.size = this.ptnum(this.deg);
        }
        else if (itemEvent.getItemSelectable() == this.cb5) {
            this.deg = 5;
            this.size = this.ptnum(this.deg);
        }
        else if (itemEvent.getItemSelectable() == this.cb6) {
            this.deg = 6;
            this.size = this.ptnum(this.deg);
        }
        this.randpoint();
        this.repaint();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("F I T")) {
            double abs = 0.0;
            double abs2 = 0.0;
            for (int i = 0; i < this.ptnum(this.deg); ++i) {
                if (Math.abs(this.pt[i].x) > abs) {
                    abs = Math.abs(this.pt[i].x);
                }
                if (Math.abs(this.pt[i].y) > abs) {
                    abs2 = Math.abs(this.pt[i].y);
                }
            }
            this.scale = this.wx / 2.0 / Math.max(abs, abs2) * 0.5;
            this.repaint();
        }
        else if (actionEvent.getActionCommand().equals("zoom IN")) {
            this.scale *= 2.0;
            this.repaint();
        }
        else if (actionEvent.getActionCommand().equals("zoom OUT")) {
            this.scale /= 2.0;
            this.repaint();
        }
        else if (actionEvent.getActionCommand().equals("Random")) {
            this.randpoint();
            this.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.near = -1;
        this.keep = -1;
        this.repaint();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.keep >= 0) {
            final int width = this.getSize().width;
            final int height = this.getSize().height;
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            if (0 < x && x < width && 0 < y && y < height - 35) {
                this.pt[this.keep].x = x / this.scale - this.xmax;
                this.pt[this.keep].y = -(y - this.offy) / this.scale;
                this.repaint();
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.near >= 0) {
            this.keep = this.near;
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final point point = new point(mouseEvent.getX() / this.scale - this.xmax, -(mouseEvent.getY() - this.offy) / this.scale);
        for (int i = 0; i < this.size; ++i) {
            if (this.distance(point, this.pt[i]) < this.xmax / 30.0) {
                this.near = i;
                this.repaint();
                break;
            }
            if (this.near >= 0) {
                this.near = -1;
                this.repaint();
            }
        }
    }
    
    double distance(final point point, final point point2) {
        final double n = point.x - point2.x;
        final double n2 = point.y - point2.y;
        return Math.sqrt(n * n + n2 * n2);
    }
    
    private void draw_Curv(final Graphics graphics, final double n, final double n2, final double n3, final double n4, final int n5, int n6) {
        final double[][] array = new double[26][26];
        if (n5 == 0) {
            switch (n6) {
                case 1:
                case 14: {
                    this.dline1(graphics, n, n2, n3, n4);
                    break;
                }
                case 2:
                case 13: {
                    this.dline1(graphics, n3, n2, n, n4);
                    break;
                }
                case 4:
                case 11: {
                    this.dline1(graphics, n3, n4, n, n2);
                    break;
                }
                case 7:
                case 8: {
                    this.dline1(graphics, n, n4, n3, n2);
                    break;
                }
                case 3:
                case 12: {
                    this.dline21(graphics, n, n2, n3, n4);
                    break;
                }
                case 6:
                case 9: {
                    this.dline22(graphics, n3, n2, n, n4);
                    break;
                }
                case 5:
                case 10: {
                    this.dline3(graphics, n, n2, n3, n4);
                    break;
                }
            }
        }
        else {
            for (int i = 0; i <= 25; ++i) {
                final double n7 = n + (n3 - n) / 25.0 * i;
                for (int j = 0; j <= 25; ++j) {
                    array[i][j] = this.func(n7, n2 + (n4 - n2) / 25.0 * j);
                }
            }
            for (int k = 0; k < 25; ++k) {
                final double n8 = n + (n3 - n) / 25.0 * k;
                for (int l = 0; l < 25; ++l) {
                    final double n9 = n2 + (n4 - n2) / 25.0 * l;
                    n6 = 0;
                    if (array[k][l] >= 0.0) {
                        ++n6;
                    }
                    if (array[k + 1][l] >= 0.0) {
                        n6 += 2;
                    }
                    if (array[k + 1][l + 1] >= 0.0) {
                        n6 += 4;
                    }
                    if (array[k][l + 1] >= 0.0) {
                        n6 += 8;
                    }
                    if (0 < n6 && n6 < 15) {
                        this.draw_Curv(graphics, n8, n9, n8 + (n3 - n) / 25.0, n9 + (n4 - n2) / 25.0, n5 - 1, n6);
                    }
                }
            }
        }
    }
    
    private void dline(final Graphics graphics, final double n, final double n2, final double n3, final double n4) {
        graphics.drawLine((int)(this.scale * n + this.offx), (int)(-this.scale * n2 + this.offy), (int)(this.scale * n3 + this.offx), (int)(-this.scale * n4 + this.offy));
    }
    
    private void dline1(final Graphics graphics, final double n, final double n2, final double n3, final double n4) {
        final double func = this.func(n, n2);
        final double func2 = this.func(n3, n2);
        final double func3 = this.func(n, n4);
        this.dline(graphics, (func * n3 - func2 * n) / (func - func2), n2, n, (func * n4 - func3 * n2) / (func - func3));
    }
    
    private void dline21(final Graphics graphics, final double n, final double n2, final double n3, final double n4) {
        final double func = this.func(n, n2);
        final double func2 = this.func(n3, n2);
        final double func3 = this.func(n3, n4);
        final double func4 = this.func(n, n4);
        this.dline(graphics, n, (func * n4 - func4 * n2) / (func - func4), n3, (func2 * n4 - func3 * n2) / (func2 - func3));
    }
    
    private void dline22(final Graphics graphics, final double n, final double n2, final double n3, final double n4) {
        final double func = this.func(n, n2);
        final double func2 = this.func(n3, n2);
        final double func3 = this.func(n3, n4);
        final double func4 = this.func(n, n4);
        this.dline(graphics, (func2 * n - func * n3) / (func2 - func), n2, (func3 * n - func4 * n3) / (func3 - func4), n4);
    }
    
    private void dline3(final Graphics graphics, final double n, final double n2, final double n3, final double n4) {
        final double func = this.func(n, n2);
        final double func2 = this.func(n3, n2);
        final double func3 = this.func(n3, n4);
        final double func4 = this.func(n, n4);
        final double n5 = (func * n3 - func2 * n) / (func - func2);
        final double n6 = (func * n4 - func4 * n2) / (func - func4);
        final double n7 = (func3 * n - func4 * n3) / (func3 - func4);
        this.dline(graphics, n, n6, n3, (func3 * n2 - func2 * n4) / (func3 - func2));
        this.dline(graphics, n5, n2, n7, n4);
    }
    
    public void haki(final double[][] array) {
        final int size = this.size;
        final int n = this.size + 1;
        for (int i = 0; i < size; ++i) {
            int n2;
            for (n2 = i; array[n2][i] == 0.0 && n2 < size - 2; ++n2) {}
            this.swap(array, i, n2);
            final double n3 = array[i][i];
            for (int j = 0; j < n; ++j) {
                final double[] array2 = array[i];
                final int n4 = j;
                array2[n4] /= n3;
            }
            for (int k = 0; k < size; ++k) {
                if (k != i) {
                    final double n5 = array[k][i];
                    for (int l = i; l < n; ++l) {
                        final double[] array3 = array[k];
                        final int n6 = l;
                        array3[n6] -= n5 * array[i][l];
                    }
                }
            }
        }
    }
    
    private void swap(final double[][] array, final int n, final int n2) {
        for (int i = 0; i < this.size + 1; ++i) {
            final double n3 = array[n][i];
            array[n][i] = array[n2][i];
            array[n2][i] = n3;
        }
    }
    
    public class point
    {
        double x;
        double y;
        
        point() {
        }
        
        point(final double x, final double y) {
            this.x = x;
            this.y = y;
        }
    }
}
