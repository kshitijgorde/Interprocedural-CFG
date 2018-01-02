// 
// Decompiled by Procyon v0.5.30
// 

package weightapplet;

public class Intervention
{
    private int day;
    private double calories;
    private double carbinpercent;
    private double PAL;
    private double sodium;
    private boolean on;
    private boolean rampon;
    private double actchangepercent;
    private boolean isdetailed;
    private String title;
    
    public static Intervention forgoal(final Baseline baseline, final double n, final double n2, final double n3, final double n4, final double n5) throws Exception {
        baseline.getWeight_kgs();
        final Intervention intervention = new Intervention();
        intervention.setTitle("Goal Intervention");
        intervention.setday(1);
        intervention.setcalories(n4);
        intervention.setactchangepercent(n3);
        intervention.setcarbinpercent(baseline.getCarbInPercent());
        intervention.setproportionalsodium(baseline);
        if (baseline.getWeight_kgs() == n && n3 == 0.0) {
            intervention.setcalories(baseline.getMaintCals());
            intervention.setproportionalsodium(baseline);
        }
        else {
            final BodyModel projectFromBaseline = BodyModel.projectFromBaseline(baseline, intervention, n2);
            System.out.println("Starvation test complete: " + projectFromBaseline.getFat() + "," + projectFromBaseline.getLean());
            double weight = projectFromBaseline.getWeight(baseline);
            if (weight < 0.0) {
                weight = 0.0;
            }
            final double abs = Math.abs(weight - n);
            if (abs < n5 || n <= weight) {
                System.out.println("PROBLEM in calsforgoal");
                System.out.println("    error is " + abs);
                System.out.println("    starvwt is" + weight);
                System.out.println("    starv[0] is" + projectFromBaseline.getFat());
                System.out.println("    starv[1] is" + projectFromBaseline.getLean());
                System.out.println("    starv[2] is" + projectFromBaseline.getDecw());
                System.out.println("    goalwt is" + n);
                System.out.println("    mincals is " + n4);
                System.out.println("    goalwt is " + n);
                System.out.println("    goaltime is " + n2);
                System.out.println("    eps is " + n5);
                baseline.print();
                intervention.print();
                intervention.setcalories(0.0);
                throw new Exception();
            }
            double n6 = n4;
            double n7 = 200.0;
            System.out.println("Entering loop");
            System.out.println("first calstep in cals for goal =" + n7);
            int n8 = 0;
            double abs2;
            double n9;
            do {
                ++n8;
                n9 = n6;
                n6 += n7;
                intervention.setcalories(n6);
                intervention.setproportionalsodium(baseline);
                final BodyModel projectFromBaseline2 = BodyModel.projectFromBaseline(baseline, intervention, n2);
                final double weight2 = projectFromBaseline2.getWeight(baseline);
                if (weight2 < 0.0) {
                    System.out.println("NEGATIVE testwt");
                    baseline.print();
                    intervention.print();
                }
                abs2 = Math.abs(n - weight2);
                if (Math.IEEEremainder(n8, 100.0) == 0.0) {
                    System.out.println("Loop report " + n8);
                    System.out.println("    error=" + abs2);
                    System.out.println("    bc=" + projectFromBaseline2.getFat() + "," + projectFromBaseline2.getLean());
                    System.out.println("    testwt=" + weight2);
                    System.out.println("    calstep=" + n7);
                    System.out.println("    holdcals=" + n9);
                }
                if (abs2 > n5 && weight2 > n) {
                    n7 /= 2.0;
                    n6 = n9;
                }
            } while (abs2 > n5);
            System.out.printf("Exiting loop after %d iterations, result is %f, error=%f, calstep=%f", n8, n9, abs2, n7);
        }
        return intervention;
    }
    
    public Intervention(final int n, final double n2, final double n3, final double n4, final double n5) {
        this.day = 100;
        this.calories = 2200.0;
        this.carbinpercent = 50.0;
        this.PAL = 1.5;
        this.sodium = 4000.0;
        this.on = true;
        this.rampon = false;
        this.actchangepercent = 0.0;
        this.isdetailed = false;
        this.setday(n);
        this.setcalories(n2);
        this.setcarbinpercent(n3);
        this.setactchangepercent(n4);
        this.setsodium(n5);
    }
    
    public Intervention() {
        this.day = 100;
        this.calories = 2200.0;
        this.carbinpercent = 50.0;
        this.PAL = 1.5;
        this.sodium = 4000.0;
        this.on = true;
        this.rampon = false;
        this.actchangepercent = 0.0;
        this.isdetailed = false;
    }
    
    public void seton(final boolean on) {
        this.on = on;
    }
    
    public boolean ison() {
        return this.on;
    }
    
    public boolean rampon() {
        return this.rampon;
    }
    
    public int getday() {
        return this.day;
    }
    
    public double getcalories() {
        return this.calories;
    }
    
    public double getcarbinpercent() {
        return this.carbinpercent;
    }
    
    public double getPAL() {
        return this.PAL;
    }
    
    public double getPAL_Act(final Baseline baseline) {
        return baseline.getActParam() * (this.PAL - 1.0) / (baseline.getPAL() - 1.0);
    }
    
    public double getAct(final Baseline baseline) {
        return baseline.getActParam() * (1.0 + this.actchangepercent / 100.0);
    }
    
    public double getactchangepercent() {
        return this.actchangepercent;
    }
    
    public double getsodium() {
        return this.sodium;
    }
    
    public void setday(final int day) {
        if (day > 0) {
            this.day = day;
        }
    }
    
    public void setrampon(final boolean rampon) {
        this.rampon = rampon;
    }
    
    public void setcalories(final double calories) {
        if (calories >= 0.0) {
            this.calories = calories;
        }
    }
    
    public void setcarbinpercent(final double carbinpercent) {
        if (carbinpercent >= 0.0 && carbinpercent <= 100.0) {
            this.carbinpercent = carbinpercent;
        }
    }
    
    public void setPAL(final double pal) {
        if (pal >= 1.0 && pal <= 3.0) {
            this.PAL = pal;
        }
    }
    
    public void setactchangepercent(final double actchangepercent) {
        if (actchangepercent >= -100.0) {
            this.actchangepercent = actchangepercent;
        }
    }
    
    public void setsodium(final double sodium) {
        if (sodium >= 0.0 && sodium <= 50000.0) {
            this.sodium = sodium;
        }
    }
    
    public void setproportionalsodium(final Baseline baseline) {
        this.sodium = baseline.getSodium() * this.calories / baseline.getMaintCals();
    }
    
    public void setdetailed(final boolean isdetailed) {
        this.isdetailed = isdetailed;
    }
    
    public boolean isdetailed() {
        return this.isdetailed;
    }
    
    public void print() {
        System.out.println(((this.title == null) ? "intervention" : this.title) + " report:");
        System.out.println("    Day=" + this.day);
        System.out.println("    Energy Intake (kcal/day)=" + this.calories);
        System.out.println("    Carb Percent=" + this.carbinpercent);
        System.out.println("    Percent Activity Change=" + this.actchangepercent);
        System.out.println("    Sodium (mg/day) =" + this.sodium);
        System.out.println("    on=" + this.on);
        System.out.println("    rampon=" + this.rampon);
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return this.title;
    }
}
