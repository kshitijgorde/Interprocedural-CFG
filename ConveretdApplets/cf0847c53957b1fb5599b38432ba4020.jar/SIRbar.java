import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.applet.AppletContext;
import java.util.StringTokenizer;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SIRbar extends Applet implements MouseMotionListener, MouseListener
{
    boolean started;
    int[] pixs;
    int index;
    int i;
    int j;
    String sTemp;
    String sTemp2;
    StringTokenizer st;
    static AppletContext APPLETCONTEXT;
    int WIDTH;
    int HEIGHT;
    Color SBGCOLOR;
    Color[][] barcolor;
    Color[] Titlecolor;
    Color baroutline;
    Color axiscolor;
    Color floorcolor;
    Color gridColor;
    Color Ycolor;
    Color labelcolor;
    Color[] Textcolor;
    Font TestFont;
    Font Yfont;
    Font labelfont;
    Font[] Titlefont;
    Font[] Textfont;
    int[][] Titlexy;
    int[][] ImageXY;
    int[][] TextXY;
    int labelOrientation;
    boolean outline;
    double[][] value;
    double chartScale;
    double chartStartX;
    String message;
    String[] labels;
    String[] Text;
    Image[] Images;
    boolean[] BoolArray;
    int[] IntArray;
    String[] StringArray;
    int[][] PosArray;
    Font[] FontArray;
    Color[] ColorArray;
    boolean[][] displayValue;
    String[][][] BarURL;
    int[][][] BarArea;
    public Image buffer;
    Graphics pad;
    
    public SIRbar() {
        this.started = false;
        this.index = 0;
    }
    
    public void init() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        SIRbar.APPLETCONTEXT = this.getAppletContext();
        new Evaluation();
        this.sTemp = this.getParameter("STARTUPCOLOR");
        this.setBackground(this.SBGCOLOR = SIRdata.verifyclr(this.sTemp, "255,255,255", ","));
        this.BoolArray = new boolean[5];
        this.IntArray = new int[11];
        this.StringArray = new String[13];
        this.PosArray = new int[13][2];
        this.FontArray = new Font[15];
        this.ColorArray = new Color[20];
        this.i = 0;
        while (this.i < 11) {
            this.IntArray[this.i] = 0;
            ++this.i;
        }
    }
    
    public void startUp(final Graphics graphics) {
        this.started = true;
        this.sTemp = this.getParameter("LOADINGMESSAGE");
        final String verifystr = SIRdata.verifystr(this.sTemp, "Loading Please Wait...");
        final FontMetrics fontMetrics = this.getFontMetrics(graphics.getFont());
        this.sTemp = this.getParameter("STEXTCOLOR");
        graphics.setColor(SIRdata.verifyclr(this.sTemp, "0,0,0", ","));
        graphics.drawString(verifystr, (this.size().width - fontMetrics.stringWidth(verifystr)) / 2, (this.size().height + fontMetrics.getHeight()) / 2);
        this.WIDTH = this.getSize().width;
        this.HEIGHT = this.getSize().height;
        this.BoolArray = new boolean[5];
        this.IntArray = new int[11];
        this.StringArray = new String[13];
        this.PosArray = new int[13][2];
        this.FontArray = new Font[15];
        this.ColorArray = new Color[20];
        this.sTemp = this.getParameter("3D");
        this.BoolArray[0] = SIRdata.verifybool(this.sTemp, "false");
        this.sTemp = this.getParameter("grid");
        this.BoolArray[1] = SIRdata.verifybool(this.sTemp, "false");
        this.sTemp = this.getParameter("axis");
        this.BoolArray[2] = SIRdata.verifybool(this.sTemp, "false");
        this.sTemp = this.getParameter("xlabels");
        this.BoolArray[3] = SIRdata.verifybool(this.sTemp, "false");
        this.sTemp = this.getParameter("outline");
        this.BoolArray[4] = SIRdata.verifybool(this.sTemp, "false");
        this.sTemp = this.getParameter("nBars");
        this.IntArray[0] = SIRdata.verifyint(this.sTemp);
        this.sTemp = this.getParameter("nCols");
        this.IntArray[1] = SIRdata.verifyint(this.sTemp);
        this.sTemp = this.getParameter("hSpace");
        this.IntArray[2] = SIRdata.verifyint(this.sTemp);
        this.sTemp = this.getParameter("nSeries");
        this.IntArray[3] = SIRdata.verifyint(this.sTemp);
        this.sTemp = this.getParameter("barwidth");
        this.IntArray[4] = SIRdata.verifyint(this.sTemp);
        this.sTemp = this.getParameter("gridxpos");
        this.IntArray[5] = SIRdata.verifyint(this.sTemp);
        this.sTemp = this.getParameter("gridypos");
        this.IntArray[6] = SIRdata.verifyint(this.sTemp);
        this.sTemp = this.getParameter("depth3D");
        this.IntArray[7] = SIRdata.verifyint(this.sTemp);
        this.sTemp = this.getParameter("ndecplaces");
        this.IntArray[8] = SIRdata.verifyint(this.sTemp);
        this.sTemp = this.getParameter("labelOrientation");
        this.IntArray[9] = SIRdata.verifyint(this.sTemp);
        this.sTemp = this.getParameter("labelsX");
        this.IntArray[10] = SIRdata.verifyint(this.sTemp);
        this.sTemp = this.getParameter("chartScale");
        this.chartScale = SIRdata.verifydbl(this.sTemp);
        this.sTemp = this.getParameter("chartStartX");
        this.chartStartX = SIRdata.verifydbl(this.sTemp);
        this.i = 13;
        while (this.i < 15) {
            this.sTemp = this.getParameter("font" + (this.i + 1));
            this.FontArray[this.i] = SIRdata.verifyfnt(this.sTemp, "Courier,N,12", ",");
            ++this.i;
        }
        this.i = 13;
        while (this.i < 19) {
            this.sTemp = this.getParameter("color" + (this.i + 1));
            this.ColorArray[this.i] = SIRdata.verifyclr(this.sTemp, "255,255,255", ",");
            ++this.i;
        }
        this.ColorArray[19] = this.SBGCOLOR;
        this.labels = new String[this.IntArray[0]];
        this.i = 0;
        while (this.i < this.IntArray[0]) {
            this.sTemp = this.getParameter("label" + (this.i + 1));
            this.labels[this.i] = SIRdata.verifystr(this.sTemp, "");
            ++this.i;
        }
        this.sTemp = this.getParameter("title");
        if (this.sTemp != "" & this.sTemp != null) {
            this.st = new StringTokenizer(this.sTemp, ",");
            if (this.st.hasMoreTokens()) {
                this.StringArray[10] = this.st.nextToken();
            }
            else {
                this.StringArray[10] = "";
            }
            if (this.st.hasMoreTokens()) {
                this.PosArray[10][0] = Integer.parseInt(this.st.nextToken());
            }
            else {
                this.PosArray[10][0] = 0;
            }
            if (this.st.hasMoreTokens()) {
                this.PosArray[10][1] = Integer.parseInt(this.st.nextToken());
            }
            else {
                this.PosArray[10][1] = 0;
            }
            this.sTemp2 = "";
            if (this.st.hasMoreTokens()) {
                this.sTemp2 += this.st.nextToken();
            }
            else {
                this.sTemp2 += "Courier";
            }
            if (this.st.hasMoreTokens()) {
                this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
            }
            else {
                this.sTemp2 = this.sTemp2 + "|" + "N";
            }
            if (this.st.hasMoreTokens()) {
                this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
            }
            else {
                this.sTemp2 = this.sTemp2 + "|" + "12";
            }
            this.FontArray[10] = SIRdata.verifyfnt(this.sTemp2, "Courier|N|12", "|");
            this.sTemp2 = "";
            if (this.st.hasMoreTokens()) {
                this.sTemp2 += this.st.nextToken();
            }
            else {
                this.sTemp2 += "0";
            }
            if (this.st.hasMoreTokens()) {
                this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
            }
            else {
                this.sTemp2 = this.sTemp2 + "|" + "0";
            }
            if (this.st.hasMoreTokens()) {
                this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
            }
            else {
                this.sTemp2 = this.sTemp2 + "|" + "0";
            }
            this.ColorArray[10] = SIRdata.verifyclr(this.sTemp2, "0|0|0", "|");
        }
        else {
            this.StringArray[10] = "";
        }
        this.sTemp = this.getParameter("xtitle");
        if (this.sTemp != "" & this.sTemp != null) {
            this.st = new StringTokenizer(this.sTemp, ",");
            if (this.st.hasMoreTokens()) {
                this.StringArray[11] = this.st.nextToken();
            }
            else {
                this.StringArray[11] = "";
            }
            if (this.st.hasMoreTokens()) {
                this.PosArray[11][0] = Integer.parseInt(this.st.nextToken());
            }
            else {
                this.PosArray[11][0] = 0;
            }
            if (this.st.hasMoreTokens()) {
                this.PosArray[11][1] = Integer.parseInt(this.st.nextToken());
            }
            else {
                this.PosArray[11][1] = 0;
            }
            this.sTemp2 = "";
            if (this.st.hasMoreTokens()) {
                this.sTemp2 += this.st.nextToken();
            }
            else {
                this.sTemp2 += "Courier";
            }
            if (this.st.hasMoreTokens()) {
                this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
            }
            else {
                this.sTemp2 = this.sTemp2 + "|" + "N";
            }
            if (this.st.hasMoreTokens()) {
                this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
            }
            else {
                this.sTemp2 = this.sTemp2 + "|" + "12";
            }
            this.FontArray[11] = SIRdata.verifyfnt(this.sTemp2, "Courier|N|12", "|");
            this.sTemp2 = "";
            if (this.st.hasMoreTokens()) {
                this.sTemp2 += this.st.nextToken();
            }
            else {
                this.sTemp2 += "0";
            }
            if (this.st.hasMoreTokens()) {
                this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
            }
            else {
                this.sTemp2 = this.sTemp2 + "|" + "0";
            }
            if (this.st.hasMoreTokens()) {
                this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
            }
            else {
                this.sTemp2 = this.sTemp2 + "|" + "0";
            }
            this.ColorArray[11] = SIRdata.verifyclr(this.sTemp2, "0|0|0", "|");
        }
        else {
            this.StringArray[11] = "";
        }
        this.sTemp = this.getParameter("ytitle");
        if (this.sTemp != "" & this.sTemp != null) {
            this.st = new StringTokenizer(this.sTemp, ",");
            if (this.st.hasMoreTokens()) {
                this.StringArray[12] = this.st.nextToken();
            }
            else {
                this.StringArray[12] = "";
            }
            if (this.st.hasMoreTokens()) {
                this.PosArray[12][0] = Integer.parseInt(this.st.nextToken());
            }
            else {
                this.PosArray[12][0] = 0;
            }
            if (this.st.hasMoreTokens()) {
                this.PosArray[12][1] = Integer.parseInt(this.st.nextToken());
            }
            else {
                this.PosArray[12][1] = 0;
            }
            this.sTemp2 = "";
            if (this.st.hasMoreTokens()) {
                this.sTemp2 += this.st.nextToken();
            }
            else {
                this.sTemp2 += "Courier";
            }
            if (this.st.hasMoreTokens()) {
                this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
            }
            else {
                this.sTemp2 = this.sTemp2 + "|" + "N";
            }
            if (this.st.hasMoreTokens()) {
                this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
            }
            else {
                this.sTemp2 = this.sTemp2 + "|" + "12";
            }
            this.FontArray[12] = SIRdata.verifyfnt(this.sTemp2, "Courier|N|12", "|");
            this.sTemp2 = "";
            if (this.st.hasMoreTokens()) {
                this.sTemp2 += this.st.nextToken();
            }
            else {
                this.sTemp2 += "0";
            }
            if (this.st.hasMoreTokens()) {
                this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
            }
            else {
                this.sTemp2 = this.sTemp2 + "|" + "0";
            }
            if (this.st.hasMoreTokens()) {
                this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
            }
            else {
                this.sTemp2 = this.sTemp2 + "|" + "0";
            }
            this.ColorArray[12] = SIRdata.verifyclr(this.sTemp2, "0|0|0", "|");
        }
        else {
            this.StringArray[12] = "";
        }
        this.i = 0;
        while (this.i < 10) {
            this.sTemp = this.getParameter("text" + (this.i + 1));
            if (this.sTemp != "" & this.sTemp != null) {
                this.st = new StringTokenizer(this.sTemp, ",");
                if (this.st.hasMoreTokens()) {
                    this.StringArray[this.i] = this.st.nextToken();
                }
                else {
                    this.StringArray[this.i] = "";
                }
                if (this.st.hasMoreTokens()) {
                    this.PosArray[this.i][0] = Integer.parseInt(this.st.nextToken());
                }
                else {
                    this.PosArray[this.i][0] = 0;
                }
                if (this.st.hasMoreTokens()) {
                    this.PosArray[this.i][1] = Integer.parseInt(this.st.nextToken());
                }
                else {
                    this.PosArray[this.i][1] = 0;
                }
                this.sTemp2 = "";
                if (this.st.hasMoreTokens()) {
                    this.sTemp2 += this.st.nextToken();
                }
                else {
                    this.sTemp2 += "Courier";
                }
                if (this.st.hasMoreTokens()) {
                    this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
                }
                else {
                    this.sTemp2 = this.sTemp2 + "|" + "N";
                }
                if (this.st.hasMoreTokens()) {
                    this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
                }
                else {
                    this.sTemp2 = this.sTemp2 + "|" + "12";
                }
                this.FontArray[this.i] = SIRdata.verifyfnt(this.sTemp2, "Courier|N|12", "|");
                this.sTemp2 = "";
                if (this.st.hasMoreTokens()) {
                    this.sTemp2 += this.st.nextToken();
                }
                else {
                    this.sTemp2 += "0";
                }
                if (this.st.hasMoreTokens()) {
                    this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
                }
                else {
                    this.sTemp2 = this.sTemp2 + "|" + "0";
                }
                if (this.st.hasMoreTokens()) {
                    this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
                }
                else {
                    this.sTemp2 = this.sTemp2 + "|" + "0";
                }
                this.ColorArray[this.i] = SIRdata.verifyclr(this.sTemp2, "0|0|0", "|");
            }
            else {
                this.StringArray[this.i] = "";
            }
            ++this.i;
        }
        this.barcolor = new Color[this.IntArray[0]][this.IntArray[3]];
        this.value = new double[this.IntArray[0]][this.IntArray[3]];
        this.BarURL = new String[this.IntArray[0]][this.IntArray[3]][2];
        this.i = 0;
        while (this.i < this.IntArray[0]) {
            this.j = 0;
            while (this.j < this.IntArray[3]) {
                this.sTemp = this.getParameter("bar" + (this.i + 1) + "series" + (this.j + 1));
                if (this.sTemp != "" & this.sTemp != null) {
                    this.st = new StringTokenizer(this.sTemp, ",");
                    if (this.st.hasMoreTokens()) {
                        this.value[this.i][this.j] = (float)(Object)Double.valueOf(this.st.nextToken());
                    }
                    else {
                        this.value[this.i][0] = 0.0;
                    }
                    this.sTemp2 = "";
                    if (this.st.hasMoreTokens()) {
                        this.sTemp2 += this.st.nextToken();
                    }
                    else {
                        this.sTemp2 += "0";
                    }
                    if (this.st.hasMoreTokens()) {
                        this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
                    }
                    else {
                        this.sTemp2 = this.sTemp2 + "|" + "0";
                    }
                    if (this.st.hasMoreTokens()) {
                        this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
                    }
                    else {
                        this.sTemp2 = this.sTemp2 + "|" + "0";
                    }
                    this.barcolor[this.i][this.j] = SIRdata.verifyclr(this.sTemp2, "0|0|0", "|");
                    if (this.st.hasMoreTokens()) {
                        this.BarURL[this.i][this.j][0] = this.st.nextToken();
                    }
                    else {
                        this.BarURL[this.i][this.j][0] = "";
                    }
                    if (this.st.hasMoreTokens()) {
                        this.BarURL[this.i][this.j][1] = this.st.nextToken();
                    }
                    else {
                        this.BarURL[this.i][this.j][1] = "_self";
                    }
                }
                else {
                    this.value[this.i][this.j] = 0.0;
                    this.barcolor[this.i][this.j] = SIRdata.verifyclr("0|0|0", "0|0|0", "|");
                }
                ++this.j;
            }
            ++this.i;
        }
        this.displayValue = new boolean[this.IntArray[0]][this.IntArray[3]];
        this.BarArea = new int[this.IntArray[0]][this.IntArray[3]][4];
        this.CalcArea(this.IntArray[0], this.IntArray[3], this.IntArray[4], this.IntArray[2], this.IntArray[6], this.IntArray[5], this.chartScale, this.chartStartX);
        this.Images = new Image[10];
        this.ImageXY = new int[10][2];
        this.i = 0;
        while (this.i < 10) {
            this.sTemp = this.getParameter("image" + (this.i + 1));
            if (this.sTemp != "" & this.sTemp != null) {
                this.st = new StringTokenizer(this.sTemp, ",");
                if (this.st.hasMoreTokens()) {
                    this.Images[this.i] = this.downloadImage(this.st.nextToken());
                }
                if (this.st.hasMoreTokens()) {
                    this.ImageXY[this.i][0] = Integer.parseInt(this.st.nextToken());
                }
                else {
                    this.ImageXY[this.i][0] = 0;
                }
                if (this.st.hasMoreTokens()) {
                    this.ImageXY[this.i][1] = Integer.parseInt(this.st.nextToken());
                }
                else {
                    this.ImageXY[this.i][1] = 0;
                }
            }
            ++this.i;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (!this.started) {
            this.startUp(graphics);
        }
        this.buffer = this.createImage(this.WIDTH, this.HEIGHT);
        this.pad = this.buffer.getGraphics();
        barchart.barchart(this.buffer, this.value, this.displayValue, this.labels, this.ImageXY, this.Images, this.chartStartX, this.chartScale, this.barcolor, this.BoolArray, this.IntArray, this.StringArray, this.PosArray, this.FontArray, this.ColorArray);
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.i = 0;
        while (this.i < this.IntArray[0]) {
            this.j = 0;
            while (this.j < this.IntArray[3]) {
                if (x > this.BarArea[this.i][this.j][0] && x < this.BarArea[this.i][this.j][1] && y > this.BarArea[this.i][this.j][3] && y < this.BarArea[this.i][this.j][2] && this.BarURL[this.i][this.j][0] != null) {
                    if (!this.BarURL[this.i][this.j][0].equals("")) {
                        try {
                            this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.BarURL[this.i][this.j][0]), this.BarURL[this.i][this.j][1]);
                        }
                        catch (MalformedURLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                ++this.j;
            }
            ++this.i;
        }
        this.paint(this.getGraphics());
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.i = 0;
        while (this.i < this.IntArray[0]) {
            this.j = 0;
            while (this.j < this.IntArray[3]) {
                if (x > this.BarArea[this.i][this.j][0] && x < this.BarArea[this.i][this.j][1] && y > this.BarArea[this.i][this.j][3] && y < this.BarArea[this.i][this.j][2]) {
                    this.displayValue[this.i][this.j] = true;
                }
                else {
                    this.displayValue[this.i][this.j] = false;
                }
                ++this.j;
            }
            ++this.i;
        }
        this.paint(this.getGraphics());
    }
    
    public Image downloadImage(final String s) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image image = this.getImage(this.getCodeBase(), s);
        mediaTracker.addImage(image, 0);
        try {
            this.showStatus("Loading Image...");
            mediaTracker.waitForID(0);
            this.showStatus("");
        }
        catch (InterruptedException ex) {
            return this.createImage(this.size().width, this.size().height);
        }
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        this.pixs = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, this.pixs, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex2) {
            System.err.println("interrupted waiting for pixels!");
            this.showStatus("Image loading error");
            return this.createImage(this.size().width, this.size().height);
        }
        if ((pixelGrabber.status() & 0x80) != 0x0) {
            System.err.println("image fetch aborted or errored");
            this.showStatus("Image loading error");
            return this.createImage(this.size().width, this.size().height);
        }
        final Image image2 = this.createImage(new MemoryImageSource(width, height, this.pixs, 0, width));
        this.index = 0;
        return image2;
    }
    
    public void CalcArea(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final double n7, final double n8) {
        this.i = 0;
        while (this.i < n) {
            this.j = 0;
            while (this.j < n2) {
                final double doubleValue = new Double(n4);
                int n9;
                if (n8 < 0.0) {
                    n9 = n6 - (int)Math.round(doubleValue * n8 / n7);
                }
                else {
                    n9 = n6;
                }
                final int n10 = (int)Math.round(new Double(n4) * (this.value[this.i][this.j] - n8) / n7) + n6 - n9;
                final int n11 = n5 - 5 - (this.i * n2 + this.j) * (n3 + 5) - this.i * 5;
                this.BarArea[this.i][this.j][2] = n11;
                this.BarArea[this.i][this.j][3] = n11 - n3;
                if (this.value[this.i][this.j] < 0.0) {
                    this.BarArea[this.i][this.j][0] = n9 + n10;
                    this.BarArea[this.i][this.j][1] = n9;
                }
                else {
                    this.BarArea[this.i][this.j][0] = n9;
                    this.BarArea[this.i][this.j][1] = n9 + n10;
                }
                this.displayValue[this.i][this.j] = false;
                ++this.j;
            }
            ++this.i;
        }
    }
}
