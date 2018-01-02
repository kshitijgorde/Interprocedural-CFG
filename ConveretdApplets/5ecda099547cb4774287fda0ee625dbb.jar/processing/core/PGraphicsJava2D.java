// 
// Decompiled by Procyon v0.5.30
// 

package processing.core;

import java.awt.image.WritableRaster;
import java.util.Arrays;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.awt.Paint;
import java.awt.Color;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.Graphics2D;

public class PGraphicsJava2D extends PGraphics
{
    public Graphics2D g2;
    GeneralPath gpath;
    boolean breakShape;
    float[] curveCoordX;
    float[] curveCoordY;
    float[] curveDrawX;
    float[] curveDrawY;
    int transformCount;
    AffineTransform[] transformStack;
    double[] transform;
    Line2D.Float line;
    Ellipse2D.Float ellipse;
    Rectangle2D.Float rect;
    Arc2D.Float arc;
    protected Color tintColorObject;
    protected Color fillColorObject;
    public boolean fillGradient;
    public Paint fillGradientObject;
    protected Color strokeColorObject;
    public boolean strokeGradient;
    public Paint strokeGradientObject;
    int[] clearPixels;
    static int[] getset;
    
    public PGraphicsJava2D() {
        this.transformStack = new AffineTransform[32];
        this.transform = new double[6];
        this.line = new Line2D.Float();
        this.ellipse = new Ellipse2D.Float();
        this.rect = new Rectangle2D.Float();
        this.arc = new Arc2D.Float();
    }
    
    public void setSize(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.width1 = this.width - 1;
        this.height1 = this.height - 1;
        this.allocate();
        this.reapplySettings();
    }
    
    protected void allocate() {
        this.image = new BufferedImage(this.width, this.height, 2);
        this.g2 = (Graphics2D)this.image.getGraphics();
    }
    
    public boolean canDraw() {
        return true;
    }
    
    public void beginDraw() {
        this.checkSettings();
        this.resetMatrix();
        this.vertexCount = 0;
    }
    
    public void endDraw() {
        if (!this.primarySurface) {
            this.loadPixels();
        }
        this.modified = true;
    }
    
    public void beginShape(final int shape) {
        this.shape = shape;
        this.vertexCount = 0;
        this.curveVertexCount = 0;
        this.gpath = null;
    }
    
    public void texture(final PImage pImage) {
        PGraphics.showMethodWarning("texture");
    }
    
    public void vertex(final float n, final float n2) {
        this.curveVertexCount = 0;
        if (this.vertexCount == this.vertices.length) {
            final float[][] vertices = new float[this.vertexCount << 1][36];
            System.arraycopy(this.vertices, 0, vertices, 0, this.vertexCount);
            this.vertices = vertices;
        }
        this.vertices[this.vertexCount][0] = n;
        this.vertices[this.vertexCount][1] = n2;
        ++this.vertexCount;
        switch (this.shape) {
            case 2: {
                this.point(n, n2);
                break;
            }
            case 4: {
                if (this.vertexCount % 2 == 0) {
                    this.line(this.vertices[this.vertexCount - 2][0], this.vertices[this.vertexCount - 2][1], n, n2);
                    break;
                }
                break;
            }
            case 9: {
                if (this.vertexCount % 3 == 0) {
                    this.triangle(this.vertices[this.vertexCount - 3][0], this.vertices[this.vertexCount - 3][1], this.vertices[this.vertexCount - 2][0], this.vertices[this.vertexCount - 2][1], n, n2);
                    break;
                }
                break;
            }
            case 10: {
                if (this.vertexCount >= 3) {
                    this.triangle(this.vertices[this.vertexCount - 2][0], this.vertices[this.vertexCount - 2][1], this.vertices[this.vertexCount - 1][0], this.vertices[this.vertexCount - 1][1], this.vertices[this.vertexCount - 3][0], this.vertices[this.vertexCount - 3][1]);
                    break;
                }
                break;
            }
            case 11: {
                if (this.vertexCount == 3) {
                    this.triangle(this.vertices[0][0], this.vertices[0][1], this.vertices[1][0], this.vertices[1][1], n, n2);
                    break;
                }
                if (this.vertexCount > 3) {
                    (this.gpath = new GeneralPath()).moveTo(this.vertices[0][0], this.vertices[0][1]);
                    this.gpath.lineTo(this.vertices[this.vertexCount - 2][0], this.vertices[this.vertexCount - 2][1]);
                    this.gpath.lineTo(n, n2);
                    this.drawShape(this.gpath);
                    break;
                }
                break;
            }
            case 16: {
                if (this.vertexCount % 4 == 0) {
                    this.quad(this.vertices[this.vertexCount - 4][0], this.vertices[this.vertexCount - 4][1], this.vertices[this.vertexCount - 3][0], this.vertices[this.vertexCount - 3][1], this.vertices[this.vertexCount - 2][0], this.vertices[this.vertexCount - 2][1], n, n2);
                    break;
                }
                break;
            }
            case 17: {
                if (this.vertexCount >= 4 && this.vertexCount % 2 == 0) {
                    this.quad(this.vertices[this.vertexCount - 4][0], this.vertices[this.vertexCount - 4][1], this.vertices[this.vertexCount - 2][0], this.vertices[this.vertexCount - 2][1], n, n2, this.vertices[this.vertexCount - 3][0], this.vertices[this.vertexCount - 3][1]);
                    break;
                }
                break;
            }
            case 20: {
                if (this.gpath == null) {
                    (this.gpath = new GeneralPath()).moveTo(n, n2);
                    break;
                }
                if (this.breakShape) {
                    this.gpath.moveTo(n, n2);
                    this.breakShape = false;
                    break;
                }
                this.gpath.lineTo(n, n2);
                break;
            }
        }
    }
    
    public void vertex(final float n, final float n2, final float n3) {
        PGraphics.showDepthWarningXYZ("vertex");
    }
    
    public void vertex(final float n, final float n2, final float n3, final float n4) {
        PGraphics.showVariationWarning("vertex(x, y, u, v)");
    }
    
    public void vertex(final float n, final float n2, final float n3, final float n4, final float n5) {
        PGraphics.showDepthWarningXYZ("vertex");
    }
    
    public void breakShape() {
        this.breakShape = true;
    }
    
    public void endShape(final int n) {
        if (this.gpath != null && this.shape == 20) {
            if (n == 2) {
                this.gpath.closePath();
            }
            this.drawShape(this.gpath);
        }
        this.shape = 0;
    }
    
    public void bezierVertex(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.bezierVertexCheck();
        this.gpath.curveTo(n, n2, n3, n4, n5, n6);
    }
    
    public void bezierVertex(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        PGraphics.showDepthWarningXYZ("bezierVertex");
    }
    
    protected void curveVertexCheck() {
        super.curveVertexCheck();
        if (this.curveCoordX == null) {
            this.curveCoordX = new float[4];
            this.curveCoordY = new float[4];
            this.curveDrawX = new float[4];
            this.curveDrawY = new float[4];
        }
    }
    
    protected void curveVertexSegment(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        this.curveCoordX[0] = n;
        this.curveCoordY[0] = n2;
        this.curveCoordX[1] = n3;
        this.curveCoordY[1] = n4;
        this.curveCoordX[2] = n5;
        this.curveCoordY[2] = n6;
        this.curveCoordX[3] = n7;
        this.curveCoordY[3] = n8;
        this.curveToBezierMatrix.mult(this.curveCoordX, this.curveDrawX);
        this.curveToBezierMatrix.mult(this.curveCoordY, this.curveDrawY);
        if (this.gpath == null) {
            (this.gpath = new GeneralPath()).moveTo(this.curveDrawX[0], this.curveDrawY[0]);
        }
        this.gpath.curveTo(this.curveDrawX[1], this.curveDrawY[1], this.curveDrawX[2], this.curveDrawY[2], this.curveDrawX[3], this.curveDrawY[3]);
    }
    
    public void curveVertex(final float n, final float n2, final float n3) {
        PGraphics.showDepthWarningXYZ("curveVertex");
    }
    
    public void point(final float n, final float n2) {
        if (this.stroke) {
            this.line(n, n2, n + 1.0E-4f, n2 + 1.0E-4f);
        }
    }
    
    public void line(final float n, final float n2, final float n3, final float n4) {
        this.line.setLine(n, n2, n3, n4);
        this.strokeShape(this.line);
    }
    
    public void triangle(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        (this.gpath = new GeneralPath()).moveTo(n, n2);
        this.gpath.lineTo(n3, n4);
        this.gpath.lineTo(n5, n6);
        this.gpath.closePath();
        this.drawShape(this.gpath);
    }
    
    public void quad(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        final GeneralPath generalPath = new GeneralPath();
        generalPath.moveTo(n, n2);
        generalPath.lineTo(n3, n4);
        generalPath.lineTo(n5, n6);
        generalPath.lineTo(n7, n8);
        generalPath.closePath();
        this.drawShape(generalPath);
    }
    
    protected void rectImpl(final float n, final float n2, final float n3, final float n4) {
        this.rect.setFrame(n, n2, n3 - n, n4 - n2);
        this.drawShape(this.rect);
    }
    
    protected void ellipseImpl(final float n, final float n2, final float n3, final float n4) {
        this.ellipse.setFrame(n, n2, n3, n4);
        this.drawShape(this.ellipse);
    }
    
    protected void arcImpl(final float n, final float n2, final float n3, final float n4, float n5, float n6) {
        n5 = -n5 * 57.295776f;
        n6 = -n6 * 57.295776f;
        final float n7 = n6 - n5;
        if (this.fill) {
            this.arc.setArc(n, n2, n3, n4, n5, n7, 2);
            this.fillShape(this.arc);
        }
        if (this.stroke) {
            this.arc.setArc(n, n2, n3, n4, n5, n7, 0);
            this.strokeShape(this.arc);
        }
    }
    
    protected void fillShape(final Shape shape) {
        if (this.fillGradient) {
            this.g2.setPaint(this.fillGradientObject);
            this.g2.fill(shape);
        }
        else if (this.fill) {
            this.g2.setColor(this.fillColorObject);
            this.g2.fill(shape);
        }
    }
    
    protected void strokeShape(final Shape shape) {
        if (this.strokeGradient) {
            this.g2.setPaint(this.strokeGradientObject);
            this.g2.draw(shape);
        }
        else if (this.stroke) {
            this.g2.setColor(this.strokeColorObject);
            this.g2.draw(shape);
        }
    }
    
    protected void drawShape(final Shape shape) {
        if (this.fillGradient) {
            this.g2.setPaint(this.fillGradientObject);
            this.g2.fill(shape);
        }
        else if (this.fill) {
            this.g2.setColor(this.fillColorObject);
            this.g2.fill(shape);
        }
        if (this.strokeGradient) {
            this.g2.setPaint(this.strokeGradientObject);
            this.g2.draw(shape);
        }
        else if (this.stroke) {
            this.g2.setColor(this.strokeColorObject);
            this.g2.draw(shape);
        }
    }
    
    public void box(final float n, final float n2, final float n3) {
        PGraphics.showMethodWarning("box");
    }
    
    public void sphere(final float n) {
        PGraphics.showMethodWarning("sphere");
    }
    
    public void bezierDetail(final int n) {
    }
    
    public void curveDetail(final int n) {
    }
    
    public void smooth() {
        this.smooth = true;
        this.g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    }
    
    public void noSmooth() {
        this.smooth = false;
        this.g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        this.g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
    }
    
    protected void imageImpl(final PImage pImage, final float n, final float n2, final float n3, final float n4, final int n5, final int n6, final int n7, final int n8) {
        if (pImage.width <= 0 || pImage.height <= 0) {
            return;
        }
        if (pImage.getCache(this) == null) {
            pImage.setCache(this, new ImageCache(pImage));
            pImage.updatePixels();
            pImage.modified = true;
        }
        final ImageCache imageCache = (ImageCache)pImage.getCache(this);
        if ((this.tint && !imageCache.tinted) || (this.tint && imageCache.tintedColor != this.tintColor) || (!this.tint && imageCache.tinted)) {
            pImage.updatePixels();
        }
        if (pImage.modified) {
            imageCache.update(this.tint, this.tintColor);
            pImage.modified = false;
        }
        this.g2.drawImage(((ImageCache)pImage.getCache(this)).image, (int)n, (int)n2, (int)n3, (int)n4, n5, n6, n7, n8, null);
    }
    
    public float textAscent() {
        final Font font = this.textFont.getFont();
        if (font == null) {
            return super.textAscent();
        }
        return this.parent.getFontMetrics(font).getAscent();
    }
    
    public float textDescent() {
        final Font font = this.textFont.getFont();
        if (font == null) {
            return super.textDescent();
        }
        return this.parent.getFontMetrics(font).getDescent();
    }
    
    protected boolean textModeCheck(final int n) {
        return n == 4 || n == 256;
    }
    
    public void textSize(final float n) {
        final Font font = this.textFont.getFont();
        if (font != null) {
            final Font deriveFont = font.deriveFont(n);
            this.g2.setFont(deriveFont);
            this.textFont.setFont(deriveFont);
        }
        super.textSize(n);
    }
    
    protected float textWidthImpl(final char[] array, final int n, final int n2) {
        final Font font = this.textFont.getFont();
        if (font == null) {
            return super.textWidthImpl(array, n, n2);
        }
        return this.g2.getFontMetrics(font).charsWidth(array, n, n2 - n);
    }
    
    protected void textLineImpl(final char[] array, final int n, final int n2, final float n3, final float textY) {
        if (this.textFont.getFont() == null) {
            super.textLineImpl(array, n, n2, n3, textY);
            return;
        }
        Object o = this.g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        if (o == null) {
            o = RenderingHints.VALUE_ANTIALIAS_DEFAULT;
        }
        this.g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, this.textFont.smooth ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        this.g2.setColor(this.fillColorObject);
        this.g2.drawChars(array, n, n2 - n, (int)(n3 + 0.5f), (int)(textY + 0.5f));
        this.g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, o);
        this.textX = n3 + this.textWidthImpl(array, n, n2);
        this.textY = textY;
        this.textZ = 0.0f;
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
    
    public void translate(final float n, final float n2) {
        this.g2.translate(n, n2);
    }
    
    public void rotate(final float n) {
        this.g2.rotate(n);
    }
    
    public void rotateX(final float n) {
        PGraphics.showDepthWarning("rotateX");
    }
    
    public void rotateY(final float n) {
        PGraphics.showDepthWarning("rotateY");
    }
    
    public void rotateZ(final float n) {
        PGraphics.showDepthWarning("rotateZ");
    }
    
    public void rotate(final float n, final float n2, final float n3, final float n4) {
        PGraphics.showVariationWarning("rotate");
    }
    
    public void scale(final float n) {
        this.g2.scale(n, n);
    }
    
    public void scale(final float n, final float n2) {
        this.g2.scale(n, n2);
    }
    
    public void scale(final float n, final float n2, final float n3) {
        PGraphics.showDepthWarningXYZ("scale");
    }
    
    public void resetMatrix() {
        this.g2.setTransform(new AffineTransform());
    }
    
    public void applyMatrix(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.g2.transform(new AffineTransform(n, n4, n2, n5, n3, n6));
    }
    
    public void applyMatrix(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16) {
        PGraphics.showVariationWarning("applyMatrix");
    }
    
    public PMatrix getMatrix() {
        return this.getMatrix((PMatrix2D)null);
    }
    
    public PMatrix2D getMatrix(PMatrix2D pMatrix2D) {
        if (pMatrix2D == null) {
            pMatrix2D = new PMatrix2D();
        }
        this.g2.getTransform().getMatrix(this.transform);
        pMatrix2D.set((float)this.transform[0], (float)this.transform[2], (float)this.transform[4], (float)this.transform[1], (float)this.transform[3], (float)this.transform[5]);
        return pMatrix2D;
    }
    
    public PMatrix3D getMatrix(final PMatrix3D pMatrix3D) {
        PGraphics.showVariationWarning("getMatrix");
        return pMatrix3D;
    }
    
    public void setMatrix(final PMatrix2D pMatrix2D) {
        this.g2.setTransform(new AffineTransform(pMatrix2D.m00, pMatrix2D.m10, pMatrix2D.m01, pMatrix2D.m11, pMatrix2D.m02, pMatrix2D.m12));
    }
    
    public void setMatrix(final PMatrix3D pMatrix3D) {
        PGraphics.showVariationWarning("setMatrix");
    }
    
    public void printMatrix() {
        this.getMatrix((PMatrix2D)null).print();
    }
    
    public float screenX(final float n, final float n2) {
        this.g2.getTransform().getMatrix(this.transform);
        return (float)this.transform[0] * n + (float)this.transform[2] * n2 + (float)this.transform[4];
    }
    
    public float screenY(final float n, final float n2) {
        this.g2.getTransform().getMatrix(this.transform);
        return (float)this.transform[1] * n + (float)this.transform[3] * n2 + (float)this.transform[5];
    }
    
    public float screenX(final float n, final float n2, final float n3) {
        PGraphics.showDepthWarningXYZ("screenX");
        return 0.0f;
    }
    
    public float screenY(final float n, final float n2, final float n3) {
        PGraphics.showDepthWarningXYZ("screenY");
        return 0.0f;
    }
    
    public float screenZ(final float n, final float n2, final float n3) {
        PGraphics.showDepthWarningXYZ("screenZ");
        return 0.0f;
    }
    
    public void strokeCap(final int n) {
        super.strokeCap(n);
        this.strokeImpl();
    }
    
    public void strokeJoin(final int n) {
        super.strokeJoin(n);
        this.strokeImpl();
    }
    
    public void strokeWeight(final float n) {
        super.strokeWeight(n);
        this.strokeImpl();
    }
    
    protected void strokeImpl() {
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
    
    protected void strokeFromCalc() {
        super.strokeFromCalc();
        this.strokeColorObject = new Color(this.strokeColor, true);
        this.strokeGradient = false;
    }
    
    protected void tintFromCalc() {
        super.tintFromCalc();
        this.tintColorObject = new Color(this.tintColor, true);
    }
    
    protected void fillFromCalc() {
        super.fillFromCalc();
        this.fillColorObject = new Color(this.fillColor, true);
        this.fillGradient = false;
    }
    
    public void backgroundImpl() {
        if (this.backgroundAlpha) {
            final WritableRaster raster = ((BufferedImage)this.image).getRaster();
            if (this.clearPixels == null || this.clearPixels.length < this.width) {
                this.clearPixels = new int[this.width];
            }
            Arrays.fill(this.clearPixels, this.backgroundColor);
            for (int i = 0; i < this.height; ++i) {
                raster.setDataElements(0, i, this.width, 1, this.clearPixels);
            }
        }
        else {
            this.pushMatrix();
            this.resetMatrix();
            this.g2.setColor(new Color(this.backgroundColor));
            this.g2.fillRect(0, 0, this.width, this.height);
            this.popMatrix();
        }
    }
    
    public void beginRaw(final PGraphics pGraphics) {
        PGraphics.showMethodWarning("beginRaw");
    }
    
    public void endRaw() {
        PGraphics.showMethodWarning("endRaw");
    }
    
    public void loadPixels() {
        if (this.pixels == null || this.pixels.length != this.width * this.height) {
            this.pixels = new int[this.width * this.height];
        }
        ((BufferedImage)this.image).getRaster().getDataElements(0, 0, this.width, this.height, this.pixels);
    }
    
    public void updatePixels() {
        ((BufferedImage)this.image).getRaster().setDataElements(0, 0, this.width, this.height, this.pixels);
    }
    
    public void updatePixels(final int n, final int n2, final int n3, final int n4) {
        if (n != 0 || n2 != 0 || n3 != this.width || n4 != this.height) {
            PGraphics.showVariationWarning("updatePixels(x, y, w, h)");
        }
        this.updatePixels();
    }
    
    public void resize(final int n, final int n2) {
        PGraphics.showMethodWarning("resize");
    }
    
    public int get(final int n, final int n2) {
        if (n < 0 || n2 < 0 || n >= this.width || n2 >= this.height) {
            return 0;
        }
        ((BufferedImage)this.image).getRaster().getDataElements(n, n2, PGraphicsJava2D.getset);
        return PGraphicsJava2D.getset[0];
    }
    
    public PImage getImpl(final int n, final int n2, final int n3, final int n4) {
        final PImage pImage = new PImage(n3, n4);
        pImage.parent = this.parent;
        ((BufferedImage)this.image).getRaster().getDataElements(n, n2, n3, n4, pImage.pixels);
        return pImage;
    }
    
    public PImage get() {
        return this.get(0, 0, this.width, this.height);
    }
    
    public void set(final int n, final int n2, final int n3) {
        if (n < 0 || n2 < 0 || n >= this.width || n2 >= this.height) {
            return;
        }
        PGraphicsJava2D.getset[0] = n3;
        ((BufferedImage)this.image).getRaster().setDataElements(n, n2, PGraphicsJava2D.getset);
    }
    
    protected void setImpl(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final PImage pImage) {
        final WritableRaster raster = ((BufferedImage)this.image).getRaster();
        if (n3 == 0 && n4 == 0 && n5 == pImage.width && n6 == pImage.height) {
            raster.setDataElements(n, n2, pImage.width, pImage.height, pImage.pixels);
        }
        else {
            final PImage value = pImage.get(n3, n4, n5, n6);
            raster.setDataElements(n, n2, value.width, value.height, value.pixels);
        }
    }
    
    public void mask(final int[] array) {
        PGraphics.showMethodWarning("mask");
    }
    
    public void mask(final PImage pImage) {
        PGraphics.showMethodWarning("mask");
    }
    
    public void copy(final int n, final int n2, final int n3, final int n4, int n5, int n6, final int n7, final int n8) {
        if (n3 != n7 || n4 != n8) {
            this.copy(this, n, n2, n3, n4, n5, n6, n7, n8);
        }
        else {
            n5 -= n;
            n6 -= n2;
            this.g2.copyArea(n, n2, n3, n4, n5, n6);
        }
    }
    
    static {
        PGraphicsJava2D.getset = new int[1];
    }
    
    class ImageCache
    {
        PImage source;
        boolean tinted;
        int tintedColor;
        int[] tintedPixels;
        BufferedImage image;
        
        public ImageCache(final PImage source) {
            this.source = source;
        }
        
        public void update(final boolean tinted, final int tintedColor) {
            int n = 2;
            final boolean b = (tintedColor & 0xFF000000) == 0xFF000000;
            if (this.source.format == 1 && (!tinted || (tinted && b))) {
                n = 1;
            }
            final boolean b2 = this.image != null && this.image.getType() != n;
            if (this.image == null || b2) {
                this.image = new BufferedImage(this.source.width, this.source.height, n);
            }
            final WritableRaster raster = this.image.getRaster();
            if (tinted) {
                if (this.tintedPixels == null || this.tintedPixels.length != this.source.width) {
                    this.tintedPixels = new int[this.source.width];
                }
                final int n2 = tintedColor >> 24 & 0xFF;
                final int n3 = tintedColor >> 16 & 0xFF;
                final int n4 = tintedColor >> 8 & 0xFF;
                final int n5 = tintedColor & 0xFF;
                if (n == 1) {
                    int n6 = 0;
                    for (int i = 0; i < this.source.height; ++i) {
                        for (int j = 0; j < this.source.width; ++j) {
                            final int n7 = this.source.pixels[n6++];
                            this.tintedPixels[j] = ((n3 * (n7 >> 16 & 0xFF) & 0xFF00) << 8 | (n4 * (n7 >> 8 & 0xFF) & 0xFF00) | (n5 * (n7 & 0xFF) & 0xFF00) >> 8);
                        }
                        raster.setDataElements(0, i, this.source.width, 1, this.tintedPixels);
                    }
                }
                else if (n == 2) {
                    int n8 = 0;
                    for (int k = 0; k < this.source.height; ++k) {
                        if (this.source.format == 1) {
                            final int n9 = tintedColor & 0xFF000000;
                            for (int l = 0; l < this.source.width; ++l) {
                                final int n10 = this.source.pixels[n8++];
                                this.tintedPixels[l] = (n9 | (n3 * (n10 >> 16 & 0xFF) & 0xFF00) << 8 | (n4 * (n10 >> 8 & 0xFF) & 0xFF00) | (n5 * (n10 & 0xFF) & 0xFF00) >> 8);
                            }
                        }
                        else if (this.source.format == 2) {
                            for (int n11 = 0; n11 < this.source.width; ++n11) {
                                final int n12 = this.source.pixels[n8++];
                                this.tintedPixels[n11] = ((n2 * (n12 >> 24 & 0xFF) & 0xFF00) << 16 | (n3 * (n12 >> 16 & 0xFF) & 0xFF00) << 8 | (n4 * (n12 >> 8 & 0xFF) & 0xFF00) | (n5 * (n12 & 0xFF) & 0xFF00) >> 8);
                            }
                        }
                        else if (this.source.format == 4) {
                            final int n13 = tintedColor & 0xFFFFFF;
                            for (int n14 = 0; n14 < this.source.width; ++n14) {
                                this.tintedPixels[n14] = ((n2 * this.source.pixels[n8++] & 0xFF00) << 16 | n13);
                            }
                        }
                        raster.setDataElements(0, k, this.source.width, 1, this.tintedPixels);
                    }
                }
            }
            else {
                raster.setDataElements(0, 0, this.source.width, this.source.height, this.source.pixels);
            }
            this.tinted = tinted;
            this.tintedColor = tintedColor;
        }
    }
}
