// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.BROWSEINFO;
import org.eclipse.swt.internal.Callback;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.OS;

public class DirectoryDialog extends Dialog
{
    String message;
    String filterPath;
    String directoryPath;
    
    public DirectoryDialog(final Shell shell) {
        this(shell, 65536);
    }
    
    public DirectoryDialog(final Shell shell, final int n) {
        super(shell, Dialog.checkStyle(shell, n));
        this.message = "";
        this.filterPath = "";
        this.checkSubclass();
    }
    
    int BrowseCallbackProc(final int n, final int n2, final int n3, final int n4) {
        switch (n2) {
            case 1: {
                if (this.filterPath != null && this.filterPath.length() != 0) {
                    OS.SendMessage(n, OS.BFFM_SETSELECTION, 1, new TCHAR(0, this.filterPath.replace('/', '\\'), true));
                }
                if (this.title != null && this.title.length() != 0) {
                    OS.SetWindowText(n, new TCHAR(0, this.title, true));
                    break;
                }
                break;
            }
            case 3:
            case 4: {
                final int n5 = OS.IsUnicode ? OS.wcslen(n3) : C.strlen(n3);
                final TCHAR tchar = new TCHAR(0, n5);
                OS.MoveMemory(tchar, n3, tchar.length() * TCHAR.sizeof);
                this.directoryPath = tchar.toString(0, n5);
                break;
            }
        }
        return 0;
    }
    
    public String getFilterPath() {
        return this.filterPath;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public String open() {
        if (OS.IsWinCE) {
            SWT.error(20);
        }
        final int getProcessHeap = OS.GetProcessHeap();
        int handle = 0;
        if (this.parent != null) {
            handle = this.parent.handle;
        }
        int heapAlloc = 0;
        if (this.message.length() != 0) {
            String message = this.message;
            if (message.indexOf(38) != -1) {
                final int length = message.length();
                final char[] array = new char[length * 2];
                int n = 0;
                for (int i = 0; i < length; ++i) {
                    final char char1 = message.charAt(i);
                    if (char1 == '&') {
                        array[n++] = '&';
                    }
                    array[n++] = char1;
                }
                message = new String(array, 0, n);
            }
            final TCHAR tchar = new TCHAR(0, message, true);
            final int n2 = tchar.length() * TCHAR.sizeof;
            heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n2);
            OS.MoveMemory(heapAlloc, tchar, n2);
        }
        final Callback callback = new Callback(this, "BrowseCallbackProc", 4);
        final int address = callback.getAddress();
        if (address == 0) {
            SWT.error(3);
        }
        Dialog modalDialog = null;
        final Display display = this.parent.getDisplay();
        if ((this.style & 0x30000) != 0x0) {
            modalDialog = display.getModalDialog();
            display.setModalDialog(this);
        }
        this.directoryPath = null;
        final BROWSEINFO browseinfo = new BROWSEINFO();
        browseinfo.hwndOwner = handle;
        browseinfo.lpszTitle = heapAlloc;
        browseinfo.ulFlags = 113;
        browseinfo.lpfn = address;
        final int setErrorMode = OS.SetErrorMode(1);
        final boolean runMessages = display.runMessages;
        if (OS.COMCTL32_MAJOR < 6) {
            display.runMessages = false;
        }
        final int shBrowseForFolder = OS.SHBrowseForFolder(browseinfo);
        if (OS.COMCTL32_MAJOR < 6) {
            display.runMessages = runMessages;
        }
        OS.SetErrorMode(setErrorMode);
        if ((this.style & 0x30000) != 0x0) {
            display.setModalDialog(modalDialog);
        }
        final boolean b = shBrowseForFolder != 0;
        if (b) {
            final TCHAR tchar2 = new TCHAR(0, 260);
            if (OS.SHGetPathFromIDList(shBrowseForFolder, tchar2)) {
                this.directoryPath = tchar2.toString(0, tchar2.strlen());
                this.filterPath = this.directoryPath;
            }
        }
        callback.dispose();
        if (heapAlloc != 0) {
            OS.HeapFree(getProcessHeap, 0, heapAlloc);
        }
        final int[] array2 = { 0 };
        if (OS.SHGetMalloc(array2) == 0) {
            OS.VtblCall(5, array2[0], shBrowseForFolder);
        }
        if (!b) {
            return null;
        }
        return this.directoryPath;
    }
    
    public void setFilterPath(final String filterPath) {
        this.filterPath = filterPath;
    }
    
    public void setMessage(final String message) {
        if (message == null) {
            this.error(4);
        }
        this.message = message;
    }
}
