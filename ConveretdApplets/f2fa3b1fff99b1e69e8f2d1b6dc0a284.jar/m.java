import java.io.ByteArrayInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.io.DataInputStream;
import java.net.URL;
import java.io.BufferedReader;

// 
// Decompiled by Procyon v0.5.30
// 

public class m
{
    private String try;
    private String char;
    private BufferedReader null;
    private URL int;
    private String for;
    private int if;
    private String goto;
    private String new;
    private int a;
    private int byte;
    private BufferedReader else;
    private DataInputStream do;
    private o long;
    private byte[] case;
    
    public m(final String for1, final String goto1, final String new1, final String s, final int byte1, final String s2, final String s3, final String s4, final String s5, final String s6, final int n) throws MalformedURLException, IOException {
        this.try = "";
        this.char = "";
        this.int = null;
        this.if = 0;
        this.a = 0;
        this.byte = 66;
        this.else = null;
        this.long = null;
        String s7 = s2;
        if (s7.equals("")) {
            s7 = "234";
        }
        this.goto = goto1;
        this.new = new1;
        String s8 = for1 + "?MODE=reset&instruments=" + goto1 + "&headers=" + new1 + "&ver=" + byte1 + "&appid=" + s + "&user=" + s7 + "&SITE=" + s3 + "&URL=" + s4 + "&MOD=1";
        if (!s5.equals("")) {
            s8 = s8 + "&" + s5;
        }
        if (!s6.equals("")) {
            s8 = s8 + "&type=" + s6;
        }
        if (n != -1) {
            s8 = s8 + "&RGId=" + n;
        }
        this.for = for1;
        final URLConnection openConnection = new URL(s8).openConnection();
        openConnection.setDefaultUseCaches(false);
        openConnection.setAllowUserInteraction(false);
        openConnection.setDoInput(true);
        openConnection.setDoOutput(false);
        openConnection.setUseCaches(false);
        openConnection.setRequestProperty("Pragma", "no-cache");
        openConnection.setRequestProperty("Cache-Control", "no-cache");
        openConnection.setRequestProperty("Expires", "-1");
        openConnection.setRequestProperty("Content-type", "text/html");
        if ((this.byte = byte1) >= 66) {
            this.long = new o();
        }
        this.a(openConnection.getInputStream(), openConnection.getContentType());
    }
    
    public DataInputStream a() {
        return this.do;
    }
    
    public int do() {
        return this.if;
    }
    
    public void for() throws MalformedURLException, IOException {
        final String string = this.for + "?MODE=REFRESH&" + this.try + "&" + this.char;
        if (this.int == null) {
            this.int = new URL(string);
        }
        final URLConnection openConnection = this.int.openConnection();
        ++this.a;
        openConnection.setDefaultUseCaches(false);
        openConnection.setAllowUserInteraction(false);
        openConnection.setDoInput(true);
        openConnection.setDoOutput(false);
        openConnection.setUseCaches(false);
        openConnection.setRequestProperty("Pragma", "no-cache");
        openConnection.setRequestProperty("Cache-Control", "no-cache");
        openConnection.setRequestProperty("Expires", "-1");
        openConnection.setRequestProperty("Content-type", "text/html");
        this.a(openConnection.getInputStream(), openConnection.getContentType());
    }
    
    public void if(final String s) {
        this.try = "ID=" + s;
    }
    
    public void a(final int n) {
        this.try = "ID=" + n;
    }
    
    public void if(final int n) {
        this.char = "SEQ=" + n;
    }
    
    public void a(final String s) {
        this.char = "SEQ=" + s;
    }
    
    public boolean if() {
        return this.char.equals("") || this.try.equals("");
    }
    
    private void a(final InputStream inputStream) throws IOException {
        this.null = new BufferedReader(new InputStreamReader(inputStream));
    }
    
    private void a(final InputStream inputStream, final String s) throws IOException {
        if (s.toUpperCase().indexOf("TEXT/HTML") >= 0) {
            this.else = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = this.else.readLine()) != null) {
                this.long.a(line);
            }
            this.case = this.long.a();
            this.do = new DataInputStream(new ByteArrayInputStream(this.case));
            inputStream.close();
            this.else.close();
            this.else = null;
        }
        else {
            this.do = new DataInputStream(inputStream);
        }
    }
}
