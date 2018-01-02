// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import jclass.util.JCString;
import jclass.base.TransientComponent;
import java.awt.Graphics;
import java.awt.SystemColor;
import jclass.base.BaseComponent;
import java.awt.Component;
import java.awt.Event;
import java.util.Vector;
import java.awt.Font;
import java.applet.Applet;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Color;
import jclass.util.JCVector;

public class JCListComponent extends JCComponent implements JCListInterface, JCScrollableInterface, JCItemSelectable
{
    JCVector items;
    int visible_rows;
    boolean[] selected;
    boolean[] last_selected;
    Color selected_bg;
    Color selected_fg;
    boolean multiple_select;
    int spacing;
    boolean batched;
    boolean needs_recalc;
    boolean needs_repaint;
    int pref_height_internal;
    int pref_width_internal;
    int row_height_ext;
    int row_height;
    int[] row_heights;
    int[] row_pos;
    int horiz_origin;
    int vert_origin;
    int top_row;
    int bottom_row;
    int last_visible;
    int last_row;
    boolean auto_select;
    Object[] userdata_list;
    Rectangle draw_rect;
    Insets vis;
    boolean did_selection;
    int last_selected_item;
    int start_item;
    int old_start_item;
    int end_item;
    int old_end_item;
    boolean appending;
    int focus_row;
    boolean kbd_select;
    int selection_type;
    boolean wrap_around_search;
    protected JCVector actionListeners;
    protected JCVector itemListeners;
    private static final String base = "list";
    private static int nameCounter;
    static final int DEFAULT_VISIBLE_ROWS = 4;
    protected JCScrolledWindow scrolled_window;
    Rectangle clip_rect;
    
    public JCListComponent() {
        this(null, null, null);
    }
    
    public JCListComponent(final JCVector jcVector) {
        this(jcVector, null, null);
    }
    
    public JCListComponent(final JCVector items, final Applet applet, final String s) {
        super(applet, s);
        this.items = new JCVector();
        this.selected = new boolean[0];
        this.last_selected = new boolean[0];
        this.multiple_select = false;
        this.batched = false;
        this.needs_recalc = false;
        this.needs_repaint = false;
        this.row_height_ext = -997;
        this.vert_origin = -999;
        this.bottom_row = -999;
        this.last_row = -999;
        this.auto_select = false;
        this.draw_rect = new Rectangle();
        this.vis = new Insets(0, 0, 0, 0);
        this.did_selection = false;
        this.appending = false;
        this.focus_row = -1;
        this.kbd_select = false;
        this.wrap_around_search = true;
        this.actionListeners = new JCVector(0);
        this.itemListeners = new JCVector(0);
        this.clip_rect = new Rectangle();
        if (s == null) {
            this.setName("list" + JCListComponent.nameCounter++);
        }
        super.insets = new Insets(5, 5, 5, 5);
        if (this.getClass().getName().equals("jclass.bwt.JCListComponent")) {
            this.getParameters(applet);
        }
        if (items != null) {
            this.setItems(items);
        }
    }
    
    public JCListComponent(final int visible_rows, final boolean multiple_select) {
        this(null, null, null);
        this.visible_rows = visible_rows;
        this.multiple_select = multiple_select;
    }
    
    protected void getParameters() {
        super.getParameters();
        ListConverter.getParams(this);
    }
    
    public void addItemListener(final JCItemListener jcItemListener) {
        this.itemListeners.add(jcItemListener);
    }
    
    public void removeItemListener(final JCItemListener jcItemListener) {
        this.itemListeners.removeElement(jcItemListener);
    }
    
    public void addActionListener(final JCActionListener jcActionListener) {
        this.actionListeners.add(jcActionListener);
    }
    
    public void removeActionListener(final JCActionListener jcActionListener) {
        this.actionListeners.removeElement(jcActionListener);
    }
    
    public void setFont(final Font font) {
        synchronized (this) {
            if (this.getFont() != null && this.getFont().equals(font)) {
                // monitorexit(this)
                return;
            }
            super.setFont(font);
        }
        this.calcSize(true);
    }
    
    public Object[] getUserDataList() {
        return this.userdata_list;
    }
    
    public void setUserDataList(final Object[] userdata_list) {
        this.userdata_list = userdata_list;
        this.updateUserdataList();
    }
    
    public JCVector getItems() {
        return this.items;
    }
    
    public Object getItem(final int n) {
        return this.items.elementAt(n);
    }
    
    public void setItems(final String[] array) {
        this.setItems(new JCVector(array));
    }
    
    public synchronized String[] getItemsStrings() {
        final String[] array = new String[this.items.size()];
        for (int i = 0; i < array.length; ++i) {
            final Object element = this.items.elementAt(i);
            array[i] = ((element != null) ? element.toString() : "");
        }
        return array;
    }
    
    public void setItems(final JCVector jcVector) {
        synchronized (this) {
            this.items = ((jcVector != null) ? jcVector : new JCVector());
            for (int i = 0; i < this.selected.length; ++i) {
                this.selected[i] = (this.last_selected[i] = false);
            }
            this.focus_row = -1;
        }
        this.calcSize(true);
        this.repaint();
    }
    
    public boolean getBatched() {
        return this.batched;
    }
    
    public void setBatched(final boolean batched) {
        if (this.batched == batched) {
            return;
        }
        this.batched = batched;
        if (this.batched) {
            return;
        }
        if (this.needs_recalc) {
            this.calcSize(true);
        }
        if (this.needs_recalc || this.needs_repaint) {
            this.repaint();
        }
        final boolean b = false;
        this.needs_repaint = b;
        this.needs_recalc = b;
    }
    
    public int getSpacing() {
        return this.spacing;
    }
    
    public void setSpacing(final int spacing) {
        this.spacing = spacing;
        this.calcSize(true);
        this.repaint();
    }
    
    protected boolean getWrapAroundSearch() {
        return this.wrap_around_search;
    }
    
    protected void setWrapAroundSearch(final boolean wrap_around_search) {
        this.wrap_around_search = wrap_around_search;
    }
    
    public int countItems() {
        return this.items.size();
    }
    
    public void addItem(final Object o, int size) {
        synchronized (this) {
            if (size < 0 || size >= this.items.size()) {
                size = this.items.size();
                this.items.addElement(o);
            }
            else {
                this.items.insertElementAt(o, size);
            }
        }
        this.calcSize(size, true);
        this.repaint();
    }
    
    public void addItem(final Object o) {
        this.addItem(o, Integer.MAX_VALUE);
    }
    
    public void replaceItem(final Object o, int size) {
        synchronized (this) {
            if (size >= this.items.size()) {
                // monitorexit(this)
                return;
            }
            this.items.removeElementAt(size);
            if (size < 0 || size >= this.items.size()) {
                size = this.items.size();
                this.items.addElement(o);
            }
            else {
                this.items.insertElementAt(o, size);
            }
        }
        this.calcSize(size, true);
        this.repaint();
    }
    
    public void clear() {
        this.setItems((JCVector)null);
    }
    
    private void delete(final int n, int n2) {
        int i;
        for (n2 = (i = Math.min(this.items.size() - 1, n2)); i >= n; --i) {
            this.items.removeElementAt(i);
        }
        if (this.selected.length > n) {
            System.arraycopy(this.selected, n2 + 1, this.selected, n, this.selected.length - n2 - 1);
            System.arraycopy(this.last_selected, n2 + 1, this.last_selected, n, this.selected.length - n2 - 1);
        }
        if (this.userdata_list != null && this.userdata_list.length > n) {
            System.arraycopy(this.userdata_list, n2 + 1, this.userdata_list, n, this.userdata_list.length - n2 - 1);
        }
    }
    
    public void deleteItems(final int n, final int n2) {
        synchronized (this) {
            this.delete(n, n2);
        }
        this.calcSize(true);
        this.repaint();
    }
    
    public void deleteItem(final int n) {
        this.deleteItems(n, n);
    }
    
    public void remove(final String s) {
        synchronized (this) {
            final int index = this.items.indexOf(s);
            if (index < 0) {
                throw new IllegalArgumentException("item " + s + " not found in JCList");
            }
            this.delete(index, index);
        }
        this.calcSize(true);
        this.repaint();
    }
    
    public synchronized int getSelectedIndex() {
        for (int n = 0, size = this.items.size(); n < this.selected.length && n < size; ++n) {
            if (this.selected[n]) {
                return n;
            }
        }
        return -999;
    }
    
    public int getTopRow() {
        return this.top_row;
    }
    
    public void setTopRow(final int top_row) {
        this.top_row = top_row;
        this.vert_origin = -999;
        this.repaint();
    }
    
    public void makeVisible(final int last_visible) {
        if (last_visible < 0 || last_visible >= this.items.size()) {
            return;
        }
        int rowPosition;
        if ((this.last_visible = last_visible) <= this.top_row) {
            rowPosition = this.getRowPosition(last_visible);
        }
        else if (last_visible == this.items.size() - 1) {
            rowPosition = Integer.MAX_VALUE;
        }
        else {
            if (last_visible < this.bottom_row) {
                return;
            }
            rowPosition = this.vert_origin + this.getRowPosition(last_visible + 1) - this.getRowPosition(this.bottom_row);
        }
        if (this.scrolled_window != null) {
            this.scrolled_window.scrollVertical(rowPosition);
        }
    }
    
    public int getVisibleIndex() {
        return this.last_visible;
    }
    
    public synchronized Object getSelectedItem() {
        final int selectedIndex = this.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < this.items.size()) {
            return this.items.elementAt(selectedIndex);
        }
        return null;
    }
    
    public Object[] getSelectedObjects() {
        final int[] selectedIndexes = this.getSelectedIndexes();
        if (selectedIndexes == null) {
            return null;
        }
        final Object[] array = new Object[selectedIndexes.length];
        for (int i = 0; i < selectedIndexes.length; ++i) {
            array[i] = this.items.elementAt(selectedIndexes[i]);
        }
        return array;
    }
    
    public synchronized int[] getSelectedIndexes() {
        int n = 0;
        for (int i = 0; i < this.selected.length; ++i) {
            if (this.selected[i]) {
                ++n;
            }
        }
        if (n == 0) {
            return null;
        }
        final int[] array = new int[n];
        int j = 0;
        int n2 = 0;
        while (j < this.selected.length) {
            if (this.selected[j]) {
                array[n2++] = j;
            }
            ++j;
        }
        return array;
    }
    
    public void deselect(final int n) {
        Vector<JCItemListener> vector = null;
        JCItemEvent jcItemEvent = null;
        boolean b = false;
        boolean b2 = false;
        synchronized (this) {
            if (n >= this.selected.length || !this.selected[n]) {
                // monitorexit(this)
                return;
            }
            if (this.multiple_select) {
                b2 = true;
            }
            else {
                this.selected[n] = (this.last_selected[n] = false);
                b = true;
            }
            JCListInterface jcListInterface = this;
            if (this.scrolled_window != null) {
                jcListInterface = (JCListInterface)this.scrolled_window;
            }
            jcItemEvent = new JCItemEvent(jcListInterface, 702, this.items.elementAt(n), 2);
            vector = (Vector<JCItemListener>)this.itemListeners.clone();
        }
        if (b2) {
            this.deselectAll();
        }
        if (b) {
            this.paintRow(n);
        }
        if (vector != null && vector.size() > 0) {
            for (int i = 0; i < vector.size(); ++i) {
                vector.elementAt(i).itemStateChanged(jcItemEvent);
            }
        }
    }
    
    public void deselectAll() {
        int[] array = new int[0];
        int n = 0;
        synchronized (this) {
            array = new int[this.selected.length];
            for (int i = 0; i < this.selected.length; ++i) {
                if (this.selected[i]) {
                    this.selected[i] = (this.last_selected[i] = false);
                    array[n] = i;
                    ++n;
                }
            }
        }
        for (int j = 0; j < n; ++j) {
            this.paintRow(array[j]);
        }
    }
    
    public void select(final int n) {
        this.select(n, true);
    }
    
    public void select(final Object o) {
        this.select(o, true);
    }
    
    public void select(final Object o, final boolean b) {
        final int index = this.items.indexOf(o);
        if (index >= 0) {
            ListSelection.select(this, index, b, null);
        }
    }
    
    public void select(final int n, final boolean b) {
        ListSelection.select(this, n, b, null);
    }
    
    public int find(final Object o) {
        final int index = this.items.indexOf(o);
        if (index >= 0) {
            return index;
        }
        return -999;
    }
    
    static boolean inside(final Insets insets, final int n) {
        return insets != null && Math.min(insets.top, insets.bottom) <= n && n <= Math.max(insets.top, insets.bottom);
    }
    
    public synchronized boolean isSelected(final int n) {
        return n < this.selected.length && n >= 0 && n < this.items.size() && this.selected[n];
    }
    
    public boolean allowsMultipleSelections() {
        return this.multiple_select;
    }
    
    public boolean getAllowMultipleSelections() {
        return this.multiple_select;
    }
    
    public void setAllowMultipleSelections(final boolean multiple_select) {
        this.multiple_select = multiple_select;
    }
    
    public Rectangle getBounds(final int n, final boolean b) {
        if (n < 0 || n >= this.items.size()) {
            return null;
        }
        this.getDrawingArea(this.draw_rect);
        this.draw_rect.reshape(this.draw_rect.x - (b ? this.horiz_origin : 0), this.draw_rect.y + this.getRowPosition(n) - (b ? this.vert_origin : 0), Math.max(this.draw_rect.width, this.preferredWidth()), this.getRowHeight(n));
        return this.draw_rect;
    }
    
    public boolean getAutoSelect() {
        return this.auto_select;
    }
    
    public void setAutoSelect(final boolean auto_select) {
        this.auto_select = auto_select;
        this.repaint();
    }
    
    protected int getRowHeight(final int n) {
        if (this.row_height_ext != -998) {
            return this.row_height;
        }
        if (n < this.row_heights.length) {
            return this.row_heights[n];
        }
        return 0;
    }
    
    protected int calcRowWidth(final int n) {
        return BWTUtil.getWidth(this.items.elementAt(n), this);
    }
    
    protected int getRowPosition(int max) {
        max = Math.max(0, Math.min(max, this.items.size() - 1));
        if (this.row_height_ext != -998) {
            return max * this.row_height;
        }
        if (max < this.row_pos.length) {
            return this.row_pos[max];
        }
        return 0;
    }
    
    public int getRowHeight() {
        return this.row_height_ext;
    }
    
    public void setRowHeight(final int row_height_ext) {
        if (row_height_ext < 0 && row_height_ext != -997 && row_height_ext != -998) {
            throw new IllegalArgumentException("invalid row height: " + row_height_ext);
        }
        this.row_height_ext = row_height_ext;
        this.calcSize(true);
        this.repaint();
    }
    
    private boolean inside(final int n, int n2, int n3) {
        this.getDrawingArea(this.draw_rect);
        n3 += this.vert_origin - this.draw_rect.y;
        n2 += this.horiz_origin;
        return n2 >= this.draw_rect.x && n2 - this.draw_rect.x < this.draw_rect.width && n3 >= this.getRowPosition(n) && n3 - this.getRowPosition(n) < this.getRowHeight(n);
    }
    
    public int eventToRow(final Event event, final boolean b) {
        if (this.getPeer() == null || this.items.size() == 0) {
            return -999;
        }
        this.getVisibleRange(this.vis);
        if (this.getBounds(this.vis.top, true).inside(event.x, event.y)) {
            return this.vis.top;
        }
        if (this.getBounds(this.vis.bottom, true).inside(event.x, event.y)) {
            return this.vis.bottom;
        }
        this.getDrawingArea(this.draw_rect);
        if (b && !this.draw_rect.inside(event.x, event.y + this.space())) {
            return -999;
        }
        final int n = event.y + this.vert_origin - this.draw_rect.y;
        if (event.y > this.draw_rect.y + this.draw_rect.height) {
            for (int i = this.vis.bottom; i < this.items.size(); ++i) {
                if (n < this.getRowPosition(i)) {
                    return i - 1;
                }
            }
            return -999;
        }
        if (event.y < this.draw_rect.y) {
            for (int j = this.vis.top; j >= 0; --j) {
                if (n > this.getRowPosition(j)) {
                    return j;
                }
            }
            return -999;
        }
        for (int k = this.vis.top + 1; k <= Math.min(this.items.size(), this.vis.bottom + 1); ++k) {
            if (n < this.getRowPosition(k)) {
                return k - 1;
            }
        }
        if (this.vis.bottom == this.items.size() - 1 && n < this.getRowPosition(this.vis.bottom) + this.getRowHeight(this.vis.bottom)) {
            return this.vis.bottom;
        }
        return -999;
    }
    
    public int getRows() {
        return this.visible_rows;
    }
    
    public int getVisibleRows() {
        return this.visible_rows;
    }
    
    public void setVisibleRows(final int visible_rows) {
        this.visible_rows = visible_rows;
        this.calcSize(true);
    }
    
    public void getVisibleRange(final Insets insets) {
        if (this.bottom_row < 0) {
            this.calcBottomRow();
        }
        insets.top = this.top_row;
        insets.bottom = this.bottom_row;
    }
    
    public Color getSelectedBackground() {
        if (this.selected_bg == null && BaseComponent.use_system_colors) {
            return SystemColor.textHighlight;
        }
        if (this.selected_bg != null) {
            return this.selected_bg;
        }
        return this.getForeground();
    }
    
    public void setSelectedBackground(final Color selected_bg) {
        this.selected_bg = selected_bg;
        if (this.getSelectedIndex() >= 0) {
            this.repaint();
        }
    }
    
    public Color getSelectedForeground() {
        if (this.selected_fg == null && BaseComponent.use_system_colors) {
            return SystemColor.textHighlightText;
        }
        if (this.selected_fg != null) {
            return this.selected_fg;
        }
        return this.getBackground();
    }
    
    public void setSelectedForeground(final Color selected_fg) {
        this.selected_fg = selected_fg;
        if (this.getSelectedIndex() >= 0) {
            this.repaint();
        }
    }
    
    public int findPrevItem(final char c, int n) {
        final char upperCase = Character.toUpperCase(c);
        int i;
        for (n = (i = Math.min(n, this.items.size())); i >= 0; --i) {
            if (BWTUtil.startsWith(this.items.elementAt(i), upperCase)) {
                return i;
            }
        }
        return -999;
    }
    
    public int findNextItem(final char c, final int n) {
        final char upperCase = Character.toUpperCase(c);
        for (int i = n; i < this.items.size(); ++i) {
            if (BWTUtil.startsWith(this.items.elementAt(i), upperCase)) {
                return i;
            }
        }
        return -999;
    }
    
    public int findItem(final char c) {
        return this.findNextItem(c, 0);
    }
    
    protected void calcSize(final boolean b) {
        if (this.getPeer() == null) {
            return;
        }
        synchronized (this) {
            if (this.batched) {
                this.needs_recalc = true;
                // monitorexit(this)
                return;
            }
            this.updateSelectedList();
            this.updateUserdataList();
            int n = 0;
            this.row_height = 0;
            if (this.row_height_ext != -998) {
                final int[] array = null;
                this.row_pos = array;
                this.row_heights = array;
                if (this.row_height_ext == -997) {
                    this.row_height = this.getToolkit().getFontMetrics(this.getFont()).getHeight() + this.space();
                }
                else {
                    this.row_height = this.row_height_ext;
                }
                n = this.items.size() * this.row_height;
            }
            else {
                if (this.row_heights == null || this.items.size() > this.row_heights.length) {
                    this.row_heights = new int[this.items.size()];
                    this.row_pos = new int[this.items.size()];
                }
                for (int i = 0; i < this.items.size(); ++i) {
                    this.row_heights[i] = BWTUtil.getHeight(this.items.elementAt(i), this) + this.space();
                    if (i > 0) {
                        this.row_pos[i] = this.row_pos[i - 1] + this.row_heights[i - 1];
                    }
                    n += this.row_heights[i];
                }
            }
            if (this.visible_rows > 0) {
                if (this.visible_rows < this.items.size()) {
                    this.pref_height_internal = this.prevItemPos(this.visible_rows);
                }
                else {
                    int n2 = (n > 0) ? n : this.row_height;
                    if (n2 == 0) {
                        n2 = this.getToolkit().getFontMetrics(this.getFont()).getHeight() + this.space();
                    }
                    this.pref_height_internal = n2 + Math.max(0, this.visible_rows - this.items.size() - 1) * (n2 / Math.max(1, this.items.size()));
                }
            }
            else {
                int row_height = this.row_height;
                if (row_height == 0) {
                    row_height = this.getToolkit().getFontMetrics(this.getFont()).getHeight() + this.space();
                }
                this.pref_height_internal = row_height * 4;
                if (this.visible_rows == -998) {
                    this.pref_height_internal = Math.max(n, this.pref_height_internal);
                }
            }
            this.pref_width_internal = 0;
        }
        if (b) {
            this.updateParent();
        }
    }
    
    synchronized int[] ensureCapacity(int[] array) {
        if (array == null || this.items.size() >= array.length) {
            if (array == null) {
                array = new int[this.items.size()];
            }
            else {
                final int length = array.length;
                final int[] array2 = array;
                array = new int[this.items.size()];
                System.arraycopy(array2, 0, array, 0, length);
            }
        }
        return array;
    }
    
    protected void calcSize(final int n, final boolean b) {
        if (this.getPeer() == null) {
            return;
        }
        synchronized (this) {
            if (this.batched) {
                this.needs_recalc = true;
                // monitorexit(this)
                return;
            }
            final int length = this.selected.length;
            this.updateSelectedList();
            if (b) {
                if (length < this.selected.length && n < length) {
                    System.arraycopy(this.selected, n, this.selected, n + 1, length - n);
                    System.arraycopy(this.last_selected, n, this.last_selected, n + 1, length - n);
                }
                this.selected[n] = (this.last_selected[n] = false);
            }
            final int n2 = (this.userdata_list != null) ? this.userdata_list.length : 0;
            this.updateUserdataList();
            if (b && this.userdata_list != null) {
                if (n2 < this.userdata_list.length && n < n2) {
                    System.arraycopy(this.userdata_list, n, this.userdata_list, n + 1, n2 - n);
                }
                if (n >= 0 && n < this.userdata_list.length) {
                    this.userdata_list[n] = null;
                }
            }
        }
        if (this.row_height_ext != -998) {
            this.calcSize(true);
            return;
        }
        synchronized (this) {
            if (b) {
                final int length2 = this.row_heights.length;
                this.row_heights = this.ensureCapacity(this.row_heights);
                this.row_pos = this.ensureCapacity(this.row_pos);
                if (length2 < this.row_heights.length && n < length2) {
                    System.arraycopy(this.row_heights, n, this.row_heights, n + 1, length2 - n);
                }
            }
            this.row_heights[n] = BWTUtil.getHeight(this.items.elementAt(n), this) + this.space();
            for (int i = 1; i < this.items.size(); ++i) {
                this.row_pos[i] = this.row_pos[i - 1] + this.row_heights[i - 1];
            }
            if (this.visible_rows == 0) {
                this.pref_height_internal = 0;
                for (int j = 0; j < this.items.size(); ++j) {
                    this.pref_height_internal += this.row_heights[j];
                }
            }
            this.pref_width_internal = 0;
        }
        this.updateParent();
    }
    
    protected synchronized void updateSelectedList() {
        this.selected = BWTUtil.copyList(this.selected, this.items.size(), false);
        this.last_selected = BWTUtil.copyList(this.last_selected, this.items.size(), false);
    }
    
    protected synchronized void updateUserdataList() {
        if (this.userdata_list != null) {
            this.userdata_list = BWTUtil.copyList(this.userdata_list, this.items.size(), null);
        }
    }
    
    protected int calcWidestRow() {
        int max = 0;
        for (int i = 0; i < this.items.size(); ++i) {
            max = Math.max(max, BWTUtil.getWidth(this.items.elementAt(i), this));
        }
        return max + 2 * super.highlight;
    }
    
    private int prevItemPos(final int n) {
        if (n > 0) {
            return this.getRowPosition(n - 1) + this.getRowHeight(n - 1);
        }
        return 0;
    }
    
    private int space() {
        return 2 * super.highlight + this.spacing;
    }
    
    public void repaint() {
        if (this.batched) {
            this.needs_repaint = true;
            return;
        }
        super.repaint();
    }
    
    public void addNotify() {
        super.addNotify();
        this.calcSize(false);
        this.enableEvents(32L);
    }
    
    protected int preferredWidth() {
        if (this.pref_width_internal == 0) {
            this.pref_width_internal = this.calcWidestRow();
        }
        return this.pref_width_internal;
    }
    
    protected int preferredHeight() {
        return this.pref_height_internal;
    }
    
    public void paintRow(final int n) {
        if (!this.isShowing() || n < this.top_row || n > this.bottom_row) {
            return;
        }
        if (n < 0 || n >= this.items.size()) {
            return;
        }
        this.paintRow(n, null);
    }
    
    protected void draw(final Graphics graphics, final Object o, final int n, final Rectangle rectangle) {
        BWTUtil.draw(this, graphics, o, n, rectangle);
    }
    
    protected void drawHighlight(final int n, final boolean b) {
        this.drawHighlight(null, n, b);
    }
    
    protected void drawHighlight(Graphics graphics, final int n, final boolean b) {
        if (!this.isShowing() || n < 0 || n >= this.items.size()) {
            return;
        }
        this.getDrawingArea(this.draw_rect);
        int preferredWidth = this.preferredWidth();
        if (preferredWidth > this.draw_rect.width) {
            preferredWidth += 2 * (super.border + super.highlight);
        }
        this.draw_rect.reshape(this.draw_rect.x - this.horiz_origin, this.draw_rect.y + this.getRowPosition(n) - this.vert_origin, Math.max(this.draw_rect.width, preferredWidth), this.getRowHeight(n));
        final Rectangle drawingArea = this.getDrawingArea();
        final Color color = (graphics != null) ? graphics.getColor() : null;
        boolean b2 = false;
        if (graphics == null) {
            b2 = true;
            graphics = this.getGraphics();
            graphics.clipRect(drawingArea.x, drawingArea.y, drawingArea.width, drawingArea.height);
        }
        Color color2;
        if (b) {
            color2 = this.getHighlightColor();
        }
        else {
            color2 = (this.isSelected(n) ? this.getSelectedBackground() : this.getBackground());
        }
        if (color2 == null) {
            color2 = this.getForeground();
        }
        if (b && this.isSelected(n)) {
            graphics.setColor(Color.yellow);
        }
        else {
            graphics.setColor(color2);
        }
        if (b) {
            BWTUtil.drawDashedRect(graphics, this.draw_rect.x, this.draw_rect.y, this.draw_rect.width - 1, this.draw_rect.height - 1);
        }
        else {
            graphics.drawRect(this.draw_rect.x, this.draw_rect.y, this.draw_rect.width - 1, this.draw_rect.height - 1);
        }
        if (b2) {
            graphics.dispose();
            return;
        }
        if (color != null) {
            graphics.setColor(color);
        }
    }
    
    protected void paintRow(final int n, Graphics graphics) {
        this.getDrawingArea(this.clip_rect);
        this.draw_rect.reshape(this.clip_rect.x - this.horiz_origin, this.clip_rect.y + this.getRowPosition(n) - this.vert_origin, this.clip_rect.width + this.horiz_origin, this.getRowHeight(n));
        boolean b = false;
        if (graphics == null) {
            b = true;
            graphics = this.getGraphics();
            graphics.clipRect(this.clip_rect.x, this.clip_rect.y, this.clip_rect.width, this.clip_rect.height);
            if (n < this.selected.length && !this.selected[n]) {
                graphics.setColor(this.getBackground());
                graphics.fillRect(this.draw_rect.x, this.draw_rect.y, this.draw_rect.width, this.draw_rect.height);
            }
            if (!this.isEnabled()) {
                graphics.setColor(Color.lightGray.darker().darker());
            }
            else {
                graphics.setColor(this.getForeground());
            }
            graphics.setFont(this.getFont());
        }
        else if (!this.draw_rect.intersects(this.getPaintRect())) {
            return;
        }
        if (n < this.selected.length && this.selected[n]) {
            graphics.setColor(this.getSelectedBackground());
            graphics.fillRect(this.draw_rect.x, this.draw_rect.y, this.draw_rect.width, this.draw_rect.height);
            graphics.setColor(this.getSelectedForeground());
        }
        if (this.isEnabled() && n == this.focus_row) {
            this.drawHighlight(graphics, n, true);
        }
        this.draw_rect.translate(super.highlight, super.highlight);
        final Rectangle draw_rect = this.draw_rect;
        draw_rect.width -= 2 * super.highlight;
        final Rectangle draw_rect2 = this.draw_rect;
        draw_rect2.height -= 2 * super.highlight;
        if (this.items.elementAt(n) != null) {
            this.draw(graphics, this.items.elementAt(n), 3, this.draw_rect);
        }
        if (b) {
            graphics.dispose();
            return;
        }
        if (n < this.selected.length && this.selected[n]) {
            graphics.setColor(this.getForeground());
        }
    }
    
    void setFocus(final int focus_row) {
        if (focus_row == this.focus_row) {
            return;
        }
        if (this.focus_row >= 0 && this.focus_row < this.items.size()) {
            this.drawHighlight(this.focus_row, false);
        }
        this.drawHighlight(this.focus_row = focus_row, true);
        if (this.auto_select) {
            ListSelection.selectFocusRow(this, null);
        }
    }
    
    void calcBottomRow() {
        int n;
        int n2;
        for (n = ((this.vert_origin != -999) ? this.vert_origin : this.getRowPosition(this.top_row)) + this.getDrawingAreaHeight(), n2 = this.top_row + 1; n2 < this.items.size() && this.getRowPosition(n2) < n; ++n2) {}
        this.bottom_row = n2 - 1;
    }
    
    protected void paintComponent(final Graphics graphics) {
        if (this.items.size() == 0) {
            return;
        }
        this.top_row = Math.max(0, Math.min(this.top_row, this.items.size() - 1));
        if (this.vert_origin == -999) {
            this.vert_origin = this.getRowPosition(this.top_row);
        }
        this.calcBottomRow();
        Color color = null;
        if (!this.isEnabled()) {
            color = graphics.getColor();
            graphics.setColor(Color.lightGray.darker().darker());
        }
        for (int i = this.top_row; i <= this.bottom_row; ++i) {
            this.paintRow(i, graphics);
        }
        if (!this.isEnabled()) {
            graphics.setColor(color);
        }
    }
    
    protected boolean doubleClickAction(final Event event, final int n) {
        if (n < 0 || n >= this.selected.length) {
            return false;
        }
        if (!this.selected[n]) {
            this.select(n, false);
        }
        if (this.scrolled_window != null) {
            event.target = this.scrolled_window;
        }
        final JCActionEvent jcActionEvent = new JCActionEvent(event.target, event.id, null);
        for (int i = 0; i < this.actionListeners.size(); ++i) {
            ((JCActionListener)this.actionListeners.elementAt(i)).actionPerformed(jcActionEvent);
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        return ListSelection.mouseDrag(this, event);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        super.mouseDown(event, n, n2);
        if (BWTUtil.getMouseButton(event) != 1) {
            return false;
        }
        final long n3 = event.when - TransientComponent.popdown_event_timestamp;
        if (n3 >= 0L && n3 < 50L) {
            return true;
        }
        if (event.clickCount > 1) {
            final int eventToRow = this.eventToRow(event, true);
            if (eventToRow == this.last_row) {
                return this.doubleClickAction(event, eventToRow);
            }
            event.clickCount = 1;
        }
        if (this.multiple_select && event.shiftDown()) {
            return ListSelection.beginExtend(this, event);
        }
        if (this.multiple_select && event.controlDown()) {
            return ListSelection.beginToggle(this, event);
        }
        return ListSelection.beginSelect(this, event);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (event.clickCount > 1) {
            if (this.eventToRow(event, true) == this.last_row) {
                return true;
            }
            event.clickCount = 1;
        }
        if (this.multiple_select && event.shiftDown()) {
            return ListSelection.endExtend(this, event);
        }
        if (this.multiple_select && event.controlDown()) {
            return ListSelection.endToggle(this, event);
        }
        return ListSelection.endSelect(this, event);
    }
    
    protected int getNextAutoSearchIndex(final Event event, final int n) {
        int n2 = -999;
        int selectedIndex = this.getSelectedIndex();
        final int size = this.items.size();
        char c = ' ';
        if (size < 1) {
            return -999;
        }
        final Object selectedItem = this.getSelectedItem();
        if (selectedItem != null) {
            if (selectedItem instanceof String) {
                c = ((String)selectedItem).charAt(0);
            }
            else if (selectedItem instanceof JCString) {
                c = ((JCString)selectedItem).getString().charAt(0);
            }
        }
        if (!String.valueOf(c).equalsIgnoreCase(String.valueOf((char)n))) {
            selectedIndex = -999;
            c = (char)n;
        }
        if (c == ' ') {
            int max = Math.max(0, selectedIndex + 1);
            if (this.getWrapAroundSearch() && max >= size) {
                max = 0;
            }
            return max;
        }
        if (selectedIndex == -999) {
            n2 = this.findItem(c);
        }
        else {
            if (selectedIndex + 1 < size) {
                n2 = this.findNextItem(c, selectedIndex + 1);
            }
            if (this.getWrapAroundSearch() && n2 == -999) {
                n2 = this.findItem(c);
            }
        }
        return n2;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (event.key == 1005) {
            if (event.controlDown() && event.shiftDown()) {
                return ListSelection.ctrlShiftNextRow(this, event);
            }
            if (event.controlDown()) {
                return ListSelection.ctrlNextRow(this, event);
            }
            if (event.shiftDown()) {
                return ListSelection.shiftNextRow(this, event);
            }
            return ListSelection.normalNextRow(this, event);
        }
        else if (event.key == 1004) {
            if (event.controlDown() && event.shiftDown()) {
                return ListSelection.ctrlShiftPrevRow(this, event);
            }
            if (event.controlDown()) {
                return ListSelection.ctrlPrevRow(this, event);
            }
            if (event.shiftDown()) {
                return ListSelection.shiftPrevRow(this, event);
            }
            return ListSelection.normalPrevRow(this, event);
        }
        else {
            if (event.key == 10 && this.focus_row >= 0) {
                return this.doubleClickAction(event, this.focus_row);
            }
            if (event.key == 32) {
                return ListSelection.kbdSelect(this, event);
            }
            final int nextAutoSearchIndex = this.getNextAutoSearchIndex(event, n);
            if (nextAutoSearchIndex != -999) {
                this.deselectAll();
                this.makeVisible(nextAutoSearchIndex);
                ListSelection.select(this, nextAutoSearchIndex, true, event);
                return true;
            }
            return super.keyDown(event, n);
        }
    }
    
    public int getHorizOrigin() {
        return this.horiz_origin;
    }
    
    public void setHorizOrigin(final int horiz_origin) {
        this.horiz_origin = horiz_origin;
    }
    
    public int getVertOrigin() {
        return this.vert_origin;
    }
    
    public void setVertOrigin(final int vert_origin) {
        this.vert_origin = vert_origin;
        this.top_row = 0;
        while (this.top_row < this.items.size()) {
            if (this.getRowPosition(this.top_row) + this.getRowHeight(this.top_row) > vert_origin) {
                return;
            }
            ++this.top_row;
        }
    }
}
