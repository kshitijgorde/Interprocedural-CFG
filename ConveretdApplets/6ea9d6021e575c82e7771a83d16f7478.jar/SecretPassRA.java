import java.io.InputStream;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.LayoutManager;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SecretPassRA extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    private TextField tweede;
    private Button derde;
    private String[] teksten;
    private Color[] kleuren;
    private Image foto;
    private Image gbuffer;
    private Graphics gbuf;
    private int status;
    private int numb;
    private Dimension dim;
    private Font font;
    private FontMetrics fm;
    private Banner2 ban;
    private boolean windoze;
    private boolean imoze;
    private boolean binnen;
    
    public SecretPassRA() {
        this.status = 0;
        this.numb = 0;
        this.windoze = false;
        this.imoze = false;
        this.binnen = false;
    }
    
    public void init() {
        System.out.println("\nSecretPass Version 1.2");
        System.out.println("**********************\n");
        this.kleuren = new Color[4];
        this.setBackground(this.kleuren[0] = new Color(Integer.parseInt(this.getParameter("BackgroundColor"), 16)));
        this.setForeground(this.kleuren[1] = new Color(Integer.parseInt(this.getParameter("ForegroundColor"), 16)));
        this.kleuren[2] = new Color(Integer.parseInt(this.getParameter("LinkColor1"), 16));
        this.kleuren[3] = new Color(Integer.parseInt(this.getParameter("LinkColor2"), 16));
        this.teksten = new String[6];
        for (int i = 0; i < 5; ++i) {
            this.teksten[i] = this.getParameter("Tag" + (i + 1));
        }
        this.teksten[5] = this.getParameter("Extension");
        this.dim = this.getSize();
        this.font = new Font(this.getParameter("FontStyle"), 0, this.dim.height * 3 / 8 - 22);
        this.setLayout(null);
        this.addBanner2();
        (this.tweede = new TextField()).setBackground(Color.white);
        this.tweede.setForeground(Color.black);
        this.tweede.setBounds(10, this.dim.height / 2 - 10, this.dim.width - 20, 20);
        this.tweede.setEchoChar('*');
        this.tweede.addActionListener(this);
        final String upperCase = this.getParameter("UseImage").toUpperCase();
        if (upperCase.equals("YES") || upperCase.equals("TRUE")) {
            this.imoze = true;
            final MediaTracker mediaTracker = new MediaTracker(this);
            try {
                mediaTracker.addImage(this.foto = this.getImage(this.getCodeBase(), this.getParameter("ImageSource")), 0);
                mediaTracker.waitForAll();
            }
            catch (InterruptedException ex) {
                System.out.println("Error with loading picture " + this.getParameter("ImageSource"));
                this.imoze = false;
            }
        }
        final String upperCase2 = this.getParameter("WindowsButton").toUpperCase();
        if (!this.imoze || upperCase2.equals("YES") || upperCase2.equals("TRUE")) {
            this.windoze = true;
            (this.derde = new Button(this.teksten[1])).setForeground(Color.black);
            this.derde.setBounds(10, this.dim.height / 2 + 20, this.dim.width - 20, this.dim.height / 2 - 30);
            this.derde.addActionListener(this);
        }
        if (!this.windoze) {
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }
        this.add(this.tweede);
        if (this.windoze) {
            this.add(this.derde);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics gbuf) {
        if (this.gbuffer == null) {
            try {
                this.gbuffer = this.createImage(this.dim.width, this.dim.height);
                (this.gbuf = this.gbuffer.getGraphics()).setFont(this.font);
                this.fm = this.gbuf.getFontMetrics();
            }
            catch (Exception ex) {
                (this.gbuf = gbuf).setFont(this.font);
                this.fm = this.gbuf.getFontMetrics();
                this.gbuffer = null;
            }
        }
        if (this.imoze) {
            if (this.windoze) {
                this.gbuf.drawImage(this.foto, 0, 0, this.dim.width, this.dim.height, this);
            }
            else {
                this.gbuf.drawImage(this.foto, 0, 0, this.dim.width, this.dim.height, 0, 0, this.foto.getWidth(this), this.foto.getHeight(this) * 2 / 3 + 20, this);
                if (this.binnen) {
                    this.gbuf.drawImage(this.foto, 10, this.dim.height / 2 + 20, this.dim.width - 10, this.dim.height - 10, 10, this.foto.getHeight(this) * 2 / 3 + 20, this.foto.getWidth(this) - 10, this.foto.getHeight(this), this);
                }
            }
        }
        else {
            this.gbuf.setColor(this.kleuren[0]);
            this.gbuf.fillRect(0, 0, this.dim.width, this.dim.height);
        }
        this.gbuf.setColor(this.kleuren[1]);
        this.gbuf.drawString(this.teksten[this.status], (this.dim.width - this.fm.stringWidth(this.teksten[this.status])) / 2, this.fm.getMaxAscent() + this.dim.height / 16 + 6);
        if (this.binnen) {
            this.gbuf.setColor(this.kleuren[3]);
        }
        else {
            this.gbuf.setColor(this.kleuren[2]);
        }
        this.gbuf.drawString(this.teksten[1], (this.dim.width - this.fm.stringWidth(this.teksten[1])) / 2, this.dim.height * 15 / 16 - this.fm.getMaxDescent() - 6);
        if (this.gbuffer != null) {
            gbuf.drawImage(this.gbuffer, 0, 0, this);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x > 10 && x < this.dim.width - 10 && y > this.dim.height / 2 + 20 && y < this.dim.height - 10) {
            this.voeruit();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.binnen) {
            this.binnen = false;
            this.setCursor(new Cursor(0));
            this.repaint();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.bewogen(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.bewogen(mouseEvent);
    }
    
    private void bewogen(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x > 10 && x < this.dim.width - 10 && y > this.dim.height / 2 + 20 && y < this.dim.height - 10) {
            if (!this.binnen) {
                this.binnen = true;
                this.setCursor(new Cursor(12));
                this.repaint();
            }
        }
        else if (this.binnen) {
            this.binnen = false;
            this.setCursor(new Cursor(0));
            this.repaint();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.voeruit();
    }
    
    private void voeruit() {
        if (this.numb < 3) {
            InputStream openStream = null;
            try {
                final URL url = new URL(this.getDocumentBase(), this.tweede.getText() + "." + this.teksten[5]);
                openStream = url.openStream();
                int read = 0;
                for (int i = 0; i < 5; ++i) {
                    read = openStream.read();
                }
                if (read < 0) {
                    throw new Exception();
                }
                this.status = 2;
                this.repaint();
                openStream.close();
                this.getAppletContext().showDocument(url, this.getParameter("TargetFrame"));
            }
            catch (Exception ex) {
                try {
                    if (openStream != null) {
                        openStream.close();
                    }
                }
                catch (Exception ex2) {}
                ++this.numb;
                if (this.numb >= 3) {
                    try {
                        this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.getParameter("WrongPassURL")), this.getParameter("TargetFrame"));
                    }
                    catch (Exception ex3) {
                        this.status = 4;
                    }
                }
                else {
                    this.status = 3;
                    this.repaint();
                }
            }
            this.tweede.setText("");
            this.tweede.requestFocus();
        }
        else {
            this.status = 4;
            this.repaint();
        }
    }
    
    private void addBanner2() {
        System.out.println("\n**********************************************************************************");
        System.out.println("* Unregistered applet, written by Luna-Tic, contact at lunalifelover@hotmail.com *");
        System.out.println("* Get the registerd version without banner from http://www.realapplets.com       *");
        System.out.println("* Removal of banner without registration is punishable in a court of law.        *");
        System.out.println("**********************************************************************************\n");
        (this.ban = new Banner2(this)).setBounds(this.getSize().width - 20, 0, 20, 20);
        this.add(this.ban);
    }
}
