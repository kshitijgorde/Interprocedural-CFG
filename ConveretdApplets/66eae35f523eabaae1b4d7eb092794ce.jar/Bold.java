// 
// Decompiled by Procyon v0.5.30
// 

public final class Bold
{
    private int x;
    private int y;
    private int nextX;
    private int nextY;
    private int step;
    private int heading;
    private int speed;
    private int maxX;
    private int maxY;
    private int minY;
    private int minX;
    
    public Bold(final int maxX, final int n) {
        this.x = maxX / 2 - 2;
        this.y = n / 2 + 25;
        this.maxX = maxX;
        this.maxY = n - 9;
        this.minX = 0;
        this.minY = 51;
        this.heading = 1;
        this.step = 0;
        this.speed = 0;
        this.nextX = maxX / 2 - 2;
        this.nextY = n / 2 + 25;
        this.beregnNext();
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public int getHeading() {
        return this.heading;
    }
    
    public void setHeading(final int heading) {
        this.heading = heading;
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public void setSpeed(final int speed) {
        this.speed = speed;
    }
    
    public void move() {
        this.y = this.nextY;
        this.x = this.nextX;
        this.beregnNext();
    }
    
    public void beregnNext() {
        switch (this.heading) {
            case 1: {
                if (this.y >= this.minY + 2) {
                    this.nextY = this.y - this.speed;
                    this.nextX = this.x + this.speed;
                    break;
                }
                this.headingSkiftY();
                break;
            }
            case 2: {
                this.nextX = this.x + this.speed;
                break;
            }
            case 3: {
                if (this.y <= this.maxY - 2) {
                    this.nextY = this.y + this.speed;
                    this.nextX = this.x + this.speed;
                    break;
                }
                this.headingSkiftY();
                break;
            }
            case 4: {
                if (this.y <= this.maxY - 2) {
                    this.nextY = this.y + this.speed;
                    this.nextX = this.x - this.speed;
                    break;
                }
                this.headingSkiftY();
                break;
            }
            case 5: {
                this.nextX = this.x - this.speed;
                break;
            }
            case 6: {
                if (this.y >= this.minY + 2) {
                    this.nextY = this.y - this.speed;
                    this.nextX = this.x - this.speed;
                    break;
                }
                this.headingSkiftY();
                break;
            }
        }
    }
    
    public void headingSkiftY() {
        switch (this.heading) {
            case 1: {
                this.heading = 3;
                break;
            }
            case 2: {
                this.heading = 5;
                break;
            }
            case 3: {
                this.heading = 1;
                break;
            }
            case 4: {
                this.heading = 6;
                break;
            }
            case 5: {
                this.heading = 2;
                break;
            }
            case 6: {
                this.heading = 4;
                break;
            }
        }
        pingpong.playSound();
    }
    
    public void headingSkiftX1() {
        switch (this.heading) {
            case 1: {
                this.heading = 6;
                break;
            }
            case 2: {
                this.heading = 5;
                break;
            }
            case 3: {
                this.heading = 4;
                break;
            }
            case 4: {
                this.heading = 3;
                break;
            }
            case 5: {
                this.heading = 2;
                break;
            }
            case 6: {
                this.heading = 1;
                break;
            }
        }
        this.beregnNext();
        pingpong.playSound();
    }
    
    public void headingSkiftX2() {
        switch (this.heading) {
            case 1: {
                this.heading = 4;
                break;
            }
            case 2: {
                this.heading = 5;
                break;
            }
            case 3: {
                this.heading = 6;
                break;
            }
            case 4: {
                this.heading = 1;
                break;
            }
            case 5: {
                this.heading = 2;
                break;
            }
            case 6: {
                this.heading = 3;
                break;
            }
        }
        this.beregnNext();
        pingpong.playSound();
    }
}
