// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;

class CSelection
{
    private CRTEditor cParent;
    private CTextPointer cStart;
    private CTextPointer cEnd;
    
    CSelection(final CRTEditor cParent) {
        this.cParent = cParent;
        this.cStart = new CTextPointer();
        this.cEnd = new CTextPointer();
    }
    
    final void setStart(final CTextPointer cTextPointer) {
        this.cStart.init(cTextPointer);
    }
    
    final void setEnd(final CTextPointer cTextPointer) {
        this.cEnd.init(cTextPointer);
    }
    
    void set(final CTextPointer cTextPointer) {
        this.cStart.init(cTextPointer);
        this.cEnd.init(cTextPointer);
    }
    
    final boolean hasSelection() {
        return this.cStart.isValid() && !this.cStart.equals(this.cEnd);
    }
    
    final boolean isImage() {
        return this.hasSelection() && this.cStart.isImage() && this.cEnd.getOffset() == this.cStart.getOffset() + 3;
    }
    
    void adjust() {
        if (this.hasSelection() && this.cStart.isAfter(this.cEnd)) {
            final CTextPointer cStart = this.cStart;
            this.cStart = this.cEnd;
            this.cEnd = cStart;
        }
    }
    
    final CTextPointer getStart() {
        return this.cStart;
    }
    
    final CTextPointer getEnd() {
        return this.cEnd;
    }
    
    void selectWord() {
        this.cStart.startOfWord();
        this.cEnd.endOfWord();
    }
    
    void apply(final CFormatInfo cFormatInfo) {
        this.cStart.applyRange(cFormatInfo, this.cEnd);
    }
    
    void apply(final CParaSettings cParaSettings) {
        final CTextPointer cTextPointer = (CTextPointer)this.cStart.clone();
        do {
            cTextPointer.apply(cParaSettings);
            if (cTextPointer.isSamePara(this.cEnd)) {
                break;
            }
        } while (cTextPointer.nextPara());
    }
    
    void setStyle(final CParaStyle style) {
        final CTextPointer cTextPointer = (CTextPointer)this.cStart.clone();
        do {
            cTextPointer.setStyle(style);
            if (cTextPointer.isSamePara(this.cEnd)) {
                break;
            }
        } while (cTextPointer.nextPara());
    }
    
    void delete() {
        this.cStart.deleteRange(this.cEnd);
    }
    
    void paint(final Graphics graphics) {
        final Point point = new Point(0, 0);
        CLine cLine = null;
        final Color color = graphics.getColor();
        CTextPointer end = (CTextPointer)this.cStart.clone();
        while (end.isBefore(this.cEnd)) {
            CLine firstLine = end.getPara().getFirstLine();
            int n = 0;
            while (firstLine != null && n == 0) {
                if (firstLine.isInLine(end) && firstLine != cLine) {
                    n = 1;
                }
                else {
                    firstLine = (CLine)firstLine.getNext();
                }
            }
            if (n == 0) {
                System.out.println("Couldn't find selection pos of " + end.getOffset());
                break;
            }
            firstLine.textPointerToPoint(graphics, end, point);
            final int x = point.x;
            int n2;
            if (this.cEnd.isBeforeOrEqual(firstLine.getEnd())) {
                firstLine.textPointerToPoint(graphics, this.cEnd, point);
                n2 = point.x;
                end = (CTextPointer)this.cEnd.clone();
            }
            else {
                n2 = firstLine.getRight();
                end = firstLine.getEnd();
                if (!firstLine.isImage() || (firstLine.isImage() && end.isEOP())) {
                    end.advance(1);
                }
                else {
                    cLine = firstLine;
                }
            }
            graphics.setColor(Color.black);
            graphics.fillRect(x, firstLine.getY(), n2 - x, firstLine.getHeight());
        }
        graphics.setColor(color);
    }
}
