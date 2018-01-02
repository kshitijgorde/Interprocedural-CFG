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
import org.eclipse.swt.internal.mozilla.nsIURI;
import org.eclipse.swt.internal.mozilla.nsIWebProgressListener;
import org.eclipse.swt.internal.mozilla.nsIProgressDialog_1_8;
import org.eclipse.swt.internal.mozilla.nsIDownload_1_8;
import org.eclipse.swt.internal.mozilla.nsISupports;
import org.eclipse.swt.internal.mozilla.XPCOM;
import org.eclipse.swt.internal.mozilla.nsID;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.internal.mozilla.nsICancelable;
import org.eclipse.swt.internal.mozilla.XPCOMObject;

class Download_1_8
{
    XPCOMObject supports;
    XPCOMObject download;
    XPCOMObject progressDialog;
    XPCOMObject webProgressListener;
    nsICancelable cancelable;
    int refCount;
    Shell shell;
    Label status;
    Button cancel;
    static final boolean is32;
    
    static {
        is32 = (C.PTR_SIZEOF == 4);
    }
    
    Download_1_8() {
        this.refCount = 0;
        this.createCOMInterfaces();
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void createCOMInterfaces() {
        this.supports = new XPCOMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return Download_1_8.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Download_1_8.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Download_1_8.this.Release();
            }
        };
        this.download = new XPCOMObject(new int[] { 2, 0, 0, 4, 6, 3, 4, 3, Download_1_8.is32 ? 10 : 6, Download_1_8.is32 ? 8 : 7, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }) {
            public int method0(final int[] array) {
                return Download_1_8.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Download_1_8.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Download_1_8.this.Release();
            }
            
            public int method3(final int[] array) {
                return Download_1_8.this.OnStateChange(array[0], array[1], array[2], array[3]);
            }
            
            public int method4(final int[] array) {
                return Download_1_8.this.OnProgressChange(array[0], array[1], array[2], array[3], array[4], array[5]);
            }
            
            public int method5(final int[] array) {
                return Download_1_8.this.OnLocationChange(array[0], array[1], array[2]);
            }
            
            public int method6(final int[] array) {
                return Download_1_8.this.OnStatusChange(array[0], array[1], array[2], array[3]);
            }
            
            public int method7(final int[] array) {
                return Download_1_8.this.OnSecurityChange(array[0], array[1], array[2]);
            }
            
            public int method8(final int[] array) {
                if (array.length == 10) {
                    return Download_1_8.this.OnProgressChange64_32(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7], array[8], array[9]);
                }
                return Download_1_8.this.OnProgressChange64(array[0], array[1], array[2], array[3], array[4], array[5]);
            }
            
            public int method9(final int[] array) {
                if (array.length == 8) {
                    return Download_1_8.this.Init_32(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7]);
                }
                return Download_1_8.this.Init(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
            }
            
            public int method10(final int[] array) {
                return Download_1_8.this.GetTargetFile(array[0]);
            }
            
            public int method11(final int[] array) {
                return Download_1_8.this.GetPercentComplete(array[0]);
            }
            
            public int method12(final int[] array) {
                return Download_1_8.this.GetAmountTransferred(array[0]);
            }
            
            public int method13(final int[] array) {
                return Download_1_8.this.GetSize(array[0]);
            }
            
            public int method14(final int[] array) {
                return Download_1_8.this.GetSource(array[0]);
            }
            
            public int method15(final int[] array) {
                return Download_1_8.this.GetTarget(array[0]);
            }
            
            public int method16(final int[] array) {
                return Download_1_8.this.GetCancelable(array[0]);
            }
            
            public int method17(final int[] array) {
                return Download_1_8.this.GetDisplayName(array[0]);
            }
            
            public int method18(final int[] array) {
                return Download_1_8.this.GetStartTime(array[0]);
            }
            
            public int method19(final int[] array) {
                return Download_1_8.this.GetMIMEInfo(array[0]);
            }
        };
        this.progressDialog = new XPCOMObject(new int[] { 2, 0, 0, 4, 6, 3, 4, 3, Download_1_8.is32 ? 10 : 6, Download_1_8.is32 ? 8 : 7, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }) {
            public int method0(final int[] array) {
                return Download_1_8.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Download_1_8.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Download_1_8.this.Release();
            }
            
            public int method3(final int[] array) {
                return Download_1_8.this.OnStateChange(array[0], array[1], array[2], array[3]);
            }
            
            public int method4(final int[] array) {
                return Download_1_8.this.OnProgressChange(array[0], array[1], array[2], array[3], array[4], array[5]);
            }
            
            public int method5(final int[] array) {
                return Download_1_8.this.OnLocationChange(array[0], array[1], array[2]);
            }
            
            public int method6(final int[] array) {
                return Download_1_8.this.OnStatusChange(array[0], array[1], array[2], array[3]);
            }
            
            public int method7(final int[] array) {
                return Download_1_8.this.OnSecurityChange(array[0], array[1], array[2]);
            }
            
            public int method8(final int[] array) {
                if (array.length == 10) {
                    return Download_1_8.this.OnProgressChange64_32(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7], array[8], array[9]);
                }
                return Download_1_8.this.OnProgressChange64(array[0], array[1], array[2], array[3], array[4], array[5]);
            }
            
            public int method9(final int[] array) {
                if (array.length == 8) {
                    return Download_1_8.this.Init_32(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7]);
                }
                return Download_1_8.this.Init(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
            }
            
            public int method10(final int[] array) {
                return Download_1_8.this.GetTargetFile(array[0]);
            }
            
            public int method11(final int[] array) {
                return Download_1_8.this.GetPercentComplete(array[0]);
            }
            
            public int method12(final int[] array) {
                return Download_1_8.this.GetAmountTransferred(array[0]);
            }
            
            public int method13(final int[] array) {
                return Download_1_8.this.GetSize(array[0]);
            }
            
            public int method14(final int[] array) {
                return Download_1_8.this.GetSource(array[0]);
            }
            
            public int method15(final int[] array) {
                return Download_1_8.this.GetTarget(array[0]);
            }
            
            public int method16(final int[] array) {
                return Download_1_8.this.GetCancelable(array[0]);
            }
            
            public int method17(final int[] array) {
                return Download_1_8.this.GetDisplayName(array[0]);
            }
            
            public int method18(final int[] array) {
                return Download_1_8.this.GetStartTime(array[0]);
            }
            
            public int method19(final int[] array) {
                return Download_1_8.this.GetMIMEInfo(array[0]);
            }
            
            public int method20(final int[] array) {
                return Download_1_8.this.Open(array[0]);
            }
            
            public int method21(final int[] array) {
                return Download_1_8.this.GetCancelDownloadOnClose(array[0]);
            }
            
            public int method22(final int[] array) {
                return Download_1_8.this.SetCancelDownloadOnClose(array[0]);
            }
            
            public int method23(final int[] array) {
                return Download_1_8.this.GetObserver(array[0]);
            }
            
            public int method24(final int[] array) {
                return Download_1_8.this.SetObserver(array[0]);
            }
            
            public int method25(final int[] array) {
                return Download_1_8.this.GetDialog(array[0]);
            }
            
            public int method26(final int[] array) {
                return Download_1_8.this.SetDialog(array[0]);
            }
        };
        this.webProgressListener = new XPCOMObject(new int[] { 2, 0, 0, 4, 6, 3, 4, 3 }) {
            public int method0(final int[] array) {
                return Download_1_8.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Download_1_8.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Download_1_8.this.Release();
            }
            
            public int method3(final int[] array) {
                return Download_1_8.this.OnStateChange(array[0], array[1], array[2], array[3]);
            }
            
            public int method4(final int[] array) {
                return Download_1_8.this.OnProgressChange(array[0], array[1], array[2], array[3], array[4], array[5]);
            }
            
            public int method5(final int[] array) {
                return Download_1_8.this.OnLocationChange(array[0], array[1], array[2]);
            }
            
            public int method6(final int[] array) {
                return Download_1_8.this.OnStatusChange(array[0], array[1], array[2], array[3]);
            }
            
            public int method7(final int[] array) {
                return Download_1_8.this.OnSecurityChange(array[0], array[1], array[2]);
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
        if (nsID.Equals(nsIDownload_1_8.NS_IDOWNLOAD_IID)) {
            C.memmove(n2, new int[] { this.download.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIProgressDialog_1_8.NS_IPROGRESSDIALOG_IID)) {
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
    
    int Init_32(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        return this.Init(n, n2, n3, n4, (n6 << 32) + n5, n7, n8);
    }
    
    int Init(final int n, final int n2, final int n3, final int n4, final long n5, final int n6, final int n7) {
        this.cancelable = new nsICancelable(n7);
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
        final nsIURI nsIURI2 = new nsIURI(n2);
        final int nsEmbedCString_new2 = XPCOM.nsEmbedCString_new();
        final int getPath = nsIURI2.GetPath(nsEmbedCString_new2);
        if (getPath != 0) {
            Mozilla.error(getPath);
        }
        final int nsEmbedCString_Length2 = XPCOM.nsEmbedCString_Length(nsEmbedCString_new2);
        final int nsEmbedCString_get2 = XPCOM.nsEmbedCString_get(nsEmbedCString_new2);
        final byte[] array2 = new byte[nsEmbedCString_Length2];
        C.memmove(array2, nsEmbedCString_get2, nsEmbedCString_Length2);
        XPCOM.nsEmbedCString_delete(nsEmbedCString_new2);
        final String s2 = new String(array2);
        final String substring = s2.substring(s2.lastIndexOf(System.getProperty("file.separator")) + 1);
        final Listener listener = new Listener() {
            public void handleEvent(final Event event) {
                if (event.widget == Download_1_8.this.cancel) {
                    Download_1_8.this.shell.close();
                }
                if (Download_1_8.this.cancelable != null) {
                    final int cancel = Download_1_8.this.cancelable.Cancel(-2142568446);
                    if (cancel != 0) {
                        Mozilla.error(cancel);
                    }
                }
                Download_1_8.this.shell = null;
                Download_1_8.this.cancelable = null;
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
    
    int GetAmountTransferred(final int n) {
        return -2147467263;
    }
    
    int GetCancelable(final int n) {
        return -2147467263;
    }
    
    int GetDisplayName(final int n) {
        return -2147467263;
    }
    
    int GetMIMEInfo(final int n) {
        return -2147467263;
    }
    
    int GetPercentComplete(final int n) {
        return -2147467263;
    }
    
    int GetSize(final int n) {
        return -2147467263;
    }
    
    int GetSource(final int n) {
        return -2147467263;
    }
    
    int GetStartTime(final int n) {
        return -2147467263;
    }
    
    int GetTarget(final int n) {
        return -2147467263;
    }
    
    int GetTargetFile(final int n) {
        return -2147467263;
    }
    
    int GetCancelDownloadOnClose(final int n) {
        return -2147467263;
    }
    
    int GetDialog(final int n) {
        return -2147467263;
    }
    
    int GetObserver(final int n) {
        return -2147467263;
    }
    
    int Open(final int n) {
        return -2147467263;
    }
    
    int SetCancelDownloadOnClose(final int n) {
        return -2147467263;
    }
    
    int SetDialog(final int n) {
        return -2147467263;
    }
    
    int SetObserver(final int n) {
        return -2147467263;
    }
    
    int OnLocationChange(final int n, final int n2, final int n3) {
        return 0;
    }
    
    int OnProgressChange(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return this.OnProgressChange64(n, n2, n3, n4, n5, n6);
    }
    
    int OnProgressChange64_32(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        return this.OnProgressChange64(n, n2, (n4 << 32) + n3, (n6 << 32) + n5, (n8 << 32) + n7, (n10 << 32) + n9);
    }
    
    int OnProgressChange64(final int n, final int n2, final long n3, final long n4, final long n5, final long n6) {
        final long n7 = n5 / 1024L;
        final long n8 = n6 / 1024L;
        if (this.shell != null && !this.shell.isDisposed()) {
            this.status.setText(Compatibility.getMessage("SWT_Download_Status", new Object[] { new Long(n7), new Long(n8) }));
            this.shell.layout(true);
            this.shell.getDisplay().update();
        }
        return 0;
    }
    
    int OnSecurityChange(final int n, final int n2, final int n3) {
        return 0;
    }
    
    int OnStateChange(final int n, final int n2, final int n3, final int n4) {
        if ((n3 & 0x10) != 0x0) {
            this.cancelable = null;
            if (this.shell != null && !this.shell.isDisposed()) {
                this.shell.dispose();
            }
            this.shell = null;
        }
        return 0;
    }
    
    int OnStatusChange(final int n, final int n2, final int n3, final int n4) {
        return 0;
    }
}
