// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class TeletextRectangleTool extends TeletextTool implements TeletextToolInterface
{
    Color background;
    Color foreground;
    boolean dragActive;
    int x1;
    int y1;
    int x2;
    int y2;
    
    public TeletextRectangleTool() {
        this.background = Color.black;
        this.foreground = Color.white;
        this.dragActive = false;
        this.x1 = 0;
        this.y1 = 0;
        this.x2 = 0;
        this.y2 = 0;
    }
    
    public void implementMousePressed(final MouseEvent mouseEvent) {
        switch (this.getPageCanvas().getEditingMode()) {
            case 1: {
                this.x1 = this.getPageCanvas().pixelToCursorX(mouseEvent.getX());
                this.y1 = this.getPageCanvas().pixelToCursorY(mouseEvent.getY());
                this.dragActive = true;
                break;
            }
        }
    }
    
    public void implementMouseReleased(final MouseEvent mouseEvent) {
        if (this.dragActive) {
            this.getPageCanvas().moveCursorToPixel(mouseEvent.getX(), mouseEvent.getY());
            this.x2 = this.getPageCanvas().getCursorX();
            this.y2 = this.getPageCanvas().getCursorY();
            this.dragActive = false;
            this.getPageCanvas().getPage().drawRectangle(this.x1, this.y1, this.x2, this.y2, true);
            this.renderCanvas();
            this.repaintCanvas();
        }
    }
    
    public void implementMouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void implementMouseDragged(final MouseEvent mouseEvent) {
        if (this.dragActive) {
            this.getPageCanvas().moveCursorToPixel(mouseEvent.getX(), mouseEvent.getY());
            this.x2 = this.getPageCanvas().getCursorX();
            this.y2 = this.getPageCanvas().getCursorY();
            this.repaintCanvas();
        }
    }
    
    public boolean implementKeyPressed(final KeyEvent keyEvent) {
        return false;
    }
    
    public boolean implementKeyReleased(final KeyEvent keyEvent) {
        return false;
    }
    
    public void paint(final Graphics graphics) {
        if (this.dragActive) {
            final int[] graphPixelCenter = this.getPageCanvas().getRenderBuffer().getGraphPixelCenter(this.x1, this.y1);
            final int[] graphPixelCenter2 = this.getPageCanvas().getRenderBuffer().getGraphPixelCenter(this.x2, this.y2);
            graphics.setColor(this.background);
            for (int i = -1; i < 2; ++i) {
                for (int j = -1; j < 2; ++j) {
                    graphics.drawRect(graphPixelCenter[0] + i, graphPixelCenter[1] + j, graphPixelCenter2[0] - graphPixelCenter[0] + i, graphPixelCenter2[1] - graphPixelCenter[1] + j);
                }
            }
            graphics.setColor(this.foreground);
            graphics.drawRect(graphPixelCenter[0], graphPixelCenter[1], graphPixelCenter2[0] - graphPixelCenter[0], graphPixelCenter2[1] - graphPixelCenter[1]);
        }
    }
    
    public boolean invokeMethod(final String s) {
        return false;
    }
    
    public void handleEditorEvent(final TeletextPageCanvasEvent teletextPageCanvasEvent) {
        switch (teletextPageCanvasEvent.getEventCode()) {
            case 1: {
                this.getPageCanvas().selectDefaultTeletextTool();
                break;
            }
        }
    }
}
