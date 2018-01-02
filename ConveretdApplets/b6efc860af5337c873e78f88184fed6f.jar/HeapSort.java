import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

class HeapSort extends Heap
{
    private static final int MIN_NODES = 10;
    private static Random random;
    
    static {
        HeapSort.random = new Random();
    }
    
    public HeapSort(final Position rootPosition, final Position listPosition) {
        super(rootPosition, listPosition);
    }
    
    public void makeTree() {
        final int[] values = new int[32];
        super.numberOfNodes = Math.abs(HeapSort.random.nextInt() % 16) + 10;
        for (int node = 1; node <= 32; ++node) {
            if (node <= super.numberOfNodes) {
                final int nodeValue = Math.abs(HeapSort.random.nextInt() % 100);
                super.heap[node].setNode(nodeValue, false);
                values[node] = nodeValue;
            }
            else {
                super.heap[node].setNode(-1, true);
            }
        }
        super.list.setValues(values, super.numberOfNodes);
    }
    
    public void sort(final DrawableString nodeLabel1, final DrawableString nodeLabel2) {
        Screen.interaction.displayMessage("Changing into a Heap");
        for (int totalNodes = super.numberOfNodes, node = 1; node <= totalNodes; ++node) {
            super.numberOfNodes = totalNodes;
            super.heap[node].reheapUp(nodeLabel1, nodeLabel2);
            super.heap[node].changeColor();
        }
        Screen.interaction.displayMessage("Performing Sort");
        while (super.numberOfNodes > 1) {
            super.root.swapNodes(super.heap[super.numberOfNodes], nodeLabel1, nodeLabel2);
            super.heap[super.numberOfNodes].changeColor();
            this.markSorted(super.numberOfNodes);
            super.root.reheapDown(--super.numberOfNodes, nodeLabel1, nodeLabel2);
        }
        super.root.changeColor();
        this.markSorted(super.numberOfNodes);
        Screen.interaction.displayMessage("");
    }
}
