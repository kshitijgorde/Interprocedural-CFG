// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.a;

import java.io.ByteArrayInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.net.URL;
import java.io.DataInputStream;

public class c
{
    private String byte;
    private String null;
    private DataInputStream do;
    private URL try;
    private String new;
    private int if;
    private String b;
    private int goto;
    private int int;
    private BufferedReader void;
    private h c;
    private byte[] long;
    private int a;
    private String for;
    private int else;
    private URLConnection case;
    private HttpURLConnection char;
    
    public c(final String new1, final String b, final int goto1, final int int1, final int n, final String s, final int else1, final String s2, final String s3, final String s4, final int n2) throws MalformedURLException, IOException {
        this.byte = "";
        this.null = "";
        this.try = null;
        this.if = 0;
        this.void = null;
        this.c = null;
        this.a = 0;
        this.else = 66;
        String s5 = s2;
        if (s5.equals("")) {
            s5 = "234";
        }
        this.b = b;
        this.goto = goto1;
        this.int = int1;
        final d d = new d(new1);
        d.a("MODE", this.for = "RESET");
        d.a("symbol", b);
        d.a("charttype", goto1);
        d.a("horizon", int1);
        d.a("values", n);
        d.a("ver", else1);
        d.a("appid", s);
        d.a("user", s5);
        d.a("SITE", s3);
        d.a("URL", s4);
        String property = System.getProperty("java.vendor");
        final String property2 = System.getProperty("java.version");
        if (property.indexOf("Sun") >= 0) {
            property = "Sun";
        }
        if (property.indexOf("Microsoft") >= 0) {
            property = "MS";
        }
        final String replace = property2.replace('_', '-');
        d.a("JVend", property.replace(' ', '-'));
        d.a("JRev", replace);
        if (n2 != -1) {
            d.a("RGId", n2);
        }
        this.new = new1;
        this.case = d.a().openConnection();
        if (this.case instanceof HttpURLConnection) {
            this.char = (HttpURLConnection)this.case;
        }
        this.case.setDefaultUseCaches(false);
        this.case.setAllowUserInteraction(false);
        this.case.setDoInput(true);
        this.case.setDoOutput(false);
        this.case.setUseCaches(false);
        this.case.setRequestProperty("Pragma", "no-cache");
        this.case.setRequestProperty("Cache-Control", "no-cache");
        this.case.setRequestProperty("Expires", "-1");
        this.case.setRequestProperty("Content-type", "text/html");
        if ((this.else = else1) >= 66) {
            this.c = new h();
        }
        this.a(this.case.getInputStream(), this.case.getContentType());
        if (this.char != null) {
            this.char.disconnect();
        }
        this.char = null;
        this.case = null;
    }
    
    public DataInputStream if() {
        return this.do;
    }
    
    public int for() {
        return this.if;
    }
    
    public void a(final long n, final long n2, final int n3) throws MalformedURLException, IOException {
        final d d = new d(this.new);
        d.a("MODE", "SLICE");
        d.a("ID", this.byte);
        d.a("SEQ", this.null);
        if (n > 0L) {
            d.a("STARTTIME", n);
        }
        if (n2 > 0L) {
            d.a("ENDTIME", n2);
        }
        if (n3 > 0) {
            d.a("VALUES", n3);
        }
        this.try = d.a();
        this.for = "SLICE";
        final URLConnection openConnection = this.try.openConnection();
        ++this.a;
        openConnection.setDefaultUseCaches(false);
        openConnection.setAllowUserInteraction(true);
        openConnection.setDoInput(true);
        openConnection.setDoOutput(false);
        openConnection.setUseCaches(false);
        openConnection.setRequestProperty("Pragma", "no-cache");
        openConnection.setRequestProperty("Cache-Control", "no-cache");
        openConnection.setRequestProperty("Expires", "-1");
        openConnection.setRequestProperty("Content-type", "text/html");
        this.a(openConnection.getInputStream(), openConnection.getContentType());
    }
    
    public void int() throws MalformedURLException, IOException {
        if (this.try == null) {
            final d d = new d(this.new);
            d.a("MODE", "REFRESH");
            d.a("ID", this.byte);
            d.a("SEQ", this.null);
            this.try = d.a();
            this.for = "REFRESH";
        }
        this.case = this.try.openConnection();
        if (this.case instanceof HttpURLConnection) {
            this.char = (HttpURLConnection)this.case;
        }
        ++this.a;
        this.case.setDefaultUseCaches(false);
        this.case.setAllowUserInteraction(true);
        this.case.setDoInput(true);
        this.case.setDoOutput(false);
        this.case.setUseCaches(false);
        this.case.setRequestProperty("Pragma", "no-cache");
        this.case.setRequestProperty("Cache-Control", "no-cache");
        this.case.setRequestProperty("Expires", "-1");
        this.case.setRequestProperty("Content-type", "text/html");
        this.a(this.case.getInputStream(), this.case.getContentType());
    }
    
    public void if(final String byte1) {
        this.byte = byte1;
        this.try = null;
    }
    
    public void a(final String null) {
        this.null = null;
        this.try = null;
    }
    
    public boolean do() {
        return this.null.equals("") || this.byte.equals("");
    }
    
    private void a(final InputStream inputStream, String s) throws IOException {
        if (s == null) {
            s = "text/html";
        }
        if (s.toUpperCase().indexOf("TEXT/HTML") >= 0) {
            this.void = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = this.void.readLine()) != null) {
                this.c.a(line);
            }
            this.long = this.c.a();
            this.do = new DataInputStream(new ByteArrayInputStream(this.long));
            inputStream.close();
        }
        else {
            this.do = new DataInputStream(inputStream);
        }
    }
    
    public void a() {
        if (this.char != null) {
            this.char.disconnect();
        }
        this.char = null;
        this.case = null;
    }
}
