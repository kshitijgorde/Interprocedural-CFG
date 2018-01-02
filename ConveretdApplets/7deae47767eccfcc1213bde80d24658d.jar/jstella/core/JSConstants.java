// 
// Decompiled by Procyon v0.5.30
// 

package jstella.core;

public class JSConstants
{
    public static final double JSTELLA_VERSION_NUMBER = 0.95;
    public static final String JSTELLA_VERSION = "0.95 (beta)";
    public static final String JSTELLA_LONGTITLE = "JStella - Atari 2600 Emulator";
    public static final String JSTELLA_BYLINE_CORE = "Stella emulator by Bradford Mott and the Stella team";
    public static final String JSTELLA_BYLINE_JAVA = "Java adaptation and GUI by J.L. Allen and the JStella team";
    public static final String JSTELLA_HTTP = "http://jstella.sourceforge.net";
    public static final int LOG_MEMSIZE = 13;
    public static final int LOG_PAGESIZE = 6;
    public static final int ADDRESS_MASK = 8191;
    public static final int PAGE_MASK = 63;
    public static final int PAGE_SIZE = 64;
    public static final int PAGE_SHIFT = 6;
    public static final int PAGE_COUNT = 128;
    private static final String DIRECTORY_RESOURCES = "/jstella/resources/";
    public static final String RESOURCE_IMAGE_TEST_PATTERN = "/jstella/resources/testpattern.gif";
    public static final int RESISTANCE_MAX = Integer.MAX_VALUE;
    public static final int RESISTANCE_MIN = 0;
    public static final int TELEVISION_MODE_OFF = 0;
    public static final int TELEVISION_MODE_GAME = 1;
    public static final int TELEVISION_MODE_TEST_PATTERN = 2;
    public static final int TELEVISION_MODE_SNOW = 3;
    protected static final int CLOCKS_PER_CPU_CYCLE = 3;
    protected static final int CLOCKS_PER_LINE_BLANK = 68;
    protected static final int CLOCKS_PER_LINE_VISIBLE = 160;
    protected static final int CLOCKS_PER_LINE_TOTAL = 228;
    protected static final int LINES_PER_FRAME_TOTAL = 262;
    protected static final int CLOCKS_PER_FRAME = 59736;
    protected static final int CPU_CYCLES_PER_FRAME = 19912;
    protected static final int CLOCKS_PER_PLAYFIELD_BIT = 4;
    public static final int TIA_POKE_REGISTER_COUNT = 45;
    public static final int FRAME_Y_MAX = 300;
    public static final int FRAME_Y_MIN = 100;
    public static final int BIT0 = 1;
    public static final int BIT1 = 2;
    public static final int BIT2 = 4;
    public static final int BIT3 = 8;
    public static final int BIT4 = 16;
    public static final int BIT5 = 32;
    public static final int BIT6 = 64;
    public static final int BIT7 = 128;
    public static final double PIXEL_WIDTH_HEIGHT_RATIO = 1.6;
    public static final int VSYNC = 0;
    public static final int VBLANK = 1;
    public static final int WSYNC = 2;
    public static final int RSYNC = 3;
    public static final int NUSIZ0 = 4;
    public static final int NUSIZ1 = 5;
    public static final int COLUP0 = 6;
    public static final int COLUP1 = 7;
    public static final int COLUPF = 8;
    public static final int COLUBK = 9;
    public static final int CTRLPF = 10;
    public static final int REFP0 = 11;
    public static final int REFP1 = 12;
    public static final int PF0 = 13;
    public static final int PF1 = 14;
    public static final int PF2 = 15;
    public static final int RESP0 = 16;
    public static final int RESP1 = 17;
    public static final int RESM0 = 18;
    public static final int RESM1 = 19;
    public static final int RESBL = 20;
    public static final int AUDC0 = 21;
    public static final int AUDC1 = 22;
    public static final int AUDF0 = 23;
    public static final int AUDF1 = 24;
    public static final int AUDV0 = 25;
    public static final int AUDV1 = 26;
    public static final int GRP0 = 27;
    public static final int GRP1 = 28;
    public static final int ENAM0 = 29;
    public static final int ENAM1 = 30;
    public static final int ENABL = 31;
    public static final int HMP0 = 32;
    public static final int HMP1 = 33;
    public static final int HMM0 = 34;
    public static final int HMM1 = 35;
    public static final int HMBL = 36;
    public static final int VDELP0 = 37;
    public static final int VDELP1 = 38;
    public static final int VDELBL = 39;
    public static final int RESMP0 = 40;
    public static final int RESMP1 = 41;
    public static final int HMOVE = 42;
    public static final int HMCLR = 43;
    public static final int CXCLR = 44;
    public static final int CXM0P = 0;
    public static final int CXM1P = 1;
    public static final int CXP0FB = 2;
    public static final int CXP1FB = 3;
    public static final int CXM0FB = 4;
    public static final int CXM1FB = 5;
    public static final int CXBLPF = 6;
    public static final int CXPPMM = 7;
    public static final int INPT0 = 8;
    public static final int INPT1 = 9;
    public static final int INPT2 = 10;
    public static final int INPT3 = 11;
    public static final int INPT4 = 12;
    public static final int INPT5 = 13;
    public static final int SWCHA = 640;
    public static final int SWACNT = 641;
    public static final int SWCHB = 642;
    public static final int SWBCNT = 643;
    public static final int INTIM = 644;
    public static final int TIMINT = 645;
    public static final int TIM1T = 660;
    public static final int TIM8T = 661;
    public static final int TIM64T = 662;
    public static final int T1024T = 663;
    public static final int BIT_P0 = 1;
    public static final int BIT_M0 = 2;
    public static final int BIT_P1 = 4;
    public static final int BIT_M1 = 8;
    public static final int BIT_BL = 16;
    public static final int BIT_PF = 32;
    public static final int BIT_SCORE = 64;
    public static final int BIT_PRIORITY = 128;
    public static final int[] POKE_DELAY_TABLE;
    public static final boolean[] HMOVE_BLANK_ENABLE_CYCLES;
    public static final int[][] COMPLETE_MOTION_TABLE;
    public static final int[] PALETTE_NTSC;
    public static final int[] PALETTE_PAL;
    public static final int[] PALETTE_NTSC_11;
    public static final int[] PALETTE_PAL_11;
    public static final int[] PALETTE_NTSC_Z26;
    public static final int[] PALETTE_PAL_Z26;
    public static final boolean[][][] BALL_MASK_TABLE;
    public static final char[] COLLISION_TABLE;
    public static final int[] DISABLED_MASK_TABLE;
    public static final boolean[][][][] MISSILE_MASK_TABLE;
    public static final int[][][][] PLAYER_MASK_TABLE;
    public static final int[][][] PLAYER_POSITION_RESET_WHEN_TABLE;
    public static final int[] PLAYER_REFLECT_TABLE;
    public static final int[][] PLAYFIELD_TABLE;
    public static final int[][] PRIORITY_ENCODER;
    
    private static boolean bool(final int aValue) {
        return aValue != 0;
    }
    
    private static void computePriorityEncoder() {
        for (char x = '\0'; x < '\u0002'; ++x) {
            for (char enabled = '\0'; enabled < '\u0100'; ++enabled) {
                if ((enabled & '\u0080') != '\0') {
                    byte color = 0;
                    if ((enabled & '\f') != '\0') {
                        color = 3;
                    }
                    if ((enabled & '\u0003') != '\0') {
                        color = 2;
                    }
                    if ((enabled & '\u0010') != '\0') {
                        color = 1;
                    }
                    if ((enabled & ' ') != '\0') {
                        color = 1;
                    }
                    JSConstants.PRIORITY_ENCODER[x][enabled] = color;
                }
                else {
                    byte color = 0;
                    if ((enabled & '\u0010') != '\0') {
                        color = 1;
                    }
                    if ((enabled & ' ') != '\0') {
                        color = (byte)(((enabled & '@') != '\0') ? ((x == '\0') ? 2 : 3) : 1);
                    }
                    if ((enabled & '\f') != '\0') {
                        color = (byte)((color != 2) ? 3 : 2);
                    }
                    if ((enabled & '\u0003') != '\0') {
                        color = 2;
                    }
                    JSConstants.PRIORITY_ENCODER[x][enabled] = color;
                }
            }
        }
    }
    
    private static void computeBallMaskTable() {
        for (int size = 0; size < 4; ++size) {
            int x;
            for (x = 0, x = 0; x < 160; ++x) {
                JSConstants.BALL_MASK_TABLE[0][size][x] = false;
            }
            for (x = 0; x < 168; ++x) {
                if (x >= 0 && x < 1 << size) {
                    JSConstants.BALL_MASK_TABLE[0][size][x % 160] = true;
                }
            }
            for (x = 0; x < 160; ++x) {
                JSConstants.BALL_MASK_TABLE[0][size][x + 160] = JSConstants.BALL_MASK_TABLE[0][size][x];
            }
        }
        for (int align = 1; align < 4; ++align) {
            for (int size2 = 0; size2 < 4; ++size2) {
                for (int x2 = 0; x2 < 320; ++x2) {
                    JSConstants.BALL_MASK_TABLE[align][size2][x2] = JSConstants.BALL_MASK_TABLE[0][size2][(x2 + 320 - align) % 320];
                }
            }
        }
    }
    
    private static void computeCollisionTable() {
        for (char i = '\0'; i < '@'; ++i) {
            JSConstants.COLLISION_TABLE[i] = '\0';
            if (bool(i & '\u0002') && bool(i & '\u0004')) {
                final char[] collision_TABLE = JSConstants.COLLISION_TABLE;
                final char c = i;
                collision_TABLE[c] |= '\u0001';
            }
            if (bool(i & '\u0002') && bool(i & '\u0001')) {
                final char[] collision_TABLE2 = JSConstants.COLLISION_TABLE;
                final char c2 = i;
                collision_TABLE2[c2] |= '\u0002';
            }
            if (bool(i & '\b') && bool(i & '\u0001')) {
                final char[] collision_TABLE3 = JSConstants.COLLISION_TABLE;
                final char c3 = i;
                collision_TABLE3[c3] |= '\u0004';
            }
            if (bool(i & '\b') && bool(i & '\u0004')) {
                final char[] collision_TABLE4 = JSConstants.COLLISION_TABLE;
                final char c4 = i;
                collision_TABLE4[c4] |= '\b';
            }
            if (bool(i & '\u0001') && bool(i & ' ')) {
                final char[] collision_TABLE5 = JSConstants.COLLISION_TABLE;
                final char c5 = i;
                collision_TABLE5[c5] |= '\u0010';
            }
            if (bool(i & '\u0001') && bool(i & '\u0010')) {
                final char[] collision_TABLE6 = JSConstants.COLLISION_TABLE;
                final char c6 = i;
                collision_TABLE6[c6] |= ' ';
            }
            if (bool(i & '\u0004') && bool(i & ' ')) {
                final char[] collision_TABLE7 = JSConstants.COLLISION_TABLE;
                final char c7 = i;
                collision_TABLE7[c7] |= '@';
            }
            if (bool(i & '\u0004') && bool(i & '\u0010')) {
                final char[] collision_TABLE8 = JSConstants.COLLISION_TABLE;
                final char c8 = i;
                collision_TABLE8[c8] |= '\u0080';
            }
            if (bool(i & '\u0002') && bool(i & ' ')) {
                final char[] collision_TABLE9 = JSConstants.COLLISION_TABLE;
                final char c9 = i;
                collision_TABLE9[c9] |= '\u0100';
            }
            if (bool(i & '\u0002') && bool(i & '\u0010')) {
                final char[] collision_TABLE10 = JSConstants.COLLISION_TABLE;
                final char c10 = i;
                collision_TABLE10[c10] |= '\u0200';
            }
            if (bool(i & '\b') && bool(i & ' ')) {
                final char[] collision_TABLE11 = JSConstants.COLLISION_TABLE;
                final char c11 = i;
                collision_TABLE11[c11] |= '\u0400';
            }
            if (bool(i & '\b') && bool(i & '\u0010')) {
                final char[] collision_TABLE12 = JSConstants.COLLISION_TABLE;
                final char c12 = i;
                collision_TABLE12[c12] |= '\u0800';
            }
            if (bool(i & '\u0010') && bool(i & ' ')) {
                final char[] collision_TABLE13 = JSConstants.COLLISION_TABLE;
                final char c13 = i;
                collision_TABLE13[c13] |= '\u1000';
            }
            if (bool(i & '\u0001') && bool(i & '\u0004')) {
                final char[] collision_TABLE14 = JSConstants.COLLISION_TABLE;
                final char c14 = i;
                collision_TABLE14[c14] |= '\u2000';
            }
            if (bool(i & '\u0002') && bool(i & '\b')) {
                final char[] collision_TABLE15 = JSConstants.COLLISION_TABLE;
                final char c15 = i;
                collision_TABLE15[c15] |= '\u4000';
            }
        }
    }
    
    private static void computeMissileMaskTable() {
        for (int number = 0; number < 8; ++number) {
            for (int size = 0; size < 4; ++size) {
                for (int x = 0; x < 160; ++x) {
                    JSConstants.MISSILE_MASK_TABLE[0][number][size][x] = false;
                }
            }
        }
        for (int number = 0; number < 8; ++number) {
            for (int size = 0; size < 4; ++size) {
                for (int x = 0; x < 232; ++x) {
                    if (number == 0 || number == 5 || number == 7) {
                        if (x >= 0 && x < 1 << size) {
                            JSConstants.MISSILE_MASK_TABLE[0][number][size][x % 160] = true;
                        }
                    }
                    else if (number == 1) {
                        if (x >= 0 && x < 1 << size) {
                            JSConstants.MISSILE_MASK_TABLE[0][number][size][x % 160] = true;
                        }
                        else if (x - 16 >= 0 && x - 16 < 1 << size) {
                            JSConstants.MISSILE_MASK_TABLE[0][number][size][x % 160] = true;
                        }
                    }
                    else if (number == 2) {
                        if (x >= 0 && x < 1 << size) {
                            JSConstants.MISSILE_MASK_TABLE[0][number][size][x % 160] = true;
                        }
                        else if (x - 32 >= 0 && x - 32 < 1 << size) {
                            JSConstants.MISSILE_MASK_TABLE[0][number][size][x % 160] = true;
                        }
                    }
                    else if (number == 3) {
                        if (x >= 0 && x < 1 << size) {
                            JSConstants.MISSILE_MASK_TABLE[0][number][size][x % 160] = true;
                        }
                        else if (x - 16 >= 0 && x - 16 < 1 << size) {
                            JSConstants.MISSILE_MASK_TABLE[0][number][size][x % 160] = true;
                        }
                        else if (x - 32 >= 0 && x - 32 < 1 << size) {
                            JSConstants.MISSILE_MASK_TABLE[0][number][size][x % 160] = true;
                        }
                    }
                    else if (number == 4) {
                        if (x >= 0 && x < 1 << size) {
                            JSConstants.MISSILE_MASK_TABLE[0][number][size][x % 160] = true;
                        }
                        else if (x - 64 >= 0 && x - 64 < 1 << size) {
                            JSConstants.MISSILE_MASK_TABLE[0][number][size][x % 160] = true;
                        }
                    }
                    else if (number == 6) {
                        if (x >= 0 && x < 1 << size) {
                            JSConstants.MISSILE_MASK_TABLE[0][number][size][x % 160] = true;
                        }
                        else if (x - 32 >= 0 && x - 32 < 1 << size) {
                            JSConstants.MISSILE_MASK_TABLE[0][number][size][x % 160] = true;
                        }
                        else if (x - 64 >= 0 && x - 64 < 1 << size) {
                            JSConstants.MISSILE_MASK_TABLE[0][number][size][x % 160] = true;
                        }
                    }
                }
                for (int x = 0; x < 160; ++x) {
                    JSConstants.MISSILE_MASK_TABLE[0][number][size][x + 160] = JSConstants.MISSILE_MASK_TABLE[0][number][size][x];
                }
            }
        }
        for (int align = 1; align < 4; ++align) {
            for (int number = 0; number < 8; ++number) {
                for (int size = 0; size < 4; ++size) {
                    for (int x = 0; x < 320; ++x) {
                        JSConstants.MISSILE_MASK_TABLE[align][number][size][x] = JSConstants.MISSILE_MASK_TABLE[0][number][size][(x + 320 - align) % 320];
                    }
                }
            }
        }
    }
    
    private static void computePlayerMaskTable() {
        for (int enable = 0; enable < 2; ++enable) {
            for (int mode = 0; mode < 8; ++mode) {
                for (int x = 0; x < 160; ++x) {
                    JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x] = 0;
                }
            }
        }
        for (int enable = 0; enable < 2; ++enable) {
            for (int mode = 0; mode < 8; ++mode) {
                for (int x = 0; x < 232; ++x) {
                    if (mode == 0) {
                        if (enable == 0 && x >= 0 && x < 8) {
                            JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x % 160] = (char)(128 >> x);
                        }
                    }
                    else if (mode == 1) {
                        if (enable == 0 && x >= 0 && x < 8) {
                            JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x % 160] = 128 >> x;
                        }
                        else if (x - 16 >= 0 && x - 16 < 8) {
                            JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x % 160] = 128 >> x - 16;
                        }
                    }
                    else if (mode == 2) {
                        if (enable == 0 && x >= 0 && x < 8) {
                            JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x % 160] = 128 >> x;
                        }
                        else if (x - 32 >= 0 && x - 32 < 8) {
                            JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x % 160] = 128 >> x - 32;
                        }
                    }
                    else if (mode == 3) {
                        if (enable == 0 && x >= 0 && x < 8) {
                            JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x % 160] = 128 >> x;
                        }
                        else if (x - 16 >= 0 && x - 16 < 8) {
                            JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x % 160] = 128 >> x - 16;
                        }
                        else if (x - 32 >= 0 && x - 32 < 8) {
                            JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x % 160] = 128 >> x - 32;
                        }
                    }
                    else if (mode == 4) {
                        if (enable == 0 && x >= 0 && x < 8) {
                            JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x % 160] = 128 >> x;
                        }
                        else if (x - 64 >= 0 && x - 64 < 8) {
                            JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x % 160] = 128 >> x - 64;
                        }
                    }
                    else if (mode == 5) {
                        if (enable == 0 && x > 0 && x <= 16) {
                            JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x % 160] = 128 >> (x - 1) / 2;
                        }
                    }
                    else if (mode == 6) {
                        if (enable == 0 && x >= 0 && x < 8) {
                            JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x % 160] = 128 >> x;
                        }
                        else if (x - 32 >= 0 && x - 32 < 8) {
                            JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x % 160] = 128 >> x - 32;
                        }
                        else if (x - 64 >= 0 && x - 64 < 8) {
                            JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x % 160] = 128 >> x - 64;
                        }
                    }
                    else if (mode == 7 && enable == 0 && x > 0 && x <= 32) {
                        JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x % 160] = 128 >> (x - 1) / 4;
                    }
                }
                for (int x = 0; x < 160; ++x) {
                    JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x + 160] = JSConstants.PLAYER_MASK_TABLE[0][enable][mode][x];
                }
            }
        }
        for (int align = 1; align < 4; ++align) {
            for (int enable = 0; enable < 2; ++enable) {
                for (int mode = 0; mode < 8; ++mode) {
                    for (int x = 0; x < 320; ++x) {
                        JSConstants.PLAYER_MASK_TABLE[align][enable][mode][x] = JSConstants.PLAYER_MASK_TABLE[0][enable][mode][(x + 320 - align) % 320];
                    }
                }
            }
        }
    }
    
    private static void computePlayerPositionResetWhenTable() {
        for (int mode = 0; mode < 8; ++mode) {
            for (int oldx = 0; oldx < 160; ++oldx) {
                for (int newx = 0; newx < 160; ++newx) {
                    JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx] = 0;
                }
                for (int newx = 0; newx < 237; ++newx) {
                    if (mode == 0) {
                        if (newx >= oldx && newx < oldx + 4) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = -1;
                        }
                        if (newx >= oldx + 4 && newx < oldx + 4 + 8) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = 1;
                        }
                    }
                    else if (mode == 1) {
                        if (newx >= oldx && newx < oldx + 4) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = -1;
                        }
                        else if (newx >= oldx + 16 && newx < oldx + 16 + 4) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = -1;
                        }
                        if (newx >= oldx + 4 && newx < oldx + 4 + 8) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = 1;
                        }
                        else if (newx >= oldx + 16 + 4 && newx < oldx + 16 + 4 + 8) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = 1;
                        }
                    }
                    else if (mode == 2) {
                        if (newx >= oldx && newx < oldx + 4) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = -1;
                        }
                        else if (newx >= oldx + 32 && newx < oldx + 32 + 4) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = -1;
                        }
                        if (newx >= oldx + 4 && newx < oldx + 4 + 8) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = 1;
                        }
                        else if (newx >= oldx + 32 + 4 && newx < oldx + 32 + 4 + 8) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = 1;
                        }
                    }
                    else if (mode == 3) {
                        if (newx >= oldx && newx < oldx + 4) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = -1;
                        }
                        else if (newx >= oldx + 16 && newx < oldx + 16 + 4) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = -1;
                        }
                        else if (newx >= oldx + 32 && newx < oldx + 32 + 4) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = -1;
                        }
                        if (newx >= oldx + 4 && newx < oldx + 4 + 8) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = 1;
                        }
                        else if (newx >= oldx + 16 + 4 && newx < oldx + 16 + 4 + 8) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = 1;
                        }
                        else if (newx >= oldx + 32 + 4 && newx < oldx + 32 + 4 + 8) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = 1;
                        }
                    }
                    else if (mode == 4) {
                        if (newx >= oldx && newx < oldx + 4) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = -1;
                        }
                        else if (newx >= oldx + 64 && newx < oldx + 64 + 4) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = -1;
                        }
                        if (newx >= oldx + 4 && newx < oldx + 4 + 8) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = 1;
                        }
                        else if (newx >= oldx + 64 + 4 && newx < oldx + 64 + 4 + 8) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = 1;
                        }
                    }
                    else if (mode == 5) {
                        if (newx >= oldx && newx < oldx + 4) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = -1;
                        }
                        if (newx >= oldx + 4 && newx < oldx + 4 + 16) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = 1;
                        }
                    }
                    else if (mode == 6) {
                        if (newx >= oldx && newx < oldx + 4) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = -1;
                        }
                        else if (newx >= oldx + 32 && newx < oldx + 32 + 4) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = -1;
                        }
                        else if (newx >= oldx + 64 && newx < oldx + 64 + 4) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = -1;
                        }
                        if (newx >= oldx + 4 && newx < oldx + 4 + 8) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = 1;
                        }
                        else if (newx >= oldx + 32 + 4 && newx < oldx + 32 + 4 + 8) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = 1;
                        }
                        else if (newx >= oldx + 64 + 4 && newx < oldx + 64 + 4 + 8) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = 1;
                        }
                    }
                    else if (mode == 7) {
                        if (newx >= oldx && newx < oldx + 4) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = -1;
                        }
                        if (newx >= oldx + 4 && newx < oldx + 4 + 32) {
                            JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx % 160] = 1;
                        }
                    }
                }
                int s1 = 0;
                int s2 = 0;
                for (int newx = 0; newx < 160; ++newx) {
                    if (JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx] == -1) {
                        ++s1;
                    }
                    if (JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[mode][oldx][newx] == 1) {
                        ++s2;
                    }
                }
                assert s1 % 4 == 0 && s2 % 8 == 0;
            }
        }
    }
    
    private static void computePlayerReflectTable() {
        for (char i = '\0'; i < '\u0100'; ++i) {
            int r = 0;
            for (char t = '\u0001'; t <= '\u0080'; t *= '\u0002') {
                r = (r << 1 | ((bool(i & t) ? 1 : 0) & 0xFF));
            }
            JSConstants.PLAYER_REFLECT_TABLE[i] = r;
        }
    }
    
    private static void debugDumpArray(final int[][] aArray) {
        final int zYCount = aArray[0].length;
        for (int zXCount = aArray.length, i_x = 0; i_x < zXCount; ++i_x) {
            for (int i_y = 0; i_y < zYCount; ++i_y) {
                System.out.println("[" + i_x + "][" + i_y + "] = " + Integer.toBinaryString(aArray[i_x][i_y]));
            }
        }
    }
    
    private static void computePlayfieldMaskTable() {
        for (int x = 0; x < 160; ++x) {
            if (x < 16) {
                JSConstants.PLAYFIELD_TABLE[0][x] = 1 << x / 4;
            }
            else if (x < 48) {
                JSConstants.PLAYFIELD_TABLE[0][x] = 2048 >> (x - 16) / 4;
            }
            else if (x < 80) {
                JSConstants.PLAYFIELD_TABLE[0][x] = 4096 << (x - 48) / 4;
            }
            else if (x < 96) {
                JSConstants.PLAYFIELD_TABLE[0][x] = 1 << (x - 80) / 4;
            }
            else if (x < 128) {
                JSConstants.PLAYFIELD_TABLE[0][x] = 2048 >> (x - 96) / 4;
            }
            else if (x < 160) {
                JSConstants.PLAYFIELD_TABLE[0][x] = 4096 << (x - 128) / 4;
            }
        }
        for (int x = 0; x < 160; ++x) {
            if (x < 16) {
                JSConstants.PLAYFIELD_TABLE[1][x] = 1 << x / 4;
            }
            else if (x < 48) {
                JSConstants.PLAYFIELD_TABLE[1][x] = 2048 >> (x - 16) / 4;
            }
            else if (x < 80) {
                JSConstants.PLAYFIELD_TABLE[1][x] = 4096 << (x - 48) / 4;
            }
            else if (x < 112) {
                JSConstants.PLAYFIELD_TABLE[1][x] = 524288 >> (x - 80) / 4;
            }
            else if (x < 144) {
                JSConstants.PLAYFIELD_TABLE[1][x] = 16 << (x - 112) / 4;
            }
            else if (x < 160) {
                JSConstants.PLAYFIELD_TABLE[1][x] = 8 >> (x - 144) / 4;
            }
        }
    }
    
    static {
        POKE_DELAY_TABLE = new int[] { 0, 1, 0, 0, 8, 8, 0, 0, 0, 0, 0, 1, 1, -1, -1, -1, 0, 0, 8, 8, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        HMOVE_BLANK_ENABLE_CYCLES = new boolean[] { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true };
        COMPLETE_MOTION_TABLE = new int[][] { { 0, -1, -2, -3, -4, -5, -6, -7, 8, 7, 6, 5, 4, 3, 2, 1 }, { 0, -1, -2, -3, -4, -5, -6, -7, 8, 7, 6, 5, 4, 3, 2, 1 }, { 0, -1, -2, -3, -4, -5, -6, -7, 8, 7, 6, 5, 4, 3, 2, 1 }, { 0, -1, -2, -3, -4, -5, -6, -7, 8, 7, 6, 5, 4, 3, 2, 1 }, { 0, -1, -2, -3, -4, -5, -6, -6, 8, 7, 6, 5, 4, 3, 2, 1 }, { 0, -1, -2, -3, -4, -5, -5, -5, 8, 7, 6, 5, 4, 3, 2, 1 }, { 0, -1, -2, -3, -4, -5, -5, -5, 8, 7, 6, 5, 4, 3, 2, 1 }, { 0, -1, -2, -3, -4, -4, -4, -4, 8, 7, 6, 5, 4, 3, 2, 1 }, { 0, -1, -2, -3, -3, -3, -3, -3, 8, 7, 6, 5, 4, 3, 2, 1 }, { 0, -1, -2, -2, -2, -2, -2, -2, 8, 7, 6, 5, 4, 3, 2, 1 }, { 0, -1, -2, -2, -2, -2, -2, -2, 8, 7, 6, 5, 4, 3, 2, 1 }, { 0, -1, -1, -1, -1, -1, -1, -1, 8, 7, 6, 5, 4, 3, 2, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 8, 7, 6, 5, 4, 3, 2, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 8, 7, 6, 5, 4, 3, 2, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 8, 7, 6, 5, 4, 3, 2, 1 }, { 2, 2, 2, 2, 2, 2, 2, 2, 8, 7, 6, 5, 4, 3, 2, 2 }, { 3, 3, 3, 3, 3, 3, 3, 3, 8, 7, 6, 5, 4, 3, 3, 3 }, { 4, 4, 4, 4, 4, 4, 4, 4, 8, 7, 6, 5, 4, 4, 4, 4 }, { 4, 4, 4, 4, 4, 4, 4, 4, 8, 7, 6, 5, 4, 4, 4, 4 }, { 5, 5, 5, 5, 5, 5, 5, 5, 8, 7, 6, 5, 5, 5, 5, 5 }, { 6, 6, 6, 6, 6, 6, 6, 6, 8, 7, 6, 6, 6, 6, 6, 6 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, -1, -2, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, -1, -2, -3, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, -1, -2, -3, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, -1, -2, -3, -4, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, -1, -2, -3, -4, -5, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, -1, -2, -3, -4, -5, -6, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, -1, -2, -3, -4, -5, -6, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, -1, -2, -3, -4, -5, -6, -7, 0, 0, 0, 0, 0, 0, 0, 0 }, { -1, -2, -3, -4, -5, -6, -7, -8, 0, 0, 0, 0, 0, 0, 0, 0 }, { -2, -3, -4, -5, -6, -7, -8, -9, 0, 0, 0, 0, 0, 0, 0, -1 }, { -2, -3, -4, -5, -6, -7, -8, -9, 0, 0, 0, 0, 0, 0, 0, -1 }, { -3, -4, -5, -6, -7, -8, -9, -10, 0, 0, 0, 0, 0, 0, -1, -2 }, { -4, -5, -6, -7, -8, -9, -10, -11, 0, 0, 0, 0, 0, -1, -2, -3 }, { -5, -6, -7, -8, -9, -10, -11, -12, 0, 0, 0, 0, -1, -2, -3, -4 }, { -5, -6, -7, -8, -9, -10, -11, -12, 0, 0, 0, 0, -1, -2, -3, -4 }, { -6, -7, -8, -9, -10, -11, -12, -13, 0, 0, 0, -1, -2, -3, -4, -5 }, { -7, -8, -9, -10, -11, -12, -13, -14, 0, 0, -1, -2, -3, -4, -5, -6 }, { -8, -9, -10, -11, -12, -13, -14, -15, 0, -1, -2, -3, -4, -5, -6, -7 }, { -8, -9, -10, -11, -12, -13, -14, -15, 0, -1, -2, -3, -4, -5, -6, -7 }, { 0, -1, -2, -3, -4, -5, -6, -7, 8, 7, 6, 5, 4, 3, 2, 1 } };
        PALETTE_NTSC = new int[] { 0, 0, 4868682, 0, 7303023, 0, 9342606, 0, 11184810, 0, 12632256, 0, 14079702, 0, 15527148, 0, 4737024, 0, 6908175, 0, 8816157, 0, 10658346, 0, 12303157, 0, 13816384, 0, 15263818, 0, 16579668, 0, 8137728, 0, 9455633, 0, 10641953, 0, 11827760, 0, 12816445, 0, 13804618, 0, 14661461, 0, 15517792, 0, 9444352, 0, 10696981, 0, 11883304, 0, 13003834, 0, 13992522, 0, 14915417, 0, 15772263, 0, 16563316, 0, 9699328, 0, 10951194, 0, 12071474, 0, 13125704, 0, 14048348, 0, 14970735, 0, 15761536, 0, 16552080, 0, 8650852, 0, 9902458, 0, 11022479, 0, 12076706, 0, 12999091, 0, 13921475, 0, 14712018, 0, 15502560, 0, 5243012, 0, 6822298, 0, 8204461, 0, 9586368, 0, 10770896, 0, 11889888, 0, 12942574, 0, 13929724, 0, 1310864, 0, 3349155, 0, 5124789, 0, 6834374, 0, 8346837, 0, 9793507, 0, 11108592, 0, 12357884, 0, 148, 0, 1579687, 0, 2962104, 0, 4344008, 0, 5528790, 0, 6647780, 0, 7700720, 0, 8687868, 0, 7304, 0, 1588125, 0, 2971568, 0, 4354754, 0, 5540562, 0, 6660321, 0, 7714287, 0, 8702204, 0, 12388, 0, 1593472, 0, 2977176, 0, 4360368, 0, 5546181, 0, 6666201, 0, 7720171, 0, 8708348, 0, 16432, 0, 1598030, 0, 2982249, 0, 4365954, 0, 5552281, 0, 6672814, 0, 7727042, 0, 8715476, 0, 17408, 0, 1730074, 0, 3310642, 0, 4759624, 0, 6077020, 0, 7328367, 0, 8448128, 0, 9501840, 0, 1326080, 0, 3497752, 0, 5406253, 0, 7248962, 0, 8894292, 0, 10408037, 0, 11855733, 0, 13171844, 0, 3160064, 0, 5265686, 0, 7173675, 0, 8950334, 0, 10529615, 0, 12042847, 0, 13424750, 0, 14740604, 0, 4729856, 0, 6901012, 0, 8808998, 0, 10651192, 0, 12296007, 0, 13809238, 0, 15256675, 0, 16572528, 0 };
        PALETTE_PAL = new int[] { 0, 0, 2829099, 0, 5395026, 0, 7763574, 0, 9934743, 0, 11974326, 0, 13816530, 0, 15527148, 0, 0, 0, 2829099, 0, 5395026, 0, 7763574, 0, 9934743, 0, 11974326, 0, 13816530, 0, 15527148, 0, 8411136, 0, 9859354, 0, 11241266, 0, 12491848, 0, 13610844, 0, 14663791, 0, 15651200, 0, 16572560, 0, 4480000, 0, 6191386, 0, 7770930, 0, 9219144, 0, 10535516, 0, 11786095, 0, 12905088, 0, 13958288, 0, 7353344, 0, 8999194, 0, 10513202, 0, 11961416, 0, 13212252, 0, 14462831, 0, 15516288, 0, 16569488, 0, 25620, 0, 1736757, 0, 3315794, 0, 4763758, 0, 6079879, 0, 7330206, 0, 8448948, 0, 9501896, 0, 7340052, 0, 8985141, 0, 10498642, 0, 11946094, 0, 13196423, 0, 14446494, 0, 15499444, 0, 16552136, 0, 23644, 0, 1734262, 0, 3313294, 0, 4760740, 0, 6076600, 0, 7326667, 0, 8445148, 0, 9497836, 0, 7340124, 0, 8657524, 0, 9843337, 0, 11028638, 0, 12016816, 0, 13004737, 0, 13861073, 0, 14717152, 0, 15472, 0, 1661577, 0, 3110304, 0, 4492982, 0, 5744073, 0, 6863580, 0, 7982828, 0, 8970492, 0, 5767280, 0, 7215753, 0, 8598176, 0, 9849014, 0, 10968265, 0, 12021724, 0, 13009132, 0, 13930748, 0, 8304, 0, 1654665, 0, 3103392, 0, 4486326, 0, 5737417, 0, 6857180, 0, 7976428, 0, 8964348, 0, 3408000, 0, 4856470, 0, 6238891, 0, 7489726, 0, 8608975, 0, 9662431, 0, 10649838, 0, 11571452, 0, 136, 0, 1710749, 0, 3289776, 0, 4737218, 0, 6053074, 0, 7303137, 0, 8421615, 0, 9474300, 0, 0, 0, 2829099, 0, 5395026, 0, 7763574, 0, 9934743, 0, 11974326, 0, 13816530, 0, 15527148, 0, 0, 0, 2829099, 0, 5395026, 0, 7763574, 0, 9934743, 0, 11974326, 0, 13816530, 0, 15527148, 0 };
        PALETTE_NTSC_11 = new int[] { 0, 0, 3750201, 0, 7960953, 0, 11250603, 0, 13487565, 0, 15132390, 0, 15921906, 0, 16777215, 0, 3741441, 0, 8597512, 0, 13131556, 0, 16748829, 0, 16762141, 0, 16767052, 0, 16774230, 0, 16777112, 0, 4528388, 0, 10429470, 0, 13127970, 0, 16744734, 0, 16750636, 0, 16762181, 0, 16762477, 0, 16770209, 0, 4855556, 0, 11672855, 0, 14624028, 0, 16405077, 0, 16740462, 0, 16748431, 0, 16755629, 0, 16762830, 0, 329064, 0, 7414386, 0, 10826406, 0, 13450959, 0, 15356395, 0, 16674303, 0, 16746491, 0, 16753919, 0, 2622585, 0, 5836688, 0, 8927658, 0, 12602076, 0, 14704383, 0, 15891711, 0, 16750847, 0, 16690175, 0, 3475594, 0, 5246160, 0, 7947728, 0, 10637785, 0, 12476671, 0, 13400063, 0, 14127359, 0, 14658303, 0, 335489, 0, 536522, 0, 4476126, 0, 5925119, 0, 7439359, 0, 9478399, 0, 10466047, 0, 12635135, 0, 787595, 0, 3681717, 0, 5787610, 0, 7038207, 0, 9078015, 0, 10066175, 0, 11644671, 0, 12632831, 0, 1911130, 0, 1919122, 0, 1864134, 0, 4758489, 0, 5617407, 0, 9230591, 0, 10215423, 0, 12839423, 0, 3097346, 0, 4481283, 0, 4101153, 0, 5745467, 0, 6410352, 0, 7533956, 0, 8912791, 0, 11403190, 0, 672008, 0, 1075213, 0, 1479186, 0, 1882391, 0, 2218267, 0, 7270464, 0, 8650587, 0, 11730842, 0, 278795, 0, 419345, 0, 559127, 0, 765725, 0, 8837410, 0, 10090791, 0, 12058459, 0, 14483329, 0, 144655, 0, 805404, 0, 5207072, 0, 6591016, 0, 10596404, 0, 11719233, 0, 14082377, 0, 15925075, 0, 2502657, 0, 2310149, 0, 8415537, 0, 11508026, 0, 14005571, 0, 14797624, 0, 14935348, 0, 16514941, 0, 4200962, 0, 7349256, 0, 11227423, 0, 12547888, 0, 14783300, 0, 16362840, 0, 16761184, 0, 16763779, 0 };
        PALETTE_PAL_11 = new int[] { 0, 0, 2368548, 0, 4737096, 0, 7171437, 0, 9539985, 0, 11974326, 0, 14342874, 0, 16777215, 0, 0, 0, 2368548, 0, 4737096, 0, 7171437, 0, 9539985, 0, 11974326, 0, 14342874, 0, 16777215, 0, 4863744, 0, 7362579, 0, 9204522, 0, 10915142, 0, 12494695, 0, 13943179, 0, 15391923, 0, 16774878, 0, 2640384, 0, 4485135, 0, 6065185, 0, 7644728, 0, 9223761, 0, 10933358, 0, 12642958, 0, 14417840, 0, 4854528, 0, 7350287, 0, 9190689, 0, 10900536, 0, 12479825, 0, 13928558, 0, 15377806, 0, 16762032, 0, 18978, 0, 1011771, 0, 2198610, 0, 3712618, 0, 5357187, 0, 7263389, 0, 9366200, 0, 11599828, 0, 4849704, 0, 7343940, 0, 9183580, 0, 10893428, 0, 12472716, 0, 13921958, 0, 15371968, 0, 16756955, 0, 16458, 0, 1008496, 0, 2195084, 0, 3708838, 0, 5353406, 0, 7260116, 0, 9363178, 0, 11597055, 0, 4390956, 0, 6623051, 0, 8266085, 0, 9779328, 0, 10899866, 0, 12545719, 0, 13864659, 0, 15053041, 0, 7498, 0, 997488, 0, 2184076, 0, 3698342, 0, 5344702, 0, 7252180, 0, 9357546, 0, 11594239, 0, 3604554, 0, 5705584, 0, 7348620, 0, 8992934, 0, 10572222, 0, 12218068, 0, 13799146, 0, 15380735, 0, 6218, 0, 994928, 0, 2180236, 0, 3693478, 0, 5338302, 0, 7245780, 0, 9350122, 0, 11586047, 0, 1245258, 0, 2625392, 0, 4006284, 0, 5519526, 0, 7164350, 0, 8941268, 0, 10850026, 0, 12890367, 0, 330, 0, 987504, 0, 2172044, 0, 3685030, 0, 5329854, 0, 7237844, 0, 9342954, 0, 11580159, 0, 0, 0, 2368548, 0, 4737096, 0, 7171437, 0, 9539985, 0, 11974326, 0, 14342874, 0, 16777215, 0, 0, 0, 2368548, 0, 4737096, 0, 7171437, 0, 9539985, 0, 11974326, 0, 14342874, 0, 16777215, 0 };
        PALETTE_NTSC_Z26 = new int[] { 0, 0, 5263440, 0, 6579300, 0, 7895160, 0, 9211020, 0, 10526880, 0, 11842740, 0, 13158600, 0, 4477952, 0, 5793792, 0, 7109632, 0, 8425472, 0, 9741332, 0, 11057192, 0, 12373052, 0, 13688912, 0, 6764800, 0, 8080640, 0, 9396480, 0, 10712339, 0, 12028199, 0, 13344059, 0, 14659919, 0, 15975779, 0, 8070404, 0, 9386264, 0, 10702124, 0, 12017984, 0, 13333844, 0, 14649704, 0, 15965564, 0, 16757136, 0, 8196652, 0, 9512512, 0, 10828372, 0, 12144232, 0, 13460092, 0, 14775952, 0, 16091812, 0, 16752312, 0, 7538801, 0, 8854661, 0, 10170521, 0, 11486381, 0, 12802241, 0, 14118101, 0, 15433961, 0, 16749821, 0, 6097810, 0, 7413670, 0, 8729530, 0, 10045390, 0, 11361250, 0, 12677110, 0, 13992959, 0, 15308799, 0, 4199833, 0, 5515693, 0, 6831553, 0, 8147413, 0, 9463273, 0, 10779133, 0, 12094975, 0, 13410815, 0, 2434451, 0, 3750311, 0, 5066171, 0, 6382031, 0, 7697891, 0, 9013751, 0, 10329599, 0, 11645439, 0, 996480, 0, 2312340, 0, 3628200, 0, 4944060, 0, 6259920, 0, 7575780, 0, 8891640, 0, 10207487, 0, 279130, 0, 1594990, 0, 2910850, 0, 4226710, 0, 5542570, 0, 6858430, 0, 8174290, 0, 9490150, 0, 282416, 0, 1598276, 0, 2914136, 0, 4229996, 0, 5545856, 0, 6861716, 0, 8177576, 0, 9493436, 0, 1004810, 0, 2320670, 0, 3636530, 0, 4952390, 0, 6268250, 0, 7584110, 0, 8899970, 0, 10215830, 0, 2052352, 0, 3368197, 0, 4684057, 0, 5999917, 0, 7315777, 0, 8631637, 0, 9947497, 0, 11263357, 0, 3425792, 0, 4741632, 0, 6057492, 0, 7373352, 0, 8689212, 0, 10005072, 0, 11320932, 0, 12636792, 0, 4603392, 0, 5919237, 0, 7235097, 0, 8550957, 0, 9866817, 0, 11182677, 0, 12498537, 0, 13814397, 0 };
        PALETTE_PAL_Z26 = new int[] { 0, 0, 5000268, 0, 6316128, 0, 7631988, 0, 8947848, 0, 10263708, 0, 11579568, 0, 12895428, 0, 0, 0, 5000268, 0, 6316128, 0, 7631988, 0, 8947848, 0, 10263708, 0, 11579568, 0, 12895428, 0, 5454336, 0, 6770176, 0, 8086019, 0, 9401879, 0, 10717739, 0, 12033599, 0, 13349459, 0, 14665319, 0, 1792000, 0, 3107840, 0, 4423681, 0, 5739541, 0, 7055401, 0, 8371261, 0, 9687121, 0, 11002981, 0, 6957312, 0, 8273170, 0, 9589030, 0, 10904890, 0, 12220750, 0, 13536610, 0, 14852470, 0, 16168330, 0, 482048, 0, 1797905, 0, 3113765, 0, 4429625, 0, 5745485, 0, 7061345, 0, 8377205, 0, 9693065, 0, 7609135, 0, 8924995, 0, 10240855, 0, 11556715, 0, 12872575, 0, 14188435, 0, 15504295, 0, 16754619, 0, 22318, 0, 1076034, 0, 2391894, 0, 3707754, 0, 5023614, 0, 6339474, 0, 7655334, 0, 8971194, 0, 7149151, 0, 8465011, 0, 9780871, 0, 11096731, 0, 12412591, 0, 13728451, 0, 15044311, 0, 16360171, 0, 85086, 0, 1400946, 0, 2716806, 0, 4032666, 0, 5348526, 0, 6664386, 0, 7980246, 0, 9296106, 0, 6231432, 0, 7547292, 0, 8863152, 0, 10179012, 0, 11494872, 0, 12810732, 0, 14126591, 0, 15442431, 0, 1194887, 0, 2510747, 0, 3826607, 0, 5142467, 0, 6458327, 0, 7774187, 0, 9090047, 0, 10405887, 0, 4529821, 0, 5845681, 0, 7161541, 0, 8477401, 0, 9793261, 0, 11109119, 0, 12424959, 0, 13740799, 0, 2763678, 0, 4079538, 0, 5395398, 0, 6711258, 0, 8027118, 0, 9342975, 0, 10658815, 0, 11974655, 0, 0, 0, 5000268, 0, 6316128, 0, 7631988, 0, 8947848, 0, 10263708, 0, 11579568, 0, 12895428, 0, 0, 0, 5000268, 0, 6316128, 0, 7631988, 0, 8947848, 0, 10263708, 0, 11579568, 0, 12895428, 0 };
        BALL_MASK_TABLE = new boolean[4][4][320];
        COLLISION_TABLE = new char[64];
        DISABLED_MASK_TABLE = new int[640];
        MISSILE_MASK_TABLE = new boolean[4][8][4][320];
        PLAYER_MASK_TABLE = new int[4][2][8][320];
        PLAYER_POSITION_RESET_WHEN_TABLE = new int[8][160][160];
        PLAYER_REFLECT_TABLE = new int[256];
        PLAYFIELD_TABLE = new int[2][160];
        PRIORITY_ENCODER = new int[2][256];
        for (int i = 0; i < 640; ++i) {
            JSConstants.DISABLED_MASK_TABLE[i] = 0;
        }
        computeBallMaskTable();
        computeCollisionTable();
        computeMissileMaskTable();
        computePlayerMaskTable();
        computePlayerPositionResetWhenTable();
        computePlayerReflectTable();
        computePlayfieldMaskTable();
        computePriorityEncoder();
    }
    
    public enum Jack
    {
        LEFT, 
        RIGHT;
    }
    
    public enum DigitalPin
    {
        One, 
        Two, 
        Three, 
        Four, 
        Six;
    }
    
    public enum AnalogPin
    {
        Five, 
        Nine;
    }
    
    public enum ConsoleSwitch
    {
        SWITCH_RESET(0, 1), 
        SWITCH_SELECT(1, 2), 
        SWITCH_BW(2, 8), 
        SWITCH_DIFFICULTY_P0(3, 64), 
        SWITCH_DIFFICULTY_P1(4, 128);
        
        private final int myIndex;
        private final int myBitMask;
        
        private ConsoleSwitch(final int aIndex, final int aBitMask) {
            this.myIndex = aIndex;
            this.myBitMask = aBitMask;
        }
        
        public int getIndex() {
            return this.myIndex;
        }
        
        public int getBitMask() {
            return this.myBitMask;
        }
    }
    
    public enum DisplayFormat
    {
        NTSC(60, JSConstants.PALETTE_NTSC), 
        PAL(50, JSConstants.PALETTE_PAL), 
        PAL60(60, JSConstants.PALETTE_PAL);
        
        private final int myDisplayRate;
        private final int[] myDisplayPalette;
        
        private DisplayFormat(final int aDisplayRate, final int[] aDisplayPalette) {
            this.myDisplayRate = aDisplayRate;
            this.myDisplayPalette = aDisplayPalette;
        }
        
        public int getDisplayRate() {
            return this.myDisplayRate;
        }
        
        public int[] getDisplayPalette() {
            return this.myDisplayPalette;
        }
    }
}
