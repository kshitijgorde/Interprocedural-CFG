import java.net.URL;
import java.awt.Container;
import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Rectangle;
import java.awt.Image;
import java.util.Date;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class JChart extends Canvas
{
    private boolean m_Show_Prices;
    private int m_PricePrecision;
    private Font m_Message_Font;
    private String m_Empty_Message;
    private int m_TopMargin;
    private int m_BottomMargin;
    private int m_LeftMargin;
    private int m_RightMargin;
    private int m_Gap;
    private int m_VertGridDensity;
    private Color m_ZoomColor;
    private Color m_XORZoomColor;
    private int m_CharHeight;
    private Color m_Grid_Color;
    private int m_Width;
    private int m_Height;
    private final int UNIT_DATE = 0;
    private final int UNIT_MONTH = 1;
    private final int UNIT_MONTH3 = 2;
    private final int UNIT_YEAR = 3;
    private Vector m_CurveList;
    private Date[] m_X;
    private double[] m_Y;
    private Vector m_VDate;
    private Vector m_VX;
    private int m_StartX;
    private int m_StartY;
    private int m_W;
    private int m_H;
    private int[][] m_Spline;
    private boolean m_StartZoom;
    private int m_StartZoomX;
    private int m_EndZoomX;
    private int m_ZoomY;
    private Date m_FromDate;
    private Date m_ToDate;
    private double m_ScaleY;
    private double m_ScaleX;
    private Image offScreenImage;
    private Date m_ZoomStartDate;
    private Date m_ZoomEndDate;
    private Color m_CurrentColor;
    private boolean m_IsMovingAverage;
    private boolean m_EnableZoom;
    private boolean m_IsMessage;
    private final String DEMO_MESSAGE = "http://www.uralbeacon.co.uk/zoomchart";
    private Rectangle m_Demo_Message_Rec;
    private Applet m_HostingApplet;
    private boolean m_IsDemo;
    private final String m_Challenge = "A2sEes334THfI53";
    private String m_LicenseKey;
    private boolean m_SecurityCheckDone;
    
    private String formatNumber(final double n) {
        String string = "";
        for (int i = 0; i < this.m_PricePrecision; ++i) {
            string += "0";
        }
        final double doubleValue = Double.valueOf("1" + string);
        String s = new Double(Math.round(n * doubleValue) / doubleValue).toString();
        if (s.indexOf(".") != -1) {
            while (s.length() - s.indexOf(".") < this.m_PricePrecision + 1) {
                s += "0";
            }
            while (s.length() - s.indexOf(".") > this.m_PricePrecision + 1) {
                s = s.substring(0, s.length() - 1);
            }
        }
        else {
            s = s + "." + string;
        }
        return (this.m_PricePrecision == 0) ? s.replace('.', ' ') : s;
    }
    
    public void clearCurves() {
        this.m_CurveList.removeAllElements();
    }
    
    private void MergeCurves() {
        this.m_X = new Date[1];
        this.m_Y = new double[1];
        if (this.m_CurveList.isEmpty()) {
            return;
        }
        final Vector vector = new Vector<Date>();
        double n = 0.0;
        double n2 = 0.0;
        for (int i = 0; i < this.m_CurveList.size(); ++i) {
            final Curve curve = this.m_CurveList.elementAt(i);
            if (curve.getLength() > 3 && !curve.getIsMA()) {
                final double price = curve.getPrice(0);
                for (int j = 0; j < curve.getLength(); ++j) {
                    final Date date = curve.getDate(j);
                    final double diffPercent = this.getDiffPercent(price, curve.getPrice(j));
                    n = ((diffPercent > n) ? diffPercent : n);
                    n2 = ((diffPercent < n2) ? diffPercent : n2);
                    if (!vector.contains(date)) {
                        if (vector.size() >= 2) {
                            for (int k = 0; k < vector.size() - 1; ++k) {
                                final Date date2 = vector.elementAt(k);
                                final Date date3 = vector.elementAt(k + 1);
                                if (date.getTime() > date2.getTime() && date.getTime() < date3.getTime()) {
                                    vector.insertElementAt(date, k + 1);
                                    break;
                                }
                                if (k == vector.size() - 2) {
                                    vector.addElement(date);
                                    break;
                                }
                            }
                        }
                        else if (vector.size() == 1) {
                            if (vector.elementAt(0).getTime() > date.getTime()) {
                                vector.insertElementAt(date, 0);
                            }
                            else {
                                vector.addElement(date);
                            }
                        }
                        else {
                            vector.addElement(date);
                        }
                    }
                }
            }
        }
        if (vector.size() < 3) {
            return;
        }
        this.m_X = new Date[vector.size()];
        this.m_Y = new double[vector.size()];
        for (int l = 0; l < this.m_Y.length; ++l) {
            this.m_Y[l] = 1.0;
        }
        this.m_Y[1] = n / 100.0 + 1.0;
        this.m_Y[2] = n2 / 100.0 + 1.0;
        for (int n3 = 0; n3 < vector.size(); ++n3) {
            final Date date4 = vector.elementAt(n3);
            if (date4 != null) {
                this.m_X[n3] = date4;
            }
        }
    }
    
    private void drawMonthAxis(final int n, final Graphics graphics) {
        final Date date = new Date(this.m_FromDate.getTime());
        date.setDate(1);
        final long time = date.getTime();
        final long time2 = this.m_ToDate.getTime();
        this.m_ScaleX = this.m_W / (time2 - time);
        int n2 = -1;
        int n3 = -1;
        final Date date2 = new Date();
        int n4 = 0;
        for (long time3 = time; time3 < time2; time3 += this.getNextMonthStart(time3) - time3) {
            date2.setTime(time3);
            final int n5 = 1900 + date2.getYear();
            final int month = date2.getMonth();
            final boolean b = n == 1 || n4 % 3 == 0;
            if (month != n2) {
                if (b) {
                    final String monthString = this.getMonthString(month);
                    final int pixelValue = this.getPixelValue(time3 - time, this.m_ScaleX);
                    this.drawLabel(graphics, monthString, this.m_StartX + pixelValue, this.m_StartY + this.m_CharHeight + this.m_Gap);
                    graphics.setColor(this.m_Grid_Color);
                    graphics.drawLine(this.m_StartX + pixelValue, this.m_StartY - this.m_H, this.m_StartX + pixelValue, this.m_StartY);
                    if (n5 != n3) {
                        final int pixelValue2 = this.getPixelValue(time3 - time, this.m_ScaleX);
                        new String();
                        this.drawLabel(graphics, String.valueOf(n5), this.m_StartX + pixelValue2, this.m_StartY - this.m_H - this.m_Gap);
                        n3 = n5;
                    }
                }
                n2 = month;
                ++n4;
            }
        }
    }
    
    private void drawDateAxis(final Graphics graphics) {
        this.m_ScaleX = -1.0;
        final int n = (int)Math.floor(this.m_W / (this.m_X.length - 1));
        int startX = this.m_StartX;
        int n2 = -1;
        int n3 = -1;
        this.m_VDate.removeAllElements();
        this.m_VX.removeAllElements();
        int n4 = 0;
        for (int i = 0; i < this.m_X.length; ++i) {
            final int month = this.m_X[i].getMonth();
            final int n5 = 1900 + this.m_X[i].getYear();
            graphics.setColor(this.m_Grid_Color);
            graphics.drawLine(startX, this.m_StartY, startX, this.m_StartY - this.m_H);
            new String();
            this.drawLabel(graphics, String.valueOf(this.m_X[i].getDate()), startX, this.m_StartY + this.m_CharHeight + this.m_Gap);
            this.m_VDate.addElement(this.m_X[i]);
            this.m_VX.addElement(new Integer(startX));
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            if (n5 != n3) {
                final String monthString = this.getMonthString(month);
                if (n4 == 0) {
                    final int n6 = fontMetrics.stringWidth(monthString) + this.m_Gap;
                    final FontMetrics fontMetrics2 = fontMetrics;
                    new String();
                    n4 = n6 + fontMetrics2.stringWidth(String.valueOf(n5)) + this.m_Gap;
                }
                else if (n4 > startX - this.m_StartX) {
                    graphics.setColor(this.getBackground());
                    graphics.fillRect(this.m_StartX, this.m_StartY - this.m_H - this.m_Gap - this.m_CharHeight, n4, this.m_CharHeight + this.m_Gap);
                }
                this.drawLabel(graphics, monthString, startX, this.m_StartY - this.m_H - this.m_Gap);
                new String();
                this.drawLabel(graphics, String.valueOf(n5), startX + fontMetrics.stringWidth(monthString) + this.m_Gap, this.m_StartY - this.m_H - this.m_Gap);
                n2 = month;
                n3 = n5;
            }
            else if (month != n2) {
                final String monthString2 = this.getMonthString(month);
                if (n4 == 0) {
                    n4 = fontMetrics.stringWidth(monthString2) + this.m_Gap;
                }
                else if (n4 > startX - this.m_StartX) {
                    graphics.setColor(this.getBackground());
                    graphics.fillRect(this.m_StartX, this.m_StartY - this.m_H - this.m_Gap - this.m_CharHeight, n4, this.m_CharHeight + this.m_Gap);
                    this.drawLabel(graphics, monthString2, startX, this.m_StartY - this.m_H - this.m_Gap);
                    new String();
                    this.drawLabel(graphics, String.valueOf(n5), startX + fontMetrics.stringWidth(monthString2) + this.m_Gap, this.m_StartY - this.m_H - this.m_Gap);
                }
                else {
                    this.drawLabel(graphics, monthString2, startX, this.m_StartY - this.m_H - this.m_Gap);
                }
                n2 = month;
            }
            startX += n;
        }
    }
    
    public Date getZoomStartDate() {
        return this.m_ZoomStartDate;
    }
    
    private long getNextMonthStart(final long n) {
        final Date date = new Date(n);
        final int month = date.getMonth();
        final int year = date.getYear();
        int month2;
        int year2;
        if (month + 1 > 11) {
            month2 = 0;
            year2 = year + 1;
        }
        else {
            month2 = month + 1;
            year2 = year;
        }
        date.setYear(year2);
        date.setMonth(month2);
        date.setDate(1);
        return date.getTime();
    }
    
    private int drawAxisY(final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        this.m_ScaleY = 0.0;
        final double diffPercent = this.getDiffPercent(this.m_Y[0], this.m_Y[this.arrayMinIndex(this.m_Y)]);
        final double diffPercent2 = this.getDiffPercent(this.m_Y[0], this.m_Y[this.arrayMaxIndex(this.m_Y)]);
        final int round = Math.round(this.m_H / this.m_VertGridDensity);
        final double firstValue = this.getFirstValue();
        final Color firstColor = this.getFirstColor();
        int h;
        int n2;
        if (diffPercent == 0.0) {
            h = 0;
            final double modLevel = this.getModLevel(diffPercent2, this.m_VertGridDensity);
            this.m_ScaleY = this.m_H / modLevel;
            final double n = modLevel / this.m_VertGridDensity;
            n2 = this.getPrecision(n);
            double n3 = 0.0;
            int n4 = h;
            for (int i = 0; i < this.m_VertGridDensity - 1; ++i) {
                n4 += round;
                n3 += n;
                final String displayString = this.getDisplayString(n3, n2, "%");
                graphics.setColor(this.m_Grid_Color);
                graphics.drawLine(this.m_StartX, this.m_StartY - n4, this.m_StartX + this.m_W, this.m_StartY - n4);
                graphics.setColor(this.getForeground());
                graphics.drawString(displayString, this.m_StartX - fontMetrics.stringWidth(displayString) - this.m_Gap, this.m_StartY - n4 + this.m_CharHeight / 2);
                if (this.m_Show_Prices) {
                    final String formatNumber = this.formatNumber(firstValue * (n3 + 100.0) / 100.0);
                    graphics.setColor(firstColor);
                    graphics.drawString(formatNumber, this.m_StartX + this.m_W + this.m_Gap, this.m_StartY - n4 + this.m_CharHeight / 2);
                }
            }
            final double n5 = n3 + n;
            final String displayString2 = this.getDisplayString(n5, n2, "%");
            graphics.setColor(this.getForeground());
            graphics.drawString(displayString2, this.m_StartX - fontMetrics.stringWidth(displayString2) - this.m_Gap, this.m_StartY - this.m_H + this.m_CharHeight / 2);
            if (this.m_Show_Prices) {
                final String formatNumber2 = this.formatNumber(firstValue * (n5 + 100.0) / 100.0);
                graphics.setColor(firstColor);
                graphics.drawString(formatNumber2, this.m_StartX + this.m_W + this.m_Gap, this.m_StartY - this.m_H + this.m_CharHeight / 2);
            }
        }
        else if (diffPercent2 == 0.0) {
            h = this.m_H;
            final double modLevel2 = this.getModLevel(-diffPercent, this.m_VertGridDensity);
            this.m_ScaleY = this.m_H / modLevel2;
            final double n6 = modLevel2 / this.m_VertGridDensity;
            n2 = this.getPrecision(n6);
            double n7 = 0.0;
            int n8 = h;
            for (int j = 0; j < this.m_VertGridDensity - 1; ++j) {
                n8 -= round;
                n7 -= n6;
                final String displayString3 = this.getDisplayString(n7, n2, "%");
                graphics.setColor(this.m_Grid_Color);
                graphics.drawLine(this.m_StartX, this.m_StartY - n8, this.m_StartX + this.m_W, this.m_StartY - n8);
                graphics.setColor(this.getForeground());
                graphics.drawString(displayString3, this.m_StartX - fontMetrics.stringWidth(displayString3) - this.m_Gap, this.m_StartY - n8 + this.m_CharHeight / 2);
                if (this.m_Show_Prices) {
                    final String formatNumber3 = this.formatNumber(firstValue * (n7 + 100.0) / 100.0);
                    graphics.setColor(firstColor);
                    graphics.drawString(formatNumber3, this.m_StartX + this.m_W + this.m_Gap, this.m_StartY - n8 + this.m_CharHeight / 2);
                }
            }
            final double n9 = n7 - n6;
            final String displayString4 = this.getDisplayString(n9, n2, "%");
            graphics.setColor(this.getForeground());
            graphics.drawString(displayString4, this.m_StartX - fontMetrics.stringWidth(displayString4) - this.m_Gap, this.m_StartY + this.m_CharHeight / 2);
            if (this.m_Show_Prices) {
                final String formatNumber4 = this.formatNumber(firstValue * (n9 + 100.0) / 100.0);
                graphics.setColor(firstColor);
                graphics.drawString(formatNumber4, this.m_StartX + this.m_W + this.m_Gap, this.m_StartY + this.m_CharHeight / 2);
            }
        }
        else {
            final int zeroLevelIndex = this.getZeroLevelIndex(round, diffPercent, diffPercent2);
            h = (zeroLevelIndex + 1) * round;
            int n11;
            double n12;
            if (Math.abs(diffPercent2 / diffPercent) / ((this.m_H - h) / h) > 1.0) {
                final double n10 = diffPercent2;
                n11 = this.m_VertGridDensity - (zeroLevelIndex + 1);
                n12 = this.getModLevel(n10, n11);
                this.m_ScaleY = (this.m_H - h) / n12;
            }
            else {
                final double n13 = -diffPercent;
                n11 = zeroLevelIndex + 1;
                n12 = this.getModLevel(n13, n11);
                this.m_ScaleY = h / n12;
            }
            final double n14 = n12 / n11;
            n2 = this.getPrecision(n14);
            double n15 = 0.0;
            int n16 = h;
            for (int k = 0; k < this.m_VertGridDensity - (zeroLevelIndex + 1) - 1; ++k) {
                n16 += round;
                n15 += n14;
                final String displayString5 = this.getDisplayString(n15, n2, "%");
                graphics.setColor(this.m_Grid_Color);
                graphics.drawLine(this.m_StartX, this.m_StartY - n16, this.m_StartX + this.m_W, this.m_StartY - n16);
                graphics.setColor(this.getForeground());
                graphics.drawString(displayString5, this.m_StartX - fontMetrics.stringWidth(displayString5) - this.m_Gap, this.m_StartY - n16 + this.m_CharHeight / 2);
                if (this.m_Show_Prices) {
                    final String formatNumber5 = this.formatNumber(firstValue * (n15 + 100.0) / 100.0);
                    graphics.setColor(firstColor);
                    graphics.drawString(formatNumber5, this.m_StartX + this.m_W + this.m_Gap, this.m_StartY - n16 + this.m_CharHeight / 2);
                }
            }
            final double n17 = n15 + n14;
            final String displayString6 = this.getDisplayString(n17, n2, "%");
            graphics.setColor(this.getForeground());
            graphics.drawString(displayString6, this.m_StartX - fontMetrics.stringWidth(displayString6) - this.m_Gap, this.m_StartY - this.m_H + this.m_CharHeight / 2);
            if (this.m_Show_Prices) {
                final String formatNumber6 = this.formatNumber(firstValue * (n17 + 100.0) / 100.0);
                graphics.setColor(firstColor);
                graphics.drawString(formatNumber6, this.m_StartX + this.m_W + this.m_Gap, this.m_StartY - this.m_H + this.m_CharHeight / 2);
            }
            double n18 = 0.0;
            int n19 = h;
            for (int l = 0; l < zeroLevelIndex; ++l) {
                n19 -= round;
                n18 -= n14;
                final String displayString7 = this.getDisplayString(n18, n2, "%");
                graphics.setColor(this.m_Grid_Color);
                graphics.drawLine(this.m_StartX, this.m_StartY - n19, this.m_StartX + this.m_W, this.m_StartY - n19);
                graphics.setColor(this.getForeground());
                graphics.drawString(displayString7, this.m_StartX - fontMetrics.stringWidth(displayString7) - this.m_Gap, this.m_StartY - n19 + this.m_CharHeight / 2);
                if (this.m_Show_Prices) {
                    final String formatNumber7 = this.formatNumber(firstValue * (n18 + 100.0) / 100.0);
                    graphics.setColor(firstColor);
                    graphics.drawString(formatNumber7, this.m_StartX + this.m_W + this.m_Gap, this.m_StartY - n19 + this.m_CharHeight / 2);
                }
            }
            final double n20 = n18 - n14;
            final String displayString8 = this.getDisplayString(n20, n2, "%");
            graphics.setColor(this.getForeground());
            graphics.drawString(displayString8, this.m_StartX - fontMetrics.stringWidth(displayString8) - this.m_Gap, this.m_StartY + this.m_CharHeight / 2);
            if (this.m_Show_Prices) {
                final String formatNumber8 = this.formatNumber(firstValue * (n20 + 100.0) / 100.0);
                graphics.setColor(firstColor);
                graphics.drawString(formatNumber8, this.m_StartX + this.m_W + this.m_Gap, this.m_StartY + this.m_CharHeight / 2);
            }
            graphics.setColor(this.m_Grid_Color);
            graphics.drawLine(this.m_StartX, this.m_StartY - h, this.m_StartX + this.m_W, this.m_StartY - h);
        }
        final String s = "0";
        String s2 = null;
        switch (n2) {
            case 0: {
                s2 = s + "%";
                break;
            }
            case 1: {
                s2 = s + ".0%";
                break;
            }
            default: {
                s2 = s + ".00%";
                break;
            }
        }
        graphics.setColor(this.getForeground());
        graphics.drawString(s2, this.m_StartX - fontMetrics.stringWidth(s2) - this.m_Gap, this.m_StartY + this.m_CharHeight / 2 - h);
        if (this.m_Show_Prices) {
            final String formatNumber9 = this.formatNumber(firstValue);
            graphics.setColor(firstColor);
            graphics.drawString(formatNumber9, this.m_StartX + this.m_W + this.m_Gap, this.m_StartY + this.m_CharHeight / 2 - h);
        }
        return h;
    }
    
    private int getZeroLevelIndex(final int n, final double n2, final double n3) {
        final double[] array = new double[this.m_VertGridDensity - 1];
        for (int i = 0; i < array.length; ++i) {
            final int n4 = n * (i + 1);
            array[i] = Math.abs(1.0 - Math.abs(n3 / n2) / ((this.m_H - n4) / n4));
        }
        return this.arrayMinIndex(array);
    }
    
    private String getDisplayString(double n, final int n2, final String s) {
        switch (n2) {
            case 0: {
                n = Math.round(n);
                return new Integer((int)n).toString() + s;
            }
            case 1: {
                n = Math.round(n * 10.0) / 10.0;
                return new Float(n).toString() + s;
            }
            default: {
                n = Math.round(n * 100.0) / 100.0;
                switch (this.getPrecision(n)) {
                    case 0: {
                        return new Integer((int)n).toString() + s;
                    }
                    case 1: {
                        return new Float(n).toString() + "0" + s;
                    }
                    default: {
                        return new Float(n).toString() + s;
                    }
                }
                break;
            }
        }
    }
    
    private boolean InGrid(final int n, final int n2) {
        final int startX = this.m_StartX;
        final int n3 = this.m_StartY - this.m_H;
        final int n4 = this.m_StartX + this.m_W;
        final int startY = this.m_StartY;
        return n > startX && n < n4 && n2 > n3 && n2 < startY;
    }
    
    public boolean isBusy() {
        return this.m_StartZoom;
    }
    
    public void setZoomRange(final Date fromDate, final Date toDate) {
        this.m_FromDate = fromDate;
        this.m_ToDate = toDate;
    }
    
    public void showMessage(final String s, final Font font) {
        if (this.offScreenImage == null) {
            return;
        }
        final Graphics graphics = this.offScreenImage.getGraphics();
        try {
            this.m_IsMessage = true;
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, this.m_Width, this.m_Height);
            graphics.setFont(font);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.setColor(this.getForeground());
            graphics.drawString(s, (this.m_Width - fontMetrics.stringWidth(s)) / 2, this.m_Height / 2);
        }
        finally {
            graphics.dispose();
        }
        this.repaint();
    }
    
    private boolean isDateZoom() {
        return this.m_ScaleX <= 0.0;
    }
    
    private double getFirstValue() {
        if (this.m_CurveList.isEmpty()) {
            return 0.0;
        }
        final Curve curve = this.m_CurveList.elementAt(0);
        if (curve.getLength() == 0) {
            return 0.0;
        }
        return curve.getPrice(0);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public Date getEndDate() {
        return this.m_ToDate;
    }
    
    private long getCheckSum(String string) {
        if (string.length() == 0) {
            return 0L;
        }
        string += "A2sEes334THfI53";
        long n = string.charAt(0);
        for (int i = 1; i < string.length(); ++i) {
            n = n * 2L + string.charAt(i);
        }
        return n;
    }
    
    private Color getFirstColor() {
        if (this.m_CurveList.isEmpty()) {
            return this.getForeground();
        }
        final Curve curve = this.m_CurveList.elementAt(0);
        if (curve.getLength() == 0) {
            return this.getForeground();
        }
        return curve.getColor();
    }
    
    private int getPixelValue(final double n, final double n2) {
        return (int)Math.round(n * n2);
    }
    
    private Date getDateFromX(final int n, final int n2, final boolean b) {
        Date date = new Date();
        if (!this.isDateZoom()) {
            final Date date2 = new Date(this.m_FromDate.getTime());
            date2.setDate(1);
            date.setTime(Math.round(date2.getTime() + (n - n2) / this.m_ScaleX));
        }
        else {
            date = this.m_VDate.elementAt(this.m_VDate.size() - 1);
            for (int i = 0; i < this.m_VX.size() - 1; ++i) {
                final int intValue = this.m_VX.elementAt(i);
                final int intValue2 = this.m_VX.elementAt(i + 1);
                if (n > intValue && n < intValue2) {
                    date = (Date)this.m_VDate.elementAt(b ? (i + 1) : i);
                    break;
                }
            }
        }
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date;
    }
    
    private void drawCurve(final Graphics graphics, final int n) {
        if (this.m_X.length < 3) {
            return;
        }
        final Date date = new Date(this.m_FromDate.getTime());
        date.setDate(1);
        int n2;
        if (!this.isDateZoom()) {
            n2 = this.getPixelValue(this.m_X[0].getTime() - date.getTime(), this.m_ScaleX);
        }
        else {
            n2 = this.getXOffset(this.m_X[0]);
        }
        int n3 = this.m_IsMovingAverage ? (this.getPixelValue(this.m_Y[0], this.m_ScaleY) + n) : n;
        graphics.setColor(this.m_CurrentColor);
        for (int i = 1; i < this.m_X.length; ++i) {
            final int n4 = this.m_IsMovingAverage ? (this.getPixelValue(this.m_Y[i], this.m_ScaleY) + n) : (n + this.getPixelValue(this.getDiffPercent(this.m_Y[0], this.m_Y[i]), this.m_ScaleY));
            int n5;
            if (!this.isDateZoom()) {
                n5 = this.getPixelValue(this.m_X[i].getTime() - date.getTime(), this.m_ScaleX);
                graphics.drawLine(this.m_StartX + n2, this.m_StartY - n3, this.m_StartX + n5, this.m_StartY - n4);
            }
            else {
                n5 = this.getXOffset(this.m_X[i]);
                graphics.drawLine(n2, this.m_StartY - n3, n5, this.m_StartY - n4);
            }
            n2 = n5;
            n3 = n4;
        }
    }
    
    private void drawAxisX(final int n, final Graphics graphics) {
        switch (n) {
            case 0: {
                this.drawDateAxis(graphics);
            }
            case 1:
            case 2: {
                this.drawMonthAxis(n, graphics);
            }
            default: {
                this.drawYearAxis(graphics);
            }
        }
    }
    
    private void paintChart(final Graphics graphics) {
        this.m_IsMessage = false;
        this.m_EnableZoom = false;
        this.m_Width = this.size().width;
        this.m_Height = this.size().height;
        this.m_StartX = this.m_LeftMargin;
        this.m_StartY = this.m_Height - this.m_BottomMargin;
        this.m_W = this.m_Width - this.m_LeftMargin - this.m_RightMargin;
        this.m_H = this.m_Height - this.m_TopMargin - this.m_BottomMargin;
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.m_Width, this.m_Height);
        graphics.setColor(this.getForeground());
        this.drawGrid(graphics);
    }
    
    private void drawDemoMessage(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        if (!this.m_IsDemo) {
            return;
        }
        graphics.setFont(new Font("Dialog", 0, 10));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        this.m_Demo_Message_Rec = new Rectangle();
        this.m_Demo_Message_Rec.x = this.m_StartX + this.m_Gap;
        this.m_Demo_Message_Rec.y = this.m_TopMargin + fontMetrics.getHeight();
        this.m_Demo_Message_Rec.width = fontMetrics.stringWidth("http://www.uralbeacon.co.uk/zoomchart");
        this.m_Demo_Message_Rec.height = fontMetrics.getHeight();
        graphics.setColor(this.getForeground());
        graphics.drawString("http://www.uralbeacon.co.uk/zoomchart", this.m_Demo_Message_Rec.x, this.m_Demo_Message_Rec.y);
        graphics.drawLine(this.m_Demo_Message_Rec.x, this.m_Demo_Message_Rec.y + 2, this.m_Demo_Message_Rec.x + this.m_Demo_Message_Rec.width, this.m_Demo_Message_Rec.y + 2);
    }
    
    private void ActivateCurves(final int n) {
        final Curve curve = this.m_CurveList.elementAt(n);
        this.m_CurrentColor = curve.getColor();
        this.m_IsMovingAverage = curve.getIsMA();
        this.m_X = new Date[curve.getLength()];
        this.m_Y = new double[curve.getLength()];
        for (int i = 0; i < curve.getLength(); ++i) {
            this.m_X[i] = curve.getDate(i);
            this.m_Y[i] = curve.getPrice(i);
        }
    }
    
    private void drawYearAxis(final Graphics graphics) {
        final long time = this.m_FromDate.getTime();
        final int year = this.m_FromDate.getYear();
        final int year2 = this.m_ToDate.getYear();
        this.m_ScaleX = this.m_W / (this.m_ToDate.getTime() - time);
        int n = -1;
        final Date date = new Date();
        for (int i = year; i <= year2; ++i) {
            date.setYear(i);
            date.setMonth(0);
            date.setDate(1);
            if (i != n) {
                final int n2 = this.m_StartX + this.getPixelValue(date.getTime() - time, this.m_ScaleX);
                if (n2 - this.m_StartX > 0) {
                    new String();
                    this.drawLabel(graphics, String.valueOf(1900 + i), n2, this.m_StartY + this.m_CharHeight + this.m_Gap);
                    graphics.setColor(this.m_Grid_Color);
                    graphics.drawLine(n2, this.m_StartY, n2, this.m_StartY - this.m_H);
                }
                n = i;
            }
        }
    }
    
    private int getXOffset(final Date date) {
        return this.m_VX.elementAt(this.m_VDate.indexOf(date));
    }
    
    public void addCurve(final Curve curve) {
        this.m_CurveList.addElement(curve);
    }
    
    public synchronized void reset() {
        if (this.offScreenImage != null) {
            this.offScreenImage = null;
        }
        this.repaint();
    }
    
    private void drawGrid(final Graphics graphics) {
        this.m_CharHeight = graphics.getFontMetrics().getHeight();
        this.MergeCurves();
        if (this.m_X.length < 3) {
            this.m_EnableZoom = false;
            this.showMessage(this.m_Empty_Message, this.m_Message_Font);
            return;
        }
        this.m_EnableZoom = true;
        final long n = this.m_W;
        final long timeMinWidth = this.getTimeMinWidth(graphics, 0);
        final long timeMinWidth2 = this.getTimeMinWidth(graphics, 1);
        final long timeMinWidth3 = this.getTimeMinWidth(graphics, 2);
        if (n >= timeMinWidth) {
            this.drawAxisX(0, graphics);
        }
        else if (n >= timeMinWidth2) {
            this.drawAxisX(1, graphics);
        }
        else if (n >= timeMinWidth3) {
            this.drawAxisX(2, graphics);
        }
        else {
            this.drawAxisX(3, graphics);
        }
        final int drawAxisY = this.drawAxisY(graphics);
        final Graphics create = graphics.create();
        create.clipRect(this.m_StartX - 1, this.m_StartY - this.m_H - 1, this.m_W + 2, this.m_H + 2);
        try {
            for (int i = 0; i < this.m_CurveList.size(); ++i) {
                this.ActivateCurves(i);
                this.drawCurve(create, drawAxisY);
            }
        }
        finally {
            create.dispose();
        }
        graphics.setColor(this.getForeground());
        graphics.drawRect(this.m_StartX, this.m_StartY - this.m_H, this.m_W, this.m_H);
    }
    
    public JChart(final String licenseKey, final int n, final int n2, final String empty_Message, final Font message_Font, final Color zoomColor, final Color xorZoomColor, final Color grid_Color, final int topMargin, final int bottomMargin, final int leftMargin, final int rightMargin, final int gap, final int vertGridDensity) {
        this.m_Spline = new int[][] { { 0, 6 }, { 5, 2 } };
        this.m_StartZoom = false;
        this.offScreenImage = null;
        this.m_CurrentColor = Color.white;
        this.m_EnableZoom = false;
        this.m_LicenseKey = licenseKey;
        this.m_IsMessage = false;
        this.m_Show_Prices = (n == 1);
        this.m_PricePrecision = ((n2 < 0) ? 0 : n2);
        this.m_PricePrecision = ((this.m_PricePrecision > 10) ? 10 : this.m_PricePrecision);
        this.m_Empty_Message = empty_Message;
        this.m_Message_Font = message_Font;
        this.m_ZoomColor = zoomColor;
        this.m_XORZoomColor = xorZoomColor;
        this.m_Grid_Color = grid_Color;
        this.m_TopMargin = topMargin;
        this.m_BottomMargin = bottomMargin;
        this.m_LeftMargin = leftMargin;
        this.m_RightMargin = rightMargin;
        this.m_Gap = gap;
        this.m_VertGridDensity = vertGridDensity;
        this.m_X = new Date[1];
        this.m_Y = new double[1];
        this.m_CurveList = new Vector();
        this.m_FromDate = new Date();
        this.m_ToDate = new Date();
        this.m_VDate = new Vector();
        this.m_VX = new Vector();
        this.m_Demo_Message_Rec = null;
        this.m_HostingApplet = null;
        this.m_IsDemo = true;
        this.m_SecurityCheckDone = false;
    }
    
    public void paint(final Graphics graphics) {
        try {
            this.securityCheck();
            if (this.offScreenImage != null) {
                final Graphics graphics2 = this.offScreenImage.getGraphics();
                try {
                    this.drawDemoMessage(graphics2);
                }
                finally {
                    graphics2.dispose();
                }
                graphics.drawImage(this.offScreenImage, 0, 0, this);
                return;
            }
            final Dimension size = this.size();
            if (size.width > 0 && size.height > 0) {
                this.offScreenImage = this.createImage(size.width, size.height);
                if (this.offScreenImage != null) {
                    final Graphics graphics3 = this.offScreenImage.getGraphics();
                    try {
                        this.paintChart(graphics3);
                        this.drawDemoMessage(graphics3);
                    }
                    finally {
                        graphics3.dispose();
                    }
                    graphics.drawImage(this.offScreenImage, 0, 0, this);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.m_IsMessage) {
            return false;
        }
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return true;
        }
        graphics.setColor(this.m_ZoomColor);
        graphics.setXORMode(this.m_XORZoomColor);
        if (this.m_EndZoomX != -1) {
            this.drawSpline(graphics, this.m_StartZoomX, this.m_ZoomY, this.m_EndZoomX, this.m_ZoomY);
            this.m_EndZoomX = -1;
        }
        if (this.m_StartZoom) {
            this.m_StartZoom = false;
            this.repaint();
            if (this.m_ZoomStartDate == null) {
                return true;
            }
            if (this.m_ZoomEndDate == null) {
                return true;
            }
            if (this.m_ZoomEndDate.getTime() - this.m_ZoomStartDate.getTime() > 187200000L) {
                this.postEvent(new Event(this, 1001, ""));
            }
        }
        return true;
    }
    
    private synchronized void securityCheck() {
        if (this.m_SecurityCheckDone) {
            return;
        }
        if (this.m_HostingApplet == null) {
            for (Container container = this.getParent(); container != null && this.m_HostingApplet == null; container = container.getParent()) {
                try {
                    this.m_HostingApplet = (Applet)container;
                }
                catch (Exception ex) {
                    this.m_HostingApplet = null;
                }
            }
        }
        if (this.m_HostingApplet != null) {
            final String host = this.m_HostingApplet.getDocumentBase().getHost();
            final StringTokenizer stringTokenizer = new StringTokenizer(this.m_LicenseKey, ",");
            while (stringTokenizer.hasMoreElements()) {
                try {
                    if (Math.round(new Double(stringTokenizer.nextToken())) == this.getCheckSum(host)) {
                        this.m_IsDemo = false;
                        return;
                    }
                    continue;
                }
                catch (Exception ex2) {
                    this.m_IsDemo = true;
                    return;
                }
            }
        }
        this.m_SecurityCheckDone = true;
    }
    
    private double getModLevel(double ceil, final int n) {
        ceil = ceil;
        final double n2 = ceil / n;
        double n3;
        if (n2 >= 1000.0) {
            ceil = Math.ceil(ceil / 100.0) * 100.0;
            n3 = 100.0;
        }
        else if (n2 >= 100.0 && n2 < 1000.0) {
            ceil = Math.ceil(ceil / 10.0) * 10.0;
            n3 = 10.0;
        }
        else if (n2 >= 10.0 && n2 < 100.0) {
            ceil = Math.ceil(ceil);
            n3 = 1.0;
        }
        else if (n2 >= 1.0 && n2 < 10.0) {
            ceil = Math.ceil(ceil * 10.0) / 10.0;
            n3 = 0.1;
        }
        else {
            ceil = Math.ceil(ceil * 100.0) / 100.0;
            n3 = 0.01;
        }
        if (n3 >= 1.0) {
            if ((int)ceil % n == 0) {
                return ceil;
            }
        }
        else if ((int)(ceil / n3) % n == 0) {
            return ceil;
        }
        while (true) {
            ceil += n3;
            if (n3 >= 1.0) {
                if ((int)ceil % n == 0) {
                    return ceil;
                }
                continue;
            }
            else {
                if ((int)(ceil / n3) % n == 0) {
                    return ceil;
                }
                continue;
            }
        }
    }
    
    private String getMonthString(final int n) {
        switch (n) {
            case 0: {
                return "Jan";
            }
            case 1: {
                return "Feb";
            }
            case 2: {
                return "Mar";
            }
            case 3: {
                return "Apr";
            }
            case 4: {
                return "May";
            }
            case 5: {
                return "Jun";
            }
            case 6: {
                return "Jul";
            }
            case 7: {
                return "Aug";
            }
            case 8: {
                return "Sep";
            }
            case 9: {
                return "Oct";
            }
            case 10: {
                return "Nov";
            }
            default: {
                return "Dec";
            }
        }
    }
    
    private int arrayMinIndex(final double[] array) {
        if (array.length == 0) {
            return 0;
        }
        double n = array[0];
        int n2 = 0;
        for (int i = 0; i < array.length; ++i) {
            final double n3 = array[i];
            if (n3 < n) {
                n2 = i;
                n = n3;
            }
        }
        return n2;
    }
    
    private long getTimeMinWidth(final Graphics graphics, final int n) {
        final long n2 = this.m_ToDate.getTime() - this.m_FromDate.getTime();
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        switch (n) {
            case 0: {
                return (long)Math.ceil(n2 / 1000.0 / 60.0 / 60.0 / 24.0 * (this.m_Gap + (int)Math.round(fontMetrics.stringWidth("123456789101112131415161718192021222324252627282930") / 30.0)));
            }
            case 1:
            case 2: {
                final double n3 = n2 / 1000.0 / 60.0 / 60.0 / 24.0 / 30.0;
                return (long)Math.ceil(((n == 1) ? n3 : (n3 / 3.0)) * (this.m_Gap + (int)Math.round(fontMetrics.stringWidth("JanFebMarAprMayJunJulAugSepOctNovDec") / 12.0)));
            }
            default: {
                return (long)Math.ceil(n2 / 1000.0 / 60.0 / 60.0 / 24.0 / 365.0 * (this.m_Gap + fontMetrics.stringWidth("0000")));
            }
        }
    }
    
    private int arrayMaxIndex(final double[] array) {
        if (array.length == 0) {
            return 0;
        }
        double n = array[0];
        int n2 = 0;
        for (int i = 0; i < array.length; ++i) {
            final double n3 = array[i];
            if (n3 > n) {
                n2 = i;
                n = n3;
            }
        }
        return n2;
    }
    
    public boolean mouseDown(final Event event, final int startZoomX, final int zoomY) {
        if (this.m_Demo_Message_Rec != null && startZoomX >= this.m_Demo_Message_Rec.x && startZoomX <= this.m_Demo_Message_Rec.x + this.m_Demo_Message_Rec.width && zoomY >= this.m_Demo_Message_Rec.y - this.m_Demo_Message_Rec.height && zoomY <= this.m_Demo_Message_Rec.y && this.m_HostingApplet != null) {
            try {
                this.m_HostingApplet.getAppletContext().showDocument(new URL("http://www.uralbeacon.co.uk/zoomchart"));
            }
            catch (Exception ex) {}
        }
        if (this.m_IsMessage) {
            return false;
        }
        if (this.m_EnableZoom && this.InGrid(startZoomX, zoomY)) {
            this.m_StartZoomX = startZoomX;
            this.m_ZoomY = zoomY;
            this.m_EndZoomX = -1;
            this.m_StartZoom = true;
        }
        return true;
    }
    
    public Date getStartDate() {
        return this.m_FromDate;
    }
    
    public Date getZoomEndDate() {
        return this.m_ZoomEndDate;
    }
    
    private double getDiffPercent(final double n, final double n2) {
        return (n2 - n) / n * 100.0;
    }
    
    private void drawSpline(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (n == 0 && n3 == 0 && n2 == 0 && n4 == 0) {
            return;
        }
        final int n5 = (n > n3) ? n3 : n;
        final int n6 = (n > n3) ? n : n3;
        final int n7 = (n2 > n4) ? n4 : n2;
        final int n8 = (n2 > n4) ? n2 : n4;
        graphics.drawLine(n5 + 1, n7, n6 - 1, n8);
        graphics.drawLine(n5, this.m_StartY - this.m_H, n5, this.m_StartY);
        graphics.drawLine(n6, this.m_StartY - this.m_H, n6, this.m_StartY);
        graphics.drawLine(n5, n7, n5 + this.m_Spline[1][0], n7 - this.m_Spline[1][1]);
        graphics.drawLine(n5, n7, n5 + this.m_Spline[1][0], n7 + this.m_Spline[1][1]);
        graphics.drawLine(n6 - this.m_Spline[1][0], n8 - this.m_Spline[1][1], n6, n8);
        graphics.drawLine(n6 - this.m_Spline[1][0], n8 + this.m_Spline[1][1], n6, n8);
    }
    
    public boolean mouseDrag(final Event event, final int endZoomX, final int n) {
        if (this.m_IsMessage) {
            return false;
        }
        if (!this.m_StartZoom || !this.InGrid(endZoomX, n)) {
            return true;
        }
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return true;
        }
        graphics.setColor(this.m_ZoomColor);
        graphics.setXORMode(this.m_XORZoomColor);
        if (this.m_EndZoomX != -1) {
            this.drawSpline(graphics, this.m_StartZoomX, this.m_ZoomY, this.m_EndZoomX, this.m_ZoomY);
        }
        if (endZoomX <= this.m_StartX) {
            this.m_EndZoomX = this.m_StartX + 1;
        }
        else if (endZoomX >= this.m_StartX + this.m_W) {
            this.m_EndZoomX = this.m_StartX + this.m_W - 1;
        }
        else {
            this.m_EndZoomX = endZoomX;
        }
        this.drawSpline(graphics, this.m_StartZoomX, this.m_ZoomY, this.m_EndZoomX, this.m_ZoomY);
        if (this.m_EndZoomX > this.m_StartZoomX) {
            this.m_ZoomStartDate = this.getDateFromX(this.m_StartZoomX, this.m_StartX, true);
            this.m_ZoomEndDate = this.getDateFromX(this.m_EndZoomX, this.m_StartX, false);
        }
        else {
            this.m_ZoomStartDate = this.getDateFromX(this.m_EndZoomX, this.m_StartX, true);
            this.m_ZoomEndDate = this.getDateFromX(this.m_StartZoomX, this.m_StartX, false);
        }
        return true;
    }
    
    private void drawLabel(final Graphics graphics, final String s, final int n, final int n2) {
        graphics.setColor(this.getForeground());
        if (graphics.getFontMetrics().stringWidth(s) <= this.m_StartX + this.m_W - n) {
            graphics.drawString(s, n, n2);
        }
    }
    
    private int getPrecision(final double n) {
        final String string = new Double(Math.round(n * 100.0) / 100.0).toString();
        if (string.indexOf(".") == -1) {
            return 0;
        }
        if (string.length() - string.indexOf(".") == 2 && string.charAt(string.length() - 1) == '0') {
            return 0;
        }
        if (string.length() - string.indexOf(".") == 2 && string.charAt(string.length() - 1) != '0') {
            return 1;
        }
        return 2;
    }
}
