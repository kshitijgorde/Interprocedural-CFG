// 
// Decompiled by Procyon v0.5.30
// 

class WordListPrims extends Primitives
{
    static String[] primlist;
    
    public String[] primlist() {
        return WordListPrims.primlist;
    }
    
    public Object dispatch(final int n, final Object[] array, final LContext lContext) {
        switch (n) {
            case 0: {
                return this.prim_first(array[0], lContext);
            }
            case 1: {
                return this.prim_last(array[0], lContext);
            }
            case 2: {
                return this.prim_word(array, lContext);
            }
            case 3:
            case 4: {
                return this.prim_butfirst(array[0], lContext);
            }
            case 5:
            case 6: {
                return this.prim_butlast(array[0], lContext);
            }
            case 7: {
                return this.prim_fput(array[0], array[1], lContext);
            }
            case 8: {
                return this.prim_lput(array[0], array[1], lContext);
            }
            case 9: {
                return this.prim_item(array[0], array[1], lContext);
            }
            case 10: {
                return this.prim_nth(array[0], array[1], lContext);
            }
            case 11: {
                return this.prim_emptyp(array[0], lContext);
            }
            case 12: {
                return this.prim_count(array[0], lContext);
            }
            case 13: {
                return this.prim_wordp(array[0], lContext);
            }
            case 14: {
                return this.prim_listp(array[0], lContext);
            }
            case 15: {
                return this.prim_memberp(array[0], array[1], lContext);
            }
            case 16: {
                return this.prim_itempos(array[0], array[1], lContext);
            }
            case 17: {
                return this.prim_setitem(array[0], array[1], array[2], lContext);
            }
            case 18: {
                return this.prim_setnth(array[0], array[1], array[2], lContext);
            }
            case 19: {
                return this.prim_removeitem(array[0], array[1], lContext);
            }
            case 20: {
                return this.prim_removeitempos(array[0], array[1], lContext);
            }
            case 21:
            case 22: {
                return this.prim_sentence(array, lContext);
            }
            case 23: {
                return this.prim_list(array, lContext);
            }
            case 24: {
                return this.prim_makelist(array[0], lContext);
            }
            case 25: {
                return this.prim_copylist(array[0], lContext);
            }
            case 26: {
                return this.prim_parse(array[0], lContext);
            }
            case 27: {
                return this.prim_char(array[0], lContext);
            }
            case 28: {
                return this.prim_ascii(array[0], lContext);
            }
            case 29: {
                return this.prim_reverse(array[0], lContext);
            }
            case 30: {
                return this.prim_substring(array[0], array[1], array[2], lContext);
            }
            case 31: {
                return this.prim_ucase(array[0], lContext);
            }
            case 32: {
                return this.prim_insert(array[0], array[1], array[2], lContext);
            }
            default: {
                return null;
            }
        }
    }
    
    Object copyList(final Object[] array, int n, final int n2) {
        final Object[] array2 = new Object[n2];
        for (int i = 0; i < n2; ++i) {
            array2[i] = array[n++];
        }
        return array2;
    }
    
    Object addToList(final Object[] array, final Object o) {
        if (!(o instanceof Object[])) {
            return lput(o, array);
        }
        final Object[] array2 = (Object[])o;
        final Object[] array3 = new Object[array.length + array2.length];
        for (int i = 0; i < array.length; ++i) {
            array3[i] = array[i];
        }
        for (int j = 0; j < array2.length; ++j) {
            array3[j + array.length] = array2[j];
        }
        return array3;
    }
    
    Object removeItem(final Object[] array, final int n) {
        final Object[] array2 = new Object[array.length - 1];
        int n2 = 0;
        for (int i = 0; i < array.length; ++i) {
            if (i != n - 1) {
                array2[n2++] = array[i];
            }
        }
        return array2;
    }
    
    static Object lput(final Object o, final Object[] array) {
        final Object[] array2 = new Object[array.length + 1];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[i];
        }
        array2[array.length] = o;
        return array2;
    }
    
    static int memberp(final Object o, final Object o2) {
        if (o2 instanceof Object[]) {
            final Object[] array = (Object[])o2;
            for (int i = 0; i < array.length; ++i) {
                if (Logo.prs(o).equals(Logo.prs(array[i]))) {
                    return i + 1;
                }
            }
            return 0;
        }
        if (o instanceof Object[]) {
            return 0;
        }
        final String prs = Logo.prs(o);
        final String prs2 = Logo.prs(o2);
        for (int j = 0; j < prs2.length(); ++j) {
            if (prs.regionMatches(true, 0, prs2, j, prs.length())) {
                return j + 1;
            }
        }
        return 0;
    }
    
    Object prim_first(final Object o, final LContext lContext) {
        if (o instanceof Object[]) {
            return ((Object[])o)[0];
        }
        return Logo.prs(o).substring(0, 1);
    }
    
    Object prim_last(final Object o, final LContext lContext) {
        if (o instanceof Object[]) {
            final Object[] array = (Object[])o;
            return array[array.length - 1];
        }
        final String prs = Logo.prs(o);
        return prs.substring(prs.length() - 1, prs.length());
    }
    
    Object prim_word(final Object[] array, final LContext lContext) {
        String string = "";
        for (int i = 0; i < array.length; ++i) {
            string += Logo.prs(array[i]);
        }
        return string;
    }
    
    Object prim_butfirst(final Object o, final LContext lContext) {
        if (o instanceof Object[]) {
            final Object[] array = (Object[])o;
            return this.copyList(array, 1, array.length - 1);
        }
        final String prs = Logo.prs(o);
        return prs.substring(1, prs.length());
    }
    
    Object prim_butlast(final Object o, final LContext lContext) {
        if (o instanceof Object[]) {
            final Object[] array = (Object[])o;
            return this.copyList(array, 0, array.length - 1);
        }
        final String prs = Logo.prs(o);
        return prs.substring(0, prs.length() - 1);
    }
    
    Object prim_fput(final Object o, final Object o2, final LContext lContext) {
        final Object[] array = Logo.aList(o2, lContext);
        final Object[] array2 = new Object[array.length + 1];
        array2[0] = o;
        for (int i = 0; i < array.length; ++i) {
            array2[i + 1] = array[i];
        }
        return array2;
    }
    
    Object prim_lput(final Object o, final Object o2, final LContext lContext) {
        return lput(o, Logo.aList(o2, lContext));
    }
    
    Object prim_item(final Object o, final Object o2, final LContext lContext) {
        final int n = Logo.anInt(o, lContext) - 1;
        return (o2 instanceof Object[]) ? ((Object[])o2)[n] : Logo.prs(o2).substring(n, n + 1);
    }
    
    Object prim_nth(final Object o, final Object o2, final LContext lContext) {
        final int anInt = Logo.anInt(o, lContext);
        return (o2 instanceof Object[]) ? ((Object[])o2)[anInt] : Logo.prs(o2).substring(anInt, anInt + 1);
    }
    
    Object prim_emptyp(final Object o, final LContext lContext) {
        return new Boolean((o instanceof Object[]) ? (((Object[])o).length == 0) : (Logo.prs(o).length() == 0));
    }
    
    Object prim_count(final Object o, final LContext lContext) {
        return new Long((o instanceof Object[]) ? ((Object[])o).length : Logo.prs(o).length());
    }
    
    Object prim_wordp(final Object o, final LContext lContext) {
        return new Boolean(!(o instanceof Object[]));
    }
    
    Object prim_listp(final Object o, final LContext lContext) {
        return new Boolean(o instanceof Object[]);
    }
    
    Object prim_memberp(final Object o, final Object o2, final LContext lContext) {
        return new Boolean(memberp(o, o2) != 0);
    }
    
    Object prim_itempos(final Object o, final Object o2, final LContext lContext) {
        final int memberp = memberp(o, o2);
        if (memberp != 0) {
            return new Long(memberp);
        }
        Logo.error(lContext.cfun + " doesn't like " + Logo.prs(o) + " as input", lContext);
        return null;
    }
    
    Object prim_setitem(final Object o, final Object o2, final Object o3, final LContext lContext) {
        Logo.aList(o2, lContext)[Logo.anInt(o, lContext) - 1] = o3;
        return null;
    }
    
    Object prim_setnth(final Object o, final Object o2, final Object o3, final LContext lContext) {
        Logo.aList(o2, lContext)[Logo.anInt(o, lContext)] = o3;
        return null;
    }
    
    Object prim_removeitem(final Object o, final Object o2, final LContext lContext) {
        final Object[] aList = Logo.aList(o2, lContext);
        return this.removeItem(aList, memberp(o, aList));
    }
    
    Object prim_removeitempos(final Object o, final Object o2, final LContext lContext) {
        return this.removeItem(Logo.aList(o2, lContext), Logo.anInt(o, lContext));
    }
    
    Object prim_sentence(final Object[] array, final LContext lContext) {
        Object[] array2 = new Object[0];
        for (int i = 0; i < array.length; ++i) {
            array2 = (Object[])this.addToList(array2, array[i]);
        }
        return array2;
    }
    
    Object prim_list(final Object[] array, final LContext lContext) {
        final Object[] array2 = new Object[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[i];
        }
        return array2;
    }
    
    Object prim_makelist(final Object o, final LContext lContext) {
        final int anInt = Logo.anInt(o, lContext);
        final Object[] array = new Object[anInt];
        for (int i = 0; i < anInt; ++i) {
            array[i] = new Object[0];
        }
        return array;
    }
    
    Object prim_copylist(final Object o, final LContext lContext) {
        final Object[] aList = Logo.aList(o, lContext);
        return this.copyList(aList, 0, aList.length);
    }
    
    Object prim_parse(final Object o, final LContext lContext) {
        return Logo.parse(Logo.aString(o, lContext), lContext);
    }
    
    Object prim_char(final Object o, final LContext lContext) {
        return new String(new char[] { (char)Logo.anInt(o, lContext) });
    }
    
    Object prim_ascii(final Object o, final LContext lContext) {
        return new Long(Logo.aString(o, lContext).charAt(0));
    }
    
    Object prim_reverse(final Object o, final LContext lContext) {
        final Object[] aList = Logo.aList(o, lContext);
        final Object[] array = new Object[aList.length];
        for (int i = 0; i < aList.length; ++i) {
            array[i] = aList[aList.length - i - 1];
        }
        return array;
    }
    
    Object prim_substring(final Object o, final Object o2, final Object o3, final LContext lContext) {
        final String prs = Logo.prs(o);
        final int anInt = Logo.anInt(o2, lContext);
        final int anInt2 = Logo.anInt(o3, lContext);
        if (anInt == -1) {
            return prs.substring(prs.length() - anInt2, prs.length());
        }
        if (anInt2 == -1) {
            return prs.substring(anInt, prs.length());
        }
        return prs.substring(anInt, anInt + anInt2);
    }
    
    Object prim_ucase(final Object o, final LContext lContext) {
        return Logo.prs(o).toUpperCase();
    }
    
    Object prim_insert(final Object o, final Object o2, final Object o3, final LContext lContext) {
        final Object[] aList = Logo.aList(o, lContext);
        final int n = Logo.anInt(o2, lContext) - 1;
        if (0 > n || n > aList.length) {
            Logo.error(lContext.cfun + " doesn't like " + Logo.prs(o2) + " as input", lContext);
        }
        final Object[] array = new Object[aList.length + 1];
        int n2 = 0;
        for (int i = 0; i < aList.length; ++i) {
            if (i == n) {
                array[n2++] = o3;
            }
            array[n2++] = aList[i];
        }
        if (n == aList.length) {
            array[n2] = o3;
        }
        return array;
    }
    
    static {
        WordListPrims.primlist = new String[] { "first", "1", "last", "1", "word", "i2", "butfirst", "1", "bf", "1", "butlast", "1", "bl", "1", "fput", "2", "lput", "2", "item", "2", "nth", "2", "empty?", "1", "count", "1", "word?", "1", "list?", "1", "member?", "2", "itempos", "2", "setitem", "3", "setnth", "3", "removeitem", "2", "removeitempos", "2", "sentence", "2", "se", "i2", "list", "i2", "makelist", "1", "copylist", "1", "parse", "1", "char", "1", "ascii", "1", "reverse", "1", "substring", "3", "ucase", "1", "insert", "3" };
    }
}
