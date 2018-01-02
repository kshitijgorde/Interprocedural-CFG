import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class board extends Panel
{
    boolean shuffled;
    int GRIDX;
    int GRIDY;
    int MXKNOP;
    gamebutton[] b;
    picture pic;
    int leeg;
    int moves;
    Color[] leegkleur;
    int leegkleurnr;
    imgslide a;
    
    public board(final imgslide a, final String s, final int gridy, final int gridx) {
        this.leegkleur = new Color[] { Color.lightGray, Color.green, Color.magenta, Color.orange, Color.red, Color.yellow, Color.white, Color.black, Color.blue, Color.cyan, Color.darkGray };
        this.a = a;
        this.GRIDX = gridx;
        this.GRIDY = gridy;
        this.MXKNOP = this.GRIDX * this.GRIDY;
        this.pic = new picture(a, s);
        this.b = new gamebutton[this.MXKNOP];
        this.setLayout(new GridLayout(this.GRIDY, this.GRIDX));
        for (int i = 0; i < this.MXKNOP; ++i) {
            this.add(this.b[i] = new gamebutton(this.pic, this, i));
        }
        this.leegkleurnr = ((a.leegkleurnr >= 0 && a.leegkleurnr < this.leegkleur.length) ? a.leegkleurnr : 0);
        this.shuffled = false;
    }
    
    public void reset() {
        this.leeg = this.MXKNOP - 1;
        for (int i = 0; i < this.MXKNOP; ++i) {
            this.b[i].butval = i;
            this.b[i].repaint();
        }
        this.moves = 0;
        this.shuffled = false;
        this.a.msg.setText("Startposition");
    }
    
    public void shuffle() {
        for (int n = this.MXKNOP * 16, i = 0; i < n; ++i) {
            this.moveleeg(this.kiesrndbuur(this.leeg));
        }
        this.moves = 0;
        this.shuffled = true;
        this.a.msg.setText("Shuffled");
    }
    
    boolean startsit() {
        boolean b = true;
        for (int i = 0; i < this.MXKNOP; ++i) {
            if (this.b[i].butval != i) {
                b = false;
            }
        }
        return b;
    }
    
    private boolean buren(final int n, final int n2) {
        return (n >= this.GRIDX && n2 == n - this.GRIDX) || (n2 >= this.GRIDX && n == n2 - this.GRIDX) || (n % this.GRIDX > 0 && n2 == n - 1) || (n2 % this.GRIDX > 0 && n == n2 - 1);
    }
    
    private boolean moveable(final int n, final int n2) {
        return this.a.oldrules ? this.buren(n, n2) : ((n / this.GRIDX == n2 / this.GRIDX || n % this.GRIDX == n2 % this.GRIDX) && n != n2);
    }
    
    private int kiesrndbuur(final int n) {
        final int[] array = new int[4];
        int n2 = 0;
        if (n >= this.GRIDX) {
            array[n2] = n - this.GRIDX;
            ++n2;
        }
        if (n + this.GRIDX < this.MXKNOP) {
            array[n2] = n + this.GRIDX;
            ++n2;
        }
        if (n % this.GRIDX > 0) {
            array[n2] = n - 1;
            ++n2;
        }
        if (n % this.GRIDX < this.GRIDX - 1) {
            array[n2] = n + 1;
            ++n2;
        }
        return array[Math.abs(this.a.rnd.nextInt()) % n2];
    }
    
    private void moveleeg(final int leeg) {
        final int butval = this.b[leeg].butval;
        this.b[leeg].butval = this.b[this.leeg].butval;
        this.b[this.leeg].butval = butval;
        this.b[leeg].repaint();
        this.b[this.leeg].repaint();
        this.leeg = leeg;
    }
    
    private void moveseq(final int n) {
        final int leeg = this.leeg;
        if (n % this.GRIDX == leeg % this.GRIDX) {
            for (int n2 = (n < leeg) ? (-this.GRIDX) : this.GRIDX, i = leeg; i != n; i += n2) {
                this.moveleeg(i + n2);
            }
        }
        if (n / this.GRIDX == leeg / this.GRIDX) {
            for (int n3 = (n < leeg) ? -1 : 1, j = leeg; j != n; j += n3) {
                this.moveleeg(j + n3);
            }
        }
    }
    
    void movemsg(final boolean b) {
        if (b) {
            ++this.moves;
        }
        new String();
        final String value = String.valueOf(this.moves);
        this.a.msg.setText((this.moves > 0 && this.startsit()) ? ("Won in " + ((this.moves == 1) ? "1 move" : (String.valueOf(value) + " moves"))) : ((this.moves == 1) ? "1 move" : (String.valueOf(value) + " moves")));
    }
    
    void newcolorleeg() {
        this.leegkleurnr = (this.leegkleurnr + 1) % this.leegkleur.length;
        this.b[this.leeg].repaint();
    }
    
    public void process(final Object o) {
        if (o == this.a.ctrl.reset) {
            this.reset();
        }
        if (o == this.a.ctrl.shuffle) {
            this.shuffle();
        }
        if (o == this.a.ctrl.stand) {
            this.movemsg(false);
        }
        if (o == this.a.ctrl.vb && this.a.vb == null) {
            this.a.vb = new example(this.a, this.pic);
        }
        if (this.a.vb != null && o == this.a.vb.close) {
            this.a.vb.dispose();
            this.a.vb = null;
        }
        if (o == this.a.ctrl.jbf) {
            this.a.msg.setText("Written by JP Hendriks");
        }
        for (int i = 0; i < this.MXKNOP; ++i) {
            if (o == this.b[i]) {
                this.processgame(this.b[i]);
            }
        }
    }
    
    void processgame(final gamebutton gamebutton) {
        if (!this.shuffled) {
            this.a.msg.setText("Shuffle in order to play");
            return;
        }
        if (this.moveable(gamebutton.butno, this.leeg)) {
            this.moveseq(gamebutton.butno);
            this.movemsg(true);
            return;
        }
        if (gamebutton.butno == this.leeg) {
            this.a.msg.setText("Yeah!");
            this.newcolorleeg();
            return;
        }
        this.a.msg.setText("Impossible");
    }
}
