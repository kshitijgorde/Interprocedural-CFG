// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Dimension;

class CParagraph extends CDoubleLinkList implements Cloneable
{
    private char[] cData;
    private int cDataLen;
    private int cAllocLen;
    private CParaFormatList cFormatList;
    private CParaSettings cParaSettings;
    private CParaStyle cStyle;
    private long cChangeCount;
    private CLine cLine;
    private static CRTEditor cEditor;
    private static int GROWSIZE;
    private boolean cNeedsClear;
    
    CParagraph(final CParaStyle cParaStyle, final CRTEditor cEditor) {
        this(cParaStyle);
        CParagraph.cEditor = cEditor;
    }
    
    CParagraph(final CParaStyle cStyle) {
        this.cData = new char[CParagraph.GROWSIZE];
        this.cAllocLen = CParagraph.GROWSIZE;
        this.cParaSettings = new CParaSettings();
        this.cStyle = cStyle;
        this.cChangeCount = 1L;
    }
    
    CParagraph(final char[] array, final int cDataLen, final CParaSettings cParaSettings, final CParaFormatList cFormatList, final CParaStyle cStyle) {
        if (array.length > 0) {
            this.cAllocLen = array.length;
        }
        else {
            this.cAllocLen = CParagraph.GROWSIZE;
        }
        this.cData = new char[this.cAllocLen];
        for (int i = 0; i < cDataLen; ++i) {
            this.cData[i] = array[i];
        }
        this.cDataLen = cDataLen;
        this.cParaSettings = cParaSettings;
        this.cFormatList = cFormatList;
        this.cStyle = cStyle;
        this.cChangeCount = 1L;
    }
    
    public Object clone() {
        CParaSettings cParaSettings = null;
        CParaFormatList list = null;
        CParagraph cParagraph;
        try {
            if (this.cParaSettings != null) {
                cParaSettings = (CParaSettings)this.cParaSettings.clone();
            }
            if (this.cFormatList != null) {
                list = (CParaFormatList)this.cFormatList.clone();
            }
            cParagraph = new CParagraph(this.getData(), this.getDataLen(), cParaSettings, list, this.cStyle);
            final CParagraph cParagraph2 = (CParagraph)this.getNext();
            if (cParagraph2 != null) {
                cParagraph.insertChain((CDoubleLinkList)cParagraph2.clone());
            }
        }
        catch (Exception ex) {
            throw new InternalError();
        }
        return cParagraph;
    }
    
    public boolean getNeedsClear() {
        return this.cNeedsClear;
    }
    
    public void setNeedsClear(final boolean cNeedsClear) {
        this.cNeedsClear = cNeedsClear;
    }
    
    final CParaStyle getStyle() {
        return this.cStyle;
    }
    
    final CParaSettings getParaSettings() {
        return this.cParaSettings;
    }
    
    final void setStyle(final CParaStyle cStyle) {
        this.cStyle = cStyle;
        this.cStyle.getParaSettings().apply(this.cParaSettings);
        ++this.cChangeCount;
        this.changed(0, this.cDataLen, 0);
    }
    
    final long getChangeCount() {
        return this.cChangeCount;
    }
    
    final void setFirstLine(final CLine cLine) {
        this.cLine = cLine;
    }
    
    final CLine getFirstLine() {
        return this.cLine;
    }
    
    CLine getLastLine() {
        CLine cLine = this.cLine;
        if (cLine != null) {
            for (CLine cLine2 = (CLine)cLine.getNext(); cLine2 != null && cLine.getStart().isSamePara(cLine2.getStart()); cLine = cLine2, cLine2 = (CLine)cLine.getNext()) {}
        }
        return cLine;
    }
    
    int getBottom() {
        int n = 0;
        if (this.hasImage()) {
            CLine cLine = this.cLine;
            if (cLine != null) {
                n = cLine.getBottom();
                for (CLine cLine2 = (CLine)cLine.getNext(); cLine2 != null && cLine.getStart().isSamePara(cLine2.getStart()); cLine = cLine2, n = Math.max(n, cLine.getBottom()), cLine2 = (CLine)cLine.getNext()) {}
            }
        }
        else {
            final CLine lastLine = this.getLastLine();
            if (lastLine != null) {
                n = lastLine.getBottom();
            }
        }
        return n;
    }
    
    CImage getImage(int n) {
        CImage imageData = null;
        final String property = System.getProperty("java.vendor");
        System.getProperty("java.version");
        if (property != null && (property.indexOf("Sun Microsystems") != -1 || property.indexOf("IBM") != -1)) {
            --n;
        }
        if (this.cData[n] == '\u2028') {
            imageData = CParagraph.cEditor.cEscMgr.getImageData(this.cData[n + 2]);
        }
        return imageData;
    }
    
    void resetCoords(final CFormatInfo cFormatInfo) {
        final CRTEditor parent = this.cLine.getParent().getParent();
        final Graphics graphics = parent.getParent().getGraphics();
        final Dimension dimension = new Dimension();
        graphics.setFont(CFontCache.getFont(cFormatInfo));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        dimension.width = 0;
        dimension.height = fontMetrics.getAscent();
        this.cLine.resetCoords(dimension);
        parent.imageChanged();
    }
    
    boolean hasImage() {
        return new String(this.cData).indexOf(8232) != -1;
    }
    
    boolean isSingleImagePara() {
        boolean b = false;
        if (this.cDataLen > 0) {
            b = true;
            for (int i = 0; i < this.cDataLen; i += 3) {
                if (this.cData[i] != '\u2028') {
                    b = false;
                    break;
                }
            }
        }
        return b;
    }
    
    int findImageInRun(final int n, final int n2) {
        int cDataLen = n + n2;
        if (cDataLen > this.cDataLen) {
            cDataLen = this.cDataLen;
        }
        for (int i = n; i < cDataLen; ++i) {
            if (this.cData[i] == '\u2028') {
                return i;
            }
        }
        return -1;
    }
    
    void insert(final int n, final String s, final CFormatInfo cFormatInfo) {
        this.insert(n, s, 0, s.length(), cFormatInfo);
    }
    
    void insert(final int n, final String s, final int n2, final int n3, final CFormatInfo cFormatInfo) {
        if (n3 == 0) {
            return;
        }
        final CParaFormatList paraFormat = this.getParaFormat(n);
        boolean b;
        if (paraFormat != null) {
            b = (cFormatInfo != null && paraFormat.getFormatInfo().equals(cFormatInfo));
            if (!b && paraFormat.getLength() != 0 && paraFormat.getOffset() < n) {
                this.splitParaFormat(paraFormat, n);
            }
        }
        else {
            b = (cFormatInfo == null);
        }
        this.growData(n3);
        this.createRoom(n, n3);
        for (int i = 0; i < n3; ++i) {
            this.cData[n + i] = s.charAt(n2 + i);
        }
        this.adjustDataLen(n, n3, b);
        if (!b && cFormatInfo != null) {
            this.insertParaFormat(cFormatInfo, n, n3);
        }
        this.changed(n, n3, n3);
        ++this.cChangeCount;
    }
    
    void insert(final int n, final char c, final CFormatInfo cFormatInfo) {
        final CParaFormatList paraFormat = this.getParaFormat(n);
        boolean b;
        if (paraFormat != null) {
            b = (cFormatInfo != null && paraFormat.getFormatInfo().equals(cFormatInfo));
            if (!b && paraFormat.getLength() != 0 && paraFormat.getOffset() < n) {
                this.splitParaFormat(paraFormat, n);
            }
        }
        else {
            b = (cFormatInfo == null);
        }
        this.growData(1);
        this.createRoom(n, 1);
        this.cData[n] = c;
        this.adjustDataLen(n, 1, b);
        if (!b && cFormatInfo != null) {
            this.insertParaFormat(cFormatInfo, n, 1);
        }
        this.fixParaFormatList();
        this.changed(n, 1, 1);
        ++this.cChangeCount;
    }
    
    CParaFormatList insertParaFormat(final CFormatInfo cFormatInfo, final int n, final int n2) {
        final CParaFormatList cFormatList = new CParaFormatList(n, n2, (CFormatInfo)cFormatInfo.clone());
        if (this.cFormatList == null || n < this.cFormatList.getOffset()) {
            if (this.cFormatList != null) {
                this.cFormatList.insertBefore(cFormatList);
            }
            this.cFormatList = cFormatList;
        }
        else {
            CParaFormatList list;
            for (CParaFormatList cFormatList2 = this.cFormatList; cFormatList2 != null; cFormatList2 = list) {
                list = (CParaFormatList)cFormatList2.getNext();
                if (list == null || list.getOffset() > n) {
                    cFormatList2.insert(cFormatList);
                    break;
                }
            }
        }
        this.changed(n, n2, 0);
        ++this.cChangeCount;
        return cFormatList;
    }
    
    CParaFormatList splitParaFormat(final CParaFormatList list, final int offset) {
        CParaFormatList list2 = null;
        if (list != null) {
            list2 = new CParaFormatList(list.getOffset(), list.getLength(), (CFormatInfo)list.getFormatInfo().clone());
        }
        final int length = list.getLength();
        final int length2 = offset - list.getOffset();
        list.setLength(length2);
        list2.setOffset(offset);
        list2.setLength(length - length2);
        list.insert(list2);
        this.changed(offset, length, 0);
        ++this.cChangeCount;
        return list2;
    }
    
    private void adjustDataLen(final int offset, int n, final boolean b) {
        final boolean b2 = n < 0;
        CParaFormatList nearestParaFormat = this.getNearestParaFormat(offset);
        this.cDataLen += n;
        if (b2) {
            n = -n;
        }
        while (nearestParaFormat != null) {
            final int offset2 = nearestParaFormat.getOffset();
            final int length = nearestParaFormat.getLength();
            boolean b3 = true;
            if (b2) {
                if (offset > offset2) {
                    if (offset < offset2 + length) {
                        if (offset + n < offset2 + length) {
                            nearestParaFormat.setLength(length - n);
                        }
                        else {
                            nearestParaFormat.setLength(offset - offset2);
                        }
                    }
                }
                else if (offset + n > offset2) {
                    if (offset + n >= offset2 + length) {
                        b3 = false;
                        final CParaFormatList cFormatList = (CParaFormatList)nearestParaFormat.getNext();
                        if (this.cFormatList == nearestParaFormat) {
                            this.cFormatList = cFormatList;
                        }
                        nearestParaFormat.remove();
                        nearestParaFormat = cFormatList;
                    }
                    else {
                        nearestParaFormat.setLength(offset2 + length - (offset + n));
                        nearestParaFormat.setOffset(offset);
                    }
                }
                else {
                    nearestParaFormat.setOffset(offset2 - n);
                }
            }
            else if (offset > offset2 || (b && offset == offset2)) {
                if (offset <= offset2 + length) {
                    nearestParaFormat.setLength(length + n);
                }
            }
            else if (length > 0) {
                nearestParaFormat.setOffset(offset2 + n);
            }
            if (b3) {
                nearestParaFormat = (CParaFormatList)nearestParaFormat.getNext();
            }
        }
    }
    
    private void createRoom(final int n, final int n2) {
        System.arraycopy(this.cData, n, this.cData, n + n2, this.cDataLen - n);
    }
    
    private void growData(final int n) {
        if (this.cDataLen + n > this.cAllocLen) {
            final int cAllocLen = this.cDataLen + n + CParagraph.GROWSIZE;
            final char[] cData = new char[cAllocLen];
            System.arraycopy(this.cData, 0, cData, 0, this.cDataLen);
            this.cAllocLen = cAllocLen;
            this.cData = cData;
        }
    }
    
    boolean delete(final int n, int n2) {
        final int dataLen = this.getDataLen();
        if (n < dataLen) {
            if (n + n2 > dataLen) {
                n2 = dataLen - n;
            }
            if (this.cData[n] == '\u2028') {
                if (n2 > 0 && n2 < 3) {
                    n2 = 3;
                }
                final CImage imageData = CParagraph.cEditor.cEscMgr.getImageData(this.cData[n + 2]);
                if (imageData != null) {
                    imageData.release();
                }
            }
            System.arraycopy(this.cData, n + n2, this.cData, n, dataLen - (n + n2));
            this.adjustDataLen(n, -n2, false);
            this.changed(n, n2, -n2);
            ++this.cChangeCount;
            return true;
        }
        return false;
    }
    
    final char[] getData() {
        return this.cData;
    }
    
    final int getDataLen() {
        return this.cDataLen;
    }
    
    final CParaFormatList getFormatList() {
        return this.cFormatList;
    }
    
    CParaFormatList getNearestParaFormat(final int n) {
        if (this.cDataLen == 0 && n == 0) {
            return this.cFormatList;
        }
        for (CParaFormatList cFormatList = this.cFormatList; cFormatList != null; cFormatList = (CParaFormatList)cFormatList.getNext()) {
            final int offset = cFormatList.getOffset();
            if (offset > n) {
                return cFormatList;
            }
            if (n < offset + cFormatList.getLength()) {
                return cFormatList;
            }
        }
        return null;
    }
    
    CParaFormatList getParaFormat(final int n) {
        CParaFormatList cFormatList;
        for (cFormatList = this.cFormatList; cFormatList != null && !cFormatList.isFor(n); cFormatList = (CParaFormatList)cFormatList.getNext()) {}
        return cFormatList;
    }
    
    void splitPara(final int cDataLen, final CParagraph cParagraph, final CFormatInfo cFormatInfo) {
        final int n = this.cDataLen - cDataLen;
        if (n > 0) {
            cParagraph.insert(0, new String(this.cData, cDataLen, n), null);
            this.cDataLen = cDataLen;
            CParaFormatList cFormatList = this.getNearestParaFormat(cDataLen);
            if (cFormatList != null && cDataLen > cFormatList.getOffset() && cFormatList.isFor(cDataLen)) {
                cFormatList = this.splitParaFormat(cFormatList, cDataLen);
            }
            if (cFormatList != null) {
                final CParaFormatList list = (CParaFormatList)cFormatList.getPrevious();
                if (list != null) {
                    list.truncate();
                }
                else {
                    this.cFormatList = null;
                }
                for (CParaFormatList list2 = cFormatList; list2 != null; list2 = (CParaFormatList)list2.getNext()) {
                    list2.setOffset(list2.getOffset() - cDataLen);
                }
                if (cParagraph.cFormatList == null) {
                    cParagraph.cFormatList = cFormatList;
                }
                else {
                    cParagraph.cFormatList.addToEnd(cFormatList);
                }
            }
        }
        if (this.cDataLen == 0 && cFormatInfo != null) {
            this.insertParaFormat(cFormatInfo, 0, 0);
            this.fixParaFormatList();
        }
        if (cParagraph.getDataLen() == 0 && cFormatInfo != null) {
            cParagraph.insertParaFormat(cFormatInfo, 0, 0);
        }
        this.cParaSettings.apply(cParagraph.cParaSettings);
        this.changed(cDataLen, n, -n);
        ++this.cChangeCount;
    }
    
    void combinePara(final CParagraph cParagraph) {
        final int cDataLen = this.cDataLen;
        if (this.cDataLen == 0) {
            this.cStyle = cParagraph.cStyle;
        }
        if (cParagraph.getDataLen() == 0) {
            this.changed(this.getDataLen(), 0, 0);
        }
        else {
            this.insert(cDataLen, new String(cParagraph.getData(), 0, cParagraph.getDataLen()), null);
        }
        CParaFormatList cFormatList2;
        CParaFormatList cFormatList;
        for (cFormatList = (cFormatList2 = cParagraph.cFormatList); cFormatList2 != null; cFormatList2 = (CParaFormatList)cFormatList2.getNext()) {
            cFormatList2.setOffset(cFormatList2.getOffset() + cDataLen);
        }
        if (this.cFormatList == null) {
            this.cFormatList = cFormatList;
        }
        else if (cFormatList != null) {
            this.cFormatList.getLast().insertChain(cFormatList);
        }
        cParagraph.cFormatList = null;
        cParagraph.remove();
        this.fixParaFormatList();
    }
    
    void fixParaFormatList() {
        final CFormatInfo formatInfo = this.cStyle.getFormatInfo();
        CParaFormatList cFormatList2;
        for (CParaFormatList cFormatList = this.cFormatList; cFormatList != null; cFormatList = cFormatList2) {
            cFormatList2 = (CParaFormatList)cFormatList.getNext();
            if (!cFormatList.getFormatInfo().affects(formatInfo)) {
                cFormatList.remove();
                if (cFormatList == this.cFormatList) {
                    this.cFormatList = cFormatList2;
                }
                ++this.cChangeCount;
            }
            else {
                final int length = cFormatList.getLength();
                if (cFormatList2 != null && cFormatList.getOffset() + length == cFormatList2.getOffset() && cFormatList.getFormatInfo().equals(cFormatList2.getFormatInfo())) {
                    cFormatList.setLength(length + cFormatList2.getLength());
                    cFormatList2.remove();
                    cFormatList2 = cFormatList;
                    ++this.cChangeCount;
                }
            }
        }
    }
    
    void fixParaSettings() {
        this.cStyle.getParaSettings().cleanup(this.cParaSettings);
    }
    
    boolean isAfter(CParagraph cParagraph) {
        while (cParagraph != null) {
            if (cParagraph == this) {
                return true;
            }
            cParagraph = (CParagraph)cParagraph.getNext();
        }
        return false;
    }
    
    void apply(final CParaSettings cParaSettings) {
        cParaSettings.apply(this.cParaSettings);
        this.changed(0, this.cDataLen, 0);
        ++this.cChangeCount;
    }
    
    void apply(final int n, final CFormatInfo cFormatInfo, final CParaFormatList list) {
        cFormatInfo.apply(list.getFormatInfo());
        this.changed(n, list.getLength(), 0);
        ++this.cChangeCount;
    }
    
    void remove() {
        final boolean hasImage = this.hasImage();
        if (this.cLine != null && hasImage) {
            this.cLine.getParent().getParent().imageChanged();
        }
        this.changed(0, this.cDataLen, -this.cDataLen);
        super.remove();
    }
    
    private void changed(final int n, final int n2, final int n3) {
        if (this.cLine != null) {
            final CRTEditor parent = this.cLine.getParent().getParent();
            parent.cChangeStart.init(this, n);
            parent.cChangeEnd.init(this, n + n2);
            for (CLine cLine = this.cLine; cLine != null && cLine.getStart().getPara() == this; cLine = (CLine)cLine.getNext()) {
                cLine.changed(parent.cChangeStart, parent.cChangeEnd, n3);
            }
        }
    }
    
    static {
        CParagraph.GROWSIZE = 16;
    }
}
