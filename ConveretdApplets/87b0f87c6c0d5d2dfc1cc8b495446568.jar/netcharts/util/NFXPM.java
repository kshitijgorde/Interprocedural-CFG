// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.io.PrintStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.awt.Graphics;
import java.awt.image.ColorModel;
import java.awt.Image;
import java.awt.Color;
import java.util.Vector;
import java.awt.image.ImageProducer;
import java.util.Hashtable;
import java.awt.image.ImageConsumer;

public class NFXPM implements ImageConsumer
{
    protected static final char transparentChar = ' ';
    protected static final char startChar = '!';
    protected static final char endChar = '~';
    protected Object encodedLock;
    protected boolean encoded;
    protected Object builtLock;
    protected boolean built;
    protected int[][] image;
    protected int charsPerPixel;
    protected int width;
    protected int height;
    protected Hashtable colors;
    protected Hashtable codes;
    protected char lastPlace;
    protected ImageProducer ip;
    protected Vector chunks;
    protected Color transparentColor;
    
    public NFXPM(final int[][] image) {
        this.encodedLock = new Object();
        this.encoded = false;
        this.builtLock = new Object();
        this.built = false;
        this.image = null;
        this.charsPerPixel = 1;
        this.width = 0;
        this.height = 0;
        this.colors = new Hashtable();
        this.codes = new Hashtable();
        this.lastPlace = '!';
        this.ip = null;
        this.chunks = new Vector();
        this.transparentColor = null;
        this.image = image;
        this.width = image.length;
        this.height = image[0].length;
        this.built = true;
    }
    
    public NFXPM(final Image image) {
        this.encodedLock = new Object();
        this.encoded = false;
        this.builtLock = new Object();
        this.built = false;
        this.image = null;
        this.charsPerPixel = 1;
        this.width = 0;
        this.height = 0;
        this.colors = new Hashtable();
        this.codes = new Hashtable();
        this.lastPlace = '!';
        this.ip = null;
        this.chunks = new Vector();
        this.transparentColor = null;
        if (image != null) {
            (this.ip = image.getSource()).startProduction(this);
        }
    }
    
    public int getWidth() {
        this.waitTillBuilt();
        return this.width;
    }
    
    public int getHeight() {
        this.waitTillBuilt();
        return this.height;
    }
    
    public void setDimensions(final int width, final int height) {
        this.width = width;
        this.height = height;
    }
    
    public void setProperties(final Hashtable hashtable) {
    }
    
    public void setColorModel(final ColorModel colorModel) {
    }
    
    public void setHints(final int n) {
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
        final int[][] array2 = new int[n3][n4];
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n3; ++j) {
                final int n7 = array[i * n6 + j + n5] & 0xFF;
                if (colorModel.getAlpha(n7) == 0) {
                    array2[j][i] = 0;
                }
                else {
                    array2[j][i] = colorModel.getRGB(n7);
                }
            }
        }
        this.chunks.addElement(new NFImageChunk(n, n2, n3, n4, array2));
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        final int[][] array2 = new int[n3][n4];
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n3; ++j) {
                final int n7 = array[i * n6 + j + n5];
                if (colorModel.getAlpha(n7) == 0) {
                    array2[j][i] = 0;
                }
                else {
                    array2[j][i] = colorModel.getRGB(n7);
                }
            }
        }
        this.chunks.addElement(new NFImageChunk(n, n2, n3, n4, array2));
    }
    
    public void imageComplete(final int n) {
        this.ip.removeConsumer(this);
        if (n != 1) {
            this.build();
        }
        else {
            synchronized (this.builtLock) {
                this.built = true;
                this.builtLock.notifyAll();
            }
        }
    }
    
    protected void waitTillBuilt() {
        synchronized (this.builtLock) {
            if (!this.built) {
                try {
                    this.builtLock.wait();
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.waitTillBuilt();
        for (int i = 0; i < this.image.length; ++i) {
            for (int j = 0; j < this.image[i].length; ++j) {
                if (this.image[i][j] >> 24 != 0) {
                    graphics.setColor(this.getColor(this.image[i][j]));
                    graphics.drawLine(i, j, i, j);
                }
            }
        }
    }
    
    protected void build() {
        this.image = new int[this.width][this.height];
        for (int i = 0; i < this.chunks.size(); ++i) {
            final NFImageChunk nfImageChunk = this.chunks.elementAt(i);
            final int x = nfImageChunk.getX();
            final int y = nfImageChunk.getY();
            final int width = nfImageChunk.getWidth();
            final int height = nfImageChunk.getHeight();
            final int[][] pixels = nfImageChunk.getPixels();
            for (int j = 0; j < height; ++j) {
                for (int k = 0; k < width; ++k) {
                    this.image[x + k][y + j] = pixels[k][j];
                }
            }
        }
        this.chunks.removeAllElements();
        synchronized (this.builtLock) {
            this.built = true;
            this.builtLock.notifyAll();
        }
    }
    
    protected void incrementColor(final Hashtable hashtable, final StringBuffer sb) {
        boolean b = false;
        for (int i = sb.length() - 1; i >= 0; --i) {
            final char char1 = sb.charAt(i);
            if (char1 != '~') {
                sb.setCharAt(i, (char)(char1 + '\u0001'));
                break;
            }
            sb.setCharAt(i, '!');
            if (i == 0) {
                sb.setCharAt(i, ++this.lastPlace);
                sb.append('!');
                b = true;
                break;
            }
        }
        if (b) {
            final Enumeration<Color> keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                final Color color = keys.nextElement();
                final StringBuffer sb2 = new StringBuffer(hashtable.get(color));
                while (sb2.length() != sb.length()) {
                    sb2.insert(0, '!');
                }
                hashtable.put(color, sb2.toString());
            }
            this.charsPerPixel = sb.length();
        }
    }
    
    protected Color getColor(final int n) {
        synchronized (this.colors) {
            final String value = String.valueOf(n);
            Color color;
            if ((color = this.colors.get(value)) == null) {
                color = new Color(n);
                this.colors.put(value, color);
            }
            return color;
        }
    }
    
    public void encodeAsIntArray(final OutputStream outputStream) {
        this.waitTillBuilt();
        final PrintStream printStream = new PrintStream(outputStream);
        printStream.println("int[][] image = {");
        for (int i = 0; i < this.image.length; ++i) {
            printStream.print("{");
            for (int j = 0; j < this.image[i].length; ++j) {
                if (j > 0) {
                    printStream.print(", ");
                }
                printStream.print(this.image[i][j]);
            }
            printStream.println("},");
        }
        printStream.println("};");
    }
    
    protected String escapeCode(String string) {
        if (string.indexOf(34) != -1) {
            final StringBuffer sb = new StringBuffer(string);
            for (int i = 0; i < sb.length(); ++i) {
                if (sb.charAt(i) == '\"') {
                    sb.insert(i, '\\');
                    ++i;
                }
            }
            string = sb.toString();
        }
        return string;
    }
    
    public void encode(final OutputStream outputStream) {
        this.encode(outputStream, false);
    }
    
    public void encode(final OutputStream outputStream, final boolean b) {
        this.waitTillBuilt();
        final PrintStream printStream = new PrintStream(outputStream);
        synchronized (this.encodedLock) {
            if (!this.encoded) {
                final StringBuffer sb = new StringBuffer("!");
                boolean b2 = false;
                for (int i = 0; i < this.image.length; ++i) {
                    for (int j = 0; j < this.image[i].length; ++j) {
                        if (this.image[i][j] >> 24 != 0) {
                            final Color color = this.getColor(this.image[i][j]);
                            if (this.codes.get(color) == null) {
                                this.codes.put(color, sb.toString());
                                this.incrementColor(this.codes, sb);
                            }
                        }
                        else {
                            b2 = true;
                        }
                    }
                }
                if (b2) {
                    Color transparentColor = new Color(255, 255, 255);
                    for (int rgb = transparentColor.getRGB(); this.codes.get(transparentColor) != null; transparentColor = new Color(--rgb)) {}
                    this.transparentColor = transparentColor;
                    final StringBuffer sb2 = new StringBuffer(" ");
                    while (sb2.length() != this.charsPerPixel) {
                        sb2.append(' ');
                    }
                    this.codes.put(this.transparentColor, sb2.toString());
                }
                this.encoded = true;
            }
        }
        printStream.println("/* XPM */");
        printStream.println("static char *image[] = {");
        printStream.println("/* width height num_colors chars_per_pixel */");
        printStream.println("\" " + this.width + " " + this.height + " " + this.codes.size() + " " + this.charsPerPixel + "\",");
        printStream.println("/* colors */");
        final Enumeration<Color> keys = this.codes.keys();
        while (keys.hasMoreElements()) {
            final Color color2 = keys.nextElement();
            final String s = this.codes.get(color2);
            String s2 = Integer.toHexString(color2.getRed());
            if (s2.length() == 1) {
                s2 = "0" + s2;
            }
            String s3 = Integer.toHexString(color2.getGreen());
            if (s3.length() == 1) {
                s3 = "0" + s3;
            }
            String s4 = Integer.toHexString(color2.getBlue());
            if (s4.length() == 1) {
                s4 = "0" + s4;
            }
            printStream.println("\"" + (b ? this.escapeCode(s) : s) + " c #" + s2 + s3 + s4 + "\",");
        }
        printStream.println("/* pixels */");
        for (int k = 0; k < this.image[0].length; ++k) {
            printStream.print("\"");
            for (int l = 0; l < this.image.length; ++l) {
                if (this.image[l][k] >> 24 != 0) {
                    final String s5 = this.codes.get(this.getColor(this.image[l][k]));
                    printStream.print(b ? this.escapeCode(s5) : s5);
                }
                else {
                    printStream.print(this.codes.get(this.transparentColor));
                }
            }
            printStream.println("\",");
        }
        printStream.println("};");
    }
}
