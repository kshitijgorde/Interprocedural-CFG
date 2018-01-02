import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class HeapList extends SwapableList
{
    private static final Color SORTED_COLOR;
    
    static {
        SORTED_COLOR = Screen.LIGHT_BLUE;
    }
    
    public HeapList(final Position position, final int size) {
        super(position, size, HeapList.SORTED_COLOR);
    }
    
    public void pop() {
        --super.count;
    }
    
    public void push(final int value) {
        super.list[++super.count] = value;
    }
}
