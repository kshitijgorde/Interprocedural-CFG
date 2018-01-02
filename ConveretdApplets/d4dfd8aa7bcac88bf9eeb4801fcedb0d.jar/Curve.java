import java.awt.Color;
import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class Curve
{
    private boolean m_isMA;
    private String m_Name;
    private Date m_StartDate;
    private Date m_EndDate;
    private Date[] m_Date;
    private double[] m_Price;
    private Color m_Color;
    private int m_length;
    
    public Curve(final int length) {
        this.m_Name = "";
        this.m_StartDate = null;
        this.m_EndDate = null;
        this.m_isMA = false;
        this.m_Date = new Date[length];
        this.m_Price = new double[length];
        this.m_length = length;
        this.m_Color = null;
    }
    
    public Date getDate(final int n) {
        return this.m_Date[n];
    }
    
    public Color getColor() {
        return this.m_Color;
    }
    
    public void setColor(final Color color) {
        this.m_Color = color;
    }
    
    public void setDate(final int n, final Date date) {
        this.m_Date[n] = date;
    }
    
    public double getPrice(final int n) {
        return this.m_Price[n];
    }
    
    public void setPrice(final int n, final double n2) {
        this.m_Price[n] = n2;
    }
    
    public Date getEndDate() {
        return this.m_EndDate;
    }
    
    public void setEndDate(final Date endDate) {
        this.m_EndDate = endDate;
    }
    
    public String getName() {
        return this.m_Name;
    }
    
    public void setName(final String name) {
        this.m_Name = name;
    }
    
    public boolean getIsMA() {
        return this.m_isMA;
    }
    
    public void setIsMA(final boolean isMA) {
        this.m_isMA = isMA;
    }
    
    public Date getStartDate() {
        return this.m_StartDate;
    }
    
    public void setStartDate(final Date startDate) {
        this.m_StartDate = startDate;
    }
    
    public int getLength() {
        return this.m_length;
    }
}
