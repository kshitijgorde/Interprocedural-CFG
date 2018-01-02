import netscape.javascript.JSObject;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class di extends implements
{
    private String B;
    private String C;
    private String D;
    private String E;
    private break kb;
    private Vector F;
    private Vector G;
    private Vector H;
    private String I;
    private String J;
    private String K;
    private double[] L;
    private double[] M;
    private double[] N;
    private double[] O;
    private double[] P;
    private double[] Q;
    private double[] info;
    private static final String R = "try{";
    private static final String S = "}\n\tcatch(e){\n\t\tvar msg=\"\";\n\t\tif(__isIEBrowser() && typeof(e.description)!=\"undefined\")\n\t\t\tmsg=e.description;\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(e+\" \"+msg);\n\t\tthrow(msg);\n\t}\n";
    private static final String T = "function __isIEBrowser(){\n\tvar agent=navigator.userAgent.toLowerCase();\n\treturn ((agent.indexOf(\"msie\") != -1) || (agent.indexOf(\"opera\") != -1))\n\t\t\t|| ((agent.indexOf(\"konqueror\") != -1) || (agent.indexOf(\"safari\") != -1));\n}\nfunction __checkParams(funcName, params, types, minParamValues){\n\tvar msg=\"\";\n\tvar error=false;\n\tif(types.length!=params.length){\n\t\terror=true;\n\t\tmsg+=\"Invalid number of arguments passed to function: '\" + funcName + \"'\";\n\t\tmsg+=\"\\nFunction '\" + funcName + \"' requires \" + types.length + \" arguments (\" + params.length + \" passed)\";\n\t}\n\tif(!error)\n\tfor(var i=0;i<types.length;i++){\n\t\tif((types[i] == \"array\" && (!(params[i] instanceof Object) || params[i].constructor.toString().indexOf(\"Array\") == -1))\n\t\t\t|| (types[i] != \"array\" && typeof(params[i]) != types[i])){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument types, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid type of argument \"+ (i+1) + \" '\" +typeof(params[i])+ \"', required type: \" + types[i];\n\t\t}\n\t}\n\tif(!error && typeof(minParamValues)!=\"undefined\")\n\tfor(var i=0;i<minParamValues.length;i++){\n\t\tif(typeof(params[i]) == \"number\" && params[i]<minParamValues[i]){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument value, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid value of argument \"+ (i+1) + \", value must be >= \" + minParamValues[i];\n\t\t}\n\t}\n\tif(error){\n\t\tthrow(\"Error: \"+msg);\n\t}\n}\nfunction Max(data,period){\n\t__checkParams(\"Max(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMax(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar max=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmax=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]>max) max=data[i-j];\n\t\tresult[i]=max;\n\t}\n\treturn result;\n}\nfunction Min(data,period){\n\t__checkParams(\"Min(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMin(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar min=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmin=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]<min) min=data[i-j];\n\t\tresult[i]=min;\n\t}\n\treturn result;\n}\nfunction ExpAvg(data,period){\n\t__checkParams(\"ExpAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateExpAvg(data,period);\n\tif(period<1) period=1;\n\tvar k = 2.0 / (period + 1);\n\tvar result=new Array(data.length);\n\tif(data.length>0) result[0]=data[0];\n\tfor(var i=1;i<data.length;i++)\n\t\tresult[i] = result[i - 1] * (1 - k) + data[i] * k;\n\treturn result;\n}\nfunction SimpleAvg(data,period){\n\t__checkParams(\"SimpleAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateSimpleAvg(data,period);\n\tif(period<1) period=1;\n\tvar sum=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length;i++){\n\t\tsum+=data[i];\n\t\tif(i-period>=0){\n\t\t\tsum-=data[i-period];\n\t\t\tresult[i]=sum/period;\n\t\t}\n\t\telse\n\t\t\tresult[i]=sum/(i+1);\n\t}\n\treturn result;\n}\nfunction StdDev(data,period){\n\t__checkParams(\"StdDev(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>200 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateStdDev(data,period);\n\tif(period<1) period=1;\n\tvar avg=SimpleAvg(data,period);\n\tvar dev=0.0;\n\tvar dif=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length && i<period;i++)\n\t\tresult[i]=0.0;\n\tfor(var i=period-1;i<data.length;i++){\n\t\tdev=0.0;\n\t\tfor(var j = i; j > i - period; j--){\n\t\t\tdif = data[j] - avg[i];\n\t\t\tdev += dif * dif;\n\t\t}\n\t\tresult[i] = Math.sqrt(dev / (period));\n\t}\n\treturn result;\n}\nfunction CreateArray(len){\n\t__checkParams(\"CreateArray(length)\",arguments,[\"number\"],[0]);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=0.0;\n\treturn tab;\n}\nfunction Param(val){\n\t__checkParams(\"Param(paramNumber)\",arguments,[\"number\"],[1]);\n\treturn document.FnChartsApplet.getParam(val);\n}\nfunction __GetValues(type){\n\tif(!__isIEBrowser()){\n\t\tvar tab=document.FnChartsApplet.getValues(type);\n\t\treturn tab;\n\t}\n\tvar len=document.FnChartsApplet.getValuesLength(type);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=document.FnChartsApplet.getValue(type,i);\n\treturn tab;\n}\nfunction Open(){\n\t__checkParams(\"Open()\",arguments,[]);\n\treturn __GetValues(1);\n}\nfunction High(){\n\t__checkParams(\"High()\",arguments,[]);\n\treturn __GetValues(2);\n}\nfunction Low(){\n\t__checkParams(\"Low()\",arguments,[]);\n\treturn __GetValues(3);\n}\nfunction Close(){\n\t__checkParams(\"Close()\",arguments,[]);\n\treturn __GetValues(4);\n}\nfunction Volume(){\n\t__checkParams(\"Volume()\",arguments,[]);\n\treturn __GetValues(5);\n}\nfunction OpenInt(){\n\t__checkParams(\"OpenInt()\",arguments,[]);\n\treturn __GetValues(6);\n}\nfunction Info(){\n\t__checkParams(\"Info()\",arguments,[]);\n\treturn __GetValues(7);\n}\nfunction AddGraph(graphData,firstValidIndex){\n\tif(typeof(firstValidIndex)==\"undefined\")\n\t\tfirstValidIndex=0;\n\t__checkParams(\"AddGraph(dataArray,firstValidIndex)\",[graphData,firstValidIndex],[\"array\",\"number\"],[,0]);\n\tvar closeLength=document.FnChartsApplet.getValuesLength(4);\n\tif(graphData.length!=closeLength)\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(\"Invalid length of dataArray passed to 'AddGraph()'\\nThe length must the same as the length of arrays returned by Close(),Open() etc. \");\n\tif(!__isIEBrowser()){\n\t\tdocument.FnChartsApplet.addGraphData(graphData,firstValidIndex);\n\t\treturn;\n\t}\n\tdocument.FnChartsApplet.beginGraphData(graphData.length);\n\tfor(var i=0;i<graphData.length;i++)\n\t\tdocument.FnChartsApplet.addGraphDataPoint(i,graphData[i]);\n\tdocument.FnChartsApplet.endGraphData(firstValidIndex);\n}\nfunction AddHorizLine(value){\n\t__checkParams(\"AddHorizLine(value)\",arguments,[\"number\"]);\n\tdocument.FnChartsApplet.addHorizLine(value);\n}\nfunction AddBuySignal(index){\n\t__checkParams(\"AddBuySignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addBuySignal(index);\n}\nfunction AddSellSignal(index){\n\t__checkParams(\"AddSellSignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addSellSignal(index);\n}\n";
    
    public di(final String b, final String s, final int[] array, final String c, final break kb) {
        super(s, 0, array, null, kb._());
        this.F = new Vector();
        this.G = new Vector();
        this.H = new Vector();
        this.I = null;
        this.J = null;
        this.K = null;
        this.kb = kb;
        this.B = b;
        this.C = c;
        super.r = new int[3];
        this.W();
    }
    
    public bj _() {
        String string = "";
        if (super.Ua != null) {
            for (int i = 0; i < super.Ua.length; ++i) {
                string = string + super.Ua[i] + ",";
            }
        }
        return new bj(this.B.substring(1), super.name, string, this.C);
    }
    
    private static double[] _(final double[] array) {
        if (array == null) {
            return new double[0];
        }
        final double[] array2 = new double[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    protected void X() {
        this.F.removeAllElements();
        this.G.removeAllElements();
        this.H.removeAllElements();
        super.t = null;
        super.z = null;
        super.u = 0;
        for (int i = 0; i < super.r.length; ++i) {
            super.r[i] = 0;
        }
        super.A = null;
        this.D = null;
        this.E = null;
        this.kb.a(this);
        try {
            this.L = _(super.s.g());
            this.M = _(super.s.a());
            this.N = _(super.s.b());
            this.O = _(super.s._());
            this.P = _(super.s.f());
            this.Q = _(super.s.h());
            this.info = _(super.s.i());
            final JSObject jsObject = (JSObject)this.kb._();
            if (this.I == null || this.J == null || this.K == null) {
                final String h = this.kb.H();
                if (h != null && h.length() > 0) {
                    this.I = catch.a("try{", "FnChartsApplet", h);
                    this.J = catch.a("}\n\tcatch(e){\n\t\tvar msg=\"\";\n\t\tif(__isIEBrowser() && typeof(e.description)!=\"undefined\")\n\t\t\tmsg=e.description;\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(e+\" \"+msg);\n\t\tthrow(msg);\n\t}\n", "FnChartsApplet", h);
                    this.K = catch.a("function __isIEBrowser(){\n\tvar agent=navigator.userAgent.toLowerCase();\n\treturn ((agent.indexOf(\"msie\") != -1) || (agent.indexOf(\"opera\") != -1))\n\t\t\t|| ((agent.indexOf(\"konqueror\") != -1) || (agent.indexOf(\"safari\") != -1));\n}\nfunction __checkParams(funcName, params, types, minParamValues){\n\tvar msg=\"\";\n\tvar error=false;\n\tif(types.length!=params.length){\n\t\terror=true;\n\t\tmsg+=\"Invalid number of arguments passed to function: '\" + funcName + \"'\";\n\t\tmsg+=\"\\nFunction '\" + funcName + \"' requires \" + types.length + \" arguments (\" + params.length + \" passed)\";\n\t}\n\tif(!error)\n\tfor(var i=0;i<types.length;i++){\n\t\tif((types[i] == \"array\" && (!(params[i] instanceof Object) || params[i].constructor.toString().indexOf(\"Array\") == -1))\n\t\t\t|| (types[i] != \"array\" && typeof(params[i]) != types[i])){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument types, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid type of argument \"+ (i+1) + \" '\" +typeof(params[i])+ \"', required type: \" + types[i];\n\t\t}\n\t}\n\tif(!error && typeof(minParamValues)!=\"undefined\")\n\tfor(var i=0;i<minParamValues.length;i++){\n\t\tif(typeof(params[i]) == \"number\" && params[i]<minParamValues[i]){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument value, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid value of argument \"+ (i+1) + \", value must be >= \" + minParamValues[i];\n\t\t}\n\t}\n\tif(error){\n\t\tthrow(\"Error: \"+msg);\n\t}\n}\nfunction Max(data,period){\n\t__checkParams(\"Max(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMax(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar max=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmax=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]>max) max=data[i-j];\n\t\tresult[i]=max;\n\t}\n\treturn result;\n}\nfunction Min(data,period){\n\t__checkParams(\"Min(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMin(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar min=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmin=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]<min) min=data[i-j];\n\t\tresult[i]=min;\n\t}\n\treturn result;\n}\nfunction ExpAvg(data,period){\n\t__checkParams(\"ExpAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateExpAvg(data,period);\n\tif(period<1) period=1;\n\tvar k = 2.0 / (period + 1);\n\tvar result=new Array(data.length);\n\tif(data.length>0) result[0]=data[0];\n\tfor(var i=1;i<data.length;i++)\n\t\tresult[i] = result[i - 1] * (1 - k) + data[i] * k;\n\treturn result;\n}\nfunction SimpleAvg(data,period){\n\t__checkParams(\"SimpleAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateSimpleAvg(data,period);\n\tif(period<1) period=1;\n\tvar sum=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length;i++){\n\t\tsum+=data[i];\n\t\tif(i-period>=0){\n\t\t\tsum-=data[i-period];\n\t\t\tresult[i]=sum/period;\n\t\t}\n\t\telse\n\t\t\tresult[i]=sum/(i+1);\n\t}\n\treturn result;\n}\nfunction StdDev(data,period){\n\t__checkParams(\"StdDev(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>200 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateStdDev(data,period);\n\tif(period<1) period=1;\n\tvar avg=SimpleAvg(data,period);\n\tvar dev=0.0;\n\tvar dif=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length && i<period;i++)\n\t\tresult[i]=0.0;\n\tfor(var i=period-1;i<data.length;i++){\n\t\tdev=0.0;\n\t\tfor(var j = i; j > i - period; j--){\n\t\t\tdif = data[j] - avg[i];\n\t\t\tdev += dif * dif;\n\t\t}\n\t\tresult[i] = Math.sqrt(dev / (period));\n\t}\n\treturn result;\n}\nfunction CreateArray(len){\n\t__checkParams(\"CreateArray(length)\",arguments,[\"number\"],[0]);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=0.0;\n\treturn tab;\n}\nfunction Param(val){\n\t__checkParams(\"Param(paramNumber)\",arguments,[\"number\"],[1]);\n\treturn document.FnChartsApplet.getParam(val);\n}\nfunction __GetValues(type){\n\tif(!__isIEBrowser()){\n\t\tvar tab=document.FnChartsApplet.getValues(type);\n\t\treturn tab;\n\t}\n\tvar len=document.FnChartsApplet.getValuesLength(type);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=document.FnChartsApplet.getValue(type,i);\n\treturn tab;\n}\nfunction Open(){\n\t__checkParams(\"Open()\",arguments,[]);\n\treturn __GetValues(1);\n}\nfunction High(){\n\t__checkParams(\"High()\",arguments,[]);\n\treturn __GetValues(2);\n}\nfunction Low(){\n\t__checkParams(\"Low()\",arguments,[]);\n\treturn __GetValues(3);\n}\nfunction Close(){\n\t__checkParams(\"Close()\",arguments,[]);\n\treturn __GetValues(4);\n}\nfunction Volume(){\n\t__checkParams(\"Volume()\",arguments,[]);\n\treturn __GetValues(5);\n}\nfunction OpenInt(){\n\t__checkParams(\"OpenInt()\",arguments,[]);\n\treturn __GetValues(6);\n}\nfunction Info(){\n\t__checkParams(\"Info()\",arguments,[]);\n\treturn __GetValues(7);\n}\nfunction AddGraph(graphData,firstValidIndex){\n\tif(typeof(firstValidIndex)==\"undefined\")\n\t\tfirstValidIndex=0;\n\t__checkParams(\"AddGraph(dataArray,firstValidIndex)\",[graphData,firstValidIndex],[\"array\",\"number\"],[,0]);\n\tvar closeLength=document.FnChartsApplet.getValuesLength(4);\n\tif(graphData.length!=closeLength)\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(\"Invalid length of dataArray passed to 'AddGraph()'\\nThe length must the same as the length of arrays returned by Close(),Open() etc. \");\n\tif(!__isIEBrowser()){\n\t\tdocument.FnChartsApplet.addGraphData(graphData,firstValidIndex);\n\t\treturn;\n\t}\n\tdocument.FnChartsApplet.beginGraphData(graphData.length);\n\tfor(var i=0;i<graphData.length;i++)\n\t\tdocument.FnChartsApplet.addGraphDataPoint(i,graphData[i]);\n\tdocument.FnChartsApplet.endGraphData(firstValidIndex);\n}\nfunction AddHorizLine(value){\n\t__checkParams(\"AddHorizLine(value)\",arguments,[\"number\"]);\n\tdocument.FnChartsApplet.addHorizLine(value);\n}\nfunction AddBuySignal(index){\n\t__checkParams(\"AddBuySignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addBuySignal(index);\n}\nfunction AddSellSignal(index){\n\t__checkParams(\"AddSellSignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addSellSignal(index);\n}\n", "FnChartsApplet", h);
                }
                else {
                    this.I = "try{";
                    this.J = "}\n\tcatch(e){\n\t\tvar msg=\"\";\n\t\tif(__isIEBrowser() && typeof(e.description)!=\"undefined\")\n\t\t\tmsg=e.description;\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(e+\" \"+msg);\n\t\tthrow(msg);\n\t}\n";
                    this.K = "function __isIEBrowser(){\n\tvar agent=navigator.userAgent.toLowerCase();\n\treturn ((agent.indexOf(\"msie\") != -1) || (agent.indexOf(\"opera\") != -1))\n\t\t\t|| ((agent.indexOf(\"konqueror\") != -1) || (agent.indexOf(\"safari\") != -1));\n}\nfunction __checkParams(funcName, params, types, minParamValues){\n\tvar msg=\"\";\n\tvar error=false;\n\tif(types.length!=params.length){\n\t\terror=true;\n\t\tmsg+=\"Invalid number of arguments passed to function: '\" + funcName + \"'\";\n\t\tmsg+=\"\\nFunction '\" + funcName + \"' requires \" + types.length + \" arguments (\" + params.length + \" passed)\";\n\t}\n\tif(!error)\n\tfor(var i=0;i<types.length;i++){\n\t\tif((types[i] == \"array\" && (!(params[i] instanceof Object) || params[i].constructor.toString().indexOf(\"Array\") == -1))\n\t\t\t|| (types[i] != \"array\" && typeof(params[i]) != types[i])){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument types, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid type of argument \"+ (i+1) + \" '\" +typeof(params[i])+ \"', required type: \" + types[i];\n\t\t}\n\t}\n\tif(!error && typeof(minParamValues)!=\"undefined\")\n\tfor(var i=0;i<minParamValues.length;i++){\n\t\tif(typeof(params[i]) == \"number\" && params[i]<minParamValues[i]){\n\t\t\t\terror=true;\n\t\t\t\tif(msg.length==0)\n\t\t\t\t\tmsg+=\"Invalid argument value, function: '\" + funcName +\"'\";\n\t\t\t\tmsg+=\"\\nInvalid value of argument \"+ (i+1) + \", value must be >= \" + minParamValues[i];\n\t\t}\n\t}\n\tif(error){\n\t\tthrow(\"Error: \"+msg);\n\t}\n}\nfunction Max(data,period){\n\t__checkParams(\"Max(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMax(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar max=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmax=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]>max) max=data[i-j];\n\t\tresult[i]=max;\n\t}\n\treturn result;\n}\nfunction Min(data,period){\n\t__checkParams(\"Min(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateMin(data,period);\n\tif(period<1) period=1;\n\tvar result=new Array(data.length);\n\tvar min=0;\n\tfor(var i=0;i<data.length;i++){\n\t\tmin=data[i];\n\t\tfor(var j=1;j<period && i-j>=0;j++)\n\t\t\tif(data[i-j]<min) min=data[i-j];\n\t\tresult[i]=min;\n\t}\n\treturn result;\n}\nfunction ExpAvg(data,period){\n\t__checkParams(\"ExpAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateExpAvg(data,period);\n\tif(period<1) period=1;\n\tvar k = 2.0 / (period + 1);\n\tvar result=new Array(data.length);\n\tif(data.length>0) result[0]=data[0];\n\tfor(var i=1;i<data.length;i++)\n\t\tresult[i] = result[i - 1] * (1 - k) + data[i] * k;\n\treturn result;\n}\nfunction SimpleAvg(data,period){\n\t__checkParams(\"SimpleAvg(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>1500 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateSimpleAvg(data,period);\n\tif(period<1) period=1;\n\tvar sum=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length;i++){\n\t\tsum+=data[i];\n\t\tif(i-period>=0){\n\t\t\tsum-=data[i-period];\n\t\t\tresult[i]=sum/period;\n\t\t}\n\t\telse\n\t\t\tresult[i]=sum/(i+1);\n\t}\n\treturn result;\n}\nfunction StdDev(data,period){\n\t__checkParams(\"StdDev(dataArray,range)\",arguments,[\"array\",\"number\"],[,1]);\n\tif(data.length>200 && !__isIEBrowser())\n\t\treturn document.FnChartsApplet.calculateStdDev(data,period);\n\tif(period<1) period=1;\n\tvar avg=SimpleAvg(data,period);\n\tvar dev=0.0;\n\tvar dif=0.0;\n\tvar result=new Array(data.length);\n\tfor(var i=0;i<data.length && i<period;i++)\n\t\tresult[i]=0.0;\n\tfor(var i=period-1;i<data.length;i++){\n\t\tdev=0.0;\n\t\tfor(var j = i; j > i - period; j--){\n\t\t\tdif = data[j] - avg[i];\n\t\t\tdev += dif * dif;\n\t\t}\n\t\tresult[i] = Math.sqrt(dev / (period));\n\t}\n\treturn result;\n}\nfunction CreateArray(len){\n\t__checkParams(\"CreateArray(length)\",arguments,[\"number\"],[0]);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=0.0;\n\treturn tab;\n}\nfunction Param(val){\n\t__checkParams(\"Param(paramNumber)\",arguments,[\"number\"],[1]);\n\treturn document.FnChartsApplet.getParam(val);\n}\nfunction __GetValues(type){\n\tif(!__isIEBrowser()){\n\t\tvar tab=document.FnChartsApplet.getValues(type);\n\t\treturn tab;\n\t}\n\tvar len=document.FnChartsApplet.getValuesLength(type);\n\tvar tab=new Array(len);\n\tfor(var i=0;i<len;i++)\n\t\ttab[i]=document.FnChartsApplet.getValue(type,i);\n\treturn tab;\n}\nfunction Open(){\n\t__checkParams(\"Open()\",arguments,[]);\n\treturn __GetValues(1);\n}\nfunction High(){\n\t__checkParams(\"High()\",arguments,[]);\n\treturn __GetValues(2);\n}\nfunction Low(){\n\t__checkParams(\"Low()\",arguments,[]);\n\treturn __GetValues(3);\n}\nfunction Close(){\n\t__checkParams(\"Close()\",arguments,[]);\n\treturn __GetValues(4);\n}\nfunction Volume(){\n\t__checkParams(\"Volume()\",arguments,[]);\n\treturn __GetValues(5);\n}\nfunction OpenInt(){\n\t__checkParams(\"OpenInt()\",arguments,[]);\n\treturn __GetValues(6);\n}\nfunction Info(){\n\t__checkParams(\"Info()\",arguments,[]);\n\treturn __GetValues(7);\n}\nfunction AddGraph(graphData,firstValidIndex){\n\tif(typeof(firstValidIndex)==\"undefined\")\n\t\tfirstValidIndex=0;\n\t__checkParams(\"AddGraph(dataArray,firstValidIndex)\",[graphData,firstValidIndex],[\"array\",\"number\"],[,0]);\n\tvar closeLength=document.FnChartsApplet.getValuesLength(4);\n\tif(graphData.length!=closeLength)\n\t\tdocument.FnChartsApplet.setJSExceptionMessage(\"Invalid length of dataArray passed to 'AddGraph()'\\nThe length must the same as the length of arrays returned by Close(),Open() etc. \");\n\tif(!__isIEBrowser()){\n\t\tdocument.FnChartsApplet.addGraphData(graphData,firstValidIndex);\n\t\treturn;\n\t}\n\tdocument.FnChartsApplet.beginGraphData(graphData.length);\n\tfor(var i=0;i<graphData.length;i++)\n\t\tdocument.FnChartsApplet.addGraphDataPoint(i,graphData[i]);\n\tdocument.FnChartsApplet.endGraphData(firstValidIndex);\n}\nfunction AddHorizLine(value){\n\t__checkParams(\"AddHorizLine(value)\",arguments,[\"number\"]);\n\tdocument.FnChartsApplet.addHorizLine(value);\n}\nfunction AddBuySignal(index){\n\t__checkParams(\"AddBuySignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addBuySignal(index);\n}\nfunction AddSellSignal(index){\n\t__checkParams(\"AddSellSignal(index)\",arguments,[\"number\"],[0]);\n\tdocument.FnChartsApplet.addSellSignal(index);\n}\n";
                }
            }
            String s = this.I + this.C + this.J + "\n" + this.K;
            if (this.kb.b().l() && !this.kb.b().m()) {
                s = s.replace('\r', ' ').replace('\n', ' ');
            }
            jsObject.eval(s);
            super.u = this.F.size();
            if (super.u == 0) {
                super.t = null;
            }
            else {
                super.t = new double[super.u][this.O.length];
                for (int j = 0; j < this.F.size(); ++j) {
                    final double[] array = this.F.elementAt(j);
                    for (int n = 0; n < array.length && n < this.O.length; ++n) {
                        super.t[j][n] = array[n];
                    }
                }
                this.F.removeAllElements();
                for (int n2 = 0; n2 < super.t.length && n2 < super.r.length; ++n2) {
                    for (int n3 = super.r[n2], n4 = 0; n4 < n3 && n3 < super.t[0].length; ++n4) {
                        super.t[n2][n4] = super.t[n2][n3];
                    }
                }
                super.z = new byte[this.O.length];
                for (int k = 0; k < this.G.size(); ++k) {
                    final int intValue = this.G.elementAt(k);
                    if (intValue >= 0 && intValue < super.z.length) {
                        super.z[intValue] = 1;
                    }
                }
                this.G.removeAllElements();
                for (int l = 0; l < this.H.size(); ++l) {
                    final int intValue2 = this.H.elementAt(l);
                    if (intValue2 >= 0 && intValue2 < super.z.length) {
                        super.z[intValue2] = -1;
                    }
                }
                this.H.removeAllElements();
            }
            if (this.E != null) {
                this.D = this.E;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            if (this.E != null) {
                this.D = this.E + "\n" + ex.getMessage();
            }
            else {
                this.D = ex.getMessage();
            }
        }
        final double[] m = null;
        this.info = m;
        this.Q = m;
        this.P = m;
        this.O = m;
        this.N = m;
        this.M = m;
        this.L = m;
    }
    
    public void b(final double[] array, int n) {
        if (array == null || this.O == null || array.length != this.O.length) {
            return;
        }
        if (this.F.size() >= 3) {
            return;
        }
        if (n < 0 || n >= this.O.length) {
            n = 0;
        }
        if (this.F.size() < super.r.length) {
            super.r[this.F.size()] = n;
        }
        this.F.addElement(array);
    }
    
    protected void W() {
    }
    
    public int _(final int n) {
        if (n >= 0 && n < super.r.length) {
            return super.r[n];
        }
        return 0;
    }
    
    public String toString() {
        return this.B;
    }
    
    public String I() {
        if (this.D != null) {
            return this.D;
        }
        return super.name;
    }
    
    public int m() {
        if (super.y) {
            this.X();
        }
        super.y = false;
        return super.m();
    }
    
    public int n() {
        if (super.y) {
            this.X();
        }
        super.y = false;
        if (super.A == null) {
            return 0;
        }
        return super.A.length;
    }
    
    public double _(final int n) {
        if (super.y) {
            this.X();
        }
        super.y = false;
        if (super.A == null) {
            return 0.0;
        }
        return super.A[n];
    }
    
    public void b(final double n) {
        double[] a;
        if (super.A == null) {
            a = new double[] { 0.0 };
        }
        else {
            a = new double[super.A.length + 1];
            for (int i = 0; i < super.A.length; ++i) {
                a[i] = super.A[i];
            }
        }
        a[a.length - 1] = n;
        super.A = a;
    }
    
    public void addBuySignal(final int n) {
        if (n >= 0 && n < this.O.length) {
            this.G.addElement(new Integer(n));
        }
    }
    
    public void addSellSignal(final int n) {
        if (n >= 0 && n < this.O.length) {
            this.H.addElement(new Integer(n));
        }
    }
    
    public double[] j() {
        return this.O;
    }
    
    public double[] k() {
        return this.M;
    }
    
    public double[] l() {
        return this.N;
    }
    
    public double[] m() {
        return this.L;
    }
    
    public double[] n() {
        return this.Q;
    }
    
    public double[] c() {
        return this.P;
    }
    
    public double[] d() {
        return this.info;
    }
    
    public void l(final String e) {
        if (this.E != null) {
            this.E = this.E + "\n" + e;
        }
        else {
            this.E = e;
        }
    }
}
