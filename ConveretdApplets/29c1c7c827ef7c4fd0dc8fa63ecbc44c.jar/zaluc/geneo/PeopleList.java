// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.Vector;

class PeopleList
{
    private Globals globals;
    private int peopleCount;
    private Vector peopleList;
    private int familyCount;
    private Family[] familyList;
    public boolean debug;
    Position curPos;
    DrawingObject centerBox;
    DrawingObject selectedBox;
    Font nameFont;
    Font dateFont;
    FontMetrics nameFontMetrics;
    FontMetrics dateFontMetrics;
    int vertSpace;
    int horzSpace;
    int vertSpouseSpace;
    int vertLineOffset;
    int screenHeight;
    int screenWidth;
    int fontHeight;
    int fontWidth;
    int trunkLen;
    int branchLen;
    int vertPageAmt;
    int horzPageAmt;
    int vertIncAmt;
    int horzIncAmt;
    int shiftX;
    int shiftY;
    int vertMaxPos;
    int vertMinPos;
    int horzMaxPos;
    int horzMinPos;
    private boolean showHidden;
    Generation childGenerationList;
    Generation parentGenerationList;
    Generation leftMostVisibleGeneration;
    Generation rightMostVisibleGeneration;
    DrawingObject visibleList;
    
    public PeopleList(final Globals globals, final int peopleCount, final int familyCount) {
        this.debug = false;
        this.showHidden = false;
        this.globals = globals;
        this.peopleCount = peopleCount;
        this.familyCount = familyCount;
        this.peopleList = new Vector(peopleCount, 50);
        this.familyList = new Family[familyCount];
    }
    
    public synchronized Person newPerson(final int n) {
        Person person = null;
        if (n < this.peopleCount) {
            person = new Person(this.globals, this, n);
            if (person != null) {
                try {
                    if (n >= this.peopleList.size()) {
                        this.peopleList.setSize(n + 1);
                    }
                    this.peopleList.setElementAt(person, n);
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("PeopleList#newPerson, ArrayIndexOutOfBoundsException: " + ex.getMessage());
                    ex.printStackTrace();
                    person = null;
                }
            }
        }
        return person;
    }
    
    public synchronized int appendPerson() {
        final Person person = new Person(this.globals, this, this.peopleCount);
        if (person != null) {
            person.isBlank = true;
            person.complete();
            this.peopleList.setSize(this.peopleCount + 1);
            this.peopleList.setElementAt(person, this.peopleCount);
            return this.peopleCount++;
        }
        return -1;
    }
    
    public synchronized Family newFamily(final int n) {
        return this.familyList[n] = new Family(this.globals, this, n);
    }
    
    public synchronized Person getPerson(final int n) {
        Person person = null;
        if (n >= 0 && n < this.peopleCount && n < this.peopleList.size()) {
            person = this.peopleList.elementAt(n);
            if (person != null && (!person.isComplete || (!this.showHidden && person.hidden))) {
                person = null;
            }
        }
        return person;
    }
    
    public synchronized Person getPersonFromId(final int n) {
        Person person = null;
        for (int i = 0; i < this.peopleCount; ++i) {
            person = (Person)this.peopleList.elementAt(i);
            if (person != null) {
                if (person.id == n) {
                    if (!person.isComplete || (!this.showHidden && person.hidden)) {
                        person = null;
                        break;
                    }
                    break;
                }
                else {
                    person = null;
                }
            }
        }
        return person;
    }
    
    public synchronized Family getFamily(final int n) {
        if (n >= 0 && n < this.familyCount && this.familyList[n] != null && this.familyList[n].isComplete) {
            return this.familyList[n];
        }
        return null;
    }
    
    public int getCount() {
        return this.peopleCount;
    }
    
    public void setScreenSize(final int screenHeight, final int screenWidth) {
        if (this.debug) {
            System.out.println("PeopleList::setScreenSize (" + screenHeight + "," + screenWidth + ")");
        }
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.calcShifts();
    }
    
    public synchronized void setCenterPerson(final Person person) {
        if (person != null) {
            if (this.curPos == null) {
                this.curPos = new Position(person);
            }
            else if (this.curPos.centerPerson != person) {
                this.curPos.next = new Position(person);
                this.curPos.next.prev = this.curPos;
                this.curPos = this.curPos.next;
            }
            this.curPos.vertCurPos = -(this.screenHeight / 2);
            this.curPos.horzCurPos = -(this.screenWidth / 2);
            if (this.debug) {
                System.out.println("Peoplelist::setCenterPerson - screenHeight = " + this.screenHeight + ", screenWidth = " + this.screenWidth);
                System.out.println("Peoplelist::setCenterPerson - setting vertCurPos to " + this.curPos.vertCurPos + ", horzCurPos to " + this.curPos.horzCurPos);
            }
            this.calcShifts();
            this.createTree();
        }
    }
    
    public Person getCenterPerson() {
        if (this.curPos != null) {
            return this.curPos.centerPerson;
        }
        return null;
    }
    
    public synchronized void setSelectedBox(final Graphics graphics, final DrawingObject selectedBox) {
        if (this.selectedBox != null) {
            this.selectedBox.deselect(graphics);
        }
        this.selectedBox = selectedBox;
        if (this.selectedBox != null) {
            this.selectedBox.select(graphics);
        }
    }
    
    public Person getSelectedPerson() {
        return this.selectedBox.person;
    }
    
    public void setFonts(final Font nameFont, final Font dateFont, final FontMetrics nameFontMetrics, final FontMetrics dateFontMetrics) {
        this.nameFont = nameFont;
        this.dateFont = dateFont;
        this.nameFontMetrics = nameFontMetrics;
        this.dateFontMetrics = dateFontMetrics;
        if (this.centerBox != null) {
            this.centerBox.calcChildDimensions();
            if (this.centerBox.father != null) {
                this.centerBox.father.calcParentDimensions();
            }
            if (this.centerBox.mother != null) {
                this.centerBox.mother.calcParentDimensions();
            }
        }
    }
    
    public void showHidden() {
        this.showHidden = true;
    }
    
    public boolean shouldDraw() {
        return this.curPos == null || this.curPos.centerPerson == null || this.curPos.centerPerson.shouldDrawChildren() || this.curPos.centerPerson.shouldDrawParents();
    }
    
    public synchronized void calcTree() {
        this.horzMaxPos = 0;
        this.horzMinPos = 0;
        this.vertMaxPos = 0;
        this.vertMinPos = 0;
        if (this.debug) {
            System.out.println("PeopleList::calcTree - shiftX = " + this.shiftX + ", shiftY = " + this.shiftY);
        }
        if (this.centerBox != null && this.dateFontMetrics != null) {
            this.fontHeight = this.dateFontMetrics.getHeight();
            this.fontWidth = this.dateFontMetrics.charWidth('F');
            this.trunkLen = 2 * this.fontWidth;
            this.branchLen = 2 * this.fontWidth;
            this.vertSpace = 2 * this.fontHeight;
            this.vertSpouseSpace = this.fontHeight;
            this.vertLineOffset = this.fontWidth;
            this.horzSpace = this.trunkLen + this.branchLen;
            this.vertIncAmt = 2 * this.vertSpace;
            this.vertPageAmt = this.screenHeight - this.vertIncAmt;
            this.horzIncAmt = 4 * this.branchLen;
            this.horzPageAmt = this.screenWidth - this.horzIncAmt;
            boolean b = true;
            for (Generation generation = this.childGenerationList; generation != null; generation = generation.nextGeneration) {
                this.horzMinPos = generation.calcChildDim(b, false, this.horzMinPos);
                b = false;
            }
            this.horzMaxPos = this.childGenerationList.leftX + this.childGenerationList.dim.width;
            for (Generation generation2 = this.parentGenerationList; generation2 != null; generation2 = generation2.nextGeneration) {
                this.horzMaxPos = generation2.calcParentDim(this.horzMaxPos);
            }
            this.calcYPositions();
            boolean b2 = true;
            this.horzMaxPos = 0;
            this.horzMinPos = 0;
            for (Generation generation3 = this.childGenerationList; generation3 != null; generation3 = generation3.nextGeneration) {
                this.horzMinPos = generation3.calcChildDim(b2, true, this.horzMinPos);
                b2 = false;
            }
            this.horzMaxPos = this.childGenerationList.leftX + this.childGenerationList.dim.width;
            for (Generation generation4 = this.parentGenerationList; generation4 != null; generation4 = generation4.nextGeneration) {
                this.horzMaxPos = generation4.calcParentDim(this.horzMaxPos);
            }
        }
    }
    
    public int getVertMaxPos() {
        return this.vertMaxPos;
    }
    
    public int getVertMinPos() {
        return this.vertMinPos;
    }
    
    public int getVertCurPos() {
        if (this.curPos != null) {
            return this.curPos.vertCurPos;
        }
        return 0;
    }
    
    public int getHorzMaxPos() {
        return this.horzMaxPos;
    }
    
    public int getHorzMinPos() {
        return this.horzMinPos;
    }
    
    public int getHorzCurPos() {
        if (this.curPos != null) {
            return this.curPos.horzCurPos;
        }
        return 0;
    }
    
    public void drawTree(final Graphics graphics) {
        this.visibleList = null;
        if (this.debug) {
            System.out.println("PeopleList::drawTree - shiftX = " + this.shiftX + ", shiftY = " + this.shiftY);
        }
        if (this.centerBox != null) {
            if (this.debug) {
                graphics.setColor(Color.red);
                graphics.drawLine(this.horzMinPos + this.shiftX, this.vertMinPos + this.shiftY, this.horzMaxPos + this.shiftX - 1, this.vertMinPos + this.shiftY);
                graphics.drawLine(this.horzMinPos + this.shiftX, this.vertMinPos + this.shiftY, this.horzMinPos + this.shiftX, this.vertMaxPos + this.shiftY - 1);
                graphics.drawLine(this.horzMaxPos + this.shiftX - 1, this.vertMaxPos + this.shiftY - 1, this.horzMaxPos + this.shiftX - 1, this.vertMinPos + this.shiftY);
                graphics.drawLine(this.horzMaxPos + this.shiftX - 1, this.vertMaxPos + this.shiftY - 1, this.horzMinPos + this.shiftX, this.vertMaxPos + this.shiftY - 1);
            }
            this.centerBox.drawChildren(graphics);
            this.centerBox.drawParents(graphics);
        }
    }
    
    public boolean back() {
        if (this.curPos != null && this.curPos.prev != null) {
            this.curPos = this.curPos.prev;
            this.calcShifts();
            this.createTree();
            return true;
        }
        return false;
    }
    
    public boolean forward() {
        if (this.curPos != null && this.curPos.next != null) {
            this.curPos = this.curPos.next;
            this.calcShifts();
            this.createTree();
            return true;
        }
        return false;
    }
    
    public boolean pageLeft() {
        return this.leftMostVisibleGeneration != null && this.curPos != null && this.setHorz(this.curPos.horzCurPos - (this.screenWidth - this.leftMostVisibleGeneration.dim.width));
    }
    
    public boolean pageRight() {
        return this.rightMostVisibleGeneration != null && this.curPos != null && this.setHorz(this.curPos.horzCurPos + (this.screenWidth - this.rightMostVisibleGeneration.dim.width));
    }
    
    public boolean pageUp() {
        return this.curPos != null && this.setVert(this.curPos.vertCurPos - this.vertPageAmt);
    }
    
    public boolean pageDown() {
        return this.curPos != null && this.setVert(this.curPos.vertCurPos + this.vertPageAmt);
    }
    
    public boolean incLeft() {
        return this.leftMostVisibleGeneration != null && this.curPos != null && this.setHorz(this.curPos.horzCurPos - this.leftMostVisibleGeneration.dim.width);
    }
    
    public boolean incRight() {
        return this.rightMostVisibleGeneration != null && this.curPos != null && this.setHorz(this.curPos.horzCurPos + this.rightMostVisibleGeneration.dim.width);
    }
    
    public boolean incUp() {
        return this.curPos != null && this.setVert(this.curPos.vertCurPos - this.vertIncAmt);
    }
    
    public boolean incDown() {
        return this.curPos != null && this.setVert(this.curPos.vertCurPos + this.vertIncAmt);
    }
    
    public boolean setVert(int vertCurPos) {
        if (this.debug) {
            System.out.println("PeopleList::setVert(" + vertCurPos + ") - screenHeight = " + this.screenHeight);
            System.out.println("                                   vertMinPos   = " + this.vertMinPos);
            System.out.println("                                   vertMaxPos   = " + this.vertMaxPos);
        }
        if (this.curPos != null) {
            if (this.vertMaxPos - this.vertMinPos > this.screenHeight) {
                if (vertCurPos < this.vertMinPos) {
                    vertCurPos = this.vertMinPos;
                }
                else if (vertCurPos + this.screenHeight > this.vertMaxPos) {
                    vertCurPos = this.vertMaxPos - this.screenHeight;
                }
            }
            else if (this.vertMinPos < vertCurPos) {
                vertCurPos = this.vertMinPos;
            }
            else if (this.vertMaxPos > vertCurPos + this.screenHeight) {
                vertCurPos = this.vertMaxPos - this.screenHeight;
            }
            if (vertCurPos != this.curPos.vertCurPos) {
                this.shiftY = -vertCurPos;
                this.curPos.vertCurPos = vertCurPos;
                if (this.debug) {
                    System.out.println("PeopleList::setVert - setting vertCurPos to " + this.curPos.vertCurPos);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean setHorz(int horzCurPos) {
        if (this.debug) {
            System.out.println("PeopleList::setHorz(" + horzCurPos + ") - screenWidth = " + this.screenWidth);
            System.out.println("                                   horzMinPos  = " + this.horzMinPos);
            System.out.println("                                   horzMaxPos  = " + this.horzMaxPos);
        }
        if (this.curPos != null) {
            if (this.horzMaxPos - this.horzMinPos > this.screenWidth) {
                if (horzCurPos < this.horzMinPos) {
                    horzCurPos = this.horzMinPos;
                }
                else if (horzCurPos + this.screenWidth > this.horzMaxPos) {
                    horzCurPos = this.horzMaxPos - this.screenWidth;
                }
            }
            else if (this.horzMinPos < horzCurPos) {
                horzCurPos = this.horzMinPos;
            }
            else if (this.horzMaxPos > horzCurPos + this.screenWidth) {
                horzCurPos = this.horzMaxPos - this.screenWidth;
            }
            if (horzCurPos != this.curPos.horzCurPos) {
                this.shiftX = -horzCurPos;
                this.curPos.horzCurPos = horzCurPos;
                if (this.debug) {
                    System.out.println("PeopleList::setHorz - setting horzCurPos to " + this.curPos.vertCurPos);
                }
                this.calcTree();
                this.setVert(this.curPos.vertCurPos);
                return true;
            }
        }
        return false;
    }
    
    public boolean home() {
        if (this.curPos != null) {
            System.out.println("PeopleList::home");
            this.curPos.vertCurPos = -(this.screenHeight / 2);
            this.curPos.horzCurPos = -(this.screenWidth / 2);
            this.calcShifts();
            this.calcTree();
            return true;
        }
        return false;
    }
    
    public DrawingObject getBoxUnderPoint(final int n, final int n2) {
        for (DrawingObject drawingObject = this.visibleList; drawingObject != null; drawingObject = drawingObject.nextVisible) {
            if (drawingObject.isUnderPoint(n - this.shiftX, n2 - this.shiftY)) {
                return drawingObject;
            }
        }
        return null;
    }
    
    public Person getPersonUnderPoint(final int n, final int n2) {
        final DrawingObject boxUnderPoint = this.getBoxUnderPoint(n, n2);
        if (boxUnderPoint != null) {
            return boxUnderPoint.person;
        }
        return null;
    }
    
    private void createTree() {
        if (this.curPos != null && this.curPos.centerPerson.isComplete) {
            this.centerBox = new DrawingObject(this.globals, this, this.curPos.centerPerson);
            this.selectedBox = this.centerBox;
            this.centerBox.createChildTree();
            this.childGenerationList = this.centerBox.addToChildGenerationList(null);
            this.centerBox.calcChildDimensions();
            this.centerBox.createParentTree();
            this.parentGenerationList = null;
            if (this.centerBox.father != null) {
                this.parentGenerationList = this.centerBox.father.addToParentGenerationList(null);
                this.centerBox.father.calcParentDimensions();
            }
            if (this.centerBox.mother != null) {
                this.parentGenerationList = this.centerBox.mother.addToParentGenerationList(this.parentGenerationList);
                this.centerBox.mother.calcParentDimensions();
            }
        }
    }
    
    private void calcYPositions() {
        this.vertMaxPos = 0;
        this.vertMinPos = 0;
        this.leftMostVisibleGeneration = null;
        this.rightMostVisibleGeneration = null;
        this.centerBox.calcChildY(-(this.centerBox.calcChildSpacing(this.childGenerationList) / 2));
        if (this.parentGenerationList != null) {
            final DrawingObject father = this.centerBox.father;
            final DrawingObject mother = this.centerBox.mother;
            int calcParentSpacing = 0;
            if (father != null) {
                calcParentSpacing = father.calcParentSpacing(this.parentGenerationList);
            }
            if (mother != null) {
                calcParentSpacing += mother.calcParentSpacing(this.parentGenerationList);
            }
            int n = -(calcParentSpacing / 2);
            if (father != null) {
                n += father.calcParentY(n);
            }
            if (mother != null) {
                mother.calcParentY(n);
            }
        }
    }
    
    private void calcShifts() {
        if (this.curPos != null) {
            this.shiftY = -this.curPos.vertCurPos;
            this.shiftX = -this.curPos.horzCurPos;
        }
        else {
            this.shiftY = this.screenHeight / 2;
            this.shiftX = this.screenWidth / 2;
        }
        if (this.debug) {
            System.out.println("PeopleList::calcShifts - setting shiftX to " + this.shiftX + ", shiftY to " + this.shiftY);
        }
    }
    
    private class Position
    {
        public Position next;
        public Position prev;
        public Person centerPerson;
        public int vertCurPos;
        public int horzCurPos;
        
        Position(final Person centerPerson) {
            this.centerPerson = centerPerson;
        }
    }
}
