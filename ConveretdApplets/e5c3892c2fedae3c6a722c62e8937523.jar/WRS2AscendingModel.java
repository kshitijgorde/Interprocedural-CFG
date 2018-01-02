// 
// Decompiled by Procyon v0.5.30
// 

public class WRS2AscendingModel extends WRS2Model
{
    WRS2AscendingModel() {
        super(true);
    }
    
    @Override
    public int checkRowBounds(int i) {
        while (i > 248) {
            i -= 124;
        }
        while (i < 125) {
            i += 124;
        }
        return i;
    }
    
    @Override
    public int getRowDownDirection() {
        return -1;
    }
    
    @Override
    public int getMaximumRow() {
        return 248;
    }
    
    @Override
    public int getMinimumRow() {
        return 125;
    }
}
