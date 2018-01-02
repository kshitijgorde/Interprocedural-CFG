import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class JavaMiner102V01MinerShot extends JavaMiner102V01Object
{
    public static final int SPEED = 16;
    public static final int BILD_RECHTS = 0;
    public static final int BILD_LINKS = 1;
    public static final int BILD_OBEN = 2;
    public static final int BILD_UNTEN = 3;
    public static final int HIT_KEIN_GEGNER = 0;
    public static final int HIT_SIMPLE_HOSTILE = 1;
    public static final int HIT_GOLD = 2;
    private JavaMiner102V01Level map;
    private JavaMiner102V01Miner digger;
    private JavaMiner102V01Object[] objects;
    
    public JavaMiner102V01MinerShot(final Image[] array, final int ausrichtung, final int xPos, final int yPos, final JavaMiner102V01Level map, final JavaMiner102V01Object[] objects, final JavaMiner102V01Miner digger) {
        this.digger = digger;
        this.objects = objects;
        int n = 0;
        switch (ausrichtung) {
            case 1: {
                n = 0;
                break;
            }
            case 2: {
                n = 1;
                break;
            }
            case 4: {
                n = 2;
                break;
            }
            case 8: {
                n = 3;
                break;
            }
            default: {
                System.out.println("Fehler in JavaMiner102VXXMinerShot::Constructor()");
                break;
            }
        }
        this.setImage(array[n]);
        this.setAusrichtung(ausrichtung);
        this.setXPos(xPos);
        this.setYPos(yPos);
        this.map = map;
    }
    
    public void act() {
        switch (this.getAusrichtung()) {
            case 4: {
                this.setYPos(this.getYPos() - 16);
                break;
            }
            case 8: {
                this.setYPos(this.getYPos() + 16);
                break;
            }
            case 2: {
                this.setXPos(this.getXPos() - 16);
                break;
            }
            case 1: {
                this.setXPos(this.getXPos() + 16);
                break;
            }
        }
        final int xKoord = this.getXKoord();
        final int yKoord = this.getYKoord();
        if (xKoord < 0 || xKoord >= 14 || yKoord < 0 || yKoord >= 13 || !true || !this.map.isWay(this.getXKoord(), this.getYKoord())) {
            this.dispose();
        }
        for (int i = 0; i < this.objects.length; ++i) {
            if (this.objects[i] != null && this.touchsObject(this.objects[i])) {
                switch (this.objects[i].shotHits()) {
                    default: {
                        System.out.println("Fehler in JavaMiner102V01Shot::act()");
                        break;
                    }
                    case 0: {
                        break;
                    }
                    case 1: {
                        this.dispose();
                        this.digger.hostileHit();
                        --JavaMiner102V01Spielfenster.activeSimpleHostiles;
                        break;
                    }
                    case 2: {
                        this.dispose();
                        break;
                    }
                }
            }
        }
    }
    
    public int shotHits() {
        return 0;
    }
    
    public int goldHits() {
        return this.shotHits();
    }
    
    public int goldCollected() {
        return 1;
    }
    
    public boolean stayAtLevelRestart() {
        return false;
    }
    
    public int getObjectType() {
        return 3;
    }
}
