// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui.text;

import java.util.Vector;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import com.objectbox.runner.util.JBLogger;
import java.util.StringTokenizer;
import java.awt.datatransfer.DataFlavor;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.datatransfer.Transferable;
import java.awt.Graphics;
import java.awt.AWTEventMulticaster;
import java.awt.datatransfer.Clipboard;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.ActionListener;
import java.awt.Point;
import java.awt.datatransfer.ClipboardOwner;

public class Text extends OBLabel implements Runnable, ClipboardOwner
{
    boolean AllowEnter;
    public String textModified;
    public String cursorMovedOutOfBound;
    public static String endEditing;
    protected Point cursorPoint;
    protected ActionListener actionListener;
    protected TextListener textListener;
    protected TextEvent textEvent;
    protected TextEvent textEventEnter;
    protected ActionEvent actionEventEnter;
    Point selectionStart;
    Point selectionEnd;
    transient Thread m_tCursor;
    Color m_cCursorColor;
    Point cursor;
    boolean m_bAllowHScroll;
    boolean m_bEditable;
    boolean m_bModified;
    boolean m_bBlinkCursor;
    transient Clipboard clipboard;
    int m_nCursorWidth;
    
    static {
        Text.endEditing = "End_Editing";
    }
    
    public Text() {
        this("");
    }
    
    public Text(final String s) {
        super(s);
        this.AllowEnter = false;
        this.textModified = "textModified";
        this.cursorMovedOutOfBound = "cursorMovedOutOfBound";
        this.textEvent = new TextEvent(this, 900);
        this.textEventEnter = new TextEvent(this, 900);
        this.actionEventEnter = new ActionEvent(this, 1001, Text.endEditing);
        this.m_bAllowHScroll = true;
        this.m_bEditable = true;
        this.m_bModified = false;
        this.m_bBlinkCursor = true;
        this.clipboard = new Clipboard("BlendTextComponentClipboard");
        this.m_nCursorWidth = 2;
        this.setBorderStyle(1);
        super.m_inTextInsets.top = 3;
        super.m_inTextInsets.left = 3;
        this.cursorPoint = new Point(0, 0);
        this.cursor = new Point(0, 0);
        this.m_cCursorColor = Color.black;
        this.selectionStart = new Point(0, 0);
        this.selectionEnd = new Point(0, 0);
        this.setAutoWrap(false);
        this.setInsets(super.m_inTextInsets);
        this.setAllowHScroll(true);
        this.enableEvents(16L);
        this.enableEvents(32L);
        this.enableEvents(4L);
        this.enableEvents(8L);
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.add(this.actionListener, actionListener);
    }
    
    public void addTextListener(final TextListener textListener) {
        this.textListener = AWTEventMulticaster.add(this.textListener, textListener);
    }
    
    protected void copy() {
        if (!this.isSelected()) {
            return;
        }
        this.setClipboardText(this.getSelectedText());
    }
    
    protected void cut(final boolean b) {
        if (!this.isSelected()) {
            return;
        }
        if (b) {
            this.setClipboardText(this.getSelectedText());
        }
        Point selectionStart = this.selectionStart;
        Point selectionEnd = this.selectionEnd;
        if (selectionEnd.y < selectionStart.y || (selectionStart.y == selectionEnd.y && selectionStart.x > selectionEnd.x)) {
            final Point point = selectionStart;
            selectionStart = selectionEnd;
            selectionEnd = point;
        }
        String text = new String();
        boolean b2 = true;
        for (int i = selectionStart.y; i <= selectionEnd.y; ++i) {
            final Paragraph paragraph = super.m_vParagraphs.elementAt(i);
            if (i == selectionStart.y) {
                final String text2 = paragraph.getText();
                if (selectionStart.x > text2.length()) {
                    b2 = false;
                    break;
                }
                text = String.valueOf(text) + text2.substring(0, selectionStart.x);
            }
            if (i == selectionEnd.y) {
                final String text3 = paragraph.getText();
                if (selectionEnd.x > text3.length()) {
                    b2 = false;
                    break;
                }
                text = String.valueOf(text) + text3.substring(selectionEnd.x);
            }
        }
        if (b2) {
            super.m_vParagraphs.elementAt(selectionStart.y).setText(text);
            for (int j = selectionStart.y + 1; j <= selectionEnd.y; ++j) {
                super.m_vParagraphs.removeElement(super.m_vParagraphs.elementAt(selectionStart.y + 1));
            }
        }
        this.unselectAll();
        this.start();
        this.cursorPoint.y = selectionStart.y;
        final Paragraph paragraph2 = super.m_vParagraphs.elementAt(selectionStart.y);
        paragraph2.moveSpecial(true, paragraph2.getCursorPoint().x, 0);
        paragraph2.setCursorPos(selectionStart.x);
        super.y_offset = super.prevYOffset;
        this.repaint();
    }
    
    public void draw(final Graphics graphics) {
        super.draw(graphics);
        if (!this.m_bBlinkCursor) {
            this.paintCursor(graphics);
        }
    }
    
    protected Paragraph findParagraph(int n, final boolean b) {
        int n2 = 0;
        n += super.y_offset;
        for (int i = 0; i < super.m_vParagraphs.size(); ++i) {
            final Paragraph paragraph = super.m_vParagraphs.elementAt(i);
            n2 += paragraph.getYSpan();
            if (n < n2) {
                return paragraph;
            }
        }
        return b ? super.m_vParagraphs.lastElement() : null;
    }
    
    protected int findYCoord(final Paragraph paragraph) {
        int top = super.m_inTextInsets.top;
        for (int i = 0; i < super.m_vParagraphs.size(); ++i) {
            final Paragraph paragraph2 = super.m_vParagraphs.elementAt(i);
            if (paragraph2 == paragraph) {
                break;
            }
            top += paragraph2.getYSpan();
        }
        return top;
    }
    
    public boolean getAllowHScroll() {
        return this.m_bAllowHScroll && this.getHAlign() == 1 && !this.isAutoWrap();
    }
    
    public Clipboard getClipboard() {
        return this.clipboard;
    }
    
    public int getCursorPos() {
        return super.m_vParagraphs.elementAt(this.cursorPoint.y).getCursorPos();
    }
    
    public int getCursorWidth() {
        return this.m_nCursorWidth;
    }
    
    public boolean getModified() {
        return this.m_bModified;
    }
    
    public String getSelectedText() {
        String s = new String();
        if (!this.isSelected()) {
            return s;
        }
        Point selectionStart = this.selectionStart;
        Point selectionEnd = this.selectionEnd;
        if (selectionEnd.y < selectionStart.y || (selectionStart.y == selectionEnd.y && selectionStart.x > selectionEnd.x)) {
            final Point point = selectionStart;
            selectionStart = selectionEnd;
            selectionEnd = point;
        }
        for (int i = selectionStart.y; i <= selectionEnd.y; ++i) {
            final Paragraph paragraph = super.m_vParagraphs.elementAt(i);
            if (i == selectionStart.y && selectionStart.y == selectionEnd.y) {
                s = String.valueOf(s) + paragraph.getText().substring(selectionStart.x, selectionEnd.x);
            }
            else {
                if (i == selectionStart.y) {
                    s = String.valueOf(s) + paragraph.getText().substring(selectionStart.x);
                }
                if (i == selectionEnd.y) {
                    s = String.valueOf(s) + paragraph.getText().substring(0, selectionEnd.x);
                }
                if (i != selectionStart.y && i != selectionEnd.y && i > selectionStart.y && i < selectionEnd.y) {
                    s = String.valueOf(s) + paragraph.getText();
                }
            }
            s = String.valueOf(s) + "\n";
        }
        return s.substring(0, s.length() - 1);
    }
    
    public int getSelectionEnd() {
        return Math.max(this.selectionStart.x, this.selectionEnd.x);
    }
    
    public int getSelectionStart() {
        return Math.min(this.selectionStart.x, this.selectionEnd.x);
    }
    
    protected void gotFocus() {
        if (this.m_bEditable) {
            this.start();
            for (int i = 0; i < super.m_vParagraphs.size(); ++i) {
                ((Paragraph)super.m_vParagraphs.elementAt(i)).setMarkVisible(true);
            }
            this.repaint();
        }
    }
    
    protected boolean insertChar(final char c) {
        if (c == '\0') {
            return false;
        }
        if (super.m_vParagraphs == null || this.cursorPoint.y < 0 || this.cursorPoint.y > super.m_vParagraphs.size() - 1) {
            return false;
        }
        if (this.isSelected()) {
            this.cut(false);
            this.start();
        }
        if (super.m_vParagraphs.elementAt(this.cursorPoint.y).insertChar(c, !this.m_bAllowHScroll && !this.isAutoWrap())) {
            this.processTextEvent(this.textEvent);
            this.update();
            return true;
        }
        return false;
    }
    
    public boolean isAllowEnter() {
        return false;
    }
    
    public boolean isBlinkCursor() {
        return this.m_bBlinkCursor;
    }
    
    public boolean isEditable() {
        return this.m_bEditable;
    }
    
    public boolean isFocusTraversable() {
        return this.m_bEditable;
    }
    
    public boolean isModified() {
        return this.m_bModified;
    }
    
    private boolean isNotBreakChar(final char c) {
        boolean b = true;
        switch (c) {
            case ' ':
            case '!':
            case ',':
            case '.':
            case ':':
            case ';':
            case '?': {
                b = false;
                break;
            }
        }
        return b;
    }
    
    public boolean isSelected() {
        for (int i = 0; i < super.m_vParagraphs.size(); ++i) {
            if (((Paragraph)super.m_vParagraphs.elementAt(i)).getSelectionState() != 0) {
                return true;
            }
        }
        return false;
    }
    
    protected void lostFocus() {
        if (this.m_bEditable) {
            this.stop();
            for (int i = 0; i < super.m_vParagraphs.size(); ++i) {
                ((Paragraph)super.m_vParagraphs.elementAt(i)).setMarkVisible(false);
            }
            this.processActionEvent(this.actionEventEnter);
            this.repaint();
        }
    }
    
    public void lostOwnership(final Clipboard clipboard, final Transferable transferable) {
    }
    
    protected void onBackspaceKey() {
        if (this.isSelected()) {
            this.cut(false);
            this.processTextEvent(this.textEvent);
            this.start();
        }
        else {
            final Paragraph paragraph = super.m_vParagraphs.elementAt(this.cursorPoint.y);
            if (this.getAllowHScroll() && paragraph.getCursorPos() == 0) {
                if (paragraph.getXOffset() > 0) {
                    paragraph.setXOffset(0);
                    this.repaint();
                }
                return;
            }
            if (paragraph.getCursorPos() == 0) {
                return;
            }
            paragraph.onBackspaceKey();
            this.processTextEvent(this.textEvent);
        }
        this.repaint();
    }
    
    protected void onDeleteKey() {
        if (this.isSelected()) {
            this.cut(false);
            this.processTextEvent(this.textEvent);
            this.start();
        }
        else {
            final Paragraph paragraph = super.m_vParagraphs.elementAt(this.cursorPoint.y);
            if (paragraph.isCursorAtEnd()) {
                return;
            }
            paragraph.onDeleteKey();
            this.processTextEvent(this.textEvent);
        }
        this.repaint();
    }
    
    protected void onDoubleClicked() {
        this.selectWord();
    }
    
    protected void onDownKey(final int n) {
    }
    
    protected void onEndKey(final int n) {
        final Paragraph paragraph = super.m_vParagraphs.elementAt(this.cursorPoint.y);
        final int cursorPos = paragraph.getCursorPos();
        this.stop();
        final String text = paragraph.getText();
        paragraph.setCursorPos(text.length());
        if (paragraph.getFontMetrics().stringWidth(text) > this.getSize().width) {
            paragraph.setXOffset(paragraph.getFontMetrics().stringWidth(text) - this.getSize().width + 15);
        }
        if (n == 1) {
            if (paragraph.getSelectionState() == 0) {
                paragraph.setSelectionState(1);
                paragraph.setSelectionStart(cursorPos);
                this.selectionStart.x = cursorPos;
            }
            this.selectionEnd.x = paragraph.getText().length();
            paragraph.setSelectionEnd(paragraph.getText().length());
        }
        else {
            if (paragraph.getSelectionState() != 0) {
                paragraph.setSelectionState(0);
            }
            this.start();
        }
        this.repaint();
    }
    
    protected void onEnterKey() {
        this.processTextEvent(this.textEventEnter);
        this.processActionEvent(this.actionEventEnter);
    }
    
    protected void onHomeKey(final int n) {
        final Paragraph paragraph = super.m_vParagraphs.elementAt(this.cursorPoint.y);
        final int cursorPos = paragraph.getCursorPos();
        this.stop();
        paragraph.setCursorPos(0);
        paragraph.setXOffset(0);
        if (n == 1) {
            if (paragraph.getSelectionState() == 0) {
                paragraph.setSelectionState(1);
                paragraph.setSelectionStart(cursorPos);
                this.selectionStart.x = cursorPos;
            }
            paragraph.setSelectionEnd(this.selectionEnd.x = 0);
        }
        else {
            if (paragraph.getSelectionState() != 0) {
                paragraph.setSelectionState(0);
            }
            this.start();
        }
        this.repaint();
    }
    
    protected void onLeftKey(final int n) {
        final Paragraph paragraph = super.m_vParagraphs.elementAt(this.cursorPoint.y);
        int cursorPos = paragraph.getCursorPos();
        if (cursorPos == 0) {
            if (paragraph.getXOffset() > 0) {
                this.setXOffset(0);
                this.repaint();
            }
            return;
        }
        paragraph.setCursorPos(--cursorPos);
        if (n == 1) {
            if (paragraph.getSelectionState() == 0) {
                paragraph.setSelectionState(1);
                paragraph.setSelectionStart(cursorPos + 1);
                this.selectionStart.x = cursorPos + 1;
            }
            paragraph.setSelectionEnd(this.selectionEnd.x = cursorPos);
        }
        else if (this.isSelected()) {
            paragraph.setSelectionState(0);
            paragraph.setCursorPos(Math.min(this.selectionStart.x, this.selectionEnd.x));
        }
        if (this.m_bAllowHScroll && this.getHAlign() == 1 && !this.isAutoWrap() && paragraph.getCursorPoint().x < super.m_inTextInsets.left + 2) {
            int xOffset = paragraph.getXOffset() - this.getSize().width / 2;
            if (xOffset < 0) {
                xOffset = 0;
            }
            paragraph.setXOffset(xOffset);
        }
        this.repaint();
    }
    
    protected void onMovement() {
        this.repaint();
    }
    
    protected void onRightKey(final int n) {
        final Paragraph paragraph = super.m_vParagraphs.elementAt(this.cursorPoint.y);
        int cursorPos = paragraph.getCursorPos();
        if (cursorPos == paragraph.getText().length()) {
            return;
        }
        this.stop();
        paragraph.setCursorPos(++cursorPos);
        if (this.m_bAllowHScroll) {
            paragraph.onScrollAction();
        }
        if (n == 1) {
            if (paragraph.getSelectionState() == 0) {
                paragraph.setSelectionState(1);
                paragraph.setSelectionStart(cursorPos - 1);
                this.selectionStart.x = cursorPos - 1;
                if (this.m_bAllowHScroll) {
                    paragraph.setPrevXOffset(paragraph.getXOffset());
                }
            }
            paragraph.setSelectionEnd(this.selectionEnd.x = cursorPos);
        }
        else {
            if (paragraph.getSelectionState() != 0) {
                paragraph.setSelectionState(0);
                paragraph.setCursorPos(Math.max(this.selectionStart.x, this.selectionEnd.x));
            }
            this.start();
        }
        this.repaint();
    }
    
    protected void onTripleClicked() {
        this.selectAll();
    }
    
    protected void onUpKey(final int n) {
    }
    
    protected void paintCursor(final Graphics graphics) {
        if (super.m_vParagraphs == null || this.cursorPoint.y < 0 || this.cursorPoint.y > super.m_vParagraphs.size() - 1) {
            return;
        }
        final Paragraph paragraph = super.m_vParagraphs.elementAt(this.cursorPoint.y);
        final Point cursorPoint = paragraph.getCursorPoint();
        if (super.y_offset == 0 && super.m_nVAlign != 1) {
            final int height = this.getSize().height;
            int n = 0;
            for (int i = 0; i < super.m_vParagraphs.size(); ++i) {
                n += ((Paragraph)super.m_vParagraphs.elementAt(i)).getYSpan();
            }
            if (super.m_nVAlign == 3) {
                final Point point = cursorPoint;
                point.y += height / 2 - n / 2;
            }
            else if (super.m_nVAlign == 2) {
                final Point point2 = cursorPoint;
                point2.y += height - n - super.m_inTextInsets.bottom;
            }
        }
        if (super.m_nVAlign == 1) {
            final Point point3 = cursorPoint;
            point3.y += super.m_inTextInsets.top;
        }
        final Point point4 = cursorPoint;
        point4.y -= super.y_offset;
        this.getSize();
        for (int j = 0; j < this.cursorPoint.y; ++j) {
            final Paragraph paragraph2 = super.m_vParagraphs.elementAt(j);
            if (paragraph2.isVisible()) {
                final Point point5 = cursorPoint;
                point5.y += paragraph2.getYSpan();
            }
        }
        final Point point6 = cursorPoint;
        point6.x += 2;
        Font font = paragraph.getFont();
        if (font == null) {
            font = this.getFont();
        }
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        graphics.setColor(this.m_cCursorColor);
        graphics.fillRect(cursorPoint.x, cursorPoint.y, this.m_nCursorWidth, fontMetrics.getHeight());
    }
    
    protected void paintCursor(final boolean b) {
        if (super.m_vParagraphs == null || this.cursorPoint.y < 0 || this.cursorPoint.y > super.m_vParagraphs.size() - 1) {
            return;
        }
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        final Paragraph paragraph = super.m_vParagraphs.elementAt(this.cursorPoint.y);
        final Point cursorPoint = paragraph.getCursorPoint();
        if (super.y_offset == 0 && super.m_nVAlign != 1) {
            final int height = this.getSize().height;
            int n = 0;
            for (int i = 0; i < super.m_vParagraphs.size(); ++i) {
                n += ((Paragraph)super.m_vParagraphs.elementAt(i)).getYSpan();
            }
            if (super.m_nVAlign == 3) {
                final Point point = cursorPoint;
                point.y += height / 2 - n / 2;
            }
            else if (super.m_nVAlign == 2) {
                final Point point2 = cursorPoint;
                point2.y += height - n - super.m_inTextInsets.bottom;
            }
        }
        if (super.m_nVAlign == 1) {
            final Point point3 = cursorPoint;
            point3.y += super.m_inTextInsets.top;
        }
        final Point point4 = cursorPoint;
        point4.y -= super.y_offset;
        this.getSize();
        for (int j = 0; j < this.cursorPoint.y; ++j) {
            final Paragraph paragraph2 = super.m_vParagraphs.elementAt(j);
            if (paragraph2.isVisible()) {
                final Point point5 = cursorPoint;
                point5.y += paragraph2.getYSpan();
            }
        }
        final Point point6 = cursorPoint;
        point6.x += 2;
        graphics.setXORMode(this.getBackground());
        Font font = paragraph.getFont();
        if (font == null) {
            font = this.getFont();
        }
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        graphics.setColor(this.m_cCursorColor);
        graphics.fillRect(cursorPoint.x, cursorPoint.y, this.m_nCursorWidth, fontMetrics.getHeight());
        graphics.setPaintMode();
    }
    
    protected void paste() {
        final Transferable contents = this.clipboard.getContents(this);
        if (this.isSelected()) {
            this.cut(false);
            this.unselectAll();
            this.start();
        }
        if (contents != null) {
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer((String)contents.getTransferData(DataFlavor.stringFlavor), "\n", false);
                final String nextToken = stringTokenizer.nextToken();
                Paragraph paragraph = super.m_vParagraphs.elementAt(this.cursorPoint.y);
                final int cursorPos = paragraph.getCursorPos();
                final String text = paragraph.getText();
                final String substring = text.substring(0, cursorPos);
                final String substring2 = text.substring(cursorPos);
                paragraph.setText(String.valueOf(substring) + nextToken);
                paragraph.setCursorPos(cursorPos + nextToken.length());
                while (stringTokenizer.hasMoreTokens()) {
                    paragraph = new Paragraph(this, stringTokenizer.nextToken());
                    paragraph.setFont(this.getFont());
                    paragraph.setFontMetrics(this.getFontMetrics(this.getFont()));
                    paragraph.setWidth(this.getSize().width - super.m_nWidthOffset);
                    paragraph.setForeground(this.getForeground());
                    paragraph.setBackground(this.getBackground());
                    paragraph.setInsets(super.m_inTextInsets);
                    super.m_vParagraphs.insertElementAt(paragraph, this.cursorPoint.y + 1);
                    final Point cursorPoint = this.cursorPoint;
                    ++cursorPoint.y;
                }
                paragraph.setText(String.valueOf(paragraph.getText()) + substring2);
                paragraph.setCursorPos(paragraph.getText().length() - substring2.length());
                this.processTextEvent(this.textEvent);
                this.repaint();
            }
            catch (Exception ex) {
                JBLogger.log("Couldn't get contents in format: " + DataFlavor.stringFlavor.getHumanPresentableName());
            }
        }
    }
    
    protected void processActionEvent(final ActionEvent actionEvent) {
        if (this.actionListener != null) {
            this.actionListener.actionPerformed(actionEvent);
        }
    }
    
    protected void processFocusEvent(final FocusEvent focusEvent) {
        super.processFocusEvent(focusEvent);
        if (focusEvent.getID() == 1004) {
            this.gotFocus();
        }
        else if (focusEvent.getID() == 1005) {
            this.lostFocus();
        }
    }
    
    protected void processKeyEvent(final KeyEvent keyEvent) {
        super.processKeyEvent(keyEvent);
        if (keyEvent.isConsumed() || !this.m_bEditable) {
            return;
        }
        if (keyEvent.getID() == 401) {
            final int keyCode = keyEvent.getKeyCode();
            if (keyCode != 27) {
                if (keyCode == 9) {
                    this.transferFocus();
                }
                else if (keyCode == 37) {
                    this.onLeftKey(keyEvent.getModifiers());
                }
                else if (keyCode == 39) {
                    this.onRightKey(keyEvent.getModifiers());
                }
                else if (keyCode == 38) {
                    this.onUpKey(keyEvent.getModifiers());
                }
                else if (keyCode == 40) {
                    this.onDownKey(keyEvent.getModifiers());
                }
                else if (keyCode == 36) {
                    this.onHomeKey(keyEvent.getModifiers());
                }
                else if (keyCode == 35) {
                    this.onEndKey(keyEvent.getModifiers());
                }
                else if (keyCode == 8) {
                    this.onBackspaceKey();
                }
                else if (keyCode == 127) {
                    this.onDeleteKey();
                }
                else if (keyCode == 10) {
                    this.onEnterKey();
                }
                else if (keyCode != 33 && keyCode != 34) {
                    if (keyEvent.getModifiers() == 2 && keyCode == 67) {
                        this.copy();
                    }
                    else if (keyEvent.getModifiers() == 2 && keyCode == 88) {
                        this.cut(true);
                    }
                    else if (keyEvent.getModifiers() == 2 && keyCode == 86) {
                        this.paste();
                    }
                    else {
                        this.insertChar(keyEvent.getKeyChar());
                    }
                }
            }
        }
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (!this.m_bEditable) {
            return;
        }
        final Point point = mouseEvent.getPoint();
        if (mouseEvent.getID() == 501) {
            this.requestFocus();
            this.setCursorPosition(point.x, point.y);
            this.unselectAll();
            this.repaint();
            try {
                this.start();
            }
            catch (Exception ex) {}
            if (mouseEvent.getClickCount() == 2) {
                this.onDoubleClicked();
            }
            else if (mouseEvent.getClickCount() == 3) {
                this.onTripleClicked();
            }
        }
    }
    
    protected void processMouseMotionEvent(final MouseEvent mouseEvent) {
        super.processMouseMotionEvent(mouseEvent);
        if (!this.m_bEditable) {
            return;
        }
        final Point point = mouseEvent.getPoint();
        if (mouseEvent.getID() == 506) {
            this.stop();
            final Paragraph paragraph = this.findParagraph(point.y, true);
            final Point point2 = point;
            point2.x -= super.m_inTextInsets.left;
            this.selectionEnd.y = super.m_vParagraphs.indexOf(paragraph);
            final Point point3 = point;
            point3.y += super.y_offset;
            for (int i = 0; i < this.selectionEnd.y; ++i) {
                final Point point4 = point;
                point4.y -= super.m_vParagraphs.elementAt(i).getYSpan();
            }
            if (super.y_offset == 0 && super.m_nVAlign != 1) {
                final int height = this.getSize().height;
                int n = 0;
                for (int j = 0; j < super.m_vParagraphs.size(); ++j) {
                    n += ((Paragraph)super.m_vParagraphs.elementAt(j)).getYSpan();
                }
                if (super.m_nVAlign == 3) {
                    final Point point5 = point;
                    point5.y -= (height - n) / 2;
                }
                else if (super.m_nVAlign == 2) {
                    final Point point6 = point;
                    point6.y -= height - n;
                }
            }
            paragraph.setCursorPos(this.selectionEnd.x = paragraph.getCursorPosFromPoint(point));
            this.cursorPoint.y = this.selectionEnd.y;
            this.cursorPoint.x = this.selectionEnd.x;
            if (this.m_bAllowHScroll) {
                paragraph.onScrollAction();
            }
            Point point7 = new Point(this.selectionStart);
            Point point8 = new Point(this.selectionEnd);
            if (point8.y < point7.y || (point8.y == point7.y && point8.x < point7.x)) {
                final Point point9 = new Point(point7);
                point7 = point8;
                point8 = point9;
            }
            for (int k = 0; k < super.m_vParagraphs.size(); ++k) {
                final Paragraph paragraph2 = super.m_vParagraphs.elementAt(k);
                if (k < point7.y || k > point8.y) {
                    paragraph2.setSelectionState(0);
                }
                else if (point7.y == point8.y) {
                    paragraph2.setSelectionState(1);
                    paragraph2.setSelectionStart(point7.x);
                    paragraph2.setSelectionEnd(point8.x);
                }
                else {
                    for (int l = point7.y; l <= point8.y; ++l) {
                        final Paragraph paragraph3 = super.m_vParagraphs.elementAt(l);
                        if (l == point7.y) {
                            paragraph3.setSelectionState(1);
                            paragraph3.setSelectionStart(point7.x);
                            paragraph3.setSelectionEnd(paragraph3.getText().length());
                        }
                        else if (l == point8.y) {
                            paragraph3.setSelectionState(1);
                            paragraph3.setSelectionStart(0);
                            paragraph3.setSelectionEnd(point8.x);
                        }
                        else {
                            paragraph3.setSelectionState(2);
                        }
                    }
                    k = point8.y;
                }
            }
            this.repaint();
        }
    }
    
    protected void processTextEvent(final TextEvent textEvent) {
        if (textEvent.equals(this.textEvent)) {
            this.setModified(true);
        }
        if (this.textListener != null) {
            this.textListener.textValueChanged(textEvent);
        }
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.remove(this.actionListener, actionListener);
    }
    
    public void removeTextListener(final TextListener textListener) {
        this.textListener = AWTEventMulticaster.remove(this.textListener, textListener);
    }
    
    public void run() {
        boolean b = true;
        while (this.m_bBlinkCursor) {
            this.paintCursor(b);
            b = !b;
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {
                this.stop();
            }
        }
    }
    
    public void select(final int selectionStart, final int n) {
        if (super.m_vParagraphs == null || super.m_vParagraphs.size() == 0) {
            return;
        }
        this.stop();
        this.selectionStart = new Point(selectionStart, 0);
        this.selectionEnd = new Point(n, 0);
        final Paragraph paragraph = super.m_vParagraphs.firstElement();
        paragraph.setSelectionStart(selectionStart);
        paragraph.setSelectionEnd(n);
        paragraph.setCursorPos(n);
        if (selectionStart == n) {
            paragraph.setSelectionState(0);
            this.start();
        }
        else {
            paragraph.setSelectionState(1);
        }
        this.repaint();
    }
    
    public void selectAll() {
        this.stop();
        this.selectionStart = new Point(0, 0);
        this.selectionEnd = new Point(super.m_vParagraphs.lastElement().getText().length(), super.m_vParagraphs.size() - 1);
        for (int i = 0; i < super.m_vParagraphs.size(); ++i) {
            final Paragraph paragraph = super.m_vParagraphs.elementAt(i);
            paragraph.setSelectionState(2);
            paragraph.setMarkVisible(true);
        }
        this.repaint();
    }
    
    public void selectWord() {
        final Paragraph paragraph = super.m_vParagraphs.elementAt(this.cursorPoint.y);
        final String text = paragraph.getText();
        if (text.length() == 0) {
            return;
        }
        this.stop();
        int x = this.cursorPoint.x;
        int x2 = this.cursorPoint.x;
        if (x2 == 0) {
            x2 = 1;
        }
        if (x > text.length()) {
            return;
        }
        while (x > 0 && this.isNotBreakChar(text.charAt(x - 1))) {
            --x;
        }
        while (x2 < text.length() && this.isNotBreakChar(text.charAt(x2))) {
            ++x2;
        }
        this.selectionStart = new Point(x, this.cursorPoint.y);
        this.selectionEnd = new Point(x2, this.cursorPoint.y);
        paragraph.setSelectionState(1);
        paragraph.setSelectionStart(x);
        paragraph.setSelectionEnd(x2);
        this.repaint();
    }
    
    public void setAllowEnter(final boolean b) {
    }
    
    public void setAllowHScroll(final boolean b) {
        this.m_bAllowHScroll = (b && this.getHAlign() == 1 && !this.isAutoWrap());
        for (int i = 0; i < super.m_vParagraphs.size(); ++i) {
            ((Paragraph)super.m_vParagraphs.elementAt(i)).setAllowScroll(this.m_bAllowHScroll);
        }
    }
    
    public void setBlinkCursor(final boolean bBlinkCursor) {
        this.m_bBlinkCursor = bBlinkCursor;
    }
    
    public void setClipboard(final Clipboard clipboard) {
        this.clipboard = clipboard;
    }
    
    protected void setClipboardText(final String s) {
        if (s != null) {
            this.clipboard.setContents(new StringSelection(s), this);
        }
    }
    
    public void setCursorPos(final int n) {
        super.m_vParagraphs.elementAt(this.cursorPoint.y).setCursorPos(n);
        this.cursorPoint.x = n;
    }
    
    protected void setCursorPosition(final int n, int n2) {
        n2 -= super.m_inTextInsets.top;
        if (super.y_offset == 0 && super.m_nVAlign != 1) {
            final int height = this.getSize().height;
            int n3 = 0;
            for (int i = 0; i < super.m_vParagraphs.size(); ++i) {
                n3 += ((Paragraph)super.m_vParagraphs.elementAt(i)).getYSpan();
            }
            if (super.m_nVAlign == 3) {
                n2 -= (height - n3) / 2;
            }
            else if (super.m_nVAlign == 2) {
                n2 -= height - n3;
            }
        }
        final Point point = new Point(n, n2);
        final Paragraph paragraph = this.findParagraph(point.y, true);
        this.cursorPoint.y = super.m_vParagraphs.indexOf(paragraph);
        final Point point2 = point;
        point2.y -= super.m_inTextInsets.top;
        final Point point3 = point;
        point3.y += super.y_offset;
        for (int j = 0; j < this.cursorPoint.y; ++j) {
            final Point point4 = point;
            point4.y -= super.m_vParagraphs.elementAt(j).getYSpan();
        }
        paragraph.setCursorPos(this.cursorPoint.x = paragraph.getCursorPosFromPoint(point));
        this.selectionStart.x = this.cursorPoint.x;
        this.selectionStart.y = this.cursorPoint.y;
        this.selectionEnd.x = this.cursorPoint.x;
        this.selectionEnd.y = this.cursorPoint.y;
    }
    
    public void setCursorWidth(final int nCursorWidth) {
        if (nCursorWidth < 0) {
            return;
        }
        this.m_nCursorWidth = nCursorWidth;
    }
    
    public void setEditable(final boolean bEditable) {
        this.m_bEditable = bEditable;
    }
    
    public void setHAlign(final int hAlign) {
        if (hAlign != 1) {
            this.setAllowHScroll(false);
        }
        super.setHAlign(hAlign);
    }
    
    public void setModified(final boolean bModified) {
        this.m_bModified = bModified;
    }
    
    public void setText(String s, final boolean b) {
        if (s == null) {
            s = "";
        }
        if (super.m_vParagraphs.size() > 0) {
            super.m_vParagraphs.removeAllElements();
        }
        final Paragraph paragraph = new Paragraph(this, s);
        paragraph.setFont(this.getFont());
        paragraph.setFontMetrics(this.getFontMetrics(this.getFont()));
        paragraph.setBackground(this.getBackground());
        paragraph.setForeground(this.getForeground());
        paragraph.setWidth(this.getSize().width - super.m_nWidthOffset);
        paragraph.setInsets(super.m_inTextInsets);
        paragraph.setHAlign(this.getHAlign());
        paragraph.setAutoWrap(this.isAutoWrap());
        paragraph.setAllowScroll(this.getAllowHScroll());
        super.m_vParagraphs.addElement(paragraph);
        if (b) {
            this.repaint();
        }
    }
    
    public void setXOffset(final int xOffset) {
        super.m_vParagraphs.elementAt(this.cursorPoint.y).setXOffset(xOffset);
    }
    
    public void start() {
        if (this == null) {
            return;
        }
        if (this.m_tCursor == null) {
            (this.m_tCursor = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.m_tCursor != null) {
            this.m_tCursor.stop();
            this.m_tCursor = null;
        }
    }
    
    protected void unselectAll() {
        for (int i = 0; i < super.m_vParagraphs.size(); ++i) {
            ((Paragraph)super.m_vParagraphs.elementAt(i)).setSelectionState(0);
        }
    }
    
    public void update() {
        this.repaint();
    }
    
    public static Vector wrapText(final String s, final int n, final boolean b, final FontMetrics fontMetrics) {
        final Vector<String> vector = new Vector<String>();
        int n2 = 0;
        int n3 = 0;
        if (b) {
            for (int i = 0; i < s.length(); ++i) {
                n3 += fontMetrics.charWidth(s.charAt(i));
                if (s.charAt(i) == '\n' || n3 > n) {
                    int n4;
                    for (n4 = i; n4 > n2 && s.charAt(n4) != '\n' && s.charAt(n4) != ' '; --n4) {}
                    if (n4 == n2) {
                        n4 = i;
                    }
                    if (n4 != n2) {
                        vector.addElement(s.substring(n2, n4));
                    }
                    n2 = ((s.charAt(n4) == '\n' || s.charAt(n4) == ' ') ? (n4 + 1) : n4);
                    if (n2 < i) {
                        n3 = fontMetrics.stringWidth(s.substring(n2, i + 1));
                    }
                    else {
                        n3 = fontMetrics.charWidth(s.charAt(i));
                    }
                }
            }
        }
        else {
            for (int j = 0; j < s.length(); ++j) {
                if (s.charAt(j) == '\n') {
                    vector.addElement(s.substring(n2, j));
                    n2 = j + 1;
                }
            }
        }
        if (n2 < s.length()) {
            vector.addElement(s.substring(n2));
        }
        else {
            vector.addElement(new String());
        }
        return vector;
    }
}
