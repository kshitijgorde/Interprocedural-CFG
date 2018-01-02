import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

public class Shapes
{
    int[][][] top;
    int[][][] left;
    public static int deep;
    
    Shapes(final int n, final int n2) {
        this.top = new int[n + 1][n2 + 1][3];
        this.left = new int[n + 1][n2 + 1][3];
        final int n3 = Global.pieceSize / 2;
        Shapes.deep = Global.pieceSize / 4;
        final int n4 = n3 - Shapes.deep;
        final int n5 = n4 / 4;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= n2; ++j) {
                if (i == 0 || i == n) {
                    this.left[i][j][0] = 0;
                }
                else {
                    this.left[i][j][0] = plusOrMinus() * (Shapes.deep + Global.randomInt(Shapes.deep)) / 2;
                }
                if (j == 0 || j == n2) {
                    this.top[i][j][0] = 0;
                }
                else {
                    this.top[i][j][0] = plusOrMinus() * (Shapes.deep + Global.randomInt(Shapes.deep)) / 2;
                }
                this.top[i][j][1] = n3 - Global.randomInt(n4 - n5) - n5;
                this.top[i][j][2] = n3 + Global.randomInt(n4 - n5) + n5;
                this.left[i][j][1] = n3 - Global.randomInt(n4 - n5) - n5;
                this.left[i][j][2] = n3 + Global.randomInt(n4 - n5) + n5;
            }
        }
    }
    
    public OrMask getOrMask(final int n, final int n2) {
        new Polygon();
        final int pieceSize = Global.pieceSize;
        int deep = Shapes.deep;
        int deep2 = Shapes.deep;
        int n3 = Shapes.deep + pieceSize;
        int n4 = Shapes.deep + pieceSize;
        final int n5 = Global.pieceSize + 2 * Shapes.deep;
        final int[] array = new int[n5 * n5];
        for (int i = 0; i < n5; ++i) {
            for (int j = 0; j < n5; ++j) {
                array[j + i * n5] = 0;
            }
        }
        for (int k = Shapes.deep; k < pieceSize + Shapes.deep; ++k) {
            for (int l = Shapes.deep; l < pieceSize + Shapes.deep; ++l) {
                array[l + k * n5] = -16777216;
            }
        }
        final int n6 = Shapes.deep * (1 + n5);
        if (this.top[n][n2][0] != 0) {
            final int n7 = this.top[n][n2][0];
            final int n8 = this.top[n][n2][1];
            final int n9 = this.top[n][n2][2];
            if (n7 > 0) {
                for (int n10 = 0; n10 < n7 / 2; ++n10) {
                    for (int n11 = n8 + (n9 - n8) / 4; n11 < n9 + (n8 - n9) / 4; ++n11) {
                        array[n6 + n11 + n10 * n5] = 0;
                    }
                }
                for (int n12 = n7 / 2; n12 < n7; ++n12) {
                    for (int n13 = n8; n13 < n9; ++n13) {
                        array[n6 + n13 + n12 * n5] = 0;
                    }
                }
            }
            else {
                for (int n14 = 0; n14 > n7 / 2; --n14) {
                    for (int n15 = n8 + (n9 - n8) / 4; n15 < n9 + (n8 - n9) / 4; ++n15) {
                        array[n6 + n15 + n14 * n5] = -16777216;
                    }
                }
                for (int n16 = n7 / 2; n16 > n7; --n16) {
                    for (int n17 = n8; n17 < n9; ++n17) {
                        array[n6 + n17 + n16 * n5] = -16777216;
                    }
                }
                deep2 += n7;
            }
        }
        if (this.left[n + 1][n2][0] != 0) {
            final int n18 = this.left[n + 1][n2][0];
            final int n19 = this.left[n + 1][n2][1];
            final int n20 = this.left[n + 1][n2][2];
            if (n18 > 0) {
                for (int n21 = n19 + (n20 - n19) / 4; n21 < n20 + (n19 - n20) / 4; ++n21) {
                    for (int n22 = pieceSize; n22 < pieceSize + n18 / 2; ++n22) {
                        array[n6 + n22 + n21 * n5] = -16777216;
                    }
                }
                for (int n23 = n19; n23 < n20; ++n23) {
                    for (int n24 = pieceSize + n18 / 2; n24 < pieceSize + n18; ++n24) {
                        array[n6 + n24 + n23 * n5] = -16777216;
                    }
                }
                n3 += n18;
            }
            else {
                for (int n25 = n19 + (n20 - n19) / 4; n25 < n20 + (n19 - n20) / 4; ++n25) {
                    for (int n26 = pieceSize; n26 > pieceSize + n18 / 2; --n26) {
                        array[n6 + n26 + n25 * n5] = 0;
                    }
                }
                for (int n27 = n19; n27 < n20; ++n27) {
                    for (int n28 = pieceSize + n18 / 2; n28 > pieceSize + n18; --n28) {
                        array[n6 + n28 + n27 * n5] = 0;
                    }
                }
            }
        }
        if (this.top[n][n2 + 1][0] != 0) {
            final int n29 = this.top[n][n2 + 1][0];
            final int n30 = this.top[n][n2 + 1][1];
            final int n31 = this.top[n][n2 + 1][2];
            if (n29 > 0) {
                for (int n32 = pieceSize; n32 < pieceSize + n29 / 2; ++n32) {
                    for (int n33 = n30 + (n31 - n30) / 4; n33 < n31 + (n30 - n31) / 4; ++n33) {
                        array[n6 + n33 + n32 * n5] = -16777216;
                    }
                }
                for (int n34 = pieceSize + n29 / 2; n34 < pieceSize + n29; ++n34) {
                    for (int n35 = n30; n35 < n31; ++n35) {
                        array[n6 + n35 + n34 * n5] = -16777216;
                    }
                }
                n4 += n29;
            }
            else {
                for (int n36 = pieceSize; n36 > pieceSize + n29 / 2; --n36) {
                    for (int n37 = n30 + (n31 - n30) / 4; n37 < n31 + (n30 - n31) / 4; ++n37) {
                        array[n6 + n37 + n36 * n5] = 0;
                    }
                }
                for (int n38 = pieceSize + n29 / 2; n38 > pieceSize + n29; --n38) {
                    for (int n39 = n30; n39 < n31; ++n39) {
                        array[n6 + n39 + n38 * n5] = 0;
                    }
                }
            }
        }
        if (this.left[n][n2][0] != 0) {
            final int n40 = this.left[n][n2][0];
            final int n41 = this.left[n][n2][1];
            final int n42 = this.left[n][n2][2];
            if (n40 > 0) {
                for (int n43 = n41 + (n42 - n41) / 4; n43 < n42 + (n41 - n42) / 4; ++n43) {
                    for (int n44 = 0; n44 < n40 / 2; ++n44) {
                        array[n6 + n44 + n43 * n5] = 0;
                    }
                }
                for (int n45 = n41; n45 < n42; ++n45) {
                    for (int n46 = n40 / 2; n46 < n40; ++n46) {
                        array[n6 + n46 + n45 * n5] = 0;
                    }
                }
            }
            else {
                for (int n47 = n41 + (n42 - n41) / 4; n47 < n42 + (n41 - n42) / 4; ++n47) {
                    for (int n48 = 0; n48 > n40 / 2; --n48) {
                        array[n6 + n48 + n47 * n5] = -16777216;
                    }
                }
                for (int n49 = n41; n49 < n42; ++n49) {
                    for (int n50 = n40 / 2; n50 > n40; --n50) {
                        array[n6 + n50 + n49 * n5] = -16777216;
                    }
                }
                deep += n40;
            }
        }
        return new OrMask(array, 0, 0, deep, deep2, n3, n4);
    }
    
    private static int plusOrMinus() {
        if (Global.randomInt(2) == 0) {
            return 1;
        }
        return -1;
    }
}
