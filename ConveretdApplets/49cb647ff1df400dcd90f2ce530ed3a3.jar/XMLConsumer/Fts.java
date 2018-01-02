// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

import hhapplet.Language;
import java.net.MalformedURLException;
import hhapplet.URLFileHandler;
import BsscXML.IBsscXMLElementReader;
import java.util.Vector;
import java.net.URL;

public class Fts extends Consumer
{
    private URL m_projURL;
    private Vector m_vChunkedFts;
    private Vector m_vChunkedTopicFts;
    
    public FtsTData getChunkedTopicData(final int n) {
        final int size = this.m_vChunkedTopicFts.size();
        if (size > 0) {
            int n2 = 0;
            int i = size - 1;
            boolean b = false;
            FtsTData ftsTData;
            do {
                final int n3 = n2 + i >> 1;
                ftsTData = this.m_vChunkedTopicFts.elementAt(n3);
                if (ftsTData.getBegin() > n) {
                    i = n3 - 1;
                }
                else {
                    if (ftsTData.getEnd() >= n) {
                        b = true;
                        break;
                    }
                    n2 = n3 + 1;
                }
            } while (i >= n2);
            if (b) {
                return ftsTData;
            }
        }
        return null;
    }
    
    public Fts(final URL url, final URL projURL) {
        super(url);
        this.m_projURL = projURL;
        this.m_vChunkedFts = new Vector();
        this.m_vChunkedTopicFts = new Vector();
    }
    
    public void consume(final IBsscXMLElementReader bsscXMLElementReader) {
        if (bsscXMLElementReader.getName().equals("fts")) {
            int n = 0;
            while (true) {
                final IBsscXMLElementReader child = bsscXMLElementReader.getChild(n++);
                if (child == null) {
                    break;
                }
                if (child.getName().equals("chunkinfo")) {
                    final String attribute = child.getAttribute("url");
                    final String attribute2 = child.getAttribute("first");
                    final String attribute3 = child.getAttribute("last");
                    if (attribute == null || attribute2 == null || attribute3 == null) {
                        continue;
                    }
                    try {
                        this.m_vChunkedFts.addElement(new FtsWData(URLFileHandler.makeURL(this.getURL(), attribute, null), this.m_projURL, attribute2, attribute3));
                    }
                    catch (MalformedURLException ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    if (!child.getName().equals("tchunkinfo")) {
                        continue;
                    }
                    final String attribute4 = child.getAttribute("url");
                    final String attribute5 = child.getAttribute("first");
                    final String attribute6 = child.getAttribute("last");
                    if (attribute4 == null || attribute5 == null || attribute6 == null) {
                        continue;
                    }
                    try {
                        this.m_vChunkedTopicFts.addElement(new FtsTData(URLFileHandler.makeURL(this.getURL(), attribute4, null), this.m_projURL, Integer.parseInt(attribute5), Integer.parseInt(attribute6)));
                    }
                    catch (MalformedURLException ex2) {
                        ex2.printStackTrace();
                    }
                    catch (NumberFormatException ex3) {
                        ex3.printStackTrace();
                    }
                }
            }
        }
    }
    
    public FtsWData getChunkedData(final String s) {
        final int size = this.m_vChunkedFts.size();
        if (size > 0) {
            int n = 0;
            int i = size - 1;
            boolean b = false;
            FtsWData ftsWData;
            do {
                final int n2 = n + i >> 1;
                ftsWData = this.m_vChunkedFts.elementAt(n2);
                if (Language.compare(s, ftsWData.getLast()) > 0) {
                    n = n2 + 1;
                }
                else {
                    if (Language.compare(s, ftsWData.getFirst()) >= 0) {
                        b = true;
                        break;
                    }
                    i = n2 - 1;
                }
            } while (i >= n);
            if (b) {
                return ftsWData;
            }
        }
        return null;
    }
}
