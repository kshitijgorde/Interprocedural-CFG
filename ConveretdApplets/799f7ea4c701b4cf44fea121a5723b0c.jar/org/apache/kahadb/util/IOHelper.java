// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.util;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

public final class IOHelper
{
    protected static final int MAX_DIR_NAME_LENGTH;
    protected static final int MAX_FILE_NAME_LENGTH;
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    
    public static String getDefaultDataDirectory() {
        return getDefaultDirectoryPrefix() + "activemq-data";
    }
    
    public static String getDefaultStoreDirectory() {
        return getDefaultDirectoryPrefix() + "amqstore";
    }
    
    public static String getDefaultDirectoryPrefix() {
        try {
            return System.getProperty("org.apache.activemq.default.directory.prefix", "");
        }
        catch (Exception e) {
            return "";
        }
    }
    
    public static String toFileSystemDirectorySafeName(final String name) {
        return toFileSystemSafeName(name, true, IOHelper.MAX_DIR_NAME_LENGTH);
    }
    
    public static String toFileSystemSafeName(final String name) {
        return toFileSystemSafeName(name, false, IOHelper.MAX_FILE_NAME_LENGTH);
    }
    
    public static String toFileSystemSafeName(final String name, final boolean dirSeparators, final int maxFileLength) {
        final int size = name.length();
        final StringBuffer rc = new StringBuffer(size * 2);
        for (int i = 0; i < size; ++i) {
            final char c = name.charAt(i);
            boolean valid = c >= 'a' && c <= 'z';
            valid = (valid || (c >= 'A' && c <= 'Z'));
            valid = (valid || (c >= '0' && c <= '9'));
            valid = (valid || c == '_' || c == '-' || c == '.' || c == '#' || (dirSeparators && (c == '/' || c == '\\')));
            if (valid) {
                rc.append(c);
            }
            else {
                rc.append('#');
                rc.append(HexSupport.toHexFromInt(c, true));
            }
        }
        String result = rc.toString();
        if (result.length() > maxFileLength) {
            result = result.substring(result.length() - maxFileLength, result.length());
        }
        return result;
    }
    
    public static boolean deleteFile(final File fileToDelete) {
        if (fileToDelete == null || !fileToDelete.exists()) {
            return true;
        }
        boolean result = deleteChildren(fileToDelete);
        result &= fileToDelete.delete();
        return result;
    }
    
    public static boolean deleteChildren(final File parent) {
        if (parent == null || !parent.exists()) {
            return false;
        }
        boolean result = true;
        if (parent.isDirectory()) {
            final File[] files = parent.listFiles();
            if (files == null) {
                result = false;
            }
            else {
                for (int i = 0; i < files.length; ++i) {
                    final File file = files[i];
                    if (!file.getName().equals(".")) {
                        if (!file.getName().equals("..")) {
                            if (file.isDirectory()) {
                                result &= deleteFile(file);
                            }
                            else {
                                result &= file.delete();
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
    
    public static void moveFile(final File src, final File targetDirectory) throws IOException {
        if (!src.renameTo(new File(targetDirectory, src.getName()))) {
            throw new IOException("Failed to move " + src + " to " + targetDirectory);
        }
    }
    
    public static void copyFile(final File src, final File dest) throws IOException {
        final FileInputStream fileSrc = new FileInputStream(src);
        final FileOutputStream fileDest = new FileOutputStream(dest);
        copyInputStream(fileSrc, fileDest);
    }
    
    public static void copyInputStream(final InputStream in, final OutputStream out) throws IOException {
        final byte[] buffer = new byte[4096];
        for (int len = in.read(buffer); len >= 0; len = in.read(buffer)) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }
    
    public static void mkdirs(final File dir) throws IOException {
        if (dir.exists()) {
            if (!dir.isDirectory()) {
                throw new IOException("Failed to create directory '" + dir + "', regular file already existed with that name");
            }
        }
        else if (!dir.mkdirs()) {
            throw new IOException("Failed to create directory '" + dir + "'");
        }
    }
    
    static {
        MAX_DIR_NAME_LENGTH = Integer.valueOf(System.getProperty("MaximumDirNameLength", "200"));
        MAX_FILE_NAME_LENGTH = Integer.valueOf(System.getProperty("MaximumFileNameLength", "64"));
    }
}
