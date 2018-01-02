import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

public final class EntityDef
{
    public int anInt55;
    private static int anInt56;
    private int anInt57;
    public int anInt58;
    private int anInt59;
    private static Stream stream;
    public int combatLevel;
    private final int anInt64;
    public String name;
    public String[] actions;
    public int anInt67;
    public byte aByte68;
    private int[] anIntArray70;
    private static int[] streamIndices;
    private int[] anIntArray73;
    public int anInt75;
    private int[] anIntArray76;
    public int anInt77;
    public long type;
    public int anInt79;
    private static EntityDef[] cache;
    public static client clientInstance;
    public int anInt83;
    public boolean aBoolean84;
    private int anInt85;
    private int anInt86;
    public boolean aBoolean87;
    public int[] childrenIDs;
    public byte[] description;
    private int anInt91;
    private int anInt92;
    public boolean aBoolean93;
    private int[] anIntArray94;
    public static MRUNodes mruNodes;
    
    public static EntityDef forID(final int n) {
        for (int i = 0; i < 20; ++i) {
            if (EntityDef.cache[i].type == n) {
                return EntityDef.cache[i];
            }
        }
        EntityDef.anInt56 = (EntityDef.anInt56 + 1) % 20;
        final EntityDef[] cache = EntityDef.cache;
        final int anInt56 = EntityDef.anInt56;
        final EntityDef entityDef = new EntityDef();
        cache[anInt56] = entityDef;
        final EntityDef entityDef2 = entityDef;
        EntityDef.stream.currentOffset = EntityDef.streamIndices[n];
        entityDef2.type = n;
        entityDef2.readValues(EntityDef.stream);
        if (n == 6203) {
            entityDef2.anInt77 = 13597;
            entityDef2.anInt67 = 13598;
            entityDef2.anInt58 = 13598;
            entityDef2.anInt83 = 13598;
        }
        if (n == 5248) {
            (entityDef2.actions = new String[5])[1] = "Attack";
            (entityDef2.anIntArray94 = new int[8])[0] = 62738;
            entityDef2.anIntArray94[1] = 62746;
            entityDef2.anIntArray94[2] = 62743;
            entityDef2.anIntArray94[3] = 27738;
            entityDef2.anIntArray94[4] = 20130;
            entityDef2.anIntArray94[5] = 26422;
            entityDef2.anIntArray94[6] = 35087;
            entityDef2.anIntArray94[7] = 35095;
            entityDef2.anInt77 = 808;
            entityDef2.anInt67 = 819;
            entityDef2.name = "Fumus";
            entityDef2.combatLevel = 1058;
            entityDef2.description = "A forgotten warrior who has mastered the art of ancient combat".getBytes();
        }
        if (n == 5249) {
            (entityDef2.actions = new String[5])[1] = "Attack";
            entityDef2.anInt91 = 129;
            entityDef2.anInt86 = 129;
            (entityDef2.anIntArray94 = new int[7])[0] = 62755;
            entityDef2.anIntArray94[1] = 62758;
            entityDef2.anIntArray94[2] = 62764;
            entityDef2.anIntArray94[3] = 62751;
            entityDef2.anIntArray94[4] = 62752;
            entityDef2.anIntArray94[5] = 26422;
            entityDef2.anIntArray94[6] = 6232;
            entityDef2.anInt77 = 813;
            entityDef2.anInt67 = 1205;
            entityDef2.name = "Umbra";
            entityDef2.combatLevel = 1085;
            entityDef2.description = "A forgotten mage who has mastered the art of ancient curse magic".getBytes();
        }
        if (n == 5250) {
            (entityDef2.actions = new String[5])[1] = "Attack";
            (entityDef2.anIntArray94 = new int[6])[0] = 62734;
            entityDef2.anIntArray94[1] = 62735;
            entityDef2.anIntArray94[2] = 62737;
            entityDef2.anIntArray94[3] = 62741;
            entityDef2.anIntArray94[4] = 62744;
            entityDef2.anIntArray94[5] = 62750;
            entityDef2.anInt77 = 808;
            entityDef2.anInt67 = 819;
            entityDef2.name = "Glacies";
            entityDef2.combatLevel = 1021;
            entityDef2.description = "A forgotten ranger that has mastered the art of ancient bowmanship".getBytes();
        }
        if (n == 1225) {
            entityDef2.name = "Bryan the Vampire";
            entityDef2.combatLevel = 341;
        }
        if (n == 3109) {
            (entityDef2.actions = new String[5])[1] = "Attack";
            entityDef2.name = "I don't remember my name";
            entityDef2.combatLevel = 389;
        }
        if (n == 4477) {
            (entityDef2.actions = new String[5])[1] = "Attack";
            entityDef2.name = "Barebones";
            entityDef2.combatLevel = 941;
        }
        if (n == 879) {
            entityDef2.name = "Pim the Delrith";
            entityDef2.combatLevel = 341;
        }
        if (n == 2134) {
            entityDef2.name = "Terrance the Rage";
            entityDef2.combatLevel = 119;
        }
        if (n == 2579) {
            entityDef2.name = "Mandrith";
            entityDef2.aByte68 = 1;
            entityDef2.anInt77 = 2715;
            entityDef2.anInt67 = 2715;
            (entityDef2.actions = new String[5])[0] = "Sell Artifacts";
            entityDef2.combatLevel = 0;
            entityDef2.anInt91 = 130;
            entityDef2.anInt86 = 130;
            (entityDef2.anIntArray94 = new int[1])[0] = 30182;
        }
        if (n == 2577) {
            entityDef2.name = "Harry";
            entityDef2.aByte68 = 1;
            entityDef2.combatLevel = 0;
            entityDef2.anInt91 = 130;
            entityDef2.anInt67 = 819;
            entityDef2.anInt86 = 180;
            entityDef2.anInt77 = 2715;
            entityDef2.anInt67 = 2715;
            (entityDef2.anIntArray94 = new int[1])[0] = 29909;
        }
        if (n == 2578) {
            entityDef2.name = "Nastroth";
            entityDef2.aByte68 = 1;
            entityDef2.combatLevel = 0;
            entityDef2.anInt91 = 130;
            entityDef2.anInt86 = 130;
            entityDef2.anInt77 = 2715;
            entityDef2.anInt67 = 2715;
            (entityDef2.actions = new String[5])[0] = "Open SSP Shop";
            (entityDef2.anIntArray94 = new int[1])[0] = 40058;
        }
        if (n == 2580) {
            (entityDef2.actions = new String[5])[1] = "Attack";
            entityDef2.name = "Lucien";
            entityDef2.combatLevel = 987;
            entityDef2.anInt67 = 10764;
            entityDef2.anInt77 = 10763;
            (entityDef2.anIntArray94 = new int[1])[0] = 44701;
        }
        if (n == 5247) {
            (entityDef2.actions = new String[5])[1] = "Attack";
            (entityDef2.anIntArray94 = new int[1])[0] = 40955;
            entityDef2.anInt77 = 10056;
            entityDef2.anInt67 = 10055;
            entityDef2.name = "Corporeal Beast";
            entityDef2.combatLevel = 785;
            entityDef2.description = "A vision of supernatural horror.".getBytes();
        }
        if (n == 3592) {
            (entityDef2.actions = new String[5])[2] = "Special Move";
            (entityDef2.anIntArray94 = new int[1])[0] = 31101;
            entityDef2.anInt77 = 6374;
            entityDef2.anInt67 = 6373;
            entityDef2.name = "Unicorn Stallion";
            entityDef2.combatLevel = 70;
            entityDef2.description = "A summoning monster.".getBytes();
        }
        if (n == 1282) {
            entityDef2.name = "Summoning Master";
            entityDef2.actions[2] = "Refill-BoB";
            entityDef2.actions[3] = "Open Pouch Shop";
        }
        if (n == 243) {
            entityDef2.name = "Dungeoneering Master";
            entityDef2.actions[2] = "Start Dungeoneering";
            entityDef2.actions[3] = "Open Token Shop";
        }
        if (n == 945) {
            entityDef2.name = "Devilishpkz Guide";
        }
        if (n == 3591) {
            (entityDef2.actions = new String[5])[2] = "Special Move";
            (entityDef2.anIntArray94 = new int[1])[0] = 30469;
            entityDef2.anInt77 = 8186;
            entityDef2.anInt67 = 8189;
            entityDef2.name = "Steel Titan";
            entityDef2.combatLevel = 175;
            entityDef2.description = "A summoning monster.".getBytes();
        }
        if (n == 3587) {
            (entityDef2.actions = new String[5])[1] = "Take BoB";
            entityDef2.actions[2] = "Store";
            (entityDef2.anIntArray94 = new int[1])[0] = 30460;
            entityDef2.anInt77 = 8284;
            entityDef2.anInt67 = 8281;
            entityDef2.name = "War tortoise";
            entityDef2.anInt91 = 129;
            entityDef2.anInt86 = 129;
            entityDef2.combatLevel = 86;
            entityDef2.description = "A summoning monster.".getBytes();
        }
        if (n == 3588) {
            (entityDef2.actions = new String[5])[2] = "Teleport";
            (entityDef2.anIntArray94 = new int[1])[0] = 31132;
            entityDef2.anInt77 = 5225;
            entityDef2.anInt67 = 5226;
            entityDef2.name = "Spirit Ghraak";
            entityDef2.anInt91 = 129;
            entityDef2.anInt86 = 129;
            entityDef2.combatLevel = 51;
            entityDef2.description = "A summoning monster.".getBytes();
        }
        if (n == 3593) {
            (entityDef2.actions = new String[5])[2] = "Special move";
            entityDef2.anInt91 = 129;
            entityDef2.anInt86 = 129;
            (entityDef2.anIntArray94 = new int[1])[0] = 30475;
            entityDef2.anInt77 = 8301;
            entityDef2.anInt67 = 8302;
            entityDef2.name = "Wolpertinger";
            entityDef2.combatLevel = 210;
            entityDef2.description = "A summoning monster.".getBytes();
        }
        if (n == 3590) {
            (entityDef2.actions = new String[5])[2] = "Take-Supplies";
            (entityDef2.anIntArray94 = new int[1])[0] = 30460;
            entityDef2.anInt77 = 8284;
            entityDef2.anInt67 = 8281;
            entityDef2.name = "War tortoise";
            entityDef2.anInt91 = 129;
            entityDef2.anInt86 = 129;
            entityDef2.combatLevel = 86;
            entityDef2.description = "A summoning monster.".getBytes();
        }
        if (n == 3586) {
            (entityDef2.actions = new String[5])[1] = "Take BoB";
            entityDef2.actions[2] = "Store";
            (entityDef2.anIntArray94 = new int[1])[0] = 31096;
            entityDef2.anInt77 = 1008;
            entityDef2.anInt67 = 1007;
            entityDef2.anInt91 = 129;
            entityDef2.anInt86 = 129;
            entityDef2.name = "Spirit terrorbird";
            entityDef2.combatLevel = 62;
            entityDef2.description = "A Bank That Follows You.".getBytes();
        }
        if (n == 3596) {
            (entityDef2.actions = new String[5])[2] = "Take-Supplies";
            (entityDef2.anIntArray94 = new int[1])[0] = 31096;
            entityDef2.anInt77 = 1008;
            entityDef2.anInt67 = 1007;
            entityDef2.anInt91 = 129;
            entityDef2.anInt86 = 129;
            entityDef2.name = "Spirit terrorbird";
            entityDef2.combatLevel = 62;
            entityDef2.description = "A Bank That Follows You.".getBytes();
        }
        if (n == 3594) {
            (entityDef2.actions = new String[5])[3] = "Take-Supplies";
            (entityDef2.anIntArray94 = new int[1])[0] = 23892;
            entityDef2.anInt77 = 5785;
            entityDef2.anInt67 = 5781;
            entityDef2.name = "Pack-yak";
            entityDef2.combatLevel = 175;
            entityDef2.description = "Summoning Bank.".getBytes();
        }
        if (n == 3595) {
            (entityDef2.actions = new String[5])[1] = "Take BoB";
            entityDef2.actions[2] = "Store";
            (entityDef2.anIntArray94 = new int[1])[0] = 23892;
            entityDef2.anInt77 = 5785;
            entityDef2.anInt67 = 5781;
            entityDef2.name = "Pack-yak";
            entityDef2.combatLevel = 175;
            entityDef2.description = "Summoning Bank.".getBytes();
        }
        if (n == 1155) {
            (entityDef2.actions = new String[5])[1] = "Attack";
            entityDef2.name = "Tormented Demon";
            entityDef2.combatLevel = 450;
            entityDef2.anIntArray94 = new int[1];
            entityDef2.anInt77 = 10921;
            entityDef2.anInt67 = 10920;
            entityDef2.anIntArray94[0] = 44733;
            entityDef2.description = "Dangerous shit bro...".getBytes();
        }
        return entityDef2;
    }
    
    public static void dumpEntitydef(final int n) throws IOException {
        final FileWriter fileWriter = new FileWriter(new File("npc info.txt"));
        for (int i = 0; i < n; ++i) {
            fileWriter.write(i + "\t" + forID(i).name + "\r\n");
        }
        fileWriter.close();
    }
    
    public Model method160() {
        if (this.childrenIDs != null) {
            final EntityDef method161 = this.method161();
            if (method161 == null) {
                return null;
            }
            return method161.method160();
        }
        else {
            if (this.anIntArray73 == null) {
                return null;
            }
            boolean b = false;
            for (int i = 0; i < this.anIntArray73.length; ++i) {
                if (!Model.method463(this.anIntArray73[i])) {
                    b = true;
                }
            }
            if (b) {
                return null;
            }
            final Model[] array = new Model[this.anIntArray73.length];
            for (int j = 0; j < this.anIntArray73.length; ++j) {
                array[j] = Model.method462(this.anIntArray73[j]);
            }
            Model model;
            if (array.length == 1) {
                model = array[0];
            }
            else {
                model = new Model(array.length, array);
            }
            if (this.anIntArray76 != null) {
                for (int k = 0; k < this.anIntArray76.length; ++k) {
                    model.method476(this.anIntArray76[k], this.anIntArray70[k]);
                }
            }
            return model;
        }
    }
    
    public EntityDef method161() {
        int n = -1;
        if (this.anInt57 != -1) {
            try {
                final VarBit varBit = VarBit.cache[this.anInt57];
                final int anInt648 = varBit.anInt648;
                final int anInt649 = varBit.anInt649;
                n = (EntityDef.clientInstance.variousSettings[anInt648] >> anInt649 & client.anIntArray1232[varBit.anInt650 - anInt649]);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (this.anInt59 != -1) {
            n = EntityDef.clientInstance.variousSettings[this.anInt59];
        }
        if (n < 0 || n >= this.childrenIDs.length || this.childrenIDs[n] == -1) {
            return null;
        }
        return forID(this.childrenIDs[n]);
    }
    
    public static void unpackConfig(final StreamLoader streamLoader) {
        EntityDef.stream = new Stream(streamLoader.getDataForName("npc.dat"));
        final Stream stream = new Stream(streamLoader.getDataForName("npc.idx"));
        final int unsignedWord = stream.readUnsignedWord();
        EntityDef.streamIndices = new int[unsignedWord];
        int n = 2;
        for (int i = 0; i < unsignedWord; ++i) {
            EntityDef.streamIndices[i] = n;
            n += stream.readUnsignedWord();
        }
        EntityDef.cache = new EntityDef[20];
        for (int j = 0; j < 20; ++j) {
            EntityDef.cache[j] = new EntityDef();
        }
    }
    
    public static void nullLoader() {
        EntityDef.mruNodes = null;
        EntityDef.streamIndices = null;
        EntityDef.cache = null;
        EntityDef.stream = null;
    }
    
    public Model method164(final int n, final int n2, final int[] array) {
        if (this.childrenIDs == null) {
            Model model = (Model)EntityDef.mruNodes.insertFromCache(this.type);
            if (model == null) {
                boolean b = false;
                for (int i = 0; i < this.anIntArray94.length; ++i) {
                    if (!Model.method463(this.anIntArray94[i])) {
                        b = true;
                    }
                }
                if (b) {
                    return null;
                }
                final Model[] array2 = new Model[this.anIntArray94.length];
                for (int j = 0; j < this.anIntArray94.length; ++j) {
                    array2[j] = Model.method462(this.anIntArray94[j]);
                }
                if (array2.length == 1) {
                    model = array2[0];
                }
                else {
                    model = new Model(array2.length, array2);
                }
                if (this.anIntArray76 != null) {
                    for (int k = 0; k < this.anIntArray76.length; ++k) {
                        model.method476(this.anIntArray76[k], this.anIntArray70[k]);
                    }
                }
                model.method469();
                System.setProperty("sun.java2d.d3d", "true");
                System.setProperty("sun.java2d.d3dtexbpp", "32");
                System.setProperty("sun.java2d.ddforcevram", "true");
                System.setProperty("sun.java2d.translaccel", "true");
                System.setProperty("sun.java2d.pmoffscreen", "true");
                System.setProperty("sun.java2d.opengl", "true");
                model.method479(84 + this.anInt85, 1000 + this.anInt92, -90, -580, -90, true);
                EntityDef.mruNodes.removeFromCache(model, this.type);
            }
            final Model aModel_1621 = Model.aModel_1621;
            aModel_1621.method464(model, Class36.method532(n2) & Class36.method532(n));
            if (n2 != -1 && n != -1) {
                aModel_1621.method471(array, n, n2);
            }
            else if (n2 != -1) {
                aModel_1621.method470(n2);
            }
            if (this.anInt91 != 128 || this.anInt86 != 128) {
                aModel_1621.method478(this.anInt91 + 4, this.anInt91 + 4, this.anInt86 + 4);
            }
            aModel_1621.method466();
            aModel_1621.anIntArrayArray1658 = null;
            aModel_1621.anIntArrayArray1657 = null;
            if (this.aByte68 == 1) {
                aModel_1621.aBoolean1659 = true;
            }
            return aModel_1621;
        }
        final EntityDef method161 = this.method161();
        if (method161 == null) {
            return null;
        }
        return method161.method164(n, n2, array);
    }
    
    private void readValues(final Stream stream) {
        while (true) {
            final int unsignedByte = stream.readUnsignedByte();
            if (unsignedByte == 0) {
                break;
            }
            if (unsignedByte == 1) {
                final int unsignedByte2 = stream.readUnsignedByte();
                this.anIntArray94 = new int[unsignedByte2];
                for (int i = 0; i < unsignedByte2; ++i) {
                    this.anIntArray94[i] = stream.readUnsignedWord();
                }
            }
            else if (unsignedByte == 2) {
                this.name = stream.readNewString();
            }
            else if (unsignedByte == 3) {
                this.description = stream.readBytes();
            }
            else if (unsignedByte == 12) {
                this.aByte68 = stream.readSignedByte();
            }
            else if (unsignedByte == 13) {
                this.anInt77 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 14) {
                this.anInt67 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 17) {
                this.anInt67 = stream.readUnsignedWord();
                this.anInt58 = stream.readUnsignedWord();
                this.anInt83 = stream.readUnsignedWord();
                this.anInt55 = stream.readUnsignedWord();
            }
            else if (unsignedByte >= 30 && unsignedByte < 40) {
                if (this.actions == null) {
                    this.actions = new String[5];
                }
                this.actions[unsignedByte - 30] = stream.readNewString();
                if (!this.actions[unsignedByte - 30].equalsIgnoreCase("hidden")) {
                    continue;
                }
                this.actions[unsignedByte - 30] = null;
            }
            else if (unsignedByte == 40) {
                final int unsignedByte3 = stream.readUnsignedByte();
                this.anIntArray76 = new int[unsignedByte3];
                this.anIntArray70 = new int[unsignedByte3];
                for (int j = 0; j < unsignedByte3; ++j) {
                    this.anIntArray76[j] = stream.readUnsignedWord();
                    this.anIntArray70[j] = stream.readUnsignedWord();
                }
            }
            else if (unsignedByte == 60) {
                final int unsignedByte4 = stream.readUnsignedByte();
                this.anIntArray73 = new int[unsignedByte4];
                for (int k = 0; k < unsignedByte4; ++k) {
                    this.anIntArray73[k] = stream.readUnsignedWord();
                }
            }
            else if (unsignedByte == 90) {
                stream.readUnsignedWord();
            }
            else if (unsignedByte == 91) {
                stream.readUnsignedWord();
            }
            else if (unsignedByte == 92) {
                stream.readUnsignedWord();
            }
            else if (unsignedByte == 93) {
                this.aBoolean87 = false;
            }
            else if (unsignedByte == 95) {
                this.combatLevel = stream.readUnsignedWord();
            }
            else if (unsignedByte == 97) {
                this.anInt91 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 98) {
                this.anInt86 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 99) {
                this.aBoolean93 = true;
            }
            else if (unsignedByte == 100) {
                this.anInt85 = stream.readSignedByte();
            }
            else if (unsignedByte == 101) {
                this.anInt92 = stream.readSignedByte() * 5;
            }
            else if (unsignedByte == 102) {
                this.anInt75 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 103) {
                this.anInt79 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 106) {
                this.anInt57 = stream.readUnsignedWord();
                if (this.anInt57 == 65535) {
                    this.anInt57 = -1;
                }
                this.anInt59 = stream.readUnsignedWord();
                if (this.anInt59 == 65535) {
                    this.anInt59 = -1;
                }
                final int unsignedByte5 = stream.readUnsignedByte();
                this.childrenIDs = new int[unsignedByte5 + 1];
                for (int l = 0; l <= unsignedByte5; ++l) {
                    this.childrenIDs[l] = stream.readUnsignedWord();
                    if (this.childrenIDs[l] == 65535) {
                        this.childrenIDs[l] = -1;
                    }
                }
            }
            else {
                if (unsignedByte != 107) {
                    continue;
                }
                this.aBoolean84 = false;
            }
        }
    }
    
    private EntityDef() {
        this.anInt55 = -1;
        this.anInt57 = -1;
        this.anInt58 = -1;
        this.anInt59 = -1;
        this.combatLevel = -1;
        this.anInt64 = 1834;
        this.anInt67 = -1;
        this.aByte68 = 1;
        this.anInt75 = -1;
        this.anInt77 = -1;
        this.type = -1L;
        this.anInt79 = 32;
        this.anInt83 = -1;
        this.aBoolean84 = true;
        this.anInt86 = 128;
        this.aBoolean87 = true;
        this.anInt91 = 128;
        this.aBoolean93 = false;
    }
    
    static {
        EntityDef.mruNodes = new MRUNodes(30);
    }
}
