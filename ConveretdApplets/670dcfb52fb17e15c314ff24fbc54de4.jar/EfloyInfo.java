import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Color;
import java.util.Enumeration;
import java.awt.Component;
import java.awt.Event;
import java.awt.CardLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

final class EfloyInfo extends Frame
{
    Button primary;
    Button secondary;
    Button environ;
    Button chromo;
    Button fitness;
    Button history;
    Button quitit;
    Button test;
    Panel okPanel;
    Panel TitlePanel;
    Panel TablePanel;
    Panel PrimaryTable;
    Panel SecondaryTable;
    Panel EnvironTable;
    Panel ChromoTable;
    Panel FitnessTable;
    Panel HistoryTable;
    Panel TestTable;
    Label TitleLabel;
    CardLayout cardLayout;
    EfloyScrollPanel sp;
    Efloy[] floys;
    
    public static String double2String(final double value, final int sigFigs) {
        final double factor = Math.pow(10.0, sigFigs);
        return new String(Math.round(value * factor) / factor + "");
    }
    
    public boolean mouseExit(final Event evt, final int x, final int y) {
        Efloys.appcontext.showStatus("");
        return true;
    }
    
    private void FillHistory(final Panel panel) {
        EfloyGeneration g = new EfloyGeneration();
        panel.add(new Label("#"));
        panel.add(new Label("Steps "));
        panel.add(new Label("Speed"));
        panel.add(new Label("Accel."));
        panel.add(new Label("DLL"));
        panel.add(new Label("DLS"));
        panel.add(new Label("DSL"));
        panel.add(new Label("CLL"));
        panel.add(new Label("CLS"));
        panel.add(new Label("CSL"));
        final int n = Efloys.HistoryData.size();
        final int m = n / 24 + 1;
        final Enumeration e = Efloys.HistoryData.elements();
        int k = 0;
        for (int i = 0; i < n; ++i) {
            try {
                g = e.nextElement();
                ++k;
                panel.add(new Label("" + g.num));
                panel.add(new Label("" + g.steps));
                panel.add(new Label("" + double2String(g.speedavg, 2)));
                panel.add(new Label("" + double2String(g.accavg, 2)));
                panel.add(new Label("" + double2String(g.dllavg, 2)));
                panel.add(new Label("" + double2String(g.dlsavg, 2)));
                panel.add(new Label("" + double2String(g.dslavg, 2)));
                panel.add(new Label("" + double2String(g.cllavg, 2)));
                panel.add(new Label("" + double2String(g.clsavg, 2)));
                panel.add(new Label("" + double2String(g.cslavg, 2)));
            }
            catch (Exception ex) {}
        }
        if (k < 18) {
            for (int i = k + 1; i < 17; ++i) {
                int j = 0;
                do {
                    panel.add(new Label(" "));
                } while (++j < 7);
            }
        }
    }
    
    void SortFloys() {
        for (int i = 0; i < this.floys.length - 1; ++i) {
            boolean swap = false;
            for (int j = this.floys.length - 2; j >= i; --j) {
                final int e = this.floys[j].fitness - this.floys[j + 1].fitness;
                if (e < 0) {
                    final Efloy etemp = this.floys[j];
                    this.floys[j] = this.floys[j + 1];
                    this.floys[j + 1] = etemp;
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }
    }
    
    public EfloyInfo(final Efloy[] f) {
        super("Floy Population Information");
        this.floys = f;
        final float fi = 0.0f;
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        this.primary = new Button("Primary");
        this.secondary = new Button("Secondary");
        this.environ = new Button("Environmental");
        this.chromo = new Button("Chromosomes");
        this.fitness = new Button("Fitness");
        this.history = new Button("History");
        this.quitit = new Button("Quit");
        this.test = new Button("Test");
        (this.okPanel = new Panel()).setLayout(new FlowLayout(1, 5, 5));
        this.okPanel.add(this.primary);
        this.okPanel.add(this.secondary);
        this.okPanel.add(this.environ);
        this.okPanel.add(this.fitness);
        this.okPanel.add(this.history);
        this.okPanel.add(this.test);
        this.okPanel.add(this.quitit);
        this.add("South", this.okPanel);
        final Font ButtonFont = new Font("TimesRoman", 0, 12);
        this.okPanel.setFont(ButtonFont);
        (this.TitlePanel = new Panel()).setLayout(new FlowLayout(1, 15, 15));
        this.TitleLabel = new Label("Primary Traits");
        this.TitlePanel.add(this.TitleLabel);
        this.add("North", this.TitlePanel);
        final Font TitleFont = new Font("TimesRoman", 1, 12);
        this.TitlePanel.setFont(TitleFont);
        this.TitlePanel.setForeground(Color.blue);
        this.TablePanel = new Panel();
        this.cardLayout = new CardLayout(0, 0);
        this.TablePanel.setLayout(this.cardLayout);
        (this.PrimaryTable = new Panel()).setLayout(new GridLayout(0, 10));
        this.FillPrimary(this.PrimaryTable);
        this.sp = new EfloyScrollPanel(this.PrimaryTable, "V");
        this.TablePanel.add("Primary", this.sp);
        (this.SecondaryTable = new Panel()).setLayout(new GridLayout(0, 7));
        this.FillSecondary(this.SecondaryTable);
        this.sp = new EfloyScrollPanel(this.SecondaryTable, "V");
        this.TablePanel.add("Secondary", this.sp);
        (this.EnvironTable = new Panel()).setLayout(new GridLayout(0, 2));
        this.FillEnviron(this.EnvironTable);
        this.sp = new EfloyScrollPanel(this.EnvironTable, "V");
        this.TablePanel.add("Environ", this.sp);
        (this.FitnessTable = new Panel()).setLayout(new GridLayout(0, 5));
        this.FillFitness(this.FitnessTable);
        this.sp = new EfloyScrollPanel(this.FitnessTable, "V");
        this.TablePanel.add("Fitness", this.sp);
        (this.HistoryTable = new Panel()).setLayout(new GridLayout(0, 10));
        this.FillHistory(this.HistoryTable);
        this.sp = new EfloyScrollPanel(this.HistoryTable, "V");
        this.TablePanel.add("History", this.sp);
        this.add("Center", this.TablePanel);
        this.pack();
    }
    
    private void FillEnviron(final Panel panel) {
        panel.add(new Label("Population Size"));
        panel.add(new Label("" + this.floys[0].PopulationSize));
        panel.add(new Label("Number of Neighbors"));
        panel.add(new Label("" + this.floys[0].NumberOfNeighbors));
        panel.add(new Label("Crossover Factor"));
        panel.add(new Label("" + this.floys[0].CrossoverFactor));
        panel.add(new Label("Mutation Factor"));
        panel.add(new Label("" + this.floys[0].MutationFactor));
        panel.add(new Label("Energy Weight Factor"));
        panel.add(new Label("" + this.floys[0].EnergyFactor));
        panel.add(new Label("Safety Weight Factor"));
        panel.add(new Label("" + this.floys[0].SafetyFactor));
        panel.add(new Label("Cooperation Weight Factor"));
        panel.add(new Label("" + this.floys[0].CooperationFactor));
        panel.add(new Label("Life Span"));
        panel.add(new Label("" + this.floys[0].LifeSpan));
        panel.add(new Label("Free Will Factor"));
        panel.add(new Label("" + this.floys[0].FreeWillFactor));
        panel.add(new Label("Max. Energy Dose"));
        panel.add(new Label("" + this.floys[0].MaxEnergyDose));
        panel.add(new Label("Max. Safety Dose"));
        panel.add(new Label("" + this.floys[0].MaxSafetyDose));
        panel.add(new Label("Max. Cooperation Dose"));
        panel.add(new Label("" + this.floys[0].MaxCooperationDose));
        panel.add(new Label("Survivers Percent"));
        panel.add(new Label("" + this.floys[0].SurviversFactor));
        panel.add(new Label("Movement Slowdown Property"));
        panel.add(new Label("" + this.floys[0].sleep));
    }
    
    public boolean mouseEnter(final Event evt, final int x, final int y) {
        if (evt.target == this.primary) {
            Efloys.appcontext.showStatus("Display main traits");
        }
        if (evt.target == this.secondary) {
            Efloys.appcontext.showStatus("Display secondary traits");
        }
        if (evt.target == this.environ) {
            Efloys.appcontext.showStatus("Display environment characteristics");
        }
        if (evt.target == this.chromo) {
            Efloys.appcontext.showStatus("Display chromosome strings");
        }
        if (evt.target == this.fitness) {
            Efloys.appcontext.showStatus("Display fitness and fitness components");
        }
        if (evt.target == this.history) {
            Efloys.appcontext.showStatus("Display generation history");
        }
        if (evt.target == this.test) {
            Efloys.appcontext.showStatus("Display test page");
        }
        if (evt.target == this.quitit) {
            Efloys.appcontext.showStatus("Exit info screen");
        }
        return true;
    }
    
    private void FillChromo(final Panel panel) {
        panel.add(new Label("ID"));
        panel.add(new Label("Variable"));
        panel.add(new Label("Fixed"));
        panel.add(new Label("Environmental"));
        panel.add(new Label("Color"));
        for (int i = 0; i < this.floys.length; ++i) {
            panel.add(new Label("" + this.floys[i].id));
            panel.add(new Label("" + this.floys[i].chrom));
            panel.add(new Label("" + this.floys[i].fixed));
            panel.add(new Label("" + this.floys[i].environ));
            panel.add(new Label("" + this.floys[i].GetColorName()));
        }
    }
    
    private void FillPrimary(final Panel panel) {
        this.SortFloys();
        panel.add(new Label("#"));
        panel.add(new Label("ID"));
        panel.add(new Label("Speed"));
        panel.add(new Label("Bounce"));
        panel.add(new Label("Acc."));
        panel.add(new Label("Center"));
        panel.add(new Label("Collision"));
        panel.add(new Label("Color"));
        panel.add(new Label("Fitness"));
        panel.add(new Label("Energy"));
        for (int i = 0; i < this.floys.length; ++i) {
            panel.add(new Label("" + i));
            panel.add(new Label("" + this.floys[i].id));
            panel.add(new Label("" + this.floys[i].MaxSpeed));
            panel.add(new Label("" + this.floys[i].BounceSpeed));
            panel.add(new Label("" + this.floys[i].ApproachAcceleration));
            panel.add(new Label("" + this.floys[i].CenterAcceleration));
            panel.add(new Label("" + this.floys[i].CollisionDistance));
            panel.add(new Label("" + this.floys[i].GetColorName()));
            panel.add(new Label("" + this.floys[i].fitness));
            panel.add(new Label("" + this.floys[i].energy));
        }
    }
    
    public boolean action(final Event e, final Object arg) {
        if (e.target == this.primary) {
            this.TitleLabel.setText("Primary Traits");
            this.cardLayout.show(this.TablePanel, "Primary");
        }
        if (e.target == this.secondary) {
            this.TitleLabel.setText("Secondary Traits");
            this.cardLayout.show(this.TablePanel, "Secondary");
        }
        if (e.target == this.environ) {
            this.TitleLabel.setText("Environmental Characteristics");
            this.cardLayout.show(this.TablePanel, "Environ");
        }
        if (e.target == this.chromo) {
            this.TitleLabel.setText("The Chromosomes");
            this.cardLayout.show(this.TablePanel, "Chromo");
        }
        if (e.target == this.fitness) {
            this.TitleLabel.setText("Fitness Table");
            this.cardLayout.show(this.TablePanel, "Fitness");
        }
        if (e.target == this.history) {
            this.TitleLabel.setText("History");
            this.cardLayout.show(this.TablePanel, "History");
        }
        if (e.target == this.test) {
            this.TitleLabel.setText("Test");
            this.cardLayout.show(this.TablePanel, "Test");
        }
        if (e.target == this.quitit) {
            this.hide();
            this.dispose();
            return true;
        }
        this.TitleLabel.resize(this.TitleLabel.preferredSize());
        return true;
    }
    
    private void FillSecondary(final Panel panel) {
        panel.add(new Label("ID"));
        panel.add(new Label("Distance"));
        panel.add(new Label("Distance"));
        panel.add(new Label("Distance"));
        panel.add(new Label("Collision"));
        panel.add(new Label("Collision"));
        panel.add(new Label("Collision"));
        panel.add(new Label(" "));
        panel.add(new Label("Brother"));
        panel.add(new Label("Stranger"));
        panel.add(new Label("Local"));
        panel.add(new Label("Brother"));
        panel.add(new Label("Stranger"));
        panel.add(new Label("Local"));
        for (int i = 0; i < this.floys.length; ++i) {
            panel.add(new Label("" + this.floys[i].id));
            panel.add(new Label("" + this.floys[i].DistBrotherFactor));
            panel.add(new Label("" + this.floys[i].DistStrangerFactor));
            panel.add(new Label("" + this.floys[i].DistLocalFactor));
            panel.add(new Label("" + this.floys[i].CollisionBrotherFactor));
            panel.add(new Label("" + this.floys[i].CollisionStrangerFactor));
            panel.add(new Label("" + this.floys[i].CollisionLocalFactor));
        }
    }
    
    private void FillFitness(final Panel panel) {
        panel.add(new Label("ID"));
        panel.add(new Label("Energy"));
        panel.add(new Label("Safety"));
        panel.add(new Label("Cooperation"));
        panel.add(new Label("Fitness"));
        for (int i = 0; i < this.floys.length; ++i) {
            panel.add(new Label("" + this.floys[i].id));
            panel.add(new Label("" + this.floys[i].energy));
            panel.add(new Label("" + this.floys[i].safety));
            panel.add(new Label("" + this.floys[i].cooperation));
            panel.add(new Label("" + this.floys[i].fitness));
        }
    }
    
    public boolean handleEvent(final Event ev) {
        if (ev.id == 201) {
            this.hide();
            this.dispose();
            return true;
        }
        return super.handleEvent(ev);
    }
}
