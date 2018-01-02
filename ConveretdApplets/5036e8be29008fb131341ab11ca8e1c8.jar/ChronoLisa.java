import java.awt.Polygon;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Event;
import java.util.Date;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ChronoLisa extends Applet implements Runnable
{
    Thread m_appChronoLisa;
    private int m_fps;
    private boolean m_bAllowHyperlink;
    private Color m_cBackGround;
    private Color m_cOutsideFrame;
    private Color m_cFrame;
    private Color m_cInsideFrame;
    private Color m_cClockFace;
    private Color m_cHourRing;
    private Color m_cMinuteRing;
    private Color m_cSecondRing;
    private Color m_cOneHourMarks;
    private Color m_cTenMinuteMarks;
    private Color m_cFifteenSecondMarks;
    private Color m_cPermOutsideFrame;
    private Color m_cPermFrame;
    private Color m_cPermInsideFrame;
    private final String PARAM_fps = "fps";
    private final String PARAM_allowhyperlink = "allowhyperlink";
    private final String PARAM_background = "background";
    private final String PARAM_outsideframe = "outsideframe";
    private final String PARAM_frame = "frame";
    private final String PARAM_insideframe = "insideframe";
    private final String PARAM_clockface = "clockface";
    private final String PARAM_hourring = "hourring";
    private final String PARAM_minutering = "minutering";
    private final String PARAM_secondring = "secondring";
    private final String PARAM_hourmarks = "onehourmarks";
    private final String PARAM_minutemarks = "tenminutemarks";
    private final String PARAM_secondmarks = "fifteensecondmarks";
    private int m_nUpLeftX;
    private int m_nUpLeftY;
    private int m_nLowRgtX;
    private int m_nLowRgtY;
    private Date m_dt;
    private int[] m_nTime;
    private int[] m_nLastLite;
    private boolean m_bRepaintAll;
    private boolean m_bMouseInClock;
    private boolean m_bJustFrame;
    private boolean m_bVeryFirstPaint;
    private static double outOvalRadius;
    
    public void stop() {
        if (this.m_appChronoLisa != null) {
            this.m_appChronoLisa.stop();
            this.m_appChronoLisa = null;
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.m_bMouseInClock) {
            this.m_cFrame = this.m_cPermFrame;
            this.m_bRepaintAll = true;
            this.m_bJustFrame = true;
            this.repaint();
            this.m_bMouseInClock = false;
        }
        return true;
    }
    
    private Color ParseParamColors(final String s) {
        final int[] array = new int[3];
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == ',') {
                array[n] = Integer.parseInt(s.substring(n2, i));
                n2 = i + 1;
                if (n < array.length) {
                    ++n;
                }
            }
            if (i + 1 == s.length()) {
                array[n] = Integer.parseInt(s.substring(n2, s.length()));
            }
        }
        return new Color(array[0], array[1], array[2]);
    }
    
    public ChronoLisa() {
        this.m_appChronoLisa = null;
        this.m_fps = 1;
        this.m_bAllowHyperlink = true;
        this.m_cBackGround = Color.white;
        this.m_cOutsideFrame = Color.black;
        this.m_cFrame = Color.lightGray;
        this.m_cInsideFrame = Color.black;
        this.m_cClockFace = Color.black;
        this.m_cHourRing = Color.yellow;
        this.m_cMinuteRing = Color.blue;
        this.m_cSecondRing = Color.red;
        this.m_cOneHourMarks = Color.magenta;
        this.m_cTenMinuteMarks = Color.magenta;
        this.m_cFifteenSecondMarks = Color.magenta;
        this.m_cPermOutsideFrame = Color.black;
        this.m_cPermFrame = Color.lightGray;
        this.m_cPermInsideFrame = Color.black;
        this.m_dt = new Date();
        this.m_nLastLite = new int[3];
        this.m_bRepaintAll = true;
        this.m_bMouseInClock = false;
        this.m_bJustFrame = false;
        this.m_bVeryFirstPaint = true;
    }
    
    public void paint(final Graphics graphics) {
        this.PaintFrame(graphics);
        this.PaintTicks(graphics);
        this.m_bRepaintAll = false;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.m_bAllowHyperlink) {
            URL url;
            try {
                url = new URL("http://www.krotek.com");
            }
            catch (MalformedURLException ex) {
                url = null;
            }
            if (url != null && n > this.m_nUpLeftX && n < this.m_nLowRgtX && n2 > this.m_nUpLeftY && n2 < this.m_nLowRgtY) {
                this.getAppletContext().showDocument(url, "_top");
            }
        }
        return true;
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "fps", "int", "Frame rate " } };
    }
    
    public void destroy() {
    }
    
    private void PaintFrame(final Graphics graphics) {
        final Dimension size = this.size();
        final int width = size.width;
        final int height = size.height;
        if (height < width * 11795 / 10000) {
            ChronoLisa.outOvalRadius = height / 3;
        }
        else {
            ChronoLisa.outOvalRadius = width * 11795 / 30000;
        }
        final int n = (int)(ChronoLisa.outOvalRadius * 14113.0 / 5000.0);
        final int n2 = (int)(ChronoLisa.outOvalRadius * 11965.0 / 5000.0);
        final int nUpLeftX = (width - n2) / 2;
        final int nUpLeftY = (height - n) / 2;
        final int n3 = (int)(ChronoLisa.outOvalRadius * 13538.0 / 5000.0);
        final int n4 = (int)(ChronoLisa.outOvalRadius * 11390.0 / 5000.0);
        final int n5 = (width - n4) / 2;
        final int n6 = (height - n3) / 2;
        this.m_nUpLeftX = nUpLeftX;
        this.m_nUpLeftY = nUpLeftY;
        this.m_nLowRgtX = nUpLeftX + n2;
        this.m_nLowRgtY = nUpLeftY + n;
        if (this.m_bJustFrame) {
            graphics.setColor(this.m_cFrame);
            graphics.fillRect(nUpLeftX, nUpLeftY, n2, n6 - nUpLeftY);
            graphics.fillRect(nUpLeftX, n6 + n3, n2, n6 - nUpLeftY);
            graphics.fillRect(nUpLeftX, nUpLeftY, n5 - nUpLeftX, n);
            graphics.fillRect(n5 + n4, nUpLeftY, n5 - nUpLeftX, n);
            this.m_bJustFrame = false;
        }
        else {
            graphics.setColor(this.m_cBackGround);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(this.m_cFrame);
            graphics.fillRect(nUpLeftX, this.m_nUpLeftY, n2, n);
            graphics.setColor(this.m_cClockFace);
            graphics.fillRect(n5, n6, n4, n3);
            this.m_nLastLite[0] = 0;
            this.m_nLastLite[1] = 0;
            this.m_nLastLite[2] = 0;
        }
        graphics.setColor(this.m_cOutsideFrame);
        graphics.drawRect(nUpLeftX, this.m_nUpLeftY, n2, n);
        graphics.setColor(this.m_cInsideFrame);
        graphics.drawRect(n5, n6, n4, n3);
    }
    
    public void update(final Graphics graphics) {
        if (this.m_bRepaintAll || this.m_bVeryFirstPaint) {
            this.paint(graphics);
        }
        else {
            this.PaintTicks(graphics);
        }
        this.m_bVeryFirstPaint = false;
    }
    
    private synchronized void PaintTicks(final Graphics graphics) {
        final double n = ChronoLisa.outOvalRadius * 91595.0 / 100000.0;
        final double n2 = ChronoLisa.outOvalRadius * 85763.0 / 1.0E7;
        final double n3 = this.size().height / 2;
        int n4 = 0;
        do {
            final double n5 = this.size().width / 2 + (n4 - 1) * ChronoLisa.outOvalRadius * 0.40518;
            for (int i = this.m_nLastLite[n4]; i <= this.m_nTime[n4]; ++i) {
                final double n6 = 1.5707963267948966 - i * 3.141592653589793 / 30.0;
                final double atan = Math.atan(Math.sin(n6) / (Math.cos(n6) * 1.7402987));
                final int[] array = new int[7];
                final int[] array2 = new int[7];
                array[0] = (int)(n5 + Math.cos(n6) * ChronoLisa.outOvalRadius * 0.574614);
                array2[0] = (int)(n3 - Math.sin(n6) * ChronoLisa.outOvalRadius);
                array[1] = (int)(array[0] + Math.sin(atan) * n2);
                array2[1] = (int)(array2[0] + Math.cos(atan) * n2);
                array[5] = (int)(array[0] - Math.sin(atan) * n2);
                array2[5] = (int)(array2[0] - Math.cos(atan) * n2);
                array[3] = (int)(n5 + Math.cos(n6) * n * 0.574614);
                array2[3] = (int)(n3 - Math.sin(n6) * n);
                array[2] = (int)(array[3] + Math.sin(atan) * n2);
                array2[2] = (int)(array2[3] + Math.cos(atan) * n2);
                array[4] = (int)(array[3] - Math.sin(atan) * n2);
                array2[4] = (int)(array2[3] - Math.cos(atan) * n2);
                array[6] = array[0];
                array2[6] = array2[0];
                final Polygon polygon = new Polygon(array, array2, 7);
                this.m_nLastLite[n4] = i;
                switch (n4) {
                    case 0: {
                        if (i % 5 == 0) {
                            graphics.setColor(this.m_cOneHourMarks);
                        }
                        else {
                            graphics.setColor(this.m_cHourRing);
                        }
                        graphics.drawPolygon(polygon);
                        graphics.fillPolygon(polygon);
                        break;
                    }
                    case 1: {
                        if (i % 10 == 0) {
                            graphics.setColor(this.m_cTenMinuteMarks);
                        }
                        else {
                            graphics.setColor(this.m_cMinuteRing);
                        }
                        if (i != 33 && i != 34 && i != 56 && i != 57) {
                            graphics.drawPolygon(polygon);
                            graphics.fillPolygon(polygon);
                            break;
                        }
                        break;
                    }
                    default: {
                        if (i % 15 == 0) {
                            graphics.setColor(this.m_cFifteenSecondMarks);
                        }
                        else {
                            graphics.setColor(this.m_cSecondRing);
                        }
                        if (i != 33 && i != 34 && i != 38 && i != 52 && i != 56 && i != 57) {
                            graphics.drawPolygon(polygon);
                            graphics.fillPolygon(polygon);
                            break;
                        }
                        break;
                    }
                }
            }
        } while (++n4 <= 2);
    }
    
    public void start() {
        if (this.m_appChronoLisa == null) {
            (this.m_appChronoLisa = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Name: ChronoLisa\r\nAuthor: Stephen Krochenski\r\nCopyright (c) 2002 by KroTek Electronics\r\nhttp://www.krotek.com\r\nCreated with Microsoft Visual J++ Version 1.1";
    }
    
    private synchronized void SetLights(final long n) {
        this.m_dt = new Date(n);
        final int n2 = this.m_dt.getTimezoneOffset() / 59;
        final long n3 = n / 1000L;
        long n4 = n3 % 86400L / 3600L - n2;
        if (n4 < 0L) {
            n4 += 12L;
        }
        final long n5 = n3 % 3600L / 60L;
        final long n6 = n3 % 60L;
        (this.m_nTime = new int[3])[0] = (int)n4;
        this.m_nTime[1] = (int)n5;
        this.m_nTime[2] = (int)n6;
        if (this.m_nTime[0] >= 12) {
            this.m_nTime[0] = (this.m_nTime[0] - 12) * 5 + this.m_nTime[1] / 12;
        }
        else {
            this.m_nTime[0] = this.m_nTime[0] * 5 + this.m_nTime[1] / 12;
        }
        if (this.m_nLastLite[1] != this.m_nTime[1]) {
            this.m_bRepaintAll = true;
            return;
        }
        if (this.m_nLastLite[2] != this.m_nTime[2]) {
            this.m_bRepaintAll = false;
        }
    }
    
    public void run() {
        final int n = 1000;
    Label_0004_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        this.SetLights(System.currentTimeMillis());
                        this.repaint();
                        Thread.sleep(n);
                    }
                }
                catch (InterruptedException ex) {
                    this.stop();
                    continue Label_0004_Outer;
                }
                continue;
            }
        }
    }
    
    public void init() {
        final String parameter = this.getParameter("fps");
        if (parameter != null) {
            this.m_fps = Integer.parseInt(parameter);
        }
        final String parameter2 = this.getParameter("allowhyperlink");
        if (parameter2 != null) {
            this.m_bAllowHyperlink = Boolean.valueOf(parameter2);
        }
        final String parameter3 = this.getParameter("background");
        if (parameter3 != null) {
            this.m_cBackGround = this.ParseParamColors(parameter3);
        }
        final String parameter4 = this.getParameter("outsideframe");
        if (parameter4 != null) {
            final Color parseParamColors = this.ParseParamColors(parameter4);
            this.m_cPermOutsideFrame = parseParamColors;
            this.m_cOutsideFrame = parseParamColors;
        }
        final String parameter5 = this.getParameter("frame");
        if (parameter5 != null) {
            final Color parseParamColors2 = this.ParseParamColors(parameter5);
            this.m_cPermFrame = parseParamColors2;
            this.m_cFrame = parseParamColors2;
        }
        final String parameter6 = this.getParameter("insideframe");
        if (parameter6 != null) {
            final Color parseParamColors3 = this.ParseParamColors(parameter6);
            this.m_cPermInsideFrame = parseParamColors3;
            this.m_cInsideFrame = parseParamColors3;
        }
        final String parameter7 = this.getParameter("clockface");
        if (parameter7 != null) {
            this.m_cClockFace = this.ParseParamColors(parameter7);
        }
        final String parameter8 = this.getParameter("hourring");
        if (parameter8 != null) {
            this.m_cHourRing = this.ParseParamColors(parameter8);
        }
        final String parameter9 = this.getParameter("minutering");
        if (parameter9 != null) {
            this.m_cMinuteRing = this.ParseParamColors(parameter9);
        }
        final String parameter10 = this.getParameter("secondring");
        if (parameter10 != null) {
            this.m_cSecondRing = this.ParseParamColors(parameter10);
        }
        final String parameter11 = this.getParameter("onehourmarks");
        if (parameter11 != null) {
            this.m_cOneHourMarks = this.ParseParamColors(parameter11);
        }
        final String parameter12 = this.getParameter("tenminutemarks");
        if (parameter12 != null) {
            this.m_cTenMinuteMarks = this.ParseParamColors(parameter12);
        }
        final String parameter13 = this.getParameter("fifteensecondmarks");
        if (parameter13 != null) {
            this.m_cFifteenSecondMarks = this.ParseParamColors(parameter13);
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (n > this.m_nUpLeftX && n < this.m_nLowRgtX && n2 > this.m_nUpLeftY && n2 < this.m_nLowRgtY) {
            if (!this.m_bMouseInClock) {
                this.m_cFrame = new Color(this.m_cFrame.getRed() - 30, this.m_cFrame.getGreen() - 30, this.m_cFrame.getBlue());
                this.m_bRepaintAll = true;
                this.m_bMouseInClock = true;
                this.m_bJustFrame = true;
                this.repaint();
            }
        }
        else if (this.m_bMouseInClock) {
            this.m_cFrame = this.m_cPermFrame;
            this.m_bRepaintAll = true;
            this.m_bJustFrame = true;
            this.repaint();
            this.m_bMouseInClock = false;
        }
        return true;
    }
}
