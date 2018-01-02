// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import XMLConsumer.IEntry;
import java.util.Enumeration;
import java.awt.Image;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Color;

public abstract class DynListView extends ListView implements IChunkedDataListener
{
    private static String m_sLoadingMsg;
    private static final Color YELLOW;
    private Vector m_chunkedInfos;
    private String m_sKeyBefore;
    private String m_sKeyAfter;
    private Vector m_vFakeBlocks;
    private Vector m_vRealBlocks;
    private String m_sCheckKey;
    private int m_nLoadingChunk;
    private INumChunkedData[] m_aDataCon;
    private int m_nCheck;
    private boolean m_bNeedCalc;
    private boolean m_bFindCK;
    private int m_nIns;
    private boolean m_bScrl;
    private int m_nNeeded;
    private String m_sShowKey;
    private boolean m_bProcess;
    private boolean m_bLoading;
    private int m_nItems;
    private IChunkedInfo m_ciRef;
    private int m_nRef;
    private int m_nNeedToFindKeyIndex;
    private String m_sFindKey;
    private String m_sBackFindKey;
    private boolean m_bBackActive;
    private boolean m_bActive;
    private VirtualEntry m_virtualEntry;
    
    protected void procResize() {
        if (!this.m_bProcess) {
            this.markBegin();
            super.procResize();
            if (this.m_nIns == -1) {
                this.getUnitIdx(this.getTop(), this.getVisible());
            }
        }
    }
    
    private boolean insertIdxKs(final int n, final BlockContainer blockContainer, final boolean b) {
        boolean b2 = true;
        final int consumed = blockContainer.getConsumed();
        final int current = blockContainer.getCurrent();
        final int n2 = consumed - current;
        int n3 = 0;
        int i = this.m_vFakeBlocks.size() - 1;
        boolean b3 = false;
        int n4;
        FakeItemBlock fakeItemBlock;
        do {
            n4 = n3 + i >> 1;
            fakeItemBlock = this.m_vFakeBlocks.elementAt(n4);
            if (fakeItemBlock.getBegin() > n) {
                i = n4 - 1;
            }
            else {
                if (fakeItemBlock.getBegin() + fakeItemBlock.getNum() > n) {
                    b3 = true;
                    break;
                }
                n3 = n4 + 1;
            }
        } while (i >= n3);
        if (b3) {
            final int top = fakeItemBlock.getTop();
            final int n5 = top + fakeItemBlock.getNum();
            final int num = n - fakeItemBlock.getBegin();
            final int num2 = fakeItemBlock.getNum() + fakeItemBlock.getBegin() - (n + consumed);
            int n6 = n4 + 1;
            if (num > 0) {
                final int n7 = fakeItemBlock.getNum() - num;
                fakeItemBlock.setNum(num);
                final String keyBefore = fakeItemBlock.getKeyBefore();
                fakeItemBlock.setKeyBefore(blockContainer.getFirstKey());
                if (num2 > 0) {
                    this.m_vFakeBlocks.insertElementAt(new FakeItemBlock(n + consumed, num2, blockContainer.getLastKey(), keyBefore, top + num + current), n4 + 1);
                    n6 = n4 + 2;
                }
                this.insertRealBlock(n5 - n7, blockContainer, b);
            }
            else {
                this.insertRealBlock(top, blockContainer, b);
                if (num2 > 0) {
                    fakeItemBlock.setBegin(n + consumed);
                    final int n8 = fakeItemBlock.getNum() - num2;
                    fakeItemBlock.setNum(num2);
                    fakeItemBlock.posChange(current);
                    fakeItemBlock.setKeyAfter(blockContainer.getLastKey());
                }
                else {
                    this.m_vFakeBlocks.removeElementAt(n4);
                    n6 = n4;
                }
            }
            if (n2 > 0) {
                for (int j = n6; j < this.m_vFakeBlocks.size(); ++j) {
                    ((FakeItemBlock)this.m_vFakeBlocks.elementAt(j)).posChange(-n2);
                }
                this.setVerticalMax(this.getVerticalMax() - n2);
            }
        }
        else {
            b2 = false;
        }
        return b2;
    }
    
    protected void listPaint(final Graphics graphics, final Image image) {
        final int top = this.getTop();
        this.setHorizontalMax(this.getWidth(graphics));
        final Enumeration elements = this.getRealBlockToShow(top, this.getVisible()).elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().display(graphics, top, this.getVisible(), this.getUnitHeight(), this.getBackground(), image);
        }
        if (this.m_bLoading) {
            this.displayLoadingMsg(graphics);
        }
    }
    
    public abstract void mergeItems(final BlockContainer p0, final boolean p1, final INumChunkedData[] p2, final int[] p3, final int p4, final int[] p5, final int p6);
    
    private void endLoading() {
        if (this.m_bLoading) {
            this.m_bLoading = false;
            this.repaint();
        }
    }
    
    public void findAndSelect(final String s) {
        if (s != null && s.length() != 0) {
            if (!this.m_bProcess) {
                this.m_sFindKey = s;
                this.m_bActive = false;
                this.findCK();
                return;
            }
            this.m_sBackFindKey = s;
            this.m_bBackActive = false;
        }
    }
    
    public void dispatchToDo(final String s) {
        if (s.equals("checkReady")) {
            this.checkReady();
            return;
        }
        if (s.equals("checkAgain")) {
            this.checkAgain();
            return;
        }
        super.dispatchToDo(s);
    }
    
    public void putData(final IChunkedData chunkedData) {
        this.endLoading();
        if (this.m_nNeedToFindKeyIndex == -1) {
            this.setTimeout("checkReady", 1);
            return;
        }
        this.m_sKeyAfter = ((INumChunkedData)chunkedData).getKeyByPosition(this.m_nNeedToFindKeyIndex);
        this.m_bNeedCalc = true;
        this.m_bScrl = true;
        this.m_nNeedToFindKeyIndex = -1;
        if (this.m_sKeyAfter != null) {
            this.setTimeout("checkReady", 1);
            return;
        }
        this.markEnd();
        this.setTimeout("checkAgain", 50);
    }
    
    private void getLimit(final INumChunkedData[] array, final int[] array2, final int[] array3, final int[] array4, final int n) {
        array3[n] = array[n].getNum();
        array4[n] = -1;
        if (array[n].getUsedItems() != null) {
            array[n].getUsedItems().getLimit(array2, array3, array4, n);
        }
    }
    
    private boolean findCKInRealBlocks() {
        if (this.m_sFindKey != null) {
            if (this.m_vRealBlocks != null && this.m_vRealBlocks.size() > 0) {
                int i = 0;
                int n = this.m_vRealBlocks.size() - 1;
                boolean b = false;
                int n2;
                RealItemBlock realItemBlock;
                int n3;
                do {
                    n2 = (i + n) / 2;
                    realItemBlock = this.m_vRealBlocks.elementAt(n2);
                    n3 = realItemBlock.getClosestEntryId(this.m_sFindKey);
                    if (n3 < 0) {
                        n = n2 - 1;
                    }
                    else {
                        if (n3 < realItemBlock.getNum()) {
                            b = true;
                            break;
                        }
                        i = n2 + 1;
                    }
                } while (i <= n);
                if (!b) {
                    if (n2 == i - 1) {
                        ++n2;
                    }
                    if (n2 < this.m_vRealBlocks.size() && n2 - 1 >= 0) {
                        final RealItemBlock realItemBlock2 = this.m_vRealBlocks.elementAt(n2);
                        final String firstKey = realItemBlock2.getFirstKey();
                        if (firstKey.length() > this.m_sFindKey.length() && Language.compare(firstKey.substring(0, this.m_sFindKey.length()), this.m_sFindKey) == 0) {
                            realItemBlock = realItemBlock2;
                            n3 = realItemBlock.getClosestEntryId(firstKey);
                        }
                        else {
                            realItemBlock = this.m_vRealBlocks.elementAt(n2 - 1);
                            n3 = realItemBlock.getClosestEntryId(realItemBlock.getLastKey());
                        }
                    }
                    else if (n2 >= this.m_vRealBlocks.size()) {
                        realItemBlock = this.m_vRealBlocks.elementAt(n2 - 1);
                        n3 = realItemBlock.getClosestEntryId(realItemBlock.getLastKey());
                    }
                    else if (n2 - 1 < 0) {
                        realItemBlock = this.m_vRealBlocks.elementAt(n2);
                        n3 = realItemBlock.getClosestEntryId(realItemBlock.getFirstKey());
                    }
                }
                if (realItemBlock != null) {
                    final int top = realItemBlock.getTop() + n3;
                    final IEntry entry = realItemBlock.getEntry(top);
                    this.setTop(top);
                    this.repaint();
                    this.selectEntry(this.getTop(), top, entry);
                    if (this.m_bActive) {
                        entry.action(this);
                    }
                }
            }
            this.m_sFindKey = this.m_sBackFindKey;
            this.m_bActive = this.m_bBackActive;
            if (this.m_sBackFindKey != null) {
                this.m_sBackFindKey = null;
                this.m_bBackActive = false;
                this.findCK();
                return false;
            }
        }
        return true;
    }
    
    public void active() {
        if (this.m_sBackFindKey != null) {
            this.m_bBackActive = true;
            return;
        }
        if (this.m_sFindKey != null) {
            this.m_bActive = true;
            return;
        }
        super.active();
    }
    
    public void checkReady() {
        final int size = this.m_chunkedInfos.size();
        final boolean b = false;
        int nLoadingChunk = 0;
        final boolean b2 = this.m_sKeyBefore == null;
        final String sCheckKey = b2 ? this.m_sKeyAfter : this.m_sKeyBefore;
        if (sCheckKey == null) {
            this.markEnd();
            this.setTimeout("checkAgain", 50);
            return;
        }
        INumChunkedData[] aDataCon;
        if (this.m_sCheckKey == null || sCheckKey != this.m_sCheckKey || this.m_nLoadingChunk == 0) {
            this.m_nCheck = 0;
            this.m_sCheckKey = sCheckKey;
            aDataCon = new INumChunkedData[this.m_chunkedInfos.size()];
        }
        else {
            nLoadingChunk = this.m_nLoadingChunk;
            aDataCon = this.m_aDataCon;
        }
        for (int i = this.m_nCheck; i < size; ++i) {
            final INumChunkedData numChunkedData = (INumChunkedData)this.m_chunkedInfos.elementAt(i).getChunkedData(b2, sCheckKey);
            if (numChunkedData != null) {
                if (!numChunkedData.isLoaded()) {
                    this.m_nLoadingChunk = nLoadingChunk;
                    this.m_nCheck = i;
                    this.m_aDataCon = aDataCon;
                    this.beginLoading();
                    numChunkedData.load(this);
                    return;
                }
                aDataCon[nLoadingChunk++] = numChunkedData;
            }
        }
        if (!b) {
            final int n = nLoadingChunk;
            this.m_nLoadingChunk = 0;
            final String sKeyAfter = null;
            this.m_sShowKey = sKeyAfter;
            this.m_sKeyBefore = sKeyAfter;
            this.m_sKeyAfter = sKeyAfter;
            final int[] array = new int[n];
            final int[] array2 = new int[n];
            final int[] array3 = new int[n];
            final int[] array4 = new int[n];
            if (this.m_bNeedCalc || this.m_bFindCK) {
                this.m_nIns = 0;
            }
            for (int j = 0; j < n; ++j) {
                final INumChunkedData numChunkedData2 = aDataCon[j];
                array[j] = numChunkedData2.getKeyPosition(b2, sCheckKey);
                if (this.m_bNeedCalc || this.m_bFindCK) {
                    this.m_nIns += array[j] + numChunkedData2.getLastIndex() - numChunkedData2.getNum();
                    if (!b2 && array[j] != -1) {
                        this.m_nIns += aDataCon[j].getEntry(array[j]).getNextSpan();
                    }
                }
                array2[j] = array[j];
                this.getLimit(aDataCon, array, array3, array4, j);
            }
            if ((this.m_bNeedCalc || this.m_bFindCK) && !b2 && this.m_nIns != -1) {
                this.m_nIns += size - 1;
            }
            if (this.m_nIns != -1 || this.m_bFindCK) {
                final BlockContainer blockContainer = new BlockContainer(this.m_nNeeded);
                this.writeItems(blockContainer, aDataCon, array, array4, array3, b2, 1);
                if (blockContainer.getConsumed() != 0) {
                    int nIns;
                    if (!b2) {
                        nIns = this.m_nIns - blockContainer.getConsumed() + 1;
                    }
                    else {
                        nIns = this.m_nIns;
                    }
                    if (this.insertIdxKs(nIns, blockContainer, this.m_bScrl)) {
                        this.updateUsedK(aDataCon, array2, array, b2);
                        this.repaint();
                        this.m_bScrl = false;
                    }
                    if (this.m_bFindCK) {
                        this.m_bFindCK = false;
                        this.m_bNeedCalc = true;
                        this.m_sKeyBefore = blockContainer.getFirstKey();
                        this.m_nIns = -1;
                        this.setTimeout("checkReady", 50);
                        return;
                    }
                }
                else if (this.m_bFindCK) {
                    this.m_bFindCK = false;
                    this.m_bNeedCalc = true;
                    this.m_sKeyBefore = this.getFirstKeyFromPos(aDataCon, array);
                    this.m_nIns = -1;
                    this.setTimeout("checkReady", 50);
                    return;
                }
                if (!this.findCKInRealBlocks()) {
                    return;
                }
                this.m_nNeeded -= blockContainer.getCurrent();
                this.m_nIns = -1;
                this.markEnd();
                this.setTimeout("checkAgain", 50);
                this.m_bNeedCalc = false;
            }
            else {
                if (!this.findCKInRealBlocks()) {
                    return;
                }
                this.markEnd();
                this.setTimeout("checkAgain", 50);
            }
        }
    }
    
    private IEntry getEntryByPosInternal(final int n) {
        IEntry entry = null;
        final Vector realBlockToShow = this.getRealBlockToShow(n, 1);
        if (realBlockToShow != null && realBlockToShow.size() == 1) {
            entry = realBlockToShow.elementAt(0).getEntry(n);
        }
        return entry;
    }
    
    protected int getWidth(final Graphics graphics) {
        int n = 0;
        final Enumeration<RealItemBlock> elements = this.m_vRealBlocks.elements();
        while (elements.hasMoreElements()) {
            final int width = elements.nextElement().getWidth(graphics);
            if (width > n) {
                n = width;
            }
        }
        return n;
    }
    
    public boolean writeItems(final BlockContainer blockContainer, final INumChunkedData[] array, final int[] array2, final int[] array3, final int[] array4, final boolean b, final int n) {
        final int length = array2.length;
        final int[] array5 = new int[length];
        for (int i = 0; i < length; ++i) {
            array5[i] = array2[i];
        }
        int j;
        do {
            String s = null;
            final int[] array6 = new int[length];
            j = 0;
            for (int k = 0; k < length; ++k) {
                if ((array[k].getNum() > 0 && array2[k] != -1 && b && array2[k] < array4[k]) || (!b && array2[k] > array3[k])) {
                    final String name = array[k].getEntry(array2[k]).getName();
                    if (s == null || (b && Language.compare(s, name) > 0) || (!b && Language.compare(s, name) < 0)) {
                        s = name;
                        j = 0;
                        array6[j++] = k;
                    }
                    else if (Language.compare(s, name) == 0) {
                        array6[j++] = k;
                    }
                }
                else if (n == 1 && array4[k] != array3[k]) {
                    if (b && array2[k] == array4[k]) {
                        this.m_sKeyAfter = array[k].getEntry(array5[k]).getName();
                        return false;
                    }
                    if (!b && array2[k] == array3[k]) {
                        this.m_sKeyBefore = array[k].getEntry(array5[k]).getName();
                        return false;
                    }
                }
            }
            if (j >= 1) {
                for (int l = 0; l < j; ++l) {
                    array5[array6[l]] = array2[array6[l]];
                }
                this.mergeItems(blockContainer, b, array, array6, j, array2, n);
                this.adjustPosition(b, array, array6, j, array2);
                if (n == 1 && blockContainer.getNeeded() <= blockContainer.getCurrent()) {
                    return true;
                }
                continue;
            }
        } while (j > 0);
        return true;
    }
    
    public abstract void adjustPosition(final boolean p0, final INumChunkedData[] p1, final int[] p2, final int p3, final int[] p4);
    
    private Vector getRealBlockToShow(final int n, final int n2) {
        int n3 = 0;
        int i = this.m_vRealBlocks.size() - 1;
        final Vector<RealItemBlock> vector = new Vector<RealItemBlock>();
        boolean b = false;
        if (this.m_vRealBlocks.size() == 0) {
            return vector;
        }
        int n4;
        RealItemBlock realItemBlock;
        do {
            n4 = n3 + i >> 1;
            realItemBlock = this.m_vRealBlocks.elementAt(n4);
            final int top = realItemBlock.getTop();
            final int n5 = top + realItemBlock.getNum();
            if (top >= n + n2) {
                i = n4 - 1;
            }
            else {
                if (n5 > n) {
                    b = true;
                    break;
                }
                n3 = n4 + 1;
            }
        } while (i >= n3);
        if (b) {
            vector.addElement(realItemBlock);
            for (int j = n4 - 1; j >= 0; --j) {
                final RealItemBlock realItemBlock2 = this.m_vRealBlocks.elementAt(j);
                if (realItemBlock2.getTop() + realItemBlock2.getNum() <= n) {
                    break;
                }
                vector.addElement(realItemBlock2);
            }
            for (int k = n4 + 1; k < this.m_vRealBlocks.size(); ++k) {
                final RealItemBlock realItemBlock3 = this.m_vRealBlocks.elementAt(k);
                final int top2 = realItemBlock3.getTop();
                final int n6 = top2 + realItemBlock3.getNum();
                if (top2 >= n + n2) {
                    break;
                }
                vector.addElement(realItemBlock3);
            }
        }
        return vector;
    }
    
    private void insertRealBlock(final int top, final BlockContainer blockContainer, final boolean b) {
        final RealItemBlock realItemBlock = new RealItemBlock(top, blockContainer);
        int n = 0;
        int i = this.m_vRealBlocks.size() - 1;
        int n2 = 0;
        while (i >= n) {
            n2 = n + i >> 1;
            final RealItemBlock realItemBlock2 = this.m_vRealBlocks.elementAt(n2);
            RealItemBlock realItemBlock3;
            if (n2 + 1 <= i) {
                realItemBlock3 = this.m_vRealBlocks.elementAt(n2 + 1);
            }
            else {
                realItemBlock3 = null;
            }
            if (realItemBlock2.getTop() > top) {
                i = n2 - 1;
            }
            else {
                if (realItemBlock3 == null || realItemBlock3.getTop() >= top) {
                    ++n2;
                    break;
                }
                n = n2 + 1;
            }
        }
        this.m_vRealBlocks.insertElementAt(realItemBlock, n2);
        final int n3 = blockContainer.getConsumed() - blockContainer.getCurrent();
        if (n3 != 0) {
            for (int j = n2 + 1; j < this.m_vRealBlocks.size(); ++j) {
                ((RealItemBlock)this.m_vRealBlocks.elementAt(j)).posChange(-n3);
            }
        }
        if (b) {
            this.setTop(top);
        }
        if (this.m_virtualEntry != null) {
            final IEntry entryByPosInternal = this.getEntryByPosInternal(this.m_virtualEntry.getPos());
            if (entryByPosInternal != null) {
                this.m_virtualEntry.fillReal(entryByPosInternal);
                this.m_virtualEntry = null;
            }
        }
    }
    
    private void displayLoadingMsg(final Graphics graphics) {
        final Color color = graphics.getColor();
        final Font font = graphics.getFont();
        graphics.setFont(new Font(font.getName(), font.getStyle() | 0x1, font.getSize() + 1));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth(DynListView.m_sLoadingMsg);
        graphics.setColor(Color.black);
        graphics.drawRect(2, 2, stringWidth + 3, fontMetrics.getHeight() + 3);
        graphics.setColor(DynListView.YELLOW);
        graphics.fillRect(3, 3, stringWidth + 2, fontMetrics.getHeight() + 2);
        graphics.setColor(Color.black);
        graphics.drawString(DynListView.m_sLoadingMsg, 3, 3 + fontMetrics.getLeading() + fontMetrics.getAscent());
        graphics.setColor(color);
        graphics.setFont(font);
    }
    
    protected void procClicked() {
        if (!this.m_bProcess) {
            super.procClicked();
        }
    }
    
    private void beginLoading() {
        this.m_bLoading = true;
    }
    
    public DynListView(final Vector chunkedInfos) {
        this.m_chunkedInfos = null;
        this.m_sKeyBefore = null;
        this.m_sKeyAfter = "";
        this.m_vFakeBlocks = null;
        this.m_vRealBlocks = null;
        this.m_nLoadingChunk = 0;
        this.m_nCheck = 0;
        if (DynListView.m_sLoadingMsg == null) {
            DynListView.m_sLoadingMsg = ResourceLib.GetRes("LoadingData");
        }
        this.m_chunkedInfos = chunkedInfos;
        this.m_bProcess = false;
        this.m_bLoading = false;
        this.m_nNeedToFindKeyIndex = -1;
        this.m_nIns = -1;
        this.m_vFakeBlocks = new Vector();
        this.m_vRealBlocks = new Vector();
        this.setUnitHeight(16);
        this.init();
    }
    
    public void checkAgain() {
        if (this.m_bProcess) {
            this.setTimeout("checkAgain", 50);
            return;
        }
        if (this.m_sBackFindKey != null) {
            this.m_sFindKey = this.m_sBackFindKey;
            this.m_bActive = this.m_bBackActive;
            this.m_sBackFindKey = null;
            this.m_bBackActive = false;
            this.findCK();
            return;
        }
        this.markBegin();
        this.getUnitIdx(this.getTop(), this.getVisible());
    }
    
    protected void procMoved() {
        if (!this.m_bProcess) {
            super.procMoved();
        }
    }
    
    private void markBegin() {
        this.m_bProcess = true;
    }
    
    private int getTotalNum() {
        int n = 0;
        final Enumeration<IChunkedInfo> elements = this.m_chunkedInfos.elements();
        while (elements.hasMoreElements()) {
            final IChunkedInfo nextElement = elements.nextElement();
            if (nextElement instanceof IChunkedInfo) {
                n += nextElement.getTotalNum();
            }
        }
        return n;
    }
    
    protected IEntry getEntryByPos(final int n) {
        IEntry entry = this.getEntryByPosInternal(n);
        if (entry == null) {
            this.m_virtualEntry = new VirtualEntry(n);
            entry = this.m_virtualEntry;
        }
        return entry;
    }
    
    private String getFirstKeyFromPos(final INumChunkedData[] array, final int[] array2) {
        final int length = array2.length;
        String biggestChar = Language.getBiggestChar();
        for (int i = 0; i < length; ++i) {
            if (array[i].getNum() > 0 && array2[i] >= 0 && array2[i] < array[i].getNum()) {
                final String name = array[i].getEntry(array2[i]).getName();
                if (biggestChar == null || Language.compare(biggestChar, name) > 0) {
                    biggestChar = name;
                }
            }
        }
        return biggestChar;
    }
    
    private String getEndString() {
        final String biggestChar = Language.getBiggestChar();
        return biggestChar + biggestChar + biggestChar + biggestChar + biggestChar + biggestChar + biggestChar + biggestChar;
    }
    
    private void updateUsedK(final INumChunkedData[] array, final int[] array2, final int[] array3, final boolean b) {
        for (int i = 0; i < array2.length; ++i) {
            if (array3[i] != array2[i]) {
                int n;
                int n2;
                if (b) {
                    n = array2[i];
                    n2 = array3[i] - 1;
                }
                else {
                    if (array3[i] != -1) {
                        n = array3[i] + array[i].getEntry(array3[i]).getNextSpan() + 1;
                    }
                    else {
                        n = 0;
                    }
                    n2 = array2[i] + array[i].getEntry(array2[i]).getNextSpan();
                }
                if (n <= n2) {
                    array[i].getUsedItems().addUsedItem(n, n2);
                    if (array[i].getUsedItems().isCovered(0, array[i].getNum() - 1)) {
                        array[i].setDone(true);
                    }
                }
            }
        }
    }
    
    static {
        DynListView.m_sLoadingMsg = null;
        YELLOW = new Color(255, 255, 225);
    }
    
    private void getUnitIdx(final int n, final int n2) {
        if (this.m_vFakeBlocks.size() == 0) {
            this.markEnd();
            return;
        }
        int n3 = 0;
        int i = this.m_vFakeBlocks.size() - 1;
        boolean b = false;
        FakeItemBlock fakeItemBlock;
        int top;
        int n5;
        do {
            final int n4 = n3 + i >> 1;
            fakeItemBlock = this.m_vFakeBlocks.elementAt(n4);
            top = fakeItemBlock.getTop();
            n5 = top + fakeItemBlock.getNum();
            if (top >= n + n2) {
                i = n4 - 1;
            }
            else {
                if (n5 > n) {
                    b = true;
                    break;
                }
                n3 = n4 + 1;
            }
        } while (i >= n3);
        if (b) {
            if (top >= n) {
                this.m_sKeyAfter = fakeItemBlock.getKeyAfter();
                this.m_sKeyBefore = null;
                this.m_nNeeded = n2 - top + n;
                this.m_nIns = fakeItemBlock.getBegin();
                this.checkReady();
                return;
            }
            if (n5 <= n + n2) {
                this.m_sKeyBefore = fakeItemBlock.getKeyBefore();
                this.m_sKeyAfter = null;
                this.m_nNeeded = n5 - n;
                this.m_bNeedCalc = true;
                this.checkReady();
                return;
            }
            this.m_nNeeded = this.getVisible();
            if (!this.showItemsInEvaluation(fakeItemBlock.getBegin() + (n - top))) {
                this.m_sKeyAfter = fakeItemBlock.getKeyAfter();
                this.m_sKeyBefore = null;
                this.m_nNeeded = this.getVisible();
                this.m_nIns = fakeItemBlock.getBegin();
                this.checkReady();
            }
        }
        else {
            this.markEnd();
        }
    }
    
    protected void procScroll() {
        if (!this.m_bProcess) {
            this.markBegin();
            this.getUnitIdx(this.getTop(), this.getVisible());
            super.procScroll();
        }
    }
    
    private void findCK() {
        if (this.m_sFindKey != null) {
            this.m_sKeyAfter = this.m_sFindKey;
            this.m_bFindCK = true;
            this.m_nNeeded = 1;
            if (!this.m_bProcess) {
                this.markBegin();
            }
            this.checkReady();
        }
    }
    
    public Dimension getPreferredSize() {
        return this.getParent().getSize();
    }
    
    private void init() {
        this.m_nNeeded = 100;
        this.m_nItems = this.getTotalNum();
        this.m_vFakeBlocks.addElement(new FakeItemBlock(0, this.m_nItems, "", this.getEndString(), 0));
        this.setVerticalMax(this.m_nItems);
        this.getMaxChunkInfo();
    }
    
    private void markEnd() {
        if (this.m_bProcess) {
            this.m_bProcess = false;
            BsscHelpRedirector.showStatus(ResourceLib.GetRes("Done"));
        }
    }
    
    private void getMaxChunkInfo() {
        int nRef = 0;
        IChunkedInfo ciRef = null;
        final Enumeration<IChunkedInfo> elements = (Enumeration<IChunkedInfo>)this.m_chunkedInfos.elements();
        while (elements.hasMoreElements()) {
            final IChunkedInfo nextElement = elements.nextElement();
            if (nextElement instanceof IChunkedInfo) {
                final int totalNum = nextElement.getTotalNum();
                if (totalNum <= nRef) {
                    continue;
                }
                ciRef = nextElement;
                nRef = totalNum;
            }
        }
        this.m_nRef = nRef;
        this.m_ciRef = ciRef;
    }
    
    private boolean showItemsInEvaluation(final int n) {
        boolean b = true;
        if (this.m_nItems != 0) {
            final int nNeedToFindKeyIndex = (int)Math.floor(n / this.m_nItems * this.m_nRef);
            final INumChunkedData numChunkedData = (INumChunkedData)this.m_ciRef.getChunkByIdx(nNeedToFindKeyIndex);
            if (numChunkedData != null) {
                if (!numChunkedData.isLoaded()) {
                    this.m_nNeedToFindKeyIndex = nNeedToFindKeyIndex;
                    this.beginLoading();
                    numChunkedData.load(this);
                }
                else {
                    this.m_sKeyAfter = numChunkedData.getKeyByPosition(nNeedToFindKeyIndex);
                    if (this.m_sKeyAfter != null) {
                        this.m_bNeedCalc = true;
                        this.m_bScrl = true;
                        this.checkReady();
                    }
                    else {
                        this.markEnd();
                        b = false;
                    }
                }
            }
            else {
                this.markEnd();
                b = false;
            }
        }
        else {
            this.markEnd();
            b = false;
        }
        return b;
    }
}
