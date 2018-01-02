// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.awt.Canvas;

public class SingleList extends Canvas
{
    private Vector items;
    private int rows;
    private Image imageBuffer;
    private int imageWidth;
    private int imageHeight;
    private int marginH;
    private int marginV;
    private boolean isEffective;
    private SelectAction action;
    private static final int IMAGE_NORMAL_WIDTH = 92;
    private static final int IMAGE_NORMAL_HEIGHT = 40;
    
    public SingleList(final int rows) {
        this.items = new Vector();
        this.rows = 0;
        this.imageBuffer = null;
        this.imageWidth = 0;
        this.imageHeight = 0;
        this.marginH = 5;
        this.marginV = 4;
        this.isEffective = false;
        this.action = null;
        this.rows = rows;
    }
    
    public void addSelectAction(final SelectAction action) {
        this.action = action;
    }
    
    private int getImageWidth() {
        return this.imageWidth;
    }
    
    private int getImageHeight() {
        return this.imageHeight;
    }
    
    public void refresh() {
        this.prepareImage();
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.isEffective = true;
        if (this.imageBuffer == null) {
            this.prepareImage();
        }
        if (this.imageBuffer == null) {
            System.out.println("#error342, canvas null");
            return;
        }
        graphics.drawImage(this.imageBuffer, 0, 0, this);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 502: {
                this.handleMouseUp(event.x, event.y);
                return false;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public synchronized void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        this.repaint();
    }
    
    public synchronized int countItems() {
        return this.items.size();
    }
    
    public synchronized String getItem(final int n) {
        final ListData listData = this.items.elementAt(n);
        if (listData == null) {
            return null;
        }
        return listData.item;
    }
    
    public synchronized void addItem(final String s) {
        this.addItem(s, -1);
    }
    
    public synchronized void addItem(final String s, int n) {
        final ListData newData = this.createNewData(s);
        if (n < -1 || n >= this.items.size()) {
            n = -1;
        }
        if (n == -1) {
            this.items.addElement(newData);
        }
        else {
            this.items.insertElementAt(newData, n);
        }
        this.prepareImage();
        this.repaint();
    }
    
    public synchronized void delItem(final int n) {
        this.delItems(n, n);
        this.prepareImage();
        this.repaint();
    }
    
    public synchronized void delItems(final int n, final int n2) {
        for (int i = n2; i >= n; --i) {
            this.items.removeElementAt(i);
        }
        this.prepareImage();
        this.repaint();
    }
    
    public synchronized int getSelectedIndex() {
        for (int i = 0; i < this.items.size(); ++i) {
            if (((ListData)this.items.elementAt(i)).isSelected) {
                return i;
            }
        }
        return -1;
    }
    
    public synchronized String getSelectedItem() {
        final int selectedIndex = this.getSelectedIndex();
        return (selectedIndex < 0) ? null : this.getItem(selectedIndex);
    }
    
    public synchronized void deselect(final int n) {
        final ListData listData = this.items.elementAt(n);
        if (listData == null) {
            return;
        }
        listData.isSelected = false;
        this.prepareImage();
        this.repaint();
    }
    
    public synchronized int getStatus(final int n) {
        if (n >= this.items.size() || n < 0) {
            return 1;
        }
        return this.items.elementAt(n).state;
    }
    
    public synchronized void setStrikeThrough(final int n, final boolean b) {
        if (n >= this.items.size() || n < 0) {
            return;
        }
        final ListData listData = this.items.elementAt(n);
        if (b) {
            listData.state = 2;
        }
        else {
            listData.state = 1;
        }
        this.prepareImage();
        this.repaint();
    }
    
    public synchronized void setItemColor(final int n, final Color color) {
        if (n >= this.items.size() || n < 0) {
            return;
        }
        this.items.elementAt(n).color = color;
        this.prepareImage();
        this.repaint();
    }
    
    public synchronized void setItemFont(final int n, final Font font) {
        if (n >= this.items.size() || n < 0) {
            return;
        }
        this.items.elementAt(n).font = font;
        this.prepareImage();
        this.repaint();
    }
    
    public synchronized Dimension getPreferredSize() {
        if (this.imageBuffer == null) {
            final Dimension size = this.getParent().getSize();
            return new Dimension(size.width - this.marginH, size.height - this.marginV);
        }
        return new Dimension(this.getImageWidth(), this.getImageHeight());
    }
    
    public Dimension minimumSize(final int n) {
        return new Dimension(7, 7);
    }
    
    public Dimension getMinimumSize() {
        return this.minimumSize(0);
    }
    
    protected String paramString() {
        return super.paramString() + ",selected=" + this.getSelectedItem();
    }
    
    private Image calculateImageBuffer(final Image image) {
        final Dimension size = this.getParent().getSize();
        final int imageWidth = size.width - this.marginH;
        final int imageHeight = size.height - this.marginV;
        final int calculatePositions = this.calculatePositions();
        final int imageWidth2 = this.calculateMaxLength() + 3;
        this.imageWidth = imageWidth;
        if (imageWidth2 > imageWidth) {
            this.imageWidth = imageWidth2;
        }
        if (calculatePositions != (this.imageHeight = imageHeight)) {
            this.imageHeight = calculatePositions;
        }
        if (this.imageHeight < 1) {
            this.imageHeight = 40;
        }
        final Dimension size2 = this.getParent().getSize();
        final int imageWidth3 = size2.width - this.marginH;
        final int imageHeight2 = size2.height - this.marginV;
        if (this.imageWidth < imageWidth3) {
            this.imageWidth = imageWidth3;
        }
        if (this.imageHeight < imageHeight2) {
            this.imageHeight = imageHeight2;
        }
        int width;
        int height;
        if (image == null) {
            width = 0;
            height = 0;
        }
        else {
            width = image.getWidth(null);
            height = image.getHeight(null);
        }
        if (this.imageWidth == width && this.imageHeight == height) {
            return image;
        }
        if (this.imageWidth == 0 || this.imageHeight == 0) {
            return null;
        }
        this.printDebug("buffer width " + this.imageWidth);
        this.printDebug("buffer height " + this.imageHeight);
        return this.createImage(this.imageWidth, this.imageHeight);
    }
    
    private ListData createNewData(final String item) {
        final ListData listData = new ListData();
        listData.item = item;
        listData.color = null;
        listData.background = null;
        listData.state = 1;
        listData.isSelected = false;
        listData.font = this.getFont();
        return listData;
    }
    
    private void drawAllItems(final Image image) {
        final Graphics graphics = image.getGraphics();
        for (int i = 0; i < this.items.size(); ++i) {
            this.drawOneItem(graphics, image, (ListData)this.items.elementAt(i));
        }
    }
    
    private void drawOneItem(final Graphics graphics, final Image image, final ListData listData) {
        graphics.setFont(listData.font);
        Color color = this.getForeground();
        Color color2 = this.getBackground();
        if (listData.color != null) {
            color = listData.color;
        }
        if (listData.background != null) {
            color2 = listData.background;
        }
        if (listData.isSelected) {
            graphics.setColor(color);
            graphics.fillRect(listData.startX, listData.startY, image.getWidth(null), listData.endY - listData.startY);
            graphics.setColor(color2);
        }
        else {
            graphics.setColor(color2);
            graphics.fillRect(listData.startX, listData.startY, image.getWidth(null), listData.endY - listData.startY);
            graphics.setColor(color);
        }
        graphics.drawString(listData.item, listData.startX, listData.baseline + listData.startY);
        if (listData.state == 2) {
            final int n = (listData.startY + listData.endY) / 2;
            graphics.drawLine(listData.startX, n, listData.startX + listData.length, n);
        }
    }
    
    private int calculatePositions() {
        int endX = 2;
        int startY = 1;
        for (int i = 0; i < this.items.size(); ++i) {
            final ListData listData = this.items.elementAt(i);
            listData.startX = endX;
            listData.startY = startY;
            if (listData.font == null) {
                listData.font = this.getFont();
            }
            listData.baseline = this.getMaxBaseline(listData.font);
            final int endY = listData.startY + this.calculateFontHeight(listData.font);
            listData.length = this.calculateLength(listData.font, listData.item);
            listData.endX = endX;
            listData.endY = endY;
            endX = listData.endX;
            startY = endY + 0;
        }
        return startY + 6;
    }
    
    private int calculateMaxLength() {
        int length = 0;
        for (int i = 0; i < this.items.size(); ++i) {
            final ListData listData = this.items.elementAt(i);
            if (listData.font == null) {
                listData.font = this.getFont();
            }
            listData.length = this.calculateLength(listData.font, listData.item);
            if (listData.length > length) {
                length = listData.length;
            }
        }
        return length;
    }
    
    private int findIndex(final int n, final int n2) {
        for (int i = 0; i < this.items.size(); ++i) {
            final ListData listData = this.items.elementAt(i);
            if (n2 >= listData.startY && n2 < listData.endY) {
                return i;
            }
        }
        return -1;
    }
    
    private void deselectAllItems() {
        for (int i = 0; i < this.items.size(); ++i) {
            ((ListData)this.items.elementAt(i)).isSelected = false;
        }
        this.prepareImage();
        this.repaint();
    }
    
    private int calculateLength(final Font font, final String s) {
        return Toolkit.getDefaultToolkit().getFontMetrics(font).stringWidth(s);
    }
    
    private int calculateFontHeight(final Font font) {
        final int n = 0;
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        return n + fontMetrics.getAscent() + fontMetrics.getDescent();
    }
    
    private int getMaxBaseline(final Font font) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        if (fontMetrics == null) {
            System.out.println("font metrics is null");
        }
        return fontMetrics.getMaxAscent();
    }
    
    private void clearBuffer() {
        final Graphics graphics = this.imageBuffer.getGraphics();
        final int width = this.imageBuffer.getWidth(null);
        final int height = this.imageBuffer.getHeight(null);
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, width, height);
    }
    
    private void prepareImage() {
        this.imageBuffer = this.calculateImageBuffer(this.imageBuffer);
        if (this.imageBuffer == null) {
            return;
        }
        this.imageBuffer.getWidth(null);
        this.imageBuffer.getHeight(null);
        this.clearBuffer();
        this.drawAllItems(this.imageBuffer);
    }
    
    private void handleMouseUp(final int n, final int n2) {
        final int index = this.findIndex(n, n2);
        if (index < 0) {
            return;
        }
        final ListData listData = this.items.elementAt(index);
        this.deselectAllItems();
        this.printDebug("selected string: " + listData.item);
        listData.isSelected = true;
        this.prepareImage();
        this.repaint();
        if (this.action != null) {
            this.action.clickAction();
        }
    }
    
    private void printDebug(final String s) {
    }
}
