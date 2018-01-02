// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.ftp;

import java.util.List;

public class FTPFileIterator
{
    private List rawlines;
    private FTPFileEntryParser parser;
    private static final int UNINIT = -1;
    private static final int DIREMPTY = -2;
    private int itemptr;
    private int firstGoodEntry;
    private static final FTPFile[] EMPTY;
    
    FTPFileIterator(final FTPFileList rawlist) {
        this(rawlist, rawlist.getParser());
    }
    
    FTPFileIterator(final FTPFileList rawlist, final FTPFileEntryParser parser) {
        this.itemptr = 0;
        this.firstGoodEntry = -1;
        this.rawlines = rawlist.getLines();
        this.parser = parser;
    }
    
    private FTPFile parseFTPEntry(final String entry) {
        return this.parser.parseFTPEntry(entry);
    }
    
    private int getFirstGoodEntry() {
        FTPFile entry = null;
        for (int iter = 0; iter < this.rawlines.size(); ++iter) {
            final String line = this.rawlines.get(iter);
            entry = this.parseFTPEntry(line);
            if (null != entry) {
                return iter;
            }
        }
        return -2;
    }
    
    private void init() {
        this.itemptr = 0;
        this.firstGoodEntry = -1;
    }
    
    public FTPFile[] getFiles() {
        if (this.itemptr != -2) {
            this.init();
        }
        return this.getNext(0);
    }
    
    public FTPFile[] getNext(final int quantityRequested) {
        if (this.firstGoodEntry == -1) {
            this.firstGoodEntry = this.getFirstGoodEntry();
        }
        if (this.firstGoodEntry == -2) {
            return FTPFileIterator.EMPTY;
        }
        final int max = this.rawlines.size() - this.firstGoodEntry;
        int howMany = (quantityRequested == 0) ? max : quantityRequested;
        howMany = ((howMany + this.itemptr < this.rawlines.size()) ? howMany : (this.rawlines.size() - this.itemptr));
        final FTPFile[] output = new FTPFile[howMany];
        for (int i = 0, e = this.firstGoodEntry + this.itemptr; i < howMany; ++i, ++e) {
            output[i] = this.parseFTPEntry(this.rawlines.get(e));
            ++this.itemptr;
        }
        return output;
    }
    
    public boolean hasNext() {
        int fge = this.firstGoodEntry;
        if (fge == -2) {
            return false;
        }
        if (fge < 0) {
            fge = this.getFirstGoodEntry();
        }
        return fge + this.itemptr < this.rawlines.size();
    }
    
    public FTPFile next() {
        final FTPFile[] file = this.getNext(1);
        if (file.length > 0) {
            return file[0];
        }
        return null;
    }
    
    public FTPFile[] getPrevious(final int quantityRequested) {
        int howMany = quantityRequested;
        if (howMany > this.itemptr) {
            howMany = this.itemptr;
        }
        final FTPFile[] output = new FTPFile[howMany];
        for (int i = howMany, e = this.firstGoodEntry + this.itemptr; i > 0; output[--i] = this.parseFTPEntry(this.rawlines.get(--e)), --this.itemptr) {}
        return output;
    }
    
    public boolean hasPrevious() {
        int fge = this.firstGoodEntry;
        if (fge == -2) {
            return false;
        }
        if (fge < 0) {
            fge = this.getFirstGoodEntry();
        }
        return this.itemptr > fge;
    }
    
    public FTPFile previous() {
        final FTPFile[] file = this.getPrevious(1);
        if (file.length > 0) {
            return file[0];
        }
        return null;
    }
    
    static {
        EMPTY = new FTPFile[0];
    }
}
