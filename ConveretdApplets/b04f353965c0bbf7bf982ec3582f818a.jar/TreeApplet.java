import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import Sirius.tree.Treenode;
import java.net.URLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import Sirius.tree.Treeimage;
import Sirius.tree.Treedata;
import Sirius.tree.SIRdata;
import java.awt.Component;
import java.awt.LayoutManager;
import Sirius.tree.Evaluation;
import java.util.StringTokenizer;
import Sirius.tree.SIRpoint;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;
import Sirius.tree.Treebean;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TreeApplet extends Applet
{
    private Treebean treebean;
    private Vector treedata;
    private Vector tree;
    private Vector nodedata;
    private Vector imagedata;
    private Color t_bgcolor;
    private int t_width;
    private int t_height;
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
    private Vector t_images;
    private Image t_bgimage;
    private SIRpoint t_bgimageposition;
    private String nodeid;
    private String parentid;
    private String nodetext;
    private Font nodefont;
    private Color nodecolor;
    private boolean expanded;
    private boolean mouseover;
    private String nodeurl;
    private String nodetarget;
    private int nodeheight;
    private int nodewidth;
    private String nodestatustext;
    private boolean nodedisplaystatus;
    private Image normalimage;
    private Image mouseoverimage;
    private Image expandedimage;
    private int i;
    private String sTemp;
    private String sTemp2;
    private StringTokenizer st;
    
    public void init() {
    }
    
    public void start() {
        this.treebean = new Treebean();
        this.get_images();
        this.sTemp = this.getParameter("Nodedata");
        if (this.sTemp != "" & this.sTemp != null) {
            this.get_nodedatafromfile(this.sTemp);
        }
        else {
            this.get_nodedata();
        }
        this.get_treedata();
        this.set_treedata();
        this.treebean.setParentapplet(this);
        new Evaluation(this);
        this.treebean.resize(this.t_width, this.t_height);
        this.treebean.start();
        this.setLayout(null);
        this.setBackground(this.t_bgcolor);
        this.add(this.treebean);
        this.treebean.resize(this.t_width, this.t_height);
    }
    
    private void set_treedata() {
        this.tree = SIRdata.getsubmenu(this.nodedata, "root");
        (this.treedata = new Vector()).addElement(new Treedata(this.t_bgcolor, this.t_width, this.t_height, this.t_nodegap, this.t_connlines, this.t_connlinescolor, this.t_connlinesstyle, this.t_smoothscroll, this.t_scrolljumpsize, this.t_scrollspeed, this.t_snem, this.t_hightxtcolor, this.t_highbgcolor, this.t_nodeoffset, this.t_title, this.t_titlefont, this.t_titlecolor, this.t_titleheight, this.t_borderwidth, this.t_bordercolor, this.t_statusbar, this.t_statusbarfont, this.t_statusbarbgcolor, this.t_statusbartxtcolor, this.t_statusbarheight, this.t_underlineexpanded, this.t_underlinelinks, this.t_scrollbarbgcolor, this.tree, this.t_images, this.t_bgimage, this.t_bgimageposition));
        this.treebean.setTreedata(this.treedata);
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
    
    private void get_nodedata() {
        this.nodedata = new Vector();
        this.i = 1;
        this.sTemp = this.getParameter("Node" + this.i);
        while (this.sTemp != "" & this.sTemp != null) {
            this.create_node(this.sTemp);
            ++this.i;
            this.sTemp = this.getParameter("Node" + this.i);
        }
        this.treebean.setNodedata(this.nodedata);
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
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(openConnection.getInputStream()));
            Label_0100: {
                break Label_0100;
                String line;
                do {
                    this.create_node(this.sTemp);
                    line = dataInputStream.readLine();
                    this.sTemp = line;
                } while (line != null);
            }
        }
        catch (IOException ex) {
            System.out.println("IO Error:" + ex.getMessage());
        }
        this.treebean.setNodedata(this.nodedata);
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
        this.nodefont = SIRdata.verifyfnt(this.sTemp2, "Courier|N|10", "|");
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
        this.nodecolor = SIRdata.verifyclr(this.sTemp2, "0|0|0", "|");
        if (this.st.hasMoreTokens()) {
            this.expanded = SIRdata.verifybool(this.st.nextToken(), "false");
        }
        else {
            this.expanded = false;
        }
        this.mouseover = false;
        if (this.st.hasMoreTokens()) {
            this.normalimage = SIRdata.getimage(this.st.nextToken(), this.imagedata);
        }
        else {
            this.normalimage = null;
        }
        if (this.st.hasMoreTokens()) {
            this.expandedimage = SIRdata.getimage(this.st.nextToken(), this.imagedata);
        }
        else {
            this.expandedimage = null;
        }
        if (this.st.hasMoreTokens()) {
            this.mouseoverimage = SIRdata.getimage(this.st.nextToken(), this.imagedata);
        }
        else {
            this.mouseoverimage = null;
        }
        this.nodeheight = this.getFontMetrics(this.nodefont).getHeight() + 4;
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
        this.nodewidth = 0;
        this.nodedisplaystatus = false;
        this.nodedata.addElement(new Treenode(this.nodeid, this.parentid, this.nodetext, this.nodefont, this.nodecolor, this.expanded, this.mouseover, this.nodeurl, this.nodetarget, this.nodeheight, this.nodewidth, this.nodestatustext, this.nodedisplaystatus, this.normalimage, this.mouseoverimage, this.expandedimage, null, false));
    }
    
    private void get_treedata() {
        this.t_bgcolor = SIRdata.verifyclr(this.getParameter("backgroundColor"), "255|255|255", "|");
        this.t_nodegap = SIRdata.verifyint(this.getParameter("nodegap"));
        this.t_connlines = SIRdata.verifybool(this.getParameter("connectingLines"), "true");
        this.t_connlinescolor = SIRdata.verifyclr(this.getParameter("connectingLinesColor"), "100|100|100", "|");
        this.t_connlinesstyle = SIRdata.verifyint(this.getParameter("connectingLinesStyle"));
        this.t_smoothscroll = SIRdata.verifybool(this.getParameter("smoothScroll"), "true");
        this.t_scrolljumpsize = SIRdata.verifyint(this.getParameter("scrollJumpSize"));
        this.t_scrollspeed = SIRdata.verifyint(this.getParameter("scrollSpeed"));
        this.t_snem = SIRdata.verifybool(this.getParameter("singleNodeExapansionMode"), "false");
        this.t_hightxtcolor = SIRdata.verifyclr(this.getParameter("highTextColor"), "255|255|255", "|");
        this.t_highbgcolor = SIRdata.verifyclr(this.getParameter("highBgColor"), "0|0|0", "|");
        this.t_nodeoffset = SIRdata.verifyint(this.getParameter("nodeOffset"));
        this.t_title = SIRdata.verifystr(this.getParameter("title"), "");
        this.t_titlefont = SIRdata.verifyfnt(this.getParameter("titleFont"), "Courier|N|12", "|");
        this.t_titlecolor = SIRdata.verifyclr(this.getParameter("titleColor"), "0|0|0", "|");
        this.t_borderwidth = SIRdata.verifyint(this.getParameter("borderWidth"));
        this.t_bordercolor = SIRdata.verifyclr(this.getParameter("borderColor"), "200|200|200", "|");
        this.t_statusbar = SIRdata.verifybool(this.getParameter("statusBar"), "false");
        this.t_statusbarfont = SIRdata.verifyfnt(this.getParameter("statusBarFont"), "Courier|N|10", "|");
        this.t_statusbarbgcolor = SIRdata.verifyclr(this.getParameter("statusBarBgColor"), "200|200|200", "|");
        this.t_statusbartxtcolor = SIRdata.verifyclr(this.getParameter("statusBarTextColor"), "0|0|255", "|");
        this.t_statusbarheight = SIRdata.verifyint(this.getParameter("statusBarHeight"));
        if (this.t_statusbarheight == 0) {
            this.t_statusbarheight = 15;
        }
        this.t_underlineexpanded = SIRdata.verifybool(this.getParameter("underlineExpanded"), "false");
        this.t_underlinelinks = SIRdata.verifybool(this.getParameter("underlineLinks"), "false");
        this.t_scrollbarbgcolor = SIRdata.verifyclr(this.getParameter("scrollBarBgColor"), "150|150|150", "|");
        this.t_images = null;
        this.t_width = this.getSize().width;
        this.t_height = this.getSize().height;
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
                verifyint = SIRdata.verifyint(this.st.nextToken());
            }
            if (this.st.hasMoreTokens()) {
                verifyint2 = SIRdata.verifyint(this.st.nextToken());
            }
        }
        this.t_bgimageposition = new SIRpoint(verifyint, verifyint2);
        this.t_titleheight = this.getFontMetrics(this.t_titlefont).getHeight();
    }
    
    public Image downloadImage(final String s) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image image = this.getImage(this.getCodeBase(), s);
        mediaTracker.addImage(image, 0);
        try {
            this.showStatus("Loading Image...");
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
            this.showStatus("Image loading error");
            return this.createImage(this.size().width, this.size().height);
        }
        if ((pixelGrabber.status() & 0x80) != 0x0) {
            System.err.println("image fetch aborted or errored");
            this.showStatus("Image loading error");
            return this.createImage(this.size().width, this.size().height);
        }
        return this.createImage(new MemoryImageSource(width, height, array, 0, width));
    }
}
