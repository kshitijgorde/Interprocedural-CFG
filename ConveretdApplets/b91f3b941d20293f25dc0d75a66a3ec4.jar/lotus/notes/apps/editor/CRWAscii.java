// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

class CRWAscii
{
    private CTextPointer cPos;
    private CTextPointer cEnd;
    private CFormatInfo cInfo;
    
    CRWAscii(final CTextPointer cTextPointer, final CTextPointer cTextPointer2) {
        this.cPos = (CTextPointer)cTextPointer.clone();
        if (cTextPointer2 != null) {
            this.cEnd = (CTextPointer)cTextPointer2.clone();
        }
    }
    
    final CTextPointer getPosition() {
        return this.cPos;
    }
    
    final void setInfo(final CFormatInfo cInfo) {
        this.cInfo = cInfo;
    }
    
    public String getText(final boolean b) {
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        int i = 1;
        while (i != 0) {
            final char[] data = this.cPos.getData();
            int n2;
            if (this.cEnd != null && this.cPos.isSamePara(this.cEnd)) {
                n2 = this.cEnd.getOffset();
                i = 0;
            }
            else {
                n2 = this.cPos.getDataLen();
            }
            if (b) {
                final char bullet = this.cPos.getParaSettings().getBullet();
                if (bullet != '\0') {
                    if (bullet > '\u25a0') {
                        sb.append("   -  ");
                    }
                    else {
                        if (++n < 10) {
                            sb.append(" ");
                        }
                        sb.append("  " + n + ". ");
                    }
                }
                else {
                    n = 0;
                }
            }
            int j;
            int n3 = j = this.cPos.getOffset();
            while (j < n2) {
                if (data[j] == '\u0003') {
                    sb.append(data, n3, j - n3);
                    sb.append("\r\n");
                    n3 = ++j;
                }
                else if (data[j] == '\u2028') {
                    sb.append(data, n3, j - n3);
                    if (b) {
                        final CImage image = this.cPos.getPara().getImage(j);
                        if (image != null) {
                            final String altText = image.getAltText();
                            if (altText != null && altText.length() > 0) {
                                sb.append(" " + altText + " ");
                            }
                        }
                    }
                    j += 3;
                    n3 = j;
                }
                else if (data[j] == ' ') {
                    sb.append(data, n3, j - n3);
                    sb.append(" ");
                    n3 = ++j;
                }
                else {
                    ++j;
                }
            }
            sb.append(data, n3, n2 - n3);
            if (i != 0) {
                sb.append("\r\n\r\n");
            }
            if (!this.cPos.nextPara()) {
                break;
            }
        }
        return sb.toString();
    }
    
    public void setText(final String s) {
        final int length = s.length();
        int n;
        int i = n = 0;
        int n2 = 0;
        while (i < length) {
            if (n2 > 0) {
                if (n2 == 1) {
                    this.cPos.insertLineBreak(this.cInfo);
                }
                else {
                    this.cPos.splitPara(this.cInfo);
                }
                n2 = 0;
            }
            final char char1 = s.charAt(i);
            if (char1 < ' ') {
                this.cPos.insert(s, n, i - n, this.cInfo);
                ++i;
                if (char1 == '\r' || char1 == '\n') {
                    ++n2;
                    if (i < length) {
                        final char char2 = s.charAt(i);
                        if (char2 != char1 && (char2 == '\r' || char2 == '\n')) {
                            ++i;
                        }
                    }
                    if (i < length) {
                        final char char3 = s.charAt(i);
                        if (char3 == '\r' || char3 == '\n') {
                            ++n2;
                            if (++i < length) {
                                final char char4 = s.charAt(i);
                                if (char4 != char3 && (char4 == '\r' || char4 == '\n')) {
                                    ++i;
                                }
                            }
                        }
                    }
                }
                else if (char1 == '\t') {
                    this.cPos.insert(' ', this.cInfo);
                }
                n = i;
            }
            else {
                ++i;
            }
        }
        if (n < length) {
            this.cPos.insert(s, n, length - n, this.cInfo);
        }
    }
}
