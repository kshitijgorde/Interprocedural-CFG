// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.tabsplitter;

import com.magelang.splitter.SplitterLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Container;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.PopupMenu;
import java.util.Enumeration;
import java.awt.Component;
import java.util.Hashtable;
import java.awt.MenuItem;
import java.awt.Cursor;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;

public class TabSplitter extends TabPanel implements ActionListener, MouseListener, MouseMotionListener
{
    private static Cursor DEF_CURSOR;
    private static Cursor HAND_CURSOR;
    private MenuItem swapItem;
    private Hashtable position;
    private int tabBeingDragged;
    private Hashtable compsInSplitters;
    
    public void remove(final int n) {
        final Enumeration<Component> keys = this.position.keys();
        while (keys.hasMoreElements()) {
            final Component nextElement = keys.nextElement();
            if (!(nextElement instanceof SplitterPanel) && this.getPosition(nextElement) == n) {
                this.removeBody(nextElement);
                return;
            }
        }
        throw new IllegalArgumentException("tab position not found");
    }
    
    public TabSplitter() {
        this.swapItem = null;
        this.position = new Hashtable();
        this.tabBeingDragged = -1;
        this.compsInSplitters = new Hashtable();
        this.addMouseMotionListener(this);
        final PopupMenu popupMenu = this.getPopupMenu();
        popupMenu.setLabel("TabSplitter");
        popupMenu.add(this.swapItem = new MenuItem("Swap Orientation"));
        popupMenu.add(new MenuItem("-"));
        this.swapItem.addActionListener(this);
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.invalidate();
        this.validate();
        ((CardLayout)this.getLayout()).next(this);
        ((CardLayout)this.getLayout()).previous(this);
        this.repaint();
    }
    
    protected void findWhereToAdd(final String s, final Component component) {
        final int componentCount = this.getComponentCount();
        final Component[] components = this.getComponents();
        int n = 0;
        for (int position = this.getPosition(component); n < componentCount && this.getPosition(components[n]) < position; ++n) {}
        if (n == componentCount) {
            this.add(s, component);
        }
        else {
            this.add(component, s, n);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (super.leftEnabled && super.leftArrow.contains(mouseEvent.getX(), mouseEvent.getY())) {
            this.shiftLeft();
        }
        else if (super.rightEnabled && super.rightArrow.contains(mouseEvent.getX(), mouseEvent.getY())) {
            this.shiftRight();
        }
    }
    
    protected int getMaxPos() {
        int n = -1;
        final Enumeration<Object> keys = this.position.keys();
        while (keys.hasMoreElements()) {
            final int intValue = this.position.get(keys.nextElement());
            if (intValue > n) {
                n = intValue;
            }
        }
        return n;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int selectedTabNum = this.getSelectedTabNum();
        final int firstVisible = this.getFirstVisible();
        if (!mouseEvent.isMetaDown()) {
            if (selectedTabNum > firstVisible && this.tabContains(selectedTabNum - firstVisible, mouseEvent.getX(), mouseEvent.getY())) {
                this.tabBeingDragged = selectedTabNum;
            }
            else {
                for (int i = firstVisible; i < this.getComponentCount(); ++i) {
                    if (this.tabContains(i - firstVisible, mouseEvent.getX(), mouseEvent.getY())) {
                        this.tabBeingDragged = i;
                        break;
                    }
                }
            }
            if (this.tabBeingDragged != -1) {
                this.setCursor(TabSplitter.HAND_CURSOR);
            }
        }
        super.mousePressed(mouseEvent);
    }
    
    protected void addImpl(final Component component, final Object o, int position) {
        final int componentCount = this.getComponentCount();
        final Component[] components = this.getComponents();
        if (this.compsInSplitters.get(component) != null) {
            position = this.getPosition(component);
        }
        else if (position == -1) {
            position = this.getMaxPos() + 1;
        }
        int n = 0;
        for (int i = 0; i < componentCount; ++i) {
            if (components[i] == component) {
                return;
            }
            if (this.getPosition(components[i]) < position) {
                n = i + 1;
            }
        }
        super.addImpl(component, o, n);
        if (!(component instanceof SplitterPanel) && this.compsInSplitters.get(component) == null) {
            final Enumeration<Object> keys = (Enumeration<Object>)this.position.keys();
            while (keys.hasMoreElements()) {
                final Object nextElement = keys.nextElement();
                final int position2 = this.getPosition(nextElement);
                if (position2 >= position) {
                    this.position.put(nextElement, new Integer(position2 + 1));
                }
            }
            this.position.put(component, new Integer(position));
        }
        this.tabBeingDragged = -1;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.setCursor(TabSplitter.DEF_CURSOR);
        super.mouseReleased(mouseEvent);
    }
    
    static {
        TabSplitter.DEF_CURSOR = new Cursor(0);
        TabSplitter.HAND_CURSOR = new Cursor(12);
    }
    
    public void show(final String s) {
        try {
            super.show(s);
        }
        catch (IllegalArgumentException ex) {
            final Component[] components = this.getComponents();
            for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
                if (components[i] instanceof SplitterPanel && ((SplitterPanel)components[i]).contains(s)) {
                    this.showPhysicalTab(i);
                    return;
                }
            }
            throw new IllegalArgumentException(String.valueOf("No tab found with text \"") + s + "\"");
        }
    }
    
    public void separateTabs(final String s, final Component component, final String s2, final String s3, final Component component2, final String s4, final SplitterPanel splitterPanel) {
        int n = 0;
        for (Component[] components = this.getComponents(); n < this.getComponentCount() && components[n] != splitterPanel; ++n) {}
        this.remove(splitterPanel);
        if (component2 == null) {
            this.add(splitterPanel, splitterPanel.getTabName(), n);
        }
        else {
            this.position.remove(splitterPanel);
        }
        if (s2 != null) {
            this.setExplicitTabText(component, s2);
        }
        this.findWhereToAdd(s, component);
        this.compsInSplitters.remove(component);
        if (component2 != null) {
            if (s4 != null) {
                this.setExplicitTabText(component2, s4);
            }
            this.findWhereToAdd(s3, component2);
            this.compsInSplitters.remove(component2);
        }
        this.show(component);
        this.invalidate();
        this.validate();
        this.repaint();
    }
    
    public void show(final Component component) {
        try {
            super.show(component);
        }
        catch (IllegalArgumentException ex) {
            final Component[] components = this.getComponents();
            for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
                if (components[i] instanceof SplitterPanel && ((SplitterPanel)components[i]).contains(component)) {
                    this.showPhysicalTab(i);
                    return;
                }
            }
            throw new IllegalArgumentException("Tab not found");
        }
    }
    
    protected int getPosition(final Object o) {
        final Integer n = this.position.get(o);
        if (n == null) {
            return -1;
        }
        return n;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.tabBeingDragged = -1;
        this.setCursor(TabSplitter.DEF_CURSOR);
        super.mouseExited(mouseEvent);
    }
    
    public int[] getVisibleComponentNum() {
        if (this.getComponentCount() == 0) {
            return new int[] { -1 };
        }
        final Component component = this.getComponent(this.getSelectedTabNum());
        if (component instanceof SplitterPanel) {
            return ((SplitterPanel)component).getVisibleComponentNum();
        }
        return new int[] { this.getPosition(component) };
    }
    
    public void removeAll() {
        super.removeAll();
        this.position = new Hashtable();
        this.tabBeingDragged = -1;
    }
    
    public void show(final int n) {
        final Component[] components = this.getComponents();
        for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
            final Integer n2 = this.position.get(components[i]);
            if (n2 != null && n2 == n) {
                this.show(components[n]);
                return;
            }
        }
        throw new IllegalArgumentException("Tab not found");
    }
    
    public Object getVisibleComponent() {
        if (this.getComponentCount() == 0) {
            return null;
        }
        final Component component = this.getComponent(this.getSelectedTabNum());
        if (component instanceof SplitterPanel) {
            return ((SplitterPanel)component).getVisibleComponent();
        }
        return component;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    private void removeBody(final Component component) {
        this.tabBeingDragged = -1;
        boolean b = false;
        boolean b2 = false;
        int n = this.getComponentCount();
        Component[] array = this.getComponents();
        for (int n2 = 0; !b && n2 < n; b = (array[n2] == component), ++n2) {}
        for (int n3 = 0; !b && n3 < n; ++n3) {
            if (array[n3] instanceof SplitterPanel && ((SplitterPanel)array[n3]).contains(component)) {
                ((SplitterPanel)array[n3]).separate(component);
                array = this.getComponents();
                n = this.getComponentCount();
                this.compsInSplitters.remove(component);
                b2 = (b = true);
            }
        }
        if (b) {
            if (!(component instanceof SplitterPanel) && this.compsInSplitters.get(component) == null) {
                final int position = this.getPosition(component);
                this.position.remove(component);
                for (int i = 0; i < n; ++i) {
                    if (array[i] instanceof SplitterPanel) {
                        ((SplitterPanel)array[i]).decrPositions(position);
                    }
                }
                final Enumeration<Object> keys = this.position.keys();
                while (keys.hasMoreElements()) {
                    final Object nextElement = keys.nextElement();
                    final int position2 = this.getPosition(nextElement);
                    if (position2 > position) {
                        this.position.put(nextElement, new Integer(position2 - 1));
                    }
                }
            }
            for (int j = 0; j < n; ++j) {
                if (array[j] == component) {
                    super.remove(j);
                }
            }
        }
        if (b2) {
            this.showPhysicalTab(this.getSelectedTabNum());
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.swapItem) {
            this.swapOrientation();
        }
        else {
            super.actionPerformed(actionEvent);
        }
    }
    
    protected void mergeTabs(final int n, final int n2) {
        this.tabBeingDragged = -1;
        final String[] determineTabText = this.determineTabText();
        final Component component = this.getComponent(n);
        final Component component2 = this.getComponent(n2);
        if (!(component instanceof SplitterPanel)) {
            this.compsInSplitters.put(component, component);
        }
        if (!(component2 instanceof SplitterPanel)) {
            this.compsInSplitters.put(component2, component2);
        }
        final String explicitTabText = this.getExplicitTabText(component2);
        final String explicitTabText2 = this.getExplicitTabText(component);
        final int position = this.getPosition(component);
        final int position2 = this.getPosition(component2);
        this.remove(component2);
        this.remove(component);
        if (component instanceof SplitterPanel) {
            if (component2 instanceof SplitterPanel) {
                ((SplitterPanel)component).add((SplitterPanel)component2);
            }
            else {
                ((SplitterPanel)component).add(determineTabText[n2], component2, position2, explicitTabText);
            }
            this.position.put(component, new Integer(position));
            this.add(component, component.getName(), position);
        }
        else if (component2 instanceof SplitterPanel) {
            ((SplitterPanel)component2).add(determineTabText[n], component, position, explicitTabText2);
            this.position.put(component2, new Integer(position));
            this.add(component2, component2.getName(), position);
        }
        else {
            final SplitterPanel splitterPanel = new SplitterPanel();
            splitterPanel.add(determineTabText[n], component, position, explicitTabText2);
            splitterPanel.add(determineTabText[n2], component2, position2, explicitTabText);
            this.position.put(splitterPanel, new Integer(position));
            this.add(splitterPanel, splitterPanel.getName(), position);
        }
        this.showPhysicalTab((n > n2) ? (n - 1) : n);
        component2.setVisible(true);
        component.setVisible(true);
    }
    
    protected void mergeOrShow(final int n) {
        if (this.tabBeingDragged == n || this.tabBeingDragged == -1) {
            this.showPhysicalTab(n);
        }
        else {
            this.mergeTabs(n, this.tabBeingDragged);
        }
    }
    
    public void swapOrientation() {
        final Component[] components = this.getComponents();
        for (int i = this.getComponentCount() - 1; i > -1; --i) {
            if (components[i] instanceof Container && ((Container)components[i]).getLayout() instanceof SplitterLayout) {
                final Container container = (Container)components[i];
                ((SplitterLayout)container.getLayout()).swapOrientation(container);
            }
        }
    }
    
    public void remove(final Component component) {
        if (this.position.get(component) == null) {
            throw new IllegalArgumentException("tab not found");
        }
        this.removeBody(component);
    }
}
