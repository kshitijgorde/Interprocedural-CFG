import java.util.StringTokenizer;
import java.io.BufferedInputStream;
import java.util.Date;
import java.net.URLConnection;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Font;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.awt.Color;
import java.awt.Label;
import java.util.Hashtable;
import java.awt.Button;
import java.awt.List;
import java.awt.TextField;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JSearchBox extends Applet implements Runnable
{
    String loading_file;
    String loading_letter;
    String old_query;
    String wordlist;
    String compressed_wordlist;
    String compressed_indexlist;
    String indexlist;
    String target;
    String default_url;
    String nomatches;
    String searching;
    String matchesfound;
    String search_caption;
    String open_caption;
    String promo_text;
    String promo_url;
    String promo2_text;
    String promo2_url;
    int min_query_size;
    int wordlist_size;
    int first_match_pos;
    int index_size;
    int mouse_x;
    int mouse_y;
    TextField query_field;
    List result_list;
    List search_list;
    List result_list1;
    List result_list2;
    Button submit_button;
    Hashtable indexlist_hash;
    boolean shareware;
    boolean loading_in_progress;
    boolean cursor_in_button;
    boolean case_sensitive;
    boolean search_implicitly;
    boolean searching_implicitly;
    boolean no_matches_found;
    boolean gzip_missing;
    char[] wordlist_chararray;
    char entry_separator;
    char component_separator;
    Label loading_letter_label;
    Label loading_letter_caption;
    Label help_label;
    Thread load_thread;
    AMDProgressBar progress_bar;
    
    public void init() {
        System.out.println("\nJSearchBox 1.0\n© Copyright by Sam Makagon, 1998-1999\nAll Rights Reserved\n" + this.promo_text + " " + this.promo_url);
        Color white = Color.white;
        Color black = Color.black;
        Color white2 = Color.white;
        Color black2 = Color.black;
        Font font = null;
        this.gzip_missing = false;
        try {
            new GZIPInputStream(null);
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            this.gzip_missing = true;
        }
        catch (NoClassDefFoundError noClassDefFoundError) {
            this.gzip_missing = true;
        }
        catch (Exception ex) {}
        if (this.getParameter("list_bg_color") != null && !this.getParameter("list_bg_color").equals("")) {
            white2 = new Color(Integer.parseInt(this.getParameter("list_bg_color"), 16));
        }
        if (this.getParameter("list_fg_color") != null && !this.getParameter("list_fg_color").equals("")) {
            black2 = new Color(Integer.parseInt(this.getParameter("list_fg_color"), 16));
        }
        if (this.getParameter("text_bg_color") != null && !this.getParameter("text_bg_color").equals("")) {
            white = new Color(Integer.parseInt(this.getParameter("text_bg_color"), 16));
        }
        if (this.getParameter("text_fg_color") != null && !this.getParameter("text_fg_color").equals("")) {
            black = new Color(Integer.parseInt(this.getParameter("text_fg_color"), 16));
        }
        this.search_caption = this.getParameter("search");
        if (this.search_caption == null) {
            this.search_caption = "Search";
        }
        this.open_caption = this.getParameter("open");
        if (this.open_caption == null) {
            this.open_caption = "Open";
        }
        String parameter = this.getParameter("font");
        if (parameter.equals("")) {
            parameter = null;
        }
        if (parameter != null) {
            int n = 0;
            int int1 = 10;
            final String parameter2 = this.getParameter("font_style");
            if (parameter2.toLowerCase().indexOf("bold") != -1) {
                ++n;
            }
            else if (parameter2.toLowerCase().indexOf("italic") != -1) {
                n += 2;
            }
            if (this.getParameter("font_size") != null && !this.getParameter("font_size").equals("")) {
                int1 = Integer.parseInt(this.getParameter("font_size"));
            }
            try {
                font = new Font(this.getParameter("font"), n, int1);
            }
            catch (Exception ex2) {
                font = null;
            }
        }
        final boolean b = this.getParameter("show_list") == null || this.getParameter("show_list").equals("") || new Boolean(this.getParameter("show_list"));
        final boolean b2 = this.getParameter("show_button") == null || this.getParameter("show_button").equals("") || new Boolean(this.getParameter("show_button"));
        this.loading_file = this.getParameter("loading_file");
        if (this.loading_file == null) {
            this.loading_file = "loading file";
        }
        this.loading_letter = this.getParameter("loading_letter");
        if (this.loading_letter == null) {
            this.loading_letter = "Letter:";
        }
        this.setLayout(null);
        if (this.search_caption.length() >= this.open_caption.length()) {
            this.submit_button = new Button(this.search_caption);
        }
        else {
            this.submit_button = new Button(this.open_caption);
        }
        if (font != null) {
            this.submit_button.setFont(font);
        }
        (this.result_list = new List()).setBackground(white2);
        this.result_list.setForeground(black2);
        if (font != null) {
            this.result_list.setFont(font);
        }
        (this.search_list = new List()).setBackground(white2);
        this.search_list.setForeground(black2);
        if (font != null) {
            this.search_list.setFont(font);
        }
        (this.query_field = new TextField()).setBackground(white);
        this.query_field.setForeground(black);
        if (font != null) {
            this.query_field.setFont(font);
        }
        final Insets insets = this.insets();
        final Dimension size = this.size();
        final int top = insets.top;
        final int n2 = size.height - insets.bottom;
        final int left = insets.left;
        final int n3 = size.width - insets.right;
        if (b2) {
            this.add(this.submit_button);
            final Dimension preferredSize = this.submit_button.preferredSize();
            this.submit_button.reshape(n3 - preferredSize.width, top, preferredSize.width, preferredSize.height);
            this.submit_button.setLabel(this.search_caption);
        }
        this.add(this.query_field);
        final Dimension preferredSize2 = this.query_field.preferredSize();
        int n4 = n3 - left;
        if (b2) {
            n4 = n3 - left - this.submit_button.size().width;
        }
        this.query_field.reshape(left, top, n4, preferredSize2.height);
        this.add(this.progress_bar = new AMDProgressBar());
        this.progress_bar.reshape(left, n2 - 21, (n3 - left) * 2 / 3, 21);
        if (!this.loading_file.equals("")) {
            this.progress_bar.setText(String.valueOf(this.loading_file) + " 1/2");
        }
        this.progress_bar.setBarColor(new Color(0, 0, 128));
        this.progress_bar.setBoxColors(white2, white2);
        this.progress_bar.setBackgroundColors(Color.white, Color.white);
        this.progress_bar.setBorderColors(white2, Color.black);
        this.progress_bar.setTextColors(new Color(255, 102, 51), Color.black);
        this.progress_bar.setFont(new Font("Courier", 0, 12));
        (this.loading_letter_caption = new Label(this.loading_letter)).setBackground(white2);
        this.loading_letter_caption.setForeground(black2);
        this.add(this.loading_letter_caption);
        final Dimension preferredSize3 = this.loading_letter_caption.preferredSize();
        this.loading_letter_caption.reshape(left + (n3 - left) * 2 / 3, n2 - 21, preferredSize3.width, preferredSize3.height);
        this.add(this.loading_letter_label = new Label("  ", 0));
        this.loading_letter_label.reshape(left + (n3 - left) * 2 / 3 + preferredSize3.width, n2 - 21, n3 - (left + (n3 - left) * 2 / 3 + preferredSize3.width), this.loading_letter_label.preferredSize().height);
        this.loading_letter_label.setBackground(white2);
        this.loading_letter_label.setForeground(black2);
        if (b) {
            this.add(this.result_list);
            final Dimension preferredSize4 = this.query_field.preferredSize();
            final Dimension preferredSize5 = this.progress_bar.preferredSize();
            this.result_list.reshape(left, top + preferredSize4.height, n3 - left, n2 - top - preferredSize4.height - preferredSize5.height);
            if (this.shareware) {
                this.result_list.addItem(this.promo2_text);
            }
            this.add(this.search_list);
            this.search_list.reshape(left, top + preferredSize4.height, n3 - left, n2 - top - preferredSize4.height - preferredSize5.height);
        }
        this.query_field.requestFocus();
        this.wordlist = this.getParameter("data_file");
        if (this.wordlist == null) {
            this.wordlist = "";
        }
        this.compressed_wordlist = this.getParameter("compressed_data_file");
        if (this.compressed_wordlist == null) {
            this.compressed_wordlist = "";
        }
        if (this.compressed_wordlist.equals("") && this.wordlist.equals("")) {
            this.loading_err("parameter wordlist missing");
            return;
        }
        if (this.wordlist.equals("") && this.gzip_missing) {
            this.loading_err("parameter wordlist missing");
            return;
        }
        this.indexlist = this.getParameter("index_file");
        if (this.indexlist == null) {
            this.indexlist = "";
        }
        this.compressed_indexlist = this.getParameter("compressed_index_file");
        if (this.compressed_indexlist == null) {
            this.compressed_indexlist = "";
        }
        if (this.compressed_indexlist.equals("") && this.indexlist.equals("")) {
            this.loading_err("parameter indexlist missing");
            return;
        }
        if (this.indexlist.equals("") && this.gzip_missing) {
            this.loading_err("parameter indexlist missing");
            return;
        }
        if (this.getParameter("min_implicit_query_length") == null || this.getParameter("min_implicit_query_length").equals("")) {
            this.min_query_size = 1;
        }
        else {
            this.min_query_size = new Integer(this.getParameter("min_implicit_query_length")) - 1;
        }
        this.target = this.getParameter("target");
        if (this.target == null || this.target.equals("")) {
            this.target = "_self";
        }
        if (this.getParameter("case_sensitive") == null || this.getParameter("case_sensitive").equals("")) {
            this.case_sensitive = false;
        }
        else {
            this.case_sensitive = new Boolean(this.getParameter("case_sensitive"));
        }
        this.default_url = this.getParameter("default_url");
        if (this.default_url == null) {
            this.default_url = "";
        }
        if (this.getParameter("search_implicitly") == null) {
            this.search_implicitly = true;
        }
        else {
            this.search_implicitly = new Boolean(this.getParameter("search_implicitly"));
        }
        this.nomatches = this.getParameter("no_matches");
        if (this.nomatches == null) {
            this.nomatches = "No matches";
        }
        this.searching = this.getParameter("searching");
        if (this.searching == null) {
            this.searching = "Searching...";
        }
        this.matchesfound = this.getParameter("matches_found");
        if (this.matchesfound == null) {
            this.matchesfound = "matches found";
        }
        (this.load_thread = new Thread(this)).start();
    }
    
    public void loading_err(final String s) {
        this.show_error(s);
    }
    
    public void run() {
        this.loading_in_progress = true;
        this.load_index(1);
        this.progress_bar.setPercent(1.0);
        this.load_wordlist(1);
        this.progress_bar.setPercent(1.0);
        this.remove(this.progress_bar);
        this.remove(this.loading_letter_caption);
        this.remove(this.loading_letter_label);
        final Insets insets = this.insets();
        final Dimension size = this.size();
        final int top = insets.top;
        final int n = size.height - insets.bottom;
        final int left = insets.left;
        final int n2 = size.width - insets.right;
        final Dimension preferredSize = this.query_field.preferredSize();
        this.result_list.resize(n2 - left, n - top - preferredSize.height);
        this.search_list.resize(n2 - left, n - top - preferredSize.height);
        this.loading_in_progress = false;
        this.no_matches_found = false;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof List || (event.target instanceof Button && String.valueOf(o).equals(this.open_caption))) {
            final String selectedItem = this.result_list.getSelectedItem();
            if (this.shareware && selectedItem.equals(this.promo2_text)) {
                try {
                    this.getAppletContext().showDocument(new URL(this.promo2_url), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
            else {
                this.load_url(selectedItem);
            }
        }
        else if (event.target instanceof TextField || (event.target instanceof Button && String.valueOf(o).equals(this.search_caption))) {
            final String text = this.query_field.getText();
            if (this.result_list.countItems() == 1 && !this.result_list.getItem(0).equals(this.nomatches) && this.result_list.getItem(0).equals(text)) {
                this.load_url(this.result_list.getItem(0));
                return true;
            }
            this.searching_implicitly = false;
            this.search(text);
            this.old_query = text;
        }
        return true;
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (this.search_implicitly && event.target instanceof TextField && this.query_field.getText().length() > this.min_query_size && !this.query_field.getText().equals(this.old_query)) {
            this.searching_implicitly = true;
            this.search(this.query_field.getText());
            this.old_query = this.query_field.getText();
        }
        return false;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 9) {
            if (event.modifiers == 1) {
                if (event.target instanceof TextField) {
                    this.submit_button.requestFocus();
                }
                else if (event.target instanceof List) {
                    this.query_field.requestFocus();
                }
                else if (event.target instanceof Button) {
                    this.result_list.requestFocus();
                }
            }
            else if (event.target instanceof TextField) {
                this.result_list.requestFocus();
            }
            else if (event.target instanceof List) {
                this.submit_button.requestFocus();
            }
            else if (event.target instanceof Button) {
                this.query_field.requestFocus();
            }
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 701) {
            this.submit_button.setLabel(this.open_caption);
        }
        else if (event.target instanceof TextField && event.id == 1004) {
            this.submit_button.setLabel(this.search_caption);
        }
        else if (event.id == 402 && event.key == 10 && event.target instanceof List) {
            this.load_url(this.result_list.getSelectedItem());
        }
        return super.handleEvent(event);
    }
    
    public URL get_input_url(final String s) {
        if (s.equals("") || s == null) {
            return null;
        }
        URL url = null;
        try {
            String lowerCase = "";
            try {
                lowerCase = s.substring(0, 4).toLowerCase();
            }
            catch (StringIndexOutOfBoundsException ex2) {}
            if (lowerCase.equals("http")) {
                url = new URL(s);
            }
            else if (s.substring(0, 1).equals("/")) {
                url = new URL(String.valueOf(this.getCodeBase().getProtocol()) + "://" + this.getCodeBase().getHost() + s);
            }
            else {
                url = new URL(String.valueOf(this.getCodeBase()) + s);
            }
        }
        catch (MalformedURLException ex) {
            this.show_error("cant load file " + url.toString() + "(" + ex + ")");
        }
        return url;
    }
    
    public void update_progress_bar(final double n, final int n2) {
        final double percent = n / new Double(n2);
        if (percent > 0.01) {
            this.progress_bar.setPercent(percent);
        }
    }
    
    public void load_index(int n) {
        final int n2 = 5;
        URL url = null;
        this.indexlist_hash = new Hashtable(26, 10.0f);
        int n3 = 0;
        final double n4 = 0.0;
        try {
            if (!this.gzip_missing) {
                BufferedReader bufferedReader = null;
                boolean b = false;
                if (!this.compressed_indexlist.equals("")) {
                    url = this.get_input_url(this.compressed_indexlist);
                    final URLConnection openConnection = url.openConnection();
                    n3 = openConnection.getContentLength();
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(openConnection.getInputStream())));
                        System.out.println("loading " + url.toString() + ", gzip compressed");
                    }
                    catch (IOException ex4) {
                        try {
                            final ZipInputStream zipInputStream = new ZipInputStream(openConnection.getInputStream());
                            System.out.println("loading " + url.toString() + ", zip compressed");
                            zipInputStream.getNextEntry();
                            bufferedReader = new BufferedReader(new InputStreamReader(zipInputStream));
                        }
                        catch (IOException ex5) {
                            b = true;
                        }
                    }
                }
                if (this.compressed_indexlist.equals("") || b) {
                    url = this.get_input_url(this.indexlist);
                    final URLConnection openConnection2 = url.openConnection();
                    n3 = openConnection2.getContentLength();
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(openConnection2.getInputStream()));
                        System.out.println("loading " + url.toString() + ", no compression");
                    }
                    catch (IOException ex) {
                        this.show_error("cant read index " + url.toString() + " (" + ex + ")");
                        return;
                    }
                }
                final String line = bufferedReader.readLine();
                final double n5 = n4 + line.length();
                if (n3 != 0) {
                    this.update_progress_bar(n5, n3);
                }
                this.wordlist_size = new Integer(line);
                String line2;
                while ((line2 = bufferedReader.readLine()) != null) {
                    final double n6 = line2.length();
                    if (n3 != 0) {
                        this.update_progress_bar(n6, n3);
                    }
                    final String substring = line2.substring(0, line2.indexOf(this.component_separator));
                    if (substring.length() > this.index_size) {
                        this.index_size = substring.length();
                    }
                    this.indexlist_hash.put(substring, line2.substring(line2.indexOf(this.component_separator) + 1));
                }
                bufferedReader.close();
                System.out.println("index length is " + this.index_size);
                return;
            }
            url = this.get_input_url(this.indexlist);
            final URLConnection openConnection3 = url.openConnection();
            openConnection3.getContentLength();
            DataInputStream dataInputStream = null;
            try {
                dataInputStream = new DataInputStream(openConnection3.getInputStream());
            }
            catch (IOException ex2) {
                this.show_error("cant read index " + url.toString() + " (" + ex2 + ")");
            }
            System.out.println("loading " + url.toString() + ", no compression");
            this.wordlist_size = new Integer(dataInputStream.readLine());
            String line3;
            while ((line3 = dataInputStream.readLine()) != null) {
                final String substring2 = line3.substring(0, line3.indexOf(this.component_separator));
                if (substring2.length() > this.index_size) {
                    this.index_size = substring2.length();
                }
                this.indexlist_hash.put(substring2, line3.substring(line3.indexOf(this.component_separator) + 1));
            }
            dataInputStream.close();
            System.out.println("index length is " + this.index_size);
        }
        catch (IOException ex3) {
            if (n <= n2) {
                System.out.println("could not read index on attempt " + n + " out of " + n2 + " (" + ex3 + ")");
                this.load_index(++n);
                return;
            }
            this.show_error("cant load index file " + url.toString() + ": " + ex3);
        }
        catch (StringIndexOutOfBoundsException ex6) {
            this.show_error("cant read poorly formated index");
        }
        catch (NumberFormatException ex7) {
            this.show_error("first line of index must be the size of the data file, attempting to use default setting");
        }
    }
    
    public void loading_letter(final String s) {
        final int index = s.indexOf(10);
        if (index != -1) {
            String substring = null;
            try {
                substring = s.substring(index + 1, index + 2);
            }
            catch (Exception ex) {}
            if (substring != null) {
                this.loading_letter_label.setText(substring.toUpperCase());
            }
        }
    }
    
    public void load_wordlist(int n) {
        final int n2 = 5;
        URL get_input_url = null;
        this.wordlist_chararray = new char[this.wordlist_size];
        final int n3 = 4096;
        int n4 = 0;
        final Date date = new Date();
        if (!this.gzip_missing && !this.compressed_wordlist.equals("")) {
            URL url = this.get_input_url(this.compressed_wordlist);
            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(url.openStream())));
                System.out.println("loading " + url.toString() + ", gzip compressed");
            }
            catch (IOException ex5) {
                try {
                    final ZipInputStream zipInputStream = new ZipInputStream(url.openStream());
                    System.out.println("loading " + url.toString() + ", zip compressed");
                    zipInputStream.getNextEntry();
                    bufferedReader = new BufferedReader(new InputStreamReader(zipInputStream));
                }
                catch (IOException ex6) {
                    try {
                        url = this.get_input_url(this.wordlist);
                        bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                        System.out.println("loading " + url.toString() + ", no compression");
                    }
                    catch (IOException ex) {
                        this.show_error("cant read data file " + url.toString() + " (" + ex + ")");
                        return;
                    }
                }
            }
            try {
                if (!this.loading_file.equals("")) {
                    this.progress_bar.setText(String.valueOf(this.loading_file) + " 2/2");
                }
                int read;
                while ((read = bufferedReader.read(this.wordlist_chararray, n4, n3)) != -1) {
                    n4 += read;
                    this.update_progress_bar(n4, this.wordlist_size);
                    this.loading_letter(new String(this.wordlist_chararray, n4 - 30, 30));
                }
                bufferedReader.close();
            }
            catch (IOException ex2) {
                if (n <= n2) {
                    System.out.println("could not read wordlist on attempt " + n + " out of " + n2 + " (" + ex2 + ")");
                    this.load_wordlist(++n);
                    return;
                }
                this.show_error("cant load data file" + url.toString() + ": " + ex2);
            }
            catch (ArrayIndexOutOfBoundsException ex7) {
                this.show_error("cant store data file in memory. Allocated array is too small.");
            }
        }
        else {
            BufferedInputStream bufferedInputStream = null;
            final byte[] array = new byte[n3];
            try {
                get_input_url = this.get_input_url(this.wordlist);
                bufferedInputStream = new BufferedInputStream(get_input_url.openStream());
                System.out.println("loading " + get_input_url.toString() + ", no compression");
            }
            catch (IOException ex3) {
                this.show_error("cant read data file " + get_input_url.toString() + " (" + ex3 + ")");
            }
            try {
                if (!this.loading_file.equals("")) {
                    this.progress_bar.setText(String.valueOf(this.loading_file) + " 2/2");
                }
                int read2;
                while ((read2 = bufferedInputStream.read(array, 0, n3)) != -1) {
                    final String s = new String(array, 0, 0, read2);
                    s.getChars(0, s.length(), this.wordlist_chararray, n4);
                    this.loading_letter(s);
                    n4 += read2;
                    this.update_progress_bar(n4, this.wordlist_size);
                }
                bufferedInputStream.close();
            }
            catch (IOException ex4) {
                if (n <= n2) {
                    System.out.println("could not read wordlist on attempt " + n + " out of " + n2 + " (" + ex4 + ")");
                    this.load_wordlist(++n);
                    return;
                }
            }
            catch (ArrayIndexOutOfBoundsException ex8) {
                this.show_error("cant store data file in memory. Allocated array is too small.");
            }
        }
        System.out.println("wordlist load took " + (new Date().getTime() - date.getTime()) + " milliseconds");
        System.out.println("read " + n4 + " bytes");
        if (n4 != this.wordlist_size) {
            this.show_error("List of wrong size. Expected " + this.wordlist_size + " bytes, read " + n4);
        }
    }
    
    public String get_component(final int n, int n2, final int n3) {
        while (this.wordlist_chararray[++n2] != this.entry_separator) {}
        final StringTokenizer stringTokenizer = new StringTokenizer(new String(this.wordlist_chararray, n, n2 - n), "\t", false);
        int n4 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (n4 == n3) {
                return nextToken;
            }
            ++n4;
        }
        return null;
    }
    
    public Object find(int first_match_pos, String lowerCase, final List list) {
        int n = 0;
        if (lowerCase.startsWith(this.old_query) && this.first_match_pos > 0 && list != null && !this.loading_in_progress) {
            if (this.no_matches_found) {
                return new Integer(0);
            }
            first_match_pos = this.first_match_pos;
        }
        final int n2 = first_match_pos;
        if (!this.case_sensitive) {
            lowerCase = lowerCase.toLowerCase();
        }
        this.no_matches_found = true;
        int n3 = 0;
        final char[] charArray = lowerCase.toCharArray();
        try {
            while (true) {
                for (int i = 0; i <= charArray.length - 1; ++i, ++first_match_pos) {
                    char lowerCase2 = this.wordlist_chararray[first_match_pos];
                    if (!this.case_sensitive) {
                        lowerCase2 = Character.toLowerCase(this.wordlist_chararray[first_match_pos]);
                    }
                    if (lowerCase2 == this.entry_separator) {
                        i = charArray.length - 1;
                    }
                    else if (lowerCase2 < charArray[i]) {
                        while (this.wordlist_chararray[++first_match_pos] != this.entry_separator) {}
                        i = charArray.length - 1;
                    }
                    else if (lowerCase2 == charArray[i]) {
                        if (i == charArray.length - 1) {
                            this.no_matches_found = false;
                            if (n == 0) {
                                this.first_match_pos = first_match_pos - charArray.length + 1;
                                n = 1;
                            }
                            if (list == null) {
                                return this.get_component(first_match_pos - i, first_match_pos, 1);
                            }
                            list.addItem(this.get_component(first_match_pos - i, first_match_pos, 0));
                            while (this.wordlist_chararray[++first_match_pos] != this.entry_separator) {}
                            ++n3;
                        }
                    }
                    else if (lowerCase2 > charArray[i]) {
                        System.out.print("searched " + (first_match_pos - n2) + " bytes, ");
                        return new Integer(n3);
                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return new Integer(n3);
        }
    }
    
    public void switch_lists() {
        this.result_list.hide();
        this.search_list.show();
        this.result_list.delItems(0, this.result_list.countItems() - 1);
        final List search_list = this.search_list;
        this.search_list = this.result_list;
        this.result_list = search_list;
    }
    
    public int get_starting_search_pos(final String s) {
        int intValue = 0;
        for (int i = (this.index_size > s.length()) ? s.length() : this.index_size; i >= 1; --i) {
            final String s2 = this.indexlist_hash.get(s.substring(0, i).toLowerCase());
            if (s2 != null) {
                intValue = new Integer(s2);
                break;
            }
        }
        return intValue;
    }
    
    public void search(final String s) {
        if (s.equals("")) {
            return;
        }
        System.out.print(String.valueOf(s) + " - ");
        final Date date = new Date();
        final int n = 0;
        final int get_starting_search_pos = this.get_starting_search_pos(s);
        if (get_starting_search_pos == -1) {
            this.nomatches();
            this.switch_lists();
            this.showStatus(String.valueOf(n) + " " + this.matchesfound);
            System.out.print("found " + n + " matches in ");
            return;
        }
        this.showStatus(this.searching);
        final boolean no_matches_found = this.no_matches_found;
        final int intValue = (int)this.find(get_starting_search_pos, s, this.search_list);
        if (no_matches_found && this.no_matches_found) {
            this.showStatus(String.valueOf(intValue) + " " + this.matchesfound);
            System.out.print("found " + intValue + " matches in ");
            return;
        }
        if (intValue == 0) {
            this.nomatches();
        }
        else if (intValue == 1 && !this.searching_implicitly) {
            this.query_field.setText(this.search_list.getItem(0));
            this.query_field.selectAll();
        }
        this.switch_lists();
        this.showStatus(String.valueOf(intValue) + " " + this.matchesfound);
        System.out.print("found " + intValue + " matches in ");
        System.out.println(String.valueOf(new Date().getTime() - date.getTime()) + " milliseconds");
    }
    
    public void nomatches() {
        this.search_list.addItem(this.nomatches);
        if (!this.searching_implicitly) {
            this.query_field.selectAll();
            this.query_field.requestFocus();
        }
    }
    
    public String munge_default_url(final String s) {
        final int index = this.default_url.indexOf("${word}");
        String s2;
        if (index != -1) {
            s2 = String.valueOf(this.default_url.substring(0, index)) + s + this.default_url.substring(this.default_url.indexOf(125) + 1);
        }
        else {
            s2 = this.default_url;
        }
        return s2;
    }
    
    public void load_url(final String s) {
        if (s == null || s.equals("")) {
            return;
        }
        final String s2 = (String)this.find(this.get_starting_search_pos(s), s, null);
        String munge_default_url;
        if (s2 != null) {
            munge_default_url = s2;
        }
        else {
            if (this.default_url.equals("")) {
                this.show_error("No URL to load for " + s);
                return;
            }
            munge_default_url = this.munge_default_url(s);
        }
        try {
            this.getAppletContext().showDocument(new URL(munge_default_url), this.target);
        }
        catch (MalformedURLException ex) {
            this.show_error("Bad URL: " + ex);
        }
        System.out.println("loading " + munge_default_url);
    }
    
    public void show_error(String string) {
        string = "ERROR: " + string;
        System.out.println(string);
        this.query_field.setText(string);
        this.showStatus(string);
    }
    
    public JSearchBox() {
        this.old_query = "";
        this.promo_text = "For more info visit";
        this.promo_url = "http://www.smacktech.com/";
        this.promo2_text = "JSearchBox by SmackTech";
        this.promo2_url = "http://www.smacktech.com/jsearchbox/about.html";
        this.wordlist_size = 25000;
        this.shareware = true;
        this.cursor_in_button = false;
        this.no_matches_found = false;
        this.entry_separator = '\n';
        this.component_separator = '\t';
    }
}
