// 
// Decompiled by Procyon v0.5.30
// 

public class Move
{
    private int \u0131;
    private int \u0132;
    private int \u0133;
    private int \u0134;
    private int \u0135;
    private boolean \u0136;
    private boolean \u0137;
    
    Move() {
        this.\u0131 = 0;
        this.\u0132 = 0;
        this.\u0133 = 0;
        this.\u0134 = 0;
        this.\u0135 = 0;
        this.\u0136 = false;
        this.\u0137 = false;
    }
    
    public void set(final int \u0131, final int \u0133, final int \u01332, final int \u0135, final boolean \u0137, final int \u01352, final boolean \u01372) {
        this.\u0131 = \u0131;
        this.\u0132 = \u0133;
        this.\u0133 = \u01332;
        this.\u0134 = \u0135;
        this.\u0136 = \u0137;
        this.\u0135 = \u01352;
        this.\u0137 = \u01372;
    }
    
    public void set(final int \u0131, final int \u0133, final int \u01332, final int \u0135) {
        this.\u0131 = \u0131;
        this.\u0132 = \u0133;
        this.\u0133 = \u01332;
        this.\u0134 = \u0135;
        this.\u0136 = false;
        this.\u0135 = 0;
        this.\u0137 = false;
    }
    
    public int getType() {
        return this.\u0131;
    }
    
    public int getRow() {
        return this.\u0132;
    }
    
    public int getColumn() {
        return this.\u0133;
    }
    
    public int getValue() {
        return this.\u0134;
    }
    
    public boolean getMode() {
        return this.\u0136;
    }
    
    public int getPreviousValue() {
        return this.\u0135;
    }
    
    public boolean getPreviousMode() {
        return this.\u0137;
    }
}
