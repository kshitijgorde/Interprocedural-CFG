// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.F;

import java.io.FilterInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.Writer;
import java.io.Reader;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.File;

public class A
{
    public static String C(final File file) {
        return A(file.getName());
    }
    
    public static String A(final String s) {
        String substring = null;
        final int lastIndex = s.lastIndexOf(".");
        if (lastIndex != -1 && lastIndex < s.length() - 1) {
            substring = s.substring(lastIndex + 1);
        }
        return substring;
    }
    
    public static String B(final File file) {
        return B(file.getName());
    }
    
    public static String B(final String s) {
        final int lastIndex = s.lastIndexOf(".");
        String substring;
        if (lastIndex != -1 && lastIndex < s.length() - 1) {
            substring = s.substring(0, lastIndex);
        }
        else {
            substring = s;
        }
        return substring;
    }
    
    public static File[] B(final File file, final File file2) throws IOException {
        final File[] listFiles = file.listFiles();
        File[] array = null;
        StringBuffer sb = null;
        if (listFiles != null) {
            array = new File[listFiles.length];
            for (int i = 0; i < listFiles.length; ++i) {
                final File file3 = listFiles[i];
                final File file4 = array[i] = new File(file2, file3.getName());
                if (!file3.renameTo(file4)) {
                    if (sb == null) {
                        sb = new StringBuffer();
                    }
                    sb.append("ERROR: file rename failed: fileSrc=" + file3 + ", fileDst=" + file4 + "\r\n");
                }
            }
        }
        if (sb != null) {
            throw new IOException(sb.toString());
        }
        return array;
    }
    
    public static void A(final File file) throws IOException {
        final File[] listFiles = file.listFiles();
        StringBuffer sb = null;
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; ++i) {
                if (!listFiles[i].delete()) {
                    if (sb == null) {
                        sb = new StringBuffer();
                    }
                    sb.append("ERROR: file delete failed:" + listFiles[i].getAbsolutePath() + "\r\n");
                }
            }
        }
        if (sb != null) {
            throw new IOException(sb.toString());
        }
    }
    
    public static long A(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        return A(inputStream, outputStream, 0L, -1L, null);
    }
    
    public static long A(final InputStream inputStream, final OutputStream outputStream, final long n, final long n2, byte[] array) throws IOException {
        if (n > 0L) {
            inputStream.skip(n);
        }
        if (array == null) {
            array = new byte[16384];
        }
        long n3 = 0L;
        int read;
        while (n2 != 0L && (read = inputStream.read(array)) != -1) {
            final int n4 = (n2 < 0L) ? read : ((n2 - read > 0L) ? read : ((int)(read - n2)));
            outputStream.write(array, 0, n4);
            n3 += n4;
        }
        return n3;
    }
    
    public static long A(final Reader reader, final Writer writer, final long n, long n2, char[] array) throws IOException {
        if (n > 0L) {
            reader.skip(n);
        }
        if (array == null) {
            array = new char[8192];
        }
        long n3 = 0L;
        int read;
        while (n2 != 0L && (read = reader.read(array)) != -1) {
            final int n4 = (n2 < 0L) ? read : ((read - n2 > 0L) ? ((int)n2) : read);
            writer.write(array, 0, n4);
            n3 += n4;
            n2 -= n4;
        }
        return n3;
    }
    
    public static void C(final File file, final File file2) throws IOException {
        FileInputStream fileInputStream = null;
        FilterInputStream filterInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            filterInputStream = new BufferedInputStream(fileInputStream = new FileInputStream(file));
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream = new FileOutputStream(file2));
            final byte[] array = new byte[8192];
            int read;
            while ((read = filterInputStream.read(array)) != -1) {
                bufferedOutputStream.write(array, 0, read);
            }
        }
        finally {
            try {
                ((BufferedInputStream)filterInputStream).close();
            }
            catch (Exception ex) {}
            try {
                fileInputStream.close();
            }
            catch (Exception ex2) {}
            try {
                bufferedOutputStream.close();
            }
            catch (Exception ex3) {}
            try {
                fileOutputStream.close();
            }
            catch (Exception ex4) {}
        }
    }
    
    public static long A(final File file, final File file2, final long n, final long n2, final byte[] array) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(file2);
            return A(inputStream, outputStream, n, n2, array);
        }
        finally {
            try {
                ((FileInputStream)inputStream).close();
            }
            catch (Exception ex) {}
            try {
                ((FileOutputStream)outputStream).close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public static long A(final File file, final File file2) throws IOException {
        return A(file, file2, 0L, -1L, null);
    }
    
    public static void A(final InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            }
            catch (Exception ex) {}
        }
    }
    
    public static void A(final OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            }
            catch (Exception ex) {}
        }
    }
    
    public static void A(final Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            }
            catch (Exception ex) {}
        }
    }
    
    public static void A(final Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            }
            catch (Exception ex) {}
        }
    }
}
