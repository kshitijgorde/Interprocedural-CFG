// 
// Decompiled by Procyon v0.5.30
// 

package MSBTree;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class RNode
{
    public boolean expanded;
    public boolean selected;
    public Image icon;
    public boolean raisedEffectOnSelection;
    public Image expandedIcon;
    public Font font;
    public String text;
    public Color color;
    public Color selectedColor;
    public Color backColor;
    public Color selectedBackColor;
    public String Name;
    public Object userValue;
    public RNode parent;
    public String tipText;
    public boolean childrenDefinitionLoaded;
    public String childrenDefinitionFile;
    public Image backImage;
    public boolean centerText;
    public static final int SBORDER_NO = 0;
    public static final int SBORDER_RAISED = 1;
    public static final int SBORDER_LINE = 2;
    public int submenuBorder;
    public Image submenuImage;
    public Color submenuColor;
    public Color submenuBorderColor;
    public boolean submenuInverted;
    public String target;
    public int xPos;
    public int yPos;
    public int hSize;
    public int wSize;
    public int level;
    public int xPosText;
    public int wSizeText;
    private Vector Children;
    
    public RNode getChild(final int n) {
        return this.Children.elementAt(n);
    }
    
    public void draw(final Graphics g, final int x, final int y, final int h, final int w) {
        Image i = this.icon;
        this.xPos = x;
        this.yPos = y;
        this.hSize = h;
        this.wSize = w;
        if (this.expanded && this.expandedIcon != null) {
            i = this.expandedIcon;
        }
        if (this.backColor != null && !this.selected) {
            g.setColor(this.backColor);
            g.fillRect(x, y, w, h);
        }
        if (this.selectedBackColor != null && this.selected) {
            g.setColor(this.selectedBackColor);
            g.fillRect(x, y, w, h);
        }
        int iconWidth = 0;
        if (i != null) {
            int toCenterY = (h - i.getHeight(null)) / 2;
            if (toCenterY < 0) {
                toCenterY = 0;
            }
            g.drawImage(i, x, y + toCenterY, null);
            iconWidth = i.getWidth(null);
            if (iconWidth == -1) {
                iconWidth = 0;
            }
        }
        if (this.backImage != null) {
            int toCenterY = (h - this.backImage.getHeight(null)) / 2;
            if (toCenterY < 0) {
                toCenterY = 0;
            }
            int toCenterX = 0;
            if (this.centerText) {
                toCenterX = (w - (iconWidth + 6) - this.backImage.getWidth(null)) / 2;
            }
            if (toCenterX < 0) {
                toCenterX = 0;
            }
            g.drawImage(this.backImage, x + iconWidth + 6 + toCenterX, y + toCenterY, null);
        }
        g.setFont(this.font);
        g.setColor(this.color);
        if (this.selected) {
            g.setColor(this.selectedColor);
        }
        final int textH = g.getFontMetrics().getHeight();
        final int textW = g.getFontMetrics().stringWidth(this.text);
        int toCenterY2 = 0;
        int toCenterX2 = 0;
        if (h > textH) {
            toCenterY2 = (h - textH) / 2;
        }
        if (this.centerText) {
            toCenterX2 = (w - (iconWidth + 6) - textW) / 2;
            if (toCenterX2 < 0) {
                toCenterX2 = 0;
            }
        }
        this.xPosText = 0;
        this.wSizeText = 0;
        if (this.text.length() > 0) {
            g.drawString(this.text, x + iconWidth + 6 + toCenterX2, y + textH + toCenterY2 - 2);
            this.xPosText = x + iconWidth + 6 + toCenterX2;
            this.wSizeText = textW;
        }
        if (this.raisedEffectOnSelection && this.selected) {
            g.setColor(Color.white);
            g.drawLine(toCenterX2 + x + iconWidth + 3, y + toCenterY2 - 2, toCenterX2 + x + iconWidth + 3, y + toCenterY2 + textH + 2);
            g.drawLine(toCenterX2 + x + iconWidth + 3, y + toCenterY2 - 2, toCenterX2 + x + iconWidth + 6 + textW + 5, y + toCenterY2 - 2);
            g.setColor(Color.darkGray);
            g.drawLine(toCenterX2 + x + iconWidth + 6 + textW + 5, y + toCenterY2 - 2, toCenterX2 + x + iconWidth + 6 + textW + 5, y + toCenterY2 + textH + 2);
            g.drawLine(toCenterX2 + x + iconWidth + 3, y + toCenterY2 + textH + 2, toCenterX2 + x + iconWidth + 6 + textW + 5, y + toCenterY2 + textH + 2);
        }
    }
    
    public void deleteChildren() {
        this.Children.removeAllElements();
        this.childrenDefinitionLoaded = false;
        this.expanded = false;
    }
    
    public RNode() {
        this.expanded = false;
        this.selected = false;
        this.icon = null;
        this.raisedEffectOnSelection = false;
        this.expandedIcon = null;
        this.font = new Font("Arial", 0, 10);
        this.text = "";
        this.color = Color.black;
        this.selectedColor = Color.black;
        this.Name = "";
        this.userValue = null;
        this.tipText = "";
        this.childrenDefinitionLoaded = false;
        this.childrenDefinitionFile = "";
        this.backImage = null;
        this.centerText = false;
        this.submenuBorder = 0;
        this.submenuImage = null;
        this.submenuColor = null;
        this.submenuBorderColor = Color.black;
        this.submenuInverted = false;
        this.target = "";
        this.level = 0;
        this.Children = new Vector(10, 5);
    }
    
    public void addChild(final RNode c) {
        this.Children.addElement(c);
        c.parent = this;
        c.expanded = false;
    }
    
    public int getChildrenNumber() {
        return this.Children.size();
    }
    
    public boolean hasChildren() {
        return this.getChildrenNumber() > 0 || this.childrenDefinitionFile.length() > 0;
    }
    
    public void deleteChild(final int c) {
        this.Children.removeElementAt(c);
    }
}
