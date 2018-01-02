import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class CalculateVector extends Vector
{
    private Vector dV;
    private Vector sV;
    
    public CalculateVector() {
        this.dV = new Vector();
        this.sV = new Vector();
    }
    
    public void addElement(final double a) {
        final Double d = new Double(a);
        this.dV.addElement(d);
    }
    
    public void addElement(final String s) {
        this.sV.addElement(s);
    }
    
    public void clear() {
        this.dV.setSize(0);
        this.sV.setSize(0);
    }
    
    public boolean contains(final double a) {
        final Double d = new Double(a);
        return this.dV.contains(d);
    }
    
    public boolean contains(final String s) {
        return this.sV.contains(s);
    }
    
    public double doubleAt(final int a) {
        final Double d = this.dV.elementAt(a);
        return d;
    }
    
    public String stringAt(final int a) {
        return this.sV.elementAt(a);
    }
    
    public int indexOf(final double a) {
        final Double d = new Double(a);
        return this.dV.indexOf(d);
    }
    
    public int indexOf(final String s) {
        return this.sV.indexOf(s);
    }
    
    public int lastIndexOf(final double a) {
        final Double d = new Double(a);
        return this.dV.lastIndexOf(d);
    }
    
    public int lastIndexOf(final String s) {
        return this.sV.lastIndexOf(s);
    }
    
    public void setElementAt(final double a, final int i) {
        final Double d = new Double(a);
        this.dV.setElementAt(d, i);
    }
    
    public void setElementAt(final String s, final int i) {
        this.sV.setElementAt(s, i);
    }
    
    public double lastDouble() {
        final Double d = this.dV.lastElement();
        return d;
    }
    
    public void calculate() {
        if ((this.sV.lastElement().equals("+") || this.sV.lastElement().equals("-")) && this.dV.size() >= 2) {
            final Double d = this.dV.elementAt(this.dV.size() - 2);
            final double a = d;
            final Double o = this.dV.elementAt(this.dV.size() - 1);
            final double b = o;
            final String s = this.sV.lastElement();
            if (this.stringAt(this.sV.size() - 2).equals("^")) {
                this.setElementAt(Math.pow(a, b), this.dV.size() - 2);
                this.setElementAt(s, this.sV.size() - 2);
                this.dV.setSize(this.dV.size() - 1);
                this.sV.setSize(this.dV.size());
                this.calculate();
            }
            else if (this.stringAt(this.sV.size() - 2).equals("*")) {
                this.setElementAt(a * b, this.dV.size() - 2);
                this.setElementAt(s, this.sV.size() - 2);
                this.dV.setSize(this.dV.size() - 1);
                this.sV.setSize(this.dV.size());
                this.calculate();
            }
            else if (this.stringAt(this.sV.size() - 2).equals("/")) {
                this.setElementAt(a / b, this.dV.size() - 2);
                this.setElementAt(s, this.sV.size() - 2);
                this.dV.setSize(this.dV.size() - 1);
                this.sV.setSize(this.dV.size());
                this.calculate();
            }
            else if (this.stringAt(this.sV.size() - 2).equals("+")) {
                this.setElementAt(a + b, this.dV.size() - 2);
                this.setElementAt(s, this.sV.size() - 2);
                this.dV.setSize(this.dV.size() - 1);
                this.sV.setSize(this.dV.size());
                this.calculate();
            }
            else if (this.stringAt(this.sV.size() - 2).equals("-")) {
                this.setElementAt(a - b, this.dV.size() - 2);
                this.setElementAt(s, this.sV.size() - 2);
                this.dV.setSize(this.dV.size() - 1);
                this.sV.setSize(this.dV.size());
                this.calculate();
            }
        }
        if ((this.sV.lastElement().equals("*") || this.sV.lastElement().equals("/")) && this.dV.size() >= 2) {
            final Double d = this.dV.elementAt(this.dV.size() - 2);
            final double a = d;
            final Double o = this.dV.elementAt(this.dV.size() - 1);
            final double b = o;
            final String s = this.sV.lastElement();
            if (this.stringAt(this.sV.size() - 2).equals("^")) {
                this.setElementAt(Math.pow(a, b), this.dV.size() - 2);
                this.setElementAt(s, this.sV.size() - 2);
                this.dV.setSize(this.dV.size() - 1);
                this.sV.setSize(this.dV.size());
                this.calculate();
                return;
            }
            if (this.stringAt(this.sV.size() - 2).equals("*")) {
                this.setElementAt(a * b, this.dV.size() - 2);
                this.setElementAt(s, this.sV.size() - 2);
                this.dV.setSize(this.dV.size() - 1);
                this.sV.setSize(this.dV.size());
                this.calculate();
                return;
            }
            if (this.stringAt(this.sV.size() - 2).equals("/")) {
                this.setElementAt(a / b, this.dV.size() - 2);
                this.setElementAt(s, this.sV.size() - 2);
                this.dV.setSize(this.dV.size() - 1);
                this.sV.setSize(this.dV.size());
                this.calculate();
            }
        }
    }
    
    public void calculateAbs() {
        if (this.dV.size() > 1 && this.sV.size() > 1) {
            for (int i = 0; i < this.sV.size() - 2; ++i) {
                if (this.stringAt(i).equals("*")) {
                    this.setElementAt(this.doubleAt(1) * this.doubleAt(2), 1);
                }
                else if (this.stringAt(i).equals("/")) {
                    this.setElementAt(this.doubleAt(1) / this.doubleAt(2), 1);
                }
            }
            this.dV.setSize(this.dV.size() - 1);
            this.sV.setSize(this.dV.size());
        }
        if (this.dV.size() > 1 && this.sV.size() > 1) {
            for (int i = 0; i < this.sV.size() - 2; ++i) {
                if (this.stringAt(i).equals("+")) {
                    this.setElementAt(this.doubleAt(1) + this.doubleAt(2), 1);
                }
                else if (this.stringAt(i).equals("-")) {
                    this.setElementAt(this.doubleAt(1) - this.doubleAt(2), 1);
                }
            }
            this.dV.setSize(this.dV.size() - 1);
            this.sV.setSize(this.dV.size());
        }
    }
    
    public void removeAll() {
        this.sV.removeAllElements();
        this.dV.removeAllElements();
    }
}
