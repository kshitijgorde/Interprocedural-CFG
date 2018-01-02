// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;

class CTextPointer implements Cloneable
{
    private CParagraph cPara;
    private int cOffset;
    private long cParaChange;
    private CParaFormatList cInfo;
    private int cRemain;
    private boolean cSelected;
    private CFormatInfo cFormat;
    static String whitespace;
    static String breaks;
    
    CTextPointer(final CParagraph cPara, final int cOffset) {
        this.cPara = cPara;
        this.cOffset = cOffset;
    }
    
    CTextPointer() {
    }
    
    void init(final CParagraph cPara, final int cOffset) {
        this.cPara = cPara;
        this.cOffset = cOffset;
        this.invalidate();
    }
    
    void init(final int cOffset) {
        this.cOffset = cOffset;
        this.invalidate();
    }
    
    public Object clone() {
        try {
            final CTextPointer cTextPointer = (CTextPointer)super.clone();
            cTextPointer.cSelected = false;
            return cTextPointer;
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    private final boolean isWhitespace(final char c) {
        return Character.isWhitespace(c);
    }
    
    private final boolean isBreak(final char c) {
        return CTextPointer.breaks.indexOf(c) != -1;
    }
    
    private final boolean isAlpha(final char c) {
        boolean b = false;
        if (!this.isBreak(c) && !this.isWhitespace(c) && c >= '0') {
            b = true;
        }
        return b;
    }
    
    private final boolean infoValid() {
        return this.cParaChange >= this.cPara.getChangeCount();
    }
    
    private void findInfo() {
        this.cFormat = null;
        final CParaFormatList nearestParaFormat = this.cPara.getNearestParaFormat(this.cOffset);
        if (nearestParaFormat != null) {
            final int offset = nearestParaFormat.getOffset();
            if (this.cOffset >= offset) {
                this.cInfo = nearestParaFormat;
                this.cRemain = offset + nearestParaFormat.getLength() - this.cOffset;
            }
            else {
                this.cInfo = null;
                this.cRemain = offset - this.cOffset;
            }
        }
        else {
            this.cInfo = null;
            this.cRemain = this.cPara.getDataLen() - this.cOffset;
        }
        this.cParaChange = this.cPara.getChangeCount();
    }
    
    CFormatInfo getFormatInfo() {
        if (this.cFormat == null || !this.infoValid()) {
            if (!this.infoValid()) {
                this.findInfo();
            }
            final CFormatInfo cFormat = (CFormatInfo)this.cPara.getStyle().getFormatInfo().clone();
            if (this.cInfo != null) {
                this.cInfo.getFormatInfo().apply(cFormat);
            }
            this.cFormat = cFormat;
        }
        return this.cFormat;
    }
    
    CFormatInfo getLocalFormatInfo() {
        if (!this.infoValid()) {
            this.findInfo();
        }
        return (this.cInfo != null) ? this.cInfo.getFormatInfo() : null;
    }
    
    CParaSettings getParaSettings() {
        final CParaSettings cParaSettings = (CParaSettings)this.cPara.getStyle().getParaSettings().clone();
        this.cPara.getParaSettings().apply(cParaSettings);
        return cParaSettings;
    }
    
    void getParaSettings(final CParaSettings cParaSettings) {
        this.cPara.getStyle().getParaSettings().duplicate(cParaSettings);
        this.cPara.getParaSettings().apply(cParaSettings);
    }
    
    int getRunLength() {
        if (!this.infoValid()) {
            this.findInfo();
        }
        return this.cRemain;
    }
    
    int getRunDistance(final CTextPointer cTextPointer) {
        final int runLength = this.getRunLength();
        if (this.cPara == cTextPointer.cPara && this.cOffset + runLength > cTextPointer.cOffset) {
            return cTextPointer.cOffset - this.cOffset;
        }
        return runLength;
    }
    
    void init(final CTextPointer cTextPointer) {
        this.cPara = cTextPointer.cPara;
        this.cOffset = cTextPointer.cOffset;
        this.invalidate();
    }
    
    final char[] getData() {
        return this.cPara.getData();
    }
    
    final CParagraph getPara() {
        return this.cPara;
    }
    
    final int getOffset() {
        return this.cOffset;
    }
    
    final boolean isValid() {
        return this.cPara != null;
    }
    
    final int getDataLen() {
        return this.cPara.getDataLen();
    }
    
    boolean isEmpty() {
        return this.cPara.getDataLen() == 0;
    }
    
    boolean isBOP() {
        return this.cOffset == 0;
    }
    
    boolean isEOP() {
        return this.cOffset == this.cPara.getDataLen();
    }
    
    boolean isBOS() {
        return this.cOffset == 0 && this.cPara.getPrevious() == null;
    }
    
    boolean isFirstPara() {
        return this.cPara.getPrevious() == null;
    }
    
    boolean isLastPara() {
        return this.cPara.getNext() == null;
    }
    
    boolean isImage() {
        return this.isImage(this.cOffset);
    }
    
    boolean isImage(int n) {
        boolean b = false;
        final String property = System.getProperty("java.vendor");
        System.getProperty("java.version");
        if (property != null && (property.indexOf("Sun Microsystems") != -1 || property.indexOf("IBM") != -1)) {
            --n;
        }
        if (n >= 0 && n != this.cPara.getDataLen() && this.cPara.getData()[n] == '\u2028') {
            b = true;
        }
        return b;
    }
    
    boolean isImageRun(final int n) {
        boolean image = this.isImage(n);
        if (!image) {
            final char[] data = this.cPara.getData();
            if ((n > 0 && data[n - 1] == '\u2028') || (n > 1 && data[this.cOffset - 2] == '\u2028')) {
                image = true;
            }
        }
        return image;
    }
    
    boolean isImagePara() {
        return this.cPara.hasImage();
    }
    
    CImage getImage() {
        return this.cPara.getImage(this.cOffset);
    }
    
    boolean equals(final CTextPointer cTextPointer) {
        return this.cPara == cTextPointer.cPara && this.cOffset == cTextPointer.cOffset;
    }
    
    boolean isSamePara(final CTextPointer cTextPointer) {
        return this.cPara == cTextPointer.cPara;
    }
    
    boolean isNextPara(final CTextPointer cTextPointer) {
        return this.cPara == cTextPointer.cPara.getNext();
    }
    
    boolean isBefore(final CTextPointer cTextPointer) {
        if (this.cPara == cTextPointer.cPara) {
            return this.cOffset < cTextPointer.cOffset;
        }
        return !this.cPara.isAfter(cTextPointer.cPara);
    }
    
    boolean isBeforeOrEqual(final CTextPointer cTextPointer) {
        if (this.cPara == cTextPointer.cPara) {
            return this.cOffset <= cTextPointer.cOffset;
        }
        return !this.cPara.isAfter(cTextPointer.cPara);
    }
    
    boolean isAfter(final CTextPointer cTextPointer) {
        if (this.cPara == cTextPointer.cPara) {
            return this.cOffset > cTextPointer.cOffset;
        }
        return this.cPara.isAfter(cTextPointer.cPara);
    }
    
    boolean isGreaterOrEqual(final CTextPointer cTextPointer) {
        if (this.cPara == cTextPointer.cPara) {
            return this.cOffset >= cTextPointer.cOffset;
        }
        return this.cPara.isAfter(cTextPointer.cPara);
    }
    
    void endOfPara() {
        this.cOffset = this.cPara.getDataLen();
        this.invalidate();
    }
    
    void endOfRun() {
        this.cOffset += this.getRunLength();
        this.invalidate();
    }
    
    void endOfStream() {
        CParagraph cPara;
        CParagraph cParagraph;
        for (cPara = this.cPara; cPara != null; cPara = cParagraph) {
            cParagraph = (CParagraph)cPara.getNext();
            if (cParagraph == null) {
                break;
            }
        }
        this.init(cPara, cPara.getDataLen());
    }
    
    boolean advanceByElement(final int n) {
        final CDataIterator cDataIterator = new CDataIterator(this);
        int n2;
        for (n2 = n; n2 > 0 && !this.isEOP(); --n2) {
            cDataIterator.next();
            this.cOffset = cDataIterator.getIndex();
        }
        boolean advanceByElement;
        if (n2 > 0) {
            --n2;
            if (!this.nextPara()) {
                this.endOfPara();
                advanceByElement = false;
            }
            else {
                advanceByElement = this.advanceByElement(n2);
            }
        }
        else {
            advanceByElement = true;
        }
        return advanceByElement;
    }
    
    boolean advance(final int n) {
        final int dataLen = this.cPara.getDataLen();
        if (this.cOffset + n <= dataLen) {
            this.cOffset += n;
            if (this.infoValid()) {
                this.cRemain -= n;
                if (this.cRemain <= 0) {
                    this.invalidate();
                }
            }
            return true;
        }
        final int n2 = dataLen - (this.cOffset + n - 1);
        if (!this.nextPara()) {
            this.endOfPara();
            return false;
        }
        return n2 == 0 || this.advance(n2);
    }
    
    boolean retreatByElement(final int n) {
        final CDataIterator cDataIterator = new CDataIterator(this, 0);
        int n2;
        for (n2 = n; n2 > 0 && !this.isBOP(); --n2) {
            cDataIterator.previous();
            this.cOffset = cDataIterator.getIndex();
        }
        boolean retreatByElement;
        if (n2 > 0) {
            --n2;
            if (!this.prevPara()) {
                retreatByElement = false;
            }
            else {
                this.endOfPara();
                retreatByElement = this.retreatByElement(n2);
            }
        }
        else {
            this.invalidate();
            retreatByElement = true;
        }
        return retreatByElement;
    }
    
    boolean retreat(final int n) {
        if (n <= this.cOffset) {
            this.cOffset -= n;
            this.invalidate();
            return true;
        }
        final int n2 = n - this.cOffset - 1;
        if (!this.prevPara()) {
            return false;
        }
        this.endOfPara();
        return n2 == 0 || this.retreat(n2);
    }
    
    private final void invalidate() {
        this.cParaChange = 0L;
        this.cFormat = null;
        this.cSelected = false;
    }
    
    boolean nextRunInPara() {
        final int dataLen = this.cPara.getDataLen();
        final int runLength = this.getRunLength();
        if (runLength == 0) {
            return false;
        }
        if (this.cOffset + runLength == dataLen) {
            this.endOfPara();
            return true;
        }
        return this.advance(runLength);
    }
    
    boolean nextRun() {
        return this.nextRunInPara() || this.nextPara();
    }
    
    boolean nextPara() {
        final CParagraph cParagraph = (CParagraph)this.cPara.getNext();
        if (cParagraph != null) {
            this.init(cParagraph, 0);
            return true;
        }
        return false;
    }
    
    boolean prevRun() {
        if (this.prevRunInPara()) {
            return true;
        }
        if (!this.prevPara()) {
            return false;
        }
        this.endOfPara();
        this.prevRunInPara();
        return true;
    }
    
    boolean prevRunInPara() {
        CParaFormatList list = null;
        CParaFormatList list2 = this.cPara.getNearestParaFormat(this.cOffset);
        if (list2 == null) {
            list2 = this.cPara.getFormatList();
            if (list2 != null) {
                list2 = (CParaFormatList)list2.getLast();
            }
        }
        else {
            if (list2.isFor(this.cOffset)) {
                list = list2;
            }
            while (list2 != null) {
                if (list2.getEnd() <= this.cOffset) {
                    break;
                }
                list2 = (CParaFormatList)list2.getPrevious();
            }
        }
        if (list2 != null) {
            if (list != null) {
                if (list2.getEnd() == list.getOffset()) {
                    this.init(list2.getOffset());
                }
                else {
                    this.init(list2.getEnd());
                }
            }
            else {
                this.init(list2.getOffset());
            }
        }
        else {
            if (list == null || list.getOffset() <= 0) {
                return false;
            }
            this.init(0);
        }
        return true;
    }
    
    boolean prevPara() {
        final CParagraph cParagraph = (CParagraph)this.cPara.getPrevious();
        if (cParagraph != null) {
            this.init(cParagraph, 0);
            return true;
        }
        return false;
    }
    
    void insertLineBreak(final CFormatInfo cFormatInfo) {
        this.insert('\u0003', cFormatInfo);
    }
    
    void insert(final String s, final CFormatInfo cFormatInfo) {
        this.insert(s, 0, s.length(), cFormatInfo);
    }
    
    void insert(final String s, final int n, final int n2, CFormatInfo cFormatInfo) {
        if (cFormatInfo != null && !cFormatInfo.isAnythingOverridden()) {
            cFormatInfo = null;
        }
        this.cPara.insert(this.cOffset, s, n, n2, cFormatInfo);
        this.cOffset += n2;
        this.invalidate();
    }
    
    void insert(final char c, CFormatInfo cFormatInfo) {
        if (cFormatInfo != null && !cFormatInfo.isAnythingOverridden()) {
            cFormatInfo = null;
        }
        this.cPara.insert(this.cOffset, c, cFormatInfo);
        ++this.cOffset;
        this.invalidate();
    }
    
    void insertPara() {
        final CParagraph cParagraph = new CParagraph(this.cPara.getStyle());
        this.cPara.insert(cParagraph);
        this.init(cParagraph, 0);
    }
    
    void insertParaBefore() {
        final CParagraph cParagraph = new CParagraph(this.cPara.getStyle());
        this.cPara.insertBefore(cParagraph);
        this.init(cParagraph, 0);
    }
    
    void insertChain(final CParagraph cParagraph) {
        CParagraph cPara = (CParagraph)this.cPara.insertChain(cParagraph);
        if (cPara == this.cPara.getNext()) {
            cPara = this.cPara;
        }
        this.combinePara();
        this.init(cPara, cPara.getDataLen());
    }
    
    void selectFont(final Graphics graphics, final CRTEditor crtEditor) {
        if (this.cSelected && this.infoValid() && this.cRemain > 0) {
            return;
        }
        this.selectFont(graphics, crtEditor, this.getFormatInfo());
        this.cSelected = true;
    }
    
    void selectFont(final Graphics graphics, final CRTEditor crtEditor, final CFormatInfo cFormatInfo) {
        graphics.setFont(CFontCache.getFont(cFormatInfo));
        if (cFormatInfo.getURL() != null) {
            graphics.setColor(crtEditor.getLinkColor());
        }
        else {
            graphics.setColor(cFormatInfo.getColor());
        }
    }
    
    int paint(final Graphics graphics, final CRTEditor crtEditor, final int n, final int n2, final int n3, final boolean b) {
        int n4 = n;
        final Color color = graphics.getColor();
        final char[] data = this.cPara.getData();
        final CFormatInfo formatInfo = this.getFormatInfo();
        this.selectFont(graphics, crtEditor);
        graphics.drawChars(data, this.cOffset, n3, n, n2);
        if (!b || formatInfo.isDifficultToPaint()) {
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            n4 = n + fontMetrics.charsWidth(data, this.cOffset, n3);
            if (formatInfo.isStrikeThrough()) {
                final int n5 = n2 - fontMetrics.getAscent() / 3;
                graphics.drawLine(n, n5, n4, n5);
            }
            if (formatInfo.isUnderline() || formatInfo.getURL() != null) {
                final int n6 = n2 + 1;
                graphics.drawLine(n, n6, n4, n6);
            }
        }
        graphics.setColor(color);
        return n4;
    }
    
    int paintImage(final Graphics graphics, final CRTEditor crtEditor, final int n, final int n2, final int n3) {
        int n4 = n;
        int n5 = n3;
        final CImage image = this.cPara.getImage(this.cOffset);
        if (image != null) {
            final int height;
            if ((height = image.getHeight()) != -1) {
                n5 = n2 - height;
            }
            graphics.drawImage(image.getImage(), n, n5, crtEditor.getParent());
            if (crtEditor.getBehavior() == 1) {
                final int browser = crtEditor.getBrowser();
                if (browser > 0 && browser < 3) {
                    final String imageURLStr = image.getImageURLStr();
                    if (imageURLStr.indexOf("&Animation=") != -1 && imageURLStr.indexOf("&Animation=NONE") == -1) {
                        final int width = image.getWidth();
                        final String s = "&TextString=";
                        final String s2 = " (animated)";
                        final int index = imageURLStr.indexOf(s);
                        String s3;
                        if (index != -1) {
                            final int n6 = index + s.length();
                            int n7 = imageURLStr.indexOf(38, n6);
                            if (n7 == -1) {
                                n7 = imageURLStr.length();
                            }
                            s3 = imageURLStr.substring(n6, n7);
                            final String s4 = "%20";
                            String s5;
                            String substring;
                            for (int i = s3.indexOf(s4); i != -1; i += 3, s3 = substring + " " + s5.substring(i), i = s3.indexOf(s4, i)) {
                                s5 = s3;
                                substring = s5.substring(0, i);
                            }
                        }
                        else {
                            s3 = "";
                        }
                        graphics.setColor(Color.black);
                        graphics.drawRect(n, n5, width, height);
                        final CFormatInfo cFormatInfo = new CFormatInfo();
                        cFormatInfo.setFaceName("Helvetica");
                        cFormatInfo.setPointSize(10);
                        graphics.setFont(CFontCache.getFont(cFormatInfo));
                        String s6 = s3 + s2;
                        final FontMetrics fontMetrics = graphics.getFontMetrics();
                        if (fontMetrics.stringWidth(s6) > width) {
                            final String s7 = " ...";
                            s6 = s3 + s7;
                            for (int n8 = fontMetrics.stringWidth(s6); n8 > width && s6.length() > 1; s6 = s3 + s7, n8 = fontMetrics.stringWidth(s6)) {
                                s3 = s3.substring(0, s3.length() - 1);
                            }
                        }
                        graphics.drawString(s6, n + 5, n2 - 5);
                    }
                }
            }
            n4 = n + image.getWidth();
        }
        return n4;
    }
    
    boolean findX(final Graphics graphics, final CRTEditor crtEditor, final int n, final Point point, final int n2) {
        boolean b = false;
        int n3 = 0;
        this.selectFont(graphics, crtEditor);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int[] widths = fontMetrics.getWidths();
        final char[] data = this.cPara.getData();
        final int n4 = this.cOffset + n2;
        int x = point.x;
        int i;
        for (i = this.cOffset; i < n4; ++i) {
            final char c = data[i];
            int width;
            if (c == '\u0001') {
                width = this.getWidth(graphics, crtEditor, 1);
                n3 = 3;
            }
            else {
                width = ((c < '\u0100') ? widths[c] : fontMetrics.charWidth(c));
            }
            if (x + width / 2 >= n) {
                b = true;
                break;
            }
            x += width;
            if (n3 > 0) {
                i += n3;
                n3 = 0;
            }
        }
        this.cOffset = i;
        point.x = x;
        this.invalidate();
        return b;
    }
    
    int getWidth(final Graphics graphics, final CRTEditor crtEditor, int i) {
        int n = 0;
        int cOffset = this.cOffset;
        while (i > 0) {
            this.selectFont(graphics, crtEditor);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            if (this.isImage(cOffset)) {
                final CImage image = this.cPara.getImage(this.cOffset);
                if (image != null) {
                    n += image.getWidth();
                }
                if (i >= 3) {
                    i -= 3;
                }
                else {
                    i = 0;
                }
                cOffset += 3;
            }
            else {
                final int imageInRun = this.cPara.findImageInRun(cOffset, i);
                if (imageInRun == -1) {
                    n += fontMetrics.charsWidth(this.cPara.getData(), cOffset, i);
                    i = 0;
                }
                else {
                    final int n2 = imageInRun - cOffset;
                    n += fontMetrics.charsWidth(this.cPara.getData(), cOffset, n2);
                    i = -n2;
                    cOffset = imageInRun;
                }
            }
        }
        return n;
    }
    
    boolean isStartOfWord() {
        boolean b = false;
        if (this.cOffset == 0 || this.isEOP() || this.isImage()) {
            b = true;
        }
        else {
            final char[] data = this.cPara.getData();
            final char c = data[this.cOffset];
            final char c2 = data[this.cOffset - 1];
            if (this.isAlpha(c) && (this.isImageRun(this.cOffset - 1) || !this.isAlpha(c2))) {
                b = true;
            }
            else if (this.isBreak(c) && !this.isBreak(c2)) {
                b = true;
            }
        }
        return b;
    }
    
    boolean isInWord() {
        boolean b = false;
        if (!this.isBOP() && !this.isEOP()) {
            final char[] data = this.cPara.getData();
            final char c = data[this.cOffset];
            if (this.isAlpha(c)) {
                b = true;
            }
            else if (this.isBreak(c) && ((this.cOffset > 0 && this.isBreak(data[this.cOffset - 1])) || (this.cOffset + 1 < this.cPara.getDataLen() && this.isBreak(data[this.cOffset + 1])))) {
                b = true;
            }
        }
        return b;
    }
    
    void startOfWord() {
        int startOfWord = 0;
        this.cPara.getData();
        while (this.cOffset > 0 && startOfWord == 0) {
            if (this.isImageRun(this.cOffset)) {
                while (!this.isImage(this.cOffset) && this.cOffset >= 0) {
                    --this.cOffset;
                }
                startOfWord = 1;
            }
            else {
                if ((startOfWord = (this.isStartOfWord() ? 1 : 0)) != 0) {
                    continue;
                }
                --this.cOffset;
            }
        }
        this.invalidate();
    }
    
    void endOfWord() {
        boolean startOfWord = false;
        while (this.cOffset < this.cPara.getDataLen() && !startOfWord) {
            if (this.isImage()) {
                this.cOffset += 3;
                break;
            }
            if (startOfWord = this.isStartOfWord()) {
                continue;
            }
            ++this.cOffset;
        }
        this.invalidate();
    }
    
    boolean prevWord() {
        if (this.isBOP()) {
            if (!this.prevPara()) {
                return false;
            }
            this.endOfPara();
        }
        if (this.isStartOfWord()) {
            --this.cOffset;
        }
        this.startOfWord();
        return true;
    }
    
    boolean nextWord() {
        if (this.isEOP()) {
            return this.nextPara();
        }
        if (!this.isImage()) {
            this.endOfWord();
        }
        final int dataLen = this.cPara.getDataLen();
        if (this.isStartOfWord() && this.cOffset < dataLen) {
            ++this.cOffset;
        }
        while (this.cOffset < dataLen && !this.isStartOfWord()) {
            ++this.cOffset;
        }
        return true;
    }
    
    void applyRange(final CFormatInfo cFormatInfo, final CTextPointer cTextPointer) {
        final CParagraph cPara = cTextPointer.cPara;
        for (CParagraph cPara2 = this.cPara; cPara2 != null; cPara2 = (CParagraph)cPara2.getNext()) {
            int i;
            CParaFormatList list;
            if (cPara2 == this.cPara) {
                i = this.cOffset;
                list = cPara2.getNearestParaFormat(i);
            }
            else {
                i = 0;
                list = cPara2.getFormatList();
            }
            final int n = (cPara2 == cPara) ? cTextPointer.cOffset : cPara2.getDataLen();
            while (i < n) {
                if (list != null && list.isFor(i)) {
                    if (list.isFor(n)) {
                        cPara2.splitParaFormat(list, n);
                    }
                    if (list.getOffset() < i) {
                        list = cPara2.splitParaFormat(list, i);
                    }
                    cPara2.apply(i, cFormatInfo, list);
                    i += list.getLength();
                }
                else {
                    int n2;
                    if (list != null) {
                        if (n < list.getOffset()) {
                            n2 = n - i;
                        }
                        else {
                            n2 = list.getOffset() - i;
                        }
                    }
                    else {
                        n2 = n - i;
                    }
                    list = cPara2.insertParaFormat(cFormatInfo, i, n2);
                    i += n2;
                }
                list = (CParaFormatList)list.getNext();
            }
            if (cPara2.getDataLen() == 0) {
                if (list == null) {
                    cPara2.insertParaFormat(cFormatInfo, 0, 0);
                }
                else {
                    cPara2.apply(0, cFormatInfo, list);
                }
            }
            cPara2.fixParaFormatList();
            if (cPara2 == cPara) {
                break;
            }
        }
        this.invalidate();
    }
    
    void apply(final CParaSettings cParaSettings) {
        this.cPara.apply(cParaSettings);
        this.cPara.fixParaSettings();
    }
    
    void splitPara(final CFormatInfo cFormatInfo) {
        final CParagraph cParagraph = new CParagraph(this.cPara.getStyle());
        this.cPara.insert(cParagraph);
        this.cPara.splitPara(this.cOffset, cParagraph, cFormatInfo);
        this.init(cParagraph, 0);
    }
    
    boolean combinePara() {
        final CParagraph cParagraph = (CParagraph)this.cPara.getNext();
        if (cParagraph == null) {
            return false;
        }
        this.cPara.combinePara(cParagraph);
        return true;
    }
    
    boolean delete(int i) {
        boolean b = false;
        while (i > 0) {
            if (this.isEOP()) {
                if (!this.combinePara()) {
                    break;
                }
                b = true;
                if (--i == 0) {
                    break;
                }
            }
            final int n = this.cPara.getDataLen() - this.cOffset;
            final int n2 = (i > n) ? n : i;
            this.cPara.delete(this.cOffset, n2);
            b = true;
            i -= n2;
        }
        return b;
    }
    
    void deleteRange(final CTextPointer cTextPointer) {
        CParagraph cParagraph;
        for (CParagraph cPara = this.cPara; cPara != null; cPara = cParagraph) {
            cParagraph = (CParagraph)cPara.getNext();
            if (cPara == this.cPara) {
                if (cPara == cTextPointer.cPara) {
                    cPara.delete(this.cOffset, cTextPointer.cOffset - this.cOffset);
                    break;
                }
                cPara.delete(this.cOffset, cPara.getDataLen() - this.cOffset);
            }
            else {
                if (cPara == cTextPointer.cPara) {
                    cPara.delete(0, cTextPointer.cOffset);
                    break;
                }
                cPara.remove();
            }
        }
        if (this.cPara != cTextPointer.cPara) {
            this.cPara.combinePara(cTextPointer.cPara);
        }
        this.invalidate();
        cTextPointer.init(this);
    }
    
    CParaStyle getStyle() {
        return this.cPara.getStyle();
    }
    
    void setStyle(final CParaStyle style) {
        this.cPara.setStyle(style);
        this.invalidate();
    }
    
    final void setFirstLine(final CLine firstLine) {
        this.cPara.setFirstLine(firstLine);
    }
    
    final CLine getFirstLine() {
        return this.cPara.getFirstLine();
    }
    
    boolean isInSameWord(final int n) {
        final int dataLen = this.cPara.getDataLen();
        this.cPara.getData();
        for (int i = this.cOffset; i < dataLen; ++i) {
            if (i >= n) {
                return true;
            }
            if (this.isStartOfWord()) {
                return false;
            }
        }
        return true;
    }
    
    void changed(final CTextPointer cTextPointer, final int n, final boolean b) {
        if (this.cOffset < cTextPointer.cOffset || n == 0) {
            return;
        }
        if (n > 0) {
            if (!b || this.cOffset >= cTextPointer.cOffset + n) {
                this.cOffset += n;
            }
        }
        else if (this.cOffset < cTextPointer.cOffset - n) {
            this.cOffset = cTextPointer.cOffset;
        }
        else {
            this.cOffset += n;
        }
    }
    
    int getSizeInfo(final CSizeInfo cSizeInfo, final Graphics graphics, final CRTEditor crtEditor, int n) {
        int n2 = 0;
        cSizeInfo.cWidth = 0;
        final boolean b = false;
        cSizeInfo.cDescent = (b ? 1 : 0);
        cSizeInfo.cAscent = (b ? 1 : 0);
        final char[] data = this.cPara.getData();
        if (n > this.cOffset && data[n - 1] == '\u0003') {
            n2 = 1;
            --n;
        }
        else if (this.isImage(n)) {
            n2 = 3;
        }
        while (this.cOffset < n) {
            final int runLength = this.getRunLength();
            final int n3 = (this.cOffset + runLength > n) ? (n - this.cOffset) : runLength;
            this.selectFont(graphics, crtEditor);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int ascent = fontMetrics.getAscent();
            final int descent = fontMetrics.getDescent();
            if (ascent > cSizeInfo.cAscent) {
                cSizeInfo.cAscent = ascent;
            }
            if (descent > cSizeInfo.cDescent) {
                cSizeInfo.cDescent = descent;
            }
            cSizeInfo.cWidth += fontMetrics.charsWidth(data, this.cOffset, n3);
            this.cOffset += n3;
            this.invalidate();
        }
        return n2;
    }
    
    int getImageSizeInfo(final CSizeInfo cSizeInfo, final Graphics graphics, final CRTEditor crtEditor, final int cOffset) {
        final CImage image = this.cPara.getImage(this.cOffset);
        int n = 0;
        if (image != null) {
            final int width = image.getWidth();
            final int height = image.getHeight();
            if (width != -1 && height != -1) {
                cSizeInfo.cWidth += width;
                cSizeInfo.cAscent += height;
            }
        }
        if (this.getDataLen() > cOffset) {
            n = 3;
        }
        this.cOffset = cOffset;
        return n;
    }
    
    final char getChar() {
        return this.cPara.getData()[this.cOffset];
    }
    
    CLine getLine() {
        for (CLine firstLine = this.cPara.getFirstLine(); firstLine != null; firstLine = (CLine)firstLine.getNext()) {
            if (firstLine.isInLine(this)) {
                return firstLine;
            }
        }
        return null;
    }
    
    void startOfURL(final String s) {
        while (s.equals(this.getFormatInfo().getURL())) {
            if (!this.prevRun()) {
                return;
            }
        }
        this.endOfRun();
    }
    
    void endOfURL(final String s) {
        while (s.equals(this.getFormatInfo().getURL())) {
            if (!this.nextRun()) {
                return;
            }
            if (!this.isEOP()) {
                continue;
            }
            this.nextPara();
        }
    }
    
    static {
        CTextPointer.whitespace = new String(" \r\n\t ,\u3000");
        CTextPointer.breaks = new String("\"~`!@#$%^&*()_+-={}[]|\\/?.,<>:;',\u3001,\u3002,\u3003");
    }
}
