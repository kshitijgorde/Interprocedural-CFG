import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

final class EfloyPredefined extends Frame
{
    Button Button1;
    Button Button2;
    Button Button3;
    Button Button4;
    Button ButtonDefault;
    Button ButtonCancel;
    Label Label1;
    Label Label2;
    Label Label3;
    Label Label4;
    Label LabelDefault;
    Label LabelCancel;
    Label Help;
    Label[] Labels;
    Button[] Buttons;
    Label[] DummiesA;
    Label[] DummiesB;
    Label[] DummiesC;
    Panel MainPanel;
    Label TitleLabel;
    Font ButtonFont;
    
    public boolean mouseExit(final Event evt, final int x, final int y) {
        this.Help.setText("");
        return true;
    }
    
    void UpdateParams(final int num) {
        this.DefaultParams();
        if (num == 1) {
            Efloys.params[0].value = 8.0f;
            Efloys.params[1].value = 2.0f;
            Efloys.params[2].value = 0.8f;
            Efloys.params[4].value = 0.1f;
            Efloys.params[5].value = 3.0f;
            Efloys.params[6].value = 40.0f;
            Efloys.params[7].value = 0.0f;
            Efloys.params[8].value = 200.0f;
            Efloys.params[9].value = -1.0f;
            Efloys.params[10].value = 50.0f;
            Efloys.params[11].value = -50.0f;
            Efloys.InEvolution = false;
            Efloys.NewGeneration = false;
            Efloys.Evolution.setLabel("Start Evolution");
        }
        else if (num == 2) {
            Efloys.params[0].value = 1.0f;
            Efloys.params[1].value = 0.1f;
            Efloys.params[2].value = 0.05f;
            Efloys.params[4].value = 0.0f;
            Efloys.params[5].value = 1.0f;
            Efloys.params[6].value = 10.0f;
            Efloys.params[7].value = 0.0f;
            Efloys.params[8].value = 200.0f;
            Efloys.params[9].value = -1.0f;
            Efloys.params[10].value = 10.0f;
            Efloys.params[11].value = -20.0f;
            Efloys.InEvolution = false;
            Efloys.NewGeneration = false;
            Efloys.Evolution.setLabel("Start Evolution");
        }
        else if (num == 3) {
            Efloys.envpars[5].value = 1.0f;
            Efloys.envpars[6].value = 3.0f;
            Efloys.envpars[7].value = 0.0f;
            Efloys.envpars[8].value = 0.0f;
            Efloys.envpars[12].value = 25.0f;
            Efloys.envpars[13].value = 0.05f;
            Efloys.envpars[14].value = 25.0f;
            Efloys.InEvolution = true;
            Efloys.NewGeneration = true;
            Efloys.Evolution.setLabel("Stop Evolution");
        }
        else if (num == 4) {
            Efloys.envpars[5].value = 3.0f;
            Efloys.envpars[6].value = 1.0f;
            Efloys.envpars[7].value = 0.0f;
            Efloys.envpars[8].value = 0.0f;
            Efloys.envpars[12].value = 25.0f;
            Efloys.envpars[13].value = 0.05f;
            Efloys.envpars[14].value = 25.0f;
            Efloys.InEvolution = true;
            Efloys.NewGeneration = true;
            Efloys.Evolution.setLabel("Stop Evolution");
        }
        Efloys.NF = (int)Efloys.envpars[12].value;
        Efloys.SLEEP = (long)Efloys.envpars[3].value;
        Efloys.KICK = Efloys.envpars[13].value;
    }
    
    private void InitControls() {
        final Font LabelFont = new Font("TimesRoman", 1, 12);
        final Font ButtonFont = new Font("TimesRoman", 1, 12);
        this.Button1 = new Button("Nervous Population");
        this.Button2 = new Button("Phlegmatic Population");
        this.Button3 = new Button("Cautious Evolution");
        this.Button4 = new Button("Risk Taking Evolution");
        this.ButtonDefault = new Button("Select Default");
        this.ButtonCancel = new Button("Cancel");
        this.Button1.setFont(ButtonFont);
        this.Button2.setFont(ButtonFont);
        this.Button3.setFont(ButtonFont);
        this.Button4.setFont(ButtonFont);
        this.ButtonDefault.setFont(ButtonFont);
        this.ButtonCancel.setFont(ButtonFont);
        this.Labels = new Label[11];
        this.Buttons = new Button[11];
        this.DummiesA = new Label[11];
        this.DummiesB = new Label[11];
        this.DummiesC = new Label[11];
        int i = 0;
        do {
            this.Labels[i] = new Label("");
            this.Buttons[i] = new Button("");
            this.DummiesA[i] = new Label("");
            this.DummiesB[i] = new Label("");
            this.DummiesC[i] = new Label("");
            this.Labels[i].setFont(LabelFont);
            this.Buttons[i].setFont(ButtonFont);
        } while (++i < 11);
        this.Labels[0] = new Label("Configuration #1: Energetic, fast nervous population");
        this.Labels[1] = new Label("Configuration #2: Slow, lazy, phlegmatic population");
        this.Labels[2] = new Label("Configuration #3: Evolution that favors safety over energy");
        this.Labels[3] = new Label("Configuration #4: Evolution that favors energy over safety");
        this.Labels[4] = new Label("Reset to Default configuration");
        this.Labels[5] = new Label("Keep existing environment and population");
        i = 0;
        do {
            this.Labels[i].setFont(LabelFont);
            this.Buttons[i].setFont(ButtonFont);
        } while (++i < 6);
        this.Buttons[0] = this.Button1;
        this.Buttons[1] = this.Button2;
        this.Buttons[2] = this.Button3;
        this.Buttons[3] = this.Button4;
        this.Buttons[4] = this.ButtonDefault;
        this.Buttons[5] = this.ButtonCancel;
    }
    
    public EfloyPredefined() {
        super("Predefined Populations and Environments");
        this.ButtonFont = new Font("TimesRoman", 0, 12);
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        this.InitControls();
        (this.Help = new Label("These predefined configurations are arbitrary samples. Each can be defined in the Edit Properties screen", 1)).setForeground(Color.blue);
        final Panel StatusPanel = new Panel();
        StatusPanel.setLayout(new FlowLayout(1, 5, 5));
        StatusPanel.add(this.Help);
        final Font StatusFont = new Font("TimesRoman", 1, 12);
        this.Help.setFont(StatusFont);
        this.add("South", StatusPanel);
        final Panel TitlePanel = new Panel();
        TitlePanel.setLayout(new FlowLayout(1, 10, 10));
        TitlePanel.add(this.TitleLabel = new Label("  Predefined Populations and Environments  "));
        this.add("North", TitlePanel);
        final Font TitleFont = new Font("TimesRoman", 1, 14);
        TitlePanel.setFont(TitleFont);
        TitlePanel.setForeground(Color.blue);
        (this.MainPanel = new Panel()).setLayout(new GridLayout(10, 1));
        this.InitMainPanel(this.MainPanel, this.Labels, this.Buttons, this.DummiesA, this.DummiesB, this.DummiesC);
        this.add("Center", this.MainPanel);
        this.pack();
        this.hide();
    }
    
    public boolean mouseEnter(final Event evt, final int x, final int y) {
        if (evt.target == this.Button1) {
            this.Help.setText("Population #1 Main Properties:  Max Speed = 8, Bounce = 2, Acceleration = 0.8");
        }
        if (evt.target == this.Button2) {
            this.Help.setText("Population #2 Main Properties:  Max Speed = 1, Bounce = 0.1, Acceleration = 0.1");
        }
        if (evt.target == this.Button3) {
            this.Help.setText("Evolution #3 Main Properties:  Energy Weight Factor = 1, Safety Weight Factor = 3, Life Span = 25");
        }
        if (evt.target == this.Button4) {
            this.Help.setText("Evolution #4 Main Properties:  Energy Weight Factor = 3, Safety Weight Factor = 1, Life Span = 25");
        }
        if (evt.target == this.ButtonDefault) {
            this.Help.setText("All properties reset to defaults (Initial properties)");
        }
        if (evt.target == this.ButtonCancel) {
            this.Help.setText("Quit this screen without changing anything");
        }
        return true;
    }
    
    public boolean action(final Event e, final Object arg) {
        boolean ok = true;
        if (e.target == this.Button1) {
            this.UpdateParams(1);
        }
        if (e.target == this.Button2) {
            this.UpdateParams(2);
        }
        if (e.target == this.Button3) {
            this.UpdateParams(3);
        }
        if (e.target == this.Button4) {
            this.UpdateParams(4);
            Efloys.appcontext.showStatus("Button #4");
        }
        if (e.target == this.ButtonDefault) {
            this.DefaultParams();
            Efloys.appcontext.showStatus("Button #5");
        }
        if (e.target == this.ButtonCancel) {
            ok = false;
        }
        if (ok) {
            Efloys.ResetPopulation = true;
            Efloys.First = true;
        }
        this.hide();
        this.dispose();
        return true;
    }
    
    void DefaultParams() {
        Efloys.params[0].value = 5.0f;
        Efloys.params[1].value = 0.8f;
        Efloys.params[2].value = 0.3f;
        Efloys.params[4].value = 0.1f;
        Efloys.params[5].value = 1.0f;
        Efloys.params[6].value = 20.0f;
        Efloys.params[7].value = 0.0f;
        Efloys.params[8].value = 200.0f;
        Efloys.params[9].value = -1.0f;
        Efloys.params[10].value = 30.0f;
        Efloys.params[11].value = -40.0f;
        Efloys.fixpars[5].value = 2.0f;
        Efloys.fixpars[6].value = 0.1f;
        Efloys.fixpars[7].value = 1.0f;
        Efloys.fixpars[8].value = 10.0f;
        Efloys.fixpars[9].value = 10.0f;
        Efloys.fixpars[10].value = 10.0f;
        Efloys.envpars[5].value = 1.0f;
        Efloys.envpars[6].value = 1.0f;
        Efloys.envpars[7].value = 0.0f;
        Efloys.envpars[8].value = 0.0f;
        Efloys.envpars[12].value = 15.0f;
        Efloys.envpars[13].value = 0.05f;
        Efloys.envpars[14].value = 50.0f;
        Efloys.NF = (int)Efloys.envpars[12].value;
        Efloys.SLEEP = (long)Efloys.envpars[3].value;
        Efloys.KICK = Efloys.envpars[13].value;
    }
    
    private void InitMainPanel(final Panel panel, final Label[] labels, final Button[] buttons, final Label[] dummiesa, final Label[] dummiesb, final Label[] dummiesc) {
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = 2;
        int i = 0;
        do {
            constraints.gridwidth = 1;
            layout.setConstraints(dummiesa[i], constraints);
            panel.add(dummiesa[i]);
            constraints.gridwidth = 3;
            layout.setConstraints(this.Labels[i], constraints);
            panel.add(labels[i]);
            constraints.gridwidth = 1;
            layout.setConstraints(buttons[i], constraints);
            panel.add(this.Buttons[i]);
            constraints.gridwidth = 0;
            layout.setConstraints(dummiesb[i], constraints);
            panel.add(dummiesb[i]);
        } while (++i < 6);
        panel.resize(panel.preferredSize());
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
