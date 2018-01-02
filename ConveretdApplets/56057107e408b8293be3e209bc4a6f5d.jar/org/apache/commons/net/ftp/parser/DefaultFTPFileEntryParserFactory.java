// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.ftp.parser;

import org.apache.commons.net.ftp.Configurable;
import org.apache.commons.net.ftp.FTPFileEntryParser;
import org.apache.commons.net.ftp.FTPClientConfig;

public class DefaultFTPFileEntryParserFactory implements FTPFileEntryParserFactory
{
    private FTPClientConfig config;
    
    public DefaultFTPFileEntryParserFactory() {
        this.config = null;
    }
    
    public FTPFileEntryParser createFileEntryParser(final String key) {
        Class parserClass = null;
        FTPFileEntryParser parser = null;
        try {
            parserClass = Class.forName(key);
            parser = parserClass.newInstance();
        }
        catch (ClassNotFoundException e3) {
            String ukey = null;
            if (null != key) {
                ukey = key.toUpperCase();
            }
            if (ukey.indexOf("UNIX") >= 0) {
                parser = this.createUnixFTPEntryParser();
            }
            else if (ukey.indexOf("VMS") >= 0) {
                parser = this.createVMSVersioningFTPEntryParser();
            }
            else if (ukey.indexOf("WINDOWS") >= 0) {
                parser = this.createNTFTPEntryParser();
            }
            else if (ukey.indexOf("OS/2") >= 0) {
                parser = this.createOS2FTPEntryParser();
            }
            else if (ukey.indexOf("OS/400") >= 0) {
                parser = this.createOS400FTPEntryParser();
            }
            else {
                if (ukey.indexOf("MVS") < 0) {
                    throw new ParserInitializationException("Unknown parser type: " + key);
                }
                parser = this.createMVSEntryParser();
            }
        }
        catch (ClassCastException e) {
            throw new ParserInitializationException(parserClass.getName() + " does not implement the interface " + "org.apache.commons.net.ftp.FTPFileEntryParser.", e);
        }
        catch (Throwable e2) {
            throw new ParserInitializationException("Error initializing parser", e2);
        }
        if (parser instanceof Configurable) {
            ((Configurable)parser).configure(this.config);
        }
        return parser;
    }
    
    public FTPFileEntryParser createFileEntryParser(final FTPClientConfig config) throws ParserInitializationException {
        this.config = config;
        final String key = config.getServerSystemKey();
        return this.createFileEntryParser(key);
    }
    
    public FTPFileEntryParser createUnixFTPEntryParser() {
        return new UnixFTPEntryParser();
    }
    
    public FTPFileEntryParser createVMSVersioningFTPEntryParser() {
        return new VMSVersioningFTPEntryParser();
    }
    
    public FTPFileEntryParser createNTFTPEntryParser() {
        if (this.config != null && "WINDOWS".equals(this.config.getServerSystemKey())) {
            return new NTFTPEntryParser();
        }
        return new CompositeFileEntryParser(new FTPFileEntryParser[] { new NTFTPEntryParser(), new UnixFTPEntryParser() });
    }
    
    public FTPFileEntryParser createOS2FTPEntryParser() {
        return new OS2FTPEntryParser();
    }
    
    public FTPFileEntryParser createOS400FTPEntryParser() {
        if (this.config != null && "OS/400".equals(this.config.getServerSystemKey())) {
            return new OS400FTPEntryParser();
        }
        return new CompositeFileEntryParser(new FTPFileEntryParser[] { new OS400FTPEntryParser(), new UnixFTPEntryParser() });
    }
    
    public FTPFileEntryParser createMVSEntryParser() {
        return new MVSFTPEntryParser();
    }
}
