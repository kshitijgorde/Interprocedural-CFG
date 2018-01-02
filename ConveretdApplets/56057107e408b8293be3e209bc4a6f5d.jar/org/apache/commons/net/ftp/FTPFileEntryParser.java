// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.ftp;

import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;

public interface FTPFileEntryParser
{
    FTPFile parseFTPEntry(final String p0);
    
    String readNextEntry(final BufferedReader p0) throws IOException;
    
    List preParse(final List p0);
}
