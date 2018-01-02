import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.awt.CheckboxGroup;
import java.awt.Label;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class OptionsDlg extends Dialog
{
    BJOptions bjOptions;
    Checkbox HitSoft17Check;
    Checkbox DoubleOn10_or_11_onlyCheck;
    Checkbox DoubleAfterSplitCheck;
    Checkbox ResplitPairsCheck;
    Checkbox ResplitAcesCheck;
    Checkbox SurrenderAllowedCheck;
    Button OkButton;
    Button CancelButton;
    Button BasicStrategyButton;
    Label numberOfDecksLabel;
    LinePanel NumberOfDecksPanel;
    CheckboxGroup numberOfDecksGroup;
    Checkbox Check1Deck;
    Checkbox Check2Deck;
    Checkbox Check4Deck;
    Checkbox Check6Deck;
    Label deckDepletionLabel;
    LinePanel DeckDepletionPanel;
    CheckboxGroup deckDepletionGroup;
    Checkbox DeckDepletion1Check;
    Checkbox DeckDepletion2Check;
    Checkbox DeckDepletion3Check;
    Checkbox DeckDepletion5Check;
    Label limitsLabel;
    LinePanel LimitsPanel;
    CheckboxGroup limitsGroup;
    Checkbox Limit1Check;
    Checkbox Limit2Check;
    Checkbox Limit3Check;
    Checkbox Limit4Check;
    boolean fComponentsAdjusted;
    
    public OptionsDlg(final Frame frame, final boolean b, final BJOptions bjOptions) {
        super(frame, b);
        this.HitSoft17Check = new Checkbox();
        this.DoubleOn10_or_11_onlyCheck = new Checkbox();
        this.DoubleAfterSplitCheck = new Checkbox();
        this.ResplitPairsCheck = new Checkbox();
        this.ResplitAcesCheck = new Checkbox();
        this.SurrenderAllowedCheck = new Checkbox();
        this.OkButton = new Button();
        this.CancelButton = new Button();
        this.BasicStrategyButton = new Button();
        this.numberOfDecksLabel = new Label("Decks");
        this.NumberOfDecksPanel = new LinePanel();
        this.numberOfDecksGroup = new CheckboxGroup();
        this.Check1Deck = new Checkbox();
        this.Check2Deck = new Checkbox();
        this.Check4Deck = new Checkbox();
        this.Check6Deck = new Checkbox();
        this.deckDepletionLabel = new Label("Shuffle Point");
        this.DeckDepletionPanel = new LinePanel();
        this.deckDepletionGroup = new CheckboxGroup();
        this.DeckDepletion1Check = new Checkbox();
        this.DeckDepletion2Check = new Checkbox();
        this.DeckDepletion3Check = new Checkbox();
        this.DeckDepletion5Check = new Checkbox();
        this.limitsLabel = new Label("Limits");
        this.LimitsPanel = new LinePanel();
        this.limitsGroup = new CheckboxGroup();
        this.Limit1Check = new Checkbox();
        this.Limit2Check = new Checkbox();
        this.Limit3Check = new Checkbox();
        this.Limit4Check = new Checkbox();
        this.fComponentsAdjusted = false;
        this.bjOptions = bjOptions;
        this.setTitle("Options");
        this.setResizable(false);
        this.setModal(true);
        this.setLayout(null);
        this.setSize(479, 297);
        this.setVisible(false);
        this.HitSoft17Check.setLabel("Dealer Hits Soft 17");
        this.add(this.HitSoft17Check);
        this.HitSoft17Check.setBounds(24, 36, 192, 28);
        this.DoubleOn10_or_11_onlyCheck.setLabel("Double Only on 10 or 11");
        this.add(this.DoubleOn10_or_11_onlyCheck);
        this.DoubleOn10_or_11_onlyCheck.setBounds(24, 76, 192, 28);
        this.DoubleAfterSplitCheck.setLabel("Double After Split");
        this.add(this.DoubleAfterSplitCheck);
        this.DoubleAfterSplitCheck.setBounds(24, 116, 192, 28);
        this.ResplitPairsCheck.setLabel("Resplit Pairs");
        this.add(this.ResplitPairsCheck);
        this.ResplitPairsCheck.setBounds(24, 156, 192, 28);
        this.ResplitAcesCheck.setLabel("Resplit Aces");
        this.add(this.ResplitAcesCheck);
        this.ResplitAcesCheck.setBounds(24, 196, 192, 28);
        this.SurrenderAllowedCheck.setLabel("Late Surrender Allowed");
        this.add(this.SurrenderAllowedCheck);
        this.SurrenderAllowedCheck.setBounds(24, 236, 192, 28);
        this.OkButton.setLabel("OK");
        this.OkButton.setActionCommand("OK");
        this.add(this.OkButton);
        this.OkButton.setBounds(348, 252, 83, 24);
        this.CancelButton.setLabel("Cancel");
        this.CancelButton.setActionCommand("Cancel");
        this.add(this.CancelButton);
        this.CancelButton.setBounds(348, 216, 84, 24);
        this.BasicStrategyButton.setLabel("Strategy");
        this.BasicStrategyButton.setActionCommand("Strategy");
        this.add(this.BasicStrategyButton);
        this.BasicStrategyButton.setBounds(348, 180, 84, 24);
        this.add(this.NumberOfDecksPanel);
        this.NumberOfDecksPanel.setBounds(216, 32, 96, 110);
        this.add(this.numberOfDecksLabel);
        this.numberOfDecksLabel.setBounds(222, 17, 96, 15);
        this.Check1Deck.setCheckboxGroup(this.numberOfDecksGroup);
        this.Check1Deck.setLabel("1 Deck");
        this.NumberOfDecksPanel.add(this.Check1Deck);
        this.Check1Deck.setBounds(6, 3, 84, 26);
        this.Check2Deck.setCheckboxGroup(this.numberOfDecksGroup);
        this.Check2Deck.setLabel("2 Decks");
        this.NumberOfDecksPanel.add(this.Check2Deck);
        this.Check2Deck.setBounds(6, 29, 84, 26);
        this.Check4Deck.setCheckboxGroup(this.numberOfDecksGroup);
        this.Check4Deck.setLabel("4 Decks");
        this.NumberOfDecksPanel.add(this.Check4Deck);
        this.Check4Deck.setBounds(6, 55, 84, 26);
        this.Check6Deck.setCheckboxGroup(this.numberOfDecksGroup);
        this.Check6Deck.setLabel("6 Decks");
        this.NumberOfDecksPanel.add(this.Check6Deck);
        this.Check6Deck.setBounds(6, 81, 84, 26);
        this.add(this.DeckDepletionPanel);
        this.DeckDepletionPanel.setBounds(336, 32, 96, 110);
        this.add(this.deckDepletionLabel);
        this.deckDepletionLabel.setBounds(342, 17, 96, 15);
        this.DeckDepletion1Check.setCheckboxGroup(this.deckDepletionGroup);
        this.DeckDepletion1Check.setLabel("1/2 Shoe");
        this.DeckDepletionPanel.add(this.DeckDepletion1Check);
        this.DeckDepletion1Check.setBounds(6, 3, 84, 26);
        this.DeckDepletion2Check.setCheckboxGroup(this.deckDepletionGroup);
        this.DeckDepletion2Check.setLabel("2/3 Shoe");
        this.DeckDepletionPanel.add(this.DeckDepletion2Check);
        this.DeckDepletion2Check.setBounds(6, 29, 84, 26);
        this.DeckDepletion3Check.setCheckboxGroup(this.deckDepletionGroup);
        this.DeckDepletion3Check.setLabel("3/4 Shoe");
        this.DeckDepletionPanel.add(this.DeckDepletion3Check);
        this.DeckDepletion3Check.setBounds(6, 55, 84, 26);
        this.DeckDepletion5Check.setCheckboxGroup(this.deckDepletionGroup);
        this.DeckDepletion5Check.setLabel("5/6 Shoe");
        this.DeckDepletionPanel.add(this.DeckDepletion5Check);
        this.DeckDepletion5Check.setBounds(6, 81, 84, 26);
        this.add(this.LimitsPanel);
        this.LimitsPanel.setBounds(216, 176, 96, 110);
        this.add(this.limitsLabel);
        this.limitsLabel.setBounds(222, 161, 96, 15);
        this.Limit1Check.setCheckboxGroup(this.limitsGroup);
        this.Limit1Check.setLabel("2-200");
        this.LimitsPanel.add(this.Limit1Check);
        this.Limit1Check.setBounds(6, 3, 84, 23);
        this.Limit2Check.setCheckboxGroup(this.limitsGroup);
        this.Limit2Check.setLabel("5-1000");
        this.LimitsPanel.add(this.Limit2Check);
        this.Limit2Check.setBounds(6, 29, 84, 23);
        this.Limit3Check.setCheckboxGroup(this.limitsGroup);
        this.Limit3Check.setLabel("25-3000");
        this.LimitsPanel.add(this.Limit3Check);
        this.Limit3Check.setBounds(6, 55, 84, 23);
        this.Limit4Check.setCheckboxGroup(this.limitsGroup);
        this.Limit4Check.setLabel("100-5000");
        this.LimitsPanel.add(this.Limit4Check);
        this.Limit4Check.setBounds(6, 81, 84, 23);
        this.addWindowListener(new SymWindow());
        final SymAction symAction = new SymAction();
        this.OkButton.addActionListener(symAction);
        this.CancelButton.addActionListener(symAction);
        this.BasicStrategyButton.addActionListener(symAction);
    }
    
    public OptionsDlg(final Frame frame, final String title, final boolean b, final BJOptions bjOptions) {
        this(frame, b, bjOptions);
        this.setTitle(title);
    }
    
    public void addNotify() {
        final Dimension size = this.getSize();
        super.addNotify();
        if (this.fComponentsAdjusted) {
            return;
        }
        final Insets insets = this.getInsets();
        this.setSize(insets.left + insets.right + size.width, insets.top + insets.bottom + size.height);
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Point location = components[i].getLocation();
            location.translate(insets.left, insets.top);
            components[i].setLocation(location);
        }
        this.fComponentsAdjusted = true;
    }
    
    public void setVisible(final boolean visible) {
        if (visible) {
            final Rectangle bounds = this.getParent().getBounds();
            final Rectangle bounds2 = this.getBounds();
            this.setLocation(bounds.x + (bounds.width - bounds2.width) / 2, bounds.y + (bounds.height - bounds2.height) / 2);
            this.HitSoft17Check.setState(this.bjOptions.hitSoft17);
            this.DoubleOn10_or_11_onlyCheck.setState(this.bjOptions.doubleOn_10_or_11_only);
            this.DoubleAfterSplitCheck.setState(this.bjOptions.doubleAfterSplitAllowed);
            this.ResplitPairsCheck.setState(this.bjOptions.resplitPairsAllowed);
            this.ResplitAcesCheck.setState(this.bjOptions.resplitAcesAllowed);
            this.SurrenderAllowedCheck.setState(this.bjOptions.surrenderAllowed);
            switch (this.bjOptions.numberDecks) {
                case 1: {
                    this.Check1Deck.setState(true);
                    break;
                }
                case 2: {
                    this.Check2Deck.setState(true);
                    break;
                }
                case 4: {
                    this.Check4Deck.setState(true);
                    break;
                }
                case 6: {
                    this.Check6Deck.setState(true);
                    break;
                }
            }
            switch (this.bjOptions.penetration) {
                case 2: {
                    this.DeckDepletion1Check.setState(true);
                    break;
                }
                case 3: {
                    this.DeckDepletion2Check.setState(true);
                    break;
                }
                case 4: {
                    this.DeckDepletion3Check.setState(true);
                    break;
                }
                case 6: {
                    this.DeckDepletion5Check.setState(true);
                    break;
                }
            }
            switch (this.bjOptions.limitIndex) {
                case 0: {
                    this.Limit1Check.setState(true);
                    break;
                }
                case 1: {
                    this.Limit2Check.setState(true);
                    break;
                }
                case 2: {
                    this.Limit3Check.setState(true);
                    break;
                }
                case 3: {
                    this.Limit4Check.setState(true);
                    break;
                }
            }
        }
        super.setVisible(visible);
    }
    
    void OkButton_ActionPerformed(final ActionEvent actionEvent) {
        this.bjOptions.hitSoft17 = this.HitSoft17Check.getState();
        this.bjOptions.doubleOn_10_or_11_only = this.DoubleOn10_or_11_onlyCheck.getState();
        this.bjOptions.doubleAfterSplitAllowed = this.DoubleAfterSplitCheck.getState();
        this.bjOptions.resplitPairsAllowed = this.ResplitPairsCheck.getState();
        this.bjOptions.resplitAcesAllowed = this.ResplitAcesCheck.getState();
        this.bjOptions.surrenderAllowed = this.SurrenderAllowedCheck.getState();
        if (this.Check1Deck.getState()) {
            this.bjOptions.numberDecks = 1;
        }
        if (this.Check2Deck.getState()) {
            this.bjOptions.numberDecks = 2;
        }
        if (this.Check4Deck.getState()) {
            this.bjOptions.numberDecks = 4;
        }
        if (this.Check6Deck.getState()) {
            this.bjOptions.numberDecks = 6;
        }
        if (this.DeckDepletion1Check.getState()) {
            this.bjOptions.penetration = 2;
        }
        if (this.DeckDepletion2Check.getState()) {
            this.bjOptions.penetration = 3;
        }
        if (this.DeckDepletion3Check.getState()) {
            this.bjOptions.penetration = 4;
        }
        if (this.DeckDepletion5Check.getState()) {
            this.bjOptions.penetration = 6;
        }
        if (this.Limit1Check.getState()) {
            this.bjOptions.limitIndex = 0;
        }
        if (this.Limit2Check.getState()) {
            this.bjOptions.limitIndex = 1;
        }
        if (this.Limit3Check.getState()) {
            this.bjOptions.limitIndex = 2;
        }
        if (this.Limit4Check.getState()) {
            this.bjOptions.limitIndex = 3;
        }
        this.bjOptions.setChanged(true);
        this.dispose();
    }
    
    void CancelButton_ActionPerformed(final ActionEvent actionEvent) {
        this.dispose();
    }
    
    void BasicStrategyButton_ActionPerformed(final ActionEvent actionEvent) {
        new BasicStrategyChart((Frame)this.getParent(), "Strategy", this.bjOptions, this.DoubleOn10_or_11_onlyCheck.getState(), this.DoubleAfterSplitCheck.getState(), this.SurrenderAllowedCheck.getState(), !this.Check1Deck.getState()).setVisible(true);
    }
    
    void OptionsDlg_WindowClosing(final WindowEvent windowEvent) {
        this.dispose();
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == OptionsDlg.this.OkButton) {
                OptionsDlg.this.OkButton_ActionPerformed(actionEvent);
            }
            if (source == OptionsDlg.this.CancelButton) {
                OptionsDlg.this.CancelButton_ActionPerformed(actionEvent);
            }
            if (source == OptionsDlg.this.BasicStrategyButton) {
                OptionsDlg.this.BasicStrategyButton_ActionPerformed(actionEvent);
            }
        }
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == OptionsDlg.this) {
                OptionsDlg.this.OptionsDlg_WindowClosing(windowEvent);
            }
        }
    }
}
