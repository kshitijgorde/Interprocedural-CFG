import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.net.URL;
import java.net.URLEncoder;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Label;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GemGame extends Applet
{
    Label label1;
    Label label2;
    Label label3;
    Choice choice1;
    Button button1;
    Label scoreLabel;
    Button button2;
    Button button3;
    Label timeLabel;
    GemCanvas canvas1;
    
    public void init() {
        this.setLayout(null);
        this.setSize(388, 328);
        this.setFont(new Font("Dialog", 0, 12));
        this.setForeground(new Color(16776960));
        this.setBackground(new Color(0));
        try {
            final String lowerCase = this.getDocumentBase().getProtocol().toLowerCase();
            try {
                this.getAppletContext().showDocument(new URL("http://www.4webgames.com/gemgame/popunder.php?host=" + URLEncoder.encode(this.getDocumentBase().toString())), "_new");
            }
            catch (Exception ex) {}
            if (lowerCase.equals("file")) {
                (this.label1 = new Label("This version not intended for desktop play.", 1)).setBounds(0, 70, 388, 30);
                this.label1.setFont(new Font("Serif", 1, 16));
                this.label1.setForeground(new Color(16756655));
                this.label1.setVisible(true);
                this.add(this.label1);
                (this.label2 = new Label("A Windows version can be found at", 1)).setBounds(0, 100, 388, 30);
                this.label2.setFont(new Font("Serif", 1, 16));
                this.label2.setForeground(new Color(16756655));
                this.label2.setVisible(true);
                this.add(this.label2);
                (this.label3 = new Label("http://www.4webgames.com", 1)).setBounds(0, 150, 388, 30);
                this.label3.setFont(new Font("Serif", 1, 16));
                this.label3.setForeground(new Color(16756655));
                this.label3.setVisible(true);
                this.add(this.label3);
                return;
            }
            (this.canvas1 = new GemCanvas(this)).setBounds(84, 24, 300, 300);
            this.add(this.canvas1);
        }
        catch (Exception ex2) {}
        (this.label1 = new Label("The Gem Game, (C) 2001 4WebGames.com", 1)).setBounds(0, 0, 388, 24);
        this.label1.setFont(new Font("Serif", 2, 16));
        this.label1.setForeground(new Color(16756655));
        this.add(this.label1);
        (this.label2 = new Label("Score:", 1)).setBounds(0, 24, 84, 24);
        this.label2.setFont(new Font("Serif", 0, 16));
        this.label2.setForeground(new Color(16711935));
        this.label2.setBackground(new Color(0));
        this.add(this.label2);
        (this.label3 = new Label("Time:", 1)).setVisible(false);
        this.label3.setBounds(0, 72, 84, 24);
        this.label3.setFont(new Font("Serif", 0, 16));
        this.label3.setForeground(new Color(16711935));
        this.label3.setBackground(new Color(0));
        this.add(this.label3);
        (this.choice1 = new Choice()).addItem("Level 1");
        this.choice1.addItem("Level 2");
        this.choice1.addItem("Level 3");
        this.choice1.addItem("Level 4");
        this.choice1.addItem("Level 5");
        try {
            this.choice1.select(0);
        }
        catch (IllegalArgumentException ex3) {}
        this.add(this.choice1);
        this.choice1.setBounds(0, 132, 84, 24);
        this.choice1.setFont(new Font("Dialog", 0, 12));
        this.choice1.setForeground(new Color(65535));
        this.choice1.setBackground(new Color(0));
        (this.button1 = new Button()).setLabel("New Game");
        this.button1.setBounds(0, 156, 84, 24);
        this.button1.setForeground(new Color(65535));
        this.button1.setBackground(new Color(4210752));
        this.add(this.button1);
        (this.scoreLabel = new Label("0", 1)).setBounds(0, 48, 84, 24);
        this.scoreLabel.setFont(new Font("Serif", 1, 16));
        this.scoreLabel.setForeground(new Color(65535));
        this.scoreLabel.setBackground(new Color(0));
        this.add(this.scoreLabel);
        (this.button2 = new Button()).setLabel("Hint");
        this.button2.setBounds(0, 204, 84, 24);
        this.button2.setFont(new Font("Serif", 0, 16));
        this.button2.setForeground(new Color(65535));
        this.button2.setBackground(new Color(4210752));
        this.add(this.button2);
        (this.button3 = new Button()).setLabel("4WebGames");
        this.button3.setBounds(0, 250, 84, 24);
        this.button3.setFont(new Font("Serif", 0, 16));
        this.button3.setForeground(new Color(65535));
        this.button3.setBackground(new Color(4210752));
        this.add(this.button3);
        (this.timeLabel = new Label("0", 1)).setVisible(false);
        this.timeLabel.setBounds(0, 96, 84, 24);
        this.timeLabel.setFont(new Font("Serif", 1, 16));
        this.timeLabel.setForeground(new Color(65535));
        this.timeLabel.setBackground(new Color(0));
        this.add(this.timeLabel);
        final SymAction symAction = new SymAction();
        this.button2.addActionListener(symAction);
        this.button1.addActionListener(symAction);
        this.button3.addActionListener(symAction);
        this.label1.addMouseListener(new SymMouse());
    }
    
    void button2_ActionPerformed(final ActionEvent actionEvent) {
        if (!this.canvas1.showHint) {
            final GemCanvas canvas1 = this.canvas1;
            canvas1.score -= 50;
            this.scoreLabel.setText(String.valueOf(this.canvas1.score));
            this.canvas1.showHint = true;
        }
    }
    
    void button1_ActionPerformed(final ActionEvent actionEvent) {
        this.canvas1.newGame(this.choice1.getSelectedIndex());
    }
    
    public void destroy() {
        this.canvas1.dieoff = true;
    }
    
    void button3_ActionPerformed(final ActionEvent actionEvent) {
        try {
            this.getAppletContext().showDocument(new URL("http://www.4webgames.com/?host=" + URLEncoder.encode(this.getDocumentBase().toString())), "_top");
        }
        catch (Exception ex) {}
    }
    
    void label1_MousePressed(final MouseEvent mouseEvent) {
        try {
            this.getAppletContext().showDocument(new URL("http://www.4webgames.com/?host=" + URLEncoder.encode(this.getDocumentBase().toString())), "_top");
        }
        catch (Exception ex) {}
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == GemGame.this.button2) {
                GemGame.this.button2_ActionPerformed(actionEvent);
                return;
            }
            if (source == GemGame.this.button1) {
                GemGame.this.button1_ActionPerformed(actionEvent);
                return;
            }
            if (source == GemGame.this.button3) {
                GemGame.this.button3_ActionPerformed(actionEvent);
            }
        }
    }
    
    class SymMouse extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            if (mouseEvent.getSource() == GemGame.this.label1) {
                GemGame.this.label1_MousePressed(mouseEvent);
            }
        }
    }
}
