// 
// Decompiled by Procyon v0.5.30
// 

class City
{
    public String name;
    public String country;
    public double popMillions;
    public double latDegrees;
    public double lonDegrees;
    public double timeZone;
    public boolean haveLanguages;
    public Language[] languages;
    
    public City(final String name, final String country, final double popMillions, final double latDegrees, final double lonDegrees, final double timeZone) {
        this.name = name;
        this.country = country;
        this.popMillions = popMillions;
        this.latDegrees = latDegrees;
        this.lonDegrees = lonDegrees;
        this.timeZone = timeZone;
        this.haveLanguages = false;
    }
    
    public City(final String name, final String country, final double popMillions, final double latDegrees, final double lonDegrees, final double timeZone, final String s, final String s2) {
        this.name = name;
        this.country = country;
        this.popMillions = popMillions;
        this.latDegrees = latDegrees;
        this.lonDegrees = lonDegrees;
        this.timeZone = timeZone;
        this.haveLanguages = true;
        this.languages = new Language[] { new Language(s), new Language(s2) };
    }
    
    public City(final String name, final String country, final double popMillions, final double latDegrees, final double lonDegrees, final double timeZone, final String s) {
        this.name = name;
        this.country = country;
        this.popMillions = popMillions;
        this.latDegrees = latDegrees;
        this.lonDegrees = lonDegrees;
        this.timeZone = timeZone;
        this.haveLanguages = true;
        this.languages = new Language[] { new Language(s) };
    }
    
    public boolean isInLanguageList(final Language language) {
        if (!this.haveLanguages) {
            return false;
        }
        for (int i = 0; i < this.languages.length; ++i) {
            if (language.equals(this.languages[i])) {
                return true;
            }
        }
        return false;
    }
}
