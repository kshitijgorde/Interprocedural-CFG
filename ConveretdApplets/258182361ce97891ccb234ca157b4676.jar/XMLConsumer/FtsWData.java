// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

import BsscXML.IBsscXMLElementReader;
import hhapplet.Language;
import hhapplet.IChunkedDataListener;
import java.util.Vector;
import java.net.URL;
import hhapplet.IChunkedData;

public class FtsWData extends Consumer implements IChunkedData
{
    private URL m_projURL;
    private String m_sFirst;
    private String m_sLast;
    private Vector m_keys;
    private boolean m_bLoaded;
    private IChunkedDataListener m_listener;
    private boolean m_bDone;
    
    public int getNum() {
        return 0;
    }
    
    public FtsWData(final URL url, final URL projURL, final String sFirst, final String sLast) {
        super(url);
        this.m_projURL = null;
        this.m_sFirst = null;
        this.m_sLast = null;
        this.m_sFirst = sFirst;
        this.m_sLast = sLast;
        this.m_projURL = projURL;
        this.m_bLoaded = false;
        this.m_bDone = false;
        this.m_keys = new Vector();
        this.m_listener = null;
    }
    
    public Vector getTopics(final String s) {
        new Vector();
        if (this.m_keys != null && this.m_keys.size() > 0) {
            int i = 0;
            int n = this.m_keys.size() - 1;
            boolean b = false;
            FtsKeyInt ftsKeyInt;
            do {
                final int n2 = i + n >> 1;
                ftsKeyInt = this.m_keys.elementAt(n2);
                if (Language.compare(ftsKeyInt.getName(), s) > 0) {
                    n = n2 - 1;
                }
                else {
                    if (Language.compare(ftsKeyInt.getName(), s) >= 0) {
                        b = true;
                        break;
                    }
                    i = n2 + 1;
                }
            } while (i <= n);
            if (b) {
                return ftsKeyInt.getTopics();
            }
        }
        return null;
    }
    
    public void consume(final IBsscXMLElementReader bsscXMLElementReader) {
        new Vector();
        if (bsscXMLElementReader.getName().equals("ftswdata")) {
            int n = 0;
            while (true) {
                final IBsscXMLElementReader child = bsscXMLElementReader.getChild(n++);
                if (child == null) {
                    break;
                }
                if (!child.getName().equals("key")) {
                    continue;
                }
                final String attribute = child.getAttribute("name");
                if (attribute == null || attribute.length() == 0) {
                    continue;
                }
                final FtsKeyInt ftsKeyInt = new FtsKeyInt(attribute);
                this.m_keys.addElement(ftsKeyInt);
                int n2 = 0;
                while (true) {
                    final IBsscXMLElementReader child2 = child.getChild(n2++);
                    if (child2 == null) {
                        break;
                    }
                    if (!child2.getName().equals("#text")) {
                        continue;
                    }
                    final String value = child2.getValue();
                    if (value == null || value.length() <= 0) {
                        continue;
                    }
                    int i = 0;
                    do {
                        final int index = value.indexOf(",", i);
                        String s;
                        if (index != -1) {
                            s = value.substring(i, index);
                        }
                        else {
                            s = value.substring(i);
                        }
                        if (s != null) {
                            try {
                                ftsKeyInt.addTopic(Integer.parseInt(s));
                            }
                            catch (NumberFormatException ex) {}
                        }
                        i = index + 1;
                        if (i != 0) {
                            continue;
                        }
                        break;
                    } while (i < value.length());
                }
            }
            if (this.m_listener != null) {
                this.m_listener.putData(this);
            }
        }
    }
    
    public String getLast() {
        return this.m_sLast;
    }
    
    public String getFirst() {
        return this.m_sFirst;
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
    
    public void setDone(final boolean bDone) {
        this.m_bDone = bDone;
    }
}
