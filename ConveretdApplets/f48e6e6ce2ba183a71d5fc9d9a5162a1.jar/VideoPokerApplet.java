import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.Label;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class VideoPokerApplet extends Applet implements Bank, Runnable
{
    private static final int NUMGAMES = 4;
    private VideoPokerGame[] games;
    private VideoPoker videoPoker;
    public static final int FIVECENTS = 1;
    public static final int TWENTYFIVECENTS = 2;
    public static final int ONEDOLLAR = 3;
    public static final int FIVEDOLLAR = 4;
    private String[] gameNames;
    LinePanel linePanel1;
    Checkbox game1Radio;
    CheckboxGroup gameGroup;
    Checkbox game3Radio;
    Checkbox game2Radio;
    Checkbox game4Radio;
    LinePanel linePanel2;
    Checkbox fiveCentsRadio;
    CheckboxGroup amountGroup;
    Checkbox twentyfiveCentsRadio;
    Checkbox dollarRadio;
    Checkbox fiveDollarRadio;
    Label label1;
    Label label2;
    Label label3;
    Button playVideoPokerButton;
    LinePanel linePanel3;
    Label bankAmountLabel;
    Label bankLabel;
    private Checkbox[] gameRadios;
    private int amountInBank;
    
    public void init() {
        this.setLayout(null);
        this.setBackground(new Color(0, 128, 0));
        this.setSize(302, 253);
        this.setVisible(false);
        this.linePanel1.setLayout(null);
        this.add(this.linePanel1);
        this.linePanel1.setBounds(24, 48, 156, 120);
        this.game1Radio.setCheckboxGroup(this.gameGroup);
        this.game1Radio.setState(true);
        this.game1Radio.setLabel("Game 1");
        this.game1Radio.setEnabled(false);
        this.linePanel1.add(this.game1Radio);
        this.game1Radio.setBounds(12, 12, 132, 24);
        this.game1Radio.setVisible(false);
        this.game3Radio.setCheckboxGroup(this.gameGroup);
        this.game3Radio.setLabel("Game 3");
        this.game3Radio.setEnabled(false);
        this.linePanel1.add(this.game3Radio);
        this.game3Radio.setBounds(12, 60, 132, 24);
        this.game3Radio.setVisible(false);
        this.game2Radio.setCheckboxGroup(this.gameGroup);
        this.game2Radio.setLabel("Game 2");
        this.game2Radio.setEnabled(false);
        this.linePanel1.add(this.game2Radio);
        this.game2Radio.setBounds(12, 36, 132, 24);
        this.game2Radio.setVisible(false);
        this.game4Radio.setCheckboxGroup(this.gameGroup);
        this.game4Radio.setLabel("Game 4");
        this.game4Radio.setEnabled(false);
        this.linePanel1.add(this.game4Radio);
        this.game4Radio.setBounds(12, 84, 132, 24);
        this.game4Radio.setVisible(false);
        this.linePanel2.setLayout(null);
        this.add(this.linePanel2);
        this.linePanel2.setBounds(192, 48, 84, 120);
        this.fiveCentsRadio.setCheckboxGroup(this.amountGroup);
        this.fiveCentsRadio.setLabel("$ .05");
        this.linePanel2.add(this.fiveCentsRadio);
        this.fiveCentsRadio.setBounds(12, 12, 48, 24);
        this.twentyfiveCentsRadio.setCheckboxGroup(this.amountGroup);
        this.twentyfiveCentsRadio.setState(true);
        this.twentyfiveCentsRadio.setLabel("$ .25");
        this.linePanel2.add(this.twentyfiveCentsRadio);
        this.twentyfiveCentsRadio.setBounds(12, 36, 48, 24);
        this.dollarRadio.setCheckboxGroup(this.amountGroup);
        this.dollarRadio.setLabel("$1");
        this.linePanel2.add(this.dollarRadio);
        this.dollarRadio.setBounds(12, 60, 48, 24);
        this.fiveDollarRadio.setCheckboxGroup(this.amountGroup);
        this.fiveDollarRadio.setLabel("$5");
        this.linePanel2.add(this.fiveDollarRadio);
        this.fiveDollarRadio.setBounds(12, 84, 48, 24);
        this.label1.setText("Select Game to Play");
        this.label1.setAlignment(1);
        this.add(this.label1);
        this.label1.setFont(new Font("Dialog", 1, 12));
        this.label1.setBounds(71, 0, 156, 24);
        this.label2.setText("Video Poker Game");
        this.add(this.label2);
        this.label2.setBounds(24, 24, 156, 28);
        this.label3.setText("Amount");
        this.add(this.label3);
        this.label3.setBounds(192, 24, 60, 28);
        this.playVideoPokerButton.setLabel("Play Video Poker");
        this.playVideoPokerButton.setEnabled(false);
        this.add(this.playVideoPokerButton);
        this.playVideoPokerButton.setBackground(Color.lightGray);
        this.playVideoPokerButton.setBounds(24, 204, 120, 36);
        this.linePanel3.setLayout(null);
        this.add(this.linePanel3);
        this.linePanel3.setBounds(156, 204, 120, 36);
        this.bankAmountLabel.setText("$0.00");
        this.bankAmountLabel.setAlignment(1);
        this.linePanel3.add(this.bankAmountLabel);
        this.bankAmountLabel.setFont(new Font("Dialog", 1, 12));
        this.bankAmountLabel.setBounds(12, 12, 96, 12);
        this.bankLabel.setText("Bank");
        this.add(this.bankLabel);
        this.bankLabel.setBounds(156, 180, 84, 24);
        this.playVideoPokerButton.addActionListener(new SymAction());
    }
    
    public void run() {
        VideoPoker.setBank(this);
        this.showStatus("Loading Images...");
        CardPanel.setCardsImage(this.getImage(this.getCodeBase(), "faces.gif"), this.getImage(this.getCodeBase(), "back.gif"), this.getImage(this.getCodeBase(), "joker.gif"), this);
        this.showStatus("Loading Games...");
        for (int i = 0; i < 4; ++i) {
            this.gameRadios[i].setVisible(false);
        }
        int n = 0;
        for (int j = 0; j < 4; ++j) {
            final DefaultPokerGame loadGame = DefaultPokerGame.loadGame(this.gameNames[j], this);
            if (loadGame != null) {
                this.gameRadios[n].setEnabled(true);
                this.gameRadios[n].setLabel(loadGame.getName());
                this.gameRadios[n].setVisible(true);
                this.gameRadios[0].setState(true);
                this.playVideoPokerButton.setEnabled(true);
                this.games[n] = loadGame;
                ++n;
            }
        }
        this.showStatus("");
    }
    
    public void start() {
        new Thread(this).start();
    }
    
    private int getGame() {
        for (int i = 0; i < 4; ++i) {
            if (this.gameRadios[i].getState()) {
                return i;
            }
        }
        return 0;
    }
    
    private int getAmount() {
        if (this.fiveCentsRadio.getState()) {
            return 1;
        }
        if (this.twentyfiveCentsRadio.getState()) {
            return 2;
        }
        if (this.dollarRadio.getState()) {
            return 3;
        }
        if (this.fiveDollarRadio.getState()) {
            return 4;
        }
        return 0;
    }
    
    void playVideoPokerButton_ActionPerformed(final ActionEvent actionEvent) {
        this.videoPoker.setGame(this.games[this.getGame()], this.getAmount());
    }
    
    public void addToBank(final int n) {
        this.amountInBank += n;
        String s;
        int n2;
        if (this.amountInBank < 0) {
            s = "-$" + -this.amountInBank / 100;
            n2 = -this.amountInBank % 100;
            this.bankAmountLabel.setForeground(Color.red);
        }
        else {
            s = "$" + this.amountInBank / 100;
            n2 = this.amountInBank % 100;
            this.bankAmountLabel.setForeground(Color.black);
        }
        if (n2 < 10) {
            this.bankAmountLabel.setText(String.valueOf(s) + ".0" + n2);
            return;
        }
        this.bankAmountLabel.setText(String.valueOf(s) + "." + n2);
    }
    
    public VideoPokerApplet() {
        this.games = new VideoPokerGame[4];
        this.videoPoker = new VideoPoker();
        this.gameNames = new String[] { "game1.html", "game2.html", "game3.html", "game4.html" };
        this.linePanel1 = new LinePanel();
        this.game1Radio = new Checkbox();
        this.gameGroup = new CheckboxGroup();
        this.game3Radio = new Checkbox();
        this.game2Radio = new Checkbox();
        this.game4Radio = new Checkbox();
        this.linePanel2 = new LinePanel();
        this.fiveCentsRadio = new Checkbox();
        this.amountGroup = new CheckboxGroup();
        this.twentyfiveCentsRadio = new Checkbox();
        this.dollarRadio = new Checkbox();
        this.fiveDollarRadio = new Checkbox();
        this.label1 = new Label();
        this.label2 = new Label();
        this.label3 = new Label();
        this.playVideoPokerButton = new Button();
        this.linePanel3 = new LinePanel();
        this.bankAmountLabel = new Label();
        this.bankLabel = new Label();
        this.gameRadios = new Checkbox[] { this.game1Radio, this.game2Radio, this.game3Radio, this.game4Radio };
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == VideoPokerApplet.this.playVideoPokerButton) {
                VideoPokerApplet.this.playVideoPokerButton_ActionPerformed(actionEvent);
            }
        }
    }
}
