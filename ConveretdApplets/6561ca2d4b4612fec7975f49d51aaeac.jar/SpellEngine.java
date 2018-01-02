import java.awt.Dialog;
import java.awt.Frame;
import java.util.Enumeration;
import java.io.IOException;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.Container;
import java.util.Hashtable;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class SpellEngine
{
    public static String host;
    public static int port;
    private static Socket spellServerSocket;
    private static DataInputStream in;
    private static DataOutputStream out;
    private static Hashtable wordSequence;
    private static Hashtable validWords;
    private static Hashtable ignoreWords;
    private static Hashtable learnedWords;
    private static Thread th;
    private static Thread thProg;
    private static Container f;
    private static TextArea textArea;
    private static Runnable eh;
    private static Runnable ruProg;
    private static int batchSize;
    private static int wordSequenceNumber;
    public static boolean stopped;
    private static boolean resultsToProcess;
    private static boolean wordsToCheck;
    private static boolean ignoreUpper;
    private static int progxPos;
    private static int progyPos;
    private static int errxPos;
    private static int erryPos;
    private static int result;
    private static String sugg;
    private static String[] suggestions;
    private static WordBundle checkWord;
    private static String wordResult;
    private static char curChar;
    private static boolean wordComplete;
    private static boolean done;
    private static boolean hasLetters;
    private static boolean hasLower;
    private static boolean hasDigits;
    private static int textLength;
    
    public static final void initChecker() {
        SpellEngine.validWords = new Hashtable(SpellEngine.batchSize);
        SpellEngine.wordSequence = new Hashtable(SpellEngine.batchSize);
        SpellEngine.learnedWords = new Hashtable();
        SpellEngine.ignoreWords = new Hashtable();
        final Point point = new Point(0, 0);
        SpellEngine.errxPos = point.x;
        SpellEngine.erryPos = point.y;
        final Point point2 = new Point(0, 0);
        SpellEngine.progxPos = point2.x;
        SpellEngine.progyPos = point2.y;
        SpellEngine.spellServerSocket = null;
    }
    
    public static final void check(final TextArea textArea) {
        SpellEngine.textArea = textArea;
        initContainer();
        SpellEngine.wordSequence.clear();
        SpellEngine.learnedWords.clear();
        SpellEngine.ignoreWords.clear();
        openSocket();
    }
    
    public static final void setErrPos(final Point point) {
        SpellEngine.errxPos = point.x;
        SpellEngine.erryPos = point.y;
    }
    
    public static final void setProgPos(final Point point) {
        SpellEngine.progxPos = point.x;
        SpellEngine.progyPos = point.y;
    }
    
    public static final void ignoreUpperCaseWords(final boolean ignoreUpper) {
        SpellEngine.ignoreUpper = ignoreUpper;
    }
    
    private static final void addFrequentWords() {
        SpellEngine.validWords.put("able", "");
        SpellEngine.validWords.put("about", "");
        SpellEngine.validWords.put("above", "");
        SpellEngine.validWords.put("according", "");
        SpellEngine.validWords.put("across", "");
        SpellEngine.validWords.put("act", "");
        SpellEngine.validWords.put("added", "");
        SpellEngine.validWords.put("after", "");
        SpellEngine.validWords.put("again", "");
        SpellEngine.validWords.put("against", "");
        SpellEngine.validWords.put("age", "");
        SpellEngine.validWords.put("agency", "");
        SpellEngine.validWords.put("ago", "");
        SpellEngine.validWords.put("agreed", "");
        SpellEngine.validWords.put("agreement", "");
        SpellEngine.validWords.put("air", "");
        SpellEngine.validWords.put("all", "");
        SpellEngine.validWords.put("almost", "");
        SpellEngine.validWords.put("alone", "");
        SpellEngine.validWords.put("along", "");
        SpellEngine.validWords.put("already", "");
        SpellEngine.validWords.put("also", "");
        SpellEngine.validWords.put("although", "");
        SpellEngine.validWords.put("always", "");
        SpellEngine.validWords.put("am", "");
        SpellEngine.validWords.put("among", "");
        SpellEngine.validWords.put("an", "");
        SpellEngine.validWords.put("analyst", "");
        SpellEngine.validWords.put("and", "");
        SpellEngine.validWords.put("announced", "");
        SpellEngine.validWords.put("annual", "");
        SpellEngine.validWords.put("another", "");
        SpellEngine.validWords.put("any", "");
        SpellEngine.validWords.put("anything", "");
        SpellEngine.validWords.put("are", "");
        SpellEngine.validWords.put("area", "");
        SpellEngine.validWords.put("around", "");
        SpellEngine.validWords.put("art", "");
        SpellEngine.validWords.put("as", "");
        SpellEngine.validWords.put("asked", "");
        SpellEngine.validWords.put("assets", "");
        SpellEngine.validWords.put("at", "");
        SpellEngine.validWords.put("august", "");
        SpellEngine.validWords.put("average", "");
        SpellEngine.validWords.put("away", "");
        SpellEngine.validWords.put("back", "");
        SpellEngine.validWords.put("bank", "");
        SpellEngine.validWords.put("banks", "");
        SpellEngine.validWords.put("based", "");
        SpellEngine.validWords.put("be", "");
        SpellEngine.validWords.put("became", "");
        SpellEngine.validWords.put("because", "");
        SpellEngine.validWords.put("become", "");
        SpellEngine.validWords.put("been", "");
        SpellEngine.validWords.put("before", "");
        SpellEngine.validWords.put("began", "");
        SpellEngine.validWords.put("behind", "");
        SpellEngine.validWords.put("being", "");
        SpellEngine.validWords.put("best", "");
        SpellEngine.validWords.put("better", "");
        SpellEngine.validWords.put("between", "");
        SpellEngine.validWords.put("bid", "");
        SpellEngine.validWords.put("big", "");
        SpellEngine.validWords.put("bill", "");
        SpellEngine.validWords.put("billion", "");
        SpellEngine.validWords.put("board", "");
        SpellEngine.validWords.put("body", "");
        SpellEngine.validWords.put("bond", "");
        SpellEngine.validWords.put("bonds", "");
        SpellEngine.validWords.put("book", "");
        SpellEngine.validWords.put("both", "");
        SpellEngine.validWords.put("brought", "");
        SpellEngine.validWords.put("business", "");
        SpellEngine.validWords.put("but", "");
        SpellEngine.validWords.put("buy", "");
        SpellEngine.validWords.put("buying", "");
        SpellEngine.validWords.put("by", "");
        SpellEngine.validWords.put("called", "");
        SpellEngine.validWords.put("came", "");
        SpellEngine.validWords.put("camera", "");
        SpellEngine.validWords.put("can", "");
        SpellEngine.validWords.put("capital", "");
        SpellEngine.validWords.put("car", "");
        SpellEngine.validWords.put("case", "");
        SpellEngine.validWords.put("cases", "");
        SpellEngine.validWords.put("cash", "");
        SpellEngine.validWords.put("cent", "");
        SpellEngine.validWords.put("cents", "");
        SpellEngine.validWords.put("century", "");
        SpellEngine.validWords.put("certain", "");
        SpellEngine.validWords.put("certainly", "");
        SpellEngine.validWords.put("chairman", "");
        SpellEngine.validWords.put("change", "");
        SpellEngine.validWords.put("chief", "");
        SpellEngine.validWords.put("child", "");
        SpellEngine.validWords.put("children", "");
        SpellEngine.validWords.put("clear", "");
        SpellEngine.validWords.put("client", "");
        SpellEngine.validWords.put("close", "");
        SpellEngine.validWords.put("closed", "");
        SpellEngine.validWords.put("come", "");
        SpellEngine.validWords.put("comment", "");
        SpellEngine.validWords.put("committee", "");
        SpellEngine.validWords.put("common", "");
        SpellEngine.validWords.put("companies", "");
        SpellEngine.validWords.put("company", "");
        SpellEngine.validWords.put("compared", "");
        SpellEngine.validWords.put("computer", "");
        SpellEngine.validWords.put("concern", "");
        SpellEngine.validWords.put("concerned", "");
        SpellEngine.validWords.put("congress", "");
        SpellEngine.validWords.put("continue", "");
        SpellEngine.validWords.put("contract", "");
        SpellEngine.validWords.put("control", "");
        SpellEngine.validWords.put("corp", "");
        SpellEngine.validWords.put("corporate", "");
        SpellEngine.validWords.put("cost", "");
        SpellEngine.validWords.put("costs", "");
        SpellEngine.validWords.put("could", "");
        SpellEngine.validWords.put("council", "");
        SpellEngine.validWords.put("countries", "");
        SpellEngine.validWords.put("country", "");
        SpellEngine.validWords.put("course", "");
        SpellEngine.validWords.put("court", "");
        SpellEngine.validWords.put("credit", "");
        SpellEngine.validWords.put("current", "");
        SpellEngine.validWords.put("cut", "");
        SpellEngine.validWords.put("day", "");
        SpellEngine.validWords.put("days", "");
        SpellEngine.validWords.put("death", "");
        SpellEngine.validWords.put("debt", "");
        SpellEngine.validWords.put("debug", "");
        SpellEngine.validWords.put("declined", "");
        SpellEngine.validWords.put("demand", "");
        SpellEngine.validWords.put("department", "");
        SpellEngine.validWords.put("development", "");
        SpellEngine.validWords.put("did", "");
        SpellEngine.validWords.put("different", "");
        SpellEngine.validWords.put("difficult", "");
        SpellEngine.validWords.put("director", "");
        SpellEngine.validWords.put("disk", "");
        SpellEngine.validWords.put("diskette", "");
        SpellEngine.validWords.put("do", "");
        SpellEngine.validWords.put("does", "");
        SpellEngine.validWords.put("doing", "");
        SpellEngine.validWords.put("dollar", "");
        SpellEngine.validWords.put("done", "");
        SpellEngine.validWords.put("door", "");
        SpellEngine.validWords.put("doubt", "");
        SpellEngine.validWords.put("dow", "");
        SpellEngine.validWords.put("down", "");
        SpellEngine.validWords.put("dr", "");
        SpellEngine.validWords.put("drive", "");
        SpellEngine.validWords.put("drug", "");
        SpellEngine.validWords.put("due", "");
        SpellEngine.validWords.put("during", "");
        SpellEngine.validWords.put("each", "");
        SpellEngine.validWords.put("earlier", "");
        SpellEngine.validWords.put("early", "");
        SpellEngine.validWords.put("earnings", "");
        SpellEngine.validWords.put("economic", "");
        SpellEngine.validWords.put("economy", "");
        SpellEngine.validWords.put("education", "");
        SpellEngine.validWords.put("effect", "");
        SpellEngine.validWords.put("either", "");
        SpellEngine.validWords.put("employees", "");
        SpellEngine.validWords.put("end", "");
        SpellEngine.validWords.put("ended", "");
        SpellEngine.validWords.put("england", "");
        SpellEngine.validWords.put("english", "");
        SpellEngine.validWords.put("enough", "");
        SpellEngine.validWords.put("european", "");
        SpellEngine.validWords.put("even", "");
        SpellEngine.validWords.put("ever", "");
        SpellEngine.validWords.put("every", "");
        SpellEngine.validWords.put("example", "");
        SpellEngine.validWords.put("except", "");
        SpellEngine.validWords.put("exchange", "");
        SpellEngine.validWords.put("executive", "");
        SpellEngine.validWords.put("expected", "");
        SpellEngine.validWords.put("expects", "");
        SpellEngine.validWords.put("experience", "");
        SpellEngine.validWords.put("eyes", "");
        SpellEngine.validWords.put("face", "");
        SpellEngine.validWords.put("fact", "");
        SpellEngine.validWords.put("family", "");
        SpellEngine.validWords.put("far", "");
        SpellEngine.validWords.put("father", "");
        SpellEngine.validWords.put("federal", "");
        SpellEngine.validWords.put("feet", "");
        SpellEngine.validWords.put("fell", "");
        SpellEngine.validWords.put("felt", "");
        SpellEngine.validWords.put("few", "");
        SpellEngine.validWords.put("field", "");
        SpellEngine.validWords.put("financial", "");
        SpellEngine.validWords.put("find", "");
        SpellEngine.validWords.put("firm", "");
        SpellEngine.validWords.put("firms", "");
        SpellEngine.validWords.put("first", "");
        SpellEngine.validWords.put("fiscal", "");
        SpellEngine.validWords.put("five", "");
        SpellEngine.validWords.put("floppy", "");
        SpellEngine.validWords.put("following", "");
        SpellEngine.validWords.put("food", "");
        SpellEngine.validWords.put("for", "");
        SpellEngine.validWords.put("foreign", "");
        SpellEngine.validWords.put("form", "");
        SpellEngine.validWords.put("former", "");
        SpellEngine.validWords.put("forward", "");
        SpellEngine.validWords.put("found", "");
        SpellEngine.validWords.put("four", "");
        SpellEngine.validWords.put("free", "");
        SpellEngine.validWords.put("french", "");
        SpellEngine.validWords.put("friday", "");
        SpellEngine.validWords.put("from", "");
        SpellEngine.validWords.put("full", "");
        SpellEngine.validWords.put("funds", "");
        SpellEngine.validWords.put("further", "");
        SpellEngine.validWords.put("futures", "");
        SpellEngine.validWords.put("gains", "");
        SpellEngine.validWords.put("gave", "");
        SpellEngine.validWords.put("general", "");
        SpellEngine.validWords.put("get", "");
        SpellEngine.validWords.put("girl", "");
        SpellEngine.validWords.put("give", "");
        SpellEngine.validWords.put("given", "");
        SpellEngine.validWords.put("go", "");
        SpellEngine.validWords.put("going", "");
        SpellEngine.validWords.put("gone", "");
        SpellEngine.validWords.put("good", "");
        SpellEngine.validWords.put("got", "");
        SpellEngine.validWords.put("government", "");
        SpellEngine.validWords.put("great", "");
        SpellEngine.validWords.put("group", "");
        SpellEngine.validWords.put("growth", "");
        SpellEngine.validWords.put("had", "");
        SpellEngine.validWords.put("half", "");
        SpellEngine.validWords.put("hand", "");
        SpellEngine.validWords.put("hands", "");
        SpellEngine.validWords.put("hard", "");
        SpellEngine.validWords.put("has", "");
        SpellEngine.validWords.put("have", "");
        SpellEngine.validWords.put("having", "");
        SpellEngine.validWords.put("he", "");
        SpellEngine.validWords.put("head", "");
        SpellEngine.validWords.put("heard", "");
        SpellEngine.validWords.put("held", "");
        SpellEngine.validWords.put("help", "");
        SpellEngine.validWords.put("her", "");
        SpellEngine.validWords.put("here", "");
        SpellEngine.validWords.put("herself", "");
        SpellEngine.validWords.put("high", "");
        SpellEngine.validWords.put("higher", "");
        SpellEngine.validWords.put("him", "");
        SpellEngine.validWords.put("himself", "");
        SpellEngine.validWords.put("his", "");
        SpellEngine.validWords.put("home", "");
        SpellEngine.validWords.put("hope", "");
        SpellEngine.validWords.put("house", "");
        SpellEngine.validWords.put("how", "");
        SpellEngine.validWords.put("however", "");
        SpellEngine.validWords.put("human", "");
        SpellEngine.validWords.put("idea", "");
        SpellEngine.validWords.put("if", "");
        SpellEngine.validWords.put("important", "");
        SpellEngine.validWords.put("in", "");
        SpellEngine.validWords.put("Inc", "");
        SpellEngine.validWords.put("including", "");
        SpellEngine.validWords.put("income", "");
        SpellEngine.validWords.put("increase", "");
        SpellEngine.validWords.put("increased", "");
        SpellEngine.validWords.put("indeed", "");
        SpellEngine.validWords.put("index", "");
        SpellEngine.validWords.put("industry", "");
        SpellEngine.validWords.put("information", "");
        SpellEngine.validWords.put("insurance", "");
        SpellEngine.validWords.put("interest", "");
        SpellEngine.validWords.put("International", "");
        SpellEngine.validWords.put("internet", "");
        SpellEngine.validWords.put("into", "");
        SpellEngine.validWords.put("intranet", "");
        SpellEngine.validWords.put("investment", "");
        SpellEngine.validWords.put("investors", "");
        SpellEngine.validWords.put("is", "");
        SpellEngine.validWords.put("issue", "");
        SpellEngine.validWords.put("issues", "");
        SpellEngine.validWords.put("it", "");
        SpellEngine.validWords.put("its", "");
        SpellEngine.validWords.put("itself", "");
        SpellEngine.validWords.put("japan", "");
        SpellEngine.validWords.put("japanese", "");
        SpellEngine.validWords.put("java", "");
        SpellEngine.validWords.put("john", "");
        SpellEngine.validWords.put("july", "");
        SpellEngine.validWords.put("june", "");
        SpellEngine.validWords.put("just", "");
        SpellEngine.validWords.put("keep", "");
        SpellEngine.validWords.put("kind", "");
        SpellEngine.validWords.put("knew", "");
        SpellEngine.validWords.put("know", "");
        SpellEngine.validWords.put("knowledge", "");
        SpellEngine.validWords.put("known", "");
        SpellEngine.validWords.put("labour", "");
        SpellEngine.validWords.put("land", "");
        SpellEngine.validWords.put("large", "");
        SpellEngine.validWords.put("largest", "");
        SpellEngine.validWords.put("last", "");
        SpellEngine.validWords.put("late", "");
        SpellEngine.validWords.put("later", "");
        SpellEngine.validWords.put("law", "");
        SpellEngine.validWords.put("least", "");
        SpellEngine.validWords.put("leave", "");
        SpellEngine.validWords.put("left", "");
        SpellEngine.validWords.put("less", "");
        SpellEngine.validWords.put("let", "");
        SpellEngine.validWords.put("level", "");
        SpellEngine.validWords.put("life", "");
        SpellEngine.validWords.put("light", "");
        SpellEngine.validWords.put("like", "");
        SpellEngine.validWords.put("likely", "");
        SpellEngine.validWords.put("line", "");
        SpellEngine.validWords.put("little", "");
        SpellEngine.validWords.put("living", "");
        SpellEngine.validWords.put("loans", "");
        SpellEngine.validWords.put("local", "");
        SpellEngine.validWords.put("London", "");
        SpellEngine.validWords.put("long", "");
        SpellEngine.validWords.put("look", "");
        SpellEngine.validWords.put("looked", "");
        SpellEngine.validWords.put("looking", "");
        SpellEngine.validWords.put("Lord", "");
        SpellEngine.validWords.put("loss", "");
        SpellEngine.validWords.put("love", "");
        SpellEngine.validWords.put("lower", "");
        SpellEngine.validWords.put("ltd", "");
        SpellEngine.validWords.put("made", "");
        SpellEngine.validWords.put("main", "");
        SpellEngine.validWords.put("major", "");
        SpellEngine.validWords.put("make", "");
        SpellEngine.validWords.put("maker", "");
        SpellEngine.validWords.put("making", "");
        SpellEngine.validWords.put("man", "");
        SpellEngine.validWords.put("management", "");
        SpellEngine.validWords.put("many", "");
        SpellEngine.validWords.put("market", "");
        SpellEngine.validWords.put("markets", "");
        SpellEngine.validWords.put("matter", "");
        SpellEngine.validWords.put("may", "");
        SpellEngine.validWords.put("me", "");
        SpellEngine.validWords.put("mean", "");
        SpellEngine.validWords.put("means", "");
        SpellEngine.validWords.put("meeting", "");
        SpellEngine.validWords.put("members", "");
        SpellEngine.validWords.put("men", "");
        SpellEngine.validWords.put("might", "");
        SpellEngine.validWords.put("million", "");
        SpellEngine.validWords.put("mind", "");
        SpellEngine.validWords.put("miss", "");
        SpellEngine.validWords.put("modern", "");
        SpellEngine.validWords.put("moment", "");
        SpellEngine.validWords.put("money", "");
        SpellEngine.validWords.put("monitor", "");
        SpellEngine.validWords.put("month", "");
        SpellEngine.validWords.put("months", "");
        SpellEngine.validWords.put("more", "");
        SpellEngine.validWords.put("morning", "");
        SpellEngine.validWords.put("most", "");
        SpellEngine.validWords.put("mother", "");
        SpellEngine.validWords.put("move", "");
        SpellEngine.validWords.put("mr", "");
        SpellEngine.validWords.put("mrs", "");
        SpellEngine.validWords.put("ms", "");
        SpellEngine.validWords.put("much", "");
        SpellEngine.validWords.put("music", "");
        SpellEngine.validWords.put("must", "");
        SpellEngine.validWords.put("my", "");
        SpellEngine.validWords.put("name", "");
        SpellEngine.validWords.put("named", "");
        SpellEngine.validWords.put("national", "");
        SpellEngine.validWords.put("near", "");
        SpellEngine.validWords.put("necessary", "");
        SpellEngine.validWords.put("need", "");
        SpellEngine.validWords.put("net", "");
        SpellEngine.validWords.put("never", "");
        SpellEngine.validWords.put("new", "");
        SpellEngine.validWords.put("news", "");
        SpellEngine.validWords.put("next", "");
        SpellEngine.validWords.put("night", "");
        SpellEngine.validWords.put("no", "");
        SpellEngine.validWords.put("nor", "");
        SpellEngine.validWords.put("not", "");
        SpellEngine.validWords.put("notes", "");
        SpellEngine.validWords.put("nothing", "");
        SpellEngine.validWords.put("now", "");
        SpellEngine.validWords.put("number", "");
        SpellEngine.validWords.put("oct", "");
        SpellEngine.validWords.put("october", "");
        SpellEngine.validWords.put("of", "");
        SpellEngine.validWords.put("off", "");
        SpellEngine.validWords.put("offer", "");
        SpellEngine.validWords.put("offering", "");
        SpellEngine.validWords.put("office", "");
        SpellEngine.validWords.put("officer", "");
        SpellEngine.validWords.put("official", "");
        SpellEngine.validWords.put("officials", "");
        SpellEngine.validWords.put("often", "");
        SpellEngine.validWords.put("oil", "");
        SpellEngine.validWords.put("old", "");
        SpellEngine.validWords.put("on", "");
        SpellEngine.validWords.put("once", "");
        SpellEngine.validWords.put("one", "");
        SpellEngine.validWords.put("only", "");
        SpellEngine.validWords.put("open", "");
        SpellEngine.validWords.put("operating", "");
        SpellEngine.validWords.put("operations", "");
        SpellEngine.validWords.put("or", "");
        SpellEngine.validWords.put("order", "");
        SpellEngine.validWords.put("other", "");
        SpellEngine.validWords.put("others", "");
        SpellEngine.validWords.put("our", "");
        SpellEngine.validWords.put("out", "");
        SpellEngine.validWords.put("outside", "");
        SpellEngine.validWords.put("over", "");
        SpellEngine.validWords.put("own", "");
        SpellEngine.validWords.put("own", "");
        SpellEngine.validWords.put("part", "");
        SpellEngine.validWords.put("particular", "");
        SpellEngine.validWords.put("particularly", "");
        SpellEngine.validWords.put("party", "");
        SpellEngine.validWords.put("past", "");
        SpellEngine.validWords.put("pay", "");
        SpellEngine.validWords.put("people", "");
        SpellEngine.validWords.put("per", "");
        SpellEngine.validWords.put("perhaps", "");
        SpellEngine.validWords.put("period", "");
        SpellEngine.validWords.put("place", "");
        SpellEngine.validWords.put("plan", "");
        SpellEngine.validWords.put("plans", "");
        SpellEngine.validWords.put("plant", "");
        SpellEngine.validWords.put("point", "");
        SpellEngine.validWords.put("points", "");
        SpellEngine.validWords.put("policy", "");
        SpellEngine.validWords.put("political", "");
        SpellEngine.validWords.put("position", "");
        SpellEngine.validWords.put("possible", "");
        SpellEngine.validWords.put("power", "");
        SpellEngine.validWords.put("present", "");
        SpellEngine.validWords.put("president", "");
        SpellEngine.validWords.put("price", "");
        SpellEngine.validWords.put("prices", "");
        SpellEngine.validWords.put("probably", "");
        SpellEngine.validWords.put("problem", "");
        SpellEngine.validWords.put("problems", "");
        SpellEngine.validWords.put("product", "");
        SpellEngine.validWords.put("production", "");
        SpellEngine.validWords.put("products", "");
        SpellEngine.validWords.put("profit", "");
        SpellEngine.validWords.put("program", "");
        SpellEngine.validWords.put("programming", "");
        SpellEngine.validWords.put("proposed", "");
        SpellEngine.validWords.put("public", "");
        SpellEngine.validWords.put("purchase", "");
        SpellEngine.validWords.put("put", "");
        SpellEngine.validWords.put("quarter", "");
        SpellEngine.validWords.put("question", "");
        SpellEngine.validWords.put("quite", "");
        SpellEngine.validWords.put("rate", "");
        SpellEngine.validWords.put("rates", "");
        SpellEngine.validWords.put("rather", "");
        SpellEngine.validWords.put("read", "");
        SpellEngine.validWords.put("real", "");
        SpellEngine.validWords.put("really", "");
        SpellEngine.validWords.put("reason", "");
        SpellEngine.validWords.put("recent", "");
        SpellEngine.validWords.put("recently", "");
        SpellEngine.validWords.put("record", "");
        SpellEngine.validWords.put("report", "");
        SpellEngine.validWords.put("reported", "");
        SpellEngine.validWords.put("research", "");
        SpellEngine.validWords.put("result", "");
        SpellEngine.validWords.put("results", "");
        SpellEngine.validWords.put("revenue", "");
        SpellEngine.validWords.put("right", "");
        SpellEngine.validWords.put("rise", "");
        SpellEngine.validWords.put("robert", "");
        SpellEngine.validWords.put("room", "");
        SpellEngine.validWords.put("rose", "");
        SpellEngine.validWords.put("round", "");
        SpellEngine.validWords.put("said", "");
        SpellEngine.validWords.put("sale", "");
        SpellEngine.validWords.put("sales", "");
        SpellEngine.validWords.put("same", "");
        SpellEngine.validWords.put("saw", "");
        SpellEngine.validWords.put("say", "");
        SpellEngine.validWords.put("says", "");
        SpellEngine.validWords.put("school", "");
        SpellEngine.validWords.put("second", "");
        SpellEngine.validWords.put("securities", "");
        SpellEngine.validWords.put("see", "");
        SpellEngine.validWords.put("seem", "");
        SpellEngine.validWords.put("seemed", "");
        SpellEngine.validWords.put("seems", "");
        SpellEngine.validWords.put("seen", "");
        SpellEngine.validWords.put("sell", "");
        SpellEngine.validWords.put("selling", "");
        SpellEngine.validWords.put("senior", "");
        SpellEngine.validWords.put("sense", "");
        SpellEngine.validWords.put("sept", "");
        SpellEngine.validWords.put("server", "");
        SpellEngine.validWords.put("service", "");
        SpellEngine.validWords.put("services", "");
        SpellEngine.validWords.put("set", "");
        SpellEngine.validWords.put("several", "");
        SpellEngine.validWords.put("shall", "");
        SpellEngine.validWords.put("share", "");
        SpellEngine.validWords.put("shares", "");
        SpellEngine.validWords.put("she", "");
        SpellEngine.validWords.put("short", "");
        SpellEngine.validWords.put("should", "");
        SpellEngine.validWords.put("show", "");
        SpellEngine.validWords.put("shown", "");
        SpellEngine.validWords.put("side", "");
        SpellEngine.validWords.put("since", "");
        SpellEngine.validWords.put("sir", "");
        SpellEngine.validWords.put("six", "");
        SpellEngine.validWords.put("small", "");
        SpellEngine.validWords.put("so", "");
        SpellEngine.validWords.put("social", "");
        SpellEngine.validWords.put("society", "");
        SpellEngine.validWords.put("sold", "");
        SpellEngine.validWords.put("some", "");
        SpellEngine.validWords.put("something", "");
        SpellEngine.validWords.put("sometimes", "");
        SpellEngine.validWords.put("soon", "");
        SpellEngine.validWords.put("source", "");
        SpellEngine.validWords.put("special", "");
        SpellEngine.validWords.put("spokesman", "");
        SpellEngine.validWords.put("stage", "");
        SpellEngine.validWords.put("stake", "");
        SpellEngine.validWords.put("state", "");
        SpellEngine.validWords.put("still", "");
        SpellEngine.validWords.put("stock", "");
        SpellEngine.validWords.put("stocks", "");
        SpellEngine.validWords.put("story", "");
        SpellEngine.validWords.put("strong", "");
        SpellEngine.validWords.put("such", "");
        SpellEngine.validWords.put("support", "");
        SpellEngine.validWords.put("sure", "");
        SpellEngine.validWords.put("system", "");
        SpellEngine.validWords.put("table", "");
        SpellEngine.validWords.put("take", "");
        SpellEngine.validWords.put("taken", "");
        SpellEngine.validWords.put("takeover", "");
        SpellEngine.validWords.put("taking", "");
        SpellEngine.validWords.put("talk", "");
        SpellEngine.validWords.put("tax", "");
        SpellEngine.validWords.put("tell", "");
        SpellEngine.validWords.put("terms", "");
        SpellEngine.validWords.put("text", "");
        SpellEngine.validWords.put("than", "");
        SpellEngine.validWords.put("that", "");
        SpellEngine.validWords.put("the", "");
        SpellEngine.validWords.put("their", "");
        SpellEngine.validWords.put("them", "");
        SpellEngine.validWords.put("themselves", "");
        SpellEngine.validWords.put("then", "");
        SpellEngine.validWords.put("there", "");
        SpellEngine.validWords.put("therefore", "");
        SpellEngine.validWords.put("these", "");
        SpellEngine.validWords.put("they", "");
        SpellEngine.validWords.put("thing", "");
        SpellEngine.validWords.put("things", "");
        SpellEngine.validWords.put("think", "");
        SpellEngine.validWords.put("third", "");
        SpellEngine.validWords.put("this", "");
        SpellEngine.validWords.put("those", "");
        SpellEngine.validWords.put("though", "");
        SpellEngine.validWords.put("thought", "");
        SpellEngine.validWords.put("three", "");
        SpellEngine.validWords.put("through", "");
        SpellEngine.validWords.put("thus", "");
        SpellEngine.validWords.put("time", "");
        SpellEngine.validWords.put("times", "");
        SpellEngine.validWords.put("to", "");
        SpellEngine.validWords.put("today", "");
        SpellEngine.validWords.put("together", "");
        SpellEngine.validWords.put("told", "");
        SpellEngine.validWords.put("too", "");
        SpellEngine.validWords.put("took", "");
        SpellEngine.validWords.put("top", "");
        SpellEngine.validWords.put("total", "");
        SpellEngine.validWords.put("towards", "");
        SpellEngine.validWords.put("town", "");
        SpellEngine.validWords.put("trade", "");
        SpellEngine.validWords.put("trading", "");
        SpellEngine.validWords.put("true", "");
        SpellEngine.validWords.put("turn", "");
        SpellEngine.validWords.put("turned", "");
        SpellEngine.validWords.put("two", "");
        SpellEngine.validWords.put("type", "");
        SpellEngine.validWords.put("under", "");
        SpellEngine.validWords.put("unit", "");
        SpellEngine.validWords.put("until", "");
        SpellEngine.validWords.put("up", "");
        SpellEngine.validWords.put("upon", "");
        SpellEngine.validWords.put("us", "");
        SpellEngine.validWords.put("use", "");
        SpellEngine.validWords.put("used", "");
        SpellEngine.validWords.put("usually", "");
        SpellEngine.validWords.put("value", "");
        SpellEngine.validWords.put("very", "");
        SpellEngine.validWords.put("vice", "");
        SpellEngine.validWords.put("view", "");
        SpellEngine.validWords.put("voice", "");
        SpellEngine.validWords.put("wall", "");
        SpellEngine.validWords.put("want", "");
        SpellEngine.validWords.put("wanted", "");
        SpellEngine.validWords.put("war", "");
        SpellEngine.validWords.put("was", "");
        SpellEngine.validWords.put("washington", "");
        SpellEngine.validWords.put("water", "");
        SpellEngine.validWords.put("way", "");
        SpellEngine.validWords.put("we", "");
        SpellEngine.validWords.put("week", "");
        SpellEngine.validWords.put("weeks", "");
        SpellEngine.validWords.put("well", "");
        SpellEngine.validWords.put("went", "");
        SpellEngine.validWords.put("were", "");
        SpellEngine.validWords.put("west", "");
        SpellEngine.validWords.put("what", "");
        SpellEngine.validWords.put("when", "");
        SpellEngine.validWords.put("where", "");
        SpellEngine.validWords.put("whether", "");
        SpellEngine.validWords.put("which", "");
        SpellEngine.validWords.put("while", "");
        SpellEngine.validWords.put("white", "");
        SpellEngine.validWords.put("who", "");
        SpellEngine.validWords.put("whole", "");
        SpellEngine.validWords.put("whom", "");
        SpellEngine.validWords.put("whose", "");
        SpellEngine.validWords.put("why", "");
        SpellEngine.validWords.put("wife", "");
        SpellEngine.validWords.put("will", "");
        SpellEngine.validWords.put("with", "");
        SpellEngine.validWords.put("within", "");
        SpellEngine.validWords.put("without", "");
        SpellEngine.validWords.put("without", "");
        SpellEngine.validWords.put("woman", "");
        SpellEngine.validWords.put("word", "");
        SpellEngine.validWords.put("words", "");
        SpellEngine.validWords.put("work", "");
        SpellEngine.validWords.put("working", "");
        SpellEngine.validWords.put("world", "");
        SpellEngine.validWords.put("would", "");
        SpellEngine.validWords.put("year", "");
        SpellEngine.validWords.put("years", "");
        SpellEngine.validWords.put("yen", "");
        SpellEngine.validWords.put("yes", "");
        SpellEngine.validWords.put("yesterday", "");
        SpellEngine.validWords.put("yet", "");
        SpellEngine.validWords.put("yield", "");
        SpellEngine.validWords.put("york", "");
        SpellEngine.validWords.put("you", "");
        SpellEngine.validWords.put("young", "");
        SpellEngine.validWords.put("your", "");
        SpellEngine.validWords.put("yours", "");
    }
    
    public static final void addLearned(final String s) {
        SpellEngine.learnedWords.put(s, s);
    }
    
    public static final void addIgnore(final String s) {
        SpellEngine.ignoreWords.put(s, "");
    }
    
    public static final void setBatchSize(final int batchSize) {
        SpellEngine.batchSize = batchSize;
    }
    
    public static final void setPort(final int port) {
        SpellEngine.port = port;
    }
    
    public static final void setHost(final String host) {
        SpellEngine.host = host;
    }
    
    private static final void cleanEntry(final TextInfo textInfo) {
        SpellEngine.wordsToCheck = true;
        SpellEngine.suggestions = new String[10];
        cleanCheck(textInfo);
    }
    
    private static final WordBundle getNextWord(final TextInfo textInfo) {
        SpellEngine.wordResult = "";
        SpellEngine.wordComplete = false;
        SpellEngine.done = false;
        SpellEngine.hasLetters = false;
        SpellEngine.hasDigits = false;
        SpellEngine.hasLower = false;
        final WordBundle wordBundle = new WordBundle("", 0);
        while (!SpellEngine.done) {
            final int curPos = textInfo.curPos;
            while (!SpellEngine.wordComplete) {
                if (textInfo.curPos < SpellEngine.textLength) {
                    SpellEngine.curChar = textInfo.sbText.charAt(textInfo.curPos);
                    if (SpellEngine.curChar >= 'a' && SpellEngine.curChar <= 'z') {
                        SpellEngine.wordResult = String.valueOf(SpellEngine.wordResult) + SpellEngine.curChar;
                        SpellEngine.hasLetters = true;
                        SpellEngine.hasLower = true;
                    }
                    else if (SpellEngine.curChar >= 'A' && SpellEngine.curChar <= 'Z') {
                        SpellEngine.wordResult = String.valueOf(SpellEngine.wordResult) + SpellEngine.curChar;
                        SpellEngine.hasLetters = true;
                    }
                    else if (SpellEngine.curChar >= '0' && SpellEngine.curChar <= '9') {
                        SpellEngine.wordResult = String.valueOf(SpellEngine.wordResult) + SpellEngine.curChar;
                        SpellEngine.hasDigits = true;
                    }
                    else {
                        SpellEngine.wordComplete = true;
                    }
                    ++textInfo.curPos;
                }
                else {
                    SpellEngine.wordComplete = true;
                    SpellEngine.wordsToCheck = false;
                    SpellEngine.done = true;
                }
            }
            if (SpellEngine.hasLetters && !SpellEngine.hasDigits && (!SpellEngine.ignoreUpper || SpellEngine.hasLower)) {
                wordBundle.word = SpellEngine.wordResult;
                wordBundle.position = curPos;
                SpellEngine.done = true;
            }
            else {
                SpellEngine.wordResult = "";
                SpellEngine.wordComplete = false;
            }
            SpellEngine.hasLetters = false;
            SpellEngine.hasDigits = false;
            SpellEngine.hasLower = false;
        }
        return wordBundle;
    }
    
    public static final void replaceSingle(final TextInfo textInfo, final String s, final String s2) {
        final StringBuffer sbText = new StringBuffer(textInfo.sbText.toString().substring(0, textInfo.curPos));
        sbText.append(s2);
        sbText.append(textInfo.sbText.toString().substring(textInfo.curPos + s.length()));
        textInfo.sbText = sbText;
        SpellEngine.textLength = textInfo.sbText.length();
        SpellEngine.textArea.setText(textInfo.sbText.toString());
        SpellEngine.textArea.select(textInfo.curPos, textInfo.curPos);
    }
    
    public static final void replaceAll(final TextInfo textInfo, final String s, final String s2) {
        int curPos = textInfo.curPos;
        final StringBuffer sbText = new StringBuffer(textInfo.sbText.toString().substring(0, curPos));
        sbText.append(s2);
        for (int i = textInfo.sbText.toString().indexOf(s, curPos + 1); i > 0; i = textInfo.sbText.toString().indexOf(s, curPos + 1)) {
            sbText.append(textInfo.sbText.toString().substring(curPos + s.length(), i));
            sbText.append(s2);
            curPos = i;
        }
        sbText.append(textInfo.sbText.toString().substring(curPos + s.length()));
        textInfo.sbText = sbText;
        SpellEngine.textLength = textInfo.sbText.length();
        SpellEngine.textArea.setText(textInfo.sbText.toString());
        SpellEngine.textArea.select(textInfo.curPos, textInfo.curPos);
    }
    
    public static final void resetChecker() {
        SpellEngine.wordSequenceNumber = 0;
        SpellEngine.wordSequence.clear();
        try {
            if (SpellEngine.resultsToProcess) {
                while (true) {
                    SpellEngine.result = SpellEngine.in.readInt();
                    if (SpellEngine.result == -1) {
                        break;
                    }
                    for (boolean b = false; !b; b = true) {
                        SpellEngine.sugg = SpellEngine.in.readUTF();
                        if (SpellEngine.sugg.equals(":")) {}
                    }
                }
            }
        }
        catch (IOException ex) {
            showErrorDialog("Warning", "Communication Error: " + ex);
        }
        SpellEngine.resultsToProcess = false;
        SpellEngine.wordsToCheck = true;
    }
    
    private static final void cleanCheck(final TextInfo textInfo) {
        while (SpellEngine.wordsToCheck || SpellEngine.resultsToProcess) {
            while (SpellEngine.resultsToProcess) {
                try {
                    SpellEngine.result = SpellEngine.in.readInt();
                    if (SpellEngine.result == -1) {
                        SpellEngine.resultsToProcess = false;
                    }
                    else {
                        boolean b = false;
                        int n = 0;
                        for (int i = 0; i < SpellEngine.suggestions.length; ++i) {
                            SpellEngine.suggestions[i] = null;
                        }
                        while (!b) {
                            SpellEngine.sugg = SpellEngine.in.readUTF();
                            if (!SpellEngine.sugg.equals(":")) {
                                SpellEngine.suggestions[n] = SpellEngine.sugg;
                                ++n;
                            }
                            else {
                                b = true;
                            }
                        }
                        final Integer n2 = new Integer(SpellEngine.result);
                        final WordBundle wordBundle = SpellEngine.wordSequence.get(n2);
                        if (!SpellEngine.ignoreWords.containsKey(wordBundle.word) && !SpellEngine.stopped && !SpellEngine.learnedWords.containsKey(wordBundle.word)) {
                            SpellEngine.eh = new ErrorHandler((sendFrame)SpellEngine.f, SpellEngine.textArea, wordBundle, SpellEngine.suggestions, textInfo, SpellEngine.errxPos, SpellEngine.erryPos);
                            SpellEngine.wordSequence.remove(n2);
                            (SpellEngine.th = new Thread(SpellEngine.eh)).start();
                            return;
                        }
                    }
                }
                catch (IOException ex) {
                    showErrorDialog("Warning", "Communication Error: " + ex);
                    return;
                }
                if (!SpellEngine.resultsToProcess) {
                    final Enumeration<WordBundle> elements = (Enumeration<WordBundle>)SpellEngine.wordSequence.elements();
                    while (elements.hasMoreElements()) {
                        SpellEngine.validWords.put(elements.nextElement().word, "");
                    }
                    SpellEngine.wordSequence.clear();
                    SpellEngine.wordSequenceNumber = 0;
                }
            }
            while (SpellEngine.wordsToCheck && !SpellEngine.resultsToProcess && !SpellEngine.stopped) {
                SpellEngine.checkWord = getNextWord(textInfo);
                if (!SpellEngine.checkWord.word.equals("") && !SpellEngine.validWords.containsKey(SpellEngine.checkWord.word) && !SpellEngine.ignoreWords.containsKey(SpellEngine.checkWord.word) && !SpellEngine.learnedWords.containsKey(SpellEngine.checkWord.word)) {
                    SpellEngine.wordSequence.put(new Integer(SpellEngine.wordSequenceNumber), SpellEngine.checkWord);
                    ++SpellEngine.wordSequenceNumber;
                    if (SpellEngine.batchSize != SpellEngine.wordSequenceNumber) {
                        continue;
                    }
                    ((ProgBarFrame)SpellEngine.ruProg).setProgress((int)(textInfo.curPos * 100.0 / textInfo.sbText.length()));
                    processBatch();
                    SpellEngine.resultsToProcess = true;
                }
            }
            if (!SpellEngine.wordsToCheck || SpellEngine.stopped) {
                if (SpellEngine.wordSequenceNumber > 0 && !SpellEngine.stopped) {
                    processBatch();
                    SpellEngine.resultsToProcess = true;
                }
                else {
                    ((ProgBarFrame)SpellEngine.ruProg).setProgress(100);
                    try {
                        final Enumeration<String> elements2 = (Enumeration<String>)SpellEngine.learnedWords.elements();
                        while (elements2.hasMoreElements()) {
                            SpellEngine.out.writeByte(33);
                            SpellEngine.out.writeUTF(elements2.nextElement());
                        }
                        SpellEngine.out.writeByte(58);
                    }
                    catch (IOException ex2) {
                        showErrorDialog("Warning", "Communication Error Closing Connection: " + ex2);
                    }
                    if (!SpellEngine.stopped) {
                        showErrorDialog("Notice", "Spelling Check Complete");
                    }
                    else {
                        showErrorDialog("Notice", "Spell Check Aborted");
                    }
                }
            }
        }
    }
    
    private static final void showErrorDialog(final String s, final String s2) {
        new Thread(new ErrDialog(s, s2, (Frame)SpellEngine.f)).start();
        SpellEngine.stopped = true;
        SpellEngine.wordsToCheck = false;
        if (SpellEngine.thProg != null) {
            SpellEngine.thProg.stop();
            SpellEngine.thProg = null;
            ((ProgBarFrame)SpellEngine.ruProg).dispose();
        }
        SpellEngine.wordSequence.clear();
        SpellEngine.spellServerSocket = null;
    }
    
    private static final void processBatch() {
        try {
            for (int n = 0; n < SpellEngine.batchSize && n < SpellEngine.wordSequenceNumber; ++n) {
                SpellEngine.out.writeByte(124);
                SpellEngine.out.writeUTF(((WordBundle)SpellEngine.wordSequence.get(new Integer(n))).word);
                SpellEngine.out.writeInt(n);
            }
            SpellEngine.out.writeByte(64);
            SpellEngine.out.flush();
        }
        catch (IOException ex) {
            showErrorDialog("Warning", "Communication Error: " + ex);
        }
    }
    
    public static final void resumeChecking(final TextInfo textInfo) {
        ((ErrorHandler)SpellEngine.eh).dispose();
        SpellEngine.f.requestFocus();
        try {
            SpellEngine.th.stop();
            SpellEngine.th = null;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Trying to Stop Thread: " + ex);
        }
        try {
            cleanCheck(textInfo);
        }
        catch (Exception ex2) {
            showErrorDialog("Warning", "ERROR: " + ex2);
        }
    }
    
    private static final void initContainer() {
        SpellEngine.f = SpellEngine.textArea.getParent();
        while (SpellEngine.f != null && !(SpellEngine.f instanceof sendFrame)) {
            SpellEngine.f = SpellEngine.f.getParent();
        }
        if (SpellEngine.f == null) {
            System.out.println("No Frame Container parent could be found.");
        }
    }
    
    private static final void openSocket() {
        if (SpellEngine.spellServerSocket == null) {
            try {
                SpellEngine.spellServerSocket = new Socket(SpellEngine.host, SpellEngine.port);
                SpellEngine.in = new DataInputStream(SpellEngine.spellServerSocket.getInputStream());
                SpellEngine.out = new DataOutputStream(SpellEngine.spellServerSocket.getOutputStream());
                SpellEngine.resultsToProcess = false;
                SpellEngine.stopped = false;
                SpellEngine.ignoreUpper = true;
                addFrequentWords();
                final TextInfo textInfo = new TextInfo();
                textInfo.sbText = new StringBuffer(SpellEngine.textArea.getText());
                SpellEngine.textLength = textInfo.sbText.length();
                textInfo.curPos = 0;
                SpellEngine.ruProg = new ProgBarFrame((Frame)SpellEngine.f, "Checking", 100, SpellEngine.progxPos, SpellEngine.progyPos);
                (SpellEngine.thProg = new Thread(SpellEngine.ruProg)).start();
                SpellEngine.wordsToCheck = true;
                SpellEngine.suggestions = new String[10];
                cleanCheck(textInfo);
            }
            catch (Exception ex) {
                showErrorDialog("Warning", "Spelling Server is down.  Please Try Again Later");
            }
        }
    }
    
    public static final void killErrorDialog(final Dialog dialog) {
        dialog.dispose();
        SpellEngine.f.requestFocus();
    }
    
    static {
        SpellEngine.spellServerSocket = null;
        SpellEngine.th = null;
        SpellEngine.batchSize = 50;
    }
}
