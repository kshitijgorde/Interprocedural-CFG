import java.util.Enumeration;
import java.awt.Component;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class GCThread extends Thread
{
    static final String TOC_CONTENTSTYPE = "f=JContents&c=getchildren";
    static final String PROP_DOCNAME = "DN";
    static final String PROP_TITLE = "T";
    static final String PROP_BREAK = "&";
    static final String VALUE_BREAK = "=";
    static final String PARENT_ID = "PID";
    static final String ID = "ID";
    static final String PROP_MORENODE = "M";
    static final String SYNC = "SYNC=1";
    static final String PROP_NODETYPE = "NT";
    static final String PROP_PATH = "path=";
    static final String PROP_SUBPATH = "subpath=";
    static final String PROP_START = "start=";
    static final String PROP_FLAGS = "FLAGS";
    static final String PROP_MEDIATITLES = "MT";
    static final String GC_START_NODE = "startnode";
    static final String GC_MAX_NODES = "maxnodes";
    static final String GC_MAXGRANDCHILDREN = "maxgrandchildren";
    static final String GC_MINNODESLEFT = "minnodesleft";
    static final String GC_DIRECTION = "direction";
    static final String SYNC_PATH = "syncpath";
    static final String SYNC_MAX_SIBLINGS = "maxsiblings";
    boolean bFirstChild;
    boolean bAddFirstChild;
    int nNewNodeCount;
    Integer nLevel;
    JNode StartNode;
    JNode MoreNode;
    JTreeView TreeView;
    String szServerURL;
    String szSyncPath;
    String szReceiveBuffer;
    JTOC toc;
    Vector ParentLevelNodes;
    Vector CurrentLevelNodes;
    
    void addChildren(final Properties properties) {
        TocDebug.TraceL3("GCThread::addChildren");
        int intValue = -1;
        final String property = properties.getProperty("DN");
        final String property2 = properties.getProperty("T");
        if (property != null && property2 != null) {
            this.toc.showStatus(TocUtil.replace(this.toc.szAddingDocument, "%s", property2));
            ++this.nNewNodeCount;
            int int1;
            int int2;
            try {
                int1 = Integer.parseInt(properties.getProperty("PID"));
                int2 = Integer.parseInt(properties.getProperty("ID"));
            }
            catch (NumberFormatException ex) {
                return;
            }
            final String property3 = properties.getProperty("M");
            if (property3 != null) {
                intValue = Integer.valueOf(property3);
            }
            if (int2 == 0 || this.bFirstChild) {
                this.ParentLevelNodes = this.CurrentLevelNodes;
                this.CurrentLevelNodes = new Vector();
                this.bFirstChild = false;
                this.bAddFirstChild = false;
            }
            JNode startNode;
            if (this.ParentLevelNodes == null) {
                startNode = this.StartNode;
            }
            else {
                startNode = this.ParentLevelNodes.elementAt(int1);
            }
            JNode node;
            if (startNode != null) {
                this.bFirstChild = false;
                if (this.MoreNode != null) {
                    if (this.MoreNode.getMoreType() == 0) {
                        this.bAddFirstChild = true;
                    }
                    if (property.compareTo(this.MoreNode.getDocumentName()) == 0) {
                        this.MoreNode.setMoreType(-1);
                        this.TreeView.removeNode(this.MoreNode.getFirstChild());
                        node = this.MoreNode;
                        node.setExpanded(false);
                        this.MoreNode = null;
                    }
                    else {
                        node = this.TreeView.addChildNode(startNode, property2, 6, this.bAddFirstChild);
                        node.setLoading(this.TreeView.m_LoadingText);
                    }
                }
                else {
                    node = this.TreeView.addChildNode(startNode, property2, 4, this.bAddFirstChild);
                }
                if (this.MoreNode == null && this.StartNode == null && node.getLevel() <= this.TreeView.getDefaultExpandLevel()) {
                    startNode.setExpanded(true);
                }
            }
            else {
                node = this.TreeView.addRootNode(property2, 1);
            }
            node.setDocumentName(property);
            final String property4 = properties.getProperty("FLAGS");
            TocDebug.Assert(property4 != null, "Server did not return a Flags property for this node");
            node.setAllFlags(new Integer(property4));
            if (node.isRemovable(false)) {
                node.setMediaTitles(properties.getProperty("MT"));
            }
            final String property5 = properties.getProperty("NT");
            TocDebug.Assert(property5 != null, "Server did not return a NoteType property for this node.");
            node.setType(Integer.valueOf(property5));
            if (intValue > -1) {
                node.setMoreType(intValue);
            }
            this.CurrentLevelNodes.addElement(node);
            return;
        }
        this.toc.showStatus(this.toc.szErrorNoTitle);
    }
    
    public GCThread(final JTOC toc, final String s, final JTreeView treeView, final JNode startNode, final String szSyncPath) {
        this.bFirstChild = true;
        this.bAddFirstChild = false;
        this.nNewNodeCount = 0;
        this.nLevel = new Integer(1);
        this.StartNode = null;
        this.MoreNode = null;
        this.TreeView = null;
        this.szServerURL = null;
        this.szSyncPath = null;
        this.szReceiveBuffer = null;
        this.toc = null;
        this.ParentLevelNodes = null;
        this.CurrentLevelNodes = null;
        TocDebug.TraceL3("GCThread::GCThread");
        this.StartNode = startNode;
        this.szServerURL = new String(s);
        this.TreeView = treeView;
        this.toc = toc;
        this.szSyncPath = szSyncPath;
    }
    
    public String getChildren() {
        TocDebug.TraceL3("GCThread::getChildren");
        String line = null;
        Label_0688: {
            URL url;
            try {
                if (this.szSyncPath != null && this.szSyncPath.length() > 0) {
                    this.toc.showStatus(this.toc.szSynchingTOC);
                }
                String s = new String() + "f=JContents&c=getchildren";
                if (this.StartNode != null) {
                    String s2 = s + "&";
                    if (this.StartNode.getMoreType() > -1) {
                        final String string = s2 + "start=" + TocUtil.percentEncode(this.StartNode.getDocumentName()) + "&";
                        this.MoreNode = this.StartNode;
                        this.StartNode = this.MoreNode.getParent();
                        s2 = string + "direction" + "=" + new Integer(this.MoreNode.getMoreType()).toString() + "&";
                    }
                    s = s2 + "path=" + this.StartNode.getPath(false, true);
                    if (this.StartNode.isSubDocument()) {
                        s = s + "&" + "subpath=" + this.StartNode.getSubPath(false, true);
                    }
                }
                if (this.szSyncPath != null && this.szSyncPath.length() > 0) {
                    if (this.StartNode != null) {
                        this.szSyncPath = this.szSyncPath.substring(this.StartNode.getPath(false, false).length() + 1);
                    }
                    s = s + "&" + "syncpath" + "=" + TocUtil.percentEncode(this.szSyncPath) + "&" + "maxsiblings" + "=" + new Integer(JTOC.MAX_SYNC_SIBLINGS).toString();
                }
                url = new URL(JTOC.codebase.getProtocol(), JTOC.codebase.getHost(), JTOC.codebase.getPort(), this.szServerURL + "?" + (s + "&" + "maxnodes" + "=" + new Integer(JTOC.MAX_NODES).toString() + "&" + "maxgrandchildren" + "=" + new Integer(JTOC.MAX_GRAND_CHILDREN).toString() + "&" + "minnodesleft" + "=" + new Integer(JTOC.MIN_NODES_LEFT).toString()));
                break Label_0688;
            }
            catch (MalformedURLException ex) {
                this.toc.showStatus("MalformedURLException Thrown");
                return null;
            }
            try {
                boolean handleNotPresent;
                do {
                    handleNotPresent = false;
                    TocDebug.TraceL2(this.toc.szRequestingChildren);
                    this.toc.showStatus(this.toc.szRequestingChildren);
                    final DataInputStream dataInputStream = new DataInputStream(new DataInputStream(url.openConnection().getInputStream()));
                    TocDebug.TraceL2("Request Children Finished");
                    line = dataInputStream.readLine();
                    dataInputStream.close();
                    if (line.compareTo("INFOBASENOTPRESENT") == 0) {
                        int n = 0;
                        JNode startNode = null;
                        if (this.StartNode.GetType() == 5 || this.StartNode.GetTypeBeforeError() == 5) {
                            startNode = this.StartNode;
                            n = 1;
                        }
                        if (n == 0) {
                            for (JNode node = this.StartNode.getParent(); node != null; node = node.getParent()) {
                                if (node.GetType() == 5) {
                                    startNode = node;
                                    n = 1;
                                    break;
                                }
                            }
                        }
                        if (n == 0) {
                            handleNotPresent = this.toc.handleNotPresent(this.StartNode);
                        }
                        else {
                            if (startNode != null) {
                                this.StartNode.setError(this.StartNode.getText());
                                this.TreeView.setExpanded(this.StartNode, false);
                            }
                            this.TreeView.repaint();
                            handleNotPresent = false;
                        }
                        if (handleNotPresent) {
                            continue;
                        }
                        line = "";
                    }
                    else {
                        if (this.StartNode == null) {
                            continue;
                        }
                        this.StartNode.unsetError();
                    }
                } while (handleNotPresent);
            }
            catch (IOException ex2) {
                this.toc.showStatus(this.toc.szServerError);
            }
        }
        return line;
    }
    
    public void run() {
        TocDebug.TraceL3("GCThread::run");
        try {
            TocDebug.TraceL2(this.toString() + " Getting Children");
            this.szReceiveBuffer = this.getChildren();
            this.TreeView.setRedraw(false);
            if (this.szReceiveBuffer == null || this.szReceiveBuffer.length() == 0 || this.szReceiveBuffer.indexOf("LivePublish System Message") != -1 || this.szReceiveBuffer.indexOf("<HTML>") != -1) {
                TocDebug.TraceL1("GetChildren returned null");
                if (this.StartNode.getFirstChild() != null && this.StartNode.getFirstChild().isLoading()) {
                    if (!this.StartNode.isRemovable(true)) {
                        this.StartNode.getFirstChild().setError(this.TreeView.m_ErrorText);
                    }
                    else {
                        this.TreeView.removeNode(this.StartNode.getFirstChild());
                        this.StartNode.setExpanded(false);
                    }
                }
                else if (this.MoreNode != null && this.MoreNode.getFirstChild() != null && this.MoreNode.getFirstChild().isLoading()) {
                    if (!this.MoreNode.isRemovable(true)) {
                        this.MoreNode.getFirstChild().setError(this.TreeView.m_ErrorText);
                    }
                    else {
                        this.TreeView.removeNode(this.MoreNode.getFirstChild());
                        this.MoreNode.setExpanded(false);
                    }
                }
                this.toc.showStatus(this.toc.szErrorNoData);
                return;
            }
            this.toc.showStatus(this.toc.szProcessingData);
            final LPParser lpParser = new LPParser(JTOC.MAX_NODES, this.szReceiveBuffer);
            lpParser.processServerResponce();
            boolean b = false;
            if (lpParser.getNodeCount() == 0 && this.StartNode != null) {
                b = true;
            }
            this.TreeView.m_RootNode.setText(lpParser.getRootTitle());
            TocDebug.TraceL3("Adding Nodes");
            final Enumeration enumerate = lpParser.enumerate();
            while (enumerate.hasMoreElements()) {
                this.addChildren(enumerate.nextElement());
            }
            if (this.StartNode != null && this.MoreNode == null && b) {
                this.TreeView.removeNode(this.StartNode.getFirstChild());
            }
            if (this.szSyncPath != null && this.szSyncPath.length() > 0) {
                TocDebug.TraceL3("Sync Tree");
                String s;
                if (this.StartNode != null) {
                    s = this.StartNode.getPath(false, false) + "/" + this.szSyncPath;
                }
                else {
                    s = this.szSyncPath;
                }
                this.TreeView.syncTree(s);
                this.toc.showStatus("");
            }
            else if (lpParser.getSyncPath().length() > 0) {
                this.TreeView.syncTree(lpParser.getSyncPath());
            }
            this.toc.showStatus(TocUtil.replace(this.toc.szLoadedDocuments, "%i", new Integer(this.nNewNodeCount).toString()));
            TocDebug.TraceL2(this.toString() + " Finshed getting Chidren");
        }
        catch (Throwable t) {
            TocDebug.MessageBox(TocUtil.GetFrame(this.TreeView), "Unhandled exception in GCThread::Run: " + t.toString(), "Exception");
            t.printStackTrace();
        }
        finally {
            if (this.MoreNode != null) {
                this.MoreNode.setLoading(false);
            }
            else if (this.StartNode != null) {
                this.StartNode.setLoading(false);
                if (this.StartNode.getFirstChild() != null && this.StartNode.getFirstChild().isLoading()) {
                    this.TreeView.removeNode(this.StartNode.getFirstChild());
                }
            }
            this.TreeView.setRedraw(true);
            if (this.StartNode != null && !this.StartNode.isPresent(false)) {
                this.TreeView.m_RootNode.updateIsPresent(this.toc, this.TreeView, this.szServerURL);
            }
            else if (this.StartNode != null && this.StartNode.getFirstChild() != null && (!this.StartNode.getFirstChild().isPresent(false) || !this.StartNode.areChildrenPresent(false))) {
                this.StartNode.notifyFamilyOfPresentState(null);
                this.TreeView.repaint();
            }
            System.gc();
        }
    }
}
