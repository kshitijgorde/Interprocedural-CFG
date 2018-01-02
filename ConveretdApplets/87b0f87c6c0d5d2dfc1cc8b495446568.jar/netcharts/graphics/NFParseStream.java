// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import netcharts.util.NFBuffer;
import netcharts.util.NFMd5;
import java.util.Vector;
import java.io.InputStream;
import netcharts.util.NFToken;
import netcharts.util.NFDebug;

public class NFParseStream
{
    private static void a(final String s) {
        NFDebug.print(1L, "NetCharts: " + s);
    }
    
    private static String b(final String s) {
        final int index = s.indexOf(98);
        if (index < 0) {
            return s;
        }
        return s.substring(0, index);
    }
    
    public static NFLicenseEntry parseStream(final StringBuffer input, final String s, final String s2, final String s3) throws Exception {
        final NFToken nfToken = new NFToken();
        nfToken.setInput(input);
        return parseStream(nfToken, s, s2, s3);
    }
    
    public static NFLicenseEntry parseStream(final InputStream input, final String s, final String s2, final String s3) throws Exception {
        final NFToken nfToken = new NFToken();
        nfToken.setInput(input);
        return parseStream(nfToken, s, s2, s3);
    }
    
    public static NFLicenseEntry parseStream(final NFToken nfToken, String b, final String s, final String s2) throws Exception {
        final StringBuffer sb = new StringBuffer();
        b = b(b);
        a("Target = " + b);
        nfToken.setCharType(2, 46, 0);
        nfToken.setCharType(2, 45, 0);
        nfToken.setCharType(3, 10, 0);
        nfToken.setCharRange(2, 48, 57);
        final NFLicenseEntry nfLicenseEntry = new NFLicenseEntry(b);
        while (true) {
            final StringBuffer nextToken = nfToken.nextToken();
            if (nextToken == null) {
                throw new Exception("No valid entry found");
            }
            if (nextToken.length() == 0) {
                a("No entry found");
            }
            else {
                final String string = nextToken.toString();
                if (string.charAt(0) == '<') {
                    throw new Exception("No valid entry found");
                }
                if (string.equals("\n")) {
                    continue;
                }
                a("Checking Entry: " + string);
                if (!b.equals(b(string))) {
                    sb.setLength(0);
                    nfToken.getAllUntil('\n', sb);
                }
                else {
                    final Vector vector = new Vector<String>();
                    StringBuffer nextToken2;
                    while ((nextToken2 = nfToken.nextToken()) != null && nextToken2.length() != 0) {
                        if (nextToken2.toString().equals("\n")) {
                            break;
                        }
                        final String stripQuotes = NFToken.stripQuotes(nextToken2.toString());
                        final StringBuffer nextToken3 = nfToken.nextToken();
                        if (nextToken3 == null) {
                            break;
                        }
                        if (nextToken3.toString().equals("\n")) {
                            break;
                        }
                        if (!nextToken3.toString().equals("=")) {
                            sb.setLength(0);
                            nfToken.getAllUntil('\n', sb);
                            break;
                        }
                        final StringBuffer nextToken4 = nfToken.nextToken();
                        if (nextToken4 == null || nextToken4.toString().equals("\n")) {
                            a("Malformed name/value pair for name: " + stripQuotes);
                            break;
                        }
                        String s3 = NFToken.stripQuotes(nextToken4.toString());
                        a("name = " + stripQuotes + ", value = " + s3);
                        if (!stripQuotes.equals("KEY")) {
                            vector.addElement(stripQuotes);
                            vector.addElement(s3);
                        }
                        else if (s3.indexOf(" ") != -1) {
                            final StringBuffer sb2 = new StringBuffer();
                            for (int i = 0; i < s3.length(); ++i) {
                                final char char1 = s3.charAt(i);
                                if (char1 != ' ') {
                                    sb2.append(char1);
                                }
                            }
                            s3 = sb2.toString();
                        }
                        nfLicenseEntry.put(stripQuotes, s3);
                    }
                    if (nfLicenseEntry.get("KEY") == null) {
                        a("Entry does not contain a KEY field: " + nfLicenseEntry.toString());
                    }
                    else {
                        vector.addElement(b);
                        final int size = vector.size();
                        final String[] array = new String[size];
                        for (int j = 0; j < size; ++j) {
                            array[j] = vector.elementAt(j);
                        }
                        if (!genKeyString(array).equals(nfLicenseEntry.get("KEY"))) {
                            continue;
                        }
                        if (nfLicenseEntry.get("HOST") == null) {
                            break;
                        }
                        final String field = nfLicenseEntry.getField("HOST");
                        a("node locked to: " + field);
                        a("this host is: " + s);
                        if (field.endsWith(".0.0")) {
                            if (!field.substring(0, field.lastIndexOf(".0.0")).equals(s.substring(0, s.indexOf(".", s.indexOf(".") + 1)))) {
                                continue;
                            }
                            break;
                        }
                        else if (field.endsWith(".0")) {
                            if (!field.substring(0, field.lastIndexOf(".0")).equals(s.substring(0, s.lastIndexOf(".")))) {
                                continue;
                            }
                            break;
                        }
                        else {
                            if (!s.equals(nfLicenseEntry.getField("HOST"))) {
                                continue;
                            }
                            break;
                        }
                    }
                }
            }
        }
        return nfLicenseEntry;
    }
    
    public static NFLicenseEntry parseStream(final InputStream input, final String s) throws Exception {
        final StringBuffer sb = new StringBuffer();
        final NFToken nfToken = new NFToken();
        nfToken.setInput(input);
        return parseStream(nfToken, s, null, null);
    }
    
    public static String genKeyString(final String s, final String s2) {
        final byte[] array = new byte[1024];
        final NFMd5 nfMd5 = new NFMd5();
        final StringBuffer sb = new StringBuffer();
        final int length = s2.length();
        s2.getBytes(0, length, array, 0);
        nfMd5.Update(new NFBuffer(array, 0, length));
        array[0] = 12;
        array[1] = 34;
        array[2] = -55;
        array[3] = 67;
        array[4] = 78;
        array[5] = -127;
        array[6] = 54;
        array[7] = 34;
        array[8] = 98;
        array[9] = -94;
        array[10] = 59;
        array[11] = 63;
        array[12] = 87;
        array[13] = 82;
        array[14] = 90;
        array[15] = -3;
        nfMd5.Update(new NFBuffer(array, 0, 16));
        final int length2 = s.length();
        s.getBytes(0, length2, array, 0);
        nfMd5.Update(new NFBuffer(array, 0, length2));
        nfMd5.Finalize(new NFBuffer(array, 0, 16));
        for (int i = 0; i < 16; ++i) {
            sb.append("ABCDEFGHXJKYMNZP".charAt(array[i] >> 4 & 0xF));
            sb.append("ABCDEFGHXJKYMNZP".charAt(array[i] & 0xF));
        }
        return sb.toString();
    }
    
    public static String genKeyString(final String[] array) {
        final byte[] array2 = new byte[1024];
        final NFMd5 nfMd5 = new NFMd5();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            final String s = array[i];
            final int length = s.length();
            s.getBytes(0, length, array2, 0);
            nfMd5.Update(new NFBuffer(array2, 0, length));
        }
        array2[0] = 12;
        array2[1] = 34;
        array2[2] = -55;
        array2[3] = 67;
        array2[4] = 78;
        array2[5] = -127;
        array2[6] = 54;
        array2[7] = 34;
        array2[8] = 98;
        array2[9] = -94;
        array2[10] = 59;
        array2[11] = 63;
        array2[12] = 87;
        array2[13] = 82;
        array2[14] = 90;
        array2[15] = -3;
        nfMd5.Finalize(new NFBuffer(array2, 0, 16));
        for (int j = 0; j < 16; ++j) {
            sb.append("ABCDEFGHXJKYMNZP".charAt(array2[j] >> 4 & 0xF));
            sb.append("ABCDEFGHXJKYMNZP".charAt(array2[j] & 0xF));
        }
        return sb.toString();
    }
    
    public static void main(final String[] array) throws Exception {
        NFDebug.set(1L);
        if (array.length < 2) {
            System.out.println("usage: java netcharts.graphics.NFParseStream <product>, <NFLicense.dat file>");
        }
        try {
            parseStream(new FileInputStream(array[1]), array[0]);
        }
        catch (FileNotFoundException ex) {
            System.out.println("License file <" + array[1] + "> not found: " + ex);
        }
        catch (Exception ex2) {
            System.out.println("Invalid License file: " + ex2);
        }
        throw new Exception();
    }
}
