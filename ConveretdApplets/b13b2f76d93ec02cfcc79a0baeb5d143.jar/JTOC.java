import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.Color;
import java.net.MalformedURLException;
import java.awt.Component;
import java.util.StringTokenizer;
import java.awt.Dimension;
import java.net.URL;
import java.applet.AppletContext;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JTOC extends Applet
{
    JTreeView TreeView;
    JTree Tree;
    AppletContext Browser;
    static URL codebase;
    static String szServerURL;
    private Dimension m_Size;
    GCThread GCThread;
    public String szProcessingData;
    public String szErrorNoData;
    public String szLoadedDocuments;
    public String szSynchingTOC;
    public String szRequestingChildren;
    public String szAddingDocument;
    public String szErrorNoTitle;
    public String szServerError;
    static int MAX_NODES;
    static int MAX_GRAND_CHILDREN;
    static int MIN_NODES_LEFT;
    static int MAX_SYNC_SIBLINGS;
    static int NETSCAPE;
    static int MSIE;
    static int IEMAXSCROLLVER;
    static int NSMAXSCROLLVER;
    static int DEFAULTEXPANDLEVEL;
    
    public void stop() {
        TocDebug.TraceL2("JTOC::stop");
        if (this.GCThread != null) {
            this.GCThread.stop();
        }
    }
    
    public boolean handleNotPresent(final JNode node) {
        boolean b = false;
        if (!node.remoteIsPresent(true)) {
            this.TreeView.repaint();
            TocMessageBox tocMessageBox;
            do {
                final StringTokenizer stringTokenizer = new StringTokenizer(node.getMediaTitles(), ";");
                final String[] array = new String[stringTokenizer.countTokens() + 2];
                int n = 0;
                array[n] = new String(this.getParameter("LangMediaInsertMessage"));
                array[++n] = new String("");
                ++n;
                for (int i = 0; i < stringTokenizer.countTokens(); ++i) {
                    array[n + i] = stringTokenizer.nextToken();
                }
                tocMessageBox = new TocMessageBox(TocUtil.GetFrame(this), array, this.getParameter("LangMediaInsertMessageTitle"), 2);
                tocMessageBox.setBackground(this.TreeView.getBackground());
                tocMessageBox.setForeground(this.TreeView.getForeground());
                tocMessageBox.setFont(this.TreeView.m_Font);
                tocMessageBox.show();
            } while (tocMessageBox.Result() != 1 && !node.remoteIsPresent(false));
            if (tocMessageBox.Result() == 0) {
                b = true;
                this.TreeView.m_RootNode.updateIsPresent(this, this.TreeView, JTOC.szServerURL);
            }
        }
        return b;
    }
    
    public void mediaChanged() {
        this.TreeView.m_RootNode.updateIsPresent(this, this.TreeView, JTOC.szServerURL);
    }
    
    public JTOC() {
        this.TreeView = null;
        this.Tree = null;
        this.Browser = null;
        this.m_Size = new Dimension(200, 1000);
        this.GCThread = null;
        this.szProcessingData = "Processing Data...";
        this.szErrorNoData = "No data returned from server.";
        this.szLoadedDocuments = "Loaded %i documents.";
        this.szSynchingTOC = "Synchronizing the TOC...";
        this.szRequestingChildren = "Requesting children...";
        this.szAddingDocument = "Adding document %s";
        this.szErrorNoTitle = "Error - Child with no name or title,";
        this.szServerError = "Error returned from server.";
    }
    
    public static URL _getCodeBase() {
        return JTOC.codebase;
    }
    
    public void resize(final Dimension dimension) {
        TocDebug.TraceL2("JTOC::resize(Dimension d) " + dimension.toString());
        if (this.Tree != null) {
            this.Tree.reshape(0, 0, dimension.width, dimension.height);
            super.resize(dimension);
        }
    }
    
    public boolean syncTOC(String s) {
        TocDebug.TraceL3("JTOC::syncTOC");
        boolean syncTree = false;
        if (!this.TreeView.getRedraw() || s == null || s.length() == 0) {
            return false;
        }
        try {
            s = TocUtil.percentDecode(s);
            if (s.length() > 0 && s.charAt(0) != '/') {
                s = new String("/").concat(s);
            }
            int n = -1;
            final JNode findChild = this.TreeView.m_RootNode.FindChild(s);
            if (findChild != null) {
                if (findChild.getPath(false, false).compareTo(s) == 0) {
                    n = 3;
                }
                else {
                    n = 0;
                }
            }
            switch (n) {
                case 3: {
                    syncTree = this.TreeView.syncTree(s);
                    break;
                }
                case 0: {
                    if (findChild.getMoreType() > -1) {
                        final JNode parent = findChild.getParent();
                        this.TreeView.removeChildren(parent);
                        this.LoadSyncPath(parent, s);
                    }
                    else {
                        this.TreeView.removeChildren(findChild);
                        this.LoadSyncPath(findChild, s);
                    }
                    syncTree = true;
                    break;
                }
                case -1: {
                    syncTree = false;
                    break;
                }
                default: {
                    TocDebug.Assert(false);
                    break;
                }
            }
        }
        catch (Throwable t) {
            TocDebug.MessageBox(TocUtil.GetFrame(this), "Unhandled exception: " + t.toString(), "Exception");
            t.printStackTrace();
        }
        return syncTree;
    }
    
    void handleViewDocument(final JNode node) {
        TocDebug.TraceL3("JTOC::handleViewDocument");
        if (!node.hasContent() || (node.getMoreType() > -1 && !TocDebug.getDebug()) || node.isLoading() || this.TreeView.m_bShowChecks) {
            return;
        }
        if (node.getPath(false, true) != "") {
            try {
                String s = JTOC.szServerURL + node.getPath(false, true);
                final String parameter = this.getParameter("DocTemplate");
                if (parameter != null && parameter.length() > 0) {
                    s = s + "?" + TocUtil.percentEncode("fn=" + parameter + "&f=templates");
                }
                if (node.isSubDocument()) {
                    s = s + "#LPTOC" + node.getSubPath(true, false);
                }
                final URL url = new URL(JTOC.codebase.getProtocol(), JTOC.codebase.getHost(), JTOC.codebase.getPort(), s);
                final String parameter2 = this.getParameter("Target");
                TocDebug.TraceL2(url.toString() + " Target: " + parameter2);
                if (parameter2 != null && parameter2.length() > 0) {
                    this.Browser.showDocument(url, parameter2);
                }
            }
            catch (MalformedURLException ex) {
                this.showStatus("MalformedURLException thrown.");
            }
        }
        if (!node.isPresent(false) && node.isRemovable(true)) {
            node.remoteIsPresent(true);
            this.TreeView.repaint();
        }
    }
    
    public void LoadSyncPath(final JNode node, final String s) {
        new GCThread(this, JTOC.szServerURL, this.getTreeView(), node, s).start();
    }
    
    static {
        JTOC.codebase = null;
        JTOC.szServerURL = null;
        JTOC.MAX_NODES = 75;
        JTOC.MAX_GRAND_CHILDREN = 25;
        JTOC.MIN_NODES_LEFT = 10;
        JTOC.MAX_SYNC_SIBLINGS = 10;
        JTOC.NETSCAPE = 1;
        JTOC.MSIE = 2;
        JTOC.IEMAXSCROLLVER = 112;
        JTOC.NSMAXSCROLLVER = 112;
        JTOC.DEFAULTEXPANDLEVEL = 0;
    }
    
    public String getQueryPaths() {
        TocDebug.TraceL3("JTOC::getQueryPaths");
        return this.TreeView.getQueryPaths();
    }
    
    public String getAppletInfo() {
        TocDebug.TraceL3("JTOC::getAppletInfo");
        return "JTOC Rev 5\r\nNextPage\r\nCopyright 2001, All rights reserved.";
    }
    
    public static String getServerURL() {
        return JTOC.szServerURL;
    }
    
    public void setBrowserInfo() {
        TocDebug.TraceL2("JTOC::setBrowserInfo");
        int n = 0;
        int intValue = 0;
        String property = "";
        String property2 = "";
        try {
            property = System.getProperty("java.vendor");
            if (property.indexOf("Sym") >= 0) {
                this.TreeView.m_bUseAltScrollMethod = true;
                return;
            }
            if (property.indexOf("Sun") >= 0) {
                this.TreeView.m_bUseAltScrollMethod = true;
                return;
            }
            if (property.indexOf("Netscape") >= 0) {
                n = JTOC.NETSCAPE;
            }
            if (property.indexOf("Microsoft") >= 0) {
                n = JTOC.MSIE;
                try {
                    property2 = System.getProperty("http.agent");
                }
                catch (Throwable t2) {
                    intValue = 5;
                }
                final int index = property2.indexOf("MSIE");
                if (index > 0) {
                    intValue = Integer.valueOf(property2.substring(index + 5, index + 6));
                }
            }
        }
        catch (NumberFormatException ex) {
            TocDebug.TraceL2(ex.toString() + " Setting to MSIE");
            n = 2;
        }
        catch (Throwable t) {
            TocDebug.TraceL2(t.toString() + " Setting to NN");
            n = 1;
        }
        final String property3 = System.getProperty("java.version");
        int intValue2 = new Integer(TocUtil.replace(property3, ".", ""));
        if (intValue2 < 100) {
            intValue2 += 100;
        }
        TocDebug.TraceL2("MSBrowserVer:" + new Integer(intValue).toString() + " BrowserID:" + new Integer(n).toString());
        this.TreeView.m_bUseAltScrollMethod = ((n == JTOC.MSIE && intValue2 > JTOC.IEMAXSCROLLVER) || intValue >= 5 || (n == JTOC.NETSCAPE && intValue2 > JTOC.NSMAXSCROLLVER));
        TocDebug.TraceL2("JTOC: " + property + " " + property3 + (this.TreeView.m_bUseAltScrollMethod ? " - Setting Alt Scroll" : " - Normal Scroll"));
    }
    
    private boolean handleNodeExpansion(final JNode node) {
        TocDebug.TraceL3("JTOC::handleNodeExpansion");
        if (node.getNeedToLoadChildren() && !node.isLoading()) {
            node.setLoading(true);
            new GCThread(this, JTOC.szServerURL, this.getTreeView(), node, null).start();
        }
        return true;
    }
    
    public void showCheckBoxes(final boolean showCheckBoxes) {
        this.getTreeView().setShowCheckBoxes(showCheckBoxes);
    }
    
    void setParameters() {
        TocDebug.TraceL3("JTOC::setParameters");
        try {
            final String parameter = this.getParameter("TextColor");
            if (parameter != null && parameter.length() > 0 && parameter.charAt(0) == '#') {
                this.TreeView.setForeground(new Color(Integer.valueOf(parameter.substring(1), 16)));
                super.setForeground(new Color(Integer.valueOf(parameter.substring(1), 16)));
            }
            else {
                super.setForeground(Color.black);
            }
            final String parameter2 = this.getParameter("VisitedTextColor");
            if (parameter2 != null && parameter2.length() > 0 && parameter2.charAt(0) == '#') {
                this.TreeView.setViewedColor(new Color(Integer.valueOf(parameter2.substring(1), 16)));
            }
            final String parameter3 = this.getParameter("BackgroundColor");
            if (parameter3 != null && parameter3.length() > 0 && parameter3.charAt(0) == '#') {
                this.TreeView.setBackground(new Color(Integer.valueOf(parameter3.substring(1), 16)));
                super.setBackground(new Color(Integer.valueOf(parameter3.substring(1), 16)));
            }
            else {
                super.setBackground(Color.white);
            }
            final String parameter4 = this.getParameter("MouseOverColor");
            if (parameter4 != null && parameter4.length() > 0 && parameter4.charAt(0) == '#') {
                this.TreeView.setMouseOverTextColor(new Color(Integer.valueOf(parameter4.substring(1), 16)));
            }
            final String parameter5 = this.getParameter("LineColor");
            if (parameter5 != null && parameter5.length() > 0 && parameter5.charAt(0) == '#') {
                this.TreeView.setLineColor(new Color(Integer.valueOf(parameter5.substring(1), 16)));
            }
            final String parameter6 = this.getParameter("SelectedBackColor");
            if (parameter6 != null && parameter6.length() > 0 && parameter6.charAt(0) == '#') {
                this.TreeView.setSelectedBackColor(new Color(Integer.valueOf(parameter6.substring(1), 16)));
            }
            final String parameter7 = this.getParameter("SelectedTextColor");
            if (parameter7 != null && parameter7.length() > 0 && parameter7.charAt(0) == '#') {
                this.TreeView.setSelectedTextColor(new Color(Integer.valueOf(parameter7.substring(1), 16)));
            }
            final String parameter8 = this.getParameter("ShowCheckBoxes");
            if (parameter8 != null && parameter8.compareTo("1") == 0) {
                this.TreeView.setShowCheckBoxes(true);
            }
            final String parameter9 = this.getParameter("ShowImages");
            if (parameter9 != null && parameter9.compareTo("0") == 0) {
                this.TreeView.setImages(false);
            }
            final String parameter10 = this.getParameter("MaxNodes");
            if (parameter10 != null && parameter10.length() > 0) {
                JTOC.MAX_NODES = Integer.valueOf(parameter10);
            }
            final String parameter11 = this.getParameter("MinNodesLeft");
            if (parameter11 != null && parameter11.length() > 0) {
                JTOC.MIN_NODES_LEFT = Integer.valueOf(parameter11);
            }
            final String parameter12 = this.getParameter("MaxGrandChildren");
            if (parameter12 != null && parameter12.length() > 0) {
                JTOC.MAX_GRAND_CHILDREN = Integer.valueOf(parameter12);
            }
            final String parameter13 = this.getParameter("MaxSyncSiblings");
            if (parameter13 != null && parameter13.length() > 0) {
                JTOC.MAX_SYNC_SIBLINGS = Integer.valueOf(parameter13);
            }
            final String parameter14 = this.getParameter("DefaultExpandLevel");
            if (parameter14 != null && parameter14.length() > 0) {
                this.TreeView.setDefaultExpandLevel(Integer.valueOf(parameter14));
            }
            final String parameter15 = this.getParameter("TocDebugLevel");
            if (parameter15 != null && parameter15.length() > 0) {
                final int intValue = Integer.valueOf(parameter15);
                if (intValue > 0) {
                    TocDebug.Initialize(true, intValue, TocUtil.GetFrame(this));
                }
            }
        }
        catch (NumberFormatException ex) {
            TocDebug.TraceL2("JTOC::setParameters number format exception");
        }
        final String parameter16 = this.getParameter("LangProcessingData");
        if (parameter16 != null && parameter16.length() > 0) {
            this.szProcessingData = new String(parameter16);
        }
        final String parameter17 = this.getParameter("LangErrorNoServerData");
        if (parameter17 != null && parameter17.length() > 0) {
            this.szErrorNoData = new String(parameter17);
        }
        final String parameter18 = this.getParameter("LangLoadedNDocuments");
        if (parameter18 != null && parameter18.length() > 0) {
            this.szLoadedDocuments = new String(parameter18);
        }
        final String parameter19 = this.getParameter("LangSynchronizingTOC");
        if (parameter19 != null && parameter19.length() > 0) {
            this.szSynchingTOC = new String(parameter19);
        }
        final String parameter20 = this.getParameter("LangRequestingChildren");
        if (parameter20 != null && parameter20.length() > 0) {
            this.szRequestingChildren = new String(parameter20);
        }
        final String parameter21 = this.getParameter("LangAddingDocument");
        if (parameter21 != null && parameter21.length() > 0) {
            this.szAddingDocument = new String(parameter21);
        }
        final String parameter22 = this.getParameter("LangErrorNoTitle");
        if (parameter22 != null && parameter22.length() > 0) {
            this.szErrorNoTitle = new String(parameter22);
        }
        final String parameter23 = this.getParameter("LangServerError");
        if (parameter23 != null && parameter23.length() > 0) {
            this.szServerError = new String(parameter23);
        }
        final String parameter24 = this.getParameter("LangMoreNode");
        if (parameter24 != null && parameter24.length() > 0) {
            this.TreeView.m_MoreText = new String(parameter24);
        }
        final String parameter25 = this.getParameter("LangErrorNode");
        if (parameter25 != null && parameter25.length() > 0) {
            this.TreeView.m_ErrorText = new String(parameter25);
        }
        final String parameter26 = this.getParameter("LangLoadingNode");
        if (parameter26 != null && parameter26.length() > 0) {
            this.TreeView.m_LoadingText = new String(parameter26);
        }
    }
    
    public void init() {
        TocDebug.TraceL1("JTOC::init");
        try {
            TocDebug.Initialize(false, TocUtil.GetFrame(this));
            TocDebug.SetTraceLevel(2);
            super.init();
            this.Tree = new JTree(this);
            this.TreeView = this.Tree.m_TreeView;
            this.Browser = this.getAppletContext();
            this.setLayout(null);
            this.addNotify();
            JTOC.codebase = this.getCodeBase();
            final String parameter = this.getParameter("ExtDll");
            if (parameter.substring(0, 1).compareTo("/") == 0) {
                JTOC.szServerURL = parameter;
            }
            else {
                JTOC.szServerURL = "/" + parameter;
            }
            TocDebug.TraceL2("Server URL: " + JTOC.szServerURL);
            TocDebug.TraceL3("Start adding images...");
            this.TreeView.addImage(this.getImage(JTOC.codebase, "toc-check.gif"));
            this.TreeView.addImage(this.getImage(JTOC.codebase, "toc-site.gif"));
            this.TreeView.addImage(this.getImage(JTOC.codebase, "toc-collapsed.gif"));
            this.TreeView.addImage(this.getImage(JTOC.codebase, "toc-expand.gif"));
            this.TreeView.addImage(this.getImage(JTOC.codebase, "toc-collapsedhascontent.gif"));
            this.TreeView.addImage(this.getImage(JTOC.codebase, "toc-expandhascontent.gif"));
            this.TreeView.addImage(this.getImage(JTOC.codebase, "toc-partialcollapsed.gif"));
            this.TreeView.addImage(this.getImage(JTOC.codebase, "toc-partialexpanded.gif"));
            this.TreeView.addImage(this.getImage(JTOC.codebase, "toc-leaf.gif"));
            this.TreeView.addImage(this.getImage(JTOC.codebase, "toc-notpresent.gif"));
            this.TreeView.addImage(this.getImage(JTOC.codebase, "toc-docnotpresent.gif"));
            this.TreeView.addImage(this.getImage(JTOC.codebase, "toc-preload.gif"));
            this.TreeView.addImage(this.getImage(JTOC.codebase, "toc-loading.gif"));
            this.TreeView.addImage(this.getImage(JTOC.codebase, "toc-more.gif"));
            this.TreeView.addImage(this.getImage(JTOC.codebase, "toc-error.gif"));
            TocDebug.TraceL3("Fished Adding Images...");
            this.setParameters();
            TocDebug.TraceL3("Adding Tree to container");
            this.add(this.Tree);
            TocDebug.TraceL3("Resizing Tree");
            this.Tree.reshape(0, 0, this.m_Size.width, this.m_Size.height);
            this.setBrowserInfo();
            TocDebug.TraceL3("Adding Root Nodes");
            (this.GCThread = new GCThread(this, JTOC.szServerURL, this.TreeView, null, this.getParameter("SyncPath"))).start();
        }
        catch (Throwable t) {
            TocDebug.MessageBox(TocUtil.GetFrame(this), "Unhandled exception in JTOC::Initialize: " + t.toString(), "Exception");
            t.printStackTrace();
        }
    }
    
    public synchronized boolean handleEvent(final Event event) {
        TocDebug.TraceL3("JTOC::handleEvent");
        if (event.id == 54) {
            return !this.handleNodeExpansion((JNode)event.arg);
        }
        if (event.id == 61) {
            this.handleViewDocument((JNode)event.arg);
        }
        return super.handleEvent(event);
    }
    
    public JTreeView getTreeView() {
        TocDebug.TraceL3("JTOC::getTree");
        return this.TreeView;
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        TocDebug.TraceL2("JTOC::reshape");
        if (this.Tree != null) {
            this.Tree.reshape(0, 0, n3, n4);
            super.reshape(0, 0, n3, n4);
        }
    }
}
