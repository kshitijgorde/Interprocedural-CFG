// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util.zip;

import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.IOException;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.Dictionary;
import java.io.OutputStream;

public class $EG extends $CD
{
    public OutputStream $HG(final String s) {
        return new $QG(this, s);
    }
    
    public $EG() {
    }
    
    public $EG(final InputStream inputStream) {
        super(inputStream);
    }
    
    public $EG(final InputStream inputStream, final FilenameFilter filenameFilter, final Dictionary dictionary) {
        super(inputStream, filenameFilter, dictionary);
    }
    
    public void save(final OutputStream outputStream) throws IOException {
        this.save(outputStream, null, 8, -1);
    }
    
    public synchronized void save(final OutputStream outputStream, final String comment, final int method, final int level) throws IOException {
        if (super.$PG != null) {
            this.get(";-)");
        }
        final ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        if (comment != null && !comment.equals("")) {
            zipOutputStream.setComment(comment);
        }
        zipOutputStream.setLevel(level);
        zipOutputStream.setMethod(method);
        final Enumeration<String> keys = this.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            zipOutputStream.putNextEntry(new ZipEntry(s));
            final byte[] array = (byte[])this.get(s);
            zipOutputStream.write(array, 0, array.length);
        }
        zipOutputStream.closeEntry();
        zipOutputStream.finish();
    }
}
