import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class HeapSortPanel extends ScreenPanel
{
    private static final int MAKE_TREE_BUTTON = 0;
    private static final Position ROOT_POSITION;
    private static final Position LIST_POSITION;
    private HeapSort heap;
    private DrawableString nodeLabel1;
    private DrawableString nodeLabel2;
    private String[] showHelp;
    private String[] tryHelp;
    
    static {
        ROOT_POSITION = new Position(273, 20);
        LIST_POSITION = new Position(0, 250);
    }
    
    public HeapSortPanel(final String[] names, final int first, final Color backgroundColor) {
        this.heap = new HeapSort(HeapSortPanel.ROOT_POSITION, HeapSortPanel.LIST_POSITION);
        this.nodeLabel1 = new DrawableString(HeapSortPanel.ROOT_POSITION);
        this.nodeLabel2 = new DrawableString(HeapSortPanel.ROOT_POSITION);
        this.showHelp = new String[] { "Generates an almost complete binary tree with a random number of nodes containing random values.", "Demonstrates the heap sort algorithm by first transforming the tree into a heap and then performing the sort." };
        this.tryHelp = new String[] { "Generates an almost complete binary tree with a random number of nodes containing random values.", "You will be asked to perform the heap sort algorithm by indicating which node pairs are to be swapped at each step of the algorithm." };
        super.init(names, this.showHelp, this.tryHelp, first, null, false, backgroundColor);
        Screen.canvas.add(this.heap);
        Screen.canvas.add(this.nodeLabel1);
        Screen.canvas.add(this.nodeLabel2);
        this.heap.show();
        this.buttonClicked(0);
        Screen.canvas.repaint();
    }
    
    public void buttonClicked(final int buttonNumber) {
        if (buttonNumber == 0) {
            this.heap.makeTree();
        }
        else {
            this.heap.sort(this.nodeLabel1, this.nodeLabel2);
        }
        Screen.canvas.repaint();
    }
}
