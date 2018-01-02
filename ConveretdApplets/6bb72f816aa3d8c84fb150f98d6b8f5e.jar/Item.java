// 
// Decompiled by Procyon v0.5.30
// 

final class Item extends Animable
{
    public int ID;
    public int x;
    public int y;
    public int anInt1559;
    
    public final Model getRotatedModel() {
        return ItemDef.forID(this.ID).method201(this.anInt1559);
    }
}
