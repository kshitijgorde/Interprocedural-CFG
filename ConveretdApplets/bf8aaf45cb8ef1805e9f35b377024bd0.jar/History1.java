import netscape.javascript.JSObject;
import java.awt.Color;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class History1 extends Applet
{
    int m_nMax;
    int m_nIdx;
    int m_nCounts;
    String sUrl;
    Vector m_vHistory;
    Color m_cBackGroundColor;
    
    public void Add1(final String s) {
        this.m_nCounts = this.m_vHistory.size();
        if (this.m_nCounts + 1 > this.m_nMax && this.m_nIdx == this.m_nCounts - 1) {
            this.m_vHistory.removeElementAt(0);
            this.m_vHistory.addElement(s);
            this.m_nIdx = this.m_nCounts - 1;
            return;
        }
        if (this.m_nIdx == this.m_vHistory.size() - 1) {
            this.m_vHistory.addElement(s);
            this.m_nIdx = this.m_vHistory.size() - 1;
        }
        else {
            this.m_vHistory.insertElementAt(s, this.m_nIdx + 1);
            ++this.m_nIdx;
            for (int size = this.m_vHistory.size(), n = 0, i = this.m_nIdx + 1; i < size - n; ++n) {
                this.m_vHistory.removeElementAt(i);
            }
        }
        this.m_nCounts = this.m_vHistory.size();
    }
    
    public History1() {
        this.m_nIdx = -1;
        this.m_vHistory = new Vector(10, 10);
    }
    
    private void notifyLoaded() {
        final String parameter = this.getParameter("LOAD");
        if (parameter != null) {
            try {
                JSObject.getWindow((Applet)this).eval(parameter);
            }
            catch (Exception ex) {}
        }
    }
    
    public String Forward() {
        if (this.m_nIdx == -1) {
            return "";
        }
        this.m_nIdx = ((this.m_nIdx - 1 < 0) ? 0 : (--this.m_nIdx));
        return this.m_vHistory.elementAt(this.m_nIdx);
    }
    
    public String Backward() {
        if (this.m_nIdx == -1) {
            return "";
        }
        if (this.m_nIdx + 1 == this.m_nMax) {
            this.m_nIdx = this.m_nMax - 1;
        }
        else {
            this.m_nIdx = ((this.m_nIdx + 1 >= this.m_nCounts) ? (this.m_nCounts - 1) : (++this.m_nIdx));
        }
        return this.m_vHistory.elementAt(this.m_nIdx);
    }
    
    public String ShowCur() {
        if (this.m_nIdx == -1) {
            return "";
        }
        return this.m_vHistory.elementAt(this.m_nIdx);
    }
    
    public void init() {
        this.m_nMax = ((this.getParameter("MaxRecord") == null) ? 10 : Integer.parseInt(this.getParameter("MaxRecord")));
        this.m_vHistory.setSize(this.m_nMax);
        this.m_vHistory.removeAllElements();
        String parameter = this.getParameter("background");
        if (parameter == null) {
            parameter = "C0C0C0";
        }
        this.setBackground(this.m_cBackGroundColor = this.stringToColor(parameter));
        this.notifyLoaded();
    }
    
    private Color stringToColor(final String s) {
        return new Color(Integer.decode("0x" + s.substring(0, 2)), Integer.decode("0x" + s.substring(2, 4)), Integer.decode("0x" + s.substring(4, 6)));
    }
}
