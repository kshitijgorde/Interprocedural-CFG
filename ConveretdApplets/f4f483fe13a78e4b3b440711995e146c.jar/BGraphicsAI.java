import java.text.NumberFormat;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

// 
// Decompiled by Procyon v0.5.30
// 

public class BGraphicsAI extends BGraphics
{
    boolean capture;
    PrintWriter writer;
    String path;
    boolean flip;
    float[][] vertices;
    
    public void beginFrame() {
        super.beginFrame();
        try {
            (this.writer = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(this.path + format(super.frameCount, 4, 0) + ".ai"))))).print("%!PS-Adobe-2.0 \r\n");
            this.writer.print("%%Creator:Adobe Illustrator(TM) 5.0\r\n");
            this.writer.print("%%BoundingBox:0 0 " + super.width + " " + super.height + "\r\n");
            this.writer.print("%%AI3_TemplateBox:" + super.width / 2 + " " + super.height / 2 + " " + super.width / 2 + " " + super.height / 2 + "\r\n");
            this.writer.print("%AI5_ArtSize: " + super.width + " " + super.height + "\r\n");
            this.writer.print("%AI5_RulerUnits: 0\r\n");
            this.writer.print("%%EndComments\r\n");
            this.writer.print("%%EndProlog\r\n");
            this.writer.print("%%BeginSetup\r\n");
            this.writer.print("Adobe_Illustrator_1.1 begin\r\n");
            this.writer.print("n\r\n");
            this.writer.print("%%EndSetup\r\n");
            this.writer.print("0 i\r\n");
            this.writer.print("0 J 0 j 0.25 w 4 M []0 d\r\n");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void clear() {
        if (super._background) {
            this.beginLock();
            this.moveto(0.0f, 0.0f);
            this.lineto(super.width, 0.0f);
            this.lineto(super.width, super.height);
            this.lineto(0.0f, super.height);
            this.lineto(0.0f, 0.0f);
            this.writer.print("f\r\n");
            this.endLock();
        }
    }
    
    public void endFrame() {
        super.endFrame();
        this.writer.print("%%PageTrailer\r\n");
        this.writer.print("%%Trailer\r\n");
        this.writer.print("_E end\r\n");
        this.writer.print("%%EOF\r\n");
        this.writer.flush();
        this.writer.close();
        this.writer = null;
    }
    
    public void endShape() {
        super.shape = false;
        int vertexCount = super.polygon.vertexCount;
        final float[][] vertices = super.polygon.vertices;
        switch (super.shapeKind) {
            case 16: {
                if (super._stroke) {
                    for (int i = 0; i < vertexCount; ++i) {
                        if (super.strokeChanged) {
                            this.writer.print(format(vertices[i][12], 1, 2) + " " + format(vertices[i][13], 1, 2) + " " + format(vertices[i][14], 1, 2) + " Xa\r\n");
                        }
                        this.moveto(vertices[i][9], vertices[i][10]);
                        this.lineto(vertices[i][9], vertices[i][10]);
                    }
                }
                break;
            }
            case 32:
            case 33:
            case 34: {
                if (!super._stroke) {
                    return;
                }
                if (super.shapeKind == 34) {
                    final float[] array = super.polygon.vertices[0];
                    final float[] nextVertex = super.polygon.nextVertex();
                    ++vertexCount;
                    nextVertex[0] = array[0];
                    nextVertex[1] = array[1];
                    nextVertex[2] = array[2];
                    nextVertex[12] = array[12];
                    nextVertex[13] = array[13];
                    nextVertex[14] = array[14];
                }
                final int n = (super.shapeKind == 32) ? 2 : 1;
                final int n2 = vertexCount - 1;
                final int n3 = 1;
                final int n4 = 0;
                for (int j = 0; j < n2; j += n) {
                    if (n4 == 0 || (j + n3) % n4 != 0) {
                        final float[] array2 = vertices[j];
                        final float[] array3 = vertices[j + n3];
                        if (super.strokeChanged) {
                            this.writer.print(format(vertices[j][12], 1, 2) + " " + format(vertices[j][13], 1, 2) + " " + format(vertices[j][14], 1, 2) + " Xa\r\n");
                        }
                        this.moveto(array2[0], array2[1]);
                        this.lineto(array2[0], array3[1]);
                        this.writer.print("S\r\n");
                    }
                }
                break;
            }
            case 64:
            case 65: {
                for (int n5 = (super.shapeKind == 64) ? 3 : 2, k = 0; k < vertexCount - 2; k += n5) {
                    if (super.strokeChanged) {
                        this.writer.print(format(vertices[k][12], 1, 2) + " " + format(vertices[k][13], 1, 2) + " " + format(vertices[k][14], 1, 2) + " Xa\r\n");
                    }
                    this.moveto(vertices[k][0], vertices[k][1]);
                    this.lineto(vertices[k + 1][0], vertices[k + 1][1]);
                    this.lineto(vertices[k + 2][0], vertices[k + 2][1]);
                    this.lineto(vertices[k][0], vertices[k][1]);
                }
                if (super._fill) {
                    if (super._stroke) {
                        this.writer.print("B\r\n");
                    }
                    else {
                        this.writer.print("f\r\n");
                    }
                }
                else {
                    this.writer.print("S\r\n");
                }
                break;
            }
            case 128:
            case 129: {
                for (int n6 = (super.shapeKind == 128) ? 4 : 3, l = 0; l < vertexCount - 3; l += n6) {
                    if (super.strokeChanged) {
                        this.writer.print(format(vertices[l][12], 1, 2) + " " + format(vertices[l][13], 1, 2) + " " + format(vertices[l][14], 1, 2) + " Xa\r\n");
                    }
                    this.moveto(vertices[l][0], vertices[l][1]);
                    this.lineto(vertices[l + 1][0], vertices[l + 1][1]);
                    this.lineto(vertices[l + 2][0], vertices[l + 2][1]);
                    this.lineto(vertices[l + 3][0], vertices[l + 3][1]);
                    this.lineto(vertices[l][0], vertices[l][1]);
                }
                if (super._fill) {
                    if (super._stroke) {
                        this.writer.print("B\r\n");
                    }
                    else {
                        this.writer.print("f\r\n");
                    }
                }
                else {
                    this.writer.print("S\r\n");
                }
                break;
            }
            case 256:
            case 257:
            case 258: {
                this.moveto(vertices[vertexCount - 1][0], vertices[vertexCount - 1][1]);
                for (int n7 = 0; n7 < vertexCount; ++n7) {
                    if (super.strokeChanged) {
                        this.writer.print(format(vertices[n7][12], 1, 2) + " " + format(vertices[n7][13], 1, 2) + " " + format(vertices[n7][14], 1, 2) + " Xa\r\n");
                    }
                    this.lineto(vertices[n7][0], vertices[n7][1]);
                }
                if (super._fill) {
                    if (super._stroke) {
                        this.writer.print("B\r\n");
                    }
                    else {
                        this.writer.print("f\r\n");
                    }
                }
                else {
                    this.writer.print("S\r\n");
                }
                break;
            }
        }
    }
    
    public BFont loadFont(final String s) {
        return new BFontAI(s, this);
    }
    
    protected void calc_fill() {
        super.calc_fill();
        if (super.fillR == super.fillG && super.fillG == super.fillB) {
            this.writer.print(format(super.fillR, 1, 2) + " " + format(super.fillG, 1, 2) + " " + format(super.fillB, 1, 2) + " Xa\r\n");
        }
        else {
            this.writer.print(format(super.fillR, 1, 2) + " g\r\n");
        }
    }
    
    protected void calc_stroke() {
        super.calc_stroke();
        if (super.strokeR == super.strokeG && super.strokeG == super.strokeB) {
            this.writer.print(format(super.strokeR, 1, 2) + " " + format(super.strokeG, 1, 2) + " " + format(super.strokeB, 1, 2) + " XA\r\n");
        }
        else {
            this.writer.print(format(super.strokeG, 1, 2) + " G\r\n");
        }
    }
    
    protected void calc_background() {
        super.calc_background();
    }
    
    public void strokeWidth(final float n) {
        this.writer.print(format(n, 4, 4) + " w\r\n");
    }
    
    public void strokeMode(final int n) {
        final int n2 = n & 0x7;
        if (n2 != 0) {
            this.writer.print((n2 >> 1) + " J\r\n");
        }
        final int n3 = n & 0x38;
        if (n3 != 0) {
            this.writer.print((n3 >> 4) + " j\r\n");
        }
    }
    
    public void beginGroup() {
        this.writer.print("u\r\n");
    }
    
    public void endGroup() {
        this.writer.print("U\r\n");
    }
    
    public void beginLock() {
        this.writer.print("1 A\r\n");
    }
    
    public void endLock() {
        this.writer.print("0 A\r\n");
    }
    
    public void moveto(final float n, final float n2) {
        this.writer.print(format(n, 4, 4) + " " + format(this.flip ? (super.height - n2) : n2, 4, 4) + " m\r\n");
    }
    
    public void lineto(final float n, final float n2) {
        this.writer.print(format(n, 4, 4) + " " + format(this.flip ? (super.height - n2) : n2, 4, 4) + " l\r\n");
    }
    
    public static String format(final float n, final int maximumIntegerDigits, final int n2) {
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumIntegerDigits(maximumIntegerDigits);
        instance.setMaximumFractionDigits(n2);
        instance.setMinimumFractionDigits(n2);
        instance.setGroupingUsed(false);
        return instance.format(n);
    }
    
    public BGraphicsAI(final int width, final int height, final String path) {
        super.width = width;
        super.height = height;
        this.path = path;
        super.frameCount = 0;
        this.flip = true;
        this.defaults();
        System.out.println("BGraphicsAI.<init>");
    }
}
