// 
// Decompiled by Procyon v0.5.30
// 

package weightapplet;

public class DailyParams
{
    private double calories;
    private double carbpercent;
    private double sodium;
    private double actparam;
    public boolean flag;
    private boolean ramped;
    
    public DailyParams() {
        this.flag = false;
        this.ramped = false;
    }
    
    public DailyParams(double calories, double carbpercent, double sodium, double actparam) {
        this.flag = false;
        this.ramped = false;
        if (calories < 0.0) {
            calories = 0.0;
        }
        if (carbpercent < 0.0) {
            carbpercent = 0.0;
        }
        if (carbpercent > 100.0) {
            carbpercent = 100.0;
        }
        if (sodium < 0.0) {
            sodium = 0.0;
        }
        if (actparam < 0.0) {
            actparam = 0.0;
        }
        this.calories = calories;
        this.carbpercent = carbpercent;
        this.sodium = sodium;
        this.actparam = actparam;
    }
    
    public DailyParams(final Baseline baseline) {
        this.flag = false;
        this.ramped = false;
        this.calories = baseline.getMaintCals();
        this.carbpercent = baseline.getCarbInPercent();
        this.sodium = baseline.getSodium();
        this.actparam = baseline.getActParam();
    }
    
    public DailyParams(final Intervention intervention, final Baseline baseline) {
        this.flag = false;
        this.ramped = false;
        this.calories = intervention.getcalories();
        this.carbpercent = intervention.getcarbinpercent();
        this.sodium = intervention.getsodium();
        this.actparam = intervention.getAct(baseline);
    }
    
    public double getCalories() {
        return this.calories;
    }
    
    public void setCalories(double calories) {
        if (calories < 0.0) {
            calories = 0.0;
        }
        this.calories = calories;
    }
    
    public double getSodium() {
        return this.sodium;
    }
    
    public void setSodium(double sodium) {
        if (sodium < 0.0) {
            sodium = 0.0;
        }
        this.sodium = sodium;
    }
    
    public double getCarbPercent() {
        return this.carbpercent;
    }
    
    public void setCarbPercent(final double carbpercent) {
        if (carbpercent >= 0.0 && carbpercent <= 100.0) {
            this.carbpercent = carbpercent;
        }
    }
    
    public double getCarbIntake() {
        return this.carbpercent / 100.0 * this.calories;
    }
    
    public double getActParam() {
        return this.actparam;
    }
    
    public void setActParam(double actparam) {
        if (actparam < 0.0) {
            actparam = 0.0;
        }
        this.actparam = actparam;
    }
    
    public boolean isramped() {
        return this.ramped;
    }
    
    public void setramped(final boolean ramped) {
        this.ramped = ramped;
    }
    
    public DailyParams makeCaloricCopy(final double n) {
        double n2 = this.getCalories() + n;
        if (n2 < 0.0) {
            n2 = 0.0;
        }
        return new DailyParams(n2, this.getCarbPercent(), this.getSodium(), this.getActParam());
    }
    
    public static DailyParams avg(final DailyParams dailyParams, final DailyParams dailyParams2) {
        return new DailyParams((dailyParams.getCalories() + dailyParams2.getCalories()) / 2.0, (dailyParams.getCarbPercent() + dailyParams2.getCarbPercent()) / 2.0, (dailyParams.getSodium() + dailyParams2.getSodium()) / 2.0, (dailyParams.getActParam() + dailyParams2.getActParam()) / 2.0);
    }
    
    public static DailyParams[] makeparamtrajectory(final Baseline baseline, final Intervention intervention, final Intervention intervention2, final double n) {
        baseline.print();
        intervention.print();
        intervention2.print();
        final double maintCals = baseline.getMaintCals();
        final double carbInPercent = baseline.getCarbInPercent();
        final double actParam = baseline.getActParam();
        final double sodium = baseline.getSodium();
        final DailyParams[] array = new DailyParams[(int)n];
        array[0] = new DailyParams(baseline);
        final boolean b = !intervention.ison() || (intervention.ison() && intervention.getday() > n && !intervention.rampon());
        final boolean b2 = !intervention2.ison() || (intervention2.ison() && intervention2.getday() > n && !intervention2.rampon());
        final boolean b3 = b && b2;
        final boolean b4 = intervention.getday() == intervention2.getday();
        final boolean b5 = (intervention.ison() && !intervention2.ison()) || (!intervention.ison() && intervention2.ison());
        final boolean b6 = intervention.ison() && intervention2.ison();
        if (b3) {
            for (int n2 = 1; n2 < n; ++n2) {
                array[n2] = new DailyParams(baseline);
            }
        }
        else if (b5 || (b6 && b4 && intervention2.rampon())) {
            Intervention intervention3;
            if (b5) {
                intervention3 = (intervention.ison() ? intervention : intervention2);
            }
            else {
                intervention3 = intervention2;
            }
            if (intervention3.rampon()) {
                for (int i = 1; i < intervention3.getday(); ++i) {
                    (array[i] = new DailyParams(maintCals + i / intervention3.getday() * (intervention3.getcalories() - maintCals), carbInPercent + i / intervention3.getcarbinpercent() * (intervention3.getcarbinpercent() - carbInPercent), sodium + i / intervention3.getday() * (intervention3.getsodium() - sodium), actParam + i / intervention3.getday() * (intervention3.getAct(baseline) - actParam))).setramped(true);
                }
                for (int getday = intervention3.getday(); getday < n; ++getday) {
                    array[getday] = new DailyParams(intervention3, baseline);
                }
            }
            else {
                for (int j = 1; j < intervention3.getday(); ++j) {
                    array[j] = new DailyParams(baseline);
                }
                for (int getday2 = intervention3.getday(); getday2 < n; ++getday2) {
                    array[getday2] = new DailyParams(intervention3, baseline);
                }
            }
        }
        else {
            final Intervention intervention4 = (intervention.getday() < intervention2.getday()) ? intervention : intervention2;
            final Intervention intervention5 = (intervention.getday() < intervention2.getday()) ? intervention2 : intervention;
            if (intervention4.rampon()) {
                for (int k = 1; k < intervention4.getday(); ++k) {
                    (array[k] = new DailyParams(maintCals + k / intervention4.getday() * (intervention4.getcalories() - maintCals), carbInPercent + k / intervention4.getcarbinpercent() * (intervention4.getcarbinpercent() - carbInPercent), sodium + k / intervention4.getday() * (intervention4.getsodium() - sodium), actParam + k / intervention4.getday() * (intervention4.getAct(baseline) - actParam))).setramped(true);
                }
            }
            else {
                for (int l = 1; l < intervention4.getday(); ++l) {
                    array[l] = new DailyParams(baseline);
                }
            }
            if (intervention5.rampon()) {
                for (int getday3 = intervention4.getday(); getday3 < intervention5.getday(); ++getday3) {
                    (array[getday3] = new DailyParams(intervention4.getcalories() + (getday3 - intervention4.getday()) / (intervention5.getday() - intervention4.getday()) * (intervention5.getcalories() - intervention4.getcalories()), intervention4.getcarbinpercent() + (getday3 - intervention4.getday()) / (intervention5.getday() - intervention4.getday()) * (intervention5.getcarbinpercent() - intervention4.getcarbinpercent()), intervention4.getsodium() + (getday3 - intervention4.getday()) / (intervention5.getday() - intervention4.getday()) * (intervention5.getsodium() - intervention4.getsodium()), intervention4.getAct(baseline) + (getday3 - intervention4.getday()) / (intervention5.getday() - intervention4.getday()) * (intervention5.getAct(baseline) - intervention4.getAct(baseline)))).setramped(true);
                }
            }
            else {
                for (int min = Math.min(intervention5.getday(), (int)n), getday4 = intervention4.getday(); getday4 < min; ++getday4) {
                    array[getday4] = new DailyParams(intervention4, baseline);
                }
            }
            if (n > intervention5.getday()) {
                for (int getday5 = intervention5.getday(); getday5 < n; ++getday5) {
                    array[getday5] = new DailyParams(intervention5, baseline);
                }
            }
        }
        return array;
    }
    
    public void print() {
        System.out.println("Calories=" + this.calories);
        System.out.println("CarbPercent=" + this.carbpercent);
        System.out.println("Sodium=" + this.sodium);
        System.out.println("ActParam=" + this.actparam);
    }
}
