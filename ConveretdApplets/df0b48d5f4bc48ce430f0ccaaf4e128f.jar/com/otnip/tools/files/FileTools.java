// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.tools.files;

import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.HashMap;

public class FileTools
{
    private static HashMap<String, String> fileExtensionsAndDescriptions;
    
    public static void setFileExtensionAndDescription(final String extension, final String description) {
        FileTools.fileExtensionsAndDescriptions.put(extension, description);
    }
    
    public static String getDescription(final String fileExtension) {
        String description = FileTools.fileExtensionsAndDescriptions.get(fileExtension);
        if (description == null) {
            description = "";
        }
        return description;
    }
    
    public static File setExtension(final File file, final String extension) {
        final String filename = file.getName();
        final int index = filename.lastIndexOf(46);
        File newFile;
        if (index != -1) {
            newFile = new File(file.getParentFile(), filename.substring(0, index + 1) + extension);
        }
        else {
            newFile = new File(file.getAbsolutePath() + "." + extension);
        }
        return newFile;
    }
    
    public static String getExtension(final File file) {
        String fileExtension = "";
        final String filename = file.getName();
        final int index = filename.lastIndexOf(46);
        if (index != -1) {
            fileExtension = file.getName().substring(index + 1, filename.length());
        }
        return fileExtension;
    }
    
    public static String getText(final File file) throws Exception {
        final FileInputStream fis = new FileInputStream(file);
        final BufferedInputStream bis = new BufferedInputStream(fis);
        final byte[] bytes = new byte[(int)file.length()];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = (byte)bis.read();
        }
        fis.close();
        return new String(bytes);
    }
    
    public static void writeText(final File file, final String text) throws Exception {
        final FileWriter writer = new FileWriter(file);
        writer.write(text);
        writer.flush();
        writer.close();
    }
    
    public static File[] getAllFilesRecursively(final File dir) {
        final ArrayList<File> files = new ArrayList<File>();
        getAllFilesRecursively(dir, files);
        return files.toArray(new File[files.size()]);
    }
    
    public static void delete(final File file) throws Exception {
        if (file.isDirectory()) {
            final File[] arr$;
            final File[] files = arr$ = file.listFiles();
            for (final File f : arr$) {
                delete(f);
            }
        }
        file.delete();
    }
    
    public static void copy(final File srcFile, final File destFile) throws Exception {
        final FileInputStream fis = new FileInputStream(srcFile);
        final FileChannel inputChannel = fis.getChannel();
        final FileOutputStream fos = new FileOutputStream(destFile);
        final FileChannel outputChannel = fos.getChannel();
        final ByteBuffer buffer = ByteBuffer.allocate(524288);
        while (fis.available() > 0) {
            inputChannel.read(buffer);
            buffer.flip();
            outputChannel.write(buffer);
            buffer.flip();
        }
        buffer.flip();
        while (buffer.remaining() > 0) {
            outputChannel.write(buffer);
        }
        outputChannel.close();
        fos.close();
    }
    
    private static void getAllFilesRecursively(final File dir, final ArrayList<File> files) {
        final File[] arr$;
        final File[] localFiles = arr$ = dir.listFiles();
        for (final File file : arr$) {
            if (file.isDirectory()) {
                getAllFilesRecursively(file, files);
            }
            files.add(file);
        }
    }
    
    static {
        FileTools.fileExtensionsAndDescriptions = new HashMap<String, String>();
    }
}
