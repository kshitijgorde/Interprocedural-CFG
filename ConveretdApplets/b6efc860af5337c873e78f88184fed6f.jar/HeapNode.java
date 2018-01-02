// 
// Decompiled by Procyon v0.5.30
// 

class HeapNode extends DrawableNode
{
    private int nodeNumber;
    private HeapNode parent;
    private HeapNode leftChild;
    private HeapNode rightChild;
    private Heap heap;
    
    public HeapNode(final int level, final int nodeNumber, final Position position, final HeapNode parent, final HeapNode[] heapList, final Heap heap) {
        super.level = level;
        this.nodeNumber = nodeNumber;
        super.x = position.x();
        super.y = position.y();
        this.parent = parent;
        this.heap = heap;
        super.nullNode = true;
        heapList[nodeNumber] = this;
        if (level < 6) {
            this.leftChild = new HeapNode(level + 1, nodeNumber * 2, this.leftPosition(), this, heapList, heap);
            this.rightChild = new HeapNode(level + 1, nodeNumber * 2 + 1, this.rightPosition(), this, heapList, heap);
        }
    }
    
    public void addRightLeaf(final int value, final DrawableString nodeLabel1, final DrawableString nodeLabel2) {
        super.value = value;
        super.nullNode = false;
        this.reheapUp(nodeLabel1, nodeLabel2);
    }
    
    public DrawableNode left() {
        return this.leftChild;
    }
    
    public void reheapDown(final int numberOfNodes, final DrawableString nodeLabel1, final DrawableString nodeLabel2) {
        HeapNode current = this;
        final int currentValue = current.value;
        while (current.leftChild.nodeNumber <= numberOfNodes) {
            HeapNode next;
            if (current.rightChild.nodeNumber > numberOfNodes) {
                next = current.leftChild;
            }
            else {
                final int leftValue = current.leftChild.value;
                final int rightValue = current.rightChild.value;
                if (leftValue > rightValue) {
                    next = current.leftChild;
                }
                else {
                    next = current.rightChild;
                }
            }
            final int nextValue = next.value;
            if (nextValue > currentValue) {
                current.swapNodes(next, nodeLabel1, nodeLabel2);
            }
            current = next;
        }
    }
    
    public void reheapUp(final DrawableString nodeLabel1, final DrawableString nodeLabel2) {
        for (HeapNode current = this, parent = this.parent; parent != null; parent = current.parent) {
            final int currentValue = current.value;
            final int parentValue = parent.value;
            if (parentValue >= currentValue) {
                break;
            }
            current.swapNodes(parent, nodeLabel1, nodeLabel2);
            current = parent;
        }
    }
    
    public void removeRightLeaf(final HeapNode root, final DrawableString nodeLabel1, final DrawableString nodeLabel2) {
        final int newRootValue = super.value;
        super.nullNode = true;
        if (this != root) {
            root.value = -1;
            nodeLabel1.setString(String.valueOf(super.value));
            nodeLabel1.reposition(this.NodeStringPosition(super.x, super.y));
            this.moveValue(root, nodeLabel1);
            this.heap.startSwap(root.nodeNumber, this.nodeNumber);
            Screen.canvas.awaitMovingCompletion();
            this.heap.finishSwap(root.nodeNumber, this.nodeNumber);
            root.value = newRootValue;
            nodeLabel1.hide();
            root.reheapDown(this.nodeNumber, nodeLabel1, nodeLabel2);
        }
        Screen.canvas.repaint();
    }
    
    public DrawableNode right() {
        return this.rightChild;
    }
    
    public void setNode(final int value, final boolean nullNode) {
        super.value = value;
        super.nullNode = nullNode;
    }
    
    public void swapNodes(final HeapNode second, final DrawableString nodeLabel1, final DrawableString nodeLabel2) {
        final int firstValue = super.value;
        final int secondValue = second.value;
        nodeLabel1.setString(String.valueOf(super.value));
        nodeLabel1.reposition(this.NodeStringPosition(super.x, super.y));
        nodeLabel2.setString(String.valueOf(second.value));
        nodeLabel2.reposition(this.NodeStringPosition(second.x, second.y));
        if (Screen.panel.isInteractive()) {
            Screen.canvas.acceptClick(nodeLabel1);
            Screen.canvas.acceptClick(nodeLabel2);
            Screen.canvas.awaitClick("Click Nodes To Swap", true, false);
        }
        else {
            Screen.controls.nextStep(true, true);
        }
        this.moveValue(second, nodeLabel1);
        second.moveValue(this, nodeLabel2);
        this.heap.startSwap(this.nodeNumber, second.nodeNumber);
        Screen.canvas.awaitMovingCompletion();
        this.heap.finishSwap(this.nodeNumber, second.nodeNumber);
        nodeLabel1.hide();
        nodeLabel2.hide();
        super.value = secondValue;
        second.value = firstValue;
        Screen.canvas.repaint();
    }
}
