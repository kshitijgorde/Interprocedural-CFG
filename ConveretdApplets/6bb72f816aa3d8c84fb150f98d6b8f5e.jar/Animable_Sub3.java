// 
// Decompiled by Procyon v0.5.30
// 

final class Animable_Sub3 extends Animable
{
    public final int anInt1560;
    public final int anInt1561;
    public final int anInt1562;
    public final int anInt1563;
    public final int anInt1564;
    public boolean aBoolean1567;
    private final SpotAnim aSpotAnim_1568;
    private int anInt1569;
    private int anInt1570;
    
    public Animable_Sub3(final int anInt1560, final int n, final int n2, final int n3, final int anInt1561, final int anInt1562, final int anInt1563) {
        this.aBoolean1567 = false;
        this.aSpotAnim_1568 = SpotAnim.cache[n3];
        this.anInt1560 = anInt1560;
        this.anInt1561 = anInt1563;
        this.anInt1562 = anInt1562;
        this.anInt1563 = anInt1561;
        this.anInt1564 = n + n2;
        this.aBoolean1567 = false;
    }
    
    public Model getRotatedModel() {
        final Model model = this.aSpotAnim_1568.getModel();
        if (model == null) {
            return null;
        }
        final int n = this.aSpotAnim_1568.aAnimation_407.anIntArray353[this.anInt1569];
        final Model model2 = new Model(true, Class36.method532(n), false, model);
        if (!this.aBoolean1567) {
            model2.method469();
            model2.method470(n);
            model2.anIntArrayArray1658 = null;
            model2.anIntArrayArray1657 = null;
        }
        if (this.aSpotAnim_1568.anInt410 != 128 || this.aSpotAnim_1568.anInt411 != 128) {
            model2.method478(this.aSpotAnim_1568.anInt410, this.aSpotAnim_1568.anInt410, this.aSpotAnim_1568.anInt411);
        }
        if (this.aSpotAnim_1568.anInt412 != 0) {
            if (this.aSpotAnim_1568.anInt412 == 90) {
                model2.method473();
            }
            if (this.aSpotAnim_1568.anInt412 == 180) {
                model2.method473();
                model2.method473();
            }
            if (this.aSpotAnim_1568.anInt412 == 270) {
                model2.method473();
                model2.method473();
                model2.method473();
            }
        }
        model2.method479(64 + this.aSpotAnim_1568.anInt413, 850 + this.aSpotAnim_1568.anInt414, -30, -50, -30, true);
        return model2;
    }
    
    public void method454(final int n) {
        this.anInt1570 += n;
        while (this.anInt1570 > this.aSpotAnim_1568.aAnimation_407.method258(this.anInt1569)) {
            this.anInt1570 -= this.aSpotAnim_1568.aAnimation_407.method258(this.anInt1569) + 1;
            ++this.anInt1569;
            if (this.anInt1569 >= this.aSpotAnim_1568.aAnimation_407.anInt352 && (this.anInt1569 < 0 || this.anInt1569 >= this.aSpotAnim_1568.aAnimation_407.anInt352)) {
                this.anInt1569 = 0;
                this.aBoolean1567 = true;
            }
        }
    }
}
