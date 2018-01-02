import java.awt.Container;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Choice;
import java.awt.CardLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

final class EfloyCommand extends Frame
{
    Button Environ;
    Button Fixed;
    Button Variable;
    Button Secondary;
    Button Test;
    Button Reset;
    Button Update;
    Button Updateall;
    Button Quit;
    Panel ButtonPanel;
    Panel TitlePanel;
    Panel InputPanel;
    Panel EnvironPanel;
    Panel FixedPanel;
    Panel VariablePanel;
    Panel SecondaryPanel;
    Panel TestPanel;
    Label TitleLabel;
    CardLayout cardLayout;
    Label[] EnvLabels;
    Choice[] EnvChoices;
    Label[] EnvDummiesA;
    Label[] EnvDummiesB;
    Label[] EnvDummiesC;
    Label[] FixLabels;
    Choice[] FixChoices;
    Label[] FixDummiesA;
    Label[] FixDummiesB;
    Label[] FixDummiesC;
    Label[] VarLabels;
    Choice[] VarChoices;
    Label[] VarDummiesA;
    Label[] VarDummiesB;
    Label[] VarDummiesC;
    Label[] SecLabels;
    Choice[] SecChoices;
    Label[] SecDummiesA;
    Label[] SecDummiesB;
    Label[] SecDummiesC;
    Choice Floynum;
    Label FloynumLabel;
    Efloy[] floys;
    int SelectedFloy;
    Font ButtonFont;
    int CurNF;
    int CurPop;
    
    private String readString(final Choice c, final String d) {
        String n;
        try {
            n = c.getSelectedItem();
        }
        catch (Exception e) {
            n = d;
        }
        return n;
    }
    
    void FillFloyNumbers(final Choice choice) {
        for (int i = 0; i < this.floys.length; ++i) {
            choice.addItem("" + (i + 1));
        }
        choice.select("1");
    }
    
    private void FillColors(final Choice choice) {
        choice.addItem("BLACK");
        choice.addItem("BLUE");
        choice.addItem("CYAN");
        choice.addItem("DARKGRAY");
        choice.addItem("GRAY");
        choice.addItem("GREEN");
        choice.addItem("LIGHTGRAY");
        choice.addItem("MAGENTA");
        choice.addItem("ORANGE");
        choice.addItem("PINK");
        choice.addItem("RED");
        choice.addItem("WHITE");
        choice.addItem("YELLOW");
        choice.select(this.floys[this.floys.length - 1].GetColorName());
    }
    
    private int GetFloyNumber(final int fid) {
        int num = -1;
        for (int i = 0; i < Efloys.Efloys.length; ++i) {
            if (Efloys.Efloys[i].id == fid) {
                num = i;
            }
        }
        return num;
    }
    
    void UpdateOne(final int num) {
        this.UpdateEnviron(0);
        this.UpdateFloy(0);
        this.UpdateVariable(num);
        this.UpdateSecondary(num);
        this.UpdateFixed(num);
        this.UpdateFloy(num);
    }
    
    private void InitFixed() {
        this.FixLabels[0].setText("Type");
        this.FixLabels[1].setText("Color");
        this.FixLabels[2].setText("Number of Neighbors");
        this.FixLabels[3].setText("Mutation Factor");
        this.FixLabels[4].setText("Crossover Factor");
        this.FixLabels[5].setText("Energy Level");
        this.FixLabels[6].setText("Safety Level");
        this.FixLabels[7].setText("Cooperation Level");
        this.FixLabels[8].setText("Fitness");
        this.FillTypes(this.FixChoices[0]);
        this.FillColors(this.FixChoices[1]);
        this.InitChoice(this.FixChoices[2], Efloys.fixpars, 5);
        this.InitChoice(this.FixChoices[3], Efloys.fixpars, 6);
        this.InitChoice(this.FixChoices[4], Efloys.fixpars, 7);
        this.InitChoice(this.FixChoices[5], Efloys.fixpars, 8);
        this.InitChoice(this.FixChoices[6], Efloys.fixpars, 9);
        this.InitChoice(this.FixChoices[7], Efloys.fixpars, 10);
        this.InitChoice(this.FixChoices[8], Efloys.fixpars, 11);
        this.InitInputPanel(this.FixedPanel, this.FixLabels, this.FixChoices, this.FixDummiesA, this.FixDummiesB, this.FixDummiesC, 9);
    }
    
    private void FillTypes(final Choice choice) {
        choice.addItem("Local");
        choice.addItem("Stranger");
        choice.select(this.floys[this.floys.length - 1].GetTypeName());
    }
    
    private void UpdateSecondary(final int num) {
        Efloys.params[5].value = this.readFloat(this.SecChoices[0], this.floys[num].DistBrotherFactor);
        Efloys.params[6].value = this.readFloat(this.SecChoices[1], this.floys[num].DistStrangerFactor);
        Efloys.params[7].value = this.readFloat(this.SecChoices[2], this.floys[num].DistLocalFactor);
        Efloys.params[9].value = this.readFloat(this.SecChoices[3], this.floys[num].CollisionBrotherFactor);
        Efloys.params[10].value = this.readFloat(this.SecChoices[4], this.floys[num].CollisionStrangerFactor);
        Efloys.params[11].value = this.readFloat(this.SecChoices[5], this.floys[num].CollisionLocalFactor);
    }
    
    private void UpdateFixed(final int num) {
        Efloys.fixpars[3].value = this.FixChoices[0].getSelectedIndex();
        Efloys.fixpars[4].value = this.FixChoices[1].getSelectedIndex();
        Efloys.fixpars[5].value = this.readInt(this.FixChoices[2], this.floys[num].NumberOfNeighbors);
        Efloys.fixpars[6].value = this.readFloat(this.FixChoices[3], this.floys[num].MutationFactor);
        Efloys.fixpars[7].value = this.readFloat(this.FixChoices[4], this.floys[num].CrossoverFactor);
        Efloys.fixpars[8].value = this.readInt(this.FixChoices[5], this.floys[num].energy);
        Efloys.fixpars[9].value = this.readInt(this.FixChoices[6], this.floys[num].safety);
        Efloys.fixpars[10].value = this.readInt(this.FixChoices[7], this.floys[num].cooperation);
        Efloys.fixpars[11].value = this.readInt(this.FixChoices[8], this.floys[num].fitness);
    }
    
    private void InitControls() {
        this.EnvLabels = new Label[11];
        this.EnvChoices = new Choice[11];
        this.EnvDummiesA = new Label[11];
        this.EnvDummiesB = new Label[11];
        this.EnvDummiesC = new Label[11];
        this.FixLabels = new Label[11];
        this.FixChoices = new Choice[11];
        this.FixDummiesA = new Label[11];
        this.FixDummiesB = new Label[11];
        this.FixDummiesC = new Label[11];
        this.VarLabels = new Label[11];
        this.VarChoices = new Choice[11];
        this.VarDummiesA = new Label[11];
        this.VarDummiesB = new Label[11];
        this.VarDummiesC = new Label[11];
        this.SecLabels = new Label[11];
        this.SecChoices = new Choice[11];
        this.SecDummiesA = new Label[11];
        this.SecDummiesB = new Label[11];
        this.SecDummiesC = new Label[11];
        final Font font = new Font("TimesRoman", 1, 12);
        int i = 0;
        do {
            this.EnvLabels[i] = new Label("");
            this.EnvChoices[i] = new Choice();
            this.EnvDummiesA[i] = new Label("");
            this.EnvDummiesB[i] = new Label("");
            this.EnvDummiesC[i] = new Label("");
            this.EnvLabels[i].setFont(font);
            this.FixLabels[i] = new Label("");
            this.FixChoices[i] = new Choice();
            this.FixDummiesA[i] = new Label("");
            this.FixDummiesB[i] = new Label("");
            this.FixDummiesC[i] = new Label("");
            this.FixLabels[i].setFont(font);
            this.VarLabels[i] = new Label("");
            this.VarChoices[i] = new Choice();
            this.VarDummiesA[i] = new Label("");
            this.VarDummiesB[i] = new Label("");
            this.VarDummiesC[i] = new Label("");
            this.VarLabels[i].setFont(font);
            this.SecLabels[i] = new Label("");
            this.SecChoices[i] = new Choice();
            this.SecDummiesA[i] = new Label("");
            this.SecDummiesB[i] = new Label("");
            this.SecDummiesC[i] = new Label("");
            this.SecLabels[i].setFont(font);
        } while (++i < 11);
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
    
    public Color GetColorByString(final String c) {
        Color col;
        if (c.equals("BLACK")) {
            col = Color.black;
        }
        else if (c.equals("BLUE")) {
            col = Color.blue;
        }
        else if (c.equals("CYAN")) {
            col = Color.cyan;
        }
        else if (c.equals("DARKGRAY")) {
            col = Color.darkGray;
        }
        else if (c.equals("GRAY")) {
            col = Color.gray;
        }
        else if (c.equals("GREEN")) {
            col = Color.green;
        }
        else if (c.equals("LIGHTGRAY")) {
            col = Color.lightGray;
        }
        else if (c.equals("ORANGE")) {
            col = Color.orange;
        }
        else if (c.equals("PINK")) {
            col = Color.pink;
        }
        else if (c.equals("RED")) {
            col = Color.red;
        }
        else if (c.equals("WHITE")) {
            col = Color.white;
        }
        else if (c.equals("YELLOW")) {
            col = Color.yellow;
        }
        else {
            col = Color.green;
        }
        return col;
    }
    
    private float readFloat(final Choice c, final float d) {
        float n;
        try {
            n = Float.valueOf(c.getSelectedItem());
        }
        catch (NumberFormatException e) {
            n = d;
        }
        return n;
    }
    
    private void InitEnviron() {
        this.CurNF = Efloys.NF;
        this.CurPop = this.floys[0].PopulationSize;
        this.EnvLabels[0].setText("Population Size");
        this.EnvLabels[1].setText("Movement Slowdown Attribute");
        this.EnvLabels[2].setText("Energy Weight factor");
        this.EnvLabels[3].setText("Safety Weight Factor");
        this.EnvLabels[4].setText("Cooperation Weight Factor");
        this.EnvLabels[5].setText("Max. Energy Dose");
        this.EnvLabels[6].setText("Max. Safety Dose");
        this.EnvLabels[7].setText("Max. Cooperation Dose");
        this.EnvLabels[8].setText("Survivers Percent");
        this.EnvLabels[9].setText("Free Will Factor");
        this.EnvLabels[10].setText("Stranger Life Span");
        this.InitChoice(this.EnvChoices[0], Efloys.envpars, 12);
        this.InitChoice(this.EnvChoices[1], Efloys.envpars, 3);
        this.InitChoice(this.EnvChoices[2], Efloys.envpars, 5);
        this.InitChoice(this.EnvChoices[3], Efloys.envpars, 6);
        this.InitChoice(this.EnvChoices[4], Efloys.envpars, 7);
        this.InitChoice(this.EnvChoices[5], Efloys.envpars, 9);
        this.InitChoice(this.EnvChoices[6], Efloys.envpars, 10);
        this.InitChoice(this.EnvChoices[7], Efloys.envpars, 11);
        this.InitChoice(this.EnvChoices[8], Efloys.envpars, 8);
        this.InitChoice(this.EnvChoices[9], Efloys.envpars, 13);
        this.InitChoice(this.EnvChoices[10], Efloys.envpars, 14);
        this.InitInputPanel(this.EnvironPanel, this.EnvLabels, this.EnvChoices, this.EnvDummiesA, this.EnvDummiesB, this.EnvDummiesC, 11);
    }
    
    private void UpdateControls(final Choice floynum) {
        final String fnum = this.readString(floynum, "1");
        int fid;
        if (fnum.equals("Stranger")) {
            fid = 0;
        }
        else {
            fid = Integer.parseInt(fnum);
        }
        final int num = this.GetFloyNumber(fid);
        this.VarChoices[0].select(Float.toString(this.floys[num].MaxSpeed));
        this.VarChoices[1].select(Float.toString(this.floys[num].BounceSpeed));
        this.VarChoices[2].select(Float.toString(this.floys[num].ApproachAcceleration));
        this.VarChoices[3].select(Float.toString(this.floys[num].CenterAcceleration));
        this.VarChoices[4].select(Float.toString(this.floys[num].CollisionDistance));
        this.SecChoices[0].select(Float.toString(this.floys[num].DistBrotherFactor));
        this.SecChoices[1].select(Float.toString(this.floys[num].DistStrangerFactor));
        this.SecChoices[2].select(Float.toString(this.floys[num].DistLocalFactor));
        this.SecChoices[3].select(Float.toString(this.floys[num].CollisionBrotherFactor));
        this.SecChoices[4].select(Float.toString(this.floys[num].CollisionStrangerFactor));
        this.SecChoices[5].select(Float.toString(this.floys[num].CollisionLocalFactor));
        this.FixChoices[0].select(this.floys[num].GetTypeName());
        this.FixChoices[1].select(this.floys[num].GetColorName());
        this.FixChoices[2].select(Float.toString(this.floys[num].NumberOfNeighbors));
        this.FixChoices[3].select(Float.toString((int)this.floys[num].MutationFactor));
        this.FixChoices[4].select(Integer.toString((int)this.floys[num].CrossoverFactor));
        this.FixChoices[5].select(Integer.toString(this.floys[num].energy));
        this.FixChoices[6].select(Integer.toString(this.floys[num].safety));
        this.FixChoices[7].select(Integer.toString(this.floys[num].cooperation));
        this.FixChoices[8].select(Integer.toString(this.floys[num].fitness));
        this.EnvChoices[0].select(Integer.toString(this.floys[num].PopulationSize));
        this.EnvChoices[1].select(Integer.toString(this.floys[num].sleep));
        this.EnvChoices[2].select(Float.toString(this.floys[num].EnergyFactor));
        this.EnvChoices[3].select(Float.toString(this.floys[num].SafetyFactor));
        this.EnvChoices[4].select(Float.toString(this.floys[num].CooperationFactor));
        this.EnvChoices[5].select(Float.toString(this.floys[num].MaxEnergyDose));
        this.EnvChoices[6].select(Float.toString(this.floys[num].MaxSafetyDose));
        this.EnvChoices[7].select(Float.toString(this.floys[num].MaxCooperationDose));
        this.EnvChoices[8].select(Float.toString(this.floys[num].FreeWillFactor));
    }
    
    void UpdateAll() {
        for (int i = 0; i < this.floys.length; ++i) {
            this.UpdateVariable(i);
            this.UpdateSecondary(i);
            this.UpdateFixed(i);
            this.UpdateEnviron(i);
            this.UpdateFloy(i);
        }
    }
    
    public EfloyCommand(final Efloy[] f) {
        super("Control Center");
        this.floys = f;
        final float fi = 0.0f;
        this.ButtonFont = new Font("TimesRoman", 0, 12);
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        this.InitControls();
        this.Environ = new Button("Environment");
        this.Fixed = new Button("Fixed Traits");
        this.Variable = new Button("Primary Traits");
        this.Secondary = new Button("Secondary Traits");
        this.Update = new Button("Update");
        this.Updateall = new Button("Update All");
        this.Quit = new Button("Quit");
        this.FillFloyNumbers(this.Floynum = new Choice());
        (this.ButtonPanel = new Panel()).setLayout(new FlowLayout(1, 5, 5));
        this.ButtonPanel.add(this.Environ);
        this.ButtonPanel.add(this.Fixed);
        this.ButtonPanel.add(this.Variable);
        this.ButtonPanel.add(this.Secondary);
        this.ButtonPanel.add(this.Update);
        this.ButtonPanel.add(this.Updateall);
        this.ButtonPanel.add(this.Quit);
        this.ButtonPanel.setFont(this.ButtonFont);
        this.add("South", this.ButtonPanel);
        (this.TitlePanel = new Panel()).setLayout(new FlowLayout(1, 15, 15));
        this.TitleLabel = new Label("Environmental Properties");
        this.TitlePanel.add(this.TitleLabel);
        this.FloynumLabel = new Label("Floy Number:");
        this.TitlePanel.add(this.FloynumLabel);
        this.TitlePanel.add(this.Floynum);
        this.add("North", this.TitlePanel);
        final Font TitleFont = new Font("TimesRoman", 1, 14);
        this.TitlePanel.setFont(TitleFont);
        this.TitlePanel.setForeground(Color.blue);
        this.UpdateTitle("              Environmental Properties           ", true);
        this.InputPanel = new Panel();
        this.cardLayout = new CardLayout(0, 0);
        this.InputPanel.setLayout(this.cardLayout);
        this.EnvironPanel = new Panel();
        this.InitEnviron();
        this.InputPanel.add("Environ", this.EnvironPanel);
        this.FixedPanel = new Panel();
        this.InitFixed();
        this.InputPanel.add("Fixed", this.FixedPanel);
        this.VariablePanel = new Panel();
        this.InitVariable();
        this.InputPanel.add("Variable", this.VariablePanel);
        this.SecondaryPanel = new Panel();
        this.InitSecondary();
        this.InputPanel.add("Secondary", this.SecondaryPanel);
        (this.TestPanel = new Panel()).setLayout(new GridLayout(this.floys.length + 2, 9));
        this.InputPanel.add("Test", this.TestPanel);
        this.add("Center", this.InputPanel);
        this.pack();
        this.hide();
        this.UpdateTitle("Environmental Properties", false);
    }
    
    private Color readColor(final Choice c, final String d) {
        String n;
        try {
            n = c.getSelectedItem();
        }
        catch (Exception e) {
            n = d;
        }
        final Color col = this.GetColorByString(n);
        return col;
    }
    
    private void UpdateFloy(final int num) {
        final int id = Efloys.Efloys[num].id;
        final int e = Efloys.Efloys[num].energy;
        final int s = Efloys.Efloys[num].safety;
        final int c = Efloys.Efloys[num].cooperation;
        final String newpars = Efloys.EncodeChrom(Efloys.params);
        final String newfixs = Efloys.EncodeChrom(Efloys.fixpars);
        final String newenvs = Efloys.EncodeChrom(Efloys.envpars);
        this.floys[num] = new Efloy(Efloys.params, newpars, Efloys.fixpars, newfixs, Efloys.envpars, newenvs);
        Efloys.Efloys[num].id = id;
        Efloys.Efloys[num].energy = e;
        Efloys.Efloys[num].safety = s;
        Efloys.Efloys[num].cooperation = c;
        Efloys.Efloys[num].GetFitness();
    }
    
    private void UpdateEnviron(final int num) {
        Efloys.envpars[12].value = this.readInt(this.EnvChoices[0], this.floys[num].PopulationSize);
        Efloys.envpars[3].value = this.readInt(this.EnvChoices[1], this.floys[num].sleep);
        Efloys.envpars[5].value = this.readFloat(this.EnvChoices[2], this.floys[num].EnergyFactor);
        Efloys.envpars[6].value = this.readFloat(this.EnvChoices[3], this.floys[num].SafetyFactor);
        Efloys.envpars[7].value = this.readFloat(this.EnvChoices[4], this.floys[num].CooperationFactor);
        Efloys.envpars[9].value = this.readFloat(this.EnvChoices[5], this.floys[num].MaxEnergyDose);
        Efloys.envpars[10].value = this.readFloat(this.EnvChoices[6], this.floys[num].MaxSafetyDose);
        Efloys.envpars[11].value = this.readFloat(this.EnvChoices[7], this.floys[num].MaxCooperationDose);
        Efloys.envpars[8].value = this.readFloat(this.EnvChoices[8], this.floys[num].SurviversFactor);
        Efloys.envpars[13].value = this.readFloat(this.EnvChoices[9], this.floys[num].FreeWillFactor);
        Efloys.envpars[14].value = this.readFloat(this.EnvChoices[10], this.floys[num].LifeSpan);
        Efloys.NF = this.readInt(this.EnvChoices[0], this.floys[num].PopulationSize);
        Efloys.KICK = this.readFloat(this.EnvChoices[9], this.floys[num].FreeWillFactor);
        Efloys.SLEEP = this.readInt(this.EnvChoices[1], this.floys[num].sleep);
    }
    
    private void InitInputPanel(final Panel panel, final Label[] labels, final Choice[] choices, final Label[] dummiesa, final Label[] dummiesb, final Label[] dummiesc, final int num) {
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = 1;
        int i = 0;
        do {
            constraints.gridwidth = 1;
            layout.setConstraints(dummiesa[i], constraints);
            panel.add(dummiesa[i]);
            constraints.gridwidth = 2;
            layout.setConstraints(labels[i], constraints);
            panel.add(labels[i]);
            constraints.gridwidth = 1;
            if (i > num - 1) {
                layout.setConstraints(dummiesc[i], constraints);
                panel.add(dummiesc[i]);
            }
            else {
                layout.setConstraints(choices[i], constraints);
                panel.add(choices[i]);
            }
            constraints.gridwidth = 0;
            layout.setConstraints(dummiesb[i], constraints);
            panel.add(dummiesb[i]);
        } while (++i < 11);
        panel.resize(panel.preferredSize());
    }
    
    private void InitVariable() {
        this.VarLabels[0].setText("Maximum Speed");
        this.VarLabels[1].setText("Bounce-from-Wall Speed");
        this.VarLabels[2].setText("Acceleration");
        this.VarLabels[3].setText("Attraction to Center");
        this.VarLabels[4].setText("Collision Distance");
        this.InitChoice(this.VarChoices[0], Efloys.params, 0);
        this.InitChoice(this.VarChoices[1], Efloys.params, 1);
        this.InitChoice(this.VarChoices[2], Efloys.params, 2);
        this.InitChoice(this.VarChoices[3], Efloys.params, 4);
        this.InitChoice(this.VarChoices[4], Efloys.params, 8);
        this.InitInputPanel(this.VariablePanel, this.VarLabels, this.VarChoices, this.VarDummiesA, this.VarDummiesB, this.VarDummiesC, 5);
    }
    
    public boolean action(final Event e, final Object arg) {
        if (e.target == this.Environ) {
            this.UpdateTitle("Environmental Properties", false);
            this.cardLayout.show(this.InputPanel, "Environ");
        }
        if (e.target == this.Fixed) {
            this.UpdateTitle("Fixed Traits", true);
            this.cardLayout.show(this.InputPanel, "Fixed");
        }
        if (e.target == this.Variable) {
            this.UpdateTitle("Primary Traits", true);
            this.cardLayout.show(this.InputPanel, "Variable");
        }
        if (e.target == this.Secondary) {
            this.UpdateTitle("Secondary Traits", true);
            this.cardLayout.show(this.InputPanel, "Secondary");
        }
        if (e.target == this.Test) {
            this.UpdateTitle("Test Screen", false);
            this.cardLayout.show(this.InputPanel, "Test");
        }
        if (e.target == this.Update) {
            final int num = this.GetSelectedFloy(this.Floynum);
            this.UpdateOne(num);
            this.UpdateControls(this.Floynum);
            if (Efloys.envpars[12].value != this.CurPop) {
                Efloys.ResetPopulation = true;
                Efloys.First = true;
            }
        }
        if (e.target == this.Updateall) {
            this.UpdateAll();
            this.UpdateControls(this.Floynum);
            Efloys.ResetPopulation = true;
            Efloys.First = true;
        }
        if (e.target == this.Floynum) {
            this.UpdateControls(this.Floynum);
        }
        if (e.target == this.Quit) {
            this.hide();
            this.dispose();
            return true;
        }
        this.TitleLabel.resize(this.TitleLabel.preferredSize());
        return true;
    }
    
    private void InitChoice(final Choice choice, final EfloyParam[] param, final int Parnum) {
        for (int i = 0; i <= param[Parnum].nsteps; ++i) {
            final float fi = param[Parnum].min + i * param[Parnum].step;
            choice.addItem(Float.toString(fi));
        }
        choice.select(Float.toString(param[Parnum].value));
    }
    
    private void UpdateVariable(final int num) {
        Efloys.params[0].value = this.readFloat(this.VarChoices[0], this.floys[num].MaxSpeed);
        Efloys.params[1].value = this.readFloat(this.VarChoices[1], this.floys[num].BounceSpeed);
        Efloys.params[2].value = this.readFloat(this.VarChoices[2], this.floys[num].ApproachAcceleration);
        Efloys.params[4].value = this.readFloat(this.VarChoices[3], this.floys[num].CenterAcceleration);
        Efloys.params[8].value = this.readInt(this.VarChoices[4], this.floys[num].CollisionDistance);
    }
    
    void UpdateTitle(final String title, final boolean WithNumber) {
        this.TitleLabel.setText(title);
        if (WithNumber) {
            this.Floynum.show();
            this.FloynumLabel.show();
        }
        else {
            this.Floynum.hide();
            this.FloynumLabel.hide();
        }
        this.TitleLabel.resize(this.TitleLabel.preferredSize());
        this.FloynumLabel.resize(this.FloynumLabel.preferredSize());
    }
    
    private void InitSecondary() {
        this.SecLabels[0].setText("Distance Response (Local to Local)");
        this.SecLabels[1].setText("Distance Response (Local to Stranger)");
        this.SecLabels[2].setText("Distance Response (Stranger to Local)");
        this.SecLabels[3].setText("Collision Response (Local to Local)");
        this.SecLabels[4].setText("Collision Response (Local to Stranger)");
        this.SecLabels[5].setText("Collision Response (Stranger to Local)");
        this.InitChoice(this.SecChoices[0], Efloys.params, 5);
        this.InitChoice(this.SecChoices[1], Efloys.params, 6);
        this.InitChoice(this.SecChoices[2], Efloys.params, 7);
        this.InitChoice(this.SecChoices[3], Efloys.params, 9);
        this.InitChoice(this.SecChoices[4], Efloys.params, 10);
        this.InitChoice(this.SecChoices[5], Efloys.params, 11);
        this.InitInputPanel(this.SecondaryPanel, this.SecLabels, this.SecChoices, this.SecDummiesA, this.SecDummiesB, this.SecDummiesC, 6);
    }
    
    private int readInt(final Choice c, final int d) {
        int n;
        try {
            n = Integer.parseInt(c.getSelectedItem());
        }
        catch (NumberFormatException e) {
            n = d;
        }
        return n;
    }
    
    public boolean handleEvent(final Event ev) {
        if (ev.id == 201) {
            this.hide();
            this.dispose();
            return true;
        }
        return super.handleEvent(ev);
    }
    
    int GetSelectedFloy(final Choice floynum) {
        int fid = -1;
        final String fnum = this.readString(floynum, "1");
        if (fnum.equals("Stranger")) {
            fid = 0;
        }
        else {
            fid = Integer.parseInt(fnum);
        }
        int num;
        if (fid > -1) {
            num = this.GetFloyNumber(fid);
        }
        else {
            num = -1;
        }
        return num;
    }
}
