// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.io.Serializable;
import java.io.PrintStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;

public class br
{
    private static final int case = 4096;
    private static final int byte = 544501094;
    private static final int try = 1635017060;
    private static final int new = 1179011410;
    private static final int int = 1163280727;
    private File h;
    private a for;
    private int do;
    private long if;
    private FileOutputStream a;
    private FileInputStream g;
    private double f;
    private double e;
    private boolean d;
    private int c;
    private long i;
    private int b;
    private int void;
    private byte[] char;
    private int long;
    private int goto;
    private long else;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$webphone$WavFile$IOState;
    
    private br() {
        this.char = new byte[4096];
    }
    
    public int byte() {
        return this.c;
    }
    
    public long new() {
        return this.if;
    }
    
    public long do() {
        return this.if - this.else;
    }
    
    public long try() {
        return this.i;
    }
    
    public int for() {
        return this.void;
    }
    
    public static br a(final File h, final int c, final long if1, final int void1, final long i) throws IOException, bn {
        final br br = new br();
        br.h = h;
        br.c = c;
        br.if = if1;
        br.i = i;
        br.do = (void1 + 7) / 8;
        br.b = br.do * c;
        br.void = void1;
        if (c < 1 || c > 65535) {
            throw new bn("Illegal number of channels, valid range 1 to 65536");
        }
        if (if1 < 0L) {
            throw new bn("Number of frames must be positive");
        }
        if (void1 < 2 || void1 > 65535) {
            throw new bn("Illegal number of valid bits, valid range 2 to 65536");
        }
        if (i < 0L) {
            throw new bn("Sample rate must be positive");
        }
        br.a = new FileOutputStream(h);
        final long n = br.b * if1;
        long n2 = 36L + n;
        if (n % 2L == 1L) {
            ++n2;
            br.d = true;
        }
        else {
            br.d = false;
        }
        a(1179011410L, br.char, 0, 4);
        a(n2, br.char, 4, 4);
        a(1163280727L, br.char, 8, 4);
        br.a.write(br.char, 0, 12);
        final long n3 = i * br.b;
        a(544501094L, br.char, 0, 4);
        a(16L, br.char, 4, 4);
        a(1L, br.char, 8, 2);
        a(c, br.char, 10, 2);
        a(i, br.char, 12, 4);
        a(n3, br.char, 16, 4);
        a(br.b, br.char, 20, 2);
        a(void1, br.char, 22, 2);
        br.a.write(br.char, 0, 24);
        a(1635017060L, br.char, 0, 4);
        a(n, br.char, 4, 4);
        br.a.write(br.char, 0, 8);
        if (br.void > 8) {
            br.e = 0.0;
            br.f = Long.MAX_VALUE >> 64 - br.void;
        }
        else {
            br.e = 1.0;
            br.f = 0.5 * ((1 << br.void) - 1);
        }
        br.long = 0;
        br.goto = 0;
        br.else = 0L;
        br.for = a.a;
        return br;
    }
    
    public static br a(final File h) throws IOException, bn {
        final br br = new br();
        br.h = h;
        br.g = new FileInputStream(h);
        if (br.g.read(br.char, 0, 12) != 12) {
            throw new bn("Not enough wav file bytes for header");
        }
        final long a = a(br.char, 0, 4);
        final long a2 = a(br.char, 4, 4);
        final long a3 = a(br.char, 8, 4);
        if (a != 1179011410L) {
            throw new bn("Invalid Wav Header data, incorrect riff chunk ID");
        }
        if (a3 != 1163280727L) {
            throw new bn("Invalid Wav Header data, incorrect riff type ID");
        }
        if (h.length() != a2 + 8L) {
            throw new bn("Header chunk size (" + a2 + ") does not match file size (" + h.length() + ")");
        }
        boolean b = false;
        while (true) {
            final int read = br.g.read(br.char, 0, 8);
            if (read == -1) {
                throw new bn("Reached end of file without finding format chunk");
            }
            if (read != 8) {
                throw new bn("Could not read chunk header");
            }
            final long a4 = a(br.char, 0, 4);
            final long a5 = a(br.char, 4, 4);
            final long n = (a5 % 2L == 1L) ? (a5 + 1L) : a5;
            if (a4 == 544501094L) {
                b = true;
                br.g.read(br.char, 0, 16);
                final int n2 = (int)a(br.char, 0, 2);
                if (n2 != 1) {
                    throw new bn("Compression Code " + n2 + " not supported");
                }
                br.c = (int)a(br.char, 2, 2);
                br.i = a(br.char, 4, 4);
                br.b = (int)a(br.char, 12, 2);
                br.void = (int)a(br.char, 14, 2);
                if (br.c == 0) {
                    throw new bn("Number of channels specified in header is equal to zero");
                }
                if (br.b == 0) {
                    throw new bn("Block Align specified in header is equal to zero");
                }
                if (br.void < 2) {
                    throw new bn("Valid Bits specified in header is less than 2");
                }
                if (br.void > 64) {
                    throw new bn("Valid Bits specified in header is greater than 64, this is greater than a long can hold");
                }
                br.do = (br.void + 7) / 8;
                if (br.do * br.c != br.b) {
                    throw new bn("Block Align does not agree with bytes required for validBits and number of channels");
                }
                final long n3 = n - 16L;
                if (n3 <= 0L) {
                    continue;
                }
                br.g.skip(n3);
            }
            else if (a4 == 1635017060L) {
                if (!b) {
                    throw new bn("Data chunk found before Format chunk");
                }
                if (a5 % br.b != 0L) {
                    throw new bn("Data Chunk size is not multiple of Block Align");
                }
                br.if = a5 / br.b;
                if (!true) {
                    throw new bn("Did not find a data chunk");
                }
                if (br.void > 8) {
                    br.e = 0.0;
                    br.f = 1 << br.void - 1;
                }
                else {
                    br.e = -1.0;
                    br.f = 0.5 * ((1 << br.void) - 1);
                }
                br.long = 0;
                br.goto = 0;
                br.else = 0L;
                br.for = webphone.br.a.if;
                return br;
            }
            else {
                br.g.skip(n);
            }
        }
    }
    
    private static long a(final byte[] array, int n, int n2) {
        --n2;
        n += n2;
        long n3 = array[n] & 0xFF;
        for (int i = 0; i < n2; ++i) {
            n3 = (n3 << 8) + (array[--n] & 0xFF);
        }
        return n3;
    }
    
    private static void a(long n, final byte[] array, int n2, final int n3) {
        for (int i = 0; i < n3; ++i) {
            array[n2] = (byte)(n & 0xFFL);
            n >>= 8;
            ++n2;
        }
    }
    
    private void a(long n) throws IOException {
        for (int i = 0; i < this.do; ++i) {
            if (this.long == 4096) {
                this.a.write(this.char, 0, 4096);
                this.long = 0;
            }
            this.char[this.long] = (byte)(n & 0xFFL);
            n >>= 8;
            ++this.long;
        }
    }
    
    private long a() throws IOException, bn {
        long n = 0L;
        for (int i = 0; i < this.do; ++i) {
            if (this.long == this.goto) {
                final int read = this.g.read(this.char, 0, 4096);
                if (read == -1) {
                    throw new bn("Not enough data available");
                }
                this.goto = read;
                this.long = 0;
            }
            int n2 = this.char[this.long];
            if (i < this.do - 1 || this.do == 1) {
                n2 &= 0xFF;
            }
            n += n2 << i * 8;
            ++this.long;
        }
        return n;
    }
    
    public int if(final int[] array, final int n) throws IOException, bn {
        return this.a(array, 0, n);
    }
    
    public int a(final int[] array, int n, final int n2) throws IOException, bn {
        if (this.for != br.a.if) {
            throw new IOException("Cannot read from WavFile instance");
        }
        for (int i = 0; i < n2; ++i) {
            if (this.else == this.if) {
                return i;
            }
            for (int j = 0; j < this.c; ++j) {
                array[n] = (int)this.a();
                ++n;
            }
            ++this.else;
        }
        return n2;
    }
    
    public int a(final int[][] array, final int n) throws IOException, bn {
        return this.if(array, 0, n);
    }
    
    public int if(final int[][] array, int n, final int n2) throws IOException, bn {
        if (this.for != br.a.if) {
            throw new IOException("Cannot read from WavFile instance");
        }
        for (int i = 0; i < n2; ++i) {
            if (this.else == this.if) {
                return i;
            }
            for (int j = 0; j < this.c; ++j) {
                array[j][n] = (int)this.a();
            }
            ++n;
            ++this.else;
        }
        return n2;
    }
    
    public int a(final int[] array, final int n) throws IOException, bn {
        return this.if(array, 0, n);
    }
    
    public int if(final int[] array, int n, final int n2) throws IOException, bn {
        if (this.for != br.a.a) {
            throw new IOException("Cannot write to WavFile instance");
        }
        for (int i = 0; i < n2; ++i) {
            if (this.else == this.if) {
                return i;
            }
            for (int j = 0; j < this.c; ++j) {
                this.a(array[n]);
                ++n;
            }
            ++this.else;
        }
        return n2;
    }
    
    public int if(final int[][] array, final int n) throws IOException, bn {
        return this.a(array, 0, n);
    }
    
    public int a(final int[][] array, int n, final int n2) throws IOException, bn {
        if (this.for != br.a.a) {
            throw new IOException("Cannot write to WavFile instance");
        }
        for (int i = 0; i < n2; ++i) {
            if (this.else == this.if) {
                return i;
            }
            for (int j = 0; j < this.c; ++j) {
                this.a(array[j][n]);
            }
            ++n;
            ++this.else;
        }
        return n2;
    }
    
    public int if(final long[] array, final int n) throws IOException, bn {
        return this.if(array, 0, n);
    }
    
    public int if(final long[] array, int n, final int n2) throws IOException, bn {
        if (this.for != br.a.if) {
            throw new IOException("Cannot read from WavFile instance");
        }
        for (int i = 0; i < n2; ++i) {
            if (this.else == this.if) {
                return i;
            }
            for (int j = 0; j < this.c; ++j) {
                array[n] = this.a();
                ++n;
            }
            ++this.else;
        }
        return n2;
    }
    
    public int a(final long[][] array, final int n) throws IOException, bn {
        return this.a(array, 0, n);
    }
    
    public int a(final long[][] array, int n, final int n2) throws IOException, bn {
        if (this.for != br.a.if) {
            throw new IOException("Cannot read from WavFile instance");
        }
        for (int i = 0; i < n2; ++i) {
            if (this.else == this.if) {
                return i;
            }
            for (int j = 0; j < this.c; ++j) {
                array[j][n] = this.a();
            }
            ++n;
            ++this.else;
        }
        return n2;
    }
    
    public int a(final long[] array, final int n) throws IOException, bn {
        return this.a(array, 0, n);
    }
    
    public int a(final long[] array, int n, final int n2) throws IOException, bn {
        if (this.for != br.a.a) {
            throw new IOException("Cannot write to WavFile instance");
        }
        for (int i = 0; i < n2; ++i) {
            if (this.else == this.if) {
                return i;
            }
            for (int j = 0; j < this.c; ++j) {
                this.a(array[n]);
                ++n;
            }
            ++this.else;
        }
        return n2;
    }
    
    public int if(final long[][] array, final int n) throws IOException, bn {
        return this.if(array, 0, n);
    }
    
    public int if(final long[][] array, int n, final int n2) throws IOException, bn {
        if (this.for != br.a.a) {
            throw new IOException("Cannot write to WavFile instance");
        }
        for (int i = 0; i < n2; ++i) {
            if (this.else == this.if) {
                return i;
            }
            for (int j = 0; j < this.c; ++j) {
                this.a(array[j][n]);
            }
            ++n;
            ++this.else;
        }
        return n2;
    }
    
    public int if(final double[] array, final int n) throws IOException, bn {
        return this.a(array, 0, n);
    }
    
    public int a(final double[] array, int n, final int n2) throws IOException, bn {
        if (this.for != br.a.if) {
            throw new IOException("Cannot read from WavFile instance");
        }
        for (int i = 0; i < n2; ++i) {
            if (this.else == this.if) {
                return i;
            }
            for (int j = 0; j < this.c; ++j) {
                array[n] = this.e + this.a() / this.f;
                ++n;
            }
            ++this.else;
        }
        return n2;
    }
    
    public int a(final double[][] array, final int n) throws IOException, bn {
        return this.if(array, 0, n);
    }
    
    public int if(final double[][] array, int n, final int n2) throws IOException, bn {
        if (this.for != br.a.if) {
            throw new IOException("Cannot read from WavFile instance");
        }
        for (int i = 0; i < n2; ++i) {
            if (this.else == this.if) {
                return i;
            }
            for (int j = 0; j < this.c; ++j) {
                array[j][n] = this.e + this.a() / this.f;
            }
            ++n;
            ++this.else;
        }
        return n2;
    }
    
    public int a(final double[] array, final int n) throws IOException, bn {
        return this.if(array, 0, n);
    }
    
    public int if(final double[] array, int n, final int n2) throws IOException, bn {
        if (this.for != br.a.a) {
            throw new IOException("Cannot write to WavFile instance");
        }
        for (int i = 0; i < n2; ++i) {
            if (this.else == this.if) {
                return i;
            }
            for (int j = 0; j < this.c; ++j) {
                this.a((long)(this.f * (this.e + array[n])));
                ++n;
            }
            ++this.else;
        }
        return n2;
    }
    
    public int if(final double[][] array, final int n) throws IOException, bn {
        return this.a(array, 0, n);
    }
    
    public int a(final double[][] array, int n, final int n2) throws IOException, bn {
        if (this.for != br.a.a) {
            throw new IOException("Cannot write to WavFile instance");
        }
        for (int i = 0; i < n2; ++i) {
            if (this.else == this.if) {
                return i;
            }
            for (int j = 0; j < this.c; ++j) {
                this.a((long)(this.f * (this.e + array[j][n])));
            }
            ++n;
            ++this.else;
        }
        return n2;
    }
    
    public void if() throws IOException {
        if (this.g != null) {
            this.g.close();
            this.g = null;
        }
        if (this.a != null) {
            if (this.long > 0) {
                this.a.write(this.char, 0, this.long);
            }
            if (this.d) {
                this.a.write(0);
            }
            this.a.close();
            this.a = null;
        }
        this.for = br.a.do;
    }
    
    public void int() {
        this.a(System.out);
    }
    
    public void a(final PrintStream printStream) {
        printStream.printf("File: %s\n", this.h);
        printStream.printf("Channels: %d, Frames: %d\n", new Integer(this.c), new Long(this.if));
        printStream.printf("IO State: %s\n", this.for);
        printStream.printf("Sample Rate: %d, Block Align: %d\n", new Long(this.i), new Integer(this.b));
        printStream.printf("Valid Bits: %d, Bytes per sample: %d\n", new Integer(this.void), new Integer(this.do));
    }
    
    public static void a(final String[] array) {
        if (array.length < 1) {
            System.err.println("Must supply filename");
            System.exit(1);
        }
        try {
            for (int length = array.length, i = 0; i < length; ++i) {
                final br a = a(new File(array[i]));
                a.int();
                final long new1 = a.new();
                final int byte1 = a.byte();
                final br a2 = a(new File("out.wav"), byte1, new1, a.for(), a.try());
                final double[] array2 = new double[5001 * byte1];
                int j;
                do {
                    j = a.if(array2, 5001);
                    System.out.printf("%d %d\n", new Integer(j), new Integer(a2.a(array2, 5001)));
                } while (j != 0);
                a.if();
                a2.if();
            }
            final br a3 = a(new File("out2.wav"), 1, 10L, 23, 44100L);
            a3.a(new double[10], 10);
            a3.if();
        }
        catch (Exception ex) {
            System.err.println(ex);
            ex.printStackTrace();
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    private static final class a implements Serializable, Comparable
    {
        public static final a if;
        public static final a a;
        public static final a do;
        private static final /* synthetic */ a[] _$24004;
        private final /* synthetic */ String _$24006;
        private final /* synthetic */ int _$24005;
        
        private a(final String $24006, final int $24007) {
            this._$24005 = $24007;
            this._$24006 = $24006;
        }
        
        protected final Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }
        
        public int a(final a a) {
            return this._$24005 - a._$24005;
        }
        
        public final boolean equals(final Object o) {
            return this == o;
        }
        
        public final Class do() {
            final Class<? extends a> class1 = this.getClass();
            final Class<? super a> superclass = class1.getSuperclass();
            return (Class)((superclass == ((br.class$java$lang$Object == null) ? (br.class$java$lang$Object = br.class$("java.lang.Object")) : br.class$java$lang$Object)) ? class1 : superclass);
        }
        
        public final int hashCode() {
            return System.identityHashCode(this);
        }
        
        public final String for() {
            return this._$24006;
        }
        
        public final int a() {
            return this._$24005;
        }
        
        public static a a(final String s) {
            return Enum.valueOf((Class<a>)((br.class$webphone$WavFile$IOState == null) ? (br.class$webphone$WavFile$IOState = br.class$("webphone.WavFile$IOState")) : br.class$webphone$WavFile$IOState), s);
        }
        
        public static final a[] if() {
            return br.a._$24004.clone();
        }
        
        static {
            if = new a("READING", 0);
            a = new a("WRITING", 1);
            do = new a("CLOSED", 2);
            _$24004 = new a[] { br.a.if, br.a.a, br.a.do };
        }
        
        public String toString() {
            return this._$24006;
        }
    }
}
