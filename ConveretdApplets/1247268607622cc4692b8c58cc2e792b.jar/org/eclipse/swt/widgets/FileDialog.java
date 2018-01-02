// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.Callback;
import org.eclipse.swt.internal.win32.CREATESTRUCT;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.OPENFILENAME;
import org.eclipse.swt.internal.win32.OFNOTIFY;
import org.eclipse.swt.internal.win32.OS;

public class FileDialog extends Dialog
{
    String[] filterNames;
    String[] filterExtensions;
    String[] fileNames;
    String filterPath;
    String fileName;
    int filterIndex;
    boolean overwrite;
    static final String FILTER = "*.*";
    static int BUFFER_SIZE;
    static boolean USE_HOOK;
    
    static {
        FileDialog.BUFFER_SIZE = 32768;
        FileDialog.USE_HOOK = true;
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
            FileDialog.USE_HOOK = false;
        }
    }
    
    public FileDialog(final Shell shell) {
        this(shell, 65536);
    }
    
    public FileDialog(final Shell shell, final int n) {
        super(shell, Dialog.checkStyle(shell, n));
        this.filterNames = new String[0];
        this.filterExtensions = new String[0];
        this.fileNames = new String[0];
        this.filterPath = "";
        this.fileName = "";
        this.filterIndex = 0;
        this.overwrite = false;
        this.checkSubclass();
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public String[] getFileNames() {
        return this.fileNames;
    }
    
    public String[] getFilterExtensions() {
        return this.filterExtensions;
    }
    
    public int getFilterIndex() {
        return this.filterIndex;
    }
    
    public String[] getFilterNames() {
        return this.filterNames;
    }
    
    public String getFilterPath() {
        return this.filterPath;
    }
    
    public boolean getOverwrite() {
        return this.overwrite;
    }
    
    int OFNHookProc(final int n, final int n2, final int n3, final int n4) {
        switch (n2) {
            case 78: {
                final OFNOTIFY ofnotify = new OFNOTIFY();
                OS.MoveMemory(ofnotify, n4, OFNOTIFY.sizeof);
                if (ofnotify.code != -602) {
                    break;
                }
                int sendMessage = OS.SendMessage(ofnotify.hwndFrom, 1124, 0, 0);
                if (sendMessage <= 0) {
                    break;
                }
                sendMessage += 260;
                final OPENFILENAME openfilename = new OPENFILENAME();
                OS.MoveMemory(openfilename, ofnotify.lpOFN, OPENFILENAME.sizeof);
                if (openfilename.nMaxFile >= sendMessage) {
                    break;
                }
                final int getProcessHeap = OS.GetProcessHeap();
                final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, sendMessage * TCHAR.sizeof);
                if (heapAlloc != 0) {
                    if (openfilename.lpstrFile != 0) {
                        OS.HeapFree(getProcessHeap, 0, openfilename.lpstrFile);
                    }
                    openfilename.lpstrFile = heapAlloc;
                    openfilename.nMaxFile = sendMessage;
                    OS.MoveMemory(ofnotify.lpOFN, openfilename, OPENFILENAME.sizeof);
                    break;
                }
                break;
            }
        }
        return 0;
    }
    
    public String open() {
        final int getProcessHeap = OS.GetProcessHeap();
        int hwndOwner = this.parent.handle;
        final int handle = this.parent.handle;
        boolean isWindowEnabled = false;
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
            final int n = this.style & 0x6000000;
            if (n != (this.parent.style & 0x6000000)) {
                int n2 = 1048576;
                if (n == 67108864) {
                    n2 |= 0x400000;
                }
                hwndOwner = OS.CreateWindowEx(n2, Shell.DialogClass, null, 0, Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0, handle, 0, OS.GetModuleHandle(null), null);
                isWindowEnabled = OS.IsWindowEnabled(handle);
                if (isWindowEnabled) {
                    OS.EnableWindow(handle, false);
                }
            }
        }
        if (this.title == null) {
            this.title = "";
        }
        final TCHAR tchar = new TCHAR(0, this.title, true);
        final int n3 = tchar.length() * TCHAR.sizeof;
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n3);
        OS.MoveMemory(heapAlloc, tchar, n3);
        String s = "";
        if (this.filterNames == null) {
            this.filterNames = new String[0];
        }
        if (this.filterExtensions == null) {
            this.filterExtensions = new String[0];
        }
        for (int i = 0; i < this.filterExtensions.length; ++i) {
            String s2 = this.filterExtensions[i];
            if (i < this.filterNames.length) {
                s2 = this.filterNames[i];
            }
            s = String.valueOf(s) + s2 + '\0' + this.filterExtensions[i] + '\0';
        }
        if (this.filterExtensions.length == 0) {
            s = String.valueOf(s) + "*.*" + '\0' + "*.*" + '\0';
        }
        final TCHAR tchar2 = new TCHAR(0, s, true);
        final int n4 = tchar2.length() * TCHAR.sizeof;
        final int heapAlloc2 = OS.HeapAlloc(getProcessHeap, 8, n4);
        OS.MoveMemory(heapAlloc2, tchar2, n4);
        if (this.fileName == null) {
            this.fileName = "";
        }
        final TCHAR tchar3 = new TCHAR(0, this.fileName, true);
        int max = 260;
        if ((this.style & 0x2) != 0x0) {
            max = Math.max(max, FileDialog.BUFFER_SIZE);
        }
        final int n5 = max * TCHAR.sizeof;
        final int heapAlloc3 = OS.HeapAlloc(getProcessHeap, 8, n5);
        OS.MoveMemory(heapAlloc3, tchar3, Math.min(tchar3.length() * TCHAR.sizeof, n5 - TCHAR.sizeof));
        if (this.filterPath == null) {
            this.filterPath = "";
        }
        final TCHAR tchar4 = new TCHAR(0, this.filterPath.replace('/', '\\'), true);
        final int n6 = 260 * TCHAR.sizeof;
        final int heapAlloc4 = OS.HeapAlloc(getProcessHeap, 8, n6);
        OS.MoveMemory(heapAlloc4, tchar4, Math.min(tchar4.length() * TCHAR.sizeof, n6 - TCHAR.sizeof));
        final OPENFILENAME openfilename = new OPENFILENAME();
        openfilename.lStructSize = OPENFILENAME.sizeof;
        openfilename.Flags = 12;
        final boolean b = (this.style & 0x2000) != 0x0;
        if (b && this.overwrite) {
            final OPENFILENAME openfilename2 = openfilename;
            openfilename2.Flags |= 0x2;
        }
        Callback callback = null;
        if ((this.style & 0x2) != 0x0) {
            final OPENFILENAME openfilename3 = openfilename;
            openfilename3.Flags |= 0x880200;
            if (!OS.IsWinCE && FileDialog.USE_HOOK) {
                callback = new Callback(this, "OFNHookProc", 4);
                final int address = callback.getAddress();
                if (address == 0) {
                    SWT.error(3);
                }
                openfilename.lpfnHook = address;
                final OPENFILENAME openfilename4 = openfilename;
                openfilename4.Flags |= 0x20;
            }
        }
        openfilename.hwndOwner = hwndOwner;
        openfilename.lpstrTitle = heapAlloc;
        openfilename.lpstrFile = heapAlloc3;
        openfilename.nMaxFile = max;
        openfilename.lpstrInitialDir = heapAlloc4;
        openfilename.lpstrFilter = heapAlloc2;
        openfilename.nFilterIndex = ((this.filterIndex == 0) ? this.filterIndex : (this.filterIndex + 1));
        int heapAlloc5 = 0;
        if (b) {
            heapAlloc5 = OS.HeapAlloc(getProcessHeap, 8, TCHAR.sizeof);
            openfilename.lpstrDefExt = heapAlloc5;
        }
        Dialog modalDialog = null;
        final Display display = this.parent.getDisplay();
        if ((this.style & 0x30000) != 0x0) {
            modalDialog = display.getModalDialog();
            display.setModalDialog(this);
        }
        final boolean runMessagesInIdle = display.runMessagesInIdle;
        display.runMessagesInIdle = !OS.IsWin95;
        boolean b2 = b ? OS.GetSaveFileName(openfilename) : OS.GetOpenFileName(openfilename);
        switch (OS.CommDlgExtendedError()) {
            case 12290: {
                OS.MoveMemory(heapAlloc3, new TCHAR(0, "", true), TCHAR.sizeof);
                b2 = (b ? OS.GetSaveFileName(openfilename) : OS.GetOpenFileName(openfilename));
                break;
            }
            case 12291: {
                FileDialog.USE_HOOK = true;
                break;
            }
        }
        display.runMessagesInIdle = runMessagesInIdle;
        if ((this.style & 0x30000) != 0x0) {
            display.setModalDialog(modalDialog);
        }
        if (callback != null) {
            callback.dispose();
        }
        final int lpstrFile = openfilename.lpstrFile;
        this.fileNames = new String[0];
        String string = null;
        if (b2) {
            final TCHAR tchar5 = new TCHAR(0, openfilename.nMaxFile);
            OS.MoveMemory(tchar5, lpstrFile, tchar5.length() * TCHAR.sizeof);
            int nFileOffset = openfilename.nFileOffset;
            if (OS.IsWinCE && nFileOffset == 0) {
                for (int j = 0; j < tchar5.length(); ++j) {
                    final int tchar6 = tchar5.tcharAt(j);
                    if (tchar6 == 0) {
                        break;
                    }
                    if (tchar6 == 92) {
                        nFileOffset = j + 1;
                    }
                }
            }
            if (nFileOffset > 0) {
                final TCHAR tchar7 = new TCHAR(0, nFileOffset - 1);
                OS.MoveMemory(tchar7, lpstrFile, tchar7.length() * TCHAR.sizeof);
                this.filterPath = tchar7.toString(0, tchar7.length());
                int n7 = 0;
                this.fileNames = new String[((this.style & 0x2) != 0x0) ? 4 : 1];
                int n8 = nFileOffset;
                do {
                    int n9;
                    for (n9 = n8; n9 < tchar5.length() && tchar5.tcharAt(n9) != 0; ++n9) {}
                    final String string2 = tchar5.toString(n8, n9 - n8);
                    n8 = n9;
                    if (n7 == this.fileNames.length) {
                        final String[] fileNames = new String[this.fileNames.length + 4];
                        System.arraycopy(this.fileNames, 0, fileNames, 0, this.fileNames.length);
                        this.fileNames = fileNames;
                    }
                    this.fileNames[n7++] = string2;
                    if ((this.style & 0x2) == 0x0) {
                        break;
                    }
                } while (++n8 < tchar5.length() && tchar5.tcharAt(n8) != 0);
                if (this.fileNames.length > 0) {
                    this.fileName = this.fileNames[0];
                }
                String s3 = "";
                final int length = this.filterPath.length();
                if (length > 0 && this.filterPath.charAt(length - 1) != '\\') {
                    s3 = "\\";
                }
                string = String.valueOf(this.filterPath) + s3 + this.fileName;
                if (n7 < this.fileNames.length) {
                    final String[] fileNames2 = new String[n7];
                    System.arraycopy(this.fileNames, 0, fileNames2, 0, n7);
                    this.fileNames = fileNames2;
                }
            }
            this.filterIndex = openfilename.nFilterIndex - 1;
        }
        OS.HeapFree(getProcessHeap, 0, lpstrFile);
        OS.HeapFree(getProcessHeap, 0, heapAlloc2);
        OS.HeapFree(getProcessHeap, 0, heapAlloc4);
        OS.HeapFree(getProcessHeap, 0, heapAlloc);
        if (heapAlloc5 != 0) {
            OS.HeapFree(getProcessHeap, 0, heapAlloc5);
        }
        if (handle != hwndOwner) {
            if (isWindowEnabled) {
                OS.EnableWindow(handle, true);
            }
            OS.SetActiveWindow(handle);
            OS.DestroyWindow(hwndOwner);
        }
        return string;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public void setFilterExtensions(final String[] filterExtensions) {
        this.filterExtensions = filterExtensions;
    }
    
    public void setFilterIndex(final int filterIndex) {
        this.filterIndex = filterIndex;
    }
    
    public void setFilterNames(final String[] filterNames) {
        this.filterNames = filterNames;
    }
    
    public void setFilterPath(final String filterPath) {
        this.filterPath = filterPath;
    }
    
    public void setOverwrite(final boolean overwrite) {
        this.overwrite = overwrite;
    }
}
