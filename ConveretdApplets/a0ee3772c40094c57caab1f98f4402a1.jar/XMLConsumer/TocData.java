// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

import java.net.MalformedURLException;
import hhapplet.URLFileHandler;
import treeview.SiblingChildTree;
import BsscXML.IBsscXMLElementReader;
import hhapplet.IChunkedDataListener;
import java.util.Vector;
import java.net.URL;
import hhapplet.IChunkedData;

public class TocData extends Consumer implements IChunkedData
{
    private URL m_projURL;
    private Vector m_vEntries;
    private boolean m_bLoaded;
    private int m_nRootLevel;
    private IChunkedDataListener m_listener;
    
    public TocData(final URL url, final URL projURL) {
        super(url);
        this.m_projURL = null;
        this.m_vEntries = null;
        this.m_projURL = projURL;
        this.m_nRootLevel = 0;
        this.m_vEntries = new Vector();
    }
    
    public Vector getTocEntires() {
        return this.m_vEntries;
    }
    
    public void consume(final IBsscXMLElementReader bsscXMLElementReader) {
        if (bsscXMLElementReader.getName().equals("tocdata")) {
            this.processBook(bsscXMLElementReader, this.m_nRootLevel, null);
        }
        if (this.m_listener != null) {
            this.m_listener.putData(this);
        }
    }
    
    private void processBook(final IBsscXMLElementReader bsscXMLElementReader, final int n, final TocEntry tocEntry) {
        int n2 = 0;
        SiblingChildTree siblingChildTree = null;
        while (true) {
            final IBsscXMLElementReader child = bsscXMLElementReader.getChild(n2++);
            if (child == null) {
                break;
            }
            final String attribute = child.getAttribute("name");
            String attribute2 = child.getAttribute("url");
            String s = child.getAttribute("ref");
            final String attribute3 = child.getAttribute("target");
            final String attribute4 = child.getAttribute("images");
            if (attribute2 == null) {
                attribute2 = "";
            }
            if (child.getName().equals("book")) {
                final TocEntry tocEntry2 = new TocEntry(attribute, attribute2, null, this.m_vEntries.size(), 1, n, this);
                this.m_vEntries.addElement(tocEntry2);
                if (tocEntry != null) {
                    tocEntry.addChild(tocEntry2);
                }
                else if (siblingChildTree != null) {
                    siblingChildTree.addSibling(tocEntry2);
                }
                siblingChildTree = tocEntry2;
                this.processBook(child, n + 1, tocEntry2);
                if (attribute3 != null) {
                    tocEntry2.setTarget(attribute3);
                }
                if (attribute4 == null) {
                    continue;
                }
                tocEntry2.setIcon(attribute4);
            }
            else if (child.getName().equals("item")) {
                final TocEntry tocEntry3 = new TocEntry(attribute, attribute2, null, this.m_vEntries.size(), 2, n, this);
                this.m_vEntries.addElement(tocEntry3);
                if (tocEntry != null) {
                    tocEntry.addChild(tocEntry3);
                }
                else if (siblingChildTree != null) {
                    siblingChildTree.addSibling(tocEntry3);
                }
                siblingChildTree = tocEntry3;
                if (attribute3 != null) {
                    tocEntry3.setTarget(attribute3);
                }
                if (attribute4 == null) {
                    continue;
                }
                tocEntry3.setIcon(attribute4);
            }
            else if (child.getName().equals("remoteitem")) {
                final TocEntry tocEntry4 = new TocEntry(attribute, attribute2, null, this.m_vEntries.size(), 5, n, this);
                this.m_vEntries.addElement(tocEntry4);
                if (tocEntry != null) {
                    tocEntry.addChild(tocEntry4);
                }
                else if (siblingChildTree != null) {
                    siblingChildTree.addSibling(tocEntry4);
                }
                siblingChildTree = tocEntry4;
                if (attribute3 != null) {
                    tocEntry4.setTarget(attribute3);
                }
                if (attribute4 == null) {
                    continue;
                }
                tocEntry4.setIcon(attribute4);
            }
            else if (child.getName().equals("project")) {
                if (s != null && s.length() != 0 && s.lastIndexOf("/") != s.length() - 1) {
                    s += "/";
                }
                String s2 = this.m_projURL.toString();
                final int lastIndex = s2.lastIndexOf("/");
                if (lastIndex != -1 && lastIndex < s2.length()) {
                    s2 = s2.substring(lastIndex + 1);
                }
                try {
                    final TocEntry tocEntry5 = new TocEntry(attribute, attribute2, URLFileHandler.makeURL(this.m_projURL, s + s2, null).toString(), this.m_vEntries.size(), 4, n, this);
                    this.m_vEntries.addElement(tocEntry5);
                    if (tocEntry != null) {
                        tocEntry.addChild(tocEntry5);
                    }
                    else if (siblingChildTree != null) {
                        siblingChildTree.addSibling(tocEntry5);
                    }
                    siblingChildTree = tocEntry5;
                    if (attribute3 != null) {
                        tocEntry5.setTarget(attribute3);
                    }
                    if (attribute4 == null) {
                        continue;
                    }
                    tocEntry5.setIcon(attribute4);
                }
                catch (MalformedURLException ex) {
                    ex.printStackTrace();
                }
            }
            else {
                if (!child.getName().equals("chunk")) {
                    continue;
                }
                try {
                    final TocEntry tocEntry6 = new TocEntry(attribute, attribute2, URLFileHandler.makeURL(this.getURL(), s, null).toString(), this.m_vEntries.size(), 3, n, this);
                    this.m_vEntries.addElement(tocEntry6);
                    if (tocEntry != null) {
                        tocEntry.addChild(tocEntry6);
                    }
                    else if (siblingChildTree != null) {
                        siblingChildTree.addSibling(tocEntry6);
                    }
                    siblingChildTree = tocEntry6;
                    if (attribute3 != null) {
                        tocEntry6.setTarget(attribute3);
                    }
                    if (attribute4 == null) {
                        continue;
                    }
                    tocEntry6.setIcon(attribute4);
                }
                catch (MalformedURLException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }
    
    public boolean isLoaded() {
        return this.m_bLoaded;
    }
    
    public void load(final IChunkedDataListener chunkedDataListener) {
        this.load(chunkedDataListener, 0);
    }
    
    public void load(final IChunkedDataListener listener, final int nRootLevel) {
        if (!this.m_bLoaded) {
            this.m_listener = listener;
            this.m_nRootLevel = nRootLevel;
            this.process(false);
            this.m_bLoaded = true;
        }
    }
    
    public URL getProjURL() {
        return this.m_projURL;
    }
}
