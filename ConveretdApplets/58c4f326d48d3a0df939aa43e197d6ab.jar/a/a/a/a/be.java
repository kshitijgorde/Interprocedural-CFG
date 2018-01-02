// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.util.Enumeration;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.Vector;
import java.util.Hashtable;
import java.net.URL;

public class be implements Runnable
{
    private URL else;
    private Hashtable goto;
    private Vector d;
    private o new;
    private ac long;
    private byte[] e;
    private int void;
    private int for;
    private byte[] case;
    private byte[] a;
    private int do;
    private boolean int;
    private int if;
    private byte[] try;
    private byte[] b;
    private boolean byte;
    private boolean c;
    private boolean char;
    
    public void a() {
        this.else = null;
        if (this.d != null) {
            for (int i = 0; i < this.d.size(); ++i) {
                ((ae)this.d.elementAt(i)).a();
            }
            this.d.removeAllElements();
        }
        this.try = null;
        this.case = null;
        this.a = null;
        this.e = null;
        if (this.goto != null) {
            this.goto.clear();
        }
        this.goto = null;
    }
    
    be(final URL else1, final o new1, final ac long1) {
        this.void = 0;
        this.for = 0;
        this.case = null;
        this.a = null;
        this.do = 0;
        this.int = false;
        this.if = 127;
        this.try = new byte[] { -39, 112, 62, -80, -113, 117, 57, -54, 92, -71, 15, 114, 71, 32, 46, -5, -56, -9, 16, -18, 66, -16, -67, -113, -103, 40, -94, 110, 37, 62, 108, -89, 83, 26, -125, 25, 72, 101, -24, 69, 29, -18, -101, -53, 29, 67, -51, -83, 117, 27, 10, 8, 62, -49, 6, 56, -1, 77, -77, 75, -97, -92, 87, -100, 60, -71, -119, -21, -124, -38, 43, 111, -43, -61, 110, 99, 83, -112, -17, 126, -86, 38, 114, 0, 88, -14, 106, 61, -32, -31, -124, -25, -19, -74, 4, 80, -77, -120, 27, 15, -36, 76, -73, 39, -112, -11, 122, -36, -40, 70, 58, -3, -11, 78, 86, 26, 62, -102, -48, -73, -6, -41, 25, -56, -90, -33, 83, -104 };
        this.byte = false;
        this.c = false;
        this.char = false;
        this.else = else1;
        this.new = new1;
        this.goto = new Hashtable();
        this.d = new Vector();
        this.long = long1;
        this.b = new byte[128];
        for (int i = this.try.length - 1; i >= 0; --i) {
            this.b[this.try.length - 1 - i] = this.try[i];
        }
    }
    
    public void a(final URL else1) {
        this.else = else1;
    }
    
    ae a(final String s, final ae ae, final boolean b, final boolean b2, final boolean b3) {
        return this.a(s, ae, b, b2, b3, null);
    }
    
    synchronized ae a(final String n, final ae ae, final boolean b, final boolean for1, final boolean b2, final ae ae2) {
        if (ae != null && ae.long != null) {
            for (ae ae3 = ae.long; ae3 != null; ae3 = ae3.long) {
                for (int i = 0; i < ae3.k; ++i) {
                    if (ae3.try[i].n.compareTo(n) == 0) {
                        if (for1 && !ae3.try[i].byte) {
                            ae3.try[i].for = for1;
                            ae3.try[i].h = b2;
                            this.long.aa.if(ae3.try[i]);
                        }
                        ae3.try[i].goto = ae2;
                        ae3.try[i].h = b2;
                        return ae3.try[i];
                    }
                }
            }
        }
        ae ae4 = this.goto.get(n);
        if (ae4 != null && for1 && !ae4.byte) {
            ae4.goto = ae2;
            ae4.for = for1;
            ae4.h = b2;
            this.long.aa.if(ae4);
            ae4.h = b2;
            return ae4;
        }
        if (ae4 != null && for1 && ae4.byte) {
            ae4.goto = ae2;
        }
        if (ae4 == null && b) {
            return null;
        }
        if (ae4 == null && !b) {
            ae4 = new ae(this.long);
            ae4.goto = ae2;
            ae4.for = for1;
            ae4.h = b2;
            this.goto.put(n, ae4);
            ae4.n = n;
            synchronized (this) {
                this.d.addElement(ae4);
                if (!this.c) {
                    this.c = true;
                    new Thread(this).start();
                }
            }
        }
        ae4.h = b2;
        return ae4;
    }
    
    public void run() {
        while (!this.long.G) {
            synchronized (this) {
                if (this.d.size() == 0) {
                    if (!this.long.P.try) {
                        this.new.for = false;
                    }
                    this.c = false;
                    // monitorexit(this)
                    return;
                }
            }
            this.new.for = true;
            final ae ae = this.d.elementAt(0);
            this.d.removeElementAt(0);
            this.a(ae);
        }
    }
    
    public void do() {
        this.goto.clear();
        System.gc();
    }
    
    protected void a(final String s) {
        final ae ae = this.goto.get(s);
        if (ae != null) {
            this.goto.remove(s);
            ae.a();
        }
    }
    
    public void a(final ae ae) {
        int contentLength = 0;
        InputStream inputStream = null;
        boolean b = false;
        if (ae.n.compareTo(new String(ac.Y)) == 0) {
            this.byte = true;
            try {
                final URLConnection openConnection = this.getClass().getResource(ae.n).openConnection();
                openConnection.setUseCaches(true);
                openConnection.connect();
                contentLength = openConnection.getContentLength();
                final InputStream inputStream2 = openConnection.getInputStream();
                this.new.a(contentLength);
                ae.e = contentLength;
                b = true;
                this.a(ae, contentLength, inputStream2, true);
                inputStream2.close();
                return;
            }
            catch (Exception ex) {
                if (b) {
                    this.new.a(-contentLength);
                }
                inputStream = this.getClass().getResourceAsStream(ae.n);
                if (inputStream != null) {
                    try {
                        int n = 0;
                        for (int i = inputStream.read(); i != -1; i = inputStream.read()) {
                            ++n;
                        }
                        inputStream.close();
                        inputStream = this.getClass().getResourceAsStream(ae.n);
                        ae.l = new byte[n];
                        ae.e = n;
                        for (int j = 0; j < n; ++j) {
                            ae.l[j] = (byte)inputStream.read();
                        }
                        inputStream.close();
                        ae.do = n;
                        ae.b = true;
                        return;
                    }
                    catch (Exception ex2) {}
                }
            }
        }
        try {
            final URLConnection openConnection2 = new URL(this.else, ae.n).openConnection();
            openConnection2.setUseCaches(true);
            openConnection2.connect();
            final int contentLength2 = openConnection2.getContentLength();
            inputStream = new BufferedInputStream(openConnection2.getInputStream());
            if (contentLength2 == -1) {
                System.out.println("Can't get file size for: " + ae.n);
            }
            else {
                this.char = true;
                this.new.a(contentLength2);
            }
            this.a(ae, ae.e = contentLength2, inputStream, true);
            this.char = false;
            inputStream.close();
            inputStream = null;
            this.byte = false;
        }
        catch (Exception ex3) {
            System.out.println(String.valueOf(new String(ac.az)) + ae.n);
            ae.do = 0;
            ae.b = true;
            ae.char = new af();
            ae.char.do = 1;
            ae.char.else = 1;
            (ae.char.new = new int[1])[0] = 0;
            ae.char.goto = true;
            ae.char.int = true;
            ae.case = true;
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (Exception ex4) {}
            }
            this.byte = false;
        }
    }
    
    private void a(final ae ae, int n, final InputStream inputStream, final boolean b) throws Exception {
        if (n < 11 && n != -1) {
            ae.l = new byte[n];
            ae.do = 0;
            while (ae.do < n) {
                ae.l[ae.do] = (byte)this.if(ae, inputStream, true);
                if (this.char) {
                    final o new1 = this.new;
                    ++new1.by;
                }
                ++ae.do;
            }
            ae.b = true;
        }
        else {
            ae.l = new byte[11];
            for (int i = 0; i < 11; ++i) {
                ae.l[i] = (byte)this.if(ae, inputStream, true);
                if (this.char) {
                    final o new2 = this.new;
                    ++new2.by;
                }
            }
            if (!this.byte && ae.l[0] == 37 && ((ae.l[1] == 73 && ae.l[2] == 86) || (ae.l[1] == 79 && ae.l[2] == 80)) && ae.l[3] == 80 && ae.l[4] == 65 && ae.l[5] == 67 && ae.l[6] == 75 && ae.l[7] == 65 && ae.l[8] == 71 && ae.l[9] == 69 && ae.l[10] == 37) {
                ae.l = null;
                this.a(inputStream);
                if (this.a(inputStream) >= 1) {
                    if (!this.char) {
                        n = this.a(inputStream);
                        ae.e = n;
                        this.new.a(n);
                        this.char = true;
                    }
                    else {
                        this.a(inputStream);
                    }
                }
                this.a(ae, inputStream, b);
                ae.b = true;
            }
            else if (this.byte || (ae.l[0] == 37 && ((ae.l[1] == 73 && ae.l[2] == 86) || (ae.l[1] == 79 && ae.l[2] == 80)) && ae.l[3] == 67 && ae.l[4] == 82 && ae.l[5] == 89 && ae.l[6] == 80 && ae.l[7] == 84 && ae.l[10] == 37)) {
                this.a(inputStream);
                this.a(inputStream);
                if (!this.char) {
                    n = this.a(inputStream);
                    ae.e = n;
                    this.new.a(n);
                    this.char = true;
                }
                else {
                    this.a(inputStream);
                }
                if (!this.int) {
                    this.do = 0;
                    this.int = true;
                    if (ae.l[9] == 49 || this.byte) {
                        if (this.byte) {
                            this.e = this.b;
                        }
                        else {
                            this.e = this.try;
                        }
                        this.byte = false;
                        this.void = 128;
                    }
                    else {
                        String s = "";
                        if (ae.l[9] == 50) {
                            final int n2 = ae.l[8] & 0xFF;
                            final String file = this.long.case.getDocumentBase().getFile();
                            final String substring = file.substring(0, file.lastIndexOf(47) + 1);
                            if (n2 > substring.length()) {
                                s = "";
                            }
                            else {
                                s = substring.substring(substring.length() - n2, substring.length());
                            }
                        }
                        else if (ae.l[9] == 51) {
                            s = String.valueOf(this.long.case.getDocumentBase().getHost()) + this.long.case.getDocumentBase().getFile();
                            if (!s.endsWith("/")) {
                                s = s.substring(0, s.lastIndexOf(47) + 1);
                            }
                        }
                        else if (ae.l[9] == 52) {
                            s = this.long.case.getDocumentBase().getHost();
                        }
                        this.e = s.getBytes();
                        this.void = s.length();
                        if (this.void >= 128) {
                            for (int j = 0; j < this.void; ++j) {
                                this.e[j] ^= this.try[j & this.if];
                            }
                        }
                        else {
                            final byte[] e = new byte[128];
                            for (int k = 0; k < this.void; ++k) {
                                e[k] = (byte)(this.e[k] ^ this.try[k & this.if]);
                            }
                            for (int l = this.void; l < 128; ++l) {
                                e[l] = this.try[l & this.if];
                            }
                            this.e = e;
                            this.void = 128;
                        }
                    }
                }
                ae.l = null;
                this.for = n - 11;
                this.case = new byte[this.void];
                this.a = new byte[this.void];
                for (int n3 = 0; n3 < this.void; ++n3) {
                    if ((byte)this.if(ae, inputStream, false) != -1) {
                        System.out.println(String.valueOf(new String(ac.int)) + ae.n + ".");
                        return;
                    }
                }
                this.a(ae, inputStream, b);
                ae.b = true;
                this.void = 0;
                this.for = 0;
                this.do = 0;
                this.int = false;
                this.e = null;
            }
            else {
                if ((ae.l[0] == -1 && ae.l[1] == -40 && ae.l[2] == -1 && ae.l[3] == -32 && ae.l[4] == 0) || (ae.l[0] == -1 && ae.l[1] == -40 && ae.l[2] == -1 && ae.l[3] == -31 && ae.l[4] == 0)) {
                    ae.a = 1;
                    if (ae.for && !ae.byte) {
                        this.long.aa.if(ae);
                    }
                }
                else if (ae.l[0] == 71 && ae.l[1] == 73 && ae.l[2] == 70 && ae.l[3] == 56) {
                    ae.a = 2;
                    if (ae.for && !ae.byte) {
                        this.long.aa.if(ae);
                    }
                }
                else if (ae.l[0] == 60 && ae.l[1] == 63 && ae.l[2] == 120 && ae.l[3] == 109 && ae.l[4] == 108) {
                    ae.a = 3;
                    if (ae.for && !ae.byte) {
                        this.long.aa.if(ae);
                    }
                }
                ae.k = 0;
                final byte[] m = ae.l;
                if (n == -1) {
                    ae.l = new byte[2048];
                }
                else {
                    ae.l = new byte[n];
                }
                for (int n4 = 0; n4 < 11; ++n4) {
                    ae.l[n4] = m[n4];
                }
                final byte[] array = null;
                if (n == -1) {
                    ae.do = 11;
                    for (int n5 = this.if(ae, inputStream, true); n5 != -1; n5 = this.if(ae, inputStream, true)) {
                        if (this.long.G) {
                            return;
                        }
                        ae.l[ae.do] = (byte)n5;
                        ++ae.do;
                    }
                }
                else {
                    ae.do = 11;
                    while (ae.do < n) {
                        if (this.long.G) {
                            return;
                        }
                        ae.l[ae.do] = (byte)this.if(ae, inputStream, false);
                        final o new3 = this.new;
                        ++new3.by;
                        ++ae.do;
                    }
                }
                if (!this.char && ae.l.length != ae.do) {
                    synchronized (ae) {
                        final byte[] l2 = new byte[ae.do];
                        System.arraycopy(ae.l, 0, l2, 0, ae.do);
                        ae.l = null;
                        ae.l = l2;
                        System.gc();
                    }
                }
                ae.e = ae.do;
                ae.b = true;
            }
        }
        this.long.a(ae);
    }
    
    private void a(final ae long1, final InputStream inputStream, final boolean b) throws Exception {
        int n = 0;
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
                System.out.println(ac.A);
                return;
            }
        }
        final int a = this.a(inputStream);
        long1.try = new ae[a];
        for (int i = 0; i < a; ++i) {
            long1.try[i] = new ae(this.long);
        }
        if (long1.for) {
            long1.try[0].for = true;
        }
        final byte[] array = new byte[255];
        for (int j = 0; j < a; ++j) {
            int n2 = this.if(long1, inputStream, false);
            if (this.char) {
                final o new1 = this.new;
                ++new1.by;
            }
            int n3 = 0;
            while (n2 != 0 && n2 != -1) {
                if (this.long.G) {
                    return;
                }
                array[n3] = (byte)n2;
                ++n3;
                n2 = this.if(long1, inputStream, false);
                if (!this.char) {
                    continue;
                }
                final o new2 = this.new;
                ++new2.by;
            }
            long1.try[j].n = new String(array, 0, n3);
            long1.try[j].e = this.a(inputStream);
            if (b && !this.char) {
                n += long1.try[j].e;
            }
            long1.try[j].long = long1;
        }
        long1.k = a;
        long1.void = true;
        long1.b = true;
        if (b && !this.char) {
            this.new.a(n);
            this.char = true;
        }
        for (int k = 0; k < a; ++k) {
            if (this.long.G) {
                return;
            }
            this.a(long1.try[k], long1.try[k].e, inputStream, false);
        }
    }
    
    private int a(final InputStream inputStream) {
        int n = 0;
        for (int i = 0; i < 4; ++i) {
            n = (n << 8 | ((byte)this.if(null, inputStream, false) & 0xFF));
        }
        return n;
    }
    
    private long if(final InputStream inputStream) {
        long n = 0L;
        for (int i = 0; i < 8; ++i) {
            n = (n << 8 | ((byte)this.if(null, inputStream, false) & 0xFF));
        }
        return n;
    }
    
    public void if() {
        final Enumeration<ae> elements = this.goto.elements();
        try {
            while (true) {
                this.if(elements.nextElement());
            }
        }
        catch (Exception ex) {}
    }
    
    private void if(final ae ae) {
        if (ae.k == 0) {
            if (ae.m != null) {
                ae.m.a(0L);
            }
            if (ae.int != null) {
                ae.int.a(0L);
            }
        }
        else {
            for (int i = 0; i < ae.k; ++i) {
                this.if(ae.try[i]);
            }
        }
    }
    
    private int if(final ae ae, final InputStream inputStream, final boolean b) {
        if (b && ae != null && ae.do >= ae.l.length) {
            synchronized (ae) {
                final byte[] l = new byte[ae.do + 1048576];
                System.arraycopy(ae.l, 0, l, 0, ae.do);
                ae.l = null;
                ae.l = l;
                System.gc();
            }
        }
        if (this.int) {
            if (this.do == 0 || this.do == this.void) {
                this.do = 0;
                int n = 0;
                try {
                    int n2 = inputStream.read();
                    while (n2 != -1 && n < this.void) {
                        if (n >= this.for) {
                            break;
                        }
                        this.case[n] = (byte)n2;
                        if (++n >= this.void || n >= this.for) {
                            continue;
                        }
                        n2 = inputStream.read();
                    }
                }
                catch (Exception ex) {}
                this.for -= n;
                int n3 = 0;
                for (int i = 0; i < n; ++i) {
                    final byte b2 = this.case[i];
                    n3 += ((((b2 & 0x1) != 0x0) + ((b2 & 0x2) != 0x0) + ((b2 & 0x4) != 0x0) + ((b2 & 0x8) != 0x0) + ((b2 & 0x10) != 0x0) + ((b2 & 0x20) != 0x0) + ((b2 & 0x40) != 0x0) + ((b2 & 0x80) != 0x0)) ? 1 : 0);
                }
                final int n4 = n3 % n;
                for (int j = 0; j < n; ++j) {
                    this.a[j] = (byte)(this.case[(j - n4 < 0) ? (j - n4 + n) : (j - n4)] ^ this.e[j]);
                }
            }
            ++this.do;
            return 0xFF & this.a[this.do - 1];
        }
        try {
            return inputStream.read();
        }
        catch (Exception ex2) {
            return 0;
        }
    }
}
