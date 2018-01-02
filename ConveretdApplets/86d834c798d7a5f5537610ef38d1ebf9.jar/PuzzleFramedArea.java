import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;

// 
// Decompiled by Procyon v0.5.30
// 

class PuzzleFramedArea extends FramedArea
{
    PuzzleArea puzzleArea;
    
    public PuzzleFramedArea(final SlidePuzzle slidePuzzle, final String s) {
        super(slidePuzzle, s);
        this.setLayout(new GridLayout(1, 0));
        this.add(this.puzzleArea = new PuzzleArea(slidePuzzle));
    }
    
    public void scramble() {
        this.puzzleArea.scramble();
    }
}
