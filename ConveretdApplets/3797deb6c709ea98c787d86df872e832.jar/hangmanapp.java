import java.awt.Graphics;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Color;
import java.util.StringTokenizer;
import java.util.Random;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class hangmanapp extends Applet
{
    private String[] words;
    private Random rand;
    private int whichWord;
    private word newWord;
    private final String FileDelimeter = ":";
    
    public hangmanapp() {
        this.words = new String[] { "money:thing", "guava:fruit", "youthful:trait", "grace:trait", "falcon:animal", "honesty:trait", "relationship:thing", "party:event", "house:place", "baptism:event", "sensitive:trait", "computer:thing", "school:place", "monkey:animal", "stereo:thing", "piano:thing", "ring:thing", "lamp:thing", "picture:thing", "gym:place", "beach:place", "car:thing", "box:thing", "prison:place", "mask:thing", "chair:thing", "table:thing", "key:thing", "towel:thing", "compassionate:trait", "generous:trait", "intelligent:trait", "funny:trait", "book:thing", "comforter:thing", "horse:animal", "hose:thing", "england:country", "canada:country", "tennis:sport", "volleyball:sport", "baseball:sport", "basketball:sport", "brazil:country", "curling:sport", "japan:country", "volcano:thing", "fire:thing", "skirt:thing", "apple:thing", "orange:thing", "ostrich:animal", "policeman:occupation", "doctor:occupation", "teacher:occupation", "namibia:country", "kayak:palindrome", "civic:palindrome", "level:palindrome", "racecar:palindrome", "rotator:palindrome", "sexes:palindrome", "misogynist:trait", "esoteric:trait", "integrity:trait", "umbrella:thing", "cosmopolitan:drink", "drapes:thing", "ticket:thing", "vacation:event", "needle:thing", "movie:thing", "charismatic:trait", "voluptuous:trait", "tequilla:drink", "vodka:drink", "cigarettes:thing", "trampoline:thing", "divorce:thing", "giraffe:animal", "clock:thing", "streetcar:thing", "kaleidoscope:thing", "camera:thing", "toaster:thing", "utensils:thing", "geography:subject", "mathematics:subject", "salad:food", "pasta:food", "karate:sport", "mirror:thing", "rabbit:animal", "plumber:occupation", "considerate:trait", "crayon:thing", "apartment:place", "ladder:thing", "sandwich:food", "conscientious:trait", "shark:animal", "lifeguard:occupation", "receptionist:occupation", "programmer:occupation" };
        this.rand = new Random();
        this.whichWord = this.rand.nextInt() % this.words.length;
        if (this.whichWord < 0) {
            this.whichWord = -this.whichWord;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(this.words[this.whichWord], ":");
        this.newWord = new word(stringTokenizer.nextToken(), stringTokenizer.nextToken());
    }
    
    public void init() {
        this.removeAll();
        this.setBackground(Color.white);
        int int1;
        try {
            int1 = Integer.parseInt(this.getParameter("numguesses"));
        }
        catch (Exception ex) {
            System.out.println("No max num guesses given");
            int1 = 5;
        }
        final HangmanActionListener hangmanActionListener = new HangmanActionListener(int1);
        hangmanActionListener.setWord(this.newWord);
        hangmanActionListener.setApplet(this);
        char c = 'a';
        for (int i = 0; i < 26; ++i) {
            final Button button = new Button(new String(new Character(c).toString()));
            button.addActionListener(hangmanActionListener);
            this.add(button);
            ++c;
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawString("TOPIC: " + this.newWord.getTopic(), 100, 115);
        graphics.drawString(this.newWord.toString(), 100, 130);
        graphics.drawString(this.newWord.getTried(), 100, 145);
    }
}
