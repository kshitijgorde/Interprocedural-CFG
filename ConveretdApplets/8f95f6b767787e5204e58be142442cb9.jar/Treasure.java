import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.Label;
import java.awt.CheckboxGroup;
import java.awt.FontMetrics;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Checkbox;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Treasure extends Applet
{
    boolean[][][] bConnected;
    boolean bDirectionFound;
    boolean[][][] bDirectionUsed;
    boolean bEuclidean;
    boolean bPathFound;
    boolean[] bRoomUsed;
    boolean bTreasureCarried;
    boolean[] bVisited;
    boolean bWeaponRoomFound;
    boolean bWidthsFound;
    Button buttonAbout;
    Button buttonAboutOk;
    Button buttonBackward;
    Button buttonCarry;
    Button buttonDown;
    Button buttonDropTreasures;
    Button buttonEast;
    Button buttonForward;
    Button buttonNorth;
    Button buttonOk;
    Button buttonSouth;
    Button buttonUp;
    Button buttonWayOut;
    Button buttonWest;
    Checkbox check2D;
    Checkbox check3D;
    Checkbox check4D;
    Checkbox checkEuclidean;
    TextArea editAbout;
    TextArea editInventory;
    TextArea editLocation;
    TextField editGameNumber;
    TextField editMaxScore;
    TextField editMoves;
    TextField editNumRooms;
    TextField editNumTreasures;
    TextField editRoomsVisited;
    TextField editScore;
    TextField editTreasuresRecovered;
    FontMetrics fmAbout;
    FontMetrics fmTreasure;
    CheckboxGroup groupNumDim;
    Label label1;
    Label labelGameNumber;
    Label labelInventory;
    Label labelLocation;
    Label labelMaxScore;
    Label labelMoves;
    Label labelNumRooms;
    Label labelNumTreasures;
    Label labelRoomsVisited;
    Label labelScore;
    Label labelTitle;
    Label labelTreasuresRecovered;
    int nAddIndex;
    int[][][][] nCell;
    int[] nCoordinate;
    int[] nCoordinateNext;
    int nDimensions;
    int nDimension1;
    int nDimension2;
    int nDirection1;
    int nDirection2;
    int nDirectionsPossible;
    int[] nDirectionsUsed;
    long nGame;
    int[] nGuardRoom;
    int nMaxWidth;
    int nMoves;
    int nPreviousRandomNumber;
    int nReplaceIndex;
    int[] nRN;
    int nRNPartialSum;
    int nRoom1;
    int nRoom2;
    int nRooms;
    int nScore;
    int nTCoordinate;
    int[] nTreasureRoom;
    int nTreasures;
    int nTreasuresCarried;
    int nTreasuresRecovered;
    int nTreasure1;
    int nTreasure2;
    int nTrial;
    int nVisited;
    int nVolume;
    int[] nWayOutDimension;
    int[] nWayOutDirection;
    int nWayOutHead;
    int nWayOutPtr;
    int[] nWeaponRoom;
    int[] nWidth;
    int nXCoordinate;
    int nYCoordinate;
    int nZCoordinate;
    Panel panelNumDimensions;
    String strAbout;
    String[] strDescription;
    String strGameNumber;
    String[] strGuard;
    String[] strTreasure;
    String[] strWeapon;
    StringBuffer sbInventory;
    StringBuffer sbLine;
    StringBuffer sbLocation;
    StringBuffer sbPendingMsg;
    StringBuffer sbTreasures;
    StringBuffer sbWayOut;
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        (this.panelNumDimensions = new Panel()).setLayout(null);
        this.add(this.panelNumDimensions);
        this.panelNumDimensions.reshape(45, 112, 180, 48);
        this.groupNumDim = new CheckboxGroup();
        this.add(this.labelGameNumber = new Label("Game number"));
        this.labelGameNumber.reshape(18, 40, 117, 16);
        (this.editGameNumber = new TextField(10)).setText("1");
        this.add(this.editGameNumber);
        this.editGameNumber.reshape(153, 32, 108, 24);
        this.check2D = new Checkbox("2", this.groupNumDim, false);
        this.panelNumDimensions.add(this.check2D);
        this.check2D.reshape(18, 16, 36, 24);
        this.check3D = new Checkbox("3", this.groupNumDim, true);
        this.panelNumDimensions.add(this.check3D);
        this.check3D.reshape(72, 16, 36, 24);
        this.check4D = new Checkbox("4", this.groupNumDim, false);
        this.panelNumDimensions.add(this.check4D);
        this.check4D.reshape(126, 16, 36, 24);
        this.add(this.label1 = new Label("Number of Dimensions"));
        this.label1.reshape(45, 80, 180, 16);
        (this.checkEuclidean = new Checkbox("Euclidean space")).setState(true);
        this.add(this.checkEuclidean);
        this.checkEuclidean.reshape(45, 184, 144, 24);
        this.add(this.buttonOk = new Button("Ok"));
        this.buttonOk.reshape(108, 232, 63, 24);
        (this.editAbout = new TextArea(9, 36)).hide();
        this.editAbout.setEditable(false);
        this.add(this.editAbout);
        this.editAbout.reshape(this.insets().left + 9, this.insets().top + 8, 369, 192);
        (this.buttonAboutOk = new Button("Ok")).hide();
        this.add(this.buttonAboutOk);
        this.buttonAboutOk.reshape(108, 232, 63, 24);
        (this.buttonWest = new Button("West")).hide();
        this.add(this.buttonWest);
        this.buttonWest.reshape(this.insets().left + 279, this.insets().top + 200, 99, 16);
        (this.buttonSouth = new Button("South")).hide();
        this.add(this.buttonSouth);
        this.buttonSouth.reshape(this.insets().left + 387, this.insets().top + 216, 90, 16);
        (this.buttonNorth = new Button("North")).hide();
        this.add(this.buttonNorth);
        this.buttonNorth.reshape(this.insets().left + 387, this.insets().top + 184, 90, 16);
        (this.buttonEast = new Button("East")).hide();
        this.add(this.buttonEast);
        this.buttonEast.reshape(this.insets().left + 486, this.insets().top + 200, 99, 16);
        (this.buttonUp = new Button("Up")).hide();
        this.add(this.buttonUp);
        this.buttonUp.reshape(this.insets().left + 486, this.insets().top + 168, 99, 16);
        (this.buttonDown = new Button("Down")).hide();
        this.add(this.buttonDown);
        this.buttonDown.reshape(this.insets().left + 279, this.insets().top + 232, 99, 16);
        (this.editLocation = new TextArea(6, 22)).setEditable(false);
        this.editLocation.hide();
        this.add(this.editLocation);
        this.editLocation.reshape(this.insets().left + 9, this.insets().top + 56, 225, 136);
        (this.buttonForward = new Button("Forward")).hide();
        this.add(this.buttonForward);
        this.buttonForward.reshape(this.insets().left + 279, this.insets().top + 168, 99, 16);
        (this.buttonBackward = new Button("Backward")).hide();
        this.add(this.buttonBackward);
        this.buttonBackward.reshape(this.insets().left + 486, this.insets().top + 232, 99, 16);
        (this.buttonCarry = new Button("Carry")).hide();
        this.add(this.buttonCarry);
        this.buttonCarry.reshape(this.insets().left + 252, this.insets().top + 272, 117, 16);
        (this.buttonDropTreasures = new Button("Drop Treasures")).hide();
        this.add(this.buttonDropTreasures);
        this.buttonDropTreasures.reshape(this.insets().left + 495, this.insets().top + 272, 117, 16);
        (this.buttonWayOut = new Button("Way Out")).hide();
        this.add(this.buttonWayOut);
        this.buttonWayOut.reshape(this.insets().left + 387, this.insets().top + 280, 90, 16);
        (this.labelTitle = new Label("Visit all of the rooms and return all of the treasures to the entrance.")).hide();
        this.add(this.labelTitle);
        this.labelTitle.reshape(this.insets().left + 9, this.insets().top + 8, 495, 16);
        (this.labelMoves = new Label("Moves", 2)).hide();
        this.add(this.labelMoves);
        this.labelMoves.reshape(this.insets().left + 315, this.insets().top + 40, 90, 16);
        (this.labelRoomsVisited = new Label("Rooms visited", 2)).hide();
        this.add(this.labelRoomsVisited);
        this.labelRoomsVisited.reshape(this.insets().left + 288, this.insets().top + 72, 117, 16);
        (this.labelScore = new Label("Score", 2)).hide();
        this.add(this.labelScore);
        this.labelScore.reshape(this.insets().left + 315, this.insets().top + 136, 90, 16);
        (this.labelTreasuresRecovered = new Label("Treasures recovered", 2)).hide();
        this.add(this.labelTreasuresRecovered);
        this.labelTreasuresRecovered.reshape(this.insets().left + 243, this.insets().top + 104, 162, 16);
        (this.editRoomsVisited = new TextField(8)).setEditable(false);
        this.editRoomsVisited.hide();
        this.add(this.editRoomsVisited);
        this.editRoomsVisited.reshape(this.insets().left + 414, this.insets().top + 64, 81, 24);
        (this.editTreasuresRecovered = new TextField(8)).setEditable(false);
        this.editTreasuresRecovered.hide();
        this.add(this.editTreasuresRecovered);
        this.editTreasuresRecovered.reshape(this.insets().left + 414, this.insets().top + 96, 81, 24);
        (this.editScore = new TextField(8)).setEditable(false);
        this.editScore.hide();
        this.add(this.editScore);
        this.editScore.reshape(this.insets().left + 414, this.insets().top + 128, 81, 24);
        (this.editMoves = new TextField(8)).setEditable(false);
        this.editMoves.hide();
        this.add(this.editMoves);
        this.editMoves.reshape(this.insets().left + 414, this.insets().top + 32, 81, 24);
        (this.labelLocation = new Label("Location")).hide();
        this.add(this.labelLocation);
        this.labelLocation.reshape(this.insets().left + 9, this.insets().top + 32, 90, 16);
        (this.labelInventory = new Label("Inventory")).hide();
        this.add(this.labelInventory);
        this.labelInventory.reshape(this.insets().left + 9, this.insets().top + 200, 90, 16);
        (this.editInventory = new TextArea(4, 22)).setEditable(false);
        this.editInventory.hide();
        this.add(this.editInventory);
        this.editInventory.reshape(this.insets().left + 9, this.insets().top + 224, 225, 80);
        (this.labelNumRooms = new Label("of")).hide();
        this.add(this.labelNumRooms);
        this.labelNumRooms.reshape(this.insets().left + 504, this.insets().top + 72, 36, 16);
        (this.editNumRooms = new TextField(8)).setEditable(false);
        this.editNumRooms.hide();
        this.add(this.editNumRooms);
        this.editNumRooms.reshape(this.insets().left + 549, this.insets().top + 64, 81, 24);
        (this.labelNumTreasures = new Label("of")).hide();
        this.add(this.labelNumTreasures);
        this.labelNumTreasures.reshape(this.insets().left + 504, this.insets().top + 104, 36, 16);
        (this.editNumTreasures = new TextField(8)).setEditable(false);
        this.editNumTreasures.hide();
        this.add(this.editNumTreasures);
        this.editNumTreasures.reshape(this.insets().left + 549, this.insets().top + 96, 81, 24);
        (this.labelMaxScore = new Label("of")).hide();
        this.add(this.labelMaxScore);
        this.labelMaxScore.reshape(this.insets().left + 504, this.insets().top + 136, 36, 16);
        (this.editMaxScore = new TextField(8)).setEditable(false);
        this.editMaxScore.hide();
        this.add(this.editMaxScore);
        this.editMaxScore.reshape(this.insets().left + 549, this.insets().top + 128, 81, 24);
        (this.buttonAbout = new Button("About")).hide();
        this.add(this.buttonAbout);
        this.buttonAbout.reshape(this.insets().left + 531, this.insets().top + 24, 90, 16);
        this.resize(this.insets().left + this.insets().right + 632, this.insets().top + this.insets().bottom + 305);
        super.init();
        this.panelNumDimensions.hide();
        this.labelGameNumber.hide();
        this.editGameNumber.hide();
        this.check2D.hide();
        this.check3D.hide();
        this.check4D.hide();
        this.label1.hide();
        this.checkEuclidean.hide();
        this.buttonOk.hide();
        this.showGame();
        this.hideGame();
        this.buttonAboutOk.show();
        this.editAbout.show();
        this.buttonAboutOk.hide();
        this.editAbout.hide();
        this.panelNumDimensions.show();
        this.labelGameNumber.show();
        this.editGameNumber.show();
        this.check2D.show();
        this.check3D.show();
        this.check4D.show();
        this.label1.show();
        this.checkEuclidean.show();
        this.buttonOk.show();
        this.fmTreasure = this.getFontMetrics(this.editLocation.getFont());
        this.sbWayOut = new StringBuffer(256);
        this.sbPendingMsg = new StringBuffer(256);
        this.sbTreasures = new StringBuffer(1024);
        this.sbInventory = new StringBuffer(1024);
        this.sbLocation = new StringBuffer(3072);
        this.nGame = 1L;
        this.nDimensions = 4;
        this.bEuclidean = true;
        this.nRooms = 100;
        this.nRoom1 = 0;
        while (this.nRoom1 < this.nRooms) {
            this.bVisited[this.nRoom1] = false;
            this.nDimension1 = 0;
            while (this.nDimension1 < this.nDimensions) {
                this.nDirection1 = 0;
                while (this.nDirection1 < 2) {
                    this.bConnected[this.nRoom1][this.nDimension1][this.nDirection1] = false;
                    ++this.nDirection1;
                }
                ++this.nDimension1;
            }
            ++this.nRoom1;
        }
        this.nTreasures = 15;
    }
    
    public void showGame() {
        this.buttonWest.show();
        this.buttonSouth.show();
        this.buttonNorth.show();
        this.buttonEast.show();
        this.buttonUp.show();
        this.buttonDown.show();
        this.editLocation.show();
        this.buttonForward.show();
        this.buttonBackward.show();
        this.buttonCarry.show();
        this.buttonDropTreasures.show();
        this.buttonWayOut.show();
        this.labelTitle.show();
        this.labelMoves.show();
        this.labelRoomsVisited.show();
        this.labelScore.show();
        this.labelTreasuresRecovered.show();
        this.editRoomsVisited.show();
        this.editTreasuresRecovered.show();
        this.editScore.show();
        this.editMoves.show();
        this.labelLocation.show();
        this.labelInventory.show();
        this.editInventory.show();
        this.labelNumRooms.show();
        this.editNumRooms.show();
        this.labelNumTreasures.show();
        this.editNumTreasures.show();
        this.labelMaxScore.show();
        this.editMaxScore.show();
        this.buttonAbout.show();
    }
    
    public void clickedButtonOk() {
        if (this.check2D.getState()) {
            this.startGame(this.editGameNumber.getText(), this.checkEuclidean.getState(), 2);
        }
        else if (this.check3D.getState()) {
            this.startGame(this.editGameNumber.getText(), this.checkEuclidean.getState(), 3);
        }
        else {
            this.startGame(this.editGameNumber.getText(), this.checkEuclidean.getState(), 4);
        }
        this.panelNumDimensions.hide();
        this.labelGameNumber.hide();
        this.editGameNumber.hide();
        this.check2D.hide();
        this.check3D.hide();
        this.check4D.hide();
        this.label1.hide();
        this.checkEuclidean.hide();
        this.buttonOk.hide();
        this.showGame();
    }
    
    public void clickedButtonAboutOk() {
        this.buttonAboutOk.hide();
        this.editAbout.hide();
        this.showGame();
    }
    
    public synchronized void startGame(final String s, final boolean bEuclidean, final int n) {
        this.nDimensions = n;
        this.bEuclidean = bEuclidean;
        int length;
        int i;
        for (length = s.length(), i = 0; i < length && s.charAt(i) == ' '; ++i) {}
        int n2;
        for (n2 = length - 1; n2 >= 0 && s.charAt(n2) == ' '; --n2) {}
        int j;
        for (j = 0; i <= n2; this.nRN[j++] = '\u0001' + s.charAt(i++)) {
            if (j >= 8) {
                break;
            }
        }
        while (j < 8) {
            this.nRN[j++] = 16411;
        }
        this.nRNPartialSum = 0;
        for (int k = 7; k > 0; --k) {
            this.nRNPartialSum += this.nRN[k];
            if (this.nRNPartialSum >= 32771) {
                this.nRNPartialSum -= 32771;
            }
        }
        this.nReplaceIndex = 1;
        this.nAddIndex = 0;
        this.nMaxWidth = 1 + (int)Math.exp(Math.log(2 * this.nRooms) / n);
        this.bWidthsFound = false;
        while (!this.bWidthsFound) {
            this.nDimension1 = 0;
            this.nVolume = 1;
            while (this.nDimension1 < n) {
                this.nWidth[this.nDimension1] = this.nMaxWidth - this.nRandomNumber() % 2;
                this.nVolume *= this.nWidth[this.nDimension1++];
            }
            if (this.nVolume > this.nRooms) {
                this.bWidthsFound = true;
            }
        }
        this.nDimension1 = n;
        while (this.nDimension1 < 4) {
            this.nWidth[this.nDimension1++] = 1;
        }
        this.nRoom1 = 1;
        while (this.nRoom1 < this.nRooms) {
            this.nRoom2 = 1 + this.nRandomNumber() % (this.nRooms - 1);
            final String s2 = this.strDescription[this.nRoom1];
            this.strDescription[this.nRoom1++] = this.strDescription[this.nRoom2];
            this.strDescription[this.nRoom2] = s2;
        }
        this.nXCoordinate = 0;
        while (this.nXCoordinate < this.nWidth[0]) {
            this.nYCoordinate = 0;
            while (this.nYCoordinate < this.nWidth[1]) {
                this.nZCoordinate = 0;
                while (this.nZCoordinate < this.nWidth[2]) {
                    this.nTCoordinate = 0;
                    while (this.nTCoordinate < this.nWidth[3]) {
                        this.nCell[this.nXCoordinate][this.nYCoordinate][this.nZCoordinate][this.nTCoordinate++] = -1;
                    }
                    ++this.nZCoordinate;
                }
                ++this.nYCoordinate;
            }
            ++this.nXCoordinate;
        }
        this.nCoordinate[0] = 0;
        this.nCoordinate[1] = 0;
        this.nCoordinate[2] = 0;
        this.nCoordinate[3] = 0;
        this.nRoom1 = 0;
        this.nRoom2 = 0;
        this.nCell[0][0][0][0] = 0;
        while (this.nRoom1 < this.nRooms - 1) {
            this.bDirectionFound = false;
            while (!this.bDirectionFound) {
                this.nDirection1 = this.nRandomNumber() % 2;
                this.nDimension1 = this.nRandomNumber() % n;
                if (bEuclidean) {
                    if (this.nCoordinate[this.nDimension1] + 2 * this.nDirection1 - 1 < 0 || this.nCoordinate[this.nDimension1] + 2 * this.nDirection1 - 1 >= this.nWidth[this.nDimension1]) {
                        continue;
                    }
                    this.bDirectionFound = true;
                }
                else {
                    this.bDirectionFound = true;
                }
            }
            this.bConnected[this.nRoom2][this.nDimension1][this.nDirection1] = true;
            this.nCoordinateNext[0] = this.nCoordinate[0];
            this.nCoordinateNext[1] = this.nCoordinate[1];
            this.nCoordinateNext[2] = this.nCoordinate[2];
            this.nCoordinateNext[3] = this.nCoordinate[3];
            this.nCoordinateNext[this.nDimension1] = this.nCoordinate[this.nDimension1] + 2 * this.nDirection1 - 1;
            if (!bEuclidean) {
                if (this.nCoordinateNext[this.nDimension1] < 0) {
                    this.nDimension2 = 0;
                    while (this.nDimension2 < n) {
                        this.nCoordinateNext[this.nDimension2] = this.nWidth[this.nDimension2] - this.nCoordinateNext[this.nDimension2] - 1;
                        ++this.nDimension2;
                    }
                    this.nCoordinateNext[this.nDimension1] = this.nWidth[this.nDimension1] - 1;
                }
                else if (this.nCoordinateNext[this.nDimension1] >= this.nWidth[this.nDimension1]) {
                    this.nDimension2 = 0;
                    while (this.nDimension2 < n) {
                        this.nCoordinateNext[this.nDimension2] = this.nWidth[this.nDimension2] - this.nCoordinateNext[this.nDimension2] - 1;
                        ++this.nDimension2;
                    }
                    this.nCoordinateNext[this.nDimension1] = 0;
                }
            }
            if (this.nCell[this.nCoordinateNext[0]][this.nCoordinateNext[1]][this.nCoordinateNext[2]][this.nCoordinateNext[3]] < 0) {
                this.nCell[this.nCoordinateNext[0]][this.nCoordinateNext[1]][this.nCoordinateNext[2]][this.nCoordinateNext[3]] = ++this.nRoom1;
            }
            this.nRoom2 = this.nCell[this.nCoordinateNext[0]][this.nCoordinateNext[1]][this.nCoordinateNext[2]][this.nCoordinateNext[3]];
            this.bConnected[this.nRoom2][this.nDimension1][1 - this.nDirection1] = true;
            this.nCoordinate[0] = this.nCoordinateNext[0];
            this.nCoordinate[1] = this.nCoordinateNext[1];
            this.nCoordinate[2] = this.nCoordinateNext[2];
            this.nCoordinate[3] = this.nCoordinateNext[3];
        }
        this.nTreasure1 = 0;
        while (this.nTreasure1 < this.nTreasures) {
            this.nTreasureRoom[this.nTreasure1] = 1 + this.nRandomNumber() % (this.nRooms - 1);
            this.nGuardRoom[this.nTreasure1] = this.nTreasureRoom[this.nTreasure1];
            this.bWeaponRoomFound = false;
            while (!this.bWeaponRoomFound) {
                this.nWeaponRoom[this.nTreasure1] = 1 + this.nRandomNumber() % (this.nRooms - 1);
                if (this.nWeaponRoom[this.nTreasure1] != this.nTreasureRoom[this.nTreasure1]) {
                    this.bWeaponRoomFound = true;
                }
            }
            ++this.nTreasure1;
        }
        this.nMoves = 0;
        this.nVisited = 0;
        this.nXCoordinate = 0;
        this.nYCoordinate = 0;
        this.nZCoordinate = 0;
        this.nTCoordinate = 0;
        this.sbWayOut.setLength(0);
        this.sbPendingMsg.setLength(0);
        this.updateScreen();
        this.enable();
        this.requestFocus();
    }
    
    public boolean handleEvent(final Event event) {
        boolean handleEvent = true;
        if (event.id == 1001 && event.target == this.buttonAboutOk) {
            this.clickedButtonAboutOk();
        }
        else if (event.id == 1001 && event.target == this.buttonOk) {
            this.clickedButtonOk();
        }
        else if (event.id != 1001 || event.target != this.checkEuclidean) {
            if (event.id == 1001 && event.target == this.buttonAbout) {
                this.clickedButtonAbout();
            }
            else if (event.id == 1001 && event.target == this.buttonWayOut) {
                this.clickedButtonWayOut();
            }
            else if (event.id == 1001 && event.target == this.buttonDropTreasures) {
                this.clickedButtonDropTreasures();
            }
            else if (event.id == 1001 && event.target == this.buttonCarry) {
                this.clickedButtonCarry();
            }
            else if (event.id == 1001 && event.target == this.buttonBackward) {
                this.clickedButtonBackward();
            }
            else if (event.id == 1001 && event.target == this.buttonDown) {
                this.clickedButtonDown();
            }
            else if (event.id == 1001 && event.target == this.buttonSouth) {
                this.clickedButtonSouth();
            }
            else if (event.id == 1001 && event.target == this.buttonEast) {
                this.clickedButtonEast();
            }
            else if (event.id == 1001 && event.target == this.buttonWest) {
                this.clickedButtonWest();
            }
            else if (event.id == 1001 && event.target == this.buttonNorth) {
                this.clickedButtonNorth();
            }
            else if (event.id == 1001 && event.target == this.buttonForward) {
                this.clickedButtonForward();
            }
            else if (event.id == 1001 && event.target == this.buttonUp) {
                this.clickedButtonUp();
            }
            else {
                handleEvent = super.handleEvent(event);
            }
        }
        return handleEvent;
    }
    
    public void clickedButtonUp() {
        ++this.nMoves;
        --this.nZCoordinate;
        this.sbPendingMsg.setLength(0);
        this.updateScreen();
    }
    
    public void clickedButtonForward() {
        ++this.nMoves;
        --this.nTCoordinate;
        this.sbPendingMsg.setLength(0);
        this.updateScreen();
    }
    
    public void clickedButtonNorth() {
        ++this.nMoves;
        --this.nXCoordinate;
        this.sbPendingMsg.setLength(0);
        this.updateScreen();
    }
    
    public void clickedButtonWest() {
        ++this.nMoves;
        ++this.nYCoordinate;
        this.sbPendingMsg.setLength(0);
        this.updateScreen();
    }
    
    public void clickedButtonEast() {
        ++this.nMoves;
        --this.nYCoordinate;
        this.sbPendingMsg.setLength(0);
        this.updateScreen();
    }
    
    public void clickedButtonSouth() {
        ++this.nMoves;
        ++this.nXCoordinate;
        this.sbPendingMsg.setLength(0);
        this.updateScreen();
    }
    
    public void clickedButtonDown() {
        ++this.nMoves;
        ++this.nZCoordinate;
        this.sbPendingMsg.setLength(0);
        this.updateScreen();
    }
    
    public void clickedButtonBackward() {
        ++this.nMoves;
        ++this.nTCoordinate;
        this.sbPendingMsg.setLength(0);
        this.updateScreen();
    }
    
    public void clickedButtonCarry() {
        this.sbPendingMsg.setLength(0);
        this.nTreasure1 = 0;
        while (this.nTreasure1 < this.nTreasures) {
            if (this.nWeaponRoom[this.nTreasure1] == this.nRoom1) {
                this.nWeaponRoom[this.nTreasure1] = -1;
            }
            ++this.nTreasure1;
        }
        this.nTreasure1 = 0;
        while (this.nTreasure1 < this.nTreasures) {
            if (this.nTreasureRoom[this.nTreasure1] == this.nRoom1) {
                if (this.nWeaponRoom[this.nTreasure1] < 0) {
                    this.nTreasureRoom[this.nTreasure1] = -1;
                    ++this.nTreasuresRecovered;
                    if (this.nGuardRoom[this.nTreasure1] == this.nRoom1) {
                        this.nGuardRoom[this.nTreasure1] = -1;
                        this.nWeaponRoom[this.nTreasure1] = -2;
                        this.sbPendingMsg.append("You're ");
                        this.sbPendingMsg.append(this.strWeapon[this.nTreasure1]);
                        this.sbPendingMsg.append(" overcomes the ");
                        this.sbPendingMsg.append(this.strGuard[this.nTreasure1]);
                        this.sbPendingMsg.append(".\n");
                    }
                }
                else {
                    this.sbPendingMsg.append("You carry nothing to overcome the ");
                    this.sbPendingMsg.append(this.strGuard[this.nTreasure1]);
                    this.sbPendingMsg.append(".\n");
                }
            }
            if (this.nWeaponRoom[this.nTreasure1] == this.nRoom1) {
                this.nWeaponRoom[this.nTreasure1] = -1;
            }
            ++this.nTreasure1;
        }
        this.updateScreen();
    }
    
    public void clickedButtonDropTreasures() {
        this.sbPendingMsg.setLength(0);
        this.nTreasure1 = 0;
        while (this.nTreasure1 < this.nTreasures) {
            if (this.nTreasureRoom[this.nTreasure1] == -1) {
                this.nTreasureRoom[this.nTreasure1] = 0;
            }
            ++this.nTreasure1;
        }
        this.updateScreen();
    }
    
    public void clickedButtonWayOut() {
        this.sbPendingMsg.setLength(0);
        this.bPathFound = false;
        if (this.bTreasureCarried && this.nRoom1 != 0) {
            this.nCoordinate[0] = this.nXCoordinate;
            this.nCoordinate[1] = this.nYCoordinate;
            this.nCoordinate[2] = this.nZCoordinate;
            this.nCoordinate[3] = this.nTCoordinate;
            this.nWayOutHead = 0;
            this.nRoom2 = 0;
            while (this.nRoom2 < this.nRooms) {
                this.bRoomUsed[this.nRoom2++] = false;
            }
            this.bRoomUsed[this.nRoom1] = true;
            this.nDirectionsUsed[this.nWayOutHead] = 0;
            this.nDirectionsPossible = 2 * this.nDimensions;
            this.nDimension1 = 0;
            while (this.nDimension1 < this.nDimensions) {
                this.nDirection1 = 0;
                while (this.nDirection1 < 2) {
                    this.bDirectionUsed[this.nWayOutHead][this.nDimension1][this.nDirection1++] = false;
                }
                ++this.nDimension1;
            }
            this.sbWayOut.setLength(0);
            this.nRoom2 = this.nRoom1;
            this.nTrial = 0;
            while (this.nTrial < 500 && this.nRoom2 != 0 && this.nWayOutHead < 255) {
                ++this.nTrial;
                this.bDirectionFound = false;
                while (!this.bDirectionFound && this.nDirectionsUsed[this.nWayOutHead] < this.nDirectionsPossible) {
                    this.nDirection1 = this.nRandomNumber() % 2;
                    this.nDimension1 = this.nRandomNumber() % this.nDimensions;
                    if (!this.bDirectionUsed[this.nWayOutHead][this.nDimension1][this.nDirection1]) {
                        this.bDirectionUsed[this.nWayOutHead][this.nDimension1][this.nDirection1] = true;
                        ++this.nDirectionsUsed[this.nWayOutHead];
                        if (!this.bConnected[this.nRoom2][this.nDimension1][this.nDirection1]) {
                            continue;
                        }
                        this.nCoordinateNext[0] = this.nCoordinate[0];
                        this.nCoordinateNext[1] = this.nCoordinate[1];
                        this.nCoordinateNext[2] = this.nCoordinate[2];
                        this.nCoordinateNext[3] = this.nCoordinate[3];
                        this.nCoordinateNext[this.nDimension1] = this.nCoordinate[this.nDimension1] + 2 * this.nDirection1 - 1;
                        if (!this.bEuclidean) {
                            if (this.nCoordinateNext[this.nDimension1] < 0) {
                                this.nDimension2 = 0;
                                while (this.nDimension2 < this.nDimensions) {
                                    this.nCoordinateNext[this.nDimension2] = this.nWidth[this.nDimension2] - this.nCoordinateNext[this.nDimension2] - 1;
                                    ++this.nDimension2;
                                }
                                this.nCoordinateNext[this.nDimension1] = this.nWidth[this.nDimension1] - 1;
                            }
                            else if (this.nCoordinateNext[this.nDimension1] >= this.nWidth[this.nDimension1]) {
                                this.nDimension2 = 0;
                                while (this.nDimension2 < this.nDimensions) {
                                    this.nCoordinateNext[this.nDimension2] = this.nWidth[this.nDimension2] - this.nCoordinateNext[this.nDimension2] - 1;
                                    ++this.nDimension2;
                                }
                                this.nCoordinateNext[this.nDimension1] = 0;
                            }
                        }
                        if (this.bRoomUsed[this.nCell[this.nCoordinateNext[0]][this.nCoordinateNext[1]][this.nCoordinateNext[2]][this.nCoordinateNext[3]]]) {
                            continue;
                        }
                        this.bDirectionFound = true;
                    }
                }
                if (this.bDirectionFound) {
                    this.nRoom2 = this.nCell[this.nCoordinateNext[0]][this.nCoordinateNext[1]][this.nCoordinateNext[2]][this.nCoordinateNext[3]];
                    this.bRoomUsed[this.nRoom2] = true;
                    this.nDirectionsUsed[++this.nWayOutHead] = 0;
                    this.nDimension2 = 0;
                    while (this.nDimension2 < this.nDimensions) {
                        this.nDirection2 = 0;
                        while (this.nDirection2 < 2) {
                            this.bDirectionUsed[this.nWayOutHead][this.nDimension2][this.nDirection2++] = false;
                        }
                        ++this.nDimension2;
                    }
                    this.nWayOutDimension[this.nWayOutHead] = this.nDimension1;
                    this.nWayOutDirection[this.nWayOutHead] = 1 - this.nDirection1;
                    switch (this.nDimension1) {
                        case 0: {
                            if (this.nDirection1 == 0) {
                                this.sbWayOut.append("N");
                                break;
                            }
                            this.sbWayOut.append("S");
                            break;
                        }
                        case 1: {
                            if (this.nDirection1 == 0) {
                                this.sbWayOut.append("E");
                                break;
                            }
                            this.sbWayOut.append("W");
                            break;
                        }
                        case 2: {
                            if (this.nDirection1 == 0) {
                                this.sbWayOut.append("U");
                                break;
                            }
                            this.sbWayOut.append("D");
                            break;
                        }
                        default: {
                            if (this.nDirection1 == 0) {
                                this.sbWayOut.append("F");
                                break;
                            }
                            this.sbWayOut.append("B");
                            break;
                        }
                    }
                }
                else {
                    this.nDirection1 = this.nWayOutDirection[this.nWayOutHead];
                    this.nDimension1 = this.nWayOutDimension[this.nWayOutHead];
                    this.nCoordinateNext[0] = this.nCoordinate[0];
                    this.nCoordinateNext[1] = this.nCoordinate[1];
                    this.nCoordinateNext[2] = this.nCoordinate[2];
                    this.nCoordinateNext[3] = this.nCoordinate[3];
                    this.nCoordinateNext[this.nDimension1] = this.nCoordinateNext[this.nDimension1] + 2 * this.nDirection1 - 1;
                    if (!this.bEuclidean) {
                        if (this.nCoordinateNext[this.nDimension1] < 0) {
                            this.nDimension2 = 0;
                            while (this.nDimension2 < this.nDimensions) {
                                this.nCoordinateNext[this.nDimension2] = this.nWidth[this.nDimension2] - this.nCoordinateNext[this.nDimension2] - 1;
                                ++this.nDimension2;
                            }
                            this.nCoordinateNext[this.nDimension1] = this.nWidth[this.nDimension1] - 1;
                        }
                        else if (this.nCoordinateNext[this.nDimension1] >= this.nWidth[this.nDimension1]) {
                            this.nDimension2 = 0;
                            while (this.nDimension2 < this.nDimensions) {
                                this.nCoordinateNext[this.nDimension2] = this.nWidth[this.nDimension2] - this.nCoordinateNext[this.nDimension2] - 1;
                                ++this.nDimension2;
                            }
                            this.nCoordinateNext[this.nDimension1] = 0;
                        }
                    }
                    this.nRoom2 = this.nCell[this.nCoordinateNext[0]][this.nCoordinateNext[1]][this.nCoordinateNext[2]][this.nCoordinateNext[3]];
                    --this.nWayOutHead;
                    this.sbWayOut.setLength(this.sbWayOut.length() - 1);
                }
                this.nCoordinate[0] = this.nCoordinateNext[0];
                this.nCoordinate[1] = this.nCoordinateNext[1];
                this.nCoordinate[2] = this.nCoordinateNext[2];
                this.nCoordinate[3] = this.nCoordinateNext[3];
            }
            if (this.nRoom2 == 0) {
                this.bPathFound = true;
            }
        }
        if (this.bPathFound) {
            this.nTreasure1 = 0;
            this.nRoom2 = 0;
            while (this.nTreasure1 < this.nTreasures && this.nRoom2 >= 0) {
                this.nRoom2 = this.nTreasureRoom[this.nTreasure1];
                if (this.nRoom2 >= 0) {
                    ++this.nTreasure1;
                }
            }
            this.nRoom2 = this.nRoom1;
            while (this.nRoom1 == this.nRoom2) {
                this.nRoom2 = 1 + this.nRandomNumber() % (this.nRooms - 1);
            }
            this.nTreasureRoom[this.nTreasure1] = this.nRoom2;
            this.sbPendingMsg.append("The pirate takes one of your treasures.  ");
            this.sbPendingMsg.append("As he leaves, he shouts the letters, \"");
            this.sbPendingMsg.append((Object)this.sbWayOut);
            this.sbPendingMsg.append("\".\n");
        }
        else {
            this.sbPendingMsg.append("Nothing happens.  Try again later.\n");
        }
        this.updateScreen();
    }
    
    public synchronized void updateScreen() {
        this.editMoves.setText(new Integer(this.nMoves).toString());
        if (!this.bEuclidean) {
            if (this.nXCoordinate < 0) {
                this.nYCoordinate = this.nWidth[1] - 1 - this.nYCoordinate;
                this.nZCoordinate = this.nWidth[2] - 1 - this.nZCoordinate;
                this.nTCoordinate = this.nWidth[3] - 1 - this.nTCoordinate;
                this.nXCoordinate = this.nWidth[0] - 1;
            }
            else if (this.nXCoordinate >= this.nWidth[0]) {
                this.nYCoordinate = this.nWidth[1] - 1 - this.nYCoordinate;
                this.nZCoordinate = this.nWidth[2] - 1 - this.nZCoordinate;
                this.nTCoordinate = this.nWidth[3] - 1 - this.nTCoordinate;
                this.nXCoordinate = 0;
            }
            if (this.nYCoordinate < 0) {
                this.nXCoordinate = this.nWidth[0] - 1 - this.nXCoordinate;
                this.nZCoordinate = this.nWidth[2] - 1 - this.nZCoordinate;
                this.nTCoordinate = this.nWidth[3] - 1 - this.nTCoordinate;
                this.nYCoordinate = this.nWidth[1] - 1;
            }
            else if (this.nYCoordinate >= this.nWidth[1]) {
                this.nXCoordinate = this.nWidth[0] - 1 - this.nXCoordinate;
                this.nZCoordinate = this.nWidth[2] - 1 - this.nZCoordinate;
                this.nTCoordinate = this.nWidth[3] - 1 - this.nTCoordinate;
                this.nYCoordinate = 0;
            }
            if (this.nZCoordinate < 0) {
                this.nXCoordinate = this.nWidth[0] - 1 - this.nXCoordinate;
                this.nYCoordinate = this.nWidth[1] - 1 - this.nYCoordinate;
                this.nTCoordinate = this.nWidth[3] - 1 - this.nTCoordinate;
                this.nZCoordinate = this.nWidth[2] - 1;
            }
            else if (this.nZCoordinate >= this.nWidth[2]) {
                this.nXCoordinate = this.nWidth[0] - 1 - this.nXCoordinate;
                this.nYCoordinate = this.nWidth[1] - 1 - this.nYCoordinate;
                this.nTCoordinate = this.nWidth[3] - 1 - this.nTCoordinate;
                this.nZCoordinate = 0;
            }
            if (this.nTCoordinate < 0) {
                this.nXCoordinate = this.nWidth[0] - 1 - this.nXCoordinate;
                this.nYCoordinate = this.nWidth[1] - 1 - this.nYCoordinate;
                this.nZCoordinate = this.nWidth[2] - 1 - this.nZCoordinate;
                this.nTCoordinate = this.nWidth[3] - 1;
            }
            else if (this.nTCoordinate >= this.nWidth[3]) {
                this.nXCoordinate = this.nWidth[0] - 1 - this.nXCoordinate;
                this.nYCoordinate = this.nWidth[1] - 1 - this.nYCoordinate;
                this.nZCoordinate = this.nWidth[2] - 1 - this.nZCoordinate;
                this.nTCoordinate = 0;
            }
        }
        this.nRoom1 = this.nCell[this.nXCoordinate][this.nYCoordinate][this.nZCoordinate][this.nTCoordinate];
        if (this.nRoom1 != 0 && this.sbWayOut.length() == 0 && this.nRandomNumber() % 100 == 0) {
            this.nRoom2 = 0;
            while (this.nRoom2 <= 0) {
                this.nXCoordinate = this.nRandomNumber() % this.nWidth[0];
                this.nYCoordinate = this.nRandomNumber() % this.nWidth[1];
                this.nZCoordinate = this.nRandomNumber() % this.nWidth[2];
                this.nTCoordinate = this.nRandomNumber() % this.nWidth[3];
                this.nRoom2 = this.nCell[this.nXCoordinate][this.nYCoordinate][this.nZCoordinate][this.nTCoordinate];
            }
            if (this.nRoom2 != this.nRoom1) {
                this.nRoom1 = this.nRoom2;
                this.sbPendingMsg.append("Yeow!!!!  A flock of bats grabs you, flies you through the caverns, and drops you.\n");
            }
        }
        this.sbWayOut.setLength(0);
        this.nTreasuresRecovered = 0;
        this.nTreasure1 = 0;
        this.bTreasureCarried = false;
        while (this.nTreasure1 < this.nTreasures && !this.bTreasureCarried) {
            if (this.nTreasureRoom[this.nTreasure1] < 0) {
                this.bTreasureCarried = true;
            }
            else {
                ++this.nTreasure1;
            }
        }
        if (this.bTreasureCarried && this.nRandomNumber() % (2 * this.nRooms) == 0) {
            this.nRoom2 = 0;
            while (this.nRoom2 <= 0) {
                this.nDimension1 = 0;
                while (this.nDimension1 < this.nDimensions) {
                    this.nCoordinate[this.nDimension1] = this.nRandomNumber() % this.nWidth[this.nDimension1];
                    ++this.nDimension1;
                }
                this.nRoom2 = this.nCell[this.nCoordinate[0]][this.nCoordinate[1]][this.nCoordinate[2]][this.nCoordinate[3]];
                if (this.nRoom1 == this.nRoom2) {
                    this.nRoom2 = -1;
                }
            }
            this.nTreasure1 = 0;
            while (this.nTreasure1 < this.nTreasures) {
                if (this.nTreasureRoom[this.nTreasure1] < 0) {
                    this.nTreasureRoom[this.nTreasure1] = this.nRoom2;
                }
                ++this.nTreasure1;
            }
            this.bTreasureCarried = false;
            this.sbPendingMsg.append("A pirate jumps out of the shadows and takes your treasure.");
            this.sbPendingMsg.append("As he leaves, he says, \"Arggh!  I'll hide me booty better this time.\"\n");
        }
        this.nTreasure1 = 0;
        this.nTreasure2 = 0;
        this.sbTreasures.setLength(0);
        this.sbInventory.setLength(0);
        this.nTreasuresCarried = 0;
        while (this.nTreasure1 < this.nTreasures) {
            if (this.nTreasureRoom[this.nTreasure1] == 0) {
                ++this.nTreasuresRecovered;
                if (this.nRoom1 == 0) {
                    this.sbTreasures.append("There's ");
                    this.sbTreasures.append(this.strTreasure[this.nTreasure1]);
                    this.sbTreasures.append(" here.\n");
                }
            }
            else if (this.nTreasureRoom[this.nTreasure1] == this.nRoom1) {
                this.sbTreasures.append("There's ");
                this.sbTreasures.append(this.strTreasure[this.nTreasure1]);
                this.sbTreasures.append(" here.  ");
                if (this.nGuardRoom[this.nTreasure1] == this.nRoom1) {
                    final char char1 = this.strGuard[this.nTreasure1].charAt(0);
                    if (char1 == 'a' || char1 == 'e' || char1 == 'i' || char1 == 'o' || char1 == 'u') {
                        this.sbTreasures.append("It's guarded by an ");
                    }
                    else {
                        this.sbTreasures.append("It's guarded by a ");
                    }
                    this.sbTreasures.append(this.strGuard[this.nTreasure1]);
                    this.sbTreasures.append(".\n");
                }
            }
            else if (this.nTreasureRoom[this.nTreasure1] == -1) {
                this.bTreasureCarried = true;
                ++this.nTreasuresCarried;
                ++this.nTreasure2;
                this.sbInventory.append(this.strTreasure[this.nTreasure1]);
                this.sbInventory.append("\n");
            }
            if (this.nWeaponRoom[this.nTreasure1] == this.nRoom1) {
                final char char2 = this.strWeapon[this.nTreasure1].charAt(0);
                if (char2 == 'a' || char2 == 'e' || char2 == 'i' || char2 == 'o' || char2 == 'u') {
                    this.sbTreasures.append("There's an ");
                }
                else {
                    this.sbTreasures.append("There's a ");
                }
                this.sbTreasures.append(this.strWeapon[this.nTreasure1]);
                this.sbTreasures.append(" here.\n");
            }
            else if (this.nWeaponRoom[this.nTreasure1] == -1) {
                ++this.nTreasure2;
                this.sbInventory.append(this.strWeapon[this.nTreasure1]);
                this.sbInventory.append("\n");
            }
            ++this.nTreasure1;
        }
        this.editTreasuresRecovered.setText(new Integer(this.nTreasuresRecovered).toString());
        this.editNumTreasures.setText(new Integer(this.nTreasures).toString());
        if (!this.bVisited[this.nRoom1]) {
            ++this.nVisited;
            this.bVisited[this.nRoom1] = true;
        }
        this.editRoomsVisited.setText(new Integer(this.nVisited).toString());
        this.editNumRooms.setText(new Integer(this.nRooms).toString());
        this.nScore = 25 * this.nVisited / this.nRooms + 75 * this.nTreasuresRecovered / this.nTreasures + 45 * this.nTreasuresCarried / this.nTreasures;
        if (this.nVisited > 5 * this.nRooms) {
            this.nScore -= this.nVisited / (5 * this.nRooms);
            if (this.nScore < 0) {
                this.nScore = 0;
            }
        }
        this.editScore.setText(new Integer(this.nScore).toString());
        this.editMaxScore.setText("100");
        this.sbLocation.setLength(0);
        this.sbLocation.append((Object)this.sbPendingMsg);
        this.sbLocation.append("You're in ");
        this.sbLocation.append(this.strDescription[this.nRoom1]);
        this.sbLocation.append("\n");
        this.sbLocation.append((Object)this.sbTreasures);
        this.displayFormattedLocation(this.sbLocation, this.editLocation.getColumns());
        this.editInventory.setText(this.sbInventory.toString());
        if (this.sbTreasures.length() == 0) {
            this.buttonCarry.disable();
        }
        else {
            this.buttonCarry.enable();
        }
        if (this.nRoom1 == 0 && this.bTreasureCarried) {
            this.buttonDropTreasures.enable();
        }
        else {
            this.buttonDropTreasures.disable();
        }
        if (this.bConnected[this.nRoom1][0][0]) {
            this.buttonNorth.enable();
        }
        else {
            this.buttonNorth.disable();
        }
        if (this.bConnected[this.nRoom1][0][1]) {
            this.buttonSouth.enable();
        }
        else {
            this.buttonSouth.disable();
        }
        if (this.bConnected[this.nRoom1][1][0]) {
            this.buttonEast.enable();
        }
        else {
            this.buttonEast.disable();
        }
        if (this.bConnected[this.nRoom1][1][1]) {
            this.buttonWest.enable();
        }
        else {
            this.buttonWest.disable();
        }
        if (this.bConnected[this.nRoom1][2][0]) {
            this.buttonUp.enable();
        }
        else {
            this.buttonUp.disable();
        }
        if (this.bConnected[this.nRoom1][2][1]) {
            this.buttonDown.enable();
        }
        else {
            this.buttonDown.disable();
        }
        if (this.bConnected[this.nRoom1][3][0]) {
            this.buttonForward.enable();
        }
        else {
            this.buttonForward.disable();
        }
        if (this.bConnected[this.nRoom1][3][1]) {
            this.buttonBackward.enable();
            return;
        }
        this.buttonBackward.disable();
    }
    
    public void displayFormattedLocation(final StringBuffer sb, final int n) {
        final StringBuffer sb2 = new StringBuffer(3072);
        final StringBuffer sb3 = new StringBuffer(50);
        final StringBuffer sb4 = new StringBuffer(256);
        final StringBuffer sb5 = new StringBuffer(256);
        sb2.setLength(0);
        sb2.append("  ");
        int i = 0;
        final int length = sb.length();
        sb3.setLength(0);
        sb3.append("  ");
        int n2 = 0;
        while (i < length) {
            while (i < length && sb.charAt(i) == ' ') {
                ++i;
            }
            sb5.setLength(0);
            boolean b;
            char char1;
            for (b = false; i < length && !b && (char1 = sb.charAt(i)) != ' '; b = (char1 == '\n'), ++i) {
                sb5.append(char1);
            }
            if (sb5.length() > 0) {
                if (n2 != 0) {
                    if (sb3.length() == 0) {
                        sb2.append("  ");
                    }
                    else {
                        sb2.append("\n  ");
                    }
                    sb3.setLength(0);
                    sb3.append("  ");
                    n2 = 0;
                }
                if (b) {
                    n2 = 1;
                    sb5.setLength(sb5.length() - 1);
                }
            }
            if (sb5.length() > 0) {
                if (sb5.charAt(sb5.length() - 1) == '.') {
                    sb4.setLength(0);
                    sb4.append((Object)sb3);
                    sb4.append((Object)sb5);
                    sb4.append("  ");
                    if (this.fmTreasure.stringWidth(sb4.toString()) <= this.editLocation.preferredSize().width - 36) {
                        sb2.append((Object)sb5).append("  ");
                        sb3.append((Object)sb5).append("  ");
                    }
                    else {
                        sb4.setLength(sb4.length() - 2);
                        if (this.fmTreasure.stringWidth(sb4.toString()) <= this.editLocation.preferredSize().width - 36) {
                            sb2.append((Object)sb5).append('\n');
                            sb3.setLength(0);
                        }
                        else {
                            sb2.append('\n');
                            while (sb5.length() > 0) {
                                if (this.fmTreasure.stringWidth(sb5.toString()) > this.editLocation.preferredSize().width - 36) {
                                    sb4.setLength(0);
                                    int n3 = 0;
                                    while (this.fmTreasure.stringWidth(sb4.toString()) <= this.editLocation.preferredSize().width - 36) {
                                        sb4.append(sb5.charAt(n3++));
                                    }
                                    --n3;
                                    sb2.append(sb5.toString().substring(0, n3));
                                    sb2.append('\n');
                                    sb4.setLength(0);
                                    sb4.append(sb5.toString().substring(n3));
                                    sb5.setLength(0);
                                    sb5.append((Object)sb4);
                                }
                                else {
                                    sb2.append((Object)sb5);
                                    sb3.setLength(0);
                                    sb3.append((Object)sb5);
                                    sb4.setLength(0);
                                    sb4.append((Object)sb3);
                                    sb4.append("  ");
                                    if (this.fmTreasure.stringWidth(sb4.toString()) <= this.editLocation.preferredSize().width - 36) {
                                        sb2.append("  ");
                                        sb3.append("  ");
                                    }
                                    else {
                                        sb2.append('\n');
                                        sb3.setLength(0);
                                    }
                                    sb5.setLength(0);
                                }
                            }
                        }
                    }
                }
                else {
                    sb4.setLength(0);
                    sb4.append((Object)sb3);
                    sb4.append((Object)sb5);
                    sb4.append(' ');
                    if (this.fmTreasure.stringWidth(sb4.toString()) <= this.editLocation.preferredSize().width - 36) {
                        sb2.append((Object)sb5).append(' ');
                        sb3.append((Object)sb5).append(' ');
                    }
                    else {
                        sb4.setLength(sb4.length() - 2);
                        if (this.fmTreasure.stringWidth(sb4.toString()) <= this.editLocation.preferredSize().width - 36) {
                            sb2.append((Object)sb5).append('\n');
                            sb3.setLength(0);
                        }
                        else {
                            sb2.append('\n');
                            while (sb5.length() > 0) {
                                if (sb5.length() > n) {
                                    sb4.setLength(0);
                                    int n4 = 0;
                                    while (this.fmTreasure.stringWidth(sb4.toString()) <= this.editLocation.preferredSize().width - 36) {
                                        sb4.append(sb5.charAt(n4++));
                                    }
                                    --n4;
                                    sb2.append(sb5.toString().substring(0, n4));
                                    sb2.append('\n');
                                    sb4.setLength(0);
                                    sb4.append(sb5.toString().substring(n4));
                                    sb5.setLength(0);
                                    sb5.append((Object)sb4);
                                }
                                else {
                                    sb2.append((Object)sb5);
                                    sb3.setLength(0);
                                    sb3.append((Object)sb5);
                                    sb4.setLength(0);
                                    sb4.append((Object)sb3);
                                    sb4.append(' ');
                                    if (this.fmTreasure.stringWidth(sb4.toString()) <= this.editLocation.preferredSize().width - 36) {
                                        sb2.append(' ');
                                        sb3.append(' ');
                                    }
                                    else {
                                        sb2.append('\n');
                                        sb3.setLength(0);
                                    }
                                    sb5.setLength(0);
                                }
                            }
                        }
                    }
                }
            }
        }
        this.editLocation.setText(sb2.toString());
    }
    
    public int nRandomNumber() {
        int i;
        do {
            i = this.nRNPartialSum + this.nRN[this.nAddIndex];
            if (i >= 32771) {
                i -= 32771;
            }
            this.nRNPartialSum = i - this.nRN[this.nReplaceIndex];
            if (this.nRNPartialSum < 0) {
                this.nRNPartialSum += 32771;
            }
            this.nRN[this.nReplaceIndex] = i;
            this.nAddIndex = this.nReplaceIndex;
            if (++this.nReplaceIndex >= 8) {
                this.nReplaceIndex = 0;
            }
        } while (i > 32767);
        return i;
    }
    
    public void hideGame() {
        this.buttonWest.hide();
        this.buttonSouth.hide();
        this.buttonNorth.hide();
        this.buttonEast.hide();
        this.buttonUp.hide();
        this.buttonDown.hide();
        this.editLocation.hide();
        this.buttonForward.hide();
        this.buttonBackward.hide();
        this.buttonCarry.hide();
        this.buttonDropTreasures.hide();
        this.buttonWayOut.hide();
        this.labelTitle.hide();
        this.labelMoves.hide();
        this.labelRoomsVisited.hide();
        this.labelScore.hide();
        this.labelTreasuresRecovered.hide();
        this.editRoomsVisited.hide();
        this.editTreasuresRecovered.hide();
        this.editScore.hide();
        this.editMoves.hide();
        this.labelLocation.hide();
        this.labelInventory.hide();
        this.editInventory.hide();
        this.labelNumRooms.hide();
        this.editNumRooms.hide();
        this.labelNumTreasures.hide();
        this.editNumTreasures.hide();
        this.labelMaxScore.hide();
        this.editMaxScore.hide();
        this.buttonAbout.hide();
    }
    
    public void displayFormattedAbout(final String s, final int n) {
        final StringBuffer sb = new StringBuffer(3072);
        final StringBuffer sb2 = new StringBuffer(50);
        final StringBuffer sb3 = new StringBuffer(256);
        final StringBuffer sb4 = new StringBuffer(256);
        sb.setLength(0);
        sb.append("  ");
        int i = 0;
        final int length = s.length();
        sb2.setLength(0);
        sb2.append("  ");
        int n2 = 0;
        while (i < length) {
            while (i < length && s.charAt(i) == ' ') {
                ++i;
            }
            sb4.setLength(0);
            boolean b;
            char char1;
            for (b = false; i < length && !b && (char1 = s.charAt(i)) != ' '; b = (char1 == '\n'), ++i) {
                sb4.append(char1);
            }
            if (sb4.length() > 0) {
                if (n2 != 0) {
                    if (sb2.length() == 0) {
                        sb.append("  ");
                    }
                    else {
                        sb.append("\n  ");
                    }
                    sb2.setLength(0);
                    sb2.append("  ");
                    n2 = 0;
                }
                if (b) {
                    n2 = 1;
                    sb4.setLength(sb4.length() - 1);
                }
            }
            if (sb4.length() > 0) {
                if (sb4.charAt(sb4.length() - 1) == '.') {
                    sb3.setLength(0);
                    sb3.append((Object)sb2);
                    sb3.append((Object)sb4);
                    sb3.append("  ");
                    if (this.fmAbout.stringWidth(sb3.toString()) <= this.editAbout.preferredSize().width - 36) {
                        sb.append((Object)sb4).append("  ");
                        sb2.append((Object)sb4).append("  ");
                    }
                    else {
                        sb3.setLength(sb3.length() - 2);
                        if (this.fmAbout.stringWidth(sb3.toString()) <= this.editAbout.preferredSize().width - 36) {
                            sb.append((Object)sb4).append('\n');
                            sb2.setLength(0);
                        }
                        else {
                            sb.append('\n');
                            while (sb4.length() > 0) {
                                if (this.fmAbout.stringWidth(sb4.toString()) > this.editAbout.preferredSize().width - 36) {
                                    sb3.setLength(0);
                                    int n3 = 0;
                                    while (this.fmAbout.stringWidth(sb3.toString()) <= this.editAbout.preferredSize().width - 36) {
                                        sb3.append(sb4.charAt(n3++));
                                    }
                                    --n3;
                                    sb.append(sb4.toString().substring(0, n3));
                                    sb.append('\n');
                                    sb3.setLength(0);
                                    sb3.append(sb4.toString().substring(n3));
                                    sb4.setLength(0);
                                    sb4.append((Object)sb3);
                                }
                                else {
                                    sb.append((Object)sb4);
                                    sb2.setLength(0);
                                    sb2.append((Object)sb4);
                                    sb3.setLength(0);
                                    sb3.append((Object)sb2);
                                    sb3.append("  ");
                                    if (this.fmAbout.stringWidth(sb3.toString()) <= this.editAbout.preferredSize().width - 36) {
                                        sb.append("  ");
                                        sb2.append("  ");
                                    }
                                    else {
                                        sb.append('\n');
                                        sb2.setLength(0);
                                    }
                                    sb4.setLength(0);
                                }
                            }
                        }
                    }
                }
                else {
                    sb3.setLength(0);
                    sb3.append((Object)sb2);
                    sb3.append((Object)sb4);
                    sb3.append(' ');
                    if (this.fmAbout.stringWidth(sb3.toString()) <= this.editAbout.preferredSize().width - 36) {
                        sb.append((Object)sb4).append(' ');
                        sb2.append((Object)sb4).append(' ');
                    }
                    else {
                        sb3.setLength(sb3.length() - 2);
                        if (this.fmAbout.stringWidth(sb3.toString()) <= this.editAbout.preferredSize().width - 36) {
                            sb.append((Object)sb4).append('\n');
                            sb2.setLength(0);
                        }
                        else {
                            sb.append('\n');
                            while (sb4.length() > 0) {
                                if (sb4.length() > n) {
                                    sb3.setLength(0);
                                    int n4 = 0;
                                    while (this.fmAbout.stringWidth(sb3.toString()) <= this.editAbout.preferredSize().width - 36) {
                                        sb3.append(sb4.charAt(n4++));
                                    }
                                    --n4;
                                    sb.append(sb4.toString().substring(0, n4));
                                    sb.append('\n');
                                    sb3.setLength(0);
                                    sb3.append(sb4.toString().substring(n4));
                                    sb4.setLength(0);
                                    sb4.append((Object)sb3);
                                }
                                else {
                                    sb.append((Object)sb4);
                                    sb2.setLength(0);
                                    sb2.append((Object)sb4);
                                    sb3.setLength(0);
                                    sb3.append((Object)sb2);
                                    sb3.append(' ');
                                    if (this.fmAbout.stringWidth(sb3.toString()) <= this.editAbout.preferredSize().width - 36) {
                                        sb.append(' ');
                                        sb2.append(' ');
                                    }
                                    else {
                                        sb.append('\n');
                                        sb2.setLength(0);
                                    }
                                    sb4.setLength(0);
                                }
                            }
                        }
                    }
                }
            }
        }
        this.editAbout.setText(sb.toString());
    }
    
    public void clickedButtonAbout() {
        this.fmAbout = this.getFontMetrics(this.editAbout.getFont());
        this.displayFormattedAbout(this.strAbout, this.editAbout.getColumns());
        this.hideGame();
        this.editAbout.show();
        this.buttonAboutOk.show();
    }
    
    public Treasure() {
        this.bConnected = new boolean[100][4][2];
        this.bDirectionUsed = new boolean[256][4][2];
        this.bRoomUsed = new boolean[100];
        this.bVisited = new boolean[100];
        this.nCell = new int[15][15][15][15];
        this.nCoordinate = new int[4];
        this.nCoordinateNext = new int[4];
        this.nDirectionsUsed = new int[256];
        this.nGuardRoom = new int[15];
        this.nRN = new int[8];
        this.nTreasureRoom = new int[15];
        this.nWayOutDimension = new int[256];
        this.nWayOutDirection = new int[256];
        this.nWeaponRoom = new int[15];
        this.nWidth = new int[4];
        this.strAbout = "    Adventures in Four Dimensions\n\n    Copyright (c) 1979, 1996, 1997 James L. Dean\n\n    Version 4.4 released 3/13/97\n\n    This program may be distributed or used without payment to its author.\n\n    This ADVENTURE-like game was inspired by a program given by Roger Chaffee in his article \"Quest\" published in the July 1979 issue of Byte.  Unlike that program, this program allows multiple treasures and play in four dimensions.  In addition, at the beginning of a game, this program allows the player to specify a game number that changes the connections between the locations in an unpredictable manner.  Thus, the player has a whole series of games at his disposal.\n\n    The object of this game is to bring all the treasures to the starting point while visiting all the locations in the smallest number of moves.  Hint:  the distance from one room to the next is always the same.\n\n    This program was originally written in 1979 in Basic for a TANO Corporation Outpost having 64K of memory.  A later version was written in COBOL for CICS on an IBM Mainframe. This version is written in Java.";
        this.strDescription = new String[] { "the entrance to a large network of caverns.", "a spherical room.", "the hall of bones.  Dinosaur bones are everywhere.", "a subway tunnel.  Don't touch that third rail!", "a railroad tunnel.  A whistle wails in the distance.", "an elfin quiche factory.  The elves are out mowing spinach.", "an abandoned Plymouth plant.  Beware of Road Runners and Barracudas.", "an abandoned Dodge plant.  There is a Dart embedded in the North wall.", "a mouse's nest.  You'd best exhale; this is a small room.", "a giant crayfish hole.  An immense chicken neck is hanging from a rope.", "an abandoned coal mine.  Beware of methane.", "the hall of winds.  Presently, the wind is from the south.", "a stove pipe!", "a totally darkened room.  Although you can see nothing, the sound of dripping water echos from the walls.", "an industrial waste site.  Hold your breath and don't touch anything.", "the warehouse for an extremely popular brand of home computer.  Tacky plastic enclosures abound.", "a hobbit's bedroom.  The hobbit does not look pleased!", "a troll sewerage processing plant.  The air quality is not good.", "a rabbit hole.  There is a jar of marmalade on a shelf in the wall.", "the giant's oven.  Fortunately, it hasn't been used for years.", "a hobbit's drying room.  Tobacco leaves abound.", "a large circular room.  It is dark in here.", "the Crystal Palace.  Quartz crystals cover the walls.", "the Emerald Room.  Green crystals cover the ceiling.", "a curtained room.", "an air conditioning duct!", "a giant kiln.  Smoke stains the walls.", "the Hall of Mists.  Wisps of white vapor rise from the floor.", "an Aztec pyramid.  A mummy lies in the northwest corner.", "the Room of the Eternal Flame.  A large natural gas flame burns in the center of the room.  Coming from the west wall you can barely hear the words, 'Fee Fye Foe Fum'.", "the Giant's store room.  You are surrounded by old rugs, books, chairs, etc.", "the Leprechaun's Treasure Room.  Unfortunately, the leprechaun's treasure was stolen years ago.", "a large tiled room.  A girl is inquiring after a rabbit.  Feel free to ignore her.", "a former nuclear test site.  There is a large pile of rubble in the center of the floor.  The walls are streaked with a multitude of colors.", "a drainage system holding tank.  Either it is no longer used, or it hasn't rained in a long time; the room is dry.", "Mayor Schiro's bomb shelter.", "a room with a waterfall.  It is not very impressive; it looks like someone left a water faucet running.", "an abandoned Neanderthal home.", "a volcanic chimney.  The air is getting warmer.", "a pit full of snakes.  Let's get out of here!!!!", "a salt dome.", "Eleanor Roosevelt's privy.  Wendall Wilkie buttons cover the wall.", "Napoleon's wine cellar.  German wines line the shelves.", "the space behind the giant's bathroom wall. Large razor blades litter the floor.  Step lightly.", "the room where all old toe nail clipping come to rest.  Used corn pads litter the floor.", "the Den of the Horta.  The walls are covered with a sticky fluid.  Don't touch it; it is extremely corrosive.", "a damp room.  A small creek runs into a crack in the West wall.", "what appears to be a NOPSI manhole.", "the cafeteria of Mammoth Cave.  The aroma of rancid corned beef assails your nostrils.", "a small room with a large table.  On the table is a bottle that says, 'Drink me.'", "a Confederate States of America bank vault.  Once worthless currency papers the walls.", "an abandoned subway station.", "a mine shaft.  In the distance you can hear seven high pitched voices singing, 'Hi Ho, Hi Ho, ...'", "a Minuteman missile silo.", "the giant's mouse trap.  Fortunately, you are small enough to escape.", "Adolph Hitler's summer bunker.", "a dwarf work site.  A sign says, 'Under construction. Enter at your own risk.'", "the giant's refrigerator.  Dwarf bodies hang from hooks.", "the Dragon's Lair.  Slightly melted suits of armor litter the floor.", "a nuclear waste depository.  The walls glow faintly.", "Millard Filmore's tomb.  It is dull.", "an abandoned corridor of the Strategic Air Command Headquarters.  A graffito reads, 'Beware of Bat Guano.'", "a gnome's workshop.  Half-completed whoopee cushions line the tables.", "the Mummy's Tomb.  You've triggered some mechanism and the ceiling is slowly descending.", "the Underground Gourmet's retreat.  Twinky and King Don wrappers are piled knee deep.", "a Hoola Hoop warehouse.  The words 'Shoop Shoop' echo from the walls.", "the first circle of hell.  The living are not allowed here.", "the hall of the pixies.  The furniture appears to have been made from cradles.", "a sulfur mine.  Molten sulfur churns in a nearby pit. It is becoming difficult to breath.", "a fairy mushroom farm.  Brilliantly colored mushrooms cover the floor.", "an ice cave.  Along the western wall, a brontosaurus is defrosting.", "the giant's stove.  Fortunately, the giant now owns a microwave oven.", "the rib cage of a long deceased whale.", "a room with six walls.  The walls appear to be made of wax.  A loud buzzing noise can be heard.", "the tomb of a Pharoah.  It has obviously been visited by others; the tomb is in a state of total disarray.", "a coal bin.  There is a fossilized fern stump here.", "a diamond mine.  It is uncomfortably hot here.", "the bottom of an oil well.  The walls are slick.", "the lowest level of Project Mohole.  The funding bubble burst before the earth did.", "the giant's cesspool.  Fortunately, the giant was connected to the city sewerage system years ago.", "an eighteenth century sewer.  The walls are crumbling brick.  Small alligators snap at your feet.", "the lair of a giant trapdoor spider.", "a giant gopher tunnel.", "a shell -- the sole remains of a prehistoric turtle.", "a small chamber.  The walls are made of earth.  The air smells of formic acid.  A strange squeaking noise can be heard in the distance.", "a chamber of columns.  The stalagmites and stalactites join here.", "a service tunnel.  Ducts, pipes, and cables are everywhere.", "a gas tank below an abandoned service station.  No smoking!", "a huge dark chamber.  To one side, a dark, muddy river moves sluggishly.  A sign written in ancient Greek says, 'Ferry out of order.'", "a small chamber. It is brightly lit by a peculiar lichen growing on the walls and ceiling.  The floor is rocky and steeply sloping.  Nearby, a cold, clear creek boils into the floor and out of sight.", "the nest of a very large pack rat.  There are discarded aluminum Christmas trees, broken steel utility poles, and other shiny, worthless items here.", "a dungeon.  An iron maiden, a rack, a piano, and other machines of torture can be seen.", "the hall of bats.  Thousands of bats hang from the ceiling.  Watch your step; the droppings are quite deep in places.", "a hobgoblin's hideaway.", "an electrical substation.  A transformer is humming loudly.  Nearby, cables crackle with high voltage.", "the 'gold' room.  The walls are covered with iron pyrite.", "a room with one of Dracula's emergency coffins.  The Count is out.", "a saltpeter mine.  To one side there is a huge wooden evaporation vat.  Small crystals of saltpeter cause the walls to sparkle.", "the basement cafeteria of a local hospital.  Some say that there has been nothing edible here for years.", "a troll arsenal.  Kegs of gun powder surround you." };
        this.strGuard = new String[] { "giant", "ferocious lion", "grizzly bear", "Tasmanian devil", "crocodile", "giant crayfish", "troll", "boa constrictor", "harpy", "cobra", "singularly large leech", "rabid Doberman pinscher", "colossal cockroach", "giant rat", "titanic toad" };
        this.strTreasure = new String[] { "a complete set of superman comic books", "a bag full of gold coins", "a large blue-white diamond", "a four ounce emerald", "a platinum crucifix", "a string of pearls", "a ming vase", "an oriental rug", "a pile of rubies", "a previously undiscovered Rembrandt painting", "a Faberge egg", "a stack of silver bars", "a set of ivory tusks", "an ancient Greek statue", "a small amount of radium in a lead container" };
        this.strWeapon = new String[] { "dagger", "axe", "mace", "sword", "staff", "silver bullet", "hand of glory", "trident", "sling shot", "blow gun", "pike", "cutlass", "spear", "stileto", "magic wand" };
    }
}
