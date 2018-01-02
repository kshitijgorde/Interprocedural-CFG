// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.webkit.IWebMutableURLRequest;
import org.eclipse.swt.internal.webkit.WebKit_win32;
import org.eclipse.swt.internal.ole.win32.IUnknown;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.webkit.IWebURLResponse;
import java.io.File;
import org.eclipse.swt.internal.webkit.IWebDownload;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.internal.ole.win32.COMObject;

class WebDownloadDelegate
{
    COMObject iWebDownloadDelegate;
    Browser browser;
    int refCount;
    int status;
    long size;
    long totalSize;
    String url;
    static final int DOWNLOAD_FINISHED = 0;
    static final int DOWNLOAD_CANCELLED = 1;
    static final int DOWNLOAD_ERROR = 3;
    
    WebDownloadDelegate(final Browser browser) {
        this.refCount = 0;
        this.status = -1;
        this.createCOMInterfaces();
        this.browser = browser;
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void createCOMInterfaces() {
        this.iWebDownloadDelegate = new COMObject(new int[] { 2, 0, 0, 2, 2, 2, 2, 2, 2, 2, 3, 3, 4, 1, 1 }) {
            public int method0(final int[] array) {
                return WebDownloadDelegate.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return WebDownloadDelegate.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return WebDownloadDelegate.this.Release();
            }
            
            public int method3(final int[] array) {
                return WebDownloadDelegate.this.decideDestinationWithSuggestedFilename(array[0], array[1]);
            }
            
            public int method4(final int[] array) {
                return -2147467263;
            }
            
            public int method5(final int[] array) {
                return -2147467263;
            }
            
            public int method6(final int[] array) {
                return WebDownloadDelegate.this.didFailWithError(array[0], array[1]);
            }
            
            public int method7(final int[] array) {
                return -2147467263;
            }
            
            public int method8(final int[] array) {
                return WebDownloadDelegate.this.didReceiveDataOfLength(array[0], array[1]);
            }
            
            public int method9(final int[] array) {
                return WebDownloadDelegate.this.didReceiveResponse(array[0], array[1]);
            }
            
            public int method10(final int[] array) {
                return -2147467263;
            }
            
            public int method11(final int[] array) {
                return -2147467263;
            }
            
            public int method12(final int[] array) {
                return WebDownloadDelegate.this.willSendRequest(array[0], array[1], array[2], array[3]);
            }
            
            public int method13(final int[] array) {
                return WebDownloadDelegate.this.didBegin(array[0]);
            }
            
            public int method14(final int[] array) {
                return WebDownloadDelegate.this.didFinish(array[0]);
            }
        };
    }
    
    int decideDestinationWithSuggestedFilename(final int n, final int n2) {
        final String bstr = WebKit.extractBSTR(n2);
        final FileDialog fileDialog = new FileDialog(this.browser.getShell(), 8192);
        fileDialog.setText(SWT.getMessage("SWT_FileDownload"));
        fileDialog.setFileName(bstr);
        fileDialog.setOverwrite(true);
        final String open = fileDialog.open();
        final IWebDownload webDownload = new IWebDownload(n);
        webDownload.setDeletesFileUponFailure(0);
        if (open == null) {
            webDownload.setDestination(WebKit.createBSTR(""), 1);
            webDownload.cancel();
            webDownload.Release();
        }
        else {
            final File file = new File(open);
            if (file.exists()) {
                file.delete();
            }
            webDownload.setDestination(WebKit.createBSTR(open), 1);
            this.openDownloadWindow(webDownload, open);
        }
        return 0;
    }
    
    int didBegin(final int n) {
        new IWebDownload(n).AddRef();
        this.status = -1;
        this.size = 0L;
        this.totalSize = 0L;
        this.url = null;
        return 0;
    }
    
    int didFailWithError(final int n, final int n2) {
        new IWebDownload(n).Release();
        this.status = 3;
        return 0;
    }
    
    int didFinish(final int n) {
        new IWebDownload(n).Release();
        return this.status = 0;
    }
    
    int didReceiveDataOfLength(final int n, final int n2) {
        this.size += n2;
        return 0;
    }
    
    int didReceiveResponse(final int n, final int n2) {
        if (n2 != 0) {
            final IWebURLResponse webURLResponse = new IWebURLResponse(n2);
            final long[] array = { 0L };
            if (webURLResponse.expectedContentLength(array) == 0) {
                this.totalSize = array[0];
            }
            final int[] array2 = { 0 };
            if (webURLResponse.URL(array2) == 0 && array2[0] != 0) {
                this.url = WebKit.extractBSTR(array2[0]);
                COM.SysFreeString(array2[0]);
            }
        }
        return 0;
    }
    
    void disposeCOMInterfaces() {
        if (this.iWebDownloadDelegate != null) {
            this.iWebDownloadDelegate.dispose();
            this.iWebDownloadDelegate = null;
        }
    }
    
    int getAddress() {
        return this.iWebDownloadDelegate.getAddress();
    }
    
    void openDownloadWindow(final IWebDownload webDownload, final String s) {
        final Shell shell = new Shell();
        shell.setText(Compatibility.getMessage("SWT_FileDownload"));
        final GridLayout layout = new GridLayout();
        layout.marginHeight = 15;
        layout.marginWidth = 15;
        layout.verticalSpacing = 20;
        shell.setLayout(layout);
        final Label label = new Label(shell, 64);
        label.setText(Compatibility.getMessage("SWT_Download_Location", new Object[] { s, this.url }));
        final GridData layoutData = new GridData();
        layoutData.widthHint = Math.min(label.computeSize(-1, -1).x, this.browser.getMonitor().getBounds().width / 2);
        layoutData.horizontalAlignment = 4;
        layoutData.grabExcessHorizontalSpace = true;
        label.setLayoutData(layoutData);
        final Label label2 = new Label(shell, 0);
        label2.setText(Compatibility.getMessage("SWT_Download_Started"));
        label2.setLayoutData(new GridData(1808));
        final Button button = new Button(shell, 8);
        button.setText(Compatibility.getMessage("SWT_Cancel"));
        final GridData layoutData2 = new GridData();
        layoutData2.horizontalAlignment = 2;
        button.setLayoutData(layoutData2);
        final Listener listener = new Listener() {
            public void handleEvent(final Event event) {
                webDownload.cancel();
                WebDownloadDelegate.this.status = 1;
                webDownload.Release();
            }
        };
        button.addListener(13, listener);
        final Display display = this.browser.getDisplay();
        display.timerExec(500, new Runnable() {
            private final /* synthetic */ Listener val$cancelListener = listener;
            
            public void run() {
                if (shell.isDisposed() || WebDownloadDelegate.this.status == 0 || WebDownloadDelegate.this.status == 1) {
                    shell.dispose();
                    return;
                }
                if (WebDownloadDelegate.this.status == 3) {
                    label2.setText(Compatibility.getMessage("SWT_Download_Error"));
                    button.removeListener(13, this.val$cancelListener);
                    button.addListener(13, new Listener() {
                        public void handleEvent(final Event event) {
                            shell.dispose();
                        }
                    });
                    return;
                }
                label2.setText(Compatibility.getMessage("SWT_Download_Status", new Object[] { new Long(WebDownloadDelegate.this.size / 1024L), new Long(WebDownloadDelegate.this.totalSize / 1024L) }));
                display.timerExec(500, this);
            }
        });
        shell.pack();
        shell.open();
    }
    
    int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown)) {
            OS.MoveMemory(n2, new int[] { this.iWebDownloadDelegate.getAddress() }, OS.PTR_SIZEOF);
            new IUnknown(this.iWebDownloadDelegate.getAddress()).AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, WebKit_win32.IID_IWebDownloadDelegate)) {
            OS.MoveMemory(n2, new int[] { this.iWebDownloadDelegate.getAddress() }, OS.PTR_SIZEOF);
            new IUnknown(this.iWebDownloadDelegate.getAddress()).AddRef();
            return 0;
        }
        OS.MoveMemory(n2, new int[1], OS.PTR_SIZEOF);
        return -2147467262;
    }
    
    int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            this.disposeCOMInterfaces();
        }
        return this.refCount;
    }
    
    int willSendRequest(final int n, final int n2, final int n3, final int n4) {
        new IWebMutableURLRequest(n2).AddRef();
        OS.MoveMemory(n4, new int[] { n2 }, C.PTR_SIZEOF);
        return 0;
    }
}
