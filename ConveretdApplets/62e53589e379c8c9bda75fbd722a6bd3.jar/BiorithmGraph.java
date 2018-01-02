import java.util.Date;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class BiorithmGraph extends CartesianAxes
{
    static final int PHISICAL_PERIOD = 23;
    static final int EMOTIONAL_PERIOD = 28;
    static final int INTELLECTUAL_PERIOD = 33;
    protected long m_nPhase;
    protected XDate m_startDate;
    
    public BiorithmGraph() {
        super(new Point(0, 0), 0, 31, -100, 100);
        this.setLabelsAt(1, 0, 10, 0);
        this.addLegend("Physical", Color.green.darker());
        this.addLegend("Emotional", Color.red);
        this.addLegend("Intellectual", Color.blue);
        this.init();
    }
    
    public void init() {
        this.m_nPhase = -1L;
    }
    
    public void clear() {
        final Graphics graphics = this.getGraphics();
        final Rectangle clipRect = graphics.getClipRect();
        graphics.clearRect(clipRect.x, clipRect.y, clipRect.width, clipRect.height);
        this.init();
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.m_nPhase > -1L) {
            this.drawBiorithmLine(23, Color.green.darker(), graphics);
            this.drawBiorithmLine(28, Color.red, graphics);
            this.drawBiorithmLine(33, Color.blue, graphics);
        }
    }
    
    public void setDates(final XDate xDate, final XDate startDate) {
        this.m_nPhase = startDate.getPastDays() - xDate.getPastDays();
        this.m_startDate = startDate;
        this.repaint();
    }
    
    protected void drawXLabels(final Graphics graphics) {
        Date date = new Date(this.m_startDate.getYear(), this.m_startDate.getMonth(), this.m_startDate.getDate());
        if (super.m_nXLabelsAt != 0) {
            for (int i = super.m_nLowerX; i <= super.m_nUpperX; i += super.m_nXLabelsAt) {
                final int date2 = date.getDate();
                final Point translate = this.translate(new Point(i, super.m_UserOrg.y));
                graphics.drawString(Integer.toString(date2), translate.x, translate.y + 20);
                graphics.drawLine(translate.x, translate.y, translate.x, translate.y + 5);
                date = new Date(date.getYear(), date.getMonth(), date.getDate() + super.m_nXLabelsAt);
            }
        }
    }
    
    protected void drawBiorithmLine(final int n, final Color color, final Graphics graphics) {
        graphics.setColor(color);
        for (int n2 = 0; n2 <= super.m_nUpperX * super.m_dUnitX; ++n2) {
            final Point translate = this.translate(new Point((int)((n2 + super.m_Org.x) / super.m_dUnitX), (int)(100.0 * Math.sin(6.283185307179586 / (n * super.m_dUnitX) * (n2 + this.m_nPhase * super.m_dUnitX)))));
            graphics.drawLine(n2 + super.m_Org.x, translate.y, n2 + super.m_Org.x, translate.y);
        }
    }
}
