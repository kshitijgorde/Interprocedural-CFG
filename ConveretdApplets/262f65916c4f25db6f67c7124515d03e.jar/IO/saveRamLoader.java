// 
// Decompiled by Procyon v0.5.30
// 

package IO;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedOutputStream;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import UTIL.hex;
import java.net.URL;

public class saveRamLoader implements Runnable
{
    private URL a;
    private String b;
    private String c;
    private String d;
    private String e;
    private boolean f;
    private hex g;
    
    public saveRamLoader(final String s, final int[] array, final String c, final String d, final boolean f) {
        this.g = new hex();
        this.e = new String();
        try {
            this.a = new URL(s);
        }
        catch (Exception ex) {
            System.out.println("saveRamLoader : unable to load URL - " + ex);
        }
        if (array != null) {
            this.b = this.g.H2HS(array);
        }
        this.c = c;
        this.d = d;
        this.f = f;
    }
    
    public int[] getSRAM() {
        if (this.e.equals("") || this.e == null) {
            return new int[0];
        }
        return this.g.HS2H(this.e);
    }
    
    public int[] getAltSRAM(final String s) {
        return this.g.HS2H(s);
    }
    
    public void run() {
        try {
            final String string = URLEncoder.encode("userid", "UTF-8") + "=" + this.c + "&" + URLEncoder.encode("rom", "UTF-8") + "=" + this.d;
            String s;
            if (this.f) {
                s = string + "&" + URLEncoder.encode("sram", "UTF-8") + "=" + URLEncoder.encode(this.b, "UTF-8") + "&" + URLEncoder.encode("mode", "UTF-8") + "=" + URLEncoder.encode("save", "UTF-8");
            }
            else {
                s = string + "&" + URLEncoder.encode("mode", "UTF-8") + "=" + URLEncoder.encode("load", "UTF-8");
            }
            final HttpURLConnection httpURLConnection;
            (httpURLConnection = (HttpURLConnection)this.a.openConnection()).setDoOutput(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.connect();
            final OutputStreamWriter outputStreamWriter;
            (outputStreamWriter = new OutputStreamWriter(new BufferedOutputStream(httpURLConnection.getOutputStream()))).write(s);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            final StringBuffer sb = new StringBuffer();
            if (!this.f) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
            }
            bufferedReader.close();
            httpURLConnection.disconnect();
            this.e = sb.toString();
        }
        catch (Exception ex) {
            System.out.println("saveRamLoader : unable to connect to save script");
            throw new IllegalStateException();
        }
    }
}
