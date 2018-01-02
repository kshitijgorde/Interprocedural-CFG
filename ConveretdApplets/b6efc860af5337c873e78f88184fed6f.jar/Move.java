// 
// Decompiled by Procyon v0.5.30
// 

class Move
{
    private static int MAX_LEGS;
    private int leg;
    private int legs;
    private Position from;
    private Position to;
    private double xIncrement;
    private double yIncrement;
    
    static {
        Move.MAX_LEGS = 10;
    }
    
    public Move(final Position from, final Position to) {
        this.from = from;
        this.to = to;
        final double deltaX = to.x() - from.x();
        final double deltaY = to.y() - from.y();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        distance = Math.max(1.0, distance);
        if (Screen.controls.isAborted()) {
            this.legs = 0;
        }
        else {
            this.legs = (int)Math.round(Math.sqrt(distance)) * Move.MAX_LEGS / Screen.controls.getSpeed();
        }
        if (this.legs > 0) {
            this.xIncrement = deltaX / this.legs;
            this.yIncrement = deltaY / this.legs;
        }
        this.leg = 1;
    }
    
    public boolean moreLegs() {
        return this.legs > 0;
    }
    
    public void nextMove(final Drawable object) {
        int x = (int)Math.round(this.from.x() + this.xIncrement * this.leg);
        int y = (int)Math.round(this.from.y() + this.yIncrement * this.leg);
        ++this.leg;
        final int legs = this.legs - 1;
        this.legs = legs;
        if (legs <= 0) {
            x = this.to.x();
            y = this.to.y();
        }
        object.reposition(new Position(x, y));
    }
}
