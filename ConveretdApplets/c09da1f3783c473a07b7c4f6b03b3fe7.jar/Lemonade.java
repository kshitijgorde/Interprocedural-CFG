import java.awt.event.ActionEvent;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.event.ActionListener;
import java.net.URL;
import java.beans.PropertyVetoException;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import symantec.itools.lang.Context;
import java.awt.TextArea;
import symantec.itools.awt.util.ProgressBar;
import symantec.itools.awt.shape.HorizontalLine;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Lemonade extends Applet implements Runnable
{
    double money;
    double reputation;
    int speed;
    double totalExpenses;
    double totalIncome;
    int inPitcher;
    int repLevel;
    int pricePerCup;
    int lemonsPerPitcher;
    int sugarPerPitcher;
    int icePerCup;
    int[] inventory;
    int temp;
    int weatherToday;
    int day;
    int dailySell;
    int dailyPossible;
    float[] cupPrice;
    float[] lemonPrice;
    float[] sugarPrice;
    float[] icePrice;
    String[] weatherStrings;
    int[][] itemNum;
    String[] itemDesc;
    double[][] itemCost;
    int itemType;
    boolean soldout;
    int[] PeopleBubble;
    int[] PeopleBubbleTime;
    Panel priceQualityPanel;
    Label label2;
    Label label3;
    TextField pricePerCupField;
    Label label4;
    TextField lemonsPerPitcherField;
    Label label5;
    Label label6;
    TextField sugarPerPitcherField;
    Label label7;
    TextField icePerCupField;
    Label label9;
    Button qualityOKButton;
    Label label8;
    Label label10;
    Button pqcHelpButton;
    Button bankruptButton2;
    Button button1;
    HorizontalLine horizontalLine1;
    Panel inventoryPanel;
    Label label11;
    Label label12;
    Label cupsInv;
    Label lemonsInv;
    Label sugarInv;
    Label iceInv;
    Button buyCupsButton;
    Button buyLemonsButton;
    Button buySugarButton;
    Button buyIceButton;
    Button invOKButton;
    Button ipHelpButton;
    Button bankruptButton;
    Panel acquisitionPanel;
    Label acquisitionLabel;
    Label currentInvLabel;
    Label buyOption1;
    Label buyOption2;
    Label buyOption3;
    Button acqOKButton;
    Button buyOpt1;
    Button buyOpt2;
    Label label13;
    Button buyOpt3;
    Panel EODPanel;
    Label label14;
    Label EOD1;
    ProgressBar reputationBar;
    Label label15;
    Label EOD2;
    Label label16;
    ProgressBar popularityBar;
    Button EODOKButton;
    Panel EODPanel2;
    Label label17;
    Label iceMelted;
    Label lemonsBad;
    Label sugarBad;
    Button inventoryOK;
    Panel finalScreen;
    Label label18;
    Label label19;
    Label label20;
    HorizontalLine horizontalLine2;
    Label label21;
    Label incomeLabel;
    Label expensesLabel;
    Label label22;
    Label inventoryLabel;
    Label netProfitLabel;
    Label winLoseLabel;
    Button playAgain;
    Panel introPanel;
    Label label23;
    Button introOKButton;
    TextArea textArea1;
    Panel copyrightPanel;
    Label label24;
    Label label25;
    Label label26;
    Label label27;
    Button copyrightOKButton;
    Label label1;
    Panel pqcHelpPanel;
    Label label29;
    TextArea pqcHelpText;
    Button pqcHelpOK;
    Panel inventoryHelp;
    Label label30;
    TextArea ipHelpText;
    Button ipHelpOK;
    Panel panel1;
    Label dayLabel;
    Label moneyLabel;
    Label tempLabel;
    Label weatherLabel;
    Button FFButton;
    LemonadeAnim canvas1;
    
    public void init() {
        Context.setApplet(this);
        this.setLayout(null);
        this.setSize(396, 318);
        this.setForeground(new Color(0));
        this.setBackground(new Color(16776960));
        (this.priceQualityPanel = new Panel()).setLayout(null);
        this.priceQualityPanel.setVisible(false);
        this.priceQualityPanel.setBounds(12, 48, 372, 216);
        this.priceQualityPanel.setFont(new Font("Serif", 0, 14));
        this.add(this.priceQualityPanel);
        (this.label2 = new Label("Price per Cup:", 2)).setBounds(60, 36, 84, 24);
        this.label2.setFont(new Font("SansSerif", 0, 12));
        this.priceQualityPanel.add(this.label2);
        (this.label3 = new Label("Cents")).setBounds(216, 36, 48, 24);
        this.label3.setFont(new Font("SansSerif", 0, 12));
        this.priceQualityPanel.add(this.label3);
        (this.pricePerCupField = new TextField()).setText("25");
        this.pricePerCupField.setBounds(156, 36, 48, 24);
        this.priceQualityPanel.add(this.pricePerCupField);
        (this.label4 = new Label("Lemons per Pitcher:", 2)).setBounds(24, 72, 120, 24);
        this.label4.setFont(new Font("SansSerif", 0, 12));
        this.priceQualityPanel.add(this.label4);
        (this.lemonsPerPitcherField = new TextField()).setText("4");
        this.lemonsPerPitcherField.setBounds(156, 72, 48, 24);
        this.priceQualityPanel.add(this.lemonsPerPitcherField);
        (this.label5 = new Label("Lemons")).setBounds(216, 72, 60, 24);
        this.label5.setFont(new Font("SansSerif", 0, 12));
        this.priceQualityPanel.add(this.label5);
        (this.label6 = new Label("Sugar per Pitcher:", 2)).setBounds(36, 108, 108, 24);
        this.label6.setFont(new Font("SansSerif", 0, 12));
        this.priceQualityPanel.add(this.label6);
        (this.sugarPerPitcherField = new TextField()).setText("4");
        this.sugarPerPitcherField.setBounds(156, 108, 48, 24);
        this.priceQualityPanel.add(this.sugarPerPitcherField);
        (this.label7 = new Label("Cups")).setBounds(216, 108, 48, 24);
        this.label7.setFont(new Font("SansSerif", 0, 12));
        this.priceQualityPanel.add(this.label7);
        (this.icePerCupField = new TextField()).setText("4");
        this.icePerCupField.setBounds(156, 144, 48, 24);
        this.priceQualityPanel.add(this.icePerCupField);
        (this.label9 = new Label("Cubes")).setBounds(216, 144, 48, 24);
        this.label9.setFont(new Font("SansSerif", 0, 12));
        this.priceQualityPanel.add(this.label9);
        (this.qualityOKButton = new Button()).setLabel("OK");
        this.qualityOKButton.setBounds(120, 180, 120, 24);
        this.qualityOKButton.setBackground(new Color(65280));
        this.priceQualityPanel.add(this.qualityOKButton);
        (this.label8 = new Label("Ice per Cup:", 2)).setBounds(72, 144, 72, 24);
        this.label8.setFont(new Font("SansSerif", 0, 12));
        this.priceQualityPanel.add(this.label8);
        (this.label10 = new Label("Price / Quality Control", 1)).setBounds(48, 0, 276, 36);
        this.label10.setFont(new Font("Serif", 3, 24));
        this.label10.setForeground(new Color(16711935));
        this.priceQualityPanel.add(this.label10);
        (this.pqcHelpButton = new Button()).setLabel("HELP!");
        this.pqcHelpButton.setBounds(276, 180, 72, 24);
        this.pqcHelpButton.setBackground(new Color(16711935));
        this.priceQualityPanel.add(this.pqcHelpButton);
        (this.bankruptButton2 = new Button()).setLabel("Bankrupt!");
        this.bankruptButton2.setBounds(0, 180, 72, 24);
        this.bankruptButton2.setFont(new Font("Dialog", 1, 12));
        this.bankruptButton2.setForeground(new Color(16777215));
        this.bankruptButton2.setBackground(new Color(16711680));
        this.priceQualityPanel.add(this.bankruptButton2);
        (this.button1 = new Button()).setLabel("Back to Store!");
        this.button1.setBounds(272, 144, 96, 24);
        this.button1.setBackground(new Color(16776960));
        this.priceQualityPanel.add(this.button1);
        (this.horizontalLine1 = new HorizontalLine()).setBounds(12, 36, 372, 2);
        this.add(this.horizontalLine1);
        (this.inventoryPanel = new Panel()).setLayout(null);
        this.inventoryPanel.setVisible(false);
        this.inventoryPanel.setBounds(12, 48, 372, 216);
        this.inventoryPanel.setFont(new Font("Dialog", 1, 12));
        this.add(this.inventoryPanel);
        (this.label11 = new Label("Inventory/Purchasing", 1)).setBounds(36, 0, 276, 36);
        this.label11.setFont(new Font("Serif", 3, 24));
        this.label11.setForeground(new Color(16711935));
        this.inventoryPanel.add(this.label11);
        (this.label12 = new Label("You Have:")).setBounds(60, 36, 108, 24);
        this.label12.setFont(new Font("Dialog", 1, 16));
        this.inventoryPanel.add(this.label12);
        (this.cupsInv = new Label("-1 Cups")).setBounds(24, 60, 180, 24);
        this.inventoryPanel.add(this.cupsInv);
        (this.lemonsInv = new Label("-1 Lemons")).setBounds(24, 84, 180, 24);
        this.inventoryPanel.add(this.lemonsInv);
        (this.sugarInv = new Label("-1 Cups of Sugar")).setBounds(24, 108, 180, 24);
        this.inventoryPanel.add(this.sugarInv);
        (this.iceInv = new Label("-1 Ice Cubes")).setBounds(24, 132, 180, 24);
        this.inventoryPanel.add(this.iceInv);
        (this.buyCupsButton = new Button()).setLabel("Buy More Cups");
        this.buyCupsButton.setBounds(216, 60, 120, 24);
        this.buyCupsButton.setFont(new Font("Serif", 1, 12));
        this.buyCupsButton.setForeground(new Color(16776960));
        this.buyCupsButton.setBackground(new Color(255));
        this.inventoryPanel.add(this.buyCupsButton);
        (this.buyLemonsButton = new Button()).setLabel("Buy More Lemons");
        this.buyLemonsButton.setBounds(216, 84, 120, 24);
        this.buyLemonsButton.setFont(new Font("Serif", 1, 12));
        this.buyLemonsButton.setForeground(new Color(16776960));
        this.buyLemonsButton.setBackground(new Color(255));
        this.inventoryPanel.add(this.buyLemonsButton);
        (this.buySugarButton = new Button()).setLabel("Buy More Sugar");
        this.buySugarButton.setBounds(216, 108, 120, 24);
        this.buySugarButton.setFont(new Font("Serif", 1, 12));
        this.buySugarButton.setForeground(new Color(16776960));
        this.buySugarButton.setBackground(new Color(255));
        this.inventoryPanel.add(this.buySugarButton);
        (this.buyIceButton = new Button()).setLabel("Buy More Ice");
        this.buyIceButton.setBounds(216, 132, 120, 24);
        this.buyIceButton.setFont(new Font("Serif", 1, 12));
        this.buyIceButton.setForeground(new Color(16776960));
        this.buyIceButton.setBackground(new Color(255));
        this.inventoryPanel.add(this.buyIceButton);
        (this.invOKButton = new Button()).setLabel("OK");
        this.invOKButton.setBounds(120, 180, 120, 24);
        this.invOKButton.setBackground(new Color(65280));
        this.inventoryPanel.add(this.invOKButton);
        (this.ipHelpButton = new Button()).setLabel("HELP!");
        this.ipHelpButton.setBounds(276, 180, 72, 24);
        this.ipHelpButton.setBackground(new Color(16711935));
        this.inventoryPanel.add(this.ipHelpButton);
        (this.bankruptButton = new Button()).setLabel("Bankrupt!");
        this.bankruptButton.setBounds(0, 180, 72, 24);
        this.bankruptButton.setFont(new Font("Dialog", 1, 12));
        this.bankruptButton.setForeground(new Color(16777215));
        this.bankruptButton.setBackground(new Color(16711680));
        this.inventoryPanel.add(this.bankruptButton);
        (this.acquisitionPanel = new Panel()).setLayout(null);
        this.acquisitionPanel.setVisible(false);
        this.acquisitionPanel.setBounds(12, 48, 372, 216);
        this.add(this.acquisitionPanel);
        (this.acquisitionLabel = new Label("Acquisition: [item]", 1)).setBounds(36, 0, 276, 36);
        this.acquisitionLabel.setFont(new Font("Serif", 3, 24));
        this.acquisitionLabel.setForeground(new Color(16711935));
        this.acquisitionPanel.add(this.acquisitionLabel);
        (this.currentInvLabel = new Label("You currently have # [item] and $[money].")).setBounds(0, 36, 372, 24);
        this.acquisitionPanel.add(this.currentInvLabel);
        (this.buyOption1 = new Label("# [item] for $[amount].")).setBounds(12, 84, 252, 24);
        this.acquisitionPanel.add(this.buyOption1);
        (this.buyOption2 = new Label("# [item] for $[amount].")).setBounds(12, 108, 252, 24);
        this.acquisitionPanel.add(this.buyOption2);
        (this.buyOption3 = new Label("# [item] for $[amount].")).setBounds(12, 132, 252, 24);
        this.acquisitionPanel.add(this.buyOption3);
        (this.acqOKButton = new Button()).setLabel("OK");
        this.acqOKButton.setBounds(120, 180, 120, 24);
        this.acqOKButton.setBackground(new Color(65280));
        this.acquisitionPanel.add(this.acqOKButton);
        (this.buyOpt1 = new Button()).setLabel("Buy it!");
        this.buyOpt1.setBounds(264, 84, 96, 24);
        this.buyOpt1.setBackground(new Color(65280));
        this.acquisitionPanel.add(this.buyOpt1);
        (this.buyOpt2 = new Button()).setLabel("Buy it!");
        this.buyOpt2.setBounds(264, 108, 96, 24);
        this.buyOpt2.setBackground(new Color(65280));
        this.acquisitionPanel.add(this.buyOpt2);
        (this.label13 = new Label("You can buy:")).setBounds(0, 60, 156, 24);
        this.label13.setFont(new Font("Dialog", 1, 14));
        this.acquisitionPanel.add(this.label13);
        (this.buyOpt3 = new Button()).setLabel("Buy it!");
        this.buyOpt3.setBounds(264, 132, 96, 24);
        this.buyOpt3.setBackground(new Color(65280));
        this.acquisitionPanel.add(this.buyOpt3);
        (this.EODPanel = new Panel()).setLayout(null);
        this.EODPanel.setVisible(false);
        this.EODPanel.setBounds(12, 48, 372, 216);
        this.add(this.EODPanel);
        (this.label14 = new Label("End of Day Reports", 1)).setBounds(48, 0, 276, 36);
        this.label14.setFont(new Font("Serif", 3, 24));
        this.label14.setForeground(new Color(16711935));
        this.EODPanel.add(this.label14);
        (this.EOD1 = new Label("You managed to sell X cups to Y potential customers.")).setBounds(0, 36, 360, 24);
        this.EODPanel.add(this.EOD1);
        this.reputationBar = new ProgressBar();
        try {
            this.reputationBar.setBorderColor(new Color(255));
        }
        catch (PropertyVetoException ex) {}
        this.reputationBar.setBounds(0, 108, 360, 24);
        this.reputationBar.setForeground(new Color(65280));
        this.EODPanel.add(this.reputationBar);
        (this.label15 = new Label("Your Customer Satisfaction:")).setBounds(0, 96, 164, 12);
        this.label15.setFont(new Font("Dialog", 1, 12));
        this.EODPanel.add(this.label15);
        (this.EOD2 = new Label("Considering the weather, I'd say this is pitiful!")).setBounds(0, 60, 360, 24);
        this.EODPanel.add(this.EOD2);
        (this.label16 = new Label("Your Popularity:")).setBounds(0, 132, 156, 12);
        this.label16.setFont(new Font("Dialog", 1, 12));
        this.EODPanel.add(this.label16);
        this.popularityBar = new ProgressBar();
        try {
            this.popularityBar.setBorderColor(new Color(255));
        }
        catch (PropertyVetoException ex2) {}
        this.popularityBar.setBounds(0, 144, 360, 24);
        this.popularityBar.setForeground(new Color(65280));
        this.EODPanel.add(this.popularityBar);
        (this.EODOKButton = new Button()).setLabel("OK");
        this.EODOKButton.setBounds(120, 180, 120, 24);
        this.EODOKButton.setBackground(new Color(65280));
        this.EODPanel.add(this.EODOKButton);
        (this.EODPanel2 = new Panel()).setLayout(null);
        this.EODPanel2.setVisible(false);
        this.EODPanel2.setBounds(12, 48, 372, 216);
        this.add(this.EODPanel2);
        (this.label17 = new Label("Inventory Losses", 1)).setBounds(36, 0, 276, 36);
        this.label17.setFont(new Font("Serif", 3, 24));
        this.label17.setForeground(new Color(16711935));
        this.EODPanel2.add(this.label17);
        (this.iceMelted = new Label("Your remaining ice has melted. ")).setBounds(0, 60, 372, 24);
        this.EODPanel2.add(this.iceMelted);
        (this.lemonsBad = new Label("0 of your remaining lemons went bad.")).setVisible(false);
        this.lemonsBad.setBounds(0, 84, 372, 24);
        this.EODPanel2.add(this.lemonsBad);
        (this.sugarBad = new Label("Bugs in the sugar!  All remaining sugar discarded.")).setVisible(false);
        this.sugarBad.setBounds(0, 108, 372, 24);
        this.EODPanel2.add(this.sugarBad);
        (this.inventoryOK = new Button()).setLabel("OK");
        this.inventoryOK.setBounds(120, 180, 120, 24);
        this.inventoryOK.setBackground(new Color(65280));
        this.EODPanel2.add(this.inventoryOK);
        (this.finalScreen = new Panel()).setLayout(null);
        this.finalScreen.setVisible(false);
        this.finalScreen.setBounds(12, 48, 372, 216);
        this.add(this.finalScreen);
        (this.label18 = new Label("End of Season Report:", 1)).setBounds(48, 0, 276, 36);
        this.label18.setFont(new Font("Serif", 3, 24));
        this.label18.setForeground(new Color(16711935));
        this.finalScreen.add(this.label18);
        (this.label19 = new Label("Total Income:", 2)).setBounds(12, 36, 156, 24);
        this.label19.setFont(new Font("Dialog", 1, 12));
        this.finalScreen.add(this.label19);
        (this.label20 = new Label("Total Expenses:", 2)).setBounds(48, 60, 120, 24);
        this.label20.setFont(new Font("Dialog", 1, 12));
        this.finalScreen.add(this.label20);
        (this.horizontalLine2 = new HorizontalLine()).setBounds(12, 108, 348, 2);
        this.finalScreen.add(this.horizontalLine2);
        (this.label21 = new Label("Net Profit/Loss:", 2)).setBounds(60, 120, 108, 24);
        this.label21.setFont(new Font("Dialog", 1, 12));
        this.finalScreen.add(this.label21);
        (this.incomeLabel = new Label("text")).setBounds(180, 36, 84, 24);
        this.incomeLabel.setFont(new Font("Dialog", 1, 12));
        this.incomeLabel.setForeground(new Color(-16744448));
        this.finalScreen.add(this.incomeLabel);
        (this.expensesLabel = new Label("text")).setBounds(180, 60, 72, 24);
        this.expensesLabel.setFont(new Font("Dialog", 1, 12));
        this.expensesLabel.setForeground(new Color(16711680));
        this.finalScreen.add(this.expensesLabel);
        (this.label22 = new Label("Liquidated Inventory Value:", 2)).setBounds(12, 84, 156, 24);
        this.label22.setFont(new Font("Dialog", 1, 12));
        this.finalScreen.add(this.label22);
        (this.inventoryLabel = new Label("text")).setBounds(180, 84, 72, 24);
        this.inventoryLabel.setFont(new Font("Dialog", 1, 12));
        this.inventoryLabel.setForeground(new Color(-16744448));
        this.finalScreen.add(this.inventoryLabel);
        (this.netProfitLabel = new Label("text")).setBounds(180, 120, 72, 24);
        this.netProfitLabel.setFont(new Font("Dialog", 1, 12));
        this.finalScreen.add(this.netProfitLabel);
        (this.winLoseLabel = new Label("Congratulations!", 1)).setBounds(24, 144, 336, 36);
        this.winLoseLabel.setFont(new Font("Serif", 3, 32));
        this.winLoseLabel.setForeground(new Color(255));
        this.finalScreen.add(this.winLoseLabel);
        (this.playAgain = new Button()).setLabel("Play Again?");
        this.playAgain.setBounds(132, 192, 120, 24);
        this.playAgain.setBackground(new Color(65280));
        this.finalScreen.add(this.playAgain);
        (this.introPanel = new Panel()).setLayout(null);
        this.introPanel.setBounds(12, 48, 372, 216);
        this.add(this.introPanel);
        (this.label23 = new Label("Introduction", 1)).setBounds(48, 0, 276, 36);
        this.label23.setFont(new Font("Serif", 3, 24));
        this.label23.setForeground(new Color(16711935));
        this.introPanel.add(this.label23);
        (this.introOKButton = new Button()).setLabel("OK");
        this.introOKButton.setBounds(120, 192, 120, 24);
        this.introOKButton.setBackground(new Color(65280));
        this.introPanel.add(this.introOKButton);
        (this.textArea1 = new TextArea("", 0, 0, 1)).setEditable(false);
        this.textArea1.setBounds(8, 36, 336, 148);
        this.introPanel.add(this.textArea1);
        (this.copyrightPanel = new Panel()).setLayout(null);
        this.copyrightPanel.setVisible(false);
        this.copyrightPanel.setBounds(12, 48, 372, 216);
        this.add(this.copyrightPanel);
        (this.label24 = new Label("Lemonade Stand", 1)).setBounds(44, 0, 264, 24);
        this.label24.setFont(new Font("Serif", 3, 20));
        this.copyrightPanel.add(this.label24);
        (this.label25 = new Label("(C)2000-2001 4WebGames.com, All Rights Reserved", 1)).setBounds(16, 72, 344, 24);
        this.label25.setFont(new Font("Serif", 1, 14));
        this.copyrightPanel.add(this.label25);
        (this.label26 = new Label("http://4WebGames.com", 1)).setBounds(44, 96, 264, 24);
        this.label26.setFont(new Font("Serif", 1, 14));
        this.copyrightPanel.add(this.label26);
        (this.label27 = new Label("Email: webmaster@4WebGames.com", 1)).setBounds(32, 120, 312, 24);
        this.label27.setFont(new Font("Serif", 1, 14));
        this.copyrightPanel.add(this.label27);
        (this.copyrightOKButton = new Button()).setLabel("OK");
        this.copyrightOKButton.setBounds(120, 180, 120, 24);
        this.copyrightOKButton.setBackground(new Color(65280));
        this.copyrightPanel.add(this.copyrightOKButton);
        (this.label1 = new Label("Lemonade Stand", 1)).setBounds(36, 0, 312, 36);
        this.label1.setFont(new Font("Serif", 3, 36));
        this.label1.setForeground(new Color(255));
        this.add(this.label1);
        (this.pqcHelpPanel = new Panel()).setLayout(null);
        this.pqcHelpPanel.setVisible(false);
        this.pqcHelpPanel.setBounds(12, 48, 372, 216);
        this.add(this.pqcHelpPanel);
        (this.label29 = new Label("Price / Quality Control Help", 1)).setBounds(-12, 0, 384, 36);
        this.label29.setFont(new Font("Serif", 3, 24));
        this.label29.setForeground(new Color(16711935));
        this.pqcHelpPanel.add(this.label29);
        (this.pqcHelpText = new TextArea("", 0, 0, 1)).setBounds(0, 36, 372, 144);
        this.pqcHelpPanel.add(this.pqcHelpText);
        (this.pqcHelpOK = new Button()).setLabel("OK");
        this.pqcHelpOK.setBounds(120, 180, 120, 24);
        this.pqcHelpOK.setBackground(new Color(65280));
        this.pqcHelpPanel.add(this.pqcHelpOK);
        (this.inventoryHelp = new Panel()).setLayout(null);
        this.inventoryHelp.setVisible(false);
        this.inventoryHelp.setBounds(12, 48, 372, 216);
        this.add(this.inventoryHelp);
        (this.label30 = new Label("Inventory/Purchasing Help", 1)).setBounds(-12, 0, 384, 36);
        this.label30.setFont(new Font("Serif", 3, 24));
        this.label30.setForeground(new Color(16711935));
        this.inventoryHelp.add(this.label30);
        (this.ipHelpText = new TextArea("", 0, 0, 1)).setBounds(0, 36, 372, 144);
        this.inventoryHelp.add(this.ipHelpText);
        (this.ipHelpOK = new Button()).setLabel("OK");
        this.ipHelpOK.setBounds(120, 180, 120, 24);
        this.ipHelpOK.setBackground(new Color(65280));
        this.inventoryHelp.add(this.ipHelpOK);
        (this.panel1 = new Panel()).setLayout(null);
        this.panel1.setVisible(false);
        this.panel1.setBounds(12, 264, 372, 48);
        this.add(this.panel1);
        (this.dayLabel = new Label("Day: -1")).setVisible(false);
        this.dayLabel.setBounds(0, 0, 60, 24);
        this.dayLabel.setFont(new Font("Serif", 0, 12));
        this.panel1.add(this.dayLabel);
        (this.moneyLabel = new Label("Money: $00.00")).setVisible(false);
        this.moneyLabel.setBounds(84, 0, 96, 24);
        this.moneyLabel.setFont(new Font("Serif", 0, 12));
        this.panel1.add(this.moneyLabel);
        (this.tempLabel = new Label("High Temperature: -1 Degrees")).setVisible(false);
        this.tempLabel.setBounds(192, 0, 180, 24);
        this.tempLabel.setFont(new Font("Serif", 0, 12));
        this.panel1.add(this.tempLabel);
        (this.weatherLabel = new Label("Weather Prediction: Who knows?")).setVisible(false);
        this.weatherLabel.setBounds(0, 24, 252, 24);
        this.weatherLabel.setFont(new Font("Serif", 0, 12));
        this.panel1.add(this.weatherLabel);
        (this.FFButton = new Button()).setLabel("Fast Forward");
        this.FFButton.setVisible(false);
        this.FFButton.setBounds(600, 600, 120, 24);
        this.FFButton.setBackground(new Color(16711680));
        this.panel1.add(this.FFButton);
        try {
            this.getAppletContext().showDocument(new URL("http://www.4webgames.com/lemonade/popunder.php"), "_new");
        }
        catch (Exception ex3) {}
        (this.canvas1 = new LemonadeAnim()).setBounds(12, 48, 372, 216);
        this.add(this.canvas1);
        this.canvas1.getLemonadeGraphics(this);
        this.canvas1.setVisible(false);
        this.inventory = new int[4];
        this.weatherStrings = new String[5];
        this.cupPrice = new float[3];
        this.sugarPrice = new float[3];
        this.icePrice = new float[3];
        this.lemonPrice = new float[3];
        this.weatherStrings[1] = "Clear and Sunny";
        this.weatherStrings[0] = "Hazy";
        this.weatherStrings[2] = "Cloudy";
        this.weatherStrings[3] = "Overcast";
        this.weatherStrings[4] = "Rain!";
        this.itemCost = new double[4][3];
        this.PeopleBubble = new int[20];
        this.PeopleBubbleTime = new int[20];
        this.speed = 250;
        final SymAction symAction = new SymAction();
        this.qualityOKButton.addActionListener(symAction);
        this.invOKButton.addActionListener(symAction);
        this.acqOKButton.addActionListener(symAction);
        this.buyCupsButton.addActionListener(symAction);
        this.buyLemonsButton.addActionListener(symAction);
        this.buySugarButton.addActionListener(symAction);
        this.buyIceButton.addActionListener(symAction);
        this.buyOpt1.addActionListener(symAction);
        this.buyOpt2.addActionListener(symAction);
        this.buyOpt3.addActionListener(symAction);
        this.FFButton.addActionListener(symAction);
        this.EODOKButton.addActionListener(symAction);
        this.inventoryOK.addActionListener(symAction);
        this.playAgain.addActionListener(symAction);
        this.introOKButton.addActionListener(symAction);
        this.copyrightOKButton.addActionListener(symAction);
        this.pqcHelpButton.addActionListener(symAction);
        this.pqcHelpOK.addActionListener(symAction);
        this.ipHelpButton.addActionListener(symAction);
        this.ipHelpOK.addActionListener(symAction);
        this.bankruptButton.addActionListener(symAction);
        this.bankruptButton2.addActionListener(symAction);
        this.button1.addActionListener(symAction);
        this.textArea1.setText("Lemonade Stand Introduction\n---------------------------------------------------\nHi, and welcome to Lemonade Stand!  Your goal in this game will be to make as much money as you can within 30 days.  To do this, you've decided to open your own business -- a Lemonade Stand!  You'll have complete control over almost every part of your business, including pricing, quality control, inventory control, and purchasing supplies.  You'll also have to deal with the weather, which can be unpredictable.  Unfortunately, the weather will play a big part when customers are deciding whether or not to buy your product.\nOther factors which will make or break your business is the price you charge.  Customers are more apt to pay higher prices when the product (your lemonade) is more in demand - When the weather is hotter.  As the temperature drops, and the weather turns bad (overcast, cloudy, rain), don't expect them to pay nearly what they would on a hot, hazy day.\nThe other major factor which comes into play is your customer's satisfaction.  As you sell your product, people will decide whether or not they like it, and how much they like or dislike it.  As time goes on, they'll start to tell their friends, neighbors, and relatives (hence, your 'popularity').  Sell a good product for a good price, and you'll build business over time; overcharge for inferior products, and you'll be out of business sooner than you'd think.  Another more direct form of customer satisfaction affecting sales takes place directly at the stand.  As customers buy your product, you'll see some tell you what they think by the bubbles over their heads.  If customers are enjoying their product, others are more likely to buy.  If they're expressing their dissatisfaction, other customers are more likely to take their business elsewhere.\nIf you'd like more hints and tips on running a successful lemonade stand, be sure to visit the author's homepage!  =)");
    }
    
    public void newGame() {
        for (int i = 0; i < 4; ++i) {
            this.inventory[i] = 0;
        }
        this.reputation = 0.0;
        this.repLevel = 0;
        this.money = 20.0;
        this.pricePerCup = 25;
        this.icePerCup = 4;
        this.lemonsPerPitcher = 4;
        this.sugarPerPitcher = 4;
        this.totalExpenses = 0.0;
        this.totalIncome = 0.0;
        this.day = 0;
        this.newDay();
        this.setInventory();
        this.panel1.setVisible(true);
    }
    
    public void setPriceQuality() {
        this.pricePerCupField.setText(new Integer(this.pricePerCup).toString());
        this.lemonsPerPitcherField.setText(new Integer(this.lemonsPerPitcher).toString());
        this.sugarPerPitcherField.setText(new Integer(this.sugarPerPitcher).toString());
        this.icePerCupField.setText(new Integer(this.icePerCup).toString());
        this.priceQualityPanel.setVisible(true);
    }
    
    public void setInventory() {
        this.cupsInv.setText(String.valueOf(this.inventory[3]) + " " + this.itemDesc[3]);
        this.sugarInv.setText(String.valueOf(this.inventory[1]) + " " + this.itemDesc[1]);
        this.iceInv.setText(String.valueOf(this.inventory[2]) + " " + this.itemDesc[2]);
        this.lemonsInv.setText(String.valueOf(this.inventory[0]) + " " + this.itemDesc[0]);
        this.inventoryPanel.setVisible(true);
    }
    
    public double buyOrPass() {
        double n = ((this.temp - 50) / 200.0 + (5 - this.weatherToday) / 20.0) * ((this.temp / 4 - this.pricePerCup) / (this.temp / 4) + 1);
        if (this.repLevel < Math.random() * (this.repLevel - 500)) {
            n *= this.reputation;
        }
        double n2 = n * ((this.lemonsPerPitcher + 1) / 5.0f) * ((this.sugarPerPitcher + 4) / 8.0f);
        for (int i = 0; i < 20; ++i) {
            if (this.PeopleBubbleTime[i] > 0) {
                if (this.PeopleBubble[i] == 0) {
                    n2 *= 1.3;
                }
                else {
                    n2 *= 0.5;
                }
            }
        }
        return (n2 + (Math.random() * 0.2 - 0.1)) * 1.3;
    }
    
    public int CheckBubble() {
        final int[] array = new int[3];
        if (this.lemonsPerPitcher < 4 || this.sugarPerPitcher < 4) {
            array[2] = 1;
        }
        if (this.icePerCup < (this.temp - 49) / 5) {
            array[1] = 1;
        }
        if (this.pricePerCup > this.temp / 4) {
            array[0] = 1;
        }
        final int n = (int)(Math.random() * 3.0);
        if (array[n] == 1) {
            return n + 1;
        }
        return 0;
    }
    
    public double giveRep() {
        double n = (0.8 + Math.random() * 0.4) * (this.lemonsPerPitcher / 4.0) * (this.sugarPerPitcher / 4.0) * (this.icePerCup / ((this.temp - 50) / 5) + 1.0) * (((this.temp - 50) / 5 + 1) / (this.icePerCup + 4)) * ((this.temp / 4 - this.pricePerCup) / (this.temp / 4) + 1.0);
        if (n < 0.0) {
            n = 0.0;
        }
        if (n > 2.0) {
            n = 2.0;
        }
        this.reputation += n;
        ++this.repLevel;
        return n;
    }
    
    public void setAcquisition(final int itemType) {
        this.itemType = itemType;
        this.acquisitionLabel.setText("Acquisition: " + this.itemDesc[this.itemType]);
        this.currentInvLabel.setText("You have " + this.inventory[this.itemType] + " " + this.itemDesc[this.itemType] + " and $" + this.formatPrice(this.money));
        this.acquisitionPanel.setVisible(true);
        if (this.money >= this.itemCost[this.itemType][0]) {
            this.buyOption1.setText(String.valueOf(this.itemNum[this.itemType][0]) + " " + this.itemDesc[this.itemType] + " for " + this.formatPrice(this.itemCost[this.itemType][0]));
            this.buyOption1.setVisible(true);
            this.buyOpt1.setVisible(true);
        }
        else {
            this.buyOption1.setVisible(false);
            this.buyOpt1.setVisible(false);
        }
        if (this.money >= this.itemCost[this.itemType][1]) {
            this.buyOption2.setText(String.valueOf(this.itemNum[this.itemType][1]) + " " + this.itemDesc[this.itemType] + " for " + this.formatPrice(this.itemCost[this.itemType][1]));
            this.buyOption2.setVisible(true);
            this.buyOpt2.setVisible(true);
        }
        else {
            this.buyOption2.setVisible(false);
            this.buyOpt2.setVisible(false);
        }
        if (this.money >= this.itemCost[this.itemType][2]) {
            this.buyOption3.setText(String.valueOf(this.itemNum[this.itemType][2]) + " " + this.itemDesc[this.itemType] + " for " + this.formatPrice(this.itemCost[this.itemType][2]));
            this.buyOption3.setVisible(true);
            this.buyOpt3.setVisible(true);
            return;
        }
        this.buyOption3.setVisible(false);
        this.buyOpt3.setVisible(false);
    }
    
    public void newDay() {
        ++this.day;
        this.soldout = false;
        this.inPitcher = 0;
        this.temp = (int)(Math.random() * 50.0) + 50;
        this.weatherToday = (int)(Math.random() * 5.0);
        this.canvas1.setWeather(this.weatherToday);
        this.itemCost[0][0] = this.getPrice(0.5, 1.0);
        this.itemCost[0][1] = this.getPrice(2.0, 2.5);
        this.itemCost[0][2] = this.getPrice(4.0, 4.5);
        this.itemCost[1][0] = this.getPrice(0.5, 0.75);
        this.itemCost[1][1] = this.getPrice(1.5, 1.75);
        this.itemCost[1][2] = this.getPrice(3.25, 3.5);
        this.itemCost[2][0] = this.getPrice(0.75, 1.0);
        this.itemCost[2][1] = this.getPrice(2.0, 2.25);
        this.itemCost[2][2] = this.getPrice(3.5, 4.0);
        this.itemCost[3][0] = this.getPrice(0.75, 1.0);
        this.itemCost[3][1] = this.getPrice(1.5, 1.75);
        this.itemCost[3][2] = this.getPrice(2.75, 3.25);
        this.showDay();
    }
    
    public boolean buyGlass() {
        if (this.inPitcher == 0 && this.inventory[0] >= this.lemonsPerPitcher && this.inventory[1] >= this.sugarPerPitcher) {
            this.inPitcher = 8 + this.icePerCup;
            final int[] inventory = this.inventory;
            final int n = 0;
            inventory[n] -= this.lemonsPerPitcher;
            final int[] inventory2 = this.inventory;
            final int n2 = 1;
            inventory2[n2] -= this.sugarPerPitcher;
        }
        if (this.inPitcher > 0 && this.inventory[3] > 0 && this.inventory[2] >= this.icePerCup) {
            --this.inPitcher;
            final int[] inventory3 = this.inventory;
            final int n3 = 2;
            inventory3[n3] -= this.icePerCup;
            final int[] inventory4 = this.inventory;
            final int n4 = 3;
            --inventory4[n4];
            this.money += this.pricePerCup / 100.0;
            this.showDay();
            return true;
        }
        return false;
    }
    
    public void run() {
        this.FFButton.setBounds(252, 24, 120, 24);
        this.canvas1.setVisible(true);
        this.FFButton.setVisible(true);
        this.dailySell = 0;
        this.dailyPossible = 0;
        final int[] array = new int[20];
        final int[] array2 = new int[20];
        final int[] array3 = new int[20];
        final int[] array4 = new int[20];
        final int[] array5 = new int[20];
        final int[] array6 = new int[20];
        int n = 0;
        for (int i = 0; i < 1000; ++i) {
            this.canvas1.prepareNextImage();
            if (this.soldout) {
                this.canvas1.SoldOut();
            }
            if (Math.random() < 0.1) {
                ++n;
                ++this.dailyPossible;
                array2[n] = (int)(Math.random() * 2.0);
                array4[n] = array2[n] * 297;
                array3[n] = (int)(Math.random() * 2.0);
                array[n] = (int)(Math.random() * 5.0);
                array6[n] = (array5[n] = 0);
                this.PeopleBubble[n] = -1;
                this.PeopleBubbleTime[n] = 0;
            }
            if (n > 0) {
                for (int j = 1; j <= n; ++j) {
                    if (array4[j] > 140 && array4[j] < 168 && array5[j] == 0 && array6[j] == 0 && Math.random() < this.buyOrPass() && !this.soldout) {
                        if (this.buyGlass()) {
                            array6[j] = 1;
                            ++this.dailySell;
                            this.totalIncome += this.pricePerCup / 100.0;
                            if (this.giveRep() < 1.0) {
                                final int checkBubble = this.CheckBubble();
                                if (checkBubble > 0) {
                                    this.PeopleBubble[j] = checkBubble;
                                    this.PeopleBubbleTime[j] = 10 + (int)(Math.random() * 10.0);
                                }
                            }
                            else if (Math.random() < 0.3) {
                                this.PeopleBubble[j] = 0;
                                this.PeopleBubbleTime[j] = (int)(Math.random() * 10.0) + 10;
                            }
                            array5[j] = (int)(Math.random() * 10.0) + 10;
                        }
                        else {
                            this.soldout = true;
                        }
                    }
                    if (array5[j] > 0) {
                        final int[] array7 = array5;
                        final int n2 = j;
                        --array7[n2];
                        this.canvas1.addPerson(array[j], array2[j], array3[j], array4[j]);
                        if (this.PeopleBubbleTime[j] > 0) {
                            final int[] peopleBubbleTime = this.PeopleBubbleTime;
                            final int n3 = j;
                            --peopleBubbleTime[n3];
                            this.canvas1.addBubble(this.PeopleBubble[j], array4[j]);
                        }
                    }
                    else {
                        final int[] array8 = array4;
                        final int n4 = j;
                        array8[n4] -= array2[j] * 50 - 25;
                        if (array4[j] < 0 || array4[j] > 372) {
                            --n;
                            if (j <= n) {
                                for (int k = j; k <= n; ++k) {
                                    array4[k] = array4[k + 1];
                                    array2[k] = array2[k + 1];
                                    array[k] = array[k + 1];
                                    array3[k] = array3[k + 1];
                                    array5[k] = array5[k + 1];
                                    array6[k] = array6[k + 1];
                                    this.PeopleBubble[k] = this.PeopleBubble[k + 1];
                                    this.PeopleBubbleTime[k] = this.PeopleBubbleTime[k + 1];
                                }
                            }
                            --j;
                        }
                        else {
                            array3[j] = 1 - array3[j];
                            this.canvas1.addPerson(array[j], array2[j], array3[j], array4[j]);
                            if (this.PeopleBubbleTime[j] > 0) {
                                final int[] peopleBubbleTime2 = this.PeopleBubbleTime;
                                final int n5 = j;
                                --peopleBubbleTime2[n5];
                                this.canvas1.addBubble(this.PeopleBubble[j], array4[j]);
                            }
                        }
                    }
                }
            }
            this.canvas1.addText("Cups: " + this.inventory[3] + ", Ice: " + this.inventory[2] + ", Lemons: " + this.inventory[0] + ", Sugar: " + this.inventory[1] + ", Cups in Pitcher: " + this.inPitcher);
            if (this.weatherToday == 4) {
                this.canvas1.addRain();
            }
            this.canvas1.repaint();
            try {
                Thread.sleep(this.speed);
            }
            catch (Exception ex) {}
        }
        this.canvas1.setVisible(false);
        this.FFButton.setVisible(false);
        this.RunEOD();
    }
    
    public void RunEOD2() {
        this.EODPanel2.setVisible(true);
        this.inventory[2] = 0;
        if (Math.random() < 0.25) {
            final int n = (int)(Math.random() * this.inventory[0] * 0.5);
            final int[] inventory = this.inventory;
            final int n2 = 0;
            inventory[n2] -= n;
            this.lemonsBad.setVisible(true);
            this.lemonsBad.setText(String.valueOf(n) + " of your remaining lemons spoiled.");
        }
        else {
            this.lemonsBad.setVisible(false);
            this.lemonsBad.setText("");
        }
        if (Math.random() < 0.05) {
            this.inventory[1] = 0;
            this.sugarBad.setVisible(true);
            this.sugarBad.setText("Bugs in the sugar!  All remaining sugar needs discarded.");
            return;
        }
        this.sugarBad.setVisible(false);
        this.sugarBad.setText("");
    }
    
    public void RunEOD() {
        this.EODPanel.setVisible(true);
        this.EOD1.setText("You managed to sell " + this.dailySell + " cups to " + this.dailyPossible + " potential customers.");
        final double n = (5 - this.weatherToday + (this.temp - 50) / 10) / 20.0 + 0.1;
        final double n2 = n * 0.75;
        final double n3 = n * 1.25;
        String s = "GREAT!";
        final double n4 = this.dailySell / this.dailyPossible;
        if (n4 < n3) {
            s = "Good.";
        }
        if (n4 < n) {
            s = "Average.";
        }
        if (n4 < n2) {
            s = "Pitiful!";
        }
        this.EOD2.setText("Considering the weather, I'd say this is " + s);
        try {
            int value = 50;
            if (this.repLevel > 0) {
                value = (int)(this.reputation / this.repLevel * 50.0);
            }
            if (value > 100) {
                value = 100;
            }
            if (value < 0) {
                value = 0;
            }
            this.reputationBar.setValue(value);
            int value2 = this.repLevel / 10;
            if (value2 > 100) {
                value2 = 100;
            }
            this.popularityBar.setValue(value2);
        }
        catch (Exception ex) {}
    }
    
    public void finalizeGame() {
        final double n = (this.inventory[0] * 0.04 + this.inventory[1] * 0.06 + this.inventory[3] * 0.02) * 100.0 / 100.0;
        this.inventoryLabel.setText("$" + this.formatPrice(n));
        this.incomeLabel.setText("$" + this.formatPrice(this.totalIncome));
        this.expensesLabel.setText("$" + this.formatPrice(this.totalExpenses));
        final double n2 = n + this.totalIncome - this.totalExpenses;
        this.netProfitLabel.setText("$" + this.formatPrice(n2));
        if (n2 < 0.0) {
            this.netProfitLabel.setForeground(Color.red);
            this.winLoseLabel.setText("Too Bad!");
        }
        else {
            this.netProfitLabel.setForeground(new Color(32768));
            this.winLoseLabel.setText("Congratulations!");
        }
        this.finalScreen.setVisible(true);
        try {
            System.out.println("Sending score....");
            final URLConnection openConnection = new URL(String.valueOf(this.getCodeBase()) + "/scores.php?&score=" + n2).openConnection();
            openConnection.setDoInput(true);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            String line;
            do {
                line = bufferedReader.readLine();
                System.out.println(line);
            } while (!line.equals(null));
            System.out.println("Done w/ score.");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void showDay() {
        this.weatherLabel.setText("Weather Forecast: " + this.weatherStrings[this.weatherToday]);
        this.tempLabel.setText("High Temperature: " + this.temp + " degrees");
        this.dayLabel.setText("Day: " + this.day);
        this.moneyLabel.setText("Money: $" + this.formatPrice(this.money));
    }
    
    public String formatPrice(final double n) {
        final double n2 = (int)Math.round(n * 100.0) / 100.0;
        String s = new Double(n2).toString();
        if ((int)(n2 * 10.0) == n2 * 10.0) {
            s = String.valueOf(s) + "0";
        }
        return s;
    }
    
    public double getPrice(final double n, final double n2) {
        return ((int)(Math.random() * ((n2 - n) * 100.0)) + n * 100.0) / 100.0;
    }
    
    void qualityOKButton_Action(final ActionEvent actionEvent) {
        this.priceQualityPanel.setVisible(false);
        this.requestFocus();
        this.pricePerCup = new Integer(this.pricePerCupField.getText());
        this.lemonsPerPitcher = new Integer(this.lemonsPerPitcherField.getText());
        this.sugarPerPitcher = new Integer(this.sugarPerPitcherField.getText());
        this.icePerCup = new Integer(this.icePerCupField.getText());
        if (this.lemonsPerPitcher < 0) {
            this.lemonsPerPitcher = 0;
        }
        if (this.sugarPerPitcher < 0) {
            this.sugarPerPitcher = 0;
        }
        if (this.icePerCup < 0) {
            this.icePerCup = 0;
        }
        if (this.pricePerCup < 0) {
            this.pricePerCup = 0;
        }
        new Thread(this).start();
    }
    
    void invOKButton_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        this.inventoryPanel.setVisible(false);
        this.setPriceQuality();
    }
    
    void acqOKButton_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        this.acquisitionPanel.setVisible(false);
        this.setInventory();
    }
    
    void buyCupsButton_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        this.inventoryPanel.setVisible(false);
        this.setAcquisition(3);
    }
    
    void buyLemonsButton_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        this.inventoryPanel.setVisible(false);
        this.setAcquisition(0);
    }
    
    void buySugarButton_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        this.inventoryPanel.setVisible(false);
        this.setAcquisition(1);
    }
    
    void buyIceButton_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        this.inventoryPanel.setVisible(false);
        this.setAcquisition(2);
    }
    
    void buyOpt1_Action(final ActionEvent actionEvent) {
        final int[] inventory = this.inventory;
        final int itemType = this.itemType;
        inventory[itemType] += this.itemNum[this.itemType][0];
        this.money -= this.itemCost[this.itemType][0];
        this.totalExpenses += this.itemCost[this.itemType][0];
        this.setAcquisition(this.itemType);
        this.showDay();
    }
    
    void buyOpt2_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        final int[] inventory = this.inventory;
        final int itemType = this.itemType;
        inventory[itemType] += this.itemNum[this.itemType][1];
        this.totalExpenses += this.itemCost[this.itemType][1];
        this.money -= this.itemCost[this.itemType][1];
        this.setAcquisition(this.itemType);
        this.showDay();
    }
    
    void buyOpt3_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        final int[] inventory = this.inventory;
        final int itemType = this.itemType;
        inventory[itemType] += this.itemNum[this.itemType][2];
        this.money -= this.itemCost[this.itemType][2];
        this.totalExpenses += this.itemCost[this.itemType][2];
        this.setAcquisition(this.itemType);
        this.showDay();
    }
    
    void FFButton_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        if (this.speed == 250) {
            this.speed = 10;
            this.FFButton.setLabel("Normal Speed");
            return;
        }
        this.speed = 250;
        this.FFButton.setLabel("Fast Forward");
    }
    
    void EODOKButton_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        this.EODPanel.setVisible(false);
        this.RunEOD2();
    }
    
    void inventoryOK_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        this.EODPanel2.setVisible(false);
        if (this.day < 30) {
            this.newDay();
            this.setInventory();
            return;
        }
        this.finalizeGame();
    }
    
    void playAgain_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        this.finalScreen.setVisible(false);
        this.newGame();
    }
    
    void introOK_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        this.introPanel.setVisible(false);
        this.copyrightPanel.setVisible(true);
    }
    
    void copyrightOKButton_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        this.copyrightPanel.setVisible(false);
        this.dayLabel.setVisible(true);
        this.moneyLabel.setVisible(true);
        this.tempLabel.setVisible(true);
        this.weatherLabel.setVisible(true);
        this.newGame();
    }
    
    void pqcHelpButton_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        this.pqcHelpPanel.setVisible(true);
        this.priceQualityPanel.setVisible(false);
        this.pqcHelpText.setText("The price you set will be a large factor in determining a customer's happiness with your product.  As the weather becomes hotter, and clearer (not cloudy, raining, or overcast), customers will be willing to pay more for your product, because it's in a higher demand.  As the weather turns bad, there is less demand, and customers won't pay as much.\n\nThe amount of sugar and lemons you add determines the overall quality of your product.  Most people will expect 4 lemons and 4 cups of sugar per pitcher of Lemonade, however to save on costs you can reduce this (and reduce quality).  If you're more concerned with satisfying the customers, you can add more lemons and sugar, and make a better product that, over time, they'll be willing to pay a little extra for.  (Also, if you plan on overcharging for your product, this is a good way to help offset the negative reputation you would otherwise build).\n\nThe amount of ice should be determined by how hot it is outside.  Too much or too little ice will lower your quality, and your customer's satisfaction!  For instance, at 50 degrees, most people will want about 1-4 ice cubes in their lemonade; as you get into the 95 degree days, I'd recommend about 12-15 ice cubes per cup, to make sure it's nice and cool for the customer.  Another benefit of adding more ice, is that your pitcher of lemonade will be able to make more cups - more ice and less lemonade in each cup, means your pitcher of lemonade is spread out among more cups!\n");
    }
    
    void pqcHelpOK_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        this.priceQualityPanel.setVisible(true);
        this.pqcHelpPanel.setVisible(false);
    }
    
    void ipHelpButton_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        this.inventoryPanel.setVisible(false);
        this.inventoryHelp.setVisible(true);
        this.ipHelpText.setText("Your inventory is crucial to making sales - After all, without product, you have nothing to sell, and can't make any money!\n\nSo what and how much do you need?  This depends on several things.  First, how many customers are you expecting?  If the weather is foul, and the price is high, don't expect too many (maybe 15-25 tops).  However, if the weather is hot and clear, and your prices are reasonable, you could expect about 50-75 customers.\n\nOnce you've decided how many customers to expect, you can figure out what you need.  One paper cup per customer is necessary, plus however many ice cubes you've decided to serve your product with.  The sugar and lemons can be a bit trickier.\n\nFirst, you'll need to figure out how many pitchers of lemonade you'll need to serve the customers you expect.  If you're not putting much ice in the cups, expect a pitcher to serve about 12 cups.  If you're giving plenty of ice, you can expect a pitcher to go for 20-25 cups. Next, figure out how many lemons and cups of sugar will be required to make that many pitchers!\n\nMake sure you have enough of everything, because if you're missing any ingredients, you can't make your lemonade!  Also, it's better to buy a little extra to have left over (or possibly go to waste), than to not have enough.\n\nAnother few tips:  Your paper cups will never go bad, so you should always keep those on hand.  Your ice will melt at the end of the day, so if you don't plan on using much ice, don't go overboard on buying it.  Lemons and sugar will normally be OK the next day, but are subject to bugs and spoiling!");
    }
    
    void ipHelpOK_Action(final ActionEvent actionEvent) {
        this.requestFocus();
        this.inventoryHelp.setVisible(false);
        this.inventoryPanel.setVisible(true);
    }
    
    void bankruptButton_ActionPerformed(final ActionEvent actionEvent) {
        this.requestFocus();
        this.inventoryPanel.setVisible(false);
        this.finalizeGame();
    }
    
    void bankruptButton2_ActionPerformed(final ActionEvent actionEvent) {
        this.requestFocus();
        this.priceQualityPanel.setVisible(false);
        this.finalizeGame();
    }
    
    void button1_ActionPerformed(final ActionEvent actionEvent) {
        this.requestFocus();
        this.priceQualityPanel.setVisible(false);
        this.inventoryPanel.setVisible(true);
    }
    
    public Lemonade() {
        this.itemNum = new int[][] { { 10, 30, 75 }, { 8, 20, 48 }, { 100, 250, 500 }, { 25, 50, 100 } };
        this.itemDesc = new String[] { "Lemons", "Cups of Sugar", "Ice Cubes", "Paper Cups" };
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == Lemonade.this.qualityOKButton) {
                Lemonade.this.qualityOKButton_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.invOKButton) {
                Lemonade.this.invOKButton_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.acqOKButton) {
                Lemonade.this.acqOKButton_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.buyCupsButton) {
                Lemonade.this.buyCupsButton_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.buyLemonsButton) {
                Lemonade.this.buyLemonsButton_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.buySugarButton) {
                Lemonade.this.buySugarButton_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.buyIceButton) {
                Lemonade.this.buyIceButton_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.buyOpt1) {
                Lemonade.this.buyOpt1_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.buyOpt2) {
                Lemonade.this.buyOpt2_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.buyOpt3) {
                Lemonade.this.buyOpt3_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.FFButton) {
                Lemonade.this.FFButton_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.EODOKButton) {
                Lemonade.this.EODOKButton_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.inventoryOK) {
                Lemonade.this.inventoryOK_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.playAgain) {
                Lemonade.this.playAgain_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.introOKButton) {
                Lemonade.this.introOK_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.copyrightOKButton) {
                Lemonade.this.copyrightOKButton_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.pqcHelpButton) {
                Lemonade.this.pqcHelpButton_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.pqcHelpOK) {
                Lemonade.this.pqcHelpOK_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.ipHelpButton) {
                Lemonade.this.ipHelpButton_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.ipHelpOK) {
                Lemonade.this.ipHelpOK_Action(actionEvent);
                return;
            }
            if (source == Lemonade.this.bankruptButton) {
                Lemonade.this.bankruptButton_ActionPerformed(actionEvent);
                return;
            }
            if (source == Lemonade.this.bankruptButton2) {
                Lemonade.this.bankruptButton2_ActionPerformed(actionEvent);
                return;
            }
            if (source == Lemonade.this.button1) {
                Lemonade.this.button1_ActionPerformed(actionEvent);
            }
        }
    }
}
