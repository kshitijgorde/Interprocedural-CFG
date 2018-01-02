// 
// Decompiled by Procyon v0.5.30
// 

package DesignARoom;

import java.awt.Graphics;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Event;
import java.util.Hashtable;
import java.awt.Color;
import java.util.Vector;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.applet.Applet;

public class WebApplet extends Applet
{
    GridBagLayout gbl;
    Panel roomPP;
    Panel roomP;
    Panel furnP;
    Panel prebuiltP;
    Panel rotateP;
    Panel controlsP;
    Panel rotatePP;
    Panel wCP;
    Panel labelP;
    public Panel updateP;
    public WebTitleCanvas wtC;
    public WebTitleCanvas wtCroom;
    public WebTitleCanvas wtCfurn;
    public Choice rmCurr;
    public TextField rmName;
    public TextField rmWidth;
    public Choice rmWunit;
    public TextField rmLength;
    public Choice rmLunit;
    public Button rmButton;
    public Button gridButton;
    public TextField fnLabel;
    public TextField fnWidth;
    public Choice fnWunit;
    public TextField fnLength;
    public Choice fnLunit;
    public Choice fnColor;
    public Choice fnShape;
    public Button fnButton;
    public Choice prebuilt;
    public Button clearButton;
    public Button copyButton;
    public Button deleteButton;
    public Choice rDir;
    public Choice rAmt;
    public Button rButton;
    public Button storeButton;
    public Button clrstorButton;
    Vector compVector;
    public WebCanvas wC;
    Webfurn furnItem;
    Color offWhite;
    Color offWhite2;
    Color titleColor;
    Object focussedComp;
    boolean opening;
    Hashtable prebuiltHash;
    Event actionEvent;
    String rmWfeet;
    String rmHfeet;
    
    public WebApplet() {
        this.gbl = new GridBagLayout();
        this.roomPP = new Panel();
        this.roomP = new Panel();
        this.furnP = new Panel();
        this.prebuiltP = new Panel();
        this.rotateP = new Panel();
        this.controlsP = new Panel();
        this.rotatePP = new Panel();
        this.wCP = new Panel();
        this.labelP = new Panel();
        this.updateP = new Panel();
        this.wtC = new WebTitleCanvas(0, "", Color.black, 24, 21, 28, true);
        this.wtCroom = new WebTitleCanvas(170, "ROOM", Color.red.darker().darker(), 16, 16, 20, false);
        this.wtCfurn = new WebTitleCanvas(169, "FURNITURE", Color.blue.darker().darker(), 16, 16, 20, false);
        this.rmCurr = new Choice();
        this.rmName = new TextField(10);
        this.rmWidth = new TextField(3);
        this.rmWunit = new Choice();
        this.rmLength = new TextField(3);
        this.rmLunit = new Choice();
        this.rmButton = new Button("Create Room");
        this.gridButton = new Button("Grid OFF");
        this.fnLabel = new TextField(10);
        this.fnWidth = new TextField(3);
        this.fnWunit = new Choice();
        this.fnLength = new TextField(3);
        this.fnLunit = new Choice();
        this.fnColor = new Choice();
        this.fnShape = new Choice();
        this.fnButton = new Button("Create Furniture");
        this.prebuilt = new Choice();
        this.clearButton = new Button("Clear");
        this.copyButton = new Button("Copy");
        this.deleteButton = new Button("Delete");
        this.rDir = new Choice();
        this.rAmt = new Choice();
        this.rButton = new Button("Rotate Furniture");
        this.storeButton = new Button("Store Everything");
        this.clrstorButton = new Button("Clear Storage Area");
        this.compVector = new Vector();
        this.offWhite = new Color(255, 255, 255);
        this.offWhite2 = new Color(205, 205, 205);
        this.titleColor = Color.green;
        this.focussedComp = this;
        this.opening = false;
        this.prebuiltHash = new Hashtable();
        this.actionEvent = null;
    }
    
    public void decideAbles(final int n) {
    }
    
    public void decideSave() {
    }
    
    public void addToOrigVectors(final int n) {
    }
    
    public void updateOrigVectors(final int n) {
    }
    
    public void save(final int n) {
    }
    
    public void print() {
    }
    
    public void New() {
        this.rmCurr.select(0);
        this.handleChoice(this.rmCurr);
    }
    
    public void constructTitles() {
        if (this.getParent().size().width == 510) {
            this.wC = new WebCanvas(this, this.getParent().size().width - 230, 395);
        }
        else {
            this.wC = new WebCanvas(this, this.getParent().size().width - 230, this.getParent().size().height - 110);
        }
    }
    
    public void constructwC() {
        if (this.getParent().size().width == 510) {
            this.wC = new WebCanvas(this, this.getParent().size().width - 230, 395);
        }
        else {
            this.wC = new WebCanvas(this, this.getParent().size().width - 230, this.getParent().size().height - 110);
        }
    }
    
    public void init() {
        this.loadPrebuiltHash();
        this.constructTitles();
        this.constructwC();
        this.wC.createNew = true;
        this.wC.defineRoomSize("25", "25", 'f', 'f', "");
        this.compVector.addElement(this.rmName);
        this.compVector.addElement(this.rmWidth);
        this.compVector.addElement(this.rmLength);
        this.compVector.addElement(this.rmButton);
        this.compVector.addElement(this.fnLabel);
        this.compVector.addElement(this.fnWidth);
        this.compVector.addElement(this.fnLength);
        this.compVector.addElement(this.fnColor);
        this.compVector.addElement(this.fnShape);
        this.compVector.addElement(this.fnButton);
        this.compVector.addElement(this.prebuilt);
        this.compVector.addElement(this.rDir);
        this.compVector.addElement(this.rAmt);
        this.compVector.addElement(this.rButton);
        this.compVector.addElement(this.rmName);
        this.roomP.setLayout(this.gbl);
        int n = 0;
        this.addComponent(this.roomP, new Label("Current:"), 0, n, 1, 1, 0, 3, 'W');
        this.rmCurr.addItem("CREATE NEW");
        this.addComponent(this.roomP, this.rmCurr, 1, n, 2, 1, 0, 3, 'W');
        ++n;
        this.addComponent(this.roomP, new Label("Name:"), 0, n, 1, 1, 0, 2, 'W');
        this.addComponent(this.roomP, this.rmName, 1, n, 2, 1, 0, 2, 'W');
        ++n;
        this.addComponent(this.roomP, new Label("Width:"), 0, n, 1, 1, 0, 2, 'W');
        this.addComponent(this.roomP, this.rmWidth, 1, n, 1, 1, 3, 2, 'W');
        this.rmWunit.addItem("Feet");
        this.rmWunit.addItem("Inches");
        this.rmWunit.addItem("Meters");
        this.addComponent(this.roomP, this.rmWunit, 2, n, 1, 1, 0, 2, 'W');
        ++n;
        this.addComponent(this.roomP, new Label("Length:"), 0, n, 1, 1, 0, 7, 'W');
        this.addComponent(this.roomP, this.rmLength, 1, n, 1, 1, 3, 8, 'W');
        this.rmLunit.addItem("Feet");
        this.rmLunit.addItem("Inches");
        this.rmLunit.addItem("Meters");
        this.addComponent(this.roomP, this.rmLunit, 2, n, 1, 1, 0, 7, 'W');
        ++n;
        this.addComponent(this.roomP, this.rmButton, 0, n, 3, 1, 0, 2, 'C');
        this.furnP.setLayout(this.gbl);
        int n2 = 0;
        this.addComponent(this.furnP, new Label("Label:"), 0, n2, 1, 1, 0, 2, 'W');
        this.addComponent(this.furnP, this.fnLabel, 1, n2, 2, 1, 0, 2, 'W');
        ++n2;
        this.addComponent(this.furnP, new Label("Width:"), 0, n2, 1, 1, 0, 2, 'W');
        this.addComponent(this.furnP, this.fnWidth, 1, n2, 1, 1, 3, 2, 'W');
        this.fnWunit.addItem("Feet");
        this.fnWunit.addItem("Inches");
        this.fnWunit.addItem("Meters");
        this.addComponent(this.furnP, this.fnWunit, 2, n2, 1, 1, 0, 2, 'W');
        ++n2;
        this.addComponent(this.furnP, new Label("Length:"), 0, n2, 1, 1, 0, 2, 'W');
        this.addComponent(this.furnP, this.fnLength, 1, n2, 1, 1, 3, 2, 'W');
        this.fnLunit.addItem("Feet");
        this.fnLunit.addItem("Inches");
        this.fnLunit.addItem("Meters");
        this.addComponent(this.furnP, this.fnLunit, 2, n2, 1, 1, 0, 2, 'W');
        ++n2;
        this.addComponent(this.furnP, new Label("Color:"), 0, n2, 1, 1, 0, 1, 'W');
        this.fnColor.addItem("Black");
        this.fnColor.addItem("Brown");
        this.fnColor.addItem("Tan");
        this.fnColor.addItem("Red");
        this.fnColor.addItem("Orange");
        this.fnColor.addItem("Yellow");
        this.fnColor.addItem("Green");
        this.fnColor.addItem("Light Blue");
        this.fnColor.addItem("Blue");
        this.fnColor.addItem("Purple");
        this.addComponent(this.furnP, this.fnColor, 1, n2, 2, 1, 0, 1, 'W');
        ++n2;
        this.addComponent(this.furnP, new Label("Shape:"), 0, n2, 1, 1, 0, 7, 'W');
        this.fnShape.addItem("Rectangular");
        this.fnShape.addItem("Oval");
        this.fnShape.addItem("Open Door");
        this.fnShape.addItem("Window");
        this.addComponent(this.furnP, this.fnShape, 1, n2, 2, 1, 0, 7, 'W');
        ++n2;
        this.addComponent(this.furnP, this.fnButton, 0, n2, 3, 1, 0, 12, 'C');
        this.updateP.setLayout(this.gbl);
        this.addComponent(this.updateP, this.clearButton, 0, 0, 1, 1, 15, 0, 'C');
        this.addComponent(this.updateP, this.copyButton, 1, 0, 1, 1, 15, 0, 'C');
        this.addComponent(this.updateP, this.deleteButton, 2, 0, 1, 1, 0, 0, 'C');
        ++n2;
        this.addComponent(this.furnP, this.updateP, 0, n2, 3, 1, 0, 0, 'C');
        this.prebuiltP.setLayout(this.gbl);
        this.addComponent(this.prebuiltP, new Label("Pre-Built:"), 0, 0, 1, 1, 0, 0, 'C');
        this.addComponent(this.prebuiltP, this.prebuilt, 1, 0, 1, 1, 0, 0, 'N');
        this.addComponent(this.rotateP, this.rButton, 0, 0, 2, 1, 0, 3, 'C');
        this.rotateP.setLayout(this.gbl);
        this.rDir.addItem("Clockwise");
        this.rDir.addItem("Counter");
        this.addComponent(this.rotateP, this.rDir, 0, 1, 1, 1, 2, 0, 'W');
        this.rAmt.addItem("1 Degree");
        this.rAmt.addItem("45 Degrees");
        this.rAmt.addItem("90 Degrees");
        this.rAmt.addItem("Continuous");
        this.rAmt.addItem("RESET");
        this.rAmt.select(2);
        this.addComponent(this.rotateP, this.rAmt, 1, 1, 1, 1, 0, 0, 'W');
        this.wCP.setLayout(this.gbl);
        this.addComponent(this.wCP, this.wtC, 0, 0, 2, 1, 0, 0, 'W');
        this.addComponent(this.wCP, this.wC, 0, 1, 2, 1, 0, 5, 'C');
        this.addComponent(this.wCP, this.storeButton, 0, 2, 1, 1, 0, 5, 'W');
        this.addComponent(this.wCP, this.clrstorButton, 1, 2, 1, 1, 0, 5, 'E');
        this.addComponent(this.wCP, this.gridButton, 0, 3, 2, 1, 0, 0, 'C');
        this.roomPP.setBackground(Color.gray);
        this.roomP.setBackground(this.offWhite2);
        this.furnP.setBackground(this.offWhite2);
        this.updateP.setBackground(this.offWhite2);
        this.prebuiltP.setBackground(this.offWhite2);
        this.rotateP.setBackground(Color.black);
        this.wCP.setBackground(Color.black);
        this.controlsP.setBackground(Color.gray);
        this.rotatePP.setBackground(Color.gray);
        this.roomPP.setLayout(this.gbl);
        this.wtCroom.c = Color.gray.brighter().brighter();
        this.addComponent(this.roomPP, this.wtCroom, 0, 0, 1, 1, 0, 4, 'C');
        this.addComponent(this.roomPP, this.roomP, 0, 1, 1, 1, 0, 0, 'C');
        this.controlsP.setLayout(this.gbl);
        this.wtCfurn.c = Color.gray.brighter().brighter();
        this.addComponent(this.controlsP, this.wtCfurn, 0, 0, 1, 1, 0, 4, 'C');
        this.addComponent(this.controlsP, this.prebuiltP, 0, 1, 1, 1, 0, 4, 'N');
        this.addComponent(this.controlsP, this.furnP, 0, 2, 1, 1, 0, 0, 'C');
        this.rotatePP.setLayout(this.gbl);
        this.addComponent(this.rotatePP, this.rotateP, 0, 0, 1, 1, 0, 0, 'N');
        this.repaint();
        this.setLayout(this.gbl);
        this.addComponent(this, this.roomPP, 0, 0, 1, 1, 20, 8, 'N');
        this.addComponent(this, this.controlsP, 0, 1, 1, 1, 20, 6, 'N');
        this.addComponent(this, this.rotatePP, 0, 2, 1, 1, 20, 0, 'N');
        this.addComponent(this, this.wCP, 1, 0, 1, 3, 0, 0, 'S');
        this.fnLabel.disable();
        this.fnWidth.disable();
        this.fnLength.disable();
        this.fnWunit.disable();
        this.fnLunit.disable();
        this.fnColor.disable();
        this.fnShape.disable();
        this.fnButton.disable();
        this.prebuilt.disable();
        this.rDir.disable();
        this.rAmt.disable();
        this.rButton.disable();
        this.storeButton.disable();
        this.gridButton.disable();
        this.clrstorButton.disable();
        this.clearButton.disable();
        this.copyButton.disable();
        this.deleteButton.disable();
        this.rmName.requestFocus();
        this.decideAbles(0);
        this.addToOrigVectors(0);
    }
    
    void addComponent(final Panel panel, final Object o, final int gridx, final int gridy, final int gridwidth, final int gridheight, final int right, final int bottom, final char c) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        switch (c) {
            case 'W': {
                gridBagConstraints.anchor = 17;
                break;
            }
            case 'E': {
                gridBagConstraints.anchor = 13;
                break;
            }
            case 'C': {
                gridBagConstraints.anchor = 10;
                break;
            }
            case 'N': {
                gridBagConstraints.anchor = 11;
                break;
            }
            case 'S': {
                gridBagConstraints.anchor = 15;
                break;
            }
        }
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        final Component component = (Component)o;
        if (o instanceof Button) {
            component.setForeground(Color.black);
            component.setBackground(Color.lightGray);
        }
        if (o instanceof Label) {
            component.setForeground(Color.black);
            if (panel.equals(this.roomP)) {
                component.setBackground(this.offWhite2);
            }
            if (panel.equals(this.controlsP)) {
                component.setBackground(this.offWhite2);
            }
        }
        if (o instanceof TextField) {
            component.setBackground(Color.white);
        }
        if (o instanceof Choice) {
            component.setBackground(this.offWhite);
        }
        gridBagConstraints.insets.right = right;
        gridBagConstraints.insets.bottom = bottom;
        if (panel.equals(this.controlsP) || panel.equals(this.rotatePP) || panel.equals(this.roomPP)) {
            gridBagConstraints.ipadx = 20;
            gridBagConstraints.ipady = 10;
        }
        if (o.equals(this.rotateP)) {
            gridBagConstraints.ipadx = 10;
            gridBagConstraints.ipady = 10;
        }
        if (o.equals(this.wtCroom) || o.equals(this.wtCfurn)) {
            gridBagConstraints.ipadx = 0;
            gridBagConstraints.ipady = 0;
        }
        if (o.equals(this.roomPP)) {
            gridBagConstraints.ipadx = 7;
            gridBagConstraints.ipady = 7;
        }
        if (o.equals(this.controlsP)) {
            gridBagConstraints.ipadx = 7;
            gridBagConstraints.ipady = 7;
        }
        if (o.equals(this.rotatePP)) {
            gridBagConstraints.ipadx = 5;
            gridBagConstraints.ipady = 5;
        }
        this.gbl.setConstraints(component, gridBagConstraints);
        panel.add(component);
    }
    
    void setNewMode() {
        if (this.wC.mode.equals("update")) {
            this.wC.mode = "new";
            this.copyButton.disable();
            this.deleteButton.disable();
            this.fnButton.setLabel("Create Furniture");
            this.fnButton.enable();
            this.rDir.disable();
            this.rAmt.disable();
            this.rButton.disable();
            this.rDir.select(0);
            this.rAmt.select(2);
        }
    }
    
    void clearFurnValues() {
        this.fnLabel.setText("");
        this.fnLength.setText("");
        this.fnWidth.setText("");
        this.fnWunit.select(0);
        this.fnLunit.select(0);
        this.fnColor.select(0);
        this.fnShape.select(0);
        this.prebuilt.select(0);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.size().width, this.size().height);
        graphics.setColor(Color.darkGray);
        graphics.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        if (this.rmCurr.getSelectedItem().equals("CREATE NEW")) {
            this.wtC.c = Color.black;
        }
        else {
            this.wtC.c = this.titleColor;
            if (this.wC.spinning) {
                this.stopSpinning();
            }
            this.decideSave();
        }
    }
    
    void stopSpinning() {
        this.wC.spinning = false;
        this.rButton.setLabel("Rotate Furniture");
        this.rDir.enable();
    }
    
    public boolean action(final Event actionEvent, final Object o) {
        boolean b = false;
        this.actionEvent = actionEvent;
        if (this.wC.spinning && !actionEvent.target.equals(this.rButton)) {
            this.stopSpinning();
        }
        if (actionEvent.target instanceof Button) {
            b = this.handleButton((Button)actionEvent.target, (String)o);
        }
        if (actionEvent.target instanceof Choice) {
            b = this.handleChoice((Choice)actionEvent.target);
        }
        if (!actionEvent.target.equals(this.gridButton)) {
            this.decideSave();
        }
        return b;
    }
    
    public boolean handleButton(final Button button, final String s) {
        if (s.equals("Create Room") && this.fieldEntered(this.rmName, "Room Name") && this.NumericAndPositive(this.rmWidth, "Room Width") && this.NumericAndPositive(this.rmLength, "Room Length")) {
            this.rmWfeet = this.toFeet(this.rmWidth.getText(), this.rmWunit.getSelectedItem());
            this.rmHfeet = this.toFeet(this.rmLength.getText(), this.rmLunit.getSelectedItem());
            this.wC.createNew = false;
            this.wC.defineRoomSize(this.rmWfeet, this.rmHfeet, this.UtoChar(this.rmWunit.getSelectedItem()), this.UtoChar(this.rmLunit.getSelectedItem()), this.rmName.getText());
            this.rmCurr.addItem(this.rmName.getText());
            this.rmCurr.select(this.rmCurr.countItems() - 1);
            this.rmName.disable();
            this.rmButton.setLabel("Update Room");
            this.fnLabel.enable();
            this.fnWidth.enable();
            this.fnLength.enable();
            this.fnWunit.enable();
            this.fnLunit.enable();
            this.fnColor.enable();
            this.fnShape.enable();
            this.fnButton.enable();
            this.prebuilt.enable();
            this.clearButton.enable();
            this.storeButton.enable();
            this.gridButton.enable();
            this.clrstorButton.enable();
            this.wtC.resize(this.wC.roomWidth, 28);
            this.wtC.setWidth(this.wC.roomWidth);
            this.wtC.setText(this.rmName.getText());
            this.wtC.c = this.titleColor;
            this.wtC.repaint();
            this.wtC.show();
            this.fnLabel.requestFocus();
            this.decideAbles(this.rmCurr.getSelectedIndex());
            this.addToOrigVectors(this.rmCurr.getSelectedIndex());
        }
        if (s.equals("Update Room") && this.NumericAndPositive(this.rmWidth, "Room Width") && this.NumericAndPositive(this.rmLength, "Room Length")) {
            this.rmWfeet = this.toFeet(this.rmWidth.getText(), this.rmWunit.getSelectedItem());
            this.rmHfeet = this.toFeet(this.rmLength.getText(), this.rmLunit.getSelectedItem());
            this.wC.updateRoomSize(this.rmWfeet, this.rmHfeet, this.UtoChar(this.rmWunit.getSelectedItem()), this.UtoChar(this.rmLunit.getSelectedItem()));
            this.wtC.resize(this.wC.roomWidth, 28);
            this.wtC.setWidth(this.wC.roomWidth);
            this.wtC.setText(this.rmName.getText());
            this.wtC.repaint();
            this.wtC.show();
        }
        if (s.equals("Create Furniture") && this.furnitureOK()) {
            this.wC.addFurniture(this.toFeet(this.fnWidth.getText(), this.fnWunit.getSelectedItem()), this.toFeet(this.fnLength.getText(), this.fnLunit.getSelectedItem()), this.fnWunit.getSelectedItem(), this.fnLunit.getSelectedItem(), this.fnLabel.getText(), this.fnColor.getSelectedItem(), this.fnShape.getSelectedItem(), "0");
            this.clearFurnValues();
        }
        if (s.equals("Rotate Furniture")) {
            if (this.rAmt.getSelectedItem().equals("Continuous")) {
                this.rButton.setLabel("Stop");
                this.rDir.disable();
            }
            this.wC.doRotate();
        }
        if (s.equals("Stop")) {
            this.stopSpinning();
        }
        if (s.equals("Store Everything")) {
            this.wC.storeEverything();
        }
        if (s.equals("Clear Storage Area")) {
            this.wC.clearStorage();
            this.furnItem = this.wC.getGrabbedOne();
            if (this.furnItem != null) {
                this.furnItem.grabbed = false;
                this.furnItem.doubleClicked = false;
                this.wC.repaintNewPosition(this.furnItem);
            }
            this.setNewMode();
            this.clearFurnValues();
        }
        if (s.equals("Grid ON")) {
            this.gridButton.setLabel("Grid OFF");
            this.wC.setGridOn(true);
        }
        if (s.equals("Grid OFF")) {
            this.gridButton.setLabel("Grid ON");
            this.wC.setGridOn(false);
        }
        if (s.equals("Clear")) {
            this.furnItem = this.wC.getGrabbedOne();
            if (this.furnItem != null) {
                this.furnItem.grabbed = false;
                this.furnItem.doubleClicked = false;
                this.wC.repaintNewPosition(this.furnItem);
            }
            this.setNewMode();
            this.clearFurnValues();
        }
        if (s.equals("Copy") && this.furnitureOK()) {
            this.furnItem = this.wC.getGrabbedOne();
            this.wC.addFurniture(this.toFeet(this.fnWidth.getText(), this.fnWunit.getSelectedItem()), this.toFeet(this.fnLength.getText(), this.fnLunit.getSelectedItem()), this.fnWunit.getSelectedItem(), this.fnLunit.getSelectedItem(), this.fnLabel.getText(), this.fnColor.getSelectedItem(), this.fnShape.getSelectedItem(), "0");
        }
        if (s.equals("Delete")) {
            this.furnItem = this.wC.getGrabbedOne();
            this.wC.deleteFurniture(this.furnItem);
            this.setNewMode();
        }
        return true;
    }
    
    public boolean handleChoice(final Choice choice) {
        final Webfurn grabbedOne = this.wC.getGrabbedOne();
        if (choice.equals(this.prebuilt)) {
            this.createPrebuilt();
        }
        if (choice.equals(this.fnColor) || choice.equals(this.fnShape)) {
            this.updateFurn();
        }
        if (grabbedOne != null) {
            if (choice.equals(this.fnWunit)) {
                grabbedOne.setWunit(this.UtoChar(this.fnWunit.getSelectedItem()));
                grabbedOne.oWu = this.fnWunit.getSelectedItem();
                this.fnWidth.setText(this.fromFeet(grabbedOne.oW, grabbedOne.getWunit()));
            }
            if (choice.equals(this.fnLunit)) {
                grabbedOne.setHunit(this.UtoChar(this.fnLunit.getSelectedItem()));
                grabbedOne.oHu = this.fnLunit.getSelectedItem();
                this.fnLength.setText(this.fromFeet(grabbedOne.oH, grabbedOne.getHunit()));
            }
        }
        if (choice.equals(this.rAmt) || choice.equals(this.rDir)) {
            if (this.rAmt.getSelectedItem().equals("Continuous")) {
                this.rButton.setLabel("Stop");
                this.rDir.disable();
            }
            this.wC.doRotate();
        }
        if (choice.equals(this.rmCurr)) {
            if (choice.getSelectedIndex() == 0) {
                this.wC.createNew = true;
                this.wC.retrieveRoom(choice.getSelectedItem(), choice.getSelectedIndex());
                this.rmName.setText("");
                this.rmName.enable();
                this.rmWidth.setText("");
                this.rmLength.setText("");
                this.rmWunit.select(0);
                this.rmLunit.select(0);
                this.rmButton.setLabel("Create Room");
                this.fnLabel.disable();
                this.fnWidth.disable();
                this.fnLength.disable();
                this.fnWunit.disable();
                this.fnLunit.disable();
                this.fnColor.disable();
                this.fnShape.disable();
                this.fnButton.disable();
                this.clearButton.disable();
                this.prebuilt.disable();
                this.storeButton.disable();
                this.gridButton.disable();
                this.clrstorButton.disable();
                this.wtC.c = Color.black;
                this.wtC.repaint();
                this.rmName.requestFocus();
            }
            else {
                this.wC.createNew = false;
                this.wC.retrieveRoom(choice.getSelectedItem(), choice.getSelectedIndex());
                this.rmName.setText(this.wC.getName());
                this.rmName.disable();
                final char wunit = this.wC.getWunit();
                final char hunit = this.wC.getHunit();
                this.rmWidth.setText(this.fromFeet(this.wC.getWidth(), wunit));
                this.rmLength.setText(this.fromFeet(this.wC.getHeight(), hunit));
                switch (wunit) {
                    case 'f': {
                        this.rmWunit.select(0);
                        break;
                    }
                    case 'i': {
                        this.rmWunit.select(1);
                        break;
                    }
                    case 'm': {
                        this.rmWunit.select(2);
                        break;
                    }
                }
                switch (hunit) {
                    case 'f': {
                        this.rmLunit.select(0);
                        break;
                    }
                    case 'i': {
                        this.rmLunit.select(1);
                        break;
                    }
                    case 'm': {
                        this.rmLunit.select(2);
                        break;
                    }
                }
                this.rmButton.setLabel("Update Room");
                this.fnLabel.enable();
                this.fnWidth.enable();
                this.fnLength.enable();
                this.fnWunit.enable();
                this.fnLunit.enable();
                this.fnColor.enable();
                this.fnShape.enable();
                this.fnButton.enable();
                this.clearButton.enable();
                this.prebuilt.enable();
                this.storeButton.enable();
                this.gridButton.enable();
                this.clrstorButton.enable();
                this.wtC.resize(this.wC.roomWidth, 28);
                this.wtC.setWidth(this.wC.roomWidth);
                this.wtC.setText(this.rmName.getText());
                this.wtC.c = this.titleColor;
                this.wtC.repaint();
                this.wtC.show();
                this.fnLabel.requestFocus();
            }
            this.copyButton.disable();
            this.deleteButton.disable();
            this.clearFurnValues();
            this.rDir.disable();
            this.rAmt.disable();
            this.rButton.disable();
            this.rDir.select(0);
            this.rAmt.select(2);
            if (this.opening) {
                this.opening = false;
                this.updateOrigVectors(choice.getSelectedIndex());
            }
            this.decideAbles(choice.getSelectedIndex());
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1004) {
            this.focussedComp = event.target;
        }
        return super.handleEvent(event);
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (!altDown(event) && (event.target == this.fnLabel || event.target == this.fnWidth || event.target == this.fnLength)) {
            if (this.wC.spinning) {
                this.stopSpinning();
            }
            this.updateFurn();
            this.decideSave();
        }
        return false;
    }
    
    public static boolean altDown(final Event event) {
        return (event.modifiers & 0x8) != 0x0;
    }
    
    public boolean keyDown(final Event event, final int n) {
        final char c = (char)n;
        if (!altDown(event)) {
            if (c == '\t') {
                if (this.compVector.contains(this.focussedComp)) {
                    if (!event.shiftDown()) {
                        this.compVector.elementAt(this.compVector.indexOf(this.focussedComp) + 1).requestFocus();
                    }
                    else {
                        this.compVector.elementAt(this.compVector.lastIndexOf(this.focussedComp) - 1).requestFocus();
                    }
                }
                else {
                    this.compVector.firstElement().requestFocus();
                }
            }
            return false;
        }
        System.out.println(c);
        switch (c) {
            case 'c': {
                this.wC.repaint();
                return true;
            }
            case 'C': {
                this.wC.repaint();
                return true;
            }
            case 'n': {
                this.New();
                return true;
            }
            case 'N': {
                this.New();
                return true;
            }
            case 's': {
                this.save(this.rmCurr.getSelectedIndex());
                return true;
            }
            case 'S': {
                this.save(this.rmCurr.getSelectedIndex());
                return true;
            }
            case 'p': {
                this.print();
                return true;
            }
            case 'P': {
                this.print();
                return true;
            }
            default: {
                this.wC.keyDown(event, n);
                return true;
            }
        }
    }
    
    void createPrebuilt() {
        final String[] array = this.prebuiltHash.get(this.prebuilt.getSelectedItem());
        if (array != null) {
            final Webfurn addFurniture = this.wC.addFurniture(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7]);
            this.wC.mouseDown(this.actionEvent, addFurniture.getLeft() + 1, addFurniture.getTop() + 1);
        }
    }
    
    void updateFurn() {
        final Webfurn grabbedOne = this.wC.getGrabbedOne();
        if (grabbedOne != null) {
            if (this.fnShape.getSelectedItem().equals("Open Door")) {
                if (this.focussedComp.equals(this.fnLength)) {
                    this.fnWidth.setText(this.fnLength.getText());
                }
                else {
                    this.fnLength.setText(this.fnWidth.getText());
                }
            }
            if (this.furnitureOK()) {
                this.wC.setWHNCS(grabbedOne, this.toFeet(this.fnWidth.getText(), this.fnWunit.getSelectedItem()), this.toFeet(this.fnLength.getText(), this.fnLunit.getSelectedItem()), this.fnWunit.getSelectedItem(), this.fnLunit.getSelectedItem(), this.fnLabel.getText(), this.fnColor.getSelectedItem(), this.fnShape.getSelectedItem());
                if (this.fnShape.getSelectedItem().equals("Rectangular")) {
                    this.rAmt.select(2);
                    this.rAmt.enable();
                    this.rDir.select(0);
                    this.rDir.enable();
                }
                if (this.fnShape.getSelectedItem().equals("Oval")) {
                    this.rAmt.select(2);
                    this.rAmt.disable();
                    this.rDir.select(0);
                    this.rDir.disable();
                }
                if (this.fnShape.getSelectedItem().equals("Open Door")) {
                    this.rAmt.select(2);
                    this.rAmt.disable();
                    this.rDir.select(0);
                    this.rDir.enable();
                }
                if (this.fnShape.getSelectedItem().equals("Window")) {
                    this.rAmt.select(2);
                    this.rAmt.disable();
                    this.rDir.select(0);
                    this.rDir.disable();
                }
            }
        }
    }
    
    boolean NumericAndPositive(final TextField textField, final String s) {
        float floatValue = Float.NaN;
        boolean b = true;
        try {
            floatValue = Float.valueOf(textField.getText());
        }
        catch (NumberFormatException ex) {
            b = false;
        }
        if (!b || Math.max(floatValue, 0.0f) == 0) {
            if (textField.getText().equals("")) {
                textField.setText("###");
            }
            textField.selectAll();
            textField.requestFocus();
            this.showStatus(String.valueOf(String.valueOf("***** Input Error -- ").concat(String.valueOf(s))).concat(String.valueOf(" must be Numeric and Positive *****")));
            return false;
        }
        return true;
    }
    
    boolean fieldEntered(final TextField textField, final String s) {
        if (textField.getText().equals("")) {
            textField.setText("***");
            textField.selectAll();
            textField.requestFocus();
            this.showStatus(String.valueOf(String.valueOf("***** Input Error -- ").concat(String.valueOf(s))).concat(String.valueOf(" must be entered *****")));
            return false;
        }
        return true;
    }
    
    boolean furnitureOK() {
        if (!this.NumericAndPositive(this.fnWidth, "Furniture Width") || !this.NumericAndPositive(this.fnLength, "Furniture Length")) {
            return false;
        }
        if (this.fnShape.getSelectedItem().equals("Open Door")) {
            if (Double.valueOf(this.toFeet(this.fnLength.getText(), this.fnLunit.getSelectedItem())) > Double.valueOf(this.toFeet(this.fnWidth.getText(), this.fnWunit.getSelectedItem()))) {
                this.fnWidth.setText(this.fnLength.getText());
                this.fnWunit.select(this.fnLunit.getSelectedItem());
            }
            else {
                this.fnLength.setText(this.fnWidth.getText());
                this.fnLunit.select(this.fnWunit.getSelectedItem());
            }
        }
        if (this.fnShape.getSelectedItem().equals("Window")) {
            if (Double.valueOf(this.toFeet(this.fnLength.getText(), this.fnLunit.getSelectedItem())) > Double.valueOf(this.toFeet(this.fnWidth.getText(), this.fnWunit.getSelectedItem()))) {
                this.fnWidth.setText("0.5");
                this.fnWunit.select(0);
            }
            else {
                this.fnLength.setText("0.5");
                this.fnLunit.select(0);
            }
        }
        return true;
    }
    
    String toFeet(String s, final String s2) {
        if (s2.equals("Feet")) {
            return s;
        }
        double n = 0.0;
        final double doubleValue = Double.valueOf(s);
        if (s2.equals("Inches")) {
            n = doubleValue / 12.0;
        }
        if (s2.equals("Meters")) {
            n = doubleValue * 39.37 / 12.0;
        }
        final double n2 = Math.ceil(n * 10000) / 10000;
        if ((int)n2 == n2) {
            s = Integer.toString((int)n2);
        }
        else {
            final double n3 = Math.floor(n * 10000) / 10000;
            if ((int)n3 == n3) {
                s = Integer.toString((int)n3);
            }
            else {
                s = Double.toString(n3);
            }
        }
        return s;
    }
    
    String fromFeet(String s, final char c) {
        if (c == 'f') {
            return s;
        }
        double n = 0.0;
        final double doubleValue = Double.valueOf(s);
        if (c == 'i') {
            n = doubleValue * 12.0;
        }
        if (c == 'm') {
            n = doubleValue / 39.37 * 12.0;
        }
        final double n2 = Math.ceil(n * 100) / 100;
        if ((int)n2 == n2) {
            s = Integer.toString((int)n2);
        }
        else {
            final double n3 = Math.floor(n * 100) / 100;
            if ((int)n3 == n3) {
                s = Integer.toString((int)n3);
            }
            else {
                s = Double.toString(n3);
            }
        }
        return s;
    }
    
    char UtoChar(final String s) {
        char c = ' ';
        if (s.equals("Feet")) {
            c = 'f';
        }
        if (s.equals("Inches")) {
            c = 'i';
        }
        if (s.equals("Meters")) {
            c = 'm';
        }
        return c;
    }
    
    String UfromChar(final char c) {
        String s = null;
        if (c == 'f') {
            s = "Feet";
        }
        if (c == 'i') {
            s = "Inches";
        }
        if (c == 'm') {
            s = "Meters";
        }
        return s;
    }
    
    void loadPrebuiltHash() {
        this.prebuilt.addItem(" ");
        final String s = "Bed (Twin)";
        this.prebuilt.addItem(s);
        this.prebuiltHash.put(s, this.fnProperties("3.33", "6.2", "Feet", "Feet", s, "Blue", "Rectangular", "0"));
        final String s2 = "Bed (Full)";
        this.prebuilt.addItem(s2);
        this.prebuiltHash.put(s2, this.fnProperties("4.58", "6.25", "Feet", "Feet", s2, "Blue", "Rectangular", "0"));
        final String s3 = "Bed (Queen)";
        this.prebuilt.addItem(s3);
        this.prebuiltHash.put(s3, this.fnProperties("5.25", "6.67", "Feet", "Feet", s3, "Blue", "Rectangular", "0"));
        final String s4 = "Bed (King)";
        this.prebuilt.addItem(s4);
        this.prebuiltHash.put(s4, this.fnProperties("6.67", "7", "Feet", "Feet", s4, "Blue", "Rectangular", "0"));
        final String s5 = "Chair";
        this.prebuilt.addItem(s5);
        this.prebuiltHash.put(s5, this.fnProperties("2", "2.2", "Feet", "Feet", s5, "Green", "Rectangular", "0"));
        final String s6 = "Coffee Table";
        this.prebuilt.addItem(s6);
        this.prebuiltHash.put(s6, this.fnProperties("4", "2.5", "Feet", "Feet", s6, "Brown", "Rectangular", "0"));
        final String s7 = "Couch";
        this.prebuilt.addItem(s7);
        this.prebuiltHash.put(s7, this.fnProperties("6.5", "2.75", "Feet", "Feet", s7, "Light Blue", "Rectangular", "0"));
        final String s8 = "Desk";
        this.prebuilt.addItem(s8);
        this.prebuiltHash.put(s8, this.fnProperties("4", "2", "Feet", "Feet", s8, "Purple", "Rectangular", "0"));
        final String s9 = "Door";
        this.prebuilt.addItem(s9);
        this.prebuiltHash.put(s9, this.fnProperties("2.67", "2.67", "Feet", "Feet", "", "Black", "Open Door", "0"));
        final String s10 = "Dresser";
        this.prebuilt.addItem(s10);
        this.prebuiltHash.put(s10, this.fnProperties("2", "1.5", "Feet", "Feet", s10, "Red", "Rectangular", "0"));
        final String s11 = "End Table";
        this.prebuilt.addItem(s11);
        this.prebuiltHash.put(s11, this.fnProperties("2", "2", "Feet", "Feet", s11, "Tan", "Rectangular", "0"));
        final String s12 = "Fridge";
        this.prebuilt.addItem(s12);
        this.prebuiltHash.put(s12, this.fnProperties("2", "2.5", "Feet", "Feet", s12, "Orange", "Rectangular", "0"));
        final String s13 = "Kitchen Table";
        this.prebuilt.addItem(s13);
        this.prebuiltHash.put(s13, this.fnProperties("3", "3", "Feet", "Feet", s13, "Yellow", "Oval", "0"));
        final String s14 = "Nightstand";
        this.prebuilt.addItem(s14);
        this.prebuiltHash.put(s14, this.fnProperties("1.5", "1.5", "Feet", "Feet", s14, "Light Blue", "Rectangular", "0"));
        final String s15 = "Stove";
        this.prebuilt.addItem(s15);
        this.prebuiltHash.put(s15, this.fnProperties("1.75", "2.5", "Feet", "Feet", s15, "Black", "Rectangular", "0"));
        final String s16 = "TV";
        this.prebuilt.addItem(s16);
        this.prebuiltHash.put(s16, this.fnProperties("2", "1.67", "Feet", "Feet", s16, "Black", "Rectangular", "0"));
        final String s17 = "Window";
        this.prebuilt.addItem(s17);
        this.prebuiltHash.put(s17, this.fnProperties("2.67", "0.5", "Feet", "Feet", "", "Black", "Window", "0"));
    }
    
    String[] fnProperties(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) {
        return new String[] { s, s2, s3, s4, s5, s6, s7, s8 };
    }
    
    public void showStatus(final String s) {
        this.getAppletContext().showStatus(s);
    }
}
