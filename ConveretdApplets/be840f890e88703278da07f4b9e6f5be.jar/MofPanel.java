import java.awt.Dimension;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class MofPanel extends Panel
{
    mofjava parent;
    boolean SPMOFFlag;
    boolean LPMOFFlag;
    boolean SPLOFFlag;
    boolean LPLOFFlag;
    boolean SunFlag;
    boolean QTHFlag;
    boolean PathsFlag;
    boolean PolarQTHFlag;
    boolean PolarNFlag;
    boolean PolarSFlag;
    Const LatDeg;
    Const LatRad;
    Const LonDeg;
    Const LonRad;
    Const SF;
    Const AIndex;
    Const KIndex;
    Const Month;
    Const Day;
    Const Time;
    JulianArray Julian;
    SunLineArray SunLines;
    static int SunRiseIndex;
    static int SunSetIndex;
    SSNArray SSN;
    AKArray AK;
    SolLatArray SolLat;
    CSolZenArray CSolZen;
    DayLengthArray DayLength;
    MLatArray MLat;
    MLonArray MLon;
    TNoonArray TNoon;
    TSunSetArray TSunSet;
    TSunRiseArray TSunRise;
    PathlenArray Pathlen;
    AzimArray Azim;
    SolAngleArray SolAngle;
    G2aArray G2a;
    HArray H;
    G2Array G2;
    LSArray LS;
    SPMOFArray SPMOF;
    LPMOFArray LPMOF;
    SPLOFArray SPLOF;
    LPLOFArray LPLOF;
    int Rows;
    int Cols;
    int MapWidth;
    int MapHeight;
    int BlockWidth;
    int BlockHeight;
    Image ColoredMap;
    Image PolarMap;
    boolean DoneUpdate;
    boolean DoneComputation;
    
    public MofPanel(final mofjava parent, final int mapHeight, final int mapWidth, final int rows, final int cols) {
        this.parent = parent;
        this.MapWidth = mapWidth;
        this.MapHeight = mapHeight;
        this.Rows = rows;
        this.Cols = cols;
        this.BlockWidth = this.MapWidth / this.Cols;
        this.BlockHeight = this.MapHeight / this.Rows;
        this.DoneUpdate = false;
        this.DoneComputation = false;
        this.LatDeg = new Const();
        this.LonDeg = new Const();
        this.LatRad = new Const();
        this.LonRad = new Const();
        this.SF = new Const();
        this.AIndex = new Const();
        this.KIndex = new Const();
        this.Month = new Const();
        this.Day = new Const();
        this.Time = new Const();
        this.Julian = new JulianArray(this);
        this.SunLines = new SunLineArray(this.Rows, this);
        this.SSN = new SSNArray(this);
        this.AK = new AKArray(this);
        this.SolLat = new SolLatArray(this);
        this.CSolZen = new CSolZenArray(this.Rows, this);
        this.DayLength = new DayLengthArray(this.Rows, this);
        this.MLat = new MLatArray(this.Rows, this.Cols, this);
        this.MLon = new MLonArray(this.Rows, this.Cols, this);
        this.TNoon = new TNoonArray(this.Rows, this.Cols, this);
        this.TSunSet = new TSunSetArray(this.Rows, this.Cols, this);
        this.TSunRise = new TSunRiseArray(this.Rows, this.Cols, this);
        this.Pathlen = new PathlenArray(this.Rows, this.Cols, this);
        this.Azim = new AzimArray(this.Rows, this.Cols, this);
        this.SolAngle = new SolAngleArray(this.Rows, this.Cols, this);
        this.G2a = new G2aArray(this.Rows, this.Cols, this);
        this.H = new HArray(this.Rows, this.Cols, this);
        this.G2 = new G2Array(this.Rows, this.Cols, this);
        this.LS = new LSArray(this.Rows, this.Cols, this);
        this.SPMOF = new SPMOFArray(this.Rows, this.Cols, this);
        this.LPMOF = new LPMOFArray(this.Rows, this.Cols, this);
        this.SPLOF = new SPLOFArray(this.Rows, this.Cols, this);
        this.LPLOF = new LPLOFArray(this.Rows, this.Cols, this);
    }
    
    public void init() {
        this.ColoredMap = this.createImage(this.MapWidth, this.MapHeight);
        this.setBounds(0, 0, this.MapWidth, this.MapHeight);
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    public void destroy() {
    }
    
    public void DoComputation() {
        this.DoneComputation = false;
        this.LatRad.SetValue(this.LatDeg2Rad(this.LatDeg.GetValue()));
        this.LonRad.SetValue(this.LonDeg2Rad(this.LonDeg.GetValue()));
        this.Julian.Compute();
        this.SunLines.Compute();
        this.SSN.Compute();
        this.AK.Compute();
        this.SolLat.Compute();
        this.CSolZen.Compute();
        this.DayLength.Compute();
        this.MLat.Compute();
        this.MLon.Compute();
        this.TNoon.Compute();
        this.TSunSet.Compute();
        this.TSunRise.Compute();
        this.Pathlen.Compute();
        this.Azim.Compute();
        this.SolAngle.Compute();
        this.G2a.Compute();
        this.H.Compute();
        this.G2.Compute();
        this.LS.Compute();
        this.SPMOF.Compute();
        this.LPMOF.Compute();
        this.SPLOF.Compute();
        this.LPLOF.Compute();
        this.DoneComputation = true;
        this.DoneUpdate = false;
        this.repaint();
        this.LatDeg.ResetChanged();
        this.LonDeg.ResetChanged();
        this.LatRad.ResetChanged();
        this.LonRad.ResetChanged();
        this.SF.ResetChanged();
        this.AIndex.ResetChanged();
        this.KIndex.ResetChanged();
        this.Month.ResetChanged();
        this.Day.ResetChanged();
        this.Time.ResetChanged();
        this.Julian.ResetChanged();
        this.SunLines.ResetChanged();
        this.SSN.ResetChanged();
        this.AK.ResetChanged();
        this.SolLat.ResetChanged();
        this.CSolZen.ResetChanged();
        this.DayLength.ResetChanged();
        this.MLat.ResetChanged();
        this.MLon.ResetChanged();
        this.TNoon.ResetChanged();
        this.TSunSet.ResetChanged();
        this.TSunRise.ResetChanged();
        this.Pathlen.ResetChanged();
        this.Azim.ResetChanged();
        this.SolAngle.ResetChanged();
        this.G2a.ResetChanged();
        this.H.ResetChanged();
        this.G2.ResetChanged();
        this.LS.ResetChanged();
        this.SPMOF.ResetChanged();
        this.LPMOF.ResetChanged();
        this.SPLOF.ResetChanged();
        this.LPLOF.ResetChanged();
    }
    
    public void DisplayFlagsChanged() {
        this.DoneUpdate = false;
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Graphics graphics2 = this.ColoredMap.getGraphics();
        if (!this.DoneComputation) {
            return;
        }
        if (this.DoneUpdate) {
            return;
        }
        graphics2.setColor(Color.black);
        graphics2.fillRect(0, 0, this.MapWidth, this.MapHeight);
        if (!graphics2.drawImage(this.parent.ScaledBackroundMap, 0, 0, this)) {
            System.out.println("in MofPanel update(), error drawing");
        }
        mediaTracker.addImage(this.ColoredMap, 0);
        mediaTracker.checkID(0, true);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            System.out.println("in MofPanel update(), interrupted");
        }
        if (this.SPMOFFlag) {
            this.ColorRectangularMap(graphics2, this.SPMOF);
        }
        if (this.LPMOFFlag) {
            this.ColorRectangularMap(graphics2, this.LPMOF);
        }
        if (this.SPLOFFlag) {
            this.ColorRectangularMap(graphics2, this.SPLOF);
        }
        if (this.LPLOFFlag) {
            this.ColorRectangularMap(graphics2, this.LPLOF);
        }
        if (this.SunFlag) {
            this.ColorSunLines(graphics2);
        }
        if (this.QTHFlag) {
            this.ColorQTH(graphics2);
        }
        if (this.PathsFlag) {
            this.ColorPaths(graphics2);
        }
        if (this.PolarQTHFlag || this.PolarNFlag || this.PolarSFlag) {
            System.out.println("in MofPanel update(), doing polar");
            this.ConvertPolar();
            if (!graphics.drawImage(this.PolarMap, 0, 0, this)) {
                System.out.println("in MofPanel update(), error doing final drawing");
            }
        }
        else {
            System.out.println("in MofPanel update(), doing rectangular");
            if (!graphics.drawImage(this.ColoredMap, 0, 0, this)) {
                System.out.println("in MofPanel update(), error doing final drawing");
            }
        }
        this.DoneUpdate = true;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.DoneUpdate) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.MapWidth, this.MapHeight);
            graphics.drawImage(this.parent.ScaledBackroundMap, 0, 0, this);
            return;
        }
        if (this.PolarQTHFlag || this.PolarNFlag || this.PolarSFlag) {
            System.out.println("in MofPanel paint() drawing polar map");
            graphics.drawImage(this.PolarMap, 0, 0, this);
        }
        else {
            System.out.println("in MofPanel paint() drawing rectangular map");
            graphics.drawImage(this.ColoredMap, 0, 0, this);
        }
    }
    
    public void ConvertPolar() {
        final int[] array = new int[this.MapWidth * this.MapHeight];
        final int[] array2 = new int[this.MapWidth * this.MapHeight];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.ColoredMap, 0, 0, this.MapWidth, this.MapHeight, array2, 0, this.MapWidth);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println("ConvertPolar = interrupted  waiting  for  pixels!");
            return;
        }
        if ((pixelGrabber.status() & 0x80) != 0x0) {
            System.err.println("ConvertPolar = image  fetch  aborted  or  errored");
            return;
        }
        System.out.println("in MofPanel ConvertPolar(), done grabbing");
        for (int i = 0; i < this.MapHeight; ++i) {
            for (int j = 0; j < this.MapWidth; ++j) {
                array[j + i * this.MapWidth] = -4144960;
            }
        }
        System.out.println("in MofPanel ConvertPolar(), done initializing polar map");
        final float n = 1.0f;
        final int n2 = this.MapWidth / 2;
        final int n3 = this.MapHeight / 2;
        final float n4 = 3.1415927f;
        final float n5 = 1.5707964f;
        final float n6 = 6.2831855f;
        final float getValue = this.LatRad.GetValue();
        final float getValue2 = this.LonRad.GetValue();
        for (int k = 0; k < this.MapHeight; ++k) {
            for (int n7 = (int)(n * Math.sqrt(Math.pow(n3, 2.0) - Math.pow(k - n3, 2.0)) + 0.5), l = n2 - n7; l < n2 + n7; ++l) {
                final double sqrt = Math.sqrt(Math.pow(k - n3, 2.0) + Math.pow((l - n2) / n, 2.0));
                if (sqrt <= n3) {
                    int n8;
                    int n9;
                    if (this.PolarNFlag) {
                        double atan = Math.atan((k - n3) / ((l - n2 - 0.5) / n));
                        if (l - n2 - 0.5 < 0.0) {
                            atan += n4;
                        }
                        if (atan < 0.0) {
                            atan += n6;
                        }
                        n8 = (int)((n6 - atan) * this.MapWidth / n6);
                        if (n8 >= this.MapWidth || n8 < 0) {
                            n8 = 0;
                        }
                        n9 = (int)(2.0 * sqrt);
                        if (n9 >= this.MapHeight || n9 < 0) {
                            n9 = 0;
                        }
                    }
                    else if (this.PolarSFlag) {
                        double atan2 = Math.atan((k - n3) / ((l - n2 - 0.5) / n));
                        if (l - n2 - 0.5 < 0.0) {
                            atan2 += n4;
                        }
                        if (atan2 < 0.0) {
                            atan2 += n6;
                        }
                        n8 = this.MapWidth - (int)((n6 - atan2) * this.MapWidth / n6) - 1;
                        if (n8 >= this.MapWidth || n8 < 0) {
                            n8 = 0;
                        }
                        n9 = this.MapHeight - (int)(2.0 * sqrt) - 1;
                        if (n9 >= this.MapHeight || n9 < 0) {
                            n9 = 0;
                        }
                    }
                    else {
                        double atan3 = Math.atan((k - n3) / ((l - n2 - 0.5) / n));
                        if (l - n2 - 0.5 < 0.0) {
                            atan3 += n4;
                        }
                        if (atan3 < 0.0) {
                            atan3 += n6;
                        }
                        final float n10 = (float)(n6 - (n6 - atan3) + n5);
                        final float n11 = (float)(sqrt * n4 / n3);
                        final float compute_lat = WorldArray.compute_lat(getValue, getValue2, n11, n10);
                        float compute_lon = WorldArray.compute_lon(getValue, getValue2, n11, n10);
                        final double n12 = (n5 - compute_lat) * n3 / n4;
                        if (compute_lon < 0.0f) {
                            compute_lon += n6;
                        }
                        n8 = (int)((n6 - compute_lon) * this.MapWidth / n6);
                        if (n8 >= this.MapWidth || n8 < 0) {
                            n8 = 0;
                        }
                        n9 = (int)(2.0 * n12);
                        if (n9 >= this.MapHeight || n9 < 0) {
                            n9 = 0;
                        }
                    }
                    array[l + k * this.MapWidth] = array2[n8 + n9 * this.MapWidth];
                }
            }
        }
        this.PolarMap = this.createImage(new MemoryImageSource(this.MapWidth, this.MapHeight, array, 0, this.MapWidth));
        System.out.println("in MofPanel ConvertPolar(), done create");
        if (this.prepareImage(this.PolarMap, this)) {
            System.out.println("in mofpanel ConvertPolar() polarmap prepareimage==true");
        }
        int n13 = this.checkImage(this.PolarMap, this);
        System.out.println("in mofPanel ConvertPolar() initial Polarmap checkimage loop check=" + n13);
        while ((n13 & 0xE0) == 0x0) {
            n13 = this.checkImage(this.PolarMap, this);
            System.out.println("in mofPanel ConvertPolar() in Polarmap checkimage loop check=" + n13);
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex2) {
                System.out.println("in mofpanel update() sleep interrupted");
            }
        }
        System.out.println("in mofPanel ConvertPolar() DONE!");
    }
    
    public void ColorRectangularMap(final Graphics graphics, final WorldArray worldArray) {
        for (int i = 0; i < this.Rows; ++i) {
            for (int j = 0; j < this.Cols; ++j) {
                Color xorMode;
                if (worldArray.IsMOF) {
                    xorMode = this.MOFColor(worldArray.GetValue(i, j));
                }
                else {
                    xorMode = this.LOFColor(worldArray.GetValue(i, j));
                }
                graphics.setXORMode(xorMode);
                graphics.fillRect(this.BlockWidth * (this.Cols - j - 1), this.BlockHeight * i, this.BlockWidth, this.BlockHeight);
            }
        }
    }
    
    public Color MOFColor(final float n) {
        Color color = Color.black;
        if ((int)n >= 14) {
            color = Color.blue;
        }
        if ((int)n >= 21) {
            color = Color.green;
        }
        if ((int)n >= 28) {
            color = Color.red;
        }
        return color;
    }
    
    public Color LOFColor(final float n) {
        Color color = Color.black;
        if ((int)n <= 8) {
            color = Color.blue;
        }
        if ((int)n <= 4) {
            color = Color.green;
        }
        if ((int)n <= 2) {
            color = Color.red;
        }
        return color;
    }
    
    public void ColorSunLines(final Graphics graphics) {
        graphics.setPaintMode();
        graphics.setColor(Color.yellow);
        for (int i = 0; i < this.Rows; ++i) {
            final int n = i * this.BlockHeight;
            final int n2 = this.LonRad2Pixel(this.LonDeg2Rad(this.SunLines.GetValue(i, MofPanel.SunRiseIndex))) - this.BlockWidth / 2;
            final int n3 = this.LonRad2Pixel(this.LonDeg2Rad(this.SunLines.GetValue(i, MofPanel.SunSetIndex))) - this.BlockWidth / 2;
            graphics.fillOval(n2, n, this.BlockWidth, this.BlockHeight);
            graphics.drawOval(n3, n, this.BlockWidth, this.BlockHeight);
        }
    }
    
    public void ColorQTH(final Graphics graphics) {
        final int n = this.LatRad2Pixel(this.LatRad.GetValue()) - this.BlockHeight / 2;
        final int n2 = this.LonRad2Pixel(this.LonRad.GetValue()) - this.BlockWidth / 2;
        graphics.setColor(Color.white);
        graphics.setPaintMode();
        graphics.fillOval(n2, n, this.BlockWidth, this.BlockHeight);
    }
    
    public void ColorPaths(final Graphics graphics) {
        final int n = 45;
        final double n2 = 0.7853981633974483;
        final double n3 = 3.141592653589793;
        final double n4 = this.LatRad.GetValue();
        final double n5 = this.LonRad.GetValue();
        graphics.setColor(Color.white);
        graphics.setPaintMode();
        for (double n6 = 0.0; n6 < 2.0 * n3; n6 += n2) {
            for (double n7 = n3 / n; n7 < n3; n7 += n3 / n) {
                final double n8 = n3 / 2.0 - Math.acos(Math.sin(n4) * Math.cos(n7) + Math.cos(n4) * Math.sin(n7) * Math.cos(n6));
                double n9 = (Math.cos(n7) - Math.sin(n8) * Math.sin(n4)) / (Math.cos(n4) * Math.cos(n8));
                if (n9 > 1.0) {
                    n9 = 1.0;
                }
                if (n9 < -1.0) {
                    n9 = -1.0;
                }
                final double acos = Math.acos(n9);
                double n10;
                if (Math.sin(n6) < 0.0) {
                    n10 = n5 - acos;
                }
                else {
                    n10 = n5 + acos;
                }
                if (n10 > 2.0 * n3) {
                    n10 -= 2.0 * n3;
                }
                if (n10 < 0.0) {
                    n10 += 2.0 * n3;
                }
                graphics.fillOval(this.LonRad2Pixel((float)n10), this.LatRad2Pixel((float)n8), 3, 3);
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.parent.SetQTH(this.LatRad2Deg(this.Pixel2LatRad(n2)), this.LonRad2Deg(this.Pixel2LonRad(n)));
        return true;
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.MapWidth, this.MapHeight);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.MapWidth, this.MapHeight);
    }
    
    private float LatDeg2Rad(float n) {
        if (n < -90.0f) {
            n = -90.0f;
        }
        if (n > 90.0f) {
            n = 90.0f;
        }
        return (float)(3.141592653589793 * n / 180.0);
    }
    
    private float LatRad2Deg(final float n) {
        return (float)(n * 180.0f / 3.141592653589793);
    }
    
    private float LonDeg2Rad(float n) {
        while (n < 0.0f) {
            n += 360.0f;
        }
        while (n > 360.0f) {
            n -= 360.0f;
        }
        return (float)(3.141592653589793 * n / 180.0);
    }
    
    private float LonRad2Deg(float n) {
        n = (float)(n * 180.0f / 3.141592653589793);
        if (n > 180.0f) {
            n -= 360.0f;
        }
        return n;
    }
    
    private int LatRad2Pixel(final float n) {
        return (int)(this.MapHeight * (1.5707963267948966 - n) / 3.141592653589793);
    }
    
    private int LonRad2Pixel(final float n) {
        return (int)(this.MapWidth * (6.283185307179586 - n) / 6.283185307179586);
    }
    
    private float Pixel2LatRad(final int n) {
        return (float)(1.5707963267948966 - 3.141592653589793 * n / this.MapHeight);
    }
    
    private float Pixel2LonRad(final int n) {
        return (float)(6.283185307179586 - 6.283185307179586 * n / this.MapWidth);
    }
    
    static {
        MofPanel.SunRiseIndex = 0;
        MofPanel.SunSetIndex = 1;
    }
}
