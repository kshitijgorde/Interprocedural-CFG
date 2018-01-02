import java.awt.geom.PathIterator;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.text.AttributedCharacterIterator;
import java.io.DataOutput;
import java.util.Vector;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class WVFGraphics extends Graphics implements VecGraphics
{
    float I;
    float abs;
    Color bezier;
    Color black;
    Color cos;
    double currentSegment;
    double dibuixaArc;
    Font dibuixaCamiArc;
    Font dibuixaCamiArc2;
    float drawAnsiString;
    float endPath;
    float equals;
    boolean extractDouble;
    ByteArrayOutputStream extractToken;
    DataOutputStream firstToken;
    Hashtable flush;
    ByteArrayOutputStream get;
    DataOutputStream getBlue;
    Hashtable getClipBounds;
    ByteArrayOutputStream getColorNumber;
    public DataOutputStream data;
    ByteArrayOutputStream getFontMetrics;
    DataOutputStream getFontNumber;
    String getGreen;
    WVFComponentCapsa getName;
    
    WVFGraphics(final int n, final int n2, final WVFComponentCapsa getName) {
        this.black = null;
        this.cos = null;
        this.dibuixaArc = -1.0;
        this.dibuixaCamiArc2 = null;
        this.extractDouble = false;
        this.flush = new Hashtable();
        this.getClipBounds = new Hashtable();
        this.getName = getName;
        this.I = 0.0f;
        this.abs = 0.0f;
        this.bezier = Color.black;
        this.dibuixaCamiArc = null;
        this.equals = 1.0f / getName.I;
        this.getGreen = getName.Z;
        this.drawAnsiString = (int)(n * this.equals);
        this.endPath = (int)(n2 * this.equals);
        this.getColorNumber = new ByteArrayOutputStream();
        this.data = new DataOutputStream(this.getColorNumber);
        this.getFontMetrics = new ByteArrayOutputStream();
        this.getFontNumber = new DataOutputStream(this.getFontMetrics);
        try {
            this.getFontNumber.writeByte(87);
            this.getFontNumber.writeByte(86);
            this.getFontNumber.writeByte(70);
            this.getFontNumber.writeUTF("1.0");
            this.getFontNumber.writeUTF("nom");
            this.getFontNumber.writeFloat(0.0f);
            this.getFontNumber.writeFloat(0.0f);
            this.getFontNumber.writeFloat(n * this.equals);
            this.getFontNumber.writeFloat(n2 * this.equals);
            this.getFontNumber.writeFloat(n * this.equals);
            this.getFontNumber.writeFloat(n2 * this.equals);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void setLineWidth(final float n) {
        this.currentSegment = n;
    }
    
    public final Graphics create() {
        return null;
    }
    
    public final void translate(final int n, final int n2) {
        this.I += n * this.equals;
        this.abs += n2 * this.equals;
    }
    
    public final Color getColor() {
        return this.bezier;
    }
    
    public final void setColor(final Color bezier) {
        this.bezier = bezier;
    }
    
    public final void setPaintMode() {
    }
    
    public final void setXORMode(final Color color) {
    }
    
    public final Font getFont() {
        return this.dibuixaCamiArc;
    }
    
    public final void setFont(final Font dibuixaCamiArc) {
        this.dibuixaCamiArc = dibuixaCamiArc;
    }
    
    public final FontMetrics getFontMetrics(final Font font) {
        return this.getName.getFontMetrics(font);
    }
    
    public final Rectangle getClipBounds() {
        return new Rectangle(0, 0, (int)(this.drawAnsiString / this.equals), (int)(this.endPath / this.equals));
    }
    
    public final void setClip(final int n, final int n2, final int n3, final int n4) {
    }
    
    public final void clipRect(final int n, final int n2, final int n3, final int n4) {
    }
    
    public final void setClip(final Shape shape) {
    }
    
    public final Shape getClip() {
        return this.getClipBounds();
    }
    
    public final void copyArea(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
    }
    
    public final void drawLine(final int n, final int n2, final int n3, final int n4) {
        try {
            this.abs();
            this.data.writeShort(-15870);
            this.data.writeInt(16);
            this.data.writeFloat(this.I + n * this.equals);
            this.data.writeFloat(this.endPath - this.abs - n2 * this.equals);
            this.data.writeFloat(this.I + n3 * this.equals);
            this.data.writeFloat(this.endPath - this.abs - n4 * this.equals);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void drawRect(final int n, final int n2, final int n3, final int n4) {
        try {
            this.abs();
            this.data.writeShort(-15867);
            this.data.writeInt(36);
            this.data.writeInt(4);
            final float n5 = this.I + n * this.equals;
            final float n6 = this.endPath - (this.abs + (n2 + n4) * this.equals);
            final float n7 = n3 * this.equals;
            final float n8 = n4 * this.equals;
            this.data.writeFloat(n5);
            this.data.writeFloat(n6);
            this.data.writeFloat(n5 + n7);
            this.data.writeFloat(n6);
            this.data.writeFloat(n5 + n7);
            this.data.writeFloat(n6 + n8);
            this.data.writeFloat(n5);
            this.data.writeFloat(n6 + n8);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void fillRect(final int n, final int n2, final int n3, final int n4) {
        try {
            this.bezier();
            this.data.writeShort(-15867);
            this.data.writeInt(36);
            this.data.writeInt(4);
            final float n5 = this.I + n * this.equals;
            final float n6 = this.endPath - (this.abs + (n2 + n4) * this.equals);
            final float n7 = n3 * this.equals;
            final float n8 = n4 * this.equals;
            this.data.writeFloat(n5);
            this.data.writeFloat(n6);
            this.data.writeFloat(n5 + n7);
            this.data.writeFloat(n6);
            this.data.writeFloat(n5 + n7);
            this.data.writeFloat(n6 + n8);
            this.data.writeFloat(n5);
            this.data.writeFloat(n6 + n8);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void clearRect(final int n, final int n2, final int n3, final int n4) {
    }
    
    public final void drawRoundRect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
    }
    
    public final void fillRoundRect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
    }
    
    public final void drawOval(final int n, final int n2, final int n3, final int n4) {
        this.dibuixaArc(n, n2, n3, n4, 0.0, 360.0, false);
    }
    
    public final void fillOval(final int n, final int n2, final int n3, final int n4) {
        this.dibuixaArc(n, n2, n3, n4, 0.0, 360.0, true);
    }
    
    public final void drawArc(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.dibuixaArc(n, n2, n3, n4, n5, n6, false);
    }
    
    public final void fillArc(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.dibuixaArc(n, n2, n3, n4, n5, n6, true);
    }
    
    public final void drawPolygon(final int[] array, final int[] array2, final int n) {
        try {
            this.abs();
            this.data.writeShort(-15867);
            this.data.writeInt(8 * n + 4);
            this.data.writeInt(n);
            for (int i = 0; i < n; ++i) {
                this.data.writeFloat(this.I + array[i] * this.equals);
                this.data.writeFloat(this.endPath - this.abs - array2[i] * this.equals);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void fillPolygon(final int[] array, final int[] array2, final int n) {
        try {
            this.bezier();
            this.data.writeShort(-15867);
            this.data.writeInt(8 * n + 4);
            this.data.writeInt(n);
            for (int i = 0; i < n; ++i) {
                this.data.writeFloat(this.I + array[i] * this.equals);
                this.data.writeFloat(this.endPath - this.abs - array2[i] * this.equals);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void drawString(final String s, final int n, final int n2) {
        this.drawAnsiString(s, n, n2);
    }
    
    public final void drawAnsiString(String s, final int n, final int n2) {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            if (s.length() == 0) {
                s = " ";
            }
            dataOutputStream.writeUTF(s);
            dataOutputStream.flush();
            this.I();
            this.data.writeShort(-15865);
            this.data.writeInt(byteArrayOutputStream.size() + 8);
            this.data.writeFloat(this.I + n * this.equals);
            this.data.writeFloat(this.endPath - this.abs - n2 * this.equals);
            this.data.write(byteArrayOutputStream.toByteArray());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final boolean drawImage(final Image image, final int n, final int n2, final ImageObserver imageObserver) {
        return false;
    }
    
    public final boolean drawImage(final Image image, final int n, final int n2, final int n3, final int n4, final ImageObserver imageObserver) {
        return false;
    }
    
    public final boolean drawImage(final Image image, final int n, final int n2, final Color color, final ImageObserver imageObserver) {
        return false;
    }
    
    public final boolean drawImage(final Image image, final int n, final int n2, final int n3, final int n4, final Color color, final ImageObserver imageObserver) {
        return false;
    }
    
    public final boolean drawImage(final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final ImageObserver imageObserver) {
        return false;
    }
    
    public final boolean drawImage(final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final Color color, final ImageObserver imageObserver) {
        return false;
    }
    
    public final void drawPolyline(final int[] array, final int[] array2, final int n) {
        try {
            this.abs();
            this.data.writeShort(-15866);
            this.data.writeInt(8 * n + 4);
            this.data.writeInt(n);
            for (int i = 0; i < n; ++i) {
                this.data.writeFloat(this.I + array[i] * this.equals);
                this.data.writeFloat(this.endPath - this.abs - array2[i] * this.equals);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void dispose() {
    }
    
    private void dibuixaCamiArc(final boolean b, double n, double n2, double n3, double n4, double n5, double n6) {
        try {
            n *= this.equals;
            n2 *= this.equals;
            n3 *= this.equals;
            n4 *= this.equals;
            n5 = n5 * 3.141592653589793 / 180.0;
            n6 = n6 * 3.141592653589793 / 180.0;
            n3 /= 2.0;
            n4 = -n4 / 2.0;
            n += n3;
            n2 += n4;
            int n7;
            if (Math.abs(n6) < 1.0) {
                n7 = 1;
            }
            else if (Math.abs(n6) < 3.0) {
                n7 = 4;
            }
            else {
                n7 = 8;
            }
            final double n8 = n6 / n7;
            final double n9 = n8 / 3.0;
            final double n10 = n3 / Math.cos(n9);
            final double n11 = n4 / Math.cos(n9);
            for (int i = 0; i <= n7; ++i) {
                final double n12 = n + n3 * Math.cos(n5 + i * n8) + this.I;
                final double n13 = n2 + n4 * Math.sin(n5 + i * n8) + this.abs;
                if (i == 0) {
                    if (b) {
                        this.data.writeShort(-15742);
                        this.data.writeInt(8);
                        this.data.writeFloat((float)n12);
                        this.data.writeFloat((float)(this.endPath - n13));
                    }
                }
                else {
                    final double n14 = n + n10 * Math.cos(n5 + i * n8 - n9) + this.I;
                    final double n15 = n2 + n11 * Math.sin(n5 + i * n8 - n9) + this.abs;
                    final double n16 = n + n10 * Math.cos(n5 + (i - 1) * n8 + n9) + this.I;
                    final double n17 = n2 + n11 * Math.sin(n5 + (i - 1) * n8 + n9) + this.abs;
                    this.data.writeShort(-15740);
                    this.data.writeInt(24);
                    this.data.writeFloat((float)n16);
                    this.data.writeFloat((float)(this.endPath - n17));
                    this.data.writeFloat((float)n14);
                    this.data.writeFloat((float)(this.endPath - n15));
                    this.data.writeFloat((float)n12);
                    this.data.writeFloat((float)(this.endPath - n13));
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void dibuixaArc(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final boolean b) {
        try {
            if (b) {
                this.bezier();
            }
            else {
                this.abs();
            }
            this.dibuixaCamiArc(true, n, n2 + n4, n3, n4, n5, n6);
            if (b && n6 < 360.0) {
                final double n7 = (n + n3 / 2.0) * this.equals + this.I;
                final double n8 = (n2 + n4 / 2.0) * this.equals + this.abs;
                this.data.writeShort(-15741);
                this.data.writeInt(8);
                this.data.writeFloat((float)n7);
                this.data.writeFloat((float)(this.endPath - n8));
            }
            this.data.writeShort(641);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void dibuixaPoly(final Poly2 poly2, final DoubleRectangle doubleRectangle, final boolean b, final boolean b2) {
        this.bezier();
        final int[] array = new int[20];
        double n = 0.0;
        double double1 = 1.0;
        final Vector vector = new Vector();
        final int[] array2 = { poly2.nextToken };
        double n2 = 0.0;
        double double2 = 0.0;
        String s = poly2.firstToken;
        doubleRectangle.width /= poly2.source_width;
        doubleRectangle.height /= poly2.source_height;
        int n3 = 1;
        while (!s.equals("") && !s.startsWith("e")) {
            if (s.startsWith("clock")) {}
            if (s.startsWith("i") || s.startsWith("f")) {
                if (n3 == 0) {
                    this.endPath();
                }
                n3 = 0;
                n = 0.0;
                double1 = 1.0;
                n2 = Poly2.extractDouble(poly2.source, array2);
                double2 = -Poly2.extractDouble(poly2.source, array2);
                this.move(doubleRectangle.x + n2 * doubleRectangle.width, doubleRectangle.y + double2 * doubleRectangle.height);
            }
            else if (s.startsWith("oy")) {
                Poly2.extractDouble(poly2.source, array2);
            }
            else if (s.startsWith("ox")) {
                Poly2.extractDouble(poly2.source, array2);
            }
            if (s.startsWith("s")) {
                n2 += Poly2.extractDouble(poly2.source, array2);
                double2 -= Poly2.extractDouble(poly2.source, array2);
                this.line(doubleRectangle.x + n2 * doubleRectangle.width, doubleRectangle.y + double2 * doubleRectangle.height);
            }
            else if (s.startsWith("l")) {
                final double double3 = Poly2.extractDouble(poly2.source, array2);
                n2 += double3 * Math.cos(n);
                double2 -= double3 * Math.sin(n);
                this.line(doubleRectangle.x + n2 * doubleRectangle.width, doubleRectangle.y + double2 * doubleRectangle.height);
            }
            else if (s.startsWith("al")) {
                n2 = Poly2.extractDouble(poly2.source, array2);
                double2 = Poly2.extractDouble(poly2.source, array2);
                this.line(doubleRectangle.x + n2 * doubleRectangle.width, doubleRectangle.y + double2 * doubleRectangle.height);
            }
            else if (s.startsWith("g")) {
                n += Poly2.extractDouble(poly2.source, array2) * 3.141592653589793 / 180.0;
            }
            else if (s.equals("ag")) {
                n = Poly2.extractDouble(poly2.source, array2) * 3.141592653589793 / 180.0;
            }
            else if (s.startsWith("t")) {
                double1 = Poly2.extractDouble(poly2.source, array2);
            }
            else if (s.startsWith("a")) {
                final double double4 = Poly2.extractDouble(poly2.source, array2);
                final double double5 = Poly2.extractDouble(poly2.source, array2);
                final double n4 = n;
                final double n5 = Integer.parseInt(Poly2.extractToken(poly2.source, array2)) * 3.141592653589793 / 180.0;
                final double n6 = n4 + ((n5 >= 0.0) ? -1.5707963267948966 : 1.5707963267948966);
                n += n5;
                final double n7 = n2 - double4 * Math.cos(n6);
                final double n8 = double2 + double5 * Math.sin(n6);
                n2 = double4 * Math.cos(n6 + n5) + n7;
                double2 = -double5 * Math.sin(n6 + n5) + n8;
                this.dibuixaCamiArc2(doubleRectangle, false, n7 - double4, n8 + double5, 2.0 * double4, 2.0 * double5, n6 * 180.0 / 3.141592653589793, n5 * 180.0 / 3.141592653589793);
            }
            else if (s.startsWith("z")) {
                final double double6 = Poly2.extractDouble(poly2.source, array2);
                final double double7 = Poly2.extractDouble(poly2.source, array2);
                final double n9 = Poly2.extractDouble(poly2.source, array2) * 3.141592653589793 / 180.0;
                final double double8 = Poly2.extractDouble(poly2.source, array2);
                final double n10 = double1 * Math.cos(n);
                final double n11 = -double1 * Math.sin(n);
                double1 = double8;
                n = n9;
                final double n12 = -double1 * Math.cos(n);
                final double n13 = double1 * Math.sin(n);
                final double n14 = n2 + n10;
                final double n15 = double2 + n11;
                final double n16 = n2 + double6;
                final double n17 = double2 - double7;
                this.bezier(doubleRectangle.x + n14 * doubleRectangle.width, doubleRectangle.y + n15 * doubleRectangle.height, doubleRectangle.x + (n16 + n12) * doubleRectangle.width, doubleRectangle.y + (n17 + n13) * doubleRectangle.height, doubleRectangle.x + n16 * doubleRectangle.width, doubleRectangle.y + n17 * doubleRectangle.height);
                n2 = n16;
                double2 = n17;
            }
            s = Poly2.extractToken(poly2.source, array2);
        }
        this.endPath();
    }
    
    public final void move(final double n, final double n2) {
        try {
            this.data.writeShort(-15742);
            this.data.writeInt(8);
            this.data.writeFloat((float)(this.I + n * this.equals));
            this.data.writeFloat((float)(this.endPath - this.abs - n2 * this.equals));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void line(final double n, final double n2) {
        try {
            this.data.writeShort(-15741);
            this.data.writeInt(8);
            this.data.writeFloat((float)(this.I + n * this.equals));
            this.data.writeFloat((float)(this.endPath - this.abs - n2 * this.equals));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void bezier(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        try {
            this.data.writeShort(-15740);
            this.data.writeInt(24);
            this.data.writeFloat((float)(this.I + n * this.equals));
            this.data.writeFloat((float)(this.endPath - this.abs - n2 * this.equals));
            this.data.writeFloat((float)(this.I + n3 * this.equals));
            this.data.writeFloat((float)(this.endPath - this.abs - n4 * this.equals));
            this.data.writeFloat((float)(this.I + n5 * this.equals));
            this.data.writeFloat((float)(this.endPath - this.abs - n6 * this.equals));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void endPath() {
        try {
            this.data.writeShort(641);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void dibuixaCamiArc2(final DoubleRectangle doubleRectangle, final boolean b, final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        this.dibuixaCamiArc(b, doubleRectangle.x + doubleRectangle.width * n, doubleRectangle.y + doubleRectangle.height * n2, doubleRectangle.width * n3, doubleRectangle.height * n4, n5, n6);
    }
    
    private final void I() {
        this.bezier();
        if (this.dibuixaCamiArc != this.dibuixaCamiArc2) {
            try {
                final int fontNumber = this.getFontNumber(this.dibuixaCamiArc);
                this.data.writeShort(-32506);
                this.data.writeInt(fontNumber);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private final void abs() {
        try {
            if (this.extractDouble) {
                this.data.writeShort(16641);
                this.data.writeShort(0);
                this.extractDouble = false;
            }
            if (this.currentSegment != this.dibuixaArc) {
                this.data.writeShort(-32509);
                this.data.writeFloat((float)(this.currentSegment * this.equals));
                this.dibuixaArc = this.currentSegment;
            }
            if (!this.bezier.equals(this.cos)) {
                this.cos = this.bezier;
                this.data.writeShort(-32510);
                this.data.writeInt(this.getColorNumber(this.bezier));
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private final void bezier() {
        try {
            if (!this.extractDouble) {
                this.data.writeShort(16641);
                this.data.writeShort(1);
                this.extractDouble = true;
            }
            if (!this.bezier.equals(this.black)) {
                this.cos = this.bezier;
                this.data.writeShort(-32510);
                this.data.writeInt(this.getColorNumber(this.bezier));
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final int getFontNumber(final Font font) {
        Integer n = this.flush.get(font);
        if (n == null) {
            try {
                if (this.extractToken == null) {
                    this.extractToken = new ByteArrayOutputStream();
                    this.firstToken = new DataOutputStream(this.extractToken);
                }
                this.firstToken.writeUTF(font.getName());
                this.firstToken.writeInt(font.getSize());
                int n2 = 1281;
                switch (font.getStyle()) {
                    case 0: {
                        n2 = 1281;
                        break;
                    }
                    case 1: {
                        n2 = 1282;
                        break;
                    }
                    case 2: {
                        n2 = 1283;
                        break;
                    }
                    case 3: {
                        n2 = 1284;
                        break;
                    }
                }
                this.firstToken.writeShort(n2);
                n = new Integer(this.flush.size());
                this.flush.put(font, n);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return n;
    }
    
    public final int getColorNumber(final Color color) {
        Integer n = this.getClipBounds.get(color);
        if (n == null) {
            try {
                if (this.get == null) {
                    this.get = new ByteArrayOutputStream();
                    this.getBlue = new DataOutputStream(this.get);
                }
                this.getBlue.writeByte(color.getRed());
                this.getBlue.writeByte(color.getGreen());
                this.getBlue.writeByte(color.getBlue());
                n = new Integer(this.getClipBounds.size());
                this.getClipBounds.put(color, n);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return n;
    }
    
    public final int getSize() {
        try {
            final int n = 0;
            this.getFontNumber.flush();
            int n2 = n + this.getFontMetrics.size();
            if (this.get != null) {
                this.getBlue.flush();
                n2 += this.get.size() + 2 + 8;
            }
            if (this.extractToken != null) {
                this.firstToken.flush();
                n2 += this.extractToken.size() + 2 + 8;
            }
            this.data.flush();
            return n2 + this.getColorNumber.size();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
    public final void saveTo(final DataOutput dataOutput) {
        dataOutput.write(this.getFontMetrics.toByteArray());
        if (this.get != null) {
            dataOutput.writeShort(-16383);
            dataOutput.writeInt(this.get.size() + 4);
            dataOutput.writeInt(this.getClipBounds.size());
            dataOutput.write(this.get.toByteArray());
        }
        if (this.extractToken != null) {
            dataOutput.writeShort(-16382);
            dataOutput.writeInt(this.extractToken.size() + 4);
            dataOutput.writeInt(this.flush.size());
            dataOutput.write(this.extractToken.toByteArray());
        }
        dataOutput.write(this.getColorNumber.toByteArray());
    }
    
    public final void drawString(final AttributedCharacterIterator attributedCharacterIterator, final int n, final int n2) {
    }
    
    public final void fill(final GeneralPath generalPath, final boolean b) {
        final PathIterator pathIterator = generalPath.getPathIterator(new AffineTransform());
        final double[] array = new double[6];
        if (b) {
            this.bezier();
        }
        else {
            this.abs();
        }
        while (!pathIterator.isDone()) {
            final int currentSegment = pathIterator.currentSegment(array);
            if (currentSegment == 0) {
                this.move(array[0], array[1]);
            }
            if (currentSegment == 1) {
                this.line(array[0], array[1]);
            }
            pathIterator.next();
        }
        this.endPath();
    }
}
