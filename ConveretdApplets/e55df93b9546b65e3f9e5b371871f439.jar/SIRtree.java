import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.BorderLayout;
import java.awt.Scrollbar;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Image;
import java.util.StringTokenizer;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SIRtree extends Applet implements MouseMotionListener, MouseListener
{
    boolean found;
    boolean end;
    int i;
    int j;
    int k;
    int x;
    int xoffset;
    int y;
    int yoffset;
    int StrWidth;
    int NODES_DISP;
    int LARGEST_X;
    int MAX_LINES;
    int MAX_X;
    String TEMPSTR;
    String[] TEMPARRAY;
    String TEMPPARENT;
    String SC;
    String FT;
    String Fonttype;
    String Fontstyle;
    StringTokenizer st;
    Image tempimage;
    FontMetrics fontMetrics;
    int FIRST;
    int WIDTH;
    int HEIGHT;
    String TITLE;
    String ICONS;
    String BGIMG;
    int BGCOLOR;
    int TXTCOLOR;
    int HBGCOLOR;
    int HTXTCOLOR;
    int TITLEFONT;
    int NODEFONT;
    Color[] COLORS;
    Font[] FONTS;
    String[] PARENT;
    String[][] NODET;
    int[][] NODEI;
    int NO_NODES;
    int NO_PARENTS;
    int AreaCount;
    int[][] Area;
    Image[] NODE_IMG;
    Image BG_IMG;
    public Image buffer;
    public Image buffer2;
    public Image buffer3;
    Graphics pad;
    Graphics BGpad;
    Graphics TREE;
    Scrollbar vscrollbar;
    Scrollbar hscrollbar;
    boolean Vscroll;
    boolean Hscroll;
    int Linset;
    int Rinset;
    int Tinset;
    int Binset;
    BorderLayout layout;
    
    public SIRtree() {
        this.FIRST = 0;
        this.Linset = 0;
        this.Rinset = 0;
        this.Tinset = 0;
        this.Binset = 0;
        this.layout = new BorderLayout();
    }
    
    public void AddArea(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        if (this.AreaCount < 1000) {
            this.Area[this.AreaCount][0] = n;
            this.Area[this.AreaCount][1] = n2 - 1;
            this.Area[this.AreaCount][2] = n3 - 1;
            this.Area[this.AreaCount][3] = n4 + 1;
            this.Area[this.AreaCount][4] = n5 + 1;
            this.Area[this.AreaCount][5] = n6;
            ++this.AreaCount;
        }
    }
    
    public void CreateBG() {
        this.buffer2 = this.createImage(this.WIDTH, this.HEIGHT);
        (this.BGpad = this.buffer2.getGraphics()).setColor(this.COLORS[this.BGCOLOR]);
        this.BGpad.fillRect(0, 0, this.WIDTH, this.HEIGHT);
        this.BGpad.drawImage(this.BG_IMG, 0, 0, this);
        this.BGpad.setColor(this.COLORS[0]);
        this.BGpad.drawLine(0, 0, this.WIDTH, 0);
        this.BGpad.drawLine(0, 0, 0, this.HEIGHT);
        this.BGpad.drawLine(5, 30, this.WIDTH - 5, 30);
        this.BGpad.drawLine(this.WIDTH - 5, 5, this.WIDTH - 5, 30);
        this.BGpad.drawLine(5, this.HEIGHT - 5, this.WIDTH - 5, this.HEIGHT - 5);
        this.BGpad.drawLine(this.WIDTH - 5, 34, this.WIDTH - 5, this.HEIGHT - 5);
        this.BGpad.setColor(this.COLORS[11]);
        this.BGpad.drawLine(this.WIDTH - 1, 0, this.WIDTH - 1, this.HEIGHT);
        this.BGpad.drawLine(0, this.HEIGHT - 1, this.WIDTH - 1, this.HEIGHT - 1);
        this.BGpad.drawLine(5, 5, this.WIDTH - 5, 5);
        this.BGpad.drawLine(5, 5, 5, 30);
        this.BGpad.drawLine(5, 34, this.WIDTH - 5, 34);
        this.BGpad.drawLine(5, 34, 5, this.HEIGHT - 5);
        this.BGpad.setColor(this.COLORS[this.TXTCOLOR]);
        this.BGpad.setFont(this.FONTS[this.TITLEFONT]);
        this.fontMetrics = this.getFontMetrics(this.FONTS[this.TITLEFONT]);
        this.y = this.fontMetrics.getHeight();
        this.x = this.fontMetrics.stringWidth(this.TITLE);
        this.BGpad.drawString(this.TITLE, (this.WIDTH - this.x) / 2, (30 + this.y) / 2);
    }
    
    public void CreateTree() {
        this.buffer3 = this.createImage(this.WIDTH - 11, this.HEIGHT - 40);
        (this.TREE = this.buffer3.getGraphics()).setColor(this.COLORS[this.BGCOLOR]);
        this.TREE.fillRect(0, 0, this.WIDTH - 11, this.HEIGHT - 40);
        this.TREE.drawImage(this.BG_IMG, 0, 0, this);
        this.AreaCount = 0;
        this.y = 16;
        final int n = 10 - this.xoffset;
        final int n2 = 0;
        this.NODES_DISP = 0;
        this.LARGEST_X = 0;
        this.DrawTheLeafs(n2, n);
        this.checkScroll();
    }
    
    public void DrawTheLeafs(final int n, final int n2) {
        int n3 = -1;
        final int n4 = this.y - this.yoffset;
        this.TREE.setColor(this.COLORS[11]);
        this.TREE.drawLine(n2, n4 - 11, n2, n4 - 16);
        if (n4 < this.HEIGHT - 40) {
            for (int i = 0; i < this.NO_NODES; ++i) {
                final int n5 = this.y - this.yoffset;
                if (this.NODEI[i][0] == n) {
                    this.TREE.setColor(this.COLORS[11]);
                    if (n3 > -1) {
                        if (this.NODEI[i - 1][2] == 0) {
                            this.TREE.drawLine(n2, n3 - 7, n2, n5 - 11);
                        }
                        else {
                            this.TREE.drawLine(n2, n3 - 2, n2, n5 - 11);
                        }
                    }
                    this.TREE.drawLine(n2 + 5, n5 - 7, n2 + 8, n5 - 7);
                    ++this.NODES_DISP;
                    if (n5 > -1) {
                        if (this.NODEI[i][2] == 1) {
                            this.TREE.drawRect(n2 - 4, n5 - 11, 8, 8);
                            this.AddArea(0, n2 - 4 + 6, n5 - 11 + 35, n2 + 3 + 6, n5 - 3 + 35, i);
                            this.TREE.setColor(this.COLORS[12]);
                            this.TREE.drawLine(n2 - 2, n5 - 7, n2 + 2, n5 - 7);
                            if (this.NODEI[i][3] == 0) {
                                this.TREE.drawLine(n2, n5 - 9, n2, n5 - 5);
                            }
                        }
                        else {
                            this.TREE.drawLine(n2, n5 - 11, n2, n5 - 7);
                            this.TREE.drawLine(n2, n5 - 7, n2 + 4, n5 - 7);
                        }
                        this.TREE.setFont(this.FONTS[this.NODEFONT]);
                        this.fontMetrics = this.getFontMetrics(this.FONTS[this.NODEFONT]);
                        this.StrWidth = this.fontMetrics.stringWidth(this.NODET[i][0]);
                        if (this.NODEI[i][5] == 1) {
                            this.TREE.setColor(this.COLORS[this.HBGCOLOR]);
                            this.TREE.fillRect(n2 + 25, n5 - 13, this.StrWidth + 4, 16);
                            this.TREE.setColor(this.COLORS[0]);
                            this.TREE.drawRect(n2 + 25, n5 - 13, this.StrWidth + 4, 16);
                            this.TREE.setColor(this.COLORS[11]);
                            this.TREE.drawLine(n2 + 25, n5 + 3, n2 + 29 + this.StrWidth, n5 + 3);
                            this.TREE.drawLine(n2 + 29 + this.StrWidth, n5 + 3, n2 + 29 + this.StrWidth, n5 - 13);
                            this.TREE.setColor(this.COLORS[this.HTXTCOLOR]);
                        }
                        else {
                            this.TREE.setColor(this.COLORS[this.TXTCOLOR]);
                        }
                        this.TREE.drawString(this.NODET[i][0], n2 + 28, n5);
                        if (this.NODEI[i][3] == 1) {
                            this.TREE.drawImage(this.NODE_IMG[this.NODEI[i][6]], n2 + 9, n5 - 12, this);
                        }
                        else {
                            this.TREE.drawImage(this.NODE_IMG[this.NODEI[i][1]], n2 + 9, n5 - 12, this);
                        }
                        this.AddArea(1, n2 + 28 + 6, n5 - 12 + 35, n2 + 28 + this.StrWidth + 6, n5 + 35, i);
                        if (n2 + 28 + this.StrWidth + 6 > this.LARGEST_X) {
                            this.LARGEST_X = n2 + 28 + this.StrWidth + 6;
                        }
                        if (this.NODEI[i][4] > -1) {
                            if (this.NODEI[i][5] == 2) {
                                this.TREE.setColor(this.COLORS[this.HBGCOLOR]);
                                this.TREE.fillRect(n2 + 31 + this.StrWidth, n5 - 13, 16, 16);
                                this.TREE.setColor(this.COLORS[0]);
                                this.TREE.drawRect(n2 + 31 + this.StrWidth, n5 - 13, 16, 16);
                                this.TREE.setColor(this.COLORS[11]);
                                this.TREE.drawLine(n2 + 31 + this.StrWidth, n5 + 3, n2 + 47 + this.StrWidth, n5 + 3);
                                this.TREE.drawLine(n2 + 47 + this.StrWidth, n5 - 13, n2 + 47 + this.StrWidth, n5 + 3);
                            }
                            this.TREE.drawImage(this.NODE_IMG[this.NODEI[i][4]], n2 + 32 + this.StrWidth, n5 - 12, this);
                            this.AddArea(2, n2 + 32 + this.StrWidth + 6, n5 - 12 + 35, n2 + 46 + this.StrWidth + 6, n5 + 2 + 35, i);
                            if (n2 + 46 + this.StrWidth + 6 > this.LARGEST_X) {
                                this.LARGEST_X = n2 + 46 + this.StrWidth + 6;
                            }
                        }
                    }
                    n3 = n5;
                    if (n3 < 0) {
                        n3 = 0;
                    }
                    this.y += 16;
                    if (this.NODEI[i][3] == 1) {
                        int j = 0;
                        this.j = 0;
                        do {
                            if (this.NODET[i][0].equals(this.PARENT[this.j])) {
                                j = this.j;
                            }
                            ++this.j;
                        } while (this.j < this.NO_PARENTS && j == 0);
                        if (j > 0) {
                            this.DrawTheLeafs(j, n2 + 16);
                        }
                    }
                }
            }
        }
    }
    
    public void checkScroll() {
        if (this.LARGEST_X + this.xoffset < this.MAX_X) {
            this.hscrollbar.setVisible(false);
            this.xoffset = 0;
            this.hscrollbar.setValue(0);
        }
        else {
            this.hscrollbar.setMaximum((this.LARGEST_X + this.xoffset - 3 * this.WIDTH / 4 + 25) / 10);
            this.hscrollbar.setVisible(true);
        }
        if (this.NODES_DISP < this.MAX_LINES) {
            this.vscrollbar.setVisible(false);
            this.yoffset = 0;
            this.vscrollbar.setValue(0);
        }
        else {
            this.vscrollbar.setMaximum(this.NODES_DISP - this.MAX_LINES + 1);
            this.vscrollbar.setVisible(true);
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target instanceof Scrollbar) {
            if (this.vscrollbar.isVisible()) {
                this.yoffset = 16 * this.vscrollbar.getValue();
                this.repaint();
            }
            if (this.hscrollbar.isVisible()) {
                this.xoffset = 10 * this.hscrollbar.getValue();
                this.repaint();
            }
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void init() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.WIDTH = this.getSize().width;
        this.HEIGHT = this.getSize().height;
        this.xoffset = 0;
        this.yoffset = 0;
        this.TITLE = this.parmgetstr("TITLE", " ");
        this.ICONS = this.parmgetstr("ICONS", " ");
        this.BGIMG = this.parmgetstr("BGIMG", " ");
        this.FONTS = new Font[11];
        this.i = 1;
        while (this.i < 11) {
            this.FT = this.parmgetstr("FONT" + this.i, "Courier|N");
            this.st = new StringTokenizer(this.FT, "|");
            if (this.st.hasMoreTokens()) {
                this.Fonttype = this.st.nextToken();
            }
            else {
                this.Fonttype = "Courier";
            }
            if (this.st.hasMoreTokens()) {
                this.Fontstyle = this.st.nextToken();
            }
            else {
                this.Fontstyle = "N";
            }
            if (this.Fontstyle.equals("B")) {
                this.FONTS[this.i] = new Font(this.Fonttype, 1, 12);
            }
            else if (this.Fontstyle.equals("I")) {
                this.FONTS[this.i] = new Font(this.Fonttype, 2, 12);
            }
            else if (this.Fontstyle.equals("BI") || this.Fontstyle.equals("IB")) {
                this.FONTS[this.i] = new Font(this.Fonttype, 3, 12);
            }
            else {
                this.FONTS[this.i] = new Font(this.Fonttype, 0, 12);
            }
            ++this.i;
        }
        this.FONTS[0] = new Font("Helvetica", 0, 12);
        this.COLORS = new Color[13];
        this.i = 0;
        while (this.i < 10) {
            this.SC = this.getParameter("COLOR" + (this.i + 1));
            if (this.SC == null) {
                if (this.i == 0) {
                    this.SC = "0|0|0";
                }
                else {
                    this.SC = "255|255|255";
                }
            }
            this.st = new StringTokenizer(this.SC, "|");
            this.COLORS[this.i + 1] = new Color(Math.abs(Integer.parseInt(this.st.nextToken())), Math.abs(Integer.parseInt(this.st.nextToken())), Math.abs(Integer.parseInt(this.st.nextToken())));
            ++this.i;
        }
        this.COLORS[0] = new Color(255, 255, 255);
        this.COLORS[11] = new Color(148, 148, 148);
        this.COLORS[12] = new Color(0, 0, 0);
        this.BGCOLOR = this.parmgetint("BGCOLOR", 0, 1, 10);
        this.TXTCOLOR = this.parmgetint("TXTCOLOR", 12, 1, 10);
        this.HBGCOLOR = this.parmgetint("HBGCOLOR", 11, 1, 10);
        this.HTXTCOLOR = this.parmgetint("HTXTCOLOR", this.TXTCOLOR, 1, 10);
        this.TITLEFONT = this.parmgetint("TITLEFONT", 0, 0, 9);
        this.NODEFONT = this.parmgetint("NODEFONT", 0, 0, 9);
        this.NODE_IMG = new Image[10];
        this.BG_IMG = this.getImage(this.getCodeBase(), this.BGIMG);
        this.tempimage = this.getImage(this.getCodeBase(), this.ICONS);
        this.i = 0;
        while (this.i < 10) {
            this.NODE_IMG[this.i] = this.createImage(new FilteredImageSource(this.tempimage.getSource(), new CropImageFilter(0, this.i * 14, 14, 14)));
            ++this.i;
        }
        this.NO_NODES = 0;
        this.NO_PARENTS = 1;
        this.TEMPARRAY = new String[1000];
        this.TEMPSTR = "ROOT|Sirius|0|http://www.net800.co.uk/netstart/sirius/|NewTarget|http://www.net800.co.uk/netstart/sirius/|NewTarget|1";
        do {
            this.TEMPARRAY[this.NO_NODES] = this.TEMPSTR;
            ++this.NO_NODES;
            this.TEMPSTR = this.getParameter("NODE" + this.NO_NODES);
        } while (this.TEMPSTR != null && this.NO_NODES < 1000);
        this.PARENT = new String[this.NO_NODES];
        this.NODET = new String[this.NO_NODES][5];
        this.NODEI = new int[this.NO_NODES][7];
        this.PARENT[0] = "ROOT";
        this.i = 0;
        while (this.i < this.NO_NODES) {
            if (this.i == 0) {
                this.TEMPARRAY[0] = "ROOT|Sirius Home|0|http://www.net800.co.uk/netstart/sirius/|NewTarget| | |0";
            }
            this.st = new StringTokenizer(this.TEMPARRAY[this.i], "|");
            if (this.st.hasMoreTokens()) {
                this.TEMPPARENT = this.st.nextToken();
            }
            if (this.st.hasMoreTokens()) {
                this.NODET[this.i][0] = this.st.nextToken();
            }
            if (this.st.hasMoreTokens()) {
                this.NODEI[this.i][1] = Integer.parseInt(this.st.nextToken());
            }
            if (this.st.hasMoreTokens()) {
                this.NODET[this.i][1] = this.st.nextToken();
            }
            if (this.st.hasMoreTokens()) {
                this.NODET[this.i][2] = this.st.nextToken();
            }
            if (this.st.hasMoreTokens()) {
                this.NODET[this.i][3] = this.st.nextToken();
            }
            if (this.st.hasMoreTokens()) {
                this.NODET[this.i][4] = this.st.nextToken();
            }
            if (this.st.hasMoreTokens()) {
                this.NODEI[this.i][2] = Integer.parseInt(this.st.nextToken());
            }
            if (this.st.hasMoreTokens()) {
                this.NODEI[this.i][4] = Integer.parseInt(this.st.nextToken());
            }
            else {
                this.NODEI[this.i][4] = -1;
            }
            if (this.st.hasMoreTokens()) {
                this.NODEI[this.i][6] = Integer.parseInt(this.st.nextToken());
            }
            else {
                this.NODEI[this.i][6] = this.NODEI[this.i][1];
            }
            this.NODEI[this.i][3] = 0;
            this.NODEI[this.i][5] = 0;
            this.found = false;
            this.end = false;
            this.j = 0;
            do {
                if (this.TEMPPARENT.equals(this.PARENT[this.j])) {
                    this.found = true;
                }
                ++this.j;
                if (this.j >= this.NO_PARENTS) {
                    this.end = true;
                }
            } while (!this.found && !this.end);
            --this.j;
            if (this.found) {
                this.NODEI[this.i][0] = this.j;
            }
            else {
                this.PARENT[this.NO_PARENTS] = this.TEMPPARENT;
                this.NODEI[this.i][0] = this.NO_PARENTS;
                ++this.NO_PARENTS;
            }
            ++this.i;
        }
        this.Area = new int[1000][6];
        this.i = 0;
        while (this.i < 1000) {
            this.Area[this.i][0] = -1;
            ++this.i;
        }
        this.Linset = 6;
        this.Rinset = 5;
        this.Tinset = 35;
        this.Binset = 5;
        this.setLayout(this.layout);
        (this.vscrollbar = new Scrollbar(1, 0, 1, 0, this.NO_NODES)).setPageIncrement(1);
        this.add("East", this.vscrollbar);
        (this.hscrollbar = new Scrollbar(0, 0, 1, 0, 10)).setPageIncrement(1);
        this.add("South", this.hscrollbar);
        this.MAX_LINES = (this.HEIGHT - 40) / 16;
        this.MAX_X = this.WIDTH - 11;
    }
    
    public Insets insets() {
        return new Insets(this.Tinset, this.Linset, this.Binset, this.Rinset);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        boolean b = false;
        this.k = 0;
        while (this.k < this.AreaCount) {
            this.NODEI[this.Area[this.k][5]][5] = 0;
            ++this.k;
        }
        this.k = 0;
        do {
            if (x > this.Area[this.k][1] && x < this.Area[this.k][3] && y > this.Area[this.k][2] && y < this.Area[this.k][4]) {
                b = true;
                switch (this.Area[this.k][0]) {
                    case 1: {
                        this.NODEI[this.Area[this.k][5]][5] = 1;
                        break;
                    }
                    case 2: {
                        this.NODEI[this.Area[this.k][5]][5] = 2;
                        break;
                    }
                }
            }
            ++this.k;
        } while (!b && this.k < this.AreaCount);
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        boolean b = false;
        this.k = 0;
        do {
            if (x > this.Area[this.k][1] && x < this.Area[this.k][3] && y > this.Area[this.k][2] && y < this.Area[this.k][4]) {
                b = true;
                switch (this.Area[this.k][0]) {
                    case 0: {
                        if (this.NODEI[this.Area[this.k][5]][3] == 0) {
                            this.NODEI[this.Area[this.k][5]][3] = 1;
                            break;
                        }
                        this.NODEI[this.Area[this.k][5]][3] = 0;
                        break;
                    }
                    case 1: {
                        if (this.NODET[this.Area[this.k][5]][1] != null) {
                            try {
                                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.NODET[this.Area[this.k][5]][1]), this.NODET[this.Area[this.k][5]][2]);
                            }
                            catch (MalformedURLException ex) {
                                ex.printStackTrace();
                            }
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (this.NODET[this.Area[this.k][5]][3] != null) {
                            try {
                                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.NODET[this.Area[this.k][5]][3]), this.NODET[this.Area[this.k][5]][4]);
                            }
                            catch (MalformedURLException ex2) {
                                ex2.printStackTrace();
                            }
                            break;
                        }
                        break;
                    }
                }
            }
            ++this.k;
        } while (!b && this.k < this.AreaCount);
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        mouseEvent.getX();
        mouseEvent.getY();
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        this.CreateBG();
        if (this.FIRST < 1) {
            this.buffer = this.createImage(this.WIDTH, this.HEIGHT);
            this.pad = this.buffer.getGraphics();
            this.FIRST = 1;
        }
        this.pad.drawImage(this.buffer2, 0, 0, this);
        this.CreateTree();
        this.pad.drawImage(this.buffer3, 6, 35, this);
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    public int parmgetint(final String s, final int n, final int n2, final int n3) {
        final String parameter = this.getParameter(s);
        int int1;
        if (parameter == null) {
            int1 = n;
        }
        else {
            int1 = Integer.parseInt(parameter);
            if (int1 > n3) {
                int1 = n3;
            }
            if (int1 < n2) {
                int1 = n2;
            }
        }
        return int1;
    }
    
    public String parmgetstr(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        String s3;
        if (parameter == null) {
            s3 = s2;
        }
        else {
            s3 = parameter;
        }
        return s3;
    }
    
    public void start() {
        this.hscrollbar.setVisible(false);
        this.vscrollbar.setVisible(false);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
