import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.net.URLConnection;
import java.util.Enumeration;
import java.io.BufferedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.applet.AppletContext;
import java.net.URL;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import javax.swing.JFrame;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class client extends RSApplet
{
    public static final int port = 43594;
    public static boolean canSwap;
    public Sprite magicAuto;
    public Sprite sumhover;
    public Sprite sumnormal;
    public boolean switched;
    public boolean Autocast;
    public int autocastId;
    boolean counterOn;
    private static final long serialVersionUID = 987987L;
    private boolean saveMaps;
    public long lastSwitch;
    public int[] positions;
    public TextDrawingArea2 newSmallFont;
    public TextDrawingArea2 newRegularFont;
    public TextDrawingArea2 newBoldFont;
    public TextDrawingArea2 newFancyFont;
    public int[] landScapes;
    int i;
    public int[] objects;
    public float LP;
    public int MapX;
    public int MapY;
    public static int spellID;
    public static boolean newDamage;
    public Sprite[] chatToggle;
    public boolean displayChat;
    public boolean isLoggingIn;
    public static int totalRead;
    private long timeMe;
    private int cButtonHPos;
    private int cButtonHCPos;
    private int cButtonCPos;
    public boolean isNewGameFrame;
    public boolean resizableFixed;
    public int tabHPos;
    Sprite[] counter;
    private int xpAddedPos;
    private int expAdded;
    private int xpCounter;
    private boolean xpLock;
    public static client instance;
    public String fName;
    public String dir;
    protected boolean webclient;
    public int world;
    public String spec;
    public static int zoom;
    private int zoomLevel;
    public int rights;
    public Sprite backgroundFix;
    public Sprite[] tabArea562LongFS;
    public Sprite[] tabArea562ShortFS;
    public Sprite logoutX;
    private Sprite loadingBarFull;
    private Sprite loadingBarEmpty;
    private Background titleBox;
    private Sprite boxHover;
    private Background titleButton;
    public String name;
    public String message;
    public String clanname;
    private final int[] chatRights;
    public int chatTypeView;
    public int clanChatMode;
    public int duelMode;
    private Sprite chatArea;
    private Sprite customMapArea;
    private Sprite CustomMapBack;
    private Sprite[] chatButtons;
    private Sprite tabArea;
    private Sprite tabArea2;
    private Sprite tabHover;
    private Sprite tabClicked;
    private Sprite[] newSideIcons;
    private Sprite mapArea;
    private RSImageProducer leftFrame;
    private RSImageProducer topFrame;
    private RSImageProducer rightFrame;
    private int ignoreCount;
    private long aLong824;
    private int[][] anIntArrayArray825;
    private int[] friendsNodeIDs;
    private NodeList[][][] groundArray;
    private int[] anIntArray828;
    private int[] anIntArray829;
    private volatile boolean aBoolean831;
    private Socket aSocket832;
    private int loginScreenState;
    private Stream aStream_834;
    private NPC[] npcArray;
    private int npcCount;
    private int[] npcIndices;
    private int anInt839;
    private int[] anIntArray840;
    private int anInt841;
    private int anInt842;
    private int anInt843;
    private String aString844;
    private int privateChatMode;
    private Stream aStream_847;
    private boolean aBoolean848;
    private static int anInt849;
    private int[] anIntArray850;
    private int[] anIntArray851;
    private int[] anIntArray852;
    private int[] anIntArray853;
    private static int anInt854;
    private int anInt855;
    static int openInterfaceID;
    private int xCameraPos;
    private int zCameraPos;
    private int yCameraPos;
    private int yCameraCurve;
    private int xCameraCurve;
    private int myPrivilege;
    private final int[] currentExp;
    private Sprite[] redStones;
    private Sprite mapFlag;
    private Sprite mapMarker;
    private boolean aBoolean872;
    private final int[] anIntArray873;
    private int anInt874;
    private final boolean[] aBooleanArray876;
    private int weight;
    private MouseDetection mouseDetection;
    private volatile boolean drawFlames;
    private String reportAbuseInput;
    private int unknownInt10;
    private boolean menuOpen;
    private int anInt886;
    private String inputString;
    private final int maxPlayers;
    private final int myPlayerIndex;
    private Player[] playerArray;
    private int playerCount;
    private int[] playerIndices;
    private int anInt893;
    private int[] anIntArray894;
    private Stream[] aStreamArray895s;
    private int anInt896;
    private int anInt897;
    private int friendsCount;
    private int anInt900;
    private int[][] anIntArrayArray901;
    private final int anInt902;
    private byte[] aByteArray912;
    private int anInt913;
    private int crossX;
    private int crossY;
    private int crossIndex;
    private int crossType;
    private int plane;
    private final int[] currentStats;
    private static int anInt924;
    private final long[] ignoreListAsLongs;
    private boolean loadingError;
    private final int anInt927;
    private final int[] anIntArray928;
    private int[][] anIntArrayArray929;
    private Sprite aClass30_Sub2_Sub1_Sub1_931;
    private Sprite aClass30_Sub2_Sub1_Sub1_932;
    private int anInt933;
    private int anInt934;
    private int anInt935;
    private int anInt936;
    private int anInt937;
    private int anInt938;
    private static int anInt940;
    private final int[] chatTypes;
    private final String[] chatNames;
    private final String[] chatMessages;
    private int anInt945;
    private WorldController worldController;
    private Sprite[] sideIcons;
    private int menuScreenArea;
    private int menuOffsetX;
    private int menuOffsetY;
    private int menuWidth;
    private int menuHeight;
    private long aLong953;
    private boolean aBoolean954;
    private long[] friendsListAsLongs;
    private String[] clanList;
    private int currentSong;
    private static int nodeID;
    static int portOff;
    static boolean clientData;
    private static boolean isMembers;
    private static boolean lowMem;
    private volatile boolean drawingFlames;
    private int spriteDrawX;
    private int spriteDrawY;
    private final int[] anIntArray965;
    private Background aBackground_966;
    private Background aBackground_967;
    private final int[] anIntArray968;
    private final int[] anIntArray969;
    final Decompressor[] decompressors;
    public int[] variousSettings;
    private boolean aBoolean972;
    private final int anInt975;
    private final int[] anIntArray976;
    private final int[] anIntArray977;
    private final int[] anIntArray978;
    private final int[] anIntArray979;
    private final int[] anIntArray980;
    private final int[] anIntArray981;
    private final int[] anIntArray982;
    private final String[] aStringArray983;
    private int anInt984;
    private int anInt985;
    private static int anInt986;
    private Sprite[] hitMarks;
    public Sprite[] combatIcons;
    private int anInt988;
    private int anInt989;
    private final int[] anIntArray990;
    private static boolean aBoolean993;
    private final boolean aBoolean994;
    private int anInt995;
    private int anInt996;
    private int anInt997;
    private int anInt998;
    private int anInt999;
    private ISAACRandomGen encryption;
    private Sprite mapEdge;
    private Sprite multiOverlay;
    private final int anInt1002;
    static final int[][] anIntArrayArray1003;
    private String amountOrNameInput;
    private static int anInt1005;
    private int daysSinceLastLogin;
    private int pktSize;
    private int pktType;
    private int anInt1009;
    private int anInt1010;
    private int anInt1011;
    private NodeList aClass19_1013;
    private int anInt1014;
    private int anInt1015;
    private int anInt1016;
    private boolean aBoolean1017;
    private int anInt1018;
    private static final int[] anIntArray1019;
    private int anInt1021;
    private int anInt1022;
    private int loadingStage;
    private Sprite scrollBar1;
    private Sprite scrollBar2;
    private int anInt1026;
    private Background backBase1;
    private Background backBase2;
    private Background backHmid1;
    private final int[] anIntArray1030;
    private boolean aBoolean1031;
    private Sprite[] mapFunctions;
    private int baseX;
    private int baseY;
    private int anInt1036;
    private int anInt1037;
    private int loginFailures;
    private int anInt1039;
    private int anInt1040;
    private int anInt1041;
    private int dialogID;
    private final int[] maxStats;
    private final int[] anIntArray1045;
    private int anInt1046;
    private boolean aBoolean1047;
    private int anInt1048;
    private String aString1049;
    private static int anInt1051;
    private final int[] anIntArray1052;
    private StreamLoader titleStreamLoader;
    private int anInt1054;
    private int anInt1055;
    private NodeList aClass19_1056;
    private final int[] anIntArray1057;
    public final RSInterface aClass9_1059;
    private Background[] mapScenes;
    private static int anInt1061;
    private int anInt1062;
    private final int barFillColor;
    private int friendsListAction;
    private final int[] anIntArray1065;
    private int mouseInvInterfaceIndex;
    private int lastActiveInvInterface;
    public OnDemandFetcher onDemandFetcher;
    private int anInt1069;
    private int anInt1070;
    private int anInt1071;
    private int[] anIntArray1072;
    private int[] anIntArray1073;
    private Sprite mapDotItem;
    private Sprite mapDotNPC;
    private Sprite mapDotPlayer;
    private Sprite mapDotFriend;
    private Sprite mapDotTeam;
    private Sprite mapDotClan;
    private int anInt1079;
    private boolean aBoolean1080;
    private String[] friendsList;
    private Stream inStream;
    private int anInt1084;
    private int anInt1085;
    private int activeInterfaceType;
    private int anInt1087;
    private int anInt1088;
    public static int anInt1089;
    private final int[] expectedCRCs;
    private int[] menuActionCmd2;
    private int[] menuActionCmd3;
    private int[] menuActionID;
    private int[] menuActionCmd1;
    private Sprite[] headIcons;
    private Sprite[] skullIcons;
    private Sprite[] headIconsHint;
    private static int anInt1097;
    private int anInt1098;
    private int anInt1099;
    private int anInt1100;
    private int anInt1101;
    private int anInt1102;
    private static boolean tabAreaAltered;
    private int anInt1104;
    private RSImageProducer aRSImageProducer_1107;
    private RSImageProducer aRSImageProducer_1108;
    private RSImageProducer aRSImageProducer_1109;
    private RSImageProducer aRSImageProducer_1110;
    private RSImageProducer aRSImageProducer_1111;
    private RSImageProducer aRSImageProducer_1112;
    private RSImageProducer aRSImageProducer_1113;
    private RSImageProducer aRSImageProducer_1114;
    private RSImageProducer aRSImageProducer_1115;
    private static int anInt1117;
    private int membersInt;
    private String aString1121;
    private Sprite compass;
    private RSImageProducer aRSImageProducer_1123;
    private RSImageProducer aRSImageProducer_1124;
    private RSImageProducer aRSImageProducer_1125;
    public static Player myPlayer;
    private final String[] atPlayerActions;
    private final boolean[] atPlayerArray;
    private final int[][][] anIntArrayArrayArray1129;
    public static final int[] tabInterfaceIDs;
    private int anInt1131;
    private int anInt1132;
    private int menuActionRow;
    private static int anInt1134;
    private int spellSelected;
    private int anInt1137;
    private int spellUsableOn;
    private String spellTooltip;
    private Sprite[] aClass30_Sub2_Sub1_Sub1Array1140;
    private boolean aBoolean1141;
    private static int anInt1142;
    private int energy;
    private boolean aBoolean1149;
    private Sprite[] crosses;
    private boolean musicEnabled;
    private Background[] aBackgroundArray1152s;
    private static boolean needDrawTabArea;
    private int unreadMessages;
    private static int anInt1155;
    private static boolean fpsOn;
    public boolean loggedIn;
    private boolean canMute;
    private boolean aBoolean1159;
    private boolean aBoolean1160;
    static int loopCycle;
    private static final String validUserPassChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"£$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";
    private RSImageProducer tabAreaIP;
    private RSImageProducer mapEdgeIP;
    private RSImageProducer mapAreaIP;
    private RSImageProducer gameScreenIP;
    private RSImageProducer chatAreaIP;
    private int daysSinceRecovChange;
    private RSSocket socketStream;
    private int anInt1169;
    private int minimapInt3;
    private int anInt1171;
    private long aLong1172;
    private String myUsername;
    private String myPassword;
    private static int anInt1175;
    private boolean genericLoadingError;
    private final int[] anIntArray1177;
    private int reportAbuseInterfaceID;
    private NodeList aClass19_1179;
    private int[] anIntArray1180;
    private int[] anIntArray1181;
    private int[] anIntArray1182;
    private byte[][] aByteArrayArray1183;
    private int anInt1184;
    private int minimapInt1;
    private int anInt1186;
    private int anInt1187;
    private static int anInt1188;
    private int invOverlayInterfaceID;
    private int[] anIntArray1190;
    private int[] anIntArray1191;
    private Stream stream;
    private int anInt1193;
    private int splitPrivateChat;
    private Background mapBack;
    public Sprite[] fsSprite;
    private String[] menuActionName;
    private Sprite aClass30_Sub2_Sub1_Sub1_1201;
    private Sprite aClass30_Sub2_Sub1_Sub1_1202;
    private final int[] anIntArray1203;
    static final int[] anIntArray1204;
    private static boolean flagged;
    private final int[] anIntArray1207;
    private int anInt1208;
    private int minimapInt2;
    private int anInt1210;
    private int anInt1211;
    private String promptInput;
    private int anInt1213;
    private int[][][] intGroundArray;
    private long aLong1215;
    private int loginScreenCursorPos;
    private Sprite[] modIcons;
    private long aLong1220;
    public static int tabID;
    private int anInt1222;
    public static boolean inputTaken;
    private int inputDialogState;
    private static int anInt1226;
    private int nextSong;
    private boolean songChanging;
    private final int[] anIntArray1229;
    private Class11[] aClass11Array1230;
    public static int[] anIntArray1232;
    private boolean aBoolean1233;
    private int[] anIntArray1234;
    private int[] anIntArray1235;
    private int[] anIntArray1236;
    private int anInt1237;
    private int anInt1238;
    public final int anInt1239 = 100;
    private final int[] anIntArray1240;
    private final int[] anIntArray1241;
    private boolean aBoolean1242;
    private int atInventoryLoopCycle;
    private int atInventoryInterface;
    private int atInventoryIndex;
    private int atInventoryInterfaceType;
    private byte[][] aByteArrayArray1247;
    private int tradeMode;
    private int anInt1249;
    private final int[] anIntArray1250;
    private int anInt1251;
    private final boolean rsAlreadyLoaded;
    private int anInt1253;
    private int anInt1254;
    private boolean welcomeScreenRaised;
    private boolean messagePromptRaised;
    private int anInt1257;
    private byte[][][] byteGroundArray;
    private int prevSong;
    private int destX;
    private int destY;
    private Sprite aClass30_Sub2_Sub1_Sub1_1263;
    private int anInt1264;
    private int anInt1265;
    private String loginMessage1;
    private String loginMessage2;
    private int anInt1268;
    private int anInt1269;
    private TextDrawingArea smallText;
    private TextDrawingArea aTextDrawingArea_1271;
    private TextDrawingArea chatTextDrawingArea;
    private int anInt1275;
    private int backDialogID;
    private int anInt1278;
    private int anInt1279;
    private int[] bigX;
    private int[] bigY;
    private int itemSelected;
    private int anInt1283;
    private int anInt1284;
    private int anInt1285;
    private String selectedItemName;
    private int publicChatMode;
    private static int anInt1288;
    private int anInt1289;
    public static int anInt1290;
    public static String server;
    public int drawCount;
    public int fullscreenInterfaceID;
    public int anInt1044;
    public int anInt1129;
    public int anInt1315;
    public int anInt1500;
    public int anInt1501;
    public int[] fullScreenTextureArray;
    private boolean newHitMarks;
    private boolean newHpBar;
    public JFrame frame;
    private Sprite HPBarFull;
    private Sprite HPBarEmpty;
    static boolean newHits;
    private boolean[] screenHover;
    private Sprite loginBox;
    private Sprite loginBoxHover;
    private Sprite loginHover;
    private Sprite loginHoverWorld;
    public Sprite[] globe;
    public Sprite[] ORBS;
    public static boolean[] globeState;
    boolean runHover;
    boolean runClicked;
    boolean prayHover;
    boolean prayClicked;
    private boolean safeZone;
    private Sprite[] pvpIcon;
    private boolean countToSafe;
    private boolean pvpWindow;
    private Sprite penal;
    private Sprite penal2;
    private Sprite bounty;
    private Sprite bounty2;
    private int count;
    
    public void musics() {
        for (int i = 0; i < 3536; ++i) {
            final byte[] getMusic = this.GetMusic(i);
            if (getMusic != null && getMusic.length > 0) {
                this.decompressors[3].method234(getMusic.length, getMusic, i);
            }
        }
    }
    
    public byte[] GetMusic(final int n) {
        try {
            final File file = new File(SignLink.findcachedir() + "/Music/" + n + ".gz");
            final byte[] array = new byte[(int)file.length()];
            final FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(array);
            fileInputStream.close();
            return array;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private void processMinimapActions() {
        final int mouseX = super.mouseX;
        final int mouseY = super.mouseY;
        if (mouseX >= 531 && mouseX <= 557 && mouseY >= 7 && mouseY <= 40) {
            this.menuActionName[1] = "Face North";
            this.menuActionID[1] = 477;
            this.menuActionRow = 2;
        }
        if (super.mouseX >= 691 && super.mouseX <= 745 && super.mouseY >= 130 && super.mouseY < 162) {
            this.menuActionID[1] = 1753;
            this.menuActionName[1] = "Select left-click option";
            this.menuActionRow = 2;
        }
        if (super.mouseX >= 707 && super.mouseX <= 762 && super.mouseY >= 92 && super.mouseY < 125) {
            if (!this.runClicked) {
                this.menuActionName[2] = "Turn run mode on";
            }
            else if (this.runClicked) {
                this.menuActionName[2] = "Turn run mode off";
            }
            this.menuActionID[2] = 1050;
            this.menuActionRow = 2;
            this.menuActionName[1] = "Rest";
            this.menuActionID[1] = 1501;
            this.menuActionRow = 3;
        }
        if (super.mouseX >= 706 && super.mouseX <= 762 && super.mouseY >= 52 && super.mouseY < 87) {
            if (!this.prayClicked) {
                this.menuActionName[2] = "Toggle Quick-Prayers on";
            }
            else if (this.prayClicked) {
                this.menuActionName[2] = "Toggle Quick-Prayers off";
            }
            this.menuActionID[2] = 1500;
            this.menuActionRow = 2;
            this.menuActionName[1] = "Select Quick-Prayers";
            this.menuActionID[1] = 1506;
            this.menuActionRow = 3;
        }
        if (super.mouseX >= 527 && super.mouseX <= 560 && super.mouseY >= 126 && super.mouseY <= 159) {
            this.menuActionName[1] = "World Map";
            this.menuActionID[1] = 1005;
            this.menuActionRow = 2;
        }
        if (super.mouseX >= 706 && super.mouseX <= 762 && super.mouseY >= 52 && super.mouseY < 87) {
            if (!this.prayClicked) {
                this.menuActionName[2] = "Turn quick prayers on";
            }
            else if (this.prayClicked) {
                this.menuActionName[2] = "Turn quick prayers off";
            }
            this.menuActionID[2] = 1500;
            this.menuActionRow = 2;
            this.menuActionName[1] = "Select quick prayers";
            this.menuActionID[1] = 1510;
            this.menuActionRow = 3;
        }
    }
    
    private void loadNewMap() {
        this.positions[1] = 8253;
        this.landScapes[1] = 1432;
        this.objects[1] = 1433;
        this.positions[2] = 8508;
        this.landScapes[2] = 1434;
        this.objects[2] = 1435;
        this.positions[3] = 8509;
        this.landScapes[3] = 1436;
        this.objects[3] = 1437;
        this.positions[4] = 8252;
        this.landScapes[4] = 1430;
        this.objects[4] = 1431;
        this.positions[5] = 9019;
        this.landScapes[5] = 1462;
        this.objects[5] = 1463;
        this.positions[6] = 9020;
        this.landScapes[6] = 1464;
        this.objects[6] = 1465;
        this.positions[7] = 9275;
        this.landScapes[7] = 1754;
        this.objects[7] = 1755;
        this.positions[8] = 9276;
        this.landScapes[8] = 1756;
        this.objects[8] = 1757;
        this.positions[9] = 11347;
        this.landScapes[9] = 1852;
        this.objects[9] = 1853;
        this.positions[10] = 11346;
        this.landScapes[10] = 1854;
        this.objects[10] = 1855;
        this.positions[11] = 11603;
        this.landScapes[11] = 1858;
        this.objects[11] = 1859;
        this.positions[12] = 11602;
        this.landScapes[12] = 1860;
        this.objects[12] = 1861;
        this.positions[19] = 11353;
        this.landScapes[19] = 1962;
        this.objects[19] = 1963;
        this.positions[20] = 11097;
        this.landScapes[20] = 1970;
        this.objects[20] = 1971;
        this.positions[21] = 11098;
        this.landScapes[21] = 1972;
        this.objects[21] = 1973;
        this.positions[22] = 10583;
        this.landScapes[22] = 1976;
        this.objects[22] = 1977;
        this.positions[13] = 11352;
        this.landScapes[13] = 1928;
        this.objects[13] = 1929;
        this.positions[14] = 10840;
        this.landScapes[14] = 1932;
        this.objects[14] = 1933;
        this.positions[15] = 10842;
        this.landScapes[15] = 1934;
        this.objects[15] = 1935;
        this.positions[16] = 11354;
        this.landScapes[16] = 1938;
        this.objects[16] = 1939;
        this.positions[17] = 11096;
        this.landScapes[17] = 1942;
        this.objects[17] = 1943;
        this.positions[18] = 10841;
        this.landScapes[18] = 1950;
        this.objects[18] = 1951;
    }
    
    public void drawSmoothLoading(final int n, final String s) {
        for (float lp = this.LP; lp < n; lp += 0.3) {
            this.drawLoadingText((int)lp, s);
        }
        this.LP = n;
    }
    
    public void drawSmoothLoading2(final int n, final String s) {
        for (float lp = this.LP; lp < n; lp += 0.3) {
            this.drawLoadingText2((int)lp, s);
        }
        this.LP = n;
    }
    
    public void replyLastPM() {
        String substring = null;
        for (int i = 0; i < 100; ++i) {
            if (this.chatMessages[i] != null) {
                final int n = this.chatTypes[i];
                if (n == 3 || n == 7) {
                    substring = this.chatNames[i];
                    break;
                }
            }
        }
        if (substring == null) {
            this.pushMessage("You haven't received any messages to which you can reply.", 0, "");
            return;
        }
        if (substring.startsWith("@cr")) {
            substring = substring.substring(5);
        }
        final long longForName = TextClass.longForName(substring.trim());
        int n2 = -1;
        for (int j = 0; j < this.friendsCount; ++j) {
            if (this.friendsListAsLongs[j] == longForName) {
                n2 = j;
                break;
            }
        }
        if (n2 != -1) {
            if (this.friendsNodeIDs[n2] > 0) {
                client.inputTaken = true;
                this.inputDialogState = 0;
                this.messagePromptRaised = true;
                this.promptInput = "";
                this.friendsListAction = 3;
                this.aLong953 = this.friendsListAsLongs[n2];
                this.aString1121 = "Enter message to send to " + this.friendsList[n2];
            }
            else {
                this.pushMessage("That player is currently offline.", 0, "");
            }
        }
    }
    
    public void toggleChat() {
        if (this.displayChat) {
            this.displayChat = false;
        }
        else if (!this.displayChat) {
            this.displayChat = true;
        }
    }
    
    public void drawBorder(int i, int j, final int n, final int n2) {
        final int n3 = i;
        final int n4 = j;
        while (i < n3 + n - 30) {
            this.fsSprite[5].drawSprite(i, n4);
            this.fsSprite[7].drawSprite(i, n4 + n2 - 7);
            i += 32;
        }
        while (j < n4 + n2 - 30) {
            this.fsSprite[6].drawSprite(n3, j);
            this.fsSprite[8].drawSprite(n3 + n - 7, j);
            j += 32;
        }
        this.fsSprite[9].drawSprite(n3, n4);
        this.fsSprite[10].drawSprite(n3 + n - 32, n4);
        this.fsSprite[11].drawSprite(n3, n4 + n2 - 32);
        this.fsSprite[12].drawSprite(n3 + n - 32, n4 + n2 - 32);
    }
    
    public static String capitalize(String s) {
        for (int i = 0; i < s.length(); ++i) {
            if (i == 0) {
                s = String.format("%s%s", Character.toUpperCase(s.charAt(0)), s.substring(1));
            }
            if (!Character.isLetterOrDigit(s.charAt(i)) && i + 1 < s.length()) {
                s = String.format("%s%s%s", s.subSequence(0, i + 1), Character.toUpperCase(s.charAt(i + 1)), s.substring(i + 2));
            }
        }
        return s;
    }
    
    private static String intToKOrMilLongName(final int n) {
        String s = String.valueOf(n);
        for (int i = s.length() - 3; i > 0; i -= 3) {
            s = s.substring(0, i) + "," + s.substring(i);
        }
        if (s.length() > 8) {
            s = "@gre@" + s.substring(0, s.length() - 8) + " million @whi@(" + s + ")";
        }
        else if (s.length() > 4) {
            s = "@cya@" + s.substring(0, s.length() - 4) + "K @whi@(" + s + ")";
        }
        return " " + s;
    }
    
    public final String methodR(final int n) {
        if (n >= 0 && n < 10000) {
            return String.valueOf(n);
        }
        if (n >= 10000 && n < 10000000) {
            return n / 1000 + "K";
        }
        if (n >= 10000000 && n < 999999999) {
            return n / 1000000 + "M";
        }
        if (n >= 999999999) {
            return "*";
        }
        return "?";
    }
    
    public static final byte[] ReadFile(final String s) {
        try {
            final int n = (int)new File(s).length();
            final byte[] array = new byte[n];
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(s)));
            dataInputStream.readFully(array, 0, n);
            dataInputStream.close();
            return array;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private void stopMidi() {
        if (SoundProvider.getSingleton().midiSequencer.isRunning()) {
            SoundProvider.getSingleton().midiSequencer.stop();
        }
        SignLink.midifade = 0;
        SignLink.midi = "stop";
    }
    
    private boolean menuHasAddFriend(final int n) {
        if (n < 0) {
            return false;
        }
        int n2 = this.menuActionID[n];
        if (n2 >= 2000) {
            n2 -= 2000;
        }
        return n2 == 337;
    }
    
    public void drawChannelButtons(final int n, final int n2) {
        final String[] array = { "On", "Friends", "Off", "Hide" };
        final int[] array2 = { 65280, 16776960, 16711680, 65535 };
        switch (this.cButtonCPos) {
            case 0: {
                this.chatButtons[1].drawSprite(5 + n, 142 + n2);
                break;
            }
            case 1: {
                this.chatButtons[1].drawSprite(71 + n, 142 + n2);
                break;
            }
            case 2: {
                this.chatButtons[1].drawSprite(137 + n, 142 + n2);
                break;
            }
            case 3: {
                this.chatButtons[1].drawSprite(203 + n, 142 + n2);
                break;
            }
            case 4: {
                this.chatButtons[1].drawSprite(269 + n, 142 + n2);
                break;
            }
            case 5: {
                this.chatButtons[1].drawSprite(335 + n, 142 + n2);
                break;
            }
        }
        if (this.cButtonHPos == this.cButtonCPos) {
            switch (this.cButtonHPos) {
                case 0: {
                    this.chatButtons[2].drawSprite(5 + n, 142 + n2);
                    break;
                }
                case 1: {
                    this.chatButtons[2].drawSprite(71 + n, 142 + n2);
                    break;
                }
                case 2: {
                    this.chatButtons[2].drawSprite(137 + n, 142 + n2);
                    break;
                }
                case 3: {
                    this.chatButtons[2].drawSprite(203 + n, 142 + n2);
                    break;
                }
                case 4: {
                    this.chatButtons[2].drawSprite(269 + n, 142 + n2);
                    break;
                }
                case 5: {
                    this.chatButtons[2].drawSprite(335 + n, 142 + n2);
                    break;
                }
                case 6: {
                    this.chatButtons[3].drawSprite(404 + n, 142 + n2);
                    break;
                }
            }
        }
        else {
            switch (this.cButtonHPos) {
                case 0: {
                    this.chatButtons[0].drawSprite(5 + n, 142 + n2);
                    break;
                }
                case 1: {
                    this.chatButtons[0].drawSprite(71 + n, 142 + n2);
                    break;
                }
                case 2: {
                    this.chatButtons[0].drawSprite(137 + n, 142 + n2);
                    break;
                }
                case 3: {
                    this.chatButtons[0].drawSprite(203 + n, 142 + n2);
                    break;
                }
                case 4: {
                    this.chatButtons[0].drawSprite(269 + n, 142 + n2);
                    break;
                }
                case 5: {
                    this.chatButtons[0].drawSprite(335 + n, 142 + n2);
                    break;
                }
                case 6: {
                    this.chatButtons[3].drawSprite(404 + n, 142 + n2);
                    break;
                }
            }
        }
        this.smallText.method389(true, 425 + n, 16777215, "Report Abuse", 157 + n2);
        this.smallText.method389(true, 26 + n, 16777215, "All", 157 + n2);
        this.smallText.method389(true, 86 + n, 16777215, "Game", 157 + n2);
        this.smallText.method389(true, 150 + n, 16777215, "Public", 152 + n2);
        this.smallText.method389(true, 212 + n, 16777215, "Private", 152 + n2);
        this.smallText.method389(true, 286 + n, 16777215, "Clan", 152 + n2);
        this.smallText.method389(true, 349 + n, 16777215, "Trade", 152 + n2);
        this.smallText.method382(array2[this.publicChatMode], 164 + n, array[this.publicChatMode], 163 + n2, true);
        this.smallText.method382(array2[this.privateChatMode], 230 + n, array[this.privateChatMode], 163 + n2, true);
        this.smallText.method382(array2[this.clanChatMode], 296 + n, array[this.clanChatMode], 163 + n2, true);
        this.smallText.method382(array2[this.tradeMode], 362 + n, array[this.tradeMode], 163 + n2, true);
    }
    
    private void drawChatArea() {
        if (client.clientSize == 0 && this.chatAreaIP != null) {
            this.chatAreaIP.initDrawingArea();
        }
        final int n = (client.clientSize != 0) ? (client.clientHeight - 166) : 0;
        final int n2 = (client.clientSize != 0) ? 0 : 0;
        Texture.anIntArray1472 = this.anIntArray1180;
        final TextDrawingArea aTextDrawingArea_1271 = this.aTextDrawingArea_1271;
        if (this.displayChat || client.clientSize == 0) {
            if (client.clientSize == 0) {
                this.chatArea.drawSprite(n2 + 4, n);
                this.drawChannelButtons(n2 + 4, n);
            }
            else if (client.clientSize >= 1) {
                this.chatArea.drawSprite(n2, n);
                this.drawBorder(n2, n, 520, 142);
                this.drawChannelButtons(n2, n + 1);
            }
            if (this.messagePromptRaised) {
                this.chatTextDrawingArea.drawText(0, this.aString1121, 60 + n, 259 + n2);
                this.chatTextDrawingArea.drawText(128, this.promptInput + "*", 80 + n, 259 + n2);
            }
            else if (this.inputDialogState == 1) {
                this.chatTextDrawingArea.drawText(0, "Enter amount:", 60 + n, 259 + n2);
                this.chatTextDrawingArea.drawText(128, this.amountOrNameInput + "*", 80 + n, 259 + n2);
            }
            else if (this.inputDialogState == 2) {
                this.chatTextDrawingArea.drawText(0, "Enter name:", 60 + n, 259 + n2);
                this.chatTextDrawingArea.drawText(128, this.amountOrNameInput + "*", 80 + n, 259 + n2);
            }
            else if (this.aString844 != null) {
                this.chatTextDrawingArea.drawText(0, this.aString844, 60 + n, 259 + n2);
                this.chatTextDrawingArea.drawText(128, "Click to continue", 80 + n, 259 + n2);
            }
            else if (this.backDialogID != -1) {
                this.drawInterface(0, 20 + n2, RSInterface.interfaceCache[this.backDialogID], 20 + n);
            }
            else if (this.dialogID != -1) {
                this.drawInterface(0, 20 + n2, RSInterface.interfaceCache[this.dialogID], 20 + n);
            }
            else {
                int n3 = -3;
                int n4 = 0;
                DrawingArea.setDrawingArea(122 + n, 8 + n2, 497 + n2, 7 + n);
                for (int i = 0; i < 500; ++i) {
                    if (this.chatMessages[i] != null) {
                        final int n5 = this.chatTypes[i];
                        final int n6 = 70 - n3 * 14 + client.anInt1089 + 5;
                        String s = this.chatNames[i];
                        int n7 = 0;
                        if (s != null && s.startsWith("@cr1@")) {
                            s = s.substring(5);
                            n7 = 1;
                        }
                        else if (s != null && s.startsWith("@cr2@")) {
                            s = s.substring(5);
                            n7 = 2;
                        }
                        else if (s != null && s.startsWith("@cr3@")) {
                            s = s.substring(5);
                            n7 = 3;
                        }
                        else if (s != null && s.startsWith("@cr4@")) {
                            s = s.substring(5);
                            n7 = 4;
                        }
                        if (n5 == 0 && (this.chatTypeView == 5 || this.chatTypeView == 0)) {
                            if (n6 > 0 && n6 < 210) {
                                aTextDrawingArea_1271.method389(false, 11 + n2, 0, this.chatMessages[i], n6 + n);
                            }
                            ++n4;
                            ++n3;
                        }
                        if ((n5 == 1 || n5 == 2) && (n5 == 1 || this.publicChatMode == 0 || (this.publicChatMode == 1 && this.isFriendOrSelf(s))) && (this.chatTypeView == 1 || this.chatTypeView == 0)) {
                            if (n6 > 0 && n6 < 210) {
                                int n8 = 11;
                                if (n7 == 1) {
                                    this.modIcons[0].drawSprite(n8 + 1 + n2, n6 - 12 + n);
                                    n8 += 14;
                                }
                                else if (n7 == 2) {
                                    this.modIcons[1].drawSprite(n8 + 1 + n2, n6 - 12 + n);
                                    n8 += 14;
                                }
                                else if (n7 == 3) {
                                    this.modIcons[2].drawSprite(n8 + 1 + n2, n6 - 12 + n);
                                    n8 += 14;
                                }
                                else if (n7 == 4) {
                                    this.modIcons[3].drawSprite(n8 + 1 + n2, n6 - 12 + n);
                                    n8 += 14;
                                }
                                aTextDrawingArea_1271.method385(0, s + ":", n6 + n, n8 + n2);
                                aTextDrawingArea_1271.method389(false, n8 + (aTextDrawingArea_1271.getTextWidth(s) + 8) + n2, 255, this.chatMessages[i], n6 + n);
                            }
                            ++n4;
                            ++n3;
                        }
                        if ((n5 == 3 || n5 == 7) && (this.splitPrivateChat == 0 || this.chatTypeView == 2) && (n5 == 7 || this.privateChatMode == 0 || (this.privateChatMode == 1 && this.isFriendOrSelf(s))) && (this.chatTypeView == 2 || this.chatTypeView == 0)) {
                            if (n6 > 0 && n6 < 210) {
                                final int n9 = 11;
                                aTextDrawingArea_1271.method385(0, "From", n6 + n, n9 + n2);
                                int n10 = n9 + aTextDrawingArea_1271.getTextWidth("From ");
                                if (n7 == 1) {
                                    this.modIcons[0].drawSprite(n10 + n2, n6 + n - 12);
                                    n10 += 12;
                                }
                                else if (n7 == 2) {
                                    this.modIcons[1].drawSprite(n10 + n2, n6 + n - 12);
                                    n10 += 12;
                                }
                                else if (n7 == 3) {
                                    this.modIcons[2].drawSprite(n10 + n2, n6 + n - 12);
                                    n10 += 12;
                                }
                                else if (n7 == 4) {
                                    this.modIcons[3].drawSprite(n10 + n2, n6 + n - 12);
                                    n10 += 12;
                                }
                                aTextDrawingArea_1271.method385(0, s + ":", n6 + n, n10 + n2);
                                aTextDrawingArea_1271.method385(8388608, this.chatMessages[i], n6 + n, n10 + (aTextDrawingArea_1271.getTextWidth(s) + 8) + n2);
                            }
                            ++n4;
                            ++n3;
                        }
                        if (n5 == 4 && (this.tradeMode == 0 || (this.tradeMode == 1 && this.isFriendOrSelf(s))) && (this.chatTypeView == 3 || this.chatTypeView == 0)) {
                            if (n6 > 0 && n6 < 210) {
                                aTextDrawingArea_1271.method385(8388736, s + " " + this.chatMessages[i], n6 + n, 11 + n2);
                            }
                            ++n4;
                            ++n3;
                        }
                        if (n5 == 5 && this.splitPrivateChat == 0 && this.privateChatMode < 2 && (this.chatTypeView == 2 || this.chatTypeView == 0)) {
                            if (n6 > 0 && n6 < 210) {
                                aTextDrawingArea_1271.method385(8388608, this.chatMessages[i], n6 + n, 11 + n2);
                            }
                            ++n4;
                            ++n3;
                        }
                        if (n5 == 6 && (this.splitPrivateChat == 0 || this.chatTypeView == 2) && this.privateChatMode < 2 && (this.chatTypeView == 2 || this.chatTypeView == 0)) {
                            if (n6 > 0 && n6 < 210) {
                                aTextDrawingArea_1271.method385(0, "To " + s + ":", n6 + n, 11 + n2);
                                aTextDrawingArea_1271.method385(8388608, this.chatMessages[i], n6, 15 + aTextDrawingArea_1271.getTextWidth("To :" + s));
                            }
                            ++n4;
                            ++n3;
                        }
                        if (n5 == 8 && (this.tradeMode == 0 || (this.tradeMode == 1 && this.isFriendOrSelf(s)))) {
                            if (this.chatTypeView == 3 || this.chatTypeView == 0) {
                                if (n6 > 0 && n6 < 210) {
                                    aTextDrawingArea_1271.method385(8270336, s + " " + this.chatMessages[i], n6 + n, 11 + n2);
                                }
                                ++n4;
                                ++n3;
                            }
                            if (n5 == 11 && this.clanChatMode == 0) {
                                if (this.chatTypeView == 11) {
                                    if (n6 > 0 && n6 < 210) {
                                        aTextDrawingArea_1271.method385(8270336, s + " " + this.chatMessages[i], n6 + n, 11 + n2);
                                    }
                                    ++n4;
                                    ++n3;
                                }
                                if (n5 == 12) {
                                    if (n6 > 0 && n6 < 110) {
                                        aTextDrawingArea_1271.method385(8270336, this.chatMessages[i] + " @blu@" + s, n6 + n, 11 + n2);
                                    }
                                    ++n4;
                                }
                            }
                        }
                        if (n5 == 16) {
                            int n11 = 40;
                            final int textWidth = aTextDrawingArea_1271.getTextWidth(this.clanname);
                            if (this.chatTypeView == 11 || this.chatTypeView == 0) {
                                if (n6 > 3 && n6 < 130) {
                                    switch (this.chatRights[i]) {
                                        case 1: {
                                            n11 += textWidth;
                                            this.modIcons[0].drawSprite(n11 - 18 + n2, n6 - 12 + n);
                                            n11 += 14;
                                            break;
                                        }
                                        case 2: {
                                            n11 += textWidth;
                                            this.modIcons[1].drawSprite(n11 - 18 + n2, n6 - 12 + n);
                                            n11 += 14;
                                            break;
                                        }
                                        case 3: {
                                            n11 += textWidth;
                                            this.modIcons[1].drawSprite(n11 - 18 + n2, n6 - 12 + n);
                                            n11 += 14;
                                            break;
                                        }
                                        case 4: {
                                            n11 += textWidth;
                                            this.modIcons[3].drawSprite(n11 - 18 + n2, n6 - 12 + n);
                                            n11 += 14;
                                            break;
                                        }
                                        default: {
                                            n11 += textWidth;
                                            break;
                                        }
                                    }
                                }
                                aTextDrawingArea_1271.method385(0, "[", n6 + n, 8 + n2);
                                aTextDrawingArea_1271.method385(255, "" + this.clanname + "", n6 + n, 14 + n2);
                                aTextDrawingArea_1271.method385(0, "]", n6 + n, textWidth + 14 + n2);
                                aTextDrawingArea_1271.method385(0, this.chatNames[i] + ":", n6 + n, n11 - 17 + n2);
                                aTextDrawingArea_1271.method385(8388608, this.chatMessages[i], n6 + n, n11 + (aTextDrawingArea_1271.getTextWidth(this.chatNames[i]) + 7) - 16 + n2);
                                ++n4;
                                ++n3;
                            }
                        }
                    }
                }
                DrawingArea.defaultDrawingAreaSize();
                this.anInt1211 = n4 * 14 + 7 + 5;
                if (this.anInt1211 < 111) {
                    this.anInt1211 = 111;
                }
                this.drawScrollbar(114, this.anInt1211 - client.anInt1089 - 113, 7 + n, 496 + n2, this.anInt1211);
                String s2;
                if (client.myPlayer != null && client.myPlayer.name != null) {
                    s2 = client.myPlayer.name;
                }
                else {
                    s2 = TextClass.fixName(this.myUsername);
                }
                aTextDrawingArea_1271.method385(0, s2 + ":", 133 + n, 11 + n2);
                aTextDrawingArea_1271.drawChatInput(255, 12 + aTextDrawingArea_1271.getTextWidth(s2 + ": "), this.inputString + "*", 133 + n, false);
                DrawingArea.method339(121 + n, 8418912, 506, 7 + n2);
            }
            if (this.menuOpen && this.menuScreenArea == 2) {
                this.drawMenu();
            }
        }
        if (!this.displayChat && client.clientSize != 0) {
            this.chatToggle[1].drawSprite(247 + n2, client.clientHeight - 15);
            this.chatArea.drawSprite(0 + n2, client.clientHeight - 7);
        }
        if (client.clientSize == 0 && this.chatAreaIP != null && super.graphics != null) {
            this.chatAreaIP.drawGraphics(338, super.graphics, 0);
            this.gameScreenIP.initDrawingArea();
            Texture.anIntArray1472 = this.anIntArray1182;
        }
    }
    
    @Override
    public void init() {
        try {
            client.nodeID = 10;
            client.portOff = 0;
            client.clientHeight = 503;
            client.clientWidth = 765;
            setHighMem();
            client.isMembers = true;
            SignLink.startpriv(InetAddress.getLocalHost());
            (client.instance = this).initClientFrame(client.clientHeight, client.clientWidth);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void startRunnable(final Runnable runnable, int n) {
        if (n > 10) {
            n = 10;
        }
        if (SignLink.mainapp != null) {
            SignLink.startthread(runnable, n);
        }
        else {
            super.startRunnable(runnable, n);
        }
    }
    
    public Socket openSocket(final int n) throws IOException {
        return new Socket(InetAddress.getByName(client.server), n);
    }
    
    private void processMenuClick() {
        if (this.activeInterfaceType != 0) {
            return;
        }
        int clickMode3 = super.clickMode3;
        if (this.spellSelected == 1 && super.saveClickX >= 516 && super.saveClickY >= 160 && super.saveClickX <= 765 && super.saveClickY <= 205) {
            clickMode3 = 0;
        }
        if (this.menuOpen) {
            if (clickMode3 != 1) {
                int mouseX = super.mouseX;
                int mouseY = super.mouseY;
                if (this.menuScreenArea == 0) {
                    mouseX -= 4;
                    mouseY -= 4;
                }
                if (this.menuScreenArea == 1) {
                    mouseX -= 519;
                    mouseY -= 168;
                }
                if (this.menuScreenArea == 2) {
                    mouseX -= 17;
                    mouseY -= 338;
                }
                if (this.menuScreenArea == 3) {
                    mouseX -= 519;
                    mouseY += 0;
                }
                if (mouseX < this.menuOffsetX - 10 || mouseX > this.menuOffsetX + this.menuWidth + 10 || mouseY < this.menuOffsetY - 10 || mouseY > this.menuOffsetY + this.menuHeight + 10) {
                    this.menuOpen = false;
                    if (this.menuScreenArea == 1) {
                        client.needDrawTabArea = true;
                    }
                    if (this.menuScreenArea == 2) {
                        client.inputTaken = true;
                    }
                }
            }
            if (clickMode3 == 1) {
                final int menuOffsetX = this.menuOffsetX;
                final int menuOffsetY = this.menuOffsetY;
                final int menuWidth = this.menuWidth;
                int saveClickX = super.saveClickX;
                int saveClickY = super.saveClickY;
                if (this.menuScreenArea == 0) {
                    saveClickX -= 4;
                    saveClickY -= 4;
                }
                if (this.menuScreenArea == 1) {
                    saveClickX -= 519;
                    saveClickY -= 168;
                }
                if (this.menuScreenArea == 2) {
                    saveClickX -= 17;
                    saveClickY -= 338;
                }
                if (this.menuScreenArea == 3) {
                    saveClickX -= 519;
                    saveClickY += 0;
                }
                int n = -1;
                for (int i = 0; i < this.menuActionRow; ++i) {
                    final int n2 = menuOffsetY + 31 + (this.menuActionRow - 1 - i) * 15;
                    if (saveClickX > menuOffsetX && saveClickX < menuOffsetX + menuWidth && saveClickY > n2 - 13 && saveClickY < n2 + 3) {
                        n = i;
                    }
                }
                if (n != -1) {
                    this.doAction(n);
                }
                this.menuOpen = false;
                if (this.menuScreenArea == 1) {
                    client.needDrawTabArea = true;
                }
                if (this.menuScreenArea == 2) {
                    client.inputTaken = true;
                }
            }
        }
        else {
            if (clickMode3 == 1 && this.menuActionRow > 0) {
                final int n3 = this.menuActionID[this.menuActionRow - 1];
                if (n3 == 632 || n3 == 78 || n3 == 867 || n3 == 431 || n3 == 53 || n3 == 74 || n3 == 454 || n3 == 539 || n3 == 493 || n3 == 847 || n3 == 447 || n3 == 1125) {
                    final int anInt1085 = this.menuActionCmd2[this.menuActionRow - 1];
                    final int anInt1086 = this.menuActionCmd3[this.menuActionRow - 1];
                    final RSInterface rsInterface = RSInterface.interfaceCache[anInt1086];
                    if (rsInterface.aBoolean259 || rsInterface.aBoolean235) {
                        this.aBoolean1242 = false;
                        this.anInt989 = 0;
                        this.anInt1084 = anInt1086;
                        this.anInt1085 = anInt1085;
                        this.activeInterfaceType = 2;
                        this.anInt1087 = super.saveClickX;
                        this.anInt1088 = super.saveClickY;
                        if (RSInterface.interfaceCache[anInt1086].parentID == client.openInterfaceID) {
                            this.activeInterfaceType = 1;
                        }
                        if (RSInterface.interfaceCache[anInt1086].parentID == this.backDialogID) {
                            this.activeInterfaceType = 3;
                        }
                        return;
                    }
                }
            }
            if (clickMode3 == 1 && (this.anInt1253 == 1 || this.menuHasAddFriend(this.menuActionRow - 1)) && this.menuActionRow > 2) {
                clickMode3 = 2;
            }
            if (clickMode3 == 1 && this.menuActionRow > 0) {
                this.doAction(this.menuActionRow - 1);
            }
            if (clickMode3 == 2 && this.menuActionRow > 0) {
                this.determineMenuSize();
            }
        }
    }
    
    public static String getFileNameWithoutExtension(final String s) {
        final File file = new File(s);
        file.getName();
        final int lastIndex = file.getName().lastIndexOf(46);
        if (0 < lastIndex && lastIndex <= file.getName().length() - 2) {
            return file.getName().substring(0, lastIndex);
        }
        return "";
    }
    
    public static void writeFile(final byte[] array, final String s) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(s);
        fileOutputStream.write(array);
        fileOutputStream.close();
    }
    
    private void method22() {
        boolean b = false;
        try {
            this.anInt985 = -1;
            this.aClass19_1056.removeAll();
            this.aClass19_1013.removeAll();
            Texture.method366();
            this.unlinkMRUNodes();
            this.worldController.initToNull();
            System.gc();
            for (int i = 0; i < 4; ++i) {
                this.aClass11Array1230[i].method210();
            }
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 104; ++k) {
                    for (int l = 0; l < 104; ++l) {
                        this.byteGroundArray[j][k][l] = 0;
                    }
                }
            }
            final ObjectManager objectManager = new ObjectManager(this.byteGroundArray, this.intGroundArray);
            final int length = this.aByteArrayArray1183.length;
            for (int n = 0; n < length; ++n) {
                for (int n2 = 1; n2 < this.positions.length; ++n2) {
                    if (this.anIntArray1234[n] == this.positions[n2]) {
                        this.anIntArray1235[n] = this.landScapes[n2];
                        this.anIntArray1236[n] = this.objects[n2];
                    }
                }
            }
            this.stream.createFrame(0);
            if (!this.aBoolean1159) {
                for (int n3 = 0; n3 < length; ++n3) {
                    final int n4 = (this.anIntArray1234[n3] >> 8) * 64 - this.baseX;
                    final int n5 = (this.anIntArray1234[n3] & 0xFF) * 64 - this.baseY;
                    byte[] readFile = this.aByteArrayArray1183[n3];
                    if (FileOperations.FileExists(SignLink.findcachedir() + "Sprites/maps/" + this.anIntArray1235[n3] + ".dat")) {
                        readFile = FileOperations.ReadFile(SignLink.findcachedir() + "Sprites/maps/" + this.anIntArray1235[n3] + ".dat");
                        b = true;
                    }
                    if ((this.aByteArrayArray1183[n3] = readFile) != null) {
                        objectManager.method180(readFile, n5, n4, (this.anInt1069 - 6) * 8, (this.anInt1070 - 6) * 8, this.aClass11Array1230);
                    }
                }
                for (int n6 = 0; n6 < length; ++n6) {
                    final int n7 = (this.anIntArray1234[n6] >> 8) * 64 - this.baseX;
                    final int n8 = (this.anIntArray1234[n6] & 0xFF) * 64 - this.baseY;
                    if (this.aByteArrayArray1183[n6] == null && this.anInt1070 < 800) {
                        objectManager.method174(n8, 64, 64, n7);
                    }
                }
                ++client.anInt1097;
                if (client.anInt1097 > 160) {
                    client.anInt1097 = 0;
                }
                this.stream.createFrame(0);
                for (int n9 = 0; n9 < length; ++n9) {
                    byte[] readFile2 = this.aByteArrayArray1247[n9];
                    if (FileOperations.FileExists(SignLink.findcachedir() + "Sprites/maps/" + this.anIntArray1236[n9] + ".dat")) {
                        readFile2 = FileOperations.ReadFile(SignLink.findcachedir() + "Sprites/maps/" + this.anIntArray1236[n9] + ".dat");
                        b = true;
                    }
                    if ((this.aByteArrayArray1247[n9] = readFile2) != null) {
                        objectManager.method190((this.anIntArray1234[n9] >> 8) * 64 - this.baseX, this.aClass11Array1230, (this.anIntArray1234[n9] & 0xFF) * 64 - this.baseY, this.worldController, readFile2);
                    }
                }
            }
            if (this.aBoolean1159) {
                for (int n10 = 0; n10 < 4; ++n10) {
                    for (int n11 = 0; n11 < 13; ++n11) {
                        for (int n12 = 0; n12 < 13; ++n12) {
                            final int n13 = this.anIntArrayArrayArray1129[n10][n11][n12];
                            if (n13 != -1) {
                                final int n14 = n13 >> 24 & 0x3;
                                final int n15 = n13 >> 1 & 0x3;
                                final int n16 = n13 >> 14 & 0x3FF;
                                final int n17 = n13 >> 3 & 0x7FF;
                                final int n18 = (n16 / 8 << 8) + n17 / 8;
                                for (int n19 = 0; n19 < this.anIntArray1234.length; ++n19) {
                                    if (this.anIntArray1234[n19] == n18 && this.aByteArrayArray1183[n19] != null) {
                                        objectManager.method179(n14, n15, this.aClass11Array1230, n11 * 8, (n16 & 0x7) * 8, this.aByteArrayArray1183[n19], (n17 & 0x7) * 8, n10, n12 * 8);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                for (int n20 = 0; n20 < 13; ++n20) {
                    for (int n21 = 0; n21 < 13; ++n21) {
                        if (this.anIntArrayArrayArray1129[0][n20][n21] == -1) {
                            objectManager.method174(n21 * 8, 8, 8, n20 * 8);
                        }
                    }
                }
                this.stream.createFrame(0);
                for (int n22 = 0; n22 < 4; ++n22) {
                    for (int n23 = 0; n23 < 13; ++n23) {
                        for (int n24 = 0; n24 < 13; ++n24) {
                            final int n25 = this.anIntArrayArrayArray1129[n22][n23][n24];
                            if (n25 != -1) {
                                final int n26 = n25 >> 24 & 0x3;
                                final int n27 = n25 >> 1 & 0x3;
                                final int n28 = n25 >> 14 & 0x3FF;
                                final int n29 = n25 >> 3 & 0x7FF;
                                final int n30 = (n28 / 8 << 8) + n29 / 8;
                                for (int n31 = 0; n31 < this.anIntArray1234.length; ++n31) {
                                    if (this.anIntArray1234[n31] == n30) {
                                        if (this.aByteArrayArray1247[n31] != null) {
                                            objectManager.method183(this.aClass11Array1230, this.worldController, n26, n23 * 8, (n29 & 0x7) * 8, n22, this.aByteArrayArray1247[n31], (n28 & 0x7) * 8, n27, n24 * 8);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            this.stream.createFrame(0);
            objectManager.method171(this.aClass11Array1230, this.worldController);
            this.gameScreenIP.initDrawingArea();
            this.stream.createFrame(0);
            int n32 = ObjectManager.anInt145;
            if (n32 > this.plane) {
                n32 = this.plane;
            }
            if (n32 < this.plane - 1) {
                final int n33 = this.plane - 1;
            }
            this.worldController.method275(0);
            for (int n34 = 0; n34 < 104; ++n34) {
                for (int n35 = 0; n35 < 104; ++n35) {
                    this.spawnGroundItem(n34, n35);
                }
            }
            ++client.anInt1051;
            if (client.anInt1051 > 98) {
                client.anInt1051 = 0;
                this.stream.createFrame(150);
            }
            this.method63();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        ObjectDef.mruNodes1.unlinkAll();
        if (super.gameFrame != null) {
            this.stream.createFrame(210);
            this.stream.writeDWord(1057001181);
        }
        System.gc();
        Texture.method367();
        this.onDemandFetcher.method566();
        int n36 = (this.anInt1069 - 6) / 8 - 1;
        int n37 = (this.anInt1069 + 6) / 8 + 1;
        int n38 = (this.anInt1070 - 6) / 8 - 1;
        int n39 = (this.anInt1070 + 6) / 8 + 1;
        if (this.aBoolean1141) {
            n36 = 49;
            n37 = 50;
            n38 = 49;
            n39 = 50;
        }
        for (int n40 = n36; n40 <= n37; ++n40) {
            for (int n41 = n38; n41 <= n39; ++n41) {
                if (n40 == n36 || n40 == n37 || n41 == n38 || n41 == n39) {
                    final int method562 = this.onDemandFetcher.method562(0, n41, n40);
                    if (method562 != -1) {
                        this.onDemandFetcher.method560(method562, 3);
                    }
                    final int method563 = this.onDemandFetcher.method562(1, n41, n40);
                    if (method563 != -1) {
                        this.onDemandFetcher.method560(method563, 3);
                    }
                }
            }
        }
        if (b && System.currentTimeMillis() - this.timeMe > 3000L) {
            this.loadingStage = 1;
            this.timeMe = System.currentTimeMillis();
            this.loadingStages();
        }
    }
    
    private void unlinkMRUNodes() {
        ObjectDef.mruNodes1.unlinkAll();
        ObjectDef.mruNodes2.unlinkAll();
        EntityDef.mruNodes.unlinkAll();
        ItemDef.mruNodes2.unlinkAll();
        ItemDef.mruNodes1.unlinkAll();
        Player.mruNodes.unlinkAll();
        SpotAnim.aMRUNodes_415.unlinkAll();
    }
    
    private void method24(final int n) {
        final int[] myPixels = this.aClass30_Sub2_Sub1_Sub1_1263.myPixels;
        for (int length = myPixels.length, i = 0; i < length; ++i) {
            myPixels[i] = 0;
        }
        for (int j = 1; j < 103; ++j) {
            int n2 = 24628 + (103 - j) * 512 * 4;
            for (int k = 1; k < 103; ++k) {
                if ((this.byteGroundArray[n][k][j] & 0x18) == 0x0) {
                    this.worldController.method309(myPixels, n2, n, k, j);
                }
                if (n < 3 && (this.byteGroundArray[n + 1][k][j] & 0x8) != 0x0) {
                    this.worldController.method309(myPixels, n2, n + 1, k, j);
                }
                n2 += 4;
            }
        }
        final int n3 = (238 + (int)(Math.random() * 20.0) - 10 << 16) + (238 + (int)(Math.random() * 20.0) - 10 << 8) + (238 + (int)(Math.random() * 20.0) - 10);
        final int n4 = 238 + (int)(Math.random() * 20.0) - 10 << 16;
        this.aClass30_Sub2_Sub1_Sub1_1263.method343();
        for (int l = 1; l < 103; ++l) {
            for (int n5 = 1; n5 < 103; ++n5) {
                if ((this.byteGroundArray[n][n5][l] & 0x18) == 0x0) {
                    this.method50(l, n3, n5, n4, n);
                }
                if (n < 3 && (this.byteGroundArray[n + 1][n5][l] & 0x8) != 0x0) {
                    this.method50(l, n3, n5, n4, n + 1);
                }
            }
        }
        this.gameScreenIP.initDrawingArea();
        this.anInt1071 = 0;
        for (int n6 = 0; n6 < 104; ++n6) {
            for (int n7 = 0; n7 < 104; ++n7) {
                final int method303 = this.worldController.method303(this.plane, n6, n7);
                if (method303 != 0) {
                    final int anInt746 = ObjectDef.forID(method303 >> 14 & 0x7FFF).anInt746;
                    if (anInt746 >= 0) {
                        int n8 = n6;
                        int n9 = n7;
                        if (anInt746 != 22 && anInt746 != 29 && anInt746 != 34 && anInt746 != 36 && anInt746 != 46 && anInt746 != 47 && anInt746 != 48) {
                            final int n10 = 104;
                            final int n11 = 104;
                            final int[][] anIntArrayArray294 = this.aClass11Array1230[this.plane].anIntArrayArray294;
                            for (int n12 = 0; n12 < 10; ++n12) {
                                final int n13 = (int)(Math.random() * 4.0);
                                if (n13 == 0 && n8 > 0 && n8 > n6 - 3 && (anIntArrayArray294[n8 - 1][n9] & 0x1280108) == 0x0) {
                                    --n8;
                                }
                                if (n13 == 1 && n8 < n10 - 1 && n8 < n6 + 3 && (anIntArrayArray294[n8 + 1][n9] & 0x1280180) == 0x0) {
                                    ++n8;
                                }
                                if (n13 == 2 && n9 > 0 && n9 > n7 - 3 && (anIntArrayArray294[n8][n9 - 1] & 0x1280102) == 0x0) {
                                    --n9;
                                }
                                if (n13 == 3 && n9 < n11 - 1 && n9 < n7 + 3 && (anIntArrayArray294[n8][n9 + 1] & 0x1280120) == 0x0) {
                                    ++n9;
                                }
                            }
                        }
                        this.aClass30_Sub2_Sub1_Sub1Array1140[this.anInt1071] = this.mapFunctions[anInt746];
                        this.anIntArray1072[this.anInt1071] = n8;
                        this.anIntArray1073[this.anInt1071] = n9;
                        ++this.anInt1071;
                    }
                }
            }
        }
        if (!this.saveMaps) {
            return;
        }
        final File file = new File("./MapImageDumps/");
        if (!file.exists()) {
            file.mkdir();
        }
        final BufferedImage bufferedImage = new BufferedImage(this.aClass30_Sub2_Sub1_Sub1_1263.myWidth, this.aClass30_Sub2_Sub1_Sub1_1263.myHeight, 1);
        bufferedImage.setRGB(0, 0, this.aClass30_Sub2_Sub1_Sub1_1263.myWidth, this.aClass30_Sub2_Sub1_Sub1_1263.myHeight, this.aClass30_Sub2_Sub1_Sub1_1263.myPixels, 0, this.aClass30_Sub2_Sub1_Sub1_1263.myWidth);
        bufferedImage.createGraphics().dispose();
        try {
            ImageIO.write(bufferedImage, "png", new File("MapImageDumps/" + (file.listFiles().length + 1) + ".png"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void spawnGroundItem(final int n, final int n2) {
        final NodeList list = this.groundArray[this.plane][n][n2];
        if (list == null) {
            this.worldController.method295(this.plane, n, n2);
            return;
        }
        int n3 = -99999999;
        Item item = null;
        for (Item item2 = (Item)list.reverseGetFirst(); item2 != null; item2 = (Item)list.reverseGetNext()) {
            final ItemDef forID = ItemDef.forID(item2.ID);
            int value = forID.value;
            if (forID.stackable) {
                value *= item2.anInt1559 + 1;
            }
            if (value > n3) {
                n3 = value;
                item = item2;
            }
        }
        list.insertTail(item);
        Item item3 = null;
        Item item4 = null;
        for (Item item5 = (Item)list.reverseGetFirst(); item5 != null; item5 = (Item)list.reverseGetNext()) {
            if (item5.ID != item.ID && item3 == null) {
                item3 = item5;
            }
            if (item5.ID != item.ID && item5.ID != item3.ID && item4 == null) {
                item4 = item5;
            }
        }
        this.worldController.method281(n, n + (n2 << 7) + 1610612736, item3, this.method42(this.plane, n2 * 128 + 64, n * 128 + 64), item4, item, this.plane, n2);
    }
    
    private void method26(final boolean b) {
        for (int i = 0; i < this.npcCount; ++i) {
            final NPC npc = this.npcArray[this.npcIndices[i]];
            int n = 536870912 + (this.npcIndices[i] << 14);
            if (npc != null && npc.isVisible()) {
                if (npc.desc.aBoolean93 == b) {
                    final int n2 = npc.x >> 7;
                    final int n3 = npc.y >> 7;
                    if (n2 >= 0 && n2 < 104 && n3 >= 0) {
                        if (n3 < 104) {
                            if (npc.anInt1540 == 1 && (npc.x & 0x7F) == 0x40 && (npc.y & 0x7F) == 0x40) {
                                if (this.anIntArrayArray929[n2][n3] == this.anInt1265) {
                                    continue;
                                }
                                this.anIntArrayArray929[n2][n3] = this.anInt1265;
                            }
                            if (!npc.desc.aBoolean84) {
                                n -= Integer.MIN_VALUE;
                            }
                            this.worldController.method285(this.plane, npc.anInt1552, this.method42(this.plane, npc.y, npc.x), n, npc.y, (npc.anInt1540 - 1) * 64 + 60, npc.x, npc, npc.aBoolean1541);
                        }
                    }
                }
            }
        }
    }
    
    private void loadError() {
        final String s = "ondemand";
    Label_0058:
        while (true) {
            try {
                this.getAppletContext().showDocument(new URL(this.getCodeBase(), "loaderror_" + s + ".html"));
                break Label_0058;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            while (true) {
                try {
                    while (true) {
                        Thread.sleep(1000L);
                    }
                }
                catch (Exception ex2) {
                    continue;
                }
                continue Label_0058;
            }
            break;
        }
    }
    
    public void drawHoverBox(final int n, int n2, final String s) {
        final String[] split = s.split("\n");
        final int n3 = split.length * 16 + 6;
        int n4 = this.chatTextDrawingArea.getTextWidth(split[0]) + 6;
        for (int i = 1; i < split.length; ++i) {
            if (n4 <= this.chatTextDrawingArea.getTextWidth(split[i]) + 6) {
                n4 = this.chatTextDrawingArea.getTextWidth(split[i]) + 6;
            }
        }
        DrawingArea.drawPixels(n3, n2, n, 16777120, n4);
        DrawingArea.fillPixels(n, n4, n3, 0, n2);
        n2 += 14;
        for (int j = 0; j < split.length; ++j) {
            this.chatTextDrawingArea.method389(false, n + 3, 0, split[j], n2);
            n2 += 16;
        }
    }
    
    private void buildInterfaceMenu(final int n, final RSInterface rsInterface, final int n2, final int n3, final int n4, final int n5) {
        try {
            if (rsInterface.type != 0 || rsInterface.children == null || rsInterface.isMouseoverTriggered) {
                return;
            }
            if (n2 < n || n4 < n3 || n2 > n + rsInterface.width || n4 > n3 + rsInterface.height) {
                return;
            }
            for (int length = rsInterface.children.length, i = 0; i < length; ++i) {
                final int n6 = rsInterface.childX[i] + n;
                final int n7 = rsInterface.childY[i] + n3 - n5;
                final RSInterface rsInterface2 = RSInterface.interfaceCache[rsInterface.children[i]];
                final int n8 = n6 + rsInterface2.anInt263;
                final int n9 = n7 + rsInterface2.anInt265;
                if ((rsInterface2.mOverInterToTrigger >= 0 || rsInterface2.anInt216 != 0) && n2 >= n8 && n4 >= n9 && n2 < n8 + rsInterface2.width && n4 < n9 + rsInterface2.height) {
                    if (rsInterface2.mOverInterToTrigger >= 0) {
                        this.anInt886 = rsInterface2.mOverInterToTrigger;
                    }
                    else {
                        this.anInt886 = rsInterface2.id;
                    }
                }
                if (rsInterface2.type == 8 && n2 >= n8 && n4 >= n9 && n2 < n8 + rsInterface2.width && n4 < n9 + rsInterface2.height) {
                    this.anInt1315 = rsInterface2.id;
                }
                if (rsInterface2.type == 10 && n2 >= n8 && n4 >= n9 && n2 < n8 + rsInterface2.width && n4 < n9 + rsInterface2.height) {
                    this.anInt1315 = rsInterface2.id;
                }
                if (rsInterface2.type == 0) {
                    this.buildInterfaceMenu(n8, rsInterface2, n2, n9, n4, rsInterface2.scrollPosition);
                    if (rsInterface2.scrollMax > rsInterface2.height) {
                        this.method65(n8 + rsInterface2.width, rsInterface2.height, n2, n4, rsInterface2, n9, true, rsInterface2.scrollMax);
                    }
                }
                else {
                    if (rsInterface2.atActionType == 1 && n2 >= n8 && n4 >= n9 && n2 < n8 + rsInterface2.width && n4 < n9 + rsInterface2.height) {
                        boolean buildFriendsListMenu = false;
                        if (rsInterface2.contentType != 0) {
                            buildFriendsListMenu = this.buildFriendsListMenu(rsInterface2);
                        }
                        if (!buildFriendsListMenu) {
                            this.menuActionName[this.menuActionRow] = rsInterface2.tooltip;
                            this.menuActionID[this.menuActionRow] = 315;
                            this.menuActionCmd3[this.menuActionRow] = rsInterface2.id;
                            ++this.menuActionRow;
                        }
                    }
                    if (rsInterface2.atActionType == 2 && this.spellSelected == 0 && n2 >= n8 && n4 >= n9 && n2 < n8 + rsInterface2.width && n4 < n9 + rsInterface2.height) {
                        String s = rsInterface2.selectedActionName;
                        if (s.indexOf(" ") != -1) {
                            s = s.substring(0, s.indexOf(" "));
                        }
                        if (rsInterface2.spellName.endsWith("Rush") || rsInterface2.spellName.endsWith("Burst") || rsInterface2.spellName.endsWith("Blitz") || rsInterface2.spellName.endsWith("Barrage") || rsInterface2.spellName.endsWith("strike") || rsInterface2.spellName.endsWith("bolt") || rsInterface2.spellName.equals("Crumble undead") || rsInterface2.spellName.endsWith("blast") || rsInterface2.spellName.endsWith("wave") || rsInterface2.spellName.equals("Claws of Guthix") || rsInterface2.spellName.equals("Flames of Zamorak") || rsInterface2.spellName.equals("Magic Dart")) {
                            this.menuActionName[this.menuActionRow] = "Autocast @gre@" + rsInterface2.spellName;
                            this.menuActionID[this.menuActionRow] = 104;
                            this.menuActionCmd3[this.menuActionRow] = rsInterface2.id;
                            ++this.menuActionRow;
                        }
                        this.menuActionName[this.menuActionRow] = s + " @gre@" + rsInterface2.spellName;
                        this.menuActionID[this.menuActionRow] = 626;
                        this.menuActionCmd3[this.menuActionRow] = rsInterface2.id;
                        ++this.menuActionRow;
                    }
                    if (rsInterface2.atActionType == 3 && n2 >= n8 && n4 >= n9 && n2 < n8 + rsInterface2.width && n4 < n9 + rsInterface2.height) {
                        this.menuActionName[this.menuActionRow] = "Close";
                        this.menuActionID[this.menuActionRow] = 200;
                        this.menuActionCmd3[this.menuActionRow] = rsInterface2.id;
                        ++this.menuActionRow;
                    }
                    if (rsInterface2.atActionType == 4 && n2 >= n8 && n4 >= n9 && n2 < n8 + rsInterface2.width && n4 < n9 + rsInterface2.height) {
                        this.menuActionName[this.menuActionRow] = rsInterface2.tooltip;
                        this.menuActionID[this.menuActionRow] = 169;
                        this.menuActionCmd3[this.menuActionRow] = rsInterface2.id;
                        ++this.menuActionRow;
                        if (rsInterface2.hoverText != null) {}
                    }
                    if (rsInterface2.atActionType == 5 && n2 >= n8 && n4 >= n9 && n2 < n8 + rsInterface2.width && n4 < n9 + rsInterface2.height) {
                        this.menuActionName[this.menuActionRow] = rsInterface2.tooltip;
                        this.menuActionID[this.menuActionRow] = 646;
                        this.menuActionCmd3[this.menuActionRow] = rsInterface2.id;
                        ++this.menuActionRow;
                    }
                    if (rsInterface2.atActionType == 6 && !this.aBoolean1149 && n2 >= n8 && n4 >= n9 && n2 < n8 + rsInterface2.width && n4 < n9 + rsInterface2.height) {
                        this.menuActionName[this.menuActionRow] = rsInterface2.tooltip;
                        this.menuActionID[this.menuActionRow] = 679;
                        this.menuActionCmd3[this.menuActionRow] = rsInterface2.id;
                        ++this.menuActionRow;
                    }
                    if (rsInterface2.type == 2) {
                        int mouseInvInterfaceIndex = 0;
                        for (int j = 0; j < rsInterface2.height; ++j) {
                            for (int k = 0; k < rsInterface2.width; ++k) {
                                int n10 = n8 + k * (32 + rsInterface2.invSpritePadX);
                                int n11 = n9 + j * (32 + rsInterface2.invSpritePadY);
                                if (mouseInvInterfaceIndex < 20) {
                                    n10 += rsInterface2.spritesX[mouseInvInterfaceIndex];
                                    n11 += rsInterface2.spritesY[mouseInvInterfaceIndex];
                                }
                                if (n2 >= n10 && n4 >= n11 && n2 < n10 + 32 && n4 < n11 + 32) {
                                    this.mouseInvInterfaceIndex = mouseInvInterfaceIndex;
                                    this.lastActiveInvInterface = rsInterface2.id;
                                    if (rsInterface2.inv[mouseInvInterfaceIndex] > 0) {
                                        final ItemDef forID = ItemDef.forID(rsInterface2.inv[mouseInvInterfaceIndex] - 1);
                                        if (this.itemSelected == 1 && rsInterface2.isInventoryInterface) {
                                            if (rsInterface2.id != this.anInt1284 || mouseInvInterfaceIndex != this.anInt1283) {
                                                this.menuActionName[this.menuActionRow] = "Use " + this.selectedItemName + " with @lre@" + forID.name;
                                                this.menuActionID[this.menuActionRow] = 870;
                                                this.menuActionCmd1[this.menuActionRow] = forID.ID;
                                                this.menuActionCmd2[this.menuActionRow] = mouseInvInterfaceIndex;
                                                this.menuActionCmd3[this.menuActionRow] = rsInterface2.id;
                                                ++this.menuActionRow;
                                            }
                                        }
                                        else if (this.spellSelected == 1 && rsInterface2.isInventoryInterface) {
                                            if ((this.spellUsableOn & 0x10) == 0x10) {
                                                this.menuActionName[this.menuActionRow] = this.spellTooltip + " @lre@" + forID.name;
                                                this.menuActionID[this.menuActionRow] = 543;
                                                this.menuActionCmd1[this.menuActionRow] = forID.ID;
                                                this.menuActionCmd2[this.menuActionRow] = mouseInvInterfaceIndex;
                                                this.menuActionCmd3[this.menuActionRow] = rsInterface2.id;
                                                ++this.menuActionRow;
                                            }
                                        }
                                        else {
                                            if (rsInterface2.isInventoryInterface) {
                                                for (int l = 4; l >= 3; --l) {
                                                    if (forID.actions != null && forID.actions[l] != null) {
                                                        this.menuActionName[this.menuActionRow] = forID.actions[l] + " @lre@" + forID.name;
                                                        if (l == 3) {
                                                            this.menuActionID[this.menuActionRow] = 493;
                                                        }
                                                        if (l == 4) {
                                                            this.menuActionID[this.menuActionRow] = 847;
                                                        }
                                                        this.menuActionCmd1[this.menuActionRow] = forID.ID;
                                                        this.menuActionCmd2[this.menuActionRow] = mouseInvInterfaceIndex;
                                                        this.menuActionCmd3[this.menuActionRow] = rsInterface2.id;
                                                        ++this.menuActionRow;
                                                    }
                                                    else if (l == 4) {
                                                        this.menuActionName[this.menuActionRow] = "Drop @lre@" + forID.name;
                                                        this.menuActionID[this.menuActionRow] = 847;
                                                        this.menuActionCmd1[this.menuActionRow] = forID.ID;
                                                        this.menuActionCmd2[this.menuActionRow] = mouseInvInterfaceIndex;
                                                        this.menuActionCmd3[this.menuActionRow] = rsInterface2.id;
                                                        ++this.menuActionRow;
                                                    }
                                                }
                                            }
                                            if (rsInterface2.usableItemInterface) {
                                                this.menuActionName[this.menuActionRow] = "Use @lre@" + forID.name;
                                                this.menuActionID[this.menuActionRow] = 447;
                                                this.menuActionCmd1[this.menuActionRow] = forID.ID;
                                                this.menuActionCmd2[this.menuActionRow] = mouseInvInterfaceIndex;
                                                this.menuActionCmd3[this.menuActionRow] = rsInterface2.id;
                                                ++this.menuActionRow;
                                            }
                                            if (rsInterface2.isInventoryInterface && forID.actions != null) {
                                                for (int n12 = 2; n12 >= 0; --n12) {
                                                    if (forID.actions[n12] != null) {
                                                        this.menuActionName[this.menuActionRow] = forID.actions[n12] + " @lre@" + forID.name;
                                                        if (n12 == 0) {
                                                            this.menuActionID[this.menuActionRow] = 74;
                                                        }
                                                        if (n12 == 1) {
                                                            this.menuActionID[this.menuActionRow] = 454;
                                                        }
                                                        if (n12 == 2) {
                                                            this.menuActionID[this.menuActionRow] = 539;
                                                        }
                                                        this.menuActionCmd1[this.menuActionRow] = forID.ID;
                                                        this.menuActionCmd2[this.menuActionRow] = mouseInvInterfaceIndex;
                                                        this.menuActionCmd3[this.menuActionRow] = rsInterface2.id;
                                                        ++this.menuActionRow;
                                                    }
                                                }
                                            }
                                            if (rsInterface2.actions != null) {
                                                for (int n13 = 4; n13 >= 0; --n13) {
                                                    if (rsInterface2.actions[n13] != null) {
                                                        this.menuActionName[this.menuActionRow] = rsInterface2.actions[n13] + " @lre@" + forID.name;
                                                        if (n13 == 0) {
                                                            this.menuActionID[this.menuActionRow] = 632;
                                                        }
                                                        if (n13 == 1) {
                                                            this.menuActionID[this.menuActionRow] = 78;
                                                        }
                                                        if (n13 == 2) {
                                                            this.menuActionID[this.menuActionRow] = 867;
                                                        }
                                                        if (n13 == 3) {
                                                            this.menuActionID[this.menuActionRow] = 431;
                                                        }
                                                        if (n13 == 4) {
                                                            this.menuActionID[this.menuActionRow] = 53;
                                                        }
                                                        this.menuActionCmd1[this.menuActionRow] = forID.ID;
                                                        this.menuActionCmd2[this.menuActionRow] = mouseInvInterfaceIndex;
                                                        this.menuActionCmd3[this.menuActionRow] = rsInterface2.id;
                                                        ++this.menuActionRow;
                                                    }
                                                }
                                            }
                                            this.menuActionName[this.menuActionRow] = "Examine @lre@" + forID.name;
                                            this.menuActionID[this.menuActionRow] = 1125;
                                            this.menuActionCmd1[this.menuActionRow] = forID.ID;
                                            this.menuActionCmd2[this.menuActionRow] = mouseInvInterfaceIndex;
                                            this.menuActionCmd3[this.menuActionRow] = rsInterface2.id;
                                            ++this.menuActionRow;
                                        }
                                    }
                                }
                                ++mouseInvInterfaceIndex;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
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
    
    public void drawScrollbar(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.scrollBar1.drawSprite(n4, n3);
        this.scrollBar2.drawSprite(n4, n3 + n - 16);
        DrawingArea.drawPixels(n - 32, n3 + 16, n4, 1, 16);
        DrawingArea.drawPixels(n - 32, n3 + 16, n4, 4011046, 15);
        DrawingArea.drawPixels(n - 32, n3 + 16, n4, 3419425, 13);
        DrawingArea.drawPixels(n - 32, n3 + 16, n4, 3024925, 11);
        DrawingArea.drawPixels(n - 32, n3 + 16, n4, 2696219, 10);
        DrawingArea.drawPixels(n - 32, n3 + 16, n4, 2433049, 9);
        DrawingArea.drawPixels(n - 32, n3 + 16, n4, 1, 1);
        int n6 = (n - 32) * n / n5;
        if (n6 < 8) {
            n6 = 8;
        }
        final int n7 = (n - 32 - n6) * n2 / (n5 - n);
        DrawingArea.drawPixels(n6, n3 + 16 + n7, n4, this.barFillColor, 16);
        DrawingArea.method341(n3 + 16 + n7, 1, n6, n4);
        DrawingArea.method341(n3 + 16 + n7, 8482897, n6, n4 + 1);
        DrawingArea.method341(n3 + 16 + n7, 7562570, n6, n4 + 2);
        DrawingArea.method341(n3 + 16 + n7, 6970435, n6, n4 + 3);
        DrawingArea.method341(n3 + 16 + n7, 6970435, n6, n4 + 4);
        DrawingArea.method341(n3 + 16 + n7, 6641729, n6, n4 + 5);
        DrawingArea.method341(n3 + 16 + n7, 6641729, n6, n4 + 6);
        DrawingArea.method341(n3 + 16 + n7, 6378814, n6, n4 + 7);
        DrawingArea.method341(n3 + 16 + n7, 6378814, n6, n4 + 8);
        DrawingArea.method341(n3 + 16 + n7, 6115644, n6, n4 + 9);
        DrawingArea.method341(n3 + 16 + n7, 6115644, n6, n4 + 10);
        DrawingArea.method341(n3 + 16 + n7, 5852730, n6, n4 + 11);
        DrawingArea.method341(n3 + 16 + n7, 5852730, n6, n4 + 12);
        DrawingArea.method341(n3 + 16 + n7, 5326389, n6, n4 + 13);
        DrawingArea.method341(n3 + 16 + n7, 4931889, n6, n4 + 14);
        DrawingArea.method339(n3 + 16 + n7, 1, 15, n4);
        DrawingArea.method339(n3 + 17 + n7, 1, 15, n4);
        DrawingArea.method339(n3 + 17 + n7, 6641729, 14, n4);
        DrawingArea.method339(n3 + 17 + n7, 6970435, 13, n4);
        DrawingArea.method339(n3 + 17 + n7, 7167816, 11, n4);
        DrawingArea.method339(n3 + 17 + n7, 7562570, 10, n4);
        DrawingArea.method339(n3 + 17 + n7, 7759947, 7, n4);
        DrawingArea.method339(n3 + 17 + n7, 8088141, 5, n4);
        DrawingArea.method339(n3 + 17 + n7, 8285776, 4, n4);
        DrawingArea.method339(n3 + 17 + n7, 8482897, 3, n4);
        DrawingArea.method339(n3 + 17 + n7, 1, 2, n4);
        DrawingArea.method339(n3 + 18 + n7, 1, 16, n4);
        DrawingArea.method339(n3 + 18 + n7, 5655352, 15, n4);
        DrawingArea.method339(n3 + 18 + n7, 6115644, 14, n4);
        DrawingArea.method339(n3 + 18 + n7, 6444608, 11, n4);
        DrawingArea.method339(n3 + 18 + n7, 6641729, 10, n4);
        DrawingArea.method339(n3 + 18 + n7, 6970435, 7, n4);
        DrawingArea.method339(n3 + 18 + n7, 7233606, 5, n4);
        DrawingArea.method339(n3 + 18 + n7, 7430727, 4, n4);
        DrawingArea.method339(n3 + 18 + n7, 8088141, 3, n4);
        DrawingArea.method339(n3 + 18 + n7, 8482897, 2, n4);
        DrawingArea.method339(n3 + 18 + n7, 1, 1, n4);
        DrawingArea.method339(n3 + 19 + n7, 1, 16, n4);
        DrawingArea.method339(n3 + 19 + n7, 5326389, 15, n4);
        DrawingArea.method339(n3 + 19 + n7, 5655352, 14, n4);
        DrawingArea.method339(n3 + 19 + n7, 6115644, 11, n4);
        DrawingArea.method339(n3 + 19 + n7, 6378814, 9, n4);
        DrawingArea.method339(n3 + 19 + n7, 6641729, 7, n4);
        DrawingArea.method339(n3 + 19 + n7, 6970435, 5, n4);
        DrawingArea.method339(n3 + 19 + n7, 7233606, 4, n4);
        DrawingArea.method339(n3 + 19 + n7, 7562570, 3, n4);
        DrawingArea.method339(n3 + 19 + n7, 8482897, 2, n4);
        DrawingArea.method339(n3 + 19 + n7, 1, 1, n4);
        DrawingArea.method339(n3 + 20 + n7, 1, 16, n4);
        DrawingArea.method339(n3 + 20 + n7, 4931889, 15, n4);
        DrawingArea.method339(n3 + 20 + n7, 5523766, 14, n4);
        DrawingArea.method339(n3 + 20 + n7, 5852730, 13, n4);
        DrawingArea.method339(n3 + 20 + n7, 6115644, 10, n4);
        DrawingArea.method339(n3 + 20 + n7, 6378814, 8, n4);
        DrawingArea.method339(n3 + 20 + n7, 6641729, 6, n4);
        DrawingArea.method339(n3 + 20 + n7, 6970435, 4, n4);
        DrawingArea.method339(n3 + 20 + n7, 7562570, 3, n4);
        DrawingArea.method339(n3 + 20 + n7, 8482897, 2, n4);
        DrawingArea.method339(n3 + 20 + n7, 1, 1, n4);
        DrawingArea.method341(n3 + 16 + n7, 1, n6, n4 + 15);
        DrawingArea.method339(n3 + 15 + n7 + n6, 1, 16, n4);
        DrawingArea.method339(n3 + 14 + n7 + n6, 1, 15, n4);
        DrawingArea.method339(n3 + 14 + n7 + n6, 4142890, 14, n4);
        DrawingArea.method339(n3 + 14 + n7 + n6, 4471853, 10, n4);
        DrawingArea.method339(n3 + 14 + n7 + n6, 4734511, 9, n4);
        DrawingArea.method339(n3 + 14 + n7 + n6, 4866095, 7, n4);
        DrawingArea.method339(n3 + 14 + n7 + n6, 4931889, 4, n4);
        DrawingArea.method339(n3 + 14 + n7 + n6, 5655352, 3, n4);
        DrawingArea.method339(n3 + 14 + n7 + n6, 1, 2, n4);
        DrawingArea.method339(n3 + 13 + n7 + n6, 1, 16, n4);
        DrawingArea.method339(n3 + 13 + n7 + n6, 4471853, 15, n4);
        DrawingArea.method339(n3 + 13 + n7 + n6, 4931889, 11, n4);
        DrawingArea.method339(n3 + 13 + n7 + n6, 5326389, 9, n4);
        DrawingArea.method339(n3 + 13 + n7 + n6, 5523766, 7, n4);
        DrawingArea.method339(n3 + 13 + n7 + n6, 5655352, 6, n4);
        DrawingArea.method339(n3 + 13 + n7 + n6, 5852730, 4, n4);
        DrawingArea.method339(n3 + 13 + n7 + n6, 6444608, 3, n4);
        DrawingArea.method339(n3 + 13 + n7 + n6, 6970435, 2, n4);
        DrawingArea.method339(n3 + 13 + n7 + n6, 1, 1, n4);
        DrawingArea.method339(n3 + 12 + n7 + n6, 1, 16, n4);
        DrawingArea.method339(n3 + 12 + n7 + n6, 4471853, 15, n4);
        DrawingArea.method339(n3 + 12 + n7 + n6, 4931889, 14, n4);
        DrawingArea.method339(n3 + 12 + n7 + n6, 5523766, 12, n4);
        DrawingArea.method339(n3 + 12 + n7 + n6, 5655352, 11, n4);
        DrawingArea.method339(n3 + 12 + n7 + n6, 5852730, 10, n4);
        DrawingArea.method339(n3 + 12 + n7 + n6, 6115644, 7, n4);
        DrawingArea.method339(n3 + 12 + n7 + n6, 6378814, 4, n4);
        DrawingArea.method339(n3 + 12 + n7 + n6, 7233606, 3, n4);
        DrawingArea.method339(n3 + 12 + n7 + n6, 8088141, 2, n4);
        DrawingArea.method339(n3 + 12 + n7 + n6, 1, 1, n4);
        DrawingArea.method339(n3 + 11 + n7 + n6, 1, 16, n4);
        DrawingArea.method339(n3 + 11 + n7 + n6, 4931889, 15, n4);
        DrawingArea.method339(n3 + 11 + n7 + n6, 5326389, 14, n4);
        DrawingArea.method339(n3 + 11 + n7 + n6, 5655352, 13, n4);
        DrawingArea.method339(n3 + 11 + n7 + n6, 5852730, 11, n4);
        DrawingArea.method339(n3 + 11 + n7 + n6, 6115644, 9, n4);
        DrawingArea.method339(n3 + 11 + n7 + n6, 6378814, 7, n4);
        DrawingArea.method339(n3 + 11 + n7 + n6, 6641729, 5, n4);
        DrawingArea.method339(n3 + 11 + n7 + n6, 6970435, 4, n4);
        DrawingArea.method339(n3 + 11 + n7 + n6, 7562570, 3, n4);
        DrawingArea.method339(n3 + 11 + n7 + n6, 8088141, 2, n4);
        DrawingArea.method339(n3 + 11 + n7 + n6, 1, 1, n4);
    }
    
    private void updateNPCs(final Stream stream, final int n) {
        try {
            this.anInt839 = 0;
            this.anInt893 = 0;
            this.method139(stream);
            this.method46(n, stream);
            this.method86(stream);
            for (int i = 0; i < this.anInt839; ++i) {
                final int n2 = this.anIntArray840[i];
                if (this.npcArray[n2].anInt1537 != client.loopCycle) {
                    this.npcArray[n2].desc = null;
                    this.npcArray[n2] = null;
                }
            }
            if (stream.currentOffset != n) {}
            for (int j = 0; j < this.npcCount; ++j) {
                if (this.npcArray[this.npcIndices[j]] == null) {
                    SignLink.reporterror(this.myUsername + " null entry in npc list - pos:" + j + " size:" + this.npcCount);
                    throw new RuntimeException("eek");
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void processChatModeClick() {
        if (super.mouseX >= 5 && super.mouseX <= 61 && super.mouseY >= client.clientHeight - 21 && super.mouseY <= client.clientHeight) {
            this.cButtonHPos = 0;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        else if (super.mouseX >= 71 && super.mouseX <= 127 && super.mouseY >= client.clientHeight - 21 && super.mouseY <= client.clientHeight) {
            this.cButtonHPos = 1;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        else if (super.mouseX >= 137 && super.mouseX <= 193 && super.mouseY >= client.clientHeight - 21 && super.mouseY <= client.clientHeight) {
            this.cButtonHPos = 2;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        else if (super.mouseX >= 203 && super.mouseX <= 259 && super.mouseY >= client.clientHeight - 21 && super.mouseY <= client.clientHeight) {
            this.cButtonHPos = 3;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        else if (super.mouseX >= 269 && super.mouseX <= 325 && super.mouseY >= client.clientHeight - 21 && super.mouseY <= client.clientHeight) {
            this.cButtonHPos = 4;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        else if (super.mouseX >= 335 && super.mouseX <= 391 && super.mouseY >= client.clientHeight - 21 && super.mouseY <= client.clientHeight) {
            this.cButtonHPos = 5;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        else if (super.mouseX >= 404 && super.mouseX <= 515 && super.mouseY >= client.clientHeight - 21 && super.mouseY <= client.clientHeight) {
            this.cButtonHPos = 6;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        else {
            this.cButtonHPos = -1;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (super.clickMode3 == 1) {
            if (super.saveClickX >= 5 && super.saveClickX <= 61 && super.saveClickY >= client.clientHeight - 21 && super.saveClickY <= client.clientHeight) {
                this.cButtonCPos = 0;
                this.chatTypeView = 0;
                this.aBoolean1233 = true;
                client.inputTaken = true;
            }
            else if (super.saveClickX >= 71 && super.saveClickX <= 127 && super.saveClickY >= client.clientHeight - 21 && super.saveClickY <= client.clientHeight) {
                this.cButtonCPos = 1;
                this.chatTypeView = 5;
                this.aBoolean1233 = true;
                client.inputTaken = true;
            }
            else if (super.saveClickX >= 137 && super.saveClickX <= 193 && super.saveClickY >= client.clientHeight - 21 && super.saveClickY <= client.clientHeight) {
                this.cButtonCPos = 2;
                this.chatTypeView = 1;
                this.aBoolean1233 = true;
                client.inputTaken = true;
            }
            else if (super.saveClickX >= 203 && super.saveClickX <= 259 && super.saveClickY >= client.clientHeight - 21 && super.saveClickY <= client.clientHeight) {
                this.cButtonCPos = 3;
                this.chatTypeView = 2;
                this.aBoolean1233 = true;
                client.inputTaken = true;
            }
            else if (super.saveClickX >= 269 && super.saveClickX <= 325 && super.saveClickY >= client.clientHeight - 21 && super.saveClickY <= client.clientHeight) {
                this.cButtonCPos = 4;
                this.chatTypeView = 11;
                this.aBoolean1233 = true;
                client.inputTaken = true;
            }
            else if (super.saveClickX >= 335 && super.saveClickX <= 391 && super.saveClickY >= client.clientHeight - 21 && super.saveClickY <= client.clientHeight) {
                this.cButtonCPos = 5;
                this.chatTypeView = 3;
                this.aBoolean1233 = true;
                client.inputTaken = true;
            }
            else if (super.saveClickX >= 404 && super.saveClickX <= 515 && super.saveClickY >= client.clientHeight - 21 && super.saveClickY <= client.clientHeight) {
                if (client.openInterfaceID == -1) {
                    this.clearTopInterfaces();
                    this.reportAbuseInput = "";
                    this.canMute = false;
                    for (int i = 0; i < RSInterface.interfaceCache.length; ++i) {
                        if (RSInterface.interfaceCache[i] != null && RSInterface.interfaceCache[i].contentType == 600) {
                            this.reportAbuseInterfaceID = (client.openInterfaceID = RSInterface.interfaceCache[i].parentID);
                            break;
                        }
                    }
                }
                else {
                    this.pushMessage("Please close the interface you have open before using 'report abuse'", 0, "");
                }
            }
        }
    }
    
    private void method33(final int n) {
        final int anInt709 = Varp.cache[n].anInt709;
        if (anInt709 == 0) {
            return;
        }
        final int n2 = this.variousSettings[n];
        if (anInt709 == 1) {
            if (n2 == 1) {
                Texture.method372(0.9);
            }
            if (n2 == 2) {
                Texture.method372(0.8);
            }
            if (n2 == 3) {
                Texture.method372(0.7);
            }
            if (n2 == 4) {
                Texture.method372(0.6);
            }
            ItemDef.mruNodes1.unlinkAll();
            this.welcomeScreenRaised = true;
        }
        if (anInt709 == 3) {
            final boolean musicEnabled = this.musicEnabled;
            if (n2 == 0) {
                this.musicEnabled = true;
            }
            if (n2 == 1) {
                this.musicEnabled = true;
            }
            if (n2 == 2) {
                this.musicEnabled = true;
            }
            if (n2 == 3) {
                this.musicEnabled = true;
            }
            if (n2 == 4) {
                this.musicEnabled = false;
            }
            if (this.musicEnabled != musicEnabled && !client.lowMem) {
                if (this.musicEnabled) {
                    this.nextSong = this.currentSong;
                    this.songChanging = true;
                    try {
                        this.onDemandFetcher.method558(2, this.nextSong);
                    }
                    catch (NullPointerException ex) {}
                }
                else {
                    this.stopMidi();
                }
                this.prevSong = 0;
            }
        }
        if (anInt709 == 4) {
            if (n2 == 0) {
                this.aBoolean848 = true;
                this.setWaveVolume(0);
            }
            if (n2 == 1) {
                this.aBoolean848 = true;
                this.setWaveVolume(-400);
            }
            if (n2 == 2) {
                this.aBoolean848 = true;
                this.setWaveVolume(-800);
            }
            if (n2 == 3) {
                this.aBoolean848 = true;
                this.setWaveVolume(-1200);
            }
            if (n2 == 4) {
                this.aBoolean848 = false;
            }
        }
        if (anInt709 == 5) {
            this.anInt1253 = n2;
        }
        if (anInt709 == 6) {
            this.anInt1249 = n2;
        }
        if (anInt709 == 8) {
            this.splitPrivateChat = n2;
            client.inputTaken = true;
        }
        if (anInt709 == 9) {
            this.anInt913 = n2;
        }
    }
    
    public void models() {
        for (int i = 0; i < 70000; ++i) {
            final byte[] model = this.getModel(i);
            if (model != null && model.length > 0) {
                this.decompressors[1].method234(model.length, model, i);
                System.out.println("Packed " + i + "");
            }
        }
    }
    
    public byte[] getModel(final int n) {
        try {
            final File file = new File(SignLink.findcachedir() + "/Models/pack/" + n + ".dat.gz");
            final byte[] array = new byte[(int)file.length()];
            final FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(array);
            this.pushMessage("aByte = [" + array + "]!", 0, "");
            fileInputStream.close();
            return array;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static final byte[] ReadFile(final String s, final boolean b) {
        try {
            final int n = (int)new File(s).length();
            final byte[] array = new byte[n];
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(s)));
            dataInputStream.readFully(array, 0, n);
            dataInputStream.close();
            return array;
        }
        catch (Exception ex) {
            System.out.println("Read Error: " + s);
            return null;
        }
    }
    
    private void updateEntities() {
        try {
            int n = 0;
            for (int i = -1; i < this.playerCount + this.npcCount; ++i) {
                Entity myPlayer;
                if (i == -1) {
                    myPlayer = client.myPlayer;
                }
                else if (i < this.playerCount) {
                    myPlayer = this.playerArray[this.playerIndices[i]];
                }
                else {
                    myPlayer = this.npcArray[this.npcIndices[i - this.playerCount]];
                }
                if (myPlayer != null) {
                    if (((NPC)myPlayer).isVisible()) {
                        if (myPlayer instanceof NPC) {
                            EntityDef entityDef = ((NPC)myPlayer).desc;
                            if (entityDef.childrenIDs != null) {
                                entityDef = entityDef.method161();
                            }
                            if (entityDef == null) {
                                continue;
                            }
                        }
                        if (i < this.playerCount) {
                            int n2 = 30;
                            final Player player = (Player)myPlayer;
                            if (player.headIcon >= 0) {
                                this.npcScreenPos(myPlayer, ((Player)myPlayer).height + 15);
                                if (this.spriteDrawX > -1) {
                                    if (player.skullIcon < 2) {
                                        this.skullIcons[player.skullIcon].drawSprite(this.spriteDrawX - 12, this.spriteDrawY - n2);
                                        n2 += 25;
                                    }
                                    if (player.headIcon < 18) {
                                        this.headIcons[player.headIcon].drawSprite(this.spriteDrawX - 12, this.spriteDrawY - n2);
                                        n2 += 18;
                                    }
                                }
                            }
                            if (i >= 0 && this.anInt855 == 10 && this.anInt933 == this.playerIndices[i]) {
                                this.npcScreenPos(myPlayer, ((Player)myPlayer).height + 15);
                                if (this.spriteDrawX > -1) {
                                    this.headIconsHint[player.hintIcon].drawSprite(this.spriteDrawX - 12, this.spriteDrawY - n2);
                                }
                            }
                        }
                        else {
                            final EntityDef desc = ((NPC)myPlayer).desc;
                            if (desc.anInt75 >= 0 && desc.anInt75 < this.headIcons.length) {
                                this.npcScreenPos(myPlayer, ((Player)myPlayer).height + 15);
                                if (this.spriteDrawX > -1) {
                                    this.headIcons[desc.anInt75].drawSprite(this.spriteDrawX - 12, this.spriteDrawY - 30);
                                }
                            }
                            if (this.anInt855 == 1 && this.anInt1222 == this.npcIndices[i - this.playerCount] && client.loopCycle % 20 < 10) {
                                this.npcScreenPos(myPlayer, ((Player)myPlayer).height + 15);
                                if (this.spriteDrawX > -1) {
                                    this.headIconsHint[0].drawSprite(this.spriteDrawX - 12, this.spriteDrawY - 28);
                                }
                            }
                        }
                        if (((Player)myPlayer).textSpoken != null && (i >= this.playerCount || this.publicChatMode == 0 || this.publicChatMode == 3 || (this.publicChatMode == 1 && this.isFriendOrSelf(((Player)myPlayer).name)))) {
                            this.npcScreenPos(myPlayer, ((Player)myPlayer).height);
                            if (this.spriteDrawX > -1 && n < this.anInt975) {
                                this.anIntArray979[n] = this.chatTextDrawingArea.method384(((Player)myPlayer).textSpoken) / 2;
                                this.anIntArray978[n] = this.chatTextDrawingArea.anInt1497;
                                this.anIntArray976[n] = this.spriteDrawX;
                                this.anIntArray977[n] = this.spriteDrawY;
                                this.anIntArray980[n] = ((Player)myPlayer).anInt1513;
                                this.anIntArray981[n] = ((Player)myPlayer).anInt1531;
                                this.anIntArray982[n] = ((Player)myPlayer).textCycle;
                                this.aStringArray983[n++] = ((Player)myPlayer).textSpoken;
                                if (this.anInt1249 == 0 && ((Player)myPlayer).anInt1531 >= 1 && ((Player)myPlayer).anInt1531 <= 3) {
                                    final int[] anIntArray978 = this.anIntArray978;
                                    final int n3 = n;
                                    anIntArray978[n3] += 10;
                                    final int[] anIntArray979 = this.anIntArray977;
                                    final int n4 = n;
                                    anIntArray979[n4] += 5;
                                }
                                if (this.anInt1249 == 0 && ((Player)myPlayer).anInt1531 == 4) {
                                    this.anIntArray979[n] = 60;
                                }
                                if (this.anInt1249 == 0 && ((Player)myPlayer).anInt1531 == 5) {
                                    final int[] anIntArray980 = this.anIntArray978;
                                    final int n5 = n;
                                    anIntArray980[n5] += 5;
                                }
                            }
                        }
                        if (((Player)myPlayer).loopCycleStatus > client.loopCycle) {
                            try {
                                this.npcScreenPos(myPlayer, ((Player)myPlayer).height + 15);
                                if (this.spriteDrawX > -1) {
                                    if (!this.newHpBar) {
                                        int n6 = ((Player)myPlayer).currentHealth * 30 / ((Player)myPlayer).maxHealth;
                                        if (n6 > 30) {
                                            n6 = 30;
                                        }
                                        DrawingArea.drawPixels(5, this.spriteDrawY - 3, this.spriteDrawX - 15, 65280, n6);
                                        DrawingArea.drawPixels(5, this.spriteDrawY - 3, this.spriteDrawX - 15 + n6, 16711680, 30 - n6);
                                    }
                                    else {
                                        int n7 = ((Player)myPlayer).currentHealth * 56 / ((Player)myPlayer).maxHealth;
                                        if (n7 > 56) {
                                            n7 = 56;
                                        }
                                        this.HPBarEmpty.drawSprite(this.spriteDrawX - 28, this.spriteDrawY - 3);
                                        (this.HPBarFull = new Sprite("bar 0", n7, 7, true)).drawSprite(this.spriteDrawX - 28, this.spriteDrawY - 3);
                                    }
                                }
                            }
                            catch (Exception ex) {}
                        }
                        for (int j = 0; j < 4; ++j) {
                            if (((Player)myPlayer).hitsLoopCycle[j] > client.loopCycle) {
                                if (myPlayer instanceof NPC) {
                                    this.npcScreenPos(myPlayer, this.newHitMarks ? ((Player)myPlayer).height : (((Player)myPlayer).height / 2));
                                }
                                else if (myPlayer instanceof Player) {
                                    this.npcScreenPos(myPlayer, this.newHitMarks ? (((Player)myPlayer).height - 25) : (((Player)myPlayer).height / 2));
                                }
                                if (this.spriteDrawX > -1) {
                                    if (!this.newHitMarks) {
                                        if (j == 1) {
                                            this.spriteDrawY -= 20;
                                        }
                                        if (j == 2) {
                                            this.spriteDrawX -= 15;
                                            this.spriteDrawY -= 10;
                                        }
                                        if (j == 3) {
                                            this.spriteDrawX += 15;
                                            this.spriteDrawY -= 10;
                                        }
                                    }
                                    else {
                                        if (j == 1) {
                                            this.spriteDrawY += 20;
                                        }
                                        if (j == 2) {
                                            this.spriteDrawY += 40;
                                        }
                                        if (j == 3) {
                                            this.spriteDrawY += 60;
                                        }
                                    }
                                    final int n8 = ((Player)myPlayer).hitsLoopCycle[j];
                                    final int n9 = n8 - client.loopCycle;
                                    final int n10 = (int)Math.round(n9 / 4.5);
                                    final int n11 = (int)Math.round(n9 * 3.710144927536232);
                                    if (this.newHitMarks) {
                                        if (n8 > client.loopCycle) {
                                            if (((Player)myPlayer).hitMarkTypes[j] == 1) {
                                                if (((Player)myPlayer).hitArray[j] > 99 || (client.newHits && ((Player)myPlayer).hitArray[j] * 10 > 99)) {
                                                    this.hitMarks[22].drawSpriteOpacity(this.spriteDrawX - 22, this.spriteDrawY - 14 + n10, n11);
                                                }
                                                else {
                                                    this.hitMarks[20].drawSpriteOpacity(this.spriteDrawX - 11, this.spriteDrawY - 12 + n10, n11);
                                                }
                                            }
                                            else if (((Player)myPlayer).hitMarkTypes[j] == 0) {
                                                this.hitMarks[21].drawSpriteOpacity(this.spriteDrawX - 12, this.spriteDrawY - 13 + n10, n11);
                                            }
                                            else {
                                                this.hitMarks[((Player)myPlayer).hitMarkTypes[j]].drawSpriteOpacity(this.spriteDrawX - 12, this.spriteDrawY - 12 + n10, n11);
                                            }
                                            if (!this.newHitMarks) {
                                                this.newSmallFont.setTrans(0, 16777215, n11);
                                                this.newSmallFont.drawCenteredString(String.valueOf(((Player)myPlayer).hitArray[j]), this.spriteDrawX, this.spriteDrawY + 4 + n10);
                                            }
                                            else if (((Player)myPlayer).hitArray[j] > 0) {
                                                this.newSmallFont.setTrans(0, 16777215, n11);
                                                this.newSmallFont.drawCenteredString(String.valueOf(((Player)myPlayer).hitArray[j]), this.spriteDrawX, this.spriteDrawY + 3 + n10);
                                            }
                                        }
                                    }
                                    else {
                                        this.hitMarks[((Player)myPlayer).hitMarkTypes[j]].drawSprite(this.spriteDrawX - 12, this.spriteDrawY - 12);
                                        if (!this.newHitMarks) {
                                            this.smallText.drawText(0, String.valueOf(((Player)myPlayer).hitArray[j]), this.spriteDrawY + 4, this.spriteDrawX);
                                            this.smallText.drawText(16777215, String.valueOf(((Player)myPlayer).hitArray[j]), this.spriteDrawY + 3, this.spriteDrawX - 1);
                                        }
                                        else if (((Player)myPlayer).hitArray[j] > 0) {
                                            this.smallText.drawText(0, String.valueOf(((Player)myPlayer).hitArray[j]), this.spriteDrawY + 4, this.spriteDrawX);
                                            this.smallText.drawText(16777215, String.valueOf(((Player)myPlayer).hitArray[j]), this.spriteDrawY + 3, this.spriteDrawX - 1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (int k = 0; k < n; ++k) {
                final int n12 = this.anIntArray976[k];
                int n13 = this.anIntArray977[k];
                final int n14 = this.anIntArray979[k];
                final int n15 = this.anIntArray978[k];
                int l = 1;
                while (l != 0) {
                    l = 0;
                    for (int n16 = 0; n16 < k; ++n16) {
                        if (n13 + 2 > this.anIntArray977[n16] - this.anIntArray978[n16] && n13 - n15 < this.anIntArray977[n16] + 2 && n12 - n14 < this.anIntArray976[n16] + this.anIntArray979[n16] && n12 + n14 > this.anIntArray976[n16] - this.anIntArray979[n16] && this.anIntArray977[n16] - this.anIntArray978[n16] < n13) {
                            n13 = this.anIntArray977[n16] - this.anIntArray978[n16];
                            l = 1;
                        }
                    }
                }
                this.spriteDrawX = this.anIntArray976[k];
                final int[] anIntArray981 = this.anIntArray977;
                final int n17 = k;
                final int spriteDrawY = n13;
                anIntArray981[n17] = spriteDrawY;
                this.spriteDrawY = spriteDrawY;
                final String s = this.aStringArray983[k];
                if (this.anInt1249 == 0) {
                    int n18 = 16776960;
                    if (this.anIntArray980[k] < 6) {
                        n18 = this.anIntArray965[this.anIntArray980[k]];
                    }
                    if (this.anIntArray980[k] == 6) {
                        n18 = ((this.anInt1265 % 20 >= 10) ? 16776960 : 16711680);
                    }
                    if (this.anIntArray980[k] == 7) {
                        n18 = ((this.anInt1265 % 20 >= 10) ? 65535 : 255);
                    }
                    if (this.anIntArray980[k] == 8) {
                        n18 = ((this.anInt1265 % 20 >= 10) ? 8454016 : 45056);
                    }
                    if (this.anIntArray980[k] == 9) {
                        final int n19 = 150 - this.anIntArray982[k];
                        if (n19 < 50) {
                            n18 = 16711680 + 1280 * n19;
                        }
                        else if (n19 < 100) {
                            n18 = 16776960 - 327680 * (n19 - 50);
                        }
                        else if (n19 < 150) {
                            n18 = 65280 + 5 * (n19 - 100);
                        }
                    }
                    if (this.anIntArray980[k] == 10) {
                        final int n20 = 150 - this.anIntArray982[k];
                        if (n20 < 50) {
                            n18 = 16711680 + 5 * n20;
                        }
                        else if (n20 < 100) {
                            n18 = 16711935 - 327680 * (n20 - 50);
                        }
                        else if (n20 < 150) {
                            n18 = 255 + 327680 * (n20 - 100) - 5 * (n20 - 100);
                        }
                    }
                    if (this.anIntArray980[k] == 11) {
                        final int n21 = 150 - this.anIntArray982[k];
                        if (n21 < 50) {
                            n18 = 16777215 - 327685 * n21;
                        }
                        else if (n21 < 100) {
                            n18 = 65280 + 327685 * (n21 - 50);
                        }
                        else if (n21 < 150) {
                            n18 = 16777215 - 327680 * (n21 - 100);
                        }
                    }
                    if (this.anIntArray981[k] == 0) {
                        this.chatTextDrawingArea.drawText(0, s, this.spriteDrawY + 1, this.spriteDrawX);
                        this.chatTextDrawingArea.drawText(n18, s, this.spriteDrawY, this.spriteDrawX);
                    }
                    if (this.anIntArray981[k] == 1) {
                        this.chatTextDrawingArea.method386(0, s, this.spriteDrawX, this.anInt1265, this.spriteDrawY + 1);
                        this.chatTextDrawingArea.method386(n18, s, this.spriteDrawX, this.anInt1265, this.spriteDrawY);
                    }
                    if (this.anIntArray981[k] == 2) {
                        this.chatTextDrawingArea.method387(this.spriteDrawX, s, this.anInt1265, this.spriteDrawY + 1, 0);
                        this.chatTextDrawingArea.method387(this.spriteDrawX, s, this.anInt1265, this.spriteDrawY, n18);
                    }
                    if (this.anIntArray981[k] == 3) {
                        this.chatTextDrawingArea.method388(150 - this.anIntArray982[k], s, this.anInt1265, this.spriteDrawY + 1, this.spriteDrawX, 0);
                        this.chatTextDrawingArea.method388(150 - this.anIntArray982[k], s, this.anInt1265, this.spriteDrawY, this.spriteDrawX, n18);
                    }
                    if (this.anIntArray981[k] == 4) {
                        final int n22 = (150 - this.anIntArray982[k]) * (this.chatTextDrawingArea.method384(s) + 100) / 150;
                        DrawingArea.setDrawingArea(334, this.spriteDrawX - 50, this.spriteDrawX + 50, 0);
                        this.chatTextDrawingArea.method385(0, s, this.spriteDrawY + 1, this.spriteDrawX + 50 - n22);
                        this.chatTextDrawingArea.method385(n18, s, this.spriteDrawY, this.spriteDrawX + 50 - n22);
                        DrawingArea.defaultDrawingAreaSize();
                    }
                    if (this.anIntArray981[k] == 5) {
                        final int n23 = 150 - this.anIntArray982[k];
                        int n24 = 0;
                        if (n23 < 25) {
                            n24 = n23 - 25;
                        }
                        else if (n23 > 125) {
                            n24 = n23 - 125;
                        }
                        DrawingArea.setDrawingArea(this.spriteDrawY + 5, 0, 512, this.spriteDrawY - this.chatTextDrawingArea.anInt1497 - 1);
                        this.chatTextDrawingArea.drawText(0, s, this.spriteDrawY + 1 + n24, this.spriteDrawX);
                        this.chatTextDrawingArea.drawText(n18, s, this.spriteDrawY + n24, this.spriteDrawX);
                        DrawingArea.defaultDrawingAreaSize();
                    }
                }
                else {
                    this.chatTextDrawingArea.drawText(0, s, this.spriteDrawY + 1, this.spriteDrawX);
                    this.chatTextDrawingArea.drawText(16776960, s, this.spriteDrawY, this.spriteDrawX);
                }
            }
        }
        catch (Exception ex2) {}
    }
    
    private void delFriend(final long n) {
        try {
            if (n == 0L) {
                return;
            }
            for (int i = 0; i < this.friendsCount; ++i) {
                if (this.friendsListAsLongs[i] == n) {
                    --this.friendsCount;
                    client.needDrawTabArea = true;
                    for (int j = i; j < this.friendsCount; ++j) {
                        this.friendsList[j] = this.friendsList[j + 1];
                        this.friendsNodeIDs[j] = this.friendsNodeIDs[j + 1];
                        this.friendsListAsLongs[j] = this.friendsListAsLongs[j + 1];
                    }
                    this.stream.createFrame(215);
                    this.stream.writeQWord(n);
                    break;
                }
            }
        }
        catch (RuntimeException ex) {
            SignLink.reporterror("18622, false, " + n + ", " + ex.toString());
            throw new RuntimeException();
        }
    }
    
    public void draw562GameFrame(int n, int n2) {
        if (client.clientSize == 0) {
            if (client.tabInterfaceIDs[0] != -1) {
                this.newSideIcons[0].drawSprite(8, 8);
            }
            if (client.tabInterfaceIDs[1] != -1) {
                this.newSideIcons[1].drawSprite(37, 8);
            }
            if (client.tabInterfaceIDs[2] != -1) {
                this.newSideIcons[2].drawSprite(67, 8);
            }
            if (client.tabInterfaceIDs[14] != -1) {
                this.newSideIcons[3].drawSprite(97, 8);
            }
            if (client.tabInterfaceIDs[3] != -1) {
                this.newSideIcons[4].drawSprite(127, 8);
            }
            if (client.tabInterfaceIDs[4] != -1) {
                this.newSideIcons[5].drawSprite(159, 8);
            }
            if (client.tabInterfaceIDs[5] != -1) {
                this.newSideIcons[6].drawSprite(187, 8);
            }
            if (client.tabInterfaceIDs[6] != -1) {
                this.newSideIcons[7].drawSprite(217, 8);
            }
            if (client.tabInterfaceIDs[9] != -1) {
                this.newSideIcons[8].drawSprite(38, 306);
            }
            if (client.tabInterfaceIDs[8] != -1) {
                this.newSideIcons[9].drawSprite(70, 306);
            }
            if (client.tabInterfaceIDs[7] != -1) {
                this.newSideIcons[13].drawSprite(97, 306);
            }
            if (client.tabInterfaceIDs[11] != -1) {
                this.newSideIcons[10].drawSprite(127, 306);
            }
            if (client.tabInterfaceIDs[12] != -1) {
                this.newSideIcons[11].drawSprite(157, 306);
            }
            if (client.tabInterfaceIDs[13] != -1) {
                this.newSideIcons[12].drawSprite(187, 306);
            }
            if (client.tabInterfaceIDs[15] != -1) {
                this.newSideIcons[14].drawSprite(216, 307);
            }
        }
        else {
            if (client.clientSize >= 1) {
                n = client.clientWidth - 488;
                n2 = client.clientHeight - 34;
            }
            if (client.tabInterfaceIDs[0] != -1) {
                this.newSideIcons[0].drawSprite(9 + n, 5 + n2);
            }
            if (client.tabInterfaceIDs[1] != -1) {
                this.newSideIcons[1].drawSprite(38 + n, 5 + n2);
            }
            if (client.tabInterfaceIDs[2] != -1) {
                this.newSideIcons[2].drawSprite(69 + n, 5 + n2);
            }
            if (client.tabInterfaceIDs[3] != -1) {
                this.newSideIcons[3].drawSprite(99 + n, 5 + n2);
            }
            if (client.tabInterfaceIDs[4] != -1) {
                this.newSideIcons[4].drawSprite(127 + n, 5 + n2);
            }
            if (client.tabInterfaceIDs[5] != -1) {
                this.newSideIcons[5].drawSprite(161 + n, 5 + n2);
            }
            if (client.tabInterfaceIDs[6] != -1) {
                this.newSideIcons[6].drawSprite(188 + n, 5 + n2);
            }
            n = ((client.clientSize >= 1) ? (client.clientWidth - 239) : n);
            n2 = ((client.clientSize >= 1) ? (client.clientHeight - 36 - 298) : n2);
            if (client.tabInterfaceIDs[7] != -1) {
                this.newSideIcons[7].drawSprite(-30 + n, 306 + n2);
            }
            if (client.tabInterfaceIDs[8] != -1) {
                this.newSideIcons[13].drawSprite(31 + n, 306 + n2);
            }
            if (client.tabInterfaceIDs[9] != -1) {
                this.newSideIcons[8].drawSprite(63 + n, 306 + n2);
            }
            if (client.tabInterfaceIDs[10] != -1) {
                this.newSideIcons[10].drawSprite(120 + n, 306 + n2);
            }
            if (client.tabInterfaceIDs[11] != -1) {
                this.newSideIcons[11].drawSprite(150 + n, 306 + n2);
            }
            if (client.tabInterfaceIDs[12] != -1) {
                this.newSideIcons[12].drawSprite(181 + n, 305 + n2);
            }
            if (client.tabInterfaceIDs[13] != -1) {
                this.newSideIcons[9].drawSprite(89 + n, 307 + n2);
            }
        }
    }
    
    public void draw525GameFrame(int n, int n2) {
        if (client.clientSize >= 1) {
            n = client.clientWidth - 470;
            n2 = client.clientHeight - 36;
        }
        if (client.tabInterfaceIDs[0] != -1) {
            if (client.clientSize == 0) {
                this.sideIcons[0].drawSprite(10, 4);
            }
            else if (client.clientSize >= 1) {
                this.sideIcons[0].drawSprite(10 + n, 4 + n2);
            }
        }
        if (client.tabInterfaceIDs[1] != -1) {
            this.sideIcons[1].drawSprite(43 + n, 4 + n2);
        }
        if (client.tabInterfaceIDs[2] != -1) {
            this.sideIcons[2].drawSprite(76 + n, 3 + n2);
        }
        if (client.tabInterfaceIDs[3] != -1) {
            this.sideIcons[3].drawSprite(111 + n, 5 + n2);
        }
        if (client.tabInterfaceIDs[4] != -1) {
            this.sideIcons[4].drawSprite(140 + n, 1 + n2);
        }
        if (client.tabInterfaceIDs[5] != -1) {
            this.sideIcons[5].drawSprite(174 + n, 1 + n2);
        }
        if (client.tabInterfaceIDs[6] != -1) {
            this.sideIcons[6].drawSprite(208 + n, 4 + n2);
        }
        n = ((client.clientSize >= 1) ? (client.clientWidth - 239) : n);
        n2 = ((client.clientSize >= 1) ? (client.clientHeight - 36 - 298) : n2);
        if (client.tabInterfaceIDs[7] != -1) {
            if (client.clientSize == 0) {
                this.sideIcons[7].drawSprite(11 + n, 303 + n2);
            }
            else if (client.clientSize >= 1) {
                this.sideIcons[7].drawSprite(14 + n, 303 + n2);
            }
        }
        if (client.tabInterfaceIDs[8] != -1) {
            this.sideIcons[8].drawSprite(46 + n, 306 + n2);
        }
        if (client.tabInterfaceIDs[9] != -1) {
            this.sideIcons[9].drawSprite(79 + n, 306 + n2);
        }
        if (client.tabInterfaceIDs[10] != -1) {
            this.sideIcons[10].drawSprite(113 + n, 302 + n2);
        }
        if (client.tabInterfaceIDs[11] != -1) {
            this.sideIcons[11].drawSprite(145 + n, 304 + n2);
        }
        if (client.tabInterfaceIDs[12] != -1) {
            this.sideIcons[12].drawSprite(181 + n, 302 + n2);
        }
        if (client.tabInterfaceIDs[13] != -1) {
            if (client.clientSize == 0) {
                this.sideIcons[13].drawSprite(213 + n, 303 + n2);
            }
            else if (client.clientSize >= 1) {
                this.sideIcons[13].drawSprite(211 + n, 303 + n2);
            }
        }
    }
    
    public void drawSideIcons(final int n, final int n2) {
        if (this.isNewGameFrame) {
            this.draw562GameFrame(n2, n2);
        }
        else {
            this.draw525GameFrame(n2, n2);
        }
    }
    
    private void handleFixed562Stones() {
        if (client.tabInterfaceIDs[client.tabID] != -1) {
            if (client.tabID == 0) {
                this.tabClicked.drawSprite(2, 0);
            }
            if (client.tabID == 1) {
                this.tabClicked.drawSprite(32, 0);
            }
            if (client.tabID == 2) {
                this.tabClicked.drawSprite(62, 0);
            }
            if (client.tabID == 14) {
                this.tabClicked.drawSprite(92, 0);
            }
            if (client.tabID == 3) {
                this.tabClicked.drawSprite(122, 0);
            }
            if (client.tabID == 4) {
                this.tabClicked.drawSprite(152, 0);
            }
            if (client.tabID == 5) {
                this.tabClicked.drawSprite(182, 0);
            }
            if (client.tabID == 6) {
                this.tabClicked.drawSprite(212, 0);
            }
            if (client.tabID == 16) {
                this.tabClicked.drawSprite(2, 298);
            }
            if (client.tabID == 8) {
                this.tabClicked.drawSprite(32, 298);
            }
            if (client.tabID == 9) {
                this.tabClicked.drawSprite(62, 298);
            }
            if (client.tabID == 7) {
                this.tabClicked.drawSprite(92, 298);
            }
            if (client.tabID == 11) {
                this.tabClicked.drawSprite(122, 298);
            }
            if (client.tabID == 12) {
                this.tabClicked.drawSprite(152, 298);
            }
            if (client.tabID == 13) {
                this.tabClicked.drawSprite(182, 298);
            }
            if (client.tabID == 15) {
                this.tabClicked.drawSprite(212, 298);
            }
        }
    }
    
    private void handleResized562Stones(int n, int n2) {
        if (client.clientSize >= 1) {
            n = client.clientWidth - 488;
            n2 = client.clientHeight - 34;
            if (client.tabID == 0) {
                this.tabClicked.drawSprite(3 + n, n2 - 1);
            }
            if (client.tabID == 1) {
                this.tabClicked.drawSprite(33 + n, n2 - 1);
            }
            if (client.tabID == 2) {
                this.tabClicked.drawSprite(62 + n, n2 - 1);
            }
            if (client.tabID == 3) {
                this.tabClicked.drawSprite(123 + n, n2 - 1);
            }
            if (client.tabID == 4) {
                this.tabClicked.drawSprite(152 + n, n2 - 1);
            }
            if (client.tabID == 5) {
                this.tabClicked.drawSprite(182 + n, n2 - 1);
            }
            if (client.tabID == 6) {
                this.tabClicked.drawSprite(212 + n, n2 - 1);
            }
            if (client.tabID == 7) {
                this.tabClicked.drawSprite(273 + n, n2 - 1);
            }
            if (client.tabID == 8) {
                this.tabClicked.drawSprite(303 + n, n2 - 1);
            }
            if (client.tabID == 9) {
                this.tabClicked.drawSprite(333 + n, n2 - 1);
            }
            if (client.tabID == 10) {
                this.tabClicked.drawSprite(363 + n, n2 - 1);
            }
            if (client.tabID == 11) {
                this.tabClicked.drawSprite(363 + n, n2 - 1);
            }
            if (client.tabID == 12) {
                this.tabClicked.drawSprite(393 + n, n2 - 1);
            }
            if (client.tabID == 13) {
                this.tabClicked.drawSprite(423 + n, n2 - 1);
            }
        }
    }
    
    public void draw562RedStones() {
        if (client.clientSize == 0) {
            this.handleFixed562Stones();
        }
        else {
            this.handleResized562Stones(0, 0);
        }
    }
    
    public void draw525RedStones() {
        int n = 0;
        if (client.clientSize >= 1) {
            n = client.clientHeight - 36;
        }
        if (client.tabID == -1) {
            return;
        }
        if (client.tabInterfaceIDs[client.tabID] != -1) {
            switch (client.tabID) {
                case 0: {
                    if (client.clientSize == 0) {
                        this.redStones[0].drawSprite(6, 0);
                        break;
                    }
                    if (this.resizableFixed) {
                        this.redStones[0].drawSprite(1, 1);
                        break;
                    }
                    this.redStones[4].drawSprite(RSApplet.clientWidth - 462, n);
                    break;
                }
                case 1: {
                    if (client.clientSize == 0) {
                        this.redStones[4].drawSprite(44, 0);
                        break;
                    }
                    this.redStones[4].drawSprite(RSApplet.clientWidth - 429, n);
                    break;
                }
                case 2: {
                    if (client.clientSize == 0) {
                        this.redStones[4].drawSprite(77, 0);
                        break;
                    }
                    this.redStones[4].drawSprite(RSApplet.clientWidth - 396, n);
                    break;
                }
                case 3: {
                    if (client.clientSize == 0) {
                        this.redStones[4].drawSprite(110, 0);
                        break;
                    }
                    this.redStones[4].drawSprite(RSApplet.clientWidth - 363, n);
                    break;
                }
                case 4: {
                    if (client.clientSize == 0) {
                        this.redStones[4].drawSprite(143, 0);
                        break;
                    }
                    this.redStones[4].drawSprite(client.clientWidth - 462 + 132, n);
                    break;
                }
                case 5: {
                    if (client.clientSize == 0) {
                        this.redStones[4].drawSprite(176, 0);
                        break;
                    }
                    this.redStones[4].drawSprite(client.clientWidth - 462 + 165, n);
                    break;
                }
                case 6: {
                    if (client.clientSize == 0) {
                        this.redStones[1].drawSprite(209, 0);
                        break;
                    }
                    this.redStones[4].drawSprite(client.clientWidth - 462 + 198, n);
                    break;
                }
                case 7: {
                    if (client.clientSize == 0) {
                        this.redStones[2].drawSprite(6, 298);
                        break;
                    }
                    this.redStones[4].drawSprite(client.clientWidth - 231, n);
                    break;
                }
                case 8: {
                    if (client.clientSize == 0) {
                        this.redStones[4].drawSprite(44, 298);
                        break;
                    }
                    this.redStones[4].drawSprite(client.clientWidth - 231 + 33, n);
                    break;
                }
                case 9: {
                    if (client.clientSize == 0) {
                        this.redStones[4].drawSprite(77, 298);
                        break;
                    }
                    this.redStones[4].drawSprite(client.clientWidth - 231 + 66, n);
                    break;
                }
                case 10: {
                    if (client.clientSize == 0) {
                        this.redStones[4].drawSprite(110, 298);
                        break;
                    }
                    this.redStones[4].drawSprite(client.clientWidth - 231 + 99, n);
                    break;
                }
                case 11: {
                    if (client.clientSize == 0) {
                        this.redStones[4].drawSprite(143, 298);
                        break;
                    }
                    this.redStones[4].drawSprite(client.clientWidth - 231 + 132, n);
                    break;
                }
                case 12: {
                    if (client.clientSize == 0) {
                        this.redStones[4].drawSprite(176, 298);
                        break;
                    }
                    this.redStones[4].drawSprite(client.clientWidth - 231 + 165, n);
                    break;
                }
                case 13: {
                    if (client.clientSize == 0) {
                        this.redStones[3].drawSprite(209, 298);
                        break;
                    }
                    this.redStones[4].drawSprite(client.clientWidth - 231 + 198, n);
                    break;
                }
            }
        }
    }
    
    public void drawRedStones() {
        if (this.isNewGameFrame) {
            this.draw562RedStones();
        }
        else {
            this.draw525RedStones();
        }
    }
    
    private void fixedHovering() {
        if (this.tabHPos == 0 && client.tabInterfaceIDs[0] != -1) {
            this.tabHover.drawSprite(3, 0);
        }
        else if (this.tabHPos == 1 && client.tabInterfaceIDs[1] != -1) {
            this.tabHover.drawSprite(33, 0);
        }
        else if (this.tabHPos == 2 && client.tabInterfaceIDs[2] != -1) {
            this.tabHover.drawSprite(63, 0);
        }
        else if (this.tabHPos == 3 && client.tabInterfaceIDs[14] != -1) {
            this.tabHover.drawSprite(93, 0);
        }
        else if (this.tabHPos == 4 && client.tabInterfaceIDs[3] != -1) {
            this.tabHover.drawSprite(123, 0);
        }
        else if (this.tabHPos == 5 && client.tabInterfaceIDs[4] != -1) {
            this.tabHover.drawSprite(153, 0);
        }
        else if (this.tabHPos == 6 && client.tabInterfaceIDs[5] != -1) {
            this.tabHover.drawSprite(183, 0);
        }
        else if (this.tabHPos == 7 && client.tabInterfaceIDs[6] != -1) {
            this.tabHover.drawSprite(213, 0);
        }
        else if (this.tabHPos == 15 && client.tabInterfaceIDs[16] != -1) {
            this.tabHover.drawSprite(3, 298);
        }
        else if (this.tabHPos == 8 && client.tabInterfaceIDs[9] != -1) {
            this.tabHover.drawSprite(33, 298);
        }
        else if (this.tabHPos == 9 && client.tabInterfaceIDs[8] != -1) {
            this.tabHover.drawSprite(63, 298);
        }
        else if (this.tabHPos == 10 && client.tabInterfaceIDs[7] != -1) {
            this.tabHover.drawSprite(93, 298);
        }
        else if (this.tabHPos == 11 && client.tabInterfaceIDs[11] != -1) {
            this.tabHover.drawSprite(123, 298);
        }
        else if (this.tabHPos == 12 && client.tabInterfaceIDs[12] != -1) {
            this.tabHover.drawSprite(153, 298);
        }
        else if (this.tabHPos == 13 && client.tabInterfaceIDs[13] != -1) {
            this.tabHover.drawSprite(183, 298);
        }
        else if (this.tabHPos == 14 && client.tabInterfaceIDs[15] != -1) {
            this.tabHover.drawSprite(213, 298);
        }
    }
    
    public void resizedHovering() {
        if (this.tabHPos == 0 && client.tabInterfaceIDs[0] != -1) {
            this.tabHover.drawSprite(3, 0);
        }
        else if (this.tabHPos == 1 && client.tabInterfaceIDs[1] != -1) {
            this.tabHover.drawSprite(33, 0);
        }
        else if (this.tabHPos == 2 && client.tabInterfaceIDs[2] != -1) {
            this.tabHover.drawSprite(63, 0);
        }
        else if (this.tabHPos == 3 && client.tabInterfaceIDs[14] != -1) {
            this.tabHover.drawSprite(93, 0);
        }
        else if (this.tabHPos == 4 && client.tabInterfaceIDs[3] != -1) {
            this.tabHover.drawSprite(123, 0);
        }
        else if (this.tabHPos == 5 && client.tabInterfaceIDs[4] != -1) {
            this.tabHover.drawSprite(153, 0);
        }
        else if (this.tabHPos == 6 && client.tabInterfaceIDs[5] != -1) {
            this.tabHover.drawSprite(183, 0);
        }
        else if (this.tabHPos == 7 && client.tabInterfaceIDs[6] != -1) {
            this.tabHover.drawSprite(213, 0);
        }
        else if (this.tabHPos == 15 && client.tabInterfaceIDs[16] != -1) {
            this.tabHover.drawSprite(3, 298);
        }
        else if (this.tabHPos == 8 && client.tabInterfaceIDs[9] != -1) {
            this.tabHover.drawSprite(33, 298);
        }
        else if (this.tabHPos == 9 && client.tabInterfaceIDs[8] != -1) {
            this.tabHover.drawSprite(63, 298);
        }
        else if (this.tabHPos == 10 && client.tabInterfaceIDs[7] != -1) {
            this.tabHover.drawSprite(93, 298);
        }
        else if (this.tabHPos == 11 && client.tabInterfaceIDs[11] != -1) {
            this.tabHover.drawSprite(123, 298);
        }
        else if (this.tabHPos == 12 && client.tabInterfaceIDs[12] != -1) {
            this.tabHover.drawSprite(153, 298);
        }
        else if (this.tabHPos == 13 && client.tabInterfaceIDs[13] != -1) {
            this.tabHover.drawSprite(183, 298);
        }
        else if (this.tabHPos == 14 && client.tabInterfaceIDs[15] != -1) {
            this.tabHover.drawSprite(213, 298);
        }
    }
    
    public void drawTabHover() {
        if (client.clientSize == 0) {
            this.fixedHovering();
        }
        else {
            this.resizedHovering();
        }
    }
    
    private void drawTabArea() {
        final int n = (client.clientSize >= 1) ? (client.clientWidth - 226) : 0;
        final int n2 = (client.clientSize >= 1) ? (client.clientHeight - 341) : 0;
        final int n3 = (client.clientSize >= 1) ? (client.clientWidth + 519) : 0;
        final int n4 = (client.clientSize >= 1) ? (client.clientHeight + 168) : 0;
        if (client.clientSize == 0) {
            this.tabAreaIP.initDrawingArea();
        }
        Texture.anIntArray1472 = this.anIntArray1181;
        if (client.clientSize == 0) {
            this.tabArea.drawSprite(0, 0);
            if (this.isNewGameFrame) {
                this.tabArea2.drawSprite(0, 0);
                this.drawTabHover();
            }
        }
        else if (client.clientSize >= 1 && !this.isNewGameFrame) {
            this.fsSprite[2].drawSprite(client.clientWidth - 462, client.clientHeight - 36);
            this.fsSprite[2].drawSprite(client.clientWidth - 231, client.clientHeight - 36);
        }
        else if (client.clientSize >= 1 && this.isNewGameFrame) {
            this.fsSprite[14].drawSprite(-23 + client.clientWidth - 462, client.clientHeight - 35);
        }
        if (this.invOverlayInterfaceID == -1) {
            if (this.isNewGameFrame) {
                this.drawTabHover();
            }
            this.drawRedStones();
            this.drawSideIcons(n + 3, n2);
        }
        if (client.clientSize >= 1 && client.tabID != -1) {
            this.fsSprite[3].drawSprite3(28 + n, 37 + n2);
            this.drawBorder(21 + n, 30 + n2, 204, 275);
        }
        if (client.tabID != -1) {
            if (this.invOverlayInterfaceID != -1 && (client.clientSize < 1 || client.tabID != -1)) {
                this.drawInterface(0, 28 + n, RSInterface.interfaceCache[this.invOverlayInterfaceID], 37 + n2);
            }
            else if (client.tabInterfaceIDs[client.tabID] != -1 && (client.clientSize < 1 || client.tabID != -1)) {
                this.drawInterface(0, 28 + n, RSInterface.interfaceCache[client.tabInterfaceIDs[client.tabID]], 37 + n2);
            }
        }
        if (this.menuOpen && this.menuScreenArea == 1) {
            this.drawMenu();
        }
        if (client.clientSize == 0) {
            this.tabAreaIP.drawGraphics(168, super.graphics, 516);
        }
        this.gameScreenIP.initDrawingArea();
        Texture.anIntArray1472 = this.anIntArray1182;
    }
    
    private void method37(final int n) {
        if (!client.lowMem) {
            if (Texture.anIntArray1480[17] >= n) {
                final Background background = Texture.aBackgroundArray1474s[17];
                final int n2 = background.anInt1452 * background.anInt1453 - 1;
                final int n3 = background.anInt1452 * this.anInt945 * 2;
                final byte[] aByteArray1450 = background.aByteArray1450;
                final byte[] aByteArray1451 = this.aByteArray912;
                for (int i = 0; i <= n2; ++i) {
                    aByteArray1451[i] = aByteArray1450[i - n3 & n2];
                }
                background.aByteArray1450 = aByteArray1451;
                this.aByteArray912 = aByteArray1450;
                Texture.method370(17);
                ++client.anInt854;
                if (client.anInt854 > 1235) {
                    client.anInt854 = 0;
                }
            }
            if (Texture.anIntArray1480[24] >= n) {
                final Background background2 = Texture.aBackgroundArray1474s[24];
                final int n4 = background2.anInt1452 * background2.anInt1453 - 1;
                final int n5 = background2.anInt1452 * this.anInt945 * 2;
                final byte[] aByteArray1452 = background2.aByteArray1450;
                final byte[] aByteArray1453 = this.aByteArray912;
                for (int j = 0; j <= n4; ++j) {
                    aByteArray1453[j] = aByteArray1452[j - n5 & n4];
                }
                background2.aByteArray1450 = aByteArray1453;
                this.aByteArray912 = aByteArray1452;
                Texture.method370(24);
            }
            if (Texture.anIntArray1480[34] >= n) {
                final Background background3 = Texture.aBackgroundArray1474s[34];
                final int n6 = background3.anInt1452 * background3.anInt1453 - 1;
                final int n7 = background3.anInt1452 * this.anInt945 * 2;
                final byte[] aByteArray1454 = background3.aByteArray1450;
                final byte[] aByteArray1455 = this.aByteArray912;
                for (int k = 0; k <= n6; ++k) {
                    aByteArray1455[k] = aByteArray1454[k - n7 & n6];
                }
                background3.aByteArray1450 = aByteArray1455;
                this.aByteArray912 = aByteArray1454;
                Texture.method370(34);
            }
            if (Texture.anIntArray1480[40] >= n) {
                final Background background4 = Texture.aBackgroundArray1474s[40];
                final int n8 = background4.anInt1452 * background4.anInt1453 - 1;
                final int n9 = background4.anInt1452 * this.anInt945 * 2;
                final byte[] aByteArray1456 = background4.aByteArray1450;
                final byte[] aByteArray1457 = this.aByteArray912;
                for (int l = 0; l <= n8; ++l) {
                    aByteArray1457[l] = aByteArray1456[l - n9 & n8];
                }
                background4.aByteArray1450 = aByteArray1457;
                this.aByteArray912 = aByteArray1456;
                Texture.method370(40);
            }
        }
    }
    
    private void method38() {
        for (int i = -1; i < this.playerCount; ++i) {
            int myPlayerIndex;
            if (i == -1) {
                myPlayerIndex = this.myPlayerIndex;
            }
            else {
                myPlayerIndex = this.playerIndices[i];
            }
            final Player player = this.playerArray[myPlayerIndex];
            if (player != null && player.textCycle > 0) {
                final Player player2 = player;
                --player2.textCycle;
                if (player.textCycle == 0) {
                    player.textSpoken = null;
                }
            }
        }
        for (int j = 0; j < this.npcCount; ++j) {
            final NPC npc = this.npcArray[this.npcIndices[j]];
            if (npc != null && npc.textCycle > 0) {
                final NPC npc2 = npc;
                --npc2.textCycle;
                if (npc.textCycle == 0) {
                    npc.textSpoken = null;
                }
            }
        }
    }
    
    private void calcCameraPos() {
        final int n = this.anInt1098 * 128 + 64;
        final int n2 = this.anInt1099 * 128 + 64;
        final int n3 = this.method42(this.plane, n2, n) - this.anInt1100;
        if (this.xCameraPos < n) {
            this.xCameraPos += this.anInt1101 + (n - this.xCameraPos) * this.anInt1102 / 1000;
            if (this.xCameraPos > n) {
                this.xCameraPos = n;
            }
        }
        if (this.xCameraPos > n) {
            this.xCameraPos -= this.anInt1101 + (this.xCameraPos - n) * this.anInt1102 / 1000;
            if (this.xCameraPos < n) {
                this.xCameraPos = n;
            }
        }
        if (this.zCameraPos < n3) {
            this.zCameraPos += this.anInt1101 + (n3 - this.zCameraPos) * this.anInt1102 / 1000;
            if (this.zCameraPos > n3) {
                this.zCameraPos = n3;
            }
        }
        if (this.zCameraPos > n3) {
            this.zCameraPos -= this.anInt1101 + (this.zCameraPos - n3) * this.anInt1102 / 1000;
            if (this.zCameraPos < n3) {
                this.zCameraPos = n3;
            }
        }
        if (this.yCameraPos < n2) {
            this.yCameraPos += this.anInt1101 + (n2 - this.yCameraPos) * this.anInt1102 / 1000;
            if (this.yCameraPos > n2) {
                this.yCameraPos = n2;
            }
        }
        if (this.yCameraPos > n2) {
            this.yCameraPos -= this.anInt1101 + (this.yCameraPos - n2) * this.anInt1102 / 1000;
            if (this.yCameraPos < n2) {
                this.yCameraPos = n2;
            }
        }
        final int n4 = this.anInt995 * 128 + 64;
        final int n5 = this.anInt996 * 128 + 64;
        final int n6 = this.method42(this.plane, n5, n4) - this.anInt997;
        final int n7 = n4 - this.xCameraPos;
        final int n8 = n6 - this.zCameraPos;
        final int n9 = n5 - this.yCameraPos;
        int n10 = (int)(Math.atan2(n8, (int)Math.sqrt(n7 * n7 + n9 * n9)) * 325.949) & 0x7FF;
        final int xCameraCurve = (int)(Math.atan2(n7, n9) * -325.949) & 0x7FF;
        if (n10 < 128) {
            n10 = 128;
        }
        if (n10 > 383) {
            n10 = 383;
        }
        if (this.yCameraCurve < n10) {
            this.yCameraCurve += this.anInt998 + (n10 - this.yCameraCurve) * this.anInt999 / 1000;
            if (this.yCameraCurve > n10) {
                this.yCameraCurve = n10;
            }
        }
        if (this.yCameraCurve > n10) {
            this.yCameraCurve -= this.anInt998 + (this.yCameraCurve - n10) * this.anInt999 / 1000;
            if (this.yCameraCurve < n10) {
                this.yCameraCurve = n10;
            }
        }
        int n11 = xCameraCurve - this.xCameraCurve;
        if (n11 > 1024) {
            n11 -= 2048;
        }
        if (n11 < -1024) {
            n11 += 2048;
        }
        if (n11 > 0) {
            this.xCameraCurve += this.anInt998 + n11 * this.anInt999 / 1000;
            this.xCameraCurve &= 0x7FF;
        }
        if (n11 < 0) {
            this.xCameraCurve -= this.anInt998 + -n11 * this.anInt999 / 1000;
            this.xCameraCurve &= 0x7FF;
        }
        int n12 = xCameraCurve - this.xCameraCurve;
        if (n12 > 1024) {
            n12 -= 2048;
        }
        if (n12 < -1024) {
            n12 += 2048;
        }
        if ((n12 < 0 && n11 > 0) || (n12 > 0 && n11 < 0)) {
            this.xCameraCurve = xCameraCurve;
        }
    }
    
    private void drawMenu(final int n, final int n2) {
        final int n3 = this.menuOffsetX - (n - 4);
        final int n4 = -n2 + 4 + this.menuOffsetY;
        final int menuWidth = this.menuWidth;
        final int n5 = this.menuHeight + 1;
        final int n6 = 6116423;
        client.needDrawTabArea = true;
        client.inputTaken = true;
        client.tabAreaAltered = true;
        DrawingArea.drawPixels(n5, n4, n3, n6, menuWidth);
        DrawingArea.drawPixels(16, n4 + 1, n3 + 1, 0, menuWidth - 2);
        DrawingArea.fillPixels(n3 + 1, menuWidth - 2, n5 - 19, 0, n4 + 18);
        this.chatTextDrawingArea.method385(n6, "Choose Option", n4 + 14, n3 + 3);
        final int n7 = super.mouseX - n;
        final int n8 = -n2 + super.mouseY;
        for (int i = 0; i < this.menuActionRow; ++i) {
            final int n9 = n4 + 31 + (this.menuActionRow - 1 - i) * 15;
            int n10 = 16777215;
            if (n7 > n3 && n7 < n3 + menuWidth && n8 > n9 - 13 && n8 < n9 + 3) {
                n10 = 16776960;
            }
            this.chatTextDrawingArea.method389(true, n3 + 3, n10, this.menuActionName[i], n9);
        }
    }
    
    private void drawMenu() {
        final int menuOffsetX = this.menuOffsetX;
        final int menuOffsetY = this.menuOffsetY;
        final int menuWidth = this.menuWidth;
        final int n = this.menuHeight + 1;
        DrawingArea.drawPixels(n - 4, menuOffsetY + 2, menuOffsetX, 7367262, menuWidth);
        DrawingArea.drawPixels(n - 2, menuOffsetY + 1, menuOffsetX + 1, 7367262, menuWidth - 2);
        DrawingArea.drawPixels(n, menuOffsetY, menuOffsetX + 2, 7367262, menuWidth - 4);
        DrawingArea.drawPixels(n - 2, menuOffsetY + 1, menuOffsetX + 3, 2959394, menuWidth - 6);
        DrawingArea.drawPixels(n - 4, menuOffsetY + 2, menuOffsetX + 2, 2959394, menuWidth - 4);
        DrawingArea.drawPixels(n - 6, menuOffsetY + 3, menuOffsetX + 1, 2959394, menuWidth - 2);
        DrawingArea.drawPixels(n - 22, menuOffsetY + 19, menuOffsetX + 2, 5392957, menuWidth - 4);
        DrawingArea.drawPixels(n - 22, menuOffsetY + 20, menuOffsetX + 3, 5392957, menuWidth - 6);
        DrawingArea.drawPixels(n - 23, menuOffsetY + 20, menuOffsetX + 3, 2828060, menuWidth - 6);
        DrawingArea.fillPixels(menuOffsetX + 3, menuWidth - 6, 1, 2763035, menuOffsetY + 2);
        DrawingArea.fillPixels(menuOffsetX + 2, menuWidth - 4, 1, 2762267, menuOffsetY + 3);
        DrawingArea.fillPixels(menuOffsetX + 2, menuWidth - 4, 1, 2433302, menuOffsetY + 4);
        DrawingArea.fillPixels(menuOffsetX + 2, menuWidth - 4, 1, 2170389, menuOffsetY + 5);
        DrawingArea.fillPixels(menuOffsetX + 2, menuWidth - 4, 1, 1973010, menuOffsetY + 6);
        DrawingArea.fillPixels(menuOffsetX + 2, menuWidth - 4, 1, 1709838, menuOffsetY + 7);
        DrawingArea.fillPixels(menuOffsetX + 2, menuWidth - 4, 2, 1380875, menuOffsetY + 8);
        DrawingArea.fillPixels(menuOffsetX + 2, menuWidth - 4, 1, 1051912, menuOffsetY + 10);
        DrawingArea.fillPixels(menuOffsetX + 2, menuWidth - 4, 1, 592388, menuOffsetY + 11);
        DrawingArea.fillPixels(menuOffsetX + 2, menuWidth - 4, 1, 526083, menuOffsetY + 12);
        DrawingArea.fillPixels(menuOffsetX + 2, menuWidth - 4, 1, 592388, menuOffsetY + 13);
        DrawingArea.fillPixels(menuOffsetX + 2, menuWidth - 4, 1, 460802, menuOffsetY + 14);
        DrawingArea.fillPixels(menuOffsetX + 2, menuWidth - 4, 1, 592388, menuOffsetY + 15);
        DrawingArea.fillPixels(menuOffsetX + 2, menuWidth - 4, 1, 460802, menuOffsetY + 16);
        DrawingArea.fillPixels(menuOffsetX + 2, menuWidth - 4, 1, 592388, menuOffsetY + 17);
        DrawingArea.fillPixels(menuOffsetX + 2, menuWidth - 4, 1, 2763035, menuOffsetY + 18);
        DrawingArea.fillPixels(menuOffsetX + 3, menuWidth - 6, 1, 5654851, menuOffsetY + 19);
        this.chatTextDrawingArea.method385(13023381, "Choose Option", menuOffsetY + 14, menuOffsetX + 3);
        int mouseX = super.mouseX;
        int mouseY = super.mouseY;
        if (this.menuScreenArea == 0) {
            mouseX -= 4;
            mouseY -= 4;
        }
        if (this.menuScreenArea == 1) {
            mouseX -= 519;
            mouseY -= 168;
        }
        if (this.menuScreenArea == 2) {
            mouseX -= 17;
            mouseY -= 338;
        }
        if (this.menuScreenArea == 3) {
            mouseX -= 516;
            mouseY += 0;
        }
        for (int i = 0; i < this.menuActionRow; ++i) {
            final int n2 = menuOffsetY + 31 + (this.menuActionRow - 1 - i) * 15;
            int n3 = 13023381;
            if (mouseX > menuOffsetX && mouseX < menuOffsetX + menuWidth && mouseY > n2 - 13 && mouseY < n2 + 3) {
                DrawingArea.drawPixels(15, n2 - 11, menuOffsetX + 3, 7301469, this.menuWidth - 6);
                n3 = 15656390;
            }
            this.chatTextDrawingArea.method389(true, menuOffsetX + 4, n3, this.menuActionName[i], n2 + 1);
        }
    }
    
    private void addFriend(final long n) {
        try {
            if (n == 0L) {
                return;
            }
            if (this.friendsCount >= 100 && this.anInt1046 != 1) {
                this.pushMessage("Your friendlist is full. Max of 100 for free users, and 200 for members", 0, "");
                return;
            }
            if (this.friendsCount >= 200) {
                this.pushMessage("Your friendlist is full. Max of 100 for free users, and 200 for members", 0, "");
                return;
            }
            final String fixName = TextClass.fixName(TextClass.nameForLong(n));
            for (int i = 0; i < this.friendsCount; ++i) {
                if (this.friendsListAsLongs[i] == n) {
                    this.pushMessage(fixName + " is already on your friend list", 0, "");
                    return;
                }
            }
            for (int j = 0; j < this.ignoreCount; ++j) {
                if (this.ignoreListAsLongs[j] == n) {
                    this.pushMessage("Please remove " + fixName + " from your ignore list first", 0, "");
                    return;
                }
            }
            if (fixName.equals(client.myPlayer.name)) {
                return;
            }
            this.friendsList[this.friendsCount] = fixName;
            this.friendsListAsLongs[this.friendsCount] = n;
            this.friendsNodeIDs[this.friendsCount] = 0;
            ++this.friendsCount;
            client.needDrawTabArea = true;
            this.stream.createFrame(188);
            this.stream.writeQWord(n);
        }
        catch (RuntimeException ex) {
            SignLink.reporterror("15283, 68, " + n + ", " + ex.toString());
            throw new RuntimeException();
        }
    }
    
    private int method42(final int n, final int n2, final int n3) {
        final int n4 = n3 >> 7;
        final int n5 = n2 >> 7;
        if (n4 < 0 || n5 < 0 || n4 > 103 || n5 > 103) {
            return 0;
        }
        int n6 = n;
        if (n6 < 3 && (this.byteGroundArray[1][n4][n5] & 0x2) == 0x2) {
            ++n6;
        }
        final int n7 = n3 & 0x7F;
        final int n8 = n2 & 0x7F;
        return (this.intGroundArray[n6][n4][n5] * (128 - n7) + this.intGroundArray[n6][n4 + 1][n5] * n7 >> 7) * (128 - n8) + (this.intGroundArray[n6][n4][n5 + 1] * (128 - n7) + this.intGroundArray[n6][n4 + 1][n5 + 1] * n7 >> 7) * n8 >> 7;
    }
    
    private static String intToKOrMil(final int n) {
        if (n < 100000) {
            return String.valueOf(n);
        }
        if (n < 10000000) {
            return n / 1000 + "K";
        }
        return n / 1000000 + "M";
    }
    
    private void resetLogout() {
        try {
            if (this.socketStream != null) {
                this.socketStream.close();
            }
        }
        catch (Exception ex) {}
        if (client.clientSize != 0) {
            this.toggleSize(0);
        }
        this.socketStream = null;
        this.loginMessage2 = "@whi@Thank-You for playing.";
        this.loggedIn = false;
        this.loginScreenState = 0;
        this.isLoggingIn = false;
        this.unlinkMRUNodes();
        this.worldController.initToNull();
        for (int i = 0; i < 4; ++i) {
            this.aClass11Array1230[i].method210();
        }
        System.gc();
        this.stopMidi();
        this.currentSong = -1;
        this.nextSong = -1;
        this.prevSong = 0;
    }
    
    private void method45() {
        this.aBoolean1031 = true;
        for (int i = 0; i < 7; ++i) {
            this.anIntArray1065[i] = -1;
            for (int j = 0; j < IDK.length; ++j) {
                if (!IDK.cache[j].aBoolean662 && IDK.cache[j].anInt657 == i + (this.aBoolean1047 ? 0 : 7)) {
                    this.anIntArray1065[i] = j;
                    break;
                }
            }
        }
    }
    
    private void method46(final int n, final Stream stream) {
        while (stream.bitPosition + 21 < n * 8) {
            final int bits = stream.readBits(14);
            if (bits == 16383) {
                break;
            }
            if (this.npcArray[bits] == null) {
                this.npcArray[bits] = new NPC();
            }
            final NPC npc = this.npcArray[bits];
            this.npcIndices[this.npcCount++] = bits;
            npc.anInt1537 = client.loopCycle;
            int bits2 = stream.readBits(5);
            if (bits2 > 15) {
                bits2 -= 32;
            }
            int bits3 = stream.readBits(5);
            if (bits3 > 15) {
                bits3 -= 32;
            }
            final int bits4 = stream.readBits(1);
            npc.desc = EntityDef.forID(stream.readBits(14));
            if (stream.readBits(1) == 1) {
                this.anIntArray894[this.anInt893++] = bits;
            }
            npc.anInt1540 = npc.desc.aByte68;
            npc.anInt1504 = npc.desc.anInt79;
            npc.anInt1554 = npc.desc.anInt67;
            npc.anInt1555 = npc.desc.anInt58;
            npc.anInt1556 = npc.desc.anInt83;
            npc.anInt1557 = npc.desc.anInt55;
            npc.anInt1511 = npc.desc.anInt77;
            npc.setPos(client.myPlayer.smallX[0] + bits3, client.myPlayer.smallY[0] + bits2, bits4 == 1);
        }
        stream.finishBitAccess();
    }
    
    public void processGameLoop() {
        if (this.loadingError || this.genericLoadingError) {
            return;
        }
        ++client.loopCycle;
        if (!this.loggedIn) {
            this.processLoginScreenInput();
        }
        else {
            this.mainGameProcessor();
        }
        this.processOnDemandQueue();
    }
    
    private void method47(final boolean b) {
        if (client.myPlayer.x >> 7 == this.destX && client.myPlayer.y >> 7 == this.destY) {
            this.destX = 0;
        }
        int playerCount = this.playerCount;
        if (b) {
            playerCount = 1;
        }
        for (int i = 0; i < playerCount; ++i) {
            Player myPlayer;
            int n;
            if (b) {
                myPlayer = client.myPlayer;
                n = this.myPlayerIndex << 14;
            }
            else {
                myPlayer = this.playerArray[this.playerIndices[i]];
                n = this.playerIndices[i] << 14;
            }
            if (myPlayer != null) {
                if (myPlayer.isVisible()) {
                    myPlayer.aBoolean1699 = (((client.lowMem && this.playerCount > 50) || this.playerCount > 200) && !b && myPlayer.anInt1517 == myPlayer.anInt1511);
                    final int n2 = myPlayer.x >> 7;
                    final int n3 = myPlayer.y >> 7;
                    if (n2 >= 0 && n2 < 104 && n3 >= 0) {
                        if (n3 < 104) {
                            if (myPlayer.aModel_1714 != null && client.loopCycle >= myPlayer.anInt1707 && client.loopCycle < myPlayer.anInt1708) {
                                myPlayer.aBoolean1699 = false;
                                myPlayer.anInt1709 = this.method42(this.plane, myPlayer.y, myPlayer.x);
                                this.worldController.method286(this.plane, myPlayer.y, myPlayer, myPlayer.anInt1552, myPlayer.anInt1722, myPlayer.x, myPlayer.anInt1709, myPlayer.anInt1719, myPlayer.anInt1721, n, myPlayer.anInt1720);
                            }
                            else {
                                if ((myPlayer.x & 0x7F) == 0x40 && (myPlayer.y & 0x7F) == 0x40) {
                                    if (this.anIntArrayArray929[n2][n3] == this.anInt1265) {
                                        continue;
                                    }
                                    this.anIntArrayArray929[n2][n3] = this.anInt1265;
                                }
                                myPlayer.anInt1709 = this.method42(this.plane, myPlayer.y, myPlayer.x);
                                this.worldController.method285(this.plane, myPlayer.anInt1552, myPlayer.anInt1709, n, myPlayer.y, 60, myPlayer.x, myPlayer, myPlayer.aBoolean1541);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private boolean promptUserForInput(final RSInterface rsInterface) {
        final int contentType = rsInterface.contentType;
        if (this.anInt900 == 2) {
            if (contentType == 201) {
                client.inputTaken = true;
                this.inputDialogState = 0;
                this.messagePromptRaised = true;
                this.promptInput = "";
                this.friendsListAction = 1;
                this.aString1121 = "Enter name of friend to add to list";
            }
            if (contentType == 202) {
                client.inputTaken = true;
                this.inputDialogState = 0;
                this.messagePromptRaised = true;
                this.promptInput = "";
                this.friendsListAction = 2;
                this.aString1121 = "Enter name of friend to delete from list";
            }
        }
        if (contentType == 205) {
            this.anInt1011 = 250;
            return true;
        }
        if (contentType == 501) {
            client.inputTaken = true;
            this.inputDialogState = 0;
            this.messagePromptRaised = true;
            this.promptInput = "";
            this.friendsListAction = 4;
            this.aString1121 = "Enter name of player to add to list";
        }
        if (contentType == 502) {
            client.inputTaken = true;
            this.inputDialogState = 0;
            this.messagePromptRaised = true;
            this.promptInput = "";
            this.friendsListAction = 5;
            this.aString1121 = "Enter name of player to delete from list";
        }
        if (contentType == 550) {
            client.inputTaken = true;
            this.inputDialogState = 0;
            this.messagePromptRaised = true;
            this.promptInput = "";
            this.friendsListAction = 6;
            this.aString1121 = "Enter the name of the chat you wish to join";
        }
        if (contentType >= 300 && contentType <= 313) {
            final int n = (contentType - 300) / 2;
            final int n2 = contentType & 0x1;
            int n3 = this.anIntArray1065[n];
            if (n3 != -1) {
                do {
                    if (n2 == 0 && --n3 < 0) {
                        n3 = IDK.length - 1;
                    }
                    if (n2 == 1 && ++n3 >= IDK.length) {
                        n3 = 0;
                    }
                } while (IDK.cache[n3].aBoolean662 || IDK.cache[n3].anInt657 != n + (this.aBoolean1047 ? 0 : 7));
                this.anIntArray1065[n] = n3;
                this.aBoolean1031 = true;
            }
        }
        if (contentType >= 314 && contentType <= 323) {
            final int n4 = (contentType - 314) / 2;
            final int n5 = contentType & 0x1;
            int n6 = this.anIntArray990[n4];
            if (n5 == 0 && --n6 < 0) {
                n6 = client.anIntArrayArray1003[n4].length - 1;
            }
            if (n5 == 1 && ++n6 >= client.anIntArrayArray1003[n4].length) {
                n6 = 0;
            }
            this.anIntArray990[n4] = n6;
            this.aBoolean1031 = true;
        }
        if (contentType == 324 && !this.aBoolean1047) {
            this.aBoolean1047 = true;
            this.method45();
        }
        if (contentType == 325 && this.aBoolean1047) {
            this.aBoolean1047 = false;
            this.method45();
        }
        if (contentType == 326) {
            this.stream.createFrame(101);
            this.stream.writeWordBigEndian(this.aBoolean1047 ? 0 : 1);
            for (int i = 0; i < 7; ++i) {
                this.stream.writeWordBigEndian(this.anIntArray1065[i]);
            }
            for (int j = 0; j < 5; ++j) {
                this.stream.writeWordBigEndian(this.anIntArray990[j]);
            }
            return true;
        }
        if (contentType == 620) {
            this.canMute = !this.canMute;
        }
        if (contentType >= 601 && contentType <= 612) {
            this.clearTopInterfaces();
            if (this.reportAbuseInput.length() > 0) {
                this.stream.createFrame(218);
                this.stream.writeQWord(TextClass.longForName(this.reportAbuseInput));
                this.stream.writeWordBigEndian(contentType - 601);
                this.stream.writeWordBigEndian(this.canMute ? 1 : 0);
            }
        }
        return false;
    }
    
    private void drawXPCounter() {
        final int n = (client.clientSize == 0) ? 1 : (553 + (client.clientWidth - 765));
        final int n2 = (client.clientSize == 0) ? 47 : 8;
        final int mouseX = super.mouseX;
        final int mouseY = super.mouseY;
        this.counter[this.counterOn || ((client.clientSize != 0) ? (mouseX > 553 + (client.clientWidth - 765) && mouseX < 553 + (client.clientWidth - 765) + 34 && mouseY > 3 && mouseY < 37) : (mouseX >= 520 && mouseX <= 554 && mouseY >= 47 && mouseY <= 81))].drawSprite(n, n2);
    }
    
    private void drawCounterOnScreen() {
        final int n = (client.clientSize == 0) ? 520 : (553 + (client.clientWidth - 765));
        final int n2 = (client.clientSize == 0) ? 47 : 8;
        this.counter[2].drawSprite(n - 100, n2 + 8);
        final int textWidth = this.aTextDrawingArea_1271.getTextWidth(Integer.toString(this.xpCounter));
        this.aTextDrawingArea_1271.drawText(16777215, "XP:", n2 + 21, n - 88);
        this.aTextDrawingArea_1271.drawText(16777215, Integer.toString(this.xpCounter), n2 + 21, n - 12 - textWidth, false);
        if (this.expAdded != 0) {
            this.smallText.drawText(16750899, Integer.toString(this.expAdded) + "xp", n2 + 21 + 18 + this.xpAddedPos, n - 12 - this.smallText.getTextWidth(Integer.toString(this.expAdded) + "xp"));
            this.xpAddedPos += 2;
            if (this.xpAddedPos >= 50) {
                this.xpAddedPos = 0;
                this.expAdded = 0;
            }
        }
    }
    
    private void processXPCounterClick() {
        final int mouseX = super.mouseX;
        final int mouseY = super.mouseY;
        if (mouseX >= 520 && mouseX <= 554 && mouseY >= 47 && mouseY <= 81 && client.clientSize == 0) {
            this.menuActionName[3] = (this.counterOn ? "Off" : "On");
            this.menuActionID[3] = 474;
            this.menuActionName[2] = "Reset";
            this.menuActionID[2] = 475;
            this.menuActionName[1] = (this.xpLock ? "Unlock XP" : "Lock XP");
            this.menuActionID[1] = 476;
            this.menuActionRow = 4;
        }
        if (mouseX > 553 + (client.clientWidth - 765) && mouseX < 553 + (client.clientWidth - 765) + 34 && mouseY > 8 && mouseY < 37 && client.clientSize != 0) {
            this.menuActionName[3] = (this.counterOn ? "Off" : "On");
            this.menuActionID[3] = 474;
            this.menuActionName[2] = "Reset";
            this.menuActionID[2] = 475;
            this.menuActionName[1] = (this.xpLock ? "Unlock XP" : "Lock XP");
            this.menuActionID[1] = 476;
            this.menuActionRow = 4;
        }
    }
    
    private void method49(final Stream stream) {
        for (int i = 0; i < this.anInt893; ++i) {
            final int n = this.anIntArray894[i];
            final Player player = this.playerArray[n];
            int unsignedByte = stream.readUnsignedByte();
            if ((unsignedByte & 0x40) != 0x0) {
                unsignedByte += stream.readUnsignedByte() << 8;
            }
            this.method107(unsignedByte, n, stream, player);
        }
    }
    
    private void method50(final int n, final int n2, final int n3, final int n4, final int n5) {
        final int method300 = this.worldController.method300(n5, n3, n);
        if (method300 != 0) {
            final int method301 = this.worldController.method304(n5, n3, n, method300);
            final int n6 = method301 >> 6 & 0x3;
            final int n7 = method301 & 0x1F;
            int n8 = n2;
            if (method300 > 0) {
                n8 = n4;
            }
            final int[] myPixels = this.aClass30_Sub2_Sub1_Sub1_1263.myPixels;
            final int n9 = 24624 + n3 * 4 + (103 - n) * 512 * 4;
            final ObjectDef forID = ObjectDef.forID(method300 >> 14 & 0x7FFF);
            if (forID.anInt758 != -1) {
                final Background background = this.mapScenes[forID.anInt758];
                if (background != null) {
                    background.drawBackground(48 + n3 * 4 + (forID.anInt744 * 4 - background.anInt1452) / 2, 48 + (104 - n - forID.anInt761) * 4 + (forID.anInt761 * 4 - background.anInt1453) / 2);
                }
            }
            else {
                if (n7 == 0 || n7 == 2) {
                    if (n6 == 0) {
                        myPixels[n9 + 512] = (myPixels[n9] = n8);
                        myPixels[n9 + 1536] = (myPixels[n9 + 1024] = n8);
                    }
                    else if (n6 == 1) {
                        myPixels[n9 + 1] = (myPixels[n9] = n8);
                        myPixels[n9 + 3] = (myPixels[n9 + 2] = n8);
                    }
                    else if (n6 == 2) {
                        myPixels[n9 + 3 + 512] = (myPixels[n9 + 3] = n8);
                        myPixels[n9 + 3 + 1536] = (myPixels[n9 + 3 + 1024] = n8);
                    }
                    else if (n6 == 3) {
                        myPixels[n9 + 1536 + 1] = (myPixels[n9 + 1536] = n8);
                        myPixels[n9 + 1536 + 3] = (myPixels[n9 + 1536 + 2] = n8);
                    }
                }
                if (n7 == 3) {
                    if (n6 == 0) {
                        myPixels[n9] = n8;
                    }
                    else if (n6 == 1) {
                        myPixels[n9 + 3] = n8;
                    }
                    else if (n6 == 2) {
                        myPixels[n9 + 3 + 1536] = n8;
                    }
                    else if (n6 == 3) {
                        myPixels[n9 + 1536] = n8;
                    }
                }
                if (n7 == 2) {
                    if (n6 == 3) {
                        myPixels[n9 + 512] = (myPixels[n9] = n8);
                        myPixels[n9 + 1536] = (myPixels[n9 + 1024] = n8);
                    }
                    else if (n6 == 0) {
                        myPixels[n9 + 1] = (myPixels[n9] = n8);
                        myPixels[n9 + 3] = (myPixels[n9 + 2] = n8);
                    }
                    else if (n6 == 1) {
                        myPixels[n9 + 3 + 512] = (myPixels[n9 + 3] = n8);
                        myPixels[n9 + 3 + 1536] = (myPixels[n9 + 3 + 1024] = n8);
                    }
                    else if (n6 == 2) {
                        myPixels[n9 + 1536 + 1] = (myPixels[n9 + 1536] = n8);
                        myPixels[n9 + 1536 + 3] = (myPixels[n9 + 1536 + 2] = n8);
                    }
                }
            }
        }
        final int method302 = this.worldController.method302(n5, n3, n);
        if (method302 != 0) {
            final int method303 = this.worldController.method304(n5, n3, n, method302);
            final int n10 = method303 >> 6 & 0x3;
            final int n11 = method303 & 0x1F;
            final ObjectDef forID2 = ObjectDef.forID(method302 >> 14 & 0x7FFF);
            if (forID2.anInt758 != -1) {
                final Background background2 = this.mapScenes[forID2.anInt758];
                if (background2 != null) {
                    background2.drawBackground(48 + n3 * 4 + (forID2.anInt744 * 4 - background2.anInt1452) / 2, 48 + (104 - n - forID2.anInt761) * 4 + (forID2.anInt761 * 4 - background2.anInt1453) / 2);
                }
            }
            else if (n11 == 9) {
                int n12 = 15658734;
                if (method302 > 0) {
                    n12 = 15597568;
                }
                final int[] myPixels2 = this.aClass30_Sub2_Sub1_Sub1_1263.myPixels;
                final int n13 = 24624 + n3 * 4 + (103 - n) * 512 * 4;
                if (n10 == 0 || n10 == 2) {
                    myPixels2[n13 + 1024 + 1] = (myPixels2[n13 + 1536] = n12);
                    myPixels2[n13 + 3] = (myPixels2[n13 + 512 + 2] = n12);
                }
                else {
                    myPixels2[n13 + 512 + 1] = (myPixels2[n13] = n12);
                    myPixels2[n13 + 1536 + 3] = (myPixels2[n13 + 1024 + 2] = n12);
                }
            }
        }
        final int method304 = this.worldController.method303(n5, n3, n);
        if (method304 != 0) {
            final ObjectDef forID3 = ObjectDef.forID(method304 >> 14 & 0x7FFF);
            if (forID3.anInt758 != -1) {
                final Background background3 = this.mapScenes[forID3.anInt758];
                if (background3 != null) {
                    background3.drawBackground(48 + n3 * 4 + (forID3.anInt744 * 4 - background3.anInt1452) / 2, 48 + (104 - n - forID3.anInt761) * 4 + (forID3.anInt761 * 4 - background3.anInt1453) / 2);
                }
            }
        }
    }
    
    private void loadTitleScreen() {
        this.aBackground_966 = new Background(this.titleStreamLoader, "titlebox", 0);
        this.aBackground_967 = new Background(this.titleStreamLoader, "titlebutton", 0);
        this.titleBox = new Background(this.titleStreamLoader, "titlebox", 0);
        this.boxHover = new Sprite(this.titleStreamLoader, "titlebox", 1);
        this.titleButton = new Background(this.titleStreamLoader, "titlebutton", 0);
        this.loginHover = new Sprite(this.titleStreamLoader, "titlebutton", 1);
        this.aBackgroundArray1152s = new Background[12];
        int int1 = 0;
        try {
            int1 = Integer.parseInt(this.getParameter("fl_icon"));
        }
        catch (Exception ex) {}
        if (int1 == 0) {
            for (int i = 0; i < 12; ++i) {
                this.aBackgroundArray1152s[i] = new Background(this.titleStreamLoader, "runes", i);
            }
        }
        else {
            for (int j = 0; j < 12; ++j) {
                this.aBackgroundArray1152s[j] = new Background(this.titleStreamLoader, "runes", 12 + (j & 0x3));
            }
        }
        this.aClass30_Sub2_Sub1_Sub1_1201 = new Sprite(128, 265);
        this.aClass30_Sub2_Sub1_Sub1_1202 = new Sprite(128, 265);
        System.arraycopy(this.aRSImageProducer_1110.anIntArray315, 0, this.aClass30_Sub2_Sub1_Sub1_1201.myPixels, 0, 33920);
        System.arraycopy(this.aRSImageProducer_1111.anIntArray315, 0, this.aClass30_Sub2_Sub1_Sub1_1202.myPixels, 0, 33920);
        this.anIntArray851 = new int[256];
        for (int k = 0; k < 64; ++k) {
            this.anIntArray851[k] = k * 262144;
        }
        for (int l = 0; l < 64; ++l) {
            this.anIntArray851[l + 64] = 16711680 + 1024 * l;
        }
        for (int n = 0; n < 64; ++n) {
            this.anIntArray851[n + 128] = 16776960 + 4 * n;
        }
        for (int n2 = 0; n2 < 64; ++n2) {
            this.anIntArray851[n2 + 192] = 16777215;
        }
        this.anIntArray852 = new int[256];
        for (int n3 = 0; n3 < 64; ++n3) {
            this.anIntArray852[n3] = n3 * 1024;
        }
        for (int n4 = 0; n4 < 64; ++n4) {
            this.anIntArray852[n4 + 64] = 65280 + 4 * n4;
        }
        for (int n5 = 0; n5 < 64; ++n5) {
            this.anIntArray852[n5 + 128] = 65535 + 262144 * n5;
        }
        for (int n6 = 0; n6 < 64; ++n6) {
            this.anIntArray852[n6 + 192] = 16777215;
        }
        this.anIntArray853 = new int[256];
        for (int n7 = 0; n7 < 64; ++n7) {
            this.anIntArray853[n7] = n7 * 4;
        }
        for (int n8 = 0; n8 < 64; ++n8) {
            this.anIntArray853[n8 + 64] = 255 + 262144 * n8;
        }
        for (int n9 = 0; n9 < 64; ++n9) {
            this.anIntArray853[n9 + 128] = 16711935 + 1024 * n9;
        }
        for (int n10 = 0; n10 < 64; ++n10) {
            this.anIntArray853[n10 + 192] = 16777215;
        }
        this.anIntArray850 = new int[256];
        this.anIntArray1190 = new int[32768];
        this.anIntArray1191 = new int[32768];
        this.randomizeBackground(null);
        this.anIntArray828 = new int[32768];
        this.anIntArray829 = new int[32768];
        this.drawLogo();
        if (!this.aBoolean831) {
            this.drawFlames = true;
            this.aBoolean831 = true;
            this.startRunnable(this, 2);
        }
    }
    
    private static void setHighMem() {
        WorldController.lowMem = false;
        Texture.lowMem = false;
        client.lowMem = false;
        ObjectManager.lowMem = false;
        ObjectDef.lowMem = false;
    }
    
    public static void main(final String[] array) {
        try {
            client.clientSize = 0;
            client.clientHeight = 503;
            client.clientWidth = 765;
            client.nodeID = 10;
            client.portOff = 0;
            setHighMem();
            client.isMembers = true;
            SignLink.storeid = 32;
            SignLink.startpriv(InetAddress.getLocalHost());
            client.instance = new client();
            new Jframe(array);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadingStages() {
        if (client.lowMem && this.loadingStage == 2 && ObjectManager.anInt131 != this.plane) {
            this.gameScreenIP.initDrawingArea();
            this.drawLoadingMessages(1, "Loading - please wait.", null);
            if (client.clientSize == 0) {
                this.gameScreenIP.drawGraphics(4, super.graphics, 4);
            }
            else if (client.clientSize >= 1) {
                this.gameScreenIP.drawGraphics(0, super.graphics, 0);
            }
            this.loadingStage = 1;
            this.aLong824 = System.currentTimeMillis();
        }
        if (this.loadingStage == 1) {
            final int method54 = this.method54();
            if (method54 != 0 && System.currentTimeMillis() - this.aLong824 > 360000L) {
                SignLink.reporterror(this.myUsername + " glcfb " + this.aLong1215 + "," + method54 + "," + client.lowMem + "," + this.decompressors[0] + "," + this.onDemandFetcher.getNodeCount() + "," + this.plane + "," + this.anInt1069 + "," + this.anInt1070);
                this.aLong824 = System.currentTimeMillis();
            }
        }
        if (this.loadingStage == 2 && this.plane != this.anInt985) {
            this.anInt985 = this.plane;
            this.method24(this.plane);
        }
    }
    
    private int method54() {
        for (int i = 0; i < this.aByteArrayArray1183.length; ++i) {
            if (this.aByteArrayArray1183[i] == null && this.anIntArray1235[i] != -1) {
                return -1;
            }
            if (this.aByteArrayArray1247[i] == null && this.anIntArray1236[i] != -1) {
                return -2;
            }
        }
        boolean b = true;
        for (int j = 0; j < this.aByteArrayArray1183.length; ++j) {
            final byte[] array = this.aByteArrayArray1247[j];
            if (array != null) {
                int n = (this.anIntArray1234[j] >> 8) * 64 - this.baseX;
                int n2 = (this.anIntArray1234[j] & 0xFF) * 64 - this.baseY;
                if (this.aBoolean1159) {
                    n = 10;
                    n2 = 10;
                }
                b &= ObjectManager.method189(n, array, n2);
            }
        }
        if (!b) {
            return -3;
        }
        if (this.aBoolean1080) {
            return -4;
        }
        this.loadingStage = 2;
        ObjectManager.anInt131 = this.plane;
        this.method22();
        this.stream.createFrame(121);
        return 0;
    }
    
    private void method55() {
        for (Animable_Sub4 animable_Sub4 = (Animable_Sub4)this.aClass19_1013.reverseGetFirst(); animable_Sub4 != null; animable_Sub4 = (Animable_Sub4)this.aClass19_1013.reverseGetNext()) {
            if (animable_Sub4.anInt1597 != this.plane || client.loopCycle > animable_Sub4.anInt1572) {
                animable_Sub4.unlink();
            }
            else if (client.loopCycle >= animable_Sub4.anInt1571) {
                if (animable_Sub4.anInt1590 > 0) {
                    final NPC npc = this.npcArray[animable_Sub4.anInt1590 - 1];
                    if (npc != null && npc.x >= 0 && npc.x < 13312 && npc.y >= 0 && npc.y < 13312) {
                        animable_Sub4.method455(client.loopCycle, npc.y, this.method42(animable_Sub4.anInt1597, npc.y, npc.x) - animable_Sub4.anInt1583, npc.x);
                    }
                }
                if (animable_Sub4.anInt1590 < 0) {
                    final int n = -animable_Sub4.anInt1590 - 1;
                    Player myPlayer;
                    if (n == this.unknownInt10) {
                        myPlayer = client.myPlayer;
                    }
                    else {
                        myPlayer = this.playerArray[n];
                    }
                    if (myPlayer != null && myPlayer.x >= 0 && myPlayer.x < 13312 && myPlayer.y >= 0 && myPlayer.y < 13312) {
                        animable_Sub4.method455(client.loopCycle, myPlayer.y, this.method42(animable_Sub4.anInt1597, myPlayer.y, myPlayer.x) - animable_Sub4.anInt1583, myPlayer.x);
                    }
                }
                animable_Sub4.method456(this.anInt945);
                this.worldController.method285(this.plane, animable_Sub4.anInt1595, (int)animable_Sub4.aDouble1587, -1, (int)animable_Sub4.aDouble1586, 60, (int)animable_Sub4.aDouble1585, animable_Sub4, false);
            }
        }
    }
    
    @Override
    public AppletContext getAppletContext() {
        if (SignLink.mainapp != null) {
            return SignLink.mainapp.getAppletContext();
        }
        return super.getAppletContext();
    }
    
    private void drawLogo() {
        final Sprite sprite = new Sprite("background");
        this.aRSImageProducer_1110.initDrawingArea();
        sprite.method346(0, 0);
        this.aRSImageProducer_1111.initDrawingArea();
        sprite.method346(-637, 0);
        this.aRSImageProducer_1107.initDrawingArea();
        sprite.method346(-128, 0);
        this.aRSImageProducer_1108.initDrawingArea();
        sprite.method346(-202, -371);
        this.aRSImageProducer_1109.initDrawingArea();
        sprite.method346(0, 0);
        this.aRSImageProducer_1112.initDrawingArea();
        sprite.method346(0, -265);
        this.aRSImageProducer_1113.initDrawingArea();
        sprite.method346(-562, -265);
        this.aRSImageProducer_1114.initDrawingArea();
        sprite.method346(-128, -171);
        this.aRSImageProducer_1115.initDrawingArea();
        sprite.method346(-562, -171);
        final int[] array = new int[sprite.myWidth];
        for (int i = 0; i < sprite.myHeight; ++i) {
            for (int j = 0; j < sprite.myWidth; ++j) {
                array[j] = sprite.myPixels[sprite.myWidth - j - 1 + sprite.myWidth * i];
            }
            System.arraycopy(array, 0, sprite.myPixels, sprite.myWidth * i, sprite.myWidth);
        }
        System.gc();
    }
    
    private void processOnDemandQueue() {
        while (true) {
            final OnDemandData nextNode = this.onDemandFetcher.getNextNode();
            if (nextNode == null) {
                break;
            }
            if (nextNode.dataType == 0) {
                Model.method460(nextNode.buffer, nextNode.ID);
                client.needDrawTabArea = true;
                if (this.backDialogID != -1) {
                    client.inputTaken = true;
                }
            }
            if (nextNode.dataType == 2 && nextNode.ID == this.nextSong && nextNode.buffer != null) {
                SoundProvider.getSingleton().playMIDI(nextNode.buffer);
            }
            if (nextNode.dataType == 3 && this.loadingStage == 1) {
                int i = 0;
                while (i < this.aByteArrayArray1183.length) {
                    if (this.anIntArray1235[i] == nextNode.ID) {
                        this.aByteArrayArray1183[i] = nextNode.buffer;
                        if (nextNode.buffer == null) {
                            this.anIntArray1235[i] = -1;
                            break;
                        }
                        break;
                    }
                    else if (this.anIntArray1236[i] != nextNode.ID) {
                        ++i;
                    }
                    else {
                        this.aByteArrayArray1247[i] = nextNode.buffer;
                        if (nextNode.buffer == null) {
                            this.anIntArray1236[i] = -1;
                            break;
                        }
                        break;
                    }
                }
            }
            if (nextNode.dataType != 93 || !this.onDemandFetcher.method564(nextNode.ID)) {
                continue;
            }
            ObjectManager.method173(new Stream(nextNode.buffer), this.onDemandFetcher);
        }
    }
    
    private void calcFlamesPosition() {
        final int n = 256;
        for (int i = 10; i < 117; ++i) {
            if ((int)(Math.random() * 100.0) < 50) {
                this.anIntArray828[i + (n - 2 << 7)] = 255;
            }
        }
        for (int j = 0; j < 100; ++j) {
            this.anIntArray828[(int)(Math.random() * 124.0) + 2 + ((int)(Math.random() * 128.0) + 128 << 7)] = 192;
        }
        for (int k = 1; k < n - 1; ++k) {
            for (int l = 1; l < 127; ++l) {
                final int n2 = l + (k << 7);
                this.anIntArray829[n2] = (this.anIntArray828[n2 - 1] + this.anIntArray828[n2 + 1] + this.anIntArray828[n2 - 128] + this.anIntArray828[n2 + 128]) / 4;
            }
        }
        this.anInt1275 += 128;
        if (this.anInt1275 > this.anIntArray1190.length) {
            this.anInt1275 -= this.anIntArray1190.length;
            this.randomizeBackground(this.aBackgroundArray1152s[(int)(Math.random() * 12.0)]);
        }
        for (int n3 = 1; n3 < n - 1; ++n3) {
            for (int n4 = 1; n4 < 127; ++n4) {
                final int n5 = n4 + (n3 << 7);
                int n6 = this.anIntArray829[n5 + 128] - this.anIntArray1190[n5 + this.anInt1275 & this.anIntArray1190.length - 1] / 5;
                if (n6 < 0) {
                    n6 = 0;
                }
                this.anIntArray828[n5] = n6;
            }
        }
        System.arraycopy(this.anIntArray969, 1, this.anIntArray969, 0, n - 1);
        this.anIntArray969[n - 1] = (int)(Math.sin(client.loopCycle / 14.0) * 16.0 + Math.sin(client.loopCycle / 15.0) * 14.0 + Math.sin(client.loopCycle / 16.0) * 12.0);
        if (this.anInt1040 > 0) {
            this.anInt1040 -= 4;
        }
        if (this.anInt1041 > 0) {
            this.anInt1041 -= 4;
        }
        if (this.anInt1040 == 0 && this.anInt1041 == 0) {
            final int n7 = (int)(Math.random() * 2000.0);
            if (n7 == 0) {
                this.anInt1040 = 1024;
            }
            if (n7 == 1) {
                this.anInt1041 = 1024;
            }
        }
    }
    
    private void method60(final int n) {
        final RSInterface rsInterface = RSInterface.interfaceCache[n];
        for (int n2 = 0; n2 < rsInterface.children.length && rsInterface.children[n2] != -1; ++n2) {
            final RSInterface rsInterface2 = RSInterface.interfaceCache[rsInterface.children[n2]];
            if (rsInterface2.type == 1) {
                this.method60(rsInterface2.id);
            }
            rsInterface2.anInt246 = 0;
            rsInterface2.anInt208 = 0;
        }
    }
    
    private void drawHeadIcon() {
        if (this.anInt855 != 2) {
            return;
        }
        this.calcEntityScreenPos((this.anInt934 - this.baseX << 7) + this.anInt937, this.anInt936 * 2, (this.anInt935 - this.baseY << 7) + this.anInt938);
        if (this.spriteDrawX > -1 && client.loopCycle % 20 < 10) {
            this.headIconsHint[0].drawSprite(this.spriteDrawX - 12, this.spriteDrawY - 28);
        }
    }
    
    private void mainGameProcessor() {
        if (this.anInt1104 > 1) {
            --this.anInt1104;
        }
        if (this.anInt1011 > 0) {
            --this.anInt1011;
        }
        for (int n = 0; n < 5 && this.parsePacket(); ++n) {}
        if (!this.loggedIn) {
            return;
        }
        synchronized (this.mouseDetection.syncObject) {
            if ((client.clientHeight != this.getGameComponent().getHeight() || client.clientWidth != this.getGameComponent().getWidth()) && client.clientSize == 0) {
                client.clientHeight = this.getGameComponent().getHeight();
                client.clientWidth = this.getGameComponent().getWidth();
            }
            if ((client.clientHeight != this.getGameComponent().getHeight() - ((super.gameFrame == null) ? 0 : 32) || client.clientWidth != this.getGameComponent().getWidth() - ((super.gameFrame == null) ? 0 : 10)) && client.clientSize == 2) {
                this.toggleSize(2);
                if (client.clientWidth < 980 && super.gameFrame != null) {
                    this.getGameComponent().setSize(990, client.clientHeight + ((super.gameFrame == null) ? 0 : 32));
                }
            }
            if (client.flagged) {
                if (super.clickMode3 != 0 || this.mouseDetection.coordsIndex >= 40) {
                    this.stream.createFrame(45);
                    this.stream.writeWordBigEndian(0);
                    final int currentOffset = this.stream.currentOffset;
                    int n2 = 0;
                    for (int n3 = 0; n3 < this.mouseDetection.coordsIndex && currentOffset - this.stream.currentOffset < 240; ++n3) {
                        ++n2;
                        int anInt1238 = this.mouseDetection.coordsY[n3];
                        if (anInt1238 < 0) {
                            anInt1238 = 0;
                        }
                        else if (anInt1238 > 502) {
                            anInt1238 = 502;
                        }
                        int anInt1239 = this.mouseDetection.coordsX[n3];
                        if (anInt1239 < 0) {
                            anInt1239 = 0;
                        }
                        else if (anInt1239 > 764) {
                            anInt1239 = 764;
                        }
                        int n4 = anInt1238 * 765 + anInt1239;
                        if (this.mouseDetection.coordsY[n3] == -1 && this.mouseDetection.coordsX[n3] == -1) {
                            anInt1239 = -1;
                            anInt1238 = -1;
                            n4 = 524287;
                        }
                        if (anInt1239 == this.anInt1237 && anInt1238 == this.anInt1238) {
                            if (this.anInt1022 < 2047) {
                                ++this.anInt1022;
                            }
                        }
                        else {
                            int n5 = anInt1239 - this.anInt1237;
                            this.anInt1237 = anInt1239;
                            int n6 = anInt1238 - this.anInt1238;
                            this.anInt1238 = anInt1238;
                            if (this.anInt1022 < 8 && n5 >= -32 && n5 <= 31 && n6 >= -32 && n6 <= 31) {
                                n5 += 32;
                                n6 += 32;
                                this.stream.writeWord((this.anInt1022 << 12) + (n5 << 6) + n6);
                                this.anInt1022 = 0;
                            }
                            else if (this.anInt1022 < 8) {
                                this.stream.writeDWordBigEndian(8388608 + (this.anInt1022 << 19) + n4);
                                this.anInt1022 = 0;
                            }
                            else {
                                this.stream.writeDWord(-1073741824 + (this.anInt1022 << 19) + n4);
                                this.anInt1022 = 0;
                            }
                        }
                    }
                    this.stream.writeBytes(this.stream.currentOffset - currentOffset);
                    if (n2 >= this.mouseDetection.coordsIndex) {
                        this.mouseDetection.coordsIndex = 0;
                    }
                    else {
                        final MouseDetection mouseDetection = this.mouseDetection;
                        mouseDetection.coordsIndex -= n2;
                        for (int i = 0; i < this.mouseDetection.coordsIndex; ++i) {
                            this.mouseDetection.coordsX[i] = this.mouseDetection.coordsX[i + n2];
                            this.mouseDetection.coordsY[i] = this.mouseDetection.coordsY[i + n2];
                        }
                    }
                }
            }
            else {
                this.mouseDetection.coordsIndex = 0;
            }
        }
        if (super.clickMode3 != 0) {
            long n7 = (super.aLong29 - this.aLong1220) / 50L;
            if (n7 > 4095L) {
                n7 = 4095L;
            }
            this.aLong1220 = super.aLong29;
            int saveClickY = super.saveClickY;
            if (saveClickY < 0) {
                saveClickY = 0;
            }
            else if (saveClickY > 502) {
                saveClickY = 502;
            }
            int saveClickX = super.saveClickX;
            if (saveClickX < 0) {
                saveClickX = 0;
            }
            else if (saveClickX > 764) {
                saveClickX = 764;
            }
            final int n8 = saveClickY * 765 + saveClickX;
            int n9 = 0;
            if (super.clickMode3 == 2) {
                n9 = 1;
            }
            final int n10 = (int)n7;
            this.stream.createFrame(241);
            this.stream.writeDWord((n10 << 20) + (n9 << 19) + n8);
        }
        if (this.anInt1016 > 0) {
            --this.anInt1016;
        }
        if (super.keyArray[1] == 1 || super.keyArray[2] == 1 || super.keyArray[3] == 1 || super.keyArray[4] == 1) {
            this.aBoolean1017 = true;
        }
        if (this.aBoolean1017 && this.anInt1016 <= 0) {
            this.anInt1016 = 20;
            this.aBoolean1017 = false;
            this.stream.createFrame(86);
            this.stream.writeWord(this.anInt1184);
            this.stream.method432(this.minimapInt1);
        }
        if (super.awtFocus && !this.aBoolean954) {
            this.aBoolean954 = true;
            this.stream.createFrame(3);
            this.stream.writeWordBigEndian(1);
        }
        if (!super.awtFocus && this.aBoolean954) {
            this.aBoolean954 = false;
            this.stream.createFrame(3);
            this.stream.writeWordBigEndian(0);
        }
        this.loadingStages();
        this.method115();
        this.method90();
        ++this.anInt1009;
        if (this.anInt1009 > 750) {
            this.dropClient();
        }
        this.method114();
        this.method95();
        this.method38();
        ++this.anInt945;
        if (this.crossType != 0) {
            this.crossIndex += 20;
            if (this.crossIndex >= 400) {
                this.crossType = 0;
            }
        }
        if (this.atInventoryInterfaceType != 0) {
            ++this.atInventoryLoopCycle;
            if (this.atInventoryLoopCycle >= 15) {
                if (this.atInventoryInterfaceType == 2) {
                    client.needDrawTabArea = true;
                }
                if (this.atInventoryInterfaceType == 3) {
                    client.inputTaken = true;
                }
                this.atInventoryInterfaceType = 0;
            }
        }
        if (this.activeInterfaceType != 0) {
            ++this.anInt989;
            if (super.mouseX > this.anInt1087 + 5 || super.mouseX < this.anInt1087 - 5 || super.mouseY > this.anInt1088 + 5 || super.mouseY < this.anInt1088 - 5) {
                this.aBoolean1242 = true;
            }
            if (super.clickMode2 == 0) {
                if (this.activeInterfaceType == 2) {
                    client.needDrawTabArea = true;
                }
                if (this.activeInterfaceType == 3) {
                    client.inputTaken = true;
                }
                this.activeInterfaceType = 0;
                if (this.aBoolean1242 && this.anInt989 >= 5) {
                    this.lastActiveInvInterface = -1;
                    this.processRightClick();
                    if (this.lastActiveInvInterface == this.anInt1084 && this.mouseInvInterfaceIndex != this.anInt1085) {
                        final RSInterface rsInterface = RSInterface.interfaceCache[this.anInt1084];
                        int n11 = 0;
                        if (this.anInt913 == 1 && rsInterface.contentType == 206) {
                            n11 = 1;
                        }
                        if (rsInterface.inv[this.mouseInvInterfaceIndex] <= 0) {
                            n11 = 0;
                        }
                        if (rsInterface.aBoolean235) {
                            final int anInt1240 = this.anInt1085;
                            final int mouseInvInterfaceIndex = this.mouseInvInterfaceIndex;
                            rsInterface.inv[mouseInvInterfaceIndex] = rsInterface.inv[anInt1240];
                            rsInterface.invStackSizes[mouseInvInterfaceIndex] = rsInterface.invStackSizes[anInt1240];
                            rsInterface.inv[anInt1240] = -1;
                            rsInterface.invStackSizes[anInt1240] = 0;
                        }
                        else if (n11 == 1) {
                            int j = this.anInt1085;
                            final int mouseInvInterfaceIndex2 = this.mouseInvInterfaceIndex;
                            while (j != mouseInvInterfaceIndex2) {
                                if (j > mouseInvInterfaceIndex2) {
                                    rsInterface.swapInventoryItems(j, j - 1);
                                    --j;
                                }
                                else {
                                    if (j >= mouseInvInterfaceIndex2) {
                                        continue;
                                    }
                                    rsInterface.swapInventoryItems(j, j + 1);
                                    ++j;
                                }
                            }
                        }
                        else {
                            rsInterface.swapInventoryItems(this.anInt1085, this.mouseInvInterfaceIndex);
                        }
                        this.stream.createFrame(214);
                        this.stream.method433(this.anInt1084);
                        this.stream.method424(n11);
                        this.stream.method433(this.anInt1085);
                        this.stream.method431(this.mouseInvInterfaceIndex);
                    }
                }
                else if ((this.anInt1253 == 1 || this.menuHasAddFriend(this.menuActionRow - 1)) && this.menuActionRow > 2) {
                    this.determineMenuSize();
                }
                else if (this.menuActionRow > 0) {
                    this.doAction(this.menuActionRow - 1);
                }
                this.atInventoryLoopCycle = 10;
                super.clickMode3 = 0;
            }
        }
        if (WorldController.anInt470 != -1) {
            final boolean doWalkTo = this.doWalkTo(0, 0, 0, 0, client.myPlayer.smallY[0], 0, 0, WorldController.anInt471, client.myPlayer.smallX[0], true, WorldController.anInt470);
            WorldController.anInt470 = -1;
            if (doWalkTo) {
                this.crossX = super.saveClickX;
                this.crossY = super.saveClickY;
                this.crossType = 1;
                this.crossIndex = 0;
            }
        }
        if (super.clickMode3 == 1 && this.aString844 != null) {
            this.aString844 = null;
            client.inputTaken = true;
            super.clickMode3 = 0;
        }
        this.processMenuClick();
        this.processMainScreenClick();
        this.processTabClick();
        this.processChatModeClick();
        this.processOrbClick();
        if (super.clickMode2 == 1 || super.clickMode3 == 1) {
            ++this.anInt1213;
        }
        if (this.anInt1500 != 0 || this.anInt1044 != 0 || this.anInt1129 != 0) {
            if (this.anInt1501 < 100) {
                ++this.anInt1501;
                if (this.anInt1501 == 100) {
                    if (this.anInt1500 != 0) {
                        client.inputTaken = true;
                    }
                    if (this.anInt1044 != 0) {
                        client.needDrawTabArea = true;
                    }
                }
            }
        }
        else if (this.anInt1501 > 0) {
            --this.anInt1501;
        }
        if (this.loadingStage == 2) {
            this.method108();
        }
        if (this.loadingStage == 2 && this.aBoolean1160) {
            this.calcCameraPos();
        }
        for (int k = 0; k < 5; ++k) {
            final int[] anIntArray1030 = this.anIntArray1030;
            final int n12 = k;
            ++anIntArray1030[n12];
        }
        this.method73();
        ++super.idleTime;
        if (super.idleTime > 4500) {
            this.anInt1011 = 250;
            super.idleTime -= 500;
            this.stream.createFrame(202);
        }
        ++this.anInt988;
        if (this.anInt988 > 500) {
            this.anInt988 = 0;
            final int n13 = (int)(Math.random() * 8.0);
            if ((n13 & 0x1) == 0x1) {
                this.anInt1278 += this.anInt1279;
            }
            if ((n13 & 0x2) == 0x2) {
                this.anInt1131 += this.anInt1132;
            }
            if ((n13 & 0x4) == 0x4) {
                this.anInt896 += this.anInt897;
            }
        }
        if (this.anInt1278 < -50) {
            this.anInt1279 = 2;
        }
        if (this.anInt1278 > 50) {
            this.anInt1279 = -2;
        }
        if (this.anInt1131 < -55) {
            this.anInt1132 = 2;
        }
        if (this.anInt1131 > 55) {
            this.anInt1132 = -2;
        }
        if (this.anInt896 < -40) {
            this.anInt897 = 1;
        }
        if (this.anInt896 > 40) {
            this.anInt897 = -1;
        }
        ++this.anInt1254;
        if (this.anInt1254 > 500) {
            this.anInt1254 = 0;
            final int n14 = (int)(Math.random() * 8.0);
            if ((n14 & 0x1) == 0x1) {
                this.minimapInt2 += this.anInt1210;
            }
            if ((n14 & 0x2) == 0x2) {
                this.minimapInt3 += this.anInt1171;
            }
        }
        if (this.minimapInt2 < -60) {
            this.anInt1210 = 2;
        }
        if (this.minimapInt2 > 60) {
            this.anInt1210 = -2;
        }
        if (this.minimapInt3 < -20) {
            this.anInt1171 = 1;
        }
        if (this.minimapInt3 > 10) {
            this.anInt1171 = -1;
        }
        ++this.anInt1010;
        if (this.anInt1010 > 50) {
            this.stream.createFrame(0);
        }
        try {
            if (this.socketStream != null && this.stream.currentOffset > 0) {
                this.socketStream.queueBytes(this.stream.currentOffset, this.stream.buffer);
                this.stream.currentOffset = 0;
                this.anInt1010 = 0;
            }
        }
        catch (IOException ex) {
            this.dropClient();
        }
        catch (Exception ex2) {
            this.resetLogout();
        }
    }
    
    private void method63() {
        for (Class30_Sub1 class30_Sub1 = (Class30_Sub1)this.aClass19_1179.reverseGetFirst(); class30_Sub1 != null; class30_Sub1 = (Class30_Sub1)this.aClass19_1179.reverseGetNext()) {
            if (class30_Sub1.anInt1294 == -1) {
                class30_Sub1.anInt1302 = 0;
                this.method89(class30_Sub1);
            }
            else {
                class30_Sub1.unlink();
            }
        }
    }
    
    @Override
    public void resetImageProducers() {
        if (this.aRSImageProducer_1107 != null) {
            return;
        }
        super.fullGameScreen = null;
        this.chatAreaIP = null;
        this.mapAreaIP = null;
        this.tabAreaIP = null;
        this.gameScreenIP = null;
        this.aRSImageProducer_1123 = null;
        this.aRSImageProducer_1124 = null;
        this.aRSImageProducer_1125 = null;
        this.aRSImageProducer_1110 = new RSImageProducer(128, 265, this.getGameComponent());
        DrawingArea.setAllPixelsToZero();
        this.aRSImageProducer_1111 = new RSImageProducer(128, 265, this.getGameComponent());
        DrawingArea.setAllPixelsToZero();
        this.aRSImageProducer_1107 = new RSImageProducer(509, 171, this.getGameComponent());
        DrawingArea.setAllPixelsToZero();
        this.aRSImageProducer_1108 = new RSImageProducer(360, 132, this.getGameComponent());
        DrawingArea.setAllPixelsToZero();
        this.aRSImageProducer_1109 = new RSImageProducer(765, 503, this.getGameComponent());
        DrawingArea.setAllPixelsToZero();
        this.aRSImageProducer_1112 = new RSImageProducer(202, 238, this.getGameComponent());
        DrawingArea.setAllPixelsToZero();
        this.aRSImageProducer_1113 = new RSImageProducer(203, 238, this.getGameComponent());
        DrawingArea.setAllPixelsToZero();
        this.aRSImageProducer_1114 = new RSImageProducer(74, 94, this.getGameComponent());
        DrawingArea.setAllPixelsToZero();
        this.aRSImageProducer_1115 = new RSImageProducer(75, 94, this.getGameComponent());
        DrawingArea.setAllPixelsToZero();
        if (this.titleStreamLoader != null) {
            this.drawLogo();
            this.loadTitleScreen();
        }
        this.welcomeScreenRaised = true;
    }
    
    private void resetImage() {
        DrawingArea.setAllPixelsToZero();
    }
    
    @Override
    void drawLoadingText(final int anInt1079, final String aString1049) {
        this.anInt1079 = anInt1079;
        this.aString1049 = aString1049;
        this.resetImageProducers();
        if (this.titleStreamLoader == null) {
            return;
        }
        this.aRSImageProducer_1109.initDrawingArea();
        this.loadingBarEmpty = new Sprite("Configuration/empty");
        this.loadingBarFull = new Sprite("Configuration/full");
        this.resetImageProducers();
        this.loadingBarEmpty.drawSprite(220, 9);
        this.loadingBarFull.drawSprite(286, 31);
        DrawingArea.drawPixels(13, 31, 286 + anInt1079, 3157548, 194 - anInt1079);
        if (anInt1079 == 194) {
            this.smallText.drawText(16777215, aString1049 + " - 100%", 28, 385);
        }
        else {
            this.smallText.drawText(16777215, aString1049 + " - " + anInt1079 / 2 + "%", 28, 385);
        }
        this.aRSImageProducer_1109.drawGraphics(0, super.graphics, 0);
        if (this.welcomeScreenRaised) {
            this.welcomeScreenRaised = false;
        }
    }
    
    void drawLoadingText2(final int anInt1079, final String aString1049) {
        super.drawLoadingText(this.anInt1079 = anInt1079, aString1049);
        this.aString1049 = aString1049;
        this.resetImageProducers();
        if (this.titleStreamLoader == null) {
            return;
        }
        this.aRSImageProducer_1109.initDrawingArea();
        this.loadingBarEmpty = new Sprite("Configuration/empty");
        this.loadingBarFull = new Sprite("Configuration/full");
        this.resetImageProducers();
        this.loadingBarEmpty.drawSprite(220, 9);
        this.loadingBarFull.drawSprite(286, 31);
        DrawingArea.drawPixels(13, 31, 286 + anInt1079, 3157548, 194 - anInt1079);
        if (anInt1079 == 194) {
            this.smallText.drawText(16777215, aString1049 + " - 100%", 28, 385);
        }
        else {
            this.smallText.drawText(16777215, aString1049 + " - " + anInt1079 / 2 + "%", 28, 385);
        }
        this.aRSImageProducer_1109.drawGraphics(0, super.graphics, 0);
        if (this.welcomeScreenRaised) {
            this.welcomeScreenRaised = false;
        }
    }
    
    private void method65(final int n, final int n2, final int n3, final int n4, final RSInterface rsInterface, final int n5, final boolean b, final int n6) {
        int n7;
        if (this.aBoolean972) {
            n7 = 32;
        }
        else {
            n7 = 0;
        }
        this.aBoolean972 = false;
        if (n3 >= n && n3 < n + 16 && n4 >= n5 && n4 < n5 + 16) {
            rsInterface.scrollPosition -= this.anInt1213 * 4;
            if (b) {
                client.needDrawTabArea = true;
            }
        }
        else if (n3 >= n && n3 < n + 16 && n4 >= n5 + n2 - 16 && n4 < n5 + n2) {
            rsInterface.scrollPosition += this.anInt1213 * 4;
            if (b) {
                client.needDrawTabArea = true;
            }
        }
        else if (n3 >= n - n7 && n3 < n + 16 + n7 && n4 >= n5 + 16 && n4 < n5 + n2 - 16 && this.anInt1213 > 0) {
            int n8 = (n2 - 32) * n2 / n6;
            if (n8 < 8) {
                n8 = 8;
            }
            rsInterface.scrollPosition = (n6 - n2) * (n4 - n5 - 16 - n8 / 2) / (n2 - 32 - n8);
            if (b) {
                client.needDrawTabArea = true;
            }
            this.aBoolean972 = true;
        }
    }
    
    private boolean method66(final int n, final int n2, final int n3) {
        final int n4 = n >> 14 & 0x7FFF;
        final int method304 = this.worldController.method304(this.plane, n3, n2, n);
        if (method304 == -1) {
            return false;
        }
        final int n5 = method304 & 0x1F;
        final int n6 = method304 >> 6 & 0x3;
        if (n5 == 10 || n5 == 11 || n5 == 22) {
            final ObjectDef forID = ObjectDef.forID(n4);
            int n7;
            int n8;
            if (n6 == 0 || n6 == 2) {
                n7 = forID.anInt744;
                n8 = forID.anInt761;
            }
            else {
                n7 = forID.anInt761;
                n8 = forID.anInt744;
            }
            int anInt768 = forID.anInt768;
            if (n6 != 0) {
                anInt768 = (anInt768 << n6 & 0xF) + (anInt768 >> 4 - n6);
            }
            this.doWalkTo(2, 0, n8, 0, client.myPlayer.smallY[0], n7, anInt768, n2, client.myPlayer.smallX[0], false, n3);
        }
        else {
            this.doWalkTo(2, n6, 0, n5 + 1, client.myPlayer.smallY[0], 0, 0, n2, client.myPlayer.smallX[0], false, n3);
        }
        this.crossX = super.saveClickX;
        this.crossY = super.saveClickY;
        this.crossType = 2;
        this.crossIndex = 0;
        return true;
    }
    
    private StreamLoader streamLoaderForName(final int n, final String s, final String s2, final int n2, final int n3) {
        final File file = new File(SignLink.findcachedir());
        byte[] array = null;
        int n4 = 5;
        try {
            if (this.decompressors[0] != null) {
                array = this.decompressors[0].decompress(n);
            }
        }
        catch (Exception ex) {}
        if (array == null) {
            this.drawLoadingText2(15, "Updating files.");
            String s3 = "http://dl.dropbox.com/u/32985851/DevilishpkzCache.zip";
            if (Math.random() > 0.5) {
                s3 = "http://dl.dropbox.com/u/32985851/DevilishpkzCache.zip";
                System.out.println("Downloading Devilishpkz Cache");
            }
            this.downloadcache(s3, "DevilishpkzCache.zip", "", "DevilishpkzCache");
            try {
                final String findcachedir = SignLink.findcachedir();
                final File file2 = new File(findcachedir + "main_file_cache.dat");
                SignLink.cache_dat = new RandomAccessFile(findcachedir + "main_file_cache.dat", "rw");
                for (int i = 0; i < 5; ++i) {
                    SignLink.cache_idx[i] = new RandomAccessFile(findcachedir + "main_file_cache.idx" + i, "rw");
                }
                if (SignLink.cache_dat != null) {
                    for (int j = 0; j < 5; ++j) {
                        this.decompressors[j] = new Decompressor(SignLink.cache_dat, SignLink.cache_idx[j], j + 1);
                    }
                }
                if (this.decompressors[0] != null) {
                    array = this.decompressors[0].decompress(n);
                }
            }
            catch (Exception ex2) {}
        }
        if (array == null) {}
        if (array != null) {
            return new StreamLoader(array, s);
        }
        final int n5 = 0;
        while (array == null) {
            String string = "Unknown error";
            this.drawLoadingText2(n3, "Requesting " + s);
            try {
                int n6 = 0;
                final DataInputStream openJagGrabInputStream = this.openJagGrabInputStream(s2 + n2);
                final byte[] array2 = new byte[6];
                openJagGrabInputStream.readFully(array2, 0, 6);
                final Stream stream = new Stream(array2);
                stream.currentOffset = 3;
                final int n7 = stream.read3Bytes() + 6;
                int k = 6;
                array = new byte[n7];
                System.arraycopy(array2, 0, array, 0, 6);
                while (k < n7) {
                    int n8 = n7 - k;
                    if (n8 > 1000) {
                        n8 = 1000;
                    }
                    final int read = openJagGrabInputStream.read(array, k, n8);
                    if (read < 0) {
                        string = "Length error: " + k + "/" + n7;
                        throw new IOException("EOF");
                    }
                    k += read;
                    final int n9 = k * 100 / n7;
                    if (n9 != n6) {
                        this.drawLoadingText2(n3, "Loading " + s + " - " + n9 + "%");
                    }
                    n6 = n9;
                }
                openJagGrabInputStream.close();
                try {
                    if (this.decompressors[0] != null) {
                        this.decompressors[0].method234(array.length, array, n);
                    }
                }
                catch (Exception ex3) {
                    this.decompressors[0] = null;
                }
            }
            catch (IOException ex4) {
                if (string.equals("Unknown error")) {
                    string = "Connection error";
                }
                array = null;
            }
            catch (NullPointerException ex5) {
                string = "Null error";
                array = null;
                if (!SignLink.reporterror) {
                    return null;
                }
            }
            catch (ArrayIndexOutOfBoundsException ex6) {
                string = "Bounds error";
                array = null;
                if (!SignLink.reporterror) {
                    return null;
                }
            }
            catch (Exception ex7) {
                string = "Unexpected error";
                array = null;
                if (!SignLink.reporterror) {
                    return null;
                }
            }
            if (array == null) {
                for (int l = n4; l > 0; --l) {
                    if (n5 >= 3) {
                        this.drawLoadingText2(n3, "Game updated - please reload page");
                        l = 10;
                    }
                    else {
                        this.drawLoadingText(n3, string + " - Retrying in " + l);
                    }
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (Exception ex8) {}
                }
                n4 *= 2;
                if (n4 > 60) {
                    n4 = 60;
                }
                this.aBoolean872 = !this.aBoolean872;
            }
        }
        return new StreamLoader(array, s);
    }
    
    public void writeStream(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final byte[] array = new byte[4096];
        int read;
        while ((read = inputStream.read(array)) >= 0) {
            outputStream.write(array, 0, read);
        }
        inputStream.close();
        outputStream.close();
    }
    
    private boolean unZipFile() {
        try {
            final ZipFile zipFile = new ZipFile(SignLink.findcachedir() + this.dir + this.fName);
            zipFile.size();
            final Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                final ZipEntry zipEntry = (ZipEntry)entries.nextElement();
                this.drawLoadingText(5, "Unpacking files.");
                if (zipEntry.isDirectory()) {
                    new File(SignLink.findcachedir() + this.dir + zipEntry.getName()).mkdirs();
                }
                else {
                    if (zipEntry.getName().contains("/")) {
                        final File file = new File(SignLink.findcachedir() + this.dir + zipEntry.getName().replaceAll(zipEntry.getName().substring(zipEntry.getName().indexOf("/")), ""));
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                    }
                    this.writeStream(zipFile.getInputStream(zipEntry), new BufferedOutputStream(new FileOutputStream(SignLink.findcachedir() + this.dir + zipEntry.getName())));
                }
            }
            zipFile.close();
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public void deleteFile() {
        try {
            new File(SignLink.findcachedir() + this.dir + this.fName).delete();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void downloadcache(final String s, final String fName, final String dir, final String s2) {
        this.dir = dir;
        this.fName = fName;
        boolean b = true;
        try {
            final URLConnection openConnection = new URL(s).openConnection();
            final String[] split = s.split("/");
            final File file = new File(split[split.length - 1]);
            final int contentLength = openConnection.getContentLength();
            final File file2 = new File(SignLink.findcachedir() + this.dir + "/DevilishpkzCache.zip");
            System.out.println(contentLength + " : " + file2.length());
            if (file2.exists() && file2.length() == contentLength) {
                this.unZipFile();
                return;
            }
            final InputStream inputStream = openConnection.getInputStream();
            try {
                new File(SignLink.findcachedir() + this.dir).mkdir();
            }
            catch (Exception ex2) {}
            final FileOutputStream fileOutputStream = new FileOutputStream(SignLink.findcachedir() + this.dir + file);
            int n = 0;
            final byte[] array = new byte[4096];
            final long currentTimeMillis = System.currentTimeMillis();
            int read;
            while ((read = inputStream.read(array)) != -1) {
                fileOutputStream.write(array, 0, read);
                n += read;
                final int n2 = (int)(n / contentLength * 100.0);
                this.drawLoadingText2(n2, "Installing updates - " + n2 + ("% @ " + (int)(n / 1024 / (1L + (System.currentTimeMillis() - currentTimeMillis) / 1000L)) + " Kb/s"));
            }
            if (contentLength != n) {
                inputStream.close();
                fileOutputStream.close();
            }
            else {
                this.drawLoadingText2(5, "Unpacking files...");
                inputStream.close();
                fileOutputStream.close();
                if (this.unZipFile()) {
                    b = false;
                }
                this.drawLoadingText2(10, "Unpacking was complete");
            }
        }
        catch (Exception ex) {
            b = false;
            System.err.println("Error connecting to server.");
            ex.printStackTrace();
        }
        if (b) {
            this.deleteFile();
        }
    }
    
    private void dropClient() {
        if (this.anInt1011 > 0 || this.gameScreenIP == null) {
            this.resetLogout();
            return;
        }
        this.gameScreenIP.initDrawingArea();
        DrawingArea.fillPixels(2, 229, 39, 16777215, 2);
        DrawingArea.drawPixels(37, 3, 3, 0, 227);
        this.aTextDrawingArea_1271.drawText(0, "Connection lost.", 19, 120);
        this.aTextDrawingArea_1271.drawText(16777215, "Connection lost.", 18, 119);
        this.aTextDrawingArea_1271.drawText(0, "Please wait - attempting to reestablish.", 34, 117);
        this.aTextDrawingArea_1271.drawText(16777215, "Please wait - attempting to reestablish.", 34, 116);
        if (client.clientSize == 0) {
            this.gameScreenIP.drawGraphics(4, this.graphics, 4);
        }
        else if (client.clientSize >= 1 || client.clientSize == 2) {
            this.gameScreenIP.drawGraphics(0, this.graphics, 0);
        }
        this.anInt1021 = 0;
        this.destX = 0;
        final RSSocket socketStream = this.socketStream;
        this.loggedIn = false;
        this.loginFailures = 0;
        this.login(this.myUsername, this.myPassword, true);
        if (!this.loggedIn) {
            this.resetLogout();
        }
        try {
            socketStream.close();
        }
        catch (Exception ex) {}
    }
    
    private void doAction(final int n) {
        if (n < 0) {
            return;
        }
        if (this.inputDialogState != 0) {
            this.inputDialogState = 0;
            client.inputTaken = true;
        }
        final int anInt1283 = this.menuActionCmd2[n];
        final int n2 = this.menuActionCmd3[n];
        int n3 = this.menuActionID[n];
        final int anInt1284 = this.menuActionCmd1[n];
        if (n3 >= 2000) {
            n3 -= 2000;
        }
        if (n3 == 582) {
            final NPC npc = this.npcArray[anInt1284];
            if (npc != null) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, npc.smallY[0], client.myPlayer.smallX[0], false, npc.smallX[0]);
                this.crossX = super.saveClickX;
                this.crossY = super.saveClickY;
                this.crossType = 2;
                this.crossIndex = 0;
                this.stream.createFrame(57);
                this.stream.method432(this.anInt1285);
                this.stream.method432(anInt1284);
                this.stream.method431(this.anInt1283);
                this.stream.method432(this.anInt1284);
            }
        }
        if (n3 == 234) {
            if (!this.doWalkTo(2, 0, 0, 0, client.myPlayer.smallY[0], 0, 0, n2, client.myPlayer.smallX[0], false, anInt1283)) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, n2, client.myPlayer.smallX[0], false, anInt1283);
            }
            this.crossX = super.saveClickX;
            this.crossY = super.saveClickY;
            this.crossType = 2;
            this.crossIndex = 0;
            this.stream.createFrame(236);
            this.stream.method431(n2 + this.baseY);
            this.stream.writeWord(anInt1284);
            this.stream.method431(anInt1283 + this.baseX);
        }
        if (n3 == 62 && this.method66(anInt1284, n2, anInt1283)) {
            this.stream.createFrame(192);
            this.stream.writeWord(this.anInt1284);
            this.stream.method431(anInt1284 >> 14 & 0x7FFF);
            this.stream.method433(n2 + this.baseY);
            this.stream.method431(this.anInt1283);
            this.stream.method433(anInt1283 + this.baseX);
            this.stream.writeWord(this.anInt1285);
        }
        if (n3 == 104) {
            final RSInterface rsInterface = RSInterface.interfaceCache[n2];
            client.spellID = rsInterface.id;
            if (!this.Autocast) {
                this.Autocast = true;
                this.autocastId = rsInterface.id;
                this.stream.createFrame(185);
                this.stream.writeWord(rsInterface.id);
            }
            else if (this.autocastId == rsInterface.id) {
                this.Autocast = false;
                this.autocastId = 0;
                this.stream.createFrame(185);
                this.stream.writeWord(rsInterface.id);
            }
            else if (this.autocastId != rsInterface.id) {
                this.Autocast = true;
                this.autocastId = rsInterface.id;
                this.stream.createFrame(185);
                this.stream.writeWord(rsInterface.id);
            }
        }
        if (n3 == 511) {
            if (!this.doWalkTo(2, 0, 0, 0, client.myPlayer.smallY[0], 0, 0, n2, client.myPlayer.smallX[0], false, anInt1283)) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, n2, client.myPlayer.smallX[0], false, anInt1283);
            }
            this.crossX = super.saveClickX;
            this.crossY = super.saveClickY;
            this.crossType = 2;
            this.crossIndex = 0;
            this.stream.createFrame(25);
            this.stream.method431(this.anInt1284);
            this.stream.method432(this.anInt1285);
            this.stream.writeWord(anInt1284);
            this.stream.method432(n2 + this.baseY);
            this.stream.method433(this.anInt1283);
            this.stream.writeWord(anInt1283 + this.baseX);
        }
        if (n3 == 74) {
            this.stream.createFrame(122);
            this.stream.method433(n2);
            this.stream.method432(anInt1283);
            this.stream.method431(anInt1284);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = n2;
            this.atInventoryIndex = anInt1283;
            this.atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[n2].parentID == client.openInterfaceID) {
                this.atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[n2].parentID == this.backDialogID) {
                this.atInventoryInterfaceType = 3;
            }
        }
        if (n3 == 315) {
            client.spellID = RSInterface.interfaceCache[n2].id;
            final RSInterface rsInterface2 = RSInterface.interfaceCache[n2];
            boolean promptUserForInput = true;
            if (rsInterface2.contentType > 0) {
                promptUserForInput = this.promptUserForInput(rsInterface2);
            }
            if (promptUserForInput) {
                if (this.myPrivilege > 1) {}
                switch (n2) {
                    case 19144: {
                        this.sendFrame248(15106, 3213);
                        this.method60(15106);
                        client.inputTaken = true;
                        break;
                    }
                    case 18699: {
                        final int openInterfaceID = 20999;
                        this.method60(openInterfaceID);
                        if (this.invOverlayInterfaceID != -1) {
                            this.invOverlayInterfaceID = -1;
                            client.needDrawTabArea = true;
                            client.tabAreaAltered = true;
                        }
                        if (this.backDialogID != -1) {
                            this.backDialogID = -1;
                            client.inputTaken = true;
                        }
                        if (this.inputDialogState != 0) {
                            this.inputDialogState = 0;
                            client.inputTaken = true;
                        }
                        client.openInterfaceID = openInterfaceID;
                        this.aBoolean1149 = false;
                        break;
                    }
                    case 21004: {
                        if (this.invOverlayInterfaceID != -1) {
                            this.invOverlayInterfaceID = -1;
                            client.needDrawTabArea = true;
                            client.tabAreaAltered = true;
                        }
                        if (this.backDialogID != -1) {
                            this.backDialogID = -1;
                            client.inputTaken = true;
                        }
                        if (this.inputDialogState != 0) {
                            this.inputDialogState = 0;
                            client.inputTaken = true;
                        }
                        client.openInterfaceID = -1;
                        this.aBoolean1149 = false;
                        break;
                    }
                    case 23001: {
                        if (this.webclient) {
                            this.pushMessage("For FullScreen mode, download the client! Not Available on the Webclient!", 0, this.aString1049);
                            break;
                        }
                        if (super.gameFrame == null) {
                            this.toggleSize(2);
                            this.pushMessage("You can now resize your screen.", 0, this.aString1049);
                        }
                        else {
                            this.toggleSize(1);
                            this.pushMessage("You can now resize your screen.", 0, this.aString1049);
                        }
                        client.tabInterfaceIDs[11] = 904;
                        break;
                    }
                    case 23002: {
                        if (this.webclient) {
                            this.pushMessage("For FullScreen mode, download the client! Not Available on the Webclient!", 0, this.aString1049);
                            break;
                        }
                        this.toggleSize(2);
                        this.pushMessage("You can now resize your screen.", 0, this.aString1049);
                        client.tabInterfaceIDs[11] = 904;
                        break;
                    }
                    case 23003: {
                        if (this.webclient) {
                            this.pushMessage("For FullScreen mode, download the client! Not Available on the Webclient!", 0, this.aString1049);
                            break;
                        }
                        this.toggleSize(0);
                        this.pushMessage("You can now resize your screen.", 0, this.aString1049);
                        client.tabInterfaceIDs[11] = 904;
                        break;
                    }
                    case 23006:
                    case 23007: {
                        if (this.isNewGameFrame) {
                            RSInterface.interfaceCache[23006].setSprite(new Sprite("/Settings/CHECK 1"));
                            this.draw562GameFrame(0, 0);
                        }
                        else {
                            RSInterface.interfaceCache[23006].setSprite(new Sprite("/Settings/CHECK 0"));
                            this.draw525GameFrame(1, 1);
                        }
                        this.isNewGameFrame = !this.isNewGameFrame;
                        break;
                    }
                    case 23008:
                    case 23009: {
                        if (this.newHitMarks) {
                            RSInterface.interfaceCache[23008].setSprite(new Sprite("/Settings/CHECK 1"));
                        }
                        else {
                            RSInterface.interfaceCache[23008].setSprite(new Sprite("/Settings/CHECK 0"));
                        }
                        this.newHitMarks = !this.newHitMarks;
                        break;
                    }
                    case 23010:
                    case 23011: {
                        if (client.newHits) {
                            RSInterface.interfaceCache[23010].setSprite(new Sprite("/Settings/CHECK 1"));
                        }
                        else {
                            RSInterface.interfaceCache[23010].setSprite(new Sprite("/Settings/CHECK 0"));
                        }
                        client.newHits = !client.newHits;
                        break;
                    }
                    default: {
                        this.stream.createFrame(185);
                        this.stream.writeWord(n2);
                        break;
                    }
                }
            }
        }
        if (n3 == 561) {
            final Player player = this.playerArray[anInt1284];
            if (player != null) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, player.smallY[0], client.myPlayer.smallX[0], false, player.smallX[0]);
                this.crossX = super.saveClickX;
                this.crossY = super.saveClickY;
                this.crossType = 2;
                this.crossIndex = 0;
                client.anInt1188 += anInt1284;
                if (client.anInt1188 >= 90) {
                    this.stream.createFrame(136);
                    client.anInt1188 = 0;
                }
                this.stream.createFrame(128);
                this.stream.writeWord(anInt1284);
            }
        }
        if (n3 == 20) {
            final NPC npc2 = this.npcArray[anInt1284];
            if (npc2 != null) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, npc2.smallY[0], client.myPlayer.smallX[0], false, npc2.smallX[0]);
                this.crossX = super.saveClickX;
                this.crossY = super.saveClickY;
                this.crossType = 2;
                this.crossIndex = 0;
                this.stream.createFrame(155);
                this.stream.method431(anInt1284);
            }
        }
        if (n3 == 779) {
            final Player player2 = this.playerArray[anInt1284];
            if (player2 != null) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, player2.smallY[0], client.myPlayer.smallX[0], false, player2.smallX[0]);
                this.crossX = super.saveClickX;
                this.crossY = super.saveClickY;
                this.crossType = 2;
                this.crossIndex = 0;
                this.stream.createFrame(153);
                this.stream.method431(anInt1284);
            }
        }
        if (n3 == 516) {
            if (!this.menuOpen) {
                this.worldController.method312(super.saveClickY - 4, super.saveClickX - 4);
            }
            else {
                this.worldController.method312(n2 - 4, anInt1283 - 4);
            }
        }
        if (n3 == 1062) {
            client.anInt924 += this.baseX;
            if (client.anInt924 >= 113) {
                client.anInt924 = 0;
            }
            this.method66(anInt1284, n2, anInt1283);
            this.stream.createFrame(228);
            this.stream.method432(anInt1284 >> 14 & 0x7FFF);
            this.stream.method432(n2 + this.baseY);
            this.stream.writeWord(anInt1283 + this.baseX);
        }
        if (n3 == 679 && !this.aBoolean1149) {
            this.stream.createFrame(40);
            this.stream.writeWord(n2);
            this.aBoolean1149 = true;
        }
        if (n3 == 431) {
            this.stream.createFrame(129);
            this.stream.method432(anInt1283);
            this.stream.writeWord(n2);
            this.stream.method432(anInt1284);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = n2;
            this.atInventoryIndex = anInt1283;
            this.atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[n2].parentID == client.openInterfaceID) {
                this.atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[n2].parentID == this.backDialogID) {
                this.atInventoryInterfaceType = 3;
            }
        }
        if (n3 == 337 || n3 == 42 || n3 == 792 || n3 == 322) {
            final String s = this.menuActionName[n];
            final int index = s.indexOf("@whi@");
            if (index != -1) {
                final long longForName = TextClass.longForName(s.substring(index + 5).trim());
                if (n3 == 337) {
                    this.addFriend(longForName);
                }
                if (n3 == 42) {
                    this.addIgnore(longForName);
                }
                if (n3 == 792) {
                    this.delFriend(longForName);
                }
                if (n3 == 322) {
                    this.delIgnore(longForName);
                }
            }
        }
        if (n3 == 53) {
            this.stream.createFrame(135);
            this.stream.method431(anInt1283);
            this.stream.method432(n2);
            this.stream.method431(anInt1284);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = n2;
            this.atInventoryIndex = anInt1283;
            this.atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[n2].parentID == client.openInterfaceID) {
                this.atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[n2].parentID == this.backDialogID) {
                this.atInventoryInterfaceType = 3;
            }
        }
        if (n3 == 539) {
            this.stream.createFrame(16);
            this.stream.method432(anInt1284);
            this.stream.method433(anInt1283);
            this.stream.method433(n2);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = n2;
            this.atInventoryIndex = anInt1283;
            this.atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[n2].parentID == client.openInterfaceID) {
                this.atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[n2].parentID == this.backDialogID) {
                this.atInventoryInterfaceType = 3;
            }
        }
        if (n3 == 484 || n3 == 6) {
            final String s2 = this.menuActionName[n];
            final int index2 = s2.indexOf("@whi@");
            if (index2 != -1) {
                final String fixName = TextClass.fixName(TextClass.nameForLong(TextClass.longForName(s2.substring(index2 + 5).trim())));
                boolean b = false;
                for (int i = 0; i < this.playerCount; ++i) {
                    final Player player3 = this.playerArray[this.playerIndices[i]];
                    if (player3 != null && player3.name != null && player3.name.equalsIgnoreCase(fixName)) {
                        this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, player3.smallY[0], client.myPlayer.smallX[0], false, player3.smallX[0]);
                        if (n3 == 484) {
                            this.stream.createFrame(139);
                            this.stream.method431(this.playerIndices[i]);
                        }
                        if (n3 == 6) {
                            client.anInt1188 += anInt1284;
                            if (client.anInt1188 >= 90) {
                                this.stream.createFrame(136);
                                client.anInt1188 = 0;
                            }
                            this.stream.createFrame(128);
                            this.stream.writeWord(this.playerIndices[i]);
                        }
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    this.pushMessage("Unable to find " + fixName, 0, "");
                }
            }
        }
        if (n3 == 870) {
            this.stream.createFrame(53);
            this.stream.writeWord(anInt1283);
            this.stream.method432(this.anInt1283);
            this.stream.method433(anInt1284);
            this.stream.writeWord(this.anInt1284);
            this.stream.method431(this.anInt1285);
            this.stream.writeWord(n2);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = n2;
            this.atInventoryIndex = anInt1283;
            this.atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[n2].parentID == client.openInterfaceID) {
                this.atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[n2].parentID == this.backDialogID) {
                this.atInventoryInterfaceType = 3;
            }
        }
        if (n3 == 847) {
            this.stream.createFrame(87);
            this.stream.method432(anInt1284);
            this.stream.writeWord(n2);
            this.stream.method432(anInt1283);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = n2;
            this.atInventoryIndex = anInt1283;
            this.atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[n2].parentID == client.openInterfaceID) {
                this.atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[n2].parentID == this.backDialogID) {
                this.atInventoryInterfaceType = 3;
            }
        }
        if (n3 == 626) {
            final RSInterface rsInterface3 = RSInterface.interfaceCache[n2];
            this.spellSelected = 1;
            client.spellID = rsInterface3.id;
            this.anInt1137 = n2;
            this.spellUsableOn = rsInterface3.spellUsableOn;
            this.itemSelected = 0;
            client.needDrawTabArea = true;
            client.spellID = rsInterface3.id;
            String s3 = rsInterface3.selectedActionName;
            if (s3.indexOf(" ") != -1) {
                s3 = s3.substring(0, s3.indexOf(" "));
            }
            String s4 = rsInterface3.selectedActionName;
            if (s4.indexOf(" ") != -1) {
                s4 = s4.substring(s4.indexOf(" ") + 1);
            }
            this.spellTooltip = s3 + " " + rsInterface3.spellName + " " + s4;
            if (this.spellUsableOn == 16) {
                client.needDrawTabArea = true;
                client.tabID = 3;
                client.tabAreaAltered = true;
            }
            return;
        }
        if (n3 == 78) {
            this.stream.createFrame(117);
            this.stream.method433(n2);
            this.stream.method433(anInt1284);
            this.stream.method431(anInt1283);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = n2;
            this.atInventoryIndex = anInt1283;
            this.atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[n2].parentID == client.openInterfaceID) {
                this.atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[n2].parentID == this.backDialogID) {
                this.atInventoryInterfaceType = 3;
            }
        }
        if (n3 == 27) {
            final Player player4 = this.playerArray[anInt1284];
            if (player4 != null) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, player4.smallY[0], client.myPlayer.smallX[0], false, player4.smallX[0]);
                this.crossX = super.saveClickX;
                this.crossY = super.saveClickY;
                this.crossType = 2;
                this.crossIndex = 0;
                client.anInt986 += anInt1284;
                if (client.anInt986 >= 54) {
                    client.anInt986 = 0;
                }
                this.stream.createFrame(73);
                this.stream.method431(anInt1284);
            }
        }
        if (n3 == 213) {
            if (!this.doWalkTo(2, 0, 0, 0, client.myPlayer.smallY[0], 0, 0, n2, client.myPlayer.smallX[0], false, anInt1283)) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, n2, client.myPlayer.smallX[0], false, anInt1283);
            }
            this.crossX = super.saveClickX;
            this.crossY = super.saveClickY;
            this.crossType = 2;
            this.crossIndex = 0;
            this.stream.createFrame(79);
            this.stream.method431(n2 + this.baseY);
            this.stream.writeWord(anInt1284);
            this.stream.method432(anInt1283 + this.baseX);
        }
        if (n3 == 632) {
            this.stream.createFrame(145);
            this.stream.method432(n2);
            this.stream.method432(anInt1283);
            this.stream.method432(anInt1284);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = n2;
            this.atInventoryIndex = anInt1283;
            this.atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[n2].parentID == client.openInterfaceID) {
                this.atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[n2].parentID == this.backDialogID) {
                this.atInventoryInterfaceType = 3;
            }
        }
        if (n3 == 1003) {
            this.clanChatMode = 2;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 1002) {
            this.clanChatMode = 1;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 1001) {
            this.clanChatMode = 0;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 1000) {
            this.cButtonCPos = 4;
            this.chatTypeView = 11;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 999) {
            this.cButtonCPos = 0;
            this.chatTypeView = 0;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 998) {
            this.cButtonCPos = 1;
            this.chatTypeView = 5;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 997) {
            this.publicChatMode = 3;
            this.stream.createFrame(95);
            this.stream.writeWordBigEndian(this.publicChatMode);
            this.stream.writeWordBigEndian(this.privateChatMode);
            this.stream.writeWordBigEndian(this.tradeMode);
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 996) {
            this.publicChatMode = 2;
            this.stream.createFrame(95);
            this.stream.writeWordBigEndian(this.publicChatMode);
            this.stream.writeWordBigEndian(this.privateChatMode);
            this.stream.writeWordBigEndian(this.tradeMode);
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 995) {
            this.publicChatMode = 1;
            this.stream.createFrame(95);
            this.stream.writeWordBigEndian(this.publicChatMode);
            this.stream.writeWordBigEndian(this.privateChatMode);
            this.stream.writeWordBigEndian(this.tradeMode);
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 994) {
            this.publicChatMode = 0;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 993) {
            this.cButtonCPos = 2;
            this.chatTypeView = 1;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 992) {
            this.privateChatMode = 2;
            this.stream.createFrame(95);
            this.stream.writeWordBigEndian(this.publicChatMode);
            this.stream.writeWordBigEndian(this.privateChatMode);
            this.stream.writeWordBigEndian(this.tradeMode);
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 991) {
            this.privateChatMode = 1;
            this.stream.createFrame(95);
            this.stream.writeWordBigEndian(this.publicChatMode);
            this.stream.writeWordBigEndian(this.privateChatMode);
            this.stream.writeWordBigEndian(this.tradeMode);
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 990) {
            this.privateChatMode = 0;
            this.stream.createFrame(95);
            this.stream.writeWordBigEndian(this.publicChatMode);
            this.stream.writeWordBigEndian(this.privateChatMode);
            this.stream.writeWordBigEndian(this.tradeMode);
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 989) {
            this.cButtonCPos = 3;
            this.chatTypeView = 2;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 987) {
            this.tradeMode = 2;
            this.stream.createFrame(95);
            this.stream.writeWordBigEndian(this.publicChatMode);
            this.stream.writeWordBigEndian(this.privateChatMode);
            this.stream.writeWordBigEndian(this.tradeMode);
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 986) {
            this.tradeMode = 1;
            this.stream.createFrame(95);
            this.stream.writeWordBigEndian(this.publicChatMode);
            this.stream.writeWordBigEndian(this.privateChatMode);
            this.stream.writeWordBigEndian(this.tradeMode);
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 985) {
            this.tradeMode = 0;
            this.stream.createFrame(95);
            this.stream.writeWordBigEndian(this.publicChatMode);
            this.stream.writeWordBigEndian(this.privateChatMode);
            this.stream.writeWordBigEndian(this.tradeMode);
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 984) {
            this.cButtonCPos = 5;
            this.chatTypeView = 3;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 983) {
            this.duelMode = 2;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 982) {
            this.duelMode = 1;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 981) {
            this.duelMode = 0;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 980) {
            this.cButtonCPos = 6;
            this.chatTypeView = 4;
            this.aBoolean1233 = true;
            client.inputTaken = true;
        }
        if (n3 == 493) {
            this.stream.createFrame(75);
            this.stream.method433(n2);
            this.stream.method431(anInt1283);
            this.stream.method432(anInt1284);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = n2;
            this.atInventoryIndex = anInt1283;
            this.atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[n2].parentID == client.openInterfaceID) {
                this.atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[n2].parentID == this.backDialogID) {
                this.atInventoryInterfaceType = 3;
            }
        }
        if (n3 == 652) {
            if (!this.doWalkTo(2, 0, 0, 0, client.myPlayer.smallY[0], 0, 0, n2, client.myPlayer.smallX[0], false, anInt1283)) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, n2, client.myPlayer.smallX[0], false, anInt1283);
            }
            this.crossX = super.saveClickX;
            this.crossY = super.saveClickY;
            this.crossType = 2;
            this.crossIndex = 0;
            this.stream.createFrame(156);
            this.stream.method432(anInt1283 + this.baseX);
            this.stream.method431(n2 + this.baseY);
            this.stream.method433(anInt1284);
        }
        if (n3 == 94) {
            if (!this.doWalkTo(2, 0, 0, 0, client.myPlayer.smallY[0], 0, 0, n2, client.myPlayer.smallX[0], false, anInt1283)) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, n2, client.myPlayer.smallX[0], false, anInt1283);
            }
            this.crossX = super.saveClickX;
            this.crossY = super.saveClickY;
            this.crossType = 2;
            this.crossIndex = 0;
            this.stream.createFrame(181);
            this.stream.method431(n2 + this.baseY);
            this.stream.writeWord(anInt1284);
            this.stream.method431(anInt1283 + this.baseX);
            this.stream.method432(this.anInt1137);
        }
        if (n3 == 474) {
            this.counterOn = !this.counterOn;
        }
        if (n3 == 475) {
            this.xpCounter = 0;
        }
        if (n3 == 476) {
            this.stream.createFrame(185);
            this.stream.writeWord(476);
            this.xpLock = !this.xpLock;
        }
        if (n3 == 646) {
            switch (n2) {
                case 941: {
                    this.zoomLevel = 400;
                    break;
                }
                case 942: {
                    this.zoomLevel = 600;
                    break;
                }
                case 943: {
                    this.zoomLevel = 700;
                    break;
                }
                case 944: {
                    this.zoomLevel = 800;
                    break;
                }
                case 945: {
                    this.zoomLevel = 1000;
                    break;
                }
            }
            this.stream.createFrame(185);
            this.stream.writeWord(n2);
            final RSInterface rsInterface4 = RSInterface.interfaceCache[n2];
            if (rsInterface4.valueIndexArray != null && rsInterface4.valueIndexArray[0][0] == 5) {
                final int n4 = rsInterface4.valueIndexArray[0][1];
                if (this.variousSettings[n4] != rsInterface4.anIntArray212[0]) {
                    this.variousSettings[n4] = rsInterface4.anIntArray212[0];
                    this.method33(n4);
                    client.needDrawTabArea = true;
                }
            }
        }
        if (n3 == 225) {
            final NPC npc3 = this.npcArray[anInt1284];
            if (npc3 != null) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, npc3.smallY[0], client.myPlayer.smallX[0], false, npc3.smallX[0]);
                this.crossX = super.saveClickX;
                this.crossY = super.saveClickY;
                this.crossType = 2;
                this.crossIndex = 0;
                client.anInt1226 += anInt1284;
                if (client.anInt1226 >= 85) {
                    client.anInt1226 = 0;
                }
                this.stream.createFrame(17);
                this.stream.method433(anInt1284);
            }
        }
        if (n3 == 965) {
            final NPC npc4 = this.npcArray[anInt1284];
            if (npc4 != null) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, npc4.smallY[0], client.myPlayer.smallX[0], false, npc4.smallX[0]);
                this.crossX = super.saveClickX;
                this.crossY = super.saveClickY;
                this.crossType = 2;
                this.crossIndex = 0;
                ++client.anInt1134;
                if (client.anInt1134 >= 96) {
                    client.anInt1134 = 0;
                }
                this.stream.createFrame(21);
                this.stream.writeWord(anInt1284);
            }
        }
        if (n3 == 413) {
            final NPC npc5 = this.npcArray[anInt1284];
            if (npc5 != null) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, npc5.smallY[0], client.myPlayer.smallX[0], false, npc5.smallX[0]);
                this.crossX = super.saveClickX;
                this.crossY = super.saveClickY;
                this.crossType = 2;
                this.crossIndex = 0;
                this.stream.createFrame(131);
                this.stream.method433(anInt1284);
                this.stream.method432(this.anInt1137);
            }
        }
        if (n3 == 200) {
            this.clearTopInterfaces();
        }
        if (n3 == 1025) {
            final NPC npc6 = this.npcArray[anInt1284];
            if (npc6 != null) {
                EntityDef entityDef = npc6.desc;
                if (entityDef.childrenIDs != null) {
                    entityDef = entityDef.method161();
                }
                if (entityDef != null) {
                    String string;
                    if (entityDef.description != null) {
                        string = new String(entityDef.description);
                    }
                    else {
                        string = "It's a " + entityDef.name + ".";
                    }
                    this.pushMessage(string, 0, "");
                }
            }
        }
        if (n3 == 900) {
            this.method66(anInt1284, n2, anInt1283);
            this.stream.createFrame(252);
            this.stream.method433(anInt1284 >> 14 & 0x7FFF);
            this.stream.method431(n2 + this.baseY);
            this.stream.method432(anInt1283 + this.baseX);
        }
        if (n3 == 412) {
            final NPC npc7 = this.npcArray[anInt1284];
            if (npc7 != null) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, npc7.smallY[0], client.myPlayer.smallX[0], false, npc7.smallX[0]);
                this.crossX = super.saveClickX;
                this.crossY = super.saveClickY;
                this.crossType = 2;
                this.crossIndex = 0;
                this.stream.createFrame(72);
                this.stream.method432(anInt1284);
            }
        }
        if (n3 == 365) {
            final Player player5 = this.playerArray[anInt1284];
            if (player5 != null) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, player5.smallY[0], client.myPlayer.smallX[0], false, player5.smallX[0]);
                this.crossX = super.saveClickX;
                this.crossY = super.saveClickY;
                this.crossType = 2;
                this.crossIndex = 0;
                this.stream.createFrame(249);
                this.stream.method432(anInt1284);
                this.stream.method431(this.anInt1137);
            }
        }
        if (n3 == 729) {
            final Player player6 = this.playerArray[anInt1284];
            if (player6 != null) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, player6.smallY[0], client.myPlayer.smallX[0], false, player6.smallX[0]);
                this.crossX = super.saveClickX;
                this.crossY = super.saveClickY;
                this.crossType = 2;
                this.crossIndex = 0;
                this.stream.createFrame(39);
                this.stream.method431(anInt1284);
            }
        }
        if (n3 == 577) {
            final Player player7 = this.playerArray[anInt1284];
            if (player7 != null) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, player7.smallY[0], client.myPlayer.smallX[0], false, player7.smallX[0]);
                this.crossX = super.saveClickX;
                this.crossY = super.saveClickY;
                this.crossType = 2;
                this.crossIndex = 0;
                this.stream.createFrame(139);
                this.stream.method431(anInt1284);
            }
        }
        if (n3 == 956 && this.method66(anInt1284, n2, anInt1283)) {
            this.stream.createFrame(35);
            this.stream.method431(anInt1283 + this.baseX);
            this.stream.method432(this.anInt1137);
            this.stream.method432(n2 + this.baseY);
            this.stream.method431(anInt1284 >> 14 & 0x7FFF);
        }
        if (n3 == 567) {
            if (!this.doWalkTo(2, 0, 0, 0, client.myPlayer.smallY[0], 0, 0, n2, client.myPlayer.smallX[0], false, anInt1283)) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, n2, client.myPlayer.smallX[0], false, anInt1283);
            }
            this.crossX = super.saveClickX;
            this.crossY = super.saveClickY;
            this.crossType = 2;
            this.crossIndex = 0;
            this.stream.createFrame(23);
            this.stream.method431(n2 + this.baseY);
            this.stream.method431(anInt1284);
            this.stream.method431(anInt1283 + this.baseX);
        }
        if (n3 == 867) {
            if ((anInt1284 & 0x3) == 0x0) {
                ++client.anInt1175;
            }
            if (client.anInt1175 >= 59) {
                client.anInt1175 = 0;
            }
            this.stream.createFrame(43);
            this.stream.method431(n2);
            this.stream.method432(anInt1284);
            this.stream.method432(anInt1283);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = n2;
            this.atInventoryIndex = anInt1283;
            this.atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[n2].parentID == client.openInterfaceID) {
                this.atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[n2].parentID == this.backDialogID) {
                this.atInventoryInterfaceType = 3;
            }
        }
        if (n3 == 543) {
            this.stream.createFrame(237);
            this.stream.writeWord(anInt1283);
            this.stream.method432(anInt1284);
            this.stream.writeWord(n2);
            this.stream.method432(this.anInt1137);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = n2;
            this.atInventoryIndex = anInt1283;
            this.atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[n2].parentID == client.openInterfaceID) {
                this.atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[n2].parentID == this.backDialogID) {
                this.atInventoryInterfaceType = 3;
            }
        }
        if (n3 == 606) {
            final String s5 = this.menuActionName[n];
            final int index3 = s5.indexOf("@whi@");
            if (index3 != -1) {
                if (client.openInterfaceID == -1) {
                    this.clearTopInterfaces();
                    this.reportAbuseInput = s5.substring(index3 + 5).trim();
                    this.canMute = false;
                    for (int j = 0; j < RSInterface.interfaceCache.length; ++j) {
                        if (RSInterface.interfaceCache[j] != null && RSInterface.interfaceCache[j].contentType == 600) {
                            this.reportAbuseInterfaceID = (client.openInterfaceID = RSInterface.interfaceCache[j].parentID);
                            break;
                        }
                    }
                }
                else {
                    this.pushMessage("Please close the interface you have open before using 'report abuse'", 0, "");
                }
            }
        }
        if (n3 == 491) {
            final Player player8 = this.playerArray[anInt1284];
            if (player8 != null) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, player8.smallY[0], client.myPlayer.smallX[0], false, player8.smallX[0]);
                this.crossX = super.saveClickX;
                this.crossY = super.saveClickY;
                this.crossType = 2;
                this.crossIndex = 0;
                this.stream.createFrame(14);
                this.stream.method432(this.anInt1284);
                this.stream.writeWord(anInt1284);
                this.stream.writeWord(this.anInt1285);
                this.stream.method431(this.anInt1283);
            }
        }
        if (n3 == 639) {
            final String s6 = this.menuActionName[n];
            final int index4 = s6.indexOf("@whi@");
            if (index4 != -1) {
                final long longForName2 = TextClass.longForName(s6.substring(index4 + 5).trim());
                int n5 = -1;
                for (int k = 0; k < this.friendsCount; ++k) {
                    if (this.friendsListAsLongs[k] == longForName2) {
                        n5 = k;
                        break;
                    }
                }
                if (n5 != -1 && this.friendsNodeIDs[n5] > 0) {
                    client.inputTaken = true;
                    this.inputDialogState = 0;
                    this.messagePromptRaised = true;
                    this.promptInput = "";
                    this.friendsListAction = 3;
                    this.aLong953 = this.friendsListAsLongs[n5];
                    this.aString1121 = "Enter message to send to " + this.friendsList[n5];
                }
            }
        }
        if (n3 == 454) {
            this.switched = true;
            this.stream.createFrame(41);
            this.stream.writeWord(anInt1284);
            this.stream.method432(anInt1283);
            this.stream.method432(n2);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = n2;
            this.atInventoryIndex = anInt1283;
            this.atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[n2].parentID == client.openInterfaceID) {
                this.atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[n2].parentID == this.backDialogID) {
                this.atInventoryInterfaceType = 3;
            }
        }
        if (n3 == 478) {
            final NPC npc8 = this.npcArray[anInt1284];
            if (npc8 != null) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, npc8.smallY[0], client.myPlayer.smallX[0], false, npc8.smallX[0]);
                this.crossX = super.saveClickX;
                this.crossY = super.saveClickY;
                this.crossType = 2;
                this.crossIndex = 0;
                if ((anInt1284 & 0x3) == 0x0) {
                    ++client.anInt1155;
                }
                if (client.anInt1155 >= 53) {
                    client.anInt1155 = 0;
                }
                this.stream.createFrame(18);
                this.stream.method431(anInt1284);
            }
        }
        if (n3 == 113) {
            this.method66(anInt1284, n2, anInt1283);
            this.stream.createFrame(70);
            this.stream.method431(anInt1283 + this.baseX);
            this.stream.writeWord(n2 + this.baseY);
            this.stream.method433(anInt1284 >> 14 & 0x7FFF);
        }
        if (n3 == 872) {
            this.method66(anInt1284, n2, anInt1283);
            this.stream.createFrame(234);
            this.stream.method433(anInt1283 + this.baseX);
            this.stream.method432(anInt1284 >> 14 & 0x7FFF);
            this.stream.method433(n2 + this.baseY);
        }
        if (n3 == 502) {
            this.method66(anInt1284, n2, anInt1283);
            this.stream.createFrame(132);
            this.stream.method433(anInt1283 + this.baseX);
            this.stream.writeWord(anInt1284 >> 14 & 0x7FFF);
            this.stream.method432(n2 + this.baseY);
        }
        if (n3 == 1125) {
            final ItemDef forID = ItemDef.forID(anInt1284);
            final RSInterface rsInterface5 = RSInterface.interfaceCache[n2];
            String s7;
            if (rsInterface5 != null && rsInterface5.invStackSizes[anInt1283] >= 100000) {
                s7 = rsInterface5.invStackSizes[anInt1283] + " x " + forID.name;
            }
            else if (forID.description != null) {
                s7 = new String(forID.description);
            }
            else {
                s7 = "It's a " + forID.name + ".";
            }
            this.pushMessage(s7, 0, "");
        }
        if (n3 == 169) {
            this.stream.createFrame(185);
            this.stream.writeWord(n2);
            final RSInterface rsInterface6 = RSInterface.interfaceCache[n2];
            if (rsInterface6.valueIndexArray != null && rsInterface6.valueIndexArray[0][0] == 5) {
                final int n6 = rsInterface6.valueIndexArray[0][1];
                this.variousSettings[n6] = 1 - this.variousSettings[n6];
                this.method33(n6);
                client.needDrawTabArea = true;
            }
        }
        if (n3 == 447) {
            this.itemSelected = 1;
            this.anInt1283 = anInt1283;
            this.anInt1284 = n2;
            this.anInt1285 = anInt1284;
            this.selectedItemName = ItemDef.forID(anInt1284).name;
            this.spellSelected = 0;
            client.needDrawTabArea = true;
            return;
        }
        if (n3 == 1226) {
            final ObjectDef forID2 = ObjectDef.forID(anInt1284 >> 14 & 0x7FFF);
            String string2;
            if (forID2.description != null) {
                string2 = new String(forID2.description);
            }
            else {
                string2 = "It's a " + forID2.name + ".";
            }
            this.pushMessage(string2, 0, "");
        }
        if (n3 == 244) {
            if (!this.doWalkTo(2, 0, 0, 0, client.myPlayer.smallY[0], 0, 0, n2, client.myPlayer.smallX[0], false, anInt1283)) {
                this.doWalkTo(2, 0, 1, 0, client.myPlayer.smallY[0], 1, 0, n2, client.myPlayer.smallX[0], false, anInt1283);
            }
            this.crossX = super.saveClickX;
            this.crossY = super.saveClickY;
            this.crossType = 2;
            this.crossIndex = 0;
            this.stream.createFrame(253);
            this.stream.method431(anInt1283 + this.baseX);
            this.stream.method433(n2 + this.baseY);
            this.stream.method432(anInt1284);
        }
        if (n3 == 1448) {
            final ItemDef forID3 = ItemDef.forID(anInt1284);
            String string3;
            if (forID3.description != null) {
                string3 = new String(forID3.description);
            }
            else {
                string3 = "It's a " + forID3.name + ".";
            }
            this.pushMessage(string3, 0, "");
        }
        this.itemSelected = 0;
        this.spellSelected = 0;
        client.needDrawTabArea = true;
    }
    
    private void method70() {
        this.anInt1251 = 0;
        final int n = (client.myPlayer.x >> 7) + this.baseX;
        final int n2 = (client.myPlayer.y >> 7) + this.baseY;
        if (n >= 3053 && n <= 3156 && n2 >= 3056 && n2 <= 3136) {
            this.anInt1251 = 1;
        }
        if (n >= 3072 && n <= 3118 && n2 >= 9492 && n2 <= 9535) {
            this.anInt1251 = 1;
        }
        if (this.anInt1251 == 1 && n >= 3139 && n <= 3199 && n2 >= 3008 && n2 <= 3062) {
            this.anInt1251 = 0;
        }
    }
    
    @Override
    public void run() {
        if (this.drawFlames) {
            this.drawFlames();
        }
        else {
            super.run();
        }
    }
    
    private void build3dScreenMenu() {
        if (this.itemSelected == 0 && this.spellSelected == 0) {
            this.menuActionName[this.menuActionRow] = "Walk here";
            this.menuActionID[this.menuActionRow] = 516;
            this.menuActionCmd2[this.menuActionRow] = super.mouseX;
            this.menuActionCmd3[this.menuActionRow] = super.mouseY;
            ++this.menuActionRow;
        }
        int n = -1;
        for (int i = 0; i < Model.anInt1687; ++i) {
            final int n2 = Model.anIntArray1688[i];
            final int n3 = n2 & 0x7F;
            final int n4 = n2 >> 7 & 0x7F;
            final int n5 = n2 >> 29 & 0x3;
            final int n6 = n2 >> 14 & 0x7FFF;
            if (n2 != n) {
                n = n2;
                if (n5 == 2 && this.worldController.method304(this.plane, n3, n4, n2) >= 0) {
                    ObjectDef objectDef = ObjectDef.forID(n6);
                    if (objectDef.childrenIDs != null) {
                        objectDef = objectDef.method580();
                    }
                    if (objectDef == null) {
                        continue;
                    }
                    if (this.itemSelected == 1) {
                        this.menuActionName[this.menuActionRow] = "Use " + this.selectedItemName + " with @cya@" + objectDef.name;
                        this.menuActionID[this.menuActionRow] = 62;
                        this.menuActionCmd1[this.menuActionRow] = n2;
                        this.menuActionCmd2[this.menuActionRow] = n3;
                        this.menuActionCmd3[this.menuActionRow] = n4;
                        ++this.menuActionRow;
                    }
                    else if (this.spellSelected == 1) {
                        if ((this.spellUsableOn & 0x4) == 0x4) {
                            this.menuActionName[this.menuActionRow] = this.spellTooltip + " @cya@" + objectDef.name;
                            this.menuActionID[this.menuActionRow] = 956;
                            this.menuActionCmd1[this.menuActionRow] = n2;
                            this.menuActionCmd2[this.menuActionRow] = n3;
                            this.menuActionCmd3[this.menuActionRow] = n4;
                            ++this.menuActionRow;
                        }
                    }
                    else {
                        if (objectDef.actions != null) {
                            for (int j = 4; j >= 0; --j) {
                                if (objectDef.actions[j] != null) {
                                    this.menuActionName[this.menuActionRow] = objectDef.actions[j] + " @cya@" + objectDef.name;
                                    if (j == 0) {
                                        this.menuActionID[this.menuActionRow] = 502;
                                    }
                                    if (j == 1) {
                                        this.menuActionID[this.menuActionRow] = 900;
                                    }
                                    if (j == 2) {
                                        this.menuActionID[this.menuActionRow] = 113;
                                    }
                                    if (j == 3) {
                                        this.menuActionID[this.menuActionRow] = 872;
                                    }
                                    if (j == 4) {
                                        this.menuActionID[this.menuActionRow] = 1062;
                                    }
                                    this.menuActionCmd1[this.menuActionRow] = n2;
                                    this.menuActionCmd2[this.menuActionRow] = n3;
                                    this.menuActionCmd3[this.menuActionRow] = n4;
                                    ++this.menuActionRow;
                                }
                            }
                        }
                        this.menuActionName[this.menuActionRow] = "Examine @cya@" + objectDef.name;
                        this.menuActionID[this.menuActionRow] = 1226;
                        this.menuActionCmd1[this.menuActionRow] = objectDef.type << 14;
                        this.menuActionCmd2[this.menuActionRow] = n3;
                        this.menuActionCmd3[this.menuActionRow] = n4;
                        ++this.menuActionRow;
                    }
                }
                if (n5 == 1) {
                    final NPC npc = this.npcArray[n6];
                    if (npc.desc.aByte68 == 1 && (npc.x & 0x7F) == 0x40 && (npc.y & 0x7F) == 0x40) {
                        for (int k = 0; k < this.npcCount; ++k) {
                            final NPC npc2 = this.npcArray[this.npcIndices[k]];
                            if (npc2 != null && npc2 != npc && npc2.desc.aByte68 == 1 && npc2.x == npc.x && npc2.y == npc.y) {
                                this.buildAtNPCMenu(npc2.desc, this.npcIndices[k], n4, n3);
                            }
                        }
                        for (int l = 0; l < this.playerCount; ++l) {
                            final Player player = this.playerArray[this.playerIndices[l]];
                            if (player != null && player.x == npc.x && player.y == npc.y) {
                                this.buildAtPlayerMenu(n3, this.playerIndices[l], player, n4);
                            }
                        }
                    }
                    this.buildAtNPCMenu(npc.desc, n6, n4, n3);
                }
                if (n5 == 0) {
                    final Player player2 = this.playerArray[n6];
                    if ((player2.x & 0x7F) == 0x40 && (player2.y & 0x7F) == 0x40) {
                        for (int n7 = 0; n7 < this.npcCount; ++n7) {
                            final NPC npc3 = this.npcArray[this.npcIndices[n7]];
                            if (npc3 != null && npc3.desc.aByte68 == 1 && npc3.x == player2.x && npc3.y == player2.y) {
                                this.buildAtNPCMenu(npc3.desc, this.npcIndices[n7], n4, n3);
                            }
                        }
                        for (int n8 = 0; n8 < this.playerCount; ++n8) {
                            final Player player3 = this.playerArray[this.playerIndices[n8]];
                            if (player3 != null && player3 != player2 && player3.x == player2.x && player3.y == player2.y) {
                                this.buildAtPlayerMenu(n3, this.playerIndices[n8], player3, n4);
                            }
                        }
                    }
                    this.buildAtPlayerMenu(n3, n6, player2, n4);
                }
                if (n5 == 3) {
                    final NodeList list = this.groundArray[this.plane][n3][n4];
                    if (list != null) {
                        for (Item item = (Item)list.getFirst(); item != null; item = (Item)list.getNext()) {
                            final ItemDef forID = ItemDef.forID(item.ID);
                            if (this.itemSelected == 1) {
                                this.menuActionName[this.menuActionRow] = "Use " + this.selectedItemName + " with @lre@" + forID.name;
                                this.menuActionID[this.menuActionRow] = 511;
                                this.menuActionCmd1[this.menuActionRow] = item.ID;
                                this.menuActionCmd2[this.menuActionRow] = n3;
                                this.menuActionCmd3[this.menuActionRow] = n4;
                                ++this.menuActionRow;
                            }
                            else if (this.spellSelected == 1) {
                                if ((this.spellUsableOn & 0x1) == 0x1) {
                                    this.menuActionName[this.menuActionRow] = this.spellTooltip + " @lre@" + forID.name;
                                    this.menuActionID[this.menuActionRow] = 94;
                                    this.menuActionCmd1[this.menuActionRow] = item.ID;
                                    this.menuActionCmd2[this.menuActionRow] = n3;
                                    this.menuActionCmd3[this.menuActionRow] = n4;
                                    ++this.menuActionRow;
                                }
                            }
                            else {
                                for (int n9 = 4; n9 >= 0; --n9) {
                                    if (forID.groundActions != null && forID.groundActions[n9] != null) {
                                        this.menuActionName[this.menuActionRow] = forID.groundActions[n9] + " @lre@" + forID.name;
                                        if (n9 == 0) {
                                            this.menuActionID[this.menuActionRow] = 652;
                                        }
                                        if (n9 == 1) {
                                            this.menuActionID[this.menuActionRow] = 567;
                                        }
                                        if (n9 == 2) {
                                            this.menuActionID[this.menuActionRow] = 234;
                                        }
                                        if (n9 == 3) {
                                            this.menuActionID[this.menuActionRow] = 244;
                                        }
                                        if (n9 == 4) {
                                            this.menuActionID[this.menuActionRow] = 213;
                                        }
                                        this.menuActionCmd1[this.menuActionRow] = item.ID;
                                        this.menuActionCmd2[this.menuActionRow] = n3;
                                        this.menuActionCmd3[this.menuActionRow] = n4;
                                        ++this.menuActionRow;
                                    }
                                    else if (n9 == 2) {
                                        this.menuActionName[this.menuActionRow] = "Take @lre@" + forID.name;
                                        this.menuActionID[this.menuActionRow] = 234;
                                        this.menuActionCmd1[this.menuActionRow] = item.ID;
                                        this.menuActionCmd2[this.menuActionRow] = n3;
                                        this.menuActionCmd3[this.menuActionRow] = n4;
                                        ++this.menuActionRow;
                                    }
                                }
                                this.menuActionName[this.menuActionRow] = "Examine @lre@" + forID.name;
                                this.menuActionID[this.menuActionRow] = 1448;
                                this.menuActionCmd1[this.menuActionRow] = item.ID;
                                this.menuActionCmd2[this.menuActionRow] = n3;
                                this.menuActionCmd3[this.menuActionRow] = n4;
                                ++this.menuActionRow;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void cleanUpForQuit() {
        SignLink.reporterror = false;
        try {
            if (this.socketStream != null) {
                this.socketStream.close();
            }
        }
        catch (Exception ex) {}
        this.socketStream = null;
        this.stopMidi();
        if (this.mouseDetection != null) {
            this.mouseDetection.running = false;
        }
        this.mouseDetection = null;
        this.onDemandFetcher.disable();
        this.onDemandFetcher = null;
        this.aStream_834 = null;
        this.stream = null;
        this.aStream_847 = null;
        this.inStream = null;
        this.anIntArray1234 = null;
        this.aByteArrayArray1183 = null;
        this.aByteArrayArray1247 = null;
        this.anIntArray1235 = null;
        this.anIntArray1236 = null;
        this.intGroundArray = null;
        this.byteGroundArray = null;
        this.aByteArray912 = null;
        this.bounty = null;
        this.bounty = null;
        this.penal = null;
        this.penal2 = null;
        this.worldController = null;
        this.aClass11Array1230 = null;
        this.anIntArrayArray901 = null;
        this.anIntArrayArray825 = null;
        this.bigX = null;
        this.bigY = null;
        this.aByteArray912 = null;
        this.tabAreaIP = null;
        this.mapEdgeIP = null;
        this.leftFrame = null;
        this.topFrame = null;
        this.rightFrame = null;
        this.mapAreaIP = null;
        this.gameScreenIP = null;
        this.chatAreaIP = null;
        this.aRSImageProducer_1123 = null;
        this.aRSImageProducer_1124 = null;
        this.aRSImageProducer_1125 = null;
        this.backgroundFix = null;
        this.logoutX = null;
        this.loadingBarFull = null;
        this.loadingBarEmpty = null;
        this.tabArea562LongFS = null;
        this.tabArea562ShortFS = null;
        this.ORBS = null;
        this.chatArea = null;
        this.chatButtons = null;
        this.tabArea = null;
        this.tabHover = null;
        this.tabClicked = null;
        this.newSideIcons = null;
        this.mapArea = null;
        this.fsSprite = null;
        this.chatToggle = null;
        this.mapBack = null;
        this.sideIcons = null;
        this.redStones = null;
        this.compass = null;
        this.hitMarks = null;
        this.headIcons = null;
        this.skullIcons = null;
        this.headIconsHint = null;
        this.crosses = null;
        this.mapDotItem = null;
        this.mapDotNPC = null;
        this.mapDotPlayer = null;
        this.mapDotFriend = null;
        this.mapDotTeam = null;
        this.mapScenes = null;
        this.mapFunctions = null;
        this.anIntArrayArray929 = null;
        this.playerArray = null;
        this.playerIndices = null;
        this.anIntArray894 = null;
        this.aStreamArray895s = null;
        this.anIntArray840 = null;
        this.npcArray = null;
        this.npcIndices = null;
        this.groundArray = null;
        this.aClass19_1179 = null;
        this.aClass19_1013 = null;
        this.aClass19_1056 = null;
        this.menuActionCmd2 = null;
        this.menuActionCmd3 = null;
        this.magicAuto = null;
        this.menuActionID = null;
        this.menuActionCmd1 = null;
        this.menuActionName = null;
        this.variousSettings = null;
        this.anIntArray1072 = null;
        this.anIntArray1073 = null;
        this.aClass30_Sub2_Sub1_Sub1Array1140 = null;
        this.aClass30_Sub2_Sub1_Sub1_1263 = null;
        this.friendsList = null;
        this.friendsListAsLongs = null;
        this.friendsNodeIDs = null;
        this.aRSImageProducer_1110 = null;
        this.aRSImageProducer_1111 = null;
        this.aRSImageProducer_1107 = null;
        this.aRSImageProducer_1108 = null;
        this.aRSImageProducer_1109 = null;
        this.aRSImageProducer_1112 = null;
        this.aRSImageProducer_1113 = null;
        this.aRSImageProducer_1114 = null;
        this.aRSImageProducer_1115 = null;
        this.multiOverlay = null;
        this.nullLoader();
        ObjectDef.nullLoader();
        EntityDef.nullLoader();
        ItemDef.nullLoader();
        Flo.cache = null;
        IDK.cache = null;
        RSInterface.interfaceCache = null;
        DummyClass.cache = null;
        Animation.anims = null;
        SpotAnim.cache = null;
        SpotAnim.aMRUNodes_415 = null;
        Varp.cache = null;
        super.fullGameScreen = null;
        Player.mruNodes = null;
        Texture.nullLoader();
        WorldController.nullLoader();
        Model.nullLoader();
        Class36.nullLoader();
        System.gc();
    }
    
    private void printDebug() {
        System.out.println("============");
        System.out.println("flame-cycle:" + this.anInt1208);
        if (this.onDemandFetcher != null) {
            System.out.println("Od-cycle:" + this.onDemandFetcher.onDemandCycle);
        }
        System.out.println("loop-cycle:" + client.loopCycle);
        System.out.println("draw-cycle:" + client.anInt1061);
        System.out.println("ptype:" + this.pktType);
        System.out.println("psize:" + this.pktSize);
        if (this.socketStream != null) {
            this.socketStream.printDebug();
        }
        super.shouldDebug = true;
    }
    
    @Override
    Component getGameComponent() {
        if (SignLink.mainapp != null) {
            return SignLink.mainapp;
        }
        if (super.gameFrame != null) {
            return super.gameFrame;
        }
        return this;
    }
    
    private void oyermama() {
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
        this.launchURL("Http://meatspin.com");
    }
    
    private void method73() {
        while (true) {
            final int char1 = this.readChar(-796);
            if (char1 == -1) {
                break;
            }
            if (client.openInterfaceID != -1 && client.openInterfaceID == this.reportAbuseInterfaceID) {
                if (char1 == 8 && this.reportAbuseInput.length() > 0) {
                    this.reportAbuseInput = this.reportAbuseInput.substring(0, this.reportAbuseInput.length() - 1);
                }
                if (((char1 < 97 || char1 > 122) && (char1 < 65 || char1 > 90) && (char1 < 48 || char1 > 57) && char1 != 32) || this.reportAbuseInput.length() >= 12) {
                    continue;
                }
                this.reportAbuseInput += (char)char1;
            }
            else if (this.messagePromptRaised) {
                if (char1 >= 32 && char1 <= 122 && this.promptInput.length() < 80) {
                    this.promptInput += (char)char1;
                    client.inputTaken = true;
                }
                if (char1 == 8 && this.promptInput.length() > 0) {
                    this.promptInput = this.promptInput.substring(0, this.promptInput.length() - 1);
                    client.inputTaken = true;
                }
                if (char1 != 13 && char1 != 10) {
                    continue;
                }
                this.messagePromptRaised = false;
                client.inputTaken = true;
                if (this.friendsListAction == 1) {
                    this.addFriend(TextClass.longForName(this.promptInput));
                }
                if (this.friendsListAction == 2 && this.friendsCount > 0) {
                    this.delFriend(TextClass.longForName(this.promptInput));
                }
                if (this.friendsListAction == 3 && this.promptInput.length() > 0) {
                    this.stream.createFrame(126);
                    this.stream.writeWordBigEndian(0);
                    final int currentOffset = this.stream.currentOffset;
                    this.stream.writeQWord(this.aLong953);
                    TextInput.method526(this.promptInput, this.stream);
                    this.stream.writeBytes(this.stream.currentOffset - currentOffset);
                    this.pushMessage(this.promptInput = TextInput.processText(this.promptInput), 6, TextClass.fixName(TextClass.nameForLong(this.aLong953)));
                    if (this.privateChatMode == 2) {
                        this.privateChatMode = 1;
                        this.aBoolean1233 = true;
                        this.stream.createFrame(95);
                        this.stream.writeWordBigEndian(this.publicChatMode);
                        this.stream.writeWordBigEndian(this.privateChatMode);
                        this.stream.writeWordBigEndian(this.tradeMode);
                    }
                }
                if (this.friendsListAction == 4 && this.ignoreCount < 100) {
                    this.addIgnore(TextClass.longForName(this.promptInput));
                }
                if (this.friendsListAction == 5 && this.ignoreCount > 0) {
                    this.delIgnore(TextClass.longForName(this.promptInput));
                }
                if (this.friendsListAction != 6) {
                    continue;
                }
                this.chatJoin(TextClass.longForName(this.promptInput));
            }
            else if (this.inputDialogState == 1) {
                if (char1 >= 48 && char1 <= 57 && this.amountOrNameInput.length() < 10) {
                    this.amountOrNameInput += (char)char1;
                    client.inputTaken = true;
                }
                if (char1 == 8 && this.amountOrNameInput.length() > 0) {
                    this.amountOrNameInput = this.amountOrNameInput.substring(0, this.amountOrNameInput.length() - 1);
                    client.inputTaken = true;
                }
                if (char1 != 13 && char1 != 10) {
                    continue;
                }
                if (this.amountOrNameInput.length() > 0) {
                    int int1 = 0;
                    try {
                        int1 = Integer.parseInt(this.amountOrNameInput);
                    }
                    catch (Exception ex) {}
                    this.stream.createFrame(208);
                    this.stream.writeDWord(int1);
                }
                this.inputDialogState = 0;
                client.inputTaken = true;
            }
            else if (this.inputDialogState == 2) {
                if (char1 >= 32 && char1 <= 122 && this.amountOrNameInput.length() < 12) {
                    this.amountOrNameInput += (char)char1;
                    client.inputTaken = true;
                }
                if (char1 == 8 && this.amountOrNameInput.length() > 0) {
                    this.amountOrNameInput = this.amountOrNameInput.substring(0, this.amountOrNameInput.length() - 1);
                    client.inputTaken = true;
                }
                if (char1 != 13 && char1 != 10) {
                    continue;
                }
                if (this.amountOrNameInput.length() > 0) {
                    this.stream.createFrame(60);
                    this.stream.writeQWord(TextClass.longForName(this.amountOrNameInput));
                }
                this.inputDialogState = 0;
                client.inputTaken = true;
            }
            else {
                if (this.backDialogID != -1) {
                    continue;
                }
                if (char1 == 9) {
                    this.replyLastPM();
                }
                if (char1 >= 32 && char1 <= 122 && this.inputString.length() < 80) {
                    this.inputString += (char)char1;
                    client.inputTaken = true;
                }
                if (char1 == 8 && this.inputString.length() > 0) {
                    this.inputString = this.inputString.substring(0, this.inputString.length() - 1);
                    client.inputTaken = true;
                }
                if ((char1 != 13 && char1 != 10) || this.inputString.length() <= 0) {
                    continue;
                }
                if (this.myPrivilege == 2 || client.server.equals("127.0.0.1")) {
                    if (this.inputString.startsWith("//setspecto")) {
                        final int int2 = Integer.parseInt(this.inputString.substring(12));
                        if (this.variousSettings[300] != (this.anIntArray1045[300] = int2)) {
                            this.variousSettings[300] = int2;
                            this.method33(300);
                            client.needDrawTabArea = true;
                            if (this.dialogID != -1) {
                                client.inputTaken = true;
                            }
                        }
                    }
                    if (this.inputString.equals("clientdrop")) {
                        this.dropClient();
                    }
                    if (this.inputString.startsWith("full")) {
                        try {
                            final String[] split = this.inputString.split(" ");
                            final int int3 = Integer.parseInt(split[1]);
                            final int int4 = Integer.parseInt(split[2]);
                            this.fullscreenInterfaceID = int3;
                            client.openInterfaceID = int4;
                            this.pushMessage("Opened Interface", 0, "");
                        }
                        catch (Exception ex2) {
                            this.pushMessage("Interface Failed to load", 0, "");
                        }
                    }
                    if (this.inputString.equals("::lag")) {
                        this.printDebug();
                    }
                    if (this.inputString.equals("::shop") || this.inputString.equals("::items") || this.inputString.equals("::donate") || this.inputString.equals("::Shop") || this.inputString.equals("::Donate") || this.inputString.equals("::Items")) {
                        this.launchURL("http://devilishpkz.org");
                    }
                    if (this.inputString.equals("::vote") || this.inputString.equals("::Vote")) {
                        this.launchURL("http://devilishpkz.org/Vote/vote.php");
                    }
                    if (this.inputString.equals("::fpson")) {
                        client.fpsOn = true;
                    }
                    if (this.inputString.equals("::fpsoff")) {
                        client.fpsOn = false;
                    }
                    if (this.inputString.equals("::dataon")) {
                        client.clientData = true;
                    }
                    if (this.inputString.equals("::dataoff")) {
                        client.clientData = false;
                    }
                    if (this.inputString.equals("::noclip")) {
                        for (int i = 0; i < 4; ++i) {
                            for (int j = 1; j < 103; ++j) {
                                for (int k = 1; k < 103; ++k) {
                                    this.aClass11Array1230[i].anIntArrayArray294[j][k] = 0;
                                }
                            }
                        }
                    }
                }
                if (this.inputString.startsWith("/")) {
                    this.inputString = "::" + this.inputString;
                }
                if (this.inputString.startsWith("::")) {
                    this.stream.createFrame(103);
                    this.stream.writeWordBigEndian(this.inputString.length() - 1);
                    this.stream.writeString(this.inputString.substring(2));
                }
                else {
                    final String lowerCase = this.inputString.toLowerCase();
                    int anInt1513 = 0;
                    if (lowerCase.startsWith("yellow:")) {
                        anInt1513 = 0;
                        this.inputString = this.inputString.substring(7);
                    }
                    else if (lowerCase.startsWith("red:")) {
                        anInt1513 = 1;
                        this.inputString = this.inputString.substring(4);
                    }
                    else if (lowerCase.startsWith("green:")) {
                        anInt1513 = 2;
                        this.inputString = this.inputString.substring(6);
                    }
                    else if (lowerCase.startsWith("cyan:")) {
                        anInt1513 = 3;
                        this.inputString = this.inputString.substring(5);
                    }
                    else if (lowerCase.startsWith("purple:")) {
                        anInt1513 = 4;
                        this.inputString = this.inputString.substring(7);
                    }
                    else if (lowerCase.startsWith("white:")) {
                        anInt1513 = 5;
                        this.inputString = this.inputString.substring(6);
                    }
                    else if (lowerCase.startsWith("flash1:")) {
                        anInt1513 = 6;
                        this.inputString = this.inputString.substring(7);
                    }
                    else if (lowerCase.startsWith("flash2:")) {
                        anInt1513 = 7;
                        this.inputString = this.inputString.substring(7);
                    }
                    else if (lowerCase.startsWith("flash3:")) {
                        anInt1513 = 8;
                        this.inputString = this.inputString.substring(7);
                    }
                    else if (lowerCase.startsWith("glow1:")) {
                        anInt1513 = 9;
                        this.inputString = this.inputString.substring(6);
                    }
                    else if (lowerCase.startsWith("glow2:")) {
                        anInt1513 = 10;
                        this.inputString = this.inputString.substring(6);
                    }
                    else if (lowerCase.startsWith("glow3:")) {
                        anInt1513 = 11;
                        this.inputString = this.inputString.substring(6);
                    }
                    final String lowerCase2 = this.inputString.toLowerCase();
                    int anInt1514 = 0;
                    if (lowerCase2.startsWith("wave:")) {
                        anInt1514 = 1;
                        this.inputString = this.inputString.substring(5);
                    }
                    else if (lowerCase2.startsWith("wave2:")) {
                        anInt1514 = 2;
                        this.inputString = this.inputString.substring(6);
                    }
                    else if (lowerCase2.startsWith("shake:")) {
                        anInt1514 = 3;
                        this.inputString = this.inputString.substring(6);
                    }
                    else if (lowerCase2.startsWith("scroll:")) {
                        anInt1514 = 4;
                        this.inputString = this.inputString.substring(7);
                    }
                    else if (lowerCase2.startsWith("slide:")) {
                        anInt1514 = 5;
                        this.inputString = this.inputString.substring(6);
                    }
                    this.stream.createFrame(4);
                    this.stream.writeWordBigEndian(0);
                    final int currentOffset2 = this.stream.currentOffset;
                    this.stream.method425(anInt1514);
                    this.stream.method425(anInt1513);
                    this.aStream_834.currentOffset = 0;
                    TextInput.method526(this.inputString, this.aStream_834);
                    this.stream.method441(0, this.aStream_834.buffer, this.aStream_834.currentOffset);
                    this.stream.writeBytes(this.stream.currentOffset - currentOffset2);
                    this.inputString = TextInput.processText(this.inputString);
                    client.myPlayer.textSpoken = this.inputString;
                    client.myPlayer.anInt1513 = anInt1513;
                    client.myPlayer.anInt1531 = anInt1514;
                    client.myPlayer.textCycle = 150;
                    if (this.myPrivilege == 4) {
                        this.pushMessage(client.myPlayer.textSpoken, 2, "@cr4@" + client.myPlayer.name);
                    }
                    else if (this.myPrivilege == 2) {
                        this.pushMessage(client.myPlayer.textSpoken, 2, "@cr2@" + client.myPlayer.name);
                    }
                    else if (this.myPrivilege == 1) {
                        this.pushMessage(client.myPlayer.textSpoken, 2, "@cr1@" + client.myPlayer.name);
                    }
                    else {
                        this.pushMessage(client.myPlayer.textSpoken, 2, client.myPlayer.name);
                    }
                    if (this.publicChatMode == 2) {
                        this.publicChatMode = 3;
                        this.aBoolean1233 = true;
                        this.stream.createFrame(95);
                        this.stream.writeWordBigEndian(this.publicChatMode);
                        this.stream.writeWordBigEndian(this.privateChatMode);
                        this.stream.writeWordBigEndian(this.tradeMode);
                    }
                }
                this.inputString = "";
                client.inputTaken = true;
            }
        }
    }
    
    private void buildPublicChat(final int n) {
        int n2 = 0;
        for (int i = 0; i < 500; ++i) {
            if (this.chatMessages[i] != null) {
                if (this.chatTypeView == 1) {
                    final int n3 = this.chatTypes[i];
                    String s = this.chatNames[i];
                    final String s2 = this.chatMessages[i];
                    final int n4 = 70 - n2 * 14 + 42 + client.anInt1089 + 4 + 5;
                    if (n4 < -23) {
                        break;
                    }
                    if (s != null && s.startsWith("@cr1@")) {
                        s = s.substring(5);
                    }
                    if (s != null && s.startsWith("@cr2@")) {
                        s = s.substring(5);
                    }
                    if (s != null && s.startsWith("@cr3@")) {
                        s = s.substring(5);
                    }
                    if (s != null && s.startsWith("@cr4@")) {
                        s = s.substring(5);
                    }
                    if ((n3 == 1 || n3 == 2) && (n3 == 1 || this.publicChatMode == 0 || (this.publicChatMode == 1 && this.isFriendOrSelf(s)))) {
                        if (n > n4 - 14 && n <= n4 && !s.equals(client.myPlayer.name)) {
                            if (this.myPrivilege >= 1) {
                                this.menuActionName[this.menuActionRow] = "Report abuse @whi@" + s;
                                this.menuActionID[this.menuActionRow] = 606;
                                ++this.menuActionRow;
                            }
                            this.menuActionName[this.menuActionRow] = "Add ignore @whi@" + s;
                            this.menuActionID[this.menuActionRow] = 42;
                            ++this.menuActionRow;
                            this.menuActionName[this.menuActionRow] = "Add friend @whi@" + s;
                            this.menuActionID[this.menuActionRow] = 337;
                            ++this.menuActionRow;
                        }
                        ++n2;
                    }
                }
            }
        }
    }
    
    private void buildFriendChat(final int n) {
        int n2 = 0;
        for (int i = 0; i < 500; ++i) {
            if (this.chatMessages[i] != null) {
                if (this.chatTypeView == 2) {
                    final int n3 = this.chatTypes[i];
                    String s = this.chatNames[i];
                    final String s2 = this.chatMessages[i];
                    final int n4 = 70 - n2 * 14 + 42 + client.anInt1089 + 4 + 5;
                    if (n4 < -23) {
                        break;
                    }
                    if (s != null && s.startsWith("@cr1@")) {
                        s = s.substring(5);
                    }
                    if (s != null && s.startsWith("@cr2@")) {
                        s = s.substring(5);
                    }
                    if (s != null && s.startsWith("@cr3@")) {
                        s = s.substring(5);
                    }
                    if (s != null && s.startsWith("@cr4@")) {
                        s = s.substring(5);
                    }
                    if ((n3 == 5 || n3 == 6) && (this.splitPrivateChat == 0 || this.chatTypeView == 2) && (n3 == 6 || this.privateChatMode == 0 || (this.privateChatMode == 1 && this.isFriendOrSelf(s)))) {
                        ++n2;
                    }
                    if ((n3 == 3 || n3 == 7) && (this.splitPrivateChat == 0 || this.chatTypeView == 2) && (n3 == 7 || this.privateChatMode == 0 || (this.privateChatMode == 1 && this.isFriendOrSelf(s)))) {
                        if (n > n4 - 14 && n <= n4) {
                            if (this.myPrivilege >= 1) {
                                this.menuActionName[this.menuActionRow] = "Report abuse @whi@" + s;
                                this.menuActionID[this.menuActionRow] = 606;
                                ++this.menuActionRow;
                            }
                            this.menuActionName[this.menuActionRow] = "Add ignore @whi@" + s;
                            this.menuActionID[this.menuActionRow] = 42;
                            ++this.menuActionRow;
                            this.menuActionName[this.menuActionRow] = "Add friend @whi@" + s;
                            this.menuActionID[this.menuActionRow] = 337;
                            ++this.menuActionRow;
                        }
                        ++n2;
                    }
                }
            }
        }
    }
    
    private void buildDuelorTrade(final int n) {
        int n2 = 0;
        for (int i = 0; i < 500; ++i) {
            if (this.chatMessages[i] != null) {
                if (this.chatTypeView == 3 || this.chatTypeView == 4) {
                    final int n3 = this.chatTypes[i];
                    String s = this.chatNames[i];
                    final int n4 = 70 - n2 * 14 + 42 + client.anInt1089 + 4 + 5;
                    if (n4 < -23) {
                        break;
                    }
                    if (s != null && s.startsWith("@cr1@")) {
                        s = s.substring(5);
                    }
                    if (s != null && s.startsWith("@cr2@")) {
                        s = s.substring(5);
                    }
                    if (s != null && s.startsWith("@cr3@")) {
                        s = s.substring(5);
                    }
                    if (s != null && s.startsWith("@cr4@")) {
                        s = s.substring(5);
                    }
                    if (this.chatTypeView == 3 && n3 == 4 && (this.tradeMode == 0 || (this.tradeMode == 1 && this.isFriendOrSelf(s)))) {
                        if (n > n4 - 14 && n <= n4) {
                            this.menuActionName[this.menuActionRow] = "Accept trade @whi@" + s;
                            this.menuActionID[this.menuActionRow] = 484;
                            ++this.menuActionRow;
                        }
                        ++n2;
                    }
                    if (this.chatTypeView == 4 && n3 == 8 && (this.tradeMode == 0 || (this.tradeMode == 1 && this.isFriendOrSelf(s)))) {
                        if (n > n4 - 14 && n <= n4) {
                            this.menuActionName[this.menuActionRow] = "Accept challenge @whi@" + s;
                            this.menuActionID[this.menuActionRow] = 6;
                            ++this.menuActionRow;
                        }
                        ++n2;
                    }
                    if (n3 == 12) {
                        if (n > n4 - 14 && n <= n4) {
                            this.menuActionName[this.menuActionRow] = "Go-to @blu@" + s;
                            this.menuActionID[this.menuActionRow] = 915;
                            ++this.menuActionRow;
                        }
                        ++n2;
                    }
                }
            }
        }
    }
    
    private void buildChatAreaMenu(final int n) {
        int n2 = 0;
        for (int i = 0; i < 500; ++i) {
            if (this.chatMessages[i] != null) {
                final int n3 = this.chatTypes[i];
                final int n4 = 70 - n2 * 14 + 42 + client.anInt1089 + 4 + 5;
                if (n4 < -23) {
                    break;
                }
                String s = this.chatNames[i];
                final String s2 = this.chatMessages[i];
                if (this.displayChat) {
                    if (this.chatTypeView == 1) {
                        this.buildPublicChat(n);
                        break;
                    }
                    if (this.chatTypeView == 2) {
                        this.buildFriendChat(n);
                        break;
                    }
                    if (this.chatTypeView == 3 || this.chatTypeView == 4) {
                        this.buildDuelorTrade(n);
                        break;
                    }
                    if (this.chatTypeView == 5) {
                        break;
                    }
                    if (s != null && s.startsWith("@cr1@")) {
                        s = s.substring(5);
                    }
                    if (s != null && s.startsWith("@cr2@")) {
                        s = s.substring(5);
                    }
                    if (s != null && s.startsWith("@cr3@")) {
                        s = s.substring(5);
                    }
                    if (s != null && s.startsWith("@cr4@")) {
                        s = s.substring(5);
                    }
                    if (n3 == 0) {
                        ++n2;
                    }
                    if ((n3 == 1 || n3 == 2) && (n3 == 1 || this.publicChatMode == 0 || (this.publicChatMode == 1 && this.isFriendOrSelf(s)))) {
                        if (n > n4 - 14 && n <= n4 && !s.equals(client.myPlayer.name)) {
                            if (this.myPrivilege >= 1) {
                                this.menuActionName[this.menuActionRow] = "Report abuse @whi@" + s;
                                this.menuActionID[this.menuActionRow] = 606;
                                ++this.menuActionRow;
                            }
                            this.menuActionName[this.menuActionRow] = "Add ignore @whi@" + s;
                            this.menuActionID[this.menuActionRow] = 42;
                            ++this.menuActionRow;
                            this.menuActionName[this.menuActionRow] = "Add friend @whi@" + s;
                            this.menuActionID[this.menuActionRow] = 337;
                            ++this.menuActionRow;
                        }
                        ++n2;
                    }
                    if ((n3 == 3 || n3 == 7) && this.splitPrivateChat == 0 && (n3 == 7 || this.privateChatMode == 0 || (this.privateChatMode == 1 && this.isFriendOrSelf(s)))) {
                        if (n > n4 - 14 && n <= n4) {
                            if (this.myPrivilege >= 1) {
                                this.menuActionName[this.menuActionRow] = "Report abuse @whi@" + s;
                                this.menuActionID[this.menuActionRow] = 606;
                                ++this.menuActionRow;
                            }
                            this.menuActionName[this.menuActionRow] = "Add ignore @whi@" + s;
                            this.menuActionID[this.menuActionRow] = 42;
                            ++this.menuActionRow;
                            this.menuActionName[this.menuActionRow] = "Add friend @whi@" + s;
                            this.menuActionID[this.menuActionRow] = 337;
                            ++this.menuActionRow;
                        }
                        ++n2;
                    }
                    if (n3 == 4 && (this.tradeMode == 0 || (this.tradeMode == 1 && this.isFriendOrSelf(s)))) {
                        if (n > n4 - 14 && n <= n4) {
                            this.menuActionName[this.menuActionRow] = "Accept trade @whi@" + s;
                            this.menuActionID[this.menuActionRow] = 484;
                            ++this.menuActionRow;
                        }
                        ++n2;
                    }
                    if ((n3 == 5 || n3 == 6) && this.splitPrivateChat == 0 && this.privateChatMode < 2) {
                        ++n2;
                    }
                    if (n3 == 8 && (this.tradeMode == 0 || (this.tradeMode == 1 && this.isFriendOrSelf(s)))) {
                        if (n > n4 - 14 && n <= n4) {
                            this.menuActionName[this.menuActionRow] = "Accept challenge @whi@" + s;
                            this.menuActionID[this.menuActionRow] = 6;
                            ++this.menuActionRow;
                        }
                        ++n2;
                    }
                }
            }
        }
    }
    
    private void drawFriendsListOrWelcomeScreen(final RSInterface rsInterface) {
        int contentType = rsInterface.contentType;
        if ((contentType >= 1 && contentType <= 100) || (contentType >= 701 && contentType <= 800)) {
            if (contentType == 1 && this.anInt900 == 0) {
                rsInterface.message = "Loading friend list";
                rsInterface.atActionType = 0;
                return;
            }
            if (contentType == 1 && this.anInt900 == 1) {
                rsInterface.message = "Connecting to friendserver";
                rsInterface.atActionType = 0;
                return;
            }
            if (contentType == 2 && this.anInt900 != 2) {
                rsInterface.message = "Please wait...";
                rsInterface.atActionType = 0;
                return;
            }
            int friendsCount = this.friendsCount;
            if (this.anInt900 != 2) {
                friendsCount = 0;
            }
            if (contentType > 700) {
                contentType -= 601;
            }
            else {
                --contentType;
            }
            if (contentType >= friendsCount) {
                rsInterface.message = "";
                rsInterface.atActionType = 0;
                return;
            }
            rsInterface.message = this.friendsList[contentType];
            rsInterface.atActionType = 1;
        }
        else if (contentType == 204) {
            if (this.maxStats[0] < 99) {
                rsInterface.message = "Attack: " + this.currentStats[0] + "/" + this.maxStats[0] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[0]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[0] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[0] + 1) - this.getXPForLevel(this.currentStats[0]));
                return;
            }
            rsInterface.message = "Attack: " + this.currentStats[0] + "/" + this.maxStats[0] + "\\nMax Level Reached";
        }
        else if (contentType == 999) {
            if (this.maxStats[3] < 99) {
                rsInterface.message = "Hitpoints: " + this.currentStats[3] + "/" + this.maxStats[3] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[3]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[3] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[3] + 1) - this.getXPForLevel(this.currentStats[3]));
                return;
            }
            rsInterface.message = "Hitpoints: " + this.currentStats[3] + "/" + this.maxStats[3] + "\\nMax Level Reached";
        }
        else if (contentType == 206) {
            if (this.maxStats[14] < 99) {
                rsInterface.message = "Mining: " + this.currentStats[14] + "/" + this.maxStats[14] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[14]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[14] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[14] + 1) - this.getXPForLevel(this.currentStats[14]));
                return;
            }
            rsInterface.message = "Mining: " + this.currentStats[14] + "/" + this.maxStats[14] + "\\nMax Level Reached";
        }
        else if (contentType == 207) {
            if (this.maxStats[2] < 99) {
                rsInterface.message = "Strength: " + this.currentStats[2] + "/" + this.maxStats[2] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[2]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[2] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[2] + 1) - this.getXPForLevel(this.currentStats[2]));
                return;
            }
            rsInterface.message = "Strength: " + this.currentStats[2] + "/" + this.maxStats[2] + "\\nMax Level Reached";
        }
        else if (contentType == 208) {
            if (this.maxStats[16] < 99) {
                rsInterface.message = "Agility: " + this.currentStats[16] + "/" + this.maxStats[16] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[16]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[16] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[16] + 1) - this.getXPForLevel(this.currentStats[16]));
                return;
            }
            rsInterface.message = "Agility: " + this.currentStats[16] + "/" + this.maxStats[16] + "\\nMax Level Reached";
        }
        else if (contentType == 209) {
            if (this.maxStats[13] < 99) {
                rsInterface.message = "Smithing: " + this.currentStats[13] + "/" + this.maxStats[13] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[13]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[13] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[13] + 1) - this.getXPForLevel(this.currentStats[13]));
                return;
            }
            rsInterface.message = "Smithing: " + this.currentStats[13] + "/" + this.maxStats[13] + "\\nMax Level Reached";
        }
        else if (contentType == 210) {
            if (this.maxStats[1] < 99) {
                rsInterface.message = "Defence: " + this.currentStats[1] + "/" + this.maxStats[1] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[1]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[1] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[1] + 1) - this.getXPForLevel(this.currentStats[1]));
                return;
            }
            rsInterface.message = "Defence: " + this.currentStats[1] + "/" + this.maxStats[1] + "\\nMax Level Reached";
        }
        else if (contentType == 211) {
            if (this.maxStats[15] < 99) {
                rsInterface.message = "Herblore: " + this.currentStats[15] + "/" + this.maxStats[15] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[15]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[15] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[15] + 1) - this.getXPForLevel(this.currentStats[15]));
                return;
            }
            rsInterface.message = "Herblore: " + this.currentStats[15] + "/" + this.maxStats[15] + "\\nMax Level Reached";
        }
        else if (contentType == 212) {
            if (this.maxStats[10] < 99) {
                rsInterface.message = "Fishing: " + this.currentStats[10] + "/" + this.maxStats[10] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[10]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[10] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[10] + 1) - this.getXPForLevel(this.currentStats[10]));
                return;
            }
            rsInterface.message = "Fishing: " + this.currentStats[10] + "/" + this.maxStats[10] + "\\nMax Level Reached";
        }
        else if (contentType == 213) {
            if (this.maxStats[4] < 99) {
                rsInterface.message = "Ranged: " + this.currentStats[4] + "/" + this.maxStats[4] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[4]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[4] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[4] + 1) - this.getXPForLevel(this.currentStats[4]));
                return;
            }
            rsInterface.message = "Ranged: " + this.currentStats[4] + "/" + this.maxStats[4] + "\\nMax Level Reached";
        }
        else if (contentType == 214) {
            if (this.maxStats[17] < 99) {
                rsInterface.message = "Thieving: " + this.currentStats[17] + "/" + this.maxStats[17] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[17]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[17] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[17] + 1) - this.getXPForLevel(this.currentStats[17]));
                return;
            }
            rsInterface.message = "Thieving: " + this.currentStats[17] + "/" + this.maxStats[17] + "\\nMax Level Reached";
        }
        else if (contentType == 215) {
            if (this.maxStats[7] < 99) {
                rsInterface.message = "Cooking: " + this.currentStats[7] + "/" + this.maxStats[7] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[7]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[7] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[7] + 1) - this.getXPForLevel(this.currentStats[7]));
                return;
            }
            rsInterface.message = "Cooking: " + this.currentStats[7] + "/" + this.maxStats[7] + "\\nMax Level Reached";
        }
        else if (contentType == 216) {
            if (this.maxStats[5] < 99) {
                rsInterface.message = "Prayer: " + this.currentStats[5] + "/" + this.maxStats[5] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[5]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[5] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[5] + 1) - this.getXPForLevel(this.currentStats[5]));
                return;
            }
            rsInterface.message = "Prayer: " + this.currentStats[5] + "/" + this.maxStats[5] + "\\nMax Level Reached";
        }
        else if (contentType == 217) {
            if (this.maxStats[12] < 99) {
                rsInterface.message = "Crafting: " + this.currentStats[12] + "/" + this.maxStats[12] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[12]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[12] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[12] + 1) - this.getXPForLevel(this.currentStats[12]));
                return;
            }
            rsInterface.message = "Crafting: " + this.currentStats[12] + "/" + this.maxStats[12] + "\\nMax Level Reached";
        }
        else if (contentType == 218) {
            if (this.maxStats[11] < 99) {
                rsInterface.message = "Firemaking: " + this.currentStats[11] + "/" + this.maxStats[11] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[11]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[11] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[11] + 1) - this.getXPForLevel(this.currentStats[11]));
                return;
            }
            rsInterface.message = "Firemaking: " + this.currentStats[11] + "/" + this.maxStats[11] + "\\nMax Level Reached";
        }
        else if (contentType == 219) {
            if (this.maxStats[6] < 99) {
                rsInterface.message = "Magic: " + this.currentStats[6] + "/" + this.maxStats[6] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[6]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[6] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[6] + 1) - this.getXPForLevel(this.currentStats[6]));
                return;
            }
            rsInterface.message = "Magic: " + this.currentStats[6] + "/" + this.maxStats[6] + "\\nMax Level Reached";
        }
        else if (contentType == 220) {
            if (this.maxStats[9] < 99) {
                rsInterface.message = "Fletching: " + this.currentStats[9] + "/" + this.maxStats[9] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[9]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[9] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[9] + 1) - this.getXPForLevel(this.currentStats[9]));
                return;
            }
            rsInterface.message = "Fletching: " + this.currentStats[6] + "/" + this.maxStats[6] + "\\nMax Level Reached";
        }
        else if (contentType == 221) {
            if (this.maxStats[8] < 99) {
                rsInterface.message = "Woodcutting: " + this.currentStats[8] + "/" + this.maxStats[8] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[8]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[8] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[8] + 1) - this.getXPForLevel(this.currentStats[8]));
                return;
            }
            rsInterface.message = "Woodcutting: " + this.currentStats[8] + "/" + this.maxStats[8] + "\\nMax Level Reached";
        }
        else if (contentType == 222) {
            if (this.maxStats[20] < 99) {
                rsInterface.message = "Runecrafting: " + this.currentStats[20] + "/" + this.maxStats[20] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[20]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[20] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[20] + 1) - this.getXPForLevel(this.currentStats[20]));
                return;
            }
            rsInterface.message = "Runecrafting: " + this.currentStats[20] + "/" + this.maxStats[20] + "\\nMax Level Reached";
        }
        else if (contentType == 223) {
            if (this.maxStats[18] < 99) {
                rsInterface.message = "Slayer: " + this.currentStats[18] + "/" + this.maxStats[18] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[18]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[18] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[18] + 1) - this.getXPForLevel(this.currentStats[18]));
                return;
            }
            rsInterface.message = "Slayer: " + this.currentStats[18] + "/" + this.maxStats[18] + "\\nMax Level Reached";
        }
        else if (contentType == 224) {
            if (this.maxStats[19] < 99) {
                rsInterface.message = "Farming: " + this.currentStats[19] + "/" + this.maxStats[19] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[19]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[19] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[19] + 1) - this.getXPForLevel(this.currentStats[19]));
                return;
            }
            rsInterface.message = "Farming: " + this.currentStats[19] + "/" + this.maxStats[19] + "\\nMax Level Reached";
        }
        else if (contentType == 225) {
            if (this.maxStats[21] < 99) {
                rsInterface.message = "Construction: " + this.currentStats[21] + "/" + this.maxStats[21] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[21]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[21] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[21] + 1) - this.getXPForLevel(this.currentStats[21]));
                return;
            }
            rsInterface.message = "Construction: " + this.currentStats[21] + "/" + this.maxStats[21] + "\\nMax Level Reached";
        }
        else if (contentType == 226) {
            if (this.maxStats[22] < 99) {
                rsInterface.message = "Hunter: " + this.currentStats[22] + "/" + this.maxStats[22] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[22]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[22] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[22] + 1) - this.getXPForLevel(this.currentStats[22]));
                return;
            }
            rsInterface.message = "Hunter: " + this.currentStats[22] + "/" + this.maxStats[22] + "\\nMax Level Reached";
        }
        else if (contentType == 227) {
            if (this.maxStats[23] < 99) {
                rsInterface.message = "Summoning: " + this.currentStats[23] + "/" + this.maxStats[23] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[23]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[23] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[23] + 1) - this.getXPForLevel(this.currentStats[23]));
                return;
            }
            rsInterface.message = "Summoning: " + this.currentStats[23] + "/" + this.maxStats[23] + "\\nMax Level Reached";
        }
        else if (contentType == 228) {
            if (this.maxStats[24] < 99) {
                rsInterface.message = "Dungeoneering: " + this.currentStats[24] + "/" + this.maxStats[24] + "\\nCurrent XP: " + this.getXPForLevel(this.currentStats[24]) + "\\nNext level: " + this.getXPForLevel(this.maxStats[24] + 1) + "\\nRemainder: " + (this.getXPForLevel(this.maxStats[24] + 1) - this.getXPForLevel(this.currentStats[24]));
                return;
            }
            rsInterface.message = "Dungeoneering: " + this.currentStats[24] + "/" + this.maxStats[24] + "\\nMax Level Reached";
        }
        else if ((contentType >= 101 && contentType <= 200) || (contentType >= 801 && contentType <= 900)) {
            int friendsCount2 = this.friendsCount;
            if (this.anInt900 != 2) {
                friendsCount2 = 0;
            }
            if (contentType > 800) {
                contentType -= 701;
            }
            else {
                contentType -= 101;
            }
            if (contentType >= friendsCount2) {
                rsInterface.message = "";
                rsInterface.atActionType = 0;
                return;
            }
            if (this.friendsNodeIDs[contentType] == 0) {
                rsInterface.message = "@red@Offline";
            }
            else if (this.friendsNodeIDs[contentType] == client.nodeID) {
                rsInterface.message = "@gre@Online";
            }
            else {
                rsInterface.message = "@red@Offline";
            }
            rsInterface.atActionType = 1;
        }
        else {
            if (contentType == 203) {
                int friendsCount3 = this.friendsCount;
                if (this.anInt900 != 2) {
                    friendsCount3 = 0;
                }
                rsInterface.scrollMax = friendsCount3 * 15 + 20;
                if (rsInterface.scrollMax <= rsInterface.height) {
                    rsInterface.scrollMax = rsInterface.height + 1;
                }
                return;
            }
            if (contentType >= 401 && contentType <= 500) {
                contentType -= 401;
                if (contentType == 0 && this.anInt900 == 0) {
                    rsInterface.message = "Loading ignore list";
                    rsInterface.atActionType = 0;
                    return;
                }
                if (contentType == 1 && this.anInt900 == 0) {
                    rsInterface.message = "Please wait...";
                    rsInterface.atActionType = 0;
                    return;
                }
                int ignoreCount = this.ignoreCount;
                if (this.anInt900 == 0) {
                    ignoreCount = 0;
                }
                if (contentType >= ignoreCount) {
                    rsInterface.message = "";
                    rsInterface.atActionType = 0;
                    return;
                }
                rsInterface.message = TextClass.fixName(TextClass.nameForLong(this.ignoreListAsLongs[contentType]));
                rsInterface.atActionType = 1;
            }
            else {
                if (contentType == 503) {
                    rsInterface.scrollMax = this.ignoreCount * 15 + 20;
                    if (rsInterface.scrollMax <= rsInterface.height) {
                        rsInterface.scrollMax = rsInterface.height + 1;
                    }
                    return;
                }
                if (contentType == 327) {
                    rsInterface.modelRotation1 = 150;
                    rsInterface.modelRotation2 = ((int)(Math.sin(client.loopCycle / 40.0) * 256.0) & 0x7FF);
                    if (this.aBoolean1031) {
                        for (int i = 0; i < 7; ++i) {
                            final int n = this.anIntArray1065[i];
                            if (n >= 0 && !IDK.cache[n].method537()) {
                                return;
                            }
                        }
                        this.aBoolean1031 = false;
                        final Model[] array = new Model[7];
                        int n2 = 0;
                        for (int j = 0; j < 7; ++j) {
                            final int n3 = this.anIntArray1065[j];
                            if (n3 >= 0) {
                                array[n2++] = IDK.cache[n3].method538();
                            }
                        }
                        final Model model = new Model(n2, array);
                        for (int k = 0; k < 5; ++k) {
                            if (this.anIntArray990[k] != 0) {
                                model.method476(client.anIntArrayArray1003[k][0], client.anIntArrayArray1003[k][this.anIntArray990[k]]);
                                if (k == 1) {
                                    model.method476(client.anIntArray1204[0], client.anIntArray1204[this.anIntArray990[k]]);
                                }
                            }
                        }
                        model.method469();
                        model.method470(Animation.anims[client.myPlayer.anInt1511].anIntArray353[0]);
                        model.method479(64, 850, -30, -50, -30, true);
                        rsInterface.anInt233 = 5;
                        rsInterface.mediaID = 0;
                        RSInterface.method208(this.aBoolean994, model);
                    }
                    return;
                }
                if (contentType == 328) {
                    final int modelRotation1 = 150;
                    final int modelRotation2 = (int)(Math.sin(client.loopCycle / 40.0) * 256.0) & 0x7FF;
                    rsInterface.modelRotation1 = modelRotation1;
                    rsInterface.modelRotation2 = modelRotation2;
                    if (this.aBoolean1031) {
                        final Model method452 = client.myPlayer.method452();
                        for (int l = 0; l < 5; ++l) {
                            if (this.anIntArray990[l] != 0) {
                                method452.method476(client.anIntArrayArray1003[l][0], client.anIntArrayArray1003[l][this.anIntArray990[l]]);
                                if (l == 1) {
                                    method452.method476(client.anIntArray1204[0], client.anIntArray1204[this.anIntArray990[l]]);
                                }
                            }
                        }
                        final int anInt1511 = client.myPlayer.anInt1511;
                        method452.method469();
                        method452.method470(Animation.anims[anInt1511].anIntArray353[0]);
                        rsInterface.anInt233 = 5;
                        rsInterface.mediaID = 0;
                        RSInterface.method208(this.aBoolean994, method452);
                    }
                    return;
                }
                if (contentType == 324) {
                    if (this.aClass30_Sub2_Sub1_Sub1_931 == null) {
                        this.aClass30_Sub2_Sub1_Sub1_931 = rsInterface.sprite1;
                        this.aClass30_Sub2_Sub1_Sub1_932 = rsInterface.sprite2;
                    }
                    if (this.aBoolean1047) {
                        rsInterface.sprite1 = this.aClass30_Sub2_Sub1_Sub1_932;
                        return;
                    }
                    rsInterface.sprite1 = this.aClass30_Sub2_Sub1_Sub1_931;
                }
                else if (contentType == 325) {
                    if (this.aClass30_Sub2_Sub1_Sub1_931 == null) {
                        this.aClass30_Sub2_Sub1_Sub1_931 = rsInterface.sprite1;
                        this.aClass30_Sub2_Sub1_Sub1_932 = rsInterface.sprite2;
                    }
                    if (this.aBoolean1047) {
                        rsInterface.sprite1 = this.aClass30_Sub2_Sub1_Sub1_931;
                        return;
                    }
                    rsInterface.sprite1 = this.aClass30_Sub2_Sub1_Sub1_932;
                }
                else {
                    if (contentType != 600) {
                        if (contentType == 620) {
                            if (this.myPrivilege >= 1) {
                                if (this.canMute) {
                                    rsInterface.textColor = 16711680;
                                    rsInterface.message = "Moderator option: Mute player for 48 hours: <ON>";
                                }
                                else {
                                    rsInterface.textColor = 16777215;
                                    rsInterface.message = "Moderator option: Mute player for 48 hours: <OFF>";
                                }
                            }
                            else {
                                rsInterface.message = "";
                            }
                        }
                        if (contentType == 650 || contentType == 655) {
                            if (this.anInt1193 != 0) {
                                String string;
                                if (this.daysSinceLastLogin == 0) {
                                    string = "earlier today";
                                }
                                else if (this.daysSinceLastLogin == 1) {
                                    string = "yesterday";
                                }
                                else {
                                    string = this.daysSinceLastLogin + " days ago";
                                }
                                rsInterface.message = "You last logged in " + string + " from: " + SignLink.dns;
                            }
                            else {
                                rsInterface.message = "";
                            }
                        }
                        if (contentType == 651) {
                            if (this.unreadMessages == 0) {
                                rsInterface.message = "0 unread messages";
                                rsInterface.textColor = 16776960;
                            }
                            if (this.unreadMessages == 1) {
                                rsInterface.message = "1 unread message";
                                rsInterface.textColor = 65280;
                            }
                            if (this.unreadMessages > 1) {
                                rsInterface.message = this.unreadMessages + " unread messages";
                                rsInterface.textColor = 65280;
                            }
                        }
                        if (contentType == 652) {
                            if (this.daysSinceRecovChange == 201) {
                                if (this.membersInt == 1) {
                                    rsInterface.message = "@whi@This is a non-members world: @whi@Since you are a member we";
                                }
                                else {
                                    rsInterface.message = "";
                                }
                            }
                            else if (this.daysSinceRecovChange == 200) {
                                rsInterface.message = "You have not yet set any password recovery questions.";
                            }
                            else {
                                String string2;
                                if (this.daysSinceRecovChange == 0) {
                                    string2 = "Earlier today";
                                }
                                else if (this.daysSinceRecovChange == 1) {
                                    string2 = "Yesterday";
                                }
                                else {
                                    string2 = this.daysSinceRecovChange + " days ago";
                                }
                                rsInterface.message = string2 + " you changed your recovery questions";
                            }
                        }
                        if (contentType == 653) {
                            if (this.daysSinceRecovChange == 201) {
                                if (this.membersInt == 1) {
                                    rsInterface.message = "@whi@recommend you use a members world instead. You may use";
                                }
                                else {
                                    rsInterface.message = "";
                                }
                            }
                            else if (this.daysSinceRecovChange == 200) {
                                rsInterface.message = "We strongly recommend you do so now to secure your account.";
                            }
                            else {
                                rsInterface.message = "If you do not remember making this change then cancel it immediately";
                            }
                        }
                        if (contentType == 654) {
                            if (this.daysSinceRecovChange == 201) {
                                if (this.membersInt == 1) {
                                    rsInterface.message = "@whi@this world but member benefits are unavailable whilst here.";
                                    return;
                                }
                                rsInterface.message = "";
                            }
                            else {
                                if (this.daysSinceRecovChange == 200) {
                                    rsInterface.message = "Do this from the 'account management' area on our front webpage";
                                    return;
                                }
                                rsInterface.message = "Do this from the 'account management' area on our front webpage";
                            }
                        }
                        return;
                    }
                    rsInterface.message = this.reportAbuseInput;
                    if (client.loopCycle % 20 < 10) {
                        rsInterface.message += "|";
                        return;
                    }
                    rsInterface.message += " ";
                }
            }
        }
    }
    
    private void drawSplitPrivateChat() {
        if (this.splitPrivateChat == 0) {
            return;
        }
        final TextDrawingArea aTextDrawingArea_1271 = this.aTextDrawingArea_1271;
        int n = 0;
        if (this.anInt1104 != 0) {
            n = 1;
        }
        for (int i = 0; i < 500; ++i) {
            if (this.chatMessages[i] != null) {
                final int n2 = this.chatTypes[i];
                String s = this.chatNames[i];
                int n3 = 0;
                if (s != null && s.startsWith("@cr1@")) {
                    s = s.substring(5);
                    n3 = 1;
                }
                if (s != null && s.startsWith("@cr2@")) {
                    s = s.substring(5);
                    n3 = 2;
                }
                if (s != null && s.startsWith("@cr4@")) {
                    s = s.substring(5);
                    n3 = 4;
                }
                if ((n2 == 3 || n2 == 7) && (n2 == 7 || this.privateChatMode == 0 || (this.privateChatMode == 1 && this.isFriendOrSelf(s)))) {
                    final int n4 = ((client.clientSize == 0) ? 503 : client.clientHeight) - 170 - n * 13;
                    final int n5 = 4;
                    aTextDrawingArea_1271.method385(0, "From", n4, n5);
                    aTextDrawingArea_1271.method385(65535, "From", n4 - 1, n5);
                    int n6 = n5 + aTextDrawingArea_1271.getTextWidth("From ");
                    if (n3 == 1) {
                        this.modIcons[0].drawSprite(n6, n4 - 13);
                        n6 += 14;
                    }
                    if (n3 == 2) {
                        this.modIcons[1].drawSprite(n6, n4 - 13);
                        n6 += 14;
                    }
                    if (n3 == 4) {
                        this.modIcons[3].drawSprite(n6, n4 - 13);
                        n6 += 14;
                    }
                    aTextDrawingArea_1271.method385(0, s + ": " + this.chatMessages[i], n4, n6);
                    aTextDrawingArea_1271.method385(65535, s + ": " + this.chatMessages[i], n4 - 1, n6);
                    if (++n >= 5) {
                        return;
                    }
                }
                if (n2 == 5 && this.privateChatMode < 2) {
                    final int n7 = ((client.clientSize == 0) ? 503 : client.clientHeight) - 170 - n * 13;
                    aTextDrawingArea_1271.method385(0, this.chatMessages[i], n7, 4);
                    aTextDrawingArea_1271.method385(65535, this.chatMessages[i], n7 - 1, 4);
                    if (++n >= 5) {
                        return;
                    }
                }
                if (n2 == 6 && this.privateChatMode < 2) {
                    final int n8 = ((client.clientSize == 0) ? 503 : client.clientHeight) - 170 - n * 13;
                    aTextDrawingArea_1271.method385(0, "To " + s + ": " + this.chatMessages[i], n8, 4);
                    aTextDrawingArea_1271.method385(65535, "To " + s + ": " + this.chatMessages[i], n8 - 1, 4);
                    if (++n >= 5) {
                        return;
                    }
                }
            }
        }
    }
    
    public void pushMessage(final String aString844, final int n, final String s) {
        if (n == 0 && this.dialogID != -1) {
            this.aString844 = aString844;
            super.clickMode3 = 0;
        }
        if (this.backDialogID == -1) {
            client.inputTaken = true;
        }
        for (int i = 499; i > 0; --i) {
            this.chatTypes[i] = this.chatTypes[i - 1];
            this.chatNames[i] = this.chatNames[i - 1];
            this.chatMessages[i] = this.chatMessages[i - 1];
            this.chatRights[i] = this.chatRights[i - 1];
        }
        this.chatTypes[0] = n;
        this.chatNames[0] = s;
        this.chatMessages[0] = aString844;
        this.chatRights[0] = this.rights;
    }
    
    public static void setTab(final int tabID) {
        client.needDrawTabArea = true;
        client.tabID = tabID;
        client.tabAreaAltered = true;
    }
    
    public void handle525Click() {
        if (this.clickMode3 != 1) {
            return;
        }
        final int tabID = client.tabID;
        final int n = (client.clientSize == 0) ? 522 : (client.clientWidth - 462);
        final int n2 = (client.clientSize == 0) ? 168 : (client.clientHeight - 36);
        if (this.saveClickX >= n && this.saveClickX <= n + 37 && this.saveClickY >= n2 && this.saveClickY < n2 + 36 && client.tabInterfaceIDs[0] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 0;
            client.tabAreaAltered = true;
        }
        int n3 = n + ((client.clientSize == 0) ? 38 : 33);
        if (this.saveClickX >= n3 && this.saveClickX <= n3 + 32 && this.saveClickY >= n2 && this.saveClickY < n2 + 36 && client.tabInterfaceIDs[1] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 1;
            client.tabAreaAltered = true;
        }
        n3 += 33;
        if (this.saveClickX >= n3 && this.saveClickX <= n3 + 32 && this.saveClickY >= n2 && this.saveClickY < n2 + 36 && client.tabInterfaceIDs[2] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 2;
            client.tabAreaAltered = true;
        }
        n3 += 33;
        if (this.saveClickX >= n3 && this.saveClickX <= n3 + 32 && this.saveClickY >= n2 && this.saveClickY < n2 + 36 && client.tabInterfaceIDs[3] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 3;
            client.tabAreaAltered = true;
        }
        n3 += 33;
        if (this.saveClickX >= n3 && this.saveClickX <= n3 + 32 && this.saveClickY >= n2 && this.saveClickY < n2 + 36 && client.tabInterfaceIDs[4] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 4;
            client.tabAreaAltered = true;
        }
        n3 += 33;
        if (this.saveClickX >= n3 && this.saveClickX <= n3 + 32 && this.saveClickY >= n2 && this.saveClickY < n2 + 36 && client.tabInterfaceIDs[5] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 5;
            client.tabAreaAltered = true;
        }
        n3 += 33;
        if (this.saveClickX >= n3 && this.saveClickX <= n3 + 38 && this.saveClickY >= n2 && this.saveClickY < n2 + 36 && client.tabInterfaceIDs[6] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 6;
            client.tabAreaAltered = true;
        }
        final int n4 = (client.clientSize == 0) ? 522 : (client.clientWidth - 231);
        final int n5 = (client.clientSize == 0) ? 466 : (client.clientHeight - 36);
        if (this.saveClickX >= n4 && this.saveClickX <= n4 + 38 && this.saveClickY >= n5 && this.saveClickY < n5 + 36 && client.tabInterfaceIDs[7] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 7;
            client.tabAreaAltered = true;
        }
        int n6 = n4 + ((client.clientSize == 0) ? 38 : 33);
        if (this.saveClickX >= n6 && this.saveClickX <= n6 + 32 && this.saveClickY >= n5 && this.saveClickY < n5 + 36 && client.tabInterfaceIDs[8] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 8;
            client.tabAreaAltered = true;
        }
        n6 += 33;
        if (this.saveClickX >= n6 && this.saveClickX <= n6 + 32 && this.saveClickY >= n5 && this.saveClickY < n5 + 36 && client.tabInterfaceIDs[9] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 9;
            client.tabAreaAltered = true;
        }
        n6 += 33;
        if (this.saveClickX >= n6 && this.saveClickX <= n6 + 32 && this.saveClickY >= n5 && this.saveClickY < n5 + 36 && client.tabInterfaceIDs[10] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 10;
            client.tabAreaAltered = true;
        }
        n6 += 33;
        if (this.saveClickX >= n6 && this.saveClickX <= n6 + 32 && this.saveClickY >= n5 && this.saveClickY < n5 + 36 && client.tabInterfaceIDs[11] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 11;
            client.tabAreaAltered = true;
        }
        n6 += 33;
        if (this.saveClickX >= n6 && this.saveClickX <= n6 + 32 && this.saveClickY >= n5 && this.saveClickY < n5 + 36 && client.tabInterfaceIDs[12] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 12;
            client.tabAreaAltered = true;
        }
        n6 += 33;
        if (this.saveClickX >= n6 && this.saveClickX <= n6 + 38 && this.saveClickY >= n5 && this.saveClickY < n5 + 36 && client.tabInterfaceIDs[13] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 13;
            client.tabAreaAltered = true;
        }
        if (tabID == client.tabID && client.clientSize >= 1 && client.tabAreaAltered) {
            client.tabID = -1;
        }
    }
    
    private void handleFixed562Click() {
        if (super.mouseX >= 521 && super.mouseX <= 550 && super.mouseY >= 169 && super.mouseY < 205) {
            this.tabHPos = 0;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        else if (super.mouseX >= 552 && super.mouseX <= 581 && super.mouseY >= 168 && super.mouseY < 205) {
            this.tabHPos = 1;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        else if (super.mouseX >= 582 && super.mouseX <= 611 && super.mouseY >= 168 && super.mouseY < 205) {
            this.tabHPos = 2;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        else if (super.mouseX >= 612 && super.mouseX <= 641 && super.mouseY >= 168 && super.mouseY < 203) {
            this.tabHPos = 3;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        else if (super.mouseX >= 642 && super.mouseX <= 671 && super.mouseY >= 168 && super.mouseY < 205) {
            this.tabHPos = 4;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        else if (super.mouseX >= 672 && super.mouseX <= 701 && super.mouseY >= 168 && super.mouseY < 205) {
            this.tabHPos = 5;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        else if (super.mouseX >= 702 && super.mouseX <= 731 && super.mouseY >= 169 && super.mouseY < 205) {
            this.tabHPos = 6;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        else if (super.mouseX >= 732 && super.mouseX <= 761 && super.mouseY >= 169 && super.mouseY < 205) {
            this.tabHPos = 7;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        else if (super.mouseX >= 522 && super.mouseX <= 551 && super.mouseY >= 466 && super.mouseY < 503) {
            this.tabHPos = 15;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        else if (super.mouseX >= 552 && super.mouseX <= 581 && super.mouseY >= 466 && super.mouseY < 503) {
            this.tabHPos = 8;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        else if (super.mouseX >= 582 && super.mouseX <= 611 && super.mouseY >= 466 && super.mouseY < 503) {
            this.tabHPos = 9;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        else if (super.mouseX >= 612 && super.mouseX <= 641 && super.mouseY >= 466 && super.mouseY < 503) {
            this.tabHPos = 10;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        else if (super.mouseX >= 642 && super.mouseX <= 671 && super.mouseY >= 466 && super.mouseY < 503) {
            this.tabHPos = 11;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        else if (super.mouseX >= 672 && super.mouseX <= 701 && super.mouseY >= 466 && super.mouseY < 503) {
            this.tabHPos = 12;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        else if (super.mouseX >= 702 && super.mouseX <= 731 && super.mouseY >= 466 && super.mouseY < 502) {
            this.tabHPos = 13;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        else if (super.mouseX >= 732 && super.mouseX <= 761 && super.mouseY >= 466 && super.mouseY < 502) {
            this.tabHPos = 14;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        else {
            this.tabHPos = -1;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        if (super.clickMode3 == 1) {
            if (super.saveClickX >= 522 && super.saveClickX <= 551 && super.saveClickY >= 169 && super.saveClickY < 205 && client.tabInterfaceIDs[0] != -1) {
                client.needDrawTabArea = true;
                client.tabID = 0;
                client.tabAreaAltered = true;
            }
            else if (super.saveClickX >= 552 && super.saveClickX <= 581 && super.saveClickY >= 168 && super.saveClickY < 205 && client.tabInterfaceIDs[1] != -1) {
                client.needDrawTabArea = true;
                client.tabID = 1;
                client.tabAreaAltered = true;
            }
            else if (super.saveClickX >= 582 && super.saveClickX <= 611 && super.saveClickY >= 168 && super.saveClickY < 205 && client.tabInterfaceIDs[2] != -1) {
                client.needDrawTabArea = true;
                client.tabID = 2;
                client.tabAreaAltered = true;
            }
            else if (super.saveClickX >= 612 && super.saveClickX <= 641 && super.saveClickY >= 168 && super.saveClickY < 203 && client.tabInterfaceIDs[14] != -1) {
                client.needDrawTabArea = true;
                client.tabID = 14;
                client.tabAreaAltered = true;
            }
            else if (super.saveClickX >= 642 && super.saveClickX <= 671 && super.saveClickY >= 168 && super.saveClickY < 205 && client.tabInterfaceIDs[3] != -1) {
                client.needDrawTabArea = true;
                client.tabID = 3;
                client.tabAreaAltered = true;
            }
            else if (super.saveClickX >= 672 && super.saveClickX <= 701 && super.saveClickY >= 168 && super.saveClickY < 205 && client.tabInterfaceIDs[4] != -1) {
                client.needDrawTabArea = true;
                client.tabID = 4;
                client.tabAreaAltered = true;
            }
            else if (super.saveClickX >= 702 && super.saveClickX <= 731 && super.saveClickY >= 169 && super.saveClickY < 205 && client.tabInterfaceIDs[5] != -1) {
                client.needDrawTabArea = true;
                client.tabID = 5;
                client.tabAreaAltered = true;
            }
            else if (super.saveClickX >= 732 && super.saveClickX <= 761 && super.saveClickY >= 169 && super.saveClickY < 205 && client.tabInterfaceIDs[6] != -1) {
                client.needDrawTabArea = true;
                client.tabID = 6;
                client.tabAreaAltered = true;
            }
            else if (super.saveClickX >= 522 && super.saveClickX <= 551 && super.saveClickY >= 466 && super.saveClickY < 503 && client.tabInterfaceIDs[16] != -1) {
                client.needDrawTabArea = true;
                client.tabID = 16;
                client.tabAreaAltered = true;
            }
            else if (super.saveClickX >= 552 && super.saveClickX <= 581 && super.saveClickY >= 466 && super.saveClickY < 503 && client.tabInterfaceIDs[8] != -1) {
                client.needDrawTabArea = true;
                client.tabID = 8;
                client.tabAreaAltered = true;
            }
            else if (super.saveClickX >= 582 && super.saveClickX <= 611 && super.saveClickY >= 466 && super.saveClickY < 503 && client.tabInterfaceIDs[9] != -1) {
                client.needDrawTabArea = true;
                client.tabID = 9;
                client.tabAreaAltered = true;
            }
            else if (super.saveClickX >= 612 && super.saveClickX <= 641 && super.saveClickY >= 466 && super.saveClickY < 503 && client.tabInterfaceIDs[7] != -1) {
                client.needDrawTabArea = true;
                client.tabID = 7;
                client.tabAreaAltered = true;
            }
            else if (super.saveClickX >= 642 && super.saveClickX <= 671 && super.saveClickY >= 466 && super.saveClickY < 503 && client.tabInterfaceIDs[11] != -1) {
                client.needDrawTabArea = true;
                client.tabID = 11;
                client.tabAreaAltered = true;
            }
            else if (super.saveClickX >= 672 && super.saveClickX <= 701 && super.saveClickY >= 466 && super.saveClickY < 503 && client.tabInterfaceIDs[12] != -1) {
                client.needDrawTabArea = true;
                client.tabID = 12;
                client.tabAreaAltered = true;
            }
            else if (super.saveClickX >= 702 && super.saveClickX <= 731 && super.saveClickY >= 466 && super.saveClickY < 502 && client.tabInterfaceIDs[13] != -1) {
                client.needDrawTabArea = true;
                client.tabID = 13;
                client.tabAreaAltered = true;
            }
            else if (super.saveClickX >= 732 && super.saveClickX <= 761 && super.saveClickY >= 466 && super.saveClickY < 502 && client.tabInterfaceIDs[15] != -1) {
                client.needDrawTabArea = true;
                client.tabID = 15;
                client.tabAreaAltered = true;
            }
            else if (super.saveClickX >= 742 && super.saveClickX <= 764 && super.saveClickY >= 1 && super.saveClickY < 24 && client.tabInterfaceIDs[10] != -1) {
                client.needDrawTabArea = true;
                client.tabID = 10;
                client.tabAreaAltered = true;
            }
        }
    }
    
    public void handleResized562Click(final int n, final int n2) {
        if (this.clickMode3 != 1) {
            return;
        }
        final int tabID = client.tabID;
        final int n3 = (client.clientSize == 0) ? 522 : (client.clientWidth - 488);
        final int n4 = (client.clientSize == 0) ? 168 : (client.clientHeight - 34);
        if (this.saveClickX >= n3 && this.saveClickX <= n3 + 37 && this.saveClickY >= n4 && this.saveClickY < n4 + 36 && client.tabInterfaceIDs[0] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 0;
            client.tabAreaAltered = true;
        }
        int n5 = n3 + ((client.clientSize == 0) ? 38 : 33);
        if (this.saveClickX >= n5 && this.saveClickX <= n5 + 32 && this.saveClickY >= n4 && this.saveClickY < n4 + 36 && client.tabInterfaceIDs[1] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 1;
            client.tabAreaAltered = true;
        }
        n5 += 33;
        if (this.saveClickX >= n5 && this.saveClickX <= n5 + 32 && this.saveClickY >= n4 && this.saveClickY < n4 + 36 && client.tabInterfaceIDs[2] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 2;
            client.tabAreaAltered = true;
        }
        n5 += 50;
        if (this.saveClickX >= n5 && this.saveClickX <= n5 + 32 && this.saveClickY >= n4 && this.saveClickY < n4 + 36 && client.tabInterfaceIDs[3] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 3;
            client.tabAreaAltered = true;
        }
        n5 += 33;
        if (this.saveClickX >= n5 && this.saveClickX <= n5 + 32 && this.saveClickY >= n4 && this.saveClickY < n4 + 36 && client.tabInterfaceIDs[4] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 4;
            client.tabAreaAltered = true;
        }
        n5 += 33;
        if (this.saveClickX >= n5 && this.saveClickX <= n5 + 32 && this.saveClickY >= n4 && this.saveClickY < n4 + 36 && client.tabInterfaceIDs[5] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 5;
            client.tabAreaAltered = true;
        }
        n5 += 33;
        if (this.saveClickX >= n5 && this.saveClickX <= n5 + 33 && this.saveClickY >= n4 && this.saveClickY < n4 + 36 && client.tabInterfaceIDs[6] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 6;
            client.tabAreaAltered = true;
        }
        int n6 = (client.clientSize == 0) ? 522 : (client.clientWidth - 231);
        final int n7 = (client.clientSize == 0) ? 466 : (client.clientHeight - 36);
        n6 += 17;
        if (this.saveClickX >= n6 && this.saveClickX <= n6 + 33 && this.saveClickY >= n7 && this.saveClickY < n7 + 36 && client.tabInterfaceIDs[7] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 7;
            client.tabAreaAltered = true;
        }
        int n8 = n6 + ((client.clientSize == 0) ? 38 : 33);
        if (this.saveClickX >= n8 && this.saveClickX <= n8 + 32 && this.saveClickY >= n7 && this.saveClickY < n7 + 36 && client.tabInterfaceIDs[8] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 8;
            client.tabAreaAltered = true;
        }
        n8 += 33;
        if (this.saveClickX >= n8 && this.saveClickX <= n8 + 32 && this.saveClickY >= n7 && this.saveClickY < n7 + 36 && client.tabInterfaceIDs[9] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 9;
            client.tabAreaAltered = true;
        }
        n8 += 33;
        if (this.saveClickX >= n8 && this.saveClickX <= n8 + 32 && this.saveClickY >= n7 && this.saveClickY < n7 + 36 && client.tabInterfaceIDs[10] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 11;
            client.tabAreaAltered = true;
        }
        n8 -= 10;
        if (this.saveClickX >= n8 && this.saveClickX <= n8 + 32 && this.saveClickY >= n7 && this.saveClickY < n7 + 36 && client.tabInterfaceIDs[11] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 11;
            client.tabAreaAltered = true;
        }
        n8 += 33;
        if (this.saveClickX >= n8 && this.saveClickX <= n8 + 30 && this.saveClickY >= n7 && this.saveClickY < n7 + 36 && client.tabInterfaceIDs[12] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 12;
            client.tabAreaAltered = true;
        }
        n8 += 33;
        if (this.saveClickX >= n8 && this.saveClickX <= n8 + 28 && this.saveClickY >= n7 && this.saveClickY < n7 + 36 && client.tabInterfaceIDs[13] != -1) {
            client.needDrawTabArea = true;
            client.tabID = 13;
            client.tabAreaAltered = true;
        }
        if (tabID == client.tabID && client.clientSize >= 1 && client.tabAreaAltered) {
            client.tabID = -1;
        }
    }
    
    public void handle562Click() {
        if (client.clientSize == 0) {
            this.handleFixed562Click();
        }
        else {
            this.handleResized562Click(0, 0);
        }
    }
    
    private void processTabClick() {
        if (this.isNewGameFrame) {
            this.handle562Click();
        }
        else {
            this.handle525Click();
        }
    }
    
    @Override
    public void resetImageProducers2() {
        if (this.chatAreaIP != null) {
            return;
        }
        this.nullLoader();
        super.fullGameScreen = null;
        this.aRSImageProducer_1107 = null;
        this.aRSImageProducer_1108 = null;
        this.aRSImageProducer_1109 = null;
        this.aRSImageProducer_1110 = null;
        this.aRSImageProducer_1111 = null;
        this.aRSImageProducer_1112 = null;
        this.aRSImageProducer_1113 = null;
        this.aRSImageProducer_1114 = null;
        this.aRSImageProducer_1115 = null;
        this.chatAreaIP = new RSImageProducer(519, 165, this.getGameComponent());
        this.mapAreaIP = new RSImageProducer(246, 168, this.getGameComponent());
        DrawingArea.setAllPixelsToZero();
        this.tabAreaIP = new RSImageProducer(246, 335, this.getGameComponent());
        if (client.clientSize == 0) {
            this.gameScreenIP = new RSImageProducer(512, 334, this.getGameComponent());
        }
        else if (client.clientSize == 1 || client.clientSize == 2) {
            this.gameScreenIP = new RSImageProducer(client.clientWidth, client.clientHeight, this.getGameComponent());
        }
        DrawingArea.setAllPixelsToZero();
        this.aRSImageProducer_1124 = new RSImageProducer(269, 37, this.getGameComponent());
        this.aRSImageProducer_1125 = new RSImageProducer(249, 45, this.getGameComponent());
        this.welcomeScreenRaised = true;
    }
    
    public String getDocumentBaseHost() {
        if (SignLink.mainapp != null) {
            return SignLink.mainapp.getDocumentBase().getHost().toLowerCase();
        }
        return null;
    }
    
    private void method81(final Sprite sprite, final int n, final int n2) {
        final int n3 = n2 * n2 + n * n;
        if (n3 > 4225 && n3 < 90000) {
            final int n4 = this.minimapInt1 + this.minimapInt2 & 0x7FF;
            final int n5 = Model.modelIntArray1[n4];
            final int n6 = Model.modelIntArray2[n4];
            final int n7 = n5 * 256 / (this.minimapInt3 + 256);
            final int n8 = n6 * 256 / (this.minimapInt3 + 256);
            final double atan2 = Math.atan2(n * n7 + n2 * n8 >> 16, n * n8 - n2 * n7 >> 16);
            this.mapEdge.method353(83 - (int)(Math.cos(atan2) * 57.0) - 20, atan2, 94 + (int)(Math.sin(atan2) * 63.0) + 4 - 10);
        }
        else {
            this.markMinimap(sprite, n2, n);
        }
    }
    
    private void rightClickChatButtons() {
        if (!this.displayChat) {
            return;
        }
        if (super.mouseX >= 5 && super.mouseX <= 61 && super.mouseY >= ((client.clientSize != 0) ? client.clientHeight : 503) - 23 && super.mouseY <= ((client.clientSize != 0) ? client.clientHeight : 503)) {
            this.menuActionName[1] = "View All";
            this.menuActionID[1] = 999;
            this.menuActionRow = 2;
        }
        else if (super.mouseX >= 71 && super.mouseX <= 127 && super.mouseY >= ((client.clientSize != 0) ? client.clientHeight : 503) - 23 && super.mouseY <= ((client.clientSize != 0) ? client.clientHeight : 503)) {
            this.menuActionName[1] = "View Game";
            this.menuActionID[1] = 998;
            this.menuActionRow = 2;
        }
        else if (super.mouseX >= 137 && super.mouseX <= 193 && super.mouseY >= ((client.clientSize != 0) ? client.clientHeight : 503) - 23 && super.mouseY <= ((client.clientSize != 0) ? client.clientHeight : 503)) {
            this.menuActionName[1] = "Hide public";
            this.menuActionID[1] = 997;
            this.menuActionName[2] = "Off public";
            this.menuActionID[2] = 996;
            this.menuActionName[3] = "Friends public";
            this.menuActionID[3] = 995;
            this.menuActionName[4] = "On public";
            this.menuActionID[4] = 994;
            this.menuActionName[5] = "View public";
            this.menuActionID[5] = 993;
            this.menuActionRow = 6;
        }
        else if (super.mouseX >= 203 && super.mouseX <= 259 && super.mouseY >= ((client.clientSize != 0) ? client.clientHeight : 503) - 23 && super.mouseY <= ((client.clientSize != 0) ? client.clientHeight : 503)) {
            this.menuActionName[1] = "Off private";
            this.menuActionID[1] = 992;
            this.menuActionName[2] = "Friends private";
            this.menuActionID[2] = 991;
            this.menuActionName[3] = "On private";
            this.menuActionID[3] = 990;
            this.menuActionName[4] = "View private";
            this.menuActionID[4] = 989;
            this.menuActionRow = 5;
        }
        else if (super.mouseX >= 269 && super.mouseX <= 325 && super.mouseY >= ((client.clientSize != 0) ? client.clientHeight : 503) - 23 && super.mouseY <= ((client.clientSize != 0) ? client.clientHeight : 503)) {
            this.menuActionName[1] = "Off clan chat";
            this.menuActionID[1] = 1003;
            this.menuActionName[2] = "Friends clan chat";
            this.menuActionID[2] = 1002;
            this.menuActionName[3] = "On clan chat";
            this.menuActionID[3] = 1001;
            this.menuActionName[4] = "View clan chat";
            this.menuActionID[4] = 1000;
            this.menuActionRow = 5;
        }
        else if (super.mouseX >= 335 && super.mouseX <= 391 && super.mouseY >= ((client.clientSize != 0) ? client.clientHeight : 503) - 23 && super.mouseY <= ((client.clientSize != 0) ? client.clientHeight : 503)) {
            this.menuActionName[1] = "Off trade";
            this.menuActionID[1] = 987;
            this.menuActionName[2] = "Friends trade";
            this.menuActionID[2] = 986;
            this.menuActionName[3] = "On trade";
            this.menuActionID[3] = 985;
            this.menuActionName[4] = "View trade";
            this.menuActionID[4] = 984;
            this.menuActionRow = 5;
        }
    }
    
    public boolean canClickScreen() {
        return (super.mouseX <= 0 || super.mouseY <= client.clientHeight - 165 || super.mouseX >= 519 || super.mouseY >= client.clientHeight || !this.displayChat) && (super.mouseX <= client.clientWidth - 246 || super.mouseY <= client.clientHeight - 335 || super.mouseX >= client.clientWidth || super.mouseY >= client.clientHeight) && (super.mouseX <= client.clientWidth - 220 || super.mouseY <= 0 || super.mouseX >= client.clientWidth || super.mouseY >= 164) && (super.mouseX <= 247 || super.mouseX >= 260 || super.mouseY <= client.clientHeight - 173 || super.mouseY >= client.clientHeight - 166) && super.mouseY <= client.clientHeight - 15 && (super.mouseX <= client.clientWidth - 462 || super.mouseY <= client.clientHeight - 36 || super.mouseX >= client.clientWidth || super.mouseY >= client.clientHeight);
    }
    
    public void processRightClick() {
        if (this.activeInterfaceType != 0) {
            return;
        }
        this.menuActionName[0] = "Cancel";
        this.menuActionID[0] = 1107;
        this.menuActionRow = 1;
        if (this.fullscreenInterfaceID != -1) {
            this.anInt886 = 0;
            this.anInt1315 = 0;
            this.buildInterfaceMenu(8, RSInterface.interfaceCache[this.fullscreenInterfaceID], super.mouseX, 8, super.mouseY, 0);
            if (this.anInt886 != this.anInt1026) {
                this.anInt1026 = this.anInt886;
            }
            if (this.anInt1315 != this.anInt1129) {
                this.anInt1129 = this.anInt1315;
            }
            return;
        }
        this.buildSplitPrivateChatMenu();
        this.anInt886 = 0;
        this.anInt1315 = 0;
        if (client.clientSize == 0) {
            if (super.mouseX > 4 && super.mouseY > 4 && super.mouseX < 516 && super.mouseY < 338) {
                if (client.openInterfaceID != -1) {
                    this.buildInterfaceMenu(4, RSInterface.interfaceCache[client.openInterfaceID], super.mouseX, 4, super.mouseY, 0);
                }
                else {
                    this.build3dScreenMenu();
                }
            }
        }
        else if ((client.clientSize == 1 || client.clientSize == 2) && this.canClickScreen()) {
            if (super.mouseX > client.clientWidth / 2 - 256 && super.mouseY > client.clientHeight / 2 - 167 && super.mouseX < client.clientWidth / 2 + 256 && super.mouseY < client.clientHeight / 2 + 167 && client.openInterfaceID != -1) {
                this.buildInterfaceMenu(client.clientWidth / 2 - 256, RSInterface.interfaceCache[client.openInterfaceID], super.mouseX, client.clientHeight / 2 - 167, super.mouseY, 0);
            }
            else {
                this.build3dScreenMenu();
            }
        }
        if (this.anInt886 != this.anInt1026) {
            this.anInt1026 = this.anInt886;
        }
        if (this.anInt1315 != this.anInt1129) {
            this.anInt1129 = this.anInt1315;
        }
        this.anInt886 = 0;
        this.anInt1315 = 0;
        if (client.clientSize == 0) {
            if (super.mouseX > 547 && super.mouseY > 205 && super.mouseX < 740 && super.mouseY < 468) {
                if (this.invOverlayInterfaceID != -1) {
                    this.buildInterfaceMenu(547, RSInterface.interfaceCache[this.invOverlayInterfaceID], super.mouseX, 205, super.mouseY, 0);
                }
                else if (client.tabInterfaceIDs[client.tabID] != -1) {
                    this.buildInterfaceMenu(547, RSInterface.interfaceCache[client.tabInterfaceIDs[client.tabID]], super.mouseX, 205, super.mouseY, 0);
                }
            }
        }
        else if (client.clientSize >= 1 && super.mouseX > client.clientWidth - 198 && super.mouseY > client.clientHeight - 304 && super.mouseX < client.clientWidth - 8 && super.mouseY < client.clientHeight - 41 && client.tabID != -1) {
            if (this.invOverlayInterfaceID != -1) {
                this.buildInterfaceMenu(client.clientWidth - 198, RSInterface.interfaceCache[this.invOverlayInterfaceID], this.mouseX, client.clientHeight - 304, this.mouseY, 0);
            }
            else if (client.tabInterfaceIDs[client.tabID] != -1) {
                this.buildInterfaceMenu(client.clientWidth - 198, RSInterface.interfaceCache[client.tabInterfaceIDs[client.tabID]], this.mouseX, client.clientHeight - 304, this.mouseY, 0);
            }
        }
        if (this.anInt886 != this.anInt1048) {
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
            this.anInt1048 = this.anInt886;
        }
        if (this.anInt1315 != this.anInt1044) {
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
            this.anInt1044 = this.anInt1315;
        }
        this.anInt886 = 0;
        this.anInt1315 = 0;
        if (super.mouseX > 0 && super.mouseY > ((client.clientSize != 0) ? client.clientHeight : 503) - 165 && super.mouseX < 490 && super.mouseY < client.clientHeight) {
            if (this.backDialogID != -1) {
                this.buildInterfaceMenu(20, RSInterface.interfaceCache[this.backDialogID], super.mouseX, ((client.clientSize != 0) ? client.clientHeight : 503) - 145, super.mouseY, 0);
            }
            else if (super.mouseY < ((client.clientSize != 0) ? client.clientHeight : 503) && super.mouseX < 490) {
                this.buildChatAreaMenu(super.mouseY - (((client.clientSize != 0) ? client.clientHeight : 503) - 165));
            }
        }
        if (this.backDialogID != -1 && this.anInt886 != this.anInt1039) {
            client.inputTaken = true;
            this.anInt1039 = this.anInt886;
        }
        if (this.backDialogID != -1 && this.anInt1315 != this.anInt1500) {
            client.inputTaken = true;
            this.anInt1500 = this.anInt1315;
        }
        this.processMinimapActions();
        if (super.mouseX > 4 && super.mouseY > ((client.clientSize != 0) ? client.clientHeight : 503) - 23 && super.mouseX < 516 && super.mouseY < ((client.clientSize != 0) ? client.clientHeight : 503)) {
            this.rightClickChatButtons();
        }
        if (super.mouseX > 519 && super.mouseX < 555 && super.mouseY > 46 && super.mouseY < 82 && client.clientSize == 0) {
            this.processXPCounterClick();
        }
        if (super.mouseX > 553 + (client.clientWidth - 765) && super.mouseX < 553 + (client.clientWidth - 765) + 34 && super.mouseY > 3 && super.mouseY < 37 && client.clientSize != 0) {
            this.processXPCounterClick();
        }
        int i = 0;
        while (i == 0) {
            i = 1;
            for (int j = 0; j < this.menuActionRow - 1; ++j) {
                if (this.menuActionID[j] < 1000 && this.menuActionID[j + 1] > 1000) {
                    final String s = this.menuActionName[j];
                    this.menuActionName[j] = this.menuActionName[j + 1];
                    this.menuActionName[j + 1] = s;
                    final int n = this.menuActionID[j];
                    this.menuActionID[j] = this.menuActionID[j + 1];
                    this.menuActionID[j + 1] = n;
                    final int n2 = this.menuActionCmd2[j];
                    this.menuActionCmd2[j] = this.menuActionCmd2[j + 1];
                    this.menuActionCmd2[j + 1] = n2;
                    final int n3 = this.menuActionCmd3[j];
                    this.menuActionCmd3[j] = this.menuActionCmd3[j + 1];
                    this.menuActionCmd3[j + 1] = n3;
                    final int n4 = this.menuActionCmd1[j];
                    this.menuActionCmd1[j] = this.menuActionCmd1[j + 1];
                    this.menuActionCmd1[j + 1] = n4;
                    i = 0;
                }
            }
        }
    }
    
    public final String loyaltyRank(final int n) {
        switch (n) {
            case 1: {
                return "Lord";
            }
            case 2: {
                return "Lady";
            }
            case 3: {
                return "Sir";
            }
            case 4: {
                return "Dame";
            }
            case 5: {
                return "Duderino";
            }
            case 6: {
                return "Dudette";
            }
            case 7: {
                return "Lionheart";
            }
            case 8: {
                return "Crusader";
            }
            case 9: {
                return "Hellraiser";
            }
            case 10: {
                return "Desperado";
            }
            case 11: {
                return "Baron";
            }
            case 12: {
                return "Baroness";
            }
            case 13: {
                return "Count";
            }
            case 14: {
                return "Countess";
            }
            case 15: {
                return "Overlord";
            }
            case 16: {
                return "Overlordess";
            }
            case 17: {
                return "Bandito";
            }
            case 18: {
                return "Duke";
            }
            case 19: {
                return "Duchess";
            }
            case 20: {
                return "Big Cheese";
            }
            case 21: {
                return "Bigwig";
            }
            case 22: {
                return "King";
            }
            case 23: {
                return "Queen";
            }
            case 24: {
                return "Wunderkind";
            }
            case 25: {
                return "Legend";
            }
            case 26: {
                return "Owner";
            }
            case 27: {
                return "Moderator";
            }
            case 28: {
                return "Administrator";
            }
            case 29: {
                return "Donator";
            }
            case 30: {
                return "Support-Member";
            }
            case 31: {
                return "Veteran";
            }
            case 32: {
                return "Forum-Moderator";
            }
            case 33: {
                return "Developer";
            }
            case 34: {
                return "Co-Owner";
            }
            case 35: {
                return "Global Mod";
            }
            default: {
                return "";
            }
        }
    }
    
    private int method83(final int n, final int n2, final int n3) {
        final int n4 = 256 - n3;
        return ((n & 0xFF00FF) * n4 + (n2 & 0xFF00FF) * n3 & 0xFF00FF00) + ((n & 0xFF00) * n4 + (n2 & 0xFF00) * n3 & 0xFF0000) >> 8;
    }
    
    private void login(final String errorname, final String s, final boolean b) {
        if (this.isLoggingIn) {
            return;
        }
        this.isLoggingIn = true;
        SignLink.errorname = errorname;
        final String s2 = "error";
        try {
            if (!b) {
                this.loginMessage1 = "";
                this.loginMessage2 = "@whi@Connecting to Devilishpkz server...";
                this.drawLoginScreen(true);
            }
            this.socketStream = new RSSocket(this, this.openSocket(43594 + client.portOff));
            final int n = (int)(TextClass.longForName(s2) >> 16 & 0x1FL);
            this.stream.currentOffset = 0;
            this.stream.writeWordBigEndian(14);
            this.stream.writeWordBigEndian(n);
            this.socketStream.queueBytes(2, this.stream.buffer);
            for (int i = 0; i < 8; ++i) {
                this.socketStream.read();
            }
            int n2 = this.socketStream.read();
            final int n3;
            if ((n3 = n2) == 0) {
                this.socketStream.flushInputStream(this.inStream.buffer, 8);
                this.inStream.currentOffset = 0;
                this.aLong1215 = this.inStream.readQWord();
                final int[] array = { (int)(Math.random() * 9.9999999E7), (int)(Math.random() * 9.9999999E7), (int)(this.aLong1215 >> 32), (int)this.aLong1215 };
                this.stream.currentOffset = 0;
                this.stream.writeByte(10);
                this.stream.writeDWord(array[0]);
                this.stream.writeDWord(array[1]);
                this.stream.writeDWord(array[2]);
                this.stream.writeDWord(array[3]);
                this.stream.writeDWord(0);
                this.stream.writeString(errorname);
                this.stream.writeString(s);
                this.stream.doKeys();
                this.aStream_847.currentOffset = 0;
                if (b) {
                    this.aStream_847.writeByte(18);
                }
                else {
                    this.aStream_847.writeByte(16);
                }
                this.aStream_847.writeByte(this.stream.currentOffset + 36 + 1 + 1 + 2);
                this.aStream_847.writeByte(255);
                this.aStream_847.writeWord(317);
                this.aStream_847.writeByte(client.lowMem ? 1 : 0);
                for (int j = 0; j < 9; ++j) {
                    this.aStream_847.writeDWord(this.expectedCRCs[j]);
                }
                this.aStream_847.writeBytes(this.stream.buffer, this.stream.currentOffset, 0);
                this.stream.encryption = new ISAACRandomGen(array);
                for (int k = 0; k < 4; ++k) {
                    final int[] array2 = array;
                    final int n4 = k;
                    array2[n4] += 50;
                }
                this.encryption = new ISAACRandomGen(array);
                this.socketStream.queueBytes(this.aStream_847.currentOffset, this.aStream_847.buffer);
                n2 = this.socketStream.read();
            }
            if (n2 == 1) {
                try {
                    Thread.sleep(2000L);
                }
                catch (Exception ex) {}
                this.login(errorname, s, b);
                return;
            }
            if (n2 == 2) {
                this.myPrivilege = this.socketStream.read();
                client.flagged = (this.socketStream.read() == 1);
                this.aLong1220 = 0L;
                this.anInt1022 = 0;
                this.mouseDetection.coordsIndex = 0;
                super.awtFocus = true;
                Texture.method372(0.6);
                this.aBoolean954 = true;
                this.loggedIn = true;
                this.stream.currentOffset = 0;
                this.inStream.currentOffset = 0;
                this.pktType = -1;
                this.anInt841 = -1;
                this.anInt842 = -1;
                this.anInt843 = -1;
                this.pktSize = 0;
                this.anInt1009 = 0;
                this.anInt1104 = 0;
                this.anInt1011 = 0;
                this.anInt855 = 0;
                this.menuActionRow = 0;
                this.menuOpen = false;
                super.idleTime = 0;
                for (int l = 0; l < 500; ++l) {
                    this.chatMessages[l] = null;
                }
                this.itemSelected = 0;
                this.spellSelected = 0;
                this.loadingStage = 0;
                this.anInt1278 = (int)(Math.random() * 100.0) - 50;
                this.anInt1131 = (int)(Math.random() * 110.0) - 55;
                this.anInt896 = (int)(Math.random() * 80.0) - 40;
                this.minimapInt2 = (int)(Math.random() * 120.0) - 60;
                this.minimapInt3 = (int)(Math.random() * 30.0) - 20;
                this.minimapInt1 = ((int)(Math.random() * 20.0) - 10 & 0x7FF);
                this.anInt1021 = 0;
                this.anInt985 = -1;
                this.destX = 0;
                this.destY = 0;
                this.playerCount = 0;
                this.npcCount = 0;
                for (int n5 = 0; n5 < this.maxPlayers; ++n5) {
                    this.playerArray[n5] = null;
                    this.aStreamArray895s[n5] = null;
                }
                for (int n6 = 0; n6 < 16384; ++n6) {
                    this.npcArray[n6] = null;
                }
                final Player[] playerArray = this.playerArray;
                final int myPlayerIndex = this.myPlayerIndex;
                final Player myPlayer = new Player();
                playerArray[myPlayerIndex] = myPlayer;
                client.myPlayer = myPlayer;
                this.aClass19_1013.removeAll();
                this.aClass19_1056.removeAll();
                for (int n7 = 0; n7 < 4; ++n7) {
                    for (int n8 = 0; n8 < 104; ++n8) {
                        for (int n9 = 0; n9 < 104; ++n9) {
                            this.groundArray[n7][n8][n9] = null;
                        }
                    }
                }
                this.aClass19_1179 = new NodeList();
                this.fullscreenInterfaceID = -1;
                this.anInt900 = 0;
                this.friendsCount = 0;
                this.dialogID = -1;
                this.backDialogID = -1;
                client.openInterfaceID = -1;
                this.invOverlayInterfaceID = -1;
                this.anInt1018 = -1;
                this.aBoolean1149 = false;
                client.tabID = 3;
                this.inputDialogState = 0;
                this.menuOpen = false;
                this.messagePromptRaised = false;
                this.aString844 = null;
                this.anInt1055 = 0;
                this.anInt1054 = -1;
                this.aBoolean1047 = true;
                this.method45();
                for (int n10 = 0; n10 < 5; ++n10) {
                    this.anIntArray990[n10] = 0;
                }
                for (int n11 = 0; n11 < 5; ++n11) {
                    this.atPlayerActions[n11] = null;
                    this.atPlayerArray[n11] = false;
                }
                client.anInt1175 = 0;
                client.anInt1134 = 0;
                client.anInt986 = 0;
                client.anInt1288 = 0;
                client.anInt924 = 0;
                client.anInt1188 = 0;
                client.anInt1155 = 0;
                client.anInt1226 = 0;
                this.resetImageProducers2();
                return;
            }
            if (n2 < 0 || n2 > 2) {
                this.isLoggingIn = false;
            }
            if (n2 == 3) {
                this.loginMessage1 = "";
                this.loginMessage2 = "@whi@Invalid username or password.";
                return;
            }
            if (n2 == 4) {
                this.loginMessage1 = "@whi@Your account has been banned.";
                this.loginMessage2 = "@whi@Visit the forums to appeal.";
                return;
            }
            if (n2 == 5) {
                this.loginMessage1 = "@whi@Your account is already logged in.";
                this.loginMessage2 = "@whi@Wait 10 seconds after a logout.";
                return;
            }
            if (n2 == 6) {
                this.loginMessage1 = "@whi@Devilishpkz has been updated!";
                this.loginMessage2 = "@whi@Please reload this page.";
                return;
            }
            if (n2 == 7) {
                this.loginMessage1 = "@whi@This world is full.";
                this.loginMessage2 = "@whi@Please use a different world.";
                return;
            }
            if (n2 == 8) {
                this.loginMessage1 = "@whi@Unable to connect.";
                this.loginMessage2 = "@whi@Login server offline.";
                return;
            }
            if (n2 == 9) {
                this.loginMessage1 = "@whi@Login limit exceeded.";
                this.loginMessage2 = "@whi@Too many connections from your address.";
                return;
            }
            if (n2 == 10) {
                this.loginMessage1 = "@whi@Unable to connect.";
                this.loginMessage2 = "@whi@Bad session id.";
                return;
            }
            if (n2 == 11) {
                this.loginMessage1 = "@whi@Login server rejected session.";
                this.loginMessage2 = "@whi@Please try again.";
                return;
            }
            if (n2 == 12) {
                this.loginMessage1 = "@whi@You need a members account to login to this world.";
                this.loginMessage2 = "@whi@Please subscribe, or use a different world.";
                return;
            }
            if (n2 == 13) {
                this.loginMessage1 = "@whi@Could not complete login.";
                this.loginMessage2 = "@whi@Please try using a different world.";
                return;
            }
            if (n2 == 14) {
                this.loginMessage1 = "@whi@The server is being updated.";
                this.loginMessage2 = "@whi@Please wait 1 minute and try again.";
                return;
            }
            if (n2 == 15) {
                this.loggedIn = true;
                this.stream.currentOffset = 0;
                this.inStream.currentOffset = 0;
                this.pktType = -1;
                this.anInt841 = -1;
                this.anInt842 = -1;
                this.anInt843 = -1;
                this.pktSize = 0;
                this.anInt1009 = 0;
                this.anInt1104 = 0;
                this.menuActionRow = 0;
                this.menuOpen = false;
                this.aLong824 = System.currentTimeMillis();
                return;
            }
            if (n2 == 16) {
                this.loginMessage1 = "@whi@Login attempts exceeded.";
                this.loginMessage2 = "@whi@Please wait 1 minute and try again.";
                return;
            }
            if (n2 == 17) {
                this.loginMessage1 = "@whi@You are standing in a members-only area.";
                this.loginMessage2 = "@whi@To play on this world move to a free area first";
                return;
            }
            if (n2 == 20) {
                this.loginMessage1 = "@whi@Invalid loginserver requested";
                this.loginMessage2 = "@whi@Please try using a different world.";
                return;
            }
            if (n2 == 21) {
                for (int read = this.socketStream.read(); read >= 0; --read) {
                    this.loginMessage1 = "@whi@You have only just left another world";
                    this.loginMessage2 = "@whi@Your profile will be transferred in: " + read + " seconds";
                    this.drawLoginScreen(true);
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (Exception ex2) {}
                }
                this.login(errorname, s, b);
                return;
            }
            if (n2 != -1) {
                this.loginMessage1 = "@whi@Unexpected server response";
                this.loginMessage2 = "@whi@Please try using a different world.";
                return;
            }
            if (n3 != 0) {
                this.loginMessage1 = "@whi@No response from server";
                this.loginMessage2 = "@whi@Please try using a different world.";
                return;
            }
            if (this.loginFailures < 4) {
                try {
                    Thread.sleep(4000L);
                }
                catch (Exception ex3) {}
                ++this.loginFailures;
                this.login(errorname, s, b);
                return;
            }
            this.loginMessage1 = "@whi@No response from loginserver";
            this.loginMessage2 = "@whi@Please wait 1 minute and try again.";
        }
        catch (IOException ex4) {
            this.loginMessage1 = "";
            this.loginMessage2 = "@whi@Error connecting to server.";
        }
    }
    
    private boolean doWalkTo(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final boolean b, final int n10) {
        final int n11 = 104;
        final int n12 = 104;
        for (int i = 0; i < n11; ++i) {
            for (int j = 0; j < n12; ++j) {
                this.anIntArrayArray901[i][j] = 0;
                this.anIntArrayArray825[i][j] = 99999999;
            }
        }
        int n13 = n9;
        int n14 = n5;
        this.anIntArrayArray901[n9][n5] = 99;
        this.anIntArrayArray825[n9][n5] = 0;
        int n15 = 0;
        int k = 0;
        this.bigX[n15] = n9;
        this.bigY[n15++] = n5;
        int n16 = 0;
        final int length = this.bigX.length;
        final int[][] anIntArrayArray294 = this.aClass11Array1230[this.plane].anIntArrayArray294;
        while (k != n15) {
            n13 = this.bigX[k];
            n14 = this.bigY[k];
            k = (k + 1) % length;
            if (n13 == n10 && n14 == n8) {
                n16 = 1;
                break;
            }
            if (n4 != 0) {
                if ((n4 < 5 || n4 == 10) && this.aClass11Array1230[this.plane].method219(n10, n13, n14, n2, n4 - 1, n8)) {
                    n16 = 1;
                    break;
                }
                if (n4 < 10 && this.aClass11Array1230[this.plane].method220(n10, n8, n14, n4 - 1, n2, n13)) {
                    n16 = 1;
                    break;
                }
            }
            if (n6 != 0 && n3 != 0 && this.aClass11Array1230[this.plane].method221(n8, n10, n13, n3, n7, n6, n14)) {
                n16 = 1;
                break;
            }
            final int n17 = this.anIntArrayArray825[n13][n14] + 1;
            if (n13 > 0 && this.anIntArrayArray901[n13 - 1][n14] == 0 && (anIntArrayArray294[n13 - 1][n14] & 0x1280108) == 0x0) {
                this.bigX[n15] = n13 - 1;
                this.bigY[n15] = n14;
                n15 = (n15 + 1) % length;
                this.anIntArrayArray901[n13 - 1][n14] = 2;
                this.anIntArrayArray825[n13 - 1][n14] = n17;
            }
            if (n13 < n11 - 1 && this.anIntArrayArray901[n13 + 1][n14] == 0 && (anIntArrayArray294[n13 + 1][n14] & 0x1280180) == 0x0) {
                this.bigX[n15] = n13 + 1;
                this.bigY[n15] = n14;
                n15 = (n15 + 1) % length;
                this.anIntArrayArray901[n13 + 1][n14] = 8;
                this.anIntArrayArray825[n13 + 1][n14] = n17;
            }
            if (n14 > 0 && this.anIntArrayArray901[n13][n14 - 1] == 0 && (anIntArrayArray294[n13][n14 - 1] & 0x1280102) == 0x0) {
                this.bigX[n15] = n13;
                this.bigY[n15] = n14 - 1;
                n15 = (n15 + 1) % length;
                this.anIntArrayArray901[n13][n14 - 1] = 1;
                this.anIntArrayArray825[n13][n14 - 1] = n17;
            }
            if (n14 < n12 - 1 && this.anIntArrayArray901[n13][n14 + 1] == 0 && (anIntArrayArray294[n13][n14 + 1] & 0x1280120) == 0x0) {
                this.bigX[n15] = n13;
                this.bigY[n15] = n14 + 1;
                n15 = (n15 + 1) % length;
                this.anIntArrayArray901[n13][n14 + 1] = 4;
                this.anIntArrayArray825[n13][n14 + 1] = n17;
            }
            if (n13 > 0 && n14 > 0 && this.anIntArrayArray901[n13 - 1][n14 - 1] == 0 && (anIntArrayArray294[n13 - 1][n14 - 1] & 0x128010E) == 0x0 && (anIntArrayArray294[n13 - 1][n14] & 0x1280108) == 0x0 && (anIntArrayArray294[n13][n14 - 1] & 0x1280102) == 0x0) {
                this.bigX[n15] = n13 - 1;
                this.bigY[n15] = n14 - 1;
                n15 = (n15 + 1) % length;
                this.anIntArrayArray901[n13 - 1][n14 - 1] = 3;
                this.anIntArrayArray825[n13 - 1][n14 - 1] = n17;
            }
            if (n13 < n11 - 1 && n14 > 0 && this.anIntArrayArray901[n13 + 1][n14 - 1] == 0 && (anIntArrayArray294[n13 + 1][n14 - 1] & 0x1280183) == 0x0 && (anIntArrayArray294[n13 + 1][n14] & 0x1280180) == 0x0 && (anIntArrayArray294[n13][n14 - 1] & 0x1280102) == 0x0) {
                this.bigX[n15] = n13 + 1;
                this.bigY[n15] = n14 - 1;
                n15 = (n15 + 1) % length;
                this.anIntArrayArray901[n13 + 1][n14 - 1] = 9;
                this.anIntArrayArray825[n13 + 1][n14 - 1] = n17;
            }
            if (n13 > 0 && n14 < n12 - 1 && this.anIntArrayArray901[n13 - 1][n14 + 1] == 0 && (anIntArrayArray294[n13 - 1][n14 + 1] & 0x1280138) == 0x0 && (anIntArrayArray294[n13 - 1][n14] & 0x1280108) == 0x0 && (anIntArrayArray294[n13][n14 + 1] & 0x1280120) == 0x0) {
                this.bigX[n15] = n13 - 1;
                this.bigY[n15] = n14 + 1;
                n15 = (n15 + 1) % length;
                this.anIntArrayArray901[n13 - 1][n14 + 1] = 6;
                this.anIntArrayArray825[n13 - 1][n14 + 1] = n17;
            }
            if (n13 >= n11 - 1 || n14 >= n12 - 1 || this.anIntArrayArray901[n13 + 1][n14 + 1] != 0 || (anIntArrayArray294[n13 + 1][n14 + 1] & 0x12801E0) != 0x0 || (anIntArrayArray294[n13 + 1][n14] & 0x1280180) != 0x0 || (anIntArrayArray294[n13][n14 + 1] & 0x1280120) != 0x0) {
                continue;
            }
            this.bigX[n15] = n13 + 1;
            this.bigY[n15] = n14 + 1;
            n15 = (n15 + 1) % length;
            this.anIntArrayArray901[n13 + 1][n14 + 1] = 12;
            this.anIntArrayArray825[n13 + 1][n14 + 1] = n17;
        }
        this.anInt1264 = 0;
        if (n16 == 0) {
            if (b) {
                int n18 = 100;
                for (int l = 1; l < 2; ++l) {
                    for (int n19 = n10 - l; n19 <= n10 + l; ++n19) {
                        for (int n20 = n8 - l; n20 <= n8 + l; ++n20) {
                            if (n19 >= 0 && n20 >= 0 && n19 < 104 && n20 < 104 && this.anIntArrayArray825[n19][n20] < n18) {
                                n18 = this.anIntArrayArray825[n19][n20];
                                n13 = n19;
                                n14 = n20;
                                this.anInt1264 = 1;
                                n16 = 1;
                            }
                        }
                    }
                    if (n16 != 0) {
                        break;
                    }
                }
            }
            if (n16 == 0) {
                return false;
            }
        }
        int n21 = 0;
        this.bigX[n21] = n13;
        this.bigY[n21++] = n14;
        int n23;
        int n22 = n23 = this.anIntArrayArray901[n13][n14];
        while (n13 != n9 || n14 != n5) {
            if (n23 != n22) {
                n22 = n23;
                this.bigX[n21] = n13;
                this.bigY[n21++] = n14;
            }
            if ((n23 & 0x2) != 0x0) {
                ++n13;
            }
            else if ((n23 & 0x8) != 0x0) {
                --n13;
            }
            if ((n23 & 0x1) != 0x0) {
                ++n14;
            }
            else if ((n23 & 0x4) != 0x0) {
                --n14;
            }
            n23 = this.anIntArrayArray901[n13][n14];
        }
        if (n21 > 0) {
            int n24 = n21;
            if (n24 > 25) {
                n24 = 25;
            }
            --n21;
            final int n25 = this.bigX[n21];
            final int n26 = this.bigY[n21];
            client.anInt1288 += n24;
            if (client.anInt1288 >= 92) {
                this.stream.createFrame(36);
                this.stream.writeDWord(0);
                client.anInt1288 = 0;
            }
            if (n == 0) {
                this.stream.createFrame(164);
                this.stream.writeWordBigEndian(n24 + n24 + 3);
            }
            if (n == 1) {
                this.stream.createFrame(248);
                this.stream.writeWordBigEndian(n24 + n24 + 3 + 14);
            }
            if (n == 2) {
                this.stream.createFrame(98);
                this.stream.writeWordBigEndian(n24 + n24 + 3);
            }
            this.stream.method433(n25 + this.baseX);
            this.destX = this.bigX[0];
            this.destY = this.bigY[0];
            for (int n27 = 1; n27 < n24; ++n27) {
                --n21;
                this.stream.writeWordBigEndian(this.bigX[n21] - n25);
                this.stream.writeWordBigEndian(this.bigY[n21] - n26);
            }
            this.stream.method431(n26 + this.baseY);
            this.stream.method424((super.keyArray[5] == 1) ? 1 : 0);
            return true;
        }
        return n != 1;
    }
    
    private void method86(final Stream stream) {
        for (int i = 0; i < this.anInt893; ++i) {
            final NPC npc = this.npcArray[this.anIntArray894[i]];
            final int unsignedByte = stream.readUnsignedByte();
            if ((unsignedByte & 0x10) != 0x0) {
                int method434 = stream.method434();
                if (method434 == 65535) {
                    method434 = -1;
                }
                final int unsignedByte2 = stream.readUnsignedByte();
                if (method434 == npc.anim && method434 != -1) {
                    final int anInt365 = Animation.anims[method434].anInt365;
                    if (anInt365 == 1) {
                        npc.anInt1527 = 0;
                        npc.anInt1528 = 0;
                        npc.anInt1529 = unsignedByte2;
                        npc.anInt1530 = 0;
                    }
                    if (anInt365 == 2) {
                        npc.anInt1530 = 0;
                    }
                }
                else if (method434 == -1 || npc.anim == -1 || Animation.anims[method434].anInt359 >= Animation.anims[npc.anim].anInt359) {
                    npc.anim = method434;
                    npc.anInt1527 = 0;
                    npc.anInt1528 = 0;
                    npc.anInt1529 = unsignedByte2;
                    npc.anInt1530 = 0;
                    npc.anInt1542 = npc.smallXYIndex;
                }
            }
            if ((unsignedByte & 0x8) != 0x0) {
                npc.updateHitData(stream.method427(), stream.method426(), client.loopCycle);
                npc.loopCycleStatus = client.loopCycle + 300;
                npc.currentHealth = stream.method426();
                npc.maxHealth = stream.readUnsignedByte();
            }
            if ((unsignedByte & 0x80) != 0x0) {
                npc.anInt1520 = stream.readUnsignedWord();
                final int dWord = stream.readDWord();
                npc.anInt1524 = dWord >> 16;
                npc.anInt1523 = client.loopCycle + (dWord & 0xFFFF);
                npc.anInt1521 = 0;
                npc.anInt1522 = 0;
                if (npc.anInt1523 > client.loopCycle) {
                    npc.anInt1521 = -1;
                }
                if (npc.anInt1520 == 65535) {
                    npc.anInt1520 = -1;
                }
            }
            if ((unsignedByte & 0x20) != 0x0) {
                npc.interactingEntity = stream.readUnsignedWord();
                if (npc.interactingEntity == 65535) {
                    npc.interactingEntity = -1;
                }
            }
            if ((unsignedByte & 0x1) != 0x0) {
                npc.textSpoken = stream.readString();
                npc.textCycle = 100;
            }
            if ((unsignedByte & 0x40) != 0x0) {
                npc.updateHitData(stream.method428(), stream.method427(), client.loopCycle);
                npc.loopCycleStatus = client.loopCycle + 300;
                npc.currentHealth = stream.method428();
                npc.maxHealth = stream.method427();
            }
            if ((unsignedByte & 0x2) != 0x0) {
                npc.desc = EntityDef.forID(stream.method436());
                npc.anInt1540 = npc.desc.aByte68;
                npc.anInt1504 = npc.desc.anInt79;
                npc.anInt1554 = npc.desc.anInt67;
                npc.anInt1555 = npc.desc.anInt58;
                npc.anInt1556 = npc.desc.anInt83;
                npc.anInt1557 = npc.desc.anInt55;
                npc.anInt1511 = npc.desc.anInt77;
            }
            if ((unsignedByte & 0x4) != 0x0) {
                npc.anInt1538 = stream.method434();
                npc.anInt1539 = stream.method434();
            }
        }
    }
    
    private void buildAtNPCMenu(EntityDef method161, final int n, final int n2, final int n3) {
        if (this.menuActionRow >= 400) {
            return;
        }
        if (method161.childrenIDs != null) {
            method161 = method161.method161();
        }
        if (method161 == null) {
            return;
        }
        if (!method161.aBoolean84) {
            return;
        }
        String s = method161.name;
        if (method161.combatLevel != 0) {
            s = s + combatDiffColor(client.myPlayer.combatLevel, method161.combatLevel) + " (level-" + method161.combatLevel + ")";
        }
        if (this.itemSelected == 1) {
            this.menuActionName[this.menuActionRow] = "Use " + this.selectedItemName + " with @yel@" + s;
            this.menuActionID[this.menuActionRow] = 582;
            this.menuActionCmd1[this.menuActionRow] = n;
            this.menuActionCmd2[this.menuActionRow] = n3;
            this.menuActionCmd3[this.menuActionRow] = n2;
            ++this.menuActionRow;
            return;
        }
        if (this.spellSelected == 1) {
            if ((this.spellUsableOn & 0x2) == 0x2) {
                this.menuActionName[this.menuActionRow] = this.spellTooltip + " @yel@" + s;
                this.menuActionID[this.menuActionRow] = 413;
                this.menuActionCmd1[this.menuActionRow] = n;
                this.menuActionCmd2[this.menuActionRow] = n3;
                this.menuActionCmd3[this.menuActionRow] = n2;
                ++this.menuActionRow;
            }
        }
        else {
            if (method161.actions != null) {
                for (int i = 4; i >= 0; --i) {
                    if (method161.actions[i] != null && !method161.actions[i].equalsIgnoreCase("attack")) {
                        this.menuActionName[this.menuActionRow] = method161.actions[i] + " @yel@" + s;
                        if (i == 0) {
                            this.menuActionID[this.menuActionRow] = 20;
                        }
                        if (i == 1) {
                            this.menuActionID[this.menuActionRow] = 412;
                        }
                        if (i == 2) {
                            this.menuActionID[this.menuActionRow] = 225;
                        }
                        if (i == 3) {
                            this.menuActionID[this.menuActionRow] = 965;
                        }
                        if (i == 4) {
                            this.menuActionID[this.menuActionRow] = 478;
                        }
                        this.menuActionCmd1[this.menuActionRow] = n;
                        this.menuActionCmd2[this.menuActionRow] = n3;
                        this.menuActionCmd3[this.menuActionRow] = n2;
                        ++this.menuActionRow;
                    }
                }
            }
            if (method161.actions != null) {
                for (int j = 4; j >= 0; --j) {
                    if (method161.actions[j] != null && method161.actions[j].equalsIgnoreCase("attack")) {
                        int n4 = 0;
                        if (method161.combatLevel > client.myPlayer.combatLevel) {
                            n4 = 2000;
                        }
                        this.menuActionName[this.menuActionRow] = method161.actions[j] + " @yel@" + s;
                        if (j == 0) {
                            this.menuActionID[this.menuActionRow] = 20 + n4;
                        }
                        if (j == 1) {
                            this.menuActionID[this.menuActionRow] = 412 + n4;
                        }
                        if (j == 2) {
                            this.menuActionID[this.menuActionRow] = 225 + n4;
                        }
                        if (j == 3) {
                            this.menuActionID[this.menuActionRow] = 965 + n4;
                        }
                        if (j == 4) {
                            this.menuActionID[this.menuActionRow] = 478 + n4;
                        }
                        this.menuActionCmd1[this.menuActionRow] = n;
                        this.menuActionCmd2[this.menuActionRow] = n3;
                        this.menuActionCmd3[this.menuActionRow] = n2;
                        ++this.menuActionRow;
                    }
                }
            }
            this.menuActionName[this.menuActionRow] = "Examine @yel@" + s;
            this.menuActionID[this.menuActionRow] = 1025;
            this.menuActionCmd1[this.menuActionRow] = n;
            this.menuActionCmd2[this.menuActionRow] = n3;
            this.menuActionCmd3[this.menuActionRow] = n2;
            ++this.menuActionRow;
        }
    }
    
    private void buildAtPlayerMenu(final int n, final int n2, final Player player, final int n3) {
        if (player == client.myPlayer) {
            return;
        }
        if (this.menuActionRow >= 400) {
            return;
        }
        String s;
        if (player.skill == 0) {
            s = player.name + combatDiffColor(client.myPlayer.combatLevel, player.combatLevel) + " (level-" + player.combatLevel + ")";
        }
        else {
            s = "@or2@" + this.loyaltyRank(player.skill) + "@whi@ " + player.name + combatDiffColor(client.myPlayer.combatLevel, player.combatLevel) + " (level-" + player.combatLevel + ")";
        }
        if (this.itemSelected == 1) {
            this.menuActionName[this.menuActionRow] = "Use " + this.selectedItemName + " with @whi@" + s;
            this.menuActionID[this.menuActionRow] = 491;
            this.menuActionCmd1[this.menuActionRow] = n2;
            this.menuActionCmd2[this.menuActionRow] = n;
            this.menuActionCmd3[this.menuActionRow] = n3;
            ++this.menuActionRow;
        }
        else if (this.spellSelected == 1) {
            if ((this.spellUsableOn & 0x8) == 0x8) {
                this.menuActionName[this.menuActionRow] = this.spellTooltip + " @whi@" + s;
                this.menuActionID[this.menuActionRow] = 365;
                this.menuActionCmd1[this.menuActionRow] = n2;
                this.menuActionCmd2[this.menuActionRow] = n;
                this.menuActionCmd3[this.menuActionRow] = n3;
                ++this.menuActionRow;
            }
        }
        else {
            for (int i = 4; i >= 0; --i) {
                if (this.atPlayerActions[i] != null) {
                    this.menuActionName[this.menuActionRow] = this.atPlayerActions[i] + " @whi@" + s;
                    int n4 = 0;
                    if (this.atPlayerActions[i].equalsIgnoreCase("attack")) {
                        if (player.combatLevel > client.myPlayer.combatLevel) {
                            n4 = 2000;
                        }
                        if (client.myPlayer.team != 0 && player.team != 0) {
                            if (client.myPlayer.team == player.team) {
                                n4 = 2000;
                            }
                            else {
                                n4 = 0;
                            }
                        }
                    }
                    else if (this.atPlayerArray[i]) {
                        n4 = 2000;
                    }
                    if (i == 0) {
                        this.menuActionID[this.menuActionRow] = 561 + n4;
                    }
                    if (i == 1) {
                        this.menuActionID[this.menuActionRow] = 779 + n4;
                    }
                    if (i == 2) {
                        this.menuActionID[this.menuActionRow] = 27 + n4;
                    }
                    if (i == 3) {
                        this.menuActionID[this.menuActionRow] = 577 + n4;
                    }
                    if (i == 4) {
                        this.menuActionID[this.menuActionRow] = 729 + n4;
                    }
                    this.menuActionCmd1[this.menuActionRow] = n2;
                    this.menuActionCmd2[this.menuActionRow] = n;
                    this.menuActionCmd3[this.menuActionRow] = n3;
                    ++this.menuActionRow;
                }
            }
        }
        for (int j = 0; j < this.menuActionRow; ++j) {
            if (this.menuActionID[j] == 516) {
                this.menuActionName[j] = "Walk here @whi@" + s;
                return;
            }
        }
    }
    
    private void method89(final Class30_Sub1 class30_Sub1) {
        int n = 0;
        int anInt1299 = -1;
        int anInt1300 = 0;
        int anInt1301 = 0;
        if (class30_Sub1.anInt1296 == 0) {
            n = this.worldController.method300(class30_Sub1.anInt1295, class30_Sub1.anInt1297, class30_Sub1.anInt1298);
        }
        if (class30_Sub1.anInt1296 == 1) {
            n = this.worldController.method301(class30_Sub1.anInt1295, class30_Sub1.anInt1297, class30_Sub1.anInt1298);
        }
        if (class30_Sub1.anInt1296 == 2) {
            n = this.worldController.method302(class30_Sub1.anInt1295, class30_Sub1.anInt1297, class30_Sub1.anInt1298);
        }
        if (class30_Sub1.anInt1296 == 3) {
            n = this.worldController.method303(class30_Sub1.anInt1295, class30_Sub1.anInt1297, class30_Sub1.anInt1298);
        }
        if (n != 0) {
            final int method304 = this.worldController.method304(class30_Sub1.anInt1295, class30_Sub1.anInt1297, class30_Sub1.anInt1298, n);
            anInt1299 = (n >> 14 & 0x7FFF);
            anInt1300 = (method304 & 0x1F);
            anInt1301 = method304 >> 6;
        }
        class30_Sub1.anInt1299 = anInt1299;
        class30_Sub1.anInt1301 = anInt1300;
        class30_Sub1.anInt1300 = anInt1301;
    }
    
    private void method90() {
        for (int i = 0; i < this.anInt1062; ++i) {
            if (this.anIntArray1250[i] <= 0) {
                boolean b = false;
                try {
                    if (this.anIntArray1207[i] == this.anInt874 && this.anIntArray1241[i] == this.anInt1289) {
                        if (!this.replayWave()) {
                            b = true;
                        }
                    }
                    else {
                        final Stream method241 = Sounds.method241(this.anIntArray1241[i], this.anIntArray1207[i]);
                        if (System.currentTimeMillis() + method241.currentOffset / 22 > this.aLong1172 + this.anInt1257 / 22) {
                            this.anInt1257 = method241.currentOffset;
                            this.aLong1172 = System.currentTimeMillis();
                            if (this.saveWave(method241.buffer, method241.currentOffset)) {
                                this.anInt874 = this.anIntArray1207[i];
                                this.anInt1289 = this.anIntArray1241[i];
                            }
                            else {
                                b = true;
                            }
                        }
                    }
                }
                catch (Exception ex) {}
                if (!b || this.anIntArray1250[i] == -5) {
                    --this.anInt1062;
                    for (int j = i; j < this.anInt1062; ++j) {
                        this.anIntArray1207[j] = this.anIntArray1207[j + 1];
                        this.anIntArray1241[j] = this.anIntArray1241[j + 1];
                        this.anIntArray1250[j] = this.anIntArray1250[j + 1];
                    }
                    --i;
                }
                else {
                    this.anIntArray1250[i] = -5;
                }
            }
            else {
                final int[] anIntArray1250 = this.anIntArray1250;
                final int n = i;
                --anIntArray1250[n];
            }
        }
        if (this.prevSong > 0) {
            this.prevSong -= 20;
            if (this.prevSong < 0) {
                this.prevSong = 0;
            }
            if (this.prevSong == 0 && this.musicEnabled && !client.lowMem) {
                this.nextSong = this.currentSong;
                this.songChanging = true;
                this.onDemandFetcher.method558(2, this.nextSong);
            }
        }
    }
    
    private boolean replayWave() {
        return SignLink.wavereplay();
    }
    
    private boolean saveWave(final byte[] array, final int n) {
        return array == null || SignLink.wavesave(array, n);
    }
    
    public void cachePack() {
        for (int i = 0; i <= 100000; ++i) {
            final byte[] model2 = this.getModel2(i);
            if (model2 != null && model2.length > 0) {
                this.decompressors[1].method234(model2.length, model2, i);
                System.out.println("Model: " + i + " has been cachepacked!");
            }
        }
    }
    
    public byte[] getModel2(final int n) {
        try {
            final File file = new File(SignLink.findcachedir() + "Models/" + n + ".gz");
            final byte[] array = new byte[(int)file.length()];
            final FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(array);
            fileInputStream.close();
            return array;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public void preloadModels() {
        final File[] listFiles = new File(SignLink.findcachedir() + "Models/").listFiles();
        for (int i = 0; i < listFiles.length; ++i) {
            final String name = listFiles[i].getName();
            Model.method460(ReadFile(SignLink.findcachedir() + "Models/" + name), Integer.parseInt(getFileNameWithoutExtension(name)));
        }
    }
    
    @Override
    void startUp() {
        this.setFocusTraversalKeysEnabled(false);
        this.drawSmoothLoading(20, "Starting up");
        if (SignLink.sunjava) {
            super.minDelay = 5;
        }
        client.aBoolean993 = true;
        this.getDocumentBaseHost();
        if (SignLink.cache_dat != null) {
            for (int i = 0; i < 5; ++i) {
                this.decompressors[i] = new Decompressor(SignLink.cache_dat, SignLink.cache_idx[i], i + 1);
            }
        }
        try {
            this.titleStreamLoader = this.streamLoaderForName(1, "title screen", "title", this.expectedCRCs[1], 25);
            this.smallText = new TextDrawingArea(false, "p11_full", this.titleStreamLoader);
            this.aTextDrawingArea_1271 = new TextDrawingArea(false, "p12_full", this.titleStreamLoader);
            this.chatTextDrawingArea = new TextDrawingArea(false, "b12_full", this.titleStreamLoader);
            final TextDrawingArea textDrawingArea = new TextDrawingArea(true, "q8_full", this.titleStreamLoader);
            this.newSmallFont = new TextDrawingArea2(false, "p11_full", this.titleStreamLoader);
            this.newRegularFont = new TextDrawingArea2(false, "p12_full", this.titleStreamLoader);
            this.newBoldFont = new TextDrawingArea2(false, "b12_full", this.titleStreamLoader);
            this.newFancyFont = new TextDrawingArea2(true, "q8_full", this.titleStreamLoader);
            this.drawLogo();
            this.loadTitleScreen();
            final StreamLoader streamLoaderForName = this.streamLoaderForName(2, "config", "config", this.expectedCRCs[2], 30);
            final StreamLoader streamLoaderForName2 = this.streamLoaderForName(3, "interface", "interface", this.expectedCRCs[3], 35);
            final StreamLoader streamLoaderForName3 = this.streamLoaderForName(4, "2d graphics", "media", this.expectedCRCs[4], 40);
            final StreamLoader streamLoaderForName4 = this.streamLoaderForName(6, "textures", "textures", this.expectedCRCs[6], 45);
            this.streamLoaderForName(7, "chat system", "wordenc", this.expectedCRCs[7], 50);
            final StreamLoader streamLoaderForName5 = this.streamLoaderForName(8, "sound effects", "sounds", this.expectedCRCs[8], 55);
            this.byteGroundArray = new byte[4][104][104];
            this.intGroundArray = new int[4][105][105];
            this.worldController = new WorldController(this.intGroundArray);
            for (int j = 0; j < 4; ++j) {
                this.aClass11Array1230[j] = new Class11();
            }
            this.aClass30_Sub2_Sub1_Sub1_1263 = new Sprite(512, 512);
            final StreamLoader streamLoaderForName6 = this.streamLoaderForName(5, "update list", "versionlist", this.expectedCRCs[5], 60);
            this.drawSmoothLoading(60, "Connecting to update server");
            (this.onDemandFetcher = new OnDemandFetcher()).start(streamLoaderForName6, this);
            Class36.method528(this.onDemandFetcher.getAnimCount());
            Model.method459(this.onDemandFetcher.getModelCount(), this.onDemandFetcher);
            ModelDecompressor.loadModelz5();
            this.musics();
            DataBase.loadAnimations();
            if (!client.lowMem) {
                this.nextSong = 0;
                try {
                    this.nextSong = Integer.parseInt(this.getParameter("music"));
                }
                catch (Exception ex5) {}
                while (this.onDemandFetcher.getNodeCount() > 0) {
                    this.processOnDemandQueue();
                    try {
                        Thread.sleep(100L);
                    }
                    catch (Exception ex6) {}
                    if (this.onDemandFetcher.anInt1349 > 3) {
                        return;
                    }
                }
            }
            this.loadNewMap();
            this.drawSmoothLoading(80, "Unpacking media");
            try {
                this.loginBox = new Sprite("title");
                this.loginBoxHover = new Sprite("box_hover");
                this.loginHover = new Sprite("login_hover");
                this.loginHoverWorld = new Sprite("login_hover2");
                this.HPBarFull = new Sprite("bar 0");
                this.HPBarEmpty = new Sprite("bar 1");
                this.hitMarks[20] = new Sprite("hitMark");
                this.hitMarks[21] = new Sprite("hitMark2");
                this.hitMarks[22] = new Sprite("hitMark3");
                for (int k = 0; k <= 3; ++k) {
                    this.combatIcons[k] = new Sprite("Player/combatIcon " + k + "");
                }
                for (int l = 0; l < 16; ++l) {
                    this.fsSprite[l] = new Sprite("fullscreen/" + l);
                }
                for (int n = 0; n < 3; ++n) {
                    this.counter[n] = new Sprite("Gameframe/Orbs/XP " + n);
                }
                for (int n2 = 0; n2 < 2; ++n2) {
                    this.chatToggle[n2] = new Sprite("fullscreen/chattoggle " + n2);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.magicAuto = new Sprite("Misc/magicAuto");
            this.pvpIcon[0] = new Sprite("Gameframe/pvp0");
            this.pvpIcon[1] = new Sprite("Gameframe/pvp1");
            this.pvpIcon[2] = new Sprite("Gameframe/pvp2");
            this.bounty = new Sprite("./Sprites/bounty/bounty.PNG");
            this.bounty2 = new Sprite("./Sprites/bounty/bounty2.PNG");
            this.penal = new Sprite("./Sprites/bounty/Penal.PNG");
            this.penal2 = new Sprite("./Sprites/bounty/Penal2.PNG");
            this.logoutX = new Sprite("./Sprites/logout.png");
            this.chatArea = new Sprite("chatarea");
            this.tabArea = new Sprite("tabarea");
            this.tabHover = new Sprite("tabhover");
            this.tabClicked = new Sprite("tabclicked");
            for (int n3 = 0; n3 <= 14; ++n3) {
                this.newSideIcons[n3] = new Sprite("icon " + n3);
            }
            this.tabArea2 = new Sprite("tabarea2");
            this.mapArea = new Sprite("maparea");
            this.backgroundFix = new Sprite("background");
            for (int n4 = 0; n4 < 16; ++n4) {
                this.ORBS[n4] = new Sprite("Gameframe/Orbs/ORBS " + n4);
            }
            for (int n5 = 0; n5 <= 2; ++n5) {
                this.globe[n5] = new Sprite("Gameframe/Globe " + n5 + "");
            }
            this.customMapArea = new Sprite("Gameframe/Black 1");
            this.CustomMapBack = new Sprite("Gameframe/Mapback 1");
            this.multiOverlay = new Sprite(streamLoaderForName3, "overlay_multiway", 0);
            this.mapBack = new Background(streamLoaderForName3, "mapback", 0);
            for (int n6 = 0; n6 <= 3; ++n6) {
                this.chatButtons[n6] = new Sprite(streamLoaderForName3, "chatbuttons", n6);
            }
            for (int n7 = 0; n7 <= 12; ++n7) {
                this.sideIcons[n7] = new Sprite(streamLoaderForName3, "sideicons", n7);
            }
            this.sideIcons[13] = new Sprite("Misc/Summon");
            for (int n8 = 0; n8 < 5; ++n8) {
                this.redStones[n8] = new Sprite("redstones " + n8);
            }
            this.compass = new Sprite(streamLoaderForName3, "compass", 0);
            (this.mapEdge = new Sprite(streamLoaderForName3, "mapedge", 0)).method345();
            try {
                for (int n9 = 0; n9 < 100; ++n9) {
                    this.mapScenes[n9] = new Background(streamLoaderForName3, "mapscene", n9);
                }
            }
            catch (Exception ex7) {}
            try {
                for (int n10 = 0; n10 < 100; ++n10) {
                    this.mapFunctions[n10] = new Sprite(streamLoaderForName3, "mapfunction", n10);
                }
            }
            catch (Exception ex8) {}
            try {
                for (int n11 = 0; n11 < 20; ++n11) {
                    this.hitMarks[n11] = new Sprite(streamLoaderForName3, "hitmarks", n11);
                }
            }
            catch (Exception ex9) {}
            try {
                for (int n12 = 0; n12 < 6; ++n12) {
                    this.headIconsHint[n12] = new Sprite(streamLoaderForName3, "headicons_hint", n12);
                }
            }
            catch (Exception ex10) {}
            try {
                for (int n13 = 0; n13 < 8; ++n13) {
                    this.headIcons[n13] = new Sprite(streamLoaderForName3, "headicons_prayer", n13);
                }
                for (int n14 = 9; n14 < 18; ++n14) {
                    this.headIcons[n14] = new Sprite("Prayer1/Prayer " + n14);
                }
                for (int n15 = 0; n15 < 3; ++n15) {
                    this.skullIcons[n15] = new Sprite(streamLoaderForName3, "headicons_pk", n15);
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            this.mapFlag = new Sprite(streamLoaderForName3, "mapmarker", 0);
            this.mapMarker = new Sprite(streamLoaderForName3, "mapmarker", 1);
            for (int n16 = 0; n16 < 8; ++n16) {
                this.crosses[n16] = new Sprite(streamLoaderForName3, "cross", n16);
            }
            this.mapDotItem = new Sprite(streamLoaderForName3, "mapdots", 0);
            this.mapDotNPC = new Sprite(streamLoaderForName3, "mapdots", 1);
            this.mapDotPlayer = new Sprite(streamLoaderForName3, "mapdots", 2);
            this.mapDotFriend = new Sprite(streamLoaderForName3, "mapdots", 3);
            this.mapDotTeam = new Sprite(streamLoaderForName3, "mapdots", 4);
            this.mapDotClan = new Sprite(streamLoaderForName3, "mapdots", 5);
            this.scrollBar1 = new Sprite(streamLoaderForName3, "scrollbar", 0);
            this.scrollBar2 = new Sprite(streamLoaderForName3, "scrollbar", 1);
            this.modIcons[0] = new Sprite("Gameframe/MODICONS 1");
            this.modIcons[1] = new Sprite("Gameframe/MODICONS 3");
            this.modIcons[2] = new Sprite("Gameframe/MODICONS 2");
            this.modIcons[3] = new Sprite("Gameframe/MODICONS 0");
            final Sprite sprite = new Sprite(streamLoaderForName3, "screenframe", 0);
            this.leftFrame = new RSImageProducer(sprite.myWidth, sprite.myHeight, this.getGameComponent());
            sprite.method346(0, 0);
            final Sprite sprite2 = new Sprite(streamLoaderForName3, "screenframe", 1);
            this.topFrame = new RSImageProducer(sprite2.myWidth, sprite2.myHeight, this.getGameComponent());
            sprite2.method346(0, 0);
            final Sprite sprite3 = new Sprite(streamLoaderForName3, "screenframe", 2);
            this.rightFrame = new RSImageProducer(sprite3.myWidth, sprite3.myHeight, this.getGameComponent());
            sprite3.method346(0, 0);
            final Sprite sprite4 = new Sprite(streamLoaderForName3, "mapedge", 0);
            this.mapEdgeIP = new RSImageProducer(sprite4.myWidth, sprite4.myHeight, this.getGameComponent());
            sprite4.method346(0, 0);
            final int n17 = (int)(Math.random() * 21.0) - 10;
            final int n18 = (int)(Math.random() * 21.0) - 10;
            final int n19 = (int)(Math.random() * 21.0) - 10;
            final int n20 = (int)(Math.random() * 41.0) - 20;
            for (int n21 = 0; n21 < 100; ++n21) {
                if (this.mapFunctions[n21] != null) {
                    this.mapFunctions[n21].method344(n17 + n20, n18 + n20, n19 + n20);
                }
                if (this.mapScenes[n21] != null) {
                    this.mapScenes[n21].method360(n17 + n20, n18 + n20, n19 + n20);
                }
            }
            this.drawSmoothLoading(166, "Unpacking textures");
            Texture.method368(streamLoaderForName4);
            Texture.method372(0.8);
            Texture.method367();
            this.drawSmoothLoading(172, "Unpacking config");
            try {
                Animation.unpackConfig(streamLoaderForName);
                ObjectDef.unpackConfig(streamLoaderForName);
                Flo.unpackConfig(streamLoaderForName);
                ItemDef.unpackConfig(streamLoaderForName);
                EntityDef.unpackConfig(streamLoaderForName);
                IDK.unpackConfig(streamLoaderForName);
                SpotAnim.unpackConfig(streamLoaderForName);
                Varp.unpackConfig(streamLoaderForName);
                VarBit.unpackConfig(streamLoaderForName);
                ItemDef.isMembers = true;
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
            this.drawSmoothLoading(180, "Unpacking sounds");
            Sounds.unpack(new Stream(streamLoaderForName5.getDataForName("sounds.dat")));
            this.drawSmoothLoading(190, "Unpacking interfaces");
            RSInterface.unpack(streamLoaderForName2, RSInterface.fonts = new TextDrawingArea[] { this.smallText, this.aTextDrawingArea_1271, this.chatTextDrawingArea, textDrawingArea }, streamLoaderForName3);
            this.drawSmoothLoading(200, "Preparing game engine");
            for (int n22 = 0; n22 < 33; ++n22) {
                int n23 = 999;
                int n24 = 0;
                for (int n25 = 0; n25 < 34; ++n25) {
                    if (this.mapBack.aByteArray1450[n25 + n22 * this.mapBack.anInt1452] == 0) {
                        if (n23 == 999) {
                            n23 = n25;
                        }
                    }
                    else if (n23 != 999) {
                        n24 = n25;
                        break;
                    }
                }
                this.anIntArray968[n22] = n23;
                this.anIntArray1057[n22] = n24 - n23;
            }
            this.anIntArray1229[0] = 22;
            this.anIntArray1229[1] = 34;
            this.anIntArray1229[2] = 42;
            this.anIntArray1229[3] = 50;
            this.anIntArray1229[4] = 56;
            this.anIntArray1229[5] = 60;
            this.anIntArray1229[6] = 66;
            this.anIntArray1229[7] = 70;
            this.anIntArray1229[8] = 74;
            this.anIntArray1229[9] = 78;
            this.anIntArray1229[10] = 82;
            this.anIntArray1229[11] = 84;
            this.anIntArray1229[12] = 88;
            this.anIntArray1229[13] = 90;
            this.anIntArray1229[14] = 94;
            this.anIntArray1229[15] = 96;
            this.anIntArray1229[16] = 98;
            this.anIntArray1229[17] = 102;
            this.anIntArray1229[18] = 104;
            this.anIntArray1229[19] = 106;
            this.anIntArray1229[20] = 108;
            this.anIntArray1229[21] = 110;
            this.anIntArray1229[22] = 112;
            this.anIntArray1229[23] = 114;
            this.anIntArray1229[24] = 116;
            this.anIntArray1229[25] = 118;
            this.anIntArray1229[26] = 120;
            this.anIntArray1229[27] = 122;
            this.anIntArray1229[28] = 122;
            this.anIntArray1229[29] = 124;
            this.anIntArray1229[30] = 126;
            this.anIntArray1229[31] = 128;
            this.anIntArray1229[32] = 128;
            this.anIntArray1229[33] = 130;
            this.anIntArray1229[34] = 132;
            this.anIntArray1229[35] = 132;
            this.anIntArray1229[36] = 134;
            this.anIntArray1229[37] = 136;
            this.anIntArray1229[38] = 136;
            this.anIntArray1229[39] = 138;
            this.anIntArray1229[40] = 138;
            this.anIntArray1229[41] = 140;
            this.anIntArray1229[42] = 140;
            this.anIntArray1229[43] = 142;
            this.anIntArray1229[44] = 142;
            this.anIntArray1229[45] = 144;
            this.anIntArray1229[46] = 144;
            this.anIntArray1229[47] = 144;
            this.anIntArray1229[48] = 146;
            this.anIntArray1229[49] = 146;
            this.anIntArray1229[50] = 148;
            this.anIntArray1229[51] = 148;
            this.anIntArray1229[52] = 148;
            this.anIntArray1229[53] = 150;
            this.anIntArray1229[54] = 150;
            this.anIntArray1229[55] = 150;
            this.anIntArray1229[56] = 150;
            this.anIntArray1229[57] = 152;
            this.anIntArray1229[58] = 152;
            this.anIntArray1229[59] = 152;
            this.anIntArray1229[60] = 152;
            this.anIntArray1229[61] = 152;
            this.anIntArray1229[62] = 154;
            this.anIntArray1229[63] = 154;
            this.anIntArray1229[64] = 154;
            this.anIntArray1229[65] = 154;
            this.anIntArray1229[66] = 154;
            this.anIntArray1229[67] = 154;
            this.anIntArray1229[68] = 156;
            this.anIntArray1229[69] = 156;
            this.anIntArray1229[70] = 156;
            this.anIntArray1229[71] = 156;
            this.anIntArray1229[72] = 156;
            this.anIntArray1229[73] = 156;
            this.anIntArray1229[74] = 156;
            this.anIntArray1229[75] = 156;
            this.anIntArray1229[150] = 22;
            this.anIntArray1229[149] = 34;
            this.anIntArray1229[148] = 42;
            this.anIntArray1229[147] = 50;
            this.anIntArray1229[146] = 56;
            this.anIntArray1229[145] = 60;
            this.anIntArray1229[144] = 66;
            this.anIntArray1229[143] = 70;
            this.anIntArray1229[142] = 74;
            this.anIntArray1229[141] = 78;
            this.anIntArray1229[140] = 82;
            this.anIntArray1229[139] = 84;
            this.anIntArray1229[138] = 88;
            this.anIntArray1229[137] = 90;
            this.anIntArray1229[136] = 94;
            this.anIntArray1229[135] = 96;
            this.anIntArray1229[134] = 98;
            this.anIntArray1229[133] = 102;
            this.anIntArray1229[132] = 104;
            this.anIntArray1229[131] = 106;
            this.anIntArray1229[130] = 108;
            this.anIntArray1229[129] = 110;
            this.anIntArray1229[128] = 112;
            this.anIntArray1229[127] = 114;
            this.anIntArray1229[126] = 116;
            this.anIntArray1229[125] = 118;
            this.anIntArray1229[124] = 120;
            this.anIntArray1229[123] = 122;
            this.anIntArray1229[122] = 122;
            this.anIntArray1229[121] = 124;
            this.anIntArray1229[120] = 126;
            this.anIntArray1229[119] = 128;
            this.anIntArray1229[118] = 128;
            this.anIntArray1229[117] = 130;
            this.anIntArray1229[116] = 132;
            this.anIntArray1229[115] = 132;
            this.anIntArray1229[114] = 134;
            this.anIntArray1229[113] = 136;
            this.anIntArray1229[112] = 136;
            this.anIntArray1229[111] = 138;
            this.anIntArray1229[110] = 138;
            this.anIntArray1229[109] = 140;
            this.anIntArray1229[108] = 140;
            this.anIntArray1229[107] = 142;
            this.anIntArray1229[106] = 142;
            this.anIntArray1229[105] = 144;
            this.anIntArray1229[104] = 144;
            this.anIntArray1229[103] = 144;
            this.anIntArray1229[102] = 146;
            this.anIntArray1229[101] = 146;
            this.anIntArray1229[100] = 148;
            this.anIntArray1229[99] = 148;
            this.anIntArray1229[98] = 148;
            this.anIntArray1229[97] = 150;
            this.anIntArray1229[96] = 150;
            this.anIntArray1229[95] = 150;
            this.anIntArray1229[94] = 150;
            this.anIntArray1229[93] = 152;
            this.anIntArray1229[92] = 152;
            this.anIntArray1229[91] = 152;
            this.anIntArray1229[90] = 152;
            this.anIntArray1229[89] = 152;
            this.anIntArray1229[88] = 154;
            this.anIntArray1229[87] = 154;
            this.anIntArray1229[86] = 154;
            this.anIntArray1229[85] = 154;
            this.anIntArray1229[84] = 154;
            this.anIntArray1229[83] = 154;
            this.anIntArray1229[82] = 156;
            this.anIntArray1229[81] = 156;
            this.anIntArray1229[80] = 156;
            this.anIntArray1229[79] = 156;
            this.anIntArray1229[78] = 156;
            this.anIntArray1229[77] = 156;
            this.anIntArray1229[76] = 156;
            this.anIntArray1229[75] = 156;
            this.anIntArray1052[0] = 67;
            this.anIntArray1052[1] = 61;
            this.anIntArray1052[2] = 57;
            this.anIntArray1052[3] = 53;
            this.anIntArray1052[4] = 50;
            this.anIntArray1052[5] = 48;
            this.anIntArray1052[6] = 45;
            this.anIntArray1052[7] = 43;
            this.anIntArray1052[8] = 41;
            this.anIntArray1052[9] = 39;
            this.anIntArray1052[10] = 37;
            this.anIntArray1052[11] = 36;
            this.anIntArray1052[12] = 34;
            this.anIntArray1052[13] = 33;
            this.anIntArray1052[14] = 31;
            this.anIntArray1052[15] = 30;
            this.anIntArray1052[16] = 29;
            this.anIntArray1052[17] = 28;
            this.anIntArray1052[18] = 27;
            this.anIntArray1052[19] = 26;
            this.anIntArray1052[20] = 25;
            this.anIntArray1052[21] = 24;
            this.anIntArray1052[22] = 23;
            this.anIntArray1052[23] = 22;
            this.anIntArray1052[24] = 21;
            this.anIntArray1052[25] = 20;
            this.anIntArray1052[26] = 19;
            this.anIntArray1052[27] = 18;
            this.anIntArray1052[28] = 17;
            this.anIntArray1052[29] = 17;
            this.anIntArray1052[30] = 16;
            this.anIntArray1052[31] = 15;
            this.anIntArray1052[32] = 14;
            this.anIntArray1052[33] = 14;
            this.anIntArray1052[34] = 13;
            this.anIntArray1052[35] = 12;
            this.anIntArray1052[36] = 12;
            this.anIntArray1052[37] = 11;
            this.anIntArray1052[38] = 10;
            this.anIntArray1052[39] = 10;
            this.anIntArray1052[40] = 9;
            this.anIntArray1052[41] = 9;
            this.anIntArray1052[42] = 8;
            this.anIntArray1052[43] = 8;
            this.anIntArray1052[44] = 7;
            this.anIntArray1052[45] = 7;
            this.anIntArray1052[46] = 6;
            this.anIntArray1052[47] = 6;
            this.anIntArray1052[48] = 6;
            this.anIntArray1052[49] = 5;
            this.anIntArray1052[50] = 5;
            this.anIntArray1052[51] = 4;
            this.anIntArray1052[52] = 4;
            this.anIntArray1052[53] = 4;
            this.anIntArray1052[54] = 3;
            this.anIntArray1052[55] = 3;
            this.anIntArray1052[56] = 3;
            this.anIntArray1052[57] = 3;
            this.anIntArray1052[58] = 2;
            this.anIntArray1052[59] = 2;
            this.anIntArray1052[60] = 2;
            this.anIntArray1052[61] = 2;
            this.anIntArray1052[62] = 1;
            this.anIntArray1052[63] = 1;
            this.anIntArray1052[64] = 1;
            this.anIntArray1052[65] = 1;
            this.anIntArray1052[66] = 1;
            this.anIntArray1052[67] = 1;
            this.anIntArray1052[68] = 0;
            this.anIntArray1052[69] = 0;
            this.anIntArray1052[70] = 0;
            this.anIntArray1052[71] = 0;
            this.anIntArray1052[72] = 0;
            this.anIntArray1052[73] = 0;
            this.anIntArray1052[74] = 0;
            this.anIntArray1052[75] = 0;
            this.anIntArray1052[150] = 67;
            this.anIntArray1052[149] = 61;
            this.anIntArray1052[148] = 57;
            this.anIntArray1052[147] = 53;
            this.anIntArray1052[146] = 50;
            this.anIntArray1052[145] = 48;
            this.anIntArray1052[144] = 45;
            this.anIntArray1052[143] = 43;
            this.anIntArray1052[142] = 41;
            this.anIntArray1052[141] = 39;
            this.anIntArray1052[140] = 37;
            this.anIntArray1052[139] = 36;
            this.anIntArray1052[138] = 34;
            this.anIntArray1052[137] = 33;
            this.anIntArray1052[136] = 31;
            this.anIntArray1052[135] = 30;
            this.anIntArray1052[134] = 29;
            this.anIntArray1052[133] = 28;
            this.anIntArray1052[132] = 27;
            this.anIntArray1052[131] = 26;
            this.anIntArray1052[130] = 25;
            this.anIntArray1052[129] = 24;
            this.anIntArray1052[128] = 23;
            this.anIntArray1052[127] = 22;
            this.anIntArray1052[126] = 21;
            this.anIntArray1052[125] = 20;
            this.anIntArray1052[124] = 19;
            this.anIntArray1052[123] = 18;
            this.anIntArray1052[122] = 17;
            this.anIntArray1052[121] = 17;
            this.anIntArray1052[120] = 16;
            this.anIntArray1052[119] = 15;
            this.anIntArray1052[118] = 14;
            this.anIntArray1052[117] = 14;
            this.anIntArray1052[116] = 13;
            this.anIntArray1052[115] = 12;
            this.anIntArray1052[114] = 12;
            this.anIntArray1052[113] = 11;
            this.anIntArray1052[112] = 10;
            this.anIntArray1052[111] = 10;
            this.anIntArray1052[110] = 9;
            this.anIntArray1052[109] = 9;
            this.anIntArray1052[108] = 8;
            this.anIntArray1052[107] = 8;
            this.anIntArray1052[106] = 7;
            this.anIntArray1052[105] = 7;
            this.anIntArray1052[104] = 6;
            this.anIntArray1052[103] = 6;
            this.anIntArray1052[102] = 6;
            this.anIntArray1052[101] = 5;
            this.anIntArray1052[100] = 5;
            this.anIntArray1052[99] = 4;
            this.anIntArray1052[98] = 4;
            this.anIntArray1052[97] = 4;
            this.anIntArray1052[96] = 3;
            this.anIntArray1052[95] = 3;
            this.anIntArray1052[94] = 3;
            this.anIntArray1052[93] = 3;
            this.anIntArray1052[92] = 2;
            this.anIntArray1052[91] = 2;
            this.anIntArray1052[90] = 2;
            this.anIntArray1052[89] = 2;
            this.anIntArray1052[88] = 1;
            this.anIntArray1052[87] = 1;
            this.anIntArray1052[86] = 1;
            this.anIntArray1052[85] = 1;
            this.anIntArray1052[84] = 1;
            this.anIntArray1052[83] = 1;
            this.anIntArray1052[82] = 0;
            this.anIntArray1052[81] = 0;
            this.anIntArray1052[80] = 0;
            this.anIntArray1052[79] = 0;
            this.anIntArray1052[78] = 0;
            this.anIntArray1052[77] = 0;
            this.anIntArray1052[76] = 0;
            this.anIntArray1052[75] = 0;
            Texture.method365(765, 503);
            this.fullScreenTextureArray = Texture.anIntArray1472;
            Texture.method365(519, 165);
            this.anIntArray1180 = Texture.anIntArray1472;
            Texture.method365(246, 335);
            this.anIntArray1181 = Texture.anIntArray1472;
            Texture.method365(512, 334);
            this.anIntArray1182 = Texture.anIntArray1472;
            final int[] array = new int[9];
            for (int n26 = 0; n26 < 9; ++n26) {
                final int n27 = 128 + n26 * 32 + 15;
                array[n26] = (600 + n27 * 3) * Texture.anIntArray1470[n27] >> 16;
            }
            WorldController.method310(500, 800, 512, 334, array);
            this.startRunnable(this.mouseDetection = new MouseDetection(this), 10);
            Animable_Sub5.clientInstance = this;
            ObjectDef.clientInstance = this;
            (EntityDef.clientInstance = this).resetImageProducers();
            this.resetImageProducers2();
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
            SignLink.reporterror("loaderror " + this.aString1049 + " " + this.anInt1079);
            this.loadingError = false;
        }
    }
    
    private void method91(final Stream stream, final int n) {
        while (stream.bitPosition + 10 < n * 8) {
            final int bits = stream.readBits(11);
            if (bits == 2047) {
                break;
            }
            if (this.playerArray[bits] == null) {
                this.playerArray[bits] = new Player();
                if (this.aStreamArray895s[bits] != null) {
                    this.playerArray[bits].updatePlayer(this.aStreamArray895s[bits]);
                }
            }
            this.playerIndices[this.playerCount++] = bits;
            final Player player = this.playerArray[bits];
            player.anInt1537 = client.loopCycle;
            if (stream.readBits(1) == 1) {
                this.anIntArray894[this.anInt893++] = bits;
            }
            final int bits2 = stream.readBits(1);
            int bits3 = stream.readBits(5);
            if (bits3 > 15) {
                bits3 -= 32;
            }
            int bits4 = stream.readBits(5);
            if (bits4 > 15) {
                bits4 -= 32;
            }
            player.setPos(client.myPlayer.smallX[0] + bits4, client.myPlayer.smallY[0] + bits3, bits2 == 1);
        }
        stream.finishBitAccess();
    }
    
    private void processMainScreenClick() {
        if (this.anInt1021 != 0) {
            return;
        }
        if (super.clickMode3 != 1) {
            return;
        }
        int n = (client.clientSize == 0) ? (this.saveClickX - 541) : (this.saveClickX - 8 - (client.clientWidth - 164));
        int n2 = (client.clientSize == 0) ? (this.saveClickY - 8) : (this.saveClickY - 5);
        if (n >= 0 && n2 >= 0 && n < 146 && n2 < 151) {
            n -= 73;
            n2 -= 75;
            final int n3 = this.minimapInt1 + this.minimapInt2 & 0x7FF;
            final int n4 = Texture.anIntArray1470[n3];
            final int n5 = Texture.anIntArray1471[n3];
            final int n6 = n4 * (this.minimapInt3 + 256) >> 8;
            final int n7 = n5 * (this.minimapInt3 + 256) >> 8;
            if (this.doWalkTo(1, 0, 0, 0, client.myPlayer.smallY[0], 0, 0, client.myPlayer.y - (n2 * n7 - n * n6 >> 11) >> 7, client.myPlayer.smallX[0], true, client.myPlayer.x + (n2 * n6 + n * n7 >> 11) >> 7)) {
                this.stream.writeWordBigEndian(n);
                this.stream.writeWordBigEndian(n2);
                this.stream.writeWord(this.minimapInt1);
                this.stream.writeWordBigEndian(57);
                this.stream.writeWordBigEndian(this.minimapInt2);
                this.stream.writeWordBigEndian(this.minimapInt3);
                this.stream.writeWordBigEndian(89);
                this.stream.writeWord(client.myPlayer.x);
                this.stream.writeWord(client.myPlayer.y);
                this.stream.writeWordBigEndian(this.anInt1264);
                this.stream.writeWordBigEndian(63);
            }
        }
        ++client.anInt1117;
        if (client.anInt1117 > 1151) {
            client.anInt1117 = 0;
        }
    }
    
    private String interfaceIntToString(final int n) {
        if (n < 999999999) {
            return String.valueOf(n);
        }
        return "*";
    }
    
    private void showErrorScreen() {
        final Graphics graphics = this.getGameComponent().getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 765, 503);
        this.method4(1);
        if (this.loadingError) {
            this.aBoolean831 = false;
            graphics.setFont(new Font("Helvetica", 1, 16));
            graphics.setColor(Color.red);
            int n = 35;
            graphics.drawString("This client doesn't seem to be working", 30, n);
            n += 50;
            graphics.setColor(Color.white);
            graphics.drawString("NOTE: Devilishpkz does not work on Mac OS or linux yet", 30, n);
            n += 50;
            graphics.setColor(Color.white);
            graphics.setFont(new Font("Helvetica", 1, 12));
            graphics.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, n);
            n += 30;
            graphics.drawString("2: Try clearing your web-browsers cache from tools->internet options", 30, n);
            n += 30;
            graphics.drawString("3: Go to your user folder located at C:/Users/Yourname and remove the DevilishpkzCache folder then restart the client", 30, n);
            n += 30;
            graphics.drawString("4: Try restarting your computer", 30, n);
            n += 30;
            graphics.drawString("5: Try using the downloadable .rar client at www.devilishpkz.org", 30, n);
        }
        if (this.genericLoadingError) {
            this.aBoolean831 = false;
            graphics.setFont(new Font("Helvetica", 1, 20));
            graphics.setColor(Color.white);
            graphics.drawString("Error - unable to load game!", 50, 50);
            graphics.drawString("To play Devilishpkz make sure you play from", 50, 100);
            graphics.drawString("http://www.devilishpkz.org", 50, 150);
        }
    }
    
    @Override
    public URL getCodeBase() {
        try {
            return new URL(client.server + ":" + (80 + client.portOff));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private void method95() {
        for (int i = 0; i < this.npcCount; ++i) {
            final NPC npc = this.npcArray[this.npcIndices[i]];
            if (npc != null) {
                this.method96(npc);
            }
        }
    }
    
    private void method96(final Entity entity) {
        if (entity.x < 128 || entity.y < 128 || entity.x >= 13184 || entity.y >= 13184) {
            entity.anim = -1;
            entity.anInt1520 = -1;
            entity.anInt1547 = 0;
            entity.anInt1548 = 0;
            entity.x = entity.smallX[0] * 128 + entity.anInt1540 * 64;
            entity.y = entity.smallY[0] * 128 + entity.anInt1540 * 64;
            entity.method446();
        }
        if (entity == client.myPlayer && (entity.x < 1536 || entity.y < 1536 || entity.x >= 11776 || entity.y >= 11776)) {
            entity.anim = -1;
            entity.anInt1520 = -1;
            entity.anInt1547 = 0;
            entity.anInt1548 = 0;
            entity.x = entity.smallX[0] * 128 + entity.anInt1540 * 64;
            entity.y = entity.smallY[0] * 128 + entity.anInt1540 * 64;
            entity.method446();
        }
        if (entity.anInt1547 > client.loopCycle) {
            this.method97(entity);
        }
        else if (entity.anInt1548 >= client.loopCycle) {
            this.method98(entity);
        }
        else {
            this.method99(entity);
        }
        this.method100(entity);
        this.method101(entity);
    }
    
    private void method97(final Entity entity) {
        final int n = entity.anInt1547 - client.loopCycle;
        final int n2 = entity.anInt1543 * 128 + entity.anInt1540 * 64;
        final int n3 = entity.anInt1545 * 128 + entity.anInt1540 * 64;
        entity.x += (n2 - entity.x) / n;
        entity.y += (n3 - entity.y) / n;
        entity.anInt1503 = 0;
        if (entity.anInt1549 == 0) {
            entity.turnDirection = 1024;
        }
        if (entity.anInt1549 == 1) {
            entity.turnDirection = 1536;
        }
        if (entity.anInt1549 == 2) {
            entity.turnDirection = 0;
        }
        if (entity.anInt1549 == 3) {
            entity.turnDirection = 512;
        }
    }
    
    private void method98(final Entity entity) {
        if (entity.anInt1548 == client.loopCycle || entity.anim == -1 || entity.anInt1529 != 0 || entity.anInt1528 + 1 > Animation.anims[entity.anim].method258(entity.anInt1527)) {
            final int n = entity.anInt1548 - entity.anInt1547;
            final int n2 = client.loopCycle - entity.anInt1547;
            final int n3 = entity.anInt1543 * 128 + entity.anInt1540 * 64;
            final int n4 = entity.anInt1545 * 128 + entity.anInt1540 * 64;
            final int n5 = entity.anInt1544 * 128 + entity.anInt1540 * 64;
            final int n6 = entity.anInt1546 * 128 + entity.anInt1540 * 64;
            entity.x = (n3 * (n - n2) + n5 * n2) / n;
            entity.y = (n4 * (n - n2) + n6 * n2) / n;
        }
        entity.anInt1503 = 0;
        if (entity.anInt1549 == 0) {
            entity.turnDirection = 1024;
        }
        if (entity.anInt1549 == 1) {
            entity.turnDirection = 1536;
        }
        if (entity.anInt1549 == 2) {
            entity.turnDirection = 0;
        }
        if (entity.anInt1549 == 3) {
            entity.turnDirection = 512;
        }
        entity.anInt1552 = entity.turnDirection;
    }
    
    private void method99(final Entity entity) {
        entity.anInt1517 = entity.anInt1511;
        if (entity.smallXYIndex == 0) {
            entity.anInt1503 = 0;
            return;
        }
        if (entity.anim != -1 && entity.anInt1529 == 0) {
            final Animation animation = Animation.anims[entity.anim];
            if (entity.anInt1542 > 0 && animation.anInt363 == 0) {
                ++entity.anInt1503;
                return;
            }
            if (entity.anInt1542 <= 0 && animation.anInt364 == 0) {
                ++entity.anInt1503;
                return;
            }
        }
        final int x = entity.x;
        final int y = entity.y;
        final int x2 = entity.smallX[entity.smallXYIndex - 1] * 128 + entity.anInt1540 * 64;
        final int y2 = entity.smallY[entity.smallXYIndex - 1] * 128 + entity.anInt1540 * 64;
        if (x2 - x > 256 || x2 - x < -256 || y2 - y > 256 || y2 - y < -256) {
            entity.x = x2;
            entity.y = y2;
            return;
        }
        if (x < x2) {
            if (y < y2) {
                entity.turnDirection = 1280;
            }
            else if (y > y2) {
                entity.turnDirection = 1792;
            }
            else {
                entity.turnDirection = 1536;
            }
        }
        else if (x > x2) {
            if (y < y2) {
                entity.turnDirection = 768;
            }
            else if (y > y2) {
                entity.turnDirection = 256;
            }
            else {
                entity.turnDirection = 512;
            }
        }
        else if (y < y2) {
            entity.turnDirection = 1024;
        }
        else {
            entity.turnDirection = 0;
        }
        int n = entity.turnDirection - entity.anInt1552 & 0x7FF;
        if (n > 1024) {
            n -= 2048;
        }
        int anInt1517 = entity.anInt1555;
        if (n >= -256 && n <= 256) {
            anInt1517 = entity.anInt1554;
        }
        else if (n >= 256 && n < 768) {
            anInt1517 = entity.anInt1557;
        }
        else if (n >= -768 && n <= -256) {
            anInt1517 = entity.anInt1556;
        }
        if (anInt1517 == -1) {
            anInt1517 = entity.anInt1554;
        }
        entity.anInt1517 = anInt1517;
        int n2 = 4;
        if (entity.anInt1552 != entity.turnDirection && entity.interactingEntity == -1 && entity.anInt1504 != 0) {
            n2 = 2;
        }
        if (entity.smallXYIndex > 2) {
            n2 = 6;
        }
        if (entity.smallXYIndex > 3) {
            n2 = 8;
        }
        if (entity.anInt1503 > 0 && entity.smallXYIndex > 1) {
            n2 = 8;
            --entity.anInt1503;
        }
        if (entity.aBooleanArray1553[entity.smallXYIndex - 1]) {
            n2 <<= 1;
        }
        if (n2 >= 8 && entity.anInt1517 == entity.anInt1554 && entity.anInt1505 != -1) {
            entity.anInt1517 = entity.anInt1505;
        }
        if (x < x2) {
            entity.x += n2;
            if (entity.x > x2) {
                entity.x = x2;
            }
        }
        else if (x > x2) {
            entity.x -= n2;
            if (entity.x < x2) {
                entity.x = x2;
            }
        }
        if (y < y2) {
            entity.y += n2;
            if (entity.y > y2) {
                entity.y = y2;
            }
        }
        else if (y > y2) {
            entity.y -= n2;
            if (entity.y < y2) {
                entity.y = y2;
            }
        }
        if (entity.x == x2 && entity.y == y2) {
            --entity.smallXYIndex;
            if (entity.anInt1542 > 0) {
                --entity.anInt1542;
            }
        }
    }
    
    private void method100(final Entity entity) {
        if (entity.anInt1504 == 0) {
            return;
        }
        if (entity.interactingEntity != -1 && entity.interactingEntity < 32768) {
            final NPC npc = this.npcArray[entity.interactingEntity];
            if (npc != null) {
                final int n = entity.x - npc.x;
                final int n2 = entity.y - npc.y;
                if (n != 0 || n2 != 0) {
                    entity.turnDirection = ((int)(Math.atan2(n, n2) * 325.949) & 0x7FF);
                }
            }
        }
        if (entity.interactingEntity >= 32768) {
            int myPlayerIndex = entity.interactingEntity - 32768;
            if (myPlayerIndex == this.unknownInt10) {
                myPlayerIndex = this.myPlayerIndex;
            }
            final Player player = this.playerArray[myPlayerIndex];
            if (player != null) {
                final int n3 = entity.x - player.x;
                final int n4 = entity.y - player.y;
                if (n3 != 0 || n4 != 0) {
                    entity.turnDirection = ((int)(Math.atan2(n3, n4) * 325.949) & 0x7FF);
                }
            }
        }
        if ((entity.anInt1538 != 0 || entity.anInt1539 != 0) && (entity.smallXYIndex == 0 || entity.anInt1503 > 0)) {
            final int n5 = entity.x - (entity.anInt1538 - this.baseX - this.baseX) * 64;
            final int n6 = entity.y - (entity.anInt1539 - this.baseY - this.baseY) * 64;
            if (n5 != 0 || n6 != 0) {
                entity.turnDirection = ((int)(Math.atan2(n5, n6) * 325.949) & 0x7FF);
            }
            entity.anInt1538 = 0;
            entity.anInt1539 = 0;
        }
        final int n7 = entity.turnDirection - entity.anInt1552 & 0x7FF;
        if (n7 != 0) {
            if (n7 < entity.anInt1504 || n7 > 2048 - entity.anInt1504) {
                entity.anInt1552 = entity.turnDirection;
            }
            else if (n7 > 1024) {
                entity.anInt1552 -= entity.anInt1504;
            }
            else {
                entity.anInt1552 += entity.anInt1504;
            }
            entity.anInt1552 &= 0x7FF;
            if (entity.anInt1517 == entity.anInt1511 && entity.anInt1552 != entity.turnDirection) {
                if (entity.anInt1512 != -1) {
                    entity.anInt1517 = entity.anInt1512;
                    return;
                }
                entity.anInt1517 = entity.anInt1554;
            }
        }
    }
    
    private void method101(final Entity entity) {
        entity.aBoolean1541 = false;
        if (entity.anInt1517 != -1) {
            final Animation animation = Animation.anims[entity.anInt1517];
            ++entity.anInt1519;
            if (entity.anInt1518 < animation.anInt352 && entity.anInt1519 > animation.method258(entity.anInt1518)) {
                entity.anInt1519 = 0;
                ++entity.anInt1518;
            }
            if (entity.anInt1518 >= animation.anInt352) {
                entity.anInt1519 = 0;
                entity.anInt1518 = 0;
            }
        }
        if (entity.anInt1520 != -1 && client.loopCycle >= entity.anInt1523) {
            if (entity.anInt1521 < 0) {
                entity.anInt1521 = 0;
            }
            final Animation aAnimation_407 = SpotAnim.cache[entity.anInt1520].aAnimation_407;
            ++entity.anInt1522;
            while (entity.anInt1521 < aAnimation_407.anInt352 && entity.anInt1522 > aAnimation_407.method258(entity.anInt1521)) {
                entity.anInt1522 -= aAnimation_407.method258(entity.anInt1521);
                ++entity.anInt1521;
            }
            if (entity.anInt1521 >= aAnimation_407.anInt352 && (entity.anInt1521 < 0 || entity.anInt1521 >= aAnimation_407.anInt352)) {
                entity.anInt1520 = -1;
            }
        }
        if (entity.anim != -1 && entity.anInt1529 <= 1 && Animation.anims[entity.anim].anInt363 == 1 && entity.anInt1542 > 0 && entity.anInt1547 <= client.loopCycle && entity.anInt1548 < client.loopCycle) {
            entity.anInt1529 = 1;
            return;
        }
        if (entity.anim != -1 && entity.anInt1529 == 0) {
            final Animation animation2 = Animation.anims[entity.anim];
            ++entity.anInt1528;
            while (entity.anInt1527 < animation2.anInt352 && entity.anInt1528 > animation2.method258(entity.anInt1527)) {
                entity.anInt1528 -= animation2.method258(entity.anInt1527);
                ++entity.anInt1527;
            }
            if (entity.anInt1527 >= animation2.anInt352) {
                entity.anInt1527 -= animation2.anInt356;
                ++entity.anInt1530;
                if (entity.anInt1530 >= animation2.anInt362) {
                    entity.anim = -1;
                }
                if (entity.anInt1527 < 0 || entity.anInt1527 >= animation2.anInt352) {
                    entity.anim = -1;
                }
            }
            entity.aBoolean1541 = animation2.aBoolean358;
        }
        if (entity.anInt1529 > 0) {
            --entity.anInt1529;
        }
    }
    
    private void drawGameScreen() {
        if (this.fullscreenInterfaceID != -1 && (this.loadingStage == 2 || super.fullGameScreen != null)) {
            if (this.loadingStage == 2) {
                this.method119(this.anInt945, this.fullscreenInterfaceID);
                if (client.openInterfaceID != -1) {
                    this.method119(this.anInt945, client.openInterfaceID);
                }
                this.anInt945 = 0;
                this.resetAllImageProducers();
                super.fullGameScreen.initDrawingArea();
                Texture.anIntArray1472 = this.fullScreenTextureArray;
                DrawingArea.setAllPixelsToZero();
                this.welcomeScreenRaised = true;
                if (client.openInterfaceID != -1) {
                    final RSInterface rsInterface = RSInterface.interfaceCache[client.openInterfaceID];
                    if (rsInterface.width == 512 && rsInterface.height == 334 && rsInterface.type == 0) {
                        rsInterface.width = 765;
                        rsInterface.height = 503;
                    }
                    this.drawInterface(0, 0, rsInterface, 8);
                }
                final RSInterface rsInterface2 = RSInterface.interfaceCache[this.fullscreenInterfaceID];
                if (rsInterface2.width == 512 && rsInterface2.height == 334 && rsInterface2.type == 0) {
                    rsInterface2.width = 765;
                    rsInterface2.height = 503;
                }
                this.drawInterface(0, 0, rsInterface2, 8);
                if (!this.menuOpen) {
                    this.processRightClick();
                    this.drawTooltip();
                }
                else {
                    this.drawMenu();
                }
            }
            ++this.drawCount;
            super.fullGameScreen.drawGraphics(0, super.graphics, 0);
            return;
        }
        if (this.drawCount != 0) {
            this.resetImageProducers2();
        }
        if (this.welcomeScreenRaised) {
            this.welcomeScreenRaised = false;
            if (client.clientSize == 0) {
                this.topFrame.drawGraphics(0, super.graphics, 0);
                this.leftFrame.drawGraphics(4, super.graphics, 0);
                this.rightFrame.drawGraphics(4, super.graphics, 516);
            }
            client.needDrawTabArea = true;
            client.inputTaken = true;
            client.tabAreaAltered = true;
            this.aBoolean1233 = true;
            if (this.loadingStage != 2 && client.clientSize == 0) {
                if (this.gameScreenIP != null && super.graphics != null) {
                    this.gameScreenIP.drawGraphics(4, super.graphics, 4);
                    this.mapAreaIP.drawGraphics(0, super.graphics, 516);
                }
                else if (client.clientSize == 1 || client.clientSize == 2) {
                    this.gameScreenIP.drawGraphics(0, super.graphics, 0);
                }
            }
        }
        if (this.loadingStage == 2) {
            this.method146();
        }
        if (this.menuOpen && this.menuScreenArea == 1) {
            client.needDrawTabArea = true;
        }
        if (this.invOverlayInterfaceID != -1 && this.method119(this.anInt945, this.invOverlayInterfaceID)) {
            client.needDrawTabArea = true;
        }
        if (this.atInventoryInterfaceType == 2) {
            client.needDrawTabArea = true;
        }
        if (this.activeInterfaceType == 2) {
            client.needDrawTabArea = true;
        }
        if (client.needDrawTabArea) {
            if (client.clientSize == 0) {
                this.drawTabArea();
            }
            client.needDrawTabArea = false;
        }
        if (this.backDialogID == -1) {
            this.aClass9_1059.scrollPosition = this.anInt1211 - client.anInt1089 - 110;
            if (super.mouseX > 478 && super.mouseX < 580 && super.mouseY > ((client.clientSize == 0) ? 503 : client.clientHeight) - 161) {
                this.method65(494, 110, super.mouseX - 0, super.mouseY - (((client.clientSize == 0) ? 503 : client.clientHeight) - 155), this.aClass9_1059, 0, false, this.anInt1211);
            }
            int anInt1089 = this.anInt1211 - 110 - this.aClass9_1059.scrollPosition;
            if (anInt1089 < 0) {
                anInt1089 = 0;
            }
            if (anInt1089 > this.anInt1211 - 110) {
                anInt1089 = this.anInt1211 - 110;
            }
            if (client.anInt1089 != anInt1089) {
                client.anInt1089 = anInt1089;
                client.inputTaken = true;
            }
        }
        if (this.backDialogID != -1 && this.method119(this.anInt945, this.backDialogID)) {
            client.inputTaken = true;
        }
        if (this.atInventoryInterfaceType == 3) {
            client.inputTaken = true;
        }
        if (this.activeInterfaceType == 3) {
            client.inputTaken = true;
        }
        if (this.aString844 != null) {
            client.inputTaken = true;
        }
        if (this.menuOpen && this.menuScreenArea == 2) {
            client.inputTaken = true;
        }
        if (client.inputTaken) {
            if (client.clientSize == 0) {
                this.drawChatArea();
            }
            client.inputTaken = false;
        }
        if (this.loadingStage == 2 && client.clientSize == 0) {
            this.drawMinimap();
            this.mapAreaIP.drawGraphics(0, super.graphics, 516);
        }
        if (this.anInt1054 != -1) {
            client.tabAreaAltered = true;
        }
        if (client.tabAreaAltered) {
            if (this.anInt1054 != -1 && this.anInt1054 == client.tabID) {
                this.anInt1054 = -1;
                this.stream.createFrame(120);
                this.stream.writeWordBigEndian(client.tabID);
            }
            client.tabAreaAltered = false;
            this.gameScreenIP.initDrawingArea();
        }
        this.anInt945 = 0;
    }
    
    private boolean buildFriendsListMenu(final RSInterface rsInterface) {
        int contentType = rsInterface.contentType;
        if ((contentType >= 1 && contentType <= 200) || (contentType >= 701 && contentType <= 900)) {
            if (contentType >= 801) {
                contentType -= 701;
            }
            else if (contentType >= 701) {
                contentType -= 601;
            }
            else if (contentType >= 101) {
                contentType -= 101;
            }
            else {
                --contentType;
            }
            this.menuActionName[this.menuActionRow] = "Remove @whi@" + this.friendsList[contentType];
            this.menuActionID[this.menuActionRow] = 792;
            ++this.menuActionRow;
            this.menuActionName[this.menuActionRow] = "Message @whi@" + this.friendsList[contentType];
            this.menuActionID[this.menuActionRow] = 639;
            ++this.menuActionRow;
            return true;
        }
        if (contentType >= 401 && contentType <= 500) {
            this.menuActionName[this.menuActionRow] = "Remove @whi@" + rsInterface.message;
            this.menuActionID[this.menuActionRow] = 322;
            ++this.menuActionRow;
            return true;
        }
        return false;
    }
    
    private void method104() {
        for (Animable_Sub3 animable_Sub3 = (Animable_Sub3)this.aClass19_1056.reverseGetFirst(); animable_Sub3 != null; animable_Sub3 = (Animable_Sub3)this.aClass19_1056.reverseGetNext()) {
            if (animable_Sub3.anInt1560 != this.plane || animable_Sub3.aBoolean1567) {
                animable_Sub3.unlink();
            }
            else if (client.loopCycle >= animable_Sub3.anInt1564) {
                animable_Sub3.method454(this.anInt945);
                if (animable_Sub3.aBoolean1567) {
                    animable_Sub3.unlink();
                }
                else {
                    this.worldController.method285(animable_Sub3.anInt1560, 0, animable_Sub3.anInt1563, -1, animable_Sub3.anInt1562, 60, animable_Sub3.anInt1561, animable_Sub3, false);
                }
            }
        }
    }
    
    public void drawBlackBox(final int n, final int n2) {
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
    
    private void drawInterface(final int n, final int n2, final RSInterface rsInterface, final int n3) {
        try {
            if (rsInterface.type != 0 || rsInterface.children == null) {
                return;
            }
            if (rsInterface.isMouseoverTriggered && this.anInt1026 != rsInterface.id && this.anInt1048 != rsInterface.id && this.anInt1039 != rsInterface.id) {
                return;
            }
            final int topX = DrawingArea.topX;
            final int topY = DrawingArea.topY;
            final int bottomX = DrawingArea.bottomX;
            final int bottomY = DrawingArea.bottomY;
            DrawingArea.setDrawingArea(n3 + rsInterface.height, n2, n2 + rsInterface.width, n3);
            for (int length = rsInterface.children.length, i = 0; i < length; ++i) {
                final int n4 = rsInterface.childX[i] + n2;
                final int n5 = rsInterface.childY[i] + n3 - n;
                final RSInterface rsInterface2 = RSInterface.interfaceCache[rsInterface.children[i]];
                final int n6 = n4 + rsInterface2.anInt263;
                final int n7 = n5 + rsInterface2.anInt265;
                if (rsInterface2.contentType > 0) {
                    this.drawFriendsListOrWelcomeScreen(rsInterface2);
                }
                final int[] array = { 1196, 1199, 1206, 1215, 1224, 1231, 1240, 1249, 1258, 1267, 1274, 1283, 1573, 1290, 1299, 1308, 1315, 1324, 1333, 1340, 1349, 1358, 1367, 1374, 1381, 1388, 1397, 1404, 1583, 12038, 1414, 1421, 1430, 1437, 1446, 1453, 1460, 1469, 15878, 1602, 1613, 1624, 7456, 1478, 1485, 1494, 1503, 1512, 1521, 1530, 1544, 1553, 1563, 1593, 1635, 12426, 12436, 12446, 12456, 6004, 18471, 12940, 12988, 13036, 12902, 12862, 13046, 12964, 13012, 13054, 12920, 12882, 13062, 12952, 13000, 13070, 12912, 12872, 13080, 12976, 13024, 13088, 12930, 12892, 13096 };
                for (int j = 0; j < array.length; ++j) {
                    if (rsInterface2.id == array[j] + 1) {
                        if (j > 61) {
                            this.drawBlackBox(n6 + 1, n7);
                        }
                        else {
                            this.drawBlackBox(n6, n7 + 1);
                        }
                    }
                }
                final int[] array2 = { 1202, 1203, 1209, 1210, 1211, 1218, 1219, 1220, 1227, 1228, 1234, 1235, 1236, 1243, 1244, 1245, 1252, 1253, 1254, 1261, 1262, 1263, 1270, 1271, 1277, 1278, 1279, 1286, 1287, 1293, 1294, 1295, 1302, 1303, 1304, 1311, 1312, 1318, 1319, 1320, 1327, 1328, 1329, 1336, 1337, 1343, 1344, 1345, 1352, 1353, 1354, 1361, 1362, 1363, 1370, 1371, 1377, 1378, 1384, 1385, 1391, 1392, 1393, 1400, 1401, 1407, 1408, 1410, 1417, 1418, 1424, 1425, 1426, 1433, 1434, 1440, 1441, 1442, 1449, 1450, 1456, 1457, 1463, 1464, 1465, 1472, 1473, 1474, 1481, 1482, 1488, 1489, 1490, 1497, 1498, 1499, 1506, 1507, 1508, 1515, 1516, 1517, 1524, 1525, 1526, 1533, 1534, 1535, 1547, 1548, 1549, 1556, 1557, 1558, 1566, 1567, 1568, 1576, 1577, 1578, 1586, 1587, 1588, 1596, 1597, 1598, 1605, 1606, 1607, 1616, 1617, 1618, 1627, 1628, 1629, 1638, 1639, 1640, 6007, 6008, 6011, 8673, 8674, 12041, 12042, 12429, 12430, 12431, 12439, 12440, 12441, 12449, 12450, 12451, 12459, 12460, 15881, 15882, 15885, 18474, 18475, 18478 };
                for (int k = 0; k < array2.length; ++k) {
                    if (rsInterface2.id == array2[k]) {
                        rsInterface2.modelZoom = 775;
                    }
                }
                if (rsInterface2.type == 6) {
                    final int[] array3 = { 4901, 4883, 969, 4888, 4894, 974, 980, 987 };
                    for (int l = 0; l < array3.length; ++l) {
                        if (rsInterface2.id == array3[l]) {
                            rsInterface2.modelZoom = 1600;
                        }
                    }
                }
                if (rsInterface2.type == 0) {
                    if (rsInterface2.scrollPosition > rsInterface2.scrollMax - rsInterface2.height) {
                        rsInterface2.scrollPosition = rsInterface2.scrollMax - rsInterface2.height;
                    }
                    if (rsInterface2.scrollPosition < 0) {
                        rsInterface2.scrollPosition = 0;
                    }
                    this.drawInterface(rsInterface2.scrollPosition, n6, rsInterface2, n7);
                    if (rsInterface2.scrollMax > rsInterface2.height) {
                        this.drawScrollbar(rsInterface2.height, rsInterface2.scrollPosition, n7, n6 + rsInterface2.width, rsInterface2.scrollMax);
                    }
                }
                else if (rsInterface2.type != 1) {
                    if (rsInterface2.type == 2) {
                        int n8 = 0;
                        for (int n9 = 0; n9 < rsInterface2.height; ++n9) {
                            for (int n10 = 0; n10 < rsInterface2.width; ++n10) {
                                int n11 = n6 + n10 * (32 + rsInterface2.invSpritePadX);
                                int n12 = n7 + n9 * (32 + rsInterface2.invSpritePadY);
                                if (n8 < 20) {
                                    n11 += rsInterface2.spritesX[n8];
                                    n12 += rsInterface2.spritesY[n8];
                                }
                                if (rsInterface2.inv[n8] > 0) {
                                    int n13 = 0;
                                    int n14 = 0;
                                    final int n15 = rsInterface2.inv[n8] - 1;
                                    if ((n11 > DrawingArea.topX - 32 && n11 < DrawingArea.bottomX && n12 > DrawingArea.topY - 32 && n12 < DrawingArea.bottomY) || (this.activeInterfaceType != 0 && this.anInt1085 == n8)) {
                                        int n16 = 0;
                                        if (this.itemSelected == 1 && this.anInt1283 == n8 && this.anInt1284 == rsInterface2.id) {
                                            n16 = 16777215;
                                        }
                                        final Sprite sprite = ItemDef.getSprite(n15, rsInterface2.invStackSizes[n8], n16);
                                        if (sprite != null) {
                                            if (this.activeInterfaceType != 0 && this.anInt1085 == n8 && this.anInt1084 == rsInterface2.id) {
                                                n13 = super.mouseX - this.anInt1087;
                                                n14 = super.mouseY - this.anInt1088;
                                                if (n13 < 5 && n13 > -5) {
                                                    n13 = 0;
                                                }
                                                if (n14 < 5 && n14 > -5) {
                                                    n14 = 0;
                                                }
                                                if (this.anInt989 < 5) {
                                                    n13 = 0;
                                                    n14 = 0;
                                                }
                                                sprite.drawSprite1(n11 + n13, n12 + n14);
                                                if (n12 + n14 < DrawingArea.topY && rsInterface.scrollPosition > 0) {
                                                    int scrollPosition = this.anInt945 * (DrawingArea.topY - n12 - n14) / 3;
                                                    if (scrollPosition > this.anInt945 * 10) {
                                                        scrollPosition = this.anInt945 * 10;
                                                    }
                                                    if (scrollPosition > rsInterface.scrollPosition) {
                                                        scrollPosition = rsInterface.scrollPosition;
                                                    }
                                                    rsInterface.scrollPosition -= scrollPosition;
                                                    this.anInt1088 += scrollPosition;
                                                }
                                                if (n12 + n14 + 32 > DrawingArea.bottomY && rsInterface.scrollPosition < rsInterface.scrollMax - rsInterface.height) {
                                                    int n17 = this.anInt945 * (n12 + n14 + 32 - DrawingArea.bottomY) / 3;
                                                    if (n17 > this.anInt945 * 10) {
                                                        n17 = this.anInt945 * 10;
                                                    }
                                                    if (n17 > rsInterface.scrollMax - rsInterface.height - rsInterface.scrollPosition) {
                                                        n17 = rsInterface.scrollMax - rsInterface.height - rsInterface.scrollPosition;
                                                    }
                                                    rsInterface.scrollPosition += n17;
                                                    this.anInt1088 -= n17;
                                                }
                                            }
                                            else if (this.atInventoryInterfaceType != 0 && this.atInventoryIndex == n8 && this.atInventoryInterface == rsInterface2.id) {
                                                sprite.drawSprite1(n11, n12);
                                            }
                                            else {
                                                sprite.drawSprite(n11, n12);
                                            }
                                            if (sprite.anInt1444 == 33 || rsInterface2.invStackSizes[n8] != 1) {
                                                final int n18 = rsInterface2.invStackSizes[n8];
                                                if (n18 >= 1) {
                                                    this.smallText.method385(16776960, intToKOrMil(n18), n12 + 9 + n14, n11 + n13);
                                                }
                                                if (n18 >= 100000) {
                                                    this.smallText.method385(16777215, intToKOrMil(n18), n12 + 9 + n14, n11 + n13);
                                                }
                                                if (n18 >= 10000000) {
                                                    this.smallText.method385(4841998, intToKOrMil(n18), n12 + 9 + n14, n11 + n13);
                                                }
                                            }
                                        }
                                    }
                                }
                                else if (rsInterface2.sprites != null && n8 < 20) {
                                    final Sprite sprite2 = rsInterface2.sprites[n8];
                                    if (sprite2 != null) {
                                        sprite2.drawSprite(n11, n12);
                                    }
                                }
                                ++n8;
                            }
                        }
                    }
                    else if (rsInterface2.type == 3) {
                        boolean b = false;
                        if (this.anInt1039 == rsInterface2.id || this.anInt1048 == rsInterface2.id || this.anInt1026 == rsInterface2.id) {
                            b = true;
                        }
                        int n19;
                        if (this.interfaceIsSelected(rsInterface2)) {
                            n19 = rsInterface2.anInt219;
                            if (b && rsInterface2.anInt239 != 0) {
                                n19 = rsInterface2.anInt239;
                            }
                        }
                        else {
                            n19 = rsInterface2.textColor;
                            if (b && rsInterface2.anInt216 != 0) {
                                n19 = rsInterface2.anInt216;
                            }
                        }
                        if (rsInterface2.aByte254 == 0) {
                            if (rsInterface2.aBoolean227) {
                                DrawingArea.drawPixels(rsInterface2.height, n7, n6, n19, rsInterface2.width);
                            }
                            else {
                                DrawingArea.fillPixels(n6, rsInterface2.width, rsInterface2.height, n19, n7);
                            }
                        }
                        else if (rsInterface2.aBoolean227) {
                            DrawingArea.method335(n19, n7, rsInterface2.width, rsInterface2.height, 256 - (rsInterface2.aByte254 & 0xFF), n6);
                        }
                        else {
                            DrawingArea.method338(n7, rsInterface2.height, 256 - (rsInterface2.aByte254 & 0xFF), n19, rsInterface2.width, n6);
                        }
                    }
                    else if (rsInterface2.type == 4) {
                        try {
                            final TextDrawingArea textDrawingAreas = rsInterface2.textDrawingAreas;
                            String s = rsInterface2.message;
                            boolean b2 = false;
                            if (this.anInt1039 == rsInterface2.id || this.anInt1048 == rsInterface2.id || this.anInt1026 == rsInterface2.id) {
                                b2 = true;
                            }
                            int n20;
                            if (this.interfaceIsSelected(rsInterface2)) {
                                n20 = rsInterface2.anInt219;
                                if (b2 && rsInterface2.anInt239 != 0) {
                                    n20 = rsInterface2.anInt239;
                                }
                                if (rsInterface2.aString228.length() > 0) {
                                    s = rsInterface2.aString228;
                                }
                            }
                            else {
                                n20 = rsInterface2.textColor;
                                if (b2 && rsInterface2.anInt216 != 0) {
                                    n20 = rsInterface2.anInt216;
                                }
                            }
                            if (rsInterface2.atActionType == 6 && this.aBoolean1149) {
                                s = "Please wait...";
                                n20 = rsInterface2.textColor;
                            }
                            if (DrawingArea.width == 519) {
                                if (n20 == 16776960) {
                                    n20 = 255;
                                }
                                if (n20 == 49152) {
                                    n20 = 16777215;
                                }
                            }
                            if (rsInterface2.parentID == 1151 || rsInterface2.parentID == 12855) {
                                switch (n20) {
                                    case 16773120: {
                                        n20 = 16685087;
                                        break;
                                    }
                                    case 7040819: {
                                        n20 = 11495962;
                                        break;
                                    }
                                }
                            }
                            int n21 = n7 + textDrawingAreas.anInt1497;
                            while (s.length() > 0) {
                                if (s.indexOf("%") != -1) {
                                    while (true) {
                                        final int index = s.indexOf("%1");
                                        if (index == -1) {
                                            break;
                                        }
                                        if (rsInterface2.id < 4000 || (rsInterface2.id > 5000 && rsInterface2.id != 13921 && rsInterface2.id != 13922 && rsInterface2.id != 12171 && rsInterface2.id != 12172)) {
                                            s = s.substring(0, index) + this.methodR(this.extractInterfaceValues(rsInterface2, 0)) + s.substring(index + 2);
                                        }
                                        else {
                                            s = s.substring(0, index) + this.interfaceIntToString(this.extractInterfaceValues(rsInterface2, 0)) + s.substring(index + 2);
                                        }
                                    }
                                    while (true) {
                                        final int index2 = s.indexOf("%2");
                                        if (index2 == -1) {
                                            break;
                                        }
                                        s = s.substring(0, index2) + this.interfaceIntToString(this.extractInterfaceValues(rsInterface2, 1)) + s.substring(index2 + 2);
                                    }
                                    while (true) {
                                        final int index3 = s.indexOf("%3");
                                        if (index3 == -1) {
                                            break;
                                        }
                                        s = s.substring(0, index3) + this.interfaceIntToString(this.extractInterfaceValues(rsInterface2, 2)) + s.substring(index3 + 2);
                                    }
                                    while (true) {
                                        final int index4 = s.indexOf("%4");
                                        if (index4 == -1) {
                                            break;
                                        }
                                        s = s.substring(0, index4) + this.interfaceIntToString(this.extractInterfaceValues(rsInterface2, 3)) + s.substring(index4 + 2);
                                    }
                                    while (true) {
                                        final int index5 = s.indexOf("%5");
                                        if (index5 == -1) {
                                            break;
                                        }
                                        s = s.substring(0, index5) + this.interfaceIntToString(this.extractInterfaceValues(rsInterface2, 4)) + s.substring(index5 + 2);
                                    }
                                }
                                final int index6 = s.indexOf("\\n");
                                String substring;
                                if (index6 != -1) {
                                    substring = s.substring(0, index6);
                                    s = s.substring(index6 + 2);
                                }
                                else {
                                    substring = s;
                                    s = "";
                                }
                                if (rsInterface2.centerText) {
                                    textDrawingAreas.method382(n20, n6 + rsInterface2.width / 2, substring, n21, rsInterface2.textShadow);
                                }
                                else {
                                    textDrawingAreas.method389(rsInterface2.textShadow, n6, n20, substring, n21);
                                }
                                n21 += textDrawingAreas.anInt1497;
                            }
                        }
                        catch (NullPointerException ex) {}
                    }
                    else if (rsInterface2.type == 5) {
                        Sprite sprite3;
                        if (this.interfaceIsSelected(rsInterface2)) {
                            sprite3 = rsInterface2.sprite2;
                        }
                        else if (rsInterface2.id == 13035 || rsInterface2.id == 13045 || rsInterface2.id == 13053 || rsInterface2.id == 13061 || rsInterface2.id == 13069 || rsInterface2.id == 13079 || rsInterface2.id == 13087 || rsInterface2.id == 13095 || rsInterface2.id == 27 || rsInterface2.id == 1167 || rsInterface2.id == 1170 || rsInterface2.id == 1164 || rsInterface2.id == 1174 || rsInterface2.id == 1541 || rsInterface2.id == 7455 || rsInterface2.id == 1540 || rsInterface2.id == 13069 || rsInterface2.id == 18470) {
                            sprite3 = rsInterface2.sprite2;
                        }
                        else {
                            sprite3 = rsInterface2.sprite1;
                        }
                        if (this.spellSelected == 1 && rsInterface2.id == client.spellID && client.spellID != 0 && sprite3 != null) {
                            sprite3.drawSprite(n6, n7, 16777215);
                        }
                        else if (this.Autocast && rsInterface2.id == this.autocastId) {
                            this.magicAuto.drawSprite(n6 - 3, n7 - 3);
                        }
                        if (sprite3 != null) {
                            sprite3.drawSprite(n6, n7);
                        }
                    }
                    else if (rsInterface2.type == 6) {
                        final int textureInt1 = Texture.textureInt1;
                        final int textureInt2 = Texture.textureInt2;
                        Texture.textureInt1 = n6 + rsInterface2.width / 2;
                        Texture.textureInt2 = n7 + rsInterface2.height / 2;
                        final int n22 = Texture.anIntArray1470[rsInterface2.modelRotation1] * rsInterface2.modelZoom >> 16;
                        final int n23 = Texture.anIntArray1471[rsInterface2.modelRotation1] * rsInterface2.modelZoom >> 16;
                        final boolean interfaceIsSelected = this.interfaceIsSelected(rsInterface2);
                        int n24;
                        if (interfaceIsSelected) {
                            n24 = rsInterface2.anInt258;
                        }
                        else {
                            n24 = rsInterface2.anInt257;
                        }
                        Model model;
                        if (n24 == -1) {
                            model = rsInterface2.method209(-1, -1, interfaceIsSelected);
                        }
                        else {
                            final Animation animation = Animation.anims[n24];
                            model = rsInterface2.method209(animation.anIntArray354[rsInterface2.anInt246], animation.anIntArray353[rsInterface2.anInt246], interfaceIsSelected);
                        }
                        if (model != null) {
                            model.method482(rsInterface2.modelRotation2, 0, rsInterface2.modelRotation1, 0, n22, n23);
                        }
                        Texture.textureInt1 = textureInt1;
                        Texture.textureInt2 = textureInt2;
                    }
                    else if (rsInterface2.type == 7) {
                        final TextDrawingArea textDrawingAreas2 = rsInterface2.textDrawingAreas;
                        int n25 = 0;
                        for (int n26 = 0; n26 < rsInterface2.height; ++n26) {
                            for (int n27 = 0; n27 < rsInterface2.width; ++n27) {
                                if (rsInterface2.inv[n25] > 0) {
                                    final ItemDef forID = ItemDef.forID(rsInterface2.inv[n25] - 1);
                                    String s2 = forID.name;
                                    if (forID.stackable || rsInterface2.invStackSizes[n25] != 1) {
                                        s2 = s2 + " x" + intToKOrMilLongName(rsInterface2.invStackSizes[n25]);
                                    }
                                    final int n28 = n6 + n27 * (115 + rsInterface2.invSpritePadX);
                                    final int n29 = n7 + n26 * (12 + rsInterface2.invSpritePadY);
                                    if (rsInterface2.centerText) {
                                        textDrawingAreas2.method382(rsInterface2.textColor, n28 + rsInterface2.width / 2, s2, n29, rsInterface2.textShadow);
                                    }
                                    else {
                                        textDrawingAreas2.method389(rsInterface2.textShadow, n28, rsInterface2.textColor, s2, n29);
                                    }
                                }
                                ++n25;
                            }
                        }
                        if (rsInterface2.type == 8) {
                            String s3;
                            if (this.interfaceIsSelected(rsInterface2)) {
                                s3 = rsInterface2.aString228;
                            }
                            else {
                                s3 = rsInterface2.message;
                            }
                            this.drawHoverBox(n6, n7, s3);
                        }
                    }
                    else if (rsInterface2.type == 8) {
                        this.drawHoverBox(n6, n7, rsInterface2.popupString);
                    }
                    else if (rsInterface2.type == 10) {
                        final int n30 = (client.clientSize >= 1) ? 0 : 4;
                        final int n31 = (client.clientSize >= 1) ? 0 : 4;
                        final Sprite sprite4 = rsInterface2.sprite1;
                        final Sprite sprite5 = rsInterface2.sprite2;
                        if (!rsInterface2.isInventory || sprite4 != null) {
                            sprite4.drawSprite(n6, n7);
                        }
                        if (sprite5 != null && this.mouseX >= n30 + n6 && this.mouseX <= n30 + n6 + rsInterface2.width && this.mouseY >= n31 + n7 && this.mouseY <= n31 + n7 + rsInterface2.height) {
                            sprite5.drawSprite(n6, n7);
                        }
                    }
                    else if (rsInterface2.type == 9 && (this.anInt1500 == rsInterface2.id || this.anInt1044 == rsInterface2.id || this.anInt1129 == rsInterface2.id) && !this.menuOpen) {
                        int n32 = 0;
                        int n33 = 0;
                        final TextDrawingArea smallText = this.smallText;
                        String s4 = rsInterface2.message;
                        while (s4.length() > 0) {
                            final int index7 = s4.indexOf("\\n");
                            String substring2;
                            if (index7 != -1) {
                                substring2 = s4.substring(0, index7);
                                s4 = s4.substring(index7 + 2);
                            }
                            else {
                                substring2 = s4;
                                s4 = "";
                            }
                            final int textWidth = smallText.getTextWidth(substring2);
                            if (textWidth > n32) {
                                n32 = textWidth;
                            }
                            n33 += smallText.anInt1497 + 1;
                        }
                        n32 += 6;
                        n33 += 7;
                        int n34 = n6 + rsInterface2.width - 5 - n32;
                        int n35 = n7 + rsInterface2.height + 5;
                        if (n34 < n6 + 5) {
                            n34 = n6 + 5;
                        }
                        if (n34 + n32 > n2 + rsInterface.width) {
                            n34 = n2 + rsInterface.width - n32;
                        }
                        if (n35 + n33 > n3 + rsInterface.height) {
                            n35 = n3 + rsInterface.height - n33;
                        }
                        DrawingArea.drawPixels(n33, n35, n34, 16777120, n32);
                        DrawingArea.fillPixels(n34, n32, n33, 0, n35);
                        String s5 = rsInterface2.message;
                        int n36 = n35 + smallText.anInt1497 + 2;
                        while (s5.length() > 0) {
                            final int index8 = s5.indexOf("\\n");
                            String substring3;
                            if (index8 != -1) {
                                substring3 = s5.substring(0, index8);
                                s5 = s5.substring(index8 + 2);
                            }
                            else {
                                substring3 = s5;
                                s5 = "";
                            }
                            smallText.method389(false, n34 + 3, 0, substring3, n36);
                            n36 += smallText.anInt1497 + 1;
                        }
                    }
                }
            }
            DrawingArea.setDrawingArea(bottomY, topX, bottomX, topY);
        }
        catch (Exception ex2) {}
    }
    
    private void randomizeBackground(final Background background) {
        final int n = 256;
        for (int i = 0; i < this.anIntArray1190.length; ++i) {
            this.anIntArray1190[i] = 0;
        }
        for (int j = 0; j < 5000; ++j) {
            this.anIntArray1190[(int)(Math.random() * 128.0 * n)] = (int)(Math.random() * 256.0);
        }
        for (int k = 0; k < 20; ++k) {
            for (int l = 1; l < n - 1; ++l) {
                for (int n2 = 1; n2 < 127; ++n2) {
                    final int n3 = n2 + (l << 7);
                    this.anIntArray1191[n3] = (this.anIntArray1190[n3 - 1] + this.anIntArray1190[n3 + 1] + this.anIntArray1190[n3 - 128] + this.anIntArray1190[n3 + 128]) / 4;
                }
            }
            final int[] anIntArray1190 = this.anIntArray1190;
            this.anIntArray1190 = this.anIntArray1191;
            this.anIntArray1191 = anIntArray1190;
        }
        if (background != null) {
            int n4 = 0;
            for (int n5 = 0; n5 < background.anInt1453; ++n5) {
                for (int n6 = 0; n6 < background.anInt1452; ++n6) {
                    if (background.aByteArray1450[n4++] != 0) {
                        this.anIntArray1190[n6 + 16 + background.anInt1454 + (n5 + 16 + background.anInt1455 << 7)] = 0;
                    }
                }
            }
        }
    }
    
    private void method107(final int n, final int n2, final Stream stream, final Player player) {
        if ((n & 0x400) != 0x0) {
            player.anInt1543 = stream.method428();
            player.anInt1545 = stream.method428();
            player.anInt1544 = stream.method428();
            player.anInt1546 = stream.method428();
            player.anInt1547 = stream.method436() + client.loopCycle;
            player.anInt1548 = stream.method435() + client.loopCycle;
            player.anInt1549 = stream.method428();
            player.method446();
        }
        if ((n & 0x100) != 0x0) {
            player.anInt1520 = stream.method434();
            final int dWord = stream.readDWord();
            player.anInt1524 = dWord >> 16;
            player.anInt1523 = client.loopCycle + (dWord & 0xFFFF);
            player.anInt1521 = 0;
            player.anInt1522 = 0;
            if (player.anInt1523 > client.loopCycle) {
                player.anInt1521 = -1;
            }
            if (player.anInt1520 == 65535) {
                player.anInt1520 = -1;
            }
        }
        if ((n & 0x8) != 0x0) {
            int method434 = stream.method434();
            if (method434 == 65535) {
                method434 = -1;
            }
            final int method435 = stream.method427();
            if (method434 == player.anim && method434 != -1) {
                final int anInt365 = Animation.anims[method434].anInt365;
                if (anInt365 == 1) {
                    player.anInt1527 = 0;
                    player.anInt1528 = 0;
                    player.anInt1529 = method435;
                    player.anInt1530 = 0;
                }
                if (anInt365 == 2) {
                    player.anInt1530 = 0;
                }
            }
            else if (method434 == -1 || player.anim == -1 || Animation.anims[method434].anInt359 >= Animation.anims[player.anim].anInt359) {
                player.anim = method434;
                player.anInt1527 = 0;
                player.anInt1528 = 0;
                player.anInt1529 = method435;
                player.anInt1530 = 0;
                player.anInt1542 = player.smallXYIndex;
            }
        }
        if ((n & 0x4) != 0x0) {
            player.textSpoken = stream.readString();
            if (player.textSpoken.charAt(0) == '~' && player != client.myPlayer) {
                this.pushMessage(player.textSpoken = player.textSpoken.substring(1), 2, player.name);
            }
            else if (player == client.myPlayer) {
                this.pushMessage(player.textSpoken, 2, player.name);
            }
            player.anInt1513 = 0;
            player.anInt1531 = 0;
            player.textCycle = 150;
        }
        if ((n & 0x80) != 0x0) {
            final int method436 = stream.method434();
            final int unsignedByte = stream.readUnsignedByte();
            final int method437 = stream.method427();
            final int currentOffset = stream.currentOffset;
            if (player.name != null && player.visible) {
                final long longForName = TextClass.longForName(player.name);
                boolean b = false;
                if (unsignedByte <= 1) {
                    for (int i = 0; i < this.ignoreCount; ++i) {
                        if (this.ignoreListAsLongs[i] == longForName) {
                            b = true;
                            break;
                        }
                    }
                }
                if (!b && this.anInt1251 == 0) {
                    try {
                        stream.method442(method437, this.aStream_834.currentOffset = 0, this.aStream_834.buffer);
                        this.aStream_834.currentOffset = 0;
                        final String method438 = TextInput.method525(method437, this.aStream_834);
                        player.textSpoken = method438;
                        player.anInt1513 = method436 >> 8;
                        player.privelage = unsignedByte;
                        player.anInt1531 = (method436 & 0xFF);
                        player.textCycle = 150;
                        if (unsignedByte == 4) {
                            this.pushMessage(method438, 1, "@cr4@" + player.name);
                        }
                        else if (unsignedByte == 2 || unsignedByte == 3) {
                            this.pushMessage(method438, 1, "@cr2@" + player.name);
                        }
                        else if (unsignedByte == 1) {
                            this.pushMessage(method438, 1, "@cr1@" + player.name);
                        }
                        else {
                            this.pushMessage(method438, 2, player.name);
                        }
                    }
                    catch (Exception ex) {
                        SignLink.reporterror("cde2");
                    }
                }
            }
            stream.currentOffset = currentOffset + method437;
        }
        if ((n & 0x1) != 0x0) {
            player.interactingEntity = stream.method434();
            if (player.interactingEntity == 65535) {
                player.interactingEntity = -1;
            }
        }
        if ((n & 0x10) != 0x0) {
            final int method439 = stream.method427();
            final byte[] array = new byte[method439];
            final Stream stream2 = new Stream(array);
            stream.readBytes(method439, 0, array);
            player.updatePlayer(this.aStreamArray895s[n2] = stream2);
        }
        if ((n & 0x2) != 0x0) {
            player.anInt1538 = stream.method436();
            player.anInt1539 = stream.method434();
        }
        if ((n & 0x20) != 0x0) {
            player.updateHitData(stream.method426(), stream.readUnsignedByte(), client.loopCycle);
            player.loopCycleStatus = client.loopCycle + 300;
            player.currentHealth = stream.method427();
            player.maxHealth = stream.readUnsignedByte();
        }
        if ((n & 0x200) != 0x0) {
            player.updateHitData(stream.method428(), stream.readUnsignedByte(), client.loopCycle);
            player.loopCycleStatus = client.loopCycle + 300;
            player.currentHealth = stream.readUnsignedByte();
            player.maxHealth = stream.method427();
        }
    }
    
    private void method108() {
        try {
            final int anInt1014 = client.myPlayer.x + this.anInt1278;
            final int anInt1015 = client.myPlayer.y + this.anInt1131;
            if (this.anInt1014 - anInt1014 < -500 || this.anInt1014 - anInt1014 > 500 || this.anInt1015 - anInt1015 < -500 || this.anInt1015 - anInt1015 > 500) {
                this.anInt1014 = anInt1014;
                this.anInt1015 = anInt1015;
            }
            if (this.anInt1014 != anInt1014) {
                this.anInt1014 += (anInt1014 - this.anInt1014) / 16;
            }
            if (this.anInt1015 != anInt1015) {
                this.anInt1015 += (anInt1015 - this.anInt1015) / 16;
            }
            if (super.keyArray[1] == 1) {
                this.anInt1186 += (-24 - this.anInt1186) / 2;
            }
            else if (super.keyArray[2] == 1) {
                this.anInt1186 += (24 - this.anInt1186) / 2;
            }
            else {
                this.anInt1186 /= 2;
            }
            if (super.keyArray[3] == 1) {
                this.anInt1187 += (12 - this.anInt1187) / 2;
            }
            else if (super.keyArray[4] == 1) {
                this.anInt1187 += (-12 - this.anInt1187) / 2;
            }
            else {
                this.anInt1187 /= 2;
            }
            this.minimapInt1 = (this.minimapInt1 + this.anInt1186 / 2 & 0x7FF);
            this.anInt1184 += this.anInt1187 / 2;
            if (this.anInt1184 < 128) {
                this.anInt1184 = 128;
            }
            if (this.anInt1184 > 383) {
                this.anInt1184 = 383;
            }
            final int n = this.anInt1014 >> 7;
            final int n2 = this.anInt1015 >> 7;
            final int method42 = this.method42(this.plane, this.anInt1015, this.anInt1014);
            int n3 = 0;
            if (n > 3 && n2 > 3 && n < 100 && n2 < 100) {
                for (int i = n - 4; i <= n + 4; ++i) {
                    for (int j = n2 - 4; j <= n2 + 4; ++j) {
                        int plane = this.plane;
                        if (plane < 3 && (this.byteGroundArray[1][i][j] & 0x2) == 0x2) {
                            ++plane;
                        }
                        final int n4 = method42 - this.intGroundArray[plane][i][j];
                        if (n4 > n3) {
                            n3 = n4;
                        }
                    }
                }
            }
            ++client.anInt1005;
            if (client.anInt1005 > 1512) {
                client.anInt1005 = 0;
            }
            int n5 = n3 * 192;
            if (n5 > 98048) {
                n5 = 98048;
            }
            if (n5 < 32768) {
                n5 = 32768;
            }
            if (n5 > this.anInt984) {
                this.anInt984 += (n5 - this.anInt984) / 24;
                return;
            }
            if (n5 < this.anInt984) {
                this.anInt984 += (n5 - this.anInt984) / 80;
            }
        }
        catch (Exception ex) {
            SignLink.reporterror("glfc_ex " + client.myPlayer.x + "," + client.myPlayer.y + "," + this.anInt1014 + "," + this.anInt1015 + "," + this.anInt1069 + "," + this.anInt1070 + "," + this.baseX + "," + this.baseY);
            throw new RuntimeException("eek");
        }
    }
    
    public void processDrawing() {
        if (this.loadingError || this.genericLoadingError) {
            this.showErrorScreen();
            return;
        }
        ++client.anInt1061;
        if (!this.loggedIn) {
            this.drawLoginScreen(false);
        }
        else {
            this.drawGameScreen();
        }
        this.anInt1213 = 0;
    }
    
    private boolean isFriendOrSelf(final String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0; i < this.friendsCount; ++i) {
            if (s.equalsIgnoreCase(this.friendsList[i])) {
                return true;
            }
        }
        return s.equalsIgnoreCase(client.myPlayer.name);
    }
    
    private static String combatDiffColor(final int n, final int n2) {
        final int n3 = n - n2;
        if (n3 < -9) {
            return "@red@";
        }
        if (n3 < -6) {
            return "@or3@";
        }
        if (n3 < -3) {
            return "@or2@";
        }
        if (n3 < 0) {
            return "@or1@";
        }
        if (n3 > 9) {
            return "@gre@";
        }
        if (n3 > 6) {
            return "@gr3@";
        }
        if (n3 > 3) {
            return "@gr2@";
        }
        if (n3 > 0) {
            return "@gr1@";
        }
        return "@yel@";
    }
    
    private void setWaveVolume(final int wavevol) {
        SignLink.wavevol = wavevol;
    }
    
    private void draw3dScreen() {
        final int n = (client.clientSize >= 1) ? (client.clientWidth / 2 - 256) : 0;
        final int n2 = (client.clientSize >= 1) ? (client.clientHeight / 2 - 167) : 0;
        this.drawSplitPrivateChat();
        if (this.crossType == 1) {
            this.crosses[this.crossIndex / 100].drawSprite(this.crossX - 8 - 4, this.crossY - 8 - 4);
            ++client.anInt1142;
            if (client.anInt1142 > 67) {
                client.anInt1142 = 0;
                this.stream.createFrame(78);
            }
        }
        if (this.crossType == 2) {
            this.crosses[4 + this.crossIndex / 100].drawSprite(this.crossX - 8 - 4, this.crossY - 8 - 4);
        }
        if (this.pvpWindow) {
            if (this.safeZone) {
                this.pvpIcon[0].drawSprite(5, 15);
            }
            else {
                this.pvpIcon[2].drawSprite(5, 15);
            }
        }
        if (this.anInt1018 != -1) {
            this.method119(this.anInt945, this.anInt1018);
            this.drawInterface(0, n, RSInterface.interfaceCache[this.anInt1018], n2);
        }
        if (client.openInterfaceID != -1) {
            this.method119(this.anInt945, client.openInterfaceID);
            this.drawInterface(0, n, RSInterface.interfaceCache[client.openInterfaceID], n2);
        }
        this.method70();
        if (!this.menuOpen) {
            this.processRightClick();
            this.drawTooltip();
        }
        else if (this.menuScreenArea == 0) {
            this.drawMenu();
        }
        if (this.anInt1055 == 1) {
            this.multiOverlay.drawSprite(5, 40);
        }
        if (client.fpsOn) {
            final int n3 = 507;
            int n4 = 20;
            int n5 = 16776960;
            if (super.fps < 15) {
                n5 = 16711680;
            }
            this.aTextDrawingArea_1271.method380("Fps:" + super.fps, n3, n5, n4);
            n4 += 15;
            final Runtime runtime = Runtime.getRuntime();
            final int n6 = (int)((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
            if (n6 > 33554432 && client.lowMem) {}
            this.aTextDrawingArea_1271.method380("Mem:" + n6 + "k", n3, 16776960, n4);
            n4 += 15;
        }
        final int n7 = this.baseX + (client.myPlayer.x - 6 >> 7);
        final int n8 = this.baseY + (client.myPlayer.y - 6 >> 7);
        if (client.clientData) {
            int n9 = 20;
            if (super.fps < 15) {}
            this.aTextDrawingArea_1271.method385(16776960, "PlayersListSize: " + this.playerCount, 272, 5);
            this.aTextDrawingArea_1271.method385(16776960, "Fps: " + super.fps, 285, 5);
            final Runtime runtime2 = Runtime.getRuntime();
            final int n10 = (int)((runtime2.totalMemory() - runtime2.freeMemory()) / 1024L);
            if (n10 > 33554432 && client.lowMem) {}
            n9 += 15;
            this.aTextDrawingArea_1271.method385(16776960, "Mem: " + n10 + "k", 299, 5);
            this.aTextDrawingArea_1271.method385(16776960, "Mouse X: " + super.mouseX + " , Mouse Y: " + super.mouseY, 314, 5);
            this.aTextDrawingArea_1271.method385(16776960, "Coords: " + n7 + ", " + n8, 329, 5);
        }
        if (this.anInt1104 != 0) {
            final int n11 = this.anInt1104 / 50;
            final int n12 = n11 / 60;
            final int n13 = n11 % 60;
            final int n14 = 329;
            System.out.println(client.clientHeight - 170 + ":329:" + client.clientHeight);
            if (n13 < 10) {
                this.aTextDrawingArea_1271.method385(16776960, "Devilishpkz System update in: " + n12 + ":0" + n13, n14, 4);
            }
            else {
                this.aTextDrawingArea_1271.method385(16776960, "Devilishpkz System update in: " + n12 + ":" + n13, n14, 4);
            }
            ++client.anInt849;
            if (client.anInt849 > 75) {
                client.anInt849 = 0;
                this.stream.createFrame(148);
            }
        }
    }
    
    private void addIgnore(final long n) {
        try {
            if (n == 0L) {
                return;
            }
            if (this.ignoreCount >= 100) {
                this.pushMessage("Your ignore list is full. Max of 100 hit", 0, "");
                return;
            }
            final String fixName = TextClass.fixName(TextClass.nameForLong(n));
            for (int i = 0; i < this.ignoreCount; ++i) {
                if (this.ignoreListAsLongs[i] == n) {
                    this.pushMessage(fixName + " is already on your ignore list", 0, "");
                    return;
                }
            }
            for (int j = 0; j < this.friendsCount; ++j) {
                if (this.friendsListAsLongs[j] == n) {
                    this.pushMessage("Please remove " + fixName + " from your friend list first", 0, "");
                    return;
                }
            }
            this.ignoreListAsLongs[this.ignoreCount++] = n;
            client.needDrawTabArea = true;
            this.stream.createFrame(133);
            this.stream.writeQWord(n);
        }
        catch (RuntimeException ex) {
            SignLink.reporterror("45688, " + n + ", " + 4 + ", " + ex.toString());
            throw new RuntimeException();
        }
    }
    
    private void method114() {
        for (int i = -1; i < this.playerCount; ++i) {
            int myPlayerIndex;
            if (i == -1) {
                myPlayerIndex = this.myPlayerIndex;
            }
            else {
                myPlayerIndex = this.playerIndices[i];
            }
            final Player player = this.playerArray[myPlayerIndex];
            if (player != null) {
                this.method96(player);
            }
        }
    }
    
    private void method115() {
        if (this.loadingStage == 2) {
            for (Class30_Sub1 class30_Sub1 = (Class30_Sub1)this.aClass19_1179.reverseGetFirst(); class30_Sub1 != null; class30_Sub1 = (Class30_Sub1)this.aClass19_1179.reverseGetNext()) {
                if (class30_Sub1.anInt1294 > 0) {
                    final Class30_Sub1 class30_Sub2 = class30_Sub1;
                    --class30_Sub2.anInt1294;
                }
                if (class30_Sub1.anInt1294 == 0) {
                    if (class30_Sub1.anInt1299 < 0 || ObjectManager.method178(class30_Sub1.anInt1299, class30_Sub1.anInt1301)) {
                        this.method142(class30_Sub1.anInt1298, class30_Sub1.anInt1295, class30_Sub1.anInt1300, class30_Sub1.anInt1301, class30_Sub1.anInt1297, class30_Sub1.anInt1296, class30_Sub1.anInt1299);
                        class30_Sub1.unlink();
                    }
                }
                else {
                    if (class30_Sub1.anInt1302 > 0) {
                        final Class30_Sub1 class30_Sub3 = class30_Sub1;
                        --class30_Sub3.anInt1302;
                    }
                    if (class30_Sub1.anInt1302 == 0 && class30_Sub1.anInt1297 >= 1 && class30_Sub1.anInt1298 >= 1 && class30_Sub1.anInt1297 <= 102 && class30_Sub1.anInt1298 <= 102 && (class30_Sub1.anInt1291 < 0 || ObjectManager.method178(class30_Sub1.anInt1291, class30_Sub1.anInt1293))) {
                        this.method142(class30_Sub1.anInt1298, class30_Sub1.anInt1295, class30_Sub1.anInt1292, class30_Sub1.anInt1293, class30_Sub1.anInt1297, class30_Sub1.anInt1296, class30_Sub1.anInt1291);
                        class30_Sub1.anInt1302 = -1;
                        if (class30_Sub1.anInt1291 == class30_Sub1.anInt1299 && class30_Sub1.anInt1299 == -1) {
                            class30_Sub1.unlink();
                        }
                        else if (class30_Sub1.anInt1291 == class30_Sub1.anInt1299 && class30_Sub1.anInt1292 == class30_Sub1.anInt1300 && class30_Sub1.anInt1293 == class30_Sub1.anInt1301) {
                            class30_Sub1.unlink();
                        }
                    }
                }
            }
        }
    }
    
    private void determineMenuSize() {
        int textWidth = this.chatTextDrawingArea.getTextWidth("Choose Option");
        for (int i = 0; i < this.menuActionRow; ++i) {
            final int textWidth2 = this.chatTextDrawingArea.getTextWidth(this.menuActionName[i]);
            if (textWidth2 > textWidth) {
                textWidth = textWidth2;
            }
        }
        textWidth += 8;
        final int n = 15 * this.menuActionRow + 21;
        if (client.clientSize == 0) {
            if (super.saveClickX > 4 && super.saveClickY > 4 && super.saveClickX < 516 && super.saveClickY < 338) {
                int menuOffsetX = super.saveClickX - 4 - textWidth / 2;
                if (menuOffsetX + textWidth > 512) {
                    menuOffsetX = 512 - textWidth;
                }
                if (menuOffsetX < 0) {
                    menuOffsetX = 0;
                }
                int menuOffsetY = super.saveClickY - 4;
                if (menuOffsetY + n > 334) {
                    menuOffsetY = 334 - n;
                }
                if (menuOffsetY < 0) {
                    menuOffsetY = 0;
                }
                this.menuOpen = true;
                this.menuScreenArea = 0;
                this.menuOffsetX = menuOffsetX;
                this.menuOffsetY = menuOffsetY;
                this.menuWidth = textWidth;
                this.menuHeight = 15 * this.menuActionRow + 22;
            }
            if (super.saveClickX > 519 && super.saveClickY > 168 && super.saveClickX < 765 && super.saveClickY < 503) {
                int menuOffsetX2 = super.saveClickX - 519 - textWidth / 2;
                if (menuOffsetX2 < 0) {
                    menuOffsetX2 = 0;
                }
                else if (menuOffsetX2 + textWidth > 245) {
                    menuOffsetX2 = 245 - textWidth;
                }
                int menuOffsetY2 = super.saveClickY - 168;
                if (menuOffsetY2 < 0) {
                    menuOffsetY2 = 0;
                }
                else if (menuOffsetY2 + n > 333) {
                    menuOffsetY2 = 333 - n;
                }
                this.menuOpen = true;
                this.menuScreenArea = 1;
                this.menuOffsetX = menuOffsetX2;
                this.menuOffsetY = menuOffsetY2;
                this.menuWidth = textWidth;
                this.menuHeight = 15 * this.menuActionRow + 22;
            }
            if (super.saveClickX > 0 && super.saveClickY > 338 && super.saveClickX < 516 && super.saveClickY < 503) {
                int menuOffsetX3 = super.saveClickX - 0 - textWidth / 2;
                if (menuOffsetX3 < 0) {
                    menuOffsetX3 = 0;
                }
                else if (menuOffsetX3 + textWidth > 516) {
                    menuOffsetX3 = 516 - textWidth;
                }
                int menuOffsetY3 = super.saveClickY - 338;
                if (menuOffsetY3 < 0) {
                    menuOffsetY3 = 0;
                }
                else if (menuOffsetY3 + n > 165) {
                    menuOffsetY3 = 165 - n;
                }
                this.menuOpen = true;
                this.menuScreenArea = 2;
                this.menuOffsetX = menuOffsetX3;
                this.menuOffsetY = menuOffsetY3;
                this.menuWidth = textWidth;
                this.menuHeight = 15 * this.menuActionRow + 22;
            }
            if (super.saveClickX > 519 && super.saveClickY > 0 && super.saveClickX < 765 && super.saveClickY < 168) {
                int menuOffsetX4 = super.saveClickX - 519 - textWidth / 2;
                if (menuOffsetX4 < 0) {
                    menuOffsetX4 = 0;
                }
                else if (menuOffsetX4 + textWidth > 765) {
                    menuOffsetX4 = 765 - textWidth;
                }
                int menuOffsetY4 = super.saveClickY - 0;
                if (menuOffsetY4 < 0) {
                    menuOffsetY4 = 0;
                }
                else if (menuOffsetY4 + n > 168) {
                    menuOffsetY4 = 168 - n;
                }
                this.menuOpen = true;
                this.menuScreenArea = 3;
                this.menuOffsetX = menuOffsetX4;
                this.menuOffsetY = menuOffsetY4;
                this.menuWidth = textWidth;
                this.menuHeight = 15 * this.menuActionRow + 22;
            }
        }
        else if (super.saveClickX > 0 && super.saveClickY > 0 && super.saveClickX < client.clientWidth && super.saveClickY < client.clientHeight) {
            int menuOffsetX5 = super.saveClickX - 0 - textWidth / 2;
            if (menuOffsetX5 + textWidth > client.clientWidth) {
                menuOffsetX5 = client.clientWidth - textWidth;
            }
            if (menuOffsetX5 < 0) {
                menuOffsetX5 = 0;
            }
            int menuOffsetY5 = super.saveClickY - 0;
            if (menuOffsetY5 + n > client.clientHeight) {
                menuOffsetY5 = client.clientHeight - n;
            }
            if (menuOffsetY5 < 0) {
                menuOffsetY5 = 0;
            }
            this.menuOpen = true;
            this.menuScreenArea = 0;
            this.menuOffsetX = menuOffsetX5;
            this.menuOffsetY = menuOffsetY5;
            this.menuWidth = textWidth;
            this.menuHeight = 15 * this.menuActionRow + 22;
        }
    }
    
    private void method117(final Stream stream) {
        stream.initBitAccess();
        if (stream.readBits(1) == 0) {
            return;
        }
        final int bits = stream.readBits(2);
        if (bits == 0) {
            this.anIntArray894[this.anInt893++] = this.myPlayerIndex;
            return;
        }
        if (bits == 1) {
            client.myPlayer.moveInDir(false, stream.readBits(3));
            if (stream.readBits(1) == 1) {
                this.anIntArray894[this.anInt893++] = this.myPlayerIndex;
            }
            return;
        }
        if (bits == 2) {
            client.myPlayer.moveInDir(true, stream.readBits(3));
            client.myPlayer.moveInDir(true, stream.readBits(3));
            if (stream.readBits(1) == 1) {
                this.anIntArray894[this.anInt893++] = this.myPlayerIndex;
            }
            return;
        }
        if (bits == 3) {
            this.plane = stream.readBits(2);
            final int bits2 = stream.readBits(1);
            if (stream.readBits(1) == 1) {
                this.anIntArray894[this.anInt893++] = this.myPlayerIndex;
            }
            client.myPlayer.setPos(stream.readBits(7), stream.readBits(7), bits2 == 1);
        }
    }
    
    private void nullLoader() {
        this.aBoolean831 = false;
        while (this.drawingFlames) {
            this.aBoolean831 = false;
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {}
        }
        this.aBackground_966 = null;
        this.aBackground_967 = null;
        this.aBackgroundArray1152s = null;
        this.anIntArray850 = null;
        this.anIntArray851 = null;
        this.anIntArray852 = null;
        this.anIntArray853 = null;
        this.anIntArray1190 = null;
        this.anIntArray1191 = null;
        this.anIntArray828 = null;
        this.anIntArray829 = null;
        this.aClass30_Sub2_Sub1_Sub1_1201 = null;
        this.aClass30_Sub2_Sub1_Sub1_1202 = null;
    }
    
    private boolean method119(final int n, final int n2) {
        boolean b = false;
        final RSInterface rsInterface = RSInterface.interfaceCache[n2];
        for (int n3 = 0; n3 < rsInterface.children.length && rsInterface.children[n3] != -1; ++n3) {
            final RSInterface rsInterface2 = RSInterface.interfaceCache[rsInterface.children[n3]];
            if (rsInterface2.type == 1) {
                b |= this.method119(n, rsInterface2.id);
            }
            if (rsInterface2.type == 6 && (rsInterface2.anInt257 != -1 || rsInterface2.anInt258 != -1)) {
                int n4;
                if (this.interfaceIsSelected(rsInterface2)) {
                    n4 = rsInterface2.anInt258;
                }
                else {
                    n4 = rsInterface2.anInt257;
                }
                if (n4 != -1) {
                    final Animation animation = Animation.anims[n4];
                    final RSInterface rsInterface3 = rsInterface2;
                    rsInterface3.anInt208 += n;
                    while (rsInterface2.anInt208 > animation.method258(rsInterface2.anInt246)) {
                        final RSInterface rsInterface4 = rsInterface2;
                        rsInterface4.anInt208 -= animation.method258(rsInterface2.anInt246) + 1;
                        final RSInterface rsInterface5 = rsInterface2;
                        ++rsInterface5.anInt246;
                        if (rsInterface2.anInt246 >= animation.anInt352) {
                            final RSInterface rsInterface6 = rsInterface2;
                            rsInterface6.anInt246 -= animation.anInt356;
                            if (rsInterface2.anInt246 < 0 || rsInterface2.anInt246 >= animation.anInt352) {
                                rsInterface2.anInt246 = 0;
                            }
                        }
                        b = true;
                    }
                }
            }
        }
        return b;
    }
    
    private int method120() {
        int n = 3;
        if (this.yCameraCurve < 310) {
            int i = this.xCameraPos >> 7;
            int j = this.yCameraPos >> 7;
            final int n2 = client.myPlayer.x >> 7;
            final int n3 = client.myPlayer.y >> 7;
            if ((this.byteGroundArray[this.plane][i][j] & 0x4) != 0x0) {
                n = this.plane;
            }
            int n4;
            if (n2 > i) {
                n4 = n2 - i;
            }
            else {
                n4 = i - n2;
            }
            int n5;
            if (n3 > j) {
                n5 = n3 - j;
            }
            else {
                n5 = j - n3;
            }
            if (n4 > n5) {
                final int n6 = n5 * 65536 / n4;
                int n7 = 32768;
                while (i != n2) {
                    if (i < n2) {
                        ++i;
                    }
                    else if (i > n2) {
                        --i;
                    }
                    if ((this.byteGroundArray[this.plane][i][j] & 0x4) != 0x0) {
                        n = this.plane;
                    }
                    n7 += n6;
                    if (n7 >= 65536) {
                        n7 -= 65536;
                        if (j < n3) {
                            ++j;
                        }
                        else if (j > n3) {
                            --j;
                        }
                        if ((this.byteGroundArray[this.plane][i][j] & 0x4) == 0x0) {
                            continue;
                        }
                        n = this.plane;
                    }
                }
            }
            else {
                final int n8 = n4 * 65536 / n5;
                int n9 = 32768;
                while (j != n3) {
                    if (j < n3) {
                        ++j;
                    }
                    else if (j > n3) {
                        --j;
                    }
                    if ((this.byteGroundArray[this.plane][i][j] & 0x4) != 0x0) {
                        n = this.plane;
                    }
                    n9 += n8;
                    if (n9 >= 65536) {
                        n9 -= 65536;
                        if (i < n2) {
                            ++i;
                        }
                        else if (i > n2) {
                            --i;
                        }
                        if ((this.byteGroundArray[this.plane][i][j] & 0x4) == 0x0) {
                            continue;
                        }
                        n = this.plane;
                    }
                }
            }
        }
        if ((this.byteGroundArray[this.plane][client.myPlayer.x >> 7][client.myPlayer.y >> 7] & 0x4) != 0x0) {
            n = this.plane;
        }
        return n;
    }
    
    private int method121() {
        if (this.method42(this.plane, this.yCameraPos, this.xCameraPos) - this.zCameraPos < 800 && (this.byteGroundArray[this.plane][this.xCameraPos >> 7][this.yCameraPos >> 7] & 0x4) != 0x0) {
            return this.plane;
        }
        return 3;
    }
    
    private void delIgnore(final long n) {
        try {
            if (n == 0L) {
                return;
            }
            for (int i = 0; i < this.ignoreCount; ++i) {
                if (this.ignoreListAsLongs[i] == n) {
                    --this.ignoreCount;
                    client.needDrawTabArea = true;
                    System.arraycopy(this.ignoreListAsLongs, i + 1, this.ignoreListAsLongs, i, this.ignoreCount - i);
                    this.stream.createFrame(74);
                    this.stream.writeQWord(n);
                    return;
                }
            }
        }
        catch (RuntimeException ex) {
            SignLink.reporterror("47229, 3, " + n + ", " + ex.toString());
            throw new RuntimeException();
        }
    }
    
    private void chatJoin(final long n) {
        try {
            if (n == 0L) {
                return;
            }
            this.stream.createFrame(60);
            this.stream.writeQWord(n);
        }
        catch (RuntimeException ex) {
            SignLink.reporterror("47229, 3, " + n + ", " + ex.toString());
            throw new RuntimeException();
        }
    }
    
    @Override
    public String getParameter(final String s) {
        if (SignLink.mainapp != null) {
            return SignLink.mainapp.getParameter(s);
        }
        return super.getParameter(s);
    }
    
    private void adjustVolume(final boolean b, final int midivol) {
        SignLink.midivol = midivol;
        if (b) {
            SignLink.midi = "voladjust";
        }
    }
    
    private int extractInterfaceValues(final RSInterface rsInterface, final int n) {
        if (rsInterface.valueIndexArray == null || n >= rsInterface.valueIndexArray.length) {
            return -2;
        }
        try {
            final int[] array = rsInterface.valueIndexArray[n];
            int n2 = 0;
            int n3 = 0;
            int n4 = 0;
            while (true) {
                final int n5 = array[n3++];
                int n6 = 0;
                int n7 = 0;
                if (n5 == 0) {
                    break;
                }
                if (n5 == 1) {
                    n6 = this.currentStats[array[n3++]];
                }
                if (n5 == 2) {
                    n6 = this.maxStats[array[n3++]];
                }
                if (n5 == 3) {
                    n6 = this.currentExp[array[n3++]];
                }
                if (n5 == 4) {
                    final RSInterface rsInterface2 = RSInterface.interfaceCache[array[n3++]];
                    final int n8 = array[n3++];
                    if (n8 >= 0 && n8 < ItemDef.totalItems && (!ItemDef.forID(n8).membersObject || client.isMembers)) {
                        for (int i = 0; i < rsInterface2.inv.length; ++i) {
                            if (rsInterface2.inv[i] == n8 + 1) {
                                n6 += rsInterface2.invStackSizes[i];
                            }
                        }
                    }
                }
                if (n5 == 5) {
                    n6 = this.variousSettings[array[n3++]];
                }
                if (n5 == 6) {
                    n6 = client.anIntArray1019[this.maxStats[array[n3++]] - 1];
                }
                if (n5 == 7) {
                    n6 = this.variousSettings[array[n3++]] * 100 / 46875;
                }
                if (n5 == 8) {
                    n6 = client.myPlayer.combatLevel;
                }
                if (n5 == 9) {
                    for (int j = 0; j < Skills.skillsCount; ++j) {
                        if (Skills.skillEnabled[j]) {
                            n6 += this.maxStats[j];
                        }
                    }
                }
                if (n5 == 10) {
                    final RSInterface rsInterface3 = RSInterface.interfaceCache[array[n3++]];
                    final int n9 = array[n3++] + 1;
                    if (n9 >= 0 && n9 < ItemDef.totalItems && (!ItemDef.forID(n9).membersObject || client.isMembers)) {
                        for (int k = 0; k < rsInterface3.inv.length; ++k) {
                            if (rsInterface3.inv[k] == n9) {
                                n6 = 999999999;
                                break;
                            }
                        }
                    }
                }
                if (n5 == 11) {
                    n6 = this.energy;
                }
                if (n5 == 12) {
                    n6 = this.weight;
                }
                if (n5 == 13) {
                    n6 = (((this.variousSettings[array[n3++]] & 1 << array[n3++]) != 0x0) ? 1 : 0);
                }
                if (n5 == 14) {
                    final VarBit varBit = VarBit.cache[array[n3++]];
                    final int anInt648 = varBit.anInt648;
                    final int anInt649 = varBit.anInt649;
                    n6 = (this.variousSettings[anInt648] >> anInt649 & client.anIntArray1232[varBit.anInt650 - anInt649]);
                }
                if (n5 == 15) {
                    n7 = 1;
                }
                if (n5 == 16) {
                    n7 = 2;
                }
                if (n5 == 17) {
                    n7 = 3;
                }
                if (n5 == 18) {
                    n6 = (client.myPlayer.x >> 7) + this.baseX;
                }
                if (n5 == 19) {
                    n6 = (client.myPlayer.y >> 7) + this.baseY;
                }
                if (n5 == 20) {
                    n6 = array[n3++];
                }
                if (n7 == 0) {
                    if (n4 == 0) {
                        n2 += n6;
                    }
                    if (n4 == 1) {
                        n2 -= n6;
                    }
                    if (n4 == 2 && n6 != 0) {
                        n2 /= n6;
                    }
                    if (n4 == 3) {
                        n2 *= n6;
                    }
                    n4 = 0;
                }
                else {
                    n4 = n7;
                }
            }
            return n2;
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    private void drawTooltip() {
        if (this.menuActionRow < 2 && this.itemSelected == 0 && this.spellSelected == 0) {
            return;
        }
        String s;
        if (this.itemSelected == 1 && this.menuActionRow < 2) {
            s = "Use " + this.selectedItemName + " with...";
        }
        else if (this.spellSelected == 1 && this.menuActionRow < 2) {
            s = this.spellTooltip + "...";
        }
        else {
            s = this.menuActionName[this.menuActionRow - 1];
        }
        if (this.menuActionRow > 2) {
            s = s + "@whi@ / " + (this.menuActionRow - 2) + " more options";
        }
        this.chatTextDrawingArea.method390(4, 16777215, s, client.loopCycle / 1000, 15);
    }
    
    private void drawMinimap() {
        if (this.menuOpen && this.menuScreenArea == 3) {
            this.drawMenu();
        }
        final int n = (client.clientSize != 0) ? (client.clientWidth - 164) : 0;
        if (client.clientSize == 0) {
            this.mapAreaIP.initDrawingArea();
        }
        if (this.anInt1021 == 2) {
            if (client.clientSize == 0) {
                this.compass.method352(33, this.minimapInt1, this.anIntArray1057, 256, this.anIntArray968, 25, 8, 10 + n, 33, 25);
                this.customMapArea.drawSprite(0, 0);
                this.loadOrbs();
            }
            else {
                DrawingArea.drawPixels(3, 79, 80 + n, 16777215, 3);
                this.fsSprite[0].drawSprite(0 + n, 0);
                this.fsSprite[1].drawSprite(0 + n, 0);
                this.compass.method352(33, this.minimapInt1, this.anIntArray1057, 256, this.anIntArray968, 25, 5, 5 + n, 33, 25);
            }
            this.drawXPCounter();
            this.gameScreenIP.initDrawingArea();
            return;
        }
        final int n2 = this.minimapInt1 + this.minimapInt2 & 0x7FF;
        final int n3 = 48 + client.myPlayer.x / 32;
        final int n4 = 464 - client.myPlayer.y / 32;
        if (client.clientSize == 0) {
            this.aClass30_Sub2_Sub1_Sub1_1263.method352(151, n2, this.anIntArray1229, 256 + this.minimapInt3, this.anIntArray1052, n4, 10, 31, 138, n3);
        }
        else if (client.clientSize >= 1) {
            this.aClass30_Sub2_Sub1_Sub1_1263.method352(152, n2, this.anIntArray1229, 256 + this.minimapInt3, this.anIntArray1052, n4, 5, 5 + n, 146, n3);
        }
        for (int i = 0; i < this.anInt1071; ++i) {
            this.markMinimap(this.aClass30_Sub2_Sub1_Sub1Array1140[i], this.anIntArray1072[i] * 4 + 2 - client.myPlayer.x / 32, this.anIntArray1073[i] * 4 + 2 - client.myPlayer.y / 32);
        }
        for (int j = 0; j < 104; ++j) {
            for (int k = 0; k < 104; ++k) {
                if (this.groundArray[this.plane][j][k] != null) {
                    this.markMinimap(this.mapDotItem, j * 4 + 2 - client.myPlayer.x / 32, k * 4 + 2 - client.myPlayer.y / 32);
                }
            }
        }
        for (int l = 0; l < this.npcCount; ++l) {
            final NPC npc = this.npcArray[this.npcIndices[l]];
            if (npc != null && npc.isVisible()) {
                EntityDef entityDef = npc.desc;
                if (entityDef.childrenIDs != null) {
                    entityDef = entityDef.method161();
                }
                if (entityDef != null && entityDef.aBoolean87 && entityDef.aBoolean84) {
                    this.markMinimap(this.mapDotNPC, npc.x / 32 - client.myPlayer.x / 32, npc.y / 32 - client.myPlayer.y / 32);
                }
            }
        }
        for (int n5 = 0; n5 < this.playerCount; ++n5) {
            final Player player = this.playerArray[this.playerIndices[n5]];
            if (player != null && player.isVisible()) {
                final int n6 = player.x / 32 - client.myPlayer.x / 32;
                final int n7 = player.y / 32 - client.myPlayer.y / 32;
                boolean b = false;
                boolean b2 = false;
                for (int n8 = 0; n8 < this.clanList.length; ++n8) {
                    if (this.clanList[n8] != null) {
                        if (this.clanList[n8].equalsIgnoreCase(player.name)) {
                            b2 = true;
                            break;
                        }
                    }
                }
                final long longForName = TextClass.longForName(player.name);
                for (int n9 = 0; n9 < this.friendsCount; ++n9) {
                    if (longForName == this.friendsListAsLongs[n9] && this.friendsNodeIDs[n9] != 0) {
                        b = true;
                        break;
                    }
                }
                boolean b3 = false;
                if (client.myPlayer.team != 0 && player.team != 0 && client.myPlayer.team == player.team) {
                    b3 = true;
                }
                if (b) {
                    this.markMinimap(this.mapDotFriend, n6, n7);
                }
                else if (b2) {
                    this.markMinimap(this.mapDotClan, n6, n7);
                }
                else if (b3) {
                    this.markMinimap(this.mapDotTeam, n6, n7);
                }
                else {
                    this.markMinimap(this.mapDotPlayer, n6, n7);
                }
            }
        }
        if (this.anInt855 != 0 && client.loopCycle % 20 < 10) {
            if (this.anInt855 == 1 && this.anInt1222 >= 0 && this.anInt1222 < this.npcArray.length) {
                final NPC npc2 = this.npcArray[this.anInt1222];
                if (npc2 != null) {
                    this.method81(this.mapMarker, npc2.y / 32 - client.myPlayer.y / 32, npc2.x / 32 - client.myPlayer.x / 32);
                }
            }
            if (this.anInt855 == 2) {
                this.method81(this.mapMarker, (this.anInt935 - this.baseY) * 4 + 2 - client.myPlayer.y / 32, (this.anInt934 - this.baseX) * 4 + 2 - client.myPlayer.x / 32);
            }
            if (this.anInt855 == 10 && this.anInt933 >= 0 && this.anInt933 < this.playerArray.length) {
                final Player player2 = this.playerArray[this.anInt933];
                if (player2 != null) {
                    this.method81(this.mapMarker, player2.y / 32 - client.myPlayer.y / 32, player2.x / 32 - client.myPlayer.x / 32);
                }
            }
        }
        if (this.destX != 0) {
            this.markMinimap(this.mapFlag, this.destX * 4 + 2 - client.myPlayer.x / 32, this.destY * 4 + 2 - client.myPlayer.y / 32);
        }
        if (client.clientSize == 0) {
            this.CustomMapBack.drawSprite(0, 0);
            DrawingArea.drawPixels(3, 82, 99 + n, 16777215, 3);
            this.compass.method352(33, this.minimapInt1, this.anIntArray1057, 256, this.anIntArray968, 25, 8, 10 + n, 33, 25);
            this.loadOrbs();
            this.drawXPCounter();
            this.logoutX.drawSprite(226, 3);
        }
        else if (client.clientSize >= 1) {
            DrawingArea.drawPixels(3, 80, 78 + n, 16777215, 3);
            this.fsSprite[0].drawSprite(0 + n, 0);
            this.fsSprite[1].drawSprite(0 + n, 0);
            this.drawFullOrbs(n);
            this.compass.method352(33, this.minimapInt1, this.anIntArray1057, 256, this.anIntArray968, 25, 5, 5 + n, 33, 25);
        }
        this.drawXPCounter();
        if (this.menuOpen && this.menuScreenArea == 3) {
            this.drawMenu();
        }
        this.gameScreenIP.initDrawingArea();
    }
    
    private void npcScreenPos(final Entity entity, final int n) {
        this.calcEntityScreenPos(entity.x, n, entity.y);
    }
    
    private void calcEntityScreenPos(int n, final int n2, int n3) {
        if (n < 128 || n3 < 128 || n > 13056 || n3 > 13056) {
            this.spriteDrawX = -1;
            this.spriteDrawY = -1;
            return;
        }
        final int n4 = this.method42(this.plane, n3, n) - n2;
        n -= this.xCameraPos;
        final int n5 = n4 - this.zCameraPos;
        n3 -= this.yCameraPos;
        final int n6 = Model.modelIntArray1[this.yCameraCurve];
        final int n7 = Model.modelIntArray2[this.yCameraCurve];
        final int n8 = Model.modelIntArray1[this.xCameraCurve];
        final int n9 = Model.modelIntArray2[this.xCameraCurve];
        final int n10 = n3 * n8 + n * n9 >> 16;
        n3 = n3 * n9 - n * n8 >> 16;
        n = n10;
        final int n11 = n5 * n7 - n3 * n6 >> 16;
        n3 = n5 * n6 + n3 * n7 >> 16;
        final int n12 = n11;
        if (n3 >= 50) {
            this.spriteDrawX = Texture.textureInt1 + (n << 9) / n3;
            this.spriteDrawY = Texture.textureInt2 + (n12 << 9) / n3;
        }
        else {
            this.spriteDrawX = -1;
            this.spriteDrawY = -1;
        }
    }
    
    private void buildSplitPrivateChatMenu() {
        if (this.splitPrivateChat == 0) {
            return;
        }
        int n = 0;
        if (this.anInt1104 != 0) {
            n = 1;
        }
        for (int i = 0; i < 500; ++i) {
            if (this.chatMessages[i] != null) {
                final int n2 = this.chatTypes[i];
                String s = this.chatNames[i];
                if (s != null && s.startsWith("@cr1@")) {
                    s = s.substring(5);
                }
                if (s != null && s.startsWith("@cr2@")) {
                    s = s.substring(5);
                }
                if (s != null && s.startsWith("@cr4@")) {
                    s = s.substring(5);
                }
                if ((n2 == 3 || n2 == 7) && (n2 == 7 || this.privateChatMode == 0 || (this.privateChatMode == 1 && this.isFriendOrSelf(s)))) {
                    final int n3 = 329 - n * 13;
                    if (super.mouseX > 4 && super.mouseY - 4 > n3 - 10 && super.mouseY - 4 <= n3 + 3) {
                        int n4 = this.aTextDrawingArea_1271.getTextWidth("From:  " + s + this.chatMessages[i]) + 25;
                        if (n4 > 450) {
                            n4 = 450;
                        }
                        if (super.mouseX < 4 + n4) {
                            if (this.myPrivilege >= 1) {
                                this.menuActionName[this.menuActionRow] = "Report abuse @whi@" + s;
                                this.menuActionID[this.menuActionRow] = 2606;
                                ++this.menuActionRow;
                            }
                            this.menuActionName[this.menuActionRow] = "Add ignore @whi@" + s;
                            this.menuActionID[this.menuActionRow] = 2042;
                            ++this.menuActionRow;
                            this.menuActionName[this.menuActionRow] = "Add friend @whi@" + s;
                            this.menuActionID[this.menuActionRow] = 2337;
                            ++this.menuActionRow;
                        }
                    }
                    if (++n >= 5) {
                        return;
                    }
                }
                if ((n2 == 5 || n2 == 6) && this.privateChatMode < 2 && ++n >= 5) {
                    return;
                }
            }
        }
    }
    
    private void method130(final int anInt1294, final int anInt1295, final int anInt1296, final int anInt1297, final int anInt1298, final int anInt1299, final int anInt1300, final int anInt1301, final int anInt1302) {
        Class30_Sub1 class30_Sub1 = null;
        for (Class30_Sub1 class30_Sub2 = (Class30_Sub1)this.aClass19_1179.reverseGetFirst(); class30_Sub2 != null; class30_Sub2 = (Class30_Sub1)this.aClass19_1179.reverseGetNext()) {
            if (class30_Sub2.anInt1295 == anInt1300 && class30_Sub2.anInt1297 == anInt1301 && class30_Sub2.anInt1298 == anInt1298 && class30_Sub2.anInt1296 == anInt1297) {
                class30_Sub1 = class30_Sub2;
                break;
            }
        }
        if (class30_Sub1 == null) {
            class30_Sub1 = new Class30_Sub1();
            class30_Sub1.anInt1295 = anInt1300;
            class30_Sub1.anInt1296 = anInt1297;
            class30_Sub1.anInt1297 = anInt1301;
            class30_Sub1.anInt1298 = anInt1298;
            this.method89(class30_Sub1);
            this.aClass19_1179.insertHead(class30_Sub1);
        }
        class30_Sub1.anInt1291 = anInt1295;
        class30_Sub1.anInt1293 = anInt1299;
        class30_Sub1.anInt1292 = anInt1296;
        class30_Sub1.anInt1302 = anInt1302;
        class30_Sub1.anInt1294 = anInt1294;
    }
    
    private boolean interfaceIsSelected(final RSInterface rsInterface) {
        if (rsInterface.anIntArray245 == null) {
            return false;
        }
        for (int i = 0; i < rsInterface.anIntArray245.length; ++i) {
            final int interfaceValues = this.extractInterfaceValues(rsInterface, i);
            final int n = rsInterface.anIntArray212[i];
            if (rsInterface.anIntArray245[i] == 2) {
                if (interfaceValues >= n) {
                    return false;
                }
            }
            else if (rsInterface.anIntArray245[i] == 3) {
                if (interfaceValues <= n) {
                    return false;
                }
            }
            else if (rsInterface.anIntArray245[i] == 4) {
                if (interfaceValues == n) {
                    return false;
                }
            }
            else if (interfaceValues != n) {
                return false;
            }
        }
        return true;
    }
    
    private DataInputStream openJagGrabInputStream(final String s) throws IOException {
        if (this.aSocket832 != null) {
            try {
                this.aSocket832.close();
            }
            catch (Exception ex) {}
            this.aSocket832 = null;
        }
        (this.aSocket832 = this.openSocket(43594)).setSoTimeout(10000);
        final InputStream inputStream = this.aSocket832.getInputStream();
        this.aSocket832.getOutputStream().write(("JAGGRAB /" + s + "\n\n").getBytes());
        return new DataInputStream(inputStream);
    }
    
    private void doFlamesDrawing() {
        final int n = 256;
        if (this.anInt1040 > 0) {
            for (int i = 0; i < 256; ++i) {
                if (this.anInt1040 > 768) {
                    this.anIntArray850[i] = this.method83(this.anIntArray851[i], this.anIntArray852[i], 1024 - this.anInt1040);
                }
                else if (this.anInt1040 > 256) {
                    this.anIntArray850[i] = this.anIntArray852[i];
                }
                else {
                    this.anIntArray850[i] = this.method83(this.anIntArray852[i], this.anIntArray851[i], 256 - this.anInt1040);
                }
            }
        }
        else if (this.anInt1041 > 0) {
            for (int j = 0; j < 256; ++j) {
                if (this.anInt1041 > 768) {
                    this.anIntArray850[j] = this.method83(this.anIntArray851[j], this.anIntArray853[j], 1024 - this.anInt1041);
                }
                else if (this.anInt1041 > 256) {
                    this.anIntArray850[j] = this.anIntArray853[j];
                }
                else {
                    this.anIntArray850[j] = this.method83(this.anIntArray853[j], this.anIntArray851[j], 256 - this.anInt1041);
                }
            }
        }
        else {
            System.arraycopy(this.anIntArray851, 0, this.anIntArray850, 0, 256);
        }
        System.arraycopy(this.aClass30_Sub2_Sub1_Sub1_1201.myPixels, 0, this.aRSImageProducer_1110.anIntArray315, 0, 33920);
        int n2 = 0;
        int n3 = 1152;
        for (int k = 1; k < n - 1; ++k) {
            int n4 = 22 + this.anIntArray969[k] * (n - k) / n;
            if (n4 < 0) {
                n4 = 0;
            }
            n2 += n4;
            for (int l = n4; l < 128; ++l) {
                final int n5 = this.anIntArray828[n2++];
                if (n5 != 0) {
                    final int n6 = n5;
                    final int n7 = 256 - n5;
                    final int n8 = this.anIntArray850[n5];
                    final int n9 = this.aRSImageProducer_1110.anIntArray315[n3];
                    this.aRSImageProducer_1110.anIntArray315[n3++] = ((n8 & 0xFF00FF) * n6 + (n9 & 0xFF00FF) * n7 & 0xFF00FF00) + ((n8 & 0xFF00) * n6 + (n9 & 0xFF00) * n7 & 0xFF0000) >> 8;
                }
                else {
                    ++n3;
                }
            }
            n3 += n4;
        }
        this.aRSImageProducer_1110.drawGraphics(0, super.graphics, 0);
        System.arraycopy(this.aClass30_Sub2_Sub1_Sub1_1202.myPixels, 0, this.aRSImageProducer_1111.anIntArray315, 0, 33920);
        int n10 = 0;
        int n11 = 1176;
        for (int n12 = 1; n12 < n - 1; ++n12) {
            final int n13 = this.anIntArray969[n12] * (n - n12) / n;
            final int n14 = 103 - n13;
            int n15 = n11 + n13;
            for (int n16 = 0; n16 < n14; ++n16) {
                final int n17 = this.anIntArray828[n10++];
                if (n17 != 0) {
                    final int n18 = n17;
                    final int n19 = 256 - n17;
                    final int n20 = this.anIntArray850[n17];
                    final int n21 = this.aRSImageProducer_1111.anIntArray315[n15];
                    this.aRSImageProducer_1111.anIntArray315[n15++] = ((n20 & 0xFF00FF) * n18 + (n21 & 0xFF00FF) * n19 & 0xFF00FF00) + ((n20 & 0xFF00) * n18 + (n21 & 0xFF00) * n19 & 0xFF0000) >> 8;
                }
                else {
                    ++n15;
                }
            }
            n10 += 128 - n14;
            n11 = n15 + (128 - n14 - n13);
        }
        this.aRSImageProducer_1111.drawGraphics(0, super.graphics, 637);
    }
    
    private void method134(final Stream stream) {
        try {
            final int bits = stream.readBits(8);
            if (bits < this.playerCount) {
                for (int i = bits; i < this.playerCount; ++i) {
                    this.anIntArray840[this.anInt839++] = this.playerIndices[i];
                }
            }
            if (bits > this.playerCount) {
                SignLink.reporterror(this.myUsername + " Too many players");
                throw new RuntimeException("eek");
            }
            this.playerCount = 0;
            for (int j = 0; j < bits; ++j) {
                final int n = this.playerIndices[j];
                final Player player = this.playerArray[n];
                if (stream.readBits(1) == 0) {
                    this.playerIndices[this.playerCount++] = n;
                    player.anInt1537 = client.loopCycle;
                }
                else {
                    final int bits2 = stream.readBits(2);
                    if (bits2 == 0) {
                        this.playerIndices[this.playerCount++] = n;
                        player.anInt1537 = client.loopCycle;
                        this.anIntArray894[this.anInt893++] = n;
                    }
                    else if (bits2 == 1) {
                        this.playerIndices[this.playerCount++] = n;
                        player.anInt1537 = client.loopCycle;
                        player.moveInDir(false, stream.readBits(3));
                        if (stream.readBits(1) == 1) {
                            this.anIntArray894[this.anInt893++] = n;
                        }
                    }
                    else if (bits2 == 2) {
                        this.playerIndices[this.playerCount++] = n;
                        player.anInt1537 = client.loopCycle;
                        player.moveInDir(true, stream.readBits(3));
                        player.moveInDir(true, stream.readBits(3));
                        if (stream.readBits(1) == 1) {
                            this.anIntArray894[this.anInt893++] = n;
                        }
                    }
                    else if (bits2 == 3) {
                        this.anIntArray840[this.anInt839++] = n;
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void drawLoginScreen(final boolean b) {
        this.resetImageProducers();
        this.backgroundFix.drawSprite(0, 0);
        this.aRSImageProducer_1109.initDrawingArea();
        this.titleBox.drawBackground(254, 140);
        final int n = 360;
        if (this.loginScreenState == 0) {
            this.aTextDrawingArea_1271.method382(7711145, n / 190, this.onDemandFetcher.statusString, 100, true);
            if (this.mouseX >= 276 && this.mouseX <= 493 && this.mouseY >= 199 && this.mouseY <= 224) {
                this.boxHover.drawSprite(276, 198);
            }
            else if (this.mouseX >= 276 && this.mouseX <= 492 && this.mouseY >= 245 && this.mouseY <= 272) {
                this.boxHover.drawSprite(276, 244);
            }
            this.smallText.method382(16777215, 385, this.loginMessage1, 283, true);
            this.smallText.method382(16777215, 385, this.loginMessage2, 297, true);
            this.aTextDrawingArea_1271.method389(false, 281, 16777215, "" + capitalize(this.myUsername) + ((this.loginScreenCursorPos == 0 & client.loopCycle % 40 < 20) ? "|" : ""), 217);
            this.aTextDrawingArea_1271.method389(true, 281, 16777215, "" + TextClass.passwordAsterisks(this.myPassword) + ((this.loginScreenCursorPos == 1 & client.loopCycle % 40 < 20) ? "|" : ""), 263);
            if (this.mouseX >= 293 && this.mouseX <= 474 && this.mouseY >= 302 && this.mouseY <= 327) {
                this.loginHover.drawSprite(296, 301);
            }
        }
        this.aRSImageProducer_1109.drawGraphics(0, super.graphics, 0);
        if (this.welcomeScreenRaised) {}
    }
    
    private void drawFlames() {
    }
    
    public void raiseWelcomeScreen() {
        this.welcomeScreenRaised = true;
    }
    
    private void method137(final Stream stream, final int n) {
        if (n == 84) {
            final int unsignedByte = stream.readUnsignedByte();
            final int n2 = this.anInt1268 + (unsignedByte >> 4 & 0x7);
            final int n3 = this.anInt1269 + (unsignedByte & 0x7);
            final int unsignedWord = stream.readUnsignedWord();
            final int unsignedWord2 = stream.readUnsignedWord();
            final int unsignedWord3 = stream.readUnsignedWord();
            if (n2 >= 0 && n3 >= 0 && n2 < 104 && n3 < 104) {
                final NodeList list = this.groundArray[this.plane][n2][n3];
                if (list != null) {
                    for (Item item = (Item)list.reverseGetFirst(); item != null; item = (Item)list.reverseGetNext()) {
                        if (item.ID == (unsignedWord & 0x7FFF) && item.anInt1559 == unsignedWord2) {
                            item.anInt1559 = unsignedWord3;
                            break;
                        }
                    }
                    this.spawnGroundItem(n2, n3);
                }
            }
            return;
        }
        if (n == 105) {
            final int unsignedByte2 = stream.readUnsignedByte();
            final int n4 = this.anInt1268 + (unsignedByte2 >> 4 & 0x7);
            final int n5 = this.anInt1269 + (unsignedByte2 & 0x7);
            final int unsignedWord4 = stream.readUnsignedWord();
            final int unsignedByte3 = stream.readUnsignedByte();
            final int n6 = unsignedByte3 >> 4 & 0xF;
            final int n7 = unsignedByte3 & 0x7;
            if (client.myPlayer.smallX[0] >= n4 - n6 && client.myPlayer.smallX[0] <= n4 + n6 && client.myPlayer.smallY[0] >= n5 - n6 && client.myPlayer.smallY[0] <= n5 + n6 && this.aBoolean848 && !client.lowMem && this.anInt1062 < 50) {
                this.anIntArray1207[this.anInt1062] = unsignedWord4;
                this.anIntArray1241[this.anInt1062] = n7;
                this.anIntArray1250[this.anInt1062] = Sounds.anIntArray326[unsignedWord4];
                ++this.anInt1062;
            }
        }
        if (n == 215) {
            final int method435 = stream.method435();
            final int method436 = stream.method428();
            final int n8 = this.anInt1268 + (method436 >> 4 & 0x7);
            final int n9 = this.anInt1269 + (method436 & 0x7);
            final int method437 = stream.method435();
            final int unsignedWord5 = stream.readUnsignedWord();
            if (n8 >= 0 && n9 >= 0 && n8 < 104 && n9 < 104 && method437 != this.unknownInt10) {
                final Item item2 = new Item();
                item2.ID = method435;
                item2.anInt1559 = unsignedWord5;
                if (this.groundArray[this.plane][n8][n9] == null) {
                    this.groundArray[this.plane][n8][n9] = new NodeList();
                }
                this.groundArray[this.plane][n8][n9].insertHead(item2);
                this.spawnGroundItem(n8, n9);
            }
            return;
        }
        if (n == 156) {
            final int method438 = stream.method426();
            final int n10 = this.anInt1268 + (method438 >> 4 & 0x7);
            final int n11 = this.anInt1269 + (method438 & 0x7);
            final int unsignedWord6 = stream.readUnsignedWord();
            if (n10 >= 0 && n11 >= 0 && n10 < 104 && n11 < 104) {
                final NodeList list2 = this.groundArray[this.plane][n10][n11];
                if (list2 != null) {
                    for (Item item3 = (Item)list2.reverseGetFirst(); item3 != null; item3 = (Item)list2.reverseGetNext()) {
                        if (item3.ID == (unsignedWord6 & 0x7FFF)) {
                            item3.unlink();
                            break;
                        }
                    }
                    if (list2.reverseGetFirst() == null) {
                        this.groundArray[this.plane][n10][n11] = null;
                    }
                    this.spawnGroundItem(n10, n11);
                }
            }
            return;
        }
        if (n == 160) {
            final int method439 = stream.method428();
            final int n12 = this.anInt1268 + (method439 >> 4 & 0x7);
            final int n13 = this.anInt1269 + (method439 & 0x7);
            final int method440 = stream.method428();
            int n14 = method440 >> 2;
            final int n15 = method440 & 0x3;
            final int n16 = this.anIntArray1177[n14];
            final int method441 = stream.method435();
            if (n12 >= 0 && n13 >= 0 && n12 < 103 && n13 < 103) {
                final int n17 = this.intGroundArray[this.plane][n12][n13];
                final int n18 = this.intGroundArray[this.plane][n12 + 1][n13];
                final int n19 = this.intGroundArray[this.plane][n12 + 1][n13 + 1];
                final int n20 = this.intGroundArray[this.plane][n12][n13 + 1];
                if (n16 == 0) {
                    final Object1 method442 = this.worldController.method296(this.plane, n12, n13);
                    if (method442 != null) {
                        final int n21 = method442.uid >> 14 & 0x7FFF;
                        if (n14 == 2) {
                            method442.aClass30_Sub2_Sub4_278 = new Animable_Sub5(n21, 4 + n15, 2, n18, n19, n17, n20, method441, false);
                            method442.aClass30_Sub2_Sub4_279 = new Animable_Sub5(n21, n15 + 1 & 0x3, 2, n18, n19, n17, n20, method441, false);
                        }
                        else {
                            method442.aClass30_Sub2_Sub4_278 = new Animable_Sub5(n21, n15, n14, n18, n19, n17, n20, method441, false);
                        }
                    }
                }
                if (n16 == 1) {
                    final Object2 method443 = this.worldController.method297(n12, n13, this.plane);
                    if (method443 != null) {
                        method443.aClass30_Sub2_Sub4_504 = new Animable_Sub5(method443.uid >> 14 & 0x7FFF, 0, 4, n18, n19, n17, n20, method441, false);
                    }
                }
                if (n16 == 2) {
                    final Object5 method444 = this.worldController.method298(n12, n13, this.plane);
                    if (n14 == 11) {
                        n14 = 10;
                    }
                    if (method444 != null) {
                        method444.aClass30_Sub2_Sub4_521 = new Animable_Sub5(method444.uid >> 14 & 0x7FFF, n15, n14, n18, n19, n17, n20, method441, false);
                    }
                }
                if (n16 == 3) {
                    final Object3 method445 = this.worldController.method299(n13, n12, this.plane);
                    if (method445 != null) {
                        method445.aClass30_Sub2_Sub4_814 = new Animable_Sub5(method445.uid >> 14 & 0x7FFF, n15, 22, n18, n19, n17, n20, method441, false);
                    }
                }
            }
            return;
        }
        if (n == 147) {
            final int method446 = stream.method428();
            final int n22 = this.anInt1268 + (method446 >> 4 & 0x7);
            final int n23 = this.anInt1269 + (method446 & 0x7);
            final int unsignedWord7 = stream.readUnsignedWord();
            int method447 = stream.method430();
            final int method448 = stream.method434();
            int method449 = stream.method429();
            final int unsignedWord8 = stream.readUnsignedWord();
            final int method450 = stream.method428();
            final int n24 = method450 >> 2;
            final int n25 = method450 & 0x3;
            final int n26 = this.anIntArray1177[n24];
            int signedByte = stream.readSignedByte();
            final int unsignedWord9 = stream.readUnsignedWord();
            int method451 = stream.method429();
            Player myPlayer;
            if (unsignedWord7 == this.unknownInt10) {
                myPlayer = client.myPlayer;
            }
            else {
                myPlayer = this.playerArray[unsignedWord7];
            }
            if (myPlayer != null) {
                if (unsignedWord9 == 10284) {}
                final ObjectDef forID = ObjectDef.forID(unsignedWord9);
                final Model method452 = forID.method578(n24, n25, this.intGroundArray[this.plane][n22][n23], this.intGroundArray[this.plane][n22 + 1][n23], this.intGroundArray[this.plane][n22 + 1][n23 + 1], this.intGroundArray[this.plane][n22][n23 + 1], -1);
                if (method452 != null) {
                    this.method130(unsignedWord8 + 1, -1, 0, n26, n23, 0, this.plane, n22, method448 + 1);
                    myPlayer.anInt1707 = method448 + client.loopCycle;
                    myPlayer.anInt1708 = unsignedWord8 + client.loopCycle;
                    myPlayer.aModel_1714 = method452;
                    int n27 = forID.anInt744;
                    int n28 = forID.anInt761;
                    if (n25 == 1 || n25 == 3) {
                        n27 = forID.anInt761;
                        n28 = forID.anInt744;
                    }
                    myPlayer.anInt1711 = n22 * 128 + n27 * 64;
                    myPlayer.anInt1713 = n23 * 128 + n28 * 64;
                    myPlayer.anInt1712 = this.method42(this.plane, myPlayer.anInt1713, myPlayer.anInt1711);
                    if (signedByte > method447) {
                        final int n29 = signedByte;
                        signedByte = method447;
                        method447 = n29;
                    }
                    if (method451 > method449) {
                        final int n30 = method451;
                        method451 = method449;
                        method449 = n30;
                    }
                    myPlayer.anInt1719 = n22 + signedByte;
                    myPlayer.anInt1721 = n22 + method447;
                    myPlayer.anInt1720 = n23 + method451;
                    myPlayer.anInt1722 = n23 + method449;
                }
            }
        }
        if (n == 151) {
            final int method453 = stream.method426();
            final int n31 = this.anInt1268 + (method453 >> 4 & 0x7);
            final int n32 = this.anInt1269 + (method453 & 0x7);
            final int method454 = stream.method434();
            final int method455 = stream.method428();
            final int n33 = method455 >> 2;
            final int n34 = method455 & 0x3;
            final int n35 = this.anIntArray1177[n33];
            if (n31 >= 0 && n32 >= 0 && n31 < 104 && n32 < 104) {
                this.method130(-1, method454, n34, n35, n32, n33, this.plane, n31, 0);
            }
            return;
        }
        if (n == 4) {
            final int unsignedByte4 = stream.readUnsignedByte();
            final int n36 = this.anInt1268 + (unsignedByte4 >> 4 & 0x7);
            final int n37 = this.anInt1269 + (unsignedByte4 & 0x7);
            final int unsignedWord10 = stream.readUnsignedWord();
            final int unsignedByte5 = stream.readUnsignedByte();
            final int unsignedWord11 = stream.readUnsignedWord();
            if (n36 >= 0 && n37 >= 0 && n36 < 104 && n37 < 104) {
                final int n38 = n36 * 128 + 64;
                final int n39 = n37 * 128 + 64;
                this.aClass19_1056.insertHead(new Animable_Sub3(this.plane, client.loopCycle, unsignedWord11, unsignedWord10, this.method42(this.plane, n39, n38) - unsignedByte5, n39, n38));
            }
            return;
        }
        if (n == 44) {
            final int method456 = stream.method436();
            final int unsignedWord12 = stream.readUnsignedWord();
            final int unsignedByte6 = stream.readUnsignedByte();
            final int n40 = this.anInt1268 + (unsignedByte6 >> 4 & 0x7);
            final int n41 = this.anInt1269 + (unsignedByte6 & 0x7);
            if (n40 >= 0 && n41 >= 0 && n40 < 104 && n41 < 104) {
                final Item item4 = new Item();
                item4.ID = method456;
                item4.anInt1559 = unsignedWord12;
                if (this.groundArray[this.plane][n40][n41] == null) {
                    this.groundArray[this.plane][n40][n41] = new NodeList();
                }
                this.groundArray[this.plane][n40][n41].insertHead(item4);
                this.spawnGroundItem(n40, n41);
            }
            return;
        }
        if (n == 101) {
            final int method457 = stream.method427();
            final int n42 = method457 >> 2;
            final int n43 = method457 & 0x3;
            final int n44 = this.anIntArray1177[n42];
            final int unsignedByte7 = stream.readUnsignedByte();
            final int n45 = this.anInt1268 + (unsignedByte7 >> 4 & 0x7);
            final int n46 = this.anInt1269 + (unsignedByte7 & 0x7);
            if (n45 >= 0 && n46 >= 0 && n45 < 104 && n46 < 104) {
                this.method130(-1, -1, n43, n44, n46, n42, this.plane, n45, 0);
            }
            return;
        }
        if (n == 117) {
            final int unsignedByte8 = stream.readUnsignedByte();
            final int n47 = this.anInt1268 + (unsignedByte8 >> 4 & 0x7);
            final int n48 = this.anInt1269 + (unsignedByte8 & 0x7);
            final byte b = (byte)(n47 + stream.readSignedByte());
            final byte b2 = (byte)(n48 + stream.readSignedByte());
            final int signedWord = stream.readSignedWord();
            final int unsignedWord13 = stream.readUnsignedWord();
            final int n49 = stream.readUnsignedByte() * 4;
            final int n50 = stream.readUnsignedByte() * 4;
            final int unsignedWord14 = stream.readUnsignedWord();
            final int unsignedWord15 = stream.readUnsignedWord();
            final int unsignedByte9 = stream.readUnsignedByte();
            final int unsignedByte10 = stream.readUnsignedByte();
            if (n47 >= 0 && n48 >= 0 && n47 < 104 && n48 < 104 && b >= 0 && b2 >= 0 && b < 104 && b2 < 104 && unsignedWord13 != 65535) {
                final int n51 = n47 * 128 + 64;
                final int n52 = n48 * 128 + 64;
                final int n53 = b * 128 + 64;
                final int n54 = b2 * 128 + 64;
                final Animable_Sub4 animable_Sub4 = new Animable_Sub4(unsignedByte9, n50, unsignedWord14 + client.loopCycle, unsignedWord15 + client.loopCycle, unsignedByte10, this.plane, this.method42(this.plane, n52, n51) - n49, n52, n51, signedWord, unsignedWord13);
                animable_Sub4.method455(unsignedWord14 + client.loopCycle, n54, this.method42(this.plane, n54, n53) - n50, n53);
                this.aClass19_1013.insertHead(animable_Sub4);
            }
        }
    }
    
    private static void setLowMem() {
        WorldController.lowMem = true;
        Texture.lowMem = true;
        client.lowMem = true;
        ObjectManager.lowMem = true;
        ObjectDef.lowMem = true;
    }
    
    private void method139(final Stream stream) {
        stream.initBitAccess();
        final int bits = stream.readBits(8);
        if (bits < this.npcCount) {
            for (int i = bits; i < this.npcCount; ++i) {
                this.anIntArray840[this.anInt839++] = this.npcIndices[i];
            }
        }
        if (bits > this.npcCount) {
            SignLink.reporterror(this.myUsername + " Too many npcs");
            throw new RuntimeException("eek");
        }
        this.npcCount = 0;
        for (int j = 0; j < bits; ++j) {
            final int n = this.npcIndices[j];
            final NPC npc = this.npcArray[n];
            if (stream.readBits(1) == 0) {
                this.npcIndices[this.npcCount++] = n;
                npc.anInt1537 = client.loopCycle;
            }
            else {
                final int bits2 = stream.readBits(2);
                if (bits2 == 0) {
                    this.npcIndices[this.npcCount++] = n;
                    npc.anInt1537 = client.loopCycle;
                    this.anIntArray894[this.anInt893++] = n;
                }
                else if (bits2 == 1) {
                    this.npcIndices[this.npcCount++] = n;
                    npc.anInt1537 = client.loopCycle;
                    npc.moveInDir(false, stream.readBits(3));
                    if (stream.readBits(1) == 1) {
                        this.anIntArray894[this.anInt893++] = n;
                    }
                }
                else if (bits2 == 2) {
                    this.npcIndices[this.npcCount++] = n;
                    npc.anInt1537 = client.loopCycle;
                    npc.moveInDir(true, stream.readBits(3));
                    npc.moveInDir(true, stream.readBits(3));
                    if (stream.readBits(1) == 1) {
                        this.anIntArray894[this.anInt893++] = n;
                    }
                }
                else if (bits2 == 3) {
                    this.anIntArray840[this.anInt839++] = n;
                }
            }
        }
    }
    
    private void processLoginScreenInput() {
        if (this.loginScreenState == 0) {
            int n = super.myHeight / 2 + 20;
            n += 20;
            int n2 = this.myHeight / 2 - 40;
            n2 += 30;
            n2 += 25;
            this.resetImage();
            if (super.clickMode3 == 1 && super.saveClickX >= 276 && super.saveClickX <= 490 && super.saveClickY >= 194 && super.saveClickY <= 223) {
                this.loginScreenCursorPos = 0;
            }
            if (super.clickMode3 == 1 && super.mouseX >= 276 && super.mouseX <= 490 && super.mouseY >= 240 && super.mouseY <= 270) {
                this.loginScreenCursorPos = 1;
            }
            if (super.clickMode3 == 1 && super.saveClickX >= 295 && super.saveClickX <= 474 && super.saveClickY >= 301 && super.saveClickY <= 325) {
                this.loginFailures = 0;
                if (this.myUsername.length() > 0 && this.myPassword.length() > 0) {
                    this.login(this.myUsername, this.myPassword, false);
                }
                else {
                    this.loginScreenCursorPos = 0;
                    this.loginMessage1 = "Username & Password";
                    this.loginMessage2 = "Must be more than 1 character";
                }
                if (this.loggedIn) {
                    return;
                }
            }
            while (true) {
                final int char1 = this.readChar(-796);
                if (char1 == -1) {
                    break;
                }
                boolean b = false;
                for (int i = 0; i < "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"£$%^&*()-_=+[{]};:'@#~,<.>/?\\| ".length(); ++i) {
                    if (char1 == "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"£$%^&*()-_=+[{]};:'@#~,<.>/?\\| ".charAt(i)) {
                        b = true;
                        break;
                    }
                }
                if (this.loginScreenCursorPos == 0) {
                    if (char1 == '\b' && this.myUsername.length() > 0) {
                        this.myUsername = this.myUsername.substring(0, this.myUsername.length() - 1);
                    }
                    if (char1 == '\t' || char1 == '\n' || char1 == '\r') {
                        this.loginScreenCursorPos = 1;
                    }
                    if (b) {
                        this.myUsername += (char)char1;
                    }
                    if (this.myUsername.length() <= 12) {
                        continue;
                    }
                    this.myUsername = this.myUsername.substring(0, 12);
                }
                else {
                    if (this.loginScreenCursorPos != 1) {
                        continue;
                    }
                    if (char1 == '\b' && this.myPassword.length() > 0) {
                        this.myPassword = this.myPassword.substring(0, this.myPassword.length() - 1);
                    }
                    if (char1 == '\t' || char1 == '\n' || char1 == '\r') {
                        this.login(this.myUsername, this.myPassword, false);
                    }
                    if (b) {
                        this.myPassword += (char)char1;
                    }
                    if (this.myPassword.length() <= 20) {
                        continue;
                    }
                    this.myPassword = this.myPassword.substring(0, 20);
                }
            }
            return;
        }
        if (this.loginScreenState == 3) {
            final int n3 = super.myWidth / 2;
            int n4 = super.myHeight / 2 + 50;
            n4 += 20;
            if (super.clickMode3 == 1 && super.saveClickX >= n3 - 75 && super.saveClickX <= n3 + 75 && super.saveClickY >= n4 - 20 && super.saveClickY <= n4 + 20) {
                this.loginScreenState = 0;
            }
        }
    }
    
    private void markMinimap(final Sprite sprite, final int n, final int n2) {
        final int n3 = (client.clientSize != 0) ? (client.clientWidth - 164) : 3;
        if (client.clientSize == 0) {
            try {
                final int n4 = this.minimapInt1 + this.minimapInt2 & 0x7FF;
                if (n * n + n2 * n2 > 6400) {
                    return;
                }
                final int n5 = Model.modelIntArray1[n4];
                final int n6 = Model.modelIntArray2[n4];
                final int n7 = n5 * 256 / (this.minimapInt3 + 256);
                final int n8 = n6 * 256 / (this.minimapInt3 + 256);
                sprite.drawSprite(94 + (n2 * n7 + n * n8 >> 16) - sprite.anInt1444 / 2 + 4 + n3, 83 - (n2 * n8 - n * n7 >> 16) - sprite.anInt1445 / 2 + 2);
            }
            catch (Exception ex) {}
        }
        else if (client.clientSize >= 1) {
            try {
                final int n9 = this.minimapInt1 + this.minimapInt2 & 0x7FF;
                if (n * n + n2 * n2 > 6400) {
                    return;
                }
                final int n10 = Model.modelIntArray1[n9];
                final int n11 = Model.modelIntArray2[n9];
                final int n12 = n10 * 256 / (this.minimapInt3 + 256);
                final int n13 = n11 * 256 / (this.minimapInt3 + 256);
                sprite.drawSprite(77 + (n2 * n12 + n * n13 >> 16) - sprite.anInt1444 / 2 + 4 + n3, 85 - (n2 * n13 - n * n12 >> 16) - sprite.anInt1445 / 2 - 4);
            }
            catch (Exception ex2) {}
        }
    }
    
    private void method142(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        if (n5 >= 1 && n >= 1 && n5 <= 102 && n <= 102) {
            if (client.lowMem && n2 != this.plane) {
                return;
            }
            int n8 = 0;
            if (n6 == 0) {
                n8 = this.worldController.method300(n2, n5, n);
            }
            if (n6 == 1) {
                n8 = this.worldController.method301(n2, n5, n);
            }
            if (n6 == 2) {
                n8 = this.worldController.method302(n2, n5, n);
            }
            if (n6 == 3) {
                n8 = this.worldController.method303(n2, n5, n);
            }
            if (n8 != 0) {
                final int method304 = this.worldController.method304(n2, n5, n, n8);
                final int n9 = n8 >> 14 & 0x7FFF;
                final int n10 = method304 & 0x1F;
                final int n11 = method304 >> 6;
                if (n6 == 0) {
                    this.worldController.method291(n5, n2, n, (byte)(-119));
                    final ObjectDef forID = ObjectDef.forID(n9);
                    if (forID.aBoolean767) {
                        this.aClass11Array1230[n2].method215(n11, n10, forID.aBoolean757, n5, n);
                    }
                }
                if (n6 == 1) {
                    this.worldController.method292(n, n2, n5);
                }
                if (n6 == 2) {
                    this.worldController.method293(n2, n5, n);
                    final ObjectDef forID2 = ObjectDef.forID(n9);
                    if (n5 + forID2.anInt744 > 103 || n + forID2.anInt744 > 103 || n5 + forID2.anInt761 > 103 || n + forID2.anInt761 > 103) {
                        return;
                    }
                    if (forID2.aBoolean767) {
                        this.aClass11Array1230[n2].method216(n11, forID2.anInt744, n5, n, forID2.anInt761, forID2.aBoolean757);
                    }
                }
                if (n6 == 3) {
                    this.worldController.method294(n2, n, n5);
                    final ObjectDef forID3 = ObjectDef.forID(n9);
                    if (forID3.aBoolean767 && forID3.hasActions) {
                        this.aClass11Array1230[n2].method218(n, n5);
                    }
                }
            }
            if (n7 >= 0) {
                int n12 = n2;
                if (n12 < 3 && (this.byteGroundArray[1][n5][n] & 0x2) == 0x2) {
                    ++n12;
                }
                ObjectManager.method188(this.worldController, n3, n, n4, n12, this.aClass11Array1230[n2], this.intGroundArray, n5, n7, n2);
            }
        }
    }
    
    private void updatePlayers(final int n, final Stream stream) {
        this.anInt839 = 0;
        this.anInt893 = 0;
        this.method117(stream);
        this.method134(stream);
        this.method91(stream, n);
        this.method49(stream);
        for (int i = 0; i < this.anInt839; ++i) {
            final int n2 = this.anIntArray840[i];
            if (this.playerArray[n2].anInt1537 != client.loopCycle) {
                this.playerArray[n2] = null;
            }
        }
        if (stream.currentOffset != n) {}
        for (int j = 0; j < this.playerCount; ++j) {
            if (this.playerArray[this.playerIndices[j]] == null) {}
        }
    }
    
    private void setCameraPos(final int n, final int yCameraCurve, final int n2, final int n3, final int xCameraCurve, final int n4) {
        final int n5 = 2048 - yCameraCurve & 0x7FF;
        final int n6 = 2048 - xCameraCurve & 0x7FF;
        int n7 = 0;
        int n8 = 0;
        int n9 = n;
        if (n5 != 0) {
            final int n10 = Model.modelIntArray1[n5];
            final int n11 = Model.modelIntArray2[n5];
            final int n12 = n8 * n11 - n9 * n10 >> 16;
            n9 = n8 * n10 + n9 * n11 >> 16;
            n8 = n12;
        }
        if (n6 != 0) {
            final int n13 = Model.modelIntArray1[n6];
            final int n14 = Model.modelIntArray2[n6];
            final int n15 = n9 * n13 + n7 * n14 >> 16;
            n9 = n9 * n14 - n7 * n13 >> 16;
            n7 = n15;
        }
        this.xCameraPos = n2 - n7;
        this.zCameraPos = n3 - n8;
        this.yCameraPos = n4 - n9;
        this.yCameraCurve = yCameraCurve;
        this.xCameraCurve = xCameraCurve;
    }
    
    public void updateStrings(final String s, final int n) {
        switch (n) {
            case 1675: {
                this.sendFrame126(s, 17508);
                break;
            }
            case 1676: {
                this.sendFrame126(s, 17509);
                break;
            }
            case 1677: {
                this.sendFrame126(s, 17510);
                break;
            }
            case 1678: {
                this.sendFrame126(s, 17511);
                break;
            }
            case 1679: {
                this.sendFrame126(s, 17512);
                break;
            }
            case 1680: {
                this.sendFrame126(s, 17513);
                break;
            }
            case 1681: {
                this.sendFrame126(s, 17514);
                break;
            }
            case 1682: {
                this.sendFrame126(s, 17515);
                break;
            }
            case 1683: {
                this.sendFrame126(s, 17516);
                break;
            }
            case 1684: {
                this.sendFrame126(s, 17517);
                break;
            }
            case 1686: {
                this.sendFrame126(s, 17518);
                break;
            }
            case 1687: {
                this.sendFrame126(s, 17519);
                break;
            }
        }
    }
    
    public void sendFrame126(final String message, final int n) {
        RSInterface.interfaceCache[n].message = message;
        if (client.tabID != -1 && RSInterface.interfaceCache[n].parentID == client.tabInterfaceIDs[client.tabID]) {
            client.needDrawTabArea = true;
        }
    }
    
    public void sendPacket185(final int n, final int n2, final int n3) {
        Label_0294: {
            switch (n3) {
                case 135: {
                    final RSInterface rsInterface = RSInterface.interfaceCache[n];
                    boolean promptUserForInput = true;
                    if (rsInterface.contentType > 0) {
                        promptUserForInput = this.promptUserForInput(rsInterface);
                    }
                    if (promptUserForInput) {
                        this.stream.createFrame(185);
                        this.stream.writeWord(n);
                        break;
                    }
                    break;
                }
                case 646: {
                    this.stream.createFrame(185);
                    this.stream.writeWord(n);
                    final RSInterface rsInterface2 = RSInterface.interfaceCache[n];
                    if (rsInterface2.valueIndexArray != null && rsInterface2.valueIndexArray[0][0] == 5 && this.variousSettings[n2] != rsInterface2.anIntArray212[0]) {
                        this.variousSettings[n2] = rsInterface2.anIntArray212[0];
                        this.method33(n2);
                        client.needDrawTabArea = true;
                        break;
                    }
                    break;
                }
                case 169: {
                    this.stream.createFrame(185);
                    this.stream.writeWord(n);
                    final RSInterface rsInterface3 = RSInterface.interfaceCache[n];
                    if (rsInterface3.valueIndexArray != null && rsInterface3.valueIndexArray[0][0] == 5) {
                        this.variousSettings[n2] = 1 - this.variousSettings[n2];
                        this.method33(n2);
                        client.needDrawTabArea = true;
                    }
                    switch (n) {
                        case 19136: {
                            if (n2 == 0) {
                                this.sendFrame36(173, n2);
                            }
                            if (n2 == 1) {
                                this.sendPacket185(153, 173, 646);
                                break Label_0294;
                            }
                            break Label_0294;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    public void sendFrame36(final int n, final int n2) {
        this.anIntArray1045[n] = n2;
        if (this.variousSettings[n] != n2) {
            this.variousSettings[n] = n2;
            this.method33(n);
            client.needDrawTabArea = true;
            if (this.dialogID != -1) {
                client.inputTaken = true;
            }
        }
    }
    
    public void sendFrame219() {
        if (this.invOverlayInterfaceID != -1) {
            this.invOverlayInterfaceID = -1;
            client.needDrawTabArea = true;
            client.tabAreaAltered = true;
        }
        if (this.backDialogID != -1) {
            this.backDialogID = -1;
            client.inputTaken = true;
        }
        if (this.inputDialogState != 0) {
            this.inputDialogState = 0;
            client.inputTaken = true;
        }
        client.openInterfaceID = -1;
        this.aBoolean1149 = false;
    }
    
    public void sendFrame248(final int openInterfaceID, final int invOverlayInterfaceID) {
        if (this.backDialogID != -1) {
            this.backDialogID = -1;
            client.inputTaken = true;
        }
        if (this.inputDialogState != 0) {
            this.inputDialogState = 0;
            client.inputTaken = true;
        }
        client.openInterfaceID = openInterfaceID;
        this.invOverlayInterfaceID = invOverlayInterfaceID;
        client.needDrawTabArea = true;
        client.tabAreaAltered = true;
        this.aBoolean1149 = false;
    }
    
    private boolean parsePacket() {
        if (this.socketStream == null) {
            return false;
        }
        try {
            int available = this.socketStream.available();
            if (available == 0) {
                return false;
            }
            if (this.pktType == -1) {
                this.socketStream.flushInputStream(this.inStream.buffer, 1);
                this.pktType = (this.inStream.buffer[0] & 0xFF);
                if (this.encryption != null) {
                    this.pktType = (this.pktType - this.encryption.getNextKey() & 0xFF);
                }
                this.pktSize = SizeConstants.packetSizes[this.pktType];
                --available;
            }
            if (this.pktSize == -1) {
                if (available <= 0) {
                    return false;
                }
                this.socketStream.flushInputStream(this.inStream.buffer, 1);
                this.pktSize = (this.inStream.buffer[0] & 0xFF);
                --available;
            }
            if (this.pktSize == -2) {
                if (available <= 1) {
                    return false;
                }
                this.socketStream.flushInputStream(this.inStream.buffer, 2);
                this.inStream.currentOffset = 0;
                this.pktSize = this.inStream.readUnsignedWord();
                available -= 2;
            }
            if (available < this.pktSize) {
                return false;
            }
            this.inStream.currentOffset = 0;
            this.socketStream.flushInputStream(this.inStream.buffer, this.pktSize);
            this.anInt1009 = 0;
            this.anInt843 = this.anInt842;
            this.anInt842 = this.anInt841;
            this.anInt841 = this.pktType;
            switch (this.pktType) {
                case 81: {
                    try {
                        this.updatePlayers(this.pktSize, this.inStream);
                        this.aBoolean1080 = false;
                        this.pktType = -1;
                        return true;
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        return false;
                    }
                }
                case 176: {
                    this.daysSinceRecovChange = this.inStream.method427();
                    this.unreadMessages = this.inStream.method435();
                    this.membersInt = this.inStream.readUnsignedByte();
                    this.anInt1193 = this.inStream.method440();
                    this.daysSinceLastLogin = this.inStream.readUnsignedWord();
                    if (this.anInt1193 != 0 && client.openInterfaceID == -1) {
                        SignLink.dnslookup(TextClass.method586(this.anInt1193));
                        this.clearTopInterfaces();
                        int n = 650;
                        if (this.daysSinceRecovChange != 201 || this.membersInt == 1) {
                            n = 655;
                        }
                        this.reportAbuseInput = "";
                        this.canMute = false;
                        for (int i = 0; i < RSInterface.interfaceCache.length; ++i) {
                            if (RSInterface.interfaceCache[i] != null) {
                                if (RSInterface.interfaceCache[i].contentType == n) {
                                    client.openInterfaceID = RSInterface.interfaceCache[i].parentID;
                                }
                            }
                        }
                    }
                    this.pktType = -1;
                    return true;
                }
                case 64: {
                    this.anInt1268 = this.inStream.method427();
                    this.anInt1269 = this.inStream.method428();
                    for (int j = this.anInt1268; j < this.anInt1268 + 8; ++j) {
                        for (int k = this.anInt1269; k < this.anInt1269 + 8; ++k) {
                            if (this.groundArray[this.plane][j][k] != null) {
                                this.groundArray[this.plane][j][k] = null;
                                this.spawnGroundItem(j, k);
                            }
                        }
                    }
                    for (Class30_Sub1 class30_Sub1 = (Class30_Sub1)this.aClass19_1179.reverseGetFirst(); class30_Sub1 != null; class30_Sub1 = (Class30_Sub1)this.aClass19_1179.reverseGetNext()) {
                        if (class30_Sub1.anInt1297 >= this.anInt1268 && class30_Sub1.anInt1297 < this.anInt1268 + 8 && class30_Sub1.anInt1298 >= this.anInt1269 && class30_Sub1.anInt1298 < this.anInt1269 + 8 && class30_Sub1.anInt1295 == this.plane) {
                            class30_Sub1.anInt1294 = 0;
                        }
                    }
                    this.pktType = -1;
                    return true;
                }
                case 185: {
                    final int method436 = this.inStream.method436();
                    RSInterface.interfaceCache[method436].anInt233 = 3;
                    if (client.myPlayer.desc == null) {
                        RSInterface.interfaceCache[method436].mediaID = (client.myPlayer.anIntArray1700[0] << 25) + (client.myPlayer.anIntArray1700[4] << 20) + (client.myPlayer.equipment[0] << 15) + (client.myPlayer.equipment[8] << 10) + (client.myPlayer.equipment[11] << 5) + client.myPlayer.equipment[1];
                    }
                    else {
                        RSInterface.interfaceCache[method436].mediaID = (int)(305419896L + client.myPlayer.desc.type);
                    }
                    this.pktType = -1;
                    return true;
                }
                case 134: {
                    client.needDrawTabArea = true;
                    final int unsignedByte = this.inStream.readUnsignedByte();
                    final int method437 = this.inStream.method439();
                    final int unsignedByte2 = this.inStream.readUnsignedByte();
                    final int n2 = this.currentExp[unsignedByte];
                    this.currentExp[unsignedByte] = method437;
                    this.currentStats[unsignedByte] = unsignedByte2;
                    this.maxStats[unsignedByte] = 1;
                    this.xpCounter += this.currentExp[unsignedByte] - n2;
                    this.expAdded = this.currentExp[unsignedByte] - n2;
                    for (int l = 0; l < 98; ++l) {
                        if (method437 >= client.anIntArray1019[l]) {
                            this.maxStats[unsignedByte] = l + 2;
                        }
                    }
                    this.pktType = -1;
                    return true;
                }
                case 217: {
                    try {
                        this.name = this.inStream.readString();
                        this.message = this.inStream.readString();
                        this.clanname = this.inStream.readString();
                        this.rights = this.inStream.readUnsignedWord();
                        this.pushMessage(this.message = TextInput.processText(this.message), 16, this.name);
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    this.pktType = -1;
                    return true;
                }
                case 107: {
                    this.aBoolean1160 = false;
                    for (int n3 = 0; n3 < 5; ++n3) {
                        this.aBooleanArray876[n3] = false;
                    }
                    this.xpCounter = 0;
                    this.pktType = -1;
                    return true;
                }
                case 72: {
                    final RSInterface rsInterface = RSInterface.interfaceCache[this.inStream.method434()];
                    for (int n4 = 0; n4 < rsInterface.inv.length; ++n4) {
                        rsInterface.inv[n4] = -1;
                        rsInterface.inv[n4] = 0;
                    }
                    this.pktType = -1;
                    return true;
                }
                case 214: {
                    this.ignoreCount = this.pktSize / 8;
                    for (int n5 = 0; n5 < this.ignoreCount; ++n5) {
                        this.ignoreListAsLongs[n5] = this.inStream.readQWord();
                    }
                    this.pktType = -1;
                    return true;
                }
                case 166: {
                    this.aBoolean1160 = true;
                    this.anInt1098 = this.inStream.readUnsignedByte();
                    this.anInt1099 = this.inStream.readUnsignedByte();
                    this.anInt1100 = this.inStream.readUnsignedWord();
                    this.anInt1101 = this.inStream.readUnsignedByte();
                    this.anInt1102 = this.inStream.readUnsignedByte();
                    if (this.anInt1102 >= 100) {
                        this.xCameraPos = this.anInt1098 * 128 + 64;
                        this.yCameraPos = this.anInt1099 * 128 + 64;
                        this.zCameraPos = this.method42(this.plane, this.yCameraPos, this.xCameraPos) - this.anInt1100;
                    }
                    this.pktType = -1;
                    return true;
                }
                case 71: {
                    int unsignedWord = this.inStream.readUnsignedWord();
                    final int method438 = this.inStream.method426();
                    if (unsignedWord == 65535) {
                        unsignedWord = -1;
                    }
                    client.tabInterfaceIDs[method438] = unsignedWord;
                    client.needDrawTabArea = true;
                    client.tabAreaAltered = true;
                    this.pktType = -1;
                    return true;
                }
                case 74: {
                    final int method439 = this.inStream.method434();
                    if (method439 == 65535) {
                        this.stopMidi();
                    }
                    else {
                        if (method439 != this.currentSong) {
                            this.nextSong = method439;
                            this.songChanging = true;
                            try {
                                this.onDemandFetcher.method558(2, this.nextSong);
                            }
                            catch (NullPointerException ex4) {}
                        }
                        this.currentSong = method439;
                    }
                    this.pktType = -1;
                    return true;
                }
                case 121: {
                    final int method440 = this.inStream.method436();
                    final int method441 = this.inStream.method435();
                    if (this.musicEnabled && !client.lowMem) {
                        this.nextSong = method440;
                        this.songChanging = false;
                        this.onDemandFetcher.method558(2, this.nextSong);
                        this.prevSong = method441;
                    }
                    this.pktType = -1;
                    return true;
                }
                case 109: {
                    this.resetLogout();
                    this.pktType = -1;
                    return false;
                }
                case 70: {
                    final int signedWord = this.inStream.readSignedWord();
                    final int method442 = this.inStream.method437();
                    final RSInterface rsInterface2 = RSInterface.interfaceCache[this.inStream.method434()];
                    rsInterface2.anInt263 = signedWord;
                    rsInterface2.anInt265 = method442;
                    this.pktType = -1;
                    return true;
                }
                case 73:
                case 241: {
                    int anInt1069 = this.anInt1069;
                    int anInt1070 = this.anInt1070;
                    if (this.pktType == 73) {
                        anInt1069 = this.inStream.method435();
                        anInt1070 = this.inStream.readUnsignedWord();
                        this.aBoolean1159 = false;
                    }
                    if (this.pktType == 241) {
                        anInt1070 = this.inStream.method435();
                        this.inStream.initBitAccess();
                        for (int n6 = 0; n6 < 4; ++n6) {
                            for (int n7 = 0; n7 < 13; ++n7) {
                                for (int n8 = 0; n8 < 13; ++n8) {
                                    if (this.inStream.readBits(1) == 1) {
                                        this.anIntArrayArrayArray1129[n6][n7][n8] = this.inStream.readBits(26);
                                    }
                                    else {
                                        this.anIntArrayArrayArray1129[n6][n7][n8] = -1;
                                    }
                                }
                            }
                        }
                        this.inStream.finishBitAccess();
                        anInt1069 = this.inStream.readUnsignedWord();
                        this.aBoolean1159 = true;
                    }
                    if (this.anInt1069 == anInt1069 && this.anInt1070 == anInt1070 && this.loadingStage == 2) {
                        this.pktType = -1;
                        return true;
                    }
                    this.anInt1069 = anInt1069;
                    this.anInt1070 = anInt1070;
                    this.baseX = (this.anInt1069 - 6) * 8;
                    this.baseY = (this.anInt1070 - 6) * 8;
                    this.aBoolean1141 = ((this.anInt1069 / 8 == 48 || this.anInt1069 / 8 == 49) && this.anInt1070 / 8 == 48);
                    if (this.anInt1069 / 8 == 48 && this.anInt1070 / 8 == 148) {
                        this.aBoolean1141 = true;
                    }
                    this.loadingStage = 1;
                    this.aLong824 = System.currentTimeMillis();
                    this.gameScreenIP.initDrawingArea();
                    this.drawLoadingMessages(1, "Loading - please wait.", null);
                    this.gameScreenIP.drawGraphics(4, super.graphics, 4);
                    if (this.pktType == 73) {
                        int n9 = 0;
                        for (int n10 = (this.anInt1069 - 6) / 8; n10 <= (this.anInt1069 + 6) / 8; ++n10) {
                            for (int n11 = (this.anInt1070 - 6) / 8; n11 <= (this.anInt1070 + 6) / 8; ++n11) {
                                ++n9;
                            }
                        }
                        this.aByteArrayArray1183 = new byte[n9][];
                        this.aByteArrayArray1247 = new byte[n9][];
                        this.anIntArray1234 = new int[n9];
                        this.anIntArray1235 = new int[n9];
                        this.anIntArray1236 = new int[n9];
                        int n12 = 0;
                        for (int n13 = (this.anInt1069 - 6) / 8; n13 <= (this.anInt1069 + 6) / 8; ++n13) {
                            for (int n14 = (this.anInt1070 - 6) / 8; n14 <= (this.anInt1070 + 6) / 8; ++n14) {
                                this.anIntArray1234[n12] = (n13 << 8) + n14;
                                if (this.aBoolean1141 && (n14 == 49 || n14 == 149 || n14 == 147 || n13 == 50 || (n13 == 49 && n14 == 47))) {
                                    this.anIntArray1235[n12] = -1;
                                    this.anIntArray1236[n12] = -1;
                                    ++n12;
                                }
                                else {
                                    final int[] anIntArray1235 = this.anIntArray1235;
                                    final int n15 = n12;
                                    final int method443 = this.onDemandFetcher.method562(0, n14, n13);
                                    anIntArray1235[n15] = method443;
                                    final int n16 = method443;
                                    if (n16 != -1) {
                                        this.onDemandFetcher.method558(3, n16);
                                    }
                                    final int[] anIntArray1236 = this.anIntArray1236;
                                    final int n17 = n12;
                                    final int method444 = this.onDemandFetcher.method562(1, n14, n13);
                                    anIntArray1236[n17] = method444;
                                    final int n18 = method444;
                                    if (n18 != -1) {
                                        this.onDemandFetcher.method558(3, n18);
                                    }
                                    ++n12;
                                }
                            }
                        }
                    }
                    if (this.pktType == 241) {
                        int n19 = 0;
                        final int[] array = new int[676];
                        for (int n20 = 0; n20 < 4; ++n20) {
                            for (int n21 = 0; n21 < 13; ++n21) {
                                for (int n22 = 0; n22 < 13; ++n22) {
                                    final int n23 = this.anIntArrayArrayArray1129[n20][n21][n22];
                                    if (n23 != -1) {
                                        int n24 = ((n23 >> 14 & 0x3FF) / 8 << 8) + (n23 >> 3 & 0x7FF) / 8;
                                        for (int n25 = 0; n25 < n19; ++n25) {
                                            if (array[n25] == n24) {
                                                n24 = -1;
                                            }
                                        }
                                        if (n24 != -1) {
                                            array[n19++] = n24;
                                        }
                                    }
                                }
                            }
                        }
                        this.aByteArrayArray1183 = new byte[n19][];
                        this.aByteArrayArray1247 = new byte[n19][];
                        this.anIntArray1234 = new int[n19];
                        this.anIntArray1235 = new int[n19];
                        this.anIntArray1236 = new int[n19];
                        for (int n26 = 0; n26 < n19; ++n26) {
                            final int[] anIntArray1237 = this.anIntArray1234;
                            final int n27 = n26;
                            final int n28 = array[n26];
                            anIntArray1237[n27] = n28;
                            final int n29 = n28;
                            final int n30 = n29 >> 8 & 0xFF;
                            final int n31 = n29 & 0xFF;
                            final int[] anIntArray1238 = this.anIntArray1235;
                            final int n32 = n26;
                            final int method445 = this.onDemandFetcher.method562(0, n31, n30);
                            anIntArray1238[n32] = method445;
                            final int n33 = method445;
                            if (n33 != -1) {
                                this.onDemandFetcher.method558(3, n33);
                            }
                            final int[] anIntArray1239 = this.anIntArray1236;
                            final int n34 = n26;
                            final int method446 = this.onDemandFetcher.method562(1, n31, n30);
                            anIntArray1239[n34] = method446;
                            final int n35 = method446;
                            if (n35 != -1) {
                                this.onDemandFetcher.method558(3, n35);
                            }
                        }
                    }
                    final int n36 = this.baseX - this.anInt1036;
                    final int n37 = this.baseY - this.anInt1037;
                    this.anInt1036 = this.baseX;
                    this.anInt1037 = this.baseY;
                    for (int n38 = 0; n38 < 16384; ++n38) {
                        final NPC npc = this.npcArray[n38];
                        if (npc != null) {
                            for (int n39 = 0; n39 < 10; ++n39) {
                                final int[] smallX = npc.smallX;
                                final int n40 = n39;
                                smallX[n40] -= n36;
                                final int[] smallY = npc.smallY;
                                final int n41 = n39;
                                smallY[n41] -= n37;
                            }
                            final NPC npc2 = npc;
                            npc2.x -= n36 * 128;
                            final NPC npc3 = npc;
                            npc3.y -= n37 * 128;
                        }
                    }
                    for (int n42 = 0; n42 < this.maxPlayers; ++n42) {
                        final Player player = this.playerArray[n42];
                        if (player != null) {
                            for (int n43 = 0; n43 < 10; ++n43) {
                                final int[] smallX2 = player.smallX;
                                final int n44 = n43;
                                smallX2[n44] -= n36;
                                final int[] smallY2 = player.smallY;
                                final int n45 = n43;
                                smallY2[n45] -= n37;
                            }
                            final Player player2 = player;
                            player2.x -= n36 * 128;
                            final Player player3 = player;
                            player3.y -= n37 * 128;
                        }
                    }
                    this.aBoolean1080 = true;
                    int n46 = 0;
                    int n47 = 104;
                    int n48 = 1;
                    if (n36 < 0) {
                        n46 = 103;
                        n47 = -1;
                        n48 = -1;
                    }
                    int n49 = 0;
                    int n50 = 104;
                    int n51 = 1;
                    if (n37 < 0) {
                        n49 = 103;
                        n50 = -1;
                        n51 = -1;
                    }
                    for (int n52 = n46; n52 != n47; n52 += n48) {
                        for (int n53 = n49; n53 != n50; n53 += n51) {
                            final int n54 = n52 + n36;
                            final int n55 = n53 + n37;
                            for (int n56 = 0; n56 < 4; ++n56) {
                                if (n54 >= 0 && n55 >= 0 && n54 < 104 && n55 < 104) {
                                    this.groundArray[n56][n52][n53] = this.groundArray[n56][n54][n55];
                                }
                                else {
                                    this.groundArray[n56][n52][n53] = null;
                                }
                            }
                        }
                    }
                    for (Class30_Sub1 class30_Sub2 = (Class30_Sub1)this.aClass19_1179.reverseGetFirst(); class30_Sub2 != null; class30_Sub2 = (Class30_Sub1)this.aClass19_1179.reverseGetNext()) {
                        final Class30_Sub1 class30_Sub3 = class30_Sub2;
                        class30_Sub3.anInt1297 -= n36;
                        final Class30_Sub1 class30_Sub4 = class30_Sub2;
                        class30_Sub4.anInt1298 -= n37;
                        if (class30_Sub2.anInt1297 < 0 || class30_Sub2.anInt1298 < 0 || class30_Sub2.anInt1297 >= 104 || class30_Sub2.anInt1298 >= 104) {
                            class30_Sub2.unlink();
                        }
                    }
                    if (this.destX != 0) {
                        this.destX -= n36;
                        this.destY -= n37;
                    }
                    this.aBoolean1160 = false;
                    this.pktType = -1;
                    return true;
                }
                case 208: {
                    final int method447 = this.inStream.method437();
                    if (method447 >= 0) {
                        this.method60(method447);
                    }
                    this.anInt1018 = method447;
                    this.pktType = -1;
                    return true;
                }
                case 99: {
                    this.anInt1021 = this.inStream.readUnsignedByte();
                    this.pktType = -1;
                    return true;
                }
                case 75: {
                    final int method448 = this.inStream.method436();
                    final int method449 = this.inStream.method436();
                    RSInterface.interfaceCache[method449].anInt233 = 2;
                    RSInterface.interfaceCache[method449].mediaID = method448;
                    this.pktType = -1;
                    return true;
                }
                case 114: {
                    this.anInt1104 = this.inStream.method434() * 30;
                    this.pktType = -1;
                    return true;
                }
                case 60: {
                    this.anInt1269 = this.inStream.readUnsignedByte();
                    this.anInt1268 = this.inStream.method427();
                    while (this.inStream.currentOffset < this.pktSize) {
                        this.method137(this.inStream, this.inStream.readUnsignedByte());
                    }
                    this.pktType = -1;
                    return true;
                }
                case 35: {
                    final int unsignedByte3 = this.inStream.readUnsignedByte();
                    final int unsignedByte4 = this.inStream.readUnsignedByte();
                    final int unsignedByte5 = this.inStream.readUnsignedByte();
                    final int unsignedByte6 = this.inStream.readUnsignedByte();
                    this.aBooleanArray876[unsignedByte3] = true;
                    this.anIntArray873[unsignedByte3] = unsignedByte4;
                    this.anIntArray1203[unsignedByte3] = unsignedByte5;
                    this.anIntArray928[unsignedByte3] = unsignedByte6;
                    this.anIntArray1030[unsignedByte3] = 0;
                    this.pktType = -1;
                    return true;
                }
                case 174: {
                    final int unsignedWord2 = this.inStream.readUnsignedWord();
                    final int unsignedByte7 = this.inStream.readUnsignedByte();
                    final int unsignedWord3 = this.inStream.readUnsignedWord();
                    if (this.aBoolean848 && this.anInt1062 < 50) {
                        this.anIntArray1207[this.anInt1062] = unsignedWord2;
                        this.anIntArray1241[this.anInt1062] = unsignedByte7;
                        this.anIntArray1250[this.anInt1062] = unsignedWord3 + Sounds.anIntArray326[unsignedWord2];
                        ++this.anInt1062;
                    }
                    this.pktType = -1;
                    return true;
                }
                case 104: {
                    final int method450 = this.inStream.method427();
                    final int method451 = this.inStream.method426();
                    String string = this.inStream.readString();
                    if (method450 >= 1 && method450 <= 5) {
                        if (string.equalsIgnoreCase("null")) {
                            string = null;
                        }
                        this.atPlayerActions[method450 - 1] = string;
                        this.atPlayerArray[method450 - 1] = (method451 == 0);
                    }
                    this.pktType = -1;
                    return true;
                }
                case 78: {
                    this.destX = 0;
                    this.pktType = -1;
                    return true;
                }
                case 253: {
                    final String string2 = this.inStream.readString();
                    if (string2.endsWith(":tradereq:")) {
                        final String substring = string2.substring(0, string2.indexOf(":"));
                        final long longForName = TextClass.longForName(substring);
                        boolean b = false;
                        for (int n57 = 0; n57 < this.ignoreCount; ++n57) {
                            if (this.ignoreListAsLongs[n57] == longForName) {
                                b = true;
                            }
                        }
                        if (!b && this.anInt1251 == 0) {
                            this.pushMessage("wishes to trade with you.", 4, substring);
                        }
                    }
                    else if (string2.endsWith(":clan:")) {
                        final String substring2 = string2.substring(0, string2.indexOf(":"));
                        TextClass.longForName(substring2);
                        this.pushMessage("Clan: ", 8, substring2);
                    }
                    else if (string2.endsWith("#url#")) {
                        this.pushMessage("Link: ", 9, string2.substring(0, string2.indexOf("#")));
                    }
                    else if (string2.endsWith(":hotzone:")) {
                        this.pvpWindow = true;
                        this.safeZone = false;
                        this.countToSafe = false;
                    }
                    else if (string2.endsWith(":bh:")) {
                        if (this.anInt1055 == 2 && this.anInt1055 == 3) {
                            this.bounty.drawSprite2(330, 5);
                        }
                        this.bounty2.drawSprite(330, 5);
                        this.penal.drawSprite(430, 40);
                        this.penal2.drawSprite(337, 40);
                    }
                    else if (string2.endsWith("::Shop") || string2.endsWith("::Donate") || string2.endsWith("::Items")) {
                        this.launchURL("http://devilishpkz.org");
                    }
                    else if (string2.endsWith(":disablemusic:")) {
                        this.stopMidi();
                        this.musicEnabled = false;
                    }
                    else if (string2.endsWith(":safezone:")) {
                        this.pvpWindow = true;
                        this.safeZone = true;
                        this.countToSafe = false;
                    }
                    else if (string2.endsWith(":killpvp:")) {
                        this.pvpWindow = false;
                        this.safeZone = false;
                        this.countToSafe = false;
                    }
                    else if (string2.endsWith(":duelreq:")) {
                        final String substring3 = string2.substring(0, string2.indexOf(":"));
                        final long longForName2 = TextClass.longForName(substring3);
                        boolean b2 = false;
                        for (int n58 = 0; n58 < this.ignoreCount; ++n58) {
                            if (this.ignoreListAsLongs[n58] == longForName2) {
                                b2 = true;
                            }
                        }
                        if (!b2 && this.anInt1251 == 0) {
                            this.pushMessage("wishes to duel with you.", 8, substring3);
                        }
                    }
                    else if (string2.endsWith(":resetautocast:")) {
                        this.Autocast = false;
                        this.autocastId = 0;
                        this.magicAuto.drawSprite(1000, 1000);
                    }
                    else if (string2.endsWith(":chalreq:")) {
                        final String substring4 = string2.substring(0, string2.indexOf(":"));
                        final long longForName3 = TextClass.longForName(substring4);
                        boolean b3 = false;
                        for (int n59 = 0; n59 < this.ignoreCount; ++n59) {
                            if (this.ignoreListAsLongs[n59] == longForName3) {
                                b3 = true;
                            }
                        }
                        if (!b3 && this.anInt1251 == 0) {
                            this.pushMessage(string2.substring(string2.indexOf(":") + 1, string2.length() - 9), 8, substring4);
                        }
                    }
                    else {
                        this.pushMessage(string2, 0, "");
                    }
                    this.pktType = -1;
                    return true;
                }
                case 1: {
                    for (int n60 = 0; n60 < this.playerArray.length; ++n60) {
                        if (this.playerArray[n60] != null) {
                            this.playerArray[n60].anim = -1;
                        }
                    }
                    for (int n61 = 0; n61 < this.npcArray.length; ++n61) {
                        if (this.npcArray[n61] != null) {
                            this.npcArray[n61].anim = -1;
                        }
                    }
                    this.pktType = -1;
                    return true;
                }
                case 50: {
                    final long qWord = this.inStream.readQWord();
                    final int unsignedByte8 = this.inStream.readUnsignedByte();
                    String fixName = TextClass.fixName(TextClass.nameForLong(qWord));
                    for (int n62 = 0; n62 < this.friendsCount; ++n62) {
                        if (qWord == this.friendsListAsLongs[n62]) {
                            if (this.friendsNodeIDs[n62] != unsignedByte8) {
                                this.friendsNodeIDs[n62] = unsignedByte8;
                                client.needDrawTabArea = true;
                                if (unsignedByte8 >= 2) {
                                    this.pushMessage(fixName + " has logged in.", 5, "");
                                }
                                if (unsignedByte8 <= 1) {
                                    this.pushMessage(fixName + " has logged out.", 5, "");
                                }
                            }
                            fixName = null;
                        }
                    }
                    if (fixName != null && this.friendsCount < 200) {
                        this.friendsListAsLongs[this.friendsCount] = qWord;
                        this.friendsList[this.friendsCount] = fixName;
                        this.friendsNodeIDs[this.friendsCount] = unsignedByte8;
                        ++this.friendsCount;
                        client.needDrawTabArea = true;
                    }
                    int n63 = 0;
                    while (n63 == 0) {
                        n63 = 1;
                        for (int n64 = 0; n64 < this.friendsCount - 1; ++n64) {
                            if ((this.friendsNodeIDs[n64] != client.nodeID && this.friendsNodeIDs[n64 + 1] == client.nodeID) || (this.friendsNodeIDs[n64] == 0 && this.friendsNodeIDs[n64 + 1] != 0)) {
                                final int n65 = this.friendsNodeIDs[n64];
                                this.friendsNodeIDs[n64] = this.friendsNodeIDs[n64 + 1];
                                this.friendsNodeIDs[n64 + 1] = n65;
                                final String s = this.friendsList[n64];
                                this.friendsList[n64] = this.friendsList[n64 + 1];
                                this.friendsList[n64 + 1] = s;
                                final long n66 = this.friendsListAsLongs[n64];
                                this.friendsListAsLongs[n64] = this.friendsListAsLongs[n64 + 1];
                                this.friendsListAsLongs[n64 + 1] = n66;
                                client.needDrawTabArea = true;
                                n63 = 0;
                            }
                        }
                    }
                    this.pktType = -1;
                    return true;
                }
                case 110: {
                    if (client.tabID == 12) {
                        client.needDrawTabArea = true;
                    }
                    this.energy = this.inStream.readUnsignedByte();
                    this.pktType = -1;
                    return true;
                }
                case 254: {
                    this.anInt855 = this.inStream.readUnsignedByte();
                    if (this.anInt855 == 1) {
                        this.anInt1222 = this.inStream.readUnsignedWord();
                    }
                    if (this.anInt855 >= 2 && this.anInt855 <= 6) {
                        if (this.anInt855 == 2) {
                            this.anInt937 = 64;
                            this.anInt938 = 64;
                        }
                        if (this.anInt855 == 3) {
                            this.anInt937 = 0;
                            this.anInt938 = 64;
                        }
                        if (this.anInt855 == 4) {
                            this.anInt937 = 128;
                            this.anInt938 = 64;
                        }
                        if (this.anInt855 == 5) {
                            this.anInt937 = 64;
                            this.anInt938 = 0;
                        }
                        if (this.anInt855 == 6) {
                            this.anInt937 = 64;
                            this.anInt938 = 128;
                        }
                        this.anInt855 = 2;
                        this.anInt934 = this.inStream.readUnsignedWord();
                        this.anInt935 = this.inStream.readUnsignedWord();
                        this.anInt936 = this.inStream.readUnsignedByte();
                    }
                    if (this.anInt855 == 10) {
                        this.anInt933 = this.inStream.readUnsignedWord();
                    }
                    this.pktType = -1;
                    return true;
                }
                case 248: {
                    final int method452 = this.inStream.method435();
                    final int unsignedWord4 = this.inStream.readUnsignedWord();
                    if (this.backDialogID != -1) {
                        this.backDialogID = -1;
                        client.inputTaken = true;
                    }
                    if (this.inputDialogState != 0) {
                        this.inputDialogState = 0;
                        client.inputTaken = true;
                    }
                    client.openInterfaceID = method452;
                    this.invOverlayInterfaceID = unsignedWord4;
                    client.needDrawTabArea = true;
                    client.tabAreaAltered = true;
                    this.aBoolean1149 = false;
                    this.pktType = -1;
                    return true;
                }
                case 79: {
                    final int method453 = this.inStream.method434();
                    int method454 = this.inStream.method435();
                    final RSInterface rsInterface3 = RSInterface.interfaceCache[method453];
                    if (rsInterface3 != null && rsInterface3.type == 0) {
                        if (method454 < 0) {
                            method454 = 0;
                        }
                        if (method454 > rsInterface3.scrollMax - rsInterface3.height) {
                            method454 = rsInterface3.scrollMax - rsInterface3.height;
                        }
                        rsInterface3.scrollPosition = method454;
                    }
                    this.pktType = -1;
                    return true;
                }
                case 68: {
                    for (int n67 = 0; n67 < this.variousSettings.length; ++n67) {
                        if (this.variousSettings[n67] != this.anIntArray1045[n67]) {
                            this.variousSettings[n67] = this.anIntArray1045[n67];
                            this.method33(n67);
                            client.needDrawTabArea = true;
                        }
                    }
                    this.pktType = -1;
                    return true;
                }
                case 196: {
                    final long qWord2 = this.inStream.readQWord();
                    final int dWord = this.inStream.readDWord();
                    final int unsignedByte9 = this.inStream.readUnsignedByte();
                    boolean b4 = false;
                    if (unsignedByte9 <= 1) {
                        for (int n68 = 0; n68 < this.ignoreCount; ++n68) {
                            if (this.ignoreListAsLongs[n68] == qWord2) {
                                b4 = true;
                            }
                        }
                    }
                    if (!b4 && this.anInt1251 == 0) {
                        try {
                            this.anIntArray1240[this.anInt1169] = dWord;
                            this.anInt1169 = (this.anInt1169 + 1) % 100;
                            final String method455 = TextInput.method525(this.pktSize - 13, this.inStream);
                            if (unsignedByte9 == 4) {
                                this.pushMessage(method455, 7, "@cr4@" + TextClass.fixName(TextClass.nameForLong(qWord2)));
                            }
                            else if (unsignedByte9 == 2 || unsignedByte9 == 3) {
                                this.pushMessage(method455, 7, "@cr2@" + TextClass.fixName(TextClass.nameForLong(qWord2)));
                            }
                            else if (unsignedByte9 == 1) {
                                this.pushMessage(method455, 7, "@cr1@" + TextClass.fixName(TextClass.nameForLong(qWord2)));
                            }
                            else {
                                this.pushMessage(method455, 3, TextClass.fixName(TextClass.nameForLong(qWord2)));
                            }
                        }
                        catch (Exception ex5) {
                            SignLink.reporterror("cde1");
                        }
                    }
                    this.pktType = -1;
                    return true;
                }
                case 85: {
                    this.anInt1269 = this.inStream.method427();
                    this.anInt1268 = this.inStream.method427();
                    this.pktType = -1;
                    return true;
                }
                case 24: {
                    this.anInt1054 = this.inStream.method428();
                    if (this.anInt1054 == client.tabID) {
                        if (this.anInt1054 == 3) {
                            client.tabID = 1;
                        }
                        else {
                            client.tabID = 3;
                        }
                        client.needDrawTabArea = true;
                    }
                    this.pktType = -1;
                    return true;
                }
                case 246: {
                    final int method456 = this.inStream.method434();
                    final int unsignedWord5 = this.inStream.readUnsignedWord();
                    final int unsignedWord6 = this.inStream.readUnsignedWord();
                    if (unsignedWord6 == 65535) {
                        RSInterface.interfaceCache[method456].anInt233 = 0;
                        this.pktType = -1;
                        return true;
                    }
                    final ItemDef forID = ItemDef.forID(unsignedWord6);
                    RSInterface.interfaceCache[method456].anInt233 = 4;
                    RSInterface.interfaceCache[method456].mediaID = unsignedWord6;
                    RSInterface.interfaceCache[method456].modelRotation1 = forID.modelRotation1;
                    RSInterface.interfaceCache[method456].modelRotation2 = forID.modelRotation2;
                    RSInterface.interfaceCache[method456].modelZoom = forID.modelZoom * 100 / unsignedWord5;
                    this.pktType = -1;
                    return true;
                }
                case 171: {
                    RSInterface.interfaceCache[this.inStream.readUnsignedWord()].isMouseoverTriggered = (this.inStream.readUnsignedByte() == 1);
                    this.pktType = -1;
                    return true;
                }
                case 142: {
                    final int method457 = this.inStream.method434();
                    this.method60(method457);
                    if (this.backDialogID != -1) {
                        this.backDialogID = -1;
                        client.inputTaken = true;
                    }
                    if (this.inputDialogState != 0) {
                        this.inputDialogState = 0;
                        client.inputTaken = true;
                    }
                    this.invOverlayInterfaceID = method457;
                    client.needDrawTabArea = true;
                    client.tabAreaAltered = true;
                    client.openInterfaceID = -1;
                    this.aBoolean1149 = false;
                    this.pktType = -1;
                    return true;
                }
                case 126: {
                    final String string3 = this.inStream.readString();
                    final int method458 = this.inStream.method435();
                    if (string3.startsWith("www.")) {
                        this.launchURL(string3);
                        this.pktType = -1;
                        return true;
                    }
                    this.updateStrings(string3, method458);
                    this.sendFrame126(string3, method458);
                    if (method458 >= 18144 && method458 <= 18244) {
                        this.clanList[method458 - 18144] = string3;
                    }
                    this.pktType = -1;
                    return true;
                }
                case 206: {
                    this.publicChatMode = this.inStream.readUnsignedByte();
                    this.privateChatMode = this.inStream.readUnsignedByte();
                    this.tradeMode = this.inStream.readUnsignedByte();
                    this.aBoolean1233 = true;
                    client.inputTaken = true;
                    this.pktType = -1;
                    return true;
                }
                case 240: {
                    if (client.tabID == 12) {
                        client.needDrawTabArea = true;
                    }
                    this.weight = this.inStream.readSignedWord();
                    this.pktType = -1;
                    return true;
                }
                case 8: {
                    final int method459 = this.inStream.method436();
                    final int unsignedWord7 = this.inStream.readUnsignedWord();
                    RSInterface.interfaceCache[method459].anInt233 = 1;
                    RSInterface.interfaceCache[method459].mediaID = unsignedWord7;
                    this.pktType = -1;
                    return true;
                }
                case 122: {
                    final int method460 = this.inStream.method436();
                    final int method461 = this.inStream.method436();
                    RSInterface.interfaceCache[method460].textColor = ((method461 >> 10 & 0x1F) << 19) + ((method461 >> 5 & 0x1F) << 11) + ((method461 & 0x1F) << 3);
                    this.pktType = -1;
                    return true;
                }
                case 53: {
                    client.needDrawTabArea = true;
                    final RSInterface rsInterface4 = RSInterface.interfaceCache[this.inStream.readUnsignedWord()];
                    final int unsignedWord8 = this.inStream.readUnsignedWord();
                    for (int n69 = 0; n69 < unsignedWord8; ++n69) {
                        try {
                            int n70 = this.inStream.readUnsignedByte();
                            if (n70 == 255) {
                                n70 = this.inStream.method440();
                            }
                            rsInterface4.inv[n69] = this.inStream.method436();
                            rsInterface4.invStackSizes[n69] = n70;
                        }
                        catch (Exception ex3) {
                            ex3.printStackTrace();
                        }
                    }
                    for (int n71 = unsignedWord8; n71 < rsInterface4.inv.length; ++n71) {
                        rsInterface4.inv[n71] = 0;
                        rsInterface4.invStackSizes[n71] = 0;
                    }
                    this.pktType = -1;
                    return true;
                }
                case 230: {
                    final int method462 = this.inStream.method435();
                    final int unsignedWord9 = this.inStream.readUnsignedWord();
                    final int unsignedWord10 = this.inStream.readUnsignedWord();
                    final int method463 = this.inStream.method436();
                    RSInterface.interfaceCache[unsignedWord9].modelRotation1 = unsignedWord10;
                    RSInterface.interfaceCache[unsignedWord9].modelRotation2 = method463;
                    RSInterface.interfaceCache[unsignedWord9].modelZoom = method462;
                    this.pktType = -1;
                    return true;
                }
                case 221: {
                    this.anInt900 = this.inStream.readUnsignedByte();
                    client.needDrawTabArea = true;
                    this.pktType = -1;
                    return true;
                }
                case 177: {
                    this.aBoolean1160 = true;
                    this.anInt995 = this.inStream.readUnsignedByte();
                    this.anInt996 = this.inStream.readUnsignedByte();
                    this.anInt997 = this.inStream.readUnsignedWord();
                    this.anInt998 = this.inStream.readUnsignedByte();
                    this.anInt999 = this.inStream.readUnsignedByte();
                    if (this.anInt999 >= 100) {
                        final int n72 = this.anInt995 * 128 + 64;
                        final int n73 = this.anInt996 * 128 + 64;
                        final int n74 = this.method42(this.plane, n73, n72) - this.anInt997;
                        final int n75 = n72 - this.xCameraPos;
                        final int n76 = n74 - this.zCameraPos;
                        final int n77 = n73 - this.yCameraPos;
                        this.yCameraCurve = ((int)(Math.atan2(n76, (int)Math.sqrt(n75 * n75 + n77 * n77)) * 325.949) & 0x7FF);
                        this.xCameraCurve = ((int)(Math.atan2(n75, n77) * -325.949) & 0x7FF);
                        if (this.yCameraCurve < 128) {
                            this.yCameraCurve = 128;
                        }
                        if (this.yCameraCurve > 383) {
                            this.yCameraCurve = 383;
                        }
                    }
                    this.pktType = -1;
                    return true;
                }
                case 249: {
                    this.anInt1046 = this.inStream.method426();
                    this.unknownInt10 = this.inStream.method436();
                    this.pktType = -1;
                    return true;
                }
                case 65: {
                    this.updateNPCs(this.inStream, this.pktSize);
                    this.pktType = -1;
                    return true;
                }
                case 27: {
                    this.messagePromptRaised = false;
                    this.inputDialogState = 1;
                    this.amountOrNameInput = "";
                    client.inputTaken = true;
                    this.pktType = -1;
                    return true;
                }
                case 187: {
                    this.messagePromptRaised = false;
                    this.inputDialogState = 2;
                    this.amountOrNameInput = "";
                    client.inputTaken = true;
                    this.pktType = -1;
                    return true;
                }
                case 97: {
                    final int unsignedWord11 = this.inStream.readUnsignedWord();
                    this.method60(unsignedWord11);
                    if (this.invOverlayInterfaceID != -1) {
                        this.invOverlayInterfaceID = -1;
                        client.needDrawTabArea = true;
                        client.tabAreaAltered = true;
                    }
                    if (this.backDialogID != -1) {
                        this.backDialogID = -1;
                        client.inputTaken = true;
                    }
                    if (this.inputDialogState != 0) {
                        this.inputDialogState = 0;
                        client.inputTaken = true;
                    }
                    client.openInterfaceID = unsignedWord11;
                    this.aBoolean1149 = false;
                    this.pktType = -1;
                    return true;
                }
                case 218: {
                    this.dialogID = this.inStream.method438();
                    client.inputTaken = true;
                    this.pktType = -1;
                    return true;
                }
                case 87: {
                    final int method464 = this.inStream.method434();
                    final int method465 = this.inStream.method439();
                    this.anIntArray1045[method464] = method465;
                    if (this.variousSettings[method464] != method465) {
                        this.variousSettings[method464] = method465;
                        this.method33(method464);
                        client.needDrawTabArea = true;
                        if (this.dialogID != -1) {
                            client.inputTaken = true;
                        }
                    }
                    this.pktType = -1;
                    return true;
                }
                case 36: {
                    final int method466 = this.inStream.method434();
                    final byte signedByte = this.inStream.readSignedByte();
                    this.anIntArray1045[method466] = signedByte;
                    if (method466 == 173) {
                        this.runClicked = (signedByte != 0);
                    }
                    if (this.variousSettings[method466] != signedByte) {
                        this.variousSettings[method466] = signedByte;
                        this.method33(method466);
                        client.needDrawTabArea = true;
                        if (this.dialogID != -1) {
                            client.inputTaken = true;
                        }
                    }
                    this.pktType = -1;
                    return true;
                }
                case 61: {
                    this.anInt1055 = this.inStream.readUnsignedByte();
                    this.pktType = -1;
                    return true;
                }
                case 200: {
                    final int unsignedWord12 = this.inStream.readUnsignedWord();
                    final int signedWord2 = this.inStream.readSignedWord();
                    final RSInterface rsInterface5 = RSInterface.interfaceCache[unsignedWord12];
                    rsInterface5.anInt257 = signedWord2;
                    if (signedWord2 == -1) {
                        rsInterface5.anInt246 = 0;
                        rsInterface5.anInt208 = 0;
                    }
                    this.pktType = -1;
                    return true;
                }
                case 219: {
                    if (this.invOverlayInterfaceID != -1) {
                        this.invOverlayInterfaceID = -1;
                        client.needDrawTabArea = true;
                        client.tabAreaAltered = true;
                    }
                    if (this.backDialogID != -1) {
                        this.backDialogID = -1;
                        client.inputTaken = true;
                    }
                    if (this.inputDialogState != 0) {
                        this.inputDialogState = 0;
                        client.inputTaken = true;
                    }
                    client.openInterfaceID = -1;
                    this.aBoolean1149 = false;
                    this.pktType = -1;
                    return true;
                }
                case 34: {
                    client.needDrawTabArea = true;
                    final RSInterface rsInterface6 = RSInterface.interfaceCache[this.inStream.readUnsignedWord()];
                    while (this.inStream.currentOffset < this.pktSize) {
                        final int method467 = this.inStream.method422();
                        final int unsignedWord13 = this.inStream.readUnsignedWord();
                        int n78 = this.inStream.readUnsignedByte();
                        if (n78 == 255) {
                            n78 = this.inStream.readDWord();
                        }
                        if (method467 >= 0 && method467 < rsInterface6.inv.length) {
                            rsInterface6.inv[method467] = unsignedWord13;
                            rsInterface6.invStackSizes[method467] = n78;
                        }
                    }
                    this.pktType = -1;
                    return true;
                }
                case 4:
                case 44:
                case 84:
                case 101:
                case 105:
                case 117:
                case 147:
                case 151:
                case 156:
                case 160:
                case 215: {
                    this.method137(this.inStream, this.pktType);
                    this.pktType = -1;
                    return true;
                }
                case 106: {
                    client.tabID = this.inStream.method427();
                    client.needDrawTabArea = true;
                    client.tabAreaAltered = true;
                    this.pktType = -1;
                    return true;
                }
                case 164: {
                    final int method468 = this.inStream.method434();
                    this.method60(method468);
                    if (this.invOverlayInterfaceID != -1) {
                        this.invOverlayInterfaceID = -1;
                        client.needDrawTabArea = true;
                        client.tabAreaAltered = true;
                    }
                    this.backDialogID = method468;
                    client.inputTaken = true;
                    client.openInterfaceID = -1;
                    this.aBoolean1149 = false;
                    this.pktType = -1;
                    return true;
                }
            }
        }
        catch (IOException ex6) {}
        catch (Exception ex7) {
            String s2 = "T2 - " + this.pktType + "," + this.anInt842 + "," + this.anInt843 + " - " + this.pktSize + "," + (this.baseX + client.myPlayer.smallX[0]) + "," + (this.baseY + client.myPlayer.smallY[0]) + " - ";
            for (int n79 = 0; n79 < this.pktSize && n79 < 50; ++n79) {
                s2 = s2 + this.inStream.buffer[n79] + ",";
            }
        }
        this.pktType = -1;
        return true;
    }
    
    public void method146() {
        ++this.anInt1265;
        this.method47(true);
        this.method26(true);
        this.method47(false);
        this.method26(false);
        this.method55();
        this.method104();
        if (!this.aBoolean1160) {
            int anInt1184 = this.anInt1184;
            if (this.anInt984 / 256 > anInt1184) {
                anInt1184 = this.anInt984 / 256;
            }
            if (this.aBooleanArray876[4] && this.anIntArray1203[4] + 128 > anInt1184) {
                anInt1184 = this.anIntArray1203[4] + 128;
            }
            this.setCameraPos(((client.clientSize == 0) ? 600 : this.zoomLevel) + anInt1184 * 3, anInt1184, this.anInt1014, this.method42(this.plane, client.myPlayer.y, client.myPlayer.x) - 50, this.minimapInt1 + this.anInt896 & 0x7FF, this.anInt1015);
        }
        int n;
        if (!this.aBoolean1160) {
            n = this.method120();
        }
        else {
            n = this.method121();
        }
        final int xCameraPos = this.xCameraPos;
        final int zCameraPos = this.zCameraPos;
        final int yCameraPos = this.yCameraPos;
        final int yCameraCurve = this.yCameraCurve;
        final int xCameraCurve = this.xCameraCurve;
        for (int i = 0; i < 5; ++i) {
            if (!this.aBooleanArray876[i]) {
                final int n2 = (int)(Math.random() * (this.anIntArray873[i] * 2 + 1) - this.anIntArray873[i] + Math.sin(this.anIntArray1030[i] * (this.anIntArray928[i] / 100.0)) * this.anIntArray1203[i]);
                if (i == 0) {
                    this.xCameraPos += n2;
                }
                if (i == 1) {
                    this.zCameraPos += n2;
                }
                if (i == 2) {
                    this.yCameraPos += n2;
                }
                if (i == 3) {
                    this.xCameraCurve = (this.xCameraCurve + n2 & 0x7FF);
                }
                if (i == 4) {
                    this.yCameraCurve += n2;
                    if (this.yCameraCurve < 128) {
                        this.yCameraCurve = 128;
                    }
                    if (this.yCameraCurve > 383) {
                        this.yCameraCurve = 383;
                    }
                }
            }
        }
        final int anInt1185 = Texture.anInt1481;
        Model.aBoolean1684 = true;
        Model.anInt1687 = 0;
        Model.anInt1685 = this.mouseX - 4;
        Model.anInt1686 = this.mouseY - 4;
        DrawingArea.setAllPixelsToZero();
        try {
            this.worldController.method313(this.xCameraPos, this.yCameraPos, this.xCameraCurve, this.zCameraPos, n, this.yCameraCurve);
        }
        catch (Exception ex) {}
        this.worldController.clearObj5Cache();
        this.updateEntities();
        this.drawHeadIcon();
        if (client.clientSize >= 1) {
            this.drawChatArea();
            this.drawMinimap();
            this.drawTabArea();
        }
        this.method37(anInt1185);
        this.draw3dScreen();
        if (client.clientSize == 0) {
            this.graphics.setColor(Color.BLACK);
            this.graphics.fillRect(0, 0, (client.clientWidth - 765) / 2, client.clientHeight);
            this.graphics.fillRect(0, 503, client.clientWidth, client.clientHeight - 503);
            this.graphics.fillRect(client.clientWidth - (client.clientWidth - 765) / 2 - 8, 0, (client.clientWidth - 765) / 2, client.clientHeight);
        }
        if (this.counterOn) {
            this.drawCounterOnScreen();
        }
        if (client.clientSize == 0) {
            this.gameScreenIP.drawGraphics(4, this.graphics, 4);
        }
        else if (client.clientSize >= 1) {
            this.gameScreenIP.drawGraphics(0, this.graphics, 0);
        }
        this.xCameraPos = xCameraPos;
        this.zCameraPos = zCameraPos;
        this.yCameraPos = yCameraPos;
        this.yCameraCurve = yCameraCurve;
        this.xCameraCurve = xCameraCurve;
    }
    
    public void clearTopInterfaces() {
        this.stream.createFrame(130);
        if (this.invOverlayInterfaceID != -1) {
            this.invOverlayInterfaceID = -1;
            client.needDrawTabArea = true;
            this.aBoolean1149 = false;
            client.tabAreaAltered = true;
        }
        if (this.backDialogID != -1) {
            this.backDialogID = -1;
            client.inputTaken = true;
            this.aBoolean1149 = false;
        }
        client.openInterfaceID = -1;
        this.fullscreenInterfaceID = -1;
    }
    
    public client() {
        this.Autocast = false;
        this.autocastId = 0;
        this.counterOn = false;
        this.saveMaps = false;
        this.positions = new int[23];
        this.landScapes = new int[23];
        this.objects = new int[23];
        this.displayChat = true;
        this.timeMe = System.currentTimeMillis();
        this.isNewGameFrame = true;
        this.webclient = true;
        this.world = 1;
        this.spec = " Regular World";
        this.zoomLevel = 500;
        this.clanList = new String[100];
        this.anIntArray965 = new int[] { 16776960, 16711680, 65280, 65535, 16711935, 16777215 };
        this.combatIcons = new Sprite[4];
        this.anIntArray1177 = new int[] { 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3 };
        this.newHitMarks = true;
        this.newHpBar = true;
        this.screenHover = new boolean[] { false, false, false, false };
        this.globe = new Sprite[3];
        this.ORBS = new Sprite[16];
        this.runHover = false;
        this.runClicked = true;
        this.prayHover = false;
        this.prayClicked = false;
        this.safeZone = false;
        this.pvpIcon = new Sprite[3];
        this.countToSafe = false;
        this.pvpWindow = false;
        this.count = 0;
        this.LP = 0.0f;
        this.fsSprite = new Sprite[16];
        this.counter = new Sprite[3];
        this.chatToggle = new Sprite[2];
        this.tabArea562LongFS = new Sprite[2];
        this.fullscreenInterfaceID = -1;
        this.chatRights = new int[500];
        this.tabHPos = -1;
        this.chatTypeView = 0;
        this.clanChatMode = 0;
        this.cButtonHPos = -1;
        this.cButtonHCPos = -1;
        this.cButtonCPos = 0;
        client.server = "devilishpkz.no-ip.biz";
        this.anIntArrayArray825 = new int[104][104];
        this.friendsNodeIDs = new int[200];
        this.groundArray = new NodeList[4][104][104];
        this.aStream_834 = new Stream(new byte[25000]);
        this.aBoolean831 = false;
        this.npcArray = new NPC[16384];
        this.npcIndices = new int[16384];
        this.anIntArray840 = new int[1000];
        this.aStream_847 = Stream.create();
        this.aBoolean848 = true;
        client.openInterfaceID = -1;
        this.currentExp = new int[Skills.skillsCount];
        this.aBoolean872 = false;
        this.anIntArray873 = new int[5];
        this.anInt874 = -1;
        this.aBooleanArray876 = new boolean[5];
        this.drawFlames = false;
        this.reportAbuseInput = "";
        this.unknownInt10 = -1;
        this.menuOpen = false;
        this.inputString = "";
        this.maxPlayers = 2048;
        this.myPlayerIndex = 2047;
        this.playerArray = new Player[this.maxPlayers];
        this.playerIndices = new int[this.maxPlayers];
        this.anIntArray894 = new int[this.maxPlayers];
        this.aStreamArray895s = new Stream[this.maxPlayers];
        this.anInt897 = 1;
        this.anIntArrayArray901 = new int[104][104];
        this.anInt902 = 7759444;
        this.aByteArray912 = new byte[16384];
        this.currentStats = new int[Skills.skillsCount];
        this.ignoreListAsLongs = new long[100];
        this.loadingError = false;
        this.anInt927 = 3353893;
        this.anIntArray928 = new int[5];
        this.anIntArrayArray929 = new int[104][104];
        this.chatTypes = new int[500];
        this.chatNames = new String[500];
        this.chatMessages = new String[500];
        this.chatButtons = new Sprite[4];
        this.sideIcons = new Sprite[15];
        this.newSideIcons = new Sprite[15];
        this.redStones = new Sprite[5];
        this.aBoolean954 = true;
        this.friendsListAsLongs = new long[200];
        this.currentSong = -1;
        this.drawingFlames = false;
        this.spriteDrawX = -1;
        this.spriteDrawY = -1;
        this.anIntArray968 = new int[33];
        this.anIntArray969 = new int[256];
        this.decompressors = new Decompressor[5];
        this.variousSettings = new int[2000];
        this.aBoolean972 = false;
        this.anInt975 = 50;
        this.anIntArray976 = new int[this.anInt975];
        this.anIntArray977 = new int[this.anInt975];
        this.anIntArray978 = new int[this.anInt975];
        this.anIntArray979 = new int[this.anInt975];
        this.anIntArray980 = new int[this.anInt975];
        this.anIntArray981 = new int[this.anInt975];
        this.anIntArray982 = new int[this.anInt975];
        this.aStringArray983 = new String[this.anInt975];
        this.anInt985 = -1;
        this.hitMarks = new Sprite[23];
        this.anIntArray990 = new int[5];
        this.aBoolean994 = false;
        this.anInt1002 = 2301979;
        this.amountOrNameInput = "";
        this.aClass19_1013 = new NodeList();
        this.aBoolean1017 = false;
        this.anInt1018 = -1;
        this.anIntArray1030 = new int[5];
        this.aBoolean1031 = false;
        this.mapFunctions = new Sprite[100];
        this.dialogID = -1;
        this.maxStats = new int[Skills.skillsCount];
        this.anIntArray1045 = new int[2000];
        this.aBoolean1047 = true;
        this.anIntArray1052 = new int[157];
        this.anInt1054 = -1;
        this.aClass19_1056 = new NodeList();
        this.anIntArray1057 = new int[33];
        this.aClass9_1059 = new RSInterface();
        this.mapScenes = new Background[100];
        this.barFillColor = 5063219;
        this.anIntArray1065 = new int[7];
        this.anIntArray1072 = new int[1000];
        this.anIntArray1073 = new int[1000];
        this.aBoolean1080 = false;
        this.friendsList = new String[200];
        this.inStream = Stream.create();
        this.expectedCRCs = new int[9];
        this.menuActionCmd2 = new int[500];
        this.menuActionCmd3 = new int[500];
        this.menuActionID = new int[500];
        this.menuActionCmd1 = new int[500];
        this.headIcons = new Sprite[20];
        this.skullIcons = new Sprite[20];
        this.headIconsHint = new Sprite[20];
        client.tabAreaAltered = false;
        this.aString1121 = "";
        this.atPlayerActions = new String[5];
        this.atPlayerArray = new boolean[5];
        this.anIntArrayArrayArray1129 = new int[4][13][13];
        this.anInt1132 = 2;
        this.aClass30_Sub2_Sub1_Sub1Array1140 = new Sprite[1000];
        this.aBoolean1141 = false;
        this.aBoolean1149 = false;
        this.crosses = new Sprite[8];
        this.musicEnabled = true;
        client.needDrawTabArea = false;
        this.loggedIn = false;
        this.canMute = false;
        this.aBoolean1159 = false;
        this.aBoolean1160 = false;
        this.anInt1171 = 1;
        this.myUsername = "";
        this.myPassword = "";
        this.genericLoadingError = false;
        this.reportAbuseInterfaceID = -1;
        this.aClass19_1179 = new NodeList();
        this.anInt1184 = 128;
        this.invOverlayInterfaceID = -1;
        this.stream = Stream.create();
        this.menuActionName = new String[500];
        this.anIntArray1203 = new int[5];
        this.anIntArray1207 = new int[50];
        this.anInt1210 = 2;
        this.anInt1211 = 78;
        this.promptInput = "";
        this.modIcons = new Sprite[5];
        client.tabID = 3;
        client.inputTaken = false;
        this.songChanging = true;
        this.anIntArray1229 = new int[157];
        this.aClass11Array1230 = new Class11[4];
        this.aBoolean1233 = false;
        this.anIntArray1240 = new int[100];
        this.anIntArray1241 = new int[50];
        this.aBoolean1242 = false;
        this.anIntArray1250 = new int[50];
        this.rsAlreadyLoaded = false;
        this.welcomeScreenRaised = false;
        this.messagePromptRaised = false;
        this.loginMessage1 = "";
        this.loginMessage2 = "";
        this.backDialogID = -1;
        this.anInt1279 = 2;
        this.bigX = new int[4000];
        this.bigY = new int[4000];
        this.anInt1289 = -1;
    }
    
    public void resetAllImageProducers() {
        if (super.fullGameScreen != null) {
            return;
        }
        this.chatAreaIP = null;
        this.mapAreaIP = null;
        this.CustomMapBack = null;
        this.tabAreaIP = null;
        this.gameScreenIP = null;
        this.aRSImageProducer_1123 = null;
        this.aRSImageProducer_1124 = null;
        this.aRSImageProducer_1125 = null;
        this.aRSImageProducer_1107 = null;
        this.aRSImageProducer_1108 = null;
        this.aRSImageProducer_1109 = null;
        this.aRSImageProducer_1110 = null;
        this.aRSImageProducer_1111 = null;
        this.aRSImageProducer_1112 = null;
        this.aRSImageProducer_1113 = null;
        this.aRSImageProducer_1114 = null;
        this.aRSImageProducer_1115 = null;
        super.fullGameScreen = new RSImageProducer(765, 503, this.getGameComponent());
        this.welcomeScreenRaised = true;
    }
    
    public void launchURL(final String s) {
        final String property = System.getProperty("os.name");
        try {
            if (property.startsWith("Mac OS")) {
                Class.forName("com.apple.eio.FileManager").getDeclaredMethod("openURL", String.class).invoke(null, s);
            }
            else if (property.startsWith("Windows")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + s);
            }
            else {
                final String[] array = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape", "safari" };
                String s2 = null;
                for (int n = 0; n < array.length && s2 == null; ++n) {
                    if (Runtime.getRuntime().exec(new String[] { "which", array[n] }).waitFor() == 0) {
                        s2 = array[n];
                    }
                }
                if (s2 == null) {
                    throw new Exception("Could not find web browser");
                }
                Runtime.getRuntime().exec(new String[] { s2, s });
            }
        }
        catch (Exception ex) {
            this.pushMessage("Failed to open URL.", 0, "");
        }
    }
    
    private void drawLoadingMessages(final int n, final String s, final String s2) {
        final int textWidth = this.aTextDrawingArea_1271.getTextWidth((n == 1) ? s : s2);
        final int n2 = (s2 == null) ? 25 : 38;
        DrawingArea.drawPixels(n2, 1, 1, 0, textWidth + 6);
        DrawingArea.drawPixels(1, 1, 1, 16777215, textWidth + 6);
        DrawingArea.drawPixels(n2, 1, 1, 16777215, 1);
        DrawingArea.drawPixels(1, n2, 1, 16777215, textWidth + 6);
        DrawingArea.drawPixels(n2, 1, textWidth + 6, 16777215, 1);
        this.aTextDrawingArea_1271.drawText(16777215, s, 18, textWidth / 2 + 5);
        if (s2 != null) {
            this.aTextDrawingArea_1271.drawText(16777215, s2, 31, textWidth / 2 + 5);
        }
    }
    
    public void toggleSize(final int clientSize) {
        if (this.loggedIn) {
            if ((client.clientSize = clientSize) == 1) {
                this.setResizable(false);
                this.getResolution();
                this.getGameComponent().setSize(client.clientWidth, client.clientHeight);
                if (super.gameFrame != null) {
                    super.gameFrame.setExtendedState(6);
                }
            }
            else if (clientSize == 0) {
                client.clientWidth = 765;
                client.clientHeight = 503;
                this.setResizable(false);
                this.getGameComponent().setSize(client.clientWidth + 8, client.clientHeight + 28);
            }
            else if (clientSize == 2) {
                this.setResizable(true);
                if (this.getGameComponent().getWidth() <= 980) {
                    this.getGameComponent().setSize(990, this.getGameComponent().getHeight());
                }
                client.clientWidth = this.getGameComponent().getWidth() - ((super.gameFrame == null) ? 0 : 10);
                client.clientHeight = this.getGameComponent().getHeight() - ((super.gameFrame == null) ? 0 : 32);
            }
            Label_0303: {
                if (clientSize == 0) {
                    try {
                        Texture.method365(519, 165);
                        this.anIntArray1180 = Texture.anIntArray1472;
                        Texture.method365(246, 335);
                        this.anIntArray1181 = Texture.anIntArray1472;
                        Texture.method365(512, 334);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    if (clientSize != 1) {
                        if (clientSize != 2) {
                            break Label_0303;
                        }
                    }
                    try {
                        Texture.method365(client.clientWidth, client.clientHeight);
                        this.anIntArray1180 = Texture.anIntArray1472;
                        Texture.method365(client.clientWidth, client.clientHeight);
                        this.anIntArray1181 = Texture.anIntArray1472;
                        Texture.method365(client.clientWidth, client.clientHeight);
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                }
            }
            this.anIntArray1182 = Texture.anIntArray1472;
            final int[] array = new int[9];
            for (int i = 0; i < 9; ++i) {
                final int n = 128 + i * 32 + 15;
                array[i] = (600 + n * 3) * Texture.anIntArray1470[n] >> 16;
            }
            if (clientSize == 0) {
                WorldController.method310(500, 800, 512, 334, array);
            }
            else if (clientSize == 1 || clientSize == 2) {
                WorldController.method310(500, 800, client.clientWidth, client.clientHeight, array);
            }
            try {
                this.resetImageProducers();
                this.resetImageProducers2();
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
        }
    }
    
    public boolean isUserOSWin7() {
        return System.getProperty("os.name").contains("Windows 7");
    }
    
    public void getResolution() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        client.clientWidth = screenSize.width;
        client.clientHeight = (this.isUserOSWin7() ? (screenSize.height - 40) : (screenSize.height - 20));
    }
    
    public void drawSumm() {
        final int int1 = Integer.parseInt(RSInterface.interfaceCache[4030].message);
        this.sumhover = new Sprite("Gameframe/Orbs/sumhover");
        (this.sumnormal = new Sprite("Gameframe/Orbs/sumnormal")).drawSprite(174, 127);
        this.smallText.method382(65280, 217, "" + int1, 153, true);
        if (super.mouseX >= 687 && super.mouseX <= 744 && super.mouseY >= 119 && super.mouseY < 157) {
            this.sumhover.drawSprite(174, 127);
            this.smallText.method382(65280, 217, "" + int1, 153, true);
        }
    }
    
    public void loadOrbs() {
        this.drawPrayer();
        this.drawGlobe();
        this.drawRunOrb();
        this.drawSumm();
        if (client.globeState[0] && client.globeState[1]) {
            this.globe[2].drawSprite(10, 123);
        }
        else if (client.globeState[1]) {
            this.globe[2].drawSprite(10, 123);
        }
        else if (client.globeState[0]) {
            this.globe[1].drawSprite(10, 123);
        }
        else {
            this.globe[1].drawSprite(10, 123);
        }
    }
    
    public void drawGlobe() {
        if (super.mouseX >= 522 && super.mouseX <= 558 && super.mouseY >= 124 && super.mouseY < 161) {
            client.globeState[1] = true;
        }
        else {
            client.globeState[1] = false;
        }
    }
    
    public int getOrbTextColor(final int n) {
        if (n >= 75) {
            return 65280;
        }
        if (n >= 50 && n <= 74) {
            return 16776960;
        }
        if (n >= 25 && n <= 49) {
            return 16750623;
        }
        if (n < 25) {
            return 16711680;
        }
        return 65280;
    }
    
    public int getOrbFill(final int n) {
        if (n <= 100 && n >= 97) {
            return 0;
        }
        if (n <= 96 && n >= 93) {
            return 1;
        }
        if (n <= 92 && n >= 89) {
            return 2;
        }
        if (n <= 88 && n >= 85) {
            return 3;
        }
        if (n <= 84 && n >= 81) {
            return 4;
        }
        if (n <= 80 && n >= 77) {
            return 5;
        }
        if (n <= 76 && n >= 73) {
            return 6;
        }
        if (n <= 72 && n >= 69) {
            return 7;
        }
        if (n <= 68 && n >= 65) {
            return 8;
        }
        if (n <= 64 && n >= 61) {
            return 9;
        }
        if (n <= 60 && n >= 57) {
            return 10;
        }
        if (n <= 56 && n >= 53) {
            return 11;
        }
        if (n <= 52 && n >= 49) {
            return 12;
        }
        if (n <= 48 && n >= 45) {
            return 13;
        }
        if (n <= 44 && n >= 41) {
            return 14;
        }
        if (n <= 40 && n >= 37) {
            return 15;
        }
        if (n <= 36 && n >= 33) {
            return 16;
        }
        if (n <= 32 && n >= 29) {
            return 17;
        }
        if (n <= 28 && n >= 25) {
            return 18;
        }
        if (n <= 24 && n >= 21) {
            return 19;
        }
        if (n <= 20 && n >= 17) {
            return 20;
        }
        if (n <= 16 && n >= 13) {
            return 21;
        }
        if (n <= 12 && n >= 9) {
            return 22;
        }
        if (n <= 8 && n >= 7) {
            return 23;
        }
        if (n <= 6 && n >= 5) {
            return 24;
        }
        if (n <= 4 && n >= 3) {
            return 25;
        }
        if (n <= 2 && n >= 1) {
            return 26;
        }
        if (n <= 0) {
            return 27;
        }
        return 0;
    }
    
    private void drawPrayer() {
        final String string = SignLink.findcachedir() + "/Sprites/Gameframe/Orbs/";
        int int1 = Integer.parseInt(RSInterface.interfaceCache[4012].message);
        if (int1 < 0) {
            int1 = 0;
        }
        final int n = (int)(int1 / Integer.parseInt(RSInterface.interfaceCache[4013].message) * 100.0);
        this.ORBS[0] = new Sprite(string + "ORBS 0.PNG", 27, this.getOrbFill(n), false);
        if (client.clientSize == 0) {
            if (!this.prayClicked) {
                if (this.prayHover) {
                    this.ORBS[7].drawSprite(187, 53);
                }
                else if (!this.prayHover) {
                    this.ORBS[1].drawSprite(187, 53);
                }
                this.ORBS[4].drawSprite(190, 56);
            }
            else if (this.prayClicked) {
                if (this.prayHover) {
                    this.ORBS[7].drawSprite(187, 53);
                }
                else if (!this.prayHover) {
                    this.ORBS[1].drawSprite(187, 53);
                }
                this.ORBS[5].drawSprite(190, 56);
            }
            this.ORBS[0].drawSprite(190, 56);
            if (n <= 25) {
                if (client.loopCycle % 20 < 10) {
                    this.ORBS[6].drawSprite(194, 60);
                }
            }
            else {
                this.ORBS[6].drawSprite(194, 60);
            }
            this.smallText.method382(this.getOrbTextColor(n), 229, "" + Integer.parseInt(RSInterface.interfaceCache[4012].message) * 10, 79, true);
        }
    }
    
    public void drawRunOrb() {
        final int int1 = Integer.parseInt(RSInterface.interfaceCache[149].message.replaceAll("%", ""));
        new StringBuilder().append(SignLink.findcachedir()).append("/Sprites/Gameframe/Orbs/").toString();
        if (client.clientSize == 0) {
            if (!this.runClicked) {
                if (this.runHover) {
                    this.ORBS[7].drawSprite(188, 95);
                }
                else {
                    this.ORBS[1].drawSprite(188, 95);
                }
                this.ORBS[10].drawSprite(191, 98);
                this.ORBS[0].drawSprite(191, 98);
                this.ORBS[8].drawSprite(197, 102);
            }
            else {
                if (this.runHover) {
                    this.ORBS[7].drawSprite(188, 95);
                }
                else {
                    this.ORBS[1].drawSprite(188, 95);
                }
                this.ORBS[11].drawSprite(191, 98);
                this.ORBS[0].drawSprite(191, 98);
                this.ORBS[9].drawSprite(197, 102);
            }
            this.smallText.method382(this.getOrbTextColor(int1), 230, "@gre@" + this.energy, 121, true);
        }
    }
    
    private void drawFullOrbs(final int n) {
        this.loadFullHp(n);
        this.loadFullPrayer(n);
        this.loadFullRun(n);
    }
    
    private void loadFullHp(final int n) {
        final int n2 = Integer.parseInt(RSInterface.interfaceCache[4016].message.replaceAll("%", "")) * (client.newHits ? 10 : 1);
        final int n3 = (int)(n2 / (Integer.parseInt(RSInterface.interfaceCache[4017].message.replaceAll("%", "")) * (client.newHits ? 10 : 1)) * 100.0);
        this.ORBS[0] = new Sprite(SignLink.findcachedir() + "/Sprites/Gameframe/Orbs/" + "ORBS 0.PNG", 27, this.getOrbFill(n3), false);
        this.ORBS[15].drawSprite(n - 40, 35);
        this.ORBS[2].drawSprite(n - 14, 37);
        this.ORBS[0].drawSprite(n - 14, 37);
        if (n3 <= 20) {
            if (client.loopCycle % 20 < 10) {
                this.ORBS[3].drawSprite(n - 7, 45);
            }
        }
        else {
            this.ORBS[3].drawSprite(n - 7, 45);
        }
        this.smallText.method382(this.getOrbTextColor(n3), n - 26, Integer.toString(n2), 60, true);
    }
    
    private void loadFullPrayer(final int n) {
        final int n2 = 45;
        final String string = SignLink.findcachedir() + "/Sprites/Gameframe/Orbs/";
        final int int1 = Integer.parseInt(RSInterface.interfaceCache[4012].message);
        final int n3 = (int)(int1 / Integer.parseInt(RSInterface.interfaceCache[4013].message) * 100.0);
        this.ORBS[0] = new Sprite(string + "ORBS 0.PNG", 27, this.getOrbFill(n3), false);
        this.ORBS[15].drawSprite(n - 90 + n2, 70);
        this.ORBS[4].drawSprite(n - 64 + n2, 72);
        this.ORBS[0].drawSprite(n - 64 + n2, 72);
        this.ORBS[6].drawSprite(n - 60 + n2, 76);
        this.smallText.method382(this.getOrbTextColor(n3), n - 76 + n2, Integer.toString(int1), 95, true);
    }
    
    private void loadFullRun(final int n) {
        final int n2 = 55;
        final int n3 = 35;
        new StringBuilder().append(SignLink.findcachedir()).append("/Sprites/Gameframe/Orbs/").toString();
        final int int1 = Integer.parseInt(RSInterface.interfaceCache[149].message.replaceAll("%", ""));
        if (!this.runClicked) {
            if (this.runHover) {
                this.ORBS[14].drawSprite(n - 90 + n2, 70 + n3);
            }
            else {
                this.ORBS[15].drawSprite(n - 90 + n2, 70 + n3);
            }
            this.ORBS[4].drawSprite(n - 64 + n2, 72 + n3);
            this.ORBS[10].drawSprite(n - 64 + n2, 72 + n3);
            this.ORBS[8].drawSprite(n - 58 + n2, 77 + n3);
        }
        else {
            if (this.runHover) {
                this.ORBS[14].drawSprite(n - 90 + n2, 70 + n3);
            }
            else {
                this.ORBS[15].drawSprite(n - 90 + n2, 70 + n3);
            }
            this.ORBS[4].drawSprite(n - 64 + n2, 72 + n3);
            this.ORBS[11].drawSprite(n - 64 + n2, 72 + n3);
            this.ORBS[9].drawSprite(n - 58 + n2, 77 + n3);
        }
        this.smallText.method382(this.getOrbTextColor(int1), n - 76 + n2, "@gre@" + this.energy, 95 + n3, true);
    }
    
    private void processOrbClick() {
        this.processXPCounterClick();
        final int mouseX = super.mouseX;
        final int mouseY = super.mouseY;
        if (super.clickMode3 == 1 && mouseX >= 708 && mouseX <= 758 && mouseY >= 103 && mouseY < 126 && client.clientSize == 0) {
            this.runClicked = !this.runClicked;
            this.sendPacket185(152, 173, 646);
        }
        if (mouseX >= 708 && mouseX <= 758 && mouseY >= 103 && mouseY < 126 && client.clientSize == 0) {
            this.runHover = true;
            return;
        }
        if (mouseX >= 710 && mouseX <= 760 && mouseY >= 61 && mouseY < 84 && client.clientSize == 0) {
            this.prayHover = true;
            return;
        }
        this.runHover = false;
        this.prayHover = false;
    }
    
    static {
        client.canSwap = true;
        client.spellID = 0;
        client.newDamage = false;
        client.totalRead = 0;
        client.zoom = 0;
        client.nodeID = 10;
        client.isMembers = true;
        anIntArrayArray1003 = new int[][] { { 6798, 107, 10283, 16, 4797, 7744, 5799, 4634, 33697, 22433, 2983, 54193 }, { 8741, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153, 56621, 4783, 1341, 16578, 35003, 25239 }, { 25238, 8742, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153, 56621, 4783, 1341, 16578, 35003 }, { 4626, 11146, 6439, 12, 4758, 10270 }, { 4550, 4537, 5681, 5673, 5790, 6806, 8076, 4574 } };
        tabInterfaceIDs = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        anIntArray1204 = new int[] { 9104, 10275, 7595, 3610, 7975, 8526, 918, 38802, 24466, 10145, 58654, 5027, 1457, 16565, 34991, 25486 };
        client.server = "devilishpkz.no-ip.biz";
        client.newHits = true;
        client.globeState = new boolean[] { false, false };
        anIntArray1019 = new int[99];
        int n = 0;
        for (int i = 0; i < 99; ++i) {
            final int n2 = i + 1;
            n += (int)(n2 + 300.0 * Math.pow(2.0, n2 / 7.0));
            client.anIntArray1019[i] = n / 4;
        }
        client.anIntArray1232 = new int[32];
        int n3 = 2;
        for (int j = 0; j < 32; ++j) {
            client.anIntArray1232[j] = n3 - 1;
            n3 += n3;
        }
    }
}
