// 
// Decompiled by Procyon v0.5.30
// 

package processing.core;

import java.lang.reflect.Method;
import java.awt.FontMetrics;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.awt.Color;
import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.InputStream;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.OutputStream;

public class PFont implements PConstants
{
    static final char[] EXTRA_CHARS;
    public static char[] DEFAULT_CHARSET;
    public int charCount;
    public PImage[] images;
    public String name;
    public String psname;
    public int size;
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
    int[] ascii;
    protected char[] textBuffer;
    protected char[] widthBuffer;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$awt$Font;
    
    public void save(final OutputStream outputStream) throws IOException {
        final DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(this.charCount);
        dataOutputStream.writeInt((this.name != null) ? 10 : 8);
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
        if (this.name != null) {
            dataOutputStream.writeUTF(this.name);
            dataOutputStream.writeUTF(this.psname);
        }
        dataOutputStream.flush();
    }
    
    public int index(final char c) {
        if (this.value.length == 0) {
            return -1;
        }
        if (c < '\u0080') {
            return this.ascii[c];
        }
        return this.index_hunt(c, 0, this.value.length - 1);
    }
    
    protected int index_hunt(final int n, final int n2, final int n3) {
        final int n4 = (n2 + n3) / 2;
        if (n == this.value[n4]) {
            return n4;
        }
        if (n2 >= n3) {
            return -1;
        }
        if (n < this.value[n4]) {
            return this.index_hunt(n, n2, n4 - 1);
        }
        return this.index_hunt(n, n4 + 1, n3);
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
    
    public float width(final String s) {
        final int length = s.length();
        if (length > this.widthBuffer.length) {
            this.widthBuffer = new char[length + 10];
        }
        s.getChars(0, length, this.widthBuffer, 0);
        float n = 0.0f;
        int i = 0;
        int n2 = 0;
        while (i < length) {
            if (this.widthBuffer[i] == '\n') {
                n = Math.max(n, this.calcWidth(this.widthBuffer, n2, i));
                n2 = i + 1;
            }
            ++i;
        }
        if (n2 < length) {
            n = Math.max(n, this.calcWidth(this.widthBuffer, n2, i));
        }
        return n;
    }
    
    private final float calcWidth(final char[] array, final int n, final int n2) {
        float n3 = 0.0f;
        for (int i = n; i < n2; ++i) {
            n3 += this.width(array[i]);
        }
        return n3;
    }
    
    public void text(final char c, final float n, final float n2, final PGraphics pGraphics) {
        this.text(c, n, n2, 0.0f, pGraphics);
    }
    
    public void text(final char c, float n, final float n2, final float n3, final PGraphics pGraphics) {
        if (pGraphics.textAlign == 3) {
            n -= pGraphics.textSize * this.width(c) / 2.0f;
        }
        else if (pGraphics.textAlign == 39) {
            n -= pGraphics.textSize * this.width(c);
        }
        if (n3 != 0.0f) {
            pGraphics.translate(0.0f, 0.0f, n3);
        }
        pGraphics.textImpl(c, n, n2, n3);
        if (n3 != 0.0f) {
            pGraphics.translate(0.0f, 0.0f, -n3);
        }
    }
    
    public void text(final String s, final float n, final float n2, final PGraphics pGraphics) {
        this.text(s, n, n2, 0.0f, pGraphics);
    }
    
    public void text(final String s, final float n, float n2, final float n3, final PGraphics pGraphics) {
        if (n3 != 0.0f) {
            pGraphics.translate(0.0f, 0.0f, n3);
        }
        final int length = s.length();
        if (length > this.textBuffer.length) {
            this.textBuffer = new char[length + 10];
        }
        s.getChars(0, length, this.textBuffer, 0);
        int n4 = 0;
        int i;
        for (i = 0; i < length; ++i) {
            if (this.textBuffer[i] == '\n') {
                this.textLine(n4, i, n, n2, n3, pGraphics);
                n4 = i + 1;
                n2 += pGraphics.textLeading;
            }
        }
        if (n4 < length) {
            this.textLine(n4, i, n, n2, n3, pGraphics);
        }
        if (n3 != 0.0f) {
            pGraphics.translate(0.0f, 0.0f, -n3);
        }
    }
    
    protected void textLine(final int n, final int n2, float n3, final float n4, final float n5, final PGraphics pGraphics) {
        if (pGraphics.textAlign == 3) {
            n3 -= pGraphics.textSize * this.calcWidth(this.textBuffer, n, n2) / 2.0f;
        }
        else if (pGraphics.textAlign == 39) {
            n3 -= pGraphics.textSize * this.calcWidth(this.textBuffer, n, n2);
        }
        for (int i = n; i < n2; ++i) {
            pGraphics.textImpl(this.textBuffer[i], n3, n4, 0.0f);
            n3 += pGraphics.textSize * this.width(this.textBuffer[i]);
        }
    }
    
    public void text(final String s, final float n, final float n2, final float n3, final float n4, final PGraphics pGraphics) {
        this.text(s, n, n2, n3, n4, 0.0f, pGraphics);
    }
    
    public void text(final String s, final float n, final float n2, final float n3, final float n4, final float n5, final PGraphics pGraphics) {
        if (n5 != 0.0f) {
            pGraphics.translate(0.0f, 0.0f, n5);
        }
        final float n6 = this.width(' ') * pGraphics.textSize;
        float n7 = n;
        final float n8 = n3 - n;
        float n9 = n;
        if (pGraphics.textAlign == 3) {
            n9 += n8 / 2.0f;
        }
        else if (pGraphics.textAlign == 39) {
            n9 = n3;
        }
        float n10 = n2 + this.ascent() * pGraphics.textSize;
        if (n10 > n4) {
            return;
        }
        final int length = s.length();
        if (length > this.textBuffer.length) {
            this.textBuffer = new char[length + 10];
        }
        s.getChars(0, length, this.textBuffer, 0);
        int n11 = 0;
        int n12 = 0;
        int n13 = 0;
        int i;
        for (i = 0; i < length; ++i) {
            if (this.textBuffer[i] == ' ' || i == length - 1) {
                final float n14 = pGraphics.textSize * this.calcWidth(this.textBuffer, n11, i);
                if (n7 + n14 > n3) {
                    Label_0320: {
                        if (n7 == n) {
                            while (--i != n11) {
                                if (pGraphics.textSize * this.calcWidth(this.textBuffer, n11, i) <= n8) {
                                    this.textLine(n13, i, n9, n10, n5, pGraphics);
                                    break Label_0320;
                                }
                            }
                            return;
                        }
                        this.textLine(n13, n12, n9, n10, n5, pGraphics);
                        for (i = n12; i < length && this.textBuffer[i] == ' '; ++i) {}
                    }
                    n13 = i;
                    n11 = i;
                    n12 = i;
                    n7 = n;
                    n10 += pGraphics.textLeading;
                    if (n10 > n4) {
                        return;
                    }
                }
                else {
                    n7 += n14 + n6;
                    n12 = i;
                    n11 = i + 1;
                }
            }
            else if (this.textBuffer[i] == '\n') {
                if (n13 != i) {
                    this.textLine(n13, i, n9, n10, n5, pGraphics);
                }
                n13 = (n11 = i + 1);
                n10 += pGraphics.textLeading;
                if (n10 > n4) {
                    return;
                }
            }
        }
        if (n13 < length && n13 != i) {
            this.textLine(n13, i, n9, n10, n5, pGraphics);
        }
        if (n5 != 0.0f) {
            pGraphics.translate(0.0f, 0.0f, -n5);
        }
    }
    
    public static String[] list() {
        if (PApplet.javaVersion < 1.3f) {
            return Toolkit.getDefaultToolkit().getFontList();
        }
        try {
            final Class<?> forName = Class.forName("java.awt.GraphicsEnvironment");
            final Font[] array = (Font[])forName.getMethod("getAllFonts", (Class<?>[])null).invoke(forName.getMethod("getLocalGraphicsEnvironment", (Class[])null).invoke(null, (Object[])null), (Object[])null);
            final String[] array2 = new String[array.length];
            for (int i = 0; i < array2.length; ++i) {
                array2[i] = array[i].getName();
            }
            return array2;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error inside PFont.list()");
        }
    }
    
    static /* synthetic */ Class class(final String s, final boolean b) {
        try {
            final Class<?> forName = Class.forName(s);
            if (!b) {
                forName.getComponentType();
            }
            return forName;
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    private final /* synthetic */ void this() {
        this.textBuffer = new char[8192];
        this.widthBuffer = new char[8192];
    }
    
    public PFont() {
        this.this();
    }
    
    public PFont(final InputStream inputStream) throws IOException {
        this.this();
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.charCount = dataInputStream.readInt();
        final int int1 = dataInputStream.readInt();
        this.size = dataInputStream.readInt();
        this.mbox2 = dataInputStream.readInt();
        this.fwidth = this.size;
        this.fheight = this.size;
        this.mbox2 = (int)Math.pow(2, Math.ceil(Math.log(this.mbox2) / Math.log(2)));
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
            this.images[k] = new PImage(new int[this.twidth * this.theight], this.twidth, this.theight, 4);
            final byte[] array = new byte[this.height[k] * this.width[k]];
            dataInputStream.readFully(array);
            final int n = this.width[k];
            for (int n2 = this.height[k], l = 0; l < n2; ++l) {
                for (int n3 = 0; n3 < n; ++n3) {
                    this.images[k].pixels[l * this.twidth + n3] = (array[l * n + n3] & 0xFF);
                }
            }
        }
        if (int1 == 10) {
            this.name = dataInputStream.readUTF();
            this.psname = dataInputStream.readUTF();
        }
    }
    
    public PFont(final Font font, final char[] array, final boolean b) {
        this.this();
        if (PApplet.javaVersion < 1.3f) {
            throw new RuntimeException("Can only create fonts with Java 1.3 or higher");
        }
        this.name = font.getName();
        this.psname = font.getPSName();
        try {
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
            final Class<?> forName = Class.forName("java.awt.image.BufferedImage");
            final Constructor<?> constructor = forName.getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE);
            final Field field = forName.getField("TYPE_INT_RGB");
            final Object instance = constructor.newInstance(new Integer(n2), new Integer(n2), new Integer(field.getInt(field)));
            final Class<?> forName2 = Class.forName("java.awt.Graphics2D");
            final Graphics graphics = (Graphics)forName.getMethod("getGraphics", (Class[])new Class[0]).invoke(instance, new Object[0]);
            final Class<?> forName3 = Class.forName("java.awt.RenderingHints");
            final Class<?> forName4 = Class.forName("java.awt.RenderingHints$Key");
            final Object value = forName3.getDeclaredField("KEY_TEXT_ANTIALIASING").get(forName3);
            final Object value2 = (b ? forName3.getField("VALUE_TEXT_ANTIALIAS_ON") : forName3.getField("VALUE_TEXT_ANTIALIAS_OFF")).get(forName3);
            final Class<?> clazz = forName2;
            final String s = "setRenderingHint";
            final Class[] array3 = { forName4, null };
            final int n3 = 1;
            Class class$java$lang$Object;
            if ((class$java$lang$Object = PFont.class$java$lang$Object) == null) {
                class$java$lang$Object = (PFont.class$java$lang$Object = class("[Ljava.lang.Object;", false));
            }
            array3[n3] = class$java$lang$Object;
            clazz.getMethod(s, (Class[])array3).invoke(graphics, value, value2);
            graphics.setFont(font);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int[] array4 = new int[n2 * n2];
            Class class$java$awt$Font;
            if ((class$java$awt$Font = PFont.class$java$awt$Font) == null) {
                class$java$awt$Font = (PFont.class$java$awt$Font = class("[Ljava.awt.Font;", false));
            }
            final Method method = class$java$awt$Font.getMethod("canDisplay", Character.TYPE);
            final Method method2 = forName.getMethod("getData", (Class[])new Class[0]);
            final Method method3 = Class.forName("java.awt.image.Raster").getMethod("getSamples", Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, array4.getClass());
            int n4 = 0;
            int charCount = 0;
            for (int j = 0; j < this.charCount; ++j) {
                final char c = (array == null) ? ((char)j) : array[j];
                try {
                    if (!(boolean)method.invoke(font, new Character(c))) {
                        continue;
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
                graphics.setColor(Color.white);
                graphics.fillRect(0, 0, n2, n2);
                graphics.setColor(Color.black);
                graphics.drawString(String.valueOf(c), this.size, this.size * 2);
                method3.invoke(method2.invoke(instance, new Object[0]), new Integer(0), new Integer(0), new Integer(n2), new Integer(n2), new Integer(0), array4);
                int n5 = 1000;
                int n6 = 0;
                int n7 = 1000;
                int n8 = 0;
                boolean b2 = false;
                for (int k = 0; k < n2; ++k) {
                    for (int l = 0; l < n2; ++l) {
                        if ((array4[k * n2 + l] & 0xFF) != 0xFF) {
                            if (l < n5) {
                                n5 = l;
                            }
                            if (k < n7) {
                                n7 = k;
                            }
                            if (l > n6) {
                                n6 = l;
                            }
                            if (k > n8) {
                                n8 = k;
                            }
                            b2 = true;
                        }
                    }
                }
                if (!b2) {
                    n7 = (n5 = 0);
                    n8 = (n6 = 0);
                }
                this.value[charCount] = c;
                this.height[charCount] = n8 - n7 + 1;
                this.width[charCount] = n6 - n5 + 1;
                this.setWidth[charCount] = fontMetrics.charWidth(c);
                if (c < '\u0080') {
                    this.ascii[c] = charCount;
                }
                this.topExtent[charCount] = this.size * 2 - n7;
                this.leftExtent[charCount] = n5 - this.size;
                if (c == 'd') {
                    this.ascent = this.topExtent[charCount];
                }
                if (c == 'p') {
                    this.descent = -this.topExtent[charCount] + this.height[charCount];
                }
                if (this.width[charCount] > n4) {
                    n4 = this.width[charCount];
                }
                if (this.height[charCount] > n4) {
                    n4 = this.height[charCount];
                }
                array2[charCount] = new PImage(new int[this.width[charCount] * this.height[charCount]], this.width[charCount], this.height[charCount], 4);
                for (int n9 = n7; n9 <= n8; ++n9) {
                    for (int n10 = n5; n10 <= n6; ++n10) {
                        array2[charCount].pixels[(n9 - n7) * this.width[charCount] + (n10 - n5)] = 255 - (array4[n9 * n2 + n10] & 0xFF);
                    }
                }
                ++charCount;
            }
            this.charCount = charCount;
            if (this.ascent == 0 && this.descent == 0) {
                for (int n11 = 0; n11 < this.charCount; ++n11) {
                    final char c2 = (char)this.value[n11];
                    if (!Character.isWhitespace(c2) && c2 != ' ' && c2 != '\u2007' && c2 != '\u202f') {
                        if (this.topExtent[n11] > this.ascent) {
                            this.ascent = this.topExtent[n11];
                        }
                        final int descent = -this.topExtent[n11] + this.height[n11];
                        if (descent > this.descent) {
                            this.descent = descent;
                        }
                    }
                }
            }
            this.mbox2 = (int)Math.pow(2, Math.ceil(Math.log(n4) / Math.log(2)));
            final int mbox2 = this.mbox2;
            this.theight = mbox2;
            this.twidth = mbox2;
            this.images = new PImage[this.charCount];
            for (int n12 = 0; n12 < this.charCount; ++n12) {
                this.images[n12] = new PImage(new int[this.mbox2 * this.mbox2], this.mbox2, this.mbox2, 4);
                for (int n13 = 0; n13 < this.height[n12]; ++n13) {
                    System.arraycopy(array2[n12].pixels, n13 * this.width[n12], this.images[n12].pixels, n13 * this.mbox2, this.width[n12]);
                }
                array2[n12] = null;
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            throw new RuntimeException(ex2.getMessage());
        }
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
