// 
// Decompiled by Procyon v0.5.30
// 

package com.objectplanet.gui;

import java.awt.Cursor;
import java.awt.image.ImageObserver;
import java.applet.AppletContext;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.awt.Font;
import java.awt.Event;
import java.awt.FontMetrics;
import java.net.URL;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Insets;
import java.awt.Color;
import java.util.Hashtable;
import java.applet.Applet;

public class PieChartApplet extends Applet implements Runnable
{
    private Double[][] data;
    private boolean[][] selectedSamples;
    private String[] seriesLabels;
    private String[] sampleLabels;
    private String[] legendLabels;
    private Hashtable urlList;
    private Hashtable urlTargetList;
    private Color[] sampleColors;
    private Color[] sampleLabelColors;
    private Color[] seriesLabelColors;
    private String labelDelimiter;
    private String chartTitle;
    private int sampleDecimalCount;
    private boolean legendOn;
    private int legendPosition;
    private boolean valueLabelsOn;
    private boolean display3dOn;
    private Hashtable labelFonts;
    private Hashtable labels;
    private int pieAngle;
    private double pieDepth;
    private boolean sampleLabelsOn;
    private boolean percentLabelsOn;
    private boolean pieLabelsOn;
    private int percentDecimalCount;
    private int selectionStyle;
    private boolean sliceSeperatorOn;
    private Color sliceSeperatorColor;
    private double detachedDistance;
    private Hashtable detachedSlices;
    private Insets graphInsets;
    private int automaticRefreshTime;
    private final int LEFT = 0;
    private final int RIGHT = 1;
    private final int TOP = 2;
    private final int BOTTOM = 3;
    private final int SELECTION_STYLE_TRIANGLE = 0;
    private final int SELECTION_STYLE_CIRCLE = 1;
    private final int SELECTION_STYLE_DETACHED = 2;
    private final int PAINT_2D = 0;
    private final int PAINT_3D = 1;
    private final int PAINT_ALL = 2;
    private Object HAND_CURSOR;
    private Object POINT_CURSOR;
    private boolean hasChanged;
    private boolean autoRepaintOn;
    private Rectangle currentBounds;
    private int legendWidth;
    private int legendHeight;
    private Rectangle[] legendBounds;
    private int selectedSeries;
    private int selectedSample;
    private double lastSelectedTime;
    private Point mousePosition;
    private Image offscreen;
    private Rectangle[] pieBounds;
    private Point[] pieCenter;
    private int[] pieWidth;
    private double[][] angles;
    private boolean displayCopyright;
    private Thread refreshThread;
    private static Color[] DEFAULT_SAMPLE_COLORS;
    
    private String formatNumber(final double n, int max) {
        max = Math.max(0, max);
        if (max == 0 && n < 1000.0 && n > -1000.0) {
            return Math.round(n) + "";
        }
        final long n2 = (long)Math.pow(10.0, max);
        final long round = Math.round(n * n2);
        final long n3 = Math.abs(round) % n2 + n2;
        String s = "";
        if (max > 0) {
            s = "." + (n3 + "").substring(1);
        }
        long n4 = round / n2;
        if (n4 == 0L) {
            String s2;
            if (n < 0.0) {
                s2 = "-0" + s;
            }
            else {
                s2 = "0" + s;
            }
            return s2;
        }
        if (n4 > 0L) {
            while (n4 >= 1L) {
                final long n5 = n4 % 1000L + 1000L;
                if (n5 >= 1000L && n4 >= 1000L) {
                    s = " " + (n5 + "").substring(1) + s;
                }
                else {
                    s = n4 % 1000L + s;
                }
                n4 /= 1000L;
            }
        }
        else if (n4 < 0L) {
            while (n4 <= -1L) {
                final long n6 = n4 % 1000L - 1000L;
                if (n6 <= -1000L && n4 <= -1000L) {
                    s = " " + (n6 + "").substring(2) + s;
                }
                else {
                    s = n4 % 1000L + s;
                }
                n4 /= 1000L;
            }
        }
        return s;
    }
    
    public void stop() {
        if (this.refreshThread != null) {
            this.refreshThread.stop();
            this.refreshThread = null;
        }
    }
    
    private Color[] getColorValues(final String s) {
        Color[] array = null;
        if (s != null && s.trim().length() > 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            array = new Color[stringTokenizer.countTokens()];
            for (int i = 0; i < array.length; ++i) {
                if (stringTokenizer.hasMoreTokens()) {
                    array[i] = this.createColor(stringTokenizer.nextToken().trim());
                }
            }
        }
        return array;
    }
    
    private void paintPies(final Graphics graphics, final Rectangle rectangle) {
        final int seriesCount = this.getSeriesCount();
        if (seriesCount == 1) {
            this.paintPie(graphics, this.pieBounds[0] = rectangle, this.data[0], 0);
        }
        else {
            final int sampleCount = this.getSampleCount();
            this.calculatePieBounds(rectangle, sampleCount);
            for (int i = 0; i < sampleCount; ++i) {
                final Double[] array = new Double[seriesCount];
                for (int j = 0; j < seriesCount; ++j) {
                    array[j] = this.data[j][i];
                }
                this.paintPie(graphics, this.pieBounds[i], array, i);
            }
        }
    }
    
    private URL getSampleURL(final int n, final int n2) {
        URL url = this.urlList.get(this.createURLParam("url", n, n2));
        final int seriesCount = this.getSeriesCount();
        if (url == null) {
            String s;
            if (seriesCount > 1) {
                s = this.createURLParam("url", n, -1);
            }
            else {
                s = this.createURLParam("url", -1, n2);
            }
            url = (URL)this.urlList.get(s);
        }
        if (url == null) {
            url = this.urlList.get(this.createURLParam("url", -1, -1));
        }
        return url;
    }
    
    public void invalidate() {
        this.offscreen = null;
        this.hasChanged = true;
    }
    
    private void paintPieSegment(final Graphics graphics, final Color color, final double n, final double n2, final int n3, final int n4, int n5, int n6, final boolean b, final double n7, final int n8) {
        if (n7 > 0.0) {
            final double n9 = (-n + n2 / 2.0) * 0.017453292519943295;
            n5 += (int)Math.round(Math.cos(n9) * (n3 * (n7 / 2.0)));
            n6 += (int)Math.round(Math.sin(n9) * (n4 * (n7 / 2.0)));
        }
        if (n8 == 1 || n8 == 2) {
            final double n10 = n * 0.017453292519943295;
            final double n11 = (n - n2) * 0.017453292519943295;
            final int n12 = (int)Math.round(n3 * this.pieDepth * (this.pieAngle / 90.0));
            final int n13 = n3 / 2;
            final int n14 = n4 / 2;
            final double n15 = Math.abs(n10 - n11) / (Math.abs(n10 - n11) / 6.283185307179586 * (3.141592653589793 * Math.max(n3, n4)));
            double n16 = n10;
            graphics.setColor(color.darker().darker());
            while (n16 >= n11) {
                n16 -= n15;
                final int n17 = (int)Math.round(Math.cos(n16) * n13);
                final int n18 = (int)Math.round(Math.sin(n16) * n14);
                if (n18 < 0) {
                    graphics.drawLine(n5 + n17, n6 - n18, n5 + n17, n6 - n18 + n12 + 1);
                }
                else {
                    graphics.drawLine(n5 + n17, n6 - n18 + 1, n5 + n17, n6 - n18 + n12 + 2);
                }
            }
        }
        if (n8 == 0 || n8 == 2) {
            final int n19 = n5 - n3 / 2;
            final int n20 = n6 - n4 / 2;
            graphics.setColor(color);
            graphics.fillArc(n19, n20, n3 + 1, n4 + 1, (int)Math.round(n), (int)Math.round(-n2));
            graphics.setColor(color.darker());
            graphics.drawArc(n19, n20, n3, n4, (int)Math.round(n), (int)Math.round(-n2));
        }
        if (b) {
            this.paintSelectionMarker(graphics, color, (int)Math.round(-n), (int)Math.round(-n2), n3, n4, n5, n6, this.selectionStyle);
        }
    }
    
    private String getSeriesLabel(final int n) {
        if (this.seriesLabels != null && n >= 0 && n < this.seriesLabels.length) {
            return this.seriesLabels[n];
        }
        return null;
    }
    
    private String[] getLegendLabels() {
        if (this.legendLabels != null) {
            return this.legendLabels;
        }
        if (this.getSeriesCount() == 1) {
            return this.sampleLabels;
        }
        return this.seriesLabels;
    }
    
    private void paintLabel(final Graphics graphics, final int n, final int n2, final boolean b, final FontMetrics fontMetrics) {
        final int seriesCount = this.getSeriesCount();
        final Point point = this.pieCenter[(seriesCount > 1) ? n2 : 0];
        if (point == null) {
            return;
        }
        double n3;
        if (seriesCount == 1) {
            if (n2 < this.angles[0].length - 1) {
                n3 = -((this.angles[0][n2] + this.angles[0][n2 + 1]) / 2.0);
            }
            else {
                n3 = -((this.angles[0][n2] - 270.0) / 2.0);
            }
        }
        else if (n < this.angles.length - 1) {
            n3 = -((this.angles[n][n2] + this.angles[n + 1][n2]) / 2.0);
        }
        else {
            n3 = -((this.angles[n][n2] - 270.0) / 2.0);
        }
        double n4 = 1.0;
        final int n5 = this.pieWidth[(seriesCount == 1) ? 0 : n2];
        if (this.display3dOn) {
            n4 = (n5 - (int)Math.round(n5 * (this.pieAngle / 90.0))) / n5;
        }
        final double n6 = n3 * 0.017453292519943295;
        final int n7 = point.x + (int)Math.round(Math.cos(n6) * n5 / 3.0);
        final int n8 = point.y + (int)Math.round(Math.sin(n6) * n5 / 3.0 * n4);
        final String constructLabel = this.constructLabel(n, n2, b);
        final int stringWidth = fontMetrics.stringWidth(constructLabel);
        final int height = fontMetrics.getHeight();
        final int n9 = n7 - stringWidth / 2;
        final int n10 = n8 - height / 2;
        final int min = Math.min(Math.max(0, n9), this.size().width - stringWidth - 6);
        graphics.setColor(new Color(255, 255, 231));
        graphics.fillRect(min, n10, stringWidth + 4, height + 2);
        graphics.setColor(Color.black);
        graphics.drawRect(min, n10, stringWidth + 4, height + 2);
        graphics.drawString(constructLabel, min + 3, n10 + height - 1);
    }
    
    private void setDetachedSlice(final int n, final int n2, final double n3) {
        final Double n4 = new Double(n * 1000000 + n2);
        if (n3 != 0.0) {
            this.detachedSlices.put(n4, new Double(n3));
        }
        else {
            this.detachedSlices.remove(n4);
        }
        this.hasChanged = true;
    }
    
    private double getDetachedSlice(final int n, final int n2) {
        final Double n3 = this.detachedSlices.get(new Double(n * 1000000 + n2));
        if (n3 != null) {
            return n3;
        }
        return 0.0;
    }
    
    private void checkCopyrightNotice(final Event event) {
        if (this.displayCopyright) {
            final Dimension size = this.size();
            final int n = size.height / 2;
            final int n2 = size.width / 2;
            final int stringWidth = this.getFontMetrics(new Font("Dialog", 0, 10)).stringWidth("www.objectplanet.com/comp/PieChartApplet");
            if (event.x >= n2 - stringWidth / 2 && event.y >= n + 36 && event.x <= n2 + stringWidth / 2 && event.y <= n + 46) {
                try {
                    final URL url = new URL("http://objectplanet.com/comp/PieChartApplet/");
                    final AppletContext appletContext = this.getAppletContext();
                    if (appletContext != null) {
                        appletContext.showDocument(url, "ObjectPlanet");
                    }
                }
                catch (MalformedURLException ex) {}
            }
            else {
                this.displayCopyright = false;
                this.repaint();
            }
            return;
        }
        final Dimension size2 = this.size();
        if (event.x > 0 && event.x < 11 && event.y > size2.height - 11 && event.y < size2.height) {
            this.displayCopyright = true;
            this.repaint();
        }
    }
    
    private double getPercentValue(final int n, final int n2) {
        if (n < 0 || n >= this.getSeriesCount()) {
            throw new IllegalArgumentException("Invalid series index: " + n);
        }
        if (n2 < 0 || n2 >= this.getSampleCount()) {
            throw new IllegalArgumentException("Invalid sample index: " + n2);
        }
        final int seriesCount = this.getSeriesCount();
        if (seriesCount == 1) {
            final int sampleCount = this.getSampleCount();
            double n3 = 0.0;
            for (int i = 0; i < sampleCount; ++i) {
                final Double n4 = this.data[0][i];
                if (n4 != null && n4 > 0.0) {
                    n3 += n4;
                }
            }
            final Double n5 = this.data[0][n2];
            if (n5 != null && n5 >= 0.0 && n3 > 0.0) {
                return n5 / n3 * 100.0;
            }
        }
        else {
            double n6 = 0.0;
            for (int j = 0; j < seriesCount; ++j) {
                final Double n7 = this.data[j][n2];
                if (n7 != null && n7 > 0.0) {
                    n6 += n7;
                }
            }
            final Double n8 = this.data[n][n2];
            if (n8 != null && n8 > 0.0 && n6 > 0.0) {
                return n8 / n6 * 100.0;
            }
        }
        return 0.0;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private Color createColor(String lowerCase) {
        if (lowerCase == null || lowerCase.length() == 0) {
            return null;
        }
        if (lowerCase.indexOf(",") >= 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(lowerCase, ", ");
            try {
                return new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
            }
            catch (Exception ex) {
                System.out.println("Invalid RGB color: " + lowerCase);
                return null;
            }
        }
        if (lowerCase.startsWith("#")) {
            try {
                return new Color(Integer.parseInt(lowerCase.substring(1, 3), 16), Integer.parseInt(lowerCase.substring(3, 5), 16), Integer.parseInt(lowerCase.substring(5, 7), 16));
            }
            catch (Exception ex2) {
                System.out.println("Invalid hex color: " + lowerCase);
                return null;
            }
        }
        lowerCase = lowerCase.toLowerCase();
        if (lowerCase.equals("black")) {
            return Color.black;
        }
        if (lowerCase.equals("blue")) {
            return Color.blue;
        }
        if (lowerCase.equals("cyan")) {
            return Color.cyan;
        }
        if (lowerCase.equals("darkgray")) {
            return Color.darkGray;
        }
        if (lowerCase.equals("gray")) {
            return Color.gray;
        }
        if (lowerCase.equals("green")) {
            return Color.green;
        }
        if (lowerCase.equals("lightgray")) {
            return Color.lightGray;
        }
        if (lowerCase.equals("magenta")) {
            return Color.magenta;
        }
        if (lowerCase.equals("orange")) {
            return Color.orange;
        }
        if (lowerCase.equals("pink")) {
            return Color.pink;
        }
        if (lowerCase.equals("red")) {
            return Color.red;
        }
        if (lowerCase.equals("white")) {
            return Color.white;
        }
        if (lowerCase.equals("yellow")) {
            return Color.yellow;
        }
        System.out.println("Invalid color name: " + lowerCase);
        return null;
    }
    
    private void autoRepaint() {
        if (this.autoRepaintOn) {
            this.repaint();
        }
    }
    
    private void handleMouseUp(final Event event) {
        for (int i = 0; i < this.selectedSamples.length; ++i) {
            for (int j = 0; j < this.selectedSamples[i].length; ++j) {
                this.selectedSamples[i][j] = false;
            }
        }
        final Point checkSelection = this.checkSelection(new Point(event.x, event.y), false);
        if (checkSelection != null) {
            final double lastSelectedTime = System.currentTimeMillis();
            final boolean b = lastSelectedTime - this.lastSelectedTime < 300.0 && checkSelection.x == this.selectedSeries && checkSelection.y == this.selectedSample;
            this.selectedSeries = checkSelection.x;
            if (checkSelection.y > -1) {
                this.selectedSamples[checkSelection.x][checkSelection.y] = true;
                this.selectedSample = checkSelection.y;
            }
            else {
                for (int k = 0; k < this.selectedSamples[checkSelection.x].length; ++k) {
                    this.selectedSamples[checkSelection.x][k] = true;
                }
                this.selectedSample = -1;
            }
            if (b) {
                this.openURL(this.selectedSeries, this.selectedSample);
            }
            this.lastSelectedTime = lastSelectedTime;
        }
        else {
            this.selectedSeries = -1;
            this.selectedSample = -1;
        }
        this.hasChanged = true;
        this.repaint();
    }
    
    private Color getSampleColor(final int n) {
        Color color = PieChartApplet.DEFAULT_SAMPLE_COLORS[n % PieChartApplet.DEFAULT_SAMPLE_COLORS.length];
        if (this.sampleColors != null && this.sampleColors.length > 0) {
            color = this.sampleColors[n % this.sampleColors.length];
            if (color == null) {
                color = PieChartApplet.DEFAULT_SAMPLE_COLORS[n % Math.min(this.sampleColors.length, PieChartApplet.DEFAULT_SAMPLE_COLORS.length)];
            }
        }
        return color;
    }
    
    public void start() {
        if (this.automaticRefreshTime > 0) {
            (this.refreshThread = new Thread(this)).start();
        }
    }
    
    private void paintChart(final Graphics graphics) {
        final Dimension size = this.size();
        final int n = size.height / 2;
        final int n2 = size.width / 2;
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Dialog", 0, 10));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final String s = "ObjectPlanet's";
        graphics.drawString(s, n2 - fontMetrics.stringWidth(s) / 2, n - 35);
        graphics.setFont(new Font("Dialog", 1, 20));
        final FontMetrics fontMetrics2 = graphics.getFontMetrics();
        final String s2 = "PieChartApplet 2.03";
        graphics.drawString(s2, n2 - fontMetrics2.stringWidth(s2) / 2, n - 15);
        graphics.setFont(new Font("Dialog", 0, 10));
        final FontMetrics fontMetrics3 = graphics.getFontMetrics();
        final String s3 = "(banner removed with registration)";
        graphics.drawString(s3, n2 - fontMetrics3.stringWidth(s3) / 2, n);
        final String s4 = "Copyright 1998-2000";
        graphics.drawString(s4, n2 - fontMetrics3.stringWidth(s4) / 2, n + 15);
        final String s5 = "ObjectPlanet, Inc. - all rights reserved";
        graphics.drawString(s5, n2 - fontMetrics3.stringWidth(s5) / 2, n + 30);
        final String s6 = "www.objectplanet.com/comp/PieChartApplet";
        graphics.setColor(Color.blue);
        graphics.drawString(s6, n2 - fontMetrics3.stringWidth(s6) / 2, n + 45);
        graphics.drawLine(n2 - fontMetrics3.stringWidth(s6) / 2, n + 46, n2 + fontMetrics3.stringWidth(s6) / 2, n + 46);
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, size.width - 1, size.height - 1);
    }
    
    private void paintSelectionMarker(final Graphics graphics, final Color color, final double n, final double n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        if (n7 == 1) {
            final double n8 = (n - n2 / 2.0) * 0.017453292519943295;
            final int n9 = n5 + (int)Math.round(Math.cos(n8) * (n3 * 0.4));
            final int n10 = n6 + (int)Math.round(Math.sin(n8) * (n4 * 0.4));
            final int max = Math.max(n3 / 16, 3);
            final int max2 = Math.max(n4 / 16, 3);
            final int n11 = max / 2;
            final int n12 = max2 / 2;
            graphics.setColor(color.darker());
            graphics.fillOval(n9 - n11, n10 - n12, max, max2);
            graphics.setColor(color.darker());
            graphics.drawArc(n9 - n11, n10 - n12, max, max2, 45, 180);
            graphics.setColor(color.darker().darker());
            graphics.drawArc(n9 - n11 + 1, n10 - n12 + 1, max - 2, max2 - 2, 45, 180);
            graphics.setColor(color);
            graphics.drawArc(n9 - n11 + 1, n10 - n12 + 1, max - 2, max2 - 2, 45, -180);
            graphics.setColor(color.brighter());
            graphics.drawArc(n9 - n11, n10 - n12, max, max2, 45, -180);
        }
        else if (n7 == 0) {
            final double n13 = (n - n2 / 2.0) * 0.017453292519943295;
            final int n14 = (int)Math.round(n3 * 0.7);
            final int n15 = (int)Math.round(n4 * 0.7);
            final int n16 = n5 + (int)Math.round(Math.cos(n13) * (n3 * 0.1));
            final int n17 = n6 + (int)Math.round(Math.sin(n13) * (n4 * 0.1));
            final int n18 = n16 - (int)Math.round(n14 / 2.0);
            final int n19 = n17 - (int)Math.round(n15 / 2.0);
            graphics.setColor(color.darker());
            graphics.fillArc(n18, n19, n14 + 1, n15 + 1, (int)(-Math.round(n)), (int)Math.round(n2));
        }
    }
    
    private Double[] getDoubleValues(final String s) {
        Double[] array = null;
        if (s != null && s.trim().length() > 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            array = new Double[stringTokenizer.countTokens()];
            for (int i = 0; i < array.length; ++i) {
                try {
                    array[i] = new Double(stringTokenizer.nextToken());
                }
                catch (NumberFormatException ex) {}
            }
        }
        return array;
    }
    
    private String[] getStringValues(final String s, final String s2) {
        String[] array = null;
        if (s != null && s.trim().length() > 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
            array = new String[stringTokenizer.countTokens()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = stringTokenizer.nextToken().trim();
            }
        }
        return array;
    }
    
    public void setParameter(String lowerCase, String lowerCase2) {
        if (lowerCase == null) {
            return;
        }
        lowerCase = lowerCase.trim().toLowerCase();
        if (lowerCase.equals("samplevalues")) {
            final Double[] doubleValues = this.getDoubleValues(lowerCase2);
            if (doubleValues != null && lowerCase2.trim().length() > 0) {
                if (this.data[0] == null) {
                    this.data[0] = doubleValues;
                }
                for (int i = 0; i < this.data[0].length; ++i) {
                    if (i < doubleValues.length) {
                        this.data[0][i] = doubleValues[i];
                    }
                    else {
                        this.data[0][i] = null;
                    }
                }
            }
        }
        else if (lowerCase.equals("samplecount")) {
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                try {
                    this.setDataCount(-1, Math.max(0, Integer.parseInt(lowerCase2.trim())));
                }
                catch (NumberFormatException ex) {
                    System.out.println("Invalid sampleCount: " + lowerCase2);
                }
            }
        }
        else if (lowerCase.equals("samplecolors")) {
            this.sampleColors = new Color[PieChartApplet.DEFAULT_SAMPLE_COLORS.length];
            System.arraycopy(PieChartApplet.DEFAULT_SAMPLE_COLORS, 0, this.sampleColors, 0, this.sampleColors.length);
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                final Color[] colorValues = this.getColorValues(lowerCase2);
                if (colorValues != null) {
                    this.sampleColors = colorValues;
                }
            }
        }
        else if (lowerCase.equals("seriescount")) {
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                try {
                    this.setDataCount(Math.max(1, Integer.parseInt(lowerCase2.trim())), -1);
                }
                catch (NumberFormatException ex2) {
                    System.out.println("Invalid seriesCount: " + lowerCase2);
                }
            }
        }
        else if (lowerCase.startsWith("samplevalues_")) {
            try {
                final int int1 = Integer.parseInt(lowerCase.substring(13));
                if (this.data != null && int1 >= 0 && int1 < this.data.length) {
                    if (this.data[int1] != null) {
                        for (int j = 0; j < this.data[int1].length; ++j) {
                            this.data[int1][j] = null;
                        }
                    }
                    final Double[] doubleValues2 = this.getDoubleValues(lowerCase2);
                    if (doubleValues2 != null) {
                        if (this.data[int1] == null) {
                            this.data[int1] = new Double[doubleValues2.length];
                        }
                        for (int k = 0; k < this.data[int1].length; ++k) {
                            if (k < doubleValues2.length) {
                                this.data[int1][k] = doubleValues2[k];
                            }
                            else {
                                this.data[int1][k] = null;
                            }
                        }
                    }
                }
            }
            catch (NumberFormatException ex3) {
                System.out.println("Invalid series index: " + lowerCase);
            }
        }
        else if (lowerCase.equals("labeldelimiter")) {
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                this.labelDelimiter = lowerCase2;
            }
            else {
                this.labelDelimiter = ",";
            }
        }
        else if (lowerCase.equals("serieslabels")) {
            if (this.seriesLabels == null) {
                this.seriesLabels = new String[this.data.length];
            }
            for (int l = 0; l < this.seriesLabels.length; ++l) {
                this.seriesLabels[l] = null;
            }
            final String[] stringValues = this.getStringValues(lowerCase2, this.labelDelimiter);
            if (stringValues != null) {
                for (int n = 0; n < this.seriesLabels.length; ++n) {
                    if (n < stringValues.length) {
                        this.seriesLabels[n] = stringValues[n];
                    }
                    else {
                        this.seriesLabels[n] = null;
                    }
                }
            }
        }
        else if (lowerCase.equals("serieslabelcolors")) {
            this.seriesLabelColors = this.getColorValues(lowerCase2);
        }
        else if (lowerCase.equals("samplelabels")) {
            if (this.sampleLabels == null) {
                this.sampleLabels = new String[this.data[0].length];
            }
            for (int n2 = 0; n2 < this.sampleLabels.length; ++n2) {
                this.sampleLabels[n2] = null;
            }
            final String[] stringValues2 = this.getStringValues(lowerCase2, this.labelDelimiter);
            if (stringValues2 != null) {
                for (int n3 = 0; n3 < this.sampleLabels.length; ++n3) {
                    if (n3 < stringValues2.length) {
                        this.sampleLabels[n3] = stringValues2[n3];
                    }
                    else {
                        this.sampleLabels[n3] = null;
                    }
                }
            }
        }
        else if (lowerCase.equals("samplelabelcolors")) {
            this.sampleLabelColors = this.getColorValues(lowerCase2);
        }
        else if (lowerCase.equals("legendlabels")) {
            this.legendLabels = null;
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                final String[] stringValues3 = this.getStringValues(lowerCase2, this.labelDelimiter);
                if (stringValues3.length > 1) {
                    this.legendLabels = stringValues3;
                }
                else if (stringValues3.length == 1 && stringValues3[0] != null) {
                    if (stringValues3[0].trim().toLowerCase().equals("samplelabels")) {
                        this.legendLabels = this.sampleLabels;
                    }
                    else if (stringValues3[0].trim().toLowerCase().equals("serieslabels")) {
                        this.legendLabels = this.seriesLabels;
                    }
                    else {
                        this.legendLabels = stringValues3;
                    }
                }
            }
        }
        else if (lowerCase.equals("valuelabelson")) {
            this.valueLabelsOn = (lowerCase2 != null && lowerCase2.trim().toLowerCase().equals("true"));
        }
        else if (lowerCase.equals("sampledecimalcount")) {
            this.sampleDecimalCount = 0;
            try {
                if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                    this.sampleDecimalCount = Math.max(0, Integer.parseInt(lowerCase2.trim()));
                }
            }
            catch (NumberFormatException ex4) {
                System.out.println("Invalid sampleDecimalCount: " + lowerCase2);
            }
        }
        else if (lowerCase.equals("charttitle")) {
            this.chartTitle = lowerCase2;
        }
        else if (lowerCase.equals("legendon")) {
            this.legendOn = (lowerCase2 != null && lowerCase2.trim().toLowerCase().equals("true"));
        }
        else if (lowerCase.equals("legendposition")) {
            this.legendPosition = 1;
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                lowerCase2 = lowerCase2.trim().toLowerCase();
                if (lowerCase2.equals("right")) {
                    this.legendPosition = 1;
                }
                else if (lowerCase2.equals("top")) {
                    this.legendPosition = 2;
                }
                else if (lowerCase2.equals("bottom")) {
                    this.legendPosition = 3;
                }
                else if (lowerCase2.equals("left")) {
                    this.legendPosition = 0;
                }
                else {
                    System.out.println("Invalid legendPosition: " + lowerCase2);
                }
            }
        }
        else if (lowerCase.equals("3dmodeon")) {
            this.display3dOn = (lowerCase2 != null && lowerCase2.trim().toLowerCase().equals("true"));
        }
        else if (lowerCase.equals("foreground")) {
            this.setForeground(Color.black);
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                final Color color = this.createColor(lowerCase2);
                if (color != null) {
                    this.setForeground(color);
                }
            }
        }
        else if (lowerCase.equals("background")) {
            this.setBackground(new Color(231, 221, 231));
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                final Color color2 = this.createColor(lowerCase2);
                if (color2 != null) {
                    this.setBackground(color2);
                }
            }
        }
        else if (lowerCase.equals("titlefont")) {
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                this.setFont("titleFont", this.createFont(lowerCase2, "Dialog", 1, 14));
            }
            else {
                this.setFont("titleFont", new Font("Dialog", 1, 14));
            }
        }
        else if (lowerCase.equals("font")) {
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                this.setFont(this.createFont(lowerCase2, "Arial", 0, 11));
            }
            else {
                this.setFont(new Font("Arial", 0, 11));
            }
        }
        else if (lowerCase.endsWith("font")) {
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                this.setFont(lowerCase, this.createFont(lowerCase2, "Arial", 0, 11));
            }
            else {
                this.setFont(lowerCase, null);
            }
        }
        else if (lowerCase.equals("graphinsets")) {
            this.graphInsets.top = -1;
            this.graphInsets.left = -1;
            this.graphInsets.bottom = -1;
            this.graphInsets.right = -1;
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                final StringTokenizer stringTokenizer = new StringTokenizer(lowerCase2.trim(), ",");
                int int2 = -1;
                int int3 = -1;
                int int4 = -1;
                int int5 = -1;
                try {
                    if (stringTokenizer.hasMoreTokens()) {
                        int2 = Integer.parseInt(((String)stringTokenizer.nextElement()).trim());
                    }
                    if (stringTokenizer.hasMoreTokens()) {
                        int3 = Integer.parseInt(((String)stringTokenizer.nextElement()).trim());
                    }
                    if (stringTokenizer.hasMoreTokens()) {
                        int4 = Integer.parseInt(((String)stringTokenizer.nextElement()).trim());
                    }
                    if (stringTokenizer.hasMoreTokens()) {
                        int5 = Integer.parseInt(((String)stringTokenizer.nextElement()).trim());
                    }
                    this.graphInsets.top = int2;
                    this.graphInsets.left = int3;
                    this.graphInsets.bottom = int4;
                    this.graphInsets.right = int5;
                }
                catch (NumberFormatException ex5) {
                    System.out.println("Invalid graphinsets: " + lowerCase2);
                }
            }
        }
        else if (lowerCase.startsWith("urltarget")) {
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                this.urlTargetList.put(lowerCase, lowerCase2);
            }
            else if (lowerCase.equals("urltarget")) {
                this.urlTargetList.put(lowerCase, "_self");
            }
            else {
                this.urlTargetList.remove(lowerCase);
            }
        }
        else if (lowerCase.startsWith("url")) {
            this.urlList.remove(lowerCase);
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                final URL url = this.createURL(lowerCase2);
                if (url != null) {
                    this.urlList.put(lowerCase, url);
                }
            }
        }
        else if (lowerCase.startsWith("valuelabelprefix") || lowerCase.startsWith("valuelabelpostfix")) {
            this.setLabel(lowerCase, lowerCase2);
        }
        else if (lowerCase.equals("angle")) {
            this.pieAngle = 20;
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                try {
                    this.pieAngle = Math.min(80, Math.max(10, Integer.parseInt(lowerCase2.trim())));
                }
                catch (NumberFormatException ex6) {
                    System.out.println("Invalid angle: " + lowerCase2);
                }
            }
        }
        else if (lowerCase.equals("depth")) {
            this.pieDepth = 0.4;
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                try {
                    this.pieDepth = new Double(lowerCase2.trim());
                    this.pieDepth = Math.min(1.0, Math.max(0.0, this.pieDepth));
                }
                catch (NumberFormatException ex7) {
                    System.out.println("Invalid depth: " + lowerCase2);
                }
            }
        }
        else if (lowerCase.equals("samplelabelson")) {
            this.sampleLabelsOn = (lowerCase2 != null && lowerCase2.trim().toLowerCase().equals("true"));
        }
        else if (lowerCase.equals("percentlabelson")) {
            this.percentLabelsOn = (lowerCase2 != null && lowerCase2.trim().toLowerCase().equals("true"));
        }
        else if (lowerCase.equals("pielabelson")) {
            this.pieLabelsOn = (lowerCase2 != null && lowerCase2.trim().toLowerCase().equals("true"));
        }
        else if (lowerCase.equals("percentdecimalcount")) {
            this.percentDecimalCount = 0;
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                try {
                    this.percentDecimalCount = Math.max(0, Integer.parseInt(lowerCase2.trim()));
                }
                catch (NumberFormatException ex8) {
                    System.out.println("Invalid percentDecimalCount: " + lowerCase2);
                }
            }
        }
        else if (lowerCase.equals("sliceseperatoron")) {
            this.sliceSeperatorOn = (lowerCase2 != null && lowerCase2.trim().toLowerCase().equals("true"));
        }
        else if (lowerCase.equals("sliceseperatorcolor")) {
            this.sliceSeperatorColor = this.createColor(lowerCase2);
        }
        else if (lowerCase.equals("selectionstyle")) {
            this.selectionStyle = 0;
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                if (lowerCase2.trim().toLowerCase().equals("circle")) {
                    this.selectionStyle = 1;
                }
                else if (lowerCase2.trim().toLowerCase().equals("detached")) {
                    this.selectionStyle = 2;
                }
            }
        }
        else if (lowerCase.equals("detacheddistance")) {
            this.detachedDistance = 0.1;
            if (lowerCase2 != null && lowerCase2.trim().length() > 0) {
                try {
                    this.detachedDistance = Math.max(0.0, new Double(lowerCase2.trim()));
                }
                catch (NumberFormatException ex9) {
                    System.out.println("Invalid detachDistance: " + lowerCase2);
                }
            }
        }
        else if (lowerCase.startsWith("detachedslices")) {
            int int6 = lowerCase.equals("detachedslices") ? 0 : -1;
            try {
                if (lowerCase.length() > 15) {
                    int6 = Integer.parseInt(lowerCase.substring(15));
                }
            }
            catch (NumberFormatException ex10) {
                System.out.println("Invalid index in detachedSlices_N parameter: " + lowerCase);
            }
            final Double[] doubleValues3 = this.getDoubleValues(lowerCase2);
            final int seriesCount = this.getSeriesCount();
            final int sampleCount = this.getSampleCount();
            if (seriesCount == 1) {
                for (int n4 = 0; n4 < sampleCount; ++n4) {
                    this.setDetachedSlice(0, n4, 0.0);
                }
                if (doubleValues3 != null) {
                    final double detachedDistance = this.detachedDistance;
                    for (int n5 = 0; n5 < doubleValues3.length; ++n5) {
                        final int intValue = (int)(Object)doubleValues3[n5];
                        if (intValue < sampleCount) {
                            this.setDetachedSlice(0, intValue, detachedDistance);
                        }
                    }
                }
            }
            else if (int6 < sampleCount) {
                for (int n6 = 0; n6 < seriesCount; ++n6) {
                    this.setDetachedSlice(n6, int6, 0.0);
                }
                if (doubleValues3 != null) {
                    final double detachedDistance2 = this.detachedDistance;
                    for (int n7 = 0; n7 < doubleValues3.length; ++n7) {
                        final int intValue2 = (int)(Object)doubleValues3[n7];
                        if (intValue2 < seriesCount) {
                            this.setDetachedSlice(intValue2, int6, detachedDistance2);
                        }
                    }
                }
            }
        }
        else {
            System.out.println("Invalid parameter name: " + lowerCase);
        }
        this.hasChanged = true;
        this.autoRepaint();
    }
    
    private URL createURL(final String s) {
        URL url = null;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            final URL documentBase = this.getDocumentBase();
            if (documentBase == null) {
                return null;
            }
            String s2 = documentBase.toExternalForm();
            final int lastIndex = s2.lastIndexOf("?");
            if (lastIndex > -1) {
                s2 = s2.substring(0, lastIndex);
            }
            if (!s2.endsWith("/")) {
                s2 = s2.substring(0, s2.lastIndexOf("/") + 1);
            }
            if (s2.startsWith("file:/\\")) {
                s2 = "file://" + s2.substring(8);
            }
            try {
                url = new URL(s2 + s);
            }
            catch (MalformedURLException ex2) {
                System.out.println("Invalid URL: " + s2);
            }
        }
        return url;
    }
    
    private Font createFont(final String s, final String s2, final int n, final int n2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        String nextToken = s2;
        int n3 = Math.max(0, n2);
        int fontType = n;
        if (stringTokenizer.hasMoreTokens()) {
            nextToken = stringTokenizer.nextToken();
        }
        if (stringTokenizer.hasMoreTokens()) {
            fontType = this.getFontType(stringTokenizer.nextToken());
        }
        if (stringTokenizer.hasMoreTokens()) {
            try {
                n3 = Integer.parseInt(stringTokenizer.nextToken().trim());
            }
            catch (NumberFormatException ex) {
                n3 = 12;
            }
        }
        return new Font(nextToken, fontType, n3);
    }
    
    private void paintPie(final Graphics graphics, final Rectangle rectangle, final Double[] array, final int n) {
        int min;
        int n2 = min = Math.min(rectangle.width, rectangle.height);
        final int x = rectangle.x + rectangle.width / 2;
        int y = rectangle.y + rectangle.height / 2;
        final Font font = this.getFont("pieLabelFont");
        if (font != null) {
            graphics.setFont(font);
        }
        else {
            graphics.setFont(this.getFont());
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        if (this.display3dOn) {
            final int n3 = min - (int)Math.round(n2 * (this.pieAngle / 90.0));
            final int n4 = (int)Math.round(n2 * this.pieDepth * (this.pieAngle / 90.0));
            final double n5 = rectangle.width / n2;
            int n6 = (int)Math.round(n2 * n5);
            int n7 = (int)Math.round(n3 * n5);
            final int n8 = (int)Math.round(n6 * this.pieDepth * (this.pieAngle / 90.0));
            final int n9 = n7 + n8;
            y = rectangle.y + rectangle.height / 2 - n8 / 2;
            if (y - n7 / 2 < rectangle.y) {
                n7 = (int)Math.round(rectangle.height * (n7 / n9));
                final int n10 = rectangle.height - n7;
                n6 = (int)Math.round(n7 * (n2 / n3));
                y = rectangle.y + rectangle.height / 2 - n10 / 2;
            }
            n2 = n6;
            min = n7;
        }
        if (this.pieLabelsOn) {
            if (this.display3dOn) {
                final int n11 = y + min / 2 + fontMetrics.getHeight() - fontMetrics.getDescent() + 2 + (int)Math.round(n2 * this.pieDepth * (this.pieAngle / 90.0));
                if (n11 > rectangle.y + rectangle.height) {
                    final int n12 = n11 - (rectangle.y + rectangle.height);
                    min -= n12;
                    y -= n12 / 2;
                }
            }
            else {
                final int height = fontMetrics.getHeight();
                min = (n2 = min - height);
                y -= height / 2;
            }
        }
        this.pieCenter[n].x = x;
        this.pieCenter[n].y = y;
        this.pieWidth[n] = n2;
        double n13 = 0.0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null && array[i] > 0.0) {
                n13 += array[i];
            }
        }
        double n14 = 90.0;
        final int seriesCount = this.getSeriesCount();
        for (int j = 0; j < array.length; ++j) {
            final double n15 = (array[j] != null) ? array[j] : 0.0;
            double n16 = 0.0;
            if (n13 > 0.0) {
                n16 = n15 / n13 * 360.0;
            }
            if (seriesCount == 1) {
                this.angles[0][j] = n14;
            }
            else {
                this.angles[j][n] = n14;
            }
            n14 -= n16;
        }
        if (this.display3dOn) {
            final int n17 = y;
            final int n18 = y + (int)Math.round(n2 * this.pieDepth * (this.pieAngle / 90.0));
            for (int k = 0; k < array.length; ++k) {
                double n19 = -270.0;
                double n20;
                if (seriesCount == 1) {
                    if (k < this.angles[0].length - 1) {
                        n19 = this.angles[0][k + 1];
                    }
                    n20 = this.angles[0][k];
                }
                else {
                    if (k < this.angles.length - 1) {
                        n19 = this.angles[k + 1][n];
                    }
                    n20 = this.angles[k][n];
                }
                if (n20 != n19) {
                    boolean b;
                    if (seriesCount == 1) {
                        b = this.selectedSamples[0][k];
                    }
                    else {
                        b = this.selectedSamples[k][n];
                    }
                    double n21;
                    if (b && this.selectionStyle == 2) {
                        n21 = this.detachedDistance;
                    }
                    else if (seriesCount == 1) {
                        n21 = this.getDetachedSlice(0, k);
                    }
                    else {
                        n21 = this.getDetachedSlice(k, n);
                    }
                    this.paintPieSegment(graphics, this.getSampleColor(k).darker(), n20, n20 - n19, n2, min, x, n18, false, n21, 0);
                }
            }
            y = n17;
        }
        if (this.display3dOn) {
            double n22 = -270.0;
            double n24;
            for (int n23 = array.length - 1; n22 < -90.0 && n23 >= 0; --n23, n22 = n24) {
                if (seriesCount == 1) {
                    n24 = this.angles[0][n23];
                }
                else {
                    n24 = this.angles[n23][n];
                }
                boolean b2;
                if (seriesCount == 1) {
                    b2 = this.selectedSamples[0][n23];
                }
                else {
                    b2 = this.selectedSamples[n23][n];
                }
                double n25;
                if (b2 && this.selectionStyle == 2) {
                    n25 = this.detachedDistance;
                }
                else if (seriesCount == 1) {
                    n25 = this.getDetachedSlice(0, n23);
                }
                else {
                    n25 = this.getDetachedSlice(n23, n);
                }
                this.paintPieSegment(graphics, this.getSampleColor(n23), n24, n24 - n22, n2, min, x, y, b2, n25, 1);
            }
            for (int l = 0; l < array.length; ++l) {
                double n26 = -270.0;
                double n27;
                if (seriesCount == 1) {
                    if (l < this.angles[0].length - 1) {
                        n26 = this.angles[0][l + 1];
                    }
                    n27 = this.angles[0][l];
                }
                else {
                    if (l < this.angles.length - 1) {
                        n26 = this.angles[l + 1][n];
                    }
                    n27 = this.angles[l][n];
                }
                if (n27 >= -90.0) {
                    boolean b3;
                    if (seriesCount == 1) {
                        b3 = this.selectedSamples[0][l];
                    }
                    else {
                        b3 = this.selectedSamples[l][n];
                    }
                    double n28;
                    if (b3 && this.selectionStyle == 2) {
                        n28 = this.detachedDistance;
                    }
                    else if (seriesCount == 1) {
                        n28 = this.getDetachedSlice(0, l);
                    }
                    else {
                        n28 = this.getDetachedSlice(l, n);
                    }
                    this.paintPieSegment(graphics, this.getSampleColor(l), n27, n27 - n26, n2, min, x, y, b3, n28, 1);
                }
            }
        }
        for (int n29 = 0; n29 < array.length; ++n29) {
            double n30 = -270.0;
            double n31;
            if (seriesCount == 1) {
                if (n29 < this.angles[0].length - 1) {
                    n30 = this.angles[0][n29 + 1];
                }
                n31 = this.angles[0][n29];
            }
            else {
                if (n29 < this.angles.length - 1) {
                    n30 = this.angles[n29 + 1][n];
                }
                n31 = this.angles[n29][n];
            }
            if (n31 != n30) {
                boolean b4;
                if (seriesCount == 1) {
                    b4 = this.selectedSamples[0][n29];
                }
                else {
                    b4 = this.selectedSamples[n29][n];
                }
                double n32;
                if (b4 && this.selectionStyle == 2) {
                    n32 = this.detachedDistance;
                }
                else if (seriesCount == 1) {
                    n32 = this.getDetachedSlice(0, n29);
                }
                else {
                    n32 = this.getDetachedSlice(n29, n);
                }
                this.paintPieSegment(graphics, this.getSampleColor(n29), n31, n31 - n30, n2, min, x, y, b4, n32, 0);
            }
        }
        if (this.sliceSeperatorOn) {
            for (int n33 = 0; n33 < array.length; ++n33) {
                double n34;
                if (seriesCount == 1) {
                    n34 = this.angles[0][n33];
                }
                else {
                    n34 = this.angles[n33][n];
                }
                final double n35 = -n34 * 0.017453292519943295;
                final int n36 = x + (int)Math.round(Math.cos(n35) * (n2 * 0.5));
                final int n37 = y + (int)Math.round(Math.sin(n35) * (min * 0.5));
                if (this.sliceSeperatorColor != null) {
                    graphics.setColor(this.sliceSeperatorColor);
                }
                else {
                    graphics.setColor(this.getSampleColor(n33).darker());
                }
                graphics.drawLine(x, y, n36, n37);
            }
        }
        if (this.pieLabelsOn) {
            String s = "";
            if (seriesCount == 1 && n < this.seriesLabels.length) {
                s = this.seriesLabels[n];
            }
            else if (seriesCount != 1 && n < this.sampleLabels.length) {
                s = this.sampleLabels[n];
            }
            if (s != null && s.trim().length() > 0) {
                final int n38 = x - fontMetrics.stringWidth(s) / 2;
                int n39 = y + min / 2 + fontMetrics.getHeight() - fontMetrics.getDescent() + 2;
                if (this.display3dOn) {
                    n39 += (int)Math.round(n2 * this.pieDepth * (this.pieAngle / 90.0));
                }
                Color foreground = this.getForeground();
                if (this.sampleLabelColors != null && n < this.sampleLabelColors.length && this.sampleLabelColors[n] != null) {
                    foreground = this.sampleLabelColors[n];
                }
                graphics.setColor(foreground);
                graphics.drawString(s, n38, n39);
            }
        }
    }
    
    private String constructLabel(final int n, final int n2, final boolean b) {
        final int seriesCount = this.getSeriesCount();
        final int sampleCount = this.getSampleCount();
        if (n < 0 || n >= seriesCount || n2 < 0 || n2 >= sampleCount) {
            return "";
        }
        String s = this.getLabel("valueLabelPrefix_" + n);
        if (s == null) {
            s = this.getLabel("valueLabelPrefix");
        }
        String s2 = this.getLabel("valueLabelPostfix_" + n);
        if (s2 == null) {
            s2 = this.getLabel("valueLabelPostfix");
        }
        final Double n3 = this.data[n][n2];
        final double n4 = (n3 != null) ? n3 : 0.0;
        String s3 = "";
        final int sampleDecimalCount = this.sampleDecimalCount;
        if (this.sampleLabelsOn) {
            if (seriesCount == 1 && this.getSampleLabel(n2) != null) {
                s3 = this.getSampleLabel(n2);
            }
            else if (seriesCount > 1) {
                if (b && this.getSeriesLabel(n) != null) {
                    s3 = this.getSeriesLabel(n) + " : ";
                }
                if (this.getSampleLabel(n2) != null) {
                    s3 += this.getSampleLabel(n2);
                }
            }
        }
        if (this.valueLabelsOn && this.percentLabelsOn) {
            if (s3.length() > 0) {
                s3 += " : ";
            }
            final String formatNumber = this.formatNumber(n4, sampleDecimalCount);
            final String s4 = (s != null) ? (s + formatNumber) : formatNumber;
            s3 = s3 + ((s2 != null) ? (s4 + s2) : s4) + " : " + this.formatNumber(this.getPercentValue(n, n2), this.percentDecimalCount) + "%";
        }
        else if (this.valueLabelsOn) {
            if (this.sampleLabelsOn) {
                s3 += " : ";
            }
            final String formatNumber2 = this.formatNumber(n4, sampleDecimalCount);
            final String s5 = (s != null) ? (s + formatNumber2) : formatNumber2;
            s3 += ((s2 != null) ? (s5 + s2) : s5);
        }
        else if (this.percentLabelsOn) {
            if (this.sampleLabelsOn) {
                s3 += " : ";
            }
            s3 = s3 + this.formatNumber(this.getPercentValue(n, n2), this.percentDecimalCount) + "%";
        }
        return s3;
    }
    
    private void paintTitle(final Graphics graphics, final Dimension dimension) {
        if (this.chartTitle != null) {
            graphics.setFont(this.getFont("titleFont"));
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int n = dimension.width / 2 - fontMetrics.stringWidth(this.chartTitle) / 2;
            final int n2 = fontMetrics.getMaxAscent() + 2;
            graphics.setColor(this.getForeground());
            graphics.drawString(this.chartTitle, n, n2);
        }
    }
    
    private Point checkSelection(final Point point, final boolean b) {
        Point point2 = null;
        final int seriesCount = this.getSeriesCount();
        final int sampleCount = this.getSampleCount();
        if (this.legendOn && this.legendBounds != null && seriesCount > 0 && sampleCount > 0) {
            for (int i = 0; i < this.legendBounds.length; ++i) {
                if (this.legendBounds[i] != null && this.legendBounds[i].inside(point.x, point.y)) {
                    if (seriesCount == 1) {
                        point2 = new Point(0, i);
                    }
                    else {
                        point2 = new Point(i, -1);
                    }
                    break;
                }
            }
        }
        if (point2 == null) {
            int n = -1;
            for (int j = 0; j < this.pieBounds.length; ++j) {
                final Rectangle rectangle = this.pieBounds[j];
                if (rectangle != null && rectangle.inside(point.x, point.y)) {
                    n = j;
                    break;
                }
            }
            if (n >= 0) {
                point.x -= this.pieCenter[n].x;
                point.y -= this.pieCenter[n].y;
                if (this.display3dOn) {
                    final int n2 = this.pieWidth[n];
                    point.y = (int)Math.round(point.y * (n2 / (n2 - (int)Math.round(n2 * (this.pieAngle / 90.0)))));
                }
                if (Math.sqrt(point.x * point.x + point.y * point.y) > this.pieWidth[n] / 2) {
                    return null;
                }
                double n3 = -(Math.atan2(point.y, point.x) * 57.29577951308232);
                if (n3 > 90.0 && n3 <= 180.0) {
                    n3 -= 360.0;
                }
                if (seriesCount == 1) {
                    for (int k = 0; k < sampleCount; ++k) {
                        double n4;
                        double n5;
                        if (k < sampleCount - 1) {
                            n4 = Math.min(this.angles[0][k], this.angles[0][k + 1]);
                            n5 = Math.max(this.angles[0][k], this.angles[0][k + 1]);
                        }
                        else {
                            n4 = Math.min(this.angles[0][k], -270.0);
                            n5 = Math.max(this.angles[0][k], -270.0);
                        }
                        if (n3 >= n4 && n3 < n5) {
                            return new Point(0, k);
                        }
                    }
                }
                else {
                    for (int l = 0; l < seriesCount; ++l) {
                        double n6;
                        double n7;
                        if (l < seriesCount - 1) {
                            n6 = Math.min(this.angles[l][n], this.angles[l + 1][n]);
                            n7 = Math.max(this.angles[l][n], this.angles[l + 1][n]);
                        }
                        else {
                            n6 = Math.min(this.angles[l][n], -270.0);
                            n7 = Math.max(this.angles[l][n], -270.0);
                        }
                        if (n3 >= n6 && n3 < n7) {
                            return new Point(l, n);
                        }
                    }
                }
            }
        }
        return point2;
    }
    
    private int getSeriesCount() {
        if (this.data != null) {
            return this.data.length;
        }
        return 0;
    }
    
    private void setDataCount(int length, int length2) {
        if (length <= -1) {
            try {
                length = this.data.length;
            }
            catch (NullPointerException ex) {
                length = 1;
            }
        }
        if (length2 <= -1) {
            try {
                length2 = this.data[0].length;
            }
            catch (IndexOutOfBoundsException ex2) {
                length2 = 0;
            }
        }
        final Double[][] data = new Double[length][length2];
        for (int i = 0; i < data.length; ++i) {
            if (i < this.data.length) {
                final Double[] array = data[i];
                for (int j = 0; j < array.length; ++j) {
                    if (j < this.data[i].length) {
                        data[i][j] = this.data[i][j];
                    }
                }
            }
        }
        this.data = data;
        final String[] seriesLabels = new String[length];
        for (int k = 0; k < seriesLabels.length; ++k) {
            if (k < this.seriesLabels.length) {
                seriesLabels[k] = this.seriesLabels[k];
            }
        }
        this.seriesLabels = seriesLabels;
        final String[] sampleLabels = new String[length2];
        for (int l = 0; l < sampleLabels.length; ++l) {
            if (l < this.sampleLabels.length) {
                sampleLabels[l] = this.sampleLabels[l];
            }
        }
        this.sampleLabels = sampleLabels;
        final boolean[][] selectedSamples = new boolean[length][length2];
        for (int n = 0; n < this.selectedSamples.length; ++n) {
            for (int n2 = 0; n2 < this.selectedSamples[n].length; ++n2) {
                if (n < selectedSamples.length && n2 < selectedSamples[n].length) {
                    selectedSamples[n][n2] = this.selectedSamples[n][n2];
                }
            }
        }
        this.selectedSamples = selectedSamples;
        final Rectangle[] pieBounds = new Rectangle[(length == 1) ? 1 : length2];
        for (int n3 = 0; n3 < pieBounds.length; ++n3) {
            if (this.pieBounds != null && n3 < this.pieBounds.length && this.pieBounds[n3] != null) {
                pieBounds[n3] = this.pieBounds[n3];
            }
            else {
                pieBounds[n3] = new Rectangle();
            }
        }
        this.pieBounds = pieBounds;
        final Point[] pieCenter = new Point[this.pieBounds.length];
        for (int n4 = 0; n4 < pieCenter.length; ++n4) {
            if (this.pieCenter != null && n4 < this.pieCenter.length && this.pieCenter[n4] != null) {
                pieCenter[n4] = this.pieCenter[n4];
            }
            else {
                pieCenter[n4] = new Point(0, 0);
            }
        }
        this.pieCenter = pieCenter;
        this.pieWidth = new int[this.pieCenter.length];
        this.angles = new double[length][length2];
    }
    
    private int getSampleCount() {
        if (this.data != null && this.data.length > 0 && this.data[0] != null) {
            return this.data[0].length;
        }
        return 0;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.displayCopyright) {
            this.paintChart(graphics);
        }
        else {
            this.render(graphics);
            graphics.setColor(Color.red);
            graphics.fill3DRect(1, this.size().height - 10, 9, 9, true);
        }
    }
    
    private int getFontType(String trim) {
        trim = trim.toLowerCase().trim();
        if (trim.equals("bold")) {
            return 1;
        }
        if (trim.equals("italic")) {
            return 2;
        }
        if (trim.equals("bolditalic") || trim.equals("italicbold")) {
            return 3;
        }
        return 0;
    }
    
    private void setFont(String lowerCase, final Font font) {
        if (lowerCase == null || lowerCase.trim().length() < 1) {
            return;
        }
        lowerCase = lowerCase.trim().toLowerCase();
        if (font != null) {
            this.labelFonts.put(lowerCase, font);
        }
        else {
            this.labelFonts.remove(lowerCase);
        }
    }
    
    private Font getFont(final String s) {
        if (s == null) {
            this.getFont();
        }
        final Font font = this.labelFonts.get(s.trim().toLowerCase());
        if (font != null) {
            return font;
        }
        return this.getFont();
    }
    
    private String getSampleLabel(final int n) {
        if (this.sampleLabels != null && n >= 0 && n < this.sampleLabels.length) {
            return this.sampleLabels[n];
        }
        return null;
    }
    
    private void paintLabels(final Graphics graphics, final int n, final int n2) {
        final int seriesCount = this.getSeriesCount();
        final int sampleCount = this.getSampleCount();
        if (graphics == null || n < 0 || n >= seriesCount || n2 < -1 || n2 >= sampleCount) {
            return;
        }
        final Font font = this.getFont("floatingLabelFont");
        if (font != null) {
            graphics.setFont(font);
        }
        else {
            graphics.setFont(this.getFont());
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        if (n2 >= 0) {
            this.paintLabel(graphics, n, n2, true, fontMetrics);
        }
        else {
            for (int i = 0; i < sampleCount; ++i) {
                this.paintLabel(graphics, n, i, false, fontMetrics);
            }
        }
    }
    
    static {
        PieChartApplet.DEFAULT_SAMPLE_COLORS = new Color[] { new Color(132, 130, 255), new Color(132, 32, 99), new Color(255, 255, 198), new Color(165, 227, 231), new Color(99, 0, 132), new Color(255, 130, 132), new Color(0, 130, 198), new Color(198, 195, 255), new Color(0, 0, 132), new Color(255, 0, 255), new Color(255, 255, 0), new Color(0, 255, 255), new Color(132, 0, 132), new Color(132, 0, 0), new Color(0, 130, 132), new Color(0, 0, 255), new Color(0, 207, 255), new Color(107, 255, 255), new Color(206, 255, 206), new Color(255, 255, 156) };
    }
    
    private void initParameter(final String s, final String s2) {
        if (s2 != null) {
            this.setParameter(s, s2);
        }
    }
    
    protected void render(final Graphics graphics) {
        final Dimension size = this.size();
        final Rectangle graphBounds = this.getGraphBounds();
        if (this.offscreen == null) {
            this.offscreen = this.createImage(Math.max(1, size.width), Math.max(1, size.height));
            this.hasChanged = true;
        }
        if (this.hasChanged || this.offscreen == null) {
            Graphics graphics2 = graphics;
            if (this.offscreen != null) {
                graphics2 = this.offscreen.getGraphics();
            }
            graphics2.setColor(this.getBackground());
            graphics2.fillRect(0, 0, size.width, size.height);
            this.paintPies(graphics2, graphBounds);
            if (this.legendOn) {
                this.paintLegend(graphics2, graphBounds, this.getLegendLabels());
            }
            this.paintTitle(graphics2, size);
            if (graphics2 != graphics) {
                graphics2.dispose();
            }
            this.hasChanged = false;
        }
        if (this.offscreen != null) {
            graphics.drawImage(this.offscreen, 0, 0, this);
        }
        if (this.sampleLabelsOn || this.valueLabelsOn || this.percentLabelsOn) {
            this.paintLabels(graphics, this.selectedSeries, this.selectedSample);
        }
    }
    
    private void openURL(final int n, final int n2) {
        final URL sampleURL = this.getSampleURL(n, n2);
        if (sampleURL != null) {
            String s = this.urlTargetList.get(this.createURLParam("urltarget", n, n2));
            if (s == null) {
                String s2;
                if (this.getSeriesCount() > 1) {
                    s2 = this.createURLParam("urltarget", n, -1);
                }
                else {
                    s2 = this.createURLParam("urltarget", -1, n2);
                }
                s = (String)this.urlTargetList.get(s2);
            }
            if (s == null) {
                s = this.urlTargetList.get("urltarget");
            }
            final AppletContext appletContext = this.getAppletContext();
            if (appletContext != null) {
                appletContext.showDocument(sampleURL, s);
            }
        }
    }
    
    public void setAutomaticRepaintOn(final boolean autoRepaintOn) {
        this.autoRepaintOn = autoRepaintOn;
    }
    
    private Rectangle getGraphBounds() {
        if (!this.hasChanged && this.currentBounds != null) {
            return this.currentBounds;
        }
        final Dimension size = this.size();
        final Rectangle currentBounds = new Rectangle(10, 10, size.width - 20, size.height - 20);
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont("legendFont"));
        final int height = fontMetrics.getHeight();
        if (this.chartTitle != null && this.chartTitle.length() > 0) {
            final int ascent = this.getFontMetrics(this.getFont("titleFont")).getAscent();
            final Rectangle rectangle = currentBounds;
            rectangle.y += ascent;
            final Rectangle rectangle2 = currentBounds;
            rectangle2.height -= ascent;
        }
        if (this.legendOn) {
            final String[] legendLabels = this.getLegendLabels();
            final int sampleCount = this.getSampleCount();
            final int seriesCount = this.getSeriesCount();
            int n = legendLabels.length;
            if (seriesCount == 1) {
                int n2 = 0;
                for (int i = 0; i < legendLabels.length; ++i) {
                    if (i < sampleCount && this.data[0][i] != null) {
                        ++n2;
                    }
                }
                n = Math.min(n2, legendLabels.length);
            }
            this.legendWidth = 0;
            for (int j = 0; j < legendLabels.length; ++j) {
                if (legendLabels[j] != null) {
                    final int stringWidth = fontMetrics.stringWidth(legendLabels[j]);
                    if (stringWidth > this.legendWidth) {
                        this.legendWidth = stringWidth;
                    }
                }
            }
            this.legendWidth += 14;
            this.legendHeight = height * n;
            switch (this.legendPosition) {
                default: {
                    final Rectangle rectangle3 = currentBounds;
                    rectangle3.width -= this.legendWidth;
                    break;
                }
                case 0: {
                    final Rectangle rectangle4 = currentBounds;
                    rectangle4.x += this.legendWidth;
                    final Rectangle rectangle5 = currentBounds;
                    rectangle5.width -= this.legendWidth;
                    break;
                }
                case 2: {
                    final Rectangle rectangle6 = currentBounds;
                    rectangle6.y += 20 + height;
                    final Rectangle rectangle7 = currentBounds;
                    rectangle7.height -= 20 + height;
                    this.legendWidth = size.width - 20;
                    break;
                }
                case 3: {
                    final Rectangle rectangle8 = currentBounds;
                    rectangle8.height -= height + 10;
                    this.legendWidth = size.width - 20;
                    this.legendHeight = height + 10;
                    break;
                }
            }
        }
        final int x = currentBounds.x;
        final int n3 = size.width - currentBounds.width - currentBounds.x;
        final int y = currentBounds.y;
        final int n4 = size.height - currentBounds.height - currentBounds.y;
        final int y2 = (this.graphInsets.top == -1) ? y : this.graphInsets.top;
        final int x2 = (this.graphInsets.left == -1) ? x : this.graphInsets.left;
        final int n5 = (this.graphInsets.bottom == -1) ? n4 : this.graphInsets.bottom;
        final int n6 = (this.graphInsets.right == -1) ? n3 : this.graphInsets.right;
        currentBounds.x = x2;
        currentBounds.width = size.width - x2 - n6;
        currentBounds.y = y2;
        currentBounds.height = size.height - y2 - n5;
        return this.currentBounds = currentBounds;
    }
    
    private void calculatePieBounds(final Rectangle rectangle, final int n) {
        int n2 = 0;
        int n3 = n;
        int n4 = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (i * j >= n) {
                    final int min = Math.min(rectangle.width / i, rectangle.height / j);
                    if (min > n2) {
                        n2 = min;
                        n3 = i;
                        n4 = j;
                    }
                }
            }
        }
        int n5 = 0;
        for (int k = 0; k < n4; ++k) {
            for (int l = 0; l < n3; ++l) {
                if (n5 < n) {
                    final Rectangle rectangle2 = this.pieBounds[n5];
                    rectangle2.width = rectangle.width / n3;
                    rectangle2.height = rectangle.height / n4;
                    rectangle2.x = rectangle.x + rectangle2.width * l;
                    rectangle2.y = rectangle.y + rectangle2.height * k;
                    final Rectangle rectangle3 = rectangle2;
                    rectangle3.width -= 10;
                    final Rectangle rectangle4 = rectangle2;
                    rectangle4.height -= 10;
                    final Rectangle rectangle5 = rectangle2;
                    rectangle5.x += 5;
                    final Rectangle rectangle6 = rectangle2;
                    rectangle6.y += 5;
                    ++n5;
                }
            }
        }
    }
    
    public void run() {
        try {
            while (true) {
                Thread.sleep(this.automaticRefreshTime);
                this.repaint();
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void init() {
        this.autoRepaintOn = false;
        this.data = new Double[0][0];
        this.sampleLabels = new String[0];
        this.seriesLabels = new String[0];
        this.urlList = new Hashtable();
        this.urlTargetList = new Hashtable();
        this.labels = new Hashtable();
        this.labelFonts = new Hashtable();
        this.selectedSamples = new boolean[0][0];
        this.mousePosition = new Point(0, 0);
        this.detachedSlices = new Hashtable();
        this.graphInsets = new Insets(-1, -1, -1, -1);
        this.automaticRefreshTime = 3600000;
        try {
            this.HAND_CURSOR = new Cursor(12);
            this.POINT_CURSOR = new Cursor(0);
            if (this.HAND_CURSOR != null) {
                this.setCursor((Cursor)this.HAND_CURSOR);
            }
        }
        catch (NoSuchMethodError noSuchMethodError) {
            System.out.println("Could not set Cursor");
            this.HAND_CURSOR = null;
            this.POINT_CURSOR = null;
        }
        catch (Exception ex) {
            System.out.println("Could not find Cursor class");
        }
        this.setForeground(Color.black);
        this.setBackground(new Color(231, 221, 231));
        this.setFont(new Font("Arial", 0, 11));
        this.setFont("titleFont", new Font("Arial", 1, 14));
        this.labelDelimiter = ",";
        this.legendPosition = 1;
        this.selectedSeries = -1;
        this.selectedSample = -1;
        this.pieAngle = 20;
        this.pieDepth = 0.4;
        this.detachedDistance = 0.1;
        int max = 1;
        int n = 0;
        final Double[] doubleValues = this.getDoubleValues(this.getParameter("sampleValues"));
        if (doubleValues != null) {
            n = doubleValues.length;
            final String parameter = this.getParameter("sampleCount");
            if (parameter != null) {
                try {
                    n = Math.max(0, Integer.parseInt(parameter));
                }
                catch (NumberFormatException ex2) {
                    System.out.println("Invalid sampleCount: " + parameter);
                }
            }
            this.setDataCount(max, n);
            this.initParameter("sampleValues", this.getParameter("sampleValues"));
        }
        if (doubleValues == null) {
            final String parameter2 = this.getParameter("seriesCount");
            if (parameter2 != null) {
                try {
                    max = Math.max(1, Integer.parseInt(parameter2));
                }
                catch (NumberFormatException ex3) {
                    System.out.println("Invalid seriesCount: " + parameter2);
                }
            }
            final String parameter3 = this.getParameter("sampleCount");
            boolean b = true;
            if (parameter3 != null) {
                try {
                    n = Math.max(0, Integer.parseInt(parameter3));
                    b = false;
                }
                catch (NumberFormatException ex4) {
                    System.out.println("Invalid sampleCount: " + parameter3);
                }
            }
            if (b) {
                for (int i = 0; i < max; ++i) {
                    final Double[] doubleValues2 = this.getDoubleValues(this.getParameter("sampleValues_" + i));
                    if (doubleValues2 != null) {
                        n = Math.max(n, doubleValues2.length);
                    }
                }
            }
            this.setDataCount(max, n);
            for (int j = 0; j < max; ++j) {
                this.initParameter("sampleValues_" + j, this.getParameter("sampleValues_" + j));
            }
        }
        this.seriesLabels = new String[max];
        this.sampleLabels = new String[n];
        this.angles = new double[max][n];
        this.pieBounds = new Rectangle[(max == 1) ? 1 : n];
        this.pieCenter = new Point[this.pieBounds.length];
        this.pieWidth = new int[this.pieBounds.length];
        for (int k = 0; k < this.pieBounds.length; ++k) {
            this.pieBounds[k] = new Rectangle();
            this.pieCenter[k] = new Point(0, 0);
        }
        this.initParameter("chartTitle", this.getParameter("chartTitle"));
        this.initParameter("sampleColors", this.getParameter("sampleColors"));
        this.initParameter("sampleDecimalCount", this.getParameter("sampleDecimalCount"));
        this.initParameter("legendOn", this.getParameter("legendOn"));
        this.initParameter("labelDelimiter", this.getParameter("labelDelimiter"));
        this.initParameter("sampleLabels", this.getParameter("sampleLabels"));
        this.initParameter("seriesLabels", this.getParameter("seriesLabels"));
        this.initParameter("sampleLabelColors", this.getParameter("sampleLabelColors"));
        this.initParameter("seriesLabelColors", this.getParameter("seriesLabelColors"));
        this.initParameter("legendPosition", this.getParameter("legendPosition"));
        this.initParameter("background", this.getParameter("background"));
        this.initParameter("foreground", this.getParameter("foreground"));
        this.initParameter("titleFont", this.getParameter("titleFont"));
        this.initParameter("font", this.getParameter("font"));
        this.initParameter("legendFont", this.getParameter("legendFont"));
        this.initParameter("3DModeOn", this.getParameter("3DModeOn"));
        this.initParameter("legendLabels", this.getParameter("legendLabels"));
        this.initParameter("valueLabelPrefix", this.getParameter("valueLabelPrefix"));
        this.initParameter("valueLabelPostfix", this.getParameter("valueLabelPostfix"));
        for (int l = 0; l < max; ++l) {
            this.initParameter("valueLabelPrefix_" + l, this.getParameter("valueLabelPrefix_" + l));
            this.initParameter("valueLabelPostfix_" + l, this.getParameter("valueLabelPostfix_" + l));
        }
        this.initParameter("angle", this.getParameter("angle"));
        this.initParameter("depth", this.getParameter("depth"));
        this.initParameter("sampleLabelsOn", this.getParameter("sampleLabelsOn"));
        this.initParameter("valueLabelsOn", this.getParameter("valueLabelsOn"));
        this.initParameter("percentLabelsOn", this.getParameter("percentLabelsOn"));
        this.initParameter("pieLabelsOn", this.getParameter("pieLabelsOn"));
        this.initParameter("percentDecimalCount", this.getParameter("percentDecimalCount"));
        this.initParameter("sliceSeperatorOn", this.getParameter("sliceSeperatorOn"));
        this.initParameter("sliceSeperatorColor", this.getParameter("sliceSeperatorColor"));
        this.initParameter("selectionStyle", this.getParameter("selectionStyle"));
        this.initParameter("pieLabelFont", this.getParameter("pieLabelFont"));
        this.initParameter("floatingLabelFont", this.getParameter("floatingLabelFont"));
        this.initParameter("detachedDistance", this.getParameter("detachedDistance"));
        this.initParameter("graphInsets", this.getParameter("graphInsets"));
        this.initParameter("detachedSlices", this.getParameter("detachedSlices"));
        for (int n2 = (this.getSeriesCount() == 1) ? 1 : this.getSampleCount(), n3 = 0; n3 < n2; ++n3) {
            final String parameter4 = this.getParameter("detachedSlices_" + n3);
            if (parameter4 != null) {
                this.initParameter("detachedSlices_" + n3, parameter4);
            }
        }
        this.initParameter("url", this.getParameter("url"));
        this.setParameter("urltarget", "_self");
        this.initParameter("urltarget", this.getParameter("urltarget"));
        for (int max2 = Math.max(max, n), n4 = 0; n4 < max2; ++n4) {
            this.initParameter("url_" + n4, this.getParameter("url_" + n4));
            this.initParameter("urltarget_" + n4, this.getParameter("urltarget_" + n4));
        }
        for (int n5 = 0; n5 < max; ++n5) {
            for (int n6 = 0; n6 < n; ++n6) {
                final String string = "url_" + n5 + "_" + n6;
                this.initParameter(string, this.getParameter(string));
                final String string2 = "urltarget_" + n5 + "_" + n6;
                this.initParameter(string2, this.getParameter(string2));
            }
        }
        this.autoRepaintOn = true;
    }
    
    private void paintLegend(final Graphics graphics, final Rectangle rectangle, final String[] array) {
        if (graphics == null || rectangle == null || array == null) {
            return;
        }
        final int n = 6;
        int n2 = 0;
        int n3 = 0;
        graphics.setFont(this.getFont("legendFont"));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final int seriesCount = this.getSeriesCount();
        final int sampleCount = this.getSampleCount();
        int n4 = array.length;
        if (seriesCount == 1) {
            int n5 = 0;
            for (int i = 0; i < sampleCount; ++i) {
                if (this.data[0][i] != null) {
                    ++n5;
                }
            }
            n4 = Math.min(n5, array.length);
        }
        final int max = Math.max(1, n4);
        if (this.legendBounds == null || this.legendBounds.length != max) {
            this.legendBounds = new Rectangle[max];
            for (int j = 0; j < this.legendBounds.length; ++j) {
                this.legendBounds[j] = new Rectangle();
            }
        }
        int n6 = 0;
        int n7 = 0;
        switch (this.legendPosition) {
            default: {
                final Dimension size = this.size();
                n2 = size.width - this.legendWidth - 2;
                n3 = n2 + n + 4;
                n6 = size.height / 2 - this.legendHeight / 2;
                n7 = n6 + n + 1;
                break;
            }
            case 0: {
                n2 = 8;
                n3 = n2 + n + 4;
                n6 = this.size().height / 2 - this.legendHeight / 2;
                n7 = n6 + n + 1;
                break;
            }
            case 2: {
                n7 = rectangle.y - fontMetrics.getDescent() - 10;
                n6 = n7 - n - height;
                break;
            }
            case 3: {
                n6 = this.size().height - fontMetrics.getHeight() - n - 5;
                n7 = n6 + n + height;
                break;
            }
        }
        int n8 = 0;
        for (int k = 0; k < array.length; ++k) {
            if (seriesCount != 1 || k >= sampleCount || this.data[0][k] != null) {
                if (n8 < this.legendBounds.length) {
                    String s = array[k];
                    if (s == null) {
                        s = " ";
                    }
                    if (this.legendPosition == 2 || this.legendPosition == 3) {
                        n2 = this.legendWidth * (k / max) + (this.legendWidth / max / 2 + n);
                        n3 = n2 - fontMetrics.stringWidth(s) / 2 + n / 2;
                    }
                    graphics.setColor(this.getSampleColor(k));
                    graphics.fillRect(n2, n6, n, n);
                    graphics.setColor(Color.black);
                    graphics.drawRect(n2, n6, n, n);
                    graphics.setColor(this.getForeground());
                    if (seriesCount == 1) {
                        if (this.sampleLabelColors != null && k < this.sampleLabelColors.length && this.sampleLabelColors[k] != null) {
                            graphics.setColor(this.sampleLabelColors[k]);
                        }
                    }
                    else if (seriesCount > 1 && this.seriesLabelColors != null && k < this.seriesLabelColors.length && this.seriesLabelColors[k] != null) {
                        graphics.setColor(this.seriesLabelColors[k]);
                    }
                    graphics.drawString(s, n3, n7);
                    int stringWidth = 5;
                    if (s != null && fontMetrics != null) {
                        stringWidth = fontMetrics.stringWidth(s);
                    }
                    final Rectangle rectangle2 = this.legendBounds[n8];
                    if (this.legendPosition == 1 || this.legendPosition == 0) {
                        rectangle2.reshape(n2 - 3, n7 - height + 2, stringWidth + n * 2 + 3, height + 1);
                    }
                    else {
                        rectangle2.reshape(n2 + n / 2 - stringWidth / 2 - 3, n6 - 3, stringWidth + 6, n * 2 + height);
                    }
                    graphics.setColor(this.getForeground());
                    if (seriesCount > 1) {
                        boolean b = false;
                        for (int l = 0; l < sampleCount; ++l) {
                            if (k < this.selectedSamples.length && this.selectedSamples[k] != null && this.selectedSamples[k][l]) {
                                b = true;
                                break;
                            }
                        }
                        if (b) {
                            graphics.drawRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
                        }
                    }
                    else if (k < sampleCount && this.selectedSamples[0][k]) {
                        graphics.drawRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
                    }
                    ++n8;
                    if (this.legendPosition == 1 || this.legendPosition == 0) {
                        n6 += height + 1;
                        n7 += height + 1;
                    }
                }
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                this.checkCopyrightNotice(event);
                break;
            }
            case 502: {
                this.handleMouseUp(event);
                break;
            }
            case 505: {
                final boolean b = this.selectedSample != -1 || this.selectedSeries != -1;
                this.selectedSample = -1;
                this.selectedSeries = -1;
                if (b) {
                    this.repaint();
                }
                break;
            }
            case 503: {
                if (this.POINT_CURSOR != null) {
                    this.setCursor((Cursor)this.POINT_CURSOR);
                }
                if (this.urlList.size() > 0) {
                    final Point checkSelection = this.checkSelection(new Point(event.x, event.y), false);
                    if (checkSelection != null && this.getSampleURL(checkSelection.x, checkSelection.y) != null && this.HAND_CURSOR != null) {
                        this.setCursor((Cursor)this.HAND_CURSOR);
                    }
                }
                if (this.sampleLabelsOn || this.valueLabelsOn || this.percentLabelsOn) {
                    final Point checkSelection2 = this.checkSelection(new Point(event.x, event.y), false);
                    boolean b2;
                    if (checkSelection2 != null) {
                        b2 = (checkSelection2.x != this.selectedSeries || checkSelection2.y != this.selectedSample);
                        this.selectedSeries = checkSelection2.x;
                        this.selectedSample = checkSelection2.y;
                    }
                    else {
                        b2 = (this.selectedSample != -1 || this.selectedSeries != -1);
                        this.selectedSample = -1;
                        this.selectedSeries = -1;
                    }
                    if (b2) {
                        this.repaint();
                    }
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    private String createURLParam(final String s, final int n, final int n2) {
        String s2 = s;
        if (n >= 0) {
            s2 = s2 + "_" + n;
        }
        if (n2 >= 0) {
            s2 = s2 + "_" + n2;
        }
        return s2;
    }
    
    private void setLabel(String trim, final String s) {
        if (trim != null && trim.length() > 0) {
            trim = trim.toLowerCase().trim();
            if (s != null && s.length() > 0) {
                this.labels.put(trim, s);
            }
            else {
                this.labels.remove(trim);
            }
        }
    }
    
    private String getLabel(String trim) {
        if (trim != null) {
            trim = trim.toLowerCase().trim();
            return this.labels.get(trim);
        }
        return null;
    }
}
