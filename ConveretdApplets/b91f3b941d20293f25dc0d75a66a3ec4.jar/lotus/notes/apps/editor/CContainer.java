// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Locale;
import java.awt.Rectangle;
import java.awt.Graphics;

class CContainer
{
    private int cWidth;
    private int cHeight;
    private int cFlowHeight;
    private CLine cLine;
    private CLine cTopLine;
    private int cViewY;
    private CRTEditor cParent;
    
    CContainer(final CRTEditor cParent, final int cWidth, final int cHeight) {
        this.cParent = cParent;
        this.cWidth = cWidth;
        this.cHeight = cHeight;
    }
    
    Rectangle flow(final Graphics graphics, final CLine cLine, final int n, final CLine cLine2) {
        final Rectangle rectangle = null;
        final Locale activeLocale = this.cParent.getParent().getActiveLocale();
        CParagraph para = null;
        CParagraph cParagraph = null;
        CLine cLine3 = null;
        int n2 = 1;
        CLine cLine4 = null;
        int cFlowHeight = 0;
        CParagraph cParagraph2;
        if (cLine != null) {
            cParagraph2 = cLine.getStart().getPara();
            if (cLine2 != null) {
                final char bullet = cLine.getStart().getParaSettings().getBullet();
                if (bullet == '\0' || bullet > 'i') {
                    para = cLine2.getStart().getPara();
                }
            }
            final CLine firstLine = cParagraph2.getFirstLine();
            if (firstLine == this.cLine) {
                this.cLine = null;
            }
            else if (firstLine != null) {
                cLine4 = (CLine)firstLine.getPrevious();
            }
            if (cLine4 != null) {
                cParagraph = cLine4.getStart().getPara();
                cLine4.truncate();
            }
            if (!cParagraph2.hasImage() && cLine != cParagraph2.getFirstLine()) {
                cLine3 = (CLine)cLine.getPrevious();
                if (cLine3 != null) {
                    cParagraph = cLine3.getStart().getPara();
                    cLine3.truncate();
                }
            }
            if (cParagraph != null) {
                if (cParagraph.getNeedsClear()) {
                    cFlowHeight = cParagraph.getBottom();
                }
                else if (cLine3 != null) {
                    cFlowHeight = cLine3.getBottom();
                }
                else {
                    cFlowHeight = cLine4.getBottom();
                }
            }
        }
        else {
            this.cLine = null;
            cParagraph2 = this.cParent.getFirstPara();
        }
        this.cTopLine = null;
        this.cFlowHeight = 0;
        final int n3 = (this.cWidth > 0) ? this.cWidth : Integer.MAX_VALUE;
        while (cParagraph2 != null) {
            final int n4 = n3 - this.cParent.toPixels(cParagraph2.getParaSettings().getRightIndent());
            int n5;
            if (n2 == 1 || cParagraph2.getFirstLine() == null) {
                n5 = this.processPara(cParagraph2, cLine3, graphics, cFlowHeight, n4, activeLocale);
                if (cParagraph2 == para) {
                    n2 = 0;
                }
                cLine3 = null;
            }
            else {
                n5 = this.updatePara(cParagraph2, graphics, cFlowHeight, n4, activeLocale);
            }
            final CLine firstLine2 = cParagraph2.getFirstLine();
            final CLine lastLine = cParagraph2.getLastLine();
            lastLine.truncate();
            if (lastLine != null) {
                if (this.cLine == null) {
                    this.cLine = firstLine2;
                }
                if (cLine4 != null) {
                    cLine4.insertChain(firstLine2);
                }
                cLine4 = lastLine;
                for (CLine cLine5 = this.cLine; cLine5 != null; cLine5 = (CLine)cLine5.getNext()) {
                    if (cLine5 == cLine5.getNext()) {
                        cLine5.setNext(null);
                        System.out.println("!!!Looping in CRTEditor.processPara()!!!");
                    }
                }
            }
            cParagraph2 = (CParagraph)cParagraph2.getNext();
            cFlowHeight = n5;
        }
        this.cFlowHeight = cFlowHeight;
        return rectangle;
    }
    
    int updatePara(final CParagraph cParagraph, final Graphics graphics, final int n, final int n2, final Locale locale) {
        int adjustY = 0;
        CLine firstLine = cParagraph.getFirstLine();
        int max = n;
        if (firstLine != null) {
            final int n3 = n - firstLine.getY();
            final CTextPointer cTextPointer = (CTextPointer)firstLine.getStart().clone();
            final CTextPointer cTextPointer2 = (CTextPointer)cTextPointer.clone();
            firstLine.setPrevious(null);
            while (firstLine != null && cTextPointer2.isSamePara(cTextPointer) && adjustY != 2) {
                adjustY = firstLine.adjustY(cTextPointer, n3);
                max = Math.max(firstLine.getBottom(), max);
                firstLine = (CLine)firstLine.getNext();
            }
        }
        return max;
    }
    
    int processPara(final CParagraph cParagraph, final CLine cLine, final Graphics graphics, int max, final int n, final Locale locale) {
        final CTextPointer cTextPointer = new CTextPointer(cParagraph, 0);
        CRow cRow = new CRow(n);
        int n2 = 0;
        int i = 0;
        int n3 = 0;
        int n4 = n;
        int maxWidth = n;
        int max3;
        int max2;
        int n6;
        int n5 = n6 = (max2 = (max3 = 0));
        final int pixels = this.cParent.toPixels(cTextPointer.getParaSettings().getRestIndent());
        CDoubleLinkList lastLine;
        if (cLine == null) {
            n2 = (cTextPointer.isFirstPara() ? 0 : this.cParent.toPixels(cTextPointer.getParaSettings().getSpaceAbove()));
            cParagraph.setNeedsClear(false);
            lastLine = null;
        }
        else {
            lastLine = cLine;
            cTextPointer.init(cLine.getEnd());
            cTextPointer.advance(1);
        }
        while (i != 2) {
            final CLine cLine2 = new CLine(this);
            i = cLine2.flow(graphics, this.cParent, cTextPointer, max, n4, 0, n2, locale);
            if (i != 5) {
                cRow.insertLine(cLine2);
                n4 = cRow.getWidthRemain();
            }
            if (i != 3 && cRow.getStart() != null) {
                cRow.doLayout(n3 + pixels, max, maxWidth);
                this.processRow(cRow, n3 + pixels, n - n5);
                this.processRowHeights(cRow);
                n6 = (n3 = n6 + cRow.getLeftImgIndent());
                n5 += cRow.getRightImgIndent();
                max2 = Math.max(max2, cRow.getLeftMaxY());
                max3 = Math.max(max3, cRow.getRightMaxY());
                final int nextY = cRow.getNextY();
                if (nextY >= max2) {
                    n3 = 0;
                    n6 = 0;
                    max2 = 0;
                }
                if (nextY >= max3) {
                    n5 = 0;
                    max3 = 0;
                }
                maxWidth = (n4 = n - n6 - n5);
                max = nextY;
                if (lastLine == null) {
                    cRow.getStart();
                }
                else {
                    lastLine.insertChain(cRow.getStart());
                }
                lastLine = cParagraph.getLastLine();
                n2 = 0;
                cRow = (CRow)cRow.getNext();
                if (cRow == null) {
                    cRow = new CRow(n4);
                }
                else {
                    cRow.setMaxWidth(maxWidth);
                    n4 = cRow.getWidthRemain();
                    i = 0;
                }
            }
            else {
                final int n7 = i;
                final CLine cLine3 = this.cLine;
                if (n7 != 5 || cRow.getStart() != null) {
                    continue;
                }
                max = Math.max(max2, max3);
                max3 = (max2 = 0);
                n5 = (n6 = 0);
                maxWidth = n;
                n4 = n;
            }
        }
        final int max4 = Math.max(max2, max3);
        if (max4 > max) {
            max = max4;
            cParagraph.setNeedsClear(true);
        }
        return max;
    }
    
    void processRow(final CRow cRow, final int n, final int n2) {
        final CLine start = cRow.getStart();
        try {
            final int alignment = start.getStart().getParaSettings().getAlignment();
            if (alignment != 0) {
                final int n3 = n2 - cRow.getRightImgIndent();
                int n4 = n3 - cRow.getCurX();
                if (n4 <= 0) {
                    n4 = 0;
                }
                if (alignment == 1 && n4 > 0) {
                    n4 /= 2;
                }
                for (CLine end = cRow.getEnd(), cLine = (CLine)start.getPrevious(); end != null && end != cLine; end = (CLine)end.getPrevious()) {
                    boolean b = true;
                    if (end.getLeft() < n3) {
                        if (end.isImage() && this.cParent.getBehavior() == 0) {
                            final CImage image = end.getImage();
                            if (image != null) {
                                final int alignment2 = image.getAlignment();
                                if (alignment2 == 0 || alignment2 == 2) {
                                    b = false;
                                }
                            }
                        }
                        if (b) {
                            end.setX(end.getLeft() + n4);
                            if (end.getStart().isBOP()) {
                                end.setStartX(end.getStartX() + n4);
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Caught exception in ProcessRow() ");
            ex.printStackTrace();
        }
    }
    
    void processRowHeights(final CRow cRow) {
        try {
            final CLine start = cRow.getStart();
            final CLine end = cRow.getEnd();
            final CLine cLine = (CLine)end.getNext();
            CLine cLine2 = null;
            CLine cLine3 = null;
            CLine cLine4 = null;
            for (CLine cLine5 = start; cLine5 != null || cLine5 != cLine; cLine5 = (CLine)cLine5.getNext()) {
                if (cLine5.isImage()) {
                    cLine4 = cLine5;
                    break;
                }
            }
            if (cLine4 != null) {
                if (cLine4 == start) {
                    cLine2 = null;
                }
                else {
                    cLine2 = start;
                }
                cLine3 = (CLine)cLine4.getNext();
            }
            while (cLine4 != null) {
                final int alignment = cLine4.getImage().getAlignment();
                final int bottom = cLine4.getBottom();
                if (alignment == 5) {
                    if (cLine2 != null) {
                        do {
                            cLine2.setBaseline(bottom - (cLine2.getBottom() - cLine2.getBaseline()));
                            cLine2.setY(bottom - cLine2.getHeight());
                            cLine2 = (CLine)cLine2.getNext();
                        } while (cLine2 != cLine4);
                    }
                    while (cLine3 != null && !cLine3.isImage()) {
                        cLine3.setBaseline(bottom - (cLine3.getBottom() - cLine3.getBaseline()));
                        cLine3.setY(bottom - cLine3.getHeight());
                        cLine3 = (CLine)cLine3.getNext();
                    }
                }
                else if (alignment == 4) {
                    final int n = bottom - cLine4.getHeight() / 2;
                    if (cLine2 != null) {
                        do {
                            final int n2 = cLine2.getBaseline() - cLine2.getY();
                            if (n - n2 >= cLine2.getY()) {
                                cLine2.setBaseline(n);
                                cLine2.setY(n - n2);
                            }
                            cLine2 = (CLine)cLine2.getNext();
                        } while (cLine2 != cLine4);
                    }
                    while (cLine3 != null && !cLine3.isImage()) {
                        final int n3 = cLine3.getBaseline() - cLine3.getY();
                        if (n - n3 >= cLine3.getY()) {
                            cLine3.setBaseline(n);
                            cLine3.setY(n - n3);
                        }
                        cLine3 = (CLine)cLine3.getNext();
                    }
                }
                else {
                    while (cLine3 != null && !cLine3.isImage()) {
                        cLine3 = (CLine)cLine3.getNext();
                    }
                }
                if (cLine3 == null || cLine3 == end || cLine3.getStart().isAfter(end.getEnd())) {
                    cLine4 = null;
                }
                else {
                    cLine2 = null;
                    if (cLine3.isImage()) {
                        cLine4 = cLine3;
                        cLine3 = (CLine)cLine3.getNext();
                    }
                    else {
                        cLine4 = null;
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private int getMaxY(final CLine cLine) {
        int n = 0;
        for (CLine cLine2 = cLine; cLine2 != null; cLine2 = (CLine)cLine2.getNext()) {
            final int bottom = cLine2.getBottom();
            if (bottom > n) {
                n = bottom;
            }
        }
        return n;
    }
    
    void paint(final Graphics graphics, final Graphics graphics2) {
        graphics.translate(0, -this.cViewY);
        if (graphics != graphics2) {
            graphics2.translate(0, -this.cViewY);
        }
        final int cViewY = this.cViewY;
        if (this.cTopLine != null && (this.cTopLine.getY() > cViewY || this.cTopLine.getBottom() < cViewY)) {
            this.cTopLine = null;
        }
        if (this.cTopLine == null) {
            for (CLine cLine = this.cLine; cLine != null; cLine = (CLine)cLine.getNext()) {
                if (cLine.getBottom() >= cViewY) {
                    this.cTopLine = cLine;
                    break;
                }
            }
        }
        int n = cViewY + this.cHeight;
        final Rectangle clipBounds = graphics2.getClipBounds();
        CLine cLine2;
        if (clipBounds == null) {
            cLine2 = this.cTopLine;
        }
        else {
            final int n2 = clipBounds.y + clipBounds.height;
            if (n2 < n) {
                n = n2;
            }
            for (cLine2 = this.cTopLine; cLine2 != null; cLine2 = (CLine)cLine2.getNext()) {
                if (cLine2.getBottom() >= clipBounds.y) {
                    break;
                }
            }
        }
        while (cLine2 != null && cLine2.getY() < n) {
            cLine2.paint(graphics);
            cLine2 = (CLine)cLine2.getNext();
        }
        graphics.translate(0, cViewY);
        if (graphics != graphics2) {
            graphics2.translate(0, cViewY);
        }
    }
    
    void paintSelection(final CSelection cSelection, final Graphics graphics) {
        graphics.translate(0, -this.cViewY);
        cSelection.paint(graphics);
        graphics.translate(0, this.cViewY);
    }
    
    boolean makeVisible(final CInsertionPoint cInsertionPoint) {
        boolean b;
        if (this.cViewY != 0 && this.cFlowHeight <= this.cHeight) {
            this.cViewY = 0;
            b = true;
        }
        else {
            final CLine line = cInsertionPoint.getLine();
            b = (line != null && this.makeVisible(line.getY(), line.getHeight()));
        }
        return b;
    }
    
    boolean makeVisible(int cFlowHeight, final int n) {
        if (cFlowHeight < 0) {
            cFlowHeight = 0;
        }
        if (cFlowHeight < this.cViewY) {
            this.cViewY = ((cFlowHeight >= 0) ? cFlowHeight : 0);
            return true;
        }
        cFlowHeight += n;
        if (cFlowHeight > this.cFlowHeight) {
            cFlowHeight = this.cFlowHeight;
        }
        if (cFlowHeight > this.cViewY + this.cHeight) {
            this.cViewY = cFlowHeight - this.cHeight;
            return true;
        }
        return false;
    }
    
    boolean pageUp() {
        if (this.cViewY == 0) {
            return false;
        }
        this.cViewY -= this.cHeight;
        if (this.cViewY < 0) {
            this.cViewY = 0;
        }
        return true;
    }
    
    boolean pageDown() {
        if (this.cFlowHeight <= this.cHeight) {
            return false;
        }
        this.cViewY += this.cHeight;
        if (this.cViewY + this.cHeight > this.cFlowHeight) {
            this.cViewY = this.cFlowHeight - this.cHeight;
        }
        return true;
    }
    
    CTextPointer pointToTextPointer(final Graphics graphics, final Point point) {
        CTextPointer pointToTextPointer = null;
        final CLine pointToLine = this.pointToLine(graphics, point);
        if (pointToLine != null) {
            pointToTextPointer = pointToLine.pointToTextPointer(graphics, point);
        }
        return pointToTextPointer;
    }
    
    CLine pointToLine(final Graphics graphics, final Point point) {
        CLine cLine = null;
        int x = point.x;
        int y = point.y;
        if (y >= this.cFlowHeight) {
            x = this.cWidth - 1;
            y = this.cFlowHeight - 1;
        }
        if (y < 0) {
            y = (x = 0);
        }
        else if (x < 0) {
            x = 0;
        }
        point.x = x;
        point.y = y;
        CLine cLine2;
        CLine cLine3;
        for (cLine2 = this.cLine; cLine2 != null; cLine2 = cLine3) {
            cLine3 = (CLine)cLine2.getNext();
            final int bottom = cLine2.getBottom();
            if (y >= cLine2.getY() && y <= bottom) {
                if (cLine3 == null || (x >= cLine2.getLeft() && x <= cLine2.getRight())) {
                    break;
                }
                cLine = cLine2;
            }
            else {}
        }
        if (cLine2 == null && cLine != null) {
            cLine2 = cLine;
        }
        return cLine2;
    }
    
    final int getYOffset() {
        return this.cViewY;
    }
    
    final int getViewY() {
        return this.cViewY;
    }
    
    final int getWidth() {
        return this.cWidth;
    }
    
    final void setViewY(final int cViewY) {
        this.cViewY = cViewY;
    }
    
    final int getFlowHeight() {
        return this.cFlowHeight;
    }
    
    final CLine getFirstLine() {
        return this.cLine;
    }
    
    final int getHeight() {
        return this.cHeight;
    }
    
    final CRTEditor getParent() {
        return this.cParent;
    }
    
    Dimension minimumSize() {
        final Dimension dimension = new Dimension(0, this.cFlowHeight);
        int width = 0;
        for (CLine cLine = this.cLine; cLine != null; cLine = (CLine)cLine.getNext()) {
            final int width2 = cLine.getWidth();
            if (width2 > width) {
                width = width2;
            }
        }
        dimension.width = width;
        return dimension;
    }
    
    int[] getFirstLineOffsets() {
        final int[] array = new int[2];
        array[0] = (array[1] = 0);
        if (this.cTopLine != null) {
            final CParagraph para = this.cTopLine.getStart().getPara();
            CParagraph firstPara = this.cParent.getFirstPara();
            int n = 0;
            int n2 = 0;
            while (firstPara != null && n2 == 0) {
                if (firstPara == para) {
                    n2 = 1;
                }
                else {
                    ++n;
                    firstPara = (CParagraph)firstPara.getNext();
                }
            }
            if (n2 == 1) {
                array[0] = n;
                final CTextPointer pointToTextPointer = this.pointToTextPointer(this.cParent.getParent().getGraphics(), new Point(0, this.cViewY));
                if (pointToTextPointer != null) {
                    array[1] = pointToTextPointer.getOffset();
                }
            }
        }
        return array;
    }
    
    boolean setFirstLineByOffsets(final int[] array) {
        boolean b = false;
        CParagraph cParagraph = null;
        CParagraph firstPara = this.cParent.getFirstPara();
        for (int n = 0; n < array[0] && firstPara != null; firstPara = (CParagraph)firstPara.getNext(), ++n) {
            cParagraph = firstPara;
        }
        int n2;
        if (firstPara == null) {
            firstPara = cParagraph;
            n2 = firstPara.getDataLen();
        }
        else if (firstPara.getDataLen() > array[1]) {
            n2 = firstPara.getDataLen();
        }
        else {
            n2 = array[1];
        }
        final CTextPointer cTextPointer = new CTextPointer(firstPara, n2);
        CLine firstLine = firstPara.getFirstLine();
        int n3 = 0;
        while (firstLine != null && n3 == 0) {
            if (firstLine.isInLine(cTextPointer)) {
                n3 = 1;
            }
            else {
                firstLine = (CLine)firstLine.getNext();
            }
        }
        if (n3 != 0) {
            this.cViewY = firstLine.getY();
            b = true;
            this.cParent.recalibrate();
            this.cParent.getParent().repaint();
        }
        return b;
    }
}
