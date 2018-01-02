// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.BITMAPINFOHEADER;
import org.eclipse.swt.internal.win32.BITMAP;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.SHDRAGIMAGE;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.graphics.Image;

public class TreeDragSourceEffect extends DragSourceEffect
{
    Image dragSourceImage;
    
    public TreeDragSourceEffect(final Tree tree) {
        super(tree);
        this.dragSourceImage = null;
    }
    
    public void dragFinished(final DragSourceEvent dragSourceEvent) {
        if (this.dragSourceImage != null) {
            this.dragSourceImage.dispose();
        }
        this.dragSourceImage = null;
    }
    
    public void dragStart(final DragSourceEvent dragSourceEvent) {
        dragSourceEvent.image = this.getDragSourceImage(dragSourceEvent);
    }
    
    Image getDragSourceImage(final DragSourceEvent dragSourceEvent) {
        if (this.dragSourceImage != null) {
            this.dragSourceImage.dispose();
        }
        this.dragSourceImage = null;
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(5, 1)) {
            final SHDRAGIMAGE shdragimage = new SHDRAGIMAGE();
            if (OS.SendMessage(this.control.handle, OS.RegisterWindowMessage(new TCHAR(0, "ShellGetDragImage", true)), 0, shdragimage) != 0) {
                if ((this.control.getStyle() & 0x8000000) != 0x0) {
                    dragSourceEvent.offsetX = shdragimage.sizeDragImage.cx - shdragimage.ptOffset.x;
                }
                else {
                    dragSourceEvent.offsetX = shdragimage.ptOffset.x;
                }
                dragSourceEvent.offsetY = shdragimage.ptOffset.y;
                final int hbmpDragImage = shdragimage.hbmpDragImage;
                if (hbmpDragImage != 0) {
                    final BITMAP bitmap = new BITMAP();
                    OS.GetObject(hbmpDragImage, BITMAP.sizeof, bitmap);
                    final int bmWidth = bitmap.bmWidth;
                    final int bmHeight = bitmap.bmHeight;
                    final int getDC = OS.GetDC(0);
                    final int createCompatibleDC = OS.CreateCompatibleDC(getDC);
                    final int selectObject = OS.SelectObject(createCompatibleDC, hbmpDragImage);
                    final int createCompatibleDC2 = OS.CreateCompatibleDC(getDC);
                    final BITMAPINFOHEADER bitmapinfoheader = new BITMAPINFOHEADER();
                    bitmapinfoheader.biSize = BITMAPINFOHEADER.sizeof;
                    bitmapinfoheader.biWidth = bmWidth;
                    bitmapinfoheader.biHeight = -bmHeight;
                    bitmapinfoheader.biPlanes = 1;
                    bitmapinfoheader.biBitCount = 32;
                    bitmapinfoheader.biCompression = 0;
                    final byte[] array = new byte[BITMAPINFOHEADER.sizeof];
                    OS.MoveMemory(array, bitmapinfoheader, BITMAPINFOHEADER.sizeof);
                    final int createDIBSection = OS.CreateDIBSection(0, array, 0, new int[1], 0, 0);
                    if (createDIBSection == 0) {
                        SWT.error(2);
                    }
                    final int selectObject2 = OS.SelectObject(createCompatibleDC2, createDIBSection);
                    final BITMAP bitmap2 = new BITMAP();
                    OS.GetObject(createDIBSection, BITMAP.sizeof, bitmap2);
                    final int n = bitmap2.bmWidthBytes * bitmap2.bmHeight;
                    OS.BitBlt(createCompatibleDC2, 0, 0, bmWidth, bmHeight, createCompatibleDC, 0, 0, 13369376);
                    final byte[] array2 = new byte[n];
                    OS.MoveMemory(array2, bitmap2.bmBits, n);
                    final ImageData imageData = new ImageData(bmWidth, bmHeight, bitmap.bmBitsPixel, new PaletteData(65280, 16711680, -16777216), bitmap.bmWidthBytes, array2);
                    if (shdragimage.crColorKey == -1) {
                        final byte[] alphaData = new byte[bmWidth * bmHeight];
                        final int n2 = bitmap2.bmWidthBytes - bmWidth * 4;
                        int n3 = 0;
                        int n4 = 3;
                        for (int i = 0; i < bmHeight; ++i) {
                            for (int j = 0; j < bmWidth; ++j) {
                                alphaData[n3++] = array2[n4];
                                n4 += 4;
                            }
                            n4 += n2;
                        }
                        imageData.alphaData = alphaData;
                    }
                    else {
                        imageData.transparentPixel = shdragimage.crColorKey << 8;
                    }
                    this.dragSourceImage = new Image(this.control.getDisplay(), imageData);
                    OS.SelectObject(createCompatibleDC2, selectObject2);
                    OS.DeleteDC(createCompatibleDC2);
                    OS.DeleteObject(createDIBSection);
                    OS.SelectObject(createCompatibleDC, selectObject);
                    OS.DeleteDC(createCompatibleDC);
                    OS.ReleaseDC(0, getDC);
                    OS.DeleteObject(hbmpDragImage);
                    return this.dragSourceImage;
                }
            }
            return null;
        }
        final Tree tree = (Tree)this.control;
        if (tree.isListening(40) || tree.isListening(42)) {
            return null;
        }
        final TreeItem[] selection = tree.getSelection();
        if (selection.length == 0) {
            return null;
        }
        if (OS.SendMessage(tree.handle, 4360, 0, 0) != 0) {
            final int min = Math.min(selection.length, 10);
            Rectangle rectangle = selection[0].getBounds(0);
            for (int k = 1; k < min; ++k) {
                rectangle = rectangle.union(selection[k].getBounds(0));
            }
            final int getDC2 = OS.GetDC(tree.handle);
            final int createCompatibleDC3 = OS.CreateCompatibleDC(getDC2);
            final int createCompatibleBitmap = OS.CreateCompatibleBitmap(getDC2, rectangle.width, rectangle.height);
            final int selectObject3 = OS.SelectObject(createCompatibleDC3, createCompatibleBitmap);
            final RECT rect = new RECT();
            rect.right = rectangle.width;
            rect.bottom = rectangle.height;
            OS.FillRect(createCompatibleDC3, rect, OS.GetStockObject(0));
            for (final TreeItem treeItem : selection) {
                final Rectangle bounds = treeItem.getBounds(0);
                final int sendMessage = OS.SendMessage(tree.handle, 4370, 0, treeItem.handle);
                OS.ImageList_Draw(sendMessage, 0, createCompatibleDC3, bounds.x - rectangle.x, bounds.y - rectangle.y, 4);
                OS.ImageList_Destroy(sendMessage);
            }
            OS.SelectObject(createCompatibleDC3, selectObject3);
            OS.DeleteDC(createCompatibleDC3);
            OS.ReleaseDC(tree.handle, getDC2);
            return this.dragSourceImage = Image.win32_new(tree.getDisplay(), 0, createCompatibleBitmap);
        }
        return null;
    }
}
