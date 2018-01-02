// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.net.URL;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.AbstractButton;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.text.Document;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class formatView extends JPanel implements ActionListener
{
    static final long serialVersionUID = 0L;
    private CResourceManager myResource;
    private JPanel c_navngivPanel;
    public JLabel c_maalestok;
    private JLabel c_opdateret;
    private JLabel c_maal;
    private JLabel c_navngiv;
    public JTextField c_navngivTextField;
    public String c_planoPris;
    public String c_falsetPris;
    public String c_indrammetPris;
    public String c_lamineretPlanoPris;
    public String c_lamineretFalsetPris;
    public JRadioButton c_planoButton;
    public JRadioButton c_falsetButton;
    public JRadioButton c_indrammetButton;
    public JRadioButton c_lamineretPlanoButton;
    public JRadioButton c_lamineretFalsetButton;
    private JFrame c_toolTipFrame;
    private JPanel c_toolTipPanel;
    private JLabel c_toolTipLabel;
    private JButton c_toolTipButton;
    private ButtonGroup c_buttonGroup;
    private JTextArea c_information;
    public JPanel c_kortEksempelPane;
    public JLabel c_kortEksempelLabel;
    private JPanel c_lowerButtonPane;
    public JButton c_addtocart;
    public JButton c_tilbage;
    private String mapType;
    
    public formatView(final String type) {
        this.myResource = CResourceManager.instance();
        this.c_navngivPanel = new JPanel();
        this.c_maalestok = new JLabel(this.myResource.getResource("format.maalestok"));
        this.c_opdateret = new JLabel();
        this.c_maal = new JLabel();
        this.c_navngiv = new JLabel(this.myResource.getResource("format.navngivditkort"));
        this.c_navngivTextField = new JTextField();
        this.c_planoPris = this.myResource.getResource("format.opklaebning.pris.ditkort.udenLaminering");
        this.c_falsetPris = this.myResource.getResource("format.opklaebning.pris.ditkort.udenLaminering");
        this.c_indrammetPris = this.myResource.getResource("format.opklaebning.pris.ditkort.indrammet");
        this.c_lamineretPlanoPris = this.myResource.getResource("format.opklaebning.pris.ditkort.laminering");
        this.c_lamineretFalsetPris = this.myResource.getResource("format.opklaebning.pris.ditkort.laminering");
        this.c_planoButton = new JRadioButton(String.valueOf(this.myResource.getResource("format.opklaebning.type.1")) + "                                " + this.c_planoPris);
        this.c_falsetButton = new JRadioButton(String.valueOf(this.myResource.getResource("format.opklaebning.type.2")) + "                               " + this.c_falsetPris);
        this.c_indrammetButton = new JRadioButton(String.valueOf(this.myResource.getResource("format.opklaebning.type.3")) + "                      " + this.c_indrammetPris);
        this.c_lamineretPlanoButton = new JRadioButton(String.valueOf(this.myResource.getResource("format.opklaebning.type.4")) + "         " + this.c_lamineretPlanoPris);
        this.c_lamineretFalsetButton = new JRadioButton(String.valueOf(this.myResource.getResource("format.opklaebning.type.5")) + "         " + this.c_lamineretFalsetPris);
        this.c_toolTipPanel = new JPanel();
        this.c_toolTipLabel = new JLabel();
        this.c_toolTipButton = new JButton();
        this.c_buttonGroup = new ButtonGroup();
        this.c_information = new JTextArea();
        this.c_kortEksempelPane = new JPanel();
        this.c_kortEksempelLabel = new JLabel();
        this.c_lowerButtonPane = new JPanel();
        this.c_addtocart = new JButton(this.myResource.getResource("knap.laegikurv"));
        this.c_tilbage = new JButton(this.myResource.getResource("knap.tilbage"));
        this.mapType = "";
        try {
            if (type.equals("egnskort")) {
                this.createFormatViewForEgnskort();
                this.mapType = type;
            }
        }
        catch (NullPointerException e) {
            this.createDefaultFormatView();
        }
    }
    
    public void createFormatViewForEgnskort() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.c_opdateret.setText("\n");
        this.c_planoButton.setAlignmentX(0.0f);
        this.c_indrammetButton.setAlignmentX(0.0f);
        this.c_lamineretPlanoButton.setAlignmentX(0.0f);
        this.setLayout(new BoxLayout(this, 3));
        this.c_addtocart.setActionCommand("addtocart");
        this.c_addtocart.setEnabled(false);
        this.c_tilbage.setActionCommand("tilbage");
        this.c_tilbage.setToolTipText(this.myResource.getResource("knap.tilbage.tip"));
        this.c_tilbage.setEnabled(true);
        this.c_lowerButtonPane.setLayout(new BoxLayout(this.c_lowerButtonPane, 2));
        this.c_lowerButtonPane.setAlignmentX(0.0f);
        this.c_lowerButtonPane.add(this.c_tilbage);
        this.c_lowerButtonPane.add(Box.createRigidArea(new Dimension(70, 0)));
        this.c_lowerButtonPane.add(this.c_addtocart);
        this.c_navngivTextField.setDocument(new JTextFieldLimit(21));
        this.c_navngivTextField.setBorder(BorderFactory.createTitledBorder(this.myResource.getResource("format.navngivning.bordername")));
        this.c_navngivTextField.setEditable(false);
        this.c_navngivPanel.setLayout(new BoxLayout(this.c_navngivPanel, 3));
        this.c_navngivPanel.setBorder(BorderFactory.createTitledBorder("Format"));
        this.c_navngivPanel.setPreferredSize(new Dimension(243, 274));
        this.c_navngivPanel.add(this.c_maalestok);
        this.c_navngivPanel.add(this.c_opdateret);
        this.c_navngivPanel.add(this.c_maal);
        this.c_navngivPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        this.c_navngivPanel.add(this.c_navngivTextField);
        this.c_navngivPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        this.c_buttonGroup.add(this.c_planoButton);
        if (!ditkort.isInEnglish) {
            this.c_buttonGroup.add(this.c_indrammetButton);
        }
        this.c_buttonGroup.add(this.c_lamineretPlanoButton);
        this.c_planoButton.setActionCommand("plano");
        if (!ditkort.isInEnglish) {
            this.c_indrammetButton.setActionCommand("indrammet");
        }
        this.c_lamineretPlanoButton.setActionCommand("lamineretplano");
        this.c_planoButton.addActionListener(this);
        this.c_indrammetButton.addActionListener(this);
        this.c_lamineretPlanoButton.addActionListener(this);
        final JPanel opklaebningPanel = new JPanel();
        final JPanel opklaebningMulighederPanel = new JPanel(new GridLayout(0, 1));
        opklaebningPanel.setBorder(BorderFactory.createTitledBorder(this.myResource.getResource("format.opklaebning")));
        opklaebningPanel.setAlignmentX(0.0f);
        opklaebningPanel.add(opklaebningMulighederPanel, "Center");
        opklaebningMulighederPanel.add(this.c_planoButton);
        if (!ditkort.isInEnglish) {
            opklaebningMulighederPanel.add(this.c_indrammetButton);
        }
        opklaebningMulighederPanel.add(this.c_lamineretPlanoButton);
        this.c_navngivPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        this.c_navngivPanel.add(opklaebningPanel);
        final JPanel infoPanel = new JPanel(new FlowLayout(3, 0, 0));
        infoPanel.setBorder(BorderFactory.createTitledBorder(this.myResource.getResource("format.information")));
        infoPanel.setAlignmentX(0.0f);
        this.c_information.setMaximumSize(new Dimension(230, 120));
        this.c_information.setMinimumSize(new Dimension(230, 120));
        this.c_information.setPreferredSize(new Dimension(230, 120));
        this.c_information.setFont(new Font("Verdana", 1, 10));
        this.c_information.setBackground(new Color(238, 238, 238));
        this.c_information.setForeground(new Color(50, 50, 50));
        this.c_information.setEditable(false);
        this.c_information.setFocusable(false);
        this.c_information.setLineWrap(true);
        this.c_information.setWrapStyleWord(true);
        infoPanel.add(this.c_information);
        this.c_kortEksempelPane.setBorder(BorderFactory.createTitledBorder(this.myResource.getResource("format.layout")));
        this.c_kortEksempelPane.setAlignmentX(0.0f);
        this.add(this.c_navngivPanel);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(infoPanel);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(this.c_lowerButtonPane);
        this.add(Box.createRigidArea(new Dimension(0, 213)));
        this.c_toolTipPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.c_toolTipPanel.setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();
        (this.c_toolTipFrame = new JFrame()).setTitle(this.myResource.getResource("format.opklaebningsmuligheder"));
        this.c_toolTipFrame.setResizable(false);
        this.c_toolTipLabel.setFont(new Font("Verdana", 1, 12));
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        this.c_toolTipPanel.add(this.c_toolTipLabel, c);
        this.c_toolTipButton.setText(this.myResource.getResource("format.lukVindue"));
        this.c_toolTipButton.setActionCommand("format.lukvindue");
        this.c_toolTipButton.addActionListener(this);
        c.gridx = 0;
        c.gridy = 1;
        this.c_toolTipPanel.add(this.c_toolTipButton, c);
        this.c_toolTipFrame.add(this.c_toolTipPanel);
        this.c_planoButton.setSelected(true);
        this.selectPlano();
    }
    
    public void createDefaultFormatView() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.c_planoButton.setAlignmentX(0.0f);
        this.c_falsetButton.setAlignmentX(0.0f);
        this.c_indrammetButton.setAlignmentX(0.0f);
        this.c_lamineretPlanoButton.setAlignmentX(0.0f);
        this.c_lamineretFalsetButton.setAlignmentX(0.0f);
        this.setLayout(new BoxLayout(this, 3));
        this.c_addtocart.setActionCommand("addtocart");
        this.c_addtocart.setEnabled(false);
        this.c_tilbage.setActionCommand("tilbage");
        this.c_tilbage.setToolTipText(this.myResource.getResource("knap.tilbage.tip"));
        this.c_tilbage.setEnabled(true);
        this.c_lowerButtonPane.setLayout(new BoxLayout(this.c_lowerButtonPane, 2));
        this.c_lowerButtonPane.setAlignmentX(0.0f);
        this.c_lowerButtonPane.add(this.c_tilbage);
        this.c_lowerButtonPane.add(Box.createRigidArea(new Dimension(70, 0)));
        this.c_lowerButtonPane.add(this.c_addtocart);
        final Dimension navnTextFieldDim = new Dimension(450, 20);
        this.c_navngivTextField.setDocument(new JTextFieldLimit(33));
        this.c_navngivTextField.setPreferredSize(navnTextFieldDim);
        this.c_navngivTextField.setMaximumSize(navnTextFieldDim);
        this.c_navngivPanel.setLayout(new BoxLayout(this.c_navngivPanel, 3));
        this.c_navngivPanel.setBorder(BorderFactory.createTitledBorder("Format"));
        this.c_navngivPanel.setPreferredSize(new Dimension(243, 274));
        this.c_navngivPanel.add(this.c_maalestok);
        this.c_navngivPanel.add(this.c_opdateret);
        this.c_navngivPanel.add(this.c_maal);
        this.c_navngivPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        this.c_navngivPanel.add(this.c_navngiv);
        this.c_navngivPanel.add(this.c_navngivTextField);
        this.c_navngivPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        this.c_buttonGroup.add(this.c_planoButton);
        this.c_buttonGroup.add(this.c_falsetButton);
        if (!ditkort.isInEnglish) {
            this.c_buttonGroup.add(this.c_indrammetButton);
        }
        this.c_buttonGroup.add(this.c_lamineretPlanoButton);
        this.c_buttonGroup.add(this.c_lamineretFalsetButton);
        this.c_planoButton.setActionCommand("plano");
        this.c_falsetButton.setActionCommand("falset");
        this.c_indrammetButton.setActionCommand("indrammet");
        this.c_lamineretPlanoButton.setActionCommand("lamineretplano");
        this.c_lamineretFalsetButton.setActionCommand("lamineretfalset");
        this.c_planoButton.addActionListener(this);
        this.c_falsetButton.addActionListener(this);
        this.c_indrammetButton.addActionListener(this);
        this.c_lamineretPlanoButton.addActionListener(this);
        this.c_lamineretFalsetButton.addActionListener(this);
        final JPanel opklaebningPanel = new JPanel();
        final JPanel opklaebningMulighederPanel = new JPanel(new GridLayout(0, 1));
        opklaebningPanel.setBorder(BorderFactory.createTitledBorder(this.myResource.getResource("format.opklaebning")));
        opklaebningPanel.setAlignmentX(0.0f);
        opklaebningPanel.add(opklaebningMulighederPanel, "Center");
        opklaebningMulighederPanel.add(this.c_planoButton);
        opklaebningMulighederPanel.add(this.c_falsetButton);
        if (!ditkort.isInEnglish) {
            opklaebningMulighederPanel.add(this.c_indrammetButton);
        }
        opklaebningMulighederPanel.add(this.c_lamineretPlanoButton);
        opklaebningMulighederPanel.add(this.c_lamineretFalsetButton);
        this.c_navngivPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        this.c_navngivPanel.add(opklaebningPanel);
        final JPanel infoPanel = new JPanel(new FlowLayout(3, 0, 0));
        infoPanel.setBorder(BorderFactory.createTitledBorder(this.myResource.getResource("format.information")));
        infoPanel.setAlignmentX(0.0f);
        this.c_information.setMaximumSize(new Dimension(230, 120));
        this.c_information.setMinimumSize(new Dimension(230, 120));
        this.c_information.setPreferredSize(new Dimension(230, 120));
        this.c_information.setFont(new Font("Verdana", 1, 10));
        this.c_information.setBackground(new Color(238, 238, 238));
        this.c_information.setForeground(new Color(50, 50, 50));
        this.c_information.setEditable(false);
        this.c_information.setFocusable(false);
        this.c_information.setLineWrap(true);
        this.c_information.setWrapStyleWord(true);
        infoPanel.add(this.c_information);
        this.c_kortEksempelPane.setBorder(BorderFactory.createTitledBorder(this.myResource.getResource("format.layout")));
        this.c_kortEksempelPane.setAlignmentX(0.0f);
        this.add(this.c_navngivPanel);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(infoPanel);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(this.c_kortEksempelPane);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(this.c_lowerButtonPane);
        this.c_toolTipPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.c_toolTipPanel.setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();
        (this.c_toolTipFrame = new JFrame()).setTitle(this.myResource.getResource("format.opklaebningsmuligheder"));
        this.c_toolTipFrame.setResizable(false);
        this.c_toolTipLabel.setFont(new Font("Verdana", 1, 12));
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        this.c_toolTipPanel.add(this.c_toolTipLabel, c);
        this.c_toolTipButton.setText(this.myResource.getResource("format.lukVindue"));
        this.c_toolTipButton.setActionCommand("format.lukvindue");
        this.c_toolTipButton.addActionListener(this);
        c.gridx = 0;
        c.gridy = 1;
        this.c_toolTipPanel.add(this.c_toolTipButton, c);
        this.c_toolTipFrame.add(this.c_toolTipPanel);
        this.c_planoButton.setSelected(true);
        this.selectPlano();
    }
    
    public void enableToolTip() {
        this.c_addtocart.setToolTipText(this.myResource.getResource("format.laegikurvtip"));
    }
    
    public void updatePrice() {
        this.c_planoButton.setText(String.valueOf(this.mellemrum(this.myResource.getResource("format.knap.plano"))) + this.c_planoPris);
        this.c_falsetButton.setText(String.valueOf(this.mellemrum(this.myResource.getResource("format.knap.falset"))) + this.c_falsetPris);
        this.c_indrammetButton.setText(String.valueOf(this.mellemrum(this.myResource.getResource("format.knap.indrammet"))) + this.c_indrammetPris);
        this.c_lamineretPlanoButton.setText(String.valueOf(this.mellemrum(this.myResource.getResource("format.knap.lamineret.plano"))) + this.c_lamineretPlanoPris);
        this.c_lamineretFalsetButton.setText(String.valueOf(this.mellemrum(this.myResource.getResource("format.knap.lamineret.falset"))) + this.c_lamineretFalsetPris);
    }
    
    public void updateHistoricalMapPrice() {
        this.c_planoPris = this.myResource.getResource("format.opklaebning.pris.historiske.udenLaminering");
        this.c_falsetPris = this.myResource.getResource("format.opklaebning.pris.historiske.udenLaminering");
        this.c_indrammetPris = this.myResource.getResource("format.opklaebning.pris.historiske.indrammet");
        this.c_lamineretPlanoPris = this.myResource.getResource("format.opklaebning.pris.historiske.laminering");
        this.c_lamineretFalsetPris = this.myResource.getResource("format.opklaebning.pris.historiske.laminering");
    }
    
    public String mellemrum(final String tekst) {
        String space = " ";
        if (tekst.equals(this.myResource.getResource("format.knap.plano"))) {
            space = "                                ";
        }
        else if (tekst.equals(this.myResource.getResource("format.knap.falset"))) {
            space = "                               ";
        }
        else if (tekst.equals(this.myResource.getResource("format.knap.indrammet"))) {
            space = "                      ";
        }
        else if (tekst.equals(this.myResource.getResource("format.knap.lamineret.plano"))) {
            space = "         ";
        }
        else if (tekst.equals(this.myResource.getResource("format.knap.lamineret.falset"))) {
            space = "         ";
        }
        return String.valueOf(tekst) + space;
    }
    
    public void displayToolTip(final String tooltip) {
        this.c_toolTipLabel.setText(tooltip);
        this.c_toolTipFrame.pack();
        this.c_toolTipFrame.setVisible(true);
        this.c_toolTipFrame.setLocation(this.getLocationOnScreen().x - 450, this.getLocationOnScreen().y);
    }
    
    public void selectPlano() {
        try {
            final URL kortEksempelUrl = new URL(String.valueOf(Constant.imageURL) + "eksempel_plano_mini.jpg");
            this.c_kortEksempelLabel.setIcon(new ImageIcon(kortEksempelUrl));
            this.c_kortEksempelPane.removeAll();
            this.c_kortEksempelPane.add(this.c_kortEksempelLabel);
            this.c_kortEksempelPane.repaint();
            this.c_information.setText(this.myResource.getResource("format.opklaebning.info.1"));
        }
        catch (MalformedURLException URLe) {
            System.err.println("Exception in selectPlano: " + URLe.getMessage());
        }
    }
    
    public void selectFalset() {
        try {
            final URL kortEksempelUrl = new URL(String.valueOf(Constant.imageURL) + "eksempel_falset_mini.jpg");
            this.c_kortEksempelLabel.setIcon(new ImageIcon(kortEksempelUrl));
            this.c_kortEksempelPane.removeAll();
            this.c_kortEksempelPane.add(this.c_kortEksempelLabel);
            this.c_kortEksempelPane.repaint();
            if (this.mapType.equals("egnskort")) {
                this.c_information.setText(this.myResource.getResource("format.opklaebning.info.egnskort.falset"));
            }
            else {
                this.c_information.setText(this.myResource.getResource("format.opklaebning.info.2"));
            }
        }
        catch (MalformedURLException URLe) {
            System.err.println("Exception in selectFalset: " + URLe.getMessage());
        }
    }
    
    public void selectIndrammet() {
        try {
            final URL kortEksempelUrl = new URL(String.valueOf(Constant.imageURL) + "eksempel_indrammet_mini.jpg");
            this.c_kortEksempelLabel.setIcon(new ImageIcon(kortEksempelUrl));
            this.c_kortEksempelPane.removeAll();
            this.c_kortEksempelPane.add(this.c_kortEksempelLabel);
            this.c_kortEksempelPane.repaint();
            if (this.mapType.equals("egnskort")) {
                this.c_information.setText(this.myResource.getResource("format.opklaebning.info.egnskort.indrammet"));
            }
            else {
                this.c_information.setText(this.myResource.getResource("format.opklaebning.info.3"));
            }
        }
        catch (MalformedURLException URLe) {
            System.err.println("Exception in selectIndrammet: " + URLe.getMessage());
        }
    }
    
    public void selectLamineretPlano() {
        try {
            final URL kortEksempelUrl = new URL(String.valueOf(Constant.imageURL) + "eksempel_lamineret_plano_mini.jpg");
            this.c_kortEksempelLabel.setIcon(new ImageIcon(kortEksempelUrl));
            this.c_kortEksempelPane.removeAll();
            this.c_kortEksempelPane.add(this.c_kortEksempelLabel);
            this.c_kortEksempelPane.repaint();
            if (this.mapType.equals("egnskort")) {
                this.c_information.setText(this.myResource.getResource("format.opklaebning.info.egnskort.lamineret"));
            }
            else {
                this.c_information.setText(this.myResource.getResource("format.opklaebning.info.4"));
            }
        }
        catch (MalformedURLException URLe) {
            System.err.println("Exception in selectLamineretPlano: " + URLe.getMessage());
        }
    }
    
    public void selectLamineretFalset() {
        try {
            final URL kortEksempelUrl = new URL(String.valueOf(Constant.imageURL) + "eksempel_lamineret_falset_mini.jpg");
            this.c_kortEksempelLabel.setIcon(new ImageIcon(kortEksempelUrl));
            this.c_kortEksempelPane.removeAll();
            this.c_kortEksempelPane.add(this.c_kortEksempelLabel);
            this.c_kortEksempelPane.repaint();
            if (this.mapType.equals("egnskort")) {
                this.c_information.setText(this.myResource.getResource("format.opklaebning.info.egnskort.falset"));
            }
            else {
                this.c_information.setText(this.myResource.getResource("format.opklaebning.info.5"));
            }
        }
        catch (MalformedURLException URLe) {
            System.err.println("Exception in selectLamineretFalset: " + URLe.getMessage());
        }
    }
    
    public void actionPerformed(final ActionEvent e) {
        if ("plano".equals(e.getActionCommand())) {
            this.selectPlano();
        }
        else if ("falset".equals(e.getActionCommand())) {
            this.selectFalset();
        }
        else if ("indrammet".equals(e.getActionCommand())) {
            this.selectIndrammet();
        }
        else if ("lamineretplano".equals(e.getActionCommand())) {
            this.selectLamineretPlano();
        }
        else if ("lamineretfalset".equals(e.getActionCommand())) {
            this.selectLamineretFalset();
        }
        else if ("lukvindue".equals(e.getActionCommand())) {
            this.c_toolTipFrame.setVisible(false);
        }
    }
}
