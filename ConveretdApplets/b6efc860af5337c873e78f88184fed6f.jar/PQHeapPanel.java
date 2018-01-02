import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class PQHeapPanel extends ScreenPanel
{
    private static final int ENQUEUE_BUTTON = 0;
    private static final Position ROOT_POSITION;
    private static final Position LIST_POSITION;
    private PQHeap heap;
    private DrawableString nodeLabel1;
    private DrawableString nodeLabel2;
    private String randomPickHelp;
    private String[] showHelp;
    private String[] tryHelp;
    
    static {
        ROOT_POSITION = new Position(273, 20);
        LIST_POSITION = new Position(0, 250);
    }
    
    public PQHeapPanel(final String[] names, final int first, final Color backgroundColor) {
        this.heap = new PQHeap(PQHeapPanel.ROOT_POSITION, PQHeapPanel.LIST_POSITION);
        this.nodeLabel1 = new DrawableString(PQHeapPanel.ROOT_POSITION);
        this.nodeLabel2 = new DrawableString(PQHeapPanel.ROOT_POSITION);
        this.randomPickHelp = "Selecting the Random button causes values to be randomly generated for the enqueue operation.  Selecting I'll Pick allows you to enter the value in a box the will appear in the upper left corner of the screen when the enqueue operation is selected.";
        this.showHelp = new String[] { "Enqueues a new value onto the priority queue, which adds a node at the bottom of the tree.  The tree is then transformed back into a heap.", "Dequeues the highest value from the queue, which removes the value in the root.  The bottom value is moved to the root and the tree is then transformed back into a heap." };
        this.tryHelp = new String[] { "Enqueues a new value onto the priority queue, which adds a node at the bottom of the tree.  If the resulting tree is not a heap, you will be asked to click the node pairs to swap to transform it into a heap.", "Dequeues the highest value from the queue, which removes the value in the root.  The bottom value is moved to the root.  If the resulting tree is not a heap, you will be asked to click the node pairs to swap to transform it into a heap." };
        super.init(names, this.showHelp, this.tryHelp, first, this.randomPickHelp, false, backgroundColor);
        Screen.canvas.add(this.heap);
        Screen.canvas.add(this.nodeLabel1);
        Screen.canvas.add(this.nodeLabel2);
        this.heap.show();
        Screen.canvas.repaint();
    }
    
    public void buttonClicked(final int buttonNumber) {
        if (buttonNumber == 0) {
            this.heap.enqueue(this.nodeLabel1, this.nodeLabel2);
        }
        else {
            this.heap.dequeue(this.nodeLabel1, this.nodeLabel2);
        }
        Screen.canvas.repaint();
    }
}
