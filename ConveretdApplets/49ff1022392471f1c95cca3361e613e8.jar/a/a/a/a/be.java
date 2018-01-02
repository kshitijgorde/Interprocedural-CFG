// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.util.Enumeration;
import java.io.IOException;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.Vector;
import java.util.Hashtable;
import java.net.URL;

public class be implements Runnable
{
    private URL if;
    private Hashtable b;
    private Vector goto;
    private o try;
    private ac byte;
    private byte[] void;
    private int long;
    private int a;
    private byte[] else;
    private byte[] char;
    private int for;
    private boolean int;
    private int do;
    private byte[] new;
    private boolean case;
    
    be(final URL if1, final o try1, final ac byte1) {
        this.long = 0;
        this.a = 0;
        this.else = null;
        this.char = null;
        this.for = 0;
        this.int = false;
        this.do = 127;
        this.new = new byte[] { -39, 112, 62, -80, -113, 117, 57, -54, 92, -71, 15, 114, 71, 32, 46, -5, -56, -9, 16, -18, 66, -16, -67, -113, -103, 40, -94, 110, 37, 62, 108, -89, 83, 26, -125, 25, 72, 101, -24, 69, 29, -18, -101, -53, 29, 67, -51, -83, 117, 27, 10, 8, 62, -49, 6, 56, -1, 77, -77, 75, -97, -92, 87, -100, 60, -71, -119, -21, -124, -38, 43, 111, -43, -61, 110, 99, 83, -112, -17, 126, -86, 38, 114, 0, 88, -14, 106, 61, -32, -31, -124, -25, -19, -74, 4, 80, -77, -120, 27, 15, -36, 76, -73, 39, -112, -11, 122, -36, -40, 70, 58, -3, -11, 78, 86, 26, 62, -102, -48, -73, -6, -41, 25, -56, -90, -33, 83, -104 };
        this.case = false;
        this.if = if1;
        this.try = try1;
        this.b = new Hashtable();
        this.goto = new Vector();
        this.byte = byte1;
    }
    
    synchronized ae a(final String m, final ae ae, final boolean b, final boolean do1, final boolean b2) {
        if (ae != null && ae.long != null) {
            for (ae ae2 = ae.long; ae2 != null; ae2 = ae2.long) {
                for (int i = 0; i < ae2.j; ++i) {
                    if (ae2.new[i].m.compareTo(m) == 0) {
                        if (do1 && !ae2.new[i].byte) {
                            ae2.new[i].do = do1;
                            ae2.new[i].g = b2;
                            this.byte.S.if(ae2.new[i]);
                        }
                        ae2.new[i].g = b2;
                        return ae2.new[i];
                    }
                }
            }
        }
        ae ae3 = this.b.get(m);
        if (ae3 != null && do1 && !ae3.byte) {
            ae3.do = do1;
            ae3.g = b2;
            this.byte.S.if(ae3);
            ae3.g = b2;
            return ae3;
        }
        if (ae3 == null && b) {
            return null;
        }
        if (ae3 == null && !b) {
            ae3 = new ae();
            ae3.do = do1;
            ae3.g = b2;
            this.b.put(m, ae3);
            ae3.m = m;
            synchronized (this) {
                this.goto.addElement(ae3);
                if (!this.case) {
                    this.case = true;
                    new Thread(this).start();
                }
            }
        }
        ae3.g = b2;
        return ae3;
    }
    
    public void run() {
        while (true) {
            synchronized (this) {
                if (this.goto.size() == 0) {
                    this.try.do = false;
                    this.case = false;
                    // monitorexit(this)
                    return;
                }
            }
            this.try.do = true;
            final ae ae = this.goto.elementAt(0);
            this.goto.removeElementAt(0);
            this.a(ae);
        }
    }
    
    public void if() {
        this.b.clear();
        System.gc();
    }
    
    public void a(final ae ae) {
        int contentLength = 0;
        InputStream inputStream = null;
        boolean b = false;
        if (ae.m.compareTo("immergui.xml") == 0) {
            try {
                final URLConnection openConnection = this.getClass().getResource(ae.m).openConnection();
                openConnection.setUseCaches(true);
                openConnection.connect();
                contentLength = openConnection.getContentLength();
                final InputStream inputStream2 = openConnection.getInputStream();
                this.try.a(contentLength);
                ae.e = contentLength;
                b = true;
                this.a(ae, contentLength, inputStream2, true);
                inputStream2.close();
                return;
            }
            catch (Exception ex) {
                if (b) {
                    this.try.a(-contentLength);
                }
                inputStream = this.getClass().getResourceAsStream(ae.m);
                if (inputStream != null) {
                    try {
                        int n = 0;
                        for (int i = inputStream.read(); i != -1; i = inputStream.read()) {
                            ++n;
                        }
                        inputStream.close();
                        inputStream = this.getClass().getResourceAsStream(ae.m);
                        ae.k = new byte[n];
                        ae.e = n;
                        for (int j = 0; j < n; ++j) {
                            ae.k[j] = (byte)inputStream.read();
                        }
                        inputStream.close();
                        ae.if = n;
                        ae.b = true;
                        return;
                    }
                    catch (Exception ex2) {}
                }
            }
        }
        try {
            final URLConnection openConnection2 = new URL(this.if, ae.m).openConnection();
            openConnection2.setUseCaches(true);
            openConnection2.connect();
            final int contentLength2 = openConnection2.getContentLength();
            inputStream = new BufferedInputStream(openConnection2.getInputStream());
            this.try.a(contentLength2);
            this.a(ae, ae.e = contentLength2, inputStream, true);
            inputStream.close();
            inputStream = null;
        }
        catch (Exception ex3) {
            System.out.println("Can't download " + ae.m);
            ae.if = 0;
            ae.b = true;
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (Exception ex4) {}
            }
        }
    }
    
    private void a(final ae ae, final int n, final InputStream inputStream, final boolean b) throws Exception {
        if (n < 11) {
            ae.k = new byte[n];
            ae.if = 0;
            while (ae.if < n) {
                ae.k[ae.if] = this.do(inputStream);
                final o try1 = this.try;
                ++try1.bn;
                ++ae.if;
            }
            ae.b = true;
        }
        else {
            ae.k = new byte[11];
            for (int i = 0; i < 11; ++i) {
                ae.k[i] = this.do(inputStream);
                final o try2 = this.try;
                ++try2.bn;
            }
            if (ae.k[0] == 37 && ((ae.k[1] == 73 && ae.k[2] == 86) || (ae.k[1] == 79 && ae.k[2] == 80)) && ae.k[3] == 80 && ae.k[4] == 65 && ae.k[5] == 67 && ae.k[6] == 75 && ae.k[7] == 65 && ae.k[8] == 71 && ae.k[9] == 69 && ae.k[10] == 37) {
                ae.k = null;
                this.a(ae, inputStream, b);
                ae.b = true;
            }
            else if (ae.k[0] == 37 && ((ae.k[1] == 73 && ae.k[2] == 86) || (ae.k[1] == 79 && ae.k[2] == 80)) && ae.k[3] == 67 && ae.k[4] == 82 && ae.k[5] == 89 && ae.k[6] == 80 && ae.k[7] == 84 && ae.k[10] == 37) {
                this.for = 0;
                this.int = true;
                if (ae.k[9] == 49) {
                    this.void = this.new;
                    this.long = 128;
                }
                else {
                    String s = "";
                    if (ae.k[9] == 50) {
                        final int n2 = ae.k[8] & 0xFF;
                        final String file = this.byte.byte.getDocumentBase().getFile();
                        final String substring = file.substring(0, file.lastIndexOf(47) + 1);
                        s = substring.substring(substring.length() - n2, substring.length());
                    }
                    else if (ae.k[9] == 51) {
                        s = String.valueOf(this.byte.byte.getDocumentBase().getHost()) + this.byte.byte.getDocumentBase().getFile();
                    }
                    else if (ae.k[9] == 52) {
                        s = this.byte.byte.getDocumentBase().getHost();
                    }
                    this.void = s.getBytes();
                    this.long = s.length();
                    if (this.long >= 128) {
                        for (int j = 0; j < this.long; ++j) {
                            this.void[j] ^= this.new[j & this.do];
                        }
                    }
                    else {
                        final byte[] void1 = new byte[128];
                        for (int k = 0; k < this.long; ++k) {
                            void1[k] = (byte)(this.void[k] ^ this.new[k & this.do]);
                        }
                        for (int l = this.long; l < 128; ++l) {
                            void1[l] = this.new[l & this.do];
                        }
                        this.void = void1;
                        this.long = 128;
                    }
                }
                ae.k = null;
                this.a = n - 11;
                this.else = new byte[this.long];
                this.char = new byte[this.long];
                this.a(ae, inputStream, b);
                ae.b = true;
                this.long = 0;
                this.a = 0;
                this.for = 0;
                this.int = false;
                this.void = null;
            }
            else {
                if (ae.k[0] == -1 && ae.k[1] == -40 && ae.k[2] == -1 && ae.k[3] == -32 && ae.k[4] == 0 && ae.k[5] == 16 && ae.k[6] == 74 && ae.k[7] == 70 && ae.k[8] == 73 && ae.k[9] == 70) {
                    ae.a = 1;
                    if (ae.do && !ae.byte) {
                        this.byte.S.if(ae);
                    }
                }
                else if (ae.k[0] == 71 && ae.k[1] == 73 && ae.k[2] == 70 && ae.k[3] == 56) {
                    ae.a = 2;
                    if (ae.do && !ae.byte) {
                        this.byte.S.if(ae);
                    }
                }
                else if (ae.k[0] == 60 && ae.k[1] == 63 && ae.k[2] == 120 && ae.k[3] == 109 && ae.k[4] == 108) {
                    ae.a = 3;
                    if (ae.do && !ae.byte) {
                        this.byte.S.if(ae);
                    }
                }
                ae.j = 0;
                final byte[] m = ae.k;
                ae.k = new byte[n];
                for (int n3 = 0; n3 < 11; ++n3) {
                    ae.k[n3] = m[n3];
                }
                final byte[] array = null;
                ae.if = 11;
                while (ae.if < n) {
                    ae.k[ae.if] = this.do(inputStream);
                    final o try3 = this.try;
                    ++try3.bn;
                    ++ae.if;
                }
                ae.b = true;
            }
        }
    }
    
    private void a(final ae long1, final InputStream inputStream, final boolean b) throws Exception {
        final boolean b2 = b & this.int;
        if (b2) {
            this.int = false;
        }
        this.a(inputStream);
        this.a(inputStream);
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
                System.out.println("Can't open current package because of time restriction.");
                return;
            }
        }
        final int a = this.a(inputStream);
        long1.new = new ae[a];
        for (int i = 0; i < a; ++i) {
            long1.new[i] = new ae();
        }
        final byte[] array = new byte[255];
        for (int j = 0; j < a; ++j) {
            byte b3 = this.do(inputStream);
            final o try1 = this.try;
            ++try1.bn;
            int n = 0;
            while (b3 != 0 && b3 != -1) {
                array[n] = b3;
                ++n;
                b3 = this.do(inputStream);
                final o try2 = this.try;
                ++try2.bn;
            }
            long1.new[j].m = new String(array, 0, n);
            long1.new[j].e = this.a(inputStream);
            long1.new[j].long = long1;
        }
        long1.j = a;
        long1.void = true;
        long1.b = true;
        for (int k = 0; k < a; ++k) {
            this.a(long1.new[k], long1.new[k].e, inputStream, false);
        }
    }
    
    private int a(final InputStream inputStream) throws IOException {
        int n = 0;
        for (int i = 0; i < 4; ++i) {
            n = (n << 8 | (this.do(inputStream) & 0xFF));
        }
        return n;
    }
    
    private long if(final InputStream inputStream) throws IOException {
        long n = 0L;
        for (int i = 0; i < 8; ++i) {
            n = (n << 8 | (this.do(inputStream) & 0xFF));
        }
        return n;
    }
    
    public void a() {
        final Enumeration<ae> elements = this.b.elements();
        try {
            while (true) {
                this.if(elements.nextElement());
            }
        }
        catch (Exception ex) {}
    }
    
    private void if(final ae ae) {
        if (ae.j == 0) {
            if (ae.l != null) {
                ae.l.a(0L);
            }
            if (ae.for != null) {
                ae.for.a(0L);
            }
        }
        else {
            for (int i = 0; i < ae.j; ++i) {
                this.if(ae.new[i]);
            }
        }
    }
    
    private byte do(final InputStream inputStream) {
        if (this.int) {
            if (this.for == 0 || this.for == this.long) {
                this.for = 0;
                int n = 0;
                try {
                    int n2 = inputStream.read();
                    while (n2 != -1 && n < this.long) {
                        if (n >= this.a) {
                            break;
                        }
                        this.else[n] = (byte)n2;
                        if (++n >= this.long || n >= this.a) {
                            continue;
                        }
                        n2 = inputStream.read();
                    }
                }
                catch (Exception ex) {}
                this.a -= n;
                int n3 = 0;
                for (int i = 0; i < n; ++i) {
                    final byte b = this.else[i];
                    n3 += ((((b & 0x1) != 0x0) + ((b & 0x2) != 0x0) + ((b & 0x4) != 0x0) + ((b & 0x8) != 0x0) + ((b & 0x10) != 0x0) + ((b & 0x20) != 0x0) + ((b & 0x40) != 0x0) + ((b & 0x80) != 0x0)) ? 1 : 0);
                }
                final int n4 = n3 % n;
                for (int j = 0; j < n; ++j) {
                    this.char[j] = (byte)(this.else[(j - n4 < 0) ? (j - n4 + n) : (j - n4)] ^ this.void[j]);
                }
            }
            ++this.for;
            return this.char[this.for - 1];
        }
        try {
            return (byte)inputStream.read();
        }
        catch (Exception ex2) {
            return 0;
        }
    }
}
