import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class ColorListener extends MouseAdapter
{
    public void mousePressed(final MouseEvent mouseEvent) {
        final ColorCanvas colorCanvas = (ColorCanvas)mouseEvent.getComponent();
        ColoringBook.col = colorCanvas.getColor();
        ColoringBook.currentCanvas = colorCanvas.getNumber();
        for (int i = 0; i < 16; ++i) {
            ((ColorCanvas)ColoringBook.pallete.elementAt(i)).repaint();
        }
        ColoringBook.con.refresh();
    }
}
