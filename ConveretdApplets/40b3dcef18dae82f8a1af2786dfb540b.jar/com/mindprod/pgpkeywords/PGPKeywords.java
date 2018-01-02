// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.pgpkeywords;

import com.mindprod.common15.FontFactory;
import com.mindprod.common13.HybridJ;
import java.awt.Component;
import java.awt.GridBagConstraints;
import com.mindprod.common13.CMPAboutJBox;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import com.mindprod.common15.Laf;
import javax.swing.JMenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mindprod.common13.JEButton;
import java.awt.Insets;
import com.mindprod.fastcat.FastCat;
import com.mindprod.common11.ST;
import com.mindprod.common15.STA;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common11.VersionCheck;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.regex.Pattern;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JApplet;

public class PGPKeywords extends JApplet
{
    private static final int APPLET_HEIGHT = 500;
    private static final int APPLET_WIDTH = 800;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2011-02-16";
    private static final String SAMPLE_HEX_KEY = "9AA3 43B6 324D F154 4098 F58F EF62 A55F 92CB 3EDD";
    private static final String TITLE_STRING = "PGP Keywords";
    private static final String VERSION_STRING = "1.0";
    private static final String[] evenWords;
    private static final String[] oddWords;
    private static final Color BACKGROUND_FOR_APPLET;
    private static final Color BACKGROUND_FOR_INPUT;
    private static final Color FOREGROUND_FOR_INPUT;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_EDITABLE_FIELDS;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private static final Pattern SPLITTER;
    private JButton toHexButton;
    private JButton toKeywordsButton;
    private JLabel hexLabel;
    private JLabel instructions;
    private JLabel keywordsLabel;
    private JLabel title;
    private JLabel title2;
    private JTextArea keywords;
    private JTextField hex;
    
    public void destroy() {
        this.toHexButton = null;
        this.toKeywordsButton = null;
        this.hexLabel = null;
        this.instructions = null;
        this.title = null;
        this.title2 = null;
        this.keywords = null;
        this.hex = null;
    }
    
    public void init() {
        final Container contentPane = this.getContentPane();
        if (!VersionCheck.isJavaVersionOK(1, 5, 1, contentPane)) {
            return;
        }
        this.buildMenu();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(PGPKeywords.BACKGROUND_FOR_APPLET);
        this.buildComponents();
        this.layoutFields(contentPane);
    }
    
    private static String hexToKeywords(String hex) {
        hex = STA.squish(hex);
        if (hex.length() != 40) {
            return hex.length() + " digits. Need 40 hex digits";
        }
        if (!ST.isLegal(hex, "0123456789abcedfABCDEF")) {
            return "only 0-9 A-F or a-f and space allowed";
        }
        final FastCat sb = new FastCat(40);
        for (int i = 0; i < 20; ++i) {
            final int wordIndex = Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
            final String word = ((i & 0x1) == 0x0) ? PGPKeywords.evenWords[wordIndex] : PGPKeywords.oddWords[wordIndex];
            sb.append(word);
            if (i % 4 == 3) {
                sb.append('\n');
            }
            else {
                sb.append(' ');
            }
        }
        return sb.toString();
    }
    
    private static String keywordsToHex(final String keywords) {
        final String[] individualKeywords = PGPKeywords.SPLITTER.split(keywords);
        if (individualKeywords.length != 20) {
            return individualKeywords.length + " keywords. Must have 20.";
        }
        final StringBuilder sb = new StringBuilder(50);
        int count = 0;
        for (final String ik : individualKeywords) {
            boolean found = false;
            final String[] words = ((count & 0x1) == 0x0) ? PGPKeywords.evenWords : PGPKeywords.oddWords;
            for (int i = 0; i < 256; ++i) {
                if (ik.equalsIgnoreCase(words[i])) {
                    sb.append(ST.toLZHexString(i, 2).toUpperCase());
                    found = true;
                    break;
                }
            }
            if (!found) {
                sb.append("??");
            }
            if ((count & 0x1) != 0x0) {
                sb.append(" ");
            }
            ++count;
        }
        return sb.toString();
    }
    
    private void buildComponents() {
        (this.title = new JLabel("PGP Keywords 1.0")).setForeground(PGPKeywords.FOREGROUND_FOR_TITLE);
        this.title.setFont(PGPKeywords.FONT_FOR_TITLE);
        (this.title2 = new JLabel("released:2011-02-16 build:9411")).setFont(PGPKeywords.FONT_FOR_TITLE2);
        this.title2.setForeground(PGPKeywords.FOREGROUND_FOR_TITLE);
        (this.hexLabel = new JLabel("hex:")).setForeground(PGPKeywords.FOREGROUND_FOR_LABEL);
        (this.hex = new JTextField("9AA3 43B6 324D F154 4098 F58F EF62 A55F 92CB 3EDD", 40)).setEditable(true);
        this.hex.setMargin(new Insets(2, 2, 2, 2));
        this.hex.setFont(PGPKeywords.FONT_FOR_EDITABLE_FIELDS);
        this.hex.setBackground(PGPKeywords.BACKGROUND_FOR_INPUT);
        this.hex.setForeground(PGPKeywords.FOREGROUND_FOR_INPUT);
        final boolean usingMac = System.getProperty("os.name").equals("Mac OS X");
        final char downArrow = usingMac ? '\u2193' : '\u21d3';
        final char upArrow = usingMac ? '\u2191' : '\u21d1';
        (this.toKeywordsButton = new JEButton("Convert to keywords " + downArrow)).setToolTipText("Convert hex representing PGP public key to keywords.");
        this.toKeywordsButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                PGPKeywords.this.keywords.setText(hexToKeywords(PGPKeywords.this.hex.getText()));
            }
        });
        (this.keywordsLabel = new JLabel("keywords:")).setForeground(PGPKeywords.FOREGROUND_FOR_LABEL);
        (this.keywords = new JTextArea()).setEditable(true);
        this.keywords.setMargin(new Insets(2, 2, 2, 2));
        this.keywords.setFont(PGPKeywords.FONT_FOR_EDITABLE_FIELDS);
        this.keywords.setBackground(PGPKeywords.BACKGROUND_FOR_INPUT);
        this.keywords.setForeground(PGPKeywords.FOREGROUND_FOR_INPUT);
        this.keywords.setLineWrap(true);
        this.keywords.setWrapStyleWord(true);
        (this.toHexButton = new JEButton("Convert to hex " + upArrow)).setToolTipText("Convert keywords representing PGP public key to hex.");
        this.toHexButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                PGPKeywords.this.hex.setText(keywordsToHex(PGPKeywords.this.keywords.getText()));
            }
        });
        (this.instructions = new JLabel("Either: fill in the hex field and hit [convert to keywords " + downArrow + "], or fill in the keywords field and hit [convert to hex " + upArrow + "].")).setForeground(PGPKeywords.FOREGROUND_FOR_INSTRUCTIONS);
    }
    
    private void buildMenu() {
        final JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);
        menubar.add(Laf.buildLookAndFeelMenu());
        final JMenu menuHelp = new JMenu("Help");
        menubar.add(menuHelp);
        final JMenuItem aboutItem = new JMenuItem("About");
        menuHelp.add(aboutItem);
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox("PGP Keywords", "1.0", "Interconverts PGP keys between hex and keyword form", "", "freeware", "2011-02-16", 2011, "Roedy Green", "PGPKEYWORDS", "1.5");
            }
        });
    }
    
    private void layoutFields(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 0, 5), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(2, 0, 2, 1, 0.0, 0.0, 13, 0, new Insets(10, 5, 0, 10), 0, 0));
        contentPane.add(this.hexLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 10, 0, 5), 0, 0));
        contentPane.add(this.hex, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0, 17, 2, new Insets(5, 5, 0, 5), 0, 0));
        contentPane.add(this.toKeywordsButton, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 5, 0, 10), 0, 0));
        contentPane.add(this.keywordsLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 10, 0, 5), 0, 0));
        contentPane.add(this.keywords, new GridBagConstraints(1, 3, 2, 1, 100.0, 100.0, 17, 1, new Insets(5, 5, 0, 5), 0, 0));
        contentPane.add(this.toHexButton, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 5, 0, 10), 0, 0));
        contentPane.add(this.instructions, new GridBagConstraints(1, 4, 4, 1, 0.0, 0.0, 17, 0, new Insets(5, 5, 10, 10), 0, 0));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new PGPKeywords(), "PGP Keywords 1.0", 800, 500);
    }
    
    static {
        evenWords = new String[] { "aardvark", "absurd", "accrue", "acme", "adrift", "adult", "afflict", "ahead", "aimless", "Algol", "allow", "alone", "ammo", "ancient", "apple", "artist", "assume", "Athens", "atlas", "Aztec", "baboon", "backfield", "backward", "banjo", "beaming", "bedlamp", "beehive", "beeswax", "befriend", "Belfast", "berserk", "billiard", "bison", "blackjack", "blockade", "blowtorch", "bluebird", "bombast", "bookshelf", "brackish", "breadline", "breakup", "brickyard", "briefcase", "Burbank", "button", "buzzard", "cement", "chairlift", "chatter", "checkup", "chisel", "choking", "chopper", "Christmas", "clamshell", "classic", "classroom", "cleanup", "clockwork", "cobra", "commence", "concert", "cowbell", "crackdown", "cranky", "crowfoot", "crucial", "crumpled", "crusade", "cubic", "dashboard", "deadbolt", "deckhand", "dogsled", "dragnet", "drainage", "dreadful", "drifter", "dropper", "drumbeat", "drunken", "Dupont", "dwelling", "eating", "edict", "egghead", "eightball", "endorse", "endow", "enlist", "erase", "escape", "exceed", "eyeglass", "eyetooth", "facial", "fallout", "flagpole", "flatfoot", "flytrap", "fracture", "framework", "freedom", "frighten", "gazelle", "Geiger", "glitter", "glucose", "goggles", "goldfish", "gremlin", "guidance", "hamlet", "highchair", "hockey", "indoors", "indulge", "inverse", "involve", "island", "jawbone", "keyboard", "kickoff", "kiwi", "klaxon", "locale", "lockup", "merit", "minnow", "miser", "Mohawk", "mural", "music", "necklace", "Neptune", "newborn", "nightbird", "Oakland", "obtuse", "offload", "optic", "orca", "payday", "peachy", "pheasant", "physique", "playhouse", "Pluto", "preclude", "prefer", "preshrunk", "printer", "prowler", "pupil", "puppy", "python", "quadrant", "quiver", "quota", "ragtime", "ratchet", "rebirth", "reform", "regain", "reindeer", "rematch", "repay", "retouch", "revenge", "reward", "rhythm", "ribcage", "ringbolt", "robust", "rocker", "ruffled", "sailboat", "sawdust", "scallion", "scenic", "scorecard", "Scotland", "seabird", "select", "sentence", "shadow", "shamrock", "showgirl", "skullcap", "skydive", "slingshot", "slowdown", "snapline", "snapshot", "snowcap", "snowslide", "solo", "southward", "soybean", "spaniel", "spearhead", "spellbind", "spheroid", "spigot", "spindle", "spyglass", "stagehand", "stagnate", "stairway", "standard", "stapler", "steamship", "sterling", "stockman", "stopwatch", "stormy", "sugar", "surmount", "suspense", "sweatband", "swelter", "tactics", "talon", "tapeworm", "tempest", "tiger", "tissue", "tonic", "topmost", "tracker", "transit", "trauma", "treadmill", "Trojan", "trouble", "tumor", "tunnel", "tycoon", "uncut", "unearth", "unwind", "uproot", "upset", "upshot", "vapor", "village", "virus", "Vulcan", "waffle", "wallet", "watchword", "wayside", "willow", "woodlark", "Zulu" };
        oddWords = new String[] { "adroitness", "adviser", "aftermath", "aggregate", "alkali", "almighty", "amulet", "amusement", "antenna", "applicant", "Apollo", "armistice", "article", "asteroid", "Atlantic", "atmosphere", "autopsy", "Babylon", "backwater", "barbecue", "belowground", "bifocals", "bodyguard", "bookseller", "borderline", "bottomless", "Bradbury", "bravado", "Brazilian", "breakaway", "Burlington", "businessman", "butterfat", "Camelot", "candidate", "cannonball", "Capricorn", "caravan", "caretaker", "celebrate", "cellulose", "certify", "chambermaid", "Cherokee", "Chicago", "clergyman", "coherence", "combustion", "commando", "company", "component", "concurrent", "confidence", "conformist", "congregate", "consensus", "consulting", "corporate", "corrosion", "councilman", "crossover", "crucifix", "cumbersome", "customer", "Dakota", "decadence", "December", "decimal", "designing", "detector", "detergent", "determine", "dictator", "dinosaur", "direction", "disable", "disbelief", "disruptive", "distortion", "document", "embezzle", "enchanting", "enrollment", "enterprise", "equation", "equipment", "escapade", "Eskimo", "everyday", "examine", "existence", "exodus", "fascinate", "filament", "finicky", "forever", "fortitude", "frequency", "gadgetry", "Galveston", "getaway", "glossary", "gossamer", "graduate", "gravity", "guitarist", "hamburger", "Hamilton", "handiwork", "hazardous", "headwaters", "hemisphere", "hesitate", "hideaway", "holiness", "hurricane", "hydraulic", "impartial", "impetus", "inception", "indigo", "inertia", "infancy", "inferno", "informant", "insincere", "insurgent", "integrate", "intention", "inventive", "Istanbul", "Jamaica", "Jupiter", "leprosy", "letterhead", "liberty", "maritime", "matchmaker", "maverick", "Medusa", "megaton", "microscope", "microwave", "midsummer", "millionaire", "miracle", "misnomer", "molasses", "molecule", "Montana", "monument", "mosquito", "narrative", "nebula", "newsletter", "Norwegian", "October", "Ohio", "onlooker", "opulent", "Orlando", "outfielder", "Pacific", "pandemic", "Pandora", "paperweight", "paragon", "paragraph", "paramount", "passenger", "pedigree", "Pegasus", "penetrate", "perceptive", "performance", "pharmacy", "phonetic", "photograph", "pioneer", "pocketful", "politeness", "positive", "potato", "processor", "provincial", "proximate", "puberty", "publisher", "pyramid", "quantity", "racketeer", "rebellion", "recipe", "recover", "repellent", "replica", "reproduce", "resistor", "responsive", "retraction", "retrieval", "retrospect", "revenue", "revival", "revolver", "sandalwood", "sardonic", "Saturday", "savagery", "scavenger", "sensation", "sociable", "souvenir", "specialist", "speculate", "stethoscope", "stupendous", "supportive", "surrender", "suspicious", "sympathy", "tambourine", "telephone", "therapist", "tobacco", "tolerance", "tomorrow", "torpedo", "tradition", "travesty", "trombonist", "truncated", "typewriter", "ultimate", "undaunted", "underfoot", "unicorn", "unify", "universe", "unravel", "upcoming", "vacancy", "vagabond", "vertigo", "Virginia", "visitor", "vocalist", "voyager", "warranty", "Waterloo", "whimsical", "Wichita", "Wilmington", "Wyoming", "yesteryear", "Yucatan" };
        BACKGROUND_FOR_APPLET = new Color(16774399);
        BACKGROUND_FOR_INPUT = new Color(16777215);
        FOREGROUND_FOR_INPUT = new Color(2003199);
        FOREGROUND_FOR_INSTRUCTIONS = new Color(32768);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_EDITABLE_FIELDS = FontFactory.build("Dialog", 1, 17);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
        SPLITTER = Pattern.compile("\\s+");
    }
}
