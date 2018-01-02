// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.event.FocusListener;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.applet.Applet;
import jclass.util.JCVector;

public class JCList extends JCScrolledWindow implements JCListInterface, JCItemSelectable
{
    JCListComponent list;
    private static final String base = "list";
    private static int nameCounter;
    
    public JCList() {
        this(null, null, null);
    }
    
    public JCList(final JCVector jcVector) {
        this(jcVector, null, null);
    }
    
    public JCList(final String[] array, final String s) {
        this(new JCVector(array), null, s);
    }
    
    public JCList(final JCVector jcVector, final Applet applet, final String s) {
        super(applet, s);
        if (s == null) {
            this.setName("list" + JCList.nameCounter++);
        }
        this.setList(new JCListComponent(jcVector, applet, this.getName()));
        if (this.getClass().getName().equals("jclass.bwt.JCList")) {
            this.getParameters(applet);
        }
    }
    
    public JCList(final int visibleRows, final boolean allowMultipleSelections) {
        this(null, null, null);
        this.list.setVisibleRows(visibleRows);
        this.list.setAllowMultipleSelections(allowMultipleSelections);
    }
    
    public JCListComponent getList() {
        return this.list;
    }
    
    public void setList(final JCListComponent jcListComponent) {
        jcListComponent.setApplet(super.applet);
        this.list = jcListComponent;
        super.viewport = jcListComponent;
        jcListComponent.scrolled_window = this;
        if (this.getPeer() != null) {
            this.validate();
        }
        jcListComponent.enable(this.isEnabled());
        jcListComponent.addKeyListener(this);
    }
    
    protected int preferredWidth() {
        if (this.list == null) {
            return -1;
        }
        return this.list.preferredSize().width + 16 + super.sb_offset;
    }
    
    protected int getViewportHeight() {
        final int n = this.list.countItems() - 1;
        if (n < 0) {
            return super.getViewportHeight();
        }
        return this.list.getRowPosition(n) + this.list.getRowHeight(n) + 2 * (this.list.getBorderThickness() + this.list.getHighlightThickness()) + this.list.getInsets().top + this.list.getInsets().bottom;
    }
    
    protected void scrollVertical(final JCScrollableInterface jcScrollableInterface, final JCAdjustmentEvent jcAdjustmentEvent, final int n) {
        super.scrollVertical(jcScrollableInterface, jcAdjustmentEvent, n);
        switch (super.keystroke) {
            case 1002: {
                this.list.setFocus(this.list.bottom_row);
            }
            case 1003: {
                this.list.setFocus(this.list.top_row);
            }
            case 1000: {
                this.list.setFocus(0);
            }
            case 1001: {
                this.list.setFocus(this.list.items.size() - 1);
                break;
            }
        }
    }
    
    public void addItemListener(final JCItemListener jcItemListener) {
        this.list.addItemListener(jcItemListener);
    }
    
    public void removeItemListener(final JCItemListener jcItemListener) {
        this.list.removeItemListener(jcItemListener);
    }
    
    public void addActionListener(final JCActionListener jcActionListener) {
        this.list.addActionListener(jcActionListener);
    }
    
    public void removeActionListener(final JCActionListener jcActionListener) {
        this.list.removeActionListener(jcActionListener);
    }
    
    public void remove(final String s) {
        this.list.remove(s);
    }
    
    public synchronized JCVector getItems() {
        return this.list.getItems();
    }
    
    public void setItems(final JCVector items) {
        this.list.setItems(items);
    }
    
    public void setItems(final String[] items) {
        this.list.setItems(items);
    }
    
    public synchronized String[] getItemsStrings() {
        return this.list.getItemsStrings();
    }
    
    public void setItemsStrings(final String[] items) {
        this.list.setItems(items);
    }
    
    public int countItems() {
        return this.list.countItems();
    }
    
    public Object getItem(final int n) {
        return this.list.getItem(n);
    }
    
    public void addItem(final Object o) {
        this.list.addItem(o);
    }
    
    public void addItem(final Object o, final int n) {
        this.list.addItem(o, n);
    }
    
    public void replaceItem(final Object o, final int n) {
        this.list.replaceItem(o, n);
    }
    
    public boolean getBatched() {
        return this.list.getBatched();
    }
    
    public void setBatched(final boolean batched) {
        this.list.setBatched(batched);
    }
    
    public void removeAll() {
        this.list.clear();
    }
    
    public void clear() {
        this.list.clear();
    }
    
    public void deleteItem(final int n) {
        this.list.deleteItem(n);
    }
    
    public void deleteItems(final int n, final int n2) {
        this.list.deleteItems(n, n2);
    }
    
    public int find(final Object o) {
        return this.list.find(o);
    }
    
    protected boolean getWrapAroundSearch() {
        return this.list.getWrapAroundSearch();
    }
    
    protected void setWrapAroundSearch(final boolean wrapAroundSearch) {
        this.list.setWrapAroundSearch(wrapAroundSearch);
    }
    
    public int getSelectedIndex() {
        return this.list.getSelectedIndex();
    }
    
    public int[] getSelectedIndexes() {
        return this.list.getSelectedIndexes();
    }
    
    public Object getSelectedItem() {
        return this.list.getSelectedItem();
    }
    
    public Object[] getSelectedObjects() {
        return this.list.getSelectedObjects();
    }
    
    public boolean getAutoSelect() {
        return this.list.getAutoSelect();
    }
    
    public void setAutoSelect(final boolean autoSelect) {
        this.list.setAutoSelect(autoSelect);
    }
    
    public synchronized void select(final int n) {
        this.list.select(n);
    }
    
    public synchronized void select(final Object o) {
        this.list.select(o);
    }
    
    public synchronized void select(final Object o, final boolean b) {
        this.list.select(o, b);
    }
    
    public synchronized void select(final int n, final boolean b) {
        this.list.select(n, b);
    }
    
    public synchronized void deselect(final int n) {
        this.list.deselect(n);
    }
    
    public synchronized void deselectAll() {
        this.list.deselectAll();
    }
    
    public boolean isSelected(final int n) {
        return this.list.isSelected(n);
    }
    
    public Color getSelectedBackground() {
        return this.list.getSelectedBackground();
    }
    
    public void setSelectedBackground(final Color selectedBackground) {
        this.list.setSelectedBackground(selectedBackground);
    }
    
    public Color getSelectedForeground() {
        return this.list.getSelectedForeground();
    }
    
    public void setSelectedForeground(final Color selectedForeground) {
        this.list.setSelectedForeground(selectedForeground);
    }
    
    public int getRows() {
        return this.list.getRows();
    }
    
    public void setVisibleRows(final int visibleRows) {
        this.list.setVisibleRows(visibleRows);
    }
    
    public int getTopRow() {
        return this.list.getTopRow();
    }
    
    public synchronized void setTopRow(final int topRow) {
        this.list.setTopRow(topRow);
    }
    
    public boolean allowsMultipleSelections() {
        return this.list.allowsMultipleSelections();
    }
    
    public boolean getAllowMultipleSelections() {
        return this.list.allowsMultipleSelections();
    }
    
    public void setAllowMultipleSelections(final boolean allowMultipleSelections) {
        this.list.setAllowMultipleSelections(allowMultipleSelections);
    }
    
    public int getVisibleIndex() {
        return this.list.getVisibleIndex();
    }
    
    public void makeVisible(final int n) {
        this.list.makeVisible(n);
    }
    
    public int getRowHeight() {
        return this.list.getRowHeight();
    }
    
    public void setRowHeight(final int rowHeight) {
        this.list.setRowHeight(rowHeight);
    }
    
    public int getSpacing() {
        return this.list.getSpacing();
    }
    
    public void setSpacing(final int spacing) {
        this.list.setSpacing(spacing);
    }
    
    public void paintRow(final int n) {
        this.list.paintRow(n);
    }
    
    public Object[] getUserDataList() {
        return this.list.userdata_list;
    }
    
    public void setUserDataList(final Object[] userDataList) {
        this.list.setUserDataList(userDataList);
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.list.calcSize(true);
    }
    
    public void addKeyListener(final KeyListener keyListener) {
        this.list.addKeyListener(keyListener);
    }
    
    public void removeKeyListener(final KeyListener keyListener) {
        this.list.removeKeyListener(keyListener);
    }
    
    public void addFocusListener(final FocusListener focusListener) {
        this.list.addFocusListener(focusListener);
    }
    
    public void removeFocusListener(final FocusListener focusListener) {
        this.list.removeFocusListener(focusListener);
    }
}
