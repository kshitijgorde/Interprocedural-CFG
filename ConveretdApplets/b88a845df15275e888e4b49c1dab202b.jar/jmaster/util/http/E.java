// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.http;

import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import jmaster.util.B.A;
import java.io.BufferedInputStream;
import java.util.Iterator;
import java.net.URLConnection;
import java.io.BufferedOutputStream;
import java.net.HttpURLConnection;

public class E extends B
{
    protected HttpURLConnection V;
    
    protected void B() throws Exception {
        HttpURLConnection.setFollowRedirects(true);
        final URLConnection openConnection = this.I.openConnection();
        (this.V = (HttpURLConnection)openConnection).setRequestMethod("POST");
        openConnection.setDoInput(true);
        openConnection.setDoOutput(true);
        try {
            jmaster.util.C.B.A(this.V, "setFixedLengthStreamingMode", new Class[] { Integer.TYPE }, new Object[] { new Integer((int)this.N) });
        }
        catch (Exception ex) {
            this.L.A("Failed to set fixed length streaming mode", ex);
        }
        if (this.M != null) {
            for (final String s : this.M.keySet()) {
                this.V.setRequestProperty(s, (String)this.M.get(s));
            }
        }
        this.O = new BufferedOutputStream(openConnection.getOutputStream());
    }
    
    protected void E() throws Exception {
        if (this.R != null) {
            this.R.clear();
            for (final String s : this.V.getHeaderFields().keySet()) {
                this.R.createStringAttribute(s, this.V.getHeaderField(s));
            }
        }
        this.D = "" + this.V.getResponseCode();
        if (this.Q != null) {
            InputStream inputStream = null;
            try {
                try {
                    inputStream = new BufferedInputStream(this.V.getInputStream());
                }
                catch (Exception ex) {
                    inputStream = new BufferedInputStream(this.V.getErrorStream());
                }
                String s2 = this.V.getContentEncoding();
                if (jmaster.util.B.A.C(s2)) {
                    final String contentType = this.V.getContentType();
                    if (contentType != null) {
                        final String lowerCase = contentType.toLowerCase();
                        final String s3 = "charset=";
                        final int index = lowerCase.indexOf(s3);
                        if (index != -1) {
                            s2 = lowerCase.substring(index + s3.length());
                        }
                    }
                }
                if (jmaster.util.B.A.C(s2)) {
                    s2 = "UTF-8";
                }
                String line;
                while ((line = new BufferedReader(new InputStreamReader(inputStream, s2)).readLine()) != null) {
                    if (this.Q.length() > 0) {
                        this.Q.append("\r\n");
                    }
                    this.Q.append(line);
                }
            }
            finally {
                jmaster.util.F.A.A(inputStream);
            }
        }
    }
    
    protected void C() throws Exception {
        this.O.close();
    }
}
