import java.util.Vector;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class QMScore
{
    private String textScore;
    private Point loc;
    private QMData qmd;
    private Image picture;
    private int tagScore;
    private int tagTotal;
    private int tagPercentage;
    private int mark;
    
    public QMScore(final QMData qmd, final Image picture, final String textScore, final int mark) {
        this.tagScore = -1;
        this.tagTotal = -1;
        this.tagPercentage = -1;
        this.mark = 0;
        this.qmd = qmd;
        this.picture = picture;
        this.mark = mark;
        this.textScore = textScore;
        this.loc = qmd.startLoc;
    }
    
    public boolean isNotScore(final int n) {
        return n >= this.mark;
    }
    
    public void paint(final Graphics graphics, final int n, final int n2) {
        if (this.picture != null) {
            graphics.drawImage(this.picture, this.qmd.startImage.x, this.qmd.startImage.y, this.qmd.qm);
        }
        String s = new String(this.textScore);
        while (s.indexOf(60) >= 0) {
            try {
                switch (s.charAt(s.indexOf(60) + 1)) {
                    case 'S':
                    case 's': {
                        s = s.substring(0, s.indexOf(60)) + Integer.toString(n) + s.substring(s.indexOf(62, s.indexOf(60)) + 1);
                        continue;
                    }
                    case 'T':
                    case 't': {
                        s = s.substring(0, s.indexOf(60)) + Integer.toString(n2) + s.substring(s.indexOf(62, s.indexOf(60)) + 1);
                        continue;
                    }
                    case 'P':
                    case 'p': {
                        s = s.substring(0, s.indexOf(60)) + Integer.toString(100 * n / n2) + s.substring(s.indexOf(62, s.indexOf(60)) + 1);
                        continue;
                    }
                }
            }
            catch (Exception ex) {
                this.qmd.qm.errorString = "Unpermitted use of < between <S> and </S>";
                this.qmd.qm.repaint();
            }
        }
        final Vector<QMLine> vector = new Vector<QMLine>(10, 10);
        QMLine.addLines(vector, s, this.loc, this.qmd.maximumWidth, QuizMaster.fonts[3], QuizMaster.fms[3]);
        for (int i = 0; i < vector.size(); ++i) {
            vector.elementAt(i).paint(graphics, QuizMaster.colors[7]);
        }
    }
}
