import java.awt.Container;
import java.awt.GridLayout;
import java.awt.CheckboxGroup;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.Image;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Label;
import java.util.Hashtable;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class EuroCalc extends Applet
{
    public static String CURR;
    public static String PATH;
    public static String CURR_URL;
    public static Hashtable HS_CURR;
    public static String URLBASE;
    public static int FROM_EURO;
    public static int TO_EURO;
    public static int SENSE_EURO;
    private Label l_copy;
    Choice ch_sense;
    Color c;
    Color c2;
    Image curr1;
    Image curr2;
    Choice chg_policy;
    Choice chg_round;
    Panel p_currency;
    DigitList t_euro_resultat;
    DigitList t_resultat;
    Panel p_chiffres;
    Panel p_flags;
    NewCanvas pressedTouch;
    NewCanvas b_0;
    NewCanvas b_1;
    NewCanvas b_2;
    NewCanvas b_3;
    NewCanvas b_4;
    NewCanvas b_5;
    NewCanvas b_6;
    NewCanvas b_7;
    NewCanvas b_8;
    NewCanvas b_9;
    static NewCanvas b_mul;
    static NewCanvas b_sub;
    static NewCanvas b_add;
    static NewCanvas b_res;
    static NewCanvas b_poi;
    static NewCanvas b_sig;
    static NewCanvas b_div;
    static NewCanvas b_pct;
    static NewCanvas b_mem1;
    static NewCanvas b_mem2;
    static NewCanvas b_mem3;
    static NewCanvas b_mem4;
    static NewCanvas b_mem5;
    NewCurrency c_gbp;
    NewCurrency c_bef;
    NewCurrency c_luf;
    NewCurrency c_dem;
    NewCurrency c_itl;
    NewCurrency c_pta;
    NewCurrency c_frf;
    NewCurrency c_jpy;
    NewCurrency c_nlg;
    NewCurrency c_sek;
    NewCurrency c_usd;
    NewCurrency c_iep;
    NewCurrency c_eur;
    NewCurrency c_gb1;
    NewCurrency c_gb2;
    NewCurrency c_gb3;
    NewCurrency c_gb4;
    NewCurrency c_gb5;
    FlagCanvas f_bel;
    FlagCanvas f_aus;
    FlagCanvas f_dan;
    FlagCanvas f_eur;
    FlagCanvas f_fin;
    FlagCanvas f_gre;
    FlagCanvas f_ger;
    FlagCanvas f_fra;
    FlagCanvas f_ire;
    FlagCanvas f_ita;
    FlagCanvas f_jap;
    FlagCanvas f_lux;
    FlagCanvas f_ned;
    FlagCanvas f_por;
    FlagCanvas f_spa;
    FlagCanvas f_swe;
    FlagCanvas f_usa;
    FlagCanvas f_uk;
    LogicUnit logicUnit;
    int nbOfDigits;
    int digit;
    
    public EuroCalc() {
        this.l_copy = new Label("(c) Briol Patrice 1998 - p.briol@skynet.be");
        this.ch_sense = new Choice();
        this.nbOfDigits = 10;
        this.digit = 1;
    }
    
    private Hashtable getCurrenciesFromApplet() {
        final Hashtable<String, Double> hashtable = new Hashtable<String, Double>();
        final String[] array = { "GBP", "BEF", "LUF", "DEM", "ITL", "ESP", "FRF", "JPY", "NLG", "SEK", "USD", "IEP", "EUR", "ATS", "DKK", "PTE", "FIM", "GRD" };
        for (int i = 0; i < array.length; ++i) {
            final String parameter = this.getParameter(array[i]);
            if (parameter == null) {
                System.out.println("An error occurs when reading currencies infos.");
                System.exit(0);
            }
            try {
                hashtable.put(array[i], new Double(parameter));
            }
            catch (Exception ex) {
                System.out.println("An error occurs when reading currencies infos.");
                System.exit(0);
            }
        }
        return hashtable;
    }
    
    private String getURLBase(final String s) {
        char[] charArray;
        int n;
        for (charArray = s.toCharArray(), n = charArray.length - 1; charArray[n] != '/' && charArray[n] != '\\' && n != 0; --n) {}
        return s.substring(0, n + 1);
    }
    
    public void init() {
        EuroCalc.HS_CURR = this.getCurrenciesFromApplet();
        this.logicUnit = new LogicUnit();
        this.setLayout(null);
        EuroCalc.URLBASE = this.getURLBase(this.getDocumentBase().toString());
        final EuroPanel euroPanel = new EuroPanel(this);
        euroPanel.reshape(10, 10, 120, 120);
        this.add(euroPanel);
        this.setBackground(Color.blue);
        this.setFlagsPanel();
        this.setCurrencyPanel();
        this.p_flags.hide();
        this.p_currency.hide();
        this.displayFlags(true);
        this.setResultatPanel();
        this.setChiffresPanel();
        this.setChoicePanel();
        this.setRoundPanel();
        this.logicUnit.digitList = this.t_resultat;
        this.logicUnit.digitListEuro = this.t_euro_resultat;
        this.requestFocus();
        this.curr1 = this.f_eur.imageFlag;
        this.curr2 = this.curr1;
        this.l_copy.reshape(170, 417, 400, 14);
        this.add(this.l_copy);
        final Label label = new Label("Direction:");
        label.setForeground(Color.yellow);
        label.reshape(110, 150, 80, 14);
        this.add(label);
        this.ch_sense.setBackground(Color.white);
        this.ch_sense.addItem("from Euro");
        this.ch_sense.addItem("to Euro");
        this.ch_sense.reshape(110, 170, 80, 10);
        this.add(this.ch_sense);
        final Checkbox checkbox = new Checkbox("Euro rules");
        checkbox.setForeground(Color.yellow);
        checkbox.reshape(10, 250, 80, 14);
        this.add(checkbox);
        this.setLayout(null);
        this.resize(460, 452);
    }
    
    private void reverseCcy(final int sense_EURO) {
        EuroCalc.SENSE_EURO = sense_EURO;
        final Image curr2 = this.curr2;
        this.curr2 = this.curr1;
        this.curr1 = curr2;
        this.logicUnit.OnChangeDirection(sense_EURO);
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (this.curr1 != null) {
            graphics.drawImage(this.curr1, 140, 80, this);
        }
        if (this.curr2 != null) {
            graphics.drawImage(this.curr2, 140, 15, this);
        }
    }
    
    private void displayFlags(final boolean b) {
        if (b) {
            this.p_flags.show();
            this.p_currency.hide();
        }
        else {
            this.p_currency.show();
            this.p_flags.hide();
        }
        this.requestFocus();
    }
    
    public boolean action(final Event event, final Object o) {
        final Object target = event.target;
        if (target instanceof Checkbox) {
            final Checkbox checkbox = (Checkbox)target;
            if (checkbox.getLabel().equals("Euro rules")) {
                this.chg_policy.select("Lower");
                this.chg_round.select("2");
                if (checkbox.getState()) {
                    this.chg_policy.disable();
                    this.chg_round.disable();
                    this.logicUnit.OnChangeRoundPolicy(2);
                    this.logicUnit.OnApplyEuroRules(true);
                }
                else {
                    this.chg_policy.enable();
                    this.chg_round.enable();
                    this.logicUnit.OnApplyEuroRules(false);
                }
                return true;
            }
            if (checkbox.getLabel().equals("Flags")) {
                this.displayFlags(true);
            }
            else {
                this.displayFlags(false);
            }
        }
        if (target instanceof Choice) {
            final Choice choice = (Choice)target;
            final String selectedItem = choice.getSelectedItem();
            if (Character.isDigit(selectedItem.charAt(0))) {
                this.logicUnit.OnChangeRoundValue(Integer.parseInt(choice.getSelectedItem()));
            }
            else {
                if (selectedItem.equals("None")) {
                    this.logicUnit.OnChangeRoundPolicy(4);
                }
                if (selectedItem.equals("Higher")) {
                    this.logicUnit.OnChangeRoundPolicy(1);
                }
                if (selectedItem.equals("Lower")) {
                    this.logicUnit.OnChangeRoundPolicy(2);
                }
                if (selectedItem.equals("from Euro")) {
                    this.reverseCcy(EuroCalc.FROM_EURO);
                }
                if (selectedItem.equals("to Euro")) {
                    this.reverseCcy(EuroCalc.TO_EURO);
                }
            }
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 49: {
                this.pressedTouch = this.b_1;
                break;
            }
            case 50: {
                this.pressedTouch = this.b_2;
                break;
            }
            case 51: {
                this.pressedTouch = this.b_3;
                break;
            }
            case 52: {
                this.pressedTouch = this.b_4;
                break;
            }
            case 53: {
                this.pressedTouch = this.b_5;
                break;
            }
            case 54: {
                this.pressedTouch = this.b_6;
                break;
            }
            case 55: {
                this.pressedTouch = this.b_7;
                break;
            }
            case 56: {
                this.pressedTouch = this.b_8;
                break;
            }
            case 57: {
                this.pressedTouch = this.b_9;
                break;
            }
            case 48: {
                this.pressedTouch = this.b_0;
                break;
            }
            case 47: {
                this.pressedTouch = EuroCalc.b_div;
                break;
            }
            case 43: {
                this.pressedTouch = EuroCalc.b_add;
                break;
            }
            case 45: {
                this.pressedTouch = EuroCalc.b_sub;
                break;
            }
            case 42: {
                this.pressedTouch = EuroCalc.b_mul;
                break;
            }
            case 46: {
                this.pressedTouch = EuroCalc.b_poi;
                break;
            }
            case 10: {
                this.pressedTouch = EuroCalc.b_res;
                break;
            }
            default: {
                this.pressedTouch = null;
                break;
            }
        }
        if (this.pressedTouch == null) {
            return true;
        }
        final Event event2 = new Event(this.pressedTouch, 501, null);
        this.pressedTouch.mouseDown(event2, 0, 0);
        this.mouseDown(event2, 0, 0);
        return true;
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (this.pressedTouch == null) {
            return true;
        }
        this.pressedTouch.mouseUp(new Event(this.pressedTouch, 501, null), 0, 0);
        return true;
    }
    
    public void setFlagCurr2(final Image image) {
        if (EuroCalc.SENSE_EURO == EuroCalc.FROM_EURO) {
            this.curr2 = image;
        }
        else {
            this.curr1 = image;
        }
        this.repaint();
    }
    
    public LogicUnit getLogicUnit() {
        return this.logicUnit;
    }
    
    private void setChoicePanel() {
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        final Checkbox checkbox = new Checkbox("Flags", checkboxGroup, true);
        final Checkbox checkbox2 = new Checkbox("Codes", checkboxGroup, false);
        checkbox.setForeground(Color.blue);
        checkbox2.setForeground(Color.blue);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(checkbox);
        panel.add(checkbox2);
        panel.setBackground(Color.yellow);
        panel.reshape(40, 330, 150, 20);
        this.add(panel);
    }
    
    private void setRoundPanel() {
        final Panel panel = new Panel();
        final Choice chg_round = new Choice();
        (this.chg_round = chg_round).setBackground(Color.white);
        for (int i = 0; i < 10; ++i) {
            chg_round.addItem(String.valueOf(i));
        }
        chg_round.select("6");
        final Label label = new Label(" Euro decimal");
        final Label label2 = new Label("  round value:");
        final Label label3 = new Label(" policy ");
        label.setForeground(Color.yellow);
        label2.setForeground(Color.yellow);
        label3.setForeground(Color.yellow);
        final Choice chg_policy = new Choice();
        chg_policy.setBackground(Color.white);
        chg_policy.addItem("None");
        chg_policy.addItem("Higher");
        chg_policy.addItem("Lower");
        chg_policy.select("Lower");
        this.chg_policy = chg_policy;
        panel.setLayout(new GridLayout(5, 1));
        panel.setBackground(Color.blue);
        panel.add(label);
        panel.add(label2);
        panel.add(chg_round);
        panel.add(label3);
        panel.add(chg_policy);
        panel.reshape(110, 200, 80, 120);
        this.add(panel);
    }
    
    private void setCurrencyPanel() {
        (this.p_currency = new Panel()).setLayout(new GridLayout(2, 10));
        this.c_gbp = new NewCurrency("GBP", this.p_currency, this);
        this.c_bef = new NewCurrency("BEF", this.p_currency, this);
        this.c_luf = new NewCurrency("LUF", this.p_currency, this);
        this.c_dem = new NewCurrency("DEM", this.p_currency, this);
        this.c_itl = new NewCurrency("ITL", this.p_currency, this);
        this.c_pta = new NewCurrency("ESP", this.p_currency, this);
        this.c_frf = new NewCurrency("FRF", this.p_currency, this);
        this.c_jpy = new NewCurrency("JPY", this.p_currency, this);
        this.c_nlg = new NewCurrency("NLG", this.p_currency, this);
        this.c_sek = new NewCurrency("SEK", this.p_currency, this);
        this.c_usd = new NewCurrency("USD", this.p_currency, this);
        this.c_iep = new NewCurrency("IEP", this.p_currency, this);
        this.c_eur = new NewCurrency("EUR", this.p_currency, this);
        this.c_gb1 = new NewCurrency("ATS", this.p_currency, this);
        this.c_gb2 = new NewCurrency("DKK", this.p_currency, this);
        this.c_gb3 = new NewCurrency("PTE", this.p_currency, this);
        this.c_gb4 = new NewCurrency("FIM", this.p_currency, this);
        this.c_gb5 = new NewCurrency("GRD", this.p_currency, this);
        this.p_currency.reshape(40, 360, 360, 70);
        this.add(this.p_currency);
        this.p_currency.add(this.c_gbp);
        this.p_currency.add(this.c_bef);
        this.p_currency.add(this.c_luf);
        this.p_currency.add(this.c_dem);
        this.p_currency.add(this.c_itl);
        this.p_currency.add(this.c_pta);
        this.p_currency.add(this.c_frf);
        this.p_currency.add(this.c_jpy);
        this.p_currency.add(this.c_nlg);
        this.p_currency.add(this.c_sek);
        this.p_currency.add(this.c_usd);
        this.p_currency.add(this.c_iep);
        this.p_currency.add(this.c_eur);
        this.p_currency.add(this.c_gb1);
        this.p_currency.add(this.c_gb2);
        this.p_currency.add(this.c_gb3);
        this.p_currency.add(this.c_gb4);
        this.p_currency.add(this.c_gb5);
    }
    
    private void setChiffresPanel() {
        (this.p_chiffres = new Panel()).setLayout(new GridLayout(5, 5));
        this.b_0 = new NewCanvas("0", this.p_chiffres, this);
        this.b_1 = new NewCanvas("1", this.p_chiffres, this);
        this.b_2 = new NewCanvas("2", this.p_chiffres, this);
        this.b_3 = new NewCanvas("3", this.p_chiffres, this);
        this.b_4 = new NewCanvas("4", this.p_chiffres, this);
        this.b_5 = new NewCanvas("5", this.p_chiffres, this);
        this.b_6 = new NewCanvas("6", this.p_chiffres, this);
        this.b_7 = new NewCanvas("7", this.p_chiffres, this);
        this.b_8 = new NewCanvas("8", this.p_chiffres, this);
        this.b_9 = new NewCanvas("9", this.p_chiffres, this);
        EuroCalc.b_div = new NewCanvas("/", this.p_chiffres, this);
        EuroCalc.b_mul = new NewCanvas("*", this.p_chiffres, this);
        EuroCalc.b_sub = new NewCanvas("-", this.p_chiffres, this);
        EuroCalc.b_add = new NewCanvas("+", this.p_chiffres, this);
        EuroCalc.b_res = new NewCanvas("=", this.p_chiffres, this);
        EuroCalc.b_poi = new NewCanvas(".", this.p_chiffres, this);
        EuroCalc.b_sig = new NewCanvas("+/-", this.p_chiffres, this);
        EuroCalc.b_mem1 = new NewCanvas("M+", this.p_chiffres, this);
        EuroCalc.b_mem2 = new NewCanvas("M", this.p_chiffres, this);
        EuroCalc.b_mem3 = new NewCanvas("MC", this.p_chiffres, this);
        EuroCalc.b_mem4 = new NewCanvas("CE", this.p_chiffres, this);
        EuroCalc.b_mem5 = new NewCanvas("C", this.p_chiffres, this);
        EuroCalc.b_pct = new NewCanvas("%", this.p_chiffres, this);
        this.p_chiffres.reshape(200, 120, 240, 200);
        this.add(this.p_chiffres);
        this.p_chiffres.add(this.b_7);
        this.p_chiffres.add(this.b_8);
        this.p_chiffres.add(this.b_9);
        this.p_chiffres.add(EuroCalc.b_div);
        this.p_chiffres.add(EuroCalc.b_mem1);
        this.p_chiffres.add(this.b_4);
        this.p_chiffres.add(this.b_5);
        this.p_chiffres.add(this.b_6);
        this.p_chiffres.add(EuroCalc.b_mul);
        this.p_chiffres.add(EuroCalc.b_mem2);
        this.p_chiffres.add(this.b_1);
        this.p_chiffres.add(this.b_2);
        this.p_chiffres.add(this.b_3);
        this.p_chiffres.add(EuroCalc.b_sub);
        this.p_chiffres.add(EuroCalc.b_mem3);
        this.p_chiffres.add(this.b_0);
        this.p_chiffres.add(EuroCalc.b_poi);
        this.p_chiffres.add(EuroCalc.b_sig);
        this.p_chiffres.add(EuroCalc.b_add);
        this.p_chiffres.add(EuroCalc.b_mem4);
        this.p_chiffres.add(EuroCalc.b_res);
        this.p_chiffres.add(EuroCalc.b_pct);
        this.p_chiffres.add(EuroCalc.b_mem5);
    }
    
    private void setResultatPanel() {
        this.t_euro_resultat = new DigitList(this, this.nbOfDigits);
        this.t_resultat = new DigitList(this, this.nbOfDigits);
        this.t_euro_resultat.reshape(200, 10, this.t_euro_resultat.size.width, this.t_euro_resultat.size.height);
        this.t_resultat.reshape(200, 70, this.t_resultat.size.width, this.t_resultat.size.height);
        this.add(this.t_euro_resultat);
        this.add(this.t_resultat);
    }
    
    private void setFlagsPanel() {
        (this.p_flags = new Panel()).setLayout(new GridLayout(2, 10));
        this.f_bel = new FlagCanvas("bel", this.p_flags, this, "BEF");
        this.f_aus = new FlagCanvas("aus", this.p_flags, this, "ATS");
        this.f_dan = new FlagCanvas("dan", this.p_flags, this, "DKK");
        this.f_eur = new FlagCanvas("eur", this.p_flags, this, "EUR");
        this.f_fin = new FlagCanvas("fin", this.p_flags, this, "FIM");
        this.f_gre = new FlagCanvas("gre", this.p_flags, this, "GRD");
        this.f_ger = new FlagCanvas("ger", this.p_flags, this, "DEM");
        this.f_fra = new FlagCanvas("fra", this.p_flags, this, "FRF");
        this.f_ire = new FlagCanvas("ire", this.p_flags, this, "IEP");
        this.f_ita = new FlagCanvas("ita", this.p_flags, this, "ITL");
        this.f_jap = new FlagCanvas("jap", this.p_flags, this, "JPY");
        this.f_lux = new FlagCanvas("lux", this.p_flags, this, "LUF");
        this.f_ned = new FlagCanvas("ned", this.p_flags, this, "NLG");
        this.f_por = new FlagCanvas("por", this.p_flags, this, "PTE");
        this.f_spa = new FlagCanvas("spa", this.p_flags, this, "ESP");
        this.f_swe = new FlagCanvas("swe", this.p_flags, this, "SEK");
        this.f_usa = new FlagCanvas("usa", this.p_flags, this, "USD");
        this.f_uk = new FlagCanvas("uk", this.p_flags, this, "GBP");
        this.p_flags.add(this.f_bel);
        this.p_flags.add(this.f_aus);
        this.p_flags.add(this.f_dan);
        this.p_flags.add(this.f_eur);
        this.p_flags.add(this.f_fin);
        this.p_flags.add(this.f_gre);
        this.p_flags.add(this.f_ger);
        this.p_flags.add(this.f_fra);
        this.p_flags.add(this.f_ire);
        this.p_flags.add(this.f_jap);
        this.p_flags.add(this.f_lux);
        this.p_flags.add(this.f_por);
        this.p_flags.add(this.f_uk);
        this.p_flags.add(this.f_ita);
        this.p_flags.add(this.f_spa);
        this.p_flags.add(this.f_swe);
        this.p_flags.add(this.f_usa);
        this.p_flags.add(this.f_ned);
        this.p_flags.reshape(40, 360, 360, 60);
        this.add(this.p_flags);
    }
    
    public String getAppletInfo() {
        return "Euro Converter Lite V1.0.1 - Briol Patrice 1998 - p.briol@skynet.be";
    }
    
    static {
        EuroCalc.CURR = "EUR";
        EuroCalc.TO_EURO = 1;
    }
}
