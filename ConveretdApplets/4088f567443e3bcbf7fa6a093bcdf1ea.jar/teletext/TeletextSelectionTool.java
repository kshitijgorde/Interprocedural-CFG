// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.Clipboard;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.datatransfer.ClipboardOwner;

public class TeletextSelectionTool extends TeletextTool implements TeletextToolInterface, ClipboardOwner
{
    SelectionData selectionData;
    
    public boolean isDisposable() {
        return false;
    }
    
    void createSelection() {
        (this.selectionData = new SelectionData()).setX1(this.getPageCanvas().getCursorX());
        this.selectionData.setY1(this.getPageCanvas().getCursorY());
        this.selectionData.setX2(this.getPageCanvas().getCursorX());
        this.selectionData.setY2(this.getPageCanvas().getCursorY());
    }
    
    void extendSelectionToCursor() {
        this.selectionData.setX2(this.getPageCanvas().getCursorX());
        this.selectionData.setY2(this.getPageCanvas().getCursorY());
        if (this.getPageCanvas().getCursorX() < this.selectionData.getX1()) {
            this.selectionData.setX1(this.getPageCanvas().getCursorX());
        }
        if (this.getPageCanvas().getCursorY() < this.selectionData.getY1()) {
            this.selectionData.setY1(this.getPageCanvas().getCursorY());
        }
        if (this.selectionData.isChanged()) {
            this.getPageCanvas().repaint();
        }
    }
    
    public void implementMousePressed(final MouseEvent mouseEvent) {
        this.selectionData = null;
    }
    
    public void implementMouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) > 0) {
            this.getPageCanvas().render();
            this.getPageCanvas().repaint();
        }
    }
    
    public void implementMouseClicked(final MouseEvent mouseEvent) {
        this.getPageCanvas().moveCursorToPixel(mouseEvent.getX(), mouseEvent.getY());
        this.getPageCanvas().repaint();
    }
    
    public void implementMouseDragged(final MouseEvent mouseEvent) {
        this.getPageCanvas();
        int n = this.getPageCanvas().pixelToCursorX(mouseEvent.getX());
        int n2 = this.getPageCanvas().pixelToCursorY(mouseEvent.getY());
        if (this.selectionData != null) {
            if (n < this.selectionData.getX1()) {
                n = this.selectionData.getX1();
            }
            if (n2 < this.selectionData.getY1()) {
                n2 = this.selectionData.getY1();
            }
        }
        this.getPageCanvas().moveCursorTo(n, n2);
        if (this.selectionData == null) {
            this.createSelection();
        }
        this.extendSelectionToCursor();
    }
    
    public boolean implementKeyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 27: {
                this.selectionData = null;
                this.getPageCanvas().render();
                return true;
            }
            case 127: {
                this.deleteSelection();
                this.getPageCanvas().render();
                this.selectionData = null;
                break;
            }
            case 16:
            case 17: {
                return false;
            }
        }
        if (keyEvent.isShiftDown()) {
            boolean b = true;
            if (this.selectionData == null) {
                this.createSelection();
            }
            switch (keyEvent.getKeyCode()) {
                case 37: {
                    this.getPageCanvas().moveCursorLeft();
                    break;
                }
                case 39: {
                    this.getPageCanvas().moveCursorRight();
                    break;
                }
                case 38: {
                    this.getPageCanvas().moveCursorUp();
                    break;
                }
                case 40: {
                    this.getPageCanvas().moveCursorDown();
                    break;
                }
                case 36: {
                    this.getPageCanvas().beginningOfLine();
                    break;
                }
                case 35: {
                    this.getPageCanvas().endOfLine();
                    break;
                }
                default: {
                    b = false;
                    break;
                }
            }
            if (b && this.selectionData != null) {
                this.extendSelectionToCursor();
                this.getPageCanvas().repaint();
                return true;
            }
        }
        if (keyEvent.isControlDown()) {
            switch (keyEvent.getKeyCode()) {
                case 67: {
                    if (this.selectionData != null) {
                        this.copyToClipboard();
                    }
                    return true;
                }
                case 88: {
                    if (this.selectionData != null) {
                        this.cutToClipboard();
                    }
                    return true;
                }
                case 86: {
                    this.pasteFromClipboard();
                    return true;
                }
            }
        }
        if (this.selectionData != null) {
            this.getPageCanvas().render();
            this.getPageCanvas().moveCursorTo(this.selectionData.getX1(), this.selectionData.getY1());
            this.selectionData = null;
        }
        return false;
    }
    
    public void lostOwnership(final Clipboard clipboard, final Transferable transferable) {
    }
    
    public void deleteSelection() {
        switch (this.getPageCanvas().getEditingMode()) {
            case 0: {
                this.getPage().clear(this.selectionData.getX1(), this.selectionData.getY1(), this.selectionData.getX2(), this.selectionData.getY2());
                break;
            }
            case 1: {
                System.out.println("clear not supported yet");
                break;
            }
        }
    }
    
    public void copyToClipboard() {
        final Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        final StringBuffer sb = new StringBuffer();
        switch (this.getPageCanvas().getEditingMode()) {
            case 0: {
                for (int i = this.selectionData.getY1(); i <= this.selectionData.getY2(); ++i) {
                    for (int j = this.selectionData.getX1(); j <= this.selectionData.getX2(); ++j) {
                        sb.append(this.getPage().getCharacterAt(j, i));
                    }
                    if (i < this.selectionData.getY2()) {
                        sb.append('\n');
                    }
                }
                systemClipboard.setContents(new StringSelection(sb.toString()), this);
                break;
            }
            case 1: {
                for (int k = this.selectionData.getY1(); k <= this.selectionData.getY2(); ++k) {
                    for (int l = this.selectionData.getX1(); l <= this.selectionData.getX2(); ++l) {
                        sb.append((char)(this.getPage().getPixelAt(l, k) ? 49 : 48));
                    }
                    if (k < this.selectionData.getY2()) {
                        sb.append('\n');
                    }
                }
                systemClipboard.setContents(new StringSelection(sb.toString()), this);
                break;
            }
        }
    }
    
    public void cutToClipboard() {
        this.copyToClipboard();
        this.deleteSelection();
        this.selectionData = null;
        this.getPageCanvas().render();
        this.getPageCanvas().repaint();
    }
    
    public void pasteFromClipboard() {
        final Transferable contents = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(this);
        if (contents == null) {
            return;
        }
        String s = null;
        try {
            s = (String)contents.getTransferData(DataFlavor.stringFlavor);
        }
        catch (IOException ex) {}
        catch (UnsupportedFlavorException ex2) {}
        if (s == null) {
            return;
        }
        final int cursorX = this.getPageCanvas().getCursorX();
        final int cursorY = this.getPageCanvas().getCursorY();
        switch (this.getPageCanvas().getEditingMode()) {
            case 0: {
                final int cursorX2 = this.getPageCanvas().getCursorX();
                int cursorX3 = this.getPageCanvas().getCursorX();
                int cursorY2 = this.getPageCanvas().getCursorY();
                for (int i = 0; i < s.length(); ++i) {
                    if (s.charAt(i) != '\n') {
                        if (cursorX3 < this.getPage().getWidth() && cursorY2 < this.getPage().getHeight()) {
                            this.getPage().setCharacterAt(cursorX3, cursorY2, s.charAt(i));
                        }
                        ++cursorX3;
                    }
                    else {
                        cursorX3 = cursorX2;
                        ++cursorY2;
                    }
                }
                break;
            }
            case 1: {
                final int cursorX4 = this.getPageCanvas().getCursorX();
                int cursorX5 = this.getPageCanvas().getCursorX();
                int cursorY3 = this.getPageCanvas().getCursorY();
                for (int j = 0; j < s.length(); ++j) {
                    if (s.charAt(j) != '\n') {
                        if (cursorX5 < this.getPage().getWidth() && cursorY3 < this.getPage().getHeight()) {
                            this.getPage().setPixelAt(cursorX5, cursorY3, s.charAt(j) == '1');
                        }
                        ++cursorX5;
                    }
                    else {
                        cursorX5 = cursorX4;
                        ++cursorY3;
                    }
                }
                break;
            }
        }
        this.getPageCanvas().moveCursorTo(cursorX, cursorY);
        this.selectionData = null;
        this.getPageCanvas().render();
        this.getPageCanvas().repaint();
    }
    
    public boolean implementKeyReleased(final KeyEvent keyEvent) {
        return false;
    }
    
    public void paint(final Graphics graphics) {
        if (this.selectionData != null) {
            int[] array;
            if (this.getPageCanvas().getEditingMode() == 0) {
                array = this.getPageCanvas().getRenderBuffer().getCharacterRectangleParameters(this.selectionData.getX1(), this.selectionData.getY1(), this.selectionData.getX2(), this.selectionData.getY2());
            }
            else {
                array = this.getPageCanvas().getRenderBuffer().getGraphRectangleParameters(this.selectionData.getX1(), this.selectionData.getY1(), this.selectionData.getX2(), this.selectionData.getY2());
            }
            final int n = array[0];
            final int n2 = array[1];
            final int n3 = array[2] - 1;
            final int n4 = array[3] - 1;
            graphics.setColor(Color.white);
            graphics.setXORMode(Color.black);
            graphics.fillRect(n + 1, n2 + 1, n3 - 1, n4 - 1);
            graphics.setPaintMode();
            graphics.setColor(Color.green);
            graphics.drawRect(n, n2, n3, n4);
            graphics.setColor(Color.blue);
            graphics.drawRect(n + 1, n2 + 1, n3 - 2, n4 - 2);
            graphics.setColor(Color.white);
            this.drawMessage(graphics, n + n3 / 2, n2 - 5, new Integer(this.selectionData.getWidth()).toString());
            this.drawMessage(graphics, n - 20, n2 + n4 / 2, new Integer(this.selectionData.getHeight()).toString());
        }
    }
    
    public TeletextSelectionTool() {
        this.selectionData = null;
    }
}
