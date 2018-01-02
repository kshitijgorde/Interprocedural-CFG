import java.util.Enumeration;
import java.net.URLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.FontMetrics;
import java.net.MalformedURLException;
import java.net.URL;
import netscape.javascript.JSObject;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;
import java.util.StringTokenizer;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PiechartApplet extends Applet implements MouseMotionListener, MouseListener
{
    boolean started;
    int[] pixs;
    int index;
    int i;
    int j;
    String sTemp;
    String sTemp2;
    StringTokenizer st;
    private Vector parameters;
    int WIDTH;
    int HEIGHT;
    Color SBGCOLOR;
    Color[] Titlecolor;
    Color labelcolor;
    Color[] Textcolor;
    Color[][] segmentcolor;
    Font labelfont;
    Font[] Titlefont;
    Font[] Textfont;
    int[][] Titlexy;
    int[][] ImageXY;
    int[][] TextXY;
    int labelOrientation;
    int nPies;
    int maxSegs;
    int[][][] segmentArea;
    int[][] Pie;
    int zangle;
    double[][] value;
    String message;
    String[][] labels;
    String[] Text;
    String[] legendlabels;
    String legendtitle;
    String[][][] segmentURL;
    Image[] Images;
    boolean[] BoolArray;
    int[] IntArray;
    String[] StringArray;
    int[][] PosArray;
    Font[] FontArray;
    Color[] ColorArray;
    boolean[][] displayValue;
    public Image buffer;
    Graphics pad;
    private static ImageObserver Iobs;
    
    public static boolean verifybool(String substring, final String s) {
        boolean b = false;
        if (substring == null || substring.length() < 1) {
            substring = s;
        }
        substring = substring.toLowerCase().substring(0, 1);
        if (substring.equals("t")) {
            b = true;
        }
        return b;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.j = 0;
        while (this.j < this.nPies) {
            this.IntArray[0] = this.Pie[this.j][3];
            this.IntArray[3] = this.Pie[this.j][2];
            this.IntArray[5] = this.Pie[this.j][0];
            this.IntArray[6] = this.Pie[this.j][1];
            this.IntArray[1] = this.Pie[this.j][4];
            final int n = this.IntArray[3] / 2;
            final int n2 = this.IntArray[5] + this.IntArray[3] / 2;
            final int n3 = this.IntArray[6] + this.zangle * this.IntArray[3] / 180;
            double doubleValue = new Double(n3 - y);
            double doubleValue2 = new Double(x - n2);
            if (doubleValue < 0.0) {
                doubleValue = -doubleValue;
            }
            if (doubleValue2 < 0.0) {
                doubleValue2 = -doubleValue2;
            }
            final double sqrt = Math.sqrt(doubleValue * doubleValue + doubleValue2 * doubleValue2);
            final double doubleValue3 = new Double(n);
            final double n4 = 7.0 * doubleValue3 / 10.0;
            final double n5 = doubleValue2 / sqrt;
            final double n6 = doubleValue / sqrt;
            final int n7 = (int)Math.round(Math.sqrt(doubleValue3 * doubleValue3 * n5 * n5 + n4 * n4 * n6 * n6));
            final int calcDist = this.CalcDist(n3, n2, x, y);
            final int calcAngle = this.CalcAngle(n3, n2, x, y);
            this.i = 0;
            while (this.i < this.IntArray[0]) {
                if (calcAngle > this.segmentArea[this.j][this.i][0] && calcAngle < this.segmentArea[this.j][this.i][1] && calcDist < n7) {
                    if (this.segmentURL[this.j][this.i][0] != null && !this.segmentURL[this.j][this.i][0].equals("")) {
                        if (this.segmentURL[this.j][this.i][1].equals("javascript")) {
                            try {
                                JSObject.getWindow(this).eval(this.segmentURL[this.j][this.i][0]);
                            }
                            catch (Throwable t) {
                                try {
                                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), "javascript:" + this.segmentURL[this.j][this.i][0]));
                                }
                                catch (MalformedURLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                        else {
                            try {
                                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.segmentURL[this.j][this.i][0]), this.segmentURL[this.j][this.i][1]);
                            }
                            catch (MalformedURLException ex2) {
                                ex2.printStackTrace();
                            }
                        }
                    }
                }
                ++this.i;
            }
            ++this.j;
        }
        this.paint(this.getGraphics());
    }
    
    public final void paint(final Graphics graphics) {
        if (!this.started) {
            this.startUp(graphics);
        }
        this.buffer = this.createImage(this.WIDTH, this.HEIGHT);
        (this.pad = this.buffer.getGraphics()).setColor(this.ColorArray[15]);
        this.pad.fillRect(0, 0, this.buffer.getWidth(PiechartApplet.Iobs), this.buffer.getHeight(PiechartApplet.Iobs));
        this.setBackground(this.ColorArray[15]);
        this.j = 0;
        while (this.j < this.nPies) {
            this.IntArray[0] = this.Pie[this.j][3];
            this.IntArray[3] = this.Pie[this.j][2];
            this.IntArray[5] = this.Pie[this.j][0];
            this.IntArray[6] = this.Pie[this.j][1];
            this.IntArray[1] = this.Pie[this.j][4];
            piechart.piechart(this.buffer, this.value[this.j], this.displayValue[this.j], this.labels[this.j], this.ImageXY, this.Images, this.segmentcolor[this.j], this.BoolArray, this.IntArray, this.StringArray, this.PosArray, this.FontArray, this.ColorArray, this.legendlabels, this.zangle);
            ++this.j;
        }
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.j = 0;
        while (this.j < this.nPies) {
            this.IntArray[0] = this.Pie[this.j][3];
            this.IntArray[3] = this.Pie[this.j][2];
            this.IntArray[5] = this.Pie[this.j][0];
            this.IntArray[6] = this.Pie[this.j][1];
            this.IntArray[1] = this.Pie[this.j][4];
            final int n = this.IntArray[3] / 2;
            final int n2 = this.IntArray[5] + this.IntArray[3] / 2;
            final int n3 = this.IntArray[6] + this.zangle * this.IntArray[3] / 180;
            double doubleValue = new Double(n3 - y);
            double doubleValue2 = new Double(x - n2);
            if (doubleValue < 0.0) {
                doubleValue = -doubleValue;
            }
            if (doubleValue2 < 0.0) {
                doubleValue2 = -doubleValue2;
            }
            final double sqrt = Math.sqrt(doubleValue * doubleValue + doubleValue2 * doubleValue2);
            final double doubleValue3 = new Double(n);
            final double n4 = 7.0 * doubleValue3 / 10.0;
            final double n5 = doubleValue2 / sqrt;
            final double n6 = doubleValue / sqrt;
            final int n7 = (int)Math.round(Math.sqrt(doubleValue3 * doubleValue3 * n5 * n5 + n4 * n4 * n6 * n6));
            final int calcDist = this.CalcDist(n3, n2, x, y);
            final int calcAngle = this.CalcAngle(n3, n2, x, y);
            this.i = 0;
            while (this.i < this.IntArray[0]) {
                if (calcAngle > this.segmentArea[this.j][this.i][0] && calcAngle < this.segmentArea[this.j][this.i][1] && calcDist < n7) {
                    this.displayValue[this.j][this.i] = true;
                }
                else {
                    this.displayValue[this.j][this.i] = false;
                }
                ++this.i;
            }
            ++this.j;
        }
        this.paint(this.getGraphics());
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void startUp(final Graphics graphics) {
        this.started = true;
        final String verifystr = verifystr(this.getParameter("LOADINGMESSAGE"), "Loading Please Wait...");
        final FontMetrics fontMetrics = this.getFontMetrics(graphics.getFont());
        graphics.setColor(verifyclr(this.getParameter("STEXTCOLOR"), "0,0,0", ","));
        graphics.drawString(verifystr, (this.size().width - fontMetrics.stringWidth(verifystr)) / 2, (this.size().height + fontMetrics.getHeight()) / 2);
        this.WIDTH = this.getSize().width;
        this.HEIGHT = this.getSize().height;
        this.BoolArray = new boolean[6];
        this.IntArray = new int[11];
        this.StringArray = new String[14];
        this.PosArray = new int[14][2];
        this.FontArray = new Font[16];
        this.ColorArray = new Color[23];
        this.i = 0;
        while (this.i < 14) {
            this.StringArray[this.i] = "";
            ++this.i;
        }
        this.i = 0;
        while (this.i < 16) {
            this.FontArray[this.i] = new Font("Courier", 0, 10);
            ++this.i;
        }
        this.i = 0;
        while (this.i < 23) {
            this.ColorArray[this.i] = new Color(0, 0, 0);
            ++this.i;
        }
        this.i = 0;
        while (this.i < 11) {
            this.IntArray[this.i] = 0;
            ++this.i;
        }
        this.zangle = 30;
        this.sTemp = this.getParameter("chartproperties");
        if (this.sTemp != "" && this.sTemp != null) {
            this.get_chartpropsfromfile(this.sTemp);
        }
        else {
            this.get_chartprops();
        }
        this.sTemp = this.getParameter("chartdata");
        if (this.sTemp != "" && this.sTemp != null) {
            this.get_chartdatafromfile(this.sTemp);
        }
        else {
            this.get_chartdata();
        }
        if (this.zangle > 90) {
            this.zangle = 90;
        }
        if (this.zangle < 1) {
            this.zangle = 1;
        }
        if (!this.BoolArray[0]) {
            this.zangle = 90;
            this.IntArray[7] = 0;
        }
        this.displayValue = new boolean[this.nPies][this.maxSegs];
        this.segmentArea = new int[this.nPies][this.maxSegs][2];
        this.j = 0;
        while (this.j < this.nPies) {
            this.IntArray[0] = this.Pie[this.j][3];
            this.IntArray[3] = this.Pie[this.j][2];
            this.IntArray[5] = this.Pie[this.j][0];
            this.IntArray[6] = this.Pie[this.j][1];
            this.CalcArea(this.IntArray[0], this.IntArray[6], this.IntArray[5], this.IntArray[3], this.j);
            this.i = 0;
            while (this.i < this.maxSegs) {
                this.displayValue[this.j][this.i] = false;
                ++this.i;
            }
            ++this.j;
        }
    }
    
    private void get_chartprops() {
        this.BoolArray[0] = verifybool(this.getParameter("3D"), "false");
        this.BoolArray[3] = verifybool(this.getParameter("Slabels"), "false");
        this.BoolArray[5] = verifybool(this.getParameter("legend"), "false");
        this.nPies = verifyint(this.getParameter("nPies"));
        this.IntArray[7] = verifyint(this.getParameter("depth3D"));
        this.IntArray[8] = verifyint(this.getParameter("ndecplaces"));
        this.IntArray[9] = 5;
        this.zangle = verifyint(this.getParameter("3Dangle"));
        this.i = 13;
        while (this.i < 15) {
            this.FontArray[this.i] = verifyfnt(this.getParameter("font" + (this.i + 1)), "Courier,N,12", ",");
            ++this.i;
        }
        this.i = 13;
        while (this.i < 19) {
            this.ColorArray[this.i] = verifyclr(this.getParameter("color" + (this.i + 1)), "255,255,255", ",");
            ++this.i;
        }
        this.ColorArray[19] = this.SBGCOLOR;
        this.ColorArray[20] = verifyclr(this.getParameter("LegendBackground"), "255,255,255", ",");
        this.ColorArray[21] = verifyclr(this.getParameter("LegendBorder"), "255,255,255", ",");
        this.ColorArray[22] = verifyclr(this.getParameter("LegendtextColor"), "0,0,0", ",");
        this.Pie = new int[this.nPies][5];
        this.maxSegs = 1;
        this.i = 0;
        while (this.i < this.nPies) {
            this.sTemp = this.getParameter("Pie" + (this.i + 1));
            if (this.sTemp != "" & this.sTemp != null) {
                this.st = new StringTokenizer(this.sTemp, ",");
                if (this.st.hasMoreTokens()) {
                    this.Pie[this.i][0] = Integer.parseInt(this.st.nextToken());
                }
                else {
                    this.Pie[this.i][0] = 0;
                }
                if (this.st.hasMoreTokens()) {
                    this.Pie[this.i][1] = Integer.parseInt(this.st.nextToken());
                }
                else {
                    this.Pie[this.i][1] = 0;
                }
                if (this.st.hasMoreTokens()) {
                    this.Pie[this.i][2] = Integer.parseInt(this.st.nextToken());
                }
                else {
                    this.Pie[this.i][2] = 0;
                }
                if (this.st.hasMoreTokens()) {
                    this.Pie[this.i][3] = Integer.parseInt(this.st.nextToken());
                }
                else {
                    this.Pie[this.i][3] = 0;
                }
                if (this.st.hasMoreTokens()) {
                    this.Pie[this.i][4] = Integer.parseInt(this.st.nextToken());
                }
                else {
                    this.Pie[this.i][4] = 0;
                }
                if (this.Pie[this.i][3] > this.maxSegs) {
                    this.maxSegs = this.Pie[this.i][3];
                }
            }
            else {
                this.Pie[this.i][0] = 0;
                this.Pie[this.i][1] = 0;
                this.Pie[this.i][2] = 0;
                this.Pie[this.i][3] = 1;
            }
            ++this.i;
        }
        this.labels = new String[this.nPies][this.maxSegs];
        this.j = 0;
        while (this.j < this.nPies) {
            this.i = 0;
            while (this.i < this.Pie[this.j][3]) {
                this.labels[this.j][this.i] = verifystr(this.getParameter("pie" + (this.j + 1) + "label" + (this.i + 1)), "");
                ++this.i;
            }
            ++this.j;
        }
        this.legendlabels = new String[this.maxSegs];
        this.segmentcolor = new Color[this.nPies][this.maxSegs];
        this.i = 0;
        while (this.i < this.nPies) {
            this.j = 0;
            while (this.j < this.maxSegs) {
                this.legendlabels[this.j] = " ";
                this.segmentcolor[this.i][this.j] = new Color(255, 255, 255);
                ++this.j;
            }
            ++this.i;
        }
        this.i = 0;
        while (this.i < this.maxSegs) {
            this.sTemp = this.getParameter("segment" + (this.i + 1));
            if (this.sTemp != "" & this.sTemp != null) {
                this.st = new StringTokenizer(this.sTemp, "|");
                Color verifyclr = new Color(125, 125, 125);
                this.legendlabels[this.i] = "";
                if (this.st.hasMoreTokens()) {
                    verifyclr = verifyclr(this.st.nextToken(), "125,125,125", ",");
                }
                if (this.st.hasMoreTokens()) {
                    this.legendlabels[this.i] = verifystr(this.st.nextToken(), "");
                }
                this.j = 0;
                while (this.j < this.nPies) {
                    this.segmentcolor[this.j][this.i] = verifyclr;
                    ++this.j;
                }
            }
            ++this.i;
        }
        this.i = 0;
        while (this.i < 11) {
            if (this.i < 10) {
                this.sTemp = this.getParameter("text" + (this.i + 1));
            }
            if (this.i == 10) {
                this.sTemp = this.getParameter("title");
            }
            if (this.sTemp != "" & this.sTemp != null) {
                this.st = new StringTokenizer(this.sTemp, "|");
                if (this.st.hasMoreTokens()) {
                    this.StringArray[this.i] = this.st.nextToken();
                }
                else {
                    this.StringArray[this.i] = "";
                }
                if (this.st.hasMoreTokens()) {
                    this.sTemp2 = this.st.nextToken();
                    this.PosArray[this.i][0] = verifypos(this.sTemp2, 0, ",");
                    this.PosArray[this.i][1] = verifypos(this.sTemp2, 1, ",");
                }
                if (this.st.hasMoreTokens()) {
                    this.FontArray[this.i] = verifyfnt(this.st.nextToken(), "Courier,N,10", ",");
                }
                if (this.st.hasMoreTokens()) {
                    this.ColorArray[this.i] = verifyclr(this.st.nextToken(), "0,0,0", ",");
                }
            }
            ++this.i;
        }
        this.StringArray[13] = this.getParameter("legendtitle");
        this.PosArray[13][0] = verifypos(this.getParameter("legendposition"), 0, ",");
        this.PosArray[13][1] = verifypos(this.getParameter("legendposition"), 1, ",");
        this.FontArray[15] = verifyfnt(this.getParameter("legendfont"), "Courier,N,10", ",");
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
    
    public PiechartApplet() {
        this.started = false;
        this.index = 0;
    }
    
    public int CalcAngle(final int n, final int n2, final int n3, final int n4) {
        int n5 = 0;
        final double doubleValue = new Double(n - n4);
        final double doubleValue2 = new Double(n3 - n2);
        if (doubleValue2 * doubleValue2 > 0.0) {
            double n6 = doubleValue / doubleValue2;
            if (n6 < 0.0) {
                n6 = -n6;
            }
            n5 = (int)Math.round(Math.atan(n6) * 57.29577951308232);
            if (n2 - n3 > 0 && n - n4 > 0) {
                n5 = 180 - n5;
            }
            if (n2 - n3 > 0 && n - n4 < 0) {
                n5 += 180;
            }
            if (n2 - n3 < 0 && n - n4 < 0) {
                n5 = 360 - n5;
            }
        }
        return n5;
    }
    
    public static String verifystr(final String s, final String s2) {
        String s3;
        if (s == null || s.equals("")) {
            s3 = s2;
        }
        else {
            s3 = s;
        }
        return s3;
    }
    
    public int CalcDist(final int n, final int n2, final int n3, final int n4) {
        return (int)Math.round(Math.sqrt(new Double((n - n4) * (n - n4) + (n2 - n3) * (n2 - n3))));
    }
    
    private void get_chartdata() {
        this.value = new double[this.nPies][this.maxSegs];
        this.segmentURL = new String[this.nPies][this.maxSegs][2];
        this.j = 0;
        while (this.j < this.nPies) {
            this.i = 0;
            while (this.i < this.Pie[this.j][3]) {
                this.sTemp = this.getParameter("data" + (this.i + 1) + "series" + (this.j + 1));
                if (this.sTemp != "" & this.sTemp != null) {
                    this.st = new StringTokenizer(this.sTemp, ",");
                    if (this.st.hasMoreTokens()) {
                        this.value[this.j][this.i] = (float)(Object)Double.valueOf(this.st.nextToken());
                    }
                    else {
                        this.value[this.j][this.i] = 0.0;
                    }
                    if (this.value[this.j][this.i] < 0.0) {
                        this.value[this.j][this.i] = 0.0;
                    }
                    if (this.st.hasMoreTokens()) {
                        this.segmentURL[this.j][this.i][0] = this.st.nextToken();
                    }
                    else {
                        this.segmentURL[this.j][this.i][0] = "";
                    }
                    if (this.st.hasMoreTokens()) {
                        this.segmentURL[this.j][this.i][1] = this.st.nextToken();
                    }
                    else {
                        this.segmentURL[this.j][this.i][1] = "_self";
                    }
                }
                else {
                    this.value[this.j][this.i] = 0.0;
                    this.segmentURL[this.j][this.i][0] = " ";
                }
                ++this.i;
            }
            ++this.j;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void CalcArea(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = 0;
        double n7 = 0.0;
        for (int i = 0; i < n; ++i) {
            n7 += this.value[n5][i];
        }
        for (int j = 0; j < n; ++j) {
            final int n8 = (int)Math.round(360.0 * this.value[n5][j] / n7);
            this.segmentArea[n5][j][0] = n6;
            this.segmentArea[n5][j][1] = n6 + n8;
            n6 += n8;
            this.displayValue[n5][j] = false;
        }
    }
    
    public static Color verifyclr(String s, final String s2, final String s3) {
        int abs = 0;
        int abs2 = 0;
        int abs3 = 0;
        if (s == null || s.equals("")) {
            s = s2;
        }
        String trim;
        try {
            trim = s.trim();
        }
        catch (Exception ex) {
            trim = "0|0|0";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(verifystr(trim, s2), s3);
        Color color;
        try {
            if (stringTokenizer.hasMoreTokens()) {
                abs = Math.abs(Integer.parseInt(stringTokenizer.nextToken()));
            }
            if (stringTokenizer.hasMoreTokens()) {
                abs2 = Math.abs(Integer.parseInt(stringTokenizer.nextToken()));
            }
            if (stringTokenizer.hasMoreTokens()) {
                abs3 = Math.abs(Integer.parseInt(stringTokenizer.nextToken()));
            }
            color = new Color(abs, abs2, abs3);
        }
        catch (Exception ex2) {
            color = new Color(0, 0, 0);
        }
        return color;
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
    
    public void init() {
        new Evaluation(this, "2D / 3D Pie Chart, version 3.2 - by Jpowered.", "http://www.jpowered.com/pie_chart/index.htm");
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.sTemp = this.getParameter("STARTUPCOLOR");
        this.setBackground(this.SBGCOLOR = verifyclr(this.sTemp, "255,255,255", ","));
    }
    
    public static int verifyint(final String s) {
        String trim;
        try {
            trim = s.trim();
        }
        catch (Exception ex) {
            trim = "0";
        }
        int int1;
        try {
            int1 = Integer.parseInt(trim);
        }
        catch (Exception ex2) {
            int1 = 0;
        }
        return int1;
    }
    
    public static Font verifyfnt(final String s, final String s2, final String s3) {
        String nextToken = "Courier";
        String upperCase = "N";
        int int1 = 12;
        String trim;
        try {
            trim = s.trim();
        }
        catch (Exception ex) {
            trim = "Courier|N|10";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(verifystr(trim, s2), s3);
        Font font;
        try {
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                upperCase = stringTokenizer.nextToken().toUpperCase();
            }
            if (stringTokenizer.hasMoreTokens()) {
                int1 = Integer.parseInt(stringTokenizer.nextToken());
            }
            if (upperCase.equals("B")) {
                font = new Font(nextToken, 1, int1);
            }
            else if (upperCase.equals("I")) {
                font = new Font(nextToken, 2, int1);
            }
            else if (upperCase.equals("BI") || upperCase.equals("IB")) {
                font = new Font(nextToken, 3, int1);
            }
            else {
                font = new Font(nextToken, 0, int1);
            }
        }
        catch (Exception ex2) {
            font = new Font("Courier", 0, 12);
        }
        return font;
    }
    
    public static double verifydbl(final String s) {
        String trim;
        try {
            trim = s.trim();
        }
        catch (Exception ex) {
            trim = "0.0";
        }
        double n;
        try {
            n = (float)(Object)Double.valueOf(trim);
        }
        catch (Exception ex2) {
            n = 0.0;
        }
        return n;
    }
    
    public static int verifypos(final String s, final int n, final String s2) {
        int n2 = 0;
        String trim;
        try {
            trim = s.trim();
        }
        catch (Exception ex) {
            trim = "0,0";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(trim, s2);
        try {
            if (stringTokenizer.hasMoreTokens()) {
                n2 = Math.abs(Integer.parseInt(stringTokenizer.nextToken()));
            }
            if (n > 0 && stringTokenizer.hasMoreTokens()) {
                n2 = Math.abs(Integer.parseInt(stringTokenizer.nextToken()));
            }
        }
        catch (Exception ex2) {
            n2 = 0;
        }
        return n2;
    }
    
    private void get_chartpropsfromfile(final String s) {
        URL url = null;
        try {
            url = new URL(this.getCodeBase(), s);
        }
        catch (MalformedURLException ex2) {
            System.out.println("Bad URL for File Location : " + s);
        }
        this.parameters = new Vector();
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.connect();
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(openConnection.getInputStream()));
            Label_0104: {
                break Label_0104;
                String line;
                do {
                    this.parameters.addElement(this.sTemp);
                    line = dataInputStream.readLine();
                    this.sTemp = line;
                } while (line != null);
            }
            final Enumeration<String> elements = (Enumeration<String>)this.parameters.elements();
            while (elements.hasMoreElements()) {
                this.sTemp = elements.nextElement();
                if (!this.sTemp.startsWith("<!-")) {
                    String trim = " ";
                    String trim2 = " ";
                    if (this.sTemp.length() > 20) {
                        trim = this.sTemp.substring(0, 19).trim();
                        trim2 = this.sTemp.substring(20).trim();
                    }
                    if (trim.equals("3D")) {
                        this.BoolArray[0] = verifybool(trim2, "false");
                    }
                    if (trim.equals("Slabels")) {
                        this.BoolArray[3] = verifybool(trim2, "false");
                    }
                    if (trim.equals("legend")) {
                        this.BoolArray[5] = verifybool(trim2, "false");
                    }
                    if (trim.equals("nPies")) {
                        this.nPies = verifyint(trim2);
                    }
                    if (trim.equals("depth3D")) {
                        this.IntArray[7] = verifyint(trim2);
                    }
                    if (trim.equals("ndecplaces")) {
                        this.IntArray[8] = verifyint(trim2);
                    }
                    this.IntArray[9] = 5;
                    if (trim.equals("3Dangle")) {
                        this.zangle = verifyint(trim2);
                    }
                    if (trim.equals("font14")) {
                        this.FontArray[13] = verifyfnt(trim2, "Courier,N,12", ",");
                    }
                    if (trim.equals("font15")) {
                        this.FontArray[14] = verifyfnt(trim2, "Courier,N,12", ",");
                    }
                    if (trim.equals("color14")) {
                        this.ColorArray[13] = verifyclr(trim2, "255,255,255", ",");
                    }
                    if (trim.equals("color15")) {
                        this.ColorArray[14] = verifyclr(trim2, "255,255,255", ",");
                    }
                    if (trim.equals("color16")) {
                        this.ColorArray[15] = verifyclr(trim2, "255,255,255", ",");
                    }
                    if (trim.equals("color17")) {
                        this.ColorArray[16] = verifyclr(trim2, "255,255,255", ",");
                    }
                    if (trim.equals("color18")) {
                        this.ColorArray[17] = verifyclr(trim2, "255,255,255", ",");
                    }
                    if (trim.equals("color19")) {
                        this.ColorArray[18] = verifyclr(trim2, "255,255,255", ",");
                    }
                    if (trim.equals("LegendBackground")) {
                        this.ColorArray[20] = verifyclr(trim2, "255,255,255", ",");
                    }
                    if (trim.equals("LegendBorder")) {
                        this.ColorArray[21] = verifyclr(trim2, "255,255,255", ",");
                    }
                    if (trim.equals("LegendtextColor")) {
                        this.ColorArray[22] = verifyclr(trim2, "0,0,0", ",");
                    }
                    this.ColorArray[19] = this.SBGCOLOR;
                    this.i = 0;
                    while (this.i < 10) {
                        if (trim.equals("text" + (this.i + 1)) && trim2 != "" && trim2 != null) {
                            this.st = new StringTokenizer(trim2, "|");
                            if (this.st.hasMoreTokens()) {
                                this.StringArray[this.i] = this.st.nextToken();
                            }
                            else {
                                this.StringArray[this.i] = "";
                            }
                            if (this.st.hasMoreTokens()) {
                                this.sTemp2 = this.st.nextToken();
                                this.PosArray[this.i][0] = verifypos(this.sTemp2, 0, ",");
                                this.PosArray[this.i][1] = verifypos(this.sTemp2, 1, ",");
                            }
                            if (this.st.hasMoreTokens()) {
                                this.FontArray[this.i] = verifyfnt(this.st.nextToken(), "Courier,N,10", ",");
                            }
                            if (this.st.hasMoreTokens()) {
                                this.ColorArray[this.i] = verifyclr(this.st.nextToken(), "0,0,0", ",");
                            }
                        }
                        ++this.i;
                    }
                    if (trim.equals("title") && trim2 != "" && trim2 != null) {
                        this.st = new StringTokenizer(trim2, "|");
                        if (this.st.hasMoreTokens()) {
                            this.StringArray[10] = this.st.nextToken();
                        }
                        else {
                            this.StringArray[10] = "";
                        }
                        if (this.st.hasMoreTokens()) {
                            this.sTemp2 = this.st.nextToken();
                            this.PosArray[10][0] = verifypos(this.sTemp2, 0, ",");
                            this.PosArray[10][1] = verifypos(this.sTemp2, 1, ",");
                        }
                        if (this.st.hasMoreTokens()) {
                            this.FontArray[10] = verifyfnt(this.st.nextToken(), "Courier,N,10", ",");
                        }
                        if (this.st.hasMoreTokens()) {
                            this.ColorArray[10] = verifyclr(this.st.nextToken(), "0,0,0", ",");
                        }
                    }
                    if (trim.equals("legendtitle")) {
                        this.StringArray[13] = trim2;
                    }
                    if (trim.equals("legendposition")) {
                        this.PosArray[13][0] = verifypos(trim2, 0, ",");
                        this.PosArray[13][1] = verifypos(trim2, 1, ",");
                    }
                    if (trim.equals("legendfont")) {
                        this.FontArray[15] = verifyfnt(trim2, "Courier,N,10", ",");
                    }
                    this.Images = new Image[10];
                    this.ImageXY = new int[10][2];
                    this.i = 0;
                    while (this.i < 10) {
                        if (trim.equals("image" + (this.i + 1)) && trim2 != "" && trim2 != null) {
                            this.st = new StringTokenizer(trim2, ",");
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
            }
            this.Pie = new int[this.nPies][5];
            this.maxSegs = 1;
            final Enumeration<String> elements2 = (Enumeration<String>)this.parameters.elements();
            this.i = 0;
            while (this.i < this.nPies) {
                this.Pie[this.i][0] = 0;
                this.Pie[this.i][1] = 0;
                this.Pie[this.i][2] = 0;
                this.Pie[this.i][3] = 1;
                this.Pie[this.i][4] = 0;
                ++this.i;
            }
            while (elements2.hasMoreElements()) {
                this.sTemp = elements2.nextElement();
                if (!this.sTemp.startsWith("<!-")) {
                    String trim3 = " ";
                    String trim4 = " ";
                    if (this.sTemp.length() > 20) {
                        trim3 = this.sTemp.substring(0, 19).trim();
                        trim4 = this.sTemp.substring(20).trim();
                    }
                    this.i = 0;
                    while (this.i < this.nPies) {
                        if (trim3.equals("Pie" + (this.i + 1)) && trim4 != "" && trim4 != null) {
                            this.st = new StringTokenizer(trim4, ",");
                            if (this.st.hasMoreTokens()) {
                                this.Pie[this.i][0] = Integer.parseInt(this.st.nextToken());
                            }
                            else {
                                this.Pie[this.i][0] = 0;
                            }
                            if (this.st.hasMoreTokens()) {
                                this.Pie[this.i][1] = Integer.parseInt(this.st.nextToken());
                            }
                            else {
                                this.Pie[this.i][1] = 0;
                            }
                            if (this.st.hasMoreTokens()) {
                                this.Pie[this.i][2] = Integer.parseInt(this.st.nextToken());
                            }
                            else {
                                this.Pie[this.i][2] = 0;
                            }
                            if (this.st.hasMoreTokens()) {
                                this.Pie[this.i][3] = Integer.parseInt(this.st.nextToken());
                            }
                            else {
                                this.Pie[this.i][3] = 0;
                            }
                            if (this.st.hasMoreTokens()) {
                                this.Pie[this.i][4] = Integer.parseInt(this.st.nextToken());
                            }
                            else {
                                this.Pie[this.i][4] = 0;
                            }
                            if (this.Pie[this.i][3] > this.maxSegs) {
                                this.maxSegs = this.Pie[this.i][3];
                            }
                        }
                        ++this.i;
                    }
                }
            }
            this.labels = new String[this.nPies][this.maxSegs];
            this.legendlabels = new String[this.maxSegs];
            this.segmentcolor = new Color[this.nPies][this.maxSegs];
            this.i = 0;
            while (this.i < this.nPies) {
                this.j = 0;
                while (this.j < this.maxSegs) {
                    this.labels[this.i][this.j] = " ";
                    this.legendlabels[this.j] = " ";
                    this.segmentcolor[this.i][this.j] = new Color(255, 255, 255);
                    ++this.j;
                }
                ++this.i;
            }
            final Enumeration<String> elements3 = (Enumeration<String>)this.parameters.elements();
            while (elements3.hasMoreElements()) {
                this.sTemp = elements3.nextElement();
                if (!this.sTemp.startsWith("<!-")) {
                    String trim5 = " ";
                    String trim6 = " ";
                    if (this.sTemp.length() > 20) {
                        trim5 = this.sTemp.substring(0, 19).trim();
                        trim6 = this.sTemp.substring(20).trim();
                    }
                    this.i = 0;
                    while (this.i < this.maxSegs) {
                        this.j = 0;
                        while (this.j < this.nPies) {
                            if (trim5.equals("pie" + (this.j + 1) + "label" + (this.i + 1)) && trim6 != "" && trim6 != null) {
                                this.labels[this.j][this.i] = verifystr(trim6, "");
                            }
                            ++this.j;
                        }
                        ++this.i;
                    }
                    this.i = 0;
                    while (this.i < this.maxSegs) {
                        if (trim5.equals("segment" + (this.i + 1)) && trim6 != "" && trim6 != null) {
                            this.st = new StringTokenizer(trim6, "|");
                            Color verifyclr = new Color(125, 125, 125);
                            this.legendlabels[this.i] = "";
                            if (this.st.hasMoreTokens()) {
                                verifyclr = verifyclr(this.st.nextToken(), "125,125,125", ",");
                            }
                            if (this.st.hasMoreTokens()) {
                                this.legendlabels[this.i] = verifystr(this.st.nextToken(), "");
                            }
                            this.j = 0;
                            while (this.j < this.nPies) {
                                this.segmentcolor[this.j][this.i] = verifyclr;
                                ++this.j;
                            }
                        }
                        ++this.i;
                    }
                }
            }
        }
        catch (IOException ex) {
            System.out.println("IO Error:" + ex.getMessage());
        }
    }
    
    private void get_chartdatafromfile(final String s) {
        URL url = null;
        try {
            url = new URL(this.getCodeBase(), s);
        }
        catch (MalformedURLException ex2) {
            System.out.println("Bad URL for File Location : " + s);
        }
        this.parameters = new Vector();
        this.value = new double[this.nPies][this.maxSegs];
        this.segmentURL = new String[this.nPies][this.maxSegs][2];
        this.j = 0;
        while (this.j < this.maxSegs) {
            this.i = 0;
            while (this.i < this.nPies) {
                this.value[this.i][this.j] = 0.0;
                ++this.i;
            }
            ++this.j;
        }
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.connect();
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(openConnection.getInputStream()));
            Label_0210: {
                break Label_0210;
                String line;
                do {
                    this.parameters.addElement(this.sTemp);
                    line = dataInputStream.readLine();
                    this.sTemp = line;
                } while (line != null);
            }
            final Enumeration<String> elements = (Enumeration<String>)this.parameters.elements();
            while (elements.hasMoreElements()) {
                this.sTemp = elements.nextElement();
                if (!this.sTemp.startsWith("<!-")) {
                    String trim = " ";
                    String trim2 = " ";
                    if (this.sTemp.length() > 20) {
                        trim = this.sTemp.substring(0, 19).trim();
                        trim2 = this.sTemp.substring(20).trim();
                    }
                    this.j = 0;
                    while (this.j < this.nPies) {
                        this.i = 0;
                        while (this.i < this.maxSegs) {
                            if (trim.equals("data" + (this.i + 1) + "series" + (this.j + 1)) && trim2 != "" && trim2 != null) {
                                this.st = new StringTokenizer(trim2, ",");
                                if (this.st.hasMoreTokens()) {
                                    this.value[this.j][this.i] = (float)(Object)Double.valueOf(this.st.nextToken());
                                }
                                else {
                                    this.value[this.j][this.i] = 0.0;
                                }
                                if (this.value[this.j][this.i] < 0.0) {
                                    this.value[this.j][this.i] = 0.0;
                                }
                                if (this.st.hasMoreTokens()) {
                                    this.segmentURL[this.j][this.i][0] = this.st.nextToken();
                                }
                                else {
                                    this.segmentURL[this.j][this.i][0] = "";
                                }
                                if (this.st.hasMoreTokens()) {
                                    this.segmentURL[this.j][this.i][1] = this.st.nextToken();
                                }
                                else {
                                    this.segmentURL[this.j][this.i][1] = "_self";
                                }
                            }
                            ++this.i;
                        }
                        ++this.j;
                    }
                }
            }
        }
        catch (IOException ex) {
            System.out.println("IO Error:" + ex.getMessage());
        }
    }
}
