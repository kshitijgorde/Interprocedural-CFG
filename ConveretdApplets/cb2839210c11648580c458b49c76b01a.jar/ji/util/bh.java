// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import ji.document.ad;
import ji.io.q;
import ji.net.gh;
import java.net.URLEncoder;
import java.io.PrintStream;
import ji.v1event.a6;
import ji.io.ac;
import java.io.IOException;
import ji.v1event.af;
import ji.io.h;
import ji.net.cookie.bc;
import java.net.URL;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.URLConnection;

public class bh
{
    private URLConnection a;
    private boolean b;
    private boolean c;
    private String d;
    private int e;
    private Object f;
    private InputStream g;
    private OutputStream h;
    private static boolean i;
    static /* synthetic */ Class j;
    static /* synthetic */ Class k;
    
    public bh(final URL url, final String d, final boolean c, final int e, final Object f, final boolean b) {
        this.b = false;
        this.d = d;
        this.f = f;
        this.c = c;
        this.e = e;
        try {
            this.a = ji.util.d.b(url, false, d);
            if (c) {
                this.a.setDoOutput(true);
                this.a.setDoInput(true);
                this.a.setRequestProperty("Content-type", "text/plain");
                this.a.setRequestProperty("Content-length", "".concat(String.valueOf(String.valueOf(e))));
                ji.util.d.a(this.a, d);
                bc.c(this.a, d);
                this.a.setDoOutput(true);
                this.a.setAllowUserInteraction(false);
            }
            else {
                this.a.setDoOutput(true);
                this.a.setDoInput(true);
                if (ji.util.d.bf()) {
                    this.a.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                }
                ji.util.d.a(this.a, d);
                bc.c(this.a, d);
                this.a.setAllowUserInteraction(true);
            }
            if (b) {
                this.a.setRequestProperty("Content-Encoding", "gzip");
            }
        }
        catch (Exception ex) {
            ji.io.h.d(d, "Failed to initialise connection to ".concat(String.valueOf(String.valueOf(url))));
            ji.util.d.a(ex);
            this.b = true;
        }
    }
    
    private void a(final af af) throws Exception {
        if (!this.b && this.h == null) {
            this.h = this.a.getOutputStream();
            if (this.h == null) {
                if (ji.util.d.cy()) {
                    if (this.c) {
                        ji.io.h.d(this.d, "Send failed (Servlet did not respond)");
                    }
                    else {
                        ji.io.h.d(this.d, "Send failed (POST did not respond)");
                    }
                }
                a(af, 0, this);
                throw new Exception("output stream failed to initialise");
            }
            if (ji.util.d.cy()) {
                ji.io.h.d(this.d, "Output stream established...");
            }
        }
    }
    
    private InputStream g() throws IOException {
        if (!this.b && this.g == null) {
            this.g = this.a.getInputStream();
        }
        return this.g;
    }
    
    public final void a(final af af, final ac ac) throws Exception, IOException {
        if (!this.b) {
            int i = 0;
            final int e = this.e;
            int min = Math.min(2048, this.e);
            int n = 0;
            final byte[] array = new byte[min];
            if (this.h == null) {
                this.a(af);
            }
            if (this.h == null) {
                throw new Exception("Failed to open output stream to ".concat(String.valueOf(String.valueOf(this.a.getURL()))));
            }
            while (i < this.e) {
                if (ji.util.d.cy()) {
                    ji.io.h.d(this.d, String.valueOf(String.valueOf(new StringBuffer("Sending buffer of ").append(min).append(" bytes..."))));
                }
                ac.a(array);
                if (min + n > e) {
                    min = e - n;
                }
                this.h.write(array);
                this.h.flush();
                i += min;
                n += min;
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.d, String.valueOf(String.valueOf(new StringBuffer("Sent ").append(min).append(" bytes (").append(i).append(" out of ").append(this.e).append(")"))));
                }
                a(af, 100 * (this.e - (e + min)) / this.e, this);
            }
        }
    }
    
    public final void a(final af af, final byte[] array) throws Exception {
        if (!this.b) {
            int i = 0;
            final int e = this.e;
            int min = Math.min(2048, this.e);
            int n = 0;
            this.a(af);
            if (this.h == null) {
                throw new Exception("Failed to open output stream to ".concat(String.valueOf(String.valueOf(this.a.getURL()))));
            }
            while (i < this.e) {
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.d, String.valueOf(String.valueOf(new StringBuffer("Sending buffer of ").append(min).append(" bytes..."))));
                }
                if (min + n > e) {
                    min = e - n;
                }
                this.h.write(array, n, min);
                this.h.flush();
                i += min;
                n += min;
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.d, String.valueOf(String.valueOf(new StringBuffer("Sent ").append(min).append(" bytes (").append(i).append(" out of ").append(this.e).append(")"))));
                }
                a(af, new a6(this, 14, "".concat(String.valueOf(String.valueOf(100 * i / this.e)))), this);
            }
        }
    }
    
    public final URLConnection a() {
        return this.a;
    }
    
    public final void a(final af af, final ac ac, final boolean b) throws Exception, IOException {
        if (!this.b) {
            this.a(af, new w4(ac));
        }
    }
    
    public final void a(final af af, final byte[] array, final boolean b, final boolean b2, final String s, final String s2, final boolean b3) throws Exception {
        if (ji.util.d.cs()) {
            ji.io.h.d(this.d, "annotation send to post");
        }
        if (!this.b) {
            final w7 w7 = new w7(array);
            if (b3) {
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.d, "sending zipped data");
                }
                this.a(af, w7);
            }
            else {
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.d, "sending unzipped data");
                }
                this.a(af, w7, b, b2, s, s2);
            }
        }
    }
    
    private void a(final af af, final w5 w5) throws Exception {
        PrintStream printStream = null;
        try {
            a(af, 0, this);
            this.a(af);
            if (this.h == null) {
                throw new Exception("Failed to open output stream to ".concat(String.valueOf(String.valueOf(this.a.getURL()))));
            }
            int ar = ji.util.d.ar;
            final int a = w5.a();
            if (a > 0) {
                if (ar > a) {
                    ar = a;
                }
                final int n = a / ar + 1;
                ji.util.d.bm(this.d);
                int n2 = 0;
                int n3 = 0;
                printStream = new PrintStream(this.h);
                final String b = ji.util.d.b(997, this.d);
                af.a(new a6(this, 1, b));
                final a6 a2 = new a6(this, 4, "0");
                final double n4 = 100 / n;
                int n5 = 0;
                for (int i = 0; i < n; ++i) {
                    if (af != null) {
                        af.a(a2);
                    }
                    final byte[] a3 = w5.a(n2, ar);
                    try {
                        printStream.write(a3);
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    n2 += ar;
                    n3 += a3.length;
                    if (n2 > a) {
                        n2 = a;
                    }
                    if (n2 + ar > a) {
                        ar = a - n2;
                    }
                    n5 += (int)n4;
                    a2.a(String.valueOf(n5));
                    if (af != null) {
                        af.a(new a6(this, 1, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b))).append(" ").append(n3).append(" of ").append(a)))));
                    }
                }
                if (af != null) {
                    a2.a("100");
                    af.a(a2);
                }
            }
            else if (ji.util.d.cs()) {
                ji.io.h.d(this.d, "No data to send!");
            }
        }
        finally {
            if (printStream != null) {
                printStream.close();
            }
        }
    }
    
    private void a(final af af, final w5 w5, final boolean b, final boolean b2, String bm, final String s) throws Exception {
        String s2 = "";
        a(af, 0, this);
        this.a(af);
        if (this.h == null) {
            throw new Exception("Failed to open output stream to ".concat(String.valueOf(String.valueOf(this.a.getURL()))));
        }
        if (bm == null) {
            bm = ji.util.d.bm(this.d);
        }
        final int n = ji.util.d.ay() / 2;
        final double a = w5.a(b2, bm);
        if (ji.util.d.cs()) {
            if (b2) {
                ji.io.h.d(this.d, String.valueOf(String.valueOf(new StringBuffer("Sending data...(").append(bm).append(")..."))));
            }
            else {
                ji.io.h.d(this.d, "Sending data...(no encoding)...");
            }
        }
        if (ji.util.d.cs()) {
            ji.io.h.d(this.d, "Encoded length:".concat(String.valueOf(String.valueOf(a))));
        }
        final PrintStream printStream = new PrintStream(this.h);
        int max = Math.max((int)(0.5 + a / n), 1);
        final int a2 = w5.a();
        final int n2 = a2 / max;
        if (n2 * max < a2) {
            ++max;
        }
        int n3 = 0;
        int n4 = Math.min(a2, n2);
        String s7;
        if (b) {
            final String s3 = "FnAnnoData";
            final String s4 = "FnAnnoSize";
            final String s5 = "FnNumOfFields";
            for (int i = 0; i < max; ++i) {
                if (s2.length() > 0) {
                    s2 = String.valueOf(String.valueOf(s2)).concat("&");
                }
                final String a3 = w5.a(b2, bm, n3, n4 - n3);
                String s6;
                if (!b2) {
                    if (ji.util.d.cs()) {
                        ji.io.h.d(this.d, "Encoding not in use");
                    }
                    s6 = URLEncoder.encode(a3);
                }
                else if (ji.util.d.a(1, 4, this.d)) {
                    try {
                        if (ji.util.d.cs()) {
                            ji.io.h.d(this.d, "Invoking Java 2 URLEncoder encoding");
                        }
                        s6 = (String)((bh.k == null) ? (bh.k = class$("java.net.URLEncoder")) : bh.k).getMethod("encode", (bh.j == null) ? (bh.j = class$("java.lang.String")) : bh.j, (bh.j == null) ? (bh.j = class$("java.lang.String")) : bh.j).invoke(null, a3, bm);
                    }
                    catch (Exception ex2) {
                        if (ji.util.d.cs()) {
                            ji.io.h.d(this.d, "Invoking failed. Reverting to jiURLEncoder");
                        }
                        s6 = gh.a(a3, bm);
                    }
                }
                else {
                    if (ji.util.d.cs()) {
                        ji.io.h.d(this.d, "Using jiURLEncoder");
                    }
                    s6 = gh.a(a3, bm);
                }
                if (bh.i) {
                    try {
                        new ac(q.a(this.f, this.d).p(), true, false, 0, this.f, this.d, false).b(s6.getBytes());
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.d, String.valueOf(String.valueOf(new StringBuffer("Sending chunk size:").append(a3.length()).append(" encoded size:").append(s6.length()))));
                }
                if (i < 9) {
                    s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(s3).append("0").append(i + 1).append("=").append(s6)));
                }
                else {
                    s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(s3).append(i + 1).append("=").append(s6)));
                }
                n3 += n2;
                n4 = Math.min(a2, n4 + n2);
            }
            s7 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("&").append(s4).append("=").append(a).append("&").append(s5).append("=").append(max))))));
        }
        else {
            final String e = ji.util.i.e(4);
            String encode;
            if (ji.util.d.by(e)) {
                encode = "data";
            }
            else {
                encode = URLEncoder.encode(e);
            }
            final String e2 = ji.util.i.e(3);
            String encode2;
            if (ji.util.d.by(e2)) {
                encode2 = "size";
            }
            else {
                encode2 = URLEncoder.encode(e2);
            }
            final String e3 = ji.util.i.e(5);
            String encode3;
            if (ji.util.d.by(e3)) {
                encode3 = "numdata";
            }
            else {
                encode3 = URLEncoder.encode(e3);
            }
            s7 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(encode2))).append("=").append((int)a).append("&").append(encode3).append("=").append(max))))));
            for (int j = 0; j < max; ++j) {
                final String a4 = w5.a(b2, bm, n3, n4 - n3);
                if (j < 9) {
                    s7 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s7))).append("&").append(encode).append("0").append(j + 1).append("=").append(URLEncoder.encode(a4))));
                }
                else {
                    s7 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s7))).append("&").append(encode).append(j + 1).append("=").append(URLEncoder.encode(a4))));
                }
                n3 += n2;
                n4 = Math.min(a2, n4 + n2);
            }
        }
        if ((!b || (b && ji.util.i.c(232))) && s != null) {
            String concat = s;
            if (!concat.endsWith("&")) {
                concat = String.valueOf(String.valueOf(concat)).concat("&");
            }
            s7 = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(s7)));
        }
        if (bh.i) {
            new ac(q.a(this.f, this.d).p(), true, false, 0, this.f, this.d, false).b(s7.getBytes());
        }
        printStream.print(s7);
        printStream.close();
    }
    
    private static final void a(final af af, final int n, final Object o) {
        try {
            if (af != null) {
                af.a(new a6(o, 4, "".concat(String.valueOf(String.valueOf(n)))));
            }
        }
        catch (Exception ex) {}
    }
    
    private static final void a(final af af, final a6 a6, final Object o) {
        try {
            if (af != null) {
                af.a(a6);
            }
        }
        catch (Exception ex) {}
    }
    
    public String b() {
        String s = "";
        if (!this.b) {
            try {
                s = this.a.getHeaderField(0);
                s = String.valueOf(String.valueOf(s)).concat(" ");
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.d, "Response1: ".concat(String.valueOf(String.valueOf(s))));
                }
                if (s.length() > 0) {
                    s = ji.util.d.bc(s);
                    final int index = s.indexOf(" ");
                    if (index >= 0) {
                        s = ji.util.d.bc(s.substring(index));
                    }
                }
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.d, "Response2: ".concat(String.valueOf(String.valueOf(s))));
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return s;
    }
    
    public void c() throws Exception {
        if (this.h != null) {
            this.h.flush();
            this.h.close();
        }
        this.h = null;
    }
    
    public void d() throws Exception {
        if (this.g != null) {
            this.g.close();
        }
        this.g = null;
    }
    
    public void e() throws IOException {
        try {
            if (this.g != null) {
                this.d();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.g = null;
        try {
            if (this.h != null) {
                this.c();
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        this.h = null;
        this.a = null;
    }
    
    public String a(final int n) throws IOException {
        final byte[] array = new byte[n];
        String concat = "";
        if (this.g == null) {
            this.g();
        }
        if (this.g != null) {
            int n2 = 0;
            for (int i = this.g.read(array); i >= 0; i = this.g.read(array)) {
                n2 += i;
                if (ji.util.i.c(283)) {
                    for (int j = 0; j < i; ++j) {
                        if (array[j] < 32 && array[j] != 13 && array[j] != 10) {
                            array[j] = 32;
                        }
                    }
                }
                concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(new String(array, 0, i))));
            }
        }
        return ji.util.d.b(ji.util.d.b(ji.util.d.b(concat, "\r\n", "<N>"), "\r", "<N>"), "\n", "<N>");
    }
    
    public static String a(final String s) {
        return d.bc(d.b(d.c(d.b(d.b(d.b(d.b(d.b(s, "<N>", "\n"), "null", ""), "\r\n", ""), "\n", ""), " ", "<%32>"), true), "<%32>", " "));
    }
    
    public static boolean b(final String s) {
        final String lowerCase = s.toLowerCase();
        return lowerCase.endsWith("<ok>") || lowerCase.endsWith("ok") || lowerCase.startsWith("200 ") || lowerCase.startsWith("<ok>") || lowerCase.startsWith("ok") || lowerCase.startsWith("200 ok") || lowerCase.startsWith("ok 200");
    }
    
    public static final boolean a(final String s, final String s2, final String s3, final af af, final Object o, final ad ad) {
        boolean b = false;
        String s4 = null;
        boolean b2 = true;
        try {
            final int n = 250 - s.length();
            int n2;
            if (n < 2) {
                n2 = 2;
            }
            else {
                n2 = 2 * (n / 2);
            }
            final ac ac = new ac(s2, false, false, 0, o, s3);
            final int n3 = (int)ji.io.ac.a(s2, s3);
            int n4 = n3 / n2;
            if (n4 * n2 < n3) {
                ++n4;
            }
            int i = 0;
            int n5 = 1;
            int n6 = n3;
            byte[] bytes = new byte[n2];
            a(af, 0, o);
            while (i < n3) {
                if (n6 < bytes.length) {
                    bytes = new byte[n6];
                    n2 = n6;
                }
                final int a = ac.a(bytes, 0, n2);
                final String string = s.toString();
                String s5;
                if (string.endsWith("&")) {
                    s5 = "";
                }
                else if (string.endsWith("?")) {
                    s5 = "";
                }
                else if (string.indexOf("?") >= 0) {
                    s5 = "&";
                }
                else {
                    s5 = "?";
                }
                final URL url = new URL(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(s5).append("seq=").append(n5).append("&of=").append(n4).append("&text=").append(new String(bytes, 0, a)))));
                if (d.cs()) {
                    if (url != null) {
                        h.d(s3, url.toString());
                    }
                    else {
                        h.d(s3, "URL = EMPTY!");
                    }
                }
                final byte[] array = new byte[2];
                bytes = new String(bytes, 0, a).getBytes();
                final byte[] a2 = d.a(url, af, d.cg(), o, s3);
                if (a2 == null) {
                    h.d(s3, String.valueOf(String.valueOf(new StringBuffer("Send sequence ").append(n5).append(" failed"))));
                    s4 = String.valueOf(String.valueOf(s4)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s)).concat(" did not return a result, require '\\n<OK>' to be returned"))));
                    h.d(s3, s4);
                    b = true;
                    b2 = false;
                    break;
                }
                final String s6 = new String(a2);
                if (d.cs()) {
                    h.d(s3, "Result = ".concat(String.valueOf(String.valueOf(s6))));
                }
                if (s6.toLowerCase().indexOf("<ok>") < 0) {
                    h.d(s3, String.valueOf(String.valueOf(new StringBuffer("Send sequence ").append(n5).append(" failed"))));
                    s4 = String.valueOf(String.valueOf(s4)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(" did not return an OK, require '\\n<OK>' to be returned (value received = ").append(s6).append(")"))))));
                    h.d(s3, s4);
                    b = true;
                    b2 = false;
                    break;
                }
                if (d.cs()) {
                    h.d(s3, String.valueOf(String.valueOf(new StringBuffer("Send sequence ").append(n5).append(" successful"))));
                }
                a(af, 100 * (n3 - (n6 + a)) / n3, o);
                try {
                    if (af != null) {
                        af.a(new a6(o, 14, "".concat(String.valueOf(String.valueOf(100 * (n3 - (n6 + a)) / n3)))));
                    }
                }
                catch (Exception ex2) {}
                i += a;
                n6 -= a;
                ++n5;
            }
            ac.a(o);
            a(af, 0, o);
            if (b && af != null) {
                af.a(new a6(o, 1, String.valueOf(String.valueOf(d.b(486, s3))).concat(".")));
            }
            else {
                af.a(new a6(o, 1, String.valueOf(String.valueOf(d.b(480, s3))).concat(".")));
            }
        }
        catch (Exception ex) {
            String.valueOf(String.valueOf(s4)).concat(String.valueOf(String.valueOf(ex.toString())));
            b2 = false;
            if (d.cs()) {
                h.d(s3, "Annotation send failed...");
            }
            ex.printStackTrace();
        }
        finally {
            a(af, 0, o);
        }
        if (b2) {
            ad.a(o, 24, "annotationsave: ok", 1, 1, 0, 0);
        }
        else {
            ad.a(o, 25, "annotationsave: failed", 1, 1, 0, 0);
        }
        return b2;
    }
    
    static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        bh.i = false;
    }
    
    private class w7 implements w5
    {
        private byte[] a;
        
        public w7(final byte[] a) {
            this.a = a;
        }
        
        public double a(final boolean b, final String s) {
            try {
                String s2;
                if (b) {
                    s2 = new String(this.a, s);
                }
                else {
                    s2 = new String(this.a);
                }
                return URLEncoder.encode(s2).length();
            }
            catch (Exception ex) {
                return -1.0;
            }
        }
        
        public int a() {
            return this.a.length;
        }
        
        public byte[] a(final int n, final int n2) throws IOException {
            final byte[] array = new byte[n2];
            System.arraycopy(this.a, n, array, 0, n2);
            return array;
        }
        
        public String a(final boolean b, final String s, final int n, final int n2) throws IOException {
            if (bh.i) {
                try {
                    new ac(q.a(bh.this.f, bh.this.d).p(), true, false, 0, bh.this.f, bh.this.d, false).b(this.a);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            String s2;
            if (b) {
                s2 = new String(this.a, n, n2, s);
            }
            else {
                s2 = new String(this.a, n, n2);
            }
            if (bh.i) {
                try {
                    new ac(q.a(bh.this.f, bh.this.d).p(), true, false, 0, bh.this.f, bh.this.d, false).b(s2.getBytes(s));
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
            return s2;
        }
    }
    
    private interface w5
    {
        byte[] a(final int p0, final int p1) throws Exception;
        
        String a(final boolean p0, final String p1, final int p2, final int p3) throws Exception;
        
        double a(final boolean p0, final String p1);
        
        int a();
    }
    
    private class w4 implements w5
    {
        private ac a;
        private byte[] b;
        
        public w4(final ac a) {
            this.a = a;
        }
        
        public int a() {
            try {
                return (int)this.a.v();
            }
            catch (Exception ex) {
                return -1;
            }
        }
        
        public double a(final boolean b, final String s) {
            int length = -1;
            try {
                final byte[] array = new byte[this.a()];
                this.a.a(array);
                String s2;
                if (b) {
                    s2 = new String(array, s);
                }
                else {
                    s2 = new String(array);
                }
                length = URLEncoder.encode(s2).length();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return length;
        }
        
        public byte[] a(final int n, int n2) throws Exception {
            if (n + n2 >= this.a.v()) {
                n2 = (int)(this.a.v() - n);
            }
            if (this.b == null || this.b.length != n2) {
                this.b = new byte[n2];
            }
            this.a.a(this.b);
            if (bh.i) {
                new ac(q.a(bh.this.f, bh.this.d).p(), true, false, 0, bh.this.f, bh.this.d, false).b(this.b);
            }
            return this.b;
        }
        
        public String a(final boolean b, final String s, final int n, final int n2) throws Exception {
            String s2;
            if (b) {
                s2 = new String(this.a(n, n2), s);
            }
            else {
                s2 = new String(this.a(n, n2));
            }
            if (bh.i) {
                new ac(q.a(bh.this.f, bh.this.d).p(), true, false, 0, bh.this.f, bh.this.d, false).b(s2.getBytes());
            }
            return s2;
        }
    }
}
