// 
// Decompiled by Procyon v0.5.30
// 

class worm
{
    int howLong;
    boolean dead;
    boolean last;
    int x;
    int y;
    worm next;
    worm front;
    
    worm() {
        this.howLong = 1;
        this.dead = false;
        this.last = true;
        this.x = 9;
        this.y = 9;
        this.front = null;
    }
    
    public void worm() {
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public worm getNext() {
        return this.next;
    }
    
    public boolean isDead() {
        return this.dead;
    }
    
    public void setNext(final worm next) {
        this.next = next;
    }
    
    public worm getFront() {
        return this.front;
    }
    
    public void setFront(final worm front) {
        this.front = front;
    }
    
    public boolean getLast() {
        return this.last;
    }
    
    public void setLast(final boolean last) {
        this.last = last;
    }
    
    public void moveL(final boolean b) {
        if (b) {
            this.addWorm();
        }
        this.moveAll(this);
        --this.x;
        this.checkCollision();
    }
    
    public void moveR(final boolean b) {
        if (b) {
            this.addWorm();
        }
        this.moveAll(this);
        ++this.x;
        this.checkCollision();
    }
    
    public void moveU(final boolean b) {
        if (b) {
            this.addWorm();
        }
        this.moveAll(this);
        --this.y;
        this.checkCollision();
    }
    
    public void moveD(final boolean b) {
        if (b) {
            this.addWorm();
        }
        this.moveAll(this);
        ++this.y;
        this.checkCollision();
    }
    
    public void addWorm() {
        worm next;
        for (next = this; next.getNext() != null; next = next.getNext()) {}
        final worm next2 = new worm();
        next.setNext(next2);
        next2.setFront(next);
        next.setLast(false);
    }
    
    public void moveAll(worm worm) {
        while (worm.getNext() != null) {
            worm = worm.getNext();
        }
        while (worm.getFront() != null) {
            worm.setX(worm.getFront().getX());
            worm.setY(worm.getFront().getY());
            worm = worm.getFront();
        }
    }
    
    private void checkCollision() {
        for (worm worm = this.next; worm != null && !this.dead; worm = worm.getNext()) {
            if (this.x == worm.getX() && this.y == worm.getY()) {
                this.dead = true;
            }
        }
    }
}
