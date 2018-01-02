import java.util.TimeZone;
import java.util.GregorianCalendar;

// 
// Decompiled by Procyon v0.5.30
// 

class WorldTime
{
    int zone;
    int deviation;
    GregorianCalendar gc;
    TimeZone tz;
    int part0;
    int part2;
    int gmt;
    String timezone;
    
    public WorldTime(final int zone) {
        System.out.println("New WorldTime Object made");
        this.zone = zone;
        this.gc = new GregorianCalendar();
        this.tz = this.gc.getTimeZone();
        this.deviation = this.getDeviation();
    }
    
    private int getDeviation() {
        return this.tz.getRawOffset() / 3600000;
    }
    
    public int getHour(final int n) {
        this.gc = new GregorianCalendar();
        this.gmt = this.gc.get(11) - this.deviation + this.zone;
        if (this.zone == 13) {
            this.gmt = this.gc.get(11);
        }
        if (this.gmt > 23) {
            this.gmt -= 24;
        }
        if (this.gmt < 0) {
            this.gmt += 24;
        }
        this.part2 = this.gmt % 10;
        if (n == 1) {
            return (this.gmt - this.part2) / 10;
        }
        return this.part2;
    }
    
    public int getMinutes(final int n) {
        this.gc = new GregorianCalendar();
        this.part0 = this.gc.get(12);
        this.part2 = this.part0 % 10;
        if (n == 1) {
            return (this.part0 - this.part2) / 10;
        }
        return this.part2;
    }
    
    public int getSeconds(final int n) {
        this.gc = new GregorianCalendar();
        this.part0 = this.gc.get(13);
        this.part2 = this.part0 % 10;
        if (n == 1) {
            return (this.part0 - this.part2) / 10;
        }
        return this.part2;
    }
    
    public String getTimeZone() {
        if (this.zone == 1) {
            this.timezone = "Paris";
        }
        if (this.zone == 2) {
            this.timezone = "Athens";
        }
        if (this.zone == 3) {
            this.timezone = "Moskou";
        }
        if (this.zone == 4) {
            this.timezone = "Kabul";
        }
        if (this.zone == 5) {
            this.timezone = "Tasjkent";
        }
        if (this.zone == 6) {
            this.timezone = "Colombo";
        }
        if (this.zone == 7) {
            this.timezone = "Bangkok";
        }
        if (this.zone == 8) {
            this.timezone = "Hong Kong";
        }
        if (this.zone == 9) {
            this.timezone = "Tokio";
        }
        if (this.zone == 10) {
            this.timezone = "Sydney";
        }
        if (this.zone == 11) {
            this.timezone = "Solomon Isle";
        }
        if (this.zone == 12) {
            this.timezone = "Fiji";
        }
        if (this.zone == 0) {
            this.timezone = "London (GMT)";
        }
        if (this.zone == -1) {
            this.timezone = "Asure";
        }
        if (this.zone == -2) {
            this.timezone = "Mid-Atlantic";
        }
        if (this.zone == -3) {
            this.timezone = "Buenos-Aires";
        }
        if (this.zone == -4) {
            this.timezone = "Santiago";
        }
        if (this.zone == -5) {
            this.timezone = "Lima";
        }
        if (this.zone == -6) {
            this.timezone = "Mexico City";
        }
        if (this.zone == -7) {
            this.timezone = "Arizona";
        }
        if (this.zone == -8) {
            this.timezone = "Pacific Time";
        }
        if (this.zone == -9) {
            this.timezone = "Alaska";
        }
        if (this.zone == -10) {
            this.timezone = "Hawaii";
        }
        if (this.zone == -11) {
            this.timezone = "Samoa";
        }
        if (this.zone == -12) {
            this.timezone = "Kwalajein";
        }
        if (this.zone == 13) {
            this.timezone = "Local Time";
        }
        return this.timezone;
    }
}
