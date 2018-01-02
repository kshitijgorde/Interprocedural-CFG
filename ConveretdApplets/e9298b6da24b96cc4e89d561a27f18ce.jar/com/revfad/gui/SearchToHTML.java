// 
// Decompiled by Procyon v0.5.30
// 

package com.revfad.gui;

import com.revfad.search.SearchSieve;
import java.net.URLEncoder;
import java.awt.Container;
import com.revfad.search.Match;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.io.UnsupportedEncodingException;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.revfad.search.Document;
import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.awt.Button;
import java.awt.CardLayout;
import com.revfad.search.DocumentQueue;
import java.util.Vector;
import java.util.Hashtable;
import com.revfad.search.SearchThread;
import java.awt.Frame;
import java.net.URL;
import com.revfad.search.SearchThreadListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.applet.Applet;

public class SearchToHTML extends Applet implements Runnable, KeyListener, ActionListener, SearchThreadListener
{
    private static final String version = "SearchToHTML 3.0 September 3, 2004";
    private static final String description = "A client-side search engine that displays its search results as HTML.";
    private static final String author = "David Faden <gilbertnews@gmail.com>";
    private static String appletInfo;
    private static String[][] parameterInfo;
    private int numberOfDocuments;
    private int maxNumOfMatches;
    private ProgressBar progressBar;
    private String target;
    private boolean displayMessage;
    private String message;
    private URL baseUrl;
    private URL resultsBaseUrl;
    private boolean sendContext;
    private boolean sendTitles;
    private boolean sendDocumentInfo;
    private boolean sendAnchors;
    private boolean phraseSearchesOnly;
    private boolean searching;
    private String stop_btn_txt;
    private Frame frame;
    private Thread mainThread;
    private int numberOfThreads;
    private SearchThread[] threads;
    private Hashtable options;
    private Vector documents;
    private DocumentQueue documentQueue;
    private Vector theMatches;
    private CardLayout cardLayout;
    private Button nextButton;
    private Button previousButton;
    private int pageNumber;
    private int numberOfPages;
    private int matchesPerPage;
    private int maxUrlLength;
    private String searchString;
    private Vector pages;
    private static final String SEARCHING_PANEL = "SEARCHING_PANEL";
    private static final String RESULTS_PANEL = "RESULTS_PANEL";
    private Hashtable queryParams;
    private int documentsRemainingToFinish;
    
    public SearchToHTML() {
        this.searching = false;
        this.frame = null;
        this.mainThread = null;
        this.numberOfThreads = 4;
    }
    
    public void init() {
        this.baseUrl = this.getDocumentBase();
        final String parameter = this.getParameter("base_url");
        if (parameter != null) {
            try {
                this.baseUrl = new URL(parameter);
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        this.searching = true;
        this.options = new Hashtable();
        final int positiveParameter = this.getPositiveParameter("leadingcontextlength", 15);
        final int positiveParameter2 = this.getPositiveParameter("trailingcontextlength", 0);
        this.options.put(SearchThread.Option.LEADING_CONTEXT_LENGTH, new Integer(positiveParameter));
        this.options.put(SearchThread.Option.TRAILING_CONTEXT_LENGTH, new Integer(positiveParameter2));
        String filesToSearch = this.getFilesToSearch();
        if (filesToSearch == null) {
            filesToSearch = "";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(filesToSearch, "\n\r \t,", false);
        final int countTokens = stringTokenizer.countTokens();
        this.documents = new Vector();
        for (int i = 0; i < countTokens; ++i) {
            final String nextToken = stringTokenizer.nextToken();
            try {
                this.documents.addElement(new Document(Document.buildUrl(this.baseUrl, nextToken), null));
            }
            catch (MalformedURLException ex2) {
                ex2.printStackTrace();
            }
        }
        this.numberOfDocuments = this.documents.size();
        this.maxNumOfMatches = this.getPositiveParameter("max_num_matches", 100000000);
        this.target = this.getParameter("target", "results");
        final String parameter2 = this.getParameter("resultspage", "displaymatches.html");
        try {
            this.resultsBaseUrl = Document.buildUrl(this.baseUrl, parameter2);
        }
        catch (MalformedURLException ex3) {
            throw new RuntimeException(ex3.toString());
        }
        if (!(this.sendContext = this.getParameter("send_context", true))) {
            this.options.put(SearchThread.Option.LEADING_CONTEXT_LENGTH, new Integer(0));
            this.options.put(SearchThread.Option.TRAILING_CONTEXT_LENGTH, new Integer(0));
        }
        this.sendTitles = this.getParameter("send_titles", true);
        this.sendDocumentInfo = this.getParameter("send_info", true);
        this.sendAnchors = this.getParameter("send_anchors", true);
        this.phraseSearchesOnly = this.getParameter("phrase_searches_only", false);
        this.initNormalGui();
    }
    
    private String getFilesToSearch() {
        String s = this.getParameter("files");
        final String parameter = this.getParameter("files_file");
        BufferedReader bufferedReader = null;
        if (parameter != null) {
            try {
                final URL url = new URL(this.baseUrl, parameter);
                final StringBuffer sb = new StringBuffer(128);
                bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                for (String s2 = bufferedReader.readLine(); s2 != null; s2 = bufferedReader.readLine()) {
                    sb.append(s2);
                    sb.append(' ');
                }
                final String string = sb.toString();
                if (s == null) {
                    s = string;
                }
                else {
                    s = s + ',' + string;
                }
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
            finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    }
                    catch (IOException ex3) {
                        ex3.printStackTrace();
                    }
                }
            }
        }
        return s;
    }
    
    private void initNormalGui() {
        this.setLayout(this.cardLayout = new CardLayout());
        this.addKeyListener(this);
        this.setBackground(this.getParameter("bgcolor", Color.gray));
        this.setForeground(this.getParameter("fgcolor", Color.black));
        final Panel panel = new Panel(new BorderLayout());
        this.progressBar = new ProgressBar(this.numberOfDocuments, this.getParameter("progress_bar_completed_color", Color.blue), this.getParameter("progress_bar_remaining_color", Color.cyan), this.getParameter("progress_bar_text_color", Color.black), this.getParameter("progress_bar_message", "Searching... "));
        final Dimension size = this.getSize();
        int n = this.getPositiveParameter("progress_bar_height", 20);
        if (n > size.height) {
            n = size.height;
        }
        int n2 = this.getPositiveParameter("progress_bar_width", size.width - 100);
        if (n2 > size.width) {
            n2 = size.width;
        }
        this.progressBar.setSize(n2, n);
        this.progressBar.setPercentMessageVisible(!this.getParameter("hide_progress_bar_message", false));
        final Font fontParameter = this.getFontParameter("progress_bar_font");
        if (fontParameter != null) {
            this.progressBar.setFont(fontParameter);
        }
        final Panel panel2 = new Panel();
        panel2.add(this.progressBar);
        panel.add(panel2, "West");
        this.progressBar.setProgressBarVisible(false);
        final Color color = getColor(this.getParameter("buttonbgcolor"));
        final Color color2 = getColor(this.getParameter("buttonfgcolor"));
        final Font fontParameter2 = this.getFontParameter("button_font");
        final Button button = newButton(this.getParameter("stop_btn_txt", "Stop"), color, color2, fontParameter2);
        button.addActionListener(this);
        final Panel panel3 = new Panel();
        panel3.add(button);
        panel.add(panel3, "East");
        this.add(panel, "SEARCHING_PANEL");
        final Panel panel4 = new Panel(new FlowLayout());
        final Label label = new Label(this.getParameter("label_txt", "Results Page:"));
        final Font fontParameter3 = this.getFontParameter("label_font");
        if (fontParameter3 != null) {
            label.setFont(fontParameter3);
        }
        panel4.add(label);
        (this.previousButton = newButton(this.getParameter("previous_btn_txt", "Previous"), color, color2, fontParameter2)).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                SearchToHTML.this.displayPreviousResultsPage();
            }
        });
        panel4.add(this.previousButton);
        (this.nextButton = newButton(this.getParameter("next_btn_txt", "Next"), color, color2, fontParameter2)).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                SearchToHTML.this.displayNextResultsPage();
            }
        });
        if (color != null) {
            this.nextButton.setBackground(color);
        }
        if (color2 != null) {
            this.nextButton.setForeground(color2);
        }
        panel4.add(this.nextButton);
        this.add(panel4, "RESULTS_PANEL");
        this.validate();
    }
    
    private static Button newButton(final String s, final Color background, final Color foreground, final Font font) {
        final Button button = new Button(s);
        if (background != null) {
            button.setBackground(background);
        }
        if (foreground != null) {
            button.setForeground(foreground);
        }
        if (font != null) {
            button.setFont(font);
        }
        return button;
    }
    
    public final String getAppletInfo() {
        return SearchToHTML.appletInfo;
    }
    
    public final String[][] getParameterInfo() {
        return SearchToHTML.parameterInfo;
    }
    
    public String getParameter(final String s) {
        if (this.queryParams == null) {
            final String string = this.getDocumentBase().toString();
            final int index = string.indexOf(63);
            String s2;
            if (index != -1) {
                s2 = string.substring(index + 1);
            }
            else {
                s2 = super.getParameter("query");
            }
            this.queryParams = parseQuery(s2);
        }
        final String s3 = this.queryParams.get(s);
        if (s3 != null) {
            return s3;
        }
        return super.getParameter(s);
    }
    
    private Font getFontParameter(final String s) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return null;
        }
        return Font.decode(parameter);
    }
    
    private boolean getParameter(final String s, final boolean b) {
        boolean b2 = b;
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            if ("true".equalsIgnoreCase(parameter) || "on".equalsIgnoreCase(parameter)) {
                b2 = true;
            }
            else if ("false".equalsIgnoreCase(parameter)) {
                b2 = false;
            }
        }
        return b2;
    }
    
    private int getPositiveParameter(final String s, final int n) {
        int n2 = n;
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            try {
                final int int1 = Integer.parseInt(parameter);
                if (int1 >= 0) {
                    n2 = int1;
                }
                else {
                    System.err.println("Negative value for '" + s + "': " + int1);
                }
            }
            catch (NumberFormatException ex) {
                System.err.println("Invalid value for '" + s + "': " + parameter);
            }
        }
        return n2;
    }
    
    private final String getParameter(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            return parameter;
        }
        return s2;
    }
    
    private Color getParameter(final String s, final Color color) {
        Color color2 = color;
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            final Color color3 = getColor(parameter);
            if (color3 != null) {
                color2 = color3;
            }
        }
        return color2;
    }
    
    public static final Color getColor(final String s) {
        if (s == null) {
            return null;
        }
        if (s.startsWith("#")) {
            if (s.length() != 7) {
                return null;
            }
            try {
                return new Color(Integer.parseInt(s.substring(1, 7), 16));
            }
            catch (NumberFormatException ex) {
                return null;
            }
        }
        if ("black".equalsIgnoreCase(s)) {
            return Color.black;
        }
        if ("blue".equalsIgnoreCase(s)) {
            return Color.blue;
        }
        if ("darkblue".equalsIgnoreCase(s)) {
            return Color.blue.darker().darker().darker();
        }
        if ("lightblue".equalsIgnoreCase(s)) {
            return Color.blue.brighter().brighter().brighter();
        }
        if ("cyan".equalsIgnoreCase(s)) {
            return Color.cyan;
        }
        if ("darkgray".equalsIgnoreCase(s)) {
            return Color.darkGray;
        }
        if ("lightgray".equalsIgnoreCase(s)) {
            return Color.lightGray;
        }
        if ("green".equalsIgnoreCase(s)) {
            return Color.green;
        }
        if ("gray".equalsIgnoreCase(s)) {
            return Color.gray;
        }
        if ("magenta".equalsIgnoreCase(s)) {
            return Color.magenta;
        }
        if ("orange".equalsIgnoreCase(s)) {
            return Color.orange;
        }
        if ("pink".equalsIgnoreCase(s)) {
            return Color.pink;
        }
        if ("red".equalsIgnoreCase(s)) {
            return Color.red;
        }
        if ("white".equalsIgnoreCase(s)) {
            return Color.white;
        }
        if ("yellow".equalsIgnoreCase(s)) {
            return Color.yellow;
        }
        return Color.getColor(s);
    }
    
    public static Hashtable parseQuery(String s) {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        if (s == null) {
            return hashtable;
        }
        if (s.startsWith("?")) {
            s = s.substring(1);
        }
        final int index = s.indexOf(35);
        if (index != -1) {
            s = s.substring(0, index);
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "&");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int index2 = nextToken.indexOf(61);
            if (index2 != -1) {
                final String decodeEscapedUtf8 = decodeEscapedUtf8(nextToken.substring(index2 + 1));
                if (decodeEscapedUtf8 == null) {
                    continue;
                }
                hashtable.put(nextToken.substring(0, index2), decodeEscapedUtf8);
            }
        }
        return hashtable;
    }
    
    public static String decodeEscapedUtf8(final String s) {
        final int length = s.length();
        final byte[] array = new byte[length];
        try {
            int n = 0;
            for (int i = 0; i < length; ++i) {
                final char char1 = s.charAt(i);
                if (char1 == '%') {
                    array[n] = (byte)(hexToInt(s.charAt(++i)) << 4 | hexToInt(s.charAt(++i)));
                }
                else if (char1 == '+') {
                    array[n] = 32;
                }
                else {
                    if ('\0' > char1 || char1 > '\u00ff') {
                        return null;
                    }
                    array[n] = (byte)char1;
                }
                ++n;
            }
            return new String(array, 0, n, "UTF8");
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
        catch (IndexOutOfBoundsException ex2) {
            return null;
        }
        catch (UnsupportedEncodingException ex3) {
            return null;
        }
    }
    
    private static final int hexToInt(final char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'a' + '\n';
        }
        if (c >= 'A' && c <= 'F') {
            return c - 'A' + '\n';
        }
        throw new IllegalArgumentException("Not hex: " + c);
    }
    
    private static String escapeForHtml(final String s, final boolean b) {
        final StringBuffer sb = new StringBuffer();
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 > '\u007f') {
                sb.append("&#x");
                sb.append(Integer.toHexString(char1));
                sb.append(';');
            }
            else if (char1 == '<') {
                sb.append("&lt;");
            }
            else if (char1 == '>') {
                sb.append("&gt;");
            }
            else if (b && char1 == '&') {
                sb.append("&amp;");
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    private static String replaceChar(final String s, final char c, final String s2) {
        if (s.indexOf(c) < 0) {
            return s;
        }
        final StringBuffer sb = new StringBuffer();
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == c) {
                sb.append(s2);
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    public void start() {
        if (this.mainThread == null) {
            (this.mainThread = new Thread(this, "SearchToHTML main thread")).start();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.displayMessage) {
            graphics.drawString(this.message, 3, this.getSize().height / 2);
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        if ((keyEvent.getKeyChar() == '.' && keyEvent.isMetaDown()) || keyEvent.getKeyCode() == 27) {
            this.cancelSearch();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.cancelSearch();
    }
    
    public void displayNextResultsPage() {
        if (this.pageNumber < this.numberOfPages - 1) {
            ++this.pageNumber;
            this.displayResultsPage();
        }
    }
    
    public void displayPreviousResultsPage() {
        if (this.pageNumber > 0) {
            --this.pageNumber;
            this.displayResultsPage();
        }
    }
    
    public void displayResultsPage() {
        if (this.pageNumber == 0) {
            this.previousButton.setEnabled(false);
        }
        else {
            this.previousButton.setEnabled(true);
        }
        if (this.pageNumber == this.numberOfPages - 1 || this.numberOfPages < 2) {
            this.nextButton.setEnabled(false);
        }
        else {
            this.nextButton.setEnabled(true);
        }
        this.getAppletContext().showDocument(this.pages.elementAt(this.pageNumber), this.target);
    }
    
    private void cancelSearch() {
        this.searching = false;
        this.stopSearchThreads();
    }
    
    public void run() {
        this.options.put(SearchThread.Option.IGNORE_TAG_CONTENT, this.getParameter("ignore_tags", false) ? Boolean.TRUE : Boolean.FALSE);
        this.options.put(SearchThread.Option.EXACT_MATCHES_ONLY, this.getParameter("exact_matches_only", false) ? Boolean.TRUE : Boolean.FALSE);
        this.options.put(SearchThread.Option.MUST_MATCH_ALL, this.getParameter("match_all_terms", false) ? Boolean.TRUE : Boolean.FALSE);
        this.options.put(SearchThread.Option.ALLOW_MULTIPLE_MATCHES, this.getParameter("allow_multiple", false) ? Boolean.TRUE : Boolean.FALSE);
        this.searchString = this.getParameter("q");
        if (this.searchString == null) {
            throw new RuntimeException("q is null!");
        }
        if ("*version*".equals(this.searchString)) {
            System.out.println(this.getAppletInfo());
        }
        this.progressBar.setProgressBarVisible(true);
        this.progressBar.setMaximumValue(this.numberOfDocuments);
        final Vector search = this.searchFor(this.searchString, this.options);
        final Match[] array = new Match[search.size()];
        search.copyInto(array);
        quicksort(array, 0, array.length - 1, this.getParameter("sort_by_date", false));
        if (this.searching) {
            this.matchesPerPage = this.getPositiveParameter("matches_per_page", 25);
            this.maxUrlLength = this.getPositiveParameter("max_url_length", 2040);
            this.pages = this.paginateResults(this.searchString, this.options, array);
            this.numberOfPages = this.pages.size();
            this.pageNumber = 0;
            this.displayResultsPage();
            this.cardLayout.show(this, "RESULTS_PANEL");
        }
    }
    
    private static void quicksort(final Match[] array, final int n, final int n2, final boolean b) {
        if (n < n2) {
            final int partition = partition(array, n, n2, b);
            quicksort(array, n, partition - 1, b);
            quicksort(array, partition + 1, n2, b);
        }
    }
    
    private static int partition(final Match[] array, final int n, final int n2, final boolean b) {
        final Match match = array[n2];
        int n3 = n - 1;
        for (int i = n; i < n2; ++i) {
            final Match match2 = array[i];
            if ((b && match2.compareDateTo(match) <= 0) || (!b && match2.compareTo(match) <= 0)) {
                ++n3;
                swap(array, n3, i);
            }
        }
        swap(array, n3 + 1, n2);
        return n3 + 1;
    }
    
    private static void swap(final Object[] array, final int n, final int n2) {
        final Object o = array[n2];
        array[n2] = array[n];
        array[n] = o;
    }
    
    public Vector paginateResults(final String s, final Hashtable hashtable, final Match[] array) {
        final String string = this.resultsBaseUrl.toString();
        final int length = array.length;
        if (length == 0) {
            final Vector<URL> vector = new Vector<URL>(1);
            try {
                vector.addElement(new URL(string + "?" + this.queryStringPrefix(1, 1, s, hashtable, length)));
                return vector;
            }
            catch (MalformedURLException ex) {
                throw new RuntimeException(ex.toString());
            }
        }
        final Vector vector2 = new Vector<String>();
        final int n = string.length() + 3 * s.length() + 8 + (int)Math.log(length + 1) + 6 + 1;
        int i = 0;
        while (i < length) {
            final StringBuffer sb = new StringBuffer();
            final int min = Math.min(length, i + this.matchesPerPage);
            sb.append(this.matchToQueryString(array[i]));
            int n2 = n + sb.length();
            ++i;
            while (i < min) {
                final String matchToQueryString = this.matchToQueryString(array[i]);
                if (matchToQueryString.length() + n2 >= this.maxUrlLength) {
                    break;
                }
                n2 += matchToQueryString.length();
                sb.append(matchToQueryString);
                ++i;
            }
            vector2.addElement(sb.toString());
        }
        final int size = vector2.size();
        final Vector vector3 = new Vector<URL>(size);
        for (int j = 0; j < size; ++j) {
            try {
                vector3.addElement(new URL(string + "?" + this.queryStringPrefix(size, j + 1, s, hashtable, length) + vector2.elementAt(j)));
            }
            catch (MalformedURLException ex2) {
                throw new RuntimeException(ex2.toString());
            }
        }
        return vector3;
    }
    
    public String queryStringPrefix(final int n, final int n2, final String s, final Hashtable hashtable, final int n3) {
        final StringBuffer sb = new StringBuffer();
        sb.append(n);
        sb.append(',');
        sb.append(n2);
        sb.append(',');
        sb.append(replaceChar(URLEncoder.encode(escapeForHtml(s, true)), '+', "%20"));
        sb.append(',');
        sb.append(n3);
        sb.append(',');
        if (SearchThread.Option.EXACT_MATCHES_ONLY.getFromWithDefault(hashtable, false)) {
            sb.append('y');
        }
        else {
            sb.append('n');
        }
        if (SearchThread.Option.IGNORE_TAG_CONTENT.getFromWithDefault(hashtable, false)) {
            sb.append('y');
        }
        else {
            sb.append('n');
        }
        return sb.toString();
    }
    
    private static String urlEncode(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ./", true);
        final StringBuffer sb = new StringBuffer();
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (" ".equals(nextToken)) {
                sb.append("%20");
            }
            else if ("/".equals(nextToken) || ".".equals(nextToken)) {
                sb.append(nextToken);
            }
            else {
                sb.append(URLEncoder.encode(nextToken));
            }
        }
        return sb.toString();
    }
    
    public String matchToQueryString(final Match match) {
        final StringBuffer sb = new StringBuffer();
        final Document document = match.document();
        sb.append(urlEncode(document.pathRelativeTo(this.resultsBaseUrl)));
        sb.append(':');
        final String title = document.title();
        if (title != null && this.sendTitles) {
            sb.append(urlEncode(escapeForHtml(title, false)));
        }
        sb.append(':');
        final String nearestAnchor = match.nearestAnchor();
        if (nearestAnchor != null && this.sendAnchors) {
            sb.append(urlEncode(nearestAnchor));
        }
        sb.append(':');
        final int contentLength = match.document().contentLength();
        if (contentLength > 0 && this.sendDocumentInfo) {
            sb.append(contentLength);
        }
        sb.append(':');
        final long lastModified = document.lastModified();
        if (lastModified > 0L && this.sendDocumentInfo) {
            sb.append(lastModified);
        }
        sb.append(':');
        final String context = match.context();
        if (context != null && this.sendContext) {
            sb.append(urlEncode(escapeForHtml(context, true)));
        }
        sb.append(',');
        return sb.toString();
    }
    
    public Vector searchFor(String lowerCase, final Hashtable hashtable) {
        lowerCase = lowerCase.toLowerCase();
        final boolean b = Boolean.TRUE == hashtable.get(SearchThread.Option.EXACT_MATCHES_ONLY);
        SearchSieve[] query;
        if (!this.phraseSearchesOnly) {
            query = SearchSieve.parseQuery(lowerCase, b);
        }
        else {
            query = new SearchSieve[] { new SearchSieve(lowerCase, b) };
        }
        this.documentsRemainingToFinish = this.documents.size();
        this.documentQueue = new DocumentQueue(this.documents);
        this.theMatches = new Vector();
        this.threads = new SearchThread[this.numberOfThreads];
        for (int i = 0; i < this.numberOfThreads; ++i) {
            (this.threads[i] = new SearchThread(query, this.documentQueue, this, hashtable)).start();
        }
        try {
            for (int j = 0; j < this.numberOfThreads; ++j) {
                this.threads[j].join();
            }
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return this.theMatches;
    }
    
    public void searching(final SearchThread searchThread, final Document document) {
        this.getAppletContext().showStatus("Searching " + document.fileName());
    }
    
    public synchronized void foundMatch(final SearchThread searchThread, final Match match) {
        if (this.theMatches.size() >= this.maxNumOfMatches) {
            this.stopSearchThreads();
        }
        else {
            this.theMatches.addElement(match);
            this.getAppletContext().showStatus("Found match: " + match.document().fileName());
            if (this.theMatches.size() >= this.maxNumOfMatches) {
                this.stopSearchThreads();
            }
        }
    }
    
    public synchronized void finishedSearching(final SearchThread searchThread, final Document document) {
        this.progressBar.increment();
        --this.documentsRemainingToFinish;
        if (this.documentsRemainingToFinish == 0) {
            this.stopSearchThreads();
        }
    }
    
    public void foundLink(final SearchThread searchThread, final Document document, final String s, final String s2) {
        throw new RuntimeException("Not implemented yet");
    }
    
    private void stopSearchThreads() {
        if (this.documentQueue != null) {
            this.documentQueue.kill();
        }
        if (this.threads != null) {
            for (int i = 0; i < this.numberOfThreads; ++i) {
                this.threads[i].stopSearching();
            }
        }
    }
    
    public void stop() {
        this.searching = false;
        this.stopSearchThreads();
    }
    
    static {
        SearchToHTML.appletInfo = "SearchToHTML 3.0 September 3, 2004" + System.getProperty("line.separator") + "David Faden <gilbertnews@gmail.com>" + System.getProperty("line.separator") + "A client-side search engine that displays its search results as HTML.";
        SearchToHTML.parameterInfo = new String[][] { { "files", "url list", "documents to search" }, { "target", "string", "target frame for search results" }, { "leadingcontextlength", "integer", "size of leading context, including match" }, { "trailingcontextlength", "integer", "size of trailing context" }, { "max_num_matches", "integer", "maximum number of results" }, { "resultspage", "url", "search results receiver/displayer" }, { "stop_btn_txt", "string", "text for \"stop\" button" }, { "bgcolor", "color", "applet background color" }, { "fgcolor", "color", "applet foreground color" }, { "buttonbgcolor", "color", "button background color" }, { "buttonfgcolor", "color", "button foreground color" }, { "ignore_tags", "boolean", "ignore tags" }, { "exact_matches_only", "boolean", "accept exact matches only" }, { "progress_bar_completed_color", "color", "color for completion" }, { "progress_bar_remaining_color", "color", "color for remaining" }, { "progress_bar_text_color", "color", "color for progress bar text" }, { "progress_bar_message", "string", "progress bar message" }, { "progress_bar_height", "integer", "height of progress bar" }, { "hide_progress_bar_message", "boolean", "hide progress bar message" }, { "progress_bar_width", "integer", "width of progress bar" }, { "send_context", "boolean", "send context" }, { "send_titles", "boolean", "send titles" }, { "send_info", "boolean", "send info" }, { "send_anchors", "boolean", "send anchors" }, { "phrase_searches_only", "boolean", "phrase searches only" }, { "files_file", "string", "file containing list of files to search" }, { "match_all_terms", "boolean", "must all terms match" } };
    }
}
