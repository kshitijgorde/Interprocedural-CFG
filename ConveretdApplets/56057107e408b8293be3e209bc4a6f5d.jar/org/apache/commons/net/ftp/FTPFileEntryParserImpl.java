// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.ftp;

import java.util.Iterator;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

public abstract class FTPFileEntryParserImpl implements FTPFileEntryParser, FTPFileListParser
{
    public FTPFile[] parseFileList(final InputStream listStream, final String encoding) throws IOException {
        final FTPFileList ffl = FTPFileList.create(listStream, this, encoding);
        return ffl.getFiles();
    }
    
    public FTPFile[] parseFileList(final InputStream listStream) throws IOException {
        return this.parseFileList(listStream, null);
    }
    
    public String readNextEntry(final BufferedReader reader) throws IOException {
        return reader.readLine();
    }
    
    public List preParse(final List original) {
        final Iterator it = original.iterator();
        while (it.hasNext()) {
            final String entry = it.next();
            if (null != this.parseFTPEntry(entry)) {
                break;
            }
            it.remove();
        }
        return original;
    }
}
