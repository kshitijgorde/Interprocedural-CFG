// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Component;

class CInsertionPoint
{
    private CRTEditor cParent;
    private CTextPointer cPos;
    private CCaret cCaret;
    private Component cComponent;
    private CFormatInfo cInfo;
    private boolean cNeedInfo;
    private boolean cValid;
    private CLine cLine;
    private Point cPoint;
    private int cHeight;
    private int cTop;
    private int cBottom;
    
    CInsertionPoint(final CRTEditor cParent, final CParagraph cParagraph, final CCaret cCaret) {
        this.cParent = cParent;
        this.cComponent = cParent.getParent();
        this.cPos = new CTextPointer(cParagraph, 0);
        this.cCaret = cCaret;
        this.cPoint = new Point(0, 0);
        this.cInfo = new CFormatInfo();
        this.cNeedInfo = true;
    }
    
    void set(final CTextPointer cTextPointer) {
        this.set(cTextPointer, null, false);
    }
    
    void set(final CTextPointer cTextPointer, final CLine cLine, final boolean b) {
        this.invalidate();
        if (!cTextPointer.equals(this.cPos)) {
            this.cPos.init(cTextPointer);
            this.cNeedInfo = !b;
        }
        this.validate(cLine);
        synchronized (this.cCaret) {
            this.cCaret.hide();
            this.updateCaret();
            this.cCaret.show();
        }
    }
    
    final void updateCaret() {
        if (!this.cValid) {
            this.validate(null);
        }
        this.cCaret.setPosition(this.cPoint, this.cPoint.y + this.cHeight);
    }
    
    final CTextPointer getPosition() {
        return this.cPos;
    }
    
    final void needInfo() {
        this.cNeedInfo = true;
    }
    
    CFormatInfo getLocalInfo() {
        if (this.cNeedInfo) {
            CFormatInfo cFormatInfo;
            if (this.cPos.isEOP() && !this.cPos.isBOP()) {
                final CTextPointer cTextPointer = (CTextPointer)this.cPos.clone();
                cTextPointer.retreatByElement(1);
                cFormatInfo = cTextPointer.getLocalFormatInfo();
            }
            else {
                cFormatInfo = this.cPos.getLocalFormatInfo();
            }
            if (cFormatInfo == null) {
                this.cInfo.clear();
            }
            else {
                this.cInfo = (CFormatInfo)cFormatInfo.clone();
            }
            this.cNeedInfo = false;
        }
        return this.cInfo;
    }
    
    void splitPara() {
        this.cPos.splitPara(this.getLocalInfo());
        this.invalidate();
    }
    
    void insert(final char c) {
        this.cPos.insert(c, this.getLocalInfo());
        this.invalidate();
    }
    
    void insert(final String s, final int n, final int n2) {
        this.cPos.insert(s, n, n2, this.getLocalInfo());
        this.invalidate();
    }
    
    void insert(final CParagraph cParagraph) {
        final boolean b;
        if (b = !this.cPos.isEmpty()) {
            this.splitPara();
            this.cPos.prevPara();
            this.cPos.endOfPara();
        }
        this.cPos.insertChain(cParagraph);
        if (b) {
            this.cPos.combinePara();
        }
        this.invalidate();
        this.cNeedInfo = true;
    }
    
    void insertLineBreak() {
        this.cPos.insertLineBreak(this.getLocalInfo());
        this.invalidate();
    }
    
    boolean delete(final int n) {
        this.invalidate();
        this.cNeedInfo = true;
        return this.cPos.delete(n);
    }
    
    boolean deleteWord() {
        if (this.cPos.isBOS()) {
            return false;
        }
        final CTextPointer cTextPointer = (CTextPointer)this.cPos.clone();
        if (cTextPointer.isStartOfWord()) {
            if (!cTextPointer.prevWord()) {
                return false;
            }
        }
        else {
            cTextPointer.startOfWord();
        }
        cTextPointer.deleteRange(this.cPos);
        this.set(cTextPointer);
        return true;
    }
    
    CParaStyle getStyle() {
        return this.cPos.getStyle();
    }
    
    void setStyle(final CParaStyle style) {
        this.cPos.setStyle(style);
        this.invalidate();
        this.cNeedInfo = true;
    }
    
    void setFormatInfo(final CFormatInfo cInfo) {
        this.cInfo = cInfo;
    }
    
    void apply(final CFormatInfo cFormatInfo) {
        cFormatInfo.apply(this.getLocalInfo());
        this.cPos.applyRange(this.cInfo, this.cPos);
    }
    
    CFormatInfo getFormatInfo() {
        final CFormatInfo cFormatInfo = (CFormatInfo)this.cPos.getStyle().getFormatInfo().clone();
        this.getLocalInfo().apply(cFormatInfo);
        return cFormatInfo;
    }
    
    void getFormatInfo(final CFormatInfo cFormatInfo) {
        this.cPos.getStyle().getFormatInfo().duplicate(cFormatInfo);
        this.getLocalInfo().apply(cFormatInfo);
    }
    
    final void invalidate() {
        if (this.cValid) {
            this.cValid = false;
            this.cLine = null;
        }
    }
    
    private void validate(CLine firstLine) {
        if (!this.cValid) {
            final Graphics graphics = this.cComponent.getGraphics();
            if (graphics != null) {
                if (firstLine != null) {
                    this.validate(graphics, firstLine);
                }
                if (!this.cValid) {
                    for (firstLine = this.cPos.getFirstLine(); firstLine != null; firstLine = (CLine)firstLine.getNext()) {
                        if (this.validate(graphics, firstLine)) {
                            break;
                        }
                    }
                }
                graphics.dispose();
            }
        }
    }
    
    private boolean validate(final Graphics graphics, final CLine cLine) {
        if (!cLine.isInLine(this.cPos)) {
            return false;
        }
        cLine.textPointerToPoint(graphics, this.cPos, this.cPoint);
        if (!cLine.isImage()) {
            this.cPos.selectFont(graphics, this.cParent);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            this.cHeight = fontMetrics.getAscent() + fontMetrics.getDescent();
            this.cPoint.y = cLine.getBaseline() - fontMetrics.getAscent();
        }
        else {
            this.cHeight = cLine.getHeight();
            this.cPoint.y = cLine.getBaseline() - this.cHeight;
        }
        this.cLine = cLine;
        this.cTop = cLine.getY();
        this.cBottom = cLine.getBottom();
        return this.cValid = true;
    }
    
    CLine getLine() {
        if (!this.cValid) {
            this.validate(null);
        }
        return this.cLine;
    }
    
    Point getPoint() {
        if (!this.cValid) {
            this.validate(null);
        }
        return this.cPoint;
    }
    
    int getTop() {
        if (!this.cValid) {
            this.validate(null);
        }
        return this.cTop;
    }
    
    int getBottom() {
        if (!this.cValid) {
            this.validate(null);
        }
        return this.cBottom;
    }
}
