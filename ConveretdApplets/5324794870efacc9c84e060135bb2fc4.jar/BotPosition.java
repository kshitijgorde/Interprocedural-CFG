// 
// Decompiled by Procyon v0.5.30
// 

class BotPosition
{
    int x;
    int y;
    int heading;
    int direction;
    
    public BotPosition() {
        this.x = 0;
        this.y = 0;
        this.heading = 1;
        this.direction = 0;
    }
    
    public BotPosition(final int x, final int y, final int heading, final int direction) {
        this.x = x;
        this.y = y;
        this.heading = heading;
        this.direction = direction;
    }
}
