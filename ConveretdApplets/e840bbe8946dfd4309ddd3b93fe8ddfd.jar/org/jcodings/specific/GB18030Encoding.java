// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.specific;

import org.jcodings.IntHolder;
import org.jcodings.ascii.AsciiTables;
import org.jcodings.MultiByteEncoding;

public final class GB18030Encoding extends MultiByteEncoding
{
    private static final int C1 = 0;
    private static final int C2 = 1;
    private static final int C4 = 2;
    private static final int CM = 3;
    private static final int[] GB18030_MAP;
    private static final int[][] GB18030Trans;
    public static final GB18030Encoding INSTANCE;
    
    protected GB18030Encoding() {
        super("GB18030", 1, 4, null, GB18030Encoding.GB18030Trans, AsciiTables.AsciiCtypeTable);
    }
    
    public int length(final byte[] bytes, final int p, final int end) {
        final int s = this.TransZero[bytes[p] & 0xFF];
        if (s < 0) {
            return (s == -1) ? 1 : -1;
        }
        return this.lengthForTwoUptoFour(bytes, p, end, s);
    }
    
    private int lengthForTwoUptoFour(final byte[] bytes, int p, final int end, int s) {
        if (++p == end) {
            return this.missing(1);
        }
        s = this.Trans[s][bytes[p] & 0xFF];
        if (s < 0) {
            return (s == -1) ? 2 : -1;
        }
        return this.lengthForThreeUptoFour(bytes, p, end, s);
    }
    
    private int lengthForThreeUptoFour(final byte[] bytes, int p, final int end, int s) {
        if (++p == end) {
            return this.missing(2);
        }
        s = this.Trans[s][bytes[p] & 0xFF];
        if (s < 0) {
            return (s == -1) ? 3 : -1;
        }
        if (++p == end) {
            return this.missing(1);
        }
        s = this.Trans[s][bytes[p] & 0xFF];
        return (s == -1) ? 4 : -1;
    }
    
    public int mbcToCode(final byte[] bytes, final int p, final int end) {
        return this.mbnMbcToCode(bytes, p, end);
    }
    
    public int codeToMbcLength(final int code) {
        return this.mb4CodeToMbcLength(code);
    }
    
    public int codeToMbc(final int code, final byte[] bytes, final int p) {
        return this.mb4CodeToMbc(code, bytes, p);
    }
    
    public int mbcCaseFold(final int flag, final byte[] bytes, final IntHolder pp, final int end, final byte[] lower) {
        return this.mbnMbcCaseFold(flag, bytes, pp, end, lower);
    }
    
    public boolean isCodeCType(final int code, final int ctype) {
        return this.mb4IsCodeCType(code, ctype);
    }
    
    public int[] ctypeCodeRange(final int ctype, final IntHolder sbOut) {
        return null;
    }
    
    public int leftAdjustCharHead(final byte[] bytes, final int p, final int s, final int end) {
        State state = State.START;
        for (int p_ = s; p_ >= p; --p_) {
            switch (state) {
                case START: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0: {
                            return s;
                        }
                        case 1: {
                            state = State.One_C2;
                            break;
                        }
                        case 2: {
                            state = State.One_C4;
                            break;
                        }
                        case 3: {
                            state = State.One_CM;
                            break;
                        }
                    }
                    break;
                }
                case One_C2: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1:
                        case 2: {
                            return s;
                        }
                        case 3: {
                            state = State.Odd_CM_One_CX;
                            break;
                        }
                    }
                    break;
                }
                case One_C4: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1:
                        case 2: {
                            return s;
                        }
                        case 3: {
                            state = State.One_CMC4;
                            break;
                        }
                    }
                    break;
                }
                case One_CM: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1: {
                            return s;
                        }
                        case 2: {
                            state = State.Odd_C4CM;
                            break;
                        }
                        case 3: {
                            state = State.Odd_CM_One_CX;
                            break;
                        }
                    }
                    break;
                }
                case Odd_CM_One_CX: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1:
                        case 2: {
                            return s - 1;
                        }
                        case 3: {
                            state = State.Even_CM_One_CX;
                            break;
                        }
                    }
                    break;
                }
                case Even_CM_One_CX: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1:
                        case 2: {
                            return s;
                        }
                        case 3: {
                            state = State.Odd_CM_One_CX;
                            break;
                        }
                    }
                    break;
                }
                case One_CMC4: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1: {
                            return s - 1;
                        }
                        case 2: {
                            state = State.One_C4_Odd_CMC4;
                            break;
                        }
                        case 3: {
                            state = State.Even_CM_One_CX;
                            break;
                        }
                    }
                    break;
                }
                case Odd_CMC4: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1: {
                            return s - 1;
                        }
                        case 2: {
                            state = State.One_C4_Odd_CMC4;
                            break;
                        }
                        case 3: {
                            state = State.Odd_CM_Odd_CMC4;
                            break;
                        }
                    }
                    break;
                }
                case One_C4_Odd_CMC4: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1:
                        case 2: {
                            return s - 1;
                        }
                        case 3: {
                            state = State.Even_CMC4;
                            break;
                        }
                    }
                    break;
                }
                case Even_CMC4: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1: {
                            return s - 3;
                        }
                        case 2: {
                            state = State.One_C4_Even_CMC4;
                            break;
                        }
                        case 3: {
                            state = State.Odd_CM_Even_CMC4;
                            break;
                        }
                    }
                    break;
                }
                case One_C4_Even_CMC4: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1:
                        case 2: {
                            return s - 3;
                        }
                        case 3: {
                            state = State.Odd_CMC4;
                            break;
                        }
                    }
                    break;
                }
                case Odd_CM_Odd_CMC4: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1:
                        case 2: {
                            return s - 3;
                        }
                        case 3: {
                            state = State.Even_CM_Odd_CMC4;
                            break;
                        }
                    }
                    break;
                }
                case Even_CM_Odd_CMC4: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1:
                        case 2: {
                            return s - 1;
                        }
                        case 3: {
                            state = State.Odd_CM_Odd_CMC4;
                            break;
                        }
                    }
                    break;
                }
                case Odd_CM_Even_CMC4: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1:
                        case 2: {
                            return s - 1;
                        }
                        case 3: {
                            state = State.Even_CM_Even_CMC4;
                            break;
                        }
                    }
                    break;
                }
                case Even_CM_Even_CMC4: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1:
                        case 2: {
                            return s - 3;
                        }
                        case 3: {
                            state = State.Odd_CM_Even_CMC4;
                            break;
                        }
                    }
                    break;
                }
                case Odd_C4CM: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1:
                        case 2: {
                            return s;
                        }
                        case 3: {
                            state = State.One_CM_Odd_C4CM;
                            break;
                        }
                    }
                    break;
                }
                case One_CM_Odd_C4CM: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1: {
                            return s - 2;
                        }
                        case 2: {
                            state = State.Even_C4CM;
                            break;
                        }
                        case 3: {
                            state = State.Even_CM_Odd_C4CM;
                            break;
                        }
                    }
                    break;
                }
                case Even_C4CM: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1:
                        case 2: {
                            return s - 2;
                        }
                        case 3: {
                            state = State.One_CM_Even_C4CM;
                            break;
                        }
                    }
                    break;
                }
                case One_CM_Even_C4CM: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1: {
                            return s - 0;
                        }
                        case 2: {
                            state = State.Odd_C4CM;
                            break;
                        }
                        case 3: {
                            state = State.Even_CM_Even_C4CM;
                            break;
                        }
                    }
                    break;
                }
                case Even_CM_Odd_C4CM: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1:
                        case 2: {
                            return s - 0;
                        }
                        case 3: {
                            state = State.Odd_CM_Odd_C4CM;
                            break;
                        }
                    }
                    break;
                }
                case Odd_CM_Odd_C4CM: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1:
                        case 2: {
                            return s - 2;
                        }
                        case 3: {
                            state = State.Even_CM_Odd_C4CM;
                            break;
                        }
                    }
                    break;
                }
                case Even_CM_Even_C4CM: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1:
                        case 2: {
                            return s - 2;
                        }
                        case 3: {
                            state = State.Odd_CM_Even_C4CM;
                            break;
                        }
                    }
                    break;
                }
                case Odd_CM_Even_C4CM: {
                    switch (GB18030Encoding.GB18030_MAP[bytes[p_] & 0xFF]) {
                        case 0:
                        case 1:
                        case 2: {
                            return s - 0;
                        }
                        case 3: {
                            state = State.Even_CM_Even_C4CM;
                            continue;
                        }
                    }
                    break;
                }
            }
        }
        switch (state) {
            case START: {
                return s - 0;
            }
            case One_C2: {
                return s - 0;
            }
            case One_C4: {
                return s - 0;
            }
            case One_CM: {
                return s - 0;
            }
            case Odd_CM_One_CX: {
                return s - 1;
            }
            case Even_CM_One_CX: {
                return s - 0;
            }
            case One_CMC4: {
                return s - 1;
            }
            case Odd_CMC4: {
                return s - 1;
            }
            case One_C4_Odd_CMC4: {
                return s - 1;
            }
            case Even_CMC4: {
                return s - 3;
            }
            case One_C4_Even_CMC4: {
                return s - 3;
            }
            case Odd_CM_Odd_CMC4: {
                return s - 3;
            }
            case Even_CM_Odd_CMC4: {
                return s - 1;
            }
            case Odd_CM_Even_CMC4: {
                return s - 1;
            }
            case Even_CM_Even_CMC4: {
                return s - 3;
            }
            case Odd_C4CM: {
                return s - 0;
            }
            case One_CM_Odd_C4CM: {
                return s - 2;
            }
            case Even_C4CM: {
                return s - 2;
            }
            case One_CM_Even_C4CM: {
                return s - 0;
            }
            case Even_CM_Odd_C4CM: {
                return s - 0;
            }
            case Odd_CM_Odd_C4CM: {
                return s - 2;
            }
            case Even_CM_Even_C4CM: {
                return s - 2;
            }
            case Odd_CM_Even_C4CM: {
                return s - 0;
            }
            default: {
                return s;
            }
        }
    }
    
    public boolean isReverseMatchAllowed(final byte[] bytes, final int p, final int end) {
        return GB18030Encoding.GB18030_MAP[bytes[p] & 0xFF] == 0;
    }
    
    static {
        GB18030_MAP = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0 };
        GB18030Trans = new int[][] { { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -2 }, { -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, -2, -2, -2, -2, -2, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2 }, { -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, -2 }, { -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2 } };
        INSTANCE = new GB18030Encoding();
    }
    
    private enum State
    {
        START, 
        One_C2, 
        One_C4, 
        One_CM, 
        Odd_CM_One_CX, 
        Even_CM_One_CX, 
        One_CMC4, 
        Odd_CMC4, 
        One_C4_Odd_CMC4, 
        Even_CMC4, 
        One_C4_Even_CMC4, 
        Odd_CM_Odd_CMC4, 
        Even_CM_Odd_CMC4, 
        Odd_CM_Even_CMC4, 
        Even_CM_Even_CMC4, 
        Odd_C4CM, 
        One_CM_Odd_C4CM, 
        Even_C4CM, 
        One_CM_Even_C4CM, 
        Even_CM_Odd_C4CM, 
        Odd_CM_Odd_C4CM, 
        Even_CM_Even_C4CM, 
        Odd_CM_Even_C4CM;
    }
}
