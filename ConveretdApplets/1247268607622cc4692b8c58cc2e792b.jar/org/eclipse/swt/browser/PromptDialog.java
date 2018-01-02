// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.internal.mozilla.nsIDOMWindow;
import org.eclipse.swt.internal.mozilla.nsICertificateDialogs;
import org.eclipse.swt.internal.mozilla.nsIServiceManager;
import org.eclipse.swt.internal.mozilla.XPCOM;
import org.eclipse.swt.custom.Bullet;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.internal.mozilla.nsIX509Cert;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Dialog;

class PromptDialog extends Dialog
{
    PromptDialog(final Shell shell, final int n) {
        super(shell, n);
    }
    
    PromptDialog(final Shell shell) {
        this(shell, 0);
    }
    
    void alertCheck(final String text, final String text2, final String text3, final int[] array) {
        final Shell parent = this.getParent();
        final Shell shell = new Shell(parent, 67680);
        if (text != null) {
            shell.setText(text);
        }
        shell.setLayout(new GridLayout());
        final Label label = new Label(shell, 64);
        label.setText(text2);
        final GridData layoutData = new GridData();
        layoutData.widthHint = Math.min(label.computeSize(-1, -1).x, parent.getMonitor().getBounds().width * 2 / 3);
        layoutData.horizontalAlignment = 4;
        layoutData.grabExcessHorizontalSpace = true;
        label.setLayoutData(layoutData);
        final Button button = (text3 != null) ? new Button(shell, 32) : null;
        if (button != null) {
            button.setText(text3);
            button.setSelection(array[0] != 0);
            final GridData layoutData2 = new GridData();
            layoutData2.horizontalAlignment = 1;
            button.setLayoutData(layoutData2);
        }
        final Button button2 = new Button(shell, 8);
        button2.setText(SWT.getMessage("SWT_OK"));
        final GridData layoutData3 = new GridData();
        layoutData3.horizontalAlignment = 2;
        button2.setLayoutData(layoutData3);
        button2.addListener(13, new Listener() {
            public void handleEvent(final Event event) {
                if (button != null) {
                    array[0] = (button.getSelection() ? 1 : 0);
                }
                shell.close();
            }
        });
        shell.pack();
        shell.open();
        final Display display = parent.getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
    
    boolean invalidCert(final Browser browser, final String text, final String[] array, final nsIX509Cert nsIX509Cert) {
        final Shell parent = this.getParent();
        final Display display = parent.getDisplay();
        final int n = parent.getMonitor().getBounds().width * 2 / 3;
        final Shell shell = new Shell(parent, 67680);
        shell.setText(Compatibility.getMessage("SWT_InvalidCert_Title"));
        shell.setLayout(new GridLayout());
        final Composite composite = new Composite(shell, 0);
        composite.setLayout(new GridLayout(2, false));
        new Label(composite, 0).setImage(display.getSystemImage(8));
        final Text text2 = new Text(composite, 64);
        text2.setLayoutData(new GridData(4, 16777216, true, false));
        text2.setEditable(false);
        text2.setBackground(shell.getBackground());
        text2.setText(text);
        final int x = composite.computeSize(-1, -1).x;
        final GridData layoutData = new GridData();
        layoutData.widthHint = Math.min(x, n);
        layoutData.horizontalAlignment = 4;
        layoutData.grabExcessHorizontalSpace = true;
        composite.setLayoutData(layoutData);
        final StyledText styledText = new StyledText(shell, 64);
        styledText.setMargins(30, 0, 30, 0);
        styledText.setEditable(false);
        styledText.setBackground(shell.getBackground());
        for (int i = 0; i < array.length; ++i) {
            styledText.append(String.valueOf(array[i]) + '\n');
        }
        final StyleRange styleRange = new StyleRange();
        styleRange.metrics = new GlyphMetrics(0, 0, 30);
        styledText.setLineBullet(0, array.length, new Bullet(styleRange));
        final int x2 = styledText.computeSize(-1, -1).x;
        final GridData layoutData2 = new GridData();
        layoutData2.widthHint = Math.min(x2, n);
        layoutData2.horizontalAlignment = 4;
        layoutData2.grabExcessHorizontalSpace = true;
        styledText.setLayoutData(layoutData2);
        final Text text3 = new Text(shell, 4);
        text3.setEditable(false);
        text3.setBackground(shell.getBackground());
        text3.setText(Compatibility.getMessage("SWT_InvalidCert_Connect"));
        new Label(shell, 0);
        final Browser browser2 = new Browser(shell, browser.getStyle());
        final GridData layoutData3 = new GridData();
        layoutData3.exclude = true;
        browser2.setLayoutData(layoutData3);
        final Composite composite2 = new Composite(shell, 0);
        composite2.setLayout(new GridLayout(3, true));
        composite2.setLayoutData(new GridData(16777216, 16777216, false, false));
        final Button button = new Button(composite2, 8);
        button.setLayoutData(new GridData(4, 4, false, false));
        button.setText(Compatibility.getMessage("SWT_ViewCertificate"));
        button.addListener(13, new Listener() {
            public void handleEvent(final Event event) {
                final int[] array = { 0 };
                final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array);
                if (ns_GetServiceManager != 0) {
                    Mozilla.error(ns_GetServiceManager);
                }
                if (array[0] == 0) {
                    Mozilla.error(-2147467262);
                }
                final nsIServiceManager nsIServiceManager = new nsIServiceManager(array[0]);
                array[0] = 0;
                final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/nsCertificateDialogs;1", true), nsICertificateDialogs.NS_ICERTIFICATEDIALOGS_IID, array);
                if (getServiceByContractID != 0) {
                    Mozilla.error(getServiceByContractID);
                }
                if (array[0] == 0) {
                    Mozilla.error(-2147467262);
                }
                nsIServiceManager.Release();
                final nsICertificateDialogs nsICertificateDialogs = new nsICertificateDialogs(array[0]);
                array[0] = 0;
                final Runnable runnable = new Runnable() {
                    public void run() {
                        browser.getDisplay().timerExec(1000, this);
                    }
                };
                runnable.run();
                final int getContentDOMWindow = ((Mozilla)browser2.webBrowser).webBrowser.GetContentDOMWindow(array);
                if (getContentDOMWindow != 0) {
                    Mozilla.error(getContentDOMWindow);
                }
                if (array[0] == 0) {
                    Mozilla.error(-2147467262);
                }
                final nsIDOMWindow nsIDOMWindow = new nsIDOMWindow(array[0]);
                array[0] = 0;
                nsICertificateDialogs.ViewCert(nsIDOMWindow.getAddress(), nsIX509Cert.getAddress());
                browser.getDisplay().timerExec(-1, runnable);
                nsIDOMWindow.Release();
                nsICertificateDialogs.Release();
            }
        });
        final Button button2 = new Button(composite2, 8);
        button2.setLayoutData(new GridData(4, 4, false, false));
        button2.setText(Compatibility.getMessage("SWT_OK"));
        final Button defaultButton = new Button(composite2, 8);
        defaultButton.setLayoutData(new GridData(4, 4, false, false));
        defaultButton.setText(Compatibility.getMessage("SWT_Cancel"));
        final boolean[] array2 = { false };
        final Listener listener = new Listener() {
            public void handleEvent(final Event event) {
                shell.dispose();
                array2[0] = (event.widget == button2);
            }
        };
        button2.addListener(13, listener);
        defaultButton.addListener(13, listener);
        defaultButton.setFocus();
        shell.setDefaultButton(defaultButton);
        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return array2[0];
    }
    
    void confirmEx(final String text, final String text2, final String text3, final String text4, final String text5, final String text6, final int n, final int[] array, final int[] array2) {
        final Shell parent = this.getParent();
        final Shell shell = new Shell(parent, 67680);
        shell.setText(text);
        shell.setLayout(new GridLayout());
        final Label label = new Label(shell, 64);
        label.setText(text2);
        final GridData layoutData = new GridData();
        layoutData.widthHint = Math.min(label.computeSize(-1, -1).x, parent.getMonitor().getBounds().width * 2 / 3);
        layoutData.horizontalAlignment = 4;
        layoutData.grabExcessHorizontalSpace = true;
        label.setLayoutData(layoutData);
        final Button[] array3 = new Button[4];
        final Listener listener = new Listener() {
            public void handleEvent(final Event event) {
                if (array3[0] != null) {
                    array[0] = (array3[0].getSelection() ? 1 : 0);
                }
                final Widget widget = event.widget;
                for (int i = 1; i < array3.length; ++i) {
                    if (widget == array3[i]) {
                        array2[0] = i - 1;
                        break;
                    }
                }
                shell.close();
            }
        };
        if (text3 != null) {
            (array3[0] = new Button(shell, 32)).setText(text3);
            array3[0].setSelection(array[0] != 0);
            final GridData layoutData2 = new GridData();
            layoutData2.horizontalAlignment = 1;
            array3[0].setLayoutData(layoutData2);
        }
        final Composite composite = new Composite(shell, 0);
        final GridData layoutData3 = new GridData();
        layoutData3.horizontalAlignment = 2;
        composite.setLayoutData(layoutData3);
        final GridLayout layout = new GridLayout();
        layout.makeColumnsEqualWidth = true;
        composite.setLayout(layout);
        int numColumns = 0;
        if (text4 != null) {
            (array3[1] = new Button(composite, 8)).setText(text4);
            array3[1].addListener(13, listener);
            array3[1].setLayoutData(new GridData(768));
            ++numColumns;
        }
        if (text5 != null) {
            (array3[2] = new Button(composite, 8)).setText(text5);
            array3[2].addListener(13, listener);
            array3[2].setLayoutData(new GridData(768));
            ++numColumns;
        }
        if (text6 != null) {
            (array3[3] = new Button(composite, 8)).setText(text6);
            array3[3].addListener(13, listener);
            array3[3].setLayoutData(new GridData(768));
            ++numColumns;
        }
        layout.numColumns = numColumns;
        final Button defaultButton = array3[n + 1];
        if (defaultButton != null) {
            shell.setDefaultButton(defaultButton);
        }
        shell.pack();
        shell.open();
        final Display display = parent.getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
    
    void prompt(final String text, final String text2, final String text3, final String[] array, final int[] array2, final int[] array3) {
        final Shell parent = this.getParent();
        final Shell shell = new Shell(parent, 67680);
        if (text != null) {
            shell.setText(text);
        }
        shell.setLayout(new GridLayout());
        final Label label = new Label(shell, 64);
        label.setText(text2);
        final GridData layoutData = new GridData();
        final int widthHint = parent.getMonitor().getBounds().width * 2 / 3;
        layoutData.widthHint = Math.min(label.computeSize(-1, -1).x, widthHint);
        layoutData.horizontalAlignment = 4;
        layoutData.grabExcessHorizontalSpace = true;
        label.setLayoutData(layoutData);
        final Text text4 = new Text(shell, 2048);
        if (array[0] != null) {
            text4.setText(array[0]);
        }
        final GridData layoutData2 = new GridData();
        if (text4.computeSize(-1, -1).x > widthHint) {
            layoutData2.widthHint = widthHint;
        }
        layoutData2.horizontalAlignment = 4;
        layoutData2.grabExcessHorizontalSpace = true;
        text4.setLayoutData(layoutData2);
        final Button[] array4 = new Button[3];
        final Listener listener = new Listener() {
            public void handleEvent(final Event event) {
                if (array4[0] != null) {
                    array2[0] = (array4[0].getSelection() ? 1 : 0);
                }
                array[0] = text4.getText();
                array3[0] = ((event.widget == array4[1]) ? 1 : 0);
                shell.close();
            }
        };
        if (text3 != null) {
            (array4[0] = new Button(shell, 32)).setText(text3);
            array4[0].setSelection(array2[0] != 0);
            final GridData layoutData3 = new GridData();
            layoutData3.horizontalAlignment = 1;
            array4[0].setLayoutData(layoutData3);
        }
        final Composite composite = new Composite(shell, 0);
        final GridData layoutData4 = new GridData();
        layoutData4.horizontalAlignment = 2;
        composite.setLayoutData(layoutData4);
        composite.setLayout(new GridLayout(2, true));
        (array4[1] = new Button(composite, 8)).setText(SWT.getMessage("SWT_OK"));
        array4[1].setLayoutData(new GridData(768));
        array4[1].addListener(13, listener);
        (array4[2] = new Button(composite, 8)).setText(SWT.getMessage("SWT_Cancel"));
        array4[2].setLayoutData(new GridData(768));
        array4[2].addListener(13, listener);
        shell.pack();
        shell.open();
        final Display display = parent.getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
    
    void promptUsernameAndPassword(final String text, final String text2, final String text3, final String[] array, final String[] array2, final int[] array3, final int[] array4) {
        final Shell parent = this.getParent();
        final Shell shell = new Shell(parent, 67680);
        shell.setText(text);
        shell.setLayout(new GridLayout());
        final Label label = new Label(shell, 64);
        label.setText(text2);
        final GridData layoutData = new GridData();
        layoutData.widthHint = Math.min(label.computeSize(-1, -1).x, parent.getMonitor().getBounds().width * 2 / 3);
        layoutData.horizontalAlignment = 4;
        layoutData.grabExcessHorizontalSpace = true;
        label.setLayoutData(layoutData);
        new Label(shell, 0).setText(SWT.getMessage("SWT_Username"));
        final Text text4 = new Text(shell, 2048);
        if (array[0] != null) {
            text4.setText(array[0]);
        }
        final GridData layoutData2 = new GridData();
        layoutData2.horizontalAlignment = 4;
        layoutData2.grabExcessHorizontalSpace = true;
        text4.setLayoutData(layoutData2);
        new Label(shell, 0).setText(SWT.getMessage("SWT_Password"));
        final Text text5 = new Text(shell, 4196352);
        if (array2[0] != null) {
            text5.setText(array2[0]);
        }
        final GridData layoutData3 = new GridData();
        layoutData3.horizontalAlignment = 4;
        layoutData3.grabExcessHorizontalSpace = true;
        text5.setLayoutData(layoutData3);
        final Button[] array5 = new Button[3];
        final Listener listener = new Listener() {
            public void handleEvent(final Event event) {
                if (array5[0] != null) {
                    array3[0] = (array5[0].getSelection() ? 1 : 0);
                }
                array[0] = text4.getText();
                array2[0] = text5.getText();
                array4[0] = ((event.widget == array5[1]) ? 1 : 0);
                shell.close();
            }
        };
        if (text3 != null) {
            (array5[0] = new Button(shell, 32)).setText(text3);
            array5[0].setSelection(array3[0] != 0);
            final GridData layoutData4 = new GridData();
            layoutData4.horizontalAlignment = 1;
            array5[0].setLayoutData(layoutData4);
        }
        final Composite composite = new Composite(shell, 0);
        final GridData layoutData5 = new GridData();
        layoutData5.horizontalAlignment = 2;
        composite.setLayoutData(layoutData5);
        composite.setLayout(new GridLayout(2, true));
        (array5[1] = new Button(composite, 8)).setText(SWT.getMessage("SWT_OK"));
        array5[1].setLayoutData(new GridData(768));
        array5[1].addListener(13, listener);
        (array5[2] = new Button(composite, 8)).setText(SWT.getMessage("SWT_Cancel"));
        array5[2].setLayoutData(new GridData(768));
        array5[2].addListener(13, listener);
        shell.setDefaultButton(array5[1]);
        shell.pack();
        shell.open();
        final Display display = parent.getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
}
