// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Rectangle;
import java.awt.Event;

class ListSelection
{
    static final int BEGIN = 0;
    static final int END = 1;
    static int last_x;
    static int last_y;
    
    private static boolean postEvent(final JCListComponent jcListComponent, final int n, final Event event, final int n2) {
        JCListInterface target = (JCListInterface)jcListComponent.scrolled_window;
        if (target == null) {
            target = jcListComponent;
        }
        if (jcListComponent.scrolled_window != null && event != null) {
            event.target = target;
        }
        final int id = (event != null) ? event.id : 0;
        final boolean b = n2 < jcListComponent.selected.length && jcListComponent.selected[n2];
        int n3 = b ? 2 : 1;
        if (n == 1) {
            n3 = 1;
        }
        if (n == 1 && event != null) {
            event.id = (b ? 701 : 702);
        }
        if (jcListComponent.itemListeners.size() == 0) {
            return true;
        }
        if (n == 1 && !b) {
            final JCItemEvent jcItemEvent = new JCItemEvent(target, 701, jcListComponent.getItem(n2), 2);
            for (int i = 0; i < jcListComponent.itemListeners.size(); ++i) {
                ((JCItemListener)jcListComponent.itemListeners.elementAt(i)).itemStateChanged(jcItemEvent);
            }
            return true;
        }
        if (event != null) {
            event.id = id;
        }
        final JCListEvent jcListEvent = new JCListEvent(target, event, n2, jcListComponent.selection_type, n3);
        for (int j = 0; j < jcListComponent.itemListeners.size(); ++j) {
            final JCItemListener jcItemListener = jcListComponent.itemListeners.elementAt(j);
            if (n == 1) {
                jcItemListener.itemStateChanged(jcListEvent);
            }
            if (jcItemListener instanceof JCListListener) {
                if (n == 0) {
                    ((JCListListener)jcItemListener).listItemSelectBegin(jcListEvent);
                    if (!jcListEvent.doit) {
                        return false;
                    }
                }
                else {
                    ((JCListListener)jcItemListener).listItemSelectEnd(jcListEvent);
                }
            }
        }
        return true;
    }
    
    private static void selectRange(final JCListComponent jcListComponent, final int n, final int n2, final boolean b) {
        final int[] array = new int[Math.abs(n2 - n) + 1];
        int n3 = 0;
        synchronized (jcListComponent) {
            for (int i = Math.min(n, n2); i <= Math.max(n, n2); ++i) {
                if (jcListComponent.selected[i] != b) {
                    jcListComponent.selected[i] = b;
                    array[n3] = i;
                    ++n3;
                }
            }
        }
        for (int j = 0; j < n3; ++j) {
            jcListComponent.paintRow(array[j]);
        }
    }
    
    private static void restoreRange(final JCListComponent jcListComponent, final int n, final int n2, final boolean b) {
        final int min = Math.min(n, n2);
        final int max = Math.max(n, n2);
        final int start_item = jcListComponent.start_item;
        final int[] array = new int[max - min + 1];
        int n3 = 0;
        synchronized (jcListComponent) {
            for (int i = min; i <= max; ++i) {
                if (i != start_item || b) {
                    jcListComponent.selected[i] = jcListComponent.last_selected[i];
                    array[n3] = i;
                    ++n3;
                }
            }
        }
        for (int j = 0; j < n3; ++j) {
            jcListComponent.paintRow(array[j]);
        }
    }
    
    private static void arrangeRange(final JCListComponent jcListComponent, final int n) {
        final int start_item = jcListComponent.start_item;
        final int end_item = jcListComponent.end_item;
        final boolean b = jcListComponent.selected[start_item];
        if (start_item < end_item) {
            if (n > end_item) {
                selectRange(jcListComponent, end_item, n, b);
                return;
            }
            if (n < end_item && n >= start_item) {
                if (!b) {
                    restoreRange(jcListComponent, n + 1, end_item, false);
                    return;
                }
                selectRange(jcListComponent, n + 1, end_item, false);
            }
            else if (n <= start_item) {
                if (!b) {
                    restoreRange(jcListComponent, start_item, end_item, false);
                }
                else {
                    selectRange(jcListComponent, start_item, end_item, false);
                }
                selectRange(jcListComponent, n, start_item, b);
            }
        }
        else if (start_item > end_item) {
            if (n <= end_item) {
                selectRange(jcListComponent, n, end_item, b);
                return;
            }
            if (n > end_item && n <= start_item) {
                if (!b) {
                    restoreRange(jcListComponent, end_item, n - 1, false);
                    return;
                }
                selectRange(jcListComponent, end_item, n - 1, false);
            }
            else if (n >= start_item) {
                if (!b) {
                    restoreRange(jcListComponent, end_item, start_item, false);
                }
                else {
                    selectRange(jcListComponent, end_item, start_item, false);
                }
                selectRange(jcListComponent, start_item, n, b);
            }
        }
        else {
            selectRange(jcListComponent, start_item, n, b);
        }
    }
    
    static boolean beginSelect(final JCListComponent jcListComponent, final Event event) {
        if (event.clickCount > 1) {
            return false;
        }
        if (jcListComponent.countItems() == 0) {
            return false;
        }
        final int eventToRow = jcListComponent.eventToRow(event, true);
        jcListComponent.last_row = eventToRow;
        final int start_item = eventToRow;
        if (start_item == -999) {
            return false;
        }
        if (jcListComponent.auto_select && jcListComponent.selected[start_item] && start_item == jcListComponent.focus_row) {
            return true;
        }
        jcListComponent.selection_type = 0;
        if (jcListComponent.multiple_select) {
            if (event.shiftDown()) {
                jcListComponent.selection_type = 1;
            }
            else if (event.controlDown()) {
                jcListComponent.selection_type = 2;
            }
        }
        if (!postEvent(jcListComponent, 0, event, start_item)) {
            return false;
        }
        jcListComponent.did_selection = false;
        boolean b = start_item < jcListComponent.selected.length && jcListComponent.selected[start_item];
        if (!jcListComponent.appending) {
            for (int n = 0; n < jcListComponent.selected.length && n < jcListComponent.countItems(); ++n) {
                if (jcListComponent.selected[n]) {
                    jcListComponent.selected[n] = false;
                    jcListComponent.paintRow(n);
                }
            }
        }
        if (jcListComponent.multiple_select) {
            if (event.shiftDown()) {
                b = jcListComponent.selected[jcListComponent.start_item];
            }
            else if (event.controlDown()) {
                jcListComponent.selected[start_item] = !jcListComponent.selected[start_item];
            }
            else {
                jcListComponent.last_selected[start_item] = jcListComponent.selected[start_item];
                jcListComponent.selected[start_item] = !jcListComponent.selected[start_item];
            }
        }
        else if (start_item < jcListComponent.selected.length) {
            jcListComponent.selected[start_item] = !b;
        }
        jcListComponent.paintRow(start_item);
        jcListComponent.last_selected_item = start_item;
        jcListComponent.old_end_item = jcListComponent.end_item;
        jcListComponent.end_item = start_item;
        if (!jcListComponent.multiple_select || !event.shiftDown()) {
            jcListComponent.old_start_item = jcListComponent.start_item;
            jcListComponent.start_item = start_item;
            if (jcListComponent.multiple_select && !jcListComponent.kbd_select) {
                clickRow(jcListComponent, event);
            }
            return true;
        }
        final int start_item2 = jcListComponent.start_item;
        final int old_end_item = jcListComponent.old_end_item;
        if (start_item2 < old_end_item) {
            if (start_item > old_end_item) {
                selectRange(jcListComponent, old_end_item + 1, start_item, b);
            }
            else if (start_item < old_end_item && start_item >= start_item2) {
                restoreRange(jcListComponent, start_item + 1, old_end_item, false);
            }
            else if (start_item < start_item2) {
                if (b) {
                    selectRange(jcListComponent, start_item2 + 1, old_end_item, false);
                }
                else {
                    restoreRange(jcListComponent, start_item2 + 1, old_end_item, false);
                }
                selectRange(jcListComponent, start_item, start_item2, b);
            }
        }
        else if (start_item2 > old_end_item) {
            if (start_item < old_end_item) {
                selectRange(jcListComponent, start_item, old_end_item + 1, b);
            }
            else if (start_item > old_end_item && start_item <= start_item2) {
                restoreRange(jcListComponent, old_end_item, start_item - 1, false);
            }
            else if (start_item > start_item2) {
                if (b) {
                    selectRange(jcListComponent, old_end_item, start_item2 - 1, false);
                }
                else {
                    restoreRange(jcListComponent, old_end_item, start_item2 - 1, false);
                }
                selectRange(jcListComponent, start_item2, start_item, b);
            }
        }
        else if (start_item2 == old_end_item) {
            selectRange(jcListComponent, start_item2, start_item, b);
        }
        clickRow(jcListComponent, event);
        return true;
    }
    
    static boolean endSelect(final JCListComponent jcListComponent, final Event event) {
        if (jcListComponent.countItems() == 0) {
            return false;
        }
        final int eventToRow = jcListComponent.eventToRow(event, false);
        jcListComponent.last_row = eventToRow;
        final int n = eventToRow;
        jcListComponent.old_start_item = jcListComponent.start_item;
        jcListComponent.old_end_item = jcListComponent.end_item;
        if (jcListComponent.hasFocus()) {
            if (jcListComponent.multiple_select) {
                jcListComponent.drawHighlight(jcListComponent.focus_row, false);
                jcListComponent.drawHighlight(jcListComponent.focus_row = n, true);
            }
            else {
                jcListComponent.drawHighlight(jcListComponent.focus_row, false);
                jcListComponent.drawHighlight(jcListComponent.focus_row = jcListComponent.last_selected_item, true);
            }
        }
        else {
            jcListComponent.focus_row = n;
        }
        if (((!event.shiftDown() && !event.controlDown()) || !jcListComponent.multiple_select) && !jcListComponent.did_selection) {
            if (event.clickCount > 1) {
                jcListComponent.doubleClickAction(event, n);
            }
            else {
                clickRow(jcListComponent, event);
            }
        }
        else if (event.clickCount > 1) {
            jcListComponent.doubleClickAction(event, n);
        }
        jcListComponent.drawHighlight(jcListComponent.focus_row, true);
        jcListComponent.appending = false;
        return true;
    }
    
    static boolean mouseDrag(final JCListComponent jcListComponent, final Event event) {
        if (!jcListComponent.multiple_select) {
            return false;
        }
        if (Math.abs(event.x - ListSelection.last_x) + Math.abs(event.y - ListSelection.last_y) < 20) {
            return false;
        }
        ListSelection.last_x = event.x;
        ListSelection.last_y = event.y;
        jcListComponent.did_selection = false;
        final int eventToRow = jcListComponent.eventToRow(event, false);
        jcListComponent.last_row = eventToRow;
        final int n = eventToRow;
        if (n < 0) {
            return false;
        }
        jcListComponent.makeVisible(n);
        jcListComponent.selection_type = 1;
        if (!postEvent(jcListComponent, 0, event, n)) {
            return false;
        }
        kbdNewItem(jcListComponent, n, event);
        return true;
    }
    
    private static void clickRow(final JCListComponent jcListComponent, final Event event) {
        final int last_selected_item = jcListComponent.last_selected_item;
        jcListComponent.did_selection = true;
        postEvent(jcListComponent, 1, event, last_selected_item);
    }
    
    private static void kbdNewItem(final JCListComponent jcListComponent, final int n, final Event event) {
        if (jcListComponent.last_selected_item == n) {
            return;
        }
        if (!jcListComponent.multiple_select) {
            return;
        }
        if (!jcListComponent.did_selection) {
            clickRow(jcListComponent, event);
        }
        jcListComponent.did_selection = true;
        arrangeRange(jcListComponent, n);
        jcListComponent.end_item = n;
        jcListComponent.last_selected_item = n;
        if (!jcListComponent.did_selection) {
            clickRow(jcListComponent, event);
        }
    }
    
    private static void kbdExtendedItem(final JCListComponent jcListComponent, final int n, final Event event) {
        if (jcListComponent.last_selected_item == n) {
            return;
        }
        if (event.shiftDown()) {
            arrangeRange(jcListComponent, n);
            jcListComponent.last_selected_item = n;
            jcListComponent.end_item = n;
            clickRow(jcListComponent, event);
        }
    }
    
    static void selectFocusRow(final JCListComponent jcListComponent, final Event event) {
        final boolean multiple_select = jcListComponent.multiple_select;
        jcListComponent.multiple_select = false;
        select(jcListComponent, jcListComponent.focus_row, true, event);
        jcListComponent.multiple_select = multiple_select;
    }
    
    private static boolean prevRow(final JCListComponent jcListComponent, final Event event) {
        if (jcListComponent.countItems() == 0) {
            return false;
        }
        final int focus_row = jcListComponent.focus_row - 1;
        if (focus_row < 0) {
            return false;
        }
        if (!postEvent(jcListComponent, 0, event, focus_row)) {
            return false;
        }
        jcListComponent.makeVisible(focus_row);
        jcListComponent.drawHighlight(jcListComponent.focus_row, false);
        jcListComponent.drawHighlight(jcListComponent.focus_row = focus_row, true);
        if (jcListComponent.auto_select) {
            selectFocusRow(jcListComponent, event);
        }
        else if (jcListComponent.multiple_select) {
            kbdExtendedItem(jcListComponent, focus_row, event);
        }
        return true;
    }
    
    private static boolean nextRow(final JCListComponent jcListComponent, final Event event) {
        final int focus_row = jcListComponent.focus_row + 1;
        if (focus_row >= jcListComponent.countItems()) {
            return false;
        }
        jcListComponent.makeVisible(focus_row);
        if (!postEvent(jcListComponent, 0, event, focus_row)) {
            return false;
        }
        jcListComponent.drawHighlight(jcListComponent.focus_row, false);
        jcListComponent.drawHighlight(jcListComponent.focus_row = focus_row, true);
        if (jcListComponent.auto_select) {
            selectFocusRow(jcListComponent, event);
        }
        else if (jcListComponent.multiple_select) {
            kbdExtendedItem(jcListComponent, focus_row, event);
        }
        return true;
    }
    
    static boolean normalNextRow(final JCListComponent jcListComponent, final Event event) {
        if (!jcListComponent.hasFocus()) {
            return false;
        }
        jcListComponent.appending = false;
        jcListComponent.selection_type = 0;
        return nextRow(jcListComponent, event);
    }
    
    static boolean shiftNextRow(final JCListComponent jcListComponent, final Event event) {
        if (!jcListComponent.multiple_select) {
            return false;
        }
        if (!jcListComponent.hasFocus()) {
            return false;
        }
        jcListComponent.appending = true;
        event.modifiers |= 0x1;
        jcListComponent.selection_type = 1;
        final boolean nextRow = nextRow(jcListComponent, event);
        jcListComponent.appending = false;
        return nextRow;
    }
    
    static boolean ctrlNextRow(final JCListComponent jcListComponent, final Event event) {
        if (!jcListComponent.hasFocus()) {
            return false;
        }
        jcListComponent.appending = true;
        event.modifiers |= 0x2;
        jcListComponent.selection_type = 2;
        final boolean nextRow = nextRow(jcListComponent, event);
        jcListComponent.appending = false;
        return nextRow;
    }
    
    static boolean ctrlShiftNextRow(final JCListComponent jcListComponent, final Event event) {
        if (!jcListComponent.multiple_select) {
            return false;
        }
        if (!jcListComponent.hasFocus()) {
            return false;
        }
        jcListComponent.appending = true;
        event.modifiers |= 0x3;
        jcListComponent.selection_type = 1;
        final boolean nextRow = nextRow(jcListComponent, event);
        jcListComponent.appending = false;
        return nextRow;
    }
    
    static boolean normalPrevRow(final JCListComponent jcListComponent, final Event event) {
        if (!jcListComponent.hasFocus()) {
            return false;
        }
        jcListComponent.appending = false;
        event.modifiers &= 0xFFFFFFF4;
        jcListComponent.selection_type = 0;
        return prevRow(jcListComponent, event);
    }
    
    static boolean shiftPrevRow(final JCListComponent jcListComponent, final Event event) {
        if (!jcListComponent.multiple_select) {
            return false;
        }
        if (!jcListComponent.hasFocus()) {
            return false;
        }
        jcListComponent.appending = true;
        event.modifiers |= 0x1;
        jcListComponent.selection_type = 1;
        final boolean prevRow = prevRow(jcListComponent, event);
        jcListComponent.appending = false;
        return prevRow;
    }
    
    static boolean ctrlPrevRow(final JCListComponent jcListComponent, final Event event) {
        if (!jcListComponent.hasFocus()) {
            return false;
        }
        jcListComponent.appending = true;
        event.modifiers |= 0x2;
        jcListComponent.selection_type = 2;
        final boolean prevRow = prevRow(jcListComponent, event);
        jcListComponent.appending = false;
        return prevRow;
    }
    
    static boolean ctrlShiftPrevRow(final JCListComponent jcListComponent, final Event event) {
        if (!jcListComponent.multiple_select) {
            return false;
        }
        if (!jcListComponent.hasFocus()) {
            return false;
        }
        jcListComponent.appending = true;
        event.modifiers |= 0x3;
        jcListComponent.selection_type = 1;
        final boolean prevRow = prevRow(jcListComponent, event);
        jcListComponent.appending = false;
        return prevRow;
    }
    
    static boolean beginExtend(final JCListComponent jcListComponent, final Event event) {
        if (!jcListComponent.multiple_select) {
            return false;
        }
        jcListComponent.appending = true;
        event.modifiers |= 0x1;
        return beginSelect(jcListComponent, event);
    }
    
    static boolean endExtend(final JCListComponent jcListComponent, final Event event) {
        if (!jcListComponent.multiple_select) {
            return false;
        }
        jcListComponent.appending = false;
        return endSelect(jcListComponent, event);
    }
    
    static boolean beginToggle(final JCListComponent jcListComponent, final Event event) {
        if (!jcListComponent.multiple_select) {
            return false;
        }
        jcListComponent.appending = true;
        event.modifiers |= 0x2;
        jcListComponent.old_start_item = jcListComponent.start_item;
        jcListComponent.old_end_item = jcListComponent.end_item;
        int i = Math.min(jcListComponent.old_start_item, jcListComponent.old_end_item);
        final int max = Math.max(jcListComponent.old_start_item, jcListComponent.old_end_item);
        if (i == 0) {
            if (max == 0) {
                return beginSelect(jcListComponent, event);
            }
        }
        while (i <= max) {
            jcListComponent.last_selected[i] = jcListComponent.selected[i];
            ++i;
        }
        return beginSelect(jcListComponent, event);
    }
    
    static boolean endToggle(final JCListComponent jcListComponent, final Event event) {
        if (!jcListComponent.multiple_select) {
            return false;
        }
        jcListComponent.appending = false;
        return endSelect(jcListComponent, event);
    }
    
    static boolean kbdSelect(final JCListComponent jcListComponent, final Event event) {
        if (jcListComponent.focus_row < 0) {
            return false;
        }
        final Rectangle bounds = jcListComponent.getBounds(jcListComponent.focus_row, true);
        event.x = bounds.x + 1;
        event.y = bounds.y + 1;
        if (jcListComponent.multiple_select && (event.shiftDown() || event.controlDown())) {
            jcListComponent.appending = true;
        }
        jcListComponent.kbd_select = true;
        final boolean beginSelect = beginSelect(jcListComponent, event);
        if (beginSelect) {
            clickRow(jcListComponent, event);
        }
        jcListComponent.kbd_select = false;
        return beginSelect;
    }
    
    static void select(final JCListComponent jcListComponent, final int last_selected_item, final boolean b, final Event event) {
        int[] array = new int[0];
        int n = 0;
        synchronized (jcListComponent) {
            if (jcListComponent.selected.length < jcListComponent.countItems()) {
                jcListComponent.updateSelectedList();
            }
            if (!jcListComponent.multiple_select) {
                jcListComponent.selection_type = 0;
                array = new int[jcListComponent.selected.length];
                for (int i = 0; i < jcListComponent.countItems(); ++i) {
                    if (jcListComponent.selected[i]) {
                        jcListComponent.selected[i] = (jcListComponent.last_selected[i] = false);
                        array[n] = i;
                        ++n;
                    }
                }
            }
            else {
                jcListComponent.selection_type = 1;
            }
        }
        for (int j = 0; j < n; ++j) {
            jcListComponent.paintRow(array[j]);
        }
        synchronized (jcListComponent) {
            jcListComponent.selected[last_selected_item] = (jcListComponent.last_selected[last_selected_item] = true);
            jcListComponent.last_selected_item = last_selected_item;
        }
        jcListComponent.paintRow(last_selected_item);
        if (b) {
            clickRow(jcListComponent, event);
        }
        int focus_row = 0;
        int focus_row2 = 0;
        boolean b2 = false;
        synchronized (jcListComponent) {
            for (int k = jcListComponent.countItems() - 1; k >= 0; --k) {
                if (jcListComponent.selected[k]) {
                    final int focus_row3 = k;
                    while (k != 0 && jcListComponent.selected[k]) {
                        --k;
                    }
                    int start_item;
                    if (k == 0 && jcListComponent.selected[k]) {
                        start_item = k;
                    }
                    else {
                        start_item = k + 1;
                    }
                    jcListComponent.old_end_item = jcListComponent.end_item;
                    jcListComponent.end_item = focus_row3;
                    jcListComponent.old_start_item = jcListComponent.start_item;
                    jcListComponent.start_item = start_item;
                    jcListComponent.last_selected_item = focus_row3;
                    focus_row = jcListComponent.focus_row;
                    jcListComponent.focus_row = focus_row3;
                    focus_row2 = jcListComponent.focus_row;
                    b2 = true;
                }
            }
            if (!b2) {
                jcListComponent.old_end_item = jcListComponent.end_item;
                jcListComponent.end_item = 0;
                jcListComponent.old_start_item = jcListComponent.start_item;
                jcListComponent.start_item = 0;
                jcListComponent.last_selected_item = 0;
            }
        }
        if (b2) {
            jcListComponent.drawHighlight(focus_row, false);
            jcListComponent.drawHighlight(focus_row2, true);
        }
    }
    
    static {
        ListSelection.last_x = -999;
    }
}
