// 
// Decompiled by Procyon v0.5.30
// 

public final class Player extends Entity
{
    public int forcedGraphics;
    public int forcedAnimation;
    public int privelage;
    private long aLong1697;
    public EntityDef desc;
    boolean aBoolean1699;
    final int[] anIntArray1700;
    public int team;
    private int anInt1702;
    public String name;
    static MRUNodes mruNodes;
    public int combatLevel;
    public int headIcon;
    public int skullIcon;
    public int hintIcon;
    public int anInt1707;
    int anInt1708;
    int anInt1709;
    boolean visible;
    int anInt1711;
    int anInt1712;
    int anInt1713;
    Model aModel_1714;
    private int anInt1715;
    public final int[] equipment;
    private long aLong1718;
    int anInt1719;
    int anInt1720;
    int anInt1721;
    int anInt1722;
    int skill;
    
    public Model getRotatedModel() {
        if (!this.visible) {
            return null;
        }
        Model method452 = this.method452();
        if (method452 == null) {
            return null;
        }
        super.height = method452.modelHeight;
        method452.aBoolean1659 = true;
        if (this.aBoolean1699) {
            return method452;
        }
        if (super.anInt1520 != -1 && super.anInt1521 != -1) {
            final SpotAnim spotAnim = SpotAnim.cache[super.anInt1520];
            final Model model = spotAnim.getModel();
            if (model != null) {
                final Model model2 = new Model(true, Class36.method532(super.anInt1521), false, model);
                model2.method475(0, -super.anInt1524, 0);
                model2.method469();
                model2.method470(spotAnim.aAnimation_407.anIntArray353[super.anInt1521]);
                model2.anIntArrayArray1658 = null;
                model2.anIntArrayArray1657 = null;
                if (spotAnim.anInt410 != 128 || spotAnim.anInt411 != 128) {
                    model2.method478(spotAnim.anInt410, spotAnim.anInt410, spotAnim.anInt411);
                }
                model2.method479(74 + spotAnim.anInt413, 1550 + spotAnim.anInt414, -50, -110, -50, true);
                method452 = new Model(new Model[] { method452, model2 });
            }
        }
        if (this.aModel_1714 != null) {
            if (client.loopCycle >= this.anInt1708) {
                this.aModel_1714 = null;
            }
            if (client.loopCycle >= this.anInt1707 && client.loopCycle < this.anInt1708) {
                final Model aModel_1714 = this.aModel_1714;
                aModel_1714.method475(this.anInt1711 - super.x, this.anInt1712 - this.anInt1709, this.anInt1713 - super.y);
                if (super.turnDirection == 512) {
                    aModel_1714.method473();
                    aModel_1714.method473();
                    aModel_1714.method473();
                }
                else if (super.turnDirection == 1024) {
                    aModel_1714.method473();
                    aModel_1714.method473();
                }
                else if (super.turnDirection == 1536) {
                    aModel_1714.method473();
                }
                method452 = new Model(new Model[] { method452, aModel_1714 });
                if (super.turnDirection == 512) {
                    aModel_1714.method473();
                }
                else if (super.turnDirection == 1024) {
                    aModel_1714.method473();
                    aModel_1714.method473();
                }
                else if (super.turnDirection == 1536) {
                    aModel_1714.method473();
                    aModel_1714.method473();
                    aModel_1714.method473();
                }
                aModel_1714.method475(super.x - this.anInt1711, this.anInt1709 - this.anInt1712, super.y - this.anInt1713);
            }
        }
        method452.aBoolean1659 = true;
        return method452;
    }
    
    public void updatePlayer(final Stream stream) {
        stream.currentOffset = 0;
        this.anInt1702 = stream.readUnsignedByte();
        this.headIcon = stream.readUnsignedByte();
        this.skullIcon = stream.readUnsignedByte();
        this.desc = null;
        this.team = 0;
        for (int i = 0; i < 12; ++i) {
            final int unsignedByte = stream.readUnsignedByte();
            if (unsignedByte == 0) {
                this.equipment[i] = 0;
            }
            else {
                this.equipment[i] = (unsignedByte << 8) + stream.readUnsignedByte();
                if (i == 0 && this.equipment[0] == 65535) {
                    this.desc = EntityDef.forID(stream.readUnsignedWord());
                    break;
                }
                if (this.equipment[i] >= 512 && this.equipment[i] - 512 < ItemDef.totalItems) {
                    final int team = ItemDef.forID(this.equipment[i] - 512).team;
                    if (team != 0) {
                        this.team = team;
                    }
                }
            }
        }
        for (int j = 0; j < 5; ++j) {
            int unsignedByte2 = stream.readUnsignedByte();
            if (unsignedByte2 < 0 || unsignedByte2 >= client.anIntArrayArray1003[j].length) {
                unsignedByte2 = 0;
            }
            this.anIntArray1700[j] = unsignedByte2;
        }
        super.anInt1511 = stream.readUnsignedWord();
        if (super.anInt1511 == 65535) {
            super.anInt1511 = -1;
        }
        super.anInt1512 = stream.readUnsignedWord();
        if (super.anInt1512 == 65535) {
            super.anInt1512 = -1;
        }
        super.anInt1554 = stream.readUnsignedWord();
        if (super.anInt1554 == 65535) {
            super.anInt1554 = -1;
        }
        super.anInt1555 = stream.readUnsignedWord();
        if (super.anInt1555 == 65535) {
            super.anInt1555 = -1;
        }
        super.anInt1556 = stream.readUnsignedWord();
        if (super.anInt1556 == 65535) {
            super.anInt1556 = -1;
        }
        super.anInt1557 = stream.readUnsignedWord();
        if (super.anInt1557 == 65535) {
            super.anInt1557 = -1;
        }
        super.anInt1505 = stream.readUnsignedWord();
        if (super.anInt1505 == 65535) {
            super.anInt1505 = -1;
        }
        this.name = TextClass.fixName(TextClass.nameForLong(stream.readQWord()));
        this.combatLevel = stream.readUnsignedByte();
        this.skill = stream.readUnsignedWord();
        this.visible = true;
        this.aLong1718 = 0L;
        for (int k = 0; k < 12; ++k) {
            this.aLong1718 <<= 4;
            if (this.equipment[k] >= 256) {
                this.aLong1718 += this.equipment[k] - 256;
            }
        }
        if (this.equipment[0] >= 256) {
            this.aLong1718 += this.equipment[0] - 256 >> 4;
        }
        if (this.equipment[1] >= 256) {
            this.aLong1718 += this.equipment[1] - 256 >> 8;
        }
        for (int l = 0; l < 5; ++l) {
            this.aLong1718 <<= 3;
            this.aLong1718 += this.anIntArray1700[l];
        }
        this.aLong1718 <<= 1;
        this.aLong1718 += this.anInt1702;
    }
    
    public Model method452() {
        if (this.desc != null) {
            int n = -1;
            if (super.anim >= 0 && super.anInt1529 == 0) {
                n = Animation.anims[super.anim].anIntArray353[super.anInt1527];
            }
            else if (super.anInt1517 >= 0) {
                n = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
            }
            return this.desc.method164(-1, n, null);
        }
        long aLong1718 = this.aLong1718;
        int n2 = -1;
        int n3 = -1;
        int anInt360 = -1;
        int anInt361 = -1;
        if (super.anim >= 0 && super.anInt1529 == 0) {
            final Animation animation = Animation.anims[super.anim];
            n2 = animation.anIntArray353[super.anInt1527];
            if (super.anInt1517 >= 0 && super.anInt1517 != super.anInt1511) {
                n3 = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
            }
            if (animation.anInt360 >= 0) {
                anInt360 = animation.anInt360;
                aLong1718 += anInt360 - this.equipment[5] << 40;
            }
            if (animation.anInt361 >= 0) {
                anInt361 = animation.anInt361;
                aLong1718 += anInt361 - this.equipment[3] << 48;
            }
        }
        else if (super.anInt1517 >= 0) {
            n2 = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
        }
        Model model = (Model)Player.mruNodes.insertFromCache(aLong1718);
        if (model == null) {
            boolean b = false;
            for (int i = 0; i < 12; ++i) {
                int n4 = this.equipment[i];
                if (anInt361 >= 0 && i == 3) {
                    n4 = anInt361;
                }
                if (anInt360 >= 0 && i == 5) {
                    n4 = anInt360;
                }
                if (n4 >= 256 && n4 < 512 && !IDK.cache[n4 - 256].method537()) {
                    b = true;
                }
                if (n4 >= 512 && !ItemDef.forID(n4 - 512).method195(this.anInt1702)) {
                    b = true;
                }
            }
            if (b) {
                if (this.aLong1697 != -1L) {
                    model = (Model)Player.mruNodes.insertFromCache(this.aLong1697);
                }
                if (model == null) {
                    return null;
                }
            }
        }
        if (model == null) {
            final Model[] array = new Model[12];
            int n5 = 0;
            for (int j = 0; j < 12; ++j) {
                int n6 = this.equipment[j];
                if (anInt361 >= 0 && j == 3) {
                    n6 = anInt361;
                }
                if (anInt360 >= 0 && j == 5) {
                    n6 = anInt360;
                }
                if (n6 >= 256 && n6 < 512) {
                    final Model method538 = IDK.cache[n6 - 256].method538();
                    if (method538 != null) {
                        array[n5++] = method538;
                    }
                }
                if (n6 >= 512) {
                    final Model method539 = ItemDef.forID(n6 - 512).method196(this.anInt1702);
                    if (method539 != null) {
                        array[n5++] = method539;
                    }
                }
            }
            model = new Model(n5, array);
            for (int k = 0; k < 5; ++k) {
                if (this.anIntArray1700[k] != 0) {
                    model.method476(client.anIntArrayArray1003[k][0], client.anIntArrayArray1003[k][this.anIntArray1700[k]]);
                    if (k == 1) {
                        model.method476(client.anIntArray1204[0], client.anIntArray1204[this.anIntArray1700[k]]);
                    }
                }
            }
            model.method469();
            System.setProperty("sun.java2d.d3d", "true");
            System.setProperty("sun.java2d.d3dtexbpp", "32");
            System.setProperty("sun.java2d.ddforcevram", "true");
            System.setProperty("sun.java2d.translaccel", "true");
            System.setProperty("sun.java2d.pmoffscreen", "true");
            System.setProperty("sun.java2d.opengl", "true");
            model.method479(84, 1000, -90, -580, -90, true);
            model.method478(132, 132, 132);
            Player.mruNodes.removeFromCache(model, aLong1718);
            this.aLong1697 = aLong1718;
        }
        if (this.aBoolean1699) {
            return model;
        }
        final Model aModel_1621 = Model.aModel_1621;
        aModel_1621.method464(model, Class36.method532(n2) & Class36.method532(n3));
        if (n2 != -1 && n3 != -1) {
            aModel_1621.method471(Animation.anims[super.anim].anIntArray357, n3, n2);
        }
        else if (n2 != -1) {
            aModel_1621.method470(n2);
        }
        aModel_1621.method466();
        aModel_1621.anIntArrayArray1658 = null;
        aModel_1621.anIntArrayArray1657 = null;
        return aModel_1621;
    }
    
    @Override
    public boolean isVisible() {
        return this.visible;
    }
    
    public Model method453() {
        if (!this.visible) {
            return null;
        }
        if (this.desc != null) {
            return this.desc.method160();
        }
        boolean b = false;
        for (int i = 0; i < 12; ++i) {
            final int n = this.equipment[i];
            if (n >= 256 && n < 512 && !IDK.cache[n - 256].method539()) {
                b = true;
            }
            if (n >= 512 && !ItemDef.forID(n - 512).method192(this.anInt1702)) {
                b = true;
            }
        }
        if (b) {
            return null;
        }
        final Model[] array = new Model[12];
        int n2 = 0;
        for (int j = 0; j < 12; ++j) {
            final int n3 = this.equipment[j];
            if (n3 >= 256 && n3 < 512) {
                final Model method540 = IDK.cache[n3 - 256].method540();
                if (method540 != null) {
                    array[n2++] = method540;
                }
            }
            if (n3 >= 512) {
                final Model method541 = ItemDef.forID(n3 - 512).method194(this.anInt1702);
                if (method541 != null) {
                    array[n2++] = method541;
                }
            }
        }
        final Model model = new Model(n2, array);
        for (int k = 0; k < 5; ++k) {
            if (this.anIntArray1700[k] != 0) {
                model.method476(client.anIntArrayArray1003[k][0], client.anIntArrayArray1003[k][this.anIntArray1700[k]]);
                if (k == 1) {
                    model.method476(client.anIntArray1204[0], client.anIntArray1204[this.anIntArray1700[k]]);
                }
            }
        }
        return model;
    }
    
    Player() {
        this.aLong1697 = -1L;
        this.aBoolean1699 = false;
        this.anIntArray1700 = new int[5];
        this.visible = false;
        this.anInt1715 = 9;
        this.equipment = new int[12];
    }
    
    static {
        Player.mruNodes = new MRUNodes(260);
    }
}
