// 
// Decompiled by Procyon v0.5.30
// 

package Sirius.tree;

import java.awt.Image;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;

public class Treenode implements Serializable
{
    private String nodeid;
    private String parentid;
    private String nodetext;
    private Font nodefont;
    private Color nodetxtcolor;
    private boolean expanded;
    private boolean mouseover;
    private Vector treenode;
    private String nodeurl;
    private String nodetarget;
    private int nodeheight;
    private int nodewidth;
    private String nodestatustext;
    private boolean displaystatus;
    private Image normalimage;
    private Image mouseoverimage;
    private Image expandedimage;
    private boolean highlight;
    
    public Treenode(final String nodeid, final String parentid, final String nodetext, final Font nodefont, final Color nodetxtcolor, final boolean expanded, final boolean mouseover, final String nodeurl, final String nodetarget, final int nodeheight, final int nodewidth, final String nodestatustext, final boolean displaystatus, final Image normalimage, final Image mouseoverimage, final Image expandedimage, final Vector treenode, final boolean highlight) {
        this.nodeid = nodeid;
        this.parentid = parentid;
        this.nodetext = nodetext;
        this.nodefont = nodefont;
        this.nodetxtcolor = nodetxtcolor;
        this.expanded = expanded;
        this.mouseover = mouseover;
        this.treenode = treenode;
        this.nodeurl = nodeurl;
        this.nodetarget = nodetarget;
        this.nodeheight = nodeheight;
        this.nodewidth = nodewidth;
        this.nodestatustext = nodestatustext;
        this.displaystatus = displaystatus;
        this.normalimage = normalimage;
        this.mouseoverimage = mouseoverimage;
        this.expandedimage = expandedimage;
        this.highlight = highlight;
    }
    
    public String getNodeid() {
        return this.nodeid;
    }
    
    public String getParentid() {
        return this.parentid;
    }
    
    public String getNodetext() {
        return this.nodetext;
    }
    
    public Font getNodefont() {
        return this.nodefont;
    }
    
    public Color getNodetxtcolor() {
        return this.nodetxtcolor;
    }
    
    public boolean getExpanded() {
        return this.expanded;
    }
    
    public boolean getMouseover() {
        return this.mouseover;
    }
    
    public Vector getTreenode() {
        return this.treenode;
    }
    
    public String getNodeurl() {
        return this.nodeurl;
    }
    
    public String getNodetarget() {
        return this.nodetarget;
    }
    
    public int getNodeheight() {
        return this.nodeheight;
    }
    
    public int getNodewidth() {
        return this.nodewidth;
    }
    
    public String getNodestatustext() {
        return this.nodestatustext;
    }
    
    public boolean getDisplaystatus() {
        return this.displaystatus;
    }
    
    public Image getNormalimage() {
        return this.normalimage;
    }
    
    public Image getMouseoverimage() {
        return this.mouseoverimage;
    }
    
    public Image getExpandedimage() {
        return this.expandedimage;
    }
    
    public boolean getHighlight() {
        return this.highlight;
    }
    
    public void setNodeid(final String nodeid) {
        this.nodeid = nodeid;
    }
    
    public void setParentid(final String parentid) {
        this.parentid = parentid;
    }
    
    public void setNodetext(final String nodetext) {
        this.nodetext = nodetext;
    }
    
    public void setNodefont(final Font nodefont) {
        this.nodefont = nodefont;
    }
    
    public void setNodetxtcolor(final Color nodetxtcolor) {
        this.nodetxtcolor = nodetxtcolor;
    }
    
    public void setExpanded(final boolean expanded) {
        this.expanded = expanded;
    }
    
    public void setMouseover(final boolean mouseover) {
        this.mouseover = mouseover;
    }
    
    public void setTreenode(final Vector treenode) {
        this.treenode = treenode;
    }
    
    public void setNodeurl(final String nodeurl) {
        this.nodeurl = nodeurl;
    }
    
    public void setNodetarget(final String nodetarget) {
        this.nodetarget = nodetarget;
    }
    
    public void setNodeheight(final int nodeheight) {
        this.nodeheight = nodeheight;
    }
    
    public void setNodewidth(final int nodewidth) {
        this.nodewidth = nodewidth;
    }
    
    public void setNodestatustext(final String nodestatustext) {
        this.nodestatustext = nodestatustext;
    }
    
    public void setDisplaystatus(final boolean displaystatus) {
        this.displaystatus = displaystatus;
    }
    
    public void setNormalimage(final Image normalimage) {
        this.normalimage = normalimage;
    }
    
    public void setMouseoverimage(final Image mouseoverimage) {
        this.mouseoverimage = mouseoverimage;
    }
    
    public void setExpandedimage(final Image expandedimage) {
        this.expandedimage = expandedimage;
    }
    
    public void setHighlight(final boolean highlight) {
        this.highlight = highlight;
    }
}
