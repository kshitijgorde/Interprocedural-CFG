// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.FontMetrics;
import java.text.CharacterIterator;
import java.text.BreakIterator;
import java.util.Locale;
import java.awt.Graphics;

class CLine extends CDoubleLinkList
{
    private CContainer cParent;
    private CTextPointer cStart;
    private CTextPointer cEnd;
    private int cX;
    private int cY;
    private int cHeight;
    private int cBaseline;
    private int cWidth;
    private int cStartX;
    private char cBullet;
    private int cNumber;
    private boolean cChanged;
    private boolean cWrapped;
    private boolean cIsImage;
    private CImage cImage;
    static final int STATUS_OK = 0;
    static final int STATUS_LINEBREAK = 1;
    static final int STATUS_EOP = 2;
    static final int STATUS_EOL = 3;
    static final int STATUS_WRAP = 4;
    static final int STATUS_NOFIT = 5;
    static final String cUpperLetters = "IVXLCDM";
    static final String cLowerLetters = "ivxlcdm";
    static final int[] cTemplates;
    
    CLine(final CContainer cParent) {
        this.cParent = cParent;
        this.cStart = new CTextPointer();
        this.cEnd = new CTextPointer();
        this.cIsImage = false;
    }
    
    int flow(final Graphics graphics, final CRTEditor crtEditor, final CTextPointer cTextPointer, final int n, final int n2, final Locale locale) {
        return this.flow(graphics, crtEditor, cTextPointer, n, n2, 0, 0, locale);
    }
    
    int flow(final Graphics graphics, final CRTEditor crtEditor, final CTextPointer cTextPointer, final int n, final int n2, final int n3, final int n4, final Locale locale) {
        if (!this.cChanged && this.cStart.equals(cTextPointer)) {
            this.cBaseline += n - this.cY;
            this.cY = n;
            if (this.cEnd.isEOP()) {
                cTextPointer.nextPara();
                return 2;
            }
            cTextPointer.init(this.cEnd);
            return 0;
        }
        else {
            final CParaSettings paraSettings = cTextPointer.getParaSettings();
            if (cTextPointer.isImage()) {
                this.cImage = cTextPointer.getImage();
                if (this.cImage != null) {
                    this.cIsImage = true;
                }
            }
            final boolean b = false;
            this.cWrapped = b;
            this.cChanged = b;
            this.cStartX = 0;
            this.cY = n;
            this.cStart.init(cTextPointer);
            this.cEnd.init(cTextPointer);
            int pixels;
            if (n3 != 0) {
                pixels = n3;
            }
            else {
                pixels = crtEditor.toPixels(paraSettings.getRestIndent());
            }
            int placeBullet = pixels;
            if (this.cStart.isBOP()) {
                this.cStart.setFirstLine(this);
                this.cBullet = paraSettings.getBullet();
                if (this.cBullet != '\0') {
                    placeBullet = this.placeBullet(graphics, crtEditor, crtEditor.toPixels(paraSettings.getFirstIndent()), pixels);
                }
            }
            else {
                this.cBullet = '\0';
            }
            final int cWidth = n2 - placeBullet;
            this.cX = placeBullet;
            final CSizeInfo cSizeInfo = new CSizeInfo();
            final CSizeInfo cSizeInfo2 = new CSizeInfo();
            final BreakIterator lineInstance = BreakIterator.getLineInstance(locale);
            lineInstance.setText(new CDataIterator(this.cEnd));
            int n5 = this.flow(graphics, crtEditor, cWidth, cSizeInfo2, cSizeInfo, lineInstance);
            if (n5 == 5 || (n5 == 2 && cSizeInfo2.cWidth == 0)) {
                if (!this.cIsImage) {
                    final BreakIterator characterInstance = BreakIterator.getCharacterInstance(locale);
                    characterInstance.setText(new CDataIterator(this.cEnd));
                    n5 = this.flow(graphics, crtEditor, cWidth, cSizeInfo2, cSizeInfo, characterInstance);
                    if ((n5 == 5 && n3 == 0) || cSizeInfo2.cWidth == 0) {
                        this.cEnd.selectFont(graphics, crtEditor);
                        final FontMetrics fontMetrics = graphics.getFontMetrics();
                        if (!this.cEnd.isEmpty() && n5 != 2) {
                            cSizeInfo2.cWidth = fontMetrics.charWidth(this.cEnd.getChar());
                            if (cSizeInfo2.cWidth != 0) {
                                this.cX = n2 - cSizeInfo2.cWidth;
                            }
                            if (this.cX < 0) {
                                this.cX = 0;
                            }
                            n5 = (this.cEnd.isEOP() ? 2 : 0);
                            if (n5 != 2) {
                                this.cEnd.advanceByElement(1);
                            }
                        }
                        cSizeInfo2.cAscent = fontMetrics.getAscent();
                        cSizeInfo2.cDescent = fontMetrics.getDescent();
                    }
                }
                else {
                    if (n2 != this.cParent.getWidth() - crtEditor.toPixels(paraSettings.getRightIndent())) {
                        return 5;
                    }
                    cSizeInfo2.cWidth = cWidth;
                    cSizeInfo2.cAscent = cSizeInfo.cAscent;
                    cSizeInfo2.cDescent = cSizeInfo.cDescent;
                    n5 = (this.cEnd.isEOP() ? 2 : 0);
                    if (n5 != 2) {
                        this.cEnd.advanceByElement(1);
                    }
                }
            }
            if ((n5 == 4 || n5 == 3) && !this.isImage()) {
                this.cWrapped = true;
            }
            this.cWidth = cSizeInfo2.cWidth;
            this.cHeight = cSizeInfo2.getHeight() + n4;
            this.cBaseline = this.cY + this.cHeight - cSizeInfo2.cDescent;
            if (n5 == 2) {
                if (!cTextPointer.nextPara()) {
                    cTextPointer.init(this.cEnd);
                }
                return 2;
            }
            cTextPointer.init(this.cEnd);
            if (n5 == 1) {
                cTextPointer.advance(1);
            }
            return n5;
        }
    }
    
    int flow(final Graphics graphics, final CRTEditor crtEditor, final int n, final CSizeInfo cSizeInfo, final CSizeInfo cSizeInfo2, final BreakIterator breakIterator) {
        this.cEnd.getData();
        this.cEnd.getDataLen();
        int n2;
        do {
            final int offset = this.cEnd.getOffset();
            final int next = breakIterator.next();
            if (next == -1) {
                return 2;
            }
            if (this.cIsImage) {
                this.cWrapped = false;
                n2 = this.cEnd.getImageSizeInfo(cSizeInfo2, graphics, crtEditor, next);
            }
            else {
                n2 = this.cEnd.getSizeInfo(cSizeInfo2, graphics, crtEditor, next);
            }
            final int cWidth = cSizeInfo.cWidth + cSizeInfo2.cWidth;
            if (cWidth >= n) {
                if (this.cIsImage) {
                    return 5;
                }
                this.cEnd.init(offset);
                if (cSizeInfo.cWidth == 0) {
                    return 5;
                }
                return 4;
            }
            else {
                cSizeInfo.cWidth = cWidth;
                if (cSizeInfo2.cAscent > cSizeInfo.cAscent) {
                    cSizeInfo.cAscent = cSizeInfo2.cAscent;
                }
                if (cSizeInfo2.cDescent <= cSizeInfo.cDescent) {
                    continue;
                }
                cSizeInfo.cDescent = cSizeInfo2.cDescent;
            }
        } while (n2 != 1 && n2 != 3);
        return n2;
    }
    
    private int placeBullet(final Graphics graphics, final CRTEditor crtEditor, int n, final int cStartX) {
        if (this.cBullet <= 'i') {
            this.cNumber = 1;
            final CParagraph cParagraph = (CParagraph)this.cStart.getPara().getPrevious();
            if (cParagraph != null && cParagraph.getFirstLine() != null) {
                final int number = cParagraph.getFirstLine().getNumber();
                final CParaSettings paraSettings = cParagraph.getFirstLine().getStart().getParaSettings();
                final int firstIndent = paraSettings.getFirstIndent();
                final int firstIndent2 = this.cStart.getParaSettings().getFirstIndent();
                final char bullet = paraSettings.getBullet();
                if (bullet != '\0' && bullet <= 'i') {
                    if (firstIndent2 > firstIndent) {
                        this.cNumber = 1;
                    }
                    else if (firstIndent2 == firstIndent) {
                        this.cNumber = number + 1;
                    }
                    else {
                        this.cNumber = number - 1;
                        for (CParagraph cParagraph2 = (CParagraph)cParagraph.getPrevious(); cParagraph2 != null && cParagraph2.getFirstLine() != null; cParagraph2 = (CParagraph)cParagraph2.getPrevious()) {
                            if (cParagraph2.getFirstLine().getStart().getParaSettings().getFirstIndent() == firstIndent2) {
                                this.cNumber = cParagraph2.getFirstLine().getNumber() + 1;
                                break;
                            }
                        }
                    }
                }
            }
        }
        final int bulletSize = this.getBulletSize(graphics);
        this.cStart.selectFont(graphics, crtEditor);
        if (n < bulletSize) {
            n = bulletSize;
        }
        if (this.cBullet > 'i') {
            this.cStartX = n - (bulletSize << 1);
        }
        else {
            this.cStartX = n - bulletSize;
        }
        if (this.cStartX < 0) {
            this.cStartX = 0;
        }
        if (this.cStartX > cStartX) {
            this.cStartX = cStartX;
        }
        return n;
    }
    
    int adjustY(final CTextPointer cTextPointer, final int n) {
        int n2 = 0;
        this.cBaseline += n;
        this.cY += n;
        if (this.cEnd.isEOP()) {
            cTextPointer.nextPara();
            n2 = 2;
        }
        else {
            cTextPointer.init(this.cEnd);
        }
        return n2;
    }
    
    void paint(final Graphics graphics) {
        final CTextPointer cTextPointer = (CTextPointer)this.cStart.clone();
        final CRTEditor parent = this.cParent.getParent();
        if (cTextPointer.isBOP() && this.cBullet != '\0') {
            this.paintBullet(graphics, this.cBullet);
            cTextPointer.selectFont(graphics, parent);
        }
        int n = this.cX;
        final int cBaseline = this.cBaseline;
        if (this.cIsImage) {
            cTextPointer.paintImage(graphics, parent, n, cBaseline, this.cY);
        }
        else {
            while (cTextPointer.isBefore(this.cEnd)) {
                final int runDistance = cTextPointer.getRunDistance(this.cEnd);
                n = cTextPointer.paint(graphics, parent, n, cBaseline, runDistance, cTextPointer.getOffset() + runDistance == this.cEnd.getOffset());
                if (!cTextPointer.nextRunInPara()) {
                    break;
                }
            }
        }
    }
    
    private void paintBullet(final Graphics graphics, final char c) {
        final int bulletSize = this.getBulletSize(graphics);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int n = this.cBaseline - fontMetrics.getAscent() * 7 / 12;
        String s = null;
        switch (c) {
            default: {
                graphics.fillOval(this.cStartX, n, bulletSize, bulletSize);
            }
            case '\u25cb': {
                graphics.drawOval(this.cStartX, n, bulletSize, bulletSize);
                break;
            }
            case '\u25a0': {
                graphics.fillRect(this.cStartX, n, bulletSize, bulletSize);
                break;
            }
            case '1': {
                s = new Integer(this.cNumber).toString();
                break;
            }
            case 'A':
            case 'I':
            case 'a':
            case 'i': {
                s = this.calcBullet();
                break;
            }
        }
        if (s != null) {
            final String string = s + ". ";
            final int n2 = bulletSize - fontMetrics.stringWidth(string);
            int cStartX;
            if (n2 > 0) {
                cStartX = this.cStartX + n2;
            }
            else {
                cStartX = this.cStartX;
            }
            graphics.drawString(string, cStartX, this.cBaseline);
        }
    }
    
    private int getBulletSize(final Graphics graphics) {
        this.cStart.selectFont(graphics, this.cParent.getParent(), this.cStart.getFormatInfo());
        String s = null;
        switch (this.cBullet) {
            case '1': {
                s = "99. ";
                break;
            }
            case 'A':
            case 'a': {
                s = "AA. ";
                break;
            }
            case 'I':
            case 'i': {
                s = "IIII. ";
                break;
            }
            default: {
                return graphics.getFontMetrics().getAscent() / 2;
            }
        }
        return graphics.getFontMetrics().stringWidth(s);
    }
    
    private String calcBullet() {
        String s = null;
        final StringBuffer sb = new StringBuffer();
        int i = this.cNumber;
        switch (this.cBullet) {
            case 'A':
            case 'a': {
                final int n = (this.cBullet == 'A') ? 65 : 97;
                while (i > 0) {
                    --i;
                    sb.insert(0, (char)(i % 26 + n));
                    i /= 26;
                }
                s = sb.toString();
                break;
            }
            case 'I':
            case 'i': {
                final boolean b = this.cBullet == 'I';
                if (i < 0 || i > 3999) {
                    s = "";
                    break;
                }
                final String s2 = b ? "IVXLCDM" : "ivxlcdm";
                final StringBuffer sb2 = new StringBuffer();
                int n2 = -1;
                while (i != 0) {
                    for (int j = CLine.cTemplates[i % 10]; j != 0; j /= 8) {
                        sb2.insert(0, s2.charAt((j & 0x7) + n2));
                    }
                    n2 += 2;
                    i /= 10;
                }
                s = sb2.toString();
                break;
            }
        }
        return s;
    }
    
    boolean isInLine(final CTextPointer cTextPointer) {
        return cTextPointer.isSamePara(this.cStart) && cTextPointer.isGreaterOrEqual(this.cStart) && ((!this.cWrapped && cTextPointer.equals(this.cEnd)) || cTextPointer.isBefore(this.cEnd));
    }
    
    final boolean isImage() {
        return this.cIsImage;
    }
    
    final int getStartX() {
        return this.cStartX;
    }
    
    final int getY() {
        return this.cY;
    }
    
    final int getBaseline() {
        return this.cBaseline;
    }
    
    final int getHeight() {
        return this.cHeight;
    }
    
    final int getWidth() {
        return this.cWidth;
    }
    
    final int getLeft() {
        return this.cX;
    }
    
    final int getRight() {
        return this.cWidth + this.cX;
    }
    
    final int getInvalidRight() {
        return this.cWidth + this.cX + (this.cHeight >> 1);
    }
    
    final int getBottom() {
        return this.cY + this.cHeight;
    }
    
    final char getBullet() {
        return this.cBullet;
    }
    
    final int getNumber() {
        return this.cNumber;
    }
    
    final CImage getImage() {
        return this.cImage;
    }
    
    void setX(final int cx) {
        this.cX = cx;
    }
    
    void setStartX(final int cStartX) {
        this.cStartX = cStartX;
    }
    
    void setY(final int cy) {
        this.cY = cy;
    }
    
    void setBaseline(final int cBaseline) {
        this.cBaseline = cBaseline;
    }
    
    void setWidth(final int cWidth) {
        this.cWidth = cWidth;
    }
    
    void setHeight(final int cHeight) {
        this.cHeight = cHeight;
    }
    
    final CTextPointer getStart() {
        return this.cStart;
    }
    
    CTextPointer getEnd() {
        final CTextPointer cTextPointer = (CTextPointer)this.cEnd.clone();
        if (this.cWrapped) {
            cTextPointer.retreatByElement(1);
        }
        return cTextPointer;
    }
    
    int getEndOffset() {
        return this.cEnd.getOffset();
    }
    
    final CContainer getParent() {
        return this.cParent;
    }
    
    final boolean isChanged() {
        return this.cChanged;
    }
    
    final boolean isWrapped() {
        return this.cWrapped;
    }
    
    final boolean isEmpty() {
        return this.cStart.equals(this.cEnd);
    }
    
    void textPointerToPoint(final Graphics graphics, final CTextPointer cTextPointer, final Point point) {
        final CTextPointer cTextPointer2 = (CTextPointer)this.cStart.clone();
        final CRTEditor parent = this.cParent.getParent();
        int n = 0;
        while (cTextPointer2.isBefore(cTextPointer)) {
            final int runDistance = cTextPointer2.getRunDistance(cTextPointer);
            if (runDistance <= 0) {
                break;
            }
            n += cTextPointer2.getWidth(graphics, parent, runDistance);
            cTextPointer2.advance(runDistance);
        }
        if (n == 0) {
            cTextPointer2.selectFont(graphics, parent);
        }
        point.y = this.cBaseline - graphics.getFontMetrics().getAscent();
        point.x = this.cX + n;
    }
    
    CTextPointer pointToTextPointer(final Graphics graphics, final Point point) {
        if (point.x < this.cX) {
            return (CTextPointer)this.cStart.clone();
        }
        if (point.x >= this.cX + this.cWidth) {
            return this.getEnd();
        }
        if (this.isImage()) {
            return (CTextPointer)this.cStart.clone();
        }
        final Point point2 = new Point(this.cX, point.y);
        final CRTEditor parent = this.cParent.getParent();
        final CTextPointer cTextPointer = (CTextPointer)this.cStart.clone();
        final int x = point.x;
        while (cTextPointer.isBefore(this.cEnd)) {
            final int runDistance = cTextPointer.getRunDistance(this.cEnd);
            if (cTextPointer.findX(graphics, parent, x, point2, runDistance) || runDistance == 0) {
                break;
            }
        }
        return cTextPointer;
    }
    
    void changed(final CTextPointer cTextPointer, final CTextPointer cTextPointer2, final int n) {
        if (this.cEnd.isBefore(cTextPointer)) {
            return;
        }
        this.cStart.changed(cTextPointer, n, true);
        this.cEnd.changed(cTextPointer, n, false);
        if (this.isImage() && !this.cStart.isImage()) {
            this.cIsImage = false;
        }
        if (this.cStart.isAfter(cTextPointer2)) {
            return;
        }
        this.cChanged = true;
        this.getParent().getParent().addChange(this, cTextPointer.getOffset());
    }
    
    CLine checkBackflow(final int n) {
        if (!this.cStart.isBOP() && this.cStart.isInSameWord(n)) {
            final CLine cLine = (CLine)this.getPrevious();
            cLine.cChanged = true;
            return cLine;
        }
        return this;
    }
    
    void resetCoords(final Dimension dimension) {
        this.cWidth = dimension.width;
        this.cHeight = dimension.height;
        this.cBaseline = this.cY + this.cHeight;
    }
    
    static {
        cTemplates = new int[] { 0, 1, 9, 73, 10, 2, 17, 137, 1097, 11 };
    }
}
