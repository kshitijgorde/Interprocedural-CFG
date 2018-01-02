// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.internal.mozilla.nsILocalFile;
import org.eclipse.swt.internal.mozilla.nsIURI;
import org.eclipse.swt.internal.mozilla.nsIWebProgressListener;
import org.eclipse.swt.internal.mozilla.nsIProgressDialog;
import org.eclipse.swt.internal.mozilla.nsIDownload;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.mozilla.nsISupports;
import org.eclipse.swt.internal.mozilla.XPCOM;
import org.eclipse.swt.internal.mozilla.nsID;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.internal.mozilla.nsIHelperAppLauncher;
import org.eclipse.swt.internal.mozilla.XPCOMObject;

class Download
{
    XPCOMObject supports;
    XPCOMObject download;
    XPCOMObject progressDialog;
    XPCOMObject webProgressListener;
    nsIHelperAppLauncher helperAppLauncher;
    int refCount;
    Shell shell;
    Label status;
    Button cancel;
    
    Download() {
        this.refCount = 0;
        this.createCOMInterfaces();
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void createCOMInterfaces() {
        this.supports = new XPCOMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return Download.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Download.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Download.this.Release();
            }
        };
        this.download = new XPCOMObject(new int[] { 2, 0, 0, 7, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1 }) {
            public int method0(final int[] array) {
                return Download.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Download.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Download.this.Release();
            }
            
            public int method3(final int[] array) {
                return Download.this.Init(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
            }
            
            public int method4(final int[] array) {
                return Download.this.GetSource(array[0]);
            }
            
            public int method5(final int[] array) {
                return Download.this.GetTarget(array[0]);
            }
            
            public int method6(final int[] array) {
                return Download.this.GetPersist(array[0]);
            }
            
            public int method7(final int[] array) {
                return Download.this.GetPercentComplete(array[0]);
            }
            
            public int method8(final int[] array) {
                return Download.this.GetDisplayName(array[0]);
            }
            
            public int method9(final int[] array) {
                return Download.this.SetDisplayName(array[0]);
            }
            
            public int method10(final int[] array) {
                return Download.this.GetStartTime(array[0]);
            }
            
            public int method11(final int[] array) {
                return Download.this.GetMIMEInfo(array[0]);
            }
            
            public int method12(final int[] array) {
                return Download.this.GetListener(array[0]);
            }
            
            public int method13(final int[] array) {
                return Download.this.SetListener(array[0]);
            }
            
            public int method14(final int[] array) {
                return Download.this.GetObserver(array[0]);
            }
            
            public int method15(final int[] array) {
                return Download.this.SetObserver(array[0]);
            }
        };
        this.progressDialog = new XPCOMObject(new int[] { 2, 0, 0, 7, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }) {
            public int method0(final int[] array) {
                return Download.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Download.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Download.this.Release();
            }
            
            public int method3(final int[] array) {
                return Download.this.Init(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
            }
            
            public int method4(final int[] array) {
                return Download.this.GetSource(array[0]);
            }
            
            public int method5(final int[] array) {
                return Download.this.GetTarget(array[0]);
            }
            
            public int method6(final int[] array) {
                return Download.this.GetPersist(array[0]);
            }
            
            public int method7(final int[] array) {
                return Download.this.GetPercentComplete(array[0]);
            }
            
            public int method8(final int[] array) {
                return Download.this.GetDisplayName(array[0]);
            }
            
            public int method9(final int[] array) {
                return Download.this.SetDisplayName(array[0]);
            }
            
            public int method10(final int[] array) {
                return Download.this.GetStartTime(array[0]);
            }
            
            public int method11(final int[] array) {
                return Download.this.GetMIMEInfo(array[0]);
            }
            
            public int method12(final int[] array) {
                return Download.this.GetListener(array[0]);
            }
            
            public int method13(final int[] array) {
                return Download.this.SetListener(array[0]);
            }
            
            public int method14(final int[] array) {
                return Download.this.GetObserver(array[0]);
            }
            
            public int method15(final int[] array) {
                return Download.this.SetObserver(array[0]);
            }
            
            public int method16(final int[] array) {
                return Download.this.Open(array[0]);
            }
            
            public int method17(final int[] array) {
                return Download.this.GetCancelDownloadOnClose(array[0]);
            }
            
            public int method18(final int[] array) {
                return Download.this.SetCancelDownloadOnClose(array[0]);
            }
            
            public int method19(final int[] array) {
                return Download.this.GetDialog(array[0]);
            }
            
            public int method20(final int[] array) {
                return Download.this.SetDialog(array[0]);
            }
        };
        this.webProgressListener = new XPCOMObject(new int[] { 2, 0, 0, 4, 6, 3, 4, 3 }) {
            public int method0(final int[] array) {
                return Download.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Download.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Download.this.Release();
            }
            
            public int method3(final int[] array) {
                return Download.this.OnStateChange(array[0], array[1], array[2], array[3]);
            }
            
            public int method4(final int[] array) {
                return Download.this.OnProgressChange(array[0], array[1], array[2], array[3], array[4], array[5]);
            }
            
            public int method5(final int[] array) {
                return Download.this.OnLocationChange(array[0], array[1], array[2]);
            }
            
            public int method6(final int[] array) {
                return Download.this.OnStatusChange(array[0], array[1], array[2], array[3]);
            }
            
            public int method7(final int[] array) {
                return Download.this.OnSecurityChange(array[0], array[1], array[2]);
            }
        };
    }
    
    void disposeCOMInterfaces() {
        if (this.supports != null) {
            this.supports.dispose();
            this.supports = null;
        }
        if (this.download != null) {
            this.download.dispose();
            this.download = null;
        }
        if (this.progressDialog != null) {
            this.progressDialog.dispose();
            this.progressDialog = null;
        }
        if (this.webProgressListener != null) {
            this.webProgressListener.dispose();
            this.webProgressListener = null;
        }
    }
    
    int getAddress() {
        return this.progressDialog.getAddress();
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
        if (nsID.Equals(nsIDownload.NS_IDOWNLOAD_IID)) {
            C.memmove(n2, new int[] { this.download.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIProgressDialog.NS_IPROGRESSDIALOG_IID)) {
            C.memmove(n2, new int[] { this.progressDialog.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIWebProgressListener.NS_IWEBPROGRESSLISTENER_IID)) {
            C.memmove(n2, new int[] { this.webProgressListener.getAddress() }, C.PTR_SIZEOF);
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
    
    int Init(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final nsIURI nsIURI = new nsIURI(n);
        final int nsEmbedCString_new = XPCOM.nsEmbedCString_new();
        final int getHost = nsIURI.GetHost(nsEmbedCString_new);
        if (getHost != 0) {
            Mozilla.error(getHost);
        }
        final int nsEmbedCString_Length = XPCOM.nsEmbedCString_Length(nsEmbedCString_new);
        final int nsEmbedCString_get = XPCOM.nsEmbedCString_get(nsEmbedCString_new);
        final byte[] array = new byte[nsEmbedCString_Length];
        C.memmove(array, nsEmbedCString_get, nsEmbedCString_Length);
        XPCOM.nsEmbedCString_delete(nsEmbedCString_new);
        final String s = new String(array);
        final nsISupports nsISupports = new nsISupports(n2);
        final int[] array2 = { 0 };
        String substring;
        if (nsISupports.QueryInterface(org.eclipse.swt.internal.mozilla.nsIURI.NS_IURI_IID, array2) == 0) {
            final nsIURI nsIURI2 = new nsIURI(array2[0]);
            array2[0] = 0;
            final int nsEmbedCString_new2 = XPCOM.nsEmbedCString_new();
            final int getPath = nsIURI2.GetPath(nsEmbedCString_new2);
            if (getPath != 0) {
                Mozilla.error(getPath);
            }
            final int nsEmbedCString_Length2 = XPCOM.nsEmbedCString_Length(nsEmbedCString_new2);
            final int nsEmbedCString_get2 = XPCOM.nsEmbedCString_get(nsEmbedCString_new2);
            final byte[] array3 = new byte[nsEmbedCString_Length2];
            C.memmove(array3, nsEmbedCString_get2, nsEmbedCString_Length2);
            XPCOM.nsEmbedCString_delete(nsEmbedCString_new2);
            final String s2 = new String(array3);
            substring = s2.substring(s2.lastIndexOf(System.getProperty("file.separator")) + 1);
            nsIURI2.Release();
        }
        else {
            final nsILocalFile nsILocalFile = new nsILocalFile(n2);
            final int nsEmbedCString_new3 = XPCOM.nsEmbedCString_new();
            final int getNativeLeafName = nsILocalFile.GetNativeLeafName(nsEmbedCString_new3);
            if (getNativeLeafName != 0) {
                Mozilla.error(getNativeLeafName);
            }
            final int nsEmbedCString_Length3 = XPCOM.nsEmbedCString_Length(nsEmbedCString_new3);
            final int nsEmbedCString_get3 = XPCOM.nsEmbedCString_get(nsEmbedCString_new3);
            final byte[] array4 = new byte[nsEmbedCString_Length3];
            C.memmove(array4, nsEmbedCString_get3, nsEmbedCString_Length3);
            XPCOM.nsEmbedCString_delete(nsEmbedCString_new3);
            substring = new String(array4);
        }
        final Listener listener = new Listener() {
            public void handleEvent(final Event event) {
                if (event.widget == Download.this.cancel) {
                    Download.this.shell.close();
                }
                if (Download.this.helperAppLauncher != null) {
                    Download.this.helperAppLauncher.Cancel();
                    Download.this.helperAppLauncher.Release();
                }
                Download.this.shell = null;
                Download.this.helperAppLauncher = null;
            }
        };
        (this.shell = new Shell(2144)).setText(Compatibility.getMessage("SWT_Download_File", new Object[] { substring }));
        final GridLayout layout = new GridLayout();
        layout.marginHeight = 15;
        layout.marginWidth = 15;
        layout.verticalSpacing = 20;
        this.shell.setLayout(layout);
        new Label(this.shell, 64).setText(Compatibility.getMessage("SWT_Download_Location", new Object[] { substring, s }));
        (this.status = new Label(this.shell, 64)).setText(Compatibility.getMessage("SWT_Download_Started"));
        final GridData layoutData = new GridData();
        layoutData.grabExcessHorizontalSpace = true;
        layoutData.grabExcessVerticalSpace = true;
        this.status.setLayoutData(layoutData);
        (this.cancel = new Button(this.shell, 8)).setText(SWT.getMessage("SWT_Cancel"));
        final GridData layoutData2 = new GridData();
        layoutData2.horizontalAlignment = 2;
        this.cancel.setLayoutData(layoutData2);
        this.cancel.addListener(13, listener);
        this.shell.addListener(21, listener);
        this.shell.pack();
        this.shell.open();
        return 0;
    }
    
    int GetSource(final int n) {
        return -2147467263;
    }
    
    int GetTarget(final int n) {
        return -2147467263;
    }
    
    int GetPersist(final int n) {
        return -2147467263;
    }
    
    int GetPercentComplete(final int n) {
        return -2147467263;
    }
    
    int GetDisplayName(final int n) {
        return -2147467263;
    }
    
    int SetDisplayName(final int n) {
        return -2147467263;
    }
    
    int GetStartTime(final int n) {
        return -2147467263;
    }
    
    int GetMIMEInfo(final int n) {
        return -2147467263;
    }
    
    int GetListener(final int n) {
        return -2147467263;
    }
    
    int SetListener(final int n) {
        return -2147467263;
    }
    
    int GetObserver(final int n) {
        return -2147467263;
    }
    
    int SetObserver(final int n) {
        if (n != 0) {
            final nsISupports nsISupports = new nsISupports(n);
            final int[] array = { 0 };
            final int queryInterface = nsISupports.QueryInterface(nsIHelperAppLauncher.NS_IHELPERAPPLAUNCHER_IID, array);
            if (queryInterface != 0) {
                Mozilla.error(queryInterface);
            }
            if (array[0] == 0) {
                Mozilla.error(-2147467262);
            }
            this.helperAppLauncher = new nsIHelperAppLauncher(array[0]);
        }
        return 0;
    }
    
    int Open(final int n) {
        return -2147467263;
    }
    
    int GetCancelDownloadOnClose(final int n) {
        return -2147467263;
    }
    
    int SetCancelDownloadOnClose(final int n) {
        return -2147467263;
    }
    
    int GetDialog(final int n) {
        return -2147467263;
    }
    
    int SetDialog(final int n) {
        return -2147467263;
    }
    
    int OnStateChange(final int n, final int n2, final int n3, final int n4) {
        if ((n3 & 0x10) != 0x0) {
            if (this.helperAppLauncher != null) {
                this.helperAppLauncher.Release();
            }
            this.helperAppLauncher = null;
            if (this.shell != null && !this.shell.isDisposed()) {
                this.shell.dispose();
            }
            this.shell = null;
        }
        return 0;
    }
    
    int OnProgressChange(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = n5 / 1024;
        final int n8 = n6 / 1024;
        if (this.shell != null && !this.shell.isDisposed()) {
            this.status.setText(Compatibility.getMessage("SWT_Download_Status", new Object[] { new Integer(n7), new Integer(n8) }));
            this.shell.layout(true);
            this.shell.getDisplay().update();
        }
        return 0;
    }
    
    int OnLocationChange(final int n, final int n2, final int n3) {
        return 0;
    }
    
    int OnStatusChange(final int n, final int n2, final int n3, final int n4) {
        return 0;
    }
    
    int OnSecurityChange(final int n, final int n2, final int n3) {
        return 0;
    }
}
