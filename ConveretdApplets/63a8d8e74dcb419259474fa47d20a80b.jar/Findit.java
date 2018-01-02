import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Button;
import java.awt.MenuItem;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class Findit extends Frame implements WindowListener, ActionListener
{
    MenuItem newgame;
    MenuItem exit;
    MenuItem about;
    MenuItem soff;
    MenuItem son;
    MenuItem help;
    MenuItem keuze;
    Button pauze;
    Button next;
    private Screen sc;
    private Timeline tl;
    private Finditapplet fa;
    boolean sound;
    int red;
    int green;
    int blue;
    
    public Findit(final Finditapplet fa) {
        super((fa.getParameter("title") == null) ? "Find it!" : fa.getParameter("title"));
        this.sound = true;
        this.fa = fa;
        final MenuBar menuBar = new MenuBar();
        this.setMenuBar(menuBar);
        final Menu menu = new Menu("Game");
        final Menu menu2 = new Menu("Options");
        final Menu menu3 = new Menu("Help");
        menuBar.add(menu);
        menuBar.add(menu2);
        menuBar.add(menu3);
        this.newgame = new MenuItem("New Game");
        this.exit = new MenuItem("Exit Game");
        this.help = new MenuItem("Help...");
        this.about = new MenuItem("About..");
        this.soff = new MenuItem("Sound Off");
        this.son = new MenuItem("Sound On");
        menu.add(this.newgame);
        menu.insertSeparator(1);
        menu.add(this.exit);
        menu2.add(this.soff);
        menu2.add(this.son);
        menu3.add(this.help);
        menu3.add(this.about);
        this.newgame.addActionListener(this);
        this.exit.addActionListener(this);
        this.soff.addActionListener(this);
        this.son.addActionListener(this);
        this.about.addActionListener(this);
        this.help.addActionListener(this);
        final Dimension screenSize = this.getToolkit().getScreenSize();
        final int n = 700;
        final int n2 = 560;
        this.setResizable(false);
        this.setLocation(50, 10);
        this.setSize(n, n2);
        this.setIconImage(fa.getImage(fa.getDocumentBase(), "wrong.gif"));
        this.addWindowListener(this);
        this.setLayout(null);
        this.sc = new Screen(fa, this);
        this.tl = new Timeline(this.sc, fa, this);
        this.pauze = new Button("Pauze");
        this.next = new Button("Next");
        this.sc.setBounds(10, 40, screenSize.width, 350);
        this.add(this.sc);
        this.pauze.setBounds(240, 395, 100, 40);
        this.add(this.pauze);
        this.next.setBounds(360, 395, 100, 40);
        this.add(this.next);
        this.next.addActionListener(this);
        this.pauze.addActionListener(this);
        this.tl.setBounds(10, 400, screenSize.width, 200);
        this.add(this.tl);
        this.red = ((fa.getParameter("bgred") == null) ? 0 : Integer.parseInt(fa.getParameter("bgred")));
        this.green = ((fa.getParameter("bggreen") == null) ? 0 : Integer.parseInt(fa.getParameter("bggreen")));
        this.blue = ((fa.getParameter("bgblue") == null) ? 0 : Integer.parseInt(fa.getParameter("bgblue")));
        final Color background = new Color(this.red, this.green, this.blue);
        this.sc.setBackground(background);
        this.tl.setBackground(background);
        this.setBackground(background);
        this.tl.Clear();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.pauze) {
            this.tl.stopff();
            return;
        }
        if (actionEvent.getSource() == this.next) {
            if (this.next.getLabel() == "Next") {
                this.tl.volgende();
            }
            if (this.next.getLabel() == "New Game") {
                this.tl.opnieuw();
            }
        }
        else {
            this.keuze = (MenuItem)actionEvent.getSource();
            if (this.keuze != null) {
                if (this.keuze == this.newgame) {
                    this.tl.opnieuw();
                    return;
                }
                if (this.keuze == this.exit) {
                    if (Timeline.messageThread != null) {
                        Timeline.messageThread.stop();
                        Timeline.messageThread = null;
                    }
                    this.fa.fa = null;
                    this.dispose();
                    return;
                }
                if (this.keuze == this.about) {
                    new Waarschuwing("About");
                    return;
                }
                if (this.keuze == this.soff) {
                    this.sound = false;
                    return;
                }
                if (this.keuze == this.son) {
                    this.sound = true;
                    return;
                }
                if (this.keuze == this.help) {
                    new Helpbox("Help");
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
        this.fa.fa = null;
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
}
