// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public abstract class SpanishBase implements ToWords
{
    private static final boolean DEBUGGING = false;
    private static final String AND = "y";
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2003-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String MINUS = "menos";
    static final String ONE = "uno";
    static final String ONE_COMBINING = "un";
    private static final String PLAIN_100 = "cien";
    static final String TWENTY_ONE = "vientiuno";
    static final String TWENTY_ONE_COMBINING = "veinti\u00fan";
    private static final String ZERO = "cero";
    private static final String[] groupNamePlural;
    private static final String[] groupNameSingular;
    private static final String[] hundreds;
    static final String[] lowName;
    private static final String[] tys;
    private static final int[] divisor;
    
    public String toWords(long num) {
        if (num == 0L) {
            return "cero";
        }
        final boolean negative = num < 0L;
        if (negative) {
            num = -num;
        }
        String s = "";
        int group = 0;
        while (num > 0L) {
            assert 0 <= group && group < SpanishBase.divisor.length : group + " out of bounds";
            final int remdr = (int)(num % SpanishBase.divisor[group]);
            num /= SpanishBase.divisor[group];
            if (remdr != 0) {
                String t;
                if (remdr == 1) {
                    t = this.wordForOne(group);
                }
                else if (remdr < 30) {
                    t = this.getLowName(group, remdr);
                }
                else if (remdr < 100) {
                    final int units = remdr % 10;
                    final int tens = remdr / 10;
                    t = SpanishBase.tys[tens];
                    switch (units) {
                        case 0: {
                            break;
                        }
                        default: {
                            t = t + " y " + this.getLowName(group, units);
                            break;
                        }
                    }
                }
                else if (remdr == 100) {
                    t = "cien";
                }
                else if (remdr < 1000) {
                    final int rest = remdr % 100;
                    final int cent = remdr / 100;
                    t = SpanishBase.hundreds[cent];
                    if (rest != 0) {
                        t = t + " " + this.toWords(rest);
                    }
                }
                else {
                    t = this.toWords(remdr);
                }
                s = t + ((t.length() != 0) ? " " : "") + ((remdr > 1) ? SpanishBase.groupNamePlural[group] : SpanishBase.groupNameSingular[group]) + ((s.length() > 0) ? (" " + s) : "");
            }
            ++group;
        }
        s = s.trim();
        if (negative) {
            s = "menos " + s;
        }
        return s;
    }
    
    abstract String getLowName(final int p0, final int p1);
    
    abstract String wordForOne(final int p0);
    
    static {
        groupNamePlural = new String[] { "", "mil", "milliones", "billones", "trillones", "cuatrillones" };
        groupNameSingular = new String[] { "", "mil", "mill\u00f3n", "bill\u00f3n", "trill\u00f3n", "cuatrill\u00f3n" };
        hundreds = new String[] { "", "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos" };
        lowName = new String[] { "", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez", "once", "doce", "trece", "catorce", "quince", "diecis\u00e9is", "diecisiete", "dieciocho", "diecinueve", "veinte", "veinti\u00fan/vientiuno", "veintid\u00f3s", "veintitr\u00e9s", "veinticuatro", "veinticinco", "veintis\u00e9is", "veintisiete", "veintiocho", "veintinueve" };
        tys = new String[] { "cero", "diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa" };
        divisor = new int[] { 1000, 1000, 1000000, 1000000, 1000000 };
    }
}
