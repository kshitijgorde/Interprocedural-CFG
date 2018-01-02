import java.math.BigInteger;

// 
// Decompiled by Procyon v0.5.30
// 

public final class expression
{
    private static final BigInteger BigInt0;
    private static final BigInteger BigInt1;
    private static final BigInteger BigInt2;
    private static final BigInteger BigInt3;
    
    public static int ComputeExpression(final String s, final int n, final BigInteger[] array) {
        final BigInteger value = BigInteger.valueOf(1L);
        int n2 = 0;
        int i = 0;
        final int length = s.length();
        int n3 = 0;
        final BigInteger[] array2 = new BigInteger[400];
        final int[] array3 = new int[400];
        while (i < length) {
            int char1 = s.charAt(i);
            if (char1 == 33) {
                if (n3 == 0) {
                    return -6;
                }
                if (array2[n2].bitLength() - 1 > 16) {
                    return -3;
                }
                final int intValue = array2[n2].intValue();
                if (intValue < 0 || intValue > 5984) {
                    return -3;
                }
                BigInteger multiply = value;
                for (int j = 2; j <= intValue; ++j) {
                    multiply = multiply.multiply(BigInteger.valueOf(j));
                }
                array2[n2] = multiply;
            }
            if (char1 == 35) {
                if (n3 == 0) {
                    return -6;
                }
                if (array2[n2].bitLength() - 1 > 16) {
                    return -3;
                }
                final int intValue2 = array2[n2].intValue();
                if (intValue2 < 0 || intValue2 > 46049) {
                    return -3;
                }
                for (int n4 = 2; n4 * n4 <= intValue2; ++n4) {
                    if (intValue2 / n4 * n4 == intValue2) {
                        return -8;
                    }
                }
                BigInteger multiply2 = value;
                int k = 2;
            Label_0305:
                while (k <= intValue2) {
                    while (true) {
                        for (int n5 = 2; n5 * n5 <= k; ++n5) {
                            if (k / n5 * n5 == k) {
                                ++k;
                                continue Label_0305;
                            }
                        }
                        multiply2 = multiply2.multiply(BigInteger.valueOf(k));
                        continue;
                    }
                }
                array2[n2] = multiply2;
            }
            if (char1 == 66 || char1 == 98 || char1 == 78 || char1 == 110 || char1 == 70 || char1 == 102 || char1 == 80 || char1 == 112 || char1 == 76 || char1 == 108) {
                if (n3 != 0 || i == length - 1) {
                    return -6;
                }
                ++i;
                if (s.charAt(i) != '(') {
                    return -6;
                }
                if (n2 > 395) {
                    return -7;
                }
                array3[n2++] = (char1 & 0xDF);
                char1 = 40;
            }
            if (char1 == 43 || char1 == 45) {
                if (n3 == 0) {
                    ++i;
                    if (char1 == 43) {
                        continue;
                    }
                    if (n2 > 0 && array3[n2 - 1] == 95) {
                        --n2;
                        continue;
                    }
                    if (n2 > 395) {
                        return -7;
                    }
                    array3[n2++] = 95;
                    continue;
                }
                else {
                    if (n2 > 0 && array3[n2 - 1] != 40) {
                        final int computeSubExpr;
                        if ((computeSubExpr = ComputeSubExpr(--n2, array2, array3)) != 0) {
                            return computeSubExpr;
                        }
                        if (n2 > 0 && array3[n2 - 1] != 40) {
                            final int computeSubExpr2;
                            if ((computeSubExpr2 = ComputeSubExpr(--n2, array2, array3)) != 0) {
                                return computeSubExpr2;
                            }
                            final int computeSubExpr3;
                            if (n2 > 0 && array3[n2 - 1] != 40 && (computeSubExpr3 = ComputeSubExpr(--n2, array2, array3)) != 0) {
                                return computeSubExpr3;
                            }
                        }
                    }
                    array3[n2++] = char1;
                    n3 = 0;
                }
            }
            else if (char1 == 42 || char1 == 47 || char1 == 37) {
                if (n3 == 0) {
                    return -6;
                }
                if (n2 > 0 && (array3[n2 - 1] == 94 || array3[n2 - 1] == 42 || array3[n2 - 1] == 47 || array3[n2 - 1] == 37 || array3[n2 - 1] == 66 || array3[n2 - 1] == 78 || array3[n2 - 1] == 70 || array3[n2 - 1] == 76 || array3[n2 - 1] == 80)) {
                    final int computeSubExpr4;
                    if ((computeSubExpr4 = ComputeSubExpr(--n2, array2, array3)) != 0) {
                        return computeSubExpr4;
                    }
                    final int computeSubExpr5;
                    if (n2 > 0 && (array3[n2 - 1] == 94 || array3[n2 - 1] == 42 || array3[n2 - 1] == 47 || array3[n2 - 1] == 37 || array3[n2 - 1] == 66 || array3[n2 - 1] == 78 || array3[n2 - 1] == 70 || array3[n2 - 1] == 76 || array3[n2 - 1] == 80) && (computeSubExpr5 = ComputeSubExpr(--n2, array2, array3)) != 0) {
                        return computeSubExpr5;
                    }
                }
                array3[n2++] = char1;
                n3 = 0;
            }
            else if (char1 == 94) {
                if (n3 == 0) {
                    return -6;
                }
                final int computeSubExpr6;
                if (n2 > 0 && (array3[n2 - 1] == 94 || array3[n2 - 1] == 66 || array3[n2 - 1] == 78 || array3[n2 - 1] == 70 || array3[n2 - 1] == 76 || array3[n2 - 1] == 80) && (computeSubExpr6 = ComputeSubExpr(--n2, array2, array3)) != 0) {
                    return computeSubExpr6;
                }
                array3[n2++] = char1;
                n3 = 0;
            }
            else if (char1 == 40) {
                if (n3 == 1) {
                    return -6;
                }
                if (n2 > 395) {
                    return -7;
                }
                array3[n2++] = char1;
            }
            else if (char1 == 41) {
                if (n3 == 0) {
                    return -6;
                }
                if (n2 > 0 && array3[n2 - 1] != 40) {
                    final int computeSubExpr7;
                    if ((computeSubExpr7 = ComputeSubExpr(--n2, array2, array3)) != 0) {
                        return computeSubExpr7;
                    }
                    if (n2 > 0 && array3[n2 - 1] != 40) {
                        final int computeSubExpr8;
                        if ((computeSubExpr8 = ComputeSubExpr(--n2, array2, array3)) != 0) {
                            return computeSubExpr8;
                        }
                        final int computeSubExpr9;
                        if (n2 > 0 && array3[n2 - 1] != 40 && (computeSubExpr9 = ComputeSubExpr(--n2, array2, array3)) != 0) {
                            return computeSubExpr9;
                        }
                    }
                }
                if (n2 == 0) {
                    return -5;
                }
                --n2;
                array2[n2] = array2[n2 + 1];
                n3 = 1;
            }
            else if (char1 >= 48 && char1 <= 57) {
                int l;
                for (l = i; l < length - 1; ++l) {
                    final char char2 = s.charAt(l + 1);
                    if (char2 < '0' || char2 > '9') {
                        break;
                    }
                }
                array2[n2] = new BigInteger(s.substring(i, l + 1));
                n3 = 1;
                i = l;
            }
            ++i;
        }
        if (n3 == 0) {
            return -6;
        }
        if (n2 > 0 && array3[n2 - 1] != 40) {
            final int computeSubExpr10;
            if ((computeSubExpr10 = ComputeSubExpr(--n2, array2, array3)) != 0) {
                return computeSubExpr10;
            }
            if (n2 > 0 && array3[n2 - 1] != 40) {
                final int computeSubExpr11;
                if ((computeSubExpr11 = ComputeSubExpr(--n2, array2, array3)) != 0) {
                    return computeSubExpr11;
                }
                final int computeSubExpr12;
                if (n2 > 0 && array3[n2 - 1] != 40 && (computeSubExpr12 = ComputeSubExpr(--n2, array2, array3)) != 0) {
                    return computeSubExpr12;
                }
            }
        }
        if (n2 != 0) {
            return -5;
        }
        if (array2[0].compareTo(value) <= 0 && n == 0) {
            return -1;
        }
        if (array2[0].bitLength() > 33219) {
            return -2;
        }
        array[0] = array2[0];
        return 0;
    }
    
    private static int ComputeSubExpr(final int n, final BigInteger[] array, final int[] array2) {
        final long n2 = Long.MIN_VALUE;
        final int n3 = array2[n];
        switch (n3) {
            case 43: {
                array[n] = array[n].add(array[n + 1]);
                return 0;
            }
            case 45: {
                array[n] = array[n].subtract(array[n + 1]);
                return 0;
            }
            case 95: {
                array[n] = array[n + 1].negate();
                return 0;
            }
            case 47: {
                if (array[n + 1].signum() == 0) {
                    return -3;
                }
                if (array[n].remainder(array[n + 1]).signum() != 0) {
                    return -4;
                }
                array[n] = array[n].divide(array[n + 1]);
                return 0;
            }
            case 37: {
                if (array[n + 1].signum() != 0) {
                    array[n] = array[n].remainder(array[n + 1]);
                }
                return 0;
            }
            case 42: {
                if (array[n].bitLength() + array[n + 1].bitLength() > 66438) {
                    return -3;
                }
                array[n] = array[n].multiply(array[n + 1]);
                return 0;
            }
            case 94: {
                final int n4 = array[n].bitLength() - 1;
                double n5;
                if (n4 > 32) {
                    n5 = n4 - 32 + Math.log(array[n].shiftRight(n4 - 32).doubleValue()) / Math.log(2.0);
                }
                else {
                    n5 = Math.log(array[n].doubleValue()) / Math.log(2.0);
                }
                if (n5 * array[n + 1].doubleValue() > 66438.0) {
                    return -3;
                }
                array[n] = array[n].pow(array[n + 1].intValue());
                return 0;
            }
            case 70:
            case 76: {
                if (array[n + 1].bitLength() - 1 > 17) {
                    return -3;
                }
                final int intValue = array[n + 1].intValue();
                if (intValue > 95662) {
                    return -3;
                }
                if (intValue < 0) {
                    return -8;
                }
                BigInteger value = BigInteger.valueOf((n3 == 76) ? -1 : 1);
                BigInteger value2 = BigInteger.valueOf((n3 == 76) ? 2 : 0);
                for (int i = 1; i <= intValue; ++i) {
                    final BigInteger add = value.add(value2);
                    value = value2;
                    value2 = add;
                }
                array[n] = value2;
                return 0;
            }
            case 80: {
                if (array[n + 1].bitLength() - 1 > 24) {
                    return -3;
                }
                final int intValue2 = array[n + 1].intValue();
                if (intValue2 > 3520000) {
                    return -3;
                }
                if (intValue2 < 0) {
                    return -8;
                }
                int n6 = 2;
                final double n7 = 0.05782275873960949;
                final double n8 = 0.9563674804631159;
                final double n9 = 0.022542110013890053;
                final int intValue3 = array[n + 1].intValue();
                for (int j = 1; j <= intValue3; ++j) {
                    n6 += (int)Math.floor(n7 * Math.sqrt(j) + n8 - Math.log(j) * n9);
                }
                final long[] array3 = new long[n6];
                final int[] array4 = new int[intValue3 + 2];
                array3[0] = n2 + 1L;
                array4[0] = 0;
                int n11;
                int n10 = array4[1] = (n11 = 1);
                for (int k = 1; k <= intValue3; ++k) {
                    n11 = array4[k] - array4[k - 1] + 1;
                    n10 = array4[k] + n11;
                    for (int l = array4[k]; l < n10; ++l) {
                        array3[l] = n2;
                    }
                    for (int n12 = 1; (3 * n12 - 1) * n12 <= 2 * k; ++n12) {
                        int n13 = array4[k - (3 * n12 - 1) * n12 / 2];
                        int n14 = array4[k - (3 * n12 - 1) * n12 / 2 + 1];
                        for (int n15 = ((3 * n12 + 1) * n12 > 2 * k) ? 1 : 0; n15 <= 1; ++n15) {
                            long n16 = n2;
                            int n17 = array4[k];
                            if (n12 % 2 == 0) {
                                for (int n18 = n13; n18 < n14; ++n18) {
                                    final long n19 = n16 + array3[n17] - array3[n18];
                                    n16 = ((n19 > array3[n17] || (n19 == array3[n17] && n16 != n2)) ? (n2 - 1L) : n2);
                                    array3[n17] = n19;
                                    ++n17;
                                }
                                while (n17 < n10) {
                                    final long n20 = n16 + array3[n17] + n2;
                                    n16 = ((n20 > array3[n17] || (n20 == array3[n17] && n16 != n2)) ? (n2 - 1L) : n2);
                                    array3[n17] = n20;
                                    ++n17;
                                }
                            }
                            else {
                                for (int n21 = n13; n21 < n14; ++n21) {
                                    final long n22 = n16 + array3[n17] + array3[n21];
                                    n16 = ((n22 < array3[n17] || n22 < array3[n21]) ? (n2 + 1L) : n2);
                                    array3[n17] = n22;
                                    ++n17;
                                }
                                while (n17 < n10) {
                                    final long n23 = n16 + array3[n17] + n2;
                                    n16 = ((n23 < array3[n17]) ? (n2 + 1L) : n2);
                                    array3[n17] = n23;
                                    ++n17;
                                }
                            }
                            if (n15 == 0) {
                                n13 = array4[k - (3 * n12 + 1) * n12 / 2];
                                n14 = array4[k - (3 * n12 + 1) * n12 / 2 + 1];
                            }
                        }
                    }
                    if (array3[n10 - 1] == n2) {
                        --n11;
                        --n10;
                    }
                    array4[k + 1] = n10;
                }
                int n24 = n11 * 8;
                final byte[] array5 = new byte[n24 + 1];
                for (int n25 = array4[intValue3]; n25 < n10; ++n25) {
                    array5[n24] = (byte)(array3[n25] & 0xFFL);
                    array5[n24 - 1] = (byte)(array3[n25] >>> 8 & 0xFFL);
                    array5[n24 - 2] = (byte)(array3[n25] >>> 16 & 0xFFL);
                    array5[n24 - 3] = (byte)(array3[n25] >>> 24 & 0xFFL);
                    array5[n24 - 4] = (byte)(array3[n25] >>> 32 & 0xFFL);
                    array5[n24 - 5] = (byte)(array3[n25] >>> 40 & 0xFFL);
                    array5[n24 - 6] = (byte)(array3[n25] >>> 48 & 0xFFL);
                    array5[n24 - 7] = (byte)((array3[n25] >>> 56 & 0xFFL) ^ 0x80L);
                    n24 -= 8;
                }
                array5[0] = 0;
                array[n] = new BigInteger(array5);
                break;
            }
            case 66:
            case 78: {
                BigInteger bigInteger;
                if (n3 == 66) {
                    final int compareTo = array[n + 1].compareTo(expression.BigInt3);
                    if (compareTo < 0) {
                        return -8;
                    }
                    if (compareTo == 0) {
                        array[n] = expression.BigInt2;
                        return 0;
                    }
                    bigInteger = array[n + 1].subtract(expression.BigInt2).or(expression.BigInt1);
                }
                else {
                    if (array[n + 1].compareTo(expression.BigInt2) < 0) {
                        return -8;
                    }
                    bigInteger = array[n + 1].add(expression.BigInt1).or(expression.BigInt1);
                }
                while (true) {
                    Label_1834: {
                        if (bigInteger.bitLength() < 16) {
                            final int intValue4 = bigInteger.intValue();
                            if (intValue4 >= 9) {
                                for (int n26 = 3; n26 * n26 <= intValue4; n26 += 2) {
                                    if (intValue4 % n26 == 0) {
                                        break Label_1834;
                                    }
                                }
                                break;
                            }
                            break;
                        }
                        else {
                            int n27 = 100;
                            do {
                                int n28 = 3;
                                if (bigInteger.mod(BigInteger.valueOf(n28)).signum() == 0) {
                                    break Label_1834;
                                }
                                Label_1610: {
                                    break Label_1610;
                                    int n29 = 0;
                                    do {
                                        if (n28 % n29 != 0) {
                                            n29 += 2;
                                        }
                                        else {
                                            n28 += 2;
                                            n29 = 3;
                                        }
                                    } while (n29 * n29 <= n28);
                                }
                                if (bigInteger.mod(BigInteger.valueOf(n28)).signum() != 0) {
                                    continue;
                                }
                                break Label_1834;
                            } while (--n27 > 0);
                            final BigInteger subtract = bigInteger.subtract(expression.BigInt1);
                            final int lowestSetBit = subtract.getLowestSetBit();
                            int n30 = 20;
                        Label_1818:
                            do {
                                int n31 = 3;
                                Label_1689: {
                                    break Label_1689;
                                    int n32 = 0;
                                    do {
                                        if (n31 % n32 != 0) {
                                            n32 += 2;
                                        }
                                        else {
                                            n31 += 2;
                                            n32 = 3;
                                        }
                                    } while (n32 * n32 <= n31);
                                }
                                BigInteger bigInteger2 = BigInteger.valueOf(n31).modPow(subtract.shiftRight(lowestSetBit), bigInteger);
                                if (!bigInteger2.equals(expression.BigInt1) && !bigInteger2.equals(subtract)) {
                                    for (int n33 = 1; n33 < lowestSetBit; ++n33) {
                                        bigInteger2 = bigInteger2.multiply(bigInteger2).mod(bigInteger);
                                        if (bigInteger2.equals(subtract)) {
                                            continue Label_1818;
                                        }
                                        if (bigInteger2.equals(expression.BigInt1)) {
                                            break;
                                        }
                                    }
                                    break;
                                }
                            } while (--n30 > 0);
                            if (n30 == 0) {
                                break;
                            }
                        }
                    }
                    if (n3 == 66) {
                        bigInteger = bigInteger.subtract(expression.BigInt2);
                    }
                    else {
                        bigInteger = bigInteger.add(expression.BigInt2);
                    }
                }
                array[n] = bigInteger;
                break;
            }
        }
        return 0;
    }
    
    static {
        BigInt0 = BigInteger.valueOf(0L);
        BigInt1 = BigInteger.valueOf(1L);
        BigInt2 = BigInteger.valueOf(2L);
        BigInt3 = BigInteger.valueOf(3L);
    }
}
