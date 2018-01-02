import java.net.URLConnection;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.Vector;
import java.util.Hashtable;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class k implements Runnable
{
    private URL if;
    private Hashtable c;
    private Vector long;
    private i byte;
    private l case;
    private byte[] b;
    private int void;
    private int a;
    private byte[] goto;
    private byte[] else;
    private int for;
    private boolean int;
    private int do;
    private byte[] try;
    private boolean char;
    private boolean new;
    
    public void a() {
        this.if = null;
        if (this.long != null) {
            for (int i = 0; i < this.long.size(); ++i) {
                ((aa)this.long.elementAt(i)).a();
            }
            this.long.removeAllElements();
        }
        this.try = null;
        this.goto = null;
        this.else = null;
        this.b = null;
        if (this.c != null) {
            this.c.clear();
        }
        this.c = null;
    }
    
    k(final URL if1, final i byte1, final l case1) {
        this.void = 0;
        this.a = 0;
        this.goto = null;
        this.else = null;
        this.for = 0;
        this.int = false;
        this.do = 127;
        this.try = new byte[] { -39, 112, 62, -80, -113, 117, 57, -54, 92, -71, 15, 114, 71, 32, 46, -5, -56, -9, 16, -18, 66, -16, -67, -113, -103, 40, -94, 110, 37, 62, 108, -89, 83, 26, -125, 25, 72, 101, -24, 69, 29, -18, -101, -53, 29, 67, -51, -83, 117, 27, 10, 8, 62, -49, 6, 56, -1, 77, -77, 75, -97, -92, 87, -100, 60, -71, -119, -21, -124, -38, 43, 111, -43, -61, 110, 99, 83, -112, -17, 126, -86, 38, 114, 0, 88, -14, 106, 61, -32, -31, -124, -25, -19, -74, 4, 80, -77, -120, 27, 15, -36, 76, -73, 39, -112, -11, 122, -36, -40, 70, 58, -3, -11, 78, 86, 26, 62, -102, -48, -73, -6, -41, 25, -56, -90, -33, 83, -104 };
        this.char = false;
        this.new = false;
        this.if = if1;
        this.byte = byte1;
        this.c = new Hashtable();
        this.long = new Vector();
        this.case = case1;
    }
    
    aa a(final String s, final aa aa, final boolean b, final boolean b2, final boolean b3) {
        return this.a(s, aa, b, b2, b3, null);
    }
    
    synchronized aa a(final String l, final aa aa, final boolean b, final boolean do1, final boolean b2, final aa aa2) {
        if (aa != null && aa.goto != null) {
            for (aa aa3 = aa.goto; aa3 != null; aa3 = aa3.goto) {
                for (int i = 0; i < aa3.j; ++i) {
                    if (aa3.int[i].l.compareTo(l) == 0) {
                        if (do1 && !aa3.int[i].try) {
                            aa3.int[i].do = do1;
                            aa3.int[i].g = b2;
                            this.case.o.if(aa3.int[i]);
                        }
                        aa3.int[i].else = aa2;
                        aa3.int[i].g = b2;
                        return aa3.int[i];
                    }
                }
            }
        }
        aa aa4 = this.c.get(l);
        if (aa4 != null && do1 && !aa4.try) {
            aa4.else = aa2;
            aa4.do = do1;
            aa4.g = b2;
            this.case.o.if(aa4);
            aa4.g = b2;
            return aa4;
        }
        if (aa4 != null && do1 && aa4.try) {
            aa4.else = aa2;
            final aa aa5 = aa4;
            ++aa5.i;
        }
        if (aa4 == null && b) {
            return null;
        }
        if (aa4 == null && !b) {
            aa4 = new aa(this.case);
            aa4.else = aa2;
            aa4.do = do1;
            aa4.g = b2;
            this.c.put(l, aa4);
            aa4.l = l;
            synchronized (this) {
                this.long.addElement(aa4);
                if (!this.char) {
                    this.char = true;
                    new Thread(this).start();
                }
            }
        }
        aa4.g = b2;
        return aa4;
    }
    
    public void run() {
        while (!this.case.h) {
            synchronized (this) {
                if (this.long.size() == 0) {
                    this.byte.try = false;
                    this.char = false;
                    // monitorexit(this)
                    return;
                }
            }
            this.byte.try = true;
            final aa aa = this.long.elementAt(0);
            this.long.removeElementAt(0);
            this.a(aa);
        }
    }
    
    public void if() {
        this.c.clear();
        System.gc();
    }
    
    public void a(final aa aa) {
        int contentLength = 0;
        InputStream inputStream = null;
        boolean b = false;
        Label_0272: {
            if (aa.l.compareTo("immerlg.gif") != 0 && aa.l.compareTo("immerbt3.gif") != 0 && aa.l.compareTo("immerbt2.gif") != 0 && aa.l.compareTo("immerbar.gif") != 0) {
                if (aa.l.compareTo("immerfs.gif") != 0) {
                    break Label_0272;
                }
            }
            try {
                final URLConnection openConnection = this.getClass().getResource(aa.l).openConnection();
                openConnection.setUseCaches(true);
                openConnection.connect();
                contentLength = openConnection.getContentLength();
                final InputStream inputStream2 = openConnection.getInputStream();
                this.byte.a(contentLength);
                aa.d = contentLength;
                b = true;
                this.a(aa, contentLength, inputStream2, true);
                inputStream2.close();
                return;
            }
            catch (Exception ex) {
                if (b) {
                    this.byte.a(-contentLength);
                }
                inputStream = this.getClass().getResourceAsStream(aa.l);
                if (inputStream != null) {
                    try {
                        int n = 0;
                        for (int i = inputStream.read(); i != -1; i = inputStream.read()) {
                            ++n;
                        }
                        inputStream.close();
                        inputStream = this.getClass().getResourceAsStream(aa.l);
                        aa.k = new byte[n];
                        aa.d = n;
                        for (int j = 0; j < n; ++j) {
                            aa.k[j] = (byte)inputStream.read();
                        }
                        inputStream.close();
                        aa.if = n;
                        aa.void = true;
                        return;
                    }
                    catch (Exception ex2) {}
                }
            }
            try {
                final URLConnection openConnection2 = new URL(this.if, aa.l).openConnection();
                openConnection2.setUseCaches(true);
                openConnection2.connect();
                final int contentLength2 = openConnection2.getContentLength();
                inputStream = new BufferedInputStream(openConnection2.getInputStream());
                if (contentLength2 == -1) {
                    System.out.println("Can't get file size for: " + aa.l);
                }
                else {
                    this.new = true;
                    this.byte.a(contentLength2);
                }
                this.a(aa, aa.d = contentLength2, inputStream, true);
                this.new = false;
                inputStream.close();
                inputStream = null;
            }
            catch (Exception ex3) {
                System.out.println("Can't download " + aa.l);
                aa.if = 0;
                aa.void = true;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    }
                    catch (Exception ex4) {}
                }
            }
        }
    }
    
    private void a(final aa aa, int n, final InputStream inputStream, final boolean b) throws Exception {
        if (n < 11 && n != -1) {
            aa.k = new byte[n];
            aa.if = 0;
            while (aa.if < n) {
                aa.k[aa.if] = (byte)this.a(aa, inputStream, true);
                if (this.new) {
                    final i byte1 = this.byte;
                    ++byte1.do;
                }
                ++aa.if;
            }
            aa.void = true;
        }
        else {
            aa.k = new byte[11];
            for (int i = 0; i < 11; ++i) {
                aa.k[i] = (byte)this.a(aa, inputStream, true);
                if (this.new) {
                    final i byte2 = this.byte;
                    ++byte2.do;
                }
            }
            if (aa.k[0] == 37 && ((aa.k[1] == 73 && aa.k[2] == 86) || (aa.k[1] == 79 && aa.k[2] == 80)) && aa.k[3] == 80 && aa.k[4] == 65 && aa.k[5] == 67 && aa.k[6] == 75 && aa.k[7] == 65 && aa.k[8] == 71 && aa.k[9] == 69 && aa.k[10] == 37) {
                aa.k = null;
                this.a(inputStream);
                if (this.a(inputStream) >= 1) {
                    if (!this.new) {
                        n = this.a(inputStream);
                        aa.d = n;
                        this.byte.a(n);
                        this.new = true;
                    }
                    else {
                        this.a(inputStream);
                    }
                }
                this.if(aa, inputStream, b);
                aa.void = true;
            }
            else if (aa.k[0] == 37 && ((aa.k[1] == 73 && aa.k[2] == 86) || (aa.k[1] == 79 && aa.k[2] == 80)) && aa.k[3] == 67 && aa.k[4] == 82 && aa.k[5] == 89 && aa.k[6] == 80 && aa.k[7] == 84 && aa.k[10] == 37) {
                this.for = 0;
                this.a(inputStream);
                this.a(inputStream);
                if (!this.new) {
                    n = this.a(inputStream);
                    aa.d = n;
                    this.byte.a(n);
                    this.new = true;
                }
                else {
                    this.a(inputStream);
                }
                this.int = true;
                if (aa.k[9] == 49) {
                    this.b = this.try;
                    this.void = 128;
                }
                else {
                    String s = "";
                    if (aa.k[9] == 50) {
                        final int n2 = aa.k[8] & 0xFF;
                        final String file = this.case.try.getDocumentBase().getFile();
                        final String substring = file.substring(0, file.lastIndexOf(47) + 1);
                        if (n2 > substring.length()) {
                            s = "";
                        }
                        else {
                            s = substring.substring(substring.length() - n2, substring.length());
                        }
                    }
                    else if (aa.k[9] == 51) {
                        s = String.valueOf(this.case.try.getDocumentBase().getHost()) + this.case.try.getDocumentBase().getFile();
                        if (!s.endsWith("/")) {
                            s = s.substring(0, s.lastIndexOf(47) + 1);
                        }
                    }
                    else if (aa.k[9] == 52) {
                        s = this.case.try.getDocumentBase().getHost();
                    }
                    this.b = s.getBytes();
                    this.void = s.length();
                    if (this.void >= 128) {
                        for (int j = 0; j < this.void; ++j) {
                            this.b[j] ^= this.try[j & this.do];
                        }
                    }
                    else {
                        final byte[] b2 = new byte[128];
                        for (int k = 0; k < this.void; ++k) {
                            b2[k] = (byte)(this.b[k] ^ this.try[k & this.do]);
                        }
                        for (int l = this.void; l < 128; ++l) {
                            b2[l] = this.try[l & this.do];
                        }
                        this.b = b2;
                        this.void = 128;
                    }
                }
                aa.k = null;
                this.a = n - 11;
                this.goto = new byte[this.void];
                this.else = new byte[this.void];
                for (int n3 = 0; n3 < this.void; ++n3) {
                    if ((byte)this.a(aa, inputStream, false) != -1) {
                        System.out.println(l.void + aa.l + ".");
                        return;
                    }
                }
                this.if(aa, inputStream, b);
                aa.void = true;
                this.void = 0;
                this.a = 0;
                this.for = 0;
                this.int = false;
                this.b = null;
            }
            else {
                if (aa.k[0] == -1 && aa.k[1] == -40 && aa.k[2] == -1 && aa.k[3] == -32 && aa.k[4] == 0 && aa.k[5] == 16 && aa.k[6] == 74 && aa.k[7] == 70 && aa.k[8] == 73 && aa.k[9] == 70) {
                    aa.a = 1;
                    if (aa.do && !aa.try) {
                        this.case.o.if(aa);
                    }
                }
                else if (aa.k[0] == 71 && aa.k[1] == 73 && aa.k[2] == 70 && aa.k[3] == 56) {
                    aa.a = 2;
                    if (aa.do && !aa.try) {
                        this.case.o.if(aa);
                    }
                }
                else {
                    aa.byte = true;
                }
                aa.j = 0;
                final byte[] m = aa.k;
                if (n == -1) {
                    aa.k = new byte[2048];
                }
                else {
                    aa.k = new byte[n];
                }
                for (int n4 = 0; n4 < 11; ++n4) {
                    aa.k[n4] = m[n4];
                }
                final byte[] array = null;
                if (n == -1) {
                    aa.if = 11;
                    for (int n5 = this.a(aa, inputStream, true); n5 != -1; n5 = this.a(aa, inputStream, true)) {
                        if (this.case.h) {
                            return;
                        }
                        aa.k[aa.if] = (byte)n5;
                        ++aa.if;
                    }
                }
                else {
                    aa.if = 11;
                    while (aa.if < n) {
                        if (this.case.h) {
                            return;
                        }
                        aa.k[aa.if] = (byte)this.a(aa, inputStream, false);
                        final i byte3 = this.byte;
                        ++byte3.do;
                        ++aa.if;
                    }
                }
                if (!this.new && aa.k.length != aa.if) {
                    synchronized (aa) {
                        final byte[] k2 = new byte[aa.if];
                        System.arraycopy(aa.k, 0, k2, 0, aa.if);
                        aa.k = null;
                        aa.k = k2;
                        System.gc();
                    }
                }
                aa.d = aa.if;
                aa.void = true;
            }
        }
    }
    
    private void if(final aa goto1, final InputStream inputStream, final boolean b) throws Exception {
        final boolean b2 = b & this.int;
        if (b2) {
            this.int = false;
        }
        int n = 0;
        if (b2 && b) {
            this.int = true;
        }
        if (this.int && b) {
            long if1 = this.if(inputStream);
            if (if1 < 0L) {
                if1 = 0L;
            }
            long if2 = this.if(inputStream);
            if (if2 < 0L) {
                if2 = Long.MAX_VALUE;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < if1 || currentTimeMillis > if2) {
                System.out.println(l.f);
                return;
            }
        }
        final int a = this.a(inputStream);
        goto1.int = new aa[a];
        for (int i = 0; i < a; ++i) {
            goto1.int[i] = new aa(this.case);
        }
        final byte[] array = new byte[255];
        for (int j = 0; j < a; ++j) {
            int n2 = this.a(goto1, inputStream, false);
            if (this.new) {
                final i byte1 = this.byte;
                ++byte1.do;
            }
            int n3 = 0;
            while (n2 != 0 && n2 != -1) {
                if (this.case.h) {
                    return;
                }
                array[n3] = (byte)n2;
                ++n3;
                n2 = this.a(goto1, inputStream, false);
                if (!this.new) {
                    continue;
                }
                final i byte2 = this.byte;
                ++byte2.do;
            }
            goto1.int[j].l = new String(array, 0, n3);
            goto1.int[j].d = this.a(inputStream);
            if (b && !this.new) {
                n += goto1.int[j].d;
            }
            goto1.int[j].goto = goto1;
        }
        goto1.j = a;
        goto1.long = true;
        goto1.void = true;
        if (b && !this.new) {
            this.byte.a(n);
            this.new = true;
        }
        for (int k = 0; k < a; ++k) {
            if (this.case.h) {
                return;
            }
            this.a(goto1.int[k], goto1.int[k].d, inputStream, false);
        }
    }
    
    private int a(final InputStream inputStream) {
        int n = 0;
        for (int i = 0; i < 4; ++i) {
            n = (n << 8 | ((byte)this.a(null, inputStream, false) & 0xFF));
        }
        return n;
    }
    
    private long if(final InputStream inputStream) {
        long n = 0L;
        for (int i = 0; i < 8; ++i) {
            n = (n << 8 | ((byte)this.a(null, inputStream, false) & 0xFF));
        }
        return n;
    }
    
    private int a(final aa aa, final InputStream inputStream, final boolean b) {
        if (b && aa != null && aa.if >= aa.k.length) {
            synchronized (aa) {
                final byte[] k = new byte[aa.if + 1048576];
                System.arraycopy(aa.k, 0, k, 0, aa.if);
                aa.k = null;
                aa.k = k;
                System.gc();
            }
        }
        if (this.int) {
            if (this.for == 0 || this.for == this.void) {
                this.for = 0;
                int n = 0;
                try {
                    int n2 = inputStream.read();
                    while (n2 != -1 && n < this.void) {
                        if (n >= this.a) {
                            break;
                        }
                        this.goto[n] = (byte)n2;
                        if (++n >= this.void || n >= this.a) {
                            continue;
                        }
                        n2 = inputStream.read();
                    }
                }
                catch (Exception ex) {}
                this.a -= n;
                int n3 = 0;
                for (int i = 0; i < n; ++i) {
                    final byte b2 = this.goto[i];
                    n3 += ((((b2 & 0x1) != 0x0) + ((b2 & 0x2) != 0x0) + ((b2 & 0x4) != 0x0) + ((b2 & 0x8) != 0x0) + ((b2 & 0x10) != 0x0) + ((b2 & 0x20) != 0x0) + ((b2 & 0x40) != 0x0) + ((b2 & 0x80) != 0x0)) ? 1 : 0);
                }
                final int n4 = n3 % n;
                for (int j = 0; j < n; ++j) {
                    this.else[j] = (byte)(this.goto[(j - n4 < 0) ? (j - n4 + n) : (j - n4)] ^ this.b[j]);
                }
            }
            ++this.for;
            return 0xFF & this.else[this.for - 1];
        }
        try {
            return inputStream.read();
        }
        catch (Exception ex2) {
            return 0;
        }
    }
}
