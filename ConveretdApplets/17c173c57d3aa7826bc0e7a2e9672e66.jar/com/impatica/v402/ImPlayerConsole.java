// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Polygon;
import java.awt.Image;
import java.awt.Color;

public class ImPlayerConsole extends ImPlayer
{
    int black;
    int[] create;
    Color N;
    Color O;
    Color P;
    Color Q;
    boolean R;
    Image darkGray;
    int drawLine;
    int T;
    static final int[] drawPolygon;
    static final int[] drawRect;
    static final Polygon fillRect;
    static final int[] getFontMetrics;
    static final int[] getGraphics;
    
    private Color append(final String s, Color color) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            try {
                color = new Color(Integer.parseInt(parameter, 16));
            }
            catch (NumberFormatException ex) {}
        }
        return color;
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501:
            case 506: {
                if (this.black(event.x, event.y, true)) {
                    return true;
                }
                break;
            }
            case 502: {
                if (this.black(event.x, event.y, false)) {
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public ImPlayerConsole() {
        this.create = new int[] { 12, 40, 68, 96, 124, 152 };
        this.N = Color.black;
        this.O = new Color(76, 76, 76);
        this.P = Color.lightGray;
        this.Q = Color.darkGray;
        super.F = 24;
        this.black = -1;
    }
    
    public final void init() {
        super.init();
        this.N = this.append("COLOR0", this.N);
        this.O = this.append("COLOR0A", this.O);
        this.P = this.append("COLOR1", this.P);
        this.Q = this.append("COLOR1A", this.Q);
    }
    
    private boolean black(final int n, final int n2, final boolean b) {
        if (super.Z == null || super.L == null) {
            return true;
        }
        final int n3 = super.Z.height - 24;
        if (n2 < n3) {
            if (b) {
                this.fillPolygon();
            }
            return false;
        }
        int black = -1;
        final int n4 = n3 + 4;
        if (n2 > n4 && n2 < n4 + 16) {
            int i = 0;
            while (i < 6) {
                final int n5 = this.create[i];
                if (n >= n5 && n < n5 + 16) {
                    if (b) {
                        black = i;
                        break;
                    }
                    switch (i) {
                        case 1: {
                            this.fillPolygon();
                            super.S.l = -1;
                            break;
                        }
                        case 4: {
                            if (super.D != null && super.D.I) {
                                this.I(!super.B);
                                break;
                            }
                            break;
                        }
                        case 3: {
                            this.fillPolygon();
                            super.S.l = 1;
                            break;
                        }
                        case 2: {
                            if (super.S.f == 5) {
                                this.fillPolygon();
                                break;
                            }
                            super.S.I(true);
                            break;
                        }
                        case 0: {
                            this.fillPolygon();
                            super.S.C = 0;
                            break;
                        }
                        case 5: {
                            this.R = !this.R;
                            if (this.R) {
                                super.S.B(0);
                                break;
                            }
                            super.S.B(255);
                            break;
                        }
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        if (black != this.black) {
            if (this.black >= 0) {
                this.drawRect(this.black, 0);
            }
            if (black != -1) {
                this.drawRect(black, 1);
            }
            this.black = black;
        }
        return true;
    }
    
    final boolean I(final ImIstream imIstream) {
        final boolean i = super.I(imIstream);
        if (i) {
            this.I();
            this.T = 0;
            this.drawString();
        }
        else {
            super.H = new int[0];
        }
        return i;
    }
    
    public final synchronized void paint(final Graphics graphics) {
        super.paint(graphics);
        this.create(graphics);
    }
    
    private void create(final Graphics graphics) {
        final int n = super.Z.height - 24;
        if (this.darkGray == null && super.Z != null && super.S.M != null) {
            int width = super.Z.width;
            final int n2 = this.create[4] + 16 + 12 + this.drawLine + 16 + 12;
            if (width < n2) {
                width = n2;
            }
            this.create[5] = width - 16 - 12;
            if (super.G) {
                final int[] create = this.create;
                final int n3 = 5;
                create[n3] -= 12;
            }
            this.darkGray = this.createImage(width, 24);
            final Graphics graphics2 = this.darkGray.getGraphics();
            this.drawLine(graphics2, 0, 0, width, 24);
            int n4 = 12;
            for (int i = 0; i < 6; ++i) {
                this.createImage(graphics2, i, this.create[i], 4);
                n4 += 28;
            }
            this.black = -1;
            this.drawPolygon(graphics2);
            this.repaint(0, n, width, 24);
        }
        if (this.darkGray != null) {
            graphics.drawImage(this.darkGray, 0, n, null);
        }
    }
    
    private void createImage(final Graphics graphics, final int n, final int n2, final int n3) {
        if (n == 4 && (super.D == null || !super.D.I)) {
            return;
        }
        final Graphics create = graphics.create(n2, n3, 28, 24);
        create.setColor(Color.black);
        create.translate(2, 2);
        create.fillPolygon(ImPlayerConsole.fillRect);
        create.translate(-2, -2);
        create.setColor(this.P);
        create.fillPolygon(ImPlayerConsole.fillRect);
        create.translate(-1, -1);
        create.translate(1, 1);
        create.setColor(this.Q);
        create.drawPolygon(ImPlayerConsole.fillRect);
        switch (n) {
            case 0: {
                this.darkGray(create);
                create.fillRect(3, 3, 3, 10);
                ImPlayerConsole.getFontMetrics[0] = 7;
                ImPlayerConsole.getFontMetrics[1] = 13;
                ImPlayerConsole.getFontMetrics[2] = 13;
                ImPlayerConsole.getGraphics[0] = 8;
                ImPlayerConsole.getGraphics[1] = 3;
                ImPlayerConsole.getGraphics[2] = 13;
                create.fillPolygon(new Polygon(ImPlayerConsole.getFontMetrics, ImPlayerConsole.getGraphics, ImPlayerConsole.getFontMetrics.length));
                this.drawImage(create);
                create.drawPolygon(new Polygon(ImPlayerConsole.getFontMetrics, ImPlayerConsole.getGraphics, ImPlayerConsole.getFontMetrics.length));
                create.drawRect(3, 3, 3, 10);
                break;
            }
            case 1: {
                this.darkGray(create);
                create.fillRect(10, 3, 3, 10);
                ImPlayerConsole.getFontMetrics[0] = 3;
                ImPlayerConsole.getFontMetrics[1] = 8;
                ImPlayerConsole.getFontMetrics[2] = 8;
                ImPlayerConsole.getGraphics[0] = 8;
                ImPlayerConsole.getGraphics[1] = 3;
                ImPlayerConsole.getGraphics[2] = 13;
                create.fillPolygon(new Polygon(ImPlayerConsole.getFontMetrics, ImPlayerConsole.getGraphics, ImPlayerConsole.getFontMetrics.length));
                this.drawImage(create);
                create.drawPolygon(new Polygon(ImPlayerConsole.getFontMetrics, ImPlayerConsole.getGraphics, ImPlayerConsole.getFontMetrics.length));
                create.drawRect(10, 3, 3, 10);
                break;
            }
            case 2: {
                if (super.S.f == 5) {
                    this.darkGray(create);
                    ImPlayerConsole.getFontMetrics[0] = 5;
                    ImPlayerConsole.getFontMetrics[1] = 13;
                    ImPlayerConsole.getFontMetrics[2] = 5;
                    ImPlayerConsole.getGraphics[0] = 3;
                    ImPlayerConsole.getGraphics[1] = 8;
                    ImPlayerConsole.getGraphics[2] = 13;
                    create.fillPolygon(new Polygon(ImPlayerConsole.getFontMetrics, ImPlayerConsole.getGraphics, ImPlayerConsole.getFontMetrics.length));
                    this.drawImage(create);
                    create.drawPolygon(new Polygon(ImPlayerConsole.getFontMetrics, ImPlayerConsole.getGraphics, ImPlayerConsole.getFontMetrics.length));
                    break;
                }
                this.darkGray(create);
                create.fillRect(4, 3, 3, 10);
                create.fillRect(9, 3, 3, 10);
                this.drawImage(create);
                create.drawRect(4, 3, 3, 10);
                create.drawRect(9, 3, 3, 10);
                break;
            }
            case 3: {
                this.darkGray(create);
                create.fillRect(3, 3, 3, 10);
                ImPlayerConsole.getFontMetrics[0] = 8;
                ImPlayerConsole.getFontMetrics[1] = 13;
                ImPlayerConsole.getFontMetrics[2] = 8;
                ImPlayerConsole.getGraphics[0] = 3;
                ImPlayerConsole.getGraphics[1] = 8;
                ImPlayerConsole.getGraphics[2] = 13;
                create.fillPolygon(new Polygon(ImPlayerConsole.getFontMetrics, ImPlayerConsole.getGraphics, ImPlayerConsole.getFontMetrics.length));
                this.drawImage(create);
                create.drawPolygon(new Polygon(ImPlayerConsole.getFontMetrics, ImPlayerConsole.getGraphics, ImPlayerConsole.getFontMetrics.length));
                create.drawRect(3, 3, 3, 10);
                break;
            }
            case 4: {
                this.darkGray(create);
                create.setColor(this.N);
                if (super.B) {
                    create.drawLine(6, 6, 3, 3);
                    create.drawLine(6, 6, 4, 6);
                    create.drawLine(6, 6, 6, 4);
                    create.drawLine(10, 6, 13, 3);
                    create.drawLine(10, 6, 10, 4);
                    create.drawLine(10, 6, 12, 6);
                    create.drawLine(10, 10, 13, 13);
                    create.drawLine(10, 10, 10, 12);
                    create.drawLine(10, 10, 12, 10);
                    create.drawLine(6, 10, 3, 13);
                    create.drawLine(6, 10, 4, 10);
                    create.drawLine(6, 10, 6, 12);
                    break;
                }
                create.drawLine(3, 3, 3, 5);
                create.drawLine(3, 3, 5, 3);
                create.drawLine(3, 3, 6, 6);
                create.drawLine(13, 3, 13, 5);
                create.drawLine(13, 3, 11, 3);
                create.drawLine(13, 3, 10, 6);
                create.drawLine(13, 13, 13, 11);
                create.drawLine(13, 13, 11, 13);
                create.drawLine(13, 13, 10, 10);
                create.drawLine(3, 13, 3, 11);
                create.drawLine(3, 13, 5, 13);
                create.drawLine(3, 13, 6, 10);
                break;
            }
            case 5: {
                this.darkGray(create);
                if (this.R) {
                    final int[] array = { 3, 5, 8, 10, 10, 8, 5, 3 };
                    final int[] array2 = { 6, 6, 3, 3, 13, 13, 10, 10 };
                    create.fillPolygon(new Polygon(array, array2, array.length));
                    this.drawImage(create);
                    create.drawPolygon(new Polygon(array, array2, array.length));
                    break;
                }
                final int[] array3 = { 3, 5, 8, 10, 10, 8, 5, 3 };
                final int[] array4 = { 6, 6, 3, 3, 13, 13, 10, 10 };
                create.fillPolygon(new Polygon(array3, array4, array3.length));
                create.setColor(this.N);
                create.drawLine(12, 8, 14, 8);
                create.drawLine(12, 10, 13, 12);
                create.drawLine(12, 6, 13, 4);
                this.drawImage(create);
                create.drawPolygon(new Polygon(array3, array4, array3.length));
                break;
            }
        }
    }
    
    private void darkGray(final Graphics graphics) {
        if (super.D == null) {
            graphics.setColor(this.N);
        }
        else {
            super.D.I(graphics, 0, 14, this.N, this.O);
        }
    }
    
    private void drawImage(final Graphics graphics) {
        if (super.D == null) {
            graphics.setColor(this.P);
        }
        else {
            super.D.I(graphics, 14, 9, this.N, this.O);
        }
    }
    
    private void drawLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (super.D != null) {
            super.D.I(graphics, 0, 16, this.N, this.O);
        }
        else {
            graphics.setColor(this.N);
        }
        graphics.fillRect(n, n2, n3, n4);
    }
    
    private void drawPolygon(final Graphics graphics) {
        if (super.H == null) {
            return;
        }
        final String string = Integer.toString(this.T) + '/' + Integer.toString(super.H.length);
        final int drawLine = graphics.getFontMetrics().stringWidth(string) + 10;
        if (drawLine > this.drawLine) {
            this.drawLine = drawLine;
        }
        int n = super.Z.width - 28;
        if (super.G) {
            n -= 12;
        }
        final int n2 = this.create[4] + 16 + 12 + this.drawLine;
        if (n < n2) {
            n = n2;
        }
        this.drawLine(graphics, n - this.drawLine, 0, this.drawLine, 24);
        graphics.setColor(this.P);
        graphics.drawString(string, n - drawLine, 17);
    }
    
    private void drawRect(final int n, final int n2) {
        if (this.darkGray != null) {
            final Graphics graphics = this.darkGray.getGraphics();
            final int n3 = this.create[n];
            this.drawLine(graphics, n3, 0, 28, 24);
            this.createImage(this.darkGray.getGraphics(), n, n3 + n2, 4 + n2);
        }
        this.repaint(this.create[n] - 1, super.Z.height - 24 + 4 - 1, 19, 19);
    }
    
    private void drawString() {
        if (this.darkGray != null) {
            this.drawPolygon(this.darkGray.getGraphics());
        }
        int n = super.Z.width - 28;
        if (super.G) {
            n -= 12;
        }
        final int n2 = this.create[4] + 16 + 12 + this.drawLine;
        if (n < n2) {
            n = n2;
        }
        this.repaint(n - this.drawLine, super.Z.height - 24, this.drawLine, 24);
    }
    
    public final void resize(final int n, final int n2) {
        super.resize(n, n2);
        this.darkGray = null;
    }
    
    final void Z() {
        final int z = super.S.Z;
        int i = 0;
        while (i < super.H.length) {
            if (z < super.H[i]) {
                if (this.T == i) {
                    return;
                }
                this.drawRect(2, 0);
                this.T = i;
                this.drawString();
                return;
            }
            else {
                ++i;
            }
        }
        if (this.T != super.H.length) {
            this.T = super.H.length;
            this.drawString();
        }
    }
    
    private void fillPolygon() {
        if (super.S.f == 5) {
            super.S.I(false);
            this.drawRect(2, 0);
        }
    }
    
    static {
        drawPolygon = new int[] { 0, 2, 14, 16, 16, 14, 2, 0 };
        drawRect = new int[] { 2, 0, 0, 2, 14, 16, 16, 14 };
        fillRect = new Polygon(ImPlayerConsole.drawPolygon, ImPlayerConsole.drawRect, ImPlayerConsole.drawPolygon.length);
        getFontMetrics = new int[3];
        getGraphics = new int[3];
    }
}
