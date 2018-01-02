// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.ftp;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;
import java.io.InputStream;
import java.util.LinkedList;

public class FTPFileList
{
    private LinkedList lines;
    private FTPFileEntryParser parser;
    private static final int EMPTY_DIR = -2;
    
    private FTPFileList(final FTPFileEntryParser parser, final String encoding) {
        this.lines = null;
        this.parser = parser;
        this.lines = new LinkedList();
    }
    
    public static FTPFileList create(final InputStream stream, final FTPFileEntryParser parser, final String encoding) throws IOException {
        final FTPFileList list = new FTPFileList(parser, encoding);
        list.readStream(stream, encoding);
        parser.preParse(list.lines);
        return list;
    }
    
    public static FTPFileList create(final InputStream stream, final FTPFileEntryParser parser) throws IOException {
        return create(stream, parser, null);
    }
    
    public void readStream(final InputStream stream, final String encoding) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(stream, encoding));
        for (String line = this.parser.readNextEntry(reader); line != null; line = this.parser.readNextEntry(reader)) {
            this.lines.add(line);
        }
        reader.close();
    }
    
    public void readStream(final InputStream stream) throws IOException {
        this.readStream(stream, null);
    }
    
    FTPFileEntryParser getParser() {
        return this.parser;
    }
    
    List getLines() {
        return this.lines;
    }
    
    public FTPFileIterator iterator() {
        return new FTPFileIterator(this);
    }
    
    public FTPFileIterator iterator(final FTPFileEntryParser parser) {
        return new FTPFileIterator(this, parser);
    }
    
    public FTPFile[] getFiles() {
        return this.iterator().getFiles();
    }
}
