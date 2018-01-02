import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Button;
import java.awt.MenuItem;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class Memory extends Frame implements ActionListener, Runnable, WindowListener
{
    MenuItem newgame;
    MenuItem exit;
    MenuItem about;
    MenuItem soff;
    MenuItem son;
    MenuItem help;
    MenuItem keuze;
    MenuItem difeasy;
    MenuItem difhard;
    MenuItem cardset1;
    MenuItem cardset2;
    MenuItem cardset3;
    Button start1;
    Button start2;
    Button start3;
    private Memoryapplet mem;
    Image duke1;
    Image duke2;
    Image pict;
    Image color;
    Image texture;
    Image background;
    Image photo;
    boolean wave;
    Cardscreen cv;
    Time tm;
    boolean wave2;
    Thread dukeThread;
    boolean start;
    int refresh;
    MediaTracker mt;
    
    public Memory(final Memoryapplet mem) {
        super(mem.reg ? ("Photo Memory Version 1.4 Registered to " + mem.companyname) : "Photo Memory Version 1.2 Unregistered");
        this.start1 = new Button("Pictures");
        this.start2 = new Button("Colors");
        this.start3 = new Button("Textures");
        this.wave = true;
        this.wave2 = true;
        this.start = true;
        this.refresh = 0;
        this.mt = new MediaTracker(this);
        this.setIconImage(mem.getImage(mem.getDocumentBase(), "icon.gif"));
        this.mem = mem;
        this.cv = new Cardscreen(mem, "start1");
        this.tm = new Time(this.cv, mem);
        this.setResizable(false);
        this.addWindowListener(this);
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
        this.difeasy = new MenuItem("Easy Difficulty");
        this.difhard = new MenuItem("Hard Difficulty");
        this.cardset1 = new MenuItem("Pictures");
        this.cardset2 = new MenuItem("Colors");
        this.cardset3 = new MenuItem("Textures");
        menu.add(this.newgame);
        menu.insertSeparator(1);
        menu.add(this.exit);
        menu2.add(this.soff);
        menu2.add(this.son);
        menu2.insertSeparator(2);
        menu2.add(this.difhard);
        menu2.add(this.difeasy);
        menu2.insertSeparator(5);
        menu2.add(this.cardset1);
        menu2.add(this.cardset2);
        menu2.add(this.cardset3);
        menu3.add(this.help);
        menu3.add(this.about);
        this.newgame.addActionListener(this);
        this.exit.addActionListener(this);
        this.soff.addActionListener(this);
        this.son.addActionListener(this);
        this.difhard.addActionListener(this);
        this.difeasy.addActionListener(this);
        this.cardset1.addActionListener(this);
        this.cardset2.addActionListener(this);
        this.cardset3.addActionListener(this);
        this.about.addActionListener(this);
        this.help.addActionListener(this);
        this.getPictures();
        this.setLayout(null);
        this.start1.requestFocus();
        this.setSize(500, 480);
        this.getSize();
        this.start1.setBounds(70, 250, 100, 30);
        this.start2.setBounds(190, 250, 100, 30);
        this.start3.setBounds(310, 250, 100, 30);
        this.add(this.start1);
        this.add(this.start2);
        this.add(this.start3);
        this.start1.addActionListener(this);
        this.start2.addActionListener(this);
        this.start3.addActionListener(this);
        mem.ok1.setLabel("Game Loaded");
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.start1) {
            this.remove(this.start1);
            this.remove(this.start3);
            this.remove(this.start2);
            this.start = false;
            this.cv = new Cardscreen(this.mem, "start1");
            this.add(this.tm = new Time(this.cv, this.mem));
            this.add(this.cv);
            this.cv.setBounds(100, 50, 400, 400);
            this.cv.setBackground(Color.black);
            this.tm.setBounds(0, 50, 100, 400);
            this.tm.setBackground(Color.black);
            this.setBackground(Color.black);
        }
        else if (actionEvent.getSource() == this.start2) {
            this.remove(this.start1);
            this.remove(this.start3);
            this.remove(this.start2);
            this.start = false;
            this.cv = new Cardscreen(this.mem, "start2");
            this.add(this.tm = new Time(this.cv, this.mem));
            this.add(this.cv);
            this.cv.setBounds(100, 50, 400, 400);
            this.cv.setBackground(Color.black);
            this.tm.setBounds(0, 50, 100, 400);
            this.tm.setBackground(Color.black);
            this.setBackground(Color.black);
        }
        else if (actionEvent.getSource() == this.start3) {
            this.remove(this.start1);
            this.remove(this.start3);
            this.remove(this.start2);
            this.start = false;
            this.cv = new Cardscreen(this.mem, "start3");
            this.add(this.tm = new Time(this.cv, this.mem));
            this.add(this.cv);
            this.cv.setBounds(100, 50, 400, 400);
            this.cv.setBackground(Color.black);
            this.tm.setBounds(0, 50, 100, 400);
            this.tm.setBackground(Color.black);
            this.setBackground(Color.black);
        }
        else {
            this.keuze = (MenuItem)actionEvent.getSource();
            if (this.keuze != null) {
                if (this.keuze == this.newgame) {
                    if (!this.start) {
                        this.remove(this.cv);
                        this.remove(this.tm);
                    }
                    this.add(this.start1);
                    this.add(this.start2);
                    this.add(this.start3);
                    this.start = true;
                    this.repaint();
                }
                else if (this.keuze == this.exit) {
                    this.mem.ok1.setLabel("Restart");
                    this.dispose();
                }
                else if (this.keuze == this.about) {
                    final Waarschuwing waarschuwing = new Waarschuwing("About");
                }
                else if (this.keuze == this.soff) {
                    if (!this.start) {
                        this.cv.sound = false;
                        this.tm.fix();
                    }
                }
                else if (this.keuze == this.cardset1) {
                    if (!this.start) {
                        this.cv.changecards("start1");
                        this.tm.fix();
                    }
                }
                else if (this.keuze == this.cardset2) {
                    if (!this.start) {
                        this.cv.changecards("start2");
                        this.tm.fix();
                    }
                }
                else if (this.keuze == this.cardset3) {
                    if (!this.start) {
                        this.cv.changecards("start3");
                        this.tm.fix();
                    }
                }
                else if (this.keuze == this.son) {
                    if (!this.start) {
                        this.cv.sound = true;
                        this.tm.fix();
                    }
                }
                else if (this.keuze == this.difhard) {
                    new Message().setVisible(true);
                    if (!this.start) {
                        this.tm.fix();
                    }
                    if (!this.start) {
                        this.cv.easy = false;
                    }
                }
                else if (this.keuze == this.difeasy) {
                    new Message().setVisible(true);
                    if (!this.start) {
                        this.tm.fix();
                    }
                    if (!this.start) {
                        this.cv.easy = true;
                    }
                }
                else if (this.keuze == this.help) {
                    final Helpbox helpbox = new Helpbox("Help");
                }
            }
        }
    }
    
    public void getPictures() {
        this.mem.getDocumentBase();
        if (this.dukeThread == null) {
            (this.dukeThread = new Thread(this)).start();
        }
        this.background = this.mem.getImage(this.mem.getDocumentBase(), this.mem.getParameter("background"));
        this.photo = this.mem.getImage(this.mem.getDocumentBase(), "photo.jpg");
        this.pict = this.mem.getImage(this.mem.getDocumentBase(), "pict.gif");
        this.color = this.mem.getImage(this.mem.getDocumentBase(), "color.gif");
        this.texture = this.mem.getImage(this.mem.getDocumentBase(), "texture.gif");
        this.duke1 = this.mem.getImage(this.mem.getDocumentBase(), "duke1.gif");
        this.duke2 = this.mem.getImage(this.mem.getDocumentBase(), "duke2.gif");
        this.mt.addImage(this.background, 0);
        this.mt.addImage(this.pict, 0);
        this.mt.addImage(this.photo, 0);
        this.mt.addImage(this.color, 0);
        this.mt.addImage(this.texture, 0);
        try {
            this.mt.waitForAll(0L);
        }
        catch (InterruptedException ex) {}
    }
    
    public void paint(final Graphics graphics) {
        if (this.start) {
            for (int i = 0; i < 510; i += 50) {
                for (int j = 0; j < 510; j += 50) {
                    graphics.drawImage(this.background, i, j, this);
                }
            }
            graphics.drawImage(this.pict, 80, 160, this);
            graphics.drawImage(this.color, 200, 160, this);
            graphics.drawImage(this.texture, 320, 160, this);
        }
        else {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, 600, 600);
        }
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Verdana", 1, 40));
        graphics.drawImage(this.photo, 40, 50, this);
        graphics.setFont(new Font("Verdana", 1, 16));
        graphics.drawString(this.mem.companyname, 200, 380);
    }
    
    public void restart() {
        if (!this.start) {
            this.cv.restart();
        }
    }
    
    public void run() {
        while (true) {
            ++this.refresh;
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
    
    public void stop() {
        this.dukeThread.stop();
        this.dukeThread = null;
        try {
            this.cv.messageThread.stop();
        }
        catch (Exception ex) {}
    }
    
    public void update(final Graphics graphics) {
        if (this.wave) {
            graphics.drawImage(this.duke1, 50, 310, this);
            this.wave = false;
        }
        else {
            graphics.drawImage(this.duke2, 50, 310, this);
            this.wave = true;
        }
        if (this.wave2) {
            graphics.drawImage(this.duke1, 380, 310, this);
            this.wave2 = false;
        }
        else {
            graphics.drawImage(this.duke2, 380, 310, this);
            this.wave2 = true;
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.mem.ok1.setLabel("Restart");
        this.dukeThread = null;
        this.tm.timeThread = null;
        this.cv.messageThread = null;
        this.dispose();
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
