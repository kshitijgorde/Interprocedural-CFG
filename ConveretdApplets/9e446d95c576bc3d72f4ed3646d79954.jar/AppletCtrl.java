import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.Point;
import java.awt.List;
import java.awt.Choice;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

class AppletCtrl implements ItemListener, MouseListener, ActionListener, MouseMotionListener
{
    private ChordAnalyser chordAnalyser;
    private AppletComponents appComp;
    private String strBaseNote;
    private String strSymbNote;
    private Choice choice;
    private List list;
    private int nIndex;
    private int nScopePos;
    private int nScopeEndPos;
    private boolean bTuningChanged;
    private boolean bFirstFretClick;
    private boolean bUserUpdate;
    private boolean bChordDetails;
    private ImageCanvas imgCanvas;
    private Point point;
    private int xPos;
    private int yPos;
    private boolean bFretBoardEntered;
    
    public AppletCtrl(final AppletComponents appComp, final ImageCanvas imgCanvas, final ChordAnalyser chordAnalyser) {
        this.strBaseNote = "";
        this.strSymbNote = "";
        this.nScopePos = 1;
        this.nScopeEndPos = 12;
        this.bTuningChanged = false;
        this.bFirstFretClick = true;
        this.bUserUpdate = false;
        this.bChordDetails = true;
        this.xPos = 0;
        this.yPos = 0;
        this.bFretBoardEntered = false;
        this.appComp = appComp;
        this.imgCanvas = imgCanvas;
        this.chordAnalyser = chordAnalyser;
        this.appComp.getBaseNoteList().addItemListener(this);
        this.appComp.getChordSymbols().addItemListener(this);
        this.appComp.getScaleList().addItemListener(this);
        this.appComp.getTuningChoice().addItemListener(this);
        this.appComp.getCBoxNote().addItemListener(this);
        this.appComp.getCBoxInterval().addItemListener(this);
        this.appComp.getCBoxRightHand().addItemListener(this);
        this.appComp.getCBoxLeftHand().addItemListener(this);
        this.appComp.getHomeUrl().addMouseListener(this);
        this.appComp.getShowAllNotesBtn().addActionListener(this);
        this.appComp.getResetBtn().addActionListener(this);
        this.appComp.getDecScopePosBtn().addActionListener(this);
        this.appComp.getIncScopePosBtn().addActionListener(this);
        this.appComp.getDecScopeSizeBtn().addActionListener(this);
        this.appComp.getIncScopeSizeBtn().addActionListener(this);
        this.appComp.getScopePosValue().addActionListener(this);
        this.appComp.getScopeSizeValue().addActionListener(this);
        this.appComp.getShowScopeBtn().addActionListener(this);
        imgCanvas.addMouseListener(this);
        imgCanvas.addMouseMotionListener(this);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.appComp.getCBoxRightHand()) {
            if (this.imgCanvas.isLeftHanded()) {
                this.imgCanvas.setLeftHand(false);
                this.imgCanvas.switchUserPositions();
            }
            this.imgCanvas.setTuning(this.appComp.getTuningChoice().getSelectedIndex());
        }
        if (itemEvent.getSource() == this.appComp.getCBoxLeftHand()) {
            if (!this.imgCanvas.isLeftHanded()) {
                this.imgCanvas.setLeftHand(true);
                this.imgCanvas.switchUserPositions();
            }
            this.imgCanvas.setTuning(this.appComp.getTuningChoice().getSelectedIndex());
        }
        if (itemEvent.getSource() == this.appComp.getCBoxNote()) {
            this.imgCanvas.switchNoteIntervalView(true);
        }
        if (itemEvent.getSource() == this.appComp.getCBoxInterval()) {
            this.imgCanvas.switchNoteIntervalView(false);
        }
        if (itemEvent.getSource() == this.appComp.getTuningChoice()) {
            this.choice = (Choice)itemEvent.getItemSelectable();
            this.nIndex = this.choice.getSelectedIndex();
            this.imgCanvas.setTuning(this.nIndex);
            this.imgCanvas.updateAllUserNotes();
        }
        if (itemEvent.getSource() == this.appComp.getBaseNoteList()) {
            this.list = (List)itemEvent.getItemSelectable();
            this.nIndex = (int)itemEvent.getItem();
            if (this.list.getItem(this.nIndex).length() > 2) {
                this.strBaseNote = this.list.getItem(this.nIndex).substring(0, 2);
            }
            else {
                this.strBaseNote = this.list.getItem(this.nIndex);
            }
            if (this.appComp.getCBoxNote().getState()) {
                this.imgCanvas.showNotes();
            }
            else {
                this.imgCanvas.showIntervals();
            }
            this.appComp.setUserAccessEnabled(true);
            this.bFirstFretClick = true;
            this.bUserUpdate = false;
            this.imgCanvas.resetChordResult(false);
        }
        if (itemEvent.getSource() == this.appComp.getChordSymbols()) {
            this.list = (List)itemEvent.getItemSelectable();
            this.nIndex = (int)itemEvent.getItem();
            this.strSymbNote = this.list.getItem(this.nIndex);
            if (this.appComp.getCBoxNote().getState()) {
                this.imgCanvas.showNotes();
            }
            else {
                this.imgCanvas.showIntervals();
            }
            this.appComp.deselectItemsInScaleList();
            this.appComp.setUserAccessEnabled(true);
            this.bFirstFretClick = true;
            this.bUserUpdate = false;
            this.bChordDetails = true;
            this.imgCanvas.isScaleList(false);
            this.imgCanvas.resetChordResult(false);
        }
        if (itemEvent.getSource() == this.appComp.getScaleList()) {
            this.list = (List)itemEvent.getItemSelectable();
            this.nIndex = (int)itemEvent.getItem();
            this.strSymbNote = "  " + this.list.getItem(this.nIndex);
            if (this.appComp.getCBoxNote().getState()) {
                this.imgCanvas.showNotes();
            }
            else {
                this.imgCanvas.showIntervals();
            }
            this.appComp.deselectItemsInList();
            this.appComp.setUserAccessEnabled(true);
            this.bFirstFretClick = true;
            this.bUserUpdate = false;
            this.bChordDetails = false;
            this.imgCanvas.isScaleList(true);
            this.imgCanvas.resetChordResult(false);
        }
        if (itemEvent.getStateChange() == 1) {
            this.appComp.setDetails(this.strBaseNote + this.strSymbNote, this.bChordDetails);
        }
        if (this.imgCanvas.isUserNote()) {
            this.appComp.setDetails(this.imgCanvas.getUserResult(), true);
        }
        this.imgCanvas.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this.appComp.getHomeUrl()) {
            final String s = "http://www.microtools.de/guitarcodex/";
            try {
                this.appComp.appletContext.showDocument(new URL(s), "blank");
            }
            catch (MalformedURLException ex) {
                System.err.println("Malformed URL: " + s);
            }
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.point == null) {
            this.point = new Point(x, y);
        }
        else {
            this.point.x = x;
            this.point.y = y;
        }
        if (this.checkArea(this.point.x, this.point.y)) {
            if (this.bTuningChanged) {
                this.appComp.setTuningChoice(16);
            }
            else {
                this.imgCanvas.updateUserResult();
            }
            this.bTuningChanged = false;
            this.appComp.setDetails(this.imgCanvas.getUserResult(), this.bChordDetails);
            this.imgCanvas.repaint();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        boolean b = false;
        boolean b2 = false;
        if (this.point == null) {
            this.point = new Point(x, y);
        }
        else {
            this.point.x = x;
            this.point.y = y;
        }
        if (y > 70 && y <= 380) {
            b2 = true;
        }
        if (x >= 20 && x <= 135) {
            b = true;
        }
        if (b2 && b) {
            this.bFretBoardEntered = true;
            if (this.imgCanvas.isMousePositionChanged() && (this.xPos != (x - 5) / 19 || this.yPos != (y - 45) / 26)) {
                this.xPos = (x - 5) / 19;
                this.yPos = (y - 45) / 26;
                this.imgCanvas.setMousePosition(this.xPos, this.yPos);
            }
        }
        else if (this.bFretBoardEntered) {
            this.imgCanvas.repaint(this.imgCanvas.mouseLastPos[0][3] + 17, this.imgCanvas.mouseLastPos[0][4] + 72, 21, 21);
            this.bFretBoardEntered = false;
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public boolean checkArea(final int n, final int n2) {
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        final int n3 = this.imgCanvas.getXOffset() - 2;
        final int n4 = n3 + 12;
        if (n2 > 0 && n2 <= 13) {
            b = true;
        }
        if (n2 > 34 && n2 <= 46) {
            b2 = true;
        }
        if (b || b2) {
            int n5 = -1;
            if (b) {
                if (n >= n3 && n <= n4) {
                    n5 = 0;
                    this.imgCanvas.nBase = this.imgCanvas.incrementNote(this.imgCanvas.nBase);
                }
                if (n >= n3 + 20 && n <= n4 + 20) {
                    n5 = 1;
                    this.imgCanvas.nSec = this.imgCanvas.incrementNote(this.imgCanvas.nSec);
                }
                if (n >= n3 + 40 && n <= n4 + 40) {
                    n5 = 2;
                    this.imgCanvas.nThird = this.imgCanvas.incrementNote(this.imgCanvas.nThird);
                }
                if (n >= n3 + 60 && n <= n4 + 60) {
                    n5 = 3;
                    this.imgCanvas.nFourth = this.imgCanvas.incrementNote(this.imgCanvas.nFourth);
                }
                if (n >= n3 + 80 && n <= n4 + 80) {
                    n5 = 4;
                    this.imgCanvas.nFifth = this.imgCanvas.incrementNote(this.imgCanvas.nFifth);
                }
                if (n >= n3 + 100 && n <= n4 + 100) {
                    n5 = 5;
                    this.imgCanvas.nSixth = this.imgCanvas.incrementNote(this.imgCanvas.nSixth);
                }
                if (this.bUserUpdate) {
                    this.imgCanvas.updateUserNote(n5, true);
                }
            }
            else if (b2) {
                if (n >= n3 && n <= n4) {
                    n5 = 0;
                    this.imgCanvas.nBase = this.imgCanvas.decrementNote(this.imgCanvas.nBase);
                }
                if (n >= n3 + 20 && n <= n4 + 20) {
                    n5 = 1;
                    this.imgCanvas.nSec = this.imgCanvas.decrementNote(this.imgCanvas.nSec);
                }
                if (n >= n3 + 40 && n <= n4 + 40) {
                    n5 = 2;
                    this.imgCanvas.nThird = this.imgCanvas.decrementNote(this.imgCanvas.nThird);
                }
                if (n >= n3 + 60 && n <= n4 + 60) {
                    n5 = 3;
                    this.imgCanvas.nFourth = this.imgCanvas.decrementNote(this.imgCanvas.nFourth);
                }
                if (n >= n3 + 80 && n <= n4 + 80) {
                    n5 = 4;
                    this.imgCanvas.nFifth = this.imgCanvas.decrementNote(this.imgCanvas.nFifth);
                }
                if (n >= n3 + 100 && n <= n4 + 100) {
                    n5 = 5;
                    this.imgCanvas.nSixth = this.imgCanvas.decrementNote(this.imgCanvas.nSixth);
                }
                if (this.bUserUpdate) {
                    this.imgCanvas.updateUserNote(n5, false);
                }
            }
            return this.bTuningChanged = true;
        }
        if (n2 > 48 && n2 <= 380) {
            b4 = true;
        }
        if (n >= 20 && n <= 135) {
            b3 = true;
        }
        if (b4 && b3) {
            if (this.bFirstFretClick) {
                this.appComp.setUserAccessEnabled(false);
                this.appComp.getBaseNoteList().deselect(0);
                this.appComp.getChordSymbols().deselect(0);
                this.bFirstFretClick = false;
            }
            if (this.appComp.getScopePos() > 1 || this.appComp.getScopeSize() < 12) {
                this.imgCanvas.resetScopeStatus();
                this.appComp.setScopePos(1);
                this.appComp.setScopeSize(12);
                this.nScopePos = 1;
                this.nScopeEndPos = 12;
                this.imgCanvas.setDisplaySize(this.nScopePos, this.nScopeEndPos);
            }
            this.imgCanvas.showUserNotes();
            this.imgCanvas.setUserNote((n - 5) / 19, (n2 - 45) / 26);
            if (n2 > 48 && n2 <= 64) {
                this.imgCanvas.setOpenStringStatus((n - 5) / 19);
            }
            return this.bUserUpdate = true;
        }
        return false;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.appComp.getScopePosValue()) {
            final int int1 = Integer.parseInt(this.appComp.getScopePosValue().getText());
            if (int1 >= 1 && int1 <= 12) {
                this.appComp.setScopePos(int1);
                this.nScopePos = int1;
                this.imgCanvas.enableScopeFrame();
                this.appComp.getShowScopeBtn().setLabel("Hide scope");
            }
        }
        if (actionEvent.getSource() == this.appComp.getScopeSizeValue()) {
            final int int2 = Integer.parseInt(this.appComp.getScopeSizeValue().getText());
            if (int2 >= 1 && int2 <= 12) {
                this.appComp.setScopeSize(int2);
                this.nScopeEndPos = int2;
                this.imgCanvas.enableScopeFrame();
                this.appComp.getShowScopeBtn().setLabel("Hide scope");
            }
        }
        if (actionEvent.getSource() == this.appComp.getDecScopeSizeBtn()) {
            int scopeSize = this.appComp.getScopeSize();
            if (scopeSize > 1) {
                this.appComp.setScopeSize(--scopeSize);
                this.nScopeEndPos = scopeSize;
                this.imgCanvas.enableScopeFrame();
                this.appComp.getShowScopeBtn().setLabel("Hide scope");
            }
        }
        if (actionEvent.getSource() == this.appComp.getIncScopeSizeBtn()) {
            int scopeSize2 = this.appComp.getScopeSize();
            if (scopeSize2 < 12) {
                this.appComp.setScopeSize(++scopeSize2);
                this.nScopeEndPos = scopeSize2;
                this.imgCanvas.enableScopeFrame();
                this.appComp.getShowScopeBtn().setLabel("Hide scope");
            }
        }
        if (actionEvent.getSource() == this.appComp.getDecScopePosBtn()) {
            int scopePos = this.appComp.getScopePos();
            if (scopePos > 1) {
                this.appComp.setScopePos(--scopePos);
                this.nScopePos = scopePos;
                this.imgCanvas.enableScopeFrame();
                this.appComp.getShowScopeBtn().setLabel("Hide scope");
            }
        }
        if (actionEvent.getSource() == this.appComp.getIncScopePosBtn()) {
            int scopePos2 = this.appComp.getScopePos();
            if (scopePos2 < 12) {
                this.appComp.setScopePos(++scopePos2);
                this.nScopePos = scopePos2;
                this.imgCanvas.enableScopeFrame();
                this.appComp.getShowScopeBtn().setLabel("Hide scope");
            }
        }
        if (actionEvent.getSource() == this.appComp.getShowScopeBtn()) {
            this.imgCanvas.scopeBtnPressed();
            if (this.imgCanvas.getShowScopeStatus()) {
                this.appComp.getShowScopeBtn().setLabel("Hide scope");
            }
            else {
                this.appComp.getShowScopeBtn().setLabel("Show scope");
            }
        }
        if (actionEvent.getSource() == this.appComp.getResetBtn()) {
            this.appComp.resetDetails();
            this.appComp.deselectItemsInList();
            this.appComp.deselectItemsInBaseNoteList();
            this.appComp.deselectItemsInScaleList();
            this.appComp.getTuningChoice().select(0);
            this.imgCanvas.resetChordResult(true);
            this.imgCanvas.resetFretPositions();
            this.imgCanvas.showUserNotes();
            this.imgCanvas.resetScopeStatus();
            this.appComp.setScopePos(1);
            this.appComp.setScopeSize(12);
            this.appComp.getShowScopeBtn().setLabel("Show scope");
            this.nScopePos = 1;
            this.nScopeEndPos = 12;
        }
        if (actionEvent.getSource() == this.appComp.getShowAllNotesBtn()) {
            if (this.imgCanvas.getShowNotesStatus()) {
                this.appComp.getShowAllNotesBtn().setLabel("Show all notes");
            }
            else {
                this.appComp.getShowAllNotesBtn().setLabel("Hide notes");
            }
            this.imgCanvas.allNotesBtnPressed();
        }
        this.appComp.setScopeButtonStatus(this.nScopePos, this.nScopeEndPos);
        this.imgCanvas.setDisplaySize(this.nScopePos, this.nScopeEndPos);
        this.imgCanvas.repaint();
    }
}
