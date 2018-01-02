import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class Entity extends Animable
{
    public int entScreenX;
    public int entScreenY;
    public final int index = -1;
    public final int[] smallX;
    public final int[] smallY;
    public int interactingEntity;
    int anInt1503;
    int anInt1504;
    int anInt1505;
    public String textSpoken;
    public int height;
    public int turnDirection;
    int anInt1511;
    int anInt1512;
    int anInt1513;
    final int[] hitArray;
    final int[] hitMarkTypes;
    final int[] hitsLoopCycle;
    int anInt1517;
    int anInt1518;
    int anInt1519;
    int anInt1520;
    int anInt1521;
    int anInt1522;
    int anInt1523;
    int anInt1524;
    int smallXYIndex;
    public int anim;
    int anInt1527;
    int anInt1528;
    int anInt1529;
    int anInt1530;
    int anInt1531;
    public int loopCycleStatus;
    public int currentHealth;
    public int maxHealth;
    int textCycle;
    int anInt1537;
    int anInt1538;
    int anInt1539;
    int anInt1540;
    boolean aBoolean1541;
    int anInt1542;
    int anInt1543;
    int anInt1544;
    int anInt1545;
    int anInt1546;
    int anInt1547;
    int anInt1548;
    int anInt1549;
    public int x;
    public int y;
    int anInt1552;
    final boolean[] aBooleanArray1553;
    int anInt1554;
    int anInt1555;
    int anInt1556;
    int anInt1557;
    
    public final void setPos(final int n, final int n2, final boolean b) {
        if (this.anim != -1 && Animation.anims[this.anim].anInt364 == 1) {
            this.anim = -1;
        }
        if (!b) {
            final int n3 = n - this.smallX[0];
            final int n4 = n2 - this.smallY[0];
            if (n3 >= -8 && n3 <= 8 && n4 >= -8 && n4 <= 8) {
                if (this.smallXYIndex < 9) {
                    ++this.smallXYIndex;
                }
                for (int i = this.smallXYIndex; i > 0; --i) {
                    this.smallX[i] = this.smallX[i - 1];
                    this.smallY[i] = this.smallY[i - 1];
                    this.aBooleanArray1553[i] = this.aBooleanArray1553[i - 1];
                }
                this.smallX[0] = n;
                this.smallY[0] = n2;
                this.aBooleanArray1553[0] = false;
                return;
            }
        }
        this.smallXYIndex = 0;
        this.anInt1542 = 0;
        this.anInt1503 = 0;
        this.smallX[0] = n;
        this.smallY[0] = n2;
        this.x = this.smallX[0] * 128 + this.anInt1540 * 64;
        this.y = this.smallY[0] * 128 + this.anInt1540 * 64;
    }
    
    public final void method446() {
        this.smallXYIndex = 0;
        this.anInt1542 = 0;
    }
    
    public final void updateHitData(final int n, final int n2, final int n3) {
        for (int i = 0; i < 4; ++i) {
            if (this.hitsLoopCycle[i] <= n3) {
                this.hitArray[i] = n2 * ((client.newHits && n2 > 0) ? 10 : 1);
                if (client.newHits && n2 > 0) {
                    final int[] hitArray = this.hitArray;
                    final int n4 = i;
                    hitArray[n4] += new Random().nextInt(9);
                }
                this.hitMarkTypes[i] = n;
                this.hitsLoopCycle[i] = n3 + 70;
                return;
            }
        }
    }
    
    public final void moveInDir(final boolean b, final int n) {
        int n2 = this.smallX[0];
        int n3 = this.smallY[0];
        if (n == 0) {
            --n2;
            ++n3;
        }
        if (n == 1) {
            ++n3;
        }
        if (n == 2) {
            ++n2;
            ++n3;
        }
        if (n == 3) {
            --n2;
        }
        if (n == 4) {
            ++n2;
        }
        if (n == 5) {
            --n2;
            --n3;
        }
        if (n == 6) {
            --n3;
        }
        if (n == 7) {
            ++n2;
            --n3;
        }
        if (this.anim != -1 && Animation.anims[this.anim].anInt364 == 1) {
            this.anim = -1;
        }
        if (this.smallXYIndex < 9) {
            ++this.smallXYIndex;
        }
        for (int i = this.smallXYIndex; i > 0; --i) {
            this.smallX[i] = this.smallX[i - 1];
            this.smallY[i] = this.smallY[i - 1];
            this.aBooleanArray1553[i] = this.aBooleanArray1553[i - 1];
        }
        this.smallX[0] = n2;
        this.smallY[0] = n3;
        this.aBooleanArray1553[0] = b;
    }
    
    public boolean isVisible() {
        return false;
    }
    
    Entity() {
        this.smallX = new int[10];
        this.smallY = new int[10];
        this.interactingEntity = -1;
        this.anInt1504 = 32;
        this.anInt1505 = -1;
        this.height = 200;
        this.anInt1511 = -1;
        this.anInt1512 = -1;
        this.hitArray = new int[4];
        this.hitMarkTypes = new int[4];
        this.hitsLoopCycle = new int[4];
        this.anInt1517 = -1;
        this.anInt1520 = -1;
        this.anim = -1;
        this.loopCycleStatus = -1000;
        this.textCycle = 100;
        this.anInt1540 = 1;
        this.aBoolean1541 = false;
        this.aBooleanArray1553 = new boolean[10];
        this.anInt1554 = -1;
        this.anInt1555 = -1;
        this.anInt1556 = -1;
        this.anInt1557 = -1;
    }
}
