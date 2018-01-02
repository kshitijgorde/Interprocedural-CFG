import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.awt.Button;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.Dialog;
import java.awt.Image;
import java.awt.Choice;
import java.awt.TextArea;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class EspanolFormSquare extends Applet implements ActionListener, MouseListener
{
    String urlHost;
    String urlPort;
    String textHost;
    String textPort;
    String textTemplate;
    TextArea sourceArea;
    Choice languageChoice;
    Image topImage;
    Image bottomImage;
    Dialog messageDlg;
    
    public EspanolFormSquare() {
        this.urlHost = "fets3.freetranslation.com";
        this.urlPort = "80";
        this.textHost = "ets.freetranslation.com";
        this.textPort = "80";
        this.textTemplate = "results_en-us.htm";
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final StringBuffer sb = new StringBuffer(500);
        if (actionEvent.getActionCommand().equals("Translate")) {
            final String text = this.sourceArea.getText();
            if (text.length() == 0) {
                return;
            }
            if (text.length() > 1500) {
                Container container;
                for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
                (this.messageDlg = new Dialog((Frame)container, "Error", true)).setLayout(new BorderLayout(10, 10));
                this.messageDlg.add(new Label("Your request exceeds 1,500 characters."), "North");
                this.messageDlg.add(new Label("Reduce request length and submit again."), "Center");
                final Button button = new Button();
                button.setLabel("Ok");
                button.setActionCommand("Ok");
                button.addActionListener(this);
                this.messageDlg.add(button, "South");
                this.messageDlg.pack();
                this.messageDlg.show();
                return;
            }
            final boolean regionMatches = text.regionMatches(true, 0, "http://", 0, 7);
            sb.append("http://");
            if (regionMatches) {
                sb.append(this.urlHost);
                if (!this.urlPort.equals("80")) {
                    sb.append(":");
                    sb.append(this.urlPort);
                }
                sb.append("/?url=");
                sb.append(URLEncoder.encode(text));
            }
            else {
                sb.append(this.textHost);
                if (!this.textPort.equals("80")) {
                    sb.append(":");
                    sb.append(this.textPort);
                }
                sb.append("/?srcText=");
                sb.append(URLEncoder.encode(text));
                sb.append("&mode=html&template=");
                sb.append(URLEncoder.encode(this.textTemplate));
            }
            sb.append("&sequence=core&language=");
            final String string = this.languageChoice.getSelectedItem().toString();
            if (string.equals("Espa\u00f1ol a Ingl\u00e9s")) {
                sb.append(URLEncoder.encode("Spanish/English"));
            }
            else if (string.equals("Franc\u00e9s a Ingl\u00e9s")) {
                sb.append(URLEncoder.encode("French/English"));
            }
            else if (string.equals("Alem\u00e1n a Ingl\u00e9s")) {
                sb.append(URLEncoder.encode("German/English"));
            }
            else if (string.equals("Italiano a Ingl\u00e9s")) {
                sb.append(URLEncoder.encode("Italian/English"));
            }
            else if (string.equals("Holand\u00e9s a Ingl\u00e9s")) {
                sb.append(URLEncoder.encode("Dutch/English"));
            }
            else if (string.equals("Portugu\u00e9s a Ingl\u00e9s")) {
                sb.append(URLEncoder.encode("Portuguese/English"));
            }
            else if (string.equals("Ingl\u00e9s a Espa\u00f1ol")) {
                sb.append(URLEncoder.encode("English/Spanish"));
            }
            else if (string.equals("Ingl\u00e9s a Franc\u00e9s")) {
                sb.append(URLEncoder.encode("English/French"));
            }
            else if (string.equals("Ingl\u00e9s a Holand\u00e9s")) {
                sb.append(URLEncoder.encode("English/Dutch"));
            }
            else if (string.equals("Ingl\u00e9s a Alem\u00e1n")) {
                sb.append(URLEncoder.encode("English/German"));
            }
            else if (string.equals("Ingl\u00e9s a Italiano")) {
                sb.append(URLEncoder.encode("English/Italian"));
            }
            else if (string.equals("Ingl\u00e9s a Portugu\u00e9s")) {
                sb.append(URLEncoder.encode("English/Portuguese"));
            }
            else if (string.equals("Ingl\u00e9s a Noruego")) {
                sb.append(URLEncoder.encode("English/Norwegian"));
            }
            try {
                this.getAppletContext().showDocument(new URL(sb.toString()));
            }
            catch (MalformedURLException ex) {}
        }
        else if (actionEvent.getActionCommand().equals("?")) {
            try {
                this.getAppletContext().showDocument(new URL("http://207.228.216.173/defaultHelp.htm"), "helpwin");
            }
            catch (MalformedURLException ex2) {}
        }
        else {
            this.messageDlg.hide();
            this.messageDlg = null;
        }
    }
    
    public void init() {
        final String parameter = this.getParameter("urlHost");
        this.urlHost = ((parameter != null) ? parameter : this.urlHost);
        final String parameter2 = this.getParameter("urlPort");
        this.urlPort = ((parameter2 != null) ? parameter2 : this.urlPort);
        final String parameter3 = this.getParameter("textHost");
        this.textHost = ((parameter3 != null) ? parameter3 : this.textHost);
        final String parameter4 = this.getParameter("textPort");
        this.textPort = ((parameter4 != null) ? parameter4 : this.textPort);
        final String parameter5 = this.getParameter("textTemplate");
        this.textTemplate = ((parameter5 != null) ? parameter5 : this.textTemplate);
        this.topImage = this.getImage(this.getCodeBase(), "vertical-top-esp.gif");
        this.bottomImage = this.getImage(this.getCodeBase(), "vertical-bot.gif");
        this.initComponents();
    }
    
    protected void initComponents() {
        this.setLayout(null);
        this.setBackground(Color.lightGray);
        final Canvas canvas = new Canvas() {
            public void paint(final Graphics graphics) {
                graphics.drawImage(EspanolFormSquare.this.topImage, 0, 0, this);
            }
        };
        canvas.setBackground(Color.lightGray);
        canvas.setBounds(0, 0, 150, 36);
        this.add(canvas);
        final Label label = new Label("Entrar el texto o url para traducir:");
        label.setBackground(Color.lightGray);
        label.setForeground(Color.black);
        label.setFont(new Font("Arial", 0, 9));
        label.setBounds(0, 40, 150, 15);
        this.add(label);
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(1, 0, 0));
        panel.setBackground(Color.lightGray);
        panel.setBounds(0, 55, 150, 35);
        (this.sourceArea = new TextArea("", 1, 20, 1)).setBackground(Color.white);
        this.sourceArea.setForeground(Color.black);
        this.sourceArea.setFont(new Font("Arial", 0, 10));
        if (this.getParameter("insertUrl") != null) {
            this.sourceArea.setText(this.getDocumentBase().toString());
        }
        final String parameter = this.getParameter("insertText");
        if (parameter != null) {
            if (parameter.length() > 0) {
                this.sourceArea.setText(parameter);
            }
            else {
                this.sourceArea.setText("Enter text or Url to translate here");
            }
        }
        panel.add(this.sourceArea);
        this.add(panel);
        final Label label2 = new Label("Elegir la langua:");
        label2.setBackground(Color.lightGray);
        label2.setForeground(Color.black);
        label2.setFont(new Font("Arial", 0, 11));
        label2.setBounds(10, 89, 140, 15);
        this.add(label2);
        final Panel panel2 = new Panel();
        panel2.setLayout(new FlowLayout(0, 0, 0));
        panel2.setBackground(Color.lightGray);
        panel2.setBounds(10, 105, 150, 20);
        (this.languageChoice = new Choice()).setFont(new Font("Arial", 0, 10));
        this.languageChoice.setBackground(Color.white);
        this.languageChoice.setForeground(Color.black);
        this.languageChoice.addItem("Espa\u00f1ol a Ingl\u00e9s");
        this.languageChoice.addItem("Franc\u00e9s a Ingl\u00e9s");
        this.languageChoice.addItem("Alem\u00e1n a Ingl\u00e9s");
        this.languageChoice.addItem("Italiano a Ingl\u00e9s");
        this.languageChoice.addItem("Holand\u00e9s a Ingl\u00e9s");
        this.languageChoice.addItem("Portugu\u00e9s a Ingl\u00e9s");
        this.languageChoice.addItem("Ingl\u00e9s a Espa\u00f1ol");
        this.languageChoice.addItem("Ingl\u00e9s a Franc\u00e9s");
        this.languageChoice.addItem("Ingl\u00e9s a Alem\u00e1n");
        this.languageChoice.addItem("Ingl\u00e9s a Italiano");
        this.languageChoice.addItem("Ingl\u00e9s a Holand\u00e9s");
        this.languageChoice.addItem("Ingl\u00e9s a Portugu\u00e9s");
        this.languageChoice.addItem("Ingl\u00e9s a Noruego");
        panel2.add(this.languageChoice);
        this.add(panel2);
        final Panel panel3 = new Panel();
        panel3.setLayout(new FlowLayout(1, 10, 0));
        panel3.setBackground(Color.lightGray);
        panel3.setBounds(0, 130, 150, 30);
        final Button button = new Button();
        button.setLabel("Traducir");
        button.setFont(new Font("Dialog", 0, 11));
        button.setBackground(new Color(212, 208, 200));
        button.setForeground(Color.black);
        button.setActionCommand("Translate");
        button.addActionListener(this);
        panel3.add(button);
        final Button button2 = new Button();
        button2.setLabel("?");
        button2.setFont(new Font("Dialog", 0, 11));
        button2.setBackground(new Color(212, 208, 200));
        button2.setForeground(Color.black);
        button2.setActionCommand("?");
        button2.addActionListener(this);
        panel3.add(button2);
        this.add(panel3);
        final Canvas canvas2 = new Canvas() {
            public void paint(final Graphics graphics) {
                graphics.drawImage(EspanolFormSquare.this.bottomImage, 0, 0, this);
            }
        };
        canvas2.setBackground(Color.lightGray);
        canvas2.setCursor(new Cursor(12));
        canvas2.addMouseListener(this);
        canvas2.setBounds(0, 160, 150, 21);
        this.add(canvas2);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        try {
            this.getAppletContext().showDocument(new URL("http://207.228.216.173/"));
        }
        catch (MalformedURLException ex) {}
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
}
