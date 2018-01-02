import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

class b extends RotavapSim
{
    int ah;
    int aj;
    double ad;
    double ac;
    double ab;
    double aa;
    double Z;
    double Y;
    double X;
    double ai;
    double ag;
    double af;
    double R;
    double Q;
    double P;
    double T;
    double U;
    double V;
    double S;
    double W;
    float ae;
    
    b() {
        double aa = 0.0;
        try {
            if (RotavapSim.b.getSelectedIndex() == 0) {
                this.U = Double.valueOf(RotavapSim.d.getText());
                this.ad = Double.valueOf(RotavapSim.else.getText()) + 273.15;
                this.ac = Double.valueOf(RotavapSim.e.getText()) + 273.15;
                this.aa = Double.valueOf(RotavapSim.n.getText()) + 273.15;
                this.Z = Double.valueOf(RotavapSim.void.getText());
                this.X = Double.valueOf(RotavapSim.v.getText());
                this.V = this.Z + this.X;
                this.S = (this.V - this.Z - this.X) / this.V * 100.0;
                RotavapSim.N.a(this);
                double n;
                if (this.Z > this.X) {
                    n = this.Z;
                }
                else {
                    n = this.X;
                }
                this.ah = (int)n;
                if (this.ah - n < 0.0) {
                    ++this.ah;
                }
                this.R = Math.exp(10.223719028145297 * (1.0 - this.ac / this.ad));
                this.P = Math.exp(10.223719028145297 * (1.0 - this.aa / this.ad));
                if (this.R == 0.0) {
                    RotavapSim.new.setText("Error: solvent vap. press. is too small. T may be too low, or its bp. too high.");
                    RotavapSim.u.show(RotavapSim.t, "none");
                    a.a = null;
                }
                else {
                    this.aj = (int)(this.U * 10.0) + 1;
                    if (this.U * 10.0 + 1.0 - this.aj >= 0.5) {
                        ++this.aj;
                    }
                    this.ae = (this.aj - 1) / 10.0f;
                }
            }
            else if (RotavapSim.b.getSelectedIndex() == 1) {
                this.U = Double.valueOf(RotavapSim.m.getText());
                this.ad = Double.valueOf(RotavapSim.h.getText()) + 273.15;
                this.ac = Double.valueOf(RotavapSim.w.getText()) + 273.15;
                this.ab = Double.valueOf(RotavapSim.s.getText()) + 273.15;
                this.aa = Double.valueOf(RotavapSim.char.getText()) + 273.15;
                this.Z = Double.valueOf(RotavapSim.z.getText());
                this.Y = Double.valueOf(RotavapSim.c.getText());
                this.X = Double.valueOf(RotavapSim.goto.getText());
                this.V = this.Z + this.Y + this.X;
                this.S = (this.V - this.Z - this.Y - this.X) / this.V * 100.0;
                RotavapSim.N.a(this);
                double n2;
                if (this.Z > this.X && this.Z > this.Y) {
                    n2 = this.Z;
                }
                else if (this.Y > this.X) {
                    n2 = this.Y;
                }
                else {
                    n2 = this.X;
                }
                this.ah = (int)n2;
                if (this.ah - n2 < 0.0) {
                    ++this.ah;
                }
                this.R = Math.exp(10.223719028145297 * (1.0 - this.ac / this.ad));
                this.Q = Math.exp(10.223719028145297 * (1.0 - this.ab / this.ad));
                this.P = Math.exp(10.223719028145297 * (1.0 - this.aa / this.ad));
                if (this.R == 0.0 || this.Q == 0.0) {
                    RotavapSim.new.setText("Error: vap. press. of A or B is too small. T may be too low.");
                    RotavapSim.u.show(RotavapSim.t, "none");
                    a.a = null;
                }
                else {
                    this.aj = (int)(this.U * 10.0) + 1;
                    if (this.U * 10.0 + 1.0 - this.aj >= 0.5) {
                        ++this.aj;
                    }
                    this.ae = (this.aj - 1) / 10.0f;
                }
            }
            else {
                this.U = Double.valueOf(RotavapSim.byte.getText());
                if (this.U > 99.5) {
                    RotavapSim.byte.setText("99.0");
                    this.U = 99.0;
                }
                this.ad = Double.valueOf(RotavapSim.for.getText()) + 273.15;
                this.ac = Double.valueOf(RotavapSim.long.getText()) + 273.15;
                this.Z = Double.valueOf(RotavapSim.j.getText());
                this.X = Double.valueOf(RotavapSim.D.getText());
                this.W = Double.valueOf(RotavapSim.M.getText());
                this.V = this.Z + this.X;
                this.S = (this.V - this.Z - this.X) / this.V * 100.0;
                final double n3 = this.X / this.Z;
                this.aa = -10.0;
                RotavapSim.i.setText(" * ");
                double ac;
                double ac2;
                if (this.W > n3) {
                    System.out.println("Solute has higher BP than solvent");
                    ac = this.ac;
                    ac2 = this.ac * 1.5;
                    if (this.W > this.a(ac2, this.ac, this.X, this.Z, this.ad, this.U)) {
                        System.out.println("(Solute bp much greater than solvent)");
                        RotavapSim.i.setText("(Solute bp >> solvent)");
                        this.aa = ac2;
                        RotavapSim.g.setText(">>" + a(this.aa - 273.15, 5, -1).trim());
                    }
                }
                else {
                    System.out.println("Solute has lower BP than solvent");
                    ac2 = this.ac;
                    ac = this.ac / 1.5;
                    if (this.W < this.a(ac, this.ac, this.X, this.Z, this.ad, this.U)) {
                        System.out.println("(Solute bp much less than solvent)");
                        RotavapSim.i.setText("(Solute bp << solvent)");
                        this.aa = ac;
                        RotavapSim.g.setText("<<" + a(this.aa - 273.15, 5, -1).trim());
                    }
                }
                if (this.aa < 0.0) {
                    while (ac2 - ac > 0.1) {
                        aa = (ac + ac2) / 2.0;
                        if (this.W < this.a(aa, this.ac, this.X, this.Z, this.ad, this.U)) {
                            ac2 = aa;
                        }
                        else {
                            ac = aa;
                        }
                    }
                    this.aa = aa;
                    RotavapSim.g.setText("" + a(this.aa - 273.15, 5, -1).trim());
                }
                this.U = 100.0;
                RotavapSim.N.a(this);
                double n4;
                if (this.Z > this.X) {
                    n4 = this.Z;
                }
                else {
                    n4 = this.X;
                }
                this.ah = (int)n4;
                if (this.ah - n4 < 0.0) {
                    ++this.ah;
                }
                this.R = Math.exp(10.223719028145297 * (1.0 - this.ac / this.ad));
                this.P = Math.exp(10.223719028145297 * (1.0 - this.aa / this.ad));
                if (this.R == 0.0) {
                    RotavapSim.new.setText("Error: solvent vap. press. is too small. T may be too low, or its bp. too high.");
                    RotavapSim.u.show(RotavapSim.t, "none");
                    a.a = null;
                }
                else {
                    this.aj = (int)(this.U * 10.0) + 1;
                    if (this.U * 10.0 + 1.0 - this.aj >= 0.5) {
                        ++this.aj;
                    }
                    this.ae = (this.aj - 1) / 10.0f;
                }
            }
        }
        catch (NumberFormatException ex) {
            RotavapSim.new.setText("Error: at least one field contains an invalid number.");
            a.a = null;
            RotavapSim.u.show(RotavapSim.t, "none");
        }
    }
    
    void a(final Graphics graphics, final a a) {
        final Font font = new Font("Helvetica", 1, 12);
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        final Font font2 = new Font("Helvetica", 0, 10);
        final FontMetrics fontMetrics2 = this.getFontMetrics(font2);
        final int n = (int)(1.2f * fontMetrics.stringWidth("Amount"));
        final int n2 = (int)(1.2f * fontMetrics.getHeight() + fontMetrics2.getHeight() + 6.0f);
        final int[] array = new int[this.aj];
        final int[] array2 = new int[this.aj];
        final int[] array3 = new int[this.aj];
        final int[] array4 = new int[this.aj];
        final Dimension size = a.size();
        graphics.setColor(Color.black);
        graphics.setFont(font2);
        graphics.drawString("" + this.ah, n - 20, 10);
        graphics.drawString("0", n - 15, size.height - n2);
        graphics.drawLine(n - 5, 0, n, 0);
        graphics.drawLine(n - 3, 3 * (size.height - n2) / 4, n, 3 * (size.height - n2) / 4);
        graphics.drawLine(n - 5, (size.height - n2) / 2, n, (size.height - n2) / 2);
        graphics.drawLine(n - 3, (size.height - n2) / 4, n, (size.height - n2) / 4);
        graphics.drawLine(n - 5, size.height - n2, n, size.height - n2);
        final int n3 = fontMetrics2.stringWidth("20") / 2;
        final int n4 = (int)(1.2f * fontMetrics.getHeight());
        if (this.ae == 100.0) {
            graphics.drawString("100", size.width - 20, size.height - n4);
        }
        else {
            graphics.drawString("" + this.ae, size.width - 20, size.height - n4);
        }
        graphics.drawString("0", n, size.height - n4);
        graphics.drawLine(n, size.height - n2, n, size.height - n2 + 5);
        graphics.drawLine(size.width - 1, size.height - n2, size.width - 1, size.height - n2 + 5);
        if (this.ae > 15.0f) {
            graphics.drawLine(n + (int)(10.0 / this.ae * (size.width - n)), size.height - n2, n + (int)(10.0 / this.ae * (size.width - n)), size.height - n2 + 3);
        }
        if (this.ae > 25.0f) {
            graphics.drawLine(n + (int)(20.0 / this.ae * (size.width - n)), size.height - n2, n + (int)(20.0 / this.ae * (size.width - n)), size.height - n2 + 5);
            graphics.drawString("20", n - n3 + (int)(20.0 / this.ae * (size.width - n)), size.height - n4);
        }
        if (this.ae > 35.0f) {
            graphics.drawLine(n + (int)(30.0 / this.ae * (size.width - n)), size.height - n2, n + (int)(30.0 / this.ae * (size.width - n)), size.height - n2 + 3);
        }
        if (this.ae > 45.0f) {
            graphics.drawLine(n + (int)(40.0 / this.ae * (size.width - n)), size.height - n2, n + (int)(40.0 / this.ae * (size.width - n)), size.height - n2 + 5);
            graphics.drawString("40", n - n3 + (int)(40.0 / this.ae * (size.width - n)), size.height - n4);
        }
        if (this.ae > 55.0f) {
            graphics.drawLine(n + (int)(50.0 / this.ae * (size.width - n)), size.height - n2, n + (int)(50.0 / this.ae * (size.width - n)), size.height - n2 + 3);
        }
        if (this.ae > 66.0f) {
            graphics.drawLine(n + (int)(60.0 / this.ae * (size.width - n)), size.height - n2, n + (int)(60.0 / this.ae * (size.width - n)), size.height - n2 + 5);
            graphics.drawString("60", n - n3 + (int)(60.0 / this.ae * (size.width - n)), size.height - n4);
        }
        if (this.ae > 75.0f) {
            graphics.drawLine(n + (int)(70.0 / this.ae * (size.width - n)), size.height - n2, n + (int)(70.0 / this.ae * (size.width - n)), size.height - n2 + 3);
        }
        if (this.ae > 90.0f) {
            graphics.drawLine(n + (int)(80.0 / this.ae * (size.width - n)), size.height - n2, n + (int)(80.0 / this.ae * (size.width - n)), size.height - n2 + 5);
            graphics.drawString("80", n - n3 + (int)(80.0 / this.ae * (size.width - n)), size.height - n4);
        }
        if (this.ae > 95.0f) {
            graphics.drawLine(n + (int)(90.0 / this.ae * (size.width - n)), size.height - n2, n + (int)(90.0 / this.ae * (size.width - n)), size.height - n2 + 3);
        }
        graphics.setFont(font);
        graphics.drawString("Evaporation Extent (%)", size.width / 2, size.height - 2);
        graphics.drawString("Amount", 2, (size.height - n2) / 2);
        graphics.drawLine(n, 0, n, size.height - n2);
        graphics.drawLine(n, size.height - n2, size.width - 2, size.height - n2);
        if (RotavapSim.b.getSelectedIndex() == 0) {
            this.Z = Double.valueOf(RotavapSim.void.getText());
            this.X = Double.valueOf(RotavapSim.v.getText());
            array2[0] = (int)((this.ah - this.Z) * (size.height - n2) / this.ah);
            array4[0] = (int)((this.ah - this.X) * (size.height - n2) / this.ah);
            for (int i = 0; i < this.aj; ++i) {
                array[i] = i * (size.width - n) / this.aj + n;
            }
            final double n5 = (this.Z + this.X) / 1000.0;
            for (int j = 1; j < this.aj; ++j) {
                this.ai = this.Z / (this.Z + this.X);
                if (this.ai < 0.0) {
                    this.ai = 0.0;
                    this.Z = 0.0;
                }
                this.af = this.X / (this.Z + this.X);
                if (this.af < 0.0) {
                    this.af = 0.0;
                    this.X = 0.0;
                }
                this.T = this.ai * this.R + this.af * this.P;
                this.Z -= this.ai * this.R * n5 / this.T;
                array2[j] = (int)((this.ah - this.Z) * (size.height - n2) / this.ah);
                this.X -= this.af * this.P * n5 / this.T;
                array4[j] = (int)((this.ah - this.X) * (size.height - n2) / this.ah);
            }
        }
        else if (RotavapSim.b.getSelectedIndex() == 1) {
            this.Z = Double.valueOf(RotavapSim.z.getText());
            this.Y = Double.valueOf(RotavapSim.c.getText());
            this.X = Double.valueOf(RotavapSim.goto.getText());
            array2[0] = (int)((this.ah - this.Z) * (size.height - n2) / this.ah);
            array3[0] = (int)((this.ah - this.Y) * (size.height - n2) / this.ah);
            array4[0] = (int)((this.ah - this.X) * (size.height - n2) / this.ah);
            for (int k = 0; k < this.aj; ++k) {
                array[k] = k * (size.width - n) / this.aj + n;
            }
            final double n6 = (this.Z + this.Y + this.X) / 1000.0;
            for (int l = 1; l < this.aj; ++l) {
                this.ai = this.Z / (this.Z + this.Y + this.X);
                if (this.ai < 0.0) {
                    this.ai = 0.0;
                    this.Z = 0.0;
                }
                this.ag = this.Y / (this.Z + this.Y + this.X);
                if (this.ag < 0.0) {
                    this.ag = 0.0;
                    this.Y = 0.0;
                }
                this.af = this.X / (this.Z + this.Y + this.X);
                if (this.af < 0.0) {
                    this.af = 0.0;
                    this.X = 0.0;
                }
                this.T = this.ai * this.R + this.ag * this.Q + this.af * this.P;
                this.Z -= this.ai * this.R * n6 / this.T;
                array2[l] = (int)((this.ah - this.Z) * (size.height - n2) / this.ah);
                this.Y -= this.ag * this.Q * n6 / this.T;
                array3[l] = (int)((this.ah - this.Y) * (size.height - n2) / this.ah);
                this.X -= this.af * this.P * n6 / this.T;
                array4[l] = (int)((this.ah - this.X) * (size.height - n2) / this.ah);
            }
        }
        else {
            this.Z = Double.valueOf(RotavapSim.j.getText());
            this.X = Double.valueOf(RotavapSim.D.getText());
            array2[0] = (int)((this.ah - this.Z) * (size.height - n2) / this.ah);
            array4[0] = (int)((this.ah - this.X) * (size.height - n2) / this.ah);
            for (int n7 = 0; n7 < this.aj; ++n7) {
                array[n7] = n7 * (size.width - n) / this.aj + n;
            }
            final double n8 = (this.Z + this.X) / 1000.0;
            for (int n9 = 1; n9 < this.aj; ++n9) {
                this.ai = this.Z / (this.Z + this.X);
                if (this.ai < 0.0) {
                    this.ai = 0.0;
                    this.Z = 0.0;
                }
                this.af = this.X / (this.Z + this.X);
                if (this.af < 0.0) {
                    this.af = 0.0;
                    this.X = 0.0;
                }
                this.T = this.ai * this.R + this.af * this.P;
                this.Z -= this.ai * this.R * n8 / this.T;
                array2[n9] = (int)((this.ah - this.Z) * (size.height - n2) / this.ah);
                this.X -= this.af * this.P * n8 / this.T;
                array4[n9] = (int)((this.ah - this.X) * (size.height - n2) / this.ah);
            }
        }
        graphics.setColor(Color.red);
        for (int n10 = 0; n10 < this.aj - 1; ++n10) {
            graphics.drawLine(array[n10], array2[n10], array[n10 + 1], array2[n10 + 1]);
        }
        if (RotavapSim.b.getSelectedIndex() == 1) {
            graphics.setColor(new Color(0, 80, 0));
            for (int n11 = 0; n11 < this.aj - 1; ++n11) {
                graphics.drawLine(array[n11], array3[n11], array[n11 + 1], array3[n11 + 1]);
            }
        }
        graphics.setColor(Color.blue);
        for (int n12 = 0; n12 < this.aj - 1; ++n12) {
            graphics.drawLine(array[n12], array4[n12], array[n12 + 1], array4[n12 + 1]);
        }
        if (RotavapSim.b.getSelectedIndex() == 0) {
            final int n13 = (int)(1.2f * fontMetrics.stringWidth("Solvent"));
            final int n14 = (int)(1.2f * fontMetrics.stringWidth("Solute"));
            graphics.setColor(Color.black);
            graphics.drawString("Solvent", size.width - 25 - n13, (int)(1.2f * fontMetrics.getHeight()));
            graphics.drawString("Solute", size.width - 25 - n14, (int)(2.4f * fontMetrics.getHeight()));
            graphics.setColor(Color.red);
            graphics.fillRect(size.width - 25, (int)(1.2f * fontMetrics.getHeight() / 2.0f), 20, 3);
            graphics.setColor(Color.blue);
            graphics.fillRect(size.width - 25, (int)(3.6000001f * fontMetrics.getHeight() / 2.0f), 20, 3);
        }
        else if (RotavapSim.b.getSelectedIndex() == 1) {
            final int n15 = (int)(1.2f * fontMetrics.stringWidth("SolventA"));
            final int n16 = (int)(1.2f * fontMetrics.stringWidth("SolventB"));
            final int n17 = (int)(1.2f * fontMetrics.stringWidth("Solute"));
            graphics.setColor(Color.black);
            graphics.drawString("SolventA", size.width - 25 - n15, (int)(1.2f * fontMetrics.getHeight()));
            graphics.drawString("SolventB", size.width - 25 - n16, (int)(2.4f * fontMetrics.getHeight()));
            graphics.drawString("Solute", size.width - 25 - n17, (int)(3.6000001f * fontMetrics.getHeight()));
            graphics.setColor(Color.red);
            graphics.fillRect(size.width - 25, (int)(1.2f * fontMetrics.getHeight() / 2.0f), 20, 3);
            graphics.setColor(new Color(0, 80, 0));
            graphics.fillRect(size.width - 25, (int)(3.6000001f * fontMetrics.getHeight() / 2.0f), 20, 3);
            graphics.setColor(Color.blue);
            graphics.fillRect(size.width - 25, (int)(6.0f * fontMetrics.getHeight() / 2.0f), 20, 3);
        }
        else {
            final int n18 = (int)(1.2f * fontMetrics.stringWidth("Solvent"));
            final int n19 = (int)(1.2f * fontMetrics.stringWidth("Solute"));
            graphics.setColor(Color.black);
            graphics.drawString("Solvent", size.width - 25 - n18, (int)(1.2f * fontMetrics.getHeight()));
            graphics.drawString("Solute", size.width - 25 - n19, (int)(2.4f * fontMetrics.getHeight()));
            graphics.setColor(Color.red);
            graphics.fillRect(size.width - 25, (int)(1.2f * fontMetrics.getHeight() / 2.0f), 20, 3);
            graphics.setColor(Color.blue);
            graphics.fillRect(size.width - 25, (int)(3.6000001f * fontMetrics.getHeight() / 2.0f), 20, 3);
        }
        if (RotavapSim.b.getSelectedIndex() == 0) {
            if (this.Z == Double.valueOf(RotavapSim.void.getText())) {
                this.Z += 1.0E-8;
            }
            if (this.X == Double.valueOf(RotavapSim.v.getText())) {
                this.X += 1.0E-8;
            }
        }
        else if (RotavapSim.b.getSelectedIndex() == 1) {
            if (this.Z == Double.valueOf(RotavapSim.z.getText())) {
                this.Z += 1.0E-8;
            }
            if (this.Y == Double.valueOf(RotavapSim.c.getText())) {
                this.Y += 1.0E-8;
            }
            if (this.X == Double.valueOf(RotavapSim.goto.getText())) {
                this.X += 1.0E-8;
            }
        }
        else {
            if (this.Z == Double.valueOf(RotavapSim.j.getText())) {
                this.Z += 1.0E-8;
            }
            if (this.X == Double.valueOf(RotavapSim.D.getText())) {
                this.X += 1.0E-8;
            }
        }
        String value = "0";
        String substring = "0";
        String substring2 = "0";
        String substring3 = "0";
        final String value2 = String.valueOf(this.Z);
        if (RotavapSim.b.getSelectedIndex() == 1) {
            value = String.valueOf(this.Y);
        }
        final String value3 = String.valueOf(this.X);
        if (this.Z > 0.0) {
            substring = value2.substring(0, 5);
        }
        if (RotavapSim.b.getSelectedIndex() == 1 && this.Y > 0.0) {
            substring2 = value.substring(0, 5);
        }
        if (this.X > 0.0) {
            substring3 = value3.substring(0, 5);
        }
        if (RotavapSim.b.getSelectedIndex() == 1) {
            if (this.Z >= 5.0E-4) {
                RotavapSim.int.setText("" + substring);
            }
            else {
                RotavapSim.int.setText("0.000");
            }
            if (this.Y >= 5.0E-4) {
                RotavapSim.J.setText("" + substring2);
            }
            else {
                RotavapSim.J.setText("0.000");
            }
            if (this.X >= 5.0E-4) {
                RotavapSim.G.setText("" + substring3);
            }
            else {
                RotavapSim.G.setText("0.000");
            }
        }
        else if (RotavapSim.b.getSelectedIndex() == 0) {
            if (this.Z >= 5.0E-4) {
                RotavapSim.f.setText("" + substring);
            }
            else {
                RotavapSim.f.setText("0.000");
            }
            if (this.X >= 5.0E-4) {
                RotavapSim.A.setText("" + substring3);
            }
            else {
                RotavapSim.A.setText("0.000");
            }
        }
        else {
            this.U = Double.valueOf(RotavapSim.byte.getText());
            this.Z = Double.valueOf(RotavapSim.j.getText());
            this.X = Double.valueOf(RotavapSim.D.getText());
            this.W = Double.valueOf(RotavapSim.M.getText());
            RotavapSim.F.setText(a((100.0 - this.U) / 100.0 * (this.Z + this.X) * this.W / (this.W + 1.0), 6, 2));
            RotavapSim.l.setText(a((100.0 - this.U) / 100.0 * (this.Z + this.X) / (this.W + 1.0), 6, 2));
        }
    }
    
    double a(final double n, final double n2, double n3, double n4, final double n5, final double n6) {
        double n9;
        double n10;
        double n11;
        for (double n7 = (n4 + n3) / 1000.0, n8 = n4 + n3, exp = Math.exp(10.223719028145297 * (1.0 - n2 / n5)), exp2 = Math.exp(10.223719028145297 * (1.0 - n / n5)); (n4 + n3) / n8 * 100.0 > 100.0 - n6; n4 -= n9 * exp * n7 / n11, n3 -= n10 * exp2 * n7 / n11) {
            n9 = n4 / (n4 + n3);
            if (n9 < 0.0) {
                n9 = 0.0;
                n4 = 0.0;
            }
            n10 = n3 / (n4 + n3);
            if (n10 < 0.0) {
                n10 = 0.0;
                n3 = 0.0;
            }
            n11 = n9 * exp + n10 * exp2;
        }
        return n3 / n4;
    }
    
    static String a(final double n, final int n2, final int n3) {
        final String string = "          " + Double.toString(n) + "0000000000";
        final String substring = string.substring(0, string.indexOf(46) + n3 + 1);
        return substring.substring(substring.length() - n2);
    }
}
