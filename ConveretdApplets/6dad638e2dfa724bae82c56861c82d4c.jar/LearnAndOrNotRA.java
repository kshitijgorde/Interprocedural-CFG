import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipInputStream;
import java.net.URL;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class LearnAndOrNotRA extends Applet implements ActionListener, ItemListener, MouseListener, MouseMotionListener
{
    private Banner ban;
    private Image gbuffer;
    private Graphics gbuf;
    private Dimension dim;
    private Button[] knoppen;
    private Button activeer;
    private Label tekst;
    private int selected;
    private int mx;
    private int my;
    private int linex;
    private int liney;
    private boolean actief;
    private Punten punten;
    private Choice keuze;
    private String[] files;
    
    public LearnAndOrNotRA() {
        this.knoppen = new Button[12];
        this.selected = 0;
        this.mx = -1;
        this.my = -1;
        this.linex = -1;
        this.liney = -1;
        this.actief = false;
    }
    
    public void init() {
        System.out.println("\nLearnAndOrNot Version 1.0");
        System.out.println("*************************\n");
        this.dim = this.getSize();
        this.setLayout(null);
        this.addBanner();
        final Dimension dim = this.dim;
        dim.height -= 60;
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(Verbinding.foto = this.getImage(this.getCodeBase(), "gates.gif"), 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            System.out.println("ERROR loading picture gates.gif");
            this.showStatus("ERROR loading picture gates.gif");
        }
        this.tekst = new Label("Select an example:", 1);
        this.keuze = new Choice();
        this.files = new String[Integer.parseInt(this.getParameter("NumberOfFiles"))];
        for (int i = 0; i < this.files.length; ++i) {
            this.files[i] = this.getParameter("FileName" + (i + 1));
            this.keuze.addItem(this.getParameter("FileTag" + (i + 1)));
        }
        this.keuze.addItem("New...");
        this.activeer = new Button("Go to test mode");
        this.knoppen[0] = new Button("Arrow");
        this.knoppen[1] = new Button("Delete");
        this.knoppen[2] = new Button("Line");
        this.knoppen[3] = new Button("NOT");
        this.knoppen[4] = new Button("AND");
        this.knoppen[5] = new Button("NAND");
        this.knoppen[6] = new Button("OR");
        this.knoppen[7] = new Button("NOR");
        this.knoppen[8] = new Button("XOR");
        this.knoppen[9] = new Button("NXOR");
        this.knoppen[10] = new Button("Switch");
        this.knoppen[11] = new Button("Lamp");
        for (int j = 0; j < this.knoppen.length; ++j) {
            this.knoppen[j].setBounds(10 + j / 2 * (this.dim.width - 20) / 6, 10 + j % 2 * 20, (this.dim.width - 20) / 6, 20);
            this.knoppen[j].addActionListener(this);
            this.add(this.knoppen[j]);
        }
        this.activeer.setBounds(this.dim.width / 2, this.dim.height - 30, this.dim.width / 2 - 10, 20);
        this.activeer.addActionListener(this);
        this.tekst.setBounds(10, 60, this.dim.width / 2 - 10, 20);
        this.keuze.setBounds(this.dim.width / 2, 60, this.dim.width / 2 - 10, 20);
        this.keuze.addItemListener(this);
        this.add(this.activeer);
        this.add(this.tekst);
        this.add(this.keuze);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.laadFile(this.files[0]);
    }
    
    private void laadFile(final String s) {
        this.tekst.setText("Loading...");
        try {
            final ZipInputStream zipInputStream = new ZipInputStream(new URL(this.getDocumentBase(), s).openStream());
            zipInputStream.getNextEntry();
            final ObjectInputStream objectInputStream = new ObjectInputStream(zipInputStream);
            this.punten = (Punten)objectInputStream.readObject();
            zipInputStream.closeEntry();
            objectInputStream.close();
            this.punten.activeer();
            this.actief = true;
            this.tekst.setText("Click on the switches to test the circuit");
            this.activeer.setLabel("Go to edit mode");
        }
        catch (Exception ex) {
            System.out.println("ERROR: " + ex);
            this.tekst.setText("ERROR with loading");
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (this.keuze.getSelectedIndex() >= this.files.length) {
            this.punten = new Punten(this.dim.width - 20, this.dim.height - 130);
            this.actief = false;
            this.tekst.setText("Select an editing tool");
            this.activeer.setLabel("Go to test mode");
        }
        else {
            this.laadFile(this.files[this.keuze.getSelectedIndex()]);
        }
        this.repaint();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.linex = -1;
        this.liney = -1;
        this.setCursor(new Cursor(0));
        if (!this.actief) {
            if (actionEvent.getSource() == this.knoppen[0]) {
                this.selected = 0;
                this.tekst.setText("Arrow selected");
            }
            if (actionEvent.getSource() == this.knoppen[1]) {
                this.selected = 1;
                this.tekst.setText("Deleting selected");
            }
            if (actionEvent.getSource() == this.knoppen[2]) {
                this.selected = 2;
                this.tekst.setText("Click on the origin");
            }
            if (actionEvent.getSource() == this.knoppen[3]) {
                this.selected = 3;
                this.tekst.setText("NOT");
            }
            if (actionEvent.getSource() == this.knoppen[4]) {
                this.selected = 4;
                this.tekst.setText("AND");
            }
            if (actionEvent.getSource() == this.knoppen[5]) {
                this.selected = 5;
                this.tekst.setText("NAND");
            }
            if (actionEvent.getSource() == this.knoppen[6]) {
                this.selected = 6;
                this.tekst.setText("OR");
            }
            if (actionEvent.getSource() == this.knoppen[7]) {
                this.selected = 7;
                this.tekst.setText("NOR");
            }
            if (actionEvent.getSource() == this.knoppen[8]) {
                this.selected = 8;
                this.tekst.setText("XOR");
            }
            if (actionEvent.getSource() == this.knoppen[9]) {
                this.selected = 9;
                this.tekst.setText("NXOR");
            }
            if (actionEvent.getSource() == this.knoppen[10]) {
                this.selected = 10;
                this.tekst.setText("Switch");
            }
            if (actionEvent.getSource() == this.knoppen[11]) {
                this.selected = 11;
                this.tekst.setText("Lamp");
            }
        }
        if (actionEvent.getSource() == this.activeer) {
            if (this.actief) {
                this.punten.deActiveer();
                this.tekst.setText("Select an editing tool");
                this.activeer.setLabel("Go to test mode");
            }
            else {
                this.punten.activeer();
                this.tekst.setText("Click on the switches to test your circuit");
                this.activeer.setLabel("Go to edit mode");
            }
            this.actief = !this.actief;
        }
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics gbuf) {
        if (this.gbuffer == null) {
            try {
                this.gbuffer = this.createImage(this.dim.width - 20, this.dim.height - 130);
                this.gbuf = this.gbuffer.getGraphics();
            }
            catch (Exception ex) {
                this.gbuf = gbuf;
                this.gbuffer = null;
            }
        }
        this.gbuf.setColor(Color.white);
        this.gbuf.fillRect(0, 0, this.dim.width - 20, this.dim.height - 130);
        this.punten.paint(this.gbuf, this);
        if (this.mx != -1) {
            switch (this.selected) {
                case 2: {
                    if (this.linex >= 0) {
                        this.gbuf.setColor(Color.black);
                        this.gbuf.drawLine(this.linex, this.liney, this.mx, this.my);
                        break;
                    }
                    break;
                }
                case 3: {
                    this.gbuf.drawImage(Verbinding.foto, this.mx - 5, this.my - 5, this.mx + 5, this.my + 5, 180, 90, 190, 100, this);
                    break;
                }
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11: {
                    this.gbuf.drawImage(Verbinding.foto, this.mx - 15, this.my - 15, this.mx + 15, this.my + 15, (this.selected - 4) * 30, 0, (this.selected - 3) * 30, 30, this);
                    break;
                }
            }
        }
        if (this.gbuffer != null) {
            gbuf.drawImage(this.gbuffer, 10, 90, this.dim.width - 20, this.dim.height - 130, this);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.actief) {
            if (y > 90 && y < this.dim.height - 40 && x > 10 && x < this.dim.width - 10 && this.punten.isSwitch(x - 10, y - 90)) {
                this.punten.setSwitch(x - 10, y - 90);
                this.repaint();
            }
        }
        else if (y > 90 && y < this.dim.height - 40 && x > 10 && x < this.dim.width - 10) {
            if (this.selected == 2) {
                if (this.linex >= 0) {
                    this.tekst.setText(this.punten.setLine(this.linex, this.liney, x - 10, y - 90));
                    this.linex = -1;
                    this.liney = -1;
                }
                else {
                    this.linex = this.punten.getMouseX(this.selected, x - 10);
                    this.liney = this.punten.getMouseY(this.selected, y - 90);
                }
            }
            else {
                this.tekst.setText(this.punten.setVerbinding(x - 10, y - 90, this.selected));
            }
            this.repaint();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.mx = -1;
        this.repaint();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.actief) {
            if (y > 90 && y < this.dim.height - 40 && x > 10 && x < this.dim.width - 10 && this.punten.isSwitch(x - 10, y - 90)) {
                this.setCursor(new Cursor(12));
            }
            else {
                this.setCursor(new Cursor(0));
            }
        }
        else if (y > 90 && y < this.dim.height - 40 && x > 10 && x < this.dim.width - 10) {
            int mx = this.punten.getMouseX(this.selected, x - 10);
            int my = this.punten.getMouseY(this.selected, y - 90);
            if (this.selected == 2 && this.linex >= 0) {
                if (Math.abs(mx - this.linex) <= Math.abs(my - this.liney)) {
                    mx = this.linex;
                }
                else {
                    my = this.liney;
                }
            }
            if (this.selected == 2 && this.mx < 0) {
                this.setCursor(new Cursor(1));
            }
            if (mx != this.mx || my != this.my) {
                this.mx = mx;
                this.my = my;
                this.repaint();
            }
        }
        else if (this.mx != -1) {
            if (this.selected == 2) {
                this.setCursor(new Cursor(0));
            }
            this.mx = -1;
            this.repaint();
        }
    }
    
    public void addBanner() {
        System.out.println("\n**********************************************************************************");
        System.out.println("* Unregistered applet, written by Geoffrey, contact at lunalifelover@hotmail.com *");
        System.out.println("* Get the registerd version without banner from http://www.realapplets.com       *");
        System.out.println("* Removal of banner without registration is punishable in a court of law.        *");
        System.out.println("**********************************************************************************\n");
        final Dimension size = this.getSize();
        (this.ban = new Banner(size.width, this)).setBounds(0, size.height - 60, size.width, 60);
        this.add(this.ban);
    }
}
