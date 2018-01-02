// 
// Decompiled by Procyon v0.5.30
// 

package Sirius.tree;

import java.awt.Image;
import java.util.Vector;
import java.awt.Font;
import java.awt.Color;
import java.io.Serializable;

public class Treedata implements Serializable
{
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
    private Vector treenode;
    private Vector images;
    private Image bgimage;
    private SIRpoint bgimageposition;
    
    public Treedata(final Color bgcolor, final int treewidth, final int treeheight, final int nodegap, final boolean connlines, final Color connlinescolor, final int connlinesstyle, final boolean smoothscroll, final int scrolljumpsize, final int scrollspeed, final boolean snem, final Color hightxtcolor, final Color highbgcolor, final int nodeoffset, final String title, final Font titlefont, final Color titlecolor, final int titleheight, final int borderwidth, final Color bordercolor, final boolean statusbar, final Font statusbarfont, final Color statusbarbgcolor, final Color statusbartxtcolor, final int statusbarheight, final boolean underlineexpanded, final boolean underlinelinks, final Color scrollbarbgcolor, final Vector treenode, final Vector images, final Image bgimage, final SIRpoint bgimageposition) {
        this.bgcolor = bgcolor;
        this.treewidth = treewidth;
        this.treeheight = treeheight;
        this.nodegap = nodegap;
        this.connlines = connlines;
        this.connlinescolor = connlinescolor;
        this.connlinesstyle = connlinesstyle;
        this.smoothscroll = smoothscroll;
        this.scrolljumpsize = scrolljumpsize;
        this.scrollspeed = scrollspeed;
        this.snem = snem;
        this.hightxtcolor = hightxtcolor;
        this.highbgcolor = highbgcolor;
        this.nodeoffset = nodeoffset;
        this.title = title;
        this.titlefont = titlefont;
        this.titlecolor = titlecolor;
        this.titleheight = titleheight;
        this.borderwidth = borderwidth;
        this.bordercolor = bordercolor;
        this.statusbar = statusbar;
        this.statusbarfont = statusbarfont;
        this.statusbarbgcolor = statusbarbgcolor;
        this.statusbartxtcolor = statusbartxtcolor;
        this.statusbarheight = statusbarheight;
        this.underlineexpanded = underlineexpanded;
        this.underlinelinks = underlinelinks;
        this.scrollbarbgcolor = scrollbarbgcolor;
        this.treenode = treenode;
        this.images = images;
        this.bgimage = bgimage;
        this.bgimageposition = bgimageposition;
    }
    
    public Color getBgcolor() {
        return this.bgcolor;
    }
    
    public int getTreewidth() {
        return this.treewidth;
    }
    
    public int getTreeheight() {
        return this.treeheight;
    }
    
    public int getNodegap() {
        return this.nodegap;
    }
    
    public boolean getConnlines() {
        return this.connlines;
    }
    
    public Color getConnlinescolor() {
        return this.connlinescolor;
    }
    
    public int getConnlinesstyle() {
        return this.connlinesstyle;
    }
    
    public boolean getSmoothscroll() {
        return this.smoothscroll;
    }
    
    public int getScrolljumpsize() {
        return this.scrolljumpsize;
    }
    
    public int getScrollspeed() {
        return this.scrollspeed;
    }
    
    public boolean getSnem() {
        return this.snem;
    }
    
    public Color getHightxtcolor() {
        return this.hightxtcolor;
    }
    
    public Color getHighbgcolor() {
        return this.highbgcolor;
    }
    
    public int getNodeoffset() {
        return this.nodeoffset;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public Font getTitlefont() {
        return this.titlefont;
    }
    
    public Color getTitlecolor() {
        return this.titlecolor;
    }
    
    public int getTitleheight() {
        return this.titleheight;
    }
    
    public int getBorderwidth() {
        return this.borderwidth;
    }
    
    public Color getBordercolor() {
        return this.bordercolor;
    }
    
    public boolean getStatusbar() {
        return this.statusbar;
    }
    
    public Font getStatusbarfont() {
        return this.statusbarfont;
    }
    
    public Color getStatusbarbgcolor() {
        return this.statusbarbgcolor;
    }
    
    public Color getStatusbartxtcolor() {
        return this.statusbartxtcolor;
    }
    
    public int getStatusbarheight() {
        return this.statusbarheight;
    }
    
    public boolean getUnderlineexpanded() {
        return this.underlineexpanded;
    }
    
    public boolean getUnderlinelinks() {
        return this.underlinelinks;
    }
    
    public Color getScrollbarbgcolor() {
        return this.scrollbarbgcolor;
    }
    
    public Vector getTreenode() {
        return this.treenode;
    }
    
    public Vector getImages() {
        return this.images;
    }
    
    public Image getBgimage() {
        return this.bgimage;
    }
    
    public SIRpoint getBgimageposition() {
        return this.bgimageposition;
    }
    
    public void setBgcolor(final Color bgcolor) {
        this.bgcolor = bgcolor;
    }
    
    public void setTreewidth(final int treewidth) {
        this.treewidth = treewidth;
    }
    
    public void setTreeheight(final int treeheight) {
        this.treeheight = treeheight;
    }
    
    public void setNodegap(final int nodegap) {
        this.nodegap = nodegap;
    }
    
    public void setConnlines(final boolean connlines) {
        this.connlines = connlines;
    }
    
    public void setConnlinescolor(final Color connlinescolor) {
        this.connlinescolor = connlinescolor;
    }
    
    public void setConnlinesstyle(final int connlinesstyle) {
        this.connlinesstyle = connlinesstyle;
    }
    
    public void setSmoothscroll(final boolean smoothscroll) {
        this.smoothscroll = smoothscroll;
    }
    
    public void setScrolljumpsize(final int scrolljumpsize) {
        this.scrolljumpsize = scrolljumpsize;
    }
    
    public void setScrollspeed(final int scrollspeed) {
        this.scrollspeed = scrollspeed;
    }
    
    public void setSnem(final boolean snem) {
        this.snem = snem;
    }
    
    public void setHightxtcolor(final Color hightxtcolor) {
        this.hightxtcolor = hightxtcolor;
    }
    
    public void setHighbgcolor(final Color highbgcolor) {
        this.highbgcolor = highbgcolor;
    }
    
    public void setNodeoffset(final int nodeoffset) {
        this.nodeoffset = nodeoffset;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public void setTitlefont(final Font titlefont) {
        this.titlefont = titlefont;
    }
    
    public void setTitlecolor(final Color titlecolor) {
        this.titlecolor = titlecolor;
    }
    
    public void setTitleheight(final int titleheight) {
        this.titleheight = titleheight;
    }
    
    public void setBorderwidth(final int borderwidth) {
        this.borderwidth = borderwidth;
    }
    
    public void setBordercolor(final Color bordercolor) {
        this.bordercolor = bordercolor;
    }
    
    public void setStatusbar(final boolean statusbar) {
        this.statusbar = statusbar;
    }
    
    public void setStatusbarfont(final Font statusbarfont) {
        this.statusbarfont = statusbarfont;
    }
    
    public void setStatusbarbgcolor(final Color statusbarbgcolor) {
        this.statusbarbgcolor = statusbarbgcolor;
    }
    
    public void setStatusbartxtcolor(final Color statusbartxtcolor) {
        this.statusbartxtcolor = statusbartxtcolor;
    }
    
    public void setStatusbarheight(final int statusbarheight) {
        this.statusbarheight = statusbarheight;
    }
    
    public void setUnderlineexpanded(final boolean underlineexpanded) {
        this.underlineexpanded = underlineexpanded;
    }
    
    public void setUnderlinelinks(final boolean underlinelinks) {
        this.underlinelinks = underlinelinks;
    }
    
    public void setScrollbarbgcolor(final Color scrollbarbgcolor) {
        this.scrollbarbgcolor = scrollbarbgcolor;
    }
    
    public void setTreenode(final Vector treenode) {
        this.treenode = treenode;
    }
    
    public void setImages(final Vector images) {
        this.images = images;
    }
    
    public void setBgimage(final Image bgimage) {
        this.bgimage = bgimage;
    }
    
    public void setBgimageposition(final SIRpoint bgimageposition) {
        this.bgimageposition = bgimageposition;
    }
}
