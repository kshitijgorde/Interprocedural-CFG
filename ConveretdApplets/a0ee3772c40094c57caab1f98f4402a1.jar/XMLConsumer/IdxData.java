// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

import hhapplet.IChunkedData;
import BsscXML.IBsscXMLElementReader;
import hhapplet.Language;
import hhapplet.UsedItems;
import hhapplet.IChunkedDataListener;
import java.net.URL;
import hhapplet.INumChunkedData;

public class IdxData extends Consumer implements INumChunkedData
{
    private URL m_projURL;
    private String m_sFirst;
    private String m_sLast;
    private int m_num;
    private int m_index;
    private int m_lastIndex;
    private IdxEntry[] m_keys;
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
            IdxEntry idxEntry;
            for (int length = this.m_keys.length, i = 0; i < length; i += idxEntry.getNextSpan(), ++i) {
                idxEntry = this.m_keys[i];
                if (b) {
                    if (Language.compare(idxEntry.getName(), s) > 0) {
                        num = i;
                        break;
                    }
                }
                else {
                    if (Language.compare(idxEntry.getName(), s) >= 0) {
                        break;
                    }
                    num = i;
                }
            }
        }
        return num;
    }
    
    public String getKeyByPosition(final int n) {
        int n2 = n - (this.m_lastIndex - this.m_num);
        if (n2 >= 0 && n2 < this.m_keys.length) {
            IdxEntry idxEntry;
            do {
                idxEntry = this.m_keys[n2++];
            } while ((idxEntry.getType() == 3 || this.m_usedItems.isUsed(n2 - 1)) && n2 < this.m_keys.length);
            if (idxEntry.getType() != 3) {
                return idxEntry.getName();
            }
            int n3 = n - (this.m_lastIndex - this.m_num) - 1;
            if (n3 >= 0) {
                do {
                    idxEntry = this.m_keys[n3--];
                } while ((idxEntry.getType() == 3 || this.m_usedItems.isUsed(n3 + 1)) && n3 >= 0);
            }
            if (idxEntry.getType() != 3) {
                return idxEntry.getName();
            }
        }
        return null;
    }
    
    public int getNum() {
        return this.m_num;
    }
    
    public IdxData(final URL url, final URL projURL, final String sFirst, final String sLast, final int num, final int lastIndex) {
        super(url);
        this.m_projURL = null;
        this.m_sFirst = null;
        this.m_sLast = null;
        this.m_num = 0;
        this.m_index = 0;
        this.m_lastIndex = 0;
        this.m_sFirst = sFirst;
        this.m_sLast = sLast;
        this.m_num = num;
        this.m_projURL = projURL;
        this.m_lastIndex = lastIndex;
        this.m_bLoaded = false;
        this.m_bDone = false;
        this.m_keys = new IdxEntry[num];
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
        if (bsscXMLElementReader.getName().equals("indexdata")) {
            int n = 0;
            int nextSpan = 0;
            while (true) {
                final int n2 = nextSpan;
                final IBsscXMLElementReader child = bsscXMLElementReader.getChild(n++);
                if (child == null) {
                    break;
                }
                if (child.getName().equals("key")) {
                    final String attribute = child.getAttribute("name");
                    if (attribute == null || attribute.length() == 0) {
                        continue;
                    }
                    final IdxEntry idxEntry = new IdxEntry(attribute, n2, 2, 1, this);
                    final String attribute2 = child.getAttribute("target");
                    if (attribute2 != null) {
                        idxEntry.setTarget(attribute2);
                    }
                    this.m_keys[this.m_index++] = idxEntry;
                    final int index = this.m_index;
                    this.processKey(child, idxEntry, 2);
                    nextSpan = this.m_index - index;
                    idxEntry.setNextSpan(nextSpan);
                }
                else {
                    if (!child.getName().equals("letter")) {
                        continue;
                    }
                    final String attribute3 = child.getAttribute("name");
                    if (attribute3 == null || attribute3.length() == 0) {
                        continue;
                    }
                    this.m_keys[this.m_index++] = new IdxEntry(attribute3, n2, 1, 1, this);
                    nextSpan = 0;
                }
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
    
    public URL getProjURL() {
        return this.m_projURL;
    }
    
    public void setDone(final boolean bDone) {
        this.m_bDone = bDone;
    }
    
    private void processKey(final IBsscXMLElementReader bsscXMLElementReader, final IdxEntry idxEntry, final int n) {
        int n2 = 0;
        int nextSpan = 0;
        while (true) {
            final IBsscXMLElementReader child = bsscXMLElementReader.getChild(n2++);
            if (child == null) {
                break;
            }
            if (child.getName().equals("topic")) {
                String attribute = child.getAttribute("name");
                final String attribute2 = child.getAttribute("url");
                if (attribute2 == null || attribute2.length() == 0) {
                    continue;
                }
                if (attribute == null || attribute.length() == 0) {
                    attribute = attribute2;
                }
                idxEntry.addTopic(new Topic(attribute2, attribute));
            }
            else {
                if (!child.getName().equals("key")) {
                    continue;
                }
                final int n3 = nextSpan;
                final String attribute3 = child.getAttribute("name");
                if (attribute3 == null || attribute3.length() == 0) {
                    continue;
                }
                final IdxEntry idxEntry2 = new IdxEntry(attribute3, n3, 3, n, this);
                this.m_keys[this.m_index++] = idxEntry2;
                final int index = this.m_index;
                this.processKey(child, idxEntry2, n + 1);
                nextSpan = this.m_index - index;
                idxEntry2.setNextSpan(nextSpan);
            }
        }
    }
}
