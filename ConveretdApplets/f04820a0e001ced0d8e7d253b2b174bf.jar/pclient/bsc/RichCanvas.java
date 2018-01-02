// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import com.pchat.sc.MsgOptions;
import pclient.shd.Config;
import java.awt.Color;
import java.util.Vector;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.event.ComponentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public class RichCanvas extends Canvas implements MouseListener, MouseMotionListener, ComponentListener
{
    private static final Cursor normalCursor;
    private static final Cursor handCursor;
    private static final int CURSOR_NORMAL = 0;
    private static final int CURSOR_HAND = 1;
    private int cursorState;
    private float exposePercent;
    private int MAX_LINES;
    private int TRIM_LINES;
    private Image imageBuffer;
    private Vector chatLines;
    private Vector allItems;
    private Formatter richFormatter;
    private int cachedItem;
    private Color bgColor;
    private boolean contentChanged;
    private static RichCanvas anInstance;
    private Config paraConf;
    
    public RichCanvas(final Config paraConf) {
        this.cursorState = 0;
        this.exposePercent = 1.0f;
        this.MAX_LINES = 512;
        this.TRIM_LINES = 128;
        this.imageBuffer = null;
        this.richFormatter = null;
        this.cachedItem = 0;
        this.contentChanged = false;
        this.paraConf = paraConf;
        this.chatLines = new Vector(64);
        this.allItems = new Vector(64);
        this.richFormatter = new Formatter(paraConf);
        this.bgColor = this.getBgColor();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        RichCanvas.anInstance = this;
    }
    
    public static Image generateImage(final int n, final int n2) {
        Image image = null;
        if (RichCanvas.anInstance != null) {
            image = RichCanvas.anInstance.createImage(n, n2);
        }
        return image;
    }
    
    public void ignoreURL() {
        this.richFormatter.ignoreURL();
    }
    
    public Color getBackground() {
        return this.bgColor;
    }
    
    public void setForeground(final Color textColor) {
        if (this.richFormatter != null) {
            this.richFormatter.textColor = textColor;
        }
        this.contentChanged = true;
        this.updateImageBuffer();
        this.repaint();
    }
    
    public void setBackground(final Color bgColor) {
        this.bgColor = bgColor;
        this.contentChanged = true;
        this.updateImageBuffer();
        this.repaint();
    }
    
    public void setFontSize(final int n) {
        this.richFormatter.changeFontSize(n);
        this.contentChanged = true;
        this.updateImageBuffer();
        this.repaint();
    }
    
    public void setPercentage(final float exposePercent) {
        if (exposePercent < 0.0f) {
            this.exposePercent = 0.0f;
        }
        else if (exposePercent > 1.0f) {
            this.exposePercent = 1.0f;
        }
        else {
            this.exposePercent = exposePercent;
        }
        this.updateImageBuffer();
        this.repaint();
    }
    
    public synchronized void addChatLine(final String fromUser, final String message, final String timestamp, final MsgOptions mop) {
        final ChatMessage chatMessage = new ChatMessage();
        chatMessage.fromUser = fromUser;
        chatMessage.message = message;
        chatMessage.timestamp = timestamp;
        chatMessage.mop = mop;
        this.addMessage(chatMessage);
        this.updateImageBuffer();
        this.repaint();
    }
    
    public synchronized void appendText(final String message, final MsgOptions mop) {
        final ChatMessage chatMessage = new ChatMessage();
        chatMessage.fromUser = null;
        chatMessage.message = message;
        chatMessage.mop = mop;
        this.addMessage(chatMessage);
        this.updateImageBuffer();
        this.repaint();
    }
    
    public void modUserQuestion(final String fromUser, final String message, final String timestamp) {
        final ChatMessage chatMessage = new ChatMessage();
        chatMessage.isModerated = true;
        chatMessage.isHost = false;
        chatMessage.fromUser = fromUser;
        chatMessage.message = message;
        chatMessage.timestamp = timestamp;
        this.addMessage(chatMessage);
        this.updateImageBuffer();
        this.repaint();
    }
    
    public void modAnswer(final String fromUser, final String message, final String timestamp, final boolean b) {
        final ChatMessage chatMessage = new ChatMessage();
        chatMessage.isModerated = true;
        chatMessage.isHost = true;
        chatMessage.fromUser = fromUser;
        chatMessage.message = message;
        chatMessage.timestamp = timestamp;
        this.addMessage(chatMessage);
        this.updateImageBuffer();
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final int locateItem = this.locateItem(mouseEvent.getX(), mouseEvent.getY());
        if (locateItem < 0) {
            return;
        }
        if (locateItem >= this.allItems.size()) {
            return;
        }
        final GBasicItem gBasicItem = this.allItems.elementAt(locateItem);
        if (!gBasicItem.isAction()) {
            return;
        }
        gBasicItem.executeAction();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setNormalCursor();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int locateItem = this.locateItem(mouseEvent.getX(), mouseEvent.getY());
        if (locateItem < 0) {
            this.setNormalCursor();
            return;
        }
        if (locateItem >= this.allItems.size()) {
            return;
        }
        if (((GBasicItem)this.allItems.elementAt(locateItem)).isAction()) {
            this.setHandCursor();
        }
        else {
            this.setNormalCursor();
        }
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.contentChanged = true;
        this.updateImageBuffer();
        this.repaint();
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.imageBuffer == null) {
            return;
        }
        graphics.drawImage(this.imageBuffer, 0, 0, this);
    }
    
    public synchronized void refresh() {
        if (this.imageBuffer == null) {
            return;
        }
        this.drawImageBuffer();
        this.repaint();
    }
    
    public synchronized Vector getPlainText() {
        return this.richFormatter.getPlainText(this.chatLines);
    }
    
    private void updateImageBuffer() {
        if (this.needToCreateBuffer()) {
            final Dimension size = this.getSize();
            this.imageBuffer = this.createBuffer(size.width, size.height);
            this.formatAll();
        }
        this.drawImageBuffer();
    }
    
    private synchronized void drawImageBuffer() {
        if (this.imageBuffer == null) {
            return;
        }
        this.clearBuffer();
        if (this.allItems.size() == 0) {
            return;
        }
        final Graphics graphics = this.imageBuffer.getGraphics();
        this.imageBuffer.getWidth(null);
        final int height = this.imageBuffer.getHeight(null);
        final int startLine = this.getStartLine();
        final int n = startLine + height;
        this.cachedItem = this.findUpperBound(startLine);
        for (int i = this.cachedItem; i < this.allItems.size(); ++i) {
            final GBasicItem gBasicItem = this.allItems.elementAt(i);
            final Rectangle bounds = gBasicItem.getBounds();
            if (bounds.y > n) {
                break;
            }
            if (bounds.y + bounds.height >= startLine) {
                gBasicItem.draw(graphics, bounds.x, bounds.y - startLine);
            }
        }
    }
    
    private int findUpperBound(final int n) {
        if (0 == this.allItems.size()) {
            return this.cachedItem = 0;
        }
        if (this.cachedItem >= this.allItems.size()) {
            this.cachedItem = 0;
        }
        final Rectangle bounds = this.allItems.elementAt(this.cachedItem).getBounds();
        boolean b = false;
        if (bounds.y >= n) {
            b = true;
        }
        if (b) {
            for (int i = this.cachedItem; i >= 0; --i) {
                if (((GBasicItem)this.allItems.elementAt(i)).getBounds().y < n) {
                    return this.cachedItem = i;
                }
            }
            return this.cachedItem = 0;
        }
        for (int j = this.cachedItem; j < this.allItems.size(); ++j) {
            final Rectangle bounds2 = this.allItems.elementAt(j).getBounds();
            if (bounds2.y + bounds2.height >= n) {
                return this.cachedItem = j;
            }
        }
        return this.cachedItem = 0;
    }
    
    private int locateItem(final int n, final int n2) {
        if (this.imageBuffer == null) {
            return -1;
        }
        this.imageBuffer.getGraphics();
        this.imageBuffer.getWidth(null);
        final int height = this.imageBuffer.getHeight(null);
        final int startLine = this.getStartLine();
        final int n3 = startLine + height;
        final int n4 = n2 + startLine;
        this.cachedItem = this.findUpperBound(startLine);
        for (int i = this.cachedItem; i < this.allItems.size(); ++i) {
            final GBasicItem gBasicItem = this.allItems.elementAt(i);
            if (gBasicItem.inBoundary(n, n4)) {
                return i;
            }
            if (gBasicItem.getBounds().y > n3) {
                return -1;
            }
        }
        return -1;
    }
    
    private int getStartLine() {
        final int totalLen = this.getTotalLen();
        final int height = this.imageBuffer.getHeight(null);
        if (height > totalLen) {
            return 0;
        }
        return (int)((totalLen - height) * this.exposePercent + 0.5);
    }
    
    private int getTotalLen() {
        return this.richFormatter.getTotalLen();
    }
    
    private void addToAllItems(final Vector vector) {
        for (int i = 0; i < vector.size(); ++i) {
            this.allItems.addElement(vector.elementAt(i));
        }
    }
    
    private void addMessage(final ChatMessage chatMessage) {
        boolean b = false;
        if (this.chatLines.size() > this.MAX_LINES) {
            b = true;
            for (int i = 0; i < this.TRIM_LINES; ++i) {
                if (this.chatLines.size() > 0) {
                    this.chatLines.removeElementAt(0);
                }
            }
        }
        this.chatLines.addElement(chatMessage);
        if (b) {
            this.formatAll();
        }
        else {
            this.formatLine(chatMessage);
        }
    }
    
    private void formatAll() {
        this.allItems.removeAllElements();
        this.richFormatter.reset();
        this.cachedItem = 0;
        for (int i = 0; i < this.chatLines.size(); ++i) {
            this.formatLine((ChatMessage)this.chatLines.elementAt(i));
        }
    }
    
    private void formatLine(final ChatMessage chatMessage) {
        final String fromUser = chatMessage.fromUser;
        final String message = chatMessage.message;
        int width = 400;
        if (this.imageBuffer != null) {
            width = this.imageBuffer.getWidth(null);
        }
        Vector vector;
        if (!chatMessage.isModerated) {
            if (fromUser != null) {
                vector = this.richFormatter.addChatLine(fromUser, message, width, chatMessage.timestamp, chatMessage.mop);
            }
            else {
                vector = this.richFormatter.appendText(message, width, chatMessage.mop);
            }
        }
        else if (chatMessage.isHost) {
            vector = this.richFormatter.addAnswer(fromUser, message, width, chatMessage.timestamp);
        }
        else {
            vector = this.richFormatter.addQuestion(fromUser, message, width, chatMessage.timestamp);
        }
        this.addToAllItems(vector);
    }
    
    private void addBasicItems(final Vector vector) {
        for (int i = 0; i < vector.size(); ++i) {
            this.allItems.addElement(vector.elementAt(i));
        }
    }
    
    private void setHandCursor() {
        if (this.cursorState == 1) {
            return;
        }
        this.setCursor(RichCanvas.handCursor);
        this.cursorState = 1;
    }
    
    private void setNormalCursor() {
        if (this.cursorState == 0) {
            return;
        }
        this.setCursor(RichCanvas.normalCursor);
        this.cursorState = 0;
    }
    
    private synchronized Image createBuffer(int n, int n2) {
        if (n <= 0) {
            n = 8;
        }
        if (n2 <= 0) {
            n2 = 8;
        }
        return this.createImage(n, n2);
    }
    
    private synchronized void clearBuffer() {
        if (this.imageBuffer == null) {
            return;
        }
        final Graphics graphics = this.imageBuffer.getGraphics();
        if (graphics == null) {
            System.out.println("W6734. GC.");
            return;
        }
        final int width = this.imageBuffer.getWidth(null);
        final int height = this.imageBuffer.getHeight(null);
        graphics.setColor(this.bgColor);
        graphics.fillRect(0, 0, width, height);
        if (true) {
            graphics.draw3DRect(0, 0, width, height, false);
        }
    }
    
    private boolean needToCreateBuffer() {
        if (this.imageBuffer == null) {
            return true;
        }
        if (this.contentChanged) {
            this.contentChanged = false;
            return true;
        }
        final Dimension size = this.getSize();
        final int width = this.imageBuffer.getWidth(null);
        return this.imageBuffer.getHeight(null) != size.height || width != size.width;
    }
    
    private Color getBgColor() {
        return Color.white;
    }
    
    static {
        normalCursor = new Cursor(0);
        handCursor = new Cursor(12);
        RichCanvas.anInstance = null;
    }
}
