// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

import java.awt.image.ImageObserver;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
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
    final int UNDO_RING_THRESHOLD = 32;
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
    TeletextSelectionTool selectionTool;
    TeletextTool currentTool;
    TeletextKeyAssociation[] keyAssociations;
    
    public TeletextPageCanvas(final TeletextPage teletextPage) {
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
            
            class Command
            {
                String actionCommand;
                char teletextCode;
                private final /* synthetic */ TeletextPageCanvas this$0;
                
                public Command(final TeletextPageCanvas this$0, final String actionCommand, final char teletextCode) {
                    this.this$0 = this$0;
                    this.actionCommand = actionCommand;
                    this.teletextCode = teletextCode;
                }
                
                public String getActionCommand() {
                    return this.actionCommand;
                }
                
                public char getTeletextCode() {
                    return this.teletextCode;
                }
            }
        });
        this.render();
        this.addTeletextTool(new TeletextPopupTool());
        this.selectTeletextTool(this.selectionTool = new TeletextSelectionTool());
        this.pushPage();
    }
    
    public boolean addTeletextTool(final TeletextTool teletextTool) {
        if (this.teletextTools.indexOf(teletextTool) == -1) {
            this.teletextTools.insertElementAt(teletextTool, 0);
            this.teletextTools.elementAt(0).install(this);
            this.repaint();
            return true;
        }
        return false;
    }
    
    public boolean removeTeletextTool(final TeletextTool teletextTool) {
        final int index = this.teletextTools.indexOf(teletextTool);
        if (index >= 0) {
            teletextTool.uninstall();
            this.teletextTools.removeElementAt(index);
            this.repaint();
            return true;
        }
        return false;
    }
    
    public boolean selectTeletextTool(final TeletextTool currentTool) {
        if (this.currentTool != null) {
            this.removeTeletextTool(this.currentTool);
        }
        this.addTeletextTool(this.currentTool = currentTool);
        return true;
    }
    
    public boolean selectDefaultTeletextTool() {
        return this.selectTeletextTool(this.selectionTool);
    }
    
    public void suspendUndoTracking() {
        this.undoTrackingFlag = false;
    }
    
    public void resumeUndoTracking() {
        this.undoTrackingFlag = true;
    }
    
    public void pushPage() {
        if (!this.undoTrackingFlag) {
            return;
        }
        if (this.undoRing.size() >= 32) {
            this.undoRing.removeElementAt(this.undoRing.size() - 1);
        }
        this.undoRing.insertElementAt(this.teletextPage.getContents(), 0);
        this.pageDirtyFlag = false;
    }
    
    public void popPage() {
        if (this.undoRing.size() > 0) {
            this.teletextPage.setContents(this.undoRing.elementAt(0));
            this.undoRing.removeElementAt(0);
            this.pageDirtyFlag = false;
            this.render();
            this.repaint();
        }
    }
    
    public void markPageDirty() {
        this.pageDirtyFlag = true;
    }
    
    public boolean isPageDirty() {
        return this.pageDirtyFlag;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final TeletextPageCanvas$1.Command[] array = { new TeletextPageCanvas$1.Command("insertAlphaBlackCode", '\0'), new TeletextPageCanvas$1.Command("insertAlphaRedCode", '\u0001'), new TeletextPageCanvas$1.Command("insertAlphaGreenCode", '\u0002'), new TeletextPageCanvas$1.Command("insertAlphaYellowCode", '\u0003'), new TeletextPageCanvas$1.Command("insertAlphaBlueCode", '\u0004'), new TeletextPageCanvas$1.Command("insertAlphaMagentaCode", '\u0005'), new TeletextPageCanvas$1.Command("insertAlphaCyanCode", '\u0006'), new TeletextPageCanvas$1.Command("insertAlphaWhiteCode", '\u0007'), new TeletextPageCanvas$1.Command("insertGraphBlackCode", '\u0010'), new TeletextPageCanvas$1.Command("insertGraphRedCode", '\u0011'), new TeletextPageCanvas$1.Command("insertGraphGreenCode", '\u0012'), new TeletextPageCanvas$1.Command("insertGraphYellowCode", '\u0013'), new TeletextPageCanvas$1.Command("insertGraphBlueCode", '\u0014'), new TeletextPageCanvas$1.Command("insertGraphMagentaCode", '\u0015'), new TeletextPageCanvas$1.Command("insertGraphCyanCode", '\u0016'), new TeletextPageCanvas$1.Command("insertGraphWhiteCode", '\u0017'), new TeletextPageCanvas$1.Command("insertSteadyCode", '\t'), new TeletextPageCanvas$1.Command("insertFlashCode", '\b'), new TeletextPageCanvas$1.Command("insertEndBoxCode", '\n'), new TeletextPageCanvas$1.Command("insertStartBoxCode", '\u000b'), new TeletextPageCanvas$1.Command("insertNormalHeightCode", '\f'), new TeletextPageCanvas$1.Command("insertDoubleHeightCode", '\r'), new TeletextPageCanvas$1.Command("insertConcealCode", '\u0018'), new TeletextPageCanvas$1.Command("insertGlueGraphicsCode", '\u0019'), new TeletextPageCanvas$1.Command("insertSeparateGraphicsCode", '\u001a'), new TeletextPageCanvas$1.Command("insertAutonumberCode", '\u001b'), new TeletextPageCanvas$1.Command("insertBlackBackgroundCode", '\u001c'), new TeletextPageCanvas$1.Command("insertNewBackgroundCode", '\u001d'), new TeletextPageCanvas$1.Command("insertHoldGraphicsCode", '\u001e'), new TeletextPageCanvas$1.Command("insertReleaseGraphicsCode", '\u001f') };
        for (int i = 0; i < array.length; ++i) {
            if (actionEvent.getActionCommand().equals(array[i].getActionCommand())) {
                this.insertCharacter(array[i].getTeletextCode());
            }
        }
        if (actionEvent.getActionCommand().equals("graphics:select") && this.editingMode == 1) {
            this.selectTeletextTool(this.selectionTool);
        }
        if (actionEvent.getActionCommand().equals("graphics:line") && this.editingMode == 1) {
            this.pushPage();
            this.selectTeletextTool(new TeletextLineTool());
        }
        if (actionEvent.getActionCommand().equals("graphics:rectangle") && this.editingMode == 1) {
            this.pushPage();
            this.selectTeletextTool(new TeletextRectangleTool());
        }
        if (actionEvent.getActionCommand().equals("graphics:ellipse") && this.editingMode == 1) {
            this.pushPage();
            this.selectTeletextTool(new TeletextEllipseTool());
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
            this.handleEditorEvent(new TeletextPageCanvasEvent(0));
        }
        else if (this.editingMode == 0 && n == 1) {
            this.editingMode = 1;
            this.cursorX *= TeletextFont.getHorizontalGraphCount();
            this.cursorY *= TeletextFont.getVerticalGraphCount();
            this.handleEditorEvent(new TeletextPageCanvasEvent(1));
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
    
    public boolean handleEditorEvent(final TeletextPageCanvasEvent teletextPageCanvasEvent) {
        for (int i = 0; i < this.teletextTools.size(); ++i) {
            ((TeletextTool)this.teletextTools.elementAt(i)).handleEditorEvent(teletextPageCanvasEvent);
        }
        return true;
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
        if (this.isPageDirty()) {
            this.pushPage();
        }
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
                            break;
                        }
                        this.moveCursorTo(this.cursorX, this.cursorY + 1);
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
        this.suspendUndoTracking();
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
        this.resumeUndoTracking();
        this.markPageDirty();
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
                if (keyEvent.getKeyCode() == array[i].getKeyCode() && keyEvent.getModifiers() == array[i].getModifiers()) {
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
        if (b || this.invokeMethodByKeyEvent(this.keyAssociations, keyEvent) != null) {}
        this.repaint();
    }
    
    public void processKeyReleasedEvent(final KeyEvent keyEvent) {
        for (int i = 0; i < this.teletextTools.size(); ++i) {
            if (((TeletextTool)this.teletextTools.elementAt(i)).implementKeyReleased(keyEvent)) {}
        }
    }
    
    public void processKeyTypedEvent(final KeyEvent keyEvent) {
        boolean b = false;
        for (int i = 0; i < this.teletextTools.size(); ++i) {
            if (((TeletextTool)this.teletextTools.elementAt(i)).implementKeyTyped(keyEvent)) {
                b = true;
            }
        }
        if (!b && keyEvent.getKeyChar() >= ' ' && keyEvent.getKeyChar() <= '\u007f') {
            switch (this.editingMode) {
                case 0: {
                    this.insertCharacter(keyEvent.getKeyChar());
                    break;
                }
            }
        }
    }
}
