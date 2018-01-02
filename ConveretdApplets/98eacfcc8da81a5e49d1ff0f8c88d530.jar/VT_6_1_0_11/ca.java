// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.File;
import java.net.HttpURLConnection;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Container;
import java.util.StringTokenizer;
import java.awt.Window;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.Enumeration;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import java.io.EOFException;
import java.io.DataInputStream;
import java.applet.Applet;
import java.awt.Component;
import java.util.Observable;

public final class ca implements Runnable
{
    private static byte a;
    private static byte b;
    private static byte c;
    private static byte d;
    private static byte e;
    private static byte f;
    private static byte g;
    private static byte h;
    private static byte i;
    private static byte j;
    private static byte k;
    private static Integer l;
    private long m;
    private long n;
    private Observable o;
    private static String p;
    private static String q;
    
    public ca() {
        this.m = 0L;
        this.n = 1L;
        this.o = new Observable();
    }
    
    public static void a(final Component component, final Component component2) {
        if (component == null || component2 == null) {
            return;
        }
        component2.setLocation((component.getSize().width - component2.getSize().width) / 2, (component.getSize().height - component2.getSize().height) / 3);
    }
    
    public static String a(final Applet applet) {
        String a;
        if ((a = a(applet, "context_path", "/wimba")) == null || a.equals("null") || a.length() == 0) {
            a = "/wimba";
        }
        String s;
        if (!(s = a.trim()).startsWith("/")) {
            s = "/" + s;
        }
        if (s.endsWith("/")) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }
    
    public static Object a(final DataInputStream dataInputStream) {
        byte byte1;
        try {
            byte1 = dataInputStream.readByte();
        }
        catch (EOFException ex) {
            return null;
        }
        if (byte1 == 0) {
            return null;
        }
        if (byte1 == ca.b) {
            return dataInputStream.readBoolean();
        }
        if (byte1 == ca.d) {
            return new Integer(dataInputStream.readInt());
        }
        if (byte1 == ca.e) {
            return new Long(dataInputStream.readLong());
        }
        if (byte1 == ca.c) {
            return new Byte(dataInputStream.readByte());
        }
        if (byte1 == ca.f) {
            return dataInputStream.readUTF();
        }
        if (byte1 == ca.g) {
            final int int1 = dataInputStream.readInt();
            final Vector<Object> vector = new Vector<Object>();
            for (int i = 0; i < int1; ++i) {
                vector.addElement(a(dataInputStream));
            }
            return vector;
        }
        if (byte1 == ca.h) {
            final int int2 = dataInputStream.readInt();
            final Hashtable hashtable = new Hashtable<Object, Object>(int2);
            for (int j = 0; j < int2; ++j) {
                hashtable.put(a(dataInputStream), a(dataInputStream));
            }
            return hashtable;
        }
        if (byte1 == ca.i) {
            final byte[] array = new byte[dataInputStream.readInt()];
            dataInputStream.readFully(array);
            return array;
        }
        throw new IOException("ClientUtil.readObjectFromStream: Unknown type=" + byte1);
    }
    
    public static void a(final Object o, final DataOutputStream dataOutputStream) {
        if (o == null) {
            dataOutputStream.writeByte(0);
            return;
        }
        if (o instanceof Boolean) {
            dataOutputStream.writeByte(ca.b);
            dataOutputStream.writeBoolean((boolean)o);
            return;
        }
        if (o instanceof Integer) {
            dataOutputStream.writeByte(ca.d);
            dataOutputStream.writeInt((int)o);
            return;
        }
        if (o instanceof Long) {
            dataOutputStream.writeByte(ca.e);
            dataOutputStream.writeLong((long)o);
            return;
        }
        if (o instanceof Byte) {
            dataOutputStream.writeByte(ca.c);
            dataOutputStream.writeByte((byte)o);
            return;
        }
        if (o instanceof String) {
            dataOutputStream.writeByte(ca.f);
            dataOutputStream.writeUTF((String)o);
            return;
        }
        if (o instanceof Vector) {
            dataOutputStream.writeByte(ca.g);
            synchronized (o) {
                final Vector vector = (Vector)o;
                dataOutputStream.writeInt(vector.size());
                for (int i = 0; i < vector.size(); ++i) {
                    a(vector.elementAt(i), dataOutputStream);
                }
                return;
            }
        }
        if (o instanceof Hashtable) {
            dataOutputStream.writeByte(ca.h);
            synchronized (o) {
                final Hashtable hashtable = (Hashtable)o;
                dataOutputStream.writeInt(hashtable.size());
                final Enumeration<Object> keys = hashtable.keys();
                while (keys.hasMoreElements()) {
                    final Object nextElement;
                    a(nextElement = keys.nextElement(), dataOutputStream);
                    a(hashtable.get(nextElement), dataOutputStream);
                }
                return;
            }
        }
        if (o instanceof byte[]) {
            dataOutputStream.writeByte(ca.i);
            final byte[] array = (byte[])o;
            dataOutputStream.writeInt(array.length);
            dataOutputStream.write(array);
            return;
        }
        throw new IOException("ClientUtil.writeObjectFromInStream: Object type not supported: " + o.getClass());
    }
    
    public static byte[] a(final URL url, final byte[] array, final int n, int n2) {
        final URLConnection a;
        (a = a(url)).setUseCaches(false);
        a.setDoOutput(true);
        a.setDoInput(true);
        a.setRequestProperty("Content-Type", "application/octet-stream");
        a.setRequestProperty("Content-Length", "" + n2);
        final OutputStream outputStream;
        (outputStream = a.getOutputStream()).write(array, 0, n2);
        outputStream.flush();
        outputStream.close();
        a.connect();
        final InputStream inputStream = a.getInputStream();
        final int contentLength = a.getContentLength();
        n2 = 0;
        byte[] byteArray;
        if (contentLength >= 0) {
            int read;
            for (byteArray = new byte[contentLength]; n2 < contentLength && (read = inputStream.read(byteArray, n2, contentLength - n2)) != -1; n2 += read) {}
            if (n2 != contentLength) {
                throw new IOException("cu.hgr:bad nb bytes read");
            }
        }
        else {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array2 = new byte[1024];
            int read2;
            while ((read2 = inputStream.read(array2, 0, 1024)) != -1) {
                n2 += read2;
                byteArrayOutputStream.write(array2, 0, read2);
            }
            if (n2 <= 0) {
                throw new IOException("cu.hpr: 0 bytes read");
            }
            byteArray = byteArrayOutputStream.toByteArray();
        }
        inputStream.close();
        return byteArray;
    }
    
    public final void run() {
        final String name = Thread.currentThread().getName();
        try {
            Label_0265: {
                if (name.equals(ca.p)) {
                    try {
                        final URLConnection a = a(null, false);
                        final DataInputStream dataInputStream = new DataInputStream(a.getInputStream());
                        final int contentLength;
                        if ((contentLength = a.getContentLength()) <= 0) {
                            System.err.println("!!!!!!!!!!! ClientUtil.drMHGR: contentlength=" + contentLength);
                        }
                        this.n = contentLength;
                        final byte[] array = new byte[1024];
                        final DataInputStream dataInputStream2 = dataInputStream;
                        int n = 0;
                        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        int read;
                        while ((read = dataInputStream2.read(array, 0, 1024)) != -1) {
                            n += read;
                            this.m += read;
                            byteArrayOutputStream.write(array, 0, read);
                            System.currentTimeMillis();
                        }
                        dataInputStream2.close();
                        byteArrayOutputStream.toByteArray();
                        if (contentLength >= 0 && contentLength != n) {
                            throw new IOException("Bad # bytes read:contentLength=" + contentLength + ", bytesRead=" + n);
                        }
                    }
                    catch (IOException ex) {
                        System.out.println("CU.rdmhgr:");
                        ex.printStackTrace();
                    }
                    Thread.yield();
                    synchronized (this) {
                        this.notify();
                        break Label_0265;
                    }
                }
                if (name.equals(ca.q)) {
                    Thread.sleep(0L);
                    null.dispose();
                }
            }
        }
        catch (InterruptedException ex2) {
            ex2.printStackTrace();
            Thread.currentThread().interrupt();
        }
        Thread.currentThread().interrupt();
    }
    
    private static boolean a(final char c) {
        return c >= '0' && c <= '9';
    }
    
    private static boolean b(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
    
    private static boolean d(String s) {
        if (!(s = s.trim()).equals("") && s.indexOf(64) > 0 && s.indexOf(64) == s.lastIndexOf(64) && s.indexOf(46) != -1 && s.indexOf(64) < s.lastIndexOf(46) - 1 && s.indexOf(32) == -1 && s.indexOf("..") == -1 && s.indexOf("@.") == -1) {
            s = s.substring(s.indexOf(64) + 1);
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ".");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                for (int i = 0; i < nextToken.length() - 1; ++i) {
                    final char char1;
                    if (!b(char1 = nextToken.charAt(i)) && !a(char1) && char1 != '-') {
                        return false;
                    }
                }
                final char char2;
                if (!b(char2 = nextToken.charAt(nextToken.length() - 1)) && !a(char2)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public static final boolean a(final String s) {
        if (s == null || s.trim().length() == 0) {
            return false;
        }
        if (s.indexOf(44) != -1 || s.indexOf(59) != -1 || s.indexOf(60) != s.lastIndexOf(60) || s.indexOf(62) != s.lastIndexOf(62)) {
            return false;
        }
        final int index;
        if ((index = s.indexOf(60)) != -1) {
            final int index2;
            return (index2 = s.indexOf(62, index)) != -1 && d(s.substring(index + 1, index2).trim());
        }
        return d(s.trim());
    }
    
    public static final boolean b(final String s) {
        if (s == null) {
            return false;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",;\n");
        while (stringTokenizer.hasMoreTokens()) {
            final String trim;
            if (!(trim = stringTokenizer.nextToken().trim()).equals("") && !a(trim)) {
                return false;
            }
        }
        return true;
    }
    
    public static final int c(final String s) {
        if (s == null) {
            return 0;
        }
        return new StringTokenizer(s, ",;\n").countTokens();
    }
    
    public static GridBagConstraints a(final Container container, final Component component, final int gridx, final int gridy, final int gridwidth, final int n, final double weightx, final double weighty, final int n2, final int n3, final int n4, final int n5) {
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).fill = 1;
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = weightx;
        gridBagConstraints.weighty = weighty;
        gridBagConstraints.insets = new Insets(n2, n3, n4, n5);
        container.add(component);
        ((GridBagLayout)container.getLayout()).setConstraints(component, gridBagConstraints);
        return gridBagConstraints;
    }
    
    public static Vector a(final String s, String trim) {
        final Vector<String> vector = new Vector<String>();
        if (s == null) {
            return vector;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, trim);
        while (stringTokenizer.hasMoreTokens()) {
            if (!(trim = stringTokenizer.nextToken().trim()).equals("")) {
                vector.addElement(trim);
            }
        }
        return vector;
    }
    
    public static Color a(String trim, final Color color) {
        if (trim == null) {
            return color;
        }
        if ((trim = trim.trim()).equalsIgnoreCase("black")) {
            return Color.black;
        }
        if (trim.equalsIgnoreCase("blue")) {
            return Color.blue;
        }
        if (trim.equalsIgnoreCase("cyan")) {
            return Color.cyan;
        }
        if (trim.equalsIgnoreCase("darkGray")) {
            return Color.darkGray;
        }
        if (trim.equalsIgnoreCase("gray")) {
            return Color.gray;
        }
        if (trim.equalsIgnoreCase("green")) {
            return Color.green;
        }
        if (trim.equalsIgnoreCase("lightGray")) {
            return Color.lightGray;
        }
        if (trim.equalsIgnoreCase("magenta")) {
            return Color.magenta;
        }
        if (trim.equalsIgnoreCase("orange")) {
            return Color.orange;
        }
        if (trim.equalsIgnoreCase("pink")) {
            return Color.pink;
        }
        if (trim.equalsIgnoreCase("red")) {
            return Color.red;
        }
        if (trim.equalsIgnoreCase("white")) {
            return Color.white;
        }
        if (trim.equalsIgnoreCase("yellow")) {
            return Color.yellow;
        }
        return new Color(Integer.valueOf(trim.substring(1, 3), 16), Integer.valueOf(trim.substring(3, 5), 16), Integer.valueOf(trim.substring(5, 7), 16));
    }
    
    public static Thread a(final Thread thread) {
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }
        return null;
    }
    
    public static URLConnection a(final URLConnection urlConnection) {
        if (urlConnection != null && urlConnection instanceof HttpURLConnection) {
            ((HttpURLConnection)urlConnection).disconnect();
        }
        return urlConnection;
    }
    
    public static InputStream a(final InputStream inputStream) {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static String a(final Applet applet, final String s, final String s2) {
        String parameter = null;
        if (applet != null) {
            parameter = applet.getParameter(s);
        }
        if (parameter == null || parameter.length() == 0) {
            parameter = s2;
        }
        return parameter;
    }
    
    public static Color a(final Applet applet, final String s, final Color color) {
        return a(applet.getParameter(s), color);
    }
    
    public static int a(final Applet applet, final String s, final int n) {
        try {
            return Integer.parseInt(applet.getParameter(s).trim());
        }
        catch (NumberFormatException ex) {
            return n;
        }
        catch (NullPointerException ex2) {
            return n;
        }
    }
    
    public static boolean a(final Applet applet, final String s, final boolean b) {
        try {
            return applet.getParameter(s).equalsIgnoreCase("true");
        }
        catch (NumberFormatException ex) {
            return false;
        }
        catch (NullPointerException ex2) {
            return false;
        }
    }
    
    public static String a(String s, String s2, String s3) {
        final String s4 = s;
        final String s5 = s2;
        final String s6 = s3;
        int n = 0;
        s3 = s6;
        s2 = s5;
        s = s4;
        if (s4 == null) {
            return null;
        }
        final StringBuffer sb = new StringBuffer(s.substring(0, 0));
        int index;
        while ((index = s.indexOf(s2, n)) != -1) {
            sb.append(s.substring(n, index));
            sb.append(s3);
            n = index + s2.length();
        }
        sb.append(s.substring(n));
        return sb.toString();
    }
    
    public static URLConnection a(final URL url) {
        if (bj.b()) {
            try {
                return url.openConnection();
            }
            catch (Exception ex) {
                return new sun.net.www.protocol.http.HttpURLConnection(url, url.getHost(), (url.getPort() != 0) ? url.getPort() : 80);
            }
        }
        return url.openConnection();
    }
    
    public static URLConnection a(final URL url, final boolean b) {
        URLConnection a;
        (a = a(url)).setUseCaches(b);
        if (!bj.b()) {
            a.connect();
        }
        else {
            try {
                a.connect();
                a.getInputStream();
            }
            catch (Exception ex2) {
                final Exception ex = ex2;
                if (!(ex2 instanceof IOException) && !(ex instanceof NullPointerException)) {
                    throw new IOException(ex.toString());
                }
                System.err.println("CU.getUrlGetConn: URLConnection Mac bug");
                (a = new sun.net.www.protocol.http.HttpURLConnection(url, url.getHost(), (url.getPort() != 0) ? url.getPort() : 80)).setUseCaches(b);
                a.connect();
                System.err.println("CU.getUrlGetConn: URLConection Mac bug solved !");
            }
        }
        return a;
    }
    
    public static String a(final File file) {
        String lowerCase = null;
        final String name;
        final int lastIndex;
        if ((lastIndex = (name = file.getName()).lastIndexOf(46)) > 0 && lastIndex < name.length() - 1) {
            lowerCase = name.substring(lastIndex + 1).toLowerCase();
        }
        return lowerCase;
    }
    
    static {
        ca.a = 0;
        ca.b = 1;
        ca.c = 2;
        ca.d = 3;
        ca.e = 4;
        ca.f = 7;
        ca.g = 10;
        ca.h = 11;
        ca.i = 102;
        ca.j = 2;
        ca.k = 3;
        ca.l = new Integer(1);
        ca.p = "dmhgrt";
        ca.q = "cdt";
    }
}
