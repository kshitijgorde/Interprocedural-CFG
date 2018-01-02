// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.net.InetAddress;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.io.DataInputStream;
import java.net.URLConnection;

public class AppletSocket
{
    public static long useCount;
    public static long notClosed;
    URLConnection socket;
    private DataInputStream in;
    private static String authorizationStr;
    boolean authorized;
    boolean useCaches;
    int contentLength;
    String status;
    String lastURL;
    public boolean debug;
    boolean noContent;
    public static int bufferSize;
    private static char[] alphabet;
    private static byte[] codes;
    
    static {
        AppletSocket.useCount = 0L;
        AppletSocket.notClosed = 0L;
        AppletSocket.authorizationStr = null;
        AppletSocket.bufferSize = 8192;
        AppletSocket.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
        AppletSocket.codes = new byte[256];
        for (int i = 0; i < 256; ++i) {
            AppletSocket.codes[i] = -1;
        }
        for (int i = 65; i <= 90; ++i) {
            AppletSocket.codes[i] = (byte)(i - 65);
        }
        for (int i = 97; i <= 122; ++i) {
            AppletSocket.codes[i] = (byte)(26 + i - 97);
        }
        for (int i = 48; i <= 57; ++i) {
            AppletSocket.codes[i] = (byte)(52 + i - 48);
        }
        AppletSocket.codes[43] = 62;
        AppletSocket.codes[47] = 63;
    }
    
    public AppletSocket() {
        this.authorized = true;
        this.useCaches = false;
        this.contentLength = 0;
        this.status = null;
        this.lastURL = null;
        this.debug = false;
        this.noContent = false;
        this.init();
    }
    
    public void finalize() {
        if (this.in != null) {
            ++AppletSocket.notClosed;
        }
        this.close();
    }
    
    private void init() {
        this.in = null;
        this.noContent = false;
        this.status = null;
        ++AppletSocket.useCount;
    }
    
    public boolean connect(final URL url) throws IOException {
        if (this.socket != null) {
            throw new IOException("Can't get: " + url.getFile().toString() + ". socket in use by " + this.lastURL);
        }
        this.init();
        this.lastURL = url.getFile().toString();
        if (this.debug) {
            Debug.println("calling openConnection " + url);
        }
        this.socket = url.openConnection();
        if (AppletSocket.authorizationStr != null) {
            this.socket.setRequestProperty("Authorization", AppletSocket.authorizationStr);
            this.socket.setAllowUserInteraction(false);
        }
        this.socket.setUseCaches(this.useCaches);
        this.socket.setDefaultUseCaches(this.useCaches);
        try {
            if (this.debug) {
                Debug.println("calling socket.connect()");
            }
            this.socket.connect();
        }
        catch (IOException e) {
            this.close();
            Debug.report(e, "Unable to connect to " + url);
            throw e;
        }
        if (!(this.authorized = this.getStatus())) {}
        return this.authorized;
    }
    
    public boolean authorized() {
        return this.authorized;
    }
    
    public String getResponseMessage() {
        return this.status;
    }
    
    private boolean getStatus() throws IOException {
        if (this.socket == null) {
            throw new IOException("socket null.");
        }
        if (this.status != null) {
            throw new IOException("status already set.");
        }
        this.status = this.socket.getHeaderField(0);
        if (this.status == null && this.status == null) {
            final int length = this.socket.getContentLength();
            if (length > 0) {
                this.status = "200";
            }
            else {
                Debug.println("status returned null... length=" + length);
                if (length != 0) {
                    throw new IOException("unable to get http connection.");
                }
                this.status = "204";
            }
        }
        if (this.status.indexOf("200", 0) != -1) {
            return true;
        }
        if (this.status.indexOf("401", 0) != -1) {
            return false;
        }
        if (this.status.indexOf("402", 0) != -1) {
            return false;
        }
        if (this.status.indexOf("403", 0) != -1) {
            return false;
        }
        if (this.status.indexOf("204", 0) != -1) {
            return this.noContent = true;
        }
        if (this.status.indexOf("302", 0) != -1) {
            return this.noContent = true;
        }
        throw new IOException(String.valueOf(this.status) + " connecting to " + this.lastURL);
    }
    
    public int getContentLength() throws IOException {
        if (this.contentLength == 0) {
            this.contentLength = this.socket.getContentLength();
        }
        return this.contentLength;
    }
    
    public void throwNotConnected() throws Exception {
        if (!this.authorized && this.status != null) {
            throw new Exception(this.status);
        }
    }
    
    public static void copy(final InputStream in, final OutputStream out, long byteCount) throws IOException {
        final byte[] buffer = new byte[AppletSocket.bufferSize];
        int len = AppletSocket.bufferSize;
        if (byteCount >= 0L) {
            while (byteCount > 0L) {
                if (byteCount < AppletSocket.bufferSize) {
                    len = in.read(buffer, 0, (int)byteCount);
                }
                else {
                    len = in.read(buffer, 0, AppletSocket.bufferSize);
                }
                if (len == -1) {
                    break;
                }
                byteCount -= len;
                out.write(buffer, 0, len);
            }
        }
        else {
            while (true) {
                len = in.read(buffer, 0, AppletSocket.bufferSize);
                if (len < 0) {
                    break;
                }
                out.write(buffer, 0, len);
            }
        }
    }
    
    private byte[] getContentGuessLength(final int maxBytes) throws IOException {
        final ByteArrayOutputStream buf = new ByteArrayOutputStream(32768);
        copy(this.getDataInputStream(), buf, maxBytes);
        this.contentLength = buf.size();
        return buf.toByteArray();
    }
    
    public byte[] getContent() throws IOException {
        if (this.noContent) {
            return null;
        }
        if (this.socket == null) {
            this.init();
        }
        final int len = this.getContentLength();
        if (len <= 0) {
            return this.getContentGuessLength(1048576);
        }
        final byte[] b = new byte[len];
        this.getDataInputStream().readFully(b);
        this.close();
        return b;
    }
    
    public Image getImageContent() throws IOException {
        if (this.noContent) {
            return null;
        }
        if (this.socket == null) {
            this.init();
        }
        return (Image)this.socket.getContent();
    }
    
    public String getContentText() throws IOException {
        return new String(this.getContent());
    }
    
    public DataInputStream getDataInputStream() throws IOException {
        if (this.in == null) {
            this.in = new DataInputStream(this.socket.getInputStream());
        }
        return this.in;
    }
    
    public void readFully(final byte[] b) throws IOException {
        this.getDataInputStream().readFully(b);
    }
    
    public int getContent(final byte[] b) throws IOException {
        final InputStream is = this.getDataInputStream();
        int x = 0;
        while (true) {
            final int i = is.read(b, x, b.length - x);
            if (i == -1) {
                break;
            }
            x += i;
        }
        return x;
    }
    
    public void close() {
        if (this.in != null) {
            try {
                this.in.close();
            }
            catch (Exception ex) {}
        }
        if (this.socket != null) {}
        this.in = null;
        this.socket = null;
        this.in = null;
    }
    
    public void setAuthorization(final String auth) throws IOException {
        if (auth != null && auth.length() > 0) {
            AppletSocket.authorizationStr = auth;
            if (!auth.startsWith("Basic")) {
                throw new IOException("bad auth");
            }
            AppletSocket.authorizationStr = auth;
        }
        else {
            AppletSocket.authorizationStr = null;
        }
    }
    
    public static String getAuthorization() {
        return AppletSocket.authorizationStr;
    }
    
    public String getNextLine() throws IOException {
        String headerLine = null;
        for (int x = 0; x < 5; ++x) {
            headerLine = this.getDataInputStream().readLine();
            if (headerLine != null) {
                break;
            }
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
        }
        if (headerLine == null) {
            throw new IOException("unable to read datastream");
        }
        return headerLine;
    }
    
    private static String getAuthorization(final String user, final String pass) {
        if (user != null && pass != null) {
            String auth = "Basic ";
            final String userPass = new String(String.valueOf(user) + ":" + pass);
            auth = String.valueOf(auth) + new String(encode(userPass.getBytes()));
            return auth;
        }
        return "";
    }
    
    public void setUserPass(final String user, final String pass) {
        String auth = "Basic ";
        final String userPass = new String(String.valueOf(user) + ":" + pass);
        auth = (AppletSocket.authorizationStr = String.valueOf(auth) + new String(encode(userPass.getBytes())));
    }
    
    public String getHeaderField(final String match) {
        return this.socket.getHeaderField(match);
    }
    
    private String getHeader(final String match) {
        return this.socket.getHeaderField(match);
    }
    
    public int getHeaderInt(final String match, final int def) {
        final String s = this.getHeaderField(match);
        if (s != null) {
            try {
                return Integer.parseInt(s);
            }
            catch (Exception ex) {}
        }
        return def;
    }
    
    public String lastURL() {
        return this.lastURL;
    }
    
    public String inspectStr(final String br) {
        String out = "";
        out = String.valueOf(out) + "url=" + this.lastURL + br;
        out = String.valueOf(out) + "status=" + this.status + br;
        return out;
    }
    
    public static String dottedIP(final InetAddress inetaddr) {
        String ip = "";
        try {
            final byte[] addr = inetaddr.getAddress();
            for (int x = 0; x < 4; ++x) {
                int i = addr[x] & 0xFF;
                if (i < 0) {
                    i = -i;
                }
                if (x > 0) {
                    ip = String.valueOf(ip) + ".";
                }
                ip = String.valueOf(ip) + i;
            }
        }
        catch (Exception e) {
            ip = "0.0.0.0";
        }
        return ip;
    }
    
    public static char[] encode(final byte[] data) {
        final char[] out = new char[(data.length + 2) / 3 * 4];
        for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
            boolean quad = false;
            boolean trip = false;
            int val = 0xFF & data[i];
            val <<= 8;
            if (i + 1 < data.length) {
                val |= (0xFF & data[i + 1]);
                trip = true;
            }
            val <<= 8;
            if (i + 2 < data.length) {
                val |= (0xFF & data[i + 2]);
                quad = true;
            }
            out[index + 3] = AppletSocket.alphabet[quad ? (val & 0x3F) : 64];
            val >>= 6;
            out[index + 2] = AppletSocket.alphabet[trip ? (val & 0x3F) : 64];
            val >>= 6;
            out[index + 1] = AppletSocket.alphabet[val & 0x3F];
            val >>= 6;
            out[index + 0] = AppletSocket.alphabet[val & 0x3F];
        }
        return out;
    }
    
    public static byte[] decode(final char[] data) {
        int len = (data.length + 3) / 4 * 3;
        if (data.length > 0 && data[len - 1] == '=') {
            --len;
        }
        if (data.length > 0 && data[len - 2] == '=') {
            --len;
        }
        final byte[] out = new byte[len];
        int shift = 0;
        int accum = 0;
        int index = 0;
        for (int ix = 0; ix < data.length; ++ix) {
            final int value = AppletSocket.codes[data[ix] & '\u00ff'];
            if (value >= 0) {
                accum <<= 6;
                shift += 6;
                accum |= value;
                if (shift >= 8) {
                    shift -= 8;
                    out[index++] = (byte)(accum >> shift & 0xFF);
                }
            }
        }
        if (index != out.length) {
            throw new Error("miscalculated data length!");
        }
        return out;
    }
}
