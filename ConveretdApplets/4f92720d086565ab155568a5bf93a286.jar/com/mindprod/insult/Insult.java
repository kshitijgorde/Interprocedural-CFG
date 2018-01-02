// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.insult;

import com.mindprod.common11.Build;
import com.mindprod.common11.Hybrid;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.mindprod.common11.FontFactory;
import com.mindprod.common11.CMPAboutBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Button;
import java.util.Random;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

public final class Insult extends Applet
{
    private static final int APPLET_HEIGHT = 152;
    private static final int APPLET_WIDTH = 818;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2004-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2008-04-02";
    private static final String TITLE_STRING = "CMP Insult Generator";
    private static final String VERSION_STRING = "1.9";
    private static final String[] braggingVerbs;
    private static final String[] rudeAdjectives;
    private static final String[] rudeNouns;
    private static final String[] rudePhrases;
    private static final String[] rudeScapegoats;
    private static final Color BACKGROUND_FOR_BODY;
    private static final Color BACKGROUND_FOR_FIELD;
    private static final Color FOREGROUND_FOR_BUTTON;
    private static final Color FOREGROUND_FOR_TEXT;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private static final Random wheel;
    private static int prevAdj;
    private static int prevBraggingVerb;
    private static int prevNoun;
    private static int prevPhrase;
    private static int prevScapegoat;
    private static int prevType;
    private Button about;
    private Button insultButton;
    private Label title;
    private Label title2;
    private TextField insultText;
    
    public void destroy() {
        this.about = null;
        this.insultButton = null;
        this.insultText = null;
        this.title = null;
        this.title2 = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 1, 0, this)) {
            return;
        }
        this.setBackground(Insult.BACKGROUND_FOR_BODY);
        this.setLayout(new GridBagLayout());
        this.buildComponents();
        this.layoutComponents();
        this.validate();
    }
    
    private static String generateInsult() {
        final int types = 8;
        int type;
        do {
            type = (Insult.wheel.nextInt() & Integer.MAX_VALUE) % 8;
        } while (type == Insult.prevType);
        switch (Insult.prevType = type) {
            case 0: {
                final String adj = randomAdjective();
                final boolean vowel = "aeiou".indexOf(adj.charAt(0)) >= 0;
                return "You're " + (vowel ? "an" : "a") + " " + adj + " " + randomNoun() + ".";
            }
            case 1: {
                return "Your a " + randomAdjective() + " " + randomNoun() + ".";
            }
            case 2: {
                return "You " + randomNoun() + "!";
            }
            case 3: {
                return "We will " + randomBraggingVerb() + " you!";
            }
            case 4: {
                return randomPhrase();
            }
            case 5: {
                return "Blame it on " + randomScapegoat() + "!";
            }
            case 6: {
                final String verb = randomBraggingVerb();
                return Character.toUpperCase(verb.charAt(0)) + verb.substring(1) + " " + randomScapegoat() + "!";
            }
            case 7: {
                final String scapegoat = randomScapegoat();
                return Character.toUpperCase(scapegoat.charAt(0)) + scapegoat.substring(1) + " hate America!";
            }
            default: {
                return "I can't think of anything to say.";
            }
        }
    }
    
    private static String randomAdjective() {
        int adj;
        do {
            adj = (Insult.wheel.nextInt() & Integer.MAX_VALUE) % Insult.rudeAdjectives.length;
        } while (adj == Insult.prevAdj);
        Insult.prevAdj = adj;
        return Insult.rudeAdjectives[adj];
    }
    
    private static String randomBraggingVerb() {
        int braggingVerb;
        do {
            braggingVerb = (Insult.wheel.nextInt() & Integer.MAX_VALUE) % Insult.braggingVerbs.length;
        } while (braggingVerb == Insult.prevBraggingVerb);
        Insult.prevBraggingVerb = braggingVerb;
        return Insult.braggingVerbs[braggingVerb];
    }
    
    private static String randomNoun() {
        int noun;
        do {
            noun = (Insult.wheel.nextInt() & Integer.MAX_VALUE) % Insult.rudeNouns.length;
        } while (noun == Insult.prevNoun);
        Insult.prevNoun = noun;
        return Insult.rudeNouns[noun];
    }
    
    private static String randomPhrase() {
        int phrase;
        do {
            phrase = (Insult.wheel.nextInt() & Integer.MAX_VALUE) % Insult.rudePhrases.length;
        } while (phrase == Insult.prevPhrase);
        Insult.prevPhrase = phrase;
        return Insult.rudePhrases[phrase];
    }
    
    private static String randomScapegoat() {
        int scapegoat;
        do {
            scapegoat = (Insult.wheel.nextInt() & Integer.MAX_VALUE) % Insult.rudeScapegoats.length;
        } while (scapegoat == Insult.prevScapegoat);
        Insult.prevScapegoat = scapegoat;
        return Insult.rudeScapegoats[scapegoat];
    }
    
    private void buildComponents() {
        (this.title = new Label("CMP Insult Generator 1.9")).setFont(Insult.FONT_FOR_TITLE);
        this.title.setForeground(Insult.FOREGROUND_FOR_TITLE);
        (this.title2 = new Label("released:2008-04-02 build:9411")).setFont(Insult.FONT_FOR_TITLE2);
        this.title2.setForeground(Insult.FOREGROUND_FOR_TITLE);
        (this.about = new Button("about")).setForeground(Color.white);
        this.about.setForeground(Insult.FOREGROUND_FOR_BUTTON);
        this.about.setFont(Insult.FONT_FOR_TITLE);
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutBox("CMP Insult Generator", "1.9", "Generates insults similar to those", "posted by Republicans in the alt.politics.bush newsgroup.", "freeware", "2008-04-02", 2005, "Roedy Green", "INSULT", "1.1");
            }
        });
        (this.insultButton = new Button("Insult Me!")).setFont(FontFactory.build("Dialog", 1, 16));
        this.insultButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Insult.this.insultText.setText(generateInsult());
            }
        });
        (this.insultText = new TextField(80)).setEditable(false);
        this.insultText.setFont(FontFactory.build("Dialog", 0, 15));
        this.insultText.setForeground(Insult.FOREGROUND_FOR_TEXT);
        this.insultText.setBackground(Insult.BACKGROUND_FOR_FIELD);
        this.insultText.setText(generateInsult());
    }
    
    private void layoutComponents() {
        this.add(this.title, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 5, 5), 0, 0));
        this.add(this.title2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(10, 5, 5, 5), 0, 0));
        this.add(this.about, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, 13, 0, new Insets(10, 5, 5, 10), 10, 3));
        this.add(this.insultText, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0, 10, 1, new Insets(5, 10, 5, 10), 0, 0));
        this.add(this.insultButton, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 10, 10), 3, 3));
    }
    
    public static void main(final String[] args) {
        Hybrid.fireup(new Insult(), "CMP Insult Generator 1.9", 818, 152);
    }
    
    static {
        braggingVerbs = new String[] { "abolish", "ban", "bash", "beat", "blast", "bomb", "bury", "clobber", "demolish", "drive SUVs over", "flatten", "glassify", "kill", "napalm", "nuke", "outlaw", "overwhelm", "screw", "shoot", "silence", "steam-roller", "wipe" };
        rudeAdjectives = new String[] { "AIDS-infested", "America-hating", "Arab", "ass-pounding", "bleeding heart", "boy-fucking", "camel-fucking", "camel-humping", "camel-raping", "cocksucking", "bullshitting", "Bush-hating", "butt-banging", "Chomsky-loving", "Christ-hating", "clueless", "communist", "cornholing", "crazy", "crying", "damned", "DemocRAT", "Democrat", "disgusting", "disobedient", "disrespectful", "fat", "fat-assed", "feel-good", "flag-hating", "flag-burning", "flaming", "freedom-hating", "French", "Hitler-loving", "holocaust-denying", "homo", "illegal", "impractical", "insane", "Jesus-hating", "koran-owning", "left-wing", "limp-wristed", "lying", "mendacious", "moonbat", "moronic", "queer", "rebellious", "Saddam-loving", "sick", "skinny-assed", "socialist", "spamming", "stupid", "teflon", "tin hat", "unemployed", "whining", "wimpoid" };
        rudeNouns = new String[] { "alien", "America-hater", "animal", "Arab", "assclown", "asshole", "atheist", "bastard", "black", "bullshitter", "bum biter", "Bush-hater", "Canadian", "camel jockey", "camel humper", "camel kisser", "Canuck", "Canukistani", "cheesehead", "cheese-eater", "child molester", "chink", "Chomskyite", "Clinton-lover", "Clintonite", "cock sucker", "commie", "communist", "conspiracy theorist", "crackpot", "creep", "cum-drinker", "dog-fucker", "environmentalist", "European", "faggot", "fart", "fruitcake", "gay boy", "girlie-man", "hate monger", "homo", "idiot", "Jew", "Jew-boy", "ketchup eater", "koran reader", "leftie", "liar", "Liberal", "LIEberal", "loon", "loser", "luser", "maggot", "maroon", "Mexican", "Mooseslime", "moron", "Muslim", "newbie", "nigger", "nutbar", "pedophile", "person with AIDS", "pervert", "piece of trash", "pinko", "pond sucker", "queer", "raghead", "retard", "Saddam-lover", "Saddamocrat", "sand monkey", "sand nigger", "scumbag", "SOB", "socialist", "sock puppet", "spammer", "terrorist", "trailer park scum", "traitor", "tree-hugger", "vulture sock puppet", "wannabe", "welfare bum", "wetback", "whacko", "wing nut", "woman" };
        rudePhrases = new String[] { "Another stupid Retard liberal exposes it's amoeba brain to the world.", "<All lies snipped, leaving nothing>", "<bullshit snipped>", "All terrorists are Muslims.", "Bush created more jobs that any other president.", "Bush does what he does out of his love for Jesus.", "Bush is the greatest president of all time, militarily, economically and preserving freedoms.", "Bush won! Trust me, Bush won.", "BWAHAHAHAHAHAHAHAHAHA!", "BY DEFINITION YOU ARE A LIAR!!", "By definition, Democrats never tell the truth.", "By definition, I am right and you are wrong.", "Clinton did it.", "Doing that would utterly destroy the economy.", "ELECTION NEWS FLASH! LIBERALS ARE MORONS!", "Every book you recommend must be a lie, since everything you say is a lie.", "Every child killed in Iraq was an totally unavoidable accident. How dare you insinuate otherwise!", "Everything I say just goes right over your head. You don't know nothing!", "Four more years. Get used to it!", "Fuck you!", "George Bush has never told a lie in his life. You're a traiter to suggest otherwise.", "Get over it!", "Gore is boring!!!", "Ha ha, another liberal suffers and dies!", "I don't care if Bush confessed to using coke and being an alcoholic. I know in my heart he never touched drugs.", "I don't care if the white house website said so. They are just trying to make Bush look bad.", "I don't give a shit.", "I don't have to read that. I know already what it will say.", "I don't need to look at your so-called evidence.", "I don't read books. Books contain nothing but lies. They are written by liberals.", "I DON'T TRUST YOU.", "I hope you die.", "I picture you as a child in the fetal position sucking your thumb craving for your mothers breast milk.", "I would suggest that you seek counseling and drink a pint of your mothers warm milk.", "It is a well known fact that everything you have ever said is a lie.", "Kerry eats ketchup.", "LIEberals don't know anythang.", "LMAO", "Nya nya nya. I'm a Republican and your not.", "On the economy, Greenspan is a no-nothing compared with Bush.", "One more time! American soldiers don't kill people, you liar!", "Paul O'Neill. What does he know about the economy?", "Quack, quack, quack.", "Rush hasn't been convicted of drug dealing.", "SCREW YOU!", "Sheesh!", "Take your meds.", "The bible says different. That settles it once and for all.", "There is no doubt at all. Bush won fair and square.", "There is no proof Bush paid Gannon for sex.", "Those sources are all lies.  I know in my heart what is true.", "Those sources are invalid because the contradict Bush.", "We won the election, losers, so get used to it", "What Bill Gates says doesn't count. He's a socialist.", "What Bill O'Reilly says doesn't count. He's a liberal.", "What FOX says is a lie. It's is part of the liberal media conspiracy.", "What have you been smoking?", "What the Pentagon says doesn't count. They are crawling with liberals.", "Yes but, Clinton lied under oath.", "Yes but, Clinton's penis!", "Yes but, Dean screamed.", "Yes but, Robert Byrd belonged to the KKK in the 1950s.", "Yes but, Ted Kennedy is a murderer.", "You are full of crap", "You are full of shit.", "You are short on facts.", "You are totally ignorant.", "You are trying to destroy the sanity of marriage and cause more AIDS.", "You are wrong. Q.E.D.", "You can't bring yourself to know that you are wrong.", "You can't prove Bush did it.", "You can't trust Richard Clarke. He's disgruntled cuz Bush fired him.", "You don't know anything this.", "You just pretend to be so smart with your perfect grammar and spelling.", "You just want to destroy the economy.", "You lost the election.", "You make me sick.", "You put viruses in my computer. I'm telling the police.", "You shount post when you've been drinking.", "You snipped some of my post. How dare you put words in my mouth that way!", "You think EVERYTHING is America's fault, even global warming.", "You want to turn America into a socialist pig hole.", "Your an idiot.", "Your illness is God's punishment for not doing as I say.", "Your lying." };
        rudeScapegoats = new String[] { "AIDS carriers", "Arabs", "atheists", "Bill Clinton", "Bill", "Billary", "blacks", "baby-killer", "Bush-haters", "Canadians", "Canucks", "Canukistanis", "chinks", "Clinton", "cock suckers", "communists", "conspiracy theorists", "DemocRATS", "Edwards", "environmentalists", "Europeans", "faggots", "Gooks", "Gore", "Hillary", "homos", "illegal aliens", "Indians", "Jews", "lefties", "Liberals", "LIEberals", "Mexicans", "Mooseslimes", "Muslims", "niggers", "Noam Chomsky", "Obama", "Pakis", "queers", "Robert Byrd", "sand monkeys", "sand niggers", "slopes", "socialists", "terrorists", "the Clintons", "the Democrats", "the French", "the Klintoons", "the ragheads", "the Russians", "tree-huggers", "welfare bums", "women" };
        BACKGROUND_FOR_BODY = Build.BACKGROUND_FOR_BLENDING;
        BACKGROUND_FOR_FIELD = Color.white;
        FOREGROUND_FOR_BUTTON = new Color(32768);
        FOREGROUND_FOR_TEXT = Color.black;
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
        wheel = new Random();
        Insult.prevAdj = 0;
        Insult.prevBraggingVerb = 0;
        Insult.prevNoun = 0;
        Insult.prevPhrase = 0;
        Insult.prevScapegoat = 0;
        Insult.prevType = 0;
    }
}
