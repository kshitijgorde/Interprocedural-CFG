// 
// Decompiled by Procyon v0.5.30
// 

package IntegroGeneral;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.beans.Beans;
import java.awt.Frame;
import java.awt.Adjustable;
import java.awt.Container;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.ScrollPane;
import java.awt.Button;
import java.awt.Color;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Label;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class IntegroImageWizard extends Applet implements ActionListener, MouseListener, MouseMotionListener, Runnable
{
    private ImageCanvas ivjCanvas1;
    private Label ivjLabel;
    private Panel ivjPanel1;
    protected int NumberOfImages;
    protected boolean Labels;
    public boolean Links;
    protected char Sequence;
    protected boolean Resize;
    protected String Trigger;
    protected int Time;
    protected String SpecialEffect;
    protected String BackgroundColor;
    protected int Width;
    protected int Height;
    protected String[] LinkText;
    protected String[] ImageText;
    public String[] LabelText;
    private int CurrentIndex;
    protected Thread aThread;
    int x;
    int y;
    public Image[] ImagesArray;
    public Image[] ScaledImage;
    protected Color BackgroundColorObject;
    protected int[] ImageWidth;
    protected int[] ImageHeight;
    private Image thePicture;
    private String[] EffectStrings;
    private int NumberOfEffect;
    private String CurrentEffect;
    private boolean Refresh;
    private Button ivjAboutButton;
    private Button ivjCancelButton;
    private Label ivjLabel1;
    private Button ivjOkButtonO;
    private Panel ivjPanel4;
    private ScrollPane ivjScrollPane1;
    private Choice ivjSpecialEffectChoice;
    private Label ivjLabel4;
    private Label ivjLabel5;
    private Label ivjLabel6;
    private Panel ivjPanel6;
    private ScrollPane ivjScrollPane2;
    private Button ivjButton1;
    private Label ivjLabel21;
    private Label ivjLabel31;
    private Label ivjLabel81;
    private Label ivjLabel91;
    private Label ivjLabel2;
    private Label ivjLabel3;
    public String ForegroundColor;
    public Color ForegroundColorObject;
    private Choice ivjForegroundChoice;
    private Label ivjLabel8;
    private Label ivjLabel9;
    private Choice ivjTriggerChoice;
    private Choice ivjBackgroundChoice;
    private boolean MouseIn;
    private boolean TrailEffect;
    private Button ivjHelpButton;
    private Label ivjLabel11;
    private Button ivjCancelHelp;
    private Label ivjLabel12;
    private Label ivjLabel13;
    private Label ivjLabel14;
    private Label ivjLabel15;
    private Label ivjLabel16;
    private Label ivjLabel17;
    private Label ivjLabel18;
    private Label ivjLabel19;
    private Label ivjLabel20;
    private Label ivjLabel22;
    private Label ivjLabel23;
    private Label ivjLabel24;
    private Label ivjLabel25;
    private Label ivjLabel26;
    private Label ivjLabel27;
    private Label ivjLabel28;
    private Label ivjLabel29;
    private Label ivjLabel30;
    private Label ivjLabel32;
    private Label ivjLabel33;
    private Label ivjLabel34;
    private Label ivjLabel35;
    private Label ivjLabel36;
    private Label ivjLabel37;
    private Label ivjLabel38;
    private Label ivjLabel39;
    private Label ivjLabel40;
    private Label ivjLabel41;
    private Label ivjLabel42;
    private Label ivjLabel43;
    private Label ivjLabel44;
    private Label ivjLabel45;
    private Label ivjLabel46;
    private Label ivjLabel47;
    private Label ivjLabel48;
    private Label ivjLabel49;
    private Label ivjLabel50;
    private Label ivjLabel51;
    private Label ivjLabel52;
    private Label ivjLabel53;
    private Label ivjLabel54;
    private Label ivjLabel55;
    private Label ivjLabel56;
    private Label ivjLabel57;
    private Label ivjLabel58;
    private Label ivjLabel59;
    private Label ivjLabel60;
    private Label ivjLabel61;
    private Label ivjLabel62;
    private Label ivjLabel63;
    private Label ivjLabel64;
    private Label ivjLabel65;
    private Panel ivjPanel2;
    private ScrollPane ivjScrollPane3;
    private IntegroCanvas ivjIntegroCanvas2;
    private Choice ivjTimeChoice;
    private int[] TimeArray;
    private String[] TimeStrings;
    private int loadedImages;
    private Choice ivjSequenceChoice;
    private Choice ivjDisplayLabelChoice;
    private Choice ivjResizeChoice;
    private Choice ivjTrailEffectChoice;
    private boolean loading;
    
    public IntegroImageWizard() {
        this.ivjCanvas1 = null;
        this.ivjLabel = null;
        this.ivjPanel1 = null;
        this.x = 0;
        this.y = 0;
        this.EffectStrings = new String[] { "North", "South", "East", "West", "None" };
        this.NumberOfEffect = 5;
        this.ivjAboutButton = null;
        this.ivjCancelButton = null;
        this.ivjLabel1 = null;
        this.ivjOkButtonO = null;
        this.ivjPanel4 = null;
        this.ivjScrollPane1 = null;
        this.ivjSpecialEffectChoice = null;
        this.ivjLabel4 = null;
        this.ivjLabel5 = null;
        this.ivjLabel6 = null;
        this.ivjPanel6 = null;
        this.ivjScrollPane2 = null;
        this.ivjButton1 = null;
        this.ivjLabel21 = null;
        this.ivjLabel31 = null;
        this.ivjLabel81 = null;
        this.ivjLabel91 = null;
        this.ivjLabel2 = null;
        this.ivjLabel3 = null;
        this.ivjForegroundChoice = null;
        this.ivjLabel8 = null;
        this.ivjLabel9 = null;
        this.ivjTriggerChoice = null;
        this.ivjBackgroundChoice = null;
        this.MouseIn = false;
        this.ivjHelpButton = null;
        this.ivjLabel11 = null;
        this.ivjCancelHelp = null;
        this.ivjLabel12 = null;
        this.ivjLabel13 = null;
        this.ivjLabel14 = null;
        this.ivjLabel15 = null;
        this.ivjLabel16 = null;
        this.ivjLabel17 = null;
        this.ivjLabel18 = null;
        this.ivjLabel19 = null;
        this.ivjLabel20 = null;
        this.ivjLabel22 = null;
        this.ivjLabel23 = null;
        this.ivjLabel24 = null;
        this.ivjLabel25 = null;
        this.ivjLabel26 = null;
        this.ivjLabel27 = null;
        this.ivjLabel28 = null;
        this.ivjLabel29 = null;
        this.ivjLabel30 = null;
        this.ivjLabel32 = null;
        this.ivjLabel33 = null;
        this.ivjLabel34 = null;
        this.ivjLabel35 = null;
        this.ivjLabel36 = null;
        this.ivjLabel37 = null;
        this.ivjLabel38 = null;
        this.ivjLabel39 = null;
        this.ivjLabel40 = null;
        this.ivjLabel41 = null;
        this.ivjLabel42 = null;
        this.ivjLabel43 = null;
        this.ivjLabel44 = null;
        this.ivjLabel45 = null;
        this.ivjLabel46 = null;
        this.ivjLabel47 = null;
        this.ivjLabel48 = null;
        this.ivjLabel49 = null;
        this.ivjLabel50 = null;
        this.ivjLabel51 = null;
        this.ivjLabel52 = null;
        this.ivjLabel53 = null;
        this.ivjLabel54 = null;
        this.ivjLabel55 = null;
        this.ivjLabel56 = null;
        this.ivjLabel57 = null;
        this.ivjLabel58 = null;
        this.ivjLabel59 = null;
        this.ivjLabel60 = null;
        this.ivjLabel61 = null;
        this.ivjLabel62 = null;
        this.ivjLabel63 = null;
        this.ivjLabel64 = null;
        this.ivjLabel65 = null;
        this.ivjPanel2 = null;
        this.ivjScrollPane3 = null;
        this.ivjIntegroCanvas2 = null;
        this.ivjTimeChoice = null;
        this.TimeArray = new int[] { 0, 500, 1000, 2000, 5000, 10000 };
        this.TimeStrings = new String[] { "None", "1/2 sec", " 1  sec", " 2  sec", " 5  sec", "10  sec" };
        this.loadedImages = -1;
        this.ivjSequenceChoice = null;
        this.ivjDisplayLabelChoice = null;
        this.ivjResizeChoice = null;
        this.ivjTrailEffectChoice = null;
        this.loading = true;
    }
    
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.getCancelButton()) {
            this.connEtoC2(e);
        }
        if (e.getSource() == this.getAboutButton()) {
            this.connEtoC3(e);
        }
        if (e.getSource() == this.getButton1()) {
            this.connEtoM1(e);
        }
        if (e.getSource() == this.getOkButtonO()) {
            this.connEtoC4(e);
        }
        if (e.getSource() == this.getCancelHelp()) {
            this.connEtoM2(e);
        }
        if (e.getSource() == this.getHelpButton()) {
            this.connEtoM3(e);
        }
    }
    
    private void connEtoC1() {
        try {
            this.integroImageWizard_InitChoice();
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void connEtoC2(final ActionEvent arg1) {
        try {
            this.goFirstCard();
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void connEtoC3(final ActionEvent arg1) {
        try {
            this.goNextCard();
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void connEtoC4(final ActionEvent arg1) {
        try {
            this.okButtonO_ActionPerformed(arg1);
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void connEtoM1(final ActionEvent arg1) {
        try {
            this.goOptionCard();
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void connEtoM2(final ActionEvent arg1) {
        try {
            this.goOptionCard();
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void connEtoM3(final ActionEvent arg1) {
        try {
            this.goLastCard();
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private Button getAboutButton() {
        if (this.ivjAboutButton == null) {
            try {
                (this.ivjAboutButton = new Button()).setName("AboutButton");
                this.ivjAboutButton.setBounds(211, 283, 54, 23);
                this.ivjAboutButton.setLabel("About...");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjAboutButton;
    }
    
    public String getAppletInfo() {
        return "IntegroGeneral.IntegroImageWizard created using VisualAge for Java.";
    }
    
    protected Thread getAThread() {
        return this.aThread;
    }
    
    private Choice getBackgroundChoice() {
        if (this.ivjBackgroundChoice == null) {
            try {
                (this.ivjBackgroundChoice = new Choice()).setName("BackgroundChoice");
                this.ivjBackgroundChoice.setBounds(140, 240, 90, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjBackgroundChoice;
    }
    
    public String getBackgroundColor() {
        return this.BackgroundColor;
    }
    
    public Color getBackgroundColorObject() {
        return this.BackgroundColorObject;
    }
    
    private Button getButton1() {
        if (this.ivjButton1 == null) {
            try {
                (this.ivjButton1 = new Button()).setName("Button1");
                this.ivjButton1.setBounds(174, 197, 56, 23);
                this.ivjButton1.setLabel("Cancel");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjButton1;
    }
    
    private Button getCancelButton() {
        if (this.ivjCancelButton == null) {
            try {
                (this.ivjCancelButton = new Button()).setName("CancelButton");
                this.ivjCancelButton.setBounds(27, 283, 53, 23);
                this.ivjCancelButton.setLabel("Cancel");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjCancelButton;
    }
    
    private Button getCancelHelp() {
        if (this.ivjCancelHelp == null) {
            try {
                (this.ivjCancelHelp = new Button()).setName("CancelHelp");
                this.ivjCancelHelp.setBounds(170, 1062, 56, 23);
                this.ivjCancelHelp.setLabel("Cancel");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjCancelHelp;
    }
    
    private ImageCanvas getCanvas1() {
        if (this.ivjCanvas1 == null) {
            try {
                (this.ivjCanvas1 = new ImageCanvas()).setName("Canvas1");
                this.ivjCanvas1.setBackground(Color.lightGray);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjCanvas1;
    }
    
    protected int getCurrentIndex() {
        return this.CurrentIndex;
    }
    
    private Choice getDisplayLabelChoice() {
        if (this.ivjDisplayLabelChoice == null) {
            try {
                (this.ivjDisplayLabelChoice = new Choice()).setName("DisplayLabelChoice");
                this.ivjDisplayLabelChoice.setBounds(140, 190, 50, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjDisplayLabelChoice;
    }
    
    private Choice getForegroundChoice() {
        if (this.ivjForegroundChoice == null) {
            try {
                (this.ivjForegroundChoice = new Choice()).setName("ForegroundChoice");
                this.ivjForegroundChoice.setBounds(140, 215, 90, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjForegroundChoice;
    }
    
    public String getForegroundColor() {
        return this.ForegroundColor;
    }
    
    public Color getForegroundColorObject() {
        return this.ForegroundColorObject;
    }
    
    public int getHeight() {
        return this.Height;
    }
    
    private Button getHelpButton() {
        if (this.ivjHelpButton == null) {
            try {
                (this.ivjHelpButton = new Button()).setName("HelpButton");
                this.ivjHelpButton.setBounds(142, 283, 56, 23);
                this.ivjHelpButton.setLabel("Help");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjHelpButton;
    }
    
    public Image[] getImagesArray() {
        return this.ImagesArray;
    }
    
    private IntegroCanvas getIntegroCanvas2() {
        if (this.ivjIntegroCanvas2 == null) {
            try {
                (this.ivjIntegroCanvas2 = new IntegroCanvas()).setName("IntegroCanvas2");
                this.ivjIntegroCanvas2.setFileName("IntegroLogosmall.gif");
                this.ivjIntegroCanvas2.setParentApplet(this);
                this.ivjIntegroCanvas2.setBounds(5, 6, 100, 30);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjIntegroCanvas2;
    }
    
    private Label getLabel() {
        if (this.ivjLabel == null) {
            try {
                (this.ivjLabel = new Label()).setName("Label");
                this.ivjLabel.setAlignment(1);
                this.ivjLabel.setFont(new Font("dialog", 1, 14));
                this.ivjLabel.setText("");
                this.ivjLabel.setBackground(Color.lightGray);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel;
    }
    
    private Label getLabel1() {
        if (this.ivjLabel1 == null) {
            try {
                (this.ivjLabel1 = new Label()).setName("Label1");
                this.ivjLabel1.setFont(new Font("dialog", 1, 14));
                this.ivjLabel1.setText("Applet Parameters");
                this.ivjLabel1.setBounds(141, 4, 170, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel1;
    }
    
    private Label getLabel11() {
        if (this.ivjLabel11 == null) {
            try {
                (this.ivjLabel11 = new Label()).setName("Label11");
                this.ivjLabel11.setText("Trail Effect:");
                this.ivjLabel11.setBounds(6, 90, 59, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel11;
    }
    
    private Label getLabel12() {
        if (this.ivjLabel12 == null) {
            try {
                (this.ivjLabel12 = new Label()).setName("Label12");
                this.ivjLabel12.setAlignment(1);
                this.ivjLabel12.setFont(new Font("dialog", 1, 14));
                this.ivjLabel12.setText("Help Information");
                this.ivjLabel12.setBounds(157, 11, 157, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel12;
    }
    
    private Label getLabel13() {
        if (this.ivjLabel13 == null) {
            try {
                (this.ivjLabel13 = new Label()).setName("Label13");
                this.ivjLabel13.setText("This applet has a number of parameters allowing various effects. Let's");
                this.ivjLabel13.setBounds(2, 65, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel13;
    }
    
    private Label getLabel14() {
        if (this.ivjLabel14 == null) {
            try {
                (this.ivjLabel14 = new Label()).setName("Label14");
                this.ivjLabel14.setText("review each parameter, its valid values and its description. Most parameters");
                this.ivjLabel14.setBounds(2, 90, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel14;
    }
    
    private Label getLabel15() {
        if (this.ivjLabel15 == null) {
            try {
                (this.ivjLabel15 = new Label()).setName("Label15");
                this.ivjLabel15.setText("can be defined in a param tag in the HTML applet tag, most of them are");
                this.ivjLabel15.setBounds(2, 115, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel15;
    }
    
    private Label getLabel16() {
        if (this.ivjLabel16 == null) {
            try {
                (this.ivjLabel16 = new Label()).setName("Label16");
                this.ivjLabel16.setText("optional and a default will be assigned if not defined in HTML. ");
                this.ivjLabel16.setBounds(2, 140, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel16;
    }
    
    private Label getLabel17() {
        if (this.ivjLabel17 == null) {
            try {
                (this.ivjLabel17 = new Label()).setName("Label17");
                this.ivjLabel17.setFont(new Font("dialog", 1, 12));
                this.ivjLabel17.setText("NumberOfImages");
                this.ivjLabel17.setBounds(2, 165, 130, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel17;
    }
    
    private Label getLabel18() {
        if (this.ivjLabel18 == null) {
            try {
                (this.ivjLabel18 = new Label()).setName("Label18");
                this.ivjLabel18.setText("Required parameter, has to be defined in HTML and.");
                this.ivjLabel18.setBounds(135, 165, 285, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel18;
    }
    
    private Label getLabel19() {
        if (this.ivjLabel19 == null) {
            try {
                (this.ivjLabel19 = new Label()).setName("Label19");
                this.ivjLabel19.setText("cannot be altered. This determines how many images will be displayed.");
                this.ivjLabel19.setBounds(2, 190, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel19;
    }
    
    private Label getLabel2() {
        if (this.ivjLabel2 == null) {
            try {
                (this.ivjLabel2 = new Label()).setName("Label2");
                this.ivjLabel2.setText("Image Change Trigger");
                this.ivjLabel2.setBounds(6, 165, 130, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel2;
    }
    
    private Label getLabel20() {
        if (this.ivjLabel20 == null) {
            try {
                (this.ivjLabel20 = new Label()).setName("Label20");
                this.ivjLabel20.setFont(new Font("dialog", 1, 12));
                this.ivjLabel20.setText("Image1,2,...");
                this.ivjLabel20.setBounds(2, 215, 130, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel20;
    }
    
    private Label getLabel21() {
        if (this.ivjLabel21 == null) {
            try {
                (this.ivjLabel21 = new Label()).setName("Label21");
                this.ivjLabel21.setText("Sequence: ");
                this.ivjLabel21.setBounds(7, 40, 100, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel21;
    }
    
    private Label getLabel22() {
        if (this.ivjLabel22 == null) {
            try {
                (this.ivjLabel22 = new Label()).setName("Label22");
                this.ivjLabel22.setText("Required parameter, has to be defined in HTML and.");
                this.ivjLabel22.setBounds(135, 215, 285, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel22;
    }
    
    private Label getLabel23() {
        if (this.ivjLabel23 == null) {
            try {
                (this.ivjLabel23 = new Label()).setName("Label23");
                this.ivjLabel23.setText("cannot be altered. Each Imagen parameter contains the URL or relative");
                this.ivjLabel23.setBounds(2, 240, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel23;
    }
    
    private Label getLabel24() {
        if (this.ivjLabel24 == null) {
            try {
                (this.ivjLabel24 = new Label()).setName("Label24");
                this.ivjLabel24.setText("address of the image.");
                this.ivjLabel24.setBounds(2, 265, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel24;
    }
    
    private Label getLabel25() {
        if (this.ivjLabel25 == null) {
            try {
                (this.ivjLabel25 = new Label()).setName("Label25");
                this.ivjLabel25.setFont(new Font("dialog", 1, 12));
                this.ivjLabel25.setText("Link1,2,...");
                this.ivjLabel25.setBounds(2, 290, 130, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel25;
    }
    
    private Label getLabel26() {
        if (this.ivjLabel26 == null) {
            try {
                (this.ivjLabel26 = new Label()).setName("Label26");
                this.ivjLabel26.setText("Required parameter, has to be defined in HTML and.");
                this.ivjLabel26.setBounds(135, 290, 285, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel26;
    }
    
    private Label getLabel27() {
        if (this.ivjLabel27 == null) {
            try {
                (this.ivjLabel27 = new Label()).setName("Label27");
                this.ivjLabel27.setText("cannot be altered. The link parameters work the same way as images.");
                this.ivjLabel27.setBounds(2, 315, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel27;
    }
    
    private Label getLabel28() {
        if (this.ivjLabel28 == null) {
            try {
                (this.ivjLabel28 = new Label()).setName("Label28");
                this.ivjLabel28.setFont(new Font("dialog", 1, 12));
                this.ivjLabel28.setText("Label1,2,...");
                this.ivjLabel28.setBounds(2, 340, 130, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel28;
    }
    
    private Label getLabel29() {
        if (this.ivjLabel29 == null) {
            try {
                (this.ivjLabel29 = new Label()).setName("Label29");
                this.ivjLabel29.setText("Required parameter, has to be defined in HTML and.");
                this.ivjLabel29.setBounds(135, 340, 285, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel29;
    }
    
    private Label getLabel3() {
        if (this.ivjLabel3 == null) {
            try {
                (this.ivjLabel3 = new Label()).setName("Label3");
                this.ivjLabel3.setText("Display Labels:");
                this.ivjLabel3.setBounds(6, 190, 130, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel3;
    }
    
    private Label getLabel30() {
        if (this.ivjLabel30 == null) {
            try {
                (this.ivjLabel30 = new Label()).setName("Label30");
                this.ivjLabel30.setText("cannot be altered. The label parameters work the same way as images.");
                this.ivjLabel30.setBounds(2, 365, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel30;
    }
    
    private Label getLabel31() {
        if (this.ivjLabel31 == null) {
            try {
                (this.ivjLabel31 = new Label()).setName("Label31");
                this.ivjLabel31.setText("Special Effect:");
                this.ivjLabel31.setBounds(7, 65, 100, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel31;
    }
    
    private Label getLabel32() {
        if (this.ivjLabel32 == null) {
            try {
                (this.ivjLabel32 = new Label()).setName("Label32");
                this.ivjLabel32.setFont(new Font("dialog", 1, 12));
                this.ivjLabel32.setText("LabelsYN");
                this.ivjLabel32.setBounds(2, 390, 130, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel32;
    }
    
    private Label getLabel33() {
        if (this.ivjLabel33 == null) {
            try {
                (this.ivjLabel33 = new Label()).setName("Label33");
                this.ivjLabel33.setText("indicates whether the labels are to be displayed ");
                this.ivjLabel33.setBounds(135, 390, 285, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel33;
    }
    
    private Label getLabel34() {
        if (this.ivjLabel34 == null) {
            try {
                (this.ivjLabel34 = new Label()).setName("Label34");
                this.ivjLabel34.setText("under the images. valid: (Yes,No) default [No]");
                this.ivjLabel34.setBounds(2, 415, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel34;
    }
    
    private Label getLabel35() {
        if (this.ivjLabel35 == null) {
            try {
                (this.ivjLabel35 = new Label()).setName("Label35");
                this.ivjLabel35.setFont(new Font("dialog", 1, 12));
                this.ivjLabel35.setText("LinksYN");
                this.ivjLabel35.setBounds(2, 440, 130, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel35;
    }
    
    private Label getLabel36() {
        if (this.ivjLabel36 == null) {
            try {
                (this.ivjLabel36 = new Label()).setName("Label36");
                this.ivjLabel36.setText("indicates whether the links are defined for each ");
                this.ivjLabel36.setBounds(135, 440, 285, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel36;
    }
    
    private Label getLabel37() {
        if (this.ivjLabel37 == null) {
            try {
                (this.ivjLabel37 = new Label()).setName("Label37");
                this.ivjLabel37.setText("image. If Yes, a mouse click on the image will cause to branch to the link.");
                this.ivjLabel37.setBounds(2, 465, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel37;
    }
    
    private Label getLabel38() {
        if (this.ivjLabel38 == null) {
            try {
                (this.ivjLabel38 = new Label()).setName("Label38");
                this.ivjLabel38.setText("(Yes, No) [No].");
                this.ivjLabel38.setBounds(2, 490, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel38;
    }
    
    private Label getLabel39() {
        if (this.ivjLabel39 == null) {
            try {
                (this.ivjLabel39 = new Label()).setName("Label39");
                this.ivjLabel39.setFont(new Font("dialog", 1, 12));
                this.ivjLabel39.setText("ResizeYN");
                this.ivjLabel39.setBounds(2, 515, 130, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel39;
    }
    
    private Label getLabel4() {
        if (this.ivjLabel4 == null) {
            try {
                (this.ivjLabel4 = new Label()).setName("Label4");
                this.ivjLabel4.setFont(new Font("dialog", 1, 14));
                this.ivjLabel4.setAlignment(1);
                this.ivjLabel4.setText("About the Author");
                this.ivjLabel4.setBackground(Color.lightGray);
                this.ivjLabel4.setBounds(129, 10, 167, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel4;
    }
    
    private Label getLabel40() {
        if (this.ivjLabel40 == null) {
            try {
                (this.ivjLabel40 = new Label()).setName("Label40");
                this.ivjLabel40.setText("indicates whether images will be resized to fill the ");
                this.ivjLabel40.setBounds(135, 515, 285, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel40;
    }
    
    private Label getLabel41() {
        if (this.ivjLabel41 == null) {
            try {
                (this.ivjLabel41 = new Label()).setName("Label41");
                this.ivjLabel41.setText("available space or keep their normal dimension. (Yes,No) [No]");
                this.ivjLabel41.setBounds(2, 540, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel41;
    }
    
    private Label getLabel42() {
        if (this.ivjLabel42 == null) {
            try {
                (this.ivjLabel42 = new Label()).setName("Label42");
                this.ivjLabel42.setFont(new Font("dialog", 1, 12));
                this.ivjLabel42.setText("TrailEffectYN");
                this.ivjLabel42.setBounds(2, 565, 130, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel42;
    }
    
    private Label getLabel43() {
        if (this.ivjLabel43 == null) {
            try {
                (this.ivjLabel43 = new Label()).setName("Label43");
                this.ivjLabel43.setText("this parameter when set cause the images to leave");
                this.ivjLabel43.setBounds(135, 565, 285, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel43;
    }
    
    private Label getLabel44() {
        if (this.ivjLabel44 == null) {
            try {
                (this.ivjLabel44 = new Label()).setName("Label44");
                this.ivjLabel44.setText("a trail as they move accross the screen. (Yes,No) [Yes]");
                this.ivjLabel44.setBounds(2, 590, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel44;
    }
    
    private Label getLabel45() {
        if (this.ivjLabel45 == null) {
            try {
                (this.ivjLabel45 = new Label()).setName("Label45");
                this.ivjLabel45.setFont(new Font("dialog", 1, 12));
                this.ivjLabel45.setText("Sequence");
                this.ivjLabel45.setBounds(2, 615, 130, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel45;
    }
    
    private Label getLabel46() {
        if (this.ivjLabel46 == null) {
            try {
                (this.ivjLabel46 = new Label()).setName("Label46");
                this.ivjLabel46.setText("this parameter determines the sequence of the");
                this.ivjLabel46.setBounds(135, 615, 285, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel46;
    }
    
    private Label getLabel47() {
        if (this.ivjLabel47 == null) {
            try {
                (this.ivjLabel47 = new Label()).setName("Label47");
                this.ivjLabel47.setText("images: Random: the sequence picks images randomly as opposed to ");
                this.ivjLabel47.setBounds(2, 640, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel47;
    }
    
    private Label getLabel48() {
        if (this.ivjLabel48 == null) {
            try {
                (this.ivjLabel48 = new Label()).setName("Label48");
                this.ivjLabel48.setText("sequentially. (Random, Sequentially) [Sequentially]");
                this.ivjLabel48.setBounds(2, 665, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel48;
    }
    
    private Label getLabel49() {
        if (this.ivjLabel49 == null) {
            try {
                (this.ivjLabel49 = new Label()).setName("Label49");
                this.ivjLabel49.setFont(new Font("dialog", 1, 12));
                this.ivjLabel49.setText("Trigger");
                this.ivjLabel49.setBounds(2, 690, 130, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel49;
    }
    
    private Label getLabel5() {
        if (this.ivjLabel5 == null) {
            try {
                (this.ivjLabel5 = new Label()).setName("Label5");
                this.ivjLabel5.setText("This applet is brought to you by Integro Data Systems, Inc. Visit us at");
                this.ivjLabel5.setBounds(7, 79, 411, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel5;
    }
    
    private Label getLabel50() {
        if (this.ivjLabel50 == null) {
            try {
                (this.ivjLabel50 = new Label()).setName("Label50");
                this.ivjLabel50.setText("this parameter determines what will trigger the ");
                this.ivjLabel50.setBounds(135, 690, 285, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel50;
    }
    
    private Label getLabel51() {
        if (this.ivjLabel51 == null) {
            try {
                (this.ivjLabel51 = new Label()).setName("Label51");
                this.ivjLabel51.setText("image to change: Time (image will be on for the defined time), MouseClick :");
                this.ivjLabel51.setBounds(2, 715, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel51;
    }
    
    private Label getLabel52() {
        if (this.ivjLabel52 == null) {
            try {
                (this.ivjLabel52 = new Label()).setName("Label52");
                this.ivjLabel52.setText("a mouse click on the image will bring up the next one, MouseEnter: as the");
                this.ivjLabel52.setBounds(2, 740, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel52;
    }
    
    private Label getLabel53() {
        if (this.ivjLabel53 == null) {
            try {
                (this.ivjLabel53 = new Label()).setName("Label53");
                this.ivjLabel53.setText("mouse enters the image will change if the mouse stays in the canvas, the ");
                this.ivjLabel53.setBounds(2, 765, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel53;
    }
    
    private Label getLabel54() {
        if (this.ivjLabel54 == null) {
            try {
                (this.ivjLabel54 = new Label()).setName("Label54");
                this.ivjLabel54.setText("time parameter will take over. (Time, MouseEnter, MouseClick) [Time]");
                this.ivjLabel54.setBounds(2, 790, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel54;
    }
    
    private Label getLabel55() {
        if (this.ivjLabel55 == null) {
            try {
                (this.ivjLabel55 = new Label()).setName("Label55");
                this.ivjLabel55.setFont(new Font("dialog", 1, 12));
                this.ivjLabel55.setText("Time");
                this.ivjLabel55.setBounds(2, 815, 130, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel55;
    }
    
    private Label getLabel56() {
        if (this.ivjLabel56 == null) {
            try {
                (this.ivjLabel56 = new Label()).setName("Label56");
                this.ivjLabel56.setText("this parameter determines in millisecond how long");
                this.ivjLabel56.setBounds(135, 815, 285, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel56;
    }
    
    private Label getLabel57() {
        if (this.ivjLabel57 == null) {
            try {
                (this.ivjLabel57 = new Label()).setName("Label57");
                this.ivjLabel57.setText("the image will be displayed on the surface. [2000]");
                this.ivjLabel57.setBounds(2, 840, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel57;
    }
    
    private Label getLabel58() {
        if (this.ivjLabel58 == null) {
            try {
                (this.ivjLabel58 = new Label()).setName("Label58");
                this.ivjLabel58.setFont(new Font("dialog", 1, 12));
                this.ivjLabel58.setText("SpecialEffect");
                this.ivjLabel58.setBounds(2, 865, 130, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel58;
    }
    
    private Label getLabel59() {
        if (this.ivjLabel59 == null) {
            try {
                (this.ivjLabel59 = new Label()).setName("Label59");
                this.ivjLabel59.setText("this parameter determines how the images are ");
                this.ivjLabel59.setBounds(135, 865, 285, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel59;
    }
    
    private Label getLabel6() {
        if (this.ivjLabel6 == null) {
            try {
                (this.ivjLabel6 = new Label()).setName("Label6");
                this.ivjLabel6.setText("www.integrods.com. This applet was written by Herve Devos.");
                this.ivjLabel6.setBounds(7, 110, 411, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel6;
    }
    
    private Label getLabel60() {
        if (this.ivjLabel60 == null) {
            try {
                (this.ivjLabel60 = new Label()).setName("Label60");
                this.ivjLabel60.setText("displayed on the canvas, the image can appear to come from the top (North),");
                this.ivjLabel60.setBounds(2, 890, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel60;
    }
    
    private Label getLabel61() {
        if (this.ivjLabel61 == null) {
            try {
                (this.ivjLabel61 = new Label()).setName("Label61");
                this.ivjLabel61.setText("from the left (West), ... or Random which will pick amongst the options. The");
                this.ivjLabel61.setBounds(2, 915, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel61;
    }
    
    private Label getLabel62() {
        if (this.ivjLabel62 == null) {
            try {
                (this.ivjLabel62 = new Label()).setName("Label62");
                this.ivjLabel62.setText("last option (None) will make the image appear centered in the middle of the");
                this.ivjLabel62.setBounds(2, 940, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel62;
    }
    
    private Label getLabel63() {
        if (this.ivjLabel63 == null) {
            try {
                (this.ivjLabel63 = new Label()).setName("Label63");
                this.ivjLabel63.setText("area. (North, South, East, West, Random, None) [Random]");
                this.ivjLabel63.setBounds(2, 965, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel63;
    }
    
    private Label getLabel64() {
        if (this.ivjLabel64 == null) {
            try {
                (this.ivjLabel64 = new Label()).setName("Label64");
                this.ivjLabel64.setFont(new Font("dialog", 1, 12));
                this.ivjLabel64.setText("ForegroundColor and BackgroundColor");
                this.ivjLabel64.setBounds(2, 990, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel64;
    }
    
    private Label getLabel65() {
        if (this.ivjLabel65 == null) {
            try {
                (this.ivjLabel65 = new Label()).setName("Label65");
                this.ivjLabel65.setText("sets background and foreground colors default [black, white].");
                this.ivjLabel65.setBounds(2, 1015, 420, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel65;
    }
    
    private Label getLabel8() {
        if (this.ivjLabel8 == null) {
            try {
                (this.ivjLabel8 = new Label()).setName("Label8");
                this.ivjLabel8.setText("Foreground Color:");
                this.ivjLabel8.setBounds(6, 215, 130, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel8;
    }
    
    private Label getLabel81() {
        if (this.ivjLabel81 == null) {
            try {
                (this.ivjLabel81 = new Label()).setName("Label81");
                this.ivjLabel81.setText("Resize Images:");
                this.ivjLabel81.setBounds(7, 115, 122, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel81;
    }
    
    private Label getLabel9() {
        if (this.ivjLabel9 == null) {
            try {
                (this.ivjLabel9 = new Label()).setName("Label9");
                this.ivjLabel9.setText("Background Color:");
                this.ivjLabel9.setBounds(6, 240, 130, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel9;
    }
    
    private Label getLabel91() {
        if (this.ivjLabel91 == null) {
            try {
                (this.ivjLabel91 = new Label()).setName("Label91");
                this.ivjLabel91.setText("Image Display Time:");
                this.ivjLabel91.setBounds(6, 140, 114, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel91;
    }
    
    public boolean getLabels() {
        return this.Labels;
    }
    
    public boolean getLinks() {
        return this.Links;
    }
    
    public void getNextImage() {
        if (this.getSequence() == 'R') {
            this.setCurrentIndex((int)Math.round(Math.random() * this.NumberOfImages));
            if (this.getCurrentIndex() == this.NumberOfImages) {
                this.setCurrentIndex(this.getCurrentIndex() - 1);
            }
        }
        else {
            this.setCurrentIndex(this.getCurrentIndex() + 1);
            if (this.getCurrentIndex() == this.getNumberOfImages()) {
                this.setCurrentIndex(0);
            }
        }
    }
    
    public int getNumberOfImages() {
        return this.NumberOfImages;
    }
    
    private Button getOkButtonO() {
        if (this.ivjOkButtonO == null) {
            try {
                (this.ivjOkButtonO = new Button()).setName("OkButtonO");
                this.ivjOkButtonO.setBounds(93, 283, 29, 23);
                this.ivjOkButtonO.setLabel("Ok");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjOkButtonO;
    }
    
    private Panel getPanel1() {
        if (this.ivjPanel1 == null) {
            try {
                (this.ivjPanel1 = new Panel()).setName("Panel1");
                this.ivjPanel1.setLayout(new BorderLayout());
                this.getPanel1().add(this.getCanvas1(), "Center");
                this.getPanel1().add(this.getLabel(), "South");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjPanel1;
    }
    
    private Panel getPanel2() {
        if (this.ivjPanel2 == null) {
            try {
                (this.ivjPanel2 = new Panel()).setName("Panel2");
                this.ivjPanel2.setLayout(null);
                this.ivjPanel2.setBounds(0, 0, 426, 1100);
                this.getPanel2().add(this.getLabel12(), this.getLabel12().getName());
                this.getPanel2().add(this.getLabel13(), this.getLabel13().getName());
                this.getPanel2().add(this.getLabel14(), this.getLabel14().getName());
                this.getPanel2().add(this.getLabel15(), this.getLabel15().getName());
                this.getPanel2().add(this.getLabel16(), this.getLabel16().getName());
                this.getPanel2().add(this.getLabel17(), this.getLabel17().getName());
                this.getPanel2().add(this.getLabel18(), this.getLabel18().getName());
                this.getPanel2().add(this.getLabel19(), this.getLabel19().getName());
                this.getPanel2().add(this.getLabel20(), this.getLabel20().getName());
                this.getPanel2().add(this.getLabel22(), this.getLabel22().getName());
                this.getPanel2().add(this.getLabel23(), this.getLabel23().getName());
                this.getPanel2().add(this.getLabel24(), this.getLabel24().getName());
                this.getPanel2().add(this.getLabel25(), this.getLabel25().getName());
                this.getPanel2().add(this.getLabel26(), this.getLabel26().getName());
                this.getPanel2().add(this.getLabel27(), this.getLabel27().getName());
                this.getPanel2().add(this.getLabel28(), this.getLabel28().getName());
                this.getPanel2().add(this.getLabel29(), this.getLabel29().getName());
                this.getPanel2().add(this.getLabel30(), this.getLabel30().getName());
                this.getPanel2().add(this.getLabel32(), this.getLabel32().getName());
                this.getPanel2().add(this.getLabel33(), this.getLabel33().getName());
                this.getPanel2().add(this.getLabel34(), this.getLabel34().getName());
                this.getPanel2().add(this.getLabel35(), this.getLabel35().getName());
                this.getPanel2().add(this.getLabel36(), this.getLabel36().getName());
                this.getPanel2().add(this.getLabel37(), this.getLabel37().getName());
                this.getPanel2().add(this.getLabel38(), this.getLabel38().getName());
                this.getPanel2().add(this.getLabel39(), this.getLabel39().getName());
                this.getPanel2().add(this.getLabel40(), this.getLabel40().getName());
                this.getPanel2().add(this.getLabel41(), this.getLabel41().getName());
                this.getPanel2().add(this.getLabel42(), this.getLabel42().getName());
                this.getPanel2().add(this.getLabel43(), this.getLabel43().getName());
                this.getPanel2().add(this.getLabel44(), this.getLabel44().getName());
                this.getPanel2().add(this.getLabel45(), this.getLabel45().getName());
                this.getPanel2().add(this.getLabel46(), this.getLabel46().getName());
                this.getPanel2().add(this.getLabel47(), this.getLabel47().getName());
                this.getPanel2().add(this.getLabel48(), this.getLabel48().getName());
                this.getPanel2().add(this.getLabel49(), this.getLabel49().getName());
                this.getPanel2().add(this.getLabel50(), this.getLabel50().getName());
                this.getPanel2().add(this.getLabel51(), this.getLabel51().getName());
                this.getPanel2().add(this.getLabel52(), this.getLabel52().getName());
                this.getPanel2().add(this.getLabel53(), this.getLabel53().getName());
                this.getPanel2().add(this.getLabel54(), this.getLabel54().getName());
                this.getPanel2().add(this.getLabel55(), this.getLabel55().getName());
                this.getPanel2().add(this.getLabel56(), this.getLabel56().getName());
                this.getPanel2().add(this.getLabel57(), this.getLabel57().getName());
                this.getPanel2().add(this.getLabel58(), this.getLabel58().getName());
                this.getPanel2().add(this.getLabel59(), this.getLabel59().getName());
                this.getPanel2().add(this.getLabel60(), this.getLabel60().getName());
                this.getPanel2().add(this.getLabel61(), this.getLabel61().getName());
                this.getPanel2().add(this.getLabel62(), this.getLabel62().getName());
                this.getPanel2().add(this.getLabel63(), this.getLabel63().getName());
                this.getPanel2().add(this.getLabel64(), this.getLabel64().getName());
                this.getPanel2().add(this.getLabel65(), this.getLabel65().getName());
                this.getPanel2().add(this.getCancelHelp(), this.getCancelHelp().getName());
                this.getPanel2().setBounds(0, 0, 426, 1100);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjPanel2;
    }
    
    private Panel getPanel4() {
        if (this.ivjPanel4 == null) {
            try {
                (this.ivjPanel4 = new Panel()).setName("Panel4");
                this.ivjPanel4.setLayout(null);
                this.ivjPanel4.setBounds(0, 0, 422, 450);
                this.getPanel4().add(this.getLabel1(), this.getLabel1().getName());
                this.getPanel4().add(this.getSpecialEffectChoice(), this.getSpecialEffectChoice().getName());
                this.getPanel4().add(this.getLabel21(), this.getLabel21().getName());
                this.getPanel4().add(this.getLabel31(), this.getLabel31().getName());
                this.getPanel4().add(this.getLabel81(), this.getLabel81().getName());
                this.getPanel4().add(this.getLabel91(), this.getLabel91().getName());
                this.getPanel4().add(this.getLabel2(), this.getLabel2().getName());
                this.getPanel4().add(this.getTriggerChoice(), this.getTriggerChoice().getName());
                this.getPanel4().add(this.getLabel3(), this.getLabel3().getName());
                this.getPanel4().add(this.getLabel8(), this.getLabel8().getName());
                this.getPanel4().add(this.getForegroundChoice(), this.getForegroundChoice().getName());
                this.getPanel4().add(this.getLabel9(), this.getLabel9().getName());
                this.getPanel4().add(this.getBackgroundChoice(), this.getBackgroundChoice().getName());
                this.getPanel4().add(this.getCancelButton(), this.getCancelButton().getName());
                this.getPanel4().add(this.getOkButtonO(), this.getOkButtonO().getName());
                this.getPanel4().add(this.getAboutButton(), this.getAboutButton().getName());
                this.getPanel4().add(this.getHelpButton(), this.getHelpButton().getName());
                this.getPanel4().add(this.getLabel11(), this.getLabel11().getName());
                this.getPanel4().add(this.getTimeChoice(), this.getTimeChoice().getName());
                this.getPanel4().add(this.getSequenceChoice(), this.getSequenceChoice().getName());
                this.getPanel4().add(this.getTrailEffectChoice(), this.getTrailEffectChoice().getName());
                this.getPanel4().add(this.getResizeChoice(), this.getResizeChoice().getName());
                this.getPanel4().add(this.getDisplayLabelChoice(), this.getDisplayLabelChoice().getName());
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjPanel4;
    }
    
    private Panel getPanel6() {
        if (this.ivjPanel6 == null) {
            try {
                (this.ivjPanel6 = new Panel()).setName("Panel6");
                this.ivjPanel6.setLayout(null);
                this.ivjPanel6.setBackground(Color.white);
                this.ivjPanel6.setBounds(0, 0, 423, 270);
                this.getPanel6().add(this.getLabel4(), this.getLabel4().getName());
                this.getPanel6().add(this.getLabel5(), this.getLabel5().getName());
                this.getPanel6().add(this.getLabel6(), this.getLabel6().getName());
                this.getPanel6().add(this.getButton1(), this.getButton1().getName());
                this.getPanel6().add(this.getIntegroCanvas2(), this.getIntegroCanvas2().getName());
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjPanel6;
    }
    
    public boolean getResize() {
        return this.Resize;
    }
    
    private Choice getResizeChoice() {
        if (this.ivjResizeChoice == null) {
            try {
                (this.ivjResizeChoice = new Choice()).setName("ResizeChoice");
                this.ivjResizeChoice.setBounds(140, 115, 50, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjResizeChoice;
    }
    
    private ScrollPane getScrollPane1() {
        if (this.ivjScrollPane1 == null) {
            try {
                (this.ivjScrollPane1 = new ScrollPane(0)).setName("ScrollPane1");
                this.ivjScrollPane1.setBackground(Color.lightGray);
                this.getScrollPane1().add(this.getPanel4(), this.getPanel4().getName());
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjScrollPane1;
    }
    
    private ScrollPane getScrollPane2() {
        if (this.ivjScrollPane2 == null) {
            try {
                (this.ivjScrollPane2 = new ScrollPane()).setName("ScrollPane2");
                this.getScrollPane2().add(this.getPanel6(), this.getPanel6().getName());
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjScrollPane2;
    }
    
    private ScrollPane getScrollPane3() {
        if (this.ivjScrollPane3 == null) {
            try {
                (this.ivjScrollPane3 = new ScrollPane()).setName("ScrollPane3");
                this.getScrollPane3().add(this.getPanel2(), this.getPanel2().getName());
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjScrollPane3;
    }
    
    public char getSequence() {
        return this.Sequence;
    }
    
    private Choice getSequenceChoice() {
        if (this.ivjSequenceChoice == null) {
            try {
                (this.ivjSequenceChoice = new Choice()).setName("SequenceChoice");
                this.ivjSequenceChoice.setBounds(140, 40, 140, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjSequenceChoice;
    }
    
    public String getSpecialEffect() {
        return this.SpecialEffect;
    }
    
    private Choice getSpecialEffectChoice() {
        if (this.ivjSpecialEffectChoice == null) {
            try {
                (this.ivjSpecialEffectChoice = new Choice()).setName("SpecialEffectChoice");
                this.ivjSpecialEffectChoice.setBounds(140, 65, 140, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjSpecialEffectChoice;
    }
    
    public int getTime() {
        return this.Time;
    }
    
    private Choice getTimeChoice() {
        if (this.ivjTimeChoice == null) {
            try {
                (this.ivjTimeChoice = new Choice()).setName("TimeChoice");
                this.ivjTimeChoice.setBounds(141, 138, 77, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjTimeChoice;
    }
    
    public boolean getTrailEffect() {
        return this.TrailEffect;
    }
    
    private Choice getTrailEffectChoice() {
        if (this.ivjTrailEffectChoice == null) {
            try {
                (this.ivjTrailEffectChoice = new Choice()).setName("TrailEffectChoice");
                this.ivjTrailEffectChoice.setBounds(140, 90, 50, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjTrailEffectChoice;
    }
    
    public String getTrigger() {
        return this.Trigger;
    }
    
    private Choice getTriggerChoice() {
        if (this.ivjTriggerChoice == null) {
            try {
                (this.ivjTriggerChoice = new Choice()).setName("TriggerChoice");
                this.ivjTriggerChoice.setBounds(140, 165, 140, 23);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjTriggerChoice;
    }
    
    public int getWidth() {
        return this.Width;
    }
    
    public void goFirstCard() {
        ((CardLayout)this.getLayout()).first(this);
    }
    
    public void goLastCard() {
        ((CardLayout)this.getLayout()).last(this);
    }
    
    public void goNextCard() {
        ((CardLayout)this.getLayout()).next(this);
    }
    
    public void goOptionCard() {
        ((CardLayout)this.getLayout()).show(this, this.getScrollPane1().getName());
    }
    
    public void goPreviousCard() {
        ((CardLayout)this.getLayout()).previous(this);
    }
    
    private void handleException(final Throwable exception) {
    }
    
    public void init() {
        super.init();
        try {
            this.setName("IntegroImageWizard");
            this.setLayout(new CardLayout());
            this.setBackground(Color.cyan);
            this.setSize(426, 240);
            this.add(this.getPanel1(), this.getPanel1().getName());
            this.add(this.getScrollPane1(), this.getScrollPane1().getName());
            this.add(this.getScrollPane2(), this.getScrollPane2().getName());
            this.add(this.getScrollPane3(), this.getScrollPane3().getName());
            this.initConnections();
            this.connEtoC1();
            this.setWidth(Integer.parseInt(this.getParameter("Width")));
            this.setHeight(Integer.parseInt(this.getParameter("Height")));
            this.getCanvas1().getGraphics().drawString("Applet initializing, please wait a few seconds....", 0, this.getHeight() / 2 + 20);
            try {
                final String s = this.getParameter("LabelsYN");
                final char c = Character.toUpperCase(s.charAt(0));
                if (c == 'Y') {
                    this.setLabels(true);
                    this.getDisplayLabelChoice().select("Yes");
                }
                else {
                    this.setLabels(false);
                    this.getDisplayLabelChoice().select("No");
                }
            }
            catch (Exception ex) {
                this.setLabels(false);
                this.getDisplayLabelChoice().select("No");
            }
            try {
                final String s = this.getParameter("Sequence");
                final char c = Character.toUpperCase(s.charAt(0));
                if (c == 'R') {
                    this.setSequence('R');
                    this.getSequenceChoice().select("Random");
                }
                else {
                    this.setSequence('S');
                    this.getSequenceChoice().select("Sequential");
                }
            }
            catch (Exception ex2) {
                this.setSequence('R');
                this.getSequenceChoice().select("Random");
            }
            try {
                this.NumberOfImages = Integer.parseInt(this.getParameter("NumberOfImages"));
            }
            catch (Exception ex3) {
                this.getLabel().setText("Please review your parameters in particular the required NumberOfImages");
                this.setNumberOfImages(1);
            }
            this.ImagesArray = new Image[this.NumberOfImages];
            this.ScaledImage = new Image[this.NumberOfImages];
            this.LinkText = new String[this.NumberOfImages];
            this.ImageText = new String[this.NumberOfImages];
            this.LabelText = new String[this.NumberOfImages];
            this.ImageWidth = new int[this.NumberOfImages];
            this.ImageHeight = new int[this.NumberOfImages];
            try {
                final String s = this.getParameter("LinksYN");
                final char c = Character.toUpperCase(s.charAt(0));
                if (c == 'Y') {
                    this.setLinks(true);
                }
                else {
                    this.setLinks(false);
                }
            }
            catch (Exception ex4) {
                this.setLinks(false);
            }
            try {
                final String s = this.getParameter("ResizeYN");
                final char c = Character.toUpperCase(s.charAt(0));
                if (c == 'Y') {
                    this.setResize(true);
                    this.getResizeChoice().select("Yes");
                }
                else {
                    this.setResize(false);
                    this.getResizeChoice().select("No");
                }
            }
            catch (Exception ex5) {
                this.setResize(false);
                this.getResizeChoice().select("No");
            }
            try {
                final String s = this.getParameter("TrailEffectYN");
                final char c = Character.toUpperCase(s.charAt(0));
                if (c == 'Y') {
                    this.setTrailEffect(true);
                    this.getTrailEffectChoice().select("Yes");
                }
                else {
                    this.setTrailEffect(false);
                    this.getTrailEffectChoice().select("No");
                }
            }
            catch (Exception ex6) {
                this.setTrailEffect(true);
                this.getTrailEffectChoice().select("Yes");
            }
            if (!this.getLabels()) {
                this.getLabel().setVisible(false);
            }
            this.resize(this.getWidth(), this.getHeight());
            for (int i = 0; i < this.getNumberOfImages(); ++i) {
                this.ImageText[i] = this.getParameter("Image" + (i + 1));
                this.LabelText[i] = this.getParameter("Label" + (i + 1));
                if (this.getLinks()) {
                    this.LinkText[i] = this.getParameter("Link" + (i + 1));
                }
            }
            this.loadImages();
            try {
                this.setBackgroundColor(this.getParameter("BackgroundColor"));
            }
            catch (Exception ex7) {
                this.setBackgroundColor("white");
            }
            this.getBackgroundChoice().select(this.getBackgroundColor());
            this.setBackgroundColorObject(ColorWorks.getColorObject(this.getBackgroundColor()));
            try {
                this.setForegroundColor(this.getParameter("ForegroundColor"));
            }
            catch (Exception ex8) {
                this.setForegroundColor("black");
            }
            this.getForegroundChoice().select(this.getForegroundColor());
            this.setForegroundColorObject(ColorWorks.getColorObject(this.getForegroundColor()));
            try {
                this.setTrigger(this.getParameter("Trigger"));
                if (this.getTrigger() != "Time" && this.getTrigger() != "MouseClick" && this.getTrigger() != "MouseEnter") {
                    this.setTrigger("Time");
                }
            }
            catch (Exception e) {
                e.printStackTrace(System.out);
                this.setTrigger("Time");
            }
            this.getTriggerChoice().select(this.getTrigger());
            int index = 0;
            try {
                index = Integer.parseInt(this.getParameter("Time"));
                this.setTime(this.TimeArray[index]);
            }
            catch (Exception e2) {
                this.setTime(1000);
                index = 2;
                e2.printStackTrace(System.out);
            }
            this.getTimeChoice().select(index);
            try {
                this.setSpecialEffect(this.getParameter("SpecialEffect"));
            }
            catch (Exception e2) {
                System.out.println("Problem with SpecialEffect parameter");
                e2.printStackTrace(System.out);
                this.setSpecialEffect("Random");
            }
            this.getSpecialEffectChoice().select(this.getSpecialEffect());
            ((CardLayout)this.getLayout()).first(this);
            this.getCanvas1().addMouseMotionListener(this);
            this.getCanvas1().addMouseListener(this);
            this.getLabel().addMouseMotionListener(this);
            this.getLabel().addMouseListener(this);
            this.showStatus("Click on the bottom left symbol to view options..");
            (this.aThread = new Thread(this)).start();
            Adjustable vadj = this.getScrollPane1().getVAdjustable();
            Adjustable hadj = this.getScrollPane1().getHAdjustable();
            vadj.setUnitIncrement(25);
            hadj.setUnitIncrement(25);
            vadj = this.getScrollPane2().getVAdjustable();
            hadj = this.getScrollPane2().getHAdjustable();
            vadj.setUnitIncrement(25);
            hadj.setUnitIncrement(25);
            vadj = this.getScrollPane3().getVAdjustable();
            hadj = this.getScrollPane3().getHAdjustable();
            vadj.setUnitIncrement(25);
            hadj.setUnitIncrement(25);
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void initConnections() {
        this.getCancelButton().addActionListener(this);
        this.getAboutButton().addActionListener(this);
        this.getButton1().addActionListener(this);
        this.getOkButtonO().addActionListener(this);
        this.getCancelHelp().addActionListener(this);
        this.getHelpButton().addActionListener(this);
    }
    
    public void integroImageWizard_InitChoice() {
        this.getSpecialEffectChoice().addItem("Random");
        this.getSpecialEffectChoice().addItem("North");
        this.getSpecialEffectChoice().addItem("South");
        this.getSpecialEffectChoice().addItem("West");
        this.getSpecialEffectChoice().addItem("East");
        this.getSpecialEffectChoice().addItem("None");
        this.getBackgroundChoice().addItem("black");
        this.getBackgroundChoice().addItem("cyan");
        this.getBackgroundChoice().addItem("darkGray");
        this.getBackgroundChoice().addItem("gray");
        this.getBackgroundChoice().addItem("green");
        this.getBackgroundChoice().addItem("lightGray");
        this.getBackgroundChoice().addItem("magenta");
        this.getBackgroundChoice().addItem("orange");
        this.getBackgroundChoice().addItem("pink");
        this.getBackgroundChoice().addItem("red");
        this.getBackgroundChoice().addItem("white");
        this.getBackgroundChoice().addItem("yellow");
        this.getForegroundChoice().addItem("black");
        this.getForegroundChoice().addItem("cyan");
        this.getForegroundChoice().addItem("darkGray");
        this.getForegroundChoice().addItem("gray");
        this.getForegroundChoice().addItem("green");
        this.getForegroundChoice().addItem("lightGray");
        this.getForegroundChoice().addItem("magenta");
        this.getForegroundChoice().addItem("orange");
        this.getForegroundChoice().addItem("pink");
        this.getForegroundChoice().addItem("red");
        this.getForegroundChoice().addItem("white");
        this.getForegroundChoice().addItem("yellow");
        this.getTriggerChoice().addItem("MouseEnter");
        this.getTriggerChoice().addItem("MouseClick");
        this.getTriggerChoice().addItem("Time");
        this.getSequenceChoice().addItem("Random");
        this.getSequenceChoice().addItem("Sequential");
        this.getTrailEffectChoice().addItem("Yes");
        this.getTrailEffectChoice().addItem("No");
        this.getResizeChoice().addItem("Yes");
        this.getResizeChoice().addItem("No");
        this.getDisplayLabelChoice().addItem("Yes");
        this.getDisplayLabelChoice().addItem("No");
        for (int i = 0; i < 6; ++i) {
            this.getTimeChoice().addItem(this.TimeStrings[i]);
        }
    }
    
    public void loadImages() {
        for (int i = 0; i < this.NumberOfImages; ++i) {
            try {
                this.ImagesArray[i] = this.getImage(this.getCodeBase(), this.ImageText[i]);
                if (this.ImagesArray[i] == null) {
                    this.ImagesArray[i] = this.getImage(this.getCodeBase(), this.ImageText[i]);
                }
            }
            catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }
    
    public static void main(final String[] args) {
        try {
            Frame frame;
            try {
                final Class aFrameClass = Class.forName("com.ibm.uvm.abt.edit.TestFrame");
                frame = aFrameClass.newInstance();
            }
            catch (Throwable t) {
                frame = new Frame();
            }
            final Class iiCls = Class.forName("IntegroGeneral.IntegroImageWizard");
            final ClassLoader iiClsLoader = iiCls.getClassLoader();
            final IntegroImageWizard aIntegroImageWizard = (IntegroImageWizard)Beans.instantiate(iiClsLoader, "IntegroGeneral.IntegroImageWizard");
            frame.add("Center", aIntegroImageWizard);
            frame.setSize(aIntegroImageWizard.getSize());
            frame.setVisible(true);
        }
        catch (Throwable exception) {
            System.err.println("Exception occurred in main() of java.applet.Applet");
            exception.printStackTrace(System.out);
        }
    }
    
    public void mouseClicked(final MouseEvent e) {
        String link = null;
        if (e.getSource() == this.getCanvas1()) {
            if (e.getX() <= 10 && e.getY() >= this.getCanvas1().getSize().height - 10) {
                this.goNextCard();
            }
            else {
                if (this.getLinks()) {
                    try {
                        link = this.LinkText[this.CurrentIndex];
                        this.getAppletContext().showDocument(new URL(this.LinkText[this.CurrentIndex]));
                    }
                    catch (Exception exc) {
                        System.out.println("Error connecting to :" + link + " check the validity of the URL");
                        exc.printStackTrace(System.out);
                    }
                }
                if (this.getTrigger().equalsIgnoreCase("MouseClick")) {
                    this.getAThread().resume();
                }
            }
        }
        if (e.getSource() == this.getLabel() && this.getLinks()) {
            try {
                link = this.LinkText[this.CurrentIndex];
                this.getAppletContext().showDocument(new URL(this.LinkText[this.CurrentIndex]));
            }
            catch (Exception exc) {
                System.out.println("Error connecting to :" + link + " check the validity of the URL");
                exc.printStackTrace(System.out);
            }
        }
    }
    
    public void mouseDragged(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
        if (e.getSource() == this.getCanvas1() && this.getTrigger().equalsIgnoreCase("MouseEnter")) {
            this.getAThread().resume();
            this.MouseIn = true;
        }
    }
    
    public void mouseExited(final MouseEvent e) {
        if (e.getSource() == this.getCanvas1() && this.getTrigger().equalsIgnoreCase("MouseEnter")) {
            this.MouseIn = false;
        }
    }
    
    public void mouseMoved(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
    
    public void okButtonO_ActionPerformed(final ActionEvent actionEvent) {
        if (this.getSequenceChoice().getSelectedItem().equals("Random")) {
            this.setSequence('R');
        }
        else {
            this.setSequence('S');
        }
        this.setSpecialEffect(this.getSpecialEffectChoice().getSelectedItem());
        if (this.getResizeChoice().getSelectedItem().equals("Yes")) {
            this.setResize(true);
        }
        else {
            this.setResize(false);
        }
        if (this.getTrailEffectChoice().getSelectedItem().equals("Yes")) {
            this.setTrailEffect(true);
        }
        else {
            this.setTrailEffect(false);
        }
        try {
            this.setTime(this.TimeArray[this.getTimeChoice().getSelectedIndex()]);
        }
        catch (Exception ex) {
            this.setTime(2000);
        }
        if (this.getDisplayLabelChoice().getSelectedItem().equals("Yes")) {
            this.setLabels(true);
        }
        else {
            this.setLabels(false);
        }
        this.setTrigger(this.getTriggerChoice().getSelectedItem());
        this.setForegroundColorObject(ColorWorks.getColorObject(this.getForegroundChoice().getSelectedItem()));
        this.setBackgroundColorObject(ColorWorks.getColorObject(this.getBackgroundChoice().getSelectedItem()));
        this.goFirstCard();
    }
    
    public void paint(final Graphics g) {
        if (this.getLabels() && this.CurrentIndex != -1 && !this.loading) {
            this.getLabel().setText(this.LabelText[this.CurrentIndex]);
        }
        if (this.thePicture != null) {
            this.getCanvas1().setImage(this.thePicture);
            this.getCanvas1().setX(this.x);
            this.getCanvas1().setY(this.y);
            this.getCanvas1().setRefresh(this.Refresh);
            this.getCanvas1().repaint();
        }
    }
    
    public void run() {
        for (int i = 0; i < this.getNumberOfImages(); ++i) {
            String str = "Currently loading the images ...";
            this.ImageWidth[i] = -1;
            this.ImageHeight[i] = -1;
            this.getLabel().setAlignment(0);
            while (this.ImageWidth[i] == -1 || this.ImageHeight[i] == -1) {
                try {
                    this.ImageWidth[i] = this.ImagesArray[i].getWidth(this);
                    this.ImageHeight[i] = this.ImagesArray[i].getHeight(this);
                    if (this.ImageWidth[i] != -1 && this.ImageHeight[i] != -1) {
                        continue;
                    }
                    this.getLabel().setText(str);
                    str = String.valueOf(str) + ".";
                    Thread.sleep(200L);
                }
                catch (Exception ex) {
                    System.out.println("ImagesArray is null I guess:" + i);
                }
            }
        }
        for (int i = 0; i < this.getNumberOfImages(); ++i) {
            this.ScaledImage[i] = this.ImagesArray[i].getScaledInstance(this.getCanvas1().getSize().width, this.getCanvas1().getSize().height, 1);
        }
        this.getLabel().setAlignment(1);
        this.loading = false;
        this.setCurrentIndex(-1);
        while (true) {
            this.getNextImage();
            if (this.getTrailEffect()) {
                this.Refresh = false;
            }
            else {
                this.Refresh = true;
            }
            this.thePicture = this.ImagesArray[this.CurrentIndex];
            if (this.getResize()) {
                this.thePicture = this.ScaledImage[this.CurrentIndex];
                this.x = 0;
                this.y = 0;
            }
            else {
                this.x = this.getCanvas1().getSize().width / 2 - this.ImageWidth[this.CurrentIndex] / 2;
                this.y = this.getCanvas1().getSize().height / 2 - this.ImageHeight[this.CurrentIndex] / 2;
            }
            this.CurrentEffect = this.getSpecialEffect();
            if (this.CurrentEffect.equalsIgnoreCase("Random")) {
                this.CurrentEffect = this.EffectStrings[(int)((Math.random() - 1.0E-5) * this.NumberOfEffect)];
            }
            int fromX = 0;
            int fromY = 0;
            final int toX = this.x;
            final int toY = this.y;
            if (this.CurrentEffect.equalsIgnoreCase("North")) {
                if (this.getResize()) {
                    fromX = 0;
                    fromY = -this.getCanvas1().getSize().height;
                }
                else {
                    fromX = this.getCanvas1().getSize().width / 2 - this.ImageWidth[this.CurrentIndex] / 2;
                    fromY = -this.ImageHeight[this.CurrentIndex];
                }
                for (int j = fromY; j <= toY; j += 5) {
                    this.y = j;
                    try {
                        Thread.sleep(20L);
                    }
                    catch (Exception ex2) {}
                    this.repaint();
                }
            }
            else if (this.CurrentEffect.equalsIgnoreCase("South")) {
                if (this.getResize()) {
                    fromX = 0;
                    fromY = this.getCanvas1().getSize().height;
                }
                else {
                    fromX = this.getCanvas1().getSize().width / 2 - this.ImageWidth[this.CurrentIndex] / 2;
                    fromY = this.getCanvas1().getSize().height;
                }
                for (int j = fromY; j >= toY; j -= 5) {
                    this.y = j;
                    try {
                        Thread.sleep(20L);
                    }
                    catch (Exception ex3) {}
                    this.repaint();
                }
            }
            else if (this.CurrentEffect.equalsIgnoreCase("West")) {
                if (this.getResize()) {
                    fromX = -this.getCanvas1().getSize().width;
                    fromY = 0;
                }
                else {
                    fromX = -this.ImageWidth[this.CurrentIndex];
                    fromY = this.getCanvas1().getSize().height / 2 - this.ImageHeight[this.CurrentIndex] / 2;
                }
                for (int j = fromX; j <= toX; j += 5) {
                    this.x = j;
                    try {
                        Thread.sleep(20L);
                    }
                    catch (Exception ex4) {}
                    this.repaint();
                }
            }
            else if (this.CurrentEffect.equalsIgnoreCase("East")) {
                if (this.getResize()) {
                    fromX = this.getCanvas1().getSize().width;
                    fromY = 0;
                }
                else {
                    fromX = this.getCanvas1().getSize().width;
                    fromY = this.getCanvas1().getSize().height / 2 - this.ImageHeight[this.CurrentIndex] / 2;
                }
                for (int j = fromX; j >= toX; j -= 5) {
                    this.x = j;
                    try {
                        Thread.sleep(20L);
                    }
                    catch (Exception ex5) {}
                    this.repaint();
                }
            }
            this.x = toX;
            this.y = toY;
            this.Refresh = true;
            this.repaint();
            try {
                if (this.getTrigger().equalsIgnoreCase("Time") || (this.getTrigger().equalsIgnoreCase("MouseEnter") && this.MouseIn)) {
                    Thread.sleep(this.getTime());
                }
                else {
                    this.aThread.suspend();
                }
            }
            catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }
    
    protected void setAThread(final Thread newValue) {
        this.aThread = newValue;
    }
    
    public void setBackgroundColor(final String newValue) {
        this.BackgroundColor = newValue;
    }
    
    public void setBackgroundColorObject(final Color newValue) {
        this.BackgroundColorObject = newValue;
        this.getLabel().setBackground(newValue);
        this.getPanel4().setBackground(newValue);
        this.getPanel2().setBackground(newValue);
        this.getPanel6().setBackground(newValue);
        this.getPanel1().setBackground(newValue);
        this.getLabel4().setBackground(newValue);
        this.getLabel5().setBackground(newValue);
        this.getLabel6().setBackground(newValue);
        this.getLabel1().setBackground(newValue);
        this.getLabel11().setBackground(newValue);
        this.getLabel20().setBackground(newValue);
        this.getLabel21().setBackground(newValue);
        this.getLabel31().setBackground(newValue);
        this.getLabel81().setBackground(newValue);
        this.getLabel91().setBackground(newValue);
        this.getLabel2().setBackground(newValue);
        this.getLabel3().setBackground(newValue);
        this.getLabel8().setBackground(newValue);
        this.getLabel9().setBackground(newValue);
        this.getCanvas1().setBackground(newValue);
        this.getLabel12().setBackground(newValue);
        this.getLabel13().setBackground(newValue);
        this.getLabel14().setBackground(newValue);
        this.getLabel15().setBackground(newValue);
        this.getLabel16().setBackground(newValue);
        this.getLabel17().setBackground(newValue);
        this.getLabel18().setBackground(newValue);
        this.getLabel19().setBackground(newValue);
        this.getLabel22().setBackground(newValue);
        this.getLabel23().setBackground(newValue);
        this.getLabel24().setBackground(newValue);
        this.getLabel25().setBackground(newValue);
        this.getLabel26().setBackground(newValue);
        this.getLabel27().setBackground(newValue);
        this.getLabel28().setBackground(newValue);
        this.getLabel29().setBackground(newValue);
        this.getLabel30().setBackground(newValue);
        this.getLabel32().setBackground(newValue);
        this.getLabel33().setBackground(newValue);
        this.getLabel34().setBackground(newValue);
        this.getLabel35().setBackground(newValue);
        this.getLabel36().setBackground(newValue);
        this.getLabel37().setBackground(newValue);
        this.getLabel38().setBackground(newValue);
        this.getLabel39().setBackground(newValue);
        this.getLabel40().setBackground(newValue);
        this.getLabel41().setBackground(newValue);
        this.getLabel42().setBackground(newValue);
        this.getLabel43().setBackground(newValue);
        this.getLabel44().setBackground(newValue);
        this.getLabel45().setBackground(newValue);
        this.getLabel46().setBackground(newValue);
        this.getLabel47().setBackground(newValue);
        this.getLabel48().setBackground(newValue);
        this.getLabel49().setBackground(newValue);
        this.getLabel50().setBackground(newValue);
        this.getLabel51().setBackground(newValue);
        this.getLabel52().setBackground(newValue);
        this.getLabel53().setBackground(newValue);
        this.getLabel54().setBackground(newValue);
        this.getLabel55().setBackground(newValue);
        this.getLabel56().setBackground(newValue);
        this.getLabel57().setBackground(newValue);
        this.getLabel58().setBackground(newValue);
        this.getLabel59().setBackground(newValue);
        this.getLabel60().setBackground(newValue);
        this.getLabel61().setBackground(newValue);
        this.getLabel62().setBackground(newValue);
        this.getLabel63().setBackground(newValue);
        this.getLabel64().setBackground(newValue);
        this.getLabel65().setBackground(newValue);
    }
    
    protected void setCurrentIndex(final int newValue) {
        this.CurrentIndex = newValue;
    }
    
    public void setForegroundColor(final String newValue) {
        this.ForegroundColor = newValue;
    }
    
    public void setForegroundColorObject(final Color newValue) {
        this.ForegroundColorObject = newValue;
        this.getLabel().setForeground(newValue);
        this.getPanel2().setForeground(newValue);
        this.getPanel4().setForeground(newValue);
        this.getPanel6().setForeground(newValue);
        this.getPanel1().setForeground(newValue);
        this.getLabel4().setForeground(newValue);
        this.getLabel5().setForeground(newValue);
        this.getLabel6().setForeground(newValue);
        this.getLabel1().setForeground(newValue);
        this.getLabel11().setForeground(newValue);
        this.getLabel21().setForeground(newValue);
        this.getLabel20().setForeground(newValue);
        this.getLabel31().setForeground(newValue);
        this.getLabel81().setForeground(newValue);
        this.getLabel91().setForeground(newValue);
        this.getLabel2().setForeground(newValue);
        this.getLabel3().setForeground(newValue);
        this.getLabel8().setForeground(newValue);
        this.getLabel9().setForeground(newValue);
        this.getCanvas1().setForeground(newValue);
        this.getLabel12().setForeground(newValue);
        this.getLabel13().setForeground(newValue);
        this.getLabel14().setForeground(newValue);
        this.getLabel15().setForeground(newValue);
        this.getLabel16().setForeground(newValue);
        this.getLabel17().setForeground(newValue);
        this.getLabel18().setForeground(newValue);
        this.getLabel19().setForeground(newValue);
        this.getLabel22().setForeground(newValue);
        this.getLabel23().setForeground(newValue);
        this.getLabel24().setForeground(newValue);
        this.getLabel25().setForeground(newValue);
        this.getLabel26().setForeground(newValue);
        this.getLabel27().setForeground(newValue);
        this.getLabel28().setForeground(newValue);
        this.getLabel29().setForeground(newValue);
        this.getLabel30().setForeground(newValue);
        this.getLabel32().setForeground(newValue);
        this.getLabel33().setForeground(newValue);
        this.getLabel34().setForeground(newValue);
        this.getLabel35().setForeground(newValue);
        this.getLabel36().setForeground(newValue);
        this.getLabel37().setForeground(newValue);
        this.getLabel38().setForeground(newValue);
        this.getLabel39().setForeground(newValue);
        this.getLabel40().setForeground(newValue);
        this.getLabel41().setForeground(newValue);
        this.getLabel42().setForeground(newValue);
        this.getLabel43().setForeground(newValue);
        this.getLabel44().setForeground(newValue);
        this.getLabel45().setForeground(newValue);
        this.getLabel46().setForeground(newValue);
        this.getLabel47().setForeground(newValue);
        this.getLabel48().setForeground(newValue);
        this.getLabel49().setForeground(newValue);
        this.getLabel50().setForeground(newValue);
        this.getLabel51().setForeground(newValue);
        this.getLabel52().setForeground(newValue);
        this.getLabel53().setForeground(newValue);
        this.getLabel54().setForeground(newValue);
        this.getLabel55().setForeground(newValue);
        this.getLabel56().setForeground(newValue);
        this.getLabel57().setForeground(newValue);
        this.getLabel58().setForeground(newValue);
        this.getLabel59().setForeground(newValue);
        this.getLabel60().setForeground(newValue);
        this.getLabel61().setForeground(newValue);
        this.getLabel62().setForeground(newValue);
        this.getLabel63().setForeground(newValue);
        this.getLabel64().setForeground(newValue);
        this.getLabel65().setForeground(newValue);
        this.getCanvas1().setForeground(newValue);
    }
    
    public void setHeight(final int newValue) {
        this.Height = newValue;
    }
    
    public void setImagesArray(final Image[] newValue) {
        this.ImagesArray = newValue;
    }
    
    public void setLabels(final boolean newValue) {
        this.Labels = newValue;
        this.getLabel().setVisible(newValue);
    }
    
    public void setLinks(final boolean newValue) {
        this.Links = newValue;
    }
    
    public void setNumberOfImages(final int newValue) {
        this.NumberOfImages = newValue;
    }
    
    public void setResize(final boolean newValue) {
        this.Resize = newValue;
    }
    
    public void setSequence(final char newValue) {
        this.Sequence = newValue;
    }
    
    public void setSpecialEffect(final String newValue) {
        this.SpecialEffect = newValue;
    }
    
    public void setTime(final int newValue) {
        this.Time = newValue;
    }
    
    public void setTrailEffect(final boolean newValue) {
        this.TrailEffect = newValue;
    }
    
    public void setTrigger(final String newValue) {
        this.Trigger = newValue;
    }
    
    public void setWidth(final int newValue) {
        this.Width = newValue;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
