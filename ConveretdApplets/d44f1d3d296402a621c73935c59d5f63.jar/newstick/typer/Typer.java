// 
// Decompiled by Procyon v0.5.30
// 

package newstick.typer;

import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.Point;
import java.awt.Component;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class Typer implements MouseListener, MouseMotionListener, Runnable
{
    private static String ENDOFLINE;
    private Component component;
    private Point anchorPoint;
    private Vector texts;
    private Hashtable positionTexts;
    private PositionString currentPositionText;
    private TyperDataText currentLinked;
    private TyperDataText underMouse;
    private boolean stopTick;
    private boolean stringExchange;
    private boolean canNewString;
    private FontMetrics currentFontMetrics;
    private ITyperAction typerAction;
    private TyperDataText[] newContent;
    private Thread threadTyperExchange;
    private Rectangle area;
    private Graphics graphics;
    private Font titleFont;
    private Color titleColor;
    private Color titleHighliteColor;
    private Color foreColor;
    private Color foreHighliteColor;
    private Color backColor;
    private int afterTitleSpace;
    private int afterTextSpace;
    private int lineSpace;
    private int charSpace;
    private int lineSpeed;
    private int charSpeed;
    private int scrollSpeed;
    private float startPos;
    private Vector linePositions;
    private int resetTopLines;
    private Point currentPoint;
    
    private void changeLocationTexts(final int n, final int n2) {
        final Enumeration<PositionText> elements = this.positionTexts.elements();
        while (elements.hasMoreElements()) {
            final PositionText positionText = elements.nextElement();
            if (positionText.title != null) {
                positionText.title.changeLocation(n, n2);
            }
            if (positionText.text != null) {
                positionText.text.changeLocation(n, n2);
            }
        }
    }
    
    private void sleep(final int n) {
        if (n > 0) {
            try {
                Thread.currentThread();
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void sleepTick() {
        while (this.stopTick) {
            this.sleep(10);
        }
    }
    
    private void writeLink(final PositionString positionString, String replace, final FontMetrics fontMetrics, final Color color) {
        replace = this.replace(replace, Typer.ENDOFLINE, new String(new char[] { '\n' }));
        final char[] charArray = replace.toCharArray();
        final Font font = this.graphics.getFont();
        this.graphics.setFont(fontMetrics.getFont());
        final Color color2 = this.graphics.getColor();
        if (positionString.countTyped > 0) {
            this.graphics.setColor(this.backColor);
            this.graphics.fillRect(positionString.getStartPoint().x, positionString.getStartPoint().y - fontMetrics.getAscent(), positionString.getDimension().width, positionString.getDimension().height);
        }
        this.graphics.setColor(color);
        final int width = this.area.width;
        final int height = this.area.height;
        int n = positionString.getStartPoint().x;
        int y = positionString.getStartPoint().y;
        final int heightLine = this.getHeightLine(fontMetrics);
        for (int i = 0; i < positionString.countTyped; ++i) {
            int n2 = 0;
            if (charArray[i] != '\n') {
                if (Character.isWhitespace(charArray[i])) {
                    int n3;
                    for (n3 = 1; n3 + i < charArray.length && !Character.isWhitespace(charArray[n3 + i]); ++n3) {}
                    n2 = fontMetrics.charsWidth(charArray, i, n3) + (n3 - 1) * this.charSpace;
                }
                else {
                    n2 = fontMetrics.charWidth(charArray[i]) + this.charSpace;
                }
            }
            if (charArray[i] == '\n' || n + n2 + this.charSpace > width) {
                n = this.area.x;
                y += heightLine;
                if (charArray[i] != '\n' && Character.isWhitespace(charArray[i]) && i < charArray.length - 1) {
                    ++i;
                }
            }
            if (y + fontMetrics.getDescent() > height || y > this.currentPoint.y) {
                break;
            }
            if (charArray[i] != '\n') {
                this.graphics.drawChars(charArray, i, 1, n, y);
                n += fontMetrics.charWidth(charArray[i]) + this.charSpace;
            }
        }
        this.component.repaint();
        this.graphics.setColor(color2);
        this.graphics.setFont(font);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    private void highliteLink(final String s, final PositionString positionString, final FontMetrics fontMetrics, final Color color, final Color color2) {
        if (s != null && positionString != null && positionString.countTyped > 0 && color != null && (color2 == null || !color2.equals(color))) {
            this.writeLink(positionString, s, fontMetrics, color);
        }
    }
    
    private void underLink(final TyperDataText currentLinked) {
        if (this.component.getCursor().getType() != 12) {
            final PositionText positionText = this.positionTexts.get(currentLinked);
            this.highliteLink(currentLinked.title, positionText.title, positionText.titleFontMet, this.titleHighliteColor, this.titleColor);
            this.highliteLink(currentLinked.text, positionText.text, positionText.textFontMet, this.foreHighliteColor, this.foreColor);
            this.currentLinked = currentLinked;
            this.component.setCursor(new Cursor(12));
        }
    }
    
    private void typeString(String replace, final int n) {
        if (replace == null) {
            return;
        }
        replace = this.replace(replace, Typer.ENDOFLINE, new String(new char[] { '\n' }));
        final char[] charArray = replace.toCharArray();
        final int width = this.area.width;
        final int height = this.area.height;
        final int heightLine = this.getHeightLine(this.currentFontMetrics);
        if (this.currentPoint.y + this.currentFontMetrics.getDescent() > height) {
            this.scrollPage(n);
        }
        int n2 = 1;
        for (int i = 0; i < charArray.length; ++i) {
            int n3 = 0;
            if (charArray[i] != '\n') {
                if (Character.isWhitespace(charArray[i])) {
                    int n4;
                    for (n4 = 1; n4 + i < charArray.length && !Character.isWhitespace(charArray[n4 + i]); ++n4) {}
                    n3 = this.currentFontMetrics.charsWidth(charArray, i, n4) + (n4 - 1) * this.charSpace;
                }
                else {
                    n3 = this.currentFontMetrics.charWidth(charArray[i]) + this.charSpace;
                }
            }
            if (charArray[i] == '\n' || this.currentPoint.x + n3 + this.charSpace > width) {
                this.currentPoint.x = this.area.x;
                final Point currentPoint = this.currentPoint;
                currentPoint.y += heightLine;
                this.sleep(this.lineSpeed);
                if (charArray[i] != '\n' && Character.isWhitespace(charArray[i]) && i < charArray.length - 1) {
                    ++i;
                }
                this.currentPositionText.changeSize(0, heightLine);
                n2 = 1;
            }
            if (this.currentPoint.y + this.currentFontMetrics.getDescent() > height) {
                this.scrollPage(n);
            }
            if (this.stopTick) {
                this.sleepTick();
            }
            if (charArray[i] != '\n') {
                this.graphics.drawChars(charArray, i, 1, this.currentPoint.x, this.currentPoint.y);
            }
            this.currentPositionText.countTyped = i + 1;
            this.component.repaint();
            if (n2 != 0) {
                n2 = 0;
                this.nextLinePos();
            }
            if (this.stringExchange) {
                return;
            }
            this.sleep(this.charSpeed);
            if (charArray[i] != '\n') {
                final Point currentPoint2 = this.currentPoint;
                currentPoint2.x += this.currentFontMetrics.charWidth(charArray[i]) + this.charSpace;
            }
        }
    }
    
    public void type() {
        if (this.graphics == null) {
            throw new NullPointerException("Field graphics is null. Use setGraphics() method.");
        }
        if (this.area == null) {
            throw new NullPointerException("Field area is null. Use setArea() method.");
        }
        if (this.foreColor != null && !this.graphics.getColor().equals(this.foreColor)) {
            this.graphics.setColor(this.foreColor);
        }
        else {
            this.foreColor = this.graphics.getColor();
        }
        if (this.backColor == null) {
            this.backColor = this.graphics.getColor();
        }
        this.currentFontMetrics = this.graphics.getFontMetrics();
        this.currentPoint.x = this.area.x;
        this.currentPoint.y = -1;
        if (this.isEmpty()) {
            this.sleep(10);
            return;
        }
        final Enumeration elements = this.texts.elements();
        while (elements.hasMoreElements() && !this.stringExchange) {
            final TyperDataText typerDataText = elements.nextElement();
            PositionText positionText;
            if (this.positionTexts.contains(typerDataText)) {
                positionText = this.positionTexts.get(typerDataText);
            }
            else {
                positionText = new PositionText();
                this.positionTexts.put(typerDataText, positionText);
            }
            if (typerDataText.isTitle()) {
                Font font = null;
                if (this.titleFont != null) {
                    font = this.graphics.getFont();
                    this.graphics.setFont(this.titleFont);
                    this.currentFontMetrics = this.graphics.getFontMetrics();
                }
                Color color = null;
                if (this.titleColor != null) {
                    color = this.graphics.getColor();
                    this.graphics.setColor(this.titleColor);
                }
                if (positionText.title == null) {
                    positionText.title = new PositionString(this.area);
                }
                positionText.titleFontMet = this.currentFontMetrics;
                (this.currentPositionText = positionText.title).setStartPoint(new Point(this.currentPoint.x, this.currentPoint.y));
                this.currentPositionText.setDimension(new Dimension(this.area.width, this.currentFontMetrics.getHeight()));
                if (!this.stringExchange) {
                    if (this.currentPoint.y == -1) {
                        if (this.startPos == 0.0f) {
                            this.currentPoint.y = this.currentFontMetrics.getAscent();
                        }
                        else {
                            this.currentPoint.y = this.area.height - this.currentFontMetrics.getDescent();
                        }
                        this.currentPositionText.setStartPoint(new Point(this.currentPoint.x, this.currentPoint.y));
                    }
                    this.typeString(typerDataText.title, this.resetTopLines);
                    final Point currentPoint = this.currentPoint;
                    currentPoint.y += this.getHeightLine(this.currentFontMetrics) + this.afterTitleSpace;
                    this.currentPoint.x = this.area.x;
                }
                if (this.titleColor != null) {
                    this.graphics.setColor(color);
                }
                if (this.titleFont != null) {
                    this.graphics.setFont(font);
                    this.currentFontMetrics = this.graphics.getFontMetrics();
                }
            }
            if (typerDataText.text != null) {
                if (positionText.text == null) {
                    positionText.text = new PositionString(this.area);
                }
                positionText.textFontMet = this.currentFontMetrics;
                (this.currentPositionText = positionText.text).setStartPoint(new Point(this.currentPoint.x, this.currentPoint.y));
                this.currentPositionText.setDimension(new Dimension(this.area.width, this.currentFontMetrics.getHeight()));
                if (this.stringExchange) {
                    continue;
                }
                if (this.currentPoint.y == -1) {
                    if (this.startPos == 0.0f) {
                        this.currentPoint.y = this.currentFontMetrics.getAscent();
                    }
                    else {
                        this.currentPoint.y = this.area.height - this.currentFontMetrics.getDescent();
                    }
                    this.currentPositionText.setStartPoint(new Point(this.currentPoint.x, this.currentPoint.y));
                }
                this.typeString(typerDataText.text, this.resetTopLines);
                final Point currentPoint2 = this.currentPoint;
                currentPoint2.y += this.getHeightLine(this.currentFontMetrics) + this.afterTextSpace;
                this.currentPoint.x = this.area.x;
            }
        }
        if (!this.stringExchange) {
            this.scroll(this.area, this.backColor, this.currentPoint.y + this.currentFontMetrics.getDescent());
        }
        if (this.stringExchange) {
            this.canNewString = true;
        }
    }
    
    private String replace(final String s, final String s2, final String s3) {
        final StringBuffer sb = new StringBuffer();
        int n;
        int index;
        for (n = 0; (index = s.indexOf(s2, n)) != -1; n = index + s2.length()) {
            sb.append(s.substring(n, index));
            sb.append(s3);
        }
        sb.append(s.substring(n));
        return sb.toString();
    }
    
    public void newContent(final TyperDataText[] newContent) {
        this.stringExchange = true;
        this.canNewString = false;
        this.newContent = newContent;
        if (this.threadTyperExchange != null) {
            this.threadTyperExchange.stop();
        }
        (this.threadTyperExchange = new Thread(this)).start();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.underMouse = null;
        this.notUnderLink();
        this.resumeTick();
        this.component.setCursor(Cursor.getDefaultCursor());
    }
    
    public void setTitleHighliteColor(final Color titleHighliteColor) {
        this.titleHighliteColor = titleHighliteColor;
    }
    
    public void setForeHighliteColor(final Color foreHighliteColor) {
        this.foreHighliteColor = foreHighliteColor;
    }
    
    public void setBackColor(final Color backColor) {
        this.backColor = backColor;
    }
    
    public void setScrollSpeed(final int scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
    }
    
    public int getHeightLine(final FontMetrics fontMetrics) {
        return fontMetrics.getHeight() + this.lineSpace;
    }
    
    private void scrollPage(int n) {
        if (n < 0) {
            n = 0;
        }
        int n2;
        if (this.startPos == 0.0f) {
            if (n > 0 && this.linePositions != null && this.linePositions.size() >= n) {
                final PositionLine positionLine = this.linePositions.elementAt(this.linePositions.size() - n);
                n2 = this.area.height + (positionLine.point.y - this.area.height) + positionLine.fontDescent - positionLine.fontHeight;
            }
            else {
                n2 = this.area.height + (this.currentPoint.y - this.area.height) + this.currentFontMetrics.getDescent() - this.currentFontMetrics.getHeight();
            }
            if (this.linePositions != null) {
                this.linePositions.removeAllElements();
            }
            if (this.currentPoint.y - n2 + this.currentFontMetrics.getDescent() > this.area.height) {
                n2 = this.currentPoint.y - this.area.height + this.currentFontMetrics.getDescent();
            }
        }
        else {
            n2 = this.currentPoint.y - this.area.height + this.currentFontMetrics.getDescent();
        }
        this.scroll(this.area, this.backColor, n2);
        final Point currentPoint = this.currentPoint;
        currentPoint.y -= n2;
    }
    
    public boolean isEmpty() {
        return this.texts.size() == 0;
    }
    
    private void nextLinePos() {
        if (this.resetTopLines > 0) {
            if (this.linePositions == null) {
                this.linePositions = new Vector(30, 10);
            }
            this.linePositions.addElement(new PositionLine(new Point(this.currentPoint), this.currentFontMetrics));
        }
    }
    
    private void typeStringO(final String s, final int n) {
        if (s == null) {
            return;
        }
        final char[] charArray = s.toCharArray();
        final int width = this.area.width;
        final int height = this.area.height;
        final int heightLine = this.getHeightLine(this.currentFontMetrics);
        if (this.currentPoint.y + this.currentFontMetrics.getDescent() > height) {
            this.scrollPage(n);
        }
        int n2 = 1;
        for (int i = 0; i < charArray.length; ++i) {
            int n4;
            if (Character.isWhitespace(charArray[i])) {
                int n3;
                for (n3 = 1; n3 + i < charArray.length && !Character.isWhitespace(charArray[n3 + i]); ++n3) {}
                n4 = this.currentFontMetrics.charsWidth(charArray, i, n3) + (n3 - 1) * this.charSpace;
            }
            else {
                n4 = this.currentFontMetrics.charWidth(charArray[i]) + this.charSpace;
            }
            if (this.currentPoint.x + n4 + this.charSpace > width) {
                this.currentPoint.x = this.area.x;
                final Point currentPoint = this.currentPoint;
                currentPoint.y += heightLine;
                this.sleep(this.lineSpeed);
                if (Character.isWhitespace(charArray[i]) && i < charArray.length - 1) {
                    ++i;
                }
                this.currentPositionText.changeSize(0, heightLine);
                n2 = 1;
            }
            if (this.currentPoint.y + this.currentFontMetrics.getDescent() > height) {
                this.scrollPage(n);
            }
            if (this.stopTick) {
                this.sleepTick();
            }
            this.graphics.drawChars(charArray, i, 1, this.currentPoint.x, this.currentPoint.y);
            this.currentPositionText.countTyped = i + 1;
            this.component.repaint();
            if (n2 != 0) {
                n2 = 0;
                this.nextLinePos();
            }
            if (this.stringExchange) {
                return;
            }
            this.sleep(this.charSpeed);
            final Point currentPoint2 = this.currentPoint;
            currentPoint2.x += this.currentFontMetrics.charWidth(charArray[i]) + this.charSpace;
        }
    }
    
    public void setAfterTextSpace(final int afterTextSpace) {
        this.afterTextSpace = afterTextSpace;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.isConsumed()) {
            return;
        }
        if (this.underMouse != null && this.underMouse.url != null && this.area.contains(this.translatePoint(mouseEvent.getPoint())) && this.typerAction != null) {
            this.typerAction.typerAction(this.underMouse.url);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public Typer(final Component component, final Point anchorPoint) {
        this.currentPoint = new Point();
        (this.component = component).addMouseListener(this);
        component.addMouseMotionListener(this);
        if (component instanceof ITyperAction) {
            this.typerAction = (ITyperAction)component;
        }
        this.anchorPoint = anchorPoint;
        this.texts = new Vector(10);
        this.positionTexts = new Hashtable(10);
        this.lineSpace = 0;
        this.charSpace = 0;
        this.lineSpeed = 300;
        this.charSpeed = 70;
        this.scrollSpeed = 15;
        this.afterTitleSpace = 5;
        this.afterTextSpace = 15;
        this.stopTick = false;
        this.stringExchange = false;
        this.canNewString = false;
        this.currentLinked = null;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (mouseEvent.isConsumed()) {
            return;
        }
        final Point translatePoint = this.translatePoint(mouseEvent.getPoint());
        if (this.area.contains(translatePoint)) {
            this.stopTick();
            if (this.underMouse == null) {
                this.underMouse = this.locateUnderMouse(translatePoint);
            }
            if (this.underMouse != null) {
                if (this.dataTextIsUnderMouse(this.underMouse, translatePoint) && this.underMouse.isUrl()) {
                    this.underLink(this.underMouse);
                }
                else {
                    this.underMouse = this.locateUnderMouse(translatePoint);
                    if (this.underMouse != null) {
                        if (this.dataTextIsUnderMouse(this.underMouse, translatePoint) && this.underMouse.isUrl()) {
                            this.underLink(this.underMouse);
                        }
                        else {
                            this.notUnderLink();
                        }
                    }
                    else {
                        this.notUnderLink();
                    }
                }
            }
            else {
                this.notUnderLink();
            }
        }
        else {
            this.notUnderLink();
            this.resumeTick();
            this.component.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void setTitleColor(final Color titleColor) {
        this.titleColor = titleColor;
    }
    
    public void setForeColor(final Color foreColor) {
        this.foreColor = foreColor;
    }
    
    private void scroll(final Rectangle rectangle, final Color color, final int n) {
        final Color color2 = this.graphics.getColor();
        for (int n2 = 0; n2 < n && !this.stringExchange; ++n2) {
            this.graphics.copyArea(rectangle.x, rectangle.y, rectangle.width, rectangle.height, rectangle.x, -1);
            this.graphics.setColor(color);
            this.graphics.fillRect(rectangle.x, rectangle.height - n2 - 1, rectangle.width, 1);
            this.graphics.setColor(color2);
            this.changeLocationTexts(0, -1);
            this.sleep(this.scrollSpeed);
            this.component.repaint();
            if (this.stopTick) {
                this.sleepTick();
            }
        }
    }
    
    private TyperDataText locateUnderMouse(final Point point) {
        for (int size = this.texts.size(), i = 0; i < size; ++i) {
            final TyperDataText typerDataText = this.texts.elementAt(i);
            if (this.dataTextIsUnderMouse(typerDataText, point)) {
                return typerDataText;
            }
        }
        return null;
    }
    
    public void resumeTick() {
        this.stopTick = false;
    }
    
    private boolean dataTextIsUnderMouse(final TyperDataText typerDataText, final Point point) {
        final PositionText positionText = this.positionTexts.get(typerDataText);
        return positionText != null && positionText.contains(point);
    }
    
    public void setTitleFont(final Font titleFont) {
        this.titleFont = titleFont;
    }
    
    public void setGraphics(final Graphics graphics) {
        this.graphics = graphics;
    }
    
    public void setStartPos(final float startPos) {
        this.startPos = startPos;
    }
    
    private void notUnderLink() {
        if (this.component.getCursor().getType() != 0 && this.currentLinked != null) {
            final PositionText positionText = this.positionTexts.get(this.currentLinked);
            this.highliteLink(this.currentLinked.title, positionText.title, positionText.titleFontMet, this.titleColor, null);
            this.highliteLink(this.currentLinked.text, positionText.text, positionText.textFontMet, this.foreColor, null);
            this.currentLinked = null;
            this.component.setCursor(new Cursor(0));
        }
    }
    
    static {
        Typer.ENDOFLINE = "<br>";
    }
    
    private Point typeStringOld(final Point point, final String s, final FontMetrics fontMetrics, final int n, final Color color) {
        if (s == null) {
            return point;
        }
        final char[] charArray = s.toCharArray();
        this.graphics.getColor();
        final int width = this.area.width;
        final int height = this.area.height;
        int n2 = point.x;
        int y = point.y;
        int n3 = n2;
        final int heightLine = this.getHeightLine(this.currentFontMetrics);
        for (int i = 0; i < charArray.length; ++i) {
            if (this.stopTick) {
                this.sleepTick();
            }
            int n5;
            if (Character.isWhitespace(charArray[i])) {
                int n4;
                for (n4 = 1; n4 + i < charArray.length && !Character.isWhitespace(charArray[n4 + i]); ++n4) {}
                n5 = fontMetrics.charsWidth(charArray, i, n4) + (n4 - 1) * this.charSpace;
            }
            else {
                n5 = fontMetrics.charWidth(charArray[i]) + this.charSpace;
            }
            if (n2 + n5 + this.charSpace > width) {
                n2 = this.area.x;
                y += heightLine;
                this.currentPositionText.changeSize(0, heightLine);
                if (Character.isWhitespace(charArray[i]) && i < charArray.length - 1) {
                    ++i;
                }
            }
            if (y > height) {
                if (n == -1) {
                    this.scroll(this.area, color, heightLine);
                    this.sleep(this.lineSpeed);
                    this.changeLocationTexts(0, -heightLine);
                    if (!this.currentPositionText.inArea()) {
                        this.currentPositionText.changeLocation(0, -heightLine);
                    }
                    y -= heightLine;
                }
                else {
                    y -= y + this.currentFontMetrics.getDescent() - heightLine * (n + 1);
                }
            }
            if (y > height) {
                final int n6 = y - height + fontMetrics.getDescent();
                this.scroll(this.area, color, n6);
                y -= n6;
                this.changeLocationTexts(0, -n6);
                this.currentPositionText.changeLocation(0, n6);
            }
            this.graphics.drawChars(charArray, i, 1, n2, y);
            n3 = n2;
            this.component.repaint();
            if (this.stringExchange) {
                return new Point(n3, y);
            }
            this.sleep(this.charSpeed);
            n2 += fontMetrics.charWidth(charArray[i]) + this.charSpace;
        }
        return new Point(n3, y);
    }
    
    public void setResetTopLines(final int resetTopLines) {
        this.resetTopLines = resetTopLines;
    }
    
    public void stopTick() {
        this.stopTick = true;
    }
    
    public void setArea(final Rectangle area) {
        this.area = area;
    }
    
    public void setLineSpace(final int lineSpace) {
        this.lineSpace = lineSpace;
    }
    
    public void setCharSpace(final int charSpace) {
        this.charSpace = charSpace;
    }
    
    public void clear() {
        final Color color = this.graphics.getColor();
        if (this.backColor != null) {
            this.graphics.setColor(this.backColor);
        }
        this.graphics.fillRect(0, 0, this.area.width, this.area.height);
        this.graphics.setColor(color);
    }
    
    public void run() {
        if (!this.isEmpty()) {
            while (!this.canNewString) {
                this.sleep(50);
            }
        }
        this.clear();
        this.component.repaint();
        this.clearContent();
        this.sleep(300);
        if (this.newContent != null) {
            for (int i = 0; i < this.newContent.length; ++i) {
                this.texts.addElement(this.newContent[i]);
            }
        }
        this.stringExchange = false;
    }
    
    public void clearContent() {
        this.texts.removeAllElements();
    }
    
    public void setLineSpeed(final int lineSpeed) {
        this.lineSpeed = lineSpeed;
    }
    
    public void setCharSpeed(final int charSpeed) {
        this.charSpeed = charSpeed;
    }
    
    private Point translatePoint(final Point point) {
        return new Point(point.x - this.anchorPoint.x, point.y - this.anchorPoint.y);
    }
    
    public void setAfterTitleSpace(final int afterTitleSpace) {
        this.afterTitleSpace = afterTitleSpace;
    }
}
