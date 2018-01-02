// 
// Decompiled by Procyon v0.5.30
// 

public final class ItemDef
{
    public byte aByte154;
    public int value;
    public int[] editedModelColor;
    public int ID;
    static MRUNodes mruNodes1;
    public static MRUNodes mruNodes2;
    public int[] newModelColor;
    public boolean membersObject;
    public int anInt162;
    public int certTemplateID;
    public int femaleEquip2;
    public int maleEquip1;
    public int anInt166;
    public int anInt167;
    public String[] groundActions;
    public int modelOffset1;
    public String name;
    public static ItemDef[] cache;
    public int anInt173;
    public int modelID;
    public int anInt175;
    public boolean stackable;
    public String description;
    public int certID;
    public static int cacheIndex;
    public int modelZoom;
    public static boolean isMembers;
    public static Stream stream;
    public int anInt184;
    public int anInt185;
    public int maleEquip2;
    public String[] actions;
    public int modelRotation1;
    public int anInt191;
    public int anInt192;
    public int[] stackIDs;
    public int modelOffset2;
    public static int[] streamIndices;
    public int anInt196;
    public int anInt197;
    public int modelRotation2;
    public int femaleEquip1;
    public int[] stackAmounts;
    public int team;
    public static int totalItems;
    public int anInt204;
    public byte aByte205;
    public int lendID;
    public int lentItemID;
    
    public static void dumpData() {
    }
    
    public static void nullLoader() {
        ItemDef.mruNodes2 = null;
        ItemDef.mruNodes1 = null;
        ItemDef.streamIndices = null;
        ItemDef.cache = null;
        ItemDef.stream = null;
    }
    
    public boolean method192(final int n) {
        int n2 = this.anInt175;
        int n3 = this.anInt166;
        if (n == 1) {
            n2 = this.anInt197;
            n3 = this.anInt173;
        }
        if (n2 == -1) {
            return true;
        }
        boolean b = true;
        if (!Model.method463(n2)) {
            b = false;
        }
        if (n3 != -1 && !Model.method463(n3)) {
            b = false;
        }
        return b;
    }
    
    public Model method194(final int n) {
        int n2 = this.anInt175;
        int n3 = this.anInt166;
        if (n == 1) {
            n2 = this.anInt197;
            n3 = this.anInt173;
        }
        if (n2 == -1) {
            return null;
        }
        Model method462 = Model.method462(n2);
        if (n3 != -1) {
            method462 = new Model(2, new Model[] { method462, Model.method462(n3) });
        }
        if (this.editedModelColor != null) {
            for (int i = 0; i < this.editedModelColor.length; ++i) {
                method462.method476(this.editedModelColor[i], this.newModelColor[i]);
            }
        }
        return method462;
    }
    
    public boolean method195(final int n) {
        int n2 = this.maleEquip1;
        int n3 = this.maleEquip2;
        int n4 = this.anInt185;
        if (n == 1) {
            n2 = this.femaleEquip1;
            n3 = this.femaleEquip2;
            n4 = this.anInt162;
        }
        if (n2 == -1) {
            return true;
        }
        boolean b = true;
        if (!Model.method463(n2)) {
            b = false;
        }
        if (n3 != -1 && !Model.method463(n3)) {
            b = false;
        }
        if (n4 != -1 && !Model.method463(n4)) {
            b = false;
        }
        return b;
    }
    
    public Model method196(final int n) {
        int n2 = this.maleEquip1;
        int n3 = this.maleEquip2;
        int n4 = this.anInt185;
        if (n == 1) {
            n2 = this.femaleEquip1;
            n3 = this.femaleEquip2;
            n4 = this.anInt162;
        }
        if (n2 == -1) {
            return null;
        }
        Model method462 = Model.method462(n2);
        if (n3 != -1) {
            if (n4 != -1) {
                method462 = new Model(3, new Model[] { method462, Model.method462(n3), Model.method462(n4) });
            }
            else {
                method462 = new Model(2, new Model[] { method462, Model.method462(n3) });
            }
        }
        if (n == 0 && this.aByte205 != 0) {
            method462.method475(0, this.aByte205, 0);
        }
        if (n == 1 && this.aByte154 != 0) {
            method462.method475(0, this.aByte154, 0);
        }
        if (this.editedModelColor != null) {
            for (int i = 0; i < this.editedModelColor.length; ++i) {
                method462.method476(this.editedModelColor[i], this.newModelColor[i]);
            }
        }
        return method462;
    }
    
    public void setDefaults() {
        this.modelID = 0;
        this.name = null;
        this.description = null;
        this.editedModelColor = null;
        this.newModelColor = null;
        this.modelZoom = 2000;
        this.modelRotation1 = 0;
        this.modelRotation2 = 0;
        this.anInt204 = 0;
        this.modelOffset1 = 0;
        this.modelOffset2 = 0;
        this.stackable = false;
        this.value = 1;
        this.membersObject = false;
        this.groundActions = null;
        this.actions = null;
        this.maleEquip1 = -1;
        this.maleEquip2 = -1;
        this.aByte205 = 0;
        this.femaleEquip1 = -1;
        this.femaleEquip2 = -1;
        this.aByte154 = 0;
        this.anInt185 = -1;
        this.anInt162 = -1;
        this.anInt175 = -1;
        this.anInt166 = -1;
        this.anInt197 = -1;
        this.anInt173 = -1;
        this.stackIDs = null;
        this.stackAmounts = null;
        this.certID = -1;
        this.certTemplateID = -1;
        this.anInt167 = 128;
        this.anInt192 = 128;
        this.anInt191 = 128;
        this.anInt196 = 0;
        this.anInt184 = 0;
        this.team = 0;
        this.lendID = -1;
        this.lentItemID = -1;
    }
    
    public static void unpackConfig(final StreamLoader streamLoader) {
        ItemDef.stream = new Stream(FileOperations.ReadFile(SignLink.findcachedir() + "obj.dat"));
        final Stream stream = new Stream(FileOperations.ReadFile(SignLink.findcachedir() + "obj.idx"));
        ItemDef.totalItems = stream.readUnsignedWord();
        ItemDef.streamIndices = new int[ItemDef.totalItems + 1000];
        int n = 2;
        for (int i = 0; i < ItemDef.totalItems; ++i) {
            ItemDef.streamIndices[i] = n;
            n += stream.readUnsignedWord();
        }
        ItemDef.cache = new ItemDef[10];
        for (int j = 0; j < 10; ++j) {
            ItemDef.cache[j] = new ItemDef();
        }
    }
    
    public static ItemDef forID(final int id) {
        for (int i = 0; i < 10; ++i) {
            if (ItemDef.cache[i].ID == id) {
                return ItemDef.cache[i];
            }
        }
        ItemDef.cacheIndex = (ItemDef.cacheIndex + 1) % 10;
        final ItemDef itemDef = ItemDef.cache[ItemDef.cacheIndex];
        ItemDef.stream.currentOffset = ItemDef.streamIndices[id];
        itemDef.ID = id;
        itemDef.setDefaults();
        itemDef.readValues(ItemDef.stream);
        if (itemDef.editedModelColor != null) {
            for (int j = 0; j < itemDef.editedModelColor.length; ++j) {
                if (itemDef.newModelColor[j] == 0) {
                    itemDef.newModelColor[j] = 1;
                }
            }
        }
        if (itemDef.certTemplateID != -1) {
            itemDef.toNote();
        }
        if (itemDef.lentItemID != -1) {
            itemDef.toLend();
        }
        if (!ItemDef.isMembers && itemDef.membersObject) {
            itemDef.name = "Members Object";
            itemDef.description = "Login to a members' server to use this object.";
            itemDef.groundActions = null;
            itemDef.actions = null;
            itemDef.team = 0;
        }
        switch (id) {
            case 20115: {
                itemDef.modelID = 62694;
                itemDef.name = "Ancient ceremonial hood";
                itemDef.modelZoom = 980;
                itemDef.modelRotation1 = 208;
                itemDef.modelRotation2 = 220;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffset2 = -18;
                itemDef.maleEquip1 = 62737;
                itemDef.femaleEquip1 = 62753;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.anInt175 = 62730;
                itemDef.anInt197 = 62730;
                break;
            }
            case 20116: {
                itemDef.modelID = 62705;
                itemDef.name = "Ancient ceremonial top";
                itemDef.modelZoom = 1316;
                itemDef.modelRotation1 = 477;
                itemDef.modelRotation2 = 9;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffset2 = 13;
                itemDef.maleEquip1 = 62745;
                itemDef.femaleEquip1 = 62763;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.anInt204 = 54;
                break;
            }
            case 20117: {
                itemDef.modelID = 62707;
                itemDef.name = "Ancient ceremonial legs";
                itemDef.modelZoom = 1828;
                itemDef.modelRotation1 = 539;
                itemDef.modelRotation2 = 0;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffset2 = 0;
                itemDef.maleEquip1 = 62740;
                itemDef.femaleEquip1 = 62759;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.anInt204 = 40;
                itemDef.anInt196 = 30;
                itemDef.anInt184 = 100;
                break;
            }
            case 20118: {
                itemDef.modelID = 62697;
                itemDef.name = "Ancient ceremonial gloves";
                itemDef.modelZoom = 548;
                itemDef.modelRotation1 = 618;
                itemDef.modelRotation2 = 1143;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffset2 = -5;
                itemDef.maleEquip1 = 62735;
                itemDef.femaleEquip1 = 62752;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            }
            case 20119: {
                itemDef.modelID = 62696;
                itemDef.name = "Ancient ceremonial boots";
                itemDef.modelZoom = 676;
                itemDef.modelRotation1 = 63;
                itemDef.modelRotation2 = 106;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffset2 = -1;
                itemDef.maleEquip1 = 62734;
                itemDef.femaleEquip1 = 62751;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            }
            case 20120: {
                itemDef.modelID = 57037;
                itemDef.name = "Frozen key";
                itemDef.modelZoom = 1184;
                itemDef.modelRotation1 = 384;
                itemDef.modelRotation2 = 162;
                itemDef.modelOffset1 = -8;
                itemDef.modelOffset2 = -14;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[0] = "Check-uses";
                itemDef.actions[4] = "Destroy";
                break;
            }
            case 20121: {
                itemDef.modelID = 52559;
                itemDef.name = "Frozen key piece (armadyl)";
                itemDef.description = "Frozen key piece (armadyl)";
                itemDef.modelZoom = 925;
                itemDef.modelRotation1 = 553;
                itemDef.modelRotation2 = 131;
                itemDef.modelOffset1 = 12;
                itemDef.modelOffset2 = -8;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[0] = "Assemble";
                itemDef.actions[4] = "Drop";
                itemDef.anInt167 = 260;
                itemDef.anInt192 = 260;
                itemDef.anInt191 = 260;
                break;
            }
            case 20122: {
                itemDef.modelID = 52562;
                itemDef.name = "Frozen key piece (bandos)";
                itemDef.description = "Frozen key piece (bandos)";
                itemDef.modelZoom = 720;
                itemDef.modelRotation1 = 600;
                itemDef.modelRotation2 = 223;
                itemDef.modelOffset1 = -7;
                itemDef.modelOffset2 = 2;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[0] = "Assemble";
                itemDef.actions[4] = "Drop";
                itemDef.anInt167 = 260;
                itemDef.anInt192 = 260;
                itemDef.anInt191 = 260;
                break;
            }
            case 20123: {
                itemDef.modelID = 52564;
                itemDef.name = "Frozen key piece (zamorak)";
                itemDef.description = "Frozen key piece (zamorak)";
                itemDef.modelZoom = 457;
                itemDef.modelRotation1 = 387;
                itemDef.modelRotation2 = 95;
                itemDef.modelOffset1 = 26;
                itemDef.modelOffset2 = -34;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[0] = "Assemble";
                itemDef.actions[4] = "Drop";
                itemDef.anInt167 = 260;
                itemDef.anInt192 = 260;
                itemDef.anInt191 = 260;
                break;
            }
            case 20124: {
                itemDef.modelID = 52561;
                itemDef.name = "Frozen key piece (saradomin)";
                itemDef.description = "Frozen key piece (saradomin)";
                itemDef.modelZoom = 541;
                itemDef.modelRotation1 = 444;
                itemDef.modelRotation2 = 32;
                itemDef.modelOffset1 = 16;
                itemDef.modelOffset2 = -47;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[0] = "Assemble";
                itemDef.actions[4] = "Drop";
                itemDef.anInt167 = 260;
                itemDef.anInt192 = 260;
                itemDef.anInt191 = 260;
                break;
            }
            case 20135: {
                itemDef.modelID = 62714;
                itemDef.name = "Torva full helm";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.modelRotation1 = 85;
                itemDef.modelRotation2 = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffset2 = -3;
                itemDef.maleEquip1 = 62738;
                itemDef.femaleEquip1 = 62754;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.anInt175 = 62729;
                itemDef.anInt197 = 62729;
                break;
            }
            case 20139: {
                itemDef.modelID = 62699;
                itemDef.name = "Torva platebody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.modelRotation1 = 473;
                itemDef.modelRotation2 = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffset2 = 0;
                itemDef.maleEquip1 = 62746;
                itemDef.femaleEquip1 = 62762;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            }
            case 20143: {
                itemDef.modelID = 62701;
                itemDef.name = "Torva platelegs";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.modelRotation1 = 474;
                itemDef.modelRotation2 = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffset2 = -5;
                itemDef.maleEquip1 = 62743;
                itemDef.femaleEquip1 = 62760;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            }
            case 20147: {
                itemDef.modelID = 62693;
                itemDef.name = "Pernix cowl";
                itemDef.description = "Pernix cowl";
                itemDef.modelZoom = 800;
                itemDef.modelRotation1 = 532;
                itemDef.modelRotation2 = 14;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffset2 = 1;
                itemDef.maleEquip1 = 62739;
                itemDef.femaleEquip1 = 62756;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.anInt175 = 62731;
                itemDef.anInt197 = 62727;
                itemDef.editedModelColor = new int[2];
                itemDef.newModelColor = new int[2];
                itemDef.editedModelColor[0] = 4550;
                itemDef.newModelColor[0] = 0;
                itemDef.editedModelColor[1] = 4540;
                itemDef.newModelColor[1] = 0;
                break;
            }
            case 20151: {
                itemDef.modelID = 62709;
                itemDef.name = "Pernix body";
                itemDef.description = "Pernix body";
                itemDef.modelZoom = 1378;
                itemDef.modelRotation1 = 485;
                itemDef.modelRotation2 = 2042;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffset2 = 7;
                itemDef.maleEquip1 = 62744;
                itemDef.femaleEquip1 = 62765;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            }
            case 20155: {
                itemDef.modelID = 62695;
                itemDef.name = "Pernix chaps";
                itemDef.description = "Pernix chaps";
                itemDef.modelZoom = 1740;
                itemDef.modelRotation1 = 504;
                itemDef.modelRotation2 = 0;
                itemDef.modelOffset1 = 4;
                itemDef.modelOffset2 = 3;
                itemDef.maleEquip1 = 62741;
                itemDef.femaleEquip1 = 62757;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            }
            case 20159: {
                itemDef.modelID = 62710;
                itemDef.name = "Virtus mask";
                itemDef.description = "Virtus mask";
                itemDef.modelZoom = 928;
                itemDef.modelRotation1 = 406;
                itemDef.modelRotation2 = 2041;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffset2 = -5;
                itemDef.maleEquip1 = 62736;
                itemDef.femaleEquip1 = 62755;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.anInt175 = 62728;
                itemDef.anInt197 = 62728;
                break;
            }
            case 20163: {
                itemDef.modelID = 62704;
                itemDef.name = "Virtus robe top";
                itemDef.description = "Virtus robe top";
                itemDef.modelZoom = 1122;
                itemDef.modelRotation1 = 488;
                itemDef.modelRotation2 = 3;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffset2 = 0;
                itemDef.maleEquip1 = 62748;
                itemDef.femaleEquip1 = 62764;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            }
            case 20167: {
                itemDef.modelID = 62700;
                itemDef.name = "Virtus robe legs";
                itemDef.description = "Virtus robe legs";
                itemDef.modelZoom = 1740;
                itemDef.modelRotation1 = 498;
                itemDef.modelRotation2 = 2045;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffset2 = 4;
                itemDef.maleEquip1 = 62742;
                itemDef.femaleEquip1 = 62758;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            }
            case 20171: {
                itemDef.modelID = 62692;
                itemDef.name = "Zaryte bow";
                itemDef.description = "Zaryte bow";
                itemDef.modelZoom = 1703;
                itemDef.modelRotation1 = 221;
                itemDef.modelRotation2 = 404;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffset2 = -13;
                itemDef.maleEquip1 = 62750;
                itemDef.femaleEquip1 = 62750;
                itemDef.aByte154 = -11;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[1] = "Wield";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            }
            case 20175: {
                itemDef.modelID = 57921;
                itemDef.name = "Trollheim tablet";
                itemDef.modelZoom = 465;
                itemDef.modelRotation1 = 373;
                itemDef.modelRotation2 = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffset2 = -1;
                itemDef.value = 1;
                (itemDef.groundActions = new String[5])[2] = "Take";
                (itemDef.actions = new String[5])[0] = "Break";
                itemDef.actions[4] = "Drop";
                break;
            }
        }
        return itemDef;
    }
    
    private void readValues(final Stream stream) {
        while (true) {
            final int unsignedByte = stream.readUnsignedByte();
            if (unsignedByte == 0) {
                break;
            }
            if (unsignedByte == 1) {
                this.modelID = stream.readUnsignedWord();
            }
            else if (unsignedByte == 2) {
                this.name = stream.readString();
            }
            else if (unsignedByte == 3) {
                this.description = stream.readString();
            }
            else if (unsignedByte == 4) {
                this.modelZoom = stream.readUnsignedWord();
            }
            else if (unsignedByte == 5) {
                this.modelRotation1 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 6) {
                this.modelRotation2 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 7) {
                this.modelOffset1 = stream.readUnsignedWord();
                if (this.modelOffset1 <= 32767) {
                    continue;
                }
                this.modelOffset1 -= 65536;
            }
            else if (unsignedByte == 8) {
                this.modelOffset2 = stream.readUnsignedWord();
                if (this.modelOffset2 <= 32767) {
                    continue;
                }
                this.modelOffset2 -= 65536;
            }
            else if (unsignedByte == 10) {
                stream.readUnsignedWord();
            }
            else if (unsignedByte == 11) {
                this.stackable = true;
            }
            else if (unsignedByte == 12) {
                this.value = stream.readUnsignedWord();
            }
            else if (unsignedByte == 16) {
                this.membersObject = true;
            }
            else if (unsignedByte == 23) {
                this.maleEquip1 = stream.readUnsignedWord();
                this.aByte205 = stream.readSignedByte();
            }
            else if (unsignedByte == 24) {
                this.maleEquip2 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 25) {
                this.femaleEquip1 = stream.readUnsignedWord();
                this.aByte154 = stream.readSignedByte();
            }
            else if (unsignedByte == 26) {
                this.femaleEquip2 = stream.readUnsignedWord();
            }
            else if (unsignedByte >= 30 && unsignedByte < 35) {
                if (this.groundActions == null) {
                    this.groundActions = new String[5];
                }
                this.groundActions[unsignedByte - 30] = stream.readString();
                if (!this.groundActions[unsignedByte - 30].equalsIgnoreCase("hidden")) {
                    continue;
                }
                this.groundActions[unsignedByte - 30] = null;
            }
            else if (unsignedByte >= 35 && unsignedByte < 40) {
                if (this.actions == null) {
                    this.actions = new String[5];
                }
                this.actions[unsignedByte - 35] = stream.readString();
                if (!this.actions[unsignedByte - 35].equalsIgnoreCase("null")) {
                    continue;
                }
                this.actions[unsignedByte - 35] = null;
            }
            else if (unsignedByte == 40) {
                final int unsignedByte2 = stream.readUnsignedByte();
                this.editedModelColor = new int[unsignedByte2];
                this.newModelColor = new int[unsignedByte2];
                for (int i = 0; i < unsignedByte2; ++i) {
                    this.editedModelColor[i] = stream.readUnsignedWord();
                    this.newModelColor[i] = stream.readUnsignedWord();
                }
            }
            else if (unsignedByte == 78) {
                this.anInt185 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 79) {
                this.anInt162 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 90) {
                this.anInt175 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 91) {
                this.anInt197 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 92) {
                this.anInt166 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 93) {
                this.anInt173 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 95) {
                this.anInt204 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 97) {
                this.certID = stream.readUnsignedWord();
            }
            else if (unsignedByte == 98) {
                this.certTemplateID = stream.readUnsignedWord();
            }
            else if (unsignedByte >= 100 && unsignedByte < 110) {
                if (this.stackIDs == null) {
                    this.stackIDs = new int[10];
                    this.stackAmounts = new int[10];
                }
                this.stackIDs[unsignedByte - 100] = stream.readUnsignedWord();
                this.stackAmounts[unsignedByte - 100] = stream.readUnsignedWord();
            }
            else if (unsignedByte == 110) {
                this.anInt167 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 111) {
                this.anInt192 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 112) {
                this.anInt191 = stream.readUnsignedWord();
            }
            else if (unsignedByte == 113) {
                this.anInt196 = stream.readSignedByte();
            }
            else if (unsignedByte == 114) {
                this.anInt184 = stream.readSignedByte() * 5;
            }
            else if (unsignedByte == 115) {
                this.team = stream.readUnsignedByte();
            }
            else if (unsignedByte == 116) {
                this.lendID = stream.readUnsignedWord();
            }
            else {
                if (unsignedByte != 117) {
                    continue;
                }
                this.lentItemID = stream.readUnsignedWord();
            }
        }
    }
    
    public void toNote() {
        final ItemDef forID = forID(this.certTemplateID);
        this.modelID = forID.modelID;
        this.modelZoom = forID.modelZoom;
        this.modelRotation1 = forID.modelRotation1;
        this.modelRotation2 = forID.modelRotation2;
        this.anInt204 = forID.anInt204;
        this.modelOffset1 = forID.modelOffset1;
        this.modelOffset2 = forID.modelOffset2;
        this.editedModelColor = forID.editedModelColor;
        this.newModelColor = forID.newModelColor;
        final ItemDef forID2 = forID(this.certID);
        this.name = forID2.name;
        this.membersObject = forID2.membersObject;
        this.value = forID2.value;
        String s = "a";
        final char char1 = forID2.name.charAt(0);
        if (char1 == 'A' || char1 == 'E' || char1 == 'I' || char1 == 'O' || char1 == 'U') {
            s = "an";
        }
        this.description = "Swap this note at any bank for " + s + " " + forID2.name + ".";
        this.stackable = true;
    }
    
    private void toLend() {
        final ItemDef forID = forID(this.lentItemID);
        this.actions = new String[5];
        this.modelID = forID.modelID;
        this.modelOffset1 = forID.modelOffset1;
        this.modelRotation2 = forID.modelRotation2;
        this.modelOffset2 = forID.modelOffset2;
        this.modelZoom = forID.modelZoom;
        this.modelRotation1 = forID.modelRotation1;
        this.anInt204 = forID.anInt204;
        this.value = 0;
        final ItemDef forID2 = forID(this.lendID);
        this.anInt166 = forID2.anInt166;
        this.editedModelColor = forID2.editedModelColor;
        this.anInt185 = forID2.anInt185;
        this.maleEquip2 = forID2.maleEquip2;
        this.anInt173 = forID2.anInt173;
        this.anInt175 = forID2.anInt175;
        this.groundActions = forID2.groundActions;
        this.maleEquip1 = forID2.maleEquip1;
        this.name = forID2.name;
        this.femaleEquip1 = forID2.femaleEquip1;
        this.membersObject = forID2.membersObject;
        this.anInt197 = forID2.anInt197;
        this.femaleEquip2 = forID2.femaleEquip2;
        this.anInt162 = forID2.anInt162;
        this.newModelColor = forID2.newModelColor;
        this.team = forID2.team;
        if (forID2.actions != null) {
            for (int i = 0; i < 4; ++i) {
                this.actions[i] = forID2.actions[i];
            }
        }
        this.actions[4] = "Discard";
    }
    
    public static Sprite getSprite(final int n, int anInt1445, final int n2) {
        if (n2 == 0) {
            Sprite sprite = (Sprite)ItemDef.mruNodes1.insertFromCache(n);
            if (sprite != null && sprite.anInt1445 != anInt1445 && sprite.anInt1445 != -1) {
                sprite.unlink();
                sprite = null;
            }
            if (sprite != null) {
                return sprite;
            }
        }
        ItemDef itemDef = forID(n);
        if (itemDef.stackIDs == null) {
            anInt1445 = -1;
        }
        if (anInt1445 > 1) {
            int n3 = -1;
            for (int i = 0; i < 10; ++i) {
                if (anInt1445 >= itemDef.stackAmounts[i] && itemDef.stackAmounts[i] != 0) {
                    n3 = itemDef.stackIDs[i];
                }
            }
            if (n3 != -1) {
                itemDef = forID(n3);
            }
        }
        final Model method201 = itemDef.method201(1);
        if (method201 == null) {
            return null;
        }
        Sprite sprite2 = null;
        if (itemDef.certTemplateID != -1) {
            sprite2 = getSprite(itemDef.certID, 10, -1);
            if (sprite2 == null) {
                return null;
            }
        }
        if (itemDef.lentItemID != -1) {
            sprite2 = getSprite(itemDef.lendID, 50, 0);
            if (sprite2 == null) {
                return null;
            }
        }
        final Sprite sprite3 = new Sprite(32, 32);
        final int textureInt1 = Texture.textureInt1;
        final int textureInt2 = Texture.textureInt2;
        final int[] anIntArray1472 = Texture.anIntArray1472;
        final int[] pixels = DrawingArea.pixels;
        final int width = DrawingArea.width;
        final int height = DrawingArea.height;
        final int topX = DrawingArea.topX;
        final int bottomX = DrawingArea.bottomX;
        final int topY = DrawingArea.topY;
        final int bottomY = DrawingArea.bottomY;
        Texture.aBoolean1464 = false;
        DrawingArea.initDrawingArea(32, 32, sprite3.myPixels);
        DrawingArea.drawPixels(32, 0, 0, 0, 32);
        Texture.method364();
        int modelZoom = itemDef.modelZoom;
        if (n2 == -1) {
            modelZoom *= (int)1.5;
        }
        if (n2 > 0) {
            modelZoom *= (int)1.04;
        }
        method201.method482(itemDef.modelRotation2, itemDef.anInt204, itemDef.modelRotation1, itemDef.modelOffset1, (Texture.anIntArray1470[itemDef.modelRotation1] * modelZoom >> 16) + method201.modelHeight / 2 + itemDef.modelOffset2, (Texture.anIntArray1471[itemDef.modelRotation1] * modelZoom >> 16) + itemDef.modelOffset2);
        for (int j = 31; j >= 0; --j) {
            for (int k = 31; k >= 0; --k) {
                if (sprite3.myPixels[j + k * 32] == 0) {
                    if (j > 0 && sprite3.myPixels[j - 1 + k * 32] > 1) {
                        sprite3.myPixels[j + k * 32] = 1;
                    }
                    else if (k > 0 && sprite3.myPixels[j + (k - 1) * 32] > 1) {
                        sprite3.myPixels[j + k * 32] = 1;
                    }
                    else if (j < 31 && sprite3.myPixels[j + 1 + k * 32] > 1) {
                        sprite3.myPixels[j + k * 32] = 1;
                    }
                    else if (k < 31 && sprite3.myPixels[j + (k + 1) * 32] > 1) {
                        sprite3.myPixels[j + k * 32] = 1;
                    }
                }
            }
        }
        if (n2 > 0) {
            for (int l = 31; l >= 0; --l) {
                for (int n4 = 31; n4 >= 0; --n4) {
                    if (sprite3.myPixels[l + n4 * 32] == 0) {
                        if (l > 0 && sprite3.myPixels[l - 1 + n4 * 32] == 1) {
                            sprite3.myPixels[l + n4 * 32] = n2;
                        }
                        else if (n4 > 0 && sprite3.myPixels[l + (n4 - 1) * 32] == 1) {
                            sprite3.myPixels[l + n4 * 32] = n2;
                        }
                        else if (l < 31 && sprite3.myPixels[l + 1 + n4 * 32] == 1) {
                            sprite3.myPixels[l + n4 * 32] = n2;
                        }
                        else if (n4 < 31 && sprite3.myPixels[l + (n4 + 1) * 32] == 1) {
                            sprite3.myPixels[l + n4 * 32] = n2;
                        }
                    }
                }
            }
        }
        else if (n2 == 0) {
            for (int n5 = 31; n5 >= 0; --n5) {
                for (int n6 = 31; n6 >= 0; --n6) {
                    if (sprite3.myPixels[n5 + n6 * 32] == 0 && n5 > 0 && n6 > 0 && sprite3.myPixels[n5 - 1 + (n6 - 1) * 32] > 0) {
                        sprite3.myPixels[n5 + n6 * 32] = 3153952;
                    }
                }
            }
        }
        if (itemDef.certTemplateID != -1) {
            final int anInt1446 = sprite2.anInt1444;
            final int anInt1447 = sprite2.anInt1445;
            sprite2.anInt1444 = 32;
            sprite2.anInt1445 = 32;
            sprite2.drawSprite(0, 0);
            sprite2.anInt1444 = anInt1446;
            sprite2.anInt1445 = anInt1447;
        }
        if (itemDef.lentItemID != -1) {
            final int anInt1448 = sprite2.anInt1444;
            final int anInt1449 = sprite2.anInt1445;
            sprite2.anInt1444 = 32;
            sprite2.anInt1445 = 32;
            sprite2.drawSprite(0, 0);
            sprite2.anInt1444 = anInt1448;
            sprite2.anInt1445 = anInt1449;
        }
        if (n2 == 0) {
            ItemDef.mruNodes1.removeFromCache(sprite3, n);
        }
        DrawingArea.initDrawingArea(height, width, pixels);
        DrawingArea.setDrawingArea(bottomY, topX, bottomX, topY);
        Texture.textureInt1 = textureInt1;
        Texture.textureInt2 = textureInt2;
        Texture.anIntArray1472 = anIntArray1472;
        Texture.aBoolean1464 = true;
        if (itemDef.stackable) {
            sprite3.anInt1444 = 33;
        }
        else {
            sprite3.anInt1444 = 32;
        }
        sprite3.anInt1445 = anInt1445;
        return sprite3;
    }
    
    public Model method201(final int n) {
        if (this.stackIDs != null && n > 1) {
            int n2 = -1;
            for (int i = 0; i < 10; ++i) {
                if (n >= this.stackAmounts[i] && this.stackAmounts[i] != 0) {
                    n2 = this.stackIDs[i];
                }
            }
            if (n2 != -1) {
                return forID(n2).method201(1);
            }
        }
        final Model model = (Model)ItemDef.mruNodes2.insertFromCache(this.ID);
        if (model != null) {
            return model;
        }
        final Model method462 = Model.method462(this.modelID);
        if (method462 == null) {
            return null;
        }
        if (this.anInt167 != 128 || this.anInt192 != 128 || this.anInt191 != 128) {
            method462.method478(this.anInt167, this.anInt191, this.anInt192);
        }
        if (this.editedModelColor != null) {
            for (int j = 0; j < this.editedModelColor.length; ++j) {
                method462.method476(this.editedModelColor[j], this.newModelColor[j]);
            }
        }
        method462.method479(64 + this.anInt196, 768 + this.anInt184, -50, -10, -50, true);
        method462.aBoolean1659 = true;
        ItemDef.mruNodes2.removeFromCache(method462, this.ID);
        return method462;
    }
    
    public Model method202(final int n) {
        if (this.stackIDs != null && n > 1) {
            int n2 = -1;
            for (int i = 0; i < 10; ++i) {
                if (n >= this.stackAmounts[i] && this.stackAmounts[i] != 0) {
                    n2 = this.stackIDs[i];
                }
            }
            if (n2 != -1) {
                return forID(n2).method202(1);
            }
        }
        final Model method462 = Model.method462(this.modelID);
        if (method462 == null) {
            return null;
        }
        if (this.editedModelColor != null) {
            for (int j = 0; j < this.editedModelColor.length; ++j) {
                method462.method476(this.editedModelColor[j], this.newModelColor[j]);
            }
        }
        return method462;
    }
    
    public ItemDef() {
        this.ID = -1;
    }
    
    static {
        ItemDef.mruNodes1 = new MRUNodes(100);
        ItemDef.mruNodes2 = new MRUNodes(50);
        ItemDef.isMembers = true;
    }
}
