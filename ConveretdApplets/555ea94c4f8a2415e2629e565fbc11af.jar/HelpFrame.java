import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Point;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class HelpFrame extends Dialog
{
    private static final String[] text;
    private static final int numLines = 61;
    boolean fComponentsAdjusted;
    TextArea textArea1;
    
    public HelpFrame(final Frame frame, final String s) {
        super(frame, s);
        this.fComponentsAdjusted = false;
        this.textArea1 = new TextArea();
        this.setLayout(null);
        this.setBackground(new Color(0, 128, 0));
        this.setSize(564, 432);
        this.setVisible(false);
        this.add(this.textArea1);
        this.textArea1.setBounds(12, 12, 540, 408);
        this.addWindowListener(new SymWindow());
        for (int i = 0; i < 61; ++i) {
            this.textArea1.append(String.valueOf(HelpFrame.text[i]) + "\n");
        }
    }
    
    public void setVisible(final boolean visible) {
        if (visible) {
            final Rectangle bounds = this.getParent().getBounds();
            final Rectangle bounds2 = this.getBounds();
            this.setLocation(bounds.x + (bounds.width - bounds2.width) / 2, bounds.y + (bounds.height - bounds2.height) / 2);
        }
        super.setVisible(visible);
    }
    
    public void addNotify() {
        final Dimension size = this.getSize();
        super.addNotify();
        if (this.fComponentsAdjusted) {
            return;
        }
        final Insets insets = this.getInsets();
        this.setSize(insets.left + insets.right + size.width, insets.top + insets.bottom + size.height);
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Point location = components[i].getLocation();
            location.translate(insets.left, insets.top);
            components[i].setLocation(location);
        }
        this.fComponentsAdjusted = true;
    }
    
    void HelpFrame_WindowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
        this.dispose();
    }
    
    static {
        text = new String[] { "Java Blackjack by Robert Kosirog", "rskosirog@yahoo.com", "", "--------------------------------", "You can play 1 or 2 hands.  Two hands must bet at least twice the minimum", "Start a hand by clicking the chip denominations you want to bet(Click the", "blank if there are no chips of a certain denomination).  You can cancel the", "bet by right clicking the chips.  After a short delay the cards will be", "dealt.", "A box will appear around the current hand, and buttons will allow you to", "select the action you want.  Your options are:", "Hit------Take another card", "Stand----Stop with the total you have", "Split----If you have a pair, you can split.  The two cards will be split and used", "         to start two seperate hands.  Another bet, equal to the first is placed.", "Double---Your original bet is doubled, any you only get one additional card.", "Surrender--(Right Click cards to surrender) You lose one-half your bet and the", "           hand is over.", "", "-------------------------------", "INSURANCE", "If the dealers second card is an Ace, you will have the chance to take", "insurance.  You can bet up to one-half the original bet for each hand", "If the dealer has a blackjack, the insurance bet pays 2-1.  If the dealer", "doesn't have a blackjack, the insurance bets lose, and the hand continues", "as normal.  If you have your own blackjack, taking insurance pays even money", "on your original bet, no matter what the dealer has.", "", "-------------------------------", "CONTROLS", "The 'Play 2 Hands' checkbox allows you to play two hands.", "", "The 'Show Totals' checkbox causes the totals to be visible.", "", "The 'Strategy' checkbox causes your actions to be checked against the", "correct stragegy, and informs you if you make a mistake.", "", "The 'Help' button brings up this window", "", "The 'Options'/'Strategy' button has two uses.  Before a hand starts, it", "brings up a dialog box where you can set various blackjack rules.  You", "can set the limits, number of decks, and shuffle point.  You can", "also see the strategy for those particular settings.", "", "After a hand has started, the 'Options' button changes to the 'Strategy'", "button, and brings up the strategy for the current settings.", "The Strategy window shows what you should do with each hand against", "the dealers upcard.", "", "-----------------------------", "Comments or Suggestions:  rskosirog@yahoo.com", "", "This program may be freely distributed and published", "on Internet web pages, provided you notify the author", "by e-mail at rskosirog@yahoo.com.  See the license.txt file.", "", "You may not may modify or disassemble this program,", "or use any of the classes for any other purpose without the", "permission of the author.", "", "Good Luck" };
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == HelpFrame.this) {
                HelpFrame.this.HelpFrame_WindowClosing(windowEvent);
            }
        }
    }
}
