import java.io.InputStream;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class _zf
{
    public boolean p;
    public String p;
    public int p;
    public String d;
    public String a;
    public int d;
    public long p;
    private Date p;
    private long d;
    protected static PixScreen p;
    public _zo p;
    
    public final boolean p(final String s) {
        if (s.length() == 0) {
            return false;
        }
        System.out.println("hostname:" + s);
        if (s.toString().startsWith(this.p.p(1006))) {
            return true;
        }
        String s2 = this.d;
        String d = null;
        int n = -1;
        int i;
        do {
            i = s2.indexOf(32);
            String substring;
            if (i == -1) {
                substring = s2;
                s2 = "";
                if (n == -1) {
                    n = 0;
                }
            }
            else {
                substring = s2.substring(0, i);
                s2 = s2.substring(i + 1, s2.length());
                if (n == -1) {
                    n = 1;
                }
            }
            String s3 = substring.trim();
            try {
                s3 = new URL(s3).getHost();
            }
            catch (MalformedURLException ex) {
                final String string = "http://" + s3;
                try {
                    s3 = new URL(string).getHost();
                }
                catch (MalformedURLException ex2) {
                    s3 = "";
                }
            }
            if (s3.length() > 0 && d == null) {
                d = s3;
            }
            if (s.equalsIgnoreCase(s3)) {
                if (n == 1) {
                    s3 += "*";
                }
                this.d = s3;
                return true;
            }
            if (s.length() >= 4 && s.equalsIgnoreCase(s3.substring(4, s3.length())) && s.startsWith("www")) {
                if (n == 1) {
                    s3 += "*";
                }
                this.d = s3;
                return true;
            }
        } while (i != -1);
        if (d != null) {
            this.d = d;
        }
        return false;
    }
    
    public final synchronized void p(final URL url) {
        String s = url.toString() + "_psk2.ext";
        if (this.p) {
            s = url.toString() + this.p;
        }
        if (this.p(s) == 0) {
            _zf.p.p(this.p.p(1004), 3000);
            this.p = -1;
            return;
        }
        if ((this.p & 0xFFF) == 0x3F3) {
            if (this.p == null) {
                _zf.p.p(this.p.p(1001), 3000);
                this.p = -1;
                return;
            }
            if ((this.d & 0x10000000L) == 0x10000000L) {
                if (this.d < 0) {
                    _zf.p.p(this.p.p(1001), 3000);
                    this.p = -1;
                    return;
                }
            }
            else {
                if ((this.d & 0x20000000L) != 0x20000000L) {
                    _zf.p.p(this.p.p(1004), 3000);
                    this.p = -1;
                    return;
                }
                if (this.d < 0) {
                    _zf.p.p(this.p.p(1001), 3000);
                    this.p = -1;
                    return;
                }
            }
            this.p = new Date().getTime() - this.p.getTime();
            if (this.p < 0L || this.p > this.d * 1000L * 60L * 60L * 24L) {
                _zf.p.p(this.p.p(1000), 3000);
            }
        }
        else {
            switch (this.p & 0xFFF) {
                case 1973: {
                    if ((this.d & 0x10000000L) == 0x10000000L || (this.d & 0x20000000L) == 0x20000000L) {
                        _zf.p.p(this.p.p(1004), 3000);
                        this.p = -1;
                        return;
                    }
                    if (this.d != -1) {
                        _zf.p.p(this.p.p(1004), 3000);
                        this.p = -1;
                        return;
                    }
                    break;
                }
                case 1010: {
                    if ((this.d & 0x10000000L) == 0x10000000L || (this.d & 0x20000000L) == 0x20000000L) {
                        _zf.p.p(this.p.p(1004), 3000);
                        this.p = -1;
                        return;
                    }
                    if (this.d != -1) {
                        _zf.p.p(this.p.p(1004), 3000);
                        this.p = -1;
                        return;
                    }
                    break;
                }
                case 1974: {
                    if ((this.d & 0x10000000L) == 0x10000000L || (this.d & 0x20000000L) == 0x20000000L) {
                        _zf.p.p(this.p.p(1004), 3000);
                        this.p = -1;
                        return;
                    }
                    if (this.d != -1) {
                        _zf.p.p(this.p.p(1004), 3000);
                        this.p = -1;
                        return;
                    }
                    break;
                }
            }
            if ((this.p & 0x1000) != 0x0 && (this.p & 0xFFF) != 0x7B6 && !url.getProtocol().equalsIgnoreCase("File") && !this.p(url.getHost())) {
                _zf.p.p(this.p.p(1002) + this.d, 1000);
                this.p = 0;
            }
        }
    }
    
    public _zf() {
        this.p = false;
    }
    
    public _zf(final _zo p2, final PixScreen p3) {
        this.p = false;
        this.p = p2;
        _zf.p = p3;
    }
    
    private final int p(final String s) {
        URL url;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            return 0;
        }
        byte[] array;
        try {
            final URLConnection openConnection = url.openConnection();
            final InputStream inputStream = openConnection.getInputStream();
            int contentLength = openConnection.getContentLength();
            if (contentLength == -1) {
                contentLength = 1027;
            }
            array = new byte[contentLength];
            for (int i = 0; i < contentLength; i += inputStream.read(array, i, contentLength - i)) {}
            inputStream.close();
        }
        catch (Exception ex2) {
            return 0;
        }
        final byte[] p = this.p.p(array, 1357549999);
        int n = 0;
        if (this.p.p(p, n) != 856110) {
            return 0;
        }
        n += 4;
        int n2 = n + (4 + this.p.p(p, n).length());
        this.p.p(p, n2);
        n2 += 4;
        if (this.p.p(p, n2) > 1) {
            return 0;
        }
        n2 += 4;
        this.p.p(p, n2);
        n2 += 4;
        this.p = this.p.p(p, n2);
        n2 += 4;
        final int p2 = this.p.p(p, n2);
        n2 += 4;
        final int p3 = this.p.p(p, n2);
        n2 += 4;
        final int p4 = this.p.p(p, n2);
        n2 += 4;
        this.p = new Date(p4 - 1900, p3 - 1, p2);
        this.d = this.p.p(p, n2);
        final int n3 = n2 + (4 + this.d.length());
        final int n4 = n3 + (4 + this.p.p(p, n3).length());
        final String p5 = this.p.p(p, n4);
        final int n5 = n4 + (4 + p5.length());
        if ((this.p & 0xFFF) == 0x7B5 || (this.p & 0xFFF) == 0x3F2) {
            String d = p5.trim();
            try {
                d = new URL(d).getHost();
            }
            catch (MalformedURLException ex3) {
                final String string = "http://" + d;
                try {
                    d = new URL(string).getHost();
                }
                catch (MalformedURLException ex4) {}
            }
            this.d = d;
        }
        this.a = this.p.p(p, n5);
        int n6 = n5 + (4 + this.a.length());
        this.p.p(p, n6);
        n6 += 4;
        this.d = this.p.p(p, n6);
        n6 += 4;
        this.d = this.p.p(p, n6);
        n6 += 4;
        return 1;
    }
}
