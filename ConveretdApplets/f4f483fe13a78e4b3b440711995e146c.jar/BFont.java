import java.util.Hashtable;
import java.util.Properties;
import java.io.IOException;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class BFont implements BConstants
{
    static final int VLW = 0;
    static final int RAW = 1;
    BGraphics parent;
    boolean valid;
    int kind;
    int firstChar;
    int charCount;
    BImage[] images;
    int cwidth;
    int cheight;
    int left;
    int baseline;
    int leadspace;
    int mboxY;
    int[] height;
    int[] width;
    int[] setWidth;
    int[] topExtent;
    int[] leftExtent;
    float size;
    float defaultLeading;
    float leading;
    boolean cached;
    private char[] c;
    
    private void load_vlw_font(final String s) throws IOException {
        final DataInputStream dataInputStream = new DataInputStream(this.parent.loadStream(s));
        this.firstChar = 33;
        this.charCount = dataInputStream.readInt();
        dataInputStream.readInt();
        dataInputStream.readInt();
        this.mboxY = dataInputStream.readInt();
        dataInputStream.readInt();
        dataInputStream.readInt();
        final int[] array = new int[this.charCount];
        this.height = new int[this.charCount];
        this.width = new int[this.charCount];
        this.setWidth = new int[this.charCount];
        this.topExtent = new int[this.charCount];
        this.leftExtent = new int[this.charCount];
        for (int i = 0; i < this.charCount; ++i) {
            array[i] = dataInputStream.readInt();
            this.height[i] = dataInputStream.readInt();
            this.width[i] = dataInputStream.readInt();
            this.setWidth[i] = dataInputStream.readInt();
            this.topExtent[i] = dataInputStream.readInt();
            this.leftExtent[i] = dataInputStream.readInt();
            dataInputStream.readInt();
        }
        this.images = new BImage[this.charCount];
        for (int j = 0; j < this.charCount; ++j) {
            this.images[j] = new BImage(new int[16384], 64, 64, 4);
            final byte[] array2 = new byte[this.height[j] * this.width[j]];
            dataInputStream.readFully(array2);
            final int n = this.width[j];
            final int n2 = this.height[j];
            for (int k = 0; k < n; ++k) {
                for (int l = 0; l < n2; ++l) {
                    this.images[j].pixels[l * 64 + k] = (array2[l * n + k] & 0xFF);
                }
            }
        }
        this.defaultLeading = this.mboxY / 64.0f * 1.2f;
        this.kind = 0;
    }
    
    private void load_fbf_font(final String s) throws IOException {
        final Properties properties = new Properties();
        properties.load(this.parent.loadStream(s));
        final String s2 = ((Hashtable<K, String>)properties).get("filename");
        final int int1 = Integer.parseInt(((Hashtable<K, String>)properties).get("image_width"));
        final int int2 = Integer.parseInt(((Hashtable<K, String>)properties).get("image_height"));
        final int int3 = Integer.parseInt(((Hashtable<K, String>)properties).get("columns"));
        final int int4 = Integer.parseInt(((Hashtable<K, String>)properties).get("rows"));
        this.left = Integer.parseInt(((Hashtable<K, String>)properties).get("left"));
        this.baseline = Integer.parseInt(((Hashtable<K, String>)properties).get("baseline"));
        this.leadspace = Integer.parseInt(((Hashtable<K, String>)properties).get("leading"));
        this.firstChar = Integer.parseInt(((Hashtable<K, String>)properties).get("first_char"));
        this.charCount = Integer.parseInt(((Hashtable<K, String>)properties).get("char_count"));
        final DataInputStream dataInputStream = new DataInputStream(this.parent.loadStream(s2));
        final byte[] array = new byte[int1 * int2];
        dataInputStream.readFully(array);
        dataInputStream.close();
        this.cwidth = int1 / int3;
        this.cheight = int2 / int4;
        this.images = new BImage[this.charCount];
        int n = 0;
        for (int i = 0; i < int4; ++i) {
            int[] array2;
            for (int n2 = 0; n2 < int3 && n != this.charCount; this.images[n++] = new BImage(array2, this.cwidth, this.cheight, 4), ++n2) {
                array2 = new int[this.cwidth * this.cheight];
                int n3 = 0;
                for (int j = 0; j < this.cheight; ++j) {
                    for (int k = 0; k < this.cwidth; ++k) {
                        array2[n3++] = (255 - array[(i * this.cheight + j) * int1 + n2 * this.cwidth + k] & 0xFF);
                    }
                }
            }
        }
        this.defaultLeading = this.cheight + this.leadspace;
        this.kind = 1;
    }
    
    boolean charExists(final char c) {
        return c >= this.firstChar && c - this.firstChar < this.charCount;
    }
    
    public float charWidth(final char c) {
        if (c == ' ') {
            return this.charWidth('i');
        }
        if (!this.charExists(c)) {
            return 0.0f;
        }
        if (this.kind == 0) {
            return this.setWidth[c - '!'] / 64.0f * this.size;
        }
        return this.cwidth;
    }
    
    float kernWidth(final char c, final char c2) {
        return 0.0f;
    }
    
    public void resetSize() {
        if (this.kind == 0) {
            this.size = 12.0f;
        }
        this.resetLeading();
    }
    
    public void setSize(final float size) {
        if (this.kind == 0) {
            this.size = size;
            this.resetLeading();
        }
        else {
            System.err.println("BFont: can't set size of fixed-width bitmap fonts");
        }
    }
    
    public void resetLeading() {
        if (this.kind == 0) {
            this.leading = this.defaultLeading * this.size;
        }
        else {
            this.leading = this.defaultLeading;
        }
    }
    
    public void setLeading(final float leading) {
        this.leading = leading;
    }
    
    public void drawChar(final char c, final float n, final float n2) {
        if (!this.valid) {
            return;
        }
        if (!this.charExists(c)) {
            return;
        }
        if (!this.cached) {
            this.parent.cache(this.images);
            this.cached = true;
        }
        if (this.kind == 0) {
            this.parent.image(this.images[c - '!'], n + this.leftExtent[c - '!'] / 64.0f * this.size, n2 - this.topExtent[c - '!'] / 64.0f * this.size, this.width[c - '!'] / 64.0f * this.size, this.height[c - '!'] / 64.0f * this.size, 0.0f, 0.0f, this.width[c - '!'], this.height[c - '!']);
        }
        else {
            this.parent.simage(this.images[c - this.firstChar], (int)this.parent.screenX(n - this.left, n2 - this.baseline, 0.0f), (int)this.parent.screenY(n - this.left, n2 - this.baseline, 0.0f));
        }
    }
    
    public float stringWidth(final String s) {
        if (!this.valid) {
            return 0.0f;
        }
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
                n += this.charWidth(charArray[i]);
                if (c != '\0') {
                    n += this.kernWidth(c, charArray[i]);
                }
                c = charArray[i];
            }
        }
        return (n2 > n) ? n2 : n;
    }
    
    public void drawString(final String s, float n, float n2) {
        if (!this.valid) {
            return;
        }
        this.parent.push();
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
                this.drawChar(this.c[i], n, n2);
                n += this.charWidth(this.c[i]);
                if (c != '\0') {
                    n += this.kernWidth(c, this.c[i]);
                }
                c = this.c[i];
            }
            ++i;
        }
        this.parent.pop();
    }
    
    public BFont() {
        this.c = new char[8192];
    }
    
    public BFont(final String s, final BGraphics parent) {
        this.c = new char[8192];
        this.parent = parent;
        this.valid = false;
        try {
            final String lowerCase = s.toLowerCase();
            if (lowerCase.endsWith(".vlw") || lowerCase.endsWith(".vlw.gz")) {
                this.load_vlw_font(s);
            }
            else {
                if (!lowerCase.endsWith(".fbf")) {
                    throw new IOException("don't know what type of file that is");
                }
                this.load_fbf_font(s);
            }
            this.cached = false;
            this.resetSize();
            this.valid = true;
        }
        catch (IOException ex) {
            parent.message(1, "could not load font " + s, ex);
        }
    }
}
