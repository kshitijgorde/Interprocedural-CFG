// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.http;

import java.io.InputStream;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import jmaster.util.B.A;
import java.io.BufferedOutputStream;
import javax.net.ssl.SSLSocketFactory;
import java.net.Socket;

public class SocketHttpUploader extends B
{
    protected Socket U;
    
    protected void B() throws Exception {
        final String host = this.I.getHost();
        final int n = (this.I.getPort() == -1) ? 80 : this.I.getPort();
        if ("https".equalsIgnoreCase(this.I.getProtocol())) {
            this.U = SSLSocketFactory.getDefault().createSocket(this.I.getHost(), (this.I.getPort() == -1) ? 443 : this.I.getPort());
        }
        else {
            this.U = new Socket(host, n);
        }
        this.O = new BufferedOutputStream(this.O = this.U.getOutputStream());
        String string = jmaster.util.B.A.C(this.I.getPath()) ? "/" : this.I.getPath();
        if (!jmaster.util.B.A.C(this.I.getQuery())) {
            string = string + "?" + this.I.getQuery();
        }
        final StringBuffer sb = new StringBuffer("POST " + string + " HTTP/1.0" + "\r\n");
        for (final String s : this.M.keySet()) {
            sb.append(s + ": " + (String)this.M.get(s) + "\r\n");
        }
        sb.append("\r\n");
        this.O.write(sb.toString().getBytes(this.S));
    }
    
    protected void E() throws Exception {
        final InputStream inputStream = this.U.getInputStream();
        final String s = "utf-8";
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, s));
        try {
            String substring = null;
            int n = 0;
            int n2 = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (n2 == 0) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
                    stringTokenizer.nextToken();
                    this.D = stringTokenizer.nextToken();
                }
                if (n == 0) {
                    n = ((line.length() == 0) ? 1 : 0);
                    if (this.R != null) {
                        final int index = line.indexOf(": ");
                        String substring2 = null;
                        String substring3 = line;
                        if (index != -1) {
                            substring2 = line.substring(0, index);
                            substring3 = line.substring(index + 2);
                            if ("Content-Type".equalsIgnoreCase(substring2)) {
                                final String s2 = "charset=";
                                final int index2 = substring3.indexOf(s2);
                                if (index2 != -1) {
                                    substring = substring3.substring(index2 + s2.length());
                                }
                            }
                        }
                        this.R.createAttribute(substring2, substring3);
                    }
                }
                else if (this.Q != null) {
                    if (this.Q.length() > 0) {
                        this.Q.append("\r\n");
                    }
                    if (substring != null && !substring.equalsIgnoreCase(s)) {
                        line = new String(line.getBytes(s), substring);
                    }
                    this.Q.append(line);
                }
                ++n2;
            }
        }
        finally {
            jmaster.util.F.A.A(inputStream);
        }
    }
    
    protected void C() throws Exception {
        this.U.close();
    }
}
