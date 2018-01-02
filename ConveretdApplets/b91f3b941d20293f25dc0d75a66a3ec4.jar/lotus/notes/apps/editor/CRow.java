// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

class CRow extends CDoubleLinkList
{
    CLine cStartLine;
    CLine cEndLine;
    int cX;
    int cMaxWidth;
    int cNextY;
    int cLftImgIndent;
    int cRgtImgIndent;
    int cLftMaxY;
    int cRgtMaxY;
    boolean bHasImage;
    boolean bHasText;
    
    public CRow(final int cMaxWidth) {
        final CLine cLine = null;
        this.cEndLine = cLine;
        this.cStartLine = cLine;
        this.cX = 0;
        this.cMaxWidth = cMaxWidth;
        this.bHasImage = false;
    }
    
    void setMaxWidth(final int cMaxWidth) {
        this.cMaxWidth = cMaxWidth;
    }
    
    CLine getStart() {
        return this.cStartLine;
    }
    
    CLine getEnd() {
        return this.cEndLine;
    }
    
    int getCurX() {
        return this.cX;
    }
    
    int getNextY() {
        return this.cNextY;
    }
    
    int getLeftMaxY() {
        return this.cLftMaxY;
    }
    
    int getRightMaxY() {
        return this.cRgtMaxY;
    }
    
    int getLeftImgIndent() {
        return this.cLftImgIndent;
    }
    
    int getRightImgIndent() {
        return this.cRgtImgIndent;
    }
    
    int getWidthRemain() {
        return this.cMaxWidth - this.cX;
    }
    
    int insertLine(final CLine cLine) {
        if (cLine.isImage()) {
            final CImage image = cLine.getImage();
            if (image == null) {
                return this.cX;
            }
            final int alignment = image.getAlignment();
            if (alignment == 0 || alignment == 2) {
                if (this.bHasText) {
                    CRow cRow = (CRow)this.getNext();
                    if (cRow == null) {
                        cRow = new CRow(this.cMaxWidth);
                        this.insert(cRow);
                    }
                    cRow.insertLine(cLine);
                    return this.cX;
                }
                this.cX += 5;
            }
        }
        else {
            this.bHasText = true;
        }
        if (this.cStartLine == null) {
            this.cEndLine = cLine;
            this.cStartLine = cLine;
        }
        else {
            this.cEndLine.insert(cLine);
            this.cEndLine = (CLine)cLine.getLast();
        }
        return this.cX += this.cEndLine.getWidth();
    }
    
    boolean doLayout(final int cx, final int n, final int n2) {
        final boolean b = false;
        this.cRgtImgIndent = (b ? 1 : 0);
        this.cLftImgIndent = (b ? 1 : 0);
        final boolean b2 = false;
        this.cRgtMaxY = (b2 ? 1 : 0);
        this.cLftMaxY = (b2 ? 1 : 0);
        this.cNextY = n;
        this.cX = cx;
        int left = cx + n2;
        if (this.cStartLine != null) {
            for (CLine cStartLine = this.cStartLine; cStartLine != this.cEndLine.getNext(); cStartLine = (CLine)cStartLine.getNext()) {
                int alignment = 0;
                int n3 = 0;
                boolean b3 = false;
                final int width = cStartLine.getWidth();
                cStartLine.getHeight();
                if (cStartLine.isImage()) {
                    final CImage image = cStartLine.getImage();
                    if (image != null) {
                        alignment = image.getAlignment();
                    }
                    if (cStartLine.getY() != n) {
                        cStartLine.setY(n);
                        cStartLine.setBaseline(n + cStartLine.getHeight());
                    }
                    if (alignment == 0) {
                        n3 = 5;
                        this.cLftImgIndent += width + n3;
                        this.cLftMaxY = Math.max(this.cLftMaxY, cStartLine.getBottom());
                    }
                    else if (alignment != 2) {
                        this.cNextY = Math.max(this.cNextY, cStartLine.getBottom());
                    }
                    else {
                        this.cRgtImgIndent += width + n3;
                        this.cRgtMaxY = Math.max(this.cRgtMaxY, cStartLine.getBottom());
                        final int left2 = cStartLine.getLeft();
                        final int startX = cStartLine.getStartX();
                        int n4 = 0;
                        if (cStartLine.getBullet() != '\0') {
                            n4 = left2 - startX;
                        }
                        int x = left - width;
                        if (x < 0) {
                            x = 0;
                        }
                        cStartLine.setX(x);
                        cStartLine.setStartX(cStartLine.getLeft() - n4);
                        left = cStartLine.getLeft();
                        if (left < 0) {
                            this.cX = 0;
                        }
                        b3 = true;
                    }
                }
                if (!b3) {
                    final int left3 = cStartLine.getLeft();
                    final int startX2 = cStartLine.getStartX();
                    int n5 = 0;
                    if (cStartLine.getBullet() != '\0') {
                        n5 = left3 - startX2;
                    }
                    if (this.cX >= left3) {
                        cStartLine.setX(this.cX);
                        cStartLine.setStartX(this.cX - n5);
                    }
                    this.cX += width + n3;
                }
                if (!cStartLine.isImage()) {
                    this.cNextY = Math.max(this.cNextY, cStartLine.getBottom());
                }
            }
            if (!this.bHasText) {
                this.cNextY = Math.max(this.cNextY, Math.max(this.cLftMaxY, this.cRgtMaxY));
            }
        }
        return true;
    }
}
