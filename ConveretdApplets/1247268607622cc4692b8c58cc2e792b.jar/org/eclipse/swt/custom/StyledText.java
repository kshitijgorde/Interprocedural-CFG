// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import java.util.Vector;
import java.util.Enumeration;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.accessibility.Accessible;
import org.eclipse.swt.accessibility.AccessibleControlListener;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.accessibility.AccessibleAttributeListener;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.accessibility.AccessibleTextAttributeEvent;
import org.eclipse.swt.accessibility.AccessibleAttributeEvent;
import org.eclipse.swt.accessibility.AccessibleAttributeAdapter;
import org.eclipse.swt.accessibility.AccessibleTextListener;
import org.eclipse.swt.accessibility.AccessibleTextEvent;
import org.eclipse.swt.accessibility.AccessibleTextExtendedAdapter;
import org.eclipse.swt.accessibility.AccessibleListener;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.TypedListener;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.internal.BidiUtil;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.IME;
import org.eclipse.swt.widgets.Caret;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.dnd.Clipboard;
import java.util.Hashtable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;

public class StyledText extends Canvas
{
    static final char TAB = '\t';
    static final String PlatformLineDelimiter;
    static final int BIDI_CARET_WIDTH = 3;
    static final int DEFAULT_WIDTH = 64;
    static final int DEFAULT_HEIGHT = 64;
    static final int V_SCROLL_RATE = 50;
    static final int H_SCROLL_RATE = 10;
    static final int ExtendedModify = 3000;
    static final int LineGetBackground = 3001;
    static final int LineGetStyle = 3002;
    static final int TextChanging = 3003;
    static final int TextSet = 3004;
    static final int VerifyKey = 3005;
    static final int TextChanged = 3006;
    static final int LineGetSegments = 3007;
    static final int PaintObject = 3008;
    static final int WordNext = 3009;
    static final int WordPrevious = 3010;
    static final int CaretMoved = 3011;
    static final int PREVIOUS_OFFSET_TRAILING = 0;
    static final int OFFSET_LEADING = 1;
    Color selectionBackground;
    Color selectionForeground;
    StyledTextContent content;
    StyledTextRenderer renderer;
    Listener listener;
    TextChangeListener textChangeListener;
    int verticalScrollOffset;
    int horizontalScrollOffset;
    int topIndex;
    int topIndexY;
    int clientAreaHeight;
    int clientAreaWidth;
    int tabLength;
    int[] tabs;
    int leftMargin;
    int topMargin;
    int rightMargin;
    int bottomMargin;
    Color marginColor;
    int columnX;
    int caretOffset;
    int caretAlignment;
    Point selection;
    Point clipboardSelection;
    int selectionAnchor;
    Point doubleClickSelection;
    boolean editable;
    boolean wordWrap;
    boolean doubleClickEnabled;
    boolean overwrite;
    int textLimit;
    Hashtable keyActionMap;
    Color background;
    Color foreground;
    Clipboard clipboard;
    int clickCount;
    int autoScrollDirection;
    int autoScrollDistance;
    int lastTextChangeStart;
    int lastTextChangeNewLineCount;
    int lastTextChangeNewCharCount;
    int lastTextChangeReplaceLineCount;
    int lastTextChangeReplaceCharCount;
    int lastCharCount;
    int lastLineBottom;
    boolean bidiColoring;
    Image leftCaretBitmap;
    Image rightCaretBitmap;
    int caretDirection;
    int caretWidth;
    Caret defaultCaret;
    boolean updateCaretDirection;
    boolean fixedLineHeight;
    boolean dragDetect;
    IME ime;
    Cursor cursor;
    int alignment;
    boolean justify;
    int indent;
    int wrapIndent;
    int lineSpacing;
    int alignmentMargin;
    int newOrientation;
    int accCaretOffset;
    boolean blockSelection;
    int blockXAnchor;
    int blockYAnchor;
    int blockXLocation;
    int blockYLocation;
    static final boolean IS_MAC;
    static final boolean IS_GTK;
    static final boolean IS_MOTIF;
    
    static {
        PlatformLineDelimiter = System.getProperty("line.separator");
        final String platform = SWT.getPlatform();
        IS_MAC = ("carbon".equals(platform) || "cocoa".equals(platform));
        IS_GTK = "gtk".equals(platform);
        IS_MOTIF = "motif".equals(platform);
    }
    
    public StyledText(final Composite composite, final int n) {
        super(composite, checkStyle(n));
        this.verticalScrollOffset = 0;
        this.horizontalScrollOffset = 0;
        this.topIndex = 0;
        this.clientAreaHeight = 0;
        this.clientAreaWidth = 0;
        this.tabLength = 4;
        this.selection = new Point(0, 0);
        this.editable = true;
        this.wordWrap = false;
        this.doubleClickEnabled = true;
        this.overwrite = false;
        this.textLimit = -1;
        this.keyActionMap = new Hashtable();
        this.background = null;
        this.foreground = null;
        this.autoScrollDirection = 0;
        this.autoScrollDistance = 0;
        this.lastCharCount = 0;
        this.bidiColoring = false;
        this.leftCaretBitmap = null;
        this.rightCaretBitmap = null;
        this.caretDirection = 0;
        this.caretWidth = 0;
        this.defaultCaret = null;
        this.updateCaretDirection = true;
        this.dragDetect = true;
        this.newOrientation = 0;
        this.blockXAnchor = -1;
        this.blockYAnchor = -1;
        this.blockXLocation = -1;
        this.blockYLocation = -1;
        super.setForeground(this.getForeground());
        super.setDragDetect(false);
        final Display display = this.getDisplay();
        this.fixedLineHeight = true;
        if ((n & 0x8) != 0x0) {
            this.setEditable(false);
        }
        final int n2 = this.isBidiCaret() ? 2 : 0;
        this.rightMargin = n2;
        this.leftMargin = n2;
        if ((n & 0x4) != 0x0 && (n & 0x800) != 0x0) {
            final int n3 = 2;
            this.bottomMargin = n3;
            this.rightMargin = n3;
            this.topMargin = n3;
            this.leftMargin = n3;
        }
        this.alignment = (n & 0x1024000);
        if (this.alignment == 0) {
            this.alignment = 16384;
        }
        this.clipboard = new Clipboard(display);
        this.installDefaultContent();
        (this.renderer = new StyledTextRenderer(this.getDisplay(), this)).setContent(this.content);
        this.renderer.setFont(this.getFont(), this.tabLength);
        this.ime = new IME(this, 0);
        this.defaultCaret = new Caret(this, 0);
        if ((n & 0x40) != 0x0) {
            this.setWordWrap(true);
        }
        if (this.isBidiCaret()) {
            this.createCaretBitmaps();
            BidiUtil.addLanguageListener(this, new Runnable() {
                public void run() {
                    final int n = (BidiUtil.getKeyboardLanguage() == 1) ? 131072 : 16384;
                    if (n == StyledText.this.caretDirection) {
                        return;
                    }
                    if (StyledText.this.getCaret() != StyledText.this.defaultCaret) {
                        return;
                    }
                    StyledText.this.setCaretLocation(StyledText.this.getPointAtOffset(StyledText.this.caretOffset), n);
                }
            });
        }
        this.setCaret(this.defaultCaret);
        this.calculateScrollBars();
        this.createKeyBindings();
        super.setCursor(display.getSystemCursor(19));
        this.installListeners();
        this.initializeAccessible();
        this.setData("DEFAULT_DROP_TARGET_EFFECT", new StyledTextDropTargetEffect(this));
    }
    
    public void addExtendedModifyListener(final ExtendedModifyListener extendedModifyListener) {
        this.checkWidget();
        if (extendedModifyListener == null) {
            SWT.error(4);
        }
        this.addListener(3000, new StyledTextListener(extendedModifyListener));
    }
    
    public void addBidiSegmentListener(final BidiSegmentListener bidiSegmentListener) {
        this.checkWidget();
        if (bidiSegmentListener == null) {
            SWT.error(4);
        }
        this.addListener(3007, new StyledTextListener(bidiSegmentListener));
    }
    
    public void addCaretListener(final CaretListener caretListener) {
        this.checkWidget();
        if (caretListener == null) {
            SWT.error(4);
        }
        this.addListener(3011, new StyledTextListener(caretListener));
    }
    
    public void addLineBackgroundListener(final LineBackgroundListener lineBackgroundListener) {
        this.checkWidget();
        if (lineBackgroundListener == null) {
            SWT.error(4);
        }
        if (!this.isListening(3001)) {
            this.renderer.clearLineBackground(0, this.content.getLineCount());
        }
        this.addListener(3001, new StyledTextListener(lineBackgroundListener));
    }
    
    public void addLineStyleListener(final LineStyleListener lineStyleListener) {
        this.checkWidget();
        if (lineStyleListener == null) {
            SWT.error(4);
        }
        if (!this.isListening(3002)) {
            this.setStyleRanges(0, 0, null, null, true);
            this.renderer.clearLineStyle(0, this.content.getLineCount());
        }
        this.addListener(3002, new StyledTextListener(lineStyleListener));
        this.setCaretLocation();
    }
    
    public void addModifyListener(final ModifyListener modifyListener) {
        this.checkWidget();
        if (modifyListener == null) {
            SWT.error(4);
        }
        this.addListener(24, new TypedListener(modifyListener));
    }
    
    public void addPaintObjectListener(final PaintObjectListener paintObjectListener) {
        this.checkWidget();
        if (paintObjectListener == null) {
            SWT.error(4);
        }
        this.addListener(3008, new StyledTextListener(paintObjectListener));
    }
    
    public void addSelectionListener(final SelectionListener selectionListener) {
        this.checkWidget();
        if (selectionListener == null) {
            SWT.error(4);
        }
        this.addListener(13, new TypedListener(selectionListener));
    }
    
    public void addVerifyKeyListener(final VerifyKeyListener verifyKeyListener) {
        this.checkWidget();
        if (verifyKeyListener == null) {
            SWT.error(4);
        }
        this.addListener(3005, new StyledTextListener(verifyKeyListener));
    }
    
    public void addVerifyListener(final VerifyListener verifyListener) {
        this.checkWidget();
        if (verifyListener == null) {
            SWT.error(4);
        }
        this.addListener(25, new TypedListener(verifyListener));
    }
    
    public void addWordMovementListener(final MovementListener movementListener) {
        this.checkWidget();
        if (movementListener == null) {
            SWT.error(4);
        }
        this.addListener(3009, new StyledTextListener(movementListener));
        this.addListener(3010, new StyledTextListener(movementListener));
    }
    
    public void append(final String s) {
        this.checkWidget();
        if (s == null) {
            SWT.error(4);
        }
        this.replaceTextRange(Math.max(this.getCharCount(), 0), 0, s);
    }
    
    void calculateScrollBars() {
        final ScrollBar horizontalBar = this.getHorizontalBar();
        final ScrollBar verticalBar = this.getVerticalBar();
        this.setScrollBars(true);
        if (verticalBar != null) {
            verticalBar.setIncrement(this.getVerticalIncrement());
        }
        if (horizontalBar != null) {
            horizontalBar.setIncrement(this.getHorizontalIncrement());
        }
    }
    
    void calculateTopIndex(int n) {
        final int topIndex = this.topIndex;
        final int topIndexY = this.topIndexY;
        if (this.isFixedLineHeight()) {
            final int verticalIncrement = this.getVerticalIncrement();
            if (verticalIncrement == 0) {
                return;
            }
            this.topIndex = Compatibility.ceil(this.getVerticalScrollOffset(), verticalIncrement);
            if (this.topIndex > 0) {
                if (this.clientAreaHeight > 0) {
                    if (this.getVerticalScrollOffset() + this.clientAreaHeight - this.topIndex * verticalIncrement < verticalIncrement) {
                        --this.topIndex;
                    }
                }
                else if (this.topIndex >= this.content.getLineCount()) {
                    this.topIndex = this.content.getLineCount() - 1;
                }
            }
        }
        else if (n >= 0) {
            int topIndex2;
            int lineCount;
            for (n -= this.topIndexY, topIndex2 = this.topIndex, lineCount = this.content.getLineCount(); topIndex2 < lineCount && n > 0; n -= this.renderer.getLineHeight(topIndex2++)) {}
            if (topIndex2 < lineCount && -n + this.renderer.getLineHeight(topIndex2) <= this.clientAreaHeight - this.topMargin - this.bottomMargin) {
                this.topIndex = topIndex2;
                this.topIndexY = -n;
            }
            else {
                this.topIndex = topIndex2 - 1;
                this.topIndexY = -this.renderer.getLineHeight(this.topIndex) - n;
            }
        }
        else {
            n -= this.topIndexY;
            int i;
            for (i = this.topIndex; i > 0; --i) {
                final int lineHeight = this.renderer.getLineHeight(i - 1);
                if (n + lineHeight > 0) {
                    break;
                }
                n += lineHeight;
            }
            if (i == 0 || -n + this.renderer.getLineHeight(i) <= this.clientAreaHeight - this.topMargin - this.bottomMargin) {
                this.topIndex = i;
                this.topIndexY = -n;
            }
            else {
                this.topIndex = i - 1;
                this.topIndexY = -this.renderer.getLineHeight(this.topIndex) - n;
            }
        }
        if (this.topIndex != topIndex || topIndexY != this.topIndexY) {
            this.renderer.calculateClientArea();
            this.setScrollBars(false);
        }
    }
    
    static int checkStyle(int n) {
        if ((n & 0x4) != 0x0) {
            n &= 0xFFFFFCBD;
        }
        else {
            n |= 0x2;
            if ((n & 0x40) != 0x0) {
                n &= 0xFFFFFEFF;
            }
        }
        n |= 0x20140000;
        return n & 0xFEFFFFFF;
    }
    
    void claimBottomFreeSpace() {
        if (this.ime.getCompositionOffset() != -1) {
            return;
        }
        if (this.isFixedLineHeight()) {
            final int max = Math.max(0, this.renderer.getHeight() - this.clientAreaHeight);
            if (max < this.getVerticalScrollOffset()) {
                this.scrollVertical(max - this.getVerticalScrollOffset(), true);
            }
        }
        else {
            final int linePixel = this.getLinePixel(this.getPartialBottomIndex() + 1);
            if (this.clientAreaHeight > linePixel) {
                this.scrollVertical(-this.getAvailableHeightAbove(this.clientAreaHeight - linePixel), true);
            }
        }
    }
    
    void claimRightFreeSpace() {
        final int max = Math.max(0, this.renderer.getWidth() - this.clientAreaWidth);
        if (max < this.horizontalScrollOffset) {
            this.scrollHorizontal(max - this.horizontalScrollOffset, true);
        }
    }
    
    void clearBlockSelection(final boolean b, final boolean b2) {
        if (b) {
            this.resetSelection();
        }
        final int n = -1;
        this.blockYAnchor = n;
        this.blockXAnchor = n;
        final int n2 = -1;
        this.blockYLocation = n2;
        this.blockXLocation = n2;
        this.caretDirection = 0;
        this.updateCaretVisibility();
        super.redraw();
        if (b2) {
            this.sendSelectionEvent();
        }
    }
    
    void clearSelection(final boolean b) {
        final int x = this.selection.x;
        final int y = this.selection.y;
        this.resetSelection();
        if (y - x > 0) {
            final int charCount = this.content.getCharCount();
            final int min = Math.min(x, charCount);
            final int min2 = Math.min(y, charCount);
            if (min2 - min > 0) {
                this.internalRedrawRange(min, min2 - min);
            }
            if (b) {
                this.sendSelectionEvent();
            }
        }
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        final int n3 = ((this.getStyle() & 0x4) != 0x0) ? 1 : this.content.getLineCount();
        int max = 0;
        int n4 = 0;
        if (n == -1 || n2 == -1) {
            final int height = this.getDisplay().getClientArea().height;
            for (int i = 0; i < n3; ++i) {
                final TextLayout textLayout = this.renderer.getTextLayout(i);
                final int width = textLayout.getWidth();
                if (this.wordWrap) {
                    textLayout.setWidth((n == 0) ? 1 : n);
                }
                final Rectangle bounds = textLayout.getBounds();
                n4 += bounds.height;
                max = Math.max(max, bounds.width);
                textLayout.setWidth(width);
                this.renderer.disposeTextLayout(textLayout);
                if (this.isFixedLineHeight() && n4 > height) {
                    break;
                }
            }
            if (this.isFixedLineHeight()) {
                n4 = n3 * this.renderer.getLineHeight();
            }
        }
        if (max == 0) {
            max = 64;
        }
        if (n4 == 0) {
            n4 = 64;
        }
        if (n != -1) {
            max = n;
        }
        if (n2 != -1) {
            n4 = n2;
        }
        final Rectangle computeTrim = this.computeTrim(0, 0, max + (this.leftMargin + this.rightMargin + this.getCaretWidth()), n4 + (this.topMargin + this.bottomMargin));
        return new Point(computeTrim.width, computeTrim.height);
    }
    
    public void copy() {
        this.checkWidget();
        this.copySelection(1);
    }
    
    public void copy(final int n) {
        this.checkWidget();
        this.copySelection(n);
    }
    
    boolean copySelection(final int n) {
        if (n != 1 && n != 2) {
            return false;
        }
        try {
            if (this.blockSelection && this.blockXLocation != -1) {
                final String blockSelectionText = this.getBlockSelectionText(StyledText.PlatformLineDelimiter);
                if (blockSelectionText.length() > 0) {
                    this.clipboard.setContents(new Object[] { blockSelectionText }, new Transfer[] { TextTransfer.getInstance() }, n);
                    return true;
                }
            }
            else {
                final int n2 = this.selection.y - this.selection.x;
                if (n2 > 0) {
                    this.setClipboardContent(this.selection.x, n2, n);
                    return true;
                }
            }
        }
        catch (SWTError swtError) {
            if (swtError.code != 2002) {
                throw swtError;
            }
        }
        return false;
    }
    
    public int getAlignment() {
        this.checkWidget();
        return this.alignment;
    }
    
    int getAvailableHeightAbove(final int n) {
        int verticalScrollOffset = this.verticalScrollOffset;
        if (verticalScrollOffset == -1) {
            int n2 = this.topIndex - 1;
            verticalScrollOffset = -this.topIndexY;
            if (this.topIndexY > 0) {
                verticalScrollOffset += this.renderer.getLineHeight(n2--);
            }
            while (n > verticalScrollOffset && n2 >= 0) {
                verticalScrollOffset += this.renderer.getLineHeight(n2--);
            }
        }
        return Math.min(n, verticalScrollOffset);
    }
    
    int getAvailableHeightBellow(final int n) {
        final int partialBottomIndex = this.getPartialBottomIndex();
        final int linePixel = this.getLinePixel(partialBottomIndex);
        final int lineHeight = this.renderer.getLineHeight(partialBottomIndex);
        int n2 = 0;
        final int n3 = this.clientAreaHeight - this.topMargin - this.bottomMargin;
        if (linePixel + lineHeight > n3) {
            n2 = lineHeight - (n3 - linePixel);
        }
        for (int n4 = partialBottomIndex + 1, lineCount = this.content.getLineCount(); n > n2 && n4 < lineCount; n2 += this.renderer.getLineHeight(n4++)) {}
        return Math.min(n, n2);
    }
    
    public Color getMarginColor() {
        this.checkWidget();
        return (this.marginColor != null) ? this.marginColor : this.getBackground();
    }
    
    String getModelDelimitedText(final String s) {
        final int length = s.length();
        if (length == 0) {
            return s;
        }
        int index = 0;
        int index2 = 0;
        int i = 0;
        final StringBuffer sb = new StringBuffer(length);
        final String lineDelimiter = this.getLineDelimiter();
        while (i < length) {
            if (index != -1) {
                index = s.indexOf(13, i);
            }
            if (index2 != -1) {
                index2 = s.indexOf(10, i);
            }
            if (index2 == -1 && index == -1) {
                break;
            }
            if ((index < index2 && index != -1) || index2 == -1) {
                sb.append(s.substring(i, index));
                if (index2 == index + 1) {
                    i = index2 + 1;
                }
                else {
                    i = index + 1;
                }
            }
            else {
                sb.append(s.substring(i, index2));
                i = index2 + 1;
            }
            if (this.isSingleLine()) {
                break;
            }
            sb.append(lineDelimiter);
        }
        if (i < length && (!this.isSingleLine() || sb.length() == 0)) {
            sb.append(s.substring(i));
        }
        return sb.toString();
    }
    
    boolean checkDragDetect(final Event event) {
        if (!this.isListening(29)) {
            return false;
        }
        if (StyledText.IS_MOTIF) {
            if (event.button != 2) {
                return false;
            }
        }
        else if (event.button != 1) {
            return false;
        }
        if (this.blockSelection && this.blockXLocation != -1) {
            if (this.getBlockSelectionRectangle().contains(event.x, event.y)) {
                return this.dragDetect(event);
            }
        }
        else {
            if (this.selection.x == this.selection.y) {
                return false;
            }
            final int offsetAtPoint = this.getOffsetAtPoint(event.x, event.y, null, true);
            if (this.selection.x <= offsetAtPoint && offsetAtPoint < this.selection.y) {
                return this.dragDetect(event);
            }
        }
        return false;
    }
    
    void createKeyBindings() {
        final int n = this.isMirrored() ? 16777219 : 16777220;
        final int n2 = this.isMirrored() ? 16777220 : 16777219;
        this.setKeyBinding(16777217, 16777217);
        this.setKeyBinding(16777218, 16777218);
        if (StyledText.IS_MAC) {
            this.setKeyBinding(n2 | SWT.MOD1, 16777223);
            this.setKeyBinding(n | SWT.MOD1, 16777224);
            this.setKeyBinding(16777223, 17039367);
            this.setKeyBinding(16777224, 17039368);
            this.setKeyBinding(0x1000001 | SWT.MOD1, 17039367);
            this.setKeyBinding(0x1000002 | SWT.MOD1, 17039368);
            this.setKeyBinding(n | SWT.MOD3, 17039364);
            this.setKeyBinding(n2 | SWT.MOD3, 17039363);
        }
        else {
            this.setKeyBinding(16777223, 16777223);
            this.setKeyBinding(16777224, 16777224);
            this.setKeyBinding(0x1000007 | SWT.MOD1, 17039367);
            this.setKeyBinding(0x1000008 | SWT.MOD1, 17039368);
            this.setKeyBinding(n | SWT.MOD1, 17039364);
            this.setKeyBinding(n2 | SWT.MOD1, 17039363);
        }
        this.setKeyBinding(16777221, 16777221);
        this.setKeyBinding(16777222, 16777222);
        this.setKeyBinding(0x1000005 | SWT.MOD1, 17039365);
        this.setKeyBinding(0x1000006 | SWT.MOD1, 17039366);
        this.setKeyBinding(n, 16777220);
        this.setKeyBinding(n2, 16777219);
        this.setKeyBinding(0x1000001 | SWT.MOD2, 16908289);
        this.setKeyBinding(0x1000002 | SWT.MOD2, 16908290);
        if (StyledText.IS_MAC) {
            this.setKeyBinding(n2 | SWT.MOD1 | SWT.MOD2, 16908295);
            this.setKeyBinding(n | SWT.MOD1 | SWT.MOD2, 16908296);
            this.setKeyBinding(0x1000007 | SWT.MOD2, 17170439);
            this.setKeyBinding(0x1000008 | SWT.MOD2, 17170440);
            this.setKeyBinding(0x1000001 | SWT.MOD1 | SWT.MOD2, 17170439);
            this.setKeyBinding(0x1000002 | SWT.MOD1 | SWT.MOD2, 17170440);
            this.setKeyBinding(n | SWT.MOD2 | SWT.MOD3, 17170436);
            this.setKeyBinding(n2 | SWT.MOD2 | SWT.MOD3, 17170435);
        }
        else {
            this.setKeyBinding(0x1000007 | SWT.MOD2, 16908295);
            this.setKeyBinding(0x1000008 | SWT.MOD2, 16908296);
            this.setKeyBinding(0x1000007 | SWT.MOD1 | SWT.MOD2, 17170439);
            this.setKeyBinding(0x1000008 | SWT.MOD1 | SWT.MOD2, 17170440);
            this.setKeyBinding(n | SWT.MOD1 | SWT.MOD2, 17170436);
            this.setKeyBinding(n2 | SWT.MOD1 | SWT.MOD2, 17170435);
        }
        this.setKeyBinding(0x1000005 | SWT.MOD2, 16908293);
        this.setKeyBinding(0x1000006 | SWT.MOD2, 16908294);
        this.setKeyBinding(0x1000005 | SWT.MOD1 | SWT.MOD2, 17170437);
        this.setKeyBinding(0x1000006 | SWT.MOD1 | SWT.MOD2, 17170438);
        this.setKeyBinding(n | SWT.MOD2, 16908292);
        this.setKeyBinding(n2 | SWT.MOD2, 16908291);
        this.setKeyBinding(0x58 | SWT.MOD1, 131199);
        this.setKeyBinding(0x43 | SWT.MOD1, 17039369);
        this.setKeyBinding(0x56 | SWT.MOD1, 16908297);
        if (StyledText.IS_MAC) {
            this.setKeyBinding(0x7F | SWT.MOD2, 127);
            this.setKeyBinding(0x8 | SWT.MOD3, 262152);
            this.setKeyBinding(0x7F | SWT.MOD3, 262271);
        }
        else {
            this.setKeyBinding(0x7F | SWT.MOD2, 131199);
            this.setKeyBinding(0x1000009 | SWT.MOD1, 17039369);
            this.setKeyBinding(0x1000009 | SWT.MOD2, 16908297);
        }
        this.setKeyBinding(0x8 | SWT.MOD2, 8);
        this.setKeyBinding(8, 8);
        this.setKeyBinding(127, 127);
        this.setKeyBinding(0x8 | SWT.MOD1, 262152);
        this.setKeyBinding(0x7F | SWT.MOD1, 262271);
        this.setKeyBinding(16777225, 16777225);
    }
    
    void createCaretBitmaps() {
        final int n = 3;
        final Display display = this.getDisplay();
        if (this.leftCaretBitmap != null) {
            if (this.defaultCaret != null && this.leftCaretBitmap.equals(this.defaultCaret.getImage())) {
                this.defaultCaret.setImage(null);
            }
            this.leftCaretBitmap.dispose();
        }
        final int lineHeight = this.renderer.getLineHeight();
        this.leftCaretBitmap = new Image(display, n, lineHeight);
        final GC gc = new GC(this.leftCaretBitmap);
        gc.setBackground(display.getSystemColor(2));
        gc.fillRectangle(0, 0, n, lineHeight);
        gc.setForeground(display.getSystemColor(1));
        gc.drawLine(0, 0, 0, lineHeight);
        gc.drawLine(0, 0, n - 1, 0);
        gc.drawLine(0, 1, 1, 1);
        gc.dispose();
        if (this.rightCaretBitmap != null) {
            if (this.defaultCaret != null && this.rightCaretBitmap.equals(this.defaultCaret.getImage())) {
                this.defaultCaret.setImage(null);
            }
            this.rightCaretBitmap.dispose();
        }
        this.rightCaretBitmap = new Image(display, n, lineHeight);
        final GC gc2 = new GC(this.rightCaretBitmap);
        gc2.setBackground(display.getSystemColor(2));
        gc2.fillRectangle(0, 0, n, lineHeight);
        gc2.setForeground(display.getSystemColor(1));
        gc2.drawLine(n - 1, 0, n - 1, lineHeight);
        gc2.drawLine(0, 0, n - 1, 0);
        gc2.drawLine(n - 1, 1, 1, 1);
        gc2.dispose();
    }
    
    public void cut() {
        this.checkWidget();
        if (this.copySelection(1)) {
            if (this.blockSelection && this.blockXLocation != -1) {
                this.insertBlockSelectionText('\0', 0);
            }
            else {
                this.doDelete();
            }
        }
    }
    
    void doAutoScroll(final Event event) {
        final int caretLine = this.getCaretLine();
        if (event.y > this.clientAreaHeight - this.bottomMargin && caretLine != this.content.getLineCount() - 1) {
            this.doAutoScroll(1024, event.y - (this.clientAreaHeight - this.bottomMargin));
        }
        else if (event.y < this.topMargin && caretLine != 0) {
            this.doAutoScroll(128, this.topMargin - event.y);
        }
        else if (event.x < this.leftMargin && !this.wordWrap) {
            this.doAutoScroll(16777219, this.leftMargin - event.x);
        }
        else if (event.x > this.clientAreaWidth - this.rightMargin && !this.wordWrap) {
            this.doAutoScroll(16777220, event.x - (this.clientAreaWidth - this.rightMargin));
        }
        else {
            this.endAutoScroll();
        }
    }
    
    void doAutoScroll(final int n, final int autoScrollDistance) {
        this.autoScrollDistance = autoScrollDistance;
        if (this.autoScrollDirection == n) {
            return;
        }
        final Display display = this.getDisplay();
        if (n == 128) {
            final Runnable runnable = new Runnable() {
                public void run() {
                    if (StyledText.this.autoScrollDirection == 128) {
                        if (StyledText.this.blockSelection) {
                            final int verticalScrollOffset = StyledText.this.getVerticalScrollOffset();
                            final int n = StyledText.this.blockYLocation - verticalScrollOffset;
                            final int max = Math.max(-StyledText.this.autoScrollDistance, -verticalScrollOffset);
                            if (max != 0) {
                                StyledText.this.setBlockSelectionLocation(StyledText.this.blockXLocation - StyledText.this.horizontalScrollOffset, n + max, true);
                                StyledText.this.scrollVertical(max, true);
                            }
                        }
                        else {
                            StyledText.this.doSelectionPageUp(StyledText.this.autoScrollDistance);
                        }
                        display.timerExec(50, this);
                    }
                }
            };
            this.autoScrollDirection = n;
            display.timerExec(50, runnable);
        }
        else if (n == 1024) {
            final Runnable runnable2 = new Runnable() {
                public void run() {
                    if (StyledText.this.autoScrollDirection == 1024) {
                        if (StyledText.this.blockSelection) {
                            final int verticalScrollOffset = StyledText.this.getVerticalScrollOffset();
                            final int n = StyledText.this.blockYLocation - verticalScrollOffset;
                            final int min = Math.min(StyledText.this.autoScrollDistance, Math.max(0, StyledText.this.renderer.getHeight() - verticalScrollOffset - StyledText.this.clientAreaHeight));
                            if (min != 0) {
                                StyledText.this.setBlockSelectionLocation(StyledText.this.blockXLocation - StyledText.this.horizontalScrollOffset, n + min, true);
                                StyledText.this.scrollVertical(min, true);
                            }
                        }
                        else {
                            StyledText.this.doSelectionPageDown(StyledText.this.autoScrollDistance);
                        }
                        display.timerExec(50, this);
                    }
                }
            };
            this.autoScrollDirection = n;
            display.timerExec(50, runnable2);
        }
        else if (n == 16777220) {
            final Runnable runnable3 = new Runnable() {
                public void run() {
                    if (StyledText.this.autoScrollDirection == 16777220) {
                        if (StyledText.this.blockSelection) {
                            final int n = StyledText.this.blockXLocation - StyledText.this.horizontalScrollOffset;
                            final int min = Math.min(StyledText.this.autoScrollDistance, Math.max(0, StyledText.this.renderer.getWidth() - StyledText.this.horizontalScrollOffset - StyledText.this.clientAreaWidth));
                            if (min != 0) {
                                StyledText.this.setBlockSelectionLocation(n + min, StyledText.this.blockYLocation - StyledText.this.getVerticalScrollOffset(), true);
                                StyledText.this.scrollHorizontal(min, true);
                            }
                        }
                        else {
                            StyledText.this.doVisualNext();
                            StyledText.this.setMouseWordSelectionAnchor();
                            StyledText.this.doMouseSelection();
                        }
                        display.timerExec(10, this);
                    }
                }
            };
            this.autoScrollDirection = n;
            display.timerExec(10, runnable3);
        }
        else if (n == 16777219) {
            final Runnable runnable4 = new Runnable() {
                public void run() {
                    if (StyledText.this.autoScrollDirection == 16777219) {
                        if (StyledText.this.blockSelection) {
                            final int n = StyledText.this.blockXLocation - StyledText.this.horizontalScrollOffset;
                            final int max = Math.max(-StyledText.this.autoScrollDistance, -StyledText.this.horizontalScrollOffset);
                            if (max != 0) {
                                StyledText.this.setBlockSelectionLocation(n + max, StyledText.this.blockYLocation - StyledText.this.getVerticalScrollOffset(), true);
                                StyledText.this.scrollHorizontal(max, true);
                            }
                        }
                        else {
                            StyledText.this.doVisualPrevious();
                            StyledText.this.setMouseWordSelectionAnchor();
                            StyledText.this.doMouseSelection();
                        }
                        display.timerExec(10, this);
                    }
                }
            };
            this.autoScrollDirection = n;
            display.timerExec(10, runnable4);
        }
    }
    
    void doBackspace() {
        final Event event = new Event();
        event.text = "";
        if (this.selection.x != this.selection.y) {
            event.start = this.selection.x;
            event.end = this.selection.y;
            this.sendKeyEvent(event);
        }
        else if (this.caretOffset > 0) {
            final int lineAtOffset = this.content.getLineAtOffset(this.caretOffset);
            final int offsetAtLine = this.content.getOffsetAtLine(lineAtOffset);
            if (this.caretOffset == offsetAtLine) {
                event.start = this.content.getOffsetAtLine(lineAtOffset - 1) + this.content.getLine(lineAtOffset - 1).length();
                event.end = this.caretOffset;
            }
            else {
                boolean b = false;
                final String line = this.content.getLine(lineAtOffset);
                final char char1 = line.charAt(this.caretOffset - offsetAtLine - 1);
                if ('\udc00' <= char1 && char1 <= '\udfff' && this.caretOffset - offsetAtLine - 2 >= 0) {
                    final char char2 = line.charAt(this.caretOffset - offsetAtLine - 2);
                    b = ('\ud800' <= char2 && char2 <= '\udbff');
                }
                final TextLayout textLayout = this.renderer.getTextLayout(lineAtOffset);
                final int previousOffset = textLayout.getPreviousOffset(this.caretOffset - offsetAtLine, b ? 2 : 1);
                this.renderer.disposeTextLayout(textLayout);
                event.start = previousOffset + offsetAtLine;
                event.end = this.caretOffset;
            }
            this.sendKeyEvent(event);
        }
    }
    
    void doBlockColumn(final boolean b) {
        if (this.blockXLocation == -1) {
            this.setBlockSelectionOffset(this.caretOffset, false);
        }
        final int n = this.blockXLocation - this.horizontalScrollOffset;
        final int n2 = this.blockYLocation - this.getVerticalScrollOffset();
        final int[] array = { 0 };
        int offsetAtPoint = this.getOffsetAtPoint(n, n2, array, true);
        if (offsetAtPoint != -1) {
            final int n3 = offsetAtPoint + array[0];
            final int lineAtOffset = this.content.getLineAtOffset(n3);
            int n4;
            if (b) {
                n4 = this.getClusterNext(n3, lineAtOffset);
            }
            else {
                n4 = this.getClusterPrevious(n3, lineAtOffset);
            }
            offsetAtPoint = ((n4 != n3) ? n4 : -1);
        }
        if (offsetAtPoint != -1) {
            this.setBlockSelectionOffset(offsetAtPoint, true);
            this.showCaret();
        }
        else {
            final int n5 = Math.max(0, Math.min(this.blockXLocation + (b ? this.renderer.averageCharWidth : (-this.renderer.averageCharWidth)), Math.max(this.clientAreaWidth - this.rightMargin - this.leftMargin, this.renderer.getWidth()))) - this.horizontalScrollOffset;
            this.setBlockSelectionLocation(n5, n2, true);
            this.showLocation(new Rectangle(n5, n2, 0, 0), true);
        }
    }
    
    void doBlockWord(final boolean b) {
        if (this.blockXLocation == -1) {
            this.setBlockSelectionOffset(this.caretOffset, false);
        }
        final int n = this.blockXLocation - this.horizontalScrollOffset;
        final int n2 = this.blockYLocation - this.getVerticalScrollOffset();
        final int[] array = { 0 };
        int offsetAtPoint = this.getOffsetAtPoint(n, n2, array, true);
        if (offsetAtPoint != -1) {
            final int n3 = offsetAtPoint + array[0];
            final int lineAtOffset = this.content.getLineAtOffset(n3);
            final int offsetAtLine = this.content.getOffsetAtLine(lineAtOffset);
            final int length = this.content.getLine(lineAtOffset).length();
            int n4 = n3;
            if (b) {
                if (n3 < offsetAtLine + length) {
                    n4 = this.getWordNext(n3, 4);
                }
            }
            else if (n3 > offsetAtLine) {
                n4 = this.getWordPrevious(n3, 4);
            }
            offsetAtPoint = ((n4 != n3) ? n4 : -1);
        }
        if (offsetAtPoint != -1) {
            this.setBlockSelectionOffset(offsetAtPoint, true);
            this.showCaret();
        }
        else {
            final int n5 = Math.max(0, Math.min(this.blockXLocation + (b ? this.renderer.averageCharWidth : (-this.renderer.averageCharWidth)) * 6, Math.max(this.clientAreaWidth - this.rightMargin - this.leftMargin, this.renderer.getWidth()))) - this.horizontalScrollOffset;
            this.setBlockSelectionLocation(n5, n2, true);
            this.showLocation(new Rectangle(n5, n2, 0, 0), true);
        }
    }
    
    void doBlockLineVertical(final boolean b) {
        if (this.blockXLocation == -1) {
            this.setBlockSelectionOffset(this.caretOffset, false);
        }
        final int lineIndex = this.getLineIndex(this.blockYLocation - this.getVerticalScrollOffset());
        if (b) {
            if (lineIndex > 0) {
                final int linePixel = this.getLinePixel(lineIndex - 1);
                this.setBlockSelectionLocation(this.blockXLocation - this.horizontalScrollOffset, linePixel, true);
                if (linePixel < this.topMargin) {
                    this.scrollVertical(linePixel - this.topMargin, true);
                }
            }
        }
        else if (lineIndex + 1 < this.content.getLineCount()) {
            final int n = this.getLinePixel(lineIndex + 2) - 1;
            this.setBlockSelectionLocation(this.blockXLocation - this.horizontalScrollOffset, n, true);
            final int n2 = this.clientAreaHeight - this.bottomMargin;
            if (n > n2) {
                this.scrollVertical(n - n2, true);
            }
        }
    }
    
    void doBlockLineHorizontal(final boolean b) {
        if (this.blockXLocation == -1) {
            this.setBlockSelectionOffset(this.caretOffset, false);
        }
        final int n = this.blockXLocation - this.horizontalScrollOffset;
        final int n2 = this.blockYLocation - this.getVerticalScrollOffset();
        final int lineIndex = this.getLineIndex(n2);
        final int offsetAtLine = this.content.getOffsetAtLine(lineIndex);
        final int length = this.content.getLine(lineIndex).length();
        final int[] array = { 0 };
        int offsetAtPoint = this.getOffsetAtPoint(n, n2, array, true);
        if (offsetAtPoint != -1) {
            int n4;
            final int n3 = n4 = offsetAtPoint + array[0];
            if (b) {
                if (n3 < offsetAtLine + length) {
                    n4 = offsetAtLine + length;
                }
            }
            else if (n3 > offsetAtLine) {
                n4 = offsetAtLine;
            }
            offsetAtPoint = ((n4 != n3) ? n4 : -1);
        }
        else if (!b) {
            offsetAtPoint = offsetAtLine + length;
        }
        if (offsetAtPoint != -1) {
            this.setBlockSelectionOffset(offsetAtPoint, true);
            this.showCaret();
        }
        else {
            final int max = Math.max(this.clientAreaWidth - this.rightMargin - this.leftMargin, this.renderer.getWidth());
            final int n5 = (b ? max : false) - this.horizontalScrollOffset;
            this.setBlockSelectionLocation(n5, n2, true);
            this.showLocation(new Rectangle(n5, n2, 0, 0), true);
        }
    }
    
    void doBlockSelection(final boolean b) {
        if (this.caretOffset > this.selectionAnchor) {
            this.selection.x = this.selectionAnchor;
            this.selection.y = this.caretOffset;
        }
        else {
            this.selection.x = this.caretOffset;
            this.selection.y = this.selectionAnchor;
        }
        this.updateCaretVisibility();
        this.setCaretLocation();
        super.redraw();
        if (b) {
            this.sendSelectionEvent();
        }
        this.sendAccessibleTextCaretMoved();
    }
    
    void doContent(final char c) {
        if (this.blockSelection && this.blockXLocation != -1) {
            this.insertBlockSelectionText(c, 0);
            return;
        }
        final Event event = new Event();
        event.start = this.selection.x;
        event.end = this.selection.y;
        if (c == '\r' || c == '\n') {
            if (!this.isSingleLine()) {
                event.text = this.getLineDelimiter();
            }
        }
        else if (this.selection.x == this.selection.y && this.overwrite && c != '\t') {
            final int lineAtOffset = this.content.getLineAtOffset(event.end);
            if (event.end < this.content.getOffsetAtLine(lineAtOffset) + this.content.getLine(lineAtOffset).length()) {
                final Event event2 = event;
                ++event2.end;
            }
            event.text = new String(new char[] { c });
        }
        else {
            event.text = new String(new char[] { c });
        }
        if (event.text != null) {
            if (this.textLimit > 0 && this.content.getCharCount() - (event.end - event.start) >= this.textLimit) {
                return;
            }
            this.sendKeyEvent(event);
        }
    }
    
    void doContentEnd() {
        if (this.isSingleLine()) {
            this.doLineEnd();
        }
        else {
            final int charCount = this.content.getCharCount();
            if (this.caretOffset < charCount) {
                this.setCaretOffset(charCount, -1);
                this.showCaret();
            }
        }
    }
    
    void doContentStart() {
        if (this.caretOffset > 0) {
            this.setCaretOffset(0, -1);
            this.showCaret();
        }
    }
    
    void doCursorPrevious() {
        if (this.selection.y - this.selection.x > 0) {
            this.setCaretOffset(this.selection.x, 1);
            this.showCaret();
        }
        else {
            this.doSelectionCursorPrevious();
        }
    }
    
    void doCursorNext() {
        if (this.selection.y - this.selection.x > 0) {
            this.setCaretOffset(this.selection.y, 0);
            this.showCaret();
        }
        else {
            this.doSelectionCursorNext();
        }
    }
    
    void doDelete() {
        final Event event = new Event();
        event.text = "";
        if (this.selection.x != this.selection.y) {
            event.start = this.selection.x;
            event.end = this.selection.y;
            this.sendKeyEvent(event);
        }
        else if (this.caretOffset < this.content.getCharCount()) {
            final int lineAtOffset = this.content.getLineAtOffset(this.caretOffset);
            if (this.caretOffset == this.content.getOffsetAtLine(lineAtOffset) + this.content.getLine(lineAtOffset).length()) {
                event.start = this.caretOffset;
                event.end = this.content.getOffsetAtLine(lineAtOffset + 1);
            }
            else {
                event.start = this.caretOffset;
                event.end = this.getClusterNext(this.caretOffset, lineAtOffset);
            }
            this.sendKeyEvent(event);
        }
    }
    
    void doDeleteWordNext() {
        if (this.selection.x != this.selection.y) {
            this.doDelete();
        }
        else {
            final Event event = new Event();
            event.text = "";
            event.start = this.caretOffset;
            event.end = this.getWordNext(this.caretOffset, 4);
            this.sendKeyEvent(event);
        }
    }
    
    void doDeleteWordPrevious() {
        if (this.selection.x != this.selection.y) {
            this.doBackspace();
        }
        else {
            final Event event = new Event();
            event.text = "";
            event.start = this.getWordPrevious(this.caretOffset, 4);
            event.end = this.caretOffset;
            this.sendKeyEvent(event);
        }
    }
    
    void doLineDown(final boolean b) {
        int caretLine = this.getCaretLine();
        final int lineCount = this.content.getLineCount();
        int y = 0;
        int n = 0;
        if (this.wordWrap) {
            final int n2 = this.caretOffset - this.content.getOffsetAtLine(caretLine);
            final TextLayout textLayout = this.renderer.getTextLayout(caretLine);
            final int visualLineIndex = this.getVisualLineIndex(textLayout, n2);
            if (visualLineIndex == textLayout.getLineCount() - 1) {
                n = ((caretLine == lineCount - 1) ? 1 : 0);
                ++caretLine;
            }
            else {
                y = textLayout.getLineBounds(visualLineIndex + 1).y;
            }
            this.renderer.disposeTextLayout(textLayout);
        }
        else {
            n = ((caretLine == lineCount - 1) ? 1 : 0);
            ++caretLine;
        }
        if (n != 0) {
            if (b) {
                this.setCaretOffset(this.content.getCharCount(), -1);
            }
        }
        else {
            final int[] array = { 0 };
            this.setCaretOffset(this.getOffsetAtPoint(this.columnX, y, caretLine, array), array[0]);
        }
        final int columnX = this.columnX;
        final int horizontalScrollOffset = this.horizontalScrollOffset;
        if (b) {
            this.setMouseWordSelectionAnchor();
            this.doSelection(16777220);
        }
        this.showCaret();
        this.columnX = columnX + (horizontalScrollOffset - this.horizontalScrollOffset);
    }
    
    void doLineEnd() {
        final int caretLine = this.getCaretLine();
        final int offsetAtLine = this.content.getOffsetAtLine(caretLine);
        int n;
        if (this.wordWrap) {
            final TextLayout textLayout = this.renderer.getTextLayout(caretLine);
            n = offsetAtLine + textLayout.getLineOffsets()[this.getVisualLineIndex(textLayout, this.caretOffset - offsetAtLine) + 1];
            this.renderer.disposeTextLayout(textLayout);
        }
        else {
            n = offsetAtLine + this.content.getLine(caretLine).length();
        }
        if (this.caretOffset < n) {
            this.setCaretOffset(n, 0);
            this.showCaret();
        }
    }
    
    void doLineStart() {
        final int caretLine = this.getCaretLine();
        int offsetAtLine = this.content.getOffsetAtLine(caretLine);
        if (this.wordWrap) {
            final TextLayout textLayout = this.renderer.getTextLayout(caretLine);
            offsetAtLine += textLayout.getLineOffsets()[this.getVisualLineIndex(textLayout, this.caretOffset - offsetAtLine)];
            this.renderer.disposeTextLayout(textLayout);
        }
        if (this.caretOffset > offsetAtLine) {
            this.setCaretOffset(offsetAtLine, 1);
            this.showCaret();
        }
    }
    
    void doLineUp(final boolean b) {
        int caretLine = this.getCaretLine();
        int y = 0;
        boolean b2 = false;
        if (this.wordWrap) {
            final int n = this.caretOffset - this.content.getOffsetAtLine(caretLine);
            final TextLayout textLayout = this.renderer.getTextLayout(caretLine);
            final int visualLineIndex = this.getVisualLineIndex(textLayout, n);
            if (visualLineIndex == 0) {
                b2 = (caretLine == 0);
                if (!b2) {
                    --caretLine;
                    y = this.renderer.getLineHeight(caretLine) - 1;
                }
            }
            else {
                y = textLayout.getLineBounds(visualLineIndex - 1).y;
            }
            this.renderer.disposeTextLayout(textLayout);
        }
        else {
            b2 = (caretLine == 0);
            --caretLine;
        }
        if (b2) {
            if (b) {
                this.setCaretOffset(0, -1);
            }
        }
        else {
            final int[] array = { 0 };
            this.setCaretOffset(this.getOffsetAtPoint(this.columnX, y, caretLine, array), array[0]);
        }
        final int columnX = this.columnX;
        final int horizontalScrollOffset = this.horizontalScrollOffset;
        if (b) {
            this.setMouseWordSelectionAnchor();
        }
        this.showCaret();
        if (b) {
            this.doSelection(16777219);
        }
        this.columnX = columnX + (horizontalScrollOffset - this.horizontalScrollOffset);
    }
    
    void doMouseLinkCursor() {
        final Display display = this.getDisplay();
        final Point map = display.map(null, this, display.getCursorLocation());
        this.doMouseLinkCursor(map.x, map.y);
    }
    
    void doMouseLinkCursor(final int n, final int n2) {
        final int offsetAtPoint = this.getOffsetAtPoint(n, n2, null, true);
        final Display display = this.getDisplay();
        Cursor cursor = this.cursor;
        if (this.renderer.hasLink(offsetAtPoint)) {
            cursor = display.getSystemCursor(21);
        }
        else if (this.cursor == null) {
            cursor = display.getSystemCursor(this.blockSelection ? 2 : 19);
        }
        if (cursor != this.getCursor()) {
            super.setCursor(cursor);
        }
    }
    
    void doMouseLocationChange(int max, int max2, final boolean b) {
        final int lineIndex = this.getLineIndex(max2);
        this.updateCaretDirection = true;
        if (this.blockSelection) {
            max = Math.max(this.leftMargin, Math.min(max, this.clientAreaWidth - this.rightMargin));
            max2 = Math.max(this.topMargin, Math.min(max2, this.clientAreaHeight - this.bottomMargin));
            if (this.doubleClickEnabled && this.clickCount > 1) {
                if ((this.clickCount & 0x1) == 0x0) {
                    final Point pointAtOffset = this.getPointAtOffset(this.doubleClickSelection.x);
                    final int[] array = { 0 };
                    final int offsetAtPoint = this.getOffsetAtPoint(max, max2, array, true);
                    if (offsetAtPoint != -1) {
                        if (max > pointAtOffset.x) {
                            this.setBlockSelectionOffset(this.doubleClickSelection.x, this.getWordNext(offsetAtPoint + array[0], 8), true);
                        }
                        else {
                            this.setBlockSelectionOffset(this.doubleClickSelection.y, this.getWordPrevious(offsetAtPoint + array[0], 16), true);
                        }
                    }
                    else if (max > pointAtOffset.x) {
                        this.setBlockSelectionLocation(pointAtOffset.x, pointAtOffset.y, max, max2, true);
                    }
                    else {
                        final Point pointAtOffset2 = this.getPointAtOffset(this.doubleClickSelection.y);
                        this.setBlockSelectionLocation(pointAtOffset2.x, pointAtOffset2.y, max, max2, true);
                    }
                }
                else {
                    this.setBlockSelectionLocation(this.blockXLocation, max2, true);
                }
                return;
            }
            if (b) {
                if (this.blockXLocation == -1) {
                    this.setBlockSelectionOffset(this.caretOffset, false);
                }
            }
            else {
                this.clearBlockSelection(true, false);
            }
            final int[] array2 = { 0 };
            final int offsetAtPoint2 = this.getOffsetAtPoint(max, max2, array2, true);
            if (offsetAtPoint2 == -1) {
                if (this.isFixedLineHeight() && this.renderer.fixedPitch) {
                    final int averageCharWidth = this.renderer.averageCharWidth;
                    max = (max + averageCharWidth / 2 - this.leftMargin + this.horizontalScrollOffset) / averageCharWidth * averageCharWidth + this.leftMargin - this.horizontalScrollOffset;
                }
                this.setBlockSelectionLocation(max, max2, true);
                return;
            }
            if (b) {
                this.setBlockSelectionOffset(offsetAtPoint2 + array2[0], true);
                return;
            }
        }
        if (lineIndex < 0 || (this.isSingleLine() && lineIndex > 0)) {
            return;
        }
        final int[] array3 = { 0 };
        int n = this.getOffsetAtPoint(max, max2, array3);
        final int n2 = array3[0];
        if (this.doubleClickEnabled && this.clickCount > 1) {
            n = this.doMouseWordSelect(max, n, lineIndex);
        }
        final int lineAtOffset = this.content.getLineAtOffset(n);
        final boolean b2 = (max2 >= 0 && max2 < this.clientAreaHeight) || lineAtOffset == 0 || lineAtOffset == this.content.getLineCount() - 1;
        final boolean b3 = (max >= 0 && max < this.clientAreaWidth) || this.wordWrap || lineAtOffset != this.content.getLineAtOffset(this.caretOffset);
        if (b2 && b3 && (n != this.caretOffset || n2 != this.caretAlignment)) {
            this.setCaretOffset(n, n2);
            if (b) {
                this.doMouseSelection();
            }
            this.showCaret();
        }
        if (!b) {
            this.setCaretOffset(n, n2);
            this.clearSelection(true);
        }
    }
    
    void doMouseSelection() {
        if (this.caretOffset <= this.selection.x || (this.caretOffset > this.selection.x && this.caretOffset < this.selection.y && this.selectionAnchor == this.selection.x)) {
            this.doSelection(16777219);
        }
        else {
            this.doSelection(16777220);
        }
    }
    
    int doMouseWordSelect(final int n, int n2, final int n3) {
        if (n2 < this.selectionAnchor && this.selectionAnchor == this.selection.x) {
            this.selectionAnchor = this.doubleClickSelection.y;
        }
        else if (n2 > this.selectionAnchor && this.selectionAnchor == this.selection.y) {
            this.selectionAnchor = this.doubleClickSelection.x;
        }
        if (n >= 0 && n < this.clientAreaWidth) {
            final boolean b = (this.clickCount & 0x1) == 0x0;
            if (this.caretOffset == this.selection.x) {
                if (b) {
                    n2 = this.getWordPrevious(n2, 16);
                }
                else {
                    n2 = this.content.getOffsetAtLine(n3);
                }
            }
            else if (b) {
                n2 = this.getWordNext(n2, 8);
            }
            else {
                int n4 = this.content.getCharCount();
                if (n3 + 1 < this.content.getLineCount()) {
                    n4 = this.content.getOffsetAtLine(n3 + 1);
                }
                n2 = n4;
            }
        }
        return n2;
    }
    
    void doPageDown(final boolean b, int availableHeightBellow) {
        if (this.isSingleLine()) {
            return;
        }
        final int columnX = this.columnX;
        final int horizontalScrollOffset = this.horizontalScrollOffset;
        if (this.isFixedLineHeight()) {
            final int lineCount = this.content.getLineCount();
            final int caretLine = this.getCaretLine();
            if (caretLine < lineCount - 1) {
                final int max = Math.max(1, Math.min(lineCount - caretLine - 1, ((availableHeightBellow == -1) ? this.clientAreaHeight : availableHeightBellow) / this.renderer.getLineHeight()));
                final int[] array = { 0 };
                this.setCaretOffset(this.getOffsetAtPoint(this.columnX, this.getLinePixel(caretLine + max), array), array[0]);
                if (b) {
                    this.doSelection(16777220);
                }
                final int n = lineCount * this.getVerticalIncrement();
                final int clientAreaHeight = this.clientAreaHeight;
                final int verticalScrollOffset = this.getVerticalScrollOffset();
                int n2 = verticalScrollOffset + max * this.getVerticalIncrement();
                if (n2 + clientAreaHeight > n) {
                    n2 = n - clientAreaHeight;
                }
                if (n2 > verticalScrollOffset) {
                    this.scrollVertical(n2 - verticalScrollOffset, true);
                }
            }
        }
        else {
            final int lineCount2 = this.content.getLineCount();
            final int caretLine2 = this.getCaretLine();
            if (availableHeightBellow == -1) {
                final int partialBottomIndex = this.getPartialBottomIndex();
                final int linePixel = this.getLinePixel(partialBottomIndex);
                final int lineHeight = this.renderer.getLineHeight(partialBottomIndex);
                availableHeightBellow = linePixel;
                if (linePixel + lineHeight <= this.clientAreaHeight) {
                    availableHeightBellow += lineHeight;
                }
                else if (this.wordWrap) {
                    final TextLayout textLayout = this.renderer.getTextLayout(partialBottomIndex);
                    final int n3 = this.clientAreaHeight - linePixel;
                    for (int i = 0; i < textLayout.getLineCount(); ++i) {
                        final Rectangle lineBounds = textLayout.getLineBounds(i);
                        if (lineBounds.contains(lineBounds.x, n3)) {
                            availableHeightBellow += lineBounds.y;
                            break;
                        }
                    }
                    this.renderer.disposeTextLayout(textLayout);
                }
            }
            else {
                final int lineIndex = this.getLineIndex(availableHeightBellow);
                final int linePixel2 = this.getLinePixel(lineIndex);
                if (this.wordWrap) {
                    final TextLayout textLayout2 = this.renderer.getTextLayout(lineIndex);
                    final int n4 = availableHeightBellow - linePixel2;
                    for (int j = 0; j < textLayout2.getLineCount(); ++j) {
                        final Rectangle lineBounds2 = textLayout2.getLineBounds(j);
                        if (lineBounds2.contains(lineBounds2.x, n4)) {
                            availableHeightBellow = linePixel2 + lineBounds2.y + lineBounds2.height;
                            break;
                        }
                    }
                    this.renderer.disposeTextLayout(textLayout2);
                }
                else {
                    availableHeightBellow = linePixel2 + this.renderer.getLineHeight(lineIndex);
                }
            }
            int n5 = availableHeightBellow;
            if (this.wordWrap) {
                final TextLayout textLayout3 = this.renderer.getTextLayout(caretLine2);
                n5 += textLayout3.getLineBounds(this.getVisualLineIndex(textLayout3, this.caretOffset - this.content.getOffsetAtLine(caretLine2))).y;
                this.renderer.disposeTextLayout(textLayout3);
            }
            int n6 = caretLine2;
            for (int n7 = this.renderer.getLineHeight(n6); n5 - n7 >= 0 && n6 < lineCount2 - 1; n5 -= n7, n7 = this.renderer.getLineHeight(++n6)) {}
            final int[] array2 = { 0 };
            this.setCaretOffset(this.getOffsetAtPoint(this.columnX, n5, n6, array2), array2[0]);
            if (b) {
                this.doSelection(16777220);
            }
            availableHeightBellow = this.getAvailableHeightBellow(availableHeightBellow);
            this.scrollVertical(availableHeightBellow, true);
            if (availableHeightBellow == 0) {
                this.setCaretLocation();
            }
        }
        this.showCaret();
        this.columnX = columnX + (horizontalScrollOffset - this.horizontalScrollOffset);
    }
    
    void doPageEnd() {
        if (this.isSingleLine()) {
            this.doLineEnd();
        }
        else {
            int n2;
            if (this.wordWrap) {
                final int partialBottomIndex = this.getPartialBottomIndex();
                final TextLayout textLayout = this.renderer.getTextLayout(partialBottomIndex);
                final int n = this.clientAreaHeight - this.bottomMargin - this.getLinePixel(partialBottomIndex);
                int i;
                for (i = textLayout.getLineCount() - 1; i >= 0; --i) {
                    final Rectangle lineBounds = textLayout.getLineBounds(i);
                    if (n >= lineBounds.y + lineBounds.height) {
                        break;
                    }
                }
                if (i == -1 && partialBottomIndex > 0) {
                    n2 = this.content.getOffsetAtLine(partialBottomIndex - 1) + this.content.getLine(partialBottomIndex - 1).length();
                }
                else {
                    n2 = this.content.getOffsetAtLine(partialBottomIndex) + Math.max(0, textLayout.getLineOffsets()[i + 1] - 1);
                }
                this.renderer.disposeTextLayout(textLayout);
            }
            else {
                final int bottomIndex = this.getBottomIndex();
                n2 = this.content.getOffsetAtLine(bottomIndex) + this.content.getLine(bottomIndex).length();
            }
            if (this.caretOffset < n2) {
                this.setCaretOffset(n2, 1);
                this.showCaret();
            }
        }
    }
    
    void doPageStart() {
        int n3;
        if (this.wordWrap) {
            int topIndex;
            int n;
            if (this.topIndexY > 0) {
                topIndex = this.topIndex - 1;
                n = this.renderer.getLineHeight(topIndex) - this.topIndexY;
            }
            else {
                topIndex = this.topIndex;
                n = -this.topIndexY;
            }
            TextLayout textLayout;
            int n2;
            int lineCount;
            for (textLayout = this.renderer.getTextLayout(topIndex), n2 = 0, lineCount = textLayout.getLineCount(); n2 < lineCount && n > textLayout.getLineBounds(n2).y; ++n2) {}
            if (n2 == lineCount) {
                n3 = this.content.getOffsetAtLine(topIndex + 1);
            }
            else {
                n3 = this.content.getOffsetAtLine(topIndex) + textLayout.getLineOffsets()[n2];
            }
            this.renderer.disposeTextLayout(textLayout);
        }
        else {
            n3 = this.content.getOffsetAtLine(this.topIndex);
        }
        if (this.caretOffset > n3) {
            this.setCaretOffset(n3, 1);
            this.showCaret();
        }
    }
    
    void doPageUp(final boolean b, int n) {
        if (this.isSingleLine()) {
            return;
        }
        final int horizontalScrollOffset = this.horizontalScrollOffset;
        final int columnX = this.columnX;
        if (this.isFixedLineHeight()) {
            final int caretLine = this.getCaretLine();
            if (caretLine > 0) {
                final int max = Math.max(1, Math.min(caretLine, ((n == -1) ? this.clientAreaHeight : n) / this.renderer.getLineHeight()));
                final int n2 = caretLine - max;
                final int[] array = { 0 };
                this.setCaretOffset(this.getOffsetAtPoint(this.columnX, this.getLinePixel(n2), array), array[0]);
                if (b) {
                    this.doSelection(16777219);
                }
                final int verticalScrollOffset = this.getVerticalScrollOffset();
                final int max2 = Math.max(0, verticalScrollOffset - max * this.getVerticalIncrement());
                if (max2 < verticalScrollOffset) {
                    this.scrollVertical(max2 - verticalScrollOffset, true);
                }
            }
        }
        else {
            final int caretLine2 = this.getCaretLine();
            if (n == -1) {
                if (this.topIndexY == 0) {
                    n = this.clientAreaHeight;
                }
                else {
                    int topIndex;
                    int n3;
                    int n4;
                    if (this.topIndex > 0) {
                        topIndex = this.topIndex - 1;
                        n3 = this.renderer.getLineHeight(topIndex);
                        n = this.clientAreaHeight - this.topIndexY;
                        n4 = n3 - this.topIndexY;
                    }
                    else {
                        topIndex = this.topIndex;
                        n3 = this.renderer.getLineHeight(topIndex);
                        n = this.clientAreaHeight - (n3 + this.topIndexY);
                        n4 = -this.topIndexY;
                    }
                    if (this.wordWrap) {
                        final TextLayout textLayout = this.renderer.getTextLayout(topIndex);
                        for (int i = 0; i < textLayout.getLineCount(); ++i) {
                            final Rectangle lineBounds = textLayout.getLineBounds(i);
                            if (lineBounds.contains(lineBounds.x, n4)) {
                                n += n3 - (lineBounds.y + lineBounds.height);
                                break;
                            }
                        }
                        this.renderer.disposeTextLayout(textLayout);
                    }
                }
            }
            else {
                final int lineIndex = this.getLineIndex(this.clientAreaHeight - n);
                final int linePixel = this.getLinePixel(lineIndex);
                if (this.wordWrap) {
                    final TextLayout textLayout2 = this.renderer.getTextLayout(lineIndex);
                    final int n5 = linePixel;
                    for (int j = 0; j < textLayout2.getLineCount(); ++j) {
                        final Rectangle lineBounds2 = textLayout2.getLineBounds(j);
                        if (lineBounds2.contains(lineBounds2.x, n5)) {
                            n = this.clientAreaHeight - (linePixel + lineBounds2.y);
                            break;
                        }
                    }
                    this.renderer.disposeTextLayout(textLayout2);
                }
                else {
                    n = this.clientAreaHeight - linePixel;
                }
            }
            int n6 = n;
            if (this.wordWrap) {
                final TextLayout textLayout3 = this.renderer.getTextLayout(caretLine2);
                n6 += textLayout3.getBounds().height - textLayout3.getLineBounds(this.getVisualLineIndex(textLayout3, this.caretOffset - this.content.getOffsetAtLine(caretLine2))).y;
                this.renderer.disposeTextLayout(textLayout3);
            }
            int n7 = caretLine2;
            for (int n8 = this.renderer.getLineHeight(n7); n6 - n8 >= 0 && n7 > 0; n6 -= n8, n8 = this.renderer.getLineHeight(--n7)) {}
            final int lineHeight = this.renderer.getLineHeight(n7);
            final int[] array2 = { 0 };
            this.setCaretOffset(this.getOffsetAtPoint(this.columnX, lineHeight - n6, n7, array2), array2[0]);
            if (b) {
                this.doSelection(16777219);
            }
            n = this.getAvailableHeightAbove(n);
            this.scrollVertical(-n, true);
            if (n == 0) {
                this.setCaretLocation();
            }
        }
        this.showCaret();
        this.columnX = columnX + (horizontalScrollOffset - this.horizontalScrollOffset);
    }
    
    void doSelection(final int n) {
        int n2 = -1;
        int n3 = -1;
        if (this.selectionAnchor == -1) {
            this.selectionAnchor = this.selection.x;
        }
        if (n == 16777219) {
            if (this.caretOffset < this.selection.x) {
                n3 = this.selection.x;
                final Point selection = this.selection;
                final int caretOffset = this.caretOffset;
                selection.x = caretOffset;
                n2 = caretOffset;
                if (this.selection.y != this.selectionAnchor) {
                    n3 = this.selection.y;
                    this.selection.y = this.selectionAnchor;
                }
            }
            else if (this.selectionAnchor == this.selection.x && this.caretOffset < this.selection.y) {
                n3 = this.selection.y;
                final Point selection2 = this.selection;
                final int caretOffset2 = this.caretOffset;
                selection2.y = caretOffset2;
                n2 = caretOffset2;
            }
        }
        else if (this.caretOffset > this.selection.y) {
            n2 = this.selection.y;
            final Point selection3 = this.selection;
            final int caretOffset3 = this.caretOffset;
            selection3.y = caretOffset3;
            n3 = caretOffset3;
            if (this.selection.x != this.selectionAnchor) {
                n2 = this.selection.x;
                this.selection.x = this.selectionAnchor;
            }
        }
        else if (this.selectionAnchor == this.selection.y && this.caretOffset > this.selection.x) {
            n2 = this.selection.x;
            final Point selection4 = this.selection;
            final int caretOffset4 = this.caretOffset;
            selection4.x = caretOffset4;
            n3 = caretOffset4;
        }
        if (n2 != -1 && n3 != -1) {
            this.internalRedrawRange(n2, n3 - n2);
            this.sendSelectionEvent();
        }
        this.sendAccessibleTextCaretMoved();
    }
    
    void doSelectionCursorNext() {
        int caretLine = this.getCaretLine();
        final int offsetAtLine = this.content.getOffsetAtLine(caretLine);
        final int n = this.caretOffset - offsetAtLine;
        if (n < this.content.getLine(caretLine).length()) {
            final TextLayout textLayout = this.renderer.getTextLayout(caretLine);
            final int nextOffset = textLayout.getNextOffset(n, 2);
            final int n2 = textLayout.getLineOffsets()[textLayout.getLineIndex(nextOffset)];
            this.renderer.disposeTextLayout(textLayout);
            this.setCaretOffset(nextOffset + offsetAtLine, (nextOffset == n2) ? 1 : 0);
            this.showCaret();
        }
        else if (caretLine < this.content.getLineCount() - 1 && !this.isSingleLine()) {
            ++caretLine;
            this.setCaretOffset(this.content.getOffsetAtLine(caretLine), 0);
            this.showCaret();
        }
    }
    
    void doSelectionCursorPrevious() {
        int caretLine = this.getCaretLine();
        if (this.caretOffset - this.content.getOffsetAtLine(caretLine) > 0) {
            this.setCaretOffset(this.getClusterPrevious(this.caretOffset, caretLine), 1);
            this.showCaret();
        }
        else if (caretLine > 0) {
            --caretLine;
            this.setCaretOffset(this.content.getOffsetAtLine(caretLine) + this.content.getLine(caretLine).length(), 1);
            this.showCaret();
        }
    }
    
    void doSelectionLineDown() {
        final int x = this.getPointAtOffset(this.caretOffset).x;
        this.columnX = x;
        final int columnX = x;
        this.doLineDown(true);
        this.columnX = columnX;
    }
    
    void doSelectionLineUp() {
        final int x = this.getPointAtOffset(this.caretOffset).x;
        this.columnX = x;
        final int columnX = x;
        this.doLineUp(true);
        this.columnX = columnX;
    }
    
    void doSelectionPageDown(final int n) {
        final int x = this.getPointAtOffset(this.caretOffset).x;
        this.columnX = x;
        final int columnX = x;
        this.doPageDown(true, n);
        this.columnX = columnX;
    }
    
    void doSelectionPageUp(final int n) {
        final int x = this.getPointAtOffset(this.caretOffset).x;
        this.columnX = x;
        final int columnX = x;
        this.doPageUp(true, n);
        this.columnX = columnX;
    }
    
    void doSelectionWordNext() {
        final int wordNext = this.getWordNext(this.caretOffset, 4);
        if (!this.isSingleLine() || this.content.getLineAtOffset(this.caretOffset) == this.content.getLineAtOffset(wordNext)) {
            this.setCaretOffset(wordNext, 1);
            this.showCaret();
        }
    }
    
    void doSelectionWordPrevious() {
        this.setCaretOffset(this.getWordPrevious(this.caretOffset, 4), 1);
        this.showCaret();
    }
    
    void doVisualPrevious() {
        this.setCaretOffset(this.getClusterPrevious(this.caretOffset, this.getCaretLine()), -1);
        this.showCaret();
    }
    
    void doVisualNext() {
        this.setCaretOffset(this.getClusterNext(this.caretOffset, this.getCaretLine()), -1);
        this.showCaret();
    }
    
    void doWordNext() {
        if (this.selection.y - this.selection.x > 0) {
            this.setCaretOffset(this.selection.y, -1);
            this.showCaret();
        }
        else {
            this.doSelectionWordNext();
        }
    }
    
    void doWordPrevious() {
        if (this.selection.y - this.selection.x > 0) {
            this.setCaretOffset(this.selection.x, -1);
            this.showCaret();
        }
        else {
            this.doSelectionWordPrevious();
        }
    }
    
    void endAutoScroll() {
        this.autoScrollDirection = 0;
    }
    
    public Color getBackground() {
        this.checkWidget();
        if (this.background == null) {
            return this.getDisplay().getSystemColor(25);
        }
        return this.background;
    }
    
    public int getBaseline() {
        this.checkWidget();
        return this.renderer.getBaseline();
    }
    
    public int getBaseline(final int n) {
        this.checkWidget();
        if (n < 0 || n > this.content.getCharCount()) {
            SWT.error(6);
        }
        if (this.isFixedLineHeight()) {
            return this.renderer.getBaseline();
        }
        final int lineAtOffset = this.content.getLineAtOffset(n);
        final int offsetAtLine = this.content.getOffsetAtLine(lineAtOffset);
        final TextLayout textLayout = this.renderer.getTextLayout(lineAtOffset);
        final FontMetrics lineMetrics = textLayout.getLineMetrics(textLayout.getLineIndex(Math.min(n - offsetAtLine, textLayout.getText().length())));
        this.renderer.disposeTextLayout(textLayout);
        return lineMetrics.getAscent() + lineMetrics.getLeading();
    }
    
    public boolean getBidiColoring() {
        this.checkWidget();
        return this.bidiColoring;
    }
    
    public boolean getBlockSelection() {
        this.checkWidget();
        return this.blockSelection;
    }
    
    Rectangle getBlockSelectionPosition() {
        int lineIndex = this.getLineIndex(this.blockYAnchor - this.getVerticalScrollOffset());
        int lineIndex2 = this.getLineIndex(this.blockYLocation - this.getVerticalScrollOffset());
        if (lineIndex > lineIndex2) {
            final int n = lineIndex;
            lineIndex = lineIndex2;
            lineIndex2 = n;
        }
        int n2 = this.blockXAnchor;
        int n3 = this.blockXLocation;
        if (n2 > n3) {
            n2 = this.blockXLocation;
            n3 = this.blockXAnchor;
        }
        return new Rectangle(n2 - this.horizontalScrollOffset, lineIndex, n3 - this.horizontalScrollOffset, lineIndex2);
    }
    
    public Rectangle getBlockSelectionBounds() {
        Rectangle blockSelectionRectangle;
        if (this.blockSelection && this.blockXLocation != -1) {
            blockSelectionRectangle = this.getBlockSelectionRectangle();
        }
        else {
            final Point pointAtOffset = this.getPointAtOffset(this.selection.x);
            final Point pointAtOffset2 = this.getPointAtOffset(this.selection.y);
            blockSelectionRectangle = new Rectangle(pointAtOffset.x, pointAtOffset.y, pointAtOffset2.x - pointAtOffset.x, pointAtOffset2.y + this.getLineHeight(this.selection.y) - pointAtOffset.y);
            if (this.selection.x == this.selection.y) {
                blockSelectionRectangle.width = this.getCaretWidth();
            }
        }
        final Rectangle rectangle = blockSelectionRectangle;
        rectangle.x += this.horizontalScrollOffset;
        final Rectangle rectangle2 = blockSelectionRectangle;
        rectangle2.y += this.getVerticalScrollOffset();
        return blockSelectionRectangle;
    }
    
    Rectangle getBlockSelectionRectangle() {
        final Rectangle blockSelectionPosition = this.getBlockSelectionPosition();
        blockSelectionPosition.y = this.getLinePixel(blockSelectionPosition.y);
        blockSelectionPosition.width -= blockSelectionPosition.x;
        blockSelectionPosition.height = this.getLinePixel(blockSelectionPosition.height + 1) - blockSelectionPosition.y;
        return blockSelectionPosition;
    }
    
    String getBlockSelectionText(final String s) {
        final Rectangle blockSelectionPosition = this.getBlockSelectionPosition();
        final int y = blockSelectionPosition.y;
        final int height = blockSelectionPosition.height;
        final int x = blockSelectionPosition.x;
        final int width = blockSelectionPosition.width;
        final StringBuffer sb = new StringBuffer();
        for (int i = y; i <= height; ++i) {
            int offsetAtPoint = this.getOffsetAtPoint(x, 0, i, null);
            int offsetAtPoint2 = this.getOffsetAtPoint(width, 0, i, null);
            if (offsetAtPoint > offsetAtPoint2) {
                final int n = offsetAtPoint;
                offsetAtPoint = offsetAtPoint2;
                offsetAtPoint2 = n;
            }
            sb.append(this.content.getTextRange(offsetAtPoint, offsetAtPoint2 - offsetAtPoint));
            if (i < height) {
                sb.append(s);
            }
        }
        return sb.toString();
    }
    
    int getBottomIndex() {
        int n2;
        if (this.isFixedLineHeight()) {
            int n = 1;
            final int lineHeight = this.renderer.getLineHeight();
            if (lineHeight != 0) {
                n = (this.clientAreaHeight - (this.topIndex * lineHeight - this.getVerticalScrollOffset())) / lineHeight;
            }
            n2 = Math.min(this.content.getLineCount() - 1, this.topIndex + Math.max(0, n - 1));
        }
        else {
            final int n3 = this.clientAreaHeight - this.bottomMargin;
            n2 = this.getLineIndex(n3);
            if (n2 > 0 && this.getLinePixel(n2) + this.renderer.getLineHeight(n2) > n3 && this.getLinePixel(n2 - 1) >= this.topMargin) {
                --n2;
            }
        }
        return n2;
    }
    
    public int getBottomMargin() {
        this.checkWidget();
        return this.bottomMargin;
    }
    
    Rectangle getBoundsAtOffset(final int n) {
        final int lineAtOffset = this.content.getLineAtOffset(n);
        final int offsetAtLine = this.content.getOffsetAtLine(lineAtOffset);
        final String line = this.content.getLine(lineAtOffset);
        Rectangle bounds;
        if (line.length() != 0) {
            final int n2 = n - offsetAtLine;
            final TextLayout textLayout = this.renderer.getTextLayout(lineAtOffset);
            bounds = textLayout.getBounds(n2, n2);
            this.renderer.disposeTextLayout(textLayout);
        }
        else {
            bounds = new Rectangle(0, 0, 0, this.renderer.getLineHeight());
        }
        if (n == this.caretOffset && !this.wordWrap && n == offsetAtLine + line.length()) {
            final Rectangle rectangle = bounds;
            rectangle.width += this.getCaretWidth();
        }
        final Rectangle rectangle2 = bounds;
        rectangle2.x += this.leftMargin - this.horizontalScrollOffset;
        final Rectangle rectangle3 = bounds;
        rectangle3.y += this.getLinePixel(lineAtOffset);
        return bounds;
    }
    
    public int getCaretOffset() {
        this.checkWidget();
        return this.caretOffset;
    }
    
    int getCaretWidth() {
        final Caret caret = this.getCaret();
        if (caret == null) {
            return 0;
        }
        return caret.getSize().x;
    }
    
    Object getClipboardContent(final int n) {
        return this.clipboard.getContents(TextTransfer.getInstance(), n);
    }
    
    int getClusterNext(int nextOffset, final int n) {
        final int offsetAtLine = this.content.getOffsetAtLine(n);
        final TextLayout textLayout = this.renderer.getTextLayout(n);
        nextOffset -= offsetAtLine;
        nextOffset = textLayout.getNextOffset(nextOffset, 2);
        nextOffset += offsetAtLine;
        this.renderer.disposeTextLayout(textLayout);
        return nextOffset;
    }
    
    int getClusterPrevious(int previousOffset, final int n) {
        final int offsetAtLine = this.content.getOffsetAtLine(n);
        final TextLayout textLayout = this.renderer.getTextLayout(n);
        previousOffset -= offsetAtLine;
        previousOffset = textLayout.getPreviousOffset(previousOffset, 2);
        previousOffset += offsetAtLine;
        this.renderer.disposeTextLayout(textLayout);
        return previousOffset;
    }
    
    public StyledTextContent getContent() {
        this.checkWidget();
        return this.content;
    }
    
    public boolean getDragDetect() {
        this.checkWidget();
        return this.dragDetect;
    }
    
    public boolean getDoubleClickEnabled() {
        this.checkWidget();
        return this.doubleClickEnabled;
    }
    
    public boolean getEditable() {
        this.checkWidget();
        return this.editable;
    }
    
    public Color getForeground() {
        this.checkWidget();
        if (this.foreground == null) {
            return this.getDisplay().getSystemColor(24);
        }
        return this.foreground;
    }
    
    int getHorizontalIncrement() {
        return this.renderer.averageCharWidth;
    }
    
    public int getHorizontalIndex() {
        this.checkWidget();
        return this.horizontalScrollOffset / this.getHorizontalIncrement();
    }
    
    public int getHorizontalPixel() {
        this.checkWidget();
        return this.horizontalScrollOffset;
    }
    
    public int getIndent() {
        this.checkWidget();
        return this.indent;
    }
    
    public boolean getJustify() {
        this.checkWidget();
        return this.justify;
    }
    
    public int getKeyBinding(final int n) {
        this.checkWidget();
        final Integer n2 = this.keyActionMap.get(new Integer(n));
        return (n2 == null) ? 0 : n2;
    }
    
    public int getCharCount() {
        this.checkWidget();
        return this.content.getCharCount();
    }
    
    public String getLine(final int n) {
        this.checkWidget();
        if (n < 0 || (n > 0 && n >= this.content.getLineCount())) {
            SWT.error(6);
        }
        return this.content.getLine(n);
    }
    
    public int getLineAlignment(final int n) {
        this.checkWidget();
        if (n < 0 || n > this.content.getLineCount()) {
            SWT.error(5);
        }
        return this.renderer.getLineAlignment(n, this.alignment);
    }
    
    public int getLineAtOffset(final int n) {
        this.checkWidget();
        if (n < 0 || n > this.getCharCount()) {
            SWT.error(6);
        }
        return this.content.getLineAtOffset(n);
    }
    
    public Color getLineBackground(final int n) {
        this.checkWidget();
        if (n < 0 || n > this.content.getLineCount()) {
            SWT.error(5);
        }
        return this.isListening(3001) ? null : this.renderer.getLineBackground(n, null);
    }
    
    public Bullet getLineBullet(final int n) {
        this.checkWidget();
        if (n < 0 || n > this.content.getLineCount()) {
            SWT.error(5);
        }
        return this.isListening(3002) ? null : this.renderer.getLineBullet(n, null);
    }
    
    StyledTextEvent getLineBackgroundData(final int n, final String s) {
        return this.sendLineEvent(3001, n, s);
    }
    
    public int getLineCount() {
        this.checkWidget();
        return this.content.getLineCount();
    }
    
    int getLineCountWhole() {
        if (this.isFixedLineHeight()) {
            final int lineHeight = this.renderer.getLineHeight();
            return (lineHeight != 0) ? (this.clientAreaHeight / lineHeight) : 1;
        }
        return this.getBottomIndex() - this.topIndex + 1;
    }
    
    public String getLineDelimiter() {
        this.checkWidget();
        return this.content.getLineDelimiter();
    }
    
    public int getLineHeight() {
        this.checkWidget();
        return this.renderer.getLineHeight();
    }
    
    public int getLineHeight(final int n) {
        this.checkWidget();
        if (n < 0 || n > this.content.getCharCount()) {
            SWT.error(6);
        }
        if (this.isFixedLineHeight()) {
            return this.renderer.getLineHeight();
        }
        final int lineAtOffset = this.content.getLineAtOffset(n);
        final int offsetAtLine = this.content.getOffsetAtLine(lineAtOffset);
        final TextLayout textLayout = this.renderer.getTextLayout(lineAtOffset);
        final int height = textLayout.getLineBounds(textLayout.getLineIndex(Math.min(n - offsetAtLine, textLayout.getText().length()))).height;
        this.renderer.disposeTextLayout(textLayout);
        return height;
    }
    
    public int getLineIndent(final int n) {
        this.checkWidget();
        if (n < 0 || n > this.content.getLineCount()) {
            SWT.error(5);
        }
        return this.isListening(3002) ? 0 : this.renderer.getLineIndent(n, this.indent);
    }
    
    public boolean getLineJustify(final int n) {
        this.checkWidget();
        if (n < 0 || n > this.content.getLineCount()) {
            SWT.error(5);
        }
        return !this.isListening(3002) && this.renderer.getLineJustify(n, this.justify);
    }
    
    public int getLineSpacing() {
        this.checkWidget();
        return this.lineSpacing;
    }
    
    StyledTextEvent getLineStyleData(final int n, final String s) {
        return this.sendLineEvent(3002, n, s);
    }
    
    public int getLinePixel(int max) {
        this.checkWidget();
        max = Math.max(0, Math.min(this.content.getLineCount(), max));
        if (this.isFixedLineHeight()) {
            return max * this.renderer.getLineHeight() - this.getVerticalScrollOffset() + this.topMargin;
        }
        if (max == this.topIndex) {
            return this.topIndexY + this.topMargin;
        }
        int topIndexY = this.topIndexY;
        if (max > this.topIndex) {
            for (int i = this.topIndex; i < max; ++i) {
                topIndexY += this.renderer.getLineHeight(i);
            }
        }
        else {
            for (int j = this.topIndex - 1; j >= max; --j) {
                topIndexY -= this.renderer.getLineHeight(j);
            }
        }
        return topIndexY + this.topMargin;
    }
    
    public int getLineIndex(int i) {
        this.checkWidget();
        i -= this.topMargin;
        if (this.isFixedLineHeight()) {
            return Math.max(0, Math.min(this.content.getLineCount() - 1, (i + this.getVerticalScrollOffset()) / this.renderer.getLineHeight()));
        }
        if (i == this.topIndexY) {
            return this.topIndex;
        }
        int topIndex = this.topIndex;
        if (i < this.topIndexY) {
            while (i < this.topIndexY) {
                if (topIndex <= 0) {
                    break;
                }
                i += this.renderer.getLineHeight(--topIndex);
            }
        }
        else {
            for (int lineCount = this.content.getLineCount(), n = this.renderer.getLineHeight(topIndex); i - n >= this.topIndexY && topIndex < lineCount - 1; i -= n, n = this.renderer.getLineHeight(++topIndex)) {}
        }
        return topIndex;
    }
    
    public int[] getLineTabStops(final int n) {
        this.checkWidget();
        if (n < 0 || n > this.content.getLineCount()) {
            SWT.error(5);
        }
        if (this.isListening(3002)) {
            return null;
        }
        int[] array = this.renderer.getLineTabStops(n, null);
        if (array == null) {
            array = this.tabs;
        }
        if (array == null) {
            return new int[] { this.renderer.tabWidth };
        }
        final int[] array2 = new int[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    public int getLineWrapIndent(final int n) {
        this.checkWidget();
        if (n < 0 || n > this.content.getLineCount()) {
            SWT.error(5);
        }
        return this.isListening(3002) ? 0 : this.renderer.getLineWrapIndent(n, this.wrapIndent);
    }
    
    public int getLeftMargin() {
        this.checkWidget();
        return this.leftMargin - this.alignmentMargin;
    }
    
    public Point getLocationAtOffset(final int n) {
        this.checkWidget();
        if (n < 0 || n > this.getCharCount()) {
            SWT.error(6);
        }
        return this.getPointAtOffset(n);
    }
    
    public int getOffsetAtLine(final int n) {
        this.checkWidget();
        if (n < 0 || (n > 0 && n >= this.content.getLineCount())) {
            SWT.error(6);
        }
        return this.content.getOffsetAtLine(n);
    }
    
    public int getOffsetAtLocation(final Point point) {
        this.checkWidget();
        if (point == null) {
            SWT.error(4);
        }
        final int[] array = { 0 };
        final int offsetAtPoint = this.getOffsetAtPoint(point.x, point.y, array, true);
        if (offsetAtPoint == -1) {
            SWT.error(5);
        }
        return offsetAtPoint + array[0];
    }
    
    int getOffsetAtPoint(final int n, int n2, final int[] array) {
        final int lineIndex = this.getLineIndex(n2);
        n2 -= this.getLinePixel(lineIndex);
        return this.getOffsetAtPoint(n, n2, lineIndex, array);
    }
    
    int getOffsetAtPoint(int n, final int n2, final int n3, final int[] array) {
        final TextLayout textLayout = this.renderer.getTextLayout(n3);
        n += this.horizontalScrollOffset - this.leftMargin;
        final int[] array2 = { 0 };
        int offset = textLayout.getOffset(n, n2, array2);
        if (array != null) {
            array[0] = 1;
        }
        if (array2[0] != 0) {
            if (offset + array2[0] == textLayout.getLineOffsets()[textLayout.getLineIndex(offset + array2[0])]) {
                offset += array2[0];
                if (array != null) {
                    array[0] = 0;
                }
            }
            else {
                final String line = this.content.getLine(n3);
                int mirrored = 0;
                if (array != null) {
                    int n4;
                    for (n4 = offset; n4 > 0 && Character.isDigit(line.charAt(n4)); --n4) {}
                    if (n4 == 0 && Character.isDigit(line.charAt(n4))) {
                        mirrored = (this.isMirrored() ? 1 : 0);
                    }
                    else {
                        mirrored = (textLayout.getLevel(n4) & 0x1);
                    }
                }
                offset += array2[0];
                if (array != null) {
                    if ((mirrored ^ (textLayout.getLevel(offset) & 0x1)) != 0x0) {
                        array[0] = 0;
                    }
                    else {
                        array[0] = 1;
                    }
                }
            }
        }
        this.renderer.disposeTextLayout(textLayout);
        return offset + this.content.getOffsetAtLine(n3);
    }
    
    int getOffsetAtPoint(int n, int n2, final int[] array, final boolean b) {
        if ((b && n2 + this.getVerticalScrollOffset() < 0) || n + this.horizontalScrollOffset < 0) {
            return -1;
        }
        final int linePixel = this.getLinePixel(this.getPartialBottomIndex() + 1);
        if (b && n2 > linePixel) {
            return -1;
        }
        final int lineIndex = this.getLineIndex(n2);
        final int offsetAtLine = this.content.getOffsetAtLine(lineIndex);
        final TextLayout textLayout = this.renderer.getTextLayout(lineIndex);
        n += this.horizontalScrollOffset - this.leftMargin;
        n2 -= this.getLinePixel(lineIndex);
        final int offset = textLayout.getOffset(n, n2, array);
        final Rectangle lineBounds = textLayout.getLineBounds(textLayout.getLineIndex(offset));
        this.renderer.disposeTextLayout(textLayout);
        if (b && (lineBounds.x > n || n > lineBounds.x + lineBounds.width)) {
            return -1;
        }
        return offset + offsetAtLine;
    }
    
    public int getOrientation() {
        return super.getOrientation();
    }
    
    int getPartialBottomIndex() {
        if (this.isFixedLineHeight()) {
            return Math.max(0, Math.min(this.content.getLineCount(), this.topIndex + Compatibility.ceil(this.clientAreaHeight, this.renderer.getLineHeight())) - 1);
        }
        return this.getLineIndex(this.clientAreaHeight - this.bottomMargin);
    }
    
    int getPartialTopIndex() {
        if (this.isFixedLineHeight()) {
            return this.getVerticalScrollOffset() / this.renderer.getLineHeight();
        }
        return (this.topIndexY <= 0) ? this.topIndex : (this.topIndex - 1);
    }
    
    String getPlatformDelimitedText(final TextWriter textWriter) {
        final int n = textWriter.getStart() + textWriter.getCharCount();
        final int lineAtOffset = this.content.getLineAtOffset(textWriter.getStart());
        final int lineAtOffset2 = this.content.getLineAtOffset(n);
        final String line = this.content.getLine(lineAtOffset2);
        final int offsetAtLine = this.content.getOffsetAtLine(lineAtOffset2);
        for (int i = lineAtOffset; i <= lineAtOffset2; ++i) {
            textWriter.writeLine(this.content.getLine(i), this.content.getOffsetAtLine(i));
            if (i < lineAtOffset2) {
                textWriter.writeLineDelimiter(StyledText.PlatformLineDelimiter);
            }
        }
        if (n > offsetAtLine + line.length()) {
            textWriter.writeLineDelimiter(StyledText.PlatformLineDelimiter);
        }
        textWriter.close();
        return textWriter.toString();
    }
    
    public int[] getRanges() {
        this.checkWidget();
        if (!this.isListening(3002)) {
            final int[] ranges = this.renderer.getRanges(0, this.content.getCharCount());
            if (ranges != null) {
                return ranges;
            }
        }
        return new int[0];
    }
    
    public int[] getRanges(final int n, final int n2) {
        this.checkWidget();
        final int charCount = this.getCharCount();
        final int n3 = n + n2;
        if (n > n3 || n < 0 || n3 > charCount) {
            SWT.error(6);
        }
        if (!this.isListening(3002)) {
            final int[] ranges = this.renderer.getRanges(n, n2);
            if (ranges != null) {
                return ranges;
            }
        }
        return new int[0];
    }
    
    public int getRightMargin() {
        this.checkWidget();
        return this.rightMargin;
    }
    
    public Point getSelection() {
        this.checkWidget();
        return new Point(this.selection.x, this.selection.y);
    }
    
    public Point getSelectionRange() {
        this.checkWidget();
        return new Point(this.selection.x, this.selection.y - this.selection.x);
    }
    
    public int[] getSelectionRanges() {
        this.checkWidget();
        if (this.blockSelection && this.blockXLocation != -1) {
            final Rectangle blockSelectionPosition = this.getBlockSelectionPosition();
            final int y = blockSelectionPosition.y;
            final int height = blockSelectionPosition.height;
            final int x = blockSelectionPosition.x;
            final int width = blockSelectionPosition.width;
            final int[] array = new int[(height - y + 1) * 2];
            int n = 0;
            for (int i = y; i <= height; ++i) {
                int offsetAtPoint = this.getOffsetAtPoint(x, 0, i, null);
                int offsetAtPoint2 = this.getOffsetAtPoint(width, 0, i, null);
                if (offsetAtPoint > offsetAtPoint2) {
                    final int n2 = offsetAtPoint;
                    offsetAtPoint = offsetAtPoint2;
                    offsetAtPoint2 = n2;
                }
                array[n++] = offsetAtPoint;
                array[n++] = offsetAtPoint2 - offsetAtPoint;
            }
            return array;
        }
        return new int[] { this.selection.x, this.selection.y - this.selection.x };
    }
    
    public Color getSelectionBackground() {
        this.checkWidget();
        if (this.selectionBackground == null) {
            return this.getDisplay().getSystemColor(26);
        }
        return this.selectionBackground;
    }
    
    public int getSelectionCount() {
        this.checkWidget();
        if (this.blockSelection && this.blockXLocation != -1) {
            return this.getBlockSelectionText(this.content.getLineDelimiter()).length();
        }
        return this.getSelectionRange().y;
    }
    
    public Color getSelectionForeground() {
        this.checkWidget();
        if (this.selectionForeground == null) {
            return this.getDisplay().getSystemColor(27);
        }
        return this.selectionForeground;
    }
    
    public String getSelectionText() {
        this.checkWidget();
        if (this.blockSelection && this.blockXLocation != -1) {
            return this.getBlockSelectionText(this.content.getLineDelimiter());
        }
        return this.content.getTextRange(this.selection.x, this.selection.y - this.selection.x);
    }
    
    StyledTextEvent getBidiSegments(final int n, final String s) {
        if (!this.isBidi()) {
            return null;
        }
        if (!this.isListening(3007)) {
            final StyledTextEvent styledTextEvent = new StyledTextEvent(this.content);
            styledTextEvent.segments = this.getBidiSegmentsCompatibility(s, n);
            return styledTextEvent;
        }
        final StyledTextEvent sendLineEvent = this.sendLineEvent(3007, n, s);
        if (sendLineEvent == null || sendLineEvent.segments == null || sendLineEvent.segments.length == 0) {
            return null;
        }
        final int length = s.length();
        int[] segments = sendLineEvent.segments;
        final int length2 = segments.length;
        if (sendLineEvent.segmentsChars == null) {
            if (segments[0] != 0) {
                SWT.error(5);
            }
            for (int i = 1; i < length2; ++i) {
                if (segments[i] <= segments[i - 1] || segments[i] > length) {
                    SWT.error(5);
                }
            }
            if (segments[length2 - 1] != length) {
                segments = new int[length2 + 1];
                System.arraycopy(sendLineEvent.segments, 0, segments, 0, length2);
                segments[length2] = length;
            }
            sendLineEvent.segments = segments;
        }
        else {
            for (int j = 1; j < length2; ++j) {
                if (sendLineEvent.segments[j] < sendLineEvent.segments[j - 1] || sendLineEvent.segments[j] > length) {
                    SWT.error(5);
                }
            }
        }
        return sendLineEvent;
    }
    
    int[] getBidiSegmentsCompatibility(final String s, final int n) {
        final int length = s.length();
        if (!this.bidiColoring) {
            return new int[] { 0, length };
        }
        final StyleRange[] array = null;
        final StyledTextEvent lineStyleData = this.getLineStyleData(n, s);
        StyleRange[] array2;
        if (lineStyleData != null) {
            array2 = lineStyleData.styles;
        }
        else {
            array2 = this.renderer.getStyleRanges(n, length, true);
        }
        if (array2 == null || array2.length == 0) {
            return new int[] { 0, length };
        }
        int n2 = 0;
        int n3 = 1;
        while (n2 < array2.length && array2[n2].start == 0 && array2[n2].length == length) {
            ++n2;
        }
        final int[] array3 = new int[(array2.length - n2) * 2 + 2];
        for (int i = n2; i < array2.length; ++i) {
            final StyleRange styleRange = array2[i];
            final int max = Math.max(styleRange.start - n, 0);
            final int min = Math.min(Math.max(styleRange.start + styleRange.length - n, max), s.length());
            if (i > 0 && n3 > 1 && ((max >= array3[n3 - 2] && max <= array3[n3 - 1]) || (min >= array3[n3 - 2] && min <= array3[n3 - 1])) && styleRange.similarTo(array2[i - 1])) {
                array3[n3 - 2] = Math.min(array3[n3 - 2], max);
                array3[n3 - 1] = Math.max(array3[n3 - 1], min);
            }
            else {
                if (max > array3[n3 - 1]) {
                    array3[n3] = max;
                    ++n3;
                }
                array3[n3] = min;
                ++n3;
            }
        }
        if (length > array3[n3 - 1]) {
            array3[n3] = length;
            ++n3;
        }
        if (n3 == array3.length) {
            return array3;
        }
        final int[] array4 = new int[n3];
        System.arraycopy(array3, 0, array4, 0, n3);
        return array4;
    }
    
    public StyleRange getStyleRangeAtOffset(final int n) {
        this.checkWidget();
        if (n < 0 || n >= this.getCharCount()) {
            SWT.error(5);
        }
        if (!this.isListening(3002)) {
            final StyleRange[] styleRanges = this.renderer.getStyleRanges(n, 1, true);
            if (styleRanges != null) {
                return styleRanges[0];
            }
        }
        return null;
    }
    
    public StyleRange[] getStyleRanges() {
        this.checkWidget();
        return this.getStyleRanges(0, this.content.getCharCount(), true);
    }
    
    public StyleRange[] getStyleRanges(final boolean b) {
        this.checkWidget();
        return this.getStyleRanges(0, this.content.getCharCount(), b);
    }
    
    public StyleRange[] getStyleRanges(final int n, final int n2) {
        this.checkWidget();
        return this.getStyleRanges(n, n2, true);
    }
    
    public StyleRange[] getStyleRanges(final int n, final int n2, final boolean b) {
        this.checkWidget();
        final int charCount = this.getCharCount();
        final int n3 = n + n2;
        if (n > n3 || n < 0 || n3 > charCount) {
            SWT.error(6);
        }
        if (!this.isListening(3002)) {
            final StyleRange[] styleRanges = this.renderer.getStyleRanges(n, n2, b);
            if (styleRanges != null) {
                return styleRanges;
            }
        }
        return new StyleRange[0];
    }
    
    public int getTabs() {
        this.checkWidget();
        return this.tabLength;
    }
    
    public int[] getTabStops() {
        this.checkWidget();
        if (this.tabs == null) {
            return new int[] { this.renderer.tabWidth };
        }
        final int[] array = new int[this.tabs.length];
        System.arraycopy(this.tabs, 0, array, 0, this.tabs.length);
        return array;
    }
    
    public String getText() {
        this.checkWidget();
        return this.content.getTextRange(0, this.getCharCount());
    }
    
    public String getText(final int n, final int n2) {
        this.checkWidget();
        final int charCount = this.getCharCount();
        if (n < 0 || n >= charCount || n2 < 0 || n2 >= charCount || n > n2) {
            SWT.error(6);
        }
        return this.content.getTextRange(n, n2 - n + 1);
    }
    
    public Rectangle getTextBounds(final int n, final int n2) {
        this.checkWidget();
        final int charCount = this.getCharCount();
        if (n < 0 || n >= charCount || n2 < 0 || n2 >= charCount || n > n2) {
            SWT.error(6);
        }
        final int lineAtOffset = this.content.getLineAtOffset(n);
        final int lineAtOffset2 = this.content.getLineAtOffset(n2);
        int linePixel = this.getLinePixel(lineAtOffset);
        int n3 = 0;
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = lineAtOffset; i <= lineAtOffset2; ++i) {
            final int offsetAtLine = this.content.getOffsetAtLine(i);
            final TextLayout textLayout = this.renderer.getTextLayout(i);
            final int length = textLayout.getText().length();
            if (length > 0) {
                Rectangle rectangle;
                if (i == lineAtOffset) {
                    if (i == lineAtOffset2) {
                        rectangle = textLayout.getBounds(n - offsetAtLine, n2 - offsetAtLine);
                    }
                    else {
                        rectangle = textLayout.getBounds(n - offsetAtLine, length);
                    }
                    linePixel += rectangle.y;
                }
                else if (i == lineAtOffset2) {
                    rectangle = textLayout.getBounds(0, n2 - offsetAtLine);
                }
                else {
                    rectangle = textLayout.getBounds();
                }
                min = Math.min(min, rectangle.x);
                max = Math.max(max, rectangle.x + rectangle.width);
                n3 += rectangle.height;
            }
            else {
                n3 += this.renderer.getLineHeight();
            }
            this.renderer.disposeTextLayout(textLayout);
        }
        final Rectangle rectangle3;
        final Rectangle rectangle2 = rectangle3 = new Rectangle(min, linePixel, max - min, n3);
        rectangle3.x += this.leftMargin - this.horizontalScrollOffset;
        return rectangle2;
    }
    
    public String getTextRange(final int n, final int n2) {
        this.checkWidget();
        final int charCount = this.getCharCount();
        final int n3 = n + n2;
        if (n > n3 || n < 0 || n3 > charCount) {
            SWT.error(6);
        }
        return this.content.getTextRange(n, n2);
    }
    
    public int getTextLimit() {
        this.checkWidget();
        return this.textLimit;
    }
    
    public int getTopIndex() {
        this.checkWidget();
        return this.topIndex;
    }
    
    public int getTopMargin() {
        this.checkWidget();
        return this.topMargin;
    }
    
    public int getTopPixel() {
        this.checkWidget();
        return this.getVerticalScrollOffset();
    }
    
    int getVerticalIncrement() {
        return this.renderer.getLineHeight();
    }
    
    int getVerticalScrollOffset() {
        if (this.verticalScrollOffset == -1) {
            this.renderer.calculate(0, this.topIndex);
            int n = 0;
            for (int i = 0; i < this.topIndex; ++i) {
                n += this.renderer.getLineHeight(i);
            }
            this.verticalScrollOffset = n - this.topIndexY;
        }
        return this.verticalScrollOffset;
    }
    
    int getVisualLineIndex(final TextLayout textLayout, final int n) {
        int lineIndex = textLayout.getLineIndex(n);
        final int[] lineOffsets = textLayout.getLineOffsets();
        if (lineIndex != 0 && n == lineOffsets[lineIndex]) {
            if (textLayout.getLineBounds(lineIndex).y > this.getCaret().getLocation().y - this.topMargin - this.getLinePixel(this.getCaretLine())) {
                --lineIndex;
            }
            this.caretAlignment = 1;
        }
        return lineIndex;
    }
    
    int getCaretDirection() {
        if (!this.isBidiCaret()) {
            return -1;
        }
        if (this.ime.getCompositionOffset() != -1) {
            return -1;
        }
        if (!this.updateCaretDirection && this.caretDirection != 0) {
            return this.caretDirection;
        }
        this.updateCaretDirection = false;
        final int caretLine = this.getCaretLine();
        final int offsetAtLine = this.content.getOffsetAtLine(caretLine);
        final String line = this.content.getLine(caretLine);
        int n = this.caretOffset - offsetAtLine;
        final int length = line.length();
        if (length == 0) {
            return this.isMirrored() ? 131072 : 16384;
        }
        if (this.caretAlignment == 0 && n > 0) {
            --n;
        }
        if (n == length && n > 0) {
            --n;
        }
        while (n > 0 && Character.isDigit(line.charAt(n))) {
            --n;
        }
        if (n == 0 && Character.isDigit(line.charAt(n))) {
            return this.isMirrored() ? 131072 : 16384;
        }
        final TextLayout textLayout = this.renderer.getTextLayout(caretLine);
        final int level = textLayout.getLevel(n);
        this.renderer.disposeTextLayout(textLayout);
        return ((level & 0x1) != 0x0) ? 131072 : 16384;
    }
    
    int getCaretLine() {
        return this.content.getLineAtOffset(this.caretOffset);
    }
    
    int getWrapWidth() {
        if (this.wordWrap && !this.isSingleLine()) {
            final int n = this.clientAreaWidth - this.leftMargin - this.rightMargin;
            return (n > 0) ? n : true;
        }
        return -1;
    }
    
    int getWordNext(final int n, final int n2) {
        return this.getWordNext(n, n2, false);
    }
    
    int getWordNext(final int n, final int n2, final boolean b) {
        int offsetAtLine;
        int n4;
        String s;
        if (n >= this.getCharCount()) {
            offsetAtLine = n;
            final int n3 = this.content.getLineCount() - 1;
            n4 = this.content.getOffsetAtLine(n3);
            s = this.content.getLine(n3);
        }
        else {
            final int lineAtOffset = this.content.getLineAtOffset(n);
            n4 = this.content.getOffsetAtLine(lineAtOffset);
            s = this.content.getLine(lineAtOffset);
            if (n >= n4 + s.length()) {
                offsetAtLine = this.content.getOffsetAtLine(lineAtOffset + 1);
            }
            else {
                final TextLayout textLayout = this.renderer.getTextLayout(lineAtOffset);
                offsetAtLine = n4 + textLayout.getNextOffset(n - n4, n2);
                this.renderer.disposeTextLayout(textLayout);
            }
        }
        if (b) {
            return offsetAtLine;
        }
        return this.sendWordBoundaryEvent(3009, n2, n, offsetAtLine, s, n4);
    }
    
    int getWordPrevious(final int n, final int n2) {
        return this.getWordPrevious(n, n2, false);
    }
    
    int getWordPrevious(final int n, final int n2, final boolean b) {
        int n3;
        int n4;
        String s;
        if (n <= 0) {
            n3 = 0;
            final int lineAtOffset = this.content.getLineAtOffset(n3);
            n4 = this.content.getOffsetAtLine(lineAtOffset);
            s = this.content.getLine(lineAtOffset);
        }
        else {
            final int lineAtOffset2 = this.content.getLineAtOffset(n);
            n4 = this.content.getOffsetAtLine(lineAtOffset2);
            s = this.content.getLine(lineAtOffset2);
            if (n == n4) {
                n3 = this.content.getOffsetAtLine(lineAtOffset2 - 1) + this.content.getLine(lineAtOffset2 - 1).length();
            }
            else {
                final int min = Math.min(n - n4, s.length());
                final TextLayout textLayout = this.renderer.getTextLayout(lineAtOffset2);
                n3 = n4 + textLayout.getPreviousOffset(min, n2);
                this.renderer.disposeTextLayout(textLayout);
            }
        }
        if (b) {
            return n3;
        }
        return this.sendWordBoundaryEvent(3010, n2, n, n3, s, n4);
    }
    
    public boolean getWordWrap() {
        this.checkWidget();
        return this.wordWrap;
    }
    
    public int getWrapIndent() {
        this.checkWidget();
        return this.wrapIndent;
    }
    
    Point getPointAtOffset(final int n) {
        final int lineAtOffset = this.content.getLineAtOffset(n);
        final String line = this.content.getLine(lineAtOffset);
        int n2 = n - this.content.getOffsetAtLine(lineAtOffset);
        final int length = line.length();
        if (lineAtOffset < this.content.getLineCount() - 1) {
            final int n3 = this.content.getOffsetAtLine(lineAtOffset + 1) - 1;
            if (length < n2 && n2 <= n3) {
                n2 = length;
            }
        }
        final TextLayout textLayout = this.renderer.getTextLayout(lineAtOffset);
        Point point;
        if (length != 0 && n2 <= length) {
            if (n2 == length) {
                point = textLayout.getLocation(n2 - 1, true);
            }
            else {
                switch (this.caretAlignment) {
                    case 1: {
                        point = textLayout.getLocation(n2, false);
                        break;
                    }
                    default: {
                        if (n2 == 0) {
                            point = textLayout.getLocation(n2, false);
                            break;
                        }
                        point = textLayout.getLocation(n2 - 1, true);
                        break;
                    }
                }
            }
        }
        else {
            point = new Point(textLayout.getIndent(), 0);
        }
        this.renderer.disposeTextLayout(textLayout);
        final Point point2 = point;
        point2.x += this.leftMargin - this.horizontalScrollOffset;
        final Point point3 = point;
        point3.y += this.getLinePixel(lineAtOffset);
        return point;
    }
    
    public void insert(final String s) {
        this.checkWidget();
        if (s == null) {
            SWT.error(4);
        }
        if (this.blockSelection) {
            this.insertBlockSelectionText(s, false);
        }
        else {
            final Point selectionRange = this.getSelectionRange();
            this.replaceTextRange(selectionRange.x, selectionRange.y, s);
        }
    }
    
    int insertBlockSelectionText(final String s, final boolean b) {
        int n = 1;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '\n' || char1 == '\r') {
                ++n;
                if (char1 == '\r' && i + 1 < s.length() && s.charAt(i + 1) == '\n') {
                    ++i;
                }
            }
        }
        final String[] array = new String[n];
        int n2 = 0;
        int n3 = 0;
        for (int j = 0; j < s.length(); ++j) {
            final char char2 = s.charAt(j);
            if (char2 == '\n' || char2 == '\r') {
                array[n3++] = s.substring(n2, j);
                if (char2 == '\r' && j + 1 < s.length() && s.charAt(j + 1) == '\n') {
                    ++j;
                }
                n2 = j + 1;
            }
        }
        array[n3++] = s.substring(n2);
        if (b) {
            int max = 0;
            for (int k = 0; k < array.length; ++k) {
                max = Math.max(max, array[k].length());
            }
            for (int l = 0; l < array.length; ++l) {
                final String s2 = array[l];
                final int length = s2.length();
                if (length < max) {
                    final int n4 = max - length;
                    final StringBuffer sb = new StringBuffer(length + n4);
                    sb.append(s2);
                    for (int n5 = 0; n5 < n4; ++n5) {
                        sb.append(' ');
                    }
                    array[l] = sb.toString();
                }
            }
        }
        int n6;
        int height;
        int n7;
        int width;
        if (this.blockXLocation != -1) {
            final Rectangle blockSelectionPosition = this.getBlockSelectionPosition();
            n6 = blockSelectionPosition.y;
            height = blockSelectionPosition.height;
            n7 = blockSelectionPosition.x;
            width = blockSelectionPosition.width;
        }
        else {
            height = (n6 = this.getCaretLine());
            width = (n7 = this.getPointAtOffset(this.caretOffset).x);
        }
        int caretOffset = this.caretOffset;
        final int caretLine = this.getCaretLine();
        int n8 = 0;
        int n9;
        for (n9 = n6; n9 <= height; ++n9) {
            final int sendTextEvent = this.sendTextEvent(n7, width, n9, (n8 < n3) ? array[n8++] : "", b);
            if (n9 == caretLine) {
                caretOffset = sendTextEvent;
            }
        }
        while (n8 < n3) {
            final int sendTextEvent2 = this.sendTextEvent(n7, n7, n9, array[n8++], b);
            if (n9 == caretLine) {
                caretOffset = sendTextEvent2;
            }
            ++n9;
        }
        return caretOffset;
    }
    
    void insertBlockSelectionText(final char c, final int n) {
        if (c == '\r' || c == '\n') {
            return;
        }
        final Rectangle blockSelectionPosition = this.getBlockSelectionPosition();
        final int y = blockSelectionPosition.y;
        final int height = blockSelectionPosition.height;
        final int x = blockSelectionPosition.x;
        final int width = blockSelectionPosition.width;
        final int[] array = { 0 };
        int n2 = 0;
        int n3 = 0;
        final String text = (c != '\0') ? new String(new char[] { c }) : "";
        final int length = text.length();
        for (int i = y; i <= height; ++i) {
            final String line = this.content.getLine(i);
            final int offsetAtLine = this.content.getOffsetAtLine(i);
            final int n4 = offsetAtLine + line.length();
            final int linePixel = this.getLinePixel(i);
            final int offsetAtPoint = this.getOffsetAtPoint(x, linePixel, array, true);
            final boolean b = offsetAtPoint == -1;
            int clusterPrevious;
            if (b) {
                clusterPrevious = ((x < this.leftMargin) ? offsetAtLine : n4);
            }
            else {
                clusterPrevious = offsetAtPoint + array[0];
            }
            final int offsetAtPoint2 = this.getOffsetAtPoint(width, linePixel, array, true);
            int clusterNext;
            if (offsetAtPoint2 == -1) {
                clusterNext = ((width < this.leftMargin) ? offsetAtLine : n4);
            }
            else {
                clusterNext = offsetAtPoint2 + array[0];
            }
            if (clusterPrevious > clusterNext) {
                final int n5 = clusterPrevious;
                clusterPrevious = clusterNext;
                clusterNext = n5;
            }
            if (clusterPrevious == clusterNext && !b) {
                switch (n) {
                    case 8: {
                        if (clusterPrevious > offsetAtLine) {
                            clusterPrevious = this.getClusterPrevious(clusterPrevious, i);
                            break;
                        }
                        break;
                    }
                    case 127: {
                        if (clusterNext < n4) {
                            clusterNext = this.getClusterNext(clusterNext, i);
                            break;
                        }
                        break;
                    }
                }
            }
            if (b) {
                if (line.length() >= n3) {
                    n3 = line.length();
                    n2 = n4 + length;
                }
            }
            else {
                n2 = clusterPrevious + length;
                n3 = this.content.getCharCount();
            }
            final Event event = new Event();
            event.text = text;
            event.start = clusterPrevious;
            event.end = clusterNext;
            this.sendKeyEvent(event);
        }
        final int x2 = this.getPointAtOffset(n2).x;
        final int verticalScrollOffset = this.getVerticalScrollOffset();
        this.setBlockSelectionLocation(x2, this.blockYAnchor - verticalScrollOffset, x2, this.blockYLocation - verticalScrollOffset, false);
    }
    
    void installDefaultContent() {
        this.textChangeListener = new TextChangeListener() {
            public void textChanging(final TextChangingEvent textChangingEvent) {
                StyledText.this.handleTextChanging(textChangingEvent);
            }
            
            public void textChanged(final TextChangedEvent textChangedEvent) {
                StyledText.this.handleTextChanged(textChangedEvent);
            }
            
            public void textSet(final TextChangedEvent textChangedEvent) {
                StyledText.this.handleTextSet(textChangedEvent);
            }
        };
        (this.content = new DefaultContent()).addTextChangeListener(this.textChangeListener);
    }
    
    void installListeners() {
        final ScrollBar verticalBar = this.getVerticalBar();
        final ScrollBar horizontalBar = this.getHorizontalBar();
        this.addListener(12, this.listener = new Listener() {
            public void handleEvent(final Event event) {
                switch (event.type) {
                    case 12: {
                        StyledText.this.handleDispose(event);
                        break;
                    }
                    case 1: {
                        StyledText.this.handleKeyDown(event);
                        break;
                    }
                    case 2: {
                        StyledText.this.handleKeyUp(event);
                        break;
                    }
                    case 3: {
                        StyledText.this.handleMouseDown(event);
                        break;
                    }
                    case 4: {
                        StyledText.this.handleMouseUp(event);
                        break;
                    }
                    case 5: {
                        StyledText.this.handleMouseMove(event);
                        break;
                    }
                    case 9: {
                        StyledText.this.handlePaint(event);
                        break;
                    }
                    case 11: {
                        StyledText.this.handleResize(event);
                        break;
                    }
                    case 31: {
                        StyledText.this.handleTraverse(event);
                        break;
                    }
                }
            }
        });
        this.addListener(1, this.listener);
        this.addListener(2, this.listener);
        this.addListener(3, this.listener);
        this.addListener(4, this.listener);
        this.addListener(5, this.listener);
        this.addListener(9, this.listener);
        this.addListener(11, this.listener);
        this.addListener(31, this.listener);
        this.ime.addListener(43, new Listener() {
            public void handleEvent(final Event event) {
                switch (event.detail) {
                    case 3: {
                        StyledText.this.handleCompositionSelection(event);
                        break;
                    }
                    case 1: {
                        StyledText.this.handleCompositionChanged(event);
                        break;
                    }
                    case 2: {
                        StyledText.this.handleCompositionOffset(event);
                        break;
                    }
                }
            }
        });
        if (verticalBar != null) {
            verticalBar.addListener(13, new Listener() {
                public void handleEvent(final Event event) {
                    StyledText.this.handleVerticalScroll(event);
                }
            });
        }
        if (horizontalBar != null) {
            horizontalBar.addListener(13, new Listener() {
                public void handleEvent(final Event event) {
                    StyledText.this.handleHorizontalScroll(event);
                }
            });
        }
    }
    
    void internalRedrawRange(int n, final int n2) {
        if (n2 <= 0) {
            return;
        }
        final int n3 = n + n2;
        int lineAtOffset = this.content.getLineAtOffset(n);
        int lineAtOffset2 = this.content.getLineAtOffset(n3);
        final int partialBottomIndex = this.getPartialBottomIndex();
        final int partialTopIndex = this.getPartialTopIndex();
        if (lineAtOffset > partialBottomIndex || lineAtOffset2 < partialTopIndex) {
            return;
        }
        if (partialTopIndex > lineAtOffset) {
            lineAtOffset = partialTopIndex;
            n = 0;
        }
        else {
            n -= this.content.getOffsetAtLine(lineAtOffset);
        }
        int n4;
        if (partialBottomIndex < lineAtOffset2) {
            lineAtOffset2 = partialBottomIndex + 1;
            n4 = 0;
        }
        else {
            n4 = n3 - this.content.getOffsetAtLine(lineAtOffset2);
        }
        TextLayout textLayout = this.renderer.getTextLayout(lineAtOffset);
        final int n5 = this.leftMargin - this.horizontalScrollOffset;
        final int linePixel = this.getLinePixel(lineAtOffset);
        int[] array = textLayout.getLineOffsets();
        final int lineIndex = textLayout.getLineIndex(Math.min(n, textLayout.getText().length()));
        if (this.wordWrap && lineIndex > 0 && array[lineIndex] == n) {
            final Rectangle lineBounds = textLayout.getLineBounds(lineIndex - 1);
            lineBounds.x = lineBounds.width;
            lineBounds.width = this.clientAreaWidth - this.rightMargin - lineBounds.x;
            final Rectangle rectangle = lineBounds;
            rectangle.x += n5;
            final Rectangle rectangle2 = lineBounds;
            rectangle2.y += linePixel;
            super.redraw(lineBounds.x, lineBounds.y, lineBounds.width, lineBounds.height, false);
        }
        if (lineAtOffset == lineAtOffset2 && lineIndex == textLayout.getLineIndex(Math.min(n4, textLayout.getText().length()))) {
            final Rectangle bounds;
            final Rectangle rectangle3 = bounds = textLayout.getBounds(n, n4 - 1);
            bounds.x += n5;
            final Rectangle rectangle4 = rectangle3;
            rectangle4.y += linePixel;
            super.redraw(rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height, false);
            this.renderer.disposeTextLayout(textLayout);
            return;
        }
        final Rectangle bounds2 = textLayout.getBounds(n, array[lineIndex + 1] - 1);
        if (bounds2.height == 0) {
            final Rectangle lineBounds2 = textLayout.getLineBounds(lineIndex);
            bounds2.x = lineBounds2.width;
            bounds2.y = lineBounds2.y;
            bounds2.height = lineBounds2.height;
        }
        final Rectangle rectangle5 = bounds2;
        rectangle5.x += n5;
        final Rectangle rectangle6 = bounds2;
        rectangle6.y += linePixel;
        bounds2.width = this.clientAreaWidth - this.rightMargin - bounds2.x;
        super.redraw(bounds2.x, bounds2.y, bounds2.width, bounds2.height, false);
        if (lineAtOffset != lineAtOffset2) {
            this.renderer.disposeTextLayout(textLayout);
            textLayout = this.renderer.getTextLayout(lineAtOffset2);
            array = textLayout.getLineOffsets();
        }
        final int lineIndex2 = textLayout.getLineIndex(Math.min(n4, textLayout.getText().length()));
        final Rectangle bounds3 = textLayout.getBounds(array[lineIndex2], n4 - 1);
        if (bounds3.height == 0) {
            final Rectangle lineBounds3 = textLayout.getLineBounds(lineIndex2);
            bounds3.y = lineBounds3.y;
            bounds3.height = lineBounds3.height;
        }
        final Rectangle rectangle7 = bounds3;
        rectangle7.x += n5;
        final Rectangle rectangle8 = bounds3;
        rectangle8.y += this.getLinePixel(lineAtOffset2);
        super.redraw(bounds3.x, bounds3.y, bounds3.width, bounds3.height, false);
        this.renderer.disposeTextLayout(textLayout);
        final int n6 = bounds2.y + bounds2.height;
        if (bounds3.y > n6) {
            super.redraw(this.leftMargin, n6, this.clientAreaWidth - this.rightMargin - this.leftMargin, bounds3.y - n6, false);
        }
    }
    
    void handleCompositionOffset(final Event event) {
        final int[] array = { 0 };
        event.index = this.getOffsetAtPoint(event.x, event.y, array, true);
        event.count = array[0];
    }
    
    void handleCompositionSelection(final Event event) {
        event.start = this.selection.x;
        event.end = this.selection.y;
        event.text = this.getSelectionText();
    }
    
    void handleCompositionChanged(final Event event) {
        final String text = event.text;
        final int start = event.start;
        final int end = event.end;
        final int length = text.length();
        if (length == this.ime.getCommitCount()) {
            this.content.replaceTextRange(start, end - start, "");
            this.setCaretOffset(this.ime.getCompositionOffset(), -1);
            this.caretWidth = 0;
            this.caretDirection = 0;
        }
        else {
            this.content.replaceTextRange(start, end - start, text);
            int n = -1;
            if (this.ime.getWideCaret()) {
                final int compositionOffset = this.ime.getCompositionOffset();
                final int caretLine = this.getCaretLine();
                final int offsetAtLine = this.content.getOffsetAtLine(caretLine);
                final TextLayout textLayout = this.renderer.getTextLayout(caretLine);
                this.caretWidth = textLayout.getBounds(compositionOffset - offsetAtLine, compositionOffset + length - 1 - offsetAtLine).width;
                this.renderer.disposeTextLayout(textLayout);
                n = 1;
            }
            this.setCaretOffset(this.ime.getCaretOffset(), n);
        }
        this.showCaret();
    }
    
    void handleDispose(final Event event) {
        this.removeListener(12, this.listener);
        this.notifyListeners(12, event);
        event.type = 0;
        this.clipboard.dispose();
        if (this.renderer != null) {
            this.renderer.dispose();
            this.renderer = null;
        }
        if (this.content != null) {
            this.content.removeTextChangeListener(this.textChangeListener);
            this.content = null;
        }
        if (this.defaultCaret != null) {
            this.defaultCaret.dispose();
            this.defaultCaret = null;
        }
        if (this.leftCaretBitmap != null) {
            this.leftCaretBitmap.dispose();
            this.leftCaretBitmap = null;
        }
        if (this.rightCaretBitmap != null) {
            this.rightCaretBitmap.dispose();
            this.rightCaretBitmap = null;
        }
        if (this.isBidiCaret()) {
            BidiUtil.removeLanguageListener(this);
        }
        this.selectionBackground = null;
        this.selectionForeground = null;
        this.marginColor = null;
        this.textChangeListener = null;
        this.selection = null;
        this.doubleClickSelection = null;
        this.keyActionMap = null;
        this.background = null;
        this.foreground = null;
        this.clipboard = null;
        this.tabs = null;
    }
    
    void handleHorizontalScroll(final Event event) {
        this.scrollHorizontal(this.getHorizontalBar().getSelection() - this.horizontalScrollOffset, false);
    }
    
    void handleKey(final Event event) {
        this.caretAlignment = 0;
        int n;
        if (event.keyCode != 0) {
            n = this.getKeyBinding(event.keyCode | event.stateMask);
        }
        else {
            n = this.getKeyBinding(event.character | event.stateMask);
            if (n == 0 && (event.stateMask & 0x40000) != 0x0 && event.character <= '\u001f') {
                n = this.getKeyBinding(event.character + '@' | event.stateMask);
            }
        }
        if (n == 0) {
            boolean b;
            if (StyledText.IS_MAC) {
                b = ((event.stateMask ^ 0x400000) == 0x0 || (event.stateMask ^ 0x420000) == 0x0);
            }
            else if (StyledText.IS_MOTIF) {
                b = ((event.stateMask ^ 0x40000) == 0x0 || (event.stateMask ^ 0x60000) == 0x0);
            }
            else {
                b = ((event.stateMask ^ 0x10000) == 0x0 || (event.stateMask ^ 0x40000) == 0x0 || (event.stateMask ^ 0x30000) == 0x0 || (event.stateMask ^ 0x60000) == 0x0);
            }
            if ((!b && event.character > '\u001f' && event.character != '\u007f') || event.character == '\r' || event.character == '\n' || event.character == '\t') {
                this.doContent(event.character);
                this.update();
            }
        }
        else {
            this.invokeAction(n);
        }
    }
    
    void handleKeyDown(final Event event) {
        if (this.clipboardSelection == null) {
            this.clipboardSelection = new Point(this.selection.x, this.selection.y);
        }
        this.newOrientation = 0;
        final Event event2 = new Event();
        event2.character = event.character;
        event2.keyCode = event.keyCode;
        event2.keyLocation = event.keyLocation;
        event2.stateMask = event.stateMask;
        event2.doit = true;
        this.notifyListeners(3005, event2);
        if (event2.doit) {
            if ((event.stateMask & SWT.MODIFIER_MASK) == 0x40000 && event.keyCode == 131072 && this.isBidiCaret()) {
                this.newOrientation = ((event.keyLocation == 16384) ? 33554432 : 67108864);
            }
            this.handleKey(event);
        }
    }
    
    void handleKeyUp(final Event event) {
        if (this.clipboardSelection != null && (this.clipboardSelection.x != this.selection.x || this.clipboardSelection.y != this.selection.y)) {
            this.copySelection(2);
        }
        this.clipboardSelection = null;
        if (this.newOrientation != 0) {
            if (this.newOrientation != this.getOrientation()) {
                final Event event2 = new Event();
                event2.doit = true;
                this.notifyListeners(44, event2);
                if (event2.doit) {
                    this.setOrientation(this.newOrientation);
                }
            }
            this.newOrientation = 0;
        }
    }
    
    void handleMouseDown(final Event event) {
        this.forceFocus();
        if (this.dragDetect && this.checkDragDetect(event)) {
            return;
        }
        if (event.button == 2) {
            final String s = (String)this.getClipboardContent(2);
            if (s != null && s.length() > 0) {
                this.doMouseLocationChange(event.x, event.y, false);
                final Event event2 = new Event();
                event2.start = this.selection.x;
                event2.end = this.selection.y;
                event2.text = this.getModelDelimitedText(s);
                this.sendKeyEvent(event2);
            }
        }
        if (event.button != 1 || (StyledText.IS_MAC && (event.stateMask & SWT.MOD4) != 0x0)) {
            return;
        }
        this.clickCount = event.count;
        if (this.clickCount == 1) {
            this.doMouseLocationChange(event.x, event.y, (event.stateMask & SWT.MOD2) != 0x0);
        }
        else if (this.doubleClickEnabled) {
            final boolean b = (this.clickCount & 0x1) == 0x0;
            final int offsetAtPoint = this.getOffsetAtPoint(event.x, event.y, null);
            final int lineAtOffset = this.content.getLineAtOffset(offsetAtPoint);
            final int offsetAtLine = this.content.getOffsetAtLine(lineAtOffset);
            if (b) {
                final int n = this.blockSelection ? offsetAtLine : 0;
                final int n2 = this.blockSelection ? (offsetAtLine + this.content.getLine(lineAtOffset).length()) : this.content.getCharCount();
                final int max = Math.max(n, this.getWordPrevious(offsetAtPoint, 16));
                this.setSelection(max, Math.min(n2, this.getWordNext(max, 8)) - max, false, true);
                this.sendSelectionEvent();
            }
            else if (this.blockSelection) {
                this.setBlockSelectionLocation(this.leftMargin, event.y, this.clientAreaWidth - this.rightMargin, event.y, true);
            }
            else {
                int n3 = this.content.getCharCount();
                if (lineAtOffset + 1 < this.content.getLineCount()) {
                    n3 = this.content.getOffsetAtLine(lineAtOffset + 1);
                }
                this.setSelection(offsetAtLine, n3 - offsetAtLine, false, false);
                this.sendSelectionEvent();
            }
            this.doubleClickSelection = new Point(this.selection.x, this.selection.y);
            this.showCaret();
        }
    }
    
    void handleMouseMove(final Event event) {
        if (this.clickCount > 0) {
            this.update();
            this.doAutoScroll(event);
            this.doMouseLocationChange(event.x, event.y, true);
        }
        if (this.renderer.hasLinks) {
            this.doMouseLinkCursor(event.x, event.y);
        }
    }
    
    void handleMouseUp(final Event event) {
        this.clickCount = 0;
        this.endAutoScroll();
        if (event.button == 1) {
            this.copySelection(2);
        }
    }
    
    void handlePaint(final Event event) {
        if (event.width == 0 || event.height == 0) {
            return;
        }
        if (this.clientAreaWidth == 0 || this.clientAreaHeight == 0) {
            return;
        }
        final int lineIndex = this.getLineIndex(event.y);
        int linePixel = this.getLinePixel(lineIndex);
        final int n = event.y + event.height;
        final GC gc = event.gc;
        final Color background = this.getBackground();
        final Color foreground = this.getForeground();
        if (n > 0) {
            for (int n2 = this.isSingleLine() ? 1 : this.content.getLineCount(), n3 = this.leftMargin - this.horizontalScrollOffset, n4 = lineIndex; linePixel < n && n4 < n2; linePixel += this.renderer.drawLine(n4, n3, linePixel, gc, background, foreground), ++n4) {}
            if (linePixel < n) {
                gc.setBackground(background);
                this.drawBackground(gc, 0, linePixel, this.clientAreaWidth, n - linePixel);
            }
        }
        if (this.blockSelection && this.blockXLocation != -1) {
            gc.setBackground(this.getSelectionBackground());
            final Rectangle blockSelectionRectangle = this.getBlockSelectionRectangle();
            gc.drawRectangle(blockSelectionRectangle.x, blockSelectionRectangle.y, Math.max(1, blockSelectionRectangle.width - 1), Math.max(1, blockSelectionRectangle.height - 1));
            gc.setAdvanced(true);
            if (gc.getAdvanced()) {
                gc.setAlpha(100);
                gc.fillRectangle(blockSelectionRectangle);
                gc.setAdvanced(false);
            }
        }
        gc.setBackground((this.marginColor != null) ? this.marginColor : background);
        if (this.topMargin > 0) {
            this.drawBackground(gc, 0, 0, this.clientAreaWidth, this.topMargin);
        }
        if (this.bottomMargin > 0) {
            this.drawBackground(gc, 0, this.clientAreaHeight - this.bottomMargin, this.clientAreaWidth, this.bottomMargin);
        }
        if (this.leftMargin - this.alignmentMargin > 0) {
            this.drawBackground(gc, 0, 0, this.leftMargin - this.alignmentMargin, this.clientAreaHeight);
        }
        if (this.rightMargin > 0) {
            this.drawBackground(gc, this.clientAreaWidth - this.rightMargin, 0, this.rightMargin, this.clientAreaHeight);
        }
    }
    
    void handleResize(final Event event) {
        final int clientAreaHeight = this.clientAreaHeight;
        final int clientAreaWidth = this.clientAreaWidth;
        final Rectangle clientArea = this.getClientArea();
        this.clientAreaHeight = clientArea.height;
        this.clientAreaWidth = clientArea.width;
        if (clientAreaWidth != this.clientAreaWidth && this.rightMargin > 0) {
            super.redraw(((clientAreaWidth < this.clientAreaWidth) ? clientAreaWidth : this.clientAreaWidth) - this.rightMargin, 0, this.rightMargin, clientAreaHeight, false);
        }
        if (clientAreaHeight != this.clientAreaHeight && this.bottomMargin > 0) {
            super.redraw(0, ((clientAreaHeight < this.clientAreaHeight) ? clientAreaHeight : this.clientAreaHeight) - this.bottomMargin, clientAreaWidth, this.bottomMargin, false);
        }
        if (this.wordWrap) {
            if (clientAreaWidth != this.clientAreaWidth) {
                this.renderer.reset(0, this.content.getLineCount());
                this.verticalScrollOffset = -1;
                this.renderer.calculateIdle();
                super.redraw();
            }
            if (clientAreaHeight != this.clientAreaHeight) {
                if (clientAreaHeight == 0) {
                    this.topIndexY = 0;
                }
                this.setScrollBars(true);
            }
            this.setCaretLocation();
        }
        else {
            this.renderer.calculateClientArea();
            this.setScrollBars(true);
            this.claimRightFreeSpace();
            if (this.clientAreaWidth != 0) {
                final ScrollBar horizontalBar = this.getHorizontalBar();
                if (horizontalBar != null && horizontalBar.getVisible() && this.horizontalScrollOffset != horizontalBar.getSelection()) {
                    horizontalBar.setSelection(this.horizontalScrollOffset);
                    this.horizontalScrollOffset = horizontalBar.getSelection();
                }
            }
        }
        this.updateCaretVisibility();
        this.claimBottomFreeSpace();
        this.setAlignment();
    }
    
    void handleTextChanged(final TextChangedEvent textChangedEvent) {
        final int compositionOffset = this.ime.getCompositionOffset();
        if (compositionOffset != -1 && this.lastTextChangeStart < compositionOffset) {
            this.ime.setCompositionOffset(compositionOffset + this.lastTextChangeNewCharCount - this.lastTextChangeReplaceCharCount);
        }
        final int lineAtOffset = this.content.getLineAtOffset(this.lastTextChangeStart);
        this.resetCache(lineAtOffset, 0);
        if (!this.isFixedLineHeight() && this.topIndex > lineAtOffset) {
            this.topIndex = lineAtOffset;
            this.topIndexY = 0;
            super.redraw();
        }
        else {
            final int n = lineAtOffset + this.lastTextChangeNewLineCount;
            final int linePixel = this.getLinePixel(lineAtOffset);
            final int linePixel2 = this.getLinePixel(n + 1);
            if (this.lastLineBottom != linePixel2) {
                super.redraw();
            }
            else {
                super.redraw(0, linePixel, this.clientAreaWidth, linePixel2 - linePixel, false);
                this.redrawLinesBullet(this.renderer.redrawLines);
            }
        }
        this.renderer.redrawLines = null;
        if (!this.blockSelection || this.blockXLocation == -1) {
            this.updateSelection(this.lastTextChangeStart, this.lastTextChangeReplaceCharCount, this.lastTextChangeNewCharCount);
        }
        if (this.lastTextChangeReplaceLineCount > 0 || this.wordWrap) {
            this.claimBottomFreeSpace();
        }
        if (this.lastTextChangeReplaceCharCount > 0) {
            this.claimRightFreeSpace();
        }
        this.sendAccessibleTextChanged(this.lastTextChangeStart, this.lastTextChangeNewCharCount, 0);
        this.lastCharCount += this.lastTextChangeNewCharCount;
        this.lastCharCount -= this.lastTextChangeReplaceCharCount;
        this.setAlignment();
    }
    
    void handleTextChanging(final TextChangingEvent textChangingEvent) {
        if (textChangingEvent.replaceCharCount < 0) {
            textChangingEvent.start += textChangingEvent.replaceCharCount;
            textChangingEvent.replaceCharCount *= -1;
        }
        this.lastTextChangeStart = textChangingEvent.start;
        this.lastTextChangeNewLineCount = textChangingEvent.newLineCount;
        this.lastTextChangeNewCharCount = textChangingEvent.newCharCount;
        this.lastTextChangeReplaceLineCount = textChangingEvent.replaceLineCount;
        this.lastTextChangeReplaceCharCount = textChangingEvent.replaceCharCount;
        final int lineAtOffset = this.content.getLineAtOffset(textChangingEvent.start);
        final int linePixel = this.getLinePixel(lineAtOffset + textChangingEvent.replaceLineCount + 1);
        final int lastLineBottom = this.getLinePixel(lineAtOffset + 1) + textChangingEvent.newLineCount * this.renderer.getLineHeight();
        this.lastLineBottom = lastLineBottom;
        if (linePixel < 0 && lastLineBottom < 0) {
            this.lastLineBottom += linePixel - lastLineBottom;
            this.verticalScrollOffset += lastLineBottom - linePixel;
            this.calculateTopIndex(lastLineBottom - linePixel);
            this.setScrollBars(true);
        }
        else {
            this.scrollText(linePixel, lastLineBottom);
        }
        this.sendAccessibleTextChanged(this.lastTextChangeStart, 0, this.lastTextChangeReplaceCharCount);
        this.renderer.textChanging(textChangingEvent);
        final int n = this.content.getCharCount() - textChangingEvent.replaceCharCount + textChangingEvent.newCharCount;
        if (this.caretOffset > n) {
            this.setCaretOffset(n, -1);
        }
    }
    
    void handleTextSet(final TextChangedEvent textChangedEvent) {
        this.reset();
        final int charCount = this.getCharCount();
        this.sendAccessibleTextChanged(0, charCount, this.lastCharCount);
        this.lastCharCount = charCount;
        this.setAlignment();
    }
    
    void handleTraverse(final Event event) {
        switch (event.detail) {
            case 2:
            case 256:
            case 512: {
                event.doit = true;
                break;
            }
            case 4:
            case 8:
            case 16: {
                if ((this.getStyle() & 0x4) != 0x0) {
                    event.doit = true;
                    break;
                }
                if (!this.editable || (event.stateMask & SWT.MODIFIER_MASK) != 0x0) {
                    event.doit = true;
                    break;
                }
                break;
            }
        }
    }
    
    void handleVerticalScroll(final Event event) {
        this.scrollVertical(this.getVerticalBar().getSelection() - this.getVerticalScrollOffset(), false);
    }
    
    void initializeAccessible() {
        final Accessible accessible = this.getAccessible();
        accessible.addAccessibleListener(new AccessibleAdapter() {
            public void getName(final AccessibleEvent accessibleEvent) {
                String stripMnemonic = null;
                final String associatedLabel = StyledText.this.getAssociatedLabel();
                if (associatedLabel != null) {
                    stripMnemonic = StyledText.this.stripMnemonic(associatedLabel);
                }
                accessibleEvent.result = stripMnemonic;
            }
            
            public void getHelp(final AccessibleEvent accessibleEvent) {
                accessibleEvent.result = StyledText.this.getToolTipText();
            }
            
            public void getKeyboardShortcut(final AccessibleEvent accessibleEvent) {
                String string = null;
                final String associatedLabel = StyledText.this.getAssociatedLabel();
                if (associatedLabel != null) {
                    final char findMnemonic = StyledText.this._findMnemonic(associatedLabel);
                    if (findMnemonic != '\0') {
                        string = "Alt+" + findMnemonic;
                    }
                }
                accessibleEvent.result = string;
            }
        });
        accessible.addAccessibleTextListener(new AccessibleTextExtendedAdapter() {
            public void getCaretOffset(final AccessibleTextEvent accessibleTextEvent) {
                accessibleTextEvent.offset = StyledText.this.getCaretOffset();
            }
            
            public void setCaretOffset(final AccessibleTextEvent accessibleTextEvent) {
                StyledText.this.setCaretOffset(accessibleTextEvent.offset);
                accessibleTextEvent.result = "OK";
            }
            
            public void getSelectionRange(final AccessibleTextEvent accessibleTextEvent) {
                final Point selectionRange = StyledText.this.getSelectionRange();
                accessibleTextEvent.offset = selectionRange.x;
                accessibleTextEvent.length = selectionRange.y;
            }
            
            public void addSelection(final AccessibleTextEvent accessibleTextEvent) {
                final StyledText this$0 = StyledText.this;
                final Point selection = this$0.getSelection();
                if (selection.x == selection.y) {
                    int n = accessibleTextEvent.end;
                    if (n == -1) {
                        n = this$0.getCharCount();
                    }
                    this$0.setSelection(accessibleTextEvent.start, n);
                    accessibleTextEvent.result = "OK";
                }
            }
            
            public void getSelection(final AccessibleTextEvent accessibleTextEvent) {
                final StyledText this$0 = StyledText.this;
                if (this$0.blockSelection && this$0.blockXLocation != -1) {
                    final Rectangle blockSelectionPosition = this$0.getBlockSelectionPosition();
                    final int linePixel = this$0.getLinePixel(blockSelectionPosition.y + accessibleTextEvent.index);
                    accessibleTextEvent.ranges = this.getRanges(blockSelectionPosition.x, linePixel, blockSelectionPosition.width, linePixel);
                    if (accessibleTextEvent.ranges.length > 0) {
                        accessibleTextEvent.start = accessibleTextEvent.ranges[0];
                        accessibleTextEvent.end = accessibleTextEvent.ranges[accessibleTextEvent.ranges.length - 1];
                    }
                }
                else if (accessibleTextEvent.index == 0) {
                    final Point selection = this$0.getSelection();
                    accessibleTextEvent.start = selection.x;
                    accessibleTextEvent.end = selection.y;
                    if (accessibleTextEvent.start > accessibleTextEvent.end) {
                        final int start = accessibleTextEvent.start;
                        accessibleTextEvent.start = accessibleTextEvent.end;
                        accessibleTextEvent.end = start;
                    }
                }
            }
            
            public void getSelectionCount(final AccessibleTextEvent accessibleTextEvent) {
                final StyledText this$0 = StyledText.this;
                if (this$0.blockSelection && this$0.blockXLocation != -1) {
                    final Rectangle blockSelectionPosition = this$0.getBlockSelectionPosition();
                    accessibleTextEvent.count = blockSelectionPosition.height - blockSelectionPosition.y + 1;
                }
                else {
                    final Point selection = this$0.getSelection();
                    accessibleTextEvent.count = ((selection.x != selection.y) ? 1 : 0);
                }
            }
            
            public void removeSelection(final AccessibleTextEvent accessibleTextEvent) {
                final StyledText this$0 = StyledText.this;
                if (accessibleTextEvent.index == 0) {
                    if (this$0.blockSelection) {
                        this$0.clearBlockSelection(true, false);
                    }
                    else {
                        this$0.clearSelection(false);
                    }
                    accessibleTextEvent.result = "OK";
                }
            }
            
            public void setSelection(final AccessibleTextEvent accessibleTextEvent) {
                if (accessibleTextEvent.index != 0) {
                    return;
                }
                final StyledText this$0 = StyledText.this;
                final Point selection = this$0.getSelection();
                if (selection.x == selection.y) {
                    return;
                }
                int n = accessibleTextEvent.end;
                if (n == -1) {
                    n = this$0.getCharCount();
                }
                this$0.setSelection(accessibleTextEvent.start, n);
                accessibleTextEvent.result = "OK";
            }
            
            public void getCharacterCount(final AccessibleTextEvent accessibleTextEvent) {
                accessibleTextEvent.count = StyledText.this.getCharCount();
            }
            
            public void getOffsetAtPoint(final AccessibleTextEvent accessibleTextEvent) {
                final StyledText this$0 = StyledText.this;
                final Point map = this$0.getDisplay().map(null, this$0, new Point(accessibleTextEvent.x, accessibleTextEvent.y));
                accessibleTextEvent.offset = this$0.getOffsetAtPoint(map.x, map.y, null, true);
            }
            
            public void getTextBounds(final AccessibleTextEvent accessibleTextEvent) {
                final StyledText this$0 = StyledText.this;
                final int start = accessibleTextEvent.start;
                final int end = accessibleTextEvent.end;
                final int charCount = this$0.getCharCount();
                int max = Math.max(0, Math.min(start, charCount));
                int max2 = Math.max(0, Math.min(end, charCount));
                if (max > max2) {
                    final int n = max;
                    max = max2;
                    max2 = n;
                }
                final int lineAtOffset = this$0.getLineAtOffset(max);
                final int lineAtOffset2 = this$0.getLineAtOffset(max2);
                final Rectangle[] rectangles = new Rectangle[lineAtOffset2 - lineAtOffset + 1];
                Rectangle rectangle = null;
                int n2 = 0;
                final Display display = this$0.getDisplay();
                for (int i = lineAtOffset; i <= lineAtOffset2; ++i) {
                    final Rectangle rectangle2 = new Rectangle(0, 0, 0, 0);
                    rectangle2.y = this$0.getLinePixel(i);
                    rectangle2.height = this$0.renderer.getLineHeight(i);
                    if (i == lineAtOffset) {
                        rectangle2.x = this$0.getPointAtOffset(max).x;
                    }
                    else {
                        rectangle2.x = this$0.leftMargin - this$0.horizontalScrollOffset;
                    }
                    if (i == lineAtOffset2) {
                        rectangle2.width = this$0.getPointAtOffset(max2).x - rectangle2.x;
                    }
                    else {
                        final TextLayout textLayout = this$0.renderer.getTextLayout(i);
                        rectangle2.width = textLayout.getBounds().width - rectangle2.x;
                        this$0.renderer.disposeTextLayout(textLayout);
                    }
                    final Rectangle rectangle3 = rectangles[n2++] = display.map(this$0, null, rectangle2);
                    if (rectangle == null) {
                        rectangle = new Rectangle(rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height);
                    }
                    else {
                        rectangle.add(rectangle3);
                    }
                }
                accessibleTextEvent.rectangles = rectangles;
                if (rectangle != null) {
                    accessibleTextEvent.x = rectangle.x;
                    accessibleTextEvent.y = rectangle.y;
                    accessibleTextEvent.width = rectangle.width;
                    accessibleTextEvent.height = rectangle.height;
                }
            }
            
            int[] getRanges(final int n, final int n2, final int n3, final int n4) {
                final StyledText this$0 = StyledText.this;
                final int lineIndex = this$0.getLineIndex(n2);
                final int lineIndex2 = this$0.getLineIndex(n4);
                final int[] array = new int[(lineIndex2 - lineIndex + 1) * 2];
                int n5 = 0;
                for (int i = lineIndex; i <= lineIndex2; ++i) {
                    final String line = this$0.content.getLine(i);
                    final int offsetAtLine = this$0.content.getOffsetAtLine(i);
                    final int n6 = offsetAtLine + line.length();
                    final int linePixel = this$0.getLinePixel(i);
                    int offsetAtPoint = this$0.getOffsetAtPoint(n, linePixel, null, true);
                    if (offsetAtPoint == -1) {
                        offsetAtPoint = ((n < this$0.leftMargin) ? offsetAtLine : n6);
                    }
                    final int[] array2 = { 0 };
                    final int offsetAtPoint2 = this$0.getOffsetAtPoint(n3, linePixel, array2, true);
                    int n7;
                    if (offsetAtPoint2 == -1) {
                        n7 = ((n3 < this$0.leftMargin) ? offsetAtLine : n6);
                    }
                    else {
                        n7 = offsetAtPoint2 + array2[0];
                    }
                    if (offsetAtPoint > n7) {
                        final int n8 = offsetAtPoint;
                        offsetAtPoint = n7;
                        n7 = n8;
                    }
                    array[n5++] = offsetAtPoint;
                    array[n5++] = n7;
                }
                return array;
            }
            
            public void getRanges(final AccessibleTextEvent accessibleTextEvent) {
                final StyledText this$0 = StyledText.this;
                final Point map = this$0.getDisplay().map(null, this$0, new Point(accessibleTextEvent.x, accessibleTextEvent.y));
                accessibleTextEvent.ranges = this.getRanges(map.x, map.y, map.x + accessibleTextEvent.width, map.y + accessibleTextEvent.height);
                if (accessibleTextEvent.ranges.length > 0) {
                    accessibleTextEvent.start = accessibleTextEvent.ranges[0];
                    accessibleTextEvent.end = accessibleTextEvent.ranges[accessibleTextEvent.ranges.length - 1];
                }
            }
            
            public void getText(final AccessibleTextEvent accessibleTextEvent) {
                final StyledText this$0 = StyledText.this;
                final int start = accessibleTextEvent.start;
                int end = accessibleTextEvent.end;
                final int charCount = this$0.getCharCount();
                if (end == -1) {
                    end = charCount;
                }
                int start2 = Math.max(0, Math.min(start, charCount));
                int end2 = Math.max(0, Math.min(end, charCount));
                if (start2 > end2) {
                    final int n = start2;
                    start2 = end2;
                    end2 = n;
                }
                int i = accessibleTextEvent.count;
                switch (accessibleTextEvent.type) {
                    case 0: {
                        int n2 = 0;
                        if (i > 0) {
                            while (i-- > 0) {
                                final int wordNext = this$0.getWordNext(end2, 2);
                                if (wordNext == charCount) {
                                    break;
                                }
                                if (wordNext == end2) {
                                    break;
                                }
                                end2 = wordNext;
                                ++n2;
                            }
                            start2 = end2;
                            end2 = this$0.getWordNext(end2, 2);
                        }
                        else {
                            while (i++ < 0) {
                                final int wordPrevious = this$0.getWordPrevious(start2, 2);
                                if (wordPrevious == start2) {
                                    break;
                                }
                                start2 = wordPrevious;
                                --n2;
                            }
                            end2 = this$0.getWordNext(start2, 2);
                        }
                        i = n2;
                        break;
                    }
                    case 1: {
                        int n3 = 0;
                        if (i > 0) {
                            while (i-- > 0) {
                                final int wordNext2 = this$0.getWordNext(end2, 16, true);
                                if (wordNext2 == end2) {
                                    break;
                                }
                                ++n3;
                                end2 = wordNext2;
                            }
                            start2 = end2;
                            end2 = this$0.getWordNext(start2, 8, true);
                        }
                        else {
                            if (this$0.getWordPrevious(Math.min(start2 + 1, charCount), 16, true) == start2) {
                                ++i;
                            }
                            while (i <= 0) {
                                final int wordPrevious2 = this$0.getWordPrevious(start2, 16, true);
                                if (wordPrevious2 == start2) {
                                    break;
                                }
                                ++i;
                                start2 = wordPrevious2;
                                if (i == 0) {
                                    continue;
                                }
                                --n3;
                            }
                            if (i <= 0 && start2 == 0) {
                                end2 = start2;
                            }
                            else {
                                end2 = this$0.getWordNext(start2, 8, true);
                            }
                        }
                        i = n3;
                        break;
                    }
                    case 2:
                    case 3:
                    case 4: {
                        final int n4 = (i > 0) ? end2 : start2;
                        final int max = Math.max(0, Math.min(this$0.getLineAtOffset(n4) + i, this$0.getLineCount() - 1));
                        start2 = this$0.getOffsetAtLine(max);
                        end2 = start2 + this$0.getLine(max).length();
                        i = max - this$0.getLineAtOffset(n4);
                        break;
                    }
                }
                accessibleTextEvent.start = start2;
                accessibleTextEvent.end = end2;
                accessibleTextEvent.count = i;
                accessibleTextEvent.result = this$0.content.getTextRange(start2, end2 - start2);
            }
            
            public void getVisibleRanges(final AccessibleTextEvent accessibleTextEvent) {
                accessibleTextEvent.ranges = this.getRanges(StyledText.this.leftMargin, StyledText.this.topMargin, StyledText.this.clientAreaWidth - StyledText.this.rightMargin, StyledText.this.clientAreaHeight - StyledText.this.bottomMargin);
                if (accessibleTextEvent.ranges.length > 0) {
                    accessibleTextEvent.start = accessibleTextEvent.ranges[0];
                    accessibleTextEvent.end = accessibleTextEvent.ranges[accessibleTextEvent.ranges.length - 1];
                }
            }
            
            public void scrollText(final AccessibleTextEvent accessibleTextEvent) {
                final StyledText this$0 = StyledText.this;
                int topPixel = StyledText.this.getTopPixel();
                int horizontalPixel = this$0.getHorizontalPixel();
                switch (accessibleTextEvent.type) {
                    case 0:
                    case 2:
                    case 4:
                    case 6: {
                        final Rectangle boundsAtOffset = this$0.getBoundsAtOffset(accessibleTextEvent.start);
                        if (accessibleTextEvent.type != 2) {
                            horizontalPixel = horizontalPixel + boundsAtOffset.x - this$0.leftMargin;
                        }
                        if (accessibleTextEvent.type != 4) {
                            topPixel = topPixel + boundsAtOffset.y - this$0.topMargin;
                            break;
                        }
                        break;
                    }
                    case 1:
                    case 3:
                    case 5: {
                        final Rectangle boundsAtOffset2 = this$0.getBoundsAtOffset(accessibleTextEvent.end - 1);
                        if (accessibleTextEvent.type != 3) {
                            horizontalPixel = horizontalPixel - this$0.clientAreaWidth + boundsAtOffset2.x + boundsAtOffset2.width + this$0.rightMargin;
                        }
                        if (accessibleTextEvent.type != 5) {
                            topPixel = topPixel - this$0.clientAreaHeight + boundsAtOffset2.y + boundsAtOffset2.height + this$0.bottomMargin;
                            break;
                        }
                        break;
                    }
                    case 7: {
                        final Point map = this$0.getDisplay().map(null, this$0, new Point(accessibleTextEvent.x, accessibleTextEvent.y));
                        final Rectangle boundsAtOffset3 = this$0.getBoundsAtOffset(accessibleTextEvent.start);
                        topPixel = topPixel - map.y + boundsAtOffset3.y;
                        horizontalPixel = horizontalPixel - map.x + boundsAtOffset3.x;
                        break;
                    }
                }
                this$0.setTopPixel(topPixel);
                this$0.setHorizontalPixel(horizontalPixel);
                accessibleTextEvent.result = "OK";
            }
        });
        accessible.addAccessibleAttributeListener(new AccessibleAttributeAdapter() {
            public void getAttributes(final AccessibleAttributeEvent accessibleAttributeEvent) {
                final StyledText this$0 = StyledText.this;
                accessibleAttributeEvent.leftMargin = this$0.getLeftMargin();
                accessibleAttributeEvent.topMargin = this$0.getTopMargin();
                accessibleAttributeEvent.rightMargin = this$0.getRightMargin();
                accessibleAttributeEvent.bottomMargin = this$0.getBottomMargin();
                accessibleAttributeEvent.tabStops = this$0.getTabStops();
                accessibleAttributeEvent.justify = this$0.getJustify();
                accessibleAttributeEvent.alignment = this$0.getAlignment();
                accessibleAttributeEvent.indent = this$0.getIndent();
            }
            
            public void getTextAttributes(final AccessibleTextAttributeEvent accessibleTextAttributeEvent) {
                final StyledText this$0 = StyledText.this;
                final int charCount = this$0.getCharCount();
                if (!StyledText.this.isListening(3002) && this$0.renderer.styleCount == 0) {
                    accessibleTextAttributeEvent.start = 0;
                    accessibleTextAttributeEvent.end = charCount;
                    accessibleTextAttributeEvent.textStyle = new TextStyle(this$0.getFont(), this$0.foreground, this$0.background);
                    return;
                }
                final int max = Math.max(0, Math.min(accessibleTextAttributeEvent.offset, charCount - 1));
                final int lineAtOffset = this$0.getLineAtOffset(max);
                final int offsetAtLine = this$0.getOffsetAtLine(lineAtOffset);
                final int lineCount = this$0.getLineCount();
                final int n = max - offsetAtLine;
                final TextLayout textLayout = this$0.renderer.getTextLayout(lineAtOffset);
                final int length = textLayout.getText().length();
                if (length > 0) {
                    accessibleTextAttributeEvent.textStyle = textLayout.getStyle(Math.max(0, Math.min(n, length - 1)));
                }
                if (accessibleTextAttributeEvent.textStyle == null) {
                    accessibleTextAttributeEvent.textStyle = new TextStyle(this$0.getFont(), this$0.foreground, this$0.background);
                }
                else if (accessibleTextAttributeEvent.textStyle.foreground == null || accessibleTextAttributeEvent.textStyle.background == null || accessibleTextAttributeEvent.textStyle.font == null) {
                    final TextStyle textStyle = new TextStyle(accessibleTextAttributeEvent.textStyle);
                    if (textStyle.foreground == null) {
                        textStyle.foreground = this$0.foreground;
                    }
                    if (textStyle.background == null) {
                        textStyle.background = this$0.background;
                    }
                    if (textStyle.font == null) {
                        textStyle.font = this$0.getFont();
                    }
                    accessibleTextAttributeEvent.textStyle = textStyle;
                }
                if (n >= length) {
                    accessibleTextAttributeEvent.start = offsetAtLine + length;
                    if (lineAtOffset + 1 < lineCount) {
                        accessibleTextAttributeEvent.end = this$0.getOffsetAtLine(lineAtOffset + 1);
                    }
                    else {
                        accessibleTextAttributeEvent.end = charCount;
                    }
                    return;
                }
                final int[] ranges = textLayout.getRanges();
                this$0.renderer.disposeTextLayout(textLayout);
                int i = 0;
                int n2 = 0;
                while (i < ranges.length) {
                    final int n3 = ranges[i++];
                    final int n4 = ranges[i++];
                    if (n3 <= n && n <= n4) {
                        accessibleTextAttributeEvent.start = offsetAtLine + n3;
                        accessibleTextAttributeEvent.end = offsetAtLine + n4 + 1;
                        return;
                    }
                    if (n3 > n) {
                        accessibleTextAttributeEvent.start = offsetAtLine + n2;
                        accessibleTextAttributeEvent.end = offsetAtLine + n3;
                        return;
                    }
                    n2 = n4 + 1;
                }
                if (i == ranges.length) {
                    accessibleTextAttributeEvent.start = offsetAtLine + n2;
                    if (lineAtOffset + 1 < lineCount) {
                        accessibleTextAttributeEvent.end = this$0.getOffsetAtLine(lineAtOffset + 1);
                    }
                    else {
                        accessibleTextAttributeEvent.end = charCount;
                    }
                }
            }
        });
        accessible.addAccessibleControlListener(new AccessibleControlAdapter() {
            public void getRole(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.detail = 42;
            }
            
            public void getState(final AccessibleControlEvent accessibleControlEvent) {
                int n = 0;
                if (StyledText.this.isEnabled()) {
                    n |= 0x100000;
                }
                if (StyledText.this.isFocusControl()) {
                    n |= 0x4;
                }
                if (!StyledText.this.isVisible()) {
                    n |= 0x8000;
                }
                if (!StyledText.this.getEditable()) {
                    n |= 0x40;
                }
                int detail;
                if (StyledText.this.isSingleLine()) {
                    detail = (n | 0x8000000);
                }
                else {
                    detail = (n | 0x10000000);
                }
                accessibleControlEvent.detail = detail;
            }
            
            public void getValue(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.result = StyledText.this.getText();
            }
        });
        this.addListener(15, new Listener() {
            public void handleEvent(final Event event) {
                accessible.setFocus(-1);
            }
        });
    }
    
    String getAssociatedLabel() {
        final Control[] children = this.getParent().getChildren();
        int i = 0;
        while (i < children.length) {
            if (children[i] == this) {
                if (i <= 0) {
                    break;
                }
                final Control control = children[i - 1];
                if (control instanceof Label) {
                    return ((Label)control).getText();
                }
                if (control instanceof CLabel) {
                    return ((CLabel)control).getText();
                }
                break;
            }
            else {
                ++i;
            }
        }
        return null;
    }
    
    String stripMnemonic(final String s) {
        int n = 0;
        final int length = s.length();
        while (true) {
            if (n >= length || s.charAt(n) == '&') {
                if (++n >= length) {
                    return s;
                }
                if (s.charAt(n) != '&') {
                    return String.valueOf(s.substring(0, n - 1)) + s.substring(n, length);
                }
                if (++n >= length) {
                    return s;
                }
                continue;
            }
            else {
                ++n;
            }
        }
    }
    
    char _findMnemonic(final String s) {
        if (s == null) {
            return '\0';
        }
        int n = 0;
        final int length = s.length();
        while (true) {
            if (n >= length || s.charAt(n) == '&') {
                if (++n >= length) {
                    return '\0';
                }
                if (s.charAt(n) != '&') {
                    return Character.toLowerCase(s.charAt(n));
                }
                if (++n >= length) {
                    return '\0';
                }
                continue;
            }
            else {
                ++n;
            }
        }
    }
    
    public void invokeAction(final int n) {
        this.checkWidget();
        if (this.blockSelection && this.invokeBlockAction(n)) {
            return;
        }
        this.updateCaretDirection = true;
        switch (n) {
            case 16777217: {
                this.doLineUp(false);
                this.clearSelection(true);
                break;
            }
            case 16777218: {
                this.doLineDown(false);
                this.clearSelection(true);
                break;
            }
            case 16777223: {
                this.doLineStart();
                this.clearSelection(true);
                break;
            }
            case 16777224: {
                this.doLineEnd();
                this.clearSelection(true);
                break;
            }
            case 16777219: {
                this.doCursorPrevious();
                this.clearSelection(true);
                break;
            }
            case 16777220: {
                this.doCursorNext();
                this.clearSelection(true);
                break;
            }
            case 16777221: {
                this.doPageUp(false, -1);
                this.clearSelection(true);
                break;
            }
            case 16777222: {
                this.doPageDown(false, -1);
                this.clearSelection(true);
                break;
            }
            case 17039363: {
                this.doWordPrevious();
                this.clearSelection(true);
                break;
            }
            case 17039364: {
                this.doWordNext();
                this.clearSelection(true);
                break;
            }
            case 17039367: {
                this.doContentStart();
                this.clearSelection(true);
                break;
            }
            case 17039368: {
                this.doContentEnd();
                this.clearSelection(true);
                break;
            }
            case 17039365: {
                this.doPageStart();
                this.clearSelection(true);
                break;
            }
            case 17039366: {
                this.doPageEnd();
                this.clearSelection(true);
                break;
            }
            case 16908289: {
                this.doSelectionLineUp();
                break;
            }
            case 262209: {
                this.selectAll();
                break;
            }
            case 16908290: {
                this.doSelectionLineDown();
                break;
            }
            case 16908295: {
                this.doLineStart();
                this.doSelection(16777219);
                break;
            }
            case 16908296: {
                this.doLineEnd();
                this.doSelection(16777220);
                break;
            }
            case 16908291: {
                this.doSelectionCursorPrevious();
                this.doSelection(16777219);
                break;
            }
            case 16908292: {
                this.doSelectionCursorNext();
                this.doSelection(16777220);
                break;
            }
            case 16908293: {
                this.doSelectionPageUp(-1);
                break;
            }
            case 16908294: {
                this.doSelectionPageDown(-1);
                break;
            }
            case 17170435: {
                this.doSelectionWordPrevious();
                this.doSelection(16777219);
                break;
            }
            case 17170436: {
                this.doSelectionWordNext();
                this.doSelection(16777220);
                break;
            }
            case 17170439: {
                this.doContentStart();
                this.doSelection(16777219);
                break;
            }
            case 17170440: {
                this.doContentEnd();
                this.doSelection(16777220);
                break;
            }
            case 17170437: {
                this.doPageStart();
                this.doSelection(16777219);
                break;
            }
            case 17170438: {
                this.doPageEnd();
                this.doSelection(16777220);
                break;
            }
            case 131199: {
                this.cut();
                break;
            }
            case 17039369: {
                this.copy();
                break;
            }
            case 16908297: {
                this.paste();
                break;
            }
            case 8: {
                this.doBackspace();
                break;
            }
            case 127: {
                this.doDelete();
                break;
            }
            case 262152: {
                this.doDeleteWordPrevious();
                break;
            }
            case 262271: {
                this.doDeleteWordNext();
                break;
            }
            case 16777225: {
                this.overwrite = !this.overwrite;
                break;
            }
            case 16777226: {
                this.setBlockSelection(!this.blockSelection);
                break;
            }
        }
    }
    
    boolean invokeBlockAction(final int n) {
        switch (n) {
            case 16777217:
            case 16777218:
            case 16777219:
            case 16777220:
            case 16777221:
            case 16777222:
            case 16777223:
            case 16777224:
            case 17039363:
            case 17039364:
            case 17039365:
            case 17039366:
            case 17039367:
            case 17039368: {
                this.clearBlockSelection(false, false);
                return false;
            }
            case 16908289: {
                this.doBlockLineVertical(true);
                return true;
            }
            case 16908290: {
                this.doBlockLineVertical(false);
                return true;
            }
            case 16908295: {
                this.doBlockLineHorizontal(false);
                return true;
            }
            case 16908296: {
                this.doBlockLineHorizontal(true);
                return false;
            }
            case 16908291: {
                this.doBlockColumn(false);
                return true;
            }
            case 16908292: {
                this.doBlockColumn(true);
                return true;
            }
            case 17170435: {
                this.doBlockWord(false);
                return true;
            }
            case 17170436: {
                this.doBlockWord(true);
                return true;
            }
            case 262209: {
                return false;
            }
            case 16908293:
            case 16908294:
            case 17170437:
            case 17170438:
            case 17170439:
            case 17170440: {
                return true;
            }
            case 131199:
            case 16908297:
            case 17039369: {
                return false;
            }
            case 8:
            case 127: {
                if (this.blockXLocation != -1) {
                    this.insertBlockSelectionText('\0', n);
                    return true;
                }
                return false;
            }
            case 262152:
            case 262271: {
                return this.blockXLocation != -1;
            }
            default: {
                return false;
            }
        }
    }
    
    boolean isBidi() {
        return StyledText.IS_GTK || StyledText.IS_MAC || BidiUtil.isBidiPlatform() || this.isMirrored();
    }
    
    boolean isBidiCaret() {
        return BidiUtil.isBidiPlatform();
    }
    
    boolean isFixedLineHeight() {
        return this.fixedLineHeight;
    }
    
    boolean isLineDelimiter(final int n) {
        final int lineAtOffset = this.content.getLineAtOffset(n);
        return n - this.content.getOffsetAtLine(lineAtOffset) > this.content.getLine(lineAtOffset).length();
    }
    
    boolean isMirrored() {
        return (this.getStyle() & 0x8000000) != 0x0;
    }
    
    boolean isSingleLine() {
        return (this.getStyle() & 0x4) != 0x0;
    }
    
    void modifyContent(final Event event, final boolean b) {
        event.doit = true;
        this.notifyListeners(25, event);
        if (event.doit) {
            StyledTextEvent styledTextEvent = null;
            final int n = event.end - event.start;
            if (this.isListening(3000)) {
                styledTextEvent = new StyledTextEvent(this.content);
                styledTextEvent.start = event.start;
                styledTextEvent.end = event.start + event.text.length();
                styledTextEvent.text = this.content.getTextRange(event.start, n);
            }
            if (b && event.text.length() == 0) {
                final int lineAtOffset = this.content.getLineAtOffset(event.start);
                int n2 = this.content.getOffsetAtLine(lineAtOffset);
                TextLayout textLayout = this.renderer.getTextLayout(lineAtOffset);
                final int level = textLayout.getLevel(event.start - n2);
                final int lineAtOffset2 = this.content.getLineAtOffset(event.end);
                if (lineAtOffset != lineAtOffset2) {
                    this.renderer.disposeTextLayout(textLayout);
                    n2 = this.content.getOffsetAtLine(lineAtOffset2);
                    textLayout = this.renderer.getTextLayout(lineAtOffset2);
                }
                final int level2 = textLayout.getLevel(event.end - n2);
                this.renderer.disposeTextLayout(textLayout);
                if (level != level2) {
                    this.caretAlignment = 0;
                }
                else {
                    this.caretAlignment = 1;
                }
            }
            this.content.replaceTextRange(event.start, n, event.text);
            if (b && (!this.blockSelection || this.blockXLocation == -1)) {
                this.setSelection(event.start + event.text.length(), 0, true, false);
                this.showCaret();
            }
            this.notifyListeners(24, event);
            if (this.isListening(3000)) {
                this.notifyListeners(3000, styledTextEvent);
            }
        }
    }
    
    void paintObject(final GC gc, final int x, final int y, final int ascent, final int descent, final StyleRange style, final Bullet bullet, final int bulletIndex) {
        if (this.isListening(3008)) {
            final StyledTextEvent styledTextEvent = new StyledTextEvent(this.content);
            styledTextEvent.gc = gc;
            styledTextEvent.x = x;
            styledTextEvent.y = y;
            styledTextEvent.ascent = ascent;
            styledTextEvent.descent = descent;
            styledTextEvent.style = style;
            styledTextEvent.bullet = bullet;
            styledTextEvent.bulletIndex = bulletIndex;
            this.notifyListeners(3008, styledTextEvent);
        }
    }
    
    public void paste() {
        this.checkWidget();
        final String s = (String)this.getClipboardContent(1);
        if (s != null && s.length() > 0) {
            if (this.blockSelection) {
                this.setCaretOffset(this.insertBlockSelectionText(s, this.isFixedLineHeight() && this.renderer.fixedPitch), -1);
                this.clearBlockSelection(true, true);
                this.setCaretLocation();
                return;
            }
            final Event event = new Event();
            event.start = this.selection.x;
            event.end = this.selection.y;
            event.text = this.getModelDelimitedText(s);
            this.sendKeyEvent(event);
        }
    }
    
    public void print() {
        this.checkWidget();
        final Printer printer = new Printer();
        final StyledTextPrintOptions styledTextPrintOptions = new StyledTextPrintOptions();
        styledTextPrintOptions.printTextForeground = true;
        styledTextPrintOptions.printTextBackground = true;
        styledTextPrintOptions.printTextFontStyle = true;
        styledTextPrintOptions.printLineBackground = true;
        new Printing(this, printer, styledTextPrintOptions).run();
        printer.dispose();
    }
    
    public Runnable print(final Printer printer) {
        this.checkWidget();
        if (printer == null) {
            SWT.error(4);
        }
        final StyledTextPrintOptions styledTextPrintOptions = new StyledTextPrintOptions();
        styledTextPrintOptions.printTextForeground = true;
        styledTextPrintOptions.printTextBackground = true;
        styledTextPrintOptions.printTextFontStyle = true;
        styledTextPrintOptions.printLineBackground = true;
        return this.print(printer, styledTextPrintOptions);
    }
    
    public Runnable print(final Printer printer, final StyledTextPrintOptions styledTextPrintOptions) {
        this.checkWidget();
        if (printer == null || styledTextPrintOptions == null) {
            SWT.error(4);
        }
        return new Printing(this, printer, styledTextPrintOptions);
    }
    
    public void redraw() {
        super.redraw();
        final int n = this.getPartialBottomIndex() - this.topIndex + 1;
        this.renderer.reset(this.topIndex, n);
        this.renderer.calculate(this.topIndex, n);
        this.setScrollBars(false);
        this.doMouseLinkCursor();
    }
    
    public void redraw(final int n, final int n2, final int n3, final int n4, final boolean b) {
        super.redraw(n, n2, n3, n4, b);
        if (n4 > 0) {
            final int lineIndex = this.getLineIndex(n2);
            this.resetCache(lineIndex, this.getLineIndex(n2 + n4) - lineIndex + 1);
            this.doMouseLinkCursor();
        }
    }
    
    void redrawLines(int n, final int n2, final boolean b) {
        int n3 = n + n2 - 1;
        final int partialBottomIndex = this.getPartialBottomIndex();
        final int partialTopIndex = this.getPartialTopIndex();
        if (n > partialBottomIndex || n3 < partialTopIndex) {
            return;
        }
        if (n < partialTopIndex) {
            n = partialTopIndex;
        }
        if (n3 > partialBottomIndex) {
            n3 = partialBottomIndex;
        }
        final int linePixel = this.getLinePixel(n);
        int linePixel2 = this.getLinePixel(n3 + 1);
        if (b) {
            linePixel2 = this.clientAreaHeight - this.bottomMargin;
        }
        super.redraw(this.leftMargin, linePixel, this.clientAreaWidth - this.leftMargin - this.rightMargin, linePixel2 - linePixel, true);
    }
    
    void redrawLinesBullet(final int[] array) {
        if (array == null) {
            return;
        }
        final int partialTopIndex = this.getPartialTopIndex();
        final int partialBottomIndex = this.getPartialBottomIndex();
        for (int i = 0; i < array.length; ++i) {
            final int n = array[i];
            if (partialTopIndex <= n) {
                if (n <= partialBottomIndex) {
                    int n2 = -1;
                    final Bullet lineBullet = this.renderer.getLineBullet(n, null);
                    if (lineBullet != null) {
                        n2 = lineBullet.style.metrics.width;
                    }
                    if (n2 == -1) {
                        n2 = this.getClientArea().width;
                    }
                    super.redraw(0, this.getLinePixel(n), n2, this.renderer.getLineHeight(n), false);
                }
            }
        }
    }
    
    public void redrawRange(final int n, final int n2, final boolean b) {
        this.checkWidget();
        final int n3 = n + n2;
        final int charCount = this.content.getCharCount();
        if (n > n3 || n < 0 || n3 > charCount) {
            SWT.error(6);
        }
        final int lineAtOffset = this.content.getLineAtOffset(n);
        this.resetCache(lineAtOffset, this.content.getLineAtOffset(n3) - lineAtOffset + 1);
        this.internalRedrawRange(n, n2);
        this.doMouseLinkCursor();
    }
    
    public void removeBidiSegmentListener(final BidiSegmentListener bidiSegmentListener) {
        this.checkWidget();
        if (bidiSegmentListener == null) {
            SWT.error(4);
        }
        this.removeListener(3007, bidiSegmentListener);
    }
    
    public void removeCaretListener(final CaretListener caretListener) {
        this.checkWidget();
        if (caretListener == null) {
            SWT.error(4);
        }
        this.removeListener(3011, caretListener);
    }
    
    public void removeExtendedModifyListener(final ExtendedModifyListener extendedModifyListener) {
        this.checkWidget();
        if (extendedModifyListener == null) {
            SWT.error(4);
        }
        this.removeListener(3000, extendedModifyListener);
    }
    
    public void removeLineBackgroundListener(final LineBackgroundListener lineBackgroundListener) {
        this.checkWidget();
        if (lineBackgroundListener == null) {
            SWT.error(4);
        }
        this.removeListener(3001, lineBackgroundListener);
    }
    
    public void removeLineStyleListener(final LineStyleListener lineStyleListener) {
        this.checkWidget();
        if (lineStyleListener == null) {
            SWT.error(4);
        }
        this.removeListener(3002, lineStyleListener);
        this.setCaretLocation();
    }
    
    public void removeModifyListener(final ModifyListener modifyListener) {
        this.checkWidget();
        if (modifyListener == null) {
            SWT.error(4);
        }
        this.removeListener(24, modifyListener);
    }
    
    public void removePaintObjectListener(final PaintObjectListener paintObjectListener) {
        this.checkWidget();
        if (paintObjectListener == null) {
            SWT.error(4);
        }
        this.removeListener(3008, paintObjectListener);
    }
    
    public void removeSelectionListener(final SelectionListener selectionListener) {
        this.checkWidget();
        if (selectionListener == null) {
            SWT.error(4);
        }
        this.removeListener(13, selectionListener);
    }
    
    public void removeVerifyListener(final VerifyListener verifyListener) {
        this.checkWidget();
        if (verifyListener == null) {
            SWT.error(4);
        }
        this.removeListener(25, verifyListener);
    }
    
    public void removeVerifyKeyListener(final VerifyKeyListener verifyKeyListener) {
        if (verifyKeyListener == null) {
            SWT.error(4);
        }
        this.removeListener(3005, verifyKeyListener);
    }
    
    public void removeWordMovementListener(final MovementListener movementListener) {
        this.checkWidget();
        if (movementListener == null) {
            SWT.error(4);
        }
        this.removeListener(3009, movementListener);
        this.removeListener(3010, movementListener);
    }
    
    public void replaceStyleRanges(final int n, final int n2, final StyleRange[] array) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (array == null) {
            SWT.error(4);
        }
        this.setStyleRanges(n, n2, null, array, false);
    }
    
    public void replaceTextRange(final int start, final int n, final String text) {
        this.checkWidget();
        if (text == null) {
            SWT.error(4);
        }
        final int charCount = this.getCharCount();
        final int end = start + n;
        if (start > end || start < 0 || end > charCount) {
            SWT.error(6);
        }
        final Event event = new Event();
        event.start = start;
        event.end = end;
        event.text = text;
        this.modifyContent(event, false);
    }
    
    void reset() {
        final ScrollBar verticalBar = this.getVerticalBar();
        final ScrollBar horizontalBar = this.getHorizontalBar();
        this.setCaretOffset(0, -1);
        this.topIndex = 0;
        this.topIndexY = 0;
        this.verticalScrollOffset = 0;
        this.horizontalScrollOffset = 0;
        this.resetSelection();
        this.renderer.setContent(this.content);
        if (verticalBar != null) {
            verticalBar.setSelection(0);
        }
        if (horizontalBar != null) {
            horizontalBar.setSelection(0);
        }
        this.resetCache(0, 0);
        this.setCaretLocation();
        super.redraw();
    }
    
    void resetCache(final int n, final int n2) {
        final int maxWidthLineIndex = this.renderer.maxWidthLineIndex;
        this.renderer.reset(n, n2);
        this.renderer.calculateClientArea();
        if (maxWidthLineIndex >= 0 && maxWidthLineIndex < this.content.getLineCount()) {
            this.renderer.calculate(maxWidthLineIndex, 1);
        }
        this.setScrollBars(true);
        if (!this.isFixedLineHeight()) {
            if (this.topIndex > n) {
                this.verticalScrollOffset = -1;
            }
            this.renderer.calculateIdle();
        }
    }
    
    void resetSelection() {
        final Point selection = this.selection;
        final Point selection2 = this.selection;
        final int caretOffset = this.caretOffset;
        selection2.y = caretOffset;
        selection.x = caretOffset;
        this.selectionAnchor = -1;
        this.sendAccessibleTextCaretMoved();
    }
    
    public void scroll(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b) {
        super.scroll(n, n2, n3, n4, n5, n6, false);
        if (b) {
            final int n7 = n - n3;
            final int n8 = n2 - n4;
            final Control[] children = this.getChildren();
            for (int i = 0; i < children.length; ++i) {
                final Control control = children[i];
                final Rectangle bounds = control.getBounds();
                control.setLocation(bounds.x + n7, bounds.y + n8);
            }
        }
    }
    
    boolean scrollHorizontal(final int n, final boolean b) {
        if (n == 0) {
            return false;
        }
        if (this.wordWrap) {
            return false;
        }
        final ScrollBar horizontalBar = this.getHorizontalBar();
        if (horizontalBar != null && b) {
            horizontalBar.setSelection(this.horizontalScrollOffset + n);
        }
        final int n2 = this.clientAreaHeight - this.topMargin - this.bottomMargin;
        if (n > 0) {
            final int n3 = this.leftMargin + n;
            final int n4 = this.clientAreaWidth - n3 - this.rightMargin;
            if (n4 > 0) {
                this.scroll(this.leftMargin, this.topMargin, n3, this.topMargin, n4, n2, true);
            }
            if (n3 > n4) {
                super.redraw(this.leftMargin + n4, this.topMargin, n - n4, n2, true);
            }
        }
        else {
            final int n5 = this.leftMargin - n;
            final int n6 = this.clientAreaWidth - n5 - this.rightMargin;
            if (n6 > 0) {
                this.scroll(n5, this.topMargin, this.leftMargin, this.topMargin, n6, n2, true);
            }
            if (n5 > n6) {
                super.redraw(this.leftMargin + n6, this.topMargin, -n - n6, n2, true);
            }
        }
        this.horizontalScrollOffset += n;
        this.setCaretLocation();
        return true;
    }
    
    boolean scrollVertical(final int n, final boolean b) {
        if (n == 0) {
            return false;
        }
        if (this.verticalScrollOffset != -1) {
            final ScrollBar verticalBar = this.getVerticalBar();
            if (verticalBar != null && b) {
                verticalBar.setSelection(this.verticalScrollOffset + n);
            }
            final int n2 = this.clientAreaWidth - this.leftMargin - this.rightMargin;
            if (n > 0) {
                final int n3 = this.topMargin + n;
                final int n4 = this.clientAreaHeight - n3 - this.bottomMargin;
                if (n4 > 0) {
                    this.scroll(this.leftMargin, this.topMargin, this.leftMargin, n3, n2, n4, true);
                }
                if (n3 > n4) {
                    super.redraw(this.leftMargin, Math.max(0, this.topMargin + n4), n2, Math.min(this.clientAreaHeight, n - n4), true);
                }
            }
            else {
                final int n5 = this.topMargin - n;
                final int n6 = this.clientAreaHeight - n5 - this.bottomMargin;
                if (n6 > 0) {
                    this.scroll(this.leftMargin, n5, this.leftMargin, this.topMargin, n2, n6, true);
                }
                if (n5 > n6) {
                    super.redraw(this.leftMargin, Math.max(0, this.topMargin + n6), n2, Math.min(this.clientAreaHeight, -n - n6), true);
                }
            }
            this.verticalScrollOffset += n;
            this.calculateTopIndex(n);
        }
        else {
            this.calculateTopIndex(n);
            super.redraw();
        }
        this.setCaretLocation();
        return true;
    }
    
    void scrollText(final int n, final int n2) {
        if (n == n2) {
            return;
        }
        final int n3 = n2 - n;
        final int n4 = this.clientAreaWidth - this.leftMargin - this.rightMargin;
        int n5;
        if (n3 > 0) {
            n5 = this.clientAreaHeight - n - this.bottomMargin;
        }
        else {
            n5 = this.clientAreaHeight - n2 - this.bottomMargin;
        }
        this.scroll(this.leftMargin, n2, this.leftMargin, n, n4, n5, true);
        if (n + n5 > 0 && this.topMargin > n) {
            super.redraw(this.leftMargin, n3, n4, this.topMargin, false);
        }
        if (n2 + n5 > 0 && this.topMargin > n2) {
            super.redraw(this.leftMargin, 0, n4, this.topMargin, false);
        }
        if (this.clientAreaHeight - this.bottomMargin < n + n5 && this.clientAreaHeight > n) {
            super.redraw(this.leftMargin, this.clientAreaHeight - this.bottomMargin + n3, n4, this.bottomMargin, false);
        }
        if (this.clientAreaHeight - this.bottomMargin < n2 + n5 && this.clientAreaHeight > n2) {
            super.redraw(this.leftMargin, this.clientAreaHeight - this.bottomMargin, n4, this.bottomMargin, false);
        }
    }
    
    void sendAccessibleTextCaretMoved() {
        if (this.caretOffset != this.accCaretOffset) {
            this.accCaretOffset = this.caretOffset;
            this.getAccessible().textCaretMoved(this.caretOffset);
        }
    }
    
    void sendAccessibleTextChanged(final int n, final int n2, final int n3) {
        final Accessible accessible = this.getAccessible();
        if (n3 != 0) {
            accessible.textChanged(1, n, n3);
        }
        if (n2 != 0) {
            accessible.textChanged(0, n, n2);
        }
    }
    
    public void selectAll() {
        this.checkWidget();
        if (this.blockSelection) {
            this.renderer.calculate(0, this.content.getLineCount());
            this.setScrollBars(false);
            final int verticalScrollOffset = this.getVerticalScrollOffset();
            this.setBlockSelectionLocation(this.leftMargin - this.horizontalScrollOffset, this.topMargin - verticalScrollOffset, this.renderer.getWidth() - this.rightMargin - this.horizontalScrollOffset, this.renderer.getHeight() - this.bottomMargin - verticalScrollOffset, false);
            return;
        }
        this.setSelection(0, Math.max(this.getCharCount(), 0));
    }
    
    void sendKeyEvent(final Event event) {
        if (this.editable) {
            this.modifyContent(event, true);
        }
    }
    
    StyledTextEvent sendLineEvent(final int n, final int detail, final String text) {
        StyledTextEvent styledTextEvent = null;
        if (this.isListening(n)) {
            styledTextEvent = new StyledTextEvent(this.content);
            styledTextEvent.detail = detail;
            styledTextEvent.text = text;
            styledTextEvent.alignment = this.alignment;
            styledTextEvent.indent = this.indent;
            styledTextEvent.wrapIndent = this.wrapIndent;
            styledTextEvent.justify = this.justify;
            this.notifyListeners(n, styledTextEvent);
        }
        return styledTextEvent;
    }
    
    void sendSelectionEvent() {
        this.getAccessible().textSelectionChanged();
        final Event event = new Event();
        event.x = this.selection.x;
        event.y = this.selection.y;
        this.notifyListeners(13, event);
    }
    
    int sendTextEvent(final int n, final int n2, final int n3, final String s, boolean b) {
        int width = 0;
        final StringBuffer sb = new StringBuffer();
        int charCount;
        int end;
        if (n3 < this.content.getLineCount()) {
            final int[] array = { 0 };
            final int offsetAtPoint = this.getOffsetAtPoint(n, this.getLinePixel(n3), array, true);
            if (offsetAtPoint == -1) {
                end = (charCount = this.content.getOffsetAtLine(n3) + this.content.getLine(n3).length());
                if (b) {
                    final TextLayout textLayout = this.renderer.getTextLayout(n3);
                    width = textLayout.getBounds().width;
                    this.renderer.disposeTextLayout(textLayout);
                }
            }
            else {
                charCount = offsetAtPoint + array[0];
                end = ((n == n2) ? charCount : this.getOffsetAtPoint(n2, 0, n3, null));
                b = false;
            }
        }
        else {
            end = (charCount = this.content.getCharCount());
            sb.append(this.content.getLineDelimiter());
        }
        if (charCount > end) {
            final int n4 = charCount;
            charCount = end;
            end = n4;
        }
        if (b) {
            for (int n5 = (n - width + this.horizontalScrollOffset - this.leftMargin) / this.renderer.averageCharWidth, i = 0; i < n5; ++i) {
                sb.append(' ');
            }
        }
        sb.append(s);
        final Event event = new Event();
        event.start = charCount;
        event.end = end;
        event.text = sb.toString();
        this.sendKeyEvent(event);
        return event.start + event.text.length();
    }
    
    int sendWordBoundaryEvent(final int n, final int count, int end, final int end2, final String text, final int detail) {
        if (this.isListening(n)) {
            final StyledTextEvent styledTextEvent = new StyledTextEvent(this.content);
            styledTextEvent.detail = detail;
            styledTextEvent.text = text;
            styledTextEvent.count = count;
            styledTextEvent.start = end;
            styledTextEvent.end = end2;
            this.notifyListeners(n, styledTextEvent);
            end = styledTextEvent.end;
            if (end != end2) {
                final int charCount = this.getCharCount();
                if (end < 0) {
                    end = 0;
                }
                else if (end > charCount) {
                    end = charCount;
                }
                else if (this.isLineDelimiter(end)) {
                    SWT.error(5);
                }
            }
            return end;
        }
        return end2;
    }
    
    void setAlignment() {
        if ((this.getStyle() & 0x4) == 0x0) {
            return;
        }
        final int lineAlignment = this.renderer.getLineAlignment(0, this.alignment);
        int alignmentMargin = 0;
        if (lineAlignment != 16384) {
            this.renderer.calculate(0, 1);
            alignmentMargin = this.clientAreaWidth - (this.renderer.getWidth() - this.alignmentMargin);
            if (alignmentMargin < 0) {
                alignmentMargin = 0;
            }
            if (lineAlignment == 16777216) {
                alignmentMargin /= 2;
            }
        }
        if (this.alignmentMargin != alignmentMargin) {
            this.leftMargin -= this.alignmentMargin;
            this.leftMargin += alignmentMargin;
            this.alignmentMargin = alignmentMargin;
            this.resetCache(0, 1);
            this.setCaretLocation();
            super.redraw();
        }
    }
    
    public void setAlignment(int alignment) {
        this.checkWidget();
        alignment &= 0x1024000;
        if (alignment == 0 || this.alignment == alignment) {
            return;
        }
        this.alignment = alignment;
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocation();
        this.setAlignment();
        super.redraw();
    }
    
    public void setBackground(final Color background) {
        this.checkWidget();
        super.setBackground(this.background = background);
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocation();
        super.redraw();
    }
    
    public void setBlockSelection(final boolean blockSelection) {
        this.checkWidget();
        if ((this.getStyle() & 0x4) != 0x0) {
            return;
        }
        if (blockSelection == this.blockSelection) {
            return;
        }
        if (this.wordWrap) {
            return;
        }
        this.blockSelection = blockSelection;
        if (this.cursor == null) {
            super.setCursor(this.getDisplay().getSystemCursor(blockSelection ? 2 : 19));
        }
        if (blockSelection) {
            final int x = this.selection.x;
            final int y = this.selection.y;
            if (x != y) {
                this.setBlockSelectionOffset(x, y, false);
            }
        }
        else {
            this.clearBlockSelection(false, false);
        }
    }
    
    public void setBlockSelectionBounds(final Rectangle rectangle) {
        this.checkWidget();
        if (rectangle == null) {
            SWT.error(4);
        }
        this.setBlockSelectionBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public void setBlockSelectionBounds(int n, int n2, final int n3, final int n4) {
        this.checkWidget();
        final int verticalScrollOffset = this.getVerticalScrollOffset();
        if (!this.blockSelection) {
            n -= this.horizontalScrollOffset;
            n2 -= verticalScrollOffset;
            final int offsetAtPoint = this.getOffsetAtPoint(n, n2, null);
            this.setSelection(offsetAtPoint, this.getOffsetAtPoint(n + n3 - 1, n2 + n4 - 1, null) - offsetAtPoint, false, false);
            this.setCaretLocation();
            return;
        }
        final int topMargin = this.topMargin;
        final int leftMargin = this.leftMargin;
        final int n5 = this.renderer.getHeight() - this.bottomMargin;
        final int n6 = Math.max(this.clientAreaWidth, this.renderer.getWidth()) - this.rightMargin;
        int n7 = Math.max(leftMargin, Math.min(n6, n)) - this.horizontalScrollOffset;
        final int n8 = Math.max(topMargin, Math.min(n5, n2)) - verticalScrollOffset;
        int n9 = Math.max(leftMargin, Math.min(n6, n + n3)) - this.horizontalScrollOffset;
        final int n10 = Math.max(topMargin, Math.min(n5, n2 + n4 - 1)) - verticalScrollOffset;
        if (this.isFixedLineHeight() && this.renderer.fixedPitch) {
            final int averageCharWidth = this.renderer.averageCharWidth;
            n7 = (n7 - this.leftMargin + this.horizontalScrollOffset) / averageCharWidth * averageCharWidth + this.leftMargin - this.horizontalScrollOffset;
            n9 = (n9 + averageCharWidth / 2 - this.leftMargin + this.horizontalScrollOffset) / averageCharWidth * averageCharWidth + this.leftMargin - this.horizontalScrollOffset;
        }
        this.setBlockSelectionLocation(n7, n8, n9, n10, false);
    }
    
    void setBlockSelectionLocation(final int n, final int n2, final boolean b) {
        final int verticalScrollOffset = this.getVerticalScrollOffset();
        this.blockXLocation = n + this.horizontalScrollOffset;
        this.blockYLocation = n2 + verticalScrollOffset;
        final int[] array = { 0 };
        this.setCaretOffset(this.getOffsetAtPoint(n, n2, array), array[0]);
        if (this.blockXAnchor == -1) {
            this.blockXAnchor = this.blockXLocation;
            this.blockYAnchor = this.blockYLocation;
            this.selectionAnchor = this.caretOffset;
        }
        this.doBlockSelection(b);
    }
    
    void setBlockSelectionLocation(final int n, final int n2, final int n3, final int n4, final boolean b) {
        final int verticalScrollOffset = this.getVerticalScrollOffset();
        this.blockXAnchor = n + this.horizontalScrollOffset;
        this.blockYAnchor = n2 + verticalScrollOffset;
        this.selectionAnchor = this.getOffsetAtPoint(n, n2, null);
        this.setBlockSelectionLocation(n3, n4, b);
    }
    
    void setBlockSelectionOffset(final int n, final boolean b) {
        final Point pointAtOffset = this.getPointAtOffset(n);
        final int verticalScrollOffset = this.getVerticalScrollOffset();
        this.blockXLocation = pointAtOffset.x + this.horizontalScrollOffset;
        this.blockYLocation = pointAtOffset.y + verticalScrollOffset;
        this.setCaretOffset(n, -1);
        if (this.blockXAnchor == -1) {
            this.blockXAnchor = this.blockXLocation;
            this.blockYAnchor = this.blockYLocation;
            this.selectionAnchor = this.caretOffset;
        }
        this.doBlockSelection(b);
    }
    
    void setBlockSelectionOffset(final int selectionAnchor, final int n, final boolean b) {
        final int verticalScrollOffset = this.getVerticalScrollOffset();
        final Point pointAtOffset = this.getPointAtOffset(selectionAnchor);
        this.blockXAnchor = pointAtOffset.x + this.horizontalScrollOffset;
        this.blockYAnchor = pointAtOffset.y + verticalScrollOffset;
        this.selectionAnchor = selectionAnchor;
        this.setBlockSelectionOffset(n, b);
    }
    
    public void setCaret(final Caret caret) {
        this.checkWidget();
        super.setCaret(caret);
        this.caretDirection = 0;
        if (caret != null) {
            this.setCaretLocation();
        }
    }
    
    public void setBidiColoring(final boolean bidiColoring) {
        this.checkWidget();
        this.bidiColoring = bidiColoring;
    }
    
    public void setBottomMargin(final int n) {
        this.checkWidget();
        this.setMargins(this.leftMargin, this.topMargin, this.rightMargin, n);
    }
    
    void setCaretLocation() {
        this.setCaretLocation(this.getPointAtOffset(this.caretOffset), this.getCaretDirection());
    }
    
    void setCaretLocation(final Point location, int caretDirection) {
        final Caret caret = this.getCaret();
        if (caret != null) {
            final boolean b = caret == this.defaultCaret;
            int n2;
            final int n = n2 = this.renderer.getLineHeight();
            if (!this.isFixedLineHeight() && b) {
                n2 = this.getBoundsAtOffset(this.caretOffset).height;
                if (n2 != n) {
                    caretDirection = -1;
                }
            }
            int n3 = caretDirection;
            if (this.isMirrored()) {
                if (n3 == 16384) {
                    n3 = 131072;
                }
                else if (n3 == 131072) {
                    n3 = 16384;
                }
            }
            if (b && n3 == 131072) {
                location.x -= caret.getSize().x - 1;
            }
            if (b) {
                caret.setBounds(location.x, location.y, this.caretWidth, n2);
            }
            else {
                caret.setLocation(location);
            }
            if (caretDirection != this.caretDirection) {
                this.caretDirection = caretDirection;
                if (b) {
                    if (n3 == -1) {
                        this.defaultCaret.setImage(null);
                    }
                    else if (n3 == 16384) {
                        this.defaultCaret.setImage(this.leftCaretBitmap);
                    }
                    else if (n3 == 131072) {
                        this.defaultCaret.setImage(this.rightCaretBitmap);
                    }
                }
                if (this.caretDirection == 16384) {
                    BidiUtil.setKeyboardLanguage(0);
                }
                else if (this.caretDirection == 131072) {
                    BidiUtil.setKeyboardLanguage(1);
                }
            }
            this.updateCaretVisibility();
        }
        this.columnX = location.x;
    }
    
    public void setCaretOffset(int n) {
        this.checkWidget();
        final int charCount = this.getCharCount();
        if (charCount > 0 && n != this.caretOffset) {
            if (n < 0) {
                n = 0;
            }
            else if (n > charCount) {
                n = charCount;
            }
            else if (this.isLineDelimiter(n)) {
                SWT.error(5);
            }
            this.setCaretOffset(n, 0);
            if (this.blockSelection) {
                this.clearBlockSelection(true, false);
            }
            else {
                this.clearSelection(false);
            }
        }
        this.setCaretLocation();
    }
    
    void setCaretOffset(final int caretOffset, final int caretAlignment) {
        if (this.caretOffset != caretOffset) {
            this.caretOffset = caretOffset;
            if (this.isListening(3011)) {
                final StyledTextEvent styledTextEvent = new StyledTextEvent(this.content);
                styledTextEvent.end = this.caretOffset;
                this.notifyListeners(3011, styledTextEvent);
            }
        }
        if (caretAlignment != -1) {
            this.caretAlignment = caretAlignment;
        }
    }
    
    void setClipboardContent(final int n, final int n2, final int n3) throws SWTError {
        if (n3 == 2 && !StyledText.IS_MOTIF && !StyledText.IS_GTK) {
            return;
        }
        final TextTransfer instance = TextTransfer.getInstance();
        final String platformDelimitedText = this.getPlatformDelimitedText(new TextWriter(n, n2));
        Object[] array;
        Transfer[] array2;
        if (n3 == 2) {
            array = new Object[] { platformDelimitedText };
            array2 = new Transfer[] { instance };
        }
        else {
            final RTFTransfer instance2 = RTFTransfer.getInstance();
            array = new Object[] { this.getPlatformDelimitedText(new RTFWriter(n, n2)), platformDelimitedText };
            array2 = new Transfer[] { instance2, instance };
        }
        this.clipboard.setContents(array, array2, n3);
    }
    
    public void setContent(final StyledTextContent content) {
        this.checkWidget();
        if (content == null) {
            SWT.error(4);
        }
        if (this.content != null) {
            this.content.removeTextChangeListener(this.textChangeListener);
        }
        (this.content = content).addTextChangeListener(this.textChangeListener);
        this.reset();
    }
    
    public void setCursor(final Cursor cursor) {
        this.checkWidget();
        if (cursor != null && cursor.isDisposed()) {
            SWT.error(5);
        }
        if ((this.cursor = cursor) == null) {
            super.setCursor(this.getDisplay().getSystemCursor(this.blockSelection ? 2 : 19));
        }
        else {
            super.setCursor(cursor);
        }
    }
    
    public void setDoubleClickEnabled(final boolean doubleClickEnabled) {
        this.checkWidget();
        this.doubleClickEnabled = doubleClickEnabled;
    }
    
    public void setDragDetect(final boolean dragDetect) {
        this.checkWidget();
        this.dragDetect = dragDetect;
    }
    
    public void setEditable(final boolean editable) {
        this.checkWidget();
        this.editable = editable;
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        final int lineHeight = this.renderer.getLineHeight();
        super.setFont(font);
        this.renderer.setFont(this.getFont(), this.tabLength);
        if (this.isFixedLineHeight()) {
            final int lineHeight2 = this.renderer.getLineHeight();
            if (lineHeight2 != lineHeight) {
                this.scrollVertical(this.getVerticalScrollOffset() * lineHeight2 / lineHeight - this.getVerticalScrollOffset(), true);
            }
        }
        this.resetCache(0, this.content.getLineCount());
        this.claimBottomFreeSpace();
        this.calculateScrollBars();
        if (this.isBidiCaret()) {
            this.createCaretBitmaps();
        }
        this.caretDirection = 0;
        this.setCaretLocation();
        super.redraw();
    }
    
    public void setForeground(final Color foreground) {
        this.checkWidget();
        this.foreground = foreground;
        super.setForeground(this.getForeground());
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocation();
        super.redraw();
    }
    
    public void setHorizontalIndex(int max) {
        this.checkWidget();
        if (this.getCharCount() == 0) {
            return;
        }
        if (max < 0) {
            max = 0;
        }
        max *= this.getHorizontalIncrement();
        if (this.clientAreaWidth > 0) {
            final int width = this.renderer.getWidth();
            if (max > width - this.clientAreaWidth) {
                max = Math.max(0, width - this.clientAreaWidth);
            }
        }
        this.scrollHorizontal(max - this.horizontalScrollOffset, true);
    }
    
    public void setHorizontalPixel(int max) {
        this.checkWidget();
        if (this.getCharCount() == 0) {
            return;
        }
        if (max < 0) {
            max = 0;
        }
        if (this.clientAreaWidth > 0) {
            final int width = this.renderer.getWidth();
            if (max > width - this.clientAreaWidth) {
                max = Math.max(0, width - this.clientAreaWidth);
            }
        }
        this.scrollHorizontal(max - this.horizontalScrollOffset, true);
    }
    
    public void setIndent(final int indent) {
        this.checkWidget();
        if (this.indent == indent || indent < 0) {
            return;
        }
        this.indent = indent;
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocation();
        super.redraw();
    }
    
    public void setJustify(final boolean justify) {
        this.checkWidget();
        if (this.justify == justify) {
            return;
        }
        this.justify = justify;
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocation();
        super.redraw();
    }
    
    public void setKeyBinding(final int n, final int n2) {
        this.checkWidget();
        final int n3 = n & SWT.MODIFIER_MASK;
        final char c = (char)(n & 0x100FFFF);
        if (Compatibility.isLetter(c)) {
            final char c2 = (char)(Character.toUpperCase(c) | n3);
            if (n2 == 0) {
                this.keyActionMap.remove(new Integer(c2));
            }
            else {
                this.keyActionMap.put(new Integer(c2), new Integer(n2));
            }
            final char c3 = (char)(Character.toLowerCase(c) | n3);
            if (n2 == 0) {
                this.keyActionMap.remove(new Integer(c3));
            }
            else {
                this.keyActionMap.put(new Integer(c3), new Integer(n2));
            }
        }
        else if (n2 == 0) {
            this.keyActionMap.remove(new Integer(n));
        }
        else {
            this.keyActionMap.put(new Integer(n), new Integer(n2));
        }
    }
    
    public void setLeftMargin(final int n) {
        this.checkWidget();
        this.setMargins(n, this.topMargin, this.rightMargin, this.bottomMargin);
    }
    
    public void setLineAlignment(final int n, final int n2, final int n3) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (n < 0 || n + n2 > this.content.getLineCount()) {
            SWT.error(5);
        }
        this.renderer.setLineAlignment(n, n2, n3);
        this.resetCache(n, n2);
        this.redrawLines(n, n2, false);
        final int caretLine = this.getCaretLine();
        if (n <= caretLine && caretLine < n + n2) {
            this.setCaretLocation();
        }
        this.setAlignment();
    }
    
    public void setLineBackground(final int n, final int n2, final Color color) {
        this.checkWidget();
        if (this.isListening(3001)) {
            return;
        }
        if (n < 0 || n + n2 > this.content.getLineCount()) {
            SWT.error(5);
        }
        if (color != null) {
            this.renderer.setLineBackground(n, n2, color);
        }
        else {
            this.renderer.clearLineBackground(n, n2);
        }
        this.redrawLines(n, n2, false);
    }
    
    public void setLineBullet(final int n, final int n2, final Bullet bullet) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (n < 0 || n + n2 > this.content.getLineCount()) {
            SWT.error(5);
        }
        final int linePixel = this.getLinePixel(n + n2);
        this.renderer.setLineBullet(n, n2, bullet);
        this.resetCache(n, n2);
        this.redrawLines(n, n2, linePixel != this.getLinePixel(n + n2));
        final int caretLine = this.getCaretLine();
        if (n <= caretLine && caretLine < n + n2) {
            this.setCaretLocation();
        }
    }
    
    void setVariableLineHeight() {
        if (!this.fixedLineHeight) {
            return;
        }
        this.fixedLineHeight = false;
        this.renderer.calculateIdle();
    }
    
    public void setLineIndent(final int n, final int n2, final int n3) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (n < 0 || n + n2 > this.content.getLineCount()) {
            SWT.error(5);
        }
        final int linePixel = this.getLinePixel(n + n2);
        this.renderer.setLineIndent(n, n2, n3);
        this.resetCache(n, n2);
        this.redrawLines(n, n2, linePixel != this.getLinePixel(n + n2));
        final int caretLine = this.getCaretLine();
        if (n <= caretLine && caretLine < n + n2) {
            this.setCaretLocation();
        }
    }
    
    public void setLineJustify(final int n, final int n2, final boolean b) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (n < 0 || n + n2 > this.content.getLineCount()) {
            SWT.error(5);
        }
        this.renderer.setLineJustify(n, n2, b);
        this.resetCache(n, n2);
        this.redrawLines(n, n2, false);
        final int caretLine = this.getCaretLine();
        if (n <= caretLine && caretLine < n + n2) {
            this.setCaretLocation();
        }
    }
    
    public void setLineSpacing(final int lineSpacing) {
        this.checkWidget();
        if (this.lineSpacing == lineSpacing || lineSpacing < 0) {
            return;
        }
        this.lineSpacing = lineSpacing;
        this.setVariableLineHeight();
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocation();
        super.redraw();
    }
    
    public void setLineTabStops(final int n, final int n2, final int[] array) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (n < 0 || n + n2 > this.content.getLineCount()) {
            SWT.error(5);
        }
        if (array != null) {
            int n3 = 0;
            final int[] array2 = new int[array.length];
            for (int i = 0; i < array.length; ++i) {
                if (array[i] < n3) {
                    SWT.error(5);
                }
                n3 = (array2[i] = array[i]);
            }
            this.renderer.setLineTabStops(n, n2, array2);
        }
        else {
            this.renderer.setLineTabStops(n, n2, null);
        }
        this.resetCache(n, n2);
        this.redrawLines(n, n2, false);
        final int caretLine = this.getCaretLine();
        if (n <= caretLine && caretLine < n + n2) {
            this.setCaretLocation();
        }
    }
    
    public void setLineWrapIndent(final int n, final int n2, final int n3) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (n < 0 || n + n2 > this.content.getLineCount()) {
            SWT.error(5);
        }
        final int linePixel = this.getLinePixel(n + n2);
        this.renderer.setLineWrapIndent(n, n2, n3);
        this.resetCache(n, n2);
        this.redrawLines(n, n2, linePixel != this.getLinePixel(n + n2));
        final int caretLine = this.getCaretLine();
        if (n <= caretLine && caretLine < n + n2) {
            this.setCaretLocation();
        }
    }
    
    public void setMarginColor(final Color marginColor) {
        this.checkWidget();
        if (marginColor != null && marginColor.isDisposed()) {
            SWT.error(5);
        }
        this.marginColor = marginColor;
        super.redraw();
    }
    
    public void setMargins(final int n, final int n2, final int n3, final int n4) {
        this.checkWidget();
        this.leftMargin = Math.max(0, n);
        this.topMargin = Math.max(0, n2);
        this.rightMargin = Math.max(0, n3);
        this.bottomMargin = Math.max(0, n4);
        this.resetCache(0, this.content.getLineCount());
        this.setScrollBars(true);
        this.setCaretLocation();
        this.setAlignment();
        super.redraw();
    }
    
    void setMouseWordSelectionAnchor() {
        if (this.clickCount > 1) {
            if (this.caretOffset < this.doubleClickSelection.x) {
                this.selectionAnchor = this.doubleClickSelection.y;
            }
            else if (this.caretOffset > this.doubleClickSelection.y) {
                this.selectionAnchor = this.doubleClickSelection.x;
            }
        }
    }
    
    public void setOrientation(final int orientation) {
        final int orientation2 = this.getOrientation();
        super.setOrientation(orientation);
        if (orientation2 != this.getOrientation()) {
            this.resetCache(this.caretDirection = 0, this.content.getLineCount());
            this.setCaretLocation();
            this.keyActionMap.clear();
            this.createKeyBindings();
            super.redraw();
        }
    }
    
    public void setRightMargin(final int n) {
        this.checkWidget();
        this.setMargins(this.leftMargin, this.topMargin, n, this.bottomMargin);
    }
    
    void setScrollBars(final boolean b) {
        final int n = 1;
        if (b || !this.isFixedLineHeight()) {
            final ScrollBar verticalBar = this.getVerticalBar();
            if (verticalBar != null) {
                final int height = this.renderer.getHeight();
                if (this.clientAreaHeight < height) {
                    verticalBar.setMaximum(height - this.topMargin - this.bottomMargin);
                    verticalBar.setThumb(this.clientAreaHeight - this.topMargin - this.bottomMargin);
                    verticalBar.setPageIncrement(this.clientAreaHeight - this.topMargin - this.bottomMargin);
                }
                else if (verticalBar.getThumb() != n || verticalBar.getMaximum() != n) {
                    verticalBar.setValues(verticalBar.getSelection(), verticalBar.getMinimum(), n, n, verticalBar.getIncrement(), n);
                }
            }
        }
        final ScrollBar horizontalBar = this.getHorizontalBar();
        if (horizontalBar != null && horizontalBar.getVisible()) {
            final int width = this.renderer.getWidth();
            if (this.clientAreaWidth < width) {
                horizontalBar.setMaximum(width - this.leftMargin - this.rightMargin);
                horizontalBar.setThumb(this.clientAreaWidth - this.leftMargin - this.rightMargin);
                horizontalBar.setPageIncrement(this.clientAreaWidth - this.leftMargin - this.rightMargin);
            }
            else if (horizontalBar.getThumb() != n || horizontalBar.getMaximum() != n) {
                horizontalBar.setValues(horizontalBar.getSelection(), horizontalBar.getMinimum(), n, n, horizontalBar.getIncrement(), n);
            }
        }
    }
    
    public void setSelection(final int n) {
        this.setSelection(n, n);
    }
    
    public void setSelection(final Point point) {
        this.checkWidget();
        if (point == null) {
            SWT.error(4);
        }
        this.setSelection(point.x, point.y);
    }
    
    public void setSelectionBackground(final Color selectionBackground) {
        this.checkWidget();
        if (selectionBackground != null && selectionBackground.isDisposed()) {
            SWT.error(5);
        }
        this.selectionBackground = selectionBackground;
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocation();
        super.redraw();
    }
    
    public void setSelectionForeground(final Color selectionForeground) {
        this.checkWidget();
        if (selectionForeground != null && selectionForeground.isDisposed()) {
            SWT.error(5);
        }
        this.selectionForeground = selectionForeground;
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocation();
        super.redraw();
    }
    
    public void setSelection(final int n, final int n2) {
        this.setSelectionRange(n, n2 - n);
        this.showSelection();
    }
    
    void setSelection(int x, final int n, final boolean b, final boolean b2) {
        int y = x + n;
        if (x > y) {
            final int n2 = y;
            y = x;
            x = n2;
        }
        if (this.selection.x != x || this.selection.y != y || (n > 0 && this.selectionAnchor != this.selection.x) || (n < 0 && this.selectionAnchor != this.selection.y)) {
            if (this.blockSelection && b2) {
                this.setBlockSelectionOffset(x, y, b);
            }
            else {
                this.clearSelection(b);
                if (n < 0) {
                    final Point selection = this.selection;
                    final int n3 = y;
                    selection.y = n3;
                    this.selectionAnchor = n3;
                    this.setCaretOffset(this.selection.x = x, 0);
                }
                else {
                    final Point selection2 = this.selection;
                    final int n4 = x;
                    selection2.x = n4;
                    this.selectionAnchor = n4;
                    this.setCaretOffset(this.selection.y = y, 0);
                }
                this.internalRedrawRange(this.selection.x, this.selection.y - this.selection.x);
                this.sendAccessibleTextCaretMoved();
            }
        }
    }
    
    public void setSelectionRange(int max, int n) {
        this.checkWidget();
        final int charCount = this.getCharCount();
        max = Math.max(0, Math.min(max, charCount));
        final int n2 = max + n;
        if (n2 < 0) {
            n = -max;
        }
        else if (n2 > charCount) {
            n = charCount - max;
        }
        if (this.isLineDelimiter(max) || this.isLineDelimiter(max + n)) {
            SWT.error(5);
        }
        this.setSelection(max, n, false, true);
        this.setCaretLocation();
    }
    
    public void setStyleRange(final StyleRange styleRange) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (styleRange != null) {
            if (styleRange.isUnstyled()) {
                this.setStyleRanges(styleRange.start, styleRange.length, null, null, false);
            }
            else {
                this.setStyleRanges(styleRange.start, 0, null, new StyleRange[] { styleRange }, false);
            }
        }
        else {
            this.setStyleRanges(0, 0, null, null, true);
        }
    }
    
    public void setStyleRanges(final int n, final int n2, final int[] array, final StyleRange[] array2) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (array == null || array2 == null) {
            this.setStyleRanges(n, n2, null, null, false);
        }
        else {
            this.setStyleRanges(n, n2, array, array2, false);
        }
    }
    
    public void setStyleRanges(final int[] array, final StyleRange[] array2) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (array == null || array2 == null) {
            this.setStyleRanges(0, 0, null, null, true);
        }
        else {
            this.setStyleRanges(0, 0, array, array2, true);
        }
    }
    
    void setStyleRanges(final int n, final int n2, final int[] array, final StyleRange[] array2, final boolean b) {
        final int charCount = this.content.getCharCount();
        final int n3 = n + n2;
        if (n > n3 || n < 0) {
            SWT.error(6);
        }
        if (array2 != null) {
            if (n3 > charCount) {
                SWT.error(6);
            }
            if (array != null && array.length != array2.length << 1) {
                SWT.error(5);
            }
            int n4 = 0;
            boolean b2 = false;
            for (int i = 0; i < array2.length; ++i) {
                if (array2[i] == null) {
                    SWT.error(5);
                }
                int start;
                int length;
                if (array != null) {
                    start = array[i << 1];
                    length = array[(i << 1) + 1];
                }
                else {
                    start = array2[i].start;
                    length = array2[i].length;
                }
                if (length < 0) {
                    SWT.error(5);
                }
                if (start < 0 || start + length > charCount) {
                    SWT.error(5);
                }
                if (n4 > start) {
                    SWT.error(5);
                }
                b2 |= array2[i].isVariableHeight();
                n4 = start + length;
            }
            if (b2) {
                this.setVariableLineHeight();
            }
        }
        int start2 = n;
        int n5 = n3;
        if (array2 != null && array2.length > 0) {
            if (array != null) {
                start2 = array[0];
                n5 = array[array.length - 2] + array[array.length - 1];
            }
            else {
                start2 = array2[0].start;
                n5 = array2[array2.length - 1].start + array2[array2.length - 1].length;
            }
        }
        int linePixel = 0;
        if (!this.isFixedLineHeight() && !b) {
            final int lineAtOffset = this.content.getLineAtOffset(Math.max(n3, n5));
            final int partialTopIndex = this.getPartialTopIndex();
            final int partialBottomIndex = this.getPartialBottomIndex();
            if (partialTopIndex <= lineAtOffset && lineAtOffset <= partialBottomIndex) {
                linePixel = this.getLinePixel(lineAtOffset + 1);
            }
        }
        if (b) {
            this.renderer.setStyleRanges(null, null);
        }
        else {
            this.renderer.updateRanges(n, n2, n2);
        }
        if (array2 != null && array2.length > 0) {
            this.renderer.setStyleRanges(array, array2);
        }
        if (b) {
            this.resetCache(0, this.content.getLineCount());
            super.redraw();
        }
        else {
            final int lineAtOffset2 = this.content.getLineAtOffset(Math.min(n, start2));
            final int lineAtOffset3 = this.content.getLineAtOffset(Math.max(n3, n5));
            this.resetCache(lineAtOffset2, lineAtOffset3 - lineAtOffset2 + 1);
            final int partialTopIndex2 = this.getPartialTopIndex();
            final int partialBottomIndex2 = this.getPartialBottomIndex();
            if (lineAtOffset2 <= partialBottomIndex2 && lineAtOffset3 >= partialTopIndex2) {
                int max = 0;
                int n6 = this.clientAreaHeight;
                if (partialTopIndex2 <= lineAtOffset2 && lineAtOffset2 <= partialBottomIndex2) {
                    max = Math.max(0, this.getLinePixel(lineAtOffset2));
                }
                if (partialTopIndex2 <= lineAtOffset3 && lineAtOffset3 <= partialBottomIndex2) {
                    n6 = this.getLinePixel(lineAtOffset3 + 1);
                }
                if (!this.isFixedLineHeight() && n6 != linePixel) {
                    n6 = this.clientAreaHeight;
                }
                super.redraw(0, max, this.clientAreaWidth, n6 - max, false);
            }
        }
        final int columnX = this.columnX;
        this.setCaretLocation();
        this.columnX = columnX;
        this.doMouseLinkCursor();
    }
    
    public void setStyleRanges(final StyleRange[] array) {
        this.checkWidget();
        if (this.isListening(3002)) {
            return;
        }
        if (array == null) {
            SWT.error(4);
        }
        this.setStyleRanges(0, 0, null, array, true);
    }
    
    public void setTabs(final int tabLength) {
        this.checkWidget();
        this.tabLength = tabLength;
        this.renderer.setFont(null, tabLength);
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocation();
        super.redraw();
    }
    
    public void setTabStops(final int[] array) {
        this.checkWidget();
        if (array != null) {
            int n = 0;
            final int[] tabs = new int[array.length];
            for (int i = 0; i < array.length; ++i) {
                if (array[i] < n) {
                    SWT.error(5);
                }
                n = (tabs[i] = array[i]);
            }
            this.tabs = tabs;
        }
        else {
            this.tabs = null;
        }
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocation();
        super.redraw();
    }
    
    public void setText(final String text) {
        this.checkWidget();
        if (text == null) {
            SWT.error(4);
        }
        final Event event = new Event();
        event.start = 0;
        event.end = this.getCharCount();
        event.text = text;
        event.doit = true;
        this.notifyListeners(25, event);
        if (event.doit) {
            StyledTextEvent styledTextEvent = null;
            if (this.isListening(3000)) {
                styledTextEvent = new StyledTextEvent(this.content);
                styledTextEvent.start = event.start;
                styledTextEvent.end = event.start + event.text.length();
                styledTextEvent.text = this.content.getTextRange(event.start, event.end - event.start);
            }
            this.content.setText(event.text);
            this.notifyListeners(24, event);
            if (styledTextEvent != null) {
                this.notifyListeners(3000, styledTextEvent);
            }
        }
    }
    
    public void setTextLimit(final int textLimit) {
        this.checkWidget();
        if (textLimit == 0) {
            SWT.error(7);
        }
        this.textLimit = textLimit;
    }
    
    public void setTopIndex(int max) {
        this.checkWidget();
        if (this.getCharCount() == 0) {
            return;
        }
        final int lineCount = this.content.getLineCount();
        int n;
        if (this.isFixedLineHeight()) {
            final int max2 = Math.max(1, Math.min(lineCount, this.getLineCountWhole()));
            if (max < 0) {
                max = 0;
            }
            else if (max > lineCount - max2) {
                max = lineCount - max2;
            }
            n = this.getLinePixel(max);
        }
        else {
            max = Math.max(0, Math.min(lineCount - 1, max));
            final int linePixel = this.getLinePixel(max);
            if (linePixel > 0) {
                n = this.getAvailableHeightBellow(linePixel);
            }
            else {
                n = this.getAvailableHeightAbove(linePixel);
            }
        }
        this.scrollVertical(n, true);
    }
    
    public void setTopMargin(final int n) {
        this.checkWidget();
        this.setMargins(this.leftMargin, n, this.rightMargin, this.bottomMargin);
    }
    
    public void setTopPixel(int availableHeightBellow) {
        this.checkWidget();
        if (this.getCharCount() == 0) {
            return;
        }
        if (availableHeightBellow < 0) {
            availableHeightBellow = 0;
        }
        final int lineCount = this.content.getLineCount();
        final int n = this.clientAreaHeight - this.topMargin - this.bottomMargin;
        final int verticalScrollOffset = this.getVerticalScrollOffset();
        if (this.isFixedLineHeight()) {
            final int max = Math.max(0, lineCount * this.getVerticalIncrement() - n);
            if (availableHeightBellow > max) {
                availableHeightBellow = max;
            }
            availableHeightBellow -= verticalScrollOffset;
        }
        else {
            availableHeightBellow -= verticalScrollOffset;
            if (availableHeightBellow > 0) {
                availableHeightBellow = this.getAvailableHeightBellow(availableHeightBellow);
            }
        }
        this.scrollVertical(availableHeightBellow, true);
    }
    
    public void setWordWrap(final boolean wordWrap) {
        this.checkWidget();
        if ((this.getStyle() & 0x4) != 0x0) {
            return;
        }
        if (this.wordWrap == wordWrap) {
            return;
        }
        if (this.wordWrap && this.blockSelection) {
            this.setBlockSelection(false);
        }
        this.wordWrap = wordWrap;
        this.setVariableLineHeight();
        this.resetCache(0, this.content.getLineCount());
        this.horizontalScrollOffset = 0;
        final ScrollBar horizontalBar = this.getHorizontalBar();
        if (horizontalBar != null) {
            horizontalBar.setVisible(!this.wordWrap);
        }
        this.setScrollBars(true);
        this.setCaretLocation();
        super.redraw();
    }
    
    public void setWrapIndent(final int wrapIndent) {
        this.checkWidget();
        if (this.wrapIndent == wrapIndent || wrapIndent < 0) {
            return;
        }
        this.wrapIndent = wrapIndent;
        this.resetCache(0, this.content.getLineCount());
        this.setCaretLocation();
        super.redraw();
    }
    
    boolean showLocation(final Rectangle rectangle, final boolean b) {
        boolean b2 = false;
        if (rectangle.y < this.topMargin) {
            b2 = this.scrollVertical(rectangle.y - this.topMargin, true);
        }
        else if (rectangle.y + rectangle.height > this.clientAreaHeight - this.bottomMargin) {
            if (this.clientAreaHeight - this.topMargin - this.bottomMargin <= 0) {
                b2 = this.scrollVertical(rectangle.y - this.topMargin, true);
            }
            else {
                b2 = this.scrollVertical(rectangle.y + rectangle.height - (this.clientAreaHeight - this.bottomMargin), true);
            }
        }
        final int n = this.clientAreaWidth - this.rightMargin - this.leftMargin;
        if (n > 0) {
            final int n2 = b ? (n / 4) : 0;
            if (rectangle.x < this.leftMargin) {
                b2 = this.scrollHorizontal(-Math.min(this.horizontalScrollOffset, Math.max(this.leftMargin - rectangle.x, n2)), true);
            }
            else if (rectangle.x + rectangle.width > this.clientAreaWidth - this.rightMargin) {
                b2 = this.scrollHorizontal(Math.min(this.renderer.getWidth() - this.horizontalScrollOffset - this.clientAreaWidth, Math.max(rectangle.x + rectangle.width - (this.clientAreaWidth - this.rightMargin), n2)), true);
            }
        }
        return b2;
    }
    
    void showCaret() {
        if (!this.showLocation(this.getBoundsAtOffset(this.caretOffset), true)) {
            this.setCaretLocation();
        }
    }
    
    public void showSelection() {
        this.checkWidget();
        final boolean b = this.caretOffset == this.selection.x;
        int n;
        int n2;
        if (b) {
            n = this.selection.y;
            n2 = this.selection.x;
        }
        else {
            n = this.selection.x;
            n2 = this.selection.y;
        }
        final Rectangle boundsAtOffset = this.getBoundsAtOffset(n);
        Rectangle rectangle = this.getBoundsAtOffset(n2);
        final int n3 = this.clientAreaWidth - this.leftMargin - this.rightMargin;
        if (b ? (boundsAtOffset.x - rectangle.x <= n3) : (rectangle.x - boundsAtOffset.x <= n3)) {
            if (this.showLocation(boundsAtOffset, false)) {
                rectangle = this.getBoundsAtOffset(n2);
            }
            rectangle.width = ((n2 == this.caretOffset) ? this.getCaretWidth() : 0);
            this.showLocation(rectangle, false);
        }
        else {
            this.showLocation(rectangle, true);
        }
    }
    
    void updateCaretVisibility() {
        final Caret caret = this.getCaret();
        if (caret != null) {
            if (this.blockSelection && this.blockXLocation != -1) {
                caret.setVisible(false);
            }
            else {
                final Point location = caret.getLocation();
                final Point size = caret.getSize();
                caret.setVisible(this.topMargin <= location.y + size.y && location.y <= this.clientAreaHeight - this.bottomMargin && this.leftMargin <= location.x + size.x && location.x <= this.clientAreaWidth - this.rightMargin);
            }
        }
    }
    
    void updateSelection(final int n, final int n2, final int n3) {
        if (this.selection.y <= n) {
            if (this.wordWrap) {
                this.setCaretLocation();
            }
            return;
        }
        if (this.selection.x < n) {
            this.internalRedrawRange(this.selection.x, n - this.selection.x);
        }
        if (this.selection.y > n + n2 && this.selection.x < n + n2) {
            final int n4 = n3 - n2;
            final int n5 = n + n3;
            this.internalRedrawRange(n5, this.selection.y + n4 - n5);
        }
        if (this.selection.y > n && this.selection.x < n + n2) {
            this.setSelection(n + n3, 0, true, false);
        }
        else {
            this.setSelection(this.selection.x + n3 - n2, this.selection.y - this.selection.x, true, false);
        }
        this.setCaretLocation();
    }
    
    static class Printing implements Runnable
    {
        static final int LEFT = 0;
        static final int CENTER = 1;
        static final int RIGHT = 2;
        Printer printer;
        StyledTextRenderer printerRenderer;
        StyledTextPrintOptions printOptions;
        Rectangle clientArea;
        FontData fontData;
        Font printerFont;
        Hashtable resources;
        int tabLength;
        GC gc;
        int pageWidth;
        int startPage;
        int endPage;
        int scope;
        int startLine;
        int endLine;
        boolean singleLine;
        Point selection;
        boolean mirrored;
        int lineSpacing;
        int printMargin;
        
        Printing(final StyledText styledText, final Printer printer, final StyledTextPrintOptions printOptions) {
            this.selection = null;
            this.printer = printer;
            this.printOptions = printOptions;
            this.mirrored = ((styledText.getStyle() & 0x8000000) != 0x0);
            this.singleLine = styledText.isSingleLine();
            this.startPage = 1;
            this.endPage = Integer.MAX_VALUE;
            final PrinterData printerData = printer.getPrinterData();
            this.scope = printerData.scope;
            if (this.scope == 1) {
                this.startPage = printerData.startPage;
                this.endPage = printerData.endPage;
                if (this.endPage < this.startPage) {
                    final int endPage = this.endPage;
                    this.endPage = this.startPage;
                    this.startPage = endPage;
                }
            }
            else if (this.scope == 2) {
                this.selection = styledText.getSelectionRange();
            }
            (this.printerRenderer = new StyledTextRenderer(printer, null)).setContent(this.copyContent(styledText.getContent()));
            this.cacheLineData(styledText);
        }
        
        void cacheLineData(final StyledText styledText) {
            styledText.renderer.copyInto(this.printerRenderer);
            this.fontData = styledText.getFont().getFontData()[0];
            this.tabLength = styledText.tabLength;
            final int lineCount = this.printerRenderer.lineCount;
            if (styledText.isListening(3001) || (styledText.isBidi() && styledText.isListening(3007)) || styledText.isListening(3002)) {
                final StyledTextContent content = this.printerRenderer.content;
                for (int i = 0; i < lineCount; ++i) {
                    final String line = content.getLine(i);
                    final int offsetAtLine = content.getOffsetAtLine(i);
                    final StyledTextEvent lineBackgroundData = styledText.getLineBackgroundData(offsetAtLine, line);
                    if (lineBackgroundData != null && lineBackgroundData.lineBackground != null) {
                        this.printerRenderer.setLineBackground(i, 1, lineBackgroundData.lineBackground);
                    }
                    if (styledText.isBidi()) {
                        final StyledTextEvent bidiSegments = styledText.getBidiSegments(offsetAtLine, line);
                        if (bidiSegments != null) {
                            this.printerRenderer.setLineSegments(i, 1, bidiSegments.segments);
                            this.printerRenderer.setLineSegmentChars(i, 1, bidiSegments.segmentsChars);
                        }
                    }
                    final StyledTextEvent lineStyleData = styledText.getLineStyleData(offsetAtLine, line);
                    if (lineStyleData != null) {
                        this.printerRenderer.setLineIndent(i, 1, lineStyleData.indent);
                        this.printerRenderer.setLineAlignment(i, 1, lineStyleData.alignment);
                        this.printerRenderer.setLineJustify(i, 1, lineStyleData.justify);
                        this.printerRenderer.setLineBullet(i, 1, lineStyleData.bullet);
                        final StyleRange[] styles = lineStyleData.styles;
                        if (styles != null && styles.length > 0) {
                            this.printerRenderer.setStyleRanges(lineStyleData.ranges, styles);
                        }
                    }
                }
            }
            final Point dpi = styledText.getDisplay().getDPI();
            final Point dpi2 = this.printer.getDPI();
            this.resources = new Hashtable();
            for (int j = 0; j < lineCount; ++j) {
                final Color lineBackground = this.printerRenderer.getLineBackground(j, null);
                if (lineBackground != null) {
                    if (this.printOptions.printLineBackground) {
                        Color color = this.resources.get(lineBackground);
                        if (color == null) {
                            color = new Color(this.printer, lineBackground.getRGB());
                            this.resources.put(lineBackground, color);
                        }
                        this.printerRenderer.setLineBackground(j, 1, color);
                    }
                    else {
                        this.printerRenderer.setLineBackground(j, 1, null);
                    }
                }
                final int lineIndent = this.printerRenderer.getLineIndent(j, 0);
                if (lineIndent != 0) {
                    this.printerRenderer.setLineIndent(j, 1, lineIndent * dpi2.x / dpi.x);
                }
            }
            final StyleRange[] styles2 = this.printerRenderer.styles;
            for (int k = 0; k < this.printerRenderer.styleCount; ++k) {
                final StyleRange styleRange = styles2[k];
                final Font font = styleRange.font;
                if (styleRange.font != null) {
                    Font font2 = this.resources.get(font);
                    if (font2 == null) {
                        font2 = new Font(this.printer, font.getFontData());
                        this.resources.put(font, font2);
                    }
                    styleRange.font = font2;
                }
                final Color foreground = styleRange.foreground;
                if (foreground != null) {
                    Color foreground2 = this.resources.get(foreground);
                    if (this.printOptions.printTextForeground) {
                        if (foreground2 == null) {
                            foreground2 = new Color(this.printer, foreground.getRGB());
                            this.resources.put(foreground, foreground2);
                        }
                        styleRange.foreground = foreground2;
                    }
                    else {
                        styleRange.foreground = null;
                    }
                }
                final Color background = styleRange.background;
                if (background != null) {
                    Color background2 = this.resources.get(background);
                    if (this.printOptions.printTextBackground) {
                        if (background2 == null) {
                            background2 = new Color(this.printer, background.getRGB());
                            this.resources.put(background, background2);
                        }
                        styleRange.background = background2;
                    }
                    else {
                        styleRange.background = null;
                    }
                }
                if (!this.printOptions.printTextFontStyle) {
                    styleRange.fontStyle = 0;
                }
                styleRange.rise = styleRange.rise * dpi2.y / dpi.y;
                final GlyphMetrics metrics = styleRange.metrics;
                if (metrics != null) {
                    metrics.ascent = metrics.ascent * dpi2.y / dpi.y;
                    metrics.descent = metrics.descent * dpi2.y / dpi.y;
                    metrics.width = metrics.width * dpi2.x / dpi.x;
                }
            }
            this.lineSpacing = styledText.lineSpacing * dpi2.y / dpi.y;
            if (this.printOptions.printLineNumbers) {
                this.printMargin = 3 * dpi2.x / dpi.x;
            }
        }
        
        StyledTextContent copyContent(final StyledTextContent styledTextContent) {
            final DefaultContent defaultContent = new DefaultContent();
            int n = 0;
            for (int i = 0; i < styledTextContent.getLineCount(); ++i) {
                int n2;
                if (i < styledTextContent.getLineCount() - 1) {
                    n2 = styledTextContent.getOffsetAtLine(i + 1);
                }
                else {
                    n2 = styledTextContent.getCharCount();
                }
                defaultContent.replaceTextRange(n, 0, styledTextContent.getTextRange(n, n2 - n));
                n = n2;
            }
            return defaultContent;
        }
        
        void dispose() {
            if (this.gc != null) {
                this.gc.dispose();
                this.gc = null;
            }
            if (this.resources != null) {
                final Enumeration<Resource> elements = this.resources.elements();
                while (elements.hasMoreElements()) {
                    elements.nextElement().dispose();
                }
                this.resources = null;
            }
            if (this.printerFont != null) {
                this.printerFont.dispose();
                this.printerFont = null;
            }
            if (this.printerRenderer != null) {
                this.printerRenderer.dispose();
                this.printerRenderer = null;
            }
        }
        
        void init() {
            final Rectangle computeTrim = this.printer.computeTrim(0, 0, 0, 0);
            final Point dpi = this.printer.getDPI();
            this.printerFont = new Font(this.printer, this.fontData.getName(), this.fontData.getHeight(), 0);
            this.clientArea = this.printer.getClientArea();
            this.pageWidth = this.clientArea.width;
            this.clientArea.x = dpi.x + computeTrim.x;
            this.clientArea.y = dpi.y + computeTrim.y;
            final Rectangle clientArea = this.clientArea;
            clientArea.width -= this.clientArea.x + computeTrim.width;
            final Rectangle clientArea2 = this.clientArea;
            clientArea2.height -= this.clientArea.y + computeTrim.height;
            (this.gc = new GC(this.printer, this.mirrored ? 67108864 : 33554432)).setFont(this.printerFont);
            this.printerRenderer.setFont(this.printerFont, this.tabLength);
            final int lineHeight = this.printerRenderer.getLineHeight();
            if (this.printOptions.header != null) {
                final Rectangle clientArea3 = this.clientArea;
                clientArea3.y += lineHeight * 2;
                final Rectangle clientArea4 = this.clientArea;
                clientArea4.height -= lineHeight * 2;
            }
            if (this.printOptions.footer != null) {
                final Rectangle clientArea5 = this.clientArea;
                clientArea5.height -= lineHeight * 2;
            }
            final StyledTextContent content = this.printerRenderer.content;
            this.startLine = 0;
            this.endLine = (this.singleLine ? 0 : (content.getLineCount() - 1));
            if (this.scope == 1) {
                this.startLine = (this.startPage - 1) * (this.clientArea.height / lineHeight);
            }
            else if (this.scope == 2) {
                this.startLine = content.getLineAtOffset(this.selection.x);
                if (this.selection.y > 0) {
                    this.endLine = content.getLineAtOffset(this.selection.x + this.selection.y - 1);
                }
                else {
                    this.endLine = this.startLine - 1;
                }
            }
        }
        
        void print() {
            final Color background = this.gc.getBackground();
            final Color foreground = this.gc.getForeground();
            int y = this.clientArea.y;
            int x = this.clientArea.x;
            int width = this.clientArea.width;
            int startPage = this.startPage;
            final int n = this.clientArea.y + this.clientArea.height;
            final int n2 = this.gc.getStyle() & 0x6000000;
            TextLayout textLayout = null;
            if (this.printOptions.printLineNumbers || this.printOptions.header != null || this.printOptions.footer != null) {
                textLayout = new TextLayout(this.printer);
                textLayout.setFont(this.printerFont);
            }
            if (this.printOptions.printLineNumbers) {
                int n3 = 0;
                int n4 = this.endLine - this.startLine + 1;
                final String[] lineLabels = this.printOptions.lineLabels;
                if (lineLabels != null) {
                    for (int i = this.startLine; i < Math.min(n4, lineLabels.length); ++i) {
                        if (lineLabels[i] != null) {
                            textLayout.setText(lineLabels[i]);
                            n3 = Math.max(n3, textLayout.getBounds().width);
                        }
                    }
                }
                else {
                    final StringBuffer sb = new StringBuffer("0");
                    while ((n4 /= 10) > 0) {
                        sb.append("0");
                    }
                    textLayout.setText(sb.toString());
                    n3 = textLayout.getBounds().width;
                }
                int n5 = n3 + this.printMargin;
                if (n5 > width) {
                    n5 = width;
                }
                x += n5;
                width -= n5;
            }
            for (int startLine = this.startLine; startLine <= this.endLine && startPage <= this.endPage; ++startLine) {
                if (y == this.clientArea.y) {
                    this.printer.startPage();
                    this.printDecoration(startPage, true, textLayout);
                }
                final TextLayout textLayout2 = this.printerRenderer.getTextLayout(startLine, n2, width, this.lineSpacing);
                final Color lineBackground = this.printerRenderer.getLineBackground(startLine, background);
                int n6 = y + textLayout2.getBounds().height;
                if (n6 <= n) {
                    this.printLine(x, y, this.gc, foreground, lineBackground, textLayout2, textLayout, startLine);
                    y = n6;
                }
                else {
                    int lineCount;
                    for (lineCount = textLayout2.getLineCount(); n6 > n && lineCount > 0; --lineCount, n6 -= textLayout2.getLineBounds(lineCount).height + textLayout2.getSpacing()) {}
                    if (lineCount == 0) {
                        this.printDecoration(startPage, false, textLayout);
                        this.printer.endPage();
                        if (++startPage <= this.endPage) {
                            this.printer.startPage();
                            this.printDecoration(startPage, true, textLayout);
                            final int y2 = this.clientArea.y;
                            this.printLine(x, y2, this.gc, foreground, lineBackground, textLayout2, textLayout, startLine);
                            y = y2 + textLayout2.getBounds().height;
                        }
                    }
                    else {
                        final int n7 = n6 - y;
                        this.gc.setClipping(this.clientArea.x, y, this.clientArea.width, n7);
                        this.printLine(x, y, this.gc, foreground, lineBackground, textLayout2, textLayout, startLine);
                        this.gc.setClipping((Rectangle)null);
                        this.printDecoration(startPage, false, textLayout);
                        this.printer.endPage();
                        if (++startPage <= this.endPage) {
                            this.printer.startPage();
                            this.printDecoration(startPage, true, textLayout);
                            final int n8 = this.clientArea.y - n7;
                            final int height = textLayout2.getBounds().height;
                            this.gc.setClipping(this.clientArea.x, this.clientArea.y, this.clientArea.width, height - n7);
                            this.printLine(x, n8, this.gc, foreground, lineBackground, textLayout2, textLayout, startLine);
                            this.gc.setClipping((Rectangle)null);
                            y = n8 + height;
                        }
                    }
                }
                this.printerRenderer.disposeTextLayout(textLayout2);
            }
            if (startPage <= this.endPage && y > this.clientArea.y) {
                this.printDecoration(startPage, false, textLayout);
                this.printer.endPage();
            }
            if (textLayout != null) {
                textLayout.dispose();
            }
        }
        
        void printDecoration(final int n, final boolean b, final TextLayout textLayout) {
            final String s = b ? this.printOptions.header : this.printOptions.footer;
            if (s == null) {
                return;
            }
            int n2 = 0;
            for (int i = 0; i < 3; ++i) {
                final int index = s.indexOf("\t", n2);
                if (index == -1) {
                    this.printDecorationSegment(s.substring(n2), i, n, b, textLayout);
                    break;
                }
                this.printDecorationSegment(s.substring(n2, index), i, n, b, textLayout);
                n2 = index + "\t".length();
            }
        }
        
        void printDecorationSegment(String string, final int n, final int n2, final boolean b, final TextLayout textLayout) {
            final int index = string.indexOf("<page>");
            if (index != -1) {
                final int length = "<page>".length();
                final StringBuffer sb = new StringBuffer(string.substring(0, index));
                sb.append(n2);
                sb.append(string.substring(index + length));
                string = sb.toString();
            }
            if (string.length() > 0) {
                textLayout.setText(string);
                final int width = textLayout.getBounds().width;
                final int lineHeight = this.printerRenderer.getLineHeight();
                int x = 0;
                if (n == 0) {
                    x = this.clientArea.x;
                }
                else if (n == 1) {
                    x = (this.pageWidth - width) / 2;
                }
                else if (n == 2) {
                    x = this.clientArea.x + this.clientArea.width - width;
                }
                int n3;
                if (b) {
                    n3 = this.clientArea.y - lineHeight * 2;
                }
                else {
                    n3 = this.clientArea.y + this.clientArea.height + lineHeight;
                }
                textLayout.draw(this.gc, x, n3);
            }
        }
        
        void printLine(final int n, final int n2, final GC gc, final Color foreground, final Color background, final TextLayout textLayout, final TextLayout textLayout2, final int n3) {
            if (background != null) {
                final Rectangle bounds = textLayout.getBounds();
                gc.setBackground(background);
                gc.fillRectangle(n, n2, bounds.width, bounds.height);
            }
            if (this.printOptions.printLineNumbers) {
                final FontMetrics lineMetrics = textLayout.getLineMetrics(0);
                textLayout2.setAscent(lineMetrics.getAscent() + lineMetrics.getLeading());
                textLayout2.setDescent(lineMetrics.getDescent());
                final String[] lineLabels = this.printOptions.lineLabels;
                if (lineLabels != null) {
                    if (n3 >= 0 && n3 < lineLabels.length && lineLabels[n3] != null) {
                        textLayout2.setText(lineLabels[n3]);
                    }
                    else {
                        textLayout2.setText("");
                    }
                }
                else {
                    textLayout2.setText(String.valueOf(n3));
                }
                textLayout2.draw(gc, n - this.printMargin - textLayout2.getBounds().width, n2);
                textLayout2.setAscent(-1);
                textLayout2.setDescent(-1);
            }
            gc.setForeground(foreground);
            textLayout.draw(gc, n, n2);
        }
        
        public void run() {
            String jobName = this.printOptions.jobName;
            if (jobName == null) {
                jobName = "Printing";
            }
            if (this.printer.startJob(jobName)) {
                this.init();
                this.print();
                this.dispose();
                this.printer.endJob();
            }
        }
    }
    
    class RTFWriter extends TextWriter
    {
        static final int DEFAULT_FOREGROUND = 0;
        static final int DEFAULT_BACKGROUND = 1;
        Vector colorTable;
        Vector fontTable;
        boolean WriteUnicode;
        
        public RTFWriter(final int n, final int n2) {
            super(n, n2);
            this.colorTable = new Vector();
            this.fontTable = new Vector();
            this.colorTable.addElement(StyledText.this.getForeground());
            this.colorTable.addElement(StyledText.this.getBackground());
            this.fontTable.addElement(StyledText.this.getFont());
            this.setUnicode();
        }
        
        public void close() {
            if (!this.isClosed()) {
                this.writeHeader();
                this.write("\n}}\u0000");
                super.close();
            }
        }
        
        int getColorIndex(final Color color, final int n) {
            if (color == null) {
                return n;
            }
            int n2 = this.colorTable.indexOf(color);
            if (n2 == -1) {
                n2 = this.colorTable.size();
                this.colorTable.addElement(color);
            }
            return n2;
        }
        
        int getFontIndex(final Font font) {
            int n = this.fontTable.indexOf(font);
            if (n == -1) {
                n = this.fontTable.size();
                this.fontTable.addElement(font);
            }
            return n;
        }
        
        void setUnicode() {
            final String lowerCase = System.getProperty("os.name").toLowerCase();
            final String property = System.getProperty("os.version");
            int int1 = 0;
            if (lowerCase.startsWith("windows nt") && property != null) {
                final int index = property.indexOf(46);
                if (index != -1) {
                    final String substring = property.substring(0, index);
                    try {
                        int1 = Integer.parseInt(substring);
                    }
                    catch (NumberFormatException ex) {}
                }
            }
            this.WriteUnicode = (!lowerCase.startsWith("windows 95") && !lowerCase.startsWith("windows 98") && !lowerCase.startsWith("windows me") && (!lowerCase.startsWith("windows nt") || int1 > 4));
        }
        
        void write(final String s, int n, final int n2) {
            for (int i = n; i < n2; ++i) {
                final char char1 = s.charAt(i);
                if (char1 > '\u007f' && this.WriteUnicode) {
                    if (i > n) {
                        this.write(s.substring(n, i));
                    }
                    this.write("\\u");
                    this.write(Integer.toString((short)char1));
                    this.write('?');
                    n = i + 1;
                }
                else if (char1 == '}' || char1 == '{' || char1 == '\\') {
                    if (i > n) {
                        this.write(s.substring(n, i));
                    }
                    this.write('\\');
                    this.write(char1);
                    n = i + 1;
                }
            }
            if (n < n2) {
                this.write(s.substring(n, n2));
            }
        }
        
        void writeHeader() {
            final StringBuffer sb = new StringBuffer();
            final FontData fontData = StyledText.this.getFont().getFontData()[0];
            sb.append("{\\rtf1\\ansi");
            final String lowerCase = System.getProperty("file.encoding").toLowerCase();
            if (lowerCase.startsWith("cp") || lowerCase.startsWith("ms")) {
                final String substring = lowerCase.substring(2, lowerCase.length());
                sb.append("\\ansicpg");
                sb.append(substring);
            }
            sb.append("\\uc1\\deff0{\\fonttbl{\\f0\\fnil ");
            sb.append(fontData.getName());
            sb.append(";");
            for (int i = 1; i < this.fontTable.size(); ++i) {
                sb.append("\\f");
                sb.append(i);
                sb.append(" ");
                sb.append(((Font)this.fontTable.elementAt(i)).getFontData()[0].getName());
                sb.append(";");
            }
            sb.append("}}\n{\\colortbl");
            for (int j = 0; j < this.colorTable.size(); ++j) {
                final Color color = this.colorTable.elementAt(j);
                sb.append("\\red");
                sb.append(color.getRed());
                sb.append("\\green");
                sb.append(color.getGreen());
                sb.append("\\blue");
                sb.append(color.getBlue());
                sb.append(";");
            }
            sb.append("}\n{\\f0\\fs");
            sb.append(fontData.getHeight() * 2);
            sb.append(" ");
            this.write(sb.toString(), 0);
        }
        
        public void writeLine(final String s, final int n) {
            if (this.isClosed()) {
                SWT.error(39);
            }
            final int lineAtOffset = StyledText.this.content.getLineAtOffset(n);
            final StyledTextEvent lineStyleData = StyledText.this.getLineStyleData(n, s);
            int n2;
            int n3;
            boolean b;
            int[] array;
            StyleRange[] array2;
            if (lineStyleData != null) {
                n2 = lineStyleData.alignment;
                n3 = lineStyleData.indent;
                b = lineStyleData.justify;
                array = lineStyleData.ranges;
                array2 = lineStyleData.styles;
            }
            else {
                n2 = StyledText.this.renderer.getLineAlignment(lineAtOffset, StyledText.this.alignment);
                n3 = StyledText.this.renderer.getLineIndent(lineAtOffset, StyledText.this.indent);
                b = StyledText.this.renderer.getLineJustify(lineAtOffset, StyledText.this.justify);
                array = StyledText.this.renderer.getRanges(n, s.length());
                array2 = StyledText.this.renderer.getStyleRanges(n, s.length(), false);
            }
            if (array2 == null) {
                array2 = new StyleRange[0];
            }
            Color color = StyledText.this.renderer.getLineBackground(lineAtOffset, null);
            final StyledTextEvent lineBackgroundData = StyledText.this.getLineBackgroundData(n, s);
            if (lineBackgroundData != null && lineBackgroundData.lineBackground != null) {
                color = lineBackgroundData.lineBackground;
            }
            this.writeStyledLine(s, n, array, array2, color, n3, n2, b);
        }
        
        public void writeLineDelimiter(final String s) {
            if (this.isClosed()) {
                SWT.error(39);
            }
            this.write(s, 0, s.length());
            this.write("\\par ");
        }
        
        void writeStyledLine(final String s, final int n, final int[] array, final StyleRange[] array2, final Color color, final int n2, final int n3, final boolean b) {
            final int length = s.length();
            final int start = this.getStart();
            final int n4 = start - n;
            if (n4 >= length) {
                return;
            }
            int max = Math.max(0, n4);
            this.write("\\fi");
            this.write(n2);
            switch (n3) {
                case 16384: {
                    this.write("\\ql");
                    break;
                }
                case 16777216: {
                    this.write("\\qc");
                    break;
                }
                case 131072: {
                    this.write("\\qr");
                    break;
                }
            }
            if (b) {
                this.write("\\qj");
            }
            this.write(" ");
            if (color != null) {
                this.write("{\\highlight");
                this.write(this.getColorIndex(color, 1));
                this.write(" ");
            }
            final int min = Math.min(length, start + super.getCharCount() - n);
            for (int i = 0; i < array2.length; ++i) {
                final StyleRange styleRange = array2[i];
                int n5;
                int n6;
                if (array != null) {
                    n5 = array[i << 1] - n;
                    n6 = n5 + array[(i << 1) + 1];
                }
                else {
                    n5 = styleRange.start - n;
                    n6 = n5 + styleRange.length;
                }
                if (n6 >= n4) {
                    if (n5 >= min) {
                        break;
                    }
                    if (max < n5) {
                        this.write(s, max, n5);
                        max = n5;
                    }
                    this.write("{\\cf");
                    this.write(this.getColorIndex(styleRange.foreground, 0));
                    final int colorIndex = this.getColorIndex(styleRange.background, 1);
                    if (colorIndex != 1) {
                        this.write("\\highlight");
                        this.write(colorIndex);
                    }
                    int n7 = styleRange.fontStyle;
                    final Font font = styleRange.font;
                    if (font != null) {
                        final int fontIndex = this.getFontIndex(font);
                        this.write("\\f");
                        this.write(fontIndex);
                        final FontData fontData = font.getFontData()[0];
                        this.write("\\fs");
                        this.write(fontData.getHeight() * 2);
                        n7 = fontData.getStyle();
                    }
                    if ((n7 & 0x1) != 0x0) {
                        this.write("\\b");
                    }
                    if ((n7 & 0x2) != 0x0) {
                        this.write("\\i");
                    }
                    if (styleRange.underline) {
                        this.write("\\ul");
                    }
                    if (styleRange.strikeout) {
                        this.write("\\strike");
                    }
                    this.write(" ");
                    final int max2 = Math.max(Math.min(n6, min), max);
                    this.write(s, max, max2);
                    if ((n7 & 0x1) != 0x0) {
                        this.write("\\b0");
                    }
                    if ((styleRange.fontStyle & 0x2) != 0x0) {
                        this.write("\\i0");
                    }
                    if (styleRange.underline) {
                        this.write("\\ul0");
                    }
                    if (styleRange.strikeout) {
                        this.write("\\strike0");
                    }
                    this.write("}");
                    max = max2;
                }
            }
            if (max < min) {
                this.write(s, max, min);
            }
            if (color != null) {
                this.write("}");
            }
        }
    }
    
    class TextWriter
    {
        private StringBuffer buffer;
        private int startOffset;
        private int endOffset;
        private boolean isClosed;
        
        public TextWriter(final int startOffset, final int n) {
            this.isClosed = false;
            this.buffer = new StringBuffer(n);
            this.startOffset = startOffset;
            this.endOffset = startOffset + n;
        }
        
        public void close() {
            if (!this.isClosed) {
                this.isClosed = true;
            }
        }
        
        public int getCharCount() {
            return this.endOffset - this.startOffset;
        }
        
        public int getStart() {
            return this.startOffset;
        }
        
        public boolean isClosed() {
            return this.isClosed;
        }
        
        public String toString() {
            return this.buffer.toString();
        }
        
        void write(final String s) {
            this.buffer.append(s);
        }
        
        void write(final String s, final int n) {
            if (n < 0 || n > this.buffer.length()) {
                return;
            }
            this.buffer.insert(n, s);
        }
        
        void write(final int n) {
            this.buffer.append(n);
        }
        
        void write(final char c) {
            this.buffer.append(c);
        }
        
        public void writeLine(final String s, final int n) {
            if (this.isClosed) {
                SWT.error(39);
            }
            final int n2 = this.startOffset - n;
            final int length = s.length();
            if (n2 >= length) {
                return;
            }
            int n3;
            if (n2 > 0) {
                n3 = n2;
            }
            else {
                n3 = 0;
            }
            final int min = Math.min(length, this.endOffset - n);
            if (n3 < min) {
                this.write(s.substring(n3, min));
            }
        }
        
        public void writeLineDelimiter(final String s) {
            if (this.isClosed) {
                SWT.error(39);
            }
            this.write(s);
        }
    }
}
