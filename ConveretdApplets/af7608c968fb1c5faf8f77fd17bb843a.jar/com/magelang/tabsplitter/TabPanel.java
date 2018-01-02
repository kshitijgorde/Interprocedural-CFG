// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.tabsplitter;

import java.awt.MenuComponent;
import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.beans.Beans;
import java.awt.Container;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Component;
import java.util.Hashtable;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.MenuItem;
import java.util.Vector;
import java.awt.Polygon;
import java.awt.PopupMenu;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class TabPanel extends Panel implements ActionListener, MouseListener
{
    private Color borderColor;
    private Rectangle bothRect;
    private int firstVisible;
    private int lastVisible;
    private transient Graphics g1;
    private transient Image image;
    private int hslop;
    private PopupMenu popupMenu;
    private int lastH;
    private int lastW;
    protected transient Polygon leftArrow;
    protected transient Polygon rightArrow;
    private int selected;
    private Color tabBackground;
    private Color[] tabColors;
    private Vector tabs;
    private String[] tabText;
    private boolean tooManyTabs;
    private int vslop;
    private MenuItem[] tabMenuItems;
    private Font currentFont;
    private Font boldFont;
    private FontMetrics fm;
    private FontMetrics boldfm;
    private int h;
    protected boolean leftEnabled;
    protected boolean rightEnabled;
    private Hashtable explicitTabText;
    protected transient Vector aTabSelectionListener;
    
    protected void setExplicitTabText(final Component component, final String s) {
        this.explicitTabText.put(component, s);
    }
    
    public void remove(final int n) {
        if (n >= this.getComponentCount()) {
            throw new IllegalArgumentException("Not that many tabs");
        }
        this.removeBody(this.getComponent(n), n);
    }
    
    protected void setupTabPolygons() {
        final Dimension size = this.getSize();
        final int[] array = new int[6];
        final int[] array2 = new int[12];
        this.bothRect = new Rectangle(size.width - 26, 2, 24, 6 + this.h);
        array[array[0] = 2] = (array[1] = 11);
        array2[0] = this.h / 2 + 4;
        array2[1] = this.h / 2;
        array2[2] = this.h / 2 + 8;
        this.leftArrow = new Polygon(array, array2, 3);
        array[0] = this.bothRect.x - array[0];
        array[1] = this.bothRect.x - array[1];
        array[2] = this.bothRect.x - array[2];
        this.rightArrow = new Polygon(array, array2, 3);
        array[0] = 0;
        array[1] = 3;
        array[2] = 4;
        array[3] = 9;
        array[4] = 11;
        array[5] = 14;
        array2[0] = 8 + this.h;
        array2[1] = 6 + this.h;
        array2[2] = 5 + this.h;
        array2[3] = 5;
        array2[4] = 4;
        array2[5] = 2;
        for (int i = 0; i < 6; ++i) {
            array2[11 - i] = array2[i];
        }
        final int[] array3 = new int[12];
        final String[] determineTabText = this.determineTabText();
        int n = 8;
        this.getComponents();
        final int componentCount = this.getComponentCount();
        this.lastVisible = componentCount - 1;
        this.tooManyTabs = (this.firstVisible != 0);
        this.tabs = new Vector();
        for (int j = this.firstVisible; j < componentCount; ++j) {
            final String s = determineTabText[j];
            final int n2 = (j == this.selected) ? this.boldfm.stringWidth(s) : this.fm.stringWidth(s);
            for (int k = 0; k < 6; ++k) {
                array3[k] = array[k] + n;
            }
            final int n3 = n + ((array[5] - array[0]) * 2 + this.hslop * 2 + n2);
            for (int l = 0; l < 6; ++l) {
                array3[11 - l] = n3 - array[l];
            }
            n = n3 - array[5];
            this.tabs.addElement(new Polygon(array3, array2, 12));
            if (array3[11] > this.bothRect.x) {
                this.rightEnabled = true;
                this.tooManyTabs = true;
                if (j > this.firstVisible) {
                    this.lastVisible = j - 1;
                }
                else {
                    this.lastVisible = j;
                }
            }
        }
    }
    
    protected void fireTabSelected(final TabSelectionEvent tabSelectionEvent) {
        if (this.aTabSelectionListener == null) {
            return;
        }
        for (int size = this.aTabSelectionListener.size(), i = 0; i < size; ++i) {
            final TabSelectionListener tabSelectionListener = this.aTabSelectionListener.elementAt(i);
            if (tabSelectionListener != null) {
                tabSelectionListener.tabSelected(tabSelectionEvent);
            }
        }
    }
    
    public void addTabSelectionListener(final TabSelectionListener tabSelectionListener) {
        if (this.aTabSelectionListener == null) {
            this.aTabSelectionListener = new Vector();
        }
        this.aTabSelectionListener.addElement(tabSelectionListener);
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.invalidate();
        ((CardLayout)this.getLayout()).next(this);
        ((CardLayout)this.getLayout()).previous(this);
        this.repaint();
    }
    
    public String getSelectedName() {
        return this.determineTabText()[this.selected];
    }
    
    public int getSelectedTabNum() {
        return this.selected;
    }
    
    public Color[] getTabColors() {
        if (this.tabColors == null) {
            return new Color[] { null };
        }
        return this.tabColors;
    }
    
    public void removeTabSelectionListener(final TabSelectionListener tabSelectionListener) {
        if (this.aTabSelectionListener != null) {
            this.aTabSelectionListener.removeElement(tabSelectionListener);
        }
    }
    
    public void remove(final Component component) {
        Component[] components;
        int n;
        for (components = this.getComponents(), n = 0; n < components.length && components[n] != component; ++n) {}
        if (n < components.length) {
            this.removeBody(component, n);
        }
    }
    
    protected void addImpl(final Component component, Object o, final int n) {
        if (o == null) {
            o = component.getName();
        }
        else {
            if (!(o instanceof String)) {
                throw new IllegalArgumentException("Constraint for add must be a String");
            }
            if (o != component.getName()) {
                this.explicitTabText.put(component, o);
                o = component.getName();
            }
        }
        super.addImpl(component, o, n);
        if (Beans.isDesignTime()) {
            this.repaint();
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void show(final String s) {
        final String[] determineTabText = this.determineTabText();
        for (int i = 0; i < determineTabText.length; ++i) {
            if (determineTabText[i].equals(s)) {
                this.showPhysicalTab(i);
                return;
            }
        }
        throw new IllegalArgumentException(String.valueOf("No tab found with text \"") + s + "\"");
    }
    
    public void removeAll() {
        super.removeAll();
        this.explicitTabText = new Hashtable();
        this.invalidate();
        this.validate();
        this.repaint();
        this.selected = 0;
    }
    
    public void setTabText(final String[] tabText) {
        this.tabText = tabText;
    }
    
    public void next() {
        this.showPhysicalTab(this.selected = ++this.selected % this.getComponentCount());
    }
    
    protected PopupMenu getPopupMenu() {
        return this.popupMenu;
    }
    
    public void setFirstVisible(final int firstVisible) {
        if (firstVisible >= this.getComponentCount()) {
            throw new IllegalArgumentException("Not that many tabs");
        }
        this.firstVisible = firstVisible;
    }
    
    public void show(final int n) {
        this.showPhysicalTab(n);
    }
    
    public void shiftLeft() {
        if (this.firstVisible > 0) {
            --this.firstVisible;
        }
        this.repaint();
    }
    
    public void showPhysicalTab(final int selected) {
        if (selected >= this.getComponentCount()) {
            throw new IllegalArgumentException("Not that many components!");
        }
        this.selected = selected;
        this.determineVisible();
        if (this.selected < this.firstVisible || this.selected > this.lastVisible) {
            this.firstVisible = this.selected;
        }
        ((CardLayout)this.getLayout()).show(this, this.getComponent(selected).getName());
        this.fireTabSelected(new TabSelectionEvent(this, this.getVisibleComponent(), this.selected, this.getSelectedName(), this.getVisibleComponentNum()));
        this.invalidate();
        this.validate();
        this.repaint();
    }
    
    public Object getVisibleComponent() {
        return this.getComponent(this.selected);
    }
    
    public void setTabColor(final Color color) {
        this.setTabColors(0, color);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        for (int i = this.tabMenuItems.length - 1; i > -1; --i) {
            if (actionEvent.getSource() == this.tabMenuItems[i]) {
                this.showPhysicalTab(i);
                break;
            }
        }
    }
    
    public void setTabColors(Color[] tabColors) {
        if (tabColors == null) {
            tabColors = new Color[] { null };
        }
        this.tabColors = tabColors;
        this.invalidate();
        if (Beans.isDesignTime()) {
            this.repaint();
        }
    }
    
    public String getTabText(final int n) {
        return this.getTabText()[n];
    }
    
    public Color getTabBackground() {
        if (this.tabBackground == null) {
            this.tabBackground = Color.lightGray;
        }
        return this.tabBackground;
    }
    
    public void setTabColors(final int n, final Color color) {
        if (n > this.tabColors.length - 1) {
            final Color[] tabColors = new Color[n + 1];
            System.arraycopy(this.tabColors, 0, tabColors, 0, this.tabColors.length);
            for (int i = this.tabColors.length; i < n; ++i) {
                tabColors[i] = Color.lightGray;
            }
            this.tabColors = tabColors;
        }
        this.tabColors[n] = color;
        this.invalidate();
        if (Beans.isDesignTime()) {
            this.repaint();
        }
    }
    
    protected void mergeOrShow(final int n) {
        this.showPhysicalTab(n);
    }
    
    public void setSelectedTabNum(final int n) {
        if (n > this.getComponentCount() - 1) {
            throw new IllegalArgumentException("Tab number greater than number of components");
        }
        this.showPhysicalTab(n);
    }
    
    public void previous() {
        final int selected = this.selected - 1;
        this.selected = selected;
        if (selected < 0) {
            this.selected = this.getComponentCount() - 1;
        }
        this.showPhysicalTab(this.selected);
    }
    
    protected void determineVisible() {
        if (this.fm == null) {
            return;
        }
        final int n = this.getSize().width - 26;
        final String[] determineTabText = this.determineTabText();
        this.getComponents();
        final int componentCount = this.getComponentCount();
        this.lastVisible = componentCount - 1;
        this.tooManyTabs = (this.firstVisible != 0);
        int n2 = 8;
        for (int i = this.firstVisible; i < componentCount; ++i) {
            final String s = determineTabText[i];
            n2 += 28 + this.hslop * 2 + ((i == this.selected) ? this.boldfm.stringWidth(s) : this.fm.stringWidth(s));
            if (n2 > n) {
                this.rightEnabled = true;
                this.tooManyTabs = true;
                if (i > this.firstVisible) {
                    this.lastVisible = i - 1;
                }
                else {
                    this.lastVisible = i;
                }
                break;
            }
            n2 -= 14;
        }
    }
    
    public Insets getInsets() {
        return new Insets(this.getFontMetrics(this.getFont()).getHeight() + 12, 6, 6, 6);
    }
    
    public TabPanel() {
        this.borderColor = null;
        this.firstVisible = 0;
        this.lastVisible = 0;
        this.hslop = 4;
        this.popupMenu = null;
        this.lastH = 0;
        this.lastW = 0;
        this.selected = 0;
        this.tabBackground = null;
        this.tabColors = null;
        this.tabText = null;
        this.tooManyTabs = false;
        this.vslop = 4;
        this.leftEnabled = false;
        this.rightEnabled = false;
        this.explicitTabText = new Hashtable();
        this.aTabSelectionListener = null;
        this.addMouseListener(this);
        this.tabs = new Vector();
        this.setBackground(Color.lightGray);
        this.setLayout(new CardLayout());
        this.add(this.popupMenu = new PopupMenu("TabPanel"));
    }
    
    public String[] getTabText() {
        return this.tabText;
    }
    
    public Color getBorderColor() {
        if (this.borderColor == null) {
            this.borderColor = Color.gray;
        }
        return this.borderColor;
    }
    
    public String[] determineTabText() {
        final int componentCount = this.getComponentCount();
        final String[] array = new String[componentCount];
        final Component[] components = this.getComponents();
        for (int i = 0; i < componentCount; ++i) {
            array[i] = (String)this.explicitTabText.get(components[i]);
            if (array[i] == null && components[i] instanceof TabNamedComponent) {
                array[i] = ((TabNamedComponent)components[i]).getTabName();
            }
            if (array[i] == null && this.tabText != null) {
                final int position = this.getPosition(components[i]);
                if (position < this.tabText.length) {
                    array[i] = this.tabText[position];
                }
            }
            if (array[i] == null) {
                array[i] = components[i].getName();
            }
        }
        return array;
    }
    
    protected boolean tabContains(final int n, final int n2, final int n3) {
        return this.tabs.elementAt(n).contains(n2, n3);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this && mouseEvent.isMetaDown()) {
            this.showPopup(mouseEvent);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.selectTab(mouseEvent);
    }
    
    protected void drawTabs(final Graphics graphics) {
        final String[] determineTabText = this.determineTabText();
        this.rightEnabled = false;
        final Dimension size = this.getSize();
        this.setupTabPolygons();
        this.getComponents();
        final int componentCount = this.getComponentCount();
        if (componentCount == 0) {
            return;
        }
        final Color[] tabColors = this.getTabColors();
        for (int i = componentCount - 1; i > this.firstVisible - 2; --i) {
            int selected;
            if (i == this.firstVisible - 1) {
                if (this.selected < this.firstVisible) {
                    break;
                }
                selected = this.selected;
                graphics.setFont(this.boldFont);
            }
            else {
                if (i == this.selected) {
                    continue;
                }
                selected = i;
            }
            Color lightGray;
            if (tabColors == null || tabColors.length == 0 || tabColors[selected % tabColors.length] == null) {
                lightGray = Color.lightGray;
            }
            else {
                lightGray = tabColors[selected % tabColors.length];
            }
            graphics.setColor(lightGray);
            final Polygon polygon = this.tabs.elementAt(selected - this.firstVisible);
            graphics.fillPolygon(polygon);
            final int[] xpoints = polygon.xpoints;
            final int[] ypoints = polygon.ypoints;
            graphics.drawLine(xpoints[0], ypoints[0], xpoints[11], ypoints[11]);
            graphics.setColor(lightGray.darker());
            for (int j = 10; j > 7; --j) {
                graphics.drawLine(xpoints[j], ypoints[j], xpoints[j + 1], ypoints[j + 1]);
            }
            graphics.setColor(lightGray.brighter());
            for (int k = 0; k < 5; ++k) {
                graphics.drawLine(xpoints[k], ypoints[k], xpoints[k + 1], ypoints[k + 1]);
                if (selected == this.selected) {
                    graphics.drawLine(xpoints[11 - k] - 1, ypoints[11 - k], xpoints[11 - k - 1] - 1, ypoints[11 - k - 1]);
                }
            }
            graphics.drawLine(xpoints[5], ypoints[5], xpoints[6], ypoints[6]);
            graphics.drawLine(xpoints[6], ypoints[6], xpoints[7], ypoints[7]);
            graphics.drawLine(xpoints[7], ypoints[7], xpoints[8], ypoints[8]);
            if (selected == this.selected) {
                graphics.drawLine(xpoints[0], ypoints[0], 2, ypoints[0]);
                graphics.drawLine(xpoints[11] - ((selected == this.selected) ? 1 : 0), ypoints[0], this.getSize().width - 4, ypoints[0]);
                graphics.setColor(lightGray);
            }
            graphics.setColor(this.getForeground());
            graphics.drawString(determineTabText[selected], xpoints[5] + this.hslop, ypoints[0] - (this.fm.getDescent() + this.fm.getLeading() / 2));
        }
        graphics.setFont(this.currentFont);
        if (this.tooManyTabs) {
            graphics.setColor(this.getBorderColor());
            graphics.fillRect(this.bothRect.x - 13, 0, size.width - this.bothRect.x + 13, this.h + 8);
            graphics.setColor(this.getTabBackground());
            this.leftEnabled = (this.firstVisible > 0);
            if (this.leftEnabled) {
                graphics.fillPolygon(this.leftArrow);
            }
            if (this.rightEnabled) {
                graphics.fillPolygon(this.rightArrow);
            }
            graphics.setColor(this.getTabBackground().brighter());
            if (this.leftEnabled) {
                graphics.drawPolygon(this.leftArrow);
            }
            if (this.rightEnabled) {
                graphics.drawPolygon(this.rightArrow);
            }
        }
        else {
            graphics.setColor(this.getBorderColor());
            graphics.fillRect(this.bothRect.x - 2, 0, size.width - this.bothRect.x + 2, this.h + 8);
        }
        graphics.setColor(this.getTabBackground());
        graphics.fill3DRect(this.bothRect.x, this.bothRect.y, this.bothRect.width - 1, this.bothRect.height - 1, true);
        graphics.setColor(this.getTabBackground().brighter());
        graphics.drawLine(this.bothRect.x + 3, this.bothRect.y + 3, this.bothRect.x + this.bothRect.width - 6, this.bothRect.y + this.bothRect.width - 6);
        graphics.setColor(this.getForeground());
        final int n = this.bothRect.width / 2;
        final int n2 = this.bothRect.width / 4;
        final int n3 = this.bothRect.width / 8;
        graphics.drawLine(this.bothRect.x + n, this.bothRect.y + n2, this.bothRect.x + n + n2, this.bothRect.y + n2);
        graphics.drawLine(this.bothRect.x + n + n3, this.bothRect.y + n3, this.bothRect.x + n + n3, this.bothRect.y + n2 + n3);
        graphics.drawLine(this.bothRect.x + n3, this.bothRect.y + n + n3, this.bothRect.x + n2 + n3, this.bothRect.y + n + n3);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public Color getTabColors(final int n) {
        if (this.tabColors == null) {
            return null;
        }
        return this.tabColors[n % this.tabColors.length];
    }
    
    protected void selectTab(final MouseEvent mouseEvent) {
        final int componentCount = this.getComponentCount();
        if (componentCount > 0 && this.bothRect.contains(mouseEvent.getX(), mouseEvent.getY())) {
            if (mouseEvent.getX() - this.bothRect.x > mouseEvent.getY() - this.bothRect.y) {
                this.next();
            }
            else {
                this.previous();
            }
        }
        else if (this.rightEnabled && this.tooManyTabs && this.rightArrow.contains(mouseEvent.getX(), mouseEvent.getY())) {
            this.shiftRight();
        }
        else if (this.leftEnabled && this.tooManyTabs && this.leftArrow.contains(mouseEvent.getX(), mouseEvent.getY())) {
            this.shiftLeft();
        }
        else if (componentCount > 0) {
            for (int i = (this.selected >= this.firstVisible) ? (this.firstVisible - 1) : this.firstVisible; i < componentCount; ++i) {
                final int n = (i == this.firstVisible - 1) ? this.selected : i;
                if (this.tabContains(n - this.firstVisible, mouseEvent.getX(), mouseEvent.getY())) {
                    this.mergeOrShow(n);
                    break;
                }
            }
        }
    }
    
    protected int getPosition(final Object o) {
        int componentCount;
        Component[] components;
        int n;
        for (componentCount = this.getComponentCount(), components = this.getComponents(), n = 0; n < componentCount && components[n] != o; ++n) {}
        if (n == componentCount) {
            n = -1;
        }
        return n;
    }
    
    public void setTabBackground(final Color tabBackground) {
        this.tabBackground = tabBackground;
        this.invalidate();
    }
    
    public Color getTabColor() {
        return this.getTabColors(0);
    }
    
    public void setBorderColor(final Color borderColor) {
        this.borderColor = borderColor;
        this.invalidate();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public int[] getVisibleComponentNum() {
        return new int[] { this.selected };
    }
    
    public void paint(final Graphics graphics) {
        if (this.currentFont != this.getFont()) {
            this.currentFont = this.getFont();
            this.boldFont = new Font(this.currentFont.getName(), 1, this.currentFont.getSize());
            this.fm = this.getFontMetrics(this.currentFont);
            this.boldfm = this.getFontMetrics(this.boldFont);
            this.h = this.fm.getHeight();
        }
        final Dimension size = this.getSize();
        if (size.width != this.lastW || size.height != this.lastH || this.image == null) {
            this.image = this.createImage(size.width, size.height);
            if (this.g1 != null) {
                this.g1.dispose();
            }
            this.g1 = this.image.getGraphics();
            this.lastW = size.width;
            this.lastH = size.height;
        }
        this.g1.setColor(this.getBorderColor());
        this.g1.fillRect(0, 0, size.width, size.height);
        this.g1.setColor(this.getBackground());
        this.g1.fill3DRect(2, this.h + 8, size.width - 4, size.height - this.h - 10, true);
        this.g1.setColor(this.getTabBackground());
        this.g1.draw3DRect(4, this.h + 10, size.width - 8, size.height - this.h - 14, false);
        this.drawTabs(this.g1);
        graphics.drawImage(this.image, 0, 0, this);
        super.paint(graphics);
    }
    
    private void removeBody(final Component component, final int n) {
        this.explicitTabText.remove(component);
        super.remove(n);
        if (this.selected >= n && this.selected > 0) {
            --this.selected;
        }
        if (this.selected > -1 && this.getComponentCount() > 0) {
            this.showPhysicalTab(this.selected);
        }
        this.invalidate();
        this.validate();
        this.repaint();
    }
    
    public void show(final Component component) {
        Component[] components;
        int n;
        for (components = this.getComponents(), n = 0; components[n] != component; ++n) {}
        if (n < this.getComponentCount()) {
            this.showPhysicalTab(n);
        }
    }
    
    protected String getExplicitTabText(final Component component) {
        return this.explicitTabText.get(component);
    }
    
    protected void showPopup(final MouseEvent mouseEvent) {
        if (mouseEvent.isMetaDown()) {
            if (this.tabMenuItems != null) {
                for (int i = this.tabMenuItems.length - 1; i > -1; --i) {
                    this.popupMenu.remove(this.tabMenuItems[i]);
                }
            }
            if (this.tabMenuItems == null || this.tabMenuItems.length != this.getComponentCount()) {
                this.tabMenuItems = new MenuItem[this.getComponentCount()];
            }
            final String[] determineTabText = this.determineTabText();
            for (int j = 0; j < determineTabText.length; ++j) {
                this.popupMenu.add(this.tabMenuItems[j] = new MenuItem(determineTabText[j]));
                this.tabMenuItems[j].addActionListener(this);
            }
            this.popupMenu.show(this, mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void setTabText(final int n, final String s) {
        if (this.tabText == null) {
            this.tabText = new String[n + 10];
        }
        else if (n >= this.tabText.length) {
            final String[] tabText = new String[n + 10];
            System.arraycopy(this.tabText, 0, tabText, 0, this.tabText.length);
            this.tabText = tabText;
        }
        this.tabText[n] = s;
    }
    
    public int getFirstVisible() {
        return this.firstVisible;
    }
    
    public void shiftRight() {
        if (this.firstVisible < this.getComponentCount() - 1) {
            ++this.firstVisible;
        }
        this.repaint();
    }
}
