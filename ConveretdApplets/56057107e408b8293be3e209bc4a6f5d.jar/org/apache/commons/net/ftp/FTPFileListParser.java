// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.ftp;

import java.io.IOException;
import java.io.InputStream;

public interface FTPFileListParser
{
    FTPFile[] parseFileList(final InputStream p0, final String p1) throws IOException;
    
    FTPFile[] parseFileList(final InputStream p0) throws IOException;
}
