// 
// Decompiled by Procyon v0.5.30
// 

class Connect4Kernel
{
    public static final int EMPTY = 0;
    public static final int COMP = 1;
    public static final int HUMAN = -1;
    public static final int BORDER = 99;
    public static final int GAME_NOT_OVER = 0;
    public static final int ONE_HAS_WON = 1;
    public static final int BOARD_FULL = -1;
    public static final int UNDECIDED = 0;
    public static final int PLAYER_WINS = 1;
    public static final int PLAYER_LOOSES = -1;
    private int[] board;
    private int[] field;
    private int maxDepth;
    private Connect4 c4;
    private int cnt;
    
    public int Cnt() {
        return this.cnt;
    }
    
    private boolean _IsEmpty(final int n) {
        return this.field[n] < 114;
    }
    
    private int _DoMove(final int n, final int n2) {
        final int n3 = this.field[n2];
        this.board[n3] = n;
        final int[] field = this.field;
        field[n2] += 13;
        return n3;
    }
    
    private int _BestMove(final int n, final int[] array, int n2) {
        final int n3 = 10;
        int n4 = -1;
        final int[] array2 = { 0 };
        final int[] array3 = { 0 };
        int n5 = -1;
        array[0] = -1;
        --n2;
        int i = 3;
        array3[0] = 0;
        while (i < n3) {
            if (this._IsEmpty(i)) {
                if (array[0] == -1) {
                    array[0] = i;
                }
                final int n6;
                final int doMove = this._DoMove(n, n6 = i);
                Label_0315: {
                    switch (this.GameOver(doMove, array3)) {
                        case 1: {
                            array[0] = i;
                            i = n3;
                            n4 = 1;
                            break;
                        }
                        case -1: {
                            if (n4 == -1) {
                                array[0] = i;
                                n4 = 0;
                                i = n3;
                                break;
                            }
                            break;
                        }
                        default: {
                            if (n2 != 0) {
                                switch (this._BestMove((n == -1) ? 1 : -1, array2, n2)) {
                                    case -1: {
                                        if (n4 != 1 || array3[0] > n5) {
                                            array[0] = i;
                                            n4 = 1;
                                            n5 = array3[0];
                                            break Label_0315;
                                        }
                                        break Label_0315;
                                    }
                                    case 0: {
                                        if (n4 == -1 || (n4 == 0 && array3[0] > n5)) {
                                            array[0] = i;
                                            n4 = 0;
                                            n5 = array3[0];
                                            break Label_0315;
                                        }
                                        break Label_0315;
                                    }
                                    case 1: {
                                        if (n4 == -1 && array3[0] > n5) {
                                            array[0] = i;
                                            n5 = array3[0];
                                            break Label_0315;
                                        }
                                        break Label_0315;
                                    }
                                }
                            }
                            else {
                                if (n4 == -1 && array3[0] > n5) {
                                    array[0] = i;
                                    n4 = 0;
                                    n5 = array3[0];
                                    break;
                                }
                                break;
                            }
                            break;
                        }
                    }
                }
                this.board[doMove] = 0;
                final int[] field = this.field;
                final int n7 = n6;
                field[n7] -= 13;
            }
            if (n2 == this.maxDepth) {
                final StringBuffer sb = new StringBuffer(30);
                sb.append("Analyzing");
                for (int j = 3; j < i; ++j) {
                    sb.append(".");
                }
                this.c4.DrawStatus(sb.toString());
            }
            ++i;
            ++this.cnt;
        }
        return n4;
    }
    
    private int _Field(final int n, final int n2) {
        return n * 13 + n2;
    }
    
    private int _Column(final int n) {
        return n % 13;
    }
    
    private int _Line(final int n) {
        return n / 13;
    }
    
    public Connect4Kernel(final Connect4 c4) {
        this.board = new int[156];
        this.field = new int[13];
        this.c4 = c4;
        for (int i = 0; i < 156; ++i) {
            if (this._Column(i) > 2 && this._Column(i) < 10 && this._Line(i) > 2 && this._Line(i) < 9) {
                this.board[i] = 0;
            }
            else {
                this.board[i] = 99;
            }
        }
        for (int j = 0; j < 13; ++j) {
            this.field[j] = 39 + j;
        }
    }
    
    public boolean IsEmpty(final int n) {
        return n >= 0 && n < 7 && this._IsEmpty(n + 3);
    }
    
    public int GameOver(int n, final int[] array) {
        final int n2 = this.board[n];
        int n6;
        int n5;
        int n4;
        int n3 = n4 = (n5 = (n6 = 1));
        int n10;
        int n9;
        int n8;
        int n7 = n8 = (n9 = (n10 = 0));
        int n11 = n;
        int n12 = n;
        int n13 = n;
        int n14 = n;
        int n15 = n;
        int n16 = n;
        int n32;
        int n31;
        int n30;
        int n29;
        int n28;
        int n27;
        int n26;
        int n25;
        int n24;
        int n23;
        int n22;
        int n21;
        int n20;
        int n19;
        int n18;
        int n17 = n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = (n32 = 1))))))))))))));
        if (n2 == 0 || n2 == 99) {
            return 0;
        }
        final int n33 = (this.board[n] == -1) ? 1 : -1;
        for (int i = 1; i < 4; ++i) {
            if (n19 != 0) {
                final int n34;
                if ((n34 = this.board[++n]) != 99) {
                    if (n18 != 0) {
                        if (n34 == n2) {
                            ++n4;
                        }
                        else {
                            n18 = 0;
                            if (n34 == 0) {
                                ++n8;
                            }
                            else {
                                n19 = 0;
                            }
                        }
                    }
                    else if (n34 != n33) {
                        ++n8;
                    }
                    else {
                        n19 = 0;
                    }
                }
                else {
                    n19 = 0;
                }
            }
            if (n20 != 0) {
                final int n35;
                if ((n35 = this.board[--n]) != 99) {
                    if (n17 != 0) {
                        if (n35 == n2) {
                            ++n4;
                        }
                        else {
                            n17 = 0;
                            if (n35 == 0) {
                                ++n8;
                            }
                            else {
                                n20 = 0;
                            }
                        }
                    }
                    else if (n35 != n33) {
                        ++n8;
                    }
                    else {
                        n20 = 0;
                    }
                }
                else {
                    n20 = 0;
                }
            }
            if (n23 != 0) {
                final int[] board = this.board;
                n13 += 14;
                final int n36;
                if ((n36 = board[n13]) != 99) {
                    if (n21 != 0) {
                        if (n36 == n2) {
                            ++n3;
                        }
                        else {
                            n21 = 0;
                            if (n36 == 0) {
                                ++n7;
                            }
                            else {
                                n23 = 0;
                            }
                        }
                    }
                    else if (n36 != n33) {
                        ++n7;
                    }
                    else {
                        n23 = 0;
                    }
                }
                else {
                    n23 = 0;
                }
            }
            if (n24 != 0) {
                final int[] board2 = this.board;
                n12 -= 14;
                final int n37;
                if ((n37 = board2[n12]) != 99) {
                    if (n22 != 0) {
                        if (n37 == n2) {
                            ++n3;
                        }
                        else {
                            n22 = 0;
                            if (n37 == 0) {
                                ++n7;
                            }
                            else {
                                n24 = 0;
                            }
                        }
                    }
                    else if (n37 != n33) {
                        ++n7;
                    }
                    else {
                        n24 = 0;
                    }
                }
                else {
                    n24 = 0;
                }
            }
            if (n27 != 0) {
                final int[] board3 = this.board;
                n14 += 12;
                final int n38;
                if ((n38 = board3[n14]) != 99) {
                    if (n25 != 0) {
                        if (n38 == n2) {
                            ++n5;
                        }
                        else {
                            n25 = 0;
                            if (n38 == 0) {
                                ++n9;
                            }
                            else {
                                n27 = 0;
                            }
                        }
                    }
                    else if (n38 != n33) {
                        ++n9;
                    }
                    else {
                        n27 = 0;
                    }
                }
                else {
                    n27 = 0;
                }
            }
            if (n28 != 0) {
                final int[] board4 = this.board;
                n11 -= 12;
                final int n39;
                if ((n39 = board4[n11]) != 99) {
                    if (n26 != 0) {
                        if (n39 == n2) {
                            ++n5;
                        }
                        else {
                            n26 = 0;
                            if (n39 == 0) {
                                ++n9;
                            }
                            else {
                                n28 = 0;
                            }
                        }
                    }
                    else if (n39 != n33) {
                        ++n9;
                    }
                    else {
                        n28 = 0;
                    }
                }
                else {
                    n28 = 0;
                }
            }
            if (n31 != 0) {
                final int[] board5 = this.board;
                n16 += 13;
                final int n40;
                if ((n40 = board5[n16]) != 99) {
                    if (n29 != 0) {
                        if (n40 == n2) {
                            ++n6;
                        }
                        else {
                            n29 = 0;
                            if (n40 == 0) {
                                ++n10;
                            }
                            else {
                                n31 = 0;
                            }
                        }
                    }
                    else if (n40 != n33) {
                        ++n10;
                    }
                    else {
                        n31 = 0;
                    }
                }
                else {
                    n31 = 0;
                }
            }
            if (n32 != 0) {
                final int[] board6 = this.board;
                n15 -= 13;
                final int n41;
                if ((n41 = board6[n15]) != 99) {
                    if (n30 != 0) {
                        if (n41 == n2) {
                            ++n6;
                        }
                        else {
                            n30 = 0;
                            if (n41 == 0) {
                                ++n10;
                            }
                            else {
                                n32 = 0;
                            }
                        }
                    }
                    else if (n41 != n33) {
                        ++n10;
                    }
                    else {
                        n32 = 0;
                    }
                }
                else {
                    n32 = 0;
                }
            }
        }
        array[0] = 0;
        if (n8 + n4 > 3) {
            array[0] = (n4 - 1) * 6 + n8;
        }
        if (n7 + n3 > 3) {
            final int n42 = 0;
            array[n42] += (n3 - 1) * 6 + n7;
        }
        if (n9 + n5 > 3) {
            final int n43 = 0;
            array[n43] += (n5 - 1) * 6 + n9;
        }
        if (n10 + n6 > 3) {
            final int n44 = 0;
            array[n44] += (n6 - 1) * 6 + n10;
        }
        if (n4 > 3 || n3 > 3 || n5 > 3 || n6 > 3) {
            return 1;
        }
        for (int j = 3; j < 10; ++j) {
            if (this.field[j] < 156) {
                return 0;
            }
        }
        return -1;
    }
    
    public int DoMove(final int n, final int n2) {
        int doMove = -1;
        if (this.IsEmpty(n2)) {
            doMove = this._DoMove(n, n2 + 3);
        }
        return doMove;
    }
    
    public int BestMove(final int n, final int[] array, final int n2) {
        this.maxDepth = n2 - 1;
        this.cnt = 0;
        final int bestMove = this._BestMove(n, array, n2);
        final int n3 = 0;
        array[n3] -= 3;
        return bestMove;
    }
    
    public int Field(final int n, final int n2) {
        return (n + 3) * 13 + n2 + 3;
    }
    
    public int Column(final int n) {
        return this._Column(n) - 3;
    }
    
    public int Line(final int n) {
        return this._Line(n) - 3;
    }
    
    public char F(final int n, final int n2) {
        switch (this.board[this.Field(n, n2)]) {
            case 0: {
                return ' ';
            }
            case 1: {
                return 'X';
            }
            default: {
                return '0';
            }
        }
    }
    
    public int FieldState(final int n, final int n2) {
        return this.board[this.Field(n, n2)];
    }
}
