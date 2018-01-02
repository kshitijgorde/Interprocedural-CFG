// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui.text;

import java.util.StringTokenizer;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.util.Vector;
import java.util.Hashtable;

public class MultiText extends Text
{
    Hashtable m_htUpDownPositions;
    boolean m_bAllowEnter;
    boolean m_bAllowVScroll;
    int m_nLastXPosition;
    
    public MultiText() {
        this("");
    }
    
    public MultiText(final String s) {
        super(s);
        this.m_bAllowEnter = true;
        this.m_bAllowVScroll = true;
        this.m_nLastXPosition = -1;
        this.m_htUpDownPositions = new Hashtable();
    }
    
    public boolean getAllowSpace() {
        return false;
    }
    
    public boolean getAllowVScroll() {
        return this.m_bAllowVScroll;
    }
    
    public int getCursorPos() {
        int n = 0;
        for (int i = 0; i < super.cursorPoint.y; ++i) {
            n += ((Paragraph)super.m_vParagraphs.elementAt(i)).getText().length() + 1;
        }
        return n + super.m_vParagraphs.elementAt(super.cursorPoint.y).getCursorPos();
    }
    
    protected boolean insertChar(final char c) {
        if (c == '\0') {
            return false;
        }
        if (super.m_vParagraphs == null || super.cursorPoint.y < 0 || super.cursorPoint.y > super.m_vParagraphs.size() - 1) {
            return false;
        }
        this.m_htUpDownPositions.clear();
        this.m_nLastXPosition = -1;
        if (this.isSelected()) {
            this.cut(false);
            if (super.textListener != null) {
                super.textListener.textValueChanged(super.textEvent);
            }
            if (this.m_bAllowVScroll) {
                this.onVerticalScroll();
            }
            this.start();
        }
        if (super.m_vParagraphs.elementAt(super.cursorPoint.y).insertChar(c, !this.isAutoWrap() && !super.m_bAllowHScroll)) {
            if (super.textListener != null) {
                super.textListener.textValueChanged(super.textEvent);
            }
            if (this.m_bAllowVScroll) {
                this.onVerticalScroll();
            }
            this.update();
            return true;
        }
        return false;
    }
    
    public boolean isAllowEnter() {
        return this.m_bAllowEnter && this.getVAlign() == 1;
    }
    
    public boolean isCursorOnEndPos() {
        return super.m_vParagraphs.size() <= super.cursorPoint.y || (super.m_vParagraphs.size() - 1 == super.cursorPoint.y && super.getCursorPos() >= super.m_vParagraphs.elementAt(super.cursorPoint.y).getText().length());
    }
    
    protected int nextCursorPos(final int n, final int n2) {
        if (this.m_htUpDownPositions.size() == 0) {
            return -1;
        }
        if (this.m_htUpDownPositions.containsKey("" + n)) {
            int n3 = -1;
            final Vector<Integer> vector = this.m_htUpDownPositions.get("" + n);
            for (int i = 0; i < vector.size(); ++i) {
                final int intValue = vector.elementAt(i);
                if (intValue > n2) {
                    n3 = ((n3 == -1) ? intValue : Math.min(n3, intValue));
                }
            }
            return n3;
        }
        return -1;
    }
    
    protected void onBackspaceKey() {
        this.m_htUpDownPositions.clear();
        if (this.isSelected()) {
            this.cut(false);
            if (super.textListener != null) {
                super.textListener.textValueChanged(super.textEvent);
            }
            this.start();
        }
        else {
            final Paragraph paragraph = super.m_vParagraphs.elementAt(super.cursorPoint.y);
            if (!this.isAutoWrap() && paragraph.getCursorPos() == 0) {
                if (paragraph.getXOffset() > 0) {
                    this.setXOffset(0);
                    this.repaint();
                    return;
                }
                if (super.cursorPoint.y == 0) {
                    return;
                }
            }
            else if (super.cursorPoint.y == 0 && paragraph.getCursorPos() == 0) {
                return;
            }
            this.stop();
            if (paragraph.getCursorPos() == 0) {
                final String text = paragraph.getText();
                final Paragraph paragraph2 = super.m_vParagraphs.elementAt(super.cursorPoint.y - 1);
                paragraph2.setCursorPos(paragraph2.getText().length());
                paragraph2.setText(String.valueOf(paragraph2.getText()) + text);
                super.m_vParagraphs.removeElement(paragraph);
                final Point cursorPoint = super.cursorPoint;
                --cursorPoint.y;
            }
            else {
                paragraph.onBackspaceKey();
            }
            if (super.textListener != null) {
                super.textListener.textValueChanged(super.textEvent);
            }
            this.start();
        }
        if (this.m_bAllowVScroll) {
            this.onVerticalScroll();
        }
        this.repaint();
    }
    
    protected void onDeleteKey() {
        this.m_htUpDownPositions.clear();
        if (this.isSelected()) {
            this.cut(false);
            if (super.textListener != null) {
                super.textListener.textValueChanged(super.textEvent);
            }
            this.start();
        }
        else {
            final Paragraph paragraph = super.m_vParagraphs.elementAt(super.cursorPoint.y);
            if (paragraph.isCursorAtEnd() && super.cursorPoint.y >= super.m_vParagraphs.size() - 1) {
                return;
            }
            if (paragraph.isCursorAtEnd()) {
                final String text = paragraph.getText();
                final Paragraph paragraph2 = super.m_vParagraphs.elementAt(super.cursorPoint.y + 1);
                final String string = String.valueOf(text) + paragraph2.getText();
                super.m_vParagraphs.removeElement(paragraph2);
                paragraph.setText(string);
            }
            else {
                paragraph.onDeleteKey();
            }
            if (super.textListener != null) {
                super.textListener.textValueChanged(super.textEvent);
            }
        }
        if (this.m_bAllowVScroll) {
            this.onVerticalScroll();
        }
        this.repaint();
    }
    
    protected void onDownKey(final int n) {
        final Paragraph paragraph = super.m_vParagraphs.elementAt(super.cursorPoint.y);
        if (paragraph.isCursorOnLastLine() && super.cursorPoint.y == super.m_vParagraphs.size() - 1) {
            this.onEndKey(n);
            return;
        }
        this.stop();
        if (paragraph.isCursorOnLastLine()) {
            this.storePosition(super.cursorPoint.y, paragraph.getCursorPos());
            int nLastXPosition;
            if (this.m_nLastXPosition == -1) {
                nLastXPosition = paragraph.getCursorPoint().x;
                this.m_nLastXPosition = nLastXPosition;
            }
            else {
                nLastXPosition = this.m_nLastXPosition;
            }
            final Point cursorPoint = super.cursorPoint;
            ++cursorPoint.y;
            final Paragraph paragraph2 = super.m_vParagraphs.elementAt(super.cursorPoint.y);
            final int nextCursorPos = this.nextCursorPos(super.cursorPoint.y, 0);
            if (nextCursorPos == -1) {
                paragraph2.moveSpecial(false, nLastXPosition, 40);
            }
            else {
                paragraph2.setCursorPos(nextCursorPos);
            }
            if (n == 1) {
                if (paragraph.getSelectionState() == 0) {
                    paragraph.setSelectionState(1);
                    paragraph.setSelectionStart(paragraph.getCursorPos());
                    super.selectionStart.x = paragraph.getCursorPos();
                    super.selectionStart.y = super.cursorPoint.y - 1;
                    if (this.m_bAllowVScroll) {
                        super.prevYOffset = super.y_offset;
                    }
                }
                paragraph.setSelectionEnd(paragraph.getText().length());
                if (paragraph2.getSelectionState() == 0) {
                    paragraph2.setSelectionState(1);
                    paragraph2.setSelectionStart(0);
                }
                super.selectionEnd.x = paragraph2.getCursorPos();
                super.selectionEnd.y = super.cursorPoint.y;
                paragraph2.setSelectionEnd(paragraph2.getCursorPos());
            }
            else {
                if (paragraph.getSelectionState() != 0) {
                    this.unselectAll();
                }
                this.start();
            }
        }
        else {
            final int cursorPos = paragraph.getCursorPos();
            this.storePosition(super.cursorPoint.y, cursorPos);
            final int nextCursorPos2 = this.nextCursorPos(super.cursorPoint.y, cursorPos);
            if (nextCursorPos2 == -1) {
                paragraph.onDownKey();
            }
            else {
                paragraph.setCursorPos(nextCursorPos2);
            }
            final int cursorPos2 = paragraph.getCursorPos();
            if (n == 1) {
                if (paragraph.getSelectionState() == 0) {
                    paragraph.setSelectionState(1);
                    paragraph.setSelectionStart(cursorPos);
                    super.selectionStart.x = cursorPos;
                    super.selectionStart.y = super.cursorPoint.y;
                    if (this.m_bAllowVScroll) {
                        super.prevYOffset = super.y_offset;
                    }
                }
                super.selectionEnd.x = cursorPos2;
                super.selectionEnd.y = super.cursorPoint.y;
                paragraph.setSelectionEnd(cursorPos2);
            }
            else {
                if (paragraph.getSelectionState() != 0) {
                    this.unselectAll();
                }
                this.start();
            }
        }
        if (this.m_bAllowVScroll) {
            this.onVerticalScroll();
        }
        this.repaint();
    }
    
    protected void onEndKey(final int n) {
        final Paragraph paragraph = super.m_vParagraphs.elementAt(super.cursorPoint.y);
        this.m_htUpDownPositions.clear();
        this.m_nLastXPosition = -1;
        this.stop();
        final int cursorPos = paragraph.getCursorPos();
        paragraph.onEndKey();
        final int cursorPos2 = paragraph.getCursorPos();
        if (n == 1) {
            if (paragraph.getSelectionState() == 0) {
                paragraph.setSelectionState(1);
                paragraph.setSelectionStart(cursorPos);
                super.selectionStart.x = cursorPos;
                super.selectionStart.y = super.cursorPoint.y;
            }
            super.selectionEnd.x = cursorPos2;
            super.selectionEnd.y = super.cursorPoint.y;
            paragraph.setSelectionEnd(cursorPos2);
        }
        else {
            if (paragraph.getSelectionState() != 0) {
                this.unselectAll();
            }
            this.start();
        }
        this.repaint();
    }
    
    protected void onEnterKey() {
        if (!this.isAllowEnter()) {
            super.onEnterKey();
            return;
        }
        if (this.isSelected()) {
            this.cut(false);
            if (super.textListener != null) {
                super.textListener.textValueChanged(super.textEvent);
            }
            this.start();
        }
        final String onEnterKey = super.m_vParagraphs.elementAt(super.cursorPoint.y).onEnterKey();
        this.m_htUpDownPositions.clear();
        Paragraph paragraph;
        if (onEnterKey == null || onEnterKey.length() < 1) {
            paragraph = new Paragraph(this, "");
        }
        else {
            paragraph = new Paragraph(this, onEnterKey);
        }
        paragraph.setFont(this.getFont());
        paragraph.setFontMetrics(this.getFontMetrics(this.getFont()));
        paragraph.setWidth(this.getSize().width - super.m_inTextInsets.left - super.m_inTextInsets.right);
        paragraph.setForeground(this.getForeground());
        paragraph.setBackground(this.getBackground());
        paragraph.setInsets(super.m_inTextInsets);
        paragraph.setHAlign(super.m_nHAlign);
        paragraph.setAllowScroll(this.getAllowHScroll());
        paragraph.setAutoWrap(this.isAutoWrap());
        super.m_vParagraphs.insertElementAt(paragraph, super.cursorPoint.y + 1);
        final Point cursorPoint = super.cursorPoint;
        ++cursorPoint.y;
        paragraph.setCursorPos(0);
        if (this.m_bAllowVScroll) {
            this.onVerticalScroll();
        }
        this.setXOffset(0);
        if (super.textListener != null) {
            super.textListener.textValueChanged(super.textEvent);
        }
        this.repaint();
    }
    
    protected void onHomeKey(final int n) {
        final Paragraph paragraph = super.m_vParagraphs.elementAt(super.cursorPoint.y);
        this.m_htUpDownPositions.clear();
        this.m_nLastXPosition = -1;
        this.stop();
        final int cursorPos = paragraph.getCursorPos();
        paragraph.onHomeKey();
        final int cursorPos2 = paragraph.getCursorPos();
        if (n == 1) {
            if (paragraph.getSelectionState() == 0) {
                paragraph.setSelectionState(1);
                paragraph.setSelectionStart(cursorPos);
                super.selectionStart.x = cursorPos;
                super.selectionStart.y = super.cursorPoint.y;
            }
            super.selectionEnd.x = cursorPos2;
            super.selectionEnd.y = super.cursorPoint.y;
            paragraph.setSelectionEnd(cursorPos2);
        }
        else {
            if (paragraph.getSelectionState() != 0) {
                this.unselectAll();
            }
            this.start();
        }
        this.repaint();
    }
    
    protected void onLeftKey(final int n) {
        final Paragraph paragraph = super.m_vParagraphs.elementAt(super.cursorPoint.y);
        int cursorPos = paragraph.getCursorPos();
        if (cursorPos == 0) {
            if (paragraph.getXOffset() > 0) {
                this.setXOffset(0);
                this.repaint();
                return;
            }
            if (super.cursorPoint.y == 0) {
                return;
            }
        }
        this.m_htUpDownPositions.clear();
        this.m_nLastXPosition = -1;
        this.stop();
        Point selectionStart = super.selectionStart;
        final Point selectionEnd = super.selectionEnd;
        if (selectionEnd.y < selectionStart.y || (selectionEnd.y == selectionStart.y && selectionEnd.x < selectionStart.x)) {
            selectionStart = selectionEnd;
        }
        if (cursorPos == 0) {
            final Point cursorPoint = super.cursorPoint;
            --cursorPoint.y;
            final Paragraph paragraph2 = super.m_vParagraphs.elementAt(super.cursorPoint.y);
            paragraph2.moveSpecial(true, this.getSize().width, 37);
            if (n == 1) {
                if (paragraph.getSelectionState() == 0) {
                    paragraph.setSelectionState(1);
                    paragraph.setSelectionStart(0);
                    super.selectionStart.y = super.cursorPoint.y + 1;
                    super.selectionStart.x = 0;
                }
                if (paragraph2.getSelectionState() == 0) {
                    paragraph2.setSelectionState(1);
                    paragraph2.setSelectionStart(paragraph2.getCursorPos());
                }
                final int selectionEnd2 = (paragraph2.getText().length() > 0) ? (paragraph2.getCursorPos() - 1) : paragraph2.getCursorPos();
                paragraph2.setCursorPos(selectionEnd2);
                super.selectionEnd.x = selectionEnd2;
                super.selectionEnd.y = super.cursorPoint.y;
                paragraph2.setSelectionEnd(selectionEnd2);
            }
            else {
                if (this.isSelected()) {
                    super.cursorPoint.y = selectionStart.y;
                    super.cursorPoint.x = selectionStart.x;
                    super.m_vParagraphs.elementAt(super.cursorPoint.y).setCursorPos(selectionStart.x);
                    this.unselectAll();
                }
                this.start();
            }
        }
        else {
            paragraph.setCursorPos(--cursorPos);
            if (n == 1) {
                if (paragraph.getSelectionState() == 0) {
                    paragraph.setSelectionState(1);
                    paragraph.setSelectionStart(cursorPos + 1);
                    super.selectionStart.y = super.cursorPoint.y;
                    super.selectionStart.x = cursorPos + 1;
                }
                super.selectionEnd.x = cursorPos;
                super.selectionEnd.y = super.cursorPoint.y;
                paragraph.setSelectionEnd(cursorPos);
            }
            else {
                if (this.isSelected()) {
                    super.cursorPoint.y = selectionStart.y;
                    super.cursorPoint.x = selectionStart.x;
                    super.m_vParagraphs.elementAt(super.cursorPoint.y).setCursorPos(selectionStart.x);
                    this.unselectAll();
                }
                this.start();
            }
            if (!this.isAutoWrap() && paragraph.getCursorPoint().x < super.m_inTextInsets.left + 2) {
                int xOffset = paragraph.getXOffset() - this.getSize().width / 2;
                if (xOffset < 0) {
                    xOffset = 0;
                }
                this.setXOffset(xOffset);
            }
        }
        if (this.m_bAllowVScroll) {
            this.onVerticalScroll();
        }
        this.repaint();
    }
    
    protected void onRightKey(final int n) {
        final Paragraph paragraph = super.m_vParagraphs.elementAt(super.cursorPoint.y);
        int cursorPos = paragraph.getCursorPos();
        if (super.cursorPoint.y == super.m_vParagraphs.size() - 1 && cursorPos == paragraph.getText().length()) {
            return;
        }
        this.m_htUpDownPositions.clear();
        this.m_nLastXPosition = -1;
        this.stop();
        final Point selectionStart = super.selectionStart;
        Point selectionEnd = super.selectionEnd;
        if (selectionEnd.y < selectionStart.y || (selectionEnd.y == selectionStart.y && selectionEnd.x < selectionStart.x)) {
            selectionEnd = selectionStart;
        }
        if (cursorPos == paragraph.getText().length()) {
            final Point cursorPoint = super.cursorPoint;
            ++cursorPoint.y;
            final Paragraph paragraph2 = super.m_vParagraphs.elementAt(super.cursorPoint.y);
            paragraph2.moveSpecial(false, 0, 39);
            if (n == 1) {
                if (paragraph.getSelectionState() == 0) {
                    paragraph.setSelectionState(1);
                    paragraph.setSelectionStart(cursorPos);
                    super.selectionStart.x = cursorPos;
                    super.selectionStart.y = super.cursorPoint.y - 1;
                }
                if (paragraph2.getSelectionState() == 0) {
                    paragraph2.setSelectionState(1);
                    paragraph2.setSelectionStart(0);
                }
                final int selectionEnd2 = (paragraph2.getText().length() > 0) ? 1 : 0;
                super.selectionEnd.x = selectionEnd2;
                super.selectionEnd.y = super.cursorPoint.y;
                paragraph2.setCursorPos(selectionEnd2);
                paragraph2.setSelectionEnd(selectionEnd2);
            }
            else {
                if (this.isSelected()) {
                    super.cursorPoint.y = selectionEnd.y;
                    super.cursorPoint.x = selectionEnd.x;
                    super.m_vParagraphs.elementAt(super.cursorPoint.y).setCursorPos(selectionEnd.x);
                    this.unselectAll();
                }
                this.start();
            }
        }
        else {
            paragraph.setCursorPos(++cursorPos);
            if (n == 1) {
                if (paragraph.getSelectionState() == 0) {
                    paragraph.setSelectionState(1);
                    paragraph.setSelectionStart(cursorPos - 1);
                    super.selectionStart.x = cursorPos - 1;
                    super.selectionStart.y = super.cursorPoint.y;
                }
                super.selectionEnd.x = cursorPos;
                super.selectionEnd.y = super.cursorPoint.y;
                paragraph.setSelectionEnd(cursorPos);
            }
            else {
                if (this.isSelected()) {
                    super.cursorPoint.y = selectionEnd.y;
                    super.cursorPoint.x = selectionEnd.x;
                    super.m_vParagraphs.elementAt(super.cursorPoint.y).setCursorPos(selectionEnd.x);
                    this.unselectAll();
                }
                this.start();
            }
        }
        if (this.m_bAllowVScroll) {
            this.onVerticalScroll();
        }
        this.repaint();
    }
    
    protected void onUpKey(final int n) {
        final Paragraph paragraph = super.m_vParagraphs.elementAt(super.cursorPoint.y);
        if (paragraph.isCursorOnFirstLine() && super.cursorPoint.y == 0) {
            this.onHomeKey(n);
            return;
        }
        this.stop();
        if (paragraph.isCursorOnFirstLine()) {
            int nLastXPosition;
            if (this.m_nLastXPosition == -1) {
                nLastXPosition = paragraph.getCursorPoint().x;
                this.m_nLastXPosition = nLastXPosition;
            }
            else {
                nLastXPosition = this.m_nLastXPosition;
            }
            this.storePosition(super.cursorPoint.y, paragraph.getCursorPos());
            final Point cursorPoint = super.cursorPoint;
            --cursorPoint.y;
            final Paragraph paragraph2 = super.m_vParagraphs.elementAt(super.cursorPoint.y);
            final int prevCursorPos = this.prevCursorPos(super.cursorPoint.y, paragraph2.getText().length());
            if (prevCursorPos == -1) {
                paragraph2.moveSpecial(true, nLastXPosition, 38);
            }
            else {
                paragraph2.setCursorPos(prevCursorPos);
            }
            if (n == 1) {
                if (paragraph.getSelectionState() == 0) {
                    paragraph.setSelectionState(1);
                    paragraph.setSelectionStart(paragraph.getCursorPos());
                    super.selectionStart.x = paragraph.getCursorPos();
                    super.selectionStart.y = super.cursorPoint.y + 1;
                }
                paragraph.setSelectionEnd(0);
                if (paragraph2.getSelectionState() == 0) {
                    paragraph2.setSelectionState(1);
                    paragraph2.setSelectionStart(paragraph2.getText().length());
                }
                super.selectionEnd.x = paragraph2.getCursorPos();
                super.selectionEnd.y = super.cursorPoint.y;
                paragraph2.setSelectionEnd(paragraph2.getCursorPos());
            }
            else {
                if (paragraph.getSelectionState() != 0) {
                    this.unselectAll();
                }
                this.start();
            }
        }
        else {
            final int cursorPos = paragraph.getCursorPos();
            this.storePosition(super.cursorPoint.y, cursorPos);
            final int prevCursorPos2 = this.prevCursorPos(super.cursorPoint.y, cursorPos);
            if (prevCursorPos2 == -1) {
                paragraph.onUpKey();
            }
            else {
                paragraph.setCursorPos(prevCursorPos2);
            }
            final int cursorPos2 = paragraph.getCursorPos();
            if (n == 1) {
                if (paragraph.getSelectionState() == 0) {
                    paragraph.setSelectionState(1);
                    paragraph.setSelectionStart(cursorPos);
                    super.selectionStart.x = cursorPos;
                    super.selectionStart.y = super.cursorPoint.y;
                }
                super.selectionEnd.x = cursorPos2;
                super.selectionEnd.y = super.cursorPoint.y;
                paragraph.setSelectionEnd(cursorPos2);
            }
            else {
                if (paragraph.getSelectionState() != 0) {
                    this.unselectAll();
                }
                this.start();
            }
        }
        if (this.m_bAllowVScroll) {
            this.onVerticalScroll();
            super.prevYOffset = super.y_offset;
        }
        this.repaint();
    }
    
    protected void onVerticalScroll() {
        int n = 0;
        int n2 = 0;
        for (int i = 0; i <= super.cursorPoint.y; ++i) {
            n += ((Paragraph)super.m_vParagraphs.elementAt(i)).getYSpan();
            if (n > super.y_offset) {
                n2 = n - super.y_offset;
            }
        }
        final Paragraph paragraph = super.m_vParagraphs.elementAt(super.cursorPoint.y);
        final int n3 = n2 - paragraph.getYSpan() + paragraph.getCursorPoint().y;
        int n4 = 2;
        if (super.m_nBorderStyle == 0) {
            n4 = 0;
        }
        else if (super.m_nBorderStyle == 3) {
            n4 = 1;
        }
        if (n3 + paragraph.getFontMetrics().getHeight() + super.m_inTextInsets.top + super.m_inTextInsets.bottom + 2 * n4 > this.getSize().height) {
            super.y_offset += paragraph.getFontMetrics().getHeight();
        }
        else if (n3 < 0 && super.y_offset > 0) {
            super.y_offset -= paragraph.getFontMetrics().getHeight();
        }
    }
    
    protected int prevCursorPos(final int n, final int n2) {
        if (this.m_htUpDownPositions.size() == 0) {
            return -1;
        }
        if (this.m_htUpDownPositions.containsKey("" + n)) {
            int max = -1;
            final Vector<Integer> vector = this.m_htUpDownPositions.get("" + n);
            for (int i = 0; i < vector.size(); ++i) {
                final int intValue = vector.elementAt(i);
                if (intValue < n2) {
                    max = Math.max(max, intValue);
                }
            }
            return max;
        }
        return -1;
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getID() == 501) {
            this.m_htUpDownPositions.clear();
            this.m_nLastXPosition = -1;
        }
    }
    
    protected void processMouseMotionEvent(final MouseEvent mouseEvent) {
        mouseEvent.getPoint();
        if (mouseEvent.getID() == 506) {
            super.processMouseMotionEvent(mouseEvent);
            this.onVerticalScroll();
        }
        else {
            super.processMouseMotionEvent(mouseEvent);
        }
    }
    
    public void setAllowEnter(final boolean bAllowEnter) {
        this.m_bAllowEnter = bAllowEnter;
    }
    
    public void setAllowHScroll(final boolean b) {
        super.m_bAllowHScroll = (b && this.getHAlign() == 1 && this.getVAlign() == 1 && !this.isAutoWrap());
        for (int i = 0; i < super.m_vParagraphs.size(); ++i) {
            ((Paragraph)super.m_vParagraphs.elementAt(i)).setAllowScroll(super.m_bAllowHScroll);
        }
    }
    
    public void setAllowVScroll(final boolean bAllowVScroll) {
        this.m_bAllowVScroll = bAllowVScroll;
    }
    
    public void setText(String s, final boolean b) {
        if (s == null) {
            s = "";
        }
        if (super.m_vParagraphs.size() > 0) {
            super.m_vParagraphs.removeAllElements();
        }
        if (s.equals("")) {
            final Paragraph paragraph = new Paragraph(this, s);
            paragraph.setFont(this.getFont());
            paragraph.setFontMetrics(this.getFontMetrics(this.getFont()));
            paragraph.setBackground(this.getBackground());
            paragraph.setForeground(this.getForeground());
            paragraph.setWidth(this.getSize().width - super.m_nWidthOffset);
            paragraph.setInsets(super.m_inTextInsets);
            paragraph.setHAlign(this.getHAlign());
            paragraph.setAllowScroll(this.getAllowHScroll());
            paragraph.setAutoWrap(this.isAutoWrap());
            super.m_vParagraphs.addElement(paragraph);
            super.cursorPoint = new Point(0, 0);
            super.selectionStart = new Point(0, 0);
            super.selectionEnd = new Point(0, 0);
        }
        else {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n", false);
            while (stringTokenizer.hasMoreTokens()) {
                final Paragraph paragraph2 = new Paragraph(this, stringTokenizer.nextToken());
                paragraph2.setFont(this.getFont());
                paragraph2.setFontMetrics(this.getFontMetrics(this.getFont()));
                paragraph2.setBackground(this.getBackground());
                paragraph2.setForeground(this.getForeground());
                paragraph2.setWidth(this.getSize().width - super.m_nWidthOffset);
                paragraph2.setInsets(super.m_inTextInsets);
                paragraph2.setHAlign(this.getHAlign());
                paragraph2.setAllowScroll(this.getAllowHScroll());
                paragraph2.setAutoWrap(this.isAutoWrap());
                super.m_vParagraphs.addElement(paragraph2);
            }
        }
        super.y_offset = 0;
        super.cursorPoint = new Point(0, 0);
        super.selectionStart = new Point(0, 0);
        super.selectionEnd = new Point(0, 0);
        if (b) {
            this.repaint();
        }
    }
    
    public void setVAlign(final int vAlign) {
        if (vAlign != 1) {
            this.setAllowHScroll(false);
        }
        super.setVAlign(vAlign);
    }
    
    public void setXOffset(final int xOffset) {
        for (int size = super.m_vParagraphs.size(), i = 0; i < size; ++i) {
            ((Paragraph)super.m_vParagraphs.elementAt(i)).setXOffset(xOffset);
        }
    }
    
    protected void storePosition(final int n, final int n2) {
        Vector<Object> vector;
        if (this.m_htUpDownPositions.containsKey("" + n)) {
            vector = this.m_htUpDownPositions.get("" + n);
        }
        else {
            vector = new Vector<Object>();
        }
        vector.addElement(new Integer(n2));
        this.m_htUpDownPositions.put("" + n, vector);
    }
}
