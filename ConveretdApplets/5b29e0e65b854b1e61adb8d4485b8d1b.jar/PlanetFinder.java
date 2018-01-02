import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Event;
import java.awt.Graphics;
import java.util.TimeZone;
import java.util.Locale;
import java.util.GregorianCalendar;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PlanetFinder extends Applet
{
    Language lang;
    Text txt;
    long tzOffset;
    Panel sky;
    Panel dstPanel;
    Panel controls;
    Panel controls2;
    Panel timePanel;
    Panel locationPanel;
    Panel latPanel;
    Panel lonPanel;
    Panel viewCheckboxPanel;
    StaticText localTimeStaticText;
    StaticText latitudeStaticText;
    StaticText longitudeStaticText;
    StaticText timeZoneLabel;
    StaticText homePageAndAuthorStaticText;
    StaticText homePageAndAuthor2StaticText;
    Checkbox dstCheckbox;
    Checkbox dstAutoCheckbox;
    Checkbox dstManualCheckbox;
    Checkbox latNCheckbox;
    Checkbox latSCheckbox;
    Checkbox lonECheckbox;
    Checkbox lonWCheckbox;
    Checkbox skyCheckbox;
    Checkbox innerSolarSystemCheckbox;
    Checkbox outerSolarSystemCheckbox;
    Checkbox nakedEyeOnlyCheckbox;
    CheckboxGroup dstCheckboxGroup;
    CheckboxGroup latNSCheckboxGroup;
    CheckboxGroup lonEWCheckboxGroup;
    CheckboxGroup viewCheckboxGroup;
    Choice cityChoice;
    Choice monthChoice;
    TextField hourText;
    TextField minuteText;
    TextField dayText;
    TextField yearText;
    TextField yourLatText;
    TextField yourLongText;
    TextField timeZoneText;
    Button dayPlusButton;
    Button dayMinusButton;
    Button monthPlusButton;
    Button monthMinusButton;
    Button yearPlusButton;
    Button yearMinusButton;
    Button updateButton;
    long J2000EpochMinus1970Epoch;
    boolean validDate;
    boolean validLatLon;
    boolean validTimeZoneText;
    Color skyBlue;
    Color duskSkyColor;
    Color sunColor;
    Color moonIlluminatedColor;
    Color moonUnilluminatedColor;
    double optionDimmestMagnitude;
    boolean optionDisplayPlanets;
    boolean optionBlackAndWhiteOnly;
    boolean optionShowNSEW;
    boolean optionShowMagnitudes;
    boolean optionGiveLanguageChoice;
    GregorianCalendar gcalendar;
    GregorianCalendar gCalendar2000;
    double longitude;
    double latitude;
    Cartesian earthBasisX;
    Cartesian earthBasisY;
    Cartesian earthBasisZ;
    Planet mercury;
    Planet venus;
    Planet earth;
    Planet mars;
    Planet jupiter;
    Planet saturn;
    Planet uranus;
    Planet neptune;
    Planet pluto;
    Planet moon;
    Layout theLayout;
    final double earthRotationTilt = 0.4092797;
    final double earthRotationPeriod = 0.9972708;
    final double earthRotationPhase = -1.747;
    final double sunMass = 333000.0;
    Planet[] planets;
    boolean[] nakedEyePlanet;
    Color[] planetColors;
    
    public PlanetFinder() {
        this.planets = new Planet[9];
        this.nakedEyePlanet = new boolean[] { true, true, true, true, true, true, false, false, false };
        this.planetColors = new Color[] { Color.orange, Color.white, Color.green, Color.red, new Color(250, 160, 162), new Color(170, 209, 163), Color.cyan, Color.blue, Color.white };
    }
    
    public void init() {
        try {
            this.lang = new Language(this.getParameter("language"));
        }
        catch (Exception ex) {
            this.lang = new Language(Locale.getDefault().getDisplayLanguage());
        }
        this.txt = new Text(this.lang);
        this.initVariables();
        this.nakedEyeOnlyCheckbox.setState(true);
        this.dstCheckbox.setState(TimeZone.getDefault().inDaylightTime(new GregorianCalendar().getTime()));
        try {
            this.tzOffset = new Long(Math.round(Double.valueOf(this.getParameter("timezone")) * 3600000.0));
        }
        catch (Exception ex2) {}
        this.setTimeZoneText(this.tzOffset);
        this.respondToSelectionOfTimeZone(true);
        double doubleValue = -999.0;
        double doubleValue2 = -999.0;
        try {
            doubleValue = Double.valueOf(this.getParameter("latitude"));
        }
        catch (Exception ex3) {}
        try {
            doubleValue2 = Double.valueOf(this.getParameter("longitude"));
        }
        catch (Exception ex4) {}
        if (doubleValue > -900.0 && doubleValue2 > -900.0) {
            this.selectNoCity();
            this.setLatLon(doubleValue, doubleValue2);
        }
        try {
            this.optionDisplayPlanets = this.getParameter("displayplanets").equals("y");
        }
        catch (Exception ex5) {}
        try {
            this.optionBlackAndWhiteOnly = this.getParameter("blackandwhiteonly").equals("y");
        }
        catch (Exception ex6) {}
        try {
            this.optionShowNSEW = this.getParameter("shownsew").equals("y");
        }
        catch (Exception ex7) {}
        try {
            this.optionDimmestMagnitude = Double.valueOf(this.getParameter("dimmestmagnitude"));
        }
        catch (Exception ex8) {}
        try {
            this.initApplet(doubleValue <= -900.0 || doubleValue2 <= -900.0);
        }
        catch (Exception ex9) {}
        this.repaint();
    }
    
    private void initVariables() {
        this.initVariablesA();
        this.initVariablesB();
        this.initVariablesC1();
        this.initVariablesC2();
        this.initVariablesD();
    }
    
    private void initVariablesA() {
        this.tzOffset = TimeZone.getDefault().getRawOffset();
        this.sky = new Panel() {
            public void paint(final Graphics graphics) {
                PlanetFinder.this.updateSky();
            }
        };
        this.localTimeStaticText = new StaticText(this.txt.localTime);
        this.latitudeStaticText = new StaticText(this.txt.latitude);
        this.longitudeStaticText = new StaticText(this.txt.longitude);
        this.homePageAndAuthorStaticText = new StaticText("Planet Finder applet by B. Crowell, www.lightandmatter.com, (c) 1999.");
        this.homePageAndAuthor2StaticText = new StaticText("The source code is open, and I welcome help with translating the applet into new languages!");
        this.dstPanel = new Panel();
        this.dstCheckbox = new Checkbox(this.txt.daylightSavings);
        this.dstCheckboxGroup = new CheckboxGroup();
        this.dstAutoCheckbox = new Checkbox(this.txt.auto, this.dstCheckboxGroup, true);
        this.dstManualCheckbox = new Checkbox(this.txt.manual, this.dstCheckboxGroup, false);
        this.latNSCheckboxGroup = new CheckboxGroup();
        this.latNCheckbox = new Checkbox(this.txt.northLetter, this.latNSCheckboxGroup, true);
        this.latSCheckbox = new Checkbox(this.txt.southLetter, this.latNSCheckboxGroup, false);
        this.lonEWCheckboxGroup = new CheckboxGroup();
        this.lonECheckbox = new Checkbox(this.txt.eastLetter, this.lonEWCheckboxGroup, false);
        this.lonWCheckbox = new Checkbox(this.txt.westLetter, this.lonEWCheckboxGroup, true);
        this.cityChoice = new Choice();
    }
    
    private void initVariablesB() {
        this.viewCheckboxGroup = new CheckboxGroup();
        this.skyCheckbox = new Checkbox(this.txt.sky, this.viewCheckboxGroup, true);
        this.innerSolarSystemCheckbox = new Checkbox(this.txt.innerSolarSystem, this.viewCheckboxGroup, false);
        this.outerSolarSystemCheckbox = new Checkbox(this.txt.outerSolarSystem, this.viewCheckboxGroup, false);
        this.monthChoice = new Choice();
        this.hourText = new TextField("    ");
        this.minuteText = new TextField("    ");
        this.dayText = new TextField("    ");
        this.yearText = new TextField("       ");
        this.dayPlusButton = new Button("+");
        this.dayMinusButton = new Button("-");
        this.monthPlusButton = new Button("+");
        this.monthMinusButton = new Button("-");
        this.yearPlusButton = new Button("+");
        this.yearMinusButton = new Button("-");
        this.yourLatText = new TextField("34.00");
        this.yourLongText = new TextField("-118.00");
        this.updateButton = new Button(this.txt.update);
        this.validDate = true;
        this.validLatLon = true;
        this.nakedEyeOnlyCheckbox = new Checkbox(this.txt.nakedEyePlanetsOnly);
        this.timeZoneLabel = new StaticText(this.txt.timeZone);
        this.timeZoneText = new TextField("    ");
        this.validTimeZoneText = false;
        this.skyBlue = new Color(0.5f, 0.7f, 1.0f);
        this.duskSkyColor = Color.blue.brighter();
        this.optionDisplayPlanets = true;
        this.optionDimmestMagnitude = 4.0;
        this.optionBlackAndWhiteOnly = false;
        this.optionShowNSEW = true;
        this.optionShowMagnitudes = true;
        this.optionGiveLanguageChoice = false;
    }
    
    private void initVariablesC1() {
        this.gcalendar = new GregorianCalendar();
        this.gCalendar2000 = new GregorianCalendar(2000, 0, 1, 0, 0, 0);
        this.controls = new Panel();
        this.controls2 = new Panel();
        this.timePanel = new Panel();
        this.locationPanel = new Panel();
        this.latPanel = new Panel();
        this.lonPanel = new Panel();
        this.viewCheckboxPanel = new Panel();
    }
    
    private void initVariablesC2() {
        this.mercury = new Planet(this.txt.planetNames[0], 0.0558, 87.969, 0.38709893, 0.20563069, 7.00487, 48.33167, 77.45645, 252.25084, true, -0.36, 0.027, 2.2E-13, 6.0);
        this.venus = new Planet(this.txt.planetNames[1], 0.815, 224.701, 0.72333199, 0.00677323, 3.39471, 76.68069, 131.53298, 181.97973, true, -4.34, 0.013, 4.2E-7, 3.0);
        this.earth = new Planet(this.txt.planetNames[2], 1.0, 365.256, 1.00000011, 0.01671022, 5.0E-5, -11.26064, 102.94719, 100.46435, false, 0.0, 0.0, 0.0, 0.0);
        this.mars = new Planet(this.txt.planetNames[3], 0.1075, 686.98, 1.52366231, 0.09341233, 1.85061, 49.57854, 336.04084, 355.45332, true, -1.51, 0.016, 0.0, 1.0);
        this.jupiter = new Planet(this.txt.planetNames[4], 317.83, 4332.589, 5.20336301, 0.04839266, 1.3053, 100.55615, 14.75385, 34.40438, true, -9.25, 0.014, 0.0, 1.0);
        this.saturn = new Planet(this.txt.planetNames[5], 95.147, 10759.22, 9.53707032, 0.0541506, 2.48446, 113.71504, 92.43194, 49.94432, true, -9.0, 0.044, 0.0, 1.0);
        this.uranus = new Planet(this.txt.planetNames[6], 14.54, 30685.4, 19.19126393, 0.04716771, 0.76986, 74.22988, 170.96424, 313.23218, true, -7.15, 0.001, 0.0, 1.0);
        this.neptune = new Planet(this.txt.planetNames[7], 17.23, 60189.0, 30.06896348, 0.00858587, 1.76917, 131.72169, 44.97135, 304.88003, true, -6.9, 0.001, 0.0, 1.0);
        this.pluto = new Planet(this.txt.planetNames[8], 0.0022, 90465.0, 39.48168677, 0.24880766, 17.14175, 110.30347, 224.06676, 238.92881, false, 0.0, 0.0, 0.0, 0.0);
    }
    
    private void initVariablesD() {
        this.planets[0] = this.mercury;
        this.planets[1] = this.venus;
        this.planets[2] = this.earth;
        this.planets[3] = this.mars;
        this.planets[4] = this.jupiter;
        this.planets[5] = this.saturn;
        this.planets[6] = this.uranus;
        this.planets[7] = this.neptune;
        this.planets[8] = this.pluto;
        this.sunColor = Color.yellow;
        this.moonIlluminatedColor = Color.white;
        this.moonUnilluminatedColor = Color.gray;
    }
    
    private void initApplet(final boolean b) {
        this.hourText.setEditable(true);
        this.minuteText.setEditable(true);
        this.dayText.setEditable(true);
        this.yearText.setEditable(true);
        this.yourLatText.setEditable(true);
        this.yourLongText.setEditable(true);
        this.J2000EpochMinus1970Epoch = this.gCalendar2000.getTime().getTime();
        this.setMonthNames(this.txt);
        this.cityChoice.addItem("?");
        for (int i = 0; i < CityList.cityList.length; ++i) {
            this.cityChoice.addItem(CityList.cityList[i].name);
        }
        this.makeTextReflectDate();
        this.earthBasisX = Cartesian.xHat();
        this.earthBasisZ = new Cartesian(0.0, Math.sin(0.4092797), Math.cos(0.4092797));
        this.earthBasisY = this.earthBasisZ.crossProduct(this.earthBasisX);
        this.theLayout = new Layout(this);
        this.respondToSelectionOfTimeZone(b);
        this.respondToChangeInInputs();
        this.updateTimeZone();
        this.updateSky();
    }
    
    public boolean handleEvent(final Event event) {
        boolean b = false;
        switch (event.id) {
            case 1001: {
                if (event.target == this.dstCheckbox) {
                    this.dstManualCheckbox.setState(true);
                }
                if (event.target == this.cityChoice) {
                    this.respondToSelectionOfCity(this.cityChoice.getSelectedItem());
                }
                if (event.target == this.timeZoneText) {
                    this.respondToSelectionOfTimeZone(true);
                }
                if (event.target == this.lonECheckbox || event.target == this.lonWCheckbox || event.target == this.latNCheckbox || event.target == this.latSCheckbox) {
                    this.selectNoCity();
                }
                if (event.target == this.yourLatText || event.target == this.yourLongText) {
                    this.selectNoCity();
                }
                this.respondToChangeInInputs();
                b = true;
                break;
            }
            default: {
                b = false;
                break;
            }
        }
        return b;
    }
    
    private void setMonthNames(final Text text) {
        this.monthChoice.removeAll();
        for (int i = 0; i <= 11; ++i) {
            this.monthChoice.addItem(text.monthNames[i]);
        }
    }
    
    private void setPlanetNames(final Text text) {
        for (int i = 0; i < this.planets.length; ++i) {
            this.planets[i].name = text.planetNames[i];
        }
        this.moon.name = text.moon;
    }
    
    private void selectNoCity() {
        this.cityChoice.select("?");
    }
    
    private void respondToSelectionOfTimeZone(final boolean b) {
        double doubleValue;
        try {
            doubleValue = Double.valueOf(this.timeZoneText.getText());
        }
        catch (Exception ex) {
            this.selectNoCity();
            return;
        }
        if (b) {
            try {
                final String name = CityList.biggestCityInTimeZone(doubleValue, this.lang).name;
                this.cityChoice.select(name);
                this.respondToSelectionOfCity(name);
            }
            catch (Exception ex2) {
                this.selectNoCity();
                this.setLatLon(0.0, doubleValue * 15.0);
            }
        }
    }
    
    private void respondToSelectionOfCity(final String s) {
        City city = null;
        for (int i = 0; i < CityList.cityList.length; ++i) {
            if (CityList.cityList[i].name.equals(s)) {
                city = CityList.cityList[i];
            }
        }
        if (city == null) {
            return;
        }
        this.setLatLon(city.latDegrees, city.lonDegrees);
        this.setTimeZoneText(city.timeZone * 3600000.0);
    }
    
    private void setLatLon(final double n, final double n2) {
        if (Math.abs(n) > 1.0E-20) {
            this.yourLatText.setText(String.valueOf(Math.abs(n)));
        }
        else {
            this.yourLatText.setText("0");
        }
        if (n < 0.0) {
            this.latSCheckbox.setState(true);
        }
        else {
            this.latNCheckbox.setState(true);
        }
        if (Math.abs(n2) > 1.0E-20) {
            this.yourLongText.setText(String.valueOf(Math.abs(n2)));
        }
        else {
            this.yourLongText.setText("0");
        }
        if (n2 < 0.0) {
            this.lonWCheckbox.setState(true);
        }
        else {
            this.lonECheckbox.setState(true);
        }
    }
    
    private void setTimeZoneText(final double n) {
        if (Math.abs(n) > 1.0E-20) {
            if (Math.abs(Math.round(n / 3600000.0) - n / 3600000.0) < 0.1) {
                this.timeZoneText.setText(String.valueOf(Math.round(n / 3600000.0)));
            }
            else {
                this.timeZoneText.setText(String.valueOf(0.1 * Math.round(10.0 * n / 3600000.0)));
            }
        }
        else {
            this.timeZoneText.setText("0");
        }
        this.validTimeZoneText = true;
    }
    
    private void makeTextReflectDate() {
        this.yearText.setText(String.valueOf(this.gcalendar.get(1)));
        this.monthChoice.select(this.gcalendar.get(2));
        this.dayText.setText(String.valueOf(this.gcalendar.get(5)));
        int value = this.gcalendar.get(10);
        if (this.gcalendar.get(9) == 1) {
            value += 12;
        }
        this.hourText.setText(String.valueOf(value));
        final int value2 = this.gcalendar.get(12);
        if (value2 >= 10) {
            this.minuteText.setText(String.valueOf(value2));
        }
        else {
            this.minuteText.setText("0" + String.valueOf(value2));
        }
    }
    
    private void respondToChangeInInputs() {
        try {
            this.gcalendar.set(Integer.parseInt(this.yearText.getText()), this.monthChoice.getSelectedIndex(), Integer.parseInt(this.dayText.getText()), Integer.parseInt(this.hourText.getText()), Integer.parseInt(this.minuteText.getText()));
            this.validDate = true;
        }
        catch (NumberFormatException ex) {
            this.validDate = false;
        }
        try {
            this.longitude = Math.abs(Double.valueOf(this.yourLongText.getText()) * 3.141592653589793 / 180.0);
            if (this.lonWCheckbox.getState()) {
                this.longitude = -this.longitude;
            }
            this.latitude = Math.abs(Double.valueOf(this.yourLatText.getText()) * 3.141592653589793 / 180.0);
            if (this.latSCheckbox.getState()) {
                this.latitude = -this.latitude;
            }
            this.validLatLon = true;
        }
        catch (NumberFormatException ex2) {
            this.validLatLon = false;
        }
        this.updateTimeZone();
        if (this.validDate && this.dstAutoCheckbox.getState()) {
            this.dstCheckbox.setState(TimeZone.getDefault().inDaylightTime(this.gcalendar.getTime()));
        }
        this.updateSky();
    }
    
    private void updateTimeZone() {
        try {
            this.tzOffset = new Long(Math.round(Double.valueOf(this.timeZoneText.getText()) * 3600000.0));
            this.validTimeZoneText = true;
        }
        catch (NumberFormatException ex) {
            this.validTimeZoneText = false;
        }
    }
    
    private void updateSky() {
        this.updateTimeZone();
        if (this.validDate && this.validLatLon && this.validTimeZoneText) {
            final Graphics graphics = this.sky.getGraphics();
            final Rectangle bounds = this.sky.getBounds();
            final Point point = new Point(bounds.width / 2, bounds.height / 2);
            int n = bounds.width;
            if (bounds.height < n) {
                n = bounds.height;
            }
            final Rectangle rectangle = new Rectangle(point.x - n / 2, 0, n, n);
            final Point point2 = new Point(rectangle.x + rectangle.width / 2, rectangle.y + rectangle.height / 2);
            this.dstCheckbox.getState();
            final double n2 = new Long(this.gcalendar.getTime().getTime() - this.J2000EpochMinus1970Epoch - this.tzOffset + 0L) / 8.64E7;
            final double n3 = 125.1228;
            final double n4 = 318.0634;
            final double n5 = n3 - 0.0529538083 * n2;
            this.moon = new Planet(this.txt.moon, 0.0123, 27.322, 0.002569519, 0.0549, 5.145, n5, n5 + (n4 + 0.1643573223 * n2), 218.32, false, 0.0, 0.0, 0.0, 0.0);
            if (this.skyCheckbox.getState()) {
                final boolean b = false;
                final double n6 = n2 / 0.9972708 * 2.0 * 3.141592653589793 + 1.747;
                final Cartesian plus = this.earthBasisX.scalarMult(Math.cos(n6)).plus(this.earthBasisY.scalarMult(Math.sin(n6)));
                final Cartesian crossProduct = this.earthBasisZ.crossProduct(plus);
                final Cartesian crossProduct2 = plus.crossProduct(crossProduct);
                final Cartesian plus2 = plus.scalarMult(Math.cos(this.longitude)).plus(crossProduct.scalarMult(Math.sin(this.longitude)));
                final Cartesian plus3 = crossProduct.scalarMult(Math.cos(this.longitude)).plus(plus.scalarMult(-Math.sin(this.longitude)));
                final Cartesian plus4 = plus2.scalarMult(Math.cos(this.latitude)).plus(crossProduct2.scalarMult(Math.sin(this.latitude)));
                final Cartesian cartesian = plus3;
                final Cartesian crossProduct3 = plus4.crossProduct(cartesian);
                final Cartesian plus5 = this.earth.position(n2, 1.0E-6, b).plus(plus4.scalarMult(4.3E-5));
                final Cartesian position = this.moon.position(n2, 1.0E-6, b);
                final double magnitude = position.magnitude();
                final double n7 = Math.acos(-position.z / magnitude) - 1.5707963267948966;
                double atan = Math.atan(position.y / position.x);
                if (atan < -1.5707963267948966) {
                    atan += 3.141592653589793;
                }
                if (atan > 1.5707963267948966) {
                    atan -= 3.141592653589793;
                }
                if (position.x < 0.0) {
                    atan += 3.141592653589793;
                }
                if (atan < 0.0) {
                    atan += 6.283185307179586;
                }
                if (atan > 6.283185307179586) {
                    atan -= 6.283185307179586;
                }
                final Cartesian scalarMult = Cartesian.latLongToUnitVector(n7 + MoonPerturbations.moonLatitudeCorrectionDegrees(n2) * 3.141592653589793 / 180.0, atan + MoonPerturbations.moonLongitudeCorrectionDegrees(n2) * 3.141592653589793 / 180.0).scalarMult(magnitude);
                final Cartesian plus6 = scalarMult.plus(plus5);
                final double n8 = (3.141592653589793 - plus4.angleBetween(plus5)) * 180.0 / 3.141592653589793;
                final boolean b2 = n8 > 90.0;
                final boolean b3 = n8 < 90.0 && n8 > 80.0;
                graphics.setColor(Color.white);
                graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                Color color;
                Color color2;
                if (b2 || this.optionBlackAndWhiteOnly) {
                    color = Color.black;
                    color2 = Color.blue;
                }
                else {
                    if (!b3) {
                        color = this.skyBlue;
                    }
                    else {
                        color = this.duskSkyColor;
                    }
                    color2 = Color.black;
                }
                graphics.setColor(color);
                graphics.fillOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                if (this.optionShowNSEW) {
                    graphics.setColor(color2);
                    graphics.drawString(this.txt.northLetter, point2.x + n / 2 - 12, point2.y);
                    graphics.drawString(this.txt.southLetter, point2.x - n / 2 + 12, point2.y);
                    graphics.drawString(this.txt.eastLetter, point2.x, point2.y - n / 2 + 12);
                    graphics.drawString(this.txt.westLetter, point2.x, point2.y + n / 2 - 12);
                }
                for (int i = 0; i < BrightStarCatalog.mag.length; ++i) {
                    final double n9 = BrightStarCatalog.mag[i];
                    if (n9 < this.optionDimmestMagnitude) {
                        final double n10 = BrightStarCatalog.rightAscensionInRadians[i];
                        final double n11 = BrightStarCatalog.declinationInRadians[i];
                        final double cos = Math.cos(n11);
                        final Cartesian plus7 = this.earthBasisX.scalarMult(cos * Math.cos(n10)).plus(this.earthBasisY.scalarMult(cos * Math.sin(n10))).plus(this.earthBasisZ.scalarMult(Math.sin(n11)));
                        final double dotProduct = plus7.dotProduct(plus4);
                        if (dotProduct > 0.0) {
                            final double acos = Math.acos(dotProduct);
                            final double dotProduct2 = plus7.dotProduct(cartesian);
                            final double dotProduct3 = plus7.dotProduct(crossProduct3);
                            final double n12 = 1.414214 * Math.sin(acos / 2.0) / Math.sqrt(dotProduct2 * dotProduct2 + dotProduct3 * dotProduct3);
                            final double n13 = dotProduct2 * n12;
                            final double n14 = dotProduct3 * n12;
                            final double n15 = 0.5 * n13 * new Integer(n);
                            final double n16 = 0.5 * n14 * new Integer(n);
                            final int intValue = (int)(Object)new Long(Math.round(n15));
                            final int intValue2 = (int)(Object)new Long(Math.round(n16));
                            this.drawDot(graphics, this.magToDotColor(n9), point2.x + 0 * intValue + 1 * intValue2, point2.y + -1 * intValue + 0 * intValue2, this.magToDotDiam(n9));
                        }
                    }
                }
                Cartesian plus8 = new Cartesian(0.0, 0.0, 0.0);
                final Cartesian[] array = new Cartesian[this.planets.length];
                for (int j = 0; j < this.planets.length; ++j) {
                    array[j] = this.planets[j].position(n2, 1.0E-6, b);
                    plus8 = plus8.plus(array[j].scalarMult(this.planets[j].mass));
                }
                final Cartesian scalarMult2 = plus8.scalarMult(-3.003003003003003E-6);
                for (int k = -1; k < this.planets.length; ++k) {
                    boolean b4 = false;
                    boolean b5 = false;
                    boolean b6 = false;
                    Cartesian cartesian2;
                    Color color3;
                    String s;
                    if (k != -1 && this.planets[k] != this.earth) {
                        cartesian2 = array[k].plus(plus5.scalarMult(-1.0)).makeUnitLength();
                        color3 = this.planetColors[k];
                        s = this.planets[k].name;
                        b6 = (this.nakedEyeOnlyCheckbox.getState() && !this.nakedEyePlanet[k]);
                    }
                    else if (k == -1) {
                        cartesian2 = scalarMult2.plus(plus5.scalarMult(-1.0)).makeUnitLength();
                        color3 = this.sunColor;
                        s = this.txt.sun;
                        b4 = true;
                    }
                    else {
                        cartesian2 = plus6.plus(plus5.scalarMult(-1.0)).makeUnitLength();
                        s = this.txt.moon;
                        b5 = true;
                        color3 = this.moonIlluminatedColor;
                    }
                    if (!b) {
                        final double dotProduct4 = cartesian2.dotProduct(plus4);
                        if (dotProduct4 > 0.0 && !b6) {
                            final double acos2 = Math.acos(dotProduct4);
                            final double dotProduct5 = cartesian2.dotProduct(cartesian);
                            final double dotProduct6 = cartesian2.dotProduct(crossProduct3);
                            final double n17 = 1.414214 * Math.sin(acos2 / 2.0) / Math.sqrt(dotProduct5 * dotProduct5 + dotProduct6 * dotProduct6);
                            final double n18 = dotProduct5 * n17;
                            final double n19 = dotProduct6 * n17;
                            final double n20 = 0.5 * n18 * new Integer(n);
                            final double n21 = 0.5 * n19 * new Integer(n);
                            final int intValue3 = (int)(Object)new Long(Math.round(n20));
                            final int intValue4 = (int)(Object)new Long(Math.round(n21));
                            final int n22 = point2.x + 0 * intValue3 + 1 * intValue4;
                            final int n23 = point2.y + -1 * intValue3 + 0 * intValue4;
                            int n24;
                            if (b4 || b5) {
                                n24 = 7;
                            }
                            else {
                                n24 = 4;
                            }
                            if (this.optionDisplayPlanets) {
                                if (!b5) {
                                    graphics.setColor(color3);
                                    graphics.fillOval(n22 - n24, n23 - n24, n24 * 2, n24 * 2);
                                    if (!b2) {
                                        graphics.setColor(Color.black);
                                        graphics.drawOval(n22 - n24, n23 - n24, n24 * 2, n24 * 2);
                                    }
                                }
                                else {
                                    final Cartesian unitLength = scalarMult.crossProduct(plus6).makeUnitLength();
                                    Cartesian cartesian3 = plus6.crossProduct(unitLength).makeUnitLength();
                                    if (cartesian3.dotProduct(scalarMult) > 0.0) {
                                        cartesian3 = cartesian3.scalarMult(-1.0);
                                    }
                                    Cartesian cartesian4 = scalarMult.crossProduct(unitLength).makeUnitLength();
                                    if (cartesian4.dotProduct(plus6) > 0.0) {
                                        cartesian4 = cartesian4.scalarMult(-1.0);
                                    }
                                    final Cartesian unitLength2 = crossProduct3.crossProduct(scalarMult).makeUnitLength();
                                    final Cartesian unitLength3 = scalarMult.crossProduct(unitLength2).makeUnitLength();
                                    final Polygon polygon = new Polygon(new int[16], new int[16], 16);
                                    for (int l = 0; l <= 7; ++l) {
                                        final double n25 = 3.141592653589793 * l / 7.0;
                                        final Cartesian plus9 = unitLength.scalarMult(Math.cos(n25)).plus(cartesian3.scalarMult(Math.sin(n25)));
                                        polygon.xpoints[l] = n22 + (int)(Object)new Long(Math.round((0.0 * plus9.dotProduct(unitLength2) + 1.0 * plus9.dotProduct(unitLength3)) * n24));
                                        polygon.ypoints[l] = n23 + (int)(Object)new Long(Math.round((-1.0 * plus9.dotProduct(unitLength2) + 0.0 * plus9.dotProduct(unitLength3)) * n24));
                                    }
                                    for (int n26 = 0; n26 <= 7; ++n26) {
                                        final double n27 = 3.141592653589793 * n26 / 7.0;
                                        final Cartesian plus10 = unitLength.scalarMult(-Math.cos(n27)).plus(cartesian4.scalarMult(Math.sin(n27)));
                                        polygon.xpoints[n26 + 8] = n22 + (int)(Object)new Long(Math.round((0.0 * plus10.dotProduct(unitLength2) + 1.0 * plus10.dotProduct(unitLength3)) * n24));
                                        polygon.ypoints[n26 + 8] = n23 + (int)(Object)new Long(Math.round((-1.0 * plus10.dotProduct(unitLength2) + 0.0 * plus10.dotProduct(unitLength3)) * n24));
                                    }
                                    graphics.setColor(this.moonUnilluminatedColor);
                                    graphics.fillOval(n22 - n24, n23 - n24, n24 * 2, n24 * 2);
                                    graphics.setColor(this.moonIlluminatedColor);
                                    graphics.fillPolygon(polygon);
                                }
                            }
                            if (this.optionDisplayPlanets) {
                                if (!b2) {
                                    graphics.setColor(color2);
                                }
                                String value = "";
                                if (!b5 && !b4 && this.optionShowMagnitudes && this.planets[k].mag) {
                                    final double magnitude2 = array[k].magnitude();
                                    final Cartesian plus11 = array[k].plus(plus5.scalarMult(-1.0));
                                    value = String.valueOf(Math.round(10.0 * this.planets[k].magnitude(magnitude2, plus11.magnitude(), array[k].angleBetween(plus11))) / 10.0);
                                }
                                graphics.drawString(s + " " + value, n22 + n24 + 3, n23);
                            }
                        }
                    }
                }
            }
            else {
                final boolean state = this.innerSolarSystemCheckbox.getState();
                int n28 = 4;
                final boolean b7 = false;
                double n29;
                if (state) {
                    n29 = 1.8;
                }
                else {
                    n29 = 45.0;
                }
                graphics.setColor(Color.black);
                graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                for (int n30 = -1; n30 < this.planets.length; ++n30) {
                    if ((state && n30 <= 3) || (!state && n30 != 0 && n30 != 1 && n30 != 3)) {
                        Color sunColor;
                        Cartesian position2;
                        String s2;
                        if (n30 == -1) {
                            sunColor = this.sunColor;
                            n28 = 5;
                            position2 = new Cartesian(0.0, 0.0, 0.0);
                            s2 = this.txt.sun;
                        }
                        else {
                            position2 = this.planets[n30].position(n2, 1.0E-6, b7);
                            sunColor = this.planetColors[n30];
                            s2 = this.planets[n30].name;
                        }
                        if (!b7) {
                            final double x = position2.x;
                            final double y = position2.y;
                            final double n31 = x / n29;
                            final double n32 = y / n29;
                            final double n33 = 0.5 * n31 * new Integer(n);
                            final double n34 = 0.5 * n32 * new Integer(n);
                            final int n35 = point2.x + (int)(Object)new Long(Math.round(n33));
                            final int n36 = point2.y - (int)(Object)new Long(Math.round(n34));
                            graphics.setColor(sunColor);
                            graphics.fillOval(n35 - n28, n36 - n28, n28 * 2, n28 * 2);
                            graphics.drawString(s2, n35 + n28 + 3, n36);
                        }
                    }
                }
            }
        }
    }
    
    private void drawDot(final Graphics graphics, final Color color, final int n, final int n2, final int n3) {
        graphics.setColor(color);
        graphics.fillOval(n - n3 / 2, n2 - n3 / 2, n3, n3);
    }
    
    private int magToDotDiam(final double n) {
        int n2 = 1;
        if (n < 2.2) {
            n2 = 2;
        }
        if (n < 1.4) {
            n2 = 3;
        }
        if (n < 0.6) {
            n2 = 4;
        }
        if (n < -0.2) {
            n2 = 5;
        }
        return n2;
    }
    
    private Color magToDotColor(final double n) {
        if (n < 2.8) {
            return Color.white;
        }
        return Color.gray;
    }
}
