import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Frame;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class abacusBoard extends Component
{
    public column[] columns;
    public static final int COLTOTAL = 10;
    private int value;
    int height;
    int width;
    int beadheight;
    int beadwidth;
    int stickwidth;
    int border;
    int delay;
    String moveDescription;
    boolean auto;
    
    abacusBoard(final int value) {
        this.columns = new column[10];
        for (int i = 0; i < 10; ++i) {
            this.columns[i] = new column();
        }
        this.setValue(value);
        for (int j = 0; j < 10; ++j) {
            this.columns[j].onechanged = true;
            this.columns[j].fivechanged = true;
        }
        this.auto = false;
        this.height = 400;
        this.width = 550;
        this.border = 25;
        this.beadheight = 25;
        this.beadwidth = 30;
        this.stickwidth = 5;
        this.delay = 1500;
        this.enableEvents(48L);
    }
    
    public void setDelay(final int delay) {
        this.delay = delay;
    }
    
    public int setValue(final int value) {
        this.value = value;
        int i;
        for (i = 0; Math.pow(10.0, i) <= value; ++i) {
            this.columns[i].setValue(value % (int)Math.pow(10.0, i + 1) / (int)Math.pow(10.0, i));
        }
        while (i < this.columns.length) {
            this.columns[i].setValue(0);
            ++i;
        }
        this.moveDescription = "";
        if (this.getGraphics() != null) {
            this.update(this.getGraphics());
        }
        return this.value;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.getID() == 500) {
            if (mouseEvent.getY() < this.height / 3) {
                for (int i = 0; i < 10; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        final int n = this.border + this.stickwidth / 2 + (10 - i) * (this.width - 2 * this.border) / 11 - this.beadwidth / 2;
                        final int n2 = this.border + 3 + j * (this.beadheight + 5);
                        if (n <= mouseEvent.getX() && mouseEvent.getX() <= n + this.beadwidth && n2 <= mouseEvent.getY() && mouseEvent.getY() <= n2 + this.beadheight) {
                            if (this.columns[i].fiveon != 2 - j) {
                                this.moveFive(i, 2 - (j + this.columns[i].fiveon));
                            }
                        }
                    }
                }
            }
            else if (mouseEvent.getY() > this.border + this.height / 3) {
                for (int k = 0; k < 10; ++k) {
                    for (int l = 0; l < 7; ++l) {
                        final int n3 = this.border + this.stickwidth / 2 + (10 - k) * (this.width - 2 * this.border) / 11 - this.beadwidth / 2;
                        final int n4 = this.border + this.height / 3 + 3 + l * (this.beadheight + 5);
                        if (n3 <= mouseEvent.getX() && mouseEvent.getX() <= n3 + this.beadwidth && n4 <= mouseEvent.getY() && mouseEvent.getY() <= n4 + this.beadheight) {
                            if (this.columns[k].oneon >= l + 1 || this.columns[k].oneon <= l - 2) {
                                if (l < this.columns[k].oneon) {
                                    this.moveOne(k, l - this.columns[k].oneon);
                                }
                                else {
                                    this.moveOne(k, l - this.columns[k].oneon - 1);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (mouseEvent.getID() == 503) {}
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < this.border; ++i) {
            graphics.drawRect(i, i, this.width - 2 * i, this.height - 2 * i);
        }
        graphics.setColor(Color.darkGray);
        for (int j = 0; j < 10; ++j) {
            int k = 0;
            while (k < (k = this.border + this.stickwidth / 2 + (10 - j) * (this.width - 2 * this.border) / 11 - this.beadwidth / 2 + (this.beadwidth / 2 - this.stickwidth / 2)) + this.stickwidth) {
                graphics.drawLine(k, this.border, k, this.height - this.border);
                ++k;
            }
        }
        graphics.setColor(Color.black);
        for (int l = 0; l < this.border; ++l) {
            graphics.drawLine(this.border, l + this.height / 3, this.width - this.border, l + this.height / 3);
        }
        graphics.setColor(Color.black);
        for (int n = 0; n < 10; ++n) {
            for (int n2 = 0; n2 < 3; ++n2) {
                if (this.columns[n].fiveon == 2 - n2) {
                    if (this.columns[n].fivechanged) {
                        final int n3 = this.border + this.stickwidth / 2 + (10 - n) * (this.width - 2 * this.border) / 11 - this.beadwidth / 2;
                        final int n4 = this.border + 3 + n2 * (this.beadheight + 5);
                        graphics.clearRect(n3, n4, this.beadwidth / 2 - this.stickwidth / 2, this.beadheight);
                        graphics.clearRect(n3 + (this.beadwidth / 2 - this.stickwidth / 2) + this.stickwidth, n4, this.beadwidth / 2 - this.stickwidth / 2, this.beadheight);
                    }
                }
                else {
                    graphics.fillOval(this.border + this.stickwidth / 2 + (10 - n) * (this.width - 2 * this.border) / 11 - this.beadwidth / 2, this.border + 3 + n2 * (this.beadheight + 5), this.beadwidth, this.beadheight);
                }
            }
            for (int n5 = 0; n5 < 7; ++n5) {
                if (this.columns[n].oneon < n5 + 1 && this.columns[n].oneon > n5 - 2) {
                    if (this.columns[n].onechanged) {
                        final int n6 = this.border + this.stickwidth / 2 + (10 - n) * (this.width - 2 * this.border) / 11 - this.beadwidth / 2;
                        final int n7 = this.border + this.height / 3 + 3 + n5 * (this.beadheight + 5);
                        graphics.clearRect(n6, n7, this.beadwidth / 2 - this.stickwidth / 2, this.beadheight);
                        graphics.clearRect(n6 + (this.beadwidth / 2 - this.stickwidth / 2) + this.stickwidth, n7, this.beadwidth / 2 - this.stickwidth / 2, this.beadheight);
                    }
                }
                else {
                    graphics.fillOval(this.border + this.stickwidth / 2 + (10 - n) * (this.width - 2 * this.border) / 11 - this.beadwidth / 2, this.border + this.height / 3 + 3 + n5 * (this.beadheight + 5), this.beadwidth, this.beadheight);
                }
            }
            if (this.columns[n].fivechanged) {
                this.columns[n].fivechanged = false;
            }
            if (this.columns[n].onechanged) {
                this.columns[n].onechanged = false;
            }
        }
        graphics.setColor(Color.orange);
        graphics.setFont(new Font("SansSerif", 0, 20));
        for (int n8 = 0; n8 < 10; ++n8) {
            for (int n9 = this.border + 3 * this.beadwidth / 2 + n8 * (this.width - 2 * this.border) / 11; n9 < this.border + 3 * this.beadwidth / 2 + this.stickwidth + n8 * (this.width - 2 * this.border) / 11; ++n9) {
                graphics.drawString(Integer.toString(this.columns[9 - n8].value), n9, this.border - 6 + this.height - 1 * this.border);
            }
        }
    }
    
    public int add(final int n) {
        if (this.value + n > 199999999) {
            return -1;
        }
        this.moveDescription = "";
        return this.setValue(this.value + n);
    }
    
    public int addSteps(final int n) {
        if (this.value + n > 199999999) {
            return -1;
        }
        int value;
        if (n >= this.value) {
            value = n;
        }
        else {
            value = this.value;
        }
        for (int n2 = 0; Math.pow(10.0, n2) <= value; ++n2) {
            this.addColumn(n2, n % (int)Math.pow(10.0, n2 + 1) / (int)Math.pow(10.0, n2));
        }
        return this.value += n;
    }
    
    public void addColumn(final int n, final int n2) {
        final int n3 = n2 / 5;
        final int n4 = n2 % 5;
        if (this.columns[n].value + n2 < 10) {
            if (this.columns[n].value + n2 > 5) {
                if (this.columns[n].value < 5 && n2 < 5) {
                    this.moveOneFive(n, -(5 - n4), 1);
                }
                else if (this.columns[n].value >= 5 && n2 < 5) {
                    this.moveOne(n, n4);
                }
                else if (n2 == 5) {
                    this.moveFive(n, n3);
                }
                else if (n2 > 5) {
                    this.moveOneFive(n, n4, n3);
                }
            }
            else if (this.columns[n].value + n2 == 5) {
                if (this.columns[n].fiveon != 1) {
                    this.moveOne(n, n4);
                    this.moveFive(n, 1);
                    this.moveOne(n, -this.columns[n].oneon);
                }
            }
            else {
                this.moveOne(n, n4);
            }
        }
        else if (this.columns[n].value + n2 >= 10 && n + 1 < 10 && this.columns[n + 1].value == 9) {
            this.moveOne(n + 1, 1);
            this.subColumn(n, 10 - n2);
        }
        else {
            this.addColumn(n + 1, 1);
            this.subColumn(n, 10 - n2);
        }
    }
    
    public int subtract(final int n) {
        if (this.value - n < 0) {
            return -1;
        }
        return this.setValue(this.value - n);
    }
    
    public int subSteps(final int n) {
        if (this.value - n < 0) {
            return -1;
        }
        for (int n2 = 0; Math.pow(10.0, n2) <= this.value; ++n2) {
            this.subColumn(n2, n % (int)Math.pow(10.0, n2 + 1) / (int)Math.pow(10.0, n2));
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return this.value -= n;
    }
    
    public void subColumn(final int n, final int n2) {
        int n3 = n2 / 5;
        int n4 = n2 % 5;
        if (n3 == 2) {
            if (this.columns[n].fiveon == 1) {
                n3 = 1;
                n4 = 5;
            }
            else if (this.columns[n].fiveon == 2) {
                n3 = 2;
                n4 = 0;
            }
        }
        if (this.columns[n].value - n2 >= 0) {
            if (this.columns[n].value >= 5) {
                if (n2 > 5) {
                    this.moveOneFive(n, -n4, -n3);
                }
                else if (n2 == 5) {
                    this.moveFive(n, -n3);
                }
                else if (this.columns[n].value - n2 >= 5) {
                    this.moveOne(n, -n4);
                }
                else {
                    this.moveOneFive(n, 5 - n4, -1);
                }
            }
            else {
                this.moveOne(n, -n4);
            }
        }
        else {
            this.subColumn(n + 1, 1);
            this.addColumn(n, 10 - n2);
        }
    }
    
    void moveFive(final int n, final int n2) {
        if (n2 == 0) {
            return;
        }
        final column column = this.columns[n];
        column.fiveon += n2;
        final column column2 = this.columns[n];
        column2.value += 5 * n2;
        this.columns[n].fivechanged = true;
        this.update(this.getGraphics());
        if (this.auto) {
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        if (n2 > 0) {
            this.moveDescription = "Shift column " + n + " heav";
        }
    }
    
    void moveOne(final int n, final int n2) {
        if (n2 == 0) {
            return;
        }
        final column column = this.columns[n];
        column.oneon += n2;
        final column column2 = this.columns[n];
        column2.value += n2;
        this.columns[n].onechanged = true;
        this.update(this.getGraphics());
        if (this.auto) {
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    void moveOneFive(final int n, final int n2, final int n3) {
        if (n2 == 0 && n3 == 0) {
            return;
        }
        final column column = this.columns[n];
        column.fiveon += n3;
        final column column2 = this.columns[n];
        column2.value += 5 * n3;
        final column column3 = this.columns[n];
        column3.oneon += n2;
        final column column4 = this.columns[n];
        column4.value += n2;
        if (n2 != 0) {
            this.columns[n].onechanged = true;
        }
        if (n3 != 0) {
            this.columns[n].fivechanged = true;
        }
        this.update(this.getGraphics());
        if (this.auto) {
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public String printState() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 13; ++i) {
            if (i == 0 || i == 12) {
                sb.append("+");
                for (int j = 0; j < 41; ++j) {
                    sb.append("-");
                }
                sb.append("+\n");
            }
            else if (1 <= i && i <= 3) {
                sb.append("|");
                for (int k = 9; k > -1; --k) {
                    sb.append(" ");
                    if (this.columns[k].fiveon == 3 - i) {
                        sb.append(" | ");
                    }
                    else {
                        sb.append("***");
                    }
                }
                sb.append(" |\n");
            }
            else if (i == 4) {
                sb.append("|");
                for (int l = 0; l < 41; ++l) {
                    if (l % 4 == 2) {
                        sb.append("+");
                    }
                    else {
                        sb.append("-");
                    }
                }
                sb.append("|\n");
            }
            else if (5 <= i && i <= 11) {
                sb.append("|");
                for (int n = 9; n > -1; --n) {
                    sb.append(" ");
                    if (this.columns[n].oneon < i - 4 && this.columns[n].oneon > i - 7) {
                        sb.append(" | ");
                    }
                    else {
                        sb.append("***");
                    }
                }
                sb.append(" |\n");
            }
        }
        sb.append("\n");
        System.out.print(sb);
        return sb.toString();
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static void main(final String[] array) {
        final abacusBoard abacusBoard = new abacusBoard(0);
        final Frame frame = new Frame("Chinese Abacus");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        frame.add(abacusBoard, "North");
        frame.setSize(500, 700);
        frame.pack();
        frame.show();
    }
}
