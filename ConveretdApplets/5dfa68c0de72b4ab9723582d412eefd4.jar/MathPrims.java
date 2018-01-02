// 
// Decompiled by Procyon v0.5.30
// 

class MathPrims extends Primitives
{
    static String[] primlist;
    static final double degtor = 57.29577951308232;
    
    public String[] primlist() {
        return MathPrims.primlist;
    }
    
    public Object dispatch(final int n, final Object[] array, final LContext lContext) {
        switch (n) {
            case 0: {
                return this.prim_sum(array, lContext);
            }
            case 1: {
                return this.prim_remainder(array[0], array[1], lContext);
            }
            case 2:
            case 3: {
                return this.prim_diff(array[0], array[1], lContext);
            }
            case 4: {
                return this.prim_product(array, lContext);
            }
            case 5: {
                return this.prim_quotient(array[0], array[1], lContext);
            }
            case 6: {
                return this.prim_greaterp(array[0], array[1], lContext);
            }
            case 7: {
                return this.prim_lessp(array[0], array[1], lContext);
            }
            case 8: {
                return this.prim_int(array[0], lContext);
            }
            case 9: {
                return this.prim_minus(array[0], lContext);
            }
            case 10: {
                return this.prim_round(array[0], lContext);
            }
            case 11: {
                return this.prim_sqrt(array[0], lContext);
            }
            case 12: {
                return this.prim_sin(array[0], lContext);
            }
            case 13: {
                return this.prim_cos(array[0], lContext);
            }
            case 14: {
                return this.prim_tan(array[0], lContext);
            }
            case 15: {
                return this.prim_abs(array[0], lContext);
            }
            case 16: {
                return this.prim_power(array[0], array[1], lContext);
            }
            case 17: {
                return this.prim_arctan(array[0], lContext);
            }
            case 18: {
                return this.prim_pi(lContext);
            }
            case 19: {
                return this.prim_exp(array[0], lContext);
            }
            case 20: {
                return this.prim_arctan2(array[0], array[1], lContext);
            }
            case 21: {
                return this.prim_ln(array[0], lContext);
            }
            case 22: {
                return this.prim_logand(array[0], array[1], lContext);
            }
            case 23: {
                return this.prim_logior(array[0], array[1], lContext);
            }
            case 24: {
                return this.prim_logxor(array[0], array[1], lContext);
            }
            case 25: {
                return this.prim_lsh(array[0], array[1], lContext);
            }
            case 26: {
                return this.prim_and(array, lContext);
            }
            case 27: {
                return this.prim_or(array, lContext);
            }
            case 28: {
                return this.prim_not(array[0], lContext);
            }
            case 29: {
                return this.prim_random(array[0], lContext);
            }
            case 30: {
                return this.prim_min(array, lContext);
            }
            case 31: {
                return this.prim_max(array, lContext);
            }
            case 32: {
                return this.prim_numberp(array[0], lContext);
            }
            case 33: {
                return this.prim_sum(array, lContext);
            }
            case 34: {
                return this.prim_diff(array[0], array[1], lContext);
            }
            case 35: {
                return this.prim_product(array, lContext);
            }
            case 36: {
                return this.prim_quotient(array[0], array[1], lContext);
            }
            case 37: {
                return this.prim_lessp(array[0], array[1], lContext);
            }
            case 38: {
                return this.prim_greaterp(array[0], array[1], lContext);
            }
            case 39: {
                return this.prim_equalp(array, lContext);
            }
            case 40: {
                return this.prim_equalp(array, lContext);
            }
            case 41: {
                return this.prim_remainder(array[0], array[1], lContext);
            }
            case 42: {
                return new Double(Math.random());
            }
            case 43: {
                return this.prim_strequ(array[0], array[1], lContext);
            }
            case 44: {
                return this.prim_arcsin(array[0], lContext);
            }
            case 45: {
                return this.prim_arccos(array[0], lContext);
            }
            case 46: {
                return this.prim_strcmp(array[0], array[1], lContext);
            }
            default: {
                return null;
            }
        }
    }
    
    Object prim_sum(final Object[] array, final LContext lContext) {
        double n = 0.0;
        for (int i = 0; i < array.length; ++i) {
            n += Logo.aDouble(array[i], lContext);
        }
        return new Double(n);
    }
    
    Object prim_remainder(final Object o, final Object o2, final LContext lContext) {
        return new Double(Logo.aDouble(o, lContext) % Logo.aDouble(o2, lContext));
    }
    
    Object prim_diff(final Object o, final Object o2, final LContext lContext) {
        return new Double(Logo.aDouble(o, lContext) - Logo.aDouble(o2, lContext));
    }
    
    Object prim_product(final Object[] array, final LContext lContext) {
        double n = 1.0;
        for (int i = 0; i < array.length; ++i) {
            n *= Logo.aDouble(array[i], lContext);
        }
        return new Double(n);
    }
    
    Object prim_quotient(final Object o, final Object o2, final LContext lContext) {
        return new Double(Logo.aDouble(o, lContext) / Logo.aDouble(o2, lContext));
    }
    
    Object prim_greaterp(final Object o, final Object o2, final LContext lContext) {
        return new Boolean(Logo.aDouble(o, lContext) > Logo.aDouble(o2, lContext));
    }
    
    Object prim_lessp(final Object o, final Object o2, final LContext lContext) {
        return new Boolean(Logo.aDouble(o, lContext) < Logo.aDouble(o2, lContext));
    }
    
    Object prim_int(final Object o, final LContext lContext) {
        return new Double((int)(Object)new Double(Logo.aDouble(o, lContext)));
    }
    
    Object prim_minus(final Object o, final LContext lContext) {
        return new Double(0.0 - Logo.aDouble(o, lContext));
    }
    
    Object prim_round(final Object o, final LContext lContext) {
        return new Double(Math.round(Logo.aDouble(o, lContext)));
    }
    
    Object prim_sqrt(final Object o, final LContext lContext) {
        return new Double(Math.sqrt(Logo.aDouble(o, lContext)));
    }
    
    Object prim_sin(final Object o, final LContext lContext) {
        return new Double(Math.sin(Logo.aDouble(o, lContext) / 57.29577951308232));
    }
    
    Object prim_cos(final Object o, final LContext lContext) {
        return new Double(Math.cos(Logo.aDouble(o, lContext) / 57.29577951308232));
    }
    
    Object prim_tan(final Object o, final LContext lContext) {
        return new Double(Math.tan(Logo.aDouble(o, lContext) / 57.29577951308232));
    }
    
    Object prim_abs(final Object o, final LContext lContext) {
        return new Double(Math.abs(Logo.aDouble(o, lContext)));
    }
    
    Object prim_power(final Object o, final Object o2, final LContext lContext) {
        return new Double(Math.pow(Logo.aDouble(o, lContext), Logo.aDouble(o2, lContext)));
    }
    
    Object prim_arcsin(final Object o, final LContext lContext) {
        return new Double(57.29577951308232 * Math.asin(Logo.aDouble(o, lContext)));
    }
    
    Object prim_arccos(final Object o, final LContext lContext) {
        return new Double(57.29577951308232 * Math.acos(Logo.aDouble(o, lContext)));
    }
    
    Object prim_arctan(final Object o, final LContext lContext) {
        return new Double(57.29577951308232 * Math.atan(Logo.aDouble(o, lContext)));
    }
    
    Object prim_pi(final LContext lContext) {
        return new Double(3.141592653589793);
    }
    
    Object prim_exp(final Object o, final LContext lContext) {
        return new Double(Math.exp(Logo.aDouble(o, lContext)));
    }
    
    Object prim_arctan2(final Object o, final Object o2, final LContext lContext) {
        return new Double(57.29577951308232 * Math.atan2(Logo.aDouble(o, lContext), Logo.aDouble(o2, lContext)));
    }
    
    Object prim_ln(final Object o, final LContext lContext) {
        return new Double(Math.log(Logo.aDouble(o, lContext)));
    }
    
    Object prim_logand(final Object o, final Object o2, final LContext lContext) {
        return new Double(Logo.anInt(o, lContext) & Logo.anInt(o2, lContext));
    }
    
    Object prim_logior(final Object o, final Object o2, final LContext lContext) {
        return new Double(Logo.anInt(o, lContext) | Logo.anInt(o2, lContext));
    }
    
    Object prim_logxor(final Object o, final Object o2, final LContext lContext) {
        return new Double(Logo.anInt(o, lContext) ^ Logo.anInt(o2, lContext));
    }
    
    Object prim_lsh(final Object o, final Object o2, final LContext lContext) {
        final int anInt = Logo.anInt(o2, lContext);
        final int anInt2 = Logo.anInt(o, lContext);
        return (anInt > 0) ? new Double(anInt2 << anInt) : new Double(anInt2 >> -anInt);
    }
    
    Object prim_and(final Object[] array, final LContext lContext) {
        boolean b = true;
        for (int i = 0; i < array.length; ++i) {
            b &= Logo.aBoolean(array[i], lContext);
        }
        return new Boolean(b);
    }
    
    Object prim_or(final Object[] array, final LContext lContext) {
        boolean b = false;
        for (int i = 0; i < array.length; ++i) {
            b |= Logo.aBoolean(array[i], lContext);
        }
        return new Boolean(b);
    }
    
    Object prim_not(final Object o, final LContext lContext) {
        return new Boolean(!Logo.aBoolean(o, lContext));
    }
    
    Object prim_random(final Object o, final LContext lContext) {
        return new Double(Math.floor(Math.random() * Logo.anInt(o, lContext)));
    }
    
    Object prim_min(final Object[] array, final LContext lContext) {
        if (array.length == 0) {
            Logo.error("Min needs at least one input", lContext);
        }
        double n = Logo.aDouble(array[0], lContext);
        for (int i = 1; i < array.length; ++i) {
            n = Math.min(n, Logo.aDouble(array[i], lContext));
        }
        return new Double(n);
    }
    
    Object prim_max(final Object[] array, final LContext lContext) {
        if (array.length == 0) {
            Logo.error("Max needs at least one input", lContext);
        }
        double n = Logo.aDouble(array[0], lContext);
        for (int i = 1; i < array.length; ++i) {
            n = Math.max(n, Logo.aDouble(array[i], lContext));
        }
        return new Double(n);
    }
    
    Object prim_numberp(final Object o, final LContext lContext) {
        return new Boolean(o instanceof Number);
    }
    
    Object prim_equalp(final Object[] array, final LContext lContext) {
        if (array.length == 0) {
            Logo.error("Equal needs at least one input", lContext);
        }
        final Object o = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (o != array[i]) {
                if (!Logo.prs(o).equals(Logo.prs(array[i]))) {
                    return new Boolean(false);
                }
            }
        }
        return new Boolean(true);
    }
    
    Object prim_strequ(final Object o, final Object o2, final LContext lContext) {
        final String convertToString = this.convertToString(o);
        final String convertToString2 = this.convertToString(o2);
        if (convertToString == null || convertToString2 == null) {
            return new Boolean(false);
        }
        return new Boolean(convertToString.equalsIgnoreCase(convertToString2));
    }
    
    Object prim_strcmp(final Object o, final Object o2, final LContext lContext) {
        final String convertToString = this.convertToString(o);
        final String convertToString2 = this.convertToString(o2);
        if (convertToString == null || convertToString2 == null) {
            return new Boolean(false);
        }
        return new Double(convertToString.compareToIgnoreCase(convertToString2));
    }
    
    String convertToString(final Object o) {
        if (o instanceof String) {
            return (String)o;
        }
        if (o instanceof Symbol) {
            return ((Symbol)o).pname;
        }
        if (o instanceof QuotedSymbol) {
            return ((QuotedSymbol)o).sym.pname;
        }
        return null;
    }
    
    static {
        MathPrims.primlist = new String[] { "sum", "i2", "remainder", "2", "difference", "2", "diff", "2", "product", "i2", "quotient", "2", "greater?", "2", "less?", "2", "int", "1", "minus", "1", "round", "1", "sqrt", "1", "sin", "1", "cos", "1", "tan", "1", "abs", "1", "power", "2", "arctan", "1", "pi", "0", "exp", "1", "arctan2", "2", "ln", "1", "logand", "2", "logior", "2", "logxor", "2", "lsh", "2", "and", "i2", "or", "i2", "not", "1", "random", "1", "min", "i2", "max", "i2", "number?", "1", "+", "-2", "-", "-2", "*", "-3", "/", "-3", "<", "-1", ">", "-1", "=", "-1", "equal?", "i2", "%", "-3", "rand", "0", "strequ", "2", "arcsin", "1", "arccos", "1", "strcmp", "2" };
    }
}
