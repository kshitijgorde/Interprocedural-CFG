import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class CTreeElementList
{
    protected CTreeElement m_firstElem;
    protected int m_size;
    
    CTreeElementList insertElementList(final int n, final CTreeElementList list) {
        return null;
    }
    
    CTreeElement getElement(final int n) {
        if (n < 0) {
            return null;
        }
        CTreeElement cTreeElement = this.m_firstElem;
        for (int i = 0; i < n; ++i) {
            cTreeElement = cTreeElement.getNext();
        }
        return cTreeElement;
    }
    
    CTreeElement removeElement(final int n, final int n2) {
        if (this.m_size == 0) {
            return this.m_firstElem;
        }
        final CTreeElement element = this.getElement(n - 1);
        final CTreeElement element2 = this.getElement(n);
        final CTreeElement element3 = this.getElement(n + n2);
        if (element == null) {
            this.m_firstElem = element3;
        }
        else {
            element.setNext(element3);
        }
        this.m_size -= n2;
        return element2;
    }
    
    int regionClicked(final int n, final int n2, final int n3, final int n4) {
        final CTreeElement element = this.getElement(n);
        if (element == null) {
            return 0;
        }
        if (n2 < element.m_level * n3) {
            return 0;
        }
        if (n2 < element.m_level * n3 + n4) {
            return 1;
        }
        return 2;
    }
    
    CTreeElement first() {
        return this.m_firstElem;
    }
    
    void fetchDocument(final int n, final Applet applet) {
        final CTreeElement element = this.getElement(n);
        if (element == null || element.m_contentLink == null || element.m_contentLink.compareTo("none") == 0) {
            return;
        }
        String s = element.m_nodeTarget;
        if (s == null || s.compareTo("none") == 0) {
            s = applet.getParameter("TargetWindow");
        }
        URL url = null;
        try {
            if (element.m_contentLink.startsWith(new String("."))) {
                if (applet.getParameter("ServerURL") != null) {
                    url = new URL(applet.getParameter("ServerURL") + element.m_contentLink);
                }
                else {
                    url = new URL(applet.getCodeBase() + element.m_contentLink);
                }
            }
            else {
                url = new URL(element.m_contentLink);
            }
        }
        catch (MalformedURLException ex) {
            applet.getAppletContext().showStatus("Bad url: " + element.m_contentLink);
        }
        applet.getAppletContext().showDocument(url, s);
    }
    
    public int getSize() {
        return this.m_size;
    }
    
    CTreeElement insertElement(final int n, final CTreeElement cTreeElement) {
        if (cTreeElement == null) {
            return cTreeElement;
        }
        if (this.m_size == 0) {
            this.m_firstElem = cTreeElement;
            ++this.m_size;
            this.m_size += this.m_firstElem.getNumberTrailers();
            return cTreeElement;
        }
        if (n == 0) {
            final CTreeElement tail = cTreeElement.getTail();
            if (tail == null) {
                cTreeElement.setNext(this.m_firstElem);
            }
            else {
                tail.setNext(this.m_firstElem);
            }
            this.m_firstElem = cTreeElement;
            this.m_size = this.m_firstElem.getNumberTrailers() + 1;
            return cTreeElement;
        }
        if (n == this.m_size) {
            final CTreeElement tail2 = this.m_firstElem.getTail();
            tail2.setNext(cTreeElement);
            this.m_size += tail2.getNumberTrailers();
            return cTreeElement;
        }
        final CTreeElement element = this.getElement(n - 1);
        if (element == null) {
            return element;
        }
        final CTreeElement tail3 = cTreeElement.getTail();
        final int numberTrailers = cTreeElement.getNumberTrailers();
        tail3.setNext(element.getNext());
        element.setNext(cTreeElement);
        this.m_size += numberTrailers + 1;
        return cTreeElement;
    }
}
