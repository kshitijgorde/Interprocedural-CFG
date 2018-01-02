// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings;

public abstract class CaseFoldMapEncoding extends SingleByteEncoding
{
    protected final int[][] CaseFoldMap;
    protected final boolean foldFlag;
    static final int[] SS;
    
    protected CaseFoldMapEncoding(final String name, final short[] CTypeTable, final byte[] LowerCaseTable, final int[][] CaseFoldMap) {
        this(name, CTypeTable, LowerCaseTable, CaseFoldMap, true);
    }
    
    protected CaseFoldMapEncoding(final String name, final short[] CTypeTable, final byte[] LowerCaseTable, final int[][] CaseFoldMap, final boolean foldFlag) {
        super(name, CTypeTable, LowerCaseTable);
        this.CaseFoldMap = CaseFoldMap;
        this.foldFlag = foldFlag;
    }
    
    protected final int applyAllCaseFoldWithMap(final int mapSize, final int[][] map, final boolean essTsettFlag, final int flag, final ApplyAllCaseFoldFunction fun, final Object arg) {
        this.asciiApplyAllCaseFold(flag, fun, arg);
        final int[] code = { 0 };
        for (int i = 0; i < mapSize; ++i) {
            code[0] = map[i][1];
            fun.apply(map[i][0], code, 1, arg);
            code[0] = map[i][0];
            fun.apply(map[i][1], code, 1, arg);
        }
        if (essTsettFlag) {
            this.ssApplyAllCaseFold(flag, fun, arg);
        }
        return 0;
    }
    
    private void ssApplyAllCaseFold(final int flag, final ApplyAllCaseFoldFunction fun, final Object arg) {
        fun.apply(223, CaseFoldMapEncoding.SS, 2, arg);
    }
    
    protected final CaseFoldCodeItem[] getCaseFoldCodesByStringWithMap(final int mapSize, final int[][] map, final boolean essTsettFlag, final int flag, final byte[] bytes, final int p, final int end) {
        final int b = bytes[p] & 0xFF;
        if (65 <= b && b <= 90) {
            final CaseFoldCodeItem item0 = new CaseFoldCodeItem(1, 1, new int[] { b + 32 });
            if (b == 83 && essTsettFlag && end > p + 1 && (bytes[p + 1] == 83 || bytes[p + 1] == 115)) {
                final CaseFoldCodeItem item2 = new CaseFoldCodeItem(2, 1, new int[] { 223 });
                return new CaseFoldCodeItem[] { item0, item2 };
            }
            return new CaseFoldCodeItem[] { item0 };
        }
        else if (97 <= b && b <= 122) {
            final CaseFoldCodeItem item0 = new CaseFoldCodeItem(1, 1, new int[] { b - 32 });
            if (b == 115 && essTsettFlag && end > p + 1 && (bytes[p + 1] == 115 || bytes[p + 1] == 83)) {
                final CaseFoldCodeItem item2 = new CaseFoldCodeItem(2, 1, new int[] { 223 });
                return new CaseFoldCodeItem[] { item0, item2 };
            }
            return new CaseFoldCodeItem[] { item0 };
        }
        else {
            if (b == 223 && essTsettFlag) {
                final CaseFoldCodeItem item0 = new CaseFoldCodeItem(1, 2, new int[] { 115, 115 });
                final CaseFoldCodeItem item2 = new CaseFoldCodeItem(1, 2, new int[] { 83, 83 });
                final CaseFoldCodeItem item3 = new CaseFoldCodeItem(1, 2, new int[] { 115, 83 });
                final CaseFoldCodeItem item4 = new CaseFoldCodeItem(1, 2, new int[] { 83, 115 });
                return new CaseFoldCodeItem[] { item0, item2, item3, item4 };
            }
            for (int i = 0; i < mapSize; ++i) {
                if (b == map[i][0]) {
                    return new CaseFoldCodeItem[] { new CaseFoldCodeItem(1, 1, new int[] { map[i][1] }) };
                }
                if (b == map[i][1]) {
                    return new CaseFoldCodeItem[] { new CaseFoldCodeItem(1, 1, new int[] { map[i][0] }) };
                }
            }
            return CaseFoldMapEncoding.EMPTY_FOLD_CODES;
        }
    }
    
    public void applyAllCaseFold(final int flag, final ApplyAllCaseFoldFunction fun, final Object arg) {
        this.applyAllCaseFoldWithMap(this.CaseFoldMap.length, this.CaseFoldMap, this.foldFlag, flag, fun, arg);
    }
    
    public CaseFoldCodeItem[] caseFoldCodesByString(final int flag, final byte[] bytes, final int p, final int end) {
        return this.getCaseFoldCodesByStringWithMap(this.CaseFoldMap.length, this.CaseFoldMap, this.foldFlag, flag, bytes, p, end);
    }
    
    public boolean isCodeCType(final int code, final int ctype) {
        return code < 256 && this.isCodeCTypeInternal(code, ctype);
    }
    
    static {
        SS = new int[] { 115, 115 };
    }
}
