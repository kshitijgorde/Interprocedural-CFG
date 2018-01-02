import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class QMQuestion
{
    private Image picture;
    private Vector textQuestion;
    private Vector textSolution1;
    private Vector textSolution2;
    private Vector answers;
    private int correct;
    private int selected;
    private int pointed;
    public QMData qmd;
    
    public QMQuestion(final QMData qmd, final Image picture, final String s, final Vector vector, final String s2, final int correct) throws Exception {
        this.textQuestion = new Vector(10, 10);
        this.textSolution1 = new Vector(10, 10);
        this.textSolution2 = new Vector(10, 10);
        this.answers = new Vector(10, 10);
        this.correct = 0;
        this.selected = -1;
        this.pointed = -1;
        this.qmd = qmd;
        this.picture = picture;
        this.correct = correct;
        final Point addLines;
        final Point point = addLines = QMLine.addLines(this.textQuestion, s, qmd.startLoc, qmd.maximumWidth, QuizMaster.fonts[0], QuizMaster.fms[0]);
        addLines.y += 5;
        final int n = (qmd.maximumWidth - qmd.numberOfColumns * 10 + 10) / qmd.numberOfColumns;
        if (n < 26) {
            throw new Exception("To little TextMaxWidth or to many columns.");
        }
        final Point[] array = new Point[qmd.numberOfColumns];
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Point(point.x + i * (10 + n), point.y);
        }
        int n2 = 0;
        for (int j = 0; j < vector.size(); ++j) {
            this.answers.addElement(new QMAnswer(this, vector.elementAt(j), array[n2], n));
            n2 = ++n2 % qmd.numberOfColumns;
        }
        for (int k = 0; k < array.length; ++k) {
            point.y = Math.max(point.y, array[k].y);
        }
        QMLine.addLines(this.textSolution1, QuizMaster.strings[0] + s2, point, qmd.maximumWidth, QuizMaster.fonts[2], QuizMaster.fms[2]);
        QMLine.addLines(this.textSolution2, QuizMaster.strings[1] + s2, point, qmd.maximumWidth, QuizMaster.fonts[2], QuizMaster.fms[2]);
    }
    
    public void reset() {
        this.selected = -1;
    }
    
    public void paint(final Graphics graphics) {
        if (this.picture != null) {
            graphics.drawImage(this.picture, this.qmd.startImage.x, this.qmd.startImage.y, this.qmd.qm);
        }
        for (int i = 0; i < this.textQuestion.size(); ++i) {
            ((QMLine)this.textQuestion.elementAt(i)).paint(graphics, QuizMaster.colors[1]);
        }
        for (int j = 0; j < this.answers.size(); ++j) {
            ((QMAnswer)this.answers.elementAt(j)).paint(graphics, (this.selected >= 0) ? ((this.correct == j) ? 2 : (this.selected == j)) : (this.pointed == j));
        }
        if (this.selected >= 0) {
            if (this.selected == this.correct) {
                for (int k = 0; k < this.textSolution1.size(); ++k) {
                    ((QMLine)this.textSolution1.elementAt(k)).paint(graphics, QuizMaster.colors[5]);
                }
            }
            else {
                for (int l = 0; l < this.textSolution2.size(); ++l) {
                    ((QMLine)this.textSolution2.elementAt(l)).paint(graphics, QuizMaster.colors[6]);
                }
            }
        }
    }
    
    public boolean mouseMoved(final Point point) {
        if (this.selected >= 0) {
            return false;
        }
        int pointed = -1;
        for (int i = 0; i < this.answers.size(); ++i) {
            if (((QMAnswer)this.answers.elementAt(i)).isPartOf(point)) {
                pointed = i;
            }
        }
        if (pointed != this.pointed) {
            this.pointed = pointed;
            return true;
        }
        return false;
    }
    
    public boolean mousePressed(final Point point) {
        if (this.selected >= 0) {
            return false;
        }
        for (int i = 0; i < this.answers.size(); ++i) {
            if (((QMAnswer)this.answers.elementAt(i)).isPartOf(point)) {
                this.selected = i;
                this.pointed = -1;
                return true;
            }
        }
        return false;
    }
    
    public int getScore() {
        return (this.selected == this.correct) ? 1 : 0;
    }
}
