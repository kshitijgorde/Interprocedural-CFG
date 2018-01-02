import java.awt.Event;
import java.awt.Label;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Component;
import java.util.Date;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.Color;
import astro.Comet;
import astro.TimeSpan;
import astro.ATime;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Scrollbar;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class OrbitViewer extends Applet
{
    private Scrollbar scrollHorz;
    private Scrollbar scrollVert;
    private Scrollbar scrollZoom;
    private OrbitCanvas orbitCanvas;
    private Button buttonDate;
    private Button buttonRevPlay;
    private Button buttonRevStep;
    private Button buttonStop;
    private Button buttonForStep;
    private Button buttonForPlay;
    private Choice choiceTimeStep;
    private Choice choiceCenterObject;
    private Choice choiceOrbitObject;
    private Checkbox checkPlanetName;
    private Checkbox checkObjectName;
    private Checkbox checkDistanceLabel;
    private Checkbox checkDateLabel;
    private DateDialog dateDialog;
    private OrbitPlayer orbitPlayer;
    Thread playerThread;
    private ATime atime;
    static final int timeStepCount = 8;
    static final String[] timeStepLabel;
    static final TimeSpan[] timeStepSpan;
    public TimeSpan timeStep;
    public int playDirection;
    static final int CenterObjectCount = 11;
    static final String[] CenterObjectLabel;
    public int CenterObjectSelected;
    static final int OrbitDisplayCount = 14;
    static final String[] OrbitDisplayLabel;
    public int OrbitCount;
    public boolean[] OrbitDisplay;
    public boolean[] OrbitDisplayDefault;
    private ATime minATime;
    private ATime maxATime;
    static final int initialScrollVert = 130;
    static final int initialScrollHorz = 255;
    static final int initialScrollZoom = 67;
    static final int fontSize = 16;
    
    public OrbitViewer() {
        this.dateDialog = null;
        this.playerThread = null;
        this.timeStep = OrbitViewer.timeStepSpan[1];
        this.playDirection = 1;
        this.CenterObjectSelected = 0;
        this.OrbitCount = 11;
        this.OrbitDisplay = new boolean[] { false, true, true, true, true, true, true, false, false, false, false };
        this.OrbitDisplayDefault = new boolean[] { false, true, true, true, true, true, true, false, false, false, false };
        this.minATime = new ATime(1600, 1, 1, 0, 0, 0.0, 0.0);
        this.maxATime = new ATime(2200, 1, 1, 0, 0, 0.0, 0.0);
    }
    
    public String getAppletInfo() {
        return "OrbitViewer v1.3 Copyright(C) 1996-2001 by O.Ajiki/R.Baalke";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "Name", "String", "Name of the object          ex. 1P/Halley" }, { "T", "double", "Time of perihelion passage  ex. 19860209.7695" }, { "e", "double", "Eccentricity                ex. 0.967267" }, { "q", "double", "Perihelion distance AU      ex. 0.587096" }, { "Peri", "double", "Argument of perihelion deg. ex. 111.8466" }, { "Node", "double", "Ascending node deg.         ex.  58.1440" }, { "Incl", "double", "Inclination deg.            ex. 162.2393" }, { "Eqnx", "double", "Year of equinox             ex. 1950.0" }, { "Epoch", "double", "Year/Month/Day of epoch     ex. 19991118.5" }, { "M", "double", "Mean anomaly deg.           ex. 356.648434" }, { "a", "double", "Semimajor axis AU           ex. 2.76631592" }, { "Date", "double", "Initial date                ex. 19860209.7695" } };
    }
    
    private ATime ymdStringToAtime(final String s) {
        final double doubleValue = Double.valueOf(s);
        final int n = (int)Math.floor(doubleValue / 10000.0);
        final double n2 = doubleValue - n * 10000.0;
        final int n3 = (int)Math.floor(n2 / 100.0);
        return new ATime(n, n3, n2 - n3 * 100.0, 0.0);
    }
    
    private double getRequiredParameter(final String s) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            throw new Error("Required parameter '" + s + "' not found.");
        }
        return Double.valueOf(parameter);
    }
    
    private Comet getObject(final int n) {
        String parameter = this.getParameter("Name" + n);
        if (parameter == null) {
            parameter = "Object";
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("e" + n)) == null) {
            throw new Error("required parameter 'e' not found.");
        }
        final double doubleValue = Double.valueOf(parameter2);
        final String parameter3;
        ATime ymdStringToAtime;
        double n2;
        if ((parameter3 = this.getParameter("T" + n)) != null) {
            ymdStringToAtime = this.ymdStringToAtime(parameter3);
            final String parameter4;
            if ((parameter4 = this.getParameter("q" + n)) != null) {
                n2 = Double.valueOf(parameter4);
            }
            else {
                final String parameter5;
                if ((parameter5 = this.getParameter("a" + n)) == null) {
                    throw new Error("Required parameter 'q' or 'a' not found.");
                }
                final double doubleValue2 = Double.valueOf(parameter5);
                if (Math.abs(doubleValue - 1.0) < 1.0E-15) {
                    throw new Error("Orbit is parabolic, but 'q' not found.");
                }
                n2 = doubleValue2 * (1.0 - doubleValue);
            }
        }
        else {
            final String parameter6;
            if ((parameter6 = this.getParameter("Epoch" + n)) == null) {
                throw new Error("Required parameter 'T' or 'Epoch' not found.");
            }
            final ATime ymdStringToAtime2 = this.ymdStringToAtime(parameter6);
            if (doubleValue > 0.95) {
                throw new Error("Orbit is nearly parabolic, but 'T' not found.");
            }
            final String parameter7;
            double doubleValue3;
            if ((parameter7 = this.getParameter("a" + n)) != null) {
                doubleValue3 = Double.valueOf(parameter7);
                n2 = doubleValue3 * (1.0 - doubleValue);
            }
            else {
                final String parameter8;
                if ((parameter8 = this.getParameter("q" + n)) == null) {
                    throw new Error("Required parameter 'q' or 'a' not found.");
                }
                n2 = Double.valueOf(parameter8);
                doubleValue3 = n2 / (1.0 - doubleValue);
            }
            if (n2 < 1.0E-15) {
                throw new Error("Too small perihelion distance.");
            }
            final double n3 = 0.01720209895 / (doubleValue3 * Math.sqrt(doubleValue3));
            final String parameter9;
            if ((parameter9 = this.getParameter("M" + n)) == null) {
                throw new Error("Required parameter 'M' not found.");
            }
            final double n4 = Double.valueOf(parameter9) * 3.141592653589793 / 180.0;
            if (n4 < 3.141592653589793) {
                ymdStringToAtime = new ATime(ymdStringToAtime2.getJd() - n4 / n3, 0.0);
            }
            else {
                ymdStringToAtime = new ATime(ymdStringToAtime2.getJd() + (6.283185307179586 - n4) / n3, 0.0);
            }
        }
        return new Comet(parameter, ymdStringToAtime.getJd(), doubleValue, n2, this.getRequiredParameter("Peri" + n) * 3.141592653589793 / 180.0, this.getRequiredParameter("Node" + n) * 3.141592653589793 / 180.0, this.getRequiredParameter("Incl" + n) * 3.141592653589793 / 180.0, this.getRequiredParameter("Eqnx" + n));
    }
    
    private ATime limitATime(final ATime aTime) {
        if (aTime.getJd() <= this.minATime.getJd()) {
            return new ATime(this.minATime);
        }
        if (this.maxATime.getJd() <= aTime.getJd()) {
            return new ATime(this.maxATime);
        }
        return aTime;
    }
    
    private void setNewDate() {
        this.atime = this.limitATime(this.atime);
        this.orbitCanvas.setDate(this.atime);
        this.orbitCanvas.repaint();
    }
    
    public ATime getAtime() {
        return this.atime;
    }
    
    public void setNewDate(final ATime aTime) {
        this.atime = this.limitATime(aTime);
        this.orbitCanvas.setDate(this.atime);
        this.orbitCanvas.repaint();
    }
    
    public void init() {
        this.setBackground(Color.white);
        final Panel panel = new Panel();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        panel.setLayout(layout);
        final Comet object = this.getObject(0);
        final Comet object2 = this.getObject(1);
        final Comet object3 = this.getObject(2);
        final String parameter;
        if ((parameter = this.getParameter("Date")) != null) {
            this.atime = this.ymdStringToAtime(parameter);
        }
        else {
            final Date date = new Date();
            this.atime = new ATime(date.getYear() + 1900, date.getMonth() + 1, date.getDate(), 0.0);
        }
        this.orbitCanvas = new OrbitCanvas(object, object2, object3, this.atime);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridwidth = -1;
        layout.setConstraints(this.orbitCanvas, gridBagConstraints);
        panel.add(this.orbitCanvas);
        this.scrollVert = new Scrollbar(1, 130, 12, 0, 192);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.scrollVert, gridBagConstraints);
        panel.add(this.scrollVert);
        this.orbitCanvas.setRotateVert(180 - this.scrollVert.getValue());
        this.scrollHorz = new Scrollbar(0, 255, 15, 0, 375);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(this.scrollHorz, gridBagConstraints);
        panel.add(this.scrollHorz);
        this.orbitCanvas.setRotateHorz(270 - this.scrollHorz.getValue());
        final Panel panel2 = new Panel();
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(panel2, gridBagConstraints);
        panel.add(panel2);
        final Panel panel3 = new Panel();
        final GridBagLayout layout2 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.fill = 1;
        panel3.setLayout(layout2);
        panel3.setBackground(Color.white);
        (this.buttonDate = new Button(" Date ")).setFont(new Font("Dialog", 0, 16));
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 2;
        gridBagConstraints2.insets = new Insets(0, 0, 0, 12);
        layout2.setConstraints(this.buttonDate, gridBagConstraints2);
        panel3.add(this.buttonDate);
        (this.buttonRevPlay = new Button("<<")).setFont(new Font("Dialog", 1, 14));
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.insets = new Insets(0, 0, 3, 0);
        layout2.setConstraints(this.buttonRevPlay, gridBagConstraints2);
        panel3.add(this.buttonRevPlay);
        (this.buttonRevStep = new Button("|<")).setFont(new Font("Dialog", 1, 14));
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.insets = new Insets(0, 0, 3, 0);
        layout2.setConstraints(this.buttonRevStep, gridBagConstraints2);
        panel3.add(this.buttonRevStep);
        (this.buttonStop = new Button("||")).setFont(new Font("Dialog", 1, 14));
        gridBagConstraints2.gridx = 3;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.insets = new Insets(0, 0, 3, 0);
        layout2.setConstraints(this.buttonStop, gridBagConstraints2);
        panel3.add(this.buttonStop);
        (this.buttonForStep = new Button(">|")).setFont(new Font("Dialog", 1, 14));
        gridBagConstraints2.gridx = 4;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.insets = new Insets(0, 0, 3, 0);
        layout2.setConstraints(this.buttonForStep, gridBagConstraints2);
        panel3.add(this.buttonForStep);
        (this.buttonForPlay = new Button(">>")).setFont(new Font("Dialog", 1, 14));
        gridBagConstraints2.gridx = 5;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.insets = new Insets(0, 0, 3, 0);
        layout2.setConstraints(this.buttonForPlay, gridBagConstraints2);
        panel3.add(this.buttonForPlay);
        (this.choiceTimeStep = new Choice()).setFont(new Font("Dialog", 0, 16));
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridwidth = 5;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
        layout2.setConstraints(this.choiceTimeStep, gridBagConstraints2);
        panel3.add(this.choiceTimeStep);
        for (int i = 0; i < 8; ++i) {
            this.choiceTimeStep.addItem(OrbitViewer.timeStepLabel[i]);
            this.choiceTimeStep.select(OrbitViewer.timeStepLabel[1]);
        }
        final Label label = new Label("Center:");
        label.setAlignment(0);
        label.setFont(new Font("Dialog", 0, 16));
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
        layout2.setConstraints(label, gridBagConstraints2);
        panel3.add(label);
        (this.choiceCenterObject = new Choice()).setFont(new Font("Dialog", 0, 16));
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridwidth = 5;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
        layout2.setConstraints(this.choiceCenterObject, gridBagConstraints2);
        panel3.add(this.choiceCenterObject);
        for (int j = 0; j < 11; ++j) {
            this.choiceCenterObject.addItem(OrbitViewer.CenterObjectLabel[j]);
        }
        this.orbitCanvas.SelectCenterObject(0);
        final Label label2 = new Label("Orbits:");
        label2.setAlignment(0);
        label2.setFont(new Font("Dialog", 0, 16));
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
        layout2.setConstraints(label2, gridBagConstraints2);
        panel3.add(label2);
        (this.choiceOrbitObject = new Choice()).setFont(new Font("Dialog", 0, 16));
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridwidth = 5;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
        layout2.setConstraints(this.choiceOrbitObject, gridBagConstraints2);
        panel3.add(this.choiceOrbitObject);
        for (int k = 0; k < 14; ++k) {
            this.choiceOrbitObject.addItem(OrbitViewer.OrbitDisplayLabel[k]);
        }
        for (int l = 0; l < this.OrbitCount; ++l) {
            this.OrbitDisplay[l] = this.OrbitDisplayDefault[l];
        }
        this.orbitCanvas.SelectOrbits(this.OrbitDisplay, this.OrbitCount);
        (this.checkDateLabel = new Checkbox("Date Label")).setState(true);
        this.checkDateLabel.setFont(new Font("Dialog", 0, 16));
        gridBagConstraints2.gridx = 6;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.insets = new Insets(0, 12, 0, 0);
        layout2.setConstraints(this.checkDateLabel, gridBagConstraints2);
        panel3.add(this.checkDateLabel);
        this.orbitCanvas.switchPlanetName(this.checkDateLabel.getState());
        (this.checkPlanetName = new Checkbox("Planet Labels")).setState(true);
        this.checkPlanetName.setFont(new Font("Dialog", 0, 16));
        gridBagConstraints2.gridx = 7;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.insets = new Insets(0, 12, 0, 0);
        layout2.setConstraints(this.checkPlanetName, gridBagConstraints2);
        panel3.add(this.checkPlanetName);
        this.orbitCanvas.switchPlanetName(this.checkPlanetName.getState());
        (this.checkDistanceLabel = new Checkbox("Distance")).setState(true);
        this.checkDistanceLabel.setFont(new Font("Dialog", 0, 16));
        gridBagConstraints2.gridx = 6;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.insets = new Insets(0, 12, 0, 0);
        layout2.setConstraints(this.checkDistanceLabel, gridBagConstraints2);
        panel3.add(this.checkDistanceLabel);
        this.orbitCanvas.switchPlanetName(this.checkDistanceLabel.getState());
        (this.checkObjectName = new Checkbox("Object Label")).setState(true);
        this.checkObjectName.setFont(new Font("Dialog", 0, 16));
        gridBagConstraints2.gridx = 7;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.insets = new Insets(0, 12, 0, 0);
        layout2.setConstraints(this.checkObjectName, gridBagConstraints2);
        panel3.add(this.checkObjectName);
        this.orbitCanvas.switchObjectName(this.checkObjectName.getState());
        final Label label3 = new Label("Zoom:");
        label3.setAlignment(0);
        label3.setFont(new Font("Dialog", 0, 16));
        gridBagConstraints2.gridx = 6;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.insets = new Insets(10, 12, 0, 0);
        layout2.setConstraints(label3, gridBagConstraints2);
        panel3.add(label3);
        this.scrollZoom = new Scrollbar(0, 67, 15, 5, 450);
        gridBagConstraints2.gridx = 6;
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.insets = new Insets(0, 12, 6, 2);
        layout2.setConstraints(this.scrollZoom, gridBagConstraints2);
        panel3.add(this.scrollZoom);
        this.orbitCanvas.setZoom(this.scrollZoom.getValue());
        final GridBagLayout layout3 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        this.setLayout(layout3);
        gridBagConstraints3.fill = 1;
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        gridBagConstraints3.gridwidth = 0;
        layout3.setConstraints(panel, gridBagConstraints3);
        this.add(panel);
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 0.0;
        gridBagConstraints3.gridwidth = 0;
        gridBagConstraints3.gridheight = 0;
        gridBagConstraints3.insets = new Insets(6, 0, 0, 0);
        layout3.setConstraints(panel3, gridBagConstraints3);
        this.add(panel3);
        this.orbitPlayer = new OrbitPlayer(this);
        this.playerThread = null;
    }
    
    public void start() {
    }
    
    public void stop() {
        if (this.dateDialog != null) {
            this.dateDialog.dispose();
            this.endDateDialog(null);
        }
        if (this.playerThread != null) {
            this.playerThread.stop();
            this.playerThread = null;
            this.buttonDate.enable();
        }
    }
    
    public void destroy() {
        this.removeAll();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 601:
            case 602:
            case 603:
            case 604:
            case 605: {
                if (event.target == this.scrollHorz) {
                    this.orbitCanvas.setRotateHorz(270 - this.scrollHorz.getValue());
                }
                else if (event.target == this.scrollVert) {
                    this.orbitCanvas.setRotateVert(180 - this.scrollVert.getValue());
                }
                else {
                    if (event.target != this.scrollZoom) {
                        return false;
                    }
                    this.orbitCanvas.setZoom(this.scrollZoom.getValue());
                }
                this.orbitCanvas.repaint();
                return true;
            }
            case 1001: {
                if (event.target == this.buttonDate) {
                    this.dateDialog = new DateDialog(this, this.atime);
                    this.buttonDate.disable();
                    return true;
                }
                if (event.target == this.buttonForPlay) {
                    if (this.playerThread != null && this.playDirection != 1) {
                        this.playerThread.stop();
                        this.playerThread = null;
                    }
                    if (this.playerThread == null) {
                        this.buttonDate.disable();
                        this.playDirection = 1;
                        (this.playerThread = new Thread(this.orbitPlayer)).setPriority(1);
                        this.playerThread.start();
                    }
                }
                else if (event.target == this.buttonRevPlay) {
                    if (this.playerThread != null && this.playDirection != -1) {
                        this.playerThread.stop();
                        this.playerThread = null;
                    }
                    if (this.playerThread == null) {
                        this.buttonDate.disable();
                        this.playDirection = -1;
                        (this.playerThread = new Thread(this.orbitPlayer)).setPriority(1);
                        this.playerThread.start();
                    }
                }
                else if (event.target == this.buttonStop) {
                    if (this.playerThread != null) {
                        this.playerThread.stop();
                        this.playerThread = null;
                        this.buttonDate.enable();
                    }
                }
                else {
                    if (event.target == this.buttonForStep) {
                        this.atime.changeDate(this.timeStep, 1);
                        this.setNewDate();
                        return true;
                    }
                    if (event.target == this.buttonRevStep) {
                        this.atime.changeDate(this.timeStep, -1);
                        this.setNewDate();
                        return true;
                    }
                    if (event.target == this.checkPlanetName) {
                        this.orbitCanvas.switchPlanetName(this.checkPlanetName.getState());
                        this.orbitCanvas.repaint();
                        return true;
                    }
                    if (event.target == this.checkObjectName) {
                        this.orbitCanvas.switchObjectName(this.checkObjectName.getState());
                        this.orbitCanvas.repaint();
                        return true;
                    }
                    if (event.target == this.checkDistanceLabel) {
                        this.orbitCanvas.switchDistanceLabel(this.checkDistanceLabel.getState());
                        this.orbitCanvas.repaint();
                        return true;
                    }
                    if (event.target == this.checkDateLabel) {
                        this.orbitCanvas.switchDateLabel(this.checkDateLabel.getState());
                        this.orbitCanvas.repaint();
                        return true;
                    }
                    if (event.target == this.choiceTimeStep) {
                        for (int i = 0; i < 8; ++i) {
                            if (event.arg == OrbitViewer.timeStepLabel[i]) {
                                this.timeStep = OrbitViewer.timeStepSpan[i];
                                break;
                            }
                        }
                    }
                    else if (event.target == this.choiceCenterObject) {
                        for (int j = 0; j < 11; ++j) {
                            if (event.arg == OrbitViewer.CenterObjectLabel[j]) {
                                this.CenterObjectSelected = j;
                                this.orbitCanvas.SelectCenterObject(j);
                                this.orbitCanvas.repaint();
                                break;
                            }
                        }
                    }
                    else if (event.target == this.choiceOrbitObject) {
                        for (int k = 0; k < 14; ++k) {
                            if (event.arg == OrbitViewer.OrbitDisplayLabel[k]) {
                                if (k == 1) {
                                    for (int l = 0; l < this.OrbitCount; ++l) {
                                        this.OrbitDisplay[l] = true;
                                    }
                                }
                                else if (k == 2) {
                                    for (int n = 0; n < this.OrbitCount; ++n) {
                                        this.OrbitDisplay[n] = false;
                                    }
                                }
                                else if (k == 0) {
                                    for (int n2 = 0; n2 < this.OrbitCount; ++n2) {
                                        this.OrbitDisplay[n2] = this.OrbitDisplayDefault[n2];
                                    }
                                }
                                else if (k > 3) {
                                    if (this.OrbitDisplay[k - 3]) {
                                        this.OrbitDisplay[k - 3] = false;
                                    }
                                    else {
                                        this.OrbitDisplay[k - 3] = true;
                                    }
                                }
                                event.arg = OrbitViewer.OrbitDisplayLabel[0];
                                this.orbitCanvas.SelectOrbits(this.OrbitDisplay, this.OrbitCount);
                                this.orbitCanvas.repaint();
                                break;
                            }
                        }
                    }
                }
                return false;
            }
            default: {
                return false;
            }
        }
    }
    
    public void endDateDialog(final ATime date) {
        this.dateDialog = null;
        this.buttonDate.enable();
        if (date != null) {
            this.atime = this.limitATime(date);
            this.orbitCanvas.setDate(date);
            this.orbitCanvas.repaint();
        }
    }
    
    static {
        timeStepLabel = new String[] { "1 Hour", "1 Day", "3 Days", "10 Days", "1 Month", "3 Months", "6 Months", "1 Year" };
        timeStepSpan = new TimeSpan[] { new TimeSpan(0, 0, 0, 1, 0, 0.0), new TimeSpan(0, 0, 1, 0, 0, 0.0), new TimeSpan(0, 0, 3, 0, 0, 0.0), new TimeSpan(0, 0, 10, 0, 0, 0.0), new TimeSpan(0, 1, 0, 0, 0, 0.0), new TimeSpan(0, 3, 0, 0, 0, 0.0), new TimeSpan(0, 6, 0, 0, 0, 0.0), new TimeSpan(1, 0, 0, 0, 0, 0.0) };
        CenterObjectLabel = new String[] { "Sun", "Asteroid/Comet", "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto" };
        OrbitDisplayLabel = new String[] { "Default Orbits", "All Orbits", "No Orbits", "------", "Asteroid/Comet", "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto" };
    }
}
