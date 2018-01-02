// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.LVHITTESTINFO;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.LVINSERTMARK;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.LVITEM;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class TableDropTargetEffect extends DropTargetEffect
{
    static final int SCROLL_HYSTERESIS = 200;
    int scrollIndex;
    long scrollBeginTime;
    TableItem dropHighlight;
    int iItemInsert;
    
    public TableDropTargetEffect(final Table table) {
        super(table);
        this.scrollIndex = -1;
        this.iItemInsert = -1;
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
        this.scrollBeginTime = 0L;
        this.scrollIndex = -1;
        this.dropHighlight = null;
        this.iItemInsert = -1;
    }
    
    public void dragLeave(final DropTargetEvent dropTargetEvent) {
        final int handle = ((Table)this.control).handle;
        if (this.dropHighlight != null) {
            final LVITEM lvitem = new LVITEM();
            lvitem.stateMask = 8;
            OS.SendMessage(handle, 4139, -1, lvitem);
            this.dropHighlight = null;
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(5, 1) && this.iItemInsert != -1) {
            final LVINSERTMARK lvinsertmark = new LVINSERTMARK();
            lvinsertmark.cbSize = LVINSERTMARK.sizeof;
            lvinsertmark.iItem = -1;
            OS.SendMessage(handle, 4262, 0, lvinsertmark);
            this.iItemInsert = -1;
        }
        this.scrollBeginTime = 0L;
        this.scrollIndex = -1;
    }
    
    public void dragOver(final DropTargetEvent dropTargetEvent) {
        final Table table = (Table)this.getControl();
        final int checkEffect = this.checkEffect(dropTargetEvent.feedback);
        final int handle = table.handle;
        final Point control = table.toControl(new Point(dropTargetEvent.x, dropTargetEvent.y));
        final LVHITTESTINFO lvhittestinfo = new LVHITTESTINFO();
        lvhittestinfo.x = control.x;
        lvhittestinfo.y = control.y;
        OS.SendMessage(handle, 4114, 0, lvhittestinfo);
        if ((checkEffect & 0x8) == 0x0) {
            this.scrollBeginTime = 0L;
            this.scrollIndex = -1;
        }
        else if (lvhittestinfo.iItem != -1 && this.scrollIndex == lvhittestinfo.iItem && this.scrollBeginTime != 0L) {
            if (System.currentTimeMillis() >= this.scrollBeginTime) {
                final int max = Math.max(0, OS.SendMessage(handle, 4135, 0, 0));
                final int sendMessage = OS.SendMessage(handle, 4100, 0, 0);
                final int n = (this.scrollIndex - 1 < max) ? Math.max(0, this.scrollIndex - 1) : Math.min(sendMessage - 1, this.scrollIndex + 1);
                int n2 = 1;
                if (lvhittestinfo.iItem == max) {
                    n2 = ((lvhittestinfo.iItem != n) ? 1 : 0);
                }
                else {
                    final RECT rect = new RECT();
                    rect.left = 0;
                    if (OS.SendMessage(handle, 4110, lvhittestinfo.iItem, rect) != 0) {
                        final RECT rect2 = new RECT();
                        OS.GetClientRect(handle, rect2);
                        final POINT point = new POINT();
                        point.x = rect.left;
                        point.y = rect.top;
                        if (OS.PtInRect(rect2, point)) {
                            point.y = rect.bottom;
                            if (OS.PtInRect(rect2, point)) {
                                n2 = 0;
                            }
                        }
                    }
                }
                if (n2 != 0) {
                    OS.SendMessage(handle, 4115, n, 0);
                    table.redraw();
                }
                this.scrollBeginTime = 0L;
                this.scrollIndex = -1;
            }
        }
        else {
            this.scrollBeginTime = System.currentTimeMillis() + 200L;
            this.scrollIndex = lvhittestinfo.iItem;
        }
        if (lvhittestinfo.iItem != -1 && (checkEffect & 0x1) != 0x0) {
            final TableItem item = table.getItem(lvhittestinfo.iItem);
            if (this.dropHighlight != item) {
                final LVITEM lvitem = new LVITEM();
                lvitem.stateMask = 8;
                OS.SendMessage(handle, 4139, -1, lvitem);
                lvitem.state = 8;
                OS.SendMessage(handle, 4139, lvhittestinfo.iItem, lvitem);
                this.dropHighlight = item;
            }
        }
        else if (this.dropHighlight != null) {
            final LVITEM lvitem2 = new LVITEM();
            lvitem2.stateMask = 8;
            OS.SendMessage(handle, 4139, -1, lvitem2);
            this.dropHighlight = null;
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(5, 1)) {
            if (lvhittestinfo.iItem != -1 && (checkEffect & 0x6) != 0x0) {
                final LVINSERTMARK lvinsertmark = new LVINSERTMARK();
                lvinsertmark.cbSize = LVINSERTMARK.sizeof;
                lvinsertmark.dwFlags = (((checkEffect & 0x4) != 0x0) ? 1 : 0);
                lvinsertmark.iItem = lvhittestinfo.iItem;
                if (OS.SendMessage(handle, 4262, 0, lvinsertmark) != 0) {
                    this.iItemInsert = lvhittestinfo.iItem;
                }
            }
            else if (this.iItemInsert != -1) {
                final LVINSERTMARK lvinsertmark2 = new LVINSERTMARK();
                lvinsertmark2.cbSize = LVINSERTMARK.sizeof;
                lvinsertmark2.iItem = -1;
                OS.SendMessage(handle, 4262, 0, lvinsertmark2);
                this.iItemInsert = -1;
            }
        }
    }
}
