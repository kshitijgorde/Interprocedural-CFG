// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.graphics.ImageLoader;
import java.io.IOException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;

public final class TIFFFileFormat extends FileFormat
{
    boolean isFileFormat(final LEDataInputStream leDataInputStream) {
        try {
            final byte[] array = new byte[4];
            leDataInputStream.read(array);
            leDataInputStream.unread(array);
            return array[0] == array[1] && ((array[0] == 73 && array[2] == 42 && array[3] == 0) || (array[0] == 77 && array[2] == 0 && array[3] == 42));
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    ImageData[] loadFromByteStream() {
        final byte[] array = new byte[8];
        ImageData[] array2 = new ImageData[0];
        final TIFFRandomFileAccess tiffRandomFileAccess = new TIFFRandomFileAccess(this.inputStream);
        try {
            tiffRandomFileAccess.read(array);
            if (array[0] != array[1]) {
                SWT.error(40);
            }
            if ((array[0] != 73 || array[2] != 42 || array[3] != 0) && (array[0] != 77 || array[2] != 0 || array[3] != 42)) {
                SWT.error(40);
            }
            final boolean b = array[0] == 73;
            int i = b ? ((array[4] & 0xFF) | (array[5] & 0xFF) << 8 | (array[6] & 0xFF) << 16 | (array[7] & 0xFF) << 24) : ((array[7] & 0xFF) | (array[6] & 0xFF) << 8 | (array[5] & 0xFF) << 16 | (array[4] & 0xFF) << 24);
            while (i != 0) {
                tiffRandomFileAccess.seek(i);
                final TIFFDirectory tiffDirectory = new TIFFDirectory(tiffRandomFileAccess, b, this.loader);
                final int[] array3 = { 0 };
                final ImageData read = tiffDirectory.read(array3);
                i = array3[0];
                final ImageData[] array4 = array2;
                array2 = new ImageData[array4.length + 1];
                System.arraycopy(array4, 0, array2, 0, array4.length);
                array2[array2.length - 1] = read;
            }
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        return array2;
    }
    
    void unloadIntoByteStream(final ImageLoader imageLoader) {
        final TIFFDirectory tiffDirectory = new TIFFDirectory(imageLoader.data[0]);
        try {
            tiffDirectory.writeToStream(this.outputStream);
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
    }
}
