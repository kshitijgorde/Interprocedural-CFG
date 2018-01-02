// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.io.IOException;
import java.util.Date;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.FileNotFoundException;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.OutputStream;
import java.net.Socket;

public class NFHttpClient
{
    protected Socket socket;
    protected String host;
    protected int port;
    protected OutputStream os;
    protected PrintStream ps;
    protected InputStream is;
    protected BufferedInputStream bis;
    protected DataInputStream dis;
    private int a;
    private int b;
    private boolean c;
    private String d;
    private NFContext e;
    private String f;
    private String g;
    private String h;
    private String[] i;
    public static final int MAX_TRIES = 5;
    public static final int EXCEPTION_STATUS = 666;
    private NFUserInput j;
    
    public NFHttpClient() {
        this.socket = null;
        this.host = "camden";
        this.port = 8001;
        this.os = null;
        this.ps = null;
        this.is = null;
        this.bis = null;
        this.dis = null;
        this.a = 0;
        this.b = 0;
        this.c = false;
        this.d = null;
        this.e = NFContext.getDefault();
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
    }
    
    public NFHttpClient(final NFContext context) {
        this();
        this.setContext(context);
    }
    
    public void setContext(final NFContext e) {
        this.e = e;
    }
    
    public int getStatus() {
        return this.a;
    }
    
    public String getTimeStamp(final String s) {
        try {
            return this.getTimeStamp(new URL(s));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public String getTimeStamp(final URL url) {
        try {
            if (this.processURL(url.getHost(), this.getPort(url), "HEAD", url.getFile(), null, new StringBuffer()) != 200) {
                return null;
            }
            return this.d;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public InputStream getContentAsInputStream(final URL url) {
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        int processURL = 666;
        while (n < 5 && (processURL = this.processURL(url.getHost(), this.getPort(url), "GET", url.getFile(), null, sb)) == 666) {
            ++n;
            sb.setLength(0);
        }
        if (processURL != 200) {
            return null;
        }
        return new StringBufferInputStream(sb.toString());
    }
    
    public boolean checkIfModifiedSince(final String s, final String s2) {
        try {
            final URL url = new URL(s);
            return this.processURL(url.getHost(), this.getPort(url), "HEAD", url.getFile(), "If-Modified-Since: " + s2, null) != 304;
        }
        catch (Exception ex) {
            return true;
        }
    }
    
    public int processURL(final String s, final int n, final String s2, final String s3, final String s4, final StringBuffer sb) {
        return this.processURL(s, n, s2, s3, s4, (String)null, sb);
    }
    
    public int processURL(final String s, final int n, final String s2, final String s3, final String s4, final String s5, final StringBuffer sb) {
        return this.processURL(s, n, s2, s3, s4, (byte[])((s5 == null) ? null : s5.getBytes()), sb, null);
    }
    
    public int processURL(final String s, final int n, final String s2, final String s3, final String s4, final byte[] array, final StringBuffer sb) {
        return this.processURL(s, n, s2, s3, s4, array, sb, null);
    }
    
    public int processURL(final String s, final int n, final String s2, final String s3, String s4, final byte[] array, final StringBuffer sb, final String s5) {
        int n2 = 0;
        final String s6 = null;
        this.h = s6;
        this.g = s6;
        this.b(s, n);
        if (s2.equalsIgnoreCase("put") || s2.equalsIgnoreCase("post")) {
            if (s4 == null) {
                s4 = new String();
            }
            else {
                s4 = s4.concat("\r\n");
            }
            if (s2.equalsIgnoreCase("put")) {
                s4 = s4.concat("Content-Type: " + ((s5 == null) ? "text/plain" : s5) + "\r\nContent-Length: " + ((array != null) ? array.length : 0));
                s4 = s4.concat("\r\nExpect: 100-continue");
            }
            else {
                s4 = s4.concat("Content-Type: " + ((s5 == null) ? "application/x-www-form-urlencoded" : s5) + "\r\nContent-Length: " + ((array != null) ? array.length : 0));
            }
        }
        int b = 0;
        Label_0341: {
            while (true) {
                final int a = this.a(s, n);
                if (a != 0) {
                    return a;
                }
                final int a2 = this.a(s2, s3, s4);
                if (a2 != 0) {
                    return a2;
                }
                if (s2.equalsIgnoreCase("post") && array != null) {
                    this.a(array);
                }
                b = this.b();
                if (b != 0) {
                    this.a(s2, null);
                    return b;
                }
                if (this.a != 401) {
                    break Label_0341;
                }
                this.a(s2, null);
                ++n2;
                if (NFGlobal.getAllowUserInteraction()) {
                    try {
                        if (!this.a(n2)) {
                            return this.a;
                        }
                        continue;
                    }
                    catch (Exception ex) {
                        return this.a;
                    }
                    break;
                }
                break;
            }
            NFDebug.print(524288L, "Could not login to server. Could not put up login screen.");
            return this.a;
        }
        if (this.a == 100 && s2.equalsIgnoreCase("put")) {
            this.a(array);
            if (b != 0) {
                return b;
            }
            final int b2 = this.b();
            if (b2 != 0) {
                this.a(s2, null);
                return b2;
            }
        }
        final int a3 = this.a(s2, sb);
        if (a3 != 0 && this.b > 0) {
            return a3;
        }
        return this.a;
    }
    
    public InputStream getInputStream(final URL url) throws Exception {
        return this.getInputStream(url, true);
    }
    
    public InputStream getInputStream(final URL url, final boolean b) throws Exception {
        final String host = url.getHost();
        final int port = this.getPort(url);
        final String file = url.getFile();
        try {
            int n = 0;
            final String s = null;
            this.h = s;
            this.g = s;
            this.b(host, port);
            Label_0316: {
                while (true) {
                    final int a = this.a(host, port);
                    if (a != 0) {
                        throw new Exception("URL Status = " + a);
                    }
                    final int a2 = this.a("GET", file, null);
                    if (a2 != 0) {
                        throw new Exception("URL Status = " + a2);
                    }
                    if (!b) {
                        return this.dis;
                    }
                    final int b2 = this.b();
                    if (b2 != 0) {
                        throw new Exception("URL Status = " + b2);
                    }
                    if (this.a == 200) {
                        return this.dis;
                    }
                    if (this.a != 401) {
                        break Label_0316;
                    }
                    this.a("GET", null);
                    ++n;
                    if (NFGlobal.getAllowUserInteraction()) {
                        try {
                            if (!this.a(n)) {
                                throw new SecurityException("Access Denied: " + url);
                            }
                            continue;
                        }
                        catch (Exception ex2) {
                            throw new SecurityException("Access Denied: " + url);
                        }
                        break;
                    }
                    break;
                }
                NFDebug.print(524288L, "Could not login to server. Could not put up login screen.");
                throw new SecurityException("Access Denied: " + url);
            }
            if (this.a == 400) {
                throw new FileNotFoundException("File Not Found: " + url);
            }
            throw new Exception("URL Status = " + this.a);
        }
        catch (Exception ex) {
            this.close();
            throw ex;
        }
        return this.dis;
    }
    
    private boolean a(final int n) throws Exception {
        if (this.j == null) {
            (this.j = new NFUserInput()).addField("user", "Username:", "String");
            this.j.addField("pass", "Password:", "Password");
            this.j.addButton("Ok");
            this.j.addButton("Cancel");
            this.j.setFieldsActivateButton("Ok");
            this.j.buildForm("netcharts.gui.NFUserInputPanel");
            this.j.pack();
        }
        if (n == 1) {
            this.j.setHeader("Enter username for " + this.f + " at " + this.host + ":" + this.port + ":");
        }
        else {
            this.j.setHeader("Invalid authentication information provided.\nPlease try again:");
        }
        this.j.setFieldValue("user", this.g);
        this.j.setFieldValue("pass", this.h);
        if (!this.j.showAndWait().equalsIgnoreCase("OK")) {
            return false;
        }
        this.g = this.j.getFieldValue("user");
        this.h = this.j.getFieldValue("pass");
        final String ipAddress = getIPAddress(this.host);
        this.e.setAuthInfo((ipAddress == null) ? this.host : ipAddress, this.port, this.g, this.h);
        return true;
    }
    
    public static String getIPAddress(final String s) {
        String hostAddress = null;
        try {
            hostAddress = InetAddress.getByName(s).getHostAddress();
        }
        catch (UnknownHostException ex) {}
        return hostAddress;
    }
    
    public void cancel() {
        if (this.j != null && this.j.threadWaiting()) {
            this.j.cancelShowAndWait();
        }
    }
    
    private int a(final String host, final int port) {
        if (this.socket != null && host.equals(this.host) && port == this.port) {
            return 0;
        }
        this.close();
        try {
            this.host = host;
            this.port = port;
            this.socket = new Socket(host, port);
            this.os = this.socket.getOutputStream();
            this.ps = new PrintStream(this.os);
            this.is = this.socket.getInputStream();
            this.bis = new BufferedInputStream(this.is);
            this.dis = new DataInputStream(this.bis);
            return 0;
        }
        catch (Exception ex) {
            this.close();
            return 666;
        }
    }
    
    private int a(final String s, final String s2, final String s3) {
        try {
            this.ps.print(s + " " + NFUtil.encodeURL(s2) + " HTTP/1.1\r\n");
            this.ps.print("Connection: keep-alive\r\n");
            this.ps.print("Accept: */*\r\n");
            if (s3 != null) {
                this.ps.print(s3 + "\r\n");
            }
            this.ps.print("User-Agent: " + NFContext.getUserAgent() + "\r\n");
            this.ps.print("Host: ");
            this.ps.print(this.host);
            if (this.port != 80) {
                this.ps.print(":");
                this.ps.print(this.port);
            }
            this.ps.print("\r\n");
            this.a();
            if (this.g != null && this.h != null) {
                this.ps.print("Authorization: Basic ");
                this.ps.print(NFBase64.encode(this.g + ":" + this.h));
                this.ps.print("\r\n");
            }
            this.ps.print("\r\n");
            this.os.flush();
            return 0;
        }
        catch (Exception ex) {
            this.close();
            return 666;
        }
    }
    
    private int a(final String s) {
        return this.a(s.getBytes());
    }
    
    private int a(final byte[] array) {
        try {
            if (array != null) {
                this.ps.write(array);
            }
            this.os.flush();
            return 0;
        }
        catch (Exception ex) {
            this.close();
            return 666;
        }
    }
    
    private void a() {
        this.b(this.host, this.port);
    }
    
    private void b(final String s, final int n) {
        final String ipAddress = getIPAddress(s);
        String[] array = this.e.getAuthInfo((ipAddress == null) ? s : ipAddress, n);
        if (array == null) {
            array = this.i;
        }
        if (array != null) {
            this.g = array[0];
            this.h = array[1];
        }
    }
    
    private int b() {
        try {
            this.a = -1;
            this.b = -1;
            this.d = null;
            this.c = false;
            while (true) {
                final String line = this.dis.readLine();
                if (line == null) {
                    break;
                }
                final int index = line.indexOf(58);
                String s;
                if (index != -1) {
                    s = line.substring(0, index).toLowerCase();
                }
                else {
                    s = line.toLowerCase();
                }
                if (this.a == -1 && line.startsWith("HTTP")) {
                    this.a = this.b(line);
                }
                else if (this.b == -1 && s.equals("content-length")) {
                    this.b = this.c(line);
                }
                else if (s.equals("transfer-encoding")) {
                    this.c = line.substring(index + 1).trim().equalsIgnoreCase("chunked");
                }
                else if (s.equals("last-modified")) {
                    this.d = this.d(line);
                }
                else if (s.equals("www-authenticate")) {
                    this.f = this.e(line);
                }
                else {
                    if (line.length() == 0) {
                        break;
                    }
                    continue;
                }
            }
            return 0;
        }
        catch (Exception ex) {
            this.close();
            return 666;
        }
    }
    
    public Date getLastModifiedAsDate() {
        return new Date(this.d);
    }
    
    public String getLastModifiedDate() {
        return this.d;
    }
    
    public int getContentLength() {
        return this.b;
    }
    
    private int a(final String s, final StringBuffer sb) {
        if (s.equalsIgnoreCase("HEAD")) {
            return 0;
        }
        try {
            byte[] array = null;
            if (this.c) {
                while (true) {
                    final int int1 = Integer.parseInt(this.dis.readLine().trim(), 16);
                    if (int1 == 0) {
                        break;
                    }
                    final byte[] bytes = readBytes(this.dis, int1);
                    this.dis.readLine();
                    if (array == null) {
                        array = bytes;
                    }
                    else {
                        array = byteArrayConcat(array, array.length, bytes, bytes.length);
                    }
                }
                while (!this.dis.readLine().equals("")) {}
            }
            else {
                array = readBytes(this.dis, this.b);
            }
            if (array != null) {
                String s2;
                if (NFUtil.getJDKVersion() > 1.0) {
                    s2 = new String(array);
                }
                else {
                    final char[] array2 = new char[array.length];
                    for (int i = 0; i < array.length; ++i) {
                        array2[i] = (char)array[i];
                    }
                    s2 = new String(array2);
                }
                if (sb != null) {
                    sb.append(s2);
                }
                return 0;
            }
            return -1;
        }
        catch (Exception ex) {
            this.close();
            return 666;
        }
    }
    
    public void close() {
        if (this.socket == null) {
            return;
        }
        try {
            this.os.close();
        }
        catch (Exception ex) {}
        try {
            this.is.close();
        }
        catch (Exception ex2) {}
        try {
            this.socket.close();
        }
        catch (Exception ex3) {}
        this.socket = null;
    }
    
    private int b(String s) {
        final int index = s.indexOf(32);
        if (index == -1) {
            return 501;
        }
        s = s.substring(index + 1);
        final int index2 = s.indexOf(32);
        if (index2 == -1) {
            return 501;
        }
        s = s.substring(0, index2);
        return Integer.parseInt(s);
    }
    
    private int c(String substring) {
        final int index = substring.indexOf(32);
        if (index == -1) {
            return 0;
        }
        substring = substring.substring(index + 1);
        return Integer.parseInt(substring);
    }
    
    private String d(String substring) {
        final int index = substring.indexOf(32);
        if (index == -1) {
            return null;
        }
        substring = substring.substring(index + 1);
        return substring;
    }
    
    public String getLastModified() {
        return this.d;
    }
    
    private String e(final String s) {
        final int index = s.indexOf("realm=");
        if (index == -1) {
            return null;
        }
        String s2 = s.substring(index + 6);
        final int length = s2.length();
        if (s2.charAt(0) == '\"' && s2.charAt(length - 1) == '\"') {
            s2 = s2.substring(1, length - 1);
        }
        return s2;
    }
    
    public int getPort(final URL url) {
        final int port = url.getPort();
        if (port >= 0) {
            return port;
        }
        String protocol = url.getProtocol();
        if (protocol == null) {
            protocol = "http";
        }
        if (protocol.equalsIgnoreCase("http")) {
            return 80;
        }
        if (protocol.equalsIgnoreCase("ftp")) {
            return 21;
        }
        return -1;
    }
    
    public static byte[] readBytes(final InputStream inputStream, final int n) {
        byte[] byteArrayConcat = new byte[0];
        final int n2 = 1024;
        byte[] array = new byte[n2];
        int i = 0;
        try {
            while (i < n) {
                final int read = inputStream.read(array, 0, (n - i > n2) ? n2 : (n - i));
                byteArrayConcat = byteArrayConcat(byteArrayConcat, byteArrayConcat.length, array, read);
                array = new byte[1024];
                i += read;
            }
        }
        catch (IOException ex) {
            return null;
        }
        if (byteArrayConcat.length == 0) {
            return null;
        }
        return byteArrayConcat;
    }
    
    public static byte[] readBytesFully(final InputStream inputStream) {
        byte[] byteArrayConcat = new byte[0];
        byte[] array = new byte[1024];
        try {
            int read;
            while ((read = inputStream.read(array)) != -1) {
                byteArrayConcat = byteArrayConcat(byteArrayConcat, byteArrayConcat.length, array, read);
                array = new byte[1024];
            }
        }
        catch (IOException ex) {
            return null;
        }
        if (byteArrayConcat.length == 0) {
            return null;
        }
        return byteArrayConcat;
    }
    
    public static byte[] byteArrayConcat(final byte[] array, final int n, final byte[] array2, final int n2) {
        if (array == null || array2 == null || n < 0 || n2 < 0) {
            return null;
        }
        final byte[] array3 = new byte[n + n2];
        System.arraycopy(array, 0, array3, 0, n);
        System.arraycopy(array2, 0, array3, n, n2);
        return array3;
    }
    
    public static void main(final String[] array) {
        if (array.length != 3) {
            NFDebug.print("usage: NFHttpClient <host> <port> <file>");
            System.exit(-1);
        }
        final String s = array[0];
        final int int1 = Integer.parseInt(array[1]);
        final String s2 = array[2];
        try {
            final NFHttpClient nfHttpClient = new NFHttpClient();
            final InputStream inputStream = nfHttpClient.getInputStream(new URL("http://" + s + ":" + int1 + s2));
            NFDebug.print("Status = " + nfHttpClient.getStatus());
            if (inputStream == null) {
                NFDebug.print("Input Stream is NULL");
            }
            else {
                final byte[] array2 = new byte[4096];
                int read;
                while ((read = inputStream.read(array2)) > 0) {
                    System.out.write(array2, 0, read);
                }
            }
            nfHttpClient.close();
        }
        catch (Exception ex) {}
    }
}
