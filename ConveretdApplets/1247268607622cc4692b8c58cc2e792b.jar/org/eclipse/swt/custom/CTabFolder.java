// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.accessibility.Accessible;
import org.eclipse.swt.accessibility.AccessibleControlListener;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.accessibility.AccessibleListener;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.widgets.TypedListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;

public class CTabFolder extends Composite
{
    public int marginWidth;
    public int marginHeight;
    public int MIN_TAB_WIDTH;
    public static RGB borderInsideRGB;
    public static RGB borderMiddleRGB;
    public static RGB borderOutsideRGB;
    boolean onBottom;
    boolean single;
    boolean simple;
    int fixedTabHeight;
    int tabHeight;
    int minChars;
    boolean borderVisible;
    CTabFolderRenderer renderer;
    CTabItem[] items;
    int firstIndex;
    int selectedIndex;
    int[] priority;
    boolean mru;
    Listener listener;
    boolean ignoreTraverse;
    CTabFolder2Listener[] folderListeners;
    CTabFolderListener[] tabListeners;
    Image selectionBgImage;
    Color[] selectionGradientColors;
    int[] selectionGradientPercents;
    boolean selectionGradientVertical;
    Color selectionForeground;
    Color selectionBackground;
    Color[] gradientColors;
    int[] gradientPercents;
    boolean gradientVertical;
    boolean showUnselectedImage;
    boolean showClose;
    boolean showUnselectedClose;
    Rectangle chevronRect;
    int chevronImageState;
    boolean showChevron;
    Menu showMenu;
    boolean showMin;
    Rectangle minRect;
    boolean minimized;
    int minImageState;
    boolean showMax;
    Rectangle maxRect;
    boolean maximized;
    int maxImageState;
    Control topRight;
    Rectangle topRightRect;
    int topRightAlignment;
    boolean inDispose;
    Point oldSize;
    Font oldFont;
    static final int DEFAULT_WIDTH = 64;
    static final int DEFAULT_HEIGHT = 64;
    static final int SELECTION_FOREGROUND = 24;
    static final int SELECTION_BACKGROUND = 25;
    static final int FOREGROUND = 21;
    static final int BACKGROUND = 22;
    static final int CHEVRON_CHILD_ID = 0;
    static final int MINIMIZE_CHILD_ID = 1;
    static final int MAXIMIZE_CHILD_ID = 2;
    static final int EXTRA_CHILD_ID_COUNT = 3;
    
    static {
        CTabFolder.borderInsideRGB = new RGB(132, 130, 132);
        CTabFolder.borderMiddleRGB = new RGB(143, 141, 138);
        CTabFolder.borderOutsideRGB = new RGB(171, 168, 165);
    }
    
    public CTabFolder(final Composite composite, final int n) {
        super(composite, checkStyle(composite, n));
        this.marginWidth = 0;
        this.marginHeight = 0;
        this.MIN_TAB_WIDTH = 4;
        this.onBottom = false;
        this.single = false;
        this.simple = true;
        this.fixedTabHeight = -1;
        this.minChars = 20;
        this.borderVisible = false;
        this.items = new CTabItem[0];
        this.firstIndex = -1;
        this.selectedIndex = -1;
        this.priority = new int[0];
        this.mru = false;
        this.folderListeners = new CTabFolder2Listener[0];
        this.tabListeners = new CTabFolderListener[0];
        this.showUnselectedImage = true;
        this.showClose = false;
        this.showUnselectedClose = true;
        this.chevronRect = new Rectangle(0, 0, 0, 0);
        this.chevronImageState = 0;
        this.showChevron = false;
        this.showMin = false;
        this.minRect = new Rectangle(0, 0, 0, 0);
        this.minimized = false;
        this.minImageState = 0;
        this.showMax = false;
        this.maxRect = new Rectangle(0, 0, 0, 0);
        this.maximized = false;
        this.maxImageState = 0;
        this.topRightRect = new Rectangle(0, 0, 0, 0);
        this.topRightAlignment = 131072;
        this.inDispose = false;
        this.init(n);
    }
    
    void init(final int n) {
        super.setLayout(new CTabFolderLayout());
        final int style = super.getStyle();
        this.oldFont = this.getFont();
        this.onBottom = ((style & 0x400) != 0x0);
        this.showClose = ((style & 0x40) != 0x0);
        this.single = ((style & 0x4) != 0x0);
        this.borderVisible = ((n & 0x800) != 0x0);
        final Display display = this.getDisplay();
        this.selectionForeground = display.getSystemColor(24);
        this.selectionBackground = display.getSystemColor(25);
        this.renderer = new CTabFolderRenderer(this);
        this.updateTabHeight(false);
        this.listener = new Listener() {
            public void handleEvent(final Event event) {
                switch (event.type) {
                    case 12: {
                        CTabFolder.this.onDispose(event);
                        break;
                    }
                    case 29: {
                        CTabFolder.this.onDragDetect(event);
                        break;
                    }
                    case 15: {
                        CTabFolder.this.onFocus(event);
                        break;
                    }
                    case 16: {
                        CTabFolder.this.onFocus(event);
                        break;
                    }
                    case 1: {
                        CTabFolder.this.onKeyDown(event);
                        break;
                    }
                    case 8: {
                        CTabFolder.this.onMouseDoubleClick(event);
                        break;
                    }
                    case 3: {
                        CTabFolder.this.onMouse(event);
                        break;
                    }
                    case 6: {
                        CTabFolder.this.onMouse(event);
                        break;
                    }
                    case 7: {
                        CTabFolder.this.onMouse(event);
                        break;
                    }
                    case 5: {
                        CTabFolder.this.onMouse(event);
                        break;
                    }
                    case 4: {
                        CTabFolder.this.onMouse(event);
                        break;
                    }
                    case 9: {
                        CTabFolder.this.onPaint(event);
                        break;
                    }
                    case 11: {
                        CTabFolder.this.onResize();
                        break;
                    }
                    case 31: {
                        CTabFolder.this.onTraverse(event);
                        break;
                    }
                }
            }
        };
        final int[] array = { 12, 29, 15, 16, 1, 8, 3, 6, 7, 5, 4, 9, 11, 31 };
        for (int i = 0; i < array.length; ++i) {
            this.addListener(array[i], this.listener);
        }
        this.initAccessible();
    }
    
    static int checkStyle(final Composite composite, int n) {
        n &= 0x68004C6;
        if ((n & 0x80) != 0x0) {
            n &= 0xFFFFFBFF;
        }
        if ((n & 0x2) != 0x0) {
            n &= 0xFFFFFFFB;
        }
        n |= 0x100000;
        if ((n & 0x4000000) != 0x0) {
            return n;
        }
        if ((composite.getStyle() & 0x8000000) != 0x0 && (n & 0x2000000) == 0x0) {
            return n;
        }
        return n | 0x20000000;
    }
    
    public void addCTabFolder2Listener(final CTabFolder2Listener cTabFolder2Listener) {
        this.checkWidget();
        if (cTabFolder2Listener == null) {
            SWT.error(4);
        }
        final CTabFolder2Listener[] folderListeners = new CTabFolder2Listener[this.folderListeners.length + 1];
        System.arraycopy(this.folderListeners, 0, folderListeners, 0, this.folderListeners.length);
        (this.folderListeners = folderListeners)[this.folderListeners.length - 1] = cTabFolder2Listener;
    }
    
    public void addCTabFolderListener(final CTabFolderListener cTabFolderListener) {
        this.checkWidget();
        if (cTabFolderListener == null) {
            SWT.error(4);
        }
        final CTabFolderListener[] tabListeners = new CTabFolderListener[this.tabListeners.length + 1];
        System.arraycopy(this.tabListeners, 0, tabListeners, 0, this.tabListeners.length);
        (this.tabListeners = tabListeners)[this.tabListeners.length - 1] = cTabFolderListener;
        if (!this.showClose) {
            this.showClose = true;
            this.updateItems();
            this.redraw();
        }
    }
    
    public void addSelectionListener(final SelectionListener selectionListener) {
        this.checkWidget();
        if (selectionListener == null) {
            SWT.error(4);
        }
        final TypedListener typedListener = new TypedListener(selectionListener);
        this.addListener(13, typedListener);
        this.addListener(14, typedListener);
    }
    
    public Rectangle computeTrim(final int n, final int n2, final int n3, final int n4) {
        this.checkWidget();
        return this.renderer.computeTrim(-1, 0, n, n2, n3, n4);
    }
    
    void createItem(final CTabItem cTabItem, final int n) {
        if (n < 0 || n > this.getItemCount()) {
            SWT.error(6);
        }
        cTabItem.parent = this;
        final CTabItem[] items = new CTabItem[this.items.length + 1];
        System.arraycopy(this.items, 0, items, 0, n);
        items[n] = cTabItem;
        System.arraycopy(this.items, n, items, n + 1, this.items.length - n);
        this.items = items;
        if (this.selectedIndex >= n) {
            ++this.selectedIndex;
        }
        final int[] priority = new int[this.priority.length + 1];
        int n2 = 0;
        int length = this.priority.length;
        for (int i = 0; i < this.priority.length; ++i) {
            if (!this.mru && this.priority[i] == n) {
                length = n2++;
            }
            priority[n2++] = ((this.priority[i] >= n) ? (this.priority[i] + 1) : this.priority[i]);
        }
        priority[length] = n;
        this.priority = priority;
        if (this.items.length == 1) {
            if (!this.updateTabHeight(false)) {
                this.updateItems();
            }
            this.redraw();
        }
        else {
            this.updateItems();
            this.redrawTabs();
        }
    }
    
    void destroyItem(final CTabItem cTabItem) {
        if (this.inDispose) {
            return;
        }
        final int index = this.indexOf(cTabItem);
        if (index == -1) {
            return;
        }
        if (this.items.length == 1) {
            this.items = new CTabItem[0];
            this.priority = new int[0];
            this.firstIndex = -1;
            this.selectedIndex = -1;
            final Control control = cTabItem.getControl();
            if (control != null && !control.isDisposed()) {
                control.setVisible(false);
            }
            this.setToolTipText(null);
            final GC buttonBounds = new GC(this);
            this.setButtonBounds(buttonBounds);
            buttonBounds.dispose();
            this.redraw();
            return;
        }
        final CTabItem[] items = new CTabItem[this.items.length - 1];
        System.arraycopy(this.items, 0, items, 0, index);
        System.arraycopy(this.items, index + 1, items, index, this.items.length - index - 1);
        this.items = items;
        final int[] priority = new int[this.priority.length - 1];
        int n = 0;
        for (int i = 0; i < this.priority.length; ++i) {
            if (this.priority[i] != index) {
                priority[n++] = ((this.priority[i] > index) ? (this.priority[i] - 1) : this.priority[i]);
            }
        }
        this.priority = priority;
        if (this.selectedIndex == index) {
            final Control control2 = cTabItem.getControl();
            this.selectedIndex = -1;
            this.setSelection(this.mru ? this.priority[0] : Math.max(0, index - 1), true);
            if (control2 != null && !control2.isDisposed()) {
                control2.setVisible(false);
            }
        }
        else if (this.selectedIndex > index) {
            --this.selectedIndex;
        }
        this.updateItems();
        this.redrawTabs();
    }
    
    public boolean getBorderVisible() {
        this.checkWidget();
        return this.borderVisible;
    }
    
    public Rectangle getClientArea() {
        this.checkWidget();
        final Rectangle computeTrim = this.renderer.computeTrim(-1, 0, 0, 0, 0, 0);
        if (this.minimized) {
            return new Rectangle(-computeTrim.x, -computeTrim.y, 0, 0);
        }
        final Point size = this.getSize();
        return new Rectangle(-computeTrim.x, -computeTrim.y, size.x - computeTrim.width, size.y - computeTrim.height);
    }
    
    public CTabItem getItem(final int n) {
        if (n < 0 || n >= this.items.length) {
            SWT.error(6);
        }
        return this.items[n];
    }
    
    public CTabItem getItem(final Point point) {
        if (this.items.length == 0) {
            return null;
        }
        if (this.getSize().x <= this.renderer.computeTrim(-3, 0, 0, 0, 0, 0).width) {
            return null;
        }
        if (this.showChevron && this.chevronRect.contains(point)) {
            return null;
        }
        for (int i = 0; i < this.priority.length; ++i) {
            final CTabItem cTabItem = this.items[this.priority[i]];
            if (cTabItem.getBounds().contains(point)) {
                return cTabItem;
            }
        }
        return null;
    }
    
    public int getItemCount() {
        return this.items.length;
    }
    
    public CTabItem[] getItems() {
        final CTabItem[] array = new CTabItem[this.items.length];
        System.arraycopy(this.items, 0, array, 0, this.items.length);
        return array;
    }
    
    char _findMnemonic(final String s) {
        if (s == null) {
            return '\0';
        }
        int n = 0;
        final int length = s.length();
        while (true) {
            if (n >= length || s.charAt(n) == '&') {
                if (++n >= length) {
                    return '\0';
                }
                if (s.charAt(n) != '&') {
                    return Character.toLowerCase(s.charAt(n));
                }
                if (++n >= length) {
                    return '\0';
                }
                continue;
            }
            else {
                ++n;
            }
        }
    }
    
    String stripMnemonic(final String s) {
        int n = 0;
        final int length = s.length();
        while (true) {
            if (n >= length || s.charAt(n) == '&') {
                if (++n >= length) {
                    return s;
                }
                if (s.charAt(n) != '&') {
                    return String.valueOf(s.substring(0, n - 1)) + s.substring(n, length);
                }
                if (++n >= length) {
                    return s;
                }
                continue;
            }
            else {
                ++n;
            }
        }
    }
    
    public boolean getMinimized() {
        this.checkWidget();
        return this.minimized;
    }
    
    public boolean getMinimizeVisible() {
        this.checkWidget();
        return this.showMin;
    }
    
    public int getMinimumCharacters() {
        this.checkWidget();
        return this.minChars;
    }
    
    public boolean getMaximized() {
        this.checkWidget();
        return this.maximized;
    }
    
    public boolean getMaximizeVisible() {
        this.checkWidget();
        return this.showMax;
    }
    
    public boolean getMRUVisible() {
        this.checkWidget();
        return this.mru;
    }
    
    public CTabFolderRenderer getRenderer() {
        this.checkWidget();
        return this.renderer;
    }
    
    int getRightItemEdge(final GC gc) {
        final Rectangle computeTrim = this.renderer.computeTrim(-3, 0, 0, 0, 0, 0);
        int n = this.getSize().x - (computeTrim.width + computeTrim.x) - 3;
        if (this.showMin) {
            n -= this.renderer.computeSize(-6, 0, gc, -1, -1).x;
        }
        if (this.showMax) {
            n -= this.renderer.computeSize(-5, 0, gc, -1, -1).x;
        }
        if (this.showChevron) {
            n -= this.renderer.computeSize(-7, 0, gc, -1, -1).x;
        }
        if (this.topRight != null && this.topRightAlignment != 4) {
            n -= this.topRight.computeSize(-1, -1).x + 3;
        }
        return Math.max(0, n);
    }
    
    public CTabItem getSelection() {
        if (this.selectedIndex == -1) {
            return null;
        }
        return this.items[this.selectedIndex];
    }
    
    public Color getSelectionBackground() {
        this.checkWidget();
        return this.selectionBackground;
    }
    
    public Color getSelectionForeground() {
        this.checkWidget();
        return this.selectionForeground;
    }
    
    public int getSelectionIndex() {
        return this.selectedIndex;
    }
    
    public boolean getSimple() {
        this.checkWidget();
        return this.simple;
    }
    
    public boolean getSingle() {
        this.checkWidget();
        return this.single;
    }
    
    public int getStyle() {
        int n = (((super.getStyle() & 0xFFFFFB7F) | (this.onBottom ? 1024 : 128)) & 0xFFFFFFF9) | (this.single ? 4 : 2);
        if (this.borderVisible) {
            n |= 0x800;
        }
        int n2 = n & 0xFFFFFFBF;
        if (this.showClose) {
            n2 |= 0x40;
        }
        return n2;
    }
    
    public int getTabHeight() {
        this.checkWidget();
        if (this.fixedTabHeight != -1) {
            return this.fixedTabHeight;
        }
        return this.tabHeight - 1;
    }
    
    public int getTabPosition() {
        this.checkWidget();
        return this.onBottom ? 1024 : 128;
    }
    
    public Control getTopRight() {
        this.checkWidget();
        return this.topRight;
    }
    
    public int getTopRightAlignment() {
        this.checkWidget();
        return this.topRightAlignment;
    }
    
    public boolean getUnselectedCloseVisible() {
        this.checkWidget();
        return this.showUnselectedClose;
    }
    
    public boolean getUnselectedImageVisible() {
        this.checkWidget();
        return this.showUnselectedImage;
    }
    
    public int indexOf(final CTabItem cTabItem) {
        this.checkWidget();
        if (cTabItem == null) {
            SWT.error(4);
        }
        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] == cTabItem) {
                return i;
            }
        }
        return -1;
    }
    
    void initAccessible() {
        final Accessible accessible = this.getAccessible();
        accessible.addAccessibleListener(new AccessibleAdapter() {
            public void getName(final AccessibleEvent accessibleEvent) {
                String result = null;
                final int childID = accessibleEvent.childID;
                if (childID >= 0 && childID < CTabFolder.this.items.length) {
                    result = CTabFolder.this.stripMnemonic(CTabFolder.this.items[childID].getText());
                }
                else if (childID == CTabFolder.this.items.length + 0) {
                    result = SWT.getMessage("SWT_ShowList");
                }
                else if (childID == CTabFolder.this.items.length + 1) {
                    result = (CTabFolder.this.minimized ? SWT.getMessage("SWT_Restore") : SWT.getMessage("SWT_Minimize"));
                }
                else if (childID == CTabFolder.this.items.length + 2) {
                    result = (CTabFolder.this.maximized ? SWT.getMessage("SWT_Restore") : SWT.getMessage("SWT_Maximize"));
                }
                accessibleEvent.result = result;
            }
            
            public void getHelp(final AccessibleEvent accessibleEvent) {
                String result = null;
                final int childID = accessibleEvent.childID;
                if (childID == -1) {
                    result = CTabFolder.this.getToolTipText();
                }
                else if (childID >= 0 && childID < CTabFolder.this.items.length) {
                    result = CTabFolder.this.items[childID].getToolTipText();
                }
                accessibleEvent.result = result;
            }
            
            public void getKeyboardShortcut(final AccessibleEvent accessibleEvent) {
                String string = null;
                final int childID = accessibleEvent.childID;
                if (childID >= 0 && childID < CTabFolder.this.items.length) {
                    final String text = CTabFolder.this.items[childID].getText();
                    if (text != null) {
                        final char findMnemonic = CTabFolder.this._findMnemonic(text);
                        if (findMnemonic != '\0') {
                            string = "Alt+" + findMnemonic;
                        }
                    }
                }
                if (childID == -1) {
                    string = "Ctrl+PageDown";
                }
                accessibleEvent.result = string;
            }
        });
        accessible.addAccessibleControlListener(new AccessibleControlAdapter() {
            public void getChildAtPoint(final AccessibleControlEvent accessibleControlEvent) {
                final Point control = CTabFolder.this.toControl(accessibleControlEvent.x, accessibleControlEvent.y);
                int childID = -2;
                for (int i = 0; i < CTabFolder.this.items.length; ++i) {
                    if (CTabFolder.this.items[i].getBounds().contains(control)) {
                        childID = i;
                        break;
                    }
                }
                if (childID == -2) {
                    if (CTabFolder.this.showChevron && CTabFolder.this.chevronRect.contains(control)) {
                        childID = CTabFolder.this.items.length + 0;
                    }
                    else if (CTabFolder.this.showMin && CTabFolder.this.minRect.contains(control)) {
                        childID = CTabFolder.this.items.length + 1;
                    }
                    else if (CTabFolder.this.showMax && CTabFolder.this.maxRect.contains(control)) {
                        childID = CTabFolder.this.items.length + 2;
                    }
                    else {
                        final Rectangle bounds = CTabFolder.this.getBounds();
                        bounds.height -= CTabFolder.this.getClientArea().height;
                        if (bounds.contains(control)) {
                            childID = -1;
                        }
                    }
                }
                accessibleControlEvent.childID = childID;
            }
            
            public void getLocation(final AccessibleControlEvent accessibleControlEvent) {
                Rectangle rectangle = null;
                Point point = null;
                final int childID = accessibleControlEvent.childID;
                if (childID == -1) {
                    rectangle = CTabFolder.this.getBounds();
                    point = CTabFolder.this.getParent().toDisplay(rectangle.x, rectangle.y);
                }
                else {
                    if (childID >= 0 && childID < CTabFolder.this.items.length && CTabFolder.this.items[childID].isShowing()) {
                        rectangle = CTabFolder.this.items[childID].getBounds();
                    }
                    else if (CTabFolder.this.showChevron && childID == CTabFolder.this.items.length + 0) {
                        rectangle = CTabFolder.this.chevronRect;
                    }
                    else if (CTabFolder.this.showMin && childID == CTabFolder.this.items.length + 1) {
                        rectangle = CTabFolder.this.minRect;
                    }
                    else if (CTabFolder.this.showMax && childID == CTabFolder.this.items.length + 2) {
                        rectangle = CTabFolder.this.maxRect;
                    }
                    if (rectangle != null) {
                        point = CTabFolder.this.toDisplay(rectangle.x, rectangle.y);
                    }
                }
                if (rectangle != null && point != null) {
                    accessibleControlEvent.x = point.x;
                    accessibleControlEvent.y = point.y;
                    accessibleControlEvent.width = rectangle.width;
                    accessibleControlEvent.height = rectangle.height;
                }
            }
            
            public void getChildCount(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.detail = CTabFolder.this.items.length + 3;
            }
            
            public void getDefaultAction(final AccessibleControlEvent accessibleControlEvent) {
                String result = null;
                final int childID = accessibleControlEvent.childID;
                if (childID >= 0 && childID < CTabFolder.this.items.length) {
                    result = SWT.getMessage("SWT_Switch");
                }
                if (childID >= CTabFolder.this.items.length && childID < CTabFolder.this.items.length + 3) {
                    result = SWT.getMessage("SWT_Press");
                }
                accessibleControlEvent.result = result;
            }
            
            public void getFocus(final AccessibleControlEvent accessibleControlEvent) {
                int selectedIndex = -2;
                if (CTabFolder.this.isFocusControl()) {
                    if (CTabFolder.this.selectedIndex == -1) {
                        selectedIndex = -1;
                    }
                    else {
                        selectedIndex = CTabFolder.this.selectedIndex;
                    }
                }
                accessibleControlEvent.childID = selectedIndex;
            }
            
            public void getRole(final AccessibleControlEvent accessibleControlEvent) {
                int detail = 0;
                final int childID = accessibleControlEvent.childID;
                if (childID == -1) {
                    detail = 60;
                }
                else if (childID >= 0 && childID < CTabFolder.this.items.length) {
                    detail = 37;
                }
                else if (childID >= CTabFolder.this.items.length && childID < CTabFolder.this.items.length + 3) {
                    detail = 43;
                }
                accessibleControlEvent.detail = detail;
            }
            
            public void getSelection(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.childID = ((CTabFolder.this.selectedIndex == -1) ? -2 : CTabFolder.this.selectedIndex);
            }
            
            public void getState(final AccessibleControlEvent accessibleControlEvent) {
                int detail = 0;
                final int childID = accessibleControlEvent.childID;
                if (childID == -1) {
                    detail = 0;
                }
                else if (childID >= 0 && childID < CTabFolder.this.items.length) {
                    detail = 2097152;
                    if (CTabFolder.this.isFocusControl()) {
                        detail |= 0x100000;
                    }
                    if (CTabFolder.this.selectedIndex == childID) {
                        detail |= 0x2;
                        if (CTabFolder.this.isFocusControl()) {
                            detail |= 0x4;
                        }
                    }
                }
                else if (childID == CTabFolder.this.items.length + 0) {
                    detail = (CTabFolder.this.showChevron ? 0 : 32768);
                }
                else if (childID == CTabFolder.this.items.length + 1) {
                    detail = (CTabFolder.this.showMin ? 0 : 32768);
                }
                else if (childID == CTabFolder.this.items.length + 2) {
                    detail = (CTabFolder.this.showMax ? 0 : 32768);
                }
                accessibleControlEvent.detail = detail;
            }
            
            public void getChildren(final AccessibleControlEvent accessibleControlEvent) {
                final int n = CTabFolder.this.items.length + 3;
                final Object[] children = new Object[n];
                for (int i = 0; i < n; ++i) {
                    children[i] = new Integer(i);
                }
                accessibleControlEvent.children = children;
            }
        });
        this.addListener(13, new Listener() {
            public void handleEvent(final Event event) {
                if (CTabFolder.this.isFocusControl()) {
                    if (CTabFolder.this.selectedIndex == -1) {
                        accessible.setFocus(-1);
                    }
                    else {
                        accessible.setFocus(CTabFolder.this.selectedIndex);
                    }
                }
            }
        });
        this.addListener(15, new Listener() {
            public void handleEvent(final Event event) {
                if (CTabFolder.this.selectedIndex == -1) {
                    accessible.setFocus(-1);
                }
                else {
                    accessible.setFocus(CTabFolder.this.selectedIndex);
                }
            }
        });
    }
    
    void onKeyDown(final Event event) {
        switch (event.keyCode) {
            case 16777219:
            case 16777220: {
                final int length = this.items.length;
                if (length == 0) {
                    return;
                }
                if (this.selectedIndex == -1) {
                    return;
                }
                final int n = (event.keyCode == (((this.getStyle() & 0x4000000) != 0x0) ? 16777220 : 16777219)) ? -1 : 1;
                int n2;
                if (!this.mru) {
                    n2 = this.selectedIndex + n;
                }
                else {
                    final int[] array = new int[this.items.length];
                    int n3 = 0;
                    int n4 = -1;
                    for (int i = 0; i < this.items.length; ++i) {
                        if (this.items[i].showing) {
                            if (i == this.selectedIndex) {
                                n4 = n3;
                            }
                            array[n3++] = i;
                        }
                    }
                    if (n4 + n < 0 || n4 + n >= n3) {
                        if (this.showChevron) {
                            final CTabFolderEvent cTabFolderEvent = new CTabFolderEvent(this);
                            cTabFolderEvent.widget = this;
                            cTabFolderEvent.time = event.time;
                            cTabFolderEvent.x = this.chevronRect.x;
                            cTabFolderEvent.y = this.chevronRect.y;
                            cTabFolderEvent.width = this.chevronRect.width;
                            cTabFolderEvent.height = this.chevronRect.height;
                            cTabFolderEvent.doit = true;
                            for (int j = 0; j < this.folderListeners.length; ++j) {
                                this.folderListeners[j].showList(cTabFolderEvent);
                            }
                            if (cTabFolderEvent.doit && !this.isDisposed()) {
                                this.showList(this.chevronRect);
                            }
                        }
                        return;
                    }
                    n2 = array[n4 + n];
                }
                if (n2 < 0 || n2 >= length) {
                    return;
                }
                this.setSelection(n2, true);
                this.forceFocus();
                break;
            }
        }
    }
    
    void onDispose(final Event event) {
        this.removeListener(12, this.listener);
        this.notifyListeners(12, event);
        event.type = 0;
        this.inDispose = true;
        if (this.showMenu != null && !this.showMenu.isDisposed()) {
            this.showMenu.dispose();
            this.showMenu = null;
        }
        for (int length = this.items.length, i = 0; i < length; ++i) {
            if (this.items[i] != null) {
                this.items[i].dispose();
            }
        }
        this.selectionGradientColors = null;
        this.selectionGradientPercents = null;
        this.selectionBgImage = null;
        this.selectionBackground = null;
        this.selectionForeground = null;
        if (this.renderer != null) {
            this.renderer.dispose();
        }
        this.renderer = null;
    }
    
    void onDragDetect(final Event event) {
        boolean b = false;
        if (this.chevronRect.contains(event.x, event.y) || this.minRect.contains(event.x, event.y) || this.maxRect.contains(event.x, event.y)) {
            b = true;
        }
        else {
            for (int i = 0; i < this.items.length; ++i) {
                if (this.items[i].closeRect.contains(event.x, event.y)) {
                    b = true;
                    break;
                }
            }
        }
        if (b) {
            event.type = 0;
        }
    }
    
    void onFocus(final Event event) {
        this.checkWidget();
        if (this.selectedIndex >= 0) {
            this.redraw();
        }
        else {
            this.setSelection(0, true);
        }
    }
    
    boolean onMnemonic(final Event event, final boolean b) {
        final char character = event.character;
        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] != null) {
                final char findMnemonic = this._findMnemonic(this.items[i].getText());
                if (findMnemonic != '\0' && Character.toLowerCase(character) == findMnemonic) {
                    if (b) {
                        this.setSelection(i, true);
                        this.forceFocus();
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    void onMouseDoubleClick(final Event event) {
        if (event.button != 1 || (event.stateMask & 0x100000) != 0x0 || (event.stateMask & 0x200000) != 0x0) {
            return;
        }
        final Event event2 = new Event();
        event2.item = this.getItem(new Point(event.x, event.y));
        if (event2.item != null) {
            this.notifyListeners(14, event2);
        }
    }
    
    void onMouse(final Event event) {
        final int x = event.x;
        final int y = event.y;
        switch (event.type) {
            case 6: {
                this.setToolTipText(null);
                break;
            }
            case 7: {
                if (this.minImageState != 0) {
                    this.minImageState = 0;
                    this.redraw(this.minRect.x, this.minRect.y, this.minRect.width, this.minRect.height, false);
                }
                if (this.maxImageState != 0) {
                    this.maxImageState = 0;
                    this.redraw(this.maxRect.x, this.maxRect.y, this.maxRect.width, this.maxRect.height, false);
                }
                if (this.chevronImageState != 0) {
                    this.chevronImageState = 0;
                    this.redraw(this.chevronRect.x, this.chevronRect.y, this.chevronRect.width, this.chevronRect.height, false);
                }
                for (int i = 0; i < this.items.length; ++i) {
                    final CTabItem cTabItem = this.items[i];
                    if (i != this.selectedIndex && cTabItem.closeImageState != 8) {
                        cTabItem.closeImageState = 8;
                        this.redraw(cTabItem.closeRect.x, cTabItem.closeRect.y, cTabItem.closeRect.width, cTabItem.closeRect.height, false);
                    }
                    if ((cTabItem.state & 0x20) != 0x0) {
                        final CTabItem cTabItem2 = cTabItem;
                        cTabItem2.state &= 0xFFFFFFDF;
                        this.redraw(cTabItem.x, cTabItem.y, cTabItem.width, cTabItem.height, false);
                    }
                    if (i == this.selectedIndex && cTabItem.closeImageState != 0) {
                        cTabItem.closeImageState = 0;
                        this.redraw(cTabItem.closeRect.x, cTabItem.closeRect.y, cTabItem.closeRect.width, cTabItem.closeRect.height, false);
                    }
                }
                break;
            }
            case 3: {
                if (event.button != 1) {
                    return;
                }
                if (this.minRect.contains(x, y)) {
                    this.minImageState = 2;
                    this.redraw(this.minRect.x, this.minRect.y, this.minRect.width, this.minRect.height, false);
                    this.update();
                    return;
                }
                if (this.maxRect.contains(x, y)) {
                    this.maxImageState = 2;
                    this.redraw(this.maxRect.x, this.maxRect.y, this.maxRect.width, this.maxRect.height, false);
                    this.update();
                    return;
                }
                if (this.chevronRect.contains(x, y)) {
                    if (this.chevronImageState != 32) {
                        this.chevronImageState = 32;
                    }
                    else {
                        this.chevronImageState = 2;
                    }
                    this.redraw(this.chevronRect.x, this.chevronRect.y, this.chevronRect.width, this.chevronRect.height, false);
                    this.update();
                    return;
                }
                CTabItem cTabItem3 = null;
                if (this.single) {
                    if (this.selectedIndex != -1 && this.items[this.selectedIndex].getBounds().contains(x, y)) {
                        cTabItem3 = this.items[this.selectedIndex];
                    }
                }
                else {
                    for (int j = 0; j < this.items.length; ++j) {
                        if (this.items[j].getBounds().contains(x, y)) {
                            cTabItem3 = this.items[j];
                        }
                    }
                }
                if (cTabItem3 == null) {
                    break;
                }
                if (cTabItem3.closeRect.contains(x, y)) {
                    cTabItem3.closeImageState = 2;
                    this.redraw(cTabItem3.closeRect.x, cTabItem3.closeRect.y, cTabItem3.closeRect.width, cTabItem3.closeRect.height, false);
                    this.update();
                    return;
                }
                final int index = this.indexOf(cTabItem3);
                if (cTabItem3.showing) {
                    final int selectedIndex = this.selectedIndex;
                    this.setSelection(index, true);
                    if (selectedIndex == this.selectedIndex) {
                        this.forceFocus();
                    }
                }
            }
            case 5: {
                this._setToolTipText(event.x, event.y);
                boolean b = false;
                boolean b2 = false;
                boolean b3 = false;
                if (this.minRect.contains(x, y)) {
                    b = true;
                    if (this.minImageState != 2 && this.minImageState != 32) {
                        this.minImageState = 32;
                        this.redraw(this.minRect.x, this.minRect.y, this.minRect.width, this.minRect.height, false);
                    }
                }
                if (this.maxRect.contains(x, y)) {
                    b2 = true;
                    if (this.maxImageState != 2 && this.maxImageState != 32) {
                        this.maxImageState = 32;
                        this.redraw(this.maxRect.x, this.maxRect.y, this.maxRect.width, this.maxRect.height, false);
                    }
                }
                if (this.chevronRect.contains(x, y)) {
                    b3 = true;
                    if (this.chevronImageState != 2 && this.chevronImageState != 32) {
                        this.chevronImageState = 32;
                        this.redraw(this.chevronRect.x, this.chevronRect.y, this.chevronRect.width, this.chevronRect.height, false);
                    }
                }
                if (this.minImageState != 0 && !b) {
                    this.minImageState = 0;
                    this.redraw(this.minRect.x, this.minRect.y, this.minRect.width, this.minRect.height, false);
                }
                if (this.maxImageState != 0 && !b2) {
                    this.maxImageState = 0;
                    this.redraw(this.maxRect.x, this.maxRect.y, this.maxRect.width, this.maxRect.height, false);
                }
                if (this.chevronImageState != 0 && !b3) {
                    this.chevronImageState = 0;
                    this.redraw(this.chevronRect.x, this.chevronRect.y, this.chevronRect.width, this.chevronRect.height, false);
                }
                for (int k = 0; k < this.items.length; ++k) {
                    final CTabItem cTabItem4 = this.items[k];
                    boolean b4 = false;
                    if (cTabItem4.getBounds().contains(x, y)) {
                        b4 = true;
                        if (cTabItem4.closeRect.contains(x, y)) {
                            if (cTabItem4.closeImageState != 2 && cTabItem4.closeImageState != 32) {
                                cTabItem4.closeImageState = 32;
                                this.redraw(cTabItem4.closeRect.x, cTabItem4.closeRect.y, cTabItem4.closeRect.width, cTabItem4.closeRect.height, false);
                            }
                        }
                        else if (cTabItem4.closeImageState != 0) {
                            cTabItem4.closeImageState = 0;
                            this.redraw(cTabItem4.closeRect.x, cTabItem4.closeRect.y, cTabItem4.closeRect.width, cTabItem4.closeRect.height, false);
                        }
                        if ((cTabItem4.state & 0x20) == 0x0) {
                            final CTabItem cTabItem5 = cTabItem4;
                            cTabItem5.state |= 0x20;
                            this.redraw(cTabItem4.x, cTabItem4.y, cTabItem4.width, cTabItem4.height, false);
                        }
                    }
                    if (k != this.selectedIndex && cTabItem4.closeImageState != 8 && !b4) {
                        cTabItem4.closeImageState = 8;
                        this.redraw(cTabItem4.closeRect.x, cTabItem4.closeRect.y, cTabItem4.closeRect.width, cTabItem4.closeRect.height, false);
                    }
                    if ((cTabItem4.state & 0x20) != 0x0 && !b4) {
                        final CTabItem cTabItem6 = cTabItem4;
                        cTabItem6.state &= 0xFFFFFFDF;
                        this.redraw(cTabItem4.x, cTabItem4.y, cTabItem4.width, cTabItem4.height, false);
                    }
                    if (k == this.selectedIndex && cTabItem4.closeImageState != 0 && !b4) {
                        cTabItem4.closeImageState = 0;
                        this.redraw(cTabItem4.closeRect.x, cTabItem4.closeRect.y, cTabItem4.closeRect.width, cTabItem4.closeRect.height, false);
                    }
                }
                break;
            }
            case 4: {
                if (event.button != 1) {
                    return;
                }
                if (this.chevronRect.contains(x, y)) {
                    if (this.chevronImageState != 2) {
                        return;
                    }
                    final CTabFolderEvent cTabFolderEvent = new CTabFolderEvent(this);
                    cTabFolderEvent.widget = this;
                    cTabFolderEvent.time = event.time;
                    cTabFolderEvent.x = this.chevronRect.x;
                    cTabFolderEvent.y = this.chevronRect.y;
                    cTabFolderEvent.width = this.chevronRect.width;
                    cTabFolderEvent.height = this.chevronRect.height;
                    cTabFolderEvent.doit = true;
                    for (int l = 0; l < this.folderListeners.length; ++l) {
                        this.folderListeners[l].showList(cTabFolderEvent);
                    }
                    if (cTabFolderEvent.doit && !this.isDisposed()) {
                        this.showList(this.chevronRect);
                    }
                    return;
                }
                else if (this.minRect.contains(x, y)) {
                    final boolean b5 = this.minImageState == 2;
                    this.minImageState = 32;
                    this.redraw(this.minRect.x, this.minRect.y, this.minRect.width, this.minRect.height, false);
                    if (!b5) {
                        return;
                    }
                    final CTabFolderEvent cTabFolderEvent2 = new CTabFolderEvent(this);
                    cTabFolderEvent2.widget = this;
                    cTabFolderEvent2.time = event.time;
                    for (int n = 0; n < this.folderListeners.length; ++n) {
                        if (this.minimized) {
                            this.folderListeners[n].restore(cTabFolderEvent2);
                        }
                        else {
                            this.folderListeners[n].minimize(cTabFolderEvent2);
                        }
                    }
                    return;
                }
                else if (this.maxRect.contains(x, y)) {
                    final boolean b6 = this.maxImageState == 2;
                    this.maxImageState = 32;
                    this.redraw(this.maxRect.x, this.maxRect.y, this.maxRect.width, this.maxRect.height, false);
                    if (!b6) {
                        return;
                    }
                    final CTabFolderEvent cTabFolderEvent3 = new CTabFolderEvent(this);
                    cTabFolderEvent3.widget = this;
                    cTabFolderEvent3.time = event.time;
                    for (int n2 = 0; n2 < this.folderListeners.length; ++n2) {
                        if (this.maximized) {
                            this.folderListeners[n2].restore(cTabFolderEvent3);
                        }
                        else {
                            this.folderListeners[n2].maximize(cTabFolderEvent3);
                        }
                    }
                    return;
                }
                else {
                    CTabItem item = null;
                    if (this.single) {
                        if (this.selectedIndex != -1 && this.items[this.selectedIndex].getBounds().contains(x, y)) {
                            item = this.items[this.selectedIndex];
                        }
                    }
                    else {
                        for (int n3 = 0; n3 < this.items.length; ++n3) {
                            if (this.items[n3].getBounds().contains(x, y)) {
                                item = this.items[n3];
                            }
                        }
                    }
                    if (item == null || !item.closeRect.contains(x, y)) {
                        break;
                    }
                    final boolean b7 = item.closeImageState == 2;
                    item.closeImageState = 32;
                    this.redraw(item.closeRect.x, item.closeRect.y, item.closeRect.width, item.closeRect.height, false);
                    if (!b7) {
                        return;
                    }
                    final CTabFolderEvent cTabFolderEvent4 = new CTabFolderEvent(this);
                    cTabFolderEvent4.widget = this;
                    cTabFolderEvent4.time = event.time;
                    cTabFolderEvent4.item = item;
                    cTabFolderEvent4.doit = true;
                    for (int n4 = 0; n4 < this.folderListeners.length; ++n4) {
                        this.folderListeners[n4].close(cTabFolderEvent4);
                    }
                    for (int n5 = 0; n5 < this.tabListeners.length; ++n5) {
                        this.tabListeners[n5].itemClosed(cTabFolderEvent4);
                    }
                    if (cTabFolderEvent4.doit) {
                        item.dispose();
                    }
                    if (!this.isDisposed() && item.isDisposed()) {
                        final Display display = this.getDisplay();
                        final Point cursorLocation = display.getCursorLocation();
                        final Point map = display.map(null, this, cursorLocation.x, cursorLocation.y);
                        final CTabItem item2 = this.getItem(map);
                        if (item2 != null) {
                            if (item2.closeRect.contains(map)) {
                                if (item2.closeImageState != 2 && item2.closeImageState != 32) {
                                    item2.closeImageState = 32;
                                    this.redraw(item2.closeRect.x, item2.closeRect.y, item2.closeRect.width, item2.closeRect.height, false);
                                }
                            }
                            else if (item2.closeImageState != 0) {
                                item2.closeImageState = 0;
                                this.redraw(item2.closeRect.x, item2.closeRect.y, item2.closeRect.width, item2.closeRect.height, false);
                            }
                        }
                    }
                    return;
                }
                break;
            }
        }
    }
    
    void onPageTraversal(final Event event) {
        final int length = this.items.length;
        if (length == 0) {
            return;
        }
        int selectedIndex = this.selectedIndex;
        if (selectedIndex == -1) {
            selectedIndex = 0;
        }
        else {
            final int n = (event.detail == 512) ? 1 : -1;
            if (!this.mru) {
                selectedIndex = (this.selectedIndex + n + length) % length;
            }
            else {
                final int[] array = new int[this.items.length];
                int n2 = 0;
                int n3 = -1;
                for (int i = 0; i < this.items.length; ++i) {
                    if (this.items[i].showing) {
                        if (i == this.selectedIndex) {
                            n3 = n2;
                        }
                        array[n2++] = i;
                    }
                }
                if (n3 + n >= 0 && n3 + n < n2) {
                    selectedIndex = array[n3 + n];
                }
                else if (this.showChevron) {
                    final CTabFolderEvent cTabFolderEvent = new CTabFolderEvent(this);
                    cTabFolderEvent.widget = this;
                    cTabFolderEvent.time = event.time;
                    cTabFolderEvent.x = this.chevronRect.x;
                    cTabFolderEvent.y = this.chevronRect.y;
                    cTabFolderEvent.width = this.chevronRect.width;
                    cTabFolderEvent.height = this.chevronRect.height;
                    cTabFolderEvent.doit = true;
                    for (int j = 0; j < this.folderListeners.length; ++j) {
                        this.folderListeners[j].showList(cTabFolderEvent);
                    }
                    if (cTabFolderEvent.doit && !this.isDisposed()) {
                        this.showList(this.chevronRect);
                    }
                }
            }
        }
        this.setSelection(selectedIndex, true);
    }
    
    void onPaint(final Event event) {
        if (this.inDispose) {
            return;
        }
        final Font font = this.getFont();
        if (this.oldFont == null || !this.oldFont.equals(font)) {
            this.oldFont = font;
            if (!this.updateTabHeight(false)) {
                this.updateItems();
                this.redraw();
                return;
            }
        }
        final GC gc = event.gc;
        final Font font2 = gc.getFont();
        final Color background = gc.getBackground();
        final Color foreground = gc.getForeground();
        final Point size = this.getSize();
        final Rectangle rectangle = new Rectangle(0, 0, size.x, size.y);
        this.renderer.draw(-1, 24, rectangle, gc);
        gc.setFont(font2);
        gc.setForeground(foreground);
        gc.setBackground(background);
        this.renderer.draw(-2, 24, rectangle, gc);
        gc.setFont(font2);
        gc.setForeground(foreground);
        gc.setBackground(background);
        if (!this.single) {
            for (int i = 0; i < this.items.length; ++i) {
                final Rectangle bounds = this.items[i].getBounds();
                if (i != this.selectedIndex && event.getBounds().intersects(bounds)) {
                    this.renderer.draw(i, 0x18 | this.items[i].state, bounds, gc);
                }
            }
        }
        gc.setFont(font2);
        gc.setForeground(foreground);
        gc.setBackground(background);
        if (this.selectedIndex != -1) {
            this.renderer.draw(this.selectedIndex, this.items[this.selectedIndex].state | 0x8 | 0x10, this.items[this.selectedIndex].getBounds(), gc);
        }
        gc.setFont(font2);
        gc.setForeground(foreground);
        gc.setBackground(background);
        this.renderer.draw(-5, this.maxImageState, this.maxRect, gc);
        this.renderer.draw(-6, this.minImageState, this.minRect, gc);
        this.renderer.draw(-7, this.chevronImageState, this.chevronRect, gc);
        gc.setFont(font2);
        gc.setForeground(foreground);
        gc.setBackground(background);
    }
    
    void onResize() {
        if (this.updateItems()) {
            this.redrawTabs();
        }
        final Point size = this.getSize();
        if (this.oldSize == null) {
            this.redraw();
        }
        else if (this.onBottom && size.y != this.oldSize.y) {
            this.redraw();
        }
        else {
            int min = Math.min(size.x, this.oldSize.x);
            final Rectangle computeTrim = this.renderer.computeTrim(-1, 0, 0, 0, 0, 0);
            if (size.x != this.oldSize.x) {
                min -= computeTrim.width + computeTrim.x - this.marginWidth + 2;
            }
            if (!this.simple) {
                min -= 5;
            }
            int min2 = Math.min(size.y, this.oldSize.y);
            if (size.y != this.oldSize.y) {
                min2 -= computeTrim.height + computeTrim.y - this.marginHeight;
            }
            final int max = Math.max(size.x, this.oldSize.x);
            final int max2 = Math.max(size.y, this.oldSize.y);
            this.redraw(0, min2, max, max2 - min2, false);
            this.redraw(min, 0, max - min, max2, false);
        }
        this.oldSize = size;
    }
    
    void onTraverse(final Event event) {
        if (this.ignoreTraverse) {
            return;
        }
        switch (event.detail) {
            case 2:
            case 4:
            case 8:
            case 16: {
                if (this.getDisplay().getFocusControl() == this) {
                    event.doit = true;
                    break;
                }
                break;
            }
            case 128: {
                event.doit = this.onMnemonic(event, false);
                break;
            }
            case 256:
            case 512: {
                event.doit = (this.items.length > 0);
                break;
            }
        }
        this.ignoreTraverse = true;
        this.notifyListeners(31, event);
        this.ignoreTraverse = false;
        event.type = 0;
        if (this.isDisposed()) {
            return;
        }
        if (!event.doit) {
            return;
        }
        switch (event.detail) {
            case 128: {
                this.onMnemonic(event, true);
                event.detail = 0;
                break;
            }
            case 256:
            case 512: {
                this.onPageTraversal(event);
                event.detail = 0;
                break;
            }
        }
    }
    
    void redrawTabs() {
        final Point size = this.getSize();
        final Rectangle computeTrim = this.renderer.computeTrim(-1, 0, 0, 0, 0, 0);
        if (this.onBottom) {
            final int n = computeTrim.height + computeTrim.y - this.marginHeight;
            this.redraw(0, size.y - n - 1, size.x, n + 1, false);
        }
        else {
            this.redraw(0, 0, size.x, -computeTrim.y - this.marginHeight + 1, false);
        }
    }
    
    public void removeCTabFolder2Listener(final CTabFolder2Listener cTabFolder2Listener) {
        this.checkWidget();
        if (cTabFolder2Listener == null) {
            SWT.error(4);
        }
        if (this.folderListeners.length == 0) {
            return;
        }
        int n = -1;
        for (int i = 0; i < this.folderListeners.length; ++i) {
            if (cTabFolder2Listener == this.folderListeners[i]) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            return;
        }
        if (this.folderListeners.length == 1) {
            this.folderListeners = new CTabFolder2Listener[0];
            return;
        }
        final CTabFolder2Listener[] folderListeners = new CTabFolder2Listener[this.folderListeners.length - 1];
        System.arraycopy(this.folderListeners, 0, folderListeners, 0, n);
        System.arraycopy(this.folderListeners, n + 1, folderListeners, n, this.folderListeners.length - n - 1);
        this.folderListeners = folderListeners;
    }
    
    public void removeCTabFolderListener(final CTabFolderListener cTabFolderListener) {
        this.checkWidget();
        if (cTabFolderListener == null) {
            SWT.error(4);
        }
        if (this.tabListeners.length == 0) {
            return;
        }
        int n = -1;
        for (int i = 0; i < this.tabListeners.length; ++i) {
            if (cTabFolderListener == this.tabListeners[i]) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            return;
        }
        if (this.tabListeners.length == 1) {
            this.tabListeners = new CTabFolderListener[0];
            return;
        }
        final CTabFolderListener[] tabListeners = new CTabFolderListener[this.tabListeners.length - 1];
        System.arraycopy(this.tabListeners, 0, tabListeners, 0, n);
        System.arraycopy(this.tabListeners, n + 1, tabListeners, n, this.tabListeners.length - n - 1);
        this.tabListeners = tabListeners;
    }
    
    public void removeSelectionListener(final SelectionListener selectionListener) {
        this.checkWidget();
        if (selectionListener == null) {
            SWT.error(4);
        }
        this.removeListener(13, selectionListener);
        this.removeListener(14, selectionListener);
    }
    
    public void reskin(final int n) {
        super.reskin(n);
        for (int i = 0; i < this.items.length; ++i) {
            this.items[i].reskin(n);
        }
    }
    
    public void setBackground(final Color background) {
        super.setBackground(background);
        this.renderer.createAntialiasColors();
        this.redraw();
    }
    
    public void setBackground(final Color[] array, final int[] array2) {
        this.setBackground(array, array2, false);
    }
    
    public void setBackground(Color[] array, int[] array2, final boolean gradientVertical) {
        this.checkWidget();
        if (array != null) {
            if (array2 == null || array2.length != array.length - 1) {
                SWT.error(5);
            }
            for (int i = 0; i < array2.length; ++i) {
                if (array2[i] < 0 || array2[i] > 100) {
                    SWT.error(5);
                }
                if (i > 0 && array2[i] < array2[i - 1]) {
                    SWT.error(5);
                }
            }
            if (this.getDisplay().getDepth() < 15) {
                array = new Color[] { array[array.length - 1] };
                array2 = new int[0];
            }
        }
        if (this.gradientColors != null && array != null && this.gradientColors.length == array.length) {
            boolean equals = false;
            for (int j = 0; j < this.gradientColors.length; ++j) {
                if (this.gradientColors[j] == null) {
                    equals = (array[j] == null);
                }
                else {
                    equals = this.gradientColors[j].equals(array[j]);
                }
                if (!equals) {
                    break;
                }
            }
            if (equals) {
                for (int k = 0; k < this.gradientPercents.length; ++k) {
                    equals = (this.gradientPercents[k] == array2[k]);
                    if (!equals) {
                        break;
                    }
                }
            }
            if (equals && this.gradientVertical == gradientVertical) {
                return;
            }
        }
        if (array == null) {
            this.gradientColors = null;
            this.gradientPercents = null;
            this.gradientVertical = false;
            this.setBackground(null);
        }
        else {
            this.gradientColors = new Color[array.length];
            for (int l = 0; l < array.length; ++l) {
                this.gradientColors[l] = array[l];
            }
            this.gradientPercents = new int[array2.length];
            for (int n = 0; n < array2.length; ++n) {
                this.gradientPercents[n] = array2[n];
            }
            this.gradientVertical = gradientVertical;
            this.setBackground(this.gradientColors[this.gradientColors.length - 1]);
        }
        this.redraw();
    }
    
    public void setBackgroundImage(final Image backgroundImage) {
        super.setBackgroundImage(backgroundImage);
        this.renderer.createAntialiasColors();
        this.redraw();
    }
    
    public void setBorderVisible(final boolean borderVisible) {
        this.checkWidget();
        if (this.borderVisible == borderVisible) {
            return;
        }
        this.borderVisible = borderVisible;
        final Rectangle clientArea = this.getClientArea();
        this.updateItems();
        if (!clientArea.equals(this.getClientArea())) {
            this.notifyListeners(11, new Event());
        }
        this.redraw();
    }
    
    void setButtonBounds(final GC gc) {
        final Point size = this.getSize();
        final Rectangle computeTrim = this.renderer.computeTrim(-3, 0, 0, 0, 0, 0);
        final int n = computeTrim.width + computeTrim.x;
        final int n2 = -computeTrim.x;
        final int n3 = computeTrim.height + computeTrim.y;
        final int n4 = -computeTrim.y;
        final int x = this.maxRect.x;
        final int y = this.maxRect.y;
        final int width = this.maxRect.width;
        final int height = this.maxRect.height;
        final Rectangle maxRect = this.maxRect;
        final Rectangle maxRect2 = this.maxRect;
        final Rectangle maxRect3 = this.maxRect;
        final Rectangle maxRect4 = this.maxRect;
        final boolean b = false;
        maxRect4.height = (b ? 1 : 0);
        maxRect3.width = (b ? 1 : 0);
        maxRect2.y = (b ? 1 : 0);
        maxRect.x = (b ? 1 : 0);
        if (this.showMax) {
            final Point computeSize = this.renderer.computeSize(-5, 0, gc, -1, -1);
            this.maxRect.x = size.x - n - computeSize.x - 3;
            if (n > 0) {
                final Rectangle maxRect5 = this.maxRect;
                ++maxRect5.x;
            }
            this.maxRect.y = (this.onBottom ? (size.y - n3 - this.tabHeight + (this.tabHeight - computeSize.y) / 2) : (n4 + (this.tabHeight - computeSize.y) / 2));
            this.maxRect.width = computeSize.x;
            this.maxRect.height = computeSize.y;
        }
        if (x != this.maxRect.x || width != this.maxRect.width || y != this.maxRect.y || height != this.maxRect.height) {
            final int min = Math.min(x, this.maxRect.x);
            this.redraw(min, this.onBottom ? (size.y - n3 - this.tabHeight) : (n4 + 1), Math.max(x + width, this.maxRect.x + this.maxRect.width) - min, this.tabHeight, false);
        }
        final int x2 = this.minRect.x;
        final int y2 = this.minRect.y;
        final int width2 = this.minRect.width;
        final int height2 = this.minRect.height;
        final Rectangle minRect = this.minRect;
        final Rectangle minRect2 = this.minRect;
        final Rectangle minRect3 = this.minRect;
        final Rectangle minRect4 = this.minRect;
        final boolean b2 = false;
        minRect4.height = (b2 ? 1 : 0);
        minRect3.width = (b2 ? 1 : 0);
        minRect2.y = (b2 ? 1 : 0);
        minRect.x = (b2 ? 1 : 0);
        if (this.showMin) {
            final Point computeSize2 = this.renderer.computeSize(-6, 0, gc, -1, -1);
            this.minRect.x = size.x - n - this.maxRect.width - computeSize2.x - 3;
            if (n > 0) {
                final Rectangle minRect5 = this.minRect;
                ++minRect5.x;
            }
            this.minRect.y = (this.onBottom ? (size.y - n3 - this.tabHeight + (this.tabHeight - computeSize2.y) / 2) : (n4 + (this.tabHeight - computeSize2.y) / 2));
            this.minRect.width = computeSize2.x;
            this.minRect.height = computeSize2.y;
        }
        if (x2 != this.minRect.x || width2 != this.minRect.width || y2 != this.minRect.y || height2 != this.minRect.height) {
            final int min2 = Math.min(x2, this.minRect.x);
            this.redraw(min2, this.onBottom ? (size.y - n3 - this.tabHeight) : (n4 + 1), Math.max(x2 + width2, this.minRect.x + this.minRect.width) - min2, this.tabHeight, false);
        }
        final int x3 = this.topRightRect.x;
        final int y3 = this.topRightRect.y;
        final int width3 = this.topRightRect.width;
        final int height3 = this.topRightRect.height;
        final Rectangle topRightRect = this.topRightRect;
        final Rectangle topRightRect2 = this.topRightRect;
        final Rectangle topRightRect3 = this.topRightRect;
        final Rectangle topRightRect4 = this.topRightRect;
        final boolean b3 = false;
        topRightRect4.height = (b3 ? 1 : 0);
        topRightRect3.width = (b3 ? 1 : 0);
        topRightRect2.y = (b3 ? 1 : 0);
        topRightRect.x = (b3 ? 1 : 0);
        if (this.topRight != null) {
            switch (this.topRightAlignment) {
                case 4: {
                    int n5 = size.x - n - 3 - this.maxRect.width - this.minRect.width;
                    if (!this.simple && n > 0 && !this.showMax && !this.showMin) {
                        n5 -= 2;
                    }
                    if (this.single) {
                        if (this.items.length == 0 || this.selectedIndex == -1) {
                            this.topRightRect.x = n2 + 3;
                            this.topRightRect.width = n5 - this.topRightRect.x;
                        }
                        else {
                            final CTabItem cTabItem = this.items[this.selectedIndex];
                            final int x4 = this.renderer.computeSize(-7, 0, gc, -1, -1).x;
                            if (cTabItem.x + cTabItem.width + 7 + x4 >= n5) {
                                break;
                            }
                            this.topRightRect.x = cTabItem.x + cTabItem.width + 7 + x4;
                            this.topRightRect.width = n5 - this.topRightRect.x;
                        }
                    }
                    else {
                        if (this.showChevron) {
                            break;
                        }
                        if (this.items.length == 0) {
                            this.topRightRect.x = n2 + 3;
                        }
                        else {
                            final CTabItem cTabItem2 = this.items[this.items.length - 1];
                            this.topRightRect.x = cTabItem2.x + cTabItem2.width;
                        }
                        this.topRightRect.width = Math.max(0, n5 - this.topRightRect.x);
                    }
                    this.topRightRect.y = (this.onBottom ? (size.y - n3 - this.tabHeight) : (n4 + 1));
                    this.topRightRect.height = this.tabHeight - 1;
                    break;
                }
                case 131072: {
                    final Point computeSize3 = this.topRight.computeSize(-1, this.tabHeight, false);
                    int n6 = size.x - n - 3 - this.maxRect.width - this.minRect.width;
                    if (!this.simple && n > 0 && !this.showMax && !this.showMin) {
                        n6 -= 2;
                    }
                    this.topRightRect.x = n6 - computeSize3.x;
                    this.topRightRect.width = computeSize3.x;
                    this.topRightRect.y = (this.onBottom ? (size.y - n3 - this.tabHeight) : (n4 + 1));
                    this.topRightRect.height = this.tabHeight - 1;
                    break;
                }
            }
            this.topRight.setBounds(this.topRightRect);
        }
        if (x3 != this.topRightRect.x || width3 != this.topRightRect.width || y3 != this.topRightRect.y || height3 != this.topRightRect.height) {
            final int min3 = Math.min(x3, this.topRightRect.x);
            this.redraw(min3, this.onBottom ? (size.y - n3 - this.tabHeight) : (n4 + 1), Math.max(x3 + width3, this.topRightRect.x + this.topRightRect.width) - min3, this.tabHeight, false);
        }
        final int x5 = this.chevronRect.x;
        final int y4 = this.chevronRect.y;
        final int width4 = this.chevronRect.width;
        final int height4 = this.chevronRect.height;
        final Rectangle chevronRect = this.chevronRect;
        final Rectangle chevronRect2 = this.chevronRect;
        final Rectangle chevronRect3 = this.chevronRect;
        final Rectangle chevronRect4 = this.chevronRect;
        final boolean b4 = false;
        chevronRect4.width = (b4 ? 1 : 0);
        chevronRect3.height = (b4 ? 1 : 0);
        chevronRect2.y = (b4 ? 1 : 0);
        chevronRect.x = (b4 ? 1 : 0);
        final Point computeSize4 = this.renderer.computeSize(-7, 0, gc, -1, -1);
        if (this.single) {
            if (this.selectedIndex == -1 || this.items.length > 1) {
                this.chevronRect.width = computeSize4.x;
                this.chevronRect.height = computeSize4.y;
                this.chevronRect.y = (this.onBottom ? (size.y - n3 - this.tabHeight + (this.tabHeight - this.chevronRect.height) / 2) : (n4 + (this.tabHeight - this.chevronRect.height) / 2));
                if (this.selectedIndex == -1) {
                    this.chevronRect.x = size.x - n - 3 - this.minRect.width - this.maxRect.width - this.topRightRect.width - this.chevronRect.width;
                }
                else {
                    final CTabItem cTabItem3 = this.items[this.selectedIndex];
                    int n7 = size.x - n - 3 - this.minRect.width - this.maxRect.width - this.chevronRect.width;
                    if (this.topRightRect.width > 0) {
                        n7 -= this.topRightRect.width + 3;
                    }
                    this.chevronRect.x = Math.min(cTabItem3.x + cTabItem3.width + 3, n7);
                }
                if (n > 0) {
                    final Rectangle chevronRect5 = this.chevronRect;
                    ++chevronRect5.x;
                }
            }
        }
        else if (this.showChevron) {
            this.chevronRect.width = computeSize4.x;
            this.chevronRect.height = computeSize4.y;
            int n8;
            int n9;
            for (n8 = 0, n9 = -1; n8 < this.priority.length && this.items[this.priority[n8]].showing; n9 = Math.max(n9, this.priority[n8++])) {}
            if (n9 == -1) {
                n9 = this.firstIndex;
            }
            final CTabItem cTabItem4 = this.items[n9];
            int n10 = cTabItem4.x + cTabItem4.width + 3;
            if (!this.simple && n9 == this.selectedIndex) {
                n10 -= this.renderer.curveIndent;
            }
            this.chevronRect.x = Math.min(n10, this.getRightItemEdge(gc));
            this.chevronRect.y = (this.onBottom ? (size.y - n3 - this.tabHeight + (this.tabHeight - this.chevronRect.height) / 2) : (n4 + (this.tabHeight - this.chevronRect.height) / 2));
        }
        if (x5 != this.chevronRect.x || width4 != this.chevronRect.width || y4 != this.chevronRect.y || height4 != this.chevronRect.height) {
            final int min4 = Math.min(x5, this.chevronRect.x);
            this.redraw(min4, this.onBottom ? (size.y - n3 - this.tabHeight) : (n4 + 1), Math.max(x5 + width4, this.chevronRect.x + this.chevronRect.width) - min4, this.tabHeight, false);
        }
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        if (font != null && font.equals(this.getFont())) {
            return;
        }
        super.setFont(font);
        this.oldFont = this.getFont();
        if (!this.updateTabHeight(false)) {
            this.updateItems();
            this.redraw();
        }
    }
    
    public void setForeground(final Color foreground) {
        super.setForeground(foreground);
        this.redraw();
    }
    
    public void setInsertMark(final CTabItem cTabItem, final boolean b) {
        this.checkWidget();
    }
    
    public void setInsertMark(final int n, final boolean b) {
        this.checkWidget();
        if (n < -1 || n >= this.getItemCount()) {
            SWT.error(5);
        }
    }
    
    boolean setItemLocation(final GC gc) {
        boolean b = false;
        if (this.items.length == 0) {
            return false;
        }
        final Rectangle computeTrim = this.renderer.computeTrim(-3, 0, 0, 0, 0, 0);
        final int x = -computeTrim.x;
        final int n = computeTrim.height + computeTrim.y;
        final int n2 = -computeTrim.y;
        final Point size = this.getSize();
        final int n3 = this.onBottom ? Math.max(n, size.y - n - this.tabHeight) : n2;
        final Point computeSize = this.renderer.computeSize(-8, 0, gc, -1, -1);
        if (this.single) {
            final int x2 = this.getDisplay().getBounds().width + 10;
            for (int i = 0; i < this.items.length; ++i) {
                final CTabItem cTabItem = this.items[i];
                if (i == this.selectedIndex) {
                    this.firstIndex = this.selectedIndex;
                    final int x3 = cTabItem.x;
                    final int y = cTabItem.y;
                    cTabItem.x = x;
                    cTabItem.y = n3;
                    cTabItem.showing = true;
                    if (this.showClose || cTabItem.showClose) {
                        cTabItem.closeRect.x = x - this.renderer.computeTrim(i, 0, 0, 0, 0, 0).x;
                        cTabItem.closeRect.y = (this.onBottom ? (size.y - n - this.tabHeight + (this.tabHeight - computeSize.y) / 2) : (n2 + (this.tabHeight - computeSize.y) / 2));
                    }
                    if (cTabItem.x != x3 || cTabItem.y != y) {
                        b = true;
                    }
                }
                else {
                    cTabItem.x = x2;
                    cTabItem.showing = false;
                }
            }
        }
        else {
            final int n4 = this.getRightItemEdge(gc) - x;
            int n5 = 0;
            for (int j = 0; j < this.priority.length; ++j) {
                final CTabItem cTabItem2 = this.items[this.priority[j]];
                n5 += cTabItem2.width;
                cTabItem2.showing = (j == 0 || (cTabItem2.width > 0 && n5 <= n4));
            }
            int x4 = -this.renderer.computeTrim(-2, 0, 0, 0, 0, 0).x;
            final int x5 = this.getDisplay().getBounds().width + 10;
            this.firstIndex = this.items.length - 1;
            for (int k = 0; k < this.items.length; ++k) {
                final CTabItem cTabItem3 = this.items[k];
                if (!cTabItem3.showing) {
                    if (cTabItem3.x != x5) {
                        b = true;
                    }
                    cTabItem3.x = x5;
                }
                else {
                    this.firstIndex = Math.min(this.firstIndex, k);
                    if (cTabItem3.x != x4 || cTabItem3.y != n3) {
                        b = true;
                    }
                    cTabItem3.x = x4;
                    cTabItem3.y = n3;
                    int n6 = 0;
                    if (k == this.selectedIndex) {
                        n6 |= 0x2;
                    }
                    final Rectangle computeTrim2 = this.renderer.computeTrim(k, n6, 0, 0, 0, 0);
                    cTabItem3.closeRect.x = cTabItem3.x + cTabItem3.width - (computeTrim2.width + computeTrim2.x) - computeSize.x;
                    cTabItem3.closeRect.y = (this.onBottom ? (size.y - n - this.tabHeight + (this.tabHeight - computeSize.y) / 2) : (n2 + (this.tabHeight - computeSize.y) / 2));
                    x4 += cTabItem3.width;
                    if (!this.simple && k == this.selectedIndex) {
                        x4 -= this.renderer.curveIndent;
                    }
                }
            }
        }
        return b;
    }
    
    boolean setItemSize(final GC gc) {
        boolean b = false;
        if (this.isDisposed()) {
            return b;
        }
        final Point size = this.getSize();
        if (size.x <= 0 || size.y <= 0) {
            return b;
        }
        final Rectangle computeTrim = this.renderer.computeTrim(-3, 0, 0, 0, 0, 0);
        final int n = computeTrim.width + computeTrim.x;
        final int n2 = -computeTrim.x;
        this.showChevron = false;
        if (this.single) {
            this.showChevron = true;
            if (this.selectedIndex != -1) {
                final CTabItem cTabItem = this.items[this.selectedIndex];
                final int min = Math.min(this.renderer.computeSize(this.selectedIndex, 2, gc, -1, -1).x, this.getRightItemEdge(gc) - n2);
                if (cTabItem.height != this.tabHeight || cTabItem.width != min) {
                    b = true;
                    cTabItem.shortenedText = null;
                    cTabItem.shortenedTextWidth = 0;
                    cTabItem.height = this.tabHeight;
                    cTabItem.width = min;
                    final Rectangle closeRect = cTabItem.closeRect;
                    final Rectangle closeRect2 = cTabItem.closeRect;
                    final boolean b2 = false;
                    closeRect2.height = (b2 ? 1 : 0);
                    closeRect.width = (b2 ? 1 : 0);
                    if (this.showClose || cTabItem.showClose) {
                        final Point computeSize = this.renderer.computeSize(-8, 2, gc, -1, -1);
                        cTabItem.closeRect.width = computeSize.x;
                        cTabItem.closeRect.height = computeSize.y;
                    }
                }
            }
            return b;
        }
        if (this.items.length == 0) {
            return b;
        }
        int n3 = size.x - n2 - n - 3;
        if (this.showMin) {
            n3 -= this.renderer.computeSize(-6, 0, gc, -1, -1).x;
        }
        if (this.showMax) {
            n3 -= this.renderer.computeSize(-5, 0, gc, -1, -1).x;
        }
        if (this.topRightAlignment == 131072 && this.topRight != null) {
            n3 -= this.topRight.computeSize(-1, -1, false).x + 3;
        }
        int max = Math.max(0, n3);
        int n4 = 0;
        final int[] array = new int[this.items.length];
        for (int i = 0; i < this.priority.length; ++i) {
            final int n5 = this.priority[i];
            int n6 = 16777216;
            if (n5 == this.selectedIndex) {
                n6 |= 0x2;
            }
            array[n5] = this.renderer.computeSize(n5, n6, gc, -1, -1).x;
            n4 += array[n5];
            if (n4 > max) {
                break;
            }
        }
        int[] array2;
        if (n4 > max) {
            this.showChevron = (this.items.length > 1);
            if (this.showChevron) {
                max -= this.renderer.computeSize(-7, 0, gc, -1, -1).x;
            }
            array2 = array;
            final int n7 = (this.selectedIndex != -1) ? this.selectedIndex : 0;
            if (max < array2[n7]) {
                array2[n7] = Math.max(0, max);
            }
        }
        else {
            int n8 = 0;
            final int[] array3 = new int[this.items.length];
            for (int j = 0; j < this.items.length; ++j) {
                int n9 = 0;
                if (j == this.selectedIndex) {
                    n9 |= 0x2;
                }
                array3[j] = this.renderer.computeSize(j, n9, gc, -1, -1).x;
                n8 += array3[j];
            }
            if (n8 <= max) {
                array2 = array3;
            }
            else {
                int n10 = (max - n4) / this.items.length;
                while (true) {
                    int n11 = 0;
                    int n12 = 0;
                    for (int k = 0; k < this.items.length; ++k) {
                        if (array3[k] > array[k] + n10) {
                            n12 += array[k] + n10;
                            ++n11;
                        }
                        else {
                            n12 += array3[k];
                        }
                    }
                    if (n12 >= max) {
                        --n10;
                        break;
                    }
                    if (n11 == 0) {
                        break;
                    }
                    if (max - n12 < n11) {
                        break;
                    }
                    ++n10;
                }
                array2 = new int[this.items.length];
                for (int l = 0; l < this.items.length; ++l) {
                    array2[l] = Math.min(array3[l], array[l] + n10);
                }
            }
        }
        for (int n13 = 0; n13 < this.items.length; ++n13) {
            final CTabItem cTabItem2 = this.items[n13];
            final int width = array2[n13];
            if (cTabItem2.height != this.tabHeight || cTabItem2.width != width) {
                b = true;
                cTabItem2.shortenedText = null;
                cTabItem2.shortenedTextWidth = 0;
                cTabItem2.height = this.tabHeight;
                cTabItem2.width = width;
                final Rectangle closeRect3 = cTabItem2.closeRect;
                final Rectangle closeRect4 = cTabItem2.closeRect;
                final boolean b3 = false;
                closeRect4.height = (b3 ? 1 : 0);
                closeRect3.width = (b3 ? 1 : 0);
                if ((this.showClose || cTabItem2.showClose) && (n13 == this.selectedIndex || this.showUnselectedClose)) {
                    final Point computeSize2 = this.renderer.computeSize(-8, 0, gc, -1, -1);
                    cTabItem2.closeRect.width = computeSize2.x;
                    cTabItem2.closeRect.height = computeSize2.y;
                }
            }
        }
        return b;
    }
    
    public void setMaximizeVisible(final boolean showMax) {
        this.checkWidget();
        if (this.showMax == showMax) {
            return;
        }
        this.showMax = showMax;
        this.updateItems();
        this.redraw();
    }
    
    public void setLayout(final Layout layout) {
        this.checkWidget();
    }
    
    public void setMaximized(final boolean maximized) {
        this.checkWidget();
        if (this.maximized == maximized) {
            return;
        }
        if (maximized && this.minimized) {
            this.setMinimized(false);
        }
        this.maximized = maximized;
        this.redraw(this.maxRect.x, this.maxRect.y, this.maxRect.width, this.maxRect.height, false);
    }
    
    public void setMinimizeVisible(final boolean showMin) {
        this.checkWidget();
        if (this.showMin == showMin) {
            return;
        }
        this.showMin = showMin;
        this.updateItems();
        this.redraw();
    }
    
    public void setMinimized(final boolean minimized) {
        this.checkWidget();
        if (this.minimized == minimized) {
            return;
        }
        if (minimized && this.maximized) {
            this.setMaximized(false);
        }
        this.minimized = minimized;
        this.redraw(this.minRect.x, this.minRect.y, this.minRect.width, this.minRect.height, false);
    }
    
    public void setMinimumCharacters(final int minChars) {
        this.checkWidget();
        if (minChars < 0) {
            SWT.error(6);
        }
        if (this.minChars == minChars) {
            return;
        }
        this.minChars = minChars;
        if (this.updateItems()) {
            this.redrawTabs();
        }
    }
    
    public void setMRUVisible(final boolean mru) {
        this.checkWidget();
        if (this.mru == mru) {
            return;
        }
        if (!(this.mru = mru)) {
            final int firstIndex = this.firstIndex;
            int n = 0;
            for (int i = this.firstIndex; i < this.items.length; ++i) {
                this.priority[n++] = i;
            }
            for (int j = 0; j < firstIndex; ++j) {
                this.priority[n++] = j;
            }
            if (this.updateItems()) {
                this.redrawTabs();
            }
        }
    }
    
    public void setRenderer(CTabFolderRenderer renderer) {
        this.checkWidget();
        if (this.renderer == renderer) {
            return;
        }
        if (this.renderer != null) {
            this.renderer.dispose();
        }
        if (renderer == null) {
            renderer = new CTabFolderRenderer(this);
        }
        this.renderer = renderer;
        this.updateTabHeight(false);
        final Rectangle clientArea = this.getClientArea();
        this.updateItems();
        if (!clientArea.equals(this.getClientArea())) {
            this.notifyListeners(11, new Event());
        }
        this.redraw();
    }
    
    public void setSelection(final CTabItem cTabItem) {
        this.checkWidget();
        if (cTabItem == null) {
            SWT.error(4);
        }
        this.setSelection(this.indexOf(cTabItem));
    }
    
    public void setSelection(final int selectedIndex) {
        this.checkWidget();
        if (selectedIndex < 0 || selectedIndex >= this.items.length) {
            return;
        }
        final CTabItem cTabItem = this.items[selectedIndex];
        if (this.selectedIndex == selectedIndex) {
            this.showItem(cTabItem);
            return;
        }
        final int selectedIndex2 = this.selectedIndex;
        this.selectedIndex = selectedIndex;
        if (selectedIndex2 != -1) {
            this.items[selectedIndex2].closeImageState = 8;
            final CTabItem cTabItem2 = this.items[selectedIndex2];
            cTabItem2.state &= 0xFFFFFFFD;
        }
        cTabItem.closeImageState = 0;
        cTabItem.showing = false;
        final CTabItem cTabItem3 = cTabItem;
        cTabItem3.state |= 0x2;
        final Control control = cTabItem.control;
        Control control2 = null;
        if (selectedIndex2 != -1) {
            control2 = this.items[selectedIndex2].control;
        }
        if (control != control2) {
            if (control != null && !control.isDisposed()) {
                control.setBounds(this.getClientArea());
                control.setVisible(true);
            }
            if (control2 != null && !control2.isDisposed()) {
                control2.setVisible(false);
            }
        }
        this.showItem(cTabItem);
        this.redraw();
    }
    
    void setSelection(final int selection, final boolean b) {
        final int selectedIndex = this.selectedIndex;
        this.setSelection(selection);
        if (b && this.selectedIndex != selectedIndex && this.selectedIndex != -1) {
            final Event event = new Event();
            event.item = this.getItem(this.selectedIndex);
            this.notifyListeners(13, event);
        }
    }
    
    public void setSelectionBackground(Color systemColor) {
        this.checkWidget();
        this.setSelectionHighlightGradientColor(null);
        if (this.selectionBackground == systemColor) {
            return;
        }
        if (systemColor == null) {
            systemColor = this.getDisplay().getSystemColor(25);
        }
        this.selectionBackground = systemColor;
        this.renderer.createAntialiasColors();
        if (this.selectedIndex > -1) {
            this.redraw();
        }
    }
    
    public void setSelectionBackground(final Color[] array, final int[] array2) {
        this.setSelectionBackground(array, array2, false);
    }
    
    public void setSelectionBackground(Color[] array, int[] array2, final boolean selectionGradientVertical) {
        this.checkWidget();
        Color selectionHighlightGradientColor = null;
        int n;
        if (array != null) {
            if (array2 == null || (array2.length != array.length - 1 && array2.length != array.length - 2)) {
                SWT.error(5);
            }
            for (int i = 0; i < array2.length; ++i) {
                if (array2[i] < 0 || array2[i] > 100) {
                    SWT.error(5);
                }
                if (i > 0 && array2[i] < array2[i - 1]) {
                    SWT.error(5);
                }
            }
            if (array2.length == array.length - 2) {
                selectionHighlightGradientColor = array[array.length - 1];
                n = array.length - 1;
            }
            else {
                n = array.length;
            }
            if (this.getDisplay().getDepth() < 15) {
                array = new Color[] { array[n - 1] };
                n = array.length;
                array2 = new int[0];
            }
        }
        else {
            n = 0;
        }
        if (this.selectionBgImage == null) {
            if (this.selectionGradientColors != null && array != null && this.selectionGradientColors.length == n) {
                boolean equals = false;
                for (int j = 0; j < this.selectionGradientColors.length; ++j) {
                    if (this.selectionGradientColors[j] == null) {
                        equals = (array[j] == null);
                    }
                    else {
                        equals = this.selectionGradientColors[j].equals(array[j]);
                    }
                    if (!equals) {
                        break;
                    }
                }
                if (equals) {
                    for (int k = 0; k < this.selectionGradientPercents.length; ++k) {
                        equals = (this.selectionGradientPercents[k] == array2[k]);
                        if (!equals) {
                            break;
                        }
                    }
                }
                if (equals && this.selectionGradientVertical == selectionGradientVertical) {
                    return;
                }
            }
        }
        else {
            this.selectionBgImage = null;
        }
        if (array == null) {
            this.selectionGradientColors = null;
            this.selectionGradientPercents = null;
            this.selectionGradientVertical = false;
            this.setSelectionBackground((Color)null);
            this.setSelectionHighlightGradientColor(null);
        }
        else {
            this.selectionGradientColors = new Color[n];
            for (int l = 0; l < n; ++l) {
                this.selectionGradientColors[l] = array[l];
            }
            this.selectionGradientPercents = new int[array2.length];
            for (int n2 = 0; n2 < array2.length; ++n2) {
                this.selectionGradientPercents[n2] = array2[n2];
            }
            this.selectionGradientVertical = selectionGradientVertical;
            this.setSelectionBackground(this.selectionGradientColors[this.selectionGradientColors.length - 1]);
            this.setSelectionHighlightGradientColor(selectionHighlightGradientColor);
        }
        if (this.selectedIndex > -1) {
            this.redraw();
        }
    }
    
    void setSelectionHighlightGradientColor(final Color selectionHighlightGradientColor) {
        this.renderer.setSelectionHighlightGradientColor(selectionHighlightGradientColor);
    }
    
    public void setSelectionBackground(final Image selectionBgImage) {
        this.checkWidget();
        this.setSelectionHighlightGradientColor(null);
        if (selectionBgImage == this.selectionBgImage) {
            return;
        }
        if (selectionBgImage != null) {
            this.selectionGradientColors = null;
            this.selectionGradientPercents = null;
            this.renderer.disposeSelectionHighlightGradientColors();
        }
        this.selectionBgImage = selectionBgImage;
        this.renderer.createAntialiasColors();
        if (this.selectedIndex > -1) {
            this.redraw();
        }
    }
    
    public void setSelectionForeground(Color systemColor) {
        this.checkWidget();
        if (this.selectionForeground == systemColor) {
            return;
        }
        if (systemColor == null) {
            systemColor = this.getDisplay().getSystemColor(24);
        }
        this.selectionForeground = systemColor;
        if (this.selectedIndex > -1) {
            this.redraw();
        }
    }
    
    public void setSimple(final boolean simple) {
        this.checkWidget();
        if (this.simple != simple) {
            this.simple = simple;
            final Rectangle clientArea = this.getClientArea();
            this.updateItems();
            if (!clientArea.equals(this.getClientArea())) {
                this.notifyListeners(11, new Event());
            }
            this.redraw();
        }
    }
    
    public void setSingle(final boolean single) {
        this.checkWidget();
        if (this.single != single) {
            if (!(this.single = single)) {
                for (int i = 0; i < this.items.length; ++i) {
                    if (i != this.selectedIndex && this.items[i].closeImageState == 0) {
                        this.items[i].closeImageState = 8;
                    }
                }
            }
            final Rectangle clientArea = this.getClientArea();
            this.updateItems();
            if (!clientArea.equals(this.getClientArea())) {
                this.notifyListeners(11, new Event());
            }
            this.redraw();
        }
    }
    
    public void setTabHeight(final int fixedTabHeight) {
        this.checkWidget();
        if (fixedTabHeight < -1) {
            SWT.error(5);
        }
        this.fixedTabHeight = fixedTabHeight;
        this.updateTabHeight(false);
    }
    
    public void setTabPosition(final int n) {
        this.checkWidget();
        if (n != 128 && n != 1024) {
            SWT.error(5);
        }
        if (this.onBottom != (n == 1024)) {
            this.onBottom = (n == 1024);
            this.updateTabHeight(true);
            final Rectangle clientArea = this.getClientArea();
            this.updateItems();
            if (!clientArea.equals(this.getClientArea())) {
                this.notifyListeners(11, new Event());
            }
            this.redraw();
        }
    }
    
    public void setTopRight(final Control control) {
        this.setTopRight(control, 131072);
    }
    
    public void setTopRight(final Control topRight, final int topRightAlignment) {
        this.checkWidget();
        if (topRightAlignment != 131072 && topRightAlignment != 4) {
            SWT.error(5);
        }
        if (topRight != null && topRight.getParent() != this) {
            SWT.error(5);
        }
        this.topRight = topRight;
        this.topRightAlignment = topRightAlignment;
        if (this.updateItems()) {
            this.redraw();
        }
    }
    
    public void setUnselectedCloseVisible(final boolean showUnselectedClose) {
        this.checkWidget();
        if (this.showUnselectedClose == showUnselectedClose) {
            return;
        }
        this.showUnselectedClose = showUnselectedClose;
        this.updateItems();
        this.redraw();
    }
    
    public void setUnselectedImageVisible(final boolean showUnselectedImage) {
        this.checkWidget();
        if (this.showUnselectedImage == showUnselectedImage) {
            return;
        }
        this.showUnselectedImage = showUnselectedImage;
        this.updateItems();
        this.redraw();
    }
    
    public void showItem(final CTabItem cTabItem) {
        this.checkWidget();
        if (cTabItem == null) {
            SWT.error(4);
        }
        if (cTabItem.isDisposed()) {
            SWT.error(5);
        }
        final int index = this.indexOf(cTabItem);
        if (index == -1) {
            SWT.error(5);
        }
        int n = -1;
        for (int i = 0; i < this.priority.length; ++i) {
            if (this.priority[i] == index) {
                n = i;
                break;
            }
        }
        if (this.mru) {
            final int[] priority = new int[this.priority.length];
            System.arraycopy(this.priority, 0, priority, 1, n);
            System.arraycopy(this.priority, n + 1, priority, n + 1, this.priority.length - n - 1);
            priority[0] = index;
            this.priority = priority;
        }
        if (cTabItem.isShowing()) {
            return;
        }
        this.updateItems(index);
        this.redrawTabs();
    }
    
    void showList(final Rectangle rectangle) {
        if (this.items.length == 0 || !this.showChevron) {
            return;
        }
        if (this.showMenu == null || this.showMenu.isDisposed()) {
            this.showMenu = new Menu(this.getShell(), this.getStyle() & 0x6000000);
        }
        else {
            final MenuItem[] items = this.showMenu.getItems();
            for (int i = 0; i < items.length; ++i) {
                items[i].dispose();
            }
        }
        for (int j = 0; j < this.items.length; ++j) {
            final CTabItem cTabItem = this.items[j];
            if (!cTabItem.showing) {
                final MenuItem menuItem = new MenuItem(this.showMenu, 0);
                menuItem.setText(cTabItem.getText());
                menuItem.setImage(cTabItem.getImage());
                menuItem.setData("CTabFolder_showList_Index", cTabItem);
                menuItem.addSelectionListener(new SelectionAdapter() {
                    public void widgetSelected(final SelectionEvent selectionEvent) {
                        CTabFolder.this.setSelection(CTabFolder.this.indexOf((CTabItem)((MenuItem)selectionEvent.widget).getData("CTabFolder_showList_Index")), true);
                    }
                });
            }
        }
        final Point map = this.getDisplay().map(this, null, rectangle.x, rectangle.y + rectangle.height);
        this.showMenu.setLocation(map.x, map.y);
        this.showMenu.setVisible(true);
    }
    
    public void showSelection() {
        this.checkWidget();
        if (this.selectedIndex != -1) {
            this.showItem(this.getSelection());
        }
    }
    
    void _setToolTipText(final int n, final int n2) {
        final String toolTipText = this.getToolTipText();
        final String getToolTip = this._getToolTip(n, n2);
        if (getToolTip == null || !getToolTip.equals(toolTipText)) {
            this.setToolTipText(getToolTip);
        }
    }
    
    boolean updateItems() {
        return this.updateItems(this.selectedIndex);
    }
    
    boolean updateItems(final int n) {
        final GC buttonBounds = new GC(this);
        if (!this.single && !this.mru && n != -1) {
            int n2;
            if (this.priority[0] < (n2 = n)) {
                final int n3 = this.getRightItemEdge(buttonBounds) - -this.renderer.computeTrim(-3, 0, 0, 0, 0, 0).x;
                int n4 = 0;
                final int[] array = new int[this.items.length];
                for (int i = this.priority[0]; i <= n; ++i) {
                    int n5 = 16777216;
                    if (i == this.selectedIndex) {
                        n5 |= 0x2;
                    }
                    array[i] = this.renderer.computeSize(i, n5, buttonBounds, -1, -1).x;
                    n4 += array[i];
                    if (n4 > n3) {
                        break;
                    }
                }
                if (n4 > n3) {
                    int n6 = 0;
                    for (int j = n; j >= 0; --j) {
                        int n7 = 16777216;
                        if (j == this.selectedIndex) {
                            n7 |= 0x2;
                        }
                        if (array[j] == 0) {
                            array[j] = this.renderer.computeSize(j, n7, buttonBounds, -1, -1).x;
                        }
                        n6 += array[j];
                        if (n6 > n3) {
                            break;
                        }
                        n2 = j;
                    }
                }
                else {
                    n2 = this.priority[0];
                    for (int k = n + 1; k < this.items.length; ++k) {
                        int n8 = 16777216;
                        if (k == this.selectedIndex) {
                            n8 |= 0x2;
                        }
                        array[k] = this.renderer.computeSize(k, n8, buttonBounds, -1, -1).x;
                        n4 += array[k];
                        if (n4 >= n3) {
                            break;
                        }
                    }
                    if (n4 < n3) {
                        for (int l = this.priority[0] - 1; l >= 0; --l) {
                            int n9 = 16777216;
                            if (l == this.selectedIndex) {
                                n9 |= 0x2;
                            }
                            if (array[l] == 0) {
                                array[l] = this.renderer.computeSize(l, n9, buttonBounds, -1, -1).x;
                            }
                            n4 += array[l];
                            if (n4 > n3) {
                                break;
                            }
                            n2 = l;
                        }
                    }
                }
            }
            if (n2 != this.priority[0]) {
                int n10 = 0;
                for (int n11 = n2; n11 < this.items.length; ++n11) {
                    this.priority[n10++] = n11;
                }
                for (int n12 = 0; n12 < n2; ++n12) {
                    this.priority[n10++] = n12;
                }
            }
        }
        final boolean showChevron = this.showChevron;
        final boolean b = this.setItemSize(buttonBounds) | this.setItemLocation(buttonBounds);
        this.setButtonBounds(buttonBounds);
        final boolean b2 = b | (this.showChevron ^ showChevron);
        if (b2 && this.getToolTipText() != null) {
            final Point control = this.toControl(this.getDisplay().getCursorLocation());
            this._setToolTipText(control.x, control.y);
        }
        buttonBounds.dispose();
        return b2;
    }
    
    boolean updateTabHeight(final boolean b) {
        final int tabHeight = this.tabHeight;
        final GC gc = new GC(this);
        this.tabHeight = this.renderer.computeSize(-2, 0, gc, -1, -1).y;
        gc.dispose();
        if (!b && this.tabHeight == tabHeight) {
            return false;
        }
        this.oldSize = null;
        this.notifyListeners(11, new Event());
        return true;
    }
    
    String _getToolTip(final int n, final int n2) {
        if (this.showMin && this.minRect.contains(n, n2)) {
            return this.minimized ? SWT.getMessage("SWT_Restore") : SWT.getMessage("SWT_Minimize");
        }
        if (this.showMax && this.maxRect.contains(n, n2)) {
            return this.maximized ? SWT.getMessage("SWT_Restore") : SWT.getMessage("SWT_Maximize");
        }
        if (this.showChevron && this.chevronRect.contains(n, n2)) {
            return SWT.getMessage("SWT_ShowList");
        }
        final CTabItem item = this.getItem(new Point(n, n2));
        if (item == null) {
            return null;
        }
        if (!item.showing) {
            return null;
        }
        if ((this.showClose || item.showClose) && item.closeRect.contains(n, n2)) {
            return SWT.getMessage("SWT_Close");
        }
        return item.getToolTipText();
    }
}
