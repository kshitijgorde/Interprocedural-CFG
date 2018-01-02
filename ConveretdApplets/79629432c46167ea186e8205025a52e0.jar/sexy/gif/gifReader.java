// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gif;

import java.net.URLConnection;
import java.net.URL;
import java.io.ByteArrayInputStream;
import sexy.gui.SexyGraphics;
import sexy.gui.SexyImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import sexy.gui.SexyColor;

public class gifReader
{
    public int screenX;
    public int screenY;
    public int frameCount;
    public int loopCount;
    private int last_h_offset;
    private int last_v_offset;
    private int last_width;
    private int last_height;
    private int last_disposal_method;
    private StringBuffer text;
    public SexyColor bgColor;
    private byte[][] gCtab;
    private byte[][] cur_pal;
    public Vector frames;
    public Vector colorTables;
    public Vector commentText;
    private InputStream in;
    private gifImage gif;
    private String statusStr;
    private boolean mDoClose;
    public static final int AUX_TYPE = 33;
    public static final int IMAGE_TYPE = 44;
    public static final int AUX_GRAF_CNTL_EXT = 249;
    public static final int AUX_TEXT_COMMENT = 254;
    public static final int AUX_TEXT_PLAIN = 1;
    public static final int AUX_APPLICATION_EXT = 255;
    
    byte[] de_interlace(final byte[] array, final int n, final int n2) {
        final byte[] array2 = new byte[array.length];
        int n3 = 0;
        final int[] array3 = { 0, 4, 2, 1 };
        final int[] array4 = { 8, 8, 4, 2 };
        int n4 = 0;
        do {
            for (int i = array3[n4]; i < n2; i += array4[n4]) {
                System.arraycopy(array, n * n3++, array2, i * n, n);
            }
        } while (++n4 < 4);
        return array2;
    }
    
    void burn_error_bytes() throws IOException {
        int read;
        do {
            read = this.in.read();
        } while (read != 0 && read != -1);
    }
    
    private int unsigned(final int n) {
        return n & 0xFF;
    }
    
    public int last_disposal() {
        return this.last_disposal_method;
    }
    
    public int screenWidth() {
        return this.screenX;
    }
    
    public void set_last_width(final int last_width) {
        this.last_width = last_width;
    }
    
    void readFully(final byte[] array) throws IOException {
        int read;
        for (int i = 0; i < array.length; i += read) {
            read = this.in.read(array, i, array.length - i);
            if (read == -1) {
                throw new IOException("readFully failed");
            }
        }
    }
    
    void read_graphic_control_ext() throws IOException {
        final byte[] array = new byte[6];
        try {
            this.readFully(array);
            this.gif.set_delay(this.intFromBytes(array[2], array[3]));
            if ((array[1] & 0x1) != 0x0) {
                this.gif.set_transparent(true);
                this.gif.set_trans_index(this.unsigned(array[4]));
            }
            this.gif.set_disposal(array[1] >> 2 & 0x7);
        }
        catch (IOException ex) {
            System.out.println("Failed to read graphic control extension");
        }
    }
    
    public String getCommentText(final int n) {
        String s = null;
        if (this.getCommentsCount() > 0) {
            s = new String(this.commentText.elementAt(n));
        }
        return s;
    }
    
    public void readAll() {
        try {
            this.read_header();
            this.read_logical_screen_descriptor();
            while (true) {
                final int get_block_type = this.get_block_type();
                if (get_block_type == -1) {
                    break;
                }
                switch (get_block_type) {
                    case 33: {
                        switch (this.get_block_type()) {
                            case 249: {
                                this.read_graphic_control_ext();
                                continue;
                            }
                            case 254: {
                                this.read_comment_text();
                                continue;
                            }
                            case 1: {
                                this.read_plainText_block();
                                continue;
                            }
                            case 255: {
                                this.read_application_extension();
                                continue;
                            }
                        }
                        break;
                    }
                    case 44: {
                        this.read_image_data();
                        continue;
                    }
                    default: {
                        this.burn_error_bytes();
                        continue;
                    }
                }
            }
            this.close();
        }
        catch (IOException ex) {
            System.out.println("Decoding failed!");
            ex.printStackTrace();
        }
    }
    
    public void copyIndividualFrame(final SexyImage sexyImage, final int n) {
        final gifImage frame = this.getFrame(n);
        final SexyGraphics sexyGraphics = new SexyGraphics(sexyImage);
        boolean b = false;
        int last_x = 0;
        int last_y = 0;
        int n2 = 0;
        int n3 = 0;
        if (n == 0) {
            if (frame.width() != this.screenWidth() || frame.height() != this.screenHeight() || frame.transparent) {
                b = true;
                n2 = this.screenWidth();
                n3 = this.screenHeight();
            }
        }
        else if (this.last_disposal() == 2) {
            b = true;
            last_x = this.last_x();
            last_y = this.last_y();
            n2 = this.last_width();
            n3 = this.last_height();
        }
        this.set_last_disposal(frame.disposal());
        this.set_last_y(frame.vert_offset());
        this.set_last_x(frame.horiz_offset());
        this.set_last_width(frame.width());
        this.set_last_height(frame.height());
        if (b) {
            if (this.bgColor == null || frame.transparent) {
                sexyGraphics.SetColor(new SexyColor(0, 0, 0));
            }
            else {
                sexyGraphics.SetColor(this.bgColor);
            }
            sexyGraphics.FillRect(last_x, last_y, n2, n3);
        }
        sexyGraphics.DrawImage(frame.frame(), frame.horiz_offset(), frame.vert_offset());
    }
    
    public void set_last_x(final int last_h_offset) {
        this.last_h_offset = last_h_offset;
    }
    
    void read_application_extension() throws IOException {
        final byte[] array = new byte[11];
        try {
            this.unsigned(this.in.read());
            final byte[] array2 = new byte[11];
            this.readFully(array2);
            final int unsigned = this.unsigned(this.in.read());
            final byte[] array3 = new byte[unsigned];
            this.readFully(array3);
            if (new String(array2, 0).toLowerCase().indexOf("netscape2.0") != -1 && unsigned == 3) {
                this.loopCount = this.intFromBytes(array3[1], array3[2]);
            }
            int unsigned2;
            while ((unsigned2 = this.unsigned(this.in.read())) != 0) {
                this.readFully(new byte[unsigned2]);
            }
        }
        catch (IOException ex) {
            System.out.println("Failed to read application extension");
        }
    }
    
    public int last_height() {
        return this.last_height;
    }
    
    void read_image_data() throws IOException {
        final byte[] array = new byte[9];
        this.readFully(array);
        final int intFromBytes = this.intFromBytes(array[0], array[1]);
        final int intFromBytes2 = this.intFromBytes(array[2], array[3]);
        final int intFromBytes3 = this.intFromBytes(array[4], array[5]);
        final int intFromBytes4 = this.intFromBytes(array[6], array[7]);
        final byte b = array[8];
        final boolean b2 = (b & 0x40) != 0x0;
        byte[] de_interlace = new byte[intFromBytes3 * intFromBytes4];
        if ((b & 0x80) != 0x0) {
            this.colorTables.addElement(this.cur_pal = this.obtain_palette((int)Math.pow(2.0, 1 + (b & 0x7))));
        }
        else {
            this.cur_pal = null;
        }
        final int read = this.in.read();
        final byte[] array2 = { 0 };
        byte[] concatByteArray = new byte[0];
        int read2;
        while ((read2 = this.in.read()) != 0) {
            array2[0] = (byte)read2;
            final byte[] concatByteArray2 = this.concatByteArray(concatByteArray, array2);
            final byte[] array3 = new byte[read2];
            this.readFully(array3);
            concatByteArray = this.concatByteArray(concatByteArray2, array3);
        }
        new LZWDecompressor(new ByteArrayInputStream(concatByteArray), read, false).decompress(de_interlace);
        final byte[][] array4 = (this.cur_pal == null) ? this.gCtab : this.cur_pal;
        final SexyImage sexyImage = new SexyImage();
        sexyImage.Create(intFromBytes3, intFromBytes4);
        final int[] getBits = sexyImage.GetBits();
        final int[] array5 = new int[array4.length];
        for (int i = 0; i < array4.length; ++i) {
            array5[i] = (0xFF000000 | (array4[i][2] & 0xFF) | (array4[i][1] & 0xFF) << 8 | (array4[i][0] & 0xFF) << 16);
        }
        if (this.gif.transparency()) {
            final int[] array6 = array5;
            final int transparencyIndex = this.gif.transparencyIndex();
            array6[transparencyIndex] &= 0xFFFFFF;
        }
        if (b2) {
            de_interlace = this.de_interlace(de_interlace, intFromBytes3, intFromBytes4);
        }
        for (int j = 0; j < getBits.length; ++j) {
            getBits[j] = array5[de_interlace[j] & 0xFF];
        }
        this.frames.addElement(new gifImage(sexyImage, intFromBytes, intFromBytes2, this.gif.delay(), this.gif.disposal(), this.gif.transparencyIndex(), this.gif.transparency()));
        ++this.frameCount;
    }
    
    private byte[][] obtain_palette(final int n) {
        final byte[] array = new byte[3];
        final byte[][] cur_pal = new byte[n][3];
        try {
            for (int i = 0; i < n; ++i) {
                this.readFully(cur_pal[i]);
            }
        }
        catch (IOException ex) {
            System.out.println("colorTable build failed");
        }
        return this.cur_pal = cur_pal;
    }
    
    public void set_last_y(final int last_v_offset) {
        this.last_v_offset = last_v_offset;
    }
    
    void read_logical_screen_descriptor() throws IOException {
        final byte[] array = new byte[7];
        int n = 0;
        try {
            this.readFully(array);
            this.screenX = this.intFromBytes(array[0], array[1]);
            this.screenY = this.intFromBytes(array[2], array[3]);
            if ((array[4] & 0x80) != 0x0) {
                n = (int)Math.pow(2.0, 1 + (array[4] & 0x7));
                this.colorTables.addElement(this.gCtab = this.obtain_palette(n));
            }
            final int n2 = array[5] & 0xFF;
            if (n2 >= 0 && n2 < n) {
                this.bgColor = new SexyColor(this.gCtab[n2][0], this.gCtab[n2][1], this.gCtab[n2][2]);
            }
        }
        catch (IOException ex) {
            System.out.println("Failed to read header");
        }
    }
    
    public void set_last_height(final int last_height) {
        this.last_height = last_height;
    }
    
    public int last_width() {
        return this.last_width;
    }
    
    void read_comment_text() throws IOException {
        final StringBuffer sb = new StringBuffer();
        try {
            int unsigned;
            while ((unsigned = this.unsigned(this.in.read())) != 0) {
                final byte[] array = new byte[unsigned];
                this.readFully(array);
                sb.append(new String(array, 0));
            }
        }
        catch (IOException ex) {
            System.out.println("Failed to read comment text");
        }
        if (sb.length() > 0) {
            if (this.commentText == null) {
                this.commentText = new Vector();
            }
            this.commentText.addElement(sb.toString());
        }
    }
    
    void read_header() throws IOException {
        try {
            this.readFully(new byte[6]);
        }
        catch (IOException ex) {
            System.out.println("Failed to read header");
        }
    }
    
    public int last_x() {
        return this.last_h_offset;
    }
    
    void close() throws IOException {
        if (this.mDoClose) {
            this.in.close();
        }
    }
    
    public int screenHeight() {
        return this.screenY;
    }
    
    void read_plainText_block() throws IOException {
        final StringBuffer sb = new StringBuffer();
        boolean b = false;
        final byte[] array = new byte[12];
        try {
            this.unsigned(this.in.read());
            this.readFully(array);
            while (!b) {
                final int unsigned = this.unsigned(this.in.read());
                if (unsigned != 0) {
                    final byte[] array2 = new byte[unsigned];
                    this.readFully(array2);
                    sb.append(array2);
                }
                else {
                    b = true;
                }
            }
        }
        catch (IOException ex) {
            System.out.println("Failed to read plain text block");
        }
    }
    
    public int getColorTableCount() {
        if (this.colorTables == null) {
            return 0;
        }
        return this.colorTables.size();
    }
    
    public int getCommentsCount() {
        if (this.commentText == null) {
            return 0;
        }
        return this.commentText.size();
    }
    
    public int last_y() {
        return this.last_v_offset;
    }
    
    public gifReader(final URL url, final String s) throws IOException {
        this.screenX = 0;
        this.screenY = 0;
        this.frameCount = 0;
        this.loopCount = -1;
        this.bgColor = null;
        this.gCtab = null;
        this.frames = new Vector();
        this.colorTables = new Vector();
        this.commentText = null;
        this.gif = new gifImage();
        this.statusStr = "";
        this.mDoClose = false;
        final URLConnection openConnection = new URL(url, s).openConnection();
        openConnection.connect();
        this.in = openConnection.getInputStream();
        this.mDoClose = true;
    }
    
    public gifReader(final InputStream in) {
        this.screenX = 0;
        this.screenY = 0;
        this.frameCount = 0;
        this.loopCount = -1;
        this.bgColor = null;
        this.gCtab = null;
        this.frames = new Vector();
        this.colorTables = new Vector();
        this.commentText = null;
        this.gif = new gifImage();
        this.statusStr = "";
        this.mDoClose = false;
        this.in = in;
    }
    
    public gifImage getFrame(final int n) {
        if (n < this.frames.size()) {
            return this.frames.elementAt(n);
        }
        return null;
    }
    
    public void set_last_disposal(final int last_disposal_method) {
        this.last_disposal_method = last_disposal_method;
    }
    
    private int intFromBytes(final int n, final int n2) {
        return (n2 << 8 & 0xFF00) | (n & 0xFF);
    }
    
    private byte[] concatByteArray(final byte[] array, final byte[] array2) {
        final byte[] array3 = new byte[array.length + array2.length];
        System.arraycopy(array, 0, array3, 0, array.length);
        System.arraycopy(array2, 0, array3, array.length, array2.length);
        return array3;
    }
    
    public int getFrameCount() {
        return this.frameCount;
    }
    
    public byte[][] getColorTable(final int n) {
        if (n < this.colorTables.size()) {
            return this.colorTables.elementAt(n);
        }
        return null;
    }
    
    int get_block_type() throws IOException {
        return this.in.read();
    }
}
