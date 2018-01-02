// 
// Decompiled by Procyon v0.5.30
// 

package processing.core;

import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.Graphics2D;

public class PGraphics2 extends PGraphics
{
    public Graphics2D g2;
    GeneralPath gpath;
    int transformCount;
    AffineTransform[] transformStack;
    double[] transform;
    Line2D.Float line;
    Ellipse2D.Float ellipse;
    Rectangle2D.Float rect;
    Arc2D.Float arc;
    protected Color tintColorObject;
    protected Color fillColorObject;
    protected Color strokeColorObject;
    float[] curveX;
    float[] curveY;
    
    public void resize(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.width1 = this.width - 1;
        this.height1 = this.height - 1;
        this.allocate();
        this.background(this.backgroundColor);
    }
    
    protected void allocate() {
        this.image = new BufferedImage(this.width, this.height, 2);
        this.g2 = (Graphics2D)this.image.getGraphics();
    }
    
    public void endFrame() {
    }
    
    public void vertex(final float n, final float n2) {
        this.splineVertexCount = 0;
        if (this.vertexCount == this.vertices.length) {
            final float[][] vertices = new float[this.vertexCount << 1][36];
            System.arraycopy(this.vertices, 0, vertices, 0, this.vertexCount);
            this.vertices = vertices;
        }
        this.vertices[this.vertexCount][9] = n;
        this.vertices[this.vertexCount][10] = n2;
        ++this.vertexCount;
        switch (this.shape) {
            case 16: {
                this.point(n, n2);
                break;
            }
            case 32: {
                if (this.vertexCount % 2 == 0) {
                    this.line(this.vertices[this.vertexCount - 2][9], this.vertices[this.vertexCount - 2][10], n, n2);
                }
                break;
            }
            case 33:
            case 34: {
                if (this.gpath == null) {
                    (this.gpath = new GeneralPath()).moveTo(n, n2);
                }
                else {
                    this.gpath.lineTo(n, n2);
                }
                break;
            }
            case 64: {
                if (this.vertexCount % 3 == 0) {
                    this.triangle(this.vertices[this.vertexCount - 3][9], this.vertices[this.vertexCount - 3][10], this.vertices[this.vertexCount - 2][9], this.vertices[this.vertexCount - 2][10], n, n2);
                }
                break;
            }
            case 65: {
                if (this.vertexCount >= 3) {
                    this.triangle(this.vertices[this.vertexCount - 2][9], this.vertices[this.vertexCount - 2][10], this.vertices[this.vertexCount - 1][9], this.vertices[this.vertexCount - 1][10], this.vertices[this.vertexCount - 3][9], this.vertices[this.vertexCount - 3][10]);
                }
                break;
            }
            case 66: {
                if (this.vertexCount == 3) {
                    this.triangle(this.vertices[0][9], this.vertices[0][10], this.vertices[1][9], this.vertices[1][10], n, n2);
                }
                else if (this.vertexCount > 3) {
                    (this.gpath = new GeneralPath()).moveTo(this.vertices[0][9], this.vertices[0][10]);
                    this.gpath.lineTo(this.vertices[this.vertexCount - 2][9], this.vertices[this.vertexCount - 2][10]);
                    this.gpath.lineTo(n, n2);
                    this.draw_shape(this.gpath);
                }
                break;
            }
            case 128: {
                if (this.vertexCount % 4 == 0) {
                    this.quad(this.vertices[this.vertexCount - 4][9], this.vertices[this.vertexCount - 4][10], this.vertices[this.vertexCount - 3][9], this.vertices[this.vertexCount - 3][10], this.vertices[this.vertexCount - 2][9], this.vertices[this.vertexCount - 2][10], n, n2);
                }
                break;
            }
            case 129: {
                if (this.vertexCount >= 4 && this.vertexCount % 2 == 0) {
                    this.quad(this.vertices[this.vertexCount - 4][9], this.vertices[this.vertexCount - 4][10], this.vertices[this.vertexCount - 2][9], this.vertices[this.vertexCount - 2][10], n, n2, this.vertices[this.vertexCount - 3][9], this.vertices[this.vertexCount - 3][10]);
                }
                break;
            }
            case 256: {
                if (this.gpath == null) {
                    (this.gpath = new GeneralPath()).moveTo(n, n2);
                }
                else {
                    this.gpath.lineTo(n, n2);
                }
                break;
            }
        }
    }
    
    public void bezierVertex(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.gpath == null) {
            throw new RuntimeException("Must call vertex() at least once before using bezierVertex()");
        }
        switch (this.shape) {
            case 33:
            case 34:
            case 256: {
                this.gpath.curveTo(n, n2, n3, n4, n5, n6);
            }
            default: {
                throw new RuntimeException("bezierVertex() can only be used with LINE_STRIP, LINE_LOOP, or POLYGON");
            }
        }
    }
    
    public void curveVertex(final float n, final float n2) {
        if (this.shape != 34 && this.shape != 33 && this.shape != 256) {
            throw new RuntimeException("curveVertex() can only be used with LINE_LOOP, LINE_STRIP, and POLYGON shapes");
        }
        if (!this.curve_inited) {
            this.curve_init();
        }
        this.vertexCount = 0;
        if (this.splineVertices == null) {
            this.splineVertices = new float[128][36];
        }
        if (this.splineVertexCount == 128) {
            System.arraycopy(this.splineVertices[125], 0, this.splineVertices[0], 0, 36);
            System.arraycopy(this.splineVertices[126], 0, this.splineVertices[1], 0, 36);
            this.splineVertexCount = 3;
        }
        if (this.splineVertexCount >= 3) {
            this.curveX[0] = this.splineVertices[this.splineVertexCount - 3][9];
            this.curveY[0] = this.splineVertices[this.splineVertexCount - 3][10];
            this.curveX[1] = this.splineVertices[this.splineVertexCount - 2][9];
            this.curveY[1] = this.splineVertices[this.splineVertexCount - 2][10];
            this.curveX[2] = this.splineVertices[this.splineVertexCount - 1][9];
            this.curveY[2] = this.splineVertices[this.splineVertexCount - 1][10];
            this.curveX[3] = n;
            this.curveY[3] = n2;
            this.curveToBezierMatrix.mult(this.curveX, this.curveX);
            this.curveToBezierMatrix.mult(this.curveY, this.curveY);
            if (this.gpath == null) {
                (this.gpath = new GeneralPath()).moveTo(this.curveX[0], this.curveY[0]);
            }
            this.gpath.curveTo(this.curveX[1], this.curveY[1], this.curveX[2], this.curveY[2], this.curveX[3], this.curveY[3]);
        }
        this.splineVertices[this.splineVertexCount][9] = n;
        this.splineVertices[this.splineVertexCount][10] = n2;
        ++this.splineVertexCount;
    }
    
    public void beginShape(final int n) {
        super.beginShape(n);
        this.gpath = null;
    }
    
    public void endShape() {
        if (this.gpath != null) {
            switch (this.shape) {
                case 33: {
                    this.stroke_shape(this.gpath);
                    break;
                }
                case 34: {
                    this.gpath.closePath();
                    this.stroke_shape(this.gpath);
                    break;
                }
                case 256: {
                    this.gpath.closePath();
                    this.draw_shape(this.gpath);
                    break;
                }
            }
        }
        this.shape = 0;
    }
    
    protected void fill_shape(final Shape shape) {
        if (this.fill) {
            this.g2.setColor(this.fillColorObject);
            this.g2.fill(shape);
        }
    }
    
    protected void stroke_shape(final Shape shape) {
        if (this.stroke) {
            this.g2.setColor(this.strokeColorObject);
            this.g2.draw(shape);
        }
    }
    
    protected void draw_shape(final Shape shape) {
        if (this.fill) {
            this.g2.setColor(this.fillColorObject);
            this.g2.fill(shape);
        }
        if (this.stroke) {
            this.g2.setColor(this.strokeColorObject);
            this.g2.draw(shape);
        }
    }
    
    public void point(final float n, final float n2) {
        this.line(n, n2, n, n2);
    }
    
    public void line(final float n, final float n2, final float n3, final float n4) {
        this.line.setLine(n, n2, n3, n4);
        this.stroke_shape(this.line);
    }
    
    public void triangle(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        (this.gpath = new GeneralPath()).moveTo(n, n2);
        this.gpath.lineTo(n3, n4);
        this.gpath.lineTo(n5, n6);
        this.gpath.closePath();
        this.draw_shape(this.gpath);
    }
    
    public void quad(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        final GeneralPath generalPath = new GeneralPath();
        generalPath.moveTo(n, n2);
        generalPath.lineTo(n3, n4);
        generalPath.lineTo(n5, n6);
        generalPath.lineTo(n7, n8);
        generalPath.closePath();
        this.draw_shape(generalPath);
    }
    
    protected void rectImpl(final float n, final float n2, final float n3, final float n4) {
        this.rect.setFrame(n, n2, n3 - n, n4 - n2);
        this.draw_shape(this.rect);
    }
    
    protected void ellipseImpl(final float n, final float n2, final float n3, final float n4) {
        this.ellipse.setFrame(n, n2, n3, n4);
        this.draw_shape(this.ellipse);
    }
    
    protected void arcImpl(final float n, final float n2, final float n3, final float n4, float n5, float n6) {
        if (n6 - n5 >= 6.2831855f) {
            n5 = 0.0f;
            n6 = 360.0f;
        }
        else {
            for (n5 = -n5 * 57.295776f, n6 = -n6 * 57.295776f; n5 < 0.0f; n5 += 360.0f, n6 += 360.0f) {}
            if (n5 > n6) {
                final float n7 = n5;
                n5 = n6;
                n6 = n7;
            }
        }
        final float n8 = n6 - n5;
        if (this.fill) {
            this.arc.setArc(n, n2, n3, n4, n5, n8, 2);
            this.fill_shape(this.arc);
        }
        if (this.stroke) {
            this.arc.setArc(n, n2, n3, n4, n5, n8, 0);
            this.stroke_shape(this.arc);
        }
    }
    
    public void bezierDetail(final int n) {
    }
    
    public void curveDetail(final int n) {
    }
    
    protected void imageImpl(final PImage pImage, final float n, final float n2, final float n3, final float n4, final int n5, final int n6, final int n7, final int n8) {
        if (pImage.cache == null) {
            pImage.cache = new ImageCache(pImage);
            pImage.updatePixels();
        }
        final ImageCache imageCache = (ImageCache)pImage.cache;
        if ((this.tint && !imageCache.tinted) || (this.tint && imageCache.tintedColor != this.tintColor) || (!this.tint && imageCache.tinted)) {
            pImage.updatePixels();
        }
        if (pImage.modified) {
            imageCache.update();
            pImage.modified = false;
        }
        this.g2.drawImage(((ImageCache)pImage.cache).image, (int)n, (int)n2, (int)n3, (int)n4, n5, n6, n7, n8, null);
    }
    
    public void translate(final float n, final float n2) {
        this.g2.translate(n, n2);
    }
    
    public void rotate(final float n) {
        this.g2.rotate(n);
    }
    
    public void scale(final float n) {
        this.g2.scale(n, n);
    }
    
    public void scale(final float n, final float n2) {
        this.g2.scale(n, n2);
    }
    
    public void pushMatrix() {
        if (this.transformCount == this.transformStack.length) {
            throw new RuntimeException("pushMatrix() cannot use push more than " + this.transformStack.length + " times");
        }
        this.transformStack[this.transformCount] = this.g2.getTransform();
        ++this.transformCount;
    }
    
    public void popMatrix() {
        if (this.transformCount == 0) {
            throw new RuntimeException("missing a popMatrix() to go with that pushMatrix()");
        }
        --this.transformCount;
        this.g2.setTransform(this.transformStack[this.transformCount]);
    }
    
    public void resetMatrix() {
        this.g2.setTransform(new AffineTransform());
    }
    
    public void applyMatrix(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.g2.transform(new AffineTransform(n, n4, n2, n5, n3, n6));
    }
    
    public void printMatrix() {
        this.g2.getTransform().getMatrix(this.transform);
        this.m00 = (float)this.transform[0];
        this.m01 = (float)this.transform[2];
        this.m02 = (float)this.transform[4];
        this.m10 = (float)this.transform[1];
        this.m11 = (float)this.transform[3];
        this.m12 = (float)this.transform[5];
        super.printMatrix();
    }
    
    public float screenX(final float n, final float n2) {
        this.g2.getTransform().getMatrix(this.transform);
        return (float)this.transform[0] * n + (float)this.transform[2] * n2 + (float)this.transform[4];
    }
    
    public float screenY(final float n, final float n2) {
        this.g2.getTransform().getMatrix(this.transform);
        return (float)this.transform[1] * n + (float)this.transform[3] * n2 + (float)this.transform[5];
    }
    
    protected void colorTint() {
        super.colorTint();
        this.tintColorObject = new Color(this.tintColor, true);
    }
    
    protected void colorFill() {
        super.colorFill();
        this.fillColorObject = new Color(this.fillColor, true);
    }
    
    protected void colorStroke() {
        super.colorStroke();
        this.strokeColorObject = new Color(this.strokeColor, true);
    }
    
    public void strokeWeight(final float n) {
        super.strokeWeight(n);
        this.set_stroke();
    }
    
    public void strokeJoin(final int n) {
        super.strokeJoin(n);
        this.set_stroke();
    }
    
    public void strokeCap(final int n) {
        super.strokeCap(n);
        this.set_stroke();
    }
    
    protected void set_stroke() {
        int n = 0;
        if (this.strokeCap == 2) {
            n = 1;
        }
        else if (this.strokeCap == 4) {
            n = 2;
        }
        int n2 = 2;
        if (this.strokeJoin == 8) {
            n2 = 0;
        }
        else if (this.strokeJoin == 2) {
            n2 = 1;
        }
        this.g2.setStroke(new BasicStroke(this.strokeWeight, n, n2));
    }
    
    public void background(final PImage pImage) {
        if (pImage.width != this.width || pImage.height != this.height) {
            throw new RuntimeException("background image must be the same size as your application");
        }
        if (pImage.format != 1 && pImage.format != 2) {
            throw new RuntimeException("background images should be RGB or ARGB");
        }
        this.set(0, 0, pImage);
    }
    
    public void clear() {
        this.g2.setColor(new Color(this.backgroundColor));
        this.g2.fillRect(0, 0, this.width, this.height);
    }
    
    public void smooth() {
        this.g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    
    public void noSmooth() {
        this.g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }
    
    public void loadPixels() {
        if (this.pixels == null || this.pixels.length != this.width * this.height) {
            this.pixels = new int[this.width * this.height];
        }
        ((BufferedImage)this.image).getRGB(0, 0, this.width, this.height, this.pixels, 0, this.width);
    }
    
    public void updatePixels() {
        this.updatePixels(0, 0, this.width, this.height);
    }
    
    public void updatePixels(final int n, final int n2, final int n3, final int n4) {
        ((BufferedImage)this.image).setRGB(n, n2, (this.imageMode == 0) ? n3 : (n3 - n), (this.imageMode == 0) ? n4 : (n4 - n2), this.pixels, 0, this.width);
    }
    
    public int get(final int n, final int n2) {
        if (n < 0 || n2 < 0 || n >= this.width || n2 >= this.height) {
            return 0;
        }
        return ((BufferedImage)this.image).getRGB(n, n2);
    }
    
    public PImage get(int n, int n2, int n3, int n4) {
        if (this.imageMode == 1) {
            n3 -= n;
            n4 -= n;
        }
        if (n < 0) {
            n3 += n;
            n = 0;
        }
        if (n2 < 0) {
            n4 += n2;
            n2 = 0;
        }
        if (n + n3 > this.width) {
            n3 = this.width - n;
        }
        if (n2 + n4 > this.height) {
            n4 = this.height - n2;
        }
        final PImage pImage = new PImage(n3, n4);
        ((BufferedImage)this.image).getRGB(n, n2, n3, n4, pImage.pixels, 0, n3);
        return pImage;
    }
    
    public PImage get() {
        final PImage pImage = new PImage(this.width, this.height);
        ((BufferedImage)this.image).getRGB(0, 0, this.width, this.height, pImage.pixels, 0, this.width);
        return pImage;
    }
    
    public void set(final int n, final int n2, final int n3) {
        if (n < 0 || n2 < 0 || n >= this.width || n2 >= this.height) {
            return;
        }
        ((BufferedImage)this.image).setRGB(n, n2, n3);
    }
    
    protected void setImpl(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final PImage pImage) {
        ((BufferedImage)this.image).setRGB(n, n2, n5, n6, pImage.pixels, n4 * pImage.width + n3, pImage.width);
    }
    
    public void filter(final int n) {
        this.loadPixels();
        super.filter(n);
        this.updatePixels();
    }
    
    public void filter(final int n, final float n2) {
        this.loadPixels();
        super.filter(n, n2);
        this.updatePixels();
    }
    
    public void copy(final PImage pImage, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.loadPixels();
        super.copy(pImage, n, n2, n3, n4, n5, n6, n7, n8);
        this.updatePixels();
    }
    
    public void blend(final PImage pImage, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.loadPixels();
        super.blend(pImage, n, n2, n3, n4, n5);
        this.updatePixels();
    }
    
    public void blend(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.loadPixels();
        super.blend(n, n2, n3, n4, n5);
        this.updatePixels();
    }
    
    public void blend(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        this.loadPixels();
        super.blend(n, n2, n3, n4, n5, n6, n7, n8, n9);
        this.updatePixels();
    }
    
    public void blend(final PImage pImage, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        this.loadPixels();
        super.blend(pImage, n, n2, n3, n4, n5, n6, n7, n8, n9);
        this.updatePixels();
    }
    
    public void save(final String s) {
        this.loadPixels();
        super.save(s);
    }
    
    private final /* synthetic */ void this() {
        this.transformStack = new AffineTransform[32];
        this.transform = new double[6];
        this.line = new Line2D.Float();
        this.ellipse = new Ellipse2D.Float();
        this.rect = new Rectangle2D.Float();
        this.arc = new Arc2D.Float();
        this.curveX = new float[4];
        this.curveY = new float[4];
    }
    
    public PGraphics2() {
        this.this();
    }
    
    public PGraphics2(final int n, final int n2, final PApplet pApplet) {
        super(n, n2, pApplet);
        this.this();
    }
    
    class ImageCache
    {
        PImage source;
        boolean tinted;
        int tintedColor;
        int[] tintedPixels;
        BufferedImage image;
        
        public void update() {
            if (this.source.format == 2 || this.source.format == 1) {
                if (PGraphics2.this.tint) {
                    if (this.tintedPixels == null) {
                        this.tintedPixels = new int[this.source.width * this.source.height];
                    }
                    final int tintColor = PGraphics2.this.tintColor;
                    final int n = PGraphics2.this.tintColor >> 24 & 0xFF;
                    final int n2 = PGraphics2.this.tintColor >> 16 & 0xFF;
                    final int n3 = PGraphics2.this.tintColor >> 8 & 0xFF;
                    final int n4 = PGraphics2.this.tintColor & 0xFF;
                    if (this.source.format == 1) {
                        final int n5 = n << 24;
                        for (int i = 0; i < this.tintedPixels.length; ++i) {
                            final int n6 = this.source.pixels[i];
                            this.tintedPixels[i] = (n5 | (n2 * (n6 >> 16 & 0xFF) & 0xFF00) << 8 | (n3 * (n6 >> 8 & 0xFF) & 0xFF00) | (n4 * (n6 & 0xFF) & 0xFF00) >> 8);
                        }
                    }
                    else {
                        for (int j = 0; j < this.tintedPixels.length; ++j) {
                            final int n7 = this.source.pixels[j];
                            this.tintedPixels[j] = ((n * (n7 >> 24 & 0xFF) & 0xFF00) << 16 | (n2 * (n7 >> 16 & 0xFF) & 0xFF00) << 8 | (n3 * (n7 >> 8 & 0xFF) & 0xFF00) | (n4 * (n7 & 0xFF) & 0xFF00) >> 8);
                        }
                    }
                    this.tinted = true;
                    this.tintedColor = PGraphics2.this.tintColor;
                    this.image.setRGB(0, 0, this.source.width, this.source.height, this.tintedPixels, 0, this.source.width);
                }
                else {
                    this.image.setRGB(0, 0, this.source.width, this.source.height, this.source.pixels, 0, this.source.width);
                }
            }
            else if (this.source.format == 4) {
                if (this.tintedPixels == null) {
                    this.tintedPixels = new int[this.source.width * this.source.height];
                }
                final int n8 = PGraphics2.this.tintColor & 0xFFFFFF;
                if ((PGraphics2.this.tintColor >> 24 & 0xFF) >= 254) {
                    for (int k = 0; k < this.tintedPixels.length; ++k) {
                        final int[] tintedPixels = this.tintedPixels;
                        final int n9 = k;
                        int n10 = 0;
                        if (this.source.pixels[k] != 0) {
                            n10 = (this.source.pixels[k] << 24 | n8);
                        }
                        tintedPixels[n9] = n10;
                    }
                }
                else {
                    final int n11 = PGraphics2.this.tintColor >> 24 & 0xFF;
                    for (int l = 0; l < this.tintedPixels.length; ++l) {
                        final int[] tintedPixels2 = this.tintedPixels;
                        final int n12 = l;
                        int n13 = 0;
                        if (this.source.pixels[l] != 0) {
                            n13 = ((n11 * this.source.pixels[l] & 0xFF00) << 16 | n8);
                        }
                        tintedPixels2[n12] = n13;
                    }
                }
                this.tinted = true;
                this.tintedColor = PGraphics2.this.tintColor;
                this.image.setRGB(0, 0, this.source.width, this.source.height, this.tintedPixels, 0, this.source.width);
            }
        }
        
        public ImageCache(final PImage source) {
            this.source = source;
            this.image = new BufferedImage(source.width, source.height, 2);
        }
    }
}
