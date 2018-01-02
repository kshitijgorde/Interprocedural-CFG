// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.util.Vector;
import netcharts.util.NFDebug;
import netcharts.util.NFParam;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Dimension;

public class NFNote extends NFLabel
{
    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int TOP = 2;
    public static final int BOTTOM = 3;
    public static final int CENTER = 4;
    public static final int TOPRIGHT = 5;
    public static final int TOPLEFT = 6;
    public static final int BOTTOMRIGHT = 7;
    public static final int BOTTOMLEFT = 8;
    public static final int NONE = 0;
    public static final int FROMTO = 1;
    public static final int TOFROM = 2;
    public static final int BOTH = 3;
    int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private NFLine h;
    private NFArrow i;
    private NFArrow j;
    private int k;
    private int l;
    private Dimension m;
    private NFRegionBorder n;
    private Rectangle o;
    private String p;
    
    public NFNote() {
        this.a = 4;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = 0;
        this.l = 10;
        this.m = new Dimension();
        this.n = new NFRegionBorder();
        this.o = new Rectangle();
        this.p = "";
    }
    
    public void setScale(final double n) {
        super.setScale(n);
        if (this.h != null) {
            this.h.setScale(n);
        }
        if (this.i != null) {
            this.i.setScale(n);
        }
        if (this.j != null) {
            this.j.setScale(n);
        }
    }
    
    public void setCoordinates(final int x, final int y, final int b, final int c, final int d, final int e, final int f, final int g) {
        super.x = x;
        super.y = y;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }
    
    private void a(final Graphics graphics) {
        if (this.h == null || this.b == -1) {
            return;
        }
        if (this.d == -1) {
            switch (this.k) {
                case 0: {
                    this.h.setArrows(this.j, this.i);
                    this.h.draw(graphics, super.x, super.y, this.b, this.c);
                    break;
                }
                case 4: {
                    final Polygon bounds = new Polygon();
                    bounds.addPoint(super.x, super.y);
                    bounds.addPoint(this.b, super.y);
                    bounds.addPoint(this.b, this.c);
                    bounds.addPoint(super.x, this.c);
                    bounds.addPoint(super.x, super.y);
                    if (super.region != null && super.region.getColor() != null) {
                        final Color color = graphics.getColor();
                        graphics.setColor(super.region.getColor());
                        graphics.fillPolygon(bounds);
                        graphics.setColor(color);
                    }
                    this.h.drawPoly(graphics, bounds);
                    if (this.getActiveLabel() != null) {
                        this.getActiveLabel().setBounds(bounds);
                        break;
                    }
                    break;
                }
                case 5: {
                    final Polygon bounds2 = new Polygon();
                    NFGraphSymbol.generateArc(bounds2, new Rectangle(super.x, super.y, this.b - super.x, this.c - super.y), 0.0, 360.0, 0);
                    if (super.region != null && super.region.getColor() != null) {
                        final Color color2 = graphics.getColor();
                        graphics.setColor(super.region.getColor());
                        graphics.fillPolygon(bounds2);
                        graphics.setColor(color2);
                    }
                    this.h.drawPoly(graphics, bounds2);
                    if (this.getActiveLabel() != null) {
                        this.getActiveLabel().setBounds(bounds2);
                        break;
                    }
                    break;
                }
            }
            return;
        }
        final int scaleThickness = this.h.getScaleThickness();
        Color color3 = null;
        if (scaleThickness > 1) {
            color3 = graphics.getColor();
        }
        this.h.setArrows(this.j, null);
        this.h.draw(graphics, super.x, super.y, this.b, this.c);
        if (scaleThickness > 1) {
            graphics.setColor(this.h.getColor());
            graphics.fillOval(this.b - scaleThickness, this.c - scaleThickness, scaleThickness + scaleThickness, scaleThickness + scaleThickness);
            graphics.setColor(color3);
        }
        if (this.f == -1) {
            this.h.setArrows(null, this.i);
            this.h.draw(graphics, this.b, this.c, this.d, this.e);
            return;
        }
        this.h.setArrows(null, null);
        this.h.draw(graphics, this.b, this.c, this.d, this.e);
        if (scaleThickness > 1) {
            graphics.setColor(this.h.getColor());
            graphics.fillOval(this.d - scaleThickness, this.e - scaleThickness, scaleThickness + scaleThickness, scaleThickness + scaleThickness);
            graphics.setColor(color3);
        }
        this.h.setArrows(null, this.i);
        this.h.draw(graphics, this.d, this.e, this.f, this.g);
    }
    
    public void draw(final Graphics graphics, final Rectangle rectangle) {
        this.a(graphics);
        this.m = this.getBounds(graphics, this.m, this.n);
        final int n = this.m.width / 2;
        final int n2 = this.m.height / 2;
        switch (this.a) {
            case 0: {
                super.x -= n;
                break;
            }
            case 1: {
                super.x += n;
                break;
            }
            case 2: {
                super.y += n2;
                break;
            }
            case 3: {
                super.y -= n2;
                break;
            }
            case 6: {
                super.x += n;
                super.y += n2;
                break;
            }
            case 5: {
                super.x -= n;
                super.y += n2;
                break;
            }
            case 8: {
                super.x += n;
                super.y -= n2;
                break;
            }
            case 7: {
                super.x -= n;
                super.y -= n2;
                break;
            }
        }
        rectangle.x = super.x - n;
        rectangle.y = super.y - n2;
        rectangle.width = this.m.width;
        rectangle.height = this.m.height;
        if (super.lbl != null && super.lbl.length() > 0 && !this.p.equalsIgnoreCase("OFF")) {
            super.draw(graphics, super.x, super.y);
        }
    }
    
    public int hitPoint(final int n, final int n2) {
        final int n3 = n - super.x;
        final int n4 = n2 - super.y;
        if (n3 <= this.l && n3 >= -this.l && n4 <= this.l && n4 >= -this.l) {
            return 0;
        }
        if (this.b == -1) {
            return -1;
        }
        final int n5 = n - this.b;
        final int n6 = n2 - this.c;
        if (n5 <= this.l && n5 >= -this.l && n6 <= this.l && n6 >= -this.l) {
            return 1;
        }
        if (this.d == -1) {
            return -1;
        }
        final int n7 = n - this.d;
        final int n8 = n2 - this.e;
        if (n7 <= this.l && n7 >= -this.l && n8 <= this.l && n8 >= -this.l) {
            return 2;
        }
        if (this.f == -1) {
            return -1;
        }
        final int n9 = n - this.f;
        final int n10 = n2 - this.g;
        if (n9 <= this.l && n9 >= -this.l && n10 <= this.l && n10 >= -this.l) {
            return 3;
        }
        return -1;
    }
    
    public void loadParams(final NFParam nfParam, final String s) {
        try {
            this.loadLabel(nfParam, s);
            this.loadBox(nfParam, s);
            this.loadFormat(nfParam, s);
            this.loadArrow(nfParam, s);
            this.loadText(nfParam, s);
        }
        catch (Exception ex) {
            NFDebug.print("NFNote: loadParams():" + ex);
        }
    }
    
    public void loadSharedNote(final NFParam nfParam, final String s) {
        try {
            this.loadLabel(nfParam, s);
            this.loadBox(nfParam, s);
            this.loadFormat(nfParam, s);
            this.loadArrow(nfParam, s);
        }
        catch (Exception ex) {
            NFDebug.print("NFNote: loadParams():" + ex);
        }
    }
    
    public void loadLabel(final NFParam nfParam, final String s) throws Exception {
        super.loadParams(nfParam, s + "NoteLabel");
    }
    
    public void loadParams(final Vector vector) {
        super.loadParams(vector);
        this.p = super.lbl;
    }
    
    public void loadBox(final NFParam nfParam, final String s) throws Exception {
        if (nfParam.changed(s + "NoteBox")) {
            this.setRegion(NFRegion.loadParams(nfParam, nfParam.get(s + "NoteBox")));
        }
    }
    
    public void loadFormat(final NFParam nfParam, final String s) throws Exception {
        if (nfParam.changed(s + "NoteFormat")) {
            this.a = ((Number)nfParam.get(s + "NoteFormat")).intValue();
        }
    }
    
    public void loadArrow(final NFParam nfParam, final String s) throws Exception {
        if (nfParam.changed(s + "NoteArrow")) {
            this.loadArrow(nfParam, s, (Vector)nfParam.get(s + "NoteArrow"));
        }
    }
    
    public void loadArrow(final NFParam nfParam, final String s, final Vector vector) throws Exception {
        this.h = NFLine.loadParams(nfParam, vector, 0);
        final int lineParamCount = NFParam.lineParamCount();
        switch (vector.elementAt(lineParamCount)) {
            case 4: {
                this.k = 4;
                final NFArrow nfArrow = null;
                this.j = nfArrow;
                this.i = nfArrow;
                break;
            }
            case 5: {
                this.k = 5;
                final NFArrow nfArrow2 = null;
                this.j = nfArrow2;
                this.i = nfArrow2;
                break;
            }
            case 0: {
                this.k = 0;
                final NFArrow nfArrow3 = null;
                this.j = nfArrow3;
                this.i = nfArrow3;
                break;
            }
            case 1: {
                this.k = 0;
                this.i = NFArrow.loadParams(nfParam, vector, lineParamCount + 1);
                this.j = null;
                break;
            }
            case 2: {
                this.k = 0;
                this.j = NFArrow.loadParams(nfParam, vector, lineParamCount + 1);
                this.i = null;
                break;
            }
            case 3: {
                this.k = 0;
                this.j = NFArrow.loadParams(nfParam, vector, lineParamCount + 1);
                this.i = this.j;
                break;
            }
        }
    }
    
    public void loadText(final NFParam nfParam, final String s) throws Exception {
        if (nfParam.changed(s + "NoteText")) {
            final Vector vector = (Vector)nfParam.get(s + "NoteText");
            super.lbl = vector.elementAt(0);
            super.x = (int)vector.elementAt(1);
            super.y = (int)vector.elementAt(2);
            this.b = (int)vector.elementAt(3);
            this.c = (int)vector.elementAt(4);
            this.d = (int)vector.elementAt(5);
            this.e = (int)vector.elementAt(6);
            this.f = (int)vector.elementAt(7);
            this.g = (int)vector.elementAt(8);
        }
    }
}
