import java.util.Vector;
import java.awt.Event;
import java.awt.Color;
import java.awt.Component;
import java.awt.Label;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.awt.TextField;
import java.awt.Button;
import java.awt.List;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SiteSearcher extends Applet
{
    static final String searchTokenSeparators = "\"&|+ \t\r\n,*?";
    SearchThread[] threadpool;
    URL[] urls;
    String[] pageinfo;
    int[] matches;
    int nummatches;
    int numreported;
    boolean atHome;
    int numThreads;
    List list;
    Button search;
    Button stop;
    TextField searchbox;
    ProgressBar progressbar;
    String target;
    URL docbase;
    boolean errored;
    URL thegilbertpost;
    
    public void init() {
        this.target = this.getParameter("target");
        this.docbase = this.getDocumentBase();
        final String file = this.docbase.getFile();
        this.atHome = (file.indexOf("Athens/Parthenon/1911") != -1 || file.indexOf("~gilbertnews") != -1);
        try {
            this.thegilbertpost = new URL("http://www.geocities.com/Athens/Parthenon/1911");
        }
        catch (MalformedURLException ex2) {}
        final String parameter;
        if ((parameter = this.getParameter("files")) != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, "\n\r \t,", false);
            final int countTokens = stringTokenizer.countTokens();
            this.urls = new URL[countTokens];
            this.threadpool = new SearchThread[countTokens];
            this.pageinfo = new String[countTokens];
            this.matches = new int[countTokens];
            this.numThreads = countTokens;
            for (int i = 0; i < countTokens; ++i) {
                final String nextToken = stringTokenizer.nextToken();
                this.pageinfo[i] = nextToken;
                this.matches[i] = -1;
                try {
                    this.urls[i] = new URL(this.docbase, nextToken);
                    this.threadpool[i] = new SearchThread(this, this.urls[i], i);
                }
                catch (MalformedURLException ex) {
                    this.urls[i] = null;
                    (this.threadpool[i] = new SearchThread(this, this.urls[i], i)).setErrored();
                    System.out.println(ex);
                }
                this.threadpool[i].setPriority(7);
            }
            this.resize(350, 300);
            this.setLayout(new BorderLayout());
            final Panel panel = new Panel();
            panel.setLayout(new BorderLayout());
            final Panel panel2 = new Panel();
            panel2.setLayout(new GridLayout(2, 1));
            final Panel panel3 = new Panel();
            panel3.setLayout(new BorderLayout());
            final Panel panel4 = new Panel();
            panel4.add(new Label("Search for:", 2));
            panel4.add(this.searchbox = new TextField(20));
            final String parameter2 = this.getParameter("startwords");
            if (parameter2 != null) {
                this.searchbox.setText(parameter2);
            }
            panel4.add(this.search = new Button("Search"));
            panel4.add(this.stop = new Button("Stop"));
            panel3.add("Center", panel4);
            panel2.add(panel3);
            final Panel panel5 = new Panel();
            panel5.add(this.progressbar = new ProgressBar(Color.cyan, Color.blue.brighter().brighter().brighter(), 300, 20, this.numThreads, "Searching..."));
            panel.add("North", panel2);
            panel2.add(panel5);
            panel.add("Center", this.list = new List(5, false));
            this.list.addItem("[SiteSearcher c1999 The Gilbert Post]");
            this.add("Center", panel);
            this.validate();
            return;
        }
        this.errored = true;
        System.out.println("SiteSearcher Applet can't start");
        System.out.println("Missing required parameter: files");
    }
    
    public void reset() {
        this.stopAllThreads();
        this.progressbar.reset();
        this.list.clear();
        this.list.addItem("[SiteSearcher c1999 The Gilbert Post]");
        this.nummatches = 0;
    }
    
    public void stopAllThreads() {
        for (int i = 0; i < this.threadpool.length; ++i) {
            if (this.threadpool[i] != null) {
                this.threadpool[i].stopSearch();
            }
        }
    }
    
    public synchronized void foundMatch(final int n) {
        if (this.nummatches < this.matches.length) {}
        this.matches[this.nummatches] = n;
        ++this.nummatches;
        this.list.addItem(this.pageinfo[n]);
        this.progressbar.plus(1);
    }
    
    public void foundNoMatch(final int n) {
        if (this.threadpool[n].isErrored()) {
            --this.numThreads;
            this.progressbar.setMax(this.numThreads);
        }
        else {
            this.progressbar.plus(1);
        }
    }
    
    public void addInfo(final int n, final String s) {
        final String[] pageinfo = this.pageinfo;
        pageinfo[n] = pageinfo[n] + "   " + s;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.list) {
            this.stopAllThreads();
            final int selectedIndex = this.list.getSelectedIndex();
            if (selectedIndex != -1) {
                this.showPage(selectedIndex);
            }
        }
        else if (event.target == this.searchbox) {
            this.search(this.searchbox.getText());
        }
        else if (event.target == this.stop) {
            this.stopAllThreads();
        }
        else if (event.target == this.search) {
            this.search(this.searchbox.getText());
        }
        return true;
    }
    
    protected void search(final String s) {
        this.reset();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\"&|+ \t\r\n,*?", true);
        final Vector vector = new Vector<String>();
        String s2 = "";
        boolean b = false;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if ("\"&|+ \t\r\n,*?".indexOf(nextToken) != -1) {
                if ("\"".equals(nextToken)) {
                    b = !b;
                    if (b) {
                        s2 = "";
                    }
                    else {
                        if (s2 != "") {
                            vector.addElement(new String(s2).toLowerCase());
                        }
                        s2 = "";
                    }
                }
                else {
                    if (!b) {
                        continue;
                    }
                    s2 += nextToken;
                }
            }
            else if (!b) {
                vector.addElement(new String(nextToken).toLowerCase());
                s2 = "";
            }
            else {
                if (!b) {
                    continue;
                }
                s2 += nextToken;
            }
        }
        if (s2 != "") {
            vector.addElement(new String(s2).toLowerCase());
        }
        final String[] array = new String[vector.size()];
        vector.copyInto(array);
        for (int i = 0; i < this.threadpool.length; ++i) {
            this.threadpool[i].searchFor(array);
        }
    }
    
    protected void showPage(final int n) {
        if (n == 0 && !this.atHome) {
            this.stopAllThreads();
            this.getAppletContext().showDocument(this.thegilbertpost, "_top");
        }
        final int n2 = n - 1;
        if (n2 < 0) {
            return;
        }
        this.stopAllThreads();
        if (this.target != null) {
            this.getAppletContext().showDocument(this.urls[this.matches[n2]], this.target);
        }
        else {
            this.getAppletContext().showDocument(this.urls[this.matches[n2]]);
        }
    }
    
    public void stop() {
        this.stopAllThreads();
    }
    
    public void destroy() {
        for (int i = 0; i < this.threadpool.length; ++i) {
            this.threadpool[i].endLife();
        }
    }
    
    public SiteSearcher() {
        this.nummatches = 0;
        this.numreported = 0;
        this.atHome = false;
        this.numThreads = 0;
        this.errored = false;
        this.thegilbertpost = null;
    }
}
