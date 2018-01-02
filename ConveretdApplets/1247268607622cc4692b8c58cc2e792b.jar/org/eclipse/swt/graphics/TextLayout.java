// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.win32.OUTLINETEXTMETRIC;
import org.eclipse.swt.internal.win32.OUTLINETEXTMETRICA;
import org.eclipse.swt.internal.win32.OUTLINETEXTMETRICW;
import org.eclipse.swt.internal.win32.LOGFONT;
import org.eclipse.swt.internal.win32.LOGFONTA;
import org.eclipse.swt.internal.win32.LOGFONTW;
import org.eclipse.swt.internal.Callback;
import org.eclipse.swt.internal.win32.EMREXTCREATEFONTINDIRECTW;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.SCRIPT_FONTPROPERTIES;
import org.eclipse.swt.internal.win32.SCRIPT_ITEM;
import org.eclipse.swt.internal.win32.SCRIPT_DIGITSUBSTITUTE;
import org.eclipse.swt.internal.win32.SCRIPT_CONTROL;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.gdip.Rect;
import org.eclipse.swt.internal.win32.LOGBRUSH;
import org.eclipse.swt.internal.gdip.Gdip;
import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.internal.win32.SCRIPT_STATE;
import org.eclipse.swt.internal.win32.SCRIPT_ANALYSIS;
import org.eclipse.swt.internal.win32.TEXTMETRIC;
import org.eclipse.swt.internal.win32.TEXTMETRICA;
import org.eclipse.swt.internal.win32.TEXTMETRICW;
import org.eclipse.swt.internal.win32.SCRIPT_PROPERTIES;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.SCRIPT_LOGATTR;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;

public final class TextLayout extends Resource
{
    Font font;
    String text;
    String segmentsText;
    int lineSpacing;
    int ascent;
    int descent;
    int alignment;
    int wrapWidth;
    int orientation;
    int indent;
    int wrapIndent;
    boolean justify;
    int[] tabs;
    int[] segments;
    char[] segmentsChars;
    StyleItem[] styles;
    int stylesCount;
    StyleItem[] allRuns;
    StyleItem[][] runs;
    int[] lineOffset;
    int[] lineY;
    int[] lineWidth;
    int mLangFontLink2;
    static final char LTR_MARK = '\u200e';
    static final char RTL_MARK = '\u200f';
    static final int SCRIPT_VISATTR_SIZEOF = 2;
    static final int GOFFSET_SIZEOF = 8;
    static final byte[] CLSID_CMultiLanguage;
    static final byte[] IID_IMLangFontLink2;
    static final int MERGE_MAX = 512;
    static final int TOO_MANY_RUNS = 1024;
    static final int UNDERLINE_IME_DOT = 65536;
    static final int UNDERLINE_IME_DASH = 131072;
    static final int UNDERLINE_IME_THICK = 196608;
    
    static {
        CLSID_CMultiLanguage = new byte[16];
        IID_IMLangFontLink2 = new byte[16];
        OS.IIDFromString("{275c23e2-3747-11d0-9fea-00aa003f8646}\u0000".toCharArray(), TextLayout.CLSID_CMultiLanguage);
        OS.IIDFromString("{DCCFC162-2B38-11d2-B7EC-00C04F8F5D9A}\u0000".toCharArray(), TextLayout.IID_IMLangFontLink2);
    }
    
    public TextLayout(final Device device) {
        super(device);
        final int wrapWidth = -1;
        this.descent = wrapWidth;
        this.ascent = wrapWidth;
        this.wrapWidth = wrapWidth;
        this.lineSpacing = 0;
        this.orientation = 33554432;
        (this.styles = new StyleItem[2])[0] = new StyleItem();
        this.styles[1] = new StyleItem();
        this.stylesCount = 2;
        this.text = "";
        final int[] array = { 0 };
        OS.OleInitialize(0);
        if (OS.CoCreateInstance(TextLayout.CLSID_CMultiLanguage, 0, 1, TextLayout.IID_IMLangFontLink2, array) == 0) {
            this.mLangFontLink2 = array[0];
        }
        this.init();
    }
    
    RECT addClipRect(final StyleItem styleItem, RECT rect, final RECT rect2, final int n, final int n2) {
        if (rect2 != null) {
            if (rect == null) {
                rect = new RECT();
                OS.SetRect(rect, -1, rect2.top, -1, rect2.bottom);
            }
            final boolean b = (this.orientation & 0x4000000) != 0x0;
            if (styleItem.start <= n && n <= styleItem.start + styleItem.length) {
                if (styleItem.analysis.fRTL ^ b) {
                    rect.right = rect2.left;
                }
                else {
                    rect.left = rect2.left;
                }
            }
            if (styleItem.start <= n2 && n2 <= styleItem.start + styleItem.length) {
                if (styleItem.analysis.fRTL ^ b) {
                    rect.left = rect2.right;
                }
                else {
                    rect.right = rect2.right;
                }
            }
        }
        return rect;
    }
    
    void breakRun(final StyleItem styleItem) {
        if (styleItem.psla != 0) {
            return;
        }
        final char[] array = new char[styleItem.length];
        this.segmentsText.getChars(styleItem.start, styleItem.start + styleItem.length, array, 0);
        styleItem.psla = OS.HeapAlloc(OS.GetProcessHeap(), 8, SCRIPT_LOGATTR.sizeof * array.length);
        if (styleItem.psla == 0) {
            SWT.error(2);
        }
        OS.ScriptBreak(array, array.length, styleItem.analysis, styleItem.psla);
    }
    
    void checkLayout() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
    }
    
    void computeRuns(final GC gc) {
        if (this.runs != null) {
            return;
        }
        final int n = (gc != null) ? gc.handle : this.device.internal_new_GC(null);
        final int createCompatibleDC = OS.CreateCompatibleDC(n);
        this.allRuns = this.itemize();
        for (int i = 0; i < this.allRuns.length - 1; ++i) {
            final StyleItem styleItem = this.allRuns[i];
            OS.SelectObject(createCompatibleDC, this.getItemFont(styleItem));
            this.shape(createCompatibleDC, styleItem);
        }
        final SCRIPT_LOGATTR script_LOGATTR = new SCRIPT_LOGATTR();
        final SCRIPT_PROPERTIES script_PROPERTIES = new SCRIPT_PROPERTIES();
        int indent = this.indent;
        int n2 = 0;
        int n3 = 1;
        for (int j = 0; j < this.allRuns.length - 1; ++j) {
            StyleItem styleItem2 = this.allRuns[j];
            if (this.tabs != null && styleItem2.tab) {
                int length;
                int k;
                for (length = this.tabs.length, k = 0; k < length; ++k) {
                    if (this.tabs[k] > indent) {
                        styleItem2.width = this.tabs[k] - indent;
                        break;
                    }
                }
                if (k == length) {
                    int l = this.tabs[length - 1];
                    final int n4 = (length > 1) ? (this.tabs[length - 1] - this.tabs[length - 2]) : this.tabs[0];
                    if (n4 > 0) {
                        while (l <= indent) {
                            l += n4;
                        }
                        styleItem2.width = l - indent;
                    }
                }
                int length2 = styleItem2.length;
                if (length2 > 1) {
                    final int n5 = k + length2 - 1;
                    if (n5 < length) {
                        final StyleItem styleItem3 = styleItem2;
                        styleItem3.width += this.tabs[n5] - this.tabs[k];
                    }
                    else {
                        if (k < length) {
                            final StyleItem styleItem4 = styleItem2;
                            styleItem4.width += this.tabs[length - 1] - this.tabs[k];
                            length2 -= length - 1 - k;
                        }
                        final int n6 = (length > 1) ? (this.tabs[length - 1] - this.tabs[length - 2]) : this.tabs[0];
                        final StyleItem styleItem5 = styleItem2;
                        styleItem5.width += n6 * (length2 - 1);
                    }
                }
            }
            if (this.wrapWidth != -1 && indent + styleItem2.width > this.wrapWidth && !styleItem2.tab && !styleItem2.lineBreak) {
                int length3 = 0;
                final int[] array = new int[styleItem2.length];
                if (styleItem2.style != null && styleItem2.style.metrics != null) {
                    array[0] = styleItem2.width;
                }
                else {
                    OS.ScriptGetLogicalWidths(styleItem2.analysis, styleItem2.length, styleItem2.glyphCount, styleItem2.advances, styleItem2.clusters, styleItem2.visAttrs, array);
                }
                for (int n7 = 0; n7 + array[length3] < this.wrapWidth - indent; n7 += array[length3++]) {}
                final int n8 = length3;
                final int n9 = j;
                while (j >= n2) {
                    this.breakRun(styleItem2);
                    while (length3 >= 0) {
                        OS.MoveMemory(script_LOGATTR, styleItem2.psla + length3 * SCRIPT_LOGATTR.sizeof, SCRIPT_LOGATTR.sizeof);
                        if (script_LOGATTR.fSoftBreak) {
                            break;
                        }
                        if (script_LOGATTR.fWhiteSpace) {
                            break;
                        }
                        --length3;
                    }
                    if (length3 == 0 && j != n2 && !styleItem2.tab && script_LOGATTR.fSoftBreak && !script_LOGATTR.fWhiteSpace) {
                        OS.MoveMemory(script_PROPERTIES, this.device.scripts[styleItem2.analysis.eScript], SCRIPT_PROPERTIES.sizeof);
                        final short langid = script_PROPERTIES.langid;
                        final StyleItem styleItem6 = this.allRuns[j - 1];
                        OS.MoveMemory(script_PROPERTIES, this.device.scripts[styleItem6.analysis.eScript], SCRIPT_PROPERTIES.sizeof);
                        if (script_PROPERTIES.langid == langid || langid == 0 || script_PROPERTIES.langid == 0) {
                            this.breakRun(styleItem6);
                            OS.MoveMemory(script_LOGATTR, styleItem6.psla + (styleItem6.length - 1) * SCRIPT_LOGATTR.sizeof, SCRIPT_LOGATTR.sizeof);
                            if (!script_LOGATTR.fWhiteSpace) {
                                length3 = -1;
                            }
                        }
                    }
                    if (length3 >= 0) {
                        break;
                    }
                    if (j == n2) {
                        break;
                    }
                    styleItem2 = this.allRuns[--j];
                    length3 = styleItem2.length - 1;
                }
                if (length3 == 0 && j != n2 && !styleItem2.tab) {
                    styleItem2 = this.allRuns[--j];
                }
                else if (length3 <= 0 && j == n2) {
                    if (indent == this.wrapWidth && n9 > 0) {
                        j = n9 - 1;
                        styleItem2 = this.allRuns[j];
                        length3 = styleItem2.length;
                    }
                    else {
                        j = n9;
                        styleItem2 = this.allRuns[j];
                        length3 = Math.max(1, n8);
                    }
                }
                this.breakRun(styleItem2);
                while (length3 < styleItem2.length) {
                    OS.MoveMemory(script_LOGATTR, styleItem2.psla + length3 * SCRIPT_LOGATTR.sizeof, SCRIPT_LOGATTR.sizeof);
                    if (!script_LOGATTR.fWhiteSpace) {
                        break;
                    }
                    ++length3;
                }
                if (length3 > 0 && length3 < styleItem2.length) {
                    final StyleItem styleItem7 = new StyleItem();
                    styleItem7.start = styleItem2.start + length3;
                    styleItem7.length = styleItem2.length - length3;
                    styleItem7.style = styleItem2.style;
                    styleItem7.analysis = this.cloneScriptAnalysis(styleItem2.analysis);
                    styleItem2.free();
                    styleItem2.length = length3;
                    OS.SelectObject(createCompatibleDC, this.getItemFont(styleItem2));
                    styleItem2.analysis.fNoGlyphIndex = false;
                    this.shape(createCompatibleDC, styleItem2);
                    OS.SelectObject(createCompatibleDC, this.getItemFont(styleItem7));
                    styleItem7.analysis.fNoGlyphIndex = false;
                    this.shape(createCompatibleDC, styleItem7);
                    final StyleItem[] allRuns = new StyleItem[this.allRuns.length + 1];
                    System.arraycopy(this.allRuns, 0, allRuns, 0, j + 1);
                    System.arraycopy(this.allRuns, j + 1, allRuns, j + 2, this.allRuns.length - j - 1);
                    (this.allRuns = allRuns)[j + 1] = styleItem7;
                }
                if (j != this.allRuns.length - 2) {
                    final StyleItem styleItem8 = styleItem2;
                    final StyleItem styleItem9 = styleItem2;
                    final boolean b = true;
                    styleItem9.lineBreak = b;
                    styleItem8.softBreak = b;
                }
            }
            indent += styleItem2.width;
            if (styleItem2.lineBreak) {
                n2 = j + 1;
                indent = (styleItem2.softBreak ? this.wrapIndent : this.indent);
                ++n3;
            }
        }
        int n10 = 0;
        this.runs = new StyleItem[n3][];
        this.lineOffset = new int[n3 + 1];
        this.lineY = new int[n3 + 1];
        this.lineWidth = new int[n3];
        int n11 = 0;
        int n12 = 0;
        int n13 = Math.max(0, this.ascent);
        int n14 = Math.max(0, this.descent);
        final StyleItem[] array2 = new StyleItem[this.allRuns.length];
        for (int n15 = 0; n15 < this.allRuns.length; ++n15) {
            final StyleItem styleItem10 = this.allRuns[n15];
            array2[n11++] = styleItem10;
            n10 += styleItem10.width;
            n13 = Math.max(n13, styleItem10.ascent);
            n14 = Math.max(n14, styleItem10.descent);
            if (styleItem10.lineBreak || n15 == this.allRuns.length - 1) {
                if (n11 == 1 && (n15 == this.allRuns.length - 1 || !styleItem10.softBreak)) {
                    final TEXTMETRIC textmetric = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
                    OS.SelectObject(createCompatibleDC, this.getItemFont(styleItem10));
                    OS.GetTextMetrics(createCompatibleDC, textmetric);
                    styleItem10.ascent = textmetric.tmAscent;
                    styleItem10.descent = textmetric.tmDescent;
                    n13 = Math.max(n13, styleItem10.ascent);
                    n14 = Math.max(n14, styleItem10.descent);
                }
                System.arraycopy(array2, 0, this.runs[n12] = new StyleItem[n11], 0, n11);
                if (this.justify && this.wrapWidth != -1 && styleItem10.softBreak && n10 > 0) {
                    int n16 = this.wrapIndent;
                    if (n12 == 0) {
                        n16 = this.indent;
                    }
                    else {
                        final StyleItem[] array3 = this.runs[n12 - 1];
                        final StyleItem styleItem11 = array3[array3.length - 1];
                        if (styleItem11.lineBreak && !styleItem11.softBreak) {
                            n16 = this.indent;
                        }
                    }
                    final int n17 = n10 + n16;
                    final int getProcessHeap = OS.GetProcessHeap();
                    int n18 = 0;
                    for (int n19 = 0; n19 < this.runs[n12].length; ++n19) {
                        final StyleItem styleItem12 = this.runs[n12][n19];
                        final int width = styleItem12.width * this.wrapWidth / n17;
                        if (width != styleItem12.width) {
                            styleItem12.justify = OS.HeapAlloc(getProcessHeap, 8, styleItem12.glyphCount * 4);
                            if (styleItem12.justify == 0) {
                                SWT.error(2);
                            }
                            OS.ScriptJustify(styleItem12.visAttrs, styleItem12.advances, styleItem12.glyphCount, width - styleItem12.width, 2, styleItem12.justify);
                            styleItem12.width = width;
                        }
                        n18 += styleItem12.width;
                    }
                    n10 = n18;
                }
                this.lineWidth[n12] = n10;
                final StyleItem styleItem13 = this.runs[n12][n11 - 1];
                final int n20 = styleItem13.start + styleItem13.length;
                this.runs[n12] = this.reorder(this.runs[n12], n15 == this.allRuns.length - 1);
                final StyleItem styleItem14 = this.runs[n12][n11 - 1];
                if (styleItem10.softBreak && styleItem10 != styleItem14) {
                    final StyleItem styleItem15 = styleItem10;
                    final StyleItem styleItem16 = styleItem10;
                    final boolean b2 = false;
                    styleItem16.lineBreak = b2;
                    styleItem15.softBreak = b2;
                    final StyleItem styleItem17 = styleItem14;
                    final StyleItem styleItem18 = styleItem14;
                    final boolean b3 = true;
                    styleItem18.lineBreak = b3;
                    styleItem17.softBreak = b3;
                }
                int lineIndent = this.getLineIndent(n12);
                for (int n21 = 0; n21 < this.runs[n12].length; ++n21) {
                    this.runs[n12][n21].x = lineIndent;
                    lineIndent += this.runs[n12][n21].width;
                }
                ++n12;
                this.lineY[n12] = this.lineY[n12 - 1] + n13 + n14 + this.lineSpacing;
                this.lineOffset[n12] = n20;
                n10 = (n11 = 0);
                n13 = Math.max(0, this.ascent);
                n14 = Math.max(0, this.descent);
            }
        }
        if (createCompatibleDC != 0) {
            OS.DeleteDC(createCompatibleDC);
        }
        if (gc == null) {
            this.device.internal_dispose_GC(n, null);
        }
    }
    
    void destroy() {
        this.freeRuns();
        this.font = null;
        this.text = null;
        this.segmentsText = null;
        this.tabs = null;
        this.styles = null;
        this.runs = null;
        this.lineOffset = null;
        this.lineY = null;
        this.lineWidth = null;
        this.segments = null;
        this.segmentsChars = null;
        if (this.mLangFontLink2 != 0) {
            OS.VtblCall(2, this.mLangFontLink2);
            this.mLangFontLink2 = 0;
        }
        OS.OleUninitialize();
    }
    
    SCRIPT_ANALYSIS cloneScriptAnalysis(final SCRIPT_ANALYSIS script_ANALYSIS) {
        final SCRIPT_ANALYSIS script_ANALYSIS2 = new SCRIPT_ANALYSIS();
        script_ANALYSIS2.eScript = script_ANALYSIS.eScript;
        script_ANALYSIS2.fRTL = script_ANALYSIS.fRTL;
        script_ANALYSIS2.fLayoutRTL = script_ANALYSIS.fLayoutRTL;
        script_ANALYSIS2.fLinkBefore = script_ANALYSIS.fLinkBefore;
        script_ANALYSIS2.fLinkAfter = script_ANALYSIS.fLinkAfter;
        script_ANALYSIS2.fLogicalOrder = script_ANALYSIS.fLogicalOrder;
        script_ANALYSIS2.fNoGlyphIndex = script_ANALYSIS.fNoGlyphIndex;
        script_ANALYSIS2.s = new SCRIPT_STATE();
        script_ANALYSIS2.s.uBidiLevel = script_ANALYSIS.s.uBidiLevel;
        script_ANALYSIS2.s.fOverrideDirection = script_ANALYSIS.s.fOverrideDirection;
        script_ANALYSIS2.s.fInhibitSymSwap = script_ANALYSIS.s.fInhibitSymSwap;
        script_ANALYSIS2.s.fCharShape = script_ANALYSIS.s.fCharShape;
        script_ANALYSIS2.s.fDigitSubstitute = script_ANALYSIS.s.fDigitSubstitute;
        script_ANALYSIS2.s.fInhibitLigate = script_ANALYSIS.s.fInhibitLigate;
        script_ANALYSIS2.s.fDisplayZWG = script_ANALYSIS.s.fDisplayZWG;
        script_ANALYSIS2.s.fArabicNumContext = script_ANALYSIS.s.fArabicNumContext;
        script_ANALYSIS2.s.fGcpClusters = script_ANALYSIS.s.fGcpClusters;
        script_ANALYSIS2.s.fReserved = script_ANALYSIS.s.fReserved;
        script_ANALYSIS2.s.fEngineReserved = script_ANALYSIS.s.fEngineReserved;
        return script_ANALYSIS2;
    }
    
    int[] computePolyline(final int n, final int n2, final int n3, final int n4) {
        final int n5 = 2 * (n4 - n2);
        int ceil = Compatibility.ceil(n3 - n, n5);
        if (ceil == 0 && n3 - n > 2) {
            ceil = 1;
        }
        final int n6 = (2 * ceil + 1) * 2;
        if (n6 < 0) {
            return new int[0];
        }
        final int[] array = new int[n6];
        for (int i = 0; i < ceil; ++i) {
            final int n7 = 4 * i;
            array[n7] = n + n5 * i;
            array[n7 + 1] = n4;
            array[n7 + 2] = array[n7] + n5 / 2;
            array[n7 + 3] = n2;
        }
        array[n6 - 2] = n + n5 * ceil;
        array[n6 - 1] = n4;
        return array;
    }
    
    int createGdipBrush(final int n, final int n2) {
        final int color_new = Gdip.Color_new((n2 & 0xFF) << 24 | (n >> 16 & 0xFF) | (n & 0xFF00) | (n & 0xFF) << 16);
        final int solidBrush_new = Gdip.SolidBrush_new(color_new);
        Gdip.Color_delete(color_new);
        return solidBrush_new;
    }
    
    int createGdipBrush(final Color color, final int n) {
        return this.createGdipBrush(color.handle, n);
    }
    
    public void draw(final GC gc, final int n, final int n2) {
        this.draw(gc, n, n2, -1, -1, null, null);
    }
    
    public void draw(final GC gc, final int n, final int n2, final int n3, final int n4, final Color color, final Color color2) {
        this.draw(gc, n, n2, n3, n4, color, color2, 0);
    }
    
    public void draw(final GC gc, final int n, final int n2, int translateOffset, int translateOffset2, final Color color, final Color color2, final int n3) {
        this.checkLayout();
        this.computeRuns(gc);
        if (gc == null) {
            SWT.error(4);
        }
        if (gc.isDisposed()) {
            SWT.error(5);
        }
        if (color != null && color.isDisposed()) {
            SWT.error(5);
        }
        if (color2 != null && color2.isDisposed()) {
            SWT.error(5);
        }
        final int length = this.text.length();
        if (length == 0 && n3 == 0) {
            return;
        }
        final int handle = gc.handle;
        final Rectangle clipping = gc.getClipping();
        final GCData data = gc.data;
        final int gdipGraphics = data.gdipGraphics;
        final int foreground = data.foreground;
        final int getSysColor = OS.GetSysColor(OS.COLOR_HOTLIGHT);
        final int alpha = data.alpha;
        final boolean b = gdipGraphics != 0;
        int fgBrush = 0;
        int gdipBrush = 0;
        int saveDC = 0;
        if (b) {
            gc.checkGC(1);
            fgBrush = gc.getFgBrush();
        }
        else {
            saveDC = OS.SaveDC(handle);
            if ((data.style & 0x8000000) != 0x0) {
                OS.SetLayout(handle, OS.GetLayout(handle) | 0x1);
            }
        }
        final boolean b2 = translateOffset <= translateOffset2 && translateOffset != -1 && translateOffset2 != -1;
        int gdipBrush2 = 0;
        int gdipBrush3 = 0;
        int font_new = 0;
        int n4 = 0;
        int createSolidBrush = 0;
        int n5 = 0;
        if (b2 || ((n3 & 0x100000) != 0x0 && (n3 & 0x30000) != 0x0)) {
            final int n6 = (color != null) ? color.handle : OS.GetSysColor(OS.COLOR_HIGHLIGHTTEXT);
            final int n7 = (color2 != null) ? color2.handle : OS.GetSysColor(OS.COLOR_HIGHLIGHT);
            if (b) {
                gdipBrush2 = this.createGdipBrush(n7, alpha);
                gdipBrush3 = this.createGdipBrush(n6, alpha);
            }
            else {
                createSolidBrush = OS.CreateSolidBrush(n7);
                n5 = n6;
            }
            if (b2) {
                translateOffset = this.translateOffset(Math.min(Math.max(0, translateOffset), length - 1));
                translateOffset2 = this.translateOffset(Math.min(Math.max(0, translateOffset2), length - 1));
            }
        }
        final RECT rect = new RECT();
        OS.SetBkMode(handle, 1);
        for (int i = 0; i < this.runs.length; ++i) {
            int n8 = n + this.getLineIndent(i);
            final int n9 = n2 + this.lineY[i];
            final StyleItem[] array = this.runs[i];
            final int n10 = this.lineY[i + 1] - this.lineY[i] - this.lineSpacing;
            if ((n3 & 0x30000) != 0x0 && (b2 || (n3 & 0x100000) != 0x0)) {
                boolean b3 = false;
                if (i == this.runs.length - 1 && (n3 & 0x100000) != 0x0) {
                    b3 = true;
                }
                else {
                    final StyleItem styleItem = array[array.length - 1];
                    if (styleItem.lineBreak && !styleItem.softBreak) {
                        if (translateOffset <= styleItem.start && styleItem.start <= translateOffset2) {
                            b3 = true;
                        }
                    }
                    else {
                        final int n11 = styleItem.start + styleItem.length - 1;
                        if (translateOffset <= n11 && n11 < translateOffset2 && (n3 & 0x10000) != 0x0) {
                            b3 = true;
                        }
                    }
                }
                if (b3) {
                    int n12;
                    if ((n3 & 0x10000) != 0x0) {
                        n12 = (OS.IsWin95 ? 32767 : 117440511);
                    }
                    else {
                        n12 = n10 / 3;
                    }
                    if (b) {
                        Gdip.Graphics_FillRectangle(gdipGraphics, gdipBrush2, n8 + this.lineWidth[i], n9, n12, n10);
                    }
                    else {
                        OS.SelectObject(handle, createSolidBrush);
                        OS.PatBlt(handle, n8 + this.lineWidth[i], n9, n12, n10, 15728673);
                    }
                }
            }
            if (n8 <= clipping.x + clipping.width) {
                if (n8 + this.lineWidth[i] >= clipping.x) {
                    final int n13 = n8;
                    for (int j = 0; j < array.length; ++j) {
                        final StyleItem styleItem2 = array[j];
                        if (styleItem2.length != 0) {
                            if (n8 > clipping.x + clipping.width) {
                                break;
                            }
                            if (n8 + styleItem2.width >= clipping.x && (!styleItem2.lineBreak || styleItem2.softBreak)) {
                                OS.SetRect(rect, n8, n9, n8 + styleItem2.width, n9 + n10);
                                if (b) {
                                    this.drawRunBackgroundGDIP(styleItem2, gdipGraphics, rect, translateOffset, translateOffset2, alpha, gdipBrush2, b2);
                                }
                                else {
                                    this.drawRunBackground(styleItem2, handle, rect, translateOffset, translateOffset2, createSolidBrush, b2);
                                }
                            }
                            n8 += styleItem2.width;
                        }
                    }
                    int n14 = Math.max(0, this.ascent);
                    int min = 0;
                    for (int k = 0; k < array.length; ++k) {
                        n14 = Math.max(n14, array[k].ascent);
                        min = Math.min(min, array[k].underlinePos);
                    }
                    RECT rect2 = null;
                    RECT rect3 = null;
                    RECT rect4 = null;
                    int n15 = n13;
                    for (int l = 0; l < array.length; ++l) {
                        final StyleItem styleItem3 = array[l];
                        final TextStyle style = styleItem3.style;
                        final boolean b4 = style != null && (style.underline || style.strikeout || style.borderStyle != 0);
                        if (styleItem3.length != 0) {
                            if (n15 > clipping.x + clipping.width) {
                                break;
                            }
                            if (n15 + styleItem3.width >= clipping.x && (!styleItem3.tab || b4) && (!styleItem3.lineBreak || styleItem3.softBreak) && (style == null || style.metrics == null)) {
                                OS.SetRect(rect, n15, n9, n15 + styleItem3.width, n9 + n10);
                                if (b) {
                                    final int itemFont = this.getItemFont(styleItem3);
                                    if (itemFont != n4) {
                                        n4 = itemFont;
                                        if (font_new != 0) {
                                            Gdip.Font_delete(font_new);
                                        }
                                        final int selectObject = OS.SelectObject(handle, itemFont);
                                        font_new = Gdip.Font_new(handle, itemFont);
                                        OS.SelectObject(handle, selectObject);
                                        if (font_new == 0) {
                                            SWT.error(2);
                                        }
                                        if (!Gdip.Font_IsAvailable(font_new)) {
                                            Gdip.Font_delete(font_new);
                                            font_new = 0;
                                        }
                                    }
                                    int n16 = fgBrush;
                                    if (style != null && style.underline && style.underlineStyle == 4) {
                                        if (gdipBrush == 0) {
                                            gdipBrush = this.createGdipBrush(getSysColor, alpha);
                                        }
                                        n16 = gdipBrush;
                                    }
                                    RECT rect5;
                                    if (font_new != 0 && !styleItem3.analysis.fNoGlyphIndex) {
                                        rect5 = this.drawRunTextGDIP(gdipGraphics, styleItem3, rect, font_new, n14, n16, gdipBrush3, translateOffset, translateOffset2, alpha);
                                    }
                                    else {
                                        rect5 = this.drawRunTextGDIPRaster(gdipGraphics, styleItem3, rect, n14, (style != null && style.underline && style.underlineStyle == 4) ? getSysColor : foreground, n5, translateOffset, translateOffset2);
                                    }
                                    rect3 = this.drawUnderlineGDIP(gdipGraphics, n, n9 + n14, min, n9 + n10, array, l, n16, gdipBrush3, rect3, rect5, translateOffset, translateOffset2, alpha, clipping);
                                    rect4 = this.drawStrikeoutGDIP(gdipGraphics, n, n9 + n14, array, l, n16, gdipBrush3, rect4, rect5, translateOffset, translateOffset2, alpha, clipping);
                                    rect2 = this.drawBorderGDIP(gdipGraphics, n, n9, n10, array, l, n16, gdipBrush3, rect2, rect5, translateOffset, translateOffset2, alpha, clipping);
                                }
                                else {
                                    final int n17 = (style != null && style.underline && style.underlineStyle == 4) ? getSysColor : foreground;
                                    final RECT drawRunText = this.drawRunText(handle, styleItem3, rect, n14, n17, n5, translateOffset, translateOffset2);
                                    rect3 = this.drawUnderline(handle, n, n9 + n14, min, n9 + n10, array, l, n17, n5, rect3, drawRunText, translateOffset, translateOffset2, clipping);
                                    rect4 = this.drawStrikeout(handle, n, n9 + n14, array, l, n17, n5, rect4, drawRunText, translateOffset, translateOffset2, clipping);
                                    rect2 = this.drawBorder(handle, n, n9, n10, array, l, n17, n5, rect2, drawRunText, translateOffset, translateOffset2, clipping);
                                }
                            }
                            n15 += styleItem3.width;
                        }
                    }
                }
            }
        }
        if (gdipBrush2 != 0) {
            Gdip.SolidBrush_delete(gdipBrush2);
        }
        if (gdipBrush3 != 0) {
            Gdip.SolidBrush_delete(gdipBrush3);
        }
        if (gdipBrush != 0) {
            Gdip.SolidBrush_delete(gdipBrush);
        }
        if (font_new != 0) {
            Gdip.Font_delete(font_new);
        }
        if (saveDC != 0) {
            OS.RestoreDC(handle, saveDC);
        }
        if (createSolidBrush != 0) {
            OS.DeleteObject(createSolidBrush);
        }
    }
    
    RECT drawBorder(final int n, final int n2, final int n3, final int n4, final StyleItem[] array, final int n5, int lbColor, final int lbColor2, RECT addClipRect, final RECT rect, final int n6, final int n7, final Rectangle rectangle) {
        final StyleItem styleItem = array[n5];
        final TextStyle style = styleItem.style;
        if (style == null) {
            return null;
        }
        if (style.borderStyle == 0) {
            return null;
        }
        addClipRect = this.addClipRect(styleItem, addClipRect, rect, n6, n7);
        final boolean b = rectangle != null && n2 + styleItem.x + styleItem.width > rectangle.x + rectangle.width;
        if (n5 + 1 >= array.length || b || !style.isAdherentBorder(array[n5 + 1].style)) {
            int n8 = styleItem.x;
            int n9 = styleItem.start;
            int max = styleItem.start + styleItem.length - 1;
            for (int n10 = n5; n10 > 0 && style.isAdherentBorder(array[n10 - 1].style); --n10) {
                n8 = array[n10 - 1].x;
                n9 = Math.min(n9, array[n10 - 1].start);
                max = Math.max(max, array[n10 - 1].start + array[n10 - 1].length - 1);
            }
            final boolean b2 = n6 <= n7 && n6 != -1 && n7 != -1 && n6 <= n9 && max <= n7;
            if (style.borderColor != null) {
                lbColor = style.borderColor.handle;
                addClipRect = null;
            }
            else if (b2) {
                lbColor = lbColor2;
                addClipRect = null;
            }
            else if (style.foreground != null) {
                lbColor = style.foreground.handle;
            }
            final int n11 = 1;
            int n12 = 1;
            int n13 = 0;
            switch (style.borderStyle) {
                case 2: {
                    n13 = 1;
                    n12 = 4;
                    break;
                }
                case 4: {
                    n13 = 2;
                    n12 = 2;
                    break;
                }
            }
            final int selectObject = OS.SelectObject(n, OS.GetStockObject(5));
            final LOGBRUSH logbrush = new LOGBRUSH();
            logbrush.lbStyle = 0;
            logbrush.lbColor = lbColor;
            final int extCreatePen = OS.ExtCreatePen(n13 | 0x10000, n11, logbrush, 0, null);
            final int selectObject2 = OS.SelectObject(n, extCreatePen);
            final RECT rect2 = new RECT();
            OS.SetRect(rect2, n2 + n8, n3, n2 + styleItem.x + styleItem.width, n3 + n4);
            if (rectangle != null) {
                if (rect2.left < rectangle.x) {
                    rect2.left = rectangle.x / n12 * n12 + rect2.left % n12 - n12;
                }
                if (rect2.right > rectangle.x + rectangle.width) {
                    rect2.right = (rectangle.x + rectangle.width) / n12 * n12 + rect2.right % n12 + n12;
                }
            }
            OS.Rectangle(n, rect2.left, rect2.top, rect2.right, rect2.bottom);
            OS.SelectObject(n, selectObject2);
            OS.DeleteObject(extCreatePen);
            if (addClipRect != null) {
                final int saveDC = OS.SaveDC(n);
                if (addClipRect.left == -1) {
                    addClipRect.left = 0;
                }
                if (addClipRect.right == -1) {
                    addClipRect.right = 524287;
                }
                OS.IntersectClipRect(n, addClipRect.left, addClipRect.top, addClipRect.right, addClipRect.bottom);
                logbrush.lbColor = lbColor2;
                final int extCreatePen2 = OS.ExtCreatePen(n13 | 0x10000, n11, logbrush, 0, null);
                final int selectObject3 = OS.SelectObject(n, extCreatePen2);
                OS.Rectangle(n, rect2.left, rect2.top, rect2.right, rect2.bottom);
                OS.RestoreDC(n, saveDC);
                OS.SelectObject(n, selectObject3);
                OS.DeleteObject(extCreatePen2);
            }
            OS.SelectObject(n, selectObject);
            return null;
        }
        return addClipRect;
    }
    
    RECT drawBorderGDIP(final int n, final int n2, final int n3, final int n4, final StyleItem[] array, final int n5, final int n6, final int n7, RECT addClipRect, final RECT rect, final int n8, final int n9, final int n10, final Rectangle rectangle) {
        final StyleItem styleItem = array[n5];
        final TextStyle style = styleItem.style;
        if (style == null) {
            return null;
        }
        if (style.borderStyle == 0) {
            return null;
        }
        addClipRect = this.addClipRect(styleItem, addClipRect, rect, n8, n9);
        final boolean b = rectangle != null && n2 + styleItem.x + styleItem.width > rectangle.x + rectangle.width;
        if (n5 + 1 >= array.length || b || !style.isAdherentBorder(array[n5 + 1].style)) {
            int n11 = styleItem.x;
            int n12 = styleItem.start;
            int max = styleItem.start + styleItem.length - 1;
            for (int n13 = n5; n13 > 0 && style.isAdherentBorder(array[n13 - 1].style); --n13) {
                n11 = array[n13 - 1].x;
                n12 = Math.min(n12, array[n13 - 1].start);
                max = Math.max(max, array[n13 - 1].start + array[n13 - 1].length - 1);
            }
            final boolean b2 = n8 <= n9 && n8 != -1 && n9 != -1 && n8 <= n12 && max <= n9;
            int n14 = n6;
            if (style.borderColor != null) {
                n14 = this.createGdipBrush(style.borderColor, n10);
                addClipRect = null;
            }
            else if (b2) {
                n14 = n7;
                addClipRect = null;
            }
            else if (style.foreground != null) {
                n14 = this.createGdipBrush(style.foreground, n10);
            }
            final int n15 = 1;
            int n16 = 0;
            switch (style.borderStyle) {
                case 2: {
                    n16 = 1;
                    break;
                }
                case 4: {
                    n16 = 2;
                    break;
                }
            }
            final int pen_new = Gdip.Pen_new(n14, n15);
            Gdip.Pen_SetDashStyle(pen_new, n16);
            Gdip.Graphics_SetPixelOffsetMode(n, 3);
            final int graphics_GetSmoothingMode = Gdip.Graphics_GetSmoothingMode(n);
            Gdip.Graphics_SetSmoothingMode(n, 3);
            if (addClipRect != null) {
                final int graphics_Save = Gdip.Graphics_Save(n);
                if (addClipRect.left == -1) {
                    addClipRect.left = 0;
                }
                if (addClipRect.right == -1) {
                    addClipRect.right = 524287;
                }
                final Rect rect2 = new Rect();
                rect2.X = addClipRect.left;
                rect2.Y = addClipRect.top;
                rect2.Width = addClipRect.right - addClipRect.left;
                rect2.Height = addClipRect.bottom - addClipRect.top;
                Gdip.Graphics_SetClip(n, rect2, 4);
                Gdip.Graphics_DrawRectangle(n, pen_new, n2 + n11, n3, styleItem.x + styleItem.width - n11 - 1, n4 - 1);
                Gdip.Graphics_Restore(n, graphics_Save);
                final int graphics_Save2 = Gdip.Graphics_Save(n);
                Gdip.Graphics_SetClip(n, rect2, 1);
                final int pen_new2 = Gdip.Pen_new(n7, n15);
                Gdip.Pen_SetDashStyle(pen_new2, n16);
                Gdip.Graphics_DrawRectangle(n, pen_new2, n2 + n11, n3, styleItem.x + styleItem.width - n11 - 1, n4 - 1);
                Gdip.Pen_delete(pen_new2);
                Gdip.Graphics_Restore(n, graphics_Save2);
            }
            else {
                Gdip.Graphics_DrawRectangle(n, pen_new, n2 + n11, n3, styleItem.x + styleItem.width - n11 - 1, n4 - 1);
            }
            Gdip.Graphics_SetPixelOffsetMode(n, 4);
            Gdip.Graphics_SetSmoothingMode(n, graphics_GetSmoothingMode);
            Gdip.Pen_delete(pen_new);
            if (n14 != n7 && n14 != n6) {
                Gdip.SolidBrush_delete(n14);
            }
            return null;
        }
        return addClipRect;
    }
    
    void drawRunBackground(final StyleItem styleItem, final int n, final RECT rect, final int n2, final int n3, final int n4, final boolean b) {
        final int n5 = styleItem.start + styleItem.length - 1;
        if (b && n2 <= styleItem.start && n3 >= n5) {
            OS.SelectObject(n, n4);
            OS.PatBlt(n, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top, 15728673);
        }
        else {
            if (styleItem.style != null && styleItem.style.background != null) {
                final int createSolidBrush = OS.CreateSolidBrush(styleItem.style.background.handle);
                final int selectObject = OS.SelectObject(n, createSolidBrush);
                OS.PatBlt(n, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top, 15728673);
                OS.SelectObject(n, selectObject);
                OS.DeleteObject(createSolidBrush);
            }
            if (b && n2 <= n5 && styleItem.start <= n3) {
                this.getPartialSelection(styleItem, n2, n3, rect);
                OS.SelectObject(n, n4);
                OS.PatBlt(n, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top, 15728673);
            }
        }
    }
    
    void drawRunBackgroundGDIP(final StyleItem styleItem, final int n, final RECT rect, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        final int n6 = styleItem.start + styleItem.length - 1;
        if (b && n2 <= styleItem.start && n3 >= n6) {
            Gdip.Graphics_FillRectangle(n, n5, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
        }
        else {
            if (styleItem.style != null && styleItem.style.background != null) {
                final int gdipBrush = this.createGdipBrush(styleItem.style.background, n4);
                Gdip.Graphics_FillRectangle(n, gdipBrush, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
                Gdip.SolidBrush_delete(gdipBrush);
            }
            if (b && n2 <= n6 && styleItem.start <= n3) {
                this.getPartialSelection(styleItem, n2, n3, rect);
                if (rect.left > rect.right) {
                    final int left = rect.left;
                    rect.left = rect.right;
                    rect.right = left;
                }
                Gdip.Graphics_FillRectangle(n, n5, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
            }
        }
    }
    
    RECT drawRunText(final int n, final StyleItem styleItem, final RECT rect, final int n2, int handle, final int n3, final int n4, final int n5) {
        final int n6 = styleItem.start + styleItem.length - 1;
        final boolean b = n4 <= n5 && n4 != -1 && n5 != -1;
        final boolean b2 = b && n4 <= styleItem.start && n5 >= n6;
        final boolean b3 = b && !b2 && n4 <= n6 && styleItem.start <= n5;
        final int n7 = rect.left + (((this.orientation & 0x4000000) != 0x0) ? -1 : 0);
        final int n8 = rect.top + (n2 - styleItem.ascent);
        OS.SelectObject(n, this.getItemFont(styleItem));
        if (b2) {
            handle = n3;
        }
        else if (styleItem.style != null && styleItem.style.foreground != null) {
            handle = styleItem.style.foreground.handle;
        }
        OS.SetTextColor(n, handle);
        OS.ScriptTextOut(n, styleItem.psc, n7, n8, 0, null, styleItem.analysis, 0, 0, styleItem.glyphs, styleItem.glyphCount, styleItem.advances, styleItem.justify, styleItem.goffsets);
        if (b3) {
            this.getPartialSelection(styleItem, n4, n5, rect);
            OS.SetTextColor(n, n3);
            OS.ScriptTextOut(n, styleItem.psc, n7, n8, 4, rect, styleItem.analysis, 0, 0, styleItem.glyphs, styleItem.glyphCount, styleItem.advances, styleItem.justify, styleItem.goffsets);
        }
        return (b2 || b3) ? rect : null;
    }
    
    RECT drawRunTextGDIP(final int n, final StyleItem styleItem, final RECT rect, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final int n9 = styleItem.start + styleItem.length - 1;
        final boolean b = n6 <= n7 && n6 != -1 && n7 != -1;
        final boolean b2 = b && n6 <= styleItem.start && n7 >= n9;
        final boolean b3 = b && !b2 && n6 <= n9 && styleItem.start <= n7;
        final int n10 = rect.top + n3;
        final int left = rect.left;
        int gdipBrush = n4;
        if (b2) {
            gdipBrush = n5;
        }
        else if (styleItem.style != null && styleItem.style.foreground != null) {
            gdipBrush = this.createGdipBrush(styleItem.style.foreground, n8);
        }
        int graphics_Save = 0;
        Rect rect2 = null;
        if (b3) {
            rect2 = new Rect();
            this.getPartialSelection(styleItem, n6, n7, rect);
            rect2.X = rect.left;
            rect2.Y = rect.top;
            rect2.Width = rect.right - rect.left;
            rect2.Height = rect.bottom - rect.top;
            graphics_Save = Gdip.Graphics_Save(n);
            Gdip.Graphics_SetClip(n, rect2, 4);
        }
        int n11 = 0;
        final boolean b4 = (this.orientation & 0x4000000) != 0x0;
        if (b4) {
            switch (Gdip.Brush_GetType(gdipBrush)) {
                case 4: {
                    Gdip.LinearGradientBrush_ScaleTransform(gdipBrush, -1.0f, 1.0f, 0);
                    Gdip.LinearGradientBrush_TranslateTransform(gdipBrush, -2 * left - styleItem.width, 0.0f, 0);
                    break;
                }
                case 2: {
                    Gdip.TextureBrush_ScaleTransform(gdipBrush, -1.0f, 1.0f, 0);
                    Gdip.TextureBrush_TranslateTransform(gdipBrush, -2 * left - styleItem.width, 0.0f, 0);
                    break;
                }
            }
            n11 = Gdip.Graphics_Save(n);
            Gdip.Graphics_ScaleTransform(n, -1.0f, 1.0f, 0);
            Gdip.Graphics_TranslateTransform(n, -2 * left - styleItem.width, 0.0f, 0);
        }
        final int[] array = new int[styleItem.glyphCount];
        final float[] array2 = new float[styleItem.glyphCount * 2];
        C.memmove(array, (styleItem.justify != 0) ? styleItem.justify : styleItem.advances, styleItem.glyphCount * 4);
        int n12 = left;
        int i = 0;
        int n13 = 0;
        while (i < array.length) {
            array2[n13++] = n12;
            array2[n13++] = n10;
            n12 += array[i];
            ++i;
        }
        Gdip.Graphics_DrawDriverString(n, styleItem.glyphs, styleItem.glyphCount, n2, gdipBrush, array2, 0, 0);
        if (b3) {
            if (b4) {
                Gdip.Graphics_Restore(n, n11);
            }
            Gdip.Graphics_Restore(n, graphics_Save);
            final int graphics_Save2 = Gdip.Graphics_Save(n);
            Gdip.Graphics_SetClip(n, rect2, 1);
            if (b4) {
                n11 = Gdip.Graphics_Save(n);
                Gdip.Graphics_ScaleTransform(n, -1.0f, 1.0f, 0);
                Gdip.Graphics_TranslateTransform(n, -2 * left - styleItem.width, 0.0f, 0);
            }
            Gdip.Graphics_DrawDriverString(n, styleItem.glyphs, styleItem.glyphCount, n2, n5, array2, 0, 0);
            Gdip.Graphics_Restore(n, graphics_Save2);
        }
        if (b4) {
            switch (Gdip.Brush_GetType(gdipBrush)) {
                case 4: {
                    Gdip.LinearGradientBrush_ResetTransform(gdipBrush);
                    break;
                }
                case 2: {
                    Gdip.TextureBrush_ResetTransform(gdipBrush);
                    break;
                }
            }
            Gdip.Graphics_Restore(n, n11);
        }
        if (gdipBrush != n5 && gdipBrush != n4) {
            Gdip.SolidBrush_delete(gdipBrush);
        }
        return (b2 || b3) ? rect : null;
    }
    
    RECT drawRunTextGDIPRaster(final int n, final StyleItem styleItem, final RECT rect, final int n2, final int n3, final int n4, final int n5, final int n6) {
        int region_GetHRGN = 0;
        Gdip.Graphics_SetPixelOffsetMode(n, 3);
        final int region_new = Gdip.Region_new();
        if (region_new == 0) {
            SWT.error(2);
        }
        Gdip.Graphics_GetClip(n, region_new);
        if (!Gdip.Region_IsInfinite(region_new, n)) {
            region_GetHRGN = Gdip.Region_GetHRGN(region_new, n);
        }
        Gdip.Region_delete(region_new);
        Gdip.Graphics_SetPixelOffsetMode(n, 4);
        float[] array = null;
        final int matrix_new = Gdip.Matrix_new(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
        if (matrix_new == 0) {
            SWT.error(2);
        }
        Gdip.Graphics_GetTransform(n, matrix_new);
        if (!Gdip.Matrix_IsIdentity(matrix_new)) {
            array = new float[6];
            Gdip.Matrix_GetElements(matrix_new, array);
        }
        Gdip.Matrix_delete(matrix_new);
        final int graphics_GetHDC = Gdip.Graphics_GetHDC(n);
        final int saveDC = OS.SaveDC(graphics_GetHDC);
        if (array != null) {
            OS.SetGraphicsMode(graphics_GetHDC, 2);
            OS.SetWorldTransform(graphics_GetHDC, array);
        }
        if (region_GetHRGN != 0) {
            OS.SelectClipRgn(graphics_GetHDC, region_GetHRGN);
            OS.DeleteObject(region_GetHRGN);
        }
        if ((this.orientation & 0x4000000) != 0x0) {
            OS.SetLayout(graphics_GetHDC, OS.GetLayout(graphics_GetHDC) | 0x1);
        }
        OS.SetBkMode(graphics_GetHDC, 1);
        final RECT drawRunText = this.drawRunText(graphics_GetHDC, styleItem, rect, n2, n3, n4, n5, n6);
        OS.RestoreDC(graphics_GetHDC, saveDC);
        Gdip.Graphics_ReleaseHDC(n, graphics_GetHDC);
        return drawRunText;
    }
    
    RECT drawStrikeout(final int n, final int n2, final int n3, final StyleItem[] array, final int n4, int n5, final int n6, RECT addClipRect, final RECT rect, final int n7, final int n8, final Rectangle rectangle) {
        final StyleItem styleItem = array[n4];
        final TextStyle style = styleItem.style;
        if (style == null) {
            return null;
        }
        if (!style.strikeout) {
            return null;
        }
        addClipRect = this.addClipRect(styleItem, addClipRect, rect, n7, n8);
        final boolean b = rectangle != null && n2 + styleItem.x + styleItem.width > rectangle.x + rectangle.width;
        if (n4 + 1 >= array.length || b || !style.isAdherentStrikeout(array[n4 + 1].style)) {
            int n9 = styleItem.x;
            int n10 = styleItem.start;
            int max = styleItem.start + styleItem.length - 1;
            for (int n11 = n4; n11 > 0 && style.isAdherentStrikeout(array[n11 - 1].style); --n11) {
                n9 = array[n11 - 1].x;
                n10 = Math.min(n10, array[n11 - 1].start);
                max = Math.max(max, array[n11 - 1].start + array[n11 - 1].length - 1);
            }
            final boolean b2 = n7 <= n8 && n7 != -1 && n8 != -1 && n7 <= n10 && max <= n8;
            if (style.strikeoutColor != null) {
                n5 = style.strikeoutColor.handle;
                addClipRect = null;
            }
            else if (b2) {
                n5 = n6;
                addClipRect = null;
            }
            else if (style.foreground != null) {
                n5 = style.foreground.handle;
            }
            final RECT rect2 = new RECT();
            OS.SetRect(rect2, n2 + n9, n3 - styleItem.strikeoutPos, n2 + styleItem.x + styleItem.width, n3 - styleItem.strikeoutPos + styleItem.strikeoutThickness);
            final int createSolidBrush = OS.CreateSolidBrush(n5);
            OS.FillRect(n, rect2, createSolidBrush);
            OS.DeleteObject(createSolidBrush);
            if (addClipRect != null) {
                final int createSolidBrush2 = OS.CreateSolidBrush(n6);
                if (addClipRect.left == -1) {
                    addClipRect.left = 0;
                }
                if (addClipRect.right == -1) {
                    addClipRect.right = 524287;
                }
                OS.SetRect(addClipRect, Math.max(rect2.left, addClipRect.left), rect2.top, Math.min(rect2.right, addClipRect.right), rect2.bottom);
                OS.FillRect(n, addClipRect, createSolidBrush2);
                OS.DeleteObject(createSolidBrush2);
            }
            return null;
        }
        return addClipRect;
    }
    
    RECT drawStrikeoutGDIP(final int n, final int n2, final int n3, final StyleItem[] array, final int n4, int n5, final int n6, RECT addClipRect, final RECT rect, final int n7, final int n8, final int n9, final Rectangle rectangle) {
        final StyleItem styleItem = array[n4];
        final TextStyle style = styleItem.style;
        if (style == null) {
            return null;
        }
        if (!style.strikeout) {
            return null;
        }
        addClipRect = this.addClipRect(styleItem, addClipRect, rect, n7, n8);
        final boolean b = rectangle != null && n2 + styleItem.x + styleItem.width > rectangle.x + rectangle.width;
        if (n4 + 1 >= array.length || b || !style.isAdherentStrikeout(array[n4 + 1].style)) {
            int n10 = styleItem.x;
            int n11 = styleItem.start;
            int max = styleItem.start + styleItem.length - 1;
            for (int n12 = n4; n12 > 0 && style.isAdherentStrikeout(array[n12 - 1].style); --n12) {
                n10 = array[n12 - 1].x;
                n11 = Math.min(n11, array[n12 - 1].start);
                max = Math.max(max, array[n12 - 1].start + array[n12 - 1].length - 1);
            }
            final boolean b2 = n7 <= n8 && n7 != -1 && n8 != -1 && n7 <= n11 && max <= n8;
            int n13 = n5;
            if (style.strikeoutColor != null) {
                n13 = this.createGdipBrush(style.strikeoutColor, n9);
                addClipRect = null;
            }
            else if (b2) {
                n5 = n6;
                addClipRect = null;
            }
            else if (style.foreground != null) {
                n13 = this.createGdipBrush(style.foreground, n9);
            }
            if (addClipRect != null) {
                final int graphics_Save = Gdip.Graphics_Save(n);
                if (addClipRect.left == -1) {
                    addClipRect.left = 0;
                }
                if (addClipRect.right == -1) {
                    addClipRect.right = 524287;
                }
                final Rect rect2 = new Rect();
                rect2.X = addClipRect.left;
                rect2.Y = addClipRect.top;
                rect2.Width = addClipRect.right - addClipRect.left;
                rect2.Height = addClipRect.bottom - addClipRect.top;
                Gdip.Graphics_SetClip(n, rect2, 4);
                Gdip.Graphics_FillRectangle(n, n13, n2 + n10, n3 - styleItem.strikeoutPos, styleItem.x + styleItem.width - n10, styleItem.strikeoutThickness);
                Gdip.Graphics_Restore(n, graphics_Save);
                final int graphics_Save2 = Gdip.Graphics_Save(n);
                Gdip.Graphics_SetClip(n, rect2, 1);
                Gdip.Graphics_FillRectangle(n, n6, n2 + n10, n3 - styleItem.strikeoutPos, styleItem.x + styleItem.width - n10, styleItem.strikeoutThickness);
                Gdip.Graphics_Restore(n, graphics_Save2);
            }
            else {
                Gdip.Graphics_FillRectangle(n, n13, n2 + n10, n3 - styleItem.strikeoutPos, styleItem.x + styleItem.width - n10, styleItem.strikeoutThickness);
            }
            if (n13 != n6 && n13 != n5) {
                Gdip.SolidBrush_delete(n13);
            }
            return null;
        }
        return addClipRect;
    }
    
    RECT drawUnderline(final int n, final int n2, final int n3, final int n4, final int n5, final StyleItem[] array, final int n6, int n7, final int n8, RECT addClipRect, final RECT rect, final int n9, final int n10, final Rectangle rectangle) {
        final StyleItem styleItem = array[n6];
        final TextStyle style = styleItem.style;
        if (style == null) {
            return null;
        }
        if (!style.underline) {
            return null;
        }
        addClipRect = this.addClipRect(styleItem, addClipRect, rect, n9, n10);
        final boolean b = rectangle != null && n2 + styleItem.x + styleItem.width > rectangle.x + rectangle.width;
        if (n6 + 1 >= array.length || b || !style.isAdherentUnderline(array[n6 + 1].style)) {
            int n11 = styleItem.x;
            int n12 = styleItem.start;
            int max = styleItem.start + styleItem.length - 1;
            for (int n13 = n6; n13 > 0 && style.isAdherentUnderline(array[n13 - 1].style); --n13) {
                n11 = array[n13 - 1].x;
                n12 = Math.min(n12, array[n13 - 1].start);
                max = Math.max(max, array[n13 - 1].start + array[n13 - 1].length - 1);
            }
            final boolean b2 = n9 <= n10 && n9 != -1 && n10 != -1 && n9 <= n12 && max <= n10;
            if (style.underlineColor != null) {
                n7 = style.underlineColor.handle;
                addClipRect = null;
            }
            else if (b2) {
                n7 = n8;
                addClipRect = null;
            }
            else if (style.foreground != null) {
                n7 = style.foreground.handle;
            }
            final RECT rect2 = new RECT();
            OS.SetRect(rect2, n2 + n11, n3 - n4, n2 + styleItem.x + styleItem.width, n3 - n4 + styleItem.underlineThickness);
            if (addClipRect != null) {
                if (addClipRect.left == -1) {
                    addClipRect.left = 0;
                }
                if (addClipRect.right == -1) {
                    addClipRect.right = 524287;
                }
                OS.SetRect(addClipRect, Math.max(rect2.left, addClipRect.left), rect2.top, Math.min(rect2.right, addClipRect.right), rect2.bottom);
            }
            switch (style.underlineStyle) {
                case 2:
                case 3: {
                    final int n14 = 1;
                    final int n15 = 2 * n14;
                    final int min = Math.min(rect2.top - n15 / 2, n5 - n15 - 1);
                    final int[] computePolyline = this.computePolyline(rect2.left, min, rect2.right, min + n15);
                    final int createPen = OS.CreatePen(0, n14, n7);
                    final int selectObject = OS.SelectObject(n, createPen);
                    final int saveDC = OS.SaveDC(n);
                    OS.IntersectClipRect(n, rect2.left, min, rect2.right + 1, min + n15 + 1);
                    OS.Polyline(n, computePolyline, computePolyline.length / 2);
                    final int length = computePolyline.length;
                    if (length >= 2 && n14 <= 1) {
                        OS.SetPixel(n, computePolyline[length - 2], computePolyline[length - 1], n7);
                    }
                    OS.SelectObject(n, selectObject);
                    OS.DeleteObject(createPen);
                    OS.RestoreDC(n, saveDC);
                    if (addClipRect != null) {
                        final int createPen2 = OS.CreatePen(0, n14, n8);
                        final int selectObject2 = OS.SelectObject(n, createPen2);
                        final int saveDC2 = OS.SaveDC(n);
                        OS.IntersectClipRect(n, addClipRect.left, min, addClipRect.right + 1, min + n15 + 1);
                        OS.Polyline(n, computePolyline, computePolyline.length / 2);
                        if (length >= 2 && n14 <= 1) {
                            OS.SetPixel(n, computePolyline[length - 2], computePolyline[length - 1], n8);
                        }
                        OS.SelectObject(n, selectObject2);
                        OS.DeleteObject(createPen2);
                        OS.RestoreDC(n, saveDC2);
                        break;
                    }
                    break;
                }
                case 0:
                case 1:
                case 4:
                case 196608: {
                    if (style.underlineStyle == 196608) {
                        final RECT rect3 = rect2;
                        rect3.top -= styleItem.underlineThickness;
                        if (addClipRect != null) {
                            final RECT rect4 = addClipRect;
                            rect4.top -= styleItem.underlineThickness;
                        }
                    }
                    final int n16 = (style.underlineStyle == 1) ? (rect2.bottom + styleItem.underlineThickness * 2) : rect2.bottom;
                    if (n16 > n5) {
                        OS.OffsetRect(rect2, 0, n5 - n16);
                        if (addClipRect != null) {
                            OS.OffsetRect(addClipRect, 0, n5 - n16);
                        }
                    }
                    final int createSolidBrush = OS.CreateSolidBrush(n7);
                    OS.FillRect(n, rect2, createSolidBrush);
                    if (style.underlineStyle == 1) {
                        OS.SetRect(rect2, rect2.left, rect2.top + styleItem.underlineThickness * 2, rect2.right, rect2.bottom + styleItem.underlineThickness * 2);
                        OS.FillRect(n, rect2, createSolidBrush);
                    }
                    OS.DeleteObject(createSolidBrush);
                    if (addClipRect != null) {
                        final int createSolidBrush2 = OS.CreateSolidBrush(n8);
                        OS.FillRect(n, addClipRect, createSolidBrush2);
                        if (style.underlineStyle == 1) {
                            OS.SetRect(addClipRect, addClipRect.left, rect2.top, addClipRect.right, rect2.bottom);
                            OS.FillRect(n, addClipRect, createSolidBrush2);
                        }
                        OS.DeleteObject(createSolidBrush2);
                        break;
                    }
                    break;
                }
                case 65536:
                case 131072: {
                    final int n17 = (style.underlineStyle == 131072) ? 1 : 2;
                    final int createPen3 = OS.CreatePen(n17, 1, n7);
                    final int selectObject3 = OS.SelectObject(n, createPen3);
                    OS.SetRect(rect2, rect2.left, n3 + styleItem.descent, rect2.right, n3 + styleItem.descent + styleItem.underlineThickness);
                    OS.MoveToEx(n, rect2.left, rect2.top, 0);
                    OS.LineTo(n, rect2.right, rect2.top);
                    OS.SelectObject(n, selectObject3);
                    OS.DeleteObject(createPen3);
                    if (addClipRect != null) {
                        final int createPen4 = OS.CreatePen(n17, 1, n8);
                        final int selectObject4 = OS.SelectObject(n, createPen4);
                        OS.SetRect(addClipRect, addClipRect.left, rect2.top, addClipRect.right, rect2.bottom);
                        OS.MoveToEx(n, addClipRect.left, addClipRect.top, 0);
                        OS.LineTo(n, addClipRect.right, addClipRect.top);
                        OS.SelectObject(n, selectObject4);
                        OS.DeleteObject(createPen4);
                        break;
                    }
                    break;
                }
            }
            return null;
        }
        return addClipRect;
    }
    
    RECT drawUnderlineGDIP(final int n, final int n2, final int n3, final int n4, final int n5, final StyleItem[] array, final int n6, final int n7, final int n8, RECT addClipRect, final RECT rect, final int n9, final int n10, final int n11, final Rectangle rectangle) {
        final StyleItem styleItem = array[n6];
        final TextStyle style = styleItem.style;
        if (style == null) {
            return null;
        }
        if (!style.underline) {
            return null;
        }
        addClipRect = this.addClipRect(styleItem, addClipRect, rect, n9, n10);
        final boolean b = rectangle != null && n2 + styleItem.x + styleItem.width > rectangle.x + rectangle.width;
        if (n6 + 1 >= array.length || b || !style.isAdherentUnderline(array[n6 + 1].style)) {
            int n12 = styleItem.x;
            int n13 = styleItem.start;
            int max = styleItem.start + styleItem.length - 1;
            for (int n14 = n6; n14 > 0 && style.isAdherentUnderline(array[n14 - 1].style); --n14) {
                n12 = array[n14 - 1].x;
                n13 = Math.min(n13, array[n14 - 1].start);
                max = Math.max(max, array[n14 - 1].start + array[n14 - 1].length - 1);
            }
            final boolean b2 = n9 <= n10 && n9 != -1 && n10 != -1 && n9 <= n13 && max <= n10;
            int n15 = n7;
            if (style.underlineColor != null) {
                n15 = this.createGdipBrush(style.underlineColor, n11);
                addClipRect = null;
            }
            else if (b2) {
                n15 = n8;
                addClipRect = null;
            }
            else if (style.foreground != null) {
                n15 = this.createGdipBrush(style.foreground, n11);
            }
            final RECT rect2 = new RECT();
            OS.SetRect(rect2, n2 + n12, n3 - n4, n2 + styleItem.x + styleItem.width, n3 - n4 + styleItem.underlineThickness);
            Rect rect3 = null;
            if (addClipRect != null) {
                if (addClipRect.left == -1) {
                    addClipRect.left = 0;
                }
                if (addClipRect.right == -1) {
                    addClipRect.right = 524287;
                }
                OS.SetRect(addClipRect, Math.max(rect2.left, addClipRect.left), rect2.top, Math.min(rect2.right, addClipRect.right), rect2.bottom);
                rect3 = new Rect();
                rect3.X = addClipRect.left;
                rect3.Y = addClipRect.top;
                rect3.Width = addClipRect.right - addClipRect.left;
                rect3.Height = addClipRect.bottom - addClipRect.top;
            }
            int n16 = 0;
            Gdip.Graphics_SetPixelOffsetMode(n, 3);
            final int graphics_GetSmoothingMode = Gdip.Graphics_GetSmoothingMode(n);
            Gdip.Graphics_SetSmoothingMode(n, 3);
            switch (style.underlineStyle) {
                case 2:
                case 3: {
                    final int n17 = 1;
                    final int n18 = 2 * n17;
                    final int min = Math.min(rect2.top - n18 / 2, n5 - n18 - 1);
                    final int[] computePolyline = this.computePolyline(rect2.left, min, rect2.right, min + n18);
                    final int pen_new = Gdip.Pen_new(n15, n17);
                    int n19 = Gdip.Graphics_Save(n);
                    if (rect3 != null) {
                        Gdip.Graphics_SetClip(n, rect3, 4);
                    }
                    else {
                        final Rect rect4 = new Rect();
                        rect4.X = rect2.left;
                        rect4.Y = min;
                        rect4.Width = rect2.right - rect2.left;
                        rect4.Height = n18 + 1;
                        Gdip.Graphics_SetClip(n, rect4, 1);
                    }
                    Gdip.Graphics_DrawLines(n, pen_new, computePolyline, computePolyline.length / 2);
                    if (rect3 != null) {
                        final int pen_new2 = Gdip.Pen_new(n8, n17);
                        Gdip.Graphics_Restore(n, n19);
                        n19 = Gdip.Graphics_Save(n);
                        Gdip.Graphics_SetClip(n, rect3, 1);
                        Gdip.Graphics_DrawLines(n, pen_new2, computePolyline, computePolyline.length / 2);
                        Gdip.Pen_delete(pen_new2);
                    }
                    Gdip.Graphics_Restore(n, n19);
                    Gdip.Pen_delete(pen_new);
                    if (n19 != 0) {
                        Gdip.Graphics_Restore(n, n19);
                        break;
                    }
                    break;
                }
                case 0:
                case 1:
                case 4:
                case 196608: {
                    if (style.underlineStyle == 196608) {
                        final RECT rect5 = rect2;
                        rect5.top -= styleItem.underlineThickness;
                    }
                    final int n20 = (style.underlineStyle == 1) ? (rect2.bottom + styleItem.underlineThickness * 2) : rect2.bottom;
                    if (n20 > n5) {
                        OS.OffsetRect(rect2, 0, n5 - n20);
                    }
                    if (rect3 != null) {
                        rect3.Y = rect2.top;
                        if (style.underlineStyle == 196608) {
                            rect3.Height = styleItem.underlineThickness * 2;
                        }
                        if (style.underlineStyle == 1) {
                            rect3.Height = styleItem.underlineThickness * 3;
                        }
                        n16 = Gdip.Graphics_Save(n);
                        Gdip.Graphics_SetClip(n, rect3, 4);
                    }
                    Gdip.Graphics_FillRectangle(n, n15, rect2.left, rect2.top, rect2.right - rect2.left, rect2.bottom - rect2.top);
                    if (style.underlineStyle == 1) {
                        Gdip.Graphics_FillRectangle(n, n15, rect2.left, rect2.top + styleItem.underlineThickness * 2, rect2.right - rect2.left, rect2.bottom - rect2.top);
                    }
                    if (rect3 != null) {
                        Gdip.Graphics_Restore(n, n16);
                        final int graphics_Save = Gdip.Graphics_Save(n);
                        Gdip.Graphics_SetClip(n, rect3, 1);
                        Gdip.Graphics_FillRectangle(n, n8, rect2.left, rect2.top, rect2.right - rect2.left, rect2.bottom - rect2.top);
                        if (style.underlineStyle == 1) {
                            Gdip.Graphics_FillRectangle(n, n8, rect2.left, rect2.top + styleItem.underlineThickness * 2, rect2.right - rect2.left, rect2.bottom - rect2.top);
                        }
                        Gdip.Graphics_Restore(n, graphics_Save);
                        break;
                    }
                    break;
                }
                case 65536:
                case 131072: {
                    final int pen_new3 = Gdip.Pen_new(n15, 1.0f);
                    final int n21 = (style.underlineStyle == 65536) ? 2 : 1;
                    Gdip.Pen_SetDashStyle(pen_new3, n21);
                    if (rect3 != null) {
                        n16 = Gdip.Graphics_Save(n);
                        Gdip.Graphics_SetClip(n, rect3, 4);
                    }
                    Gdip.Graphics_DrawLine(n, pen_new3, rect2.left, n3 + styleItem.descent, styleItem.width - styleItem.length, n3 + styleItem.descent);
                    if (rect3 != null) {
                        Gdip.Graphics_Restore(n, n16);
                        final int graphics_Save2 = Gdip.Graphics_Save(n);
                        Gdip.Graphics_SetClip(n, rect3, 1);
                        final int pen_new4 = Gdip.Pen_new(n15, 1.0f);
                        Gdip.Pen_SetDashStyle(pen_new4, n21);
                        Gdip.Graphics_DrawLine(n, pen_new4, rect2.left, n3 + styleItem.descent, styleItem.width - styleItem.length, n3 + styleItem.descent);
                        Gdip.Graphics_Restore(n, graphics_Save2);
                        Gdip.Pen_delete(pen_new4);
                    }
                    Gdip.Pen_delete(pen_new3);
                    break;
                }
            }
            if (n15 != n8 && n15 != n7) {
                Gdip.SolidBrush_delete(n15);
            }
            Gdip.Graphics_SetPixelOffsetMode(n, 4);
            Gdip.Graphics_SetSmoothingMode(n, graphics_GetSmoothingMode);
            return null;
        }
        return addClipRect;
    }
    
    void freeRuns() {
        if (this.allRuns == null) {
            return;
        }
        for (int i = 0; i < this.allRuns.length; ++i) {
            this.allRuns[i].free();
        }
        this.allRuns = null;
        this.runs = null;
        this.segmentsText = null;
    }
    
    public int getAlignment() {
        this.checkLayout();
        return this.alignment;
    }
    
    public int getAscent() {
        this.checkLayout();
        return this.ascent;
    }
    
    public Rectangle getBounds() {
        this.checkLayout();
        this.computeRuns(null);
        int n = 0;
        if (this.wrapWidth != -1) {
            n = this.wrapWidth;
        }
        else {
            for (int i = 0; i < this.runs.length; ++i) {
                n = Math.max(n, this.lineWidth[i] + this.getLineIndent(i));
            }
        }
        return new Rectangle(0, 0, n, this.lineY[this.lineY.length - 1]);
    }
    
    public Rectangle getBounds(int n, int n2) {
        this.checkLayout();
        this.computeRuns(null);
        final int length = this.text.length();
        if (length == 0) {
            return new Rectangle(0, 0, 0, 0);
        }
        if (n > n2) {
            return new Rectangle(0, 0, 0, 0);
        }
        n = Math.min(Math.max(0, n), length - 1);
        n2 = Math.min(Math.max(0, n2), length - 1);
        n = this.translateOffset(n);
        n2 = this.translateOffset(n2);
        int min = Integer.MAX_VALUE;
        int max = 0;
        int min2 = Integer.MAX_VALUE;
        int max2 = 0;
        final boolean b = (this.orientation & 0x4000000) != 0x0;
        for (int i = 0; i < this.allRuns.length - 1; ++i) {
            final StyleItem styleItem = this.allRuns[i];
            final int n3 = styleItem.start + styleItem.length;
            if (n3 > n) {
                if (styleItem.start > n2) {
                    break;
                }
                int x = styleItem.x;
                int n4 = styleItem.x + styleItem.width;
                if (styleItem.start <= n && n < n3) {
                    int n5 = 0;
                    if (styleItem.style != null && styleItem.style.metrics != null) {
                        n5 = styleItem.style.metrics.width * (n - styleItem.start);
                    }
                    else if (!styleItem.tab) {
                        final int[] array = { 0 };
                        OS.ScriptCPtoX(n - styleItem.start, false, styleItem.length, styleItem.glyphCount, styleItem.clusters, styleItem.visAttrs, (styleItem.justify != 0) ? styleItem.justify : styleItem.advances, styleItem.analysis, array);
                        n5 = (b ? (styleItem.width - array[0]) : array[0]);
                    }
                    if (styleItem.analysis.fRTL ^ b) {
                        n4 = styleItem.x + n5;
                    }
                    else {
                        x = styleItem.x + n5;
                    }
                }
                if (styleItem.start <= n2 && n2 < n3) {
                    int width = styleItem.width;
                    if (styleItem.style != null && styleItem.style.metrics != null) {
                        width = styleItem.style.metrics.width * (n2 - styleItem.start + 1);
                    }
                    else if (!styleItem.tab) {
                        final int[] array2 = { 0 };
                        OS.ScriptCPtoX(n2 - styleItem.start, true, styleItem.length, styleItem.glyphCount, styleItem.clusters, styleItem.visAttrs, (styleItem.justify != 0) ? styleItem.justify : styleItem.advances, styleItem.analysis, array2);
                        width = (b ? (styleItem.width - array2[0]) : array2[0]);
                    }
                    if (styleItem.analysis.fRTL ^ b) {
                        x = styleItem.x + width;
                    }
                    else {
                        n4 = styleItem.x + width;
                    }
                }
                int n6;
                for (n6 = 0; n6 < this.runs.length && this.lineOffset[n6 + 1] <= styleItem.start; ++n6) {}
                min = Math.min(min, x);
                max = Math.max(max, n4);
                min2 = Math.min(min2, this.lineY[n6]);
                max2 = Math.max(max2, this.lineY[n6 + 1] - this.lineSpacing);
            }
        }
        return new Rectangle(min, min2, max - min, max2 - min2);
    }
    
    public int getDescent() {
        this.checkLayout();
        return this.descent;
    }
    
    public Font getFont() {
        this.checkLayout();
        return this.font;
    }
    
    public int getIndent() {
        this.checkLayout();
        return this.indent;
    }
    
    public boolean getJustify() {
        this.checkLayout();
        return this.justify;
    }
    
    int getItemFont(final StyleItem styleItem) {
        if (styleItem.fallbackFont != 0) {
            return styleItem.fallbackFont;
        }
        if (styleItem.style != null && styleItem.style.font != null) {
            return styleItem.style.font.handle;
        }
        if (this.font != null) {
            return this.font.handle;
        }
        return this.device.systemFont.handle;
    }
    
    public int getLevel(int translateOffset) {
        this.checkLayout();
        this.computeRuns(null);
        final int length = this.text.length();
        if (translateOffset < 0 || translateOffset > length) {
            SWT.error(6);
        }
        translateOffset = this.translateOffset(translateOffset);
        for (int i = 1; i < this.allRuns.length; ++i) {
            if (this.allRuns[i].start > translateOffset) {
                return this.allRuns[i - 1].analysis.s.uBidiLevel;
            }
        }
        return ((this.orientation & 0x4000000) != 0x0) ? 1 : 0;
    }
    
    public Rectangle getLineBounds(final int n) {
        this.checkLayout();
        this.computeRuns(null);
        if (n < 0 || n >= this.runs.length) {
            SWT.error(6);
        }
        final int lineIndent = this.getLineIndent(n);
        final int n2 = this.lineY[n];
        return new Rectangle(lineIndent, n2, this.lineWidth[n], this.lineY[n + 1] - n2 - this.lineSpacing);
    }
    
    public int getLineCount() {
        this.checkLayout();
        this.computeRuns(null);
        return this.runs.length;
    }
    
    int getLineIndent(final int n) {
        int n2 = this.wrapIndent;
        if (n == 0) {
            n2 = this.indent;
        }
        else {
            final StyleItem[] array = this.runs[n - 1];
            final StyleItem styleItem = array[array.length - 1];
            if (styleItem.lineBreak && !styleItem.softBreak) {
                n2 = this.indent;
            }
        }
        if (this.wrapWidth != -1) {
            boolean b = true;
            if (this.justify) {
                final StyleItem[] array2 = this.runs[n];
                if (array2[array2.length - 1].softBreak) {
                    b = false;
                }
            }
            if (b) {
                final int n3 = this.lineWidth[n] + n2;
                switch (this.alignment) {
                    case 16777216: {
                        n2 += (this.wrapWidth - n3) / 2;
                        break;
                    }
                    case 131072: {
                        n2 += this.wrapWidth - n3;
                        break;
                    }
                }
            }
        }
        return n2;
    }
    
    public int getLineIndex(int translateOffset) {
        this.checkLayout();
        this.computeRuns(null);
        final int length = this.text.length();
        if (translateOffset < 0 || translateOffset > length) {
            SWT.error(6);
        }
        translateOffset = this.translateOffset(translateOffset);
        for (int i = 0; i < this.runs.length; ++i) {
            if (this.lineOffset[i + 1] > translateOffset) {
                return i;
            }
        }
        return this.runs.length - 1;
    }
    
    public FontMetrics getLineMetrics(final int n) {
        this.checkLayout();
        this.computeRuns(null);
        if (n < 0 || n >= this.runs.length) {
            SWT.error(6);
        }
        final int internal_new_GC = this.device.internal_new_GC(null);
        final int createCompatibleDC = OS.CreateCompatibleDC(internal_new_GC);
        final TEXTMETRIC textmetric = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
        OS.SelectObject(createCompatibleDC, (this.font != null) ? this.font.handle : this.device.systemFont.handle);
        OS.GetTextMetrics(createCompatibleDC, textmetric);
        OS.DeleteDC(createCompatibleDC);
        this.device.internal_dispose_GC(internal_new_GC, null);
        int tmAscent = Math.max(textmetric.tmAscent, this.ascent);
        int tmDescent = Math.max(textmetric.tmDescent, this.descent);
        int tmInternalLeading = textmetric.tmInternalLeading;
        if (this.text.length() != 0) {
            final StyleItem[] array = this.runs[n];
            for (int i = 0; i < array.length; ++i) {
                final StyleItem styleItem = array[i];
                if (styleItem.ascent > tmAscent) {
                    tmAscent = styleItem.ascent;
                    tmInternalLeading = styleItem.leading;
                }
                tmDescent = Math.max(tmDescent, styleItem.descent);
            }
        }
        textmetric.tmAscent = tmAscent;
        textmetric.tmDescent = tmDescent;
        textmetric.tmHeight = tmAscent + tmDescent;
        textmetric.tmInternalLeading = tmInternalLeading;
        textmetric.tmAveCharWidth = 0;
        return FontMetrics.win32_new(textmetric);
    }
    
    public int[] getLineOffsets() {
        this.checkLayout();
        this.computeRuns(null);
        final int[] array = new int[this.lineOffset.length];
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.untranslateOffset(this.lineOffset[i]);
        }
        return array;
    }
    
    public Point getLocation(int translateOffset, final boolean b) {
        this.checkLayout();
        this.computeRuns(null);
        final int length = this.text.length();
        if (translateOffset < 0 || translateOffset > length) {
            SWT.error(6);
        }
        final int length2 = this.segmentsText.length();
        int n;
        for (translateOffset = this.translateOffset(translateOffset), n = 0; n < this.runs.length && this.lineOffset[n + 1] <= translateOffset; ++n) {}
        final int min = Math.min(n, this.runs.length - 1);
        if (translateOffset == length2) {
            return new Point(this.getLineIndent(min) + this.lineWidth[min], this.lineY[min]);
        }
        int n2 = -1;
        int length3 = this.allRuns.length;
        while (length3 - n2 > 1) {
            final int n3 = (length3 + n2) / 2;
            final StyleItem styleItem = this.allRuns[n3];
            if (styleItem.start > translateOffset) {
                length3 = n3;
            }
            else {
                if (styleItem.start + styleItem.length > translateOffset) {
                    int n4;
                    if (styleItem.style != null && styleItem.style.metrics != null) {
                        n4 = styleItem.style.metrics.width * (translateOffset - styleItem.start + (b ? 1 : 0));
                    }
                    else if (styleItem.tab) {
                        n4 = ((b || translateOffset == length2) ? styleItem.width : 0);
                    }
                    else {
                        final int n5 = translateOffset - styleItem.start;
                        final int length4 = styleItem.length;
                        final int glyphCount = styleItem.glyphCount;
                        final int[] array = { 0 };
                        OS.ScriptCPtoX(n5, b, length4, glyphCount, styleItem.clusters, styleItem.visAttrs, (styleItem.justify != 0) ? styleItem.justify : styleItem.advances, styleItem.analysis, array);
                        n4 = (((this.orientation & 0x4000000) != 0x0) ? (styleItem.width - array[0]) : array[0]);
                    }
                    return new Point(styleItem.x + n4, this.lineY[min]);
                }
                n2 = n3;
            }
        }
        return new Point(0, 0);
    }
    
    public int getNextOffset(final int n, final int n2) {
        this.checkLayout();
        return this._getOffset(n, n2, true);
    }
    
    int _getOffset(int n, final int n2, final boolean b) {
        this.computeRuns(null);
        final int length = this.text.length();
        if (n < 0 || n > length) {
            SWT.error(6);
        }
        if (b && n == length) {
            return length;
        }
        if (!b && n == 0) {
            return 0;
        }
        final int n3 = b ? 1 : -1;
        if ((n2 & 0x1) != 0x0) {
            return n + n3;
        }
        final int length2 = this.segmentsText.length();
        n = this.translateOffset(n);
        final SCRIPT_LOGATTR script_LOGATTR = new SCRIPT_LOGATTR();
        final SCRIPT_PROPERTIES script_PROPERTIES = new SCRIPT_PROPERTIES();
        int n4 = b ? 0 : (this.allRuns.length - 1);
        n = this.validadeOffset(n, n3);
        do {
            final StyleItem styleItem = this.allRuns[n4];
            if (styleItem.start <= n && n < styleItem.start + styleItem.length) {
                if (styleItem.lineBreak && !styleItem.softBreak) {
                    return this.untranslateOffset(styleItem.start);
                }
                if (styleItem.tab) {
                    return this.untranslateOffset(styleItem.start);
                }
                OS.MoveMemory(script_PROPERTIES, this.device.scripts[styleItem.analysis.eScript], SCRIPT_PROPERTIES.sizeof);
                final boolean b2 = script_PROPERTIES.fNeedsCaretInfo || script_PROPERTIES.fNeedsWordBreaking;
                if (b2) {
                    this.breakRun(styleItem);
                }
                while (styleItem.start <= n && n < styleItem.start + styleItem.length) {
                    if (b2) {
                        OS.MoveMemory(script_LOGATTR, styleItem.psla + (n - styleItem.start) * SCRIPT_LOGATTR.sizeof, SCRIPT_LOGATTR.sizeof);
                    }
                    switch (n2) {
                        case 2: {
                            if (!script_PROPERTIES.fNeedsCaretInfo) {
                                return this.untranslateOffset(n);
                            }
                            if (!script_LOGATTR.fInvalid && script_LOGATTR.fCharStop) {
                                return this.untranslateOffset(n);
                            }
                            break;
                        }
                        case 4:
                        case 16: {
                            if (script_PROPERTIES.fNeedsWordBreaking) {
                                if (!script_LOGATTR.fInvalid && script_LOGATTR.fWordStop) {
                                    return this.untranslateOffset(n);
                                }
                                break;
                            }
                            else {
                                if (n <= 0) {
                                    break;
                                }
                                final boolean letterOrDigit = Compatibility.isLetterOrDigit(this.segmentsText.charAt(n));
                                if ((letterOrDigit != Compatibility.isLetterOrDigit(this.segmentsText.charAt(n - 1)) || !letterOrDigit) && !Compatibility.isWhitespace(this.segmentsText.charAt(n))) {
                                    return this.untranslateOffset(n);
                                }
                                break;
                            }
                            break;
                        }
                        case 8: {
                            if (n <= 0) {
                                break;
                            }
                            final boolean letterOrDigit2 = Compatibility.isLetterOrDigit(this.segmentsText.charAt(n));
                            final boolean letterOrDigit3 = Compatibility.isLetterOrDigit(this.segmentsText.charAt(n - 1));
                            if (!letterOrDigit2 && letterOrDigit3) {
                                return this.untranslateOffset(n);
                            }
                            break;
                        }
                    }
                    n = this.validadeOffset(n, n3);
                }
            }
            n4 += n3;
        } while (n4 >= 0 && n4 < this.allRuns.length - 1 && n >= 0 && n < length2);
        return b ? this.text.length() : 0;
    }
    
    public int getOffset(final Point point, final int[] array) {
        this.checkLayout();
        if (point == null) {
            SWT.error(4);
        }
        return this.getOffset(point.x, point.y, array);
    }
    
    public int getOffset(int n, final int n2, final int[] array) {
        this.checkLayout();
        this.computeRuns(null);
        if (array != null && array.length < 1) {
            SWT.error(5);
        }
        int length;
        int n3;
        for (length = this.runs.length, n3 = 0; n3 < length && this.lineY[n3 + 1] <= n2; ++n3) {}
        final int min = Math.min(n3, this.runs.length - 1);
        final StyleItem[] array2 = this.runs[min];
        final int lineIndent = this.getLineIndent(min);
        if (n >= lineIndent + this.lineWidth[min]) {
            n = lineIndent + this.lineWidth[min] - 1;
        }
        if (n < lineIndent) {
            n = lineIndent;
        }
        int n4 = -1;
        int length2 = array2.length;
        while (length2 - n4 > 1) {
            final int n5 = (length2 + n4) / 2;
            final StyleItem styleItem = array2[n5];
            if (styleItem.x > n) {
                length2 = n5;
            }
            else if (styleItem.x + styleItem.width <= n) {
                n4 = n5;
            }
            else {
                if (styleItem.lineBreak && !styleItem.softBreak) {
                    return this.untranslateOffset(styleItem.start);
                }
                int n6 = n - styleItem.x;
                if (styleItem.style != null && styleItem.style.metrics != null) {
                    final GlyphMetrics metrics = styleItem.style.metrics;
                    if (metrics.width > 0) {
                        if (array != null) {
                            array[0] = ((n6 % metrics.width >= metrics.width / 2) ? 1 : 0);
                        }
                        return this.untranslateOffset(styleItem.start + n6 / metrics.width);
                    }
                }
                if (styleItem.tab) {
                    if (array != null) {
                        array[0] = ((n >= styleItem.x + styleItem.width / 2) ? 1 : 0);
                    }
                    return this.untranslateOffset(styleItem.start);
                }
                final int length3 = styleItem.length;
                final int glyphCount = styleItem.glyphCount;
                final int[] array3 = { 0 };
                final int[] array4 = { 0 };
                if ((this.orientation & 0x4000000) != 0x0) {
                    n6 = styleItem.width - n6;
                }
                OS.ScriptXtoCP(n6, length3, glyphCount, styleItem.clusters, styleItem.visAttrs, (styleItem.justify != 0) ? styleItem.justify : styleItem.advances, styleItem.analysis, array3, array4);
                if (array != null) {
                    array[0] = array4[0];
                }
                return this.untranslateOffset(styleItem.start + array3[0]);
            }
        }
        if (array != null) {
            array[0] = 0;
        }
        if (array2.length == 1) {
            final StyleItem styleItem2 = array2[0];
            if (styleItem2.lineBreak && !styleItem2.softBreak) {
                return this.untranslateOffset(styleItem2.start);
            }
        }
        return this.untranslateOffset(this.lineOffset[min + 1]);
    }
    
    public int getOrientation() {
        this.checkLayout();
        return this.orientation;
    }
    
    void getPartialSelection(final StyleItem styleItem, final int n, final int n2, final RECT rect) {
        final int n3 = styleItem.start + styleItem.length - 1;
        final int n4 = Math.max(n, styleItem.start) - styleItem.start;
        final int n5 = Math.min(n2, n3) - styleItem.start;
        final int length = styleItem.length;
        final int glyphCount = styleItem.glyphCount;
        final int[] array = { 0 };
        final int left = rect.left;
        final int n6 = (styleItem.justify != 0) ? styleItem.justify : styleItem.advances;
        OS.ScriptCPtoX(n4, false, length, glyphCount, styleItem.clusters, styleItem.visAttrs, n6, styleItem.analysis, array);
        rect.left = left + (((this.orientation & 0x4000000) != 0x0) ? (styleItem.width - array[0]) : array[0]);
        OS.ScriptCPtoX(n5, true, length, glyphCount, styleItem.clusters, styleItem.visAttrs, n6, styleItem.analysis, array);
        rect.right = left + (((this.orientation & 0x4000000) != 0x0) ? (styleItem.width - array[0]) : array[0]);
    }
    
    public int getPreviousOffset(final int n, final int n2) {
        this.checkLayout();
        return this._getOffset(n, n2, false);
    }
    
    public int[] getRanges() {
        this.checkLayout();
        int[] array = new int[this.stylesCount * 2];
        int n = 0;
        for (int i = 0; i < this.stylesCount - 1; ++i) {
            if (this.styles[i].style != null) {
                array[n++] = this.styles[i].start;
                array[n++] = this.styles[i + 1].start - 1;
            }
        }
        if (n != array.length) {
            final int[] array2 = new int[n];
            System.arraycopy(array, 0, array2, 0, n);
            array = array2;
        }
        return array;
    }
    
    public int[] getSegments() {
        this.checkLayout();
        return this.segments;
    }
    
    public char[] getSegmentsChars() {
        this.checkLayout();
        return this.segmentsChars;
    }
    
    String getSegmentsText() {
        final int length = this.text.length();
        if (length == 0) {
            return this.text;
        }
        if (this.segments == null) {
            return this.text;
        }
        final int length2 = this.segments.length;
        if (length2 == 0) {
            return this.text;
        }
        if (this.segmentsChars == null) {
            if (length2 == 1) {
                return this.text;
            }
            if (length2 == 2 && this.segments[0] == 0 && this.segments[1] == length) {
                return this.text;
            }
        }
        final char[] array = new char[length];
        this.text.getChars(0, length, array, 0);
        final char[] array2 = new char[length + length2];
        int i = 0;
        int j = 0;
        final char c = (this.orientation == 67108864) ? '\u200f' : '\u200e';
        while (i < length) {
            if (j < length2 && i == this.segments[j]) {
                array2[i + j++] = ((this.segmentsChars != null && this.segmentsChars.length > j) ? this.segmentsChars[j] : c);
            }
            else {
                array2[i + j] = array[i++];
            }
        }
        while (j < length2) {
            this.segments[j] = i;
            array2[i + j++] = ((this.segmentsChars != null && this.segmentsChars.length > j) ? this.segmentsChars[j] : c);
        }
        return new String(array2, 0, array2.length);
    }
    
    public int getSpacing() {
        this.checkLayout();
        return this.lineSpacing;
    }
    
    public TextStyle getStyle(final int n) {
        this.checkLayout();
        final int length = this.text.length();
        if (n < 0 || n >= length) {
            SWT.error(6);
        }
        for (int i = 1; i < this.stylesCount; ++i) {
            if (this.styles[i].start > n) {
                return this.styles[i - 1].style;
            }
        }
        return null;
    }
    
    public TextStyle[] getStyles() {
        this.checkLayout();
        TextStyle[] array = new TextStyle[this.stylesCount];
        int n = 0;
        for (int i = 0; i < this.stylesCount; ++i) {
            if (this.styles[i].style != null) {
                array[n++] = this.styles[i].style;
            }
        }
        if (n != array.length) {
            final TextStyle[] array2 = new TextStyle[n];
            System.arraycopy(array, 0, array2, 0, n);
            array = array2;
        }
        return array;
    }
    
    public int[] getTabs() {
        this.checkLayout();
        return this.tabs;
    }
    
    public String getText() {
        this.checkLayout();
        return this.text;
    }
    
    public int getWidth() {
        this.checkLayout();
        return this.wrapWidth;
    }
    
    public int getWrapIndent() {
        this.checkLayout();
        return this.wrapIndent;
    }
    
    public boolean isDisposed() {
        return this.device == null;
    }
    
    StyleItem[] itemize() {
        this.segmentsText = this.getSegmentsText();
        final int length = this.segmentsText.length();
        final SCRIPT_CONTROL script_CONTROL = new SCRIPT_CONTROL();
        final SCRIPT_STATE script_STATE = new SCRIPT_STATE();
        final int n = length + 1;
        if ((this.orientation & 0x4000000) != 0x0) {
            script_STATE.uBidiLevel = 1;
            script_STATE.fArabicNumContext = true;
        }
        OS.ScriptApplyDigitSubstitution(null, script_CONTROL, script_STATE);
        final int getProcessHeap = OS.GetProcessHeap();
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n * SCRIPT_ITEM.sizeof);
        if (heapAlloc == 0) {
            SWT.error(2);
        }
        final int[] array = { 0 };
        final char[] array2 = new char[length];
        this.segmentsText.getChars(0, length, array2, 0);
        OS.ScriptItemize(array2, length, n, script_CONTROL, script_STATE, heapAlloc, array);
        final StyleItem[] merge = this.merge(heapAlloc, array[0]);
        OS.HeapFree(getProcessHeap, 0, heapAlloc);
        return merge;
    }
    
    StyleItem[] merge(final int n, final int n2) {
        if (this.styles.length > this.stylesCount) {
            final StyleItem[] styles = new StyleItem[this.stylesCount];
            System.arraycopy(this.styles, 0, styles, 0, this.stylesCount);
            this.styles = styles;
        }
        int n3 = 0;
        int i = 0;
        final int length = this.segmentsText.length();
        int n4 = 0;
        int n5 = 0;
        final StyleItem[] array = new StyleItem[n2 + this.stylesCount];
        final SCRIPT_ITEM script_ITEM = new SCRIPT_ITEM();
        int n6 = -1;
        int n7 = 0;
        int n8 = 0;
        final boolean b = n2 > 1024;
        final SCRIPT_PROPERTIES script_PROPERTIES = new SCRIPT_PROPERTIES();
        while (i < length) {
            final StyleItem styleItem = new StyleItem();
            styleItem.start = i;
            styleItem.style = this.styles[n5].style;
            array[n3++] = styleItem;
            OS.MoveMemory(script_ITEM, n + n4 * SCRIPT_ITEM.sizeof, SCRIPT_ITEM.sizeof);
            styleItem.analysis = script_ITEM.a;
            script_ITEM.a = new SCRIPT_ANALYSIS();
            if (n8 != 0) {
                styleItem.analysis.fLinkBefore = true;
                n8 = 0;
            }
            final char char1 = this.segmentsText.charAt(i);
            switch (char1) {
                case 10:
                case 13: {
                    styleItem.lineBreak = true;
                    break;
                }
                case 9: {
                    styleItem.tab = true;
                    break;
                }
            }
            if (n6 == -1) {
                n7 = n4 + 1;
                OS.MoveMemory(script_ITEM, n + n7 * SCRIPT_ITEM.sizeof, SCRIPT_ITEM.sizeof);
                n6 = script_ITEM.iCharPos;
                if (n7 < n2 && char1 == '\r' && this.segmentsText.charAt(n6) == '\n') {
                    n7 = n4 + 2;
                    OS.MoveMemory(script_ITEM, n + n7 * SCRIPT_ITEM.sizeof, SCRIPT_ITEM.sizeof);
                    n6 = script_ITEM.iCharPos;
                }
                if (n7 < n2 && b && !styleItem.lineBreak) {
                    OS.MoveMemory(script_PROPERTIES, this.device.scripts[styleItem.analysis.eScript], SCRIPT_PROPERTIES.sizeof);
                    if (!script_PROPERTIES.fComplex || styleItem.tab) {
                        for (int j = 0; j < 512; ++j) {
                            if (n7 == n2) {
                                break;
                            }
                            final char char2 = this.segmentsText.charAt(n6);
                            if (char2 == '\n') {
                                break;
                            }
                            if (char2 == '\r') {
                                break;
                            }
                            if (char2 == '\t' != styleItem.tab) {
                                break;
                            }
                            OS.MoveMemory(script_PROPERTIES, this.device.scripts[script_ITEM.a.eScript], SCRIPT_PROPERTIES.sizeof);
                            if (!styleItem.tab && script_PROPERTIES.fComplex) {
                                break;
                            }
                            ++n7;
                            OS.MoveMemory(script_ITEM, n + n7 * SCRIPT_ITEM.sizeof, SCRIPT_ITEM.sizeof);
                            n6 = script_ITEM.iCharPos;
                        }
                    }
                }
            }
            final int translateOffset = this.translateOffset(this.styles[n5 + 1].start);
            if (translateOffset <= n6) {
                ++n5;
                i = translateOffset;
                if (i < n6 && i > 0 && i < length) {
                    final char char3 = this.segmentsText.charAt(i - 1);
                    final char char4 = this.segmentsText.charAt(i);
                    if (Compatibility.isLetter(char3) && Compatibility.isLetter(char4)) {
                        styleItem.analysis.fLinkAfter = true;
                        n8 = 1;
                    }
                }
            }
            if (n6 <= translateOffset) {
                n4 = n7;
                i = n6;
                n6 = -1;
            }
            styleItem.length = i - styleItem.start;
        }
        final StyleItem styleItem2 = new StyleItem();
        styleItem2.start = length;
        OS.MoveMemory(script_ITEM, n + n2 * SCRIPT_ITEM.sizeof, SCRIPT_ITEM.sizeof);
        styleItem2.analysis = script_ITEM.a;
        array[n3++] = styleItem2;
        if (array.length != n3) {
            final StyleItem[] array2 = new StyleItem[n3];
            System.arraycopy(array, 0, array2, 0, n3);
            return array2;
        }
        return array;
    }
    
    StyleItem[] reorder(final StyleItem[] array, final boolean b) {
        int length = array.length;
        if (length <= 1) {
            return array;
        }
        final byte[] array2 = new byte[length];
        for (int i = 0; i < length; ++i) {
            array2[i] = (byte)(array[i].analysis.s.uBidiLevel & 0x1F);
        }
        final StyleItem styleItem = array[length - 1];
        if (styleItem.lineBreak && !styleItem.softBreak) {
            array2[length - 1] = 0;
        }
        final int[] array3 = new int[length];
        OS.ScriptLayout(length, array2, null, array3);
        final StyleItem[] array4 = new StyleItem[length];
        for (int j = 0; j < length; ++j) {
            array4[array3[j]] = array[j];
        }
        if ((this.orientation & 0x4000000) != 0x0) {
            if (b) {
                --length;
            }
            for (int k = 0; k < length / 2; ++k) {
                final StyleItem styleItem2 = array4[k];
                array4[k] = array4[length - k - 1];
                array4[length - k - 1] = styleItem2;
            }
        }
        return array4;
    }
    
    public void setAlignment(int alignment) {
        this.checkLayout();
        alignment &= 0x1024000;
        if (alignment == 0) {
            return;
        }
        if ((alignment & 0x4000) != 0x0) {
            alignment = 16384;
        }
        if ((alignment & 0x20000) != 0x0) {
            alignment = 131072;
        }
        if (this.alignment == alignment) {
            return;
        }
        this.freeRuns();
        this.alignment = alignment;
    }
    
    public void setAscent(final int ascent) {
        this.checkLayout();
        if (ascent < -1) {
            SWT.error(5);
        }
        if (this.ascent == ascent) {
            return;
        }
        this.freeRuns();
        this.ascent = ascent;
    }
    
    public void setDescent(final int descent) {
        this.checkLayout();
        if (descent < -1) {
            SWT.error(5);
        }
        if (this.descent == descent) {
            return;
        }
        this.freeRuns();
        this.descent = descent;
    }
    
    public void setFont(final Font font) {
        this.checkLayout();
        if (font != null && font.isDisposed()) {
            SWT.error(5);
        }
        final Font font2 = this.font;
        if (font2 == font) {
            return;
        }
        this.font = font;
        if (font2 != null && font2.equals(font)) {
            return;
        }
        this.freeRuns();
    }
    
    public void setIndent(final int indent) {
        this.checkLayout();
        if (indent < 0) {
            return;
        }
        if (this.indent == indent) {
            return;
        }
        this.freeRuns();
        this.indent = indent;
    }
    
    public void setJustify(final boolean justify) {
        this.checkLayout();
        if (this.justify == justify) {
            return;
        }
        this.freeRuns();
        this.justify = justify;
    }
    
    public void setOrientation(int orientation) {
        this.checkLayout();
        orientation &= 0x6000000;
        if (orientation == 0) {
            return;
        }
        if ((orientation & 0x2000000) != 0x0) {
            orientation = 33554432;
        }
        if (this.orientation == orientation) {
            return;
        }
        this.orientation = orientation;
        this.freeRuns();
    }
    
    public void setSegments(final int[] segments) {
        this.checkLayout();
        if (this.segments == null && segments == null) {
            return;
        }
        if (this.segments != null && segments != null && this.segments.length == segments.length) {
            int n;
            for (n = 0; n < segments.length && this.segments[n] == segments[n]; ++n) {}
            if (n == segments.length) {
                return;
            }
        }
        this.freeRuns();
        this.segments = segments;
    }
    
    public void setSegmentsChars(final char[] segmentsChars) {
        this.checkLayout();
        if (this.segmentsChars == null && segmentsChars == null) {
            return;
        }
        if (this.segmentsChars != null && segmentsChars != null && this.segmentsChars.length == segmentsChars.length) {
            int n;
            for (n = 0; n < segmentsChars.length && this.segmentsChars[n] == segmentsChars[n]; ++n) {}
            if (n == segmentsChars.length) {
                return;
            }
        }
        this.freeRuns();
        this.segmentsChars = segmentsChars;
    }
    
    public void setSpacing(final int lineSpacing) {
        this.checkLayout();
        if (lineSpacing < 0) {
            SWT.error(5);
        }
        if (this.lineSpacing == lineSpacing) {
            return;
        }
        this.freeRuns();
        this.lineSpacing = lineSpacing;
    }
    
    public void setStyle(final TextStyle style, int min, int min2) {
        this.checkLayout();
        final int length = this.text.length();
        if (length == 0) {
            return;
        }
        if (min > min2) {
            return;
        }
        min = Math.min(Math.max(0, min), length - 1);
        min2 = Math.min(Math.max(0, min2), length - 1);
        int n = -1;
        int stylesCount = this.stylesCount;
        while (stylesCount - n > 1) {
            final int n2 = (stylesCount + n) / 2;
            if (this.styles[n2 + 1].start > min) {
                stylesCount = n2;
            }
            else {
                n = n2;
            }
        }
        if (stylesCount >= 0 && stylesCount < this.stylesCount) {
            final StyleItem styleItem = this.styles[stylesCount];
            if (styleItem.start == min && this.styles[stylesCount + 1].start - 1 == min2) {
                if (style == null) {
                    if (styleItem.style == null) {
                        return;
                    }
                }
                else if (style.equals(styleItem.style)) {
                    return;
                }
            }
        }
        this.freeRuns();
        int n4;
        int n3;
        for (n3 = (n4 = stylesCount); n4 < this.stylesCount && this.styles[n4 + 1].start <= min2; ++n4) {}
        if (n3 == n4) {
            final int start = this.styles[n3].start;
            final int n5 = this.styles[n4 + 1].start - 1;
            if (start == min && n5 == min2) {
                this.styles[n3].style = style;
                return;
            }
            if (start != min && n5 != min2) {
                final int stylesCount2 = this.stylesCount + 2;
                if (stylesCount2 > this.styles.length) {
                    final StyleItem[] styles = new StyleItem[Math.min(stylesCount2 + 1024, Math.max(64, stylesCount2 * 2))];
                    System.arraycopy(this.styles, 0, styles, 0, this.stylesCount);
                    this.styles = styles;
                }
                System.arraycopy(this.styles, n4 + 1, this.styles, n4 + 3, this.stylesCount - n4 - 1);
                final StyleItem styleItem2 = new StyleItem();
                styleItem2.start = min;
                styleItem2.style = style;
                this.styles[n3 + 1] = styleItem2;
                final StyleItem styleItem3 = new StyleItem();
                styleItem3.start = min2 + 1;
                styleItem3.style = this.styles[n3].style;
                this.styles[n3 + 2] = styleItem3;
                this.stylesCount = stylesCount2;
                return;
            }
        }
        if (min == this.styles[n3].start) {
            --n3;
        }
        if (min2 == this.styles[n4 + 1].start - 1) {
            ++n4;
        }
        final int stylesCount3 = this.stylesCount + 1 - (n4 - n3 - 1);
        if (stylesCount3 > this.styles.length) {
            final StyleItem[] styles2 = new StyleItem[Math.min(stylesCount3 + 1024, Math.max(64, stylesCount3 * 2))];
            System.arraycopy(this.styles, 0, styles2, 0, this.stylesCount);
            this.styles = styles2;
        }
        System.arraycopy(this.styles, n4, this.styles, n3 + 2, this.stylesCount - n4);
        final StyleItem styleItem4 = new StyleItem();
        styleItem4.start = min;
        styleItem4.style = style;
        this.styles[n3 + 1] = styleItem4;
        this.styles[n3 + 2].start = min2 + 1;
        this.stylesCount = stylesCount3;
    }
    
    public void setTabs(final int[] tabs) {
        this.checkLayout();
        if (this.tabs == null && tabs == null) {
            return;
        }
        if (this.tabs != null && tabs != null && this.tabs.length == tabs.length) {
            int n;
            for (n = 0; n < tabs.length && this.tabs[n] == tabs[n]; ++n) {}
            if (n == tabs.length) {
                return;
            }
        }
        this.freeRuns();
        this.tabs = tabs;
    }
    
    public void setText(final String text) {
        this.checkLayout();
        if (text == null) {
            SWT.error(4);
        }
        if (text.equals(this.text)) {
            return;
        }
        this.freeRuns();
        this.text = text;
        (this.styles = new StyleItem[2])[0] = new StyleItem();
        this.styles[1] = new StyleItem();
        this.styles[1].start = text.length();
        this.stylesCount = 2;
    }
    
    public void setWidth(final int wrapWidth) {
        this.checkLayout();
        if (wrapWidth < -1 || wrapWidth == 0) {
            SWT.error(5);
        }
        if (this.wrapWidth == wrapWidth) {
            return;
        }
        this.freeRuns();
        this.wrapWidth = wrapWidth;
    }
    
    public void setWrapIndent(final int wrapIndent) {
        this.checkLayout();
        if (wrapIndent < 0) {
            return;
        }
        if (this.wrapIndent == wrapIndent) {
            return;
        }
        this.freeRuns();
        this.wrapIndent = wrapIndent;
    }
    
    boolean shape(final int n, final StyleItem styleItem, final char[] array, final int[] array2, final int n2, final SCRIPT_PROPERTIES script_PROPERTIES) {
        final boolean b = !script_PROPERTIES.fComplex && !styleItem.analysis.fNoGlyphIndex;
        if (b && OS.ScriptGetCMap(n, styleItem.psc, array, array.length, 0, new short[array.length]) != 0) {
            if (styleItem.psc != 0) {
                OS.ScriptFreeCache(styleItem.psc);
                array2[0] = 0;
                OS.MoveMemory(styleItem.psc, new int[1], OS.PTR_SIZEOF);
            }
            return false;
        }
        final int scriptShape = OS.ScriptShape(n, styleItem.psc, array, array.length, n2, styleItem.analysis, styleItem.glyphs, styleItem.clusters, styleItem.visAttrs, array2);
        styleItem.glyphCount = array2[0];
        if (b) {
            return true;
        }
        if (scriptShape != -2147220992) {
            if (styleItem.analysis.fNoGlyphIndex) {
                return true;
            }
            final SCRIPT_FONTPROPERTIES script_FONTPROPERTIES = new SCRIPT_FONTPROPERTIES();
            script_FONTPROPERTIES.cBytes = SCRIPT_FONTPROPERTIES.sizeof;
            OS.ScriptGetFontProperties(n, styleItem.psc, script_FONTPROPERTIES);
            final short[] array3 = new short[array2[0]];
            OS.MoveMemory(array3, styleItem.glyphs, array3.length * 2);
            int n3;
            for (n3 = 0; n3 < array3.length && array3[n3] != script_FONTPROPERTIES.wgDefault; ++n3) {}
            if (n3 == array3.length) {
                return true;
            }
        }
        if (styleItem.psc != 0) {
            OS.ScriptFreeCache(styleItem.psc);
            array2[0] = 0;
            OS.MoveMemory(styleItem.psc, new int[1], OS.PTR_SIZEOF);
        }
        styleItem.glyphCount = 0;
        return false;
    }
    
    void shape(final int n, final StyleItem styleItem) {
        if (styleItem.tab || styleItem.lineBreak) {
            return;
        }
        if (styleItem.glyphs != 0) {
            return;
        }
        final int[] array = { 0 };
        final char[] array2 = new char[styleItem.length];
        this.segmentsText.getChars(styleItem.start, styleItem.start + styleItem.length, array2, 0);
        final int n2 = array2.length * 3 / 2 + 16;
        final int getProcessHeap = OS.GetProcessHeap();
        styleItem.glyphs = OS.HeapAlloc(getProcessHeap, 8, n2 * 2);
        if (styleItem.glyphs == 0) {
            SWT.error(2);
        }
        styleItem.clusters = OS.HeapAlloc(getProcessHeap, 8, n2 * 2);
        if (styleItem.clusters == 0) {
            SWT.error(2);
        }
        styleItem.visAttrs = OS.HeapAlloc(getProcessHeap, 8, n2 * 2);
        if (styleItem.visAttrs == 0) {
            SWT.error(2);
        }
        styleItem.psc = OS.HeapAlloc(getProcessHeap, 8, OS.PTR_SIZEOF);
        if (styleItem.psc == 0) {
            SWT.error(2);
        }
        final short eScript = styleItem.analysis.eScript;
        final SCRIPT_PROPERTIES script_PROPERTIES = new SCRIPT_PROPERTIES();
        OS.MoveMemory(script_PROPERTIES, this.device.scripts[eScript], SCRIPT_PROPERTIES.sizeof);
        boolean b = this.shape(n, styleItem, array2, array, n2, script_PROPERTIES);
        if (!b && script_PROPERTIES.fPrivateUseArea) {
            styleItem.analysis.fNoGlyphIndex = true;
            b = this.shape(n, styleItem, array2, array, n2, script_PROPERTIES);
        }
        if (!b) {
            final int getCurrentObject = OS.GetCurrentObject(n, 6);
            int n3 = 0;
            final char[] array3 = new char[Math.min(array2.length, 2)];
            final SCRIPT_LOGATTR script_LOGATTR = new SCRIPT_LOGATTR();
            this.breakRun(styleItem);
            int n4 = 0;
            for (int i = 0; i < array2.length; ++i) {
                OS.MoveMemory(script_LOGATTR, styleItem.psla + i * SCRIPT_LOGATTR.sizeof, SCRIPT_LOGATTR.sizeof);
                if (!script_LOGATTR.fWhiteSpace) {
                    array3[n4++] = array2[i];
                    if (n4 == array3.length) {
                        break;
                    }
                }
            }
            if (n4 > 0) {
                final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, OS.SCRIPT_STRING_ANALYSIS_sizeof());
                final int createEnhMetaFile = OS.CreateEnhMetaFile(n, null, null, null);
                final int selectObject = OS.SelectObject(createEnhMetaFile, getCurrentObject);
                if (OS.ScriptStringAnalyse(createEnhMetaFile, array3, n4, 0, -1, 6304, 0, null, null, 0, 0, 0, heapAlloc) == 0) {
                    OS.ScriptStringOut(heapAlloc, 0, 0, 0, null, 0, 0, false);
                    OS.ScriptStringFree(heapAlloc);
                }
                OS.HeapFree(getProcessHeap, 0, heapAlloc);
                OS.SelectObject(createEnhMetaFile, selectObject);
                final int closeEnhMetaFile = OS.CloseEnhMetaFile(createEnhMetaFile);
                final EMREXTCREATEFONTINDIRECTW emrextcreatefontindirectw = new EMREXTCREATEFONTINDIRECTW();
                final TextLayout$1.MetaFileEnumProc metaFileEnumProc = new TextLayout$1.MetaFileEnumProc(this, emrextcreatefontindirectw);
                if (false) {
                    metaFileEnumProc.metaFileEnumProc(0, 0, 0, 0, 0);
                }
                final Callback callback = new Callback(metaFileEnumProc, "metaFileEnumProc", 5);
                final int address = callback.getAddress();
                if (address == 0) {
                    SWT.error(3);
                }
                OS.EnumEnhMetaFile(0, closeEnhMetaFile, address, 0, null);
                OS.DeleteEnhMetaFile(closeEnhMetaFile);
                callback.dispose();
                n3 = OS.CreateFontIndirectW(emrextcreatefontindirectw.elfw.elfLogFont);
            }
            else {
                int j = 0;
                while (j < this.allRuns.length - 1) {
                    if (this.allRuns[j] == styleItem) {
                        if (j > 0) {
                            final StyleItem styleItem2 = this.allRuns[j - 1];
                            if (styleItem2.fallbackFont != 0 && styleItem2.analysis.eScript == styleItem.analysis.eScript) {
                                final LOGFONT logfont = OS.IsUnicode ? new LOGFONTW() : new LOGFONTA();
                                OS.GetObject(styleItem2.fallbackFont, LOGFONT.sizeof, logfont);
                                n3 = OS.CreateFontIndirect(logfont);
                            }
                        }
                        if (n3 != 0 || j + 1 >= this.allRuns.length - 1) {
                            break;
                        }
                        final StyleItem styleItem3 = this.allRuns[j + 1];
                        if (styleItem3.analysis.eScript != styleItem.analysis.eScript) {
                            break;
                        }
                        this.shape(n, styleItem3);
                        if (styleItem3.fallbackFont != 0) {
                            final LOGFONT logfont2 = OS.IsUnicode ? new LOGFONTW() : new LOGFONTA();
                            OS.GetObject(styleItem3.fallbackFont, LOGFONT.sizeof, logfont2);
                            n3 = OS.CreateFontIndirect(logfont2);
                            break;
                        }
                        break;
                    }
                    else {
                        ++j;
                    }
                }
            }
            if (n3 != 0) {
                OS.SelectObject(n, n3);
                if (b = this.shape(n, styleItem, array2, array, n2, script_PROPERTIES)) {
                    styleItem.fallbackFont = n3;
                }
            }
            if (!b && !script_PROPERTIES.fComplex) {
                styleItem.analysis.fNoGlyphIndex = true;
                if (b = this.shape(n, styleItem, array2, array, n2, script_PROPERTIES)) {
                    styleItem.fallbackFont = n3;
                }
                else {
                    styleItem.analysis.fNoGlyphIndex = false;
                }
            }
            if (!b && this.mLangFontLink2 != 0) {
                final int[] array4 = { 0 };
                final int[] array5 = { 0 };
                OS.VtblCall(4, this.mLangFontLink2, array2, array2.length, 0, array5, new int[1]);
                if (OS.VtblCall(10, this.mLangFontLink2, n, array5[0], array2[0], array4) == 0) {
                    final LOGFONT logfont3 = OS.IsUnicode ? new LOGFONTW() : new LOGFONTA();
                    OS.GetObject(array4[0], LOGFONT.sizeof, logfont3);
                    OS.VtblCall(8, this.mLangFontLink2, array4[0]);
                    final int createFontIndirect = OS.CreateFontIndirect(logfont3);
                    final int selectObject2 = OS.SelectObject(n, createFontIndirect);
                    if (b = this.shape(n, styleItem, array2, array, n2, script_PROPERTIES)) {
                        styleItem.fallbackFont = createFontIndirect;
                    }
                    else {
                        OS.SelectObject(n, selectObject2);
                        OS.DeleteObject(createFontIndirect);
                    }
                }
            }
            if (!b) {
                OS.SelectObject(n, getCurrentObject);
            }
            if (n3 != 0 && n3 != styleItem.fallbackFont) {
                OS.DeleteObject(n3);
            }
        }
        if (!b) {
            OS.ScriptShape(n, styleItem.psc, array2, array2.length, n2, styleItem.analysis, styleItem.glyphs, styleItem.clusters, styleItem.visAttrs, array);
            styleItem.glyphCount = array[0];
        }
        final int[] array6 = new int[3];
        styleItem.advances = OS.HeapAlloc(getProcessHeap, 8, styleItem.glyphCount * 4);
        if (styleItem.advances == 0) {
            SWT.error(2);
        }
        styleItem.goffsets = OS.HeapAlloc(getProcessHeap, 8, styleItem.glyphCount * 8);
        if (styleItem.goffsets == 0) {
            SWT.error(2);
        }
        OS.ScriptPlace(n, styleItem.psc, styleItem.glyphs, styleItem.glyphCount, styleItem.visAttrs, styleItem.analysis, styleItem.advances, styleItem.goffsets, array6);
        styleItem.width = array6[0] + array6[1] + array6[2];
        final TextStyle style = styleItem.style;
        if (style != null) {
            OUTLINETEXTMETRIC outlinetextmetric = null;
            if (style.underline || style.strikeout) {
                outlinetextmetric = (OS.IsUnicode ? new OUTLINETEXTMETRICW() : new OUTLINETEXTMETRICA());
                if (OS.GetOutlineTextMetrics(n, OUTLINETEXTMETRIC.sizeof, outlinetextmetric) == 0) {
                    outlinetextmetric = null;
                }
            }
            if (style.metrics != null) {
                final GlyphMetrics metrics = style.metrics;
                styleItem.width = metrics.width * Math.max(1, styleItem.glyphCount);
                styleItem.ascent = metrics.ascent;
                styleItem.descent = metrics.descent;
                styleItem.leading = 0;
            }
            else {
                TEXTMETRIC textmetric;
                if (outlinetextmetric != null) {
                    textmetric = (OS.IsUnicode ? ((OUTLINETEXTMETRICW)outlinetextmetric).otmTextMetrics : ((OUTLINETEXTMETRICA)outlinetextmetric).otmTextMetrics);
                }
                else {
                    textmetric = (OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA());
                    OS.GetTextMetrics(n, textmetric);
                }
                styleItem.ascent = textmetric.tmAscent;
                styleItem.descent = textmetric.tmDescent;
                styleItem.leading = textmetric.tmInternalLeading;
            }
            if (outlinetextmetric != null) {
                styleItem.underlinePos = outlinetextmetric.otmsUnderscorePosition;
                styleItem.underlineThickness = Math.max(1, outlinetextmetric.otmsUnderscoreSize);
                styleItem.strikeoutPos = outlinetextmetric.otmsStrikeoutPosition;
                styleItem.strikeoutThickness = Math.max(1, outlinetextmetric.otmsStrikeoutSize);
            }
            else {
                styleItem.underlinePos = 1;
                styleItem.underlineThickness = 1;
                styleItem.strikeoutPos = styleItem.ascent / 2;
                styleItem.strikeoutThickness = 1;
            }
            styleItem.ascent += style.rise;
            styleItem.descent -= style.rise;
        }
        else {
            final TEXTMETRIC textmetric2 = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
            OS.GetTextMetrics(n, textmetric2);
            styleItem.ascent = textmetric2.tmAscent;
            styleItem.descent = textmetric2.tmDescent;
            styleItem.leading = textmetric2.tmInternalLeading;
        }
    }
    
    int validadeOffset(int untranslateOffset, final int n) {
        untranslateOffset = this.untranslateOffset(untranslateOffset);
        return this.translateOffset(untranslateOffset + n);
    }
    
    public String toString() {
        if (this.isDisposed()) {
            return "TextLayout {*DISPOSED*}";
        }
        return "TextLayout {}";
    }
    
    int translateOffset(int n) {
        final int length = this.text.length();
        if (length == 0) {
            return n;
        }
        if (this.segments == null) {
            return n;
        }
        final int length2 = this.segments.length;
        if (length2 == 0) {
            return n;
        }
        if (this.segmentsChars == null) {
            if (length2 == 1) {
                return n;
            }
            if (length2 == 2 && this.segments[0] == 0 && this.segments[1] == length) {
                return n;
            }
        }
        for (int n2 = 0; n2 < length2 && n - n2 >= this.segments[n2]; ++n, ++n2) {}
        return n;
    }
    
    int untranslateOffset(int n) {
        final int length = this.text.length();
        if (length == 0) {
            return n;
        }
        if (this.segments == null) {
            return n;
        }
        final int length2 = this.segments.length;
        if (length2 == 0) {
            return n;
        }
        if (this.segmentsChars == null) {
            if (length2 == 1) {
                return n;
            }
            if (length2 == 2 && this.segments[0] == 0 && this.segments[1] == length) {
                return n;
            }
        }
        for (int n2 = 0; n2 < length2 && n > this.segments[n2]; --n, ++n2) {}
        return n;
    }
    
    class StyleItem
    {
        TextStyle style;
        int start;
        int length;
        boolean lineBreak;
        boolean softBreak;
        boolean tab;
        SCRIPT_ANALYSIS analysis;
        int psc;
        int glyphs;
        int glyphCount;
        int clusters;
        int visAttrs;
        int advances;
        int goffsets;
        int width;
        int ascent;
        int descent;
        int leading;
        int x;
        int underlinePos;
        int underlineThickness;
        int strikeoutPos;
        int strikeoutThickness;
        int justify;
        int psla;
        int fallbackFont;
        
        StyleItem() {
            this.psc = 0;
        }
        
        void free() {
            final int getProcessHeap = OS.GetProcessHeap();
            if (this.psc != 0) {
                OS.ScriptFreeCache(this.psc);
                OS.HeapFree(getProcessHeap, 0, this.psc);
                this.psc = 0;
            }
            if (this.glyphs != 0) {
                OS.HeapFree(getProcessHeap, 0, this.glyphs);
                this.glyphs = 0;
                this.glyphCount = 0;
            }
            if (this.clusters != 0) {
                OS.HeapFree(getProcessHeap, 0, this.clusters);
                this.clusters = 0;
            }
            if (this.visAttrs != 0) {
                OS.HeapFree(getProcessHeap, 0, this.visAttrs);
                this.visAttrs = 0;
            }
            if (this.advances != 0) {
                OS.HeapFree(getProcessHeap, 0, this.advances);
                this.advances = 0;
            }
            if (this.goffsets != 0) {
                OS.HeapFree(getProcessHeap, 0, this.goffsets);
                this.goffsets = 0;
            }
            if (this.justify != 0) {
                OS.HeapFree(getProcessHeap, 0, this.justify);
                this.justify = 0;
            }
            if (this.psla != 0) {
                OS.HeapFree(getProcessHeap, 0, this.psla);
                this.psla = 0;
            }
            if (this.fallbackFont != 0) {
                OS.DeleteObject(this.fallbackFont);
                this.fallbackFont = 0;
            }
            final boolean b = false;
            this.x = (b ? 1 : 0);
            this.descent = (b ? 1 : 0);
            this.ascent = (b ? 1 : 0);
            this.width = (b ? 1 : 0);
            final boolean b2 = false;
            this.softBreak = b2;
            this.lineBreak = b2;
        }
        
        public String toString() {
            return "StyleItem {" + this.start + ", " + this.style + "}";
        }
    }
}
