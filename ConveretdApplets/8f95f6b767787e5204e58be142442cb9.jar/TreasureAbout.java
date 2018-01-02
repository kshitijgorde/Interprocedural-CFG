import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.TextArea;
import java.awt.FontMetrics;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class TreasureAbout extends Frame
{
    FontMetrics fmAbout;
    String strAbout;
    TextArea editAbout;
    
    public TreasureAbout() {
        super("TreasureAbout window");
        this.strAbout = "    Adventures in Four Dimensions\n\n    Copyright (c) 1979, 1996 James L. Dean\n\n    Version 4.3 released 7/25/96\n\n    This program may be distributed or used without payment to its author.\n\n    This ADVENTURE-like game was inspired by a program given by Roger Chaffee in his article \"Quest\" published in the July 1979 issue of Byte.  Unlike that program, this program allows multiple treasures and play in four dimensions.  In addition, at the beginning of a game, this program allows the player to specify a game number that changes the connections between the locations in an unpredictable manner.  Thus, the player has a whole series of games at his disposal.\n\n    The object of this game is to bring all the treasures to the starting point while visiting all the locations in the smallest number of moves.  Hint:  the distance from one room to the next is always the same.\n\n    This program was originally written in 1979 in Basic for a TANO Corporation Outpost having 64K of memory.  A later version was written in COBOL for CICS on an IBM Mainframe. This version is written in Java with the aid of Symantec Cafe.";
        this.setLayout(null);
        this.addNotify();
        this.resize(this.insets().left + this.insets().right + 395, this.insets().top + this.insets().bottom + 238);
        this.add(this.editAbout = new TextArea(9, 36));
        this.editAbout.reshape(this.insets().left + 9, this.insets().top + 8, 369, 192);
        this.resize(this.insets().left + this.insets().right + 385, this.insets().top + this.insets().bottom + 208);
        this.editAbout.setEditable(false);
        this.fmAbout = this.getFontMetrics(this.editAbout.getFont());
        this.displayFormattedAbout(this.strAbout, this.editAbout.getColumns());
    }
    
    public synchronized void show() {
        this.move(50, 50);
        super.show();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.hide();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void displayFormattedAbout(final String s, final int n) {
        final StringBuffer sb = new StringBuffer(3072);
        final StringBuffer sb2 = new StringBuffer(50);
        final StringBuffer sb3 = new StringBuffer(256);
        final StringBuffer sb4 = new StringBuffer(256);
        sb.setLength(0);
        sb.append("  ");
        int i = 0;
        final int length = s.length();
        sb2.setLength(0);
        sb2.append("  ");
        int n2 = 0;
        while (i < length) {
            while (i < length && s.charAt(i) == ' ') {
                ++i;
            }
            sb4.setLength(0);
            boolean b;
            char char1;
            for (b = false; i < length && !b && (char1 = s.charAt(i)) != ' '; b = (char1 == '\n'), ++i) {
                sb4.append(char1);
            }
            if (sb4.length() > 0) {
                if (n2 != 0) {
                    if (sb2.length() == 0) {
                        sb.append("  ");
                    }
                    else {
                        sb.append("\n  ");
                    }
                    sb2.setLength(0);
                    sb2.append("  ");
                    n2 = 0;
                }
                if (b) {
                    n2 = 1;
                    sb4.setLength(sb4.length() - 1);
                }
            }
            if (sb4.length() > 0) {
                if (sb4.charAt(sb4.length() - 1) == '.') {
                    sb3.setLength(0);
                    sb3.append((Object)sb2);
                    sb3.append((Object)sb4);
                    sb3.append("  ");
                    if (this.fmAbout.stringWidth(sb3.toString()) <= this.editAbout.preferredSize().width - 36) {
                        sb.append((Object)sb4).append("  ");
                        sb2.append((Object)sb4).append("  ");
                    }
                    else {
                        sb3.setLength(sb3.length() - 2);
                        if (this.fmAbout.stringWidth(sb3.toString()) <= this.editAbout.preferredSize().width - 36) {
                            sb.append((Object)sb4).append('\n');
                            sb2.setLength(0);
                        }
                        else {
                            sb.append('\n');
                            while (sb4.length() > 0) {
                                if (this.fmAbout.stringWidth(sb4.toString()) > this.editAbout.preferredSize().width - 36) {
                                    sb3.setLength(0);
                                    int n3 = 0;
                                    while (this.fmAbout.stringWidth(sb3.toString()) <= this.editAbout.preferredSize().width - 36) {
                                        sb3.append(sb4.charAt(n3++));
                                    }
                                    --n3;
                                    sb.append(sb4.toString().substring(0, n3));
                                    sb.append('\n');
                                    sb3.setLength(0);
                                    sb3.append(sb4.toString().substring(n3));
                                    sb4.setLength(0);
                                    sb4.append((Object)sb3);
                                }
                                else {
                                    sb.append((Object)sb4);
                                    sb2.setLength(0);
                                    sb2.append((Object)sb4);
                                    sb3.setLength(0);
                                    sb3.append((Object)sb2);
                                    sb3.append("  ");
                                    if (this.fmAbout.stringWidth(sb3.toString()) <= this.editAbout.preferredSize().width - 36) {
                                        sb.append("  ");
                                        sb2.append("  ");
                                    }
                                    else {
                                        sb.append('\n');
                                        sb2.setLength(0);
                                    }
                                    sb4.setLength(0);
                                }
                            }
                        }
                    }
                }
                else {
                    sb3.setLength(0);
                    sb3.append((Object)sb2);
                    sb3.append((Object)sb4);
                    sb3.append(' ');
                    if (this.fmAbout.stringWidth(sb3.toString()) <= this.editAbout.preferredSize().width - 36) {
                        sb.append((Object)sb4).append(' ');
                        sb2.append((Object)sb4).append(' ');
                    }
                    else {
                        sb3.setLength(sb3.length() - 2);
                        if (this.fmAbout.stringWidth(sb3.toString()) <= this.editAbout.preferredSize().width - 36) {
                            sb.append((Object)sb4).append('\n');
                            sb2.setLength(0);
                        }
                        else {
                            sb.append('\n');
                            while (sb4.length() > 0) {
                                if (sb4.length() > n) {
                                    sb3.setLength(0);
                                    int n4 = 0;
                                    while (this.fmAbout.stringWidth(sb3.toString()) <= this.editAbout.preferredSize().width - 36) {
                                        sb3.append(sb4.charAt(n4++));
                                    }
                                    --n4;
                                    sb.append(sb4.toString().substring(0, n4));
                                    sb.append('\n');
                                    sb3.setLength(0);
                                    sb3.append(sb4.toString().substring(n4));
                                    sb4.setLength(0);
                                    sb4.append((Object)sb3);
                                }
                                else {
                                    sb.append((Object)sb4);
                                    sb2.setLength(0);
                                    sb2.append((Object)sb4);
                                    sb3.setLength(0);
                                    sb3.append((Object)sb2);
                                    sb3.append(' ');
                                    if (this.fmAbout.stringWidth(sb3.toString()) <= this.editAbout.preferredSize().width - 36) {
                                        sb.append(' ');
                                        sb2.append(' ');
                                    }
                                    else {
                                        sb.append('\n');
                                        sb2.setLength(0);
                                    }
                                    sb4.setLength(0);
                                }
                            }
                        }
                    }
                }
            }
        }
        this.editAbout.setText(sb.toString());
    }
}
