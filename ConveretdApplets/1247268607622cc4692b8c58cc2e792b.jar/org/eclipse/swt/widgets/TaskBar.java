// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.PROPERTYKEY;

public class TaskBar extends Widget
{
    int itemCount;
    TaskItem[] items;
    int mTaskbarList3;
    static final char[] EXE_PATH;
    static final char[] ICO_DIR;
    static final PROPERTYKEY PKEY_Title;
    static final PROPERTYKEY PKEY_AppUserModel_IsDestListSeparator;
    static final byte[] CLSID_TaskbarList;
    static final byte[] CLSID_DestinationList;
    static final byte[] CLSID_EnumerableObjectCollection;
    static final byte[] CLSID_ShellLink;
    static final byte[] CLSID_FileOperation;
    static final byte[] IID_ITaskbarList3;
    static final byte[] IID_ICustomDestinationList;
    static final byte[] IID_IObjectArray;
    static final byte[] IID_IObjectCollection;
    static final byte[] IID_IShellLinkW;
    static final byte[] IID_IPropertyStore;
    static final byte[] IID_IShellItem;
    static final byte[] IID_IFileOperation;
    static final byte[] FOLDERID_LocalAppData;
    
    static {
        ICO_DIR = new char[] { 'i', 'c', 'o', '_', 'd', 'i', 'r', '\0' };
        PKEY_Title = new PROPERTYKEY();
        PKEY_AppUserModel_IsDestListSeparator = new PROPERTYKEY();
        CLSID_TaskbarList = new byte[16];
        CLSID_DestinationList = new byte[16];
        CLSID_EnumerableObjectCollection = new byte[16];
        CLSID_ShellLink = new byte[16];
        CLSID_FileOperation = new byte[16];
        IID_ITaskbarList3 = new byte[16];
        IID_ICustomDestinationList = new byte[16];
        IID_IObjectArray = new byte[16];
        IID_IObjectCollection = new byte[16];
        IID_IShellLinkW = new byte[16];
        IID_IPropertyStore = new byte[16];
        IID_IShellItem = new byte[16];
        IID_IFileOperation = new byte[16];
        FOLDERID_LocalAppData = new byte[16];
        OS.IIDFromString("{56FDF344-FD6D-11d0-958A-006097C9A090}\u0000".toCharArray(), TaskBar.CLSID_TaskbarList);
        OS.IIDFromString("{77f10cf0-3db5-4966-b520-b7c54fd35ed6}\u0000".toCharArray(), TaskBar.CLSID_DestinationList);
        OS.IIDFromString("{2d3468c1-36a7-43b6-ac24-d3f02fd9607a}\u0000".toCharArray(), TaskBar.CLSID_EnumerableObjectCollection);
        OS.IIDFromString("{00021401-0000-0000-C000-000000000046}\u0000".toCharArray(), TaskBar.CLSID_ShellLink);
        OS.IIDFromString("{3ad05575-8857-4850-9277-11b85bdb8e09}\u0000".toCharArray(), TaskBar.CLSID_FileOperation);
        OS.IIDFromString("{EA1AFB91-9E28-4B86-90E9-9E9F8A5EEFAF}\u0000".toCharArray(), TaskBar.IID_ITaskbarList3);
        OS.IIDFromString("{6332debf-87b5-4670-90c0-5e57b408a49e}\u0000".toCharArray(), TaskBar.IID_ICustomDestinationList);
        OS.IIDFromString("{92CA9DCD-5622-4bba-A805-5E9F541BD8C9}\u0000".toCharArray(), TaskBar.IID_IObjectArray);
        OS.IIDFromString("{5632b1a4-e38a-400a-928a-d4cd63230295}\u0000".toCharArray(), TaskBar.IID_IObjectCollection);
        OS.IIDFromString("{000214F9-0000-0000-C000-000000000046}\u0000".toCharArray(), TaskBar.IID_IShellLinkW);
        OS.IIDFromString("{886d8eeb-8cf2-4446-8d02-cdba1dbdcf99}\u0000".toCharArray(), TaskBar.IID_IPropertyStore);
        OS.IIDFromString("{43826d1e-e718-42ee-bc55-a1e261c37bfe}\u0000".toCharArray(), TaskBar.IID_IShellItem);
        OS.IIDFromString("{947aab5f-0a5c-4c13-b4d6-4bf7836fc9f8}\u0000".toCharArray(), TaskBar.IID_IFileOperation);
        OS.IIDFromString("{F1B32785-6FBA-4FCF-9D55-7B8E7F157091}\u0000".toCharArray(), TaskBar.FOLDERID_LocalAppData);
        OS.PSPropertyKeyFromString("{F29F85E0-4FF9-1068-AB91-08002B27B3D9} 2\u0000".toCharArray(), TaskBar.PKEY_Title);
        OS.PSPropertyKeyFromString("{9F4C2855-9F79-4B39-A8D0-E1D42DE1D5F3}, 6\u0000".toCharArray(), TaskBar.PKEY_AppUserModel_IsDestListSeparator);
        TCHAR tchar;
        for (tchar = new TCHAR(0, 260); OS.GetModuleFileName(0, tchar, tchar.length()) == tchar.length(); tchar = new TCHAR(0, tchar.length() + 260)) {}
        final int strlen = tchar.strlen();
        EXE_PATH = new char[strlen + 1];
        System.arraycopy(tchar.chars, 0, TaskBar.EXE_PATH, 0, strlen);
    }
    
    TaskBar(Display display, final int n) {
        this.items = new TaskItem[4];
        if (display == null) {
            display = Display.getCurrent();
        }
        if (display == null) {
            display = Display.getDefault();
        }
        if (!display.isValidThread()) {
            this.error(22);
        }
        this.display = display;
        this.createHandle();
        this.reskinWidget();
    }
    
    void createHandle() {
        final int[] array = { 0 };
        if (OS.CoCreateInstance(TaskBar.CLSID_TaskbarList, 0, 1, TaskBar.IID_ITaskbarList3, array) != 0) {
            this.error(2);
        }
        this.mTaskbarList3 = array[0];
    }
    
    void createItem(final TaskItem taskItem, int itemCount) {
        if (itemCount == -1) {
            itemCount = this.itemCount;
        }
        if (itemCount < 0 || itemCount > this.itemCount) {
            this.error(6);
        }
        if (this.itemCount == this.items.length) {
            final TaskItem[] items = new TaskItem[this.items.length + 4];
            System.arraycopy(this.items, 0, items, 0, this.items.length);
            this.items = items;
        }
        System.arraycopy(this.items, itemCount, this.items, itemCount + 1, this.itemCount++ - itemCount);
        this.items[itemCount] = taskItem;
    }
    
    void createItems() {
        final Shell[] shells = this.display.getShells();
        for (int i = 0; i < shells.length; ++i) {
            this.getItem(shells[i]);
        }
        this.getItem(null);
    }
    
    int createShellLink(final MenuItem menuItem, final String s) {
        final int style = menuItem.getStyle();
        if ((style & 0x40) != 0x0) {
            return 0;
        }
        final int[] array = { 0 };
        if (OS.CoCreateInstance(TaskBar.CLSID_ShellLink, 0, 1, TaskBar.IID_IShellLinkW, array) != 0) {
            this.error(2);
        }
        final int n = array[0];
        final int getProcessHeap = OS.GetProcessHeap();
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, OS.PROPVARIANT_sizeof());
        int heapAlloc2 = 0;
        PROPERTYKEY propertykey;
        if ((style & 0x2) != 0x0) {
            OS.MoveMemory(heapAlloc, new short[] { 11 }, 2);
            OS.MoveMemory(heapAlloc + 8, new short[] { -1 }, 2);
            propertykey = TaskBar.PKEY_AppUserModel_IsDestListSeparator;
        }
        else {
            final String text = menuItem.getText();
            final int length = text.length();
            final char[] array2 = new char[length + 1];
            text.getChars(0, length, array2, 0);
            heapAlloc2 = OS.HeapAlloc(getProcessHeap, 8, array2.length * 2);
            OS.MoveMemory(heapAlloc2, array2, array2.length * 2);
            OS.MoveMemory(heapAlloc, new short[] { 31 }, 2);
            OS.MoveMemory(heapAlloc + 8, new int[] { heapAlloc2 }, OS.PTR_SIZEOF);
            propertykey = TaskBar.PKEY_Title;
            if (OS.VtblCall(20, n, TaskBar.EXE_PATH) != 0) {
                this.error(5);
            }
            final String string = "--launcher.openFile /SWTINTERNAL_ID" + menuItem.id;
            final int length2 = string.length();
            final char[] array3 = new char[length2 + 1];
            string.getChars(0, length2, array3, 0);
            if (OS.VtblCall(11, n, array3) != 0) {
                this.error(5);
            }
            final Image image = menuItem.getImage();
            if (image != null && s != null) {
                final String string2 = String.valueOf(s) + "\\menu" + menuItem.id + ".ico";
                ImageData imageData;
                if (menuItem.hBitmap != 0) {
                    imageData = Image.win32_new(this.display, 0, menuItem.hBitmap).getImageData();
                }
                else {
                    imageData = image.getImageData();
                }
                final ImageLoader imageLoader = new ImageLoader();
                imageLoader.data = new ImageData[] { imageData };
                imageLoader.save(string2, 3);
                final int length3 = string2.length();
                final char[] array4 = new char[length3 + 1];
                string2.getChars(0, length3, array4, 0);
                if (OS.VtblCall(17, n, array4, 0) != 0) {
                    this.error(5);
                }
            }
        }
        if (OS.VtblCall(0, n, TaskBar.IID_IPropertyStore, array) != 0) {
            this.error(2);
        }
        final int n2 = array[0];
        if (OS.VtblCall(6, n2, propertykey, heapAlloc) != 0) {
            this.error(5);
        }
        OS.VtblCall(7, n2);
        OS.VtblCall(2, n2);
        OS.HeapFree(getProcessHeap, 0, heapAlloc);
        if (heapAlloc2 != 0) {
            OS.HeapFree(getProcessHeap, 0, heapAlloc2);
        }
        return n;
    }
    
    int createShellLinkArray(final MenuItem[] array, final String s) {
        if (array == null) {
            return 0;
        }
        if (array.length == 0) {
            return 0;
        }
        final int[] array2 = { 0 };
        if (OS.CoCreateInstance(TaskBar.CLSID_EnumerableObjectCollection, 0, 1, TaskBar.IID_IObjectCollection, array2) != 0) {
            this.error(2);
        }
        final int n = array2[0];
        for (int i = 0; i < array.length; ++i) {
            final int shellLink = this.createShellLink(array[i], s);
            if (shellLink != 0) {
                if (OS.VtblCall(5, n, shellLink) != 0) {
                    this.error(5);
                }
                OS.VtblCall(2, shellLink);
            }
        }
        if (OS.VtblCall(0, n, TaskBar.IID_IObjectArray, array2) != 0) {
            this.error(2);
        }
        final int n2 = array2[0];
        OS.VtblCall(2, n);
        return n2;
    }
    
    void destroyItem(final TaskItem taskItem) {
        int n;
        for (n = 0; n < this.itemCount && this.items[n] != taskItem; ++n) {}
        if (n == this.itemCount) {
            return;
        }
        System.arraycopy(this.items, n + 1, this.items, n, --this.itemCount - n);
        this.items[this.itemCount] = null;
    }
    
    String getDirectory(final char[] array) {
        final char[] array2 = new char[array.length];
        for (int i = 0; i < array.length; ++i) {
            final char c = array[i];
            switch (c) {
                case 34:
                case 42:
                case 47:
                case 58:
                case 60:
                case 62:
                case 63:
                case 92:
                case 124: {
                    array2[i] = '_';
                    break;
                }
                default: {
                    array2[i] = c;
                    break;
                }
            }
        }
        String s = null;
        final int[] array3 = { 0 };
        if (OS.SHCreateItemInKnownFolder(TaskBar.FOLDERID_LocalAppData, 0, null, TaskBar.IID_IShellItem, array3) == 0) {
            final int n = array3[0];
            if (OS.CoCreateInstance(TaskBar.CLSID_FileOperation, 0, 1, TaskBar.IID_IFileOperation, array3) == 0) {
                final int n2 = array3[0];
                if (OS.VtblCall(5, n2, 1556) == 0) {
                    final int directory = this.getDirectory(n, n2, array2, false);
                    if (directory != 0) {
                        final int directory2 = this.getDirectory(directory, n2, TaskBar.ICO_DIR, true);
                        if (directory2 != 0) {
                            if (OS.VtblCall(5, directory2, -2147123200, array3) == 0) {
                                final int n3 = array3[0];
                                final int wcslen = OS.wcslen(n3);
                                final char[] array4 = new char[wcslen];
                                OS.MoveMemory(array4, n3, wcslen * 2);
                                s = new String(array4);
                                OS.CoTaskMemFree(n3);
                            }
                            OS.VtblCall(2, directory2);
                        }
                        OS.VtblCall(2, directory);
                    }
                }
                OS.VtblCall(2, n2);
            }
            OS.VtblCall(2, n);
        }
        return s;
    }
    
    int getDirectory(final int n, final int n2, final char[] array, final boolean b) {
        final int[] array2 = { 0 };
        if (OS.SHCreateItemFromRelativeName(n, array, 0, TaskBar.IID_IShellItem, array2) == 0) {
            if (!b) {
                return array2[0];
            }
            final int vtblCall = OS.VtblCall(18, n2, array2[0], 0);
            OS.VtblCall(2, array2[0]);
            if (vtblCall == 0 && OS.VtblCall(20, n2, n, 16, array, null, 0) == 0 && OS.VtblCall(21, n2) == 0 && OS.SHCreateItemFromRelativeName(n, array, 0, TaskBar.IID_IShellItem, array2) == 0) {
                return array2[0];
            }
        }
        else if (OS.VtblCall(20, n2, n, 16, array, null, 0) == 0 && OS.VtblCall(21, n2) == 0 && OS.SHCreateItemFromRelativeName(n, array, 0, TaskBar.IID_IShellItem, array2) == 0) {
            return array2[0];
        }
        return 0;
    }
    
    public TaskItem getItem(final int n) {
        this.checkWidget();
        this.createItems();
        if (n < 0 || n >= this.itemCount) {
            this.error(6);
        }
        return this.items[n];
    }
    
    public TaskItem getItem(final Shell shell) {
        this.checkWidget();
        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] != null && this.items[i].shell == shell) {
                return this.items[i];
            }
        }
        final TaskItem taskItem = new TaskItem(this, 0);
        if (shell != null) {
            taskItem.setShell(shell);
        }
        return taskItem;
    }
    
    public int getItemCount() {
        this.checkWidget();
        this.createItems();
        return this.itemCount;
    }
    
    public TaskItem[] getItems() {
        this.checkWidget();
        this.createItems();
        final TaskItem[] array = new TaskItem[this.itemCount];
        System.arraycopy(this.items, 0, array, 0, array.length);
        return array;
    }
    
    void releaseChildren(final boolean b) {
        if (this.items != null) {
            for (int i = 0; i < this.items.length; ++i) {
                final TaskItem taskItem = this.items[i];
                if (taskItem != null && !taskItem.isDisposed()) {
                    taskItem.release(false);
                }
            }
            this.items = null;
        }
        super.releaseChildren(b);
    }
    
    void releaseParent() {
        super.releaseParent();
        if (this.display.taskBar == this) {
            this.display.taskBar = null;
        }
    }
    
    void releaseWidget() {
        super.releaseWidget();
        if (this.mTaskbarList3 != 0) {
            OS.VtblCall(2, this.mTaskbarList3);
            this.mTaskbarList3 = 0;
        }
    }
    
    void reskinChildren(final int n) {
        if (this.items != null) {
            for (int i = 0; i < this.items.length; ++i) {
                final TaskItem taskItem = this.items[i];
                if (taskItem != null) {
                    taskItem.reskin(n);
                }
            }
        }
        super.reskinChildren(n);
    }
    
    void setMenu(final Menu menu) {
        final int[] array = { 0 };
        if (OS.CoCreateInstance(TaskBar.CLSID_DestinationList, 0, 1, TaskBar.IID_ICustomDestinationList, array) != 0) {
            this.error(2);
        }
        final int n = array[0];
        final String app_NAME = Display.APP_NAME;
        char[] array2 = null;
        if (app_NAME != null) {
            final int length = app_NAME.length();
            array2 = new char[length + 1];
            app_NAME.getChars(0, length, array2, 0);
        }
        final MenuItem[] array3 = null;
        final MenuItem[] items;
        if (menu != null && (items = menu.getItems()).length != 0) {
            final String directory = this.getDirectory(array2);
            final int shellLinkArray = this.createShellLinkArray(items, directory);
            if (shellLinkArray != 0) {
                final int vtblCall = OS.VtblCall(3, n, array2);
                if (vtblCall != 0) {
                    this.error(5);
                }
                OS.VtblCall(4, n, new int[1], TaskBar.IID_IObjectArray, array);
                if (vtblCall != 0) {
                    this.error(5);
                }
                final int n2 = array[0];
                final int[] array4 = { 0 };
                OS.VtblCall(3, shellLinkArray, array4);
                if (array4[0] != 0 && OS.VtblCall(7, n, shellLinkArray) != 0) {
                    this.error(5);
                }
                for (int i = 0; i < items.length; ++i) {
                    final MenuItem menuItem = items[i];
                    if ((menuItem.getStyle() & 0x40) != 0x0) {
                        final Menu menu2 = menuItem.getMenu();
                        if (menu2 != null) {
                            final int shellLinkArray2 = this.createShellLinkArray(menu2.getItems(), directory);
                            if (shellLinkArray2 != 0) {
                                OS.VtblCall(3, shellLinkArray2, array4);
                                if (array4[0] != 0) {
                                    final String text = menuItem.getText();
                                    final int length2 = text.length();
                                    final char[] array5 = new char[length2 + 1];
                                    text.getChars(0, length2, array5, 0);
                                    if (OS.VtblCall(5, n, array5, shellLinkArray2) != 0) {
                                        this.error(5);
                                    }
                                }
                                OS.VtblCall(2, shellLinkArray2);
                            }
                        }
                    }
                }
                if (OS.VtblCall(8, n) != 0) {
                    this.error(5);
                }
                if (n2 != 0) {
                    OS.VtblCall(2, n2);
                }
                OS.VtblCall(2, shellLinkArray);
            }
        }
        else if (OS.VtblCall(10, n, array2) != 0) {
            this.error(5);
        }
        OS.VtblCall(2, n);
    }
}
