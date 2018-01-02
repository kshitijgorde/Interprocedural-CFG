import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class JNode
{
    JNode m_Next;
    private JNode m_Prev;
    JNode m_FirstChild;
    JNode m_LastChild;
    private JNode m_Parent;
    private boolean m_bRoot;
    private boolean m_removed;
    public static final int LAST_CHILD = 5;
    public static final int FIRST_CHILD = 4;
    public static final int SELF = 3;
    public static final int LAST_SIBLING = 2;
    public static final int FIRST_SIBLING = 1;
    public static final int PARENT_NODE = 0;
    public static final int NO_RELATIONSHIP = -1;
    public static final int MORE_NONE = -1;
    public static final int MORE_FORWARD = 1;
    public static final int MORE_BACKWARD = 0;
    private int m_nMoreType;
    public static final int SITE = 1;
    public static final int DOCUMENT = 2;
    public static final int SUBDOCUMENT = 3;
    public static final int FOLDER = 4;
    public static final int LINK = 5;
    public static final int SPECIAL = 6;
    private int m_nType;
    private int m_nBeforeErrorType;
    private boolean m_bErrorDetected;
    public static final int NORMAL = 0;
    public static final int NOCONTENT = 1;
    public static final int NOTPRESENT = 2;
    public static final int CHILDRENNOTPRESENT = 4;
    public static final int MORE = 8;
    public static final int HASCHILDREN = 16;
    public static final int REMOVABLE = 32;
    public static final int LOADING = 64;
    public static final int INQUERY = 128;
    public static final int ERROR = 256;
    private volatile int m_nFlags;
    private volatile int m_nBeforeErrorFlags;
    int ROOT_LINE_OFFSET;
    int ROOT_LINE_LENGTH;
    public static final int CHECK_BOX_SIZE = 10;
    private int m_Level;
    private boolean m_Expanded;
    private String m_Text;
    private String m_BeforeErrorText;
    private String m_DocumentName;
    private String m_sMediaTitles;
    private NodeAttrib m_Attrib;
    
    public String getDocumentName() {
        TocDebug.TraceL3("Node::getDocumentName");
        return this.m_DocumentName;
    }
    
    public void setDocumentName(final String s) {
        TocDebug.TraceL3("Node::setDocumentName");
        this.m_DocumentName = new String(s);
    }
    
    public void notifyFamilyOfPresentState(final JNode node) {
        TocDebug.Assert(!this.m_bRoot, "Root not expected here.");
        boolean b = true;
        JNode node2 = this.m_FirstChild;
        while (node2 != null) {
            boolean b2;
            if (node != null && node == node2) {
                if (node2.isRemovable(true)) {
                    b2 = node2.isPresent(false);
                }
                else {
                    b2 = node2.areChildrenPresent(false);
                }
            }
            else if (node2.isRemovable(true)) {
                b2 = node2.isPresent(true);
            }
            else {
                b2 = node2.areChildrenPresent(true);
            }
            node2 = node2.getNextSibling();
            if (!b2) {
                b = false;
            }
        }
        if (this.isRemovable(false)) {
            this.setPresent(b);
        }
        else {
            this.setChildrenPresent(b);
        }
        if (this.m_Parent != null && !this.m_Parent.m_bRoot) {
            this.m_Parent.notifyFamilyOfPresentState(this);
        }
    }
    
    public void collapse(final boolean b) {
        TocDebug.TraceL3("Node::collapse");
        this.m_Expanded = false;
        if (b) {
            for (JNode node = this.m_FirstChild; node != null; node = node.m_Next) {
                node.collapse(b);
            }
        }
    }
    
    JNode getPrevLast(final JNode node) {
        TocDebug.TraceL3("Node::getPrevLast");
        if (this.m_LastChild == null) {
            return this;
        }
        if (this.m_LastChild == node) {
            return null;
        }
        return this.m_LastChild.getPrevLast(node);
    }
    
    public void setChildrenPresent(final boolean b) {
        if (b != this.areChildrenPresent(false)) {
            this.m_nFlags ^= 0x4;
        }
    }
    
    public boolean remoteIsPresent(final boolean b) {
        TocDebug.Assert(!this.m_bRoot, "Should not be the root!");
        final String s = "f=JContents&c=ispresent&path=";
        final String s2 = "f=JContents&c=arechildrenpresent&path=";
        boolean b2 = false;
        URL url;
        try {
            final String s3 = new String();
            String s4;
            if (this.isRemovable(true)) {
                s4 = s3 + s;
            }
            else {
                s4 = s3 + s2;
            }
            url = new URL(JTOC._getCodeBase().getProtocol(), JTOC._getCodeBase().getHost(), JTOC._getCodeBase().getPort(), JTOC.getServerURL() + "?" + (s4 + this.getPath(false, true)));
        }
        catch (MalformedURLException ex) {
            return false;
        }
        try {
            final DataInputStream dataInputStream = new DataInputStream(new DataInputStream(url.openConnection().getInputStream()));
            final String line = dataInputStream.readLine();
            dataInputStream.close();
            if (line.equalsIgnoreCase("true")) {
                b2 = true;
            }
        }
        catch (IOException ex2) {}
        final boolean b3 = (this.m_nFlags & 0x2) == 0x0 != b2;
        if (this.isRemovable(true)) {
            this.setPresent(b2);
        }
        else {
            this.setChildrenPresent(b2);
        }
        this.recursiveSetChildrenPresentFlag();
        if (b && b3 && this.m_Parent != null && !this.m_Parent.isRoot()) {
            this.m_Parent.notifyFamilyOfPresentState(this);
        }
        return b2;
    }
    
    public int getDecendentCount() {
        TocDebug.TraceL3("Node::getDecendentCount");
        JNode node = this.m_FirstChild;
        int n = 0;
        while (node != null) {
            n = ++n + node.getDecendentCount();
            node = node.m_Next;
        }
        return n;
    }
    
    public JNode getPrev() {
        TocDebug.TraceL3("Node::getPrev");
        if (this.m_Prev != null) {
            return this.m_Prev.getPrevLast(this);
        }
        return this.m_Parent;
    }
    
    JNode addChild(final int n, final String s, final int n2) {
        TocDebug.TraceL3("Node::addChild");
        final JNode node = new JNode(s, n2, this, this.m_Level + 1);
        node.setInQuery(this.isInQuery());
        if (this.m_LastChild == null) {
            final JNode node2 = node;
            this.m_LastChild = node2;
            this.m_FirstChild = node2;
        }
        else if (n == 2 || n == 0 || n == 5) {
            this.m_LastChild.m_Next = node;
            node.m_Prev = this.m_LastChild;
            this.m_LastChild = node;
        }
        else if (n == 1 || n == 4) {
            this.m_FirstChild.m_Prev = node;
            node.m_Next = this.m_FirstChild;
            this.m_FirstChild = node;
        }
        return node;
    }
    
    public int getLevel() {
        TocDebug.TraceL3("Node::getLevel");
        return this.m_Level;
    }
    
    int lineFromNode(final JTreeView treeView) {
        TocDebug.TraceL3("Node::lineFromNode");
        int n = 1;
        for (JNode node = treeView.m_FirstVisible; node != null && n <= treeView.m_LinesPerPage + 1; node = node.getNextVisible(), ++n) {
            if (node == this) {
                return n;
            }
        }
        return -1;
    }
    
    void remove() {
        TocDebug.TraceL3("Node::remove");
        if (this.m_Next != null && this.m_Prev != null) {
            this.m_Next.m_Prev = this.m_Prev;
            this.m_Prev.m_Next = this.m_Next;
        }
        else if (this.m_Next != null) {
            this.m_Next.m_Prev = null;
            if (this.m_Parent != null && this.m_Parent.m_FirstChild == this) {
                this.m_Parent.m_FirstChild = this.m_Next;
            }
        }
        else if (this.m_Prev != null) {
            this.m_Prev.m_Next = null;
            if (this.m_Parent != null && this.m_Parent.m_LastChild == this) {
                this.m_Parent.m_LastChild = this.m_Prev;
            }
        }
        else if (this.m_Parent != null) {
            if (this.m_Parent.m_FirstChild == this) {
                this.m_Parent.m_FirstChild = null;
            }
            if (this.m_Parent.m_LastChild == this) {
                this.m_Parent.m_LastChild = null;
            }
        }
        this.m_Prev = null;
        this.m_Next = null;
        if (this.m_FirstChild != null) {
            JNode node = this.m_FirstChild;
            while (node != null) {
                final JNode node2 = node;
                node = node.m_Next;
                node2.remove();
            }
            this.m_FirstChild = null;
            this.m_LastChild = null;
        }
        this.m_Parent = null;
        this.m_removed = true;
    }
    
    public boolean getNeedToLoadChildren() {
        TocDebug.TraceL3("Node::getNeedToLoadChildren");
        return this.hasChildren() && (this.m_FirstChild == null || (this.m_FirstChild.isLoading() && this.m_FirstChild.getNextSibling() == null));
    }
    
    public boolean isRemoved() {
        return this.m_removed;
    }
    
    public void setLoading(final String text) {
        if ((this.m_nFlags & 0x100) != 0x0) {
            this.m_nFlags ^= 0x100;
        }
        this.setText(text);
        this.m_nType = 6;
        this.m_nFlags |= 0x40;
    }
    
    public synchronized void setLoading(final boolean b) {
        TocDebug.TraceL3("Node::setLoading");
        if (b != this.isLoading()) {
            this.m_nFlags ^= 0x40;
        }
    }
    
    public JNode getNextSibling() {
        TocDebug.TraceL3("Node::getNextSibling");
        return this.m_Next;
    }
    
    int GetType() {
        return this.m_nType;
    }
    
    public void setRoot(final boolean bRoot) {
        this.m_bRoot = bRoot;
    }
    
    synchronized Image getClosedImage(final JTreeView treeView) {
        switch (this.m_nType) {
            case 4:
            case 5: {
                int n;
                if (!this.isPresent(false)) {
                    n = 9;
                }
                else if (!this.areChildrenPresent(false)) {
                    n = 6;
                }
                else if (this.hasContent()) {
                    n = 4;
                }
                else {
                    n = 2;
                }
                return (Image)treeView.m_ImageList.elementAt(n);
            }
            default: {
                return this.getOpenImage(treeView);
            }
        }
    }
    
    public Font getFont() {
        if (this.m_Attrib != null) {
            return this.m_Attrib.m_Font;
        }
        return null;
    }
    
    public void setFont(final Font font) {
        TocDebug.TraceL3("Node::setFont");
        if (this.m_Attrib == null) {
            this.m_Attrib = new NodeAttrib();
        }
        this.m_Attrib.m_Font = font;
    }
    
    public void unsetError() {
        if (this.m_bErrorDetected) {
            this.m_nType = this.m_nBeforeErrorType;
            this.m_nFlags = this.m_nBeforeErrorFlags;
            this.m_Text = this.m_BeforeErrorText;
            this.m_bErrorDetected = false;
        }
    }
    
    public void setAllFlags(final int nFlags) {
        this.m_nFlags = nFlags;
    }
    
    public boolean isMore() {
        return (this.m_nFlags & 0x8) != 0x0;
    }
    
    public String getQueryPaths(final boolean b) {
        TocDebug.TraceL3("Node::getQueryPaths");
        boolean b2 = false;
        if (this.isInQuery()) {
            if (b) {
                return this.m_Text;
            }
            return this.m_DocumentName;
        }
        else {
            String s;
            if (b) {
                s = this.m_Text;
            }
            else {
                s = this.m_DocumentName;
            }
            String s2 = s + "(";
            for (JNode node = this.getFirstChild(); node != null; node = node.getNextSibling()) {
                final String queryPaths = node.getQueryPaths(b);
                if (queryPaths != null && queryPaths.length() > 0) {
                    s2 = s2 + queryPaths + ",";
                    b2 = true;
                }
            }
            if (b2) {
                return new String(s2.substring(0, s2.length() - 1)) + ")";
            }
            return null;
        }
    }
    
    public boolean isSubDocument() {
        return this.m_nType == 3;
    }
    
    public boolean canExpand() {
        return this.m_FirstChild != null || this.getNeedToLoadChildren() || (!this.isPresent(false) && this.isRemovable(false));
    }
    
    public void setInQuery(final boolean b) {
        if (b != this.isInQuery()) {
            this.m_nFlags ^= 0x80;
        }
    }
    
    public void setForeground(final Color fColor) {
        TocDebug.TraceL3("Node::setForeground");
        if (this.m_Attrib == null) {
            this.m_Attrib = new NodeAttrib();
        }
        this.m_Attrib.m_fColor = fColor;
    }
    
    public Color getForeground() {
        TocDebug.TraceL3("Node::getForeground");
        if (this.m_Attrib != null) {
            return this.m_Attrib.m_fColor;
        }
        return null;
    }
    
    public void setError(final String text) {
        if (!this.m_bErrorDetected) {
            this.m_nBeforeErrorType = this.m_nType;
            this.m_nBeforeErrorFlags = this.m_nFlags;
            this.m_BeforeErrorText = this.m_Text;
        }
        this.setText(text);
        this.m_nType = 6;
        this.m_nFlags |= 0x100;
        this.m_bErrorDetected = true;
    }
    
    public boolean isRemovable(final boolean b) {
        if ((this.m_nFlags & 0x20) != 0x0) {
            TocDebug.Assert(this.areChildrenPresent(false));
            return true;
        }
        return b && this.m_Parent != null && this.m_Parent.isRemovable(b);
    }
    
    public JNode FindChild(String substring) {
        TocDebug.TraceL3("Node::FindChild");
        if (substring != null && substring.length() != 0) {
            String string = "";
            String copyValue = null;
            if (substring.charAt(0) == '/') {
                substring = substring.substring(1);
            }
            for (int i = 0; i < substring.length(); ++i) {
                if (substring.charAt(i) == '\\' || substring.charAt(i) == '/') {
                    copyValue = String.copyValueOf(substring.toCharArray(), i + 1, substring.length() - i - 1);
                    break;
                }
                string += substring.charAt(i);
            }
            JNode node = this.m_FirstChild;
            while (node != null) {
                if (node.m_DocumentName.toUpperCase().compareTo(string.toUpperCase()) == 0) {
                    if (copyValue == null || copyValue.length() <= 0) {
                        return node;
                    }
                    final JNode findChild = node.FindChild(copyValue);
                    if (findChild == null) {
                        return node;
                    }
                    return findChild;
                }
                else {
                    node = node.m_Next;
                }
            }
            return node;
        }
        if (this.m_bRoot) {
            return this;
        }
        return null;
    }
    
    public String getMediaTitles() {
        if (!this.isRemovable(false) && this.m_Parent != null) {
            return this.m_Parent.getMediaTitles();
        }
        return this.m_sMediaTitles;
    }
    
    public void setMediaTitles(final String s) {
        this.m_sMediaTitles = new String(s);
    }
    
    public void SetCheck(final boolean inQuery) {
        TocDebug.TraceL3("Node::SetCheck");
        boolean b = false;
        if ((this.m_Parent != null && !inQuery && this.m_Parent.isInQuery()) || (this.m_nMoreType > -1 && inQuery)) {
            b = true;
        }
        if (!b) {
            this.setInQuery(inQuery);
            for (JNode node = this.m_FirstChild; node != null; node = node.m_Next) {
                node.SetCheck(inQuery);
            }
        }
    }
    
    public boolean areChildrenPresent(final boolean b) {
        if (b && !this.isRemovable(true)) {
            this.remoteIsPresent(false);
        }
        final boolean b2 = (this.m_nFlags & 0x4) == 0x0;
        if (!b2) {
            TocDebug.Assert(this.isPresent(false));
        }
        return b2;
    }
    
    public void setPresent(final boolean b) {
        if (b != this.isPresent(false)) {
            this.m_nFlags ^= 0x2;
        }
    }
    
    public boolean updateIsPresent(final JTOC jtoc, final JTreeView treeView, final String s) {
        TocDebug.TraceL3("Node::checkIsPresent");
        if (this.isRemovable(false)) {
            new IsPresentThread(this, treeView, false).start();
            return true;
        }
        for (JNode node = this.m_FirstChild; node != null && node.updateIsPresent(jtoc, treeView, s); node = node.getNextSibling()) {}
        return true;
    }
    
    JNode getNextParent() {
        if (this.m_Next != null) {
            return this.m_Next;
        }
        if (this.m_Parent != null) {
            return this.m_Parent.getNextParent();
        }
        return null;
    }
    
    public void setExpanded(final boolean expanded) {
        TocDebug.TraceL3("Node::setExpanded");
        this.m_Expanded = expanded;
    }
    
    public boolean getExpanded() {
        TocDebug.TraceL3("Node::getExpanded");
        return this.m_Expanded;
    }
    
    public synchronized boolean isLoading() {
        TocDebug.TraceL3("Node::isLoading");
        return (this.m_nFlags & 0x40) != 0x0;
    }
    
    public JNode getPrevSibling() {
        TocDebug.TraceL3("Node::getPrevSibling");
        return this.m_Prev;
    }
    
    public JNode getNext() {
        TocDebug.TraceL3("Node::getNext");
        if (this.m_FirstChild != null) {
            return this.m_FirstChild;
        }
        if (this.m_Next != null) {
            return this.m_Next;
        }
        if (this.m_Parent != null) {
            return this.m_Parent.getNextParent();
        }
        return null;
    }
    
    public void setMoreType(final int nMoreType) {
        TocDebug.TraceL3("Node::setMoreType");
        this.m_nMoreType = nMoreType;
    }
    
    public int getMoreType() {
        return this.m_nMoreType;
    }
    
    JNode NodeAtLine(final int n, final boolean b) {
        TocDebug.TraceL3("Node::NodeAtLine");
        JNode node = null;
        JNode nextVisible = this;
        for (int n2 = 1; nextVisible != null && n2 < n; nextVisible = nextVisible.getNextVisible(), ++n2) {
            node = nextVisible;
        }
        if (nextVisible == null && !b) {
            return node;
        }
        return nextVisible;
    }
    
    public String getPath(final boolean b, final boolean b2) {
        final String getPath = this._getPath(b, b2);
        if (b2) {
            return TocUtil.percentEncode(getPath);
        }
        return getPath;
    }
    
    public String _getPath(final boolean b, final boolean b2) {
        TocDebug.TraceL3("Node::getPath");
        final String s = new String("");
        String s2;
        if (this.m_Parent != null) {
            s2 = this.m_Parent._getPath(b, b2) + "/";
        }
        else {
            s2 = "/";
        }
        if (!b2 || !this.isSubDocument()) {
            if (!b) {
                s2 += this.m_DocumentName;
            }
            else {
                s2 += this.m_Text;
            }
        }
        if (s2.substring(s2.length() - 1).compareTo(new String("/")) == 0) {
            s2 = new String(s2.substring(0, (s2.length() == 1) ? 1 : (s2.length() - 1)));
        }
        if (s2.length() >= 2 && s2.substring(0, 2).compareTo(new String("//")) == 0) {
            s2 = new String(s2.substring(1, s2.length()));
        }
        return s2;
    }
    
    public boolean isRoot() {
        return this.m_bRoot;
    }
    
    public void setType(final int nType) {
        this.m_nType = nType;
    }
    
    synchronized Image getOpenImage(final JTreeView treeView) {
        int n = -1;
        switch (this.m_nType) {
            case 2:
            case 3: {
                if (this.isPresent(false)) {
                    n = 8;
                    break;
                }
                n = 10;
                break;
            }
            case 4:
            case 5: {
                if (!this.isPresent(false)) {
                    n = 9;
                    break;
                }
                if (!this.areChildrenPresent(false)) {
                    n = 7;
                    break;
                }
                if (this.hasContent()) {
                    n = 5;
                    break;
                }
                n = 3;
                break;
            }
            case 1: {
                n = 1;
                break;
            }
            case 6: {
                if (this.isMore()) {
                    n = 13;
                    break;
                }
                if (this.isError()) {
                    n = 14;
                    break;
                }
                if (this.isLoading()) {
                    n = 12;
                    break;
                }
                break;
            }
            default: {
                TocDebug.Assert(false, "getOpenImage did not find a node type.");
                break;
            }
        }
        return (Image)treeView.m_ImageList.elementAt(n);
    }
    
    public boolean isInQuery() {
        return (this.m_nFlags & 0x80) != 0x0;
    }
    
    public JNode getNextVisible() {
        if (this.m_FirstChild != null && this.m_Expanded) {
            return this.m_FirstChild;
        }
        if (this.m_Next != null) {
            return this.m_Next;
        }
        if (this.m_Parent != null) {
            return this.m_Parent.getNextParent();
        }
        return null;
    }
    
    public void setBackground(final Color bColor) {
        TocDebug.TraceL3("Node::setBackground");
        if (this.m_Attrib == null) {
            this.m_Attrib = new NodeAttrib();
        }
        this.m_Attrib.m_bColor = bColor;
    }
    
    public Color getBackground() {
        TocDebug.TraceL3("Node::getBackground");
        if (this.m_Attrib != null) {
            return this.m_Attrib.m_bColor;
        }
        return null;
    }
    
    JNode(final String text, final int nType, final JNode parent, final int level) {
        this.m_Next = null;
        this.m_Prev = null;
        this.m_FirstChild = null;
        this.m_LastChild = null;
        this.m_Parent = null;
        this.m_bRoot = false;
        this.m_removed = false;
        this.m_nMoreType = -1;
        this.m_nType = 4;
        this.m_nBeforeErrorType = 4;
        this.m_bErrorDetected = false;
        this.m_nFlags = 0;
        this.m_nBeforeErrorFlags = 0;
        this.ROOT_LINE_OFFSET = 15;
        this.ROOT_LINE_LENGTH = 6;
        this.m_Level = -1;
        this.m_Expanded = false;
        this.m_Text = "";
        this.m_BeforeErrorText = "";
        this.m_DocumentName = "";
        this.m_sMediaTitles = "";
        TocDebug.TraceL3("Node::Node2");
        this.m_Text = text;
        this.m_nType = nType;
        this.m_Parent = parent;
        this.m_Level = level;
    }
    
    JNode() {
        this.m_Next = null;
        this.m_Prev = null;
        this.m_FirstChild = null;
        this.m_LastChild = null;
        this.m_Parent = null;
        this.m_bRoot = false;
        this.m_removed = false;
        this.m_nMoreType = -1;
        this.m_nType = 4;
        this.m_nBeforeErrorType = 4;
        this.m_bErrorDetected = false;
        this.m_nFlags = 0;
        this.m_nBeforeErrorFlags = 0;
        this.ROOT_LINE_OFFSET = 15;
        this.ROOT_LINE_LENGTH = 6;
        this.m_Level = -1;
        this.m_Expanded = false;
        this.m_Text = "";
        this.m_BeforeErrorText = "";
        this.m_DocumentName = "";
        this.m_sMediaTitles = "";
        TocDebug.TraceL3("Node::Node");
    }
    
    int GetTypeBeforeError() {
        return this.m_nBeforeErrorType;
    }
    
    public boolean isError() {
        return (this.m_nFlags & 0x100) != 0x0;
    }
    
    public boolean hasContent() {
        return (this.m_nFlags & 0x1) == 0x0;
    }
    
    public boolean isPresent(final boolean b) {
        if (b && this.isRemovable(true)) {
            this.remoteIsPresent(false);
        }
        return (this.m_nFlags & 0x2) == 0x0;
    }
    
    public void expand(final boolean b) {
        TocDebug.TraceL3("Node::expand");
        this.m_Expanded = true;
        if (b) {
            for (JNode node = this.m_FirstChild; node != null; node = node.m_Next) {
                node.expand(b);
            }
        }
        for (JNode node2 = this.m_Parent; node2 != null; node2 = node2.m_Parent) {
            node2.m_Expanded = true;
        }
    }
    
    int paint(final JTreeView treeView, final Graphics graphics, final int n) {
        TocDebug.TraceL3("Node::paint");
        final int n2 = 3 + this.m_Level * treeView.m_Indentation - treeView.m_hscroll.getValue();
        final int n3 = n * treeView.m_LineHeight;
        final int n4 = 7 - treeView.m_hscroll.getValue();
        graphics.setColor(treeView.getBackground());
        graphics.fillRect(0, n3 - treeView.m_LineHeight + 1, treeView.m_Size.width, treeView.m_LineHeight);
        if (treeView.m_ShowLines) {
            final int n5 = 0;
            if (this.m_removed) {
                return 0;
            }
            int n6;
            if (treeView.m_ShowImages) {
                n6 = 15;
            }
            else {
                n6 = 9;
            }
            graphics.setColor(treeView.m_LineColor);
            for (int i = 0; i <= this.m_Level; ++i) {
                int j = this.m_Level;
                JNode parent = this;
                while (j > i) {
                    parent = parent.m_Parent;
                    --j;
                }
                if (i != 0 || treeView.m_LinesAtRoot) {
                    if (this.m_Level == 0 && this.m_Prev == null && this.m_bRoot) {
                        graphics.drawLine(n4, this.ROOT_LINE_OFFSET, n4, this.ROOT_LINE_OFFSET + this.ROOT_LINE_LENGTH);
                    }
                    else if (parent.m_Next == null && i == this.m_Level) {
                        graphics.drawLine(i * treeView.m_Indentation + n4, n3 - treeView.m_LineHeight - n5, i * treeView.m_Indentation + n4, n3 - 6);
                    }
                    else if (parent.m_Next != null) {
                        graphics.drawLine(i * treeView.m_Indentation + n4, n3 - treeView.m_LineHeight - n5, i * treeView.m_Indentation + n4, n3);
                    }
                }
            }
            if ((this.m_Level != 0 || treeView.m_LinesAtRoot) && !this.m_bRoot) {
                graphics.drawLine(this.m_Level * treeView.m_Indentation + n4, n3 - treeView.m_LineHeight / 2 + 2, this.m_Level * treeView.m_Indentation + n4 + n6, n3 - treeView.m_LineHeight / 2 + 2);
            }
        }
        if (this.m_FirstChild != null || this.getNeedToLoadChildren() || (!this.isPresent(false) && this.isRemovable(false))) {
            if (this.isPresent(false)) {
                if (this.m_Expanded) {
                    graphics.drawImage(treeView.m_MinusImage, n2, n3 - 11, treeView);
                }
                else {
                    graphics.drawImage(treeView.m_PlusImage, n2, n3 - 11, treeView);
                }
            }
            else {
                graphics.drawImage(treeView.m_PlusImage, n2, n3 - 11, treeView);
            }
        }
        int n7 = n2 + (treeView.m_MinusImage.getWidth(treeView) + 5);
        if (treeView.m_ShowImages) {
            Image image;
            if (this.m_Expanded) {
                image = this.getOpenImage(treeView);
            }
            else {
                image = this.getClosedImage(treeView);
            }
            if (image != null) {
                if (this.m_bRoot) {
                    graphics.drawImage(image, n7 - 2, n3 - treeView.m_LineHeight, treeView);
                }
                else {
                    graphics.drawImage(image, n7, n3 - 15, treeView);
                }
                n7 += 21;
            }
        }
        final Font font = graphics.getFont();
        final Font font2 = this.getFont();
        if (font2 == null) {
            if (this.m_FirstChild != null && treeView.m_BranchFont != null) {
                graphics.setFont(treeView.m_BranchFont);
            }
        }
        else {
            graphics.setFont(font2);
        }
        if (treeView.m_bShowChecks && this.m_nMoreType == -1) {
            graphics.setColor(treeView.m_LineColor);
            graphics.drawRect(n7 - 1, n3 - treeView.m_LineHeight + 3, 10, 10);
            if (this.isInQuery()) {
                graphics.drawImage(treeView.m_ImageList.elementAt(0), n7 + 1, n3 - treeView.m_LineHeight + 5, treeView);
            }
            n7 += 15;
        }
        if (treeView.m_Font != null) {
            graphics.setFont(treeView.m_Font);
        }
        if (treeView.m_SelectedNode == this) {
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            int n8;
            if (this.m_nMoreType > -1) {
                n8 = fontMetrics.stringWidth(treeView.m_MoreText);
            }
            else {
                n8 = fontMetrics.stringWidth(this.m_Text);
            }
            graphics.setColor(treeView.m_SelBackColor);
            graphics.fillRect(n7 - 3, n3 - treeView.m_LineHeight + 1, n8 + 6, treeView.m_LineHeight);
            new DotRect().draw(graphics, Color.white, Color.black, new Rectangle(n7 - 3, n3 - treeView.m_LineHeight + 1, n8 + 6, treeView.m_LineHeight - 1), 0);
            graphics.setColor(treeView.m_SelTextColor);
            if (this.m_nMoreType > -1) {
                graphics.drawString(treeView.m_MoreText, n7, n3 - 2);
            }
            else {
                graphics.drawString(this.m_Text, n7, n3 - 2);
            }
        }
        else {
            final Color background = treeView.getBackground();
            final int stringWidth = graphics.getFontMetrics().stringWidth(this.m_Text);
            if (background != null) {
                graphics.setColor(background);
                graphics.fillRect(n7 - 3, n3 - treeView.m_LineHeight + 1, stringWidth + 6, treeView.m_LineHeight);
            }
            if (treeView.m_MouseOverNode == this) {
                graphics.setColor(treeView.m_MouseOverColor);
            }
            else {
                graphics.setColor(treeView.getForeground());
            }
            if (this.m_nMoreType > -1) {
                graphics.drawString(treeView.m_MoreText, n7, n3 - 2);
            }
            else {
                graphics.drawString(this.m_Text, n7, n3 - 2);
            }
        }
        graphics.setFont(font);
        return n;
    }
    
    public JNode getParent() {
        TocDebug.TraceL3("Node::getParent");
        return this.m_Parent;
    }
    
    public JNode getPrevVisible() {
        TocDebug.TraceL3("Node::getPrevVisible");
        if (this.m_Prev != null) {
            return this.m_Prev.getLastVisible(this);
        }
        return this.m_Parent;
    }
    
    JNode getLastVisible(final JNode node) {
        TocDebug.TraceL3("Node::getLastVisible");
        if (!this.m_Expanded) {
            return this;
        }
        if (this.m_LastChild == null) {
            return this;
        }
        if (this.m_LastChild == node) {
            return null;
        }
        return this.m_LastChild.getLastVisible(node);
    }
    
    public JNode getFirstChild() {
        TocDebug.TraceL3("Node::getChild");
        return this.m_FirstChild;
    }
    
    public void toggle(final boolean b) {
        TocDebug.TraceL3("Node::toggle");
        if (this.m_Expanded) {
            this.m_Expanded = false;
        }
        else {
            this.m_Expanded = true;
        }
        if (b) {
            for (JNode node = this.m_FirstChild; node != null; node = node.m_Next) {
                node.toggle(b);
            }
        }
    }
    
    void removeChildren() {
        while (this.m_FirstChild != null) {
            this.m_FirstChild.remove();
        }
    }
    
    public boolean hasChildren() {
        return (this.m_nFlags & 0x10) != 0x0;
    }
    
    private void recursiveSetChildrenPresentFlag() {
        for (JNode node = this.m_FirstChild; node != null; node = node.getNextSibling()) {
            if (this.isRemovable(true)) {
                node.setPresent(this.isPresent(false));
            }
            else {
                node.setChildrenPresent(this.areChildrenPresent(false));
            }
            node.recursiveSetChildrenPresentFlag();
        }
    }
    
    public String getSubPath(final boolean b, final boolean b2) {
        final String getSubPath = this._getSubPath(b);
        if (b2) {
            return TocUtil.percentEncode(getSubPath);
        }
        return getSubPath;
    }
    
    public String _getSubPath(final boolean b) {
        String s = new String("");
        if (this.isSubDocument()) {
            if (this.m_Parent != null && this.m_Parent.isSubDocument()) {
                if (b) {
                    s = this.m_Parent._getSubPath(b) + ".";
                }
                else {
                    s = this.m_Parent._getSubPath(b) + "/";
                }
            }
            s += this.m_DocumentName;
        }
        return s;
    }
    
    public String getText() {
        TocDebug.TraceL3("Node::getText");
        return this.m_Text;
    }
    
    public void setText(final String text) {
        TocDebug.TraceL3("Node::setText");
        this.m_Text = text;
    }
}
