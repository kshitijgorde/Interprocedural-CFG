import java.awt.FontMetrics;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class LoveMeter extends Applet implements ActionListener, Runnable
{
    static final int MAX_LOADING = 6;
    private boolean registered;
    private Thread loadingThread;
    private Image bufferImage;
    private Graphics bufferGraphics;
    private Dimension bufferSize;
    private TextField singleName;
    private TextField coupleName;
    private LMResult resultScreen;
    private LMThermometer thermometer;
    private LMButton[] buttons;
    private LMButton resultButton;
    private LMButton regButton;
    private String errorString;
    private String loadingString;
    private Image backgroundImage;
    private Color[] colors;
    private Font nameLabelFont;
    private Font resultLabelFont;
    private String[][] resultLabels;
    private String[] resultButtonLabels;
    private String[] nameLabels;
    private boolean resetThermometer;
    private int calculationType;
    private int genderState;
    private int rememberGenderState;
    private int resultIndex;
    private int loadingIndex;
    
    public LoveMeter() {
        this.registered = false;
        this.bufferSize = null;
        this.buttons = new LMButton[4];
        this.errorString = null;
        this.loadingString = null;
        this.backgroundImage = null;
        this.colors = new Color[4];
        this.resultLabels = new String[3][9];
        this.resultButtonLabels = new String[3];
        this.nameLabels = new String[4];
        this.resetThermometer = true;
        this.calculationType = 1;
        this.genderState = 0;
        this.rememberGenderState = 0;
        this.resultIndex = -1;
        this.loadingIndex = 0;
    }
    
    public void init() {
        System.out.println("\nLoveMeter Version 2.0");
        System.out.println("***********************\n");
        String tempRegistered = this.getDocumentBase().getHost();
        if (tempRegistered == null) {
            tempRegistered = "";
        }
        this.registered = tempRegistered.equalsIgnoreCase("www.realapplets.com");
        System.out.println(" Unregistered applet, written by Geoffrey from RealApplets");
        System.out.println(" Get your applets from http://www.realapplets.com");
        System.out.println(" Removal of add without registration is punishable in a court of law.\n");
        final Dimension size = this.getSize();
        if (size.width < 260 || size.height < 260) {
            this.setErrorString("The WIDTH or the HEIGHT of the applet must be bigger then 260.");
            return;
        }
        this.bufferImage = this.createImage(size.width, size.height);
        this.bufferGraphics = this.bufferImage.getGraphics();
        try {
            this.colors[0] = new Color(Integer.parseInt(this.getParameter("BackgroundColor"), 16));
            this.colors[1] = new Color(Integer.parseInt(this.getParameter("ForegroundColor"), 16));
            this.colors[2] = new Color(Integer.parseInt(this.getParameter("ResultLabelColor"), 16));
            this.colors[3] = new Color(Integer.parseInt(this.getParameter("WinningResultLabelColor"), 16));
        }
        catch (Exception exception) {
            this.setErrorString("One of the non button Color parameters is wrong", exception);
        }
        this.setBackground(this.colors[0]);
        this.nameLabelFont = new Font(this.getParameter("NameLabelFontStyle"), 0, 14);
        this.loadingString = this.getParameter("LoadingAppletMessage");
        this.setLayout(new BoundsLayout(260, 260));
        (this.loadingThread = new Thread(this)).setPriority(Thread.currentThread().getPriority() + 1);
        this.loadingThread.start();
        this.repaint();
    }
    
    public void destroy() {
        if (this.thermometer != null) {
            this.thermometer.destroy();
        }
        if (this.resultScreen != null) {
            this.resultScreen.destroy();
        }
        if (this.loadingThread != null) {
            this.loadingThread.interrupt();
        }
    }
    
    public void run() {
        final String[] genderStrings = { "Male", "Female", "Couple" };
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image[] loadingImages = new Image[5];
        final String[] loadingStrings = { "MaleDefaultPicture", "FemaleDefaultPicture", "CoupleDefaultPicture", "ThermometerPicture", "ButtonPicture" };
        String tempString = this.getParameter("BackgroundPicture");
        if (!tempString.equals("") && !tempString.equalsIgnoreCase("None")) {
            mediaTracker.addImage(this.backgroundImage = this.getImage(this.getCodeBase(), tempString), 0);
        }
        for (int i = 0; i < loadingImages.length; ++i) {
            tempString = this.getParameter(loadingStrings[i]);
            if (!tempString.equals("") && !tempString.equalsIgnoreCase("None")) {
                mediaTracker.addImage(loadingImages[i] = this.getImage(this.getCodeBase(), tempString), i + 1);
            }
        }
        tempString = this.getParameter("ResetThermometer");
        this.resetThermometer = (tempString.equalsIgnoreCase("Yes") || tempString.equalsIgnoreCase("True"));
        tempString = this.getParameter("CalculationType");
        if (tempString.equalsIgnoreCase("Simple")) {
            this.calculationType = 0;
        }
        else if (tempString.equalsIgnoreCase("Random")) {
            this.calculationType = 2;
        }
        this.nameLabels[0] = this.getParameter("MaleNameLabel");
        this.nameLabels[1] = this.getParameter("FemaleNameLabel");
        this.nameLabels[2] = this.getParameter("CoupleName1Label");
        this.nameLabels[3] = this.getParameter("CoupleName2Label");
        this.resultLabelFont = new Font(this.getParameter("ResultLabelFontStyle"), 0, 18);
        for (int j = 0; j < this.resultLabels.length; ++j) {
            for (int k = 0; k < this.resultLabels[j].length; ++k) {
                this.resultLabels[j][k] = this.getParameter(genderStrings[j] + (k + 1) + "Label");
            }
        }
        final String resultLoadingString = this.getParameter("LoadingPictureMessage");
        final Color[] buttonColors = new Color[4];
        buttonColors[0] = this.colors[0];
        try {
            buttonColors[1] = new Color(Integer.parseInt(this.getParameter("ButtonTagColorOut"), 16));
            buttonColors[2] = new Color(Integer.parseInt(this.getParameter("ButtonTagColorOver"), 16));
            buttonColors[3] = new Color(Integer.parseInt(this.getParameter("ButtonTagColorDown"), 16));
        }
        catch (Exception exception) {
            this.setErrorString("One of the button Color parameters is wrong", exception);
            return;
        }
        final String buttonFontStyle = this.getParameter("ButtonTagFontStyle");
        final String[] smallButtonLabels = { this.getParameter("SingleButtonTag"), this.getParameter("CoupleButtonTag"), this.getParameter("MaleButtonTag"), this.getParameter("FemaleButtonTag") };
        for (int l = 0; l < this.resultButtonLabels.length; ++l) {
            this.resultButtonLabels[l] = this.getParameter("CalculateButtonTag" + genderStrings[l]);
        }
        try {
            if (this.backgroundImage != null) {
                mediaTracker.waitForID(this.loadingIndex);
                if (this.backgroundImage.getWidth(this) <= 0) {
                    this.setErrorString("BackgroundPicture " + this.getParameter("BackgroundPicture") + " is not present.");
                    return;
                }
            }
            ++this.loadingIndex;
            this.repaint();
            while (this.loadingIndex < 6) {
                if (loadingImages[this.loadingIndex - 1] != null) {
                    mediaTracker.waitForID(this.loadingIndex);
                    if (loadingImages[this.loadingIndex - 1].getWidth(this) <= 0) {
                        this.setErrorString("Picture " + this.getParameter(loadingStrings[this.loadingIndex - 1]) + " is not present.");
                        return;
                    }
                }
                ++this.loadingIndex;
                this.repaint();
            }
        }
        catch (InterruptedException interruptedException) {
            return;
        }
        (this.singleName = new TextField(this.getParameter("DefaultNameText"))).setBackground(Color.white);
        this.singleName.addActionListener(this);
        (this.coupleName = new TextField(this.getParameter("DefaultCoupleName2Text"))).setBackground(Color.white);
        this.coupleName.setVisible(false);
        this.coupleName.addActionListener(this);
        this.resultScreen = new LMResult(this, resultLoadingString, this.colors[0], this.colors[1], loadingImages);
        this.thermometer = new LMThermometer(this, loadingImages[3], this.colors[0], this.colors[1]);
        for (int m = 0; m < this.buttons.length; ++m) {
            this.buttons[m] = new LMButton(this, loadingImages[4], buttonColors, buttonFontStyle, true, m, smallButtonLabels[m], m % 2 == 0);
        }
        this.resultButton = new LMButton(this, loadingImages[4], buttonColors, buttonFontStyle, false, 4, this.resultButtonLabels[0], false);
        if (!this.registered) {
            this.regButton = new LMButton(this, loadingImages[4], buttonColors, buttonFontStyle, false, 5, "Visit RealApplets", false);
        }
        synchronized (this.getTreeLock()) {
            this.add(this.resultScreen, new BoundsConstraints(15, 205, 80, 40, 0.0, 0.0, 0.5, 1.0));
            this.add(this.thermometer, new BoundsConstraints(210, 10, 40, 240, 1.0, 0.0, 0.0, 1.0));
            this.add(this.singleName, new BoundsConstraints(52, 90, 48, 20, 0.2, 0.0, 0.3, 0.0));
            this.add(this.coupleName, new BoundsConstraints(52, 120, 48, 20, 0.2, 0.0, 0.3, 0.0));
            this.add(this.buttons[0], new BoundsConstraints(10, 60, 40, 20, 0.0, 0.0, 0.25, 0.0));
            this.add(this.buttons[1], new BoundsConstraints(60, 60, 40, 20, 0.25, 0.0, 0.25, 0.0));
            this.add(this.buttons[2], new BoundsConstraints(10, 120, 40, 20, 0.0, 0.0, 0.25, 0.0));
            this.add(this.buttons[3], new BoundsConstraints(60, 120, 40, 20, 0.25, 0.0, 0.25, 0.0));
            this.add(this.resultButton, new BoundsConstraints(10, 160, 90, 20, 0.0, 0.0, 0.5, 0.0));
            if (!this.registered) {
                this.add(this.regButton, new BoundsConstraints(110, 230, 90, 20, 0.5, 1.0, 0.5, 0.0));
            }
        }
        this.loadingString = null;
        this.doLayout();
        this.repaint();
        this.singleName.requestFocus();
        this.singleName.selectAll();
        this.loadingThread = null;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.actionPerformed(4);
    }
    
    public void actionPerformed(final int actionIndex) {
        switch (actionIndex) {
            case 0: {
                this.coupleName.setVisible(false);
                this.buttons[2].setVisible(true);
                this.buttons[3].setVisible(true);
                this.genderState = this.rememberGenderState;
                this.buttons[1].resetDownState();
                this.resultButton.setLabel(this.resultButtonLabels[this.genderState]);
                this.resetResult();
                this.doLayout();
                break;
            }
            case 1: {
                this.buttons[2].setVisible(false);
                this.buttons[3].setVisible(false);
                this.coupleName.setVisible(true);
                this.genderState = 2;
                this.buttons[0].resetDownState();
                this.resultButton.setLabel(this.resultButtonLabels[this.genderState]);
                this.resetResult();
                this.doLayout();
                break;
            }
            case 2: {
                this.genderState = 0;
                this.rememberGenderState = 0;
                this.buttons[3].resetDownState();
                this.resultButton.setLabel(this.resultButtonLabels[this.genderState]);
                this.resetResult();
                break;
            }
            case 3: {
                this.genderState = 1;
                this.rememberGenderState = 1;
                this.buttons[2].resetDownState();
                this.resultButton.setLabel(this.resultButtonLabels[this.genderState]);
                this.resetResult();
                break;
            }
            case 4: {
                final int calculatedResult = (this.genderState < 2) ? this.calculateResult(this.singleName.getText()) : this.calculateResult(this.singleName.getText() + " " + this.coupleName.getText());
                if (this.resetThermometer) {
                    this.resetResult();
                    this.thermometer.goToResult(calculatedResult);
                    break;
                }
                if (this.resultIndex != calculatedResult) {
                    this.resultIndex = -1;
                    this.thermometer.goToResult(calculatedResult);
                    break;
                }
                break;
            }
            case 5: {
                try {
                    this.getAppletContext().showDocument(new URL("http://www.realapplets.com"), "_blank");
                }
                catch (MalformedURLException ex) {}
                break;
            }
        }
        this.repaint();
        this.singleName.requestFocus();
        this.singleName.selectAll();
    }
    
    private int calculateResult(final String name) {
        if (this.calculationType < 2) {
            int returnNumber = 0;
            for (int i = 0; i < name.length(); ++i) {
                int charNumber = name.charAt(i);
                if (charNumber >= 65 && charNumber <= 90) {
                    charNumber -= 64;
                }
                else if (charNumber >= 97 && charNumber <= 122) {
                    charNumber -= 96;
                }
                else {
                    charNumber = 0;
                }
                if (this.calculationType == 1) {
                    charNumber *= i;
                }
                returnNumber = (returnNumber + charNumber) % 9;
            }
            return returnNumber;
        }
        return (int)(Math.random() * 9.0);
    }
    
    public void resetResult() {
        this.thermometer.resetResult();
        this.resultIndex = -1;
        this.resultScreen.setResult(this.genderState, -1);
    }
    
    public void setResult(final int resultIndex) {
        this.resultIndex = resultIndex;
        this.resultScreen.setResult(this.genderState, resultIndex);
        this.repaint();
    }
    
    public String getParameter(final String parameterName) {
        final String parameterValue = super.getParameter(parameterName);
        if (parameterValue != null) {
            return parameterValue;
        }
        return "";
    }
    
    public void setErrorString(final String errorString, final Exception exception) {
        this.errorString = errorString;
        if (exception != null) {
            System.out.println(errorString + "\n" + exception);
        }
        else {
            System.out.println(errorString);
        }
        this.removeAll();
        this.repaint();
    }
    
    public void setErrorString(final String errorString) {
        this.setErrorString(errorString, null);
    }
    
    private void confirmBuffer() {
        final Dimension size = this.getSize();
        if (this.bufferSize == null || !this.bufferSize.equals(size)) {
            this.bufferImage = this.createImage(size.width, size.height);
            this.bufferGraphics = this.bufferImage.getGraphics();
            this.bufferSize = new Dimension(size);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.errorString != null) {
            final Dimension size = this.getSize();
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, size.width, size.height);
            graphics.setColor(Color.black);
            graphics.setFont(new Font("Arial", 0, 16));
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.drawString(this.errorString, (size.width - fontMetrics.stringWidth(this.errorString)) / 2, (size.height + fontMetrics.getAscent()) / 2);
        }
        else {
            this.confirmBuffer();
            if (this.loadingString != null) {
                this.bufferGraphics.setColor(this.colors[0]);
                this.bufferGraphics.fillRect(0, 0, this.bufferSize.width, this.bufferSize.height);
                this.bufferGraphics.setFont(this.nameLabelFont);
                this.bufferGraphics.setColor(this.colors[1]);
                this.bufferGraphics.drawString(this.loadingString, 10, 22);
                if (!this.registered) {
                    this.bufferGraphics.drawString("Unregistered applet from RealApplets.com", 10, 98);
                }
                this.bufferGraphics.setFont(new Font("Ariel", 1, 20));
                this.bufferGraphics.setColor(this.colors[2]);
                this.bufferGraphics.drawRect(10, 36, this.bufferSize.width - 21, 39);
                this.bufferGraphics.drawRect(11, 37, this.bufferSize.width - 23, 37);
                this.bufferGraphics.drawRect(12, 38, this.bufferSize.width - 25, 35);
                if (this.loadingIndex > 0) {
                    this.bufferGraphics.fillRect(14, 40, (this.bufferSize.width - 28) * this.loadingIndex / 6, 32);
                }
                this.bufferGraphics.setColor(this.colors[3]);
                final String tempString = 100 * this.loadingIndex / 6 + "%";
                this.bufferGraphics.drawString(tempString, (this.bufferSize.width - this.bufferGraphics.getFontMetrics().stringWidth(tempString)) / 2, 66);
            }
            else {
                if (this.backgroundImage != null) {
                    this.bufferGraphics.drawImage(this.backgroundImage, 0, 0, this.bufferSize.width, this.bufferSize.height, this);
                }
                else {
                    this.bufferGraphics.setColor(this.colors[0]);
                    this.bufferGraphics.fillRect(0, 0, this.bufferSize.width, this.bufferSize.height);
                }
                this.bufferGraphics.setFont(this.nameLabelFont);
                this.bufferGraphics.setColor(this.colors[1]);
                FontMetrics fontMetrics2 = this.bufferGraphics.getFontMetrics();
                this.bufferGraphics.drawString(this.nameLabels[this.genderState], 12, 107 - fontMetrics2.getDescent());
                if (this.genderState == 2) {
                    this.bufferGraphics.drawString(this.nameLabels[3], 12, 137 - fontMetrics2.getDescent());
                }
                this.bufferGraphics.setFont(this.resultLabelFont);
                fontMetrics2 = this.bufferGraphics.getFontMetrics();
                final int blockHeight = (this.bufferSize.height - 50) / this.resultLabels[this.genderState].length;
                for (int i = 0; i < this.resultLabels[this.genderState].length; ++i) {
                    if (this.resultIndex != i) {
                        this.bufferGraphics.setColor(this.colors[2]);
                    }
                    else {
                        this.bufferGraphics.setColor(this.colors[3]);
                    }
                    this.bufferGraphics.drawString(this.resultLabels[this.genderState][i], this.bufferSize.width - 60 - fontMetrics2.stringWidth(this.resultLabels[this.genderState][i]), this.bufferSize.height - 31 - fontMetrics2.getDescent() - (2 * i + 1) * blockHeight / 2);
                }
            }
            graphics.drawImage(this.bufferImage, 0, 0, this);
        }
    }
}
