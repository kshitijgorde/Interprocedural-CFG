// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.event.MouseListener;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Component;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.TextField;

public class bq extends bF
{
    private TextField a;
    private Choice u;
    private Checkbox y;
    private Checkbox z;
    private Choice v;
    
    public cF a() {
        return new ba(-999, "");
    }
    
    public void a(final cF cf) {
        final aC ac = (aC)cf;
        if (ac.f().length() == 3) {
            for (int i = 0; i < this.u.getItemCount(); ++i) {
                if (this.u.getItem(i).toLowerCase().startsWith(ac.f().toLowerCase())) {
                    this.u.select(i);
                    this.u.setEnabled(true);
                    this.z.setState(true);
                    this.a.setText("");
                    this.a.setEnabled(false);
                    this.y.setState(false);
                }
            }
        }
        else {
            this.a.setText(ac.f());
            this.a.setEnabled(true);
            this.y.setState(true);
            this.u.select(0);
            this.u.setEnabled(false);
            this.z.setState(false);
        }
        this.v.select(ac.aq);
    }
    
    public boolean a(final cF cf) {
        final aC ac = (aC)cf;
        ac.aq = this.v.getSelectedIndex();
        String s;
        if (this.y.getState()) {
            s = this.a.getText();
        }
        else {
            s = this.u.getSelectedItem().substring(0, 3);
        }
        if (s.length() == 0) {
            this.a.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You may enter a full or partial host name or IP address (e.g., \"123.234.56.78\", \"123.234.56.\", or \".host.com\").  Any user whose IP or host name contains this string will be denied access."), super.i).setVisible(true);
            return false;
        }
        ac.d(s);
        return true;
    }
    
    public void c(final cF cf) {
        if (cf == null) {
            super.c.d();
            super.r.d();
        }
        else {
            if (cf.d(63)) {
                super.r.a(ao.e("Restore"));
            }
            else {
                super.r.a(ao.e("Remove"));
            }
            if (!"Admin".equals(super.i.n) && super.f.b((aU)cf)) {
                super.c.c();
            }
            else {
                super.c.d();
            }
            if (cf.a && super.f.b((aU)cf) && !cf.d(62)) {
                super.r.c();
            }
            else {
                super.r.d();
            }
        }
    }
    
    public void a(final bk bk) {
        bk.a(this.y, this.a, 0, true);
        if (super.i.d(32)) {
            bk.a(this.z, this.u, 0, true);
        }
        bk.a(ao.e("Band Time:"), this.v);
        bk.a(new c(ao.e("You must enter a full or partial host name or IP address (e.g., \"123.234.56.78\", \"123.234.56.\", or \".host.com\").  Any user whose IP or host name contains this string will be denied access.")), 2, 1.0f, 0.0f);
    }
    
    public void c() {
        if (super.e) {
            final cD cd = new cD(67339, this.j());
            cd.j = -1;
            cd.o = -1;
            int i = 0;
            int n = 0;
            while (i < this.d()) {
                final aC ac = (aC)this.a(i);
                if (ac.aw) {
                    cd.a(n, ac.d());
                    cd.a(n, 0, ac.h());
                    cd.a(n, 1, ac.aq);
                    if (!ac.d(63) || "Admin".equals(super.i.n)) {
                        cd.a(n, 0, ac.f());
                    }
                    ++n;
                }
                ++i;
            }
            super.i.o(cd);
            super.e = false;
        }
    }
    
    public void d() {
        super.d();
        super.i.n.a(false);
        try {
            for (int i = 0; i < super.i.n.b(); ++i) {
                this.b((cF)new ba((aC)super.i.n.a(i)));
            }
        }
        finally {
            super.i.n.a();
        }
    }
    
    public bq(final u u) {
        super(u, ao.e("Ban Users"), ao.e("IP/Host"));
        (this.a = new TextField(30)).setBackground(Color.white);
        this.a.setForeground(Color.black);
        (this.u = new Choice()).setBackground(Color.white);
        this.u.setForeground(Color.black);
        this.u.addItem("USA - " + ao.e("UNITED STATES"));
        this.u.addItem("CAN - " + ao.e("CANADA"));
        this.u.addItem("NLD - " + ao.e("NETHERLANDS"));
        this.u.addItem("PRI - " + ao.e("PUERTO RICO"));
        this.u.addItem("CHL - " + ao.e("CHILE"));
        this.u.addItem("BHS - " + ao.e("BAHAMAS"));
        this.u.addItem("ARG - " + ao.e("ARGENTINA"));
        this.u.addItem("GBR - " + ao.e("UNITED KINGDOM"));
        this.u.addItem("ZAF - " + ao.e("SOUTH AFRICA"));
        this.u.addItem("EGY - " + ao.e("EGYPT"));
        this.u.addItem("RWA - " + ao.e("RWANDA"));
        this.u.addItem("DZA - " + ao.e("ALGERIA"));
        this.u.addItem("GHA - " + ao.e("GHANA"));
        this.u.addItem("CIV - " + ao.e("COTE D'IVOIRE"));
        this.u.addItem("SDN - " + ao.e("SUDAN"));
        this.u.addItem("SWZ - " + ao.e("SWAZILAND"));
        this.u.addItem("CMR - " + ao.e("CAMEROON"));
        this.u.addItem("MDG - " + ao.e("MADAGASCAR"));
        this.u.addItem("TZA - " + ao.e("TANZANIA UNITED REPUBLIC OF"));
        this.u.addItem("KEN - " + ao.e("KENYA"));
        this.u.addItem("NGA - " + ao.e("NIGERIA"));
        this.u.addItem("AGO - " + ao.e("ANGOLA"));
        this.u.addItem("NAM - " + ao.e("NAMIBIA"));
        this.u.addItem("MAR - " + ao.e("MOROCCO"));
        this.u.addItem("SLE - " + ao.e("SIERRA LEONE"));
        this.u.addItem("GAB - " + ao.e("GABON"));
        this.u.addItem("MUS - " + ao.e("MAURITIUS"));
        this.u.addItem("TGO - " + ao.e("TOGO"));
        this.u.addItem("LBY - " + ao.e("LIBYAN ARAB JAMAHIRIYA"));
        this.u.addItem("SEN - " + ao.e("SENEGAL"));
        this.u.addItem("UGA - " + ao.e("UGANDA"));
        this.u.addItem("ZWE - " + ao.e("ZIMBABWE"));
        this.u.addItem("MOZ - " + ao.e("MOZAMBIQUE"));
        this.u.addItem("SYC - " + ao.e("SEYCHELLES"));
        this.u.addItem("MWI - " + ao.e("MALAWI"));
        this.u.addItem("MLI - " + ao.e("MALI"));
        this.u.addItem("CPV - " + ao.e("CAPE VERDE"));
        this.u.addItem("ZMB - " + ao.e("ZAMBIA"));
        this.u.addItem("BWA - " + ao.e("BOTSWANA"));
        this.u.addItem("LBR - " + ao.e("LIBERIA"));
        this.u.addItem("COD - " + ao.e("CONGO"));
        this.u.addItem("CAF - " + ao.e("CENTRAL AFRICAN REPUBLIC"));
        this.u.addItem("COG - " + ao.e("CONGO"));
        this.u.addItem("BEN - " + ao.e("BENIN"));
        this.u.addItem("TUN - " + ao.e("TUNISIA"));
        this.u.addItem("JPN - " + ao.e("JAPAN"));
        this.u.addItem("DEU - " + ao.e("GERMANY"));
        this.u.addItem("FRA - " + ao.e("FRANCE"));
        this.u.addItem("IND - " + ao.e("INDIA"));
        this.u.addItem("AUS - " + ao.e("AUSTRALIA"));
        this.u.addItem("THA - " + ao.e("THAILAND"));
        this.u.addItem("CHN - " + ao.e("CHINA"));
        this.u.addItem("MYS - " + ao.e("MALAYSIA"));
        this.u.addItem("PAK - " + ao.e("PAKISTAN"));
        this.u.addItem("NZL - " + ao.e("NEW ZEALAND"));
        this.u.addItem("KOR - " + ao.e("KOREA REPUBLIC OF"));
        this.u.addItem("HKG - " + ao.e("HONG KONG"));
        this.u.addItem("SGP - " + ao.e("SINGAPORE"));
        this.u.addItem("BGD - " + ao.e("BANGLADESH"));
        this.u.addItem("IDN - " + ao.e("INDONESIA"));
        this.u.addItem("PHL - " + ao.e("PHILIPPINES"));
        this.u.addItem("TWN - " + ao.e("TAIWAN PROVINCE OF CHINA"));
        this.u.addItem("AFG - " + ao.e("AFGHANISTAN"));
        this.u.addItem("VNM - " + ao.e("VIET NAM"));
        this.u.addItem("NCL - " + ao.e("NEW CALEDONIA"));
        this.u.addItem("BRN - " + ao.e("BRUNEI DARUSSALAM"));
        this.u.addItem("AFR - " + ao.e("NON-SPEC ASIA PAS LOCATION"));
        this.u.addItem("ISR - " + ao.e("ISRAEL"));
        this.u.addItem("GRC - " + ao.e("GREECE"));
        this.u.addItem("CHE - " + ao.e("SWITZERLAND"));
        this.u.addItem("SAU - " + ao.e("SAUDI ARABIA"));
        this.u.addItem("SWE - " + ao.e("SWEDEN"));
        this.u.addItem("POL - " + ao.e("POLAND"));
        this.u.addItem("ITA - " + ao.e("ITALY"));
        this.u.addItem("CZE - " + ao.e("CZECH REPUBLIC"));
        this.u.addItem("BEL - " + ao.e("BELGIUM"));
        this.u.addItem("RUS - " + ao.e("RUSSIAN FEDERATION"));
        this.u.addItem("IRL - " + ao.e("IRELAND"));
        this.u.addItem("DNK - " + ao.e("DENMARK"));
        this.u.addItem("CYP - " + ao.e("CYPRUS"));
        this.u.addItem("AUT - " + ao.e("AUSTRIA"));
        this.u.addItem("ESP - " + ao.e("SPAIN"));
        this.u.addItem("UKR - " + ao.e("UKRAINE"));
        this.u.addItem("NOR - " + ao.e("NORWAY"));
        this.u.addItem("EUR - " + ao.e("EUROPEAN UNION"));
        this.u.addItem("PRT - " + ao.e("PORTUGAL"));
        this.u.addItem("TUR - " + ao.e("TURKEY"));
        this.u.addItem("BGR - " + ao.e("BULGARIA"));
        this.u.addItem("FIN - " + ao.e("FINLAND"));
        this.u.addItem("IRN - " + ao.e("IRAN (ISLAMIC REPUBLIC OF)"));
        this.u.addItem("OMN - " + ao.e("OMAN"));
        this.u.addItem("LVA - " + ao.e("LATVIA"));
        this.u.addItem("EST - " + ao.e("ESTONIA"));
        this.u.addItem("SVK - " + ao.e("SLOVAKIA (Slovak Republic)"));
        this.u.addItem("JOR - " + ao.e("JORDAN"));
        this.u.addItem("HUN - " + ao.e("HUNGARY"));
        this.u.addItem("KWT - " + ao.e("KUWAIT"));
        this.u.addItem("LTU - " + ao.e("LITHUANIA"));
        this.u.addItem("KAZ - " + ao.e("KAZAKHSTAN"));
        this.u.addItem("LBN - " + ao.e("LEBANON"));
        this.u.addItem("ARM - " + ao.e("ARMENIA"));
        this.u.addItem("CSZ - " + ao.e("Serbia and Montenegro"));
        this.u.addItem("ISL - " + ao.e("ICELAND"));
        this.u.addItem("MKD - " + ao.e("MACEDONIA"));
        this.u.addItem("GEO - " + ao.e("GEORGIA"));
        this.u.addItem("MLT - " + ao.e("MALTA"));
        this.u.addItem("AZE - " + ao.e("AZERBAIJAN"));
        this.u.addItem("ROM - " + ao.e("ROMANIA"));
        this.u.addItem("MCO - " + ao.e("MONACO"));
        this.u.addItem("TTO - " + ao.e("TRINIDAD AND TOBAGO"));
        this.u.addItem("DOM - " + ao.e("DOMINICAN REPUBLIC"));
        this.u.addItem("LSO - " + ao.e("LESOTHO"));
        this.u.addItem("BRB - " + ao.e("BARBADOS"));
        this.u.addItem("JAM - " + ao.e("JAMAICA"));
        this.u.addItem("TCA - " + ao.e("TURKS AND CAICOS ISLANDS"));
        this.u.addItem("BMU - " + ao.e("BERMUDA"));
        this.u.addItem("COL - " + ao.e("COLOMBIA"));
        this.u.addItem("VIR - " + ao.e("VIRGIN ISLANDS (U.S.)"));
        this.u.addItem("ATG - " + ao.e("ANTIGUA AND BARBUDA"));
        this.u.addItem("CYM - " + ao.e("CAYMAN ISLANDS"));
        this.u.addItem("SVN - " + ao.e("SLOVENIA"));
        this.u.addItem("SYR - " + ao.e("SYRIAN ARAB REPUBLIC"));
        this.u.addItem("BHR - " + ao.e("BAHRAIN"));
        this.u.addItem("JEY - " + ao.e("JERSEY"));
        this.u.addItem("BLR - " + ao.e("BELARUS"));
        this.u.addItem("BIH - " + ao.e("BOSNIA AND HERZEGOWINA"));
        this.u.addItem("SRB - " + ao.e("Serbia"));
        this.u.addItem("MDA - " + ao.e("MOLDOVA REPUBLIC OF"));
        this.u.addItem("TJK - " + ao.e("TAJIKISTAN"));
        this.u.addItem("KGZ - " + ao.e("KYRGYZSTAN"));
        this.u.addItem("HRV - " + ao.e("CROATIA (local name: Hrvatska)"));
        this.u.addItem("UZB - " + ao.e("UZBEKISTAN"));
        this.u.addItem("ALB - " + ao.e("ALBANIA"));
        this.u.addItem("SMR - " + ao.e("SAN MARINO"));
        this.u.addItem("IMN - " + ao.e("ISLE OF MAN"));
        this.u.addItem("LUX - " + ao.e("LUXEMBOURG"));
        this.u.addItem("GGY - " + ao.e("GUERNSEY"));
        this.u.addItem("QAT - " + ao.e("QATAR"));
        this.u.addItem("IRQ - " + ao.e("IRAQ"));
        this.u.addItem("MNE - " + ao.e("Montenegro"));
        this.u.addItem("LIE - " + ao.e("LIECHTENSTEIN"));
        this.u.addItem("FRO - " + ao.e("FAROE ISLANDS"));
        this.u.addItem("ARE - " + ao.e("UNITED ARAB EMIRATES"));
        this.u.addItem("PSE - " + ao.e("PALESTINIAN TERRITORY OCCUPIED"));
        this.u.addItem("YEM - " + ao.e("YEMEN"));
        this.u.addItem("MRT - " + ao.e("MAURITANIA"));
        this.u.addItem("AND - " + ao.e("ANDORRA"));
        this.u.addItem("GIB - " + ao.e("GIBRALTAR"));
        this.u.addItem("GRL - " + ao.e("GREENLAND"));
        this.u.addItem("KHM - " + ao.e("CAMBODIA"));
        this.u.addItem("LKA - " + ao.e("SRI LANKA"));
        this.u.addItem("NPL - " + ao.e("NEPAL"));
        this.u.addItem("GUM - " + ao.e("GUAM"));
        this.u.addItem("MAC - " + ao.e("MACAU"));
        this.u.addItem("WLF - " + ao.e("WALLIS AND FUTUNA ISLANDS"));
        this.u.addItem("PNG - " + ao.e("PAPUA NEW GUINEA"));
        this.u.addItem("MHL - " + ao.e("MARSHALL ISLANDS"));
        this.u.addItem("BTN - " + ao.e("BHUTAN"));
        this.u.addItem("VUT - " + ao.e("VANUATU"));
        this.u.addItem("MNG - " + ao.e("MONGOLIA"));
        this.u.addItem("PYF - " + ao.e("FRENCH POLYNESIA"));
        this.u.addItem("MDV - " + ao.e("MALDIVES"));
        this.u.addItem("WSM - " + ao.e("SAMOA"));
        this.u.addItem("FJI - " + ao.e("FIJI"));
        this.u.addItem("FSM - " + ao.e("MICRONESIA FEDERATED STATES OF"));
        this.u.addItem("VEN - " + ao.e("VENEZUELA"));
        this.u.addItem("MEX - " + ao.e("MEXICO"));
        this.u.addItem("BRA - " + ao.e("BRAZIL"));
        this.u.addItem("ECU - " + ao.e("ECUADOR"));
        this.u.addItem("PER - " + ao.e("PERU"));
        this.u.addItem("CRI - " + ao.e("COSTA RICA"));
        this.u.addItem("URY - " + ao.e("URUGUAY"));
        this.u.addItem("NIC - " + ao.e("NICARAGUA"));
        this.u.addItem("BOL - " + ao.e("BOLIVIA"));
        this.u.addItem("PAN - " + ao.e("PANAMA"));
        this.u.addItem("GTM - " + ao.e("GUATEMALA"));
        this.u.addItem("SLV - " + ao.e("EL SALVADOR"));
        this.u.addItem("CUB - " + ao.e("CUBA"));
        this.u.addItem("PRY - " + ao.e("PARAGUAY"));
        this.u.addItem("ANT - " + ao.e("NETHERLANDS ANTILLES"));
        this.u.addItem("HND - " + ao.e("HONDURAS"));
        this.u.addItem("GUY - " + ao.e("GUYANA"));
        this.u.addItem("BLZ - " + ao.e("BELIZE"));
        this.u.addItem("BFA - " + ao.e("BURKINA FASO"));
        this.u.addItem("BDI - " + ao.e("BURUNDI"));
        this.u.addItem("GRD - " + ao.e("GRENADA"));
        this.u.addItem("GNB - " + ao.e("GUINEA-BISSAU"));
        this.u.addItem("GMB - " + ao.e("GAMBIA"));
        this.u.addItem("ERI - " + ao.e("ERITREA"));
        this.u.addItem("DJI - " + ao.e("DJIBOUTI"));
        this.u.addItem("MSR - " + ao.e("MONTSERRAT"));
        this.u.addItem("SUR - " + ao.e("SURINAME"));
        this.u.addItem("HTI - " + ao.e("HAITI"));
        this.u.addItem("ABW - " + ao.e("ARUBA"));
        this.u.addItem("GUF - " + ao.e("FRENCH GUIANA"));
        this.u.addItem("SLB - " + ao.e("SOLOMON ISLANDS"));
        this.u.addItem("TUV - " + ao.e("TUVALU"));
        this.u.addItem("KIR - " + ao.e("KIRIBATI"));
        this.u.addItem("TON - " + ao.e("TONGA"));
        this.u.addItem("IOT - " + ao.e("BRITISH INDIAN OCEAN TERRITORY"));
        this.u.addItem("LAO - " + ao.e("LAO PEOPLE'S DEMOCRATIC REPUBLIC"));
        this.u.addItem("NIU - " + ao.e("NIUE"));
        this.u.addItem("COK - " + ao.e("COOK ISLANDS"));
        this.u.addItem("ASM - " + ao.e("AMERICAN SAMOA"));
        this.u.addItem("MNP - " + ao.e("NORTHERN MARIANA ISLANDS"));
        this.u.addItem("PLW - " + ao.e("PALAU"));
        this.u.addItem("NFK - " + ao.e("NORFOLK ISLAND"));
        this.u.addItem("MMR - " + ao.e("MYANMAR"));
        this.u.addItem("NRU - " + ao.e("NAURU"));
        this.u.addItem("AIA - " + ao.e("ANGUILLA"));
        this.u.addItem("KNA - " + ao.e("SAINT KITTS AND NEVIS"));
        this.u.addItem("LCA - " + ao.e("SAINT LUCIA"));
        this.u.addItem("VGB - " + ao.e("VIRGIN ISLANDS (BRITISH)"));
        this.u.addItem("GLP - " + ao.e("GUADELOUPE"));
        this.u.addItem("VAT - " + ao.e("HOLY SEE (VATICAN CITY STATE)"));
        this.u.addItem("ETH - " + ao.e("ETHIOPIA"));
        this.u.addItem("ALA - " + ao.e("\u00efLAND ISLANDS"));
        this.u.addItem("TKM - " + ao.e("TURKMENISTAN"));
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.y = new Checkbox(ao.e("IP/Host:"), checkboxGroup, true);
        this.z = new Checkbox(ao.e("Country:"), checkboxGroup, false);
        this.y.addMouseListener(new co(this));
        this.z.addMouseListener(new cn(this));
        (this.v = new Choice()).setBackground(Color.white);
        this.v.setForeground(Color.black);
        this.v.addItem(ao.e("Forever"));
        this.v.addItem(ao.e("15 minutes"));
        this.v.addItem(ao.e("30 minutes"));
        this.v.addItem(ao.e("1 hour"));
        this.v.addItem(ao.e("3 hours"));
        this.v.addItem(ao.e("6 hours"));
        this.v.addItem(ao.e("12 hours"));
        this.v.addItem(ao.e("24 hours"));
        if (!u.d(16)) {
            super.c.setVisible(false);
            super.d.setVisible(false);
        }
        else {
            super.x.setVisible(true);
        }
    }
    
    static TextField a(final bq bq) {
        return bq.a;
    }
    
    static Choice a(final bq bq) {
        return bq.u;
    }
}
