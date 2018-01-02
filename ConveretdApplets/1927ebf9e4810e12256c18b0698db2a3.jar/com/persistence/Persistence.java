// 
// Decompiled by Procyon v0.5.30
// 

package com.persistence;

import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.io.PrintWriter;
import java.net.SocketException;
import java.io.InputStream;
import java.io.IOException;
import java.net.Socket;
import java.applet.Applet;
import java.util.Random;

public class Persistence
{
    private String hostname;
    private int port;
    private int itemid;
    private static boolean useFilesystem;
    static Random random;
    private static final String putSmallPath = "/persistence/put_small";
    private static final String getSmallPath = "/persistence/get_small";
    private static final String getLargePath = "/persistence/get_large";
    private static final String putLargePath = "/persistence/put_large";
    private static final int socketRetries = 3;
    private static final int boundaryLength = 20;
    private static final String datadirpath = "persistence_data/";
    
    public Persistence(final int itemid) {
        this.itemid = itemid;
        useFilesystem(true);
    }
    
    Persistence(final String hostname, final int port, final int itemid) {
        this.hostname = hostname;
        this.port = port;
        this.itemid = itemid;
    }
    
    public Persistence(final Applet applet) {
        this(applet.getCodeBase().getHost(), applet.getCodeBase().getPort(), Integer.parseInt(applet.getParameter("itemid")));
    }
    
    public static void useFilesystem(final boolean useFilesystem) {
        Persistence.useFilesystem = useFilesystem;
    }
    
    private Socket getSocket() throws IOException {
        return new Socket(this.hostname, this.port);
    }
    
    private String generateBoundary() {
        String concat = "";
        for (int i = 0; i < 20; ++i) {
            concat = String.valueOf(concat).concat(String.valueOf(97 + Persistence.random.nextInt() % 26));
        }
        return concat;
    }
    
    private void skipHeaders(final InputStream inputStream) throws IOException {
        int i = 0;
        boolean b = false;
        while (i < 4) {
            final int read = inputStream.read();
            if (read == -1) {
                break;
            }
            if (read == (b ? 10 : 13)) {
                ++i;
                b = !b;
            }
            else {
                i = 0;
            }
        }
    }
    
    void putSmall(final String s, final String s2) throws IOException {
        this.getOrPutSmallOrLarge(false, true, s, s2);
    }
    
    void putLarge(final String s, final byte[] array) throws IOException {
        this.getOrPutSmallOrLarge(false, false, s, array);
    }
    
    String getSmall(final String s) throws IOException {
        return (String)this.getOrPutSmallOrLarge(true, true, s, null);
    }
    
    byte[] getLarge(final String s) throws IOException {
        return (byte[])this.getOrPutSmallOrLarge(true, false, s, null);
    }
    
    private Object getOrPutSmallOrLarge(final boolean b, final boolean b2, final String s, final Object o) throws IOException {
        if (Persistence.useFilesystem) {
            return this.getOrPutSmallOrLargeFileSystem(b, b2, s, o);
        }
        Socket socket = null;
        int n = 0;
        final boolean b3 = false;
        boolean b4 = false;
        while (!b3 && n < 3) {
            try {
                socket = this.getSocket();
                if (n > 0) {
                    System.out.println(String.valueOf("Retry #").concat(String.valueOf(n)));
                }
                if (!b) {
                    if (b2) {
                        this.putSmallImpl(s, (String)o, socket);
                    }
                    else {
                        this.putLargeImpl(s, (byte[])o, socket);
                    }
                    b4 = true;
                    return null;
                }
                if (b2) {
                    final String smallImpl = this.getSmallImpl(s, socket);
                    b4 = true;
                    return smallImpl;
                }
                final byte[] largeImpl = this.getLargeImpl(s, socket);
                b4 = true;
                return largeImpl;
            }
            catch (SocketException ex) {
                if (!ex.toString().equals("java.net.SocketException: Broken pipe")) {
                    throw ex;
                }
                ++n;
            }
            finally {
                if (b4 && n > 0) {
                    System.out.println(String.valueOf(String.valueOf("Retry #").concat(String.valueOf(n))).concat(String.valueOf(" worked!")));
                }
                try {
                    if (socket != null) {
                        socket.close();
                    }
                }
                catch (IOException ex2) {
                    System.out.println(String.valueOf("Error closing socket: ").concat(String.valueOf(ex2)));
                }
            }
        }
        return null;
    }
    
    private void putSmallImpl(final String s, final String s2, final Socket socket) throws IOException {
        final PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("POST /persistence/put_small HTTP/1.0");
        printWriter.println("Content-type: application/octet-stream");
        final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("itemid=").concat(String.valueOf(this.itemid))).concat(String.valueOf("&key="))).concat(String.valueOf(s))).concat(String.valueOf("&value="))).concat(String.valueOf(URLEncoder.encode(s2)));
        printWriter.println(String.valueOf("Content-length: ").concat(String.valueOf(concat.length())));
        printWriter.println();
        printWriter.print(concat);
        printWriter.flush();
        final InputStream inputStream = socket.getInputStream();
        this.skipHeaders(inputStream);
        final int read = inputStream.read();
        if (read != 49) {
            throw new IOException(String.valueOf(String.valueOf("Server returned '").concat(String.valueOf(read))).concat(String.valueOf("'")));
        }
    }
    
    private String getSmallImpl(final String s, final Socket socket) throws IOException {
        final PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("POST /persistence/get_small HTTP/1.0");
        printWriter.println("Content-type: application/octet-stream");
        final String concat = String.valueOf(String.valueOf(String.valueOf("itemid=").concat(String.valueOf(this.itemid))).concat(String.valueOf("&key="))).concat(String.valueOf(s));
        printWriter.println(String.valueOf("Content-length: ").concat(String.valueOf(concat.length())));
        printWriter.println();
        printWriter.print(concat);
        printWriter.flush();
        final InputStream inputStream = socket.getInputStream();
        this.skipHeaders(inputStream);
        final int read = inputStream.read();
        if (read == 1) {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int read2;
            while ((read2 = inputStream.read()) != -1) {
                byteArrayOutputStream.write(read2);
            }
            return byteArrayOutputStream.toString();
        }
        if (read == 2) {
            throw new PersistenceException(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Value not found ( itemid=").concat(String.valueOf(this.itemid))).concat(String.valueOf(", key="))).concat(String.valueOf(s))).concat(String.valueOf(")")));
        }
        throw new IOException("Bad content returned from server.");
    }
    
    private String makePutLargeQueryString(final String s, final byte[] array, final String s2) {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("").concat(String.valueOf(String.valueOf(String.valueOf("--").concat(String.valueOf(s2))).concat(String.valueOf("\r\n"))))).concat(String.valueOf("Content-Disposition: form-data; name=\"itemid\"\r\n\r\n"))).concat(String.valueOf(this.itemid))).concat(String.valueOf("\r\n"))).concat(String.valueOf(String.valueOf(String.valueOf("--").concat(String.valueOf(s2))).concat(String.valueOf("\r\n"))))).concat(String.valueOf("Content-Disposition: form-data; name=\"key\"\r\n\r\n"))).concat(String.valueOf(s))).concat(String.valueOf("\r\n"))).concat(String.valueOf(String.valueOf(String.valueOf("--").concat(String.valueOf(s2))).concat(String.valueOf("\r\n"))))).concat(String.valueOf("Content-Disposition: form-data; name=\"value\"; filename=\"file.txt\"\r\n"))).concat(String.valueOf("Content-Type: text/plain\r\n\r\n"))).concat(String.valueOf(new String(array)))).concat(String.valueOf("\r\n"))).concat(String.valueOf(String.valueOf(String.valueOf("--").concat(String.valueOf(s2))).concat(String.valueOf("--\r\n"))));
    }
    
    private void putLargeImpl(final String s, final byte[] array, final Socket socket) throws IOException {
        final String generateBoundary = this.generateBoundary();
        final PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("POST /persistence/put_large HTTP/1.0");
        printWriter.println(String.valueOf("Content-type: multipart/form-data; boundary=").concat(String.valueOf(generateBoundary)));
        final String putLargeQueryString = this.makePutLargeQueryString(s, array, generateBoundary);
        printWriter.println(String.valueOf("Content-Length: ").concat(String.valueOf(putLargeQueryString.length())));
        printWriter.println("");
        printWriter.print(putLargeQueryString);
        printWriter.flush();
        final InputStream inputStream = socket.getInputStream();
        this.skipHeaders(inputStream);
        final int read = inputStream.read();
        if (read != 49) {
            throw new IOException(String.valueOf(String.valueOf("Server returned '").concat(String.valueOf(read))).concat(String.valueOf("'")));
        }
    }
    
    private byte[] getLargeImpl(final String s, final Socket socket) throws IOException {
        final PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("POST /persistence/get_large HTTP/1.0");
        printWriter.println("Content-type: application/octet-stream");
        final String concat = String.valueOf(String.valueOf(String.valueOf("itemid=").concat(String.valueOf(this.itemid))).concat(String.valueOf("&key="))).concat(String.valueOf(s));
        printWriter.println(String.valueOf("Content-Length: ").concat(String.valueOf(concat.length())));
        printWriter.println("");
        printWriter.print(concat);
        printWriter.flush();
        final InputStream inputStream = socket.getInputStream();
        this.skipHeaders(inputStream);
        final int read = inputStream.read();
        if (read == 1) {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int read2;
            while ((read2 = inputStream.read()) != -1) {
                byteArrayOutputStream.write(read2);
            }
            return byteArrayOutputStream.toByteArray();
        }
        if (read == 2) {
            throw new PersistenceException(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Value not found ( itemid=").concat(String.valueOf(this.itemid))).concat(String.valueOf(", key="))).concat(String.valueOf(s))).concat(String.valueOf(")")));
        }
        throw new IOException("Bad content returned from server.");
    }
    
    private Object getOrPutSmallOrLargeFileSystem(final boolean b, final boolean b2, final String s, final Object o) throws IOException {
        final File file = new File("persistence_data/");
        if (!file.exists()) {
            file.mkdir();
        }
        final String concat = String.valueOf(String.valueOf(String.valueOf("persistence_data/").concat(String.valueOf(this.itemid))).concat(String.valueOf("-"))).concat(String.valueOf(s));
        if (!b) {
            final FileOutputStream fileOutputStream = new FileOutputStream(concat);
            fileOutputStream.write(b2 ? ((String)o).getBytes() : ((byte[])o));
            fileOutputStream.close();
            return null;
        }
        final File file2 = new File(concat);
        if (!file2.exists()) {
            throw new PersistenceException(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Value not found ( itemid=").concat(String.valueOf(this.itemid))).concat(String.valueOf(", key="))).concat(String.valueOf(s))).concat(String.valueOf(")")));
        }
        final byte[] array = new byte[(int)file2.length()];
        final int read = new FileInputStream(file2).read(array);
        if (read != array.length) {
            throw new IOException(String.valueOf(String.valueOf(String.valueOf("Short read: ").concat(String.valueOf(read))).concat(String.valueOf(" instead of "))).concat(String.valueOf(array.length)));
        }
        if (b2) {
            return new String(array);
        }
        return array;
    }
    
    public String getString(final String s) throws IOException {
        return new String(this.getSmall(s));
    }
    
    public void putString(final String s, final String s2) throws IOException {
        this.putSmall(s, s2);
    }
    
    public int getInt(final String s) throws IOException {
        return Integer.parseInt(new String(this.getSmall(s)));
    }
    
    public void putInt(final String s, final int n) throws IOException {
        this.putSmall(s, String.valueOf(n).concat(String.valueOf("")));
    }
    
    public byte[] getRaw(final String s) throws IOException {
        return this.getLarge(s);
    }
    
    public void putRaw(final String s, final byte[] array) throws IOException {
        this.putLarge(s, array);
    }
    
    public InputStream getInputStream(final String s) throws IOException {
        return new ByteArrayInputStream(this.getLarge(s));
    }
    
    public OutputStream getOutputStream(final String s) throws IOException {
        return new PersistenceOutputStream(this, s);
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.hostname).concat(String.valueOf(":"))).concat(String.valueOf(this.port))).concat(String.valueOf(":"))).concat(String.valueOf(this.itemid));
    }
    
    static {
        Persistence.useFilesystem = false;
        Persistence.random = new Random();
    }
}
