// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.io.DataInputStream;
import java.io.StringBufferInputStream;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.util.Enumeration;
import java.io.IOException;
import java.util.Vector;
import java.util.Hashtable;

public class NFResourceItem extends Hashtable
{
    public static final String m_statusOK = "OK";
    private static final String a = "STATUS";
    private static final String b = "RECORDS";
    private static final String c = "NFResourceItem";
    private static final String d = "|";
    private static final String e = " ";
    
    public Object get(final Object o) {
        final Object value = super.get(o);
        if (value == null) {
            return "UNKNOWN";
        }
        return value;
    }
    
    public static void writeStatus(final String s, final StringBuffer sb) {
        sb.append("STATUS|" + s + "\r\n");
    }
    
    public static void writeRecordCount(final int n, final StringBuffer sb) {
        sb.append("RECORDS|" + n + "\r\n");
    }
    
    public void writeItem(final StringBuffer sb) {
        sb.append("NFResourceItem");
        sb.append("|");
        final Vector vector = new Vector<String>();
        final Enumeration<String> keys = this.keys();
        while (keys.hasMoreElements()) {
            vector.addElement(keys.nextElement());
        }
        final Vector<String> vector2 = new Vector<String>();
        final Enumeration<String> elements = this.elements();
        while (elements.hasMoreElements()) {
            vector2.addElement(elements.nextElement());
        }
        for (int i = 0; i < vector.size(); ++i) {
            try {
                sb.append(NFBase64.encode(vector.elementAt(i)));
                sb.append(" ");
                sb.append(NFBase64.encode(vector2.elementAt(i)));
            }
            catch (IOException ex) {}
            if (i != vector.size() - 1) {
                sb.append("|");
            }
        }
        sb.append("\r\n");
    }
    
    public static void writeList(final Vector vector, final StringBuffer sb) {
        writeStatus("OK", sb);
        writeRecordCount(vector.size(), sb);
        for (int i = 0; i < vector.size(); ++i) {
            vector.elementAt(i).writeItem(sb);
        }
    }
    
    public static String readStatus(final InputStream inputStream) throws IOException {
        return a("STATUS", inputStream);
    }
    
    public static int readRecordCount(final InputStream inputStream) throws IOException {
        return Integer.parseInt(a("RECORDS", inputStream));
    }
    
    public static NFResourceItem readItem(final InputStream inputStream) throws IOException {
        final StringTokenizer stringTokenizer = new StringTokenizer(a("NFResourceItem", inputStream), "|");
        final NFResourceItem nfResourceItem = new NFResourceItem();
        while (stringTokenizer.hasMoreTokens()) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken().trim(), " ");
            if (stringTokenizer2.countTokens() != 2) {
                throw new IOException("Unexpected Data on Input Stream");
            }
            nfResourceItem.put(NFBase64.decode(stringTokenizer2.nextToken().trim()), NFBase64.decode(stringTokenizer2.nextToken().trim()));
        }
        return nfResourceItem;
    }
    
    public static Vector readList(final String s, final StringBuffer sb) throws IOException {
        return readList(new StringBufferInputStream(s), sb);
    }
    
    public static Vector readList(final InputStream inputStream, final StringBuffer sb) throws IOException {
        final String status = readStatus(inputStream);
        if (!status.equals("OK")) {
            sb.append(status);
            return null;
        }
        final int recordCount = readRecordCount(inputStream);
        final Vector<NFResourceItem> vector = new Vector<NFResourceItem>();
        for (int i = 0; i < recordCount; ++i) {
            vector.addElement(readItem(inputStream));
        }
        return vector;
    }
    
    private static String a(final String s, final InputStream inputStream) throws IOException {
        final String line = new DataInputStream(inputStream).readLine();
        if (line == null) {
            throw new IOException("EOF Reached on Input Stream");
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(line, "|");
        if (stringTokenizer.countTokens() < 2) {
            throw new IOException("Unexpected Data on Input Stream");
        }
        if (!stringTokenizer.nextToken().trim().equals(s)) {
            throw new IOException("Unexpected Data on Input Stream");
        }
        return line.substring(line.indexOf("|") + 1);
    }
}
