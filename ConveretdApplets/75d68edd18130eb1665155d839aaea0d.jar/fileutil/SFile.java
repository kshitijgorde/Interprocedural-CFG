// 
// Decompiled by Procyon v0.5.30
// 

package fileutil;

import fileutil.filefilter.SFileFilter;
import java.util.Vector;
import java.net.URI;
import java.io.File;

public class SFile extends File
{
    String _AbsolutePath;
    String _Name;
    String _TypeDescription;
    Boolean _isFile;
    Boolean _isDirectory;
    Boolean _isHidden;
    Boolean _Exists;
    Boolean _CanRead;
    Long _Length;
    Long _LastModified;
    
    public SFile(final File file, final String s) {
        super(file, s);
        this.init();
    }
    
    public SFile(final String s) {
        super(s);
        this.init();
    }
    
    public SFile(final URI uri) {
        super(uri);
        this.init();
    }
    
    public SFile(final File file) {
        super(file.getAbsolutePath());
        this.init();
    }
    
    public void init() {
        this._Exists = null;
        this._CanRead = null;
        this._Length = null;
        this._Name = null;
        this._LastModified = null;
        this._AbsolutePath = null;
        this._isFile = null;
        this._isDirectory = null;
        this._isHidden = null;
        this._TypeDescription = null;
    }
    
    public boolean exists() {
        if (null == this._Exists) {
            this._Exists = super.exists();
        }
        return this._Exists;
    }
    
    public boolean canRead() {
        if (null == this._CanRead) {
            this._CanRead = super.canRead();
        }
        return this._CanRead;
    }
    
    public long length() {
        if (null == this._Length) {
            this._Length = super.length();
        }
        return this._Length;
    }
    
    public String getName() {
        if (null == this._Name) {
            this._Name = super.getName();
        }
        return this._Name;
    }
    
    public long lastModified() {
        if (null == this._LastModified) {
            this._LastModified = super.lastModified();
        }
        return this._LastModified;
    }
    
    public String getAbsolutePath() {
        if (null == this._AbsolutePath) {
            this._AbsolutePath = super.getAbsolutePath();
        }
        return this._AbsolutePath;
    }
    
    public boolean isDirectory() {
        if (null == this._isDirectory) {
            this._isDirectory = super.isDirectory();
        }
        return this._isDirectory;
    }
    
    public boolean isFile() {
        if (null == this._isFile) {
            this._isFile = super.isFile();
        }
        return this._isFile;
    }
    
    public boolean isHidden() {
        if (null == this._isHidden) {
            this._isHidden = super.isHidden();
        }
        return this._isHidden;
    }
    
    public String getTypeDescription() {
        if (null == this._TypeDescription) {
            this._TypeDescription = FileUtil.getTypeDescription(this);
        }
        return this._TypeDescription;
    }
    
    public Vector<SFile> listFileVector() {
        final File[] listFiles = super.listFiles();
        if (listFiles == null) {
            return null;
        }
        final Vector vector = new Vector<SFile>(listFiles.length);
        final File[] array = listFiles;
        for (int length = array.length, i = 0; i < length; ++i) {
            vector.add(new SFile(array[i]));
        }
        return (Vector<SFile>)vector;
    }
    
    public Vector<SFile> listFileVector(final SFileFilter sFileFilter) {
        final File[] listFiles = super.listFiles();
        if (listFiles == null) {
            return null;
        }
        final Vector vector = new Vector<SFile>(listFiles.length);
        final File[] array = listFiles;
        for (int length = array.length, i = 0; i < length; ++i) {
            final SFile sFile = new SFile(array[i]);
            if (sFileFilter == null || sFileFilter.accept(sFile)) {
                vector.add(sFile);
            }
        }
        return (Vector<SFile>)vector;
    }
}
