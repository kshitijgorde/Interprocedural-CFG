// 
// Decompiled by Procyon v0.5.30
// 

package weightapplet;

public class BodyModel
{
    double fat;
    double lean;
    double glyc;
    double decw;
    double therm;
    public static int[] RK4wt;
    
    public BodyModel() {
    }
    
    public BodyModel(final double fat, final double lean, final double glyc, final double decw, final double therm) {
        this.fat = fat;
        this.lean = lean;
        this.glyc = glyc;
        this.decw = decw;
        this.therm = therm;
    }
    
    public BodyModel(final Baseline baseline) {
        this.fat = baseline.getFatWt();
        this.lean = baseline.getLeanWt();
        this.glyc = baseline.getGlycogen();
        this.decw = baseline.getdECW();
        this.therm = baseline.gettherm();
    }
    
    public static BodyModel projectFromBaseline(final Baseline baseline, final DailyParams dailyParams, final double n) {
        BodyModel bodyModel = new BodyModel(baseline);
        for (int i = 0; i < (int)n; ++i) {
            final BodyModel rungeKatta = RungeKatta(bodyModel, baseline, dailyParams);
            if (i == 1) {}
            bodyModel = rungeKatta;
        }
        return bodyModel;
    }
    
    public static BodyModel projectFromBaseline(final Baseline baseline, final Intervention intervention, final double n) {
        return projectFromBaseline(baseline, new DailyParams(intervention, baseline), n);
    }
    
    public static void main(final String[] array) {
        final Baseline baseline = new Baseline();
        baseline.setAge(49.0);
        baseline.setcalculatedBfp(false);
        baseline.setBfp(18.0);
        baseline.setCarbInPercent(50.0);
        baseline.setPAL(1.5);
        baseline.setWeight_kgs(100.0);
        baseline.setMale(false);
        baseline.setHeight(157.0);
        final BodyModel bodyModel = new BodyModel(baseline);
        final DailyParams dailyParams = new DailyParams(baseline);
        final DailyParams dailyParams2 = new DailyParams(1000.26, 50.0, baseline.proportionalSodium(1000.26), baseline.getActParam());
        final BodyModel rungeKatta = RungeKatta(bodyModel, baseline, dailyParams, dailyParams2);
        rungeKatta.print();
        rungeKatta.printdetailed(baseline, dailyParams2);
        System.out.println("\n\nSimple test\n\n");
        BodyModel rungeKatta2 = RungeKatta(new BodyModel(baseline), baseline, dailyParams2);
        rungeKatta2.print();
        rungeKatta2.printdetailed(baseline, dailyParams2);
        System.out.println("\n\nLong test\n\n");
        for (int i = 0; i < 200; ++i) {
            final BodyModel rungeKatta3 = RungeKatta(rungeKatta2, baseline, dailyParams2);
            System.out.printf("Fat at step %d is %f\n", i, rungeKatta3.getFat());
            rungeKatta2 = rungeKatta3;
        }
    }
    
    public double getFat() {
        return this.fat;
    }
    
    public double getLean() {
        return this.lean;
    }
    
    public double getGlyc() {
        return this.glyc;
    }
    
    public double getDecw() {
        return this.decw;
    }
    
    public double getTherm() {
        return this.therm;
    }
    
    public double getWeight(final Baseline baseline) {
        return this.getFat() + this.getLean() + baseline.getgH2O(this.glyc) + this.getDecw();
    }
    
    public double getapproxWeight() {
        return this.getFat() + this.getLean() + this.getDecw();
    }
    
    public double getFatFree(final Baseline baseline) {
        return this.getWeight(baseline) - this.getFat();
    }
    
    public double getFatPercent(final Baseline baseline) {
        return this.getFat() / this.getWeight(baseline) * 100.0;
    }
    
    public double getBMI(final Baseline baseline) {
        return baseline.getnewBMI(this.getWeight(baseline));
    }
    
    public BodyChange dt(final Baseline baseline, final DailyParams dailyParams) {
        return new BodyChange(this.dfdt(baseline, dailyParams), this.dldt(baseline, dailyParams), this.dgdt(baseline, dailyParams), this.dDecwdt(baseline, dailyParams), this.dthermdt(baseline, dailyParams));
    }
    
    public static BodyModel RungeKatta(final BodyModel bodyModel, final Baseline baseline, final DailyParams dailyParams, final DailyParams dailyParams2) {
        final DailyParams dailyParams3 = dailyParams2.isramped() ? dailyParams2 : DailyParams.avg(dailyParams, dailyParams2);
        final BodyChange dt = bodyModel.dt(baseline, dailyParams);
        final BodyChange dt2 = bodyModel.addchange(dt, 0.5).dt(baseline, dailyParams3);
        final BodyChange dt3 = bodyModel.addchange(dt2, 0.5).dt(baseline, dailyParams3);
        return bodyModel.addchange(bodyModel.avgdt_weighted(BodyModel.RK4wt, dt, dt2, dt3, bodyModel.addchange(dt3, 1.0).dt(baseline, dailyParams2)), 1.0);
    }
    
    public static BodyModel RungeKatta(final BodyModel bodyModel, final Baseline baseline, final DailyParams dailyParams) {
        final BodyChange dt = bodyModel.dt(baseline, dailyParams);
        final BodyChange dt2 = bodyModel.addchange(dt, 0.5).dt(baseline, dailyParams);
        final BodyChange dt3 = bodyModel.addchange(dt2, 0.5).dt(baseline, dailyParams);
        return bodyModel.addchange(bodyModel.avgdt_weighted(BodyModel.RK4wt, dt, dt2, dt3, bodyModel.addchange(dt3, 1.0).dt(baseline, dailyParams)), 1.0);
    }
    
    public static BodyModel Euler(final BodyModel bodyModel, final Baseline baseline, final DailyParams dailyParams) {
        return bodyModel.addchange(bodyModel.dt(baseline, dailyParams), 1.0);
    }
    
    public double getTEE(final Baseline baseline, final DailyParams dailyParams) {
        final double getp = this.getp();
        return (this.getExpend(baseline, dailyParams) + (dailyParams.getCalories() - this.carbflux(baseline, dailyParams)) * ((1.0 - getp) * 18.0 / 9440.0 + getp * 230.0 / 1807.0)) / (1.0 + getp * 230.0 / 1807.0 + (1.0 - getp) * 18.0 / 9440.0);
    }
    
    public double getExpend(final Baseline baseline, final DailyParams dailyParams) {
        final double n = 0.1 * dailyParams.getCalories();
        final double getnewWeight = baseline.getnewWeight(this);
        final double n2 = baseline.getK() + 22.0 * this.getLean() + 3.2 * this.getFat() + dailyParams.getActParam() * getnewWeight + this.getTherm() + n;
        if (dailyParams.flag) {
            System.out.println("Printing in Expend");
            System.out.println("TEF=" + n);
            System.out.println("Weight=" + getnewWeight);
            System.out.println("Expend=" + n2);
        }
        return n2;
    }
    
    public double getp() {
        return 1.9907627118644067 / (1.9907627118644067 + this.fat);
    }
    
    public double carbflux(final Baseline baseline, final DailyParams dailyParams) {
        return dailyParams.getCarbIntake() - baseline.getCarbsIn() / Math.pow(baseline.getGlycogen(), 2.0) * Math.pow(this.glyc, 2.0);
    }
    
    public double Na_imbal(final Baseline baseline, final DailyParams dailyParams) {
        return dailyParams.getSodium() - baseline.getSodium() - 3000.0 * this.decw - 4000.0 * (1.0 - dailyParams.getCarbIntake() / baseline.getCarbsIn());
    }
    
    public double dfdt(final Baseline baseline, final DailyParams dailyParams) {
        return (1.0 - this.getp()) * (dailyParams.getCalories() - this.getTEE(baseline, dailyParams) - this.carbflux(baseline, dailyParams)) / 9440.0;
    }
    
    public double dldt(final Baseline baseline, final DailyParams dailyParams) {
        return this.getp() * (dailyParams.getCalories() - this.getTEE(baseline, dailyParams) - this.carbflux(baseline, dailyParams)) / 1807.0;
    }
    
    public double dgdt(final Baseline baseline, final DailyParams dailyParams) {
        return this.carbflux(baseline, dailyParams) / 4180.0;
    }
    
    public double dDecwdt(final Baseline baseline, final DailyParams dailyParams) {
        return this.Na_imbal(baseline, dailyParams) / 3220.0;
    }
    
    public double dthermdt(final Baseline baseline, final DailyParams dailyParams) {
        return (0.13999999999999999 * dailyParams.getCalories() - this.therm) / 14.0;
    }
    
    public BodyModel addchange(final BodyChange bodyChange, final double n) {
        return new BodyModel(this.fat + n * bodyChange.df(), this.lean + n * bodyChange.dl(), this.glyc + n * bodyChange.dg(), this.decw + n * bodyChange.dDecw(), this.therm + n * bodyChange.dtherm());
    }
    
    public double cals4balance(final Baseline baseline, final double n) {
        final double n2 = baseline.getK() + 22.0 * this.getLean() + 3.2 * this.getFat() + n * this.getWeight(baseline);
        final double getp = this.getp();
        return n2 / (1.0 + getp * 230.0 / 1807.0 + (1.0 - getp) * 18.0 / 9440.0 - ((1.0 - getp) * 18.0 / 9440.0 + getp * 230.0 / 1807.0) - 0.24);
    }
    
    public static BodyModel[] Bodytraj(final Baseline baseline, final DailyParams[] array) {
        final int length = array.length;
        final BodyModel[] array2 = new BodyModel[length];
        array2[0] = new BodyModel(baseline);
        for (int i = 1; i < length; ++i) {
            array2[i] = RungeKatta(array2[i - 1], baseline, array[i - 1], array[i]);
        }
        return array2;
    }
    
    public BodyChange avgdt(final BodyChange... array) {
        double n = 0.0;
        double n2 = 0.0;
        double n3 = 0.0;
        double n4 = 0.0;
        double n5 = 0.0;
        for (int i = 0; i < array.length; ++i) {
            n += array[i].df();
            n2 += array[i].dl();
            n3 += array[i].dg();
            n4 += array[i].dDecw();
            n5 += array[i].dtherm();
        }
        return new BodyChange(n / array.length, n2 / array.length, n3 / array.length, n4 / array.length, n5 / array.length);
    }
    
    public BodyChange avgdt_weighted(final int[] array, final BodyChange... array2) {
        double n = 0.0;
        double n2 = 0.0;
        double n3 = 0.0;
        double n4 = 0.0;
        double n5 = 0.0;
        int n6 = 0;
        for (int i = 0; i < array2.length; ++i) {
            int n7;
            try {
                n7 = array[i];
            }
            catch (Exception ex) {
                n7 = 1;
            }
            if (n7 < 0) {
                n7 = 1;
            }
            n6 += n7;
            n += n7 * array2[i].df();
            n2 += n7 * array2[i].dl();
            n3 += n7 * array2[i].dg();
            n4 += n7 * array2[i].dDecw();
            n5 += n7 * array2[i].dtherm();
        }
        return new BodyChange(n / n6, n2 / n6, n3 / n6, n4 / n6, n5 / n6);
    }
    
    public void print() {
        System.out.println("Fat=" + this.fat);
        System.out.println("Lean=" + this.lean);
        System.out.println("Glycogen=" + this.glyc);
        System.out.println("Decw=" + this.decw);
        System.out.println("Therm=" + this.therm);
    }
    
    public void printdetailed(final Baseline baseline, final DailyParams dailyParams) {
        System.out.println("Fat=" + this.fat);
        System.out.println("Lean=" + this.lean);
        System.out.println("Glycogen=" + this.glyc);
        System.out.println("Decw=" + this.decw);
        System.out.println("Therm=" + this.therm);
        System.out.printf("TEE=%f\n", this.getTEE(baseline, dailyParams));
        System.out.printf("Expend=%f\n", this.getExpend(baseline, dailyParams));
        System.out.printf("p=%f\n", this.getp());
    }
    
    static {
        BodyModel.RK4wt = new int[] { 1, 2, 2, 1 };
    }
    
    public class BodyChange
    {
        private double df;
        private double dl;
        private double dg;
        private double dDecw;
        private double dtherm;
        
        public BodyChange(final double df, final double dl, final double dg, final double dDecw, final double dtherm) {
            this.df = df;
            this.dl = dl;
            this.dg = dg;
            this.dDecw = dDecw;
            this.dtherm = dtherm;
        }
        
        public double df() {
            return this.df;
        }
        
        public double dl() {
            return this.dl;
        }
        
        public double dg() {
            return this.dg;
        }
        
        public double dDecw() {
            return this.dDecw;
        }
        
        public double dtherm() {
            return this.dtherm;
        }
    }
}
