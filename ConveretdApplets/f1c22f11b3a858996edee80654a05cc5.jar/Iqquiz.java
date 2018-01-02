import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Button;
import java.awt.MenuItem;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class Iqquiz extends Frame implements WindowListener, ActionListener
{
    MenuItem newgame;
    MenuItem exit;
    MenuItem about;
    MenuItem soff;
    MenuItem help;
    MenuItem keuze;
    Button pauze;
    Button next;
    Button stop;
    Button previous;
    Button helpbt;
    Button[] opt;
    TextField tf1;
    TextField tf2;
    private Screen sc;
    private Timeline tl;
    private Iqapplet iq;
    private Infocanvas info;
    boolean text;
    boolean sound;
    int red;
    int green;
    int blue;
    int question;
    int bpressed;
    int good;
    String[] answer;
    String[] answer2;
    boolean pause;
    boolean stoppressed;
    boolean start;
    boolean checked;
    boolean timeup;
    boolean cheated;
    String[] solution;
    String[] solution2;
    Color btcolor;
    
    public Iqquiz(final Iqapplet iq) {
        super(iq.reg ? ("IQ Quiz Registered to " + iq.companyname) : "IQ Quiz Unregistered Version");
        this.opt = new Button[5];
        this.text = false;
        this.sound = true;
        this.question = 0;
        this.bpressed = 0;
        this.good = 0;
        this.answer = new String[16];
        this.answer2 = new String[16];
        this.pause = false;
        this.stoppressed = false;
        this.start = true;
        this.checked = false;
        this.timeup = false;
        this.cheated = false;
        this.solution = new String[] { "0", "10", "4", "37", "3", "2", "SWALLOW", "4", "NOSE", "2", "O", "16", "4", "33", "20", "0", "0" };
        this.solution2 = new String[] { "0", "Y" };
        this.iq = iq;
        final MenuBar menuBar = new MenuBar();
        this.setMenuBar(menuBar);
        final Menu menu = new Menu("Game");
        final Menu menu2 = new Menu("Help");
        menuBar.add(menu);
        menuBar.add(menu2);
        this.newgame = new MenuItem("New Game");
        this.exit = new MenuItem("Exit Game");
        this.help = new MenuItem("Help...");
        this.about = new MenuItem("About..");
        this.soff = new MenuItem("Pause");
        menu.add(this.newgame);
        menu.add(this.soff);
        menu.insertSeparator(1);
        menu.add(this.exit);
        menu2.add(this.help);
        menu2.add(this.about);
        this.newgame.addActionListener(this);
        this.exit.addActionListener(this);
        this.soff.addActionListener(this);
        this.about.addActionListener(this);
        this.help.addActionListener(this);
        this.getToolkit().getScreenSize();
        final int n = 700;
        final int n2 = 550;
        this.setResizable(false);
        this.setLocation(50, 10);
        this.setSize(n, n2);
        this.setIconImage(iq.getImage(iq.getDocumentBase(), "wrong.gif"));
        this.addWindowListener(this);
        this.setLayout(null);
        this.sc = new Screen(iq, this);
        this.tl = new Timeline(this.sc, iq, this);
        this.info = new Infocanvas(this.sc, iq, this);
        this.tf1 = new TextField("");
        this.tf2 = new TextField("");
        this.tf1.setBackground(Color.white);
        this.tf2.setBackground(Color.white);
        this.pauze = new Button("Pause");
        this.next = new Button("Start");
        this.stop = new Button("Stop");
        this.helpbt = new Button("Solution");
        this.previous = new Button("Previous");
        this.btcolor = new Color(180, 180, 180);
        for (int i = 0; i < 5; ++i) {
            (this.opt[i] = new Button("Choice " + (i + 1))).setBounds(50 + i * 90, 400, 70, 30);
            this.opt[i].addActionListener(this);
            this.opt[i].setBackground(this.btcolor);
        }
        this.tf1.setBounds(50, 400, 150, 30);
        this.tf2.setBounds(250, 400, 150, 30);
        this.helpbt.setBounds(50, 450, 100, 40);
        this.pauze.setBounds(50, 450, 100, 40);
        this.next.setBounds(50, 450, 360, 40);
        this.helpbt.addActionListener(this);
        this.stop.setBounds(290, 450, 100, 40);
        this.previous.setBounds(410, 450, 100, 40);
        this.helpbt.setBounds(530, 450, 100, 40);
        this.stop.addActionListener(this);
        this.previous.addActionListener(this);
        this.next.addActionListener(this);
        this.pauze.addActionListener(this);
        this.stop.setBackground(this.btcolor);
        this.helpbt.setBackground(this.btcolor);
        this.next.setBackground(this.btcolor);
        this.pauze.setBackground(this.btcolor);
        this.previous.setBackground(this.btcolor);
        this.sc.setBounds(40, 80, n - 200, 300);
        this.add(this.sc);
        this.tl.setBounds(n - 120, 80, 100, 150);
        this.add(this.tl);
        this.info.setBounds(n - 120, 250, 100, 130);
        this.add(this.info);
        this.red = ((iq.getParameter("bgred") == null) ? 0 : Integer.parseInt(iq.getParameter("bgred")));
        this.green = ((iq.getParameter("bggreen") == null) ? 0 : Integer.parseInt(iq.getParameter("bggreen")));
        this.blue = ((iq.getParameter("bgblue") == null) ? 0 : Integer.parseInt(iq.getParameter("bgblue")));
        this.red = 0;
        this.green = 0;
        this.blue = 122;
        final Color background = new Color(this.red, this.green, this.blue);
        final Color background2 = new Color(0, 0, 255);
        this.sc.setBackground(background2);
        this.info.setBackground(background2);
        this.tl.setBackground(background2);
        this.setBackground(background);
        for (int j = 0; j < 16; ++j) {
            this.answer[j] = "";
        }
        this.add(this.next);
        this.validate();
        this.doLayout();
        this.next.validate();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.pauze || actionEvent.getSource() == this.stop || actionEvent.getSource() == this.previous || actionEvent.getSource() == this.next || actionEvent.getSource() == this.opt[0] || actionEvent.getSource() == this.opt[1] || actionEvent.getSource() == this.opt[2] || actionEvent.getSource() == this.opt[3] || actionEvent.getSource() == this.opt[4] || actionEvent.getSource() == this.helpbt) {
            if (actionEvent.getSource() == this.pauze) {
                this.pauseCheck();
            }
            if (actionEvent.getSource() == this.helpbt) {
                final Helpbox helpbox = new Helpbox(this.helpbt.getLabel(), this.iq, "Solution", this.solution[this.question], false);
            }
            if (actionEvent.getSource() == this.stop) {
                this.stop.setLabel("Stopped");
                this.next.setLabel("Done");
                this.stoppressed = true;
                this.checkanswer();
                this.sc.repaint();
                this.removeAll();
                Timeline.messageThread.suspend();
            }
            if (actionEvent.getSource() == this.opt[0]) {
                this.bpressed = 1;
                this.saveanswer();
                this.nextquestion();
                this.sc.repaint();
            }
            if (actionEvent.getSource() == this.opt[1]) {
                this.bpressed = 2;
                this.saveanswer();
                this.nextquestion();
                this.sc.repaint();
            }
            if (actionEvent.getSource() == this.opt[2]) {
                this.bpressed = 3;
                this.saveanswer();
                this.nextquestion();
                this.sc.repaint();
            }
            if (actionEvent.getSource() == this.opt[3]) {
                this.bpressed = 4;
                this.saveanswer();
                this.nextquestion();
                this.sc.repaint();
            }
            if (actionEvent.getSource() == this.opt[4]) {
                this.bpressed = 5;
                this.saveanswer();
                this.nextquestion();
                this.sc.repaint();
            }
            if (actionEvent.getSource() == this.next) {
                if (this.next.getLabel() == "Done") {
                    this.restart();
                    this.removeAll();
                }
                else if (this.start) {
                    this.helpbt.setLabel("Solution");
                    this.next.setBounds(170, 450, 100, 40);
                    this.add(this.pauze);
                    this.add(this.stop);
                    this.add(this.helpbt);
                    this.add(this.previous);
                    this.start = false;
                    this.next.setLabel("Next");
                    this.nextquestion();
                    this.sc.repaint();
                    this.pauze.requestFocus();
                    this.stop.requestFocus();
                    this.previous.requestFocus();
                    this.next.requestFocus();
                    this.pauze.setVisible(true);
                    this.previous.setVisible(true);
                    this.stop.setVisible(true);
                    this.next.setVisible(true);
                    try {
                        Timeline.messageThread.start();
                    }
                    catch (Exception ex) {
                        Timeline.messageThread.resume();
                    }
                }
                else {
                    this.saveanswer();
                    this.nextquestion();
                    if (this.answer[this.question] != null && this.text) {
                        this.tf1.setText(this.answer[this.question]);
                        this.tf2.setText(this.answer2[this.question]);
                    }
                    this.sc.repaint();
                }
                if (this.next.getLabel().equals("Finish")) {}
            }
            if (actionEvent.getSource() == this.previous && this.question > 1) {
                this.tf1.requestFocus();
                this.question -= 2;
                this.tf1.setText(this.answer[this.question + 1]);
                this.tf2.setText(this.answer2[this.question + 1]);
                this.nextquestion();
                this.sc.repaint();
                this.info.repaint();
            }
        }
        else {
            this.keuze = (MenuItem)actionEvent.getSource();
            if (this.keuze != null) {
                if (this.keuze == this.newgame) {
                    this.restart();
                }
                else if (this.keuze == this.exit) {
                    if (Timeline.messageThread != null) {
                        Timeline.messageThread.stop();
                        Timeline.messageThread = null;
                    }
                    this.iq.iq = null;
                    this.dispose();
                }
                else if (this.keuze == this.about) {
                    final Waarschuwing waarschuwing = new Waarschuwing(this.iq, "About");
                }
                else if (this.keuze == this.soff) {
                    this.pauseCheck();
                }
                else if (this.keuze == this.help) {
                    final Helpbox helpbox2 = new Helpbox(this.helpbt.getLabel(), this.iq, "Help", "", true);
                }
            }
        }
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (Timeline.messageThread != null) {
            Timeline.messageThread.stop();
            Timeline.messageThread = null;
        }
        this.dispose();
        this.iq.iq = null;
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void nextquestion() {
        this.tf1.requestFocus();
        ++this.question;
        this.info.repaint();
        if (this.question == 14) {
            this.next.setLabel("Finish");
            this.text = true;
        }
        else {
            this.next.setLabel("Next");
            if (this.question == 1) {
                this.text = true;
            }
            if (this.question == 2) {
                this.text = false;
            }
            if (this.question == 3) {
                this.text = true;
            }
            if (this.question == 4) {
                this.text = false;
            }
            if (this.question == 5) {
                this.text = false;
            }
            if (this.question == 6) {
                this.text = true;
            }
            if (this.question == 7) {
                this.text = false;
            }
            if (this.question == 8) {
                this.text = true;
            }
            if (this.question == 9) {
                this.text = false;
            }
            if (this.question == 10) {
                this.text = true;
            }
            if (this.question == 11) {
                this.text = true;
            }
            if (this.question == 12) {
                this.text = false;
            }
            if (this.question == 13) {
                this.text = true;
            }
            if (this.question == 14) {
                this.text = true;
            }
            this.pauze.requestFocus();
            this.stop.requestFocus();
            this.previous.requestFocus();
            this.next.requestFocus();
            this.pauze.setVisible(true);
            this.next.setVisible(true);
            this.stop.setVisible(true);
            this.previous.setVisible(true);
            if (this.question == 15) {
                this.stoppressed = true;
                this.checkanswer();
                this.next.setLabel("Done");
                this.tl.stopstring = "Finished";
                this.refresh();
                this.removeAll();
                Timeline.messageThread.suspend();
            }
        }
        this.buttons();
    }
    
    public void checkanswer() {
        if (!this.checked) {
            this.question = 0;
            for (int i = 1; i < 16; ++i) {
                ++this.question;
                if (this.question == 1 && this.answer[1].toUpperCase().equals(this.solution[1])) {
                    ++this.good;
                }
                if (this.question == 2 && this.answer[2].toUpperCase().equals(this.solution[2])) {
                    ++this.good;
                }
                if (this.question == 3 && this.answer[3].toUpperCase().equals(this.solution[3])) {
                    ++this.good;
                }
                if (this.question == 4 && this.answer[4].toUpperCase().equals(this.solution[4])) {
                    ++this.good;
                }
                if (this.question == 5 && this.answer[5].toUpperCase().equals(this.solution[5])) {
                    ++this.good;
                }
                if (this.question == 6 && this.answer[6].toUpperCase().equals(this.solution[6])) {
                    ++this.good;
                }
                if (this.question == 7 && this.answer[7].toUpperCase().equals(this.solution[7])) {
                    ++this.good;
                }
                if (this.question == 8 && this.answer[8].toUpperCase().equals(this.solution[8])) {
                    ++this.good;
                }
                if (this.question == 9 && this.answer[9].toUpperCase().equals(this.solution[9])) {
                    ++this.good;
                }
                if (this.question == 10 && this.answer[10].toUpperCase().equals(this.solution[10]) && this.answer2[10].toUpperCase().equals(this.solution2[1])) {
                    ++this.good;
                }
                if (this.question == 11 && this.answer[11].toUpperCase().equals(this.solution[11])) {
                    ++this.good;
                }
                if (this.question == 12 && this.answer[12].toUpperCase().equals(this.solution[12])) {
                    ++this.good;
                }
                if (this.question == 13 && this.answer[13].toUpperCase().equals(this.solution[13])) {
                    ++this.good;
                }
                if (this.question == 14 && this.answer[14].toUpperCase().equals(this.solution[14])) {
                    ++this.good;
                }
                this.checked = true;
            }
        }
    }
    
    public void saveanswer() {
        if (this.text) {
            if (!this.tf1.getText().equals("")) {
                this.answer[this.question] = this.tf1.getText();
                this.answer2[this.question] = this.tf2.getText();
            }
        }
        else if (this.bpressed != 0) {
            this.answer[this.question] = "" + this.bpressed;
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.draw3DRect(575, 75, 110, 158, false);
        graphics.draw3DRect(575, 240, 110, 145, false);
        graphics.draw3DRect(35, 75, 510, 310, false);
    }
    
    public void buttons() {
        if (this.question != 15) {
            if (this.text) {
                for (int i = 0; i < 5; ++i) {
                    this.remove(this.opt[i]);
                }
                this.add(this.tf1);
                if (this.question == 10) {
                    this.add(this.tf2);
                    this.tf2.setVisible(true);
                }
                this.repaint();
                this.pauze.requestFocus();
                this.stop.requestFocus();
                this.previous.requestFocus();
                this.next.requestFocus();
                this.validate();
                this.tf1.requestFocus();
                this.tf1.setText("focussing");
                this.tf2.setText("focussing");
                this.tf1.setText("");
                this.tf2.setText("");
                this.tf1.setVisible(true);
            }
            else {
                for (int j = 0; j < 5; ++j) {
                    this.add(this.opt[j]);
                    this.opt[j].requestFocus();
                    this.opt[j].setVisible(true);
                    this.bpressed = 0;
                }
                this.remove(this.tf1);
                this.remove(this.tf2);
                this.tf1.setVisible(false);
            }
        }
    }
    
    public void restart() {
        this.text = false;
        this.sound = true;
        this.question = 0;
        this.bpressed = 0;
        this.good = 0;
        final String[] array = new String[16];
        final String[] array2 = new String[16];
        this.pause = false;
        this.stoppressed = false;
        this.start = true;
        this.checked = false;
        this.timeup = false;
        this.next.setLabel("Start");
        for (int i = 0; i < 16; ++i) {
            array[i] = "null";
        }
        this.tl.uur1 = 0;
        this.tl.min1 = 0;
        this.tl.sec1 = 0;
        this.sc.iqpt = 0.0;
        this.tl.stopstring = "Ready to start";
        this.info.answered = 0;
        this.refresh();
        this.removeAll();
        this.checked = false;
    }
    
    public void refresh() {
        this.sc.repaint();
        this.tl.repaint();
        this.info.repaint();
    }
    
    public void removeAll() {
        for (int i = 0; i < 5; ++i) {
            this.remove(this.opt[i]);
        }
        this.remove(this.tf1);
        this.remove(this.tf2);
        this.remove(this.previous);
        this.remove(this.pauze);
        this.remove(this.stop);
        this.helpbt.setLabel("More applets...");
    }
    
    public void pauseCheck() {
        if (!this.pause) {
            this.pause = true;
            this.tl.stopstring = "Paused";
            Timeline.messageThread.suspend();
            this.pauze.setLabel("Resume");
            this.sc.repaint();
            this.tl.repaint();
        }
        else {
            this.pause = false;
            Timeline.messageThread.resume();
            this.pauze.setLabel("Pause");
            this.sc.repaint();
        }
    }
}
