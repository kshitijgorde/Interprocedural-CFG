// 
// Decompiled by Procyon v0.5.30
// 

class Hole3D
{
    public Auxiliary m_auxiliaryChain;
    Auxiliary m_auxiliaryLast;
    public Barrier3D m_barrierChain;
    Barrier3D m_barrierLast;
    public Cup3D m_cup;
    public Duct3D m_ductChain;
    Duct3D m_ductLast;
    public Fairway3D m_fairwayChain;
    Fairway3D m_fairwayLast;
    public Golfball3D m_golfball;
    public House3D m_houseChain;
    House3D m_houseLast;
    public SlidingBridge m_slidingbridgeChain;
    SlidingBridge m_slidingbridgeLast;
    public Slope3D m_slopeChain;
    Slope3D m_slopeLast;
    public Tee3D m_tee;
    public WaterHazard3D m_waterhazardChain;
    WaterHazard3D m_waterhazardLast;
    public Windmill m_windmillChain;
    Windmill m_windmillLast;
    public VantagePoint m_vantagepointChain;
    VantagePoint m_vantagepointLast;
    public String m_stringError;
    public String m_stringException;
    public int m_nPar;
    
    public Hole3D(final String s) {
        String substring = null;
        int i = 0;
        final char[] array = new char[2];
        while (i < s.length() - 1) {
            final int index = s.indexOf(";", i);
            if (index <= i) {
                break;
            }
            try {
                substring = s.substring(i, index);
            }
            catch (Exception ex14) {}
            try {
                substring.getChars(0, 1, array, 0);
            }
            catch (Exception ex15) {}
            switch (array[0]) {
                case 'A': {
                    Auxiliary auxiliaryLast;
                    try {
                        auxiliaryLast = new Auxiliary(substring);
                    }
                    catch (Exception ex) {
                        this.m_stringError = substring;
                        this.m_stringException = ex.getMessage();
                        break;
                    }
                    if (this.m_auxiliaryLast != null) {
                        this.m_auxiliaryLast.m_auxiliaryNext = auxiliaryLast;
                    }
                    else {
                        this.m_auxiliaryChain = auxiliaryLast;
                    }
                    this.m_auxiliaryLast = auxiliaryLast;
                    break;
                }
                case 'B': {
                    Barrier3D barrierLast;
                    try {
                        barrierLast = new Barrier3D(substring);
                    }
                    catch (Exception ex2) {
                        this.m_stringError = substring;
                        this.m_stringException = ex2.getMessage();
                        break;
                    }
                    if (this.m_barrierLast != null) {
                        this.m_barrierLast.m_barrierNext = barrierLast;
                    }
                    else {
                        this.m_barrierChain = barrierLast;
                    }
                    this.m_barrierLast = barrierLast;
                    break;
                }
                case 'C': {
                    if (this.m_cup != null) {
                        this.m_stringError = new String("Multiple cups!");
                        break;
                    }
                    try {
                        this.m_cup = new Cup3D(substring);
                    }
                    catch (Exception ex3) {
                        this.m_stringError = substring;
                        this.m_stringException = ex3.getMessage();
                    }
                    break;
                }
                case 'D': {
                    Duct3D ductLast;
                    try {
                        ductLast = new Duct3D(substring);
                    }
                    catch (Exception ex4) {
                        this.m_stringError = substring;
                        this.m_stringException = ex4.getMessage();
                        break;
                    }
                    if (this.m_ductLast != null) {
                        this.m_ductLast.m_ductNext = ductLast;
                    }
                    else {
                        this.m_ductChain = ductLast;
                    }
                    this.m_ductLast = ductLast;
                    break;
                }
                case 'E': {
                    SlidingBridge slidingbridgeLast;
                    try {
                        slidingbridgeLast = new SlidingBridge(substring);
                    }
                    catch (Exception ex5) {
                        this.m_stringError = substring;
                        this.m_stringException = ex5.getMessage();
                        break;
                    }
                    if (this.m_slidingbridgeLast != null) {
                        this.m_slidingbridgeLast.m_slidingbridgeNext = slidingbridgeLast;
                    }
                    else {
                        this.m_slidingbridgeChain = slidingbridgeLast;
                    }
                    this.m_slidingbridgeLast = slidingbridgeLast;
                    break;
                }
                case 'F': {
                    Fairway3D fairwayLast;
                    try {
                        fairwayLast = new Fairway3D(substring);
                    }
                    catch (Exception ex6) {
                        this.m_stringError = substring;
                        this.m_stringException = ex6.getMessage();
                        break;
                    }
                    if (this.m_fairwayLast != null) {
                        this.m_fairwayLast.m_fairwayNext = fairwayLast;
                    }
                    else {
                        this.m_fairwayChain = fairwayLast;
                    }
                    this.m_fairwayLast = fairwayLast;
                    break;
                }
                case 'H': {
                    House3D houseLast;
                    try {
                        houseLast = new House3D(substring);
                    }
                    catch (Exception ex7) {
                        this.m_stringError = substring;
                        this.m_stringException = ex7.getMessage();
                        break;
                    }
                    if (this.m_houseLast != null) {
                        this.m_houseLast.m_houseNext = houseLast;
                    }
                    else {
                        this.m_houseChain = houseLast;
                    }
                    this.m_houseLast = houseLast;
                    break;
                }
                case 'M': {
                    Windmill windmillLast;
                    try {
                        windmillLast = new Windmill(substring);
                    }
                    catch (Exception ex8) {
                        this.m_stringError = substring;
                        this.m_stringException = ex8.getMessage();
                        break;
                    }
                    if (this.m_windmillLast != null) {
                        this.m_windmillLast.m_windmillNext = windmillLast;
                    }
                    else {
                        this.m_windmillChain = windmillLast;
                    }
                    this.m_windmillLast = windmillLast;
                    break;
                }
                case 'P': {
                    double[] numericArgs;
                    try {
                        numericArgs = Parse.NumericArgs(substring);
                    }
                    catch (Exception ex9) {
                        this.m_stringError = substring;
                        this.m_stringException = ex9.getMessage();
                        break;
                    }
                    if (numericArgs.length != 1) {
                        this.m_stringError = substring;
                        this.m_stringException = new String("Par takes 1 argument");
                        break;
                    }
                    this.m_nPar = (int)numericArgs[0];
                    break;
                }
                case 'S': {
                    Slope3D slopeLast;
                    try {
                        slopeLast = new Slope3D(substring);
                    }
                    catch (Exception ex10) {
                        this.m_stringError = substring;
                        this.m_stringException = ex10.getMessage();
                        break;
                    }
                    if (this.m_slopeLast != null) {
                        this.m_slopeLast.m_slopeNext = slopeLast;
                    }
                    else {
                        this.m_slopeChain = slopeLast;
                    }
                    this.m_slopeLast = slopeLast;
                    break;
                }
                case 'T': {
                    if (this.m_tee != null) {
                        this.m_stringError = new String("Multiple tees!");
                        break;
                    }
                    try {
                        this.m_tee = new Tee3D(substring);
                    }
                    catch (Exception ex11) {
                        this.m_stringError = substring;
                        this.m_stringException = ex11.getMessage();
                        break;
                    }
                    this.m_golfball = new Golfball3D(this.m_tee);
                    break;
                }
                case 'V': {
                    VantagePoint vantagepointLast;
                    try {
                        vantagepointLast = new VantagePoint(substring);
                    }
                    catch (Exception ex12) {
                        this.m_stringError = substring;
                        this.m_stringException = ex12.getMessage();
                        break;
                    }
                    if (this.m_vantagepointLast != null) {
                        this.m_vantagepointLast.m_vantagepointNext = vantagepointLast;
                    }
                    else {
                        this.m_vantagepointChain = vantagepointLast;
                    }
                    this.m_vantagepointLast = vantagepointLast;
                    break;
                }
                case 'W': {
                    WaterHazard3D waterhazardLast;
                    try {
                        waterhazardLast = new WaterHazard3D(substring);
                    }
                    catch (Exception ex13) {
                        this.m_stringError = substring;
                        this.m_stringException = ex13.getMessage();
                        break;
                    }
                    if (this.m_waterhazardLast != null) {
                        this.m_waterhazardLast.m_waterhazardNext = waterhazardLast;
                    }
                    else {
                        this.m_waterhazardChain = waterhazardLast;
                    }
                    this.m_waterhazardLast = waterhazardLast;
                    break;
                }
                default: {
                    this.m_stringError = substring;
                    break;
                }
            }
            if (this.m_stringError != null) {
                break;
            }
            i = index + 1;
        }
        if (this.m_stringError == null && this.m_tee == null) {
            this.m_stringError = new String("Tee missing");
        }
        if (this.m_stringError == null && this.m_cup == null) {
            this.m_stringError = new String("Cup missing");
        }
        if (this.m_stringError == null && this.m_vantagepointChain == null) {
            this.m_stringError = new String("No vantage points");
        }
    }
}
