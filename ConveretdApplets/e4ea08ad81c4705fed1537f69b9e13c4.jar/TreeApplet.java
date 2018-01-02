import java.awt.Event;
import netscape.javascript.JSObject;
import Sirius.tree.Areadata;
import java.awt.Insets;
import Sirius.tree.Treeimage;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.MediaTracker;
import Sirius.tree.Tree;
import java.net.URLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Enumeration;
import Sirius.tree.Treenode;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Scrollbar;
import Sirius.tree.SIRpoint;
import java.awt.Image;
import java.awt.Font;
import java.util.Vector;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TreeApplet extends Applet implements MouseMotionListener, MouseListener
{
    private String startmessage;
    private Color startbgcolor;
    private Color starttxtcolor;
    private Vector tree;
    private Vector nodedata;
    private Vector imagedata;
    private Vector areadata;
    private Color t_bgcolor;
    private int t_nodegap;
    private boolean t_connlines;
    private Color t_connlinescolor;
    private int t_connlinesstyle;
    private boolean t_smoothscroll;
    private int t_scrolljumpsize;
    private int t_scrollspeed;
    private boolean t_snem;
    private Color t_hightxtcolor;
    private Color t_highbgcolor;
    private int t_nodeoffset;
    private String t_title;
    private Font t_titlefont;
    private Color t_titlecolor;
    private int t_titleheight;
    private int t_borderwidth;
    private Color t_bordercolor;
    private boolean t_statusbar;
    private Font t_statusbarfont;
    private Color t_statusbarbgcolor;
    private Color t_statusbartxtcolor;
    private int t_statusbarheight;
    private boolean t_underlineexpanded;
    private boolean t_underlinelinks;
    private Color t_scrollbarbgcolor;
    private Image t_bgimage;
    private SIRpoint t_bgimageposition;
    private boolean t_highltlastclicked;
    private int nodeindex;
    private String nodeid;
    private String parentid;
    private String nodetext;
    private Font nodefont;
    private Color nodecolor;
    private boolean expanded;
    private boolean mouseover;
    private Vector submenu;
    private String nodeurl;
    private String nodetarget;
    private int nodeheight;
    private int nodewidth;
    private String nodestatustext;
    private boolean nodedisplaystatus;
    private Image normalimage;
    private Image mouseoverimage;
    private Image expandedimage;
    private boolean highlight;
    Scrollbar vscrollbar;
    Scrollbar hscrollbar;
    private int i;
    private String sTemp;
    private String sTemp2;
    private StringTokenizer st;
    private boolean started;
    private static String statusbartext;
    private Image SIRbuffer;
    private static Graphics pad;
    private static FontMetrics fontMetrics;
    private static int titleypos;
    private static int j;
    private static int xTemp;
    private static int yTemp;
    private static int xoffset;
    private static int yoffset;
    private static int totalwidth;
    private static int totalheight;
    private static int displaywidth;
    private static int displayheight;
    private static int saveypos;
    private static ImageObserver Iobs;
    private static int lastclickindex;
    private boolean first;
    
    public void Addnode(final String s) {
        this.create_node(s);
        this.tree = getsubmenu(this.nodedata, "root");
        this.paint(this.getGraphics());
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void Delnode(final String s) {
        final Enumeration elements = this.nodedata.elements();
        this.i = 0;
        boolean b = false;
        while (elements.hasMoreElements() && !b) {
            if (elements.nextElement().getNodeid().equals(s)) {
                this.nodedata.removeElementAt(this.i);
                b = true;
            }
            ++this.i;
        }
        this.tree = getsubmenu(this.nodedata, "root");
        this.paint(this.getGraphics());
    }
    
    public void ReloadParams() {
        this.get_nodedata();
        this.tree = getsubmenu(this.nodedata, "root");
        TreeApplet.yoffset = 0;
        TreeApplet.xoffset = 0;
        try {
            if (this.vscrollbar.isVisible()) {
                this.vscrollbar.setValue(0);
            }
            if (this.hscrollbar.isVisible()) {
                this.hscrollbar.setValue(0);
            }
        }
        catch (Exception ex) {}
        this.paint(this.getGraphics());
    }
    
    public static String verifystr(final String s, final String s2) {
        String s3;
        if (s == null || s.equals("")) {
            s3 = s2;
        }
        else {
            s3 = s;
        }
        return s3;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void start() {
        if (!this.started) {
            this.addMouseMotionListener(this);
            this.addMouseListener(this);
            this.startmessage = verifystr(this.getParameter("startupmessage"), "Loading data, please wait....");
            this.startbgcolor = verifyclr(this.getParameter("startBGcolor"), "255,255,255", ",");
            this.starttxtcolor = verifyclr(this.getParameter("startTXTcolor"), "75,75,75", ",");
            this.paint(this.getGraphics());
            this.get_images();
            this.sTemp = this.getParameter("Nodedata");
            if (this.sTemp != "" && this.sTemp != null) {
                this.get_nodedatafromfile(this.sTemp);
            }
            else {
                this.get_nodedata();
            }
            this.get_treedata();
            this.tree = getsubmenu(this.nodedata, "root");
            this.setLayout(new BorderLayout());
            (this.vscrollbar = new Scrollbar(1, 0, 1, 0, 100)).setVisibleAmount(50);
            this.vscrollbar.setBlockIncrement(25);
            this.vscrollbar.setUnitIncrement(5);
            this.vscrollbar.setBackground(this.t_scrollbarbgcolor);
            this.vscrollbar.setVisible(true);
            this.add("East", this.vscrollbar);
            (this.hscrollbar = new Scrollbar(0, 0, 1, 0, 100)).setVisibleAmount(50);
            this.hscrollbar.setBlockIncrement(25);
            this.hscrollbar.setUnitIncrement(5);
            this.hscrollbar.setBackground(this.t_scrollbarbgcolor);
            this.hscrollbar.setVisible(true);
            this.add("South", this.hscrollbar);
        }
        this.started = true;
    }
    
    private void get_nodedatafromfile(final String s) {
        URL url = null;
        this.nodedata = new Vector();
        try {
            url = new URL(this.getCodeBase(), s);
        }
        catch (MalformedURLException ex2) {
            System.out.println("Bad URL for File Location : " + s);
        }
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.connect();
            while ((this.sTemp = new DataInputStream(new BufferedInputStream(openConnection.getInputStream())).readLine()) != null) {
                if (!this.sTemp.startsWith("<!-")) {
                    this.create_node(this.sTemp);
                }
            }
        }
        catch (IOException ex) {
            System.out.println("IO Error:" + ex.getMessage());
        }
    }
    
    public static SIRpoint DrawMenu(final Image image, final boolean b, final Color color, final int n, final int n2, final boolean b2, final boolean b3, int n3, int y, final int n4, int nodeindex, Treenode treenode, final Color color2, final Color color3, final Color color4, SIRpoint siRpoint, final Vector vector, final Vector vector2) {
        (TreeApplet.pad = image.getGraphics()).setFont(treenode.getNodefont());
        final int n5 = TreeApplet.pad.getFontMetrics().getHeight() + 4;
        Image image2 = treenode.getNormalimage();
        if (treenode.getExpanded()) {
            image2 = treenode.getExpandedimage();
        }
        if (treenode.getMouseover() && treenode.getMouseoverimage() != null) {
            image2 = treenode.getMouseoverimage();
        }
        int n6 = y + n5 / 2 + image2.getHeight(TreeApplet.Iobs) / 2;
        y = y + treenode.getNodeheight() + n2;
        boolean b4 = false;
        if (treenode.getTreenode() != null && treenode.getTreenode().elements().hasMoreElements()) {
            b4 = true;
        }
        siRpoint = Drawnode(image, nodeindex, color2, color3, image2, n3, y, b, color, n, n4, b4, color4, b2, b3, siRpoint, vector, treenode);
        siRpoint.getY();
        if (treenode.getTreenode() != null && treenode.getExpanded()) {
            n3 += n4;
            final Enumeration<Tree> elements = treenode.getTreenode().elements();
            while (elements.hasMoreElements()) {
                nodeindex = elements.nextElement().getNodeindex();
                treenode = vector2.elementAt(nodeindex);
                TreeApplet.pad.setFont(treenode.getNodefont());
                final int n7 = TreeApplet.pad.getFontMetrics().getHeight() + 4;
                int n8;
                if (treenode.getTreenode().elements().hasMoreElements()) {
                    n8 = y + n7 - n7 / 2 - 4 + n2;
                }
                else {
                    n8 = y + n7 - n7 / 2 + n2;
                }
                if (b) {
                    drawline(image, n3 - n4 / 2, n6, 0, n8 - n6, color, n);
                }
                if (treenode.getTreenode().elements().hasMoreElements()) {
                    n6 = y + n7 - n7 / 2 + 5 + n2;
                }
                else {
                    n6 = y + n7 - n7 / 2 + n2;
                }
                siRpoint = DrawMenu(image, b, color, n, n2, b2, b3, n3, y, n4, nodeindex, treenode, color2, color3, color4, siRpoint, vector, vector2);
                y = siRpoint.getY();
            }
            n3 -= n4;
        }
        return siRpoint;
    }
    
    public static Vector getsubmenu(final Vector vector, final String s) {
        final Enumeration<Treenode> elements = vector.elements();
        final Vector<Tree> vector2 = new Vector<Tree>();
        int n = 0;
        while (elements.hasMoreElements()) {
            final Treenode treenode = elements.nextElement();
            if (treenode.getParentid().equals(s)) {
                final Vector getsubmenu = getsubmenu(vector, treenode.getNodeid());
                final Treenode treenode2 = vector.elementAt(n);
                treenode2.setTreenode(getsubmenu);
                vector.setElementAt(treenode2, n);
                vector2.addElement(new Tree(n));
            }
            ++n;
        }
        return vector2;
    }
    
    public Image downloadImage(final String s) {
        if (s == null || s.equals(" ") || s.equals("")) {
            return null;
        }
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image image = this.getImage(this.getCodeBase(), s);
        mediaTracker.addImage(image, 0);
        try {
            this.showStatus("Loading Image..." + s);
            mediaTracker.waitForID(0);
            this.showStatus("");
        }
        catch (InterruptedException ex) {
            return this.createImage(this.size().width, this.size().height);
        }
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex2) {
            System.err.println("interrupted waiting for pixels!");
            this.showStatus("Image loading error:" + s);
            return this.createImage(this.size().width, this.size().height);
        }
        if ((pixelGrabber.status() & 0x80) != 0x0) {
            System.err.println("image fetch aborted or errored:" + s);
            this.showStatus("Image loading error");
            return this.createImage(this.size().width, this.size().height);
        }
        return this.createImage(new MemoryImageSource(width, height, array, 0, width));
    }
    
    public static Image getimage(final String s, final Vector vector) {
        final Enumeration<Treeimage> elements = vector.elements();
        Image image = null;
        for (boolean b = false; elements.hasMoreElements() && !b; b = true) {
            final Treeimage treeimage = elements.nextElement();
            if (s.equals(treeimage.getImagename())) {
                image = treeimage.getImage();
            }
        }
        return image;
    }
    
    public Insets insets() {
        return new Insets(this.t_borderwidth, this.t_borderwidth, this.t_borderwidth, this.t_borderwidth);
    }
    
    private void create_node(final String s) {
        this.st = new StringTokenizer(s, "|");
        if (this.st.hasMoreTokens()) {
            this.nodeid = this.st.nextToken();
        }
        else {
            this.nodeid = "";
        }
        if (this.st.hasMoreTokens()) {
            this.parentid = this.st.nextToken();
        }
        else {
            this.parentid = "";
        }
        if (this.st.hasMoreTokens()) {
            this.nodetext = this.st.nextToken();
        }
        else {
            this.nodetext = "";
        }
        this.sTemp2 = "";
        if (this.st.hasMoreTokens()) {
            this.sTemp2 += this.st.nextToken();
        }
        else {
            this.sTemp2 += "Courier";
        }
        if (this.st.hasMoreTokens()) {
            this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
        }
        else {
            this.sTemp2 = this.sTemp2 + "|" + "N";
        }
        if (this.st.hasMoreTokens()) {
            this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
        }
        else {
            this.sTemp2 = this.sTemp2 + "|" + "12";
        }
        this.nodefont = verifyfnt(this.sTemp2, "Courier|N|10", "|");
        this.sTemp2 = "";
        if (this.st.hasMoreTokens()) {
            this.sTemp2 += this.st.nextToken();
        }
        else {
            this.sTemp2 += "0";
        }
        if (this.st.hasMoreTokens()) {
            this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
        }
        else {
            this.sTemp2 = this.sTemp2 + "|" + "0";
        }
        if (this.st.hasMoreTokens()) {
            this.sTemp2 = this.sTemp2 + "|" + this.st.nextToken();
        }
        else {
            this.sTemp2 = this.sTemp2 + "|" + "0";
        }
        this.nodecolor = verifyclr(this.sTemp2, "0|0|0", "|");
        if (this.st.hasMoreTokens()) {
            this.expanded = verifybool(this.st.nextToken(), "false");
        }
        else {
            this.expanded = false;
        }
        this.mouseover = false;
        if (this.st.hasMoreTokens()) {
            this.normalimage = getimage(this.st.nextToken(), this.imagedata);
        }
        else {
            this.normalimage = null;
        }
        if (this.st.hasMoreTokens()) {
            this.expandedimage = getimage(this.st.nextToken(), this.imagedata);
        }
        else {
            this.expandedimage = null;
        }
        if (this.st.hasMoreTokens()) {
            this.mouseoverimage = getimage(this.st.nextToken(), this.imagedata);
        }
        else {
            this.mouseoverimage = null;
        }
        if (this.st.hasMoreTokens()) {
            this.nodeurl = this.st.nextToken();
        }
        else {
            this.nodeurl = "";
        }
        if (this.st.hasMoreTokens()) {
            this.nodetarget = this.st.nextToken();
        }
        else {
            this.nodetarget = "";
        }
        if (this.st.hasMoreTokens()) {
            this.nodestatustext = this.st.nextToken();
        }
        else {
            this.nodestatustext = "";
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.nodefont);
        this.st = new StringTokenizer(this.nodetext, "~");
        this.nodeheight = (fontMetrics.getHeight() + 4) * this.st.countTokens();
        this.nodedata.addElement(new Treenode(this.nodeid, this.parentid, this.nodetext, this.nodefont, this.nodecolor, this.expanded, this.mouseover, this.nodeurl, this.nodetarget, this.nodeheight, 0, this.nodestatustext, false, this.normalimage, this.mouseoverimage, this.expandedimage, null, false));
    }
    
    private void get_treedata() {
        this.t_bgcolor = verifyclr(this.getParameter("backgroundColor"), "255|255|255", "|");
        this.t_nodegap = verifyint(this.getParameter("nodegap"));
        this.t_connlines = verifybool(this.getParameter("connectingLines"), "true");
        this.t_connlinescolor = verifyclr(this.getParameter("connectingLinesColor"), "100|100|100", "|");
        this.t_connlinesstyle = verifyint(this.getParameter("connectingLinesStyle"));
        this.t_smoothscroll = verifybool(this.getParameter("smoothScroll"), "true");
        this.t_scrolljumpsize = verifyint(this.getParameter("scrollJumpSize"));
        this.t_scrollspeed = verifyint(this.getParameter("scrollSpeed"));
        this.t_snem = verifybool(this.getParameter("singleNodeExapansionMode"), "false");
        this.t_hightxtcolor = verifyclr(this.getParameter("highTextColor"), "255|255|255", "|");
        this.t_highbgcolor = verifyclr(this.getParameter("highBgColor"), "0|0|0", "|");
        this.t_nodeoffset = verifyint(this.getParameter("nodeOffset"));
        this.t_title = verifystr(this.getParameter("title"), "");
        this.t_titlefont = verifyfnt(this.getParameter("titleFont"), "Courier|N|12", "|");
        this.t_titlecolor = verifyclr(this.getParameter("titleColor"), "0|0|0", "|");
        this.t_borderwidth = verifyint(this.getParameter("borderWidth"));
        this.t_bordercolor = verifyclr(this.getParameter("borderColor"), "200|200|200", "|");
        this.t_statusbar = verifybool(this.getParameter("statusBar"), "false");
        this.t_statusbarfont = verifyfnt(this.getParameter("statusBarFont"), "Courier|N|10", "|");
        this.t_statusbarbgcolor = verifyclr(this.getParameter("statusBarBgColor"), "200|200|200", "|");
        this.t_statusbartxtcolor = verifyclr(this.getParameter("statusBarTextColor"), "0|0|255", "|");
        this.t_statusbarheight = verifyint(this.getParameter("statusBarHeight"));
        if (this.t_statusbarheight == 0) {
            this.t_statusbarheight = 15;
        }
        this.t_underlineexpanded = verifybool(this.getParameter("underlineExpanded"), "false");
        this.t_underlinelinks = verifybool(this.getParameter("underlineLinks"), "false");
        this.t_scrollbarbgcolor = verifyclr(this.getParameter("scrollBarBgColor"), "150|150|150", "|");
        this.t_highltlastclicked = verifybool(this.getParameter("highlightLastNodeClicked"), "true");
        this.sTemp = this.getParameter("BgImage");
        this.t_bgimage = null;
        if (this.sTemp != "" & this.sTemp != null) {
            this.t_bgimage = this.downloadImage(this.sTemp);
        }
        this.sTemp = this.getParameter("BgImagePosition");
        int verifyint = 0;
        int verifyint2 = 0;
        if (this.sTemp != "" & this.sTemp != null) {
            this.st = new StringTokenizer(this.sTemp, ",");
            if (this.st.hasMoreTokens()) {
                verifyint = verifyint(this.st.nextToken());
            }
            if (this.st.hasMoreTokens()) {
                verifyint2 = verifyint(this.st.nextToken());
            }
        }
        this.t_bgimageposition = new SIRpoint(verifyint, verifyint2);
        this.t_titleheight = this.getFontMetrics(this.t_titlefont).getHeight();
        if (this.t_nodeoffset == 0) {
            this.t_nodeoffset = 20;
        }
    }
    
    public static boolean verifybool(String substring, final String s) {
        boolean b = false;
        if (substring == null || substring.length() < 1) {
            substring = s;
        }
        substring = substring.toLowerCase().substring(0, 1);
        if (substring.equals("t")) {
            b = true;
        }
        return b;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Enumeration<Treenode> elements = this.nodedata.elements();
        final Enumeration<Areadata> elements2 = this.areadata.elements();
        this.i = 0;
        while (elements.hasMoreElements()) {
            final Treenode treenode = elements.nextElement();
            if (treenode.getHighlight()) {
                treenode.setHighlight(false);
                treenode.setMouseover(false);
                this.nodedata.setElementAt(treenode, this.i);
            }
            ++this.i;
        }
        while (elements2.hasMoreElements()) {
            final Areadata areadata = elements2.nextElement();
            if (mouseEvent.getX() > areadata.getLeftx() && mouseEvent.getX() < areadata.getRightx() && mouseEvent.getY() > areadata.getTopy() && mouseEvent.getY() < areadata.getBottomy()) {
                final Treenode treenode2 = this.nodedata.elementAt(areadata.getNodeindex());
                switch (areadata.getAction()) {
                    default: {
                        continue;
                    }
                    case 2: {
                        if (this.t_snem && treenode2.getParentid().equals("root")) {
                            int n = 0;
                            final Enumeration<Treenode> elements3 = this.nodedata.elements();
                            while (elements3.hasMoreElements()) {
                                final Treenode treenode3 = elements3.nextElement();
                                if (treenode3.getParentid().equals("root") && n != areadata.getNodeindex()) {
                                    treenode3.setExpanded(false);
                                    this.nodedata.setElementAt(treenode3, n);
                                }
                                ++n;
                            }
                        }
                        final Treenode treenode4 = this.nodedata.elementAt(areadata.getNodeindex());
                        if (treenode4.getExpanded()) {
                            treenode4.setExpanded(false);
                        }
                        else {
                            treenode4.setExpanded(true);
                        }
                        this.nodedata.setElementAt(treenode4, areadata.getNodeindex());
                        continue;
                    }
                    case 3: {
                        if (this.t_highltlastclicked) {
                            TreeApplet.lastclickindex = areadata.getNodeindex();
                            treenode2.setHighlight(true);
                        }
                        try {
                            this.getAppletContext().showDocument(new URL(this.getDocumentBase(), treenode2.getNodeurl()), treenode2.getNodetarget());
                        }
                        catch (MalformedURLException ex) {
                            ex.printStackTrace();
                        }
                        continue;
                    }
                    case 4: {
                        if (this.t_highltlastclicked) {
                            TreeApplet.lastclickindex = areadata.getNodeindex();
                            treenode2.setHighlight(true);
                        }
                        try {
                            JSObject.getWindow(this).eval(treenode2.getNodeurl());
                        }
                        catch (Throwable t) {
                            try {
                                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), "javascript:" + treenode2.getNodeurl()));
                            }
                            catch (MalformedURLException ex2) {
                                ex2.printStackTrace();
                            }
                        }
                        continue;
                    }
                }
            }
        }
        this.paint(this.getGraphics());
    }
    
    public TreeApplet() {
        this.startmessage = "Loading data - please wait....";
        this.startbgcolor = new Color(255, 255, 255);
        this.starttxtcolor = new Color(75, 75, 75);
        this.tree = null;
        this.nodedata = null;
        this.imagedata = null;
        this.areadata = null;
        this.t_bgcolor = new Color(100, 100, 100);
        this.t_nodegap = 0;
        this.t_connlines = true;
        this.t_connlinescolor = new Color(100, 100, 100);
        this.t_connlinesstyle = 0;
        this.t_smoothscroll = true;
        this.t_scrolljumpsize = 2;
        this.t_scrollspeed = 0;
        this.t_snem = false;
        this.t_hightxtcolor = new Color(255, 255, 255);
        this.t_highbgcolor = new Color(0, 0, 0);
        this.t_nodeoffset = 10;
        this.t_title = null;
        this.t_titlefont = new Font("Courier", 0, 12);
        this.t_titlecolor = new Color(0, 0, 0);
        this.t_titleheight = 15;
        this.t_borderwidth = 0;
        this.t_bordercolor = new Color(200, 200, 200);
        this.t_statusbar = false;
        this.t_statusbarfont = new Font("Courier", 0, 10);
        this.t_statusbarbgcolor = new Color(200, 200, 200);
        this.t_statusbartxtcolor = new Color(0, 0, 255);
        this.t_statusbarheight = 20;
        this.t_underlineexpanded = false;
        this.t_underlinelinks = false;
        this.t_scrollbarbgcolor = new Color(150, 150, 150);
        this.t_bgimage = null;
        this.t_bgimageposition = null;
        this.t_highltlastclicked = true;
        this.started = false;
        this.first = true;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        boolean b = false;
        final Enumeration<Treenode> elements = this.nodedata.elements();
        final Enumeration<Areadata> elements2 = this.areadata.elements();
        this.i = 0;
        while (elements.hasMoreElements()) {
            final Treenode treenode = elements.nextElement();
            if (treenode.getHighlight() && (!this.t_highltlastclicked || TreeApplet.lastclickindex != this.i)) {
                b = true;
                treenode.setHighlight(false);
                treenode.setMouseover(false);
                this.nodedata.setElementAt(treenode, this.i);
            }
            ++this.i;
        }
        if (!TreeApplet.statusbartext.equals("")) {
            TreeApplet.statusbartext = "";
            b = true;
        }
        while (elements2.hasMoreElements()) {
            final Areadata areadata = elements2.nextElement();
            if (mouseEvent.getX() > areadata.getLeftx() && mouseEvent.getX() < areadata.getRightx() && mouseEvent.getY() > areadata.getTopy() && mouseEvent.getY() < areadata.getBottomy()) {
                final Treenode treenode2 = this.nodedata.elementAt(areadata.getNodeindex());
                switch (areadata.getAction()) {
                    default: {
                        continue;
                    }
                    case 1: {
                        treenode2.setHighlight(true);
                        treenode2.setMouseover(true);
                        this.nodedata.setElementAt(treenode2, areadata.getNodeindex());
                        b = true;
                        continue;
                    }
                    case 5: {
                        TreeApplet.statusbartext = treenode2.getNodestatustext();
                        b = true;
                        continue;
                    }
                }
            }
        }
        if (b) {
            this.paint(this.getGraphics());
        }
    }
    
    public void paint(final Graphics graphics) {
        this.SIRbuffer = this.createImage(this.getSize().width, this.getSize().height);
        TreeApplet.pad = this.SIRbuffer.getGraphics();
        if (this.started) {
            this.areadata = new Vector();
            this.insets();
            final int n = 2 + this.t_borderwidth;
            int n2 = 2 + this.t_borderwidth;
            final int visibleAmount = this.getSize().width - 2 - 2 * this.t_borderwidth;
            int visibleAmount2 = this.getSize().height - 2 - 2 * this.t_borderwidth;
            TreeApplet.pad.setColor(this.t_bgcolor);
            TreeApplet.pad.fillRect(0, 0, this.getSize().width, this.getSize().height);
            if (this.t_bgimage != null) {
                if (this.t_bgimageposition == null) {
                    this.t_bgimageposition = new SIRpoint(0, 0);
                }
                TreeApplet.pad.drawImage(this.t_bgimage, this.t_bgimageposition.getX(), this.t_bgimageposition.getY(), TreeApplet.Iobs);
            }
            if (!this.t_title.equals("")) {
                TreeApplet.fontMetrics = this.getFontMetrics(this.t_titlefont);
                n2 = (TreeApplet.titleypos = n2 + this.t_titleheight);
                TreeApplet.totalheight += this.t_titleheight;
                visibleAmount2 -= this.t_titleheight;
            }
            final Enumeration<Tree> elements = this.tree.elements();
            int y = n2 - TreeApplet.yoffset;
            final int n3 = n - TreeApplet.xoffset;
            int n4 = y;
            int n5 = y;
            final int n6 = y;
            final int n7 = n3;
            SIRpoint drawMenu = new SIRpoint(n3, y);
            int n8 = 1;
            while (elements.hasMoreElements()) {
                this.nodeindex = elements.nextElement().getNodeindex();
                final Treenode treenode = this.nodedata.elementAt(this.nodeindex);
                TreeApplet.pad.setFont(treenode.getNodefont());
                final int n9 = TreeApplet.pad.getFontMetrics().getHeight() + 4;
                if (treenode.getTreenode() != null) {
                    if (treenode.getTreenode().elements().hasMoreElements()) {
                        n5 = y + n9 - n9 / 2 - 4 + this.t_nodegap;
                    }
                    else {
                        n5 = y + n9 - n9 / 2 + this.t_nodegap;
                    }
                }
                if (this.t_connlines && n8 == 0) {
                    drawline(this.SIRbuffer, n3 + this.t_nodeoffset - this.t_nodeoffset / 2, n4, 0, n5 - n4, this.t_connlinescolor, this.t_connlinesstyle);
                }
                if (treenode.getTreenode() != null) {
                    if (treenode.getTreenode().elements().hasMoreElements()) {
                        n4 = y + n9 - n9 / 2 + 5 + this.t_nodegap;
                    }
                    else {
                        n4 = y + n9 - n9 / 2 + this.t_nodegap;
                    }
                }
                drawMenu = DrawMenu(this.SIRbuffer, this.t_connlines, this.t_connlinescolor, this.t_connlinesstyle, this.t_nodegap, this.t_underlineexpanded, this.t_underlinelinks, n3 + this.t_nodeoffset, y, this.t_nodeoffset, this.nodeindex, treenode, this.t_hightxtcolor, this.t_highbgcolor, this.t_bgcolor, drawMenu, this.areadata, this.nodedata);
                y = drawMenu.getY();
                n8 = 0;
            }
            TreeApplet.totalwidth = drawMenu.getX() - n7;
            TreeApplet.totalheight = y - n6;
            if (this.t_statusbar) {
                int n10 = this.getSize().width - 2 * this.t_borderwidth;
                if (this.vscrollbar.isVisible()) {
                    n10 -= 15;
                }
                int n11 = this.getSize().height - this.t_statusbarheight - this.t_borderwidth;
                if (this.hscrollbar.isVisible()) {
                    n11 -= 15;
                }
                TreeApplet.pad.setColor(this.t_statusbarbgcolor);
                TreeApplet.pad.fillRect(this.t_borderwidth, n11, n10, this.t_statusbarheight);
                TreeApplet.pad.setColor(new Color(50, 50, 50));
                TreeApplet.pad.drawRect(this.t_borderwidth, n11, n10, this.t_statusbarheight);
                TreeApplet.pad.drawRect(this.t_borderwidth + 1, n11 + 1, n10 - 2, this.t_statusbarheight - 2);
                TreeApplet.pad.setColor(new Color(225, 225, 225));
                TreeApplet.pad.drawLine(this.t_borderwidth + n10, n11, this.t_borderwidth + n10, n11 + this.t_statusbarheight);
                TreeApplet.pad.drawLine(this.t_borderwidth + n10 - 1, n11 + 1, this.t_borderwidth + n10 - 1, n11 + this.t_statusbarheight);
                TreeApplet.pad.drawLine(this.t_borderwidth + 1, n11 + this.t_statusbarheight, this.t_borderwidth + n10, n11 + this.t_statusbarheight);
                TreeApplet.pad.drawLine(this.t_borderwidth + 2, n11 + this.t_statusbarheight - 1, this.t_borderwidth + n10, n11 + this.t_statusbarheight - 1);
                TreeApplet.pad.setFont(this.t_statusbarfont);
                TreeApplet.pad.setColor(this.t_statusbartxtcolor);
                TreeApplet.pad.drawString(TreeApplet.statusbartext, this.t_borderwidth + 3, n11 + this.t_statusbarheight - 2);
                visibleAmount2 -= this.t_statusbarheight;
            }
            if (!this.t_title.equals("")) {
                TreeApplet.pad.setColor(this.t_bgcolor);
                TreeApplet.pad.fillRect(this.t_borderwidth, this.t_borderwidth, this.getSize().width - 2 * this.t_borderwidth, this.t_titleheight + 3);
                TreeApplet.fontMetrics = this.getFontMetrics(this.t_titlefont);
                TreeApplet.pad.setColor(this.t_titlecolor);
                TreeApplet.pad.setFont(this.t_titlefont);
                TreeApplet.pad.drawString(this.t_title, (this.getSize().width - 2 * this.t_borderwidth - TreeApplet.fontMetrics.stringWidth(this.t_title)) / 2, TreeApplet.titleypos - 5);
            }
            TreeApplet.pad.setColor(this.t_bordercolor);
            this.i = 0;
            while (this.i < this.t_borderwidth) {
                TreeApplet.pad.drawRect(this.i, this.i, this.getSize().width - 1 - 2 * this.i, this.getSize().height - 1 - 2 * this.i);
                ++this.i;
            }
            if (!this.first) {
                graphics.drawImage(this.SIRbuffer, 0, 0, this);
            }
            this.vscrollbar.setMaximum(TreeApplet.totalheight + 30);
            this.vscrollbar.setVisibleAmount(visibleAmount2);
            this.vscrollbar.setValue(TreeApplet.yoffset);
            if (visibleAmount2 > TreeApplet.totalheight + 15) {
                this.vscrollbar.setVisible(false);
                if (TreeApplet.yoffset != 0) {
                    this.first = true;
                }
                TreeApplet.yoffset = 0;
            }
            else {
                this.vscrollbar.setVisible(true);
                this.vscrollbar.setBackground(this.t_scrollbarbgcolor);
            }
            this.hscrollbar.setMaximum(TreeApplet.totalwidth + 30);
            this.hscrollbar.setVisibleAmount(visibleAmount);
            this.hscrollbar.setValue(TreeApplet.xoffset);
            if (visibleAmount > TreeApplet.totalwidth + 15) {
                this.hscrollbar.setVisible(false);
                if (TreeApplet.xoffset != 0) {
                    this.first = true;
                }
                TreeApplet.xoffset = 0;
            }
            else {
                this.hscrollbar.setVisible(true);
                this.hscrollbar.setBackground(this.t_scrollbarbgcolor);
            }
            if (this.first) {
                this.first = false;
                this.paint(graphics);
            }
        }
        else {
            TreeApplet.pad.setColor(this.startbgcolor);
            TreeApplet.pad.fillRect(0, 0, this.getSize().width, this.getSize().height);
            TreeApplet.pad.setColor(this.starttxtcolor);
            TreeApplet.pad.setFont(new Font("Helvetica", 0, 10));
            TreeApplet.pad.drawString(this.startmessage, 30, 30);
            graphics.drawImage(this.SIRbuffer, 0, 0, this);
        }
    }
    
    public static void drawline(final Image image, final int n, final int n2, final int n3, final int n4, final Color color, final int n5) {
        (TreeApplet.pad = image.getGraphics()).setColor(color);
        switch (n5) {
            case 1: {
                TreeApplet.pad.drawLine(n, n2, n + n3, n2 + n4);
                break;
            }
            case 2: {
                if (n3 == 0) {
                    for (int i = 0; i < n4 / 2; ++i) {
                        TreeApplet.pad.drawLine(n, n2 + i * 2, n, n2 + i * 2);
                    }
                    TreeApplet.pad.drawLine(n, n2 + n4, n, n2 + n4);
                }
                else {
                    for (int j = 0; j < n3 / 2; ++j) {
                        TreeApplet.pad.drawLine(n + j * 2, n2, n + j * 2, n2);
                    }
                    TreeApplet.pad.drawLine(n + n3, n2, n + n3, n2);
                }
                break;
            }
            case 3: {
                if (n3 == 0) {
                    for (int k = 0; k < n4 / 3; ++k) {
                        TreeApplet.pad.drawLine(n, n2 + k * 3, n, n2 + k * 3 + 1);
                    }
                    TreeApplet.pad.drawLine(n, n2 + n4, n, n2 + n4);
                }
                else {
                    for (int l = 0; l < n3 / 3; ++l) {
                        TreeApplet.pad.drawLine(n + l * 3, n2, n + l * 3 + 1, n2);
                    }
                    TreeApplet.pad.drawLine(n + n3, n2, n + n3, n2);
                }
                break;
            }
            case 4: {
                if (n3 == 0) {
                    for (int n6 = 0; n6 < n4 / 5; ++n6) {
                        TreeApplet.pad.drawLine(n, n2 + n6 * 5, n, n2 + n6 * 5 + 3);
                    }
                    TreeApplet.pad.drawLine(n, n2 + n4, n, n2 + n4);
                }
                else {
                    for (int n7 = 0; n7 < n3 / 5; ++n7) {
                        TreeApplet.pad.drawLine(n + n7 * 5, n2, n + n7 * 5 + 3, n2);
                    }
                    TreeApplet.pad.drawLine(n + n3, n2, n + n3, n2);
                }
                break;
            }
            default: {
                TreeApplet.pad.drawLine(n, n2, n + n3, n2 + n4);
                break;
            }
        }
    }
    
    private void get_nodedata() {
        this.nodedata = new Vector();
        this.i = 1;
        this.sTemp = this.getParameter("Node" + this.i);
        while (this.sTemp != "" & this.sTemp != null) {
            this.create_node(this.sTemp);
            ++this.i;
            this.sTemp = this.getParameter("Node" + this.i);
        }
    }
    
    public static Color verifyclr(String s, final String s2, final String s3) {
        int abs = 0;
        int abs2 = 0;
        int abs3 = 0;
        if (s == null || s.equals("")) {
            s = s2;
        }
        String trim;
        try {
            trim = s.trim();
        }
        catch (Exception ex) {
            trim = "0|0|0";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(verifystr(trim, s2), s3);
        Color color;
        try {
            if (stringTokenizer.hasMoreTokens()) {
                abs = Math.abs(Integer.parseInt(stringTokenizer.nextToken()));
            }
            if (stringTokenizer.hasMoreTokens()) {
                abs2 = Math.abs(Integer.parseInt(stringTokenizer.nextToken()));
            }
            if (stringTokenizer.hasMoreTokens()) {
                abs3 = Math.abs(Integer.parseInt(stringTokenizer.nextToken()));
            }
            color = new Color(abs, abs2, abs3);
        }
        catch (Exception ex2) {
            color = new Color(0, 0, 0);
        }
        return color;
    }
    
    static {
        TreeApplet.statusbartext = "";
        TreeApplet.xoffset = 0;
        TreeApplet.yoffset = 0;
        TreeApplet.lastclickindex = -1;
    }
    
    public static SIRpoint Drawnode(final Image image, final int n, final Color color, final Color color2, final Image image2, int n2, final int n3, final boolean b, final Color color3, final int n4, final int n5, final boolean b2, final Color color4, final boolean b3, final boolean b4, final SIRpoint siRpoint, final Vector vector, final Treenode treenode) {
        int x = siRpoint.getX();
        if (n3 > -30 && n3 < image.getHeight(TreeApplet.Iobs) + 30) {
            TreeApplet.pad.setFont(treenode.getNodefont());
            final FontMetrics fontMetrics = TreeApplet.pad.getFontMetrics();
            final int n6 = fontMetrics.getHeight() + 4;
            if (b) {
                drawline(image, n2 - n5 / 2, n3 - treenode.getNodeheight() + n6 / 2, n5 / 2, 0, color3, n4);
            }
            if (b2 && b) {
                TreeApplet.pad.setColor(color4);
                TreeApplet.pad.fillRect(n2 - n5 / 2 - 4, n3 - treenode.getNodeheight() + n6 / 2 - 4, 8, 8);
                TreeApplet.pad.setColor(color3);
                TreeApplet.pad.drawRect(n2 - n5 / 2 - 4, n3 - treenode.getNodeheight() + n6 / 2 - 4, 8, 8);
                TreeApplet.pad.drawLine(n2 - n5 / 2 - 2, n3 - treenode.getNodeheight() + n6 / 2, n2 - n5 / 2 + 2, n3 - treenode.getNodeheight() + n6 / 2);
                if (!treenode.getExpanded()) {
                    TreeApplet.pad.drawLine(n2 - n5 / 2, n3 - treenode.getNodeheight() + n6 / 2 - 2, n2 - n5 / 2, n3 - treenode.getNodeheight() + n6 / 2 + 2);
                }
                vector.addElement(new Areadata(n, n3 - treenode.getNodeheight() + n6 / 2 - 4, n2 - n5 / 2 - 4, n3 - treenode.getNodeheight() + n6 / 2 + 4, n2 - n5 / 2 + 4, 2));
            }
            if (image2 != null) {
                TreeApplet.pad.drawImage(image2, n2, n3 - treenode.getNodeheight() + n6 / 2 - image2.getHeight(TreeApplet.Iobs) / 2, TreeApplet.Iobs);
                if (b2) {
                    vector.addElement(new Areadata(n, n3 - treenode.getNodeheight() + n6 / 2 - image2.getHeight(TreeApplet.Iobs) / 2, n2, n3 - treenode.getNodeheight() + n6 / 2 + image2.getHeight(TreeApplet.Iobs) / 2, n2 + image2.getWidth(TreeApplet.Iobs), 2));
                }
                n2 = n2 + image2.getWidth(TreeApplet.Iobs) + 2;
            }
            int n7 = 0;
            final StringTokenizer stringTokenizer = new StringTokenizer(treenode.getNodetext(), "~");
            while (stringTokenizer.hasMoreTokens()) {
                final int stringWidth = fontMetrics.stringWidth(stringTokenizer.nextToken());
                if (stringWidth > n7) {
                    n7 = stringWidth;
                }
            }
            final int n8 = n2 + 6 + n7;
            if (treenode.getHighlight()) {
                TreeApplet.pad.setColor(color2);
                TreeApplet.pad.fillRect(n2, n3 - treenode.getNodeheight() + 5, n8 - n2, treenode.getNodeheight() - 5);
                drawline(image, n2, n3 - treenode.getNodeheight() + 5, n8 - n2 - 1, 0, color3, n4);
                drawline(image, n2, n3 - 1, n8 - n2 - 1, 0, color3, n4);
                drawline(image, n2, n3 - treenode.getNodeheight() + 5, 0, treenode.getNodeheight() - 6, color3, n4);
                drawline(image, n8 - 1, n3 - treenode.getNodeheight() + 5, 0, treenode.getNodeheight() - 6, color3, n4);
                TreeApplet.pad.setColor(color);
            }
            else {
                TreeApplet.pad.setColor(treenode.getNodetxtcolor());
            }
            TreeApplet.pad.setFont(treenode.getNodefont());
            final StringTokenizer stringTokenizer2 = new StringTokenizer(treenode.getNodetext(), "~");
            int n9 = stringTokenizer2.countTokens() - 1;
            while (stringTokenizer2.hasMoreTokens()) {
                TreeApplet.pad.drawString(stringTokenizer2.nextToken(), n2 + 3, n3 - 3 - n9 * n6);
                --n9;
            }
            vector.addElement(new Areadata(n, n3 - treenode.getNodeheight(), n2, n3, n8, 1));
            vector.addElement(new Areadata(n, n3 - treenode.getNodeheight(), n2, n3, n8, 5));
            if (treenode.getNodeurl() != null && !treenode.getNodeurl().equals("") && !treenode.getNodeurl().equals(" ")) {
                if (treenode.getNodetarget().equalsIgnoreCase("javascript")) {
                    vector.addElement(new Areadata(n, n3 - treenode.getNodeheight(), n2, n3, n8, 4));
                }
                else {
                    vector.addElement(new Areadata(n, n3 - treenode.getNodeheight(), n2, n3, n8, 3));
                }
            }
            else if (b2) {
                vector.addElement(new Areadata(n, n3 - treenode.getNodeheight(), n2, n3, n8, 2));
            }
            if (b3 && treenode.getExpanded() && b2) {
                TreeApplet.pad.drawLine(n2 + 3, n3, n8, n3);
            }
            if (b4 && !treenode.getNodeurl().equals(" ") && !treenode.getNodeurl().equals("") && !treenode.getNodeurl().equals(null)) {
                TreeApplet.pad.drawLine(n2 + 3, n3, n8, n3);
            }
            if (n8 > x) {
                x = n8;
            }
        }
        return new SIRpoint(x, n3);
    }
    
    private void get_images() {
        this.imagedata = new Vector();
        this.i = 1;
        this.sTemp = this.getParameter("image" + this.i);
        while (this.sTemp != "" & this.sTemp != null) {
            this.st = new StringTokenizer(this.sTemp, "|");
            String nextToken;
            if (this.st.hasMoreTokens()) {
                nextToken = this.st.nextToken();
            }
            else {
                nextToken = "";
            }
            Image downloadImage;
            if (this.st.hasMoreTokens()) {
                downloadImage = this.downloadImage(this.st.nextToken());
            }
            else {
                downloadImage = null;
            }
            this.imagedata.addElement(new Treeimage(nextToken, downloadImage));
            ++this.i;
            this.sTemp = this.getParameter("image" + this.i);
        }
    }
    
    public void init() {
        if (!this.getDocumentBase().toString().startsWith("http://www.jpowered.com") && !this.getDocumentBase().toString().startsWith("http://jpowered.com")) {
            System.exit(0);
        }
        this.started = false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target instanceof Scrollbar) {
            if (this.vscrollbar.isVisible()) {
                TreeApplet.yoffset = this.vscrollbar.getValue();
            }
            else {
                TreeApplet.yoffset = 0;
            }
            if (this.hscrollbar.isVisible()) {
                TreeApplet.xoffset = this.hscrollbar.getValue();
            }
            else {
                TreeApplet.xoffset = 0;
            }
            this.paint(this.getGraphics());
            return true;
        }
        return super.handleEvent(event);
    }
    
    public static Font verifyfnt(final String s, final String s2, final String s3) {
        String nextToken = "Courier";
        String upperCase = "N";
        int int1 = 12;
        String trim;
        try {
            trim = s.trim();
        }
        catch (Exception ex) {
            trim = "Courier|N|10";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(verifystr(trim, s2), s3);
        Font font;
        try {
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                upperCase = stringTokenizer.nextToken().toUpperCase();
            }
            if (stringTokenizer.hasMoreTokens()) {
                int1 = Integer.parseInt(stringTokenizer.nextToken());
            }
            if (upperCase.equals("B")) {
                font = new Font(nextToken, 1, int1);
            }
            else if (upperCase.equals("I")) {
                font = new Font(nextToken, 2, int1);
            }
            else if (upperCase.equals("BI") || upperCase.equals("IB")) {
                font = new Font(nextToken, 3, int1);
            }
            else {
                font = new Font(nextToken, 0, int1);
            }
        }
        catch (Exception ex2) {
            font = new Font("Courier", 0, 12);
        }
        return font;
    }
    
    public static int verifyint(final String s) {
        String trim;
        try {
            trim = s.trim();
        }
        catch (Exception ex) {
            trim = "0";
        }
        int int1;
        try {
            int1 = Integer.parseInt(trim);
        }
        catch (Exception ex2) {
            int1 = 0;
        }
        return int1;
    }
    
    public void ReloadFile(final String s) {
        this.get_nodedatafromfile(s);
        this.tree = getsubmenu(this.nodedata, "root");
        TreeApplet.yoffset = 0;
        TreeApplet.xoffset = 0;
        try {
            if (this.vscrollbar.isVisible()) {
                this.vscrollbar.setValue(0);
            }
            if (this.hscrollbar.isVisible()) {
                this.hscrollbar.setValue(0);
            }
        }
        catch (Exception ex) {}
        this.paint(this.getGraphics());
    }
}
