import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class QMAnswer
{
    private Vector textAnswer;
    private Point loc;
    private QMQuestion qmq;
    public static Image[] pics;
    
    public QMAnswer(final QMQuestion qmq, final String s, final Point point, final int n) {
        this.textAnswer = new Vector(10, 10);
        this.qmq = qmq;
        this.loc = new Point(point.x, point.y);
        point.y = Math.max(QMLine.addLines(this.textAnswer, s, new Point(point.x + 25, point.y + Math.max(0, 10 - QuizMaster.fonts[1].getSize() / 2)), n - 30, QuizMaster.fonts[1], QuizMaster.fms[1]).y + 5, this.loc.y + 25);
    }
    
    public void paint(final Graphics graphics, final int n) {
        graphics.drawImage(QMAnswer.pics[n], this.loc.x, this.loc.y, 20, 20, this.qmq.qmd.qm);
        for (int i = 0; i < this.textAnswer.size(); ++i) {
            ((QMLine)this.textAnswer.elementAt(i)).paint(graphics, QuizMaster.colors[2 + n]);
        }
    }
    
    public boolean isPartOf(final Point point) {
        boolean b = false;
        if (this.textAnswer.size() > 0) {
            b = (b || this.textAnswer.elementAt(0).isPartOf(point));
        }
        for (int i = 1; i < this.textAnswer.size(); ++i) {
            b = (b || ((QMLine)this.textAnswer.elementAt(i)).isPartOf(point, (QMLine)this.textAnswer.elementAt(i - 1)));
        }
        return b || (point.x >= this.loc.x && point.y >= this.loc.y && point.x < this.loc.x + 25 && point.y < this.loc.y + 20);
    }
    
    static {
        QMAnswer.pics = new Image[3];
    }
}
