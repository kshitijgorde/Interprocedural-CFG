// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

import hhapplet.IChunkedData;
import BsscXML.IBsscXMLElementReader;
import java.net.URL;
import hhapplet.Language;
import hhapplet.UsedItems;
import hhapplet.IChunkedDataListener;
import hhapplet.INumChunkedData;

public class GlossaryData extends Consumer implements INumChunkedData
{
    private String m_sFirst;
    private String m_sLast;
    private int m_num;
    private int m_index;
    private int m_lastIndex;
    private GlossaryEntry[] m_keys;
    private boolean m_bLoaded;
    private IChunkedDataListener m_listener;
    private UsedItems m_usedItems;
    private boolean m_bDone;
    
    public int getKeyPosition(final boolean b, final String s) {
        int num;
        if (b) {
            num = this.m_num;
        }
        else {
            num = -1;
        }
        if (this.m_keys != null && this.m_keys.length > 0) {
            GlossaryEntry glossaryEntry;
            for (int length = this.m_keys.length, i = 0; i < length; i += glossaryEntry.getNextSpan(), ++i) {
                glossaryEntry = this.m_keys[i];
                if (b) {
                    if (Language.compare(glossaryEntry.getName(), s) > 0) {
                        num = i;
                        break;
                    }
                }
                else {
                    if (Language.compare(glossaryEntry.getName(), s) >= 0) {
                        break;
                    }
                    num = i;
                }
            }
        }
        return num;
    }
    
    public String getKeyByPosition(final int n) {
        final int n2 = n - (this.m_lastIndex - this.m_num);
        if (n2 >= 0 && n2 < this.m_keys.length) {
            final GlossaryEntry glossaryEntry = this.m_keys[n2];
            if (glossaryEntry != null) {
                return glossaryEntry.getName();
            }
        }
        return null;
    }
    
    public int getNum() {
        return this.m_num;
    }
    
    public GlossaryData(final URL url, final String sFirst, final String sLast, final int num, final int lastIndex) {
        super(url);
        this.m_sFirst = null;
        this.m_sLast = null;
        this.m_num = 0;
        this.m_index = 0;
        this.m_lastIndex = 0;
        this.m_sFirst = sFirst;
        this.m_sLast = sLast;
        this.m_num = num;
        this.m_lastIndex = lastIndex;
        this.m_bLoaded = false;
        this.m_bDone = false;
        this.m_keys = new GlossaryEntry[num];
        this.m_listener = null;
        this.m_usedItems = new UsedItems();
    }
    
    public IEntry getEntry(final int n) {
        return this.m_keys[n];
    }
    
    public String getLast() {
        return this.m_sLast;
    }
    
    public void consume(final IBsscXMLElementReader bsscXMLElementReader) {
        if (bsscXMLElementReader.getName().equals("glossarydata")) {
            int n = 0;
            int n2 = 0;
            while (true) {
                final IBsscXMLElementReader child = bsscXMLElementReader.getChild(n++);
                if (child == null) {
                    break;
                }
                if (!child.getName().equals("entry")) {
                    continue;
                }
                final String attribute = child.getAttribute("name");
                final String attribute2 = child.getAttribute("value");
                if (attribute == null || attribute.length() == 0) {
                    continue;
                }
                this.m_keys[n2++] = new GlossaryEntry(attribute, attribute2);
            }
            if (this.m_listener != null) {
                this.m_listener.putData(this);
            }
        }
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
    
    public UsedItems getUsedItems() {
        return this.m_usedItems;
    }
    
    public int getLastIndex() {
        return this.m_lastIndex;
    }
    
    public void setDone(final boolean bDone) {
        this.m_bDone = bDone;
    }
}
