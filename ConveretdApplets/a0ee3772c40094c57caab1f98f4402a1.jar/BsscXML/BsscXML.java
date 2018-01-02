// 
// Decompiled by Procyon v0.5.30
// 

package BsscXML;

import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.zip.ZipInputStream;
import java.net.MalformedURLException;
import hhapplet.URLFileHandler;
import java.net.URL;

public class BsscXML
{
    public static URL m_url;
    
    public static IBsscXMLElementBuilder createElement(final String s) {
        return new BsscXMLElement(s);
    }
    
    public static URL getDocumentBase() {
        return BsscXML.m_url;
    }
    
    public static void setDocumentBase(final URL url) {
        BsscXML.m_url = url;
    }
    
    static {
        BsscXML.m_url = null;
    }
    
    public static boolean isSameURL(final String s, final String s2) {
        URL url;
        URL url2;
        try {
            url = URLFileHandler.makeURL(BsscXML.m_url, s, null);
            url2 = URLFileHandler.makeURL(BsscXML.m_url, s2, null);
        }
        catch (MalformedURLException ex) {
            return false;
        }
        return url.equals(url2);
    }
    
    public static void parseXML(final IBsscXMLConsumer bsscXMLConsumer, final URL url, final boolean b) {
        try {
            InputStream openStream;
            if (url.toString().endsWith(".zip")) {
                openStream = new ZipInputStream(url.openStream());
            }
            else {
                openStream = url.openStream();
            }
            final BufferedReader source = new BufferedReader(new InputStreamReader(openStream));
            final BsscXMLParser bsscXMLParser = new BsscXMLParser(bsscXMLConsumer);
            bsscXMLParser.setSource(source);
            if (b) {
                bsscXMLParser.parse();
                return;
            }
            new Thread(bsscXMLParser).start();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
