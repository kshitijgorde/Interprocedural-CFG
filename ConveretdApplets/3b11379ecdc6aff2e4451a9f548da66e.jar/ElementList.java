import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class ElementList extends Vector implements Runnable
{
    static int FORMAT_LST;
    static int FORMAT_TOC;
    static int MODE_RELATIVE;
    static int MODE_ABSOLUTE;
    Thread thread;
    HHCtrl m_applet;
    int m_numVisible;
    int m_listFormat;
    int nAuto;
    Image img0;
    Image img1;
    Image img2;
    Image img3;
    Image pageImg;
    int m_autoExpandLevel;
    static int lastNth;
    static int lastI;
    
    public void stop() {
    }
    
    public ElementList(final HHCtrl applet) {
        this.m_autoExpandLevel = 2;
        this.m_applet = applet;
    }
    
    private void showElement(final int n) {
        for (int i = this.getParent(n); i >= 0; i = this.getParent(i)) {
            this.showChildren(i, false);
        }
    }
    
    private void autoLoad(final int n) {
        URL url = null;
        final Element element = this.elementAt(n);
        element.m_text = this.m_applet.getString("cnt.merge.elementname");
        final String url2 = element.m_url;
        final int level = element.m_level;
        if (element.m_url.compareTo("") != 0) {
            try {
                url = new URL(url2);
            }
            catch (MalformedURLException ex) {
                try {
                    url = new URL(this.m_applet.getDocumentBase(), url2);
                }
                catch (MalformedURLException ex2) {
                    element.m_text = this.m_applet.getString("cnt.merge.errelement");
                    return;
                }
            }
            this.m_applet.showStatus(this.m_applet.getString("cnt.load") + ": " + url2);
        }
        else {
            element.m_text = this.m_applet.getString("cnt.merge.errelement");
        }
        if (url == null) {
            return;
        }
        this.m_applet.m_tview.m_canvas.setLock();
        int i;
        try {
            final ElementList list = new ElementList(this.m_applet);
            list.setAutoExpandLevel(1);
            if (!new SitemapParser(url, list, this.m_applet).success()) {
                return;
            }
            for (i = 0; i < list.size(); ++i) {
                final Element element2 = list.elementAt(i);
                element2.m_level += level;
                this.insertElementAt(list.elementAt(i), n + i);
            }
        }
        catch (IOException ex3) {
            this.m_applet.showStatus(this.m_applet.getString("cnt.merge.err"));
            this.m_applet.m_tview.m_canvas.clearLock();
            return;
        }
        this.m_applet.showStatus(this.m_applet.getString("cnt.merge.success"));
        this.removeElementAt(n + i);
        --this.m_numVisible;
        this.showChildren(n - 1);
        this.calcNumVisible();
        final TreeCanvas canvas = this.m_applet.m_tview.m_canvas;
        if (canvas.m_selectedItemA > n) {
            final TreeCanvas treeCanvas = canvas;
            treeCanvas.m_selectedItemA += i - 1;
            canvas.m_selectedItem = this.whichVisible(canvas.m_selectedItemA) - 1;
        }
        canvas.calcMaxWidth();
        final TreeView tview = this.m_applet.m_tview;
        tview.setVScroll();
        if (tview.setHScroll()) {
            tview.setVScroll();
        }
        canvas.repaint();
        canvas.clearLock();
    }
    
    private int getParent(final int n) {
        int n2 = -1;
        final int level = this.elementAt(n).m_level;
        if (n == 0) {
            return -1;
        }
        for (int i = n - 1; i >= 0; --i) {
            if (((Element)this.elementAt(i)).m_level < level) {
                n2 = i;
                break;
            }
        }
        return n2;
    }
    
    public int countChildren(final int n) {
        int n2 = n + 1;
        int n3 = 0;
        if (n == this.size() - 1) {
            return 0;
        }
        while (n2 < this.size() && ((Element)this.elementAt(n2)).m_level > this.elementAt(n).m_level) {
            if (((Element)this.elementAt(n2)).isVisible() && ((Element)this.elementAt(n2)).m_level == this.elementAt(n).m_level + 1) {
                ++n3;
            }
            ++n2;
        }
        return n3;
    }
    
    public int numVisible() {
        return this.m_numVisible;
    }
    
    public Element showNext(final int n) {
        final Element element = this.elementAt(n + 1);
        if (!element.isVisible()) {
            this.showChildren(n, false);
        }
        return element;
    }
    
    public int whichVisible(final int n) {
        int i = 0;
        int n2 = 0;
        if (n > this.size()) {
            return 0;
        }
        while (i <= n) {
            if (((Element)this.elementAt(i)).isVisible()) {
                ++n2;
            }
            ++i;
        }
        return n2;
    }
    
    public void showChildren(final int n) {
        this.showChildren(n, true);
    }
    
    public synchronized boolean showChildren(final int n, final boolean b) {
        boolean b2 = false;
        Element element;
        for (int level = this.elementAt(n).m_level, nAuto = n + 1; nAuto < this.size() && (element = this.elementAt(nAuto)).m_level > level; ++nAuto) {
            if (element.m_level == level + 1) {
                element.show();
                ++this.m_numVisible;
            }
            if (element.m_merge && element.m_level == level + 1) {
                if (b) {
                    this.nAuto = nAuto;
                    (this.thread = new Thread(this)).start();
                }
                else {
                    this.autoLoad(nAuto);
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public Element showPrev(final int n) {
        final Element element = this.elementAt(n - 1);
        final int size = this.size();
        if (!element.isVisible()) {
            this.showElement(n - 1);
            if (size < this.size()) {
                this.showElement(n - 1);
            }
        }
        return element;
    }
    
    public boolean hasVisibleChildren(final int n) {
        return n != this.size() - 1 && (this.elementAt(n).m_level < this.elementAt(n + 1).m_level && this.elementAt(n + 1).isVisible());
    }
    
    public boolean moreVisibleAtLevel(final int n, final int n2) {
        for (int n3 = n2 + 1; n3 < this.size() && ((Element)this.elementAt(n3)).m_level >= n; ++n3) {
            if (((Element)this.elementAt(n3)).isVisible() && ((Element)this.elementAt(n3)).m_level == n) {
                return true;
            }
        }
        return false;
    }
    
    static {
        ElementList.FORMAT_TOC = 1;
        ElementList.MODE_ABSOLUTE = 1;
    }
    
    public void start() {
    }
    
    public int showTitle(final String s) {
        int n = -1;
        for (int i = 0; i < this.size(); ++i) {
            if (((Element)this.elementAt(i)).m_text.equalsIgnoreCase(s)) {
                this.showElement(i);
                n = i;
                break;
            }
        }
        return n;
    }
    
    public int nthVisible(final int n) {
        int lastNth = 0;
        int i = 0;
        if (n + 1 == ElementList.lastNth) {
            return ElementList.lastI;
        }
        if (n > this.size()) {
            return this.size();
        }
        if (n > ElementList.lastNth) {
            i = ElementList.lastI + 1;
            lastNth = ElementList.lastNth;
        }
        while (i < this.size()) {
            if (((Element)this.elementAt(i)).isVisible()) {
                ++lastNth;
            }
            if (n + 1 == lastNth) {
                break;
            }
            ++i;
        }
        ElementList.lastI = i;
        ElementList.lastNth = lastNth;
        return i;
    }
    
    public int showURL(final String s) {
        int n = -1;
        for (int i = 0; i < this.size(); ++i) {
            if (((Element)this.elementAt(i)).m_url.equalsIgnoreCase(s)) {
                this.showElement(i);
                n = i;
                break;
            }
        }
        return n;
    }
    
    public void run() {
        this.autoLoad(this.nAuto);
    }
    
    public void hideChildren(final int n) {
        for (int n2 = n + 1; n2 < this.size() && ((Element)this.elementAt(n2)).m_level >= this.elementAt(n + 1).m_level; ++n2) {
            final Element element = this.elementAt(n2);
            if (element.isVisible()) {
                --this.m_numVisible;
            }
            element.hide();
        }
    }
    
    public boolean hasChildren(final int n) {
        return n != this.size() - 1 && this.elementAt(n).m_level < this.elementAt(n + 1).m_level;
    }
    
    void setAutoExpandLevel(final int autoExpandLevel) {
        if (autoExpandLevel > 0) {
            this.m_autoExpandLevel = autoExpandLevel;
        }
    }
    
    public void calcNumVisible() {
        int numVisible = 0;
        for (int i = 0; i < this.size(); ++i) {
            if (((Element)this.elementAt(i)).isVisible()) {
                ++numVisible;
            }
        }
        this.m_numVisible = numVisible;
    }
}
