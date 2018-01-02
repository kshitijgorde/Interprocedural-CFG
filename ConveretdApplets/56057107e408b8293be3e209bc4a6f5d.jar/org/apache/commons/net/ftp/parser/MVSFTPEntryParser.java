// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.ftp.parser;

import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

public class MVSFTPEntryParser extends ConfigurableFTPFileEntryParserImpl
{
    private static final String REGEX = "(.*)\\s+([^\\s]+)\\s*";
    static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";
    
    public MVSFTPEntryParser() {
        super("(.*)\\s+([^\\s]+)\\s*");
    }
    
    public FTPFile parseFTPEntry(final String entry) {
        FTPFile f = null;
        if (this.matches(entry)) {
            f = new FTPFile();
            final String dataSetName = this.group(2);
            f.setType(0);
            f.setName(dataSetName);
            return f;
        }
        return null;
    }
    
    protected FTPClientConfig getDefaultConfiguration() {
        return new FTPClientConfig("MVS", "yyyy/MM/dd", null, null, null, null);
    }
}
