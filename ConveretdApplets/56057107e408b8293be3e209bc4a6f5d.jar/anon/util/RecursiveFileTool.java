// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import org.bouncycastle.crypto.digests.MD5Digest;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import logging.LogHolder;
import logging.LogType;
import java.io.File;

public class RecursiveFileTool
{
    private static final int INIT_DEPTH = 0;
    private static final int MAX_DEPTH_IGNORE = -2;
    private static final int COPY_BUFFER_SIZE = 1024;
    private static final int EOF = -1;
    
    public static void copy(final File file, final File file2) {
        if (file == null) {
            LogHolder.log(2, LogType.MISC, "Source file is null: This should never happen");
            return;
        }
        if (file2 == null) {
            LogHolder.log(2, LogType.MISC, "Destination file is null: This should never happen");
            return;
        }
        if (file.isDirectory()) {
            LogHolder.log(3, LogType.MISC, "File " + file.getName() + " is a directory: cannot copy it");
            return;
        }
        if (!file.exists()) {
            LogHolder.log(3, LogType.MISC, "There is no such file or directory: " + file.getName());
            return;
        }
        try {
            copySingleFile(file, file2);
        }
        catch (IOException ex) {
            LogHolder.log(2, LogType.MISC, "An IO Exception while copying file " + file.getName() + ": " + ex.getMessage());
        }
    }
    
    public static void copyRecursive(final File file, final File file2) {
        copyRecursion(file, file2, 0, -2);
    }
    
    public static void copyRecursive(final File file, final File file2, final int n) {
        copyRecursion(file, file2, 0, n);
    }
    
    private static void copyRecursion(final File file, final File file2, final int n, final int n2) {
        if (file == null) {
            LogHolder.log(2, LogType.MISC, "Source file is null: This should never happen");
            return;
        }
        if (file2 == null) {
            LogHolder.log(2, LogType.MISC, "Destination file is null: This should never happen");
            return;
        }
        if (file2.getAbsolutePath().startsWith(file.getAbsolutePath())) {
            LogHolder.log(3, LogType.MISC, "destination path is in source path: to avoid endless loops, operation is not allowed");
            return;
        }
        if (!file.exists()) {
            LogHolder.log(3, LogType.MISC, "There is no such file or directory: " + file.getName());
            return;
        }
        if (file.isDirectory()) {
            final String[] list = file.list();
            if (!file2.mkdir()) {
                LogHolder.log(3, LogType.MISC, "Cannot create directory: " + file2.getName());
                return;
            }
            for (int i = 0; i < list.length; ++i) {
                final String s = list[i];
                if (n2 == -2 || n < n2) {
                    copyRecursion(new File(file.getAbsolutePath() + File.separator + s), new File(file2.getAbsolutePath() + File.separator + s), n + 1, n2);
                }
            }
        }
        else {
            try {
                copySingleFile(file, file2);
            }
            catch (IOException ex) {
                LogHolder.log(2, LogType.MISC, "An IO Exception while copying file " + file.getName() + ": " + ex.getMessage());
            }
        }
    }
    
    static void copySingleFile(final File file, final File file2) throws IOException {
        copySingleFile(new FileInputStream(file), file2);
    }
    
    public static boolean deleteRecursion(final File file) {
        if (file == null) {
            LogHolder.log(2, LogType.MISC, "Source file is null: This should never happen");
            return true;
        }
        if (!file.exists()) {
            LogHolder.log(3, LogType.MISC, "There is no such file or directory: " + file.getName());
            return true;
        }
        if (file.isDirectory()) {
            final String[] list = file.list();
            for (int n = 0; list != null && n < list.length; ++n) {
                deleteRecursion(new File(file.getAbsolutePath() + File.separator + list[n]));
            }
        }
        LogHolder.log(7, LogType.MISC, file.getName() + (file.delete() ? " was successfully deleted." : " was not successfully deleted."));
        return !file.exists();
    }
    
    static void copySingleFile(final InputStream inputStream, final File file) throws IOException {
        IOException ex = null;
        if (inputStream == null) {
            LogHolder.log(3, LogType.MISC, "Abort copy process: InputStream is null");
            return;
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            final byte[] array = new byte[1024];
            while (inputStream.available() > 0) {
                final int read = inputStream.read(array);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(array, 0, read);
            }
        }
        catch (IOException ex2) {
            ex = ex2;
        }
        try {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            inputStream.close();
        }
        catch (IOException ex3) {}
        if (ex != null) {
            throw ex;
        }
    }
    
    public static boolean equals(final File file, final byte[] array, final long n) {
        try {
            if (Util.arraysEqual(createMD5Digest(file), array)) {
                return true;
            }
        }
        catch (Exception ex) {
            if (file.length() == n) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean equals(final File file, final File file2, final boolean b) {
        boolean b2 = false;
        if (file == null || file2 == null) {
            return false;
        }
        try {
            if (!file.exists() || !file2.exists()) {
                return false;
            }
            b2 = true;
        }
        catch (SecurityException ex) {
            LogHolder.log(2, LogType.MISC, ex);
        }
        try {
            if (file.length() != file2.length()) {
                return false;
            }
            b2 = true;
        }
        catch (SecurityException ex2) {
            LogHolder.log(2, LogType.MISC, ex2);
        }
        if (!b && b2) {
            return true;
        }
        try {
            if (!Util.arraysEqual(createMD5Digest(file), createMD5Digest(file2))) {
                return false;
            }
        }
        catch (IOException ex3) {
            LogHolder.log(2, LogType.MISC, ex3);
            return false;
        }
        catch (SecurityException ex4) {
            LogHolder.log(2, LogType.MISC, ex4);
        }
        return true;
    }
    
    public static long getFileSize(final File file) throws SecurityException {
        if (file == null || !file.exists()) {
            return -1L;
        }
        return file.length();
    }
    
    public static byte[] createMD5Digest(final File file) throws IOException, SecurityException {
        final byte[] streamAsBytes = ResourceLoader.getStreamAsBytes(new FileInputStream(file));
        final MD5Digest md5Digest = new MD5Digest();
        final byte[] array = new byte[md5Digest.getDigestSize()];
        md5Digest.update(streamAsBytes, 0, streamAsBytes.length);
        md5Digest.doFinal(array, 0);
        return array;
    }
}
