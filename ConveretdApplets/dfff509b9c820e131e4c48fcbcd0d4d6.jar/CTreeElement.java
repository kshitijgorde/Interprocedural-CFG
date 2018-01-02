import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class CTreeElement
{
    public String m_label;
    public String m_iconLink;
    public String m_contentLink;
    public String m_nodeLink;
    public String m_nodeTarget;
    public int m_level;
    public boolean m_isExpanded;
    public Image m_imgIcon;
    protected CTreeElement m_nextElem;
    
    public CTreeElement() {
    }
    
    public CTreeElement(final String label, final String iconLink, final String contentLink, final String nodeLink, final int level, final CTreeElement nextElem, final String nodeTarget) {
        this.m_label = label;
        this.m_iconLink = iconLink;
        this.m_contentLink = contentLink;
        this.m_nodeLink = nodeLink;
        this.m_level = level;
        this.m_nodeTarget = nodeTarget;
        if (nextElem != this) {
            this.m_nextElem = nextElem;
        }
    }
    
    public void setNext(final CTreeElement nextElem) {
        if (nextElem != this) {
            this.m_nextElem = nextElem;
        }
    }
    
    public CTreeElement getNext() {
        return this.m_nextElem;
    }
    
    int getNumberTrailers() {
        int n = 0;
        CTreeElement next = this;
        while ((next = next.getNext()) != null) {
            ++n;
        }
        return n;
    }
    
    int getNumberInnerElements() {
        int n = 0;
        CTreeElement next = this;
        final int level = next.m_level;
        while ((next = next.getNext()) != null && next.m_level > level) {
            ++n;
        }
        return n;
    }
    
    CTreeElement getTail() {
        if (this.m_nextElem == null) {
            return this;
        }
        CTreeElement next;
        for (next = this; next.getNext() != null; next = next.getNext()) {}
        return next;
    }
}
