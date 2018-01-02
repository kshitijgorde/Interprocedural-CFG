// 
// Decompiled by Procyon v0.5.30
// 

package matt.web;

import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.net.URL;
import java.util.HashMap;
import java.io.IOException;
import java.util.Random;
import java.util.Map;
import java.io.OutputStream;
import java.net.URLConnection;

public class ClientHttpRequest
{
    URLConnection connection;
    OutputStream os;
    Map cookies;
    private static Random random;
    String boundary;
    
    protected void connect() throws IOException {
        if (this.os == null) {
            this.os = this.connection.getOutputStream();
        }
    }
    
    protected void write(final char c) throws IOException {
        this.connect();
        this.os.write(c);
    }
    
    protected void write(final String s) throws IOException {
        this.connect();
        this.os.write(s.getBytes());
    }
    
    protected void newline() throws IOException {
        this.connect();
        this.write("\r\n");
    }
    
    protected void writeln(final String s) throws IOException {
        this.connect();
        this.write(s);
        this.newline();
    }
    
    protected static String randomString() {
        return Long.toString(ClientHttpRequest.random.nextLong(), 36);
    }
    
    private void boundary() throws IOException {
        this.write("--");
        this.write(this.boundary);
    }
    
    public ClientHttpRequest(final URLConnection connection) throws IOException {
        this.os = null;
        this.cookies = new HashMap();
        this.boundary = "---------------------------" + randomString() + randomString() + randomString();
        (this.connection = connection).setDoOutput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + this.boundary);
    }
    
    public ClientHttpRequest(final URL url) throws IOException {
        this(url.openConnection());
    }
    
    public ClientHttpRequest(final String urlString) throws IOException {
        this(new URL(urlString));
    }
    
    private void postCookies() {
        final StringBuffer cookieList = new StringBuffer();
        final Iterator i = this.cookies.entrySet().iterator();
        while (i.hasNext()) {
            final Map.Entry entry = i.next();
            cookieList.append(entry.getKey().toString() + "=" + entry.getValue());
            if (i.hasNext()) {
                cookieList.append("; ");
            }
        }
        if (cookieList.length() > 0) {
            this.connection.setRequestProperty("Cookie", cookieList.toString());
        }
    }
    
    public void setCookie(final String name, final String value) throws IOException {
        this.cookies.put(name, value);
    }
    
    public void setCookies(final Map cookies) throws IOException {
        if (cookies == null) {
            return;
        }
        this.cookies.putAll(cookies);
    }
    
    public void setCookies(final String[] cookies) throws IOException {
        if (cookies == null) {
            return;
        }
        for (int i = 0; i < cookies.length - 1; i += 2) {
            this.setCookie(cookies[i], cookies[i + 1]);
        }
    }
    
    private void writeName(final String name) throws IOException {
        this.newline();
        this.write("Content-Disposition: form-data; name=\"");
        this.write(name);
        this.write('\"');
    }
    
    public void setParameter(final String name, final String value) throws IOException {
        this.boundary();
        this.writeName(name);
        this.newline();
        this.newline();
        this.writeln(value);
    }
    
    private static void pipe(final InputStream in, final OutputStream out) throws IOException {
        byte[] buf = new byte[500000];
        int total = 0;
        synchronized (in) {
            int nread;
            while ((nread = in.read(buf, 0, buf.length)) >= 0) {
                out.write(buf, 0, nread);
                total += nread;
            }
        }
        out.flush();
        buf = null;
    }
    
    public void setParameter(final String name, final String filename, final InputStream is) throws IOException {
        this.boundary();
        this.writeName(name);
        this.write("; filename=\"");
        this.write(filename);
        this.write('\"');
        this.newline();
        this.write("Content-Type: ");
        final URLConnection connection = this.connection;
        String type = URLConnection.guessContentTypeFromName(filename);
        if (type == null) {
            type = "application/octet-stream";
        }
        this.writeln(type);
        this.newline();
        pipe(is, this.os);
        this.newline();
    }
    
    public void setParameter(final String name, final File file) throws IOException {
        this.setParameter(name, file.getPath(), new FileInputStream(file));
    }
    
    public void setParameter(final String name, final Object object) throws IOException {
        if (object instanceof File) {
            this.setParameter(name, (File)object);
        }
        else {
            this.setParameter(name, object.toString());
        }
    }
    
    public void setParameters(final Map parameters) throws IOException {
        if (parameters == null) {
            return;
        }
        for (final Map.Entry entry : parameters.entrySet()) {
            this.setParameter(entry.getKey().toString(), entry.getValue());
        }
    }
    
    public void setParameters(final Object[] parameters) throws IOException {
        if (parameters == null) {
            return;
        }
        for (int i = 0; i < parameters.length - 1; i += 2) {
            this.setParameter(parameters[i].toString(), parameters[i + 1]);
        }
    }
    
    public InputStream post() throws IOException {
        this.boundary();
        this.writeln("--");
        this.os.close();
        return this.connection.getInputStream();
    }
    
    public InputStream post(final Map parameters) throws IOException {
        this.setParameters(parameters);
        return this.post();
    }
    
    public InputStream post(final Object[] parameters) throws IOException {
        this.setParameters(parameters);
        return this.post();
    }
    
    public InputStream post(final Map cookies, final Map parameters) throws IOException {
        this.setCookies(cookies);
        this.setParameters(parameters);
        return this.post();
    }
    
    public InputStream post(final String[] cookies, final Object[] parameters) throws IOException {
        this.setCookies(cookies);
        this.setParameters(parameters);
        return this.post();
    }
    
    public InputStream post(final String name, final Object value) throws IOException {
        this.setParameter(name, value);
        return this.post();
    }
    
    public InputStream post(final String name1, final Object value1, final String name2, final Object value2) throws IOException {
        this.setParameter(name1, value1);
        return this.post(name2, value2);
    }
    
    public InputStream post(final String name1, final Object value1, final String name2, final Object value2, final String name3, final Object value3) throws IOException {
        this.setParameter(name1, value1);
        return this.post(name2, value2, name3, value3);
    }
    
    public InputStream post(final String name1, final Object value1, final String name2, final Object value2, final String name3, final Object value3, final String name4, final Object value4) throws IOException {
        this.setParameter(name1, value1);
        return this.post(name2, value2, name3, value3, name4, value4);
    }
    
    public static InputStream post(final URL url, final Map parameters) throws IOException {
        return new ClientHttpRequest(url).post(parameters);
    }
    
    public static InputStream post(final URL url, final Object[] parameters) throws IOException {
        return new ClientHttpRequest(url).post(parameters);
    }
    
    public static InputStream post(final URL url, final Map cookies, final Map parameters) throws IOException {
        return new ClientHttpRequest(url).post(cookies, parameters);
    }
    
    public static InputStream post(final URL url, final String[] cookies, final Object[] parameters) throws IOException {
        return new ClientHttpRequest(url).post(cookies, parameters);
    }
    
    public static InputStream post(final URL url, final String name1, final Object value1) throws IOException {
        return new ClientHttpRequest(url).post(name1, value1);
    }
    
    public static InputStream post(final URL url, final String name1, final Object value1, final String name2, final Object value2) throws IOException {
        return new ClientHttpRequest(url).post(name1, value1, name2, value2);
    }
    
    public static InputStream post(final URL url, final String name1, final Object value1, final String name2, final Object value2, final String name3, final Object value3) throws IOException {
        return new ClientHttpRequest(url).post(name1, value1, name2, value2, name3, value3);
    }
    
    public static InputStream post(final URL url, final String name1, final Object value1, final String name2, final Object value2, final String name3, final Object value3, final String name4, final Object value4) throws IOException {
        return new ClientHttpRequest(url).post(name1, value1, name2, value2, name3, value3, name4, value4);
    }
    
    static {
        ClientHttpRequest.random = new Random();
    }
}
