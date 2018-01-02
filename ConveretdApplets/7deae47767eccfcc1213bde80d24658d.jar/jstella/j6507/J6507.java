// 
// Decompiled by Procyon v0.5.30
// 

package jstella.j6507;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class J6507 implements Serializable
{
    private static final long serialVersionUID = 2315253717432920075L;
    private static final boolean DEBUG_MODE_ON = false;
    public static final int INDEX_PC = 0;
    public static final int INDEX_A = 1;
    public static final int INDEX_X = 2;
    public static final int INDEX_Y = 3;
    public static final int INDEX_SP = 4;
    public static final int INDEX_FLAGS = 5;
    private static final int[][] BCDTable;
    public static final AddressingMode[] ourAddressingModeTable;
    public static final int[] ourInstructionProcessorCycleTable;
    public static final int[] ourInstructionPageCrossDelay;
    public static final String[] ourInstructionMnemonicTable;
    private static final int StopExecutionBit = 1;
    private static final int FatalErrorBit = 2;
    private static final int MaskableInterruptBit = 4;
    private static final int NonmaskableInterruptBit = 8;
    private IfcSystem myCurrentSystem;
    private boolean N;
    private boolean V;
    private boolean B;
    private boolean D;
    private boolean I;
    private boolean notZ;
    private boolean C;
    private int A;
    private int X;
    private int Y;
    private int SP;
    private int IR;
    private int PC;
    private int myDebugInstructionsLeftToReport;
    private int myExecutionStatus;
    private int myLastOperandAddress;
    private int[] myLastImmediateValues;
    private boolean myPageCrossed;
    private int myBranchResult;
    private int myCyclesSignaled;
    private boolean myReadLast;
    private boolean debugStartDump;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public J6507(final IfcSystem aSystem) {
        this.myCurrentSystem = null;
        this.N = false;
        this.V = false;
        this.B = false;
        this.D = false;
        this.I = false;
        this.notZ = false;
        this.C = false;
        this.A = 0;
        this.X = 0;
        this.Y = 0;
        this.SP = 0;
        this.IR = 0;
        this.PC = 0;
        this.myDebugInstructionsLeftToReport = 0;
        this.myExecutionStatus = 0;
        this.myLastOperandAddress = 0;
        this.myLastImmediateValues = new int[2];
        this.myPageCrossed = false;
        this.myBranchResult = 0;
        this.myCyclesSignaled = 0;
        this.myReadLast = false;
        this.debugStartDump = false;
        this.install(aSystem);
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
    
    public int[] getRegisterSnapshot() {
        final int[] zReturn = { this.getPC(), this.getA(), this.getX(), this.getY(), this.getSP(), this.getFlags() };
        return zReturn;
    }
    
    public void install(final IfcSystem aSystem) {
        this.myCurrentSystem = aSystem;
    }
    
    public void stop() {
        this.myExecutionStatus |= 0x1;
    }
    
    public static int[][] getBCDTable() {
        return J6507.BCDTable;
    }
    
    public boolean isN() {
        return this.N;
    }
    
    public void setN(final boolean N) {
        this.N = N;
    }
    
    public void setN(final int aInt) {
        this.setN(aInt != 0);
    }
    
    public boolean isV() {
        return this.V;
    }
    
    public void setV(final boolean V) {
        this.V = V;
    }
    
    public void setV(final int aInt) {
        this.setV(aInt != 0);
    }
    
    public boolean isB() {
        return this.B;
    }
    
    public void setB(final boolean B) {
        this.B = B;
    }
    
    public void setB(final int aInt) {
        this.setB(aInt != 0);
    }
    
    public boolean isD() {
        return this.D;
    }
    
    public void setD(final boolean D) {
        this.D = D;
    }
    
    public void setD(final int aInt) {
        this.setD(aInt != 0);
    }
    
    public boolean isI() {
        return this.I;
    }
    
    public void setI(final boolean I) {
        this.I = I;
    }
    
    public void setI(final int aInt) {
        this.setI(aInt != 0);
    }
    
    public boolean isNotZ() {
        return this.notZ;
    }
    
    public void setNotZ(final boolean notZ) {
        this.notZ = notZ;
    }
    
    public void setNotZ(final int aInt) {
        this.setNotZ(aInt != 0);
    }
    
    public boolean isC() {
        return this.C;
    }
    
    public void setC(final boolean C) {
        this.C = C;
    }
    
    public void setC(final int aInt) {
        this.setC(aInt != 0);
    }
    
    public int getA() {
        assert this.A >= 0 && this.A < 256;
        return this.A;
    }
    
    public void setA(final int aValue) {
        assert aValue >= 0 && aValue < 256;
        this.A = (aValue & 0xFF);
    }
    
    public int getX() {
        return this.X;
    }
    
    public void setX(final int aValue) {
        assert aValue >= 0 && aValue < 256;
        this.X = (aValue & 0xFF);
    }
    
    public int getY() {
        return this.Y;
    }
    
    public void setY(final int aValue) {
        assert aValue >= 0 && aValue < 256;
        this.Y = (aValue & 0xFF);
    }
    
    public int getSP() {
        return this.SP;
    }
    
    public void setSP(final int aValue) {
        assert aValue >= 0 && aValue < 256;
        this.SP = aValue;
    }
    
    public int SPdec() {
        final int zOldSP = this.SP;
        this.SP = (this.SP - 1 & 0xFF);
        return zOldSP;
    }
    
    public int SPinc() {
        final int zOldSP = this.SP;
        this.SP = (this.SP + 1 & 0xFF);
        return zOldSP;
    }
    
    public int getIR() {
        return this.IR;
    }
    
    public void setIR(final int aValue) {
        assert aValue >= 0 && aValue < 256;
        this.IR = aValue;
    }
    
    public int getPC() {
        return this.PC;
    }
    
    public void setPC(final int aPC) {
        this.PC = aPC;
    }
    
    public boolean lastAccessWasRead() {
        return this.myReadLast;
    }
    
    private boolean notSamePage(final int aAddrA, final int aAddrB) {
        return ((aAddrA ^ aAddrB) & 0xFF00) != 0x0;
    }
    
    public int execute() throws J6507Exception {
        return this.execute(-1);
    }
    
    public int execute(final int aRepeats) throws J6507Exception {
        boolean zContinue = true;
        this.myExecutionStatus &= 0x2;
        int zCounter = 0;
        while (zContinue) {
            if (aRepeats >= 0 && zCounter == aRepeats) {
                zContinue = false;
                break;
            }
            ++zCounter;
            if (!zContinue) {
                break;
            }
            final int[] zPreSnapshot = null;
            final int zOpPC = this.getPC();
            this.IR = this.peekImmediate();
            int zOperand = 0;
            int zOperandAddress = 0;
            switch (this.IR) {
                case 0: {
                    this.INSTR_BRK();
                    break;
                }
                case 97:
                case 101:
                case 105:
                case 109:
                case 113:
                case 117:
                case 121:
                case 125: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    this.INSTR_ADC(zOperand);
                    break;
                }
                case 161:
                case 165:
                case 169:
                case 173:
                case 177:
                case 181:
                case 185:
                case 189: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    this.INSTR_LDA(zOperand);
                    break;
                }
                case 162:
                case 166:
                case 174:
                case 182:
                case 190: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    this.INSTR_LDX(zOperand);
                    break;
                }
                case 160:
                case 164:
                case 172:
                case 180:
                case 188: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    this.INSTR_LDY(zOperand);
                    break;
                }
                case 33:
                case 37:
                case 41:
                case 45:
                case 49:
                case 53:
                case 57:
                case 61: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    this.INSTR_AND(zOperand);
                    break;
                }
                case 10: {
                    this.INSTR_ASLA();
                    break;
                }
                case 6:
                case 14:
                case 22:
                case 30: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    zOperandAddress = this.myLastOperandAddress;
                    this.INSTR_ASL(zOperand, zOperandAddress);
                    break;
                }
                case 36:
                case 44: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    this.INSTR_BIT(zOperand);
                    break;
                }
                case 24: {
                    this.INSTR_CLC();
                    break;
                }
                case 56: {
                    this.INSTR_SEC();
                    break;
                }
                case 88: {
                    this.INSTR_CLI();
                    break;
                }
                case 120: {
                    this.INSTR_SEI();
                    break;
                }
                case 184: {
                    this.INSTR_CLV();
                    break;
                }
                case 216: {
                    this.INSTR_CLD();
                    break;
                }
                case 248: {
                    this.INSTR_SED();
                    break;
                }
                case 193:
                case 197:
                case 201:
                case 205:
                case 209:
                case 213:
                case 217:
                case 221: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    this.INSTR_CMP(zOperand);
                    break;
                }
                case 224:
                case 228:
                case 236: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    this.INSTR_CPX(zOperand);
                    break;
                }
                case 192:
                case 196:
                case 204: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    this.INSTR_CPY(zOperand);
                    break;
                }
                case 198:
                case 206:
                case 214:
                case 222: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    zOperandAddress = this.myLastOperandAddress;
                    this.INSTR_DEC(zOperand, zOperandAddress);
                    break;
                }
                case 65:
                case 69:
                case 73:
                case 77:
                case 81:
                case 85:
                case 89:
                case 93: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    this.INSTR_EOR(zOperand);
                    break;
                }
                case 230:
                case 238:
                case 246:
                case 254: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    zOperandAddress = this.myLastOperandAddress;
                    this.INSTR_INC(zOperand, zOperandAddress);
                    break;
                }
                case 170: {
                    this.INSTR_TAX();
                    break;
                }
                case 138: {
                    this.INSTR_TXA();
                    break;
                }
                case 202: {
                    this.INSTR_DEX();
                    break;
                }
                case 232: {
                    this.INSTR_INX();
                    break;
                }
                case 168: {
                    this.INSTR_TAY();
                    break;
                }
                case 152: {
                    this.INSTR_TYA();
                    break;
                }
                case 136: {
                    this.INSTR_DEY();
                    break;
                }
                case 200: {
                    this.INSTR_INY();
                    break;
                }
                case 76: {
                    this.peekAbsoluteJMP();
                    this.INSTR_JMP(zOperand, this.myLastOperandAddress);
                    break;
                }
                case 108: {
                    this.peekIndirect();
                    this.INSTR_JMP(zOperand, this.myLastOperandAddress);
                    break;
                }
                case 32: {
                    this.INSTR_JSR();
                    break;
                }
                case 74: {
                    this.INSTR_LSRA();
                    break;
                }
                case 70:
                case 78:
                case 86:
                case 94: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    zOperandAddress = this.myLastOperandAddress;
                    this.INSTR_LSR(zOperand, zOperandAddress);
                    break;
                }
                case 234: {
                    this.INSTR_NOP();
                    break;
                }
                case 1:
                case 5:
                case 9:
                case 13:
                case 17:
                case 21:
                case 25:
                case 29: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    this.INSTR_ORA(zOperand);
                    break;
                }
                case 154: {
                    this.INSTR_TXS();
                    break;
                }
                case 186: {
                    this.INSTR_TSX();
                    break;
                }
                case 72: {
                    this.INSTR_PHA();
                    break;
                }
                case 104: {
                    this.INSTR_PLA();
                    break;
                }
                case 8: {
                    this.INSTR_PHP();
                    break;
                }
                case 40: {
                    this.INSTR_PLP();
                    break;
                }
                case 42: {
                    this.INSTR_ROLA();
                    break;
                }
                case 38:
                case 46:
                case 54:
                case 62: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    zOperandAddress = this.myLastOperandAddress;
                    this.INSTR_ROL(zOperand, zOperandAddress);
                    break;
                }
                case 106: {
                    this.INSTR_RORA();
                    break;
                }
                case 102:
                case 110:
                case 118:
                case 126: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    zOperandAddress = this.myLastOperandAddress;
                    this.INSTR_ROR(zOperand, zOperandAddress);
                    break;
                }
                case 64: {
                    this.INSTR_RTI();
                    break;
                }
                case 96: {
                    this.INSTR_RTS();
                    break;
                }
                case 225:
                case 229:
                case 233:
                case 237:
                case 241:
                case 245:
                case 249:
                case 253: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    this.INSTR_SBC(zOperand);
                    break;
                }
                case 129:
                case 133:
                case 141:
                case 145:
                case 149:
                case 153:
                case 157: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    zOperandAddress = this.myLastOperandAddress;
                    this.INSTR_STA(zOperand, zOperandAddress);
                    break;
                }
                case 134:
                case 142:
                case 150: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    zOperandAddress = this.myLastOperandAddress;
                    this.INSTR_STX(zOperand, zOperandAddress);
                    break;
                }
                case 132:
                case 140:
                case 148: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    zOperandAddress = this.myLastOperandAddress;
                    this.INSTR_STY(zOperand, zOperandAddress);
                    break;
                }
                case 16: {
                    zOperand = this.peekImmediate();
                    this.INSTR_BPL(zOperand);
                    break;
                }
                case 48: {
                    zOperand = this.peekImmediate();
                    this.INSTR_BMI(zOperand);
                    break;
                }
                case 80: {
                    zOperand = this.peekImmediate();
                    this.INSTR_BVC(zOperand);
                    break;
                }
                case 112: {
                    zOperand = this.peekImmediate();
                    this.INSTR_BVS(zOperand);
                    break;
                }
                case 144: {
                    zOperand = this.peekImmediate();
                    this.INSTR_BCC(zOperand);
                    break;
                }
                case 176: {
                    zOperand = this.peekImmediate();
                    this.INSTR_BCS(zOperand);
                    break;
                }
                case 208: {
                    zOperand = this.peekImmediate();
                    this.INSTR_BNE(zOperand);
                    break;
                }
                case 240: {
                    zOperand = this.peekImmediate();
                    this.INSTR_BEQ(zOperand);
                    break;
                }
                case 131:
                case 135:
                case 143:
                case 151: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    zOperandAddress = this.myLastOperandAddress;
                    this.INSTR_sax(zOperand, zOperandAddress);
                    break;
                }
                case 163:
                case 167:
                case 175:
                case 179:
                case 183:
                case 191: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    this.INSTR_lax(zOperand);
                    break;
                }
                case 203: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    this.INSTR_sbx(zOperand);
                    break;
                }
                case 4:
                case 12:
                case 20:
                case 26:
                case 28:
                case 52:
                case 58:
                case 60:
                case 68:
                case 84:
                case 90:
                case 92:
                case 100:
                case 116:
                case 122:
                case 124:
                case 128:
                case 130:
                case 137:
                case 194:
                case 212:
                case 218:
                case 220:
                case 226:
                case 244:
                case 250:
                case 252: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    this.INSTR_nop(zOperand);
                    break;
                }
                case 195:
                case 199:
                case 207:
                case 211:
                case 215:
                case 219:
                case 223: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    zOperandAddress = this.myLastOperandAddress;
                    this.INSTR_dcp(zOperand, zOperandAddress);
                    break;
                }
                case 227:
                case 231:
                case 239:
                case 243:
                case 247:
                case 251:
                case 255: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    zOperandAddress = this.myLastOperandAddress;
                    this.INSTR_isb(zOperand, zOperandAddress);
                    break;
                }
                case 3:
                case 7:
                case 15:
                case 19:
                case 23:
                case 27:
                case 31: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    zOperandAddress = this.myLastOperandAddress;
                    this.INSTR_slo(zOperand, zOperandAddress);
                    break;
                }
                case 75: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    this.INSTR_asr(zOperand);
                    break;
                }
                case 35:
                case 39:
                case 47:
                case 51:
                case 55:
                case 59:
                case 63: {
                    zOperand = this.retrieveOperand(J6507.ourAddressingModeTable[this.IR]);
                    zOperandAddress = this.myLastOperandAddress;
                    this.INSTR_rla(zOperand, zOperandAddress);
                    break;
                }
                default: {
                    final String zMsg = "Instruction not recognized - " + J6507.ourInstructionMnemonicTable[this.IR] + " (0x" + Integer.toHexString(this.IR) + ") at " + Integer.toHexString(this.getPC()) + "\n" + "Instructions in this cycle=" + zCounter;
                    throw new J6507Exception(J6507Exception.ExceptionType.INSTRUCTION_NOT_RECOGNIZED, zMsg);
                }
            }
            final int zCycles = this.calculateCycles(this.IR) - this.myCyclesSignaled;
            if (zCycles < 0) {
                final int zDebug = 20;
            }
            assert zCycles >= 0;
            this.myCurrentSystem.processorCycle(zCycles);
            this.myCyclesSignaled = 0;
            if (((this.myExecutionStatus & 0x4) != 0x0 || (this.myExecutionStatus & 0x8) != 0x0) && !J6507.$assertionsDisabled) {
                throw new AssertionError();
            }
            if ((this.myExecutionStatus & 0x1) != 0x0) {
                break;
            }
        }
        return zCounter;
    }
    
    private void signalCycle() {
        this.myCurrentSystem.processorCycle(1);
        ++this.myCyclesSignaled;
    }
    
    private int calculateCycles(final int aIR) {
        int zCycleNum = J6507.ourInstructionProcessorCycleTable[aIR];
        final boolean zIsBranch = J6507.ourInstructionPageCrossDelay[aIR] == 2;
        final boolean zPageDependent = J6507.ourInstructionPageCrossDelay[aIR] == 1;
        if (zIsBranch) {
            zCycleNum += this.myBranchResult;
        }
        else if (zPageDependent && this.myPageCrossed) {
            ++zCycleNum;
        }
        return zCycleNum;
    }
    
    private void debugCommand(final int aOpPC, final int aIR, final int[] aOperands, final int[] aPre, final int[] aPost) {
        String zReturn = "";
        zReturn = "" + Integer.toHexString(aOpPC) + " " + J6507.ourInstructionMnemonicTable[this.IR];
        final AddressingMode zMode = J6507.ourAddressingModeTable[aIR];
        switch (zMode) {
            case Absolute:
            case AbsoluteX:
            case AbsoluteY: {
                zReturn = zReturn + " $" + Integer.toHexString(aOperands[0]) + Integer.toHexString(aOperands[1]);
                if (zMode == AddressingMode.AbsoluteX) {
                    zReturn += ",X";
                    break;
                }
                if (zMode == AddressingMode.AbsoluteY) {
                    zReturn += ",Y";
                    break;
                }
                break;
            }
            case Immediate: {
                zReturn = zReturn + " #$" + Integer.toHexString(aOperands[0]);
                break;
            }
            case Indirect: {
                zReturn = zReturn + " ($" + Integer.toHexString(aOperands[0]) + Integer.toHexString(aOperands[1]) + ")";
                break;
            }
            case IndirectX: {
                zReturn = zReturn + " ($" + Integer.toHexString(aOperands[0]) + ",X)";
                break;
            }
            case IndirectY: {
                zReturn = zReturn + " ($" + Integer.toHexString(aOperands[0]) + "),Y";
                break;
            }
            case Relative: {
                zReturn = zReturn + " $" + Integer.toHexString(aOperands[0]) + "";
                break;
            }
            case Zero: {
                zReturn = zReturn + " $" + Integer.toHexString(aOperands[0]) + "";
                break;
            }
            case ZeroX: {
                zReturn = zReturn + " $" + Integer.toHexString(aOperands[0]) + ",X";
                break;
            }
            case ZeroY: {
                zReturn = zReturn + " $" + Integer.toHexString(aOperands[0]) + ",Y";
                break;
            }
        }
    }
    
    public void reset() {
        this.myExecutionStatus = 0;
        this.A = 0;
        this.X = 0;
        this.Y = 0;
        this.SP = 255;
        this.setFlags(32);
        this.setPC(this.myCurrentSystem.getResetPC());
    }
    
    private int peek(final int aAddress, final boolean aSignalCycle) {
        assert aAddress >= 0;
        this.myReadLast = true;
        this.myLastOperandAddress = aAddress;
        if (aSignalCycle) {
            this.signalCycle();
        }
        return this.myCurrentSystem.peek(aAddress);
    }
    
    private int peek(final int aAddress) {
        return this.peek(aAddress, true);
    }
    
    private int peekImmediate() {
        final int zReturn = this.peek(this.PC);
        ++this.PC;
        this.myLastImmediateValues[1] = this.myLastImmediateValues[0];
        return this.myLastImmediateValues[0] = zReturn;
    }
    
    private int peekZeroPage() {
        final int zAddr = this.peekImmediate();
        return this.peek(zAddr);
    }
    
    private int peekZeroPage(final int aAdd) {
        int zAddr = this.peekImmediate();
        this.peek(zAddr);
        zAddr += aAdd;
        zAddr &= 0xFF;
        return this.peek(zAddr);
    }
    
    private int peekAbsolute() {
        final int zLowByte = this.peekImmediate();
        final int zHighByte = this.peekImmediate();
        final int zAddr = zLowByte | zHighByte << 8;
        this.myPageCrossed = false;
        return this.peek(zAddr);
    }
    
    private int peekAbsoluteJMP() {
        final int zLowByte = this.peekImmediate();
        final int zHighByte = this.peekImmediate();
        final int zAddr = zLowByte | zHighByte << 8;
        this.myPageCrossed = false;
        return this.peek(zAddr, false);
    }
    
    private int peekAbsoluteIndex(final int aAdd) {
        final int zLowByte = this.peekImmediate();
        final int zHighByte = this.peekImmediate();
        int zAddr = zLowByte | zHighByte << 8;
        zAddr += aAdd;
        if (zLowByte + aAdd > 255) {
            this.peek(zAddr);
            this.myPageCrossed = true;
        }
        else {
            this.myPageCrossed = false;
        }
        return this.peek(zAddr);
    }
    
    private int peekIndirect() {
        final int zLowByte = this.peekImmediate();
        final int zHighByte = this.peekImmediate();
        final int zAddr = zLowByte | zHighByte << 8;
        final int zLowByteB = this.peek(zAddr);
        final int zHighByteB = this.peek(zAddr + 1);
        final int zAddrB = zLowByteB | zHighByteB << 8;
        return this.peek(zAddrB, false);
    }
    
    private int peekIndirectX() {
        final int zZeroPage = this.peekImmediate() + this.X & 0xFF;
        final int zLowByte = this.peek(zZeroPage);
        final int zHighByte = this.peek(zZeroPage + 1 & 0xFF);
        final int zAddr = zLowByte | zHighByte << 8;
        final int zReturn = this.peek(zAddr);
        return zReturn;
    }
    
    private int peekIndirectY() {
        final int zZeroPage = this.peekImmediate();
        final int zLowByte = this.peek(zZeroPage);
        final int zHighByte = this.peek(zZeroPage + 1);
        final int zAddr = (zLowByte | zHighByte << 8) + this.Y;
        if (zLowByte + this.Y > 255) {
            this.peek(zAddr);
            this.myPageCrossed = true;
        }
        else {
            this.myPageCrossed = false;
        }
        return this.peek(zAddr);
    }
    
    private int peekRelative() {
        final int zOldPC = this.PC;
        final int zByte = this.peekImmediate();
        final int zAdd = toSignedByteValue(zByte);
        return zOldPC + zAdd;
    }
    
    private static int toSignedByteValue(final int aUnsignedByteValue) {
        assert aUnsignedByteValue >= 0;
        assert aUnsignedByteValue < 256;
        if (aUnsignedByteValue >= 0 && aUnsignedByteValue <= 127) {
            return aUnsignedByteValue;
        }
        return aUnsignedByteValue - 256;
    }
    
    private int retrieveOperand(final AddressingMode aMode) {
        if (aMode == AddressingMode.Immediate) {
            return this.peekImmediate();
        }
        if (aMode == AddressingMode.Zero) {
            return this.peekZeroPage();
        }
        if (aMode == AddressingMode.ZeroX) {
            return this.peekZeroPage(this.X);
        }
        if (aMode == AddressingMode.ZeroY) {
            return this.peekZeroPage(this.Y);
        }
        if (aMode == AddressingMode.Indirect) {
            return this.peekIndirect();
        }
        if (aMode == AddressingMode.IndirectX) {
            return this.peekIndirectX();
        }
        if (aMode == AddressingMode.IndirectY) {
            return this.peekIndirectY();
        }
        if (aMode == AddressingMode.Absolute) {
            return this.peekAbsolute();
        }
        if (aMode == AddressingMode.AbsoluteX) {
            return this.peekAbsoluteIndex(this.X);
        }
        if (aMode == AddressingMode.AbsoluteY) {
            return this.peekAbsoluteIndex(this.Y);
        }
        if (aMode == AddressingMode.Relative) {
            return this.peekRelative();
        }
        assert false;
        return 0;
    }
    
    private void poke(final int aAddress, final int aByteValue) {
        assert aByteValue < 256 && aByteValue >= 0;
        if (aAddress >= 0) {
            this.myCurrentSystem.poke(aAddress, aByteValue);
        }
        this.myReadLast = false;
    }
    
    private void setFlags(final int aByteValue) {
        this.N = ((aByteValue & 0x80) != 0x0);
        this.V = ((aByteValue & 0x40) != 0x0);
        this.B = ((aByteValue & 0x10) != 0x0);
        this.D = ((aByteValue & 0x8) != 0x0);
        this.I = ((aByteValue & 0x4) != 0x0);
        this.notZ = ((aByteValue & 0x2) == 0x0);
        this.C = ((aByteValue & 0x1) != 0x0);
    }
    
    private int getFlags() {
        int ps = 32;
        if (this.N) {
            ps |= 0x80;
        }
        if (this.V) {
            ps |= 0x40;
        }
        if (this.B) {
            ps |= 0x10;
        }
        if (this.D) {
            ps |= 0x8;
        }
        if (this.I) {
            ps |= 0x4;
        }
        if (!this.notZ) {
            ps |= 0x2;
        }
        if (this.C) {
            ps |= 0x1;
        }
        return ps;
    }
    
    private static boolean getBit(final int aByte, final int aBitNumber) {
        return (aByte & 1 << aBitNumber) != 0x0;
    }
    
    private void INSTR_ADC(final int operand) {
        final int oldA = this.A;
        assert operand >= 0 && operand < 256;
        int zUSum = this.A + operand;
        if (!this.D) {
            int zSignedSum = (byte)this.A + (byte)operand;
            if (this.C) {
                ++zSignedSum;
            }
            this.V = (zSignedSum > 127 || zSignedSum < -128);
            if (this.C) {
                ++zUSum;
            }
            this.setC(zUSum > 255);
            this.setA(zUSum & 0xFF);
            this.setNotZ((zUSum & 0xFF) != 0x0);
            this.setN(getBit(this.A, 7));
        }
        else {
            final int sum = J6507.BCDTable[0][this.A] + J6507.BCDTable[0][operand] + (this.C ? 1 : 0);
            this.setC(sum > 99);
            this.setNotZ(zUSum & 0xFF);
            this.setA(J6507.BCDTable[1][sum & 0xFF]);
            this.setN(getBit(this.A, 7));
            this.V = (((oldA ^ this.A) & 0x80) != 0x0 && ((this.A ^ operand) & 0x80) != 0x0);
        }
    }
    
    private void INSTR_SBC(final int operand) {
        final int oldA = this.A & 0xFF;
        assert operand >= 0 && operand < 256;
        if (!this.D) {
            final int zRevOperand = ~operand & 0xFF;
            final int zAmountToAdd = toSignedByteValue(zRevOperand) + (this.C ? 1 : 0);
            final int zSignedResult = toSignedByteValue(this.A) + zAmountToAdd;
            this.setV(zSignedResult > 127 || zSignedResult < -128);
            final int zNewA = this.A + zAmountToAdd;
            final int zAmountToSubtract = operand + (this.C ? 0 : 1);
            this.setC(zAmountToSubtract <= oldA);
            this.setA(zNewA & 0xFF);
            this.setNotZ(this.A != 0);
            this.setN((this.A & 0x80) != 0x0);
        }
        else {
            int difference = J6507.BCDTable[0][this.A] - J6507.BCDTable[0][operand] - (this.C ? 0 : 1);
            if (difference < 0) {
                difference += 100;
            }
            this.setNotZ(this.A + ~operand + (this.C ? 1 : 0) & 0xFF);
            this.setA(J6507.BCDTable[1][difference]);
            this.setN((this.A & 0x80) != 0x0);
            this.setC(oldA >= operand + (this.C ? 0 : 1));
            this.setV(((oldA ^ this.A) & 0x80) != 0x0 && ((this.A ^ operand) & 0x80) != 0x0);
        }
    }
    
    private void INSTR_LDA(final int aValue) {
        this.setA(aValue);
        this.notZ = (this.A != 0);
        this.N = ((this.A & 0x80) != 0x0);
    }
    
    private void INSTR_LDX(final int operand) {
        assert operand < 256;
        this.setX(operand);
        this.notZ = (this.X != 0);
        this.N = ((this.X & 0x80) != 0x0);
    }
    
    private void INSTR_LDY(final int operand) {
        this.Y = operand;
        this.notZ = (this.Y != 0);
        this.N = ((this.Y & 0x80) != 0x0);
    }
    
    private void INSTR_AND(final int aValue) {
        final int zNewA = this.getA() & aValue;
        this.setA(zNewA);
        this.setNotZ(zNewA != 0);
        this.setN((zNewA & 0x80) != 0x0);
    }
    
    private void INSTR_EOR(final int aValue) {
        final int zNewA = this.getA() ^ aValue;
        this.setA(zNewA);
        this.setNotZ(zNewA != 0);
        this.setN((zNewA & 0x80) != 0x0);
    }
    
    private void INSTR_ORA(final int aValue) {
        final int zNewA = this.getA() | aValue;
        this.setA(zNewA);
        this.setNotZ(zNewA != 0);
        this.setN((zNewA & 0x80) != 0x0);
    }
    
    private void INSTR_ASL(int aValue, final int operandAddress) {
        this.setC(aValue & 0x80);
        aValue <<= 1;
        aValue &= 0xFF;
        this.poke(operandAddress, aValue);
        this.setNotZ(aValue != 0);
        this.setN(aValue & 0x80);
    }
    
    private void INSTR_ASLA() {
        this.setC(this.A & 0x80);
        int zNewA = this.getA() << 1;
        zNewA &= 0xFF;
        this.setA(zNewA);
        this.setNotZ(this.A != 0);
        this.setN((this.A & 0x80) != 0x0);
    }
    
    private void branch(final boolean aDoBranch, final int aDelta) {
        if (aDoBranch) {
            this.peek(this.PC);
            final int address = this.PC + toSignedByteValue(aDelta);
            if (this.notSamePage(this.PC, address)) {
                this.myBranchResult = 2;
            }
            else {
                this.myBranchResult = 1;
            }
            this.setPC(address);
        }
        else {
            this.myBranchResult = 0;
        }
    }
    
    private void INSTR_BCC(final int operand) {
        this.branch(!this.C, operand);
    }
    
    private void INSTR_BCS(final int operand) {
        this.branch(this.C, operand);
    }
    
    private void INSTR_BEQ(final int operand) {
        this.branch(!this.notZ, operand);
    }
    
    private void INSTR_BMI(final int operand) {
        this.branch(this.N, operand);
    }
    
    private void INSTR_BNE(final int operand) {
        this.branch(this.notZ, operand);
    }
    
    private void INSTR_BPL(final int operand) {
        this.branch(!this.N, operand);
    }
    
    private void INSTR_BVC(final int operand) {
        this.branch(!this.V, operand);
    }
    
    private void INSTR_BVS(final int operand) {
        this.branch(this.V, operand);
    }
    
    private void INSTR_BIT(final int operand) {
        assert operand >= 0 && operand < 256;
        this.setNotZ(this.A & operand);
        this.setN(operand & 0x80);
        this.setV(operand & 0x40);
    }
    
    private void INSTR_BRK() {
        this.peek(this.PC++);
        this.B = true;
        this.poke(256 + this.SPdec(), this.PC >> 8);
        this.poke(256 + this.SPdec(), this.PC & 0xFF);
        this.poke(256 + this.SPdec(), this.getFlags());
        this.I = true;
        this.PC = this.peek(65534);
        this.PC |= this.peek(65535) << 8;
    }
    
    private void INSTR_CLC() {
        this.setC(false);
    }
    
    private void INSTR_CLD() {
        this.setD(false);
    }
    
    private void INSTR_CLI() {
        this.setI(false);
    }
    
    private void INSTR_CLV() {
        this.setV(false);
    }
    
    private void INSTR_SEC() {
        this.setC(true);
    }
    
    private void INSTR_SED() {
        this.setD(true);
    }
    
    private void INSTR_SEI() {
        this.setI(true);
    }
    
    private void INSTR_CMP(final int operand) {
        final int value = this.A - operand;
        this.setNotZ(value);
        this.setN(value & 0x80);
        this.setC((value & 0x100) == 0x0);
    }
    
    private void INSTR_CPX(final int operand) {
        final int value = this.X - operand;
        this.setNotZ(value);
        this.setN(value & 0x80);
        this.setC((value & 0x100) == 0x0);
    }
    
    private void INSTR_CPY(final int operand) {
        final int value = this.Y - operand;
        this.setNotZ(value);
        this.setN(value & 0x80);
        this.setC((value & 0x100) == 0x0);
    }
    
    private void INSTR_DEC(final int operand, final int operandAddress) {
        int value = operand - 1;
        value &= 0xFF;
        this.poke(operandAddress, value);
        this.setNotZ(value);
        this.setN(value & 0x80);
    }
    
    private void INSTR_DEX() {
        --this.X;
        this.X &= 0xFF;
        this.notZ = (this.X != 0);
        this.N = ((this.X & 0x80) != 0x0);
    }
    
    private void INSTR_DEY() {
        --this.Y;
        this.Y &= 0xFF;
        this.notZ = (this.Y != 0);
        this.N = ((this.Y & 0x80) != 0x0);
    }
    
    private void INSTR_INC(final int operand, final int operandAddress) {
        int value = operand + 1;
        value &= 0xFF;
        this.poke(operandAddress, value);
        this.setNotZ(value);
        this.setN(value & 0x80);
    }
    
    private void INSTR_INX() {
        ++this.X;
        this.X &= 0xFF;
        assert this.X < 256;
        this.notZ = (this.X != 0);
        this.N = ((this.X & 0x80) != 0x0);
    }
    
    private void INSTR_INY() {
        ++this.Y;
        this.Y &= 0xFF;
        this.notZ = (this.Y != 0);
        this.N = ((this.Y & 0x80) != 0x0);
    }
    
    private void INSTR_JMP(final int operand, final int operandAddress) {
        this.PC = operandAddress;
    }
    
    private void INSTR_JSR() {
        final int low = this.peekImmediate();
        this.peek(256 + this.SP);
        this.poke(256 + this.SPdec(), this.PC >>> 8);
        this.poke(256 + this.SPdec(), this.PC & 0xFF);
        final int high = this.peekImmediate();
        this.PC = (low | high << 8);
    }
    
    private void INSTR_RTS() {
        this.peek(256 + this.SPinc());
        int zAddr = 0;
        zAddr = this.peek(256 + this.SPinc());
        final int zNewPC = zAddr | this.peek(256 + this.SP) << 8;
        this.setPC(zNewPC);
        this.peek(this.PC++);
    }
    
    private void INSTR_LSR(int operand, final int operandAddress) {
        this.setC(operand & 0x1);
        operand = (operand >> 1 & 0x7F);
        this.poke(operandAddress, operand);
        this.notZ = (operand != 0);
        this.setN(operand & 0x80);
    }
    
    private void INSTR_LSRA() {
        this.setC(this.A & 0x1);
        this.setA(this.getA() >> 1 & 0x7F);
        this.setNotZ(this.A != 0);
        this.setN((this.A & 0x80) != 0x0);
    }
    
    private void INSTR_NOP() {
    }
    
    private void INSTR_PHA() {
        this.poke(256 + this.SPdec(), this.A);
    }
    
    private void INSTR_PHP() {
        this.poke(256 + this.SPdec(), this.getFlags());
    }
    
    private void INSTR_PLA() {
        this.peek(256 + this.SPinc());
        this.setA(this.peek(256 + this.SP));
        this.setNotZ(this.A != 0);
        this.setN((this.A & 0x80) != 0x0);
    }
    
    private void INSTR_PLP() {
        this.peek(256 + this.SPinc());
        this.setFlags(this.peek(256 + this.SP));
    }
    
    private void INSTR_ROL(int operand, final int operandAddress) {
        final boolean oldC = this.C;
        this.setC(operand & 0x80);
        operand = ((operand << 1 | (oldC ? 1 : 0)) & 0xFF);
        this.poke(operandAddress, operand);
        this.notZ = (operand != 0);
        this.setN(operand & 0x80);
    }
    
    private void INSTR_ROLA() {
        final boolean oldC = this.C;
        this.setC(this.A & 0x80);
        final int zNewA = this.getA() << 1 | (oldC ? 1 : 0);
        this.setA(zNewA & 0xFF);
        this.setNotZ(this.A != 0);
        this.N = ((this.A & 0x80) != 0x0);
    }
    
    private void INSTR_ROR(int operand, final int operandAddress) {
        final boolean oldC = this.C;
        this.setC(operand & 0x1);
        operand = ((operand >> 1 & 0x7F) | (oldC ? 128 : 0));
        this.poke(operandAddress, operand);
        this.notZ = (operand != 0);
        this.setN(operand & 0x80);
    }
    
    private void INSTR_RORA() {
        final boolean oldC = this.C;
        this.setC(this.A & 0x1);
        final int zOldA = this.getA();
        final int zNewA = (this.getA() >> 1 & 0x7F) | (oldC ? 128 : 0);
        this.setA(zNewA);
        this.notZ = (zNewA != 0);
        this.N = ((zNewA & 0x80) != 0x0);
    }
    
    private void INSTR_RTI() {
        this.peek(256 + this.SPinc());
        this.setFlags(this.peek(256 + this.SPinc()));
        this.PC = this.peek(256 + this.SPinc());
        this.PC |= this.peek(256 + this.SP) << 8;
    }
    
    private void INSTR_STA(final int operand, final int operandAddress) {
        this.poke(operandAddress, this.getA());
    }
    
    private void INSTR_STX(final int operand, final int operandAddress) {
        this.poke(operandAddress, this.X);
    }
    
    private void INSTR_STY(final int operand, final int operandAddress) {
        this.poke(operandAddress, this.Y);
    }
    
    private void INSTR_TAX() {
        this.X = this.A;
        this.notZ = (this.X != 0);
        this.N = ((this.X & 0x80) != 0x0);
    }
    
    private void INSTR_TAY() {
        this.Y = this.A;
        this.notZ = (this.Y != 0);
        this.N = ((this.Y & 0x80) != 0x0);
    }
    
    private void INSTR_TSX() {
        this.X = this.SP;
        this.notZ = (this.X != 0);
        this.N = ((this.X & 0x80) != 0x0);
    }
    
    private void INSTR_TXA() {
        this.setA(this.X);
        this.notZ = (this.A != 0);
        this.N = ((this.A & 0x80) != 0x0);
    }
    
    private void INSTR_TXS() {
        this.setSP(this.X);
    }
    
    private void INSTR_TYA() {
        this.setA(this.Y);
        this.notZ = (this.A != 0);
        this.N = ((this.A & 0x80) != 0x0);
    }
    
    private void INSTR_sax(final int operand, final int operandAddress) {
        this.poke(operandAddress, this.A & this.X);
    }
    
    private void INSTR_lax(final int aValue) {
        this.setA(aValue);
        this.setX(aValue);
        this.notZ = (this.A != 0);
        this.N = ((this.A & 0x80) != 0x0);
    }
    
    private void INSTR_sbx(final int operand) {
        int difference = (this.A & this.X & 0xFF) - operand;
        this.setC((difference & 0x100) == 0x0);
        difference &= 0xFF;
        this.setX(difference);
        this.setNotZ(difference != 0);
        this.setN((difference & 0x80) != 0x0);
    }
    
    private void INSTR_asr(final int operand) {
        int myA = this.A & operand;
        this.setC(myA & 0x1);
        myA = (myA >> 1 & 0x7F);
        this.setA(myA);
        this.setNotZ(myA != 0);
        this.setN((myA & 0x80) != 0x0);
    }
    
    private void INSTR_rla(final int operand, final int operandAddress) {
        final int zValue = operand << 1 | (this.C ? 1 : 0);
        this.poke(operandAddress, zValue);
        final int zNewA = this.A & zValue;
        this.setA(zNewA & 0xFF);
        this.setC(operand & 0x80);
        this.setNotZ(zNewA);
        this.setN(zNewA & 0x80);
    }
    
    private void INSTR_nop(final int operand) {
    }
    
    private void INSTR_dcp(final int operand, final int operandAddress) {
        int value = operand - 1;
        value &= 0xFF;
        this.poke(operandAddress, value);
        value = this.A - value;
        this.setNotZ(value);
        this.setN(value & 0x80);
        this.setC((value & 0x100) == 0x0);
    }
    
    private void INSTR_isb(final int operand, final int operandAddress) {
        int value = operand + 1;
        value &= 0xFF;
        this.poke(operandAddress, value);
        final int oldA = this.A;
        if (!this.D) {
            final int zRevOperand = ~value & 0xFF;
            final int Sdifference = toSignedByteValue(this.A) + toSignedByteValue(zRevOperand) + (this.C ? 1 : 0);
            this.setV(Sdifference > 127 || Sdifference < -128);
            final int zSBV = toSignedByteValue(zRevOperand);
            final int difference = this.A + zSBV + (this.C ? 1 : 0);
            final int zSubAmount = value + (this.C ? 0 : 1);
            this.setC(zSubAmount <= oldA);
            this.setA(difference & 0xFF);
            this.setNotZ(this.A != 0);
            this.setN((this.A & 0x80) != 0x0);
        }
        else {
            int difference2 = J6507.BCDTable[0][this.A & 0xFF] - J6507.BCDTable[0][value & 0xFF] - (this.C ? 0 : 1);
            if (difference2 < 0) {
                difference2 += 100;
            }
            this.setA(J6507.BCDTable[1][difference2 & 0xFF]);
            this.setNotZ(this.A != 0);
            this.setN((this.A & 0x80) != 0x0);
            this.setC(oldA >= value + (this.C ? 0 : 1));
            this.setV(((oldA ^ this.A) & 0x80) != 0x0 && ((this.A ^ value) & 0x80) != 0x0);
        }
    }
    
    private void INSTR_slo(int operand, final int operandAddress) {
        this.setC(operand & 0x80);
        operand <<= 1;
        operand &= 0xFF;
        this.poke(operandAddress, operand);
        this.INSTR_ORA(operand);
    }
    
    static {
        BCDTable = new int[2][256];
        for (int t = 0; t < 256; ++t) {
            getBCDTable()[0][t] = (t >> 4) * 10 + (t & 0xF);
            getBCDTable()[1][t] = (t % 100 / 10 << 4 | t % 10);
        }
        ourAddressingModeTable = new AddressingMode[] { AddressingMode.Implied, AddressingMode.IndirectX, AddressingMode.Invalid, AddressingMode.IndirectX, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Implied, AddressingMode.Immediate, AddressingMode.Implied, AddressingMode.Immediate, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Relative, AddressingMode.IndirectY, AddressingMode.Invalid, AddressingMode.IndirectY, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.Implied, AddressingMode.AbsoluteY, AddressingMode.Implied, AddressingMode.AbsoluteY, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.Absolute, AddressingMode.IndirectX, AddressingMode.Invalid, AddressingMode.IndirectX, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Implied, AddressingMode.Immediate, AddressingMode.Implied, AddressingMode.Immediate, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Relative, AddressingMode.IndirectY, AddressingMode.Invalid, AddressingMode.IndirectY, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.Implied, AddressingMode.AbsoluteY, AddressingMode.Implied, AddressingMode.AbsoluteY, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.Implied, AddressingMode.IndirectX, AddressingMode.Invalid, AddressingMode.IndirectX, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Implied, AddressingMode.Immediate, AddressingMode.Implied, AddressingMode.Immediate, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Relative, AddressingMode.IndirectY, AddressingMode.Invalid, AddressingMode.IndirectY, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.Implied, AddressingMode.AbsoluteY, AddressingMode.Implied, AddressingMode.AbsoluteY, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.Implied, AddressingMode.IndirectX, AddressingMode.Invalid, AddressingMode.IndirectX, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Implied, AddressingMode.Immediate, AddressingMode.Implied, AddressingMode.Immediate, AddressingMode.Indirect, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Relative, AddressingMode.IndirectY, AddressingMode.Invalid, AddressingMode.IndirectY, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.Implied, AddressingMode.AbsoluteY, AddressingMode.Implied, AddressingMode.AbsoluteY, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.Immediate, AddressingMode.IndirectX, AddressingMode.Immediate, AddressingMode.IndirectX, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Implied, AddressingMode.Immediate, AddressingMode.Implied, AddressingMode.Immediate, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Relative, AddressingMode.IndirectY, AddressingMode.Invalid, AddressingMode.IndirectY, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.ZeroY, AddressingMode.ZeroY, AddressingMode.Implied, AddressingMode.AbsoluteY, AddressingMode.Implied, AddressingMode.AbsoluteY, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.AbsoluteY, AddressingMode.AbsoluteY, AddressingMode.Immediate, AddressingMode.IndirectX, AddressingMode.Immediate, AddressingMode.IndirectX, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Implied, AddressingMode.Immediate, AddressingMode.Implied, AddressingMode.Immediate, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Relative, AddressingMode.IndirectY, AddressingMode.Invalid, AddressingMode.IndirectY, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.ZeroY, AddressingMode.ZeroY, AddressingMode.Implied, AddressingMode.AbsoluteY, AddressingMode.Implied, AddressingMode.AbsoluteY, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.AbsoluteY, AddressingMode.AbsoluteY, AddressingMode.Immediate, AddressingMode.IndirectX, AddressingMode.Immediate, AddressingMode.IndirectX, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Implied, AddressingMode.Immediate, AddressingMode.Implied, AddressingMode.Immediate, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Relative, AddressingMode.IndirectY, AddressingMode.Invalid, AddressingMode.IndirectY, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.Implied, AddressingMode.AbsoluteY, AddressingMode.Implied, AddressingMode.AbsoluteY, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.Immediate, AddressingMode.IndirectX, AddressingMode.Immediate, AddressingMode.IndirectX, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Zero, AddressingMode.Implied, AddressingMode.Immediate, AddressingMode.Implied, AddressingMode.Immediate, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Absolute, AddressingMode.Relative, AddressingMode.IndirectY, AddressingMode.Invalid, AddressingMode.IndirectY, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.ZeroX, AddressingMode.Implied, AddressingMode.AbsoluteY, AddressingMode.Implied, AddressingMode.AbsoluteY, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX, AddressingMode.AbsoluteX };
        ourInstructionProcessorCycleTable = new int[] { 7, 6, 2, 8, 3, 3, 5, 5, 3, 2, 2, 2, 4, 4, 6, 6, 2, 5, 2, 8, 4, 4, 6, 6, 2, 4, 2, 7, 4, 4, 7, 7, 6, 6, 2, 8, 3, 3, 5, 5, 4, 2, 2, 2, 4, 4, 6, 6, 2, 5, 2, 8, 4, 4, 6, 6, 2, 4, 2, 7, 4, 4, 7, 7, 6, 6, 2, 8, 3, 3, 5, 5, 3, 2, 2, 2, 3, 4, 6, 6, 2, 5, 2, 8, 4, 4, 6, 6, 2, 4, 2, 7, 4, 4, 7, 7, 6, 6, 2, 8, 3, 3, 5, 5, 4, 2, 2, 2, 5, 4, 6, 6, 2, 5, 2, 8, 4, 4, 6, 6, 2, 4, 2, 7, 4, 4, 7, 7, 2, 6, 2, 6, 3, 3, 3, 3, 2, 2, 2, 2, 4, 4, 4, 4, 2, 6, 2, 6, 4, 4, 4, 4, 2, 5, 2, 5, 5, 5, 5, 5, 2, 6, 2, 6, 3, 3, 3, 4, 2, 2, 2, 2, 4, 4, 4, 4, 2, 5, 2, 5, 4, 4, 4, 4, 2, 4, 2, 4, 4, 4, 4, 4, 2, 6, 2, 8, 3, 3, 5, 5, 2, 2, 2, 2, 4, 4, 6, 6, 2, 5, 2, 8, 4, 4, 6, 6, 2, 4, 2, 7, 4, 4, 7, 7, 2, 6, 2, 8, 3, 3, 5, 5, 2, 2, 2, 2, 4, 4, 6, 6, 2, 5, 2, 8, 4, 4, 6, 6, 2, 4, 2, 7, 4, 4, 7, 7 };
        ourInstructionPageCrossDelay = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 };
        ourInstructionMnemonicTable = new String[] { "BRK", "ORA", "n/a", "slo", "nop", "ORA", "ASL", "slo", "PHP", "ORA", "ASLA", "anc", "nop", "ORA", "ASL", "slo", "BPL", "ORA", "n/a", "slo", "nop", "ORA", "ASL", "slo", "CLC", "ORA", "nop", "slo", "nop", "ORA", "ASL", "slo", "JSR", "AND", "n/a", "rla", "BIT", "AND", "ROL", "rla", "PLP", "AND", "ROLA", "anc", "BIT", "AND", "ROL", "rla", "BMI", "AND", "n/a", "rla", "nop", "AND", "ROL", "rla", "SEC", "AND", "nop", "rla", "nop", "AND", "ROL", "rla", "RTI", "EOR", "n/a", "sre", "nop", "EOR", "LSR", "sre", "PHA", "EOR", "LSRA", "asr", "JMP", "EOR", "LSR", "sre", "BVC", "EOR", "n/a", "sre", "nop", "EOR", "LSR", "sre", "CLI", "EOR", "nop", "sre", "nop", "EOR", "LSR", "sre", "RTS", "ADC", "n/a", "rra", "nop", "ADC", "ROR", "rra", "PLA", "ADC", "RORA", "arr", "JMP", "ADC", "ROR", "rra", "BVS", "ADC", "n/a", "rra", "nop", "ADC", "ROR", "rra", "SEI", "ADC", "nop", "rra", "nop", "ADC", "ROR", "rra", "nop", "STA", "nop", "sax", "STY", "STA", "STX", "sax", "DEY", "nop", "TXA", "ane", "STY", "STA", "STX", "sax", "BCC", "STA", "n/a", "sha", "STY", "STA", "STX", "sax", "TYA", "STA", "TXS", "shs", "shy", "STA", "shx", "sha", "LDY", "LDA", "LDX", "lax", "LDY", "LDA", "LDX", "lax", "TAY", "LDA", "TAX", "lxa", "LDY", "LDA", "LDX", "lax", "BCS", "LDA", "n/a", "lax", "LDY", "LDA", "LDX", "lax", "CLV", "LDA", "TSX", "las", "LDY", "LDA", "LDX", "lax", "CPY", "CMP", "nop", "dcp", "CPY", "CMP", "DEC", "dcp", "INY", "CMP", "DEX", "sbx", "CPY", "CMP", "DEC", "dcp", "BNE", "CMP", "n/a", "dcp", "nop", "CMP", "DEC", "dcp", "CLD", "CMP", "nop", "dcp", "nop", "CMP", "DEC", "dcp", "CPX", "SBC", "nop", "isb", "CPX", "SBC", "INC", "isb", "INX", "SBC", "NOP", "sbc", "CPX", "SBC", "INC", "isb", "BEQ", "SBC", "n/a", "isb", "nop", "SBC", "INC", "isb", "SED", "SBC", "nop", "isb", "nop", "SBC", "INC", "isb" };
    }
    
    public enum AddressingMode
    {
        Absolute, 
        AbsoluteX, 
        AbsoluteY, 
        Immediate, 
        Implied, 
        Indirect, 
        IndirectX, 
        IndirectY, 
        Invalid, 
        Relative, 
        Zero, 
        ZeroX, 
        ZeroY;
    }
    
    public static class J6507Exception extends Exception
    {
        private static final long serialVersionUID = -6695725589572365475L;
        public String myMessage;
        public ExceptionType myExceptionType;
        
        public J6507Exception(final ExceptionType aType, final String aMsg) {
            super(aMsg);
            this.myMessage = "";
            this.myExceptionType = ExceptionType.UNSPECIFIED;
            this.myExceptionType = aType;
            this.myMessage = aMsg;
        }
        
        public enum ExceptionType
        {
            UNSPECIFIED, 
            INSTRUCTION_NOT_RECOGNIZED;
        }
    }
}
