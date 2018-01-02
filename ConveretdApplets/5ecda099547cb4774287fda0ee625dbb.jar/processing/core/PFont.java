// 
// Decompiled by Procyon v0.5.30
// 

package processing.core;

import java.awt.GraphicsEnvironment;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.InputStream;
import java.awt.Font;

public class PFont implements PConstants
{
    public int charCount;
    public PImage[] images;
    protected Font font;
    protected boolean fontSearched;
    public String name;
    public String psname;
    public int size;
    public boolean smooth;
    public int mbox2;
    protected float fwidth;
    protected float fheight;
    public int twidth;
    public int theight;
    public int[] value;
    public int[] height;
    public int[] width;
    public int[] setWidth;
    public int[] topExtent;
    public int[] leftExtent;
    public int ascent;
    public int descent;
    protected int[] ascii;
    protected static Font[] fonts;
    static final char[] EXTRA_CHARS;
    public static char[] DEFAULT_CHARSET;
    
    public PFont() {
    }
    
    public PFont(final InputStream inputStream) throws IOException {
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.charCount = dataInputStream.readInt();
        final int int1 = dataInputStream.readInt();
        this.size = dataInputStream.readInt();
        this.mbox2 = dataInputStream.readInt();
        this.fwidth = this.size;
        this.fheight = this.size;
        this.mbox2 = (int)Math.pow(2.0, Math.ceil(Math.log(this.mbox2) / Math.log(2.0)));
        final int mbox2 = this.mbox2;
        this.theight = mbox2;
        this.twidth = mbox2;
        this.ascent = dataInputStream.readInt();
        this.descent = dataInputStream.readInt();
        this.value = new int[this.charCount];
        this.height = new int[this.charCount];
        this.width = new int[this.charCount];
        this.setWidth = new int[this.charCount];
        this.topExtent = new int[this.charCount];
        this.leftExtent = new int[this.charCount];
        this.ascii = new int[128];
        for (int i = 0; i < 128; ++i) {
            this.ascii[i] = -1;
        }
        for (int j = 0; j < this.charCount; ++j) {
            this.value[j] = dataInputStream.readInt();
            this.height[j] = dataInputStream.readInt();
            this.width[j] = dataInputStream.readInt();
            this.setWidth[j] = dataInputStream.readInt();
            this.topExtent[j] = dataInputStream.readInt();
            this.leftExtent[j] = dataInputStream.readInt();
            dataInputStream.readInt();
            if (this.value[j] < 128) {
                this.ascii[this.value[j]] = j;
            }
            if (this.value[j] == 100 && this.ascent == 0) {
                this.ascent = this.topExtent[j];
            }
            if (this.value[j] == 112 && this.descent == 0) {
                this.descent = -this.topExtent[j] + this.height[j];
            }
        }
        if (this.ascent == 0 && this.descent == 0) {
            throw new RuntimeException("Please use \"Create Font\" to re-create this font.");
        }
        this.images = new PImage[this.charCount];
        for (int k = 0; k < this.charCount; ++k) {
            this.images[k] = new PImage(this.twidth, this.theight, 4);
            final byte[] array = new byte[this.height[k] * this.width[k]];
            dataInputStream.readFully(array);
            final int n = this.width[k];
            for (int n2 = this.height[k], l = 0; l < n2; ++l) {
                for (int n3 = 0; n3 < n; ++n3) {
                    this.images[k].pixels[l * this.twidth + n3] = (array[l * n + n3] & 0xFF);
                }
            }
        }
        if (int1 >= 10) {
            this.name = dataInputStream.readUTF();
            this.psname = dataInputStream.readUTF();
        }
        if (int1 == 11) {
            this.smooth = dataInputStream.readBoolean();
        }
    }
    
    public void setFont(final Font font) {
        this.font = font;
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public Font findFont() {
        if (this.font == null && !this.fontSearched) {
            this.font = new Font(this.name, 0, this.size);
            if (!this.font.getPSName().equals(this.psname)) {
                this.font = new Font(this.psname, 0, this.size);
            }
            if (!this.font.getPSName().equals(this.psname)) {
                this.font = null;
            }
            this.fontSearched = true;
        }
        return this.font;
    }
    
    public void save(final OutputStream outputStream) throws IOException {
        final DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(this.charCount);
        if (this.name == null || this.psname == null) {
            this.name = "";
            this.psname = "";
        }
        dataOutputStream.writeInt(11);
        dataOutputStream.writeInt(this.size);
        dataOutputStream.writeInt(this.mbox2);
        dataOutputStream.writeInt(this.ascent);
        dataOutputStream.writeInt(this.descent);
        for (int i = 0; i < this.charCount; ++i) {
            dataOutputStream.writeInt(this.value[i]);
            dataOutputStream.writeInt(this.height[i]);
            dataOutputStream.writeInt(this.width[i]);
            dataOutputStream.writeInt(this.setWidth[i]);
            dataOutputStream.writeInt(this.topExtent[i]);
            dataOutputStream.writeInt(this.leftExtent[i]);
            dataOutputStream.writeInt(0);
        }
        for (int j = 0; j < this.charCount; ++j) {
            for (int k = 0; k < this.height[j]; ++k) {
                for (int l = 0; l < this.width[j]; ++l) {
                    dataOutputStream.write(this.images[j].pixels[k * this.mbox2 + l] & 0xFF);
                }
            }
        }
        dataOutputStream.writeUTF(this.name);
        dataOutputStream.writeUTF(this.psname);
        dataOutputStream.writeBoolean(this.smooth);
        dataOutputStream.flush();
    }
    
    public int index(final char c) {
        if (this.charCount == 0) {
            return -1;
        }
        if (c < '\u0080') {
            return this.ascii[c];
        }
        return this.indexHunt(c, 0, this.charCount - 1);
    }
    
    protected int indexHunt(final int n, final int n2, final int n3) {
        final int n4 = (n2 + n3) / 2;
        if (n == this.value[n4]) {
            return n4;
        }
        if (n2 >= n3) {
            return -1;
        }
        if (n < this.value[n4]) {
            return this.indexHunt(n, n2, n4 - 1);
        }
        return this.indexHunt(n, n4 + 1, n3);
    }
    
    public float kern(final char c, final char c2) {
        return 0.0f;
    }
    
    public float ascent() {
        return this.ascent / this.fheight;
    }
    
    public float descent() {
        return this.descent / this.fheight;
    }
    
    public float width(final char c) {
        if (c == ' ') {
            return this.width('i');
        }
        final int index = this.index(c);
        if (index == -1) {
            return 0.0f;
        }
        return this.setWidth[index] / this.fwidth;
    }
    
    public PFont(final Font font, final boolean smooth, final char[] array) {
        this.font = font;
        this.smooth = smooth;
        this.name = font.getName();
        this.psname = font.getPSName();
        if (array != null) {
            Arrays.sort(array);
        }
        this.charCount = ((array == null) ? 65536 : array.length);
        this.size = font.getSize();
        final float n = this.size;
        this.fheight = n;
        this.fwidth = n;
        final PImage[] array2 = new PImage[this.charCount];
        this.value = new int[this.charCount];
        this.height = new int[this.charCount];
        this.width = new int[this.charCount];
        this.setWidth = new int[this.charCount];
        this.topExtent = new int[this.charCount];
        this.leftExtent = new int[this.charCount];
        this.ascii = new int[128];
        for (int i = 0; i < 128; ++i) {
            this.ascii[i] = -1;
        }
        final int n2 = this.size * 3;
        final BufferedImage bufferedImage = new BufferedImage(n2, n2, 1);
        final Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, smooth ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        graphics2D.setFont(font);
        final FontMetrics fontMetrics = graphics2D.getFontMetrics();
        final int[] array3 = new int[n2 * n2];
        int n3 = 0;
        int charCount = 0;
        for (int j = 0; j < this.charCount; ++j) {
            final char c = (array == null) ? ((char)j) : array[j];
            if (font.canDisplay(c)) {
                graphics2D.setColor(Color.white);
                graphics2D.fillRect(0, 0, n2, n2);
                graphics2D.setColor(Color.black);
                graphics2D.drawString(String.valueOf(c), this.size, this.size * 2);
                bufferedImage.getData().getSamples(0, 0, n2, n2, 0, array3);
                int n4 = 1000;
                int n5 = 0;
                int n6 = 1000;
                int n7 = 0;
                boolean b = false;
                for (int k = 0; k < n2; ++k) {
                    for (int l = 0; l < n2; ++l) {
                        if ((array3[k * n2 + l] & 0xFF) != 0xFF) {
                            if (l < n4) {
                                n4 = l;
                            }
                            if (k < n6) {
                                n6 = k;
                            }
                            if (l > n5) {
                                n5 = l;
                            }
                            if (k > n7) {
                                n7 = k;
                            }
                            b = true;
                        }
                    }
                }
                if (!b) {
                    n6 = (n4 = 0);
                    n7 = (n5 = 0);
                }
                this.value[charCount] = c;
                this.height[charCount] = n7 - n6 + 1;
                this.width[charCount] = n5 - n4 + 1;
                this.setWidth[charCount] = fontMetrics.charWidth(c);
                if (c < '\u0080') {
                    this.ascii[c] = charCount;
                }
                this.topExtent[charCount] = this.size * 2 - n6;
                this.leftExtent[charCount] = n4 - this.size;
                if (c == 'd') {
                    this.ascent = this.topExtent[charCount];
                }
                if (c == 'p') {
                    this.descent = -this.topExtent[charCount] + this.height[charCount];
                }
                if (this.width[charCount] > n3) {
                    n3 = this.width[charCount];
                }
                if (this.height[charCount] > n3) {
                    n3 = this.height[charCount];
                }
                array2[charCount] = new PImage(this.width[charCount], this.height[charCount], 4);
                for (int n8 = n6; n8 <= n7; ++n8) {
                    for (int n9 = n4; n9 <= n5; ++n9) {
                        array2[charCount].pixels[(n8 - n6) * this.width[charCount] + (n9 - n4)] = 255 - (array3[n8 * n2 + n9] & 0xFF);
                    }
                }
                ++charCount;
            }
        }
        this.charCount = charCount;
        if (this.ascent == 0 && this.descent == 0) {
            for (int n10 = 0; n10 < this.charCount; ++n10) {
                final char c2 = (char)this.value[n10];
                if (!Character.isWhitespace(c2) && c2 != ' ' && c2 != '\u2007') {
                    if (c2 != '\u202f') {
                        if (this.topExtent[n10] > this.ascent) {
                            this.ascent = this.topExtent[n10];
                        }
                        final int descent = -this.topExtent[n10] + this.height[n10];
                        if (descent > this.descent) {
                            this.descent = descent;
                        }
                    }
                }
            }
        }
        this.mbox2 = (int)Math.pow(2.0, Math.ceil(Math.log(n3) / Math.log(2.0)));
        final int mbox2 = this.mbox2;
        this.theight = mbox2;
        this.twidth = mbox2;
        this.images = new PImage[this.charCount];
        for (int n11 = 0; n11 < this.charCount; ++n11) {
            this.images[n11] = new PImage(this.mbox2, this.mbox2, 4);
            for (int n12 = 0; n12 < this.height[n11]; ++n12) {
                System.arraycopy(array2[n11].pixels, n12 * this.width[n11], this.images[n11].pixels, n12 * this.mbox2, this.width[n11]);
            }
            array2[n11] = null;
        }
    }
    
    public static String[] list() {
        loadFonts();
        final String[] array = new String[PFont.fonts.length];
        for (int i = 0; i < array.length; ++i) {
            array[i] = PFont.fonts[i].getName();
        }
        return array;
    }
    
    public static void loadFonts() {
        if (PFont.fonts == null) {
            PFont.fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        }
    }
    
    public static Font findFont(final String s) {
        loadFonts();
        if (PApplet.platform == 2) {
            for (int i = 0; i < PFont.fonts.length; ++i) {
                if (s.equals(PFont.fonts[i].getName())) {
                    return PFont.fonts[i];
                }
            }
        }
        return new Font(s, 0, 1);
    }
    
    static {
        EXTRA_CHARS = new char[] { '\u0080', '\u0081', '\u0082', '\u0083', '\u0084', '\u0085', '\u0086', '\u0087', '\u0088', '\u0089', '\u008a', '\u008b', '\u008c', '\u008d', '\u008e', '\u008f', '\u0090', '\u0091', '\u0092', '\u0093', '\u0094', '\u0095', '\u0096', '\u0097', '\u0098', '\u0099', '\u009a', '\u009b', '\u009c', '\u009d', '\u009e', '\u009f', ' ', '¡', '¢', '£', '¤', '¥', '¦', '§', '¨', '©', 'ª', '«', '¬', '\u00ad', '®', '¯', '°', '±', '´', 'µ', '¶', '·', '¸', 'º', '»', '¿', '\u00c0', '\u00c1', '\u00c2', '\u00c3', '\u00c4', '\u00c5', '\u00c6', '\u00c7', '\u00c8', '\u00c9', '\u00ca', '\u00cb', '\u00cc', '\u00cd', '\u00ce', '\u00cf', '\u00d1', '\u00d2', '\u00d3', '\u00d4', '\u00d5', '\u00d6', '\u00d7', '\u00d8', '\u00d9', '\u00da', '\u00db', '\u00dc', '\u00dd', '\u00df', '\u00e0', '\u00e1', '\u00e2', '\u00e3', '\u00e4', '\u00e5', '\u00e6', '\u00e7', '\u00e8', '\u00e9', '\u00ea', '\u00eb', '\u00ec', '\u00ed', '\u00ee', '\u00ef', '\u00f1', '\u00f2', '\u00f3', '\u00f4', '\u00f5', '\u00f6', '\u00f7', '\u00f8', '\u00f9', '\u00fa', '\u00fb', '\u00fc', '\u00fd', '\u00ff', '\u0102', '\u0103', '\u0104', '\u0105', '\u0106', '\u0107', '\u010c', '\u010d', '\u010e', '\u010f', '\u0110', '\u0111', '\u0118', '\u0119', '\u011a', '\u011b', '\u0131', '\u0139', '\u013a', '\u013d', '\u013e', '\u0141', '\u0142', '\u0143', '\u0144', '\u0147', '\u0148', '\u0150', '\u0151', '\u0152', '\u0153', '\u0154', '\u0155', '\u0158', '\u0159', '\u015a', '\u015b', '\u015e', '\u015f', '\u0160', '\u0161', '\u0162', '\u0163', '\u0164', '\u0165', '\u016e', '\u016f', '\u0170', '\u0171', '\u0178', '\u0179', '\u017a', '\u017b', '\u017c', '\u017d', '\u017e', '\u0192', '\u02c6', '\u02c7', '\u02d8', '\u02d9', '\u02da', '\u02db', '\u02dc', '\u02dd', '\u03a9', '\u03c0', '\u2013', '\u2014', '\u2018', '\u2019', '\u201a', '\u201c', '\u201d', '\u201e', '\u2020', '\u2021', '\u2022', '\u2026', '\u2030', '\u2039', '\u203a', '\u2044', '\u20ac', '\u2122', '\u2202', '\u2206', '\u220f', '\u2211', '\u221a', '\u221e', '\u222b', '\u2248', '\u2260', '\u2264', '\u2265', '\u25ca', '\uf8ff', '\ufb01', '\ufb02' };
        PFont.DEFAULT_CHARSET = new char[94 + PFont.EXTRA_CHARS.length];
        int n = 0;
        for (int i = 33; i <= 126; ++i) {
            PFont.DEFAULT_CHARSET[n++] = (char)i;
        }
        for (int j = 0; j < PFont.EXTRA_CHARS.length; ++j) {
            PFont.DEFAULT_CHARSET[n++] = PFont.EXTRA_CHARS[j];
        }
    }
}
