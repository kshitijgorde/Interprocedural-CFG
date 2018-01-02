import java.util.GregorianCalendar;

// 
// Decompiled by Procyon v0.5.30
// 

public class PersonData
{
    String name;
    JulianDate btJulian;
    int gender;
    int matchscore;
    String picURL;
    
    public PersonData(final String name) {
        this.name = "";
        this.picURL = "x";
        this.name = name;
    }
    
    public void compareAll(final PersonData personData) {
        this.setMatch(this.compareEmotional(personData) + this.compareIntellectual(personData) + this.comparePhysical(personData));
    }
    
    public int compareEmotional(final PersonData personData) {
        double n = (this.getJulian(1) - personData.getJulian(1)) % 28;
        if (n < 0.0) {
            n *= -1.0;
        }
        int n2;
        if (n < 14.0) {
            n2 = 100 - (int)(n / 1.4 * 10.0);
        }
        else {
            n2 = (int)((n - 14.0) / 1.4 * 10.0);
        }
        return n2;
    }
    
    public int compareIntellectual(final PersonData personData) {
        double n = (this.getJulian(1) - personData.getJulian(1)) % 33;
        if (n < 0.0) {
            n *= -1.0;
        }
        int n2;
        if (n < 16.5) {
            n2 = 100 - (int)(n / 1.65 * 10.0);
        }
        else {
            n2 = (int)((n - 16.5) / 1.65 * 10.0);
        }
        return n2;
    }
    
    public int comparePhysical(final PersonData personData) {
        double n = (this.getJulian(1) - personData.getJulian(1)) % 23;
        if (n < 0.0) {
            n *= -1.0;
        }
        int n2;
        if (n < 11.5) {
            n2 = 100 - (int)(n / 1.15 * 10.0);
        }
        else {
            n2 = (int)((n - 11.5) / 1.15 * 10.0);
        }
        return n2;
    }
    
    public String getDayOfWeek(int n) {
        n %= 7;
        switch (new GregorianCalendar().get(7)) {
            case 0: {
                return "Sun";
            }
            case 1: {
                return "Mon";
            }
            case 2: {
                return "Tue";
            }
            case 3: {
                return "Wed";
            }
            case 4: {
                return "Thu";
            }
            case 5: {
                return "Fri";
            }
            case 6: {
                return "Sat";
            }
            default: {
                return "Err";
            }
        }
    }
    
    public int getDaysAlive() {
        return this.btJulian.todayToJulian() - this.btJulian.getJulianDay();
    }
    
    public int getEmotional(final int n) {
        return (int)((Math.sin((this.btJulian.todayToJulian() - this.btJulian.getJulianDay() + n) % 28 * 0.2243994752564138) + 1.0) / 2.0 * 100.0);
    }
    
    public int getGender() {
        return this.gender;
    }
    
    public int getIntellectual(final int n) {
        return (int)((Math.sin((this.btJulian.todayToJulian() - this.btJulian.getJulianDay() + n) % 33 * 0.19039955476301776) + 1.0) / 2.0 * 100.0);
    }
    
    public int getJulian(final int n) {
        if (n == 1) {
            return this.btJulian.getJulianDay();
        }
        return this.btJulian.todayToJulian();
    }
    
    public int getMatch() {
        return this.matchscore;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getPhysical(final int n) {
        return (int)((Math.sin(this.getDaysAlive() % 23 * 0.2731819698773733) + 1.0) / 2.0 * 100.0);
    }
    
    public String getPic() {
        if (!this.picURL.equals("x")) {
            return this.picURL;
        }
        return "nopic.gif";
    }
    
    public void setGender(final int gender) {
        this.gender = gender;
    }
    
    public void setJulian(final int n, final int n2, final int n3) throws Exception {
        this.btJulian = new JulianDate(1, 1, 1970);
        if (n > 12 || n2 > 31) {
            throw new Exception();
        }
        if (n != 1 && n != 3 && n != 5 && n != 7 && n != 8 && n != 10 && n != 12) {
            if (n2 > 30) {
                throw new Exception();
            }
            if (n == 2 && n2 > 29) {
                throw new Exception();
            }
        }
        this.btJulian = new JulianDate(n, n2, n3);
    }
    
    public void setMatch(final int matchscore) {
        this.matchscore = matchscore;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setPic(final String picURL) {
        if (!picURL.toUpperCase().equals("x")) {
            this.picURL = picURL;
        }
    }
}
