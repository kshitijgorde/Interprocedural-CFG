// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.TF_DISPLAYATTRIBUTE;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.graphics.TextStyle;

public class IME extends Widget
{
    Canvas parent;
    int caretOffset;
    int startOffset;
    int commitCount;
    String text;
    int[] ranges;
    TextStyle[] styles;
    static final int WM_MSIME_MOUSE;
    static final byte[] IID_ITfInputProcessorProfiles;
    static final byte[] IID_ITfDisplayAttributeProvider;
    static final byte[] CLSID_TF_InputProcessorProfiles;
    static final byte[] GUID_TFCAT_TIP_KEYBOARD;
    static final int UNDERLINE_IME_DOT = 65536;
    static final int UNDERLINE_IME_DASH = 131072;
    static final int UNDERLINE_IME_THICK = 196608;
    
    static {
        WM_MSIME_MOUSE = OS.RegisterWindowMessage(new TCHAR(0, "MSIMEMouseOperation", true));
        IID_ITfInputProcessorProfiles = new byte[16];
        IID_ITfDisplayAttributeProvider = new byte[16];
        CLSID_TF_InputProcessorProfiles = new byte[16];
        GUID_TFCAT_TIP_KEYBOARD = new byte[16];
        OS.IIDFromString("{1F02B6C5-7842-4EE6-8A0B-9A24183A95CA}\u0000".toCharArray(), IME.IID_ITfInputProcessorProfiles);
        OS.IIDFromString("{fee47777-163c-4769-996a-6e9c50ad8f54}\u0000".toCharArray(), IME.IID_ITfDisplayAttributeProvider);
        OS.IIDFromString("{33C53A50-F456-4884-B049-85FD643ECFED}\u0000".toCharArray(), IME.CLSID_TF_InputProcessorProfiles);
        OS.IIDFromString("{34745C63-B2F0-4784-8B67-5E12C8701A31}\u0000".toCharArray(), IME.GUID_TFCAT_TIP_KEYBOARD);
    }
    
    IME() {
    }
    
    public IME(final Canvas parent, final int n) {
        super(parent, n);
        this.parent = parent;
        this.createWidget();
    }
    
    void createWidget() {
        this.text = "";
        this.startOffset = -1;
        if (this.parent.getIME() == null) {
            this.parent.setIME(this);
        }
    }
    
    public int getCaretOffset() {
        this.checkWidget();
        return this.startOffset + this.caretOffset;
    }
    
    public int getCommitCount() {
        this.checkWidget();
        return this.commitCount;
    }
    
    public int getCompositionOffset() {
        this.checkWidget();
        return this.startOffset;
    }
    
    TF_DISPLAYATTRIBUTE getDisplayAttribute(final short n, final int n2) {
        final int[] array = { 0 };
        final int coCreateInstance = OS.CoCreateInstance(IME.CLSID_TF_InputProcessorProfiles, 0, 1, IME.IID_ITfInputProcessorProfiles, array);
        TF_DISPLAYATTRIBUTE tf_DISPLAYATTRIBUTE = null;
        if (coCreateInstance == 0) {
            final byte[] array2 = new byte[16];
            if (OS.VtblCall(8, array[0], n, IME.GUID_TFCAT_TIP_KEYBOARD, array2, new byte[16]) == 0) {
                final int[] array3 = { 0 };
                if (OS.CoCreateInstance(array2, 0, 1, IME.IID_ITfDisplayAttributeProvider, array3) == 0) {
                    final int[] array4 = { 0 };
                    if (OS.VtblCall(3, array3[0], array4) == 0) {
                        final int[] array5 = { 0 };
                        final TF_DISPLAYATTRIBUTE tf_DISPLAYATTRIBUTE2 = new TF_DISPLAYATTRIBUTE();
                        while (OS.VtblCall(4, array4[0], 1L, array5, null) == 0) {
                            OS.VtblCall(5, array5[0], tf_DISPLAYATTRIBUTE2);
                            OS.VtblCall(2, array5[0]);
                            if (tf_DISPLAYATTRIBUTE2.bAttr == n2) {
                                tf_DISPLAYATTRIBUTE = tf_DISPLAYATTRIBUTE2;
                                break;
                            }
                        }
                        OS.VtblCall(2, array4[0]);
                    }
                    OS.VtblCall(2, array3[0]);
                }
            }
            OS.VtblCall(2, array[0]);
        }
        if (tf_DISPLAYATTRIBUTE == null) {
            tf_DISPLAYATTRIBUTE = new TF_DISPLAYATTRIBUTE();
            switch (n2) {
                case 0: {
                    tf_DISPLAYATTRIBUTE.lsStyle = 4;
                    break;
                }
                case 1:
                case 2: {
                    tf_DISPLAYATTRIBUTE.lsStyle = 1;
                    tf_DISPLAYATTRIBUTE.fBoldLine = (n2 == 1);
                    break;
                }
            }
        }
        return tf_DISPLAYATTRIBUTE;
    }
    
    public int[] getRanges() {
        this.checkWidget();
        if (this.ranges == null) {
            return new int[0];
        }
        final int[] array = new int[this.ranges.length];
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.ranges[i] + this.startOffset;
        }
        return array;
    }
    
    public TextStyle[] getStyles() {
        this.checkWidget();
        if (this.styles == null) {
            return new TextStyle[0];
        }
        final TextStyle[] array = new TextStyle[this.styles.length];
        System.arraycopy(this.styles, 0, array, 0, this.styles.length);
        return array;
    }
    
    public String getText() {
        this.checkWidget();
        return this.text;
    }
    
    public boolean getWideCaret() {
        this.checkWidget();
        return OS.PRIMARYLANGID((short)OS.LOWORD(OS.GetKeyboardLayout(0))) == 18;
    }
    
    boolean isInlineEnabled() {
        return !OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(5, 1) && (OS.IsDBLocale && this.hooks(43));
    }
    
    void releaseParent() {
        super.releaseParent();
        if (this == this.parent.getIME()) {
            this.parent.setIME(null);
        }
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.parent = null;
        this.text = null;
        this.styles = null;
        this.ranges = null;
    }
    
    public void setCompositionOffset(final int startOffset) {
        this.checkWidget();
        if (startOffset < 0) {
            return;
        }
        if (this.startOffset != -1) {
            this.startOffset = startOffset;
        }
    }
    
    LRESULT WM_IME_COMPOSITION(final int n, final int n2) {
        if (!this.isInlineEnabled()) {
            return null;
        }
        this.ranges = null;
        this.styles = null;
        final boolean b = false;
        this.commitCount = (b ? 1 : 0);
        this.caretOffset = (b ? 1 : 0);
        final int handle = this.parent.handle;
        final int immGetContext = OS.ImmGetContext(handle);
        final int codePage = this.parent.getCodePage();
        if (immGetContext != 0) {
            if ((n2 & 0x800) != 0x0) {
                final int immGetCompositionString = OS.ImmGetCompositionString(immGetContext, 2048, (TCHAR)null, 0);
                if (immGetCompositionString > 0) {
                    final TCHAR tchar = new TCHAR(codePage, immGetCompositionString / TCHAR.sizeof);
                    OS.ImmGetCompositionString(immGetContext, 2048, tchar, immGetCompositionString);
                    if (this.startOffset == -1) {
                        final Event event = new Event();
                        event.detail = 3;
                        this.sendEvent(43, event);
                        this.startOffset = event.start;
                    }
                    final Event event2 = new Event();
                    event2.detail = 1;
                    event2.start = this.startOffset;
                    event2.end = this.startOffset + this.text.length();
                    final Event event3 = event2;
                    final String s = (tchar != null) ? tchar.toString() : "";
                    this.text = s;
                    event3.text = s;
                    this.commitCount = this.text.length();
                    this.sendEvent(43, event2);
                    final String text = this.text;
                    this.text = "";
                    this.startOffset = -1;
                    this.commitCount = 0;
                    if (event2.doit) {
                        final Display display = this.display;
                        display.lastKey = 0;
                        final Display display2 = display;
                        final Display display3 = display;
                        final Display display4 = display;
                        final boolean lastVirtual = false;
                        display4.lastDead = lastVirtual;
                        display3.lastNull = lastVirtual;
                        display2.lastVirtual = lastVirtual;
                        for (int length = text.length(), i = 0; i < length; ++i) {
                            final char char1 = text.charAt(i);
                            display.lastAscii = char1;
                            final Event event4 = new Event();
                            event4.character = char1;
                            this.parent.sendEvent(1, event4);
                        }
                    }
                }
                if ((n2 & 0x8) == 0x0) {
                    return LRESULT.ONE;
                }
            }
            TCHAR tchar2 = null;
            if ((n2 & 0x8) != 0x0) {
                final int immGetCompositionString2 = OS.ImmGetCompositionString(immGetContext, 8, (TCHAR)null, 0);
                if (immGetCompositionString2 > 0) {
                    tchar2 = new TCHAR(codePage, immGetCompositionString2 / TCHAR.sizeof);
                    OS.ImmGetCompositionString(immGetContext, 8, tchar2, immGetCompositionString2);
                    if ((n2 & 0x80) != 0x0) {
                        this.caretOffset = OS.ImmGetCompositionString(immGetContext, 128, (TCHAR)null, 0);
                    }
                    int[] array = null;
                    if ((n2 & 0x20) != 0x0) {
                        final int immGetCompositionString3 = OS.ImmGetCompositionString(immGetContext, 32, (int[])null, 0);
                        if (immGetCompositionString3 > 0) {
                            array = new int[immGetCompositionString3 / 4];
                            OS.ImmGetCompositionString(immGetContext, 32, array, immGetCompositionString3);
                        }
                    }
                    if ((n2 & 0x10) != 0x0 && array != null) {
                        final int immGetCompositionString4 = OS.ImmGetCompositionString(immGetContext, 16, (byte[])null, 0);
                        if (immGetCompositionString4 > 0) {
                            final byte[] array2 = new byte[immGetCompositionString4];
                            OS.ImmGetCompositionString(immGetContext, 16, array2, immGetCompositionString4);
                            final int n3 = array.length - 1;
                            this.ranges = new int[n3 * 2];
                            this.styles = new TextStyle[n3];
                            final short n4 = (short)OS.LOWORD(OS.GetKeyboardLayout(0));
                            for (int j = 0; j < n3; ++j) {
                                this.ranges[j * 2] = array[j];
                                this.ranges[j * 2 + 1] = array[j + 1] - 1;
                                final TextStyle textStyle = this.styles[j] = new TextStyle();
                                final TF_DISPLAYATTRIBUTE displayAttribute = this.getDisplayAttribute(n4, array2[array[j]]);
                                if (displayAttribute != null) {
                                    switch (displayAttribute.crText.type) {
                                        case 2: {
                                            textStyle.foreground = Color.win32_new(this.display, displayAttribute.crText.cr);
                                            break;
                                        }
                                        case 1: {
                                            textStyle.foreground = Color.win32_new(this.display, OS.GetSysColor(displayAttribute.crText.cr));
                                            break;
                                        }
                                    }
                                    switch (displayAttribute.crBk.type) {
                                        case 2: {
                                            textStyle.background = Color.win32_new(this.display, displayAttribute.crBk.cr);
                                            break;
                                        }
                                        case 1: {
                                            textStyle.background = Color.win32_new(this.display, OS.GetSysColor(displayAttribute.crBk.cr));
                                            break;
                                        }
                                    }
                                    switch (displayAttribute.crLine.type) {
                                        case 2: {
                                            textStyle.underlineColor = Color.win32_new(this.display, displayAttribute.crLine.cr);
                                            break;
                                        }
                                        case 1: {
                                            textStyle.underlineColor = Color.win32_new(this.display, OS.GetSysColor(displayAttribute.crLine.cr));
                                            break;
                                        }
                                    }
                                    textStyle.underline = (displayAttribute.lsStyle != 0);
                                    switch (displayAttribute.lsStyle) {
                                        case 4: {
                                            textStyle.underlineStyle = 3;
                                            break;
                                        }
                                        case 3: {
                                            textStyle.underlineStyle = 131072;
                                            break;
                                        }
                                        case 2: {
                                            textStyle.underlineStyle = 65536;
                                            break;
                                        }
                                        case 1: {
                                            textStyle.underlineStyle = (displayAttribute.fBoldLine ? 196608 : 0);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                OS.ImmReleaseContext(handle, immGetContext);
            }
            int end = this.startOffset + this.text.length();
            if (this.startOffset == -1) {
                final Event event5 = new Event();
                event5.detail = 3;
                this.sendEvent(43, event5);
                this.startOffset = event5.start;
                end = event5.end;
            }
            final Event event6 = new Event();
            event6.detail = 1;
            event6.start = this.startOffset;
            event6.end = end;
            final Event event7 = event6;
            final String s2 = (tchar2 != null) ? tchar2.toString() : "";
            this.text = s2;
            event7.text = s2;
            this.sendEvent(43, event6);
            if (this.text.length() == 0) {
                this.startOffset = -1;
                this.ranges = null;
                this.styles = null;
            }
        }
        return LRESULT.ONE;
    }
    
    LRESULT WM_IME_COMPOSITION_START(final int n, final int n2) {
        return this.isInlineEnabled() ? LRESULT.ONE : null;
    }
    
    LRESULT WM_IME_ENDCOMPOSITION(final int n, final int n2) {
        return this.isInlineEnabled() ? LRESULT.ONE : null;
    }
    
    LRESULT WM_KILLFOCUS(final int n, final int n2) {
        if (!this.isInlineEnabled()) {
            return null;
        }
        final int handle = this.parent.handle;
        final int immGetContext = OS.ImmGetContext(handle);
        if (immGetContext != 0) {
            if (OS.ImmGetOpenStatus(immGetContext)) {
                OS.ImmNotifyIME(immGetContext, 21, 1, 0);
            }
            OS.ImmReleaseContext(handle, immGetContext);
        }
        return null;
    }
    
    LRESULT WM_LBUTTONDOWN(final int n, final int n2) {
        if (!this.isInlineEnabled()) {
            return null;
        }
        final int handle = this.parent.handle;
        final int immGetContext = OS.ImmGetContext(handle);
        if (immGetContext != 0) {
            if (OS.ImmGetOpenStatus(immGetContext) && OS.ImmGetCompositionString(immGetContext, 8, (TCHAR)null, 0) > 0) {
                final Event event = new Event();
                event.detail = 2;
                event.x = OS.GET_X_LPARAM(n2);
                event.y = OS.GET_Y_LPARAM(n2);
                this.sendEvent(43, event);
                final int index = event.index;
                final int length = this.text.length();
                if (index != -1 && this.startOffset != -1 && this.startOffset <= index && index < this.startOffset + length) {
                    OS.SendMessage(OS.ImmGetDefaultIMEWnd(handle), IME.WM_MSIME_MOUSE, OS.MAKEWPARAM(OS.MAKEWORD(1, (event.count > 0) ? 1 : 2), event.index + event.count - this.startOffset), immGetContext);
                }
                else {
                    OS.ImmNotifyIME(immGetContext, 21, 1, 0);
                }
            }
            OS.ImmReleaseContext(handle, immGetContext);
        }
        return null;
    }
}
