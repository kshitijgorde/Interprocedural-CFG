import java.util.SimpleTimeZone;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class dat
{
    Vector city;
    SimpleTimeZone NewYork;
    SimpleTimeZone LosAngeles;
    SimpleTimeZone Chicago;
    SimpleTimeZone Denver;
    SimpleTimeZone BuenosAires;
    SimpleTimeZone MexicoCity;
    SimpleTimeZone Amsterdam;
    SimpleTimeZone Athens;
    SimpleTimeZone Berlin;
    SimpleTimeZone Brussels;
    SimpleTimeZone London;
    SimpleTimeZone Madrid;
    SimpleTimeZone Moscow;
    SimpleTimeZone Paris;
    SimpleTimeZone Sofia;
    SimpleTimeZone Stockholm;
    SimpleTimeZone Viena;
    SimpleTimeZone Montreal;
    SimpleTimeZone Montevideo;
    SimpleTimeZone Vancouver;
    SimpleTimeZone Lima;
    SimpleTimeZone Budapest;
    SimpleTimeZone Zurich;
    SimpleTimeZone Istanbul;
    SimpleTimeZone Seoul;
    SimpleTimeZone Tokyo;
    SimpleTimeZone HongKong;
    SimpleTimeZone Rome;
    SimpleTimeZone Helsinki;
    SimpleTimeZone Johannesburg;
    SimpleTimeZone Cairo;
    SimpleTimeZone Tripoli;
    SimpleTimeZone Manila;
    SimpleTimeZone Sydney;
    SimpleTimeZone Singapore;
    SimpleTimeZone Shanghai;
    SimpleTimeZone Brisbane;
    SimpleTimeZone Caracas;
    SimpleTimeZone Macao;
    SimpleTimeZone Bogota;
    SimpleTimeZone Abidjan;
    SimpleTimeZone Casablanca;
    SimpleTimeZone Djibouti;
    SimpleTimeZone Harare;
    SimpleTimeZone Khartoum;
    SimpleTimeZone Kinshasa;
    SimpleTimeZone Tijuana;
    SimpleTimeZone Managua;
    SimpleTimeZone Phoenix;
    SimpleTimeZone Bucharest;
    SimpleTimeZone Lisbon;
    SimpleTimeZone Prague;
    SimpleTimeZone Oslo;
    SimpleTimeZone Dublin;
    SimpleTimeZone Copenhagen;
    SimpleTimeZone Warsaw;
    SimpleTimeZone Calcutta;
    SimpleTimeZone Tehran;
    SimpleTimeZone Bangkok;
    SimpleTimeZone Beirut;
    
    public dat() {
        (this.city = new Vector()).addElement("Abidjan");
        this.city.addElement("Amsterdam");
        this.city.addElement("Athens");
        this.city.addElement("Bangkok");
        this.city.addElement("Beirut");
        this.city.addElement("Berlin");
        this.city.addElement("Bogota");
        this.city.addElement("Brisbane");
        this.city.addElement("Brussels");
        this.city.addElement("Bucharest");
        this.city.addElement("Budapest");
        this.city.addElement("Buenos Aires");
        this.city.addElement("Cairo");
        this.city.addElement("Calcutta");
        this.city.addElement("Caracas");
        this.city.addElement("Casablanca");
        this.city.addElement("Chicago");
        this.city.addElement("Copenhagen");
        this.city.addElement("Denver");
        this.city.addElement("Djibouti");
        this.city.addElement("Dublin");
        this.city.addElement("Harare");
        this.city.addElement("Helsinki");
        this.city.addElement("Hong Kong");
        this.city.addElement("Istanbul");
        this.city.addElement("Johannesburg");
        this.city.addElement("Khartoum");
        this.city.addElement("Kinshasa");
        this.city.addElement("Lima");
        this.city.addElement("Lisbon");
        this.city.addElement("London");
        this.city.addElement("Los Angeles");
        this.city.addElement("Madrid");
        this.city.addElement("Macao");
        this.city.addElement("Managua");
        this.city.addElement("Manila");
        this.city.addElement("Mexico City");
        this.city.addElement("Montevideo");
        this.city.addElement("Montreal");
        this.city.addElement("Moscow");
        this.city.addElement("New York");
        this.city.addElement("Oslo");
        this.city.addElement("Paris");
        this.city.addElement("Phoenix");
        this.city.addElement("Prague");
        this.city.addElement("Rome");
        this.city.addElement("Seoul");
        this.city.addElement("Shanghai");
        this.city.addElement("Singapore");
        this.city.addElement("Sofia");
        this.city.addElement("Stockholm");
        this.city.addElement("Sydney");
        this.city.addElement("Tehran");
        this.city.addElement("Tijuana");
        this.city.addElement("Tokyo");
        this.city.addElement("Tripoli");
        this.city.addElement("Vancouver");
        this.city.addElement("Viena");
        this.city.addElement("Warsaw");
        this.city.addElement("Zurich");
        this.NewYork = new SimpleTimeZone(-18000000, "America/New_York", 3, 1, 1, 7200000, 9, -1, 1, 7200000);
        this.LosAngeles = new SimpleTimeZone(-28800000, "America/Los_Angeles", 3, 1, 1, 7200000, 9, -1, 1, 7200000);
        this.Chicago = new SimpleTimeZone(-21600000, "America/Chicago", 3, 1, 1, 7200000, 9, -1, 1, 7200000);
        this.Denver = new SimpleTimeZone(-25200000, "America/Denver", 3, 1, 1, 7200000, 9, -1, 1, 7200000);
        this.Montreal = new SimpleTimeZone(-18000000, "America/Montreal", 3, 1, 1, 7200000, 9, -1, 1, 7200000);
        this.BuenosAires = new SimpleTimeZone(-10800000, "America/Buenos_Aires", 0, 0, 0, 0, 0, 0, 0, 0);
        this.MexicoCity = new SimpleTimeZone(-21600000, "America/Mexico_City", 3, 1, 1, 7200000, 9, -1, 1, 7200000);
        this.Amsterdam = new SimpleTimeZone(3600000, "Europe/Amsterdam", 2, -1, 1, 7200000, 9, -1, 1, 7200000);
        this.Athens = new SimpleTimeZone(7200000, "Europe/Athens", 2, -1, 1, 10800000, 9, -1, 1, 10800000);
        this.Berlin = new SimpleTimeZone(3600000, "Europe/Berlin", 2, -1, 1, 7200000, 9, -1, 1, 7200000);
        this.Brussels = new SimpleTimeZone(3600000, "Europe/Brussels", 2, -1, 1, 7200000, 9, -1, 1, 7200000);
        this.London = new SimpleTimeZone(0, "Europe/London", 2, -1, 1, 3600000, 9, -1, 1, 3600000);
        this.Madrid = new SimpleTimeZone(3600000, "Europe/Madrid", 2, -1, 1, 7200000, 9, -1, 1, 7200000);
        this.Moscow = new SimpleTimeZone(10800000, "Europe/Moscow", 2, -1, 1, 7200000, 9, -1, 1, 10800000);
        this.Paris = new SimpleTimeZone(3600000, "Europe/Paris", 2, -1, 1, 7200000, 9, -1, 1, 7200000);
        this.Sofia = new SimpleTimeZone(7200000, "Europe/Sofia", 2, -1, 1, 0, 9, -1, 1, 0);
        this.Stockholm = new SimpleTimeZone(3600000, "Europe/Stockholm", 2, -1, 1, 7200000, 9, -1, 1, 7200000);
        this.Viena = new SimpleTimeZone(3600000, "Europe/Vienna", 2, -1, 1, 7200000, 9, -1, 1, 7200000);
        this.Montevideo = new SimpleTimeZone(-10800000, "America/Montevideo", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Vancouver = new SimpleTimeZone(-28800000, "America/Vancouver", 3, 1, 1, 7200000, 9, -1, 1, 7200000);
        this.Lima = new SimpleTimeZone(-18000000, "America/Lima", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Budapest = new SimpleTimeZone(3600000, "Europe/Budapest", 2, -1, 1, 7200000, 9, -1, 1, 7200000);
        this.Zurich = new SimpleTimeZone(3600000, "Europe/Zurich", 2, -1, 1, 7200000, 9, -1, 1, 7200000);
        this.Istanbul = new SimpleTimeZone(7200000, "Europe/Istanbul", 2, -1, 1, 10800000, 9, -1, 1, 10800000);
        this.Seoul = new SimpleTimeZone(32400000, "Asia/Seoul", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Tokyo = new SimpleTimeZone(32400000, "Asia/Tokyo", 0, 0, 0, 0, 0, 0, 0, 0);
        this.HongKong = new SimpleTimeZone(28800000, "Asia/Hong_Kong", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Rome = new SimpleTimeZone(3600000, "Europe/Rome", 2, -1, 1, 7200000, 9, -1, 1, 7200000);
        this.Helsinki = new SimpleTimeZone(7200000, "Europe/Helsinki", 2, -1, 1, 10800000, 9, -1, 1, 10800000);
        this.Johannesburg = new SimpleTimeZone(7200000, "Africa/Johannesburg", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Cairo = new SimpleTimeZone(7200000, "Africa/Cairo", 3, -1, 6, 3600000, 8, -1, 6, 10800000);
        this.Tripoli = new SimpleTimeZone(3600000, "Africa/Tripoli", 2, -1, 5, 7200000, 9, 1, 5, 10800000);
        this.Manila = new SimpleTimeZone(28800000, "Asia/Manila", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Sydney = new SimpleTimeZone(36000000, "Australia/Sydney", 9, -1, 1, 7200000, 2, -1, 1, 10800000);
        this.Singapore = new SimpleTimeZone(28800000, "Asia/Singapore", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Shanghai = new SimpleTimeZone(28800000, "Asia/Shanghai", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Brisbane = new SimpleTimeZone(36000000, "Australia/Brisbane", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Caracas = new SimpleTimeZone(-14400000, "America/Caracas", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Macao = new SimpleTimeZone(28800000, "Asia/Macao", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Bogota = new SimpleTimeZone(-18000000, "America/Bogota", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Abidjan = new SimpleTimeZone(0, "Africa/Abidjan", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Casablanca = new SimpleTimeZone(0, "Africa/Casablanca", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Djibouti = new SimpleTimeZone(10800000, "Africa/Djibouti", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Harare = new SimpleTimeZone(7200000, "Africa/Harare", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Khartoum = new SimpleTimeZone(7200000, "Africa/Khartoum", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Kinshasa = new SimpleTimeZone(3600000, "Africa/Kinshasa", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Tijuana = new SimpleTimeZone(-28800000, "America/Tijuana", 3, 1, 1, 7200000, 9, -1, 1, 7200000);
        this.Managua = new SimpleTimeZone(-18000000, "America/Managua", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Phoenix = new SimpleTimeZone(-25200000, "America/Phoenix", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Bucharest = new SimpleTimeZone(7200000, "Europe/Bucharest", 2, -1, 1, 0, 9, -1, 1, 0);
        this.Lisbon = new SimpleTimeZone(0, "Europe/Lisbon", 2, -1, 1, 3600000, 9, -1, 1, 3600000);
        this.Prague = new SimpleTimeZone(3600000, "Europe/Prague", 2, -1, 1, 7200000, 9, -1, 1, 7200000);
        this.Oslo = new SimpleTimeZone(3600000, "Europe/Oslo", 2, -1, 1, 7200000, 9, -1, 1, 7200000);
        this.Dublin = new SimpleTimeZone(0, "Europe/Dublin", 2, -1, 1, 3600000, 9, -1, 1, 3600000);
        this.Copenhagen = new SimpleTimeZone(3600000, "Europe/Copenhagen", 2, -1, 1, 7200000, 9, -1, 1, 7200000);
        this.Warsaw = new SimpleTimeZone(3600000, "Europe/Warsaw", 2, -1, 1, 3600000, 9, -1, 1, 7200000);
        this.Calcutta = new SimpleTimeZone(19800000, "Asia/Calcutta", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Tehran = new SimpleTimeZone(12600000, "Asia/Tehran", 2, 21, 0, 0, 8, 23, 0, 0);
        this.Bangkok = new SimpleTimeZone(25200000, "Asia/Bangkok", 0, 0, 0, 0, 0, 0, 0, 0);
        this.Beirut = new SimpleTimeZone(7200000, "Asia/Beirut", 2, -1, 1, 0, 8, -1, 1, 0);
    }
}
