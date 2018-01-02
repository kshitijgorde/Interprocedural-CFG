// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.event.ActionEvent;
import java.util.Vector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.Canvas;

public class TeletextPageCanvas extends Canvas implements ActionListener
{
    public static final int TYPING_MODE_OVERWRITE = 0;
    public static final int TYPING_MODE_INSERT = 1;
    public static final int EDITING_MODE_CHARACTER = 0;
    public static final int EDITING_MODE_GRAPHICS = 1;
    final int UNDO_RING_THRESHOLD;
    TeletextPage teletextPage;
    TeletextPageImage teletextPageImage;
    TeletextPageRenderBuffer teletextPageRenderBuffer;
    Image doubleBufferImage;
    Graphics doubleBufferGraphics;
    Dimension doubleBufferSize;
    int typingMode;
    int editingMode;
    boolean pageDirtyFlag;
    boolean undoTrackingFlag;
    boolean cursorWrap;
    int cursorX;
    int cursorY;
    Color cursorColor;
    Vector teletextTools;
    Vector undoRing;
    TeletextKeyAssociation[] keyAssociations;
    
    public boolean mountTool(final TeletextTool teletextTool) {
        this.teletextTools.insertElementAt(teletextTool, 0);
        this.teletextTools.elementAt(0).install(this);
        this.repaint();
        return true;
    }
    
    public boolean umountTool() {
        if (this.teletextTools.size() > 0) {
            this.teletextTools.elementAt(0).uninstall();
            this.teletextTools.removeElementAt(0);
            this.repaint();
            return true;
        }
        return false;
    }
    
    public void suspendUndoTracking() {
        this.undoTrackingFlag = false;
    }
    
    public void resumeUndoTracking() {
        this.undoTrackingFlag = true;
    }
    
    public void pushPage() {
        System.out.println("pushPage");
        if (!this.undoTrackingFlag) {
            return;
        }
        if (this.undoRing.size() >= 32) {
            System.out.println("pushPage: removing, size = " + this.undoRing.size());
            this.undoRing.removeElementAt(this.undoRing.size() - 1);
            System.out.println("pushPage: removed, size = " + this.undoRing.size());
        }
        this.undoRing.insertElementAt(this.teletextPage.getContents(), 0);
        System.out.println("pushPage: done, size = " + this.undoRing.size());
        this.pageDirtyFlag = true;
    }
    
    public void popPage() {
        System.out.println("popPage: size = " + this.undoRing.size());
        if (this.undoRing.size() > 0) {
            this.teletextPage.setContents(this.undoRing.elementAt(0));
            this.undoRing.removeElementAt(0);
            this.pageDirtyFlag = true;
            this.render();
            this.repaint();
        }
    }
    
    public void markPageDirty() {
        this.pageDirtyFlag = false;
    }
    
    public boolean isPageDirty() {
        return !this.pageDirtyFlag;
    }
    
    public void purgeDisposableTools() {
        final Vector<Object> vector = new Vector<Object>();
        for (int i = 0; i < this.teletextTools.size(); ++i) {
            if (((TeletextTool)this.teletextTools.elementAt(i)).isDisposable()) {
                vector.addElement(this.teletextTools.elementAt(i));
            }
        }
        for (int j = 0; j < vector.size(); ++j) {
            this.teletextTools.removeElement(vector.elementAt(j));
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final TeletextPageCanvas$1.Command[] array = new TeletextPageCanvas$1.Command[30];
        final int n = 0;
        if (this == null) {
            throw null;
        }
        array[n] = new TeletextPageCanvas$1.Command("insertAlphaBlackCode", '\0');
        final int n2 = 1;
        if (this == null) {
            throw null;
        }
        array[n2] = new TeletextPageCanvas$1.Command("insertAlphaRedCode", '\u0001');
        final int n3 = 2;
        if (this == null) {
            throw null;
        }
        array[n3] = new TeletextPageCanvas$1.Command("insertAlphaGreenCode", '\u0002');
        final int n4 = 3;
        if (this == null) {
            throw null;
        }
        array[n4] = new TeletextPageCanvas$1.Command("insertAlphaYellowCode", '\u0003');
        final int n5 = 4;
        if (this == null) {
            throw null;
        }
        array[n5] = new TeletextPageCanvas$1.Command("insertAlphaBlueCode", '\u0004');
        final int n6 = 5;
        if (this == null) {
            throw null;
        }
        array[n6] = new TeletextPageCanvas$1.Command("insertAlphaMagentaCode", '\u0005');
        final int n7 = 6;
        if (this == null) {
            throw null;
        }
        array[n7] = new TeletextPageCanvas$1.Command("insertAlphaCyanCode", '\u0006');
        final int n8 = 7;
        if (this == null) {
            throw null;
        }
        array[n8] = new TeletextPageCanvas$1.Command("insertAlphaWhiteCode", '\u0007');
        final int n9 = 8;
        if (this == null) {
            throw null;
        }
        array[n9] = new TeletextPageCanvas$1.Command("insertGraphBlackCode", '\u0010');
        final int n10 = 9;
        if (this == null) {
            throw null;
        }
        array[n10] = new TeletextPageCanvas$1.Command("insertGraphRedCode", '\u0011');
        final int n11 = 10;
        if (this == null) {
            throw null;
        }
        array[n11] = new TeletextPageCanvas$1.Command("insertGraphGreenCode", '\u0012');
        final int n12 = 11;
        if (this == null) {
            throw null;
        }
        array[n12] = new TeletextPageCanvas$1.Command("insertGraphYellowCode", '\u0013');
        final int n13 = 12;
        if (this == null) {
            throw null;
        }
        array[n13] = new TeletextPageCanvas$1.Command("insertGraphBlueCode", '\u0014');
        final int n14 = 13;
        if (this == null) {
            throw null;
        }
        array[n14] = new TeletextPageCanvas$1.Command("insertGraphMagentaCode", '\u0015');
        final int n15 = 14;
        if (this == null) {
            throw null;
        }
        array[n15] = new TeletextPageCanvas$1.Command("insertGraphCyanCode", '\u0016');
        final int n16 = 15;
        if (this == null) {
            throw null;
        }
        array[n16] = new TeletextPageCanvas$1.Command("insertGraphWhiteCode", '\u0017');
        final int n17 = 16;
        if (this == null) {
            throw null;
        }
        array[n17] = new TeletextPageCanvas$1.Command("insertSteadyCode", '\t');
        final int n18 = 17;
        if (this == null) {
            throw null;
        }
        array[n18] = new TeletextPageCanvas$1.Command("insertFlashCode", '\b');
        final int n19 = 18;
        if (this == null) {
            throw null;
        }
        array[n19] = new TeletextPageCanvas$1.Command("insertEndBoxCode", '\n');
        final int n20 = 19;
        if (this == null) {
            throw null;
        }
        array[n20] = new TeletextPageCanvas$1.Command("insertStartBoxCode", '\u000b');
        final int n21 = 20;
        if (this == null) {
            throw null;
        }
        array[n21] = new TeletextPageCanvas$1.Command("insertNormalHeightCode", '\f');
        final int n22 = 21;
        if (this == null) {
            throw null;
        }
        array[n22] = new TeletextPageCanvas$1.Command("insertDoubleHeightCode", '\r');
        final int n23 = 22;
        if (this == null) {
            throw null;
        }
        array[n23] = new TeletextPageCanvas$1.Command("insertConcealCode", '\u0018');
        final int n24 = 23;
        if (this == null) {
            throw null;
        }
        array[n24] = new TeletextPageCanvas$1.Command("insertGlueGraphicsCode", '\u0019');
        final int n25 = 24;
        if (this == null) {
            throw null;
        }
        array[n25] = new TeletextPageCanvas$1.Command("insertSeparateGraphicsCode", '\u001a');
        final int n26 = 25;
        if (this == null) {
            throw null;
        }
        array[n26] = new TeletextPageCanvas$1.Command("insertAutonumberCode", '\u001b');
        final int n27 = 26;
        if (this == null) {
            throw null;
        }
        array[n27] = new TeletextPageCanvas$1.Command("insertBlackBackgroundCode", '\u001c');
        final int n28 = 27;
        if (this == null) {
            throw null;
        }
        array[n28] = new TeletextPageCanvas$1.Command("insertNewBackgroundCode", '\u001d');
        final int n29 = 28;
        if (this == null) {
            throw null;
        }
        array[n29] = new TeletextPageCanvas$1.Command("insertHoldGraphicsCode", '\u001e');
        final int n30 = 29;
        if (this == null) {
            throw null;
        }
        array[n30] = new TeletextPageCanvas$1.Command("insertReleaseGraphicsCode", '\u001f');
        final TeletextPageCanvas$1.Command[] array2 = array;
        for (int i = 0; i < array2.length; ++i) {
            if (actionEvent.getActionCommand().equals(array2[i].getActionCommand())) {
                this.insertCharacter(array2[i].getTeletextCode());
            }
        }
    }
    
    public void setTypingMode(final int typingMode) {
        this.typingMode = typingMode;
        this.repaint();
    }
    
    public int getTypingMode() {
        return this.typingMode;
    }
    
    public void toggleTypingMode() {
        switch (this.typingMode) {
            case 0: {
                this.setTypingMode(1);
                break;
            }
            case 1: {
                this.setTypingMode(0);
                break;
            }
        }
    }
    
    public void setEditingMode(final int n) {
        if (this.editingMode == 1 && n == 0) {
            this.editingMode = 0;
            this.cursorX /= TeletextFont.getHorizontalGraphCount();
            this.cursorY /= TeletextFont.getVerticalGraphCount();
        }
        else if (this.editingMode == 0 && n == 1) {
            this.editingMode = 1;
            this.cursorX *= TeletextFont.getHorizontalGraphCount();
            this.cursorY *= TeletextFont.getVerticalGraphCount();
        }
        this.repaint();
    }
    
    public int getEditingMode() {
        return this.editingMode;
    }
    
    public void toggleEditingMode() {
        switch (this.editingMode) {
            case 0: {
                this.setEditingMode(1);
                break;
            }
            case 1: {
                this.setEditingMode(0);
                break;
            }
        }
    }
    
    public void setCursorWrap(final boolean cursorWrap) {
        this.cursorWrap = cursorWrap;
    }
    
    public boolean getCursorWrap() {
        return this.cursorWrap;
    }
    
    public void toggleCursorWrap() {
        this.cursorWrap = !this.cursorWrap;
    }
    
    public int getCursorX() {
        return this.cursorX;
    }
    
    public int getCursorY() {
        return this.cursorY;
    }
    
    public int getCursorCharX() {
        if (this.editingMode == 1) {
            return this.cursorX / TeletextFont.getHorizontalGraphCount();
        }
        return this.cursorX;
    }
    
    public int getCursorCharY() {
        if (this.editingMode == 1) {
            return this.cursorY / TeletextFont.getVerticalGraphCount();
        }
        return this.cursorY;
    }
    
    public TeletextPage getPage() {
        return this.teletextPage;
    }
    
    public TeletextPageImage getPageImage() {
        return this.teletextPageImage;
    }
    
    public TeletextPageRenderBuffer getRenderBuffer() {
        return this.teletextPageRenderBuffer;
    }
    
    public void renderLine(final int n) {
        this.teletextPageImage.renderLine(n);
        this.teletextPageImage.updateImage(n);
    }
    
    public void render() {
        this.teletextPageImage.render();
        this.teletextPageImage.updateImage();
    }
    
    public void paintCursor(final Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.setColor(this.cursorColor);
        graphics.setXORMode(Color.black);
        int[] array = null;
        switch (this.editingMode) {
            case 0: {
                array = this.teletextPageRenderBuffer.getCharacterRectangleParameters(this.cursorX, this.cursorY);
                break;
            }
            case 1: {
                array = this.teletextPageRenderBuffer.getGraphRectangleParameters(this.cursorX, this.cursorY);
                break;
            }
        }
        switch (this.typingMode) {
            case 0: {
                graphics.fillRect(array[0], array[1], array[2], array[3]);
                break;
            }
            case 1: {
                graphics.fillRect(array[0], array[1], 2, array[3]);
                break;
            }
        }
        graphics.setPaintMode();
        graphics.setColor(color);
    }
    
    public void update(final Graphics graphics) {
        this.implementPaint();
        graphics.drawImage(this.doubleBufferImage, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        if (this.doubleBufferImage == null) {
            this.implementPaint();
        }
        graphics.drawImage(this.doubleBufferImage, 0, 0, this);
    }
    
    public void implementPaint() {
        boolean b = false;
        if (this.doubleBufferGraphics == null) {
            b = true;
        }
        if (this.doubleBufferSize != null && (this.doubleBufferSize.width != this.getSize().width || this.doubleBufferSize.height != this.getSize().height)) {
            b = true;
        }
        if (b) {
            this.doubleBufferImage = this.createImage(this.getSize().width, this.getSize().height);
            this.doubleBufferGraphics = this.doubleBufferImage.getGraphics();
        }
        this.doubleBufferGraphics.setPaintMode();
        this.doubleBufferGraphics.drawImage(this.teletextPageImage.getImage(), 0, 0, null);
        this.paintCursor(this.doubleBufferGraphics);
        this.paintTools(this.doubleBufferGraphics);
    }
    
    public void paintTools(final Graphics graphics) {
        for (int i = 0; i < this.teletextTools.size(); ++i) {
            ((TeletextTool)this.teletextTools.elementAt(i)).paint(this.doubleBufferGraphics);
        }
    }
    
    public boolean checkCursorPosition() {
        return true;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.teletextPage.getWidth() * TeletextFont.getPixelWidth(), this.teletextPage.getHeight() * TeletextFont.getPixelHeight());
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getMaximumSize() {
        return this.getPreferredSize();
    }
    
    public int getCursorRightLimit() {
        switch (this.editingMode) {
            case 0: {
                return this.teletextPage.getWidth() - 1;
            }
            case 1: {
                return this.teletextPage.getWidth() * TeletextFont.getHorizontalGraphCount() - 1;
            }
            default: {
                return 0;
            }
        }
    }
    
    public int getCursorBottomLimit() {
        switch (this.editingMode) {
            case 0: {
                return this.teletextPage.getHeight() - 1;
            }
            case 1: {
                return this.teletextPage.getHeight() * TeletextFont.getVerticalGraphCount() - 1;
            }
            default: {
                return 0;
            }
        }
    }
    
    public void moveCursorTo(final int cursorX, final int cursorY) {
        final int cursorX2 = this.cursorX;
        final int cursorY2 = this.cursorY;
        if (this.isPageDirty()) {}
        if (cursorX >= 0 && cursorX <= this.getCursorRightLimit()) {
            this.cursorX = cursorX;
        }
        else if (cursorX > this.getCursorRightLimit()) {
            this.moveCursorTo(this.getCursorRightLimit(), this.cursorY);
        }
        if (cursorY >= 0 && cursorY <= this.getCursorBottomLimit()) {
            this.cursorY = cursorY;
        }
        else if (cursorY > this.getCursorBottomLimit()) {
            this.moveCursorTo(this.cursorX, this.getCursorBottomLimit());
        }
        if (this.getCursorCharY() > 0 && this.teletextPageRenderBuffer.getDoubleHeightFlag(this.getCursorCharY() - 1)) {
            this.moveCursorTo(this.cursorX, this.cursorY - 1);
        }
        if (!this.checkCursorPosition()) {
            this.cursorX = cursorX2;
            this.cursorY = cursorY2;
        }
    }
    
    public void moveCursorToColumn(final int n) {
        this.moveCursorTo(n, this.cursorY);
    }
    
    public void moveCursorToRow(final int n) {
        this.moveCursorTo(this.cursorX, n);
    }
    
    public void moveCursorToPixel(final int n, final int n2) {
        this.moveCursorTo(this.pixelToCursorX(n), this.pixelToCursorY(n2));
    }
    
    public int pixelToCursorX(final int n) {
        switch (this.editingMode) {
            case 0: {
                return TeletextFont.pixelToCharX(n);
            }
            case 1: {
                return TeletextFont.pixelToGraphX(n);
            }
            default: {
                return 0;
            }
        }
    }
    
    public int pixelToCursorY(final int n) {
        switch (this.editingMode) {
            case 0: {
                return TeletextFont.pixelToCharY(n);
            }
            case 1: {
                return TeletextFont.pixelToGraphY(n);
            }
            default: {
                return 0;
            }
        }
    }
    
    public boolean moveCursorLeft() {
        if (this.cursorX > 0) {
            this.moveCursorTo(this.cursorX - 1, this.cursorY);
            return false;
        }
        if (this.cursorWrap) {
            this.moveCursorTo(this.getCursorRightLimit(), this.cursorY);
            this.moveCursorUp();
            return true;
        }
        return false;
    }
    
    public boolean moveCursorRight() {
        if (this.cursorX < this.getCursorRightLimit()) {
            this.moveCursorTo(this.cursorX + 1, this.cursorY);
            return false;
        }
        if (this.cursorWrap) {
            this.moveCursorTo(0, this.cursorY);
            this.moveCursorDown();
            return true;
        }
        return false;
    }
    
    public boolean advanceCursor() {
        this.suspendUndoTracking();
        final boolean moveCursorRight = this.moveCursorRight();
        this.resumeUndoTracking();
        return moveCursorRight;
    }
    
    public boolean moveCursorUp() {
        if (this.cursorY > 0) {
            this.moveCursorTo(this.cursorX, this.cursorY - 1);
            return false;
        }
        if (this.cursorWrap) {
            this.moveCursorTo(this.cursorX, this.getCursorBottomLimit());
            return true;
        }
        return false;
    }
    
    public boolean moveCursorDown() {
        if (this.cursorY < this.getCursorBottomLimit()) {
            if (this.teletextPageRenderBuffer.getDoubleHeightFlag(this.getCursorCharY())) {
                switch (this.editingMode) {
                    case 0: {
                        this.moveCursorTo(this.cursorX, this.cursorY + 2);
                        break;
                    }
                    case 1: {
                        if (this.cursorY % TeletextFont.getVerticalGraphCount() == TeletextFont.getVerticalGraphCount() - 1) {
                            this.moveCursorTo(this.cursorX, this.cursorY + TeletextFont.getVerticalGraphCount() + 1);
                        }
                        else {
                            this.moveCursorTo(this.cursorX, this.cursorY + 1);
                        }
                        break;
                    }
                }
            }
            else {
                this.moveCursorTo(this.cursorX, this.cursorY + 1);
            }
            return false;
        }
        if (this.cursorWrap) {
            this.moveCursorTo(this.cursorX, 0);
            return true;
        }
        return false;
    }
    
    public void beginningOfLine() {
        this.moveCursorTo(0, this.cursorY);
    }
    
    public void endOfLine() {
        this.moveCursorTo(this.getCursorRightLimit(), this.cursorY);
    }
    
    public void carriageReturn() {
        this.moveCursorDown();
        this.beginningOfLine();
        if (this.getCursorCharY() > 0 && this.teletextPage.isRowEmpty(this.cursorY - 1) && this.teletextPage.isRowEmpty(this.cursorY)) {
            return;
        }
        if (this.getCursorCharY() > 0 && this.teletextPage.getCharacterAt(this.getCursorCharX(), this.getCursorCharY()) == ' ') {
            while (this.cursorX < this.getCursorRightLimit() && this.teletextPage.getCharacterAt(this.getCursorCharX(), this.getCursorCharY() - 1) == ' ' && this.teletextPage.getCharacterAt(this.getCursorCharX(), this.getCursorCharY()) == ' ') {
                this.moveCursorRight();
            }
        }
    }
    
    public void insertCharacter(final Character c) {
        this.insertCharacter((char)c);
    }
    
    public void insertCharacter(final char c) {
        boolean b = false;
        boolean b2 = false;
        if (this.teletextPage.getCharacterAt(this.getCursorCharX(), this.getCursorCharY()) == '\r' || c == '\r') {
            b = true;
        }
        switch (this.typingMode) {
            case 0: {
                this.teletextPage.setCharacterAt(this.getCursorCharX(), this.getCursorCharY(), c);
                break;
            }
            case 1: {
                this.teletextPage.insertCharacterAt(this.getCursorCharX(), this.getCursorCharY(), c);
                break;
            }
        }
        this.markPageDirty();
        if (this.advanceCursor()) {
            b2 = true;
        }
        if (b) {
            this.render();
            this.repaint();
        }
        else {
            if (b2) {
                if (this.cursorY == 0) {
                    this.renderLine(this.teletextPage.getHeight() - 1);
                    this.repaint();
                }
                else {
                    this.renderLine(this.getCursorCharY() - 1);
                    this.repaint();
                }
            }
            this.renderLine(this.getCursorCharY());
            this.repaint();
        }
    }
    
    public void backwardDeleteCharacter() {
        this.moveCursorLeft();
        if (this.teletextPage.getCharacterAt(this.getCursorCharX(), this.getCursorCharY()) == '\r') {}
        switch (this.typingMode) {
            case 0: {
                this.teletextPage.setCharacterAt(this.getCursorCharX(), this.getCursorCharY(), ' ');
                break;
            }
            case 1: {
                this.teletextPage.deleteCharacterAt(this.getCursorCharX(), this.getCursorCharY());
                break;
            }
        }
        if (this.cursorX == this.getCursorRightLimit()) {
            while (this.cursorX > 0 && this.teletextPage.getCharacterAt(this.getCursorCharX() - 1, this.getCursorCharY()) == ' ') {
                this.moveCursorLeft();
            }
        }
        this.renderLine(this.getCursorCharY());
        this.repaint();
    }
    
    public void deleteCharacter() {
        this.moveCursorRight();
        this.backwardDeleteCharacter();
    }
    
    public TeletextKeyAssociation invokeMethodByKeyEvent(final TeletextKeyAssociation[] array, final KeyEvent keyEvent) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].getCharCode() == '\0') {
                if (keyEvent.getKeyCode() == array[i].getKeyCode() && (keyEvent.getModifiers() & array[i].getModifiers()) == array[i].getModifiers()) {
                    array[i].invoke();
                    return array[i];
                }
            }
            else if (keyEvent.getKeyChar() == array[i].getCharCode() && (keyEvent.getModifiers() & array[i].getModifiers()) == array[i].getModifiers()) {
                array[i].invoke();
                return array[i];
            }
        }
        return null;
    }
    
    public void processKeyPressedEvent(final KeyEvent keyEvent) {
        boolean b = false;
        for (int i = 0; i < this.teletextTools.size(); ++i) {
            if (((TeletextTool)this.teletextTools.elementAt(i)).implementKeyPressed(keyEvent)) {
                b = true;
            }
        }
        this.purgeDisposableTools();
        if (b || this.invokeMethodByKeyEvent(this.keyAssociations, keyEvent) != null) {}
        this.repaint();
    }
    
    public void processKeyReleasedEvent(final KeyEvent keyEvent) {
        for (int i = 0; i < this.teletextTools.size(); ++i) {
            if (((TeletextTool)this.teletextTools.elementAt(i)).implementKeyReleased(keyEvent)) {}
        }
        this.purgeDisposableTools();
    }
    
    public void processKeyTypedEvent(final KeyEvent keyEvent) {
        boolean b = false;
        for (int i = 0; i < this.teletextTools.size(); ++i) {
            if (((TeletextTool)this.teletextTools.elementAt(i)).implementKeyTyped(keyEvent)) {
                b = true;
            }
        }
        this.purgeDisposableTools();
        if (!b && keyEvent.getKeyChar() >= ' ' && keyEvent.getKeyChar() < '\u007f') {
            switch (this.editingMode) {
                case 0: {
                    this.insertCharacter(keyEvent.getKeyChar());
                }
            }
        }
    }
    
    public TeletextPageCanvas(final TeletextPage teletextPage) {
        this.UNDO_RING_THRESHOLD = 32;
        this.doubleBufferImage = null;
        this.doubleBufferGraphics = null;
        this.doubleBufferSize = null;
        this.typingMode = 0;
        this.editingMode = 0;
        this.pageDirtyFlag = false;
        this.undoTrackingFlag = true;
        this.cursorWrap = true;
        this.cursorX = 0;
        this.cursorY = 0;
        this.cursorColor = Color.white;
        this.teletextTools = new Vector();
        this.undoRing = new Vector();
        this.keyAssociations = new TeletextKeyAssociation[] { new TeletextKeyCodeAssociation(112, '\0', 0, this, '\0'), new TeletextKeyCodeAssociation(113, '\0', 0, this, '\u0001'), new TeletextKeyCodeAssociation(114, '\0', 0, this, '\u0002'), new TeletextKeyCodeAssociation(115, '\0', 0, this, '\u0003'), new TeletextKeyCodeAssociation(116, '\0', 0, this, '\u0004'), new TeletextKeyCodeAssociation(117, '\0', 0, this, '\u0005'), new TeletextKeyCodeAssociation(118, '\0', 0, this, '\u0006'), new TeletextKeyCodeAssociation(119, '\0', 0, this, '\u0007'), new TeletextKeyCodeAssociation(112, '\0', 1, this, '\u0010'), new TeletextKeyCodeAssociation(113, '\0', 1, this, '\u0011'), new TeletextKeyCodeAssociation(114, '\0', 1, this, '\u0012'), new TeletextKeyCodeAssociation(115, '\0', 1, this, '\u0013'), new TeletextKeyCodeAssociation(116, '\0', 1, this, '\u0014'), new TeletextKeyCodeAssociation(117, '\0', 1, this, '\u0015'), new TeletextKeyCodeAssociation(118, '\0', 1, this, '\u0016'), new TeletextKeyCodeAssociation(119, '\0', 1, this, '\u0017'), new TeletextKeyCodeAssociation(120, '\0', 0, this, '\u001a'), new TeletextKeyCodeAssociation(121, '\0', 0, this, '\u0019'), new TeletextKeyMethodAssociation(155, '\0', 0, this, "toggleTypingMode"), new TeletextKeyMethodAssociation(144, '\0', 0, this, "toggleEditingMode"), new TeletextKeyMethodAssociation(37, '\0', 0, this, "moveCursorLeft"), new TeletextKeyMethodAssociation(39, '\0', 0, this, "moveCursorRight"), new TeletextKeyMethodAssociation(38, '\0', 0, this, "moveCursorUp"), new TeletextKeyMethodAssociation(40, '\0', 0, this, "moveCursorDown"), new TeletextKeyMethodAssociation(36, '\0', 0, this, "beginningOfLine"), new TeletextKeyMethodAssociation(35, '\0', 0, this, "endOfLine"), new TeletextKeyMethodAssociation(10, '\0', 0, this, "carriageReturn"), new TeletextKeyMethodAssociation(8, '\0', 0, this, "backwardDeleteCharacter"), new TeletextKeyMethodAssociation(127, '\0', 0, this, "deleteCharacter"), new TeletextKeyMethodAssociation(90, '\0', 2, this, "popPage") };
        this.teletextPage = teletextPage;
        this.teletextPageImage = new TeletextPageImage(this.teletextPage);
        this.teletextPageRenderBuffer = this.teletextPageImage.getRenderBuffer();
        if (this == null) {
            throw null;
        }
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                TeletextPageCanvas.this.processKeyPressedEvent(keyEvent);
            }
            
            public void keyReleased(final KeyEvent keyEvent) {
                TeletextPageCanvas.this.processKeyReleasedEvent(keyEvent);
            }
            
            public void keyTyped(final KeyEvent keyEvent) {
                TeletextPageCanvas.this.processKeyTypedEvent(keyEvent);
            }
            
            {
                this.constructor$0(TeletextPageCanvas.this);
            }
            
            private final void constructor$0(final TeletextPageCanvas teletextPageCanvas) {
            }
            
            class Command
            {
                String actionCommand;
                char teletextCode;
                
                public String getActionCommand() {
                    return this.actionCommand;
                }
                
                public char getTeletextCode() {
                    return this.teletextCode;
                }
                
                private final void constructor$0(final TeletextPageCanvas teletextPageCanvas, final String actionCommand, final char teletextCode) {
                    this.actionCommand = actionCommand;
                    this.teletextCode = teletextCode;
                }
            }
        });
        this.render();
        this.mountTool(new TeletextPopupTool());
        this.mountTool(new TeletextSelectionTool());
    }
}
