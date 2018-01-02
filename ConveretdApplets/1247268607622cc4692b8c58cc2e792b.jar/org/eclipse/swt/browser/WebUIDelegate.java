// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.webkit.IWebOpenPanelResultListener;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.internal.ole.win32.IUnknown;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.DOCINFO;
import org.eclipse.swt.internal.webkit.IWebDataSource;
import org.eclipse.swt.internal.webkit.IWebFramePrivate;
import org.eclipse.swt.internal.webkit.WebKit_win32;
import org.eclipse.swt.internal.win32.PRINTDLG;
import org.eclipse.swt.internal.ole.win32.VARIANT;
import org.eclipse.swt.internal.ole.win32.IPropertyBag;
import org.eclipse.swt.internal.webkit.IWebView;
import org.eclipse.swt.internal.webkit.IWebFrame;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.webkit.IWebURLRequest;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.ole.win32.COMObject;

class WebUIDelegate
{
    COMObject iWebUIDelegate;
    int refCount;
    String lastHoveredLinkURL;
    Browser browser;
    Point size;
    Point location;
    boolean menuBar;
    boolean toolBar;
    boolean statusBar;
    boolean prompt;
    
    WebUIDelegate(final Browser browser) {
        this.refCount = 0;
        this.menuBar = true;
        this.toolBar = true;
        this.statusBar = true;
        this.prompt = true;
        this.createCOMInterfaces();
        this.browser = browser;
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    int canTakeFocus(final int n, final int n2, final int n3) {
        OS.MoveMemory(n3, new int[] { 1 }, 4);
        return 0;
    }
    
    int contextMenuItemsForElement(final int n, final int n2, final int n3, final int n4) {
        final Point cursorLocation = this.browser.getDisplay().getCursorLocation();
        final Event event = new Event();
        event.x = cursorLocation.x;
        event.y = cursorLocation.y;
        this.browser.notifyListeners(35, event);
        if (event.doit) {
            final Menu menu = this.browser.getMenu();
            if (menu == null || menu.isDisposed()) {
                OS.MoveMemory(n4, new int[] { n3 }, C.PTR_SIZEOF);
                return 0;
            }
            if (event.x != cursorLocation.x || event.y != cursorLocation.y) {
                menu.setLocation(event.x, event.y);
            }
            menu.setVisible(true);
        }
        OS.MoveMemory(n4, new int[1], C.PTR_SIZEOF);
        return 0;
    }
    
    void createCOMInterfaces() {
        this.iWebUIDelegate = new COMObject(new int[] { 2, 0, 0, 3, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 4, 4, 2, 3, 4, 4, 3, 3, 3, 3, 5, 3, 1, 3, 2, 2, 2, 2, 3, 2, 3, 1, 1, 0, 0, 1, 1, 2, 2, 2, 2, 3, 5, 2, 2, 3, 1, 2, 2, 4, 10, 3 }) {
            public int method0(final int[] array) {
                return WebUIDelegate.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return WebUIDelegate.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return WebUIDelegate.this.Release();
            }
            
            public int method3(final int[] array) {
                return WebUIDelegate.this.createWebViewWithRequest(array[0], array[1], array[2]);
            }
            
            public int method4(final int[] array) {
                return WebUIDelegate.this.webViewShow(array[0]);
            }
            
            public int method5(final int[] array) {
                return WebUIDelegate.this.webViewClose(array[0]);
            }
            
            public int method6(final int[] array) {
                return -2147467263;
            }
            
            public int method7(final int[] array) {
                return -2147467263;
            }
            
            public int method8(final int[] array) {
                return -2147467263;
            }
            
            public int method9(final int[] array) {
                return -2147467263;
            }
            
            public int method10(final int[] array) {
                return WebUIDelegate.this.setStatusText(array[0], array[1]);
            }
            
            public int method11(final int[] array) {
                return -2147467263;
            }
            
            public int method12(final int[] array) {
                return -2147467263;
            }
            
            public int method13(final int[] array) {
                return WebUIDelegate.this.setToolbarsVisible(array[0], array[1]);
            }
            
            public int method14(final int[] array) {
                return -2147467263;
            }
            
            public int method15(final int[] array) {
                return WebUIDelegate.this.setStatusBarVisible(array[0], array[1]);
            }
            
            public int method16(final int[] array) {
                return -2147467263;
            }
            
            public int method17(final int[] array) {
                return -2147467263;
            }
            
            public int method18(final int[] array) {
                return WebUIDelegate.this.setFrame(array[0], array[1]);
            }
            
            public int method19(final int[] array) {
                return -2147467263;
            }
            
            public int method20(final int[] array) {
                return -2147467263;
            }
            
            public int method21(final int[] array) {
                return -2147467263;
            }
            
            public int method22(final int[] array) {
                return WebUIDelegate.this.runJavaScriptAlertPanelWithMessage(array[0], array[1]);
            }
            
            public int method23(final int[] array) {
                return WebUIDelegate.this.runJavaScriptConfirmPanelWithMessage(array[0], array[1], array[2]);
            }
            
            public int method24(final int[] array) {
                return WebUIDelegate.this.runJavaScriptTextInputPanelWithPrompt(array[0], array[1], array[2], array[3]);
            }
            
            public int method25(final int[] array) {
                return WebUIDelegate.this.runBeforeUnloadConfirmPanelWithMessage(array[0], array[1], array[2], array[3]);
            }
            
            public int method26(final int[] array) {
                return WebUIDelegate.this.runOpenPanelForFileButtonWithResultListener(array[0], array[1]);
            }
            
            public int method27(final int[] array) {
                return WebUIDelegate.this.mouseDidMoveOverElement(array[0], array[1], array[2]);
            }
            
            public int method28(final int[] array) {
                return WebUIDelegate.this.contextMenuItemsForElement(array[0], array[1], array[2], array[3]);
            }
            
            public int method29(final int[] array) {
                return -2147467263;
            }
            
            public int method30(final int[] array) {
                return -2147467263;
            }
            
            public int method31(final int[] array) {
                return -2147467263;
            }
            
            public int method32(final int[] array) {
                return -2147467263;
            }
            
            public int method33(final int[] array) {
                return -2147467263;
            }
            
            public int method34(final int[] array) {
                return -2147467263;
            }
            
            public int method35(final int[] array) {
                return -2147467263;
            }
            
            public int method36(final int[] array) {
                return -2147467263;
            }
            
            public int method37(final int[] array) {
                return -2147467263;
            }
            
            public int method38(final int[] array) {
                return -2147467263;
            }
            
            public int method39(final int[] array) {
                return -2147467263;
            }
            
            public int method40(final int[] array) {
                return -2147467263;
            }
            
            public int method41(final int[] array) {
                return -2147467263;
            }
            
            public int method42(final int[] array) {
                return WebUIDelegate.this.canTakeFocus(array[0], array[1], array[2]);
            }
            
            public int method43(final int[] array) {
                return WebUIDelegate.this.takeFocus(array[0], array[1]);
            }
            
            public int method44(final int[] array) {
                return -2147467263;
            }
            
            public int method45(final int[] array) {
                return 0;
            }
            
            public int method46(final int[] array) {
                return -2147467263;
            }
            
            public int method47(final int[] array) {
                return -2147467263;
            }
            
            public int method48(final int[] array) {
                return -2147467263;
            }
            
            public int method49(final int[] array) {
                return -2147467263;
            }
            
            public int method50(final int[] array) {
                return -2147467263;
            }
            
            public int method51(final int[] array) {
                return WebUIDelegate.this.printFrame(array[0], array[1]);
            }
            
            public int method52(final int[] array) {
                return -2147467263;
            }
            
            public int method53(final int[] array) {
                return -2147467263;
            }
            
            public int method54(final int[] array) {
                return -2147467263;
            }
            
            public int method55(final int[] array) {
                return -2147467263;
            }
            
            public int method56(final int[] array) {
                return -2147467263;
            }
            
            public int method57(final int[] array) {
                return -2147467263;
            }
            
            public int method58(final int[] array) {
                return -2147467263;
            }
            
            public int method59(final int[] array) {
                return -2147467263;
            }
            
            public int method60(final int[] array) {
                return -2147467263;
            }
            
            public int method61(final int[] array) {
                return -2147467263;
            }
            
            public int method62(final int[] array) {
                return WebUIDelegate.this.setMenuBarVisible(array[0], array[1]);
            }
            
            public int method63(final int[] array) {
                return -2147467263;
            }
            
            public int method64(final int[] array) {
                return -2147467263;
            }
            
            public int method65(final int[] array) {
                return -2147467263;
            }
        };
    }
    
    int createWebViewWithRequest(final int n, final int n2, final int n3) {
        final WindowEvent windowEvent = new WindowEvent(this.browser);
        windowEvent.display = this.browser.getDisplay();
        windowEvent.widget = this.browser;
        windowEvent.required = true;
        final OpenWindowListener[] openWindowListeners = this.browser.webBrowser.openWindowListeners;
        for (int i = 0; i < openWindowListeners.length; ++i) {
            openWindowListeners[i].open(windowEvent);
        }
        Browser browser = null;
        if (windowEvent.browser != null && windowEvent.browser.webBrowser instanceof WebKit) {
            browser = windowEvent.browser;
        }
        if (browser != null && !browser.isDisposed()) {
            final IWebView webView = ((WebKit)browser.webBrowser).webView;
            OS.MoveMemory(n3, new int[] { webView.getAddress() }, OS.PTR_SIZEOF);
            if (n2 != 0) {
                final IWebURLRequest webURLRequest = new IWebURLRequest(n2);
                final int[] array = { 0 };
                if (webURLRequest.URL(array) != 0 || array[0] == 0) {
                    return 0;
                }
                final String bstr = WebKit.extractBSTR(array[0]);
                COM.SysFreeString(array[0]);
                if (bstr.length() != 0) {
                    array[0] = 0;
                    if (webView.mainFrame(array) != 0 || array[0] == 0) {
                        return 0;
                    }
                    final IWebFrame webFrame = new IWebFrame(array[0]);
                    webFrame.loadRequest(webURLRequest.getAddress());
                    webFrame.Release();
                }
            }
            return 0;
        }
        return -2147467263;
    }
    
    protected void disposeCOMInterfaces() {
        if (this.iWebUIDelegate != null) {
            this.iWebUIDelegate.dispose();
            this.iWebUIDelegate = null;
        }
    }
    
    int getAddress() {
        return this.iWebUIDelegate.getAddress();
    }
    
    int mouseDidMoveOverElement(final int n, final int n2, final int n3) {
        if (n2 == 0) {
            return 0;
        }
        final IPropertyBag propertyBag = new IPropertyBag(n2);
        final int bstr = WebKit.createBSTR("WebElementLinkURLKey");
        final int getProcessHeap = OS.GetProcessHeap();
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, VARIANT.sizeof);
        if (propertyBag.Read(bstr, heapAlloc, null) != 0 || heapAlloc == 0) {
            return 0;
        }
        String bstr2 = null;
        final VARIANT variant = new VARIANT();
        COM.MoveMemory(variant, heapAlloc, VARIANT.sizeof);
        if (variant.vt == 8) {
            bstr2 = WebKit.extractBSTR(variant.lVal);
        }
        OS.HeapFree(getProcessHeap, 0, heapAlloc);
        final StatusTextListener[] statusTextListeners = this.browser.webBrowser.statusTextListeners;
        if (bstr2 == null || bstr2.length() == 0) {
            if (this.lastHoveredLinkURL == null) {
                return 0;
            }
            this.lastHoveredLinkURL = null;
            final StatusTextEvent statusTextEvent = new StatusTextEvent(this.browser);
            statusTextEvent.display = this.browser.getDisplay();
            statusTextEvent.widget = this.browser;
            statusTextEvent.text = "";
            for (int i = 0; i < statusTextListeners.length; ++i) {
                statusTextListeners[i].changed(statusTextEvent);
            }
            return 0;
        }
        else {
            if (bstr2.equals(this.lastHoveredLinkURL)) {
                return 0;
            }
            this.lastHoveredLinkURL = bstr2;
            final StatusTextEvent statusTextEvent2 = new StatusTextEvent(this.browser);
            statusTextEvent2.display = this.browser.getDisplay();
            statusTextEvent2.widget = this.browser;
            statusTextEvent2.text = bstr2;
            for (int j = 0; j < statusTextListeners.length; ++j) {
                statusTextListeners[j].changed(statusTextEvent2);
            }
            return 0;
        }
    }
    
    int printFrame(final int n, final int n2) {
        final IWebFrame webFrame = new IWebFrame(n2);
        final PRINTDLG printdlg = new PRINTDLG();
        printdlg.lStructSize = PRINTDLG.sizeof;
        printdlg.Flags = 256;
        OS.PrintDlg(printdlg);
        final int hdc = printdlg.hDC;
        final int[] array = { 0 };
        if (webFrame.QueryInterface(WebKit_win32.IID_IWebFramePrivate, array) != 0 || array[0] == 0) {
            return 0;
        }
        final IWebFramePrivate webFramePrivate = new IWebFramePrivate(array[0]);
        webFramePrivate.setInPrintingMode(1, hdc);
        final int[] array2 = { 0 };
        if (webFramePrivate.getPrintedPageCount(hdc, array2) != 0 || array2[0] == 0) {
            webFramePrivate.Release();
            return 0;
        }
        final int n3 = array2[0];
        String bstr = null;
        array[0] = 0;
        if (webFrame.dataSource(array) == 0 && array[0] != 0) {
            final IWebDataSource webDataSource = new IWebDataSource(array[0]);
            array[0] = 0;
            final int pageTitle = webDataSource.pageTitle(array);
            webDataSource.Release();
            if (pageTitle == 0 && array[0] != 0) {
                bstr = WebKit.extractBSTR(array[0]);
                COM.SysFreeString(array[0]);
            }
        }
        final DOCINFO docinfo = new DOCINFO();
        docinfo.cbSize = DOCINFO.sizeof;
        final int getProcessHeap = OS.GetProcessHeap();
        int heapAlloc = 0;
        if (bstr != null && bstr.length() != 0) {
            final TCHAR tchar = new TCHAR(0, bstr, true);
            final int n4 = tchar.length() * TCHAR.sizeof;
            heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n4);
            OS.MoveMemory(heapAlloc, tchar, n4);
            docinfo.lpszDocName = heapAlloc;
        }
        final int startDoc = OS.StartDoc(hdc, docinfo);
        if (heapAlloc != 0) {
            OS.HeapFree(getProcessHeap, 0, heapAlloc);
        }
        if (startDoc >= 0) {
            for (int i = 0; i < n3; ++i) {
                OS.StartPage(hdc);
                webFramePrivate.spoolPages(hdc, i, i, null);
                OS.EndPage(hdc);
            }
            webFramePrivate.setInPrintingMode(0, hdc);
            OS.EndDoc(hdc);
        }
        webFramePrivate.Release();
        return 0;
    }
    
    int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown)) {
            OS.MoveMemory(n2, new int[] { this.iWebUIDelegate.getAddress() }, OS.PTR_SIZEOF);
            new IUnknown(this.iWebUIDelegate.getAddress()).AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, WebKit_win32.IID_IWebUIDelegate)) {
            OS.MoveMemory(n2, new int[] { this.iWebUIDelegate.getAddress() }, OS.PTR_SIZEOF);
            new IUnknown(this.iWebUIDelegate.getAddress()).AddRef();
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
    
    int runBeforeUnloadConfirmPanelWithMessage(final int n, final int n2, final int n3, final int n4) {
        if (!this.prompt) {
            return 0;
        }
        final Shell shell = this.browser.getShell();
        final String bstr = WebKit.extractBSTR(n2);
        final StringBuffer sb = new StringBuffer(Compatibility.getMessage("SWT_OnBeforeUnload_Message1"));
        sb.append("\n\n");
        sb.append(bstr);
        sb.append("\n\n");
        sb.append(Compatibility.getMessage("SWT_OnBeforeUnload_Message2"));
        final MessageBox messageBox = new MessageBox(shell, 292);
        messageBox.setMessage(sb.toString());
        OS.MoveMemory(n4, new int[] { messageBox.open() == 32 }, 4);
        return 0;
    }
    
    int runJavaScriptAlertPanelWithMessage(final int n, final int n2) {
        this.showAlertMessage("Javascript", WebKit.extractBSTR(n2));
        return 0;
    }
    
    int runJavaScriptConfirmPanelWithMessage(final int n, final int n2, final int n3) {
        OS.MoveMemory(n3, new int[] { this.showConfirmPanel("Javascript", WebKit.extractBSTR(n2)) == 32 }, 4);
        return 0;
    }
    
    int runJavaScriptTextInputPanelWithPrompt(final int n, final int n2, final int n3, final int n4) {
        final String showTextPrompter = this.showTextPrompter("Javascript", WebKit.extractBSTR(n2), WebKit.extractBSTR(n3));
        final int[] array = { 0 };
        if (showTextPrompter != null) {
            array[0] = WebKit.createBSTR(showTextPrompter);
        }
        OS.MoveMemory(n4, array, C.PTR_SIZEOF);
        return 0;
    }
    
    int runOpenPanelForFileButtonWithResultListener(final int n, final int n2) {
        final String open = new FileDialog(this.browser.getShell(), 0).open();
        final IWebOpenPanelResultListener webOpenPanelResultListener = new IWebOpenPanelResultListener(n2);
        if (open == null) {
            webOpenPanelResultListener.cancel();
        }
        else {
            webOpenPanelResultListener.chooseFilename(WebKit.createBSTR(open));
        }
        return 0;
    }
    
    int setFrame(final int n, final int n2) {
        final RECT rect = new RECT();
        COM.MoveMemory(rect, n2, RECT.sizeof);
        this.location = this.browser.getDisplay().map(this.browser, null, rect.left, rect.top);
        final int n3 = rect.right - rect.left;
        final int n4 = rect.bottom - rect.top;
        if (n4 < 0 || n3 < 0 || (n3 == 0 && n4 == 0)) {
            return 0;
        }
        this.size = new Point(n3, n4);
        return 0;
    }
    
    int setMenuBarVisible(final int n, final int n2) {
        this.menuBar = (n2 == 1);
        return 0;
    }
    
    int setStatusBarVisible(final int n, final int n2) {
        this.statusBar = (n2 == 1);
        return 0;
    }
    
    int setStatusText(final int n, final int n2) {
        final String bstr = WebKit.extractBSTR(n2);
        if (bstr.length() == 0) {
            return 0;
        }
        final StatusTextEvent statusTextEvent = new StatusTextEvent(this.browser);
        statusTextEvent.display = this.browser.getDisplay();
        statusTextEvent.widget = this.browser;
        statusTextEvent.text = bstr;
        final StatusTextListener[] statusTextListeners = this.browser.webBrowser.statusTextListeners;
        for (int i = 0; i < statusTextListeners.length; ++i) {
            statusTextListeners[i].changed(statusTextEvent);
        }
        return 0;
    }
    
    int setToolbarsVisible(final int n, final int n2) {
        this.toolBar = (n2 == 1);
        return 0;
    }
    
    void showAlertMessage(final String text, final String text2) {
        final Shell shell = this.browser.getShell();
        final Shell shell2 = new Shell(shell, 67680);
        final GridLayout layout = new GridLayout(2, false);
        layout.horizontalSpacing = 10;
        layout.verticalSpacing = 20;
        final GridLayout gridLayout = layout;
        final GridLayout gridLayout2 = layout;
        final int n = 10;
        gridLayout2.marginHeight = n;
        gridLayout.marginWidth = n;
        shell2.setLayout(layout);
        shell2.setText(text);
        new Label(shell2, 0).setImage(shell2.getDisplay().getSystemImage(8));
        final Label label = new Label(shell2, 64);
        label.setText(text2);
        final int n2 = shell.getMonitor().getBounds().width * 2 / 3;
        final int x = label.computeSize(-1, -1).x;
        final GridData layoutData = new GridData(4, 16777216, true, false);
        layoutData.widthHint = Math.min(x, n2);
        label.setLayoutData(layoutData);
        final Button defaultButton = new Button(shell2, 8);
        defaultButton.setText(SWT.getMessage("SWT_OK"));
        final int x2 = defaultButton.computeSize(-1, -1).x;
        final GridData layoutData2 = new GridData();
        layoutData2.horizontalAlignment = 16777216;
        layoutData2.verticalAlignment = 16777216;
        layoutData2.horizontalSpan = 2;
        layoutData2.widthHint = Math.max(x2, 75);
        defaultButton.setLayoutData(layoutData2);
        defaultButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent selectionEvent) {
                shell2.dispose();
            }
        });
        shell2.setDefaultButton(defaultButton);
        shell2.pack();
        final Rectangle bounds = shell.getBounds();
        final Rectangle bounds2 = shell2.getBounds();
        shell2.setLocation(shell.getLocation().x + (bounds.width - bounds2.width) / 2, shell.getLocation().y + (bounds.height - bounds2.height) / 2);
        shell2.open();
        final Display display = this.browser.getDisplay();
        while (!shell2.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
    
    int showConfirmPanel(final String text, final String text2) {
        final Shell shell = this.browser.getShell();
        final Shell shell2 = new Shell(shell, 67680);
        final GridLayout layout = new GridLayout(2, false);
        layout.horizontalSpacing = 10;
        layout.verticalSpacing = 20;
        final GridLayout gridLayout = layout;
        final GridLayout gridLayout2 = layout;
        final int n = 10;
        gridLayout2.marginHeight = n;
        gridLayout.marginWidth = n;
        shell2.setLayout(layout);
        shell2.setText(text);
        final Label label = new Label(shell2, 0);
        label.setImage(shell2.getDisplay().getSystemImage(4));
        label.setLayoutData(new GridData());
        final Label label2 = new Label(shell2, 64);
        label2.setText(text2);
        final int n2 = shell.getMonitor().getBounds().width * 2 / 3;
        final int x = label2.computeSize(-1, -1).x;
        final GridData layoutData = new GridData(4, 16777216, true, false);
        layoutData.widthHint = Math.min(x, n2);
        label2.setLayoutData(layoutData);
        final Composite composite = new Composite(shell2, 0);
        composite.setLayoutData(new GridData(16777216, 16777216, true, true, 2, 1));
        composite.setLayout(new GridLayout(2, true));
        final Button defaultButton = new Button(composite, 8);
        defaultButton.setText(SWT.getMessage("SWT_OK"));
        final GridData gridData = new GridData();
        gridData.horizontalAlignment = 16777216;
        gridData.verticalAlignment = 16777216;
        defaultButton.setLayoutData(gridData);
        final Button button = new Button(composite, 8);
        button.setText(SWT.getMessage("SWT_Cancel"));
        button.setLayoutData(gridData);
        gridData.widthHint = Math.max(button.computeSize(-1, -1).x, 75);
        final int[] array = { 0 };
        defaultButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent selectionEvent) {
                array[0] = 32;
                shell2.dispose();
            }
        });
        button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent selectionEvent) {
                array[0] = 256;
                shell2.dispose();
            }
        });
        shell2.setDefaultButton(defaultButton);
        shell2.pack();
        final Rectangle bounds = shell.getBounds();
        final Rectangle bounds2 = shell2.getBounds();
        shell2.setLocation(shell.getLocation().x + (bounds.width - bounds2.width) / 2, shell.getLocation().y + (bounds.height - bounds2.height) / 2);
        shell2.open();
        final Display display = this.browser.getDisplay();
        while (!shell2.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return array[0];
    }
    
    String showTextPrompter(final String text, final String text2, final String text3) {
        final Shell shell = this.browser.getShell();
        final Shell shell2 = new Shell(shell, 67680);
        shell2.setLayout(new GridLayout());
        shell2.setText(text);
        final Label label = new Label(shell2, 0);
        label.setLayoutData(new GridData(768));
        label.setText(text2);
        final Text text4 = new Text(shell2, 2052);
        final GridData layoutData = new GridData(768);
        layoutData.widthHint = 300;
        text4.setLayoutData(layoutData);
        text4.setText(text3);
        final Composite composite = new Composite(shell2, 0);
        composite.setLayout(new GridLayout(2, true));
        composite.setLayoutData(new GridData(64));
        final Button defaultButton = new Button(composite, 8);
        defaultButton.setText(SWT.getMessage("SWT_OK"));
        defaultButton.setLayoutData(new GridData(768));
        final String[] array = { null };
        defaultButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent selectionEvent) {
                array[0] = text4.getText();
                shell2.dispose();
            }
        });
        final Button button = new Button(composite, 8);
        button.setText(SWT.getMessage("SWT_Cancel"));
        button.setLayoutData(new GridData(768));
        button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent selectionEvent) {
                shell2.dispose();
            }
        });
        shell2.setDefaultButton(defaultButton);
        shell2.pack();
        final Rectangle bounds = shell.getBounds();
        final Rectangle bounds2 = shell2.getBounds();
        shell2.setLocation(shell.getLocation().x + (bounds.width - bounds2.width) / 2, shell.getLocation().y + (bounds.height - bounds2.height) / 2);
        shell2.open();
        final Display display = this.browser.getDisplay();
        while (!shell2.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return array[0];
    }
    
    int takeFocus(final int n, final int n2) {
        final int n3 = (n2 == 0) ? 8 : 16;
        ((WebKit)this.browser.webBrowser).traverseOut = true;
        this.browser.traverse(n3);
        return 0;
    }
    
    int webViewClose(final int n) {
        final WindowEvent windowEvent = new WindowEvent(this.browser);
        windowEvent.display = this.browser.getDisplay();
        windowEvent.widget = this.browser;
        final CloseWindowListener[] closeWindowListeners = this.browser.webBrowser.closeWindowListeners;
        for (int i = 0; i < closeWindowListeners.length; ++i) {
            closeWindowListeners[i].close(windowEvent);
        }
        this.browser.dispose();
        return 0;
    }
    
    int webViewFrame(final int n, final int n2) {
        OS.MoveMemory(n2, new RECT(), RECT.sizeof);
        return 0;
    }
    
    int webViewShow(final int n) {
        final WindowEvent windowEvent = new WindowEvent(this.browser);
        windowEvent.display = this.browser.getDisplay();
        windowEvent.widget = this.browser;
        if (this.location != null) {
            windowEvent.location = this.location;
        }
        if (this.size != null) {
            windowEvent.size = this.size;
        }
        windowEvent.addressBar = this.toolBar;
        windowEvent.menuBar = this.menuBar;
        windowEvent.statusBar = this.statusBar;
        windowEvent.toolBar = this.toolBar;
        final VisibilityWindowListener[] visibilityWindowListeners = this.browser.webBrowser.visibilityWindowListeners;
        for (int i = 0; i < visibilityWindowListeners.length; ++i) {
            visibilityWindowListeners[i].show(windowEvent);
        }
        this.location = null;
        this.size = null;
        return 0;
    }
}
