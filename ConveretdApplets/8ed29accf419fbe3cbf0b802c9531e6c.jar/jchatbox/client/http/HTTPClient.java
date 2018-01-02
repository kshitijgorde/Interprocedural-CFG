// 
// Decompiled by Procyon v0.5.30
// 

package jchatbox.client.http;

import jchatbox.client.util.Debug;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.io.IOException;
import java.util.Enumeration;
import java.net.Socket;
import java.util.Vector;
import java.io.PrintStream;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.net.URL;

public class HTTPClient
{
    private URL _$15269;
    private String _$15270;
    private String _$15271;
    private int _$15272;
    private static final String _$15273 = " HTTP/1.0";
    private Hashtable _$9570;
    private String _$15274;
    private int _$9578;
    
    public HTTPClient(final String s) throws MalformedURLException {
        this._$9570 = null;
        this._$15274 = null;
        this._$9578 = 10000;
        this._$9570 = new Hashtable();
        if (s != null) {
            this._$15269 = new URL(s);
            this._$15270 = this._$15269.getHost();
            this._$15271 = this._$15269.getFile();
            this._$15272 = this._$15269.getPort();
            if (this._$15272 == -1) {
                this._$15272 = 80;
            }
        }
    }
    
    public HTTPClient(final URL url) {
        this._$9570 = null;
        this._$15274 = null;
        this._$9578 = 10000;
        this._$9570 = new Hashtable();
        if (url != null) {
            this._$15270 = url.getHost();
            this._$15271 = url.getFile();
            this._$15272 = url.getPort();
            if (this._$15272 == -1) {
                this._$15272 = 80;
            }
        }
    }
    
    public void setURL(final String s) throws MalformedURLException {
        if (s != null) {
            this._$15269 = new URL(s);
            this._$15270 = this._$15269.getHost();
            this._$15271 = this._$15269.getFile();
            this._$15272 = this._$15269.getPort();
            if (this._$15272 == -1) {
                this._$15272 = 80;
            }
        }
    }
    
    public void setTimeOut(final int $9578) {
        this._$9578 = $9578;
    }
    
    public int getTimeOut() {
        return this._$9578;
    }
    
    public void setRequestProperty(final String s, final String s2) {
        this._$9570.put(s, s2);
    }
    
    public String getRequestProperty(final String s) {
        return this._$9570.get(s);
    }
    
    public void removeRequestProperty(final String s) {
        this._$9570.remove(s);
    }
    
    public void removeAllRequestProperty() {
        this._$9570 = new Hashtable();
    }
    
    public URL getURL() {
        return this._$15269;
    }
    
    public void setParams(final String $15274) {
        this._$15274 = $15274;
    }
    
    public String getParams() {
        return this._$15274;
    }
    
    public HTTPResponse doGet() throws IOException, SocketException, InterruptedIOException {
        final StringBuffer sb = new StringBuffer();
        final Socket socket = TimedSocket.getSocket(this._$15270, this._$15272, 4000);
        socket.setSoTimeout(this._$9578);
        final DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        final PrintStream printStream = new PrintStream(socket.getOutputStream());
        String s;
        if (this._$15274 == null) {
            s = String.valueOf(String.valueOf(new StringBuffer("GET ").append(this._$15271).append(" HTTP/1.0")));
        }
        else {
            s = String.valueOf(String.valueOf(new StringBuffer("GET ").append(this._$15271).append(this._$15274).append(" HTTP/1.0")));
        }
        this._$619(2, this.getClass().getName(), s);
        printStream.print(String.valueOf(String.valueOf(s)).concat("\n"));
        final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf("host"))).append(": ").append(this._$15270)));
        this._$619(9, this.getClass().getName(), value);
        printStream.print(String.valueOf(String.valueOf(value)).concat("\n"));
        final Enumeration<String> keys = (Enumeration<String>)this._$9570.keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            final String value2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(": ").append(this._$9570.get(s2))));
            this._$619(9, this.getClass().getName(), value2);
            printStream.print(String.valueOf(String.valueOf(value2)).concat("\n"));
        }
        printStream.print("\n");
        final Vector<String> headers = new Vector<String>();
        final StringBuffer body = new StringBuffer();
        int n = 1;
        while (true) {
            final String line = dataInputStream.readLine();
            if (line == null) {
                break;
            }
            if (n == 1) {
                if (line.length() == 0) {
                    n = 0;
                }
                else {
                    headers.addElement(line);
                }
            }
            else {
                body.append(String.valueOf(String.valueOf(line)).concat("\n"));
            }
        }
        printStream.close();
        dataInputStream.close();
        final HTTPResponse httpResponse = new HTTPResponse();
        httpResponse.setBody(body);
        httpResponse.setHeaders(headers);
        return httpResponse;
    }
    
    private void _$619(final int n, final String s, final String s2) {
        Debug.log(n, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(":").append(s2))));
    }
}
