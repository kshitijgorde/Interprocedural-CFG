// 
// Decompiled by Procyon v0.5.30
// 

package Sirius.tree;

import java.awt.Event;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Scrollbar;
import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

public class Treebean extends Applet implements MouseMotionListener, MouseListener
{
    private static Graphics pad;
    private Vector treedata;
    private Vector areadata;
    private Vector nodedata;
    private Color bgcolor;
    private int treewidth;
    private int treeheight;
    private int nodegap;
    private boolean connlines;
    private Color connlinescolor;
    private int connlinesstyle;
    private boolean smoothscroll;
    private int scrolljumpsize;
    private int scrollspeed;
    private boolean snem;
    private Color hightxtcolor;
    private Color highbgcolor;
    private int nodeoffset;
    private String title;
    private Font titlefont;
    private Color titlecolor;
    private int titleheight;
    private int borderwidth;
    private Color bordercolor;
    private boolean statusbar;
    private Font statusbarfont;
    private Color statusbarbgcolor;
    private Color statusbartxtcolor;
    private int statusbarheight;
    private boolean underlineexpanded;
    private boolean underlinelinks;
    private Color scrollbarbgcolor;
    private Vector tree;
    private Vector images;
    private Image bgimage;
    private SIRpoint bgimageposition;
    private int nodeindex;
    private String nodeid;
    private String parentid;
    private String nodetext;
    private Font nodefont;
    private Color nodetxtcolor;
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
    private String area_nodeid;
    private int area_topy;
    private int area_leftx;
    private int area_bottomy;
    private int area_rightx;
    private int area_action;
    private static int i;
    private static int j;
    private static int xTemp;
    private static int yTemp;
    private static int xpos;
    private static int ypos;
    private static int xoffset;
    private static int yoffset;
    private static int totalwidth;
    private static int totalheight;
    private static int displaywidth;
    private static int displayheight;
    private static Applet parentapplet;
    private static int saveypos;
    private static int titleypos;
    private static String sTemp;
    private static String debugmsg;
    private static String statusbartext;
    private static FontMetrics fontMetrics;
    private static ImageObserver Iobs;
    private Image SIRbuffer;
    private static boolean started;
    Enumeration te;
    Treedata td;
    Scrollbar vscrollbar;
    Scrollbar hscrollbar;
    
    public Insets insets() {
        this.te = this.treedata.elements();
        if (this.te.hasMoreElements()) {
            this.td = this.te.nextElement();
            this.borderwidth = this.td.getBorderwidth();
        }
        else {
            this.borderwidth = 0;
        }
        return new Insets(this.borderwidth, this.borderwidth, this.borderwidth, this.borderwidth);
    }
    
    public Vector getTreedata() {
        return this.treedata;
    }
    
    public void setTreedata(final Vector treedata) {
        this.treedata = treedata;
        this.repaint();
    }
    
    public Vector getTree() {
        return this.tree;
    }
    
    public void setTree(final Vector tree) {
        this.tree = tree;
        this.repaint();
    }
    
    public Vector getNodedata() {
        return this.nodedata;
    }
    
    public void setNodedata(final Vector nodedata) {
        this.nodedata = nodedata;
        this.repaint();
    }
    
    public int getXoffset() {
        return Treebean.xoffset;
    }
    
    public int getYoffset() {
        return Treebean.yoffset;
    }
    
    public void setXoffset(final int xoffset) {
        Treebean.xoffset = xoffset;
        this.repaint();
    }
    
    public void setYoffset(final int yoffset) {
        Treebean.yoffset = yoffset;
        this.repaint();
    }
    
    public Applet getParentapplet() {
        return Treebean.parentapplet;
    }
    
    public void setParentapplet(final Applet parentapplet) {
        Treebean.parentapplet = parentapplet;
    }
    
    public void init() {
        Treebean.started = false;
    }
    
    public Treebean() {
        this.setSize(200, 300);
        Treebean.xpos = 0;
        Treebean.ypos = 0;
        Treebean.yoffset = 0;
        Treebean.xoffset = 0;
        Treebean.totalwidth = 0;
        Treebean.totalheight = 0;
        Treebean.displayheight = 100;
        Treebean.displaywidth = 100;
        Treebean.statusbartext = "";
        this.setLayout(new BorderLayout());
        final boolean b = false;
        final int n = 100;
        final boolean b2 = true;
        final boolean b3 = false;
        (this.vscrollbar = new Scrollbar(1, b3 ? 1 : 0, b2 ? 1 : 0, b ? 1 : 0, n)).setVisibleAmount(50);
        this.vscrollbar.setBlockIncrement(25);
        this.vscrollbar.setUnitIncrement(5);
        this.vscrollbar.setBackground(this.scrollbarbgcolor);
        this.vscrollbar.setVisible(true);
        this.add("East", this.vscrollbar);
        (this.hscrollbar = new Scrollbar(0, b3 ? 1 : 0, b2 ? 1 : 0, b ? 1 : 0, n)).setVisibleAmount(50);
        this.hscrollbar.setBlockIncrement(25);
        this.hscrollbar.setUnitIncrement(5);
        this.hscrollbar.setBackground(this.scrollbarbgcolor);
        this.hscrollbar.setVisible(true);
        this.add("South", this.hscrollbar);
        (this.tree = new Vector()).addElement(new Tree(0));
        (this.nodedata = new Vector()).addElement(new Treenode("default", "root", "Default Node", new Font("Courier", 0, 12), new Color(0, 0, 0), false, false, "", "", 15, 50, "Default node.", false, null, null, null, null, false));
        (this.treedata = new Vector()).addElement(new Treedata(new Color(255, 255, 255), 200, 300, 0, true, new Color(100, 100, 100), 0, false, 2, 0, false, new Color(255, 0, 0), new Color(0, 0, 0), 10, "", new Font("Courier", 0, 12), new Color(0, 0, 0), 15, 0, new Color(200, 200, 200), false, new Font("Courier", 0, 10), new Color(200, 200, 200), new Color(0, 0, 255), 20, false, false, new Color(150, 150, 150), this.tree, null, null, new SIRpoint(0, 0)));
        (this.areadata = new Vector()).addElement(new Areadata(0, 0, 0, 0, 0, 0));
    }
    
    public void start() {
        Treebean.statusbartext = "";
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.areadata = new Vector();
        this.te = this.treedata.elements();
        if (this.te.hasMoreElements()) {
            this.td = this.te.nextElement();
            this.bgcolor = this.td.getBgcolor();
            this.treewidth = this.td.getTreewidth();
            this.treeheight = this.td.getTreeheight();
            this.nodegap = this.td.getNodegap();
            this.connlines = this.td.getConnlines();
            this.connlinescolor = this.td.getConnlinescolor();
            this.connlinesstyle = this.td.getConnlinesstyle();
            this.smoothscroll = this.td.getSmoothscroll();
            this.scrolljumpsize = this.td.getScrolljumpsize();
            this.scrollspeed = this.td.getScrollspeed();
            this.snem = this.td.getSnem();
            this.hightxtcolor = this.td.getHightxtcolor();
            this.highbgcolor = this.td.getHighbgcolor();
            this.nodeoffset = this.td.getNodeoffset();
            this.title = this.td.getTitle();
            this.titlefont = this.td.getTitlefont();
            this.titlecolor = this.td.getTitlecolor();
            this.titleheight = this.td.getTitleheight();
            this.borderwidth = this.td.getBorderwidth();
            this.bordercolor = this.td.getBordercolor();
            this.statusbar = this.td.getStatusbar();
            this.statusbarfont = this.td.getStatusbarfont();
            this.statusbarbgcolor = this.td.getStatusbarbgcolor();
            this.statusbartxtcolor = this.td.getStatusbartxtcolor();
            this.statusbarheight = this.td.getStatusbarheight();
            this.underlineexpanded = this.td.getUnderlineexpanded();
            this.underlinelinks = this.td.getUnderlinelinks();
            this.scrollbarbgcolor = this.td.getScrollbarbgcolor();
            this.tree = this.td.getTreenode();
            this.images = this.td.getImages();
            this.bgimage = this.td.getBgimage();
            this.bgimageposition = this.td.getBgimageposition();
        }
        else {
            this.bgcolor = new Color(100, 100, 100);
            this.treewidth = 200;
            this.treeheight = 300;
            this.nodegap = 0;
            this.connlines = true;
            this.connlinescolor = new Color(100, 100, 100);
            this.connlinesstyle = 0;
            this.smoothscroll = true;
            this.scrolljumpsize = 2;
            this.scrollspeed = 0;
            this.snem = false;
            this.hightxtcolor = new Color(255, 255, 255);
            this.highbgcolor = new Color(0, 0, 0);
            this.nodeoffset = 10;
            this.title = null;
            this.titlefont = new Font("Courier", 0, 12);
            this.titlecolor = new Color(0, 0, 0);
            this.titleheight = 15;
            this.borderwidth = 0;
            this.bordercolor = new Color(200, 200, 200);
            this.statusbar = false;
            this.statusbarfont = new Font("Courier", 0, 10);
            this.statusbarbgcolor = new Color(200, 200, 200);
            this.statusbartxtcolor = new Color(0, 0, 255);
            this.statusbarheight = 20;
            this.underlineexpanded = false;
            this.underlinelinks = false;
            this.scrollbarbgcolor = new Color(150, 150, 150);
            this.tree = null;
            this.images = null;
            this.bgimage = null;
            this.bgimageposition = null;
        }
        this.insets();
        Treebean.xpos = 2 + this.borderwidth;
        Treebean.ypos = 2 + this.borderwidth;
        Treebean.displaywidth = this.treewidth - 2 - 2 * this.borderwidth;
        Treebean.displayheight = this.treeheight - 2 - 2 * this.borderwidth;
        this.SIRbuffer = this.createImage(this.treewidth, this.treeheight);
        (Treebean.pad = this.SIRbuffer.getGraphics()).setColor(this.bgcolor);
        Treebean.pad.fillRect(0, 0, this.treewidth, this.treeheight);
        if (this.bgimage != null) {
            if (this.bgimageposition == null) {
                this.bgimageposition = new SIRpoint(0, 0);
            }
            Treebean.pad.drawImage(this.bgimage, this.bgimageposition.getX(), this.bgimageposition.getY(), Treebean.Iobs);
        }
        if (!this.title.equals("")) {
            Treebean.fontMetrics = this.getFontMetrics(this.titlefont);
            Treebean.ypos += this.titleheight;
            Treebean.titleypos = Treebean.ypos;
            Treebean.totalheight += this.titleheight;
            Treebean.displayheight -= this.titleheight;
        }
        final Enumeration<Tree> elements = this.tree.elements();
        Treebean.ypos -= Treebean.yoffset;
        Treebean.xpos -= Treebean.xoffset;
        int ypos = Treebean.ypos;
        int ypos2 = Treebean.ypos;
        final int ypos3 = Treebean.ypos;
        final int xpos = Treebean.xpos;
        SIRpoint drawMenu = new SIRpoint(Treebean.xpos, Treebean.ypos);
        while (elements.hasMoreElements()) {
            this.nodeindex = elements.nextElement().getNodeindex();
            final Treenode treenode = this.nodedata.elementAt(this.nodeindex);
            this.nodeid = treenode.getNodeid();
            this.parentid = treenode.getParentid();
            this.nodetext = treenode.getNodetext();
            this.nodefont = treenode.getNodefont();
            this.nodetxtcolor = treenode.getNodetxtcolor();
            this.expanded = treenode.getExpanded();
            this.mouseover = treenode.getMouseover();
            this.submenu = treenode.getTreenode();
            this.nodeurl = treenode.getNodeurl();
            this.nodetarget = treenode.getNodetarget();
            this.nodeheight = treenode.getNodeheight();
            this.nodewidth = treenode.getNodewidth();
            this.nodestatustext = treenode.getNodestatustext();
            this.nodedisplaystatus = treenode.getDisplaystatus();
            this.normalimage = treenode.getNormalimage();
            this.mouseoverimage = treenode.getMouseoverimage();
            this.expandedimage = treenode.getExpandedimage();
            this.highlight = treenode.getHighlight();
            if (this.submenu != null) {
                if (this.submenu.elements().hasMoreElements()) {
                    ypos2 = Treebean.ypos + this.nodeheight - this.nodeheight / 2 - 4 + this.nodegap;
                }
                else {
                    ypos2 = Treebean.ypos + this.nodeheight - this.nodeheight / 2 + this.nodegap;
                }
            }
            if (this.connlines) {
                SIRtreegraphics.drawline(this.SIRbuffer, Treebean.xpos + this.nodeoffset - this.nodeoffset / 2, ypos, 0, ypos2 - ypos, this.connlinescolor, this.connlinesstyle);
            }
            if (this.submenu != null) {
                if (this.submenu.elements().hasMoreElements()) {
                    ypos = Treebean.ypos + this.nodeheight - this.nodeheight / 2 + 5 + this.nodegap;
                }
                else {
                    ypos = Treebean.ypos + this.nodeheight - this.nodeheight / 2 + this.nodegap;
                }
            }
            drawMenu = SIRtreegraphics.DrawMenu(this.SIRbuffer, this.connlines, this.connlinescolor, this.connlinesstyle, this.nodegap, this.underlineexpanded, this.underlinelinks, Treebean.xpos + this.nodeoffset, Treebean.ypos, this.nodeoffset, this.nodeindex, this.parentid, this.nodetext, this.nodefont, this.nodetxtcolor, this.expanded, this.mouseover, this.submenu, this.nodeurl, this.nodetarget, this.nodeheight, this.nodewidth, this.nodestatustext, this.nodedisplaystatus, this.normalimage, this.mouseoverimage, this.expandedimage, this.highlight, this.hightxtcolor, this.highbgcolor, this.bgcolor, drawMenu, this.areadata, this.nodedata);
            Treebean.ypos = drawMenu.getY();
        }
        Treebean.totalwidth = drawMenu.getX() - xpos;
        Treebean.totalheight = Treebean.ypos - ypos3;
        if (this.statusbar) {
            int n = this.treewidth - 2 * this.borderwidth;
            if (this.vscrollbar.isVisible()) {
                n -= 15;
            }
            int n2 = this.treeheight - this.statusbarheight - this.borderwidth;
            if (this.hscrollbar.isVisible()) {
                n2 -= 15;
            }
            SIRtreegraphics.DrawStatusbar(this.SIRbuffer, Treebean.statusbartext, this.statusbarfont, this.statusbartxtcolor, this.statusbarbgcolor, this.statusbarheight, n, this.borderwidth, n2);
            Treebean.displayheight -= this.statusbarheight;
        }
        if (!this.title.equals("")) {
            Treebean.pad.setColor(this.bgcolor);
            Treebean.pad.fillRect(this.borderwidth, this.borderwidth, this.treewidth - 2 * this.borderwidth, this.titleheight + 3);
            Treebean.fontMetrics = this.getFontMetrics(this.titlefont);
            SIRtreegraphics.DrawLabel(this.SIRbuffer, this.title, this.titlefont, this.titlecolor, (this.treewidth - 2 * this.borderwidth - Treebean.fontMetrics.stringWidth(this.title)) / 2, Treebean.titleypos - 5, 0);
        }
        SIRtreegraphics.DrawBorder(this.SIRbuffer, this.getSize().width, this.getSize().height, this.borderwidth, this.bordercolor);
        graphics.drawImage(this.SIRbuffer, 0, 0, this);
        this.vscrollbar.setMaximum(Treebean.totalheight + 30);
        this.vscrollbar.setVisibleAmount(Treebean.displayheight);
        this.vscrollbar.setValue(Treebean.yoffset);
        if (Treebean.displayheight > Treebean.totalheight + 15) {
            this.vscrollbar.setVisible(false);
            this.setYoffset(0);
        }
        else {
            this.vscrollbar.setVisible(true);
            this.vscrollbar.setBackground(this.scrollbarbgcolor);
        }
        this.hscrollbar.setMaximum(Treebean.totalwidth + 30);
        this.hscrollbar.setVisibleAmount(Treebean.displaywidth);
        this.hscrollbar.setValue(Treebean.xoffset);
        if (Treebean.displaywidth > Treebean.totalwidth + 15) {
            this.hscrollbar.setVisible(false);
            this.setXoffset(0);
        }
        else {
            this.hscrollbar.setVisible(true);
            this.hscrollbar.setBackground(this.scrollbarbgcolor);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final Enumeration<Treenode> elements = (Enumeration<Treenode>)this.nodedata.elements();
        final Enumeration<Areadata> elements2 = (Enumeration<Areadata>)this.areadata.elements();
        int n = 0;
        while (elements.hasMoreElements()) {
            final Treenode treenode = elements.nextElement();
            if (treenode.getHighlight()) {
                treenode.setHighlight(false);
                treenode.setMouseover(false);
                this.nodedata.setElementAt(treenode, n);
            }
            ++n;
        }
        Treebean.statusbartext = "";
        while (elements2.hasMoreElements()) {
            final Areadata areadata = elements2.nextElement();
            if (x > areadata.getLeftx() && x < areadata.getRightx() && y > areadata.getTopy() && y < areadata.getBottomy()) {
                switch (areadata.getAction()) {
                    default: {
                        continue;
                    }
                    case 1: {
                        final int nodeindex = areadata.getNodeindex();
                        final Treenode treenode2 = this.nodedata.elementAt(nodeindex);
                        treenode2.setHighlight(true);
                        treenode2.setMouseover(true);
                        this.nodedata.setElementAt(treenode2, nodeindex);
                        continue;
                    }
                    case 5: {
                        Treebean.statusbartext = ((Treenode)this.nodedata.elementAt(areadata.getNodeindex())).getNodestatustext();
                        continue;
                    }
                }
            }
        }
        this.paint(this.getGraphics());
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final Enumeration<Treenode> elements = this.nodedata.elements();
        final Enumeration<Areadata> elements2 = this.areadata.elements();
        while (elements2.hasMoreElements()) {
            final Areadata areadata = elements2.nextElement();
            if (x > areadata.getLeftx() && x < areadata.getRightx() && y > areadata.getTopy() && y < areadata.getBottomy()) {
                switch (areadata.getAction()) {
                    default: {
                        continue;
                    }
                    case 2: {
                        final int nodeindex = areadata.getNodeindex();
                        if (this.snem && ((Treenode)this.nodedata.elementAt(nodeindex)).getParentid().equals("root")) {
                            int n = 0;
                            while (elements.hasMoreElements()) {
                                final Treenode treenode = elements.nextElement();
                                treenode.getParentid();
                                if (treenode.getParentid().equals("root") && n != nodeindex) {
                                    treenode.setExpanded(false);
                                    this.nodedata.setElementAt(treenode, n);
                                }
                                ++n;
                            }
                        }
                        final Treenode treenode2 = this.nodedata.elementAt(nodeindex);
                        if (treenode2.getExpanded()) {
                            treenode2.setExpanded(false);
                        }
                        else {
                            treenode2.setExpanded(true);
                        }
                        this.nodedata.setElementAt(treenode2, nodeindex);
                        continue;
                    }
                    case 3: {
                        final Treenode treenode3 = this.nodedata.elementAt(areadata.getNodeindex());
                        this.nodeurl = treenode3.getNodeurl();
                        this.nodetarget = treenode3.getNodetarget();
                        try {
                            Treebean.parentapplet.getAppletContext().showDocument(new URL(Treebean.parentapplet.getDocumentBase(), this.nodeurl), this.nodetarget);
                        }
                        catch (MalformedURLException ex) {
                            ex.printStackTrace();
                        }
                        continue;
                    }
                }
            }
        }
        this.paint(this.getGraphics());
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target instanceof Scrollbar) {
            if (this.vscrollbar.isVisible()) {
                this.setYoffset(this.vscrollbar.getValue());
            }
            else {
                this.setYoffset(0);
            }
            if (this.hscrollbar.isVisible()) {
                this.setXoffset(this.hscrollbar.getValue());
            }
            else {
                this.setXoffset(0);
            }
            this.repaint();
            return true;
        }
        return super.handleEvent(event);
    }
    
    static {
        Treebean.started = false;
    }
}
