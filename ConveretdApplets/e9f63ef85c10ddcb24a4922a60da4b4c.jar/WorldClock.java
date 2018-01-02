import java.awt.Event;
import java.awt.event.ItemEvent;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.StringTokenizer;
import java.awt.Color;
import java.applet.AudioClip;
import java.util.TimeZone;
import com.ibm.clock.IClock;
import java.awt.List;
import java.awt.event.ItemListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WorldClock extends Applet implements ItemListener
{
    List list;
    dat data;
    IClock clock;
    IClock dclock;
    TimeZone tz;
    QuickPanel p1;
    QuickPanel p2;
    AudioClip clip;
    String back;
    String bor;
    String dig;
    String text;
    String ltcolor;
    Color backcolor;
    Color borcolor;
    Color digcolor;
    Color tcolor;
    Color lcolor;
    StringTokenizer st;
    
    public void init() {
        this.back = this.getParameter("background_color");
        this.st = new StringTokenizer(this.back, ",");
        this.backcolor = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()));
        this.bor = this.getParameter("border_clock_color");
        this.st = new StringTokenizer(this.bor, ",");
        this.borcolor = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()));
        this.dig = this.getParameter("digit_clock_color");
        this.st = new StringTokenizer(this.dig, ",");
        this.digcolor = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()));
        this.text = this.getParameter("cities_text_color");
        this.st = new StringTokenizer(this.text, ",");
        this.tcolor = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()));
        this.ltcolor = this.getParameter("cities_background_color");
        this.st = new StringTokenizer(this.ltcolor, ",");
        this.lcolor = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()));
        this.setLayout(null);
        (this.p1 = new QuickPanel(2)).setBounds(0, 0, this.size().width - 2, this.size().height - 2);
        this.p1.setBackground(this.backcolor);
        this.add(this.p1);
        this.p1.setLayout(null);
        this.data = new dat();
        this.list = new List();
        for (int i = 0; i < this.data.city.size(); ++i) {
            this.list.add(this.data.city.elementAt(i).toString());
        }
        this.list.setBounds(this.size().width - 130, 10, 120, 100);
        this.list.setFont(new Font("Courier", 1, 14));
        this.list.setBackground(this.lcolor);
        this.list.setForeground(this.tcolor);
        this.p1.add(this.list);
        this.list.addItemListener(this);
        (this.clock = new IClock()).setDisplayMode(4);
        this.clock.setBounds(10, 10, 100, 100);
        this.clock.setBackground(this.backcolor);
        this.clock.setAnalogBorderColor(this.borcolor);
        this.p1.add(this.clock);
        (this.dclock = new IClock()).setDisplayMode(5);
        this.dclock.setBounds(2, 110, this.size().width - 4, 40);
        this.dclock.setDigitalBackGroundColor(this.backcolor);
        this.dclock.setDigitalForeGroundColor(this.digcolor);
        this.dclock.setDigitalDisplayStyle(0);
        this.dclock.setDigitalNumeralFont(new Font("TimesRoman", 1, 14));
        this.p1.add(this.dclock);
        this.clip = this.getAudioClip(this.getCodeBase(), "1.au");
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fill3DRect(0, 0, this.size().width - 1, this.size().height - 1, true);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (this.list.getSelectedItem() == "New York") {
            this.tz = this.data.NewYork;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Los Angeles") {
            this.tz = this.data.LosAngeles;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Chicago") {
            this.tz = this.data.Chicago;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Denver") {
            this.tz = this.data.Denver;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Buenos Aires") {
            this.tz = this.data.BuenosAires;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Mexico City") {
            this.tz = this.data.MexicoCity;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Amsterdam") {
            this.tz = this.data.Amsterdam;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Athens") {
            this.tz = this.data.Athens;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Berlin") {
            this.tz = this.data.Berlin;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Brussels") {
            this.tz = this.data.Brussels;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "London") {
            this.tz = this.data.London;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Madrid") {
            this.tz = this.data.Madrid;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Moscow") {
            this.tz = this.data.Moscow;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Paris") {
            this.tz = this.data.Paris;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Sofia") {
            this.tz = this.data.Sofia;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Stockholm") {
            this.tz = this.data.Stockholm;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Viena") {
            this.tz = this.data.Viena;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Montreal") {
            this.tz = this.data.Montreal;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Montevideo") {
            this.tz = this.data.Montevideo;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Vancouver") {
            this.tz = this.data.Vancouver;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Lima") {
            this.tz = this.data.Lima;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Budapest") {
            this.tz = this.data.Budapest;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Zurich") {
            this.tz = this.data.Zurich;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Istanbul") {
            this.tz = this.data.Istanbul;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Seoul") {
            this.tz = this.data.Seoul;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Tokyo") {
            this.tz = this.data.Tokyo;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Hong Kong") {
            this.tz = this.data.HongKong;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Rome") {
            this.tz = this.data.Rome;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Helsinki") {
            this.tz = this.data.Helsinki;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Johannesburg") {
            this.tz = this.data.Johannesburg;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Cairo") {
            this.tz = this.data.Cairo;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Tripoli") {
            this.tz = this.data.Tripoli;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Manila") {
            this.tz = this.data.Manila;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Sydney") {
            this.tz = this.data.Sydney;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Singapore") {
            this.tz = this.data.Singapore;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Shanghai") {
            this.tz = this.data.Shanghai;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Brisbane") {
            this.tz = this.data.Brisbane;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Caracas") {
            this.tz = this.data.Caracas;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Macao") {
            this.tz = this.data.Macao;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Bogota") {
            this.tz = this.data.Bogota;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Abidjan") {
            this.tz = this.data.Abidjan;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Casablanca") {
            this.tz = this.data.Casablanca;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Djibouti") {
            this.tz = this.data.Djibouti;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Harare") {
            this.tz = this.data.Harare;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Khartoum") {
            this.tz = this.data.Khartoum;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Kinshasa") {
            this.tz = this.data.Kinshasa;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Tijuana") {
            this.tz = this.data.Tijuana;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Managua") {
            this.tz = this.data.Managua;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Phoenix") {
            this.tz = this.data.Phoenix;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Bucharest") {
            this.tz = this.data.Bucharest;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Lisbon") {
            this.tz = this.data.Lisbon;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Prague") {
            this.tz = this.data.Prague;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Oslo") {
            this.tz = this.data.Oslo;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Dublin") {
            this.tz = this.data.Dublin;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Copenhagen") {
            this.tz = this.data.Copenhagen;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Warsaw") {
            this.tz = this.data.Warsaw;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Calcutta") {
            this.tz = this.data.Calcutta;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Tehran") {
            this.tz = this.data.Tehran;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Bangkok") {
            this.tz = this.data.Bangkok;
            this.clock.setTimeZone(this.tz);
        }
        if (this.list.getSelectedItem() == "Beirut") {
            this.tz = this.data.Beirut;
            this.clock.setTimeZone(this.tz);
        }
        this.clip.play();
        this.dclock.setTimeZone(this.tz);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus("Unregistered version ***World Clock***....www.zmei-soft.com");
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.showStatus("Unregistered version ***World Clock***....www.zmei-soft.com");
        return true;
    }
}
