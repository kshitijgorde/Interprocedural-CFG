// 
// Decompiled by Procyon v0.5.30
// 

class RowInfo
{
    static final int ROW_NORMAL = 0;
    static final int ROW_CONST = 1;
    static final int ROW_EQUAL = 2;
    int nodeEq;
    int type;
    int mapCol;
    int mapRow;
    double value;
    boolean rsChanges;
    boolean lsChanges;
    boolean dropRow;
    
    RowInfo() {
        this.type = 0;
    }
}
