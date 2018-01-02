import java.util.Random;
import java.io.InputStream;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Component;
import java.awt.Label;
import java.awt.Font;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import gfx.Colors;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class QuoteApplet extends Applet
{
    public String quoteFileName;
    public String defaultQuoteColorStr;
    public String defaultSourceColorStr;
    public String bgColorStr;
    Color bgColor;
    public String flashBrightnessStr;
    float flashBrightness;
    public String titleColorStr;
    Color titleColor;
    public String titleText;
    public String animSpeedStr;
    int animSpeed;
    public String basePauseStr;
    int basePause;
    public String perCharPauseStr;
    int perCharPause;
    public String reverseColorsStr;
    boolean reverseColors;
    Vector quoteList;
    Quote currentQuote;
    QuoteBox quoteBox;
    MouseListener quoteBoxMouseListener;
    Thread quoteThread;
    volatile boolean quotesLoaded;
    volatile boolean stopped;
    volatile boolean quit;
    
    public QuoteApplet() {
        this.quoteFileName = "quotes.txt";
        this.defaultQuoteColorStr = "orange";
        this.defaultSourceColorStr = "bred";
        this.bgColorStr = "black";
        this.flashBrightnessStr = "1.9";
        this.titleColorStr = "yellow";
        this.titleText = "Fireball's Quote of the Moment";
        this.animSpeedStr = "10";
        this.basePauseStr = "1800";
        this.perCharPauseStr = "72";
        this.reverseColorsStr = "false";
        this.quotesLoaded = false;
        this.stopped = false;
        this.quit = false;
    }
    
    void setupParameters() {
        final String parameter = this.getParameter("quoteFile");
        if (parameter != null) {
            this.quoteFileName = parameter;
        }
        final String parameter2 = this.getParameter("defaultQuoteColor");
        if (parameter2 != null) {
            this.defaultQuoteColorStr = parameter2;
        }
        final String parameter3 = this.getParameter("defaultSourceColor");
        if (parameter3 != null) {
            this.defaultSourceColorStr = parameter3;
        }
        final String parameter4 = this.getParameter("bgColor");
        if (parameter4 != null) {
            this.bgColorStr = parameter4;
        }
        final String parameter5 = this.getParameter("titleColor");
        if (parameter5 != null) {
            this.titleColorStr = parameter5;
        }
        final String parameter6 = this.getParameter("titleText");
        if (parameter6 != null) {
            this.titleText = parameter6;
        }
        final String parameter7 = this.getParameter("animSpeed");
        if (parameter7 != null) {
            this.animSpeedStr = parameter7;
        }
        final String parameter8 = this.getParameter("basePause");
        if (parameter8 != null) {
            this.basePauseStr = parameter8;
        }
        final String parameter9 = this.getParameter("perCharPause");
        if (parameter9 != null) {
            this.perCharPauseStr = parameter9;
        }
        final String parameter10 = this.getParameter("flashBrightness");
        if (parameter10 != null) {
            this.flashBrightnessStr = parameter10;
        }
        final String parameter11 = this.getParameter("reverseColors");
        if (parameter11 != null) {
            this.reverseColorsStr = parameter11;
        }
        this.bgColor = Colors.getAWTColor(this.bgColorStr);
        this.titleColor = Colors.getAWTColor(this.titleColorStr);
        this.animSpeed = Integer.parseInt(this.animSpeedStr);
        this.basePause = Integer.parseInt(this.basePauseStr);
        this.perCharPause = Integer.parseInt(this.perCharPauseStr);
        this.flashBrightness = Float.valueOf(this.flashBrightnessStr);
        this.reverseColors = this.reverseColorsStr.equals("true");
    }
    
    public void init() {
        this.setupParameters();
        this.setBackground(this.bgColor);
        this.setForeground(this.titleColor);
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        panel.setFont(new Font("SansSerif", 1, 12));
        panel.add(new Label(this.titleText, 1));
        this.quoteBox = new QuoteBox(this);
        this.quoteBoxMouseListener = new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    if (!QuoteApplet.this.stopped) {
                        QuoteApplet.this.stopped = true;
                    }
                    else {
                        QuoteApplet.this.stopped = false;
                    }
                    QuoteApplet.this.quoteBox.repaint();
                }
                if (QuoteApplet.this.quoteThread != null) {
                    QuoteApplet.this.notifyQuoteThread();
                }
            }
        };
        this.quoteBox.addMouseListener(this.quoteBoxMouseListener);
        this.add(this.quoteBox, "Center");
        this.add(panel, "North");
        new Thread() {
            public void run() {
                this.setName("quoteLoader");
                QuoteApplet.this.loadQuotes();
            }
        }.start();
        this.makeThread();
    }
    
    public void start() {
        this.stopped = false;
        this.quit = false;
        this.notifyQuoteThread();
    }
    
    public void stop() {
        this.stopped = true;
        this.notifyQuoteThread();
    }
    
    void loadQuotes() {
        this.quoteList = new Vector();
        final InputStream resourceAsStream = this.getClass().getResourceAsStream(this.quoteFileName);
        if (resourceAsStream == null) {
            error("error opening resource stream", new Exception("loadQuotes() qfn=" + this.quoteFileName));
            return;
        }
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
        }
        catch (Exception ex) {
            error("error opening quotes file", ex);
            return;
        }
        Quote quote = new Quote();
        try {
            boolean b = true;
            String line;
            while ((line = bufferedReader.readLine()) != null && b) {
                if (line.equals("$end")) {
                    line = "";
                    b = false;
                }
                if (line.equals("")) {
                    this.quoteList.addElement(quote);
                    quote = new Quote();
                }
                else if (line.startsWith("-- ")) {
                    quote.source = line.substring(3);
                }
                else {
                    if (line.startsWith("//")) {
                        continue;
                    }
                    if (line.startsWith("$flags ")) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(line.substring(7), " ");
                        while (stringTokenizer.hasMoreTokens()) {
                            final String nextToken = stringTokenizer.nextToken();
                            if (nextToken.equals("small")) {
                                quote.setFlag(1);
                            }
                            else if (nextToken.equals("big")) {
                                quote.setFlag(2);
                            }
                            else if (nextToken.equals("bigger")) {
                                quote.setFlag(3);
                            }
                            else if (nextToken.equals("huge")) {
                                quote.setFlag(4);
                            }
                            else if (nextToken.equals("dialog")) {
                                quote.setFlag(10);
                            }
                            else if (nextToken.equals("serif")) {
                                quote.setFlag(11);
                            }
                            else if (nextToken.equals("courier")) {
                                quote.setFlag(12);
                            }
                            else if (nextToken.equals("monospaced")) {
                                quote.setFlag(13);
                            }
                            else if (nextToken.equals("srcface")) {
                                quote.setFlag(14);
                            }
                            else if (nextToken.equals("flash")) {
                                quote.setFlag(16);
                            }
                            else {
                                if (!nextToken.equals("emphasis")) {
                                    continue;
                                }
                                quote.setFlag(17);
                            }
                        }
                    }
                    else {
                        quote.text.addElement(line);
                    }
                }
            }
        }
        catch (IOException ex2) {
            error("error while reading quotes", ex2);
        }
        try {
            bufferedReader.close();
        }
        catch (IOException ex3) {}
        this.quotesLoaded = true;
    }
    
    void makeThread() {
        (this.quoteThread = new QuoteThread(this)).start();
    }
    
    void pickRandomQuote() {
        Quote currentQuote = this.currentQuote;
        final Random random = new Random();
        final int size = this.quoteList.size();
        for (int i = 5; i > 0; --i) {
            currentQuote = (Quote)this.quoteList.elementAt(Math.abs(random.nextInt()) % size);
            if (currentQuote != this.currentQuote) {
                break;
            }
        }
        this.currentQuote = currentQuote;
    }
    
    int calcDelay(final Quote quote) {
        final int basePause = this.basePause;
        int n = 0;
        for (int i = 0; i < quote.text.size(); ++i) {
            n += ((String)quote.text.elementAt(i)).length();
        }
        if (quote.source != null) {
            n += quote.source.length();
        }
        return basePause + n * this.perCharPause;
    }
    
    void notifyQuoteThread() {
        System.out.println("notifyQuoteThread()");
        synchronized (this.quoteThread) {
            this.quoteThread.notify();
        }
    }
    
    public static void error(final String s, final Exception ex) {
        System.out.println("error in thread " + Thread.currentThread().getName());
        System.out.println(" - " + s + ": " + ex);
        System.out.println(" - " + ex.getMessage());
        System.out.println("");
    }
    
    class QuoteThread extends Thread
    {
        QuoteApplet app;
        
        public QuoteThread(final QuoteApplet app) {
            this.app = app;
            this.setDaemon(true);
            this.setName("QuoteThread");
        }
        
        public void run() {
            this.quoteLoop();
        }
        
        void quoteLoop() {
            do {
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex2) {
                    System.out.println("interrupted!!");
                }
            } while (!this.app.quotesLoaded);
            synchronized (this) {
                while (!this.app.quit) {
                    this.app.pickRandomQuote();
                    this.app.quoteBox.newQuote(this.app.currentQuote);
                    try {
                        this.wait(this.app.calcDelay(this.app.currentQuote));
                    }
                    catch (InterruptedException ex3) {
                        System.out.println("Interrupted");
                    }
                    catch (Exception ex) {
                        System.out.println("Exception: " + ex);
                    }
                    if (this.app.quit) {
                        break;
                    }
                    if (!this.app.stopped) {
                        continue;
                    }
                    this.waitForStart();
                }
            }
        }
        
        void waitForStart() {
            while (this.app.stopped) {
                try {
                    this.wait(1000L);
                }
                catch (InterruptedException ex) {}
            }
            this.app.stopped = false;
        }
    }
}
