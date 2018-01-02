import java.awt.Event;
import java.util.Date;
import java.awt.TextComponent;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.Label;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class VasJava2HTML extends Applet
{
    Label lState;
    TextArea taContents;
    Button bGo;
    public Component[] compo;
    StringBuffer initial;
    String[] keywords;
    char[] separators;
    char[] figures;
    char[] special;
    String[] strSpecial;
    String[] strNumber;
    String[] strEndNumber;
    String strKey;
    String strEndKey;
    String strComment1;
    String strEndComment1;
    String strComment2;
    String strEndComment2;
    String strString;
    String strEndString;
    
    public VasJava2HTML() {
        this.keywords = new String[] { "abstract", "boolean", "break", "byte", "byvalue", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "extends", "false", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "null", "package", "private", "protected", "public", "return", "short", "static", "super", "switch", "synchronized", "this", "threadsafe", "throw", "transient", "true", "try", "void", "while" };
        this.separators = new char[] { ' ', '\n', '\t', ';', ',', '(', ')', '{', '}', '=', '<', '>', '-', '+', '*', '/', '%', '^', '!', '?', '|', '&', ':', '~', '[', ']', '\'' };
        this.figures = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'x', '.', 'l' };
        this.special = new char[] { '\"', '&', '<', '>' };
        this.strSpecial = new String[] { "quot;", "amp;", "lt;", "gt;" };
        this.strNumber = new String[] { "<FONT COLOR=\"#CAFEBA\">", "<FONT COLOR=\"#0000BF\">", "<FONT COLOR=\"#CAFEBA\">", "<FONT COLOR=\"#BB00BB\">", "<FONT COLOR=\"#FFFFFF\">", "<FONT COLOR=\"#FFFFFF\">" };
        this.strEndNumber = new String[] { "</FONT>", "</FONT>", "</FONT>", "</FONT>", "</FONT>", "</FONT>" };
        this.strKey = new String("<FONT COLOR=\"#0000BF\">");
        this.strEndKey = new String("</FONT>");
        this.strComment1 = new String("<FONT COLOR=\"#007F00\">");
        this.strEndComment1 = new String("</FONT>");
        this.strComment2 = new String("<FONT COLOR=\"#007F00\">");
        this.strEndComment2 = new String("</FONT>");
        this.strString = new String("<FONT COLOR=\"#BF0000\">");
        this.strEndString = new String("</FONT>");
        this.setLayout(null);
        this.setFont(new Font("Courier", 0, 12));
        this.setBackground(Color.lightGray);
        (this.lState = new Label("Paste in TextArea your Java code and make Go!")).reshape(10, 10, 360, 25);
        this.add(this.lState);
        (this.bGo = new Button("Go !")).reshape(385, 10, 70, 30);
        this.add(this.bGo);
        (this.taContents = new TextArea()).reshape(20, 45, 435, 210);
        this.add(this.taContents);
        this.taContents.appendText("/*(C),vasile@club-internet.fr*/\nimport java.applet.Applet;\nimport java.awt.*;\n\npublic class VasTest extends Applet {\n  int hex=0x32;//C++ comment\n  int integ=32;//it works :-)\n  int OCTAL=077;\n  float floatN=0.13;\n  String string=\"String\";\n}//end VasTest");
        this.resize(470, 340);
    }
    
    public void init() {
        (this.compo = new Component[2])[0] = this.taContents;
        this.compo[1] = this.bGo;
        this.compo[0].requestFocus();
        if (this.compo[0] instanceof TextComponent) {
            ((TextComponent)this.compo[0]).selectAll();
        }
    }
    
    void transform() {
        final Date date = new Date();
        this.initial = new StringBuffer(this.taContents.getText());
        boolean b = false;
        int n = 0;
        for (int i = 0; i < this.initial.length(); ++i) {
            if (i % 100 == 0) {
                this.lState.setText("Analyzing character " + i + "/" + this.initial.length());
            }
            final char char1 = this.initial.charAt(i);
            switch (char1) {
                case 34: {
                    if (i != 0 && this.initial.charAt(i - 1) != '\\' && this.initial.charAt(i - 1) != '\'') {
                        i = this.setSpeFont(i + 1, this.strString, "\"", this.strEndString);
                        break;
                    }
                    break;
                }
                case 42: {
                    if (i != 0 && this.initial.charAt(i - 1) == '/') {
                        i = this.setSpeFont(i, this.strComment1, "*/", this.strEndComment1);
                        if (i < this.initial.length()) {
                            b = this.inSep(this.initial.charAt(i));
                        }
                        n = i;
                        break;
                    }
                    break;
                }
                case 47: {
                    if (i != 0 && this.initial.charAt(i - 1) == '/') {
                        i = this.setSpeFont(i, this.strComment2, "\n", this.strEndComment2);
                        if (i < this.initial.length()) {
                            b = this.inSep(this.initial.charAt(i));
                        }
                        n = i;
                        break;
                    }
                    break;
                }
                default: {
                    if (b != this.inSep(char1)) {
                        if (b) {
                            n = i;
                        }
                        else {
                            i += this.setKey(n, i);
                        }
                        b = !b;
                    }
                    i += this.setSpecial(char1, i);
                    break;
                }
            }
        }
        this.initial.insert(0, "<HTML>\n<HEAD>\n<TITLE>Vasile's VasJava2HTML Output</TITLE>\n<META NAME=\"GENERATOR\" CONTENT=\"VasJava2HTML\">\n</HEAD>\n<BODY BGCOLOR=\"#B4B88C\" TEXT=\"#000000\">\n<PRE>");
        this.initial.append("\n</PRE>\n&copy; 1997,98 made with <A HREF=\"http://www.chez.com/vasile/java2/VasJava2HTML.html\">VasJava2HTML</A><BR><BR></BODY>\n</HTML>");
        this.taContents.setText(this.initial.toString());
        this.lState.setText("Completed in " + (new Date().getTime() - date.getTime()) + " millisec. " + this.initial.length() + " characters.");
    }
    
    int setKey(final int n, final int n2) {
        final int n3 = n2 - n;
        if (n3 < 1) {
            return 0;
        }
        final char[] array = new char[n3];
        this.initial.getChars(n, n2, array, 0);
        for (int i = 0; i < this.keywords.length; ++i) {
            if (n3 == this.keywords[i].length()) {
                int n4;
                for (n4 = 0; n4 < n3 && array[n4] == this.keywords[i].charAt(n4); ++n4) {}
                if (n4 == n3) {
                    this.initial.insert(n2, this.strEndKey);
                    this.initial.insert(n, this.strKey);
                    return this.strEndKey.length() + this.strKey.length();
                }
            }
        }
        int j = 0;
        while (j < n3) {
            array[j] = Character.toLowerCase(array[j]);
            if (!this.inFigures(array[j])) {
                if (n3 > 5 && j == 0 && this.initial.charAt(n) == 't' && array[1] == 'h' && array[2] == 'i' && array[3] == 's' && array[4] == '.') {
                    this.initial.insert(n + 4, this.strEndKey);
                    this.initial.insert(n, this.strKey);
                    return this.strEndKey.length() + this.strKey.length();
                }
                return 0;
            }
            else {
                ++j;
            }
        }
        if (array[0] >= 'a' && array[0] < 'z') {
            return 0;
        }
        return this.putNumberType(array, n, n2);
    }
    
    boolean inFigures(final char c) {
        for (int i = 0; i < this.figures.length; ++i) {
            if (c == this.figures[i]) {
                return true;
            }
        }
        return false;
    }
    
    int putNumberType(final char[] array, final int n, final int n2) {
        int n3 = 0;
        if (array[0] == '0' && array.length > 1) {
            if (array[1] == 'x') {
                n3 = 1;
            }
            else {
                n3 = 3;
            }
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == '.' || array[i] == 'e') {
                n3 = 4;
                break;
            }
        }
        switch (array[array.length - 1]) {
            case 'd': {
                n3 = 4;
                break;
            }
            case 'f': {
                n3 = 5;
                break;
            }
            case 'l': {
                n3 = 2;
                break;
            }
        }
        if (n3 != 0 && array.length < 2) {
            return 0;
        }
        this.initial.insert(n2, this.strEndNumber[n3]);
        this.initial.insert(n, this.strNumber[n3]);
        return this.strNumber[n3].length() + this.strEndNumber[n3].length();
    }
    
    boolean inSep(final char c) {
        for (int i = 0; i < this.separators.length; ++i) {
            if (c == this.separators[i]) {
                return true;
            }
        }
        return false;
    }
    
    int setSpeFont(int i, final String s, final String s2, final String s3) {
        final int length = s2.length();
        final int n = i;
        final char char1 = s2.charAt(0);
        while (i < this.initial.length() - length) {
            final char char2 = this.initial.charAt(i);
            if (char2 == char1) {
                int n2;
                for (n2 = 1; n2 < length && this.initial.charAt(i + n2) == s2.charAt(n2); ++n2) {}
                if (n2 == length && this.initial.charAt(i - 1) != '\\' && this.initial.charAt(i - 1) != '\'') {
                    i += length;
                    break;
                }
            }
            i += this.setSpecial(char2, i);
            ++i;
        }
        if (i == this.initial.length() - length) {
            i += length;
        }
        i += this.setSpecial(this.initial.charAt(i - 1), i - 1);
        this.initial.insert(i, s3);
        i += this.setSpecial(this.initial.charAt(n - 1), n - 1);
        this.initial.insert(n - 1, s);
        i += s3.length() + s.length();
        return i;
    }
    
    int setSpecial(final char c, final int n) {
        for (int i = 0; i < this.special.length; ++i) {
            if (c == this.special[i]) {
                this.initial.setCharAt(n, '&');
                this.initial.insert(n + 1, this.strSpecial[i]);
                return this.strSpecial[i].length();
            }
        }
        return 0;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 401 && event.key == 9) {
            for (int i = 0; i < this.compo.length; ++i) {
                if (event.target == this.compo[i]) {
                    do {
                        i = (i + (event.shiftDown() ? (this.compo.length - 1) : 1)) % this.compo.length;
                    } while (!this.compo[i].isEnabled());
                    this.compo[i].requestFocus();
                    if (this.compo[i] instanceof TextComponent) {
                        ((TextComponent)this.compo[i]).selectAll();
                    }
                    return true;
                }
            }
            return false;
        }
        if (event.target == this.bGo && (event.arg == this.bGo.getLabel() || (event.id == 402 && event.key == 10))) {
            this.transform();
            return true;
        }
        return false;
    }
}
