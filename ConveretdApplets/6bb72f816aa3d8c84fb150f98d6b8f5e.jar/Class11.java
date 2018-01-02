// 
// Decompiled by Procyon v0.5.30
// 

final class Class11
{
    private final int anInt290;
    private final int anInt291;
    private final int anInt292;
    private final int anInt293;
    public final int[][] anIntArrayArray294;
    
    public Class11() {
        this.anInt290 = 0;
        this.anInt291 = 0;
        this.anInt292 = 104;
        this.anInt293 = 104;
        this.anIntArrayArray294 = new int[this.anInt292][this.anInt293];
        this.method210();
    }
    
    public void method210() {
        for (int i = 0; i < this.anInt292; ++i) {
            for (int j = 0; j < this.anInt293; ++j) {
                if (i == 0 || j == 0 || i == this.anInt292 - 1 || j == this.anInt293 - 1) {
                    this.anIntArrayArray294[i][j] = 16777215;
                }
                else {
                    this.anIntArrayArray294[i][j] = 16777216;
                }
            }
        }
    }
    
    public void method211(int n, final int n2, int n3, final int n4, final boolean b) {
        n3 -= this.anInt290;
        n -= this.anInt291;
        if (n4 == 0) {
            if (n2 == 0) {
                this.method214(n3, n, 128);
                this.method214(n3 - 1, n, 8);
            }
            if (n2 == 1) {
                this.method214(n3, n, 2);
                this.method214(n3, n + 1, 32);
            }
            if (n2 == 2) {
                this.method214(n3, n, 8);
                this.method214(n3 + 1, n, 128);
            }
            if (n2 == 3) {
                this.method214(n3, n, 32);
                this.method214(n3, n - 1, 2);
            }
        }
        if (n4 == 1 || n4 == 3) {
            if (n2 == 0) {
                this.method214(n3, n, 1);
                this.method214(n3 - 1, n + 1, 16);
            }
            if (n2 == 1) {
                this.method214(n3, n, 4);
                this.method214(n3 + 1, n + 1, 64);
            }
            if (n2 == 2) {
                this.method214(n3, n, 16);
                this.method214(n3 + 1, n - 1, 1);
            }
            if (n2 == 3) {
                this.method214(n3, n, 64);
                this.method214(n3 - 1, n - 1, 4);
            }
        }
        if (n4 == 2) {
            if (n2 == 0) {
                this.method214(n3, n, 130);
                this.method214(n3 - 1, n, 8);
                this.method214(n3, n + 1, 32);
            }
            if (n2 == 1) {
                this.method214(n3, n, 10);
                this.method214(n3, n + 1, 32);
                this.method214(n3 + 1, n, 128);
            }
            if (n2 == 2) {
                this.method214(n3, n, 40);
                this.method214(n3 + 1, n, 128);
                this.method214(n3, n - 1, 2);
            }
            if (n2 == 3) {
                this.method214(n3, n, 160);
                this.method214(n3, n - 1, 2);
                this.method214(n3 - 1, n, 8);
            }
        }
        if (b) {
            if (n4 == 0) {
                if (n2 == 0) {
                    this.method214(n3, n, 65536);
                    this.method214(n3 - 1, n, 4096);
                }
                if (n2 == 1) {
                    this.method214(n3, n, 1024);
                    this.method214(n3, n + 1, 16384);
                }
                if (n2 == 2) {
                    this.method214(n3, n, 4096);
                    this.method214(n3 + 1, n, 65536);
                }
                if (n2 == 3) {
                    this.method214(n3, n, 16384);
                    this.method214(n3, n - 1, 1024);
                }
            }
            if (n4 == 1 || n4 == 3) {
                if (n2 == 0) {
                    this.method214(n3, n, 512);
                    this.method214(n3 - 1, n + 1, 8192);
                }
                if (n2 == 1) {
                    this.method214(n3, n, 2048);
                    this.method214(n3 + 1, n + 1, 32768);
                }
                if (n2 == 2) {
                    this.method214(n3, n, 8192);
                    this.method214(n3 + 1, n - 1, 512);
                }
                if (n2 == 3) {
                    this.method214(n3, n, 32768);
                    this.method214(n3 - 1, n - 1, 2048);
                }
            }
            if (n4 == 2) {
                if (n2 == 0) {
                    this.method214(n3, n, 66560);
                    this.method214(n3 - 1, n, 4096);
                    this.method214(n3, n + 1, 16384);
                }
                if (n2 == 1) {
                    this.method214(n3, n, 5120);
                    this.method214(n3, n + 1, 16384);
                    this.method214(n3 + 1, n, 65536);
                }
                if (n2 == 2) {
                    this.method214(n3, n, 20480);
                    this.method214(n3 + 1, n, 65536);
                    this.method214(n3, n - 1, 1024);
                }
                if (n2 == 3) {
                    this.method214(n3, n, 81920);
                    this.method214(n3, n - 1, 1024);
                    this.method214(n3 - 1, n, 4096);
                }
            }
        }
    }
    
    public void method212(final boolean b, int n, int n2, int n3, int n4, final int n5) {
        int n6 = 256;
        if (b) {
            n6 += 131072;
        }
        n3 -= this.anInt290;
        n4 -= this.anInt291;
        if (n5 == 1 || n5 == 3) {
            final int n7 = n;
            n = n2;
            n2 = n7;
        }
        for (int i = n3; i < n3 + n; ++i) {
            if (i >= 0 && i < this.anInt292) {
                for (int j = n4; j < n4 + n2; ++j) {
                    if (j >= 0 && j < this.anInt293) {
                        this.method214(i, j, n6);
                    }
                }
            }
        }
    }
    
    public void method213(int n, int n2) {
        n2 -= this.anInt290;
        n -= this.anInt291;
        final int[] array = this.anIntArrayArray294[n2];
        final int n3 = n;
        array[n3] |= 0x200000;
    }
    
    private void method214(final int n, final int n2, final int n3) {
        final int[] array = this.anIntArrayArray294[n];
        array[n2] |= n3;
    }
    
    public void method215(final int n, final int n2, final boolean b, int n3, int n4) {
        n3 -= this.anInt290;
        n4 -= this.anInt291;
        if (n2 == 0) {
            if (n == 0) {
                this.method217(128, n3, n4);
                this.method217(8, n3 - 1, n4);
            }
            if (n == 1) {
                this.method217(2, n3, n4);
                this.method217(32, n3, n4 + 1);
            }
            if (n == 2) {
                this.method217(8, n3, n4);
                this.method217(128, n3 + 1, n4);
            }
            if (n == 3) {
                this.method217(32, n3, n4);
                this.method217(2, n3, n4 - 1);
            }
        }
        if (n2 == 1 || n2 == 3) {
            if (n == 0) {
                this.method217(1, n3, n4);
                this.method217(16, n3 - 1, n4 + 1);
            }
            if (n == 1) {
                this.method217(4, n3, n4);
                this.method217(64, n3 + 1, n4 + 1);
            }
            if (n == 2) {
                this.method217(16, n3, n4);
                this.method217(1, n3 + 1, n4 - 1);
            }
            if (n == 3) {
                this.method217(64, n3, n4);
                this.method217(4, n3 - 1, n4 - 1);
            }
        }
        if (n2 == 2) {
            if (n == 0) {
                this.method217(130, n3, n4);
                this.method217(8, n3 - 1, n4);
                this.method217(32, n3, n4 + 1);
            }
            if (n == 1) {
                this.method217(10, n3, n4);
                this.method217(32, n3, n4 + 1);
                this.method217(128, n3 + 1, n4);
            }
            if (n == 2) {
                this.method217(40, n3, n4);
                this.method217(128, n3 + 1, n4);
                this.method217(2, n3, n4 - 1);
            }
            if (n == 3) {
                this.method217(160, n3, n4);
                this.method217(2, n3, n4 - 1);
                this.method217(8, n3 - 1, n4);
            }
        }
        if (b) {
            if (n2 == 0) {
                if (n == 0) {
                    this.method217(65536, n3, n4);
                    this.method217(4096, n3 - 1, n4);
                }
                if (n == 1) {
                    this.method217(1024, n3, n4);
                    this.method217(16384, n3, n4 + 1);
                }
                if (n == 2) {
                    this.method217(4096, n3, n4);
                    this.method217(65536, n3 + 1, n4);
                }
                if (n == 3) {
                    this.method217(16384, n3, n4);
                    this.method217(1024, n3, n4 - 1);
                }
            }
            if (n2 == 1 || n2 == 3) {
                if (n == 0) {
                    this.method217(512, n3, n4);
                    this.method217(8192, n3 - 1, n4 + 1);
                }
                if (n == 1) {
                    this.method217(2048, n3, n4);
                    this.method217(32768, n3 + 1, n4 + 1);
                }
                if (n == 2) {
                    this.method217(8192, n3, n4);
                    this.method217(512, n3 + 1, n4 - 1);
                }
                if (n == 3) {
                    this.method217(32768, n3, n4);
                    this.method217(2048, n3 - 1, n4 - 1);
                }
            }
            if (n2 == 2) {
                if (n == 0) {
                    this.method217(66560, n3, n4);
                    this.method217(4096, n3 - 1, n4);
                    this.method217(16384, n3, n4 + 1);
                }
                if (n == 1) {
                    this.method217(5120, n3, n4);
                    this.method217(16384, n3, n4 + 1);
                    this.method217(65536, n3 + 1, n4);
                }
                if (n == 2) {
                    this.method217(20480, n3, n4);
                    this.method217(65536, n3 + 1, n4);
                    this.method217(1024, n3, n4 - 1);
                }
                if (n == 3) {
                    this.method217(81920, n3, n4);
                    this.method217(1024, n3, n4 - 1);
                    this.method217(4096, n3 - 1, n4);
                }
            }
        }
    }
    
    public void method216(final int n, int n2, int n3, int n4, int n5, final boolean b) {
        int n6 = 256;
        if (b) {
            n6 += 131072;
        }
        n3 -= this.anInt290;
        n4 -= this.anInt291;
        if (n == 1 || n == 3) {
            final int n7 = n2;
            n2 = n5;
            n5 = n7;
        }
        for (int i = n3; i < n3 + n2; ++i) {
            if (i >= 0 && i < this.anInt292) {
                for (int j = n4; j < n4 + n5; ++j) {
                    if (j >= 0 && j < this.anInt293) {
                        this.method217(n6, i, j);
                    }
                }
            }
        }
    }
    
    private void method217(final int n, final int n2, final int n3) {
        final int[] array = this.anIntArrayArray294[n2];
        array[n3] &= 16777215 - n;
    }
    
    public void method218(int n, int n2) {
        n2 -= this.anInt290;
        n -= this.anInt291;
        final int[] array = this.anIntArrayArray294[n2];
        final int n3 = n;
        array[n3] &= 0xDFFFFF;
    }
    
    public boolean method219(int n, int n2, int n3, final int n4, final int n5, int n6) {
        if (n2 == n && n3 == n6) {
            return true;
        }
        n2 -= this.anInt290;
        n3 -= this.anInt291;
        n -= this.anInt290;
        n6 -= this.anInt291;
        if (n5 == 0) {
            if (n4 == 0) {
                if (n2 == n - 1 && n3 == n6) {
                    return true;
                }
                if (n2 == n && n3 == n6 + 1 && (this.anIntArrayArray294[n2][n3] & 0x1280120) == 0x0) {
                    return true;
                }
                if (n2 == n && n3 == n6 - 1 && (this.anIntArrayArray294[n2][n3] & 0x1280102) == 0x0) {
                    return true;
                }
            }
            else if (n4 == 1) {
                if (n2 == n && n3 == n6 + 1) {
                    return true;
                }
                if (n2 == n - 1 && n3 == n6 && (this.anIntArrayArray294[n2][n3] & 0x1280108) == 0x0) {
                    return true;
                }
                if (n2 == n + 1 && n3 == n6 && (this.anIntArrayArray294[n2][n3] & 0x1280180) == 0x0) {
                    return true;
                }
            }
            else if (n4 == 2) {
                if (n2 == n + 1 && n3 == n6) {
                    return true;
                }
                if (n2 == n && n3 == n6 + 1 && (this.anIntArrayArray294[n2][n3] & 0x1280120) == 0x0) {
                    return true;
                }
                if (n2 == n && n3 == n6 - 1 && (this.anIntArrayArray294[n2][n3] & 0x1280102) == 0x0) {
                    return true;
                }
            }
            else if (n4 == 3) {
                if (n2 == n && n3 == n6 - 1) {
                    return true;
                }
                if (n2 == n - 1 && n3 == n6 && (this.anIntArrayArray294[n2][n3] & 0x1280108) == 0x0) {
                    return true;
                }
                if (n2 == n + 1 && n3 == n6 && (this.anIntArrayArray294[n2][n3] & 0x1280180) == 0x0) {
                    return true;
                }
            }
        }
        if (n5 == 2) {
            if (n4 == 0) {
                if (n2 == n - 1 && n3 == n6) {
                    return true;
                }
                if (n2 == n && n3 == n6 + 1) {
                    return true;
                }
                if (n2 == n + 1 && n3 == n6 && (this.anIntArrayArray294[n2][n3] & 0x1280180) == 0x0) {
                    return true;
                }
                if (n2 == n && n3 == n6 - 1 && (this.anIntArrayArray294[n2][n3] & 0x1280102) == 0x0) {
                    return true;
                }
            }
            else if (n4 == 1) {
                if (n2 == n - 1 && n3 == n6 && (this.anIntArrayArray294[n2][n3] & 0x1280108) == 0x0) {
                    return true;
                }
                if (n2 == n && n3 == n6 + 1) {
                    return true;
                }
                if (n2 == n + 1 && n3 == n6) {
                    return true;
                }
                if (n2 == n && n3 == n6 - 1 && (this.anIntArrayArray294[n2][n3] & 0x1280102) == 0x0) {
                    return true;
                }
            }
            else if (n4 == 2) {
                if (n2 == n - 1 && n3 == n6 && (this.anIntArrayArray294[n2][n3] & 0x1280108) == 0x0) {
                    return true;
                }
                if (n2 == n && n3 == n6 + 1 && (this.anIntArrayArray294[n2][n3] & 0x1280120) == 0x0) {
                    return true;
                }
                if (n2 == n + 1 && n3 == n6) {
                    return true;
                }
                if (n2 == n && n3 == n6 - 1) {
                    return true;
                }
            }
            else if (n4 == 3) {
                if (n2 == n - 1 && n3 == n6) {
                    return true;
                }
                if (n2 == n && n3 == n6 + 1 && (this.anIntArrayArray294[n2][n3] & 0x1280120) == 0x0) {
                    return true;
                }
                if (n2 == n + 1 && n3 == n6 && (this.anIntArrayArray294[n2][n3] & 0x1280180) == 0x0) {
                    return true;
                }
                if (n2 == n && n3 == n6 - 1) {
                    return true;
                }
            }
        }
        if (n5 == 9) {
            if (n2 == n && n3 == n6 + 1 && (this.anIntArrayArray294[n2][n3] & 0x20) == 0x0) {
                return true;
            }
            if (n2 == n && n3 == n6 - 1 && (this.anIntArrayArray294[n2][n3] & 0x2) == 0x0) {
                return true;
            }
            if (n2 == n - 1 && n3 == n6 && (this.anIntArrayArray294[n2][n3] & 0x8) == 0x0) {
                return true;
            }
            if (n2 == n + 1 && n3 == n6 && (this.anIntArrayArray294[n2][n3] & 0x80) == 0x0) {
                return true;
            }
        }
        return false;
    }
    
    public boolean method220(int n, int n2, int n3, final int n4, int n5, int n6) {
        if (n6 == n && n3 == n2) {
            return true;
        }
        n6 -= this.anInt290;
        n3 -= this.anInt291;
        n -= this.anInt290;
        n2 -= this.anInt291;
        if (n4 == 6 || n4 == 7) {
            if (n4 == 7) {
                n5 = (n5 + 2 & 0x3);
            }
            if (n5 == 0) {
                if (n6 == n + 1 && n3 == n2 && (this.anIntArrayArray294[n6][n3] & 0x80) == 0x0) {
                    return true;
                }
                if (n6 == n && n3 == n2 - 1 && (this.anIntArrayArray294[n6][n3] & 0x2) == 0x0) {
                    return true;
                }
            }
            else if (n5 == 1) {
                if (n6 == n - 1 && n3 == n2 && (this.anIntArrayArray294[n6][n3] & 0x8) == 0x0) {
                    return true;
                }
                if (n6 == n && n3 == n2 - 1 && (this.anIntArrayArray294[n6][n3] & 0x2) == 0x0) {
                    return true;
                }
            }
            else if (n5 == 2) {
                if (n6 == n - 1 && n3 == n2 && (this.anIntArrayArray294[n6][n3] & 0x8) == 0x0) {
                    return true;
                }
                if (n6 == n && n3 == n2 + 1 && (this.anIntArrayArray294[n6][n3] & 0x20) == 0x0) {
                    return true;
                }
            }
            else if (n5 == 3) {
                if (n6 == n + 1 && n3 == n2 && (this.anIntArrayArray294[n6][n3] & 0x80) == 0x0) {
                    return true;
                }
                if (n6 == n && n3 == n2 + 1 && (this.anIntArrayArray294[n6][n3] & 0x20) == 0x0) {
                    return true;
                }
            }
        }
        if (n4 == 8) {
            if (n6 == n && n3 == n2 + 1 && (this.anIntArrayArray294[n6][n3] & 0x20) == 0x0) {
                return true;
            }
            if (n6 == n && n3 == n2 - 1 && (this.anIntArrayArray294[n6][n3] & 0x2) == 0x0) {
                return true;
            }
            if (n6 == n - 1 && n3 == n2 && (this.anIntArrayArray294[n6][n3] & 0x8) == 0x0) {
                return true;
            }
            if (n6 == n + 1 && n3 == n2 && (this.anIntArrayArray294[n6][n3] & 0x80) == 0x0) {
                return true;
            }
        }
        return false;
    }
    
    public boolean method221(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final int n8 = n2 + n6 - 1;
        final int n9 = n + n4 - 1;
        return (n3 >= n2 && n3 <= n8 && n7 >= n && n7 <= n9) || (n3 == n2 - 1 && n7 >= n && n7 <= n9 && (this.anIntArrayArray294[n3 - this.anInt290][n7 - this.anInt291] & 0x8) == 0x0 && (n5 & 0x8) == 0x0) || (n3 == n8 + 1 && n7 >= n && n7 <= n9 && (this.anIntArrayArray294[n3 - this.anInt290][n7 - this.anInt291] & 0x80) == 0x0 && (n5 & 0x2) == 0x0) || (n7 == n - 1 && n3 >= n2 && n3 <= n8 && (this.anIntArrayArray294[n3 - this.anInt290][n7 - this.anInt291] & 0x2) == 0x0 && (n5 & 0x4) == 0x0) || (n7 == n9 + 1 && n3 >= n2 && n3 <= n8 && (this.anIntArrayArray294[n3 - this.anInt290][n7 - this.anInt291] & 0x20) == 0x0 && (n5 & 0x1) == 0x0);
    }
}
