// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.util.Enumeration;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

public class UnZipper
{
    private static String path;
    
    public static final void copyInputStream(final InputStream in, final OutputStream out) throws IOException {
        final byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }
    
    public static final void unzip(final String file) {
        int s = file.lastIndexOf(System.getProperty("file.separator"));
        if (s == -1) {
            s = 0;
        }
        final String filePrefix = file.substring(s + 1, file.length() - 4);
        UnZipper.path = file.substring(0, s) + System.getProperty("file.separator") + filePrefix;
        new File(UnZipper.path).mkdir();
        try {
            final ZipFile zipFile = new ZipFile(file);
            final Enumeration entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                final ZipEntry entry = entries.nextElement();
                if (entry.isDirectory()) {
                    System.err.println("Extracting directory: " + entry.getName());
                    new File(entry.getName()).mkdir();
                }
                else {
                    System.err.println("Extracting file: " + entry.getName());
                    final String newFileName = UnZipper.path + System.getProperty("file.separator") + entry.getName();
                    copyInputStream(zipFile.getInputStream(entry), new BufferedOutputStream(new FileOutputStream(newFileName)));
                }
            }
            zipFile.close();
        }
        catch (IOException ioe) {
            System.err.println("Unhandled exception:");
            ioe.printStackTrace();
        }
    }
    
    public static String getPath() {
        return UnZipper.path;
    }
}
