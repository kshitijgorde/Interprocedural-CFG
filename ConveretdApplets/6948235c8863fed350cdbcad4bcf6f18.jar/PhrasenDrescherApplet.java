import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Component;
import javafig.gui.ImageHelper;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import java.awt.Button;
import hades.models.special.TextLCDCanvas;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PhrasenDrescherApplet extends Applet implements ActionListener
{
    int orig_index;
    boolean automatic;
    TextLCDCanvas lcdCanvas;
    Button resetButton;
    Button origButton;
    Button nextButton;
    Button autoButton;
    Timer timer;
    static String message1;
    static String message2;
    static String welcomeMessage;
    static int ROM_LENGTH;
    static String[] citations;
    static String[][] phrases;
    int[] rom;
    
    static {
        PhrasenDrescherApplet.message1 = "*** Millenium-Phrasendrescher VLSI *** ";
        PhrasenDrescherApplet.message2 = "(C) 1999, AB TECH (DEMOCOM-3 inside)    ";
        PhrasenDrescherApplet.welcomeMessage = String.valueOf(PhrasenDrescherApplet.message1) + PhrasenDrescherApplet.message2;
        PhrasenDrescherApplet.ROM_LENGTH = 16384;
        PhrasenDrescherApplet.citations = new String[] { "Es mu\u00df doch m\u00f6glich sein!", "Sie k\u00f6nnen jetzt gehen. Jetzt kommen nur noch Sachthemen.", "Da mu\u00df man wie ein Ingenieur herangehen", "Studenten st\u00f6ren den Unibetrieb", "Urlaub m\u00fc\u00dfte man abschaffen", "Das ist unverst\u00e4ndlich!", "Der Chef ist f\u00fcr die Mitarbeiter da", "Die Informatik ist in allen nebens\u00e4chlichen Bereichen f\u00fchrend", "Nebenfachstudenten backen guten Kuchen", "W\u00e4hrend der Gruppenbesprechung f\u00e4llt der Mitarbeiter vom Stuhl", "Mein Rechner ist instabil!", "Der Begriff Medieninformatik ist nicht ausreichend definiert", "Das ist nur ein Beispiel", "Mikrosystemtechnik ist innovativ", "VLSI-Entwurf ist anspruchsvoll", "Da m\u00fcsste man mal einen Diplomanden dransetzen", "Mich interessieren nur L\u00f6sungen, nicht L\u00f6sungswege", "So kann man einem Informatiker nicht kommen", "St\u00f6rung auf der siebten Interruptebene", "device not found at addr:F315", "illegal access from module m.dll at addr:F311" };
        PhrasenDrescherApplet.phrases = new String[][] { { "Der Professor", "erkl\u00e4rt", "den MOS-Transistor" }, { "Der Fachbereichsrat", "diskutiert \u00fcber", "Nichtigkeiten" }, { "Das Gremium", "diskutiert stundenlang \u00fcber", "die Nachfolge" }, { "Die Studentin", "denkt an", "ein kompliziertes KV-Diagramm" }, { "Der Student", "verschl\u00e4ft", "die Vorlesung" }, { "Der Ingenieur", "entwirft", "einen Mikroprozessor" }, { "Der Mitarbeiter", "verliert", "die Geduld" }, { "Der Kommissar", "\u00fcberf\u00fchrt", "den Betr\u00fcger" }, { "Der Lokomotivf\u00fchrer", "bremst", "die Schmalspurbahn" }, { "Der Bauausschuss", "verz\u00f6gert", "den Neubau des H\u00f6rsaals" }, { "Das Gutachten", "erl\u00e4utert", "die M\u00e4ngel" }, { "Die letzte Seite der bits", "pr\u00e4sentiert", "die lustigsten Versprecher" }, { "Das Protokoll", "verzichtet auf", "s\u00e4mtliche Details" }, { "Die Mensa", "wirbt f\u00fcr", "eine franz\u00f6sische Woche" }, { "Das MAZ", "erstellt", "das Chiplayout" }, { "Die TU-Harburg", "bekommt", "unerwartete Drittmittel" }, { "Die GMD", "organisiert", "den EIS-Workshop" }, { "Eine Furie", "verdirbt", "das gute Arbeitsklima" }, { "Der Ehemann", "belegt", "einen Kochkurs" }, { "Der Techniker", "entwirft", "den komplexen Schaltplan" }, { "Der Gastprofessor", "analysiert", "den Quantencomputer" }, { "Der Assistent", "erforscht", "den Assoziativspeicher" }, { "Der Kollege", "denkt nur an", "den fr\u00fchen Feierabend" }, { "Der Assistent", "beweist", "das gesuchte Theorem" }, { "Der Pr\u00fcfling", "vergi\u00dft", "den Interrupt" }, { "Der Dekan", "genehmigt", "eine Dienstreise" }, { "Der Architekt", "skizziert", "das Steuerwerk" }, { "Word '97", "verwendet", "den falschen Zeichensatz" }, { "Der Springer-Verlag", "w\u00fcnscht sich", "eine Neuauflage" }, { "Der Autor", "versteuert", "das Honorar" }, { "Der Kritiker", "verlangt", "diverse Verbesserungen" }, { "Die Chipfabrik", "bem\u00e4ngelt", "Fanout-Verletzungen" }, { "Die Softwaretechnik", "verschleiert", "die Informatik" }, { "Die Theorie", "erm\u00f6glicht", "eine interessante Vorhersage" }, { "Die Fachschaft", "verteufelt", "die Mathe-Klausur" }, { "Das Pr\u00fcfungsamt", "mahnt", "das Gutachten" }, { "Die Mitarbeiterin", "verl\u00e4ngert", "den Urlaub in Mexiko" }, { "Netscape", "verschenkt", "einen Internet-Browser" }, { "Das Ingenieurb\u00fcro", "regelt", "billige Schrittmotoren" }, { "Die Spurensicherung", "identifiziert", "den Fingerabdruck" }, { "Die Technologie", "erlaubt", "immer kleinere Strukturen" }, { "Der Sprecher", "erzwingt", "eine Ger\u00e4te-Inventur" }, { "Der Sicherheitsbeauftragte", "berichtet \u00fcber", "zahlreiche M\u00e4ngel" }, { "Das Umweltteam", "kauft", "nagelneue K\u00fchlschr\u00e4nke" }, { "Ein Designer", "gestaltet", "das neue Briefpapier" }, { "Der Hacker", "verunsichert", "den Systemadministrator" }, { "Der Doktorand", "simuliert", "ein neuronales Netz" }, { "Ein Roboter", "\u00fcberpr\u00fcft", "den Na\u00dfdampfregeler" }, { "Der Silicon-Compiler", "\u00fcbersch\u00e4tzt", "die Gatterlaufzeit" }, { "Das Betriebssystem", "\u00fcberschreibt", "die letzte Sicherheitskopie" }, { "Die EIS-Connection", "genehmigt sich", "die F\u00f6rdermittel" }, { "Die Sekret\u00e4rin", "editiert", "die VW-Liste" }, { "Siemens", "hofft auf", "einen gesunden Halbleitermarkt" }, { "Der SRA", "zerredet", "den neuen Studienplan" }, { "Der Maschinenmeister", "repariert", "die Flurbeleuchtung" }, { "Sun Microsystems", "demonstriert", "eine asynchrone Schaltung" }, { "EUROCHIP", "verteilt", "die neue Software" }, { "Synopsys", "verkauft", "VHDL-Modelle" }, { "Microsoft", "produziert", "massenweise CD-ROMs" }, { "Der IEEE", "standardisiert", "eine Hardwarebeschreibungssprache" }, { "IBM", "entwickelt", "riesige Gate-Arrays" }, { "Der Nebenf\u00e4chler", "versteht", "den Democom" }, { "Ein Java-Applet", "animiert", "einen Strukturhazard" }, { "Der Dozent", "vermittelt", "das Taktschema" }, { "Die DFG", "bewertet", "einen Sonderforschungsbereich" }, { "Die stud. Hilfskraft", "hasst", "den Befehlssatz" }, { "Der Diplomand", "\u00fcberspringt", "die unterste Entwurfsebenene" }, { "Der Fahrdienstleiter", "\u00fcbersieht", "einen Gefahrguttransport" }, { "Der Programmierer", "importiert", "eine Klassenbibliothek" }, { "Der PowerPC", "unterst\u00fctzt", "indirekte Addressierung" }, { "Die Verwaltung", "vergi\u00dft", "den Poststempel" }, { "Der Doktorand", "ist bereit f\u00fcr", "den Minnedienst" }, { "Das Programmkommitee", "bewertet", "die Qualit\u00e4t der Paper" }, { "Das Rechenzentrum", "bezahlt", "die Campus-Lizenzen" }, { "Der Chairman", "entscheidet", "den Konflikt" }, { "Die GAL", "beschlie\u00dft", "das Semesterticket" }, { "Die SPD", "bef\u00fcrchtet", "den Globalhaushalt" }, { "Die CDU", "vermi\u00dft", "ein Gesamtkonzept" }, { "Die WELT", "berichtet \u00fcber", "das SPIEGEL-Ranking" }, { "Der Model-Checker", "verifiziert", "ein systolisches Array" }, { "Der Scheme-Prozessor", "kopiert", "eine sehr lange Liste" }, { "Der AB-Leiter", "fotografiert", "den Orient-Express" }, { "Der Uni-Pr\u00e4sident", "\u00fcberstimmt", "die Personalplanung" }, { "Die graue Eminenz", "schmunzelt \u00fcber", "den Ausschreibungstext" }, { "Die Senatorin", "bel\u00e4chelt", "die virtuelle Realit\u00e4t" }, { "Simueva", "durchsucht", "die komprommitierenden Daten" }, { "Die Analogtechnik", "\u00fcbersteht", "die Miniaturisierung" }, { "Die Senatorin", "schw\u00f6rt auf", "die Medieninformatik" }, { "Der PowerPC", "minimiert", "das Binary Decision Diagram" }, { "Der C4", "versteckt", "das Strategiepapier" }, { "Der Schwerpunkt", "mi\u00dfachtet", "den Fachbereichsbeschlu\u00df" }, { "Der Redakteur", "bewertet", "das Vorlesungsskript" }, { "Der Kapitalgeber", "verliert", "das gesamte Risikokapital" }, { "Die deutsche Bank", "riskiert", "Peanuts in H\u00f6he von 1 Mrd. DM" }, { "Der Praktikant", "vernachl\u00e4ssigt", "das Halteproblem" }, { "Der Intrigant", "sinniert \u00fcber", "die Regeln von Macchiavelli" }, { "Der Hitzkopf", "unterbricht", "die endlose Diskussion" }, { "Der Wirtschaftsausschu\u00df", "bef\u00fcrwortet", "den Vektorrechner" }, { "Das GELATI-System", "exportiert", "die Leitungs-Kapazit\u00e4ten" }, { "Motorola", "erprobt", "einen 10\" Wafer" }, { "Die Telekom", "verschickt", "eine \u00fcberh\u00f6hte Rechnung" }, { "Die Festk\u00f6rperphysik", "best\u00e4tigt", "den Phasen\u00fcbergang" }, { "Die Vortragende", "begeistert sich f\u00fcr", "die Fehlertoleranz" }, { "Das EXOR-Gatter", "berechnet", "den Cyclic Redundancy Check" }, { "Der Modellbahner", "bewundert", "die Baureihe 01" }, { "Der Lebenslauf", "verschweigt", "die j\u00fcngsten Exzesse" }, { "Der B\u00fcrgermeister", "kritisiert", "die Dauerstellen" }, { "Altavista", "findet", "den gesuchten Algorithmus" }, { "Der Projektleiter", "l\u00f6scht", "die letzten Magnetb\u00e4nder" }, { "Der Beamte", "verschleppt", "den Abgabetermin" }, { "Apple Computer", "verbessert", "die einheitliche Textverarbeitung" }, { "Philips", "bevorzugt", "schnelle Operationsverst\u00e4rker" }, { "Der Schwiegersohn", "scannt", "das Portraitphoto" }, { "Die Volksf\u00fcrsorge", "ben\u00f6tigt", "spezielle Taschenrechner" }, { "Der SPIEGEL", "publiziert", "die geheimsten Interna" }, { "Der Personalrat", "best\u00e4tigt", "das Freisemester" }, { "Die Unternehmensberatung", "beruft sich auf", "das exponentielle Wachstum" }, { "Die Hochschuldidaktik", "bewirbt", "ein Lehreseminar" }, { "Der Operator", "vermeidet", "den drohenden Datenverlust" }, { "Der Privatdozent", "fabuliert \u00fcber", "asymmetrische Kryptoverfahren" }, { "Der FB-Planer", "organisiert", "die Zeltstadt im Informatikum" }, { "Der Unix-Guru", "begehrt", "einen bequemen Arbeitsplatz" }, { "Der Audi-H\u00e4ndler", "vertuscht", "die schadhafte Motorelektrik" }, { "Der Arbeitsmarkt", "verdeutlicht", "den Studentenmangel" }, { "Die Koryph\u00e4e", "verantwortet", "das Standardwerk" }, { "Der Student", "st\u00f6rt", "den Unibetrieb" }, { "Captain Kirk", "rettet", "die Enterprise" }, { "Lieutenant Uhura", "kontaktiert", "die Cyborgs" }, { "Mr. Spock", "beherrscht", "den Todesgriff der Vulkanier" }, { "Der gro\u00dfe Unbekannte", "entwendet", "ein Dutzend SPARCstations" }, { "Der Patentanwalt", "\u00fcberfliegt", "den eingereichten Antrag" }, { "Die Bibliothekarin", "besorgt", "die DAC-Proceedings" }, { "Der RZ-Leiter", "widerruft", "den Projekt-Account" }, { "Der Beamtenbund", "verk\u00fcrzt", "die Ruhepause" }, { "Der Funkamateur", "begeistert sich f\u00fcr", "den KISS-Chip" }, { "Der Virenforscher", "untersucht", "einen Internet-Wurm" }, { "Ein Virus", "formatiert", "die gesamte Festplatte" }, { "Die Tochter", "interpretiert", "die Kunst der Fuge" }, { "Die Projektgruppe", "verl\u00e4\u00dft sich auf", "die ZIMO-Steuerung" }, { "Die ZVV", "druckt", "das Vorlesungsskript" }, { "Das Rechenzentrum", "formuliert", "das aktuelle KVV" }, { "Der Parteivorsitzende", "erh\u00e4lt", "Posts\u00e4cke voller Disketten" }, { "Der Systemarchitekt", "analysiert", "die Klassenhierarchie" }, { "Die Aushilfe", "vermasselt", "die Grundvorlesung" }, { "Der Chef", "untersch\u00e4tzt", "die verh\u00e4ngnisvolle Unterschrift" }, { "Der Gutachter", "verweist auf", "die Sollbruchstelle" }, { "Der Agent", "verschl\u00fcsselt", "vertrauliche Personaldaten" }, { "Der Staatssekret\u00e4r", "notiert", "unsachliche Argumente" }, { "Intel", "sch\u00e4tzt", "die Gr\u00f6\u00dfe der Komplexgatter" }, { "Der Arbeitsbereich", "bastelt", "einen Mikrorechner" }, { "Der Systemadministrator", "beendet", "den Druckauftrag" }, { "Die Sekret\u00e4rin", "druckt", "nutzlose Farbfolien" }, { "Der Professor", "wartet auf", "den eiligen Ausdruck" }, { "Der Ausschu\u00df", "pl\u00e4diert f\u00fcr", "eine reibungslose Zusammenarbeit" }, { "Die EG", "f\u00f6rdert", "den iX-Feldbusprozessor" }, { "Der Komponist", "notiert", "eine weitere Wiederholung" }, { "Der Theoretiker", "modelliert", "Petri-Netze" }, { "Der Querulant", "behindert", "den Fortschritt" }, { "Windows 95", "verletzt", "s\u00e4mtliche Echtzeitbedingungen" }, { "Der Techniker", "stoppt", "die Simulationszeit" }, { "Die Gegenstelle", "wartet auf", "ein Acknowledge-Signal" }, { "Der Entwickler", "programmiert", "einen schnellen Webserver" }, { "Der Eindringling", "ver\u00e4ndert", "das Root-Passwort" }, { "Der Consultant", "stolpert \u00fcber", "den Gesch\u00e4ftsbericht" }, { "Eine Arbeitsgruppe", "ruiniert", "das gegenseitige Vertrauen" }, { "Die Lehredekanin", "proklamiert", "die neuen Kernvorlesungen" }, { "Der Hinterb\u00e4nkler", "untersch\u00e4tzt", "die Dynamik des E-Commerce" }, { "Der Erfinder", "verdoppelt", "die Gesamtlebensdauer" }, { "Das Hochschulorchester", "ignoriert", "die Tempo-Angaben" }, { "Die Frauenbeauftragte", "verweist auf", "die Statistik im Jahresbericht" }, { "Der Tanzlehrer", "\u00e4rgert sich \u00fcber", "die Fehltritte" }, { "Der Hausarzt", "diagnositiziert", "ein Magengeschw\u00fcr" }, { "Der Gutsherr", "bereut", "die letzte Investition" }, { "Der Schatzmeister", "jammert \u00fcber", "die endlosen Preissteigerungen" }, { "Der Endlosstudent", "klagt gegen", "das Klausurergebnis" }, { "Ein Serienkiller", "\u00fcberwacht", "ein ahnungsloses Opfer" }, { "Der Oberkellner", "empfiehlt", "einen passenden Rotwein" }, { "Der Wunderheiler", "verl\u00e4\u00dft sich auf", "die W\u00fcnschelrute" }, { "Die Reiseleiterin", "erduldet", "die unbegr\u00fcndeten Beschwerden" }, { "Der Fernsehmoderator", "genie\u00dft", "das Lampenfieber" }, { "Der Skeptiker", "ha\u00dft", "die Knoff-Hoff Show" }, { "Der Geschichtenerz\u00e4hler", "schw\u00e4rmt f\u00fcr", "die M\u00e4rchen aus 1001 Nacht" }, { "Die Diskussionsleitung", "sorgt f\u00fcr", "faire Randbedingungen" }, { "Die gute Fee", "verzaubert", "den Goldesel" }, { "Der Augenzeuge", "belastet", "den Manta-Fahrer" }, { "Der Hypochonder", "erwischt", "die spanische Grippe" }, { "Der Kredithai", "ruiniert", "die Milchm\u00e4dchenrechnung" }, { "Der Beisitzer", "protokolliert", "jede einzelne Pr\u00fcfungsfrage" }, { "Der Therapeut", "reduziert", "die Schmerzschwelle" }, { "Der neue Nachbar", "mi\u00dfachtet", "den Mietvertrag" }, { "Der Bewerber", "verschiebt", "die Bleibeverhandlung" }, { "Der Spezialist", "verlegt", "das veraltete Koaxkabel" }, { "Der Techniker", "installiert", "die ISDN-Telefonanlage" }, { "Die Gewerkschaft", "widerspricht gegen", "den Arbeitsvertrag" }, { "Der Heimwerker", "entschl\u00fcsselt", "die Wegfahrsperre" }, { "Der Forscher", "erkundet", "das korrekte Zeitverhalten" }, { "Das Opfer", "ver\u00f6ffentlicht", "eine Gegendarstellung" }, { "Die ZVS", "erh\u00f6ht", "den Numerus-Clausus" }, { "Der Praktiker", "bef\u00fcrchtet", "einen Deadlock" }, { "Das Sonderprogramm", "rettet", "die Mikrosystemtechnik" }, { "Die Graphikerin", "besorgt sich", "einen Tintenstrahldrucker" }, { "Der Professor", "beschreibt", "das DV-Flipflop" }, { "Der Internet-Provider", "verdr\u00e4ngt", "alle verbliebenen Konkurrenten" }, { "Der Ombudsmann", "schlichtet", "den Disput der Streith\u00e4hne" }, { "Die Staatsanw\u00e4ltin", "vollendet", "die Anklageschrift" }, { "Der fliegende H\u00e4ndler", "verschleudert", "billige Raubkopien" }, { "Der Richter", "bezeifelt", "das Argument des Angeklagten" }, { "Der Stipendiat", "verbessert", "die Fehlersimulation" }, { "Der Streber", "bem\u00fcht sich um", "eine schwere Diplomarbeit" }, { "Die Brieffreundin", "schreibt", "den langen Brief" }, { "Die Erbtante", "verlangt", "einen soliden Lebenswandel" }, { "Das Fundb\u00fcro", "versteigert", "den vergessenen Regenschirm" }, { "Der Marktforscher", "korrigiert", "die wackelige Prognose" }, { "Der Pr\u00fcfungsausschu\u00df", "genehmigt", "die Dissertation" }, { "Das neue Gesetz", "stabilisiert", "die Pattsituation" }, { "Der Parteifreund", "gr\u00fcndet", "die Seilschaft" }, { "Der Zollinspektor", "entdeckt", "die Grauimporte" }, { "Der Makler", "erwartet", "eine zus\u00e4tzliche Pr\u00e4mie" }, { "Der Gerichtsvollzieher", "\u00fcberklebt", "die Invertarnummer" }, { "Die Diskussionsrunde", "sammelt", "die weiteren Gegenargumente" }, { "Der Advocatus diaboli", "belebt", "die Gruppenbesprechung" }, { "Der Bastler", "ben\u00f6tigt", "einen Glasfaserpinsel" }, { "Der Testingenieur", "dokumentiert", "die Entwurfsfehler" }, { "Der Urlauber", "besichtigt", "die Kathedrale in Toledo" }, { "Der Philosoph", "\u00fcberwindet", "die Begriffsschwierigkeiten" }, { "Der Student", "lernt", "die lose gekoppelten Automaten" }, { "Der Mathematiker", "pr\u00e4zisiert", "die neue Definition" }, { "Der Physiker", "modelliert", "den pn-\u00fcbergang" }, { "Der Elektroniker", "berechnet", "das Kennlinienfeld" }, { "Ein Betriebswirt", "optimiert", "den Arbeitsablauf" }, { "Der Astronom", "beobachtet", "den Urknall" }, { "Der Senior Scientist", "benutzt", "die Electrologika" }, { "Die Dekanin", "belohnt", "das beste Vordiplom" }, { "Das TBZ", "vermarktet", "die Prototypen" }, { "Das Management", "verschweigt", "die Kinderkrankheiten" }, { "Die Pressestelle", "meldet", "einen grandiosen Erfolg" }, { "Die KI", "verachtet", "prozedurale Sprachen" }, { "Der Anlageberater", "beobachtet", "den Online-Ticker" }, { "Der Stammtisch", "diskutiert sachkundig \u00fcber", "die Sicherheitsaspekte" }, { "Die Elefantenrunde", "definiert", "den Kriterienkatalog" }, { "Der Berufene", "fordert", "zus\u00e4tzliche Landesstellen" }, { "Die Startup-Firma", "propagiert", "dynamische Logik" }, { "Der Neuling", "beherzigt", "die guten Ratschl\u00e4ge" }, { "Der Spekulant", "bereinigt", "das Aktiendepot" }, { "Der Handwerker", "verursacht", "einen Kurzschlu\u00df" }, { "Der Professor", "erinnert sich an", "die Rickmer-Rickmers" }, { "Die Blondine", "bereut", "einen Knebelvertrag" }, { "Der Spieler", "kauft sich", "eine \u00fcbertaktete 3D-Graphikkarte" }, { "Der Fanatiker", "zerst\u00f6rt", "das Kernkraftwerk" }, { "Das Fernsehen", "zeigt", "den Unfall" }, { "Der Hardware-Fan", "verdoppelt", "den Hauptspeicher" }, { "Der Macher", "erreicht", "eine Erh\u00f6hung der Sachmittel" }, { "Der Chaos Computer Club", "demonstriert", "die Schw\u00e4chen der Chipkarte" }, { "Der Spassvogel", "lacht \u00fcber", "das E-Mail Attachment" }, { "Der Wissenschafter", "liefert", "v\u00f6llig neue Erkenntnisse" }, { "Der Democom-3", "drischt", "sinnlose Phrasen" } };
    }
    
    public PhrasenDrescherApplet() {
        this.rom = new int[PhrasenDrescherApplet.ROM_LENGTH];
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.automatic = false;
        if (actionEvent.getSource() == this.resetButton) {
            this.showMessage();
        }
        else if (actionEvent.getSource() == this.origButton) {
            this.nextOriginal();
        }
        else if (actionEvent.getSource() == this.nextButton) {
            this.nextRandom();
        }
        else if (actionEvent.getSource() == this.autoButton) {
            this.automatic = true;
        }
        else {
            System.err.println("-E- internal: unknown event source: " + actionEvent);
        }
    }
    
    void dumpDemocomAssembler() {
        try {
            final String s = "hugo.democom";
            System.out.println("-I- writing Democom3 assembler to " + s);
            final PrintStream printStream = new PrintStream(new FileOutputStream(s));
            printStream.println(";======== start phrasen daten ================");
            for (int i = 0; i < PhrasenDrescherApplet.phrases.length; ++i) {
                printStream.print("str" + i + ":     ");
                printStream.print(".assho    \"");
                printStream.print(PhrasenDrescherApplet.phrases[i][0]);
                printStream.print(" \"");
                printStream.println();
                printStream.println("          .defw     0");
                printStream.print("          .assho    \"");
                printStream.print(PhrasenDrescherApplet.phrases[i][1]);
                printStream.print(" \"");
                printStream.println();
                printStream.println("          .defw     0");
                printStream.print("          .assho    \"");
                printStream.print(PhrasenDrescherApplet.phrases[i][2]);
                printStream.print("\"");
                printStream.println();
                printStream.println("          .defw     0");
            }
            printStream.println(";======== start string tabelle ================");
            printStream.println("anzahl:     .defw      " + PhrasenDrescherApplet.phrases.length);
            printStream.println("tabell:     .defw      str1");
            for (int j = 1; j < PhrasenDrescherApplet.phrases.length; ++j) {
                printStream.println("            .defw      str" + j);
            }
            printStream.println(";======== end string tabelle ==================");
            printStream.close();
            System.out.println("-I- Democom3 assembler ok.");
        }
        catch (Exception ex) {
            System.err.println("-E- got an exception: " + ex);
        }
    }
    
    void dumpRom() throws Exception {
        final PrintStream printStream = new PrintStream(new FileOutputStream("hugo.hex"));
        for (int i = 0; i < this.rom.length; ++i) {
            printStream.println(String.valueOf(this.getHexString(i, 4)) + " " + this.getHexString(this.rom[i], 2));
        }
        printStream.close();
    }
    
    void dumpRomAsText() throws Exception {
        final PrintStream printStream = new PrintStream(new FileOutputStream("hugo.txt"));
        for (int i = 0; i < this.rom.length; ++i) {
            printStream.println(this.getHexString(i, 4) + " " + this.getHexString(this.rom[i], 2) + " " + (char)this.rom[i] + " ");
        }
        printStream.close();
    }
    
    void dumpRomBinary() throws Exception {
        final DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("hugo.dat"));
        for (int i = 0; i < this.rom.length; ++i) {
            dataOutputStream.writeByte((byte)(this.rom[i] & 0xFF));
        }
        dataOutputStream.close();
    }
    
    protected String getHexString(final int n, final int n2) {
        String s;
        if (n < 0) {
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < n2; ++i) {
                sb.append('X');
            }
            s = sb.toString();
        }
        else {
            s = Long.toHexString(n);
        }
        final StringBuffer sb2 = new StringBuffer();
        for (int n3 = n2 - s.length(), j = 0; j < n3; ++j) {
            sb2.append('0');
        }
        sb2.append(s);
        return sb2.toString();
    }
    
    public String getObject() {
        return PhrasenDrescherApplet.phrases[(int)(PhrasenDrescherApplet.phrases.length * Math.random())][2];
    }
    
    public String getSubject() {
        return PhrasenDrescherApplet.phrases[(int)(PhrasenDrescherApplet.phrases.length * Math.random())][0];
    }
    
    public String getVerb() {
        return PhrasenDrescherApplet.phrases[(int)(PhrasenDrescherApplet.phrases.length * Math.random())][1];
    }
    
    public void init() {
        this.automatic = false;
        this.orig_index = 0;
        ImageHelper.setVisibleParent(this);
        (this.lcdCanvas = new TextLCDCanvas(2, 40)).enableDisplay(true);
        this.lcdCanvas.selectTwoLines(true);
        this.lcdCanvas.enableCursor(false);
        this.lcdCanvas.selectUnderlineCursor(false);
        this.lcdCanvas.enableCursorBlinking(true);
        this.resetButton = new Button("Reset");
        this.origButton = new Button("Original...");
        this.nextButton = new Button("Phrase...");
        this.autoButton = new Button("Automatic...");
        this.resetButton.addActionListener(this);
        this.origButton.addActionListener(this);
        this.nextButton.addActionListener(this);
        this.autoButton.addActionListener(this);
        final Panel panel = new Panel(new FlowLayout(0));
        panel.add(this.resetButton);
        panel.add(this.nextButton);
        panel.add(this.autoButton);
        this.setLayout(new BorderLayout());
        this.add("Center", this.lcdCanvas);
        this.add("South", panel);
        this.replaceAllUmlauts();
        this.showMessage();
    }
    
    public void nextOriginal() {
        this.showPhrase(String.valueOf(PhrasenDrescherApplet.phrases[this.orig_index][0]) + " " + PhrasenDrescherApplet.phrases[this.orig_index][1] + " " + PhrasenDrescherApplet.phrases[this.orig_index][2]);
        ++this.orig_index;
        if (this.orig_index >= PhrasenDrescherApplet.phrases.length) {
            this.orig_index = 0;
        }
    }
    
    public void nextRandom() {
        String s;
        if (Math.random() < 0.03) {
            s = String.valueOf(PhrasenDrescherApplet.citations[(int)(Math.random() * PhrasenDrescherApplet.citations.length)]);
        }
        else if (Math.random() < 0.05) {
            final int n = (int)(PhrasenDrescherApplet.phrases.length * Math.random());
            s = String.valueOf(PhrasenDrescherApplet.phrases[n][0]) + " " + PhrasenDrescherApplet.phrases[n][1] + " " + PhrasenDrescherApplet.phrases[n][2];
        }
        else {
            s = String.valueOf(this.getSubject()) + " " + this.getVerb() + " " + this.getObject();
        }
        this.showPhrase(s);
    }
    
    public void replaceAllUmlauts() {
        PhrasenDrescherApplet.message1 = this.replaceUmlauts(PhrasenDrescherApplet.message1);
        PhrasenDrescherApplet.message2 = this.replaceUmlauts(PhrasenDrescherApplet.message2);
        PhrasenDrescherApplet.welcomeMessage = this.replaceUmlauts(PhrasenDrescherApplet.welcomeMessage);
        for (int i = 0; i < PhrasenDrescherApplet.citations.length; ++i) {
            PhrasenDrescherApplet.citations[i] = this.replaceUmlauts(PhrasenDrescherApplet.citations[i]);
        }
        for (int j = 0; j < PhrasenDrescherApplet.phrases.length; ++j) {
            PhrasenDrescherApplet.phrases[j][0] = this.replaceUmlauts(PhrasenDrescherApplet.phrases[j][0]);
            PhrasenDrescherApplet.phrases[j][1] = this.replaceUmlauts(PhrasenDrescherApplet.phrases[j][1]);
            PhrasenDrescherApplet.phrases[j][2] = this.replaceUmlauts(PhrasenDrescherApplet.phrases[j][2]);
        }
    }
    
    public String replaceUmlauts(String s) {
        s = s.replace('\u00e4', '\u00e1');
        s = s.replace('\u00f6', '\u00ef');
        s = s.replace('\u00fc', '\u00f5');
        s = s.replace('\u00df', '\u00e2');
        return s;
    }
    
    public void showMessage() {
        this.lcdCanvas.clearDisplay();
        this.lcdCanvas.cursorHome();
        for (int i = 0; i < PhrasenDrescherApplet.message1.length(); ++i) {
            this.lcdCanvas.setDataAt(i, PhrasenDrescherApplet.message1.charAt(i));
        }
        for (int j = 0; j < PhrasenDrescherApplet.message2.length(); ++j) {
            this.lcdCanvas.setDataAt(j + 64, PhrasenDrescherApplet.message2.charAt(j));
        }
        this.lcdCanvas.repaint();
    }
    
    public void showPhrase(final String s) {
        this.lcdCanvas.clearDisplay();
        this.lcdCanvas.cursorHome();
        if (s.length() <= 40) {
            for (int i = 0; i < s.length(); ++i) {
                this.lcdCanvas.setDataAt(i, s.charAt(i));
            }
        }
        else {
            final int lastIndex = s.substring(0, 40).lastIndexOf(32);
            for (int j = 0; j < lastIndex; ++j) {
                this.lcdCanvas.setDataAt(j, s.charAt(j));
            }
            for (int k = lastIndex, address = 64; k < s.length(); ++k, ++address) {
                this.lcdCanvas.setDataAt(address, s.charAt(k));
            }
        }
        this.lcdCanvas.repaint();
    }
    
    public void start() {
        (this.timer = new Timer()).start();
    }
    
    public void stop() {
        if (this.timer != null) {
            this.timer.stop();
        }
    }
    
    public void writeROM() {
        System.out.println("-I- creating ROM data, writing to 'hugo.hex' ...");
        try {
            final int length = PhrasenDrescherApplet.citations.length;
            final int length2 = PhrasenDrescherApplet.phrases.length;
            final int length3 = PhrasenDrescherApplet.phrases.length;
            final int length4 = PhrasenDrescherApplet.phrases.length;
            for (int i = 0; i < this.rom.length; ++i) {
                this.rom[i] = 0;
            }
            this.writeRomWord(16, 1);
            this.writeRomWord(18, length);
            this.writeRomWord(20, length2);
            this.writeRomWord(22, length3);
            this.writeRomWord(24, length4);
            final int n = 32;
            final int n2 = 42;
            final int n3 = n2 + 2 * length;
            final int n4 = n3 + 2 * length2;
            final int n5 = n4 + 2 * length3;
            final int n6 = n5 + 2 * length4;
            this.writeRomWord(32, n6);
            this.writeRomWord(34, n2);
            this.writeRomWord(36, n3);
            this.writeRomWord(38, n4);
            this.writeRomWord(40, n5);
            final int writeRomString = this.writeRomString(n6, PhrasenDrescherApplet.welcomeMessage);
            System.out.println(n + " " + n2 + " " + n3 + " " + n4 + " " + n5 + " " + n6);
            System.out.println("-I- creating tables&indices, a_end=" + writeRomString);
            int writeRomString2 = writeRomString;
            for (int j = 0; j < PhrasenDrescherApplet.citations.length; ++j) {
                this.writeRomWord(n2 + 2 * j, writeRomString2);
                writeRomString2 = this.writeRomString(writeRomString2, PhrasenDrescherApplet.citations[j]);
            }
            int writeRomString3 = writeRomString2;
            for (int k = 0; k < PhrasenDrescherApplet.phrases.length; ++k) {
                this.writeRomWord(n3 + 2 * k, writeRomString3);
                writeRomString3 = this.writeRomString(writeRomString3, PhrasenDrescherApplet.phrases[k][0]);
            }
            int writeRomString4 = writeRomString3;
            for (int l = 0; l < PhrasenDrescherApplet.phrases.length; ++l) {
                this.writeRomWord(n4 + 2 * l, writeRomString4);
                writeRomString4 = this.writeRomString(writeRomString4, PhrasenDrescherApplet.phrases[l][1]);
            }
            int writeRomString5 = writeRomString4;
            for (int n7 = 0; n7 < PhrasenDrescherApplet.phrases.length; ++n7) {
                this.writeRomWord(n5 + 2 * n7, writeRomString5);
                writeRomString5 = this.writeRomString(writeRomString5, PhrasenDrescherApplet.phrases[n7][2]);
            }
            System.out.println("-I- last address used: " + writeRomString5);
            System.out.println("-I- dumping ROM data...");
            this.dumpRomAsText();
            this.dumpRom();
            this.dumpRomBinary();
            System.out.println("-I- writeROM ok.");
        }
        catch (Exception ex) {
            System.out.println("-E- in writeROM:" + ex);
        }
    }
    
    int writeRomString(final int n, final String s) {
        for (int i = 0; i < s.length(); ++i) {
            this.rom[n + i] = s.charAt(i);
        }
        final int n2 = n + s.length();
        this.rom[n2] = 0;
        return n2 + 1;
    }
    
    void writeRomWord(final int n, final int n2) {
        this.rom[n] = (n2 & 0xFF00) >> 8;
        this.rom[n + 1] = (n2 & 0xFF);
    }
    
    class Timer extends Thread
    {
        public void run() {
            while (true) {
                if (PhrasenDrescherApplet.this.automatic) {
                    PhrasenDrescherApplet.this.nextRandom();
                }
                try {
                    Thread.currentThread();
                    Thread.sleep(2500L);
                }
                catch (Exception ex) {}
            }
        }
    }
}
