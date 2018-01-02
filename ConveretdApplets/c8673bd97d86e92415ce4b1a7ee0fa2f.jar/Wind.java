// 
// Decompiled by Procyon v0.5.30
// 

class Wind
{
    private int xco;
    private int yco;
    private int width;
    private int height;
    private boolean alive;
    private int maxX;
    private int maxY;
    private int depth;
    private int sign;
    private int speed;
    private int strength;
    
    public Wind(final int maxX, final int maxY) {
        this.sign = 1;
        this.speed = 1;
        this.maxY = maxY;
        this.maxX = maxX;
        this.xco = 100;
        this.yco = 100;
        this.width = 50;
        this.height = 50;
        this.depth = 1;
    }
    
    public int getDepth() {
        return this.depth;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getSign() {
        return this.sign;
    }
    
    public int getStrength() {
        return this.strength;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getXco() {
        return this.xco;
    }
    
    public int getYco() {
        return this.yco;
    }
    
    public boolean isAlive() {
        return this.alive;
    }
    
    public void move() {
        if (this.getXco() + this.getWidth() < 0 || this.getXco() > this.maxX) {
            this.alive = false;
        }
        this.xco = this.getXco() + this.speed * this.sign;
    }
    
    public void newWind() {
        this.xco = (int)(Math.random() * this.maxX - 50.0);
        this.yco = (int)(Math.random() * this.maxY - 50.0);
        this.depth = (int)(Math.random() * 3.0);
        this.speed = (int)(Math.random() * 3.0) + 1;
        this.sign *= -1;
        this.strength = (int)(Math.random() * 3.0) + 1;
        this.width = 100;
        this.height = 50;
        this.alive = true;
    }
}
