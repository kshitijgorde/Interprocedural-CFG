// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.net.URLEncoder;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.BitSet;

public class Codecs
{
    private static BitSet BoundChar;
    private static BitSet EBCDICUnsafeChar;
    private static byte[] Base64EncMap;
    private static byte[] Base64DecMap;
    private static char[] UUEncMap;
    private static byte[] UUDecMap;
    private static final String ContDisp = "\r\nContent-Disposition: form-data; name=\"";
    private static final String FileName = "\"; filename=\"";
    private static final String Boundary = "\r\n-----ieoau._._-2_8_GoodLuck8.3-dskdfJwSJKlrWLr0234324jfLdsjfdAuaoei-----";
    private static NVPair[] dummy;
    
    public static final String base64Encode(final String s) {
        if (s == null) {
            return null;
        }
        return new String(base64Encode(s.getBytes()));
    }
    
    public static final byte[] base64Encode(final byte[] array) {
        if (array == null) {
            return null;
        }
        final byte[] array2 = new byte[(array.length + 2) / 3 * 4];
        int i = 0;
        int j = 0;
        while (i < array.length - 2) {
            array2[j++] = Codecs.Base64EncMap[array[i] >>> 2 & 0x3F];
            array2[j++] = Codecs.Base64EncMap[(array[i + 1] >>> 4 & 0xF) | (array[i] << 4 & 0x3F)];
            array2[j++] = Codecs.Base64EncMap[(array[i + 2] >>> 6 & 0x3) | (array[i + 1] << 2 & 0x3F)];
            array2[j++] = Codecs.Base64EncMap[array[i + 2] & 0x3F];
            i += 3;
        }
        if (i < array.length) {
            array2[j++] = Codecs.Base64EncMap[array[i] >>> 2 & 0x3F];
            if (i < array.length - 1) {
                array2[j++] = Codecs.Base64EncMap[(array[i + 1] >>> 4 & 0xF) | (array[i] << 4 & 0x3F)];
                array2[j++] = Codecs.Base64EncMap[array[i + 1] << 2 & 0x3F];
            }
            else {
                array2[j++] = Codecs.Base64EncMap[array[i] << 4 & 0x3F];
            }
        }
        while (j < array2.length) {
            array2[j] = 61;
            ++j;
        }
        return array2;
    }
    
    public static final String base64Decode(final String s) {
        if (s == null) {
            return null;
        }
        return new String(base64Decode(s.getBytes()));
    }
    
    public static final byte[] base64Decode(final byte[] array) {
        if (array == null) {
            return null;
        }
        int length;
        for (length = array.length; array[length - 1] == 61; --length) {}
        final byte[] array2 = new byte[length - array.length / 4];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Codecs.Base64DecMap[array[i]];
        }
        int n = 0;
        int j;
        for (j = 0; j < array2.length - 2; j += 3) {
            array2[j] = (byte)((array[n] << 2 & 0xFF) | (array[n + 1] >>> 4 & 0x3));
            array2[j + 1] = (byte)((array[n + 1] << 4 & 0xFF) | (array[n + 2] >>> 2 & 0xF));
            array2[j + 2] = (byte)((array[n + 2] << 6 & 0xFF) | (array[n + 3] & 0x3F));
            n += 4;
        }
        if (j < array2.length) {
            array2[j] = (byte)((array[n] << 2 & 0xFF) | (array[n + 1] >>> 4 & 0x3));
        }
        if (++j < array2.length) {
            array2[j] = (byte)((array[n + 1] << 4 & 0xFF) | (array[n + 2] >>> 2 & 0xF));
        }
        return array2;
    }
    
    public static final char[] uuencode(final byte[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return new char[0];
        }
        final int n = 45;
        final char[] charArray = System.getProperty("line.separator", "\n").toCharArray();
        final char[] array2 = new char[(array.length + 2) / 3 * 4 + (array.length + n - 1) / n * (charArray.length + 1)];
        int i = 0;
        int n2 = 0;
        while (i + n < array.length) {
            array2[n2++] = Codecs.UUEncMap[n];
            while (i < i + n) {
                array2[n2++] = Codecs.UUEncMap[array[i] >>> 2 & 0x3F];
                array2[n2++] = Codecs.UUEncMap[(array[i + 1] >>> 4 & 0xF) | (array[i] << 4 & 0x3F)];
                array2[n2++] = Codecs.UUEncMap[(array[i + 2] >>> 6 & 0x3) | (array[i + 1] << 2 & 0x3F)];
                array2[n2++] = Codecs.UUEncMap[array[i + 2] & 0x3F];
                i += 3;
            }
            for (int j = 0; j < charArray.length; ++j) {
                array2[n2++] = charArray[j];
            }
        }
        array2[n2++] = Codecs.UUEncMap[array.length - i];
        while (i + 2 < array.length) {
            array2[n2++] = Codecs.UUEncMap[array[i] >>> 2 & 0x3F];
            array2[n2++] = Codecs.UUEncMap[(array[i + 1] >>> 4 & 0xF) | (array[i] << 4 & 0x3F)];
            array2[n2++] = Codecs.UUEncMap[(array[i + 2] >>> 6 & 0x3) | (array[i + 1] << 2 & 0x3F)];
            array2[n2++] = Codecs.UUEncMap[array[i + 2] & 0x3F];
            i += 3;
        }
        if (i < array.length - 1) {
            array2[n2++] = Codecs.UUEncMap[array[i] >>> 2 & 0x3F];
            array2[n2++] = Codecs.UUEncMap[(array[i + 1] >>> 4 & 0xF) | (array[i] << 4 & 0x3F)];
            array2[n2++] = Codecs.UUEncMap[array[i + 1] << 2 & 0x3F];
            array2[n2++] = Codecs.UUEncMap[0];
        }
        else if (i < array.length) {
            array2[n2++] = Codecs.UUEncMap[array[i] >>> 2 & 0x3F];
            array2[n2++] = Codecs.UUEncMap[array[i] << 4 & 0x3F];
            array2[n2++] = Codecs.UUEncMap[0];
            array2[n2++] = Codecs.UUEncMap[0];
        }
        for (int k = 0; k < charArray.length; ++k) {
            array2[n2++] = charArray[k];
        }
        if (n2 != array2.length) {
            throw new Error("Calculated " + array2.length + " chars but wrote " + n2 + " chars!");
        }
        return array2;
    }
    
    private static final byte[] uudecode(final BufferedReader bufferedReader) throws ParseException, IOException {
        String line;
        while ((line = bufferedReader.readLine()) != null && !line.startsWith("begin ")) {}
        if (line == null) {
            throw new ParseException("'begin' line not found");
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(line);
        stringTokenizer.nextToken();
        try {
            Integer.parseInt(stringTokenizer.nextToken(), 8);
        }
        catch (Exception ex) {
            throw new ParseException("Invalid mode on line: " + line);
        }
        try {
            stringTokenizer.nextToken();
        }
        catch (NoSuchElementException ex2) {
            throw new ParseException("No file name found on line: " + line);
        }
        byte[] resizeArray = new byte[1000];
        int n = 0;
        String line2;
        while ((line2 = bufferedReader.readLine()) != null && !line2.equals("end")) {
            final byte[] uudecode = uudecode(line2.toCharArray());
            if (n + uudecode.length > resizeArray.length) {
                resizeArray = Util.resizeArray(resizeArray, n + 1000);
            }
            System.arraycopy(uudecode, 0, resizeArray, n, uudecode.length);
            n += uudecode.length;
        }
        if (line2 == null) {
            throw new ParseException("'end' line not found");
        }
        return Util.resizeArray(resizeArray, n);
    }
    
    public static final byte[] uudecode(final char[] array) {
        if (array == null) {
            return null;
        }
        final byte[] array2 = new byte[array.length / 4 * 3];
        int i = 0;
        byte b = 0;
        while (i < array.length) {
            byte b2;
            byte b3;
            byte b4;
            byte b5;
            byte b6;
            for (b2 = (byte)(b + Codecs.UUDecMap[array[i++]]); b < b2 - 2; array2[b++] = (byte)((b3 << 2 & 0xFF) | (b4 >>> 4 & 0x3)), array2[b++] = (byte)((b4 << 4 & 0xFF) | (b5 >>> 2 & 0xF)), array2[b++] = (byte)((b5 << 6 & 0xFF) | (b6 & 0x3F)), i += 4) {
                b3 = Codecs.UUDecMap[array[i]];
                b4 = Codecs.UUDecMap[array[i + 1]];
                b5 = Codecs.UUDecMap[array[i + 2]];
                b6 = Codecs.UUDecMap[array[i + 3]];
            }
            if (b < b2) {
                array2[b++] = (byte)((Codecs.UUDecMap[array[i]] << 2 & 0xFF) | (Codecs.UUDecMap[array[i + 1]] >>> 4 & 0x3));
            }
            if (b < b2) {
                array2[b++] = (byte)((Codecs.UUDecMap[array[i + 1]] << 4 & 0xFF) | (Codecs.UUDecMap[array[i + 2]] >>> 2 & 0xF));
            }
            while (i < array.length && array[i] != '\n') {
                if (array[i] == '\r') {
                    break;
                }
                ++i;
            }
            while (i < array.length && (array[i] == '\n' || array[i] == '\r')) {
                ++i;
            }
        }
        return Util.resizeArray(array2, b);
    }
    
    public static final String quotedPrintableEncode(final String s) {
        if (s == null) {
            return null;
        }
        final char[] array = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        final char[] charArray = System.getProperty("line.separator", "\n").toCharArray();
        char[] resizeArray = new char[(int)(s.length() * 1.5)];
        final char[] charArray2 = s.toCharArray();
        int n = 0;
        int n2 = 1;
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char c = charArray2[i];
            if (c == charArray[0] && match(charArray2, i, charArray)) {
                if (resizeArray[n2 - 1] == ' ') {
                    resizeArray[n2 - 1] = '=';
                    resizeArray[n2++] = '2';
                    resizeArray[n2++] = '0';
                }
                else if (resizeArray[n2 - 1] == '\t') {
                    resizeArray[n2 - 1] = '=';
                    resizeArray[n2++] = '0';
                    resizeArray[n2++] = '9';
                }
                resizeArray[n2++] = '\r';
                resizeArray[n2++] = '\n';
                i += charArray.length - 1;
                n = n2;
            }
            else if (c > '~' || (c < ' ' && c != '\t') || c == '=' || Codecs.EBCDICUnsafeChar.get(c)) {
                resizeArray[n2++] = '=';
                resizeArray[n2++] = array[(c & '\u00f0') >>> 4];
                resizeArray[n2++] = array[c & '\u000f'];
            }
            else {
                resizeArray[n2++] = c;
            }
            if (n2 > n + 70) {
                resizeArray[n2++] = '=';
                resizeArray[n2++] = '\r';
                resizeArray[n2++] = '\n';
                n = n2;
            }
            if (n2 > resizeArray.length - 5) {
                resizeArray = Util.resizeArray(resizeArray, resizeArray.length + 500);
            }
        }
        return String.valueOf(resizeArray, 1, n2 - 1);
    }
    
    private static final boolean match(final char[] array, final int n, final char[] array2) {
        if (array.length < n + array2.length) {
            return false;
        }
        for (int i = 1; i < array2.length; ++i) {
            if (array[n + i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
    
    public static final String quotedPrintableDecode(final String s) throws ParseException {
        if (s == null) {
            return null;
        }
        char[] resizeArray = new char[(int)(s.length() * 1.1)];
        final char[] charArray = s.toCharArray();
        final char[] charArray2 = System.getProperty("line.separator", "\n").toCharArray();
        int n = 0;
        int n2 = 0;
        final int length = s.length();
        int i = 0;
        while (i < length) {
            final char c = charArray[i++];
            if (c == '=') {
                if (i >= length - 1) {
                    throw new ParseException("Premature end of input detected");
                }
                if (charArray[i] == '\n' || charArray[i] == '\r') {
                    ++i;
                    if (charArray[i - 1] == '\r' && charArray[i] == '\n') {
                        ++i;
                    }
                }
                else {
                    final int digit = Character.digit(charArray[i], 16);
                    final int digit2 = Character.digit(charArray[i + 1], 16);
                    if ((digit | digit2) < 0) {
                        throw new ParseException(new String(charArray, i - 1, 3) + " is an invalid code");
                    }
                    final char c2 = (char)(digit << 4 | digit2);
                    i += 2;
                    resizeArray[n2++] = c2;
                }
                n = n2;
            }
            else if (c == '\n' || c == '\r') {
                if (c == '\r' && i < length && charArray[i] == '\n') {
                    ++i;
                }
                for (int j = 0; j < charArray2.length; ++j) {
                    resizeArray[n++] = charArray2[j];
                }
                n2 = n;
            }
            else {
                resizeArray[n2++] = c;
                if (c != ' ' && c != '\t') {
                    n = n2;
                }
            }
            if (n2 > resizeArray.length - charArray2.length - 2) {
                resizeArray = Util.resizeArray(resizeArray, resizeArray.length + 500);
            }
        }
        return new String(resizeArray, 0, n2);
    }
    
    public static final String URLEncode(final String s) {
        if (s == null) {
            return null;
        }
        return URLEncoder.encode(s);
    }
    
    public static final String URLDecode(final String s) throws ParseException {
        if (s == null) {
            return null;
        }
        final char[] array = new char[s.length()];
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '+') {
                array[n++] = ' ';
            }
            else {
                if (char1 == '%') {
                    try {
                        array[n++] = (char)Integer.parseInt(s.substring(i + 1, i + 3), 16);
                        i += 2;
                        continue;
                    }
                    catch (NumberFormatException ex) {
                        throw new ParseException(s.substring(i, i + 3) + " is an invalid code");
                    }
                }
                array[n++] = char1;
            }
        }
        return String.valueOf(array, 0, n);
    }
    
    public static final NVPair[] mpFormDataDecode(final byte[] array, final String s, final String s2) throws IOException, ParseException {
        return mpFormDataDecode(array, s, s2, null);
    }
    
    public static final NVPair[] mpFormDataDecode(final byte[] array, final String s, final String s2, final FilenameMangler filenameMangler) throws IOException, ParseException {
        final String parameter = Util.getParameter("boundary", s);
        if (parameter == null) {
            throw new ParseException("'boundary' parameter not found in Content-type: " + s);
        }
        final byte[] bytes = ("--" + parameter + "\r\n").getBytes();
        final byte[] bytes2 = ("\r\n--" + parameter + "\r\n").getBytes();
        final byte[] bytes3 = ("\r\n--" + parameter + "--").getBytes();
        final int[] compile_search = Util.compile_search(bytes);
        final int[] compile_search2 = Util.compile_search(bytes2);
        final int[] compile_search3 = Util.compile_search(bytes3);
        final int str = Util.findStr(bytes, compile_search, array, 0, array.length);
        if (str == -1) {
            throw new ParseException("Starting boundary not found: " + new String(bytes));
        }
        int n = str + bytes.length;
        NVPair[] resizeArray = new NVPair[10];
        boolean b = false;
        int n2 = 0;
        while (!b) {
            int n3 = Util.findStr(bytes2, compile_search2, array, n, array.length);
            if (n3 == -1) {
                n3 = Util.findStr(bytes3, compile_search3, array, n, array.length);
                if (n3 == -1) {
                    throw new ParseException("Ending boundary not found: " + new String(bytes3));
                }
                b = true;
            }
            String value = null;
            String s3 = null;
            String s4 = null;
            while (true) {
                int n4 = findEOL(array, n) + 2;
                if (n4 - 2 <= n) {
                    n += 2;
                    if (n > n3) {
                        throw new ParseException("End of header not found at offset " + n3);
                    }
                    if (s4 == null) {
                        throw new ParseException("Missing 'Content-Disposition' header at offset " + n);
                    }
                    String s5;
                    if (s3 != null) {
                        if (filenameMangler != null) {
                            s3 = filenameMangler.mangleFilename(s3, value);
                        }
                        if (s3 != null) {
                            final FileOutputStream fileOutputStream = new FileOutputStream(new File(s2, s3));
                            fileOutputStream.write(array, n, n3 - n);
                            fileOutputStream.close();
                        }
                        s5 = s3;
                    }
                    else {
                        s5 = new String(array, n, n3 - n);
                    }
                    if (n2 >= resizeArray.length) {
                        resizeArray = Util.resizeArray(resizeArray, n2 + 10);
                    }
                    resizeArray[n2] = new NVPair(value, s5);
                    n = n3 + bytes2.length;
                    ++n2;
                    break;
                }
                else {
                    String string;
                    byte b2;
                    for (string = new String(array, n, n4 - 2 - n), n = n4; n4 < array.length - 1 && ((b2 = array[n4]) == 32 || b2 == 9); n4 = findEOL(array, n) + 2, string += new String(array, n, n4 - 2 - n), n = n4) {}
                    if (!string.regionMatches(true, 0, "Content-Disposition", 0, 19)) {
                        continue;
                    }
                    final HttpHeaderElement element = Util.getElement(Util.parseHeader(string.substring(string.indexOf(58) + 1)), "form-data");
                    if (element == null) {
                        throw new ParseException("Expected 'Content-Disposition: form-data' in line: " + string);
                    }
                    final NVPair[] params = element.getParams();
                    s3 = (value = null);
                    for (int i = 0; i < params.length; ++i) {
                        if (params[i].getName().equalsIgnoreCase("name")) {
                            value = params[i].getValue();
                        }
                        if (params[i].getName().equalsIgnoreCase("filename")) {
                            s3 = params[i].getValue();
                        }
                    }
                    if (value == null) {
                        throw new ParseException("'name' parameter not found in header: " + string);
                    }
                    s4 = string;
                }
            }
        }
        return Util.resizeArray(resizeArray, n2);
    }
    
    private static final int findEOL(final byte[] array, int n) {
        while (n < array.length - 1 && (array[n++] != 13 || array[n] != 10)) {}
        return n - 1;
    }
    
    public static final byte[] mpFormDataEncode(final NVPair[] array, final NVPair[] array2, final NVPair[] array3) throws IOException {
        return mpFormDataEncode(array, array2, array3, null);
    }
    
    public static final byte[] mpFormDataEncode(NVPair[] dummy, NVPair[] dummy2, final NVPair[] array, final FilenameMangler filenameMangler) throws IOException {
        int n = 0;
        final int n2 = 119;
        final byte[] array2 = new byte[74];
        final byte[] array3 = new byte[40];
        final byte[] array4 = new byte[13];
        Util.getBytes("\r\nContent-Disposition: form-data; name=\"", array3, 0);
        Util.getBytes("\"; filename=\"", array4, 0);
        Util.getBytes("\r\n-----ieoau._._-2_8_GoodLuck8.3-dskdfJwSJKlrWLr0234324jfLdsjfdAuaoei-----", array2, 0);
        if (dummy == null) {
            dummy = Codecs.dummy;
        }
        if (dummy2 == null) {
            dummy2 = Codecs.dummy;
        }
        for (int i = 0; i < dummy.length; ++i) {
            n += n2 + dummy[i].getName().length() + dummy[i].getValue().length();
        }
        for (int j = 0; j < dummy2.length; ++j) {
            final File file = new File(dummy2[j].getValue());
            String s = file.getName();
            if (filenameMangler != null) {
                s = filenameMangler.mangleFilename(s, dummy2[j].getName());
            }
            if (s != null) {
                n = (int)(n + (n2 + dummy2[j].getName().length() + 13) + (s.length() + file.length()));
            }
        }
        n -= 2;
        n += 78;
        final byte[] array5 = new byte[n];
        int n3 = 0;
        int k = 808464432;
    Label_1053_Outer:
        while (k != 2054847098) {
            n3 = 0;
            while (!Codecs.BoundChar.get(k & 0xFF)) {
                ++k;
            }
            while (!Codecs.BoundChar.get(k >> 8 & 0xFF)) {
                k += 256;
            }
            while (!Codecs.BoundChar.get(k >> 16 & 0xFF)) {
                k += 65536;
            }
            while (!Codecs.BoundChar.get(k >> 24 & 0xFF)) {
                k += 16777216;
            }
            array2[40] = (byte)(k & 0xFF);
            array2[42] = (byte)(k >> 8 & 0xFF);
            array2[44] = (byte)(k >> 16 & 0xFF);
            array2[46] = (byte)(k >> 24 & 0xFF);
            int n4 = 2;
            final int[] compile_search = Util.compile_search(array2);
            int l = 0;
        Label_1053:
            while (true) {
                while (l < dummy.length) {
                    System.arraycopy(array2, n4, array5, n3, array2.length - n4);
                    final int n5 = n3 + (array2.length - n4);
                    n4 = 0;
                    System.arraycopy(array3, 0, array5, n5, array3.length);
                    n3 = n5 + array3.length;
                    final int length = dummy[l].getName().length();
                    Util.getBytes(dummy[l].getName(), length, array5, n3);
                    if (length < array2.length || Util.findStr(array2, compile_search, array5, n3, n3 + length) == -1) {
                        n3 += length;
                        array5[n3++] = 34;
                        array5[n3++] = 13;
                        array5[n3++] = 10;
                        array5[n3++] = 13;
                        array5[n3++] = 10;
                        final int length2 = dummy[l].getValue().length();
                        Util.getBytes(dummy[l].getValue(), length2, array5, n3);
                        if (length2 < array2.length || Util.findStr(array2, compile_search, array5, n3, n3 + length2) == -1) {
                            n3 += length2;
                            ++l;
                            continue Label_1053_Outer;
                        }
                    }
                    ++k;
                    continue Label_1053_Outer;
                }
                for (int n6 = 0; n6 < dummy2.length; ++n6) {
                    final File file2 = new File(dummy2[n6].getValue());
                    String s2 = file2.getName();
                    if (filenameMangler != null) {
                        s2 = filenameMangler.mangleFilename(s2, dummy2[n6].getName());
                    }
                    if (s2 != null) {
                        System.arraycopy(array2, n4, array5, n3, array2.length - n4);
                        final int n7 = n3 + (array2.length - n4);
                        n4 = 0;
                        System.arraycopy(array3, 0, array5, n7, array3.length);
                        n3 = n7 + array3.length;
                        final int length3 = dummy2[n6].getName().length();
                        Util.getBytes(dummy2[n6].getName(), array5, n3);
                        if (length3 >= array2.length && Util.findStr(array2, compile_search, array5, n3, n3 + length3) != -1) {
                            continue Label_1053;
                        }
                        final int n8 = n3 + length3;
                        System.arraycopy(array4, 0, array5, n8, array4.length);
                        n3 = n8 + array4.length;
                        final int length4 = s2.length();
                        Util.getBytes(s2, array5, n3);
                        if (length4 >= array2.length && Util.findStr(array2, compile_search, array5, n3, n3 + length4) != -1) {
                            continue Label_1053;
                        }
                        n3 += length4;
                        array5[n3++] = 34;
                        array5[n3++] = 13;
                        array5[n3++] = 10;
                        array5[n3++] = 13;
                        array5[n3++] = 10;
                        int n9 = (int)file2.length();
                        final int n10 = n3;
                        final FileInputStream fileInputStream = new FileInputStream(file2);
                        while (n9 > 0) {
                            final int read = fileInputStream.read(array5, n3, n9);
                            n9 -= read;
                            n3 += read;
                        }
                        if (Util.findStr(array2, compile_search, array5, n10, n3) != -1) {
                            continue Label_1053;
                        }
                    }
                }
                break;
            }
            break;
        }
        System.arraycopy(array2, 0, array5, n3, array2.length);
        int n11 = n3 + array2.length;
        array5[n11++] = 45;
        array5[n11++] = 45;
        array5[n11++] = 13;
        array5[n11++] = 10;
        if (n11 != n) {
            throw new Error("Calculated " + n + " bytes but wrote " + n11 + " bytes!");
        }
        array[0] = new NVPair("Content-Type", "multipart/form-data; boundary=" + new String(array2, 4, 70));
        return array5;
    }
    
    public static final String nv2query(final NVPair[] array) {
        if (array == null) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        int i;
        for (i = 0; i < array.length; ++i) {
            sb.append(URLEncode(array[i].getName()) + "=" + URLEncode(array[i].getValue()) + "&");
        }
        if (i > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
    
    public static final NVPair[] query2nv(final String s) throws ParseException {
        if (s == null) {
            return null;
        }
        int index = -1;
        int n = 1;
        while ((index = s.indexOf(38, index + 1)) != -1) {
            ++n;
        }
        final NVPair[] array = new NVPair[n];
        int n2 = 0;
        for (int i = 0; i < array.length; ++i) {
            final int index2 = s.indexOf(61, n2);
            int n3 = s.indexOf(38, n2);
            if (n3 == -1) {
                n3 = s.length();
            }
            if (index2 == -1 || index2 >= n3) {
                throw new ParseException("'=' missing in " + s.substring(n2, n3));
            }
            array[i] = new NVPair(URLDecode(s.substring(n2, index2)), URLDecode(s.substring(index2 + 1, n3)));
            n2 = n3 + 1;
        }
        return array;
    }
    
    public static final byte[] chunkedEncode(final byte[] array, final NVPair[] array2, final boolean b) {
        return chunkedEncode(array, 0, (array == null) ? 0 : array.length, array2, b);
    }
    
    public static final byte[] chunkedEncode(byte[] array, final int n, int n2, NVPair[] array2, final boolean b) {
        if (array == null) {
            array = new byte[0];
            n2 = 0;
        }
        if (b && array2 == null) {
            array2 = new NVPair[0];
        }
        final String string = Integer.toString(n2, 16);
        int n3 = 0;
        if (n2 > 0) {
            n3 += string.length() + 2 + n2 + 2;
        }
        if (b) {
            n3 += 3;
            for (int i = 0; i < array2.length; ++i) {
                n3 += array2[i].getName().length() + 2 + array2[i].getValue().length() + 2;
            }
            n3 += 2;
        }
        final byte[] array3 = new byte[n3];
        int n4 = 0;
        if (n2 > 0) {
            Util.getBytes(string, array3, n4);
            int n5 = n4 + string.length();
            array3[n5++] = 13;
            array3[n5++] = 10;
            System.arraycopy(array, n, array3, n5, n2);
            n4 = n5 + n2;
            array3[n4++] = 13;
            array3[n4++] = 10;
        }
        if (b) {
            array3[n4++] = 48;
            array3[n4++] = 13;
            array3[n4++] = 10;
            for (int j = 0; j < array2.length; ++j) {
                Util.getBytes(array2[j].getName(), array3, n4);
                int n6 = n4 + array2[j].getName().length();
                array3[n6++] = 58;
                array3[n6++] = 32;
                Util.getBytes(array2[j].getValue(), array3, n6);
                n4 = n6 + array2[j].getValue().length();
                array3[n4++] = 13;
                array3[n4++] = 10;
            }
            array3[n4++] = 13;
            array3[n4++] = 10;
        }
        if (n4 != array3.length) {
            throw new Error("Calculated " + array3.length + " bytes but wrote " + n4 + " bytes!");
        }
        return array3;
    }
    
    public static final Object chunkedDecode(final InputStream inputStream) throws ParseException, IOException {
        int n = getChunkLength(inputStream);
        if (n <= 0) {
            NVPair[] resizeArray = new NVPair[0];
            String line;
            while ((line = new DataInputStream(inputStream).readLine()) != null && line.length() > 0) {
                final int index = line.indexOf(58);
                if (index == -1) {
                    throw new ParseException("Error in Footer format: no ':' found in '" + line + "'");
                }
                resizeArray = Util.resizeArray(resizeArray, resizeArray.length + 1);
                resizeArray[resizeArray.length - 1] = new NVPair(line.substring(0, index).trim(), line.substring(index + 1).trim());
            }
            return resizeArray;
        }
        byte[] array;
        int n2;
        for (array = new byte[n], n2 = 0; n != -1 && n2 < array.length; n = inputStream.read(array, n2, array.length - n2), n2 += n) {}
        if (n == -1) {
            throw new ParseException("Premature EOF while reading chunk;Expected: " + array.length + " Bytes, " + "Received: " + (n2 + 1) + " Bytes");
        }
        inputStream.read();
        inputStream.read();
        return array;
    }
    
    static final int getChunkLength(final InputStream inputStream) throws ParseException, IOException {
        byte[] array;
        int n;
        int n2;
        for (array = new byte[8], n = 0; (n2 = inputStream.read()) != 13 && n2 != 10 && n2 != 59 && n < array.length; array[n++] = (byte)n2) {}
        if (n2 == 59) {
            while ((n2 = inputStream.read()) != 13 && n2 != 10) {}
        }
        if (n2 != 10 && (n2 != 13 || inputStream.read() != 10)) {
            throw new ParseException("Didn't find valid chunk length: " + new String(array, 0, n));
        }
        int int1;
        try {
            int1 = Integer.parseInt(new String(array, 0, n).trim(), 16);
        }
        catch (NumberFormatException ex) {
            throw new ParseException("Didn't find valid chunk length: " + new String(array, 0, n));
        }
        return int1;
    }
    
    static {
        Codecs.BoundChar = new BitSet(256);
        for (int i = 48; i <= 57; ++i) {
            Codecs.BoundChar.set(i);
        }
        for (int j = 65; j <= 90; ++j) {
            Codecs.BoundChar.set(j);
        }
        for (int k = 97; k <= 122; ++k) {
            Codecs.BoundChar.set(k);
        }
        Codecs.BoundChar.set(39);
        Codecs.BoundChar.set(43);
        Codecs.BoundChar.set(95);
        Codecs.BoundChar.set(45);
        Codecs.BoundChar.set(46);
        (Codecs.EBCDICUnsafeChar = new BitSet(256)).set(33);
        Codecs.EBCDICUnsafeChar.set(34);
        Codecs.EBCDICUnsafeChar.set(35);
        Codecs.EBCDICUnsafeChar.set(36);
        Codecs.EBCDICUnsafeChar.set(64);
        Codecs.EBCDICUnsafeChar.set(91);
        Codecs.EBCDICUnsafeChar.set(92);
        Codecs.EBCDICUnsafeChar.set(93);
        Codecs.EBCDICUnsafeChar.set(94);
        Codecs.EBCDICUnsafeChar.set(96);
        Codecs.EBCDICUnsafeChar.set(123);
        Codecs.EBCDICUnsafeChar.set(124);
        Codecs.EBCDICUnsafeChar.set(125);
        Codecs.EBCDICUnsafeChar.set(126);
        Codecs.Base64EncMap = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
        Codecs.Base64DecMap = new byte[128];
        for (int l = 0; l < Codecs.Base64EncMap.length; ++l) {
            Codecs.Base64DecMap[Codecs.Base64EncMap[l]] = (byte)l;
        }
        Codecs.UUEncMap = new char[64];
        for (int n = 0; n < Codecs.UUEncMap.length; ++n) {
            Codecs.UUEncMap[n] = (char)(n + 32);
        }
        Codecs.UUDecMap = new byte[128];
        for (int n2 = 0; n2 < Codecs.UUEncMap.length; ++n2) {
            Codecs.UUDecMap[Codecs.UUEncMap[n2]] = (byte)n2;
        }
        Codecs.dummy = new NVPair[0];
    }
}
