// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.Graphics;
import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Panel;

public class NotePanel extends Panel
{
    public Color HELP_COLOR;
    static final int HELP_TEXT_LEFT = 3;
    Font helpFont;
    FontMetrics helpFontMetrics;
    boolean helpDown;
    NoteText currentNote;
    int currentNoteHeight;
    Rectangle box;
    Color brown;
    
    public NotePanel(final int n, final int n2, final int n3, final int n4) {
        this.HELP_COLOR = new Color(204, 204, 153);
        this.helpFont = new Font("Helvetica", 0, 12);
        this.helpDown = false;
        this.brown = new Color(153, 102, 51);
        this.box = new Rectangle(n + 5, n2, n3 - 20, n4);
        this.setLayout(null);
        this.helpFontMetrics = this.getFontMetrics(this.helpFont);
    }
    
    public void close() {
        this.currentNote = null;
        this.repaint();
    }
    
    public void displayHelp(final NoteText currentNote, final int currentNoteHeight) {
        this.currentNote = currentNote;
        this.currentNoteHeight = currentNoteHeight;
        this.repaint();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.close();
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.currentNote != null) {
            this.currentNote.draw(graphics, 0, 15);
        }
    }
}
