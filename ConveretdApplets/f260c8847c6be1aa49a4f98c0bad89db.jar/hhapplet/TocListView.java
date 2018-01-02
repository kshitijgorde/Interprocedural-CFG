// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.awt.FontMetrics;
import java.awt.Font;
import XMLConsumer.IEntry;
import java.net.URL;
import XMLConsumer.Toc;
import java.net.MalformedURLException;
import XMLConsumer.Project;
import BsscXML.BsscXML;
import java.util.Enumeration;
import java.awt.Image;
import java.awt.Graphics;
import XMLConsumer.TocData;
import java.awt.Color;
import XMLConsumer.TocEntry;
import java.util.Vector;

public class TocListView extends ListView implements IChunkedDataListener
{
    private Vector m_chunkedInfos;
    private Vector m_vTocBlocks;
    private TocEntry m_currentEntry;
    private Vector m_vProjects;
    private static String m_sLoadingMsg;
    private static final Color YELLOW;
    private String m_sCurrentTocPath;
    private String m_sSyncPath;
    private String m_sBackSyncPath;
    private static String m_sTocPathSplit;
    private boolean m_bSync;
    private boolean m_bProcess;
    private boolean m_bInit;
    private boolean m_bInSync;
    private Vector m_vRequestFilledStubEntries;
    
    public void removeEmptyBooks(final TocData tocData, final Vector vector) {
        boolean b = false;
        if (vector.size() > 0) {
            int n = vector.size() - 1;
            do {
                final TocEntry tocEntry = vector.elementAt(n);
                if (this.isEmptyBook(tocData, tocEntry)) {
                    vector.removeElementAt(n);
                    tocEntry.remove();
                    b = true;
                }
            } while (--n >= 0);
            if (b && vector.size() > 0) {
                int n2 = 0;
                do {
                    vector.elementAt(n2).updateIndex(n2);
                } while (++n2 < vector.size());
            }
        }
    }
    
    protected void procResize() {
        if (!this.m_bInit) {
            this.loadRootData();
            this.m_bInit = true;
        }
        if (!this.m_bProcess) {
            super.procResize();
        }
    }
    
    protected void listPaint(final Graphics graphics, final Image image) {
        final int top = this.getTop();
        final int visible = this.getVisible();
        this.setHorizontalMax(this.getWidth(graphics));
        final Enumeration elements = this.getVisibleBlock(top, visible).elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().display(graphics, top, visible, this.getUnitHeight(), this.getBackground(), image);
        }
        if (this.m_bProcess) {
            this.displayLoadingMsg(graphics);
        }
    }
    
    public void sync(final String s, final String s2) {
        if (this.m_vTocBlocks.size() == 0) {
            return;
        }
        final Vector interpretTocPaths = this.interpretTocPaths(s2);
        final Vector<String> vector = new Vector<String>();
        final Enumeration<String> elements = interpretTocPaths.elements();
        while (elements.hasMoreElements()) {
            final Vector tocPaths = this.getTocPaths(s, elements.nextElement());
            if (tocPaths != null && tocPaths.size() > 0) {
                final Enumeration<String> elements2 = tocPaths.elements();
                while (elements2.hasMoreElements()) {
                    vector.addElement(elements2.nextElement().substring(1));
                }
            }
        }
        final String closestTocPath = this.getClosestTocPath(vector);
        if (closestTocPath != null) {
            if (!this.m_bSync) {
                this.m_bSync = true;
                this.m_sSyncPath = closestTocPath;
                this.setTimeout("syncTocInt", 1);
                return;
            }
            this.m_sBackSyncPath = closestTocPath;
        }
    }
    
    private Vector getTocPaths(final String s, final String s2) {
        Vector<String> vector = null;
        Object url = null;
        try {
            url = URLFileHandler.makeURL(BsscXML.getDocumentBase(), s + Project.getFileName(), null);
        }
        catch (MalformedURLException ex) {}
        int i = 0;
        while (i < this.m_chunkedInfos.size()) {
            final Toc toc = this.m_chunkedInfos.elementAt(i);
            if (toc.getProjURL().equals(url)) {
                final Vector rootTocPaths = toc.getRootTocPaths();
                if (rootTocPaths != null && rootTocPaths.size() > 0) {
                    vector = new Vector<String>();
                    for (int j = 0; j < rootTocPaths.size(); ++j) {
                        vector.addElement(rootTocPaths.elementAt(j) + s2);
                    }
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
        return vector;
    }
    
    private URL getFullURL(final URL url, final String s) {
        URL url2 = null;
        try {
            url2 = URLFileHandler.makeURL(url, s, null);
        }
        catch (MalformedURLException ex) {}
        return url2;
    }
    
    public void accept(final Vector vector) {
        if (vector != null && vector.size() == 5) {
            final TocEntry currentEntry = vector.elementAt(0);
            final String s = (String)vector.elementAt(1);
            final String s2 = (String)vector.elementAt(2);
            final TocData tocData = (TocData)vector.elementAt(3);
            final String s3 = (String)vector.elementAt(4);
            final TocBlock tocBlock = (TocBlock)currentEntry.getContainer();
            switch (currentEntry.getType()) {
                case 1: {
                    currentEntry.toggle();
                    if (tocBlock != null) {
                        tocBlock.setNeedCalWidth();
                        final int height = tocBlock.getHeight();
                        tocBlock.callPosOffsetByEntry(currentEntry);
                        final int n = tocBlock.getHeight() - height;
                        final TocBlock parentBlock = tocBlock.getParentBlock();
                        if (parentBlock != null) {
                            parentBlock.propagateHeightChange(tocBlock, n);
                        }
                        this.setVerticalMax(this.getRootBlock().getHeight());
                        this.clearHightLighted();
                        this.repaint();
                    }
                    if (s != null && s.length() != 0 && !this.m_bInSync) {
                        this.jumpToURL(tocData.getProjURL(), s, s3);
                        return;
                    }
                    break;
                }
                case 2:
                case 5: {
                    if (s != null && s.length() != 0) {
                        this.jumpToURL(tocData.getProjURL(), s, s3);
                        return;
                    }
                    break;
                }
                case 3:
                case 4: {
                    if (tocBlock != null) {
                        final TocBlock subBlock = tocBlock.getSubBlock(currentEntry.getIndex());
                        if (subBlock == null) {
                            if (!this.m_bProcess) {
                                try {
                                    TocData tocData2 = null;
                                    if (currentEntry.getType() == 3) {
                                        tocData2 = new TocData(URLFileHandler.makeURL(tocData.getURL(), s2, null), tocData.getProjURL());
                                    }
                                    else {
                                        final URL url = URLFileHandler.makeURL(tocData.getProjURL(), s2, null);
                                        final Enumeration<Project> elements = (Enumeration<Project>)this.m_vProjects.elements();
                                        while (elements.hasMoreElements()) {
                                            final Project nextElement = elements.nextElement();
                                            if (nextElement instanceof Project && nextElement.getURL().equals(url)) {
                                                if (nextElement.getToc() != null) {
                                                    tocData2 = new TocData(nextElement.getToc().getRootTocURL(), url);
                                                    break;
                                                }
                                                break;
                                            }
                                        }
                                    }
                                    if (tocData2 != null) {
                                        this.m_currentEntry = currentEntry;
                                        this.markBegin();
                                        tocData2.load(this, currentEntry.getLevel());
                                    }
                                    else if (this.m_vRequestFilledStubEntries.size() > 0) {
                                        final TocEntry tocEntry = this.m_vRequestFilledStubEntries.firstElement();
                                        this.m_vRequestFilledStubEntries.removeElementAt(0);
                                        tocEntry.action(this);
                                    }
                                }
                                catch (MalformedURLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            else if (!this.m_vRequestFilledStubEntries.contains(currentEntry)) {
                                this.m_vRequestFilledStubEntries.addElement(currentEntry);
                            }
                        }
                        else {
                            currentEntry.toggle();
                            int height2 = subBlock.getHeight();
                            if (!currentEntry.isOpen()) {
                                height2 = -height2;
                            }
                            subBlock.setVisible(currentEntry.isOpen());
                            tocBlock.propagateHeightChange(subBlock, height2);
                            this.setVerticalMax(this.getRootBlock().getHeight());
                            this.clearHightLighted();
                            this.repaint();
                        }
                    }
                    if (s != null && s.length() != 0 && !this.m_bInSync) {
                        this.jumpToURL(tocData.getProjURL(), s, s3);
                        return;
                    }
                    break;
                }
            }
        }
    }
    
    public void dispatchToDo(final String s) {
        if (s.equals("syncTocInt")) {
            this.syncTocInt();
            return;
        }
        super.dispatchToDo(s);
    }
    
    public void putData(final IChunkedData chunkedData) {
        if (chunkedData instanceof TocData) {
            final TocData tocData = (TocData)chunkedData;
            final Vector tocEntires = tocData.getTocEntires();
            this.removeEmptyBooks(tocData, tocEntires);
            if (tocEntires.size() > 0) {
                final TocBlock tocBlock = new TocBlock(this, tocEntires);
                this.m_vTocBlocks.addElement(tocBlock);
                if (this.m_currentEntry != null) {
                    final TocBlock tocBlock2 = (TocBlock)this.m_currentEntry.getContainer();
                    if (tocBlock2 != null) {
                        tocBlock.setParentEntry(this.m_currentEntry);
                        this.m_currentEntry.toggle();
                        tocBlock2.insertTocBlock(this.m_currentEntry, tocBlock);
                        this.m_currentEntry.setStubFilled();
                    }
                    this.m_currentEntry = null;
                }
                else {
                    BsscHelpRedirector.doJavaScript("setTimeout(\"getInitTocInfo();\", 100);");
                }
            }
            else if (this.m_currentEntry != null) {
                this.m_currentEntry.setStubFilled();
                this.m_currentEntry = null;
            }
            final TocBlock rootBlock = this.getRootBlock();
            if (rootBlock != null) {
                rootBlock.calPosOffset();
                this.setVerticalMax(rootBlock.getHeight());
            }
            this.repaint();
        }
        this.markEnd();
        if (this.m_vRequestFilledStubEntries.size() > 0) {
            final TocEntry tocEntry = this.m_vRequestFilledStubEntries.firstElement();
            this.m_vRequestFilledStubEntries.removeElementAt(0);
            tocEntry.action(this);
        }
        if (this.m_bSync) {
            this.setTimeout("syncTocInt", 1);
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (TocEntry.hasImage(image)) {
            this.repaint();
            return true;
        }
        return super.imageUpdate(image, n, n2, n3, n4, n5);
    }
    
    private String calTocPath(final TocEntry tocEntry) {
        String s = tocEntry.getName();
        for (TocEntry tocEntry2 = this.getParentEntry(tocEntry); tocEntry2 != null; tocEntry2 = this.getParentEntry(tocEntry2)) {
            s = tocEntry2.getName() + TocListView.m_sTocPathSplit + s;
        }
        return s;
    }
    
    private int expandToc(final TocEntry tocEntry, final String s, final Vector vector) {
        vector.size();
        final int index = s.indexOf(TocListView.m_sTocPathSplit);
        String substring = null;
        String substring2;
        if (index != -1) {
            substring2 = s.substring(0, index);
            substring = s.substring(index + 1);
        }
        else {
            substring2 = s;
        }
        for (TocEntry nextSibling = tocEntry; nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
            if (nextSibling.getName().equals(substring2)) {
                if (substring == null) {
                    vector.addElement(nextSibling);
                    return 1;
                }
                if (nextSibling.getType() != 1) {
                    return 0;
                }
                vector.addElement(nextSibling);
                final int expandToc = this.expandToc(nextSibling.getFirstChild(), substring, vector);
                if (expandToc != 0) {
                    return expandToc;
                }
                vector.removeElementAt(vector.size() - 1);
            }
            else if (nextSibling.isStubEntry()) {
                final TocBlock tocBlock = (TocBlock)nextSibling.getContainer();
                if (tocBlock != null) {
                    final TocBlock subBlock = tocBlock.getSubBlock(nextSibling.getIndex());
                    if (subBlock == null) {
                        nextSibling.action(this);
                        return -1;
                    }
                    final int expandToc2 = this.expandToc(subBlock.getEntryByIdx(0), s, vector);
                    if (expandToc2 != 0) {
                        return expandToc2;
                    }
                }
            }
        }
        return 0;
    }
    
    private void jumpToURL(final URL url, final String s, final String s2) {
        final URL fullURL = this.getFullURL(url, s);
        if (fullURL != null) {
            if (s2 != null) {
                BsscHelpRedirector.showDoc(fullURL, s2);
                return;
            }
            BsscHelpRedirector.showDoc(fullURL);
        }
    }
    
    public boolean isEmptyBook(final TocData tocData, final TocEntry tocEntry) {
        if (tocEntry.getType() == 3 || tocEntry.getType() == 2 || tocEntry.getType() == 5) {
            return false;
        }
        if (tocEntry.getType() == 1) {
            for (TocEntry tocEntry2 = tocEntry.getFirstChild(); tocEntry2 != null; tocEntry2 = tocEntry2.getNextSibling()) {
                if (!this.isEmptyBook(tocData, tocEntry2)) {
                    return false;
                }
            }
        }
        else if (tocEntry.getType() == 4) {
            try {
                final URL url = URLFileHandler.makeURL(tocData.getProjURL(), tocEntry.getRef(), null);
                final Enumeration<Project> elements = this.m_vProjects.elements();
                while (elements.hasMoreElements()) {
                    final Project nextElement = elements.nextElement();
                    if (nextElement instanceof Project && nextElement.getURL().equals(url)) {
                        return nextElement.getToc() == null;
                    }
                }
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }
    
    private Vector getVisibleBlock(final int n, final int n2) {
        final Vector<TocBlock> vector = new Vector<TocBlock>();
        for (int i = 0; i < this.m_vTocBlocks.size(); ++i) {
            final TocBlock tocBlock = this.m_vTocBlocks.elementAt(i);
            final int top = tocBlock.getTop();
            final int n3 = top + tocBlock.getHeight();
            if (tocBlock.isVisible() && (top <= n || top < n + n2) && (n3 >= n + n2 || n3 > n)) {
                vector.addElement(tocBlock);
            }
        }
        return vector;
    }
    
    protected int getWidth(final Graphics graphics) {
        int n = 0;
        for (int i = 0; i < this.m_vTocBlocks.size(); ++i) {
            final int width = this.m_vTocBlocks.elementAt(i).getWidth(graphics);
            if (width > n) {
                n = width;
            }
        }
        return n;
    }
    
    protected void selectedEntry(final IEntry entry) {
        if (entry == null) {
            this.m_sCurrentTocPath = null;
            return;
        }
        if (entry instanceof TocEntry) {
            this.m_sCurrentTocPath = this.calTocPath((TocEntry)entry);
        }
    }
    
    public TocBlock getRootBlock() {
        if (this.m_vTocBlocks.size() > 0) {
            return this.m_vTocBlocks.elementAt(0);
        }
        return null;
    }
    
    private void displayLoadingMsg(final Graphics graphics) {
        final Color color = graphics.getColor();
        final Font font = graphics.getFont();
        graphics.setFont(new Font(font.getName(), font.getStyle() | 0x1, font.getSize() + 1));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth(TocListView.m_sLoadingMsg);
        graphics.setColor(Color.black);
        graphics.drawRect(2, 2, stringWidth + 3, fontMetrics.getHeight() + 3);
        graphics.setColor(TocListView.YELLOW);
        graphics.fillRect(3, 3, stringWidth + 2, fontMetrics.getHeight() + 2);
        graphics.setColor(Color.black);
        graphics.drawString(TocListView.m_sLoadingMsg, 3, 3 + fontMetrics.getLeading() + fontMetrics.getAscent());
        graphics.setColor(color);
        graphics.setFont(font);
    }
    
    protected void procClicked() {
        if (!this.m_bProcess) {
            super.procClicked();
        }
    }
    
    private String getClosestTocPath(final Vector vector) {
        int n = -1;
        String s = null;
        if (vector.size() == 0) {
            return null;
        }
        final Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final String s2 = elements.nextElement();
            if (s2.length() > 0) {
                final int compareTocPath = this.compareTocPath(this.m_sCurrentTocPath, s2);
                if (compareTocPath <= n) {
                    continue;
                }
                n = compareTocPath;
                s = s2;
            }
        }
        if (s != null && s.equals(this.m_sCurrentTocPath)) {
            s = null;
        }
        return s;
    }
    
    public TocListView(final Vector vProjects, final Vector chunkedInfos) {
        this.m_sCurrentTocPath = "";
        this.m_bSync = false;
        this.m_bProcess = false;
        this.m_bInit = false;
        this.m_bInSync = false;
        if (TocListView.m_sLoadingMsg == null) {
            TocListView.m_sLoadingMsg = ResourceLib.GetRes("LoadingData");
        }
        this.m_vProjects = vProjects;
        this.m_chunkedInfos = chunkedInfos;
        this.m_vTocBlocks = new Vector();
        this.m_vRequestFilledStubEntries = new Vector();
    }
    
    protected void procMoved() {
        if (!this.m_bProcess) {
            super.procMoved();
        }
    }
    
    private void syncTocInt() {
        if (this.m_sSyncPath != null) {
            this.m_bInSync = true;
            final TocEntry entryByIdx = this.m_vTocBlocks.elementAt(0).getEntryByIdx(0);
            final Vector vector = new Vector<TocEntry>();
            final int expandToc = this.expandToc(entryByIdx, this.m_sSyncPath, vector);
            if (expandToc != -1) {
                if (expandToc == 1) {
                    if (vector.size() > 0) {
                        for (int i = 0; i < vector.size() - 1; ++i) {
                            final TocEntry tocEntry = vector.elementAt(i);
                            if (!tocEntry.isOpen()) {
                                tocEntry.action(this);
                            }
                        }
                    }
                    final TocEntry tocEntry2 = vector.elementAt(vector.size() - 1);
                    final TocBlock tocBlock = (TocBlock)tocEntry2.getContainer();
                    this.setTop(tocBlock.getTop() + tocEntry2.getPosOffset() - this.getVisible() + 2);
                    this.selectEntry(this.getTop(), tocBlock.getTop() + tocEntry2.getPosOffset(), tocEntry2);
                    this.repaint();
                }
                this.m_sSyncPath = this.m_sBackSyncPath;
                this.m_sBackSyncPath = null;
                this.setTimeout("syncTocInt", 1);
            }
            this.m_bInSync = false;
            return;
        }
        this.m_bSync = false;
    }
    
    public void loadRootData() {
        final Toc toc = this.m_chunkedInfos.elementAt(0);
        final TocData tocData = new TocData(toc.getRootTocURL(), toc.getProjURL());
        this.markBegin();
        tocData.load(this);
    }
    
    private void markBegin() {
        this.m_bProcess = true;
        this.repaint();
    }
    
    protected IEntry getEntryByPos(final int n) {
        final Vector visibleBlock = this.getVisibleBlock(n, 1);
        if (visibleBlock != null) {
            if (visibleBlock.size() == 1) {
                return visibleBlock.elementAt(0).getEntry(n);
            }
            if (visibleBlock.size() > 1) {
                TocBlock tocBlock = visibleBlock.elementAt(0);
                int n2 = tocBlock.getHeight();
                final Enumeration<TocBlock> elements = visibleBlock.elements();
                while (elements.hasMoreElements()) {
                    final TocBlock tocBlock2 = elements.nextElement();
                    if (tocBlock2.getHeight() < n2) {
                        tocBlock = tocBlock2;
                        n2 = tocBlock2.getHeight();
                    }
                }
                return tocBlock.getEntry(n);
            }
        }
        return null;
    }
    
    private TocEntry getParentEntry(final TocEntry tocEntry) {
        if (tocEntry == null) {
            return null;
        }
        final TocEntry directParent = tocEntry.getDirectParent();
        if (directParent != null) {
            return directParent;
        }
        final TocBlock tocBlock = (TocBlock)tocEntry.getContainer();
        if (tocBlock != null) {
            return tocBlock.getParentEntry();
        }
        return null;
    }
    
    static {
        TocListView.m_sLoadingMsg = null;
        YELLOW = new Color(255, 255, 225);
        TocListView.m_sTocPathSplit = "\n";
    }
    
    private Vector interpretTocPaths(final String s) {
        final Vector<String> vector = new Vector<String>();
        if (s.length() == 0) {
            return vector;
        }
        int i = 0;
        do {
            final int index = s.indexOf("\r\r", i);
            String s2;
            if (index != -1) {
                s2 = s.substring(i, index);
                i = index + 2;
            }
            else {
                s2 = s.substring(i);
                i = -1;
            }
            if (s2.length() > 0) {
                vector.addElement(s2);
            }
        } while (i != -1);
        return vector;
    }
    
    protected void procScroll() {
        if (!this.m_bProcess) {
            super.procScroll();
        }
    }
    
    private void markEnd() {
        if (this.m_bProcess) {
            this.m_bProcess = false;
            this.repaint();
            BsscHelpRedirector.showStatus(ResourceLib.GetRes("Done"));
        }
    }
    
    private int compareTocPath(final String s, final String s2) {
        if (s == null || s2 == null) {
            return 0;
        }
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        do {
            final int index = s.indexOf(TocListView.m_sTocPathSplit, n2);
            String s3;
            if (index != -1) {
                s3 = s.substring(n2, index);
                n2 = index + 1;
            }
            else {
                s3 = s.substring(n2);
                n2 = -1;
            }
            final int index2 = s2.indexOf(TocListView.m_sTocPathSplit, n3);
            String s4;
            if (index2 != -1) {
                s4 = s2.substring(n3, index2);
                n3 = index2 + 1;
            }
            else {
                s4 = s2.substring(n3);
                n3 = -1;
            }
            if (!s3.equals(s4)) {
                break;
            }
            ++n;
        } while (n2 != -1 && n3 != -1);
        return n;
    }
}
