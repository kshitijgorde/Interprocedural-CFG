// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

import hhapplet.Language;
import java.net.MalformedURLException;
import hhapplet.URLFileHandler;
import BsscXML.IBsscXMLElementReader;
import hhapplet.IChunkedData;
import java.util.Vector;
import java.net.URL;
import hhapplet.IChunkedInfo;

public class Glossary extends Consumer implements IChunkedInfo
{
    private URL m_projURL;
    private Vector m_vChunkedGlossary;
    private int m_num;
    
    public IChunkedData getChunkByIdx(final int n) {
        IChunkedData chunkedData = null;
        if (this.m_vChunkedGlossary != null) {
            final int size = this.m_vChunkedGlossary.size();
            if (size > 0) {
                int n2 = 0;
                int i = size - 1;
                boolean b = false;
                do {
                    final int n3 = n2 + i >> 1;
                    if (n < ((GlossaryData)this.m_vChunkedGlossary.elementAt(n3)).getLastIndex()) {
                        b = true;
                        i = n3;
                    }
                    else {
                        n2 = n3 + 1;
                    }
                } while (i > n2);
                if (b) {
                    chunkedData = (GlossaryData)this.m_vChunkedGlossary.elementAt(i);
                }
                else if (n < ((GlossaryData)this.m_vChunkedGlossary.elementAt(n2)).getLastIndex()) {
                    chunkedData = (GlossaryData)this.m_vChunkedGlossary.elementAt(n2);
                }
            }
        }
        return chunkedData;
    }
    
    public Glossary(final URL url, final URL projURL) {
        super(url);
        this.m_num = 0;
        this.m_projURL = projURL;
        this.m_vChunkedGlossary = new Vector();
    }
    
    public void consume(final IBsscXMLElementReader bsscXMLElementReader) {
        if (bsscXMLElementReader.getName().equals("glossary")) {
            int n = 0;
            while (true) {
                final IBsscXMLElementReader child = bsscXMLElementReader.getChild(n++);
                if (child == null) {
                    break;
                }
                if (!child.getName().equals("chunkinfo")) {
                    continue;
                }
                final String attribute = child.getAttribute("url");
                final String attribute2 = child.getAttribute("first");
                final String attribute3 = child.getAttribute("last");
                final String attribute4 = child.getAttribute("num");
                if (attribute == null || attribute2 == null || attribute3 == null || attribute4 == null) {
                    continue;
                }
                int int1 = 0;
                try {
                    int1 = Integer.parseInt(attribute4);
                    this.m_num += int1;
                }
                catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
                try {
                    this.m_vChunkedGlossary.addElement(new GlossaryData(URLFileHandler.makeURL(this.getURL(), attribute, null), attribute2, attribute3, int1, this.m_num));
                }
                catch (MalformedURLException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }
    
    public IChunkedData getChunkedData(final boolean b, final String s) {
        IChunkedData chunkedData = null;
        if (this.m_vChunkedGlossary != null) {
            final int size = this.m_vChunkedGlossary.size();
            if (size > 0) {
                int n = 0;
                int i = size - 1;
                boolean b2 = false;
                do {
                    final int n2 = n + i + (b ? 0 : 1) >> 1;
                    final GlossaryData glossaryData = this.m_vChunkedGlossary.elementAt(n2);
                    if (glossaryData == null) {
                        return null;
                    }
                    if (b) {
                        if (Language.compare(s, glossaryData.getLast()) < 0) {
                            b2 = true;
                            i = n2;
                        }
                        else {
                            n = n2 + 1;
                        }
                    }
                    else if (Language.compare(s, glossaryData.getFirst()) > 0) {
                        b2 = true;
                        n = n2;
                    }
                    else {
                        i = n2 - 1;
                    }
                } while (i > n);
                if (b2) {
                    if (b) {
                        chunkedData = (GlossaryData)this.m_vChunkedGlossary.elementAt(i);
                    }
                    else {
                        chunkedData = (GlossaryData)this.m_vChunkedGlossary.elementAt(n);
                    }
                }
                else if (b) {
                    if (n < size && Language.compare(s, ((GlossaryData)this.m_vChunkedGlossary.elementAt(n)).getLast()) < 0) {
                        chunkedData = (GlossaryData)this.m_vChunkedGlossary.elementAt(n);
                    }
                }
                else if (i >= 0 && Language.compare(s, ((GlossaryData)this.m_vChunkedGlossary.elementAt(i)).getFirst()) > 0) {
                    chunkedData = (GlossaryData)this.m_vChunkedGlossary.elementAt(i);
                }
            }
        }
        return chunkedData;
    }
    
    public int getTotalNum() {
        return this.m_num;
    }
}
