// 
// Decompiled by Procyon v0.5.30
// 

class Language
{
    private String code;
    
    public Language(final String s) {
        this.code = this.standardizeCode(s);
    }
    
    public boolean equals(final Language language) {
        return this.code.equals(language.code);
    }
    
    public String standardizeCode(final String s) {
        final String lowerCase = s.toLowerCase();
        if (lowerCase.length() == 2) {
            return lowerCase;
        }
        if (lowerCase.equals("german")) {
            return "de";
        }
        if (lowerCase.equals("greek")) {
            return "el";
        }
        if (lowerCase.equals("spanish")) {
            return "es";
        }
        if (lowerCase.equals("dutch")) {
            return "nl";
        }
        if (lowerCase.equals("chinese")) {
            return "zh";
        }
        if (lowerCase.startsWith("por")) {
            return "pt";
        }
        if (lowerCase.startsWith("swe")) {
            return "sv";
        }
        if (lowerCase.startsWith("tur")) {
            return "tr";
        }
        return lowerCase.substring(0, 1);
    }
}
