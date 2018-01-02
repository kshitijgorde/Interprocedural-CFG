// 
// Decompiled by Procyon v0.5.30
// 

public final class NPC extends Entity
{
    public EntityDef desc;
    
    private Model method450() {
        if (super.anim >= 0 && super.anInt1529 == 0) {
            final int n = Animation.anims[super.anim].anIntArray353[super.anInt1527];
            int n2 = -1;
            if (super.anInt1517 >= 0 && super.anInt1517 != super.anInt1511) {
                n2 = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
            }
            return this.desc.method164(n2, n, Animation.anims[super.anim].anIntArray357);
        }
        int n3 = -1;
        if (super.anInt1517 >= 0) {
            n3 = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
        }
        return this.desc.method164(-1, n3, null);
    }
    
    public Model getRotatedModel() {
        if (this.desc == null) {
            return null;
        }
        Model method450 = this.method450();
        if (method450 == null) {
            return null;
        }
        super.height = method450.modelHeight;
        if (super.anInt1520 != -1 && super.anInt1521 != -1) {
            final SpotAnim spotAnim = SpotAnim.cache[super.anInt1520];
            final Model model = spotAnim.getModel();
            if (model != null) {
                final int n = spotAnim.aAnimation_407.anIntArray353[super.anInt1521];
                final Model model2 = new Model(true, Class36.method532(n), false, model);
                model2.method475(0, -super.anInt1524, 0);
                model2.method469();
                model2.method470(n);
                model2.anIntArrayArray1658 = null;
                model2.anIntArrayArray1657 = null;
                if (spotAnim.anInt410 != 128 || spotAnim.anInt411 != 128) {
                    model2.method478(spotAnim.anInt410, spotAnim.anInt410, spotAnim.anInt411);
                }
                model2.method479(64 + spotAnim.anInt413, 850 + spotAnim.anInt414, -30, -50, -30, true);
                method450 = new Model(new Model[] { method450, model2 });
            }
        }
        if (this.desc.aByte68 == 1) {
            method450.aBoolean1659 = true;
        }
        return method450;
    }
    
    @Override
    public boolean isVisible() {
        return this.desc != null;
    }
}
