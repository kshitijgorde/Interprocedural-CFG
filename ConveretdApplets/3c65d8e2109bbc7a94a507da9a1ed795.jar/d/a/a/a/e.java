// 
// Decompiled by Procyon v0.5.30
// 

package d.a.a.a;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.DataInputStream;
import java.io.InputStream;

public class e
{
    private a byte;
    private b try;
    private d do;
    private f if;
    private h goto;
    private short[] case;
    private short[] char;
    private short[] void;
    private short[] else;
    private short[] int;
    private short[] long;
    private int[] new;
    private byte[] for;
    private static final int[] a;
    
    public e() {
        this.byte = new a();
        this.try = new b();
        this.do = new d();
        this.if = new f();
        this.goto = new h();
        this.case = new short[8];
        this.char = new short[4];
        this.void = new short[4];
        this.else = new short[4];
        this.int = new short[4];
        this.long = new short[52];
        this.new = new int[160];
        this.for = new byte[33];
    }
    
    public static void if(final InputStream inputStream) throws Exception {
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        final int int1 = dataInputStream.readInt();
        dataInputStream.readInt();
        dataInputStream.readInt();
        final int int2 = dataInputStream.readInt();
        final int int3 = dataInputStream.readInt();
        dataInputStream.readInt();
        dataInputStream.readChar();
        dataInputStream.readChar();
        dataInputStream.readChar();
        dataInputStream.readChar();
        if (int1 == 779316836) {
            if (int2 == 1) {
                if (int3 != 8000) {}
            }
        }
    }
    
    public void a(final String s, final String s2) throws Exception {
        final File file = new File(s);
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            throw new IOException("File : " + s + "\ndoes not exist or cannot be read.");
        }
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = new FileInputStream(s);
            fileOutputStream = new FileOutputStream(s2);
            if(inputStream);
            while (this.a(inputStream) > 0) {
                this.a();
                this.a(fileOutputStream);
            }
        }
        catch (Exception ex) {
            throw new Exception("Encoder: " + ex.getMessage());
        }
        finally {
            if (inputStream != null) {
                try {
                    ((FileInputStream)inputStream).close();
                }
                catch (IOException ex2) {
                    throw new IOException("Encoder: " + ex2.getMessage());
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                }
                catch (IOException ex3) {
                    throw new IOException("Encoder: " + ex3.getMessage());
                }
            }
        }
    }
    
    public void a(final InputStream inputStream, final String s) throws IOException {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(s);
            while (this.a(inputStream) > 0) {
                this.a();
                this.a(fileOutputStream);
            }
        }
        catch (IOException ex) {
            throw new IOException("Encoder: " + ex.getMessage());
        }
        finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                }
                catch (IOException ex2) {
                    throw new IOException("Encoder: " + ex2.getMessage());
                }
            }
        }
    }
    
    public void a(final short[] array, final byte[] array2) {
        for (int i = 0; i < 160; ++i) {
            this.new[i] = array[i];
        }
        this.a();
        System.arraycopy(this.for, 0, array2, 0, this.for.length);
    }
    
    private int a(final InputStream inputStream) throws IOException {
        int n;
        int read;
        for (n = 0; n < this.new.length && (read = inputStream.read()) != -1; ++n) {
            if (read < 0) {
                throw new IOException("Encoder ulaw_input: Corrupt InputStream.");
            }
            this.new[n] = e.a[read];
        }
        return n;
    }
    
    private void a() {
        int n = 0;
        this.if();
        this.for[n++] = (byte)(0xD0 | (this.case[0] >> 2 & 0xF));
        this.for[n++] = (byte)((this.case[0] & 0x3) << 6 | (this.case[1] & 0x3F));
        this.for[n++] = (byte)((this.case[2] & 0x1F) << 3 | (this.case[3] >> 2 & 0x7));
        this.for[n++] = (byte)((this.case[3] & 0x3) << 6 | (this.case[4] & 0xF) << 2 | (this.case[5] >> 2 & 0x3));
        this.for[n++] = (byte)((this.case[5] & 0x3) << 6 | (this.case[6] & 0x7) << 3 | (this.case[7] & 0x7));
        this.for[n++] = (byte)((this.char[0] & 0x7F) << 1 | (this.else[0] >> 1 & 0x1));
        this.for[n++] = (byte)((this.else[0] & 0x1) << 7 | (this.void[0] & 0x3) << 5 | (this.int[0] >> 1 & 0x1F));
        this.for[n++] = (byte)((this.int[0] & 0x1) << 7 | (this.long[0] & 0x7) << 4 | (this.long[1] & 0x7) << 1 | (this.long[2] >> 2 & 0x1));
        this.for[n++] = (byte)((this.long[2] & 0x3) << 6 | (this.long[3] & 0x7) << 3 | (this.long[4] & 0x7));
        this.for[n++] = (byte)((this.long[5] & 0x7) << 5 | (this.long[6] & 0x7) << 2 | (this.long[7] >> 1 & 0x3));
        this.for[n++] = (byte)((this.long[7] & 0x1) << 7 | (this.long[8] & 0x7) << 4 | (this.long[9] & 0x7) << 1 | (this.long[10] >> 2 & 0x1));
        this.for[n++] = (byte)((this.long[10] & 0x3) << 6 | (this.long[11] & 0x7) << 3 | (this.long[12] & 0x7));
        this.for[n++] = (byte)((this.char[1] & 0x7F) << 1 | (this.else[1] >> 1 & 0x1));
        this.for[n++] = (byte)((this.else[1] & 0x1) << 7 | (this.void[1] & 0x3) << 5 | (this.int[1] >> 1 & 0x1F));
        this.for[n++] = (byte)((this.int[1] & 0x1) << 7 | (this.long[13] & 0x7) << 4 | (this.long[14] & 0x7) << 1 | (this.long[15] >> 2 & 0x1));
        this.for[n++] = (byte)((this.long[15] & 0x3) << 6 | (this.long[16] & 0x7) << 3 | (this.long[17] & 0x7));
        this.for[n++] = (byte)((this.long[18] & 0x7) << 5 | (this.long[19] & 0x7) << 2 | (this.long[20] >> 1 & 0x3));
        this.for[n++] = (byte)((this.long[20] & 0x1) << 7 | (this.long[21] & 0x7) << 4 | (this.long[22] & 0x7) << 1 | (this.long[23] >> 2 & 0x1));
        this.for[n++] = (byte)((this.long[23] & 0x3) << 6 | (this.long[24] & 0x7) << 3 | (this.long[25] & 0x7));
        this.for[n++] = (byte)((this.char[2] & 0x7F) << 1 | (this.else[2] >> 1 & 0x1));
        this.for[n++] = (byte)((this.else[2] & 0x1) << 7 | (this.void[2] & 0x3) << 5 | (this.int[2] >> 1 & 0x1F));
        this.for[n++] = (byte)((this.int[2] & 0x1) << 7 | (this.long[26] & 0x7) << 4 | (this.long[27] & 0x7) << 1 | (this.long[28] >> 2 & 0x1));
        this.for[n++] = (byte)((this.long[28] & 0x3) << 6 | (this.long[29] & 0x7) << 3 | (this.long[30] & 0x7));
        this.for[n++] = (byte)((this.long[31] & 0x7) << 5 | (this.long[32] & 0x7) << 2 | (this.long[33] >> 1 & 0x3));
        this.for[n++] = (byte)((this.long[33] & 0x1) << 7 | (this.long[34] & 0x7) << 4 | (this.long[35] & 0x7) << 1 | (this.long[36] >> 2 & 0x1));
        this.for[n++] = (byte)((this.long[36] & 0x3) << 6 | (this.long[37] & 0x7) << 3 | (this.long[38] & 0x7));
        this.for[n++] = (byte)((this.char[3] & 0x7F) << 1 | (this.else[3] >> 1 & 0x1));
        this.for[n++] = (byte)((this.else[3] & 0x1) << 7 | (this.void[3] & 0x3) << 5 | (this.int[3] >> 1 & 0x1F));
        this.for[n++] = (byte)((this.int[3] & 0x1) << 7 | (this.long[39] & 0x7) << 4 | (this.long[40] & 0x7) << 1 | (this.long[41] >> 2 & 0x1));
        this.for[n++] = (byte)((this.long[41] & 0x3) << 6 | (this.long[42] & 0x7) << 3 | (this.long[43] & 0x7));
        this.for[n++] = (byte)((this.long[44] & 0x7) << 5 | (this.long[45] & 0x7) << 2 | (this.long[46] >> 1 & 0x3));
        this.for[n++] = (byte)((this.long[46] & 0x1) << 7 | (this.long[47] & 0x7) << 4 | (this.long[48] & 0x7) << 1 | (this.long[49] >> 2 & 0x1));
        this.for[n++] = (byte)((this.long[49] & 0x3) << 6 | (this.long[50] & 0x7) << 3 | (this.long[51] & 0x7));
    }
    
    private void if() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 120;
        final short[] array = new short[40];
        final short[] array2 = new short[50];
        final short[] array3 = new short[160];
        this.a(array3);
        this.do.a(array3, this.case);
        this.goto.a(this.byte, this.case, array3);
        final short[] else1;
        final short[] array4 = else1 = this.byte.else();
        for (int i = 0; i <= 3; ++i, n += 13) {
            this.try.a(array3, i * 40, array2, array4, else1, n4, this.char, this.else, n2++);
            this.if.a(array2, this.int, this.void, n3++, this.long, n);
            for (int j = 0; j <= 39; ++j) {
                array4[j + n4] = j.new(array2[5 + j], else1[j + n4]);
            }
            this.byte.a(array4);
            n4 += 40;
        }
        for (int k = 0; k < 120; ++k) {
            this.byte.if(k, this.byte.a(160 + k));
        }
    }
    
    private void a(final short[] array) throws IllegalArgumentException {
        int n = 0;
        int n2 = 0;
        short for1 = this.byte.for();
        int n3 = this.byte.try();
        int n4 = this.byte.char();
        int i = 160;
        while (i != 0) {
            --i;
            final short n5 = (short)(j.if((int)(short)this.new[n++], 3) << 2);
            if (n5 < -16384) {
                throw new IllegalArgumentException("Gsm_Preprocess: SO = " + n5 + " is out of range. Sould be >= -0x4000 ");
            }
            if (n5 > 16380) {
                throw new IllegalArgumentException("Gsm_Preprocess: SO = " + n5 + " is out of range. Sould be <= 0x3FFC ");
            }
            final short n6 = (short)(n5 - for1);
            for1 = n5;
            if (n6 == -32768) {
                throw new IllegalArgumentException("Gsm_Preprocess: s1 = " + n6 + " is out of range. ");
            }
            final int n7 = n6 << 15;
            final short if1 = j.if(n3, 15);
            n3 = j.a(if1 * 32735, n7 + j.do((short)(n3 - (if1 << 15)), (short)32735));
            final int a = j.a(n3, 16384);
            final short do1 = j.do((short)n4, (short)(-28180));
            n4 = j.if(a, 15);
            array[n2++] = j.new((short)n4, do1);
        }
        this.byte.for(for1);
        this.byte.int(n3);
        this.byte.if(n4);
    }
    
    private void a(final FileOutputStream fileOutputStream) throws IOException {
        for (int i = 0; i < this.for.length; ++i) {
            fileOutputStream.write(this.for[i]);
        }
    }
    
    private void a(final a a) {
        a.do();
    }
    
    static {
        a = new int[] { 33280, 34308, 35336, 36364, 37393, 38421, 39449, 40477, 41505, 42534, 43562, 44590, 45618, 46647, 47675, 48703, 49474, 49988, 50503, 51017, 51531, 52045, 52559, 53073, 53587, 54101, 54616, 55130, 55644, 56158, 56672, 57186, 57572, 57829, 58086, 58343, 58600, 58857, 59114, 59371, 59628, 59885, 60142, 60399, 60656, 60913, 61171, 61428, 61620, 61749, 61877, 62006, 62134, 62263, 62392, 62520, 62649, 62777, 62906, 63034, 63163, 63291, 63420, 63548, 63645, 63709, 63773, 63838, 63902, 63966, 64030, 64095, 64159, 64223, 64287, 64352, 64416, 64480, 64544, 64609, 64657, 64689, 64721, 64753, 64785, 64818, 64850, 64882, 64914, 64946, 64978, 65010, 65042, 65075, 65107, 65139, 65163, 65179, 65195, 65211, 65227, 65243, 65259, 65275, 65291, 65308, 65324, 65340, 65356, 65372, 65388, 65404, 65416, 65424, 65432, 65440, 65448, 65456, 65464, 65472, 65480, 65488, 65496, 65504, 65512, 65520, 65528, 0, 32256, 31228, 30200, 29172, 28143, 27115, 26087, 25059, 24031, 23002, 21974, 20946, 19918, 18889, 17861, 16833, 16062, 15548, 15033, 14519, 14005, 13491, 12977, 12463, 11949, 11435, 10920, 10406, 9892, 9378, 8864, 8350, 7964, 7707, 7450, 7193, 6936, 6679, 6422, 6165, 5908, 5651, 5394, 5137, 4880, 4623, 4365, 4108, 3916, 3787, 3659, 3530, 3402, 3273, 3144, 3016, 2887, 2759, 2630, 2502, 2373, 2245, 2116, 1988, 1891, 1827, 1763, 1698, 1634, 1570, 1506, 1441, 1377, 1313, 1249, 1184, 1120, 1056, 992, 927, 879, 847, 815, 783, 751, 718, 686, 654, 622, 590, 558, 526, 494, 461, 429, 397, 373, 357, 341, 325, 309, 293, 277, 261, 245, 228, 212, 196, 180, 164, 148, 132, 120, 112, 104, 96, 88, 80, 72, 64, 56, 48, 40, 32, 24, 16, 8, 0 };
    }
}
