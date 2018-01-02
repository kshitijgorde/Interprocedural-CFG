// 
// Decompiled by Procyon v0.5.30
// 

public class StarData
{
    Double myDouble;
    String str;
    String[] CON;
    
    public StarData() {
        this.CON = new String[] { "And", "Ant", "Aps", "Aqr", "Aql", "Ara", "Ari", "Aur", "Boo", "Cae", "Cam", "Cnc", "CVn", "CMa", "CMi", "Cap", "Car", "Cas", "Cen", "Cep", "Cet", "Cha", "Cir", "Col", "Com", "CrA", "CrB", "Crv", "Crt", "Cru", "Cyg", "Del", "Dor", "Dra", "Equ", "Eri", "For", "Gem", "Gru", "Her", "Hor", "Hya", "Hyi", "Ind", "Lac", "Leo", "LMi", "Lep", "Lib", "Lup", "Lyn", "Lyr", "Men", "Mic", "Mon", "Mus", "Nor", "Oct", "Oph", "Ori", "Pav", "Peg", "Per", "Phe", "Pic", "Psc", "PsA", "Pup", "Pyx", "Ret", "Sge", "Sgr", "Sco", "Scl", "Sct", "Ser", "Sex", "Tau", "Tel", "Tri", "TrA", "Tuc", "UMa", "UMi", "Vel", "Vir", "Vol", "Vul" };
    }
    
    public int nStarData() {
        return 135;
    }
    
    public String star(final int star, final int what) {
        final String[][] L = { { "0.0", "0.0", "0.0", "Aries", "Ari" }, { "17.7611", "-29.0078", "0.0", "GalCen", "Sgr" }, { "2.530", "89.264", "1.97", "Polaris", "UMi" }, { "6.753", "-16.713", "-1.44", "Sirius", "CMa" }, { "6.399", "-52.696", "-0.62", "Canopus", "Car" }, { "14.2612", "19.187", "-0.05", "Arcturus", "Boo" }, { "14.661", "-60.835", "-0.01", "Rigil Cen", "Cen" }, { "18.6156", "38.783", "0.03", "Vega", "Lyr" }, { "5.2781", "45.999", "0.08", "Capella", "Aur" }, { "5.242", "-8.202", "0.18", "Rigel", "Ori" }, { "7.6551", "5.228", "0.40", "Procyon", "CMi" }, { "5.9195", "7.407", "0.45", "Betelgeuse", "Ori" }, { "14.064", "-60.373", "0.61", "Hadar", "Cen" }, { "19.8463", "8.867", "0.76", "Altair", "Aqu" }, { "12.443", "-63.099", "0.77", "Acrux", "Cru" }, { "4.5987", "16.510", "0.87", "Aldebaran", "Tau" }, { "13.420", "-11.161", "0.98", "Spica", "Vir" }, { "16.490", "-26.432", "1.06", "Antares", "Sco" }, { "7.7554", "28.026", "1.16", "Pollux", "Gem" }, { "22.961", "-29.622", "1.17", "Fomalhaut", "PAu" }, { "20.6905", "45.280", "1.25", "Deneb", "Cyg" }, { "12.795", "-59.689", "1.25", "Mimosa", "Cru" }, { "10.1396", "11.967", "1.36", "Regulus", "Leo" }, { "6.977", "-28.972", "1.50", "Adhara", "CMa" }, { "7.5767", "31.889", "1.58", "Castor", "Gem" }, { "12.519", "-57.113", "1.59", "Gacrux", "Cru" }, { "17.5666", "-37.1112", "1.62", "Shaula", "Sco" }, { "5.4189", "6.350", "1.64", "Bellatrix", "Ori" }, { "5.4382", "28.608", "1.65", "Alnath", "Tau" }, { "9.2201", "-69.717", "1.67", "Miaplacidus", "Car" }, { "5.604", "-1.202", "1.69", "Alnilam", "Ori" }, { "22.1372", "-46.961", "1.73", "Alnair", "Gru" }, { "5.68", "-1.9", "1.74", "Alnitak", "Ori" }, { "12.900", "55.960", "1.76", "Alioth", "UMa" }, { "18.403", "-34.384", "1.79", "Kaus Australis", "Sgr" }, { "3.4054", "49.861", "1.79", "Mirphak", "Per" }, { "11.0622", "61.751", "1.81", "Dubhe", "Uma" }, { "7.1399", "-26.393", "1.83", "Wezen", "CMa" }, { "13.7924", "49.313", "1.85", "Alkaid", "UMa" }, { "5.9921", "44.947", "1.90", "Menkalinan", "Aur" }, { "6.6285", "16.399", "1.93", "Alhena", "Gem" }, { "6.38", "-18.0", "1.98", "Mirzam", "CMa" }, { "9.4598", "-8.659", "1.99", "Alphard", "Hya" }, { "10.3328", "19.842", "2.01", "Algieba", "Leo" }, { "2.1195", "23.463", "2.01", "Hamal", "Ari" }, { "0.7265", "-17.987", "2.04", "Diphda", "Cet" }, { "18.921", "-26.297", "2.05", "Nunki", "Sgr" }, { "0.1398", "29.091", "2.07", "Alpheratz", "And" }, { "1.1621", "35.621", "2.07", "Mirach", "And" }, { "14.8451", "74.155", "2.07", "Kochab", "UMi" }, { "17.5822", "12.561", "2.08", "Rasalhague", "Oph" }, { "3.1361", "40.956", "2.09", "Algol", "Per" }, { "2.065", "42.330", "2.10", "Alamac", "And" }, { "5.7959", "-9.6697", "2.1", "Saiph", "Ori" }, { "11.8177", "14.572", "2.14", "Denebola", "Leo" }, { "0.9451", "60.717", "2.15", "Cih", "Cas" }, { "15.5781", "26.715", "2.22", "Alphekka", "CBo" }, { "20.3705", "40.257", "2.23", "Sadir", "Cyg" }, { "13.3987", "54.925", "2.23", "Mizar", "UMa" }, { "0.6751", "56.537", "2.24", "Shedir", "Cas" }, { "17.9436", "51.489", "2.24", "Etamin", "Dra" }, { "5.53", "-0.3", "2.25", "Mintaka", "Ori" }, { "0.1528", "59.150", "2.28", "Caph", "Cas" }, { "16.00", "-22.6", "2.29", "Dschubba", "Sco" }, { "11.0307", "56.382", "2.34", "Merak", "UMa" }, { "14.7498", "27.074", "2.35", "Izar", "Boo" }, { "21.7364", "9.875", "2.38", "Enif", "Peg" }, { "11.8971", "53.695", "2.41", "Phekda", "UMa" }, { "17.17", "-15.7", "2.43", "Sabib", "Oph" }, { "23.0629", "28.082", "2.44", "Scheat", "Peg" }, { "21.3096", "62.585", "2.45", "Alderamin", "Cep" }, { "20.7701", "33.969", "2.48", "Gienah", "Cyg" }, { "23.0793", "15.205", "2.49", "Markab", "Vel" }, { "3.038", "4.090", "2.54", "Menkar", "Cet" }, { "16.62", "-10.6", "2.54", "Han", "Oph" }, { "11.2351", "20.524", "2.56", "Zosma", "Leo" }, { "16.08", "-19.8", "2.56", "Graffias", "Sco" }, { "5.55", "-17.8", "2.58", "Arneb", "Lep" }, { "12.27", "-17.5", "2.58", "Giehnah Gh.", "Cor" }, { "15.28", "-9.4", "2.61", "Zubeneschemali", "Lib" }, { "15.7378", "6.426", "2.63", "Unukalhai", "Ser" }, { "1.9107", "20.808", "2.64", "Sharatan", "Ari" }, { "14.85", "-16.0", "2.64", "Zubenelgenubi", "Lib" }, { "5.9953", "37.213", "2.65", "tet Aur", "Aur" }, { "1.4302", "60.235", "2.66", "Ruchbar", "Cas" }, { "13.9114", "18.399", "2.68", "Muphrid", "Boo" }, { "4.9499", "33.166", "2.69", "Hassaleh", "Aur" }, { "19.771", "10.613", "2.72", "Tarazed", "Aqu" }, { "16.23", "-3.7", "2.73", "Yed Prior", "Oph" }, { "16.3999", "61.514", "2.73", "eta Dra", "Dra" }, { "12.70", "-1.5", "2.74", "Porrima", "Vir" }, { "5.83", "-5.9", "2.75", "Hatysa", "Ori" }, { "17.7245", "4.567", "2.76", "Cebalrai", "Oph" }, { "5.13", "-5.1", "2.78", "Kursa", "Eri" }, { "16.5037", "21.490", "2.78", "Cornephoros", "Her" }, { "17.2441", "14.390", "2.78", "Rasalgethi", "Her" }, { "17.5072", "52.301", "2.79", "Rastaban", "Dra" }, { "12.9339", "38.318", "2.80", "Cor Caroli", "CVn" }, { "5.47", "-20.8", "2.81", "Nihal", "Lep" }, { "16.6882", "31.602", "2.81", "Rutilicus", "Her" }, { "0.2206", "15.184", "2.83", "Algenib", "Peg" }, { "3.9022", "31.884", "2.84", "Menkib", "Per" }, { "13.0363", "10.959", "2.85", "Vindemiatrix", "Vir" }, { "21.78", "-16.1", "2.85", "Deneb Alg.", "Cap" }, { "3.7914", "24.105", "2.85", "Alcyone", "Tau" }, { "19.7496", "45.131", "2.86", "delta Cyg", "Cyg" }, { "6.3827", "22.514", "2.87", "Tejat", "Gem" }, { "19.17", "-21.0", "2.88", "Albaldah", "Sgr" }, { "15.98", "-26.1", "2.89", "Pi Sco", "Sco" }, { "7.4525", "8.289", "2.89", "Gomeisa", "CMi" }, { "3.97", "40.0", "2.90", "eps Per", "Per" }, { "19.5119", "27.960", "2.90", "Albireo", "Cyg" }, { "21.53", "-5.6", "2.90", "Sadalsuud", "Aqu" }, { "3.08", "53.5", "2.91", "gam Per", "Per" }, { "22.717", "30.221", "2.93", "Matar", "Peg" }, { "12.50", "-16.5", "2.94", "Algorel", "Peg" }, { "22.1", "-0.3", "2.95", "Sadalmelik", "Aqu" }, { "3.97", "-13.5", "2.97", "Zaurak", "Aqu" }, { "5.6275", "21.143", "2.97", "Alheka", "Tau" }, { "9.77", "23.8", "2.97", "eps Leo", "Leo" }, { "13.32", "-23.2", "2.99", "gam Hyd", "Hya" }, { "19.08", "13.9", "2.99", "zet Aqu", "Aqu" }, { "2.17", "35.0", "3.00", "bet Tri", "Tri" }, { "11.17", "44.5", "3.00", "psi UMa", "UMa" }, { "15.35", "71.8", "3.00", "gam UMi", "UMi" }, { "3.72", "47.8", "3.01", "del Per", "Per" }, { "7.05", "-23.8", "3.02", "omi CMi", "CMi" }, { "12.17", "-22.6", "3.02", "Minkar", "Cor" }, { "5.03", "43.8", "3.03", "Almaaz", "Aur" }, { "14.53", "38.3", "3.04", "gam Boo", "Boo" }, { "20.35", "-14.8", "3.05", "bet Cap", "Cap" }, { "6.6819", "25.183", "3.06", "Mebsuta", "Gem" }, { "10.37", "41.5", "3.06", "mu UMa", "Uma" }, { "19.22", "67.7", "3.07", "Tais", "Dra" }, { "8.92", "5.9", "3.11", "zet Hyd", "Hya" }, { "12.2571", "57.0325", "3.3", "Megrez", "UMa" } };
        if (what == 5) {
            return "5";
        }
        return L[star][what];
    }
    
    public double getRA(final int i) {
        double ra = 0.0;
        this.str = this.star(i, 0);
        try {
            this.myDouble = Double.valueOf(this.str);
            ra = this.myDouble;
        }
        catch (NumberFormatException ex) {}
        return ra;
    }
    
    public double getDec(final int i) {
        double dec = 0.0;
        this.str = this.star(i, 1);
        try {
            this.myDouble = Double.valueOf(this.str);
            dec = this.myDouble;
        }
        catch (NumberFormatException ex) {}
        return dec;
    }
    
    public double getMag(final int i) {
        double mag = 0.0;
        this.str = this.star(i, 2);
        try {
            this.myDouble = Double.valueOf(this.str);
            mag = this.myDouble;
        }
        catch (NumberFormatException ex) {}
        return mag;
    }
}
