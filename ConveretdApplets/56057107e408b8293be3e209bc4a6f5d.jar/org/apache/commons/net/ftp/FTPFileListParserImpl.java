// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.ftp;

import org.apache.commons.net.ftp.parser.RegexFTPFileEntryParserImpl;

public abstract class FTPFileListParserImpl extends RegexFTPFileEntryParserImpl
{
    public FTPFileListParserImpl(final String regex) {
        super(regex);
    }
}
