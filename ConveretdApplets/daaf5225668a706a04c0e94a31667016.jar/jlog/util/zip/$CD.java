// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util.zip;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.zip.ZipEntry;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipInputStream;
import java.util.Dictionary;
import java.io.FilenameFilter;
import java.util.Hashtable;

public class $CD extends Hashtable
{
    public static int $SG;
    FilenameFilter $KG;
    Dictionary $LG;
    ZipInputStream $PG;
    ByteArrayOutputStream $TG;
    byte[] $UG;
    boolean $DE;
    
    public synchronized void $GG(final InputStream inputStream) throws IOException {
        final ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final byte[] array = new byte[$CD.$SG];
        IOException ex = null;
        try {
            ZipEntry nextEntry;
            while ((nextEntry = zipInputStream.getNextEntry()) != null) {
                if (nextEntry.isDirectory()) {
                    zipInputStream.closeEntry();
                }
                else {
                    final String name = nextEntry.getName();
                    if (this.$KG != null && !this.$KG.accept(new File(name), name)) {
                        zipInputStream.closeEntry();
                    }
                    else {
                        while (true) {
                            final int read = zipInputStream.read(array, 0, $CD.$SG);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(array, 0, read);
                        }
                        super.put(name, byteArrayOutputStream.toByteArray());
                        byteArrayOutputStream.reset();
                    }
                }
            }
        }
        catch (IOException ex2) {
            System.err.println(ex2.toString());
            ex = ex2;
        }
        if (ex != null) {
            throw ex;
        }
    }
    
    public void $IF(final boolean $de) {
        this.$DE = $de;
    }
    
    public boolean $KE() {
        return this.$DE;
    }
    
    public byte[] $LD(final String s) throws IOException {
        byte[] array = super.get(s);
        if (array == null && this.$LG != null) {
            array = this.$LG.get(s);
        }
        if (array != null || this.$PG == null) {
            return array;
        }
        synchronized (this) {
            IOException ex = null;
            try {
                ZipEntry nextEntry;
                while ((nextEntry = this.$PG.getNextEntry()) != null) {
                    if (nextEntry.isDirectory()) {
                        this.$PG.closeEntry();
                    }
                    else {
                        final String name = nextEntry.getName();
                        if (this.$KG != null && !this.$KG.accept(new File(name), name)) {
                            this.$PG.closeEntry();
                        }
                        else {
                            while (true) {
                                final int read = this.$PG.read(this.$UG, 0, $CD.$SG);
                                if (read == -1) {
                                    break;
                                }
                                this.$TG.write(this.$UG, 0, read);
                            }
                            final byte[] byteArray = this.$TG.toByteArray();
                            if (!this.contains(name)) {
                                super.put(name, byteArray);
                            }
                            this.$TG.reset();
                            if (name.replace('\\', '/').equals(s.replace('\\', '/'))) {
                                // monitorexit(this)
                                return byteArray;
                            }
                            continue;
                        }
                    }
                }
            }
            catch (IOException ex2) {
                System.err.println(ex2.toString());
                ex = ex2;
            }
            this.flush();
            if (ex != null) {
                throw ex;
            }
        }
        return null;
    }
    
    static {
        $CD.$SG = 4096;
    }
    
    public $CD() {
        this(null, null, null);
    }
    
    public $CD(final InputStream inputStream) {
        this(inputStream, null, null);
    }
    
    public $CD(final InputStream inputStream, final FilenameFilter $kg, final Dictionary $lg) {
        this.$KG = null;
        this.$LG = null;
        this.$PG = null;
        this.$DE = false;
        this.$KG = $kg;
        this.$LG = $lg;
        if (inputStream != null) {
            this.$PG = new ZipInputStream(inputStream);
            this.$TG = new ByteArrayOutputStream();
            this.$UG = new byte[$CD.$SG];
        }
    }
    
    public void finalize() throws Throwable {
        super.finalize();
        this.flush();
    }
    
    public synchronized void flush() throws IOException {
        if (this.$PG != null) {
            try {
                this.$TG = null;
                this.$UG = null;
                this.$PG.close();
            }
            finally {
                this.$PG = null;
            }
        }
    }
    
    public Object get(final Object o) {
        try {
            return this.$LD((String)o);
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public Dictionary getDefaults() {
        return this.$LG;
    }
    
    public InputStream openStream(final String s) throws IOException {
        final byte[] array = (byte[])this.get(s);
        if (array == null) {
            throw new FileNotFoundException(s);
        }
        return new ByteArrayInputStream(array);
    }
    
    public synchronized Object put(final Object o, final Object o2) {
        final byte[] put = super.put((String)o, (byte[])o2);
        this.$DE |= (o2.equals(put) ^ true);
        return put;
    }
    
    public synchronized Object remove(final Object o) {
        final Object remove = super.remove(o);
        this.$DE |= (remove != null);
        return remove;
    }
}
