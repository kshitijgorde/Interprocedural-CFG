import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class SortPanel extends ScreenPanel
{
    private static final Color SORTED_COLOR;
    private static final Position LIST_POSITION;
    protected static final Position ARRAY_POSITION;
    protected static final int SIZE = 30;
    protected SwapableArray swapableArray;
    protected SwapableList swapableList;
    protected int[] numericArray;
    
    static {
        SORTED_COLOR = Screen.DARK_RED;
        LIST_POSITION = new Position(18, 240);
        ARRAY_POSITION = new Position(18, 220);
    }
    
    SortPanel() {
        this.swapableArray = new SwapableArray(SortPanel.ARRAY_POSITION, 30);
        this.swapableList = new SwapableList(SortPanel.LIST_POSITION, 30, Screen.LIGHT_BLUE);
        this.numericArray = new int[31];
    }
    
    public void init(final String[] names, final String[] showHelp, final String[] tryHelp, final int first, final Color backgroundColor) {
        super.init(names, showHelp, tryHelp, first, null, false, backgroundColor);
        this.makeArray();
        Screen.canvas.add(this.swapableArray);
        Screen.canvas.add(this.swapableList);
        this.swapableArray.show();
        this.swapableList.show();
        this.swapableList.setColor(backgroundColor);
        Screen.canvas.repaint();
    }
    
    public void makeArray() {
        for (int index = 1; index <= 30; ++index) {
            this.numericArray[index] = this.getRandom(10, 100);
        }
        this.swapableArray.setValues(this.numericArray, 30);
        this.swapableList.setValues(this.numericArray, 30);
    }
    
    public void markSorted(final int index) {
        this.swapableArray.markSorted(index);
        this.swapableList.markSorted(index);
    }
    
    public void swap(final int i, final int j) {
        if (i == j) {
            return;
        }
        this.swapableArray.startSwap(i, j);
        this.swapableList.startSwap(i, j);
        Screen.canvas.awaitMovingCompletion();
        this.swapableList.finishSwap(i, j);
        this.swapableArray.finishSwap(i, j);
    }
}
