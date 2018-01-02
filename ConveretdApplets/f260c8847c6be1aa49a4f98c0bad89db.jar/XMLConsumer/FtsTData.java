// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

import BsscXML.IBsscXMLElementReader;
import hhapplet.IChunkedDataListener;
import java.util.Vector;
import java.net.URL;
import hhapplet.IChunkedData;

public class FtsTData extends Consumer implements IChunkedData
{
    private URL m_projURL;
    private int m_nBegin;
    private int m_nEnd;
    private Vector m_Topics;
    private boolean m_bLoaded;
    private IChunkedDataListener m_listener;
    private boolean m_bDone;
    
    public int getEnd() {
        return this.m_nEnd;
    }
    
    public int getNum() {
        return 0;
    }
    
    public FtsTData(final URL url, final URL projURL, final int nBegin, final int nEnd) {
        super(url);
        this.m_projURL = null;
        this.m_nBegin = nBegin;
        this.m_nEnd = nEnd;
        this.m_projURL = projURL;
        this.m_bLoaded = false;
        this.m_bDone = false;
        this.m_Topics = new Vector();
        this.m_listener = null;
    }
    
    public Vector getTopics() {
        return this.m_Topics;
    }
    
    public void consume(final IBsscXMLElementReader bsscXMLElementReader) {
        if (bsscXMLElementReader.getName().equals("ftstdata")) {
            int n = 0;
            while (true) {
                final IBsscXMLElementReader child = bsscXMLElementReader.getChild(n++);
                if (child == null) {
                    break;
                }
                if (!child.getName().equals("topic")) {
                    continue;
                }
                final String attribute = child.getAttribute("name");
                final String attribute2 = child.getAttribute("url");
                if (attribute == null || attribute.length() == 0 || attribute2 == null) {
                    continue;
                }
                this.m_Topics.addElement(new FtsEntry(attribute2, attribute, this));
            }
            if (this.m_listener != null) {
                this.m_listener.putData(this);
            }
        }
    }
    
    public boolean isLoaded() {
        return this.m_bLoaded;
    }
    
    public void load(final IChunkedDataListener listener) {
        if (!this.m_bLoaded) {
            this.m_listener = listener;
            this.process(false);
            this.m_bLoaded = true;
        }
    }
    
    public URL getProjURL() {
        return this.m_projURL;
    }
    
    public int getBegin() {
        return this.m_nBegin;
    }
    
    public void setDone(final boolean bDone) {
        this.m_bDone = bDone;
    }
}
