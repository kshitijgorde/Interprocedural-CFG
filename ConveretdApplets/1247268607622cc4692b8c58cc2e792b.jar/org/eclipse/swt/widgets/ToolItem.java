// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.ImageList;
import org.eclipse.swt.internal.win32.TBBUTTONINFO;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;

public class ToolItem extends Item
{
    ToolBar parent;
    Control control;
    String toolTipText;
    Image disabledImage;
    Image hotImage;
    Image disabledImage2;
    int id;
    short cx;
    
    public ToolItem(final ToolBar parent, final int n) {
        super(parent, checkStyle(n));
        (this.parent = parent).createItem(this, parent.getItemCount());
    }
    
    public ToolItem(final ToolBar parent, final int n, final int n2) {
        super(parent, checkStyle(n));
        (this.parent = parent).createItem(this, n2);
    }
    
    public void addSelectionListener(final SelectionListener selectionListener) {
        this.checkWidget();
        if (selectionListener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener(selectionListener);
        this.addListener(13, typedListener);
        this.addListener(14, typedListener);
    }
    
    static int checkStyle(final int n) {
        return Widget.checkBits(n, 8, 32, 16, 2, 4, 0);
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    void click(final boolean b) {
        final int handle = this.parent.handle;
        if (OS.GetKeyState(1) < 0) {
            return;
        }
        final int sendMessage = OS.SendMessage(handle, 1049, this.id, 0);
        final RECT rect = new RECT();
        OS.SendMessage(handle, 1053, sendMessage, rect);
        final int sendMessage2 = OS.SendMessage(handle, 1095, 0, 0);
        final int makelparam = OS.MAKELPARAM(b ? (rect.right - 1) : rect.left, rect.top + (rect.bottom - rect.top) / 2);
        this.parent.ignoreMouse = true;
        OS.SendMessage(handle, 513, 0, makelparam);
        OS.SendMessage(handle, 514, 0, makelparam);
        this.parent.ignoreMouse = false;
        if (sendMessage2 != -1) {
            OS.SendMessage(handle, 1096, sendMessage2, 0);
        }
    }
    
    Widget[] computeTabList() {
        if (this.isTabGroup() && this.getEnabled()) {
            if ((this.style & 0x2) == 0x0) {
                return new Widget[] { this };
            }
            if (this.control != null) {
                return this.control.computeTabList();
            }
        }
        return new Widget[0];
    }
    
    void destroyWidget() {
        this.parent.destroyItem(this);
        this.releaseHandle();
    }
    
    public Rectangle getBounds() {
        this.checkWidget();
        final int handle = this.parent.handle;
        final int sendMessage = OS.SendMessage(handle, 1049, this.id, 0);
        final RECT rect = new RECT();
        OS.SendMessage(handle, 1053, sendMessage, rect);
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
    
    public Control getControl() {
        this.checkWidget();
        return this.control;
    }
    
    public Image getDisabledImage() {
        this.checkWidget();
        return this.disabledImage;
    }
    
    public boolean getEnabled() {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return (this.state & 0x8) == 0x0;
        }
        return (OS.SendMessage(this.parent.handle, 1042, this.id, 0) & 0x4) != 0x0;
    }
    
    public Image getHotImage() {
        this.checkWidget();
        return this.hotImage;
    }
    
    public ToolBar getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public boolean getSelection() {
        this.checkWidget();
        return (this.style & 0x30) != 0x0 && (OS.SendMessage(this.parent.handle, 1042, this.id, 0) & 0x1) != 0x0;
    }
    
    public String getToolTipText() {
        this.checkWidget();
        return this.toolTipText;
    }
    
    public int getWidth() {
        this.checkWidget();
        final int handle = this.parent.handle;
        final int sendMessage = OS.SendMessage(handle, 1049, this.id, 0);
        final RECT rect = new RECT();
        OS.SendMessage(handle, 1053, sendMessage, rect);
        return rect.right - rect.left;
    }
    
    public boolean isEnabled() {
        this.checkWidget();
        return this.getEnabled() && this.parent.isEnabled();
    }
    
    boolean isTabGroup() {
        final ToolItem[] getTabItemList = this.parent._getTabItemList();
        if (getTabItemList != null) {
            for (int i = 0; i < getTabItemList.length; ++i) {
                if (getTabItemList[i] == this) {
                    return true;
                }
            }
        }
        if ((this.style & 0x2) != 0x0) {
            return true;
        }
        final int index = this.parent.indexOf(this);
        return index == 0 || (this.parent.getItem(index - 1).getStyle() & 0x2) != 0x0;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.releaseImages();
        this.control = null;
        this.toolTipText = null;
        final Image image = null;
        this.hotImage = image;
        this.disabledImage = image;
        if (this.disabledImage2 != null) {
            this.disabledImage2.dispose();
        }
        this.disabledImage2 = null;
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
        this.id = -1;
    }
    
    void releaseImages() {
        final TBBUTTONINFO tbbuttoninfo = new TBBUTTONINFO();
        tbbuttoninfo.cbSize = TBBUTTONINFO.sizeof;
        tbbuttoninfo.dwMask = 9;
        OS.SendMessage(this.parent.handle, OS.TB_GETBUTTONINFO, this.id, tbbuttoninfo);
        if ((tbbuttoninfo.fsStyle & 0x1) == 0x0 && tbbuttoninfo.iImage != -2) {
            final ImageList imageList = this.parent.getImageList();
            final ImageList hotImageList = this.parent.getHotImageList();
            final ImageList disabledImageList = this.parent.getDisabledImageList();
            if (imageList != null) {
                imageList.put(tbbuttoninfo.iImage, null);
            }
            if (hotImageList != null) {
                hotImageList.put(tbbuttoninfo.iImage, null);
            }
            if (disabledImageList != null) {
                disabledImageList.put(tbbuttoninfo.iImage, null);
            }
        }
    }
    
    public void removeSelectionListener(final SelectionListener selectionListener) {
        this.checkWidget();
        if (selectionListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(13, selectionListener);
        this.eventTable.unhook(14, selectionListener);
    }
    
    void resizeControl() {
        if (this.control != null && !this.control.isDisposed()) {
            final Rectangle bounds = this.getBounds();
            this.control.setSize(bounds.width, bounds.height);
            final Rectangle bounds2 = this.control.getBounds();
            bounds2.x = bounds.x + (bounds.width - bounds2.width) / 2;
            bounds2.y = bounds.y + (bounds.height - bounds2.height) / 2;
            this.control.setLocation(bounds2.x, bounds2.y);
        }
    }
    
    void selectRadio() {
        int n;
        ToolItem[] items;
        for (n = 0, items = this.parent.getItems(); n < items.length && items[n] != this; ++n) {}
        for (int n2 = n - 1; n2 >= 0 && items[n2].setRadioSelection(false); --n2) {}
        for (int n3 = n + 1; n3 < items.length && items[n3].setRadioSelection(false); ++n3) {}
        this.setSelection(true);
    }
    
    public void setControl(final Control control) {
        this.checkWidget();
        if (control != null) {
            if (control.isDisposed()) {
                this.error(5);
            }
            if (control.parent != this.parent) {
                this.error(32);
            }
        }
        if ((this.style & 0x2) == 0x0) {
            return;
        }
        this.control = control;
        if ((this.parent.style & 0x240) != 0x0) {
            boolean b = false;
            final int handle = this.parent.handle;
            final TBBUTTONINFO tbbuttoninfo = new TBBUTTONINFO();
            tbbuttoninfo.cbSize = TBBUTTONINFO.sizeof;
            tbbuttoninfo.dwMask = 12;
            OS.SendMessage(handle, OS.TB_GETBUTTONINFO, this.id, tbbuttoninfo);
            if (control == null) {
                if ((tbbuttoninfo.fsStyle & 0x1) == 0x0) {
                    b = true;
                    final TBBUTTONINFO tbbuttoninfo2 = tbbuttoninfo;
                    tbbuttoninfo2.fsStyle &= 0xFFFFFFBF;
                    final TBBUTTONINFO tbbuttoninfo3 = tbbuttoninfo;
                    tbbuttoninfo3.fsStyle |= 0x1;
                    if ((this.state & 0x8) != 0x0) {
                        final TBBUTTONINFO tbbuttoninfo4 = tbbuttoninfo;
                        tbbuttoninfo4.fsState &= 0xFFFFFFFB;
                    }
                    else {
                        final TBBUTTONINFO tbbuttoninfo5 = tbbuttoninfo;
                        tbbuttoninfo5.fsState |= 0x4;
                    }
                }
            }
            else if ((tbbuttoninfo.fsStyle & 0x1) != 0x0) {
                b = true;
                final TBBUTTONINFO tbbuttoninfo6 = tbbuttoninfo;
                tbbuttoninfo6.fsStyle &= 0xFFFFFFFE;
                final TBBUTTONINFO tbbuttoninfo7 = tbbuttoninfo;
                tbbuttoninfo7.fsStyle |= 0x40;
                final TBBUTTONINFO tbbuttoninfo8 = tbbuttoninfo;
                tbbuttoninfo8.fsState &= 0xFFFFFFFB;
                final TBBUTTONINFO tbbuttoninfo9 = tbbuttoninfo;
                tbbuttoninfo9.dwMask |= 0x1;
                tbbuttoninfo.iImage = -2;
            }
            if (b) {
                OS.SendMessage(handle, OS.TB_SETBUTTONINFO, this.id, tbbuttoninfo);
                if (OS.SendMessage(handle, 1064, 0, 0) > 1) {
                    OS.InvalidateRect(handle, null, true);
                }
            }
        }
        this.resizeControl();
    }
    
    public void setEnabled(final boolean b) {
        this.checkWidget();
        final int handle = this.parent.handle;
        final int sendMessage = OS.SendMessage(handle, 1042, this.id, 0);
        if ((sendMessage & 0x4) != 0x0 == b) {
            return;
        }
        int n;
        if (b) {
            n = (sendMessage | 0x4);
            this.state &= 0xFFFFFFF7;
        }
        else {
            n = (sendMessage & 0xFFFFFFFB);
            this.state |= 0x8;
        }
        OS.SendMessage(handle, 1041, this.id, n);
        if ((this.style & 0x2) == 0x0 && this.image != null) {
            this.updateImages(b && this.parent.getEnabled());
        }
    }
    
    public void setDisabledImage(final Image disabledImage) {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        if (disabledImage != null && disabledImage.isDisposed()) {
            this.error(5);
        }
        this.disabledImage = disabledImage;
        this.updateImages(this.getEnabled() && this.parent.getEnabled());
    }
    
    public void setHotImage(final Image hotImage) {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        if (hotImage != null && hotImage.isDisposed()) {
            this.error(5);
        }
        this.hotImage = hotImage;
        this.updateImages(this.getEnabled() && this.parent.getEnabled());
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        super.setImage(image);
        this.updateImages(this.getEnabled() && this.parent.getEnabled());
    }
    
    boolean setRadioSelection(final boolean selection) {
        if ((this.style & 0x10) == 0x0) {
            return false;
        }
        if (this.getSelection() != selection) {
            this.setSelection(selection);
            this.sendSelectionEvent(13);
        }
        return true;
    }
    
    public void setSelection(final boolean b) {
        this.checkWidget();
        if ((this.style & 0x30) == 0x0) {
            return;
        }
        final int handle = this.parent.handle;
        final int sendMessage = OS.SendMessage(handle, 1042, this.id, 0);
        if ((sendMessage & 0x1) != 0x0 == b) {
            return;
        }
        int n;
        if (b) {
            n = (sendMessage | 0x1);
        }
        else {
            n = (sendMessage & 0xFFFFFFFE);
        }
        OS.SendMessage(handle, 1041, this.id, n);
        if ((this.style & 0x30) != 0x0 && (!this.getEnabled() || !this.parent.getEnabled())) {
            this.updateImages(false);
        }
    }
    
    boolean setTabItemFocus() {
        if (this.parent.setTabItemFocus()) {
            final int handle = this.parent.handle;
            OS.SendMessage(handle, 1096, OS.SendMessage(handle, 1049, this.id, 0), 0);
            return true;
        }
        return false;
    }
    
    public void setText(final String text) {
        this.checkWidget();
        if (text == null) {
            this.error(4);
        }
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        if (text.equals(this.text)) {
            return;
        }
        super.setText(text);
        final int handle = this.parent.handle;
        final TBBUTTONINFO tbbuttoninfo = new TBBUTTONINFO();
        tbbuttoninfo.cbSize = TBBUTTONINFO.sizeof;
        tbbuttoninfo.dwMask = 10;
        tbbuttoninfo.fsStyle = (byte)(this.widgetStyle() | 0x10);
        final int getProcessHeap = OS.GetProcessHeap();
        int heapAlloc = 0;
        if (text.length() != 0) {
            final TBBUTTONINFO tbbuttoninfo2 = tbbuttoninfo;
            tbbuttoninfo2.fsStyle |= 0x40;
            final TCHAR tchar = new TCHAR(this.parent.getCodePage(), text, true);
            final int n = tchar.length() * TCHAR.sizeof;
            heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n);
            OS.MoveMemory(heapAlloc, tchar, n);
            tbbuttoninfo.pszText = heapAlloc;
        }
        OS.SendMessage(handle, OS.TB_SETBUTTONINFO, this.id, tbbuttoninfo);
        if (heapAlloc != 0) {
            OS.HeapFree(getProcessHeap, 0, heapAlloc);
        }
        this.parent.setDropDownItems(false);
        OS.SendMessage(handle, 48, OS.SendMessage(handle, 49, 0, 0), 0);
        this.parent.setDropDownItems(true);
        this.parent.layoutItems();
    }
    
    public void setToolTipText(final String toolTipText) {
        this.checkWidget();
        this.toolTipText = toolTipText;
    }
    
    public void setWidth(final int n) {
        this.checkWidget();
        if ((this.style & 0x2) == 0x0) {
            return;
        }
        if (n < 0) {
            return;
        }
        final int handle = this.parent.handle;
        final TBBUTTONINFO tbbuttoninfo = new TBBUTTONINFO();
        tbbuttoninfo.cbSize = TBBUTTONINFO.sizeof;
        tbbuttoninfo.dwMask = 64;
        final TBBUTTONINFO tbbuttoninfo2 = tbbuttoninfo;
        final short n2 = (short)n;
        this.cx = n2;
        tbbuttoninfo2.cx = n2;
        OS.SendMessage(handle, OS.TB_SETBUTTONINFO, this.id, tbbuttoninfo);
        this.parent.layoutItems();
    }
    
    void updateImages(final boolean b) {
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        final int handle = this.parent.handle;
        final TBBUTTONINFO tbbuttoninfo = new TBBUTTONINFO();
        tbbuttoninfo.cbSize = TBBUTTONINFO.sizeof;
        tbbuttoninfo.dwMask = 1;
        OS.SendMessage(handle, OS.TB_GETBUTTONINFO, this.id, tbbuttoninfo);
        if (tbbuttoninfo.iImage == -2 && this.image == null) {
            return;
        }
        ImageList imageList = this.parent.getImageList();
        ImageList hotImageList = this.parent.getHotImageList();
        ImageList disabledImageList = this.parent.getDisabledImageList();
        if (tbbuttoninfo.iImage == -2) {
            final Rectangle bounds = this.image.getBounds();
            if (imageList == null) {
                imageList = this.display.getImageListToolBar(0, bounds.width, bounds.height);
            }
            if (disabledImageList == null) {
                disabledImageList = this.display.getImageListToolBarDisabled(0, bounds.width, bounds.height);
            }
            if (hotImageList == null) {
                hotImageList = this.display.getImageListToolBarHot(0, bounds.width, bounds.height);
            }
            Image image = this.disabledImage;
            if (this.disabledImage == null) {
                if (this.disabledImage2 != null) {
                    this.disabledImage2.dispose();
                }
                this.disabledImage2 = null;
                image = this.image;
                if (!b) {
                    final Image disabledImage2 = new Image(this.display, this.image, 1);
                    this.disabledImage2 = disabledImage2;
                    image = disabledImage2;
                }
            }
            Image image2 = this.image;
            Image hotImage = this.hotImage;
            if ((this.style & 0x30) != 0x0 && !b) {
                hotImage = (image2 = image);
            }
            tbbuttoninfo.iImage = imageList.add(image2);
            disabledImageList.add(image);
            hotImageList.add((hotImage != null) ? hotImage : image2);
            this.parent.setImageList(imageList);
            this.parent.setDisabledImageList(disabledImageList);
            this.parent.setHotImageList(hotImageList);
        }
        else {
            Image image3 = null;
            if (disabledImageList != null) {
                if (this.image != null) {
                    if (this.disabledImage2 != null) {
                        this.disabledImage2.dispose();
                    }
                    this.disabledImage2 = null;
                    image3 = this.disabledImage;
                    if (this.disabledImage == null) {
                        image3 = this.image;
                        if (!b) {
                            final Image disabledImage3 = new Image(this.display, this.image, 1);
                            this.disabledImage2 = disabledImage3;
                            image3 = disabledImage3;
                        }
                    }
                }
                disabledImageList.put(tbbuttoninfo.iImage, image3);
            }
            Image image4 = this.image;
            Image hotImage2 = this.hotImage;
            if ((this.style & 0x30) != 0x0 && !b) {
                hotImage2 = (image4 = image3);
            }
            if (imageList != null) {
                imageList.put(tbbuttoninfo.iImage, image4);
            }
            if (hotImageList != null) {
                hotImageList.put(tbbuttoninfo.iImage, (hotImage2 != null) ? hotImage2 : image4);
            }
            if (this.image == null) {
                tbbuttoninfo.iImage = -2;
            }
        }
        final TBBUTTONINFO tbbuttoninfo2 = tbbuttoninfo;
        tbbuttoninfo2.dwMask |= 0x40;
        tbbuttoninfo.cx = 0;
        OS.SendMessage(handle, OS.TB_SETBUTTONINFO, this.id, tbbuttoninfo);
        OS.SendMessage(handle, 48, OS.SendMessage(handle, 49, 0, 0), 0);
        this.parent.layoutItems();
    }
    
    int widgetStyle() {
        if ((this.style & 0x4) != 0x0) {
            return 8;
        }
        if ((this.style & 0x8) != 0x0) {
            return 0;
        }
        if ((this.style & 0x20) != 0x0) {
            return 2;
        }
        if ((this.style & 0x10) != 0x0) {
            return 2;
        }
        if ((this.style & 0x2) != 0x0) {
            return 1;
        }
        return 0;
    }
    
    LRESULT wmCommandChild(final int n, final int n2) {
        if ((this.style & 0x10) != 0x0 && (this.parent.getStyle() & 0x400000) == 0x0) {
            this.selectRadio();
        }
        this.sendSelectionEvent(13);
        return null;
    }
}
