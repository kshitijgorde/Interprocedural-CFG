// 
// Decompiled by Procyon v0.5.30
// 

package com.fsecure.launchpoint;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

class JLaunchPointXMLReader
{
    private String downloadFrom;
    private String checksum;
    private String configFile;
    private InputStream configStream;
    private static final String TAG_FROM = "binarypath";
    private static final String TAG_TO = "sha";
    
    public final void digest() {
        try {
            if (this.getConfigStream() != null) {
                SAXParserFactory.newInstance().newSAXParser().parse(this.getConfigStream(), new DefaultHandler() {
                    boolean isDownloadFromValid = false;
                    boolean isDownloadToValid = false;
                    
                    @Override
                    public void startElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
                        if (s3.equalsIgnoreCase("binarypath")) {
                            this.isDownloadFromValid = true;
                        }
                        if (s3.equalsIgnoreCase("sha")) {
                            this.isDownloadToValid = true;
                        }
                    }
                    
                    @Override
                    public void endElement(final String s, final String s2, final String s3) throws SAXException {
                    }
                    
                    @Override
                    public void characters(final char[] array, final int n, final int n2) throws SAXException {
                        if (this.isDownloadFromValid) {
                            JLaunchPointXMLReader.this.setDownloadFrom(new String(array, n, n2));
                            this.isDownloadFromValid = false;
                        }
                        if (this.isDownloadToValid) {
                            JLaunchPointXMLReader.this.setCheckSum(new String(array, n, n2));
                            this.isDownloadToValid = false;
                        }
                    }
                });
            }
        }
        catch (Exception ex) {}
    }
    
    public final void seConfigFile(final String configFile) {
        this.configFile = configFile;
    }
    
    public final String getConfigFile() {
        return this.configFile;
    }
    
    public final void setConfigStream(final InputStream configStream) {
        this.configStream = configStream;
    }
    
    public final InputStream getConfigStream() {
        return this.configStream;
    }
    
    private final void setDownloadFrom(final String downloadFrom) {
        this.downloadFrom = downloadFrom;
    }
    
    public final String getDownloadFrom() {
        return this.downloadFrom;
    }
    
    private final void setCheckSum(final String checksum) {
        this.checksum = checksum;
    }
    
    public final String getCheckSum() {
        return this.checksum;
    }
}
