// 
// Decompiled by Procyon v0.5.30
// 

final class Animable_Sub4 extends Animable
{
    public final int anInt1571;
    public final int anInt1572;
    private double aDouble1574;
    private double aDouble1575;
    private double aDouble1576;
    private double aDouble1577;
    private double aDouble1578;
    private boolean aBoolean1579;
    private final int anInt1580;
    private final int anInt1581;
    private final int anInt1582;
    public final int anInt1583;
    public double aDouble1585;
    public double aDouble1586;
    public double aDouble1587;
    private final int anInt1588;
    private final int anInt1589;
    public final int anInt1590;
    private final SpotAnim aSpotAnim_1592;
    private int anInt1593;
    private int anInt1594;
    public int anInt1595;
    private int anInt1596;
    public final int anInt1597;
    
    public void method455(final int n, final int n2, final int n3, final int n4) {
        if (!this.aBoolean1579) {
            final double n5 = n4 - this.anInt1580;
            final double n6 = n2 - this.anInt1581;
            final double sqrt = Math.sqrt(n5 * n5 + n6 * n6);
            this.aDouble1585 = this.anInt1580 + n5 * this.anInt1589 / sqrt;
            this.aDouble1586 = this.anInt1581 + n6 * this.anInt1589 / sqrt;
            this.aDouble1587 = this.anInt1582;
        }
        final double n7 = this.anInt1572 + 1 - n;
        this.aDouble1574 = (n4 - this.aDouble1585) / n7;
        this.aDouble1575 = (n2 - this.aDouble1586) / n7;
        this.aDouble1576 = Math.sqrt(this.aDouble1574 * this.aDouble1574 + this.aDouble1575 * this.aDouble1575);
        if (!this.aBoolean1579) {
            this.aDouble1577 = -this.aDouble1576 * Math.tan(this.anInt1588 * 0.02454369);
        }
        this.aDouble1578 = 2.0 * (n3 - this.aDouble1587 - this.aDouble1577 * n7) / (n7 * n7);
    }
    
    public Model getRotatedModel() {
        final Model model = this.aSpotAnim_1592.getModel();
        if (model == null) {
            return null;
        }
        int n = -1;
        if (this.aSpotAnim_1592.aAnimation_407 != null) {
            n = this.aSpotAnim_1592.aAnimation_407.anIntArray353[this.anInt1593];
        }
        final Model model2 = new Model(true, Class36.method532(n), false, model);
        if (n != -1) {
            model2.method469();
            model2.method470(n);
            model2.anIntArrayArray1658 = null;
            model2.anIntArrayArray1657 = null;
        }
        if (this.aSpotAnim_1592.anInt410 != 128 || this.aSpotAnim_1592.anInt411 != 128) {
            model2.method478(this.aSpotAnim_1592.anInt410, this.aSpotAnim_1592.anInt410, this.aSpotAnim_1592.anInt411);
        }
        model2.method474(this.anInt1596);
        model2.method479(64 + this.aSpotAnim_1592.anInt413, 850 + this.aSpotAnim_1592.anInt414, -30, -50, -30, true);
        return model2;
    }
    
    public Animable_Sub4(final int anInt1588, final int anInt1589, final int anInt1590, final int anInt1591, final int anInt1592, final int anInt1593, final int anInt1594, final int anInt1595, final int anInt1596, final int anInt1597, final int n) {
        this.aBoolean1579 = false;
        this.aSpotAnim_1592 = SpotAnim.cache[n];
        this.anInt1597 = anInt1593;
        this.anInt1580 = anInt1596;
        this.anInt1581 = anInt1595;
        this.anInt1582 = anInt1594;
        this.anInt1571 = anInt1590;
        this.anInt1572 = anInt1591;
        this.anInt1588 = anInt1588;
        this.anInt1589 = anInt1592;
        this.anInt1590 = anInt1597;
        this.anInt1583 = anInt1589;
        this.aBoolean1579 = false;
    }
    
    public void method456(final int n) {
        this.aBoolean1579 = true;
        this.aDouble1585 += this.aDouble1574 * n;
        this.aDouble1586 += this.aDouble1575 * n;
        this.aDouble1587 += this.aDouble1577 * n + 0.5 * this.aDouble1578 * n * n;
        this.aDouble1577 += this.aDouble1578 * n;
        this.anInt1595 = ((int)(Math.atan2(this.aDouble1574, this.aDouble1575) * 325.949) + 1024 & 0x7FF);
        this.anInt1596 = ((int)(Math.atan2(this.aDouble1577, this.aDouble1576) * 325.949) & 0x7FF);
        if (this.aSpotAnim_1592.aAnimation_407 != null) {
            this.anInt1594 += n;
            while (this.anInt1594 > this.aSpotAnim_1592.aAnimation_407.method258(this.anInt1593)) {
                this.anInt1594 -= this.aSpotAnim_1592.aAnimation_407.method258(this.anInt1593) + 1;
                ++this.anInt1593;
                if (this.anInt1593 >= this.aSpotAnim_1592.aAnimation_407.anInt352) {
                    this.anInt1593 = 0;
                }
            }
        }
    }
}
