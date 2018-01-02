// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.mozilla.nsILocalFile;
import org.eclipse.swt.internal.mozilla.nsEmbedString;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.internal.mozilla.nsIDOMWindow;
import org.eclipse.swt.internal.mozilla.nsIFilePicker_1_8;
import org.eclipse.swt.internal.mozilla.nsIFilePicker;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.mozilla.nsISupports;
import org.eclipse.swt.internal.mozilla.XPCOM;
import org.eclipse.swt.internal.mozilla.nsID;
import org.eclipse.swt.internal.mozilla.XPCOMObject;

class FilePicker
{
    XPCOMObject supports;
    XPCOMObject filePicker;
    int refCount;
    short mode;
    int parentHandle;
    String[] files;
    String[] masks;
    String defaultFilename;
    String directory;
    String title;
    static final String SEPARATOR;
    
    static {
        SEPARATOR = System.getProperty("file.separator");
    }
    
    FilePicker() {
        this.refCount = 0;
        this.createCOMInterfaces();
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void createCOMInterfaces() {
        this.supports = new XPCOMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return FilePicker.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return FilePicker.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return FilePicker.this.Release();
            }
        };
        this.filePicker = new XPCOMObject(new int[] { 2, 0, 0, 3, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }) {
            public int method0(final int[] array) {
                return FilePicker.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return FilePicker.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return FilePicker.this.Release();
            }
            
            public int method3(final int[] array) {
                return FilePicker.this.Init(array[0], array[1], (short)array[2]);
            }
            
            public int method4(final int[] array) {
                return FilePicker.this.AppendFilters(array[0]);
            }
            
            public int method5(final int[] array) {
                return FilePicker.this.AppendFilter(array[0], array[1]);
            }
            
            public int method6(final int[] array) {
                return FilePicker.this.GetDefaultString(array[0]);
            }
            
            public int method7(final int[] array) {
                return FilePicker.this.SetDefaultString(array[0]);
            }
            
            public int method8(final int[] array) {
                return FilePicker.this.GetDefaultExtension(array[0]);
            }
            
            public int method9(final int[] array) {
                return FilePicker.this.SetDefaultExtension(array[0]);
            }
            
            public int method10(final int[] array) {
                return FilePicker.this.GetFilterIndex(array[0]);
            }
            
            public int method11(final int[] array) {
                return FilePicker.this.SetFilterIndex(array[0]);
            }
            
            public int method12(final int[] array) {
                return FilePicker.this.GetDisplayDirectory(array[0]);
            }
            
            public int method13(final int[] array) {
                return FilePicker.this.SetDisplayDirectory(array[0]);
            }
            
            public int method14(final int[] array) {
                return FilePicker.this.GetFile(array[0]);
            }
            
            public int method15(final int[] array) {
                return FilePicker.this.GetFileURL(array[0]);
            }
            
            public int method16(final int[] array) {
                return FilePicker.this.GetFiles(array[0]);
            }
            
            public int method17(final int[] array) {
                return FilePicker.this.Show(array[0]);
            }
        };
    }
    
    void disposeCOMInterfaces() {
        if (this.supports != null) {
            this.supports.dispose();
            this.supports = null;
        }
        if (this.filePicker != null) {
            this.filePicker.dispose();
            this.filePicker = null;
        }
    }
    
    int getAddress() {
        return this.filePicker.getAddress();
    }
    
    int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147467262;
        }
        final nsID nsID = new nsID();
        XPCOM.memmove(nsID, n, 16);
        if (nsID.Equals(nsISupports.NS_ISUPPORTS_IID)) {
            C.memmove(n2, new int[] { this.supports.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIFilePicker.NS_IFILEPICKER_IID)) {
            C.memmove(n2, new int[] { this.filePicker.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIFilePicker_1_8.NS_IFILEPICKER_IID)) {
            C.memmove(n2, new int[] { this.filePicker.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        C.memmove(n2, new int[1], C.PTR_SIZEOF);
        return -2147467262;
    }
    
    int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            this.disposeCOMInterfaces();
        }
        return this.refCount;
    }
    
    Browser getBrowser(final int n) {
        if (n == 0) {
            return null;
        }
        return Mozilla.findBrowser(new nsIDOMWindow(n));
    }
    
    String parseAString(final int n) {
        return null;
    }
    
    int Init(final int parentHandle, final int n, final short mode) {
        this.parentHandle = parentHandle;
        this.mode = mode;
        this.title = this.parseAString(n);
        return 0;
    }
    
    int Show(final int n) {
        if (this.mode == 2) {
            C.memmove(n, new short[] { (short)this.showDirectoryPicker() }, 2);
            return 0;
        }
        int n2 = (this.mode == 1) ? 8192 : 4096;
        if (this.mode == 3) {
            n2 |= 0x2;
        }
        final Browser browser = this.getBrowser(this.parentHandle);
        Shell shell;
        if (browser != null) {
            shell = browser.getShell();
        }
        else {
            shell = new Shell();
        }
        final FileDialog fileDialog = new FileDialog(shell, n2);
        if (this.title != null) {
            fileDialog.setText(this.title);
        }
        if (this.directory != null) {
            fileDialog.setFilterPath(this.directory);
        }
        if (this.masks != null) {
            fileDialog.setFilterExtensions(this.masks);
        }
        if (this.defaultFilename != null) {
            fileDialog.setFileName(this.defaultFilename);
        }
        final String open = fileDialog.open();
        this.files = fileDialog.getFileNames();
        this.directory = fileDialog.getFilterPath();
        final String s = null;
        this.defaultFilename = s;
        this.title = s;
        this.masks = null;
        C.memmove(n, new short[] { (short)((open == null) ? 1 : 0) }, 2);
        return 0;
    }
    
    int showDirectoryPicker() {
        final Browser browser = this.getBrowser(this.parentHandle);
        Shell shell;
        if (browser != null) {
            shell = browser.getShell();
        }
        else {
            shell = new Shell();
        }
        final DirectoryDialog directoryDialog = new DirectoryDialog(shell, 0);
        if (this.title != null) {
            directoryDialog.setText(this.title);
        }
        if (this.directory != null) {
            directoryDialog.setFilterPath(this.directory);
        }
        this.directory = directoryDialog.open();
        final String s = null;
        this.defaultFilename = s;
        this.title = s;
        final String[] array = null;
        this.masks = array;
        this.files = array;
        return (this.directory == null) ? 1 : 0;
    }
    
    int GetFiles(final int n) {
        return -2147467263;
    }
    
    int GetFileURL(final int n) {
        return -2147467263;
    }
    
    int GetFile(final int n) {
        String s = "";
        if (this.directory != null) {
            s = String.valueOf(s) + this.directory + FilePicker.SEPARATOR;
        }
        if (this.files != null && this.files.length > 0) {
            s = String.valueOf(s) + this.files[0];
        }
        final nsEmbedString nsEmbedString = new nsEmbedString(s);
        final int[] array = { 0 };
        final int ns_NewLocalFile = XPCOM.NS_NewLocalFile(nsEmbedString.getAddress(), 1, array);
        nsEmbedString.dispose();
        if (ns_NewLocalFile != 0) {
            Mozilla.error(ns_NewLocalFile);
        }
        if (array[0] == 0) {
            Mozilla.error(-2147467261);
        }
        C.memmove(n, array, C.PTR_SIZEOF);
        return 0;
    }
    
    int SetDisplayDirectory(final int n) {
        if (n == 0) {
            return 0;
        }
        final nsILocalFile nsILocalFile = new nsILocalFile(n);
        final int nsEmbedCString_new = XPCOM.nsEmbedCString_new();
        nsILocalFile.GetNativePath(nsEmbedCString_new);
        final int nsEmbedCString_Length = XPCOM.nsEmbedCString_Length(nsEmbedCString_new);
        final int nsEmbedCString_get = XPCOM.nsEmbedCString_get(nsEmbedCString_new);
        final byte[] array = new byte[nsEmbedCString_Length];
        C.memmove(array, nsEmbedCString_get, nsEmbedCString_Length);
        XPCOM.nsEmbedCString_delete(nsEmbedCString_new);
        this.directory = new String(MozillaDelegate.mbcsToWcs(null, array));
        return 0;
    }
    
    int GetDisplayDirectory(final int n) {
        final nsEmbedString nsEmbedString = new nsEmbedString((this.directory != null) ? this.directory : "");
        final int[] array = { 0 };
        final int ns_NewLocalFile = XPCOM.NS_NewLocalFile(nsEmbedString.getAddress(), 1, array);
        nsEmbedString.dispose();
        if (ns_NewLocalFile != 0) {
            Mozilla.error(ns_NewLocalFile);
        }
        if (array[0] == 0) {
            Mozilla.error(-2147467261);
        }
        C.memmove(n, array, C.PTR_SIZEOF);
        return 0;
    }
    
    int SetFilterIndex(final int n) {
        return -2147467263;
    }
    
    int GetFilterIndex(final int n) {
        return -2147467263;
    }
    
    int SetDefaultExtension(final int n) {
        return -2147467263;
    }
    
    int GetDefaultExtension(final int n) {
        return -2147467263;
    }
    
    int SetDefaultString(final int n) {
        this.defaultFilename = this.parseAString(n);
        return 0;
    }
    
    int GetDefaultString(final int n) {
        return -2147467263;
    }
    
    int AppendFilter(final int n, final int n2) {
        return -2147467263;
    }
    
    int AppendFilters(final int n) {
        String[] masks = null;
        switch (n) {
            case 1:
            case 64: {
                this.masks = null;
                break;
            }
            case 2: {
                masks = new String[] { "*.htm;*.html" };
                break;
            }
            case 8: {
                masks = new String[] { "*.gif;*.jpeg;*.jpg;*.png" };
                break;
            }
            case 4: {
                masks = new String[] { "*.txt" };
                break;
            }
            case 16: {
                masks = new String[] { "*.xml" };
                break;
            }
            case 32: {
                masks = new String[] { "*.xul" };
                break;
            }
        }
        if (this.masks == null) {
            this.masks = masks;
        }
        else if (masks != null) {
            final String[] masks2 = new String[this.masks.length + masks.length];
            System.arraycopy(this.masks, 0, masks2, 0, this.masks.length);
            System.arraycopy(masks, 0, masks2, this.masks.length, masks.length);
            this.masks = masks2;
        }
        return 0;
    }
}
