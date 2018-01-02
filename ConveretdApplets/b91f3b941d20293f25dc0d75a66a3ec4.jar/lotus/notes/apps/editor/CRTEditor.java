// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.io.Reader;
import java.awt.datatransfer.Transferable;
import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.Component;
import java.awt.Image;
import java.net.URLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import lotus.notes.apps.editorpanel.Utility;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.AdjustmentEvent;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Scrollbar;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.event.AdjustmentListener;

class CRTEditor implements AdjustmentListener, ClipboardOwner
{
    private CRTEdit cParent;
    private CParagraph cFirstPara;
    private CInsertionPoint cInsPt;
    private CSelection cSelection;
    private CCaret cCaret;
    private boolean cEditable;
    private Scrollbar cScrollBar;
    private Insets cMargins;
    private Insets cMarginTwips;
    private Color cLinkColor;
    private CContainer cContainer;
    private int cWidth;
    private int cHeight;
    private boolean cMoved;
    private boolean cFlowed;
    private boolean cDidRelativeScroll;
    private boolean cHasFocus;
    private int cChangeLevel;
    private int cChangeType;
    private CLine cFirstChange;
    private int cFirstChangeOffset;
    private CLine cLastChange;
    private int cTwipsPerPixel;
    private CFormatInfo cCachedFormatInfo;
    private CParaSettings cCachedParaSettings;
    private MediaTracker cTracker;
    private int[] cPositionOffsets;
    private int cBehavior;
    private int cBrowser;
    private String cBaseURLStr;
    public CEscapeMgr cEscMgr;
    CSelection cTempSelection;
    CParaSettings cSettings;
    CFormatInfo cInfo;
    CDataIterator cIterator;
    CSizeInfo cSize;
    CSizeInfo cSpaceSize;
    CSizeInfo cMaxSize;
    CTextPointer cChangeStart;
    CTextPointer cChangeEnd;
    private static final int CHANGE_ALL = 1;
    private static final int CHANGE_STYLE = 2;
    private static final int CHANGE_DATA = 4;
    private static final int CHANGE_IMAGE = 8;
    static final int MOVE_NULL = 0;
    static final int MOVE_SELECT = 1;
    static final int MOVE_NOTIFY = 2;
    static final int MOVE_DOUBLE = 4;
    private static final int SCROLL_LINE = 16;
    private static String[] cTypes;
    private boolean cSubtractHeightFromScrollMax;
    
    CRTEditor(final CRTEdit cParent) {
        this.cPositionOffsets = null;
        this.cBehavior = 0;
        this.cBrowser = 0;
        this.cBaseURLStr = null;
        this.cSubtractHeightFromScrollMax = false;
        this.cParent = cParent;
        this.cTwipsPerPixel = 1440 / cParent.getToolkit().getScreenResolution();
        this.cMarginTwips = new Insets(75, 150, 75, 150);
        this.calcMargins();
        this.cEditable = true;
        this.cChangeType = 1;
        this.cCaret = new CCaret(this);
        this.cTempSelection = new CSelection(this);
        this.cSettings = new CParaSettings();
        this.cInfo = new CFormatInfo();
        this.cIterator = new CDataIterator();
        this.cSize = new CSizeInfo();
        this.cSpaceSize = new CSizeInfo();
        this.cMaxSize = new CSizeInfo();
        this.cChangeStart = new CTextPointer();
        this.cChangeEnd = new CTextPointer();
        this.cCachedFormatInfo = new CFormatInfo();
        this.cCachedParaSettings = new CParaSettings();
        this.cEscMgr = new CEscapeMgr();
        this.cTracker = null;
    }
    
    void setScrollbar(final Scrollbar cScrollBar) {
        this.cScrollBar = cScrollBar;
        if (this.cScrollBar != null) {
            this.cScrollBar.addAdjustmentListener(this);
            try {
                this.cScrollBar.setValues(0, 10, 0, 20);
                this.cScrollBar.setValue(20);
                if (this.cScrollBar.getValue() >= 20) {
                    this.cSubtractHeightFromScrollMax = true;
                }
            }
            catch (IllegalArgumentException ex) {
                this.cSubtractHeightFromScrollMax = true;
            }
        }
    }
    
    final Scrollbar getScrollbar() {
        return this.cScrollBar;
    }
    
    CParaStyleMgr getParaStyleMgr() {
        return this.cParent.getParaStyleMgr();
    }
    
    void destroy() {
        this.cCaret.quit();
    }
    
    void setSize(final int cWidth, final int cHeight) {
        this.cWidth = cWidth;
        this.cHeight = cHeight;
        this.cContainer = new CContainer(this, this.cWidth - this.cMargins.left - this.cMargins.right, this.cHeight - this.cMargins.top - this.cMargins.bottom);
        this.cChangeType = 1;
        if (this.cInsPt != null) {
            this.cInsPt.invalidate();
        }
    }
    
    void setBehavior(final int cBehavior) {
        this.cBehavior = cBehavior;
    }
    
    int getBehavior() {
        return this.cBehavior;
    }
    
    void setBrowser(final int cBrowser) {
        this.cBrowser = cBrowser;
    }
    
    int getBrowser() {
        return this.cBrowser;
    }
    
    void setBaseURL(final String cBaseURLStr) {
        this.cBaseURLStr = cBaseURLStr;
    }
    
    String getBaseURL() {
        return this.cBaseURLStr;
    }
    
    final CContainer getContainer() {
        return this.cContainer;
    }
    
    final CInsertionPoint getInsertionPoint() {
        return this.cInsPt;
    }
    
    void calcMargins() {
        this.cMargins = new Insets(this.toPixels(this.cMarginTwips.top), this.toPixels(this.cMarginTwips.left), this.toPixels(this.cMarginTwips.bottom), this.toPixels(this.cMarginTwips.right));
    }
    
    final Insets getMargins() {
        return this.cMargins;
    }
    
    void beginChange() {
        if (++this.cChangeLevel == 1) {
            this.cCaret.setEnabled(false);
        }
    }
    
    void endChange(final boolean b) {
        final int cChangeLevel = this.cChangeLevel - 1;
        this.cChangeLevel = cChangeLevel;
        if (cChangeLevel == 0) {
            if (b && this.cContainer != null) {
                if (this.cChangeType != 0) {
                    final int cChangeType = this.cChangeType;
                    final Graphics graphics = this.cParent.getGraphics();
                    if (graphics != null) {
                        final Rectangle flow = this.flow(graphics);
                        if (flow != null) {
                            flow.translate(this.cMargins.left, this.cMargins.top - this.cContainer.getYOffset());
                            this.cParent.repaint(flow.x, flow.y, flow.width, flow.height);
                        }
                        else {
                            this.cParent.repaint();
                        }
                        graphics.dispose();
                    }
                    if ((cChangeType & 0x3) > 0) {
                        this.changed(false);
                    }
                    else {
                        this.changed(true);
                    }
                }
                this.makeVisible();
            }
            if (this.cInsPt != null) {
                this.cInsPt.updateCaret();
            }
            this.cCaret.setEnabled(true);
        }
    }
    
    synchronized Rectangle flow(final Graphics graphics) {
        if (this.cChangeType == 0 || this.cWidth <= 0 || this.cHeight <= 0) {
            return null;
        }
        this.init();
        CLine cFirstChange;
        CLine cLastChange;
        if (this.cChangeType == 4) {
            cFirstChange = this.cFirstChange;
            cLastChange = this.cLastChange;
        }
        else {
            cLastChange = (cFirstChange = null);
        }
        final Rectangle flow = this.cContainer.flow(graphics, cFirstChange, this.cFirstChangeOffset, cLastChange);
        this.cFirstChange = null;
        this.cLastChange = null;
        this.cFlowed = true;
        this.cChangeType = 0;
        return flow;
    }
    
    Dimension minimumSize() {
        Dimension minimumSize;
        if (this.cContainer != null) {
            minimumSize = this.cContainer.minimumSize();
        }
        else {
            minimumSize = new Dimension();
        }
        final Dimension dimension = minimumSize;
        dimension.height += this.cMargins.top + this.cMargins.bottom;
        final Dimension dimension2 = minimumSize;
        dimension2.width += this.cMargins.left + this.cMargins.right;
        return minimumSize;
    }
    
    boolean keyAction(final int n, final boolean b, final boolean b2) {
        boolean b3 = true;
        int n2 = 2;
        if (b) {
            n2 |= 0x1;
        }
        switch (n) {
            case 37: {
                this.moveLeft(n2, b2);
                break;
            }
            case 39: {
                this.moveRight(n2, b2);
                break;
            }
            case 38: {
                this.moveUp(n2);
                break;
            }
            case 40: {
                this.moveDown(n2);
                break;
            }
            case 33: {
                this.movePageUp(n2);
                break;
            }
            case 34: {
                this.movePageDown(n2);
                break;
            }
            case 36: {
                if (b2) {
                    this.moveBOS(n2);
                    break;
                }
                this.moveHome(n2);
                break;
            }
            case 35: {
                if (b2) {
                    this.moveEOS(n2);
                    break;
                }
                this.moveEnd(n2);
                break;
            }
            case 8: {
                if (this.cEditable) {
                    this.beginChange();
                    this.deleteSelection(false);
                    if (b2) {
                        this.cInsPt.deleteWord();
                        this.dataChanged(4);
                    }
                    else {
                        this.backspace();
                    }
                    this.endChange(true);
                    break;
                }
                break;
            }
            case 10: {
                if (this.cEditable) {
                    this.beginChange();
                    this.deleteSelection(false);
                    if (b2) {
                        this.cInsPt.insertLineBreak();
                    }
                    else {
                        this.cInsPt.splitPara();
                    }
                    this.dataChanged(4);
                    this.endChange(true);
                    break;
                }
                break;
            }
            case 127: {
                if (this.cEditable) {
                    this.beginChange();
                    if (this.cSelection.hasSelection()) {
                        this.deleteSelection(false);
                    }
                    else {
                        if (b2) {
                            this.cInsPt.deleteWord();
                        }
                        else {
                            this.cInsPt.delete(1);
                        }
                        this.dataChanged(4);
                    }
                    this.endChange(true);
                    break;
                }
                break;
            }
            case 27: {
                if (this.cSelection.hasSelection()) {
                    this.deselect();
                    break;
                }
                break;
            }
            default: {
                if (b2 && n != 0) {
                    this.beginChange();
                    switch (n) {
                        case 66: {
                            this.setBold(!this.isBold());
                            break;
                        }
                        case 67: {
                            this.doCopy();
                            break;
                        }
                        case 73: {
                            this.setItalic(!this.isItalic());
                            break;
                        }
                        case 78: {
                            this.setNormal();
                            break;
                        }
                        case 84: {
                            this.setStrikeThrough(!this.isStrikeThrough());
                            break;
                        }
                        case 85: {
                            this.setUnderline(!this.isUnderline());
                            break;
                        }
                        case 86: {
                            if (this.cEditable) {
                                this.doPaste();
                                break;
                            }
                            break;
                        }
                        case 88: {
                            if (this.cEditable) {
                                this.doCut();
                                break;
                            }
                            break;
                        }
                        default: {
                            b3 = false;
                            break;
                        }
                    }
                    this.endChange(b3);
                    break;
                }
                break;
            }
        }
        return b3;
    }
    
    boolean keyTyped(final char c) {
        boolean b = true;
        if (this.cEditable && c >= ' ') {
            this.beginChange();
            this.deleteSelection(true);
            this.cInsPt.insert(c);
            this.dataChanged(4);
            this.endChange(true);
        }
        else {
            b = false;
        }
        return b;
    }
    
    private void deleteSelection(final boolean b) {
        if (this.cEditable && this.cSelection.hasSelection()) {
            this.cSelection.delete();
            this.cInsPt.set(this.cSelection.getStart(), null, b);
            this.cSelection.set(this.cSelection.getStart());
            this.cCaret.setEnabled(true);
            this.dataChanged(4);
        }
    }
    
    private void clearSelection() {
        if (this.cSelection.hasSelection()) {
            final Graphics graphics = this.cParent.getGraphics();
            this.paintSelection(this.cSelection, graphics);
            graphics.dispose();
            if (this.cHasFocus) {
                this.cCaret.setEnabled(true);
            }
            this.cSelection.set(this.cInsPt.getPosition());
        }
    }
    
    final boolean isSelectionValid() {
        return this.cSelection.hasSelection();
    }
    
    boolean isEmpty() {
        return this.cFirstPara.getNext() == null && this.cFirstPara.getDataLen() == 0;
    }
    
    void setCursorPos(final int[] cPositionOffsets) {
        boolean b = false;
        if (this.cContainer != null) {
            b = this.cContainer.setFirstLineByOffsets(cPositionOffsets);
            if (b) {
                b = this.setInsertionPointByOffsets(cPositionOffsets);
            }
        }
        if (b) {
            this.cPositionOffsets = null;
        }
        else {
            this.cPositionOffsets = cPositionOffsets;
            this.dataChanged(1);
            this.cParent.repaint();
        }
    }
    
    int[] getInsertionPointOffsets() {
        final int[] array = new int[2];
        array[0] = (array[1] = 0);
        if (this.cInsPt != null) {
            final CParagraph para = this.cInsPt.getPosition().getPara();
            int n = 0;
            int n2 = 0;
            CParagraph cFirstPara = this.cFirstPara;
            while (cFirstPara != null && n == 0) {
                if (cFirstPara == para) {
                    n = 1;
                }
                else {
                    ++n2;
                    cFirstPara = (CParagraph)cFirstPara.getNext();
                }
            }
            if (n == 1) {
                array[0] = n2;
                array[1] = this.cInsPt.getPosition().getOffset();
            }
        }
        return array;
    }
    
    boolean setInsertionPointByOffsets(final int[] array) {
        boolean b = false;
        CParagraph cParagraph = null;
        CParagraph cFirstPara = this.cFirstPara;
        final int n = array[3];
        for (int n2 = 0; n2 < array[2] && cFirstPara != null; cFirstPara = (CParagraph)cFirstPara.getNext(), ++n2) {
            cParagraph = cFirstPara;
        }
        int n3;
        if (cFirstPara == null) {
            cFirstPara = cParagraph;
            n3 = cFirstPara.getDataLen();
        }
        else if (cFirstPara.getDataLen() < array[3]) {
            n3 = cFirstPara.getDataLen();
        }
        else {
            n3 = array[3];
            b = true;
        }
        this.move(new CTextPointer(cFirstPara, n3), 0);
        return b;
    }
    
    private void move(final Point point, CLine pointToLine, int n) {
        CTextPointer pointToTextPointer = null;
        final Graphics graphics = this.cParent.getGraphics();
        if (pointToLine == null) {
            pointToLine = this.cContainer.pointToLine(graphics, point);
        }
        if (pointToLine != null) {
            pointToTextPointer = pointToLine.pointToTextPointer(graphics, point);
        }
        if (pointToTextPointer != null) {
            if (pointToTextPointer.isImage() && (n & 0x1) == 0x0) {
                n |= 0x4;
            }
            this.move(graphics, pointToTextPointer, pointToLine, n);
        }
        else {
            this.move(this.cInsPt.getPosition(), n);
        }
        graphics.dispose();
    }
    
    private void move(final CTextPointer cTextPointer, final int n) {
        this.move(null, cTextPointer, null, n);
    }
    
    private void move(final Graphics graphics, final CTextPointer cTextPointer, final CLine cLine, final int n) {
        this.init();
        if ((n & 0x5) == 0x4) {
            cTextPointer.endOfWord();
            this.move(graphics, cTextPointer, cLine, 0);
            cTextPointer.prevWord();
            this.move(graphics, cTextPointer, cLine, 5);
            if (!this.cSelection.getStart().isImage()) {
                this.cInsPt.set(this.cSelection.getEnd());
            }
            return;
        }
        final boolean hasSelection = this.cSelection.hasSelection();
        if ((n & 0x1) == 0x0 && hasSelection) {
            this.clearSelection();
        }
        if ((n & 0x1) != 0x0) {
            if (!this.cInsPt.getPosition().equals(cTextPointer)) {
                CSelection cSelection;
                if (hasSelection) {
                    cSelection = this.cTempSelection;
                    if (this.cSelection.getStart().equals(this.cInsPt.getPosition())) {
                        cSelection.setStart(cTextPointer);
                        cSelection.setEnd(this.cSelection.getStart());
                        this.cSelection.setStart(cTextPointer);
                    }
                    else {
                        cSelection.setStart(this.cSelection.getEnd());
                        cSelection.setEnd(cTextPointer);
                        this.cSelection.setEnd(cTextPointer);
                    }
                    cSelection.adjust();
                    this.cSelection.adjust();
                }
                else {
                    cSelection = this.cSelection;
                    if (cTextPointer.isBefore(this.cInsPt.getPosition())) {
                        this.cSelection.setStart(cTextPointer);
                        this.cSelection.setEnd(this.cInsPt.getPosition());
                    }
                    else {
                        this.cSelection.setStart(this.cInsPt.getPosition());
                        this.cSelection.setEnd(cTextPointer);
                    }
                }
                if (cSelection.hasSelection()) {
                    final Graphics graphics2 = (graphics == null) ? this.cParent.getGraphics() : graphics;
                    this.paintSelection(cSelection, graphics2);
                    if (graphics == null) {
                        graphics2.dispose();
                    }
                }
            }
        }
        else {
            this.cSelection.set(cTextPointer);
        }
        this.cInsPt.set(cTextPointer, cLine, false);
        if (hasSelection != this.cSelection.hasSelection()) {
            this.cCaret.setEnabled(hasSelection);
        }
        this.makeVisible();
        if ((n & 0x2) != 0x0) {
            this.changed(true);
        }
    }
    
    private void makeVisible() {
        if (this.cContainer != null && this.cInsPt != null && this.cContainer.makeVisible(this.cInsPt)) {
            this.cMoved = true;
            this.cParent.repaint();
        }
    }
    
    private void moveLeft(final int n, final boolean b) {
        final CTextPointer cTextPointer = (CTextPointer)this.cInsPt.getPosition().clone();
        if (b) {
            cTextPointer.prevWord();
        }
        else {
            cTextPointer.retreatByElement(1);
        }
        this.move(null, cTextPointer, this.cInsPt.getLine(), n);
    }
    
    private void moveRight(final int n, final boolean b) {
        final CTextPointer cTextPointer = (CTextPointer)this.cInsPt.getPosition().clone();
        if (b) {
            cTextPointer.nextWord();
        }
        else {
            cTextPointer.advanceByElement(1);
        }
        this.move(null, cTextPointer, this.cInsPt.getLine(), n);
    }
    
    private void moveUp(final int n) {
        CLine cLine;
        Point point;
        for (cLine = (CLine)this.cInsPt.getLine().getPrevious(), point = this.cInsPt.getPoint(); cLine != null && cLine.getBaseline() > point.y; cLine = (CLine)cLine.getPrevious()) {}
        if (cLine != null) {
            if (cLine.getLeft() >= point.x) {
                for (CLine cLine2 = (CLine)cLine.getPrevious(); cLine2 != null && cLine2.getBaseline() > cLine.getY(); cLine2 = (CLine)cLine2.getPrevious()) {
                    cLine = cLine2;
                    if (cLine2.getLeft() < point.x) {
                        break;
                    }
                }
            }
            this.move(new Point(point.x, cLine.getY()), cLine, n);
        }
    }
    
    private void moveDown(final int n) {
        final CLine line = this.cInsPt.getLine();
        CLine cLine = (CLine)line.getNext();
        final Point point = this.cInsPt.getPoint();
        for (int baseline = line.getBaseline(); cLine != null && cLine.getLeft() > point.x && baseline > cLine.getY(); cLine = (CLine)cLine.getNext()) {}
        if (cLine != null) {
            int n2 = cLine.getRight();
            if (n2 < point.x && !cLine.getEnd().isEOP()) {
                for (CLine cLine2 = (CLine)cLine.getNext(); cLine2 != null && cLine2.getLeft() >= n2; n2 = cLine2.getRight(), cLine2 = (CLine)cLine2.getNext()) {
                    cLine = cLine2;
                    if (cLine2.getRight() > point.x) {
                        break;
                    }
                }
            }
            this.move(new Point(point.x, cLine.getY()), cLine, n);
        }
    }
    
    private void movePageUp(final int n) {
        this.cMoved = this.cContainer.pageUp();
        final int viewY = this.cContainer.getViewY();
        int flowHeight = this.cInsPt.getPoint().y - this.cContainer.getHeight();
        if (flowHeight < viewY) {
            flowHeight = viewY;
        }
        else if (flowHeight > this.cContainer.getFlowHeight()) {
            flowHeight = this.cContainer.getFlowHeight();
        }
        this.move(new Point(this.cInsPt.getPoint().x, flowHeight), null, n);
        this.cParent.repaint();
    }
    
    private void movePageDown(final int n) {
        this.cMoved = this.cContainer.pageDown();
        final int viewY = this.cContainer.getViewY();
        int flowHeight = this.cInsPt.getPoint().y + this.cContainer.getHeight();
        if (flowHeight < viewY) {
            flowHeight = viewY;
        }
        else if (flowHeight > this.cContainer.getFlowHeight()) {
            flowHeight = this.cContainer.getFlowHeight();
        }
        this.move(new Point(this.cInsPt.getPoint().x, flowHeight), null, n);
        this.cParent.repaint();
    }
    
    void moveBOS(final int n) {
        this.move(new CTextPointer(this.cFirstPara, 0), n);
    }
    
    private void moveHome(final int n) {
        final CLine line = this.cInsPt.getLine();
        this.move(null, (CTextPointer)line.getStart().clone(), line, n);
    }
    
    private void moveEnd(final int n) {
        final CLine line = this.cInsPt.getLine();
        this.move(null, line.getEnd(), line, n);
    }
    
    void moveEOS(final int n) {
        final CTextPointer cTextPointer = (CTextPointer)this.cInsPt.getPosition().clone();
        cTextPointer.endOfStream();
        this.move(cTextPointer, n);
    }
    
    private boolean backspace() {
        if (this.cInsPt.getPosition().isBOS()) {
            return false;
        }
        final CFormatInfo cFormatInfo = (CFormatInfo)this.cInsPt.getLocalInfo().clone();
        this.cCaret.setEnabled(false);
        this.moveLeft(0, false);
        if (this.cEditable) {
            this.cInsPt.delete(1);
        }
        cFormatInfo.apply(this.cInsPt.getLocalInfo());
        this.cCaret.setEnabled(true);
        this.dataChanged(4);
        return this.cEditable;
    }
    
    void paint(final Graphics graphics, final Graphics graphics2) {
        if (this.cTracker != null) {
            try {
                for (int n = 10; !this.cTracker.checkAll(true) && n > 0; --n) {
                    this.cTracker.waitForAll(200L);
                }
                this.cChangeType = 8;
            }
            catch (InterruptedException ex) {
                System.out.println("Problem loading image(s)");
            }
            this.cTracker = null;
        }
        if (this.cChangeType != 0) {
            synchronized (this) {
                this.flow(graphics2);
                this.cChangeType = 0;
                if (this.cInsPt != null) {
                    this.cInsPt.invalidate();
                    this.cInsPt.updateCaret();
                }
            }
        }
        if (this.cPositionOffsets != null) {
            this.setCursorPos(this.cPositionOffsets);
            this.cPositionOffsets = null;
        }
        if (this.cMoved || this.cFlowed) {
            this.recalibrate();
            final boolean b = false;
            this.cFlowed = b;
            this.cMoved = b;
        }
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.cWidth, this.cHeight);
        graphics.translate(this.cMargins.left, this.cMargins.top);
        if (graphics != graphics2) {
            graphics2.translate(this.cMargins.left, this.cMargins.top);
        }
        this.cContainer.paint(graphics, graphics2);
        graphics.translate(-this.cMargins.left, -this.cMargins.top);
        if (graphics != graphics2) {
            graphics2.translate(-this.cMargins.left, -this.cMargins.top);
        }
        if (this.cSelection.hasSelection()) {
            this.paintSelection(this.cSelection, graphics);
        }
    }
    
    void recalibrate() {
        if (this.cScrollBar != null) {
            final int viewY = this.cContainer.getViewY();
            final int n = this.cContainer.getFlowHeight() + this.cMargins.top + this.cMargins.bottom;
            if (n <= this.cHeight) {
                this.cScrollBar.setEnabled(false);
            }
            else {
                this.cScrollBar.setEnabled(true);
                this.cScrollBar.setUnitIncrement(16);
                this.cScrollBar.setBlockIncrement(this.cHeight - this.cScrollBar.getUnitIncrement());
                int n2 = n;
                if (this.cSubtractHeightFromScrollMax) {
                    n2 -= this.cHeight;
                }
                this.cScrollBar.setValues(viewY, this.cHeight, 0, n2);
            }
        }
    }
    
    final void prePaint() {
        if (this.cHasFocus && !this.cSelection.hasSelection()) {
            this.cCaret.setEnabled(false);
        }
    }
    
    void postPaint(final Graphics graphics) {
        if (this.cHasFocus && !this.cSelection.hasSelection()) {
            this.cCaret.setEnabled(true);
            if (this.cCaret.isShowable()) {
                this.cCaret.show();
            }
        }
    }
    
    final void setFocus(final boolean b) {
        if (b != this.cHasFocus) {
            this.cCaret.setFocus(b);
            this.cHasFocus = b;
        }
    }
    
    void showCursor() {
        this.cCaret.setFocus(true);
    }
    
    final CRTEdit getParent() {
        return this.cParent;
    }
    
    void mouseDown(final int n, final int n2, final boolean b, final int n3) {
        final Point point = new Point(n - this.cMargins.left, n2 + this.cContainer.getYOffset() - this.cMargins.top);
        int n4 = 0;
        if (b) {
            n4 |= 0x1;
        }
        if (n3 > 1) {
            n4 |= 0x4;
        }
        this.move(point, null, n4);
    }
    
    void mouseUp() {
        this.changed(true);
    }
    
    void mouseDrag(final int n, final int n2) {
        this.move(new Point(n - this.cMargins.left, n2 + this.cContainer.getYOffset() - this.cMargins.top), null, 1);
    }
    
    private void paintSelection(final CSelection cSelection, final Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.translate(this.cMargins.left, this.cMargins.top);
        graphics.setXORMode(this.getBackground());
        this.cContainer.paintSelection(cSelection, graphics);
        graphics.setPaintMode();
        graphics.translate(-this.cMargins.left, -this.cMargins.top);
        graphics.setColor(color);
    }
    
    private CSelection getSelection(final boolean b) {
        this.init();
        if (this.cSelection.hasSelection()) {
            this.cTempSelection.setStart(this.cSelection.getStart());
            this.cTempSelection.setEnd(this.cSelection.getEnd());
            if (this.cTempSelection.getEnd().isBOP()) {
                final CTextPointer end = this.cTempSelection.getEnd();
                if (end.prevPara()) {
                    end.endOfPara();
                }
            }
        }
        else {
            final CTextPointer position = this.cInsPt.getPosition();
            this.cTempSelection.set(position);
            if (b && position.isInWord()) {
                this.cTempSelection.selectWord();
            }
        }
        return this.cTempSelection;
    }
    
    void changed(boolean b) {
        final RTEdit rtEdit = (RTEdit)this.cParent.getParent();
        final CFormatInfo formatInfo = this.getFormatInfo();
        final CParaSettings paraSettings = this.cInsPt.getPosition().getParaSettings();
        if (b && (!formatInfo.equals(this.cCachedFormatInfo) || !paraSettings.equals(this.cCachedParaSettings))) {
            b = false;
        }
        rtEdit.processActionEvent(b);
        formatInfo.duplicate(this.cCachedFormatInfo);
        paraSettings.duplicate(this.cCachedParaSettings);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final int adjustmentType = adjustmentEvent.getAdjustmentType();
        final Scrollbar scrollbar = (Scrollbar)adjustmentEvent.getSource();
        final int viewY = this.cContainer.getViewY();
        int value = adjustmentEvent.getValue();
        if (value < 0) {
            value = 0;
        }
        else {
            final int maximum = scrollbar.getMaximum();
            if (value > maximum) {
                value = maximum - this.cHeight;
            }
        }
        if (adjustmentType != 5) {
            scrollbar.setValue(value);
        }
        if (value != viewY) {
            this.cContainer.setViewY(value);
            this.cMoved = true;
            this.cParent.repaint();
        }
    }
    
    String getStyle() {
        this.init();
        return this.cInsPt.getStyle().getName();
    }
    
    boolean setStyle(final String s) {
        if (!this.cEditable) {
            return false;
        }
        final CParaStyle style = this.getParaStyleMgr().getStyle(s);
        if (style != null) {
            this.init();
            if (this.cSelection.hasSelection()) {
                this.cSelection.setStyle(style);
            }
            else {
                this.cInsPt.setStyle(style);
            }
            this.dataChanged(4);
            return true;
        }
        return false;
    }
    
    boolean setStylePreserve(final String style) {
        final CFormatInfo formatInfo = this.cInsPt.getFormatInfo();
        final int alignment = this.cInsPt.getPosition().getParaSettings().getAlignment();
        final boolean setStyle = this.setStyle(style);
        if (setStyle) {
            formatInfo.apply(this.cInsPt.getFormatInfo());
            this.cInsPt.setFormatInfo(formatInfo);
            this.cInsPt.getPosition().getParaSettings().setAlignment(alignment);
        }
        return setStyle;
    }
    
    String[] getStyles() {
        return this.getParaStyleMgr().getStyles();
    }
    
    final int getHeight() {
        return this.cHeight;
    }
    
    final void setEditable(final boolean cEditable) {
        this.cEditable = cEditable;
    }
    
    final boolean isEditable() {
        return this.cEditable;
    }
    
    private CFormatInfo getFormatInfo() {
        this.init();
        if (this.cSelection.hasSelection() && this.cSelection.getEnd().equals(this.cInsPt.getPosition())) {
            final CTextPointer cTextPointer = (CTextPointer)this.getSelection(false).getEnd().clone();
            cTextPointer.retreatByElement(1);
            return cTextPointer.getFormatInfo();
        }
        this.cInsPt.getFormatInfo(this.cInfo);
        return this.cInfo;
    }
    
    void setBold(final boolean bold) {
        if (this.cEditable) {
            final CFormatInfo cFormatInfo = new CFormatInfo();
            cFormatInfo.setBold(bold);
            this.apply(cFormatInfo);
        }
    }
    
    boolean isBold() {
        return this.getFormatInfo().isBold();
    }
    
    void setStrikeThrough(final boolean strikeThrough) {
        if (this.cEditable) {
            final CFormatInfo cFormatInfo = new CFormatInfo();
            cFormatInfo.setStrikeThrough(strikeThrough);
            this.apply(cFormatInfo);
        }
    }
    
    boolean isStrikeThrough() {
        return this.getFormatInfo().isStrikeThrough();
    }
    
    void setItalic(final boolean italic) {
        if (this.cEditable) {
            final CFormatInfo cFormatInfo = new CFormatInfo();
            cFormatInfo.setItalic(italic);
            this.apply(cFormatInfo);
        }
    }
    
    boolean isItalic() {
        return this.getFormatInfo().isItalic();
    }
    
    void setUnderline(final boolean underline) {
        if (this.cEditable) {
            final CFormatInfo cFormatInfo = new CFormatInfo();
            cFormatInfo.setUnderline(underline);
            this.apply(cFormatInfo);
        }
    }
    
    boolean isUnderline() {
        return this.getFormatInfo().isUnderline();
    }
    
    void setNormal() {
        if (this.cEditable) {
            final CSelection selection = this.getSelection(false);
            final CFormatInfo cFormatInfo = new CFormatInfo();
            final CParaSettings cParaSettings = new CParaSettings();
            cParaSettings.setNormal();
            selection.apply(cParaSettings);
            cFormatInfo.setNormal();
            if (selection.hasSelection()) {
                selection.apply(cFormatInfo);
                this.cInsPt.needInfo();
            }
            else {
                this.cInsPt.apply(cFormatInfo);
            }
            this.dataChanged(4);
        }
    }
    
    void setPointSize(final int pointSize) {
        if (this.cEditable) {
            final CFormatInfo cFormatInfo = new CFormatInfo();
            cFormatInfo.setPointSize(pointSize);
            this.apply(cFormatInfo);
        }
    }
    
    int getPointSize() {
        return this.getFormatInfo().getPointSize();
    }
    
    void setFaceName(final String faceName) {
        if (this.cEditable) {
            final CFormatInfo cFormatInfo = new CFormatInfo();
            cFormatInfo.setFaceName(faceName);
            this.apply(cFormatInfo);
        }
    }
    
    String getFaceName() {
        return this.getFormatInfo().getFaceName();
    }
    
    void setFontColor(final Color color) {
        if (this.cEditable) {
            final CFormatInfo cFormatInfo = new CFormatInfo();
            cFormatInfo.setColor(color);
            this.apply(cFormatInfo);
        }
    }
    
    Color getFontColor() {
        return this.getFormatInfo().getColor();
    }
    
    void setURL(final String s) {
        if (this.cEditable) {
            String string = s;
            if (string != null && string.length() > 0) {
                try {
                    final URL url = new URL(s);
                }
                catch (MalformedURLException ex) {
                    if (s.indexOf("//") == -1) {
                        string = "http://" + s;
                    }
                }
                if (!this.getSelection(true).hasSelection()) {
                    final CTextPointer cTextPointer = (CTextPointer)this.cInsPt.getPosition().clone();
                    this.cInsPt.insert(s, 0, s.length());
                    this.move(cTextPointer, 0);
                    final CTextPointer cTextPointer2 = (CTextPointer)cTextPointer.clone();
                    cTextPointer2.advanceByElement(s.length());
                    this.move(cTextPointer2, 3);
                }
            }
            final CFormatInfo cFormatInfo = new CFormatInfo();
            cFormatInfo.setURL(string);
            this.apply(cFormatInfo);
            if (string != null && string.length() > 0) {
                cFormatInfo.setURL(null);
                cFormatInfo.apply(this.cInsPt.getLocalInfo());
            }
            this.deselect();
        }
    }
    
    String getURL() {
        String url = null;
        final CSelection selection = this.getSelection(true);
        if (selection.hasSelection()) {
            selection.adjust();
            final CTextPointer start = selection.getStart();
            final CParaFormatList paraFormat = start.getPara().getParaFormat(start.getOffset());
            if (paraFormat != null) {
                final String url2 = paraFormat.getFormatInfo().getURL();
                if (url2 != null) {
                    final CTextPointer end = selection.getEnd();
                    if (start.isSamePara(end)) {
                        if (paraFormat.getLength() >= Math.abs(start.getOffset() - selection.getEnd().getOffset())) {
                            url = url2;
                        }
                    }
                    else {
                        final CParaFormatList paraFormat2 = end.getPara().getParaFormat(0);
                        if (paraFormat2.getFormatInfo().getURL() == url2 && start.getRunDistance(end) + end.getOffset() == paraFormat2.getLength() + paraFormat.getLength()) {
                            url = url2;
                        }
                    }
                }
            }
        }
        else {
            url = this.getFormatInfo().getURL();
        }
        return url;
    }
    
    void deselect() {
        if (this.cSelection.hasSelection()) {
            this.move(this.cInsPt.getPosition(), 0);
        }
    }
    
    void selectAll() {
        this.moveBOS(0);
        this.moveEOS(3);
    }
    
    void selectURL() {
        final String url = this.getFormatInfo().getURL();
        if (url != null) {
            final CTextPointer cTextPointer = (CTextPointer)this.cInsPt.getPosition().clone();
            if (cTextPointer.isEOP() && !cTextPointer.isBOP()) {
                cTextPointer.retreatByElement(1);
            }
            final CTextPointer cTextPointer2 = (CTextPointer)cTextPointer.clone();
            cTextPointer.startOfURL(url);
            cTextPointer2.endOfURL(url);
            this.move(cTextPointer, 0);
            this.move(cTextPointer2, 1);
        }
    }
    
    final void setAlignment(int n) {
        if (this.cEditable) {
            final CSelection selection = this.getSelection(false);
            if (selection.isImage()) {
                final CImage image = selection.getStart().getImage();
                if (image != null) {
                    if (n == 1) {
                        n = 4;
                    }
                    image.setAlignment(n);
                    this.dataChanged(2);
                }
            }
            else {
                final CParaSettings cParaSettings = new CParaSettings();
                cParaSettings.setAlignment(n);
                this.apply(cParaSettings);
            }
        }
    }
    
    int getAlignment() {
        final CSelection selection = this.getSelection(false);
        selection.getStart().getParaSettings(this.cSettings);
        int alignment = this.cSettings.getAlignment();
        if (selection.isImage()) {
            final CImage image = selection.getStart().getImage();
            if (image != null) {
                final int alignment2 = image.getAlignment();
                if (alignment2 > 2 || alignment2 < 0) {
                    alignment = 1;
                }
                else {
                    alignment = alignment2;
                }
            }
        }
        return alignment;
    }
    
    void setFirstIndent(final int firstIndent) {
        if (this.cEditable) {
            final CParaSettings cParaSettings = new CParaSettings();
            cParaSettings.setFirstIndent(firstIndent);
            this.apply(cParaSettings);
        }
    }
    
    int getFirstIndent() {
        this.getSelection(false).getStart().getParaSettings(this.cSettings);
        return this.cSettings.getFirstIndent();
    }
    
    void setRestIndent(final int restIndent) {
        if (this.cEditable) {
            final CParaSettings cParaSettings = new CParaSettings();
            cParaSettings.setRestIndent(restIndent);
            this.apply(cParaSettings);
        }
    }
    
    int getRestIndent() {
        this.getSelection(false).getStart().getParaSettings(this.cSettings);
        return this.cSettings.getRestIndent();
    }
    
    void setRightIndent(final int rightIndent) {
        if (this.cEditable) {
            final CParaSettings cParaSettings = new CParaSettings();
            cParaSettings.setRightIndent(rightIndent);
            this.apply(cParaSettings);
        }
    }
    
    int getRightIndent() {
        this.getSelection(false).getStart().getParaSettings(this.cSettings);
        return this.cSettings.getRightIndent();
    }
    
    void setBullet(final char bullet) {
        if (this.cEditable) {
            final CParaSettings cParaSettings = new CParaSettings();
            cParaSettings.setBullet(bullet);
            this.apply(cParaSettings);
        }
    }
    
    char getBullet() {
        this.getSelection(false).getStart().getParaSettings(this.cSettings);
        return this.cSettings.getBullet();
    }
    
    void setSpaceAbove(final int spaceAbove) {
        if (this.cEditable) {
            final CParaSettings cParaSettings = new CParaSettings();
            cParaSettings.setSpaceAbove(spaceAbove);
            this.apply(cParaSettings);
        }
    }
    
    int getSpaceAbove() {
        this.getSelection(false).getStart().getParaSettings(this.cSettings);
        return this.cSettings.getSpaceAbove();
    }
    
    private void apply(final CFormatInfo cFormatInfo) {
        if (this.cEditable) {
            final CSelection selection = this.getSelection(true);
            if (selection.hasSelection()) {
                selection.apply(cFormatInfo);
                this.cInsPt.needInfo();
            }
            this.cInsPt.apply(cFormatInfo);
            this.dataChanged(2);
        }
    }
    
    private void apply(final CParaSettings cParaSettings) {
        if (this.cEditable) {
            this.getSelection(true).apply(cParaSettings);
            this.cInsPt.invalidate();
            this.dataChanged(2);
        }
    }
    
    void changeStyle(final String s, final CFormatInfo cFormatInfo) {
        if (this.cEditable) {
            this.init();
            final CParaStyle style = this.getParaStyleMgr().getStyle(s);
            if (style != null) {
                style.apply(cFormatInfo);
                this.dataChanged(2);
            }
        }
    }
    
    void changeStyle(final String s, final CParaSettings cParaSettings) {
        if (this.cEditable) {
            final CParaStyle style = this.getParaStyleMgr().getStyle(s);
            if (style != null) {
                style.apply(cParaSettings);
                this.dataChanged(2);
            }
        }
    }
    
    void setPointSize(final String s, final int pointSize) {
        final CFormatInfo cFormatInfo = new CFormatInfo();
        cFormatInfo.setPointSize(pointSize);
        this.changeStyle(s, cFormatInfo);
    }
    
    int getPointSize(final String s) {
        final CParaStyle style = this.getParaStyleMgr().getStyle(s);
        return (style != null) ? style.getFormatInfo().getPointSize() : 0;
    }
    
    void setFaceName(final String s, final String faceName) {
        final CFormatInfo cFormatInfo = new CFormatInfo();
        cFormatInfo.setFaceName(faceName);
        this.changeStyle(s, cFormatInfo);
    }
    
    String getFaceName(final String s) {
        final CParaStyle style = this.getParaStyleMgr().getStyle(s);
        return (style != null) ? style.getFormatInfo().getFaceName() : null;
    }
    
    void setFontColor(final String s, final Color color) {
        final CFormatInfo cFormatInfo = new CFormatInfo();
        cFormatInfo.setColor(color);
        this.changeStyle(s, cFormatInfo);
    }
    
    Color getFontColor(final String s) {
        final CParaStyle style = this.getParaStyleMgr().getStyle(s);
        return (style != null) ? style.getFormatInfo().getColor() : null;
    }
    
    void dataChanged(final int n) {
        this.cChangeType |= n;
        if (this.cInsPt != null) {
            this.cInsPt.invalidate();
        }
    }
    
    void imageChanged() {
        this.dataChanged(8);
    }
    
    void addChange(final CLine cLine, final int n) {
        if (this.cFirstChange == cLine && n < this.cFirstChangeOffset) {
            this.cFirstChangeOffset = n;
        }
        else if (this.cFirstChange == null || cLine.getY() < this.cFirstChange.getY()) {
            this.cFirstChange = cLine;
            this.cFirstChangeOffset = n;
        }
        if (this.cLastChange == null || cLine.getY() > this.cLastChange.getY()) {
            this.cLastChange = cLine;
        }
    }
    
    final void init() {
        if (this.cFirstPara == null) {
            this.clear();
        }
    }
    
    private void clear() {
        this.cFirstPara = new CParagraph(this.getParaStyleMgr().getDefault(), this);
        this.cInsPt = new CInsertionPoint(this, this.cFirstPara, this.cCaret);
        (this.cSelection = new CSelection(this)).set(this.cInsPt.getPosition());
        this.getFormatInfo().duplicate(this.cCachedFormatInfo);
        this.cInsPt.getPosition().getParaSettings().duplicate(this.cCachedParaSettings);
        this.cEscMgr.clear();
    }
    
    final CParagraph getFirstPara() {
        return this.cFirstPara;
    }
    
    void setLinkColor(final Color cLinkColor) {
        this.cLinkColor = cLinkColor;
    }
    
    Color getLinkColor() {
        if (this.cLinkColor == null) {
            this.cLinkColor = Color.blue;
        }
        return this.cLinkColor;
    }
    
    void importText(final String s, final String s2, final CTextPointer cTextPointer) {
        final CParagraph para = cTextPointer.getPara();
        if (s.equalsIgnoreCase("text/html")) {
            new CReadHtml(this, para).setText(s2);
        }
        else {
            new CRWAscii(cTextPointer, null).setText(s2);
        }
    }
    
    String getDataFromURL(final String s) {
        String utf = null;
        DataInputStream dataInputStream = null;
        final URL url = Utility.makeURL(null, s);
        if (url != null) {
            try {
                final URLConnection openConnection = url.openConnection();
                openConnection.setDoInput(true);
                openConnection.setUseCaches(false);
                dataInputStream = new DataInputStream(new BufferedInputStream(openConnection.getInputStream()));
                utf = new UTFDISReader(dataInputStream).readUTF();
            }
            catch (Exception ex) {
                System.out.println("Exception reading data from URL");
            }
            finally {
                try {
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                }
                catch (IOException ex2) {}
            }
        }
        return utf;
    }
    
    void readText(final String s) {
        String s2 = "text/html";
        String s3 = s;
        String s4 = null;
        int n = 0;
        final String s5 = "Content-Type:";
        if (s != null && s.length() != 0) {
            int n2 = s.length() - 1;
            final int index;
            if (s.startsWith(s5) && (index = s.indexOf("\r\n\r\n")) != -1) {
                final String lowerCase = s.substring(0, index + 4).toLowerCase();
                if (lowerCase.indexOf("multi/mixed") != -1) {
                    final int index2 = s.indexOf("boundary=", 0);
                    if (index2 != -1) {
                        final int n3 = index2 + "boundary=".length();
                        n2 = s.indexOf("\r\n", index2);
                        String s6 = s.substring(n3, n2);
                        if (s6.endsWith("\"")) {
                            s6 = s6.substring(0, s6.length() - 1);
                        }
                        if (s6.startsWith("\"")) {
                            s6 = s6.substring(1, s6.length() - 1);
                        }
                        s4 = new String(s6);
                        final int index3 = s.indexOf(s5, s.indexOf(s4, n2));
                        if (index3 != -1) {
                            final int index4 = s.indexOf("\r\n\r\n", index3);
                            if (s.substring(index3, index4).toLowerCase().indexOf("text/html") == -1) {
                                s2 = "text/plain";
                            }
                            final int n4 = index4 + 4;
                            n2 = s.indexOf(s4, n4);
                            if (n2 != -1) {
                                n = 1;
                            }
                            else {
                                n2 = s.length() - 1;
                            }
                            s3 = s.substring(n4, n2);
                        }
                    }
                }
                else {
                    if (lowerCase.indexOf("text/html") == -1) {
                        s2 = "text/plain";
                    }
                    s3 = s.substring(index + 4);
                }
            }
            while (s3 != null) {
                if (s2 == "text/html") {
                    s3 = this.stripHeaderInfo(s3);
                }
                this.setText(s2, s3, true);
                if (n == 0) {
                    s3 = null;
                }
                else {
                    int n5 = n2 + s4.length() + 1;
                    final int index5 = s.indexOf(s5, n5);
                    if (index5 != -1) {
                        final int index6 = s.indexOf("\r\n\r\n", index5);
                        if (index6 != -1) {
                            if (s.substring(index5, index6).toLowerCase().indexOf("text/html") != -1) {
                                s2 = "text/html";
                            }
                            else {
                                s2 = "text/plain";
                            }
                            n5 = index6 + 4;
                        }
                        else {
                            s2 = "text/plain";
                        }
                    }
                    n2 = s.indexOf(s4, n5);
                    if (n2 == -1) {
                        if (n5 + 4 >= s.length() - 1) {
                            s3 = null;
                        }
                        else {
                            n2 = s.length() - 1;
                        }
                        n = 0;
                    }
                    if (s3 == null) {
                        continue;
                    }
                    s3 = s.substring(n5, n2);
                }
            }
        }
    }
    
    private String stripHeaderInfo(final String s) {
        final String s2 = "<FRAMESET";
        String s3 = s;
        final int index;
        if (s3.indexOf(s2) == -1 && (index = s3.indexOf(s2.toLowerCase())) == -1) {
            final String s4 = "<BODY";
            final String s5 = "<BASE";
            String substring = null;
            int n;
            if ((n = s3.indexOf(s5)) != -1 || (n = s3.indexOf(s5.toLowerCase())) != -1) {
                int index2;
                if ((index2 = s3.indexOf(62, n)) == -1) {
                    index2 = n + s5.length() + 1;
                }
                substring = s3.substring(n, index2 + 1);
            }
            int n2;
            if ((n2 = s3.indexOf(s4)) != -1 || (n2 = s3.indexOf(s4.toLowerCase())) != -1) {
                s3 = s3.substring(n2);
                final String s6 = "</BODY>";
                int n3;
                if ((n3 = s3.indexOf(s6)) != -1 || (n3 = s3.indexOf(s6.toLowerCase())) != -1) {
                    s3 = s3.substring(0, n3 + s6.length());
                }
            }
            final int index3;
            if (substring != null && (index3 = s3.indexOf(substring)) == -1) {
                s3 = substring + "<BR>" + s3;
            }
        }
        return s3;
    }
    
    void setText(final String s, final String s2, final boolean b) {
        CParagraph cParagraph;
        if (b) {
            this.init();
            if (this.cFirstPara.getDataLen() == 0 && this.cFirstPara.getNext() == null) {
                cParagraph = this.cFirstPara;
            }
            else {
                cParagraph = new CParagraph(this.getParaStyleMgr().getDefault());
                ((CParagraph)this.cFirstPara.getLast()).insert(cParagraph);
            }
        }
        else {
            this.clear();
            cParagraph = this.cFirstPara;
        }
        this.importText(s, s2, new CTextPointer(cParagraph, 0));
        this.dataChanged(1);
    }
    
    void insertText(final String s) {
        final boolean cEditable = this.cEditable;
        this.cEditable = true;
        this.beginChange();
        if (this.cSelection.hasSelection()) {
            this.deleteSelection(false);
        }
        this.cInsPt.insert(s, 0, s.length());
        this.dataChanged(1);
        this.endChange(true);
        this.cEditable = cEditable;
    }
    
    String getText(final String s) {
        return this.getText(s, new CTextPointer(this.cFirstPara, 0), null);
    }
    
    String getText(final String s, final CTextPointer cTextPointer, final CTextPointer cTextPointer2) {
        final StringBuffer sb = new StringBuffer();
        if (s.equalsIgnoreCase("text/html") || s.equalsIgnoreCase("text//html")) {
            sb.append(new CWriteHtml(this, cTextPointer, cTextPointer2).getText());
        }
        else if (s.equalsIgnoreCase("text/spell")) {
            sb.append(new CRWAscii(cTextPointer, cTextPointer2).getText(false));
        }
        else {
            sb.append(new CRWAscii(cTextPointer, cTextPointer2).getText(true));
        }
        return sb.toString();
    }
    
    Image getImageFromString(final String s) {
        final Applet applet = ((RTEdit)this.cParent.getParent()).getApplet();
        URL url;
        if (this.cBaseURLStr == null && applet != null) {
            url = applet.getDocumentBase();
        }
        else {
            url = Utility.makeURL(null, this.cBaseURLStr);
        }
        return Utility.loadImage(url, s, this.cParent, true);
    }
    
    boolean insertImage(final String s, final String s2, final String s3, final int n) {
        final Image imageFromString = this.getImageFromString(s);
        if (imageFromString == null) {
            return false;
        }
        this.beginChange();
        this.cInsPt.getPosition().insert(this.createEscapeData(s, s2, null, s3, imageFromString, n), this.cInsPt.getLocalInfo());
        this.dataChanged(1);
        this.endChange(false);
        this.cParent.repaint(10L);
        return true;
    }
    
    String createEscapeData(final String s, final String s2, final String s3, final String altText, final Image image, final int n) {
        final boolean b = s2 != null;
        int n2 = -1;
        if (this.cBehavior == 1 && (s2 == null || s2.length() == 0)) {
            final int alignment = this.cInsPt.getPosition().getParaSettings().getAlignment();
            if (alignment == 1) {
                n2 = 4;
            }
            else {
                n2 = alignment;
            }
        }
        else if (b) {
            if (s2.equalsIgnoreCase("left")) {
                n2 = 0;
            }
            else if (s2.equalsIgnoreCase("right")) {
                n2 = 2;
            }
            else if (s2.equalsIgnoreCase("top")) {
                n2 = 3;
            }
            else if (s2.equalsIgnoreCase("middle")) {
                n2 = 4;
            }
            else if (s2.equalsIgnoreCase("bottom")) {
                n2 = 5;
            }
        }
        final CImage cImage = new CImage(image, s, n2, n, this.cParent, this.cEscMgr);
        if (altText != null) {
            cImage.setAltText(altText);
        }
        final int addImageData = this.cEscMgr.addImageData(cImage);
        if (n == 3) {
            String string;
            if (s3 == null) {
                string = "Limerick" + addImageData;
            }
            else {
                string = s3;
            }
            cImage.setName(string);
        }
        return new String(new char[] { '\u2028', (char)n, (char)addImageData });
    }
    
    String setHeadline(final String s, final String s2, final String altText) {
        String name = s;
        if (s == null || s.length() == 0) {
            final CTextPointer cTextPointer = (CTextPointer)this.cInsPt.getPosition().clone();
            if (this.insertImage(s2, null, altText, 3)) {
                final CImage image = cTextPointer.getImage();
                if (image != null) {
                    name = image.getName();
                }
                if (name == null) {
                    name = "";
                }
            }
        }
        else {
            final CImage imageData;
            if ((imageData = this.cEscMgr.findImageData(s)) != null) {
                final Image imageFromString = this.getImageFromString(s2);
                if (imageFromString != null) {
                    this.beginChange();
                    imageData.updateImage(imageFromString, 3, s2);
                    if (altText != null) {
                        imageData.setAltText(altText);
                    }
                    this.dataChanged(1);
                    this.endChange(false);
                    this.cParent.repaint(10L);
                }
                else {
                    name = "";
                }
            }
        }
        return name;
    }
    
    public String getHeadline() {
        String string = "";
        final CTextPointer start = this.getSelection(false).getStart();
        if (start.isImage()) {
            final CImage image = start.getImage();
            final String name;
            if (image != null && (name = image.getName()) != null) {
                string = name + " " + image.getImageURLStr();
            }
        }
        return string;
    }
    
    final Color getBackground() {
        return this.cParent.getParent().getBackground();
    }
    
    final void setBackground(final Color background) {
        this.cParent.getParent().setBackground(background);
    }
    
    final int toTwips(final int n) {
        return this.cTwipsPerPixel * n;
    }
    
    final int toPixels(final int n) {
        return n / this.cTwipsPerPixel;
    }
    
    void doCut() {
        if (this.cSelection.hasSelection()) {
            this.doCopy();
            this.deleteSelection(false);
        }
    }
    
    void doCopy() {
        if (this.cSelection.hasSelection()) {
            final String[] cTypes = CRTEditor.cTypes;
            String text = null;
            final String[] array = new String[cTypes.length];
            for (int i = 0; i < cTypes.length; ++i) {
                array[i] = this.getText(cTypes[i], this.cSelection.getStart(), this.cSelection.getEnd());
                if (cTypes[i].equals("text/plain")) {
                    text = array[i];
                }
            }
            if (text == null) {
                text = this.getText("text/plain", this.cSelection.getStart(), this.cSelection.getEnd());
            }
            this.setClipboardContents(new CClipboardData(text, cTypes, array));
        }
    }
    
    void doPaste() {
        if (this.cEditable) {
            final Transferable clipboardContents = this.getClipboardContents();
            if (clipboardContents != null) {
                this.deleteSelection(false);
                final String[] cTypes = CRTEditor.cTypes;
                for (int i = 0; i < cTypes.length; ++i) {
                    final String flavor = this.getFlavor(clipboardContents, cTypes[i]);
                    if (flavor != null) {
                        final CParagraph cParagraph = new CParagraph(this.cInsPt.getStyle(), this);
                        this.importText(cTypes[i], flavor, new CTextPointer(cParagraph, 0));
                        this.cInsPt.insert(cParagraph);
                        this.dataChanged(1);
                        break;
                    }
                }
            }
        }
    }
    
    private Transferable getClipboardContents() {
        final LFCClipboard lfcClipboard = LFCClipboard.getLFCClipboard();
        if (lfcClipboard != null) {
            return lfcClipboard.getContents(this);
        }
        return null;
    }
    
    private void setClipboardContents(final Transferable transferable) {
        final LFCClipboard lfcClipboard = LFCClipboard.getLFCClipboard();
        if (lfcClipboard != null) {
            lfcClipboard.setContents(transferable, this);
        }
    }
    
    private String getFlavor(final Transferable transferable, final String s) {
        final DataFlavor tasteFlavor = this.tasteFlavor(transferable, s);
        if (tasteFlavor == null) {
            return null;
        }
        try {
            final Object transferData = transferable.getTransferData(tasteFlavor);
            if (transferData instanceof String) {
                return (String)transferData;
            }
            Reader reader;
            if (transferData instanceof InputStream) {
                reader = new UTF8InputStreamReader((InputStream)transferData);
            }
            else {
                reader = (Reader)transferData;
            }
            final StringBuffer sb = new StringBuffer(256);
            final char[] array = new char[256];
            while (true) {
                final int read = reader.read(array, 0, array.length);
                if (read == -1) {
                    break;
                }
                sb.append(array, 0, read);
            }
            return sb.toString();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private DataFlavor tasteFlavor(final Transferable transferable, final String s) {
        final DataFlavor[] transferDataFlavors = transferable.getTransferDataFlavors();
        for (int i = 0; i < transferDataFlavors.length; ++i) {
            final String mimeType = transferDataFlavors[i].getMimeType();
            int n = mimeType.indexOf(59);
            if (n == -1) {
                n = mimeType.length();
            }
            if (mimeType.regionMatches(0, s, 0, n)) {
                return transferDataFlavors[i];
            }
        }
        if (s.equals("text/plain") && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return DataFlavor.stringFlavor;
        }
        return null;
    }
    
    public void lostOwnership(final Clipboard clipboard, final Transferable transferable) {
    }
    
    boolean select(final int n, final int n2) {
        final CTextPointer tp = this.findTP(null, n);
        boolean b = false;
        if (tp != null) {
            this.move(tp, 0);
            final CTextPointer cTextPointer = (CTextPointer)tp.clone();
            cTextPointer.advanceByElement(n2);
            this.move(cTextPointer, 3);
            b = true;
        }
        return b;
    }
    
    boolean replace(final int n, final int n2, final String s) {
        boolean b = false;
        final CTextPointer tp = this.findTP(null, n);
        if (tp != null) {
            final CFormatInfo formatInfo = tp.getFormatInfo();
            tp.delete(n2);
            tp.insert(s, formatInfo);
            this.dataChanged(4);
            b = true;
        }
        return b;
    }
    
    public boolean insertLinkedText(final String s, final String url) {
        final CTextPointer position = this.cInsPt.getPosition();
        final CFormatInfo localInfo = this.cInsPt.getLocalInfo();
        boolean b = false;
        if (position != null) {
            localInfo.setURL(url);
            position.insert(s, localInfo);
            position.advance(s.length());
            this.move(position, 0);
            localInfo.setURL(null);
            this.cInsPt.setFormatInfo(localInfo);
            this.dataChanged(4);
            b = true;
        }
        return b;
    }
    
    private CTextPointer findTP(final CParagraph cParagraph, int n) {
        CTextPointer cTextPointer = null;
        CParagraph cFirstPara;
        if (cParagraph != null) {
            cFirstPara = cParagraph;
        }
        else {
            cFirstPara = this.cFirstPara;
        }
        int n2 = 0;
        int n3 = 0;
        while (cFirstPara != null && n3 == 0) {
            int dataLen = cFirstPara.getDataLen();
            final String s = new String(cFirstPara.getData());
            for (int i = s.indexOf(8232); i != -1; i = s.indexOf(8232, ++i)) {
                dataLen -= 3;
            }
            if (n <= n2 + dataLen) {
                n3 = 1;
            }
            else {
                n2 += dataLen;
                n -= 4;
                cFirstPara = (CParagraph)cFirstPara.getNext();
            }
        }
        if (n3 != 0) {
            int n4 = n - n2;
            for (int j = cFirstPara.findImageInRun(0, n4); j != -1; j = cFirstPara.findImageInRun(j + 3, n4)) {
                n4 += 3;
            }
            cTextPointer = new CTextPointer(cFirstPara, n4);
        }
        return cTextPointer;
    }
    
    static {
        CRTEditor.cTypes = new String[] { "text/html", "text/plain" };
    }
}
