// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

public class MOS6510Ops
{
    public static final int BRK = 0;
    public static final int ORA = 1;
    public static final int TRP = 2;
    public static final int SLO = 3;
    public static final int NOP = 4;
    public static final int ASL = 5;
    public static final int PHP = 6;
    public static final int ANC = 7;
    public static final int BPL = 8;
    public static final int CLC = 9;
    public static final int JSR = 10;
    public static final int AND = 11;
    public static final int RLA = 12;
    public static final int BIT = 13;
    public static final int ROL = 14;
    public static final int PLP = 15;
    public static final int BMI = 16;
    public static final int SEC = 17;
    public static final int RTI = 18;
    public static final int EOR = 19;
    public static final int SRE = 20;
    public static final int LSR = 21;
    public static final int PHA = 22;
    public static final int ASR = 23;
    public static final int JMP = 24;
    public static final int BVC = 25;
    public static final int CLI = 26;
    public static final int RTS = 27;
    public static final int ADC = 28;
    public static final int RRA = 29;
    public static final int ROR = 30;
    public static final int PLA = 31;
    public static final int ARR = 32;
    public static final int BVS = 33;
    public static final int SEI = 34;
    public static final int SAX = 35;
    public static final int STA = 36;
    public static final int STY = 37;
    public static final int STX = 38;
    public static final int DEY = 39;
    public static final int TXA = 40;
    public static final int ANE = 41;
    public static final int BCC = 42;
    public static final int SHA = 43;
    public static final int TYA = 44;
    public static final int TXS = 45;
    public static final int SHS = 46;
    public static final int SHY = 47;
    public static final int SHX = 48;
    public static final int LDY = 49;
    public static final int LDA = 50;
    public static final int LDX = 51;
    public static final int LAX = 52;
    public static final int TAX = 53;
    public static final int LXA = 54;
    public static final int TAY = 55;
    public static final int BCS = 56;
    public static final int CLV = 57;
    public static final int TSX = 58;
    public static final int LAS = 59;
    public static final int CPY = 60;
    public static final int CMP = 61;
    public static final int DCP = 62;
    public static final int DEC = 63;
    public static final int INY = 64;
    public static final int DEX = 65;
    public static final int SBX = 66;
    public static final int BNE = 67;
    public static final int CLD = 68;
    public static final int CPX = 69;
    public static final int SBC = 70;
    public static final int ISB = 71;
    public static final int INC = 72;
    public static final int INX = 73;
    public static final int BEQ = 74;
    public static final int SED = 75;
    public static final int OP_LOAD_FILE = 100;
    public static final int LOAD_FILE = 256;
    public static final int ADDRESSING_MASK = 3840;
    public static final int ADDRESSING_SHIFT = 8;
    public static final int OP_MASK = 255;
    public static final int IMMEDIATE = 256;
    public static final int ZERO = 512;
    public static final int ABSOLUTE = 768;
    public static final int ZERO_X = 1024;
    public static final int ZERO_Y = 1280;
    public static final int ABSOLUTE_X = 1536;
    public static final int ABSOLUTE_Y = 1792;
    public static final int RELATIVE = 2048;
    public static final int INDIRECT_X = 2304;
    public static final int INDIRECT_Y = 2560;
    public static final int ACCUMULATOR = 2816;
    public static final int INDIRECT = 3072;
    public static final int MODE_MASK = 61440;
    public static final int MODE_SHIFT = 12;
    public static final int READ = 4096;
    public static final int WRITE = 8192;
    public static final int RMW = 12288;
    public static final String[] INS_STR;
    public static final String[] ADR_STR_PRE;
    public static final String[] ADR_STR_POST;
    public static final int[] ADR_LEN;
    public static final int[] INSTRUCTION_SET;
    public static final int[] READ_INS;
    public static final int[] WRITE_INS;
    public static final int[] RMW_INS;
    
    public static void init() {
        for (int i = 0; i < MOS6510Ops.INSTRUCTION_SET.length; ++i) {
            final int n = i & 0x1F;
            final int n2 = i >> 5;
            final int n3 = MOS6510Ops.INSTRUCTION_SET[i];
            final int[] instruction_SET = MOS6510Ops.INSTRUCTION_SET;
            final int n4 = i;
            instruction_SET[n4] |= getAdrMode(n2, n);
            final int[] instruction_SET2 = MOS6510Ops.INSTRUCTION_SET;
            final int n5 = i;
            instruction_SET2[n5] |= getOpMode(n3);
        }
    }
    
    public static String toString(final int n) {
        final int n2 = MOS6510Ops.INSTRUCTION_SET[n];
        final int n3 = (n2 & 0xF00) >> 8;
        return MOS6510Ops.INS_STR[n2 & 0xFF] + MOS6510Ops.ADR_STR_PRE[n3] + MOS6510Ops.ADR_STR_POST[n3];
    }
    
    public static String toString(final int n, final boolean b) {
        final int n2 = MOS6510Ops.INSTRUCTION_SET[n];
        final int n3 = (n2 & 0xF00) >> 8;
        final int n4 = n2 & 0xFF;
        if (!b) {
            return MOS6510Ops.INS_STR[n4] + MOS6510Ops.ADR_STR_PRE[n3] + MOS6510Ops.ADR_STR_POST[n3];
        }
        final String string = MOS6510Ops.INS_STR[n4] + MOS6510Ops.ADR_STR_PRE[n3] + MOS6510Ops.ADR_STR_POST[n3];
        final int n5 = n2 & 0xF000;
        if (n5 == 4096) {
            return string + " read";
        }
        if (n5 == 8192) {
            return string + " write";
        }
        if (n5 == 12288) {
            return string + " rmw";
        }
        return string;
    }
    
    private static int getAdrMode(final int n, final int n2) {
        switch (n2) {
            case 0:
            case 2: {
                if (n > 4) {
                    return 256;
                }
                return 0;
            }
            case 1:
            case 3: {
                return 2304;
            }
            case 4:
            case 5:
            case 6:
            case 7: {
                return 512;
            }
            case 9:
            case 11: {
                return 256;
            }
            case 10: {
                if (n < 4) {
                    return 2816;
                }
                return 0;
            }
            case 12:
            case 13:
            case 14:
            case 15: {
                if (n2 == 12 && n == 3) {
                    return 3072;
                }
                return 768;
            }
            case 16: {
                return 2048;
            }
            case 17:
            case 19: {
                return 2560;
            }
            case 20:
            case 21: {
                return 1024;
            }
            case 22:
            case 23: {
                if (n == 4 || n == 5) {
                    return 1280;
                }
                return 1024;
            }
            case 25:
            case 27: {
                return 1792;
            }
            case 28:
            case 29: {
                return 1536;
            }
            case 30:
            case 31: {
                if (n == 4 || n == 5) {
                    return 1792;
                }
                return 1536;
            }
            default: {
                return 0;
            }
        }
    }
    
    private static int getOpMode(final int n) {
        for (int i = 0; i < MOS6510Ops.READ_INS.length; ++i) {
            if (MOS6510Ops.READ_INS[i] == n) {
                return 4096;
            }
        }
        for (int j = 0; j < MOS6510Ops.WRITE_INS.length; ++j) {
            if (MOS6510Ops.WRITE_INS[j] == n) {
                return 8192;
            }
        }
        for (int k = 0; k < MOS6510Ops.RMW_INS.length; ++k) {
            if (MOS6510Ops.RMW_INS[k] == n) {
                return 12288;
            }
        }
        return 0;
    }
    
    public static void main(final String[] array) {
        init();
        for (int i = 0; i < 256; ++i) {
            System.out.println(Hex.hex2(i) + " => " + toString(i, true) + " IS:" + Hex.hex2(MOS6510Ops.INSTRUCTION_SET[i]));
        }
    }
    
    static {
        INS_STR = new String[] { "BRK", "ORA", "TRP", "SLO", "NOP", "ASL", "PHP", "ANC", "BPL", "CLC", "JSR", "AND", "RLA", "BIT", "ROL", "PLP", "BMI", "SEC", "RTI", "EOR", "SRE", "LSR", "PHA", "ASR", "JMP", "BVC", "CLI", "RTS", "ADC", "RRA", "ROR", "PLA", "ARR", "BVS", "SEI", "SAX", "STA", "STY", "STX", "DEY", "TXA", "ANE", "BCC", "SHA", "TYA", "TXS", "SHS", "SHY", "SHX", "LDY", "LDA", "LDX", "LAX", "TAX", "LXA", "TAY", "BCS", "CLV", "TSX", "LAS", "CPY", "CMP", "DCP", "DEC", "INY", "DEX", "SBX", "BNE", "CLD", "CPX", "SBC", "ISB", "INC", "INX", "BEQ", "SED" };
        ADR_STR_PRE = new String[] { " ", " #", " Z ", " ", " Z ", " Z ", " ", " ", " ", " (", " (", " ACC", " (" };
        ADR_STR_POST = new String[] { "", "", "", "", ",X", ",Y", ",X", ",Y", "", ",X)", "),Y", "", ")" };
        ADR_LEN = new int[] { 1, 2, 2, 3, 2, 2, 3, 3, 2, 2, 2, 1, 3 };
        INSTRUCTION_SET = new int[] { 0, 1, 2, 3, 4, 1, 5, 3, 6, 1, 5, 7, 4, 1, 5, 3, 8, 1, 2, 3, 4, 1, 5, 3, 9, 1, 4, 3, 4, 1, 5, 3, 10, 11, 2, 12, 13, 11, 14, 12, 15, 11, 14, 7, 13, 11, 14, 12, 16, 11, 2, 12, 4, 11, 14, 12, 17, 11, 4, 12, 4, 11, 14, 12, 18, 19, 2, 20, 4, 19, 21, 20, 22, 19, 21, 23, 24, 19, 21, 20, 25, 19, 2, 20, 4, 19, 21, 20, 26, 19, 4, 20, 4, 19, 21, 20, 27, 28, 2, 29, 4, 28, 30, 29, 31, 28, 30, 32, 24, 28, 30, 29, 33, 28, 2, 29, 4, 28, 30, 29, 34, 28, 4, 29, 4, 28, 30, 29, 4, 36, 4, 35, 37, 36, 38, 35, 39, 4, 40, 41, 37, 36, 38, 35, 42, 36, 2, 43, 37, 36, 38, 35, 44, 36, 45, 46, 47, 36, 48, 43, 49, 50, 51, 52, 49, 50, 51, 52, 55, 50, 53, 54, 49, 50, 51, 52, 56, 50, 2, 52, 49, 50, 51, 52, 57, 50, 58, 59, 49, 50, 51, 52, 60, 61, 4, 62, 60, 61, 63, 62, 64, 61, 65, 66, 60, 61, 63, 62, 67, 61, 2, 62, 4, 61, 63, 62, 68, 61, 4, 62, 4, 61, 63, 62, 69, 70, 4, 71, 69, 70, 72, 71, 73, 70, 4, 70, 69, 70, 72, 71, 74, 70, 2, 71, 4, 70, 72, 71, 75, 70, 4, 71, 4, 70, 72, 71, 100 };
        READ_INS = new int[] { 50, 51, 49, 19, 11, 1, 28, 70, 61, 69, 60, 13, 52, 4 };
        WRITE_INS = new int[] { 36, 38, 37, 35, 43, 48, 47, 46 };
        RMW_INS = new int[] { 5, 21, 14, 30, 72, 63, 3, 20, 12, 29, 71, 62 };
    }
}
