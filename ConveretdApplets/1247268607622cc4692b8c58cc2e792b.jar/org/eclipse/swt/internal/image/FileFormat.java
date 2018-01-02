// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import java.io.OutputStream;
import java.io.InputStream;
import org.eclipse.swt.SWT;
import java.io.IOException;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

public abstract class FileFormat
{
    static final String FORMAT_PACKAGE = "org.eclipse.swt.internal.image";
    static final String FORMAT_SUFFIX = "FileFormat";
    static final String[] FORMATS;
    LEDataInputStream inputStream;
    LEDataOutputStream outputStream;
    ImageLoader loader;
    int compression;
    
    static {
        FORMATS = new String[] { "WinBMP", "WinBMP", "GIF", "WinICO", "JPEG", "PNG", "TIFF", "OS2BMP" };
    }
    
    abstract boolean isFileFormat(final LEDataInputStream p0);
    
    abstract ImageData[] loadFromByteStream();
    
    public ImageData[] loadFromStream(final LEDataInputStream inputStream) {
        try {
            this.inputStream = inputStream;
            return this.loadFromByteStream();
        }
        catch (Exception ex) {
            if (ex instanceof IOException) {
                SWT.error(39, ex);
            }
            else {
                SWT.error(40, ex);
            }
            return null;
        }
    }
    
    public static ImageData[] load(final InputStream inputStream, final ImageLoader loader) {
        FileFormat fileFormat = null;
        final LEDataInputStream leDataInputStream = new LEDataInputStream(inputStream);
        boolean b = false;
        for (int i = 1; i < FileFormat.FORMATS.length; ++i) {
            if (FileFormat.FORMATS[i] != null) {
                try {
                    fileFormat = (FileFormat)Class.forName("org.eclipse.swt.internal.image." + FileFormat.FORMATS[i] + "FileFormat").newInstance();
                    if (fileFormat.isFileFormat(leDataInputStream)) {
                        b = true;
                        break;
                    }
                }
                catch (ClassNotFoundException ex) {
                    FileFormat.FORMATS[i] = null;
                }
                catch (Exception ex2) {}
            }
        }
        if (!b) {
            SWT.error(42);
        }
        fileFormat.loader = loader;
        return fileFormat.loadFromStream(leDataInputStream);
    }
    
    public static void save(final OutputStream outputStream, final int n, final ImageLoader imageLoader) {
        if (n < 0 || n >= FileFormat.FORMATS.length) {
            SWT.error(42);
        }
        if (FileFormat.FORMATS[n] == null) {
            SWT.error(42);
        }
        if (imageLoader.data == null || imageLoader.data.length < 1) {
            SWT.error(5);
        }
        final LEDataOutputStream leDataOutputStream = new LEDataOutputStream(outputStream);
        FileFormat fileFormat = null;
        try {
            fileFormat = (FileFormat)Class.forName("org.eclipse.swt.internal.image." + FileFormat.FORMATS[n] + "FileFormat").newInstance();
        }
        catch (Exception ex) {
            SWT.error(42);
        }
        if (n == 1) {
            switch (imageLoader.data[0].depth) {
                case 8: {
                    fileFormat.compression = 1;
                    break;
                }
                case 4: {
                    fileFormat.compression = 2;
                    break;
                }
            }
        }
        fileFormat.unloadIntoStream(imageLoader, leDataOutputStream);
    }
    
    abstract void unloadIntoByteStream(final ImageLoader p0);
    
    public void unloadIntoStream(final ImageLoader imageLoader, final LEDataOutputStream outputStream) {
        try {
            this.outputStream = outputStream;
            this.unloadIntoByteStream(imageLoader);
            this.outputStream.flush();
        }
        catch (Exception ex) {
            try {
                this.outputStream.flush();
            }
            catch (Exception ex2) {}
            SWT.error(39, ex);
        }
    }
}
