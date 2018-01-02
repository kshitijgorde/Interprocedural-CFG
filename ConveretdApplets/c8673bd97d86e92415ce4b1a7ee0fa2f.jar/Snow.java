// 
// Decompiled by Procyon v0.5.30
// 

class Snow
{
    private int xco;
    private int yco;
    private int depth;
    private int weight;
    private int drift;
    private int driftSign;
    private int maxY;
    private int maxX;
    private boolean alive;
    private int size;
    
    public Snow(final int maxX, final int maxY) {
        this.maxY = maxY;
        this.maxX = maxX;
        this.driftSign = 1;
    }
    
    private void disable() {
        this.alive = false;
    }
    
    public void dodge(final int n) {
        this.setXco(this.getXco() + n);
    }
    
    private void drift() {
        if (this.drift <= 0) {
            this.drift = (int)(Math.random() * 10.0);
            this.driftSign *= -1;
        }
        --this.drift;
    }
    
    private void enable() {
        this.alive = true;
    }
    
    public int getDepth() {
        return this.depth;
    }
    
    private int getDrift() {
        if (this.drift > 0) {
            return this.driftSign;
        }
        return 0;
    }
    
    public int getSize() {
        return this.size;
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
        if (this.getYco() % 2 == 0) {
            this.drift();
        }
        this.setYco(this.getYco() + this.weight / 2);
        this.setXco(this.getXco() + this.getDrift());
        if (this.getYco() > this.maxY) {
            this.disable();
        }
    }
    
    public void newFlake() {
        this.randSnowFlake();
    }
    
    private void randSnowFlake() {
        this.setYco(0);
        this.setXco((int)(Math.random() * this.maxX));
        this.setWeight((int)(Math.random() * 8.0));
        this.setDepth((int)(Math.random() * 3.0));
        this.setSize((int)(Math.random() * 2.0) + 1);
        this.enable();
    }
    
    private void setDepth(final int depth) {
        this.depth = depth;
    }
    
    private void setSize(final int size) {
        this.size = size;
    }
    
    private void setWeight(final int weight) {
        if (weight > 2) {
            this.weight = weight;
        }
        else {
            this.weight = 2;
        }
    }
    
    private void setXco(final int xco) {
        this.xco = xco;
    }
    
    private void setYco(final int yco) {
        this.yco = yco;
    }
}
