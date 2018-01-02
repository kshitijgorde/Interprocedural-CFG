// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.TVHITTESTINFO;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TVITEM;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class TreeDropTargetEffect extends DropTargetEffect
{
    static final int SCROLL_HYSTERESIS = 200;
    static final int EXPAND_HYSTERESIS = 1000;
    int dropIndex;
    int scrollIndex;
    long scrollBeginTime;
    int expandIndex;
    long expandBeginTime;
    TreeItem insertItem;
    boolean insertBefore;
    
    public TreeDropTargetEffect(final Tree tree) {
        super(tree);
    }
    
    int checkEffect(int n) {
        if ((n & 0x1) != 0x0) {
            n = (n & 0xFFFFFFFB & 0xFFFFFFFD);
        }
        if ((n & 0x2) != 0x0) {
            n &= 0xFFFFFFFB;
        }
        return n;
    }
    
    public void dragEnter(final DropTargetEvent dropTargetEvent) {
        this.dropIndex = -1;
        this.insertItem = null;
        this.expandBeginTime = 0L;
        this.expandIndex = -1;
        this.scrollBeginTime = 0L;
        this.scrollIndex = -1;
    }
    
    public void dragLeave(final DropTargetEvent dropTargetEvent) {
        final Tree tree = (Tree)this.control;
        final int handle = tree.handle;
        if (this.dropIndex != -1) {
            final TVITEM tvitem = new TVITEM();
            tvitem.hItem = this.dropIndex;
            tvitem.mask = 8;
            tvitem.stateMask = 8;
            tvitem.state = 0;
            OS.SendMessage(handle, OS.TVM_SETITEM, 0, tvitem);
            this.dropIndex = -1;
        }
        if (this.insertItem != null) {
            tree.setInsertMark(null, false);
            this.insertItem = null;
        }
        this.expandBeginTime = 0L;
        this.expandIndex = -1;
        this.scrollBeginTime = 0L;
        this.scrollIndex = -1;
    }
    
    public void dragOver(final DropTargetEvent dropTargetEvent) {
        final Tree tree = (Tree)this.getControl();
        final int checkEffect = this.checkEffect(dropTargetEvent.feedback);
        final int handle = tree.handle;
        final Point control = tree.toControl(new Point(dropTargetEvent.x, dropTargetEvent.y));
        final TVHITTESTINFO tvhittestinfo = new TVHITTESTINFO();
        tvhittestinfo.x = control.x;
        tvhittestinfo.y = control.y;
        OS.SendMessage(handle, 4369, 0, tvhittestinfo);
        final int hItem = tvhittestinfo.hItem;
        if ((checkEffect & 0x8) == 0x0) {
            this.scrollBeginTime = 0L;
            this.scrollIndex = -1;
        }
        else if (hItem != -1 && this.scrollIndex == hItem && this.scrollBeginTime != 0L) {
            if (System.currentTimeMillis() >= this.scrollBeginTime) {
                final int sendMessage = OS.SendMessage(handle, 4362, 5, 0);
                final int sendMessage2 = OS.SendMessage(handle, 4362, (hItem == sendMessage) ? 7 : 6, hItem);
                int n = 1;
                if (hItem == sendMessage) {
                    n = ((sendMessage2 != 0) ? 1 : 0);
                }
                else {
                    final RECT rect = new RECT();
                    if (OS.TreeView_GetItemRect(handle, sendMessage2, rect, true)) {
                        final RECT rect2 = new RECT();
                        OS.GetClientRect(handle, rect2);
                        final POINT point = new POINT();
                        point.x = rect.left;
                        point.y = rect.top;
                        if (OS.PtInRect(rect2, point)) {
                            point.y = rect.bottom;
                            if (OS.PtInRect(rect2, point)) {
                                n = 0;
                            }
                        }
                    }
                }
                if (n != 0) {
                    OS.SendMessage(handle, 4372, 0, sendMessage2);
                    tree.redraw();
                }
                this.scrollBeginTime = 0L;
                this.scrollIndex = -1;
            }
        }
        else {
            this.scrollBeginTime = System.currentTimeMillis() + 200L;
            this.scrollIndex = hItem;
        }
        if ((checkEffect & 0x10) == 0x0) {
            this.expandBeginTime = 0L;
            this.expandIndex = -1;
        }
        else if (hItem != -1 && this.expandIndex == hItem && this.expandBeginTime != 0L) {
            if (System.currentTimeMillis() >= this.expandBeginTime) {
                if (OS.SendMessage(handle, 4362, 4, hItem) != 0) {
                    final TreeItem item = (TreeItem)tree.getDisplay().findWidget(tree.handle, hItem);
                    if (item != null && !item.getExpanded()) {
                        item.setExpanded(true);
                        tree.redraw();
                        final Event event = new Event();
                        event.item = item;
                        tree.notifyListeners(17, event);
                    }
                }
                this.expandBeginTime = 0L;
                this.expandIndex = -1;
            }
        }
        else {
            this.expandBeginTime = System.currentTimeMillis() + 1000L;
            this.expandIndex = hItem;
        }
        if (this.dropIndex != -1 && (this.dropIndex != hItem || (checkEffect & 0x1) == 0x0)) {
            final TVITEM tvitem = new TVITEM();
            tvitem.hItem = this.dropIndex;
            tvitem.mask = 8;
            tvitem.stateMask = 8;
            tvitem.state = 0;
            OS.SendMessage(handle, OS.TVM_SETITEM, 0, tvitem);
            this.dropIndex = -1;
        }
        if (hItem != -1 && hItem != this.dropIndex && (checkEffect & 0x1) != 0x0) {
            final TVITEM tvitem2 = new TVITEM();
            tvitem2.hItem = hItem;
            tvitem2.mask = 8;
            tvitem2.stateMask = 8;
            tvitem2.state = 8;
            OS.SendMessage(handle, OS.TVM_SETITEM, 0, tvitem2);
            this.dropIndex = hItem;
        }
        if ((checkEffect & 0x2) != 0x0 || (checkEffect & 0x4) != 0x0) {
            final boolean insertBefore = (checkEffect & 0x2) != 0x0;
            final TreeItem insertItem = (TreeItem)tree.getDisplay().findWidget(tree.handle, hItem);
            if (insertItem != null) {
                if (insertItem != this.insertItem || insertBefore != this.insertBefore) {
                    tree.setInsertMark(insertItem, insertBefore);
                }
                this.insertItem = insertItem;
                this.insertBefore = insertBefore;
            }
            else {
                if (this.insertItem != null) {
                    tree.setInsertMark(null, false);
                }
                this.insertItem = null;
            }
        }
        else {
            if (this.insertItem != null) {
                tree.setInsertMark(null, false);
            }
            this.insertItem = null;
        }
    }
}
