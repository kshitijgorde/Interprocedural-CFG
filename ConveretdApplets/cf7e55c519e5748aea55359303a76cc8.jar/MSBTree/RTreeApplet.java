// 
// Decompiled by Procyon v0.5.30
// 

package MSBTree;

import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.applet.Applet;

public class RTreeApplet extends Applet
{
    static final int LEFT_BORDER = 0;
    static final int TOP_BORDER = 2;
    static final int BOTTOM_BORDER = 3;
    static final int RIGHT_BORDER = 4;
    private boolean started;
    public RTreeList tree;
    borderCanvas appBorderLeft;
    borderCanvas appBorderRight;
    borderCanvas appBorderTop;
    borderCanvas appBorderBottom;
    public static final int NO_BORDER = 0;
    public static final int BORDER_LINE = 1;
    public static final int BORDER_RAISED = 2;
    public static final int BORDER_LOWERED = 3;
    public static final int BORDER_DOUBLE = 4;
    public static final int BORDER_SHADOW = 5;
    public static final int BORDER_LINE2 = 6;
    private int upperBorder;
    private int bottomBorder;
    private int leftBorder;
    private int rightBorder;
    public int borderType;
    public Color pageBackColor;
    public Color borderColor;
    
    public void clearTree(final String nodeName) {
        RNode n = null;
        if (nodeName.length() > 0) {
            n = this.tree.findNode(this.tree.Root, nodeName);
        }
        if (n == null) {
            n = this.tree.Root;
        }
        this.tree.clear(n);
        this.tree.expandNode(this.tree.Root.Name, false);
        this.tree.expandNode(this.tree.Root.Name, true);
        this.tree.refresh();
    }
    
    public void setBorderBackground() {
        if (this.borderType == 1 || this.borderType == 6) {
            this.appBorderLeft.setBackground(this.borderColor);
            this.appBorderTop.setBackground(this.borderColor);
            this.appBorderBottom.setBackground(this.borderColor);
            this.appBorderRight.setBackground(this.borderColor);
        }
        if (this.borderType == 3) {
            this.appBorderLeft.setBackground(Color.darkGray);
            this.appBorderTop.setBackground(Color.darkGray);
            this.appBorderBottom.setBackground(Color.white);
            this.appBorderRight.setBackground(Color.white);
        }
        if (this.borderType == 2 || this.borderType == 4) {
            this.appBorderBottom.setBackground(Color.darkGray);
            this.appBorderRight.setBackground(Color.darkGray);
            this.appBorderTop.setBackground(Color.white);
            this.appBorderLeft.setBackground(Color.white);
        }
        if (this.borderType == 4) {
            this.appBorderBottom.setBackground(this.borderColor);
            this.appBorderRight.setBackground(this.borderColor);
            this.appBorderTop.setBackground(this.borderColor);
            this.appBorderLeft.setBackground(this.borderColor);
        }
    }
    
    public RTreeApplet() {
        this.started = false;
        this.tree = new RTreeList();
        this.appBorderLeft = new borderCanvas(0);
        this.appBorderRight = new borderCanvas(4);
        this.appBorderTop = new borderCanvas(2);
        this.appBorderBottom = new borderCanvas(3);
        this.upperBorder = 0;
        this.bottomBorder = 0;
        this.leftBorder = 0;
        this.rightBorder = 0;
        this.borderType = 1;
        this.pageBackColor = Color.white;
        this.borderColor = Color.black;
    }
    
    private void calculateBorderSize() {
        if (this.borderType == 1 || this.borderType == 6) {
            this.upperBorder = 1;
            this.leftBorder = 1;
            this.bottomBorder = 1;
            this.rightBorder = 1;
            if (this.borderType == 6) {
                this.upperBorder = 2;
                this.leftBorder = 2;
                this.bottomBorder = 2;
                this.rightBorder = 2;
            }
        }
        if (this.borderType == 3) {
            this.upperBorder = 1;
            this.leftBorder = 1;
            this.bottomBorder = 1;
            this.rightBorder = 1;
        }
        if (this.borderType == 2 || this.borderType == 4) {
            this.upperBorder = 1;
            this.leftBorder = 1;
            this.bottomBorder = 1;
            this.rightBorder = 1;
        }
        if (this.borderType == 4) {
            this.upperBorder = 4;
            this.leftBorder = 4;
            this.bottomBorder = 4;
            this.rightBorder = 4;
        }
        if (this.borderType == 5) {
            this.upperBorder = 0;
            this.leftBorder = 0;
            this.bottomBorder = 4;
            this.rightBorder = 4;
        }
    }
    
    public void setParam(final String nodeName, final String Param, final String Value) {
        RNode n = null;
        if (nodeName.length() > 0) {
            n = this.tree.findNode(this.tree.Root, nodeName);
        }
        this.tree.processParameter(n, Param.toUpperCase(), Value);
    }
    
    public void start() {
        if (this.started) {
            return;
        }
        this.started = true;
        new Thread(this.tree).start();
    }
    
    private String getParameter(final String key, final String def) {
        if (this.getParameter(key) != null) {
            return this.getParameter(key);
        }
        return def;
    }
    
    public void refreshTree() {
        this.tree.refresh();
    }
    
    public void init() {
        if (this.started) {
            return;
        }
        super.init();
        this.tree.parentApplet = this;
        String param = this.getStringParam("TREE_PAGE_BACK_COLOR", "WHITE");
        if (param.length() > 0) {
            this.pageBackColor = this.tree.convertColor(param);
        }
        param = this.getStringParam("TREE_BORDER_COLOR", "BLACK");
        if (param.length() > 0) {
            this.borderColor = this.tree.convertColor(param);
        }
        param = this.getStringParam("TREE_LOADING_TEXT", "");
        if (param.length() > 0) {
            this.tree.loadingText = param;
        }
        param = this.getStringParam("TREE_LOADING_FONT", "");
        if (param.length() > 0) {
            this.tree.loadingFont = this.tree.convertFont(param);
        }
        param = this.getStringParam("TREE_LOADING_IMAGE", "");
        if (param.length() > 0) {
            this.tree.loadingImage = this.tree.loadImageUrl(param);
        }
        param = this.getStringParam("TREE_BORDER", "");
        if (param.length() > 0) {
            if (param.compareTo("NO") == 0) {
                this.borderType = 0;
            }
            if (param.compareTo("RAISED") == 0) {
                this.borderType = 2;
            }
            if (param.compareTo("LINE") == 0) {
                this.borderType = 1;
            }
            if (param.compareTo("LINE2") == 0) {
                this.borderType = 6;
            }
            if (param.compareTo("LOWERED") == 0) {
                this.borderType = 3;
            }
            if (param.compareTo("SHADOW") == 0) {
                this.borderType = 5;
            }
            if (param.compareTo("DOUBLE") == 0) {
                this.borderType = 4;
            }
        }
        this.setLayout(null);
        this.calculateBorderSize();
        if (this.leftBorder > 0) {
            this.appBorderLeft.setBounds(0, this.upperBorder, this.leftBorder, this.getSize().height - this.bottomBorder - this.upperBorder);
            this.add(this.appBorderLeft);
        }
        if (this.rightBorder > 0) {
            this.appBorderRight.setBounds(this.getSize().width - this.rightBorder, this.upperBorder, this.rightBorder, this.getSize().height - this.bottomBorder - this.upperBorder);
            this.add(this.appBorderRight);
        }
        if (this.upperBorder > 0) {
            this.appBorderTop.setBounds(0, 0, this.getSize().width, this.upperBorder);
            this.add(this.appBorderTop);
        }
        if (this.bottomBorder > 0) {
            this.appBorderBottom.setBounds(0, this.getSize().height - this.bottomBorder, this.getSize().width, this.bottomBorder);
            this.add(this.appBorderBottom);
        }
        this.setBorderBackground();
        this.tree.setBounds(this.leftBorder, this.upperBorder, this.getSize().width - this.rightBorder - this.leftBorder, this.getSize().height - this.upperBorder - this.bottomBorder);
        this.add(this.tree);
    }
    
    private String getStringParam(final String Param, final String def) {
        return this.getParameter(Param, def);
    }
    
    public void loadNode(final String nodeName, final String sFile) {
        this.clearTree(nodeName);
        RNode n = null;
        if (nodeName.length() > 0) {
            n = this.tree.findNode(this.tree.Root, nodeName);
        }
        if (n == null) {
            n = this.tree.Root;
        }
        n.childrenDefinitionFile = sFile;
        this.tree.createTreeFromFile(n, sFile);
        this.tree.expandNode(this.tree.Root.Name, false);
        this.tree.expandNode(this.tree.Root.Name, true);
        this.tree.refresh();
    }
    
    private class borderCanvas extends Canvas
    {
        int borderSide;
        
        public borderCanvas(final int side) {
            RTreeApplet.this.getClass();
            this.borderSide = 0;
            this.borderSide = side;
        }
        
        public void paint(Graphics g) {
            if (RTreeApplet.this.borderType == 4) {
                if (this.borderSide == 2) {
                    g.setColor(Color.white);
                    g.drawLine(0, 0, this.getSize().width, 0);
                    g.setColor(Color.darkGray);
                    g.drawLine(3, 3, this.getSize().width - 4, 3);
                    g.setColor(Color.white);
                    g.drawLine(0, 0, 0, this.getSize().height);
                    g.setColor(Color.darkGray);
                    g.drawLine(this.getSize().width - 1, 0, this.getSize().width - 1, this.getSize().height);
                }
                if (this.borderSide == 0) {
                    g.setColor(Color.darkGray);
                    g.drawLine(3, 0, 3, this.getSize().height);
                    g.setColor(Color.white);
                    g.drawLine(0, 0, 0, this.getSize().height);
                }
                if (this.borderSide == 4) {
                    g.setColor(Color.white);
                    g.drawLine(this.getSize().width - 4, 0, this.getSize().width - 4, this.getSize().height);
                    g.setColor(Color.darkGray);
                    g.drawLine(this.getSize().width - 1, 0, this.getSize().width - 1, this.getSize().height);
                }
                if (this.borderSide == 3) {
                    g.setColor(Color.white);
                    g.drawLine(3, 0, this.getSize().width - 4, 0);
                    g.setColor(Color.darkGray);
                    g.drawLine(0, this.getSize().height - 1, this.getSize().width, this.getSize().height - 1);
                    g.setColor(Color.white);
                    g.drawLine(0, 0, 0, this.getSize().height);
                    g.setColor(Color.darkGray);
                    g.drawLine(this.getSize().width - 1, 0, this.getSize().width - 1, this.getSize().height);
                }
            }
            if (RTreeApplet.this.borderType == 5) {
                if (this.borderSide == 3) {
                    g.setColor(RTreeApplet.this.pageBackColor);
                    g.fillRect(0, 0, 6, 4);
                    g.setColor(Color.darkGray);
                    g.fillRect(6, 0, this.getSize().width - 6, 4);
                }
                if (this.borderSide == 4) {
                    g = RTreeApplet.this.appBorderRight.getGraphics();
                    g.setColor(RTreeApplet.this.pageBackColor);
                    g.fillRect(0, 0, 4, 6);
                    g.setColor(Color.darkGray);
                    g.fillRect(0, 6, 4, RTreeApplet.this.appBorderRight.getSize().height);
                }
            }
        }
    }
}
