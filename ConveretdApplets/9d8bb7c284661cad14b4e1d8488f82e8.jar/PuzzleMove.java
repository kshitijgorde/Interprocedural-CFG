// 
// Decompiled by Procyon v0.5.30
// 

public class PuzzleMove
{
    final int \u0165 = 2;
    final int \u0166 = 2;
    final int \u0167 = 2;
    int \u0168;
    String \u0169;
    int \u016a;
    int \u016b;
    int \u016c;
    int \u016d;
    int \u016e;
    int \u016f;
    
    PuzzleMove() {
        this.\u016f = 100;
        this.\u0168 = 0;
        this.\u0169 = "";
        this.\u016a = 0;
        this.\u016b = 0;
    }
    
    PuzzleMove(final int \u0169, final String \u01692, final int \u016b, final int \u016b2) {
        this.\u016f = 100;
        this.\u0168 = \u0169;
        this.\u0169 = \u01692;
        this.\u016a = \u016b;
        this.\u016b = \u016b2;
    }
    
    PuzzleMove(final int \u0169, final int \u016d, final int \u016d2, final int \u016f) {
        this.\u016f = 100;
        this.\u0168 = \u0169;
        this.\u016c = \u016d;
        this.\u016d = \u016d2;
        this.\u016e = \u016f;
    }
    
    PuzzleMove(final String s) {
        this.\u016f = 100;
        this.\u0168 = this.convertToInt(s.substring(0, 2));
        this.\u016a = this.convertToInt(s.substring(2, 4));
        this.\u016b = this.convertToInt(s.substring(4, 6));
        this.\u0169 = s.substring(6);
    }
    
    public int getUserIDValue() {
        return this.\u0168;
    }
    
    public String getCodeValue() {
        return this.\u0169;
    }
    
    public int getToXValue() {
        return this.\u016a;
    }
    
    public int getToYValue() {
        return this.\u016b;
    }
    
    public String getMoveString() {
        String s = String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf("")).append(this.convertToStr(this.\u0168, 2)).toString())).append(this.convertToStr(this.\u016a, 2)).toString())).append(this.convertToStr(this.\u016b, 2)).toString()) + this.\u0169;
        switch (this.\u0169.length()) {
            case 1: {
                s = String.valueOf(s) + "   ";
                break;
            }
            case 2: {
                s = String.valueOf(s) + "  ";
                break;
            }
            case 3: {
                s = String.valueOf(s) + " ";
                break;
            }
        }
        return s;
    }
    
    public String getSetMoveString() {
        String s = String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf("")).append(this.convertToStr(this.\u0168, 2)).toString())).append(this.convertToStr(this.\u016c, 2)).toString())).append(this.convertToStr(this.\u016d, 2)).toString())).append(this.convertToStr(this.\u016e, 2)).toString()) + this.\u0169;
        switch (this.\u0169.length()) {
            case 1: {
                s = String.valueOf(s) + "   ";
                break;
            }
            case 2: {
                s = String.valueOf(s) + "  ";
                break;
            }
            case 3: {
                s = String.valueOf(s) + " ";
                break;
            }
        }
        return s;
    }
    
    public void setMove(final String s) {
        this.\u0168 = this.convertToInt(s.substring(0, 2));
        this.\u016a = this.convertToInt(s.substring(2, 4));
        this.\u016b = this.convertToInt(s.substring(4, 6));
        this.\u0169 = s.substring(6);
    }
    
    public int convertToInt(String trim) {
        trim = trim.replace('-', ' ').trim();
        return Integer.parseInt(trim);
    }
    
    public String convertToStr(final String s) {
        return s.replace('-', ' ').trim();
    }
    
    public String convertToStr(final int n, final int n2) {
        final String string = new Integer(n).toString();
        final String s = "-------------------------------";
        int n3 = n2 - string.length();
        if (n3 < 0) {
            n3 = 0;
        }
        return String.valueOf(s.substring(0, n3)) + string;
    }
    
    public String convertToStr(final String s, final int n) {
        final String s2 = "-------------------------------";
        int n2 = n - s.length();
        if (n2 < 0) {
            n2 = 0;
        }
        return String.valueOf(s2.substring(0, n2)) + s;
    }
}
