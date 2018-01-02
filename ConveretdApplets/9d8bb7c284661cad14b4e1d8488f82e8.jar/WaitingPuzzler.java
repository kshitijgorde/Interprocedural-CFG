// 
// Decompiled by Procyon v0.5.30
// 

class WaitingPuzzler
{
    int \u013e;
    int \u015d;
    String name;
    
    public WaitingPuzzler(final int \u013e, final int \u015d, final String name) {
        this.\u013e = \u013e;
        this.\u015d = \u015d;
        this.name = name;
    }
    
    void setRoomID(final int \u013e) {
        this.\u013e = \u013e;
    }
    
    int getRoomID() {
        return this.\u013e;
    }
    
    int getUniqueRoomNo() {
        return this.\u015d;
    }
    
    void \u015d(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
