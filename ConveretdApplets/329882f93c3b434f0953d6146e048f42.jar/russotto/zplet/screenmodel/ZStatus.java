// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.screenmodel;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Panel;

public class ZStatus extends Panel
{
    boolean timegame;
    boolean initialized;
    boolean chronograph;
    String location;
    int score;
    int turns;
    int hours;
    int minutes;
    Label Right;
    Label Left;
    
    public ZStatus() {
        this.setLayout(new BorderLayout());
        this.add("East", this.Right = new Label());
        this.add("West", this.Left = new Label());
        this.chronograph = false;
    }
    
    public void update_score_line(final String s, final int score, final int turns) {
        this.timegame = false;
        this.location = s;
        this.score = score;
        this.turns = turns;
        this.Left.setText(s);
        this.Right.setText(score + "/" + turns);
        this.layout();
        this.repaint();
    }
    
    public void update_time_line(final String s, int hours, final int minutes) {
        this.timegame = true;
        this.location = s;
        this.hours = hours;
        this.minutes = minutes;
        this.Left.setText(s);
        if (this.chronograph) {
            this.Right.setText(hours + ":" + minutes);
        }
        else {
            String s2;
            if (hours < 12) {
                s2 = "AM";
            }
            else {
                s2 = "PM";
            }
            hours %= 12;
            if (hours == 0) {
                hours = 12;
            }
            this.Right.setText(hours + ":" + minutes + s2);
        }
        this.layout();
        this.repaint();
    }
    
    public Dimension minimumSize() {
        return new Dimension(100, 10);
    }
    
    public Dimension preferredSize() {
        return new Dimension(500, 20);
    }
}
