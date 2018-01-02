// 
// Decompiled by Procyon v0.5.30
// 

package Sirius.tree;

import java.awt.FontMetrics;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;

public class SIRtreegraphics
{
    private static Graphics pad;
    private static int i;
    private static int j;
    private static ImageObserver Iobs;
    
    public static SIRpoint DrawMenu(final Image image, final boolean b, final Color color, final int n, final int n2, final boolean b2, final boolean b3, int n3, int y, final int n4, int nodeindex, String parentid, String nodetext, Font nodefont, Color nodetxtcolor, final boolean b4, final boolean b5, Vector treenode, String nodeurl, String nodetarget, int nodeheight, int nodewidth, String nodestatustext, final boolean b6, Image normalimage, Image mouseoverimage, Image expandedimage, final boolean b7, final Color color2, final Color color3, final Color color4, SIRpoint siRpoint, final Vector vector, final Vector vector2) {
        SIRtreegraphics.pad = image.getGraphics();
        int n5;
        y = (n5 = y + nodeheight + n2);
        Image image2 = normalimage;
        if (b4) {
            image2 = expandedimage;
        }
        if (b5 && mouseoverimage != null) {
            image2 = mouseoverimage;
        }
        boolean b8 = false;
        if (treenode != null && treenode.elements().hasMoreElements()) {
            b8 = true;
        }
        siRpoint = Drawnode(image, nodeindex, nodetext, nodefont, nodetxtcolor, b7, color2, color3, b4, nodeheight, nodewidth, nodestatustext, b6, image2, n3, y, b, color, n, n4, b8, color4, b2, b3, nodeurl, siRpoint, vector);
        siRpoint.getY();
        if (treenode != null && b4) {
            n3 += n4;
            final Enumeration<Tree> elements = treenode.elements();
            while (elements.hasMoreElements()) {
                nodeindex = elements.nextElement().getNodeindex();
                final Treenode treenode2 = vector2.elementAt(nodeindex);
                parentid = treenode2.getParentid();
                nodetext = treenode2.getNodetext();
                nodefont = treenode2.getNodefont();
                nodetxtcolor = treenode2.getNodetxtcolor();
                final boolean expanded = treenode2.getExpanded();
                final boolean mouseover = treenode2.getMouseover();
                treenode = treenode2.getTreenode();
                nodeurl = treenode2.getNodeurl();
                nodetarget = treenode2.getNodetarget();
                nodeheight = treenode2.getNodeheight();
                nodewidth = treenode2.getNodewidth();
                nodestatustext = treenode2.getNodestatustext();
                final boolean displaystatus = treenode2.getDisplaystatus();
                normalimage = treenode2.getNormalimage();
                mouseoverimage = treenode2.getMouseoverimage();
                expandedimage = treenode2.getExpandedimage();
                final boolean highlight = treenode2.getHighlight();
                int n6;
                if (treenode.elements().hasMoreElements()) {
                    n6 = y + nodeheight - nodeheight / 2 - 4 + n2;
                }
                else {
                    n6 = y + nodeheight - nodeheight / 2 + n2;
                }
                if (b) {
                    drawline(image, n3 - n4 / 2, n5, 0, n6 - n5, color, n);
                }
                if (treenode.elements().hasMoreElements()) {
                    n5 = y + nodeheight - nodeheight / 2 + 5 + n2;
                }
                else {
                    n5 = y + nodeheight - nodeheight / 2 + n2;
                }
                siRpoint = DrawMenu(image, b, color, n, n2, b2, b3, n3, y, n4, nodeindex, parentid, nodetext, nodefont, nodetxtcolor, expanded, mouseover, treenode, nodeurl, nodetarget, nodeheight, nodewidth, nodestatustext, displaystatus, normalimage, mouseoverimage, expandedimage, highlight, color2, color3, color4, siRpoint, vector, vector2);
                y = siRpoint.getY();
            }
            n3 -= n4;
        }
        return siRpoint;
    }
    
    public static SIRpoint Drawnode(final Image image, final int n, final String s, final Font font, final Color color, final boolean b, final Color color2, final Color color3, final boolean b2, final int n2, final int n3, final String s2, final boolean b3, final Image image2, int n4, final int n5, final boolean b4, final Color color4, final int n6, final int n7, final boolean b5, final Color color5, final boolean b6, final boolean b7, final String s3, final SIRpoint siRpoint, final Vector vector) {
        int x = siRpoint.getX();
        if (n5 > -30 && n5 < image.getHeight(SIRtreegraphics.Iobs) + 30) {
            if (b4) {
                drawline(image, n4 - n7 / 2, n5 - n2 / 2, n7 / 2, 0, color4, n6);
            }
            if (b5 && b4) {
                if (b2) {
                    drawMinusbox(image, n4 - n7 / 2 - 4, n5 - n2 / 2 - 4, color5, color4);
                }
                else {
                    drawPlusbox(image, n4 - n7 / 2 - 4, n5 - n2 / 2 - 4, color5, color4);
                }
                vector.addElement(new Areadata(n, n5 - n2 / 2 - 4, n4 - n7 / 2 - 4, n5 - n2 / 2 + 4, n4 - n7 / 2 + 4, 2));
            }
            if (image2 != null) {
                SIRtreegraphics.pad.drawImage(image2, n4, n5 - image2.getHeight(SIRtreegraphics.Iobs), SIRtreegraphics.Iobs);
                if (b5) {
                    vector.addElement(new Areadata(n, n5 - image2.getHeight(SIRtreegraphics.Iobs), n4, n5, n4 + image2.getHeight(SIRtreegraphics.Iobs), 2));
                }
                n4 = n4 + image2.getWidth(SIRtreegraphics.Iobs) + 2;
            }
            SIRtreegraphics.pad.setFont(font);
            final FontMetrics fontMetrics = SIRtreegraphics.pad.getFontMetrics();
            final int n8 = n4 + 6 + fontMetrics.stringWidth(s);
            if (b) {
                SIRtreegraphics.pad.setColor(color3);
                SIRtreegraphics.pad.fillRect(n4, n5 - n2 + 5, n8 - n4, n2 - 5);
                drawline(image, n4, n5 - n2 + 5, n8 - n4 - 1, 0, color4, n6);
                drawline(image, n4, n5 - 1, n8 - n4 - 1, 0, color4, n6);
                drawline(image, n4, n5 - n2 + 5, 0, n2 - 6, color4, n6);
                drawline(image, n8 - 1, n5 - n2 + 5, 0, n2 - 6, color4, n6);
                SIRtreegraphics.pad.setColor(color2);
            }
            else {
                SIRtreegraphics.pad.setColor(color);
            }
            SIRtreegraphics.pad.setFont(font);
            SIRtreegraphics.pad.drawString(s, n4 + 3, n5 - 3);
            vector.addElement(new Areadata(n, n5 - n2, n4, n5, n8, 1));
            vector.addElement(new Areadata(n, n5 - n2, n4, n5, n8, 5));
            if (s3 != null && !s3.equals("") && !s3.equals(" ")) {
                vector.addElement(new Areadata(n, n5 - n2, n4, n5, n8, 3));
            }
            else if (b5) {
                vector.addElement(new Areadata(n, n5 - n2, n4, n5, n8, 2));
            }
            if (b6 && b2 && b5) {
                SIRtreegraphics.pad.drawLine(n4 + 3, n5, n8, n5);
            }
            if (b7 && !s3.equals("") && !s3.equals(null)) {
                SIRtreegraphics.pad.drawLine(n4 + 3, n5, n8, n5);
            }
            if (n4 + 3 + fontMetrics.stringWidth(s) > x) {
                x = n4 + 3 + fontMetrics.stringWidth(s);
            }
        }
        return new SIRpoint(x, n5);
    }
    
    public static void DrawLabel(final Image image, final String s, final Font font, final Color color, final int n, final int n2, final int n3) {
        (SIRtreegraphics.pad = image.getGraphics()).setColor(color);
        SIRtreegraphics.pad.setFont(font);
        final FontMetrics fontMetrics = SIRtreegraphics.pad.getFontMetrics();
        final int length = s.length();
        switch (n3) {
            case 1: {
                final int height = fontMetrics.getHeight();
                SIRtreegraphics.i = 0;
                while (SIRtreegraphics.i < length) {
                    SIRtreegraphics.pad.drawString(s.substring(SIRtreegraphics.i, SIRtreegraphics.i + 1), n, n2 - height * (length - SIRtreegraphics.i));
                    ++SIRtreegraphics.i;
                }
                break;
            }
            case 2: {
                final int n4 = 7 * fontMetrics.getHeight() / 10;
                SIRtreegraphics.i = 0;
                while (SIRtreegraphics.i < length) {
                    SIRtreegraphics.pad.drawString(s.substring(SIRtreegraphics.i, SIRtreegraphics.i + 1), n + SIRtreegraphics.i * n4, n2 - SIRtreegraphics.i * n4);
                    ++SIRtreegraphics.i;
                }
                break;
            }
            case 3: {
                final int height2 = fontMetrics.getHeight();
                SIRtreegraphics.i = 0;
                while (SIRtreegraphics.i < length) {
                    SIRtreegraphics.pad.drawString(s.substring(SIRtreegraphics.i, SIRtreegraphics.i + 1), n, n2 - SIRtreegraphics.i * height2);
                    ++SIRtreegraphics.i;
                }
                break;
            }
            case 4: {
                SIRtreegraphics.pad.drawString(s, n - fontMetrics.stringWidth(s), n2);
                break;
            }
            case 5: {
                SIRtreegraphics.pad.drawString(s, n, n2 + fontMetrics.getHeight());
                break;
            }
            default: {
                SIRtreegraphics.pad.drawString(s, n, n2);
                break;
            }
        }
    }
    
    public static void DrawBorder(final Image image, final int n, final int n2, final int n3, final Color color) {
        (SIRtreegraphics.pad = image.getGraphics()).setColor(color);
        SIRtreegraphics.i = 0;
        while (SIRtreegraphics.i < n3) {
            SIRtreegraphics.pad.drawRect(SIRtreegraphics.i, SIRtreegraphics.i, n - 1 - 2 * SIRtreegraphics.i, n2 - 1 - 2 * SIRtreegraphics.i);
            ++SIRtreegraphics.i;
        }
    }
    
    public static void DrawStatusbar(final Image image, final String s, final Font font, final Color color, final Color color2, final int n, final int n2, final int n3, final int n4) {
        (SIRtreegraphics.pad = image.getGraphics()).setColor(color2);
        SIRtreegraphics.pad.fillRect(n3, n4, n2, n);
        SIRtreegraphics.pad.setColor(new Color(50, 50, 50));
        SIRtreegraphics.pad.drawRect(n3, n4, n2, n);
        SIRtreegraphics.pad.drawRect(n3 + 1, n4 + 1, n2 - 2, n - 2);
        SIRtreegraphics.pad.setColor(new Color(225, 225, 225));
        SIRtreegraphics.pad.drawLine(n3 + n2, n4, n3 + n2, n4 + n);
        SIRtreegraphics.pad.drawLine(n3 + n2 - 1, n4 + 1, n3 + n2 - 1, n4 + n);
        SIRtreegraphics.pad.drawLine(n3 + 1, n4 + n, n3 + n2, n4 + n);
        SIRtreegraphics.pad.drawLine(n3 + 2, n4 + n - 1, n3 + n2, n4 + n - 1);
        SIRtreegraphics.pad.setFont(font);
        SIRtreegraphics.pad.setColor(color);
        SIRtreegraphics.pad.drawString(s, n3 + 3, n4 + n - 2);
    }
    
    public static void drawline(final Image image, final int n, final int n2, final int n3, final int n4, final Color color, final int n5) {
        (SIRtreegraphics.pad = image.getGraphics()).setColor(color);
        switch (n5) {
            case 1: {
                SIRtreegraphics.pad.drawLine(n, n2, n + n3, n2 + n4);
                break;
            }
            case 2: {
                if (n3 == 0) {
                    for (int i = 0; i < n4 / 2; ++i) {
                        SIRtreegraphics.pad.drawLine(n, n2 + i * 2, n, n2 + i * 2);
                    }
                    SIRtreegraphics.pad.drawLine(n, n2 + n4, n, n2 + n4);
                    break;
                }
                for (int j = 0; j < n3 / 2; ++j) {
                    SIRtreegraphics.pad.drawLine(n + j * 2, n2, n + j * 2, n2);
                }
                SIRtreegraphics.pad.drawLine(n + n3, n2, n + n3, n2);
                break;
            }
            case 3: {
                if (n3 == 0) {
                    for (int k = 0; k < n4 / 3; ++k) {
                        SIRtreegraphics.pad.drawLine(n, n2 + k * 3, n, n2 + k * 3 + 1);
                    }
                    SIRtreegraphics.pad.drawLine(n, n2 + n4, n, n2 + n4);
                    break;
                }
                for (int l = 0; l < n3 / 3; ++l) {
                    SIRtreegraphics.pad.drawLine(n + l * 3, n2, n + l * 3 + 1, n2);
                }
                SIRtreegraphics.pad.drawLine(n + n3, n2, n + n3, n2);
                break;
            }
            case 4: {
                if (n3 == 0) {
                    for (int n6 = 0; n6 < n4 / 5; ++n6) {
                        SIRtreegraphics.pad.drawLine(n, n2 + n6 * 5, n, n2 + n6 * 5 + 3);
                    }
                    SIRtreegraphics.pad.drawLine(n, n2 + n4, n, n2 + n4);
                    break;
                }
                for (int n7 = 0; n7 < n3 / 5; ++n7) {
                    SIRtreegraphics.pad.drawLine(n + n7 * 5, n2, n + n7 * 5 + 3, n2);
                }
                SIRtreegraphics.pad.drawLine(n + n3, n2, n + n3, n2);
                break;
            }
            default: {
                SIRtreegraphics.pad.drawLine(n, n2, n + n3, n2 + n4);
                break;
            }
        }
    }
    
    public static void drawMinusbox(final Image image, final int n, final int n2, final Color color, final Color color2) {
        (SIRtreegraphics.pad = image.getGraphics()).setColor(color);
        SIRtreegraphics.pad.fillRect(n, n2, 8, 8);
        SIRtreegraphics.pad.setColor(color2);
        SIRtreegraphics.pad.drawRect(n, n2, 8, 8);
        SIRtreegraphics.pad.drawLine(n + 2, n2 + 4, n + 6, n2 + 4);
    }
    
    public static void drawPlusbox(final Image image, final int n, final int n2, final Color color, final Color color2) {
        (SIRtreegraphics.pad = image.getGraphics()).setColor(color);
        SIRtreegraphics.pad.fillRect(n, n2, 8, 8);
        SIRtreegraphics.pad.setColor(color2);
        SIRtreegraphics.pad.drawRect(n, n2, 8, 8);
        SIRtreegraphics.pad.drawLine(n + 2, n2 + 4, n + 6, n2 + 4);
        SIRtreegraphics.pad.drawLine(n + 4, n2 + 2, n + 4, n2 + 6);
    }
}
