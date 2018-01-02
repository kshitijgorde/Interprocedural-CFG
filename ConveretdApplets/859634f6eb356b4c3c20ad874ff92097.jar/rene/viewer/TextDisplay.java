// 
// Decompiled by Procyon v0.5.30
// 

package rene.viewer;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.Clipboard;
import java.io.PrintWriter;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.SystemColor;
import rene.gui.Global;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import rene.util.list.ListElement;
import java.awt.FontMetrics;
import java.awt.Font;
import rene.util.list.ListClass;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.Canvas;

public class TextDisplay extends Canvas implements ClipboardOwner
{
    ListClass L;
    Font F;
    FontMetrics FM;
    Viewer V;
    int Leading;
    int Height;
    int Ascent;
    int Descent;
    int LineCount;
    int TopLineCount;
    int PageSize;
    ListElement TopLine;
    Image I;
    Graphics IG;
    int W;
    int H;
    public int Tabsize;
    public int Offset;
    boolean LineFinished;
    int[] Widths;
    int[] HW;
    long LastScrollTime;
    Color Background;
    
    public TextDisplay(final Viewer v) {
        this.Tabsize = 4;
        this.LineFinished = true;
        this.L = new ListClass();
        this.F = null;
        this.V = v;
        this.LineCount = 0;
        this.TopLineCount = 0;
        this.TopLine = null;
        this.I = null;
        final boolean b = false;
        this.H = (b ? 1 : 0);
        this.W = (b ? 1 : 0);
        this.PageSize = 10;
        this.HW = new int[1024];
        this.addKeyListener(v);
    }
    
    void init(final Font f) {
        this.F = f;
        this.FM = this.getFontMetrics(this.F);
        this.Leading = this.FM.getLeading() + Global.getParameter("fixedfont.spacing", 0);
        this.Height = this.FM.getHeight();
        this.Ascent = this.FM.getAscent();
        this.Descent = this.FM.getDescent();
        this.Widths = this.FM.getWidths();
        if (Global.Background != null) {
            this.Background = Global.Background;
            return;
        }
        this.Background = SystemColor.window;
    }
    
    public Color getBackground() {
        if (Global.Background != null) {
            return Global.Background;
        }
        return SystemColor.window;
    }
    
    int[] getwidth(final char[] array) {
        try {
            for (int i = 0; i < array.length; ++i) {
                if (array[i] < '\u0100') {
                    this.HW[i] = this.Widths[array[i]];
                }
                else {
                    this.HW[i] = this.FM.charWidth(array[i]);
                }
            }
        }
        catch (Exception ex) {
            return this.HW;
        }
        return this.HW;
    }
    
    public synchronized void appendLine0(final String s) {
        this.appendLine0(s, Color.black);
    }
    
    public synchronized void appendLine0(final String s, final Color color) {
        this.L.append(new ListElement(new Line(s, this, color)));
        ++this.LineCount;
        if (this.LineCount == 1) {
            this.TopLine = this.L.first();
        }
        this.LineFinished = true;
    }
    
    public synchronized void appendLine(final String s) {
        this.appendLine0(s);
        this.V.setVerticalScrollbar();
    }
    
    public void append(final String s, final Color color) {
        this.append(s, color, true);
    }
    
    public void append(String substring, final Color color, final boolean b) {
        do {
            final int index = substring.indexOf(10);
            if (index < 0) {
                this.appendlast(substring, color);
                this.LineFinished = false;
                break;
            }
            this.appendlast(substring.substring(0, index), color);
            this.LineFinished = true;
            substring = substring.substring(index + 1);
        } while (!substring.equals(""));
        if (b) {
            this.doUpdate(true);
        }
        this.repaint();
    }
    
    public void doUpdate(final boolean b) {
        if (b && System.currentTimeMillis() - this.LastScrollTime > 10000L) {
            this.showlast();
        }
        this.repaint();
        this.V.setVerticalScrollbar();
    }
    
    public void setText(final String s) {
        this.TopLine = null;
        this.TopLineCount = 0;
        this.LineCount = 0;
        this.L = new ListClass();
        if (!s.equals("")) {
            this.append(s, Color.black);
        }
        this.repaint();
    }
    
    public synchronized void appendlast(final String s, final Color color) {
        if (this.LineFinished || this.L.last() == null) {
            this.L.append(new ListElement(new Line(s, this, color)));
            ++this.LineCount;
            if (this.LineCount == 1) {
                this.TopLine = this.L.first();
            }
        }
        else {
            ((Line)this.L.last().content()).append(s);
        }
    }
    
    public void showlast() {
        ListElement topLine = this.L.last();
        if (topLine == null) {
            return;
        }
        this.TopLineCount = this.LineCount;
        for (int n = 0; n < this.PageSize - 1 && topLine.previous() != null; topLine = topLine.previous(), --this.TopLineCount, ++n) {}
        this.TopLine = topLine;
        this.repaint();
    }
    
    public void makeimage() {
        final Dimension size = this.getSize();
        if (this.I == null || size.width != this.W || size.height != this.H) {
            final int width = size.width;
            this.W = width;
            final int height = size.height;
            this.H = height;
            this.I = this.createImage(width, height);
            this.IG = this.I.getGraphics();
        }
        this.IG.setColor(Color.black);
        this.IG.clearRect(0, 0, this.W, this.H);
        this.IG.setFont(this.F);
        try {
            this.PageSize = this.H / (this.Height + this.Leading);
        }
        catch (Exception ex) {}
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.F == null) {
            this.init(this.getFont());
        }
        this.makeimage();
        ListElement listElement = this.TopLine;
        int n = this.Leading + this.Ascent;
        this.getSize();
        if (this.Background == null) {
            this.Background = this.getBackground();
        }
        this.IG.setColor(this.Background);
        this.IG.fillRect(0, 0, this.W, this.H);
        for (int n2 = 0; n2 < this.PageSize && listElement != null; listElement = listElement.next(), ++n2) {
            ((Line)listElement.content()).draw(this.IG, 2, n);
            n += this.Leading + this.Height;
        }
        graphics.drawImage(this.I, 0, 0, this);
    }
    
    public ListElement getline(final int n) {
        if (this.TopLine == null) {
            return null;
        }
        ListElement listElement = this.TopLine;
        final int n2 = this.Leading + this.Height;
        if (n2 == 0) {
            return null;
        }
        for (int n3 = n / n2, i = 0; i < n3; ++i) {
            if (listElement.next() == null) {
                return listElement;
            }
            listElement = listElement.next();
        }
        return listElement;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    int computeVertical() {
        if (this.LineCount > 0) {
            return this.TopLineCount * 1000 / this.LineCount;
        }
        return 0;
    }
    
    public int setVertical(final int n) {
        if (this.TopLine == null) {
            return 0;
        }
        final int n2 = this.LineCount * n / 1000;
        if (n2 > this.TopLineCount) {
            for (int topLineCount = this.TopLineCount; topLineCount < n2 && this.TopLine.next() != null; ++topLineCount) {
                this.TopLine = this.TopLine.next();
                ++this.TopLineCount;
            }
            this.repaint();
        }
        else if (n2 < this.TopLineCount) {
            for (int topLineCount2 = this.TopLineCount; topLineCount2 > n2 && this.TopLine.previous() != null; --topLineCount2) {
                this.TopLine = this.TopLine.previous();
                --this.TopLineCount;
            }
            this.repaint();
        }
        this.LastScrollTime = System.currentTimeMillis();
        return n;
    }
    
    public void verticalUp() {
        if (this.TopLine == null) {
            return;
        }
        if (this.TopLine.next() == null) {
            return;
        }
        this.TopLine = this.TopLine.next();
        ++this.TopLineCount;
        this.repaint();
        this.LastScrollTime = System.currentTimeMillis();
    }
    
    public void verticalDown() {
        if (this.TopLine == null) {
            return;
        }
        if (this.TopLine.previous() == null) {
            return;
        }
        this.TopLine = this.TopLine.previous();
        --this.TopLineCount;
        this.repaint();
        this.LastScrollTime = System.currentTimeMillis();
    }
    
    public void verticalPageUp() {
        if (this.TopLine == null) {
            return;
        }
        for (int n = 0; n < this.PageSize - 1 && this.TopLine.next() != null; ++n) {
            this.TopLine = this.TopLine.next();
            ++this.TopLineCount;
        }
        this.repaint();
        this.LastScrollTime = System.currentTimeMillis();
    }
    
    public void verticalPageDown() {
        if (this.TopLine == null) {
            return;
        }
        for (int n = 0; n < this.PageSize - 1 && this.TopLine.previous() != null; ++n) {
            this.TopLine = this.TopLine.previous();
            --this.TopLineCount;
        }
        this.repaint();
        this.LastScrollTime = System.currentTimeMillis();
    }
    
    int computeVerticalSize() {
        if (this.LineCount == 0) {
            return 100;
        }
        int n = this.PageSize * 2000 / this.LineCount;
        if (n < 10) {
            n = 10;
        }
        return n;
    }
    
    public int setHorizontal(final int n) {
        this.Offset = n / 5;
        this.repaint();
        return n;
    }
    
    public void save(final PrintWriter printWriter) {
        for (ListElement listElement = this.L.first(); listElement != null; listElement = listElement.next()) {
            printWriter.println(new String(((Line)listElement.content()).a));
        }
    }
    
    public TextPosition getposition(final int n, final int n2) {
        if (this.L.first() == null) {
            return null;
        }
        if (n2 < 0) {
            return new TextPosition(this.TopLine, this.TopLineCount, 0);
        }
        if (this.TopLine == null) {
            return null;
        }
        ListElement listElement = this.TopLine;
        final int n3 = this.Leading + this.Height;
        if (n3 == 0) {
            return null;
        }
        int n4;
        int i;
        for (n4 = n2 / n3, i = 0; i < n4; ++i) {
            if (listElement.next() == null || i == this.PageSize - 1) {
                return new TextPosition(listElement, this.TopLineCount + i, ((Line)listElement.content()).length());
            }
            listElement = listElement.next();
        }
        return new TextPosition(listElement, this.TopLineCount + i, ((Line)listElement.content()).getpos(n, 2));
    }
    
    public void unmark() {
        for (ListElement listElement = this.L.first(); listElement != null; listElement = listElement.next()) {
            ((Line)listElement.content()).block(0, 0);
        }
        this.repaint();
    }
    
    public void unmark(final TextPosition textPosition, final TextPosition textPosition2) {
        if (textPosition == null || textPosition2 == null) {
            return;
        }
        TextPosition textPosition3;
        TextPosition textPosition4;
        if (textPosition.before(textPosition2)) {
            textPosition3 = textPosition;
            textPosition4 = textPosition2;
        }
        else {
            if (!textPosition2.before(textPosition)) {
                return;
            }
            textPosition3 = textPosition2;
            textPosition4 = textPosition;
        }
        ListElement listElement;
        for (listElement = textPosition3.L; listElement != null && listElement != textPosition4.L; listElement = listElement.next()) {
            ((Line)listElement.content()).block(0, 0);
        }
        if (listElement != null) {
            ((Line)listElement.content()).block(0, 0);
        }
        this.repaint();
    }
    
    public void mark(final TextPosition textPosition, final TextPosition textPosition2) {
        if (textPosition == null || textPosition2 == null) {
            return;
        }
        TextPosition textPosition3;
        TextPosition textPosition4;
        if (textPosition.before(textPosition2)) {
            textPosition3 = textPosition;
            textPosition4 = textPosition2;
        }
        else {
            if (!textPosition2.before(textPosition)) {
                return;
            }
            textPosition3 = textPosition2;
            textPosition4 = textPosition;
        }
        ListElement listElement = textPosition3.L;
        ((Line)listElement.content()).block(textPosition3.LPos, 1);
        if (listElement != textPosition4.L) {
            listElement = listElement.next();
        }
        while (listElement != null && listElement != textPosition4.L) {
            ((Line)listElement.content()).block(0, 4);
            listElement = listElement.next();
        }
        if (listElement != null) {
            ((Line)listElement.content()).block(textPosition4.LPos, 2);
        }
        this.repaint();
        this.requestFocus();
    }
    
    void copy(final TextPosition textPosition, final TextPosition textPosition2) {
        if (textPosition == null || textPosition2 == null) {
            return;
        }
        TextPosition textPosition3;
        TextPosition textPosition4;
        if (textPosition.before(textPosition2)) {
            textPosition3 = textPosition;
            textPosition4 = textPosition2;
        }
        else {
            if (!textPosition2.before(textPosition)) {
                return;
            }
            textPosition3 = textPosition2;
            textPosition4 = textPosition;
        }
        String s = "";
        ListElement listElement;
        for (listElement = textPosition3.L; listElement != null && listElement != textPosition4.L; listElement = listElement.next()) {
            s = String.valueOf(s) + ((Line)listElement.content()).getblock() + "\n";
        }
        if (listElement != null) {
            s = String.valueOf(s) + ((Line)listElement.content()).getblock();
        }
        new ClipboardCopy(this, this, s);
    }
    
    public void lostOwnership(final Clipboard clipboard, final Transferable transferable) {
    }
    
    TextPosition lastpos() {
        final ListElement last = this.L.last();
        if (last == null) {
            return null;
        }
        return new TextPosition(last, this.LineCount, ((Line)last.content()).length());
    }
}
