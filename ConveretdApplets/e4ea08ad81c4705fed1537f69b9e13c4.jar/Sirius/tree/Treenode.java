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
    
    public Image getExpandedimage() {
        return this.expandedimage;
    }
    
    public boolean getExpanded() {
        return this.expanded;
    }
    
    public void setExpanded(final boolean expanded) {
        this.expanded = expanded;
    }
    
    public int getNodewidth() {
        return this.nodewidth;
    }
    
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
    
    public Vector getTreenode() {
        return this.treenode;
    }
    
    public Color getNodetxtcolor() {
        return this.nodetxtcolor;
    }
    
    public String getNodetarget() {
        return this.nodetarget;
    }
    
    public void setTreenode(final Vector treenode) {
        this.treenode = treenode;
    }
    
    public boolean getDisplaystatus() {
        return this.displaystatus;
    }
    
    public Image getMouseoverimage() {
        return this.mouseoverimage;
    }
    
    public boolean getMouseover() {
        return this.mouseover;
    }
    
    public String getNodeurl() {
        return this.nodeurl;
    }
    
    public void setMouseover(final boolean mouseover) {
        this.mouseover = mouseover;
    }
    
    public int getNodeheight() {
        return this.nodeheight;
    }
    
    public Image getNormalimage() {
        return this.normalimage;
    }
    
    public boolean getHighlight() {
        return this.highlight;
    }
    
    public void setHighlight(final boolean highlight) {
        this.highlight = highlight;
    }
    
    public Font getNodefont() {
        return this.nodefont;
    }
    
    public String getNodeid() {
        return this.nodeid;
    }
    
    public String getParentid() {
        return this.parentid;
    }
    
    public String getNodestatustext() {
        return this.nodestatustext;
    }
    
    public String getNodetext() {
        return this.nodetext;
    }
}
