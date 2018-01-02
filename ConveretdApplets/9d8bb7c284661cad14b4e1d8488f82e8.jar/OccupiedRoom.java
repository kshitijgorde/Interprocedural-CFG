// 
// Decompiled by Procyon v0.5.30
// 

class OccupiedRoom
{
    int \u013e;
    int \u015d;
    String name;
    boolean isPublic;
    
    public OccupiedRoom(final int \u013e, final int \u015d, final String name, final boolean isPublic) {
        this.\u013e = \u013e;
        this.\u015d = \u015d;
        this.name = name;
        this.isPublic = isPublic;
    }
    
    void setRoomID(final int \u013e) {
        this.\u013e = \u013e;
    }
    
    int getRoomID() {
        return this.\u013e;
    }
    
    public int getUniqueRoomNo() {
        return this.\u015d;
    }
    
    void \u015d(final String name) {
        this.name = name;
    }
    
    String getName() {
        return this.name;
    }
    
    boolean isPublic() {
        return this.isPublic;
    }
}
