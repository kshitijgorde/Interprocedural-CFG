// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.DRAWITEMSTRUCT;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.TEXTMETRIC;
import org.eclipse.swt.internal.win32.TEXTMETRICA;
import org.eclipse.swt.internal.win32.TEXTMETRICW;
import org.eclipse.swt.internal.win32.SIZE;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.BUTTON_IMAGELIST;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.BITMAP;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.ImageList;
import org.eclipse.swt.graphics.Image;

public class Button extends Control
{
    String text;
    String message;
    Image image;
    Image image2;
    Image disabledImage;
    ImageList imageList;
    boolean ignoreMouse;
    boolean grayed;
    static final int MARGIN = 4;
    static final int CHECK_WIDTH;
    static final int CHECK_HEIGHT;
    static final int ICON_WIDTH = 128;
    static final int ICON_HEIGHT = 128;
    static boolean COMMAND_LINK;
    static final int ButtonProc;
    static final TCHAR ButtonClass;
    
    static {
        Button.COMMAND_LINK = false;
        ButtonClass = new TCHAR(0, "BUTTON", true);
        final int loadBitmap = OS.LoadBitmap(0, 32759);
        if (loadBitmap == 0) {
            CHECK_WIDTH = OS.GetSystemMetrics(OS.IsWinCE ? 49 : 2);
            CHECK_HEIGHT = OS.GetSystemMetrics(OS.IsWinCE ? 50 : 20);
        }
        else {
            final BITMAP bitmap = new BITMAP();
            OS.GetObject(loadBitmap, BITMAP.sizeof, bitmap);
            OS.DeleteObject(loadBitmap);
            CHECK_WIDTH = bitmap.bmWidth / 4;
            CHECK_HEIGHT = bitmap.bmHeight / 3;
        }
        final WNDCLASS wndclass = new WNDCLASS();
        OS.GetClassInfo(0, Button.ButtonClass, wndclass);
        ButtonProc = wndclass.lpfnWndProc;
    }
    
    public Button(final Composite composite, final int n) {
        super(composite, checkStyle(n));
        this.text = "";
        this.message = "";
    }
    
    void _setImage(final Image image) {
        if ((this.style & 0x400000) != 0x0) {
            return;
        }
        if (OS.COMCTL32_MAJOR >= 6) {
            if (this.imageList != null) {
                this.imageList.dispose();
            }
            this.imageList = null;
            if (image != null) {
                this.imageList = new ImageList(0);
                if (OS.IsWindowEnabled(this.handle)) {
                    this.imageList.add(image);
                }
                else {
                    if (this.disabledImage != null) {
                        this.disabledImage.dispose();
                    }
                    this.disabledImage = new Image(this.display, image, 1);
                    this.imageList.add(this.disabledImage);
                }
                final BUTTON_IMAGELIST button_IMAGELIST = new BUTTON_IMAGELIST();
                button_IMAGELIST.himl = this.imageList.getHandle();
                final int getWindowLong = OS.GetWindowLong(this.handle, -16);
                int n = getWindowLong & 0xFFFFFCFF;
                if ((this.style & 0x4000) != 0x0) {
                    n |= 0x100;
                }
                if ((this.style & 0x1000000) != 0x0) {
                    n |= 0x300;
                }
                if ((this.style & 0x20000) != 0x0) {
                    n |= 0x200;
                }
                if (this.text.length() == 0) {
                    if ((this.style & 0x4000) != 0x0) {
                        button_IMAGELIST.uAlign = 0;
                    }
                    if ((this.style & 0x1000000) != 0x0) {
                        button_IMAGELIST.uAlign = 4;
                    }
                    if ((this.style & 0x20000) != 0x0) {
                        button_IMAGELIST.uAlign = 1;
                    }
                }
                else {
                    button_IMAGELIST.uAlign = 0;
                    button_IMAGELIST.margin_left = this.computeLeftMargin();
                    button_IMAGELIST.margin_right = 4;
                    n = ((n & 0xFFFFFCFF) | 0x100);
                }
                if (n != getWindowLong) {
                    OS.SetWindowLong(this.handle, -16, n);
                    OS.InvalidateRect(this.handle, null, true);
                }
                OS.SendMessage(this.handle, 5634, 0, button_IMAGELIST);
            }
            else {
                OS.SendMessage(this.handle, 5634, 0, 0);
            }
            OS.InvalidateRect(this.handle, null, true);
        }
        else {
            if (this.image2 != null) {
                this.image2.dispose();
            }
            this.image2 = null;
            int n2 = 0;
            int n3 = 0;
            int n4 = 0;
            if (image != null) {
                switch (image.type) {
                    case 0: {
                        final Rectangle bounds = image.getBounds();
                        final ImageData imageData = image.getImageData();
                        switch (imageData.getTransparencyType()) {
                            case 4: {
                                if (bounds.width <= 128 && bounds.height <= 128) {
                                    this.image2 = new Image(this.display, imageData, imageData.getTransparencyMask());
                                    n2 = this.image2.handle;
                                    n3 = 64;
                                    n4 = 1;
                                    break;
                                }
                            }
                            case 1: {
                                this.image2 = new Image(this.display, bounds.width, bounds.height);
                                final GC gc = new GC(this.image2);
                                gc.setBackground(this.getBackground());
                                gc.fillRectangle(bounds);
                                gc.drawImage(image, 0, 0);
                                gc.dispose();
                                n2 = this.image2.handle;
                                n3 = 128;
                                n4 = 0;
                                break;
                            }
                            case 0: {
                                n2 = image.handle;
                                n3 = 128;
                                n4 = 0;
                                break;
                            }
                        }
                        break;
                    }
                    case 1: {
                        n2 = image.handle;
                        n3 = 64;
                        n4 = 1;
                        break;
                    }
                }
                if ((this.style & 0x4000000) != 0x0 && !OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
                    final Rectangle bounds2 = image.getBounds();
                    final int getDC = OS.GetDC(this.handle);
                    final int createCompatibleDC = OS.CreateCompatibleDC(getDC);
                    final int createCompatibleBitmap = OS.CreateCompatibleBitmap(getDC, bounds2.width, bounds2.height);
                    final int selectObject = OS.SelectObject(createCompatibleDC, createCompatibleBitmap);
                    OS.SetLayout(createCompatibleDC, 1);
                    if (n4 == 0) {
                        final int createCompatibleDC2 = OS.CreateCompatibleDC(getDC);
                        final int selectObject2 = OS.SelectObject(createCompatibleDC2, n2);
                        OS.SetLayout(createCompatibleDC, 0);
                        OS.BitBlt(createCompatibleDC, 0, 0, bounds2.width, bounds2.height, createCompatibleDC2, 0, 0, 13369376);
                        OS.SelectObject(createCompatibleDC2, selectObject2);
                        OS.DeleteDC(createCompatibleDC2);
                    }
                    else {
                        Control backgroundControl = this.findBackgroundControl();
                        if (backgroundControl == null) {
                            backgroundControl = this;
                        }
                        final int createSolidBrush = OS.CreateSolidBrush(backgroundControl.getBackgroundPixel());
                        final int selectObject3 = OS.SelectObject(createCompatibleDC, createSolidBrush);
                        OS.PatBlt(createCompatibleDC, 0, 0, bounds2.width, bounds2.height, 15728673);
                        OS.DrawIconEx(createCompatibleDC, 0, 0, n2, 0, 0, 0, 0, 3);
                        OS.SelectObject(createCompatibleDC, selectObject3);
                        OS.DeleteObject(createSolidBrush);
                    }
                    OS.SelectObject(createCompatibleDC, selectObject);
                    OS.DeleteDC(createCompatibleDC);
                    OS.ReleaseDC(this.handle, getDC);
                    if (this.image2 != null) {
                        this.image2.dispose();
                    }
                    this.image2 = Image.win32_new(this.display, 0, createCompatibleBitmap);
                    n3 = 128;
                    n4 = 0;
                    n2 = createCompatibleBitmap;
                }
            }
            final int getWindowLong2;
            final int n5 = ((getWindowLong2 = OS.GetWindowLong(this.handle, -16)) & 0xFFFFFF3F) | n3;
            if (n5 != getWindowLong2) {
                OS.SetWindowLong(this.handle, -16, n5);
            }
            OS.SendMessage(this.handle, 247, n4, n2);
        }
    }
    
    void _setText(String s) {
        final int getWindowLong;
        final int n = getWindowLong = OS.GetWindowLong(this.handle, -16);
        int n2;
        if (OS.COMCTL32_MAJOR >= 6) {
            n2 = (getWindowLong & 0xFFFFFCFF);
            if ((this.style & 0x4000) != 0x0) {
                n2 |= 0x100;
            }
            if ((this.style & 0x1000000) != 0x0) {
                n2 |= 0x300;
            }
            if ((this.style & 0x20000) != 0x0) {
                n2 |= 0x200;
            }
            if (this.imageList != null) {
                final BUTTON_IMAGELIST button_IMAGELIST = new BUTTON_IMAGELIST();
                button_IMAGELIST.himl = this.imageList.getHandle();
                if (s.length() == 0) {
                    if ((this.style & 0x4000) != 0x0) {
                        button_IMAGELIST.uAlign = 0;
                    }
                    if ((this.style & 0x1000000) != 0x0) {
                        button_IMAGELIST.uAlign = 4;
                    }
                    if ((this.style & 0x20000) != 0x0) {
                        button_IMAGELIST.uAlign = 1;
                    }
                }
                else {
                    button_IMAGELIST.uAlign = 0;
                    button_IMAGELIST.margin_left = this.computeLeftMargin();
                    button_IMAGELIST.margin_right = 4;
                    n2 = ((n2 & 0xFFFFFCFF) | 0x100);
                }
                OS.SendMessage(this.handle, 5634, 0, button_IMAGELIST);
            }
        }
        else {
            n2 = (getWindowLong & 0xFFFFFF3F);
        }
        if (n2 != n) {
            OS.SetWindowLong(this.handle, -16, n2);
            OS.InvalidateRect(this.handle, null, true);
        }
        if ((this.style & 0x4000000) != 0x0 && (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed())) {
            s = (OS.IsWindowEnabled(this.handle) ? s : (" " + s + " "));
        }
        OS.SetWindowText(this.handle, new TCHAR(this.getCodePage(), s, true));
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
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        return OS.CallWindowProc(Button.ButtonProc, n, n2, n3, n4);
    }
    
    static int checkStyle(int checkBits) {
        checkBits = Widget.checkBits(checkBits, 8, 4, 32, 16, 2, Button.COMMAND_LINK ? 4194304 : 0);
        if ((checkBits & 0xA) != 0x0) {
            return Widget.checkBits(checkBits, 16777216, 16384, 131072, 0, 0, 0);
        }
        if ((checkBits & 0x30) != 0x0) {
            return Widget.checkBits(checkBits, 16384, 131072, 16777216, 0, 0, 0);
        }
        if ((checkBits & 0x4) != 0x0) {
            checkBits |= 0x80000;
            return Widget.checkBits(checkBits, 128, 1024, 16384, 131072, 0, 0);
        }
        return checkBits;
    }
    
    void click() {
        this.ignoreMouse = true;
        OS.SendMessage(this.handle, 245, 0, 0);
        this.ignoreMouse = false;
    }
    
    int computeLeftMargin() {
        if (OS.COMCTL32_MAJOR < 6) {
            return 4;
        }
        if ((this.style & 0xA) == 0x0) {
            return 4;
        }
        int max = 0;
        if (this.image != null && this.text.length() != 0) {
            final int n = max + (this.image.getBounds().width + 8);
            int selectObject = 0;
            final int getDC = OS.GetDC(this.handle);
            final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
            if (sendMessage != 0) {
                selectObject = OS.SelectObject(getDC, sendMessage);
            }
            final TCHAR tchar = new TCHAR(this.getCodePage(), this.text, true);
            final RECT rect = new RECT();
            OS.DrawText(getDC, tchar, -1, rect, 1056);
            final int n2 = n + (rect.right - rect.left);
            if (sendMessage != 0) {
                OS.SelectObject(getDC, selectObject);
            }
            OS.ReleaseDC(this.handle, getDC);
            OS.GetClientRect(this.handle, rect);
            max = Math.max(4, (rect.right - rect.left - n2) / 2);
        }
        return max;
    }
    
    public Point computeSize(final int cx, final int n, final boolean b) {
        this.checkWidget();
        int n2 = 0;
        int n3 = 0;
        final int borderWidth = this.getBorderWidth();
        if ((this.style & 0x4) != 0x0) {
            if ((this.style & 0x480) != 0x0) {
                n2 += OS.GetSystemMetrics(2);
                n3 += OS.GetSystemMetrics(20);
            }
            else {
                n2 += OS.GetSystemMetrics(21);
                n3 += OS.GetSystemMetrics(3);
            }
        }
        else if ((this.style & 0x400000) != 0x0) {
            final SIZE size = new SIZE();
            if (cx != -1) {
                size.cx = cx;
                OS.SendMessage(this.handle, 5633, 0, size);
                n2 = size.cx;
                n3 = size.cy;
            }
            else {
                OS.SendMessage(this.handle, 5633, 0, size);
                n2 = size.cy;
                n3 = size.cy;
                size.cy = 0;
                while (size.cy != n3) {
                    size.cx = n2++;
                    size.cy = 0;
                    OS.SendMessage(this.handle, 5633, 0, size);
                }
            }
        }
        else {
            int max = 0;
            boolean b2 = this.image != null;
            boolean b3 = true;
            if (OS.COMCTL32_MAJOR < 6 && (this.style & 0x8) == 0x0) {
                b2 = ((OS.GetWindowLong(this.handle, -16) & 0xC0) != 0x0);
                if (b2) {
                    b3 = false;
                }
            }
            if (b2 && this.image != null) {
                final Rectangle bounds = this.image.getBounds();
                n2 = bounds.width;
                if (b3 && this.text.length() != 0) {
                    n2 += 8;
                }
                n3 = bounds.height;
                max = 8;
            }
            if (b3) {
                int selectObject = 0;
                final int getDC = OS.GetDC(this.handle);
                final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
                if (sendMessage != 0) {
                    selectObject = OS.SelectObject(getDC, sendMessage);
                }
                final TEXTMETRIC textmetric = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
                OS.GetTextMetrics(getDC, textmetric);
                if (this.text.length() == 0) {
                    n3 = Math.max(n3, textmetric.tmHeight);
                }
                else {
                    max = Math.max(8, textmetric.tmAveCharWidth);
                    final TCHAR tchar = new TCHAR(this.getCodePage(), this.text, true);
                    final RECT rect = new RECT();
                    int n4 = 1056;
                    if ((this.style & 0x40) != 0x0 && cx != -1) {
                        n4 = 1040;
                        rect.right = cx - n2 - 2 * borderWidth;
                        if ((this.style & 0x30) != 0x0) {
                            final RECT rect2 = rect;
                            rect2.right -= Button.CHECK_WIDTH + 3;
                        }
                        else {
                            final RECT rect3 = rect;
                            rect3.right -= 6;
                        }
                        if (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed()) {
                            final RECT rect4 = rect;
                            rect4.right -= 2;
                            if ((this.style & 0x30) != 0x0) {
                                final RECT rect5 = rect;
                                rect5.right -= 2;
                            }
                        }
                    }
                    OS.DrawText(getDC, tchar, -1, rect, n4);
                    n2 += rect.right - rect.left;
                    n3 = Math.max(n3, rect.bottom - rect.top);
                }
                if (sendMessage != 0) {
                    OS.SelectObject(getDC, selectObject);
                }
                OS.ReleaseDC(this.handle, getDC);
            }
            if ((this.style & 0x30) != 0x0) {
                n2 += Button.CHECK_WIDTH + max;
                n3 = Math.max(n3, Button.CHECK_HEIGHT + 3);
            }
            if ((this.style & 0xA) != 0x0) {
                n2 += 12;
                n3 += 10;
            }
        }
        if (cx != -1) {
            n2 = cx;
        }
        if (n != -1) {
            n3 = n;
        }
        return new Point(n2 + borderWidth * 2, n3 + borderWidth * 2);
    }
    
    void createHandle() {
        final Composite parent = this.parent;
        parent.state |= 0x100000;
        super.createHandle();
        final Composite parent2 = this.parent;
        parent2.state &= 0xFFEFFFFF;
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            if (!OS.IsWinCE && OS.WIN32_VERSION < OS.VERSION(6, 0)) {
                this.state |= 0x100;
            }
            else if ((this.style & 0xA) == 0x0) {
                this.state |= 0x100;
            }
        }
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && (this.style & 0x10) != 0x0) {
            this.state |= 0x200;
        }
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && !OS.IsWinCE && OS.WIN32_VERSION < OS.VERSION(6, 0) && (this.style & 0xA) != 0x0) {
            this.state |= 0x200;
        }
    }
    
    int defaultBackground() {
        if ((this.style & 0xA) != 0x0) {
            return OS.GetSysColor(OS.COLOR_BTNFACE);
        }
        return super.defaultBackground();
    }
    
    int defaultForeground() {
        return OS.GetSysColor(OS.COLOR_BTNTEXT);
    }
    
    void enableWidget(final boolean b) {
        super.enableWidget(b);
        if ((this.style & 0x4000000) != 0x0 && (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed()) && (OS.GetWindowLong(this.handle, -16) & 0xC0) == 0x0) {
            OS.SetWindowText(this.handle, new TCHAR(this.getCodePage(), b ? this.text : (" " + this.text + " "), true));
        }
        if (OS.COMCTL32_MAJOR >= 6 && this.imageList != null) {
            final BUTTON_IMAGELIST button_IMAGELIST = new BUTTON_IMAGELIST();
            OS.SendMessage(this.handle, 5635, 0, button_IMAGELIST);
            if (this.imageList != null) {
                this.imageList.dispose();
            }
            this.imageList = new ImageList(0);
            if (OS.IsWindowEnabled(this.handle)) {
                this.imageList.add(this.image);
            }
            else {
                if (this.disabledImage != null) {
                    this.disabledImage.dispose();
                }
                this.disabledImage = new Image(this.display, this.image, 1);
                this.imageList.add(this.disabledImage);
            }
            button_IMAGELIST.himl = this.imageList.getHandle();
            OS.SendMessage(this.handle, 5634, 0, button_IMAGELIST);
            OS.InvalidateRect(this.handle, null, true);
        }
    }
    
    public int getAlignment() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            if ((this.style & 0x80) != 0x0) {
                return 128;
            }
            if ((this.style & 0x400) != 0x0) {
                return 1024;
            }
            if ((this.style & 0x4000) != 0x0) {
                return 16384;
            }
            if ((this.style & 0x20000) != 0x0) {
                return 131072;
            }
            return 128;
        }
        else {
            if ((this.style & 0x4000) != 0x0) {
                return 16384;
            }
            if ((this.style & 0x1000000) != 0x0) {
                return 16777216;
            }
            if ((this.style & 0x20000) != 0x0) {
                return 131072;
            }
            return 16384;
        }
    }
    
    boolean getDefault() {
        return (this.style & 0x8) != 0x0 && (OS.GetWindowLong(this.handle, -16) & 0x1) != 0x0;
    }
    
    public boolean getGrayed() {
        this.checkWidget();
        return (this.style & 0x20) != 0x0 && this.grayed;
    }
    
    public Image getImage() {
        this.checkWidget();
        return this.image;
    }
    
    String getMessage() {
        this.checkWidget();
        return this.message;
    }
    
    String getNameText() {
        return this.getText();
    }
    
    public boolean getSelection() {
        this.checkWidget();
        return (this.style & 0x32) != 0x0 && OS.SendMessage(this.handle, 240, 0, 0) != 0;
    }
    
    public String getText() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            return "";
        }
        return this.text;
    }
    
    boolean isTabItem() {
        if ((this.style & 0x8) != 0x0) {
            return this.isTabGroup();
        }
        return super.isTabItem();
    }
    
    boolean mnemonicHit(final char c) {
        if (!this.setFocus()) {
            return false;
        }
        if ((this.style & 0x10) == 0x0) {
            this.click();
        }
        return true;
    }
    
    boolean mnemonicMatch(final char c) {
        final char mnemonic = this.findMnemonic(this.getText());
        return mnemonic != '\0' && Character.toUpperCase(c) == Character.toUpperCase(mnemonic);
    }
    
    void releaseWidget() {
        super.releaseWidget();
        if (this.imageList != null) {
            this.imageList.dispose();
        }
        this.imageList = null;
        if (this.disabledImage != null) {
            this.disabledImage.dispose();
        }
        this.disabledImage = null;
        if (this.image2 != null) {
            this.image2.dispose();
        }
        this.image2 = null;
        this.text = null;
        this.image = null;
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
    
    void selectRadio() {
        final Control[] getChildren = this.parent._getChildren();
        for (int i = 0; i < getChildren.length; ++i) {
            final Control control = getChildren[i];
            if (this != control) {
                control.setRadioSelection(false);
            }
        }
        this.setSelection(true);
    }
    
    public void setAlignment(final int n) {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            if ((this.style & 0x24480) == 0x0) {
                return;
            }
            this.style &= 0xFFFDBB7F;
            this.style |= (n & 0x24480);
            OS.InvalidateRect(this.handle, null, true);
        }
        else {
            if ((n & 0x1024000) == 0x0) {
                return;
            }
            this.style &= 0xFEFDBFFF;
            this.style |= (n & 0x1024000);
            final int getWindowLong = OS.GetWindowLong(this.handle, -16);
            int n2 = getWindowLong & 0xFFFFFCFF;
            if ((this.style & 0x4000) != 0x0) {
                n2 |= 0x100;
            }
            if ((this.style & 0x1000000) != 0x0) {
                n2 |= 0x300;
            }
            if ((this.style & 0x20000) != 0x0) {
                n2 |= 0x200;
            }
            if (OS.COMCTL32_MAJOR >= 6 && this.imageList != null) {
                final BUTTON_IMAGELIST button_IMAGELIST = new BUTTON_IMAGELIST();
                button_IMAGELIST.himl = this.imageList.getHandle();
                if (this.text.length() == 0) {
                    if ((this.style & 0x4000) != 0x0) {
                        button_IMAGELIST.uAlign = 0;
                    }
                    if ((this.style & 0x1000000) != 0x0) {
                        button_IMAGELIST.uAlign = 4;
                    }
                    if ((this.style & 0x20000) != 0x0) {
                        button_IMAGELIST.uAlign = 1;
                    }
                }
                else {
                    button_IMAGELIST.uAlign = 0;
                    button_IMAGELIST.margin_left = this.computeLeftMargin();
                    button_IMAGELIST.margin_right = 4;
                    n2 = ((n2 & 0xFFFFFCFF) | 0x100);
                }
                OS.SendMessage(this.handle, 5634, 0, button_IMAGELIST);
            }
            if (n2 != getWindowLong) {
                OS.SetWindowLong(this.handle, -16, n2);
                OS.InvalidateRect(this.handle, null, true);
            }
        }
    }
    
    void setDefault(final boolean b) {
        if ((this.style & 0x8) == 0x0) {
            return;
        }
        final int handle = this.menuShell().handle;
        final int getWindowLong = OS.GetWindowLong(this.handle, -16);
        int n;
        if (b) {
            n = (getWindowLong | 0x1);
            OS.SendMessage(handle, 1025, this.handle, 0);
        }
        else {
            n = (getWindowLong & 0xFFFFFFFE);
            OS.SendMessage(handle, 1025, 0, 0);
        }
        OS.SendMessage(this.handle, 244, n, 1);
    }
    
    public boolean setFocus() {
        this.checkWidget();
        return ((this.style & 0x10) == 0x0 || this.getSelection() || !this.display.fixFocus) && super.setFocus();
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        if ((this.style & 0x4) != 0x0) {
            return;
        }
        this._setImage(this.image = image);
    }
    
    public void setGrayed(final boolean grayed) {
        this.checkWidget();
        if ((this.style & 0x20) == 0x0) {
            return;
        }
        this.grayed = grayed;
        final int sendMessage = OS.SendMessage(this.handle, 240, 0, 0);
        if (grayed) {
            if (sendMessage == 1) {
                this.updateSelection(2);
            }
        }
        else if (sendMessage == 2) {
            this.updateSelection(1);
        }
    }
    
    void setMessage(final String message) {
        this.checkWidget();
        if (message == null) {
            this.error(4);
        }
        this.message = message;
        if (OS.COMCTL32_VERSION >= OS.VERSION(6, 1) && (this.style & 0x400000) != 0x0) {
            final int length = message.length();
            final char[] array = new char[length + 1];
            message.getChars(0, length, array, 0);
            OS.SendMessage(this.handle, 5641, 0, array);
        }
    }
    
    boolean setRadioFocus(final boolean b) {
        return (this.style & 0x10) != 0x0 && this.getSelection() && (b ? this.setTabItemFocus() : this.setFocus());
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
    
    boolean setSavedFocus() {
        return ((this.style & 0x10) == 0x0 || this.getSelection()) && super.setSavedFocus();
    }
    
    public void setSelection(final boolean b) {
        this.checkWidget();
        if ((this.style & 0x32) == 0x0) {
            return;
        }
        int n = b ? 1 : 0;
        if ((this.style & 0x20) != 0x0 && b && this.grayed) {
            n = 2;
        }
        this.updateSelection(n);
    }
    
    public void setText(final String text) {
        this.checkWidget();
        if (text == null) {
            this.error(4);
        }
        if ((this.style & 0x4) != 0x0) {
            return;
        }
        this._setText(this.text = text);
    }
    
    void updateSelection(final int n) {
        if (n != OS.SendMessage(this.handle, 240, 0, 0)) {
            int getWindowLong = OS.GetWindowLong(this.handle, -16);
            if ((this.style & 0x20) != 0x0) {
                if (n == 2) {
                    getWindowLong = ((getWindowLong & 0xFFFFFFFD) | 0x5);
                }
                else {
                    getWindowLong = ((getWindowLong | 0x2) & 0xFFFFFFFA);
                }
                if (getWindowLong != OS.GetWindowLong(this.handle, -16)) {
                    OS.SetWindowLong(this.handle, -16, getWindowLong);
                }
            }
            OS.SendMessage(this.handle, 241, n, 0);
            if (getWindowLong != OS.GetWindowLong(this.handle, -16)) {
                OS.SetWindowLong(this.handle, -16, getWindowLong);
            }
        }
    }
    
    int widgetStyle() {
        int widgetStyle = super.widgetStyle();
        if ((this.style & 0x800000) != 0x0) {
            widgetStyle |= 0x8000;
        }
        if ((this.style & 0x4) != 0x0) {
            return widgetStyle | 0xB;
        }
        if ((this.style & 0x4000) != 0x0) {
            widgetStyle |= 0x100;
        }
        if ((this.style & 0x1000000) != 0x0) {
            widgetStyle |= 0x300;
        }
        if ((this.style & 0x20000) != 0x0) {
            widgetStyle |= 0x200;
        }
        if ((this.style & 0x40) != 0x0) {
            widgetStyle |= 0x2000;
        }
        if ((this.style & 0x8) != 0x0) {
            return widgetStyle | 0x10000;
        }
        if ((this.style & 0x20) != 0x0) {
            return widgetStyle | 0x2 | 0x10000;
        }
        if ((this.style & 0x10) != 0x0) {
            return widgetStyle | 0x4;
        }
        if ((this.style & 0x2) != 0x0) {
            return widgetStyle | 0x1000 | 0x2 | 0x10000;
        }
        if ((this.style & 0x400000) != 0x0) {
            return widgetStyle | 0xE | 0x10000;
        }
        return widgetStyle | 0x10000;
    }
    
    TCHAR windowClass() {
        return Button.ButtonClass;
    }
    
    int windowProc() {
        return Button.ButtonProc;
    }
    
    LRESULT WM_ERASEBKGND(final int n, final int n2) {
        final LRESULT wm_ERASEBKGND = super.WM_ERASEBKGND(n, n2);
        if (wm_ERASEBKGND != null) {
            return wm_ERASEBKGND;
        }
        if (OS.COMCTL32_MAJOR < 6 && (this.style & 0x30) != 0x0 && this.findImageControl() != null) {
            this.drawBackground(n);
            return LRESULT.ONE;
        }
        return wm_ERASEBKGND;
    }
    
    LRESULT WM_GETDLGCODE(final int n, final int n2) {
        final LRESULT wm_GETDLGCODE = super.WM_GETDLGCODE(n, n2);
        if (wm_GETDLGCODE != null) {
            return wm_GETDLGCODE;
        }
        if ((this.style & 0x4) != 0x0) {
            return new LRESULT(256);
        }
        return wm_GETDLGCODE;
    }
    
    LRESULT WM_KILLFOCUS(final int n, final int n2) {
        final LRESULT wm_KILLFOCUS = super.WM_KILLFOCUS(n, n2);
        if ((this.style & 0x8) != 0x0 && this.getDefault()) {
            this.menuShell().setDefaultButton(null, false);
        }
        return wm_KILLFOCUS;
    }
    
    LRESULT WM_LBUTTONDOWN(final int n, final int n2) {
        if (this.ignoreMouse) {
            return null;
        }
        return super.WM_LBUTTONDOWN(n, n2);
    }
    
    LRESULT WM_LBUTTONUP(final int n, final int n2) {
        if (this.ignoreMouse) {
            return null;
        }
        return super.WM_LBUTTONUP(n, n2);
    }
    
    LRESULT WM_SETFOCUS(final int n, final int n2) {
        int getWindowLong = 0;
        if ((this.style & 0x10) != 0x0) {
            getWindowLong = OS.GetWindowLong(this.handle, -16);
        }
        final LRESULT wm_SETFOCUS = super.WM_SETFOCUS(n, n2);
        if ((this.style & 0x10) != 0x0) {
            OS.SetWindowLong(this.handle, -16, getWindowLong);
        }
        if ((this.style & 0x8) != 0x0) {
            this.menuShell().setDefaultButton(this, false);
        }
        return wm_SETFOCUS;
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        final LRESULT wm_SIZE = super.WM_SIZE(n, n2);
        if (wm_SIZE != null) {
            return wm_SIZE;
        }
        if (OS.COMCTL32_MAJOR >= 6 && (this.style & 0xA) != 0x0 && this.imageList != null && this.text.length() != 0) {
            final BUTTON_IMAGELIST button_IMAGELIST = new BUTTON_IMAGELIST();
            OS.SendMessage(this.handle, 5635, 0, button_IMAGELIST);
            button_IMAGELIST.uAlign = 0;
            button_IMAGELIST.margin_left = this.computeLeftMargin();
            button_IMAGELIST.margin_right = 4;
            OS.SendMessage(this.handle, 5634, 0, button_IMAGELIST);
        }
        return wm_SIZE;
    }
    
    LRESULT WM_SYSCOLORCHANGE(final int n, final int n2) {
        final LRESULT wm_SYSCOLORCHANGE = super.WM_SYSCOLORCHANGE(n, n2);
        if (wm_SYSCOLORCHANGE != null) {
            return wm_SYSCOLORCHANGE;
        }
        if (this.image2 != null) {
            this._setImage(this.image);
        }
        return wm_SYSCOLORCHANGE;
    }
    
    LRESULT WM_UPDATEUISTATE(final int n, final int n2) {
        final LRESULT wm_UPDATEUISTATE = super.WM_UPDATEUISTATE(n, n2);
        if (wm_UPDATEUISTATE != null) {
            return wm_UPDATEUISTATE;
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0) && (this.style & 0x32) != 0x0) {
            boolean b = this.findImageControl() != null;
            if (!b) {
                if ((this.state & 0x100) != 0x0 && OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
                    b = (this.findThemeControl() != null);
                }
                if (!b) {
                    b = (this.findBackgroundControl() != null);
                }
            }
            if (b) {
                OS.InvalidateRect(this.handle, null, false);
                return new LRESULT(OS.DefWindowProc(this.handle, 296, n, n2));
            }
        }
        if ((this.style & 0xA) != 0x0 && (this.hooks(9) || this.filters(9))) {
            OS.InvalidateRect(this.handle, null, true);
        }
        return wm_UPDATEUISTATE;
    }
    
    LRESULT wmCommandChild(final int n, final int n2) {
        switch (OS.HIWORD(n)) {
            case 0:
            case 5: {
                if ((this.style & 0x22) != 0x0) {
                    this.setSelection(!this.getSelection());
                }
                else if ((this.style & 0x10) != 0x0) {
                    if ((this.parent.getStyle() & 0x400000) != 0x0) {
                        this.setSelection(!this.getSelection());
                    }
                    else {
                        this.selectRadio();
                    }
                }
                this.sendSelectionEvent(13);
                break;
            }
        }
        return super.wmCommandChild(n, n2);
    }
    
    LRESULT wmColorChild(final int n, final int n2) {
        final LRESULT wmColorChild = super.wmColorChild(n, n2);
        if (OS.COMCTL32_MAJOR < 6 && (this.style & 0x30) != 0x0 && this.findImageControl() != null) {
            OS.SetBkMode(n, 1);
            return new LRESULT(OS.GetStockObject(5));
        }
        return wmColorChild;
    }
    
    LRESULT wmDrawChild(final int n, final int n2) {
        if ((this.style & 0x4) == 0x0) {
            return super.wmDrawChild(n, n2);
        }
        final DRAWITEMSTRUCT drawitemstruct = new DRAWITEMSTRUCT();
        OS.MoveMemory(drawitemstruct, n2, DRAWITEMSTRUCT.sizeof);
        final RECT rect = new RECT();
        OS.SetRect(rect, drawitemstruct.left, drawitemstruct.top, drawitemstruct.right, drawitemstruct.bottom);
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            int n3 = 9;
            switch (this.style & 0x24480) {
                case 128: {
                    n3 = 1;
                    break;
                }
                case 1024: {
                    n3 = 5;
                    break;
                }
                case 16384: {
                    n3 = 9;
                    break;
                }
                case 131072: {
                    n3 = 13;
                    break;
                }
            }
            if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0) && (this.style & 0x8000000) != 0x0 && (this.style & 0x24000) != 0x0) {
                n3 = ((n3 == 13) ? 9 : 13);
            }
            if (!this.getEnabled()) {
                n3 += 3;
            }
            if ((drawitemstruct.itemState & 0x1) != 0x0) {
                n3 += 2;
            }
            OS.DrawThemeBackground(this.display.hScrollBarTheme(), drawitemstruct.hDC, 1, n3, rect, null);
        }
        else {
            int n4 = 2;
            switch (this.style & 0x24480) {
                case 128: {
                    n4 = 0;
                    break;
                }
                case 1024: {
                    n4 = 1;
                    break;
                }
                case 16384: {
                    n4 = 2;
                    break;
                }
                case 131072: {
                    n4 = 3;
                    break;
                }
            }
            if (!this.getEnabled()) {
                n4 |= 0x100;
            }
            if ((this.style & 0x800000) == 0x800000) {
                n4 |= 0x4000;
            }
            if ((drawitemstruct.itemState & 0x1) != 0x0) {
                n4 |= 0x200;
            }
            OS.DrawFrameControl(drawitemstruct.hDC, rect, 3, n4);
        }
        return null;
    }
}
