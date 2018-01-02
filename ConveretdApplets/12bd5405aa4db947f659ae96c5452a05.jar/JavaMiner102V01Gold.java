import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class JavaMiner102V01Gold extends JavaMiner102V01Object
{
    public static final int STANDARD_IMAGE = 0;
    public static final int LEFT_IMAGE = 1;
    public static final int RIGHT_IMAGE = 2;
    public static final int ZERPLATZT_IMAGE = 3;
    public static final int NO_SHAKE = 6;
    public static final int SHAKE_RELAY = 2;
    public static final int FALL_SPEED = 15;
    public static final int ZERPLATZT_LIFE_TIME = 100;
    private Image[] images;
    private int x;
    private int y;
    private boolean falling;
    private int shake;
    private int shakeRelay;
    private int imgNo;
    private JavaMiner102V01Level map;
    private JavaMiner102V01Object[] objects;
    private boolean zerplatzt;
    private int fallDistance;
    private int zerplatztLifeTimer;
    
    public JavaMiner102V01Gold(final int x, final int y, final Image[] images, final JavaMiner102V01Level map, final JavaMiner102V01Object[] objects) {
        this.images = images;
        this.x = x;
        this.y = y;
        this.map = map;
        this.objects = objects;
        this.setXPos(32 + x * 32);
        this.setYPos(32 + y * 32);
        this.falling = false;
        this.zerplatzt = false;
        this.imgNo = 0;
        this.setImage(images[0]);
    }
    
    public void act() {
        if (!this.zerplatzt) {
            if (this.falling) {
                if (this.shake > 0 || this.shakeRelay > 0) {
                    if (this.shakeRelay > 0) {
                        --this.shakeRelay;
                        return;
                    }
                    --this.shake;
                    this.shakeRelay = 2;
                    ++this.imgNo;
                    if (this.imgNo > 3) {
                        this.imgNo = 0;
                    }
                    if (this.imgNo == 0 | this.imgNo == 2) {
                        this.setImage(this.images[0]);
                        return;
                    }
                    if (this.imgNo == 1) {
                        this.setImage(this.images[1]);
                        return;
                    }
                    this.setImage(this.images[2]);
                }
                else {
                    for (int i = 0; i < this.objects.length; ++i) {
                        if (this.objects[i] != null && this.touchsObject(this.objects[i])) {
                            switch (this.objects[i].goldHits()) {
                                default: {
                                    System.out.println("Fehler in JavaMiner102V01Shot::act()");
                                    break;
                                }
                                case 0:
                                case 2: {
                                    break;
                                }
                                case 1: {
                                    --JavaMiner102V01Spielfenster.activeSimpleHostiles;
                                    break;
                                }
                            }
                        }
                    }
                    this.y = (this.getYPos() - 32) / 32;
                    if ((this.getYPos() + 15 - 32) / 32 <= this.y) {
                        this.setYPos(this.getYPos() + 15);
                        return;
                    }
                    if (this.y + 2 < 13 && this.map.isWay(this.x, this.y + 2)) {
                        this.setYPos(this.getYPos() + 15);
                        ++this.fallDistance;
                        ++this.y;
                        return;
                    }
                    this.setYPos(32 + (this.y + 1) * 32);
                    ++this.y;
                    this.stopFalling();
                    if (this.y + 1 >= 13 || this.fallDistance > 1) {
                        this.zerplatze();
                    }
                }
            }
            else if (this.y + 1 < 13 && this.map.isWay(this.x, this.y + 1)) {
                this.beginFalling();
            }
        }
        else {
            for (int j = 0; j < this.objects.length; ++j) {
                if (this.objects[j] != null && this.touchsObject(this.objects[j]) && this.objects[j].goldCollected() == 0) {
                    this.dispose();
                }
            }
            if (--this.zerplatztLifeTimer <= 0) {
                this.dispose();
            }
        }
    }
    
    protected void beginFalling() {
        this.falling = true;
        this.fallDistance = 0;
        this.shake = 6;
        this.shakeRelay = 2;
    }
    
    protected void stopFalling() {
        this.falling = false;
    }
    
    protected void zerplatze() {
        this.zerplatzt = true;
        this.zerplatztLifeTimer = 100;
        this.setImage(this.images[3]);
    }
    
    public int shotHits() {
        return 2;
    }
    
    public int goldHits() {
        return this.shotHits();
    }
    
    public int goldCollected() {
        return 1;
    }
    
    public boolean stayAtLevelRestart() {
        return !this.falling && !this.zerplatzt && this.shakeRelay <= 0;
    }
    
    public int getObjectType() {
        if (!this.falling && !this.zerplatzt) {
            return 1;
        }
        return 3;
    }
}
