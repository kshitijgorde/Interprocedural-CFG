// 
// Decompiled by Procyon v0.5.30
// 

public final class RSInterface
{
    private static String[] pouchNames;
    public boolean inventoryhover;
    public boolean inventoryHover;
    public String popupString;
    public String hoverText;
    static int f;
    static int f2;
    public static StreamLoader aClass44;
    public boolean drawsTransparent;
    public Sprite sprite1;
    public int anInt208;
    public Sprite[] sprites;
    public static RSInterface[] interfaceCache;
    public int[] anIntArray212;
    public int contentType;
    public int[] spritesX;
    public int anInt216;
    public int atActionType;
    public String spellName;
    public int anInt219;
    public int width;
    public String tooltip;
    public String selectedActionName;
    public boolean centerText;
    public int scrollPosition;
    public String[] actions;
    public int[][] valueIndexArray;
    public boolean aBoolean227;
    public String aString228;
    public int mOverInterToTrigger;
    public int invSpritePadX;
    public int textColor;
    public int anInt233;
    public int mediaID;
    public boolean aBoolean235;
    public int parentID;
    public int spellUsableOn;
    private static MRUNodes aMRUNodes_238;
    public int anInt239;
    public int[] children;
    public int[] childX;
    public boolean usableItemInterface;
    public TextDrawingArea textDrawingAreas;
    public int invSpritePadY;
    public int[] anIntArray245;
    public int anInt246;
    public int[] spritesY;
    public String message;
    public boolean isInventoryInterface;
    public int id;
    public int[] invStackSizes;
    public int[] inv;
    public byte aByte254;
    private int anInt255;
    private int anInt256;
    public int anInt257;
    public int anInt258;
    public boolean aBoolean259;
    public Sprite sprite2;
    public int scrollMax;
    public int type;
    public int anInt263;
    private static final MRUNodes aMRUNodes_264;
    public int anInt265;
    public boolean isMouseoverTriggered;
    public int height;
    public boolean textShadow;
    public int modelZoom;
    public int modelRotation1;
    public int modelRotation2;
    public int[] childY;
    public boolean isInventory;
    public String disabledText;
    public static TextDrawingArea[] fonts;
    
    public void swapInventoryItems(final int n, final int n2) {
        if (!client.canSwap) {
            return;
        }
        final int n3 = this.inv[n];
        this.inv[n] = this.inv[n2];
        this.inv[n2] = n3;
        final int n4 = this.invStackSizes[n];
        this.invStackSizes[n] = this.invStackSizes[n2];
        this.invStackSizes[n2] = n4;
    }
    
    public static void unpack(final StreamLoader aClass44, final TextDrawingArea[] fonts, final StreamLoader streamLoader) {
        RSInterface.fonts = fonts;
        RSInterface.aMRUNodes_238 = new MRUNodes(50000);
        final Stream stream = new Stream(aClass44.getDataForName("data"));
        int unsignedWord = -1;
        RSInterface.interfaceCache = new RSInterface[stream.readUnsignedWord() + 41000];
        while (stream.currentOffset < stream.buffer.length) {
            int id = stream.readUnsignedWord();
            if (id == 65535) {
                unsignedWord = stream.readUnsignedWord();
                id = stream.readUnsignedWord();
            }
            final RSInterface[] interfaceCache = RSInterface.interfaceCache;
            final int n = id;
            final RSInterface rsInterface = new RSInterface();
            interfaceCache[n] = rsInterface;
            final RSInterface rsInterface2 = rsInterface;
            rsInterface2.id = id;
            rsInterface2.parentID = unsignedWord;
            rsInterface2.type = stream.readUnsignedByte();
            rsInterface2.atActionType = stream.readUnsignedByte();
            rsInterface2.contentType = stream.readUnsignedWord();
            rsInterface2.width = stream.readUnsignedWord();
            rsInterface2.height = stream.readUnsignedWord();
            rsInterface2.aByte254 = (byte)stream.readUnsignedByte();
            rsInterface2.mOverInterToTrigger = stream.readUnsignedByte();
            if (rsInterface2.mOverInterToTrigger != 0) {
                rsInterface2.mOverInterToTrigger = (rsInterface2.mOverInterToTrigger - 1 << 8) + stream.readUnsignedByte();
            }
            else {
                rsInterface2.mOverInterToTrigger = -1;
            }
            final int unsignedByte = stream.readUnsignedByte();
            if (unsignedByte > 0) {
                rsInterface2.anIntArray245 = new int[unsignedByte];
                rsInterface2.anIntArray212 = new int[unsignedByte];
                for (int i = 0; i < unsignedByte; ++i) {
                    rsInterface2.anIntArray245[i] = stream.readUnsignedByte();
                    rsInterface2.anIntArray212[i] = stream.readUnsignedWord();
                }
            }
            final int unsignedByte2 = stream.readUnsignedByte();
            if (unsignedByte2 > 0) {
                rsInterface2.valueIndexArray = new int[unsignedByte2][];
                for (int j = 0; j < unsignedByte2; ++j) {
                    final int unsignedWord2 = stream.readUnsignedWord();
                    rsInterface2.valueIndexArray[j] = new int[unsignedWord2];
                    for (int k = 0; k < unsignedWord2; ++k) {
                        rsInterface2.valueIndexArray[j][k] = stream.readUnsignedWord();
                    }
                }
            }
            if (rsInterface2.type == 0) {
                rsInterface2.drawsTransparent = false;
                rsInterface2.scrollMax = stream.readUnsignedWord();
                rsInterface2.isMouseoverTriggered = (stream.readUnsignedByte() == 1);
                final int unsignedWord3 = stream.readUnsignedWord();
                rsInterface2.children = new int[unsignedWord3];
                rsInterface2.childX = new int[unsignedWord3];
                rsInterface2.childY = new int[unsignedWord3];
                for (int l = 0; l < unsignedWord3; ++l) {
                    rsInterface2.children[l] = stream.readUnsignedWord();
                    rsInterface2.childX[l] = stream.readSignedWord();
                    rsInterface2.childY[l] = stream.readSignedWord();
                }
            }
            if (rsInterface2.type == 1) {
                stream.readUnsignedWord();
                stream.readUnsignedByte();
            }
            if (rsInterface2.type == 2) {
                rsInterface2.inv = new int[rsInterface2.width * rsInterface2.height];
                rsInterface2.invStackSizes = new int[rsInterface2.width * rsInterface2.height];
                rsInterface2.aBoolean259 = (stream.readUnsignedByte() == 1);
                rsInterface2.isInventoryInterface = (stream.readUnsignedByte() == 1);
                rsInterface2.usableItemInterface = (stream.readUnsignedByte() == 1);
                rsInterface2.aBoolean235 = (stream.readUnsignedByte() == 1);
                rsInterface2.invSpritePadX = stream.readUnsignedByte();
                rsInterface2.invSpritePadY = stream.readUnsignedByte();
                rsInterface2.spritesX = new int[20];
                rsInterface2.spritesY = new int[20];
                rsInterface2.sprites = new Sprite[20];
                for (int n2 = 0; n2 < 20; ++n2) {
                    if (stream.readUnsignedByte() == 1) {
                        rsInterface2.spritesX[n2] = stream.readSignedWord();
                        rsInterface2.spritesY[n2] = stream.readSignedWord();
                        final String string = stream.readString();
                        if (streamLoader != null && string.length() > 0) {
                            final int lastIndex = string.lastIndexOf(",");
                            rsInterface2.sprites[n2] = method207(Integer.parseInt(string.substring(lastIndex + 1)), streamLoader, string.substring(0, lastIndex));
                        }
                    }
                }
                rsInterface2.actions = new String[5];
                for (int n3 = 0; n3 < 5; ++n3) {
                    rsInterface2.actions[n3] = stream.readString();
                    if (rsInterface2.actions[n3].length() == 0) {
                        rsInterface2.actions[n3] = null;
                    }
                    if (rsInterface2.parentID == 1644) {
                        rsInterface2.actions[2] = "Operate";
                    }
                }
            }
            if (rsInterface2.type == 3) {
                rsInterface2.aBoolean227 = (stream.readUnsignedByte() == 1);
            }
            if (rsInterface2.type == 4 || rsInterface2.type == 1) {
                rsInterface2.centerText = (stream.readUnsignedByte() == 1);
                final int unsignedByte3 = stream.readUnsignedByte();
                if (fonts != null) {
                    rsInterface2.textDrawingAreas = fonts[unsignedByte3];
                }
                rsInterface2.textShadow = (stream.readUnsignedByte() == 1);
            }
            if (rsInterface2.type == 4) {
                rsInterface2.message = stream.readString().replaceAll("RuneScape", "Devilishpkz");
                rsInterface2.aString228 = stream.readString();
            }
            if (rsInterface2.type == 1 || rsInterface2.type == 3 || rsInterface2.type == 4) {
                rsInterface2.textColor = stream.readDWord();
            }
            if (rsInterface2.type == 3 || rsInterface2.type == 4) {
                rsInterface2.anInt219 = stream.readDWord();
                rsInterface2.anInt216 = stream.readDWord();
                rsInterface2.anInt239 = stream.readDWord();
            }
            if (rsInterface2.type == 5) {
                rsInterface2.drawsTransparent = false;
                final String string2 = stream.readString();
                if (streamLoader != null && string2.length() > 0) {
                    final int lastIndex2 = string2.lastIndexOf(",");
                    rsInterface2.sprite1 = method207(Integer.parseInt(string2.substring(lastIndex2 + 1)), streamLoader, string2.substring(0, lastIndex2));
                }
                final String string3 = stream.readString();
                if (streamLoader != null && string3.length() > 0) {
                    final int lastIndex3 = string3.lastIndexOf(",");
                    rsInterface2.sprite2 = method207(Integer.parseInt(string3.substring(lastIndex3 + 1)), streamLoader, string3.substring(0, lastIndex3));
                }
            }
            if (rsInterface2.type == 6) {
                final int unsignedByte4 = stream.readUnsignedByte();
                if (unsignedByte4 != 0) {
                    rsInterface2.anInt233 = 1;
                    rsInterface2.mediaID = (unsignedByte4 - 1 << 8) + stream.readUnsignedByte();
                }
                final int unsignedByte5 = stream.readUnsignedByte();
                if (unsignedByte5 != 0) {
                    rsInterface2.anInt255 = 1;
                    rsInterface2.anInt256 = (unsignedByte5 - 1 << 8) + stream.readUnsignedByte();
                }
                final int unsignedByte6 = stream.readUnsignedByte();
                if (unsignedByte6 != 0) {
                    rsInterface2.anInt257 = (unsignedByte6 - 1 << 8) + stream.readUnsignedByte();
                }
                else {
                    rsInterface2.anInt257 = -1;
                }
                final int unsignedByte7 = stream.readUnsignedByte();
                if (unsignedByte7 != 0) {
                    rsInterface2.anInt258 = (unsignedByte7 - 1 << 8) + stream.readUnsignedByte();
                }
                else {
                    rsInterface2.anInt258 = -1;
                }
                rsInterface2.modelZoom = stream.readUnsignedWord();
                rsInterface2.modelRotation1 = stream.readUnsignedWord();
                rsInterface2.modelRotation2 = stream.readUnsignedWord();
            }
            if (rsInterface2.type == 7) {
                rsInterface2.inv = new int[rsInterface2.width * rsInterface2.height];
                rsInterface2.invStackSizes = new int[rsInterface2.width * rsInterface2.height];
                rsInterface2.centerText = (stream.readUnsignedByte() == 1);
                final int unsignedByte8 = stream.readUnsignedByte();
                if (fonts != null) {
                    rsInterface2.textDrawingAreas = fonts[unsignedByte8];
                }
                rsInterface2.textShadow = (stream.readUnsignedByte() == 1);
                rsInterface2.textColor = stream.readDWord();
                rsInterface2.invSpritePadX = stream.readSignedWord();
                rsInterface2.invSpritePadY = stream.readSignedWord();
                rsInterface2.isInventoryInterface = (stream.readUnsignedByte() == 1);
                rsInterface2.actions = new String[5];
                for (int n4 = 0; n4 < 5; ++n4) {
                    rsInterface2.actions[n4] = stream.readString();
                    if (rsInterface2.actions[n4].length() == 0) {
                        rsInterface2.actions[n4] = null;
                    }
                }
            }
            if (rsInterface2.atActionType == 2 || rsInterface2.type == 2) {
                rsInterface2.selectedActionName = stream.readString();
                rsInterface2.spellName = stream.readString();
                rsInterface2.spellUsableOn = stream.readUnsignedWord();
            }
            if (rsInterface2.type == 8) {
                rsInterface2.message = stream.readString();
            }
            if (rsInterface2.atActionType == 1 || rsInterface2.atActionType == 4 || rsInterface2.atActionType == 5 || rsInterface2.atActionType == 6) {
                rsInterface2.tooltip = stream.readString();
                if (rsInterface2.tooltip.length() != 0) {
                    continue;
                }
                if (rsInterface2.atActionType == 1) {
                    rsInterface2.tooltip = "Ok";
                }
                if (rsInterface2.atActionType == 4) {
                    rsInterface2.tooltip = "Select";
                }
                if (rsInterface2.atActionType == 5) {
                    rsInterface2.tooltip = "Select";
                }
                if (rsInterface2.atActionType != 6) {
                    continue;
                }
                rsInterface2.tooltip = "Continue";
            }
        }
        RSInterface.aClass44 = aClass44;
        prayerTab(fonts);
        emoteTab();
        optionTab(fonts);
        clanChatTab(fonts);
        settings(fonts);
        Sidebar0(fonts);
        friendsTab(fonts);
        ignoreTab(fonts);
        Pestpanel(fonts);
        Pestpanel2(fonts);
        equipmentScreen(fonts);
        magicTab(fonts);
        ancientMagicTab(fonts);
        configureLunar(fonts);
        constructLunar();
        pouches(fonts);
        questTab(fonts);
        Bank();
        BH(fonts);
        GodWars(fonts);
        Curses(fonts);
        summoning(fonts);
        RSInterface.aMRUNodes_238 = null;
    }
    
    public int getXPForLevel(final int n) {
        int n2 = 0;
        int n3 = 0;
        for (int i = 1; i <= n; ++i) {
            n2 += (int)Math.floor(i + 300.0 * Math.pow(2.0, i / 7.0));
            if (i >= n) {
                return n3;
            }
            n3 = (int)Math.floor(n2 / 4);
        }
        return 0;
    }
    
    public static void Hovers(final TextDrawingArea[] array) {
        final RSInterface rsInterface = RSInterface.interfaceCache[3984];
        rsInterface.textDrawingAreas = array[2];
        rsInterface.message = "Total level %1";
        addSkillHovers(8654, 204);
        addSkillHovers(8655, 205);
        addSkillHovers(8656, 206);
        addSkillHovers(8657, 207);
        addSkillHovers(8658, 208);
        addSkillHovers(8659, 209);
        addSkillHovers(8660, 210);
        addSkillHovers(8661, 211);
        addSkillHovers(8662, 212);
        addSkillHovers(8663, 213);
        addSkillHovers(8664, 214);
        addSkillHovers(8665, 215);
        addSkillHovers(8666, 216);
        addSkillHovers(8667, 217);
        addSkillHovers(8668, 218);
        addSkillHovers(8669, 219);
        addSkillHovers(8670, 220);
        addSkillHovers(8671, 221);
        addSkillHovers(8672, 222);
        addSkillHovers(12162, 223);
        addSkillHovers(13928, 224);
        addSkillHovers(27153, 225);
        addSkillHovers(27154, 226);
        addSkillHovers(27155, 227);
        addSkillHovers(27156, 228);
    }
    
    public static void addSkillHovers(final int n, final int contentType) {
        final RSInterface addTabInterface = addTabInterface(n);
        addTabInterface.type = 10;
        addTabInterface.contentType = contentType;
        addTabInterface.message = null;
        addTabInterface.height = 27;
        addTabInterface.width = 60;
    }
    
    public static void pouches(final TextDrawingArea[] array) {
        addInterface(39700);
        addSprite(39701, 0, "Interfaces/Pouches/SPRITE");
        addHover(39702, 3, 0, 39703, 1, "Interfaces/Pouches/SPRITE", 17, 17, "Close Window");
        addHovered(39703, 2, "Interfaces/Pouches/SPRITE", 17, 17, 39704);
        addButton(39705, 3, "Interfaces/Pouches/SPRITE", "Pouches", 27640, 1, 116, 20);
        addButton(39706, 4, "Interfaces/Pouches/SPRITE", "Scrolls", 27640, 1, 116, 20);
        addText(39707, "Summoning Pouch Creation", array, 2, 16750623, false, true);
        final RSInterface addTabInterface = addTabInterface(39710);
        addTabInterface.width = 430;
        addTabInterface.height = 230;
        addTabInterface.scrollMax = 550;
        int n = 39710;
        int n2 = 4;
        for (int i = 0; i < 78; ++i) {
            addHover(n + 1, 1, 0, n + 2, n2 + 1, "Interfaces/Pouches/Sprite", 46, 50, "Infuse " + RSInterface.pouchNames[i] + " pouch");
            addHovered(n + 2, n2 + 2, "Interfaces/Pouches/Sprite", 46, 50, n + 3);
            n += 3;
            n2 += 2;
        }
    }
    
    public static void addSprite(final int n, final String s, final String tooltip, final int mOverInterToTrigger, final int width, final int height) {
        final RSInterface[] interfaceCache = RSInterface.interfaceCache;
        final RSInterface rsInterface = new RSInterface();
        interfaceCache[n] = rsInterface;
        final RSInterface rsInterface2 = rsInterface;
        rsInterface2.id = n;
        rsInterface2.parentID = n;
        rsInterface2.type = 5;
        rsInterface2.atActionType = 1;
        rsInterface2.contentType = -1;
        rsInterface2.aByte254 = 0;
        rsInterface2.mOverInterToTrigger = 52;
        rsInterface2.sprite1 = imageLoader(s);
        rsInterface2.sprite2 = imageLoader(s);
        rsInterface2.tooltip = tooltip;
        rsInterface2.mOverInterToTrigger = mOverInterToTrigger;
        rsInterface2.width = width;
        rsInterface2.height = height;
    }
    
    public static void addSprite(final int n, final String s) {
        final RSInterface[] interfaceCache = RSInterface.interfaceCache;
        final RSInterface rsInterface = new RSInterface();
        interfaceCache[n] = rsInterface;
        final RSInterface rsInterface2 = rsInterface;
        rsInterface2.id = n;
        rsInterface2.parentID = n;
        rsInterface2.type = 5;
        rsInterface2.atActionType = 1;
        rsInterface2.contentType = -1;
        rsInterface2.aByte254 = 0;
        rsInterface2.mOverInterToTrigger = 52;
        rsInterface2.sprite1 = imageLoader(s);
        rsInterface2.sprite2 = imageLoader(s);
    }
    
    public static void skillTab602(final TextDrawingArea[] array) {
        final RSInterface addInterface = addInterface(3917);
        addText(27203, "99", 16776960, false, true, -1, array, 0);
        addText(27204, "99", 16776960, false, true, -1, array, 0);
        addText(27205, "99", 16776960, false, true, -1, array, 0);
        addText(27206, "99", 16776960, false, true, -1, array, 0);
        addInterface.totalChildren(4);
        addInterface.child(0, 27203, 158, 175);
        addInterface.child(1, 27204, 171, 186);
        addInterface.child(2, 27205, 158, 203);
        addInterface.child(3, 27206, 171, 214);
        final String[] array2 = { "Attack", "HP", "Mine", "Strength", "Agility", "Smith", "Defence", "Herblore", "Fish", "Range", "Thief", "Cook", "Prayer", "Craft", "Fire", "Mage", "Fletch", "Wood", "Rune", "Slay", "Farm", "Construction", "Hunter", "Summon", "Dungeon" };
        final int[] array3 = { 8654, 8655, 8656, 8657, 8658, 8659, 8660, 8861, 8662, 8663, 8664, 8665, 8666, 8667, 8668, 8669, 8670, 8671, 8672, 12162, 13928, 27123, 27124, 27125, 27126 };
        final int[] array4 = { 4040, 4076, 4112, 4046, 4082, 4118, 4052, 4088, 4124, 4058, 4094, 4130, 4064, 4100, 4136, 4070, 4106, 4142, 4160, 2832, 13917, 19005, 19006, 19007, 19008 };
        final int[][] array5 = { { 4004, 4005 }, { 4016, 4017 }, { 4028, 4029 }, { 4006, 4007 }, { 4018, 4019 }, { 4030, 4031 }, { 4008, 4009 }, { 4020, 4021 }, { 4032, 4033 }, { 4010, 4011 }, { 4022, 4023 }, { 4034, 4035 }, { 4012, 4013 }, { 4024, 4025 }, { 4036, 4037 }, { 4014, 4015 }, { 4026, 4027 }, { 4038, 4039 }, { 4152, 4153 }, { 12166, 12167 }, { 13926, 13927 }, { 18165, 18169 }, { 18166, 18170 }, { 18167, 18171 }, { 18168, 18172 } };
        final int[] array6 = { 3965, 3966, 3967, 3968, 3969, 3970, 3971, 3972, 3973, 3974, 3975, 3976, 3977, 3978, 3979, 3980, 3981, 3982, 4151, 12165, 13925, 27127, 27128, 27129, 27130 };
        final int[][] array7 = { { 4, 4 }, { 66, 4 }, { 128, 4 }, { 4, 32 }, { 66, 32 }, { 128, 32 }, { 4, 60 }, { 66, 60 }, { 128, 60 }, { 4, 88 }, { 66, 88 }, { 128, 88 }, { 4, 116 }, { 66, 116 }, { 128, 116 }, { 4, 144 }, { 66, 144 }, { 128, 144 }, { 4, 172 }, { 66, 172 }, { 128, 172 }, { 4, 200 }, { 66, 200 }, { 128, 200 }, { 4, 229 } };
        final int[][] array8 = { { 6, 6 }, { 69, 7 }, { 131, 6 }, { 9, 34 }, { 68, 33 }, { 131, 36 }, { 9, 64 }, { 67, 63 }, { 131, 61 }, { 7, 91 }, { 68, 94 }, { 133, 90 }, { 6, 118 }, { 70, 120 }, { 130, 118 }, { 6, 147 }, { 69, 146 }, { 132, 146 }, { 6, 173 }, { 69, 173 }, { 130, 174 }, { 6, 202 }, { 69, 201 }, { 131, 202 }, { 6, 230 } };
        final int[][] array9 = { { 31, 7, 44, 18 }, { 93, 7, 106, 18 }, { 155, 7, 168, 18 }, { 31, 35, 44, 46 }, { 93, 35, 106, 46 }, { 155, 35, 168, 46 }, { 31, 63, 44, 74 }, { 93, 63, 106, 74 }, { 155, 63, 168, 74 }, { 31, 91, 44, 102 }, { 93, 91, 106, 102 }, { 155, 91, 168, 102 }, { 31, 119, 44, 130 }, { 93, 119, 106, 130 }, { 155, 119, 168, 130 }, { 31, 149, 44, 158 }, { 93, 147, 106, 158 }, { 155, 147, 168, 158 }, { 31, 175, 44, 186 }, { 93, 175, 106, 186 }, { 155, 175, 168, 186 }, { 31, 203, 44, 214 }, { 93, 203, 106, 214 }, { 155, 203, 168, 214 }, { 31, 231, 44, 242 } };
        final int[][] array10 = { { 18165, 18166, 18167, 18168 }, { 18169, 18170, 18171, 18172 } };
        for (int i = 0; i < array4.length; ++i) {
            createSkillHover(array4[i], 205 + i);
            addSkillButton(array3[i]);
            addImage(array6[i], array2[i]);
        }
        for (int j = 0; j < 4; ++j) {
            addSkillText(array10[0][j], false, j + 21);
            addSkillText(array10[1][j], true, j + 21);
        }
        addInterface.children(array6.length + array5.length * 2 + array4.length + array3.length + 1);
        int n = 0;
        final RSInterface rsInterface = RSInterface.interfaceCache[3984];
        rsInterface.message = "@yel@Total level: %1";
        rsInterface.textDrawingAreas = RSInterface.fonts[2];
        addInterface.child(n, 3984, 74, 237);
        ++n;
        for (int k = 0; k < array3.length; ++k) {
            addInterface.child(n, array3[k], array7[k][0], array7[k][1]);
            ++n;
        }
        for (int l = 0; l < array6.length; ++l) {
            addInterface.child(n, array6[l], array8[l][0], array8[l][1]);
            ++n;
        }
        for (int n2 = 0; n2 < array5.length; ++n2) {
            addInterface.child(n, array5[n2][0], array9[n2][0], array9[n2][1]);
            ++n;
        }
        for (int n3 = 0; n3 < array5.length; ++n3) {
            addInterface.child(n, array5[n3][1], array9[n3][2], array9[n3][3]);
            ++n;
        }
        for (int n4 = 0; n4 < array4.length; ++n4) {
            addInterface.child(n, array4[n4], array7[n4][0], array7[n4][1]);
            ++n;
        }
    }
    
    public void children(final int n) {
        this.children = new int[n];
        this.childX = new int[n];
        this.childY = new int[n];
    }
    
    public static void createSkillHover(final int n, final int contentType) {
        final RSInterface addInterface = addInterface(n);
        addInterface.type = 9;
        addInterface.message = "TESTING!";
        addInterface.contentType = contentType;
        addInterface.width = 60;
        addInterface.height = 28;
        addInterface.inventoryHover = true;
    }
    
    public static void addImage(final int n, final String s) {
        final RSInterface addInterface = addInterface(n);
        addInterface.type = 5;
        addInterface.atActionType = 0;
        addInterface.contentType = 0;
        addInterface.width = 100;
        addInterface.height = 100;
        addInterface.sprite1 = getSprite(s);
    }
    
    public static void addSkillText(final int n, final boolean b, final int n2) {
        final RSInterface addInterface = addInterface(n);
        addInterface.id = n;
        addInterface.parentID = n;
        addInterface.type = 4;
        addInterface.atActionType = 0;
        addInterface.width = 15;
        addInterface.height = 12;
        addInterface.textDrawingAreas = RSInterface.fonts[0];
        addInterface.textShadow = true;
        addInterface.centerText = true;
        addInterface.textColor = 16776960;
        if (!b) {
            addInterface.valueIndexArray = new int[1][];
            (addInterface.valueIndexArray[0] = new int[3])[0] = 1;
            addInterface.valueIndexArray[0][1] = n2;
            addInterface.valueIndexArray[0][2] = 0;
        }
        else {
            addInterface.valueIndexArray = new int[2][];
            (addInterface.valueIndexArray[0] = new int[3])[0] = 1;
            addInterface.valueIndexArray[0][1] = n2;
            addInterface.valueIndexArray[0][2] = 0;
            (addInterface.valueIndexArray[1] = new int[1])[0] = 0;
        }
        addInterface.message = "%1";
    }
    
    public static Sprite getSprite(final String s) {
        Sprite sprite;
        try {
            sprite = new Sprite(s);
            if (sprite != null) {
                return sprite;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return sprite;
    }
    
    public static void addSkillButton(final int n) {
        final RSInterface addInterface = addInterface(n);
        addInterface.type = 5;
        addInterface.atActionType = 5;
        addInterface.contentType = 0;
        addInterface.width = 60;
        addInterface.height = 27;
        addInterface.sprite1 = CustomSpriteLoader(33225, "");
        addInterface.sprite1 = getSprite("Button");
        addInterface.tooltip = "View";
    }
    
    private static void addHead2(final int n, final int width, final int height, final int modelZoom, final int modelRotation1, final int modelRotation2) {
        final RSInterface[] interfaceCache = RSInterface.interfaceCache;
        final RSInterface rsInterface = new RSInterface();
        interfaceCache[n] = rsInterface;
        final RSInterface rsInterface2 = rsInterface;
        rsInterface2.type = 6;
        rsInterface2.modelZoom = modelZoom;
        rsInterface2.modelRotation1 = modelRotation1;
        rsInterface2.modelRotation2 = modelRotation2;
        rsInterface2.height = height;
        rsInterface2.width = width;
    }
    
    public static void summoning(final TextDrawingArea[] array) {
        final RSInterface addTabInterface = addTabInterface(25605);
        addSprite(25606, 4, "Summon/SUMMON");
        addSprite(25607, 5, "Summon/SUMMON");
        addSprite(25608, 9, "Summon/SUMMON");
        addButton(25609, 1, "Summon/SUMMON", "Call");
        addButton(25619, 2, "Summon/SUMMON", "Renew");
        addButton(25610, 3, "Summon/SUMMON", "Dismiss");
        addSprite(25611, 7, "Summon/SUMMON");
        addText(25612, "99/99", array, 1, 12890228, false, true);
        addText(25616, "SA.", array, 1, 16748608, false, true);
        addText(25614, "Familiar", array, 2, 13408512, true, true);
        addText(25615, "99.99", array, 1, 12890228, false, true);
        addButton(25613, 8, "Summon/SUMMON", "Special Move");
        addText(25617, "Special Move 60/60", array, 1, 16777215, false, true);
        addSprite(25618, 11, "Summon/SUMMON");
        addHead2(25618, 50, 50, 2000, 40, 100);
        addTabInterface.totalChildren(14);
        addTabInterface.child(0, 25606, 11, 163);
        addTabInterface.child(1, 25607, 12, 141);
        addTabInterface.child(2, 25608, 9, 29);
        addTabInterface.child(3, 25609, 23, 211);
        addTabInterface.child(4, 25610, 121, 211);
        addTabInterface.child(5, 25611, 20, 10);
        addTabInterface.child(6, 25612, 29, 194);
        addTabInterface.child(7, 25613, 9, 29);
        addTabInterface.child(8, 25614, 97, 144);
        addTabInterface.child(9, 25615, 127, 194);
        addTabInterface.child(10, 25616, 20, 63);
        addTabInterface.child(11, 25617, 35, 10);
        addTabInterface.child(12, 25618, 70, 59);
        addTabInterface.child(13, 25619, 72, 211);
    }
    
    public static void GodWars(final TextDrawingArea[] array) {
        final RSInterface addTabInterface = addTabInterface(16210);
        addText(16211, "    Godwars killcount", array, 1, 16748608, true, true);
        addText(16212, "    Armadyl kills", array, 1, 16748608, true, true);
        addText(16213, "    Bandos kills", array, 1, 16748608, true, true);
        addText(16214, "    Saradomin kills", array, 1, 16748608, true, true);
        addText(16215, "    Zamorak kills", array, 1, 16748608, true, true);
        addText(16216, "    0", array, 1, 6750207, true, true);
        addText(16217, "    0", array, 1, 6750207, true, true);
        addText(16218, "    0", array, 1, 6750207, true, true);
        addText(16219, "    0", array, 1, 6750207, true, true);
        addTabInterface.scrollMax = 0;
        addTabInterface.children = new int[9];
        addTabInterface.childX = new int[9];
        addTabInterface.childY = new int[9];
        addTabInterface.children[0] = 16211;
        addTabInterface.childX[0] = 443;
        addTabInterface.childY[0] = 7;
        addTabInterface.children[1] = 16212;
        addTabInterface.childX[1] = 422;
        addTabInterface.childY[1] = 30;
        addTabInterface.children[2] = 16213;
        addTabInterface.childX[2] = 420;
        addTabInterface.childY[2] = 44;
        addTabInterface.children[3] = 16214;
        addTabInterface.childX[3] = 429;
        addTabInterface.childY[3] = 58;
        addTabInterface.children[4] = 16215;
        addTabInterface.childX[4] = 423;
        addTabInterface.childY[4] = 73;
        addTabInterface.children[5] = 16216;
        addTabInterface.childX[5] = 503;
        addTabInterface.childY[5] = 31;
        addTabInterface.children[6] = 16217;
        addTabInterface.childX[6] = 503;
        addTabInterface.childY[6] = 45;
        addTabInterface.children[7] = 16218;
        addTabInterface.childX[7] = 503;
        addTabInterface.childY[7] = 59;
        addTabInterface.children[8] = 16219;
        addTabInterface.childX[8] = 503;
        addTabInterface.childY[8] = 74;
    }
    
    public static void BH(final TextDrawingArea[] array) {
        final RSInterface addTabInterface = addTabInterface(16250);
        addText(16251, "Bounty Hunter", array, 1, 16711680, true, true);
        addText(16252, "Target:", array, 1, 16711680, true, true);
        addText(16253, " Penalty:", array, 1, 16711680, true, true);
        addText(16254, "", array, 1, 16753920, true, true);
        addText(16255, "", array, 1, 16753920, true, true);
        addText(16256, "Dexter's Mom", array, 1, 16753920, true, true);
        addText(16257, "Till you die.", array, 1, 16753920, true, true);
        addText(16258, "0", array, 1, 16753920, true, true);
        addText(16259, "0", array, 1, 16753920, true, true);
        addTabInterface.scrollMax = 0;
        addTabInterface.children = new int[9];
        addTabInterface.childX = new int[9];
        addTabInterface.childY = new int[9];
        addTabInterface.children[0] = 16251;
        addTabInterface.childX[0] = 443;
        addTabInterface.childY[0] = 7;
        addTabInterface.children[1] = 16252;
        addTabInterface.childX[1] = 422;
        addTabInterface.childY[1] = 30;
        addTabInterface.children[2] = 16253;
        addTabInterface.childX[2] = 420;
        addTabInterface.childY[2] = 44;
        addTabInterface.children[3] = 16254;
        addTabInterface.childX[3] = 429;
        addTabInterface.childY[3] = 58;
        addTabInterface.children[4] = 16255;
        addTabInterface.childX[4] = 423;
        addTabInterface.childY[4] = 73;
        addTabInterface.children[5] = 16256;
        addTabInterface.childX[5] = 503;
        addTabInterface.childY[5] = 31;
        addTabInterface.children[6] = 16257;
        addTabInterface.childX[6] = 503;
        addTabInterface.childY[6] = 45;
        addTabInterface.children[7] = 16258;
        addTabInterface.childX[7] = 503;
        addTabInterface.childY[7] = 59;
        addTabInterface.children[8] = 16259;
        addTabInterface.childX[8] = 503;
        addTabInterface.childY[8] = 74;
    }
    
    public static void magicTab(final TextDrawingArea[] array) {
        final RSInterface addTabInterface = addTabInterface(1151);
        final RSInterface addTabInterface2 = addTabInterface(1196);
        final RSInterface rsInterface = RSInterface.interfaceCache[12424];
        rsInterface.scrollMax = 0;
        rsInterface.height = 260;
        rsInterface.width = 190;
        final int[] array2 = { 1196, 1199, 1206, 1215, 1224, 1231, 1240, 1249, 1258, 1267, 1274, 1283, 1573, 1290, 1299, 1308, 1315, 1324, 1333, 1340, 1349, 1358, 1367, 1374, 1381, 1388, 1397, 1404, 1583, 12038, 1414, 1421, 1430, 1437, 1446, 1453, 1460, 1469, 15878, 1602, 1613, 1624, 7456, 1478, 1485, 1494, 1503, 1512, 1521, 1530, 1544, 1553, 1563, 1593, 1635, 12426, 12436, 12446, 12456, 6004, 18471 };
        addTabInterface.totalChildren(63);
        addTabInterface.child(0, 12424, 13, 24);
        for (int i = 0; i < array2.length; ++i) {
            final int n = (i > 34) ? 8 : 183;
            addTabInterface.child(1, 1195, 13, 24);
            addTabInterface.child(i + 2, array2[i], 5, n);
            addButton(1195, 1, "Magic/Home", "Cast @gre@Home Teleport");
            RSInterface.interfaceCache[1195].mOverInterToTrigger = 1196;
        }
        for (int j = 0; j < array2.length; ++j) {
            if (j < 60) {
                rsInterface.childX[j] += 24;
            }
            if (j == 6 || j == 12 || j == 19 || j == 35 || j == 41 || j == 44 || j == 49 || j == 51) {
                rsInterface.childX[j] = 0;
            }
            rsInterface.childY[6] = 24;
            rsInterface.childY[12] = 48;
            rsInterface.childY[19] = 72;
            rsInterface.childY[49] = 96;
            rsInterface.childY[44] = 120;
            rsInterface.childY[51] = 144;
            rsInterface.childY[35] = 170;
            rsInterface.childY[41] = 192;
        }
        addText(1197, "Level 0: Home Teleport", array, 1, 16685087, addTabInterface2.isMouseoverTriggered = true, true);
        final RSInterface rsInterface2 = RSInterface.interfaceCache[1197];
        rsInterface2.width = 174;
        rsInterface2.height = 68;
        addText(1198, "A teleport which requires no", array, 0, 11495962, true, true);
        addText(18998, "runes and no required level that", array, 0, 11495962, true, true);
        addText(18999, "teleports you to the main land.", array, 0, 11495962, true, true);
        addTabInterface2.totalChildren(4);
        addTabInterface2.child(0, 1197, 3, 4);
        addTabInterface2.child(1, 1198, 91, 23);
        addTabInterface2.child(2, 18998, 91, 34);
        addTabInterface2.child(3, 18999, 91, 45);
    }
    
    public static void ancientMagicTab(final TextDrawingArea[] array) {
        final RSInterface addInterface = addInterface(12855);
        addButton(12856, 1, "Magic/Home", "Cast @gre@Home Teleport");
        RSInterface.interfaceCache[12856].mOverInterToTrigger = 1196;
        final int[] array2 = { 12856, 12939, 12987, 13035, 12901, 12861, 13045, 12963, 13011, 13053, 12919, 12881, 13061, 12951, 12999, 13069, 12911, 12871, 13079, 12975, 13023, 13087, 12929, 12891, 13095, 1196, 12940, 12988, 13036, 12902, 12862, 13046, 12964, 13012, 13054, 12920, 12882, 13062, 12952, 13000, 13070, 12912, 12872, 13080, 12976, 13024, 13088, 12930, 12892, 13096 };
        addInterface.totalChildren(array2.length);
        int i = 0;
        int n = 18;
        int n2 = 8;
        while (i < array2.length) {
            if (n > 175) {
                n = 18;
                n2 += 28;
            }
            if (i < 25) {
                addInterface.child(i, array2[i], n, n2);
            }
            if (i > 24) {
                n2 = ((i < 41) ? 181 : 1);
                addInterface.child(i, array2[i], 4, n2);
            }
            ++i;
            n += 45;
        }
    }
    
    public static void drawBlackBox(final int n, final int n2) {
        DrawingArea.drawPixels(71, n2 - 1, n - 2, 7496785, 1);
        DrawingArea.drawPixels(69, n2, n + 174, 7496785, 1);
        DrawingArea.drawPixels(1, n2 - 2, n - 2, 7496785, 178);
        DrawingArea.drawPixels(1, n2 + 68, n, 7496785, 174);
        DrawingArea.drawPixels(71, n2 - 1, n - 1, 3025699, 1);
        DrawingArea.drawPixels(71, n2 - 1, n + 175, 3025699, 1);
        DrawingArea.drawPixels(1, n2 - 1, n, 3025699, 175);
        DrawingArea.drawPixels(1, n2 + 69, n, 3025699, 175);
        DrawingArea.method335(0, n2, 174, 68, 220, n);
    }
    
    public static void addButton(final int n, final int n2, final String s, final String tooltip, final int mOverInterToTrigger, final int atActionType, final int width, final int height) {
        final RSInterface[] interfaceCache = RSInterface.interfaceCache;
        final RSInterface rsInterface = new RSInterface();
        interfaceCache[n] = rsInterface;
        final RSInterface rsInterface2 = rsInterface;
        rsInterface2.id = n;
        rsInterface2.parentID = n;
        rsInterface2.type = 5;
        rsInterface2.atActionType = atActionType;
        rsInterface2.contentType = 0;
        rsInterface2.aByte254 = 0;
        rsInterface2.mOverInterToTrigger = mOverInterToTrigger;
        rsInterface2.sprite1 = imageLoader(n2, s);
        rsInterface2.sprite2 = imageLoader(n2, s);
        rsInterface2.width = width;
        rsInterface2.height = height;
        rsInterface2.tooltip = tooltip;
        rsInterface2.inventoryhover = true;
    }
    
    public static void addButton(final int n, final int n2, final String s, final String tooltip, final int atActionType) {
        final RSInterface[] interfaceCache = RSInterface.interfaceCache;
        final RSInterface rsInterface = new RSInterface();
        interfaceCache[n] = rsInterface;
        final RSInterface rsInterface2 = rsInterface;
        rsInterface2.id = n;
        rsInterface2.parentID = n;
        rsInterface2.type = 5;
        rsInterface2.atActionType = atActionType;
        rsInterface2.contentType = 0;
        rsInterface2.aByte254 = 0;
        rsInterface2.mOverInterToTrigger = 52;
        rsInterface2.sprite1 = imageLoader(n2, s);
        rsInterface2.sprite2 = imageLoader(n2, s);
        rsInterface2.width = rsInterface2.sprite1.myWidth;
        rsInterface2.height = rsInterface2.sprite1.myHeight;
        rsInterface2.tooltip = tooltip;
    }
    
    public static void addButton(final int n, final int n2, final int mOverInterToTrigger, final String s, final int width, final int height, final String tooltip, final int atActionType) {
        final RSInterface addInterface = addInterface(n);
        addInterface.id = n;
        addInterface.parentID = n;
        addInterface.type = 5;
        addInterface.atActionType = atActionType;
        addInterface.aByte254 = 0;
        addInterface.mOverInterToTrigger = mOverInterToTrigger;
        addInterface.sprite1 = imageLoader(n2, s);
        addInterface.sprite2 = imageLoader(n2, s);
        addInterface.width = width;
        addInterface.height = height;
        addInterface.tooltip = tooltip;
    }
    
    public static void addButton(final int n, final int n2, final String s, final String tooltip) {
        final RSInterface[] interfaceCache = RSInterface.interfaceCache;
        final RSInterface rsInterface = new RSInterface();
        interfaceCache[n] = rsInterface;
        final RSInterface rsInterface2 = rsInterface;
        rsInterface2.id = n;
        rsInterface2.parentID = n;
        rsInterface2.type = 5;
        rsInterface2.atActionType = 1;
        rsInterface2.contentType = 0;
        rsInterface2.aByte254 = 0;
        rsInterface2.mOverInterToTrigger = 52;
        rsInterface2.sprite1 = imageLoader(n2, s);
        rsInterface2.sprite2 = imageLoader(n2, s);
        rsInterface2.width = rsInterface2.sprite1.myWidth;
        rsInterface2.height = rsInterface2.sprite2.myHeight;
        rsInterface2.tooltip = tooltip;
    }
    
    public static void curseTab(final TextDrawingArea[] array) {
    }
    
    public static void prayerTab(final TextDrawingArea[] array) {
        final RSInterface addTabInterface = addTabInterface(5608);
        final RSInterface rsInterface = RSInterface.interfaceCache[687];
        rsInterface.textColor = 16750623;
        rsInterface.textShadow = true;
        addText(687, "99/99", 16750623, false, false, -1, array, 1);
        addSprite(5651, 0, "Prayer/PRAYER");
        addPrayer(18000, 0, 601, 7, 0, "Sharp Eye");
        addPrayer(18002, 0, 602, 8, 1, "Mystic Will");
        addPrayer(18004, 0, 603, 25, 2, "Hawk Eye");
        addPrayer(18006, 0, 604, 26, 3, "Mystic Lore");
        addPrayer(18008, 0, 605, 43, 4, "Eagle Eye");
        addPrayer(18010, 0, 606, 44, 5, "Mystic Might");
        addPrayer(18012, 0, 607, 59, 6, "Chivalry");
        addPrayer(18014, 0, 608, 69, 7, "Piety");
        addTabInterface.totalChildren(54);
        addTabInterface.child(0, 5609, 8, 4);
        addTabInterface.child(1, 5610, 44, 4);
        addTabInterface.child(2, 5611, 80, 4);
        addTabInterface.child(3, 5612, 8, 40);
        addTabInterface.child(4, 5613, 44, 40);
        addTabInterface.child(5, 5614, 80, 40);
        addTabInterface.child(6, 5615, 116, 40);
        addTabInterface.child(7, 5616, 152, 40);
        addTabInterface.child(8, 5617, 8, 76);
        addTabInterface.child(9, 5618, 116, 76);
        addTabInterface.child(10, 5619, 152, 76);
        addTabInterface.child(11, 5620, 8, 112);
        addTabInterface.child(12, 5621, 44, 112);
        addTabInterface.child(13, 5622, 80, 112);
        addTabInterface.child(14, 5623, 116, 112);
        addTabInterface.child(15, 683, 44, 148);
        addTabInterface.child(16, 684, 80, 148);
        addTabInterface.child(17, 685, 116, 148);
        addTabInterface.child(18, 5632, 10, 6);
        addTabInterface.child(19, 5633, 46, 6);
        addTabInterface.child(20, 5634, 82, 6);
        addTabInterface.child(21, 5635, 10, 42);
        addTabInterface.child(22, 5636, 46, 42);
        addTabInterface.child(23, 5637, 82, 42);
        addTabInterface.child(24, 5638, 118, 42);
        addTabInterface.child(25, 5639, 154, 42);
        addTabInterface.child(26, 5640, 10, 79);
        addTabInterface.child(27, 5641, 118, 78);
        addTabInterface.child(28, 5642, 154, 78);
        addTabInterface.child(29, 5643, 10, 114);
        addTabInterface.child(30, 5644, 46, 114);
        addTabInterface.child(31, 686, 82, 114);
        addTabInterface.child(32, 5645, 118, 114);
        addTabInterface.child(33, 5649, 46, 150);
        addTabInterface.child(34, 5647, 82, 150);
        addTabInterface.child(35, 5648, 118, 150);
        addTabInterface.child(36, 18000, 116, 4);
        addTabInterface.child(37, 18001, 119, 8);
        addTabInterface.child(38, 18002, 152, 4);
        addTabInterface.child(39, 18003, 155, 7);
        addTabInterface.child(40, 18004, 44, 76);
        addTabInterface.child(41, 18005, 47, 80);
        addTabInterface.child(42, 18006, 80, 76);
        addTabInterface.child(43, 18007, 83, 79);
        addTabInterface.child(44, 18008, 152, 112);
        addTabInterface.child(45, 18009, 155, 116);
        addTabInterface.child(46, 18010, 8, 148);
        addTabInterface.child(47, 18011, 11, 151);
        addTabInterface.child(48, 18012, 152, 148);
        addTabInterface.child(49, 18013, 159, 151);
        addTabInterface.child(50, 18014, 8, 184);
        addTabInterface.child(51, 18015, 10, 194);
        addTabInterface.child(52, 5651, 64, 240);
        addTabInterface.child(53, 687, 84, 241);
        final String[] array2 = { "Thick Skin", "Burst of Strength", "Clarity of Thought", "Rock Skin", "Superhuman Strength", "Improved Reflexes", "Rapid Restore", "Rapid Heal", "Protect Item", "Steel Skin", "Ultimate Strength", "Incredible Reflexes", "Protect from Magic", "Protect from Range", "Protect from Melee", "Retribution", "Redemption", "Smite" };
        int n = 0;
        for (int i = 5609; i <= 5623; ++i) {
            RSInterface.interfaceCache[i].tooltip = "Activate @or2@" + array2[n++];
        }
        for (int j = 683; j <= 685; ++j) {
            RSInterface.interfaceCache[j].tooltip = "Activate @or2@" + array2[n++];
        }
    }
    
    public static void addTooltipBox(final int n, final String popupString) {
        final RSInterface addInterface = addInterface(n);
        addInterface.id = n;
        addInterface.parentID = n;
        addInterface.type = 8;
        addInterface.popupString = popupString;
    }
    
    public static void addTooltip(final int id, final String s) {
        final RSInterface addInterface = addInterface(id);
        addInterface.id = id;
        addInterface.type = 0;
        addInterface.isMouseoverTriggered = true;
        addInterface.mOverInterToTrigger = -1;
        addTooltipBox(id + 1, s);
        addInterface.totalChildren(1);
        addInterface.child(0, id + 1, 0, 0);
    }
    
    public static void addPrayer(final int id, final int n, final int n2, final int n3, final int n4, final String s) {
        final RSInterface addTabInterface = addTabInterface(id);
        addTabInterface.id = id;
        addTabInterface.parentID = 5608;
        addTabInterface.type = 5;
        addTabInterface.atActionType = 4;
        addTabInterface.contentType = 0;
        addTabInterface.aByte254 = 0;
        addTabInterface.mOverInterToTrigger = -1;
        addTabInterface.sprite1 = imageLoader(0, "PRAYERGLOW");
        addTabInterface.sprite2 = imageLoader(1, "PRAYERGLOW");
        addTabInterface.width = 34;
        addTabInterface.height = 34;
        addTabInterface.anIntArray245 = new int[1];
        addTabInterface.anIntArray212 = new int[1];
        addTabInterface.anIntArray245[0] = 1;
        addTabInterface.anIntArray212[0] = n;
        addTabInterface.valueIndexArray = new int[1][3];
        addTabInterface.valueIndexArray[0][0] = 5;
        addTabInterface.valueIndexArray[0][1] = n2;
        addTabInterface.valueIndexArray[0][2] = 0;
        addTabInterface.tooltip = "Activate@or2@ " + s;
        final RSInterface addTabInterface2 = addTabInterface(id + 1);
        addTabInterface2.id = id + 1;
        addTabInterface2.parentID = 5608;
        addTabInterface2.type = 5;
        addTabInterface2.atActionType = 0;
        addTabInterface2.contentType = 0;
        addTabInterface2.aByte254 = 0;
        addTabInterface2.mOverInterToTrigger = -1;
        addTabInterface2.sprite1 = imageLoader(n4, "/PRAYER/PRAYON");
        addTabInterface2.sprite2 = imageLoader(n4, "/PRAYER/PRAYOFF");
        addTabInterface2.width = 34;
        addTabInterface2.height = 34;
        addTabInterface2.anIntArray245 = new int[1];
        addTabInterface2.anIntArray212 = new int[1];
        addTabInterface2.anIntArray245[0] = 2;
        addTabInterface2.anIntArray212[0] = n3 + 1;
        addTabInterface2.valueIndexArray = new int[1][3];
        addTabInterface2.valueIndexArray[0][0] = 2;
        addTabInterface2.valueIndexArray[0][1] = 5;
        addTabInterface2.valueIndexArray[0][2] = 0;
    }
    
    public static void friendsTab(final TextDrawingArea[] array) {
        final RSInterface addTabInterface = addTabInterface(5065);
        final RSInterface rsInterface = RSInterface.interfaceCache[5066];
        addText(5067, "Friends List", array, 1, 16750899, true, true);
        addText(5070, "Add Friend", array, 0, 16750899, false, true);
        addText(5071, "Delete Friend", array, 0, 16750899, false, true);
        addSprite(16126, 4, "/Friends/SPRITE");
        addSprite(16127, 8, "/Friends/SPRITE");
        addHoverButton(5068, "/Friends/SPRITE", 6, 72, 32, "Add Friend", 201, 5072, 1);
        addHoveredButton(5072, "/Friends/SPRITE", 7, 72, 32, 5073);
        addHoverButton(5069, "/Friends/SPRITE", 6, 72, 32, "Delete Friend", 202, 5074, 1);
        addHoveredButton(5074, "/Friends/SPRITE", 7, 72, 32, 5075);
        addTabInterface.totalChildren(11);
        addTabInterface.child(0, 5067, 95, 4);
        addTabInterface.child(1, 16127, 0, 25);
        addTabInterface.child(2, 16126, 0, 221);
        addTabInterface.child(3, 5066, 0, 24);
        addTabInterface.child(4, 16126, 0, 22);
        addTabInterface.child(5, 5068, 15, 226);
        addTabInterface.child(6, 5072, 15, 226);
        addTabInterface.child(7, 5069, 103, 226);
        addTabInterface.child(8, 5074, 103, 226);
        addTabInterface.child(9, 5070, 25, 237);
        addTabInterface.child(10, 5071, 106, 237);
        rsInterface.height = 196;
        rsInterface.width = 174;
        for (int n = 5092, n2 = 0; n <= 5191 && n2 <= 99; ++n, ++n2) {
            rsInterface.children[n2] = n;
            rsInterface.childX[n2] = 3;
            rsInterface.childY[n2] -= 7;
        }
        for (int n3 = 5192, n4 = 100; n3 <= 5291 && n4 <= 199; ++n3, ++n4) {
            rsInterface.children[n4] = n3;
            rsInterface.childX[n4] = 131;
            rsInterface.childY[n4] -= 7;
        }
    }
    
    public static void ignoreTab(final TextDrawingArea[] array) {
        final RSInterface addTabInterface = addTabInterface(5715);
        final RSInterface rsInterface = RSInterface.interfaceCache[5716];
        addText(5717, "Ignore List", array, 1, 16750899, true, true);
        addText(5720, "Add Name", array, 0, 16750899, false, true);
        addText(5721, "Delete Name", array, 0, 16750899, false, true);
        addHoverButton(5718, "/Friends/SPRITE", 6, 72, 32, "Add Name", 501, 5722, 1);
        addHoveredButton(5722, "/Friends/SPRITE", 7, 72, 32, 5723);
        addHoverButton(5719, "/Friends/SPRITE", 6, 72, 32, "Delete Name", 502, 5724, 1);
        addHoveredButton(5724, "/Friends/SPRITE", 7, 72, 32, 5725);
        addTabInterface.totalChildren(11);
        addTabInterface.child(0, 5717, 95, 4);
        addTabInterface.child(1, 16127, 0, 25);
        addTabInterface.child(2, 16126, 0, 221);
        addTabInterface.child(3, 5716, 0, 24);
        addTabInterface.child(4, 16126, 0, 22);
        addTabInterface.child(5, 5718, 15, 226);
        addTabInterface.child(6, 5722, 15, 226);
        addTabInterface.child(7, 5719, 103, 226);
        addTabInterface.child(8, 5724, 103, 226);
        addTabInterface.child(9, 5720, 27, 237);
        addTabInterface.child(10, 5721, 108, 237);
        rsInterface.height = 196;
        rsInterface.width = 174;
        for (int n = 5742, n2 = 0; n <= 5841 && n2 <= 99; ++n, ++n2) {
            rsInterface.children[n2] = n;
            rsInterface.childX[n2] = 3;
            rsInterface.childY[n2] -= 7;
        }
    }
    
    private static Sprite CustomSpriteLoader(final int n, final String s) {
        final long n2 = (TextClass.method585(s) << 8) + n;
        final Sprite sprite = (Sprite)RSInterface.aMRUNodes_238.insertFromCache(n2);
        if (sprite != null) {
            return sprite;
        }
        Sprite sprite2;
        try {
            sprite2 = new Sprite("/Attack/" + n + s);
            RSInterface.aMRUNodes_238.removeFromCache(sprite2, n2);
        }
        catch (Exception ex) {
            return null;
        }
        return sprite2;
    }
    
    public static RSInterface addInterface(final int n) {
        final RSInterface[] interfaceCache = RSInterface.interfaceCache;
        final RSInterface rsInterface = new RSInterface();
        interfaceCache[n] = rsInterface;
        final RSInterface rsInterface2 = rsInterface;
        rsInterface2.id = n;
        rsInterface2.parentID = n;
        rsInterface2.width = 512;
        rsInterface2.height = 334;
        return rsInterface2;
    }
    
    public static void addText(final int id, final String message, final TextDrawingArea[] array, final int n, final int textColor, final boolean b) {
        final RSInterface[] interfaceCache = RSInterface.interfaceCache;
        final RSInterface rsInterface = new RSInterface();
        interfaceCache[id] = rsInterface;
        final RSInterface rsInterface2 = rsInterface;
        if (b) {
            rsInterface2.centerText = true;
        }
        rsInterface2.textShadow = true;
        rsInterface2.textDrawingAreas = array[n];
        rsInterface2.message = message;
        rsInterface2.textColor = textColor;
        rsInterface2.id = id;
        rsInterface2.type = 4;
    }
    
    public static void textColor(final int n, final int textColor) {
        RSInterface.interfaceCache[n].textColor = textColor;
    }
    
    public static void textSize(final int n, final TextDrawingArea[] array, final int n2) {
        RSInterface.interfaceCache[n].textDrawingAreas = array[n2];
    }
    
    public static void addCacheSprite(final int n, final int n2, final int n3, final String s) {
        final RSInterface[] interfaceCache = RSInterface.interfaceCache;
        final RSInterface rsInterface = new RSInterface();
        interfaceCache[n] = rsInterface;
        final RSInterface rsInterface2 = rsInterface;
        rsInterface2.sprite1 = method207(n2, RSInterface.aClass44, s);
        rsInterface2.sprite2 = method207(n3, RSInterface.aClass44, s);
        rsInterface2.parentID = n;
        rsInterface2.id = n;
        rsInterface2.type = 5;
    }
    
    public static void sprite1(final int n, final int n2) {
        RSInterface.interfaceCache[n].sprite1 = CustomSpriteLoader(n2, "");
    }
    
    public static void addActionButton(final int n, final int n2, final int n3, final int width, final int height, final String tooltip) {
        final RSInterface[] interfaceCache = RSInterface.interfaceCache;
        final RSInterface rsInterface = new RSInterface();
        interfaceCache[n] = rsInterface;
        final RSInterface rsInterface2 = rsInterface;
        rsInterface2.sprite1 = CustomSpriteLoader(n2, "");
        if (n3 == n2) {
            rsInterface2.sprite2 = CustomSpriteLoader(n2, "a");
        }
        else {
            rsInterface2.sprite2 = CustomSpriteLoader(n3, "");
        }
        rsInterface2.tooltip = tooltip;
        rsInterface2.contentType = 0;
        rsInterface2.atActionType = 1;
        rsInterface2.width = width;
        rsInterface2.mOverInterToTrigger = 52;
        rsInterface2.parentID = n;
        rsInterface2.id = n;
        rsInterface2.type = 5;
        rsInterface2.height = height;
    }
    
    public static void addToggleButton(final int n, final int n2, final int n3, final int width, final int height, final String tooltip) {
        final RSInterface addInterface = addInterface(n);
        addInterface.sprite1 = CustomSpriteLoader(n2, "");
        addInterface.sprite2 = CustomSpriteLoader(n2, "a");
        (addInterface.anIntArray212 = new int[1])[0] = 1;
        (addInterface.anIntArray245 = new int[1])[0] = 1;
        addInterface.valueIndexArray = new int[1][3];
        addInterface.valueIndexArray[0][0] = 5;
        addInterface.valueIndexArray[0][1] = n3;
        addInterface.valueIndexArray[0][2] = 0;
        addInterface.atActionType = 4;
        addInterface.width = width;
        addInterface.mOverInterToTrigger = -1;
        addInterface.parentID = n;
        addInterface.id = n;
        addInterface.type = 5;
        addInterface.height = height;
        addInterface.tooltip = tooltip;
    }
    
    public void totalChildren(final int n, final int n2, final int n3) {
        this.children = new int[n];
        this.childX = new int[n2];
        this.childY = new int[n3];
    }
    
    public static void removeSomething(final int n) {
        RSInterface.interfaceCache[n] = new RSInterface();
    }
    
    public void specialBar(final int n) {
        addActionButton(n - 12, 7587, -1, 150, 26, "Use @gre@Special Attack");
        for (int i = n - 11; i < n; ++i) {
            removeSomething(i);
        }
        final RSInterface rsInterface = RSInterface.interfaceCache[n - 12];
        rsInterface.width = 150;
        rsInterface.height = 26;
        final RSInterface rsInterface2 = RSInterface.interfaceCache[n];
        rsInterface2.width = 150;
        rsInterface2.height = 26;
        rsInterface2.child(0, n - 12, 0, 0);
        rsInterface2.child(12, n + 1, 3, 7);
        rsInterface2.child(23, n + 12, 16, 8);
        for (int j = 13; j < 23; ++j) {
            final int[] childY = rsInterface2.childY;
            final int n2 = j;
            --childY[n2];
        }
        final RSInterface rsInterface3 = RSInterface.interfaceCache[n + 1];
        rsInterface3.type = 5;
        rsInterface3.sprite1 = CustomSpriteLoader(7600, "");
        for (int k = n + 2; k < n + 12; ++k) {
            RSInterface.interfaceCache[k].type = 5;
        }
        sprite1(n + 2, 7601);
        sprite1(n + 3, 7602);
        sprite1(n + 4, 7603);
        sprite1(n + 5, 7604);
        sprite1(n + 6, 7605);
        sprite1(n + 7, 7606);
        sprite1(n + 8, 7607);
        sprite1(n + 9, 7608);
        sprite1(n + 10, 7609);
        sprite1(n + 11, 7610);
    }
    
    public static void Sidebar0(final TextDrawingArea[] array) {
        Sidebar0a(1698, 1701, 7499, "Chop", "Hack", "Smash", "Block", 42, 75, 127, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, array);
        Sidebar0a(2276, 2279, 7574, "Stab", "Lunge", "Slash", "Block", 43, 75, 124, 75, 41, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, array);
        Sidebar0a(2423, 2426, 7599, "Chop", "Slash", "Lunge", "Block", 42, 75, 125, 75, 40, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, array);
        Sidebar0a(3796, 3799, 7624, "Pound", "Pummel", "Spike", "Block", 39, 75, 121, 75, 41, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, array);
        Sidebar0a(4679, 4682, 7674, "Lunge", "Swipe", "Pound", "Block", 40, 75, 124, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, array);
        Sidebar0a(4705, 4708, 7699, "Chop", "Slash", "Smash", "Block", 42, 75, 125, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, array);
        Sidebar0a(5570, 5573, 7724, "Spike", "Impale", "Smash", "Block", 41, 75, 123, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, array);
        Sidebar0a(7762, 7765, 7800, "Chop", "Slash", "Lunge", "Block", 42, 75, 125, 75, 40, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, array);
        Sidebar0b(776, 779, "Reap", "Chop", "Jab", "Block", 42, 75, 126, 75, 46, 128, 125, 128, 122, 103, 122, 50, 40, 103, 40, 50, array);
        Sidebar0c(425, 428, 7474, "Pound", "Pummel", "Block", 39, 75, 121, 75, 42, 128, 40, 103, 40, 50, 122, 50, array);
        Sidebar0c(1749, 1752, 7524, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, array);
        Sidebar0c(1764, 1767, 7549, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, array);
        Sidebar0c(4446, 4449, 7649, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, array);
        Sidebar0c(5855, 5857, 7749, "Punch", "Kick", "Block", 40, 75, 129, 75, 42, 128, 40, 50, 122, 50, 40, 103, array);
        Sidebar0c(6103, 6132, 6117, "Bash", "Pound", "Block", 43, 75, 124, 75, 42, 128, 40, 103, 40, 50, 122, 50, array);
        Sidebar0c(8460, 8463, 8493, "Jab", "Swipe", "Fend", 46, 75, 124, 75, 43, 128, 40, 103, 40, 50, 122, 50, array);
        Sidebar0c(12290, 12293, 12323, "Flick", "Lash", "Deflect", 44, 75, 127, 75, 36, 128, 40, 50, 40, 103, 122, 50, array);
        Sidebar0d(328, 331, "Bash", "Pound", "Focus", 42, 66, 39, 101, 41, 136, 40, 120, 40, 50, 40, 85, array);
        final RSInterface addInterface = addInterface(19300);
        textSize(3983, array, 0);
        addToggleButton(150, 150, 172, 150, 44, "Auto Retaliate");
        addInterface.totalChildren(2, 2, 2);
        addInterface.child(0, 3983, 52, 25);
        addInterface.child(1, 150, 21, 153);
        final RSInterface rsInterface = RSInterface.interfaceCache[3983];
        rsInterface.centerText = true;
        rsInterface.textColor = 16750623;
    }
    
    public static void Sidebar0a(final int n, final int n2, final int n3, final String s, final String s2, final String s3, final String s4, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13, final int n14, final int n15, final int n16, final int n17, final int n18, final int n19, final TextDrawingArea[] array) {
        final RSInterface addInterface = addInterface(n);
        addText(n2, "-2", array, 3, 16750623, true);
        addText(n2 + 11, s, array, 0, 16750623, false);
        addText(n2 + 12, s2, array, 0, 16750623, false);
        addText(n2 + 13, s3, array, 0, 16750623, false);
        addText(n2 + 14, s4, array, 0, 16750623, false);
        addInterface.specialBar(n3);
        addInterface.width = 190;
        addInterface.height = 261;
        final int n20 = 15;
        int n21 = 0;
        addInterface.totalChildren(n20, n20, n20);
        addInterface.child(n21, n2 + 3, 21, 46);
        ++n21;
        addInterface.child(n21, n2 + 4, 104, 99);
        ++n21;
        addInterface.child(n21, n2 + 5, 21, 99);
        ++n21;
        addInterface.child(n21, n2 + 6, 105, 46);
        ++n21;
        addInterface.child(n21, n2 + 7, n12, n13);
        ++n21;
        addInterface.child(n21, n2 + 8, n14, n15);
        ++n21;
        addInterface.child(n21, n2 + 9, n16, n17);
        ++n21;
        addInterface.child(n21, n2 + 10, n18, n19);
        ++n21;
        addInterface.child(n21, n2 + 11, n4, n5);
        ++n21;
        addInterface.child(n21, n2 + 12, n6, n7);
        ++n21;
        addInterface.child(n21, n2 + 13, n8, n9);
        ++n21;
        addInterface.child(n21, n2 + 14, n10, n11);
        ++n21;
        addInterface.child(n21, 19300, 0, 0);
        ++n21;
        addInterface.child(n21, n2, 94, 4);
        ++n21;
        addInterface.child(n21, n3, 21, 205);
        ++n21;
        for (int i = n2 + 3; i < n2 + 7; ++i) {
            final RSInterface rsInterface = RSInterface.interfaceCache[i];
            rsInterface.sprite1 = CustomSpriteLoader(19301, "");
            rsInterface.sprite2 = CustomSpriteLoader(19301, "a");
            rsInterface.width = 68;
            rsInterface.height = 44;
        }
    }
    
    public static void Sidebar0b(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13, final int n14, final int n15, final int n16, final int n17, final int n18, final TextDrawingArea[] array) {
        final RSInterface addInterface = addInterface(n);
        addText(n2, "-2", array, 3, 16750623, true);
        addText(n2 + 11, s, array, 0, 16750623, false);
        addText(n2 + 12, s2, array, 0, 16750623, false);
        addText(n2 + 13, s3, array, 0, 16750623, false);
        addText(n2 + 14, s4, array, 0, 16750623, false);
        addInterface.width = 190;
        addInterface.height = 261;
        final int n19 = 14;
        int n20 = 0;
        addInterface.totalChildren(n19, n19, n19);
        addInterface.child(n20, n2 + 3, 21, 46);
        ++n20;
        addInterface.child(n20, n2 + 4, 104, 99);
        ++n20;
        addInterface.child(n20, n2 + 5, 21, 99);
        ++n20;
        addInterface.child(n20, n2 + 6, 105, 46);
        ++n20;
        addInterface.child(n20, n2 + 7, n11, n12);
        ++n20;
        addInterface.child(n20, n2 + 8, n13, n14);
        ++n20;
        addInterface.child(n20, n2 + 9, n15, n16);
        ++n20;
        addInterface.child(n20, n2 + 10, n17, n18);
        ++n20;
        addInterface.child(n20, n2 + 11, n3, n4);
        ++n20;
        addInterface.child(n20, n2 + 12, n5, n6);
        ++n20;
        addInterface.child(n20, n2 + 13, n7, n8);
        ++n20;
        addInterface.child(n20, n2 + 14, n9, n10);
        ++n20;
        addInterface.child(n20, 19300, 0, 0);
        ++n20;
        addInterface.child(n20, n2, 94, 4);
        ++n20;
        for (int i = n2 + 3; i < n2 + 7; ++i) {
            final RSInterface rsInterface = RSInterface.interfaceCache[i];
            rsInterface.sprite1 = CustomSpriteLoader(19301, "");
            rsInterface.sprite2 = CustomSpriteLoader(19301, "a");
            rsInterface.width = 68;
            rsInterface.height = 44;
        }
    }
    
    public static void Sidebar0c(final int n, final int n2, final int n3, final String s, final String s2, final String s3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13, final int n14, final int n15, final TextDrawingArea[] array) {
        final RSInterface addInterface = addInterface(n);
        addText(n2, "-2", array, 3, 16750623, true);
        addText(n2 + 9, s, array, 0, 16750623, false);
        addText(n2 + 10, s2, array, 0, 16750623, false);
        addText(n2 + 11, s3, array, 0, 16750623, false);
        addInterface.specialBar(n3);
        addInterface.width = 190;
        addInterface.height = 261;
        final int n16 = 12;
        int n17 = 0;
        addInterface.totalChildren(n16, n16, n16);
        addInterface.child(n17, n2 + 3, 21, 99);
        ++n17;
        addInterface.child(n17, n2 + 4, 105, 46);
        ++n17;
        addInterface.child(n17, n2 + 5, 21, 46);
        ++n17;
        addInterface.child(n17, n2 + 6, n10, n11);
        ++n17;
        addInterface.child(n17, n2 + 7, n12, n13);
        ++n17;
        addInterface.child(n17, n2 + 8, n14, n15);
        ++n17;
        addInterface.child(n17, n2 + 9, n4, n5);
        ++n17;
        addInterface.child(n17, n2 + 10, n6, n7);
        ++n17;
        addInterface.child(n17, n2 + 11, n8, n9);
        ++n17;
        addInterface.child(n17, 19300, 0, 0);
        ++n17;
        addInterface.child(n17, n2, 94, 4);
        ++n17;
        addInterface.child(n17, n3, 21, 205);
        ++n17;
        for (int i = n2 + 3; i < n2 + 6; ++i) {
            final RSInterface rsInterface = RSInterface.interfaceCache[i];
            rsInterface.sprite1 = CustomSpriteLoader(19301, "");
            rsInterface.sprite2 = CustomSpriteLoader(19301, "a");
            rsInterface.width = 68;
            rsInterface.height = 44;
        }
    }
    
    public static void Sidebar0d(final int n, final int n2, final String s, final String s2, final String s3, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13, final int n14, final TextDrawingArea[] array) {
        final RSInterface addInterface = addInterface(n);
        addText(n2, "-2", array, 3, 16750623, true);
        addText(n2 + 9, s, array, 0, 16750623, false);
        addText(n2 + 10, s2, array, 0, 16750623, false);
        addText(n2 + 11, s3, array, 0, 16750623, false);
        addText(353, "Spell", array, 0, 16750623, false);
        addText(354, "Spell", array, 0, 16750623, false);
        addCacheSprite(337, 19, 0, "combaticons");
        addCacheSprite(338, 13, 0, "combaticons2");
        addCacheSprite(339, 14, 0, "combaticons2");
        addToggleButton(349, 349, 109, 68, 44, "Select");
        addToggleButton(350, 350, 108, 68, 44, "Select");
        addInterface.width = 190;
        addInterface.height = 261;
        final int n15 = 15;
        int n16 = 0;
        addInterface.totalChildren(n15, n15, n15);
        addInterface.child(n16, n2 + 3, 20, 115);
        ++n16;
        addInterface.child(n16, n2 + 4, 20, 80);
        ++n16;
        addInterface.child(n16, n2 + 5, 20, 45);
        ++n16;
        addInterface.child(n16, n2 + 6, n9, n10);
        ++n16;
        addInterface.child(n16, n2 + 7, n11, n12);
        ++n16;
        addInterface.child(n16, n2 + 8, n13, n14);
        ++n16;
        addInterface.child(n16, n2 + 9, n3, n4);
        ++n16;
        addInterface.child(n16, n2 + 10, n5, n6);
        ++n16;
        addInterface.child(n16, n2 + 11, n7, n8);
        ++n16;
        addInterface.child(n16, 349, 105, 46);
        ++n16;
        addInterface.child(n16, 350, 104, 106);
        ++n16;
        addInterface.child(n16, 353, 125, 74);
        ++n16;
        addInterface.child(n16, 354, 125, 134);
        ++n16;
        addInterface.child(n16, 19300, 0, 0);
        ++n16;
        addInterface.child(n16, n2, 94, 4);
        ++n16;
    }
    
    public static void addClickableText(final int n, final String message, final String tooltip, final TextDrawingArea[] array, final int n2, final int textColor, final boolean centerText, final boolean textShadow, final int width) {
        final RSInterface addTabInterface = addTabInterface(n);
        addTabInterface.parentID = n;
        addTabInterface.id = n;
        addTabInterface.type = 4;
        addTabInterface.atActionType = 1;
        addTabInterface.width = width;
        addTabInterface.height = 11;
        addTabInterface.contentType = 0;
        addTabInterface.aByte254 = 0;
        addTabInterface.mOverInterToTrigger = -1;
        addTabInterface.centerText = centerText;
        addTabInterface.textShadow = textShadow;
        addTabInterface.textDrawingAreas = array[n2];
        addTabInterface.message = message;
        addTabInterface.aString228 = "";
        addTabInterface.textColor = textColor;
        addTabInterface.anInt219 = 0;
        addTabInterface.anInt216 = 16777215;
        addTabInterface.anInt239 = 0;
        addTabInterface.tooltip = tooltip;
    }
    
    public static void questTab(final TextDrawingArea[] array) {
        final RSInterface addTabInterface = addTabInterface(638);
        final RSInterface addTabInterface2 = addTabInterface(16025);
        addText(640, "Information & Options", array, 2, 15439903, false, true);
        addText(663, "", array, 2, 16750848, false, true);
        addSprite(16022, 4, "SPRITE");
        addText(16023, "Website: Devilishpkz.org", array, 0, 15439903, false, true);
        addSprite(16024, 5, "SPRITE");
        addTabInterface.totalChildren(6);
        addTabInterface.child(0, 640, 5, 5);
        addTabInterface.child(1, 16024, 0, 25);
        addTabInterface.child(2, 16025, 6, 24);
        addTabInterface.child(3, 16022, 0, 22);
        addTabInterface.child(4, 16022, 0, 249);
        addTabInterface.child(5, 16023, 4, 251);
        for (int i = 16026; i <= 16125; ++i) {
            addClickableText(i, "", "Show", array, 0, 16711680, false, true, 150);
        }
        addTabInterface2.totalChildren(101);
        addTabInterface2.child(0, 663, 4, 2);
        for (int n = 1, n2 = 16026; n <= 100 && n2 <= 16125; ++n, ++n2) {
            addTabInterface2.childY[1] = 18;
            addTabInterface2.child(n, n2, 9, addTabInterface2.childY[n - 1] + 13);
        }
        addTabInterface2.width = 168;
        addTabInterface2.height = 225;
        addTabInterface2.scrollMax = 1320;
    }
    
    public static void emoteTab() {
        final RSInterface addTabInterface = addTabInterface(147);
        final RSInterface addTabInterface2 = addTabInterface(148);
        addTabInterface.totalChildren(1);
        addTabInterface.child(0, 148, 0, 1);
        addButton(168, 0, "/Emotes/EMOTE", "Yes", 41, 47);
        addButton(169, 1, "/Emotes/EMOTE", "No", 41, 47);
        addButton(164, 2, "/Emotes/EMOTE", "Bow", 41, 47);
        addButton(165, 3, "/Emotes/EMOTE", "Angry", 41, 47);
        addButton(162, 4, "/Emotes/EMOTE", "Think", 41, 47);
        addButton(163, 5, "/Emotes/EMOTE", "Wave", 41, 47);
        addButton(13370, 6, "/Emotes/EMOTE", "Shrug", 41, 47);
        addButton(171, 7, "/Emotes/EMOTE", "Cheer", 41, 47);
        addButton(167, 8, "/Emotes/EMOTE", "Beckon", 41, 47);
        addButton(170, 9, "/Emotes/EMOTE", "Laugh", 41, 47);
        addButton(13366, 10, "/Emotes/EMOTE", "Jump for Joy", 41, 47);
        addButton(13368, 11, "/Emotes/EMOTE", "Yawn", 41, 47);
        addButton(166, 12, "/Emotes/EMOTE", "Dance", 41, 47);
        addButton(13363, 13, "/Emotes/EMOTE", "Jig", 41, 47);
        addButton(13364, 14, "/Emotes/EMOTE", "Spin", 41, 47);
        addButton(13365, 15, "/Emotes/EMOTE", "Headbang", 41, 47);
        addButton(161, 16, "/Emotes/EMOTE", "Cry", 41, 47);
        addButton(11100, 17, "/Emotes/EMOTE", "Blow kiss", 41, 47);
        addButton(13362, 18, "/Emotes/EMOTE", "Panic", 41, 47);
        addButton(13367, 19, "/Emotes/EMOTE", "Raspberry", 41, 47);
        addButton(172, 20, "/Emotes/EMOTE", "Clap", 41, 47);
        addButton(13369, 21, "/Emotes/EMOTE", "Salute", 41, 47);
        addButton(13383, 22, "/Emotes/EMOTE", "Goblin Bow", 41, 47);
        addButton(13384, 23, "/Emotes/EMOTE", "Goblin Salute", 41, 47);
        addButton(667, 24, "/Emotes/EMOTE", "Glass Box", 41, 47);
        addButton(6503, 25, "/Emotes/EMOTE", "Climb Rope", 41, 47);
        addButton(6506, 26, "/Emotes/EMOTE", "Lean On Air", 41, 47);
        addButton(666, 27, "/Emotes/EMOTE", "Glass Wall", 41, 47);
        addButton(18464, 28, "/Emotes/EMOTE", "Zombie Walk", 41, 47);
        addButton(18465, 29, "/Emotes/EMOTE", "Zombie Dance", 41, 47);
        addButton(15166, 30, "/Emotes/EMOTE", "Scared", 41, 47);
        addButton(18686, 31, "/Emotes/EMOTE", "Rabbit Hop", 41, 47);
        addConfigButton(154, 147, 32, 33, "/Emotes/EMOTE", 41, 47, "Skillcape Emote", 0, 1, 700);
        addTabInterface2.totalChildren(33);
        addTabInterface2.child(0, 168, 10, 7);
        addTabInterface2.child(1, 169, 54, 7);
        addTabInterface2.child(2, 164, 98, 14);
        addTabInterface2.child(3, 165, 137, 7);
        addTabInterface2.child(4, 162, 9, 56);
        addTabInterface2.child(5, 163, 48, 56);
        addTabInterface2.child(6, 13370, 95, 56);
        addTabInterface2.child(7, 171, 137, 56);
        addTabInterface2.child(8, 167, 7, 105);
        addTabInterface2.child(9, 170, 51, 105);
        addTabInterface2.child(10, 13366, 95, 104);
        addTabInterface2.child(11, 13368, 139, 105);
        addTabInterface2.child(12, 166, 6, 154);
        addTabInterface2.child(13, 13363, 50, 154);
        addTabInterface2.child(14, 13364, 90, 154);
        addTabInterface2.child(15, 13365, 135, 154);
        addTabInterface2.child(16, 161, 8, 204);
        addTabInterface2.child(17, 11100, 51, 203);
        addTabInterface2.child(18, 13362, 99, 204);
        addTabInterface2.child(19, 13367, 137, 203);
        addTabInterface2.child(20, 172, 10, 253);
        addTabInterface2.child(21, 13369, 53, 253);
        addTabInterface2.child(22, 13383, 88, 258);
        addTabInterface2.child(23, 13384, 138, 252);
        addTabInterface2.child(24, 667, 2, 303);
        addTabInterface2.child(25, 6503, 49, 302);
        addTabInterface2.child(26, 6506, 93, 302);
        addTabInterface2.child(27, 666, 137, 302);
        addTabInterface2.child(28, 18464, 9, 352);
        addTabInterface2.child(29, 18465, 50, 352);
        addTabInterface2.child(30, 15166, 94, 356);
        addTabInterface2.child(31, 18686, 141, 353);
        addTabInterface2.child(32, 154, 5, 401);
        addTabInterface2.width = 173;
        addTabInterface2.height = 258;
        addTabInterface2.scrollMax = 450;
    }
    
    public static void settings(final TextDrawingArea[] array) {
        final RSInterface addTabInterface = addTabInterface(20999);
        addSprite(23000, 0, "/Settings/BACKGROUND");
        addButton(23001, 0, "/Settings/SCREEN", "Fullscreen", 54, 46);
        addButton(23002, 1, "/Settings/SCREEN", "Resizable", 54, 46);
        addButton(23003, 2, "/Settings/SCREEN", "Fixed", 54, 46);
        addButton(23004, 8, "/Equipment/CUSTOM", "Close", 21, 21);
        addText(23005, "Settings", array, 2, 16751360, true, true);
        addButton(23006, 0, "/Settings/CHECK", "Toggle", 13, 13);
        addClickableText(23007, "New Gameframe", "Toggle", array, 1, 16777215, false, true, array[1].getTextWidth("New gameframe"));
        addButton(23008, 0, "/Settings/CHECK", "Toggle", 13, 13);
        addClickableText(23009, "New Hitmarks", "Toggle", array, 1, 16777215, false, true, array[1].getTextWidth("New Hitmarks"));
        addButton(23010, 0, "/Settings/CHECK", "Toggle", 13, 13);
        addClickableText(23011, "10x Hits", "Toggle", array, 1, 16777215, false, true, array[1].getTextWidth("10x Hits"));
        addTabInterface.totalChildren(12);
        addTabInterface.child(0, 23000, 16, 15);
        addTabInterface.child(1, 23001, 40, 88);
        addTabInterface.child(2, 23002, 40, 149);
        addTabInterface.child(3, 23003, 40, 210);
        addTabInterface.child(4, 23004, 467, 30);
        addTabInterface.child(5, 23005, 260, 40);
        addTabInterface.child(6, 23006, 145, 100);
        addTabInterface.child(7, 23007, 160, 100);
        addTabInterface.child(8, 23008, 145, 115);
        addTabInterface.child(9, 23009, 160, 115);
        addTabInterface.child(10, 23010, 145, 130);
        addTabInterface.child(11, 23011, 160, 130);
    }
    
    public static void optionTab(final TextDrawingArea[] array) {
        final RSInterface addTabInterface = addTabInterface(904);
        RSInterface.interfaceCache[149].textColor = 16750899;
        addSprite(905, 9, "/Options/SPRITE");
        addSprite(907, 18, "/Options/SPRITE");
        addSprite(909, 29, "/Options/SPRITE");
        addSprite(951, 32, "/Options/SPRITE");
        addSprite(953, 33, "/Options/SPRITE");
        addSprite(955, 34, "/Options/SPRITE");
        addSprite(947, 36, "/Options/SPRITE");
        addSprite(949, 35, "/Options/SPRITE");
        addConfigButton(152, 904, 30, 31, "/Options/SPRITE", 40, 40, "Toggle-run", 1, 5, 173);
        addConfigButton(906, 904, 10, 14, "/Options/SPRITE", 32, 16, "Dark", 1, 5, 166);
        addConfigButton(908, 904, 11, 15, "/Options/SPRITE", 32, 16, "Normal", 2, 5, 166);
        addConfigButton(910, 904, 12, 16, "/Options/SPRITE", 32, 16, "Bright", 3, 5, 166);
        addConfigButton(912, 904, 13, 17, "/Options/SPRITE", 32, 16, "Very Bright", 4, 5, 166);
        addConfigButton(930, 904, 19, 24, "/Options/SPRITE", 26, 16, "Music Off", 4, 5, 168);
        addConfigButton(931, 904, 20, 25, "/Options/SPRITE", 26, 16, "Music Level-1", 3, 5, 168);
        addConfigButton(932, 904, 21, 26, "/Options/SPRITE", 26, 16, "Music Level-2", 2, 5, 168);
        addConfigButton(933, 904, 22, 27, "/Options/SPRITE", 26, 16, "Music Level-3", 1, 5, 168);
        addConfigButton(934, 904, 23, 28, "/Options/SPRITE", 24, 16, "Music Level-4", 0, 5, 168);
        addConfigButton(941, 904, 19, 24, "/Options/SPRITE", 26, 16, "Sound Effects Off", 4, 5, 169);
        addConfigButton(942, 904, 20, 25, "/Options/SPRITE", 26, 16, "Sound Effects Level-1", 3, 5, 169);
        addConfigButton(943, 904, 21, 26, "/Options/SPRITE", 26, 16, "Sound Effects Level-2", 2, 5, 169);
        addConfigButton(944, 904, 22, 27, "/Options/SPRITE", 26, 16, "Sound Effects Level-3", 1, 5, 169);
        addConfigButton(945, 904, 23, 28, "/Options/SPRITE", 24, 16, "Sound Effects Level-4", 0, 5, 169);
        addConfigButton(913, 904, 30, 31, "/Options/SPRITE", 40, 40, "Toggle-Mouse Buttons", 0, 5, 170);
        addConfigButton(915, 904, 30, 31, "/Options/SPRITE", 40, 40, "Toggle-Chat Effects", 0, 5, 171);
        addConfigButton(957, 904, 30, 31, "/Options/SPRITE", 40, 40, "Toggle-Split Private Chat", 1, 5, 287);
        addConfigButton(12464, 904, 30, 31, "/Options/SPRITE", 40, 40, "Toggle-Accept Aid", 0, 5, 427);
        addButton(18699, 31, "/Options/OPTION", "Change Screen Size", 30, 173);
        addTabInterface.totalChildren(29);
        final int n = 0;
        final int n2 = 2;
        addTabInterface.child(0, 905, 13 + n, 10 + n2);
        addTabInterface.child(1, 906, 48 + n, 18 + n2);
        addTabInterface.child(2, 908, 80 + n, 18 + n2);
        addTabInterface.child(3, 910, 112 + n, 18 + n2);
        addTabInterface.child(4, 912, 144 + n, 18 + n2);
        addTabInterface.child(5, 907, 14 + n, 55 + n2);
        addTabInterface.child(6, 930, 49 + n, 61 + n2);
        addTabInterface.child(7, 931, 75 + n, 61 + n2);
        addTabInterface.child(8, 932, 101 + n, 61 + n2);
        addTabInterface.child(9, 933, 127 + n, 61 + n2);
        addTabInterface.child(10, 934, 151 + n, 61 + n2);
        addTabInterface.child(11, 909, 13 + n, 99 + n2);
        addTabInterface.child(12, 941, 49 + n, 104 + n2);
        addTabInterface.child(13, 942, 75 + n, 104 + n2);
        addTabInterface.child(14, 943, 101 + n, 104 + n2);
        addTabInterface.child(15, 944, 127 + n, 104 + n2);
        addTabInterface.child(16, 945, 151 + n, 104 + n2);
        addTabInterface.child(17, 913, 15, 153);
        addTabInterface.child(18, 955, 19, 159);
        addTabInterface.child(19, 915, 75, 153);
        addTabInterface.child(20, 953, 79, 160);
        addTabInterface.child(21, 957, 135, 153);
        addTabInterface.child(22, 951, 139, 159);
        addTabInterface.child(23, 12464, 15, 208);
        addTabInterface.child(24, 949, 20, 213);
        addTabInterface.child(25, 152, 75, 208);
        addTabInterface.child(26, 947, 87, 212);
        addTabInterface.child(27, 149, 80, 231);
        addTabInterface.child(28, 18699, 135, 208);
    }
    
    public static void clanChatTab(final TextDrawingArea[] array) {
        final RSInterface addTabInterface = addTabInterface(18128);
        addHoverButton(18129, "/Clan Chat/SPRITE", 6, 72, 32, "Join Chat", 550, 18130, 1);
        addHoveredButton(18130, "/Clan Chat/SPRITE", 7, 72, 32, 18131);
        addHoverButton(18132, "/Clan Chat/SPRITE", 6, 72, 32, "Leave Chat", -1, 18133, 5);
        addHoveredButton(18133, "/Clan Chat/SPRITE", 7, 72, 32, 18134);
        addButton(18250, 0, "/Clan Chat/Lootshare", "Toggle lootshare");
        addText(18135, "Join Chat", array, 0, 16751360, true, true);
        addText(18136, "Leave Chat", array, 0, 16751360, true, true);
        addSprite(18137, 37, "/Clan Chat/SPRITE");
        addText(18138, "Clan Chat", array, 1, 16751360, true, true);
        addText(18139, "Talking in: Not in chat", array, 0, 16751360, false, true);
        addText(18140, "Owner: None", array, 0, 16751360, false, true);
        addTabInterface.totalChildren(14);
        addTabInterface.child(0, 16126, 0, 221);
        addTabInterface.child(1, 16126, 0, 59);
        addTabInterface.child(2, 18137, 0, 62);
        addTabInterface.child(3, 18143, 0, 62);
        addTabInterface.child(4, 18129, 15, 226);
        addTabInterface.child(5, 18130, 15, 226);
        addTabInterface.child(6, 18132, 103, 226);
        addTabInterface.child(7, 18133, 103, 226);
        addTabInterface.child(8, 18135, 51, 237);
        addTabInterface.child(9, 18136, 139, 237);
        addTabInterface.child(10, 18138, 95, 3);
        addTabInterface.child(11, 18139, 10, 23);
        addTabInterface.child(12, 18140, 25, 38);
        addTabInterface.child(13, 18250, 145, 15);
        final RSInterface addTabInterface2 = addTabInterface(18143);
        addTabInterface2.totalChildren(100);
        for (int i = 18144; i <= 18244; ++i) {
            addText(i, "", array, 0, 16777215, false, true);
        }
        for (int n = 18144, n2 = 0; n <= 18243 && n2 <= 99; ++n, ++n2) {
            addTabInterface2.children[n2] = n;
            addTabInterface2.childX[n2] = 5;
            for (int n3 = 18144, n4 = 1; n3 <= 18243 && n4 <= 99; ++n3, ++n4) {
                addTabInterface2.childY[0] = 2;
                addTabInterface2.childY[n4] = addTabInterface2.childY[n4 - 1] + 14;
            }
        }
        addTabInterface2.height = 158;
        addTabInterface2.width = 174;
        addTabInterface2.scrollMax = 1405;
    }
    
    public static void addText(final int n, final String message, final int textColor, final boolean centerText, final boolean textShadow, final int mOverInterToTrigger, final TextDrawingArea[] array, final int n2) {
        final RSInterface addInterface = addInterface(n);
        addInterface.parentID = n;
        addInterface.id = n;
        addInterface.type = 4;
        addInterface.atActionType = 0;
        addInterface.width = 0;
        addInterface.height = 0;
        addInterface.contentType = 0;
        addInterface.aByte254 = 0;
        addInterface.mOverInterToTrigger = mOverInterToTrigger;
        addInterface.centerText = centerText;
        addInterface.textShadow = textShadow;
        addInterface.textDrawingAreas = array[n2];
        addInterface.message = message;
        addInterface.aString228 = "";
        addInterface.textColor = textColor;
    }
    
    public static void Bank() {
        final RSInterface addTabInterface = addTabInterface(5292);
        setChildren(19, addTabInterface);
        addSprite(5293, 0, "Bank/BANK");
        setBounds(5293, 13, 13, 0, addTabInterface);
        addHover(5384, 3, 0, 5380, 1, "Bank/BANK", 17, 17, "Close Window");
        addHovered(5380, 2, "Bank/BANK", 17, 17, 5379);
        setBounds(5384, 476, 16, 3, addTabInterface);
        setBounds(5380, 476, 16, 4, addTabInterface);
        addHover(5294, 4, 0, 5295, 3, "Bank/BANK", 114, 25, "Set A Bank PIN");
        addHovered(5295, 4, "Bank/BANK", 114, 25, 5296);
        setBounds(5294, 110, 285, 5, addTabInterface);
        setBounds(5295, 110, 285, 6, addTabInterface);
        addBankHover(21000, 4, 21001, 5, 8, "Bank/BANK", 35, 25, 304, 1, "Swap Withdraw Mode", 21002, 7, 6, "Bank/BANK", 21003, "Switch to insert items \nmode", "Switch to swap items \nmode.", 12, 20);
        setBounds(21000, 25, 285, 7, addTabInterface);
        setBounds(21001, 10, 225, 8, addTabInterface);
        addBankHover(21004, 4, 21005, 13, 15, "Bank/BANK", 35, 25, 0, 1, "Search", 21006, 14, 16, "Bank/BANK", 21007, "Click here to search your \nbank", "Click here to search your \nbank", 12, 20);
        setBounds(21004, 65, 285, 9, addTabInterface);
        setBounds(21005, 50, 225, 10, addTabInterface);
        addBankHover(21008, 4, 21009, 9, 11, "Bank/BANK", 35, 25, 115, 1, "Search", 21010, 10, 12, "Bank/BANK", 21011, "Switch to note withdrawal \nmode", "Switch to item withdrawal \nmode", 12, 20);
        setBounds(21008, 240, 285, 11, addTabInterface);
        setBounds(21009, 225, 225, 12, addTabInterface);
        addBankHover1(21012, 5, 21013, 17, "Bank/BANK", 35, 25, "Deposit carried tems", 21014, 18, "Bank/BANK", 21015, "Empty your backpack into\nyour bank", 0, 20);
        setBounds(21012, 375, 285, 13, addTabInterface);
        setBounds(21013, 360, 225, 14, addTabInterface);
        addBankHover1(21016, 5, 21017, 19, "Bank/BANK", 35, 25, "Deposit worn items", 21018, 20, "Bank/BANK", 21019, "Empty the items your are\nwearing into your bank", 0, 20);
        setBounds(21016, 415, 285, 15, addTabInterface);
        setBounds(21017, 400, 225, 16, addTabInterface);
        addBankHover1(21020, 5, 21021, 21, "Bank/BANK", 35, 25, "Deposit beast of burden inventory.", 21022, 22, "Bank/BANK", 21023, "Empty your BoB's inventory\ninto your bank", 0, 20);
        setBounds(21020, 455, 285, 17, addTabInterface);
        setBounds(21021, 440, 225, 18, addTabInterface);
        setBounds(5383, 170, 15, 1, addTabInterface);
        setBounds(5385, -4, 74, 2, addTabInterface);
        final RSInterface rsInterface = RSInterface.interfaceCache[5385];
        rsInterface.height = 206;
        rsInterface.width = 480;
        final RSInterface rsInterface2 = RSInterface.interfaceCache[5382];
        rsInterface2.width = 10;
        rsInterface2.invSpritePadX = 12;
        rsInterface2.height = 35;
    }
    
    public static void addBankHover(final int n, final int atActionType, final int id, final int n2, final int n3, final String s, final int width, final int height, final int n4, final int n5, final String tooltip, final int n6, final int n7, final int n8, final String s2, final int n9, final String s3, final String s4, final int n10, final int n11) {
        final RSInterface addTabInterface = addTabInterface(n);
        addTabInterface.id = n;
        addTabInterface.parentID = n;
        addTabInterface.type = 5;
        addTabInterface.atActionType = atActionType;
        addTabInterface.contentType = 0;
        addTabInterface.aByte254 = 0;
        addTabInterface.mOverInterToTrigger = id;
        addTabInterface.sprite1 = imageLoader(n2, s);
        addTabInterface.sprite2 = imageLoader(n3, s);
        addTabInterface.width = width;
        addTabInterface.tooltip = tooltip;
        addTabInterface.height = height;
        addTabInterface.anIntArray245 = new int[1];
        addTabInterface.anIntArray212 = new int[1];
        addTabInterface.anIntArray245[0] = 1;
        addTabInterface.anIntArray212[0] = n5;
        addTabInterface.valueIndexArray = new int[1][3];
        addTabInterface.valueIndexArray[0][0] = 5;
        addTabInterface.valueIndexArray[0][1] = n4;
        addTabInterface.valueIndexArray[0][2] = 0;
        final RSInterface addTabInterface2 = addTabInterface(id);
        addTabInterface2.parentID = id;
        addTabInterface2.id = id;
        addTabInterface2.type = 0;
        addTabInterface2.atActionType = 0;
        addTabInterface2.width = 550;
        addTabInterface2.height = 334;
        addTabInterface2.isMouseoverTriggered = true;
        addTabInterface2.mOverInterToTrigger = -1;
        addSprite(n6, n7, n8, s2, n5, n4);
        addHoverBox(n9, n, s3, s4, n5, n4);
        setChildren(2, addTabInterface2);
        setBounds(n6, 15, 60, 0, addTabInterface2);
        setBounds(n9, n10, n11, 1, addTabInterface2);
    }
    
    public static void addBankHover1(final int n, final int atActionType, final int id, final int n2, final String s, final int width, final int height, final String tooltip, final int n3, final int n4, final String s2, final int n5, final String s3, final int n6, final int n7) {
        final RSInterface addTabInterface = addTabInterface(n);
        addTabInterface.id = n;
        addTabInterface.parentID = n;
        addTabInterface.type = 5;
        addTabInterface.atActionType = atActionType;
        addTabInterface.contentType = 0;
        addTabInterface.aByte254 = 0;
        addTabInterface.mOverInterToTrigger = id;
        addTabInterface.sprite1 = imageLoader(n2, s);
        addTabInterface.width = width;
        addTabInterface.tooltip = tooltip;
        addTabInterface.height = height;
        final RSInterface addTabInterface2 = addTabInterface(id);
        addTabInterface2.parentID = id;
        addTabInterface2.id = id;
        addTabInterface2.type = 0;
        addTabInterface2.atActionType = 0;
        addTabInterface2.width = 550;
        addTabInterface2.height = 334;
        addTabInterface2.isMouseoverTriggered = true;
        addTabInterface2.mOverInterToTrigger = -1;
        addSprite(n3, n4, n4, s2, 0, 0);
        addHoverBox(n5, n, s3, s3, 0, 0);
        setChildren(2, addTabInterface2);
        setBounds(n3, 15, 60, 0, addTabInterface2);
        setBounds(n5, n6, n7, 1, addTabInterface2);
    }
    
    public static void addHoverBox(final int id, final int parentID, final String aString228, final String message, final int n, final int n2) {
        final RSInterface addTabInterface = addTabInterface(id);
        addTabInterface.id = id;
        addTabInterface.parentID = parentID;
        addTabInterface.type = 8;
        addTabInterface.aString228 = aString228;
        addTabInterface.message = message;
        addTabInterface.anIntArray245 = new int[1];
        addTabInterface.anIntArray212 = new int[1];
        addTabInterface.anIntArray245[0] = 1;
        addTabInterface.anIntArray212[0] = n;
        addTabInterface.valueIndexArray = new int[1][3];
        addTabInterface.valueIndexArray[0][0] = 5;
        addTabInterface.valueIndexArray[0][1] = n2;
        addTabInterface.valueIndexArray[0][2] = 0;
    }
    
    public static void addSprite(final int n, final int n2, final int n3, final String s, final int n4, final int n5) {
        final RSInterface addTabInterface = addTabInterface(n);
        addTabInterface.id = n;
        addTabInterface.parentID = n;
        addTabInterface.type = 5;
        addTabInterface.atActionType = 0;
        addTabInterface.contentType = 0;
        addTabInterface.width = 512;
        addTabInterface.height = 334;
        addTabInterface.aByte254 = 0;
        addTabInterface.mOverInterToTrigger = -1;
        addTabInterface.anIntArray245 = new int[1];
        addTabInterface.anIntArray212 = new int[1];
        addTabInterface.anIntArray245[0] = 1;
        addTabInterface.anIntArray212[0] = n4;
        addTabInterface.valueIndexArray = new int[1][3];
        addTabInterface.valueIndexArray[0][0] = 5;
        addTabInterface.valueIndexArray[0][1] = n5;
        addTabInterface.valueIndexArray[0][2] = 0;
        addTabInterface.sprite1 = imageLoader(n2, s);
        addTabInterface.sprite2 = imageLoader(n3, s);
    }
    
    public static void addHover(final int n, final int atActionType, final int contentType, final int mOverInterToTrigger, final int n2, final String s, final int width, final int height, final String tooltip) {
        final RSInterface addTabInterface = addTabInterface(n);
        addTabInterface.id = n;
        addTabInterface.parentID = n;
        addTabInterface.type = 5;
        addTabInterface.atActionType = atActionType;
        addTabInterface.contentType = contentType;
        addTabInterface.mOverInterToTrigger = mOverInterToTrigger;
        addTabInterface.sprite1 = imageLoader(n2, s);
        addTabInterface.sprite2 = imageLoader(n2, s);
        addTabInterface.width = width;
        addTabInterface.height = height;
        addTabInterface.tooltip = tooltip;
    }
    
    public static void addHovered(final int n, final int n2, final String s, final int width, final int height, final int n3) {
        final RSInterface addTabInterface = addTabInterface(n);
        addTabInterface.parentID = n;
        addTabInterface.id = n;
        addTabInterface.type = 0;
        addTabInterface.atActionType = 0;
        addTabInterface.width = width;
        addTabInterface.height = height;
        addTabInterface.isMouseoverTriggered = true;
        addTabInterface.mOverInterToTrigger = -1;
        addSprite(n3, n2, s);
        setChildren(1, addTabInterface);
        setBounds(n3, 0, 0, 0, addTabInterface);
    }
    
    public static void setBounds(final int n, final int n2, final int n3, final int n4, final RSInterface rsInterface) {
        rsInterface.children[n4] = n;
        rsInterface.childX[n4] = n2;
        rsInterface.childY[n4] = n3;
    }
    
    public static void setChildren(final int n, final RSInterface rsInterface) {
        rsInterface.children = new int[n];
        rsInterface.childX = new int[n];
        rsInterface.childY = new int[n];
    }
    
    public static void Pestpanel(final TextDrawingArea[] array) {
        final RSInterface addInterface = addInterface(21119);
        addText(21120, "What", 10066329, false, true, 52, array, 1);
        addText(21121, "What", 3394560, false, true, 52, array, 1);
        addText(21122, "(Need 5 to 25 players)", 16763955, false, true, 52, array, 1);
        addText(21123, "Points", 3394815, false, true, 52, array, 1);
        final int n = 4;
        addInterface.children = new int[n];
        addInterface.childX = new int[n];
        addInterface.childY = new int[n];
        setBounds(21120, 15, 12, 0, addInterface);
        setBounds(21121, 15, 30, 1, addInterface);
        setBounds(21122, 15, 48, 2, addInterface);
        setBounds(21123, 15, 66, 3, addInterface);
    }
    
    public static void Pestpanel2(final TextDrawingArea[] array) {
        final RSInterface addInterface = addInterface(21100);
        addSprite(21101, 0, "Pest Control/PEST1");
        addSprite(21102, 1, "Pest Control/PEST1");
        addSprite(21103, 2, "Pest Control/PEST1");
        addSprite(21104, 3, "Pest Control/PEST1");
        addSprite(21105, 4, "Pest Control/PEST1");
        addSprite(21106, 5, "Pest Control/PEST1");
        addText(21107, "", 13369548, false, true, 52, array, 1);
        addText(21108, "", 255, false, true, 52, array, 1);
        addText(21109, "", 16777028, false, true, 52, array, 1);
        addText(21110, "", 13369344, false, true, 52, array, 1);
        addText(21111, "250", 10092339, false, true, 52, array, 1);
        addText(21112, "250", 10092339, false, true, 52, array, 1);
        addText(21113, "250", 10092339, false, true, 52, array, 1);
        addText(21114, "250", 10092339, false, true, 52, array, 1);
        addText(21115, "200", 10092339, false, true, 52, array, 1);
        addText(21116, "0", 10092339, false, true, 52, array, 1);
        addText(21117, "Time Remaining:", 16777215, false, true, 52, array, 0);
        addText(21118, "", 16777215, false, true, 52, array, 0);
        final int n = 18;
        addInterface.children = new int[n];
        addInterface.childX = new int[n];
        addInterface.childY = new int[n];
        setBounds(21101, 361, 26, 0, addInterface);
        setBounds(21102, 396, 26, 1, addInterface);
        setBounds(21103, 436, 26, 2, addInterface);
        setBounds(21104, 474, 26, 3, addInterface);
        setBounds(21105, 3, 21, 4, addInterface);
        setBounds(21106, 3, 50, 5, addInterface);
        setBounds(21107, 371, 60, 6, addInterface);
        setBounds(21108, 409, 60, 7, addInterface);
        setBounds(21109, 443, 60, 8, addInterface);
        setBounds(21110, 479, 60, 9, addInterface);
        setBounds(21111, 362, 10, 10, addInterface);
        setBounds(21112, 398, 10, 11, addInterface);
        setBounds(21113, 436, 10, 12, addInterface);
        setBounds(21114, 475, 10, 13, addInterface);
        setBounds(21115, 32, 32, 14, addInterface);
        setBounds(21116, 32, 62, 15, addInterface);
        setBounds(21117, 8, 88, 16, addInterface);
        setBounds(21118, 87, 88, 17, addInterface);
    }
    
    public static void addHoverBox(final int n, final String hoverText) {
        final RSInterface rsInterface = RSInterface.interfaceCache[n];
        rsInterface.id = n;
        rsInterface.parentID = n;
        rsInterface.isMouseoverTriggered = true;
        rsInterface.type = 8;
        rsInterface.hoverText = hoverText;
    }
    
    public static void addText(final int n, final String message, final TextDrawingArea[] array, final int n2, final int textColor, final boolean centerText, final boolean textShadow) {
        final RSInterface addTabInterface = addTabInterface(n);
        addTabInterface.parentID = n;
        addTabInterface.id = n;
        addTabInterface.type = 4;
        addTabInterface.atActionType = 0;
        addTabInterface.width = 0;
        addTabInterface.height = 11;
        addTabInterface.contentType = 0;
        addTabInterface.aByte254 = 0;
        addTabInterface.mOverInterToTrigger = -1;
        addTabInterface.centerText = centerText;
        addTabInterface.textShadow = textShadow;
        addTabInterface.textDrawingAreas = array[n2];
        addTabInterface.message = message;
        addTabInterface.aString228 = "";
        addTabInterface.textColor = textColor;
        addTabInterface.anInt219 = 0;
        addTabInterface.anInt216 = 0;
        addTabInterface.anInt239 = 0;
    }
    
    public static void addButton(final int n, final int n2, final String s, final String tooltip, final int width, final int height) {
        final RSInterface[] interfaceCache = RSInterface.interfaceCache;
        final RSInterface rsInterface = new RSInterface();
        interfaceCache[n] = rsInterface;
        final RSInterface rsInterface2 = rsInterface;
        rsInterface2.id = n;
        rsInterface2.parentID = n;
        rsInterface2.type = 5;
        rsInterface2.atActionType = 1;
        rsInterface2.contentType = 0;
        rsInterface2.aByte254 = 0;
        rsInterface2.mOverInterToTrigger = 52;
        rsInterface2.sprite1 = imageLoader(n2, s);
        rsInterface2.sprite2 = imageLoader(n2, s);
        rsInterface2.width = width;
        rsInterface2.height = height;
        rsInterface2.tooltip = tooltip;
    }
    
    public static void addConfigButton(final int id, final int parentID, final int n, final int n2, final String s, final int width, final int height, final String tooltip, final int n3, final int atActionType, final int n4) {
        final RSInterface addTabInterface = addTabInterface(id);
        addTabInterface.parentID = parentID;
        addTabInterface.id = id;
        addTabInterface.type = 5;
        addTabInterface.atActionType = atActionType;
        addTabInterface.contentType = 0;
        addTabInterface.width = width;
        addTabInterface.height = height;
        addTabInterface.aByte254 = 0;
        addTabInterface.mOverInterToTrigger = -1;
        addTabInterface.anIntArray245 = new int[1];
        addTabInterface.anIntArray212 = new int[1];
        addTabInterface.anIntArray245[0] = 1;
        addTabInterface.anIntArray212[0] = n3;
        addTabInterface.valueIndexArray = new int[1][3];
        addTabInterface.valueIndexArray[0][0] = 5;
        addTabInterface.valueIndexArray[0][1] = n4;
        addTabInterface.valueIndexArray[0][2] = 0;
        addTabInterface.sprite1 = imageLoader(n, s);
        addTabInterface.sprite2 = imageLoader(n2, s);
        addTabInterface.tooltip = tooltip;
    }
    
    public static void addSprite(final int n, final int n2, final String s) {
        final RSInterface[] interfaceCache = RSInterface.interfaceCache;
        final RSInterface rsInterface = new RSInterface();
        interfaceCache[n] = rsInterface;
        final RSInterface rsInterface2 = rsInterface;
        rsInterface2.id = n;
        rsInterface2.parentID = n;
        rsInterface2.type = 5;
        rsInterface2.atActionType = 0;
        rsInterface2.contentType = 0;
        rsInterface2.aByte254 = 0;
        rsInterface2.mOverInterToTrigger = 52;
        rsInterface2.sprite1 = imageLoader(n2, s);
        rsInterface2.sprite2 = imageLoader(n2, s);
        rsInterface2.width = 512;
        rsInterface2.height = 334;
    }
    
    public static void addHoverButton(final int n, final String s, final int n2, final int width, final int height, final String tooltip, final int contentType, final int mOverInterToTrigger, final int atActionType) {
        final RSInterface addTabInterface = addTabInterface(n);
        addTabInterface.id = n;
        addTabInterface.parentID = n;
        addTabInterface.type = 5;
        addTabInterface.atActionType = atActionType;
        addTabInterface.contentType = contentType;
        addTabInterface.aByte254 = 0;
        addTabInterface.mOverInterToTrigger = mOverInterToTrigger;
        addTabInterface.sprite1 = imageLoader(n2, s);
        addTabInterface.sprite2 = imageLoader(n2, s);
        addTabInterface.width = width;
        addTabInterface.height = height;
        addTabInterface.tooltip = tooltip;
    }
    
    public static void addHoveredButton(final int n, final String s, final int n2, final int width, final int height, final int n3) {
        final RSInterface addTabInterface = addTabInterface(n);
        addTabInterface.parentID = n;
        addTabInterface.id = n;
        addTabInterface.type = 0;
        addTabInterface.atActionType = 0;
        addTabInterface.width = width;
        addTabInterface.height = height;
        addTabInterface.isMouseoverTriggered = true;
        addTabInterface.aByte254 = 0;
        addTabInterface.mOverInterToTrigger = -1;
        addTabInterface.scrollMax = 0;
        addHoverImage(n3, n2, n2, s);
        addTabInterface.totalChildren(1);
        addTabInterface.child(0, n3, 0, 0);
    }
    
    public static void addHoverImage(final int n, final int n2, final int n3, final String s) {
        final RSInterface addTabInterface = addTabInterface(n);
        addTabInterface.id = n;
        addTabInterface.parentID = n;
        addTabInterface.type = 5;
        addTabInterface.atActionType = 0;
        addTabInterface.contentType = 0;
        addTabInterface.width = 512;
        addTabInterface.height = 334;
        addTabInterface.aByte254 = 0;
        addTabInterface.mOverInterToTrigger = 52;
        addTabInterface.sprite1 = imageLoader(n2, s);
        addTabInterface.sprite2 = imageLoader(n3, s);
    }
    
    public static void addTransparentSprite(final int n, final int n2, final String s) {
        final RSInterface[] interfaceCache = RSInterface.interfaceCache;
        final RSInterface rsInterface = new RSInterface();
        interfaceCache[n] = rsInterface;
        final RSInterface rsInterface2 = rsInterface;
        rsInterface2.id = n;
        rsInterface2.parentID = n;
        rsInterface2.type = 5;
        rsInterface2.atActionType = 0;
        rsInterface2.contentType = 0;
        rsInterface2.aByte254 = 0;
        rsInterface2.mOverInterToTrigger = 52;
        rsInterface2.sprite1 = imageLoader(n2, s);
        rsInterface2.sprite2 = imageLoader(n2, s);
        rsInterface2.width = 512;
        rsInterface2.height = 334;
        rsInterface2.drawsTransparent = true;
    }
    
    public static RSInterface addScreenInterface(final int n) {
        final RSInterface[] interfaceCache = RSInterface.interfaceCache;
        final RSInterface rsInterface = new RSInterface();
        interfaceCache[n] = rsInterface;
        final RSInterface rsInterface2 = rsInterface;
        rsInterface2.id = n;
        rsInterface2.parentID = n;
        rsInterface2.type = 0;
        rsInterface2.atActionType = 0;
        rsInterface2.contentType = 0;
        rsInterface2.width = 512;
        rsInterface2.height = 334;
        rsInterface2.aByte254 = 0;
        rsInterface2.mOverInterToTrigger = 0;
        return rsInterface2;
    }
    
    public static RSInterface addTabInterface(final int n) {
        final RSInterface[] interfaceCache = RSInterface.interfaceCache;
        final RSInterface rsInterface = new RSInterface();
        interfaceCache[n] = rsInterface;
        final RSInterface rsInterface2 = rsInterface;
        rsInterface2.id = n;
        rsInterface2.parentID = n;
        rsInterface2.type = 0;
        rsInterface2.atActionType = 0;
        rsInterface2.contentType = 0;
        rsInterface2.width = 512;
        rsInterface2.height = 700;
        rsInterface2.aByte254 = 0;
        rsInterface2.mOverInterToTrigger = -1;
        return rsInterface2;
    }
    
    private static Sprite imageLoader(final String s) {
        Sprite sprite;
        try {
            sprite = new Sprite(s);
        }
        catch (Exception ex) {
            return null;
        }
        return sprite;
    }
    
    private static Sprite imageLoader(final int n, final String s) {
        final long n2 = (TextClass.method585(s) << 8) + n;
        final Sprite sprite = (Sprite)RSInterface.aMRUNodes_238.insertFromCache(n2);
        if (sprite != null) {
            return sprite;
        }
        Sprite sprite2;
        try {
            sprite2 = new Sprite(s + " " + n);
            RSInterface.aMRUNodes_238.removeFromCache(sprite2, n2);
        }
        catch (Exception ex) {
            return null;
        }
        return sprite2;
    }
    
    public void child(final int n, final int n2, final int n3, final int n4) {
        this.children[n] = n2;
        this.childX[n] = n3;
        this.childY[n] = n4;
    }
    
    public void totalChildren(final int n) {
        this.children = new int[n];
        this.childX = new int[n];
        this.childY = new int[n];
    }
    
    private Model method206(final int n, final int n2) {
        Model model = (Model)RSInterface.aMRUNodes_264.insertFromCache((n << 16) + n2);
        if (model != null) {
            return model;
        }
        if (n == 1) {
            model = Model.method462(n2);
        }
        if (n == 2) {
            model = EntityDef.forID(n2).method160();
        }
        if (n == 3) {
            model = client.myPlayer.method453();
        }
        if (n == 4) {
            model = ItemDef.forID(n2).method202(50);
        }
        if (n == 5) {
            model = null;
        }
        if (model != null) {
            RSInterface.aMRUNodes_264.removeFromCache(model, (n << 16) + n2);
        }
        return model;
    }
    
    private static Sprite method207(final int n, final StreamLoader streamLoader, final String s) {
        final long n2 = (TextClass.method585(s) << 8) + n;
        if (s.equals("combatboxes") && ++RSInterface.f2 == 2) {
            RSInterface.f2 = 3;
            return null;
        }
        if (s.contains("prayer") && ++RSInterface.f > 93) {
            return null;
        }
        final Sprite sprite = (Sprite)RSInterface.aMRUNodes_238.insertFromCache(n2);
        if (sprite != null) {
            return sprite;
        }
        Sprite sprite2;
        try {
            sprite2 = new Sprite(streamLoader, s, n);
            if (sprite2 == null) {
                return null;
            }
            RSInterface.aMRUNodes_238.removeFromCache(sprite2, n2);
        }
        catch (Exception ex) {
            return null;
        }
        return sprite2;
    }
    
    public static void method208(final boolean b, final Model model) {
        final int n = 0;
        final int n2 = 5;
        if (b) {
            return;
        }
        RSInterface.aMRUNodes_264.unlinkAll();
        if (model != null && n2 != 4) {
            RSInterface.aMRUNodes_264.removeFromCache(model, (n2 << 16) + n);
        }
    }
    
    public Model method209(final int n, final int n2, final boolean b) {
        Model model;
        if (b) {
            model = this.method206(this.anInt255, this.anInt256);
        }
        else {
            model = this.method206(this.anInt233, this.mediaID);
        }
        if (model == null) {
            return null;
        }
        if (n2 == -1 && n == -1 && model.anIntArray1640 == null) {
            return model;
        }
        final Model model2 = new Model(true, Class36.method532(n2) & Class36.method532(n), false, model);
        if (n2 != -1 || n != -1) {
            model2.method469();
        }
        if (n2 != -1) {
            model2.method470(n2);
        }
        if (n != -1) {
            model2.method470(n);
        }
        model2.method479(64, 768, -50, -10, -50, true);
        return model2;
    }
    
    public void setSprite(final Sprite sprite1) {
        this.sprite1 = sprite1;
    }
    
    public static void equipmentScreen(final TextDrawingArea[] array) {
        final RSInterface rsInterface = RSInterface.interfaceCache[1644];
        addButton(19144, 6, "Equipment/CUSTOM", 150, 40, "Show Equipment Stats", 1);
        removeSomething(19145);
        removeSomething(19146);
        removeSomething(19147);
        setBounds(19144, 21, 210, 23, rsInterface);
        setBounds(19145, 40, 210, 24, rsInterface);
        setBounds(19146, 40, 210, 25, rsInterface);
        setBounds(19147, 40, 210, 26, rsInterface);
        final RSInterface addTabInterface = addTabInterface(15106);
        addSprite(15107, 7, "Equipment/CUSTOM");
        addHoverButton(15210, "Equipment/CUSTOM", 8, 21, 21, "Close", 250, 15211, 3);
        addHoveredButton(15211, "Equipment/CUSTOM", 9, 21, 21, 15212);
        addText(15111, "Equip Your Character...", array, 2, 14983494, false, true);
        addText(15112, "Attack bonus", array, 2, 14983494, false, true);
        addText(15113, "Defence bonus", array, 2, 14983494, false, true);
        addText(15114, "Other bonuses", array, 2, 14983494, false, true);
        for (int i = 1675; i <= 1684; ++i) {
            textSize(i, array, 1);
        }
        textSize(1686, array, 1);
        textSize(1687, array, 1);
        addChar(15125);
        addTabInterface.totalChildren(44);
        addTabInterface.child(0, 15107, 4, 20);
        addTabInterface.child(1, 15210, 476, 29);
        addTabInterface.child(2, 15211, 476, 29);
        addTabInterface.child(3, 15111, 14, 30);
        int n = 4;
        int n2 = 69;
        for (int j = 1675; j <= 1679; ++j) {
            addTabInterface.child(n, j, 20, n2);
            ++n;
            n2 += 14;
        }
        addTabInterface.child(9, 1680, 20, 161);
        addTabInterface.child(10, 1681, 20, 177);
        addTabInterface.child(11, 1682, 20, 192);
        addTabInterface.child(12, 1683, 20, 207);
        addTabInterface.child(13, 1684, 20, 221);
        addTabInterface.child(14, 1686, 20, 262);
        addTabInterface.child(15, 15125, 170, 200);
        addTabInterface.child(16, 15112, 16, 55);
        addTabInterface.child(17, 1687, 20, 276);
        addTabInterface.child(18, 15113, 16, 147);
        addTabInterface.child(19, 15114, 16, 248);
        addTabInterface.child(20, 1645, 399, 97);
        addTabInterface.child(21, 1646, 399, 163);
        addTabInterface.child(22, 1647, 399, 163);
        addTabInterface.child(23, 1648, 399, 204);
        addTabInterface.child(24, 1649, 343, 176);
        addTabInterface.child(25, 1650, 343, 212);
        addTabInterface.child(26, 1651, 455, 176);
        addTabInterface.child(27, 1652, 455, 212);
        addTabInterface.child(28, 1653, 369, 139);
        addTabInterface.child(29, 1654, 428, 139);
        addTabInterface.child(30, 1655, 379, 100);
        addTabInterface.child(31, 1656, 433, 99);
        addTabInterface.child(32, 1657, 399, 62);
        addTabInterface.child(33, 1658, 358, 101);
        addTabInterface.child(34, 1659, 399, 101);
        addTabInterface.child(35, 1660, 440, 101);
        addTabInterface.child(36, 1661, 343, 140);
        addTabInterface.child(37, 1662, 399, 140);
        addTabInterface.child(38, 1663, 455, 140);
        addTabInterface.child(39, 1664, 399, 180);
        addTabInterface.child(40, 1665, 399, 220);
        addTabInterface.child(41, 1666, 343, 220);
        addTabInterface.child(42, 1667, 455, 220);
        addTabInterface.child(43, 1688, 345, 102);
        for (int k = 1675; k <= 1684; ++k) {
            final RSInterface rsInterface2 = RSInterface.interfaceCache[k];
            rsInterface2.textColor = 14983494;
            rsInterface2.centerText = false;
        }
        for (int l = 1686; l <= 1687; ++l) {
            final RSInterface rsInterface3 = RSInterface.interfaceCache[l];
            rsInterface3.textColor = 14983494;
            rsInterface3.centerText = false;
        }
    }
    
    public static void addChar(final int n) {
        final RSInterface[] interfaceCache = RSInterface.interfaceCache;
        final RSInterface rsInterface = new RSInterface();
        interfaceCache[n] = rsInterface;
        final RSInterface rsInterface2 = rsInterface;
        rsInterface2.id = n;
        rsInterface2.parentID = n;
        rsInterface2.type = 6;
        rsInterface2.atActionType = 0;
        rsInterface2.contentType = 328;
        rsInterface2.width = 136;
        rsInterface2.height = 168;
        rsInterface2.aByte254 = 0;
        rsInterface2.mOverInterToTrigger = 0;
        rsInterface2.modelZoom = 560;
        rsInterface2.modelRotation1 = 150;
        rsInterface2.modelRotation2 = 0;
        rsInterface2.anInt257 = -1;
        rsInterface2.anInt258 = -1;
    }
    
    public static void addText(final int n, final String message, final int textColor, final boolean centerText, final boolean textShadow, final int mOverInterToTrigger, final int n2) {
        final RSInterface addTabInterface = addTabInterface(n);
        addTabInterface.parentID = n;
        addTabInterface.id = n;
        addTabInterface.type = 4;
        addTabInterface.atActionType = 0;
        addTabInterface.width = 0;
        addTabInterface.height = 0;
        addTabInterface.contentType = 0;
        addTabInterface.aByte254 = 0;
        addTabInterface.mOverInterToTrigger = mOverInterToTrigger;
        addTabInterface.centerText = centerText;
        addTabInterface.textShadow = textShadow;
        addTabInterface.textDrawingAreas = RSInterface.fonts[n2];
        addTabInterface.message = message;
        addTabInterface.textColor = textColor;
    }
    
    private static Sprite loadSprite(final int n, final String s) {
        Sprite sprite;
        try {
            sprite = new Sprite(s + " " + n);
            if (sprite != null) {
                return sprite;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return sprite;
    }
    
    public Sprite loadSprite(final String s, final int n) {
        try {
            final Sprite sprite = new Sprite(s + " " + n);
            if (sprite != null) {
                return sprite;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }
    
    public static void addLunarSprite(final int n, final int n2, final String s) {
        final RSInterface addTabInterface = addTabInterface(n);
        addTabInterface.id = n;
        addTabInterface.parentID = n;
        addTabInterface.type = 5;
        addTabInterface.atActionType = 0;
        addTabInterface.contentType = 0;
        addTabInterface.aByte254 = 0;
        addTabInterface.mOverInterToTrigger = 52;
        addTabInterface.sprite1 = imageLoader(n2, s);
        addTabInterface.width = 500;
        addTabInterface.height = 500;
        addTabInterface.tooltip = "";
    }
    
    public static void drawRune(final int n, final int n2, final String s) {
        final RSInterface addTabInterface = addTabInterface(n);
        addTabInterface.type = 5;
        addTabInterface.atActionType = 0;
        addTabInterface.contentType = 0;
        addTabInterface.aByte254 = 0;
        addTabInterface.mOverInterToTrigger = 52;
        addTabInterface.sprite1 = loadSprite(n2, "Lunar/RUNE");
        addTabInterface.width = 500;
        addTabInterface.height = 500;
    }
    
    public static void addRuneText(final int id, final int n, final int n2, final TextDrawingArea[] array) {
        final RSInterface addTabInterface = addTabInterface(id);
        addTabInterface.id = id;
        addTabInterface.parentID = 1151;
        addTabInterface.type = 4;
        addTabInterface.atActionType = 0;
        addTabInterface.contentType = 0;
        addTabInterface.width = 0;
        addTabInterface.height = 14;
        addTabInterface.aByte254 = 0;
        addTabInterface.mOverInterToTrigger = -1;
        addTabInterface.anIntArray245 = new int[1];
        addTabInterface.anIntArray212 = new int[1];
        addTabInterface.anIntArray245[0] = 3;
        addTabInterface.anIntArray212[0] = n;
        addTabInterface.valueIndexArray = new int[1][4];
        addTabInterface.valueIndexArray[0][0] = 4;
        addTabInterface.valueIndexArray[0][1] = 3214;
        addTabInterface.valueIndexArray[0][2] = n2;
        addTabInterface.valueIndexArray[0][3] = 0;
        addTabInterface.centerText = true;
        addTabInterface.textDrawingAreas = array[0];
        addTabInterface.textShadow = true;
        addTabInterface.message = "%1/" + n + "";
        addTabInterface.aString228 = "";
        addTabInterface.textColor = 12582912;
        addTabInterface.anInt219 = 49152;
    }
    
    public static void homeTeleport() {
        final RSInterface addTabInterface = addTabInterface(30000);
        addTabInterface.tooltip = "Cast @gre@Lunar Home Teleport";
        addTabInterface.id = 30000;
        addTabInterface.parentID = 30000;
        addTabInterface.type = 5;
        addTabInterface.atActionType = 5;
        addTabInterface.contentType = 0;
        addTabInterface.aByte254 = 0;
        addTabInterface.mOverInterToTrigger = 30001;
        addTabInterface.sprite1 = loadSprite(1, "Lunar/SPRITE");
        addTabInterface.width = 20;
        addTabInterface.height = 20;
        final RSInterface addTabInterface2 = addTabInterface(30001);
        addTabInterface2.mOverInterToTrigger = -1;
        addTabInterface2.isMouseoverTriggered = true;
        setChildren(1, addTabInterface2);
        addLunarSprite(30002, 0, "Lunar/SPRITE");
        setBounds(30002, 0, 0, 0, addTabInterface2);
    }
    
    public static void addLunar2RunesSmallBox(final int id, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final String spellName, final String s, final TextDrawingArea[] array, final int n7, final int spellUsableOn, final int atActionType) {
        final RSInterface addTabInterface = addTabInterface(id);
        addTabInterface.id = id;
        addTabInterface.parentID = 1151;
        addTabInterface.type = 5;
        addTabInterface.atActionType = atActionType;
        addTabInterface.contentType = 0;
        addTabInterface.mOverInterToTrigger = id + 1;
        addTabInterface.spellUsableOn = spellUsableOn;
        addTabInterface.selectedActionName = "Cast On";
        addTabInterface.width = 20;
        addTabInterface.height = 20;
        addTabInterface.tooltip = "Cast @gre@" + spellName;
        addTabInterface.spellName = spellName;
        addTabInterface.anIntArray245 = new int[3];
        addTabInterface.anIntArray212 = new int[3];
        addTabInterface.anIntArray245[0] = 3;
        addTabInterface.anIntArray212[0] = n3;
        addTabInterface.anIntArray245[1] = 3;
        addTabInterface.anIntArray212[1] = n4;
        addTabInterface.anIntArray245[2] = 3;
        addTabInterface.anIntArray212[2] = n6;
        addTabInterface.valueIndexArray = new int[3][];
        (addTabInterface.valueIndexArray[0] = new int[4])[0] = 4;
        addTabInterface.valueIndexArray[0][1] = 3214;
        addTabInterface.valueIndexArray[0][2] = n;
        addTabInterface.valueIndexArray[0][3] = 0;
        (addTabInterface.valueIndexArray[1] = new int[4])[0] = 4;
        addTabInterface.valueIndexArray[1][1] = 3214;
        addTabInterface.valueIndexArray[1][2] = n2;
        addTabInterface.valueIndexArray[1][3] = 0;
        (addTabInterface.valueIndexArray[2] = new int[3])[0] = 1;
        addTabInterface.valueIndexArray[2][1] = 6;
        addTabInterface.valueIndexArray[2][2] = 0;
        addTabInterface.sprite2 = loadSprite(n7, "Lunar/LUNARON");
        addTabInterface.sprite1 = loadSprite(n7, "Lunar/LUNAROFF");
        final RSInterface addTabInterface2 = addTabInterface(id + 1);
        addTabInterface2.mOverInterToTrigger = -1;
        addTabInterface2.isMouseoverTriggered = true;
        setChildren(7, addTabInterface2);
        addLunarSprite(id + 2, 0, "Lunar/BOX");
        setBounds(id + 2, 0, 0, 0, addTabInterface2);
        addText(id + 3, "Level " + (n6 + 1) + ": " + spellName, 16750623, true, true, 52, 1);
        setBounds(id + 3, 90, 4, 1, addTabInterface2);
        addText(id + 4, s, 11495962, true, true, 52, 0);
        setBounds(id + 4, 90, 19, 2, addTabInterface2);
        setBounds(30016, 37, 35, 3, addTabInterface2);
        setBounds(n5, 112, 35, 4, addTabInterface2);
        addRuneText(id + 5, n3 + 1, n, array);
        setBounds(id + 5, 50, 66, 5, addTabInterface2);
        addRuneText(id + 6, n4 + 1, n2, array);
        setBounds(id + 6, 123, 66, 6, addTabInterface2);
    }
    
    public static void addLunar3RunesSmallBox(final int id, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final String spellName, final String s, final TextDrawingArea[] array, final int n10, final int spellUsableOn, final int atActionType) {
        final RSInterface addTabInterface = addTabInterface(id);
        addTabInterface.id = id;
        addTabInterface.parentID = 1151;
        addTabInterface.type = 5;
        addTabInterface.atActionType = atActionType;
        addTabInterface.contentType = 0;
        addTabInterface.mOverInterToTrigger = id + 1;
        addTabInterface.spellUsableOn = spellUsableOn;
        addTabInterface.selectedActionName = "Cast on";
        addTabInterface.width = 20;
        addTabInterface.height = 20;
        addTabInterface.tooltip = "Cast @gre@" + spellName;
        addTabInterface.spellName = spellName;
        addTabInterface.anIntArray245 = new int[4];
        addTabInterface.anIntArray212 = new int[4];
        addTabInterface.anIntArray245[0] = 3;
        addTabInterface.anIntArray212[0] = n4;
        addTabInterface.anIntArray245[1] = 3;
        addTabInterface.anIntArray212[1] = n5;
        addTabInterface.anIntArray245[2] = 3;
        addTabInterface.anIntArray212[2] = n6;
        addTabInterface.anIntArray245[3] = 3;
        addTabInterface.anIntArray212[3] = n9;
        addTabInterface.valueIndexArray = new int[4][];
        (addTabInterface.valueIndexArray[0] = new int[4])[0] = 4;
        addTabInterface.valueIndexArray[0][1] = 3214;
        addTabInterface.valueIndexArray[0][2] = n;
        addTabInterface.valueIndexArray[0][3] = 0;
        (addTabInterface.valueIndexArray[1] = new int[4])[0] = 4;
        addTabInterface.valueIndexArray[1][1] = 3214;
        addTabInterface.valueIndexArray[1][2] = n2;
        addTabInterface.valueIndexArray[1][3] = 0;
        (addTabInterface.valueIndexArray[2] = new int[4])[0] = 4;
        addTabInterface.valueIndexArray[2][1] = 3214;
        addTabInterface.valueIndexArray[2][2] = n3;
        addTabInterface.valueIndexArray[2][3] = 0;
        (addTabInterface.valueIndexArray[3] = new int[3])[0] = 1;
        addTabInterface.valueIndexArray[3][1] = 6;
        addTabInterface.valueIndexArray[3][2] = 0;
        addTabInterface.sprite2 = loadSprite(n10, "Lunar/LUNARON");
        addTabInterface.sprite1 = loadSprite(n10, "Lunar/LUNAROFF");
        final RSInterface addTabInterface2 = addTabInterface(id + 1);
        addTabInterface2.mOverInterToTrigger = -1;
        addTabInterface2.isMouseoverTriggered = true;
        setChildren(9, addTabInterface2);
        addLunarSprite(id + 2, 0, "Lunar/BOX");
        setBounds(id + 2, 0, 0, 0, addTabInterface2);
        addText(id + 3, "Level " + (n9 + 1) + ": " + spellName, 16750623, true, true, 52, 1);
        setBounds(id + 3, 90, 4, 1, addTabInterface2);
        addText(id + 4, s, 11495962, true, true, 52, 0);
        setBounds(id + 4, 90, 19, 2, addTabInterface2);
        setBounds(30016, 14, 35, 3, addTabInterface2);
        setBounds(n7, 74, 35, 4, addTabInterface2);
        setBounds(n8, 130, 35, 5, addTabInterface2);
        addRuneText(id + 5, n4 + 1, n, array);
        setBounds(id + 5, 26, 66, 6, addTabInterface2);
        addRuneText(id + 6, n5 + 1, n2, array);
        setBounds(id + 6, 87, 66, 7, addTabInterface2);
        addRuneText(id + 7, n6 + 1, n3, array);
        setBounds(id + 7, 142, 66, 8, addTabInterface2);
    }
    
    public static void addLunar3RunesBigBox(final int id, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final String spellName, final String s, final TextDrawingArea[] array, final int n10, final int spellUsableOn, final int atActionType) {
        final RSInterface addTabInterface = addTabInterface(id);
        addTabInterface.id = id;
        addTabInterface.parentID = 1151;
        addTabInterface.type = 5;
        addTabInterface.atActionType = atActionType;
        addTabInterface.contentType = 0;
        addTabInterface.mOverInterToTrigger = id + 1;
        addTabInterface.spellUsableOn = spellUsableOn;
        addTabInterface.selectedActionName = "Cast on";
        addTabInterface.width = 20;
        addTabInterface.height = 20;
        addTabInterface.tooltip = "Cast @gre@" + spellName;
        addTabInterface.spellName = spellName;
        addTabInterface.anIntArray245 = new int[4];
        addTabInterface.anIntArray212 = new int[4];
        addTabInterface.anIntArray245[0] = 3;
        addTabInterface.anIntArray212[0] = n4;
        addTabInterface.anIntArray245[1] = 3;
        addTabInterface.anIntArray212[1] = n5;
        addTabInterface.anIntArray245[2] = 3;
        addTabInterface.anIntArray212[2] = n6;
        addTabInterface.anIntArray245[3] = 3;
        addTabInterface.anIntArray212[3] = n9;
        addTabInterface.valueIndexArray = new int[4][];
        (addTabInterface.valueIndexArray[0] = new int[4])[0] = 4;
        addTabInterface.valueIndexArray[0][1] = 3214;
        addTabInterface.valueIndexArray[0][2] = n;
        addTabInterface.valueIndexArray[0][3] = 0;
        (addTabInterface.valueIndexArray[1] = new int[4])[0] = 4;
        addTabInterface.valueIndexArray[1][1] = 3214;
        addTabInterface.valueIndexArray[1][2] = n2;
        addTabInterface.valueIndexArray[1][3] = 0;
        (addTabInterface.valueIndexArray[2] = new int[4])[0] = 4;
        addTabInterface.valueIndexArray[2][1] = 3214;
        addTabInterface.valueIndexArray[2][2] = n3;
        addTabInterface.valueIndexArray[2][3] = 0;
        (addTabInterface.valueIndexArray[3] = new int[3])[0] = 1;
        addTabInterface.valueIndexArray[3][1] = 6;
        addTabInterface.valueIndexArray[3][2] = 0;
        addTabInterface.sprite2 = loadSprite(n10, "Lunar/LUNARON");
        addTabInterface.sprite1 = loadSprite(n10, "Lunar/LUNAROFF");
        final RSInterface addTabInterface2 = addTabInterface(id + 1);
        addTabInterface2.mOverInterToTrigger = -1;
        addTabInterface2.isMouseoverTriggered = true;
        setChildren(9, addTabInterface2);
        addLunarSprite(id + 2, 1, "Lunar/BOX");
        setBounds(id + 2, 0, 0, 0, addTabInterface2);
        addText(id + 3, "Level " + (n9 + 1) + ": " + spellName, 16750623, true, true, 52, 1);
        setBounds(id + 3, 90, 4, 1, addTabInterface2);
        addText(id + 4, s, 11495962, true, true, 52, 0);
        setBounds(id + 4, 90, 21, 2, addTabInterface2);
        setBounds(30016, 14, 48, 3, addTabInterface2);
        setBounds(n7, 74, 48, 4, addTabInterface2);
        setBounds(n8, 130, 48, 5, addTabInterface2);
        addRuneText(id + 5, n4 + 1, n, array);
        setBounds(id + 5, 26, 79, 6, addTabInterface2);
        addRuneText(id + 6, n5 + 1, n2, array);
        setBounds(id + 6, 87, 79, 7, addTabInterface2);
        addRuneText(id + 7, n6 + 1, n3, array);
        setBounds(id + 7, 142, 79, 8, addTabInterface2);
    }
    
    public static void addLunar3RunesLargeBox(final int id, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final String spellName, final String s, final TextDrawingArea[] array, final int n10, final int spellUsableOn, final int atActionType) {
        final RSInterface addTabInterface = addTabInterface(id);
        addTabInterface.id = id;
        addTabInterface.parentID = 1151;
        addTabInterface.type = 5;
        addTabInterface.atActionType = atActionType;
        addTabInterface.contentType = 0;
        addTabInterface.mOverInterToTrigger = id + 1;
        addTabInterface.spellUsableOn = spellUsableOn;
        addTabInterface.selectedActionName = "Cast on";
        addTabInterface.width = 20;
        addTabInterface.height = 20;
        addTabInterface.tooltip = "Cast @gre@" + spellName;
        addTabInterface.spellName = spellName;
        addTabInterface.anIntArray245 = new int[4];
        addTabInterface.anIntArray212 = new int[4];
        addTabInterface.anIntArray245[0] = 3;
        addTabInterface.anIntArray212[0] = n4;
        addTabInterface.anIntArray245[1] = 3;
        addTabInterface.anIntArray212[1] = n5;
        addTabInterface.anIntArray245[2] = 3;
        addTabInterface.anIntArray212[2] = n6;
        addTabInterface.anIntArray245[3] = 3;
        addTabInterface.anIntArray212[3] = n9;
        addTabInterface.valueIndexArray = new int[4][];
        (addTabInterface.valueIndexArray[0] = new int[4])[0] = 4;
        addTabInterface.valueIndexArray[0][1] = 3214;
        addTabInterface.valueIndexArray[0][2] = n;
        addTabInterface.valueIndexArray[0][3] = 0;
        (addTabInterface.valueIndexArray[1] = new int[4])[0] = 4;
        addTabInterface.valueIndexArray[1][1] = 3214;
        addTabInterface.valueIndexArray[1][2] = n2;
        addTabInterface.valueIndexArray[1][3] = 0;
        (addTabInterface.valueIndexArray[2] = new int[4])[0] = 4;
        addTabInterface.valueIndexArray[2][1] = 3214;
        addTabInterface.valueIndexArray[2][2] = n3;
        addTabInterface.valueIndexArray[2][3] = 0;
        (addTabInterface.valueIndexArray[3] = new int[3])[0] = 1;
        addTabInterface.valueIndexArray[3][1] = 6;
        addTabInterface.valueIndexArray[3][2] = 0;
        addTabInterface.sprite2 = loadSprite(n10, "Lunar/LUNARON");
        addTabInterface.sprite1 = loadSprite(n10, "Lunar/LUNAROFF");
        final RSInterface addTabInterface2 = addTabInterface(id + 1);
        addTabInterface2.isMouseoverTriggered = true;
        addTabInterface2.mOverInterToTrigger = -1;
        setChildren(9, addTabInterface2);
        addLunarSprite(id + 2, 2, "Lunar/BOX");
        setBounds(id + 2, 0, 0, 0, addTabInterface2);
        addText(id + 3, "Level " + (n9 + 1) + ": " + spellName, 16750623, true, true, 52, 1);
        setBounds(id + 3, 90, 4, 1, addTabInterface2);
        addText(id + 4, s, 11495962, true, true, 52, 0);
        setBounds(id + 4, 90, 34, 2, addTabInterface2);
        setBounds(30016, 14, 61, 3, addTabInterface2);
        setBounds(n7, 74, 61, 4, addTabInterface2);
        setBounds(n8, 130, 61, 5, addTabInterface2);
        addRuneText(id + 5, n4 + 1, n, array);
        setBounds(id + 5, 26, 92, 6, addTabInterface2);
        addRuneText(id + 6, n5 + 1, n2, array);
        setBounds(id + 6, 87, 92, 7, addTabInterface2);
        addRuneText(id + 7, n6 + 1, n3, array);
        setBounds(id + 7, 142, 92, 8, addTabInterface2);
    }
    
    public static void drawTooltip(final int parentID, final String s) {
        final RSInterface addTabInterface = addTabInterface(parentID);
        addTabInterface.parentID = parentID;
        addTabInterface.type = 0;
        addTabInterface.isMouseoverTriggered = true;
        addTabInterface.mOverInterToTrigger = -1;
        addTooltipBox(parentID + 1, s);
        addTabInterface.totalChildren(1);
        addTabInterface.child(0, parentID + 1, 0, 0);
    }
    
    public static void addPrayer(final int id, final int n, final int n2, final int n3, final int n4, final String s, final int mOverInterToTrigger) {
        final RSInterface addTabInterface = addTabInterface(id);
        addTabInterface.id = id;
        addTabInterface.parentID = 22500;
        addTabInterface.type = 5;
        addTabInterface.atActionType = 4;
        addTabInterface.contentType = 0;
        addTabInterface.aByte254 = 0;
        addTabInterface.mOverInterToTrigger = mOverInterToTrigger;
        addTabInterface.sprite1 = imageLoader(0, "CurseTab/GLOW");
        addTabInterface.sprite2 = imageLoader(1, "CurseTab/GLOW");
        addTabInterface.width = 34;
        addTabInterface.height = 34;
        addTabInterface.anIntArray245 = new int[1];
        addTabInterface.anIntArray212 = new int[1];
        addTabInterface.anIntArray245[0] = 1;
        addTabInterface.anIntArray212[0] = n;
        addTabInterface.valueIndexArray = new int[1][3];
        addTabInterface.valueIndexArray[0][0] = 5;
        addTabInterface.valueIndexArray[0][1] = n2;
        addTabInterface.valueIndexArray[0][2] = 0;
        addTabInterface.tooltip = "Activate@lre@ " + s;
        final RSInterface addTabInterface2 = addTabInterface(id + 1);
        addTabInterface2.id = id + 1;
        addTabInterface2.parentID = 22500;
        addTabInterface2.type = 5;
        addTabInterface2.atActionType = 0;
        addTabInterface2.contentType = 0;
        addTabInterface2.aByte254 = 0;
        addTabInterface2.sprite1 = imageLoader(n4, "CurseTab/PRAYON");
        addTabInterface2.sprite2 = imageLoader(n4, "CurseTab/PRAYOFF");
        addTabInterface2.width = 34;
        addTabInterface2.height = 34;
        addTabInterface2.anIntArray245 = new int[1];
        addTabInterface2.anIntArray212 = new int[1];
        addTabInterface2.anIntArray245[0] = 2;
        addTabInterface2.anIntArray212[0] = n3 + 1;
        addTabInterface2.valueIndexArray = new int[1][3];
        addTabInterface2.valueIndexArray[0][0] = 2;
        addTabInterface2.valueIndexArray[0][1] = 5;
        addTabInterface2.valueIndexArray[0][2] = 0;
    }
    
    public static void Curses(final TextDrawingArea[] array) {
        final RSInterface addTabInterface = addTabInterface(22500);
        int n = 0;
        addText(687, "99/99", 16750623, false, false, -1, array, 1);
        addSprite(22502, 0, "CurseTab/ICON");
        addPrayer(22503, 0, 610, 49, 7, "Protect Item", 22582);
        addPrayer(22505, 0, 611, 49, 4, "Sap Warrior", 22544);
        addPrayer(22507, 0, 612, 51, 5, "Sap Ranger", 22546);
        addPrayer(22509, 0, 613, 53, 3, "Sap Mage", 22548);
        addPrayer(22511, 0, 614, 55, 2, "Sap Spirit", 22550);
        addPrayer(22513, 0, 615, 58, 18, "Berserker", 22552);
        addPrayer(22515, 0, 616, 61, 15, "Deflect Summoning", 22554);
        addPrayer(22517, 0, 617, 64, 17, "Deflect Magic", 22556);
        addPrayer(22519, 0, 618, 67, 16, "Deflect Missiles", 22558);
        addPrayer(22521, 0, 619, 70, 6, "Deflect Melee", 22560);
        addPrayer(22523, 0, 620, 73, 9, "Leech Attack", 22562);
        addPrayer(22525, 0, 621, 75, 10, "Leech Ranged", 22564);
        addPrayer(22527, 0, 622, 77, 11, "Leech Magic", 22566);
        addPrayer(22529, 0, 623, 79, 12, "Leech Defence", 22568);
        addPrayer(22531, 0, 624, 81, 13, "Leech Strength", 22570);
        addPrayer(22533, 0, 625, 83, 14, "Leech Energy", 22572);
        addPrayer(22535, 0, 626, 85, 19, "Leech Special Attack", 22574);
        addPrayer(22537, 0, 627, 88, 1, "Wrath", 22576);
        addPrayer(22539, 0, 628, 91, 8, "Soul Split", 22578);
        addPrayer(22541, 0, 629, 94, 20, "Turmoil", 22580);
        drawTooltip(22582, "Level 50\nProtect Item\nKeep 1 extra item if you die");
        drawTooltip(22544, "Level 50\nSap Warrior\nDrains 10% of enemy Attack,\nStrength and Defence,\nincreasing to 20% over time");
        drawTooltip(22546, "Level 52\nSap Ranger\nDrains 10% of enemy Ranged\nand Defence, increasing to 20%\nover time");
        drawTooltip(22548, "Level 54\nSap Mage\nDrains 10% of enemy Magic\nand Defence, increasing to 20%\nover time");
        drawTooltip(22550, "Level 56\nSap Spirit\nDrains enenmy special attack\nenergy");
        drawTooltip(22552, "Level 59\nBerserker\nBoosted stats last 15% longer");
        drawTooltip(22554, "Level 62\nDeflect Summoning\nReduces damage dealt from\nSummoning scrolls, prevents the\nuse of a familiar's special\nattack, and can deflect some of\ndamage back to the attacker");
        drawTooltip(22556, "Level 65\nDeflect Magic\nProtects against magical attacks\nand can deflect some of the\ndamage back to the attacker");
        drawTooltip(22558, "Level 68\nDeflect Missiles\nProtects against ranged attacks\nand can deflect some of the\ndamage back to the attacker");
        drawTooltip(22560, "Level 71\nDeflect Melee\nProtects against melee attacks\nand can deflect some of the\ndamage back to the attacker");
        drawTooltip(22562, "Level 74\nLeech Attack\nBoosts Attack by 5%, increasing\nto 10% over time, while draining\nenemy Attack by 10%, increasing\nto 25% over time");
        drawTooltip(22564, "Level 76\nLeech Ranged\nBoosts Ranged by 5%, increasing\nto 10% over time, while draining\nenemy Ranged by 10%,\nincreasing to 25% over\ntime");
        drawTooltip(22566, "Level 78\nLeech Magic\nBoosts Magic by 5%, increasing\nto 10% over time, while draining\nenemy Magic by 10%, increasing\nto 25% over time");
        drawTooltip(22568, "Level 80\nLeech Defence\nBoosts Defence by 5%, increasing\nto 10% over time, while draining\n enemy Defence by10%,\nincreasing to 25% over\ntime");
        drawTooltip(22570, "Level 82\nLeech Strength\nBoosts Strength by 5%, increasing\nto 10% over time, while draining\nenemy Strength by 10%, increasing\n to 25% over time");
        drawTooltip(22572, "Level 84\nLeech Energy\nDrains enemy run energy, while\nincreasing your own");
        drawTooltip(22574, "Level 86\nLeech Special Attack\nDrains enemy special attack\nenergy, while increasing your\nown");
        drawTooltip(22576, "Level 89\nWrath\nInflicts damage to nearby\ntargets if you die");
        drawTooltip(22578, "Level 92\nSoul Split\n1/4 of damage dealt is also removed\nfrom opponent's Prayer and\nadded to your Hitpoints");
        drawTooltip(22580, "Level 95\nTurmoil\nIncreases Attack and Defence\nby 15%, plus 15% of enemy's\nlevel, and Strength by 23% plus\n10% of enemy's level");
        setChildren(62, addTabInterface);
        setBounds(687, 85, 241, n, addTabInterface);
        ++n;
        setBounds(22502, 65, 241, n, addTabInterface);
        ++n;
        setBounds(22503, 2, 5, n, addTabInterface);
        ++n;
        setBounds(22504, 8, 8, n, addTabInterface);
        ++n;
        setBounds(22505, 40, 5, n, addTabInterface);
        ++n;
        setBounds(22506, 47, 12, n, addTabInterface);
        ++n;
        setBounds(22507, 76, 5, n, addTabInterface);
        ++n;
        setBounds(22508, 82, 11, n, addTabInterface);
        ++n;
        setBounds(22509, 113, 5, n, addTabInterface);
        ++n;
        setBounds(22510, 116, 8, n, addTabInterface);
        ++n;
        setBounds(22511, 150, 5, n, addTabInterface);
        ++n;
        setBounds(22512, 155, 10, n, addTabInterface);
        ++n;
        setBounds(22513, 2, 45, n, addTabInterface);
        ++n;
        setBounds(22514, 9, 48, n, addTabInterface);
        ++n;
        setBounds(22515, 39, 45, n, addTabInterface);
        ++n;
        setBounds(22516, 42, 47, n, addTabInterface);
        ++n;
        setBounds(22517, 76, 45, n, addTabInterface);
        ++n;
        setBounds(22518, 79, 48, n, addTabInterface);
        ++n;
        setBounds(22519, 113, 45, n, addTabInterface);
        ++n;
        setBounds(22520, 116, 48, n, addTabInterface);
        ++n;
        setBounds(22521, 151, 45, n, addTabInterface);
        ++n;
        setBounds(22522, 154, 48, n, addTabInterface);
        ++n;
        setBounds(22523, 2, 82, n, addTabInterface);
        ++n;
        setBounds(22524, 6, 86, n, addTabInterface);
        ++n;
        setBounds(22525, 40, 82, n, addTabInterface);
        ++n;
        setBounds(22526, 42, 86, n, addTabInterface);
        ++n;
        setBounds(22527, 77, 82, n, addTabInterface);
        ++n;
        setBounds(22528, 79, 86, n, addTabInterface);
        ++n;
        setBounds(22529, 114, 83, n, addTabInterface);
        ++n;
        setBounds(22530, 119, 87, n, addTabInterface);
        ++n;
        setBounds(22531, 153, 83, n, addTabInterface);
        ++n;
        setBounds(22532, 156, 86, n, addTabInterface);
        ++n;
        setBounds(22533, 2, 120, n, addTabInterface);
        ++n;
        setBounds(22534, 7, 125, n, addTabInterface);
        ++n;
        setBounds(22535, 40, 120, n, addTabInterface);
        ++n;
        setBounds(22536, 45, 124, n, addTabInterface);
        ++n;
        setBounds(22537, 78, 120, n, addTabInterface);
        ++n;
        setBounds(22538, 86, 124, n, addTabInterface);
        ++n;
        setBounds(22539, 114, 120, n, addTabInterface);
        ++n;
        setBounds(22540, 120, 125, n, addTabInterface);
        ++n;
        setBounds(22541, 151, 120, n, addTabInterface);
        ++n;
        setBounds(22542, 153, 127, n, addTabInterface);
        ++n;
        setBounds(22582, 10, 40, n, addTabInterface);
        ++n;
        setBounds(22544, 20, 40, n, addTabInterface);
        ++n;
        setBounds(22546, 20, 40, n, addTabInterface);
        ++n;
        setBounds(22548, 20, 40, n, addTabInterface);
        ++n;
        setBounds(22550, 20, 40, n, addTabInterface);
        ++n;
        setBounds(22552, 10, 80, n, addTabInterface);
        ++n;
        setBounds(22554, 10, 80, n, addTabInterface);
        ++n;
        setBounds(22556, 10, 80, n, addTabInterface);
        ++n;
        setBounds(22558, 10, 80, n, addTabInterface);
        ++n;
        setBounds(22560, 10, 80, n, addTabInterface);
        ++n;
        setBounds(22562, 10, 120, n, addTabInterface);
        ++n;
        setBounds(22564, 10, 120, n, addTabInterface);
        ++n;
        setBounds(22566, 10, 120, n, addTabInterface);
        ++n;
        setBounds(22568, 5, 120, n, addTabInterface);
        ++n;
        setBounds(22570, 5, 120, n, addTabInterface);
        ++n;
        setBounds(22572, 10, 160, n, addTabInterface);
        ++n;
        setBounds(22574, 10, 160, n, addTabInterface);
        ++n;
        setBounds(22576, 10, 160, n, addTabInterface);
        ++n;
        setBounds(22578, 10, 160, n, addTabInterface);
        ++n;
        setBounds(22580, 10, 160, n, addTabInterface);
        ++n;
    }
    
    public static void configureLunar(final TextDrawingArea[] array) {
        homeTeleport();
        drawRune(30003, 1, "Fire");
        drawRune(30004, 2, "Water");
        drawRune(30005, 3, "Air");
        drawRune(30006, 4, "Earth");
        drawRune(30007, 5, "Mind");
        drawRune(30008, 6, "Body");
        drawRune(30009, 7, "Death");
        drawRune(30010, 8, "Nature");
        drawRune(30011, 9, "Chaos");
        drawRune(30012, 10, "Law");
        drawRune(30013, 11, "Cosmic");
        drawRune(30014, 12, "Blood");
        drawRune(30015, 13, "Soul");
        drawRune(30016, 14, "Astral");
        addLunar3RunesSmallBox(30017, 9075, 554, 555, 0, 4, 3, 30003, 30004, 64, "Bake Pie", "Bake pies without a stove", array, 0, 16, 2);
        addLunar2RunesSmallBox(30025, 9075, 557, 0, 7, 30006, 65, "Cure Plant", "Cure disease on farming patch", array, 1, 4, 2);
        addLunar3RunesBigBox(30032, 9075, 564, 558, 0, 0, 0, 30013, 30007, 65, "Monster Examine", "Detect the combat statistics of a\\nmonster", array, 2, 2, 2);
        addLunar3RunesSmallBox(30040, 9075, 564, 556, 0, 0, 1, 30013, 30005, 66, "NPC Contact", "Speak with varied NPCs", array, 3, 0, 2);
        addLunar3RunesSmallBox(30048, 9075, 563, 557, 0, 0, 9, 30012, 30006, 67, "Cure Other", "Cure poisoned players", array, 4, 8, 2);
        addLunar3RunesSmallBox(30056, 9075, 555, 554, 0, 2, 0, 30004, 30003, 67, "Humidify", "fills certain vessels with water", array, 5, 0, 5);
        addLunar3RunesSmallBox(30064, 9075, 563, 557, 1, 0, 1, 30012, 30006, 68, "Moonclan Teleport", "Teleports you to moonclan island", array, 6, 0, 5);
        addLunar3RunesBigBox(30075, 9075, 563, 557, 1, 0, 3, 30012, 30006, 69, "Tele Group Moonclan", "Teleports players to Moonclan\\nisland", array, 7, 0, 5);
        addLunar3RunesSmallBox(30083, 9075, 563, 557, 1, 0, 5, 30012, 30006, 70, "Ourania Teleport", "Teleports you to ourania rune altar", array, 8, 0, 5);
        addLunar3RunesSmallBox(30091, 9075, 564, 563, 1, 1, 0, 30013, 30012, 70, "Cure Me", "Cures Poison", array, 9, 0, 5);
        addLunar2RunesSmallBox(30099, 9075, 557, 1, 1, 30006, 70, "Hunter Kit", "Get a kit of hunting gear", array, 10, 0, 5);
        addLunar3RunesSmallBox(30106, 9075, 563, 555, 1, 0, 0, 30012, 30004, 71, "Waterbirth Teleport", "Teleports you to Waterbirth island", array, 11, 0, 5);
        addLunar3RunesBigBox(30114, 9075, 563, 555, 1, 0, 4, 30012, 30004, 72, "Tele Group Waterbirth", "Teleports players to Waterbirth\\nisland", array, 12, 0, 5);
        addLunar3RunesSmallBox(30122, 9075, 564, 563, 1, 1, 1, 30013, 30012, 73, "Cure Group", "Cures Poison on players", array, 13, 0, 5);
        addLunar3RunesBigBox(30130, 9075, 564, 559, 1, 1, 4, 30013, 30008, 74, "Stat Spy", "Cast on another player to see their\\nskill levels", array, 14, 8, 2);
        addLunar3RunesBigBox(30138, 9075, 563, 554, 1, 1, 2, 30012, 30003, 74, "Barbarian Teleport", "Teleports you to the Barbarian\\noutpost", array, 15, 0, 5);
        addLunar3RunesBigBox(30146, 9075, 563, 554, 1, 1, 5, 30012, 30003, 75, "Tele Group Barbarian", "Teleports players to the Barbarian\\noutpost", array, 16, 0, 5);
        addLunar3RunesSmallBox(30154, 9075, 554, 556, 1, 5, 9, 30003, 30005, 76, "Superglass Make", "Make glass without a furnace", array, 17, 16, 2);
        addLunar3RunesSmallBox(30162, 9075, 563, 555, 1, 1, 3, 30012, 30004, 77, "Khazard Teleport", "Teleports you to Port khazard", array, 18, 0, 5);
        addLunar3RunesSmallBox(30170, 9075, 563, 555, 1, 1, 7, 30012, 30004, 78, "Tele Group Khazard", "Teleports players to Port khazard", array, 19, 0, 5);
        addLunar3RunesBigBox(30178, 9075, 564, 559, 1, 0, 4, 30013, 30008, 78, "Dream", "Take a rest and restore hitpoints 3\\n times faster", array, 20, 0, 5);
        addLunar3RunesSmallBox(30186, 9075, 557, 555, 1, 9, 4, 30006, 30004, 79, "String Jewellery", "String amulets without wool", array, 21, 0, 5);
        addLunar3RunesLargeBox(30194, 9075, 557, 555, 1, 9, 9, 30006, 30004, 80, "Stat Restore Pot\\nShare", "Share a potion with up to 4 nearby\\nplayers", array, 22, 0, 5);
        addLunar3RunesSmallBox(30202, 9075, 554, 555, 1, 6, 6, 30003, 30004, 81, "Magic Imbue", "Combine runes without a talisman", array, 23, 0, 5);
        addLunar3RunesBigBox(30210, 9075, 561, 557, 2, 1, 14, 30010, 30006, 82, "Fertile Soil", "Fertilise a farming patch with super\\ncompost", array, 24, 4, 2);
        addLunar3RunesBigBox(30218, 9075, 557, 555, 2, 11, 9, 30006, 30004, 83, "Boost Potion Share", "Shares a potion with up to 4 nearby\\nplayers", array, 25, 0, 5);
        addLunar3RunesSmallBox(30226, 9075, 563, 555, 2, 2, 9, 30012, 30004, 84, "Fishing Guild Teleport", "Teleports you to the fishing guild", array, 26, 0, 5);
        addLunar3RunesLargeBox(30234, 9075, 563, 555, 1, 2, 13, 30012, 30004, 85, "Tele Group Fishing\\nGuild", "Teleports players to the Fishing\\nGuild", array, 27, 0, 5);
        addLunar3RunesSmallBox(30242, 9075, 557, 561, 2, 14, 0, 30006, 30010, 85, "Plank Make", "Turn Logs into planks", array, 28, 16, 5);
        addLunar3RunesSmallBox(30250, 9075, 563, 555, 2, 2, 9, 30012, 30004, 86, "Catherby Teleport", "Teleports you to Catherby", array, 29, 0, 5);
        addLunar3RunesSmallBox(30258, 9075, 563, 555, 2, 2, 14, 30012, 30004, 87, "Tele Group Catherby", "Teleports players to Catherby", array, 30, 0, 5);
        addLunar3RunesSmallBox(30266, 9075, 563, 555, 2, 2, 7, 30012, 30004, 88, "Ice Plateau Teleport", "Teleports you to Ice Plateau", array, 31, 0, 5);
        addLunar3RunesBigBox(30274, 9075, 563, 555, 2, 2, 15, 30012, 30004, 89, "Tele Group Ice\\n Plateau", "Teleports players to Ice Plateau", array, 32, 0, 5);
        addLunar3RunesBigBox(30282, 9075, 563, 561, 2, 1, 0, 30012, 30010, 90, "Energy Transfer", "Spend hitpoints and SA Energy to\\n give another player hitpoints and run energy", array, 33, 8, 2);
        addLunar3RunesBigBox(30290, 9075, 563, 565, 2, 2, 0, 30012, 30014, 91, "Heal Other", "Transfer up to 75% of hitpoints\\n to another player", array, 34, 8, 2);
        addLunar3RunesBigBox(30298, 9075, 560, 557, 2, 1, 9, 30009, 30006, 92, "Vengeance Other", "Allows another player to rebound\\ndamage to an opponent", array, 35, 8, 2);
        addLunar3RunesSmallBox(30306, 9075, 560, 557, 3, 1, 9, 30009, 30006, 93, "Vengeance", "Rebound damage to an opponent", array, 36, 0, 5);
        addLunar3RunesBigBox(30314, 9075, 565, 563, 3, 2, 5, 30014, 30012, 94, "Heal Group", "Transfer up to 75% of hitpoints to a group", array, 37, 0, 5);
        addLunar3RunesBigBox(30322, 9075, 564, 563, 2, 1, 0, 30013, 30012, 95, "Spellbook Swap", "Change to another spellbook for 1\\nspell cast", array, 38, 0, 5);
    }
    
    public static void constructLunar() {
        final RSInterface addTabInterface = addTabInterface(29999);
        setChildren(80, addTabInterface);
        setBounds(30000, 11, 10, 0, addTabInterface);
        setBounds(30017, 40, 9, 1, addTabInterface);
        setBounds(30025, 71, 12, 2, addTabInterface);
        setBounds(30032, 103, 10, 3, addTabInterface);
        setBounds(30040, 135, 12, 4, addTabInterface);
        setBounds(30048, 165, 10, 5, addTabInterface);
        setBounds(30056, 8, 38, 6, addTabInterface);
        setBounds(30064, 39, 39, 7, addTabInterface);
        setBounds(30075, 71, 39, 8, addTabInterface);
        setBounds(30083, 103, 39, 9, addTabInterface);
        setBounds(30091, 135, 39, 10, addTabInterface);
        setBounds(30099, 165, 37, 11, addTabInterface);
        setBounds(30106, 12, 68, 12, addTabInterface);
        setBounds(30114, 42, 68, 13, addTabInterface);
        setBounds(30122, 71, 68, 14, addTabInterface);
        setBounds(30130, 103, 68, 15, addTabInterface);
        setBounds(30138, 135, 68, 16, addTabInterface);
        setBounds(30146, 165, 68, 17, addTabInterface);
        setBounds(30154, 14, 97, 18, addTabInterface);
        setBounds(30162, 42, 97, 19, addTabInterface);
        setBounds(30170, 71, 97, 20, addTabInterface);
        setBounds(30178, 101, 97, 21, addTabInterface);
        setBounds(30186, 135, 98, 22, addTabInterface);
        setBounds(30194, 168, 98, 23, addTabInterface);
        setBounds(30202, 11, 125, 24, addTabInterface);
        setBounds(30210, 42, 124, 25, addTabInterface);
        setBounds(30218, 74, 125, 26, addTabInterface);
        setBounds(30226, 103, 125, 27, addTabInterface);
        setBounds(30234, 135, 125, 28, addTabInterface);
        setBounds(30242, 164, 126, 29, addTabInterface);
        setBounds(30250, 10, 155, 30, addTabInterface);
        setBounds(30258, 42, 155, 31, addTabInterface);
        setBounds(30266, 71, 155, 32, addTabInterface);
        setBounds(30274, 103, 155, 33, addTabInterface);
        setBounds(30282, 136, 155, 34, addTabInterface);
        setBounds(30290, 165, 155, 35, addTabInterface);
        setBounds(30298, 13, 185, 36, addTabInterface);
        setBounds(30306, 42, 185, 37, addTabInterface);
        setBounds(30314, 71, 184, 38, addTabInterface);
        setBounds(30322, 104, 184, 39, addTabInterface);
        setBounds(30001, 6, 184, 40, addTabInterface);
        setBounds(30018, 5, 176, 41, addTabInterface);
        setBounds(30026, 5, 176, 42, addTabInterface);
        setBounds(30033, 5, 163, 43, addTabInterface);
        setBounds(30041, 5, 176, 44, addTabInterface);
        setBounds(30049, 5, 176, 45, addTabInterface);
        setBounds(30057, 5, 176, 46, addTabInterface);
        setBounds(30065, 5, 176, 47, addTabInterface);
        setBounds(30076, 5, 163, 48, addTabInterface);
        setBounds(30084, 5, 176, 49, addTabInterface);
        setBounds(30092, 5, 176, 50, addTabInterface);
        setBounds(30100, 5, 176, 51, addTabInterface);
        setBounds(30107, 5, 176, 52, addTabInterface);
        setBounds(30115, 5, 163, 53, addTabInterface);
        setBounds(30123, 5, 176, 54, addTabInterface);
        setBounds(30131, 5, 163, 55, addTabInterface);
        setBounds(30139, 5, 163, 56, addTabInterface);
        setBounds(30147, 5, 163, 57, addTabInterface);
        setBounds(30155, 5, 176, 58, addTabInterface);
        setBounds(30163, 5, 176, 59, addTabInterface);
        setBounds(30171, 5, 176, 60, addTabInterface);
        setBounds(30179, 5, 163, 61, addTabInterface);
        setBounds(30187, 5, 176, 62, addTabInterface);
        setBounds(30195, 5, 149, 63, addTabInterface);
        setBounds(30203, 5, 176, 64, addTabInterface);
        setBounds(30211, 5, 163, 65, addTabInterface);
        setBounds(30219, 5, 163, 66, addTabInterface);
        setBounds(30227, 5, 176, 67, addTabInterface);
        setBounds(30235, 5, 149, 68, addTabInterface);
        setBounds(30243, 5, 176, 69, addTabInterface);
        setBounds(30251, 5, 5, 70, addTabInterface);
        setBounds(30259, 5, 5, 71, addTabInterface);
        setBounds(30267, 5, 5, 72, addTabInterface);
        setBounds(30275, 5, 5, 73, addTabInterface);
        setBounds(30283, 5, 5, 74, addTabInterface);
        setBounds(30291, 5, 5, 75, addTabInterface);
        setBounds(30299, 5, 5, 76, addTabInterface);
        setBounds(30307, 5, 5, 77, addTabInterface);
        setBounds(30323, 5, 5, 78, addTabInterface);
        setBounds(30315, 5, 5, 79, addTabInterface);
    }
    
    public static void addButtons(final int n, final int n2, final String s, final int width, final int height, final String tooltip, final int atActionType) {
        final RSInterface addInterface = addInterface(n);
        addInterface.id = n;
        addInterface.parentID = n;
        addInterface.type = 5;
        addInterface.atActionType = atActionType;
        addInterface.contentType = 0;
        addInterface.aByte254 = 0;
        addInterface.mOverInterToTrigger = 52;
        addInterface.sprite1 = imageLoader(n2, s);
        addInterface.sprite2 = imageLoader(n2, s);
        addInterface.width = width;
        addInterface.height = height;
        addInterface.tooltip = tooltip;
    }
    
    public static void addButton(final int n, final int n2, final String s, final int width, final int height, final String tooltip, final int atActionType) {
        final RSInterface addInterface = addInterface(n);
        addInterface.id = n;
        addInterface.parentID = n;
        addInterface.type = 5;
        addInterface.atActionType = atActionType;
        addInterface.contentType = 0;
        addInterface.aByte254 = 0;
        addInterface.mOverInterToTrigger = 52;
        addInterface.sprite1 = imageLoader(n2, s);
        addInterface.sprite2 = imageLoader(n2, s);
        addInterface.width = width;
        addInterface.height = height;
        addInterface.tooltip = tooltip;
    }
    
    static {
        RSInterface.pouchNames = new String[] { "Spirit Wolf", "Dreadfowl", "Spirit Spider", "Thorny Snail", "Granite Crab", "Spirit Mosquito", "Desert Wyrm", "Spirit Scorpian", "Spirit Tz-Kih", "Albino rat", "Spirit Kalphite", "Compost mound", "Giant Chinchompa", "Vampire Bat", "Honey badger", "Beaver", "Void Ravager", "Void Spinner", "Void Torcher", "Void Shifter", "Bronze minotaur", "Bull ant", "Macaw", "Evil Turnip", "Spirit Cockatrice", "Spirit Guthatrice", "Spirit Saratrice", "Spirit Zamatrice", "Spirit Pengatrice", "Spirit Coraxatrice", "Spirit Vulatrice", "Iron minotaur", "Pyrelord", "Magpie", "Bloated Leech", "Spirit terrorbird", "Abyssal Parasite", "Spirit Jelly", "Steel Minotaur", "Ibis", "Spirit kyatt", "Spirit laurpia", "Spirit graahk", "Karamthulhu overlord", "Smoke Devil", "Abyssal Lurker", "Spirit cobra", "Stranger Plant", "Mithril minotaur", "Barker Toad", "War tortoise", "Bunyip", "Fruit bat", "Ravenous Locust", "Artic Bear", "Pheonix", "Obsidian Golem", "Granite Lobster", "Praying mantis", "Forge regent", "Adamant minotaur", "Talon Beast", "Giant ent", "Fire titan", "Moss titan", "Ice titan", "Hydra", "Spirit daggannoth", "Lava titan", "Swamp titan", "Rune minotaur", "Unicorn Stallion", "Geyser titan", "Wolpertinger", "Abyssal Titan", "Iron titan", "Pack Yack", "Steel titan" };
        RSInterface.f = 0;
        RSInterface.f2 = 0;
        aMRUNodes_264 = new MRUNodes(30);
    }
}
