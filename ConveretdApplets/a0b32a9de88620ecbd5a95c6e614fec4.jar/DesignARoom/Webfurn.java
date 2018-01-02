// 
// Decompiled by Procyon v0.5.30
// 

package DesignARoom;

import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Polygon;
import java.awt.Color;
import java.io.Serializable;

public abstract class Webfurn implements Serializable, Cloneable
{
    public int relL;
    public int relT;
    public String oW;
    public String oH;
    public String oWu;
    public String oHu;
    public String oN;
    public String oC;
    public String oS;
    public String oA;
    public boolean spinning;
    boolean grabbed;
    boolean wasGrabbed;
    boolean doubleClicked;
    boolean wasDoubleClicked;
    int grabbedX;
    int grabbedY;
    long clickedTime;
    int l;
    int actualL;
    int t;
    int actualT;
    int w;
    int actualW;
    int h;
    int actualH;
    char wU;
    char hU;
    String n;
    Color c;
    String s;
    double a;
    int midX;
    int midY;
    int[] xpoints;
    int[] ypoints;
    public Polygon actualFurn;
    Font horizFont;
    Font vertFont;
    betterPolygon leftTab;
    betterPolygon topTab;
    betterPolygon rightTab;
    betterPolygon bottomTab;
    char tab;
    int downX;
    int downY;
    int Tdim;
    static int apHeight;
    static int apWidth;
    static int apRmHeight;
    static int apDivHeight;
    static int apStTop;
    static WebApplet wA;
    
    public Webfurn(final int actualL, final int actualT, final int actualW, final int actualH, final char wu, final char hu, final String n, final Color c, final String s, final double a, final String ow, final String oh, final String oWu, final String oHu, final String on, final String oc, final String os, final String oa) {
        this.spinning = false;
        this.grabbed = false;
        this.wasGrabbed = false;
        this.doubleClicked = false;
        this.wasDoubleClicked = false;
        this.clickedTime = 0L;
        this.xpoints = new int[] { 0, 0, 0, 0, 0 };
        this.ypoints = new int[] { 0, 0, 0, 0, 0 };
        this.tab = ' ';
        this.Tdim = 6;
        this.actualL = actualL;
        this.actualT = actualT;
        this.actualW = actualW;
        this.actualH = actualH;
        this.wU = wu;
        this.hU = hu;
        this.n = n;
        this.c = c;
        this.s = s;
        this.a = a;
        this.oW = ow;
        this.oH = oh;
        this.oWu = oWu;
        this.oHu = oHu;
        this.oN = on;
        this.oC = oc;
        this.oS = os;
        this.oA = oa;
        this.midX = this.actualL + this.actualW / 2;
        this.midY = this.actualT + this.actualH / 2;
        this.figurePolyEnclosing();
        this.figurePolyPoints();
    }
    
    public Webfurn() {
        this.spinning = false;
        this.grabbed = false;
        this.wasGrabbed = false;
        this.doubleClicked = false;
        this.wasDoubleClicked = false;
        this.clickedTime = 0L;
        this.xpoints = new int[] { 0, 0, 0, 0, 0 };
        this.ypoints = new int[] { 0, 0, 0, 0, 0 };
        this.tab = ' ';
        this.Tdim = 6;
        this.l = 0;
        this.w = 0;
        this.t = 0;
        this.h = 0;
    }
    
    public abstract void paint(final Graphics p0);
    
    public abstract void unPaint(final Graphics p0);
    
    public abstract boolean boxFits(final int p0, final int p1);
    
    public synchronized Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public boolean equals(final Webfurn webfurn) {
        return (!(this instanceof WebDoorFurn) || ((WebDoorFurn)webfurn).oInd == ((WebDoorFurn)this).oInd) && webfurn.n == this.n && webfurn.l == this.l && webfurn.t == this.t && webfurn.w == this.w && webfurn.h == this.h && webfurn.a == this.a && webfurn.c.equals(this.c) && webfurn.s.equals(this.s);
    }
    
    public int getLeft() {
        return this.l;
    }
    
    public int getActualLeft() {
        return this.actualL;
    }
    
    public int getTop() {
        return this.t;
    }
    
    public int getActualTop() {
        return this.actualT;
    }
    
    public int getWidth() {
        return this.w;
    }
    
    public int getActualWidth() {
        return this.actualW;
    }
    
    public char getWunit() {
        return this.wU;
    }
    
    public int getHeight() {
        return this.h;
    }
    
    public int getActualHeight() {
        return this.actualH;
    }
    
    public char getHunit() {
        return this.hU;
    }
    
    public String getName() {
        return this.n;
    }
    
    public Color getColor() {
        return this.c;
    }
    
    public String getShape() {
        return this.s;
    }
    
    public double getAngle() {
        return this.a;
    }
    
    public void setLeft(final int l) {
        this.l = l;
        this.recalcMidActual();
    }
    
    public void setActualLeft(final int actualL) {
        this.actualL = actualL;
    }
    
    public void setTop(final int t) {
        this.t = t;
        this.recalcMidActual();
    }
    
    public void setActualTop(final int actualT) {
        this.actualT = actualT;
    }
    
    public void setWidth(final int w) {
        this.w = w;
        this.recalcMidActual();
    }
    
    public void setActualWidth(final int actualW) {
        this.actualW = actualW;
    }
    
    public void setWunit(final char wu) {
        this.wU = wu;
    }
    
    public void setHeight(final int h) {
        this.h = h;
        this.recalcMidActual();
    }
    
    public void setActualHeight(final int actualH) {
        this.actualH = actualH;
    }
    
    public void setHunit(final char hu) {
        this.hU = hu;
    }
    
    public void setName(final String n) {
        this.n = n;
    }
    
    public void setColor(final Color c) {
        this.c = c;
    }
    
    public void setShape(final String s) {
        this.s = s;
    }
    
    public void setAngle(final double a) {
        this.a = a;
    }
    
    public void setFonts(final Graphics graphics) {
        this.vertFont = this.fontIfVert(graphics);
        this.horizFont = this.fontIfHoriz(graphics);
    }
    
    public static void receiveConstants(final int apHeight, final int apWidth, final int apRmHeight, final int apDivHeight, final int apStTop, final WebApplet wa) {
        Webfurn.apHeight = apHeight;
        Webfurn.apWidth = apWidth;
        Webfurn.apRmHeight = apRmHeight;
        Webfurn.apDivHeight = apDivHeight;
        Webfurn.apStTop = apStTop;
        Webfurn.wA = wa;
    }
    
    void drawTabs(final Graphics graphics) {
        if (Math.min(this.actualW, this.actualH) < this.Tdim + 3) {
            return;
        }
        this.leftTab = this.calcTab('L');
        this.topTab = this.calcTab('T');
        this.rightTab = this.calcTab('R');
        this.bottomTab = this.calcTab('B');
        graphics.setColor(Color.lightGray);
        graphics.fillPolygon(this.leftTab);
        graphics.fillPolygon(this.topTab);
        graphics.fillPolygon(this.rightTab);
        graphics.fillPolygon(this.bottomTab);
        graphics.setColor(Color.black);
        graphics.drawPolygon(this.leftTab);
        graphics.drawPolygon(this.topTab);
        graphics.drawPolygon(this.rightTab);
        graphics.drawPolygon(this.bottomTab);
    }
    
    betterPolygon calcTab(final char c) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        final int n9 = (this.a != 0) ? 1 : 0;
        switch (c) {
            case 'L': {
                n = (int)(n9 + this.l + (this.actualH / 2 - this.Tdim / 2) * Math.sin(this.a));
                n2 = (int)(n9 + this.t + (this.actualH / 2 + this.Tdim / 2) * Math.cos(this.a));
                n3 = (int)(n9 + this.l + (this.actualH / 2 + this.Tdim / 2) * Math.sin(this.a));
                n4 = (int)(n9 + this.t + (this.actualH / 2 - this.Tdim / 2) * Math.cos(this.a));
                n5 = (int)(n + this.Tdim * Math.sin(this.a) + this.Tdim * Math.cos(this.a));
                n6 = (int)(n4 + this.Tdim * Math.sin(this.a));
                n7 = (int)(n + this.Tdim * Math.cos(this.a));
                n8 = (int)(n4 + this.Tdim * Math.sin(this.a) + this.Tdim * Math.cos(this.a));
                break;
            }
            case 'T': {
                n = (int)(-n9 + this.l + this.w - (this.actualW / 2 + this.Tdim / 2) * Math.cos(this.a));
                n2 = (int)(n9 * 2 + this.t + (this.actualW / 2 - this.Tdim / 2) * Math.sin(this.a));
                n3 = (int)(-n9 + this.l + this.w - (this.actualW / 2 - this.Tdim / 2) * Math.cos(this.a));
                n4 = (int)(n9 * 2 + this.t + (this.actualW / 2 + this.Tdim / 2) * Math.sin(this.a));
                n5 = (int)(n3 - this.Tdim * Math.sin(this.a));
                n6 = (int)(n2 + this.Tdim * Math.sin(this.a) + this.Tdim * Math.cos(this.a));
                n7 = (int)(n3 - this.Tdim * Math.sin(this.a) - this.Tdim * Math.cos(this.a));
                n8 = (int)(n2 + this.Tdim * Math.cos(this.a));
                break;
            }
            case 'R': {
                n = (int)(-1 + this.l + this.w - (this.actualH / 2 - this.Tdim / 2) * Math.sin(this.a));
                n2 = (int)(-n9 + this.t + this.h - (this.actualH / 2 + this.Tdim / 2) * Math.cos(this.a));
                n3 = (int)(-1 + this.l + this.w - (this.actualH / 2 + this.Tdim / 2) * Math.sin(this.a));
                n4 = (int)(-n9 + this.t + this.h - (this.actualH / 2 - this.Tdim / 2) * Math.cos(this.a));
                n5 = (int)(n - this.Tdim * Math.sin(this.a) - this.Tdim * Math.cos(this.a));
                n6 = (int)(n4 - this.Tdim * Math.sin(this.a));
                n7 = (int)(n - this.Tdim * Math.cos(this.a));
                n8 = (int)(n4 - this.Tdim * Math.sin(this.a) - this.Tdim * Math.cos(this.a));
                break;
            }
            case 'B': {
                n = (int)(n9 + this.l + (this.actualW / 2 + this.Tdim / 2) * Math.cos(this.a));
                n2 = (int)(-1 + this.t + this.h - (this.actualW / 2 - this.Tdim / 2) * Math.sin(this.a));
                n3 = (int)(n9 + this.l + (this.actualW / 2 - this.Tdim / 2) * Math.cos(this.a));
                n4 = (int)(-1 + this.t + this.h - (this.actualW / 2 + this.Tdim / 2) * Math.sin(this.a));
                n5 = (int)(n3 + this.Tdim * Math.sin(this.a));
                n6 = (int)(n2 - this.Tdim * Math.sin(this.a) - this.Tdim * Math.cos(this.a));
                n7 = (int)(n3 + this.Tdim * Math.sin(this.a) + this.Tdim * Math.cos(this.a));
                n8 = (int)(n2 - this.Tdim * Math.cos(this.a));
                break;
            }
        }
        return new betterPolygon(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public boolean mouseDown(final Event event, final int downX, final int downY) {
        this.wasGrabbed = this.grabbed;
        this.wasDoubleClicked = this.doubleClicked;
        if (this.actualFurn.inside(downX, downY)) {
            this.grabbed = true;
            this.grabbedX = downX - this.l;
            this.grabbedY = downY - this.t;
            this.tab = ' ';
            if (this.leftTab != null) {
                final WebApplet wa = Webfurn.wA;
                if (WebCanvas.altDown(event) || event.metaDown()) {
                    if (this.leftTab.inside(downX, downY)) {
                        this.tab = 'L';
                    }
                    if (this.topTab.inside(downX, downY)) {
                        this.tab = 'T';
                    }
                    if (this.rightTab.inside(downX, downY)) {
                        this.tab = 'R';
                    }
                    if (this.bottomTab.inside(downX, downY)) {
                        this.tab = 'B';
                    }
                }
            }
            if (this.tab != ' ') {
                this.downX = downX;
                this.downY = downY;
            }
        }
        else {
            this.grabbed = false;
            this.doubleClicked = false;
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        int n3 = 0;
        int n4 = 0;
        switch (this.tab) {
            case ' ': {
                this.l = n - this.grabbedX;
                this.t = n2 - this.grabbedY;
                break;
            }
            case 'L': {
                n3 = this.actualW + 2 * (this.downX - n);
                break;
            }
            case 'T': {
                n4 = this.actualH + 2 * (this.downY - n2);
                break;
            }
            case 'R': {
                n3 = this.actualW + 2 * (n - this.downX);
                break;
            }
            case 'B': {
                n4 = this.actualH + 2 * (n2 - this.downY);
                break;
            }
        }
        if (n3 > this.Tdim + 3) {
            final String string = Float.toString(n3 / Webfurn.wA.wC.multFactor);
            Webfurn.wA.fnWidth.setText(this.fromFeet(string.substring(0, Math.min(4, string.length())), this.wU));
            Webfurn.wA.updateFurn();
            this.downX = n;
            this.downY = n2;
        }
        if (n4 > this.Tdim + 3) {
            final String string2 = Float.toString(n4 / Webfurn.wA.wC.multFactor);
            Webfurn.wA.fnLength.setText(this.fromFeet(string2.substring(0, Math.min(4, string2.length())), this.hU));
            Webfurn.wA.updateFurn();
            this.downX = n;
            this.downY = n2;
        }
        this.recalcMidActual();
        return true;
    }
    
    public boolean keepInBounds(final int n, final int n2) {
        if (this.l < 0) {
            this.l = 0;
        }
        if (this.t < 0) {
            this.t = ((this.a == 0) ? 0 : -1);
        }
        if (this.t >= n2 && this.t < Webfurn.apStTop) {
            this.t = Webfurn.apStTop;
        }
        boolean b;
        if (this.t < n2) {
            if (this.t + this.h > n2) {
                this.t = n2 - this.h;
            }
            if (this.l + this.w > n) {
                this.l = n - this.w;
            }
            b = true;
        }
        else {
            if (this.t + this.h > Webfurn.apHeight) {
                this.t = Math.max(Webfurn.apHeight - this.h, Webfurn.apStTop);
            }
            if (this.l + this.w > Webfurn.apWidth) {
                this.l = Webfurn.apWidth - this.w;
            }
            b = true;
        }
        return b;
    }
    
    public Font fontIfVert(final Graphics graphics) {
        Font font = new Font("TimesRoman", 0, 12);
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n = 0;
        for (int i = 0; i < this.n.length(); ++i) {
            n = Math.max(fontMetrics.charWidth(this.n.charAt(i)), n);
        }
        final int n2 = (fontMetrics.getHeight() / 2 + fontMetrics.getDescent()) * this.n.length();
        int n5;
        for (int n3 = n + 2, n4 = n2 + 2; !this.boxFits(n3, n4) && font.getSize() > 1; n3 = n + 2, n4 = n5 + 2) {
            font = new Font(font.getName(), font.getStyle(), font.getSize() - 1);
            graphics.setFont(font);
            final FontMetrics fontMetrics2 = graphics.getFontMetrics();
            for (int j = 0; j < this.n.length(); ++j) {
                n = Math.max(fontMetrics2.charWidth(this.n.charAt(j)), n);
            }
            n5 = (fontMetrics2.getHeight() / 2 + fontMetrics2.getDescent()) * this.n.length();
        }
        return font;
    }
    
    public Font fontIfHoriz(final Graphics graphics) {
        Font font = new Font("TimesRoman", 0, 12);
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth(this.n);
        final int n = fontMetrics.getHeight() / 2 + fontMetrics.getDescent();
        int stringWidth2;
        int n4;
        for (int n2 = stringWidth + 2, n3 = n + 2; !this.boxFits(n2, n3) && font.getSize() > 1; n2 = stringWidth2 + 2, n3 = n4 + 2) {
            font = new Font(font.getName(), font.getStyle(), font.getSize() - 1);
            graphics.setFont(font);
            final FontMetrics fontMetrics2 = graphics.getFontMetrics();
            stringWidth2 = fontMetrics2.stringWidth(this.n);
            n4 = fontMetrics2.getHeight() / 2 + fontMetrics2.getDescent();
        }
        return font;
    }
    
    public void drawNameHoriz(final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth(this.n);
        final int n = fontMetrics.getHeight() / 2 + fontMetrics.getDescent();
        final int n2 = stringWidth + 2;
        final int n3 = n + 2;
        final int n4 = fontMetrics.getHeight() / 2;
        if (graphics.getFont().getSize() > 1) {
            if (this.grabbed) {
                graphics.setColor(Color.black);
            }
            else {
                graphics.setColor(Color.lightGray);
            }
            graphics.fillRect(this.l + this.w / 2 - stringWidth / 2 - 1, this.t + this.h / 2 - n / 2 - 1, n2, n3);
            if (this.grabbed) {
                graphics.setColor(Color.lightGray);
            }
            else {
                graphics.setColor(Color.black);
            }
            graphics.drawString(this.n, this.l + this.w / 2 - stringWidth / 2, this.t + this.h / 2 + n4 / 2 + 1);
        }
    }
    
    public void drawNameVert(final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int max = 0;
        for (int i = 0; i < this.n.length(); ++i) {
            max = Math.max(fontMetrics.charWidth(this.n.charAt(i)), max);
        }
        final int n = (fontMetrics.getHeight() / 2 + fontMetrics.getDescent()) * this.n.length();
        final int n2 = max + 2;
        final int n3 = n + 2;
        final int n4 = fontMetrics.getHeight() / 2 + fontMetrics.getDescent() / 2;
        if (graphics.getFont().getSize() > 1) {
            if (this.grabbed) {
                graphics.setColor(Color.black);
            }
            else {
                graphics.setColor(Color.lightGray);
            }
            graphics.fillRect(this.l + this.w / 2 - max / 2 - 1, this.t + this.h / 2 - n / 2 - 1, n2, n3);
            if (this.grabbed) {
                graphics.setColor(Color.lightGray);
            }
            else {
                graphics.setColor(Color.black);
            }
            final int n5 = this.t + this.h / 2 - n / 2 + n4;
            int n6 = 0;
            for (int j = 0; j < this.n.length(); ++j) {
                graphics.drawString(this.n.substring(j, j + 1), this.l + this.w / 2 - fontMetrics.charWidth(this.n.charAt(j)) / 2, n5 + n6);
                n6 += fontMetrics.getHeight() / 2 + fontMetrics.getDescent();
            }
        }
    }
    
    String fromFeet(String s, final char c) {
        if (c == 'i') {
            final double n = Math.ceil(Double.valueOf(s) * 12.0 * 100) / 100;
            if ((int)n == n) {
                s = Integer.toString((int)n);
            }
            else {
                s = Double.toString(n);
            }
        }
        if (c == 'm') {
            final double n2 = Math.ceil(Double.valueOf(s) / 39.37 * 12.0 * 100) / 100;
            if ((int)n2 == n2) {
                s = Integer.toString((int)n2);
            }
            else {
                s = Double.toString(n2);
            }
        }
        return s;
    }
    
    public void recalcMidActual() {
        this.midX = this.l + this.w / 2;
        this.midY = this.t + this.h / 2;
        this.actualL = this.midX - this.actualW / 2;
        this.actualT = this.midY - this.actualH / 2;
    }
    
    public void figurePolyEnclosing() {
        this.figureWandH();
        this.figureLandT();
    }
    
    public void figureWandH() {
        this.w = (int)(this.actualH * Math.sin(this.a) + this.actualW * Math.cos(this.a));
        this.h = (int)(this.actualH * Math.cos(this.a) + this.actualW * Math.sin(this.a));
    }
    
    public void figureLandT() {
        this.l = this.midX - this.w / 2;
        this.t = this.midY - this.h / 2;
    }
    
    public void figurePolyPoints() {
        this.xpoints[0] = this.l;
        this.xpoints[1] = (int)(this.l + this.actualH * Math.sin(this.a));
        this.xpoints[2] = this.l + this.w;
        this.xpoints[3] = (int)(this.l + this.actualW * Math.cos(this.a));
        this.xpoints[4] = this.l;
        this.ypoints[0] = (int)(this.t + this.actualH * Math.cos(this.a));
        this.ypoints[1] = this.t;
        this.ypoints[2] = (int)(this.t + this.actualW * Math.sin(this.a));
        this.ypoints[3] = this.t + this.h;
        this.ypoints[4] = (int)(this.t + this.actualH * Math.cos(this.a));
        this.actualFurn = new Polygon(this.xpoints, this.ypoints, 5);
    }
}
