import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Event;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Panel;
import display.CharDisplay;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CharDisplayTest extends Applet
{
    CharDisplay display;
    Panel buttons;
    Button info;
    Button chars;
    Button attr;
    Choice fonts;
    TextField from;
    
    public CharDisplayTest() {
        this.display = new CharDisplay(80, 24, "Courier", 14);
        this.buttons = new Panel();
        this.info = new Button("Information");
        this.chars = new Button("Character Table");
        this.attr = new Button("Attributes");
        this.fonts = new Choice();
        this.from = new TextField("0", 4);
    }
    
    private void Attributes() {
        int c = 4;
        int l = 8;
        this.Clear();
        this.display.putString(4, 1, "Character attributes", 4);
        this.display.putString(4, 3, "Normal", 0);
        this.display.putString(22, 3, "Bold", 1);
        this.display.putString(40, 3, "Underline", 2);
        this.display.putString(58, 3, "Invert", 4);
        this.display.putString(4, 5, "Black", 1032);
        this.display.putString(13, 5, "Red", 16);
        this.display.putString(22, 5, "Green", 24);
        this.display.putString(31, 5, "Yellow", 32);
        this.display.putString(40, 5, "Blue", 40);
        this.display.putString(49, 5, "Magenta", 48);
        this.display.putString(58, 5, "Cyan", 56);
        this.display.putString(67, 5, "LightGray", 64);
        for (int bg = 1; bg <= 8; ++bg) {
            for (int fg = 1; fg <= 8; ++fg) {
                for (int a = 0; a <= 7; ++a) {
                    this.display.putChar(c++, l, '@', fg << 3 | bg << 7 | a);
                    this.display.redraw();
                }
                ++c;
            }
            l += 2;
            c = 4;
        }
    }
    
    private void CharacterTable() {
        int ch = new Integer(this.from.getText());
        this.Clear();
        this.display.putString(4, 1, "Character Table", 4);
        for (int c = 1; c < 80; c += 6) {
            for (int l = 3; l < 23; ++l) {
                this.display.putString(c, l, String.valueOf(ch), 4);
                this.display.putChar(c + 4, l, (char)(ch++));
            }
        }
        this.display.markLine(3, 20);
        this.display.redraw();
    }
    
    private void Clear() {
        this.display.deleteArea(0, 0, 80, 24);
    }
    
    private void Info() {
        this.Clear();
        this.display.putString(4, 1, "CharDisplay.class Information", 4);
        this.display.putString(4, 3, "Version: " + this.display.version, 1);
        this.display.putString(4, 5, "This class implements several hardware features needed to implement");
        this.display.putString(4, 6, "a video terminal.");
        this.display.putString(4, 7, "This includes simple operations, such as putting and inserting single");
        this.display.putString(4, 8, "characters or strings on the screen, character attributes and colors.");
        this.display.putString(4, 9, "Special features like inserting lines, scrolling text up or down and");
        this.display.putString(4, 10, "defining scrollareas help implementing terminal emulations.");
        this.display.redraw();
    }
    
    public boolean handleEvent(final Event evt) {
        if (evt.target == this.info) {
            this.Info();
            return true;
        }
        if (evt.target == this.chars) {
            this.CharacterTable();
            return true;
        }
        if (evt.target == this.attr) {
            this.Attributes();
            return true;
        }
        if (evt.id == 1001 && (evt.target == this.fonts || evt.target == this.from)) {
            this.remove((Component)this.display);
            this.add("Center", (Component)(this.display = new CharDisplay(80, 24, this.fonts.getSelectedItem(), 12)));
            this.CharacterTable();
            this.layout();
            return true;
        }
        return false;
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        this.fonts.addItem("Helvetica");
        this.fonts.addItem("TimesRoman");
        this.fonts.addItem("Courier");
        this.fonts.addItem("Dialog");
        this.fonts.addItem("DialogInput");
        this.fonts.addItem("ZapfDingBats");
        this.fonts.addItem("default");
        this.buttons.add(this.info);
        this.buttons.add(this.chars);
        this.buttons.add(this.attr);
        this.buttons.add(this.fonts);
        this.buttons.add(this.from);
        this.add("North", this.buttons);
        this.display.setResizeStrategy(2);
        this.add("Center", (Component)this.display);
        this.Info();
    }
}
