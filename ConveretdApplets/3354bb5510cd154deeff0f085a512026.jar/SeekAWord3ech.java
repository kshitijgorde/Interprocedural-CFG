import java.awt.Event;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.List;
import java.awt.Font;
import java.awt.Button;
import java.applet.AudioClip;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class SeekAWord3ech extends Applet
{
    AudioClip found_sound;
    AppletUtil3 aut;
    boolean[] word_found;
    Button rescramble;
    Button find;
    char[][] letters;
    Font f;
    int width;
    int height;
    int rows;
    int columns;
    int cell_size;
    int placed_words;
    List wordlist;
    SeekCanvas3 canvas;
    String[] candidates;
    String[] words;
    Vector found_coords;
    Vector word_coords;
    
    public void init() {
        this.aut = new AppletUtil3(this);
        this.width = this.size().width;
        this.height = this.size().height;
        if (this.getParameter("ROWS") != null) {
            this.rows = this.aut.getRandom(this.getParameter("ROWS"));
        }
        if (this.getParameter("COLUMNS") != null) {
            this.columns = this.aut.getRandom(this.getParameter("COLUMNS"));
        }
        if (this.getParameter("FOUND.SOUND") != null) {
            try {
                this.found_sound = this.getAudioClip(this.getDocumentBase(), this.getParameter("FOUND.SOUND").trim());
            }
            catch (Exception ex) {}
        }
        this.cell_size = Math.min(this.width / this.columns, this.height / this.rows);
        this.letters = new char[this.rows][this.columns];
        final StringTokenizer stringTokenizer = new StringTokenizer((this.getParameter("WORDLIST") != null) ? this.getParameter("WORDLIST") : "Bridget Chloe Claire Gwyneth Judith Jennifer Kate Kirsten Lacey Larisa Mischa Natalie Neve Parker Winona");
        final int countTokens = stringTokenizer.countTokens();
        this.candidates = new String[countTokens];
        for (int i = 0; i < countTokens; ++i) {
            this.candidates[i] = stringTokenizer.nextToken().toUpperCase();
        }
        this.constructBoard();
        this.wordlist.setMultipleSelections(true);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(0, 0));
        panel.setBackground(this.aut.makeColor(this.getParameter("BGCOLOR"), Color.lightGray));
        panel.setForeground(this.aut.makeColor(this.getParameter("FGCOLOR"), Color.black));
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(0, 1, 0, 0));
        panel2.add(this.find);
        panel2.add(this.rescramble);
        panel.add("Center", this.wordlist);
        panel.add("South", panel2);
        (this.canvas = new SeekCanvas3(this, this.cell_size * this.columns, this.cell_size * this.rows)).repaint();
        this.canvas.setBackground(this.aut.makeColor(this.getParameter("CANVAS.BGCOLOR"), Color.lightGray));
        this.canvas.setForeground(this.aut.makeColor(this.getParameter("CANVAS.FGCOLOR"), Color.black));
        this.canvas.setFont(this.aut.makeFont(this.getParameter("CANVAS.FONTNAME"), this.getParameter("CANVAS.FONTSTYLE"), this.getParameter("CANVAS.FONTSIZE")));
        this.setLayout(new BorderLayout(0, 0));
        this.add("West", this.canvas);
        this.add("Center", panel);
    }
    
    public void constructBoard() {
        if (this.getParameter("AUTHOR").equals("Eric Harshbarger, http://www.ericharshbarger.org") && this.getParameter("COPYRIGHT").equals("SeekAWord applet, Copyright 1998, Eric Harshbarger")) {
            for (int i = 0; i < this.rows; ++i) {
                for (int j = 0; j < this.columns; ++j) {
                    this.letters[i][j] = '\0';
                }
            }
            this.found_coords.removeAllElements();
            this.word_coords.removeAllElements();
            int n = 0;
            double n2 = 1.0;
            int placed_words = 0;
            final int[] array = new int[9];
            for (int k = 0; k < 9; ++k) {
                array[k] = 0;
            }
            while (placed_words < this.candidates.length && n < 5000) {
                final int n3 = (int)(Math.random() * 9.0);
                if (n3 != 4 && array[n3] < n2 / 6.0) {
                    final int n4 = n3 % 3 - 1;
                    final int n5 = n3 / 3 - 1;
                    if (this.putWord(placed_words, n4, n5, (int)(Math.random() * (this.columns - Math.abs(n4) * this.candidates[placed_words].length()) + Math.max(0, -n4) * this.candidates[placed_words].length()), (int)(Math.random() * (this.rows - Math.abs(n5) * this.candidates[placed_words].length()) + Math.max(0, -n5) * this.candidates[placed_words].length()))) {
                        final int[] array2 = array;
                        final int n6 = n3;
                        ++array2[n6];
                        ++n2;
                        ++placed_words;
                    }
                    ++n;
                }
            }
            this.placed_words = placed_words;
            for (int l = 0; l < this.rows; ++l) {
                for (int n7 = 0; n7 < this.columns; ++n7) {
                    if (this.letters[l][n7] == '\0') {
                        this.letters[l][n7] = (char)(Math.random() * 26.0 + 65.0);
                    }
                }
            }
            this.word_found = new boolean[this.placed_words];
            for (int n8 = 0; n8 < this.word_found.length; ++n8) {
                this.word_found[n8] = false;
            }
            this.makeList();
            return;
        }
        System.out.println("AUTHOR & COPYRIGHT parameters are incorrect.");
    }
    
    public boolean putWord(final int n, final int n2, final int n3, final int n4, final int n5) {
        final int length = this.candidates[n].length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            final char char1 = this.candidates[n].charAt(i);
            if (this.letters[n5 + i * n3][n4 + i * n2] != '\0' && this.letters[n5 + i * n3][n4 + i * n2] != char1) {
                return false;
            }
            array[i] = char1;
            if (i == length - 1) {
                for (int j = 0; j < length; ++j) {
                    this.letters[n5 + j * n3][n4 + j * n2] = array[j];
                }
                this.word_coords.addElement(new int[] { n4, n5, n4 + i * n2, n5 + i * n3 });
                return true;
            }
        }
        return false;
    }
    
    public void destroy() {
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            if (event.target == this.rescramble) {
                this.constructBoard();
            }
            else if (event.target == this.find) {
                final String[] selectedItems = this.wordlist.getSelectedItems();
                for (int i = 0; i < selectedItems.length; ++i) {
                    for (int j = 0; j < this.placed_words; ++j) {
                        if (selectedItems[i].equals(this.candidates[j])) {
                            this.word_found[j] = true;
                            this.found_coords.addElement(this.word_coords.elementAt(j));
                            break;
                        }
                    }
                }
                this.makeList();
            }
            this.canvas.drawFoundWords();
        }
        return super.handleEvent(event);
    }
    
    public void makeList() {
        this.wordlist.clear();
        for (int i = 0; i < this.placed_words; ++i) {
            if (!this.word_found[i]) {
                this.wordlist.addItem(this.candidates[i]);
            }
        }
    }
    
    public void playFoundSound() {
        if (this.found_sound != null) {
            this.found_sound.play();
        }
    }
    
    public int getPlacedWordCount() {
        return this.placed_words;
    }
    
    public Vector getFoundCoords() {
        return this.found_coords;
    }
    
    public void addFoundCoords(final int[] array) {
        this.found_coords.addElement(array);
    }
    
    public String getWord(final int n) {
        return this.candidates[n];
    }
    
    public boolean getWordFound(final int n) {
        return this.word_found[n];
    }
    
    public void setWordFound(final int n, final boolean b) {
        this.word_found[n] = b;
    }
    
    public char[][] getLetters() {
        return this.letters;
    }
    
    public SeekAWord3ech() {
        this.rescramble = new Button("Rescramble");
        this.find = new Button("Find Words");
        this.rows = 15;
        this.columns = 15;
        this.wordlist = new List();
        this.found_coords = new Vector(17);
        this.word_coords = new Vector(17);
    }
}
