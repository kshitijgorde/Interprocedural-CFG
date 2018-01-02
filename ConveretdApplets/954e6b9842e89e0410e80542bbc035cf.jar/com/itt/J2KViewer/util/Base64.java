// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

import java.io.FilterOutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import java.util.zip.DeflaterOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class Base64
{
    public static final int NO_OPTIONS = 0;
    public static final int ENCODE = 1;
    public static final int DECODE = 0;
    public static final int GZIP = 2;
    public static final int DONT_BREAK_LINES = 8;
    public static final int URL_SAFE = 16;
    public static final int ORDERED = 32;
    private static final int MAX_LINE_LENGTH = 76;
    private static final byte EQUALS_SIGN = 61;
    private static final byte NEW_LINE = 10;
    private static final String PREFERRED_ENCODING = "UTF-8";
    private static final byte WHITE_SPACE_ENC = -5;
    private static final byte EQUALS_SIGN_ENC = -1;
    private static final byte[] _STANDARD_ALPHABET;
    private static final byte[] _STANDARD_DECODABET;
    private static final byte[] _URL_SAFE_ALPHABET;
    private static final byte[] _URL_SAFE_DECODABET;
    private static final byte[] _ORDERED_ALPHABET;
    private static final byte[] _ORDERED_DECODABET;
    
    private static final byte[] getAlphabet(final int n) {
        if ((n & 0x10) == 0x10) {
            return Base64._URL_SAFE_ALPHABET;
        }
        if ((n & 0x20) == 0x20) {
            return Base64._ORDERED_ALPHABET;
        }
        return Base64._STANDARD_ALPHABET;
    }
    
    private static final byte[] getDecodabet(final int n) {
        if ((n & 0x10) == 0x10) {
            return Base64._URL_SAFE_DECODABET;
        }
        if ((n & 0x20) == 0x20) {
            return Base64._ORDERED_DECODABET;
        }
        return Base64._STANDARD_DECODABET;
    }
    
    public static final void main(final String[] array) {
        if (array.length < 3) {
            usage("Not enough arguments.");
        }
        else {
            final String s = array[0];
            final String s2 = array[1];
            final String s3 = array[2];
            if (s.equals("-e")) {
                encodeFileToFile(s2, s3);
            }
            else if (s.equals("-d")) {
                decodeFileToFile(s2, s3);
            }
            else {
                usage("Unknown flag: " + s);
            }
        }
    }
    
    private static final void usage(final String s) {
        System.err.println(s);
        System.err.println("Usage: java Base64 -e|-d inputfile outputfile");
    }
    
    private static byte[] encode3to4(final byte[] array, final byte[] array2, final int n, final int n2) {
        encode3to4(array2, 0, n, array, 0, n2);
        return array;
    }
    
    private static byte[] encode3to4(final byte[] array, final int n, final int n2, final byte[] array2, final int n3, final int n4) {
        final byte[] alphabet = getAlphabet(n4);
        final int n5 = ((n2 > 0) ? (array[n] << 24 >>> 8) : 0) | ((n2 > 1) ? (array[n + 1] << 24 >>> 16) : 0) | ((n2 > 2) ? (array[n + 2] << 24 >>> 24) : 0);
        switch (n2) {
            case 3: {
                array2[n3] = alphabet[n5 >>> 18];
                array2[n3 + 1] = alphabet[n5 >>> 12 & 0x3F];
                array2[n3 + 2] = alphabet[n5 >>> 6 & 0x3F];
                array2[n3 + 3] = alphabet[n5 & 0x3F];
                return array2;
            }
            case 2: {
                array2[n3] = alphabet[n5 >>> 18];
                array2[n3 + 1] = alphabet[n5 >>> 12 & 0x3F];
                array2[n3 + 2] = alphabet[n5 >>> 6 & 0x3F];
                array2[n3 + 3] = 61;
                return array2;
            }
            case 1: {
                array2[n3] = alphabet[n5 >>> 18];
                array2[n3 + 1] = alphabet[n5 >>> 12 & 0x3F];
                array2[n3 + 3] = (array2[n3 + 2] = 61);
                return array2;
            }
            default: {
                return array2;
            }
        }
    }
    
    public static String encodeObject(final Serializable s) {
        return encodeObject(s, 0);
    }
    
    public static String encodeObject(final Serializable s, final int n) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        java.io.OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        DeflaterOutputStream deflaterOutputStream = null;
        final int n2 = n & 0x2;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            outputStream = new OutputStream(byteArrayOutputStream, 0x1 | n);
            if (n2 == 2) {
                deflaterOutputStream = new GZIPOutputStream(outputStream);
                objectOutputStream = new ObjectOutputStream(deflaterOutputStream);
            }
            else {
                objectOutputStream = new ObjectOutputStream(outputStream);
            }
            objectOutputStream.writeObject(s);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        finally {
            try {
                objectOutputStream.close();
            }
            catch (Exception ex2) {}
            try {
                deflaterOutputStream.close();
            }
            catch (Exception ex3) {}
            try {
                outputStream.close();
            }
            catch (Exception ex4) {}
            try {
                byteArrayOutputStream.close();
            }
            catch (Exception ex5) {}
        }
        try {
            return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
        }
        catch (UnsupportedEncodingException ex6) {
            return new String(byteArrayOutputStream.toByteArray());
        }
    }
    
    public static String encodeBytes(final byte[] array) {
        return encodeBytes(array, 0, array.length, 0);
    }
    
    public static String encodeBytes(final byte[] array, final int n) {
        return encodeBytes(array, 0, array.length, n);
    }
    
    public static String encodeBytes(final byte[] array, final int n, final int n2) {
        return encodeBytes(array, n, n2, 0);
    }
    
    public static String encodeBytes(final byte[] array, final int n, final int n2, final int n3) {
        final int n4 = n3 & 0x8;
        if ((n3 & 0x2) == 0x2) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            GZIPOutputStream gzipOutputStream = null;
            OutputStream outputStream = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                outputStream = new OutputStream(byteArrayOutputStream, 0x1 | n3);
                gzipOutputStream = new GZIPOutputStream(outputStream);
                gzipOutputStream.write(array, n, n2);
                gzipOutputStream.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
            finally {
                try {
                    gzipOutputStream.close();
                }
                catch (Exception ex2) {}
                try {
                    outputStream.close();
                }
                catch (Exception ex3) {}
                try {
                    byteArrayOutputStream.close();
                }
                catch (Exception ex4) {}
            }
            try {
                return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
            }
            catch (UnsupportedEncodingException ex5) {
                return new String(byteArrayOutputStream.toByteArray());
            }
        }
        final boolean b = n4 == 0;
        final int n5 = n2 * 4 / 3;
        final byte[] array2 = new byte[n5 + ((n2 % 3 > 0) ? 4 : 0) + (b ? (n5 / 76) : 0)];
        int i = 0;
        int n6 = 0;
        final int n7 = n2 - 2;
        int n8 = 0;
        while (i < n7) {
            encode3to4(array, i + n, 3, array2, n6, n3);
            n8 += 4;
            if (b && n8 == 76) {
                array2[n6 + 4] = 10;
                ++n6;
                n8 = 0;
            }
            i += 3;
            n6 += 4;
        }
        if (i < n2) {
            encode3to4(array, i + n, n2 - i, array2, n6, n3);
            n6 += 4;
        }
        try {
            return new String(array2, 0, n6, "UTF-8");
        }
        catch (UnsupportedEncodingException ex6) {
            return new String(array2, 0, n6);
        }
    }
    
    private static int decode4to3(final byte[] array, final int n, final byte[] array2, final int n2, final int n3) {
        final byte[] decodabet = getDecodabet(n3);
        if (array[n + 2] == 61) {
            array2[n2] = (byte)(((decodabet[array[n]] & 0xFF) << 18 | (decodabet[array[n + 1]] & 0xFF) << 12) >>> 16);
            return 1;
        }
        if (array[n + 3] == 61) {
            final int n4 = (decodabet[array[n]] & 0xFF) << 18 | (decodabet[array[n + 1]] & 0xFF) << 12 | (decodabet[array[n + 2]] & 0xFF) << 6;
            array2[n2] = (byte)(n4 >>> 16);
            array2[n2 + 1] = (byte)(n4 >>> 8);
            return 2;
        }
        try {
            final int n5 = (decodabet[array[n]] & 0xFF) << 18 | (decodabet[array[n + 1]] & 0xFF) << 12 | (decodabet[array[n + 2]] & 0xFF) << 6 | (decodabet[array[n + 3]] & 0xFF);
            array2[n2] = (byte)(n5 >> 16);
            array2[n2 + 1] = (byte)(n5 >> 8);
            array2[n2 + 2] = (byte)n5;
            return 3;
        }
        catch (Exception ex) {
            System.out.println("" + array[n] + ": " + decodabet[array[n]]);
            System.out.println("" + array[n + 1] + ": " + decodabet[array[n + 1]]);
            System.out.println("" + array[n + 2] + ": " + decodabet[array[n + 2]]);
            System.out.println("" + array[n + 3] + ": " + decodabet[array[n + 3]]);
            return -1;
        }
    }
    
    public static byte[] decode(final byte[] array, final int n, final int n2, final int n3) {
        final byte[] decodabet = getDecodabet(n3);
        final byte[] array2 = new byte[n2 * 3 / 4];
        int n4 = 0;
        final byte[] array3 = new byte[4];
        int n5 = 0;
        for (int i = n; i < n + n2; ++i) {
            final byte b = (byte)(array[i] & 0x7F);
            final byte b2 = decodabet[b];
            if (b2 < -5) {
                System.err.println("Bad Base64 input character at " + i + ": " + array[i] + "(decimal)");
                return null;
            }
            if (b2 >= -1) {
                array3[n5++] = b;
                if (n5 > 3) {
                    n4 += decode4to3(array3, 0, array2, n4, n3);
                    n5 = 0;
                    if (b == 61) {
                        break;
                    }
                }
            }
        }
        final byte[] array4 = new byte[n4];
        System.arraycopy(array2, 0, array4, 0, n4);
        return array4;
    }
    
    public static byte[] decode(final String s) {
        return decode(s, 0);
    }
    
    public static byte[] decode(final String s, final int n) {
        byte[] array;
        try {
            array = s.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            array = s.getBytes();
        }
        byte[] array2 = decode(array, 0, array.length, n);
        if (array2 != null && array2.length >= 4 && 0x8B1F == ((array2[0] & 0xFF) | (array2[1] << 8 & 0xFF00))) {
            java.io.InputStream inputStream = null;
            FilterInputStream filterInputStream = null;
            ByteArrayOutputStream byteArrayOutputStream = null;
            final byte[] array3 = new byte[2048];
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                inputStream = new ByteArrayInputStream(array2);
                filterInputStream = new GZIPInputStream(inputStream);
                int read;
                while ((read = filterInputStream.read(array3)) >= 0) {
                    byteArrayOutputStream.write(array3, 0, read);
                }
                array2 = byteArrayOutputStream.toByteArray();
            }
            catch (IOException ex2) {}
            finally {
                try {
                    byteArrayOutputStream.close();
                }
                catch (Exception ex3) {}
                try {
                    ((GZIPInputStream)filterInputStream).close();
                }
                catch (Exception ex4) {}
                try {
                    ((ByteArrayInputStream)inputStream).close();
                }
                catch (Exception ex5) {}
            }
        }
        return array2;
    }
    
    public static Object decodeToObject(final String s) {
        final byte[] decode = decode(s);
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        Object object = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(decode);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            object = objectInputStream.readObject();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            object = null;
        }
        catch (ClassNotFoundException ex2) {
            ex2.printStackTrace();
            object = null;
        }
        finally {
            try {
                byteArrayInputStream.close();
            }
            catch (Exception ex3) {}
            try {
                objectInputStream.close();
            }
            catch (Exception ex4) {}
        }
        return object;
    }
    
    public static boolean encodeToFile(final byte[] array, final String s) {
        boolean b = false;
        OutputStream outputStream = null;
        try {
            outputStream = new OutputStream(new FileOutputStream(s), 1);
            outputStream.write(array);
            b = true;
        }
        catch (IOException ex) {
            b = false;
        }
        finally {
            try {
                outputStream.close();
            }
            catch (Exception ex2) {}
        }
        return b;
    }
    
    public static boolean decodeToFile(final String s, final String s2) {
        boolean b = false;
        OutputStream outputStream = null;
        try {
            outputStream = new OutputStream(new FileOutputStream(s2), 0);
            outputStream.write(s.getBytes("UTF-8"));
            b = true;
        }
        catch (IOException ex) {
            b = false;
        }
        finally {
            try {
                outputStream.close();
            }
            catch (Exception ex2) {}
        }
        return b;
    }
    
    public static byte[] decodeFromFile(final String s) {
        Object o = null;
        InputStream inputStream = null;
        try {
            final File file = new File(s);
            int n = 0;
            if (file.length() > 2147483647L) {
                System.err.println("File is too big for this convenience method (" + file.length() + " bytes).");
                return null;
            }
            byte[] array;
            int read;
            for (array = new byte[(int)file.length()], inputStream = new InputStream(new BufferedInputStream(new FileInputStream(file)), 0); (read = inputStream.read(array, n, 4096)) >= 0; n += read) {}
            o = new byte[n];
            System.arraycopy(array, 0, o, 0, n);
        }
        catch (IOException ex) {
            System.err.println("Error decoding from file " + s);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (Exception ex2) {}
        }
        return (byte[])o;
    }
    
    public static String encodeFromFile(final String s) {
        String s2 = null;
        InputStream inputStream = null;
        try {
            final File file = new File(s);
            byte[] array;
            int n;
            int read;
            for (array = new byte[Math.max((int)(file.length() * 1.4), 40)], n = 0, inputStream = new InputStream(new BufferedInputStream(new FileInputStream(file)), 1); (read = inputStream.read(array, n, 4096)) >= 0; n += read) {}
            s2 = new String(array, 0, n, "UTF-8");
        }
        catch (IOException ex) {
            System.err.println("Error encoding from file " + s);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (Exception ex2) {}
        }
        return s2;
    }
    
    public static void encodeFileToFile(final String s, final String s2) {
        final String encodeFromFile = encodeFromFile(s);
        java.io.OutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(s2));
            outputStream.write(encodeFromFile.getBytes("US-ASCII"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                outputStream.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public static void decodeFileToFile(final String s, final String s2) {
        final byte[] decodeFromFile = decodeFromFile(s);
        java.io.OutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(s2));
            outputStream.write(decodeFromFile);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                outputStream.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    static {
        _STANDARD_ALPHABET = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
        _STANDARD_DECODABET = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9 };
        _URL_SAFE_ALPHABET = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
        _URL_SAFE_DECODABET = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9 };
        _ORDERED_ALPHABET = new byte[] { 45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 };
        _ORDERED_DECODABET = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -9, -9, -9, -9 };
    }
    
    public static class OutputStream extends FilterOutputStream
    {
        private boolean encode;
        private int position;
        private byte[] buffer;
        private int bufferLength;
        private int lineLength;
        private boolean breakLines;
        private byte[] b4;
        private boolean suspendEncoding;
        private int options;
        private byte[] alphabet;
        private byte[] decodabet;
        
        public OutputStream(final java.io.OutputStream outputStream) {
            this(outputStream, 1);
        }
        
        public OutputStream(final java.io.OutputStream outputStream, final int options) {
            super(outputStream);
            this.breakLines = ((options & 0x8) != 0x8);
            this.encode = ((options & 0x1) == 0x1);
            this.bufferLength = (this.encode ? 3 : 4);
            this.buffer = new byte[this.bufferLength];
            this.position = 0;
            this.lineLength = 0;
            this.suspendEncoding = false;
            this.b4 = new byte[4];
            this.options = options;
            this.alphabet = getAlphabet(options);
            this.decodabet = getDecodabet(options);
        }
        
        public void write(final int n) throws IOException {
            if (this.suspendEncoding) {
                super.out.write(n);
                return;
            }
            if (this.encode) {
                this.buffer[this.position++] = (byte)n;
                if (this.position >= this.bufferLength) {
                    this.out.write(encode3to4(this.b4, this.buffer, this.bufferLength, this.options));
                    this.lineLength += 4;
                    if (this.breakLines && this.lineLength >= 76) {
                        this.out.write(10);
                        this.lineLength = 0;
                    }
                    this.position = 0;
                }
            }
            else if (this.decodabet[n & 0x7F] > -5) {
                this.buffer[this.position++] = (byte)n;
                if (this.position >= this.bufferLength) {
                    this.out.write(this.b4, 0, decode4to3(this.buffer, 0, this.b4, 0, this.options));
                    this.position = 0;
                }
            }
            else if (this.decodabet[n & 0x7F] != -5) {
                throw new IOException("Invalid character in Base64 data.");
            }
        }
        
        public void write(final byte[] array, final int n, final int n2) throws IOException {
            if (this.suspendEncoding) {
                super.out.write(array, n, n2);
                return;
            }
            for (int i = 0; i < n2; ++i) {
                this.write(array[n + i]);
            }
        }
        
        public void flushBase64() throws IOException {
            if (this.position > 0) {
                if (!this.encode) {
                    throw new IOException("Base64 input not properly padded.");
                }
                this.out.write(encode3to4(this.b4, this.buffer, this.position, this.options));
                this.position = 0;
            }
        }
        
        public void close() throws IOException {
            this.flushBase64();
            super.close();
            this.buffer = null;
            this.out = null;
        }
        
        public void suspendEncoding() throws IOException {
            this.flushBase64();
            this.suspendEncoding = true;
        }
        
        public void resumeEncoding() {
            this.suspendEncoding = false;
        }
    }
    
    public static class InputStream extends FilterInputStream
    {
        private boolean encode;
        private int position;
        private byte[] buffer;
        private int bufferLength;
        private int numSigBytes;
        private int lineLength;
        private boolean breakLines;
        private int options;
        private byte[] alphabet;
        private byte[] decodabet;
        
        public InputStream(final java.io.InputStream inputStream) {
            this(inputStream, 0);
        }
        
        public InputStream(final java.io.InputStream inputStream, final int options) {
            super(inputStream);
            this.breakLines = ((options & 0x8) != 0x8);
            this.encode = ((options & 0x1) == 0x1);
            this.bufferLength = (this.encode ? 4 : 3);
            this.buffer = new byte[this.bufferLength];
            this.position = -1;
            this.lineLength = 0;
            this.options = options;
            this.alphabet = getAlphabet(options);
            this.decodabet = getDecodabet(options);
        }
        
        public int read() throws IOException {
            if (this.position < 0) {
                if (this.encode) {
                    final byte[] array = new byte[3];
                    int n = 0;
                    for (int i = 0; i < 3; ++i) {
                        try {
                            final int read = this.in.read();
                            if (read >= 0) {
                                array[i] = (byte)read;
                                ++n;
                            }
                        }
                        catch (IOException ex) {
                            if (i == 0) {
                                throw ex;
                            }
                        }
                    }
                    if (n <= 0) {
                        return -1;
                    }
                    encode3to4(array, 0, n, this.buffer, 0, this.options);
                    this.position = 0;
                    this.numSigBytes = 4;
                }
                else {
                    final byte[] array2 = new byte[4];
                    int j;
                    for (j = 0; j < 4; ++j) {
                        int read2;
                        do {
                            read2 = this.in.read();
                        } while (read2 >= 0 && this.decodabet[read2 & 0x7F] <= -5);
                        if (read2 < 0) {
                            break;
                        }
                        array2[j] = (byte)read2;
                    }
                    if (j == 4) {
                        this.numSigBytes = decode4to3(array2, 0, this.buffer, 0, this.options);
                        this.position = 0;
                    }
                    else {
                        if (j == 0) {
                            return -1;
                        }
                        throw new IOException("Improperly padded Base64 input.");
                    }
                }
            }
            if (this.position < 0) {
                throw new IOException("Error in Base64 code reading stream.");
            }
            if (this.position >= this.numSigBytes) {
                return -1;
            }
            if (this.encode && this.breakLines && this.lineLength >= 76) {
                this.lineLength = 0;
                return 10;
            }
            ++this.lineLength;
            final byte b = this.buffer[this.position++];
            if (this.position >= this.bufferLength) {
                this.position = -1;
            }
            return b & 0xFF;
        }
        
        public int read(final byte[] array, final int n, final int n2) throws IOException {
            int i = 0;
            while (i < n2) {
                final int read = this.read();
                if (read >= 0) {
                    array[n + i] = (byte)read;
                    ++i;
                }
                else {
                    if (i == 0) {
                        return -1;
                    }
                    break;
                }
            }
            return i;
        }
    }
}
