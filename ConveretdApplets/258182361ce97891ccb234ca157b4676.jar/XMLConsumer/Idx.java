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

public class Idx extends Consumer implements IChunkedInfo
{
    private URL m_projURL;
    private Vector m_vChunkedIndex;
    private int m_num;
    
    public IChunkedData getChunkByIdx(final int n) {
        IChunkedData chunkedData = null;
        if (this.m_vChunkedIndex != null) {
            final int size = this.m_vChunkedIndex.size();
            if (size > 0) {
                int n2 = 0;
                int i = size - 1;
                boolean b = false;
                do {
                    final int n3 = n2 + i >> 1;
                    if (n < ((IdxData)this.m_vChunkedIndex.elementAt(n3)).getLastIndex()) {
                        b = true;
                        i = n3;
                    }
                    else {
                        n2 = n3 + 1;
                    }
                } while (i > n2);
                if (b) {
                    chunkedData = (IdxData)this.m_vChunkedIndex.elementAt(i);
                }
                else if (n < ((IdxData)this.m_vChunkedIndex.elementAt(n2)).getLastIndex()) {
                    chunkedData = (IdxData)this.m_vChunkedIndex.elementAt(n2);
                }
            }
        }
        return chunkedData;
    }
    
    public Idx(final URL url, final URL projURL) {
        super(url);
        this.m_projURL = null;
        this.m_vChunkedIndex = null;
        this.m_num = 0;
        this.m_projURL = projURL;
        this.m_vChunkedIndex = new Vector();
    }
    
    public void consume(final IBsscXMLElementReader bsscXMLElementReader) {
        if (bsscXMLElementReader.getName().equals("index")) {
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
                    this.m_vChunkedIndex.addElement(new IdxData(URLFileHandler.makeURL(this.getURL(), attribute, null), this.m_projURL, attribute2, attribute3, int1, this.m_num));
                }
                catch (MalformedURLException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }
    
    public IChunkedData getChunkedData(final boolean b, final String s) {
        if (this.m_vChunkedIndex != null) {
            final int size = this.m_vChunkedIndex.size();
            if (size > 0) {
                int n = 0;
                int i = size - 1;
                boolean b2 = false;
                do {
                    final int n2 = n + i + (b ? 0 : 1) >> 1;
                    final IdxData idxData = this.m_vChunkedIndex.elementAt(n2);
                    if (idxData == null) {
                        return null;
                    }
                    if (b) {
                        if (Language.compare(s, idxData.getLast()) < 0) {
                            b2 = true;
                            i = n2;
                        }
                        else {
                            n = n2 + 1;
                        }
                    }
                    else if (Language.compare(s, idxData.getFirst()) > 0) {
                        b2 = true;
                        n = n2;
                    }
                    else {
                        i = n2 - 1;
                    }
                } while (i > n);
                int n3;
                if (b2) {
                    if (b) {
                        n3 = i;
                    }
                    else {
                        n3 = n;
                    }
                }
                else if (b) {
                    if (n < size && Language.compare(s, ((IdxData)this.m_vChunkedIndex.elementAt(n)).getLast()) < 0) {
                        n3 = n;
                    }
                    else {
                        n3 = this.m_vChunkedIndex.size() - 1;
                    }
                }
                else if (i >= 0 && Language.compare(s, ((IdxData)this.m_vChunkedIndex.elementAt(i)).getFirst()) > 0) {
                    n3 = i;
                }
                else {
                    n3 = 0;
                }
                return (IdxData)this.m_vChunkedIndex.elementAt(n3);
            }
        }
        return null;
    }
    
    public int getTotalNum() {
        return this.m_num;
    }
}
