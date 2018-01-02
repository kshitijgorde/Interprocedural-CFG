// 
// Decompiled by Procyon v0.5.30
// 

class simple_puzzle extends puzzle_layout
{
    simple_puzzle(final int start_index, final puzzle puz) {
        for (int i = 0; i <= 15; ++i) {
            super.win[i] = i + 1;
        }
        super.win_index = 15;
        super.start_index = start_index;
        super.puz = puz;
    }
    
    public boolean puzzle_complete() {
        if (super.puz.getBlankLocValue() != super.win_index) {
            return false;
        }
        for (int i = 0; i <= 15; ++i) {
            if (super.puz.getLocValue(i) != super.win[i]) {
                return false;
            }
        }
        return true;
    }
}
