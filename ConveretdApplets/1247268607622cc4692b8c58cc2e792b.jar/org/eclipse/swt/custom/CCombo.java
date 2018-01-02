// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.accessibility.AccessibleControlListener;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.accessibility.AccessibleTextListener;
import org.eclipse.swt.accessibility.AccessibleTextEvent;
import org.eclipse.swt.accessibility.AccessibleTextAdapter;
import org.eclipse.swt.accessibility.AccessibleListener;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.widgets.TypedListener;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;

public class CCombo extends Composite
{
    Text text;
    List list;
    int visibleItemCount;
    Shell popup;
    Button arrow;
    boolean hasFocus;
    Listener listener;
    Listener filter;
    Color foreground;
    Color background;
    Font font;
    Shell _shell;
    static final String PACKAGE_PREFIX = "org.eclipse.swt.custom.";
    
    public CCombo(final Composite composite, int checkStyle) {
        super(composite, checkStyle = checkStyle(checkStyle));
        this.visibleItemCount = 5;
        this._shell = super.getShell();
        int n = 4;
        if ((checkStyle & 0x8) != 0x0) {
            n |= 0x8;
        }
        if ((checkStyle & 0x800000) != 0x0) {
            n |= 0x800000;
        }
        this.text = new Text(this, n);
        int n2 = 1028;
        if ((checkStyle & 0x800000) != 0x0) {
            n2 |= 0x800000;
        }
        this.arrow = new Button(this, n2);
        this.listener = new Listener() {
            final /* synthetic */ CCombo this$0;
            
            public void handleEvent(final Event event) {
                if (CCombo.this.isDisposed()) {
                    return;
                }
                if (CCombo.this.popup == event.widget) {
                    CCombo.this.popupEvent(event);
                    return;
                }
                if (CCombo.this.text == event.widget) {
                    CCombo.this.textEvent(event);
                    return;
                }
                if (CCombo.this.list == event.widget) {
                    CCombo.this.listEvent(event);
                    return;
                }
                if (CCombo.this.arrow == event.widget) {
                    CCombo.this.arrowEvent(event);
                    return;
                }
                if (CCombo.this == event.widget) {
                    CCombo.this.comboEvent(event);
                    return;
                }
                if (CCombo.this.getShell() == event.widget) {
                    CCombo.this.getDisplay().asyncExec(new Runnable() {
                        final /* synthetic */ CCombo$1 this$1 = this$1;
                        
                        public void run() {
                            if (this.this$1.this$0.isDisposed()) {
                                return;
                            }
                            this.this$1.this$0.handleFocus(16);
                        }
                    });
                }
            }
        };
        this.filter = new Listener() {
            public void handleEvent(final Event event) {
                if (CCombo.this.isDisposed()) {
                    return;
                }
                if (event.type == 13) {
                    if (event.widget instanceof ScrollBar) {
                        CCombo.this.handleScroll(event);
                    }
                    return;
                }
                if (((Control)event.widget).getShell() == CCombo.this.getShell()) {
                    CCombo.this.handleFocus(16);
                }
            }
        };
        final int[] array = { 12, 15, 10, 11 };
        for (int i = 0; i < array.length; ++i) {
            this.addListener(array[i], this.listener);
        }
        final int[] array2 = { 14, 29, 1, 2, 35, 24, 3, 4, 8, 6, 7, 32, 5, 37, 31, 15, 25 };
        for (int j = 0; j < array2.length; ++j) {
            this.text.addListener(array2[j], this.listener);
        }
        final int[] array3 = { 29, 3, 6, 7, 32, 5, 4, 37, 13, 15 };
        for (int k = 0; k < array3.length; ++k) {
            this.arrow.addListener(array3[k], this.listener);
        }
        this.createPopup(null, -1);
        this.initAccessible();
    }
    
    static int checkStyle(final int n) {
        return 0x80000 | (n & 0x6800808);
    }
    
    public void add(final String s) {
        this.checkWidget();
        if (s == null) {
            SWT.error(4);
        }
        this.list.add(s);
    }
    
    public void add(final String s, final int n) {
        this.checkWidget();
        if (s == null) {
            SWT.error(4);
        }
        this.list.add(s, n);
    }
    
    public void addModifyListener(final ModifyListener modifyListener) {
        this.checkWidget();
        if (modifyListener == null) {
            SWT.error(4);
        }
        this.addListener(24, new TypedListener(modifyListener));
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
    
    public void addVerifyListener(final VerifyListener verifyListener) {
        this.checkWidget();
        if (verifyListener == null) {
            SWT.error(4);
        }
        this.addListener(25, new TypedListener(verifyListener));
    }
    
    void arrowEvent(final Event event) {
        switch (event.type) {
            case 15: {
                this.handleFocus(15);
                break;
            }
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 29:
            case 32: {
                final Point map = this.getDisplay().map(this.arrow, this, event.x, event.y);
                event.x = map.x;
                event.y = map.y;
                this.notifyListeners(event.type, event);
                event.type = 0;
                break;
            }
            case 37: {
                final Point map2 = this.getDisplay().map(this.arrow, this, event.x, event.y);
                event.x = map2.x;
                event.y = map2.y;
                this.notifyListeners(37, event);
                event.type = 0;
                if (this.isDisposed()) {
                    break;
                }
                if (!event.doit) {
                    break;
                }
                if (event.count == 0) {
                    break;
                }
                event.doit = false;
                final int selectionIndex = this.getSelectionIndex();
                if (event.count > 0) {
                    this.select(Math.max(selectionIndex - 1, 0));
                }
                else {
                    this.select(Math.min(selectionIndex + 1, this.getItemCount() - 1));
                }
                if (selectionIndex != this.getSelectionIndex()) {
                    final Event event2 = new Event();
                    event2.time = event.time;
                    event2.stateMask = event.stateMask;
                    this.notifyListeners(13, event2);
                }
                if (this.isDisposed()) {
                    break;
                }
                break;
            }
            case 13: {
                this.text.setFocus();
                this.dropDown(!this.isDropped());
                break;
            }
        }
    }
    
    protected void checkSubclass() {
        final String name = this.getClass().getName();
        if (!name.substring(0, name.lastIndexOf(46) + 1).equals("org.eclipse.swt.custom.")) {
            SWT.error(43);
        }
    }
    
    public void clearSelection() {
        this.checkWidget();
        this.text.clearSelection();
        this.list.deselectAll();
    }
    
    void comboEvent(final Event event) {
        switch (event.type) {
            case 12: {
                this.removeListener(12, this.listener);
                this.notifyListeners(12, event);
                event.type = 0;
                if (this.popup != null && !this.popup.isDisposed()) {
                    this.list.removeListener(12, this.listener);
                    this.popup.dispose();
                }
                this.getShell().removeListener(27, this.listener);
                this.getDisplay().removeFilter(15, this.filter);
                this.popup = null;
                this.text = null;
                this.list = null;
                this.arrow = null;
                this._shell = null;
                break;
            }
            case 15: {
                final Control focusControl = this.getDisplay().getFocusControl();
                if (focusControl == this.arrow || focusControl == this.list) {
                    return;
                }
                if (this.isDropped()) {
                    this.list.setFocus();
                    break;
                }
                this.text.setFocus();
                break;
            }
            case 10: {
                this.dropDown(false);
                break;
            }
            case 11: {
                this.internalLayout(false);
                break;
            }
        }
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        final String[] items = this.list.getItems();
        final GC gc = new GC(this.text);
        final int x = gc.stringExtent(" ").x;
        int n3 = gc.stringExtent(this.text.getText()).x;
        for (int i = 0; i < items.length; ++i) {
            n3 = Math.max(gc.stringExtent(items[i]).x, n3);
        }
        gc.dispose();
        final Point computeSize = this.text.computeSize(-1, -1, b);
        final Point computeSize2 = this.arrow.computeSize(-1, -1, b);
        final Point computeSize3 = this.list.computeSize(-1, -1, b);
        final int borderWidth = this.getBorderWidth();
        int max = Math.max(computeSize.y, computeSize2.y);
        int max2 = Math.max(n3 + 2 * x + computeSize2.x + 2 * borderWidth, computeSize3.x);
        if (n != -1) {
            max2 = n;
        }
        if (n2 != -1) {
            max = n2;
        }
        return new Point(max2 + 2 * borderWidth, max + 2 * borderWidth);
    }
    
    public void copy() {
        this.checkWidget();
        this.text.copy();
    }
    
    void createPopup(final String[] items, final int selection) {
        this.popup = new Shell(this.getShell(), 16392);
        final int style = this.getStyle();
        int n = 516;
        if ((style & 0x800000) != 0x0) {
            n |= 0x800000;
        }
        if ((style & 0x4000000) != 0x0) {
            n |= 0x4000000;
        }
        if ((style & 0x2000000) != 0x0) {
            n |= 0x2000000;
        }
        this.list = new List(this.popup, n);
        if (this.font != null) {
            this.list.setFont(this.font);
        }
        if (this.foreground != null) {
            this.list.setForeground(this.foreground);
        }
        if (this.background != null) {
            this.list.setBackground(this.background);
        }
        final int[] array = { 21, 9, 27 };
        for (int i = 0; i < array.length; ++i) {
            this.popup.addListener(array[i], this.listener);
        }
        final int[] array2 = { 4, 13, 31, 1, 2, 15, 12 };
        for (int j = 0; j < array2.length; ++j) {
            this.list.addListener(array2[j], this.listener);
        }
        if (items != null) {
            this.list.setItems(items);
        }
        if (selection != -1) {
            this.list.setSelection(selection);
        }
    }
    
    public void cut() {
        this.checkWidget();
        this.text.cut();
    }
    
    public void deselect(final int n) {
        this.checkWidget();
        if (n >= 0 && n < this.list.getItemCount() && n == this.list.getSelectionIndex() && this.text.getText().equals(this.list.getItem(n))) {
            this.text.setText("");
            this.list.deselect(n);
        }
    }
    
    public void deselectAll() {
        this.checkWidget();
        this.text.setText("");
        this.list.deselectAll();
    }
    
    void dropDown(final boolean b) {
        if (b == this.isDropped()) {
            return;
        }
        final Display display = this.getDisplay();
        if (!b) {
            display.removeFilter(13, this.filter);
            this.popup.setVisible(false);
            if (!this.isDisposed() && this.isFocusControl()) {
                this.text.setFocus();
            }
            return;
        }
        if (!this.isVisible()) {
            return;
        }
        if (this.getShell() != this.popup.getParent()) {
            final String[] items = this.list.getItems();
            final int selectionIndex = this.list.getSelectionIndex();
            this.list.removeListener(12, this.listener);
            this.popup.dispose();
            this.popup = null;
            this.list = null;
            this.createPopup(items, selectionIndex);
        }
        final Point size = this.getSize();
        final int itemCount = this.list.getItemCount();
        final Point computeSize = this.list.computeSize(-1, this.list.getItemHeight() * ((itemCount == 0) ? this.visibleItemCount : Math.min(this.visibleItemCount, itemCount)), false);
        this.list.setBounds(1, 1, Math.max(size.x - 2, computeSize.x), computeSize.y);
        final int selectionIndex2 = this.list.getSelectionIndex();
        if (selectionIndex2 != -1) {
            this.list.setTopIndex(selectionIndex2);
        }
        final Rectangle bounds = this.list.getBounds();
        final Rectangle map = display.map(this.getParent(), null, this.getBounds());
        final Point size2 = this.getSize();
        final Rectangle clientArea = this.getMonitor().getClientArea();
        final int max = Math.max(size2.x, bounds.width + 2);
        final int n = bounds.height + 2;
        int x = map.x;
        int n2 = map.y + size2.y;
        if (n2 + n > clientArea.y + clientArea.height) {
            n2 = map.y - n;
        }
        if (x + max > clientArea.x + clientArea.width) {
            x = clientArea.x + clientArea.width - bounds.width;
        }
        this.popup.setBounds(x, n2, max, n);
        this.popup.setVisible(true);
        if (this.isFocusControl()) {
            this.list.setFocus();
        }
        display.removeFilter(13, this.filter);
        display.addFilter(13, this.filter);
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
    
    String getAssociatedLabel() {
        final Control[] children = this.getParent().getChildren();
        int i = 0;
        while (i < children.length) {
            if (children[i] == this) {
                if (i <= 0) {
                    break;
                }
                final Control control = children[i - 1];
                if (control instanceof Label) {
                    return ((Label)control).getText();
                }
                if (control instanceof CLabel) {
                    return ((CLabel)control).getText();
                }
                break;
            }
            else {
                ++i;
            }
        }
        return null;
    }
    
    public Control[] getChildren() {
        this.checkWidget();
        return new Control[0];
    }
    
    public boolean getEditable() {
        this.checkWidget();
        return this.text.getEditable();
    }
    
    public String getItem(final int n) {
        this.checkWidget();
        return this.list.getItem(n);
    }
    
    public int getItemCount() {
        this.checkWidget();
        return this.list.getItemCount();
    }
    
    public int getItemHeight() {
        this.checkWidget();
        return this.list.getItemHeight();
    }
    
    public String[] getItems() {
        this.checkWidget();
        return this.list.getItems();
    }
    
    public boolean getListVisible() {
        this.checkWidget();
        return this.isDropped();
    }
    
    public Menu getMenu() {
        return this.text.getMenu();
    }
    
    public Point getSelection() {
        this.checkWidget();
        return this.text.getSelection();
    }
    
    public int getSelectionIndex() {
        this.checkWidget();
        return this.list.getSelectionIndex();
    }
    
    public Shell getShell() {
        this.checkWidget();
        final Shell shell = super.getShell();
        if (shell != this._shell) {
            if (this._shell != null && !this._shell.isDisposed()) {
                this._shell.removeListener(27, this.listener);
            }
            this._shell = shell;
        }
        return this._shell;
    }
    
    public int getStyle() {
        int n = super.getStyle() & 0xFFFFFFF7;
        if (!this.text.getEditable()) {
            n |= 0x8;
        }
        return n;
    }
    
    public String getText() {
        this.checkWidget();
        return this.text.getText();
    }
    
    public int getTextHeight() {
        this.checkWidget();
        return this.text.getLineHeight();
    }
    
    public int getTextLimit() {
        this.checkWidget();
        return this.text.getTextLimit();
    }
    
    public int getVisibleItemCount() {
        this.checkWidget();
        return this.visibleItemCount;
    }
    
    void handleFocus(final int n) {
        switch (n) {
            case 15: {
                if (this.hasFocus) {
                    return;
                }
                if (this.getEditable()) {
                    this.text.selectAll();
                }
                this.hasFocus = true;
                final Shell shell = this.getShell();
                shell.removeListener(27, this.listener);
                shell.addListener(27, this.listener);
                final Display display = this.getDisplay();
                display.removeFilter(15, this.filter);
                display.addFilter(15, this.filter);
                this.notifyListeners(15, new Event());
                break;
            }
            case 16: {
                if (!this.hasFocus) {
                    return;
                }
                final Control focusControl = this.getDisplay().getFocusControl();
                if (focusControl == this.arrow || focusControl == this.list || focusControl == this.text) {
                    return;
                }
                this.hasFocus = false;
                this.getShell().removeListener(27, this.listener);
                this.getDisplay().removeFilter(15, this.filter);
                this.notifyListeners(16, new Event());
                break;
            }
        }
    }
    
    void handleScroll(final Event event) {
        final Scrollable parent = ((ScrollBar)event.widget).getParent();
        if (parent.equals(this.list)) {
            return;
        }
        if (this.isParentScrolling(parent)) {
            this.dropDown(false);
        }
    }
    
    public int indexOf(final String s) {
        this.checkWidget();
        if (s == null) {
            SWT.error(4);
        }
        return this.list.indexOf(s);
    }
    
    public int indexOf(final String s, final int n) {
        this.checkWidget();
        if (s == null) {
            SWT.error(4);
        }
        return this.list.indexOf(s, n);
    }
    
    void initAccessible() {
        final AccessibleAdapter accessibleAdapter = new AccessibleAdapter() {
            public void getName(final AccessibleEvent accessibleEvent) {
                String stripMnemonic = null;
                final String associatedLabel = CCombo.this.getAssociatedLabel();
                if (associatedLabel != null) {
                    stripMnemonic = CCombo.this.stripMnemonic(associatedLabel);
                }
                accessibleEvent.result = stripMnemonic;
            }
            
            public void getKeyboardShortcut(final AccessibleEvent accessibleEvent) {
                String string = null;
                final String associatedLabel = CCombo.this.getAssociatedLabel();
                if (associatedLabel != null) {
                    final char findMnemonic = CCombo.this._findMnemonic(associatedLabel);
                    if (findMnemonic != '\0') {
                        string = "Alt+" + findMnemonic;
                    }
                }
                accessibleEvent.result = string;
            }
            
            public void getHelp(final AccessibleEvent accessibleEvent) {
                accessibleEvent.result = CCombo.this.getToolTipText();
            }
        };
        this.getAccessible().addAccessibleListener(accessibleAdapter);
        this.text.getAccessible().addAccessibleListener(accessibleAdapter);
        this.list.getAccessible().addAccessibleListener(accessibleAdapter);
        this.arrow.getAccessible().addAccessibleListener(new AccessibleAdapter() {
            public void getName(final AccessibleEvent accessibleEvent) {
                accessibleEvent.result = (CCombo.this.isDropped() ? SWT.getMessage("SWT_Close") : SWT.getMessage("SWT_Open"));
            }
            
            public void getKeyboardShortcut(final AccessibleEvent accessibleEvent) {
                accessibleEvent.result = "Alt+Down Arrow";
            }
            
            public void getHelp(final AccessibleEvent accessibleEvent) {
                accessibleEvent.result = CCombo.this.getToolTipText();
            }
        });
        this.getAccessible().addAccessibleTextListener(new AccessibleTextAdapter() {
            public void getCaretOffset(final AccessibleTextEvent accessibleTextEvent) {
                accessibleTextEvent.offset = CCombo.this.text.getCaretPosition();
            }
            
            public void getSelectionRange(final AccessibleTextEvent accessibleTextEvent) {
                final Point selection = CCombo.this.text.getSelection();
                accessibleTextEvent.offset = selection.x;
                accessibleTextEvent.length = selection.y - selection.x;
            }
        });
        this.getAccessible().addAccessibleControlListener(new AccessibleControlAdapter() {
            public void getChildAtPoint(final AccessibleControlEvent accessibleControlEvent) {
                if (CCombo.this.getBounds().contains(CCombo.this.toControl(accessibleControlEvent.x, accessibleControlEvent.y))) {
                    accessibleControlEvent.childID = -1;
                }
            }
            
            public void getLocation(final AccessibleControlEvent accessibleControlEvent) {
                final Rectangle bounds = CCombo.this.getBounds();
                final Point display = CCombo.this.getParent().toDisplay(bounds.x, bounds.y);
                accessibleControlEvent.x = display.x;
                accessibleControlEvent.y = display.y;
                accessibleControlEvent.width = bounds.width;
                accessibleControlEvent.height = bounds.height;
            }
            
            public void getChildCount(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.detail = 0;
            }
            
            public void getRole(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.detail = 46;
            }
            
            public void getState(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.detail = 0;
            }
            
            public void getValue(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.result = CCombo.this.getText();
            }
        });
        this.text.getAccessible().addAccessibleControlListener(new AccessibleControlAdapter() {
            public void getRole(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.detail = (CCombo.this.text.getEditable() ? 42 : 41);
            }
        });
        this.arrow.getAccessible().addAccessibleControlListener(new AccessibleControlAdapter() {
            public void getDefaultAction(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.result = (CCombo.this.isDropped() ? SWT.getMessage("SWT_Close") : SWT.getMessage("SWT_Open"));
            }
        });
    }
    
    boolean isDropped() {
        return this.popup.getVisible();
    }
    
    public boolean isFocusControl() {
        this.checkWidget();
        return this.text.isFocusControl() || this.arrow.isFocusControl() || this.list.isFocusControl() || this.popup.isFocusControl() || super.isFocusControl();
    }
    
    boolean isParentScrolling(final Control control) {
        for (Composite composite = this.getParent(); composite != null; composite = composite.getParent()) {
            if (composite.equals(control)) {
                return true;
            }
        }
        return false;
    }
    
    void internalLayout(final boolean b) {
        if (this.isDropped()) {
            this.dropDown(false);
        }
        final Rectangle clientArea = this.getClientArea();
        final int width = clientArea.width;
        final int height = clientArea.height;
        final Point computeSize = this.arrow.computeSize(-1, height, b);
        this.text.setBounds(0, 0, width - computeSize.x, height);
        this.arrow.setBounds(width - computeSize.x, 0, computeSize.x, computeSize.y);
    }
    
    void listEvent(final Event event) {
        switch (event.type) {
            case 12: {
                if (this.getShell() != this.popup.getParent()) {
                    final String[] items = this.list.getItems();
                    final int selectionIndex = this.list.getSelectionIndex();
                    this.popup = null;
                    this.list = null;
                    this.createPopup(items, selectionIndex);
                    break;
                }
                break;
            }
            case 15: {
                this.handleFocus(15);
                break;
            }
            case 4: {
                if (event.button != 1) {
                    return;
                }
                this.dropDown(false);
                break;
            }
            case 13: {
                final int selectionIndex2 = this.list.getSelectionIndex();
                if (selectionIndex2 == -1) {
                    return;
                }
                this.text.setText(this.list.getItem(selectionIndex2));
                this.text.selectAll();
                this.list.setSelection(selectionIndex2);
                final Event event2 = new Event();
                event2.time = event.time;
                event2.stateMask = event.stateMask;
                event2.doit = event.doit;
                this.notifyListeners(13, event2);
                event.doit = event2.doit;
                break;
            }
            case 31: {
                switch (event.detail) {
                    case 2:
                    case 4:
                    case 32:
                    case 64: {
                        event.doit = false;
                        break;
                    }
                    case 8:
                    case 16: {
                        event.doit = this.text.traverse(event.detail);
                        event.detail = 0;
                        if (event.doit) {
                            this.dropDown(false);
                        }
                        return;
                    }
                }
                final Event event3 = new Event();
                event3.time = event.time;
                event3.detail = event.detail;
                event3.doit = event.doit;
                event3.character = event.character;
                event3.keyCode = event.keyCode;
                event3.keyLocation = event.keyLocation;
                this.notifyListeners(31, event3);
                event.doit = event3.doit;
                event.detail = event3.detail;
                break;
            }
            case 2: {
                final Event event4 = new Event();
                event4.time = event.time;
                event4.character = event.character;
                event4.keyCode = event.keyCode;
                event4.keyLocation = event.keyLocation;
                event4.stateMask = event.stateMask;
                this.notifyListeners(2, event4);
                event.doit = event4.doit;
                break;
            }
            case 1: {
                if (event.character == '\u001b') {
                    this.dropDown(false);
                }
                if ((event.stateMask & 0x10000) != 0x0 && (event.keyCode == 16777217 || event.keyCode == 16777218)) {
                    this.dropDown(false);
                }
                if (event.character == '\r') {
                    this.dropDown(false);
                    final Event event5 = new Event();
                    event5.time = event.time;
                    event5.stateMask = event.stateMask;
                    this.notifyListeners(14, event5);
                }
                if (this.isDisposed()) {
                    break;
                }
                final Event event6 = new Event();
                event6.time = event.time;
                event6.character = event.character;
                event6.keyCode = event.keyCode;
                event6.keyLocation = event.keyLocation;
                event6.stateMask = event.stateMask;
                this.notifyListeners(1, event6);
                event.doit = event6.doit;
                break;
            }
        }
    }
    
    public void paste() {
        this.checkWidget();
        this.text.paste();
    }
    
    void popupEvent(final Event event) {
        switch (event.type) {
            case 9: {
                final Rectangle bounds = this.list.getBounds();
                event.gc.setForeground(this.getDisplay().getSystemColor(2));
                event.gc.drawRectangle(0, 0, bounds.width + 1, bounds.height + 1);
                break;
            }
            case 21: {
                this.dropDown(event.doit = false);
                break;
            }
            case 27: {
                if ("carbon".equals(SWT.getPlatform())) {
                    this.dropDown(false);
                    break;
                }
                final Point control = this.arrow.toControl(this.getDisplay().getCursorLocation());
                final Point size = this.arrow.getSize();
                if (!new Rectangle(0, 0, size.x, size.y).contains(control)) {
                    this.dropDown(false);
                    break;
                }
                break;
            }
        }
    }
    
    public void redraw() {
        super.redraw();
        this.text.redraw();
        this.arrow.redraw();
        if (this.popup.isVisible()) {
            this.list.redraw();
        }
    }
    
    public void redraw(final int n, final int n2, final int n3, final int n4, final boolean b) {
        super.redraw(n, n2, n3, n4, true);
    }
    
    public void remove(final int n) {
        this.checkWidget();
        this.list.remove(n);
    }
    
    public void remove(final int n, final int n2) {
        this.checkWidget();
        this.list.remove(n, n2);
    }
    
    public void remove(final String s) {
        this.checkWidget();
        if (s == null) {
            SWT.error(4);
        }
        this.list.remove(s);
    }
    
    public void removeAll() {
        this.checkWidget();
        this.text.setText("");
        this.list.removeAll();
    }
    
    public void removeModifyListener(final ModifyListener modifyListener) {
        this.checkWidget();
        if (modifyListener == null) {
            SWT.error(4);
        }
        this.removeListener(24, modifyListener);
    }
    
    public void removeSelectionListener(final SelectionListener selectionListener) {
        this.checkWidget();
        if (selectionListener == null) {
            SWT.error(4);
        }
        this.removeListener(13, selectionListener);
        this.removeListener(14, selectionListener);
    }
    
    public void removeVerifyListener(final VerifyListener verifyListener) {
        this.checkWidget();
        if (verifyListener == null) {
            SWT.error(4);
        }
        this.removeListener(25, verifyListener);
    }
    
    public void select(final int n) {
        this.checkWidget();
        if (n == -1) {
            this.list.deselectAll();
            this.text.setText("");
            return;
        }
        if (n >= 0 && n < this.list.getItemCount() && n != this.getSelectionIndex()) {
            this.text.setText(this.list.getItem(n));
            this.text.selectAll();
            this.list.select(n);
            this.list.showSelection();
        }
    }
    
    public void setBackground(final Color background) {
        super.setBackground(background);
        this.background = background;
        if (this.text != null) {
            this.text.setBackground(background);
        }
        if (this.list != null) {
            this.list.setBackground(background);
        }
        if (this.arrow != null) {
            this.arrow.setBackground(background);
        }
    }
    
    public void setEditable(final boolean editable) {
        this.checkWidget();
        this.text.setEditable(editable);
    }
    
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        if (this.popup != null) {
            this.popup.setVisible(false);
        }
        if (this.text != null) {
            this.text.setEnabled(enabled);
        }
        if (this.arrow != null) {
            this.arrow.setEnabled(enabled);
        }
    }
    
    public boolean setFocus() {
        this.checkWidget();
        return this.isEnabled() && this.isVisible() && (this.isFocusControl() || this.text.setFocus());
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.font = font;
        this.text.setFont(font);
        this.list.setFont(font);
        this.internalLayout(true);
    }
    
    public void setForeground(final Color foreground) {
        super.setForeground(foreground);
        this.foreground = foreground;
        if (this.text != null) {
            this.text.setForeground(foreground);
        }
        if (this.list != null) {
            this.list.setForeground(foreground);
        }
        if (this.arrow != null) {
            this.arrow.setForeground(foreground);
        }
    }
    
    public void setItem(final int n, final String s) {
        this.checkWidget();
        this.list.setItem(n, s);
    }
    
    public void setItems(final String[] items) {
        this.checkWidget();
        this.list.setItems(items);
        if (!this.text.getEditable()) {
            this.text.setText("");
        }
    }
    
    public void setLayout(final Layout layout) {
        this.checkWidget();
    }
    
    public void setListVisible(final boolean b) {
        this.checkWidget();
        this.dropDown(b);
    }
    
    public void setMenu(final Menu menu) {
        this.text.setMenu(menu);
    }
    
    public void setSelection(final Point point) {
        this.checkWidget();
        if (point == null) {
            SWT.error(4);
        }
        this.text.setSelection(point.x, point.y);
    }
    
    public void setText(final String s) {
        this.checkWidget();
        if (s == null) {
            SWT.error(4);
        }
        final int index = this.list.indexOf(s);
        if (index == -1) {
            this.list.deselectAll();
            this.text.setText(s);
            return;
        }
        this.text.setText(s);
        this.text.selectAll();
        this.list.setSelection(index);
        this.list.showSelection();
    }
    
    public void setTextLimit(final int textLimit) {
        this.checkWidget();
        this.text.setTextLimit(textLimit);
    }
    
    public void setToolTipText(final String toolTipText) {
        this.checkWidget();
        super.setToolTipText(toolTipText);
        this.arrow.setToolTipText(toolTipText);
        this.text.setToolTipText(toolTipText);
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (this.isDisposed()) {
            return;
        }
        if (this.popup == null || this.popup.isDisposed()) {
            return;
        }
        if (!visible) {
            this.popup.setVisible(false);
        }
    }
    
    public void setVisibleItemCount(final int visibleItemCount) {
        this.checkWidget();
        if (visibleItemCount < 0) {
            return;
        }
        this.visibleItemCount = visibleItemCount;
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
    
    void textEvent(final Event event) {
        switch (event.type) {
            case 15: {
                this.handleFocus(15);
                break;
            }
            case 14: {
                this.dropDown(false);
                final Event event2 = new Event();
                event2.time = event.time;
                event2.stateMask = event.stateMask;
                this.notifyListeners(14, event2);
                break;
            }
            case 5:
            case 6:
            case 7:
            case 8:
            case 29:
            case 32: {
                final Point map = this.getDisplay().map(this.text, this, event.x, event.y);
                event.x = map.x;
                event.y = map.y;
                this.notifyListeners(event.type, event);
                event.type = 0;
                break;
            }
            case 1: {
                final Event event3 = new Event();
                event3.time = event.time;
                event3.character = event.character;
                event3.keyCode = event.keyCode;
                event3.keyLocation = event.keyLocation;
                event3.stateMask = event.stateMask;
                this.notifyListeners(1, event3);
                if (this.isDisposed()) {
                    break;
                }
                if (!(event.doit = event3.doit)) {
                    break;
                }
                if (event.keyCode != 16777217 && event.keyCode != 16777218) {
                    break;
                }
                event.doit = false;
                if ((event.stateMask & 0x10000) != 0x0) {
                    final boolean dropped = this.isDropped();
                    this.text.selectAll();
                    if (!dropped) {
                        this.setFocus();
                    }
                    this.dropDown(!dropped);
                    break;
                }
                final int selectionIndex = this.getSelectionIndex();
                if (event.keyCode == 16777217) {
                    this.select(Math.max(selectionIndex - 1, 0));
                }
                else {
                    this.select(Math.min(selectionIndex + 1, this.getItemCount() - 1));
                }
                if (selectionIndex != this.getSelectionIndex()) {
                    final Event event4 = new Event();
                    event4.time = event.time;
                    event4.stateMask = event.stateMask;
                    this.notifyListeners(13, event4);
                }
                if (this.isDisposed()) {
                    break;
                }
                break;
            }
            case 2: {
                final Event event5 = new Event();
                event5.time = event.time;
                event5.character = event.character;
                event5.keyCode = event.keyCode;
                event5.keyLocation = event.keyLocation;
                event5.stateMask = event.stateMask;
                this.notifyListeners(2, event5);
                event.doit = event5.doit;
                break;
            }
            case 35: {
                final Event event6 = new Event();
                event6.time = event.time;
                this.notifyListeners(35, event6);
                break;
            }
            case 24: {
                this.list.deselectAll();
                final Event event7 = new Event();
                event7.time = event.time;
                this.notifyListeners(24, event7);
                break;
            }
            case 3: {
                final Point map2 = this.getDisplay().map(this.text, this, event.x, event.y);
                final Event event8 = new Event();
                event8.button = event.button;
                event8.count = event.count;
                event8.stateMask = event.stateMask;
                event8.time = event.time;
                event8.x = map2.x;
                event8.y = map2.y;
                this.notifyListeners(3, event8);
                if (this.isDisposed()) {
                    break;
                }
                if (!(event.doit = event8.doit)) {
                    break;
                }
                if (event.button != 1) {
                    return;
                }
                if (this.text.getEditable()) {
                    return;
                }
                final boolean dropped2 = this.isDropped();
                this.text.selectAll();
                if (!dropped2) {
                    this.setFocus();
                }
                this.dropDown(!dropped2);
                break;
            }
            case 4: {
                final Point map3 = this.getDisplay().map(this.text, this, event.x, event.y);
                final Event event9 = new Event();
                event9.button = event.button;
                event9.count = event.count;
                event9.stateMask = event.stateMask;
                event9.time = event.time;
                event9.x = map3.x;
                event9.y = map3.y;
                this.notifyListeners(4, event9);
                if (this.isDisposed()) {
                    break;
                }
                if (!(event.doit = event9.doit)) {
                    break;
                }
                if (event.button != 1) {
                    return;
                }
                if (this.text.getEditable()) {
                    return;
                }
                this.text.selectAll();
                break;
            }
            case 37: {
                this.notifyListeners(37, event);
                event.type = 0;
                if (this.isDisposed()) {
                    break;
                }
                if (!event.doit) {
                    break;
                }
                if (event.count == 0) {
                    break;
                }
                event.doit = false;
                final int selectionIndex2 = this.getSelectionIndex();
                if (event.count > 0) {
                    this.select(Math.max(selectionIndex2 - 1, 0));
                }
                else {
                    this.select(Math.min(selectionIndex2 + 1, this.getItemCount() - 1));
                }
                if (selectionIndex2 != this.getSelectionIndex()) {
                    final Event event10 = new Event();
                    event10.time = event.time;
                    event10.stateMask = event.stateMask;
                    this.notifyListeners(13, event10);
                }
                if (this.isDisposed()) {
                    break;
                }
                break;
            }
            case 31: {
                switch (event.detail) {
                    case 32:
                    case 64: {
                        event.doit = false;
                        break;
                    }
                    case 8: {
                        event.doit = this.traverse(8);
                        event.detail = 0;
                        return;
                    }
                }
                final Event event11 = new Event();
                event11.time = event.time;
                event11.detail = event.detail;
                event11.doit = event.doit;
                event11.character = event.character;
                event11.keyCode = event.keyCode;
                event11.keyLocation = event.keyLocation;
                this.notifyListeners(31, event11);
                event.doit = event11.doit;
                event.detail = event11.detail;
                break;
            }
            case 25: {
                final Event event12 = new Event();
                event12.text = event.text;
                event12.start = event.start;
                event12.end = event.end;
                event12.character = event.character;
                event12.keyCode = event.keyCode;
                event12.keyLocation = event.keyLocation;
                event12.stateMask = event.stateMask;
                this.notifyListeners(25, event12);
                event.text = event12.text;
                event.doit = event12.doit;
                break;
            }
        }
    }
    
    public boolean traverse(final int n) {
        if (n == 64 || n == 16) {
            return this.text.traverse(n);
        }
        return super.traverse(n);
    }
}
