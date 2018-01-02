import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ActionListener;
import javax.swing.JMenu;

// 
// Decompiled by Procyon v0.5.30
// 

public class SensorMenu extends JMenu implements ActionListener
{
    private imgViewer applet;
    private JCheckBoxMenuItem[] cb;
    private Sensor currentSensor;
    private Sensor[] sensors;
    private JMenu aerialMenu;
    private JMenu aquaMenu;
    private JMenu asterMenu;
    private JMenu combinedAquaTerraMenu;
    private JMenu eo1Menu;
    private JMenu landsatArchiveMenu;
    private JMenu glsMenu;
    private JMenu landsatMrlcMenu;
    private JMenu landsatLegacyMenu;
    private JMenu terraLookMenu;
    private JMenu terraMenu;
    private JMenuItem aerialDescription;
    private JMenuItem aquaDescription;
    private JMenuItem asterDescription;
    private JMenuItem combinedAquaTerraDescription;
    private JMenuItem eo1Description;
    private JMenuItem landsatArchiveDescription;
    private JMenuItem glsDescription;
    private JMenuItem landsatMrlcDescription;
    private JMenuItem landsatLegacyDescription;
    private JMenuItem terraDescription;
    private JMenuItem terraLookDescription;
    
    public SensorMenu(final imgViewer applet, final SensorStatus sensorStatus, final boolean b) {
        super("Collection");
        this.setMnemonic(67);
        this.applet = applet;
        int n = 0;
        final SensorStatus[] values = SensorStatus.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            if (values[i].isUsed()) {
                ++n;
            }
        }
        this.sensors = new Sensor[n];
        this.cb = new JCheckBoxMenuItem[n];
        (this.landsatArchiveMenu = new JMenu("Landsat Archive")).setMnemonic(76);
        if (b) {
            this.landsatArchiveMenu.setEnabled(false);
        }
        (this.glsMenu = new JMenu("Global Land Survey")).setMnemonic(71);
        if (b) {
            this.glsMenu.setEnabled(false);
        }
        (this.landsatMrlcMenu = new JMenu("Landsat MRLC Collections")).setMnemonic(67);
        if (b) {
            this.landsatMrlcMenu.setEnabled(false);
        }
        (this.landsatLegacyMenu = new JMenu("Landsat Legacy Collections")).setMnemonic(78);
        if (b) {
            this.landsatLegacyMenu.setEnabled(false);
        }
        (this.asterMenu = new JMenu("ASTER")).setMnemonic(65);
        if (b) {
            this.asterMenu.setEnabled(false);
        }
        (this.aquaMenu = new JMenu("MODIS Aqua")).setMnemonic(77);
        if (b) {
            this.aquaMenu.setEnabled(false);
        }
        (this.terraMenu = new JMenu("MODIS Terra")).setMnemonic(79);
        if (b) {
            this.terraMenu.setEnabled(false);
        }
        (this.combinedAquaTerraMenu = new JMenu("MODIS Combined")).setMnemonic(68);
        if (b) {
            this.combinedAquaTerraMenu.setEnabled(false);
        }
        (this.eo1Menu = new JMenu("EO-1")).setMnemonic(69);
        if (b) {
            this.eo1Menu.setEnabled(false);
        }
        (this.terraLookMenu = new JMenu("TerraLook")).setMnemonic(84);
        if (b) {
            this.terraLookMenu.setEnabled(false);
        }
        (this.aerialMenu = new JMenu("Aerial")).setMnemonic(82);
        if (b) {
            this.aerialMenu.setEnabled(false);
        }
        (this.aerialDescription = new JMenuItem("Data Descriptions")).addActionListener(this);
        (this.aquaDescription = new JMenuItem("Data Descriptions")).addActionListener(this);
        (this.asterDescription = new JMenuItem("Data Descriptions")).addActionListener(this);
        (this.combinedAquaTerraDescription = new JMenuItem("Data Descriptions")).addActionListener(this);
        (this.eo1Description = new JMenuItem("Data Descriptions")).addActionListener(this);
        (this.landsatArchiveDescription = new JMenuItem("Data Descriptions")).addActionListener(this);
        (this.glsDescription = new JMenuItem("Data Descriptions")).addActionListener(this);
        (this.landsatMrlcDescription = new JMenuItem("Data Descriptions")).addActionListener(this);
        (this.landsatLegacyDescription = new JMenuItem("Data Descriptions")).addActionListener(this);
        (this.terraDescription = new JMenuItem("Data Descriptions")).addActionListener(this);
        (this.terraLookDescription = new JMenuItem("Data Descriptions")).addActionListener(this);
        int sensorMenuIndex = -1;
        boolean b2 = false;
        int sensorMenuIndex2 = 0;
        for (final SensorStatus sensorStatus2 : SensorStatus.values()) {
            if (sensorStatus2.isUsed()) {
                final SensorMenuInfo sensorFactory = this.sensorFactory(sensorStatus2);
                this.sensors[sensorMenuIndex2] = sensorFactory.sensor;
                (this.cb[sensorMenuIndex2] = new JCheckBoxMenuItem(this.sensors[sensorMenuIndex2].sensorName, false)).addActionListener(this);
                if (sensorFactory.description != null) {
                    this.cb[sensorMenuIndex2].setToolTipText(sensorFactory.description);
                }
                if (sensorStatus2 == SensorStatus.LANDSAT_COMBINED) {
                    sensorFactory.menu.addSeparator();
                }
                sensorFactory.menu.add(this.cb[sensorMenuIndex2]);
                if (sensorStatus == sensorStatus2) {
                    if (sensorFactory.sensor.confirmInitialDisplay()) {
                        sensorMenuIndex = sensorMenuIndex2;
                        b2 = true;
                    }
                    sensorFactory.menu.setEnabled(true);
                }
                sensorStatus2.setSensorMenuIndex(sensorMenuIndex2);
                ++sensorMenuIndex2;
            }
        }
        if (this.aerialMenu.getItemCount() > 0 && this.aerialMenu.isEnabled()) {
            this.aerialMenu.insert(this.aerialDescription, 0);
            this.aerialMenu.insertSeparator(1);
            if (this.getItemCount() > 0) {
                this.addSeparator();
            }
            this.add(this.aerialMenu);
        }
        if (this.asterMenu.getItemCount() > 0 && this.asterMenu.isEnabled()) {
            this.asterMenu.insert(this.asterDescription, 0);
            this.asterMenu.insertSeparator(1);
            if (this.getItemCount() > 0) {
                this.addSeparator();
            }
            this.add(this.asterMenu);
        }
        if (this.eo1Menu.getItemCount() > 0 && this.eo1Menu.isEnabled()) {
            this.eo1Menu.insert(this.eo1Description, 0);
            this.eo1Menu.insertSeparator(1);
            if (this.getItemCount() > 0) {
                this.addSeparator();
            }
            this.add(this.eo1Menu);
        }
        if (this.landsatArchiveMenu.getItemCount() > 0 && this.landsatArchiveMenu.isEnabled()) {
            this.landsatArchiveMenu.insert(this.landsatArchiveDescription, 0);
            this.landsatArchiveMenu.insertSeparator(1);
            if (this.getItemCount() > 0) {
                this.addSeparator();
            }
            this.add(this.landsatArchiveMenu);
        }
        if (this.glsMenu.getItemCount() > 0 && this.glsMenu.isEnabled()) {
            this.glsMenu.insert(this.glsDescription, 0);
            this.glsMenu.insertSeparator(1);
            if (this.getItemCount() > 0) {
                this.addSeparator();
            }
            this.add(this.glsMenu);
        }
        if (this.landsatMrlcMenu.getItemCount() > 0 && this.landsatMrlcMenu.isEnabled()) {
            this.landsatMrlcMenu.insert(this.landsatMrlcDescription, 0);
            this.landsatMrlcMenu.insertSeparator(1);
            if (this.getItemCount() > 0) {
                this.addSeparator();
            }
            this.add(this.landsatMrlcMenu);
        }
        if (this.landsatLegacyMenu.getItemCount() > 0 && this.landsatLegacyMenu.isEnabled()) {
            this.landsatLegacyMenu.insert(this.landsatLegacyDescription, 0);
            this.landsatLegacyMenu.insertSeparator(1);
            if (this.getItemCount() > 0) {
                this.addSeparator();
            }
            this.add(this.landsatLegacyMenu);
        }
        if (this.aquaMenu.getItemCount() > 0 && this.aquaMenu.isEnabled()) {
            this.aquaMenu.insert(this.aquaDescription, 0);
            this.aquaMenu.insertSeparator(1);
            if (this.getItemCount() > 0) {
                this.addSeparator();
            }
            this.add(this.aquaMenu);
        }
        if (this.terraMenu.getItemCount() > 0 && this.terraMenu.isEnabled()) {
            this.terraMenu.insert(this.terraDescription, 0);
            this.terraMenu.insertSeparator(1);
            if (this.getItemCount() > 0) {
                this.addSeparator();
            }
            this.add(this.terraMenu);
        }
        if (this.combinedAquaTerraMenu.getItemCount() > 0 && this.combinedAquaTerraMenu.isEnabled()) {
            this.combinedAquaTerraMenu.insert(this.combinedAquaTerraDescription, 0);
            this.combinedAquaTerraMenu.insertSeparator(1);
            if (this.getItemCount() > 0) {
                this.addSeparator();
            }
            this.add(this.combinedAquaTerraMenu);
        }
        if (this.terraLookMenu.getItemCount() > 0 && this.terraLookMenu.isEnabled()) {
            this.terraLookMenu.insert(this.terraLookDescription, 0);
            this.terraLookMenu.insertSeparator(1);
            if (this.getItemCount() > 0) {
                this.addSeparator();
            }
            this.add(this.terraLookMenu);
        }
        if (!b2) {
            sensorMenuIndex = SensorStatus.LANDSAT_ETM_SLC_OFF.sensorMenuIndex();
        }
        if (sensorMenuIndex < 0) {
            sensorMenuIndex = 0;
        }
        this.currentSensor = this.sensors[sensorMenuIndex];
        this.setSelectedCB(this.currentSensor.sensorName);
    }
    
    private int setSelectedCB(final String s) {
        int n = 0;
        for (int i = 0; i < this.sensors.length; ++i) {
            if (s.equals(this.sensors[i].sensorName)) {
                n = i;
                this.cb[i].setState(true);
            }
            else {
                this.cb[i].setState(false);
            }
        }
        return n;
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        String s = null;
        if (actionEvent.getActionCommand().equals("Data Descriptions")) {
            final JMenuItem menuItem = (JMenuItem)actionEvent.getSource();
            if (menuItem == this.aerialDescription) {
                s = "../AboutBrowse.shtml#aerialdescription";
            }
            else if (menuItem == this.aquaDescription) {
                s = "../AboutBrowse.shtml#aquanames";
            }
            else if (menuItem == this.asterDescription) {
                s = "../AboutBrowse.shtml#asterdescription";
            }
            else if (menuItem == this.combinedAquaTerraDescription) {
                s = "../AboutBrowse.shtml#combinedaquaterranames";
            }
            else if (menuItem == this.eo1Description) {
                s = "../AboutBrowse.shtml#eo1description";
            }
            else if (menuItem == this.landsatArchiveDescription) {
                s = "../AboutBrowse.shtml#landsatarchivedescription";
            }
            else if (menuItem == this.glsDescription) {
                s = "../AboutBrowse.shtml#glsdescription";
            }
            else if (menuItem == this.landsatMrlcDescription) {
                s = "../AboutBrowse.shtml#landsatmrlcdescription";
            }
            else if (menuItem == this.landsatLegacyDescription) {
                s = "../AboutBrowse.shtml#landsatlegacydescription";
            }
            else if (menuItem == this.terraDescription) {
                s = "../AboutBrowse.shtml#terranames";
            }
            else if (menuItem == this.terraLookDescription) {
                s = "../AboutBrowse.shtml#terralookdescription";
            }
        }
        if (s != null) {
            try {
                this.applet.getAppletContext().showDocument(new URL(this.applet.getCodeBase(), s), "glovishelp");
            }
            catch (Exception ex) {
                System.out.println("exception: " + ex);
            }
        }
        else {
            final int setSelectedCB = this.setSelectedCB(actionEvent.getActionCommand());
            if (this.currentSensor != this.sensors[setSelectedCB]) {
                if (!this.sensors[setSelectedCB].confirmInitialDisplay()) {
                    this.setSelectedCB(this.currentSensor.sensorName);
                    return;
                }
                this.currentSensor = this.sensors[setSelectedCB];
                this.applet.mapLayerMenu.configureForSensor(this.currentSensor);
                this.applet.imgArea.md.setSensor(this.currentSensor);
                this.applet.searchLimitDialog.configureForSensor(this.currentSensor);
                this.applet.sceneListPanel.setSensor(this.currentSensor);
                this.applet.hideSceneDialog.setSensor(this.currentSensor);
                this.applet.sceneListDialog.setSensor(this.currentSensor);
                this.applet.helpMenu.setSensor(this.currentSensor);
                this.applet.imgArea.logo.setSensor(this.currentSensor);
                this.applet.searchForSceneDialog.setSensor(this.currentSensor);
                this.applet.toolsMenu.setSensor(this.currentSensor);
            }
        }
    }
    
    public Sensor getCurrentSensor() {
        return this.currentSensor;
    }
    
    public Sensor[] getSensors() {
        return this.sensors;
    }
    
    private SensorMenuInfo sensorFactory(final SensorStatus sensorStatus) {
        SensorMenuInfo sensorMenuInfo = new SensorMenuInfo();
        switch (sensorStatus) {
            case ANTARCTICA_ETM: {
                sensorMenuInfo.sensor = new AntarcticaEtmDataset(this.applet);
                sensorMenuInfo.menu = this.landsatLegacyMenu;
                sensorMenuInfo.description = "Landsat Image Mosaic of Antarctica Enhanced Thematic Mapper Plus";
                break;
            }
            case LANDSAT_ETM: {
                sensorMenuInfo.sensor = new LandsatETMSensor(this.applet);
                sensorMenuInfo.menu = this.landsatArchiveMenu;
                sensorMenuInfo.description = "Landsat 7 Enhanced Thematic Mapper Plus - 1999 to May 2003";
                break;
            }
            case LANDSAT_ETM_SLC_OFF: {
                sensorMenuInfo.sensor = new LandsatETMSlcOffSensor(this.applet);
                sensorMenuInfo.menu = this.landsatArchiveMenu;
                sensorMenuInfo.description = "Landsat 7 Enhanced Thematic Mapper Plus Scan Line Corrector Off - July 2003 to present";
                break;
            }
            case LANDSAT_TM: {
                sensorMenuInfo.sensor = new LandsatTMSensor(this.applet);
                sensorMenuInfo.menu = this.landsatArchiveMenu;
                sensorMenuInfo.description = "Landsats 4 and 5 Thematic Mapper - 1982 to present";
                break;
            }
            case LANDSAT_4_5MSS: {
                sensorMenuInfo.sensor = new Landsat4_5MssSensor(this.applet);
                sensorMenuInfo.menu = this.landsatArchiveMenu;
                sensorMenuInfo.description = "Landsats 4 and 5 Multispectral Scanner - 1982 to 1992";
                break;
            }
            case LANDSAT_1_3MSS: {
                sensorMenuInfo.sensor = new Landsat1_3MssSensor(this.applet);
                sensorMenuInfo.menu = this.landsatArchiveMenu;
                sensorMenuInfo.description = "Landsats 1,2 and 3 Multispectral Scanner - 1972 to 1983";
                break;
            }
            case GLS2010: {
                sensorMenuInfo.sensor = new Gls2010Dataset(this.applet);
                sensorMenuInfo.menu = this.glsMenu;
                sensorMenuInfo.description = "Global Land Survey 2010; Landsat 7 ETM+ and Landsat 5 TM (2008-2011)";
                break;
            }
            case GLS2005: {
                sensorMenuInfo.sensor = new Gls2005Dataset(this.applet);
                sensorMenuInfo.menu = this.glsMenu;
                sensorMenuInfo.description = "Global Land Survey 2005; Landsat 7 ETM+ and Landsat 5 TM (2003-2008)";
                break;
            }
            case GLS2005_EO1: {
                sensorMenuInfo.sensor = new Gls2005EO1Dataset(this.applet);
                sensorMenuInfo.menu = this.glsMenu;
                sensorMenuInfo.description = "Global Land Survey 2005; Earth Observing One (2004-2008)";
                break;
            }
            case GLS2005_ANTARCTICA: {
                sensorMenuInfo = null;
                System.out.println("Sensor  " + sensorStatus + " NOT IMPLEMENTED YET");
                break;
            }
            case GLS2000: {
                sensorMenuInfo.sensor = new Gls2000Dataset(this.applet);
                sensorMenuInfo.menu = this.glsMenu;
                sensorMenuInfo.description = "Global Land Survey 2000; Landsat 7 ETM+ and Landsat 5 TM (1999-2003)";
                break;
            }
            case GLS1990: {
                sensorMenuInfo.sensor = new Gls1990Dataset(this.applet);
                sensorMenuInfo.menu = this.glsMenu;
                sensorMenuInfo.description = "Global Land Survey 1990; Landsat 4-5 TM (1987-1997)";
                break;
            }
            case GLS1975_4_5MSS: {
                sensorMenuInfo.sensor = new Gls1975Mss4_5Dataset(this.applet);
                sensorMenuInfo.menu = this.glsMenu;
                sensorMenuInfo.description = "Global Land Survey 1975; Landsat 4-5 MSS (1982-1987)";
                break;
            }
            case GLS1975_1_3MSS: {
                sensorMenuInfo.sensor = new Gls1975Mss1_3Dataset(this.applet);
                sensorMenuInfo.menu = this.glsMenu;
                sensorMenuInfo.description = "Global Land Survey 1975; Landsat 1-3 MSS (1972-1983)";
                break;
            }
            case MRLC_2001TC: {
                sensorMenuInfo.sensor = new Mrlc2001TCDataset(this.applet);
                sensorMenuInfo.menu = this.landsatMrlcMenu;
                sensorMenuInfo.description = "Multi-resolution Land Characterization - Terrain Corrected";
                break;
            }
            case MRLC_2001RA: {
                sensorMenuInfo.sensor = new Mrlc2001RADataset(this.applet);
                sensorMenuInfo.menu = this.landsatMrlcMenu;
                sensorMenuInfo.description = "Multi-resolution Land Characterization - Reflectance Adjusted";
                break;
            }
            case ORTHO_TM: {
                sensorMenuInfo.sensor = new OrthoTMDataset(this.applet, "TM (1987-1997)", "ESAT_TM", "http://eros.usgs.gov/#Find_Data/Products_and_Data_Available/Tri-Decadal_Global_Landsat_Orthorectified_Overview", true);
                sensorMenuInfo.menu = this.landsatLegacyMenu;
                sensorMenuInfo.description = "Tri-Decadal Global Landsat Orthorectified Thematic Mapper (1987-1997)";
                break;
            }
            case ORTHO_ETM_PLUS: {
                sensorMenuInfo.sensor = new OrthoETMDataset(this.applet, "ETM+ (1999-2003)", "ESAT_ETM_NOPAN", "http://eros.usgs.gov/#Find_Data/Products_and_Data_Available/Tri-Decadal_Global_Landsat_Orthorectified_Overview", true);
                sensorMenuInfo.menu = this.landsatLegacyMenu;
                sensorMenuInfo.description = "Tri-Decadal Global Landsat Orthorectified Enhanced Thematic Mapper Plus (1999-2003)";
                break;
            }
            case PANSHARP_ETM_PLUS: {
                sensorMenuInfo.sensor = new OrthoPanSharpETMDataset(this.applet);
                sensorMenuInfo.menu = this.landsatLegacyMenu;
                sensorMenuInfo.description = "Tri-Decadal Global Landsat Orthorectified Panchromatic Sharpened Enhanced Thematic Mapper Plus (1999-2003)";
                break;
            }
            case ORTHO1_3MSS: {
                sensorMenuInfo.sensor = new Ortho1_3MssDataset(this.applet, "MSS 1-3 (1972-1983)", "ORTHO_MSS_SCENE", "http://eros.usgs.gov/#Find_Data/Products_and_Data_Available/Tri-Decadal_Global_Landsat_Orthorectified_Overview", true);
                sensorMenuInfo.menu = this.landsatLegacyMenu;
                sensorMenuInfo.description = "Tri-Decadal Global Landsat Orthorectified Multispectral Scanner 1-3 (1972-1983)";
                break;
            }
            case ORTHO4_5MSS: {
                sensorMenuInfo.sensor = new Ortho4_5MssDataset(this.applet, "MSS 4-5 (1982-1987)", "ORTHO_MSS_SCENE", true);
                sensorMenuInfo.menu = this.landsatLegacyMenu;
                sensorMenuInfo.description = "Tri-Decadal Global Landsat Orthorectified Multispectral Scanner 4-5 (1982-1987)";
                break;
            }
            case SYSTEMATIC_L1G: {
                sensorMenuInfo.sensor = new SystematicL1GDataset(this.applet);
                sensorMenuInfo.menu = this.landsatLegacyMenu;
                sensorMenuInfo.description = "Landsat 7 Enhanced Thematic Mapper Plus Systematically Corrected (1999-May 2003)";
                break;
            }
            case ASTER_VNIR: {
                sensorMenuInfo.sensor = new AsterVNIRSensor(this.applet);
                sensorMenuInfo.menu = this.asterMenu;
                sensorMenuInfo.description = "Advanced Spaceborne Thermal Emission and Reflection Radiometer Level 1A Day (VNIR data shown)";
                break;
            }
            case ASTER_TIR: {
                sensorMenuInfo.sensor = new AsterTIRSensor(this.applet);
                sensorMenuInfo.menu = this.asterMenu;
                sensorMenuInfo.description = "Advanced Spaceborne Thermal Emission and Reflection Radiometer Level 1A Night (TIR data shown)";
                break;
            }
            case ASTER_VNIR_DATAPOOL: {
                sensorMenuInfo.sensor = new AsterVNIRDataPoolSensor(this.applet);
                sensorMenuInfo.menu = this.asterMenu;
                sensorMenuInfo.description = "Advanced Spaceborne Thermal Emission and Reflection Radiometer Level 1B US Day (VNIR data shown)";
                break;
            }
            case ASTER_TIR_DATAPOOL: {
                sensorMenuInfo.sensor = new AsterTIRDataPoolSensor(this.applet);
                sensorMenuInfo.menu = this.asterMenu;
                sensorMenuInfo.description = "Advanced Spaceborne Thermal Emission and Reflection Radiometer Level 1B US Night (TIR data shown)";
                break;
            }
            case MODIS_MOD09A1: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD09A1", "modis/mod09a1", "LPDAAC_MODIS", "MOD09A1", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Surface Reflectance 8-Day L3 Global 500m SIN Grid";
                break;
            }
            case MODIS_MOD09GA: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD09GA", "modis/mod09ga", "LPDAAC_MODIS", "MOD09GA", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Surface Reflectance Daily L2G Global 1km and 500m SIN Grid";
                break;
            }
            case MODIS_MOD09GQ: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD09GQ", "modis/mod09gq", "LPDAAC_MODIS", "MOD09GQ", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Surface Reflectance Daily L2G Global 250m SIN Grid";
                break;
            }
            case MODIS_MOD09Q1: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD09Q1", "modis/mod09q1", "LPDAAC_MODIS", "MOD09Q1", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Surface Reflectance Daily L2G Global 250m SIN Grid";
                break;
            }
            case MODIS_MOD11A1_DAY: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD11A1 Day", "modis/mod11a1_day", "LPDAAC_MODIS", "MOD11A1_DAY", true);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Surface Temperature/Emissivity Daily L3 Global 1km SIN Grid Day";
                break;
            }
            case MODIS_MOD11A1_NIGHT: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD11A1 Night", "modis/mod11a1_night", "LPDAAC_MODIS", "MOD11A1_NIGHT", true);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Surface Temperature/Emissivity Daily L3 Global 1km SIN Grid Night";
                break;
            }
            case MODIS_MOD11A2_DAY: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD11A2 Day", "modis/mod11a2_day", "LPDAAC_MODIS", "MOD11A2_DAY", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Surface Temperature/Emissivity 8-Day L3 Global 1km SIN Grid Day";
                break;
            }
            case MODIS_MOD11A2_NIGHT: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD11A2 Night", "modis/mod11a2_night", "LPDAAC_MODIS", "MOD11A2_NIGHT", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Surface Temperature/Emissivity 8-Day L3 Global 1km SIN Grid Night";
                break;
            }
            case MODIS_MOD11B1_DAY: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD11B1 Day", "modis/mod11b1_day", "LPDAAC_MODIS", "MOD11B1_DAY", true);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Surface Temperature/Emissivity Daily L3 Global 5km SIN Grid Day";
                break;
            }
            case MODIS_MOD11B1_NIGHT: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD11B1 Night", "modis/mod11b1_night", "LPDAAC_MODIS", "MOD11B1_NIGHT", true);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Surface Temperature/Emissivity Daily L3 Global 5km SIN Grid Night";
                break;
            }
            case MODIS_MOD13A1_EVI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD13A1 EVI", "modis/mod13a1_evi", "LPDAAC_MODIS", "MOD13A1_EVI", true);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Vegetation Indices 16-Day L3 Global 500m SIN Grid EVI";
                break;
            }
            case MODIS_MOD13A1_NDVI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD13A1 NDVI", "modis/mod13a1_ndvi", "LPDAAC_MODIS", "MOD13A1_NDVI", true);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Vegetation Indices 16-Day L3 Global 500m SIN Grid NDVI";
                break;
            }
            case MODIS_MOD13A2_EVI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD13A2 EVI", "modis/mod13a2_evi", "LPDAAC_MODIS", "MOD13A2_EVI", true);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Vegetation Indices 16-Day L3 Global 1km SIN Grid EVI";
                break;
            }
            case MODIS_MOD13A2_NDVI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD13A2 NDVI", "modis/mod13a2_ndvi", "LPDAAC_MODIS", "MOD13A2_NDVI", true);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Vegetation Indices 16-Day L3 Global 1km SIN Grid NDVI";
                break;
            }
            case MODIS_MOD13A3_EVI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD13A3 EVI", "modis/mod13a3_evi", "LPDAAC_MODIS", "MOD13A3_EVI", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Vegetation Indices Monthly L3 Global 1km SIN Grid EVI";
                break;
            }
            case MODIS_MOD13A3_NDVI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD13A3 NDVI", "modis/mod13a3_ndvi", "LPDAAC_MODIS", "MOD13A3_NDVI", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Vegetation Indices Monthly L3 Global 1km SIN Grid NDVI";
                break;
            }
            case MODIS_MOD13Q1_EVI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD13Q1 EVI", "modis/mod13q1_evi", "LPDAAC_MODIS", "MOD13Q1_EVI", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Vegetation Indices 16-Day L3 Global 250m SIN Grid EVI";
                break;
            }
            case MODIS_MOD13Q1_NDVI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD13Q1 NDVI", "modis/mod13q1_ndvi", "LPDAAC_MODIS", "MOD13Q1_NDVI", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Vegetation Indices 16-Day L3 Global 250m SIN Grid NDVI";
                break;
            }
            case MODIS_MOD14A1: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD14A1", "modis/mod14a1", "LPDAAC_MODIS", "MOD14A1", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Thermal Anomalies/Fire Daily L3 Global 1km SIN Grid";
                break;
            }
            case MODIS_MOD14A2: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD14A2", "modis/mod14a2", "LPDAAC_MODIS", "MOD14A2", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Thermal Anomalies/Fire 8-Day L3 Global 1km SIN Grid";
                break;
            }
            case MODIS_MOD15A2_FPAR: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD15A2 FPAR", "modis/mod15a2_fpar", "LPDAAC_MODIS", "MOD15A2_FPAR", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Leaf Area Index/FPAR 8-Day L4 Global 1km SIN Grid FPAR";
                break;
            }
            case MODIS_MOD15A2_LAI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD15A2 LAI", "modis/mod15a2_lai", "LPDAAC_MODIS", "MOD15A2_LAI", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Leaf Area Index/LAI 8-Day L4 Global 1km SIN Grid LAI";
                break;
            }
            case MODIS_MOD17A2_GPP: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD17A2 GPP", "modis/mod17a2_gpp", "LPDAAC_MODIS", "MOD17A2_GPP", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Gross Primary Productivity 8-Day L4 Global 1km SIN Grid GPP";
                break;
            }
            case MODIS_MOD17A2_NETPSN: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD17A2 Net Photosynthesis", "modis/mod17a2_netpsn", "LPDAAC_MODIS", "MOD17A2_NETPSN", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Gross Primary Productivity 8-Day L4 Global 1km SIN Grid Net Photosynthesis";
                break;
            }
            case MODIS_MOD43B1: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD43B1", "modis/mod43b1", "LPDAAC_MODIS", "MOD43B1", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra BRDF/Albedo Model-1 16-Day L3 Global 1km SIN Grid";
                break;
            }
            case MODIS_MOD43B3: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD43B3", "modis/mod43b3", "LPDAAC_MODIS", "MOD43B3", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Albedo 16-Day L3 Global 1km SIN Grid";
                break;
            }
            case MODIS_MOD43B4: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MOD43B4", "modis/mod43b4", "LPDAAC_MODIS", "MOD43B4", false);
                sensorMenuInfo.menu = this.terraMenu;
                sensorMenuInfo.description = "MODIS/Terra Nadir BRDF-Adjusted Reflectance 16-Day L3 Global 1km SIN Grid";
                break;
            }
            case MODIS_MYD09A1: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD09A1", "modis/myd09a1", "LPDAAC_MODIS", "MYD09A1", false);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Surface Reflectance 8-Day L3 Global 500m SIN Grid";
                break;
            }
            case MODIS_MYD09GA: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD09GA", "modis/myd09ga", "LPDAAC_MODIS", "MYD09GA", false);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Surface Reflectance Daily L2G Global 1km and 500m SIN Grid";
                break;
            }
            case MODIS_MYD09GQ: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD09GQ", "modis/myd09gq", "LPDAAC_MODIS", "MYD09GQ", false);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Surface Reflectance Daily L2G Global 250m SIN Grid";
                break;
            }
            case MODIS_MYD09Q1: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD09Q1", "modis/myd09q1", "LPDAAC_MODIS", "MYD09Q1", false);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Surface Reflectance 8-Day L3 Global 250m SIN Grid";
                break;
            }
            case MODIS_MYD11A1_DAY: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD11A1 Day", "modis/myd11a1_day", "LPDAAC_MODIS", "MYD11A1_DAY", true);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Surface Temperature/Emissivity Daily L3 Global 1km SIN Grid Day";
                break;
            }
            case MODIS_MYD11A1_NIGHT: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD11A1 Night", "modis/myd11a1_night", "LPDAAC_MODIS", "MYD11A1_NIGHT", true);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Surface Temperature/Emissivity Daily L3 Global 1km SIN Grid Night";
                break;
            }
            case MODIS_MYD11A2_DAY: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD11A2 Day", "modis/myd11a2_day", "LPDAAC_MODIS", "MYD11A2_DAY", false);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Surface Temperature/Emissivity 8-Day L3 Global 1km SIN Grid Day";
                break;
            }
            case MODIS_MYD11A2_NIGHT: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD11A2 Night", "modis/myd11a2_night", "LPDAAC_MODIS", "MYD11A2_NIGHT", false);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Surface Temperature/Emissivity 8-Day L3 Global 1km SIN Grid Night";
                break;
            }
            case MODIS_MYD11B1_DAY: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD11B1 Day", "modis/myd11b1_day", "LPDAAC_MODIS", "MYD11B1_DAY", true);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Surface Temperature/Emissivity Daily L3 Global 5km SIN Grid Day";
                break;
            }
            case MODIS_MYD11B1_NIGHT: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD11B1 Night", "modis/myd11b1_night", "LPDAAC_MODIS", "MYD11B1_NIGHT", true);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Surface Temperature/Emissivity Daily L3 Global 5km SIN Grid Night";
                break;
            }
            case MODIS_MYD13A1_EVI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD13A1 EVI", "modis/myd13a1_evi", "LPDAAC_MODIS", "MYD13A1_EVI", true);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Vegetation Indices 16-Day L3 Global 500m SIN Grid EVI";
                break;
            }
            case MODIS_MYD13A1_NDVI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD13A1 NDVI", "modis/myd13a1_ndvi", "LPDAAC_MODIS", "MYD13A1_NDVI", true);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Vegetation Indices 16-Day L3 Global 500m SIN Grid NDVI";
                break;
            }
            case MODIS_MYD13A2_EVI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD13A2 EVI", "modis/myd13a2_evi", "LPDAAC_MODIS", "MYD13A2_EVI", true);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Vegetation Indices 16-Day L3 Global 1km SIN Grid EVI";
                break;
            }
            case MODIS_MYD13A2_NDVI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD13A2 NDVI", "modis/myd13a2_ndvi", "LPDAAC_MODIS", "MYD13A2_NDVI", true);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Vegetation Indices 16-Day L3 Global 1km SIN Grid NDVI";
                break;
            }
            case MODIS_MYD13A3_EVI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD13A3 EVI", "modis/myd13a3_evi", "LPDAAC_MODIS", "MYD13A3_EVI", false);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Vegetation Indices Monthly L3 Global 1km SIN Grid EVI";
                break;
            }
            case MODIS_MYD13A3_NDVI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD13A3 NDVI", "modis/myd13a3_ndvi", "LPDAAC_MODIS", "MYD13A3_NDVI", false);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Vegetation Indices Monthly L3 Global 1km SIN Grid NDVI";
                break;
            }
            case MODIS_MYD13Q1_EVI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD13Q1 EVI", "modis/myd13q1_evi", "LPDAAC_MODIS", "MYD13Q1_EVI", false);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Vegetation Indices 16-Day L3 Global 250m SIN Grid EVI";
                break;
            }
            case MODIS_MYD13Q1_NDVI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD13Q1 NDVI", "modis/myd13q1_ndvi", "LPDAAC_MODIS", "MYD13Q1_NDVI", false);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Vegetation Indices 16-Day L3 Global 250m SIN Grid NDVI";
                break;
            }
            case MODIS_MYD14A1: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD14A1", "modis/myd14a1", "LPDAAC_MODIS", "MYD14A1", false);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Thermal Anomalies/Fire Daily L3 Global 1km SIN Grid";
                break;
            }
            case MODIS_MYD14A2: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD14A2", "modis/myd14a2", "LPDAAC_MODIS", "MYD14A2", false);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Thermal Anomalies/Fire 8-Day L3 Global 1km SIN Grid";
                break;
            }
            case MODIS_MYD15A2_FPAR: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD15A2 FPAR", "modis/myd15a2_fpar", "LPDAAC_MODIS", "MYD15A2_FPAR", false);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Leaf Area Index/FPAR 8-Day L4 Global 1km SIN Grid FPAR";
                break;
            }
            case MODIS_MYD15A2_LAI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD15A2 LAI", "modis/myd15a2_lai", "LPDAAC_MODIS", "MYD15A2_LAI", false);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Leaf Area Index/LAI 8-Day L4 Global 1km SIN Grid LAI";
                break;
            }
            case MODIS_MYD17A2_GPP: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD17A2 GPP", "modis/myd17a2_gpp", "LPDAAC_MODIS", "MYD17A2_GPP", false);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Gross Primary Productivity 8-Day L4 Global 1km SIN Grid GPP";
                break;
            }
            case MODIS_MYD17A2_NETPSN: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MYD17A2 Net Photosynthesis", "modis/myd17a2_netpsn", "LPDAAC_MODIS", "MYD17A2_NETPSN", false);
                sensorMenuInfo.menu = this.aquaMenu;
                sensorMenuInfo.description = "MODIS/Aqua Gross Primary Productivity 8-Day L4 Global 1km SIN Grid Net Photosynthesis";
                break;
            }
            case MODIS_MCD15A2_FPAR: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MCD15A2 FPAR", "modis/mcd15a2_fpar", "LPDAAC_MODIS", "MCD15A2_FPAR", false);
                sensorMenuInfo.menu = this.combinedAquaTerraMenu;
                sensorMenuInfo.description = "MODIS/Terra+Aqua Leaf Area Index/FPAR 8-Day L4 Global 1km SIN Grid";
                break;
            }
            case MODIS_MCD15A2_LAI: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MCD15A2 LAI", "modis/mcd15a2_lai", "LPDAAC_MODIS", "MCD15A2_LAI", false);
                sensorMenuInfo.menu = this.combinedAquaTerraMenu;
                sensorMenuInfo.description = "MODIS/Terra+Aqua Leaf Area Index/LAI 8-Day L4 Global 1km SIN Grid";
                break;
            }
            case MODIS_MCD43A1: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MCD43A1", "modis/mcd43a1", "LPDAAC_MODIS", "MCD43A1", false);
                sensorMenuInfo.menu = this.combinedAquaTerraMenu;
                sensorMenuInfo.description = "MODIS/Terra+Aqua BRDF/Albedo Model Parameters 16-Day L3 Global 500m SIN Grid";
                break;
            }
            case MODIS_MCD43A2: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MCD43A2", "modis/mcd43a2", "LPDAAC_MODIS", "MCD43A2", false);
                sensorMenuInfo.menu = this.combinedAquaTerraMenu;
                sensorMenuInfo.description = "MODIS/Terra+Aqua BDRF/Albedo 16-Day L3 Global 500m SIN Grid";
                break;
            }
            case MODIS_MCD43A3: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MCD43A3", "modis/mcd43a3", "LPDAAC_MODIS", "MCD43A3", false);
                sensorMenuInfo.menu = this.combinedAquaTerraMenu;
                sensorMenuInfo.description = "MODIS/Terra+Aqua Albedo 16-Day L3 Global 500m SIN Grid";
                break;
            }
            case MODIS_MCD43A4: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MCD43A4", "modis/mcd43a4", "LPDAAC_MODIS", "MCD43A4", false);
                sensorMenuInfo.menu = this.combinedAquaTerraMenu;
                sensorMenuInfo.description = "MODIS/Terra+Aqua Nadir BRDF-Adjusted Reflectance 16-Day L3 Global 500m SIN Grid";
                break;
            }
            case MODIS_MCD43B1: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MCD43B1", "modis/mcd43b1", "LPDAAC_MODIS", "MCD43B1", false);
                sensorMenuInfo.menu = this.combinedAquaTerraMenu;
                sensorMenuInfo.description = "MODIS/Terra+Aqua BRDF/Albedo Model-1 16-Day L3 Global 1km SIN Grid";
                break;
            }
            case MODIS_MCD43B2: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MCD43B2", "modis/mcd43b2", "LPDAAC_MODIS", "MCD43B2", false);
                sensorMenuInfo.menu = this.combinedAquaTerraMenu;
                sensorMenuInfo.description = "MODIS/Terra+Aqua BDRF/Albedo 16-Day L3 Global 1km SIN Grid";
                break;
            }
            case MODIS_MCD43B3: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MCD43B3", "modis/mcd43b3", "LPDAAC_MODIS", "MCD43B3", false);
                sensorMenuInfo.menu = this.combinedAquaTerraMenu;
                sensorMenuInfo.description = "MODIS/Terra+Aqua Albedo 16-Day L3 Global 1km SIN Grid";
                break;
            }
            case MODIS_MCD43B4: {
                sensorMenuInfo.sensor = new ModisSensor(this.applet, "MCD43B4", "modis/mcd43b4", "LPDAAC_MODIS", "MCD43B4", false);
                sensorMenuInfo.menu = this.combinedAquaTerraMenu;
                sensorMenuInfo.description = "MODIS/Terra+Aqua Nadir BRDF-Adjusted Reflectance 16-Day L3 Global 1km SIN Grid";
                break;
            }
            case EO1_ALI: {
                sensorMenuInfo.sensor = new EO1AliSensor(this.applet);
                sensorMenuInfo.menu = this.eo1Menu;
                sensorMenuInfo.description = "Earth Observing One - Advanced Land Imager";
                break;
            }
            case EO1_HYP: {
                sensorMenuInfo.sensor = new EO1HypSensor(this.applet);
                sensorMenuInfo.menu = this.eo1Menu;
                sensorMenuInfo.description = "Earth Observing One - Hyperion";
                break;
            }
            case TERRALOOK_ASTER_VNIR: {
                sensorMenuInfo.sensor = new TerraLookAsterVNIRSensor(this.applet);
                sensorMenuInfo.menu = this.terraLookMenu;
                sensorMenuInfo.description = "TerraLook Advanced Spaceborne Thermal Emission and Reflection Radiometer - visible and near infrared bands";
                break;
            }
            case TERRALOOK_GLS2005: {
                sensorMenuInfo.sensor = new Gls2005Dataset(this.applet, "TL GLS 2005 (2003-2008)", "TERRA_GLS2005", "http://terralook.cr.usgs.gov", true, false);
                sensorMenuInfo.menu = this.terraLookMenu;
                sensorMenuInfo.description = "TerraLook Global Land Survey 2005 Landsat 5 TM & 7 ETM+ (2003-2008)";
                break;
            }
            case TERRALOOK_GLS2000: {
                sensorMenuInfo.sensor = new Gls2000Dataset(this.applet, "TL GLS 2000 (1999-2003)", "TERRA_GLS2000", "http://terralook.cr.usgs.gov", true, false);
                sensorMenuInfo.menu = this.terraLookMenu;
                sensorMenuInfo.description = "TerraLook Global Land Survey 2000 Landsat 7 ETM+ (1999-2003)";
                break;
            }
            case TERRALOOK_GLS1990: {
                sensorMenuInfo.sensor = new Gls1990Dataset(this.applet, "TL GLS 1990 (1984-1997)", "TERRA_GLS1990", "http://terralook.cr.usgs.gov", true, false);
                sensorMenuInfo.menu = this.terraLookMenu;
                sensorMenuInfo.description = "TerraLook Global Land Survey 1990 Landsat 4 & 5 TM (1984-1997)";
                break;
            }
            case TERRALOOK_GLS1975_L4_5: {
                sensorMenuInfo.sensor = new Gls1975Mss4_5Dataset(this.applet, "TL GLS 1975 (1982-1987)", "TERRA_GLS1975", "http://terralook.cr.usgs.gov", true, false);
                sensorMenuInfo.menu = this.terraLookMenu;
                sensorMenuInfo.description = "TerraLook Global Land Survey 1975 Landsat 4-5 MSS (1982-1987)";
                break;
            }
            case TERRALOOK_GLS1975_L1_3: {
                sensorMenuInfo.sensor = new Gls1975Mss1_3Dataset(this.applet, "TL GLS 1975 (1972-1983)", "TERRA_GLS1975", "http://terralook.cr.usgs.gov", true, false);
                sensorMenuInfo.menu = this.terraLookMenu;
                sensorMenuInfo.description = "TerraLook Global Land Survey 1975 Landsat 1-3 MSS (1972-1983)";
                break;
            }
            case NAPP: {
                sensorMenuInfo.sensor = new NappDataset(this.applet);
                sensorMenuInfo.menu = this.aerialMenu;
                sensorMenuInfo.description = "National Aerial Photography Program";
                break;
            }
            case NHAP_BW: {
                sensorMenuInfo.sensor = new NhapDataset(this.applet, "B&W NHAP", "nhap/bw", 16);
                sensorMenuInfo.menu = this.aerialMenu;
                sensorMenuInfo.description = "Black and White National High Altitude Photography";
                break;
            }
            case NHAP_CIR: {
                sensorMenuInfo.sensor = new NhapDataset(this.applet, "CIR NHAP", "nhap/cir", 24);
                sensorMenuInfo.menu = this.aerialMenu;
                sensorMenuInfo.description = "Color Infrared National High Altitude Photography";
                break;
            }
            case NALC: {
                sensorMenuInfo.sensor = new NalcDataset(this.applet);
                sensorMenuInfo.menu = this.landsatLegacyMenu;
                sensorMenuInfo.description = "North American Landscape Characterization";
                break;
            }
            case ETM_MOSAIC: {
                sensorMenuInfo.sensor = new TriDecEtmMosaicDataset(this.applet);
                sensorMenuInfo.menu = this.landsatLegacyMenu;
                sensorMenuInfo.description = "Tri-Decadal Global Landsat Orthorectified Panchromatic Sharpened Enhanced Thematic Mapper Plus Mosaics(1999-2003)";
                break;
            }
            case TM_MOSAIC: {
                sensorMenuInfo.sensor = new TriDecTmMosaicDataset(this.applet);
                sensorMenuInfo.menu = this.landsatLegacyMenu;
                sensorMenuInfo.description = "Tri-Decadal Global Landsat Orthorectified Thematic Mapper Mosaics (1987-1997)";
                break;
            }
            case LANDSAT_ETM_COMBINED: {
                sensorMenuInfo = null;
                System.out.println("Sensor " + sensorStatus + " NOT IMPLEMENTED YET");
                break;
            }
            case LANDSAT_COMBINED: {
                Sensor[] array = new Sensor[4];
                int n = 0;
                for (int i = 0; i < this.sensors.length; ++i) {
                    final Sensor sensor = this.sensors[i];
                    if (sensor != null && (sensor.sensorName.startsWith("Landsat 4-5") || sensor.sensorName.startsWith("L7 SLC-on") || sensor.sensorName.startsWith("L7 SLC-off"))) {
                        array[n] = this.sensors[i];
                        ++n;
                    }
                }
                if (n < 4) {
                    final Sensor[] array2 = new Sensor[n];
                    for (int j = 0; j < n; ++j) {
                        array2[j] = array[j];
                    }
                    array = array2;
                }
                sensorMenuInfo.sensor = new LandsatCombined(this.applet, array);
                sensorMenuInfo.menu = this.landsatArchiveMenu;
                sensorMenuInfo.description = "Landsats 4 and 5 Multispectral Scanner and Thematic Mapper, and Landsat 7 Enhanced Thematic Mapper Plus - 1982 to present";
                break;
            }
            default: {
                sensorMenuInfo = null;
                System.out.println("No info for sensor " + sensorStatus);
                break;
            }
        }
        return sensorMenuInfo;
    }
    
    private class SensorMenuInfo
    {
        public Sensor sensor;
        public String description;
        public JMenu menu;
    }
}
