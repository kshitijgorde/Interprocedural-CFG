// 
// Decompiled by Procyon v0.5.30
// 

class PQHeap extends Heap
{
    private static final int DIGITS = 2;
    private static final int LOWER = 0;
    private static final int UPPER = 99;
    
    public PQHeap(final Position rootPosition, final Position listPosition) {
        super(rootPosition, listPosition);
    }
    
    public void dequeue(final DrawableString nodeLabel1, final DrawableString nodeLabel2) {
        Screen.interaction.displayMessage(" ");
        if (super.numberOfNodes > 0) {
            super.heap[super.numberOfNodes--].removeRightLeaf(super.root, nodeLabel1, nodeLabel2);
            super.list.pop();
        }
        else {
            Screen.interaction.displayMessage("Queue Is Empty");
        }
    }
    
    public void enqueue(final DrawableString nodeLabel1, final DrawableString nodeLabel2) {
        Screen.interaction.displayMessage(" ");
        if (super.numberOfNodes < 31) {
            if (Screen.panel.userPicks()) {
                Screen.prompt.displayPrompt("Enter Value To Enqueue: ");
            }
            final int nodeValue = Screen.prompt.getData(2, 0, 99, false);
            if (nodeValue >= 0) {
                super.list.push(nodeValue);
                super.heap[++super.numberOfNodes].addRightLeaf(nodeValue, nodeLabel1, nodeLabel2);
            }
        }
        else {
            Screen.interaction.displayMessage("Queue Is Full");
        }
    }
}
