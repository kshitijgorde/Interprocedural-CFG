import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class navListItem
{
    static int kFileCode;
    static int kClosedFolderCode;
    static int kOpenFolderCode;
    private String label;
    private String partial_url;
    private Vector subItems;
    private int itemCode;
    Color myColor;
    
    navListItem(final String s, final String s2, final int itemCode, final Color myColor) {
        this.label = new String(s);
        this.partial_url = new String(s2);
        this.subItems = new Vector();
        this.itemCode = itemCode;
        this.myColor = myColor;
    }
    
    public void addItem(final navListItem navListItem) {
        this.subItems.addElement(navListItem);
    }
    
    public int countItems() {
        int n = 1;
        if (this.itemCode == navListItem.kOpenFolderCode) {
            for (int size = this.subItems.size(), i = 0; i < size; ++i) {
                n += ((navListItem)this.subItems.elementAt(i)).countItems();
            }
        }
        return n;
    }
    
    public int drawItems(final int n, final int n2, int drawItems, final int n3, int n4, final Graphics graphics, final WStSContents wStSContents) {
        if (++drawItems >= n && drawItems <= n2 && this.label.length() > 0) {
            final int n5 = wStSContents.itemHeight * (drawItems - n) + n3;
            if (this.itemCode != navListItem.kFileCode) {
                this.drawArrow(wStSContents.arrowLeft, n5 + wStSContents.itemTopOffset, graphics);
                final Color color = graphics.getColor();
                graphics.setColor(Color.black);
                graphics.drawString(this.label, n4, n5 + wStSContents.itemTopOffset + graphics.getFontMetrics().getAscent());
                graphics.setColor(color);
            }
            else {
                graphics.drawLine(n4, n5 + wStSContents.itemTopOffset + graphics.getFontMetrics().getAscent() + 1, n4 + graphics.getFontMetrics().stringWidth(this.label), n5 + wStSContents.itemTopOffset + graphics.getFontMetrics().getAscent() + 1);
                graphics.drawString(this.label, n4, n5 + wStSContents.itemTopOffset + graphics.getFontMetrics().getAscent());
            }
            final Color color2 = graphics.getColor();
            graphics.setColor(Color.lightGray);
            graphics.drawLine(wStSContents.mySize.x, n5 + wStSContents.itemHeight, wStSContents.mySize.x + wStSContents.mySize.width - 1, n5 + wStSContents.itemHeight);
            graphics.setColor(color2);
        }
        n4 += graphics.getFontMetrics().getHeight() / 4 * 3;
        if (this.itemCode == navListItem.kOpenFolderCode) {
            for (int size = this.subItems.size(), i = 0; i < size; ++i) {
                drawItems = ((navListItem)this.subItems.elementAt(i)).drawItems(n, n2, drawItems, n3, n4, graphics, wStSContents);
            }
        }
        return drawItems;
    }
    
    public int handleHit(final int n, final int n2, int handleHit, final int n3, int n4, final int n5, final int n6, final Graphics graphics, final WStSContents wStSContents) {
        if (++handleHit >= n && handleHit <= n2 && this.label.length() > 0) {
            final int n7 = wStSContents.itemHeight * (handleHit - n) + n3;
            if (this.itemCode != navListItem.kFileCode && new Rectangle(wStSContents.arrowLeft, n7 + wStSContents.itemTopOffset, graphics.getFontMetrics().getHeight(), graphics.getFontMetrics().getHeight()).inside(n5, n6)) {
                this.toggleFolder();
                return -1;
            }
            if (new Rectangle(n4, n7 + wStSContents.itemTopOffset, graphics.getFontMetrics().stringWidth(this.label), graphics.getFontMetrics().getHeight()).inside(n5, n6)) {
                if (this.itemCode == navListItem.kFileCode) {
                    wStSContents.showDocument(wStSContents.MyStringToURL(wStSContents.getCodeBase().toString() + this.partial_url));
                }
                else {
                    this.toggleFolder();
                }
                return -1;
            }
        }
        n4 += graphics.getFontMetrics().getHeight() / 4 * 3;
        if (this.itemCode == navListItem.kOpenFolderCode) {
            for (int size = this.subItems.size(), i = 0; i < size; ++i) {
                handleHit = ((navListItem)this.subItems.elementAt(i)).handleHit(n, n2, handleHit, n3, n4, n5, n6, graphics, wStSContents);
                if (handleHit == -1) {
                    return handleHit;
                }
            }
        }
        return handleHit;
    }
    
    public void toggleFolder() {
        if (this.itemCode == navListItem.kClosedFolderCode) {
            this.itemCode = navListItem.kOpenFolderCode;
        }
        else if (this.itemCode == navListItem.kOpenFolderCode) {
            this.itemCode = navListItem.kClosedFolderCode;
        }
    }
    
    public void drawArrow(final int n, final int n2, final Graphics graphics) {
        final Polygon polygon = new Polygon();
        final int n3 = n + 1;
        final int n4 = n2 + 1;
        final int n5 = graphics.getFontMetrics().getHeight() - 2;
        final int n6 = n5 / 4;
        final int n7 = n5 / 2;
        if (this.itemCode == navListItem.kClosedFolderCode) {
            final int n8 = n3 + n6;
            polygon.addPoint(n8, n4);
            polygon.addPoint(n8 + n7, n4 + n7);
            polygon.addPoint(n8, n4 + n5);
            polygon.addPoint(n8, n4);
        }
        else if (this.itemCode == navListItem.kOpenFolderCode) {
            final int n9 = n4 + n6;
            polygon.addPoint(n3, n9);
            polygon.addPoint(n3 + n5, n9);
            polygon.addPoint(n3 + n7, n9 + n7);
            polygon.addPoint(n3, n9);
        }
        final Color color = graphics.getColor();
        graphics.setColor(this.myColor);
        graphics.fillPolygon(polygon);
        graphics.setColor(color);
    }
    
    public void selfDestruct() {
        for (int i = 0; i < this.subItems.size(); ++i) {
            ((navListItem)this.subItems.elementAt(i)).selfDestruct();
        }
        this.subItems.removeAllElements();
    }
    
    static {
        navListItem.kFileCode = 0;
        navListItem.kClosedFolderCode = 1;
        navListItem.kOpenFolderCode = 2;
    }
}
