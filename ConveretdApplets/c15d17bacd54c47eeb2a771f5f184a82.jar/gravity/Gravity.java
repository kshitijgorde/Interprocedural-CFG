// 
// Decompiled by Procyon v0.5.30
// 

package gravity;

import gravity.tools.StarDate;
import gravity.cosmos.ProbeUpdateData;
import gravity.cosmos.ProbeIncomingData;
import gravity.gameObjects.Probe;
import gravity.gameObjects.Planet;
import gravity.gameObjects.Target;
import gravity.gameObjects.UFO;
import gravity.gameObjects.GameObject;
import gravity.tools.Vector2D;
import gravity.missions.MissionBalance;
import gravity.missions.MissionScanPlanets;
import gravity.missions.MissionScanUFO;
import gravity.missions.MissionHitTarget;
import gravity.tools.AssertionException;
import gravity.tools.Assert;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Random;
import gravity.gameObjects.Ship;
import gravity.cosmos.Cosmos;
import gravity.missions.Mission;
import java.util.Observer;
import java.util.Observable;

public class Gravity extends Observable implements Observer, ILauncher
{
    private ConstantsLibrary _cl;
    private int _gameState;
    private Mission _mission;
    private Cosmos _galaxy;
    private Ship _player;
    private boolean _actualInfty;
    private Random _rnd;
    private long _seed;
    private long _lastSeed;
    private boolean _getActualSeed;
    private Vector _lastAngles;
    private Enumeration _lastAngle;
    private double _actualAngle;
    
    public Gravity(final ConstantsLibrary cl) throws AssertionException {
        this._gameState = -1;
        this._actualInfty = false;
        this._rnd = new Random();
        this._getActualSeed = true;
        this._lastAngles = new Vector(48);
        this._lastAngle = this._lastAngles.elements();
        Assert.assert(cl != null);
        this._cl = cl;
        this.newGame();
    }
    
    public void newGame() {
        this.newGame((long)(Math.random() * 1000000.0) * 100L);
    }
    
    public boolean isNewGame(final long n) {
        return n != this._seed;
    }
    
    public void newGame(final long seed) {
        if (!this.isNewGame(seed)) {
            this.restart();
        }
        else {
            this._lastSeed = this._seed;
            this._seed = seed;
            this._rnd.setSeed(this._seed);
            this.clearLastAngles();
            this.initGame();
        }
    }
    
    public void restart() {
        this._rnd.setSeed(this._seed);
        this.initGame();
    }
    
    private synchronized void initGame() {
        this._gameState = -1;
        this._getActualSeed = true;
        this.removeCosmos();
        this.createGameConstants();
        this.selectMission();
        try {
            this._galaxy = new Cosmos(this._cl);
        }
        catch (AssertionException ex) {}
        this._galaxy.addObserver(this);
        this.createShip();
        this.createTargetOrUFO();
        this.createPlanets();
        this._gameState = 0;
        this.sendMessage("Welcome aboard D.S.V. Gravity!" + " - " + this._mission.getDescription() + "...");
        this._player.setAngleIndicatorVisible(true);
        this._actualInfty = this._cl.ISINFTYMODE;
    }
    
    private void createGameConstants() {
        this._cl.NUMBEROFPLANETS = (int)(this._rnd.nextDouble() * (8.0 - 1.0) + 2.0);
        this._cl.SHIPSIZE = 3.0 + 2.5 * this._rnd.nextDouble();
        this._cl.UFOSIZE = 3.0 + 2.5 * this._rnd.nextDouble();
        this._cl.TARGETSIZE = 2.5 + 3.0 * this._rnd.nextDouble();
        this._cl.GRAVCONSTANT = 22.0 + 14.0 * this._rnd.nextDouble();
    }
    
    private void selectMission() {
        switch ((int)(this._rnd.nextDouble() * 13.0 + 1.0)) {
            case 1:
            case 2:
            case 3: {
                this._mission = new MissionHitTarget();
                break;
            }
            case 4:
            case 5:
            case 6: {
                this._mission = new MissionScanUFO();
                break;
            }
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12: {
                this._mission = new MissionScanPlanets(Math.min((int)(this._rnd.nextDouble() * (this._cl.NUMBEROFPLANETS * 0.5 - 1.0) + 2.5), this._cl.NUMBEROFPLANETS));
                break;
            }
            case 13: {
                final int n = (int)(1500.0 + this._rnd.nextDouble() * 2500.0);
                this._cl.NUMBEROFPLANETS = (int)(this._rnd.nextDouble() * (8.0 / 2.0) + 8.0 / 2.0 + 1.0);
                this._mission = new MissionBalance(n);
                break;
            }
            default: {
                this._mission = new MissionHitTarget();
                break;
            }
        }
        this._cl.PROBEINITIALSPEED = 10.0 + 5.0 * this._rnd.nextDouble() + this._cl.NUMBEROFPLANETS / 2;
        this._cl.MAXPROBENUMBER = (int)(7.0 + 15.0 * this._rnd.nextDouble() + this._mission.getMinPlanetsScannedNr() * 3 + this._mission.getMinTicksNr() / 200 + this._cl.NUMBEROFPLANETS / 2);
    }
    
    private void createShip() {
        final Vector2D vector2D = new Vector2D((this._cl.GRAVITYDIMENSION.x - 3.0 * this._cl.SHIPSIZE) * this._rnd.nextDouble() + this._cl.SHIPSIZE * 3.0 / 2.0, (this._cl.GRAVITYDIMENSION.y - 3.0 * this._cl.SHIPSIZE) * this._rnd.nextDouble() + this._cl.SHIPSIZE * 3.0 / 2.0);
        try {
            (this._player = new Ship(vector2D, this._cl.SHIPSIZE, this._cl.MAXPROBENUMBER)).setProbeInitialSpeed(this._cl.PROBEINITIALSPEED);
            this._player.setColor(this._cl.SHIPCOLOR);
            this._player.getAngleIndicator().setColor(this._cl.SHIPCOLOR);
            this._galaxy.add(this._player);
        }
        catch (AssertionException ex) {}
    }
    
    private void createTargetOrUFO() {
        if (this._mission.getNeedUFO()) {
            this.createUFO();
        }
        if (this._mission.getNeedTarget()) {
            this.createTarget();
        }
        if (!this._mission.getNeedTarget() && !this._mission.getNeedUFO() && this._rnd.nextDouble() > 0.7) {
            if (this._rnd.nextDouble() > 0.5) {
                this.createUFO();
            }
            else {
                this.createTarget();
            }
        }
    }
    
    private void createUFO() {
        try {
            Vector2D vector2D;
            do {
                vector2D = new Vector2D((this._cl.GRAVITYDIMENSION.x - 3.0 * this._cl.UFOSIZE) * this._rnd.nextDouble() + this._cl.UFOSIZE * 3.0 / 2.0, (this._cl.GRAVITYDIMENSION.y - 3.0 * this._cl.UFOSIZE) * this._rnd.nextDouble() + this._cl.UFOSIZE * 3.0 / 2.0);
            } while (this._player.distance(new GameObject(vector2D)) < Math.sqrt(Math.pow(this._cl.GRAVITYDIMENSION.x, 2.0) + Math.pow(this._cl.GRAVITYDIMENSION.y, 2.0)) / 3.0);
            final UFO ufo = new UFO(vector2D, this._cl.UFOSIZE);
            ufo.setColor(this._cl.UFOCOLOR);
            this._galaxy.add(ufo);
        }
        catch (AssertionException ex) {}
    }
    
    private void createTarget() {
        try {
            Vector2D vector2D;
            do {
                vector2D = new Vector2D((this._cl.GRAVITYDIMENSION.x - 3.0 * this._cl.UFOSIZE) * this._rnd.nextDouble() + this._cl.UFOSIZE * 3.0 / 2.0, (this._cl.GRAVITYDIMENSION.y - 3.0 * this._cl.UFOSIZE) * this._rnd.nextDouble() + this._cl.TARGETSIZE * 3.0 / 2.0);
            } while (this._player.distance(new GameObject(vector2D)) < Math.sqrt(Math.pow(this._cl.GRAVITYDIMENSION.x, 2.0) + Math.pow(this._cl.GRAVITYDIMENSION.y, 2.0)) / 3.0);
            final Target target = new Target(vector2D, this._cl.TARGETSIZE);
            target.setColor(this._cl.TARGETCOLOR);
            this._galaxy.add(target);
        }
        catch (AssertionException ex) {}
    }
    
    private void createPlanets() {
        for (int i = 0; i < this._cl.NUMBEROFPLANETS; ++i) {
            Label_0010: {
                break Label_0010;
                try {
                    Planet planet;
                    double n;
                    do {
                        final Vector2D vector2D = new Vector2D((this._cl.GRAVITYDIMENSION.x - 17.5) * this._rnd.nextDouble() + 17.5 / 2.0, (this._cl.GRAVITYDIMENSION.y - 17.5) * this._rnd.nextDouble() + 17.5 / 2.0);
                        if (this._mission instanceof MissionBalance) {
                            n = this._rnd.nextDouble() * (7.0 / 2.0 - 1.0) + 2.0;
                        }
                        else {
                            n = this._rnd.nextDouble() * (7.0 - 2.0) + 2.0;
                        }
                        planet = new Planet(vector2D, n);
                    } while (this._galaxy.objectTooNear(planet));
                    if (this._mission.getDrawAtmospheres()) {
                        planet.setAtmosphereSize(this._rnd.nextDouble() * (n * 0.5 - 1.7) + 1.7);
                        planet.setAtmosphereColors(this._cl.ATMOSPHERECOLOR, this._cl.SCANNINGATMOSPHERECOLOR);
                    }
                    planet.setColor(this._cl.PLANETCOLORS[(int)(Math.random() * this._cl.PLANETCOLORS.length)]);
                    this._galaxy.add(planet);
                    if (n < 7.0 / 2.0) {
                        final ConstantsLibrary cl = this._cl;
                        cl.PROBEINITIALSPEED -= 0.4;
                    }
                }
                catch (AssertionException ex) {}
            }
        }
    }
    
    public void refresh() {
        this._galaxy.refresh();
    }
    
    public Cosmos getCosmos() {
        return this._galaxy;
    }
    
    public Ship getPlayer() {
        return this._player;
    }
    
    public String getRandomSeed(final boolean b) {
        if (!b) {
            this._getActualSeed = true;
        }
        String s = new Long(this._seed).toString();
        if (!this._getActualSeed) {
            s = new Long(this._lastSeed).toString();
        }
        final boolean startsWith = s.startsWith("-");
        if (startsWith) {
            s = s.substring(1);
        }
        while (s.length() <= 5) {
            s = "0" + s;
        }
        String s2;
        for (s2 = s.substring(0, s.length() - 5) + "." + s.substring(s.length() - 5); s2.endsWith("0"); s2 = s2.substring(0, s2.length() - 1)) {}
        if (s2.endsWith(".")) {
            s2 = s2.substring(0, s2.length() - 1);
        }
        if (startsWith) {
            s2 = "-" + s2;
        }
        if (!this._getActualSeed) {
            s2 += "'";
        }
        this._getActualSeed = !this._getActualSeed;
        return s2;
    }
    
    public String getLastAngle() {
        double doubleValue = 0.0;
        if (!this._lastAngle.hasMoreElements()) {
            this._lastAngle = this._lastAngles.elements();
        }
        if (this._lastAngle.hasMoreElements()) {
            try {
                doubleValue = this._lastAngle.nextElement();
            }
            catch (Exception ex) {}
        }
        return new String("" + doubleValue);
    }
    
    private void clearLastAngles() {
        this._lastAngles.removeAllElements();
        this._lastAngle = this._lastAngles.elements();
    }
    
    public void setAngle(final double n) {
        this._player.setAngle(n);
        this._actualAngle = n;
        if (this._player.isAngleIndicatorVisible()) {
            this.setChanged();
            this.notifyObservers();
        }
    }
    
    public void launch() {
        if (this.getState() != 0 && this.getState() != 1) {
            return;
        }
        if (this.getState() == 0) {
            this.clearLastAngles();
        }
        this._gameState = 1;
        this._player.setAngleIndicatorVisible(false);
        try {
            if (!this._actualInfty) {
                this._galaxy.runProbe(this._player.launch(this._mission));
            }
            else {
                this._galaxy.runProbe(new Probe(this._player.getCoord(), this._player.getAngleVector(), this._mission));
            }
            this._lastAngles.addElement(new Double(this._actualAngle));
        }
        catch (AssertionException ex) {
            this._gameState = 2;
            this.sendMessage("");
        }
    }
    
    public void abort() {
        this._galaxy.killProbes();
    }
    
    public void reset() {
        this.newGame();
    }
    
    public void setReady() {
    }
    
    public void doPanic() {
        int n = 0;
        while (((!this._actualInfty && this._player.getNumberOfProbes() > 1) || (this._actualInfty && n++ < 12)) && (this.getState() == 0 || this.getState() == 1)) {
            this._player.setAngleIndicatorVisible(false);
            this.setAngle(Math.floor((this._rnd.nextFloat() * 360.0f - 180.0f) * 100000.0) / 100000.0);
            this.launch();
        }
    }
    
    public int getState() {
        return this._gameState;
    }
    
    public Mission getMission() {
        return this._mission;
    }
    
    public void shutDown() {
        this.abort();
        this._gameState = 2;
        this._player.setAngleIndicatorVisible(false);
        this.sendMessage("Game aborted... see you soon back aboard D.S.V. Gravity!");
    }
    
    public void setInfty(final boolean isinftymode) {
        this._cl.ISINFTYMODE = isinftymode;
        final String string = this._mission.getDescription() + " | -> ";
        String s;
        if (this._cl.ISINFTYMODE) {
            s = string + "Infty-mode enabled...";
        }
        else if (this._player.getNumberOfProbes() == 1) {
            s = string + "1 probe left...";
        }
        else {
            s = string + this._player.getNumberOfProbes() + " probes left...";
        }
        if (this.getState() == 0) {
            this._actualInfty = this._cl.ISINFTYMODE;
            this.sendMessage(s);
        }
    }
    
    private void sendMessage(String s) {
        if (s.equals("")) {
            s = "Unexpected error... contact zeniko@gmx.ch for bug report & fix.";
        }
        final GravityUpdateData gravityUpdateData = new GravityUpdateData(s);
        this.setChanged();
        this.notifyObservers(gravityUpdateData);
    }
    
    private void removeCosmos() {
        if (this._galaxy != null) {
            this._galaxy.killProbes();
            this._galaxy.deleteObserver(this);
        }
    }
    
    protected void finalize() {
        this.removeCosmos();
    }
    
    public void update(final Observable observable, final Object o) {
        if (this.getState() == 2 || this.getState() == -1) {
            return;
        }
        if (o instanceof ProbeIncomingData && !(o instanceof ProbeUpdateData)) {
            final String incomingData = this._mission.getIncomingData(((ProbeIncomingData)o).nrOfScannedPlanets, ((ProbeIncomingData)o).ticks, ((ProbeIncomingData)o).targetScanned);
            if (incomingData != null && !incomingData.equals("")) {
                this.sendMessage(incomingData);
            }
        }
        if (!(o instanceof ProbeUpdateData)) {
            return;
        }
        String s = new String();
        boolean b = true;
        boolean b2 = false;
        final GameObject gameObject = ((ProbeUpdateData)o).gameObject;
        if (gameObject == null) {
            s = "Probe escaped out of system";
            b &= this._mission.getEscapeProbe();
        }
        if (gameObject instanceof Probe) {
            s = "Probe aborted";
        }
        if (gameObject instanceof Planet) {
            s = "Probe hit a planet";
        }
        if (gameObject == this._player) {
            if (!this._actualInfty) {
                this._player.changeIntegrity(0.25);
            }
            if (this._player.getIntegrity() <= 0.0) {
                this._galaxy.remove(this._player);
                b2 = true;
                s = "Stardate " + (int)(StarDate.starDate() * 100.0) / 100.0 + ": D.S.V. Gravity has been reported missing - Game Over!";
            }
            else if (this._player.getIntegrity() < 0.5) {
                s = "Gravity suffered heavy damages";
            }
            else {
                s = "Probe damaged Gravity";
            }
        }
        if (gameObject instanceof UFO) {
            if (!this._actualInfty) {
                ((UFO)gameObject).changeIntegrity(0.5);
            }
            if (((UFO)gameObject).getIntegrity() <= 0.0) {
                this._galaxy.remove(gameObject);
                b2 = true;
                s = "You chased the aliens away - Game Over!";
            }
            else {
                s = "You are annoying the aliens";
            }
        }
        if (gameObject instanceof Target) {
            if (!this._actualInfty) {
                ((Target)gameObject).changeIntegrity(0.5);
            }
            if (((Target)gameObject).getIntegrity() <= 0.0) {
                this._galaxy.remove(gameObject);
                s = "Target destroyed";
            }
            else {
                s = "You hit the target";
            }
        }
        boolean b3 = b & ((ProbeUpdateData)o).nrOfScannedPlanets >= this._mission.getMinPlanetsScannedNr() & ((ProbeUpdateData)o).ticks >= this._mission.getMinTicksNr();
        if (this._mission.getHitTarget()) {
            b3 &= (gameObject instanceof Target || gameObject instanceof UFO);
        }
        if (this._mission.getScanTarget()) {
            b3 &= (((ProbeUpdateData)o).targetScanned && !(gameObject instanceof UFO) && !(gameObject instanceof Target));
        }
        if (this._player.getNumberOfProbes() < 1 && !b3) {
            s = "No probes left... Game Over!";
            b2 = true;
        }
        if (this._actualInfty) {
            b3 = (b2 = false);
        }
        if (b2) {
            this._gameState = 2;
        }
        else if (b3) {
            this._gameState = -1;
            s = this._mission.getSuccessMessage(this._cl.MAXPROBENUMBER - this._player.getNumberOfProbes(), ((ProbeIncomingData)o).nrOfScannedPlanets, ((ProbeIncomingData)o).ticks);
            if (this._mission instanceof MissionScanPlanets && ((ProbeIncomingData)o).nrOfScannedPlanets == this._cl.NUMBEROFPLANETS && this._cl.NUMBEROFPLANETS > 3) {
                s = "You scanned all " + this._cl.NUMBEROFPLANETS + " planets. Excellent work!!!";
            }
        }
        else {
            if (!this._galaxy.isAProbeRunning()) {
                this._gameState = 0;
            }
            final String string = this._mission.getDescription() + " | " + s + this._mission.getFailureAdditional(((ProbeIncomingData)o).nrOfScannedPlanets, ((ProbeIncomingData)o).ticks) + " -> ";
            if (!this._cl.ISINFTYMODE) {
                if (this._player.getNumberOfProbes() == 1) {
                    s = string + "1 probe left...";
                }
                else {
                    s = string + this._player.getNumberOfProbes() + " probes left...";
                }
            }
            else {
                s = string + "Infty-mode enabled...";
            }
        }
        this._player.setAngleIndicatorVisible(this.getState() == 0);
        if (b2 || b3) {
            this.removeCosmos();
        }
        if (!this._galaxy.isAProbeRunning()) {
            this._actualInfty = this._cl.ISINFTYMODE;
        }
        this.sendMessage(s);
    }
}
