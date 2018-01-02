import java.awt.image.ImageObserver;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Airplane
{
    public static final int NORTH = 0;
    public static final int NORTHEAST = 1;
    public static final int EAST = 2;
    public static final int SOUTHEAST = 3;
    public static final int SOUTH = 4;
    public static final int SOUTHWEST = 5;
    public static final int WEST = 6;
    public static final int NORTHWEST = 7;
    public static final int SPEED = 18;
    public static final int DIAGNAL_SPEED = 10;
    public static final int GROUND_SPEED = 5;
    public static final int DIAGNAL_GROUND_SPEED = 3;
    public static final int NORMAL = 0;
    public static final int LANDING = 1;
    public static final int LEAVING = 2;
    int x;
    int y;
    int w;
    int h;
    int direction;
    int status;
    int random_course;
    
    Airplane() {
        this.x = 410;
        this.y = 0;
        this.direction = 4;
        this.status = 0;
        this.random_course = (int)(Math.random() * 20.0);
    }
    
    public abstract void Draw(final Graphics p0, final ImageObserver p1);
    
    public boolean InTheWayOfNewAirplane() {
        if (this.status == 1) {
            return false;
        }
        switch (this.direction) {
            case 2: {
                return this.x > 265;
            }
            case 3: {
                return this.status != 1;
            }
            case 4: {
                return this.status != 1 && this.y < 135;
            }
            default: {
                return false;
            }
        }
    }
    
    public boolean CurrentlyLanding() {
        return this.status == 1 && this.direction <= 4;
    }
    
    public boolean RemoveMe() {
        return (this.status == 1 && this.direction == 0) || (this.status == 2 && (this.x < -40 || this.y < -40 || this.x > 450 || this.y > 450));
    }
    
    public boolean DoLandIfPossible() {
        if (this.status == 0 && this.direction == 2 && this.x >= 250 && this.x <= 275) {
            this.status = 1;
            this.direction = 3;
            return true;
        }
        return false;
    }
    
    public void Fly() {
        if (this.status == 1) {
            switch (this.direction) {
                case 3: {
                    this.y += 10;
                    this.x += 10;
                    if (this.x >= 280) {
                        this.x = 282;
                        this.direction = 4;
                        return;
                    }
                    break;
                }
                case 4: {
                    this.y += 18 - this.y / 20;
                    if (this.y >= 269) {
                        this.direction = 5;
                        return;
                    }
                    break;
                }
                case 5: {
                    this.x -= 3;
                    this.y += 3;
                    if (this.y >= 292) {
                        this.direction = 6;
                        return;
                    }
                    break;
                }
                case 6: {
                    this.x -= 5;
                    if (this.x <= 220) {
                        this.direction = 0;
                        return;
                    }
                    break;
                }
            }
        }
        else {
            switch (this.direction) {
                case 0: {
                    this.y -= 18;
                    if (this.y + this.random_course < 65 && this.status != 2) {
                        this.direction = 1;
                        this.random_course = (int)(Math.random() * 20.0);
                        return;
                    }
                    break;
                }
                case 1: {
                    this.y -= 10;
                    this.x += 10;
                    if (this.y + this.random_course < 35 && this.status != 2) {
                        this.direction = 2;
                        return;
                    }
                    break;
                }
                case 2: {
                    this.x += 18;
                    if (this.x + this.random_course > 370 && this.status != 2) {
                        this.direction = 3;
                        this.random_course = (int)(Math.random() * 20.0);
                        return;
                    }
                    break;
                }
                case 3: {
                    this.x += 10;
                    this.y += 10;
                    if (this.x + this.random_course > 400 && this.status != 2) {
                        this.direction = 4;
                        return;
                    }
                    break;
                }
                case 4: {
                    this.y += 18;
                    if (this.y + this.random_course > 358 && this.status != 2) {
                        this.direction = 5;
                        this.random_course = (int)(Math.random() * 20.0);
                        return;
                    }
                    break;
                }
                case 5: {
                    this.x -= 10;
                    this.y += 10;
                    if (this.y + this.random_course > 388 && this.status != 2) {
                        this.direction = 6;
                        return;
                    }
                    break;
                }
                case 6: {
                    this.x -= 18;
                    if (this.x + this.random_course < 62 && this.status != 2) {
                        this.direction = 7;
                        this.random_course = (int)(Math.random() * 20.0);
                        return;
                    }
                    break;
                }
                case 7: {
                    this.x -= 10;
                    this.y -= 10;
                    if (this.x + this.random_course < 32 && this.status != 2) {
                        this.direction = 0;
                        return;
                    }
                    break;
                }
            }
        }
    }
    
    public void Release(final int n, final int n2) {
        if (this.status == 0 && n >= this.x && n <= this.x + this.w && n2 >= this.y && n2 <= this.y + this.h) {
            this.status = 2;
        }
    }
}
