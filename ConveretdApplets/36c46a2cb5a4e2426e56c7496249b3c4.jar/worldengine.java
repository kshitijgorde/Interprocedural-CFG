import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.Image;
import java.awt.TextField;
import java.net.URL;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class worldengine extends Applet
{
    Button b;
    Choice c;
    CheckboxGroup cbg;
    Checkbox cb0;
    Checkbox cb1;
    Checkbox cb2;
    Checkbox cb3;
    Checkbox cb4;
    Checkbox cb5;
    Checkbox cb6;
    Checkbox cb7;
    Checkbox cb8;
    URL theURL;
    TextField tf;
    Image img;
    String pic;
    String fram;
    Panel p;
    
    public void init() {
        this.setLayout(new GridBagLayout());
        this.setFont(new Font("Helvetica", 0, 10));
        this.cbg = new CheckboxGroup();
        this.c = new Choice();
        this.pic = this.getParameter("pic");
        if (this.pic == null) {
            this.pic = "";
        }
        this.tf = new TextField("Criteria");
        this.img = this.getImage(this.getCodeBase(), this.pic);
        this.fram = "";
        this.fram = this.getParameter("target");
        if (this.fram == null) {
            this.fram = "_top";
        }
        this.setBackground(Color.lightGray);
        this.cb0 = new Checkbox("WWW A-M", this.cbg, false);
        this.cb1 = new Checkbox("News", this.cbg, false);
        this.cb2 = new Checkbox("Software", this.cbg, false);
        this.cb3 = new Checkbox("People", this.cbg, false);
        this.cb4 = new Checkbox("Mp3", this.cbg, false);
        this.cb5 = new Checkbox("Money", this.cbg, false);
        this.cb6 = new Checkbox("Uni's", this.cbg, false);
        this.cb7 = new Checkbox("Medical", this.cbg, false);
        this.cb8 = new Checkbox("WWW N-Z", this.cbg, false);
        (this.p = new Panel()).setLayout(new GridLayout(3, 3));
        this.p.add(this.cb0);
        this.p.add(this.cb6);
        this.p.add(this.cb3);
        this.p.add(this.cb8);
        this.p.add(this.cb4);
        this.p.add(this.cb5);
        this.p.add(this.cb2);
        this.p.add(this.cb1);
        this.p.add(this.cb7);
        this.add(this.p);
        this.validate();
        this.setLayout(new GridBagLayout());
        this.add(this.c);
        this.add(this.tf);
        this.add(this.b);
    }
    
    public void paint(final Graphics graphics) {
        this.c.reshape(15, 20, 220, 24);
        this.tf.reshape(15, 60, 180, 28);
        this.b.reshape(200, 60, 35, 20);
        this.p.reshape(265, 20, 239, 67);
        graphics.drawImage(this.img, 0, 0, this);
        graphics.setColor(Color.red);
        graphics.draw3DRect(264, 19, 240, 68, true);
    }
    
    public String getAppletInfo() {
        return "Java Search Engine 3.0 (jul 30 1998). Hans Wolters. This applet is no freeware. For more info you can contact me by enail. h.wolters@gelrevision.nl";
    }
    
    public void start() {
        this.cb0.setState(true);
        this.fillwww();
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 10) {
            this.tf.setText(this.tf.getText().replace(' ', '+'));
            this.gotoSearchUrl(this.c.getSelectedItem());
        }
        else if (n == 1) {
            this.tf.setText("");
        }
        return false;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Checkbox) {
            this.remove(this.c);
            this.add(this.c = new Choice());
            final boolean state = this.cb0.getState();
            final boolean state2 = this.cb1.getState();
            final boolean state3 = this.cb2.getState();
            final boolean state4 = this.cb3.getState();
            final boolean state5 = this.cb4.getState();
            final boolean state6 = this.cb5.getState();
            final boolean state7 = this.cb6.getState();
            final boolean state8 = this.cb7.getState();
            final boolean state9 = this.cb8.getState();
            if (state) {
                this.fillwww();
            }
            else if (state2) {
                this.fillnews();
            }
            else if (state3) {
                this.fillsoftware();
            }
            else if (state4) {
                this.fillpeople();
            }
            else if (state5) {
                this.fillhobbies();
            }
            else if (state6) {
                this.fillmoney();
            }
            else if (state7) {
                this.filluni();
            }
            else if (state8) {
                this.fillmedic();
            }
            else if (state9) {
                this.fillasia();
            }
        }
        else if (event.target instanceof Button) {
            this.tf.setText(this.tf.getText().replace(' ', '+'));
            this.gotoSearchUrl(this.c.getSelectedItem());
        }
        return true;
    }
    
    void filluni() {
        this.c.addItem("Alaska Pacific University");
        this.c.addItem("Australian Nat. University");
        this.c.addItem("Brigham Young University");
        this.c.addItem("Cal. State Uni, Long Beach");
        this.c.addItem("City University of Hong Kong");
        this.c.addItem("Delft University of Technology");
        this.c.addItem("Erasmus University Holland");
        this.c.addItem("Gonzaga University");
        this.c.addItem("HKU of Sience & Technology");
        this.c.addItem("Mit Edu");
        this.c.addItem("Stanford University");
        this.c.addItem("University of Amsterdam");
        this.c.addItem("University of Hawaii");
        this.c.addItem("University of New South Wales");
        this.c.addItem("University of Oregon");
        this.c.addItem("University of Pennsylvania");
        this.c.addItem("Utrecht University Holland");
        this.c.addItem("University of Virginia");
        this.c.addItem("University of Zurich");
        this.c.addItem("Yale University Library Web");
    }
    
    void fillmedic() {
        this.c.addItem("Centers for Dis. Control & Prev.");
        this.c.addItem("Children with Diabetes");
        this.c.addItem("Dictionary of Cell Biology");
        this.c.addItem("Food and Drug Administration");
        this.c.addItem("GenBank");
        this.c.addItem("HealthAtoZ");
        this.c.addItem("Hiv InSite");
        this.c.addItem("PubMed");
        this.c.addItem("Rutgers Netherlands");
        this.c.addItem("Virtual Hospital");
    }
    
    void fillwww() {
        this.c.addItem("AOL");
        this.c.addItem("AlCanSeek");
        this.c.addItem("AliWeb");
        this.c.addItem("AltaVista");
        this.c.addItem("Apollo7 Germany");
        this.c.addItem("AskJeeves");
        this.c.addItem("Cade Brazil");
        this.c.addItem("Canada");
        this.c.addItem("Cari Malaysia");
        this.c.addItem("Carrefour France");
        this.c.addItem("Claymont");
        this.c.addItem("Cyber411");
        this.c.addItem("Dewa");
        this.c.addItem("Dir. Nac. Argentino");
        this.c.addItem("Dogpile");
        this.c.addItem("Excite");
        this.c.addItem("Euroseek");
        this.c.addItem("Explora Mexico");
        this.c.addItem("Francit\u00e9");
        this.c.addItem("Goo Japan");
        this.c.addItem("Goto");
        this.c.addItem("Highway61");
        this.c.addItem("Hong Kong Search");
        this.c.addItem("Hotbot");
        this.c.addItem("Identify");
        this.c.addItem("Ilse");
        this.c.addItem("Inference Find");
        this.c.addItem("Infomak");
        this.c.addItem("Infoseek");
        this.c.addItem("Jubii Danmark");
        this.c.addItem("Kaixo! internet Spain");
        this.c.addItem("KHOJ");
        this.c.addItem("Kolibri Germany");
        this.c.addItem("Kvasir Sweden");
        this.c.addItem("La Br\u00fajula Chile");
        this.c.addItem("La Toile du Qu\u00e9bec");
        this.c.addItem("Liszt");
        this.c.addItem("Lokoce France");
        this.c.addItem("Lycos");
        this.c.addItem("Magellan");
        this.c.addItem("Majooh Search");
        this.c.addItem("Mamma");
        this.c.addItem("Matilda");
        this.c.addItem("Metacrawler");
    }
    
    void fillasia() {
        this.c.addItem("NewHoo");
        this.c.addItem("Nomade France");
        this.c.addItem("Northernlight");
        this.c.addItem("NZSearch");
        this.c.addItem("Ol\u00e9! Spain");
        this.c.addItem("OneKey");
        this.c.addItem("Oomph! Korea");
        this.c.addItem("Openfind Taiwan");
        this.c.addItem("Polar Search Scandinavia");
        this.c.addItem("Question Ecila");
        this.c.addItem("Russia on the Net");
        this.c.addItem("Savvy Search");
        this.c.addItem("Scrubtheweb");
        this.c.addItem("Search");
        this.c.addItem("SearchUK");
        this.c.addItem("Senrigan Japan");
        this.c.addItem("Shinyseek Italy");
        this.c.addItem("Snap");
        this.c.addItem("Starting Point");
        this.c.addItem("Swiss Search");
        this.c.addItem("Thai WWW");
        this.c.addItem("UkDirectory");
        this.c.addItem("WebCrawler");
        this.c.addItem("WebSitez");
        this.c.addItem("What Site");
        this.c.addItem("Whatuseek");
        this.c.addItem("Yahoo");
        this.c.addItem("Yahoo Asia");
        this.c.addItem("Yahoo Canada");
        this.c.addItem("Yahoo Spain");
        this.c.addItem("YamWeb Navigator Taiwan");
        this.c.addItem("Yeah (China)");
        this.c.addItem("Zebra South Africa");
        this.c.addItem("Zoek NL");
        this.c.addItem("100Hot");
        this.c.addItem("1Blink");
    }
    
    void fillnews() {
        this.c.addItem("American Journ. Review");
        this.c.addItem("Bild Online Archiv");
        this.c.addItem("Canadian Online Explorer");
        this.c.addItem("CNN");
        this.c.addItem("DejaNews");
        this.c.addItem("DisInfo");
        this.c.addItem("Findlaw");
        this.c.addItem("Irish Times");
        this.c.addItem("Los Angeles Times");
        this.c.addItem("News Index");
        this.c.addItem("Total News");
        this.c.addItem("Virtual Job Fair");
        this.c.addItem("Yahoo Reuters");
    }
    
    void fillsoftware() {
        this.c.addItem("Byte");
        this.c.addItem("DaveCentral");
        this.c.addItem("Dogpile");
        this.c.addItem("Filez");
        this.c.addItem("Freew. Publish. Site");
        this.c.addItem("FreewareHome");
        this.c.addItem("Info Mac");
        this.c.addItem("Linux Mall");
        this.c.addItem("Linux Online");
        this.c.addItem("MacinSearch");
        this.c.addItem("MacNN");
        this.c.addItem("MacUpdate");
        this.c.addItem("MacWorld");
        this.c.addItem("Mac Zone");
        this.c.addItem("PcGame");
        this.c.addItem("Pc Zone");
        this.c.addItem("Pigeons.net");
        this.c.addItem("Shareware");
        this.c.addItem("SoftSeek");
        this.c.addItem("Tucows");
        this.c.addItem("VersionTracker");
        this.c.addItem("WebAttack");
        this.c.addItem("Windowscentral");
        this.c.addItem("ZebraBox Germany");
    }
    
    void fillpeople() {
        this.c.addItem("ICQ Lists");
        this.c.addItem("BigBook");
        this.c.addItem("CitySurf");
        this.c.addItem("WhoWhere");
        this.c.addItem("555-1212");
    }
    
    void fillhobbies() {
        this.c.addItem("JMp3");
        this.c.addItem("MediaFind (ChaosMp3)");
        this.c.addItem("MediaFlash");
        this.c.addItem("Media Track");
        this.c.addItem("Movie Finder");
        this.c.addItem("Mp3Site");
        this.c.addItem("mp3.box");
        this.c.addItem("Music Search (mIRC-X)");
        this.c.addItem("Scour.net (Mp3)");
    }
    
    void fillmoney() {
        this.c.addItem("justQuotes");
        this.c.addItem("Silicon Investor");
        this.c.addItem("StockSite");
        this.c.addItem("Street Eye");
    }
    
    void gotoSearchUrl(final String s) {
        String s2 = "";
        if (s == "Mit Edu") {
            s2 = "http://web.mit.edu/bin/nph-search?query=" + this.tf.getText() + "&category=any&maxresultflag=200&errorflag=0&caseflag=on&wordflag=on&descflag=on&broker=web&brokerqueryconfig=web.cf&opaqueflag=on";
        }
        if (s == "Linux Mall") {
            s2 = "http://www.linuxmall.com/cgi-bin/TextSrch.cgi?EXPR=" + this.tf.getText();
        }
        if (s == "Linux Online") {
            s2 = "http://www.linux.org/cgi-bin/search.cgi?site=On&match=20&page=1&query=" + this.tf.getText();
        }
        if (s == "Rutgers Netherlands") {
            s2 = "http://www.rutgers.nl/search=context?query=" + this.tf.getText();
        }
        if (s == "ICQ Lists") {
            s2 = "http://cgi.mirabilis.com/cgi-bin/search/search.pl5?KEYWORDS=" + this.tf.getText() + "&DAYS=&SUBSTRING=substring&CONTEXT=Search+in+all+documents";
        }
        if (s == "Matilda") {
            s2 = "http://www.aaa.com.au/matilda/nsearch.cgi?query=" + this.tf.getText();
        }
        if (s == "Northernlight") {
            s2 = "http://www.northernlight.com/nlquery.fcg?qr=" + this.tf.getText() + "&si=&cb=0&cc=&us=025&sb.x=25&sb.y=20";
        }
        if (s == "Pc Zone") {
            s2 = "http://www.search.zones.com/search/bin/searchinterface.dll?result_template=searchinterface_pc.tem&stemming=y&num_rows=50&stemming=y&Sort_Field=Mfr_Name&fieldName=section_path&fieldmatch0=N/PC_Zone/&value=" + this.tf.getText();
        }
        if (s == "Mac Zone") {
            s2 = "http://www.search.zones.com/search/bin/searchinterface.dll?result_template=searchinterface_mac.tem&stemming=y&num_rows=50&stemming=y&Sort_Field=Mfr_Name&fieldName=section_path&fieldmatch0=N/Mac_Zone/&value=" + this.tf.getText();
        }
        if (s == "Info Mac") {
            s2 = "http://hyperarchive.lcs.mit.edu/cgi-bin/NewSearch?key=" + this.tf.getText();
        }
        if (s == "Majooh Search") {
            s2 = "http://www.luxembourg.org/cgi-bin/htsearch?config=htdig&exclude=&format=builtin-long&method=and&restrict=&words=" + this.tf.getText();
        }
        if (s == "Infomak") {
            s2 = "http://search.infomak.com/?words=" + this.tf.getText() + "&method=and";
        }
        if (s == "Utrecht University Holland") {
            s2 = "http://search.surfnet.nl/cgi-bin/search.pl?fmt=.&zoekterm=url:ruu.nl+url:uu.nl&andterm=" + this.tf.getText() + "&orterm=&notterm=&tijd=alles&Web=on&referer=http://www.ruu.nl/zoeken/&header=http://www.ruu.nl/zoeken/header.html&footer=http://www.ruu.nl/zoeken/footer.html";
        }
        if (s == "Erasmus University Holland") {
            s2 = "http://www.eur.nl/cgi-bin/htsearch?config=htdig&restrict=&exclude=&method=or&format=builtin-long&words=" + this.tf.getText();
        }
        if (s == "Freew. Publish. Site") {
            s2 = "http://www.katho.be/cgi-bin/search2.pl?terms=" + this.tf.getText() + "&boolean=AND&case=Insensitive";
        }
        if (s == "AOL") {
            s2 = "http://netfind.aol.com/search.gw?search=" + this.tf.getText() + "&lk=excite_netfind2_us&nrm=n&pri=on&xls=b";
        }
        if (s == "Thai WWW") {
            s2 = "http://www.cpe.ku.ac.th/~thaiwww/cgi/search/search.cgi?search_string=" + this.tf.getText() + "&page=0";
        }
        if (s == "Carrefour France") {
            s2 = "http://cgi.carrefour.net/recherche?b=0&n=25&s=" + this.tf.getText() + "&ui=c&serveur=1";
        }
        if (s == "Nomade France") {
            s2 = "http://rechercher.nomade.fr/recherche.asp?s=" + this.tf.getText() + "&f=N&l=L";
        }
        if (s == "Question Ecila") {
            s2 = "http://ecila.ceic.com/cgi-bin/SFgate?text=" + this.tf.getText() + "&action=phrase&language=french";
        }
        if (s == "Russia on the Net") {
            s2 = "http://www.ru/cgi/find.cgi?Str_Find=" + this.tf.getText() + "&LANG=ON&QUERY_ID=447186";
        }
        if (s == "Explora Mexico") {
            s2 = "http://search1.exploramexico.net/cgi-bin/search/search?boleano=1&archivo=+&start=0&texto=" + this.tf.getText() + "&cuantos=20";
        }
        if (s == "La Br\u00fajula Chile") {
            s2 = "http://www.brujula.cl/cgi-bin/brujula/busca.html?term=" + this.tf.getText();
        }
        if (s == "Shinyseek Italy") {
            s2 = "http://www.shinyseek.it/cgi-bin/shinyseek?MODE=FIND&KEY=" + this.tf.getText();
        }
        if (s == "Polar Search Scandinavia") {
            s2 = "http://www.polarsearch.net/search.asp?query=" + this.tf.getText();
        }
        if (s == "Kvasir Sweden") {
            s2 = "http://search.kvasir.se/ar/search.cgi?query=" + this.tf.getText() + "&mengde=web-se&language=se&mode=and";
        }
        if (s == "Jubii Danmark") {
            s2 = "http://soeg.jubii.dk/res.asp?soegeord=" + this.tf.getText() + "&soegedatabase=hele+Jubii";
        }
        if (s == "Ol\u00e9! Spain") {
            s2 = "http://www.ole.es/cgi-bin/buscar.cgi?Claus=" + this.tf.getText();
        }
        if (s == "Kaixo! internet Spain") {
            s2 = "http://www.kaixo.com/cgi-local/bilatu?hizkun=B&bila=" + this.tf.getText();
        }
        if (s == "La Toile du Qu\u00e9bec") {
            s2 = "http://recherche.toile.qc.ca/cgi-bin/recherche?query=" + this.tf.getText() + "&lang=fr&range=0-19&operator=and";
        }
        if (s == "Lokoce France") {
            s2 = "http://lokace.iplus.fr/cgi-bin/lokace?MOTCLEF=" + this.tf.getText() + "&DATA=Web&AFF=2";
        }
        if (s == "Francit\u00e9") {
            s2 = "http://francite.infinit.net/i3dpro/francite.exe/q?name=" + this.tf.getText() + "&quelrech=et&ortho=exact";
        }
        if (s == "Hiv InSite") {
            s2 = "http://hivinsite.ucsf.edu/bin/sw.pl?query=" + this.tf.getText() + "&op=AND&time=any+date&results=20&rank=Rank&tier=All";
        }
        if (s == "Gonzaga University") {
            s2 = "http://www.gonzaga.edu/scripts/query.idq?CiRestriction=" + this.tf.getText() + "&CiMaxRecordsPerPage=10&CiScope=%2F&TemplateName=query&CiSort=rank%5Bd%5D&HTMLQueryForm=%2Fsearch%2Findex.html";
        }
        if (s == "Yale University Library Web") {
            s2 = "http://www.library.yale.edu/cgi-bin/swish_usr_web.pl/?keyword=" + this.tf.getText() + "&results=0";
        }
        if (s == "University of Zurich") {
            s2 = "http://www.unizh.ch/cgi-bin/unisearch?keywords=" + this.tf.getText() + "&type=body&url=unizh.ch&flg=yes&case=no&back=www.unizh.ch%2F";
        }
        if (s == "Delft University of Technology") {
            s2 = "http://www.tudelft.nl:80/Harvest/cgi-bin/BrokerQuery.pl.cgi?query=" + this.tf.getText() + "&broker=dutis&caseflag=on&errorflag=0&descflag=on&verbose=on&maxresultflag=50";
        }
        if (s == "University of Amsterdam") {
            s2 = "http://zoek.uva.nl/compass?scope=" + this.tf.getText() + "&ui=sr&view-template=uva";
        }
        if (s == "Yahoo Reuters") {
            s2 = "http://search.news.yahoo.com/search/news?p=" + this.tf.getText() + "&n=10";
        }
        if (s == "Total News") {
            s2 = "http://totalnews.com/cgi-bin/query?Q=" + this.tf.getText();
        }
        if (s == "Inference Find") {
            s2 = "http://www.infind.com/infind/infind.exe?query=" + this.tf.getText() + "&time=7";
        }
        if (s == "Brigham Young University") {
            s2 = "http://www.byu.edu:81/cgi-bin/query?pg=q&fmt=.&q=" + this.tf.getText();
        }
        if (s == "University of Hawaii") {
            s2 = "http://www.hawaii.edu/cgi-bin/htsearch?config=htdig&restrict=&exclude=&method=and&format=builtin-long&words=" + this.tf.getText();
        }
        if (s == "Stanford University") {
            s2 = "http://search.stanford.edu/query.html?col=stanford&qp=&qt=" + this.tf.getText() + "&qs=&qc=&ws=0&qm=0&st=1&nh=10&lk=1&rf=0&oq=&rq=0";
        }
        if (s == "University of Virginia") {
            s2 = "http://minerva.acc.Virginia.EDU:80/Harvest/cgi-bin/nph-search.cgi?brokerqueryconfig=UVa.cf&query=" + this.tf.getText() + "&host=minerva.acc.Virginia.EDU%3A8501&caseflag=on&wordflag=on&errorflag=0&sort=by-rank&maxobjflag=50&opaqueflag=on&descflag=on&verbose=on";
        }
        if (s == "Alaska Pacific University") {
            s2 = "http://www.alaskapacific.edu/search/search.idq?CiRestriction=" + this.tf.getText() + "&CiScope=%2F&CiMaxRecordsPerPage=10&TemplateName=search&CiSort=rank%5Bd%5D&HTMLQueryForm=search.htm";
        }
        if (s == "University of New South Wales") {
            s2 = "http://cruise.comms.unsw.edu.au:8765/query.html?col=unsw&qp=&qt=" + this.tf.getText() + "&qc=&qm=0&st=1&nh=10&lk=1&rf=0&oq=&rq=0";
        }
        if (s == "University of Oregon") {
            s2 = "http://waterfall.uoregon.edu/cgi-bin/query?pg=q&fmt=.&q=" + this.tf.getText();
        }
        if (s == "Dictionary of Cell Biology") {
            s2 = "http://www.mblab.gla.ac.uk/~julian/dict.cgi?query=" + this.tf.getText();
        }
        if (s == "GenBank") {
            s2 = "http://www.ncbi.nlm.nih.gov/irx/cgi-bin/submit_form_query?TITLE=GenBank+Text+Retrieval+Output&INPUTS=4&Q1=" + this.tf.getText() + "&OP1=AND&Q2=&OP2=AND&Q3=&OP3=AND&Q4=&NDOCS=100&DB=gbupdates+genbank";
        }
        if (s == "Children with Diabetes") {
            s2 = "http://www.childrenwithdiabetes.com/cgi-bin/cwdsearch.pl?keywords=" + this.tf.getText() + "&indexname=Children+with+Diabetes";
        }
        if (s == "HealthAtoZ") {
            s2 = "http://ss5a.medconnect.com/cgi-bin/searchsites/pri?keywords=" + this.tf.getText();
        }
        if (s == "University of Pennsylvania") {
            s2 = "http://www.upenn.edu:8080/cgi-bin/query?pg=q&q=" + this.tf.getText();
        }
        if (s == "Vindex NL") {
            s2 = "http://www.webwereld.nl/cgi-bin/zoek/nph-go.cgi?resume=on&aantal=on&newwindow=Yes&in=" + this.tf.getText() + "&submit.x=21&submit.y=42";
        }
        if (s == "Zoek NL") {
            s2 = "http://gevonden-op.zoek.nl/cgi-zoek.nl/webinator?cmd=find&db=dbonline&grsz=10&thesaurus=0&timeout=-1&arg=" + this.tf.getText() + "&Zoek%21=Zoek%21&disp=number%2Cview%2Ctitle%2Csize%2Cbody%2Crank%2Clink%2Ctree&asz=230&proximity=sent&suffixproc=max";
        }
        if (s == "American Journ. Review") {
            s2 = "http://www.newslink.org/cgi/search.cgi?terms=" + this.tf.getText() + "&type=Entire+site";
        }
        if (s == "Mp3Site") {
            s2 = "http://search.mp3site.com/search.phtml?query=" + this.tf.getText();
        }
        if (s == "Bild Online Archiv") {
            s2 = "http://www.bild.de/cgi-bin/service/suche/archiv/archiv_suche.cgi?Quest=" + this.tf.getText() + "&Suchweise=Allen+Texten&Anfang=&Sortierung=&Back=%2Fservice%2Farchiv%2Fsuche%2Farchiv%2Fsuche.html";
        }
        if (s == "Irish Times") {
            s2 = "http://www.irish-times.com/cgi-bin/search/advanced.idq?TextRes=" + this.tf.getText() + "&Day=%3F%3F&Month=%3F%3F&Year=%3F%3F%3F%3F&Sect=&CiMaxRecordsInResultSet=25&CiSort=rank%5Bd%5D&CiMaxRecordsPerPage=25&HTMLQueryForm=%2Fcgi-bin%2Fsearch%2Fsearch.html";
        }
        if (s == "Canadian Online Explorer") {
            s2 = "http://search.canoe.ca/search?NS-collection=CANOE&NS-search-page=results&NS-search-type=NS-boolean-query&NS-max-records=20&NS-query=" + this.tf.getText();
        }
        if (s == "Los Angeles Times") {
            s2 = "http://www.latimes.com/bin/iatoc?NS-query=" + this.tf.getText() + "&NS-adv-search=0&NS-search-type=boolean&NS-max-records=20&NS-collection=DailyNews&NS-collection=APOnline";
        }
        if (s == "News Index") {
            s2 = "http://www.newsindex.com/cgi-bin/process.cgi?query=" + this.tf.getText() + "&mode=all";
        }
        if (s == "Yeah (China)") {
            s2 = "http://www.yeah.net/cgi-bin/query2.exe?query=" + this.tf.getText() + "&start=0&REXP=AND";
        }
        if (s == "MediaFlash") {
            s2 = "http://sunsite.icm.edu.pl/home/kelcon/cgi-bin/music.cgi?co=" + this.tf.getText();
        }
        if (s == "JMp3") {
            s2 = "http://128.83.114.221/search.exe?MP3Name=" + this.tf.getText();
        }
        if (s == "Swiss Search") {
            s2 = "http://www.search.ch/Search?l=de&loc=ch&q=" + this.tf.getText() + "&n=10&f=standard";
        }
        if (s == "Kolibri Germany") {
            s2 = "http://www.kolibri.de/cgi-bin/qesql.ksh?Offset=1&f=noframes&objekt=10&Suchwort=" + this.tf.getText() + "&Verk=AND&Dars=stnd&sort=d&MaxAnz=10";
        }
        if (s == "Apollo7 Germany") {
            s2 = "http://www.apollo7.de/cgi-bin/mc.search.cgi?QUERY=" + this.tf.getText() + "&TIMEOUT=10&DOMAIN=de&FC=1&HITS_PER_PAGE=10&IFS=Nathan+Eule+Aladin+Sharelook+Inter%2DFux+Blitzsuche+Sternchen+Flix+Netguide";
        }
        if (s == "ZebraBox Germany") {
            s2 = "http://zebra.asta.fh-weingarten.de/cgi-bin/WebSearch.CMD?page=result&language=english&searchplace=filedescfilename&category=0&desc=long&maxfound=50&style=&searchtext=" + this.tf.getText() + "&y=s";
        }
        if (s == "Yahoo Asia") {
            s2 = "http://search.yahoo.com.sg/search/sg?p=" + this.tf.getText() + "&y=s";
        }
        if (s == "Yahoo Spain") {
            s2 = "http://ink.yahoo.com/bin/query_es?p=" + this.tf.getText() + "&z=1&hc=0&hs=0";
        }
        if (s == "Yahoo Canada") {
            s2 = "http://search.yahoo.ca/search?p=" + this.tf.getText() + "&x=search";
        }
        if (s == "Zebra South Africa") {
            s2 = "http://www.zebra.co.za/search/search?perpage=10&how=with&string=" + this.tf.getText();
        }
        if (s == "Dir. Nac. Argentino") {
            s2 = "http://dna.iwcc.com:8080/Phantom.acgi$search?Details=Details&searchText=" + this.tf.getText() + "&maxHits=35";
        }
        if (s == "Australian Nat. University") {
            s2 = "http://online.anu.edu.au/dirs/search.html?stype=ANU+Web+Pages&querytext=" + this.tf.getText();
        }
        if (s == "Scour.net (Mp3)") {
            s2 = "http://www.scour.net/search/query.phtml?query=" + this.tf.getText() + "&metaindex=AUDIO&class=exn&index=mp3&x=31&y=31";
        }
        if (s == "Media Track") {
            s2 = "http://hybridmp3.ml.org/search.perl?QS=" + this.tf.getText() + "&typeMPX=on&typeVQF=on";
        }
        if (s == "Music Search (mIRC-X)") {
            s2 = "http://www.mircx.com/cgi-bin/s?q=" + this.tf.getText() + "&d=m";
        }
        if (s == "MediaFind (ChaosMp3)") {
            s2 = "http://194.95.209.6/cgi-bin/search.cgi?file=" + this.tf.getText() + "&filetype=mp3";
        }
        if (s == "mp3.box") {
            s2 = "http://kiwi.napri.sk/cgi-bin/marek/robot/robot?srch=" + this.tf.getText() + "&project=mp3&gfx=mp3";
        }
        if (s == "Openfind Taiwan") {
            s2 = "http://www.openfind.com.tw/cgi-bin/Taiwan/webgais2.exe?database=TW&query=" + this.tf.getText() + "&group_by=site";
        }
        if (s == "What Site") {
            s2 = "http://www.whatsite.com/main/search.cgi?keyword=" + this.tf.getText() + "&start=0&REXP=OR";
        }
        if (s == "YamWeb Navigator Taiwan") {
            s2 = "http://search.yam.org.tw/b5/search/?k=" + this.tf.getText();
        }
        if (s == "Hong Kong Search") {
            s2 = "http://www.hksrch.com/cgi/srch.cgi?searchtext=" + this.tf.getText();
        }
        if (s == "Pigeons.net") {
            s2 = "http://pigeons.net/cgi-bin/dbsearch/dbsearch_s.cgi?CONFFILE=/drv1/web/sites/web751c5/cgi-bin/dbsearch/dbsearch.cfg&discript=" + this.tf.getText();
        }
        if (s == "Virtual Hospital") {
            s2 = "http://www.vh.org/cgi/aglimpse/11/var/web/VH?query=" + this.tf.getText() + "&errors=0&maxfiles=30&maxlines=10";
        }
        if (s == "NewHoo") {
            s2 = "http://www.newhoo.com/cgi-bin/search?search=" + this.tf.getText() + "&all=yes&cat=Computers%2FSoftware%2FInternet%2FClients%2FSearch";
        }
        if (s == "City University of Hong Kong") {
            s2 = "http://www.cityu.edu.hk/cgi-bin/wwwwais?keywords=" + this.tf.getText() + "&message=If+you+can+see+this%2C+then+your+browser+can%27t+support+hidden+fields.&source=cityu.swish&sourcedir=%2Fhome%2Fweb%2Fadmin%2Fwwwtools%2Fswish%2Fdatabase%2F&maxhits=40&sorttype=score&host=&port=&searchprog=swish&iconurl=%2Ficons%2Fwwwwais&useicons=yes";
        }
        if (s == "PubMed") {
            s2 = "http://www.ncbi.nlm.nih.gov/htbin-post/Entrez/query?form=4&db=m&term=" + this.tf.getText() + "&dispmax=20&relentrezdate=No+Limit";
        }
        if (s == "Centers for Dis. Control & Prev.") {
            s2 = "http://search.cdc.gov/search97cgi/s97_cgi.exe?Action=Search&Collection=CDCALL&ResultTemplate=cdcnormal.hts&queryText=" + this.tf.getText() + "&SortField=Score";
        }
        if (s == "Food and Drug Administration") {
            s2 = "http://www.verity.fda.gov/search97cgi/s97is.dll?Action=Search&AdminImagePath=&ServerKey=Primary&ResultTemplate=stndrslp.hts&Theme=&Company=&QueryText=" + this.tf.getText() + "&collection=all";
        }
        if (s == "Cal. State Uni, Long Beach") {
            s2 = "http://www.csulb.edu/cgi-bin/findpeople.pl?" + this.tf.getText();
        }
        if (s == "HKU of Sience & Technology") {
            s2 = "http://search.ust.hk/compass?scope=" + this.tf.getText() + "&browse-category=ROOT&search-category=ROOT&ui=sr&chunk-size=&page=1&taxonomy=HKUST";
        }
        if (s == "Dogpile") {
            s2 = "http://ms3.dogpile.com/search?q=" + this.tf.getText() + "&fs=web&ss=stop&to=twenty";
        }
        if (s == "Movie Finder") {
            s2 = "http://www.moviefinder.com/Movie/Find/Results/1,9,,40.html?mpos=begin&type=movie&spat=" + this.tf.getText();
        }
        if (s == "Street Eye") {
            s2 = "http://www.streeteye.com/cgi-bin/allseeingeye.cgi?searchstring=" + this.tf.getText();
        }
        if (s == "Senrigan Japan") {
            s2 = "http://senrigan.ascii.co.jp/cgi-bin/cgimat?LANG=English&WORD=" + this.tf.getText() + "&LIMIT=32&OMITSAME=true&SORTBYLINK=false&PROTOCOL=all";
        }
        if (s == "Cade Brazil") {
            s2 = "http://busca.cade.com.br/scripts/engine.exe?p1=" + this.tf.getText() + "&p2=1&p3=1";
        }
        if (s == "Euroseek") {
            s2 = "http://www.euroseek.net/query?iflang=uk&query=" + this.tf.getText() + "&domain=world&lang=world";
        }
        if (s == "Cari Malaysia") {
            s2 = "http://206.184.233.23/cariurl.cgi?" + this.tf.getText();
        }
        if (s == "Oomph! Korea") {
            s2 = "http://www.oomph.net/~dasen21/dasencgi/brief.cgi?v_db=1&v_userid=158&v_query=" + this.tf.getText() + "&v_hangul=1&v_expert=Search";
        }
        if (s == "Goo Japan") {
            s2 = "http://www.goo.ne.jp/default.asp?MT=" + this.tf.getText() + "&SM=MC&WTS=ntt&DE=2&DC=10&_v=2";
        }
        if (s == "1Blink") {
            s2 = "http://www.1blink.com/search.cgi?q=" + this.tf.getText();
        }
        if (s == "Savvy Search") {
            s2 = "http://williams.cs.colostate.edu:1969/nph-search?KW=" + this.tf.getText() + "&classic=on&t1=x&t2=x&t3=x&t4=x&t5=x&t6=x&t7=x&t8=x&t9=x&t10=x&Boolean=AND&Hits=10&Mode=MakePlan&df=normal&AutoStep=on";
        }
        if (s == "Canada") {
            s2 = "http://results.canada.com/search/search.asp?RG=world&SM=must%3Awords&QRY=" + this.tf.getText() + "&PS=10&DT=1&GO.x=30&GO.y=8";
        }
        if (s == "KHOJ") {
            s2 = "http://www.khoj.com/bin/khoj_search?searchkey=" + this.tf.getText();
        }
        if (s == "StockSite") {
            s2 = "http://www.stocksite.com/research/?symbol=" + this.tf.getText() + "&button=1";
        }
        if (s == "Silicon Investor") {
            s2 = "http://www3.techstocks.com/~wsapi/investor/search?s=" + this.tf.getText();
        }
        if (s == "AliWeb") {
            s2 = "http://www.aliweb.com/form2.pl?query=" + this.tf.getText() + "&showdescription=on&titlefield=on&descriptionfield=on&keywordfield=on&urlfield=on&hits=20&domain=&searchtype=Whole+Word&types=Any";
        }
        if (s == "WebAttack") {
            s2 = "http://www.webattack.com/cgi-bin/web73425/search.pl?Range=All&Format=Standard&Terms=" + this.tf.getText();
        }
        if (s == "Liszt") {
            s2 = "http://www.liszt.com/lists.cgi?word=" + this.tf.getText() + "&junk=s&an=all";
        }
        if (s == "Byte") {
            s2 = "http://www.byte.com/search?queryText=" + this.tf.getText();
        }
        if (s == "AlCanSeek") {
            s2 = "http://www.alcanseek.com/acgibin/find.cgi?" + this.tf.getText() + "=01";
        }
        if (s == "Claymont") {
            s2 = "http://www.claymont.com/cgi-bin/htsearch?config=htdig&restrict=&exclude=&method=and&format=builtin-long&words=" + this.tf.getText();
        }
        if (s == "CitySurf") {
            s2 = "http://www.citysurf.com/mmag/city_picker.cgi?city=" + this.tf.getText();
        }
        if (s == "Cyber411") {
            s2 = "http://www.cyber411.com/cgi-bin/nph-search.cgi?AV=on&DN=on&EX=on&GX=on&G2=on&HB=on&LS=on&LY=on&MG=on&NL=on&PS=on&SC=on&TS=on&WC=on&WU=on&YH=on&query=" + this.tf.getText() + "&timeout=30&connects=5";
        }
        if (s == "Virtual Job Fair") {
            s2 = "http://www.vjf.com/cgi-bin/texis/vjf/vjfapp/srchresults.html?function=search&andr=and+&start=1&matches=10&JOBTITLE_LOWER=" + this.tf.getText();
        }
        if (s == "OneKey") {
            s2 = "http://www.onekey.com/search/search.cgi?query=" + this.tf.getText() + "&logic=or&max_hits=10";
        }
        if (s == "FreewareHome") {
            s2 = "http://www.freewarehome.com/cgi-bin/swishsearch/search.pl?swishindex=%2Fusr%2Fbin%2Fswish%2Findex.swish&keywords=" + this.tf.getText();
        }
        if (s == "NZSearch") {
            s2 = "http://www.nzsearch.com/cgi-localbin/nzsearch.cgi?search=" + this.tf.getText();
        }
        if (s == "MacinSearch") {
            s2 = "http://macinsearch.com/find.cgi?BEGIN=0&SEARCH=" + this.tf.getText() + "&x=62&y=7";
        }
        if (s == "justQuotes") {
            s2 = "http://www.justquotes.com/name.cgi?lookup=" + this.tf.getText();
        }
        if (s == "Findlaw") {
            s2 = "http://legalnews.findlaw.com/scripts/legalnews.pl?frame=top&L=Search_Results&R=search&CiRestriction=" + this.tf.getText();
        }
        if (s == "PcGame") {
            s2 = "http://www.pcgame.com/finder/search.cgi?s=" + this.tf.getText();
        }
        if (s == "WhoWhere") {
            s2 = "http://query1.whowhere.com/jwz/name.wsrch?name=" + this.tf.getText();
        }
        if (s == "CNN") {
            s2 = "http://search.cnn.com:8765/query.html?qt=" + this.tf.getText() + "&qc=&col=cnni&qm=0&st=1&nh=10&lk=1&rf=1";
        }
        if (s == "SoftSeek") {
            s2 = "http://www.softseek.com/cgi-bin/search.cgi?keywords=" + this.tf.getText() + "&seekindex=index&maxresults=025&cb=++";
        }
        if (s == "UkDirectory") {
            s2 = "http://www.ukdirectory.com/datafiles/alphasearch.cgi?searchbox=" + this.tf.getText();
        }
        if (s == "SearchUK") {
            s2 = "http://www.searchuk.com/cgi-bin/search?search=" + this.tf.getText() + "&z=0&y=1&w=0&g=0&r=&ru=&n=3";
        }
        if (s == "MacWorld") {
            s2 = "http://macworld.zdnet.com/search/url-iatoc?NS-collection=News.98&NS-collection=News.97&NS-collection=Reviews.98&NS-collection=Reviews.97&NS-collection=Feature.98&NS-collection=Feature.97&NS-collection=Column.98&NS-collection=Column.97&NS-collection=Netsmart&NS-collection=Online.Features&NS-collection=Media&NS-collection=Gameline&NS-collection=Daily.News&NS-search-type=Free%2bText&NS-max-records=20&NS-query=" + this.tf.getText();
        }
        if (s == "MacNN") {
            s2 = "http://209.143.231.219:80/fastsearch?Type=Search&Template=MacNN&WebSite=MacNN&Category=Hardware&SearchFor=" + this.tf.getText();
        }
        if (s == "VersionTracker") {
            s2 = "http://www.versiontracker.com/searchVT.pl?SearchType=Search&SearchFor=" + this.tf.getText() + "&SearchIn=ProgName";
        }
        if (s == "100Hot") {
            s2 = "http://www.100hot.com/cgi-bin/main_search.cgi?query=" + this.tf.getText();
        }
        if (s == "DisInfo") {
            s2 = "http://www.disinfo.com/cgi-bin/htsearch.cgi?words=" + this.tf.getText() + "&config=htdig&matchesperpage=20&method=and&format=builtin-long&domain=WWW";
        }
        if (s == "Starting Point") {
            s2 = "http://www.stpt.com/cgi-bin/pwrsrch/altavista.cgi?query=" + this.tf.getText() + "&search=web";
        }
        if (s == "AltaVista") {
            s2 = "http://www.altavista.digital.com/cgi-bin/query?pg=q&what=web&fmt=.&q=" + this.tf.getText();
        }
        if (s == "DejaNews") {
            s2 = "http://x8.dejanews.com/=hotbot/dnquery.xp?QRY=" + this.tf.getText() + "&ST=PS&DBS=1&defaultOp=AND&maxhits=50&format=terse&showsort=score&groups=&authors=&subjects=&fromdate=&todate=";
        }
        if (s == "DaveCentral") {
            s2 = "http://www.davecentral.com/cgi-bin/search.pl?query=" + this.tf.getText();
        }
        if (s == "555-1212") {
            s2 = "http://www.namesecure.com/cgi-shl/newwhois.pl?who=" + this.tf.getText() + ".com&CFID=1102637&CFTOKEN=7307";
        }
        if (s == "BigBook") {
            s2 = "http://yp.gte.net/listings.phtml?SRC=bb&STYPE=S&PG=L&C=" + this.tf.getText() + "&N=&T=&S=&R=N&search=Find+It";
        }
        if (s == "Windowscentral") {
            s2 = "http://www.windowscentral.com/cgi-bin/tips.cgi?OS=windows95&tip=" + this.tf.getText();
        }
        if (s == "MacUpdate") {
            s2 = "http://www.macupdate.com/subcategories.lasso?-db=software&-layout=search&-op=bw&keywords=" + this.tf.getText() + "&-maxRecords=25&-sortfield=Title&-search=contains@-noResults=/pub/macupdate/error.lasso";
        }
        if (s == "Shareware") {
            s2 = "http://search.shareware.com/code/engine/Find?logop=and&cfrom=quick&orfile=True&hits=25&search=" + this.tf.getText() + "&category=MS-Windows%28all%29";
        }
        if (s == "Tucows") {
            s2 = "http://search.tucows.com/cgi-bin/webglimpse/webhome/server/htdocs/search?maxlines=20&maxfiles=50&whole=off&errors=0&case=off&query=" + this.tf.getText();
        }
        if (s == "Filez") {
            s2 = "http://search.filez.com/search.cgi?type=4&key=" + this.tf.getText() + "&search=1&rows=20";
        }
        if (s == "WebSitez") {
            s2 = "http://search.websitez.com/search.cgi?key=" + this.tf.getText() + "&search=1&type=1&submit1=Find";
        }
        if (s == "Dewa") {
            s2 = "http://www.dewa.com/cgi-bin/search.cgi?k=" + this.tf.getText() + "&b=o";
        }
        if (s == "Dogpile") {
            s2 = "http://ms1.dogpile.com/search?q=" + this.tf.getText() + "&fs=web&ss=stop&to=twenty";
        }
        if (s == "AskJeeves") {
            s2 = "http://www.askjeeves.com/AskJeeves.asp?ask=" + this.tf.getText() + "&qSource=0&site_name=Jeeves&metasearch=yes";
        }
        if (s == "Goto") {
            s2 = "http://www.goto.com/d/search/;$sessionid$H4EWPLIAAAYTFQFIEENQPUQ?Keywords=" + this.tf.getText();
        }
        if (s == "Scrubtheweb") {
            s2 = "http://www.scrubtheweb.com/cgi-bin/search.cgi?action=Search&cat=All&searchtype=all&keyword=" + this.tf.getText();
        }
        if (s == "Identify") {
            s2 = "http://www.identify.com/identify.cgi?w=" + this.tf.getText() + "&st=p";
        }
        if (s == "Metacrawler") {
            s2 = "http://www.metacrawler.com/crawler?general=" + this.tf.getText() + "&method=0&target=&region=0&rpp=20&timeout=5&hpe=10";
        }
        if (s == "Magellan") {
            s2 = "http://www.mckinley.com/search.gw?search=" + this.tf.getText() + "&c=web&look=magellan";
        }
        if (s == "Whatuseek") {
            s2 = "http://seek.whatuseek.com/cgi-bin/seek.alpha.go?db=db&defcmd=find&disp=all&grsz=0&proximity=rank&suffixproc=off&thesaurus=0&arg=" + this.tf.getText();
        }
        if (s == "Highway61") {
            s2 = "http://207.226.255.65/nph-seek.cgi?string=" + this.tf.getText() + "&bool=and&new_wins=on&speed=reasonable&hits=lots&yahoo_cats=on&armadillo=5&s=wwwyx&dom=2&c=73701";
        }
        if (s == "Mamma") {
            s2 = "http://www.mamma.com/cgi-bin/parsearch2?lang=1&timeout=6&qtype=0&query=" + this.tf.getText() + "&summaries=on";
        }
        if (s == "Ilse") {
            s2 = "http://www.ilse.com/?COMMAND=search_for&LANGUAGE=NL&ANDOR=OR&EXTRACT=short&SEARCH_FOR=" + this.tf.getText();
        }
        if (s == "Yahoo") {
            s2 = "http://search.yahoo.com/search?p=" + this.tf.getText();
        }
        if (s == "Infoseek") {
            s2 = "http://www.infoseek.com/Titles?qt=" + this.tf.getText();
        }
        if (s == "Hotbot") {
            s2 = "http://www.hotbot.com/default.asp?MT=" + this.tf.getText();
        }
        if (s == "Lycos") {
            s2 = "http://www-english.lycos.com/cgi-bin/pursuit?matchmode=and&cat=lycos&query=" + this.tf.getText() + "&x=22&y=6";
        }
        if (s == "WebCrawler") {
            s2 = "http://webcrawler.com/cgi-bin/WebQuery?searchText=" + this.tf.getText();
        }
        if (s == "Snap") {
            s2 = "http://home.snap.com/search/directory/results/1,61,home-0,00.html?category=0-0-WW&keyword=" + this.tf.getText();
        }
        if (s == "Excite") {
            s2 = "http://search.excite.com/search.gw?trace=1&look=excite_netscape_us&sorig=netscape&search=" + this.tf.getText();
        }
        if (s == "Search") {
            s2 = "http://www.search.com/Infoseek/1,135,0,0200.html?QUERY=" + this.tf.getText();
        }
        if (this.tf.getText().length() < 1) {
            this.tf.setText("Enter a criteria");
            return;
        }
        if (this.tf.getText().length() > 0) {
            try {
                this.theURL = new URL(s2);
            }
            catch (MalformedURLException ex) {
                System.out.println("Bad URL: " + this.theURL);
            }
            this.getAppletContext().showDocument(this.theURL, this.fram);
        }
    }
    
    public worldengine() {
        this.b = new Button("Go");
    }
}
