import java.io.InputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DefaultPokerGame implements VideoPokerGame
{
    private String name;
    private Card[] wilds;
    private int numWildsInDeck;
    private PayoffLine[] paylines;
    private int numPaylines;
    public static final int FIVEOFKIND = 9;
    public static final int STRAIGHTFLUSH = 8;
    public static final int FOUROFKIND = 7;
    public static final int FULLHOUSE = 6;
    public static final int FLUSH = 5;
    public static final int STRAIGHT = 4;
    public static final int THREEOFKIND = 3;
    public static final int TWOPAIR = 2;
    public static final int PAIR = 1;
    public static final int NOPAIR = 0;
    private int numWildsInHand;
    private int straightHighCard;
    private int[] numEachRank;
    
    public DefaultPokerGame() {
        this.wilds = new Card[10];
        this.paylines = new PayoffLine[25];
        this.numEachRank = new int[15];
    }
    
    private void setName(final String name) {
        this.name = name;
    }
    
    private void addWild(final Card card) {
        this.wilds[this.numWildsInDeck] = card;
        ++this.numWildsInDeck;
    }
    
    private int getPayline(final int n, final int n2, final int n3) {
        for (int i = 0; i < this.numPaylines; ++i) {
            if (this.paylines[i].testCriteria(n, n2, n3)) {
                return i;
            }
        }
        return -1;
    }
    
    private int getPayline(final String s) {
        for (int i = 0; i < this.numPaylines; ++i) {
            if (this.paylines[i].getDescription().equals(s)) {
                return i;
            }
        }
        this.paylines[this.numPaylines] = new PayoffLine();
        ++this.numPaylines;
        return this.numPaylines - 1;
    }
    
    private void addCriteria(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.paylines[n].addCriteria(n2, n3, n4, n5, n6);
    }
    
    private void setPayoff(final int n, final int n2, final int n3) {
        this.paylines[n].setPayoff(n2, n3);
    }
    
    private void setDescription(final int n, final String description) {
        this.paylines[n].setDescription(description);
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getNumJokers() {
        int n = 0;
        for (int i = 0; i < this.numWildsInDeck; ++i) {
            if (this.wilds[i].isJoker()) {
                ++n;
            }
        }
        return n;
    }
    
    public int getNumPayoffLines() {
        return this.numPaylines;
    }
    
    public String getDescription(final int n) {
        return this.paylines[n].getDescription();
    }
    
    public int getPayoff(final int n, final int n2) {
        if (n >= 0) {
            return this.paylines[n].getPayoff(n2);
        }
        return 0;
    }
    
    public boolean isWild(final Card card) {
        for (int i = 0; i < this.numWildsInDeck; ++i) {
            if (card.equals(this.wilds[i])) {
                return true;
            }
        }
        return false;
    }
    
    public static DefaultPokerGame loadGame(final String s, final Applet applet) {
        DefaultPokerGame loadGameFromInputStream;
        try {
            final URLConnection openConnection = new URL(applet.getCodeBase(), s).openConnection();
            openConnection.connect();
            loadGameFromInputStream = loadGameFromInputStream(openConnection.getInputStream());
        }
        catch (MalformedURLException ex) {
            loadGameFromInputStream = null;
        }
        catch (IOException ex2) {
            loadGameFromInputStream = null;
        }
        return loadGameFromInputStream;
    }
    
    private static DefaultPokerGame loadGameFromInputStream(final InputStream inputStream) {
        DefaultPokerGame defaultPokerGame = new DefaultPokerGame();
        try {
            while (!getNextTableCaption(inputStream).equals("Name")) {}
            if (!findTag(inputStream, "TD", "/TABLE")) {
                throw new IllegalGameFormatException();
            }
            defaultPokerGame.name = getTextUpToTag(inputStream, "/TD");
            String nextTableCaption;
            do {
                nextTableCaption = getNextTableCaption(inputStream);
            } while (!nextTableCaption.equals("Wild") && !nextTableCaption.equals("Payoffs"));
            if (nextTableCaption.equals("Wild")) {
                while (findTag(inputStream, "TD", "/TABLE")) {
                    try {
                        defaultPokerGame.wilds[defaultPokerGame.numWildsInDeck] = new Card(Integer.parseInt(getTextUpToTag(inputStream, "/TD")));
                        final DefaultPokerGame defaultPokerGame2 = defaultPokerGame;
                        ++defaultPokerGame2.numWildsInDeck;
                    }
                    catch (NumberFormatException ex) {}
                }
                while (!getNextTableCaption(inputStream).equals("Payoffs")) {}
            }
            if (!findTag(inputStream, "/TR", "/TABLE")) {
                throw new IllegalGameFormatException();
            }
            while (findTag(inputStream, "TD", "/TABLE")) {
                final String textUpToTag = getTextUpToTag(inputStream, "/TD");
                final int payline = defaultPokerGame.getPayline(textUpToTag);
                defaultPokerGame.paylines[payline].setDescription(textUpToTag);
                defaultPokerGame.paylines[payline].addCriteria(getNextIntInTable(inputStream), getNextIntInTable(inputStream), getNextIntInTable(inputStream), getNextIntInTable(inputStream), getNextIntInTable(inputStream));
                for (int i = 1; i <= 5; ++i) {
                    defaultPokerGame.paylines[payline].setPayoff(i, getNextIntInTable(inputStream));
                }
            }
        }
        catch (IllegalGameFormatException ex2) {
            defaultPokerGame = null;
        }
        return defaultPokerGame;
    }
    
    private static boolean findTag(final InputStream inputStream, String upperCase, String upperCase2) throws IllegalGameFormatException {
        upperCase = upperCase.toUpperCase();
        upperCase2 = upperCase2.toUpperCase();
        while (getStringUpToSymbol(inputStream, '<') != null) {
            final String stringUpToSymbol = getStringUpToSymbol(inputStream, '>');
            if (stringUpToSymbol.startsWith(upperCase)) {
                return true;
            }
            if (stringUpToSymbol.startsWith(upperCase2)) {
                return false;
            }
        }
        throw new IllegalGameFormatException();
    }
    
    private static String getStringUpToSymbol(final InputStream inputStream, final char c) {
        String string = "";
        try {
            int read;
            while ((read = inputStream.read()) != -1) {
                final char c2 = (char)read;
                if (c2 == c) {
                    return string.trim();
                }
                string = String.valueOf(string) + c2;
            }
        }
        catch (IOException ex) {}
        return null;
    }
    
    private static String getTextUpToTag(final InputStream inputStream, final String s) throws IllegalGameFormatException {
        String string = "";
        String stringUpToSymbol;
        do {
            final String stringUpToSymbol2 = getStringUpToSymbol(inputStream, '<');
            if (stringUpToSymbol2 == null) {
                throw new IllegalGameFormatException();
            }
            string = String.valueOf(string) + stringUpToSymbol2;
            stringUpToSymbol = getStringUpToSymbol(inputStream, '>');
            if (stringUpToSymbol == null) {
                throw new IllegalGameFormatException();
            }
        } while (!stringUpToSymbol.equals(s));
        return string;
    }
    
    private static String getNextTableCaption(final InputStream inputStream) throws IllegalGameFormatException {
        if (!findTag(inputStream, "TABLE", "/BODY")) {
            throw new IllegalGameFormatException();
        }
        if (!findTag(inputStream, "CAPTION", "/TABLE")) {
            return "";
        }
        return getTextUpToTag(inputStream, "/CAPTION");
    }
    
    private static int getNextIntInTable(final InputStream inputStream) throws IllegalGameFormatException {
        final String textUpToTag = getTextUpToTag(inputStream, "/TD");
        try {
            return Integer.parseInt(textUpToTag);
        }
        catch (NumberFormatException ex) {
            throw new IllegalGameFormatException();
        }
    }
    
    public int getPayline(final Card[] array) {
        final Card[] removeWilds = this.removeWilds(array);
        this.countRanks(removeWilds);
        int n;
        int n2;
        if (this.numWildsInHand == 5) {
            n = 9;
            n2 = 14;
        }
        else if (this.numWildsInHand == 4) {
            n = 9;
            n2 = this.getHighCard();
        }
        else if (this.canBeFlush(removeWilds)) {
            if (this.canBeStraight(removeWilds)) {
                n = 8;
                n2 = this.getHighCard();
            }
            else {
                if (this.numWildsInHand == 3) {
                    n = 7;
                }
                else {
                    n = 5;
                }
                if (this.numWildsInHand > 0) {
                    n2 = 14;
                }
                else {
                    n2 = this.getHighCard();
                }
            }
        }
        else if (this.canBeStraight(removeWilds)) {
            if (this.numWildsInHand == 3) {
                n = 7;
            }
            else {
                n = 4;
            }
            n2 = this.getHighCard();
        }
        else if (this.numWildsInHand == 3) {
            if ((n2 = this.getHighPair()) != -1) {
                n = 9;
            }
            else {
                n2 = this.getHighCard();
                n = 7;
            }
        }
        else if (this.numWildsInHand == 2) {
            if ((n2 = this.getTrips()) != -1) {
                n = 9;
            }
            else if ((n2 = this.getHighPair()) != -1) {
                n = 7;
            }
            else {
                n2 = this.getHighCard();
                n = 3;
            }
        }
        else if (this.numWildsInHand == 1) {
            if ((n2 = this.getQuads()) != -1) {
                n = 9;
            }
            else if ((n2 = this.getTrips()) != -1) {
                n = 7;
            }
            else if ((n2 = this.getHighPair()) != -1) {
                if (this.getLowPair() != -1) {
                    n = 6;
                }
                else {
                    n = 3;
                }
            }
            else {
                n2 = this.getHighCard();
                n = 1;
            }
        }
        else if ((n2 = this.getQuads()) != -1) {
            n = 7;
        }
        else if ((n2 = this.getTrips()) != -1) {
            if (this.getHighPair() != -1) {
                n = 6;
            }
            else {
                n = 3;
            }
        }
        else if ((n2 = this.getHighPair()) != -1) {
            if (this.getLowPair() != -1) {
                n = 2;
            }
            else {
                n = 1;
            }
        }
        else {
            n2 = this.getHighCard();
            n = 0;
        }
        return this.getPayline(n, n2, this.numWildsInHand);
    }
    
    private Card[] removeWilds(final Card[] array) {
        this.numWildsInHand = 0;
        final Card[] array2 = new Card[5];
        for (int i = 0; i < 5; ++i) {
            if (this.isWild(array[i])) {
                ++this.numWildsInHand;
                array2[i] = null;
            }
            else {
                array2[i] = array[i];
            }
        }
        return array2;
    }
    
    private boolean canBeFlush(final Card[] array) {
        int suit = -1;
        for (int i = 0; i < 5; ++i) {
            if (array[i] != null) {
                if (suit == -1) {
                    suit = array[i].getSuit();
                }
                else if (suit != array[i].getSuit()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean canBeStraight(final Card[] array) {
        this.straightHighCard = -1;
        final int n = this.getHighRank(array, true) - this.getLowRank(array, true);
        if (this.hasNoPair(array) && n < 5) {
            this.straightHighCard = this.getLowRank(array, true) + 4;
            if (this.straightHighCard > 14) {
                this.straightHighCard = 14;
            }
            return true;
        }
        final int n2 = this.getHighRank(array, false) - this.getLowRank(array, false);
        if (this.hasNoPair(array) && n2 < 5) {
            this.straightHighCard = 5;
            return true;
        }
        return false;
    }
    
    private boolean hasNoPair(final Card[] array) {
        for (int i = 0; i < 4; ++i) {
            for (int j = i + 1; j < 5; ++j) {
                if (array[i] != null && array[j] != null && array[i].getRank() == array[j].getRank()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private int getHighRank(final Card[] array, final boolean b) {
        int n = -1;
        for (int i = 0; i < 5; ++i) {
            if (array[i] != null) {
                int n2;
                if (b) {
                    n2 = array[i].getRankAcesHigh();
                }
                else {
                    n2 = array[i].getRank();
                }
                if (n2 > n) {
                    n = n2;
                }
            }
        }
        return n;
    }
    
    private int getLowRank(final Card[] array, final boolean b) {
        int n = 15;
        for (int i = 0; i < 5; ++i) {
            if (array[i] != null) {
                int n2;
                if (b) {
                    n2 = array[i].getRankAcesHigh();
                }
                else {
                    n2 = array[i].getRank();
                }
                if (n2 < n) {
                    n = n2;
                }
            }
        }
        return n;
    }
    
    private void countRanks(final Card[] array) {
        for (int i = 0; i < 15; ++i) {
            this.numEachRank[i] = 0;
        }
        for (int j = 0; j < 5; ++j) {
            if (array[j] != null) {
                final int[] numEachRank = this.numEachRank;
                final int rankAcesHigh = array[j].getRankAcesHigh();
                ++numEachRank[rankAcesHigh];
            }
        }
    }
    
    private int getQuads() {
        for (int i = 14; i > 1; --i) {
            if (this.numEachRank[i] == 4) {
                return i;
            }
        }
        return -1;
    }
    
    private int getTrips() {
        for (int i = 14; i > 1; --i) {
            if (this.numEachRank[i] == 3) {
                return i;
            }
        }
        return -1;
    }
    
    private int getHighPair() {
        for (int i = 14; i > 1; --i) {
            if (this.numEachRank[i] == 2) {
                return i;
            }
        }
        return -1;
    }
    
    private int getLowPair() {
        int n = 0;
        for (int i = 14; i > 1; --i) {
            if (this.numEachRank[i] == 2 && ++n == 2) {
                return i;
            }
        }
        return -1;
    }
    
    private int getHighCard() {
        if (this.straightHighCard != -1) {
            return this.straightHighCard;
        }
        for (int i = 14; i > 1; --i) {
            if (this.numEachRank[i] == 1) {
                return i;
            }
        }
        return -1;
    }
}
