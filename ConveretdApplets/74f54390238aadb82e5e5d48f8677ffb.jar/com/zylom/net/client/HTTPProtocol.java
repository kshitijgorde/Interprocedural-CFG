// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.net.client;

import java.util.Enumeration;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Vector;
import java.net.URLEncoder;
import java.util.zip.CRC32;
import com.zylom.cipher.CipherString;
import java.net.URLConnection;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.Socket;
import java.util.Random;

public class HTTPProtocol extends Protocol
{
    private static final Random random;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedInputStream bin;
    private URLConnection urlConnection;
    private Location location;
    private boolean useGet;
    
    private static String decryptServletParameters(final String s) {
        final String[] array = { s.substring(11) };
        array[0] = CipherString.hexDecode(array[0]);
        final char char1 = array[0].charAt(0);
        array[0] = array[0].substring(1);
        return array[0] = CipherString.cipher(array[0], char1);
    }
    
    public static String encryptServletParameters(String s) {
        if (s != null) {
            if (s.startsWith("?")) {
                s = s.substring(1);
            }
            final CRC32 crc32 = new CRC32();
            crc32.update(s.getBytes());
            s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf("&crc32=".concat(String.valueOf(String.valueOf(String.valueOf(crc32.getValue())))))));
            final int n = Math.abs(HTTPProtocol.random.nextInt()) % 127;
            return "cipherdata=".concat(String.valueOf(String.valueOf(URLEncoder.encode(CipherString.hexEncode(String.valueOf(String.valueOf((char)n)).concat(String.valueOf(String.valueOf(CipherString.cipher(s, n)))))))));
        }
        return null;
    }
    
    public static final String encryptServletParameters(final Vector vector) {
        String concat = "";
        if (vector != null && vector.size() > 0) {
            vector.addElement("".concat(String.valueOf(String.valueOf(System.currentTimeMillis()))));
            String s = "";
            for (int i = 0; i < vector.size(); ++i) {
                if (i > 0) {
                    s = String.valueOf(String.valueOf(s)).concat("&");
                }
                s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(i))).append("=").append(URLEncoder.encode("".concat(String.valueOf(String.valueOf(vector.elementAt(i)))))))))));
            }
            final int abs = Math.abs(HTTPProtocol.random.nextInt() % 127);
            concat = "cipherdata=".concat(String.valueOf(String.valueOf(URLEncoder.encode(String.valueOf(String.valueOf(CipherString.hexEncode(new CipherString().encrypt(s, abs)))).concat(String.valueOf(String.valueOf(CipherString.hexEncode("".concat(String.valueOf(String.valueOf((char)abs)))))))))));
        }
        return concat;
    }
    
    public void useGet() {
        this.useGet = true;
    }
    
    public void usePost() {
        this.useGet = false;
    }
    
    void open(final Connection connection, final Location location, final Socket socket, final InputStream inputStream, final OutputStream outputStream) throws IOException {
        this.location = location;
        this.socket = socket;
        if (connection.getUseBrowser()) {
            String s = location.getFile();
            if (!s.startsWith("/")) {
                s = "/".concat(String.valueOf(String.valueOf(s)));
            }
            (this.urlConnection = new URL("http", location.getHost(), location.getPort(), s).openConnection()).setDoOutput(true);
            this.urlConnection.setDoInput(true);
            this.urlConnection.setUseCaches(false);
            this.urlConnection.setAllowUserInteraction(true);
            this.urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            this.out = new PrintWriter(this.urlConnection.getOutputStream());
        }
        else {
            this.in = new BufferedReader(new InputStreamReader(inputStream));
            this.bin = new BufferedInputStream(inputStream);
            this.out = new PrintWriter(outputStream);
        }
    }
    
    void close(final Connection connection) {
        this.location = null;
        this.socket = null;
        this.in = null;
        this.out = null;
    }
    
    void sendData(final Connection connection, final PDU pdu) throws IOException {
        if (!connection.isOpen()) {
            connection.open();
        }
        if (pdu != null) {
            String s = null;
            final Enumeration names = pdu.names();
            while (names.hasMoreElements()) {
                final String s2 = names.nextElement();
                if (s == null) {
                    s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(URLEncoder.encode(s2)))).append("=").append(URLEncoder.encode(pdu.getString(s2)))));
                }
                else {
                    s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("&").append(URLEncoder.encode(s2)).append("=").append(URLEncoder.encode(pdu.getString(s2))))))));
                }
            }
            if (s.length() > 0 && connection.isEncrypted()) {
                s = encryptServletParameters(s);
            }
            if (this.urlConnection != null) {
                this.out.print(s);
            }
            else if (this.useGet) {
                if (s.length() > 0) {
                    s = "?".concat(String.valueOf(String.valueOf(s)));
                }
                this.out.print(String.valueOf(String.valueOf(new StringBuffer("GET /").append(this.location.getFile()).append(s).append(" HTTP/1.1\r\nHost: ").append(this.location.getHost()).append("\r\nCache-Control: no-cache\r\nUser-Agent: Zylom HTTPProtocol\r\n\r\n"))));
            }
            else {
                this.out.print(String.valueOf(String.valueOf(new StringBuffer("POST /").append(this.location.getFile()).append(" HTTP/1.1\r\nHost: ").append(this.location.getHost()).append("\r\nContent-Type: application/x-www-form-urlencoded\r\nCache-Control: no-cache\r\n").append("User-Agent: Zylom HTTPProtocol\r\nContent-Length: ").append(s.length()).append("\r\n\r\n").append(s))));
            }
            this.out.flush();
            connection.fireDataSend(new ConnectionEvent(connection, pdu));
            int n = 0;
            int n2 = 0;
            String s3 = null;
            String s4 = null;
            if (this.urlConnection != null) {
                this.out.close();
                this.in = new BufferedReader(new InputStreamReader(this.urlConnection.getInputStream()));
                this.bin = new BufferedInputStream(this.urlConnection.getInputStream());
                n = this.urlConnection.getContentLength();
            }
            else {
                this.in.readLine();
                if (s4 != null) {
                    final boolean b = s4.indexOf("200") >= 0;
                    s4 = this.in.readLine();
                }
                while (s4 != null && s4.length() > 0) {
                    if (s4.toLowerCase().startsWith("content-length")) {
                        n = Integer.parseInt(s4.substring(s4.indexOf(":") + 1).trim());
                    }
                    else if (s4.toLowerCase().startsWith("connection")) {
                        n2 = ((s4.toLowerCase().indexOf("close") > 0) ? 1 : 0);
                    }
                    else if (s4.toLowerCase().startsWith("content-type")) {
                        s3 = s4.substring(s4.indexOf(":") + 1).trim();
                        final int index = s3.indexOf(";");
                        if (index >= 0) {
                            s3 = s3.substring(0, index).trim();
                        }
                    }
                    s4 = this.in.readLine();
                }
            }
            if (n > 0) {
                int n3 = 0;
                int n4 = n;
                int i = 1;
                byte[] array = new byte[n];
                while (i > 0) {
                    i = this.bin.read(array, n3, n4);
                    if (i > 0) {
                        n3 += i;
                        n4 -= i;
                    }
                }
                if (n4 > 0) {
                    final byte[] array2 = array;
                    array = new byte[array.length - n4];
                    System.arraycopy(array2, 0, array, 0, array.length);
                }
                connection.queuePDU(new PDU(s3, array));
            }
            if (n2 != 0) {
                connection.close();
            }
        }
    }
    
    static {
        random = new Random(System.currentTimeMillis());
    }
}
