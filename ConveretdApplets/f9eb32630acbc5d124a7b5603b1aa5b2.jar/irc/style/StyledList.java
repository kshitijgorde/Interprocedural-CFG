// 
// Decompiled by Procyon v0.5.30
// 

package irc.style;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Locale;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import irc.StyleContext;
import java.util.Vector;
import java.awt.Color;
import irc.ListenerGroup;
import irc.IRCConfiguration;
import java.awt.Image;
import java.util.Hashtable;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Panel;

public class StyledList extends Panel implements MouseListener, MouseMotionListener, FormattedStringDrawerListener
{
    private LimitedArray _list;
    private Hashtable _nickInfos;
    private boolean _wrap;
    private int _last;
    private int _first;
    private int _left;
    private int _width;
    private int _toScrollX;
    private int _toScrollY;
    private FormattedStringDrawer _drawer;
    private Image _buffer;
    private int _bufferWidth;
    private int _bufferHeight;
    private int _lastWidth;
    private int _lastHeight;
    private Hashtable _results;
    private MultipleWordCatcher _catcher;
    private WordListRecognizer _wordListRecognizer;
    private IRCConfiguration _ircConfiguration;
    private int _pressedX;
    private int _pressedY;
    private int _draggedX;
    private int _draggedY;
    private boolean _dragging;
    private DrawResultItem _currentItem;
    private DrawResultItem _currentFloatItem;
    private DrawResultItem _currentHighLightItem;
    private String _currentFloatText;
    private String _copiedString;
    private boolean _fullDraw;
    private ListenerGroup _listeners;
    private ResultPair[] _addedResults;
    private int _addedCount;
    private int _hdirection;
    private int _vdirection;
    private Color _colormale;
    private Color _colorfemeale;
    private Color _colorundef;
    private Vector _updateItems;
    private long _lastRefresh;
    private Image _backImage;
    private int _backTiling;
    private int _maximumSize;
    private DecodedLine _emptyLine;
    private static final int BOTTOM = 0;
    private static final int TOP = 1;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    private static final boolean _doubleBuffer = true;
    
    public StyledList(final IRCConfiguration ircConfiguration, final StyleContext styleContext) {
        this(ircConfiguration, true, styleContext);
    }
    
    public StyledList(final IRCConfiguration ircConfiguration, final boolean b, final StyleContext styleContext) {
        this(ircConfiguration, b, styleContext, Color.blue, Color.pink, Color.gray);
    }
    
    public StyledList(final IRCConfiguration ircConfiguration, final boolean wrap, final StyleContext styleContext, final Color colormale, final Color colorfemeale, final Color colorundef) {
        this._lastRefresh = System.currentTimeMillis();
        this._backImage = null;
        this._backTiling = 0;
        this._colormale = colormale;
        this._colorfemeale = colorfemeale;
        this._colorundef = colorundef;
        this._nickInfos = new Hashtable();
        this._fullDraw = false;
        this._addedResults = new ResultPair[64];
        for (int i = 0; i < this._addedResults.length; ++i) {
            this._addedResults[i] = new ResultPair();
        }
        this._addedCount = 0;
        this._hdirection = 0;
        this._vdirection = 0;
        this._ircConfiguration = ircConfiguration;
        this._copiedString = "";
        this._dragging = false;
        this._currentFloatItem = null;
        this._currentFloatText = null;
        this._currentItem = null;
        this._toScrollX = 0;
        this._toScrollY = 0;
        this._left = 0;
        this._wrap = wrap;
        this._buffer = null;
        (this._drawer = new FormattedStringDrawer(this._ircConfiguration, styleContext, this)).setHorizontalDirection(this._hdirection);
        this._drawer.setVerticalDirection(this._vdirection);
        this._catcher = new MultipleWordCatcher();
        this._wordListRecognizer = new WordListRecognizer();
        this._catcher.addRecognizer(new ChannelRecognizer());
        this._catcher.addRecognizer(new URLRecognizer());
        this._catcher.addRecognizer(this._wordListRecognizer);
        this._results = new Hashtable();
        this._listeners = new ListenerGroup();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this._maximumSize = this._ircConfiguration.getI("style:maximumlinecount");
        this._emptyLine = this._drawer.decodeLine("");
        this.clear();
        this.setBackgroundImage(this._ircConfiguration.getStyleBackgroundImage(styleContext));
        this.setBackgroundTiling(this._ircConfiguration.getStyleBackgroundTiling(styleContext));
        if (this._ircConfiguration.getB("style:righttoleft")) {
            this.setHorizontalDirection(1);
        }
    }
    
    public void release() {
        this.clear();
        this.dispose();
        this.removeMouseListener(this);
        this.removeMouseMotionListener(this);
    }
    
    private void drawBackImage(final Graphics graphics, final int n, final int n2) {
        final int width = this._backImage.getWidth(this);
        final int height = this._backImage.getHeight(this);
        switch (this._backTiling & 0xFF) {
            case 3: {
                int n3 = 0;
                int n4 = 0;
                if ((this._backTiling & 0x100) != 0x0) {
                    n3 = n - width - 1;
                }
                if ((this._backTiling & 0x200) != 0x0) {
                    n4 = n2 - height - 1;
                }
                graphics.setColor(this._drawer.getColor(0));
                graphics.fillRect(0, 0, n, n2);
                graphics.drawImage(this._backImage, n3, n4, this._drawer.getColor(0), this);
                break;
            }
            case 0: {
                final int n5 = (n - width) / 2;
                final int n6 = (n2 - height) / 2;
                graphics.setColor(this._drawer.getColor(0));
                graphics.fillRect(0, 0, n, n2);
                graphics.drawImage(this._backImage, n5, n6, this._drawer.getColor(0), this);
                break;
            }
            case 1: {
                graphics.drawImage(this._backImage, 0, 0, n, n2, this._drawer.getColor(0), this);
                break;
            }
            case 2: {
                for (int i = 0; i < n; i += width) {
                    for (int j = 0; j < n2; j += height) {
                        graphics.drawImage(this._backImage, i, j, this._drawer.getColor(0), this);
                    }
                }
                break;
            }
        }
    }
    
    private void expandResult() {
        final ResultPair[] addedResults = new ResultPair[this._addedResults.length * 2];
        System.arraycopy(this._addedResults, 0, addedResults, 0, this._addedResults.length);
        for (int i = this._addedResults.length; i < addedResults.length; ++i) {
            addedResults[i] = new ResultPair();
        }
        this._addedResults = addedResults;
    }
    
    public void setHorizontalDirection(final int hdirection) {
        this._hdirection = hdirection;
        this._drawer.setHorizontalDirection(this._hdirection);
    }
    
    public int getHorizontalDirection() {
        return this._hdirection;
    }
    
    public void setBackgroundImage(final Image backImage) {
        this._backImage = backImage;
        this.repaint();
    }
    
    public void setBackgroundTiling(final int backTiling) {
        this._backTiling = backTiling;
        this.repaint();
    }
    
    public void setFont(final Font font) {
        this._drawer.setFont(font);
        this.reinit();
        this.repaint();
    }
    
    public void setWrap(final boolean wrap) {
        this._wrap = wrap;
        this.reinit();
        this.repaint();
    }
    
    public synchronized void setNickList(final String[] array) {
        final String[] list = new String[array.length];
        this._nickInfos.clear();
        for (int i = 0; i < array.length; ++i) {
            String substring = array[i];
            String substring2 = "";
            final int index = substring.indexOf(":");
            if (index != -1) {
                substring2 = substring.substring(index + 1);
                substring = substring.substring(0, index);
            }
            list[i] = substring;
            this._nickInfos.put(substring.toLowerCase(Locale.ENGLISH), substring2);
        }
        this._wordListRecognizer.setList(list);
    }
    
    public synchronized void addStyledListListener(final StyledListListener styledListListener) {
        this._listeners.addListener(styledListListener);
    }
    
    public synchronized void removeStyledListListener(final StyledListListener styledListListener) {
        this._listeners.removeListener(styledListListener);
    }
    
    public synchronized void setLeft(final int left) {
        final int left2 = this._left;
        this._left = left;
        if (this._left < 0) {
            this._left = 0;
        }
        if (this._left >= this.getLogicalWidth()) {
            this._left = this.getLogicalWidth() - 1;
        }
        if (this._hdirection == 1) {
            this._left = -this._left;
        }
        if (this._left != left2) {
            this.addToScroll(this._left - left2, 0);
            this.repaint();
        }
    }
    
    public int getLeft() {
        if (this._hdirection == 1) {
            return -this._left;
        }
        return this._left;
    }
    
    public synchronized void setFirst(final int first) {
        if (this._vdirection != 1) {
            this._fullDraw = true;
        }
        this._vdirection = 1;
        this._drawer.setVerticalDirection(1);
        final int first2 = this._first;
        this._first = first;
        if (this._first < 0) {
            this._last = 0;
        }
        if (this._first >= this._list.size()) {
            this._last = this._list.size() - 1;
        }
        if (this._first != first2) {
            this.addToScroll(0, this._first - first2);
            this.repaint();
        }
    }
    
    public synchronized void setLast(final int last) {
        if (this._vdirection != 0) {
            this._fullDraw = true;
        }
        this._vdirection = 0;
        this._drawer.setVerticalDirection(0);
        final int last2 = this._last;
        this._last = last;
        if (this._last < 0) {
            this._last = 0;
        }
        if (this._last >= this._list.size()) {
            this._last = this._list.size() - 1;
        }
        if (this._last != last2) {
            this.addToScroll(0, this._last - last2);
            this.repaint();
        }
    }
    
    public int getLogicalWidth() {
        return this._width;
    }
    
    public int getLast() {
        return this._last;
    }
    
    public synchronized int getLineCount() {
        return this._list.size();
    }
    
    public synchronized void addLine(final String s) {
        this._list.add(this._drawer.decodeLine(s));
        if (this._vdirection == 0) {
            if (this._last == this._list.size() - 2) {
                this.setLast(this._last + 1);
            }
        }
        else if (this._vdirection == 1) {
            this._fullDraw = true;
            this.repaint();
        }
    }
    
    public synchronized void addLines(final String[] array) {
        final boolean b = this._list.size() - 1 == this._last;
        for (int i = 0; i < array.length; ++i) {
            this._list.add(this._drawer.decodeLine(array[i]));
        }
        if (this._vdirection == 0) {
            if (b) {
                this.setLast(this._list.size() - 1);
            }
        }
        else if (this._vdirection == 1) {
            this._fullDraw = true;
            this.repaint();
        }
    }
    
    private void reinit() {
        if (this._buffer != null) {
            this._buffer.flush();
        }
        this._buffer = null;
        this._results = new Hashtable();
    }
    
    public synchronized void dispose() {
        this.reinit();
    }
    
    public synchronized void clear() {
        this._list = new LimitedArray(this._maximumSize);
        this._last = this._list.size() - 1;
        this.setLeft(this._first = 0);
        this._width = this.getSize().width;
        this._fullDraw = true;
        this.repaint();
    }
    
    public synchronized void clear(final int maximumSize) {
        this._maximumSize = maximumSize;
        this.clear();
    }
    
    private void drawPart(final Graphics graphics, final int n, int n2, final int n3, int n4, final boolean b, final int n5, final int n6) {
        if (n2 < 0) {
            n4 += n2;
            n2 = 0;
        }
        if (this._backImage != null) {
            this.drawBackImage(graphics, n5, n6);
        }
        else {
            graphics.setColor(this._drawer.getColor(0));
            graphics.fillRect(n, n2, n3, n4);
        }
        if (this._vdirection == 0) {
            int last;
            int height;
            for (last = this._last, height = this.getSize().height; height > n2 + n4 && last >= 0; height -= this.getHeight(last--, graphics)) {}
            if (last != this._last) {
                height += this.getHeight(++last, graphics);
            }
            this.draw(graphics, 0, last, height, n2, n, n + n3 - 1, b);
        }
        else if (this._vdirection == 1) {
            int first;
            int n7;
            for (first = this._first, n7 = 0; n7 < n2 && first < this._list.size(); n7 += this.getHeight(first++, graphics)) {}
            if (first != this._first) {
                n7 -= this.getHeight(--first, graphics);
            }
            this.draw(graphics, first, this._list.size() - 1, n7, n2 + n4, n, n + n3 - 1, b);
        }
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this._toScrollX != 0 || this._toScrollY != 0) {
            this._fullDraw = true;
        }
        this.update(graphics);
    }
    
    private int getHeight(final Graphics graphics, int n, int n2) {
        if (n2 < n) {
            final int n3 = n;
            n = n2;
            n2 = n3;
        }
        int n4 = 0;
        for (int i = n; i <= n2; ++i) {
            n4 += this.getHeight(i, graphics);
        }
        return n4;
    }
    
    private void draw(final Graphics graphics, final int n, final int n2, int n3, final int n4, final int n5, final int n6, final boolean b) {
        final int width = this.getSize().width;
        this._addedCount = 0;
        DrawResult drawResult = new DrawResult();
        if (this._vdirection == 0) {
            StyledRectangle rectangle;
            for (int line = n2; line >= n && n3 > n4; n3 -= rectangle.height, --line) {
                DecodedLine emptyLine = (DecodedLine)this._list.get(line);
                if (emptyLine == null) {
                    emptyLine = this._emptyLine;
                }
                this._drawer.draw(emptyLine, graphics, -this._left, width - 1 - this._left, n3, n5, n6, b, this._wrap, drawResult);
                rectangle = drawResult.rectangle;
                if (rectangle.width > this._width) {
                    this._width = rectangle.width;
                    this._listeners.sendEventAsync("virtualSizeChanged", this);
                }
                if (b) {
                    final ResultPair resultPair = this._addedResults[this._addedCount++];
                    if (this._addedCount == this._addedResults.length) {
                        this.expandResult();
                    }
                    resultPair.line = line;
                    resultPair.result = drawResult;
                    drawResult = new DrawResult();
                }
            }
        }
        else {
            StyledRectangle rectangle2;
            for (int line2 = n; line2 <= n2 && n3 < n4; n3 += rectangle2.height, ++line2) {
                DecodedLine emptyLine2 = (DecodedLine)this._list.get(line2);
                if (emptyLine2 == null) {
                    emptyLine2 = this._emptyLine;
                }
                this._drawer.draw(emptyLine2, graphics, -this._left, width - 1 - this._left, n3, n5, n6, b, this._wrap, drawResult);
                rectangle2 = drawResult.rectangle;
                if (rectangle2.width > this._width) {
                    this._width = rectangle2.width;
                    this._listeners.sendEventAsync("virtualSizeChanged", this);
                }
                if (b) {
                    final ResultPair resultPair2 = this._addedResults[this._addedCount++];
                    if (this._addedCount == this._addedResults.length) {
                        this.expandResult();
                    }
                    resultPair2.line = line2;
                    resultPair2.result = drawResult;
                    drawResult = new DrawResult();
                }
            }
        }
    }
    
    private void addToScroll(final int n, final int n2) {
        this._toScrollX += n;
        this._toScrollY += n2;
    }
    
    private int getScrollX() {
        if (this._dragging) {
            return 0;
        }
        final int toScrollX = this._toScrollX;
        this._toScrollX = 0;
        return toScrollX;
    }
    
    private int getScrollY() {
        if (this._dragging) {
            return 0;
        }
        final int toScrollY = this._toScrollY;
        this._toScrollY = 0;
        return toScrollY;
    }
    
    private void scrollDrawItems(final int n, final int n2) {
        final int height = this.getSize().height;
        final Enumeration<Integer> keys = (Enumeration<Integer>)this._results.keys();
        while (keys.hasMoreElements()) {
            final Integer n3 = keys.nextElement();
            final DrawResult drawResult = this._results.get(n3);
            final StyledRectangle rectangle = drawResult.rectangle;
            rectangle.x += n;
            final StyledRectangle rectangle2 = drawResult.rectangle;
            rectangle2.y += n2;
            if (drawResult.rectangle.y + drawResult.rectangle.height < 0 || drawResult.rectangle.y >= height) {
                this._results.remove(n3);
            }
        }
    }
    
    private void combineItems() {
        for (int i = 0; i < this._addedCount; ++i) {
            final ResultPair resultPair = this._addedResults[i];
            this._results.put(new Integer(resultPair.line), resultPair.result);
        }
        this._addedCount = 0;
    }
    
    private DrawResultItem findItem(final int n, final int n2) {
        final Enumeration<DrawResult> elements = (Enumeration<DrawResult>)this._results.elements();
        while (elements.hasMoreElements()) {
            final DrawResult drawResult = elements.nextElement();
            if (drawResult.rectangle.contains(n, n2)) {
                final int n3 = n - drawResult.rectangle.x;
                final int n4 = n2 - drawResult.rectangle.y;
                for (int i = 0; i < drawResult.items.length; ++i) {
                    final DrawResultItem drawResultItem = drawResult.items[i];
                    if (drawResultItem.rectangle.contains(n3, n4)) {
                        return drawResultItem;
                    }
                }
            }
        }
        return null;
    }
    
    private int findLine(final int n) {
        final Enumeration<Integer> keys = this._results.keys();
        while (keys.hasMoreElements()) {
            final Integer n2 = keys.nextElement();
            final DrawResult drawResult = this._results.get(n2);
            if (drawResult.rectangle.y <= n && drawResult.rectangle.y + drawResult.rectangle.height > n) {
                return n2;
            }
        }
        return -1;
    }
    
    private int getHeight(final int n, final Graphics graphics) {
        final DrawResult drawResult = this._results.get(new Integer(n));
        if (drawResult != null) {
            return drawResult.rectangle.height;
        }
        final int width = this.getSize().width;
        DecodedLine emptyLine = (DecodedLine)this._list.get(n);
        if (emptyLine == null) {
            emptyLine = this._emptyLine;
        }
        return this._drawer.getHeight(emptyLine, graphics, -this._left, width, this._wrap);
    }
    
    private Color findColor(final String s) {
        return this._ircConfiguration.getASLColor(s, this._colormale, this._colorfemeale, this._colorundef);
    }
    
    private synchronized Vector getUpdateItems() {
        final Vector updateItems = this._updateItems;
        this._updateItems = null;
        return updateItems;
    }
    
    private synchronized boolean addToUpdateItems(final Integer n) {
        if (this._updateItems == null) {
            this._updateItems = new Vector();
        }
        for (int i = 0; i < this._updateItems.size(); ++i) {
            if (((Integer)this._updateItems.elementAt(i)).equals(n)) {
                return false;
            }
        }
        this._updateItems.insertElementAt(n, this._updateItems.size());
        return true;
    }
    
    public synchronized void update(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        if (height <= 0 || width <= 0) {
            return;
        }
        if (this._buffer != null) {
            if (this._bufferWidth < width || this._bufferHeight < height) {
                this.reinit();
            }
            if (this._bufferHeight > width * 1.5 || this._bufferHeight > height * 1.5) {
                this.reinit();
            }
            if (this._lastWidth != width || this._lastHeight != height) {
                this._fullDraw = true;
            }
        }
        this._lastWidth = width;
        this._lastHeight = height;
        if (this._buffer == null) {
            this._buffer = this.createImage(width, height);
            if (this._buffer == null) {
                this.repaint();
                return;
            }
            this._bufferWidth = width;
            this._bufferHeight = height;
            this._fullDraw = true;
        }
        final Graphics graphics2 = this._buffer.getGraphics();
        if (this._ircConfiguration.getB("style:backgroundimage")) {
            this._fullDraw = true;
        }
        final int scrollX = this.getScrollX();
        final int scrollY = this.getScrollY();
        final Vector updateItems = this.getUpdateItems();
        if (!this._fullDraw) {
            if (scrollX < 0) {
                graphics2.copyArea(0, 0, width + scrollX, height, -scrollX, 0);
                this.scrollDrawItems(-scrollX, 0);
                this.drawPart(graphics2, 0, 0, -scrollX, height, false, width, height);
            }
            else if (scrollX > 0) {
                graphics2.copyArea(scrollX, 0, width - scrollX, height, -scrollX, 0);
                this.scrollDrawItems(-scrollX, 0);
                this.drawPart(graphics2, width - scrollX, 0, scrollX, height, false, width, height);
            }
            if (scrollY > 0) {
                int n;
                if (this._vdirection == 0) {
                    n = this.getHeight(graphics2, this._last - scrollY + 1, this._last);
                }
                else {
                    n = this.getHeight(graphics2, this._first - scrollY, this._first - 1);
                }
                graphics2.copyArea(0, n, width, height - n, 0, -n);
                this.scrollDrawItems(0, -n);
                this.drawPart(graphics2, 0, height - n, width, n, true, width, height);
                this.combineItems();
            }
            else if (scrollY < 0) {
                int n2;
                if (this._vdirection == 0) {
                    n2 = this.getHeight(graphics2, this._last + 1, this._last - scrollY);
                }
                else {
                    n2 = this.getHeight(graphics2, this._first, this._first - scrollY - 1);
                }
                graphics2.copyArea(0, 0, width, height - n2, 0, n2);
                this.scrollDrawItems(0, n2);
                this.drawPart(graphics2, 0, 0, width, n2, true, width, height);
                this.combineItems();
            }
            if (updateItems != null) {
                for (int i = 0; i < updateItems.size(); ++i) {
                    final DrawResult drawResult = this._results.get(updateItems.elementAt(i));
                    if (drawResult != null) {
                        final StyledRectangle rectangle = drawResult.rectangle;
                        this.drawPart(graphics2, rectangle.x, rectangle.y, rectangle.width, rectangle.height, false, width, height);
                    }
                }
            }
        }
        else {
            this._results = new Hashtable();
            this.drawPart(graphics2, 0, 0, width, height, true, width, height);
            this.combineItems();
            this._fullDraw = false;
        }
        if (this._dragging) {
            this.makeXor(graphics2);
        }
        graphics.drawImage(this._buffer, 0, 0, this);
        if (this._dragging) {
            this.makeXor(graphics2);
        }
        if (!this._dragging && this._currentFloatItem != null && this._ircConfiguration.getB("style:floatingasl")) {
            int n3 = this._currentFloatItem.rectangle.x + this._currentFloatItem.parent.rectangle.x + 4;
            int n4 = this._currentFloatItem.rectangle.y + this._currentFloatItem.parent.rectangle.y;
            if (this._vdirection == 1) {
                n4 += 8;
            }
            else {
                n4 -= 8;
            }
            if (n4 < 0) {
                n4 = 0;
            }
            final String currentFloatText = this._currentFloatText;
            final String formatASL = this._ircConfiguration.formatASL(currentFloatText);
            if (formatASL.length() > 0) {
                final int stringWidth = graphics.getFontMetrics().stringWidth(formatASL);
                final int size = graphics.getFont().getSize();
                if (n4 + size + 5 > height) {
                    n4 = height - size - 5;
                }
                if (n3 + stringWidth + 5 > width) {
                    n3 = width - stringWidth - 5;
                }
                graphics.setColor(this.getAlphaColor(this.findColor(currentFloatText), this._ircConfiguration.getI("style:floatingaslalpha")));
                graphics.fillRect(n3, n4, stringWidth + 4, size + 4);
                graphics.setColor(Color.white);
                graphics.drawString(formatASL, n3 + 2, n4 + size);
            }
        }
        if (this._ircConfiguration.getB("style:highlightlinks") && !this._dragging && this._currentHighLightItem != null) {
            final int n5 = this._currentHighLightItem.rectangle.x + this._currentHighLightItem.parent.rectangle.x;
            final int n6 = this._currentHighLightItem.rectangle.y + this._currentHighLightItem.parent.rectangle.y;
            graphics.setXORMode(Color.white);
            graphics.setColor(Color.black);
            graphics.fillRect(n5, n6, this._currentHighLightItem.rectangle.width, this._currentHighLightItem.rectangle.height);
            graphics.setPaintMode();
        }
    }
    
    private Color getAlphaColor(final Color color, final int n) {
        try {
            return new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
        }
        catch (Throwable t) {
            return color;
        }
    }
    
    private void makeXor(final Graphics graphics) {
        String copiedString = "";
        final int n = this._draggedX - this._pressedX;
        final int n2 = this._draggedY - this._pressedY;
        final int pressedX = this._pressedX;
        final int pressedY = this._pressedY;
        graphics.setXORMode(Color.white);
        graphics.setColor(Color.black);
        final int line;
        int n3 = line = this.findLine(pressedY);
        DrawResult drawResult = this._results.get(new Integer(n3));
        if (drawResult == null) {
            this._copiedString = "";
            return;
        }
        int n4 = pressedX - drawResult.rectangle.x;
        int n5 = pressedY - drawResult.rectangle.y;
        DrawResultItem drawResultItem = null;
        int n6 = 0;
        int i;
        for (i = 0; i < drawResult.items.length; ++i) {
            if (drawResult.items[i].rectangle.contains(n4, n5)) {
                drawResultItem = drawResult.items[i];
                n6 = i;
            }
        }
        if (drawResultItem == null || (n4 + n < drawResultItem.rectangle.x && n5 + n2 < drawResultItem.rectangle.y) || n5 + n2 < drawResultItem.rectangle.y) {
            this._copiedString = "";
            return;
        }
        int j = 0;
        while (j == 0) {
            copiedString += drawResultItem.originalstrippedword;
            final StyledRectangle rectangle = drawResultItem.rectangle;
            graphics.fillRect(rectangle.x + drawResult.rectangle.x, rectangle.y + drawResult.rectangle.y, rectangle.width, rectangle.height);
            if ((n3 != line || i != n6) && drawResultItem.rectangle.contains(n4 + n, n5 + n2)) {
                break;
            }
            if (++n6 >= drawResult.items.length) {
                n6 = 0;
                ++n3;
                drawResult = this._results.get(new Integer(n3));
                if (drawResult == null) {
                    break;
                }
                n4 = pressedX - drawResult.rectangle.x;
                n5 = pressedY - drawResult.rectangle.y;
                copiedString += "\n";
            }
            drawResultItem = drawResult.items[n6];
            if (drawResultItem.rectangle.y > n5 + n2) {
                j = 1;
            }
            if (this._hdirection == 0 && drawResultItem.rectangle.x > n4 + n && drawResultItem.rectangle.y + drawResultItem.rectangle.height > n5 + n2) {
                j = 1;
            }
            if (this._hdirection != 1 || drawResultItem.rectangle.x + drawResultItem.rectangle.width >= n4 + n || drawResultItem.rectangle.y + drawResultItem.rectangle.height <= n5 + n2) {
                continue;
            }
            j = 1;
        }
        this._copiedString = copiedString;
        graphics.setPaintMode();
    }
    
    public synchronized void mouseClicked(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x1) != 0x0) {
            String string = "";
            for (int i = 0; i < this._list.size(); ++i) {
                DecodedLine emptyLine = (DecodedLine)this._list.get(i);
                if (emptyLine == null) {
                    emptyLine = this._emptyLine;
                }
                string = string + emptyLine.original + "\n";
            }
            this._listeners.sendEventAsync("copyEvent", this, string, mouseEvent);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this._currentFloatItem = null;
        this._currentItem = null;
        this._currentHighLightItem = null;
        this.defCursor();
        this.mouseMoved(mouseEvent);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this._currentFloatItem = null;
        this._currentItem = null;
        this._currentHighLightItem = null;
        this.repaint();
    }
    
    public synchronized void mousePressed(final MouseEvent mouseEvent) {
        this._pressedX = mouseEvent.getX();
        this._pressedY = mouseEvent.getY();
        this._draggedX = this._pressedX;
        this._draggedY = this._pressedY;
        this._copiedString = "";
        this._dragging = false;
        this._currentItem = null;
        final DrawResultItem item = this.findItem(mouseEvent.getX(), mouseEvent.getY());
        if (item != null) {
            final String type = this._catcher.getType(item.item);
            if (type != null) {
                if (type.equals("channel")) {
                    this._listeners.sendEventAsync("channelEvent", this, item.item, mouseEvent);
                }
                else if (type.equals("url")) {
                    this._listeners.sendEventAsync("URLEvent", this, item.item, mouseEvent);
                }
                else if (type.equals("wordlist")) {
                    this._listeners.sendEventAsync("nickEvent", this, item.item, mouseEvent);
                }
            }
        }
    }
    
    public synchronized void mouseReleased(final MouseEvent mouseEvent) {
        if (this._dragging) {
            this._dragging = false;
            this.repaint();
            if (this._copiedString.length() > 0) {
                this._listeners.sendEventAsync("copyEvent", this, this._copiedString, mouseEvent);
            }
        }
    }
    
    public synchronized void mouseDragged(final MouseEvent mouseEvent) {
        this._draggedX = mouseEvent.getX();
        this._draggedY = mouseEvent.getY();
        this._dragging = true;
        final DrawResultItem item = this.findItem(mouseEvent.getX(), mouseEvent.getY());
        if (item != this._currentItem) {
            this._currentItem = item;
            this.repaint();
        }
    }
    
    private void handCursor() {
        if (!this.getCursor().equals(new Cursor(12))) {
            this.setCursor(new Cursor(12));
        }
    }
    
    private void defCursor() {
        if (!this.getCursor().equals(new Cursor(0))) {
            this.setCursor(new Cursor(0));
        }
    }
    
    private boolean sameItem(final DrawResultItem drawResultItem, final DrawResultItem drawResultItem2) {
        return (drawResultItem == null && drawResultItem2 == null) || (drawResultItem != null && drawResultItem2 != null && drawResultItem.equals(drawResultItem2));
    }
    
    public synchronized void mouseMoved(final MouseEvent mouseEvent) {
        final DrawResultItem item = this.findItem(mouseEvent.getX(), mouseEvent.getY());
        final DrawResultItem currentFloatItem = this._currentFloatItem;
        final DrawResultItem currentHighLightItem = this._currentHighLightItem;
        if (!this.sameItem(item, this._currentItem)) {
            this._currentItem = item;
            this._currentFloatItem = null;
            this._currentHighLightItem = null;
            if (item != null) {
                final String type = this._catcher.getType(item.item);
                if (type != null) {
                    this.handCursor();
                    if (type.equals("wordlist")) {
                        String currentFloatText = this._nickInfos.get(item.item.toLowerCase(Locale.ENGLISH));
                        if (currentFloatText == null) {
                            currentFloatText = "";
                        }
                        if (currentFloatText.length() == 0) {
                            this._currentFloatItem = null;
                        }
                        else {
                            this._currentFloatItem = item;
                            this._currentFloatText = currentFloatText;
                        }
                    }
                    if (this._currentFloatItem == null) {
                        this._currentHighLightItem = item;
                    }
                }
                else {
                    this.defCursor();
                }
            }
            else {
                this.defCursor();
            }
        }
        boolean b = false;
        if (!this.sameItem(currentFloatItem, this._currentFloatItem) && this._ircConfiguration.getB("style:floatingasl")) {
            b = true;
        }
        if (!this.sameItem(currentHighLightItem, this._currentHighLightItem)) {
            b = true;
        }
        if (b) {
            this.repaint();
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        this._fullDraw = true;
        this.repaint();
        return true;
    }
    
    public synchronized Boolean displayUpdated(final Object o, final Integer n) {
        boolean b = false;
        final Enumeration<Integer> keys = this._results.keys();
        while (keys.hasMoreElements()) {
            final Integer n2 = keys.nextElement();
            final DrawResult drawResult = this._results.get(n2);
            if (drawResult.updateHandles != null) {
                for (int i = 0; i < drawResult.updateHandles.size(); ++i) {
                    if (drawResult.updateHandles.elementAt(i).equals(o)) {
                        if ((n & 0x2) != 0x0) {
                            this._fullDraw = true;
                            this.repaint();
                            return Boolean.TRUE;
                        }
                        b = true;
                        this.addToUpdateItems(n2);
                        if (System.currentTimeMillis() - this._lastRefresh > 10L || (n & 0x1) != 0x0) {
                            this.repaint();
                            this._lastRefresh = System.currentTimeMillis();
                        }
                    }
                }
            }
        }
        if (b) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
