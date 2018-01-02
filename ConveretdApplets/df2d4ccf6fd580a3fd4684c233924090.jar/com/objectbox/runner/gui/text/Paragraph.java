// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui.text;

import java.awt.Graphics;
import java.awt.Component;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Vector;
import java.io.Serializable;

public class Paragraph implements Serializable
{
    public static final int LEFT = 1;
    public static final int CENTER = 3;
    public static final int RIGHT = 2;
    protected static final int NONE = 0;
    protected static final int PARTIAL = 1;
    protected static final int COMPLETE = 2;
    private int x_offset;
    private int prevXOffset;
    private boolean m_bAllowScroll;
    private boolean m_bAutoWrap;
    StringBuffer buffer;
    Vector lines;
    transient FontMetrics fontMetrics;
    Font m_fFont;
    Color m_cHighlight;
    Color m_cTextHighlight;
    Color m_cBackground;
    Color m_cForeground;
    Insets m_inInsets;
    boolean m_bVisible;
    boolean m_bMarkVisible;
    int cursorPos;
    int currentLine;
    int m_nYSpan;
    int m_nFirstLine;
    int m_nHAlign;
    int leftOffset;
    int m_nWidth;
    int selectionStart;
    int selectionEnd;
    int selected;
    IScrollable target;
    
    public Paragraph() {
        this("");
    }
    
    public Paragraph(final IScrollable scrollable) {
        this(scrollable, "");
    }
    
    public Paragraph(final IScrollable target, final String s) {
        this(s);
        this.target = target;
    }
    
    public Paragraph(final String s) {
        this.x_offset = 0;
        this.prevXOffset = 0;
        this.m_bAllowScroll = false;
        this.m_bAutoWrap = false;
        this.m_cHighlight = SystemColor.textHighlight;
        this.m_cTextHighlight = SystemColor.textHighlightText;
        this.m_cBackground = Color.white;
        this.m_cForeground = Color.black;
        this.m_bMarkVisible = true;
        this.m_nHAlign = 1;
        this.leftOffset = 10;
        this.m_nWidth = 0;
        this.selected = 0;
        this.cursorPos = 0;
        this.currentLine = 0;
        this.m_nFirstLine = 0;
        this.m_bVisible = true;
        this.m_fFont = null;
        this.lines = new Vector();
        this.buffer = new StringBuffer(s);
        this.lines.addElement(this.buffer.toString());
        this.m_inInsets = new Insets(3, 3, 3, 3);
    }
    
    public void addNotify() {
        this.onTextModified();
    }
    
    public Color getBackground() {
        return this.m_cBackground;
    }
    
    protected Point getCursorPoint() {
        if (this.fontMetrics == null) {
            if (this.m_fFont == null) {
                return null;
            }
            try {
                this.fontMetrics = ((Component)this.getTarget()).getFontMetrics(this.m_fFont);
            }
            catch (Exception ex) {
                return null;
            }
        }
        final Point point = new Point(0, 0);
        int n = 0;
        for (int i = 0; i < this.lines.size(); ++i) {
            final String s = this.lines.elementAt(i);
            int length = s.length();
            if (length > 0 && i < this.lines.size() - 1 && s.charAt(length - 1) == ' ') {
                --length;
            }
            final int n2 = length + n;
            if (this.cursorPos >= n && this.cursorPos <= n2) {
                final String substring = s.substring(0, this.cursorPos - n);
                point.x = this.fontMetrics.stringWidth(substring) + this.m_inInsets.left;
                if (this.x_offset <= 0 && this.m_nHAlign != 1) {
                    if (this.m_nHAlign == 3) {
                        point.x = this.m_nWidth / 2 - this.fontMetrics.stringWidth(s) / 2 + this.fontMetrics.stringWidth(substring) - 4;
                    }
                    else if (this.m_nHAlign == 2) {
                        if (this.fontMetrics.stringWidth(substring) > this.m_nWidth) {
                            point.x = this.m_inInsets.left + this.fontMetrics.stringWidth(substring) + 2;
                        }
                        else {
                            point.x = this.m_nWidth - 2 - this.m_inInsets.right - this.fontMetrics.stringWidth(s) + this.fontMetrics.stringWidth(substring);
                        }
                    }
                }
                final Point point2 = point;
                point2.x -= this.x_offset;
                break;
            }
            n += s.length();
            final Point point3 = point;
            point3.y += this.fontMetrics.getHeight();
        }
        return point;
    }
    
    protected int getCursorPos() {
        return this.cursorPos;
    }
    
    public int getCursorPosFromPoint(final Point point) {
        if (this.fontMetrics == null) {
            if (this.m_fFont == null) {
                return 0;
            }
            try {
                this.fontMetrics = ((Component)this.getTarget()).getFontMetrics(this.m_fFont);
            }
            catch (Exception ex) {
                return 0;
            }
        }
        if (point.y > this.getYSpan()) {
            return this.buffer.length();
        }
        int n = -1;
        int n2 = 0;
        for (int i = 0; i < this.lines.size(); ++i) {
            if ((i + 1) * this.fontMetrics.getHeight() > point.y) {
                n = i;
                break;
            }
            n2 += ((String)this.lines.elementAt(i)).length();
        }
        if (n == -1) {
            n = this.lines.size() - 1;
        }
        return n2 + this.getPositionAt(this.lines.elementAt(n), point.x);
    }
    
    protected int getFirstLine() {
        return this.m_nFirstLine;
    }
    
    public Font getFont() {
        return this.m_fFont;
    }
    
    public FontMetrics getFontMetrics() {
        return this.fontMetrics;
    }
    
    public Color getForeground() {
        return this.m_cForeground;
    }
    
    protected Color getHighlight() {
        return this.m_cHighlight;
    }
    
    protected Color getHighlightedText() {
        return this.m_cTextHighlight;
    }
    
    public Insets getInsets() {
        return this.m_inInsets;
    }
    
    protected Vector getLines() {
        return this.lines;
    }
    
    protected boolean getMarkVisible() {
        return this.m_bMarkVisible;
    }
    
    protected int getPositionAt(final String s, int n) {
        if (this.fontMetrics == null) {
            if (this.m_fFont == null) {
                return 0;
            }
            try {
                this.fontMetrics = ((Component)this.getTarget()).getFontMetrics(this.m_fFont);
            }
            catch (Exception ex) {
                return 0;
            }
        }
        if (this.x_offset <= 0 && this.m_nHAlign != 1) {
            final int nWidth = this.m_nWidth;
            final int stringWidth = this.fontMetrics.stringWidth(s);
            if (this.m_nHAlign == 3) {
                n -= (nWidth - stringWidth) / 2;
            }
            else if (this.m_nHAlign == 2) {
                n -= nWidth - stringWidth;
            }
        }
        n += this.x_offset;
        if (n == 0) {
            return n;
        }
        if (this.fontMetrics.stringWidth(s) <= n) {
            int length = s.length();
            if (this.lines.indexOf(s) < this.lines.size() - 1 && s.charAt(length - 1) == ' ') {
                --length;
            }
            return length;
        }
        int n2;
        for (n2 = 0; n2 < s.length() - 1 && this.fontMetrics.stringWidth(s.substring(0, n2 + 1)) <= n; ++n2) {}
        return n2;
    }
    
    protected int getPrevXOffset() {
        return this.prevXOffset;
    }
    
    protected int getSelectionEnd() {
        return this.selectionEnd;
    }
    
    protected int getSelectionStart() {
        return this.selectionStart;
    }
    
    protected int getSelectionState() {
        return this.selected;
    }
    
    public IScrollable getTarget() {
        return this.target;
    }
    
    protected String getText() {
        return this.buffer.toString();
    }
    
    public int getWidth() {
        return this.m_nWidth;
    }
    
    protected int getXCoord() {
        return this.cursorPos;
    }
    
    protected int getXOffset() {
        return this.x_offset;
    }
    
    protected int getYSpan() {
        if (this.fontMetrics == null) {
            if (this.m_fFont == null) {
                return 0;
            }
            try {
                this.fontMetrics = ((Component)this.getTarget()).getFontMetrics(this.m_fFont);
            }
            catch (Exception ex) {
                return 0;
            }
        }
        return this.fontMetrics.getHeight() * this.lines.size();
    }
    
    protected boolean insertChar(final char c, final boolean b) {
        if (this.fontMetrics == null) {
            if (this.m_fFont == null) {
                return false;
            }
            try {
                this.fontMetrics = ((Component)this.getTarget()).getFontMetrics(this.m_fFont);
            }
            catch (Exception ex) {
                return false;
            }
        }
        if (this.cursorPos > this.buffer.length()) {
            return false;
        }
        if (!b) {
            this.buffer.insert(this.cursorPos++, c);
            this.onTextModified();
            return true;
        }
        final StringBuffer sb = new StringBuffer(this.buffer.toString());
        sb.insert(this.cursorPos, c);
        if (this.fontMetrics.stringWidth(sb.toString()) <= this.m_nWidth - 4) {
            this.buffer.insert(this.cursorPos++, c);
            this.onTextModified();
            return true;
        }
        return false;
    }
    
    protected boolean isAllowScroll() {
        return this.m_bAllowScroll;
    }
    
    public boolean isAutoWrap() {
        return this.m_bAutoWrap;
    }
    
    protected boolean isCursorAtEnd() {
        return this.cursorPos == this.buffer.length();
    }
    
    protected boolean isCursorOnFirstLine() {
        final String s = this.lines.firstElement();
        int length = s.length();
        if (length > 0 && this.lines.size() > 1 && s.charAt(length - 1) == ' ') {
            --length;
        }
        return this.cursorPos <= length;
    }
    
    protected boolean isCursorOnLastLine() {
        if (this.lines.size() == 1) {
            return true;
        }
        int n = 0;
        for (int i = 0; i < this.lines.size() - 1; ++i) {
            n += ((String)this.lines.elementAt(i)).length();
        }
        return this.cursorPos > n;
    }
    
    protected boolean isVisible() {
        return this.m_bVisible;
    }
    
    protected void moveSpecial(final boolean b, final int n, final int n2) {
        if (this.fontMetrics == null) {
            if (this.m_fFont == null) {
                return;
            }
            try {
                this.fontMetrics = ((Component)this.getTarget()).getFontMetrics(this.m_fFont);
            }
            catch (Exception ex) {
                return;
            }
        }
        final String s = b ? this.lines.lastElement() : this.lines.firstElement();
        int n3 = 0;
        if (b) {
            for (int i = 0; i < this.lines.size() - 1; ++i) {
                n3 += ((String)this.lines.elementAt(i)).length();
            }
        }
        if (this.isAllowScroll()) {
            final int stringWidth = this.fontMetrics.stringWidth(s);
            if (b && (n2 == 37 || n2 == 35)) {
                if (stringWidth + this.m_inInsets.right + this.m_inInsets.left > n && this.getTarget() != null) {
                    this.getTarget().setXOffset(Math.max(0, stringWidth - n + this.m_inInsets.right + this.m_inInsets.left + 6));
                }
            }
            else if (!b && (n2 == 39 || n2 == 36)) {
                if (this.x_offset > 0 && this.getTarget() != null) {
                    this.getTarget().setXOffset(0);
                }
            }
            else if (this.x_offset > stringWidth && this.getTarget() != null) {
                this.getTarget().setXOffset(Math.max(0, stringWidth - n + this.m_inInsets.right + this.m_inInsets.left + 6));
            }
        }
        this.cursorPos = n3 + this.getPositionAt(s, n);
    }
    
    protected void onBackspaceKey() {
        if (this.fontMetrics == null) {
            if (this.m_fFont == null) {
                return;
            }
            try {
                this.fontMetrics = ((Component)this.getTarget()).getFontMetrics(this.m_fFont);
            }
            catch (Exception ex) {
                return;
            }
        }
        final String string = this.buffer.toString();
        String s = string.substring(0, this.cursorPos - 1);
        if (this.cursorPos < string.length()) {
            s = String.valueOf(s) + string.substring(this.cursorPos);
        }
        this.buffer = new StringBuffer(s);
        --this.cursorPos;
        this.onTextModified();
        if (this.isAllowScroll()) {
            final int stringWidth = this.fontMetrics.stringWidth(s);
            if (this.getCursorPoint().x <= this.m_inInsets.left && this.getTarget() != null) {
                final int max = Math.max(0, stringWidth - 3 * this.m_nWidth / 2 + this.m_inInsets.left + this.m_inInsets.right);
                this.setXOffset(max);
                this.getTarget().setXOffset(max);
            }
        }
    }
    
    protected void onDeleteKey() {
        final String string = this.buffer.toString();
        if (this.cursorPos < string.length()) {
            this.buffer = new StringBuffer(String.valueOf(string.substring(0, this.cursorPos)) + string.substring(this.cursorPos + 1));
            this.onTextModified();
        }
    }
    
    protected void onDownKey() {
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < this.lines.size(); ++i) {
            final String s = this.lines.elementAt(i);
            int length = s.length();
            if (length > 0 && i < this.lines.size() - 1 && s.charAt(length - 1) == ' ') {
                --length;
            }
            final int n3 = length + n;
            n += s.length();
            if (this.cursorPos <= n3) {
                n2 = i + 1;
                break;
            }
        }
        if (n2 >= this.lines.size()) {
            return;
        }
        this.cursorPos = n + this.getPositionAt((String)this.lines.elementAt(n2), this.getCursorPoint().x);
    }
    
    protected void onEndKey() {
        if (this.isAllowScroll()) {
            this.moveSpecial(true, this.m_nWidth + this.m_inInsets.right + this.m_inInsets.left, 35);
        }
        int n = 0;
        for (int i = 0; i < this.lines.size(); ++i) {
            final String s = this.lines.elementAt(i);
            int length = s.length();
            if (length > 0 && i < this.lines.size() - 1 && s.charAt(length - 1) == ' ') {
                --length;
            }
            final int cursorPos = length + n;
            if (this.cursorPos >= n && this.cursorPos <= cursorPos) {
                this.cursorPos = cursorPos;
                break;
            }
            n += s.length();
        }
    }
    
    protected String onEnterKey() {
        if (this.cursorPos == this.buffer.length()) {
            return null;
        }
        final String string = this.buffer.toString();
        this.buffer = new StringBuffer(string.substring(0, this.cursorPos));
        this.onTextModified();
        return string.substring(this.cursorPos);
    }
    
    protected void onHomeKey() {
        if (this.isAllowScroll()) {
            this.moveSpecial(false, this.m_nWidth, 36);
        }
        int cursorPos = 0;
        for (int i = 0; i < this.lines.size(); ++i) {
            final String s = this.lines.elementAt(i);
            int length = s.length();
            if (length > 0 && i < this.lines.size() - 1 && s.charAt(length - 1) == ' ') {
                --length;
            }
            if (this.cursorPos <= length + cursorPos) {
                this.cursorPos = cursorPos;
                break;
            }
            cursorPos += s.length();
        }
    }
    
    protected void onScrollAction() {
        if (!this.m_bAllowScroll) {
            return;
        }
        if (this.m_nWidth > 0) {
            final int n = this.getCursorPoint().x + 2;
            if (n > this.m_nWidth) {
                this.x_offset += n - this.m_nWidth;
            }
            else if (n < 2) {
                this.x_offset -= 2 - n;
            }
            if (this.isCursorAtEnd() && this.m_nHAlign == 2 && this.x_offset > 0 && n < this.m_nWidth - 2) {
                this.x_offset -= this.m_nWidth - 2 - n;
                if (this.x_offset < 0) {
                    this.x_offset = 0;
                }
            }
            final IScrollable target = this.getTarget();
            if (target != null) {
                target.setXOffset(this.x_offset);
            }
        }
    }
    
    protected void onTextModified() {
        if (this.isAutoWrap() && this.m_nWidth > 0) {
            this.lines = this.wrapText(this.buffer.toString());
        }
        else {
            this.lines.setElementAt(this.buffer.toString(), 0);
            this.onScrollAction();
        }
    }
    
    protected void onUpKey() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < this.lines.size(); ++i) {
            final String s = this.lines.elementAt(i);
            int length = s.length();
            if (length > 0 && i < this.lines.size() - 1 && s.charAt(length - 1) == ' ') {
                --length;
            }
            final int n4 = length + n;
            if (this.cursorPos >= n && this.cursorPos <= n4) {
                n3 = i - 1;
                break;
            }
            n2 = n;
            n += s.length();
        }
        if (n3 < 0) {
            return;
        }
        this.cursorPos = n2 + this.getPositionAt((String)this.lines.elementAt(n3), this.getCursorPoint().x);
    }
    
    protected void render(final Graphics graphics, int left, int n, final boolean b, final boolean b2) {
        if (this.fontMetrics == null) {
            if (this.m_fFont == null) {
                return;
            }
            try {
                this.fontMetrics = ((Component)this.getTarget()).getFontMetrics(this.m_fFont);
            }
            catch (Exception ex) {
                return;
            }
        }
        int n2 = 0;
        graphics.setFont(this.m_fFont);
        graphics.setColor(this.m_cForeground);
        boolean b3 = false;
        if (this.m_nHAlign == 1) {
            left += 2;
        }
        for (int i = 0; i < this.lines.size(); ++i) {
            final String s = this.lines.elementAt(i);
            if (this.x_offset == 0 && this.m_nHAlign != 1) {
                if (this.m_nHAlign == 3) {
                    left = this.m_nWidth / 2 - this.fontMetrics.stringWidth(s) / 2;
                }
                else if (this.m_nHAlign == 2) {
                    left = this.m_nWidth - this.fontMetrics.stringWidth(s) - this.m_inInsets.right;
                }
            }
            if (left < this.m_inInsets.left) {
                left = this.m_inInsets.left;
            }
            int length = s.length();
            if (length > 0 && i < this.lines.size() - 1 && s.charAt(length - 1) == ' ') {
                --length;
            }
            final int n3 = length + n2;
            final int n4 = n + this.fontMetrics.getHeight() - this.fontMetrics.getHeight() / 4;
            left -= this.x_offset;
            if (!this.m_bMarkVisible || this.selected == 0) {
                graphics.drawString(s, left, n4);
                if (b) {
                    final int n5 = n + this.fontMetrics.getHeight();
                    graphics.drawLine(left, n5, left + this.fontMetrics.stringWidth(s), n5);
                }
                if (b2) {
                    final int n6 = n + this.fontMetrics.getHeight() / 2;
                    graphics.drawLine(left, n6, left + this.fontMetrics.stringWidth(s), n6);
                }
            }
            else if (this.selected == 2) {
                graphics.setColor(this.m_cHighlight);
                graphics.fillRect(left, n, this.fontMetrics.stringWidth(s), this.fontMetrics.getHeight());
                graphics.setColor(this.m_cTextHighlight);
                graphics.drawString(s, left, n4);
                if (b) {
                    final int n7 = n + this.fontMetrics.getHeight();
                    graphics.drawLine(left, n7, left + this.fontMetrics.stringWidth(s), n7);
                }
                if (b2) {
                    final int n8 = n + this.fontMetrics.getHeight() / 2;
                    graphics.drawLine(left, n8, left + this.fontMetrics.stringWidth(s), n8);
                }
            }
            else {
                int n9 = this.selectionStart;
                int n10 = this.selectionEnd;
                if (this.selectionStart > this.selectionEnd) {
                    n10 = this.selectionStart;
                    n9 = this.selectionEnd;
                }
                boolean b6;
                boolean b5;
                boolean b4 = b5 = (b6 = false);
                if (n9 >= n2 && n9 <= n3 && n10 >= n2 && n10 <= n3) {
                    b6 = true;
                }
                else if (n9 >= n2 && n9 < n2 + s.length()) {
                    b5 = true;
                }
                else if (n10 >= n2 && n10 < n2 + s.length()) {
                    b4 = true;
                }
                if (b5) {
                    final int n11 = n9 - n2;
                    final String substring = s.substring(0, n11);
                    final String substring2 = s.substring(n11);
                    graphics.drawString(substring, left, n4);
                    final int n12 = left + this.fontMetrics.stringWidth(substring);
                    graphics.setColor(this.m_cHighlight);
                    graphics.fillRect(n12, n, this.fontMetrics.stringWidth(substring2), this.fontMetrics.getHeight());
                    graphics.setColor(this.m_cTextHighlight);
                    graphics.drawString(substring2, n12, n4);
                    if (b) {
                        final int n13 = n + this.fontMetrics.getHeight();
                        graphics.drawLine(left, n13, left + this.fontMetrics.stringWidth(s), n13);
                    }
                    if (b2) {
                        final int n14 = n + this.fontMetrics.getHeight() / 2;
                        graphics.drawLine(left, n14, left + this.fontMetrics.stringWidth(s), n14);
                    }
                    b3 = true;
                }
                else if (b4) {
                    final int n15 = n10 - n2;
                    final String substring3 = s.substring(0, n15);
                    final String substring4 = s.substring(n15);
                    final int n16 = left + this.fontMetrics.stringWidth(substring3);
                    graphics.setColor(this.m_cHighlight);
                    graphics.fillRect(left, n, this.fontMetrics.stringWidth(substring3), this.fontMetrics.getHeight());
                    graphics.setColor(this.m_cTextHighlight);
                    graphics.drawString(substring3, left, n4);
                    graphics.setColor(this.m_cForeground);
                    graphics.drawString(substring4, n16, n4);
                    if (b) {
                        final int n17 = n + this.fontMetrics.getHeight();
                        graphics.drawLine(left, n17, left + this.fontMetrics.stringWidth(s), n17);
                    }
                    if (b2) {
                        final int n18 = n + this.fontMetrics.getHeight() / 2;
                        graphics.drawLine(left, n18, left + this.fontMetrics.stringWidth(s), n18);
                    }
                    b3 = false;
                }
                else if (b3) {
                    graphics.setColor(this.m_cHighlight);
                    graphics.fillRect(left, n, this.fontMetrics.stringWidth(s), this.fontMetrics.getHeight());
                    graphics.setColor(this.m_cTextHighlight);
                    graphics.drawString(s, left, n4);
                    if (b) {
                        final int n19 = n + this.fontMetrics.getHeight();
                        graphics.drawLine(left, n19, left + this.fontMetrics.stringWidth(s), n19);
                    }
                    if (b2) {
                        final int n20 = n + this.fontMetrics.getHeight() / 2;
                        graphics.drawLine(left, n20, left + this.fontMetrics.stringWidth(s), n20);
                    }
                }
                else if (b6) {
                    final int n21 = n9 - n2;
                    final int n22 = n10 - n2;
                    final String substring5 = s.substring(0, n21);
                    final int n23 = left + this.fontMetrics.stringWidth(substring5);
                    final String substring6 = s.substring(n21, n22);
                    final int n24 = n23 + this.fontMetrics.stringWidth(substring6);
                    final String substring7 = s.substring(n22);
                    graphics.drawString(substring5, left, n4);
                    graphics.setColor(this.m_cHighlight);
                    graphics.fillRect(n23, n, this.fontMetrics.stringWidth(substring6), this.fontMetrics.getHeight());
                    graphics.setColor(this.m_cTextHighlight);
                    graphics.drawString(substring6, n23, n4);
                    graphics.setColor(this.getForeground());
                    graphics.drawString(substring7, n24, n4);
                    if (b) {
                        final int n25 = n + this.fontMetrics.getHeight();
                        graphics.drawLine(left, n25, left + this.fontMetrics.stringWidth(s), n25);
                    }
                    if (b2) {
                        final int n26 = n + this.fontMetrics.getHeight() / 2;
                        graphics.drawLine(left, n26, left + this.fontMetrics.stringWidth(s), n26);
                    }
                }
                else {
                    graphics.drawString(s, left, n4);
                    if (b) {
                        final int n27 = n + this.fontMetrics.getHeight();
                        graphics.drawLine(left, n27, left + this.fontMetrics.stringWidth(s), n27);
                    }
                    if (b2) {
                        final int n28 = n + this.fontMetrics.getHeight() / 2;
                        graphics.drawLine(left, n28, left + this.fontMetrics.stringWidth(s), n28);
                    }
                }
            }
            n += this.fontMetrics.getHeight();
            n2 += s.length();
        }
    }
    
    protected void setAllowScroll(final boolean bAllowScroll) {
        this.m_bAllowScroll = bAllowScroll;
    }
    
    public void setAutoWrap(final boolean bAutoWrap) {
        if (this.m_bAutoWrap != bAutoWrap) {
            this.m_bAutoWrap = bAutoWrap;
            this.onTextModified();
        }
    }
    
    public void setBackground(final Color cBackground) {
        this.m_cBackground = cBackground;
    }
    
    protected void setCursorPos(final int cursorPos) {
        this.cursorPos = cursorPos;
        this.onScrollAction();
    }
    
    public void setFont(final Font fFont) {
        this.m_fFont = fFont;
    }
    
    public void setFontMetrics(final FontMetrics fontMetrics) {
        this.fontMetrics = fontMetrics;
    }
    
    public void setForeground(final Color cForeground) {
        this.m_cForeground = cForeground;
    }
    
    protected void setHAlign(final int nhAlign) {
        this.m_nHAlign = nhAlign;
    }
    
    protected void setHighlight(final Color cHighlight) {
        this.m_cHighlight = cHighlight;
    }
    
    protected void setHighlightedText(final Color cTextHighlight) {
        this.m_cTextHighlight = cTextHighlight;
    }
    
    public void setInsets(final Insets inInsets) {
        this.m_inInsets = inInsets;
    }
    
    protected void setMarkVisible(final boolean bMarkVisible) {
        this.m_bMarkVisible = bMarkVisible;
    }
    
    protected void setPrevXOffset(final int prevXOffset) {
        this.prevXOffset = prevXOffset;
    }
    
    protected void setSelectionEnd(final int selectionEnd) {
        this.selectionEnd = selectionEnd;
    }
    
    protected void setSelectionStart(final int selectionStart) {
        this.selectionStart = selectionStart;
    }
    
    protected void setSelectionState(final int selected) {
        this.selected = selected;
    }
    
    public void setTarget(final IScrollable target) {
        this.target = target;
    }
    
    protected void setText(final String s) {
        this.buffer = new StringBuffer(s);
        this.onTextModified();
    }
    
    public void setWidth(final int nWidth) {
        this.m_nWidth = nWidth;
        this.onTextModified();
    }
    
    protected void setXOffset(final int x_offset) {
        this.x_offset = x_offset;
    }
    
    public String toString() {
        final String s = new String();
        final String string = "Paragraph: [text=" + this.buffer.toString() + "||selection state=";
        String s2;
        if (this.selected == 0) {
            s2 = String.valueOf(string) + "NONE";
        }
        else if (this.selected == 1) {
            s2 = String.valueOf(string) + "PARTIAL";
        }
        else {
            s2 = String.valueOf(string) + "COMPLETE";
        }
        return String.valueOf(s2) + "||selectionStart=" + this.selectionStart + "||selectionEnd=" + this.selectionEnd;
    }
    
    private Vector wrapText(final String s) {
        if (this.fontMetrics == null) {
            if (this.m_fFont == null) {
                return null;
            }
            try {
                this.fontMetrics = ((Component)this.getTarget()).getFontMetrics(this.m_fFont);
            }
            catch (Exception ex) {
                return null;
            }
        }
        final Vector<String> vector = new Vector<String>();
        int n = 0;
        if (s.length() > 0) {
            for (int i = 0; i < s.length(); ++i) {
                if (this.fontMetrics.stringWidth(s.substring(n, i + 1)) > this.m_nWidth - 4) {
                    int n2;
                    for (n2 = i; n2 > n && s.charAt(n2) != ' '; --n2) {}
                    if (n2 == n) {
                        n2 = i;
                    }
                    if (n != n2) {
                        if (n2 != i || s.charAt(n2) == ' ') {
                            ++n2;
                        }
                        vector.addElement(s.substring(n, n2));
                    }
                    n = n2;
                }
            }
        }
        if (n < s.length() || s.length() == 0) {
            vector.addElement(s.substring(n));
        }
        return vector;
    }
}
