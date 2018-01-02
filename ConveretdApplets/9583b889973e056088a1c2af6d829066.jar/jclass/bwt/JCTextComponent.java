// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.MenuItem;
import java.awt.MenuComponent;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Component;
import java.awt.Dialog;
import jclass.util.JCEnvironment;
import java.awt.Graphics;
import java.awt.Font;
import java.util.Locale;
import java.awt.SystemColor;
import jclass.base.BaseComponent;
import java.applet.Applet;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Color;
import jclass.util.JCVector;
import java.awt.Window;
import java.awt.PopupMenu;
import java.util.ResourceBundle;

public abstract class JCTextComponent extends JCComponent implements JCTextInterface, Runnable
{
    private static final boolean TRACE = false;
    public static final int LEFT = 0;
    public static final int CENTER = 1;
    public static final int RIGHT = 2;
    public static final int CASE_AS_IS = 0;
    public static final int CASE_LOWER = 1;
    public static final int CASE_UPPER = 2;
    public static final int SELECT_NONE = 0;
    public static final int SELECT_CHAR = 1;
    public static final int SELECT_WORD = 2;
    public static final int SELECT_LINE = 3;
    public static final int SELECT_PARAGRAPH = 4;
    public static final int SELECT_ALL = 5;
    protected ResourceBundle li;
    protected PopupMenu popup_menu;
    transient Thread cursor_thread;
    int cursor_pos;
    boolean cursor_state;
    boolean cursor_visible;
    boolean paint_cursor;
    boolean display_cursor;
    boolean show_cursor;
    int disabled_repaint_count;
    int action_count;
    boolean needs_repaint;
    boolean do_repaint;
    Window window;
    int num_char;
    int max_length;
    int columns;
    char[] text;
    int alignment;
    int select_start;
    int select_end;
    int select_anchor;
    int selection_type;
    protected int horiz_origin;
    protected int vert_origin;
    protected JCVector cursorListeners;
    protected JCVector valueListeners;
    boolean editable;
    boolean overstrike;
    boolean changed;
    int string_case;
    Color selected_fg;
    Color selected_bg;
    transient FontMetrics fm;
    transient Event last_event;
    protected int cursor_blink_rate;
    Rectangle rect;
    int[] select_list;
    static String clipboard;
    boolean last_state;
    boolean last_overstrike;
    int last_x;
    int last_y;
    int last_cursor_width;
    boolean first;
    private static Point p1;
    private static Point p2;
    boolean pasting;
    
    public JCTextComponent() {
        this(null, null);
    }
    
    public JCTextComponent(final Applet applet, final String s) {
        super(applet, s);
        this.cursor_state = false;
        this.cursor_visible = true;
        this.paint_cursor = true;
        this.display_cursor = true;
        this.show_cursor = true;
        this.needs_repaint = false;
        this.do_repaint = true;
        this.max_length = Integer.MAX_VALUE;
        this.columns = 20;
        this.text = new char[4];
        this.alignment = 0;
        this.cursorListeners = new JCVector(0);
        this.valueListeners = new JCVector(0);
        this.editable = true;
        this.overstrike = false;
        this.changed = false;
        this.string_case = 0;
        this.cursor_blink_rate = 500;
        this.rect = new Rectangle();
        this.select_list = new int[] { 1, 2, 3, 5 };
        this.last_state = false;
        this.last_overstrike = false;
        this.last_x = -999;
        this.first = true;
        this.pasting = false;
        if (this.getClass().getName().equals("jclass.bwt.JCTextComponent")) {
            this.getParameters(applet);
        }
        super.border_style = 8;
        if (BaseComponent.use_system_colors) {
            this.setBackground(SystemColor.text);
            this.setForeground(SystemColor.textText);
            this.selected_bg = SystemColor.textHighlight;
            this.selected_fg = SystemColor.textHighlightText;
        }
        this.li = ResourceBundle.getBundle("jclass.bwt.resources.LocaleInfo", Locale.getDefault());
    }
    
    protected void getParameters() {
        super.getParameters();
        TextComponentConverter.getParams(this);
    }
    
    public void setFont(final Font font) {
        if (this.getPeer() != null) {
            this.fm = this.getToolkit().getFontMetrics(font);
        }
        super.setFont(font);
        this.repaint();
    }
    
    public int getAlignment() {
        return this.alignment;
    }
    
    public void setAlignment(final int alignment) {
        LabelConverter.checkAlignment(alignment);
        this.alignment = alignment;
        this.repaint();
    }
    
    public boolean getChanged() {
        return this.changed;
    }
    
    public void setChanged(final boolean changed) {
        this.changed = changed;
    }
    
    public int getLastPosition() {
        return this.num_char;
    }
    
    public int getSelectionStart() {
        return this.select_start;
    }
    
    public synchronized void setSelectionStart(final int n) {
        this.select(n, this.getSelectionEnd());
    }
    
    public int getSelectionEnd() {
        return this.select_end;
    }
    
    public synchronized void setSelectionEnd(final int n) {
        this.select(this.getSelectionStart(), n);
    }
    
    public synchronized String getText() {
        return new String(this.text, 0, this.num_char);
    }
    
    char[] getTextChars() {
        return this.text;
    }
    
    public void setText(String s) {
        if (s == null) {
            s = "";
        }
        synchronized (this) {
            final boolean b = false;
            this.vert_origin = (b ? 1 : 0);
            this.horiz_origin = (b ? 1 : 0);
            this.replaceRange(s, 0, this.num_char);
            this.changed = false;
        }
    }
    
    public int getNumChar() {
        return this.num_char;
    }
    
    public int getMaximumLength() {
        return this.max_length;
    }
    
    public void setMaximumLength(final int max_length) {
        this.max_length = max_length;
    }
    
    protected boolean deleteSelection() {
        return this.deleteRange(this.select_start, this.select_end);
    }
    
    protected boolean deleteRange(final int n, final int n2) {
        return n >= n2 || this.replaceRangeInternal("", n, n2);
    }
    
    protected void cancelSelection() {
        if (this.select_start >= this.select_end) {
            return;
        }
        final int select_start = this.select_start;
        final int select_end = this.select_end;
        final int cursor_pos = this.cursor_pos;
        this.select_end = cursor_pos;
        this.select_start = cursor_pos;
        this.repaintPositions(select_start, select_end);
    }
    
    public synchronized void insert(final String s, final int n) {
        this.replaceRange(s, n, n);
    }
    
    public void append(final String s) {
        this.select(this.num_char);
        this.replaceRange(s, this.num_char, this.num_char);
    }
    
    public int getStringCase() {
        return this.string_case;
    }
    
    public void setStringCase(final int string_case) {
        TextComponentConverter.checkStringCase(string_case);
        if (string_case != this.string_case && (this.string_case = string_case) != 0) {
            this.replaceRange(this.getText(), 0, this.num_char);
        }
    }
    
    public void replaceRange(final String s, final int n, final int n2) {
        this.replaceRangeInternal(s, n, n2);
    }
    
    boolean replaceRangeInternal(String s, final int n, final int n2) {
        this.startAction(null);
        if (s == null) {
            s = "";
        }
        if (this.string_case == 1) {
            s = s.toLowerCase();
        }
        else if (this.string_case == 2) {
            s = s.toUpperCase();
        }
        final boolean replace = this.replace(s, n, n2);
        this.endAction();
        return replace;
    }
    
    protected void replaceTextInternal(final String s, int n, final int n2) {
        boolean do_repaint = false;
        synchronized (this) {
            if (n2 - n > 0) {
                System.arraycopy(this.text, n2, this.text, n, this.num_char - n2);
                this.num_char -= n2 - n;
            }
            final int n3 = (s != null) ? s.length() : 0;
            if (n3 > 0) {
                if (this.num_char + n3 + n >= this.text.length) {
                    final char[] text = new char[Math.max(this.num_char * 2, this.num_char + n3 + n + 1)];
                    System.arraycopy(this.text, 0, text, 0, this.num_char);
                    this.text = text;
                }
                if (this.num_char > n) {
                    System.arraycopy(this.text, n, this.text, n + n3, this.num_char - n);
                }
                s.getChars(0, n3, this.text, n);
                final int n4;
                n = (n4 = n + n3);
                this.select_end = n4;
                this.select_start = n4;
                this.num_char += n3;
                do_repaint = this.do_repaint;
            }
        }
        if (do_repaint && this.getPeer() != null) {
            final Rectangle rectangle = new Rectangle(this.positionToPoint(n, null));
            rectangle.add(this.positionToPoint(n2, null));
            if (this.alignment != 0) {
                rectangle.x = this.getDrawingArea().x;
            }
            this.repaint(this.getGraphics(), rectangle.x - this.horiz_origin, rectangle.y - this.vert_origin, this.size().width, rectangle.height + this.fm.getHeight());
        }
    }
    
    protected void updateScrollbars() {
    }
    
    protected boolean replace(String text, int start, int end) {
        final int n = (text != null) ? text.length() : 0;
        if (this.last_event != null && this.max_length < Integer.MAX_VALUE && this.num_char + n - (end - start) > this.max_length) {
            this.beep();
            return false;
        }
        JCTextEvent jcTextEvent = null;
        Rectangle rectangle = null;
        if (this.valueListeners.size() > 0) {
            jcTextEvent = new JCTextEvent(this, start, end, text);
            if (this.last_event != null && (this.last_event.key == 8 || this.last_event.key == 127)) {
                jcTextEvent.is_deletion = true;
            }
            else {
                jcTextEvent.is_deletion = (end - start > 0 && n < end - start);
            }
            for (int i = 0; i < this.valueListeners.size(); ++i) {
                ((JCTextListener)this.valueListeners.elementAt(i)).textValueChangeBegin(jcTextEvent);
            }
            if (!jcTextEvent.doit) {
                return false;
            }
            start = jcTextEvent.start;
            end = jcTextEvent.end;
            text = jcTextEvent.text;
        }
        if (this.last_event == null) {
            this.changed = false;
        }
        if (text == null) {
            text = "";
        }
        if (this.getPeer() != null) {
            rectangle = new Rectangle(this.positionToPoint(start, null));
            rectangle.add(this.positionToPoint(end, null));
            if (this.alignment != 0) {
                rectangle.x = this.getDrawingArea().x;
            }
        }
        this.replaceTextInternal(text, start, end);
        this.setCursorPosition(start + n);
        final int cursor_pos = this.cursor_pos;
        this.select_end = cursor_pos;
        this.select_start = cursor_pos;
        if (this.getPeer() != null) {
            rectangle.add(this.positionToPoint(this.cursor_pos, null));
            this.repaint(this.getGraphics(), rectangle.x - this.horiz_origin, rectangle.y - this.vert_origin, this.size().width, rectangle.height + this.fm.getHeight());
        }
        if (jcTextEvent != null) {
            for (int j = 0; j < this.valueListeners.size(); ++j) {
                ((JCTextListener)this.valueListeners.elementAt(j)).textValueChangeEnd(jcTextEvent);
            }
        }
        return true;
    }
    
    protected int checkCursorPosition(final int n) {
        if (n < 0) {
            return 0;
        }
        if (n > this.num_char) {
            return this.num_char;
        }
        return n;
    }
    
    public int getCursorPosition() {
        return this.cursor_pos;
    }
    
    public void setCursorPosition(int cursor_pos) {
        if (cursor_pos < 0) {
            throw new IllegalArgumentException("cursor position less than zero.");
        }
        JCTextCursorEvent jcTextCursorEvent = null;
        if (this.last_event == null) {
            this.startAction(null);
        }
        if (this.cursor_pos == cursor_pos) {
            this.endAction();
            return;
        }
        synchronized (this) {
            cursor_pos = this.checkCursorPosition(cursor_pos);
            if (this.cursorListeners.size() > 0) {
                jcTextCursorEvent = new JCTextCursorEvent(this, this.cursor_pos, cursor_pos);
                for (int i = 0; i < this.cursorListeners.size(); ++i) {
                    ((JCTextCursorListener)this.cursorListeners.elementAt(i)).textCursorMoveBegin(jcTextCursorEvent);
                }
                if (!jcTextCursorEvent.doit) {
                    // monitorexit(this)
                    return;
                }
                cursor_pos = this.checkCursorPosition(jcTextCursorEvent.new_pos);
            }
            this.cursor_pos = cursor_pos;
        }
        if (this.last_event == null) {
            this.select(this.cursor_pos);
        }
        this.endAction();
        if (jcTextCursorEvent != null) {
            for (int j = 0; j < this.cursorListeners.size(); ++j) {
                ((JCTextCursorListener)this.cursorListeners.elementAt(j)).textCursorMoveEnd(jcTextCursorEvent);
            }
        }
    }
    
    public int getCaretPosition() {
        return this.getCursorPosition();
    }
    
    public void setCaretPosition(final int cursorPosition) {
        this.setCursorPosition(cursorPosition);
    }
    
    public boolean getShowCursorPosition() {
        return this.display_cursor;
    }
    
    public void setShowCursorPosition(final boolean display_cursor) {
        this.display_cursor = display_cursor;
        if (this.show_cursor) {
            this.showPosition(this.cursor_pos);
        }
    }
    
    public void setOverstrike(final boolean overstrike) {
        this.overstrike = overstrike;
    }
    
    public boolean getOverstrike() {
        return this.overstrike;
    }
    
    public synchronized String getSelectedText() {
        return new String(this.text, this.select_start, this.select_end - this.select_start);
    }
    
    public void selectAll() {
        this.select(0, this.num_char);
    }
    
    public void select(final int cursorPosition, final int n) {
        synchronized (this) {
            this.select(cursorPosition, n, 0);
        }
        this.last_event = new Event(null, 0, null);
        this.setCursorPosition(cursorPosition);
    }
    
    void select(final int n) {
        this.select(n, n);
    }
    
    protected boolean isWordDelimiter(final char c) {
        return !Character.isJavaLetterOrDigit(c);
    }
    
    protected boolean isLineDelimiter(final char c) {
        return c == '\n' || c == '\r';
    }
    
    protected void select(int i, int j, final int n) {
        if (i > j) {
            final int n2 = i;
            i = j;
            j = n2;
        }
        i = Math.max(0, Math.min(i, this.num_char));
        j = Math.max(0, Math.min(j, this.num_char));
        final int n3 = i;
        final int n4 = j;
        switch (n) {
            case 2: {
                while (i > 0) {
                    if (this.isWordDelimiter(this.text[i - 1])) {
                        break;
                    }
                    --i;
                }
                while (j < this.num_char && !this.isWordDelimiter(this.text[j])) {
                    ++j;
                }
                if (i == n3 && j == n4 && this.getSelectionType(n + 1) == 3) {
                    i = 0;
                    j = this.num_char;
                    break;
                }
                break;
            }
            case 3: {
                while (i > 0) {
                    if (this.isLineDelimiter(this.text[i - 1])) {
                        break;
                    }
                    --i;
                }
                while (j < this.num_char) {
                    if (this.isLineDelimiter(this.text[j])) {
                        break;
                    }
                    ++j;
                }
                break;
            }
            case 5: {
                i = 0;
                j = this.num_char;
                break;
            }
        }
        if (i == this.select_start && j == this.select_end) {
            return;
        }
        final int select_start = this.select_start;
        final int select_end = this.select_end;
        this.select_start = i;
        if ((this.select_end = j) < select_start || i > select_end) {
            this.repaintPositions(select_start, select_end);
            this.repaintPositions(this.select_start, this.select_end);
            return;
        }
        this.repaintPositions(select_start, i);
        this.repaintPositions(select_end, j);
    }
    
    public abstract int positionToX(final int p0);
    
    public int positionToY(final int n) {
        return this.getDrawingArea().y;
    }
    
    public void addTextListener(final JCTextListener jcTextListener) {
        this.valueListeners.add(jcTextListener);
    }
    
    public void removeTextListener(final JCTextListener jcTextListener) {
        this.valueListeners.removeElement(jcTextListener);
    }
    
    public void addTextCursorListener(final JCTextCursorListener jcTextCursorListener) {
        this.cursorListeners.add(jcTextCursorListener);
    }
    
    public void removeTextCursorListener(final JCTextCursorListener jcTextCursorListener) {
        this.cursorListeners.removeElement(jcTextCursorListener);
    }
    
    void paintCursor(final boolean b, final Graphics graphics, final int n, int n2) {
        final int height = this.fm.getHeight();
        n2 += height - 1;
        final boolean b2 = JCEnvironment.getJavaVersion() < 115 && this.window instanceof Dialog;
        if (!b && (JCEnvironment.isJBuilder() || b2)) {
            this.repaint(new Rectangle(n, n2 - height + 1, 1, height));
            return;
        }
        if (b2) {
            graphics.setPaintMode();
            graphics.setColor(this.getForeground());
            graphics.drawLine(n, n2 - height + 1, n, n2);
            return;
        }
        graphics.drawLine(n, n2 - height + 1, n, n2);
    }
    
    protected void paintCursor(final boolean last_state) {
        if (!this.isShowing() || !this.paint_cursor || !super.has_focus) {
            return;
        }
        this.getDrawingArea(this.rect);
        int last_x = this.last_x;
        int last_y = this.last_y;
        if (this.isShowing() && this.display_cursor && last_state != this.last_state) {
            Graphics graphics;
            try {
                graphics = this.getGraphics();
            }
            catch (Exception ex) {
                return;
            }
            if (graphics == null) {
                return;
            }
            synchronized (BaseComponent.LOCK) {
                if (last_state) {
                    last_x = this.positionToX(this.cursor_pos);
                    last_y = this.positionToY(this.cursor_pos);
                }
                final int n = super.border + super.highlight;
                graphics.clipRect(n, n, this.size().width - 2 * n, this.size().height - 2 * n);
                graphics.translate(-this.horiz_origin, -this.vert_origin);
                graphics.setXORMode(this.getBackground());
                if (!last_state && this.last_overstrike != this.overstrike) {
                    final boolean overstrike = this.overstrike;
                    this.overstrike = this.last_overstrike;
                    this.paintCursor(last_state, graphics, last_x, last_y);
                    this.overstrike = overstrike;
                }
                else {
                    this.paintCursor(last_state, graphics, last_x, last_y);
                }
                graphics.dispose();
            }
            // monitorexit(BaseComponent.LOCK)
        }
        if (this.last_state = last_state) {
            this.last_x = last_x;
            this.last_y = last_y;
        }
        this.last_overstrike = this.overstrike;
    }
    
    public void run() {
        this.paintCursor(true);
        while (true) {
            try {
                Thread.sleep(this.cursor_blink_rate);
            }
            catch (Throwable t) {
                return;
            }
            if (!Thread.currentThread().isAlive() || this.cursor_thread == null) {
                break;
            }
            this.paintCursor(this.cursor_visible = !this.cursor_visible);
        }
    }
    
    int leftMargin() {
        return this.getDrawingArea().x;
    }
    
    int rightMargin() {
        return super.insets.right + 2 * (super.highlight + super.border);
    }
    
    public void setEditable(final boolean editable) {
        if (this.editable == editable) {
            return;
        }
        synchronized (this) {
            this.editable = editable;
            if (this.editable) {
                super.traversable = true;
            }
        }
        this.repaint();
    }
    
    public Color getSelectedBackground() {
        if (this.selected_bg != null) {
            return this.selected_bg;
        }
        return this.getForeground();
    }
    
    public void setSelectedBackground(final Color selected_bg) {
        this.selected_bg = selected_bg;
    }
    
    public Color getSelectedForeground() {
        if (this.selected_fg != null) {
            return this.selected_fg;
        }
        return this.getBackground();
    }
    
    public void setSelectedForeground(final Color selected_fg) {
        this.selected_fg = selected_fg;
    }
    
    public int getColumns() {
        return this.columns;
    }
    
    public void setColumns(final int columns) {
        if (columns < 0) {
            throw new IllegalArgumentException("columns less than zero.");
        }
        this.columns = columns;
    }
    
    public int[] getSelectionList() {
        return this.select_list;
    }
    
    public void setSelectionList(final int[] select_list) {
        this.select_list = select_list;
    }
    
    public boolean getEditable() {
        return this.editable;
    }
    
    public boolean isEditable() {
        return this.editable;
    }
    
    public abstract void showPosition(final int p0);
    
    private void setFm() {
        this.fm = ((this.getFont() != null) ? this.getToolkit().getFontMetrics(this.getFont()) : null);
    }
    
    protected int preferredHeight() {
        this.setFm();
        if (this.fm != null) {
            return this.fm.getHeight();
        }
        return 30;
    }
    
    protected int preferredWidth() {
        if (this.fm != null) {
            return this.columns * this.fm.charWidth('N');
        }
        return this.columns * 10;
    }
    
    public void addNotify() {
        if (this.getPeer() == null) {
            super.addNotify();
            this.setFm();
        }
        this.window = BWTUtil.getWindow(this);
        this.enableEvents(32L);
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        synchronized (this) {
            if (n == this.location().x && n2 == this.location().y && n3 == this.size().width && n4 == this.size().height) {
                // monitorexit(this)
                return;
            }
        }
        super.reshape(n, n2, n3, n4);
        synchronized (this) {
            if (this.getPeer() != null) {
                if (this.first && this.show_cursor) {
                    this.showPosition(this.cursor_pos);
                }
                this.first = false;
            }
        }
    }
    
    public void repaint() {
        if (!this.isFrozen()) {
            super.repaint();
            this.needs_repaint = false;
            return;
        }
        this.needs_repaint = true;
    }
    
    void setSelectedBg(final Graphics graphics) {
        graphics.setColor((this.selected_bg != null) ? this.selected_bg : this.getForeground());
    }
    
    void setSelectedFg(final Graphics graphics) {
        graphics.setColor((this.selected_fg != null) ? this.selected_fg : this.getBackground());
    }
    
    public abstract int pointToPosition(final int p0, final int p1);
    
    protected Point positionToPoint(final int n, final Point point) {
        if (point != null) {
            point.x = this.positionToX(n);
            point.y = this.positionToY(n);
            return null;
        }
        return new Point(this.positionToX(n), this.positionToY(n));
    }
    
    protected void repaintPositions(final int n, final int n2) {
        if (n == n2) {
            return;
        }
        synchronized (this) {
            this.positionToPoint(n, JCTextComponent.p1);
            this.positionToPoint(n2, JCTextComponent.p2);
        }
        this.repaintPositions(JCTextComponent.p1, JCTextComponent.p2);
    }
    
    protected void repaintPositions(final Point point, final Point point2) {
        int min = 0;
        int min2 = 0;
        synchronized (this) {
            if (point.equals(point2)) {
                // monitorexit(this)
                return;
            }
            point.x -= this.horiz_origin;
            point2.x -= this.horiz_origin;
            min = Math.min(point.x, point2.x);
            min2 = Math.min(point.y, point2.y);
        }
        final Graphics graphics = this.getGraphics();
        this.repaint(graphics, min, min2, Math.max(point.x, point2.x) - min, Math.max(point.y, point2.y) - min2 + this.fm.getHeight());
        graphics.dispose();
    }
    
    public void beep() {
        this.getToolkit().beep();
    }
    
    public boolean pasteFromClipboard(final Event event) {
        if (!this.editable) {
            return false;
        }
        this.deleteSelection();
        final String clipboard = this.readClipboard();
        if (this.overstrike) {
            this.replaceRange(clipboard, this.cursor_pos, this.cursor_pos + clipboard.length());
        }
        else {
            this.insert(clipboard, this.cursor_pos);
        }
        return true;
    }
    
    protected synchronized void writeClipboard(final String clipboard) {
        try {
            final Clipboard systemClipboard = this.getToolkit().getSystemClipboard();
            final StringSelection stringSelection = new StringSelection(this.getText().substring(this.select_start, this.select_end));
            systemClipboard.setContents(stringSelection, stringSelection);
        }
        catch (Exception ex) {
            JCTextComponent.clipboard = clipboard;
        }
    }
    
    protected String readClipboard() {
        String clipboard = null;
        try {
            final Transferable contents = this.getToolkit().getSystemClipboard().getContents(this);
            if (contents != null) {
                try {
                    clipboard = (String)contents.getTransferData(DataFlavor.stringFlavor);
                }
                catch (UnsupportedFlavorException ex) {}
                catch (Exception ex2) {}
            }
        }
        catch (Exception ex3) {
            clipboard = JCTextComponent.clipboard;
        }
        return clipboard;
    }
    
    public void cutToClipboard(final Event event) {
        if (this.select_start < this.select_end) {
            this.copyToClipboard(event);
            this.deleteSelection();
        }
    }
    
    public void copyToClipboard(final Event event) {
        if (this.select_start < this.select_end) {
            this.writeClipboard(this.getText().substring(this.select_start, this.select_end));
        }
    }
    
    protected int getSelectionType(final int n) {
        return this.select_list[Math.max(0, Math.min(this.select_list.length - 1, n - 1))];
    }
    
    void startAction(final Event last_event) {
        synchronized (this) {
            ++this.action_count;
            if (this.action_count > 1) {
                // monitorexit(this)
                return;
            }
            this.last_event = last_event;
        }
        this.paintCursor(false);
        this.blinkCursor(this.paint_cursor = false);
        this.freeze();
    }
    
    void endAction() {
        synchronized (this) {
            if (this.action_count > 0) {
                --this.action_count;
                if (this.action_count > 0) {
                    // monitorexit(this)
                    return;
                }
            }
            this.paint_cursor = true;
            this.last_event = null;
            this.unfreeze();
        }
        if (this.needs_repaint) {
            this.repaint();
        }
        this.updateScrollbars();
        if (this.show_cursor) {
            this.showPosition(this.cursor_pos);
        }
        this.paintCursor(true);
        this.blinkCursor(true);
    }
    
    protected void selectStart(final Event event) {
        this.startAction(event);
        final int pointToPosition = this.pointToPosition(event.x, event.y);
        this.select(this.select_anchor = pointToPosition, pointToPosition, this.selection_type = this.getSelectionType(event.clickCount));
        this.setCursorPosition(pointToPosition);
        this.endAction();
    }
    
    protected void selectExtend(final Event event) {
        this.startAction(event);
        final int pointToPosition = this.pointToPosition(event.x, event.y);
        this.select(this.select_anchor, pointToPosition, this.selection_type);
        this.setCursorPosition(pointToPosition);
        this.endAction();
    }
    
    protected void selectEnd(final Event event) {
        this.startAction(event);
        this.setCursorPosition(this.select_start);
        this.endAction();
    }
    
    protected void paste(final Event event) {
        this.startAction(event);
        this.setCursorPosition(this.pointToPosition(event.x, event.y));
        this.pasting = true;
        final boolean pasteFromClipboard = this.pasteFromClipboard(event);
        this.endAction();
        if (!pasteFromClipboard) {
            this.beep();
            return;
        }
        this.changed = true;
    }
    
    protected void freeze() {
        ++this.disabled_repaint_count;
    }
    
    protected void unfreeze() {
        if (this.disabled_repaint_count > 0) {
            --this.disabled_repaint_count;
        }
    }
    
    protected boolean isFrozen() {
        return this.disabled_repaint_count != 0;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        super.mouseDown(event, n, n2);
        if (BWTUtil.getMouseButton(event) == 2) {
            this.paste(event);
            return true;
        }
        if (BWTUtil.getMouseButton(event) == 3) {
            this.createPopup(this, n, n2);
        }
        else {
            if (BWTUtil.getMouseButton(event) != 1) {
                return false;
            }
            if (event.shiftDown()) {
                this.selectExtend(event);
            }
            else {
                this.selectStart(event);
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.pasting) {
            return true;
        }
        this.selectExtend(event);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (BWTUtil.getMouseButton(event) == 3) {
            return false;
        }
        if (!this.pasting) {
            this.selectEnd(event);
        }
        this.pasting = false;
        return true;
    }
    
    protected void scrollHome(final Event event) {
        if (event.shiftDown()) {
            if (this.select_start != this.select_end && this.cursor_pos > this.select_start) {
                this.select(0, this.select_start, 1);
            }
            else {
                this.select(0, this.select_end, 1);
            }
        }
        else {
            this.select(0);
        }
        this.setCursorPosition(0);
    }
    
    protected void scrollEnd(final Event event) {
        if (event.shiftDown()) {
            if (this.select_start != this.select_end && this.cursor_pos < this.select_end) {
                this.select(this.select_end, this.num_char, 1);
            }
            else {
                this.select(this.select_start, this.num_char, 1);
            }
        }
        else {
            this.select(this.num_char);
        }
        this.setCursorPosition(this.num_char);
    }
    
    protected void scrollLineEnd(final Event event) {
        this.scrollEnd(event);
    }
    
    protected void scrollLineBegin(final Event event) {
        this.scrollHome(event);
    }
    
    protected void moveForwardChar(final Event event) {
        if (this.cursor_pos == this.num_char) {
            return;
        }
        this.startAction(event);
        if (event.shiftDown()) {
            int select_start = this.select_start;
            int select_end = this.select_end;
            int cursorPosition;
            if (this.cursor_pos < select_end) {
                select_start = (cursorPosition = Math.min(this.num_char, select_start + 1));
            }
            else {
                select_end = (cursorPosition = Math.min(this.num_char, select_end + 1));
            }
            this.setCursorPosition(cursorPosition);
            this.select(select_start, select_end, 0);
        }
        else {
            this.select(this.cursor_pos + 1);
        }
        this.endAction();
    }
    
    protected void moveBackwardChar(final Event event) {
        if (this.cursor_pos == 0) {
            return;
        }
        this.startAction(event);
        if (event.shiftDown()) {
            int select_start = this.select_start;
            int select_end = this.select_end;
            int cursorPosition;
            if (this.cursor_pos > select_start) {
                select_end = (cursorPosition = Math.max(0, select_end - 1));
            }
            else {
                select_start = (cursorPosition = Math.max(0, select_start - 1));
            }
            this.select(select_start, select_end, 0);
            this.setCursorPosition(cursorPosition);
        }
        else {
            this.select(this.cursor_pos - 1);
        }
        this.endAction();
    }
    
    protected boolean insertChar(final Event event, final int n) {
        if (!this.editable) {
            return false;
        }
        this.startAction(event);
        final char[] array = { (char)n };
        int n2 = this.cursor_pos;
        int n3 = this.cursor_pos;
        if (this.select_end > this.select_start) {
            n2 = this.select_start;
            n3 = this.select_end;
        }
        if (this.overstrike && n3 < this.num_char) {
            ++n3;
        }
        if (this.replaceRangeInternal(new String(array), n2, n3)) {
            this.changed = true;
        }
        this.endAction();
        return true;
    }
    
    protected boolean deleteChar(final Event event, final boolean b) {
        if (!this.editable) {
            return false;
        }
        boolean b2 = false;
        this.startAction(event);
        if (this.select_start < this.select_end) {
            b2 = this.deleteRange(this.select_start, this.select_end);
        }
        else if (!b && this.cursor_pos > 0) {
            b2 = this.deleteRange(this.cursor_pos - 1, this.cursor_pos);
        }
        else if (b && this.cursor_pos < this.num_char) {
            b2 = this.deleteRange(this.cursor_pos, this.cursor_pos + 1);
        }
        if (b2) {
            this.changed = true;
        }
        this.endAction();
        return true;
    }
    
    protected boolean deletePreviousChar(final Event event) {
        return this.deleteChar(event, false);
    }
    
    protected boolean deleteCurrentChar(final Event event) {
        return this.deleteChar(event, true);
    }
    
    protected void toggleOverstrike(final Event event) {
        this.overstrike = !this.overstrike;
    }
    
    public boolean keyDown(final Event last_event, final int n) {
        boolean b = true;
        boolean b2 = true;
        this.last_event = last_event;
        if (last_event.controlDown()) {
            if (this.cmpKeyStroke("HomeKey", n)) {
                this.scrollLineBegin(last_event);
            }
            else if (this.cmpKeyStroke("CopyKey", n)) {
                this.copyToClipboard(last_event);
            }
            else if (this.cmpKeyStroke("DeleteKey", n)) {
                b = this.deleteCurrentChar(last_event);
            }
            else if (this.cmpKeyStroke("EndKey", n)) {
                this.scrollLineEnd(last_event);
            }
            else if (this.cmpKeyStroke("BackSpaceKey", n)) {
                b = this.deletePreviousChar(last_event);
            }
            else if (this.cmpKeyStroke("OverStrikeKey", n)) {
                this.toggleOverstrike(last_event);
            }
            else if (this.cmpKeyStroke("CutKey", n)) {
                this.cutToClipboard(last_event);
            }
            else if (this.cmpKeyStroke("PasteKey", n)) {
                b = this.pasteFromClipboard(last_event);
            }
            else if (n == 1000) {
                this.scrollHome(last_event);
            }
            else if (n == 1001) {
                this.scrollEnd(last_event);
            }
            else {
                b2 = false;
            }
        }
        else if (n == 1007) {
            this.moveForwardChar(last_event);
        }
        else if (n == 1006) {
            this.moveBackwardChar(last_event);
        }
        else if (n == 8) {
            b = this.deletePreviousChar(last_event);
        }
        else if (n == 127) {
            b = this.deleteCurrentChar(last_event);
        }
        else if (n == 1000) {
            this.scrollLineBegin(last_event);
        }
        else if (n == 1001) {
            this.scrollLineEnd(last_event);
        }
        else if (n == 9) {
            b2 = false;
        }
        else if (last_event.id != 403) {
            b = this.insertChar(last_event, n);
        }
        else {
            b2 = false;
        }
        if (!b) {
            this.beep();
        }
        this.last_event = null;
        return b2 || super.keyDown(last_event, n);
    }
    
    protected void createPopup(final Component component, final int n, final int n2) {
        if (this.popup_menu != null) {
            this.remove(this.popup_menu);
        }
        this.popup_menu = new PopupMenu();
        boolean b = false;
        boolean b2 = false;
        if (this.select_start == 0 && this.select_end + 1 >= this.num_char && this.num_char != 0) {
            b = true;
        }
        if (this.select_start == this.select_end || this.num_char == 0) {
            b2 = true;
        }
        final MenuItem menuItem = new MenuItem(this.li.getString("Cut"));
        if (b2 || !this.isEditable()) {
            menuItem.setEnabled(false);
        }
        else {
            menuItem.addActionListener(new Cut());
        }
        this.popup_menu.add(menuItem);
        final MenuItem menuItem2 = new MenuItem(this.li.getString("Copy"));
        if (b2) {
            menuItem2.setEnabled(false);
        }
        else {
            menuItem2.addActionListener(new Copy());
        }
        this.popup_menu.add(menuItem2);
        final MenuItem menuItem3 = new MenuItem(this.li.getString("Paste"));
        if (!this.isEditable()) {
            menuItem3.setEnabled(false);
        }
        else {
            menuItem3.addActionListener(new Paste());
        }
        this.popup_menu.add(menuItem3);
        final MenuItem menuItem4 = new MenuItem(this.li.getString("Delete"));
        if (b2 || !this.isEditable()) {
            menuItem4.setEnabled(false);
        }
        else {
            menuItem4.addActionListener(new Delete());
        }
        this.popup_menu.add(menuItem4);
        this.popup_menu.addSeparator();
        final MenuItem menuItem5 = new MenuItem(this.li.getString("SelectAll"));
        if (b || this.num_char == 0) {
            menuItem5.setEnabled(false);
        }
        else {
            menuItem5.addActionListener(new SelectAll());
        }
        this.popup_menu.add(menuItem5);
        this.add(this.popup_menu);
        this.popup_menu.show(component, n, n2);
    }
    
    protected boolean cmpKeyStroke(final String s, final int n) {
        final Integer n2 = (Integer)this.li.getObject(s);
        return n2 != null && n2 == n;
    }
    
    protected void blinkCursor(final boolean b) {
        if (!super.has_focus || !this.display_cursor || !this.isEnabled()) {
            return;
        }
        if (b) {
            if (this.cursor_thread != null && this.cursor_thread.isAlive()) {
                return;
            }
            this.cursor_thread = new Thread(this);
            if (JCEnvironment.getBrowser(this) != 3) {
                this.cursor_thread.setPriority(1);
            }
            if (this.cursor_thread != null && !this.cursor_thread.isAlive()) {
                this.cursor_thread.start();
            }
        }
        else if (this.cursor_thread != null && this.cursor_thread.isAlive()) {
            try {
                Thread.yield();
                this.cursor_thread.stop();
                this.paintCursor(false);
                this.cursor_thread = null;
            }
            catch (Exception ex) {}
        }
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        final boolean gotFocus = super.gotFocus(event, o);
        this.setFm();
        if (gotFocus) {
            this.blinkCursor(true);
        }
        return gotFocus;
    }
    
    public boolean lostFocus(final Event event, final Object o) {
        this.blinkCursor(false);
        this.cancelSelection();
        return super.lostFocus(event, o);
    }
    
    public synchronized void disable() {
        if (this.isEnabled()) {
            this.blinkCursor(false);
            super.disable();
        }
    }
    
    public abstract Dimension getMinimumSize(final int p0);
    
    static {
        JCTextComponent.p1 = new Point(0, 0);
        JCTextComponent.p2 = new Point(0, 0);
    }
    
    public class Cut implements ActionListener, Serializable
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            JCTextComponent.this.cutToClipboard(null);
        }
    }
    
    public class Copy implements ActionListener, Serializable
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            JCTextComponent.this.copyToClipboard(null);
        }
    }
    
    public class Paste implements ActionListener, Serializable
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            JCTextComponent.this.pasteFromClipboard(null);
        }
    }
    
    public class Delete implements ActionListener, Serializable
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            JCTextComponent.this.deleteSelection();
        }
    }
    
    public class SelectAll implements ActionListener, Serializable
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            JCTextComponent.this.selectAll();
        }
    }
}
