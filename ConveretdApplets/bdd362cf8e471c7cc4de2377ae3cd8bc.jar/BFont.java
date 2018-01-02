import java.io.DataInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.OutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class BFont implements BConstants
{
    static final char[] EXTRA_CHARS;
    static char[] charset;
    int charCount;
    BImage[] images;
    int iwidth;
    int iheight;
    float iwidthf;
    float iheightf;
    int mbox;
    int[] value;
    int[] height;
    int[] width;
    int[] setWidth;
    int[] topExtent;
    int[] leftExtent;
    float size;
    float leading;
    boolean cached;
    private char[] c;
    
    public void write(final OutputStream outputStream) throws IOException {
        final DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(this.charCount);
        dataOutputStream.writeInt(8);
        dataOutputStream.writeInt(this.mbox);
        dataOutputStream.writeInt(this.mbox);
        dataOutputStream.writeInt(0);
        dataOutputStream.writeInt(0);
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
                    dataOutputStream.write(this.images[j].pixels[k * this.width[j] + l] & 0xFF);
                }
            }
        }
        dataOutputStream.flush();
        dataOutputStream.close();
    }
    
    public void read(final InputStream inputStream) throws IOException {
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.charCount = dataInputStream.readInt();
        dataInputStream.readInt();
        final int int1 = dataInputStream.readInt();
        final int int2 = dataInputStream.readInt();
        this.mbox = int2;
        this.iwidth = (int)Math.pow(2, Math.ceil(Math.log(int1) / Math.log(2)));
        this.iheight = (int)Math.pow(2, Math.ceil(Math.log(int2) / Math.log(2)));
        this.iwidthf = this.iwidth;
        this.iheightf = this.iheight;
        dataInputStream.readInt();
        dataInputStream.readInt();
        this.value = new int[this.charCount];
        this.height = new int[this.charCount];
        this.width = new int[this.charCount];
        this.setWidth = new int[this.charCount];
        this.topExtent = new int[this.charCount];
        this.leftExtent = new int[this.charCount];
        for (int i = 0; i < this.charCount; ++i) {
            this.value[i] = dataInputStream.readInt();
            this.height[i] = dataInputStream.readInt();
            this.width[i] = dataInputStream.readInt();
            this.setWidth[i] = dataInputStream.readInt();
            this.topExtent[i] = dataInputStream.readInt();
            this.leftExtent[i] = dataInputStream.readInt();
            dataInputStream.readInt();
        }
        this.images = new BImage[this.charCount];
        for (int j = 0; j < this.charCount; ++j) {
            this.images[j] = new BImage(new int[this.iwidth * this.iheight], this.iwidth, this.iheight, 4);
            final byte[] array = new byte[this.height[j] * this.width[j]];
            dataInputStream.readFully(array);
            final int n = this.width[j];
            final int n2 = this.height[j];
            for (int k = 0; k < n; ++k) {
                for (int l = 0; l < n2; ++l) {
                    this.images[j].pixels[l * this.iwidth + k] = (array[l * n + k] & 0xFF);
                }
            }
        }
    }
    
    private static final int index(final char c) {
        if (c >= '!' && c <= '~') {
            return c - '!';
        }
        return index_hunt(c, 0, BFont.charset.length - 1);
    }
    
    private static final int index_hunt(final int n, final int n2, final int n3) {
        final int n4 = (n2 + n3) / 2;
        if (n == BFont.charset[n4]) {
            return n4;
        }
        if (n2 == n3) {
            return -1;
        }
        if (n < BFont.charset[n4]) {
            return index_hunt(n, n2, n4 - 1);
        }
        return index_hunt(n, n4 + 1, n3);
    }
    
    float kern(final char c, final char c2) {
        return 0.0f;
    }
    
    public void size() {
        this.size = 12.0f;
    }
    
    public void size(final float size) {
        this.size = size;
    }
    
    public void leading() {
        this.leading = this.size * (this.mbox / this.iheightf) * 1.2f;
    }
    
    public void leading(final float leading) {
        this.leading = leading;
    }
    
    public float width(final char c) {
        if (c == ' ') {
            return this.width('i');
        }
        final int index = index(c);
        if (index == -1) {
            return 0.0f;
        }
        return this.setWidth[index] / this.iwidthf * this.size;
    }
    
    public float width(final String s) {
        float n = 0.0f;
        float n2 = 0.0f;
        char c = '\0';
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            if (charArray[i] == '\n') {
                if (n > n2) {
                    n2 = n;
                }
                n = 0.0f;
                c = '\0';
            }
            else {
                n += this.width(charArray[i]);
                if (c != '\0') {
                    n += this.kern(c, charArray[i]);
                }
                c = charArray[i];
            }
        }
        return (n2 > n) ? n2 : n;
    }
    
    public void text(final char c, final float n, final float n2, final BGraphics bGraphics) {
        final int index = index(c);
        if (index == -1) {
            return;
        }
        if (!this.cached) {
            bGraphics.cache(this.images);
            this.cached = true;
        }
        if (bGraphics.text_space == 3) {
            final float n3 = this.height[index] / this.iheightf;
            final float n4 = this.width[index] / this.iwidthf;
            final float n5 = this.leftExtent[index] / this.iwidthf;
            final float n6 = this.topExtent[index] / this.iheightf;
            final int texture_mode = bGraphics.texture_mode;
            final boolean stroke = bGraphics._stroke;
            bGraphics.texture_mode = 1;
            bGraphics.drawing_text = true;
            bGraphics._stroke = false;
            final float n7 = n + n5 * this.size;
            final float n8 = n2 - n6 * this.size;
            final float n9 = n7 + n4 * this.size;
            final float n10 = n8 + n3 * this.size;
            bGraphics.beginShape(128);
            bGraphics.texture(this.images[index]);
            bGraphics.vertex(n7, n8, 0.0f, 0.0f);
            bGraphics.vertex(n7, n10, 0.0f, this.height[index]);
            bGraphics.vertex(n9, n10, this.width[index], this.height[index]);
            bGraphics.vertex(n9, n8, this.width[index], 0.0f);
            bGraphics.endShape();
            bGraphics.texture_mode = texture_mode;
            bGraphics.drawing_text = false;
            bGraphics._stroke = stroke;
        }
        else {
            int n11 = (int)n + this.leftExtent[index];
            int n12 = (int)n2 - this.topExtent[index];
            int n13 = 0;
            int n14 = 0;
            int n15 = this.width[index];
            int n16 = this.height[index];
            if (n11 >= bGraphics.width || n12 >= bGraphics.height || n11 + n15 < 0 || n12 + n16 < 0) {
                return;
            }
            if (n11 < 0) {
                n13 -= n11;
                n15 += n11;
                n11 = 0;
            }
            if (n12 < 0) {
                n14 -= n12;
                n16 += n12;
                n12 = 0;
            }
            if (n11 + n15 > bGraphics.width) {
                n15 -= n11 + n15 - bGraphics.width;
            }
            if (n12 + n16 > bGraphics.height) {
                n16 -= n12 + n16 - bGraphics.height;
            }
            final int fillRi = bGraphics.fillRi;
            final int fillGi = bGraphics.fillGi;
            final int fillBi = bGraphics.fillBi;
            final int fillAi = bGraphics.fillAi;
            final int[] pixels = this.images[index].pixels;
            final int[] pixels2 = bGraphics.pixels;
            for (int i = n14; i < n14 + n16; ++i) {
                for (int j = n13; j < n13 + n15; ++j) {
                    final int n17 = fillAi * pixels[i * this.iwidth + j] >> 8;
                    final int n18 = n17 ^ 0xFF;
                    final int n19 = pixels[i * this.width[index] + j];
                    final int n20 = pixels2[(n12 + i - n14) * bGraphics.width + (n11 + j - n13)];
                    pixels2[(n12 + i - n14) * bGraphics.width + n11 + j - n13] = (0xFF000000 | (n17 * fillRi + n18 * (n20 >> 16 & 0xFF) & 0xFF00) << 8 | (n17 * fillGi + n18 * (n20 >> 8 & 0xFF) & 0xFF00) | n17 * fillBi + n18 * (n20 & 0xFF) >> 8);
                }
            }
        }
    }
    
    public void text(final String s, float n, float n2, final BGraphics bGraphics) {
        final float n3 = n;
        int i = 0;
        char c = '\0';
        final int length = s.length();
        if (length > this.c.length) {
            this.c = new char[length + 10];
        }
        s.getChars(0, length, this.c, 0);
        while (i < length) {
            if (this.c[i] == '\n') {
                n = n3;
                n2 += this.leading;
                c = '\0';
            }
            else {
                this.text(this.c[i], n, n2, bGraphics);
                n += this.width(this.c[i]);
                if (c != '\0') {
                    n += this.kern(c, this.c[i]);
                }
                c = this.c[i];
            }
            ++i;
        }
    }
    
    private final /* synthetic */ void this() {
        this.c = new char[8192];
    }
    
    public BFont() {
        this.this();
    }
    
    public BFont(final String s, final BGraphics bGraphics) throws IOException {
        this.this();
        final String lowerCase = s.toLowerCase();
        if (lowerCase.endsWith(".vlw") || lowerCase.endsWith(".vlw.gz")) {
            this.read(bGraphics.loadStream(s));
            this.cached = false;
            this.size();
            return;
        }
        throw new IOException("don't know what type of file that is");
    }
    
    static {
        EXTRA_CHARS = new char[] { '\u0080', '\u0081', '\u0082', '\u0083', '\u0084', '\u0085', '\u0086', '\u0087', '\u0088', '\u0089', '\u008a', '\u008b', '\u008c', '\u008d', '\u008e', '\u008f', '\u0090', '\u0091', '\u0092', '\u0093', '\u0094', '\u0095', '\u0096', '\u0097', '\u0098', '\u0099', '\u009a', '\u009b', '\u009c', '\u009d', '\u009e', '\u009f', ' ', '¡', '¢', '£', '¤', '¥', '¦', '§', '¨', '©', 'ª', '«', '¬', '\u00ad', '®', '¯', '°', '±', '´', 'µ', '¶', '·', '¸', 'º', '»', '¿', '\u00c0', '\u00c1', '\u00c2', '\u00c3', '\u00c4', '\u00c5', '\u00c6', '\u00c7', '\u00c8', '\u00c9', '\u00ca', '\u00cb', '\u00cc', '\u00cd', '\u00ce', '\u00cf', '\u00d1', '\u00d2', '\u00d3', '\u00d4', '\u00d5', '\u00d6', '\u00d7', '\u00d8', '\u00d9', '\u00da', '\u00db', '\u00dc', '\u00dd', '\u00df', '\u00e0', '\u00e1', '\u00e2', '\u00e3', '\u00e4', '\u00e5', '\u00e6', '\u00e7', '\u00e8', '\u00e9', '\u00ea', '\u00eb', '\u00ec', '\u00ed', '\u00ee', '\u00ef', '\u00f1', '\u00f2', '\u00f3', '\u00f4', '\u00f5', '\u00f6', '\u00f7', '\u00f8', '\u00f9', '\u00fa', '\u00fb', '\u00fc', '\u00fd', '\u00ff', '\u0102', '\u0103', '\u0104', '\u0105', '\u0106', '\u0107', '\u010c', '\u010d', '\u010e', '\u010f', '\u0110', '\u0111', '\u0118', '\u0119', '\u011a', '\u011b', '\u0131', '\u0139', '\u013a', '\u013d', '\u013e', '\u0141', '\u0142', '\u0143', '\u0144', '\u0147', '\u0148', '\u0150', '\u0151', '\u0152', '\u0153', '\u0154', '\u0155', '\u0158', '\u0159', '\u015a', '\u015b', '\u015e', '\u015f', '\u0160', '\u0161', '\u0162', '\u0163', '\u0164', '\u0165', '\u016e', '\u016f', '\u0170', '\u0171', '\u0178', '\u0179', '\u017a', '\u017b', '\u017c', '\u017d', '\u017e', '\u0192', '\u02c6', '\u02c7', '\u02d8', '\u02d9', '\u02da', '\u02db', '\u02dc', '\u02dd', '\u03a9', '\u03c0', '\u2013', '\u2014', '\u2018', '\u2019', '\u201a', '\u201c', '\u201d', '\u201e', '\u2020', '\u2021', '\u2022', '\u2026', '\u2030', '\u2039', '\u203a', '\u2044', '\u20ac', '\u2122', '\u2202', '\u2206', '\u220f', '\u2211', '\u221a', '\u221e', '\u222b', '\u2248', '\u2260', '\u2264', '\u2265', '\u25ca', '\uf8ff', '\ufb01', '\ufb02' };
        BFont.charset = new char[94 + BFont.EXTRA_CHARS.length];
        int n = 0;
        for (int i = 33; i <= 126; ++i) {
            BFont.charset[n++] = (char)i;
        }
        for (int j = 0; j < BFont.EXTRA_CHARS.length; ++j) {
            BFont.charset[n++] = BFont.EXTRA_CHARS[j];
        }
    }
}
