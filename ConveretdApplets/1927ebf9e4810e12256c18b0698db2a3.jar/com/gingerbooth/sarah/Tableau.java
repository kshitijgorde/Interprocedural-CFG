// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

import java.util.Date;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.StreamTokenizer;
import java.io.InputStream;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Rectangle;
import courseware.util.Palettable;
import java.util.Vector;
import courseware.jfcdep.BufferCanvas;

public class Tableau extends BufferCanvas
{
    Vector shapes;
    static double edgeLength;
    static int vizPixelTolerance;
    static int pickPixelTolerance;
    static int mouseSensitivity;
    private double scale;
    private double minx;
    private double miny;
    private double maxx;
    private double maxy;
    private boolean needRepaint;
    public static Palettable palette;
    public static Palettable backPalette;
    Rectangle area;
    public boolean useOutline;
    public int outlineColor;
    public int backgroundColorID;
    public Color backgroundColor;
    public static Color borderColor;
    private double newminx;
    private double newminy;
    private double newmaxx;
    private double newmaxy;
    private int lineno;
    
    static {
        Tableau.edgeLength = 30.0;
        Tableau.vizPixelTolerance = 4;
        Tableau.pickPixelTolerance = 10;
        Tableau.mouseSensitivity = 4;
        Tableau.borderColor = Color.black;
    }
    
    public Tableau() {
        this.shapes = new Vector(0, 1);
        this.scale = 1.0;
        this.needRepaint = true;
        this.area = new Rectangle();
        this.useOutline = false;
        this.outlineColor = 215;
        this.backgroundColorID = 209;
        this.minx = 0.0;
        this.miny = 0.0;
    }
    
    public void add(final Shape shape) {
        this.shapes.addElement(shape);
        this.drawOne(super.offg = this.getOffscreen(), shape);
        this.repaint();
    }
    
    public void center() {
        final int size = this.shapes.size();
        if (size == 0) {
            return;
        }
        final double[][] pts = this.shapes.elementAt(0).getPts();
        final double n = pts[0][0];
        this.newmaxx = n;
        this.newminx = n;
        final double n2 = pts[1][0];
        this.newmaxy = n2;
        this.newminy = n2;
        this.growNewMinMax(pts);
        for (int i = 1; i < size; ++i) {
            this.growNewMinMax(((Shape)this.shapes.elementAt(i)).getPts());
        }
        final double n3 = (this.newmaxx - this.newminx) / 2.0 + this.newminx;
        final double n4 = (this.newmaxy - this.newminy) / 2.0 + this.newminy;
        final double n5 = (this.maxx - this.minx) / 2.0;
        final double n6 = (this.maxy - this.miny) / 2.0;
        this.minx = n3 - n5;
        this.maxx = n3 + n5;
        this.miny = n4 - n6;
        this.maxy = n4 + n6;
        this.needRepaint = true;
    }
    
    private void drawBackground(final Color backgroundColor) {
        final Dimension size = this.getSize();
        final Graphics offscreen = this.getOffscreen();
        offscreen.setColor(this.backgroundColor = backgroundColor);
        offscreen.fillRect(0, 0, size.width, size.height);
        offscreen.setColor(Tableau.borderColor);
        offscreen.drawRect(0, 0, size.width, size.height - 1);
    }
    
    public void drawOne(final Graphics graphics, final Shape shape) {
        final int[][] pts = this.getPts(shape.getPts());
        graphics.setColor(shape.getColor());
        graphics.fillPolygon(pts[0], pts[1], pts[0].length);
        if (this.useOutline) {
            graphics.setColor(Tableau.palette.getColor(this.outlineColor));
            graphics.drawPolygon(pts[0], pts[1], pts[0].length);
        }
    }
    
    public void drawShapes(final Graphics graphics) {
        for (int size = this.shapes.size(), i = 0; i < size; ++i) {
            this.drawOne(graphics, (Shape)this.shapes.elementAt(i));
        }
    }
    
    public void erase(final Color color) {
        this.shapes = new Vector(0, 1);
        this.drawBackground(color);
        this.needRepaint = false;
        this.repaint();
    }
    
    public void fixDimension(final boolean b, final double n) {
        final Dimension preferredSize = super.getPreferredSize();
        if (b) {
            preferredSize.width = this.x_pixel(n * Tableau.edgeLength);
        }
        else {
            preferredSize.height = this.y_pixel(n * Tableau.edgeLength);
        }
        final Rectangle bounds = super.getBounds();
        bounds.width = preferredSize.width;
        bounds.height = preferredSize.height;
        super.setBounds(bounds);
    }
    
    public double getMaxX() {
        return this.maxx;
    }
    
    public double getMaxY() {
        return this.maxy;
    }
    
    public static double getMinResolution() {
        return Tableau.vizPixelTolerance;
    }
    
    public double getMinX() {
        return this.minx;
    }
    
    public double getMinY() {
        return this.miny;
    }
    
    public double getPickResolution() {
        return Tableau.pickPixelTolerance * this.scale;
    }
    
    private int[][] getPts(final double[][] array) {
        final int length = array[0].length;
        final int[][] array2 = new int[2][length];
        for (int i = 0; i < length; ++i) {
            array2[0][i] = this.x_pixel(array[0][i]);
            array2[1][i] = this.y_pixel(array[1][i]);
        }
        return array2;
    }
    
    public double getScale() {
        return this.scale;
    }
    
    private void growNewMinMax(final double[][] array) {
        for (int i = 0; i < array[0].length; ++i) {
            if (array[0][i] < this.newminx) {
                this.newminx = array[0][i];
            }
            if (array[0][i] > this.newmaxx) {
                this.newmaxx = array[0][i];
            }
            if (array[1][i] < this.newminy) {
                this.newminy = array[1][i];
            }
            if (array[1][i] > this.newmaxy) {
                this.newmaxy = array[1][i];
            }
        }
    }
    
    public void load(final InputStream inputStream, final ShapeGame shapeGame) {
        this.lineno = 0;
        this.erase(this.backgroundColor);
        final StreamTokenizer streamTokenizer = new StreamTokenizer(inputStream);
        streamTokenizer.eolIsSignificant(true);
        streamTokenizer.commentChar(59);
        try {
            int nextToken;
            while ((nextToken = this.nextToken(streamTokenizer)) != -1) {
                if (nextToken == 10) {
                    ++this.lineno;
                }
                else {
                    if (nextToken != -3) {
                        continue;
                    }
                    final String s = new String(streamTokenizer.sval);
                    if (s.equals("bg")) {
                        this.nextToken(streamTokenizer);
                        shapeGame.setBackgroundColor(Math.round(Math.round(streamTokenizer.nval)));
                    }
                    else if (s.equals("ol")) {
                        this.nextToken(streamTokenizer);
                        if (Math.round(Math.round(streamTokenizer.nval)) == 1) {
                            this.useOutline = true;
                        }
                        else {
                            this.useOutline = false;
                        }
                        this.nextToken(streamTokenizer);
                        this.outlineColor = Math.round(Math.round(streamTokenizer.nval));
                    }
                    else if (s.equals("el")) {
                        this.nextToken(streamTokenizer);
                        Tableau.edgeLength = streamTokenizer.nval;
                    }
                    else if (s.equals("sc")) {
                        this.nextToken(streamTokenizer);
                        this.scale = streamTokenizer.nval;
                    }
                    else if (s.equals("lm")) {
                        this.nextToken(streamTokenizer);
                        this.minx = streamTokenizer.nval;
                        this.nextToken(streamTokenizer);
                        this.miny = streamTokenizer.nval;
                        this.nextToken(streamTokenizer);
                        this.maxx = streamTokenizer.nval;
                        this.nextToken(streamTokenizer);
                        this.maxy = streamTokenizer.nval;
                    }
                    else if (s.equals("s")) {
                        this.readShape(streamTokenizer);
                    }
                    else {
                        System.err.println("skipping stoken " + s);
                    }
                }
            }
        }
        catch (Exception ex) {
            System.err.println("Error reading input line " + this.lineno + ", error =" + ex);
        }
        this.needRepaint = true;
        this.repaint();
    }
    
    private Shape makeShape(final String s) {
        if (s.equals("d")) {
            return new Diamond();
        }
        if (s.equals("hh")) {
            return new HalfHexagon();
        }
        if (s.equals("h")) {
            return new Hexagon();
        }
        if (s.equals("sq")) {
            return new Square();
        }
        if (s.equals("sl")) {
            return new Sliver();
        }
        if (s.equals("t")) {
            return new Triangle();
        }
        return new Shape();
    }
    
    public void newBackground(final int backgroundColorID) {
        this.backgroundColorID = backgroundColorID;
        this.backgroundColor = Tableau.backPalette.getColor(backgroundColorID);
        this.needRepaint = true;
        if (super.offg != null) {
            this.repaint();
        }
    }
    
    private int nextToken(final StreamTokenizer streamTokenizer) {
        int n;
        try {
            n = streamTokenizer.nextToken();
            while (n == 10 || n == -1) {
                if (n == -1) {
                    return n;
                }
                if (n != 10) {
                    continue;
                }
                ++this.lineno;
                n = streamTokenizer.nextToken();
            }
        }
        catch (Exception ex) {
            System.err.println("unexpected error in nextToken = " + ex);
            return 10;
        }
        return n;
    }
    
    public synchronized void paint(final Graphics graphics) {
        synchronized (graphics) {
            super.offg = this.getOffscreen();
            final Graphics offg = super.offg;
            // monitorenter(offg)
            try {
                final Image offscreen = super.offscreen;
                // monitorenter(offscreen)
                try {
                    if (super.offg == null || super.offscreen == null) {
                        // monitorexit(offscreen)
                        // monitorexit(offg)
                        // monitorexit(graphics)
                        return;
                    }
                    if (this.needRepaint) {
                        this.drawBackground(this.backgroundColor);
                        this.drawShapes(super.offg);
                    }
                    graphics.drawImage(super.offscreen, 0, 0, this);
                }
                // monitorexit(offscreen)
                finally {}
            }
            // monitorexit(offg)
            finally {}
        }
        this.needRepaint = false;
    }
    
    public void pan(final double n, final double n2) {
        final double n3 = (this.maxx - this.minx) * n;
        this.minx += n3;
        this.maxx += n3;
        final double n4 = (this.maxy - this.miny) * n2;
        this.miny += n4;
        this.maxy += n4;
        this.needRepaint = true;
        this.repaint();
    }
    
    private Vertex parseVertex(final Vertex vertex, final StreamTokenizer streamTokenizer) {
        try {
            if (this.nextToken(streamTokenizer) == -1) {
                return null;
            }
            if (!streamTokenizer.sval.equals("v")) {
                streamTokenizer.pushBack();
                return null;
            }
            this.nextToken(streamTokenizer);
            final int round = Math.round(Math.round(streamTokenizer.nval));
            if (vertex != null) {
                return new Vertex(vertex, round);
            }
            this.nextToken(streamTokenizer);
            final int round2 = Math.round(Math.round(streamTokenizer.nval));
            this.nextToken(streamTokenizer);
            final double nval = streamTokenizer.nval;
            this.nextToken(streamTokenizer);
            return new Vertex(round, round2, nval, streamTokenizer.nval);
        }
        catch (Exception ex) {
            System.err.println("Error reading input line " + this.lineno + ", e=" + ex.getMessage());
            return null;
        }
    }
    
    public Pick pick(final double n, final double n2, final boolean b, final Tableau tableau) {
        Shape shape = null;
        int pickVertex = -1;
        for (int i = this.shapes.size() - 1; i > -1; --i) {
            shape = (Shape)this.shapes.elementAt(i);
            pickVertex = shape.pickVertex(n, n2, b, this);
            if (pickVertex >= 0) {
                break;
            }
        }
        if (pickVertex < 0) {
            return null;
        }
        final Pick pick = new Pick(shape, pickVertex, this.x_world(this.x_pixel(n)), this.y_world(this.y_pixel(n2)), tableau.getPts(shape.getPts()));
        pick.moveBy(this.x_pixel(n) - tableau.x_pixel(n), this.y_pixel(n2) - tableau.y_pixel(n2));
        if (shape instanceof Sliver) {
            ((Sliver)shape).xorablePts(pick);
        }
        return pick;
    }
    
    public Pick pick(final int n, final int n2, final boolean b) {
        return this.pick(this.x_db(n), this.y_db(n2), b, this);
    }
    
    public Pick pickCross(final int n, final int n2, final boolean b, final Tableau tableau) {
        return this.pick(this.x_db(n), this.y_db(n2), b, tableau);
    }
    
    public String print() {
        String s = String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf("; ShapeGame param file, version 1.0\n")).append("; ").append(new Date()).append("\n").toString())).append("bg ").append(this.backgroundColorID).append("\n").toString())).append("ol ").append(this.useOutline ? 1 : 0).append(" ").append(this.outlineColor).append("\n").toString())).append("el ").append(Tableau.edgeLength).append("\n").toString())).append("sc ").append(this.scale).append("\n").toString()) + "lm " + this.minx + " " + this.miny + " " + this.maxx + " " + this.maxy + "\n";
        for (int i = 0; i < this.shapes.size(); ++i) {
            final Shape shape = this.shapes.elementAt(i);
            s = String.valueOf(s) + "s " + this.shortNameLookup(shape) + " " + shape.color + " " + shape.color2 + "\n";
            final Vector vertices = shape.getVertices();
            if (vertices.size() < 1) {
                break;
            }
            final Vertex vertex = vertices.elementAt(0);
            s = String.valueOf(s) + "v  " + vertex.getAngleIn() + " " + vertex.getAngleOut() + " " + vertex.getX() + " " + vertex.getY() + "\n";
            for (int j = 1; j < vertices.size(); ++j) {
                s = String.valueOf(s) + "v  " + vertices.elementAt(j).getAngleOut() + "\n";
            }
        }
        return s;
    }
    
    private void readShape(final StreamTokenizer streamTokenizer) {
        try {
            this.nextToken(streamTokenizer);
            final Shape shape = this.makeShape(streamTokenizer.sval);
            this.shapes.addElement(shape);
            this.nextToken(streamTokenizer);
            shape.setColor(Math.round(Math.round(streamTokenizer.nval)));
            this.nextToken(streamTokenizer);
            shape.setColor2(Math.round(Math.round(streamTokenizer.nval)));
            final Vector vertices = shape.getVertices();
            Vertex vertex = this.parseVertex(null, streamTokenizer);
            vertices.setElementAt(vertex, 0);
            int n = 1;
            while ((vertex = this.parseVertex(vertex, streamTokenizer)) != null) {
                vertices.setElementAt(vertex, n);
                ++n;
            }
        }
        catch (Exception ex) {
            System.err.println("Error reading input line " + this.lineno + ", e=" + ex.getMessage());
        }
    }
    
    public void removeOne(final Shape shape) {
        final Graphics graphics = this.getGraphics();
        (super.offg = this.getOffscreen()).setColor(this.backgroundColor);
        this.getPts(shape.getPts());
        this.shapes.removeElement(shape);
        this.shapes.trimToSize();
        this.needRepaint = true;
        this.paint(graphics);
    }
    
    public void replaceColor(final int n, final int color) {
        for (int i = 0; i < this.shapes.size(); ++i) {
            final Shape shape = this.shapes.elementAt(i);
            if (shape.color == n) {
                shape.color = color;
            }
        }
    }
    
    public void replaceShapeColor(final Class clazz, final int color, final UndoableColorAll undoableColorAll) {
        for (int i = 0; i < this.shapes.size(); ++i) {
            final Shape shape = this.shapes.elementAt(i);
            if (clazz.equals(shape.getClass())) {
                undoableColorAll.add(shape);
                shape.color = color;
            }
        }
    }
    
    public static void setBackPalette(final Palettable backPalette) {
        Tableau.backPalette = backPalette;
    }
    
    public synchronized void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        this.area = new Rectangle(n, n2, n3, n4);
        this.maxx = this.minx + n3 / this.scale;
        this.maxy = this.miny + n4 / this.scale;
    }
    
    public static void setEdgeLength(final double edgeLength) {
        Tableau.edgeLength = edgeLength;
    }
    
    public void setMaxX(final double maxx) {
        this.maxx = maxx;
    }
    
    public void setMaxY(final double maxy) {
        this.maxy = maxy;
    }
    
    public void setMinX(final double minx) {
        this.minx = minx;
    }
    
    public void setMinY(final double miny) {
        this.miny = miny;
    }
    
    public void setNeedRepaint(final boolean needRepaint) {
        this.needRepaint = needRepaint;
    }
    
    public void setOutline(final boolean useOutline) {
        this.useOutline = useOutline;
    }
    
    public static void setPalette(final Palettable palette) {
        Tableau.palette = palette;
    }
    
    public static void setPickResolution(final int pickPixelTolerance) {
        Tableau.pickPixelTolerance = pickPixelTolerance;
    }
    
    public void setScale(final double n) {
        this.scale *= n;
        this.needRepaint = true;
        final double n2 = (this.maxx - this.minx) / 2.0;
        final double n3 = this.minx + n2;
        final double n4 = n2 / n;
        this.minx = n3 - n4;
        this.maxx = n3 + n4;
        final double n5 = (this.maxy - this.miny) / 2.0;
        final double n6 = this.miny + n5;
        final double n7 = n5 / n;
        this.miny = n6 - n7;
        this.maxy = n6 + n7;
        this.repaint();
    }
    
    private String shortNameLookup(final Shape shape) {
        if (shape instanceof Diamond) {
            return "d";
        }
        if (shape instanceof HalfHexagon) {
            return "hh";
        }
        if (shape instanceof Hexagon) {
            return "h";
        }
        if (shape instanceof Square) {
            return "sq";
        }
        if (shape instanceof Sliver) {
            return "sl";
        }
        if (shape instanceof Triangle) {
            return "t";
        }
        return "uk";
    }
    
    public String toString() {
        return "Tableau[" + this.area + ",min=" + this.minx + "," + this.miny + ",max=" + this.maxx + "," + this.maxy + ",scale=" + this.scale;
    }
    
    public double x_db(final int n) {
        return this.minx + (this.maxx - this.minx) * n / this.area.width;
    }
    
    public int x_pixel(final double n) {
        return Math.round(Math.round((n - this.minx) / (this.maxx - this.minx) * this.area.width));
    }
    
    public double x_world(final int n) {
        return this.minx + (this.maxx - this.minx) * (n - this.area.x) / this.area.width;
    }
    
    public void xorOne(final Graphics graphics, final Pick pick) {
        if (this.useOutline) {
            graphics.setColor(Tableau.palette.getColor(this.outlineColor));
        }
        else {
            graphics.setColor(pick.shape.getColor());
        }
        graphics.setXORMode(this.backgroundColor);
        final int[][] pts = pick.getPts();
        graphics.drawPolygon(pts[0], pts[1], pts[0].length);
    }
    
    public double y_db(final int n) {
        return this.miny - (this.maxy - this.miny) * (n - this.area.height) / this.area.height;
    }
    
    public int y_pixel(final double n) {
        return Math.round(Math.round(this.area.height - (n - this.miny) / (this.maxy - this.miny) * this.area.height));
    }
    
    public double y_world(final int n) {
        return this.miny - (this.maxy - this.miny) * (n - this.area.y - this.area.height) / this.area.height;
    }
}
