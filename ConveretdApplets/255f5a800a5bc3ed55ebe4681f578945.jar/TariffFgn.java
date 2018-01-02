import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.event.KeyEvent;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Label;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TariffFgn extends Applet implements ItemListener, ActionListener, KeyListener
{
    private Label lblCountry;
    private Choice cmbCountries;
    private String[][] country;
    private Label lblItems;
    private Choice cmbItems;
    private Label lblWeight;
    private TextField txtWeight;
    private Label lblGrams;
    private Label lblService;
    private Choice cmbService;
    private Label lblMode;
    private Choice cmbMode;
    private Checkbox chkDeliveryAdvice;
    private Checkbox chkDeliveryToAddressee;
    private Checkbox chkDeliveryFree;
    private Label lblInsAmount;
    private TextField txtInsAmount;
    private Label lblInsRs;
    private Button cmdSubmit;
    private Button cmdReset;
    private long lMaxLetter;
    private long lMaxSmallPackets;
    private long lMaxPapers;
    private long lMaxRNP;
    private long lMOBhuthan;
    private long lMONepal;
    
    public void init() {
        super.init();
        this.buildUI();
    }
    
    public void paint(final Graphics graphics) {
    }
    
    void buildUI() {
        this.setLayout(null);
        this.setForeground(Color.black);
        this.setBackground(Color.white);
        this.addNotify();
        final Font font = new Font("Verdana", 1, 12);
        (this.lblCountry = new Label("Destination Country")).setFont(font);
        (this.cmbCountries = new Choice()).setFont(font);
        (this.lblItems = new Label("Item")).setFont(font);
        (this.cmbItems = new Choice()).setFont(font);
        (this.lblWeight = new Label()).setFont(font);
        (this.lblGrams = new Label()).setFont(font);
        (this.txtWeight = new TextField()).setFont(font);
        (this.lblService = new Label()).setFont(font);
        (this.cmbService = new Choice()).setFont(font);
        (this.lblMode = new Label()).setFont(font);
        (this.cmbMode = new Choice()).setFont(font);
        (this.chkDeliveryAdvice = new Checkbox()).setFont(font);
        (this.chkDeliveryToAddressee = new Checkbox()).setFont(font);
        (this.chkDeliveryFree = new Checkbox()).setFont(font);
        (this.lblInsAmount = new Label()).setFont(font);
        (this.txtInsAmount = new TextField()).setFont(font);
        (this.lblInsRs = new Label()).setFont(font);
        (this.cmdSubmit = new Button("Submit")).setFont(font);
        (this.cmdReset = new Button("Reset")).setFont(font);
        this.lblCountry.setBounds(10, 30, 120, 20);
        this.cmbCountries.setBounds(130, 30, 310, 20);
        this.lblItems.setBounds(10, 70, 90, 20);
        this.cmbItems.setBounds(130, 70, 310, 20);
        this.lblWeight.setBounds(10, 110, 100, 25);
        this.txtWeight.setBounds(130, 110, 150, 20);
        this.lblGrams.setBounds(290, 110, 60, 20);
        this.lblMode.setBounds(10, 150, 110, 20);
        this.cmbMode.setBounds(130, 150, 155, 20);
        this.lblService.setBounds(10, 190, 100, 20);
        this.cmbService.setBounds(130, 190, 310, 20);
        this.lblInsAmount.setBounds(10, 230, 110, 20);
        this.txtInsAmount.setBounds(130, 230, 150, 20);
        this.lblInsRs.setBounds(290, 230, 60, 20);
        this.chkDeliveryAdvice.setBounds(130, 270, 310, 20);
        this.chkDeliveryToAddressee.setBounds(130, 310, 310, 20);
        this.chkDeliveryFree.setBounds(130, 350, 310, 20);
        this.cmdSubmit.setBounds(165, 390, 60, 30);
        this.cmdReset.setBounds(230, 390, 60, 30);
        this.add(this.lblCountry);
        this.add(this.cmbCountries);
        this.add(this.lblItems);
        this.add(this.cmbItems);
        this.add(this.lblWeight);
        this.add(this.txtWeight);
        this.add(this.lblGrams);
        this.add(this.lblMode);
        this.add(this.cmbMode);
        this.add(this.lblService);
        this.add(this.cmbService);
        this.add(this.lblInsAmount);
        this.add(this.txtInsAmount);
        this.add(this.lblInsRs);
        this.add(this.chkDeliveryAdvice);
        this.add(this.chkDeliveryToAddressee);
        this.add(this.chkDeliveryFree);
        this.add(this.cmdSubmit);
        this.add(this.cmdReset);
        this.cmbCountries.addItemListener(this);
        this.cmbCountries.addKeyListener(this);
        this.cmbItems.addItemListener(this);
        this.cmbItems.addKeyListener(this);
        this.cmbMode.addItemListener(this);
        this.cmbMode.addKeyListener(this);
        this.cmbService.addItemListener(this);
        this.cmbService.addKeyListener(this);
        this.cmdSubmit.addActionListener(this);
        this.cmdReset.addActionListener(this);
        this.country = this.readProperty(this.cmbCountries);
        this.readMaxValues();
        this.hideAll();
        this.cmdSubmitEnable();
        this.repaint();
    }
    
    private void showItem() {
        this.hideAll();
        final String selectedItem = this.cmbCountries.getSelectedItem();
        if (selectedItem.equals("--Select a Country--")) {
            this.cmdSubmitEnable();
            this.cmdReset.setEnabled(false);
            this.repaint();
            return;
        }
        this.cmdReset.setEnabled(true);
        this.lblItems.setVisible(true);
        this.lblItems.setText("Item");
        this.cmbItems.setVisible(true);
        this.cmbItems.removeAll();
        this.cmbItems.add("--Select an Item--");
        this.cmbItems.add("Letter");
        this.cmbItems.add("Aerogramme");
        this.cmbItems.add("Post Card");
        this.cmbItems.add("Air Mail Post Card");
        this.cmbItems.add("Printed Paper");
        this.cmbItems.add("Sample and Small Packet");
        this.cmbItems.add("Registered News Paper");
        this.cmbItems.add("Blind Literature Packet");
        final int n = this.cmbCountries.getSelectedIndex() - 1;
        if (Integer.parseInt(this.country[n][0]) > 0 || Integer.parseInt(this.country[n][2]) > 0) {
            this.cmbItems.add("Parcel");
        }
        this.cmbItems.add("Bulk Bag");
        if (selectedItem.equals("BHUTHAN") || selectedItem.equals("NEPAL")) {
            this.cmbItems.add("Money Order");
        }
        this.repaint();
    }
    
    private void hideItem() {
        this.lblItems.setVisible(false);
        this.cmbItems.setVisible(false);
    }
    
    private void hideWeight() {
        this.lblWeight.setVisible(false);
        this.txtWeight.setVisible(false);
        this.lblGrams.setVisible(false);
    }
    
    private void hideService() {
        this.lblService.setVisible(false);
        this.cmbService.setVisible(false);
    }
    
    private void hideMode() {
        this.lblMode.setVisible(false);
        this.cmbMode.setVisible(false);
    }
    
    private void hideIns() {
        this.lblInsAmount.setVisible(false);
        this.txtInsAmount.setVisible(false);
        this.lblInsRs.setVisible(false);
    }
    
    private void hideDeliveryAdvice() {
        this.chkDeliveryAdvice.setVisible(false);
    }
    
    private void hideDeliveryAddressee() {
        this.chkDeliveryToAddressee.setVisible(false);
    }
    
    private void hideDeliveryFree() {
        this.chkDeliveryFree.setVisible(false);
    }
    
    private void hideAll() {
        this.hideItem();
        this.hideWeight();
        this.hideService();
        this.hideMode();
        this.hideIns();
        this.hideDeliveryAddressee();
        this.hideDeliveryAdvice();
        this.hideDeliveryFree();
        this.cmdReset.setEnabled(false);
    }
    
    private void hideAll2() {
        this.hideWeight();
        this.hideService();
        this.hideMode();
        this.hideIns();
        this.hideDeliveryAddressee();
        this.hideDeliveryAdvice();
        this.hideDeliveryFree();
    }
    
    private void hideAll3() {
        this.hideService();
        this.hideIns();
        this.hideDeliveryAddressee();
        this.hideDeliveryAdvice();
        this.hideDeliveryFree();
    }
    
    private void hideAll4() {
        this.hideIns();
        this.hideDeliveryAddressee();
        this.hideDeliveryAdvice();
        this.hideDeliveryFree();
    }
    
    private void showWeight(final String text, final String text2) {
        this.lblWeight.setVisible(true);
        this.txtWeight.setVisible(true);
        this.lblGrams.setVisible(true);
        this.lblWeight.setText(text);
        this.txtWeight.setText("");
        this.lblGrams.setText(text2);
    }
    
    private void showService(final String text, final String[] array) {
        this.lblService.setVisible(true);
        this.lblService.setText(text);
        this.cmbService.setVisible(true);
        this.cmbService.removeAll();
        for (int i = 0; i < array.length; ++i) {
            this.cmbService.add(array[i]);
        }
    }
    
    private void cmdSubmitEnable() {
        int n = 1;
        if (this.cmbCountries.getSelectedItem() == "--Select a Country--") {
            n = 0;
        }
        if (n != 0 && this.cmbItems.isVisible() && this.cmbItems.getSelectedItem() == "--Select an Item--") {
            n = 0;
        }
        if (n != 0 && this.cmbMode.isVisible() && this.cmbMode.getSelectedItem() == "--Select a Mode--") {
            n = 0;
        }
        if (n != 0 && this.cmbService.isVisible() && this.cmbService.getSelectedItem() == "--Select a Service--") {
            n = 0;
        }
        if (n != 0) {
            this.cmdSubmit.setEnabled(true);
        }
        else {
            this.cmdSubmit.setEnabled(false);
        }
    }
    
    public void repaint() {
        int n = 70;
        if (this.lblItems.isVisible()) {
            this.lblItems.setLocation(10, n);
            this.cmbItems.setLocation(130, n);
            n += 40;
        }
        if (this.lblWeight.isVisible()) {
            this.lblWeight.setLocation(10, n);
            this.txtWeight.setLocation(130, n);
            this.lblGrams.setLocation(290, n);
            n += 40;
        }
        if (this.lblMode.isVisible()) {
            this.lblMode.setLocation(10, n);
            this.cmbMode.setLocation(130, n);
            n += 40;
        }
        if (this.lblService.isVisible()) {
            this.lblService.setLocation(10, n);
            this.cmbService.setLocation(130, n);
            n += 40;
        }
        if (this.lblInsAmount.isVisible()) {
            this.lblInsAmount.setLocation(10, n);
            this.txtInsAmount.setLocation(130, n);
            this.lblInsRs.setLocation(290, n);
            n += 40;
        }
        if (this.chkDeliveryAdvice.isVisible()) {
            this.chkDeliveryAdvice.setLocation(130, n);
            n += 40;
        }
        if (this.chkDeliveryToAddressee.isVisible()) {
            this.chkDeliveryToAddressee.setLocation(130, n);
            n += 40;
        }
        if (this.chkDeliveryFree.isVisible()) {
            this.chkDeliveryFree.setLocation(130, n);
            n += 40;
        }
        this.cmdSubmit.setLocation(165, n);
        this.cmdReset.setLocation(230, n);
        n += 40;
        super.repaint();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.cmbCountries) {
            this.showItem();
            return;
        }
        if (itemEvent.getSource() == this.cmbItems) {
            this.handleItem();
            return;
        }
        if (itemEvent.getSource() == this.cmbMode) {
            this.handleMode();
            return;
        }
        if (itemEvent.getSource() == this.cmbService) {
            this.handleService();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        if (s == "Submit") {
            if (this.doValidation()) {
                this.showStatus("OK");
                final String selectedItem = this.cmbCountries.getSelectedItem();
                String s2;
                if (this.cmbItems.getSelectedItem() != null) {
                    s2 = selectedItem + "," + this.cmbItems.getSelectedItem();
                }
                else {
                    s2 = selectedItem + ",";
                }
                String s3;
                if (this.txtWeight.getText().length() > 0) {
                    s3 = s2 + "," + this.txtWeight.getText();
                }
                else {
                    s3 = s2 + ",";
                }
                String s4;
                if (this.cmbService.getSelectedItem() != null) {
                    s4 = s3 + "," + this.cmbService.getSelectedItem();
                }
                else {
                    s4 = s3 + ",";
                }
                String s5;
                if (this.cmbMode.getSelectedItem() != null) {
                    s5 = s4 + "," + this.cmbMode.getSelectedItem();
                }
                else {
                    s5 = s4 + ",";
                }
                String s6;
                if (this.chkDeliveryAdvice.getState()) {
                    s6 = s5 + ",1";
                }
                else {
                    s6 = s5 + ",";
                }
                String s7;
                if (this.chkDeliveryFree.getState()) {
                    s7 = s6 + ",1";
                }
                else {
                    s7 = s6 + ",";
                }
                String s8;
                if (this.chkDeliveryToAddressee.getState()) {
                    s8 = s7 + ",1";
                }
                else {
                    s8 = s7 + ",";
                }
                if (this.txtInsAmount.getText().length() > 0) {
                    s = s8 + "," + this.txtInsAmount.getText();
                }
                else {
                    s = s8 + ",";
                }
            }
            this.showResults(s);
        }
        else if (s == "Reset") {
            this.hideAll();
            this.cmbCountries.select(0);
            this.cmdSubmitEnable();
            this.cmdReset.setEnabled(false);
            this.repaint();
        }
    }
    
    private void showResults(final String s) {
        final String string = this.getCodeBase() + "tarifffgn.asp?item=" + URLEncoder.encode(s);
        URL url = null;
        try {
            url = new URL(string);
        }
        catch (MalformedURLException ex) {}
        this.getAppletContext().showDocument(url);
    }
    
    private boolean doValidation() {
        final String selectedItem = this.cmbItems.getSelectedItem();
        if (selectedItem == "Letter") {
            final long long1 = Long.parseLong(this.txtWeight.getText());
            if (long1 < 1L || long1 > this.lMaxLetter) {
                this.showError("Weight to be between 1 and " + this.lMaxLetter + " Grams.", "", "");
                this.txtWeight.requestFocus();
                return false;
            }
        }
        if (selectedItem == "Sample and Small Packet") {
            final long long2 = Long.parseLong(this.txtWeight.getText());
            if (long2 < 1L || long2 > this.lMaxSmallPackets) {
                this.showError("Weight to be between 1 and " + this.lMaxSmallPackets + " Grams.", "", "");
                this.txtWeight.requestFocus();
                return false;
            }
        }
        if (selectedItem == "Printed Paper") {
            final long long3 = Long.parseLong(this.txtWeight.getText());
            if (long3 < 1L || long3 > this.lMaxPapers) {
                this.showError("Weight to be between 1 and " + this.lMaxPapers + " Grams.", "", "");
                this.txtWeight.requestFocus();
                return false;
            }
        }
        if (selectedItem == "Registered News Paper") {
            final long long4 = Long.parseLong(this.txtWeight.getText());
            if (long4 < 1L || long4 > this.lMaxRNP) {
                this.showError("Weight to be between 1 and " + this.lMaxRNP + " Grams.", "", "");
                this.txtWeight.requestFocus();
                return false;
            }
        }
        if (selectedItem == "Money Order") {
            final long long5 = Long.parseLong(this.txtWeight.getText());
            long n;
            if (this.cmbCountries.getSelectedItem().equals("BHUTHAN")) {
                n = this.lMOBhuthan;
            }
            else {
                n = this.lMONepal;
            }
            if (long5 < 1L || long5 > n) {
                this.showError("Money Order amount to be between 1 and " + n + " Rupees.", "", "");
                this.txtWeight.requestFocus();
                return false;
            }
        }
        if (selectedItem == "Parcel") {
            final long long6 = Long.parseLong(this.txtWeight.getText());
            final int n2 = this.cmbCountries.getSelectedIndex() - 1;
            final String selectedItem2 = this.cmbMode.getSelectedItem();
            long n3;
            if (selectedItem2 == "Surface Air Lifted" || selectedItem2 == "Surface") {
                n3 = Integer.parseInt(this.country[n2][2]);
            }
            else {
                n3 = Integer.parseInt(this.country[n2][0]);
            }
            if (long6 < 1L || long6 > n3) {
                this.showError("Weight of the Parcel to be between 1 and " + n3 + "  Grams.", "", "");
                this.txtWeight.requestFocus();
                return false;
            }
        }
        if (this.cmbService.getSelectedItem() == "Insured") {
            final int n4 = this.cmbCountries.getSelectedIndex() - 1;
            long n5;
            if (selectedItem == "Letter") {
                if (this.cmbMode.getSelectedItem() == "Air") {
                    n5 = Integer.parseInt(this.country[n4][4]);
                }
                else {
                    n5 = Integer.parseInt(this.country[n4][5]);
                }
            }
            else if (this.cmbMode.getSelectedItem() == "Air") {
                n5 = Integer.parseInt(this.country[n4][1]);
            }
            else {
                n5 = Integer.parseInt(this.country[n4][3]);
            }
            final long long7 = Long.parseLong(this.txtInsAmount.getText());
            if (long7 < 1L || long7 > n5) {
                this.showError("Amount of the Insurance to be between 1 and " + n5 + "  Rupees.", "", "");
                this.txtInsAmount.requestFocus();
                return false;
            }
        }
        return true;
    }
    
    private void showError(final String s, final String s2, final String s3) {
        final MsgBox msgBox = new MsgBox(new Frame(""), "Input Error", s, s2, s3, 1);
        this.requestFocus();
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final char keyChar = keyEvent.getKeyChar();
        if ((keyChar < '0' || keyChar > '9') && (keyChar < 'a' || keyChar > 'z') && (keyChar < 'A' || keyChar > 'Z') && keyChar != '-') {
            return;
        }
        if (keyEvent.getSource() == this.cmbCountries) {
            this.moveCombo(this.cmbCountries, keyEvent.getKeyChar());
            this.showItem();
            return;
        }
        if (keyEvent.getSource() == this.cmbItems) {
            this.moveCombo(this.cmbItems, keyEvent.getKeyChar());
            this.handleItem();
            return;
        }
        if (keyEvent.getSource() == this.cmbMode) {
            this.moveCombo(this.cmbMode, keyEvent.getKeyChar());
            this.handleMode();
            return;
        }
        if (keyEvent.getSource() == this.cmbService) {
            this.moveCombo(this.cmbService, keyEvent.getKeyChar());
            this.handleService();
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    private void handleItem() {
        this.hideAll2();
        final String selectedItem = this.cmbItems.getSelectedItem();
        if (selectedItem == "--Select an Item--") {
            this.hideAll();
            this.cmdReset.setEnabled(true);
            this.repaint();
            return;
        }
        if (selectedItem == "Post Card" || selectedItem == "Air Mail Post Card") {
            this.cmdSubmitEnable();
            this.repaint();
            return;
        }
        if (selectedItem == "Aerogramme") {
            this.showService("Service", new String[] { "--Select a Service--", "Un-Registered", "Registered" });
            this.cmdSubmitEnable();
            this.repaint();
            return;
        }
        if (selectedItem == "Money Order") {
            this.showWeight("Amount", "Rupees");
            this.cmdSubmitEnable();
            this.repaint();
            return;
        }
        this.showWeight("Weight", "Grams");
        this.lblMode.setVisible(true);
        this.lblMode.setText("Transmission By");
        this.cmbMode.setVisible(true);
        this.cmbMode.removeAll();
        this.cmbMode.add("--Select a Mode--");
        final int n = this.cmbCountries.getSelectedIndex() - 1;
        if (selectedItem == "Parcel") {
            if (Integer.parseInt(this.country[n][6]) == 1 && Integer.parseInt(this.country[n][2]) > 0) {
                this.cmbMode.add("Surface Air Lifted");
            }
            if (Integer.parseInt(this.country[n][0]) > 0) {
                this.cmbMode.add("Air");
            }
        }
        else {
            this.cmbMode.add("Surface");
            if (Integer.parseInt(this.country[n][6]) == 2 && selectedItem != "Letter" && selectedItem != "Blind Literature Packet") {
                this.cmbMode.add("Surface Air Lifted");
            }
            this.cmbMode.add("Air");
        }
        this.cmdSubmitEnable();
        this.repaint();
    }
    
    private void handleMode() {
        this.hideAll3();
        final String selectedItem = this.cmbMode.getSelectedItem();
        this.lblService.setVisible(true);
        this.lblService.setText("Service");
        this.cmbService.setVisible(true);
        this.cmbService.removeAll();
        this.cmbService.add("--Select a Service--");
        this.cmbService.add("Un-Registered");
        this.cmbService.add("Registered");
        final String selectedItem2 = this.cmbItems.getSelectedItem();
        final int n = this.cmbCountries.getSelectedIndex() - 1;
        int n2 = 0;
        if (selectedItem2 == "Letter") {
            if (selectedItem == "Surface") {
                n2 = 5;
            }
            if (selectedItem == "Air") {
                n2 = 4;
            }
        }
        if (selectedItem2 == "Parcel") {
            if (selectedItem == "Surface" || selectedItem == "Surface Air Lifted") {
                n2 = 3;
            }
            if (selectedItem == "Air") {
                n2 = 1;
            }
        }
        if (n2 != 0 && Integer.parseInt(this.country[n][n2]) > 0) {
            this.cmbService.add("Insured");
        }
        this.cmdSubmitEnable();
        this.repaint();
    }
    
    private void handleService() {
        this.hideAll4();
        final String selectedItem = this.cmbService.getSelectedItem();
        if (selectedItem == "--Select a Service--" || selectedItem == "Un-Registered") {
            this.cmdSubmitEnable();
            this.repaint();
            return;
        }
        if (selectedItem == "Insured") {
            this.lblInsAmount.setVisible(true);
            this.lblInsAmount.setText("Insured for");
            this.txtInsAmount.setVisible(true);
            this.txtInsAmount.setText("");
            this.lblInsRs.setText("Rupees");
            this.lblInsRs.setVisible(true);
        }
        this.chkDeliveryAdvice.setState(false);
        this.chkDeliveryAdvice.setVisible(true);
        this.chkDeliveryAdvice.setLabel("Delivery Advice required");
        String label = "";
        if (this.cmbItems.getSelectedItem() == "Sample and Small Packet") {
            if (Long.parseLong(this.txtWeight.getText()) > 500L) {
                label = "Delivery to Addressee required";
            }
        }
        else {
            label = "Delivery to Addressee in person required";
        }
        if (label != "") {
            this.chkDeliveryToAddressee.setState(false);
            this.chkDeliveryToAddressee.setVisible(true);
            this.chkDeliveryToAddressee.setLabel(label);
        }
        if (this.cmbItems.getSelectedItem() == "Parcel") {
            this.chkDeliveryFree.setState(false);
            this.chkDeliveryFree.setVisible(true);
            this.chkDeliveryFree.setLabel("Delivery free of charges required");
        }
        this.cmdSubmitEnable();
        this.repaint();
    }
    
    private void moveCombo(final Choice choice, final char c) {
        final char upperCase = Character.toUpperCase(c);
        final int selectedIndex = choice.getSelectedIndex();
        boolean b = false;
        if (selectedIndex < choice.getItemCount()) {
            for (int i = selectedIndex + 1; i < choice.getItemCount(); ++i) {
                if (Character.toUpperCase(choice.getItem(i).charAt(0)) == upperCase) {
                    choice.select(i);
                    b = true;
                    break;
                }
            }
        }
        if (!b && selectedIndex > 0) {
            for (int j = 0; j < selectedIndex; ++j) {
                if (Character.toUpperCase(choice.getItem(j).charAt(0)) == upperCase) {
                    choice.select(j);
                    break;
                }
            }
        }
    }
    
    private String[][] readProperty(final Choice choice) {
        String[][] array = null;
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("country.txt")));
            for (int i = 0; i < 3; ++i) {
                bufferedReader.readLine();
            }
            array = new String[Integer.parseInt(bufferedReader.readLine())][7];
            int n = 0;
            this.cmbCountries.removeAll();
            this.cmbCountries.add("--Select a Country--");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String[] splitString = this.splitString(line, ',');
                this.cmbCountries.add(splitString[0]);
                for (int j = 0; j < 7; ++j) {
                    array[n][j] = splitString[j + 1];
                }
                ++n;
            }
        }
        catch (Exception ex) {}
        return array;
    }
    
    private void readMaxValues() {
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream("max_fgn.txt");
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
            for (int i = 0; i < 2; ++i) {
                bufferedReader.readLine();
            }
            this.lMaxLetter = Long.parseLong(this.splitString(bufferedReader.readLine(), ',')[1]);
            this.lMaxSmallPackets = Long.parseLong(this.splitString(bufferedReader.readLine(), ',')[1]);
            this.lMaxPapers = Long.parseLong(this.splitString(bufferedReader.readLine(), ',')[1]);
            this.lMaxRNP = Long.parseLong(this.splitString(bufferedReader.readLine(), ',')[1]);
            this.lMOBhuthan = Long.parseLong(this.splitString(bufferedReader.readLine(), ',')[1]);
            this.lMONepal = Long.parseLong(this.splitString(bufferedReader.readLine(), ',')[1]);
            bufferedReader.close();
            resourceAsStream.close();
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    private String[] splitString(final String s, final char c) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == c) {
                ++n;
            }
        }
        if (n == 0) {
            return new String[] { s };
        }
        final String[] array = new String[n + 1];
        int index = -1;
        int n2 = 0;
        int j;
        for (j = 0; j < n; ++j) {
            index = s.indexOf(c, index + 1);
            array[j] = s.substring(n2, index);
            n2 = index + 1;
        }
        array[j] = s.substring(n2);
        return array;
    }
}
