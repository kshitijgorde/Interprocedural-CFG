// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.Rectangle;

class DrawingObject
{
    private Globals globals;
    private PeopleList plist;
    public Person person;
    public Rectangle rect;
    public int rightAnchorX;
    public int rightAnchorY;
    public int leftAnchorX;
    public int leftAnchorY;
    public int topAnchorX;
    public int topAnchorY;
    public int bottomAnchorX;
    public int bottomAnchorY;
    public int vertChildLineOffset;
    private int cumulativeHeight;
    private int spousesHeight;
    private int childrensHeight;
    private Generation myGeneration;
    public Vector children;
    public Vector spouses;
    public DrawingObject father;
    public DrawingObject mother;
    public DrawingObject nextInGeneration;
    public DrawingObject nextVisible;
    public DrawingObject next;
    private final int selectionBorderWidth = 3;
    private final int selectionBoxOffset = 1;
    private boolean selected;
    
    public DrawingObject(final Globals globals, final PeopleList plist, final Person person) {
        this.children = new Vector(5, 5);
        this.spouses = new Vector(3, 3);
        this.selected = false;
        this.globals = globals;
        this.plist = plist;
        this.person = person;
        this.rect = new Rectangle();
        this.person.drawMe = true;
    }
    
    public void createChildTree() {
        Family family;
        for (int n = 0; (family = this.person.getFamily(n)) != null; ++n) {
            Person person;
            if (this.person.sex == 1) {
                person = this.plist.getPerson(family.mother);
            }
            else {
                person = this.plist.getPerson(family.father);
            }
            DrawingObject drawingObject;
            if (person != null) {
                drawingObject = new DrawingObject(this.globals, this.plist, person);
                this.spouses.addElement(drawingObject);
            }
            else {
                drawingObject = this;
            }
            Person child;
            for (int n2 = 0; (child = family.getChild(n2)) != null; ++n2) {
                final DrawingObject drawingObject2 = new DrawingObject(this.globals, this.plist, child);
                drawingObject.children.addElement(drawingObject2);
                drawingObject2.createChildTree();
            }
        }
    }
    
    public void createParentTree() {
        final Person person = this.plist.getPerson(this.person.mother);
        final Person person2 = this.plist.getPerson(this.person.father);
        if (person != null) {
            (this.mother = new DrawingObject(this.globals, this.plist, person)).createParentTree();
        }
        if (person2 != null) {
            (this.father = new DrawingObject(this.globals, this.plist, person2)).createParentTree();
        }
    }
    
    public boolean isUnderPoint(final int n, final int n2) {
        return n >= this.rect.x && n <= this.rect.x + this.rect.width && n2 >= this.rect.y && n2 <= this.rect.y + this.rect.height;
    }
    
    public Generation addToChildGenerationList(Generation generation) {
        if (generation == null) {
            generation = new Generation(this.plist);
        }
        this.myGeneration = generation;
        this.nextInGeneration = generation.firstInGeneration;
        generation.firstInGeneration = this;
        for (int size = this.children.size(), i = 0; i < size; ++i) {
            generation.nextGeneration = ((DrawingObject)this.children.elementAt(i)).addToChildGenerationList(generation.nextGeneration);
        }
        for (int size2 = this.spouses.size(), j = 0; j < size2; ++j) {
            final DrawingObject drawingObject = this.spouses.elementAt(j);
            drawingObject.myGeneration = generation;
            for (int size3 = drawingObject.children.size(), k = 0; k < size3; ++k) {
                generation.nextGeneration = ((DrawingObject)drawingObject.children.elementAt(k)).addToChildGenerationList(generation.nextGeneration);
            }
        }
        return generation;
    }
    
    public Generation addToParentGenerationList(Generation myGeneration) {
        if (myGeneration == null) {
            myGeneration = new Generation(this.plist);
        }
        this.myGeneration = myGeneration;
        this.nextInGeneration = myGeneration.firstInGeneration;
        myGeneration.firstInGeneration = this;
        if (this.father != null) {
            myGeneration.nextGeneration = this.father.addToParentGenerationList(myGeneration.nextGeneration);
        }
        if (this.mother != null) {
            myGeneration.nextGeneration = this.mother.addToParentGenerationList(myGeneration.nextGeneration);
        }
        return myGeneration;
    }
    
    public void calcChildDimensions() {
        this.calcDim();
        for (int size = this.children.size(), i = 0; i < size; ++i) {
            ((DrawingObject)this.children.elementAt(i)).calcChildDimensions();
        }
        for (int size2 = this.spouses.size(), j = 0; j < size2; ++j) {
            final DrawingObject drawingObject = this.spouses.elementAt(j);
            drawingObject.calcDim();
            for (int size3 = drawingObject.children.size(), k = 0; k < size3; ++k) {
                ((DrawingObject)drawingObject.children.elementAt(k)).calcChildDimensions();
            }
        }
    }
    
    public void calcParentDimensions() {
        this.calcDim();
        if (this.father != null) {
            this.father.calcParentDimensions();
        }
        if (this.mother != null) {
            this.mother.calcParentDimensions();
        }
    }
    
    public Dimension calcDim() {
        int n = 0;
        int stringWidth;
        if (this.person.fullName != null && this.plist.nameFontMetrics != null) {
            stringWidth = this.plist.nameFontMetrics.stringWidth(this.person.fullName);
            n = this.plist.nameFontMetrics.getHeight();
        }
        else {
            stringWidth = 0;
        }
        int stringWidth2;
        if (this.person.lifeDates != null && this.plist.dateFontMetrics != null) {
            stringWidth2 = this.plist.dateFontMetrics.stringWidth(this.person.lifeDates);
            n += this.plist.dateFontMetrics.getHeight();
        }
        else {
            stringWidth2 = 0;
        }
        if (n == 0 && this.plist.dateFontMetrics != null) {
            n = this.plist.dateFontMetrics.getHeight();
        }
        this.rect.width = Math.max(stringWidth, stringWidth2) + 2 * this.globals.peopleBoxBorderWidth + 2 + 6;
        this.rect.height = n + 2 * this.globals.peopleBoxBorderWidth + 6;
        return new Dimension(this.rect.width, this.rect.height);
    }
    
    public int calcChildSpacing(final Generation generation) {
        this.setX(this.myGeneration.leftX + this.myGeneration.maxVertLineOffset + this.plist.trunkLen);
        final Generation nextGeneration = generation.nextGeneration;
        this.spousesHeight = this.rect.height + this.plist.vertSpace;
        this.childrensHeight = 0;
        if (this.shouldDrawAsChild()) {
            for (int size = this.children.size(), i = 0; i < size; ++i) {
                this.childrensHeight += ((DrawingObject)this.children.elementAt(i)).calcChildSpacing(nextGeneration);
            }
            for (int size2 = this.spouses.size(), j = 0; j < size2; ++j) {
                final DrawingObject drawingObject = this.spouses.elementAt(j);
                drawingObject.setX(this.rect.x);
                this.spousesHeight += drawingObject.rect.height + this.plist.vertSpouseSpace;
                for (int size3 = drawingObject.children.size(), k = 0; k < size3; ++k) {
                    this.childrensHeight += ((DrawingObject)drawingObject.children.elementAt(k)).calcChildSpacing(nextGeneration);
                }
            }
            this.cumulativeHeight = Math.max(this.spousesHeight, this.childrensHeight);
        }
        else {
            this.spousesHeight = 0;
            this.childrensHeight = 0;
            this.cumulativeHeight = 0;
        }
        return this.cumulativeHeight;
    }
    
    public int calcParentSpacing(final Generation generation) {
        this.cumulativeHeight = 0;
        final Generation nextGeneration = generation.nextGeneration;
        if (nextGeneration != null) {
            if (this.father != null && this.father.shouldDrawAsParent()) {
                this.cumulativeHeight += this.father.calcParentSpacing(nextGeneration);
            }
            if (this.mother != null && this.mother.shouldDrawAsParent()) {
                this.cumulativeHeight += this.mother.calcParentSpacing(nextGeneration);
            }
        }
        if (this.cumulativeHeight == 0) {
            this.cumulativeHeight = this.rect.height + this.plist.vertSpace;
        }
        return this.cumulativeHeight;
    }
    
    public int calcChildY(final int n) {
        int n2;
        if (this.childrensHeight < this.spousesHeight) {
            this.setY(n + this.plist.vertSpace / 2);
            n2 = n + (this.spousesHeight - this.childrensHeight) / 2;
        }
        else {
            this.setY(n + (this.childrensHeight - this.spousesHeight + this.plist.vertSpace) / 2);
            n2 = n;
        }
        int y = this.rect.y;
        final boolean b = this.myGeneration.nextGeneration != null && this.myGeneration.nextGeneration.shouldDrawGenAsChild();
        if (this.plist.leftMostVisibleGeneration == null || this.myGeneration.leftX < this.plist.leftMostVisibleGeneration.leftX) {
            this.plist.leftMostVisibleGeneration = this.myGeneration;
        }
        if (b) {
            for (int size = this.children.size(), i = 0; i < size; ++i) {
                n2 += ((DrawingObject)this.children.elementAt(i)).calcChildY(n2);
            }
        }
        final int size2 = this.spouses.size();
        DrawingObject drawingObject = this;
        for (int j = 0; j < size2; ++j) {
            final DrawingObject drawingObject2 = this.spouses.elementAt(j);
            y += drawingObject.rect.height + this.plist.vertSpouseSpace;
            drawingObject2.setY(y);
            drawingObject = drawingObject2;
            if (b) {
                for (int size3 = drawingObject2.children.size(), k = 0; k < size3; ++k) {
                    n2 += ((DrawingObject)drawingObject2.children.elementAt(k)).calcChildY(n2);
                }
            }
        }
        if (this.shouldDrawAsParent() && (this.plist.rightMostVisibleGeneration == null || this.myGeneration.leftX > this.plist.rightMostVisibleGeneration.leftX)) {
            this.plist.rightMostVisibleGeneration = this.myGeneration;
        }
        return this.cumulativeHeight;
    }
    
    public int calcParentY(final int n) {
        int n2 = n;
        this.setY(n + (this.cumulativeHeight - this.rect.height) / 2);
        if (this.plist.rightMostVisibleGeneration == null || this.myGeneration.leftX > this.plist.rightMostVisibleGeneration.leftX) {
            this.plist.rightMostVisibleGeneration = this.myGeneration;
        }
        if (this.father != null && this.father.shouldDrawAsParent()) {
            n2 += this.father.calcParentY(n2);
        }
        if (this.mother != null && this.mother.shouldDrawAsParent()) {
            final int n3 = n2 + this.mother.calcParentY(n2);
        }
        if (this.shouldDrawAsChild() && (this.plist.leftMostVisibleGeneration == null || this.myGeneration.leftX < this.plist.leftMostVisibleGeneration.leftX)) {
            this.plist.leftMostVisibleGeneration = this.myGeneration;
        }
        return this.cumulativeHeight;
    }
    
    public void setX(final int x) {
        this.rect.x = x;
        this.leftAnchorX = this.rect.x;
        this.rightAnchorX = this.rect.x + this.rect.width;
        final int n = this.rect.x + this.rect.width / 2;
        this.bottomAnchorX = n;
        this.topAnchorX = n;
    }
    
    public void setY(final int y) {
        this.rect.y = y;
        final int n = this.rect.y + this.rect.height / 2;
        this.leftAnchorY = n;
        this.rightAnchorY = n;
        this.topAnchorY = this.rect.y;
        this.bottomAnchorY = this.rect.y + this.rect.height;
        if (this.rect.y < 0) {
            this.plist.vertMinPos = Math.min(this.plist.vertMinPos, this.rect.y - this.plist.vertSpace);
        }
        if (this.rect.y + this.rect.height > 0) {
            this.plist.vertMaxPos = Math.max(this.plist.vertMaxPos, this.rect.y + this.rect.height + this.plist.vertSpace);
        }
    }
    
    public void drawChildren(final Graphics graphics) {
        this.setX(this.myGeneration.leftX + this.myGeneration.maxVertLineOffset + this.plist.trunkLen);
        this.draw(graphics);
        final boolean b = this.myGeneration.nextGeneration != null && this.myGeneration.nextGeneration.shouldDrawGenAsChild();
        final int size = this.children.size();
        if (b) {
            for (int i = 0; i < size; ++i) {
                final DrawingObject drawingObject = this.children.elementAt(i);
                drawingObject.drawChildren(graphics);
                this.drawLineTo(graphics, drawingObject);
            }
        }
        else if (size != 0) {
            this.drawLine(graphics, this.leftAnchorX + this.plist.shiftX, this.leftAnchorY + this.plist.shiftY, 0, this.leftAnchorY + this.plist.shiftY);
        }
        DrawingObject drawingObject2 = this;
        for (int size2 = this.spouses.size(), j = 0; j < size2; ++j) {
            final DrawingObject drawingObject3 = this.spouses.elementAt(j);
            drawingObject3.setX(this.rect.x);
            drawingObject3.draw(graphics);
            drawingObject2.drawSpouseLineTo(graphics, drawingObject3);
            drawingObject2 = drawingObject3;
            final int size3 = drawingObject3.children.size();
            if (b) {
                for (int k = 0; k < size3; ++k) {
                    final DrawingObject drawingObject4 = drawingObject3.children.elementAt(k);
                    drawingObject4.drawChildren(graphics);
                    drawingObject3.drawLineTo(graphics, drawingObject4);
                }
            }
            else if (size3 != 0) {
                this.drawLine(graphics, drawingObject3.leftAnchorX + this.plist.shiftX, drawingObject3.leftAnchorY + this.plist.shiftY, 0, drawingObject3.leftAnchorY + this.plist.shiftY);
            }
        }
    }
    
    public void drawParents(final Graphics graphics) {
        boolean b = false;
        if (this.father != null) {
            if (this.father.shouldDrawAsParent()) {
                this.father.draw(graphics);
                this.father.drawLineTo(graphics, this);
                this.father.drawParents(graphics);
            }
            else {
                b = true;
            }
        }
        if (this.mother != null) {
            if (this.mother.shouldDrawAsParent()) {
                this.mother.draw(graphics);
                this.mother.drawLineTo(graphics, this);
                this.mother.drawParents(graphics);
            }
            else {
                b = true;
            }
        }
        if (b) {
            this.drawLine(graphics, this.rightAnchorX + this.plist.shiftX, this.rightAnchorY + this.plist.shiftY, this.plist.screenWidth, this.rightAnchorY + this.plist.shiftY);
        }
    }
    
    public void select(final Graphics graphics) {
        this.selected = true;
        this.drawSelectionBox(graphics);
    }
    
    public void deselect(final Graphics graphics) {
        this.selected = false;
        this.clearSelectionBox(graphics);
    }
    
    private boolean shouldDrawAsChild() {
        return this.myGeneration.shouldDrawGenAsChild();
    }
    
    private boolean shouldDrawAsParent() {
        return this.myGeneration.shouldDrawGenAsParent();
    }
    
    private void draw(final Graphics graphics) {
        if (!this.person.isBlank) {
            this.nextVisible = this.plist.visibleList;
            this.plist.visibleList = this;
        }
        this.drawBox(graphics);
        if (this.selected) {
            this.drawSelectionBox(graphics);
        }
        if (this.person.fullName != null && this.plist.nameFont != null) {
            graphics.setFont(this.plist.nameFont);
            graphics.drawString(this.person.fullName, this.rect.x + this.plist.shiftX + this.globals.peopleBoxBorderWidth + 3 + 1, this.rect.y + this.plist.shiftY + this.globals.peopleBoxBorderWidth + 3 + this.plist.fontHeight - 2);
        }
        if (this.person.lifeDates != null && this.plist.dateFont != null) {
            graphics.setFont(this.plist.dateFont);
            graphics.drawString(this.person.lifeDates, this.rect.x + this.plist.shiftX + this.globals.peopleBoxBorderWidth + 3 + 1, this.rect.y + this.plist.shiftY + this.globals.peopleBoxBorderWidth + 3 + 2 * this.plist.fontHeight - 2);
        }
    }
    
    private void drawBox(final Graphics graphics) {
        if (this.isVisibleOnScreen(this.rect.x + this.plist.shiftX, this.rect.y + this.plist.shiftY, this.rect.x + this.plist.shiftX + this.rect.width, this.rect.y + this.plist.shiftY + this.rect.height)) {
            if (this.globals.peopleBoxBkg != null) {
                graphics.setColor(this.globals.peopleBoxBkg);
                graphics.fillRect(this.rect.x + this.plist.shiftX, this.rect.y + this.plist.shiftY, this.rect.width, this.rect.height);
            }
            if (this.globals.peopleBoxBorderWidth > 1) {
                final int n = this.rect.y + this.plist.shiftY;
                final int n2 = this.rect.x + this.plist.shiftX;
                final int n3 = this.rect.y + this.plist.shiftY + this.rect.height;
                final int n4 = this.rect.x + this.plist.shiftX + this.rect.width;
                int n5;
                int n6;
                int n7;
                if (this.globals.peopleBoxBkg != null && this.globals.backgroundImage != null) {
                    n5 = this.globals.peopleBoxBkg.getRed();
                    n6 = this.globals.peopleBoxBkg.getGreen();
                    n7 = this.globals.peopleBoxBkg.getBlue();
                }
                else {
                    n5 = this.globals.backgroundColor.getRed();
                    n6 = this.globals.backgroundColor.getGreen();
                    n7 = this.globals.backgroundColor.getBlue();
                }
                final Color color = new Color(n5 + 3 * ((255 - n5) / 5), n6 + 3 * ((255 - n6) / 5), n7 + 3 * ((255 - n7) / 5));
                final int n8 = n5 / 5;
                final int n9 = n6 / 5;
                final int n10 = n7 / 5;
                final Color color2 = new Color(3 * n8, 3 * n9, 3 * n10);
                final Color color3 = new Color(4 * n8, 4 * n9, 4 * n10);
                graphics.setColor(color);
                int i;
                for (i = 0; i < this.globals.peopleBoxBorderWidth - 1; ++i) {
                    this.drawLine(graphics, n4 - i, n3 - i, n4 - i, n + i);
                    this.drawLine(graphics, n4 - i, n3 - i, n2 + i, n3 - i);
                }
                graphics.setColor(color3);
                this.drawLine(graphics, n4 - i, n3 - i, n4 - i, n + i);
                this.drawLine(graphics, n4 - i, n3 - i, n2 + i, n3 - i);
                graphics.setColor(color3);
                int j;
                for (j = 0; j < this.globals.peopleBoxBorderWidth - 1; ++j) {
                    this.drawLine(graphics, n2 + j, n + j, n4 - j, n + j);
                    this.drawLine(graphics, n2 + j, n + j, n2 + j, n3 - j);
                }
                graphics.setColor(color2);
                this.drawLine(graphics, n2 + j, n + j, n4 - j, n + j);
                this.drawLine(graphics, n2 + j, n + j, n2 + j, n3 - j);
                graphics.setColor(this.globals.foregroundColor);
                return;
            }
            graphics.setColor(this.globals.foregroundColor);
            graphics.drawRect(this.rect.x + this.plist.shiftX, this.rect.y + this.plist.shiftY, this.rect.width, this.rect.height);
        }
    }
    
    private void drawSelectionBox(final Graphics graphics) {
        final int n = this.rect.y + this.plist.shiftY + this.globals.peopleBoxBorderWidth + 1;
        final int n2 = this.rect.x + this.plist.shiftX + this.globals.peopleBoxBorderWidth + 1;
        final int n3 = this.rect.y + this.plist.shiftY - this.globals.peopleBoxBorderWidth - 1 + this.rect.height;
        final int n4 = this.rect.x + this.plist.shiftX - this.globals.peopleBoxBorderWidth - 1 + this.rect.width;
        graphics.setColor(this.globals.foregroundColor);
        this.drawDashedHorzLine(graphics, n2, n4, n);
        this.drawDashedHorzLine(graphics, n2, n4, n3);
        this.drawDashedVertLine(graphics, n, n3, n2);
        this.drawDashedVertLine(graphics, n, n3, n4);
    }
    
    private void clearSelectionBox(final Graphics graphics) {
        final int n = this.rect.y + this.plist.shiftY + this.globals.peopleBoxBorderWidth + 1;
        final int n2 = this.rect.x + this.plist.shiftX + this.globals.peopleBoxBorderWidth + 1;
        final int n3 = this.rect.y + this.plist.shiftY - this.globals.peopleBoxBorderWidth - 1 + this.rect.height;
        final int n4 = this.rect.x + this.plist.shiftX - this.globals.peopleBoxBorderWidth - 1 + this.rect.width;
        if (this.globals.peopleBoxBkg != null) {
            graphics.setColor(this.globals.peopleBoxBkg);
        }
        else {
            graphics.setColor(this.globals.backgroundColor);
        }
        this.drawLine(graphics, n2, n, n4, n);
        this.drawLine(graphics, n2, n3, n4, n3);
        this.drawLine(graphics, n2, n, n2, n3);
        this.drawLine(graphics, n4, n, n4, n3);
    }
    
    private void drawLineTo(final Graphics graphics, final DrawingObject drawingObject) {
        final int trunkLen = this.plist.trunkLen;
        if (this.rect.x > drawingObject.rect.x) {
            final int n = this.leftAnchorX + this.plist.shiftX - 1;
            final int n2 = this.leftAnchorY + this.plist.shiftY;
            final int n3 = drawingObject.rightAnchorX + this.plist.shiftX + 1;
            final int n4 = drawingObject.rightAnchorY + this.plist.shiftY;
            final int n5 = n - trunkLen - this.myGeneration.maxVertLineOffset + this.vertChildLineOffset;
            this.drawLine(graphics, n, n2, n5, n2);
            this.drawLine(graphics, n5, n2, n5, n4);
            this.drawLine(graphics, n5, n4, n3, n4);
            return;
        }
        System.out.println("Error: drawing line backwards from " + this.person.id + " at " + this.rect.x + " to " + drawingObject.person.id + " at " + drawingObject.rect.x);
    }
    
    private void drawSpouseLineTo(final Graphics graphics, final DrawingObject drawingObject) {
        final int n = this.rect.x + this.myGeneration.spouseLineXOffset + this.plist.shiftX - 1;
        final int n2 = this.bottomAnchorY + this.plist.shiftY;
        final int n3 = drawingObject.topAnchorY + this.plist.shiftY;
        this.drawLine(graphics, n - 1, n2, n - 1, n3);
        this.drawLine(graphics, n + 1, n2, n + 1, n3);
    }
    
    private void drawLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (this.isVisibleOnScreen(n, n2, n3, n4)) {
            graphics.drawLine(n, n2, n3, n4);
        }
    }
    
    private boolean isVisibleOnScreen(final int n, final int n2, final int n3, final int n4) {
        final boolean b = (n >= 0 && n <= this.plist.screenWidth) || (n3 >= 0 && n3 <= this.plist.screenWidth);
        final boolean b2 = (n2 >= 0 && n2 <= this.plist.screenHeight) || (n4 >= 0 && n4 <= this.plist.screenHeight);
        final boolean b3 = (n <= 0 && n3 >= this.plist.screenWidth) || (n3 <= 0 && n >= this.plist.screenWidth);
        final boolean b4 = (n2 <= 0 && n4 >= this.plist.screenHeight) || (n4 <= 0 && n2 >= this.plist.screenHeight);
        return (b || b3) && (b2 || b4);
    }
    
    private void drawDashedHorzLine(final Graphics graphics, final int n, final int n2, final int n3) {
        for (int n4 = 1, i = n; i <= n2; i += 5 * n4) {
            int n5;
            if (i + n4 > n2) {
                n5 = n2;
            }
            else {
                n5 = i + n4;
            }
            this.drawLine(graphics, i, n3, n5, n3);
        }
    }
    
    private void drawDashedVertLine(final Graphics graphics, final int n, final int n2, final int n3) {
        for (int n4 = 1, i = n; i <= n2; i += 5 * n4) {
            int n5;
            if (i + n4 > n2) {
                n5 = n2;
            }
            else {
                n5 = i + n4;
            }
            this.drawLine(graphics, n3, i, n3, n5);
        }
    }
}
